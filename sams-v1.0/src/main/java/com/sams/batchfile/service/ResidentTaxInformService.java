package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

public interface ResidentTaxInformService {
	 int deleteTresidentTaxInformTmpDate(String channelCode,String businessDate);
	 int deleteTresidentTaxInformReqDate(String channelCode,String businessDate);
	 int deleteTresidentTaxInformCfmDate(String channelCode,String businessDate);
	 
	 int selecTresidentTaxInformTmpCount(String channelCode,String businessDate);
	 int selecTresidentTaxInformReqCount(String channelCode,String businessDate);
	 int selecTresidentTaxInformCfmCount(String channelCode,String businessDate);
	 
	 int insertByBatchTresidentTaxInformTmp(List<Map<String,Object>> tresidentTaxInformTmpList);
	 int insertByBatchTresidentTaxInformReq(List<Map<String,Object>> tresidentTaxInformReqList);
	 int insertByBatchTresidentTaxInformCfm(List<Map<String,Object>> tresidentTaxInformCfmList);
	 
	 public Map<String,Object> saveDealTmpData(Map<String,Object> map);
	 public Map<String,Object> saveDealReqData(Map<String,Object> map);
	 public Map<String,Object> saveDealCfMData(Map<String,Object> map);
	 
	 public Map<String, Object> checkResidentTaxInformApplyBasicData(Map<String, Object> map);
	 public Map<String, Object> saveResidentTaxInformCfmData(Map<String, Object> map);
}
