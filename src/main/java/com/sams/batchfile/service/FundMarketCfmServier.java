package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.FundMarketCustom;

public interface FundMarketCfmServier {
	FundMarketCfm selectByPrimaryKey(Long fmId);
	
    int deleteFundMarketCFM(FundMarketCfm fundMarketCfm);

    int insert(FundMarketCfm record);

    int insertSelective(Map<String,Object> inputMap);
    
    Map<String,Object> selectFundMarketCfm(FundMarketCfm fundMarketCfm);
    
    FundMarketCfm selectFundMarketCfmInfo(FundMarketCfm fundMarketCfm);
    
    int updateByPrimaryKey(FundMarketCfm fundMarketCfm);
    
    int updateByChannelCode(FundMarketCfm fundMarketCfm);
    
    int updateSendStatus(FundMarketCfm fundMarketCfm);
    
    String fundCfmJudgeTrade(Map<String,Object> map);
    
    String selectHoldShares(Map<String,Object> inputMap);
    
	public List<Map<String,Object>> findMarketDetailCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	public List<FundMarketCfm> selectFundMarketInfo(PageInfo pageInfo, Map<String, Object> condition);
}