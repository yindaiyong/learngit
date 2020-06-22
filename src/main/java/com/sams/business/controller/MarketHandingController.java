package com.sams.business.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sams.common.utils.MyMapUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.ITask;
import com.sams.batchfile.common.MultiThreadUtils;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.FundQuotTmpService;
import com.sams.batchfile.service.ProcessStepInfoService;
import com.sams.batchfile.service.ProcessingStateService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ListUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProcessStepInfo;
import com.sams.custom.model.result.MarketHandingResult;
import com.sams.sys.controller.SysDictController;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/marketHanding")
public class MarketHandingController  extends BaseController{


	// 创建多线程处理任务
	public MultiThreadUtils<Integer> threadUtils = null ;

	@Autowired
	private FundMarketProcessorService fundMarketProcessorService;
	
	@Autowired
	private FundMarketService fundMarketService;
	
	@Autowired
	private	FundQuotTmpService fundQuotTmpService;
	
	@Autowired
	private ProcessingStateService processingStateService;
	
	@Autowired
	private ProcessStepInfoService processStepInfoService;
	
	@Autowired
	private FundMarketCfmServier fundMarketCfmService;
	
	@Autowired
	private ChannelInfoService channelInfoService;
	
	private static Logger LOGGER = LoggerFactory.getLogger(DayEndProcessorController.class);
	
	/**
	 * 行情信息处理页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/business/marketHanding";
    }
	
	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getMarketHandingData")
	@ResponseBody
	public PageInfo getParProductTempInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request,String tradeDate) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.containsKey("psBusinessDate")){
			condition.put("tradeDate", MyMapUtils.getStringNotNull(condition,"psBusinessDate").replaceAll("-", ""));
		}else{
			condition.put("tradeDate", tradeDate.replaceAll("-", ""));
		}
		pageInfo.setPageResult(processingStateService.findMarketHandingCondition(pageInfo, condition));
		/*String sysDate = DateFormatUtils.format(getSysDate(), DateUtils.FORMAT_STR_DATE8);
		if(Long.parseLong(condition.get("tradeDate").toString().replaceAll("-", ""))>=Long.parseLong(sysDate)){
			pageInfo.setPageResult(processingStateService.findMarketHandingCondition(pageInfo, condition));
		}else{
			pageInfo.setPageResult(processingStateService.findMarketHandingHistory(pageInfo, condition));
		}*/
		return pageInfo;
	}
	
	
	/**
     * 行情信息处理
     * @return
     */
    @RequestMapping(value = "/marketProcessing", method = RequestMethod.POST)
    @ResponseBody
    public Object marketProcessing(String tradeDate) {
    	LOGGER.info("行情信息处理开始");
    	String userName = getLoginName();
    	//需要修改产品状态的渠道
    	List<String>  updateList = fundMarketService.selectAllUpdateChannels(tradeDate);
    	if(updateList!=null&&updateList.size()!=0){
        	LOGGER.info("需要修改产品状态的渠道有"+updateList.toString());
    		for(String channelCode:updateList){
            	Map<String,String> inputMap = Maps.newHashMap();
            	inputMap.put("TRADEDATE", tradeDate);
            	inputMap.put("CHANNELCODE", channelCode);
            	fundMarketService.changeProductInfoFlag(inputMap);
        	}
    	}
    	
    	//查询所有的在用渠道编号（需要生成行情文件的渠道）
    	List  channelCodeList=fundMarketService.selectAllUsedChannels(tradeDate);
    	LOGGER.info("查询可生成行情文件渠道有"+channelCodeList.toString());
    	Map<String,Object> retMap = Maps.newHashMap();
    	retMap = ListUtils.checkListIsEmpty(channelCodeList, ExceptionConStants.retCode_List0001);
    	threadUtils =MultiThreadUtils.newInstance(channelCodeList.size());
    	if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
    	
    	//连接FTP
    	if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
    		LOGGER.info("FTP已实例化");
    	}
    	//删除行情临时表数据
    	fundQuotTmpService.truncateFundQuotTmp();
    	LOGGER.info("删除行情临时表数据");
    	ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) fundMarketProcessorService;
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("TRADEDATE", tradeDate);
        params.put("PROCESSSTEPNO", Const.FUND_MARKET_PROCESS_CODE_000000);
        params.put("STEPCODE", "");
        params.put("USERNAME", userName);
        params.put("FLAG", ExceptionConStants.retCode_true);
        LOGGER.info("传入多线程参数为:"+params.toString());
        
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, params, task,false);
        
        //关闭FTP
        if(FTPUtils.client!=null){
          FTPUtils.getCloseFtpClient();
        }
        return resultSuccess("success");
    }
    
    
    
	/**
     * 重新生成
     * @return
     */
    @RequestMapping(value = "/reCreate", method = RequestMethod.POST)
    @ResponseBody
    public Object reCreate(String psChannelCodes ,String tradeDate,String flag) {
    	LOGGER.info("重新生成文件步骤开始");
    	LOGGER.info("重新生成文件的渠道有"+psChannelCodes);
    	String userName = getLoginName();
    	//需要修改产品状态的渠道
    	List<String>  updateList = fundMarketService.selectAllUpdateChannels(tradeDate);
    	if(updateList!=null&&updateList.size()!=0){
        	LOGGER.info("需要修改产品状态的渠道有"+updateList.toString());
    		for(String channelCode:updateList){
            	Map<String,String> inputMap = Maps.newHashMap();
            	inputMap.put("TRADEDATE", tradeDate);
            	inputMap.put("CHANNELCODE", channelCode);
            	fundMarketService.changeProductInfoFlag(inputMap);
        	}
    	}
    	
    	List  channelCodeList=Arrays.asList(psChannelCodes.split(","));
    	if(channelCodeList==null||channelCodeList.size()==0){
    		//查询所有的在用渠道编号（需要生成行情文件的渠道）
        	  channelCodeList=fundMarketService.selectAllUsedChannels(tradeDate);
    	}else{
        	threadUtils =MultiThreadUtils.newInstance(channelCodeList.size());
    	}
    	//删除行情临时表数据
    	fundQuotTmpService.truncateFundQuotTmp();
    	LOGGER.info("删除行情临时表数据");
    	 ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) fundMarketProcessorService;
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("TRADEDATE", (String)tradeDate.replaceAll("-", ""));
        params.put("PROCESSSTEPNO", Const.FUND_MARKET_PROCESS_CODE_000001);
        params.put("FILETYPE", Const.FUND_TYPE_07);
        params.put("USERNAME",userName);
        params.put("FLAG", flag);
        LOGGER.info("传入多线程参数为:"+params.toString());

        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, params, task,false);
        return resultSuccess("success");
    }
    
/*	*//**
     * 重新发送
     * @return
     *//*
    @RequestMapping(value = "/reSend", method = RequestMethod.POST)
    @ResponseBody
    public Object reSend(String psChannelCodes ,String tradeDate) {
    	LOGGER.info("重新发送文件步骤开始");
    	LOGGER.info("重新发送文件的渠道有"+psChannelCodes);

    	List  channelCodeList=Arrays.asList(psChannelCodes.split(","));
    	if(channelCodeList==null||channelCodeList.size()==0){
    		return "";
    	}else{
        	threadUtils =MultiThreadUtils.newInstance(channelCodeList.size());
    	}
    	if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
            LOGGER.info("FTP已实例化");
    	}
    	ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) fundMarketProcessorService;
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("TRADEDATE", (String)tradeDate.replaceAll("-", ""));
        params.put("PROCESSSTEPNO", Const.FUND_MARKET_PROCESS_CODE_000002);
        LOGGER.info("传入多线程参数为:"+params.toString());
        
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, params, task,false);
        //关闭FTP
        if(FTPUtils.client!=null){
          FTPUtils.getCloseFtpClient();
          LOGGER.info("FTP连接已关闭");
        }
        return resultSuccess("success");
    }*/
    
    
    /**
     * 重新发送
     * @return
     */
    @RequestMapping(value = "/reSend", method = RequestMethod.POST)
    @ResponseBody
    public Object reSend(String psChannelCodes ,String tradeDate) {
    	LOGGER.info("重新发送文件步骤开始");
    	LOGGER.info("重新发送文件的渠道有"+psChannelCodes);
    	String userName = getLoginName();
    	final Map<String,Object> checkFileMap = new HashMap<String,Object>();
    	List  channelCodeList=Arrays.asList(psChannelCodes.split(","));
    	if(channelCodeList==null||channelCodeList.size()==0){
    		return "";
    	}
    	
    	if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
            LOGGER.info("FTP已实例化");
    	}
    	
    	for(int i = 0;i<channelCodeList.size();i++){
    		Map<String,Object> infoMap = Maps.newHashMap();
    		String channelCode = channelCodeList.get(i)+"";
    		PchannelInfo info =  channelInfoService.queryChannelInfoByChannelCode(channelCode);
    		infoMap.put("TRADEDATE", tradeDate);
    		infoMap.put("CHANNELCODE", channelCode.trim());
    		infoMap.put(Const.FUND_FILETYPE, Const.FILE_TYPE_07);//上传文件类型
    		infoMap.put("CHANNELINFO", info);
    		infoMap.put("PROCESSSTARTTIME", DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
    		infoMap.put("PROCESSSTEPINFO",Const.FUND_MARKET_PROCESS_CODE_000003);//处理步骤
    		infoMap.put("BRANCHCODE", Const.FUND_MARKET_PROCESS_CODE_000000);//处理流程编码   000000 行情
    		infoMap.put("TRADEDATE", tradeDate);
    		infoMap.put("USERNAME", userName);
    		LOGGER.info("发送文件的传入参数"+infoMap.toString());
    		
    		FTPUtils ftpUtils = new FTPUtils();
    		Map<String,Object> retMap = Maps.newHashMap();
    		
    		//本地文件上传FTP
    		retMap = ftpUtils.uploadConFile(infoMap);
    		Map<String,Object> qryStepSendMap = Maps.newHashMap();
    		qryStepSendMap.put("flowCode", Const.FUND_MARKET_PROCESS_CODE_000002);
    		qryStepSendMap.put("stepCode", Const.FUND_MARKET_PROCESS_CODE_000003);
    		ProcessStepInfo fundSendstepInfo= processStepInfoService.selectStepInfo(qryStepSendMap);
    		retMap.putAll(infoMap);
    		retMap.put("PROCESSSTEPINFO", fundSendstepInfo);
    		//上传文件插入处理日志
    		fundMarketProcessorService.insertProcessLog(retMap, channelCode);
    		LOGGER.info("上传文件插入处理日志");
    		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))	
    		continue;
    	    
    		//基金行情信息确认表发送状态修改
    		retMap = ftpUtils.updateSendStatus(infoMap);
    		Map<String,Object> qryStepUpdateInfoMap = Maps.newHashMap();
    		qryStepUpdateInfoMap.put("flowCode", Const.FUND_MARKET_PROCESS_CODE_000002);
    		qryStepUpdateInfoMap.put("stepCode", Const.FUND_MARKET_PROCESS_CODE_000004);
    		ProcessStepInfo updateFundmarketstepInfo= processStepInfoService.selectStepInfo(qryStepUpdateInfoMap);
    		retMap.putAll(infoMap);
    		retMap.put("PROCESSSTEPINFO",updateFundmarketstepInfo);
    		//基金行情信息确认表发送状态出入处理日志
    		fundMarketProcessorService.insertProcessLog(retMap, channelCode);
    		LOGGER.info("基金行情信息确认表发送状态出入处理日志");
    		
    	}
        //关闭FTP
        if(FTPUtils.client!=null){
          FTPUtils.getCloseFtpClient();
          LOGGER.info("FTP连接已关闭");
        }
    	checkFileMap.put("channelCodeList", channelCodeList);
    	checkFileMap.put("tradeDate", tradeDate);
    	checkFileMap.put("fileType", Const.FILE_TYPE_07);
        new Thread(){
        	public void run(){
        		try {
					Thread.sleep(60000);
					fundMarketProcessorService.checkUploadFtpFiles(checkFileMap);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        }.start();
        return resultSuccess("success");
    }
    
    
    /**
	 * 列表页步骤 
	 * @Title: queryProcessorStep   
	 * @author: 王超 2019年5月31日 下午4:52:51
	 * @param: @param model
	 * @param: @param branchCode
	 * @param: @param errorStep
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/marketStepPage", method = RequestMethod.GET)
	public String queryProcessorStep(Model model ,String processStep,String channelCode,String transDate,String processStart){
		List<ProcessStepInfo> stepInfo = processStepInfoService.selectByFlowCode("000000");
		for(int i=0;i<stepInfo.size();i++){
			if("04".equals(stepInfo.get(i).getPsiProcessStep())){
				stepInfo.remove(i);
			}
		}
		String list = JSON.toJSONString(stepInfo);
		
		Map<String,Object> processStepMap = Maps.newHashMap();
		
	    model.addAttribute("errorStep", processStep);
		model.addAttribute("entity", stepInfo);
		model.addAttribute("list", list);
		model.addAttribute("channelCode", channelCode);
		model.addAttribute("transDate", transDate);
		model.addAttribute("processStart", processStart);
		return "sys/business/marketStepPage";
	}
	
    /**
	 * 列表页步骤 
	 * @Title: queryProcessorStep   
	 * @author: 王超 2019年5月31日 下午4:52:51
	 * @param: @param model
	 * @param: @param branchCode
	 * @param: @param errorStep
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/channelDetail", method = RequestMethod.GET)
	public String channelDetail(Model model,String channelCode,String transDate){
		Map<String, Object> inputMap = Maps.newHashMap();
		model.addAttribute("channelCode", channelCode);
		model.addAttribute("transDate", transDate);
		return "sys/business/channelDetail";
	}
	
	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getMarketHandingDetail")
	@ResponseBody
	public PageInfo getMarketHandingDetail(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request,Model model) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		JSONArray array = new JSONArray();
		pageInfo.setPageResult(fundMarketCfmService.findMarketDetailCondition(pageInfo, condition));
		return pageInfo;
	}
	
    
    /**
	 * 行情确认表数据信息修改
	 * 
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	String data = request.getParameter("data");
    	List<Map<String, Object>> checkList = new ArrayList<Map<String,Object>>();
		data = StringEscapeUtils.unescapeHtml4(data);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> list = objectMapper.readValue(data, List.class);
		String psChannelCodes = "";
		String tradeDate = "";
		for(Map<String,Object> map:list){
			tradeDate = MapUtils.getString(map,"fmBusinessDate");
			psChannelCodes = MapUtils.getString(map,"fmChannelCode");
			String fmId= MapUtils.getString(map,"fmId");
			String fmFundStatus = MapUtils.getString(map,"fmFundStatus");
			FundMarketCfm fundMarketCfm = fundMarketCfmService.selectByPrimaryKey(com.sams.common.utils.StringUtils.toLong(fmId));
			fundMarketCfm.setFmFundStatus(fmFundStatus);
			fundMarketCfmService.updateByPrimaryKey(fundMarketCfm);
		}
		reCreate(psChannelCodes,tradeDate,ExceptionConStants.retCode_false);
    	return resultSuccess("success"); 
    }
    
    /**
	 * 查询当前日期的渠道行情状态  
	 * @Title: csdcPage   
	 * @author: wangchao 2019年5月22日 下午3:08:45
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/GUI",method = RequestMethod.GET)
	public String  dayEndGui(Model model,String tradeDate){
		if(StringUtils.isEmpty(tradeDate)){
			tradeDate = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
		}
		List<MarketHandingResult> list = new ArrayList<MarketHandingResult>();
		list = processingStateService.findMarketHandGuiList(tradeDate);
		model.addAttribute("list",list);
		model.addAttribute("data", JSON.toJSONString(list));
		model.addAttribute("date", tradeDate);
		return "sys/business/marketHandGui";
	}

	/**
	 * @Description 跳转行情信息查询展示页面
	 * @Author weijunjie
	 * @Date 2020/4/29 10:33
	 **/
	@RequestMapping("/toFundMarketInfoPage")
	public String toFundMarketInfoPage(){
		return "sys/business/detailInfo/fundMarketInfo";
	}

	/**
	 * @Description 查询获取行情信息数据
	 * @Author weijunjie
	 * @Date 2020/4/29 10:34
	 **/
	@RequestMapping("/getFundMarketInfo")
	@ResponseBody
	public Object getFundMarketInfo(Integer page, Integer rows,
									String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(fundMarketCfmService.selectFundMarketInfo(pageInfo,condition));
		return pageInfo;
	}
}