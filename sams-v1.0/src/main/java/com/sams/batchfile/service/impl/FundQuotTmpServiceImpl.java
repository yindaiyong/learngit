package com.sams.batchfile.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelProductRealService;
import com.sams.batchfile.service.CloseDateService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileInterfaceFieldService;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.FundQuotTmpService;
import com.sams.batchfile.service.ParProductInfoService;
import com.sams.batchfile.service.ProductOpenDayService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.ClassRefUtil;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.StringUtils;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.mapper.FundMarketMapper;
import com.sams.custom.mapper.FundQuotTmpMapper;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.FundMarketCustom;
import com.sams.custom.model.FundQuotTmp;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductOpenDay;
import com.sams.sys.service.SysDictService;
import com.sams.sys.service.impl.SysDictServiceImpl;


@Service
public class FundQuotTmpServiceImpl implements FundQuotTmpService {
	private static Logger LOGGER = LoggerFactory.getLogger(FundMarketProcessorServiceImpl.class);

	
	@Autowired
	private FundQuotTmpMapper fundQuotTmpMapper;

	@Autowired
	private FileInterfaceFieldService fileInterfaceFieldService;

	@Autowired
	private CloseDateService closeDateService;

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private ChannelProductRealService channelProductRealService;

	@Autowired
	private FundMarketCfmServier fundMarketCfmServier;
	
	@Autowired
	private ParProductInfoService parProductInfoService;
	
	@Autowired
	private FundMarketCfmServier fundMarketCfmService;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper;

	private FTPUtils ftpUtils;
	
	@Autowired
	private SysDictService sysDictService;
	 
	@Autowired
	private FundMarketMapper fundMarketMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private ProductOpenDayService productOpenDayService;

	@Override
	public int truncateFundQuotTmp() {
		fundQuotTmpMapper = (FundQuotTmpMapper) SpringUtils
				.getBean(FundQuotTmpMapper.class);
		return fundQuotTmpMapper.truncateFundQuotTmp();
	}
	
	/**
	 * 根据募集开始日、募集结束日、产品成立日、产品到期日获取产品状态
	 * 
	 * @param tradeDate
	 *            交易日期
	 * @param fundMarketCustom
	 *            产品信息
	 * @return 返回产品状态
	 */
	public Map<String, Object> getStatusBasedOnDate(String tradeDate, FundMarketCustom fundMarketCustom) {
		Map<String,Object> retMap = Maps.newHashMap();
		String productType = fundMarketCustom.getPiProductType();
		String fundStatus = "";
		if(fundMarketCustom.getPiProEndDate()==null||"".equals(fundMarketCustom.getPiProEndDate())){
			fundMarketCustom.setPiProEndDate(Const.defaultTime);
		}
		// 判断交易日是否在募集开始日期、募集结束日期区间
		if(DateUtils.isEffectiveDate(tradeDate,
			fundMarketCustom.getPiIpoBeginDate(),
			fundMarketCustom.getPiIpoEndDate(), Const.DTAE_FORMAT_YYYYMMDD)) {
			fundStatus = Const.PUBLISH;
		}// 判断交易日是否在募集结束日期、产品成立日区间(不包含这两个日期)
		else if (DateUtils.isEffectiveDate1(tradeDate,
			fundMarketCustom.getPiIpoEndDate(),
			fundMarketCustom.getPiProSetupDate(),
			Const.DTAE_FORMAT_YYYYMMDD)) {
			fundStatus = Const.FUND_SEALING;
			//货币类、非货币类可以直接返回基金封闭状态
			if(Const.FUND_TYPE_01.equals(productType)
					||Const.FUND_TYPE_02.equals(productType)
					||Const.FUND_TYPE_03.equals(productType)
					||Const.FUND_TYPE_04.equals(productType)
					||Const.FUND_TYPE_05.equals(productType)
					||Const.FUND_TYPE_06.equals(productType)
					||Const.FUND_TYPE_07.equals(productType)
					||Const.FUND_TYPE_08.equals(productType)
					||Const.FUND_TYPE_09.equals(productType)){
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				retMap.put("FUNDSTATUS", fundStatus);
				return retMap;	
			}
			
		}// 判断交易日是否在产品成立日、产品到期日区间
		else if (DateUtils.isEffectiveDate(tradeDate,
			fundMarketCustom.getPiProSetupDate(),
			fundMarketCustom.getPiProEndDate(), Const.DTAE_FORMAT_YYYYMMDD)) {
			fundStatus = Const.FUND_SEALING;
		}// 判断交易日是否超过产品到期日
		else if (DateUtils.compareDate(tradeDate,
			fundMarketCustom.getPiProEndDate(), Const.DTAE_FORMAT_YYYYMMDD)) {
			fundStatus = Const.FUND_TERMINATION;
		}
		if (!Const.FUND_TERMINATION.equals(fundStatus) || Const.PUBLISH.equals(fundStatus)) {
			retMap = getStatusBasedOnProductType(tradeDate,fundMarketCustom, fundStatus);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))) 
			{return retMap;}
			fundStatus =MapUtils.getString(retMap, "FUNDSTATUS","");
		}
		
		//渠道校验批次的开关是否开启(目前只有西南证券有特殊需求)
		if(Const.BatchNoOn.equals(fundMarketCustom.getCiBatchNoOnOff())){
			//非货币类行情判断（存在多个批次号） 1）先判断不同批次号的产品信息是否有在募集期，若有，则基金状态为发行
			//                       2）若不同批次号的产品都不在募集期，则判断合同表是否有该产品的在用合同，若有，则基金状态为基金封闭
			//                       3）若合同表内没有改产品的在用合同，则基金状态为基金终止
			if(!Const.FUND_TYPE_01.equals(productType)&&!Const.FUND_TYPE_02.equals(productType)&&!Const.FUND_TYPE_03.equals(productType)){
				parProductInfoService = (ParProductInfoService) SpringUtils.getBean(ParProductInfoService.class);
				Map<String,Object> infoMap = Maps.newHashMap();
				infoMap.put("PRODUCTCODE", fundMarketCustom.getPiChannelProductCode());
				infoMap.put("CHANNELCODE", fundMarketCustom.getPiChannelCode());
				List<PProductInfo> list = parProductInfoService.selectBatchProductInfo(infoMap);
				for(PProductInfo info:list){
					if (DateUtils.isEffectiveDate(tradeDate,info.getPiIpoBeginDate(),info.getPiIpoEndDate(), Const.DTAE_FORMAT_YYYYMMDD)) {
						fundStatus = Const.PUBLISH;
					}
					if(Const.PUBLISH.equals(fundStatus)){
						break;
					}
				}
				if(Const.PUBLISH.equals(fundStatus)){
					retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
					retMap.put("FUNDSTATUS", fundStatus);
					return retMap;
				}else{
					contractInfoMapper = (ContractInfoMapper)SpringUtils.getBean(ContractInfoMapper.class);
	                Map contactMap = Maps.newHashMap();
	                contactMap.put("FUNDCODE", fundMarketCustom.getPiChannelProductCode());
	                contactMap.put("CHANNELCODE", fundMarketCustom.getCiChannelCode());
					List<Map<String,Object>> contactList = contractInfoMapper.selectContractList(contactMap);
					if(contactList!=null&&contactList.size()!=0){
						fundStatus = Const.FUND_SEALING;
						retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
						retMap.put("FUNDSTATUS", fundStatus);
						return retMap;
					}else{
						fundStatus = Const.FUND_TERMINATION;
					}
				}
			}
		}
		
		//产品处于停用状态的产品信息，货币类基金状态为4：停止申购赎回 非货币类9：基金封闭
		String piValidFlag = fundMarketCustom.getPiValidFlag();
		if(Const.BUSINESS_STUTAS_10.equals(piValidFlag)){
			if(Const.FUND_TYPE_01.equals(productType)||Const.FUND_TYPE_02.equals(productType)||Const.FUND_TYPE_03.equals(productType)){
				fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
				LOGGER.info(fundMarketCustom.getPiChannelCode()+"产品停用,产品的基金状态为"+fundStatus);

			}else{
				fundStatus = Const.FUND_SEALING;
				LOGGER.info(fundMarketCustom.getPiChannelCode()+"产品停用,产品的基金状态为"+fundStatus);
			}
		}
		retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		retMap.put("FUNDSTATUS", fundStatus);
		LOGGER.info(fundMarketCustom.getPiChannelProductCode()+"产品的基金状态为"+fundStatus);
		return retMap;
	}

	/**
	 * 查看所有的产品类型在字典表里是否有值
	 * 
	 * @return Map<String,Object>
	 */
	public Map<String, Object> checkDictVo() {
		Map<String, Object> output = Maps.newHashMap();
		Map<String, Object> retMap = Maps.newHashMap();
		List<String> list = new ArrayList<String>();
		list.add(Const.FUND_TYPE_01);
		list.add(Const.FUND_TYPE_02);
		list.add(Const.FUND_TYPE_03);
		list.add(Const.FUND_TYPE_04);
		list.add(Const.FUND_TYPE_05);
		list.add(Const.FUND_TYPE_06);
		list.add(Const.FUND_TYPE_07);
		list.add(Const.FUND_TYPE_08);
		list.add(Const.FUND_TYPE_09);
		for (int i = 0; i < list.size(); i++) {
			output = SysDictServiceImpl.findDictVoByTypeAndName(
					Const.FUND_TYPE_NAME, list.get(i));
			if (!(ExceptionConStants.retCode_0000)
					.equals(output.get("retCode")))
				return retMap;
			retMap.put(list.get(i), (String) output.get("dictCode"));
			retMap.putAll(output);
		}
		return retMap;
	}

	/**
	 * 根据产品类型获取产品状态
	 * 
	 * @param tradeDate
	 *            交易日期
	 * @param fundMarketCustom
	 *            产品信息
	 * @return 返回产品状态
	 */
	public Map<String, Object> getStatusBasedOnProductType(String tradeDate,
			FundMarketCustom fundMarketCustom, String fundStatus) {
		Map<String, Object> retMap = Maps.newHashMap();
		Map<String, Object> checkMap = Maps.newHashMap();
		productOpenDayService = (ProductOpenDayService)SpringUtils.getBean(ProductOpenDayService.class);

		String productType = fundMarketCustom.getPiProductType();//产品类型
		String channelCode = fundMarketCustom.getCiChannelCode();//渠道编号
		String productCode = fundMarketCustom.getPiChannelProductCode();//产品编号
		String batchNo = fundMarketCustom.getPiBatchNumber();//批次号
		SysDictService sysDictService = (SysDictService) SpringUtils.getBean(SysDictService.class);
		//产品类型 1.丰利B类  2.丰利D类    3.红宝石7天   4.固收类  5.封闭净值类 6.固收t+n 7.权益类 8.美月盈 9.FOF
		//查看所有的产品类型在字典表里是否有值
		retMap = checkDictVo();
		Map<String, Object> resultMap = Maps.newHashMap();
		// 1.丰利B类  2.丰利D类    3.红宝石7天   4.固收类  5.封闭净值类 6.固收t+n 7.权益类 8.美月盈 9.FOF这几种产品在募集期全是认购模式，全都判断是否超额，超额则基金封闭，不超额发行状态
		if (productType.equals(retMap.get(Const.FUND_TYPE_01))
				|| productType.equals(retMap.get(Const.FUND_TYPE_02))
				|| productType.equals(retMap.get(Const.FUND_TYPE_03))
				|| productType.equals(retMap.get(Const.FUND_TYPE_04))
				|| productType.equals(retMap.get(Const.FUND_TYPE_05))
				|| productType.equals(retMap.get(Const.FUND_TYPE_06))
				|| productType.equals(retMap.get(Const.FUND_TYPE_07))
				|| productType.equals(retMap.get(Const.FUND_TYPE_08))
				|| productType.equals(retMap.get(Const.FUND_TYPE_09))) {
			if (Const.PUBLISH.equals(fundStatus)) {
				resultMap = gtLimit(fundMarketCustom);
				if (ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
					// 丰利B、D、红宝石七天、固收类、T+N、封闭净值类、权益类 、美月盈、 FOF募集期超额 基金状态9:基金封闭
					fundStatus = Const.FUND_SEALING;
					LOGGER.info(fundMarketCustom.getPiChannelCode()+"产品超额,基金状态为"+fundStatus);
					retMap.put("FUNDSTATUS", fundStatus);
					return retMap;
				} else {
					// 丰利B、D、红宝石七天、固收类、T+N、封闭净值类、权益类 、美月盈、 FOF募集期不超额 基金状态1:发行
					fundStatus = Const.PUBLISH;
					retMap.put("FUNDSTATUS", fundStatus);
					return retMap;
				}
			}
		}
		if ((productType.equals(retMap.get(Const.FUND_TYPE_04)) && Const.FUND_SEALING.equals(fundStatus))
				|| (productType.equals(retMap.get(Const.FUND_TYPE_05)) && Const.FUND_SEALING.equals(fundStatus))
				|| (productType.equals(retMap.get(Const.FUND_TYPE_06)) && Const.FUND_SEALING.equals(fundStatus))) {
			//交易日在募集结束日期、产品成立日区间(不包含这两个日期) 交易日在产品成立日、产品到期日区间这个两个时间段内 固收类、T+N以及封闭净值类产品基金状态为：9：基金封闭
			fundStatus = Const.FUND_SEALING;
			retMap.put("FUNDSTATUS", fundStatus);
			return retMap;
		}
		//丰利产品状态判断（运作期）
		if (productType.equals(retMap.get(Const.FUND_TYPE_01))
				|| productType.equals(retMap.get(Const.FUND_TYPE_02))) {
			String formatDay = getForwardDay(tradeDate, fundMarketCustom.getCiMarketCode().toString());
			if (tradeDate.equals(formatDay)) {
				    //丰利B、D 是结转日且不超额 状态为6：停止赎回
				fundStatus = Const.CANNOT_REDEMPTION;
				resultMap = gtLimit(fundMarketCustom);
				if (ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
					// 丰利B、D 是结转日且超额 状态为4：停止申购赎回 
					fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
					LOGGER.info(fundMarketCustom.getPiChannelCode()+"产品是结转日且超额,基金状态为"+fundStatus);
				}
			} else {
				//丰利B、D 不是结转日且不超额 状态为0：可申购赎回
				fundStatus = Const.CAN_PURCHASE_REDEMPTION;
				resultMap = gtLimit(fundMarketCustom);
				if (ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
					// 丰利B、D 不是结转日且超额 状态为5：停止申购 
					fundStatus = Const.CANNOT_PURCHASE;
					LOGGER.info(fundMarketCustom.getPiChannelCode()+"产品不是结转日但超额,基金状态为"+fundStatus);
				}
			}
		}
		//红宝石七天产品状态判断（运作期）
		if (productType.equals(retMap.get(Const.FUND_TYPE_03))) {
			DateFormat df = new SimpleDateFormat(Const.DTAE_FORMAT_YYYYMMDD);
			// 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
			int dayNo = DateUtils.getDayofweek(tradeDate);
			//从字典表中取stopApplyNum为停止申购对应的星期几
			int stopApplyNum  = Integer.parseInt(sysDictService.findDictVo("stopApply"));
			LOGGER.debug("渠道"+channelCode+"产品"+productCode+"停止申购为"+stopApplyNum);
			//从字典表中取stopRansomNum为停止赎回对应的星期几
			int stopRansomNum  = Integer.parseInt(sysDictService.findDictVo("stopRansom"));
			LOGGER.debug("渠道"+channelCode+"产品"+productCode+"停止赎回为"+stopRansomNum);
			LOGGER.info("返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六");
			String formatDay = getQuarterForwardDay(tradeDate, fundMarketCustom.getCiMarketCode().toString());
			if (dayNo == stopApplyNum ) {
				fundStatus = Const.CANNOT_PURCHASE;
			}

			if (dayNo == stopRansomNum) {
				fundStatus = Const.CANNOT_REDEMPTION;
			}

			if (dayNo == stopApplyNum  && tradeDate.equals(formatDay)) {
				fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
			}

			resultMap = gtLimit(fundMarketCustom);
			if (dayNo == stopRansomNum && ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
				fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
			}

			if (dayNo != stopApplyNum  && dayNo != stopRansomNum) {
				fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
			}

		}
		//固收类、封闭净值类、T+N产品现在是认购模式，只有发行、基金封闭、基金终止三种状态  发行会判断限额，基金封闭、基金终止会直接返回
		if (productType.equals(retMap.get(Const.FUND_TYPE_04))
				||productType.equals(retMap.get(Const.FUND_TYPE_06))
				||productType.equals(retMap.get(Const.FUND_TYPE_05))) {
			retMap.put("FUNDSTATUS", fundStatus);
			return retMap;	
		}
		
		//权益类 、 FOF状态判断（运作期）
		if (productType.equals(retMap.get(Const.FUND_TYPE_07))
				||productType.equals(retMap.get(Const.FUND_TYPE_09))){
			Map<String,Object> qryMap = Maps.newHashMap();
			qryMap.put("channelCode", channelCode);
			qryMap.put("productCode", productCode);
			qryMap.put("batchNo", batchNo);
			qryMap.put("tradeDate", tradeDate);
			qryMap.put("checkState", Const.BUSINESS_STUTAS_01);
			qryMap.put("openDayType", Const.OP_TYPE_000);
			
			//判断传入日期所在月份的非开放日天数
			int countDays = productOpenDayService.selectMonthOpenDayCount(qryMap);
			
			//如果当月非工作日为0，则返回错误信息，前台提示查看非工作日配置
			if(countDays<=0){
				LOGGER.info(productCode+"在"+tradeDate.substring(4, 6)+"月没有找到非工作日记录，请查看产品非工作日配置");
				retMap=ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00020,productCode+ExceptionConStants.retMsg_S00020);
				return retMap;
			}
			//判断该处理日期是不是产品工作日
			boolean flag = productOpenDayService.checkTransDateIsOpenDay(qryMap);
			if(flag){
				//判断是否超额
				resultMap = gtLimit(fundMarketCustom);
				if (ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
					// 处理日期是产品工作日且超额 状态为5：停止申购
					fundStatus = Const.CANNOT_PURCHASE;
					LOGGER.info(fundMarketCustom.getPiChannelCode()+"产品是结转日且超额,基金状态为"+fundStatus);
				}else{
					//是产品工作日且不超额,基金状态为可申购赎回
					fundStatus = Const.CAN_PURCHASE_REDEMPTION;
				}
			}else{
				//不是产品工作日,基金状态为不可申购赎回
				fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
			}
		}
		
		//美月盈多币种
		if (productType.equals(retMap.get(Const.FUND_TYPE_08))){
			Map<String,Object> qryMap = Maps.newHashMap();
			qryMap.put("channelCode", channelCode);
			qryMap.put("productCode", productCode);
			qryMap.put("batchNo", batchNo);
			qryMap.put("tradeDate", tradeDate);
			qryMap.put("checkState", Const.BUSINESS_STUTAS_01);
			
			resultMap = gtLimit(fundMarketCustom);
			//判断该处理日期是不是产品工作日
			String openDayType= productOpenDayService.checkOpenDayType(qryMap);
			
			if("".equals(openDayType)|| null==openDayType){
				//该日期产品开放日表中既不是申购日期也不是赎回日期，产品状态不可申赎
				fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
			}else{
				if(openDayType.contains(Const.BUSINESS_022)&&!openDayType.contains(Const.BUSINESS_024)){
					//处理日期是申购日期但超额，产品状态不可申赎
					if (ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
						fundStatus = Const.CANNOT_PURCHASE_REDEMPTION;
					}else{
						//该日期产品开放日表中是申购日期不是赎回日期不超额，产品状态停止赎回
						fundStatus = Const.CANNOT_REDEMPTION;	
					}
				}
				
				if(openDayType.contains(Const.BUSINESS_024)&&!openDayType.contains(Const.BUSINESS_022)){
					//该日期产品开放日表中不是申购日期是赎回日期，产品状态停止申购
					fundStatus = Const.CANNOT_PURCHASE;
				}
				
				if(openDayType.contains(Const.BUSINESS_022)&&openDayType.contains(Const.BUSINESS_024)){
					//该日期产品开放日表中既是申购日期又是赎回日期但超额，产品状态为停止赎回
					if (ExceptionConStants.retCode_true.equals(resultMap.get("retCode"))) {
						fundStatus = Const.CANNOT_REDEMPTION;
					}else{
						//该日期产品开放日表中既是申购日期又是赎回日期不超额，产品状态可申赎
						fundStatus = Const.CAN_PURCHASE_REDEMPTION;	
					}
				}
			}
			
			
			
		}
		retMap.put("FUNDSTATUS", fundStatus);
		retMap.put("retCode", ExceptionConStants.retCode_0000);
		return retMap;
	}
	/**
	 * 判断丰利B、D的结转日
	 * 
	 * @param tradeDate
	 *            交易日期
	 * @param marketCode
	 *            产品类型
	 * @return
	 */
	public String getForwardDay(String tradeDate, String marketCode) {
		closeDateService = (CloseDateService) SpringUtils
				.getBean(CloseDateService.class);
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		
		exchangeProcessorService = (ExchangeProcessorService)SpringUtils.getBean(ExchangeProcessorService.class);

		//丰利B、D的结转日（15号）
		String forwardDayBD= sysDictService.findDictVo("forwardDayBD");
		forwardDayBD=forwardDayBD.length()==1?"0"+forwardDayBD:forwardDayBD;
		String formatDay = tradeDate.substring(0, 6) +forwardDayBD ;
		String retDate = "";
		CloseDate closeDate = new CloseDate();
		closeDate.setCdMarketCode(marketCode);
		closeDate.setCdCloseDate(formatDay);
		int flag = closeDateService.selectByMarketCodeAndcdCloseDate(closeDate);
		if (flag == 0) {
			retDate = formatDay;
		} else {
			retDate = exchangeProcessorService.getNextWorkDay(formatDay, marketCode);
		}
		LOGGER.info("丰利产品的结转日期为"+retDate);
		return retDate;
	}

	/**
	 * 判断是否超累计限额
	 * 
	 * @param fundMarketCustom
	 * @return true 超累计限额 false 不超累计限额
	 */
	@Override
	public Map<String,Object> gtLimit(FundMarketCustom fundMarketCustom) {
		Map<String, Object> retMap = Maps.newHashMap();
		BigDecimal sunPosition = new BigDecimal(fundMarketCustom.getSumRemainshares()==null?"0":fundMarketCustom.getSumRemainshares());
		BigDecimal maxAmountRaised = fundMarketCustom
				.getPiMaxAmountRaised()==null?new BigDecimal("0"):fundMarketCustom
						.getPiMaxAmountRaised();
		
		// 持仓大于等于限额 超额 返回true
		if (sunPosition.compareTo(maxAmountRaised) != -1) {
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_true);
		} else {
			// 持仓小于限额 不超额 返回false
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_false);
		}
		return retMap;
	}

	/**
	 * 判断红宝石七天的结转日
	 * 
	 * @param tradeDate
	 *            交易日期
	 * @param marketCode
	 *            市场编码
	 * @return
	 */
	public String getQuarterForwardDay(String tradeDate, String marketCode) {
		DateFormat df = new SimpleDateFormat(Const.DTAE_FORMAT_YYYYMMDD);
		String retDay = "";
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		//丰利B、D的结转日（15号）
		String forwardDayH= sysDictService.findDictVo("forwardDayH");
		forwardDayH=forwardDayH.length()==1?"0"+forwardDayH:forwardDayH;
		try {
			String formatDay = df.format(
					DateUtils.getLastDateOfSeason(df.parse(tradeDate)))
					.substring(0, 6)
					+ forwardDayH;
			//retDay = getForwardDay(formatDay, marketCode);
			CloseDate closeDate = new CloseDate();
			closeDate.setCdMarketCode(marketCode);
			closeDate.setCdCloseDate(formatDay);
			int flag = closeDateService.selectByMarketCodeAndcdCloseDate(closeDate);
			if (flag == 0) {
				retDay = formatDay;
			} else {
				retDay = exchangeProcessorService.getNextWorkDay(formatDay, marketCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("红宝石七天产品的结转日为"+retDay);
		return retDay;
	}

	/**
	 * 渠道产品信息插入基金行情处理过程信息表
	 * 
	 * @param inputMap
	 *            交易日期 List<FundMarketCustom> 渠道产品信息数据集合
	 * @return tradeDate 交易日期 List<FundMarketCustom> outList 入库不成功的数据
	 */
	@Override
	public Map<String, Object> insertFundQuotTmp(Map<String, Object> inputMap) {
		LOGGER.info("处理完成的渠道产品信息入库基金行情处理临时信息表步骤开始处理");
		Map<String, Object> outputMap = Maps.newHashMap();
		String tradeDate = MapUtils.getString(inputMap, "TRADEDATE","");
		String channelCode = MapUtils.getString(inputMap, "CHANNELCODE","");

		LOGGER.info("已清理所有基金行情处理过程信息表数据");
		List<FundMarketCustom> list = (List<FundMarketCustom>) inputMap.get("RETVALUE");
		List<FundQuotTmp> quotList = Lists.newArrayList();
		String isTradeDateFlag = (String) inputMap.get("ISTRADEDATEFLAG");
//		boolean caninsertFundQuotTmp = true;
		LOGGER.info("已拿到判断完非交易日的数据信息");
		List<FundMarketCustom> retList = Lists.newArrayList();
		if (Const.BUSINESS_STUTAS_01.equals(isTradeDateFlag)) {// 可生成行情的数据
			for (FundMarketCustom fundMarketCustom : list) {
				// 根据交易日期获取状态
				 outputMap = getStatusBasedOnDate(tradeDate,fundMarketCustom);
				 	if(!ExceptionConStants.retCode_0000.equals(outputMap.get("retCode")))return outputMap;
				 String fundStatus = MapUtils.getString(outputMap, "FUNDSTATUS","");
				 String productType = fundMarketCustom.getPiProductType();
				 String taProductCode = fundMarketCustom.getCpTaProductCode();
				 String sumRemainshares = fundMarketCustom.getSumRemainshares();//持仓
				 fundMarketMapper = SpringUtils.getBean(FundMarketMapper.class);
                 
				 //非货币类要根据基金状态取出相应的产品信息 发行状态：日期要处于募集期； 封闭状态 日期要大于募集结束日 ，小于产品到期日
				 if(!Const.FUND_TYPE_01.equals(productType)&&!Const.FUND_TYPE_02.equals(productType)&&!Const.FUND_TYPE_03.equals(productType)){
					 String ipoBeginDate = fundMarketCustom.getPiIpoBeginDate();
					 fundMarketCustom.setPiIpoBeginDate(tradeDate);
					 if(Const.PUBLISH.equals(fundStatus)){
						 try {
							fundMarketCustom =  fundMarketMapper.selectfundMarketIpo(fundMarketCustom);
						} catch (Exception e) {
							outputMap.put("retCode", ExceptionConStants.retCode_ipo);
							outputMap.put("retMsg", ExceptionConStants.retMsg_ipo);
						    return outputMap;
						}
					 }else if(Const.FUND_SEALING.equals(fundStatus)
							 &&(Long.parseLong(tradeDate)>Long.parseLong(fundMarketCustom.getPiIpoEndDate()))){
						 try {
							fundMarketCustom  =  fundMarketMapper.selectfundMarketPro(fundMarketCustom);
						} catch (Exception e) {
							outputMap.put("retCode", ExceptionConStants.retCode_pro);
							outputMap.put("retMsg", ExceptionConStants.retMsg_pro);
						    return outputMap;
						}
					 }
					 fundMarketCustom.setCpTaProductCode(taProductCode);
					 fundMarketCustom.setSumRemainshares(sumRemainshares);
					 fundMarketCustom.setPiIpoBeginDate(ipoBeginDate);
				 }
				 retList.add(fundMarketCustom);
				// 按要求创建一个临时类
				outputMap = createFundQuotTmp(fundMarketCustom, fundStatus,
						outputMap, tradeDate);
				FundQuotTmp fundQuotTmp = (FundQuotTmp) outputMap.get("FUNDQUOTTMP");
				//详情信息明细时候修改行情状态要重新生成将修改完后状态赋给临时表
				fundMarketCfmService = (FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
				FundMarketCfm fundMarketCfm = new FundMarketCfm();
				fundMarketCfm.setFmChannelCode(channelCode);
				fundMarketCfm.setFmFundCode(fundMarketCustom.getPiChannelProductCode());
				fundMarketCfm.setFmBusinessDate(tradeDate);
				fundMarketCfm = fundMarketCfmService.selectFundMarketCfmInfo(fundMarketCfm);
				if(fundMarketCfm!=null){
					fundQuotTmp.setFqFundStatus(fundMarketCfm.getFmFundStatus());
				}
				quotList.add(fundQuotTmp);
			}
			if(retList.size()==list.size()&&quotList.size()==list.size()){
				insertSelective(quotList);
			}else{
				inputMap.put("retCode", ExceptionConStants.retCode_S00011);
				inputMap.put("retMsg", ExceptionConStants.retCode_S00011);
				return inputMap;
			}
			
		}
		
		outputMap.put("CHANNELCODE", channelCode);
		outputMap.put("TRADEDATE", tradeDate);
		outputMap.put("RETVALUE", retList);
		LOGGER.info("第三步插入临时表的输出参数为日期:"+tradeDate+"渠道:"+channelCode+"数据量"+retList.size()+"条");
		LOGGER.info("处理完成的渠道产品信息入库基金行情处理临时信息表步骤处理完毕");
		return outputMap;

	}

	public Map<String, Object> createFundQuotTmp(FundMarketCustom fundMarketCustom, String fundStatus,
			Map<String, Object> outputMap, String tradeDate) {
		FundQuotTmp fundQuotTmp = new FundQuotTmp();
		if (Const.FUND_TERMINATION.equals(fundStatus)) {
			outputMap = SysDictServiceImpl.findDictVoByDictType(Const.FUND_SEND_END_DATE);
			int fundSendEndDate = Integer.parseInt((String) outputMap.get("dictCode"));
//			String sysDate = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
			List betweenDatesList = DateUtils.getBetweenDates(
			        DateUtils.getDateBYYYYMMDD(fundMarketCustom.getPiProEndDate()),
					DateUtils.getDateBYYYYMMDD(tradeDate));
			if (betweenDatesList.size() >= fundSendEndDate) {
				fundMarketCustom.setPiValidFlag(Const.BUSINESS_STUTAS_02);
				fundMarketService = (FundMarketService) SpringUtils.getBean(FundMarketService.class);
				List<FundMarketCustom> FundMarketCustomList = new ArrayList<FundMarketCustom>();
				FundMarketCustomList.add(fundMarketCustom);
				int updateCount = fundMarketService.updateCodeAndType(FundMarketCustomList);
				fundQuotTmp.setFqValidFlag("00");
			} else {
				fundQuotTmp.setFqValidFlag("01");
			}

		} else {
			fundQuotTmp.setFqValidFlag("01");
		}
		fundQuotTmp.setFqChannelCode(fundMarketCustom.getCiChannelCode());
		fundQuotTmp.setFqFundCode(fundMarketCustom.getPiChannelProductCode());
		fundQuotTmp.setFqFundName(fundMarketCustom.getPiChannelProductName());
		fundQuotTmp.setFqBatchNo(fundMarketCustom.getPiBatchNumber());
		fundQuotTmp.setFqTotalFundVol(new BigDecimal(fundMarketCustom
				.getPiProductTotalShare()==null?"0":fundMarketCustom
						.getPiProductTotalShare()));
		fundQuotTmp.setFqFundStatus(fundStatus);
		outputMap = SysDictServiceImpl.findDictVoByTypeAndName(
				Const.FUND_TYPE_NAME, Const.FUND_TYPE_05);
		if (!(ExceptionConStants.retCode_0000).equals(outputMap.get("retCode")))
			return outputMap;
		String dictCode = (String) outputMap.get("dictCode");
		if (dictCode.equals(fundMarketCustom.getPiProductType())) {
			fundQuotTmp.setFqConvertStatus(Const.CONVERT_STATUS_03);
		} else {
			fundQuotTmp.setFqConvertStatus(Const.CONVERT_STATUS_00);
		}
		fundQuotTmp.setFqCurrencyType(fundMarketCustom.getPiCurrencyType());
		fundQuotTmp.setFqIpostartDate(fundMarketCustom.getPiIpoBeginDate());
		fundQuotTmp.setFqIpoendDate(fundMarketCustom.getPiIpoEndDate());
		fundQuotTmp.setFqIndiAppSubsAmount(fundMarketCustom
				.getPiIndiMinAppBidsAmt());
		fundQuotTmp.setFqMinSubsAmountByIndi(fundMarketCustom
				.getPiInstMinBidsAmt());
		fundQuotTmp.setFqMinBidsAmountByIndi(fundMarketCustom
				.getPiIndiMinBidsAmt());
		fundQuotTmp.setFqMinBidsAmountByInst(fundMarketCustom
				.getPiInstMinBidsAmt());
		fundQuotTmp.setFqMinAppBidsAmountByIndi(fundMarketCustom
				.getPiIndiMinAppBidsAmt());
		fundQuotTmp.setFqMinAppBidsAmountByInst(fundMarketCustom
				.getPiInstMinAppBidsAmt());
		fundQuotTmp.setFqDataGenerate(DateUtils.getOracleSysDate());
		fundQuotTmp.setFqExchangeDate(tradeDate);
		fundQuotTmp.setFqSztSendPath(fundMarketCustom.getCiSztRecvPath());
		fundQuotTmp.setFqCsdcVer(fundMarketCustom.getCiCsdcVer());
		fundQuotTmp.setFqMaxAmountRaised(fundMarketCustom.getPiMaxAmountRaised().toString());
		fundQuotTmp.setFqProductType(fundMarketCustom.getPiProductType());
		fundQuotTmp.setFqTaProductCode(fundMarketCustom.getCpTaProductCode());
		fundQuotTmp.setFqMinAccountBalance(fundMarketCustom.getPiIndiMinVol().toString());
		fundQuotTmp.setFqRemainShares(fundMarketCustom.getSumRemainshares()==null?"0":fundMarketCustom.getSumRemainshares());
		fundQuotTmp.setFqFundSize(fundMarketCustom.getPiMaxAmountRaised()==null?new BigDecimal("0"):fundMarketCustom.getPiMaxAmountRaised());
		fundQuotTmp.setFqProSetupDate(fundMarketCustom.getPiProSetupDate()==null?"":fundMarketCustom.getPiProSetupDate());
		outputMap.put("FUNDQUOTTMP", fundQuotTmp);
		return outputMap;
	}

	
	public int insertSelective(List<FundQuotTmp> listQuot) {
		fundQuotTmpMapper = (FundQuotTmpMapper) SpringUtils
				.getBean(FundQuotTmpMapper.class);
		return fundQuotTmpMapper.insertSelective(listQuot);
	}

	/**
	 * 获取交易日的基金行情临时信息
	 * 
	 * @param inputMaps
	 *            <String, Object>
	 * @return Map<String, Object> 返回转换后的数据集合
	 */
	@Override
	public Map<String, Object> selectAllFundQuotTmp(
			Map<String, Object> inputMaps) {
		LOGGER.info("获取交易日的基金行情临时信息步骤开始处理");
		fundQuotTmpMapper = (FundQuotTmpMapper) SpringUtils.getBean(FundQuotTmpMapper.class);
		String channelCode = MapUtils.getString(inputMaps, "CHANNELCODE","");
		String tradeDate = MapUtils.getString(inputMaps, "TRADEDATE","");
		List<Map<String, Object>> dataList = fundQuotTmpMapper.selectAllFundQuotTmp(channelCode, tradeDate);
		LOGGER.info("");
		
		Map<String, Object> outputMap = Maps.newHashMap();
		outputMap.put("TRADEDATE", tradeDate);
		outputMap.put("CHANNELCODE", channelCode);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		fileInterfaceFieldService = (FileInterfaceFieldService) SpringUtils.getBean(FileInterfaceFieldService.class);
		LOGGER.info("交易日的基金行情临时信息已获取");
		List<InterfaceColInfo> colOrderList = Lists.newArrayList();
		for (Map<String, Object> fundQuotTmp : dataList) {
			Map<String,Object> infoMap = Maps.newHashMap();
			infoMap.put("productCode",MapUtils.getString(fundQuotTmp, "FUNDCODE", ""));
			infoMap.put("batchNumber",MapUtils.getString(fundQuotTmp, "BATCHNO", ""));
			infoMap.put("channelCode", MapUtils.getString(fundQuotTmp, "CHANNELCODE", ""));
			
			parProductInfoService = (ParProductInfoService) SpringUtils.getBean(ParProductInfoService.class);
			PProductInfo info = parProductInfoService.selectProductList(infoMap);
			
			Map<String, Object> map = Maps.newHashMap();
			Map<String, Object> paramMap = Maps.newHashMap();
			paramMap.put("ICINTERFACECODE","7"+MapUtils.getString(fundQuotTmp, "CSDCVER"));
			
			if(colOrderList.size()==0){
				colOrderList = fileInterfaceFieldService.selectFileInterfaceFieldOrder(paramMap);
			} 
			
			for (InterfaceColInfo interfaceColInfo : colOrderList) {
				//特殊处理的字段
				if (fundQuotTmp.containsKey( interfaceColInfo.getIcColName().toString().toUpperCase())) {
					map.put(interfaceColInfo.getIcColName().toUpperCase(), fundQuotTmp.get( interfaceColInfo.getIcColName().toUpperCase()) == null ? "": fundQuotTmp.get( interfaceColInfo.getIcColName().toUpperCase()));
				} else {
					map.put(interfaceColInfo.getIcColName().toUpperCase(), StringUtils.getStringValue(interfaceColInfo.getIcColValue()));
				}
				if(Const.specialDealStrings.contains(interfaceColInfo.getIcColName().toUpperCase())){
					String value = specialDeal(interfaceColInfo.getIcColName().toUpperCase(), info);
					map.put(interfaceColInfo.getIcColName().toUpperCase(), value);
				}
			}

			map.put("SZTRECVPATH", StringUtils.getStringValue(MapUtils.getString(fundQuotTmp, "SZTSENDPATH", "")));
			map.put("CSDCVER", StringUtils.getStringValue(MapUtils.getString(fundQuotTmp, "CSDCVER", "")));
			map.put("CHANNELCODE", StringUtils.getStringValue(MapUtils.getString(fundQuotTmp, "CHANNELCODE", "")));
			map.put("TRADEDATE", StringUtils.getStringValue(MapUtils.getString(fundQuotTmp, "EXCHANGEDATE", "")));
			map.put("BATCHNUMBER", StringUtils.getStringValue(MapUtils.getString(fundQuotTmp, "BATCHNO", "")));
			map.put("PRODUCTCODE", MapUtils.getString(fundQuotTmp, "FUNDCODE", ""));
			map.put("BATCHNUMBER",MapUtils.getString(fundQuotTmp, "BATCHNO", ""));
			map.put("PRODUCTTYPE", MapUtils.getString(fundQuotTmp, "PRODUCTTYPE", ""));
			map.put("TAPRODUCTCODE", MapUtils.getString(fundQuotTmp, "TAPRODUCTCODE", ""));//TA端产品代码
			map.put("REMAINSHARES", MapUtils.getString(fundQuotTmp, "REMAINSHARES", ""));//持仓
			map.put("MAXAMOUNTRAISED", MapUtils.getString(fundQuotTmp, "MAXAMOUNTRAISED", "0"));//最高募集金额
			map.put("PROSETUPDATE", MapUtils.getString(fundQuotTmp, "PROSETUPDATE", ""));//产品成立日


			if (!MapUtils.getString(fundQuotTmp, "VALIDFLAG", "").equals(Const.BUSINESS_STUTAS_00)) {
				mapList.add(map);
			}
		}
		outputMap.put("CHANNELCODE", inputMaps.get("CHANNELCODE"));
		outputMap.putAll(ExceptionConStants.getRetObject(
				ExceptionConStants.retCode_0000, mapList));
		LOGGER.info("获取交易日的基金行情临时信息步骤处理完毕");
		LOGGER.info("第四步获取行情数据的输出参数为日期:"+tradeDate+"渠道:"+channelCode+"数据量:"+mapList.size()+"条");
		return outputMap;
	}
	
	
	public String specialDeal(String key,PProductInfo info){
		String value = "";
		if("INSTAPPSUBSAMNT".equals(key)||"MINAPPBIDSAMOUNTBYINST".equals(key)){
			value = info.getPiInstMinAppBidsAmt()==null?"":info.getPiInstMinAppBidsAmt().toString();
		}else if("MINAMOUNTBYINST".equals(key)||"MINBIDSAMOUNTBYINST".equals(key)){
			value = info.getPiInstMinBidsAmt()==null?"":info.getPiInstMinBidsAmt().toString();
		}else if("INDIAPPSUBSAMOUNT".equals(key)||"MINAPPBIDSAMOUNTBYINDI".equals(key)){
			value = info.getPiIndiMinAppBidsAmt()==null?"":info.getPiIndiMinAppBidsAmt().toString();
		}else if("MINSUBSAMOUNTBYINDI".equals(key)||"MINBIDSAMOUNTBYINDI".equals(key)){
			value = info.getPiIndiMinBidsAmt()==null?"":info.getPiIndiMinBidsAmt().toString();
		}else if("UNITSUBSAMOUNTBYINDI".equals(key)){
			value = info.getPiIndiBidsDiffAmt()==null?"":info.getPiIndiBidsDiffAmt().toString();
		}else if("UNITSUBSAMOUNTBYINST".equals(key)){
			value = info.getPiInstBidsDiffAmt()==null?"":info.getPiInstBidsDiffAmt().toString();
		}
		return value;
	}

}