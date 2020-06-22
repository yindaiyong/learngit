package com.sams.batchfile.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;



/**
 * 读取文件工具类
 * @ClassName:  ReadFileUtil   
 * @author: yindy
 * @date:   2019年4月12日 上午9:17:15   
 *
 */
public class ReadFileUtil {

	@Autowired
	private static PchannelInfoMapper pchannelInfoMapper;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReadFileUtil.class);

	/**
	 * 把已经读取的文件变成.bak文件
	 * @Title: renameToBak   
	 * @author: yindy 2019年4月12日 上午9:19:12
	 * @param: @param file      需要改变的文件
	 */
	public static void renameToBak(File file){
		file.renameTo(new File(file.getPath()+".bak"));
	}
	
	/**
	 * 刪除文件夾下內容
	 * @Title: delFile   
	 * @author: yindy 2019年8月7日 上午10:20:10
	 * @param: @param file
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean delFile(File file) {
		if (!file.exists()) {
			return false;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				delFile(f);
			}
		}
		return file.delete();
	  }
	
	
	/**
	 * 备份文件夹及文件
	 * @Title: backupFile   
	 * @author: yindy 2019年4月12日 上午9:29:54
	 * @param: @param filePath 文件夹路径
	 * @param: @param fileBackPath     备份的文件夹路径  
	 * @return: void      
	 */
	public static void backupFile(String filePath,String fileBackPath){
		File file = new File(filePath);
		File backDir = new File(fileBackPath);
		if(!backDir.exists()){
			backDir.mkdirs();
		}
		File[] fs = file.listFiles();
		if(fs != null && fs.length > 0){
			for (File f : fs) {  
	            if(f.isFile()){  
	                fileCopy(f.getPath(),fileBackPath+"\\"+f.getName()); //调用文件拷贝的方法  
	            }else if(f.isDirectory()){  
	            	backupFile(f.getPath(),fileBackPath+"\\"+f.getName());  
	            }  
	        }
		}
	}
	/**
	 * 拷贝文件  
	 * @Title: fileCopy   
	 * @author: yindy 2019年6月18日 下午4:29:40
	 * @param: @param src 源地址
	 * @param: @param des   拷贝地址   
	 * @return: void      
	 */
	private static void fileCopy(String src, String des) {  
        BufferedReader br=null;  
        PrintStream ps=null;  
        try {  
            br=new BufferedReader(new InputStreamReader(new FileInputStream(src)));  
            ps=new PrintStream(new FileOutputStream(des));  
            String s=null;  
            while((s=br.readLine())!=null){  
                ps.println(s);  
                ps.flush();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{
	        try {  
	            if(br!=null) br.close();  
	            if(ps!=null) ps.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
        } 
	}
	
	/**
	 * 读取并解析文件   
	 * @Title: readTxtFile   
	 * @author: yindy 2019年6月18日 下午4:31:01
	 * @param exchangeCsdcChannel 
	 * @param: @param filePath 要读取的文件
	 * @param: @param fileType 文件类型
	 * @param: @param colInfoList 字段集合
	 * @param: @param channelInfo 渠道信息
	 * @return: Map<String,Object>       返回读取的结果和读取的数据集
	 */
	public static Map<String,Object> readTxtFile(String filePath,String fileType,List<InterfaceColInfo> colInfoList,PchannelInfo channelInfo, String exchangeCsdcChannel){
		Map<String,Object> retMap = new HashMap<String,Object>();//最终结果集
		//校验文件名是否正确
		int line = 1 ; // 开始行数
		InputStreamReader fReader = null;
        BufferedReader reader = null;
        List<String> fieldList = new ArrayList<String>(); //装载字段名称
        List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>(); //存放数据集合
        String csdc = channelInfo.getCiCsdcVer();
        csdc = exchangeCsdc(channelInfo,exchangeCsdcChannel);
        try {
			fReader = new InputStreamReader(new FileInputStream(filePath),Const.GB_18030);
			reader = new BufferedReader(fReader);
			LOGGER.info("以行为单位开始读取"+filePath+"文件");
			String content = null ; //记录每行内容
			Integer fieldNum = 0 ; //读取字段数量
			Integer fieldAddCount = 0 ; //累加的字段数量
			Integer fileDataNum = 0 ; //读取的数据行数
			Integer filedDataCount = 0 ;// 累加文件数据的数据行数
			long starta  = new Date().getTime();
			LOGGER.info("开始解析"+fileType+"文件");
			while((content = reader.readLine()) != null){
				if(!Const.FILE_TYPE_43.equals(fileType)){
					//校验中登版本
					if(line == 2 && !Const.FILE_TYPE_94.equals(fileType)){
						if(!csdc.equals(content.trim())){
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
							retMap.put("retMsg", fileType+"文件中登版本不一致,设置的版本为:"+csdc+",文件中的版本为:"+content.trim());
							break;
						}
					}
				}
				//校验接收人发送人
				Map<String, Object> Map = checksaleCodeAndDate(line,channelInfo,content,fileType);
				if(!ExceptionConStants.retCode_0000.equals(Map.get("retCode"))){
					retMap = Map ; 
					break;
				}
				//验证文件类型
				retMap = checkFileType(line,fileType,content);
				if(ExceptionConStants.retCode_RF0001.equals(retMap.get("retCode")))break;
				//校验字段个数
				retMap = checkFieldNumAndFieldCount(fileType,line,content,fieldNum,colInfoList.size());
				if(ExceptionConStants.retCode_RF0002.equals(retMap.get("retCode")))break;
					
				fieldNum = Integer.valueOf(MapUtils.getString(retMap, "FILENUM", "0"));
				
				if(line > 10 && line <= (fieldNum+10)){ //读取字段
					fieldList.add(content.trim().toUpperCase());
					fieldAddCount++;
				}
				if(line == (fieldNum+11)){ //数据行数字段，
					//1.判断累加的字段个数与读取的是否一致
					retMap = checkFieldAddCountAndFieldNum(fileType,fieldAddCount,fieldNum);
					if(ExceptionConStants.retCode_RF0010.equals(retMap.get("retCode")))break;
					//2.读到这个字段可以校验读取的字段和设置的字段是否一致
					retMap = checkFieldIsSame(fileType,colInfoList,fieldList);
					if(ExceptionConStants.retCode_RF0007.equals(retMap.get("retCode")))break;
					//3.读取数据行
					retMap = getFileDataNum(content,fileDataNum);
					//if(Integer.valueOf(retMap.get("FILEDATANUM").toString()) == 0)break;
					fileDataNum = Integer.valueOf(MapUtils.getString(retMap, "FILEDATANUM", ""));
				}
				if(line > (fieldNum+11) && !Const.FILE_OFDCFEND.equals(content.trim()) && !StringUtils.isEmpty(content.trim())){ //读取数据行并解析
					Map<String, InterfaceColInfo> colInfoMap = FileDataUtil.exchangeListToMap(colInfoList);
					Map<String, Object> dataResult = getDataDetailInfo(content,fieldList,colInfoMap,fileType);
					if(!ExceptionConStants.retCode_0000.equals(dataResult.get("retCode"))){
						retMap.putAll(dataResult);
						break;
					}
					dataList.add(dataResult);
					filedDataCount++;
				}
				//文件结束
				retMap = readEndCompNumAndCount(fileType,content, filedDataCount, fileDataNum,null,null);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")) || retMap.containsKey(Const.FILE_OFDCFEND))break;
				line++;
			}
			retMap.put("datalist", dataList);
			LOGGER.info("解析完的时间为:"+(new Date().getTime()-starta));
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("解析正式文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "readTxtFile方法,解析正式文件异常,请联系管理员！");
			return retMap;
		}finally{
			closeFile(fReader, reader);
		}
        if(retMap.get("retCode") == null ){
        	retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
        }
		return retMap;
	}
	

	/**
	 * @param exchangeCsdcChannel 
	 * 对特殊渠道转换中登版本号   
	 * @Title: exchangeCsdc   
	 * @author: yindy 2020年5月6日 下午2:47:37
	 * @param: @param channelInfo
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String exchangeCsdc(PchannelInfo channelInfo, String exchangeCsdcChannel) {
		String channeLCode = channelInfo.getCiChannelCode(); //渠道编号
		String csdc = channelInfo.getCiCsdcVer(); //版本号
		if(!StringUtils.isEmpty(exchangeCsdcChannel) && exchangeCsdcChannel.contains(channeLCode)){
			csdc = Const.FILE_VER_20;
		}
		return csdc;
	}

	/**
	 * 读取数据行数   
	 * @Title: getFileDataNum   
	 * @author: yindy 2019年5月10日 下午1:55:45
	 * @param: @param content 读取到的数据
	 * @param: @param fileDataNum 文件数据行数
	 * @return: Map<String,Object>  返回读取的数据行数     
	 */
	private static Map<String, Object> getFileDataNum(String content,
			Integer fileDataNum) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(Integer.valueOf(content.trim())!=0){
			fileDataNum = Integer.parseInt(content.trim());
		}
		retMap.put("FILEDATANUM", fileDataNum);
		return retMap;
	}

	/**
	 * 校验字段是否一致  
	 * @Title: checkFieldIsSame   
	 * @author: yindy 2019年5月10日 下午1:55:10
	 * @param: @param fileType 文件类型
	 * @param: @param colInfoList 查询的字段集合
	 * @param: @param fieldList 读取的字段集合
	 * @return: Map<String,Object>   返回校验的结果    
	 */
	private static Map<String, Object> checkFieldIsSame(
			String fileType, List<InterfaceColInfo> colInfoList, List<String> fieldList) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			for(int i = 0;i<colInfoList.size();i++){
				if(!colInfoList.get(i).getIcColName().equalsIgnoreCase(fieldList.get(i))){
					LOGGER.info("设置的字段"+colInfoList.get(i).getIcColName() +"和读取的字段"+fieldList.get(i)+"不一致");
					retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0007);
					retMap.put("retMsg", ExceptionConStants.retMsg_RF0007+",设置的字段为:"+colInfoList.get(i).getIcColName() +",读取的字段为:"+fieldList.get(i));
					break;
				}
			}
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error(fileType+"文件读取字段与设置字段不一致,"+error);
			e.printStackTrace();
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0007);
			retMap.put("retMsg", "checkFieldIsSame方法,校验文件字段是否一致异常,请联系管理员！");
			return retMap;
		}
		return retMap;
	}
	/**
	 * 校验读取的字段和累加的字段是否一致 
	 * @Title: checkFieldAddCountAndFieldNum   
	 * @author: yindy 2019年6月18日 下午4:41:16
	 * @param fileType  解析的文件类型
	 * @param: @param fieldAddCount 累加的字段数
	 * @param: @param fieldNum 读取的字段数
 	 * @return: Map<String,Object>     返回校验结果  
	 */
	private static Map<String, Object> checkFieldAddCountAndFieldNum(
			String fileType, Integer fieldAddCount, Integer fieldNum) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(fieldAddCount != fieldNum){
			LOGGER.info("文件字段个数不正确,读取的个数为:"+fieldNum+",实际个数为:"+fieldAddCount);
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0010);
			retMap.put("retMsg", ExceptionConStants.retMsg_RF0010+","+fileType+"读取的个数为:"+fieldNum+",实际个数为:"+fieldAddCount);
		}
		return retMap;
	}

	/**
	 * 校验读取的字段和设置的字段是否一致    
	 * @Title: checkFieldNumAndFieldCount   
	 * @author: yindy 2019年6月18日 下午4:43:29
	 * @param fileType 
	 * @param: @param line 读取的行数
	 * @param: @param content 读取的值
	 * @param: @param fieldNum 读取的字段个数
	 * @param: @param size 设置的字段个数
	 * @return: Map<String,Object>      返回校验结果 
	 */
	private static Map<String, Object> checkFieldNumAndFieldCount(String fileType, int line,
			String content, Integer fieldNum, int size) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(10 == line){ //该行为字段个数
			fieldNum = Integer.parseInt(content.trim());
			if(size != fieldNum.intValue()){
				LOGGER.info("文件字段个数不正确,实际个数为:"+fieldNum+",规定个数为:"+size);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0002);
				retMap.put("retMsg", fileType+"文件"+ExceptionConStants.retMsg_RF0002+",实际个数为:"+fieldNum+",规定个数为:"+size);
			}
		}
		retMap.put("FILENUM", fieldNum);
		return retMap;
	}

	/**
	 * 校验文件类型 
	 * @Title: checkFileType   
	 * @author: yindy 2019年5月10日 下午1:56:24
	 * @param: @param line 数据行数
	 * @param: @param fileType 传入的文件类型
	 * @param: @param content 读取的文件类型
	 * @return: Map<String,Object>    返回校验结果   
	 */
	private static Map<String, Object> checkFileType(int line, String fileType, String content) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(line == 7 ){ //该行为文件类型
			String fileKind = content.trim();
			if(!fileType.equals(fileKind)){
				LOGGER.info("读取的文件类型不对,读取的文件类型为:"+fileType+",实际文件类型为:"+fileKind);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0001);
				retMap.put("retMsg", ExceptionConStants.retMsg_RF0001+",读取的文件类型为:"+fileType+",实际文件类型为:"+fileKind);
			}
		}
		return retMap;
	}

	/**
	 * 读取数据行详细信息     
	 * @Title: getDataDetailInfo   
	 * @author: yindy 2019年6月18日 下午4:46:25
	 * @param: @param content 数据行信息
	 * @param: @param fieldList 字段集合信息
	 * @param: @param colInfoMap 字段名称和字段信息的键值对象
	 * @param: @param fileType 文件类型
	 * @return: Map<String,Object>    返回读取的数据行结果  
	 */
	private static Map<String, Object> getDataDetailInfo(String content,
			List<String> fieldList, Map<String, InterfaceColInfo> colInfoMap,String fileType) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		int index = 0;//下标
		//LOGGER.info("要解析的数据行为:"+content+",\r\n总长度为:"+content.length());
		try {
			byte[] data = content.getBytes(Const.GB_18030); 
			for (String fieldName : fieldList) {
				InterfaceColInfo colInfo = colInfoMap.get(fieldName);
				int length = Integer.parseInt(colInfo.getIcColLength());
				//截取获取对应值
				byte[] value = new byte[length];
				System.arraycopy(data, index, value, 0, length);
				String realValue = new String(value,Const.GB_18030);
				resultMap.put(fieldName, realValue);
				//LOGGER.info("解析字段为："+fieldName+"字段值为："+realValue);
				index = index + length;
			}
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("解析数据行出错"+error);
			e.printStackTrace();
			resultMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0003);
			resultMap.put("retMsg", ExceptionConStants.retMsg_RF0003+","+fileType+"文件数据行长度有误,请检查文件!");
			return resultMap;
		}
		return resultMap;
		
	}

	/**
	 * @param exchangeCsdcChannel 
	 * 读取索引文件     
	 * @Title: readTxtIndexFile   
	 * @author: yindy 2019年6月18日 下午4:49:55
	 * @param: @param fileDir 读取的文件夹地址
	 * @param: @param filePath 读取的文件地址
	 * @param: @param channelInfo 渠道信息
	 * @param: @param fileType 文件类型
	 * @param: @param fileName 文件名称
	 * @return: Map<String,Object>    返回读取的结果   
	 * @throws
	 */
	public static Map<String, Object> readTxtIndexFile(String fileDir,
			String filePath, PchannelInfo channelInfo,String fileType, String exchangeCsdcChannel) {
		Map<String,Object> retMap = new HashMap<String,Object>();//最终结果集
		int line = 1 ; // 开始行数
		Integer fileNum = 0; //索引文件数据数量
		Integer fileCount = 0 ; //索引文件累计数量 
		InputStreamReader fReader = null;
        BufferedReader reader = null;
        String paths = ""; //记录读取的正式文件名称
        String csdc = channelInfo.getCiCsdcVer(); //中登版本
        csdc = exchangeCsdc(channelInfo,exchangeCsdcChannel);
        try {
        	fReader = new InputStreamReader(new FileInputStream(filePath),Const.GB_18030);
			reader = new BufferedReader(fReader);
			String content = null; //每行的内容
			while((content = reader.readLine()) != null){
				//校验中登版本
				if(!Const.FILE_TYPE_43.equals(fileType)){
					if(line == 2){
						if(!csdc.equals(content.trim())){
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
							retMap.put("retMsg", "中登版本不一致,设置的版本为:"+csdc+",文件中的版本为:"+content.trim());
							break;
						}
					}
				}
				//校验接收人发送人
				Map<String, Object> Map = checksaleCodeAndDate(line,channelInfo,content,fileType);
				if(!ExceptionConStants.retCode_0000.equals(Map.get("retCode"))){
					retMap = Map ; 
					break;
				}
				//获得数据行数
				Map<String,Object> fileNumMap = getFileDataNum(line,content,fileNum);
				fileNum = Integer.valueOf(MapUtils.getString(fileNumMap, "FILENUM", "0"));
				//读取数据行，判断正式文件是否存在
				String dealFile = channelInfo.getCiDealFile();
				if(line > 6 && !Const.FILE_OFDCFEND.equals(content.trim()) && !StringUtils.isEmpty(content.trim())){
					fileCount++;
					int length = content.trim().length();
					if(dealFile.contains(content.trim().substring(length-6, length-4))){
						paths += content.trim()+",";
					}
				}
				//结束标识，比较读取数量和统计数量
				retMap = readEndCompNumAndCount(fileType,content,fileCount,fileNum,paths,fileDir);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")) || retMap.containsKey(Const.FILE_OFDCFEND))break;
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("解析索引文件出错"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "readTxtIndexFile方法,解析索引文件异常，请联系管理员！");
			return retMap;
		}finally{
			closeFile(fReader,reader);
		}
		return retMap;
	}

	/**
	 * 校验接收人发送人  
	 * @Title: checksaleCodeAndDate   
	 * @author: yindy 2019年6月18日 下午4:52:30
	 * @param: @param line 行数
	 * @param: @param channelInfo 渠道信息
	 * @param: @param content 读取的数据
	 * @param: @param fileType 文件类型
	 * @return: Map<String,Object>   返回校验结果   
	 * @throws
	 */
	private static Map<String, Object> checksaleCodeAndDate(int line,
			PchannelInfo channelInfo, String content, String fileType) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String saleAgentCode = "";
		String salePerson = "";
		if(channelInfo != null){
			saleAgentCode = channelInfo.getCiSaleAgentCode();
			salePerson = channelInfo.getCiSalesPerson();
			if(line == 3 && (Const.RECV_FILE_TYPE.contains(fileType))){
				if(!saleAgentCode.equals(content.trim())){
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0008));
					retMap.put("retMsg", channelInfo.getCiChannelName()+"渠道的"+fileType+ExceptionConStants.retMsg_FI0008);
					return retMap;
				}
			} 
			
			if(line == 4 && (Const.RECV_FILE_TYPE.contains(fileType))){
				if(!salePerson.equals(content.trim())){
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0008));
					retMap.put("retMsg", channelInfo.getCiChannelName()+"渠道的"+fileType+ExceptionConStants.retMsg_FI0008);
					return retMap;
				}
			} 
			
			if(line == 3 && (Const.SEND_FILE_TYPE.contains(fileType))){
				if(!salePerson.equals(content.trim())){
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0008));
					retMap.put("retMsg", channelInfo.getCiChannelName()+"渠道的"+fileType+"文件"+ExceptionConStants.retMsg_FI0008);
					return retMap;
				}
			}
			
			if(line == 4 && (Const.SEND_FILE_TYPE.contains(fileType))){
				if(!saleAgentCode.equals(content.trim())){
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0008));
					retMap.put("retMsg", channelInfo.getCiChannelName()+"渠道的"+fileType+"文件"+ExceptionConStants.retMsg_FI0008);
					return retMap;
				}
			}
		}
		return ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
	}

	/**
	 * 关闭输入流   
	 * @Title: closeFile   
	 * @author: yindy 2019年5月10日 下午1:42:45
	 * @param: @param fReader
	 * @param: @param reader      
	 * @return: void      
	 * @throws
	 */
	private static void closeFile(InputStreamReader fReader,
			BufferedReader reader) {
		if(fReader != null){
			try {
				fReader.close();
			} catch (IOException e) {
				String error = FileDataUtil.getErrorMsg(e);
				LOGGER.error("解析索引文件出错"+error);
			}
		}
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				String error = FileDataUtil.getErrorMsg(e);
				LOGGER.error("解析索引文件出错"+error);
			}
		}
		
	}

	/**
	 * @param fileType 
	 * 比较读取的文件数量和累积的是否一致 
	 * @Title: readEndCompNumAndCount   
	 * @author: yindy 2019年6月18日 下午4:53:49
	 * @param: @param content 读取的数据行
	 * @param: @param fileCount 文件累加数量
	 * @param: @param fileNum 读取的文件数量
	 * @param: @param paths 包含的文件字符串
	 * @param: @param fileName  文件名称
	 * @param: @param fileDir 文件夹路径
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private static Map<String, Object> readEndCompNumAndCount(String fileType,
			String content, Integer fileCount, Integer fileNum,String paths,String fileDir) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		if(Const.FILE_OFDCFEND.equals(content.trim())){ //文件结束标志  
			//比较读取的数据数量和累加的数量是否一致
			if(fileCount.intValue() != fileNum.intValue()){
				LOGGER.info(fileType+"文件比较读取的数据数量和累加的数量是否一致,读取的数量为:"+fileNum+",累加的数量为:"+fileCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0009);
				retMap.put("retMsg", ExceptionConStants.retMsg_RF0009+","+fileType+"文件读取的数量为:"+fileNum+",累加的数量为:"+fileCount);
			}
			//数据行文件是否有文件 
			if(paths != null){
				String[] arrName = paths.split(",");
				for (String name : arrName) {
					if(!new File(fileDir+File.separator+name).exists()){
						retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0005);
						retMap.put("retMsg", ExceptionConStants.retMsg_RF0005+fileDir+File.separator+name);
						return retMap;
					}
				}
			}
			retMap.put(Const.FILE_OFDCFEND, true);
		}
		return retMap;
	}

	/**
	 * 读取索引文件的数据行   
	 * @Title: getFileDataNum   
	 * @author: yindy 2019年6月18日 下午4:56:37
	 * @param: @param line 行数
	 * @param: @param content 读取的内容
	 * @param: @param fileNum 读取的数据
	 * @return: Map<String,Object>    返回读取的数据行  
	 * @throws
	 */
	private static Map<String, Object> getFileDataNum(int line, String content,
			Integer fileNum) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(line == 6){ //该行为索引文件数据数量
			fileNum = Integer.parseInt(content.trim());
			LOGGER.info("索引文件的读取的数据行数为:"+fileNum);
		}
		retMap.put("FILENUM", fileNum);
		return retMap;
	}
}
