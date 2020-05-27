package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.FundMarketCfm;

public interface FundMarketCfmMapper {
	
	FundMarketCfm selectByPrimaryKey(Long fmId);
	
    int deleteFundMarketCFM(FundMarketCfm fundMarketCfm);

    int insertSelective(Map<String,Object> inputMap);
    
    int updateByPrimaryKey(FundMarketCfm fundMarketCfm);
    
    List<FundMarketCfm> selectFundMarketCfm(FundMarketCfm fundMarketCfm);
    
    FundMarketCfm selectFundMarketCfmInfo(FundMarketCfm fundMarketCfm);
    
    int updateByChannelCode(FundMarketCfm fundMarketCfm);
    
    int updateSendStatus(FundMarketCfm fundMarketCfm);
    
    String selectFundStatus(Map<String,Object> inputMap);
    
    String selectHoldShares(Map<String,Object> inputMap);
    
	public List<Map<String,Object>> findMarketDetailCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	public List<Map<String,Object>> getFundMarketDataInfo(Map<String, Object> map);
	
	/**
     * @Description 更新文件生成状态以及时间
     * @Author wangchao
     * @Date 2020/4/16 13:57
     **/
    int updateGenerateStaAndTime(Map<String,Object> inputMap);

    List<FundMarketCfm> selectFundMarketInfo(@Param("pageInfo") PageInfo pageInfo,@Param("condition")Map<String, Object> condition);
}