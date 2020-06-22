package com.sams.wsdl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sams.common.constant.Const;
import com.sams.wsdl.S100512.CommonRqHdr;
import com.sams.wsdl.S100512.CommonXmlRq;
import com.sams.wsdl.S100512.CommonXmlRs;
import com.sams.wsdl.S100512.S100512;
import com.sams.wsdl.S100512.S100512Type;
import com.sams.wsdl.S100512.S100512TypeResponse;
import com.sams.wsdl.S100512.S100512_Service;

/**
 * 8408	CRM修改TA回款账户信息
 * @ClassName:  CallS100512   
 * @author: yindy
 * @date:   2020年2月13日 下午3:09:45   
 *
 */
//8408
public class CallS100512 {

	private static Logger LOGGER = LoggerFactory.getLogger(CallS100512.class);
	
	
	/**
	 * 修改TA回款账户信息
	 * @Title: callS100512   
	 * @author: yindy 2020年2月13日 下午3:10:45
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String,Object> callS100512(Map<String,Object> map){
		LOGGER.info("调用修改TA回款账户的信息"+map);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String channelCode = MapUtils.getString(map, "CHANNELCODE");
		String contractNo = MapUtils.getString(map, "INCONTRACT");
		String moneyType = MapUtils.getString(map, "CURRENCYTYPE");
		//报文头字段
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		String transCode = Const.TransCode_100512;
		String transactionDate = MapUtils.getString(map, "TRANSACTIONDATE");
		String transactionTime = MapUtils.getString(map, "TRANSACTIONTIME","")+"000";
		String version = Const.Version;
		//设置报文头的值
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(transCode);
		commonRqHdr.setTransDate(transactionDate);
		commonRqHdr.setTransTime(transactionTime);
		commonRqHdr.setVersion(version);
		
		//公共报文体字段
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		commonXmlRq.setContractserialno(contractNo);  //合同申请序列号
		commonXmlRq.setNameinbank("[空回款账户]");   //银行账户名
		commonXmlRq.setBankname("[空回款账户]");   //银行名称
		commonXmlRq.setBankacco("[空回款账户]");  //银行账户号
		commonXmlRq.setBankno("000");  //银行编号
		commonXmlRq.setBankprovincecode("");  //开户行所在地省
		commonXmlRq.setBankcityno("");  //开户行所在地市
		commonXmlRq.setBanklinecode("");  //开户行大额支付号
		commonXmlRq.setMoneytype(moneyType);  //币种
		
		
		//修改合同渠道号
		S100512_Service s100150 = new S100512_Service();
		S100512 s = s100150.getS100512SOAP();
		S100512Type common = new S100512Type();
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		
		LOGGER.info("调用S100512接口发送的报文头数据:"+JSONObject.fromObject(commonRqHdr).toString());
		LOGGER.info("调用S100512接口发送的报文体数据:"+JSONObject.fromObject(commonXmlRq).toString());
		long start = new Date().getTime();
		S100512TypeResponse response = s.newOperation(common);
		LOGGER.info("调用接口S100512耗时:"+(new Date().getTime() - start)+"ms");
		CommonXmlRs xmlRespone = response.getCommonXmlRs();
		String retCode = xmlRespone.getRetCode();
		String retMsg = xmlRespone.getRetMsg();
		resultMap.put("retCode", retCode);
		resultMap.put("retMsg", retMsg);
		LOGGER.info("调用S100512接口返回结果:"+resultMap);
		return resultMap;
	}
}
