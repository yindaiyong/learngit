package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;

public interface ContractService {
	 int deleteContractTmpDate(String channelCode,String businessDate);
	 int deleteContractReqDate(String channelCode,String businessDate);
	 int deleteContractCfmDate(String channelCode,String businessDate);
	 
	 int selectContractTmpCount(String channelCode,String businessDate);
	 int selectContractReqCount(String channelCode,String businessDate);
	 int selectContractCfmCount(String channelCode,String businessDate);
	 
	 int insertByBatchContractTmp(List<Map<String,Object>> contractList);
	 int insertByBatchContractReq(List<Map<String,Object>> contractList);
	 int insertByBatchContractCfm(List<Map<String,Object>> contractList);
	 
	 public Map<String,Object> saveDealTmpData(Map<String,Object> map);
	 public Map<String,Object> saveDealReqData(Map<String,Object> map);
	 public Map<String,Object> saveDealCfmData(Map<String,Object> map);
	 
	 public Map<String, Object> checkContractApplyBasicData(Map<String, Object> map);
	 public Map<String,Object>  checkContractBusinessData(Map<String, Object> map);
	 
	 public List<Map<String,Object>> getElContractData(PageInfo pageInfo, Map<String, Object> condition);
	Map<String, Object> saveContractCfmData(Map<String, Object> map);
}
