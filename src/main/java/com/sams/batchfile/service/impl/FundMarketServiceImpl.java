package com.sams.batchfile.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.CloseDateService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.FundQuotTmpService;
import com.sams.business.controller.DayEndProcessorController;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.custom.mapper.FundMarketCfmMapper;
import com.sams.custom.mapper.FundMarketMapper;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.FundMarketCustom;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.mapper.SysDictMapper;
import com.sitco.yt.manage.models.D_account_position;

@Service
public class FundMarketServiceImpl implements FundMarketService {
	private static Logger LOGGER = LoggerFactory.getLogger(FundMarketServiceImpl.class);

	@Autowired
	private FundMarketCfmServier fundMarketCfmServier;
	
	@Autowired
	private FundMarketMapper fundMarketMapper;

	@Autowired
	private SysDictMapper sysDictMapper;

	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Autowired
	private CloseDateService closeDateService;

	@Autowired
	private FundQuotTmpService fundQuotTmpService;

	@Autowired
	private FileDataService fileDataService;
	
	@Autowired
	private FundMarketCfmServier fundMarketCfmService;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private ChannelInfoService channelInfoService;
	

	/**
	 * 根据交易日期获取已经在募集期的产品信息
	 * 
	 * @return List<FundMarketCustom> 产品与渠道基本信息集合
	 */
	@Override
	public Map<String, Object> selectByPiIpoBeginDate(Map<String, String> inputMap) {
		Map<String, Object> outputMap = Maps.newHashMap();
		String tradeDate = MapUtils.getString(inputMap, "TRADEDATE","");
		String channelCode = MapUtils.getString(inputMap, "CHANNELCODE","");
		String flag = MapUtils.getString(inputMap, "FLAG","");
		channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
		exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		PchannelInfo info =  channelInfoService.queryChannelInfoByChannelCode(channelCode);
		FundMarketCfm fundMarketCfm = new FundMarketCfm();
		fundMarketCfm.setFmChannelCode(channelCode);
		fundMarketCfm.setFmBusinessDate(tradeDate);
		
		//获取T-1个工作日
		String lastT1WorkDay = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(tradeDate), info.getCiMarketCode()); 
		//获取T-2个工作日
		String lastT2WorkDay = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(lastT1WorkDay), info.getCiMarketCode());
		
		//只有在页面上修改渠道下各个产品的行情状态的情况下才不删除行情确认表该渠道下处理日期的数据
		if(ExceptionConStants.retCode_true.equals(flag)){
			//先删除行情确认表该渠道下处理日期的数据
			fundMarketCfmService=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
			fundMarketCfmService.deleteFundMarketCFM(fundMarketCfm);
			LOGGER.info("删除行情确认表渠道：{}下处理日期:{}的数据",channelCode,tradeDate);
		}
		
		Map<String, Object> fundMarketMap = new HashMap<String, Object>();
		fundMarketMap.put("tradeDate", tradeDate);
		fundMarketMap.put("channelCode", channelCode);
		fundMarketMap.put("lastT1WorkDay", lastT1WorkDay);
		fundMarketMap.put("lastT2WorkDay", lastT2WorkDay);
		
		fundMarketMapper = SpringUtils.getBean(FundMarketMapper.class);
		List<FundMarketCustom> list = fundMarketMapper.selectFundMarketList(fundMarketMap);
		
		/* 没有数据也要发送行情文件20200212日 15：57
		 * if(list == null ||list.size() == 0){
			outputMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00016));
			LOGGER.info("该渠道下无产品需要发送行情");
			return outputMap;
		}*/
		outputMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000, list);
		outputMap.put("TRADEDATE", tradeDate);
		outputMap.put("CHANNELCODE", channelCode);
		LOGGER.info("第一步 获取渠道下产品信息方法输出参数为日期:"+tradeDate+"渠道"+channelCode+"数据量"+list.size());
		outputMap.put("RETVALUE", list); 
		outputMap.put("CSDCVER",7+ info.getCiCsdcVer()); 
		return outputMap;
	}

	@Override
	public String selectGtLimit(D_account_position dAccountPosition) {
		fundMarketMapper = SpringUtils.getBean(FundMarketMapper.class);
		// TODO Auto-generated method stub
		return fundMarketMapper.selectGtLimit(dAccountPosition);
	}

	@Override
	public List<String> selectAllUsedChannels(String tradeDate) {
		return fundMarketMapper.selectAllUsedChannels(tradeDate);
	}

	@Override
	public int updateCodeAndType(List<FundMarketCustom> list) {
		return productInfoMapper.updateBatch(list);
	}

	@Override
	public PProductInfo selectByProductCode(String productCode) {
		return productInfoMapper.selectByProductCode(productCode);
	}

	@Override
	public long selectByConditionCount(Map<String, Object> map) {
		return productInfoMapper.selectByConditionCount(map);
	}

	@Override
	public Map<String, Object> selectPfGslFundCodeInCome(Map<String, Object> map) {
		return fundMarketMapper.selectPfGslFundCodeInCome(map);
	}

	@Override
	public Map<String, Object> getIncomeInfo(Map<String, Object> map) {
		return fundMarketMapper.getIncomeInfo(map);
	}

	/**
	 * 行情信息处理数据处理
	 * @param map
	 * @return List<FundMarketCustom> 产品与渠道基本信息集合
	 */
	@Override
	public Map<String, Object> dataProcessing(Map<String, String> map) {
		LOGGER.info("行情处理节点dataProcessing请求参数为:{}",JSONObject.toJSONString(map));
		Map<String, Object> outMap = Maps.newHashMap();
		// 第一步 获取数据
//		Date date1 = new Date();
//		LOGGER.info("第一步 获取渠道下产品信息方法selectByPiIpoBeginDate传入参数为"+map.toString());
		outMap = selectByPiIpoBeginDate(map);
		LOGGER.info("第一步 获取渠道下产品信息方法selectByPiIpoBeginDate入参:{},出参：{}",JSONObject.toJSONString(map),JSONObject.toJSONString(outMap));
		if (!(ExceptionConStants.retCode_0000).equals(outMap.get("retCode")))
			return outMap;
		List<Map<String, Object>> list=(List<Map<String, Object>>) outMap.get("RETVALUE");
		//RETVALUE为null说明无行情数据，直接到拼接数据步骤
		if(null != list && list.size() > 0){
//			Date date2 = new Date();
//			LOGGER.info("第一步处理时间"+(date2.getTime()-date1.getTime()));
			
			// 第二步 判断交易日
//			LOGGER.info("第二步判断交易日的输入参数为第一步获取渠道下产品信息的输出参数");
			closeDateService = (CloseDateService) SpringUtils.getBean(CloseDateService.class);
			LOGGER.info("第二步判断交易日closeDateService.checkChannelInfoTradeDate入参:{}",JSONObject.toJSONString(outMap));
			outMap = closeDateService.checkChannelInfoTradeDate(outMap);
			LOGGER.info("第二步判断交易日closeDateService.checkChannelInfoTradeDate出参:{}",JSONObject.toJSONString(outMap));
			if (!(ExceptionConStants.retCode_0000).equals(outMap.get("retCode")))
				return outMap;
//			Date date3 = new Date();
//			LOGGER.info("第2步处理时间"+(date3.getTime()-date2.getTime()));
			
			// 第三步 插入临时表
			LOGGER.info("第三步插入临时表的输入参数第二步判断交易日的输出参数");
			fundQuotTmpService = (FundQuotTmpService) SpringUtils.getBean(FundQuotTmpService.class);
			outMap = fundQuotTmpService.insertFundQuotTmp(outMap);
			if (!(ExceptionConStants.retCode_0000).equals(outMap.get("retCode")))
				return outMap;
//			Date date4 = new Date();
//			LOGGER.info("第3步处理时间"+(date4.getTime()-date3.getTime()));
			
			// 第四步 获取数据
			LOGGER.info("第四步获取行情数据的输入参数第三步判断交易日的输出参数");
			outMap = fundQuotTmpService.selectAllFundQuotTmp(outMap);
			if (!(ExceptionConStants.retCode_0000).equals(outMap.get("retCode")))
				return outMap;
			fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
//			Date date5 = new Date();
//			LOGGER.info("第4步处理时间"+(date5.getTime()-date4.getTime()));
			
			// 第五步 校验数据
			LOGGER.info("第五步校验数据的输入参数为第四步的输出参数");
			outMap = fileDataService.checkFiledData(outMap);
			if (!(ExceptionConStants.retCode_0000).equals(outMap.get("retCode")))
				return outMap;
//			Date date6 = new Date();
//			LOGGER.info("第5步处理时间"+(date6.getTime()-date5.getTime()));
			
			LOGGER.info("第六步拼接数据的输入参数为第五步校验数据的输出参数");
		}else{
			fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
		}
		// 第六步 拼接数据
		outMap = fileDataService.getFileDataInfo(outMap);
		return outMap;
	}

	/**
	 * 渠道下产品到期修改产品状态
	 * @param inputMap
	 */
	public void changeProductInfoFlag(Map<String, String> inputMap) {
		String tradeDate =MapUtils.getString(inputMap, "TRADEDATE",""); 
		String channelCode = MapUtils.getString(inputMap, "CHANNELCODE",""); 
		productInfoMapper = SpringUtils.getBean(ProductInfoMapper.class);
		// 查询渠道下产品到期需要进行修改状态的产品信息
		List<PProductInfo> updateList = productInfoMapper.updateList(inputMap);
		// 查询行情终止天数（到期还需要继续发送行情的天数）
		sysDictMapper = SpringUtils.getBean(SysDictMapper.class);
		String fundSendEndDate = sysDictMapper.findDictBydictCodeVo("fundSendEndDate", "") == null ? "0"
				: sysDictMapper.findDictBydictCodeVo("fundSendEndDate", "");
		LOGGER.info("到期还需要继续发送行情的天数为"+fundSendEndDate+"天");
		if (updateList != null && updateList.size() != 0) {
			for (PProductInfo info : updateList) {
				String endDate = info.getPiProEndDate();
				List<Date> days = DateUtils.getBetweenDates(DateUtils.getDateBYYYYMMDD(endDate),
						DateUtils.getDateBYYYYMMDD(tradeDate));
				if (Long.parseLong(endDate) < Long.parseLong(tradeDate)) {
					// 到期但还需要发送行情
					info.setPiValidFlag(Const.BUSINESS_STUTAS_02);
					LOGGER.info(channelCode+"渠道下"+info.getPiChannelProductCode()+"产品状态修改为到期但还需要发送行情");
				}
				if (days.size() >=Long.parseLong(fundSendEndDate)) {
					// 到期不需要发送行情
					info.setPiValidFlag(Const.BUSINESS_STUTAS_00);
					LOGGER.info(channelCode+"渠道下"+info.getPiChannelProductCode()+"产品状态修改为到期不需要发送行情");
				}
				productInfoMapper.updateByPrimaryKey1(info);
			}
		}

	}

	@Override
	public List<String> selectAllUpdateChannels(String tradeDate) {
		return fundMarketMapper.selectAllUpdateChannels(tradeDate);
	}

	@Override
	public List<PProductInfo> selectByProductCodeList(Map<String,Object> map) {
		return productInfoMapper.selectByProductCodeList(map);
	}
	
	public Map<String,Object> selectFundMarketInfo(Map<String,Object> map){
		return fundMarketMapper.selectFundMarketInfo(map);
	}
	
	/**
	 * @author: wangchao 2019年4月8日 下午5:02:21
	 * @Description: 基金行情信息确认表发送状态修改
	 * @param:Map<String, Object> map
	 * @return: int
	 * @throws Exception 
	 */
	
	@Override
	public Map<String,Object> updateSendStatus(Map<String, Object> map){
		String channelCode = MapUtils.getString(map, "CHANNELCODE","");
		String businessDate = MapUtils.getString(map, "TRADEDATE","");
		FundMarketCfm fundMarketCfm =new FundMarketCfm();
		fundMarketCfm.setFmChannelCode(channelCode);
		fundMarketCfm.setFmBusinessDate(businessDate);
		fundMarketCfm.setFmSendStatus("01");
		fundMarketCfm.setFmSendFileTime(DateUtils.getOracleSysDate());
		int listTotal = fundMarketCfmServier.updateSendStatus(fundMarketCfm);
		Map<String,Object> retMap = Maps.newHashMap();
		retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		retMap.putAll(map);
		return retMap;
	}
}