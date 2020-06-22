package com.sams.batchfile.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;


public interface AccountReqTmpService {
	
	 int deleteByChannelCodeBusinessDate(String artChannelCode,String artBusinessDate);
	 
	 int selectByChannelCodeBusinessDateCount(String channelCode,String businessDate);
	 
	 int insertByBatch(List<Map<String,Object>> attachmentTables);
	 
	 public Map<String,Object> dealTempData(Map<String,Object> map);
	 
	 int deleteByChannelCodeBusinessDateReq(String artChannelCode,String artBusinessDate);
	 
	 int selectByChannelCodeBusinessDateCountReq(String channelCode,String businessDate);
	 
	 int insertByBatchReq(List<Map<String,Object>> attachmentTables);
	 
	 public Map<String,Object> dealReqData(Map<String,Object> map);

	Map<String, Object> analyIndexFile(Map<String, Object> retMap);

	Map<String, Object> analyOfficiFile(Map<String, Object> retMap);

	Map<String, Object> checkAccoBusinessData(Map<String, Object> retMap);

	Map<String, Object> checkAccoBasicData(Map<String, Object> retMap) throws Exception;

	Map<String, Object> sendAccoDataToTA(Map<String, Object> retMap);
	
	public  Map<String, Object> getLocalAndTAAccountStatus(Map<String, Object> account);

	void accountProcessor(Map<String, Object> intoMap);

	public BigDecimal selectCount(Map<String,String> map);

	Map<String, Object> queryAndInsertCfm(Map<String, Object> intoMap);

	List<Object> queryFailedInfo(PageInfo pageInfo,Map<String, Object> condition);
	
	List<Object> queryFalseFileData(PageInfo pageInfo,Map<String, Object> condition);
	
	//交易数据更新状态
	public int updateTransData(List<Map<String,Object>> list);
	
	//账户数据更新状态
	public int updateAccountData(List<Map<String,Object>> list);
}
