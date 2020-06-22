package com.sams.business.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.ITask;
import com.sams.batchfile.common.MultiThreadUtils;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.AccountReqCfmService;
import com.sams.batchfile.service.AccountReqTmpService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.ProcessStepInfoService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.MyMapUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.csdcInterfaceConfig.service.CsdcInterfaceService;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProcessStepInfo;

/**
 * @ClassName:  DayEndProcessorController   
 * @Description:日终处理   
 * @author: yindy
 * @date:   2019年5月22日 下午1:59:47   
 *
 */
@Controller
@RequestMapping(value = "/dayEnd")
@SuppressWarnings("unchecked")
public class DayEndProcessorController extends BaseController{

	@Autowired
	private AccountReqCfmService accountReqCfmService;
	
	@Autowired
	private AccountReqTmpService accountReqTmpService;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private ProcessStepInfoService processStepInfoService;
	
	@Autowired
	private CsdcInterfaceService  csdcInterfaceService;
	
	@Autowired
	private PchannelInfoMapper pchannelInfoMapper;
	
	@Autowired
	private FundMarketProcessorService fundMarketProcessorService;
	
	public MultiThreadUtils<Integer> threadUtils = null;
	
	private static Logger LOGGER = LoggerFactory.getLogger(DayEndProcessorController.class);
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	
	/**
	 * 日终页面   
	 * @Title: csdcPage   
	 * @author: yindy 2019年5月22日 下午3:08:45
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String  dayEndList(){
		return "sys/business/dayend/dayEndProcessor";
	}
	
	/**
	 * 查询当前日期的渠道日终状态  
	 * @Title: csdcPage   
	 * @author: yindy 2019年5月22日 下午3:08:45
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/GUI",method = RequestMethod.GET)
	public String  dayEndGui(Model model,String date){
		if(StringUtils.isEmpty(date)){
			date = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
		}
		String userName = getLoginName();
		List<DayEndProcessor> list = new ArrayList<DayEndProcessor>();
		list = csdcInterfaceService.selectDayEndChannelStatus(date);
		model.addAttribute("list",list);
		String dateString = JSON.toJSONString(list).replaceAll("\\\\","&amp;");
		model.addAttribute("data", dateString);
		model.addAttribute("date", date);
		model.addAttribute("userName", userName);
		return "sys/business/dayend/dayEndProcGui";
	}
	
	/**
	 * 确认文件处理 
	 * @Title: ConfirmProcessor   
	 * @author: yindy 2019年5月22日 下午3:09:05
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/confirmProcessor", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Object confirmProcessor(String channelCodes,String date ,String fileTypes){
		String userName = getLoginName();
		LOGGER.info("当前操作人为:"+userName+"操作日期:"+date);
		Map<String, Object> intoMap = new HashMap<String, Object>();
		List  channelCodeList = new ArrayList<>();
		if(StringUtils.isEmpty(channelCodes)){
			channelCodeList = pchannelInfoMapper.selectAllChannels();
		}else{
			channelCodeList = Arrays.asList(channelCodes.split(","));
		}
		threadUtils = MultiThreadUtils.newInstance(channelCodeList.size());
		if(StringUtils.isEmpty(date)){
			date = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
		}
		LOGGER.info("需要操作的渠道为:"+channelCodeList);
		intoMap.put("TRANSDATE", date);
		intoMap.put("USERNAME", userName);
		intoMap.put("FILETYPES", fileTypes);
		ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) accountReqCfmService;
		ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, intoMap, task,true);
		return resultBean;
	}
	
	/**
	 * 申请数据处理   
	 * @Title: dataProcessor   
	 * @author: yindy 2019年5月24日 下午3:51:29
	 * @param: @param date
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/dataProcessor", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Object dataProcessor(String channelCodes,String date,String fileType){
		String userName = getLoginName();
		LOGGER.info("当前操作人为:"+userName+"操作日期:"+date);
		if(FTPUtils.client==null){
    		FTPUtils.getInstance(); 
    	}
		Map<String, Object> intoMap = new HashMap<String, Object>();
		List  channelCodeList = new ArrayList<>();
		if(StringUtils.isEmpty(channelCodes)){
			channelCodeList = pchannelInfoMapper.selectAllChannels();
		}else{
			channelCodeList = Arrays.asList(channelCodes.split(","));
		}
		threadUtils = MultiThreadUtils.newInstance(channelCodeList.size());
		if(StringUtils.isEmpty(date)){
			date = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
		}
		LOGGER.info("需要操作的渠道为:"+channelCodeList);
		intoMap.put("TRANSDATE", date);
		intoMap.put("USERNAME", userName);
		intoMap.put("SPECIALFILE", fileType);
		ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) accountReqTmpService;
		ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, intoMap, task,true);
		if(FTPUtils.client!=null){
			FTPUtils.getCloseFtpClient();
		}
		
		//处理完数据之后校验94文件 20200409
		List simpleChannelList = pchannelInfoMapper.querySend94FileChannel(channelCodeList,date);
		LOGGER.info("正常处理数据的渠道为{},其中需要发送94文件的渠道为{}",channelCodeList.toString(),simpleChannelList == null ? "" : simpleChannelList.toString());
		if(simpleChannelList != null && simpleChannelList.size() > 0 ){
			final Map<String, Object> checkMap = new HashMap<String, Object>();
			checkMap.put("channelCodeList", simpleChannelList);
			checkMap.put("tradeDate", date);
			checkMap.put("fileType", Const.FILE_TYPE_94);
			executorService.submit(new Callable() {
				@Override
				public Object call() throws Exception {
					//1分钟以后开始校验94文件
					Thread.sleep(60000);
					LOGGER.info("申请文件处理完成,另起线程校验94文件与上传文件是否一致！校验的渠道为:"+(checkMap == null ? "":checkMap.toString()));
					fundMarketProcessorService.checkUploadFtpFiles(checkMap);
					return null;
				}
	        });
		}
		return resultBean;
	}
	
	
	/**
	 * 查询日终处理步骤  
	 * @Title: processorStepList   
	 * @author: yindy 2019年5月22日 下午4:44:37
	 * @param: @param page
	 * @param: @param rows
	 * @param: @param sort
	 * @param: @param order
	 * @param: @param request
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/processorStepList")
	@ResponseBody
	public PageInfo  processorStepList(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(exchangeProcessorService.processorStepList(pageInfo,condition));
		return pageInfo;
	}
	
	/**
	 * 列表页步骤 
	 * @Title: queryProcessorStep   
	 * @author: yindy 2019年5月31日 下午4:52:51
	 * @param: @param model
	 * @param: @param branchCode
	 * @param: @param errorStep
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/processStepPage", method = RequestMethod.GET)
	public String queryProcessorStep(Model model ,String branchCode,String errorStep,String channelCode,String transDate){
		List<ProcessStepInfo> stepInfo = processStepInfoService.selectByFlowCode(branchCode);
		String list = JSON.toJSONString(stepInfo);
		model.addAttribute("errorStep", errorStep);
		model.addAttribute("entity", stepInfo);
		model.addAttribute("list", list);
		model.addAttribute("branchCode", branchCode);
		model.addAttribute("channelCode", channelCode);
		model.addAttribute("transDate", transDate);
		return "sys/business/dayend/processStepPage";
	}
	
	/**
	 * 图形界面日终流程   
	 * @Title: getdayEndStepPage   
	 * @author: yindy 2019年5月31日 下午5:04:17
	 * @param: @param model
	 * @param: @param processStep
	 * @param: @param confirmProcessStep
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/dayEndStepPage", method = RequestMethod.GET)
	public String getdayEndStepPage(Model model ,String processStep,String confirmProcessStep,
                                    String sendProcessStep,String date,String channelCode){
		List<ProcessStepInfo> stepInfo = processStepInfoService.selectByFlowCode(Const.ACCOUNT_DATA_STEP);
		List<ProcessStepInfo> stepInfo1 = processStepInfoService.selectByFlowCode(Const.ACCOUNT_CONFIRM_STEP);
		List<ProcessStepInfo> stepInfo2 = processStepInfoService.selectByFlowCode(Const.SEND_FILE_STEP);
		stepInfo.addAll(stepInfo1);
		stepInfo.addAll(stepInfo2);
		String list = JSON.toJSONString(stepInfo);
		model.addAttribute("processStep", processStep);
		model.addAttribute("entity", stepInfo);
		model.addAttribute("list", list);
		model.addAttribute("confirmProcessStep", confirmProcessStep);
		model.addAttribute("sendProcessStep", sendProcessStep);
        model.addAttribute("date", date);
        model.addAttribute("channelCode", channelCode);
		return "sys/business/dayend/dayEndStepPage";
	}
	/**
	 * 强制通过功能   重新执行功能 
	 * @Title: forceErrorStep   
	 * @author: yindy 2019年6月10日 下午5:00:35
	 * @param: @param transDate
	 * @param: @param errorStep
	 * @param: @param channelCode
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/forceErrorStep",method = RequestMethod.POST)
	@ResponseBody
	public Object forceErrorStep (String branchCode ,String transDate,String errorStep ,String channelCode,Integer operType){
		String userName = getLoginName();
		LOGGER.info("步骤编号为:"+branchCode+",错误步骤为:"+errorStep+",操作人为:"+userName);
		Map<String, Object> intoMap = new HashMap<String, Object>();
		if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
    	}
		intoMap.put("TRANSDATE", transDate);
		intoMap.put("USERNAME", userName);
		intoMap.put("ERRORSTEP", errorStep);
		intoMap.put("OPERTYPE", operType);
		List channelCodeList = new ArrayList<>();
		channelCodeList.add(channelCode);
		threadUtils = MultiThreadUtils.newInstance(channelCodeList.size());
		ITask<ResultBean<String>, Integer> task  = null;
		if(Const.ACCOUNT_CONFIRM_STEP.equals(branchCode)){
			task = (ITask<ResultBean<String>, Integer>) accountReqCfmService;
		}else{
			task = (ITask<ResultBean<String>, Integer>) accountReqTmpService;
		}
		
		ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, intoMap, task,true);
		
		if(FTPUtils.client!=null){
			FTPUtils.getCloseFtpClient();
		}
		
		
		//处理完数据之后校验94文件 20200409
	    List forceChannelList = pchannelInfoMapper.querySend94FileChannel(channelCodeList,transDate);
		LOGGER.info("处理数据的渠道为{},其中需要发送94文件的渠道为{}",channelCodeList.toString(),forceChannelList == null ? "" : forceChannelList.toString());
		if(forceChannelList != null && forceChannelList.size() > 0 ){
			final Map<String, Object> checkMap = new HashMap<String, Object>();
			checkMap.put("channelCodeList", forceChannelList);
			checkMap.put("tradeDate", transDate);
			checkMap.put("fileType", Const.FILE_TYPE_94);
			executorService.submit(new Callable() {
				@Override
				public Object call() throws Exception {
					//1分钟以后开始校验94文件
					Thread.sleep(60000);
					LOGGER.info("强制通过/重做该步处理完成,另起线程校验94文件与上传文件是否一致！校验的渠道为:"+(checkMap == null ? "":checkMap.toString()));
					fundMarketProcessorService.checkUploadFtpFiles(checkMap);
					return null;
				}
	        });
		}
		return resultSuccess(resultBean);
	}

	/**
	 * @Description 选择回到该步操作
	 * @Author weijunjie
	 * @Date 2020/4/21 10:11
	 **/
	@RequestMapping(value = "/returnStep",method = RequestMethod.POST)
	@ResponseBody
	public Object returnStep (String branchCode ,String transDate,String retStep ,String channelCode){
		String userName = getLoginName();
		LOGGER.info("步骤编号为:"+branchCode+",回到步骤为:"+retStep+",操作人为:"+userName);
        String s = processStepInfoService.returnProcessStep(branchCode, transDate, retStep, channelCode,userName);
        if("success".equals(s)){
            return resultSuccess();
        }else{
            return resultSuccess(s);
        }

	}

	/**
	 * 错误数据页面   
	 * @Title: queryFailedPage   
	 * @author: yindy 2019年6月21日 下午6:00:25
	 * @param: @param model
	 * @param: @param type
	 * @param: @param channelCode
	 * @param: @param transDate
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value= "/queryAccoFailedPage" , method = RequestMethod.GET)
	public String  queryAccoFailedPage(Model model){
		model.addAttribute("type","0");
		return "sys/business/detailInfo/accoDetailInfo";
	}
	
	/**
	 * 错误数据页面   
	 * @Title: queryFailedPage   
	 * @author: yindy 2019年6月21日 下午6:00:25
	 * @param: @param model
	 * @param: @param type
	 * @param: @param channelCode
	 * @param: @param transDate
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value= "/queryTransFailedPage" , method = RequestMethod.GET)
	public String  queryTransFailedPage(Model model){
		model.addAttribute("type","1");
		return "sys/business/detailInfo/transDetailInfo";
	}
	
	/**
	 * 查询错误数据  
	 * @Title: queryFailedInfo   
	 * @author: yindy 2019年6月21日 下午6:00:58
	 * @param: @param type 区分账户还是交易 0 账户 1交易
	 * @param: @param channelCode 渠道编码
	 * @param: @param transDate 交易日期
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/queryFailedInfo")
	@ResponseBody
	public PageInfo  queryFailedInfo(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		/*created by wangchao 20200610 渠道产品级联关系设置*/
		if(condition.get("productCode")!=null){
			condition.put("productCode", MyMapUtils.getStringArrayBySplit(condition,"productCode","-")[0]);
		}
		pageInfo.setPageResult(accountReqTmpService.queryFailedInfo(pageInfo,condition));
		return pageInfo;
	}
	/**
	 * 进入收益查看页面   
	 * @Title: queryParticipationProfit   
	 * @author: yindy 2019年7月18日 上午10:49:50
	 * @param: @param model
	 * @param: @param channelCodes 勾选的渠道
	 * @param: @param date 发送文件的日期
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/queryParticipationProfit",method = RequestMethod.GET)
	public String queryParticipationProfit(Model model,String channelCodes,String date){
		model.addAttribute("channelCodes", channelCodes);
		model.addAttribute("date", date);
		return "sys/business/detailInfo/participationProfit";
	}
	
	/**
	 * 查询分红数据列表  
	 * @Title: queryProfitList   
	 * @author: yindy 2019年7月18日 上午10:52:12
	 * @param: @param page
	 * @param: @param rows
	 * @param: @param sort
	 * @param: @param order
	 * @param: @param request
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/queryProfitList")
	@ResponseBody
	public PageInfo queryProfitList (String channelCodes,String date){
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageResult(exchangeProcessorService.queryProfitList(pageInfo,channelCodes,date));
		return pageInfo;
	}
	
	/**
	 * 查询分红数据详情   
	 * @Title: queryProfitDetail   
	 * @author: yindy 2019年7月19日 下午3:33:13
	 * @param: @param model
	 * @param: @param channelCode
	 * @param: @param date
	 * @param: @param fundCode
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/queryProfitPage",method = RequestMethod.GET)
	public String queryProfitDetail(Model model,String channelCode,String date,String fundCode){
		model.addAttribute("channelCode", channelCode);
		model.addAttribute("date", date);
		model.addAttribute("fundCode", fundCode);
		return "sys/business/detailInfo/profitDetail";
	}
	
	/**
	 * 查询详细分红数据
	 * @Title: queryProfitDetail   
	 * @author: yindy 2019年7月22日 上午11:04:45
	 * @param: @param channelCode
	 * @param: @param date
	 * @param: @param fundCode
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/queryProfitDetail")
	@ResponseBody
	public PageInfo queryProfitDetail (String channelCode,String date,String fundCode){
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageResult(exchangeProcessorService.queryProfitDetail(channelCode,date,fundCode));
		return pageInfo;
	}
	
	/**
	 * 发送文件   
	 * @Title: sendFile   
	 * @author: yindy 2019年7月22日 上午11:03:40
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/sendFile")
	@ResponseBody
	public  Object sendFile(String channelCodes,String transDate,String fileTypes){
		List<String>  channelCodeList = new ArrayList<>();
		if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
    	}
		if(StringUtils.isEmpty(channelCodes)){
			channelCodeList = pchannelInfoMapper.selectAllChannels();
		}else{
			channelCodeList = Arrays.asList(channelCodes.split(","));
		}
		Map<String, Object> intoMap = new HashMap<String, Object>();
		String userName = getLoginName();
		intoMap.put("TRANSDATE", transDate);
		intoMap.put("USERNAME", userName);
		intoMap.put("FILETYPES", fileTypes);
		//循环上传
		ResultBean<List<ResultBean<String>>> resultBean = new ResultBean<List<ResultBean<String>>>();
		List<ResultBean<String>> list = new ArrayList<ResultBean<String>>();
		for (String channelCode : channelCodeList) {
			ResultBean<String> bean = exchangeProcessorService.checkSendFile(channelCode, intoMap);
			list.add(bean);
		}
		resultBean.setData(list);
		if(FTPUtils.client!=null){
			FTPUtils.getCloseFtpClient();
		}
		final Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap.put("channelCodeList", channelCodeList);
		checkMap.put("tradeDate", transDate);
		//上传完之后校验
		new Thread(){
        	public void run(){
        		try {
					Thread.sleep(60000);
					LOGGER.info("上传文件完成，另起线程校验生成文件与上传文件是否一致！校验的渠道和时间为:"+(checkMap == null ? "":checkMap.toString()));
					fundMarketProcessorService.checkUploadFtpFiles(checkMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
        		
        	}
        }.start();
		return resultBean;
	}
	
	
	/**
	 * T日数据回退
	 * @Title: dataRoolback   
	 * @author: wangchao 2019年11月11日 下午18:13:40
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/dataRoolback")
	@ResponseBody
	public Object dataRoolback(String channelCode,String transDate){
		processStepInfoService.dataRoolback(channelCode,transDate);
		return resultSuccess("success");
	}
	
	/**
	 * 错误信息弹框  
	 * @Title: transAccoFailedPage   
	 * @author: yindy 2019年8月14日 下午1:54:58
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/transAccoFailedPage")
	public String transAccoFailedPage(Model model,String channelCode ,String transDate,String type){
		model.addAttribute("channelCode", channelCode);
		model.addAttribute("transDate", transDate);
		model.addAttribute("type", type);
		return "sys/business/detailInfo/failDiag";
	}
	
	/**
	 * 错误信息查询   
	 * @Title: transAccoFailedList   
	 * @author: yindy 2019年8月14日 下午3:05:17
	 * @param: @param channelCode
	 * @param: @param transDate
	 * @param: @param type
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/transAccoFailed")
	@ResponseBody
	public PageInfo transAccoFailedList(Integer page, Integer rows,
										String sort, String order, String channelCode ,
										String transDate,String type){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelCode", channelCode);
		map.put("transDate", transDate);
		map.put("handel", "9999");
		map.put("type", type);
		pageInfo.setPageResult(accountReqTmpService.queryFailedInfo(pageInfo,map));
		return pageInfo;
	}
	/**
	 * 交易汇总页面   
	 * @Title: transStatisticsPage   
	 * @author: yindy 2019年8月19日 下午2:36:28
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/transStatisticsPage")
	public String transStatisticsPage(){
		return "sys/business/detailInfo/transStatistics";
	}
	/**
	 * 交易汇总数据   
	 * @Title: transStatistics   
	 * @author: yindy 2019年8月19日 下午2:36:56
	 * @param: @param request
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/transStatistics")
	@ResponseBody
	public PageInfo transStatistics(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> map = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(exchangeProcessorService.selectTransStatistics(pageInfo,map));
		return pageInfo;
	}
	
	/**
	 * 查询多币种交易详情   
	 * @Title: getMultipleDetail   
	 * @author: yindy 2020年2月5日 下午6:07:47
	 * @param: @param model
	 * @param: @param channelCode
	 * @param: @param fundCode
	 * @param: @param businessCode
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/getMultipleDetail")
	public String getMultipleDetail(Model model,String channelCode ,String fundCode,String businessCode,String startDate,String endDate){
		List<Map<String, Object>> list = exchangeProcessorService.getMultipleDetail(channelCode,fundCode,businessCode,startDate,endDate);
		model.addAttribute("list", list);
		return "sys/business/detailInfo/multipleTransDetails";
	}
	
	
	/**
	 * 分配完T+N后，批量调起程序   
	 * @Title: batchSendToTA   
	 * @author: yindy 2019年9月2日 下午5:23:27
	 * @param: @param date
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/batchSendToTA",method = RequestMethod.POST)
	@ResponseBody
	public Object batchSendToTA(@Param("date")String date){
		String userName = getLoginName();
		LOGGER.info("操作时间为:"+date+",操作人为:"+userName);
		if(StringUtils.isEmpty(date)){
			date = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
		}else{
			date = date.replaceAll("-", "");
		}
		//1.查询出需要批量发送TA的渠道
		List channelCodeList  = pchannelInfoMapper.selectBatchSendChannel(date,Const.TN_BATCH_STOP_STEP,Const.TN_ERROR_CODE);
		if(CollectionUtils.isEmpty(channelCodeList)){
			return resultSuccess("没有可以直接发送TA的渠道数据!");
		}
		//2.全部从T+N分配后一步开始执行
		if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
    	}
		Map<String, Object> intoMap = new HashMap<String, Object>();
		intoMap.put("TRANSDATE", date);
		intoMap.put("USERNAME", userName);
		intoMap.put("ERRORSTEP", Const.TN_BATCH_STOP_STEP);
		intoMap.put("OPERTYPE", 1);
		threadUtils = MultiThreadUtils.newInstance(channelCodeList.size());
		ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) accountReqTmpService;
		ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, intoMap, task,true);
		if(FTPUtils.client!=null){
			FTPUtils.getCloseFtpClient();
		}
		return resultSuccess(resultBean);
	}
	
	/**
	 * 生成并发送94文件   
	 * @Title: writeAndSend94File   
	 * @author: yindy 2019年10月25日 下午4:11:14
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/writeAndSend94File",method = RequestMethod.POST)
	@ResponseBody
	public Object writeAndSend94File (String date){
		boolean flag = false;
		StringBuilder msg = new StringBuilder();
		Map<String,Object> retMap=Maps.newHashMap();
		List<PchannelInfo> list = pchannelInfoMapper.querySend94File();
		for (PchannelInfo channelInfo : list) {
			String channelCode = channelInfo.getCiChannelCode();
			Map<String,Object> intoMap=Maps.newHashMap();
			intoMap.put("FILETYPES", Const.FILE_TYPE_94);
			intoMap.put("CHANNELCODE",channelCode );
			intoMap.put("TRANSDATE", date);
			intoMap.put("CHANNELINFO", channelInfo);
			accountReqCfmService=(AccountReqCfmService) SpringUtils.getBean(AccountReqCfmService.class);
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			retMap=accountReqCfmService.selectAndWriteFile(intoMap);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				msg.append(channelCode).append("写文件错误").append(Const.FILE_ENTER);
				flag = true;
			}
			retMap = exchangeProcessorService.uploadAndUpStat(retMap);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				msg.append(channelCode).append("发送文件错误").append(Const.FILE_ENTER);
				flag = true;
			}
		}
		if(!flag){
			msg.append("94文件发送完成！");
		}
		return resultSuccess(msg);
	}
	
	/**
	 * 查询勾选的渠道日期是否有处理过数据处理  
	 * @Title: channelHasHandel   
	 * @author: yindy 2020年4月28日 上午8:46:55
	 * @param: @param channelCodes
	 * @param: @param date
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/channelHasHandel",method = RequestMethod.POST)
	@ResponseBody
	public Object channelHasHandel(String channelCodes,String date,String flowCode){
		List<String>  channelCodeList = new ArrayList<>();
		if(StringUtils.isEmpty(channelCodes)){
			channelCodeList = pchannelInfoMapper.selectAllChannels();
		}else{
			channelCodeList = Arrays.asList(channelCodes.split(","));
		}
		//查询渠道当前日期有处理过的渠道
		List<String> handelChannel = processStepInfoService.queryHasHandelChannel(channelCodeList,date,flowCode);
		if(handelChannel != null && handelChannel.size() > 0 ){
			return resultSuccess(StringUtils.strip(handelChannel.toString(),"[]"),true);
		}else{
			return resultSuccess("",false);
		}
	}
	
}
