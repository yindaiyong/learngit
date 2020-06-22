package com.sams.batchfile.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Strings;
import com.sams.batchfile.service.FileDataService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
/**
 * @ClassName:  FileDataUtil   
 * @Description:文件数据处理方法  
 * @author: yindy
 * @date:   2019年4月8日 上午11:16:38   
 *
 */
@Component
public class FileDataUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(FileDataUtil.class);
	
	
	/**
	 * 对含小数位的数据进行 补位操作
	 * @Title: exchangeRedixPoint   
	 * @author: yindy 2019年6月18日 下午4:03:34
	 * @param: @param value 传入的值
	 * @param: @param redixLength 小数位
	 * @param: @param length 长度
	 * @return: String      返回补齐位数后的长度
	 */
	public static String exchangeRedixPoint(String value ,int redixLength ,int length){
		if(!Strings.isNullOrEmpty(value)){
			if(value.contains(".")){
				String[] s = value.split("\\.");
				while(s[1].length() < redixLength){
					s[1] += "0";
				}
				//20200409  修正小数位大于规定位数的
				if(s[1].length() > redixLength ){
					s[1] = s[1].substring(0,redixLength);
				}
				value = s[0] + s[1];
			}else{
				//20200409  修正整数位大于规定位数的
				if(value.length() < length) {
					for(int l = 0 ; l < redixLength; l++ ){
						value += "0";
					}
				}
			}
			while (value.length() < length){
				value = "0" + value;
			}
			return value;
		}else{
			value = (value == null ? "" : value);
			value = getIntParameter(value,length);
		}
		return value;
	}
	
	/**
	 * 对普通数值进行前+0补位   
	 * @Title: getIntParameter   
	 * @author: yindy 2019年6月18日 下午4:06:54
	 * @param: @param value 传入值
	 * @param: @param length 要补到的长度
	 * @return: String    返回补齐长度后的值  
	 * @throws
	 */
	public static String getIntParameter(String value, int length) {
		while(value.length() < length){
			value = "0"+ value;
		}
		return value;
	}
	
	/**
	 * @author: yindy 2019年4月8日 上午11:21:26
	 * @Description: 对字符串数据进行补位处理 (编码GB18030)  
	 * @param:  value 传入得值
	 * @param: length 要补到的长度
	 * @return: String     返回补齐长度后的值   
	 */
	public static String getParameterCN(String value ,int length){
		try {
			if(value.getBytes(Const.GB_18030).length >length){
				char[] chars = value.toCharArray();
				StringBuffer sb = new StringBuffer();
				//解析每个字符
				for(char c :chars){
					String cVal = String.valueOf(c);
					//获取单个字符的字节长度
					int cValLength = cVal.getBytes(Const.GB_18030).length;
					if(length == 1){
						if(cValLength == 1){
							sb.append(c);
							length-=1;
							break;
						}else{
							sb.append(" ");
							length-=1;
							break;
						}
					}else{
						sb.append(c);
						length-=cValLength;
						if(length == 0){
							break;
						}
					}

				}
				value = sb.toString();
			}
			while(value.getBytes(Const.GB_18030).length < length){
				value = value + " ";
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * @author: yindy 2019年4月8日 上午11:22:25
	 * @Description: 对数值数据进行补位处理
	 * @param: value 传入的值
	 * @param: length 要补到的长度
	 * @return: String       返回补齐长度后的值   
	 */
	public static String getParameterNumCN(String value ,int length){
		try {
			while(value.getBytes(Const.GB_18030).length < length){
				value = "0"+ value;
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * @author: yindy 2019年4月8日 下午2:10:15
	 * @Description: 组装数据  
	 * @param:  topInfo 头部数据
	 * @param:  fileNameInfo 中间字段数据
	 * @param:  bottomInfo 尾部数据信息数据
	 * @return: String    返回组装完成后的数据  
	 */
	public static String getFileInfo(String topInfo, String fileNameInfo, String bottomInfo){
		StringBuilder info = new StringBuilder();
		return info
				.append(topInfo)
				.append(fileNameInfo)
				.append(bottomInfo)
				.toString();
	}
	/**
	 * 把接口信息字段的集合转成键值对象
	 * @Title: exchangeListToMap   
	 * @author: yindy 2019年4月12日 上午8:33:12
	 * @param: @param list 数据字段的集合
	 * @return: Map<String,InterfaceColInfo>   以字段名称为键值，该字段的信息为数据的键值对    
	 */
	public static Map<String,InterfaceColInfo> exchangeListToMap(List<InterfaceColInfo> list){
		Map<String,InterfaceColInfo> map = new HashMap<String, InterfaceColInfo>();
		for (InterfaceColInfo colInfo : list) {
			map.put(colInfo.getIcColName().toUpperCase(), colInfo);
		}
		return map;
	}
	
	/**
	 * @Title: writeFile   
	 * @author: yindy 2019年4月8日 上午11:23:27
	 * @Description: 按特殊编码GB18030写出文件  
	 * @param: @param file 需要写入的文件
	 * @param: @param value    写入文件的数据   
	 * @return: HashMap<String, Object>       返回写入成功或者失败的结果
	 */
	public static HashMap<String, Object> writeFile(File file, String value){
		OutputStreamWriter out = null ;
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		try {
			out = new OutputStreamWriter(new FileOutputStream(file), Const.GB_18030);
			out.write(value);
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("写入文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_FD0001));
			retMap.put("retMsg", "writeFile方法,写入文件异常,请联系管理员!");
			return retMap;
		}finally{
			if(out != null ){
				try {
					out.close();
				} catch (Exception e) {
					String error = FileDataUtil.getErrorMsg(e);
					LOGGER.error("写入文件错误"+error);
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_FD0001));
					retMap.put("retMsg", "writeFile方法,写入文件异常,请联系管理员!");
					return retMap;
				}
			}
		}
		retMap = (HashMap<String, Object>) ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		return retMap;
	}
	
	
	
	/**
	 * 传入map和键值获得value值   
	 * @Title: getMapValueByKey   
	 * @author: yindy 2019年6月18日 下午4:13:09
	 * @param: @param map 需要取值的map
	 * @param: @param key 需要取值的键
	 * @return: String   返回获取的值   
	 */
	public static String getMapValueByKey(Map<String, Object> map ,String key){
		return (map.get(key) == null ? "" : map.get(key).toString().trim());
	}
	
	/**
	 * @param fileDataService 
	 * 传入查询的数据集合和该文件对应的字段集合获得数据行字符串 
	 * @Title: buildDataLine   
	 * @author: yindy 2019年5月8日 下午4:01:21
	 * @param: @param retValue 传入的要写入文件的数据集
	 * @param: @param listColInfo 要写入文件的字段集合
	 * @return: Map<String,Object>  返回组装结果和组装完成的值    
	 * @throws
	 */
	public static  Map<String, Object> buildDataLine(List<Map<String, Object>> retValue,String type,String csdcVer,InterfaceColInfoMapper interfaceColInfoMapper, FileDataService fileDataService){
		Map<String, Object> retMap = new HashMap<String, Object>();
		StringBuilder dataLine = new StringBuilder();
		dataLine.append(FileDataUtil.getIntParameter(String.valueOf((retValue==null ? 0:retValue.size())),8)).append(Const.FILE_ENTER);
		if(retValue != null && retValue.size() >0){
			for (Map<String, Object> data : retValue) {
				String businessCode = (String)data.get("BUSINESSCODE");
				if(StringUtils.isEmpty(businessCode)){ //05 文件没有业务编码,默认给000,与字段配置表一致
					businessCode = "000";
				}
				String interfaceCode = getIntParameter(type,3)+businessCode+csdcVer;
				if(Const.FILE_TYPE_07.equals(type)){
					interfaceCode=Const.FILE_TYPE_07+csdcVer;
				}
				//如果业务编码不再字典中,取本地的
				String businessCodes = (String)fileDataService.getDictInfo().get("BUSINESSCODES");
				//LOGGER.info("配置的业务编码为:"+businessCodes);
				if(!businessCodes.contains(businessCode)){
					interfaceCode = Const.fileTypeMap.get(type+csdcVer);
				}
				//设置要查询的接口字段的键值
				retMap.put("ICINTERFACECODE",interfaceCode.replace("R", "9"));
				List<InterfaceColInfo> listColInfo = interfaceColInfoMapper.selectByInterfaceCodeList(retMap);
				for (InterfaceColInfo colInfo : listColInfo) {
					String value = FileDataUtil.getMapValueByKey(data,colInfo.getIcColName().toUpperCase());
					//字段长度
					Integer colLength = Integer.parseInt(colInfo.getIcColLength());
					//字段小数位
					String colDecimal = colInfo.getIcColDecimal();
					//字段的默认值
					String defaultValue = colInfo.getIcColValue(); 
					Integer decimalLen = 0;
					if(!StringUtils.isEmpty(colDecimal)){
						decimalLen = Integer.parseInt(colDecimal);
					}
					//如果字段为空值，给它默认值
					if(StringUtils.isEmpty(value) && !StringUtils.isEmpty(defaultValue)){
						value = defaultValue;
					}
					//数据类型
					String colType = colInfo.getIcColType();
					//LOGGER.info(colInfo.getIcColName()+"字段的值:"+value+",长度:"+colLength+",小数位:"+decimalLen+",字段类型:"+colType);
					if(decimalLen != 0 ){
						value = FileDataUtil.exchangeRedixPoint(value, decimalLen, colLength);
					}else{
						if((/*Const.FILED_TYPE_A.equals(colType) || */Const.FILED_TYPE_N.equals(colType))){
							value = FileDataUtil.getParameterNumCN(value, colLength);
						}else{
							value = FileDataUtil.getParameterCN(value, colLength);
						}
					}
					try {
						retMap = CheckFileUtil.checkfileInterfaceField(value,colInfo.getIcColName(),colInfo);
					} catch (Exception e) {
						String error = getErrorMsg(e);
						LOGGER.warn("组装文件时校验错误"+error);
						retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
						retMap.put("retMsg", "buildDataLine方法,生成文件异常,请联系管理员!");
						return retMap;
					}
					if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {
						if(!ObjectUtils.isEmpty(retMap.get("RETVALUE"))){retMap.put("retMsg", retMap.get("RETVALUE"));}
						return retMap;
					}
					//LOGGER.info("字段为:"+colInfo.getIcColName()+"字段值为:"+value);
					dataLine.append(value);
				}
				dataLine.append(Const.FILE_ENTER);
			}
		}
		//LOGGER.info("所有的数据行为:"+dataLine);
		dataLine.append(Const.FILE_OFDCFEND);
		retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		retMap.put("retValue", dataLine);
		return retMap;
	}
	
	/**
	 * 获得行情分页结果集   
	 * @Title: getPage   
	 * @author: yindy 2019年6月18日 下午4:16:24
	 * @param: @param pageInfo 分页对象
	 * @param: @param list 需要分页的集合对象
	 * @return: List<Object>  返回分页过后的pageinfo对象    
	 */
	public static List<Object> getPage(PageInfo pageInfo,List list) {
		List<Object> returnList = new ArrayList<Object>();
		int from  = pageInfo.getFrom();
		int to = pageInfo.getNowpage()*pageInfo.getPagesize();
		if(to >= list.size()){
			to = list.size()-1;
		}else{
			to -= 1;
		}
		for(int i = from ; i <= to ; i++ ){
			returnList.add(list.get(i));
		}
		return returnList;
	}
	
	/**
	 * 获得异常信息   
	 * @Title: getErrorMsg   
	 * @author: yindy 2019年6月13日 上午9:02:45
	 * @param: @param e 异常对象
	 * @return: String      返回异常信息字符串
	 */
	public static String getErrorMsg(Exception e) {
		StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}
	
	/**
	 * 给BigDecimal赋值   
	 * @Title: changeBigDecialValue   
	 * @author: yindy 2019年6月18日 下午4:20:26
	 * @param: @param data 传入的数据集
	 * @return: Map<String,Object>       转换之后的数据集
	 */
	public static Map<String, Object> changeBigDecialValue(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if(Const.BIG_DECIMAL_FIELD.contains(key)){ 
				String value = MapUtils.getString(data, key);
				BigDecimal realValue = new BigDecimal(0);
				if(value != null && !StringUtils.isEmpty(value)){
					realValue = new BigDecimal(value);
				}
				data.put(key, realValue);
			}
		}
		return data;
	}
	
	/**
	 * 获得从数据库读取的要生成的文件类型   
	 * @Title: getReadFile   
	 * @author: yindy 2019年6月18日 下午1:47:20
	 * @param:  channelInfo 渠道信息
	 * @param:  list 装载文件类型list
	 * @return: List<String> 装载文件类型list   
	 */
	 public static List<String> getReadFile(PchannelInfo channelInfo, List<String> list) {
		String dealfile = channelInfo.getCiDealFile();
		String volDetail = channelInfo.getCiVolDetailType(); //26文件
		String[] arr = dealfile.split(",");
		for (String a : arr) {
			list.add(a);
		}
		list.add(Const.FILE_TYPE_05);
		list.add(Const.FILE_TYPE_06);
		if(!StringUtils.isEmpty(volDetail)){
			list.add(Const.FILE_TYPE_26);
		}
		return list;
	}

	/**
	 * 获得勾选的要生成的文件类型   
	 * @Title: getPageCheckFile   
	 * @author: yindy 2019年6月18日 下午1:39:09
	 * @param: fileTypes 文件类型
	 * @return: List<String> 装载文件类型list    
	 */
	public static  List<String> getPageCheckFile(String fileTypes) {
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isEmpty(fileTypes)){
			list = Arrays.asList(fileTypes.split(","));
		}
		return list;
	}
}
