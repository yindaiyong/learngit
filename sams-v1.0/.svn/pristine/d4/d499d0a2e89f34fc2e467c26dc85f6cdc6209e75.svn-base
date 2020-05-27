package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.result.ChannelManagerResult;

public interface PChannelManagerService extends BaseService<PChannelManager>{

	List<ChannelManagerResult> findDataGrid(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	public ChannelManagerResult selectChannelManagerResult(String cmId);
	
	int selectChannelManagerCount(Map<String,Object> map);
}
