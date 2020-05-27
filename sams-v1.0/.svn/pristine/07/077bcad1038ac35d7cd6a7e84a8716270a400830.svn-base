package com.sams.business.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.ITask;
import com.sams.batchfile.common.MultiThreadUtils;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.FundQuotTmpService;
import com.sams.common.constant.Const;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.utils.DateUtils;
import com.sams.wsdl.impl.CallS100434;
import com.sams.wsdl.impl.CallS100442;
import com.sams.wsdl.impl.CallS100454;



@Controller
@RequestMapping("/fundMarket")
public class FundMarketController {
	
	// 创建多线程处理任务
	public MultiThreadUtils<Integer> threadUtils = MultiThreadUtils.newInstance(20);

	@Autowired
	private FundMarketProcessorService fundMarketProcessorService;
	
	@Autowired
	private FundMarketService fundMarketService;
	
	@Autowired
	private	FundQuotTmpService fundQuotTmpService;
	/**
	 * 行情信息处理页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/fundMarket";
    }
	
	/**
     * 获取交易日期
     * @return
     */
    @RequestMapping(value = "/tradeDate", method = RequestMethod.POST)
    @ResponseBody
    public Object tradeDate(String tradeDate) {
    	List  channelCodeList=fundMarketService.selectAllUsedChannels(tradeDate);
    	
    	if(channelCodeList==null||channelCodeList.size()==0){
    		return "";
    	}
    	if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
    	}
    	
    	fundQuotTmpService.truncateFundQuotTmp();
    	 ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) fundMarketProcessorService;
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("tradeDate", (String)tradeDate.replaceAll("-", ""));
        params.put("processStepNo", Const.FUND_MARKET_PROCESS_CODE_000000);
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, params, task,false);
        FTPUtils.getCloseFtpClient();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", "");
        map.put("total","0");
    	return map;
    	/*SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
    	SimpleDateFormat sf1 = new SimpleDateFormat("MMddHHmmss");
    	String ReserverNo = sf.format(DateUtils.getOracleSysDate())+"0000";
    	CallS100454 call454 = new CallS100454();
    	Map<String,Object> tradeMap = Maps.newHashMap();
    	tradeMap.put("TRANSACTIONDATE", (String)tradeDate.replaceAll("-", ""));
    	tradeMap.put("TRANSACTIONTIME", "050222");
    	tradeMap.put("INDIVIDUALORINSTITUTION", "1");
    	tradeMap.put("CHANNELCODE", "TTTNETB3");
    	tradeMap.put("FUNDCODE", "600503");
    	tradeMap.put("ReserverNo", ReserverNo);
    	Map<String,Object> retMap = call454.callS100454(tradeMap);
    	tradeMap.put("ReserverNo", retMap.get("ReserverNo"));
    	tradeMap.put("Crmserialno", sf1.format(DateUtils.getOracleSysDate()));
    	CallS100442 call442 = new CallS100442();
    	Map<String,Object> retMap1 = call442.callS100442(tradeMap);
    	CallS100434 call34 = new CallS100434();
    	Map<String,Object> retMap2 = call34.callS100434(tradeMap);
    	return retMap2;*/
    }
    
    
    
	/**
     * 获取交易日期
     * @return
     */
    @RequestMapping(value = "/reCreate", method = RequestMethod.POST)
    @ResponseBody
    public Object reCreate(String tradeDate,String channels) {
    	String[] channelCodes=channels.split(",");
    	List  channelCodeList=Arrays.asList(channelCodes);
    	
    	if(channelCodeList==null||channelCodeList.size()==0){
    		return "";
    	}
    	fundQuotTmpService.truncateFundQuotTmp();
    	 ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) fundMarketProcessorService;
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("TRADEDATE", (String)tradeDate.replaceAll("-", ""));
        params.put("PROCESSSTEPNO", Const.FUND_MARKET_PROCESS_CODE_000001);
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, params, task,false);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", "");
        map.put("total","0");
    	return map;
    }
    
	/**
     * 获取交易日期
     * @return
     */
    @RequestMapping(value = "/reSend", method = RequestMethod.POST)
    @ResponseBody
    public Object reSend(String tradeDate,String channels) {
    	String[] channelCodes=channels.split(",");
    	List  channelCodeList=Arrays.asList(channelCodes);
    	if(channelCodeList==null||channelCodeList.size()==0){
    		return "";
    	}
    	if(FTPUtils.client==null){
    		FTPUtils.getInstance();  
    	}
    	ITask<ResultBean<String>, Integer> task = (ITask<ResultBean<String>, Integer>) fundMarketProcessorService;
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("TRADEDATE", (String)tradeDate.replaceAll("-", ""));
        params.put("PROCESSSTEPNO", Const.FUND_MARKET_PROCESS_CODE_000002);
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(channelCodeList, params, task,false);
        FTPUtils.getCloseFtpClient();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", "");
        map.put("total","0");
    	return map;
    }
}
