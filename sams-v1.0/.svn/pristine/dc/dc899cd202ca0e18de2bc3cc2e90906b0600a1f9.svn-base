package com.sams.custom.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.custom.model.ExchangeReqCfm;

public interface ExchangeReqCfmMapper {
    int deleteByPrimaryKey(Long ercId);

    int insert(ExchangeReqCfm record);

    int insertSelective(ExchangeReqCfm record);

    ExchangeReqCfm selectByPrimaryKey(Long ercId);

    int updateByPrimaryKeySelective(ExchangeReqCfm record);

    int updateByPrimaryKey(ExchangeReqCfm record);
    
    int insertByBatchCfm(List<Map<String, Object>> list);
    
    List<Map<String,Object>> selectCheckTransContract(Map<String, Object> inputMap);
    
    Map<String,Object> selectExchangeReqCfmInfo(@Param("CHANNELCODE")String channelCode,@Param("APPSHEETSERIALNO")String appSheetSerialNo);
    
    public BigDecimal selectCount(Map<String,String> map);
    
    List<Map<String,Object>> select94BeforeHandDataCfm(Map<String, Object> inputMap);
    
    List<Map<String,Object>> selectExchangeReqCfmData(Map<String, Object> inputMap);
    
    int updateGenerateStaAndTime(Map<String, Object> intoMap);

	int updateSendStaAndTime(Map<String, Object> intoMap);

	List<Map<String, Object>> selectTransStatistics(Map<String, Object> map);

	List<Map<String, Object>> select26VolDetailDataCfm(Map<String, Object> query);

	Integer queryExchenageSamsSerialnoCount(Map<String, Object> query);

	Integer queryFundVolDetailSamsSerialnoCount(Map<String, Object> query);

	List<Map<String, Object>> getMultipleDetail(Map<String, Object> map);

	List<Map<String, Object>> getMultipleCfmDetail(Map<String, Object> map);
	
}