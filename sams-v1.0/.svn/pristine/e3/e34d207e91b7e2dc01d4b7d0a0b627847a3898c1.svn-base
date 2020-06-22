package com.sams.wsdl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sams.batchfile.common.FileDataUtil;
import com.sams.common.constant.Const;
import com.sams.common.utils.DateUtils;
import com.sams.wsdl.S100040.CommonRqHdr;
import com.sams.wsdl.S100040.CommonXmlRq;
import com.sams.wsdl.S100040.CommonXmlRs;
import com.sams.wsdl.S100040.S100040;
import com.sams.wsdl.S100040.S100040Type;
import com.sams.wsdl.S100040.S100040TypeResponse;
import com.sams.wsdl.S100040.S100040_Service;

/**
 * @ClassName:  CallS100040   
 * @Description:开通网上交易渠道
 * @author: yindy
 * @date:   2019年5月6日 上午11:21:23   
 *
 */
//8321
public class CallS100040 {

	private static Logger LOGGER = LoggerFactory.getLogger(CallS100040.class);
	
	public Map<String,Object> callS100040 (Map<String,Object> map){
		LOGGER.info("开通网上交易渠道的用户信息为:"+map);
		Map<String, Object> retMap = new HashMap<String, Object>();
		//请求报文头
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		String time  = DateUtils.getDate(DateUtils.FORMAT_STR_TIME17); //当前时间
		String channelCode = FileDataUtil.getMapValueByKey(map, "CHANNELCODE");
		String transCode = Const.TransCode_100040;
		String transDate = time.substring(0,8);
		String transTime = time.substring(8,17);
		String version = Const.Version;
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(transCode);
		commonRqHdr.setTransDate(transDate);
		commonRqHdr.setTransTime(transTime);
		commonRqHdr.setVersion(version);
		
		//请求报文体
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		String fundAcco = FileDataUtil.getMapValueByKey(map, "TAACCOUNTID");
		String tradePassword = Const.ENCRYPT_PASSWORD;
		commonXmlRq.setFundAcco(fundAcco);
		commonXmlRq.setTradePassword(tradePassword);
		
		
		LOGGER.info("调用S100040接口的请求报文头:"+JSONObject.fromObject(commonRqHdr).toString());
		LOGGER.info("调用S100040接口的请求报文体:"+JSONObject.fromObject(commonXmlRq).toString());
		
		S100040_Service S100040 = new S100040_Service();
		S100040 s =S100040.getS100040SOAP();
		S100040Type common = new S100040Type();
		
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		long start = new Date().getTime();
		S100040TypeResponse response = s.s100040(common);
		LOGGER.info("调用接口S100040耗时:"+(new Date().getTime() - start)+"ms");
		CommonXmlRs commonXmlRs = response.getCommonXmlRs();
		Object retCode = commonXmlRs.getRetCode();
		Object retMsg = commonXmlRs.getRetMsg();
		retMap.put("retCode", retCode);
		retMap.put("retMsg", retMsg);	
		LOGGER.info("调用S100040接口返回结果:"+retMap);
		return retMap;
	}
}
