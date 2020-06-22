package com.sams.batchfile.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.common.ReadFileUtil;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.ReadFileService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.service.SysDictService;

/**
 * @ClassName:  ReadFileServiceImpl   
 * @Description:读取解析文件   
 * @author: yindy
 * @date:   2019年4月12日 上午10:17:32   
 *
 */
@Service
public class ReadFileServiceImpl implements ReadFileService{

	
	private static Logger LOGGER = LoggerFactory.getLogger(ReadFileServiceImpl.class);

	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private FileDataService fileDataService;
	
	@Autowired
	private SysDictService sysDictService;
	
	/**
	 * 读取索引文件  
	 * @Title: readFile   
	 * @author: yindy 2019年4月12日 下午5:32:07
	 * @param: @param filePath
	 * @param: @param date
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public Map<String, Object> readIndexFile(String filePath,String date,PchannelInfo channelInfo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String dealFile = channelInfo.getCiDealFile();
		List<String> fileType = Arrays.asList(dealFile.split(","));
		File fileDir = new File(filePath);
		File[] files = fileDir.listFiles(); //获得文件下的文件
		if(files ==  null || files.length == 0){
			LOGGER.info(filePath+"该文件夹没有文件");
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0006));
			retMap.put("retMsg", filePath+ExceptionConStants.retMsg_RF0006);
			return retMap;
		}
		String path = "";//索引文件全路径
		//43文件索引前缀
		String indexFileNameOFG = fileDataService.getIndexFileName(Const.FILE_TYPE_43, date, channelInfo)
				.replace(Const.FILE_TXT, "");
		//其他文件索引前缀
		String indexFileNameOFI = fileDataService.getIndexFileName(Const.FILE_TYPE_01, date, channelInfo)
				.replace(Const.FILE_TXT, "");
		boolean OFGflag = false;
		boolean OFIflag = false;
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		String exchangeCsdcChannel = sysDictService.findDictVo(Const.EXCHANGE_CSDC_CHANNEL);
		
		for (File file : files) { //获得索引文件
			String fileName = file.getName();
			if(fileName.indexOf(Const.FILE_OK) > 0  
					|| fileName.indexOf(Const.FILE_OK.toLowerCase()) > 0 
					|| fileName.indexOf(".bak") > 0 ){
				continue;
			}
			if(fileType.contains(Const.FILE_TYPE_43) && fileName.indexOf(indexFileNameOFG) >= 0){
				path = filePath+File.separator+fileName;
				retMap = ReadFileUtil.readTxtIndexFile(filePath,path,channelInfo,Const.FILE_TYPE_43,exchangeCsdcChannel);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
				OFGflag = true;
			}
			if(fileName.indexOf(indexFileNameOFI) >= 0){
				path = filePath+File.separator+fileName;
				retMap = ReadFileUtil.readTxtIndexFile(filePath,path,channelInfo,Const.FILE_TYPE_01,exchangeCsdcChannel);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
				OFIflag = true;
			}
			
		}
		
		if(fileType.contains(Const.FILE_TYPE_43) && !OFGflag){ //OFG索引文件不存在
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0015));
			retMap.put("retMsg", "解析的43文件的索引文件,"+ExceptionConStants.retMsg_RF0015);
			return retMap;
		}
		
		if(!OFIflag){ //OFI索引文件不存在
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0015));
			retMap.put("retMsg", "解析的其他文件的索引文件,"+ExceptionConStants.retMsg_RF0015);
			return retMap;
		}
		return retMap;
	}
	/**
	 * 读取文件   
	 * @Title: readFile   
	 * @author: yindy 2019年4月12日 上午10:18:29
	 * @param: @param filePath
	 * @param: @param fileType
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public Map<String, Object> readFile(String filePath, String fileType,String date,PchannelInfo channelInfo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		//读取文件字段信息
		Map<String,Object> queryColInfo = new HashMap<String, Object>();
		String csdcVer = channelInfo.getCiCsdcVer(); //中登版本
		//获得对应的文件类型字段
		queryColInfo = getFileType(fileType,csdcVer,queryColInfo);
		interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
		List<InterfaceColInfo> colInfoList = interfaceColInfoMapper.selectByInterfaceCodeList(queryColInfo);
		if(colInfoList== null || colInfoList.size() == 0){
			LOGGER.info("根据条件"+queryColInfo+",查询的接口字段信息为空。");
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00014));
			retMap.put("retMsg", "根据条件"+queryColInfo+ExceptionConStants.retMsg_A00014);
			return retMap;
		}
		//获得文件名称 
		File fileDir = new File(filePath);
		File[] list = fileDir.listFiles();
		if(list ==  null || list.length == 0){
			LOGGER.info(filePath+"该文件夹没有文件");
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0006));
			retMap.put("retMsg", filePath+ExceptionConStants.retMsg_RF0006);
			return retMap;
		}
		boolean flag = false; //判断有没有读取的文件
		String fileName = "";
		
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		String exchangeCsdcChannel = sysDictService.findDictVo(Const.EXCHANGE_CSDC_CHANNEL);
		
		for (File file : list) {
			fileName = file.getName();
			//跳过OK文件
			if(fileName.indexOf(Const.FILE_OK) >= 0 
					|| fileName.indexOf(Const.FILE_OK.toLowerCase()) > 0  
					|| fileName.indexOf(".bak") > 0){
				continue;
			}
			if(fileName.indexOf(Const.FIlE_+fileType+".") >= 0 && fileName.indexOf(Const.FIlE_+date+Const.FIlE_) >= 0){
				//根据渠道勾选定向读取
				if(!new File(filePath+File.separator+fileName).exists()){
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0006));
					retMap.put("retMsg", filePath+File.separator+fileName+ExceptionConStants.retMsg_RF0006);
					return retMap;
				}
				retMap = ReadFileUtil.readTxtFile(filePath+File.separator+fileName,fileType,colInfoList,channelInfo,exchangeCsdcChannel);
				LOGGER.info("读取正式文件完成");
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
					return retMap;
				}
				flag = true;
			}
		}
		if(!flag){ //本地没有指定文件
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0006));
			retMap.put("retMsg", filePath+ExceptionConStants.retMsg_RF0006+fileType+"文件！");
		}
		return retMap;
	}
	
	private Map<String, Object> getFileType(String fileType,String csdcVer, Map<String, Object> query) {
		query.put("ICINTERFACECODE", Const.fileTypeMap.get(fileType+csdcVer));
		return query;
	}

}
