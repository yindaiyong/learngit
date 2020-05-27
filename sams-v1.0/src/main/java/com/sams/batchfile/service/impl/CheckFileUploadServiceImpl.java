package com.sams.batchfile.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.CheckFileUploadService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.CheckFileUploadMapper;
import com.sams.custom.model.CheckFileUpload;

@Service
public class CheckFileUploadServiceImpl implements CheckFileUploadService{

	private static Logger LOGGER = LoggerFactory.getLogger(AccountReqCfmServiceImpl.class);
	
	@Autowired
	private CheckFileUploadMapper checkFileUploadMapper;
	
	
	@Override
	public CheckFileUpload selectByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(CheckFileUpload entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(CheckFileUpload entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(CheckFileUpload entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CheckFileUpload> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 校验文件是否上传成功数据显示
	 * @Title: findCheckFileUploadCondition    
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param
	 * @return Map
	 * */
	@Override
	public List<CheckFileUpload> findCheckFileUploadCondition(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return checkFileUploadMapper.findCheckFileUploadCondition(pageInfo, condition);
	}

	/**
	 * 读取深圳通日志文件进行上传文件与否的判断
	 * @Title: manager   
	 * @author: 王超 2020年4月26日 下午1:55:17
	 * @return: String      
	 */
	@Override
	public Map<String, Object> readLog(String tradeDate,String loginName) {
		LOGGER.info("读取深圳通日志判断文件是否上传成功步骤开始");
		Map<String,Object> retMap = new HashMap<String,Object>();
		//新建一个FTP工具类
		FTPUtils ftpUtil = new FTPUtils();
		//当天已经读取过多少行数据
		Long lineNumber= Long.parseLong(selectTransDateMaxLineNumber(tradeDate)==null?"0":selectTransDateMaxLineNumber(tradeDate));
		//目录是否存在标识
		boolean ftpPathIsExist =true;
		//建立一个文件输入流
		InputStream is = null;
		//每行的内容
		String inLine = null;
		//记录读取行数
		Long countLine = 0L;
		//插入校验文件上传成功表的数据
		List<Map<String,Object>> insertList = new ArrayList<Map<String,Object>>();
		
		try {
			//获取读取日志所需参数配置
			Map<String,Object> dictMap = ftpUtil.chechDictBydictCode();
			/*//进行FTP连接的校验 返回0000则成功，否则提示错误信息，20200515 深证通日志同步到本地，不再在深证通上进行读取
			retMap = ftpUtil.checkFTPConnect(dictMap);
			//判断连接不成功，返回错误信息
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))
			{return retMap;}*/
			
			//获取深证通本地日志路径
			String ftpLogDir =MapUtils.getString(dictMap, Const.FTP_LOG_DIR,"/")+"/";
			LOGGER.info("读取字典表配置FTP日志路径为"+ftpLogDir);
			//获取日志名称前缀
			String ftpLogPrefix =MapUtils.getString(dictMap, Const.FTP_LOG_PREFIX,"");
			//获取日志名称后缀
			String ftpLogPostfix =MapUtils.getString(dictMap, Const.FTP_LOG_POSTFIX,"");
			//获取本地上传文件目录
			String localSend =MapUtils.getString(dictMap, Const.FTP_FILE_DIR,"")+Const.FTP_REMOTE_UPLOAD_DIR; 
			//String localSend = "C:"+"\\"+"daixiaoftp"+"\\"+"send"+"\\";//"C:\daixiaoftp\send\";
			/*///切换到日志文件目录
			ftpPathIsExist =ftpUtil.client.changeWorkingDirectory(ftpLogDir);
			/切换日志目录失败，返回错误信息，请检查深圳通日志路径参数配置
			if(!ftpPathIsExist){
				LOGGER.info("切换日志目录失败，返回错误信息，请检查深圳通日志路径参数配置");
				return ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0024);
			}
			LOGGER.info("目录已切换到深圳通日志文件下"+ftpLogDir);*/
			//拼接日志文件名称  格式：fxapi_20200423_235959.log
		    String ftpLogName =  ftpLogPrefix+"_"+tradeDate+"_"+ftpLogPostfix+Const.FTP_LOG;
		    LOGGER.info("深圳通日志文件名称为"+ftpLogName);
		    //日志文件路径
		    String ftpLogPath = ftpLogDir+ftpLogName;
		    LOGGER.info("深圳通日志文件路径为"+ftpLogPath);
		    //获取日志文件的输入流
			/*is = ftpUtil.client.retrieveFileStream(new String(ftpLogPath.getBytes("GBK"),FTP.DEFAULT_CONTROL_ENCODING));*/
		    is = new FileInputStream(ftpLogPath);
			//判断文件输入流是否为空，为空则提示错误信息 //|| ftpUtil.client.getReplyCode() == FTPReply.FILE_UNAVAILABLE
			if(is == null){
				LOGGER.info("日志文件输入流为空，请检查日志前后缀参数配置以及文件名称");
				return ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0025);
			}
			LOGGER.info("开始读取深圳通日志文件");
			//缓冲处理流、字符流、输入流
			BufferedReader reader=new BufferedReader(new InputStreamReader(is));
			//读取日志文件内容 Delete file=C:\daixiaoftp\send\TTTNETGTJA\OFD_SH_601_20200423_02.TXT.OK ok.
			while ((inLine = reader.readLine()) != null) {
			  //当前行数
			  countLine = countLine+1;
			  if(countLine<=lineNumber){
				  continue;
			  }
			  if(inLine.contains(Const.FILE_DELETE) && inLine.contains(localSend) && inLine.contains(Const.FILE_OK)){
				  //获取上传成功文件名称 Delete file=C:\daixiaoftp\send\TTTNETDBZQ\OFD_SH_638_20200424_07.TXT.OK ok.
				  Map<String,Object> paramMap = new HashMap<String,Object>();
				  String channelCode = "";
				  //获取渠道名称 上海农商的多了一层目录，特殊处理
				  if(inLine.contains(Const.CHANNELCODESHNS)){
					   channelCode = inLine.split("\\\\")[inLine.split("\\\\").length-3];
				  }else{
					//获取渠道名称
					   channelCode = inLine.split("\\\\")[inLine.split("\\\\").length-2]; 
				  }
				  //获取上传文件名称
				  String fileName = inLine.split("\\\\")[inLine.split("\\\\").length-1].replaceAll(Const.FILE_OK, "").replaceAll("ok.", "");
				  //获取文件上传成功时间
				  String successTime = inLine.split(",")[0].substring(1, inLine.split(",")[0].length());
				  LOGGER.info("日志中"+countLine+"行显示"+channelCode+"渠道的"+fileName+"文件显示上传成功");
				  //需要插入的参数
				  paramMap.put("CHANNELCODE", channelCode);
				  paramMap.put("FILENAME", fileName);
				  paramMap.put("LINENUMBER", countLine);
				  paramMap.put("TRANSDATE", tradeDate);
				  paramMap.put("UPLOADFLAG", Const.NORMAL_CHECK_STATUS_01);
				  paramMap.put("MSG", Const.READ_LOG_SUCCESS_MSG);
				  paramMap.put("CUSER", loginName);
				  paramMap.put("SUCCESSTIME",successTime);
				  insertList.add(paramMap);
			  }
			}
			//缓冲处理流关闭
			reader.close();
			LOGGER.info("缓冲处理流关闭");
		    //保存校验上传成功的文件数据
			if(null!=insertList && insertList.size()>0){
				retMap = saveUploadSuccessData(insertList);
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))
				{return retMap;}
			}
			return ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		} catch (Exception e) {
			e.getMessage();
			LOGGER.info(e.getMessage());
			return ExceptionConStants.getRetObjects(ExceptionConStants.retCode_9999,e.getMessage());
		}finally {
			try {
				if(null !=is){
					is.close();
					LOGGER.info("输入流关闭");
					/*ftpUtil.client.completePendingCommand();*/
				}
				/*ftpUtil.closeFtpClient();*/
			} catch (IOException e) {
				e.getMessage();
				LOGGER.info(e.getMessage());
				return ExceptionConStants.getRetObjects(ExceptionConStants.retCode_9999,e.getMessage());
			}
		}
	}

	/**
	 * 获取处理日期最大的行号
	 * @Title: selectTransDateMaxLineNumber   
	 * @author: 王超 2020年4月28日 下午1:55:17
	 * @return: Long      
	 */
	public String selectTransDateMaxLineNumber(String tradeDate) {
		return checkFileUploadMapper.selectTransDateMaxLineNumber(tradeDate);
	}

	/**
	 * 保存校验上传成功的文件数据
	 * @Title: saveUploadSuccessData   
	 * @author: 王超 2020年4月28日 下午2:55:17
	 * @param: Map<String, Object> map  CHANNELCODE 渠道编号  TRADEDATE 交易日期  FILENAME 文件名称
	 * @return: Map<String, Object>      
	 */
	public Map<String, Object> saveUploadSuccessData(List<Map<String, Object>> insertList) {
		//需要插入校验上传成功的文件数量
		int size = insertList.size();
		LOGGER.info("需要插入校验上传成功的文件数量"+size);
		//上传成功的数量
		int insertCount = 0;
		List<Map<String, Object>> newInsertList  = new ArrayList<>();
		int num = 100;
		if(size > num ){
			for (int i = 0; i < size ; i++) {
				newInsertList.add(insertList.get(i));
				if (num == newInsertList.size() || i == size-1) {  //载体list达到要求,进行批量操作
                    //调用批量插入
					insertCount+=insertUploadSuccessData(newInsertList);
					newInsertList.clear();//每次批量操作后,情况载体list,等待下次的数据填入
                }
			}
		}else{
			insertCount+=insertUploadSuccessData(insertList);
		}
		LOGGER.info("校验上传成功的文件成功的数量"+insertCount);
		if(size==insertCount){
			LOGGER.info("插入校验上传成功的文件成功，上传完毕");
			return ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);	
		}else{
			LOGGER.info("插入校验上传成功的文件失败，上传完毕");
			return ExceptionConStants.getRetObjects(ExceptionConStants.retCode_9999,"批量保存上传成功的文件数据失败");	
		}
	}

	/**
	 * 批量保存上传成功的文件数据
	 * @Title: insertUploadSuccessData   
	 * @author: 王超 2020年4月28日 下午2:55:17
	 * @param: Map<String, Object> map  CHANNELCODE 渠道编号  TRADEDATE 交易日期  FILENAME 文件名称
	 * @return: Map<String, Object>      
	 */
	@Override
	public int insertUploadSuccessData(List<Map<String, Object>> insertList) {
		return checkFileUploadMapper.insertUploadSuccessData(insertList);
	}
	
}