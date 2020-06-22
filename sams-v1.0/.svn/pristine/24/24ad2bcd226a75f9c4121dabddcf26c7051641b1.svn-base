package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.ProcessingState;
import com.sams.custom.model.result.ChannelProductInfo;
import com.sams.custom.model.result.MarketHandingResult;

public interface ProcessingStateService extends BaseService<ProcessingState>{

	public void insertProcessingState(ProcessingState processingState);
	
	//PageInfo findMarketHandingCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	List<MarketHandingResult> findMarketHandGuiList(String tradeDate);
	
	public List<MarketHandingResult> findMarketHandingCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	public List<MarketHandingResult> findMarketHandingHistory(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	public List<String>  checkChannelCodeList(Map<String,Object> qryMap);
}
