package com.sams.wsdl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.sams.batchfile.common.FileDataUtil;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.custom.mapper.AccountStatMapper;
import com.sams.custom.model.AccountStat;
import com.sams.sys.service.SysDictService;
import com.sams.wsdl.S100031.CommonRqHdr ;
import com.sams.wsdl.S100031.CommonXmlRq;
import com.sams.wsdl.S100031.CommonXmlRs;
import com.sams.wsdl.S100031.S100031;
import com.sams.wsdl.S100031.S100031Type;
import com.sams.wsdl.S100031.S100031TypeResponse;
import com.sams.wsdl.S100031.S100031_Service;
import com.trust.util.StringUtil;
/**
 * @ClassName:  CallS100031   
 * @Description: 用户修改资料
 * @author: yindy
 * @date:   2019年4月30日 下午4:56:44   
 *
 */
//8322
public class CallS100031 {

	private static Logger LOGGER = LoggerFactory.getLogger(CallS100031.class);
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private AccountStatMapper accountStatMapper;
	
	/**
	 * 修改用户信息   
	 * @Title: callS100031   
	 * @author: yindy 2019年5月6日 上午10:19:21
	 * @param: @param map 用户信息
	 * @param: @return      
	 * @return: Map<String,Object>       
	 * @throws
	 */
	public Map<String,Object> callS100031(Map<String,Object> map){
		//查询映射关系
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		Map<String, Object> sexMap = sysDictService.findSaleToTaMapper(Const.SEX);
		Map<String, Object> grMap = sysDictService.findSaleToTaMapper(Const.GRIDENTITYTYPE);
		Map<String, Object> zzMap = sysDictService.findSaleToTaMapper(Const.ZZIDENTITYTYPE);
		
		LOGGER.info("调用开户接口的用户信息"+map);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//报文头字段
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		String transactionDate = FileDataUtil.getMapValueByKey(map, "TRANSACTIONDATE");
		String transactionTime = FileDataUtil.getMapValueByKey(map, "TRANSACTIONTIME")+"000";
		String channelCode = FileDataUtil.getMapValueByKey(map, "CHANNELCODE");
		String transCode = Const.TransCode_100031;
		String version = Const.Version;
		
		//设置报文头的值
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(transCode);
		commonRqHdr.setTransDate(transactionDate);
		commonRqHdr.setTransTime(transactionTime);
		commonRqHdr.setVersion(version);
		
		//公共报文体字段
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		String custName = FileDataUtil.getMapValueByKey(map, "INVESTORNAME"); //客户姓名
		String individualOrInstitution = FileDataUtil.getMapValueByKey(map, "INDIVIDUALORINSTITUTION"); //客户类型
		String certificateType = "";
		if("0".equals(individualOrInstitution)){
			certificateType = (String)zzMap.get(FileDataUtil.getMapValueByKey(map, "CERTIFICATETYPE"));//证件类型
		}else{
			certificateType = (String)grMap.get(FileDataUtil.getMapValueByKey(map, "CERTIFICATETYPE"));//证件类型
		}
		String identityNo = FileDataUtil.getMapValueByKey(map, "CERTIFICATENO");//证件号
		//String mobiletelno = FileDataUtil.getMapValueByKey(map, "MOBILETELNO");
		String validEnddate = FileDataUtil.getMapValueByKey(map, "CERTVALIDDATE");//证件有效开始时间
		String password = Const.ENCRYPT_PASSWORD;//密码
		String clientriskrate = FileDataUtil.getMapValueByKey(map, "CLIENTRISKRATE");//客户风险等级
		String managerno = FileDataUtil.getMapValueByKey(map, "MANAGERNO");//客户经理编码
		String flag = (map.get("FLAG") == null ? null : (String)map.get("FLAG")); //开户   修改资料     标志 1 修改资料 ,其他 开户
		String sex = (map.get("SEX") == null ? "1" : (String)sexMap.get((String)map.get("SEX"))); //性别
		String fundAcco = ""; //基金账号
		
		//create by yindy 20191015
		String address = FileDataUtil.getMapValueByKey(map, "ADDRESS");//地址
		String postCode = FileDataUtil.getMapValueByKey(map, "POSTCODE");//邮编
		String homeNo = FileDataUtil.getMapValueByKey(map, "HOMETELNO");//电话
		String officeNo = FileDataUtil.getMapValueByKey(map, "OFFICETELNO");
		String mobileTelNo = FileDataUtil.getMapValueByKey(map, "MOBILETELNO");
		String telNo = FileDataUtil.getMapValueByKey(map, "TELNO");
		String telephone = "";
		if(!StringUtils.isEmpty(homeNo)){
			telephone = homeNo;
		}else if(!StringUtils.isEmpty(officeNo)){
			telephone = officeNo;
		}else if(!StringUtils.isEmpty(mobileTelNo)){
			telephone = mobileTelNo;
		}else if(!StringUtils.isEmpty(telNo)){
			telephone = telNo;
		}
		//end 
		
		//修改资料字段
		commonXmlRq = updateAccoDateField(commonXmlRq,map);
		//设置报文体字段
		commonXmlRq.setAddress(address); //地址
		commonXmlRq.setZipCode(postCode);//邮编
		commonXmlRq.setPhoneNo(telephone); //电话号码
		commonXmlRq.setCustName(custName);
		commonXmlRq.setIdentityType(certificateType);
		commonXmlRq.setIdentityNo(identityNo);
		commonXmlRq.setIdValidEndDate(validEnddate);
		commonXmlRq.setBindMobile(telephone);
		commonXmlRq.setCustType(individualOrInstitution);
		commonXmlRq.setPassword(password);
		commonXmlRq.setRiskLevel(clientriskrate);
		commonXmlRq.setBrokerCode(managerno);
		commonXmlRq.setFlag(flag);
		commonXmlRq.setSex(sex);
		
		//机构特殊化处理
		commonXmlRq = instSpecialHandel(commonXmlRq,map);
		//开户
		S100031_Service s100031 = new S100031_Service();
		S100031 s =s100031.getS100031SOAP();
		S100031Type common = new S100031Type();
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		
		LOGGER.info("调用S100031接口发送的报文头数据:"+JSONObject.fromObject(commonRqHdr).toString());
		LOGGER.info("调用S100031接口发送的报文体数据:"+JSONObject.fromObject(commonXmlRq).toString());
		long start = new Date().getTime();
		S100031TypeResponse response = s.newOperation(common);
		LOGGER.info("调用接口S100031耗时:"+(new Date().getTime() - start)+"ms");
		CommonXmlRs xmlRespone = response.getCommonXmlRs();
		String retCode = xmlRespone.getRetCode();
		String retMsg = xmlRespone.getRetMsg();
		if(ExceptionConStants.retCode_0000.equals(retCode)){
			fundAcco =  xmlRespone.getFundAcco();
		}
		resultMap.put("retCode", retCode);
		resultMap.put("TAACCOUNTID", fundAcco);
		resultMap.put("retMsg", retMsg);
		LOGGER.info("调用S100031接口返回结果:"+resultMap);
		return resultMap;
	}

	/**
	 * 修改资料特殊字段  
	 * @param commonXmlRq 
	 * @Title: updateAccoDateField   
	 * @author: yindy 2019年6月20日 上午8:34:00
	 * @param: @param map 数据
	 * @return: CommonXmlRq    对象   
	 * @throws
	 */
	private CommonXmlRq updateAccoDateField(CommonXmlRq commonXmlRq, Map<String, Object> map) {
		if("1".equals(map.get("FLAG"))){
			String fundAcco = FileDataUtil.getMapValueByKey(map, "TAACCOUNTID");
			if(StringUtil.isEmpty(fundAcco)){
				accountStatMapper = (AccountStatMapper) SpringUtils.getBean(AccountStatMapper.class);
				//根据四要素查询本地用户状态
				AccountStat accountStat = accountStatMapper.selectAccountStatus(map);
				if(accountStat != null){
					fundAcco = accountStat.getAsTaAccountId();
				}
			}
			commonXmlRq.setFundAcco(fundAcco); // 理财账号
		}
		return commonXmlRq;
	}

	/**
	 * 机构报文体的特殊处理  
	 * @Title: instSpecialHandel   
	 * @author: yindy 2019年5月6日 上午9:25:29
	 * @param: @param commonXmlRq
	 * @param: @param map
	 * @param: @return      
	 * @return: CommonXmlRq      
	 * @throws
	 */
	private CommonXmlRq instSpecialHandel(CommonXmlRq commonXmlRq, Map<String, Object> map) {
		String individualOrInstitution = FileDataUtil.getMapValueByKey(map, "INDIVIDUALORINSTITUTION"); //客户类型
		if("0".equals(individualOrInstitution)){
			//机构的
			String InstiRegAddr = FileDataUtil.getMapValueByKey(map, "VOCATION");
			String instreprname = FileDataUtil.getMapValueByKey(map, "INSTREPRNAME");
			String instrepridtype = FileDataUtil.getMapValueByKey(map, "INSTREPRIDTYPE");
			String instrepridcode = FileDataUtil.getMapValueByKey(map, "INSTREPRIDCODE");
			String instreprcertvaliddate = FileDataUtil.getMapValueByKey(map, "INSTREPRCERTVALIDDATE");
			String transactorname = FileDataUtil.getMapValueByKey(map, "TRANSACTORNAME");
			String transactorcerttype = FileDataUtil.getMapValueByKey(map, "TRANSACTORCERTTYPE");
			String transactorcertno  = FileDataUtil.getMapValueByKey(map, "TRANSACTORCERTNO");
			String insttrancertvaliddate = FileDataUtil.getMapValueByKey(map, "INSTTRANCERTVALIDDATE");
			
			String province = FileDataUtil.getMapValueByKey(map, "PROVINCE");
			String city = FileDataUtil.getMapValueByKey(map, "CITY");
			//设置报文体的值
			commonXmlRq.setRegCustType("");
			commonXmlRq.setIndustryDetail("");
			commonXmlRq.setInstiRegAddr(InstiRegAddr);
			commonXmlRq.setIndustryIdentityNo("");
			commonXmlRq.setTaxIdentityNo("");
			commonXmlRq.setLawName(instreprname);
			commonXmlRq.setLawIdentityType(instrepridtype);
			commonXmlRq.setLawIdentityNo(instrepridcode);
			commonXmlRq.setLawIdValidBegDate("");
			commonXmlRq.setLawIdValidEndDate(instreprcertvaliddate);
			commonXmlRq.setContact(transactorname);
			commonXmlRq.setConIdentityType(transactorcerttype);
			commonXmlRq.setConNo(transactorcertno);
			commonXmlRq.setConIdValiddate(insttrancertvaliddate);
			
			commonXmlRq.setProvinceCode(province);
			commonXmlRq.setCityNo(city);
		}
		return commonXmlRq;
	}
	
}
