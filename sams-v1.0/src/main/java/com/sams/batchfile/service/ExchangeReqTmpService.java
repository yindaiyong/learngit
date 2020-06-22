package com.sams.batchfile.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ExchangeReq;
import com.sams.custom.model.ProductSalePrarms;

public interface ExchangeReqTmpService {
	 int deleteTransTmpDate(String artChannelCode,String artBusinessDate);
	 int deleteTransReqDate(String artChannelCode,String artBusinessDate);
	 int deleteTransCfmDate(String artChannelCode,String artBusinessDate);
	
	 int selectTransTmpCount(String channelCode,String businessDate);
	 int selectTransReqCount(String channelCode,String businessDate);
	 int selectTransCfmCount(String channelCode,String businessDate);
	 
	 int insertByBatchTransTmp(List<Map<String,Object>> attachmentTables);
	 int insertByBatchTransReq(List<Map<String,Object>> attachmentTables);
	 int insertByBatchTransCfm(List<Map<String,Object>> attachmentTables);
	 
	 public Map<String,Object> saveDealTmpData(Map<String,Object> map);
	 public Map<String,Object> saveDealReqData(Map<String,Object> map);
	 public Map<String,Object> saveDealCfmData(Map<String,Object> map);
	 
	 
	 public Map<String, Object> checkTransactionApplyBasicData(Map<String, Object> map) throws Exception;
	 public Map<String, Object> checkTransactionApplyBusinessData(Map<String, Object> map) throws Exception ;
	 
     public Map<String, Object> sendTransactionApplyDataToTA(Map<String, Object> map); 
     public Map<String, Object> saveTransactionData(Map<String, Object> map);
     
     public Map<String, Object> updateTransactionData(Map<String, Object> map);
     
     public Map<String, Object> getContractAndAccountInfo(Map<String, Object> tempAccount);
     
     public BigDecimal selectCount(Map<String,String> map);
     
     public Map<String,Object> send94FileDate(Map<String, Object> map);
     
     public List<ExchangeReq> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);
     
     public ProductSalePrarms getProductSaleParams(Map<String, Object> map,
			String fundType, String channelCode, String fundCode);
     
     public Map<String,Object> getRemainshares(Map<String,Object> qryMap);
}
