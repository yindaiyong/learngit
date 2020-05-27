package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.PProductInfo;

public interface ParProductInfoService extends BaseService<PProductInfo>{
	
	 public List<PProductInfo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);
	
	 List<PProductInfo> selectByProductCodeList(Map<String,Object> map);
	 
	 List<PProductInfo> selectBatchProductInfo(Map<String,Object> map);
	 
	 PProductInfo selectProductList(Map<String,Object> map);
	 
	 PProductInfo selectByProductCode(String productCode);
	 
	 long selectByConditionCount(Map<String,Object> map);
	 
	 Map<String, Object> updateAllAboutProduct(Map<String, Object> inputMap);
	 
	 int selectProductCount(String channelProductCode);
	 
	 int selectTaProductCount(String fundCode);
	 
	 public int updateConnetBatchNo(String productCode,
			String batchNo,String newBatchNo);

	public String checkSubChannelCode(String channelCode,String subChannelCode);
	
	public int countProductSubChannelCode(Map<String,Object> qryMap);

}
