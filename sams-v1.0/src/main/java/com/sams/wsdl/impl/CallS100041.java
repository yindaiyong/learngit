package com.sams.wsdl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sams.batchfile.common.FileDataUtil;
import com.sams.common.constant.Const;
import com.sams.wsdl.S100041.CommonRqHdr;
import com.sams.wsdl.S100041.CommonXmlRq;
import com.sams.wsdl.S100041.CommonXmlRs;
import com.sams.wsdl.S100041.S100041;
import com.sams.wsdl.S100041.S100041Type;
import com.sams.wsdl.S100041.S100041TypeResponse;
import com.sams.wsdl.S100041.S100041_Service;

import net.sf.json.JSONObject;
//8302
public class CallS100041 {

	private static Logger LOGGER = LoggerFactory.getLogger(CallS100041.class);
	
	
	/**
	 * @ClassName:  CallS100041  
	 * @Description:无检验新合同
	 * @author: wangchao
	 * 必填字段  ChannelId Flag FundCode FundAcco Balance Password REQUESTNO(TA_TREQUEST表的c_outrequestno字段值)
	 * @date:   2020年02月22日 下午02:21:23   
	 *return='0000'时TA_TREQUEST表的c_memo='取消合同’ c_nodealflag='1'  c_inputmemo='网上交易撤销申请 ' c_nodealcause='1'
	 */
	public Map<String,Object> callS100041 (Map<String,Object> map){
		LOGGER.info("开始无检验新合同的输入参数为:"+map);
		//撤单必填
		Map<String, Object> retMap = new HashMap<String, Object>();
		String channelCode = FileDataUtil.getMapValueByKey(map, "CHANNELCODE");
		String flag = FileDataUtil.getMapValueByKey(map, "FLAG");//业务标志	0：新增 1：修改 2：撤单	
		String fundCode = FileDataUtil.getMapValueByKey(map, "TAPRODUCTCODE");//产品代码	
		String fundAcco = FileDataUtil.getMapValueByKey(map, "TAACCOUNTID");//理财账号
		String requestNo = FileDataUtil.getMapValueByKey(map, "REQUESTNO");//申请单编号 Flag 为1 或2 必填
		String password = Const.ENCRYPT_PASSWORD;//交易密码 必填
		
		//撤单非必填
//		String balance = FileDataUtil.getMapValueByKey(map, "APPLICATIONAMOUNT");//申请金额 必填
//		String fromBankAcco = FileDataUtil.getMapValueByKey(map, "FROMBANKACCO");//	扣款账号	非必填 Flag 为0 必填
//		String remitType = FileDataUtil.getMapValueByKey(map, "REMITTYPE");//	RemitType	扣款方式	0：网页支付 1：托收扣款 2 : 线下支付 3：货币类产品 非必填	 Flag 为0 必填
//		String businFlag = FileDataUtil.getMapValueByKey(map, "BUSINFLAG");//BusinFlag	业务代码	01：认购  02：申购	☆  Flag 为0 必填
//		String profitClass = FileDataUtil.getMapValueByKey(map, "PROFITCLASS");//ProfitClass	受益级别		非必填 Flag 为0 必填
//		String bankNo = FileDataUtil.getMapValueByKey(map, "BANKNO");//	BankNo	回款银行编号 非必填		Flag 为0 必填
//		String bankName = FileDataUtil.getMapValueByKey(map, "BANKNAME");//	BankName	回款银行名称	 非必填	
//		String bankAcco = FileDataUtil.getMapValueByKey(map, "BANKACCO");//	BankAcco	回款银行账号	非必填	Flag 为0 必填
//		String provinceCode = FileDataUtil.getMapValueByKey(map, "PROVINCECODE");// ProvinceCode	回款账户开户省份		非必填	
//		String cityNo = FileDataUtil.getMapValueByKey(map, "CITYNO");//CityNo	回款账户开户城市	非必填	
//		String bigPaySystemNo = FileDataUtil.getMapValueByKey(map, "BIGPAYSYSTEMNO");//BigPaySystemNo	回款账户支付联行号(大额支付号)		非必填	
//		String fromRequestDate = FileDataUtil.getMapValueByKey(map, "FROMREQUESTDATE");//FromRequestDate	支付合同赎回日期		非必填	
//		String isNeedPContract = FileDataUtil.getMapValueByKey(map, "ISNEEDPCONTRACT");//IsNeedPContract	是否需要纸质合同文本	0或空：不需要 1：需要	非必填	
//		String pContractSendAddress = FileDataUtil.getMapValueByKey(map, "PCONTRACTSENDADDRESS");//PContractSendAddress	合同寄送地址		非必填
//		String fromFundCode = FileDataUtil.getMapValueByKey(map, "FROMFUNDCODE");//FromFundCode	支付产品编号	非必填		remittype为3时必填
//		String fromContractSerialNo = FileDataUtil.getMapValueByKey(map, "FROMCONTRACTSERIALNO");//FromContractSerialNo	支付产品合同		非必填	
//		String sourceType = FileDataUtil.getMapValueByKey(map, "SOURCETYPE");//	SourceType	缴款方式	0 转账(0128)  5 转账(5859) 6 转账(3938) 8转账(4647) 9 网上信托建行 14 转账(1383)  16 网上信托浦发	非必填	
//		String outSystemFlag = FileDataUtil.getMapValueByKey(map, "OUTSYSTEMFLAG");//	OutSystemFlag	外部申请来源系统	0：CRM；	1：网上交易平台；2：微信平台；3：付费通平台；4：赢通宝平台；	消费必填 非必填
//		String outRequestNo = FileDataUtil.getMapValueByKey(map, "OUTREQUESTNO");//	OutRequestNo	外部申请编号 非必填			消费必填
//		String bankId = FileDataUtil.getMapValueByKey(map, "BANKID");//	BankId	支付渠道	非必填		
//		String backContractSerialNo = FileDataUtil.getMapValueByKey(map, "BACKCONTRACTSERIALNO");//	BackContractSerialNo	返回产品的合同号		非必填
//		String backFundCode = FileDataUtil.getMapValueByKey(map, "BACKFUNDCODE");//	BackFundCode	返回产品的产品编号	非必填
//		String transType = FileDataUtil.getMapValueByKey(map, "TRANSTYPE");//	TransType	交易类型	返回方式为产品时填1	非必填
//		String contractId = FileDataUtil.getMapValueByKey(map, "CONTRACTID");//	ContractId	外部合同编号		非必填
//		String shareBeginDate = FileDataUtil.getMapValueByKey(map, "SHAREBEGINDATE");//	ShareBeginDate	成立日	非必填
//		String contractEndDate = FileDataUtil.getMapValueByKey(map, "CONTRACTENDDATE");//	ContractEndDate	截止日		非必填
//		String profit = FileDataUtil.getMapValueByKey(map, "PROFIT");//	Profit	收益率		非必填
//		String yearCalcType = FileDataUtil.getMapValueByKey(map, "YEARCALCTYPE");//YearCalcType	收益计算方式	0:算头不算尾; 1:算头又算尾;	非必填
//		String incomeYearDays = FileDataUtil.getMapValueByKey(map, "INCOMEYEARDAYS");//IncomeYearDays	年化计算天数		非必填

		//请求报文头
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		String transCode = Const.TransCode_100041;
		String transactionDate = MapUtils.getString(map, "TRANSACTIONDATE");
		String transactionTime = MapUtils.getString(map, "TRANSACTIONTIME","")+"000";
		String version = Const.Version;
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(transCode);
		commonRqHdr.setTransDate(transactionDate);
		commonRqHdr.setTransTime(transactionTime);
		commonRqHdr.setVersion(version);
		
		//请求报文体
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		if(Const.S100041Flag_0.equals(flag)){
//			commonXmlRq.setFromBankAcco(fromBankAcco);
//			commonXmlRq.setRemitType(remitType);
//			commonXmlRq.setBusinFlag(businFlag);
//			commonXmlRq.setProfitClass(profitClass);
//			commonXmlRq.setBankNo(bankNo);
//			commonXmlRq.setBankAcco(bankAcco);
		}else if(Const.S100041Flag_1.equals(flag)||Const.S100041Flag_2.equals(flag)){
			commonXmlRq.setRequestNo(requestNo);
		}
		//撤单必填
		commonXmlRq.setFlag(flag);
		commonXmlRq.setFundCode(fundCode);
		commonXmlRq.setFundAcco(fundAcco);
		commonXmlRq.setPassword(password);
		
		
		//撤单非必填
//		commonXmlRq.setBalance(balance);
//		commonXmlRq.setBankName(bankName);
//		commonXmlRq.setProvinceCode(provinceCode); 
//		commonXmlRq.setCityNo(cityNo);
//		commonXmlRq.setBigPaySystemNo(bigPaySystemNo);
//		commonXmlRq.setFromRequestDate(fromRequestDate);
//		commonXmlRq.setIsNeedPContract(isNeedPContract);
//		commonXmlRq.setPContractSendAddress(pContractSendAddress);
//		commonXmlRq.setFromFundCode(fromFundCode);
//		commonXmlRq.setFromContractSerialNo(fromContractSerialNo);
//		commonXmlRq.setSourceType(sourceType);
//		commonXmlRq.setOutSystemFlag(outSystemFlag);
//		commonXmlRq.setBankId(bankId);
//		commonXmlRq.setBackContractSerialNo(backContractSerialNo);
//		commonXmlRq.setBackFundCode(backFundCode);
//		commonXmlRq.setTransType(transType);
//		commonXmlRq.setContractId(contractId);
//		commonXmlRq.setOutRequestNo(outRequestNo);
//		commonXmlRq.setContractEndDate(contractEndDate);
//		commonXmlRq.setProfit(profit);
//		commonXmlRq.setYearCalcType(yearCalcType);
//		commonXmlRq.setIncomeYearDays(incomeYearDays);

	
		LOGGER.info("调用S100041接口的请求报文头:"+JSONObject.fromObject(commonRqHdr).toString());
		LOGGER.info("调用S100041接口的请求报文体:"+JSONObject.fromObject(commonXmlRq).toString());
		
		S100041_Service S100041 = new S100041_Service();
		S100041 s =S100041.getS100041SOAP();
		S100041Type common = new S100041Type();
		
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		long start = new Date().getTime();
		S100041TypeResponse response = s.s100041(common);
		LOGGER.info("调用接口S100041耗时:"+(new Date().getTime() - start)+"ms");
		CommonXmlRs commonXmlRs = response.getCommonXmlRs();
		Object retCode = commonXmlRs.getRetCode();
		Object retMsg = commonXmlRs.getRetMsg();
		//Object RequestNo = commonXmlRs.getRequestNo();
		retMap.put("retCode", retCode);
		retMap.put("retMsg", retMsg);	
		//retMap.put("RequestNo", RequestNo);	
		LOGGER.info("调用S100041接口返回结果:"+retMap);
		return retMap;
	}
}
