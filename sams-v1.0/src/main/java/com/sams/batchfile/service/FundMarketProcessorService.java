package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;


public interface FundMarketProcessorService {
	
	public void  fundMarkerProcess(String tradeDate,String ChannelCode,String processStepNo,String stepCode,String flag);
	
	public Map<String, Object> insertProcessLog(Map<String, Object> inputMap,String ChannelCode);
	
	public void checkUploadFtpFiles(Map<String, Object> inputMap);

}
