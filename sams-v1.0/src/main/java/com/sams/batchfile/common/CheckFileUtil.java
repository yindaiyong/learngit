package com.sams.batchfile.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
import com.trust.util.StringUtil;
/**
 * @ClassName:  CheckFileUtil   
 * @Description: 校验文件接口类
 * @author: yindy
 * @date:   2019年4月8日 上午9:19:01   
 *
 */

public class CheckFileUtil {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CheckFileUtil.class);
	
	/**
	 * 校验字段是否必填   
	 * @Title: checkFieldRequire   
	 * @author: yindy 2019年6月18日 下午3:47:21
	 * @param: @param fieldValue 字段的值
	 * @param: @param filedName 字段的名称
	 * @param: @param required 是否必填
	 * @return: HashMap<String,Object>    返回校验通过还是具体错误 
	 */
	public static Map<String, Object> checkFieldRequire(String fieldValue,String filedName, String required){
		Map<String, Object> retMap = new HashMap<String, Object>();
		if("1".equals(required) && (StringUtils.isEmpty(fieldValue)) ){
			String retMsg = ExceptionConStants.retMsg_FI0003;
			LOGGER.info(filedName+retMsg);
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0003,filedName+retMsg);
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		}
		return retMap;
	}
	
	/**
	 * 检验字段的长度 
	 * @Title: checkFieldLength   
	 * @author: yindy 2019年6月18日 下午3:49:39
	 * @param: @param fieldValue 字段的值
	 * @param: @param filedName 字段的名称
	 * @param: @param length 字段的长度
	 * @return: HashMap<String,Object>    返回校验通过还是具体错误    
	 * @throws Exception 转换类型错误
	 */
	public static Map<String, Object> checkFieldLength(String fieldValue,String filedName, Integer length) throws Exception{
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(fieldValue) || fieldValue.getBytes(Const.GB_18030).length != length.intValue()){
			String retMsg = ExceptionConStants.retMsg_FI0004;
			LOGGER.info(filedName+retMsg+"实际长度为："+fieldValue.getBytes(Const.GB_18030).length+"规定长度为："+length);
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0004,filedName+retMsg+",实际长度为:"+fieldValue.getBytes(Const.GB_18030).length+",规定长度为:"+length+"。");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		}
		return retMap;
	}
	
	/**
	 * 校验字段规则   
	 * @Title: checkFieldformat   
	 * @author: yindy 2019年6月18日 下午3:52:40
	 * @param: @param fieldValue 字段值
	 * @param: @param filedName 字段名称
	 * @param: @param format 字段的规则
	 * @return: HashMap<String,Object>   返回校验通过还是具体错误       
	 */
	public static Map<String, Object> checkFieldformat (String fieldValue,String filedName, String format){
		Map<String, Object> retMap = new HashMap<String, Object>();
		String retMsg = ExceptionConStants.retMsg_FI0005;
		if(StringUtils.isEmpty(fieldValue) || StringUtils.isEmpty(format)){
			LOGGER.info(filedName+"字段的值为空/或者规则为空");
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			return retMap;
		}
		try {
			Pattern pattern = Pattern.compile(format.trim());
			Matcher matcher = pattern.matcher(fieldValue.trim());
			if(!matcher.matches()){
				LOGGER.info(filedName+retMsg+",字段值为："+fieldValue+",字段规则为："+format);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0005, filedName+retMsg+",字段值为:"+fieldValue+",字段的规则为:"+format);
			}else{
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("正则表达式解析异常"+error);
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0010,filedName+"的正则表达式:"+format.trim()+"错误!");
			return retMap;
		}
		return retMap;
	}
	
	/**
	 * 校验字段的各项规则   
	 * @Title: checkfileInterfaceField   
	 * @author: yindy 2019年6月18日 下午3:54:18
	 * @param: @param fieldValue 字段的值
	 * @param: @param filedName 字段名称
	 * @param: @param interfaceColInfo 接口字段信息
	 * @return: HashMap<String,Object>     返回校验通过还是具体错误        
	 */
	public static Map<String, Object> checkfileInterfaceField(String fieldValue, String filedName,InterfaceColInfo interfaceColInfo) throws Exception{
		Map<String, Object> result = Maps.newHashMap() ;
		//校验该字段的长度
		result = checkFieldLength(fieldValue,filedName, Integer.valueOf(interfaceColInfo.getIcColLength()));
		if(!(ExceptionConStants.retCode_0000).equals(result.get("retCode"))) {
			return result;
		}

		//校验必填
		if(("01").equals(interfaceColInfo.getIcRequOnOff())){
			result = checkFieldRequire(fieldValue, filedName,interfaceColInfo.getIcColRequ());
			if(!(ExceptionConStants.retCode_0000).equals(result.get("retCode"))) {
				return result;
			}
		}
		
		if(("01").equals(interfaceColInfo.getIcRuleOnOff())){
			//校验字段类型
			result = checkFieldType(fieldValue, filedName, interfaceColInfo.getIcColType());
			if(!(ExceptionConStants.retCode_0000).equals(result.get("retCode"))) {
				return result;
			}
			
			//校验字段的数据格式  
			result = checkFieldformat(fieldValue,filedName, interfaceColInfo.getIcColRule());
			if(!(ExceptionConStants.retCode_0000).equals(result.get("retCode"))) {
				return result;
			}
			
			//校验数据字典
			result = checkDict(fieldValue, filedName, interfaceColInfo.getIcColDict());
			if(!(ExceptionConStants.retCode_0000).equals(result.get("retCode"))) {
				return result;
			}
		}
		result = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		return result;
	}
	
	/**
	 * 校验该字段在不在字典中  
	 * @Title: checkDict   
	 * @author: yindy 2019年6月18日 下午3:56:46
	 * @param: @param fieldValue 字段值
	 * @param: @param filedName 字段名称
	 * @param: @param colDict 该字段对应的字典值
	 * @return: Map<String,Object>      返回校验通过还是具体错误 
	 */
	public static Map<String, Object> checkDict(String fieldValue, String filedName,String colDict){
		Map<String, Object> retMap = new HashMap<String, Object>();
		String retMsg = "该字段数据不在设定内容中";
		if(!StringUtil.isEmpty(colDict)){
			if(!colDict.contains(fieldValue)){
				LOGGER.info(filedName+retMsg+"设定内容为:"+colDict+",实际值为："+fieldValue);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_FI0006,filedName+retMsg+",设定内容为:"+colDict+",实际值为:"+fieldValue+"。");
				return retMap;
			}
		}
		retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		return retMap;
	}

	/**
	 * 校验用户信息的四个电话号码 
	 * @Title: checkPhoneNo   
	 * @author: yindy 2019年4月24日 上午9:26:22
	 * @param: @param tempAccount 用户的申请数据Map
	 * @return: Map<String,Object>   返回校验通过还是具体错误     
	 */
	public static  Map<String, Object> checkPhoneNo(Map<String, Object> tempAccount) {
		LOGGER.info("需要校验的用户信息为:"+tempAccount);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		//获得四个电话号码
		String homeTelNo = FileDataUtil.getMapValueByKey(tempAccount, "HOMETELNO");
		String mobileTelno = FileDataUtil.getMapValueByKey(tempAccount, "MOBILETELNO");
		String officeTelNo = FileDataUtil.getMapValueByKey(tempAccount, "OFFICETELNO");
		String telNo = FileDataUtil.getMapValueByKey(tempAccount, "TELNO");
		if(StringUtils.isEmpty(homeTelNo) && StringUtils.isEmpty(mobileTelno) &&
			StringUtils.isEmpty(officeTelNo) && StringUtils.isEmpty(telNo)){
			LOGGER.info("校验用户信息的四个电话号码都为空！");
			retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_FI0007,"HOMETELNO,MOBILETELNO,OFFICETELNO,TELNO,不可都为空!");
		}
		return retMap;
	}
	
	/**
	 * 校验开户时证件的有效期
	 * @Title: checkCertValidDate   
	 * @author: yindy 2019年10月21日 下午3:46:57
	 * @param: @param tempAccount
	 * @param: @return      
	 * @return: java.util.Map<String,Object>      
	 * @throws
	 */
	public static java.util.Map<String, Object> checkCertValidDate(
			Map<String, Object> tempAccount) {
		LOGGER.info("需要校验的用户信息为:"+tempAccount);
		Map<String, Object> retMap = new HashMap<String, Object>();
		String certValidDate = MapUtils.getString(tempAccount, "CERTVALIDDATE", "");
		String businessDate = MapUtils.getString(tempAccount, "BUSINESSDATE", "");
		if(!StringUtils.isEmpty(certValidDate) && !StringUtils.isEmpty(businessDate)){
			//校验
			if(certValidDate.compareTo(businessDate) >= 0){
				//证件有效期有效
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("证件有效日期已过期。。");
				retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_9999,"开户证件有效期为:"+certValidDate+",该证件已过期。");
				//证件有效期无效
			}
		}else{
			LOGGER.info("证件有效期为空。。");
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		}
		return retMap;
	}
	
	public static String  transNumberValue(String fieldValue, String decimalLen,int fieldLen){
		if(fieldValue.equals("")){
			return fieldValue;
		}
		int len=Integer.valueOf(decimalLen);
		String value=fieldValue.substring(0,fieldLen-len)+"."+fieldValue.substring(fieldLen-len,fieldValue.length());
		return value;
	}

	/**
	 * 校验字段类型 
	 * @Title: checkFieldType   
	 * @author: yindy 2019年7月11日 下午1:14:05
	 * @param: @param value
	 * @param: @param key
	 * @param: @param type
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public static Map<String, Object> checkFieldType(String value, String filedName,
			String type) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(value) || StringUtils.isEmpty(type) ){
			LOGGER.info(filedName+"字段的值为空/或者规则为空");
			retMap = (HashMap<String, Object>) ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			return retMap;
		}
		if("A".equals(type) || "N".equals(type)){ //数字字符，限于0-9; 数值型 ,其长度不包含小数点，可参与数值计算
			retMap = checkFieldformat(value, filedName, "[0-9]*");
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
		}
		retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		return retMap;
	}
	
	/**
	 * 校验勾选证件类型   
	 * @Title: checkCertificateType   
	 * @author: yindy 2019年11月12日 下午5:16:53
	 * @param: @param tempAccount
	 * @param: @param channelInfo
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public static Map<String, Object> checkCertificateType(
			Map<String, Object> tempAccount, PchannelInfo channelInfo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		String IndividualOrInstitution = MapUtils.getString(tempAccount, "INDIVIDUALORINSTITUTION", "0"); //1 个人 0  机构类型
		String certificateType = MapUtils.getString(tempAccount, "CERTIFICATETYPE", "0"); //证件类型
		String setPerCertificateType = channelInfo.getCiPerAcctType(); //设置的个人证件类型
		String setOrgCertificateType = channelInfo.getCiOrgAcctType(); //设置的机构证件类型
		if(Const.INDIVIDUAL.equals(IndividualOrInstitution)){ //个人
			if(setPerCertificateType == null || !setPerCertificateType.contains(certificateType)){
				LOGGER.info("不支持"+certificateType+"个人证件类型开户！");
				retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_A00022,
						ExceptionConStants.retMsg_A00022+",支持的类型为:"+setPerCertificateType+",实际类型为:"+certificateType+"。");
			}
		}else{
			if(setOrgCertificateType == null || !setOrgCertificateType.contains(certificateType)){
				LOGGER.info("不支持"+certificateType+"机构证件类型开户！");
				retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_A00023,
						ExceptionConStants.retMsg_A00023+",支持的类型为:"+setOrgCertificateType+",实际类型为:"+certificateType+"。");
			}
		}
		return retMap;
	}
	
	/**
	 * 对身份证进行校验  
	 * @Title: checkCertificateNo   
	 * @author: yindy 2019年11月29日 下午1:44:07
	 * @param: @param tempAccount
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private static Map<String, Object> checkCertificateNo(
			Map<String, Object> tempAccount) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		String IndividualOrInstitution = MapUtils.getString(tempAccount, "INDIVIDUALORINSTITUTION", "0");//1 个人 0  机构类型
		String certificateNo = MapUtils.getString(tempAccount, "CERTIFICATENO","").trim();
		String certificateType = MapUtils.getString(tempAccount, "CERTIFICATETYPE", ""); //证件类型
		if(Const.INDIVIDUAL.equals(IndividualOrInstitution) && "0".equals(certificateType)){ //身份证开户
			//校验身份证
			if (certificateNo.length() != 18 && certificateNo.length() != 15 ) {
				LOGGER.info(certificateNo+"该身份证号码长度不正确！");
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00024));
				return retMap;
	        }
			if(certificateNo.length() == 18){
				String identity_pattern = "^[0-9]{17}[0-9Xx]$";
				if (!certificateNo.matches(identity_pattern)) {
					LOGGER.info(certificateNo+"该身份证号码格式不正确！");
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00025));
			        return retMap;
		        }
			}
		}
		return retMap;
	}
	
	
	/**
	 * @param noCheckPhoneChannel 
	 * 特殊校验处理   
	 * @Title: specialCheckHandel   
	 * @author: yindy 2019年5月28日 下午1:09:04
	 * @param: @param tempAccount 用户数据
	 * @return: Map<String,Object>      校验结果 
	 * @throws
	 */
	public static Map<String, Object> specialCheckHandel(
			Map<String, Object> tempAccount,PchannelInfo channelInfo, String noCheckPhoneChannel) {
		String businessCode = MapUtils.getString(tempAccount, "BUSINESSCODE", Const.BUSINESS_003); //开户
		String channelCode = channelInfo.getCiChannelCode();
		if(Const.BUSINESS_001.equals(businessCode)){
		
			//20200426 添加前置条件过滤不需要校验电话号码的渠道
			if(StringUtils.isEmpty(noCheckPhoneChannel) || !noCheckPhoneChannel.contains(channelCode)){
				//校验电话必须填写一个
				Map<String, Object> phoneCheckMap = CheckFileUtil.checkPhoneNo(tempAccount);
				LOGGER.info("校验四个电话必填一个的结果为:"+phoneCheckMap);
				if(!ExceptionConStants.retCode_0000.equals(phoneCheckMap.get("retCode"))){
					tempAccount.put("retCode", MapUtils.getString(tempAccount, "retCode", "")+MapUtils.getString(phoneCheckMap, "retCode", ""));
					tempAccount.put("retMsg", MapUtils.getString(tempAccount, "retMsg", "")+MapUtils.getString(phoneCheckMap, "retMsg", ""));
					tempAccount.put("ERRORDEC", MapUtils.getString(tempAccount, "ERRORDEC", "")+MapUtils.getString(phoneCheckMap, "retMsg", ""));
				}
			}
			//校验开户时证件有效期大于开户日期     --CERTVALIDDATE 证件有效期
//			Map<String, Object> certValidCheckMap = CheckFileUtil.checkCertValidDate(tempAccount);
//			LOGGER.info("校验开户证件有效期的结果为:"+certValidCheckMap);
//			if(!ExceptionConStants.retCode_0000.equals(certValidCheckMap.get("retCode"))){
//				tempAccount.put("retCode", MapUtils.getString(tempAccount, "retCode", "")+MapUtils.getString(certValidCheckMap, "retCode", ""));
//				tempAccount.put("retMsg", MapUtils.getString(tempAccount, "retMsg", "")+MapUtils.getString(certValidCheckMap, "retMsg", ""));
//				tempAccount.put("ERRORDEC", MapUtils.getString(tempAccount, "ERRORDEC", "")+MapUtils.getString(certValidCheckMap, "retMsg", ""));
//			}
			
			//个人开户类型为身份证时，对身份证进行校验
			Map<String, Object> certificateNoCheckMap = CheckFileUtil.checkCertificateNo(tempAccount);
			LOGGER.info("校验个人身份证开户结果为:"+certificateNoCheckMap);
			if(!ExceptionConStants.retCode_0000.equals(certificateNoCheckMap.get("retCode"))){
				tempAccount.put("retCode", MapUtils.getString(tempAccount, "retCode", "")+MapUtils.getString(certificateNoCheckMap, "retCode", ""));
				tempAccount.put("retMsg", MapUtils.getString(tempAccount, "retMsg", "")+MapUtils.getString(certificateNoCheckMap, "retMsg", ""));
				tempAccount.put("ERRORDEC", MapUtils.getString(tempAccount, "ERRORDEC", "")+MapUtils.getString(certificateNoCheckMap, "retMsg", ""));
			}
			
			//校验发送的证件类型在勾选的证件类型中
			Map<String, Object> certificateTypeMap = checkCertificateType(tempAccount, channelInfo);
			LOGGER.info("校验勾选的证件类型结果为:"+certificateTypeMap);
			if(!ExceptionConStants.retCode_0000.equals(certificateTypeMap.get("retCode"))){
				tempAccount.put("retCode", MapUtils.getString(tempAccount, "retCode", "")+MapUtils.getString(certificateTypeMap, "retCode", ""));
				tempAccount.put("retMsg", MapUtils.getString(tempAccount, "retMsg", "")+MapUtils.getString(certificateTypeMap, "retMsg", ""));
				tempAccount.put("ERRORDEC", MapUtils.getString(tempAccount, "ERRORDEC", "")+MapUtils.getString(certificateTypeMap, "retMsg", ""));
			}
		}
		return tempAccount;
	}

}
