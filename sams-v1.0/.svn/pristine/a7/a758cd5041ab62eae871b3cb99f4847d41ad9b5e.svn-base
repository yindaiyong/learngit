package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ProcessingState;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.MarketHandingResult;

public interface ProcessingStateMapper {
    int deleteByPrimaryKey(Long psId);

    int insert(ProcessingState record);

    int insertSelective(ProcessingState record);

    ProcessingState selectByPrimaryKey(Long psId);

    int updateByPrimaryKeySelective(ProcessingState record);

    int updateByPrimaryKey(ProcessingState record);
    
    List<MarketHandingResult> findMarketHandingCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    List<MarketHandingResult> findMarketHandGuiList(String tradeDate);
    
    List<MarketHandingResult> findMarketHandingHistory(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
	public List<String>  checkChannelCodeList(Map<String,Object> qryMap);


    
}