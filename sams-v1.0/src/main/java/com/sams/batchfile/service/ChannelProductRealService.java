package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.ChannelProductRelation;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.ChannelProductResult;

public interface ChannelProductRealService extends BaseService<ChannelProductRelation>{

	List<ChannelManagerResult> findDataGrid(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	public List<ChannelProduct> selectProductRealByChannel(Map<String,Object> map);
	
	public ChannelProductResult selectChannelManagerResult(Map<String,Long> map);
	
	public int selectUsedReal(Map<String,Object> map);
	
}
