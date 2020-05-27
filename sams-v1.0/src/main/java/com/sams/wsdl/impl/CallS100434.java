package com.sams.wsdl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sams.common.constant.Const;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.StringUtils;
import com.sams.custom.mapper.AccountReqCfmMapper;
import com.sams.wsdl.S100434.CommonRqHdr;
import com.sams.wsdl.S100434.CommonXmlRq;
import com.sams.wsdl.S100434.CommonXmlRs;
import com.sams.wsdl.S100434.S100434;
import com.sams.wsdl.S100434.S100434Type;
import com.sams.wsdl.S100434.S100434TypeResponse;
import com.sams.wsdl.S100434.S100434_Service;
//8403 
public class CallS100434 {
	private static Logger LOGGER = LoggerFactory.getLogger(CallS100434.class);
	
	public Map<String,Object> callS100434(Map<String,Object> tradeMap){
		LOGGER.info("s100434赎回交易发送：");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String tradeDate = StringUtils.getStringValue((String)tradeMap.get("TRANSACTIONDATE"));//yyyyMMdd 交易发生日期
		String tradeTime = StringUtils.getStringValue((String)tradeMap.get("TRANSACTIONTIME"));//hhmmss 交易发生时间
		String channelCode = StringUtils.getStringValue((String)tradeMap.get("CHANNELCODE"));//渠道号
		String inContract = StringUtils.getStringValue((String)tradeMap.get("INCONTRACT"));//内部合同编号
		String outContract = StringUtils.getStringValue((String)tradeMap.get("OUTCONTRACT"));//外部合同编号
		String taAccountId = StringUtils.getStringValue((String)tradeMap.get("TRANSACTIONACCOUNTID"));//理财账号
		String applicationvol = MapUtils.getString(tradeMap, "APPLICATIONVOL", "0");//赎回份额
		S100434_Service s100434 = new S100434_Service();
		S100434 s = s100434.getS100434SOAP();
		S100434Type common = new S100434Type();
		//请求报文头
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(Const.TransCode_100434);
		commonRqHdr.setTransDate(tradeDate);
		commonRqHdr.setTransTime(tradeTime+"000");
		commonRqHdr.setVersion(Const.Version);
		//报文体
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		commonXmlRq.setActflag("");//操作类型
		commonXmlRq.setFundcode(StringUtils.getStringValue((String)tradeMap.get("TAPRODUCTCODE")));//产品代码
		commonXmlRq.setTrustcontractid(outContract);//外部合同编号
		AccountReqCfmMapper accountReqCfmMapper=(AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
		Map<String, Object> retAccoMap=accountReqCfmMapper.selectByTAAccountId(taAccountId,channelCode);
		
		commonXmlRq.setCustname(StringUtils.getStringValue((String)retAccoMap.get("INVESTORNAME")));//客户名称
		commonXmlRq.setRedeemtype("0");//赎回方式	0:份额赎回;1:金额赎回;2:收益赎回;3:手工录入赎回;4:份额+金额赎回;5:收益率赎回
		commonXmlRq.setExceedflag("");//巨额赎回处理方式  
		commonXmlRq.setRedeemshares(applicationvol);
		//from by luhua 20200123  
		//发送上送的赎回申请中赎回费用类型（faretype）和业绩报酬类型（deductfaretype）都上送了“不收费”（1），请予将默认上送选择设置为“按产品费率设置”（0）。
		commonXmlRq.setFaretype(Const.FARE_TYPE_0);//费用类型  
		commonXmlRq.setDeductfaretype(Const.DEDUCT_FARE_TYPE_0);//业绩报酬选项 
		commonXmlRq.setRequestdate(tradeDate.substring(0,4)+"-"+tradeDate.substring(4,6)+"-"+tradeDate.substring(6,8));
		commonXmlRq.setContractserialno(inContract);//内部合同编号
		commonXmlRq.setOutrequestno(StringUtils.getStringValue((String)tradeMap.get("TASERIALNO")));//预约编号
		commonXmlRq.setOutsystemflag("F");//来源外部系统标识
		
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		//返回报文
		LOGGER.info("s100434接口发送请求的报文头数据：" + JSONObject.fromObject(commonRqHdr).toString());
		LOGGER.info("s100434接口发送请求的报文体数据：" + JSONObject.fromObject(commonXmlRq).toString());
		long a = new Date().getTime();
		S100434TypeResponse response = s.s100434(common);
		CommonXmlRs commonXmlRs = response.getCommonXmlRs();
		resultMap.put("RetCode", commonXmlRs.getRetCode());		//返回码
		resultMap.put("RetMsg", commonXmlRs.getRetMsg());		//说明
		resultMap.put("RESERVENO", commonXmlRs.getReserveno());	//预约编号
		resultMap.put("REQUESTNO", commonXmlRs.getRequestno());
		LOGGER.info("s100434接口返回的请求的报文体数据：" + JSONObject.fromObject(commonXmlRs).toString());
		LOGGER.info("S1000434耗时:"+(new Date().getTime() - a)+"ms" );
		return resultMap;
	}
}
