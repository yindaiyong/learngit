package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

public interface FundAccountReconCfmService {
	 
	 int deleteByChannelCodeBusinessDateCfm(String channelCode,String businessDate);
	 
	 int insertByBatchCfm(List<Map<String,Object>> attachmentTables);
	 
	 int selectByChannelCodeBusinessDateCountCfm(String channelCode,String businessDate);
	 
	 public Map<String,Object> saveDealCfMData(Map<String,Object> map);
	 
	 public Map<String, Object> checkFundAccountReconApplyBasicData(Map<String, Object> map);

	public Map<String, Object> checkFundShareDetailCfm(Map<String, Object> map);

	void backUpAccountRecon(Map<String, Object> map) throws Exception;

	void updateRelAllowRedemptDate(String channelCode, String transDate,
			String ciMarketCode);
	 
}