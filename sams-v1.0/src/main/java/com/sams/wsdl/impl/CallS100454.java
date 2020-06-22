package com.sams.wsdl.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.RandomizingID;
import com.sams.common.utils.StringUtils;
import com.sams.custom.mapper.AccountReqTmpMapper;
import com.sams.custom.mapper.AccountStatMapper;
import com.sams.custom.mapper.ChannelProductRelationMapper;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.mapper.ExchangeReqTmpMapper;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.model.AccountStat;
import com.sams.custom.model.PProductInfo;
import com.sams.sys.service.SysDictService;
import com.sams.wsdl.S100454.CommonRqHdr;
import com.sams.wsdl.S100454.CommonXmlRq;
import com.sams.wsdl.S100454.CommonXmlRs;
import com.sams.wsdl.S100454.S100454;
import com.sams.wsdl.S100454.S100454Type;
import com.sams.wsdl.S100454.S100454TypeResponse;
import com.sams.wsdl.S100454.S100454_Service;

//8500
public class CallS100454 {
	
	private static Logger log = LoggerFactory.getLogger(CallS100454.class);
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private AccountStatMapper accountStatMapper;
	
	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	@Autowired
	private AccountReqTmpMapper accountReqTmpMapper;
	 
	public Map<String,Object> callS100454(Map<String,Object> tradeMap){
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		accountStatMapper = (AccountStatMapper)SpringUtils.getBean(AccountStatMapper.class);
		accountReqTmpMapper = (AccountReqTmpMapper)SpringUtils.getBean(AccountReqTmpMapper.class);
		Map<String, Object> grMap = sysDictService.findSaleToTaMapper(Const.GRIDENTITYTYPE);
		Map<String, Object> zzMap = sysDictService.findSaleToTaMapper(Const.ZZIDENTITYTYPE);
		String specialFieldToTAChannel = sysDictService.findDictVo(Const.SPECIAL_FIELD_TO_TA_CHANNEL);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String tradeDate = StringUtils.getStringValue((String)tradeMap.get("BUSINESSDATE"));
				//StringUtils.getStringValue((String)tradeMap.get("TRANSACTIONDATE"));//yyyyMMdd 交易发生日期
		String tradeTime = StringUtils.getStringValue((String)tradeMap.get("TRANSACTIONTIME"));//hhmmss 交易发生时间
		String custType = StringUtils.getStringValue((String)tradeMap.get("INDIVIDUALORINSTITUTION"));//个人或机构标志
		String channelCode = StringUtils.getStringValue((String)tradeMap.get("CHANNELCODE"));//渠道号
		String fundCode = StringUtils.getStringValue((String)tradeMap.get("TAPRODUCTCODE"));//TA产品编号
		String productCode = StringUtils.getStringValue((String)tradeMap.get("FUNDCODE"));//产品编号
		String businessCode = StringUtils.getStringValue((String)tradeMap.get("BUSINESSCODE"));//产品编号
		String contractserialno="";
		PProductInfo productInfo =(PProductInfo)tradeMap.get("PRODUCTINFO");
		String setupDate = productInfo.getPiProSetupDate()==null?"":productInfo.getPiProSetupDate();//产品成立日
		String endDate = productInfo.getPiProEndDate()==null?"":productInfo.getPiProEndDate();//产品到期日
		String ipoEndDate = productInfo.getPiIpoEndDate() == null ? "" : productInfo.getPiIpoEndDate();//募集结束日
		
		String identifyTypeMap = null;
		AccountStat accountStat = accountStatMapper.qrySendTaAccountStatus(tradeMap);
		String taAccountId = accountStat.getAsTaAccountId();
		//修改个人机构取值逻辑，从账户取值 20191217
		custType = accountStat.getAsIndividualOrInstitution(); // 个人或机构标志
		if(custType.equals("0")){
			identifyTypeMap = (String)zzMap.get(accountStat.getAsCertificateType());//证件类型
		}else{
			identifyTypeMap = (String)grMap.get(accountStat.getAsCertificateType());//证件类型
		}
		//是否含费标识
		S100454_Service s100454 = new S100454_Service();
		S100454 s = s100454.getS100454SOAP();
		S100454Type common = new S100454Type();
		//请求报文头
		CommonRqHdr commonRqHdr = new CommonRqHdr();
		commonRqHdr.setChannelId(channelCode);
		commonRqHdr.setTransCode(Const.TransCode_100454);
		commonRqHdr.setTransDate(tradeDate);
		commonRqHdr.setTransTime(tradeTime+"000");
		commonRqHdr.setVersion(Const.Version);
		
		//报文体
		CommonXmlRq commonXmlRq = new CommonXmlRq();
		commonXmlRq.setActflag("3");//操作类型1：新增/修改 2：撤消/认申购、追加置无效 3：新增/修改(已确认) 4：TA中根据CRM预约金额更新合同金额(Y) 必填
		commonXmlRq.setCustno(taAccountId.replace("SH1", "00"));//客户编号 必填 取交易申请临时表的投资人基金帐号TAAccountID字段
		commonXmlRq.setFundacco(taAccountId);//理财账号
		//String certificateType= StringUtils.getStringValue((String)retAccoMap.get("CERTIFICATETYPE"));
		//证件类型、证件号、客户类型 必填 从账户确认表里取个人证件类型及机构证件型CertificateType、投资人证件号码CertificateNo、个人/机构标志IndividualOrInstitution取
		commonXmlRq.setIdentitytype(identifyTypeMap);//证件类型  必填  CERTIFICATETYPE
		commonXmlRq.setIdentityno(accountStat.getAsCertificateNo());//证件号 必填
		commonXmlRq.setCusttype(custType);//客户类型  必填  CERTIFICATENO
		commonXmlRq.setCustname(accountStat.getAsInvestorName());//客户姓名  必填 INVESTORNAME
		PchannelInfoMapper pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		String managerCode  = pchannelInfoMapper.selectManagerCode(channelCode);
		commonXmlRq.setBrokeraccount(managerCode==null?"":managerCode); //客户经理编号      
		commonXmlRq.setYearcalctype(productInfo.getPiProYieldType()==null?"":productInfo.getPiProYieldType());//收益类别计算方式 （0:算头不算尾  1:算头又算尾）
		commonXmlRq.setIncomeyeardays(productInfo.getPiAnnuCalDays()==null?"":productInfo.getPiAnnuCalDays());//年化计算天数 传数字 365 360
		String productType=productInfo.getPiProductType();
		Map<String,Object> inputMap=Maps.newHashMap();
		inputMap.put("FUNDCODE", StringUtils.getStringValue((String)tradeMap.get("FUNDCODE")));
		inputMap.put("CHANNELCODE", channelCode);
		ChannelProductRelationMapper channelProductRelationMapper=(ChannelProductRelationMapper) SpringUtils.getBean(ChannelProductRelationMapper.class);
		inputMap.put("APPLICATIONAMOUNT", StringUtils.getStringValue((String.valueOf(tradeMap.get("APPLICATIONAMOUNT")))));
		Map<String,Object>  sectionNumberAndFundRate=channelProductRelationMapper.selectSectionNumberAndFundRate(inputMap);
		commonXmlRq.setProfitclass(MapUtils.getString(sectionNumberAndFundRate, "SECTIONNUMBER", ""));//受益级别
		commonXmlRq.setProfit(MapUtils.getString(sectionNumberAndFundRate,"FUNDRATE",""));
		commonXmlRq.setFundcode(fundCode); 
		
		//交易申请临时表  TA确认交易流水号 TASerialNO
		//commonXmlRq.setReserveno("SH201905151111341440");
		commonXmlRq.setReserveno(StringUtils.getStringValue((String)tradeMap.get("TASERIALNO")));  //预约编号(Y)   
		//commonXmlRq.setFundacco("SH1000670249");
		//交易申请临时表 申请金额 ApplicationAmount
		commonXmlRq.setContractsignbalance(StringUtils.getStringValue((String.valueOf(tradeMap.get("APPLICATIONAMOUNT")))));
		//账户申请表 投资人收款银行账户户名Acct_Name_Inve_Clear_Agen、投资人收款银行账户账号Acct_No_Inve_Clear_Agen、投资人收款银行账户开户行Clearing_Agency
		
		//20200507  建设银行回款账户需要上送
		if(!StringUtils.isEmpty(specialFieldToTAChannel) && specialFieldToTAChannel.contains(channelCode)){
			//查询该客户开户成功的账户记录
			Map<String, Object> paymentAccount = accountReqTmpMapper.queryPaymentAccountInfo(tradeMap);
			//投资人收款银行账户户名
			String nameOfInvestor = MapUtils.getString(paymentAccount, "NAMEOFINVESTOR");
			//投资人收款银行账户账号
			String noOfInvestor = MapUtils.getString(paymentAccount, "NOOFINVESTOR");
			//投资人收款银行账户开户行
			String clearingAgency = MapUtils.getString(paymentAccount, "CLEARINGAGENCY");
			commonXmlRq.setNameinbank(nameOfInvestor);                          //银行账户名(Y)                             
			commonXmlRq.setBankname(noOfInvestor);                      //银行名称(Y)                               
			commonXmlRq.setBankacco(clearingAgency); 
		}else{
			commonXmlRq.setNameinbank("[空回款账户]");                          //银行账户名(Y)                             
			commonXmlRq.setBankname("[空回款账户]");                      //银行名称(Y)                               
			commonXmlRq.setBankacco("[空回款账户]");                            //银行账户号(Y)   
		}
		
		RandomizingID random = new RandomizingID("SH", "yyyyMMddHHmmss", 8, false);
		//create by yindy  外部合同号包含成立日
		//random = new RandomizingID("SH"+setupDate, "ddHHmmss", 4, false);
		String trustcontractid = "";
		if(Const.FUND_TYPE_456.contains(productType)){
			/*实现该需求 wangchao20200612  
		      TA接收代销管理系统的交易信息时，外部合同号包含产品成立日（产品类型包括：固收、T+N，封闭净值）。
		               调整获得组合外部合同号唯一数值规则逻辑。然后把产品成立日加入生成的外部合同号规则数值中。*/
			 trustcontractid=random.genOutContract(setupDate);
		}else{
			 trustcontractid=random.genNewId();
		}
		commonXmlRq.setBankno("000");// 银行编号 必填
		commonXmlRq.setContractoperator("");// 合同录入人 必填（默认为空）
        inputMap.put("TRANSACTIONACCOUNTID", (String)tradeMap.get("TRANSACTIONACCOUNTID"));
        inputMap.put("FUNDCODE", productCode);
        inputMap.put("CHANNELCODE", channelCode);
        ExchangeReqTmpMapper exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
        int firstTradeCount= exchangeReqTmpMapper.selectFirstTradeCount(inputMap);
		/*货币类产品：现金丰利A、现金丰利B、红宝石7天  权益类 FOF 多币种  允许追加
		非货币类产品：固收类、红宝石T+N，封闭净值类  不允许追加*/
        //发交易方式，查询设置
        productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
        Map<String, Object> pcProduct = productInfoMapper.selectAllByChannelCodeAndFundCode(StringUtils.getStringValue((String)tradeMap.get("FUNDCODE")),channelCode,fundCode,StringUtils.getStringValue((String)tradeMap.get("BATCHNO")));
        String isSetUp = (String)pcProduct.get("CP_IS_SETUP");  //设置的TA端产品是否已成立
		if(firstTradeCount == 0 && Const.FUND_TYPE_123789.contains(productType)){
			//首次购买 
			commonXmlRq = getFirstTrade(commonXmlRq,isSetUp);
		}else{
			if(Const.FUND_TYPE_0456.contains(productType)){
				//首次购买 
				commonXmlRq = getFirstTrade(commonXmlRq,isSetUp);
				//权益  fof 多币种 认购期只允许一个合同，并且没有追加
			}else if(Const.FUND_TYPE_789.contains(productType)){
				//认购期
				if(tradeDate.compareTo(ipoEndDate) <= 0){
					if(firstTradeCount != 0 ){//有合同    该笔交易置失败 
						resultMap.put("RETCODE", ExceptionConStants.retCode_9999);
						resultMap.put("RETMSG",ExceptionConStants.retMsg_S00017);
						log.info("权益/FOF/多币种类产品,募集期"+tradeDate+",基金账号"+taAccountId+",产品："+fundCode+",渠道："+channelCode+"不允许购买多次");
						return resultMap; 
					}
				}else{ // 运作期
					if(firstTradeCount != 0 ){// 有合同   追加 
						commonXmlRq.setIsappend("1");                            //是否申购追加  0:否;1:是  追加只针对申购(Y)
						ContractInfoMapper contractInfoMapper= (ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
						Map<String,Object> firstTradeMap=Maps.newHashMap();
						firstTradeMap.put("TAACCOUNTID", taAccountId);
						firstTradeMap.put("TRANSACTIONACCOUNT", (String)tradeMap.get("TRANSACTIONACCOUNTID"));
						firstTradeMap.put("CHANNELCODE", channelCode);
						firstTradeMap.put("FUNDCODE", fundCode);
						//获得内外部合同号
						Map<String, String> contract = contractInfoMapper.selectFirstTradeContractInfo(firstTradeMap);
						contractserialno = MapUtils.getString(contract, "INCONTRACT", "");
						trustcontractid = MapUtils.getString(contract, "OUTCONTRACT", "");
						commonXmlRq.setBusinflag(Const.BUSINESS_020.equals(businessCode)?Const.TA_BUSINESS_01:Const.TA_BUSINESS_02);
						commonXmlRq.setContractserialno(contractserialno); 
					}
					
				}
			}else{
				commonXmlRq.setIsappend("1");                            //是否申购追加  0:否;1:是  追加只针对申购(Y)
				ContractInfoMapper contractInfoMapper= (ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
				Map<String,Object> firstTradeMap=Maps.newHashMap();
				firstTradeMap.put("TAACCOUNTID", taAccountId);
				firstTradeMap.put("TRANSACTIONACCOUNT", (String)tradeMap.get("TRANSACTIONACCOUNTID"));
				firstTradeMap.put("CHANNELCODE", channelCode);
				firstTradeMap.put("FUNDCODE", fundCode);
				//获得内外部合同号
				Map<String, String> contract = contractInfoMapper.selectFirstTradeContractInfo(firstTradeMap);
				contractserialno = MapUtils.getString(contract, "INCONTRACT", "");
				trustcontractid = MapUtils.getString(contract, "OUTCONTRACT", "");
				commonXmlRq.setBusinflag(Const.BUSINESS_020.equals(businessCode)?Const.TA_BUSINESS_01:Const.TA_BUSINESS_02);
				commonXmlRq.setContractserialno(contractserialno); 
			}
		}

		commonXmlRq.setTrustcontractid(trustcontractid);// 纸质合同号 必填 项目成立日+（到期日-成立日）+预约编号=纸质合同号
		commonXmlRq.setIndustrydetail("");                      //行业细分  
		commonXmlRq.setEastcosttype("0");                        //交易费用选项(Y)   必填 交易费用选项。无值时传递默认值0表示按照产品费率设置。                        
		commonXmlRq.setEastcostvalue("0");                       //交易费用(Y)   必填                            
		commonXmlRq.setTrustagencyno(channelCode); //渠道编号
		commonXmlRq.setRiskgrade("");                           //风险等级          
		commonXmlRq.setOutsystemflag("F");                       //来源外部系统标识(F)    必填 默认（F）       
		commonXmlRq.setRequestdate(tradeDate.substring(0,4)+"-"+tradeDate.substring(4,6)+"-"+tradeDate.substring(6,8));                         //业务申请日期(Y)  格式 yyyy-mm-dd
		commonXmlRq.setContractsigndate(tradeDate.substring(0,4)+"-"+tradeDate.substring(4,6)+"-"+tradeDate.substring(6,8));                    //合同签署日期（申请日期）  
		commonXmlRq.setSourcetype("0");                          //合同类型  必填  0-普通 2-他益 4-子合同(Y)                               
		String moneyType = StringUtils.getStringValue((String)tradeMap.get("CURRENCYTYPE"));		//币种
		commonXmlRq.setRequestmoneytype(moneyType);                    //币种
		commonXmlRq.setBankmoneytype(moneyType);                       //银行币种  
		commonXmlRq.setIsbalancewithfare(productInfo.getPiFeeFlag()==null?"":(String)productInfo.getPiFeeFlag());                   //金额是否含费 1-是 ''/0-否
		commonXmlRq.setGencontractmode("2");	//认购费此字段固定值  必填 默认（2）2:自动生成且自动复核
		commonXmlRq.setNodealflag("1");// 不处理客户信息的标识 默认值传1
		
		//起息日和到期日的设置  货币类产品起息日和到期日都为空，非货币类的取产品信息的成立日和到期日（非货币类：表中取出什么是什么,固收类有到期日为空的情况）
		// 20200117 say from luhua 到期日不影响系统交易，起息日会导致无法确认，所以仅在T+N、封闭净值、丰利F产品中设置起息日
		if(Const.FUND_TYPE_056.contains(productType)){
			commonXmlRq.setBegincalcdate(setupDate==""?setupDate:setupDate.substring(0,4)+"-"+setupDate.substring(4,6)+"-"+setupDate.substring(6,8));
			commonXmlRq.setContractenddate(endDate==""?endDate:endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,8));
		}else{
			commonXmlRq.setBegincalcdate("");
			commonXmlRq.setContractenddate("");
		}
		
		//0类型，丰利F,根据交易币种,设置子渠道号
		if(Const.FUND_TYPE_00.equals(productType)){
			String subChannelCode = productInfo.getPiSubChannelCode();
			commonXmlRq.setTrustagencyno(subChannelCode);
		}
		
		common.setCommonRqHdr(commonRqHdr);
		common.setCommonXmlRq(commonXmlRq);
		
		log.info("s100454接口发送请求的报文头数据：" + JSONObject.fromObject(commonRqHdr).toString());
		log.info("s100454接口发送请求的报文体数据：" + JSONObject.fromObject(commonXmlRq).toString());
		long a = new Date().getTime();
		S100454TypeResponse response = s.newOperation(common);
		CommonXmlRs commonXmlRs = response.getCommonXmlRs();
		resultMap.put("RETCODE", commonXmlRs.getRetCode());
		resultMap.put("RETMSG", commonXmlRs.getRetMsg());
		resultMap.put("RESERVERNO", commonXmlRs.getReserverno());
		resultMap.put("GENCONTRACTMODE", commonXmlRs.getGencontractmode());
		if(firstTradeCount == 0 && Const.FUND_TYPE_123789.contains(productType)){
			resultMap.put("CONTRACTSERIALNO", commonXmlRs.getContractserialno());
		}else if(Const.FUND_TYPE_0456.contains(productType)){
			resultMap.put("CONTRACTSERIALNO", commonXmlRs.getContractserialno());
		}else{
			resultMap.put("CONTRACTSERIALNO", contractserialno);
		}
		resultMap.put("TRUSTCONTRACTID", trustcontractid);
		resultMap.put("TAACCOUNTID", taAccountId);
		log.info("s100454接口返回的请求的报文体数据：" + JSONObject.fromObject(commonXmlRs).toString());
		String isAppend = commonXmlRq.getIsappend() == null ? "" : commonXmlRq.getIsappend();
		if(isAppend.equals("1")){
			resultMap.put("ISAPPEND", "2");
		}else{
			resultMap.put("ISAPPEND", "1");
		}
		log.info("产品:"+fundCode+",S1000454耗时:"+(new Date().getTime() - a)+"ms" );
		return resultMap;
	}


	/**
	 * 首次购买设置参数  
	 * @Title: getFirstTrade   
	 * @author: yindy 2019年11月20日 下午2:28:30
	 * @param: @param commonXmlRq
	 * @param: @param isSetUp
	 * @param: @return      
	 * @return: CommonXmlRq      
	 * @throws
	 */
	private CommonXmlRq getFirstTrade(CommonXmlRq commonXmlRq, String isSetUp) {
		commonXmlRq.setIsappend("0");                            //是否申购追加  0:否;1:是  追加只针对申购(Y)
		commonXmlRq.setContractserialno(""); 
		commonXmlRq.setBusinflag("0".equals(isSetUp)?Const.TA_BUSINESS_01:Const.TA_BUSINESS_02);
		return commonXmlRq;
	}
}
