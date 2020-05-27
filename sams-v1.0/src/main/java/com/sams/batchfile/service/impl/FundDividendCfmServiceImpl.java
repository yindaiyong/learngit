package com.sams.batchfile.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FundDividendCfmService;
import com.sams.batchfile.service.FundQuotTmpService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.mapper.FundAccountReconCfmMapper;
import com.sams.custom.mapper.FundDividendCfmMapper;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.model.result.SysDictVo;
import com.sams.sys.service.SysDictService;
/**
 * @ClassName:  FundDividendCfmServiceImpl   
 * @author: yindy
 * @date:   2020年3月19日 下午3:58:03   
 *
 */
@Service
public class FundDividendCfmServiceImpl implements FundDividendCfmService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(FundDividendCfmServiceImpl.class);
	
	@Autowired
	private FundDividendCfmMapper fundDividendCfmMapper;
	
	@Autowired
	private FundAccountReconCfmMapper fundAccountReconCfmMapper;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired 
	private SysDictService sysDictService;
	
	@Autowired
	private FundQuotTmpService fundQuotTmpService;

	@Override
	public int deleteByChannelCodeBusinessDateCfm(String channelCode,
			String businessDate) {
		return fundDividendCfmMapper.deleteByChannelCodeBusinessDateCfm(channelCode, businessDate);
	}

	@Override
	public int insertByBatchCfm(List<Map<String, Object>> attachmentTables) {
		return fundDividendCfmMapper.insertByBatchCfm(attachmentTables);
	}

	@Override
	public int selectByChannelCodeBusinessDateCountCfm(String channelCode,
			String businessDate) {
		return fundDividendCfmMapper.selectByChannelCodeBusinessDateCountCfm(channelCode, businessDate);
	}

	
	/**
	 * 保存06分红确认表数据
	 * @Title: dealCfMData   
	 * @author: wanshunbin
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  RETVALUE 数据记录集
	 * @return List<Map<String,Object>>
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> dealCfMData(Map<String, Object> map) {
		LOGGER.info("06分红信息入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String businessDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectByChannelCodeBusinessDateCountCfm(channelCode, businessDate);
		LOGGER.info(channelCode+"06分红信息查询存在的数据，渠道为："+channelCode+",交易日期为："+businessDate+",数据条数："+selectCount);
		int deleteCount=deleteByChannelCodeBusinessDateCfm(channelCode, businessDate);
		LOGGER.info(channelCode+"06分红信息清理存在的数据，渠道为："+channelCode+",交易日期为："+businessDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchCfm(list);
			LOGGER.info(channelCode+"保存06分红数据入临时表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info(channelCode+"06分红入库正式表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info(channelCode+"06分红入库正式表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00030);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00031);
			LOGGER.info(channelCode+"06分红入库正式表失败,原因为："+ExceptionConStants.retCode_T00031);
		}
		LOGGER.info("06分红入库结束处理");
		return retMap;
	}

	
	/**
	 * 分红信息数据基础规则校验
	 * @Title: checkFundDividendApplyBasicData   
	 * @author: wanshunbin
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  CHANNELINFO 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> checkFundDividendApplyBasicData(Map<String, Object> fundCodeMap) {
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		Map<String,Object> retMap=Maps.newHashMap();
		try {
		
			contractInfoMapper=(ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
			fundQuotTmpService=(FundQuotTmpService) SpringUtils.getBean(FundQuotTmpService.class);
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
			fundDividendCfmMapper=(FundDividendCfmMapper) SpringUtils.getBean(FundDividendCfmMapper.class);
			fundAccountReconCfmMapper=(FundAccountReconCfmMapper) SpringUtils.getBean(FundAccountReconCfmMapper.class);
			
			LOGGER.info("分红文件数据处理开始");
			String channelCode=(String)fundCodeMap.get("CHANNELCODE");//渠道编号
			String transDate=(String)fundCodeMap.get("TRANSDATE");//交易日期
			PchannelInfo channelInfo = (PchannelInfo)fundCodeMap.get("CHANNELINFO");//渠道基本信息
			String batchNoOnOff=channelInfo.getCiBatchNoOnOff();
			//根据交易日期、市场编号获取上一个工作日
			String lastDate=exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate),channelInfo.getCiMarketCode());
			LOGGER.info(channelCode+"分红文件数据处理的上一个工作日期为："+lastDate);
			//查询该渠道下所拥有产品 配置的交易类型
			List<SysDictVo>  sysDictList= sysDictService.findDealTypeByChannelCode(channelCode);
			LOGGER.info("分红查询"+channelCode+"配置的交易类型的数量为："+(sysDictList == null ? 0 : sysDictList.size()));
			for(SysDictVo sysDictVo:sysDictList){
				String businessCode=sysDictVo.getDictCode(); //交易业务类型
				String productType = sysDictVo.getDictType().substring(0,1);//产品类型
				
			    Map<String,Object> fixedIncomeParam=Maps.newHashMap();
			    fixedIncomeParam.put("CHANNELCODE", channelCode);
			    fixedIncomeParam.put("LASTDATE", lastDate);
			    fixedIncomeParam.put("TRANSDATE", transDate);
			    fixedIncomeParam.put("BATCHNOONOFF", batchNoOnOff);
				if(businessCode.indexOf(Const.BUSINESSCODE_143)!=-1){
					fixedIncomeParam.put("PRODUCTTYPE", productType);//产品类型
					//获取代销端产品数据集
					List<String> fundCodes= contractInfoMapper.selectSalesFundDividend(fixedIncomeParam);
					LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取分红的有效产品的数量为："+(fundCodes == null ? 0:fundCodes.size()));
					if(fundCodes == null || fundCodes.size() == 0 ){
						continue;
					}
					
					//固收类 分红处理逻辑    根据产品查分红数据  部分返本的分红不给数据
					if(Const.FUND_TYPE_04.equals(productType)){
						LOGGER.info(channelCode+"开始获取固收类分红数据");
						// 20200331 开会确定过滤掉部分分配数据加到04的确认金额上
						List<Map<String, Object>> list = contractInfoMapper.selectFixedIncome(fixedIncomeParam);
						retList.addAll(list);
						LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取固收类分红数据结束,获取的数据数量为:"+(list == null ? 0:list.size()));
						continue;
					}
					//权益类
					if(Const.FUND_TYPE_07.equals(productType)){
						LOGGER.info(channelCode+"开始获取权益类分红数据");
						List<Map<String, Object>> list = contractInfoMapper.selectFixedIncomeGQ(fixedIncomeParam);
						retList.addAll(list);
						LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取权益类分红数据结束,获取的数据数量为:"+(list == null ? 0:list.size()));
						continue;
					}
					
					//多币种
					if(Const.FUND_TYPE_08.equals(productType)){
						LOGGER.info(channelCode+"开始获取多币种分红数据");
						//cteated by 王超 20200427 多币种分红到目前为止还没测试过
						List<Map<String, Object>> list = contractInfoMapper.selectmultiCurrencyIncome(fixedIncomeParam);
						retList.addAll(list);
						LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取多币种分红数据结束,获取的数据数量为:"+(list == null ? 0:list.size()));
						continue;
					}
	
					//获取当前渠道下所有产品的合同数据集
					//修改为查询上一日的对账份额不为空的对账的基金账号，并排除掉上一日全额赎回的基金账号
					List<String> inContracts = fundAccountReconCfmMapper.queryHasShareFundAcco(fixedIncomeParam);
					//List<String> inContracts = contractInfoMapper.selectInContracts(fixedIncomeParam);
					LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取分红的有效合同数量为："+(inContracts == null ? 0:inContracts.size()));
					
					if(inContracts==null || inContracts.size() == 0 ){
						continue;
					}
					
					//根据合同查分红
					if(Const.FUND_TYPE_03.equals(productType)){
						LOGGER.info(channelCode+"开始获取红宝石七天分红数据");
						String quarterForwardDay= fundQuotTmpService.getQuarterForwardDay(transDate,(String)channelInfo.getCiMarketCode());
						fixedIncomeParam.put("TRANSDATE", transDate);
						fixedIncomeParam.put("LASTDATE", quarterForwardDay);
						//红宝石 七天处理 分红
						List<Map<String,Object>> rubyIncomeListCount=contractInfoMapper.selectRubyIncomeCount(fixedIncomeParam);
						//先判断根据合同取数据是否有重复数据
						if(rubyIncomeListCount.size()>0){
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00085));
							return retMap;
						}else{
							//红宝石 七天处理 分红
							if(transDate.equals(quarterForwardDay)){
								List<Map<String,Object>> rubyIncomeList=contractInfoMapper.selectRubyIncome(fixedIncomeParam);
								LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取红宝石七天分红数据结束,获取的数据数量为:"+(rubyIncomeList == null ? 0:rubyIncomeList.size()));
								//数据集有数据并且dictFlag 是红宝石类集 
								if(rubyIncomeList!=null && rubyIncomeList.size()>0){
									//红宝石七天 赎回是T+2日确认  所以校验上一日的份额不为0的对账数与分红数一致去掉  20191122
									retList.addAll(rubyIncomeList);
									continue;
									/*//合同与数据记录相等
									if(inContracts.size() == rubyIncomeList.size()){
										
									}else{
										retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00082));
										return retMap;
									}*/
								}else{
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00078));
									return retMap;
								}
							}
						}
					}
					
					
					if(Const.FUND_TYPE_01.equals(productType)||Const.FUND_TYPE_02.equals(productType)){
						LOGGER.info(channelCode+"开始获取丰利分红数据");
						String forwardDay=fundQuotTmpService.getForwardDay(transDate,(String)channelInfo.getCiMarketCode());
						//现金管理类产品分红
						List<Map<String,Object>> cashFixedIncomeCount=contractInfoMapper.selectCashFixedIncomeCount(fixedIncomeParam);
						//先判断根据合同取数据是否有重复数据
						if(cashFixedIncomeCount.size()>0){
							retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00086));
							return retMap;
						}else{
							//丰利B、D 分红
							if(transDate.equals(forwardDay)){
								//取丰利类收益结转取值
								List<Map<String,Object>> cashFixedIncome=contractInfoMapper.selectCashFixedIncome(fixedIncomeParam);
								LOGGER.info(channelCode+"通过"+(fixedIncomeParam == null ? null : fixedIncomeParam.toString())+"获取丰利分红数据结束,获取的数据数量为:"+(cashFixedIncome == null ? 0:cashFixedIncome.size()));
								//数据集有数据并且dictFlag 是现金管理类集 
								if(cashFixedIncome != null && cashFixedIncome.size() > 0){
									//上一日的份额不为0的对账数与数据记录相等
									if(inContracts.size() == cashFixedIncome.size()){
										retList.addAll(cashFixedIncome);
									}else{
										retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00083));
										return retMap;
									}
								}else{
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00079));
									return retMap;
								}
								
							}
						}
					}
					
					//丰利F类分红 yindy 20200227
					if(Const.FUND_TYPE_00.equals(productType)){
						LOGGER.info(channelCode+"开始获取丰利F分红数据");
						String forwardDay = fundQuotTmpService.getForwardDay(transDate,(String)channelInfo.getCiMarketCode());
						if(transDate.equals(forwardDay)){
							//查询可以参与分红的有效合同
							int contractCount = fundAccountReconCfmMapper.queryCanDividendContract(fixedIncomeParam);
							LOGGER.info("通过{}查询的丰利f类可以分红的合同数量为:{}",fixedIncomeParam.toString(),contractCount);
							if(contractCount == 0 ) {continue;}
							List<Map<String,Object>> currencyFDividendList = contractInfoMapper.queryCurrencyFDividend(fixedIncomeParam);
							LOGGER.info("通过{}查询的丰利f类可以分红的数量为:{}",fixedIncomeParam.toString(),(currencyFDividendList == null ? "" : currencyFDividendList.size()));
							if(currencyFDividendList != null && currencyFDividendList.size() > 0){
								//上一日的对账的数量与分红数量一致
								if(contractCount == currencyFDividendList.size()){
									retList.addAll(currencyFDividendList);
								}else{
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00117));
									return retMap;
								}
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00116));
								return retMap;
							}
						}
					}
				}
			}
	
			retMap.put("CHANNELCODE", channelCode);
			retMap.put("TRANSDATE", transDate);
			retMap.put("RETVALUE", retList);
			if(retList != null && retList.size() > 0 ){
				retMap=dealCfMData(retMap);
			}
			retMap.putAll(fundCodeMap);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			LOGGER.info(channelCode+"分红文件数据处理结束");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("分红取值错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkFundDividendApplyBasicData方法,分红取值异常,请联系管理员!");
			return retMap;
		}
	}
}