package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.custom.model.AccountReqCfm;

public interface AccountReqCfmService {

	public List<AccountReqCfm> selectByChannelAndSeriaNo(Map<String,Object> map);

	Map<String, Object> selectAndWriteFile(Map<String, Object> intoMap);

	public void confirmProcessor(Map<String, Object> intoMap, String errorStep,
			Integer operType);
	
	public void backUpAndDelFile(String channelCode, String businessDate);
	
	
}
