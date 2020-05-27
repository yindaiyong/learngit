package com.sams.batchfile.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sams.custom.mapper.*;
import com.sams.custom.model.FundDividendCfm;
import com.sams.custom.model.FundVolDetailCfm;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FundAccountReconCfmService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.model.result.SysDictVo;
import com.sams.sys.service.SysDictService;
/**
 * @ClassName:  FundAccountReconCfmServiceImpl   
 * @author: yindy
 * @date:   2020年3月19日 下午3:55:48   
 *
 */
@Service
public class FundAccountReconCfmServiceImpl implements FundAccountReconCfmService {
	private static Logger LOGGER = LoggerFactory.getLogger(FundAccountReconCfmServiceImpl.class);
	@Autowired
	private FundAccountReconCfmMapper fundAccountReconCfmMapper;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper;
	
	@Autowired
	private ExchangeReqTmpMapper exchangeReqTmpMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired 
	private SysDictService sysDictService;
	
	@Autowired
	private FundVolDetailCfmMapper fundVolDetailCfmMapper ;

	@Override
	public int deleteByChannelCodeBusinessDateCfm(String channelCode,
			String businessDate) {
		return fundAccountReconCfmMapper.deleteByChannelCodeBusinessDateCfm(channelCode, businessDate);
	}

	@Override
	public int insertByBatchCfm(List<Map<String, Object>> attachmentTables) {
		return fundAccountReconCfmMapper.insertByBatchCfm(attachmentTables);
	}

	@Override
	public int selectByChannelCodeBusinessDateCountCfm(String channelCode,
			String businessDate) {
		return fundAccountReconCfmMapper.selectByChannelCodeBusinessDateCountCfm(channelCode, businessDate);
	}

	/**
	 * 保存05对账确认表数据
	 * @Title: saveDealCfMData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  RETVALUE 数据记录集
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> saveDealCfMData(Map<String, Object> map) {
		LOGGER.info("05对账信息入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String businessDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectByChannelCodeBusinessDateCountCfm(channelCode, businessDate);
		LOGGER.info(channelCode+"05对账信息查询存在的数据，渠道为："+channelCode+",交易日期为："+businessDate+",数据条数："+selectCount);
		int deleteCount=deleteByChannelCodeBusinessDateCfm(channelCode, businessDate);
		LOGGER.info(channelCode+"05对账信息清理存在的数据，渠道为："+channelCode+",交易日期为："+businessDate+",数据条数："+deleteCount);
		int insertCount=0;
		List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
		if(selectCount==deleteCount&&list.size()>0){
			int size = list.size();
			int num = 100;
			if(size > num ){
				for (int i = 0; i < size ; i++) {
					newList.add(list.get(i));
					if (num == newList.size() || i == size-1) {  //载体list达到要求,进行批量操作
	                    //调用批量插入
						insertCount+=insertByBatchCfm(newList);
						LOGGER.info(channelCode+"插入对账数据："+insertCount);
	                    newList.clear();//每次批量操作后,情况载体list,等待下次的数据填入
	                }
				}
			}else{
				insertCount+=insertByBatchCfm(list);
			}
			
			LOGGER.info(channelCode+"保存05对账信息数据入临时表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info(channelCode+"05对账信息入库正式表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info(channelCode+"05对账信息入库正式表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00030);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00031);
			LOGGER.info(channelCode+"05对账信息入库正式表失败,原因为："+ExceptionConStants.retCode_T00031);
		}
		LOGGER.info(channelCode+"05对账信息入库结束处理");
		return retMap;
	}

	/**
	 * 对账信息数据基础规则校验
	 * @Title: checkFundAccountReconApplyBasicData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> checkFundAccountReconApplyBasicData(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		try {
		
			sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
			fundAccountReconCfmMapper=(FundAccountReconCfmMapper) SpringUtils.getBean(FundAccountReconCfmMapper.class);
			contractInfoMapper=(ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
			exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			boolean falg04=true;
			boolean falg01=true;
			boolean falg00=true;
			
			List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
			String channelCode=(String)map.get("CHANNELCODE");//渠道编号
			String transDate=(String)map.get("TRANSDATE");//交易日期
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");//渠道基本信息
			String lastDate=exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate),channelInfo.getCiMarketCode());
			
			LOGGER.info(channelCode+"处理日期为"+transDate+"的"+channelCode+"渠道,对账信息数据处理开始");
			
			//查询该渠道下所拥有产品 配置的交易类型
			List<SysDictVo>  sysDictList= sysDictService.findDealTypeByChannelCode(channelCode);
			LOGGER.info("对账查询"+channelCode+"配置的交易类型的数量为："+(sysDictList == null ? 0 : sysDictList.size()));
			for(SysDictVo sysDictVo:sysDictList){
				String dictType=sysDictVo.getDictType().substring(0,1); //产品类型
				//获取代销端产品数据集
				List<String> fundCodes = new ArrayList<String>() ;
				
				//丰利F  yindy 20200227   对账成立日当天出
				if(Const.FUND_TYPE_00.equals(dictType)){
					if(falg00){
						falg00=false;
						//查询该渠道下丰利F在用产品
						Map<String,Object> query = Maps.newHashMap();
						query.put("CHANNELCODE", channelCode);
						query.put("TRANSDATE", transDate);
						query.put("FUNDTYPE", dictType);
						fundCodes = contractInfoMapper.queryCurrencyFFund(query);
						LOGGER.info(channelCode+"通过"+(query == null ? null : query.toString())+"获取丰利F类对账产品数量为："+(fundCodes == null ? 0:fundCodes.size()));
						if(fundCodes == null || fundCodes.size() == 0 ) {continue;}
						int count = exchangeReqTmpMapper.selectCurrencyFTransCount(query);
						LOGGER.info(channelCode+"通过"+(query == null ? null : query.toString())+"获取丰利F类对账确认数据的数量为："+count);
						if(count==0) {continue;}
						//查询出对账数据
						LOGGER.info(channelCode+"开始获取丰利F类对账确认数据");
						List<Map<String,Object>> currencyFList = fundAccountReconCfmMapper.selectCurrencyFIncome(query);
						LOGGER.info(channelCode+"通过"+(query == null ? null : query.toString())+"获取丰利F类对账确认数据结束,获取的数据数量为:"+(currencyFList == null ? 0:currencyFList.size()));
						if(count > 0 && currencyFList.size() > 0){
							retList.addAll(currencyFList);
						}else {
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00115));        
							return retMap;
						}
					}
				}
				
				// 固收 T+N 封闭净值 权益 FOF 多币种 
				if(Const.FUND_TYPE_456789.contains(dictType)){
					if(falg04){
						falg04=false;
						Map<String,Object> inputMap=Maps.newHashMap();
						inputMap.put("CHANNELCODE", channelCode);
						inputMap.put("TRANSDATE", transDate);
						inputMap.put("DICTFLAG", dictType);
						inputMap.put("DATEFLAG", Const.BUSINESS_STUTAS_01);
						//查出渠道下固收、T+N、封闭净值类所对应的TA产品代码 
						fundCodes= contractInfoMapper.selectSalesFund(inputMap);
						LOGGER.info(channelCode+"通过"+(inputMap == null ? null : inputMap.toString())+"获取非货币类对账产品数量为："+(fundCodes == null ? 0:fundCodes.size()));
						if(fundCodes == null || fundCodes.size() == 0 ) {continue;}
						List<String> ids=Arrays.asList(Const.BUSINESS_020,Const.BUSINESS_022);
						inputMap.put("ids", ids);
						inputMap.put("FIXEDINCOME", "01");
						//通过查出的TA产品代码查询所有的交易信息总数
						//修改函数
						inputMap.put("DATEFLAG", "01");
						int count = exchangeReqTmpMapper.selectCheckBusinessCodeCount(inputMap);
						LOGGER.info(channelCode+"通过"+(inputMap == null ? null : inputMap.toString())+"获取非货币类对账确认数据的数量为："+count);
						if(count==0) {continue;}
						//查询出对账数据
						LOGGER.info(channelCode+"开始获取非货币类对账确认数据");
						List<Map<String,Object>> fixedIncomeList=fundAccountReconCfmMapper.selectFixedIncome(inputMap);
						LOGGER.info(channelCode+"通过"+(inputMap == null ? null : inputMap.toString())+"获取非货币类对账确认数据结束,获取的数据数量为:"+(fixedIncomeList == null ? 0:fixedIncomeList.size()));
						if(count > 0 && fixedIncomeList.size() > 0){
							retList.addAll(fixedIncomeList);
						}else {
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00088));
							return retMap;
						}
					}
				}
				//丰利b 丰利d 红宝石七天
				if(Const.FUND_TYPE_123.contains(dictType)){
					if(falg01){
						falg01=false;
						Map<String,Object> inputMap=Maps.newHashMap();
						inputMap.put("CHANNELCODE", channelCode);
						inputMap.put("TRANSDATE", transDate);
						inputMap.put("FIXEDINCOME", "00");
						fundCodes= contractInfoMapper.selectSalesFund123(inputMap);
						LOGGER.info(channelCode+"通过"+(inputMap == null ? null : inputMap.toString())+"获取货币类对账产品数量为："+(fundCodes == null ? 0:fundCodes.size()));
						if(fundCodes==null) {continue;}
						inputMap.put("FUNDCODES", fundCodes);
						List<String> ids=Arrays.asList(Const.BUSINESS_022,Const.BUSINESS_024);
						inputMap.put("ids", ids);
						int count = exchangeReqTmpMapper.selectCheckBusinessCodeCount(inputMap);
						LOGGER.info(channelCode+"通过"+(inputMap == null ? null : inputMap.toString())+"获取货币类对账确认数据的数量为："+count);
						if(count==0) {continue;}
						LOGGER.info(channelCode+"开始获取货币类对账确认数据");
						List<Map<String,Object>> cashIncomeList= fundAccountReconCfmMapper.selectCashIncome(inputMap);
						//添加份额数据校验
	                    String s = checkUndistributeMonetaryIncome(cashIncomeList,lastDate,channelCode,transDate);
	                    if(!ExceptionConStants.retCode_0000.equals(s)){
	                        retMap.putAll(ExceptionConStants.getRetObject(s));
	                        return retMap;
	                    }
	                    LOGGER.info(channelCode+"通过"+(inputMap == null ? null : inputMap.toString())+"获取货币类对账确认数据结束,获取的数据数量为:"+(cashIncomeList == null ? 0:cashIncomeList.size()));
						if(count>0&&cashIncomeList.size()>0){
							retList.addAll(cashIncomeList);
						}else{
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00102));
							return retMap;
						}
					}
				}
				
			}
			
			LOGGER.info(channelCode+"对账信息插入正式表处理开始");		
			retMap.putAll(map);
			retMap.put("RETVALUE", retList);
			if(retList != null && retList.size() >0){
				retMap=saveDealCfMData(retMap);
			}
			
			//26文件数据取值方法 处理
			FundAccountReconCfmService fundAccountReconCfmService = (FundAccountReconCfmService)SpringUtils.getBean(FundAccountReconCfmService.class);
			Map<String, Object> stringObjectMap = fundAccountReconCfmService.checkFundShareDetailCfm(map);
			if(!ExceptionConStants.retCode_0000.equals(stringObjectMap.get("retCode"))) {return stringObjectMap;}
	
			Map<String,Object> checkInputMap=Maps.newHashMap();
			checkInputMap.put("CHANNELCODE", channelCode);
			checkInputMap.put("TRANSDATE", transDate);
			checkInputMap.put("LASTDATE", lastDate);
			//非货币类
			int checkFixedIncome = fundAccountReconCfmMapper.selectCheckFixedIncome(checkInputMap);
			if(checkFixedIncome>0){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00087));
				return retMap;
			}
			 //货币类
			int CheckCashIncome = fundAccountReconCfmMapper.selectCheckCashIncome(checkInputMap);
			if(CheckCashIncome>0){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00089));
				return retMap;
			}
			//权益/FOF类
			int checkStockIncome = fundAccountReconCfmMapper.queryCheckStockIncome(checkInputMap);
			if(checkStockIncome>0){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00103));
				return retMap;
			}
			
			// 多币种对账校验规则
			int checkMultiIncome = fundAccountReconCfmMapper.queryCheckMultiIncome(checkInputMap);
			if(checkMultiIncome >0 ){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00112));
				return retMap;
			}
			//丰利F对账校验  20200311 yindy
			int checkMoneyFIncome = fundAccountReconCfmMapper.queryCheckMoneyFIncome(checkInputMap);
			if(checkMoneyFIncome >0 ){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00118));
				return retMap;
			}
			
			//校验浦发的货币类对账中给到的特殊字段每日新增收益的正确性  20200525 
			/**
			 * 浦发当日分红数据  + 浦发当日未付收益金额数据 - 浦发上一个工作日未付收益金额数据  - 浦发取到新增收益 = 0 
			 */
			/*if("TTTNETB3".equals(channelCode)){
				int checkNewIncome = fundAccountReconCfmMapper.queryCheckNewIncome(checkInputMap);
				if(checkNewIncome > 0 ){
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00123));
					return retMap;
				}
			}*/
			
			retMap.putAll(map);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		
			LOGGER.info(channelCode+"对账信息插入正式表处理结束");	
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("对账确认错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkFundAccountReconApplyBasicData方法,对账确认异常,请联系管理员!");
			return retMap;
		}
	}

	/**
	 * @Description 26文件份额确认数据
	 * @Author weijunjie
	 * @Date 2020/1/7 10:20
	 **/
	@Override
	public Map<String, Object> checkFundShareDetailCfm(Map<String, Object> map){
		Map<String,Object> retMap=Maps.newHashMap();

		String channelCode=(String)map.get("CHANNELCODE");//渠道编号
		String transDate=(String)map.get("TRANSDATE");//交易日期
		PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");//渠道基本信息
        if(StringUtils.isBlank(channelInfo.getCiVolDetailType())){
            LOGGER.info("渠道{}下不需要份额确认数据，直接返回成功", channelCode);
            retMap.putAll(map);
            retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
            return retMap;
        }
        exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
        String lastDate=exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate),channelInfo.getCiMarketCode());
        String nextDate=exchangeProcessorService.getNextWorkDay(DateUtils.getNextDay(transDate,"1"),channelInfo.getCiMarketCode());
		//获取对应产品类型在TA视图中所有符合条件的数据
		String[] productTypes = channelInfo.getCiVolDetailType().split(",");
        FundVolDetailCfmMapper fundVolDetailCfmMapper = (FundVolDetailCfmMapper)SpringUtils.getBean(FundVolDetailCfmMapper.class);
        List<FundVolDetailCfm> fundVolDetailList = fundVolDetailCfmMapper.
                getFundVolDetailFromTA(channelCode,transDate, productTypes);
        
        if(fundVolDetailList.size() == 0){
        	//前端界面勾选的需要有26文件的产品类型,有成立数据  必须有26数据  20200416 
            int count = fundVolDetailCfmMapper.querySetUpDataCount(channelCode,transDate, productTypes);
        	if (count > 0 ){
        		//有成立数据
        		retMap.putAll(map);
        		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00121));
                return retMap;
        	}
            LOGGER.info("渠道{}下产品类型{}查询TA视图获取的数据量为：{},做通过处理",
                    channelCode,JSONObject.toJSONString(productTypes),fundVolDetailList.size());
            retMap.putAll(map);
            retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
            return retMap;
        }

        LOGGER.info("渠道{}下产品类型{}查询TA视图获取的数据量为：{}",
                channelCode,JSONObject.toJSONString(productTypes),fundVolDetailList.size());
        //清空份额确认表数据表交易日数据
        fundVolDetailCfmMapper.deleteByTransDate(channelCode,transDate);
        //开始份额数据入库操作
        int oldCount = fundVolDetailList.size();
        List<List<FundVolDetailCfm>> lists = subBeanList( 50,fundVolDetailList);
        int newCount = 0;
        for(List<FundVolDetailCfm> list:lists){
            newCount +=fundVolDetailCfmMapper.insertByBatch(list);
        }
        if(newCount != oldCount){
        	LOGGER.info("渠道{}下产品类型{}的份额数据数据入库数据不一致-》总条数{}，成功条数{}",
                    channelCode,JSONObject.toJSONString(productTypes),oldCount,newCount);
            retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00108));
            return retMap;
        }
        
        /**20200424  更新表,给到实际的赎回日期
         * 我们去做可用基金份额值的校验时，由于有3笔数据的可赎回日期是20200426（我方TA端是工作日）会于今日解封冻结份额，
         * 当天解封的这几笔数据我们会去除掉，不做可用份额的校验，但是由于非工作日配置中券商市场的20200426日是非工作日导致这几笔数据没有被去除掉
         */
        updateRelAllowRedemptDate(channelCode,transDate,channelInfo.getCiMarketCode());
        //检验赎回后的可用份额
        // 20200402   该渠道，该产品，该客户的上一日可用份额的和-上一日赎回份额 = 今日可用份额的和（过滤掉可赎回日期是下一个工作日的记录）
        int checkRedeem = fundVolDetailCfmMapper.checkRedeemAvailableVol(channelCode,transDate,lastDate,nextDate);
        if(checkRedeem > 0 ){
        	LOGGER.info("渠道{}下产品类型{}的份额数据的可用分额有误,请检查！",channelCode,JSONObject.toJSONString(productTypes));
        	retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00119));
            return retMap;
        }
        //校验赎回确认后和追加/认购确认后的总份额
        // 20200402   该渠道，该产品，该客户的上一日总份额的和-赎回确认份额+122/130确认份额= 今日可用份额的和
        int checkRedeemCfm = fundVolDetailCfmMapper.checkRedeemCfmTotalVol(channelCode,transDate,lastDate);
        if(checkRedeemCfm > 0 ){
        	LOGGER.info("渠道{}下产品类型{}的份额数据的总分额有误,请检查！",channelCode,JSONObject.toJSONString(productTypes));
        	retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00120));
            return retMap;
        }
        
        LOGGER.info("渠道{}下产品类型{}的份额数据数据入库成功：{}，流程结束",
        channelCode,JSONObject.toJSONString(productTypes),newCount);
		retMap.putAll(map);
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		return retMap;
        
	}

	/**
	 * 更新实际可赎回日期为渠道端工作日  
	 * @Title: updateRelAllowRedemptDate   
	 * @author: yindy 2020年4月24日 下午3:23:13
	 * @param: @param channelCode
	 * @param: @param transDate
	 * @param: @param ciMarketCode      
	 * @return: void      
	 * @throws
	 */
	@Override
	public void updateRelAllowRedemptDate(String channelCode,
			String transDate, String ciMarketCode) {
		List<Map<String, Object>> updateDateList = new ArrayList<Map<String,Object>>();
		exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		fundVolDetailCfmMapper = (FundVolDetailCfmMapper)SpringUtils.getBean(FundVolDetailCfmMapper.class);
		List<String> notWorkallowDate = fundVolDetailCfmMapper.queryNotWorkAllowDate(channelCode,transDate);
		LOGGER.info("{}渠道,{}时间,需要修改的不是工作日的可赎回日期为{}",channelCode,transDate,(notWorkallowDate == null ? "" : notWorkallowDate.toString()));
		for (String allowDate : notWorkallowDate) {
			Map<String, Object> updateDate = new HashMap<String, Object>();
			String relDate = exchangeProcessorService.getNextWorkDay(allowDate,ciMarketCode);
			updateDate.put("CHANNELCODE", channelCode);
			updateDate.put("TRANSDATE", transDate);
			updateDate.put("ALLOWDATE", allowDate);
			updateDate.put("RELALLOWDATE", relDate);
			updateDateList.add(updateDate);
		}
		if(updateDateList != null && updateDateList.size() > 0 ){
			//更新可赎回日期
			fundVolDetailCfmMapper.updateRelAllowDate(updateDateList);
		}
	}

	/**
	 * @Description 分段批量插入数据库数据  分段方法
	 * @Author weijunjie
	 * @Date 2020/1/7 15:37
	 **/
	public List<List<FundVolDetailCfm>> subBeanList(Integer max,List<FundVolDetailCfm> beanList){
        List<List<FundVolDetailCfm>> resList = new ArrayList<>();
        max = max == null?50:max;
        if(beanList.size() <= max){
            resList.add(beanList);
            return resList;
        }else{
            int out = beanList.size()%max;
            int z = beanList.size()/max;
            for(int i=0;i<=z;i++){
                if(i==z){
                    resList.add(beanList.subList(i*max,i*max+out));
                }else{
                    resList.add(beanList.subList(i*max,(i+1)*max));
                }
            }
        }
        return resList;
    }

    /**
     * @Description 未付收益金额数据有效性验证
     * @Author weijunjie
     * @Date 2020/2/14 14:35
     **/
    public String checkUndistributeMonetaryIncome(List<Map<String,Object>> cashIncomeList,String lastDate,
												  String channelCode,String transDate){
		String retCode = ExceptionConStants.retCode_0000;
    	for(Map<String,Object> cashIncome :cashIncomeList){
            String transactionAccountID = MapUtils.getString(cashIncome, "TRANSACTIONACCOUNTID", "");
            String fundCode = MapUtils.getString(cashIncome, "FUNDCODE", "");
            
            //1 份额判断 （基金可用份数）
            String availableVol = MapUtils.getString(cashIncome, "AVAILABLEVOL", null);
            if(StringUtils.isBlank(availableVol) || new BigDecimal(availableVol).compareTo(new BigDecimal("0")) == 0){
                continue;
            }else{
                //2 分红数据判断 （获取TransactionAccountID查询分红表数据信息）
                FundDividendCfmMapper fundDividendCfmMapper =(FundDividendCfmMapper)SpringUtils.getBean(FundDividendCfmMapper.class);
                FundDividendCfm fundDividendCfm = fundDividendCfmMapper.
                        selectByTransactionAccountID(transactionAccountID,fundCode,transDate,channelCode);
                if(null != fundDividendCfm &&
                        null != fundDividendCfm.getDcDividendAmount() &&
                        fundDividendCfm.getDcDividendAmount().compareTo(new BigDecimal("0")) == 1){
                    continue;
                }else{
                    //3 判断是否有未付收益金额数据 (有值且大于0)
                    String undistributeMonetaryIncome = MapUtils.getString(cashIncome,
                            "UNDISTRIBUTEMONETARYINCOME", null);
                    if(StringUtils.isNotBlank(undistributeMonetaryIncome) &&
                            new BigDecimal(undistributeMonetaryIncome).compareTo(new BigDecimal("0")) == 1){
                        continue;
                    }else{
                        //4 判断上一工作日是否有对账数据 (是否是首次购买 没有对账数据不去验证)
                        FundAccountReconCfmMapper fundAccountReconCfmMapper =(FundAccountReconCfmMapper)SpringUtils.getBean(FundAccountReconCfmMapper.class);
                        String vol = fundAccountReconCfmMapper.selectFundAccountByTransDate(
                                channelCode, fundCode, transactionAccountID, lastDate);
                        if( StringUtils.isBlank(vol) || new BigDecimal(vol).compareTo(new BigDecimal("0")) == 0){
                            continue;
                        }
                        LOGGER.error("基金账号{},产品代码{},错误信息{}",transactionAccountID,fundCode,ExceptionConStants.retMsg_T00113);
                        retCode = ExceptionConStants.retCode_T00113;
                        break;
                    }
                }

            }
        }
        return retCode;
	}
    
    /**
     * 备份对账表数据   
     * @Title: backUpAccountRecon   
     * @author: yindy 2020年3月13日 下午2:08:48
     * @param: @throws Exception      
     * @return:      
     * @throws
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
    public void backUpAccountRecon(Map<String, Object> map) throws Exception{
    	int insertCount = fundAccountReconCfmMapper.insertBackUpAccountRecon(map);
    	int deleteCount = fundAccountReconCfmMapper.deleteBackUpAccountRecon(map);
    	LOGGER.info("插入备份表的数据为{},删除原表的数据为{}",insertCount,deleteCount);
    	if(insertCount != deleteCount){
    		throw new Exception("手动报错,回滚");
    	}
    }
	
}