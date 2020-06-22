package com.sams.wsdl.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.sams.batchfile.service.impl.ExchangeReqTmpServiceImpl;
import com.sams.common.utils.DateUtils;
import com.sams.wsdl.S100411.CommonRqHdr;
import com.sams.wsdl.S100411.CommonXmlRq;
import com.sams.wsdl.S100411.CommonXmlRs;
import com.sams.wsdl.S100411.S100411;
import com.sams.wsdl.S100411.S100411Type;
import com.sams.wsdl.S100411.S100411TypeResponse;
import com.sams.wsdl.S100411.S100411_Service;


/**
 * @ClassName:  CallS100441
 * @Description:发送邮件
 * @author: yindy
 * @date:   2019年7月8日 下午4:41:22   
 *
 */
public class CallS100411 {

	private static Logger LOGGER = LoggerFactory.getLogger(CallS100411.class);


	public Map<String,Object> callS100441(String msg){
		S100411_Service s100411 = new S100411_Service();
		S100411 s = s100411.getS100411SOAP();
		S100411Type common = new S100411Type();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LOGGER.info("发送的信息为："+msg);
		//邮件接口中渠道是TTTNET01,发送邮箱yangsy@shanghaitrust.com
		//接收邮件yangsy@shanghaitrust.com,yangyt@shanghaitrust.com
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		commonRqHdr.setChannelId("TTTNET01");
		commonRqHdr.setTransCode("100411");
		commonRqHdr.setTransDate(DateUtils.getDate(DateUtils.FORMAT_STR_DATE8));
		commonRqHdr.setTransTime("094830123");
		commonRqHdr.setVersion("1.0");

		String mailForm ="";//"luh@shanghaitrust.com";
		String mailTo = "";//"yangsy@shanghaitrust.com,yangyt@shanghaitrust.com";

		commonXmlRq.setMailFrom(mailForm);
		commonXmlRq.setMailTo(mailTo);
		commonXmlRq.setSubject("生成文件与上送文件不一致通知");
		commonXmlRq.setContent(msg);
		commonXmlRq.setBcc(" ");
		commonXmlRq.setCc(" ");

		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);

		LOGGER.info("s100411接口发送请求的报文头数据：" + JSONObject.fromObject(commonRqHdr).toString());
		LOGGER.info("s100411接口发送请求的报文体数据：" + JSONObject.fromObject(commonXmlRq).toString());

		S100411TypeResponse response = s.newOperation(common);

		CommonXmlRs xmlRespone = response.getCommonXmlRs();
		String retCode = xmlRespone.getRetCode();
		String retMsg = xmlRespone.getRetMsg();
		resultMap.put("retCode", retCode);
		resultMap.put("retMsg", retMsg);
		LOGGER.info("调用发送邮件接口的返回结果为:"+retCode+"---"+retMsg);
		return resultMap;
	}
}