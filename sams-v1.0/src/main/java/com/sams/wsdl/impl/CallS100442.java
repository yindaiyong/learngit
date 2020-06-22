package com.sams.wsdl.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sams.batchfile.service.ExchangeReqTmpService;
import com.sams.common.utils.MyMapUtils;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sams.common.constant.Const;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.RandomizingID;
import com.sams.common.utils.StringUtils;
import com.sams.custom.mapper.AccountStatMapper;
import com.sams.custom.model.AccountStat;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductSalePrarms;
import com.sams.wsdl.S100442.CommonRqHdr;
import com.sams.wsdl.S100442.CommonXmlRq;
import com.sams.wsdl.S100442.CommonXmlRs;
import com.sams.wsdl.S100442.S100442;
import com.sams.wsdl.S100442.S100442Type;
import com.sams.wsdl.S100442.S100442TypeResponse;
import com.sams.wsdl.S100442.S100442_Service;
//8584
public class CallS100442 {
	private static Logger log = LoggerFactory.getLogger(CallS100442.class);
	
	@Autowired
	private AccountStatMapper accountStatMapper;
	
	@Autowired
	private ExchangeReqTmpService exchangeReqTmpService;
	
	public Map<String,Object> callS100442(Map<String,Object> tradeMap){
		log.info("s100442是否含费标识发送："+tradeMap);
		accountStatMapper = (AccountStatMapper)SpringUtils.getBean(AccountStatMapper.class);
		exchangeReqTmpService = (ExchangeReqTmpService)SpringUtils.getBean(ExchangeReqTmpService.class);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String tradeDate = StringUtils.getStringValue((String)tradeMap.get("BUSINESSDATE"));
		String tradeTime = StringUtils.getStringValue((String)tradeMap.get("TRANSACTIONTIME"));//hhmmss 交易发生时间
		String channelCode = StringUtils.getStringValue((String)tradeMap.get("CHANNELCODE"));//渠道号
		String fundCode = StringUtils.getStringValue((String)tradeMap.get("TAPRODUCTCODE"));//产品编号
		PProductInfo productInfo =(PProductInfo)tradeMap.get("PRODUCTINFO");
		
		AccountStat accountStat = accountStatMapper.qrySendTaAccountStatus(tradeMap);
		String taAccountId = accountStat.getAsTaAccountId();//理财账号
		//修改个人机构取值逻辑，从账户取值 20191217
		//是否含费标识
		S100442_Service s100442 = new S100442_Service();
		S100442 s = s100442.getS100442SOAP();
		S100442Type common = new S100442Type();
		//请求报文头
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(Const.TransCode_100442);
		commonRqHdr.setTransDate(tradeDate);
		commonRqHdr.setTransTime(tradeTime+"000");
		commonRqHdr.setVersion(Const.Version);
		
		//报文体
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		commonXmlRq.setActflag("1");	
		RandomizingID random = new RandomizingID("", "HHmmss", 4, false);
		commonXmlRq.setCrmserialno(random.genNewId());		//序列号(唯一索引)
		commonXmlRq.setReserveno(StringUtils.getStringValue((String)tradeMap.get("RESERVERNO")));		//预约编号
		
		//commonXmlRq.setOccurbalance(tradeMap.get("L_APPLICATIONAMOUNT") == null ? "" : tradeMap.get("L_APPLICATIONAMOUNT").toString());	//到账金额--申请金额
		commonXmlRq.setOccurbalance(MyMapUtils.getString(tradeMap,"APPLICATIONAMOUNT"));	//到账金额--申请金额
		commonXmlRq.setArrivedate(tradeDate.substring(0,4)+"-"+tradeDate.substring(4,6)+"-"+tradeDate.substring(6,8));		//到账日期(TA如果为勾兑模式，POS机：必填；其他方式：可以不录)，经确认，此字段实际为打款日期
		commonXmlRq.setSourcetype(productInfo.getPiPayType()==null?"0":(String)productInfo.getPiPayType());		//缴款方式(0-转账 1-POS机 2-支票)0或者7
		commonXmlRq.setPaidcount("1");		//打款笔数(TA如果为勾兑模式，需必填)
		commonXmlRq.setFundacco(taAccountId);		//理财账号
		commonXmlRq.setFundcode(fundCode);//产品代码
		
		String productType=productInfo.getPiProductType();
		commonXmlRq.setOutsystemflag("F");	//来源外部系统标识
		commonXmlRq.setCapdealtype("1");		//资金处理方式(0或空：默认根据PayingTypeIsAvailable参数控制 1：按资金勾兑处理 2：按到账复核处理)
		//以下非必填
		commonXmlRq.setPaidcount("1");
		commonXmlRq.setFrombankno("");
		commonXmlRq.setFrombankname("");
		commonXmlRq.setFrombankacco("");
		commonXmlRq.setFromnameinbank("");
		commonXmlRq.setAccoid("");
		commonXmlRq.setCrmoperator("");
		
		//8 类型,根据交易币种,设置子渠道号  20200214 废弃该逻辑 by  luhua
		//只处理缴款方式
		if(Const.FUND_TYPE_08.equals(productType)){
			ProductSalePrarms product = exchangeReqTmpService.getProductSaleParams(tradeMap,productType,channelCode,productInfo.getPiChannelProductCode());
	        if(product != null){
	        	commonXmlRq.setSourcetype(product.getPspPayType());
	        }
		}
		
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		log.info("s100442接口发送请求的报文头数据：" + JSONObject.fromObject(commonRqHdr).toString());
		log.info("s100442接口发送请求的报文体数据：" + JSONObject.fromObject(commonXmlRq).toString());
		long a = new Date().getTime();
		S100442TypeResponse response = s.s100442(common);
		CommonXmlRs commonXmlRs = response.getCommonXmlRs();
		log.info("产品:"+fundCode+",S100442耗时:"+(new Date().getTime() - a)+"ms" );
		resultMap.put("RETCODE", commonXmlRs.getRetCode());		//返回码
		resultMap.put("RETMSG", commonXmlRs.getRetMsg());		//说明
		resultMap.put("RESERVENO", commonXmlRs.getReserveno());	//预约编号
		resultMap.put("TAACCOUNTID", taAccountId);
		log.info("s100442接口返回的请求的报文体数据：" + JSONObject.fromObject(commonXmlRs).toString());
		return resultMap;
	}
}