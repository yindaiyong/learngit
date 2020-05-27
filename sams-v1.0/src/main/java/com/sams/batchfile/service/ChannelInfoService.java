package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProductTemplate;

public interface ChannelInfoService extends BaseService<PchannelInfo>{

	public List<PchannelInfo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);
	
	int selectByCode(String ciChannelCode);
	
	public PchannelInfo selectByChannelCode(Map<String,Object> map) ;
	
	public PchannelInfo queryChannelInfoByChannelCode(String channelCode);

	public List<Map<String, Object>> getAllChannelDataCount(String date);
	
	public List<Map<String, Object>> selectSubChannelList(String channelCode);

	public List<Map<String, Object>> selectSubChannelToShow(String channelCode);

}
