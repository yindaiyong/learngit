package com.sams.batchfile.service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sams.batchfile.common.ResultBean;
import com.sams.common.web.PageInfo;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.ParticipationProfit;

public interface ExchangeProcessorService {
	
	public Map<String,Object> insertExchangeData(Map<String,Object> map);
	
	public Map<String, Object> deleteExchangeData(Map<String, Object> map);
	
	public Map<String,Object> insertExchangeDataReqTable(Map<String,Object> map);
	
	public  Map<String, Object> baseVerify(Map<String, Object> tempData, Map<String, InterfaceColInfo> colInfoMap,Map<String, Object> infoData);
	
	List<Map<String, Object>> querySendData(Map<String, Object> retMap);

	public int updateGenerateStaAndTime(Map<String, Object> intoMap);
	
	Map<String, Object> buildAndWriteData(Map<String, Object> retMap) throws Exception;

	public Map<String, Object> uploadAndUpStat(Map<String, Object> retMap);

	public String getNextWorkDay(String date,String marketCode);
	
	public String getLastWorkDay(String date,String marketCode);
	
	public String getCfmDay(String channelCode);
	
	public Map<String, Object> check94ApplyBasicData(Map<String, Object> fundCodeMap);

	public List<DayEndProcessor>  processorStepList(PageInfo pageInfo,
			Map<String, Object> condition);


	boolean checkDateIsWork(String date, String channelCode);

	public List<ParticipationProfit> queryProfitList(PageInfo pageInfo, String channelCodes,
			String date);

	public List<ParticipationProfit> queryProfitDetail(String channelCode,
			String date, String fundCode);

	public List<Map<String, Object>> selectTransStatistics(PageInfo pageInfo, Map<String, Object> map);

	public ResultBean<String> checkSendFile(String channelCode,
			Map<String, Object> map);

	public Map<String, Object> serialNoSamsCheck(Map<String, Object> retMap);

	public List<Map<String, Object>> getMultipleDetail(String channelCode,
			String fundCode, String businessCode, String startDate,
			String endDate);

	public String getFirstOpenDay(String channelCode, String fundCode,
			String transDate, String businessCode,String marketCode);

	public String uploadFiles(String channelCode, InputStream is, String fileType,String fileName);

	public List<Map<String, String>> checkFileStatus(String channelCode, String businessDate, String upType);

    public List<Map<String, String>> getFileStatusForPage(PageInfo pageInfo,String channelCode, String businessDate, String upType);

    public List<Map<String,String>> getFileLocalUrls(String channelCode,String tradeDate,List<Map<String,String>> fileUrlMaps);
}
