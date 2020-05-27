package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import com.sams.custom.mapper.ChannelInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.ChannelInfoService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.model.PchannelInfo;

@Service
public class ChannelInfoServiceimpl implements ChannelInfoService{

	@Autowired
	private PchannelInfoMapper pChannelInfoMapper;

	@Autowired
	private ChannelInfoMapper channelInfoMapper;

	@Override
	public PchannelInfo selectByKey(Object key) {
		Long ciId = Long.parseLong(key.toString());
		return pChannelInfoMapper.selectByPrimaryKey(ciId);
	}

	@Override
	public int save(PchannelInfo entity) {
		return pChannelInfoMapper.insert(entity);
	}

	@Override
	public int delete(Object key) {
		Long ciId = Long.parseLong((String)key);
		return pChannelInfoMapper.deleteByPrimaryKey(ciId);
	}

	@Override
	public int updateAll(PchannelInfo entity) {
		return pChannelInfoMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateNotNull(PchannelInfo entity) {
		return 0;
	}

	@Override
	public List<PchannelInfo> selectByExample(Object example) {
		return null;
	}

	@Override
	public List<PchannelInfo> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return pChannelInfoMapper.findPchannelInfoCondition(pageInfo, condition);
	}

	@Override
	public int selectByCode(String ciChannelCode) {
		return pChannelInfoMapper.selectByCode(ciChannelCode);
	}
	
	@Override
	public PchannelInfo selectByChannelCode(Map<String,Object> map) {
		return pChannelInfoMapper.selectByChannelCode(map);
	}

	@Override
	public PchannelInfo queryChannelInfoByChannelCode(String channelCode) {
		return pChannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
	}

	public List<Map<String, Object>> getAllChannelDataCount(String date){
		List<Map<String, Object>> allChannelDataCount = channelInfoMapper.getAllChannelDataCount(date);
		return allChannelDataCount;
	}

	@Override
	public List<Map<String, Object>> selectSubChannelList(String channelCode) {
		List<Map<String, Object>> subChannelList = channelInfoMapper.selectSubChannelList(channelCode);
		return subChannelList;
	}



	@Override
	public List<Map<String, Object>> selectSubChannelToShow(String channelCode) {
		List<Map<String, Object>> subChannelList = channelInfoMapper.selectSubChannelToShow(channelCode);
		return subChannelList;
	}



}
