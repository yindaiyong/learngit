package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

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
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.CloseDateMapper;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.FundMarketCustom;
import com.sams.custom.model.PchannelInfo;

@Service
public class CloseDateServiceImpl implements CloseDateService{
	private static Logger LOGGER = LoggerFactory.getLogger(CloseDateServiceImpl.class);
	
	@Autowired
    private CloseDateMapper closeDateMapper;
	@Autowired
	private ChannelInfoService channelInfoService;
	
	private ExchangeProcessorService exchangeProcessorService;
	
	
	/**
	 * 根据市场编码、交易日获得是否为交易日
	 * @param closeDate 交易日期  marketCode 市场编码
	 * @return int 返回值 0 为交易日  1 不为交易日
	 */
	@Override
	public int selectByMarketCodeAndcdCloseDate(CloseDate closeDate) {
		closeDateMapper=SpringUtils.getBean(CloseDateMapper.class);
		return closeDateMapper.selectByMarketCodeAndcdCloseDate(closeDate);
	}
	
	/**
	 * 根据交易日期、市场编码 设置渠道交易标志
	 * @param inputMap 交易日期  marketCode 市场编码
	 * @return Map<String, Object> 01 为交易日  00 不为交易日
	 */
	@Override
	public Map<String,Object> checkChannelInfoTradeDate(Map<String,Object> inputMap){
		Map<String,Object> outputMap=Maps.newHashMap();
		String channelCode = (String)inputMap.get("CHANNELCODE");
		List<FundMarketCustom> list=(List<FundMarketCustom>)inputMap.get("RETVALUE");
		String tradeDate=MapUtils.getString(inputMap, "TRADEDATE", 	DateUtils.getDate(DateUtils.FORMAT_STR_DATE8));

		FundMarketCustom fundMarketCustom = new FundMarketCustom();
		if(list!=null&&list.size()!=0){
			fundMarketCustom = list.get(0);
			LOGGER.info("校验非交易日的产品信息以及交易日已获取");
			CloseDate closeDate=new CloseDate();
			closeDate.setCdMarketCode(fundMarketCustom.getCiMarketCode());
			closeDate.setCdCloseDate(tradeDate);
			exchangeProcessorService = (ExchangeProcessorService)SpringUtils.getBean(ExchangeProcessorService.class);
			boolean flag=exchangeProcessorService.checkDateIsWork(tradeDate,channelCode);
			if(flag){
				outputMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000,list));
				outputMap.put("ISTRADEDATEFLAG", Const.BUSINESS_STUTAS_01);//可处理状态
			}else{
				outputMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00007));
				outputMap.put("ISTRADEDATEFLAG", Const.BUSINESS_STUTAS_00);//不可处理状态
				LOGGER.info(fundMarketCustom.getCiChannelName()+"渠道下的"+fundMarketCustom.getPiChannelProductName()+tradeDate+"不是交易日");
			}
		}else{
			    //list为空时，说明渠道下的所有产品都已经到期，且不需要发送行情信息，置不可处理状态
			    channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
			    Map<String,Object> codeMap = Maps.newHashMap();
			    codeMap.put("CHANNELCODE", channelCode);
			    PchannelInfo info =  channelInfoService.selectByChannelCode(codeMap);
			    outputMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000,list));
			    outputMap.put("ISTRADEDATEFLAG", Const.BUSINESS_STUTAS_00);//不可处理状态
			    LOGGER.info(info.getCiChannelName()+"渠道下的所有产品都已经到期，不发送行情");
		}
		outputMap.put("TRADEDATE", tradeDate);
		outputMap.put("CHANNELCODE", inputMap.get("CHANNELCODE"));
		LOGGER.info("第二步判断交易日的输出参数为日期:"+tradeDate+"渠道:"+channelCode+"数据量:"+list.size()+"条");
		return outputMap;
		
	}

	@Override
	public List<CloseDate> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return closeDateMapper.findCloseDateCondition(pageInfo, condition);
	}


	@Override
	public int save(CloseDate entity) {
		return closeDateMapper.insert(entity);
	}

	@Override
	public int deleteCloseDate(String year,String month,String marketCode) {
		return closeDateMapper.deleteCloseDate(year, month, marketCode);
	}


	@Override
	public List<CloseDate> findClodeDateByCodeDate(Map<String, String> map) {
		return closeDateMapper.findClodeDateByCodeDate(map);
	}

	@Override
	public int updateByPrimaryKey(List<CloseDate> list) {
		return closeDateMapper.updateByPrimaryKey(list);
	}

}
