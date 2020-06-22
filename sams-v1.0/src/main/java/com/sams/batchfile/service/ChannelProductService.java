package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.result.ChannelProductInfo;

public interface ChannelProductService  extends BaseService<ChannelProduct>{

	 List<ChannelProductInfo> findChannelProductCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition); 
	 
	 public ChannelProductInfo selectChannelProductInfo(String cpId);
	 
	 int selectChannelProductCount(String channelCode);
	    
	 int selectProductCount(String channelCode);
	 
	 List<ChannelProduct> selectChannelProduct(Map<String,Object> map);
}
