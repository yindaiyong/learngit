package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.FundDividendCfm;
import com.sams.custom.model.ParticipationProfit;

public interface FundDividendCfmMapper {
    int deleteByPrimaryKey(Long dcId);

    int insert(FundDividendCfm record);

    int insertSelective(FundDividendCfm record);

    FundDividendCfm selectByPrimaryKey(Long dcId);

    int updateByPrimaryKeySelective(FundDividendCfm record);

    int updateByPrimaryKey(FundDividendCfm record);
    
    int insertByBatchCfm(List<Map<String,Object>> attachmentTables);
    
    int selectByChannelCodeBusinessDateCountCfm(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String businessDate);
    
	int deleteByChannelCodeBusinessDateCfm(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String businessDate);
	
    List<Map<String,Object>> selectfundDividendCfm(Map<String,Object> inputMap);
	
	int updateGenerateStaAndTime(Map<String,Object> inputMap);
	
	int updateSendStaAndTime(Map<String,Object> inputMap);

	List<ParticipationProfit> selectTransProfitList(
			@Param("channelCodeList")List<String> channelCodeList, @Param("date")String date);

	List<ParticipationProfit> selectDividendList(@Param("channelCodeList")List<String> channelCodeList,
			@Param("date")String date);

	List<ParticipationProfit> selectTransProfitDetail(@Param("channelCode")String channelCode,
			@Param("date")String date, @Param("fundCode")String fundCode);

	List<ParticipationProfit> selectDividendDetail(@Param("channelCode")String channelCode,
			@Param("date")String date, @Param("fundCode")String fundCode);

	List queryfundDividendInfo(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	Integer queryDividendSamsSerialnoCount(Map<String, Object> query);

	FundDividendCfm selectByTransactionAccountID(@Param("transactionAccountID") String transactionAccountID,
												 @Param("fundCode")String fundCode,
												 @Param("transDate") String transDate,
												 @Param("channelCode") String channelCode);
}