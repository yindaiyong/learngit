package com.sams.batchfile.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sams.common.utils.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.ITask;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.CloseDateService;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.ProcessStepInfoService;
import com.sams.batchfile.service.ProcessingStateService;
import com.sams.batchfile.service.RuningErrorService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.local.LocalUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.custom.mapper.FundQuotTmpMapper;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.model.ChannelInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProcessStepInfo;
import com.sams.custom.model.ProcessingState;
import com.sams.custom.model.RuningError;
import com.sams.wsdl.impl.CallS100411;

@Service
public class FundMarketProcessorServiceImpl implements ITask<ResultBean<String>, Integer>,FundMarketProcessorService	 {	
	private static Logger LOGGER = LoggerFactory.getLogger(FundMarketProcessorServiceImpl.class);


	@Autowired
	private ProcessStepInfoService processStepInfoService;
	@Autowired
	private ProcessingStateService processingStateService;
	@Autowired
	private RuningErrorService RuningService;
	@Autowired
	private ChannelInfoService channelInfoService;
	
	
	/**
	 * 日启行情处理
	 * @param tradeDate 交易日期
	 * @param channelCode 渠道编号
	 * @param processStepNo 处理编号
	 */
	public void  fundMarkerProcess(String tradeDate,String channelCode,String processStepNo,String userName,String flag){
		try{
			//获取行情处理流程记录集
			Map<String,Object> retMap = Maps.newHashMap();
			retMap = processStepInfoService.selectByProcessStepInfo(processStepNo,null,null,null);
			List<ProcessStepInfo> list =(List)retMap.get("LISTREQ");
			LOGGER.info("获取行情处理流程记录集"+list.toString());
			
			//校验行情处理流程记录集 是否为空
			retMap=processStepInfoService.checkProcessStepInfoCount(list);
			if(ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				String retCode=MapUtils.getString(retMap,"retCode");
				
				Map<String,Object> inputMap=Maps.newHashMap();
				inputMap.put("TRADEDATE", tradeDate);
				inputMap.put("CHANNELCODE", channelCode.trim());
				inputMap.put(Const.FUND_FILETYPE, Const.FILE_TYPE_07);//上传文件类型
				inputMap.put("FLAG",flag);//用于判断是否删除行情确认表该渠道当天的行情
				
				channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
				PchannelInfo info =  channelInfoService.queryChannelInfoByChannelCode(channelCode);
				LOGGER.info("获取渠道信息为"+info.toString());
				inputMap.put("CHANNELINFO", info);
				
				Object[] inputObj=new Object[inputMap.size()-4];
				inputObj[0]=inputMap;
				
				if(ExceptionConStants.retCode_0000.equals(retCode)){
					for(ProcessStepInfo procStp:list){
						
						Map<String, Object> insertProcessMap=Maps.newHashMap();
						insertProcessMap.put("PROCESSSTARTTIME", DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
						insertProcessMap.put("PROCESSSTEPINFO", procStp);//处理步骤
						insertProcessMap.put("BRANCHCODE", Const.FUND_MARKET_PROCESS_CODE_000000);//处理流程编码   000000 行情
						
						String packageName=procStp.getPsiPackageName();
						String className=procStp.getPsiClassName();
						String methodName=procStp.getPsiMethodName();
						String ProcessName=procStp.getPsiProcessName();//处理方法描述
						
						LOGGER.info(ProcessName+"开始");
						LOGGER.info(ProcessName+"调用类:"+className+",方法为:"+methodName);
						/*LOGGER.info("输入参数为:"+inputObj[0].toString());*/
						
						Class<?> clazz=Class.forName(packageName+"."+className);
						
						Object obj=Reflections.invokeMethodByName(clazz, methodName, inputObj);
						/*LOGGER.info("输出参数为:"+obj.toString());*/
						LOGGER.info(ProcessName+"结束");
						
						inputObj[0]=obj;
						insertProcessMap.putAll((Map<String, Object>) obj);
						insertProcessMap.put("USERNAME", userName);
						insertProcessMap.put("TRADEDATE", tradeDate);
						
						String nextFlagCode= (String)insertProcessMap.get("retCode");
						insertProcessLog(insertProcessMap,channelCode);
						if(!ExceptionConStants.retCode_0000.equals(nextFlagCode)){
							break;
						}
					}
				}
			}else{
				LOGGER.info("校验行情处理流程记录集为空");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 业务处理信息入库
	 * @param inputMap 参数集合 retCode 、 tradeDate、branchCode、processStepInfo、processStartTime
	 * @param ChannelCode 渠道编号
	 * @return Map<String, Object>
	 */
	public Map<String, Object> insertProcessLog(Map<String, Object> inputMap,String ChannelCode){
		//状态信息入库
		RandomizingID randomizingID = new RandomizingID("yyyyMMddHHmmss",7,false);
		ProcessingState processingState = new ProcessingState();
		Map<String,Object> outMap = (HashMap)inputMap;
		
		String retCode = MapUtils.getString(outMap, "retCode","");
		String tradeDate = MapUtils.getString(outMap, "TRADEDATE","");
		String userName = MapUtils.getString(outMap, "USERNAME","");
	    
		String retMsg = MapUtils.getString(outMap, "retMsg","");
		String branchCode = MapUtils.getString(outMap, "BRANCHCODE","");
	    ProcessStepInfo processStepInfo=(ProcessStepInfo) outMap.get("PROCESSSTEPINFO");
	    processingState.setPsId(new BigDecimal(randomizingID.genNewId()));
	    processingState.setPsProcessStartTime(MyMapUtils.getString(inputMap,"PROCESSSTARTTIME"));
	    processingState.setPsProcessCode(processStepInfo.getPsiProcessCode());
	    processingState.setPsChannelCode(ChannelCode);
	    processingState.setPsBranchCode(branchCode);
	    processingState.setPsProcessStep(processStepInfo.getPsiProcessStep());
	    if(ExceptionConStants.retCode_0000.equals(retCode)){
	    	processingState.setPsProcessStart(Const.BUSINESS_STUTAS_01);
	    }else{
	    	processingState.setPsProcessStart(Const.BUSINESS_STUTAS_00);
	    	processingState.setPsErrorCode(processingState.getPsId()+"");
	    	RuningError runningError = new RuningError();
	    	runningError.setReId(new BigDecimal(randomizingID.genNewId()));
	    	runningError.setReErrorCode(processingState.getPsId()+"");
	    	if(ExceptionConStants.retCode_T00051.equals(retCode)){
	    		runningError.setReErrorMessage(retCode+","+retMsg);
	    	}else{
	    		runningError.setReErrorMessage(retMsg);
	    	}
	    	
	    	runningError.setReBranchCode(branchCode);
	    	runningError.setReErrorTime(DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
	    	runningError.setReProcessStep(processStepInfo.getPsiProcessStep());
	    	LOGGER.info(branchCode+"业务处理时，"+"出现异常"+processStepInfo.getPsiProcessName()+"报错，记录处理异常信息");
	    	RuningService.insertRuningError(runningError);
	    }
	    processingState.setPsProcessDec(processStepInfo.getPsiProcessName());
	    processingState.setPsProcessEndTime(DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
	    processingState.setPsBusinessDate(tradeDate);
	    processingState.setPsCuser(userName);
		processingStateService.insertProcessingState(processingState);
		return inputMap;
	}

	@Override
	public ResultBean<String> execute(String channnelCode, Map<String, Object> params) {
		String tradeDate=(String)params.get("TRADEDATE");
		String processStepNo=(String)params.get("PROCESSSTEPNO");//步骤编号
		String userName=(String)params.get("USERNAME");
		String flag=(String)params.get("FLAG");//用于判断是否删除行情确认表该渠道当天的行情
		fundMarkerProcess(tradeDate,channnelCode,processStepNo,userName,flag);
		LOGGER.info("传入步骤处理方法的参数为"+params.toString());
        /**
         * 具体业务逻辑：将list中的元素加上辅助参数中的数据返回
         */
        ResultBean<String> resultBean = ResultBean.newInstance();
        resultBean.setData(channnelCode.toString());
        return resultBean;
	}

	/**
	 * @Description 上传后置验证上传文件的准确性
	 **/
	@Override
	public void checkUploadFtpFiles(Map<String, Object> inputMap) {
		Map<String,Object> retMap = new HashMap<>(); 
		StringBuilder msg = new StringBuilder();
		int sum = 0;
		
		List   channelCodeList = (List)inputMap.get("channelCodeList")==null?new ArrayList<String>():(List)inputMap.get("channelCodeList");
		String tradeDate = MapUtils.getString(inputMap, "tradeDate","");
		String fileType = MapUtils.getString(inputMap, "fileType","");
		
		FTPUtils ftpUtil = new FTPUtils();
		Map<String,Object> dictMap = ftpUtil.chechDictBydictCode();
		String ftpremote =MapUtils.getString(dictMap, Const.FTP_REMOTE,"/");
		
		ftpUtil.checkFTPConnect(dictMap);
		
		if(null!=channelCodeList && channelCodeList.size()>0){
			for(int i=0;i<channelCodeList.size();i++){
				String channelCode = channelCodeList.get(i).toString();
				channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
				PchannelInfo channelInfo =  channelInfoService.queryChannelInfoByChannelCode(channelCode);
				String FTPUrl = channelInfo.getCiSztSendPath(); //深证通发送路径
				if(StringUtils.isEmpty(FTPUrl)){
					FTPUrl = ftpremote+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/";
				}else{
					if(!FTPUrl.endsWith("/") && !FTPUrl.endsWith(File.separator)){
						FTPUrl+="/";
					}
				}
				
				if(Const.CHANNELCODESHNS.equals(channelCode)&&Const.FILE_TYPE_07.equals(fileType)){
				    //上传07文件在fundday文件夹下
					FTPUrl = ftpremote+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/"+Const.UPLOADSHNS07+"/";
				}else if(Const.CHANNELCODESHNS.equals(channelCode)&&!Const.FILE_TYPE_07.equals(fileType)){
						//上传其他文件在confirm文件夹下
						FTPUrl = ftpremote+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/"+Const.UPLOADSHNSOTHER+"/";
				}
				
				List<String> nameList = ftpUtil.getUploadNameList(channelCode, tradeDate, fileType);
				retMap = ftpUtil.checkFTPUploadFile(nameList,  FTPUrl);
				
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
					LOGGER.info("上传文件有错误的渠道为:"+channelCode);
					msg.append(channelCode+"--"+"上传文件出错"+"\r\n");
					msg.append(fileType+"FTP目录文件与预期上传文件名称不一致，请重新上传");
					sum = sum+1;
				}
			}
		}
		
		if(sum>0){
			//发邮件
			LOGGER.info("调用发邮件接口。。");
			CallS100411 callS100411 = new CallS100411();
			callS100411.callS100441(msg.toString());
		}
		
		//关闭FTP
        if(FTPUtils.client!=null){
          FTPUtils.getCloseFtpClient();
          LOGGER.info("FTP连接已关闭");
        }
	}


}
