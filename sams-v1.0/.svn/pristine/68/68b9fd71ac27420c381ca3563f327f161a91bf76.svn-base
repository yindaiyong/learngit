package com.sams.business.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.FundAccountReconCfmService;
import com.sams.batchfile.service.ProcessingStateService;
import com.sams.batchfile.service.impl.FundMarketProcessorServiceImpl;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.custom.model.PchannelInfo;
import com.sams.wsdl.impl.CallS100411;

@Component
public class TaskCheckFileServiceController {
	private static Logger LOGGER = LoggerFactory.getLogger(FundMarketProcessorServiceImpl.class);
	
	@Autowired
	private ProcessingStateService processingStateService;
	
	@Autowired
	private ChannelInfoService channelInfoService;
	
	@Autowired
	private FundAccountReconCfmService fundAccountReconCfmService;
	
	//@Scheduled(cron = "0 0 0,9,12,18,21 * * ? ") // 每天的9点，12点，18点，21点执行一次
	//@Scheduled(cron = "*/20 * * * * ?") // 测试用 20秒执行一次
	public void run() {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		Map<String,Object> retMap = new HashMap<>(); 
		StringBuilder msg = new StringBuilder();
		int sum = 0;
		System.out.println("定时器执行开始时间：" + sm.format(new Date()));
		try{ 
			String transDate = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
			String fileType = "";
			Map<String,Object> qryMap = new HashMap<String,Object>();
			/*qryMap.put("TRANSDATE", transDate);
			qryMap.put("FLOWCODE", "111111");*/
			List<String> channelCodeList = processingStateService.checkChannelCodeList(qryMap);
			
			
			FTPUtils ftpUtil = new FTPUtils();
			Map<String,Object> dictMap = ftpUtil.chechDictBydictCode();
			String ftpremote =MapUtils.getString(dictMap, Const.FTP_REMOTE,"/");
			
			ftpUtil.checkFTPConnect(dictMap);
			
			for(int i=0;i<channelCodeList.size();i++){
				String channelCode = channelCodeList.get(i).toString();
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
				
				//拿到所有需要校验的文件名称
				List<String> nameList = ftpUtil.getUploadNameList(channelCode, transDate, fileType);
				List<String> nameList07 = ftpUtil.getUploadNameList(channelCode, transDate,Const.FILE_TYPE_07);
				List<String> nameList94 = ftpUtil.getUploadNameList(channelCode, transDate,Const.FILE_TYPE_94);
				nameList.addAll(nameList07);
				nameList.addAll(nameList94);
				
				//校验文件名称是否存在于FTP中
				retMap = checkFTPUploadFile(nameList,  FTPUrl);
				
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
					LOGGER.info("上传文件有错误的渠道为:"+channelCode);
					msg.append(channelCode+"--"+"上传文件出错"+"\r\n");
					msg.append("FTP目录文件中包含.OK文件，请检查上传文件");
					sum = sum+1;
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
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		} 
		System.out.println("定时器执行完成时间:" + sm.format(new Date()));
		
	}
	
	
	/**
	 * @author: wangchao 2019年4月8日  下午1:50:41
	 * @Description: 上传完毕之后校验FTP文件是否与预期文件相一致
	 * @param:file:文件
	 * @return: localArrayList本地列表名称    nameList预期列表名称
	 */
	public Map<String,Object> checkFTPUploadFile(List<String> nameList, String ftpUrl){
		Map<String,Object> retMap =Maps.newHashMap();
		StringBuilder msg = new StringBuilder();
		boolean allFileDataNotExists = false;//FTP是否存在当天的文件
		boolean okFileDataExist = false;//单个文件是否存在
		String retMsg = "";
		//校验FTP路径中文件是否与预估文件名称一致
		try{
		  for(int i = 0; i < nameList.size(); i++){
				 InputStream is = null;
				//只检验是否含有.OK文件,不是ok文件名称continue
				if(!nameList.get(i).toUpperCase().contains(Const.FILE_OK)){
					continue;
				}
				String ftpFilePath = ftpUrl+nameList.get(i);
				is = FTPUtils.client.retrieveFileStream(new String(ftpFilePath.getBytes("GBK"),FTP.DEFAULT_CONTROL_ENCODING));

				if (is == null) {
					LOGGER.info(nameList.get(i)+"文件在"+ftpUrl+"路径下不存在");
					/*msg.append(nameList.get(i)+"文件在"+ftpUrl+"路径下不存在"+"\r\n");*/
	                retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
	              
	            }else{
	            	 LOGGER.info(nameList.get(i)+"文件在"+ftpUrl+"路径下存在");
	            	 msg.append(nameList.get(i)+"文件在"+ftpUrl+"路径下存在"+"\r\n");
	            	 okFileDataExist=true;
	            	 is.close();
	            	 FTPUtils.client.completePendingCommand();
	            }
				
		   }
		}catch(Exception e){
			retMap= ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0020);
			retMap.put("retMsg", ExceptionConStants.retMsg_FT0020);
			e.printStackTrace();
		}
		
		if(okFileDataExist){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999);
		}else{
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		}
		retMap.put("retMsg", msg.toString());
		return retMap;
	}
	
	/**
	 * 备份历史数据   
	 * @Title: backUpData   
	 * @author: yindy 2020年3月13日 下午2:21:32
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	@Scheduled(cron = "0 0 1 1 * ?") //每月1号的1点
	public void backUpData() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MONTHNUM", "-2");
		LOGGER.info("执行备份对账数据定时任务开始。。");
		fundAccountReconCfmService.backUpAccountRecon(map);
		LOGGER.info("执行备份对账数据定时任务结束。。");
	}
}
