package com.sams.wsdl.impl;

import java.util.HashMap;
import java.util.Map;

import com.sams.common.utils.MyMapUtils;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sams.common.constant.Const;
import com.sams.wsdl.S100037.CommonRqHdr;
import com.sams.wsdl.S100037.CommonXmlRq;
import com.sams.wsdl.S100037.CommonXmlRs;
import com.sams.wsdl.S100037.S100037;
import com.sams.wsdl.S100037.S100037Type;
import com.sams.wsdl.S100037.S100037TypeResponse;
import com.sams.wsdl.S100037.S100037_Service;


public class CallS100037 {
	private Logger log = Logger.getLogger(CallS100037.class);
	
	public Map<String,Object> callS100037(Map<String,Object> map){
		log.info("s100037赎回："+map);
		Map<String,Object> result = new HashMap<String,Object>();
		String tradeDate = MyMapUtils.getStringByTrim(map,"TRANSACTIONDATE");//yyyyMMdd
		String tradeTime = MyMapUtils.getStringByTrim(map,"TRANSACTIONTIME");//hhmmss
		
		S100037_Service S100037 = new S100037_Service();
		S100037 s =S100037.getS100037SOAP();
		S100037Type common = new S100037Type();
		//请求报文头
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		commonRqHdr.setChannelId(MyMapUtils.getStringByTrim(map,"CHANNELCODE"));
		commonRqHdr.setTransCode(Const.TransCode_100037);
		commonRqHdr.setTransDate(tradeDate);
		commonRqHdr.setTransTime(tradeTime+"000");
		commonRqHdr.setVersion(Const.Version);
		
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		commonXmlRq.setFundAcco(MyMapUtils.getStringByTrim(map,"TAACCOUNTID"));
		commonXmlRq.setFundCode(MyMapUtils.getStringByTrim(map,"FUNDCODE"));
		commonXmlRq.setSerialNo(MyMapUtils.getString(map,"SERIALNO"));
		commonXmlRq.setTrustContractId(MyMapUtils.getString(map,"TRUSTCONTRACTID"));
		commonXmlRq.setShares(MyMapUtils.getStringByTrim(map,"APPLICATIONVOL"));
		commonXmlRq.setRedeemType(Const.REDEEMTYPE_0);//赎回类型
		commonXmlRq.setPassword(Const.ENCRYPT_PASSWORD);
		
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		
		log.info("s100037接口发送请求的报文头数据：" + JSONObject.fromObject(commonRqHdr).toString());
		log.info("s100037接口发送请求的报文体数据：" + JSONObject.fromObject(commonXmlRq).toString());
		
		S100037TypeResponse response = s.s100037(common);
		CommonXmlRs xmlRespone = response.getCommonXmlRs();
		String retCode = xmlRespone.getRetCode();
		String retMsg = xmlRespone.getRetMsg();
		String requestNo = xmlRespone.getRequestNo();
		result.put("retCode", retCode);
		result.put("requestNo", requestNo);
		result.put("retMsg", retMsg);
		return result;
	}
}
