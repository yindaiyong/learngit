package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;

public interface FundAccountReconCfmMapper {

    int insertByBatchCfm(List<Map<String,Object>> attachmentTables);
    
    int selectByChannelCodeBusinessDateCountCfm(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    
	int deleteByChannelCodeBusinessDateCfm(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
	
	
	List<Map<String,Object>> selectFundAccountReconCfm(Map<String,Object> inputMap);
	
    int updateGenerateStaAndTime(Map<String,Object> inputMap);
    
    int updateSendStaAndTime(Map<String,Object> inputMap);
    
    int selectPIPROENDDATEGTTransDate(Map<String,Object> inputMap);
    
    List<Map<String,Object>>  selectFixedIncome(Map<String,Object> inputMap);
    
    int selectCheckFixedIncome(Map<String,Object> inputMap);
    
    List<Map<String,Object>>  selectCashIncome(Map<String,Object> inputMap);
    
    int selectCheckCashIncome(Map<String,Object> inputMap);

	List<Map<String, Object>> queryAccontReconInfo(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	int queryCheckStockIncome(Map<String, Object> checkInputMap);

	List<String> queryHasShareFundAcco(Map<String, Object> fixedIncomeParam);

	Integer queryAccountReconSamsSerialnoCount(Map<String, Object> query);

	int queryCheckMultiIncome(Map<String, Object> checkInputMap);

    String selectFundAccountByTransDate(@Param("channelCode") String channelCode,@Param("fundCode")String fundCode,
                                     @Param("accountId")String accountId,@Param("transDate")String transDate);

	List<Map<String, Object>> selectCurrencyFIncome(Map<String, Object> query);

	int queryCanDividendContract(Map<String, Object> fixedIncomeParam);

	int queryCheckMoneyFIncome(Map<String, Object> checkInputMap);

	int insertBackUpAccountRecon(Map<String, Object> map);

	int deleteBackUpAccountRecon(Map<String, Object> map);

	int queryCheckNewIncome(Map<String, Object> checkInputMap);

}