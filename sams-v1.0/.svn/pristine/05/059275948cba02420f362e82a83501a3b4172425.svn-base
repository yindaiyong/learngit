package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.filter.AutoLoad;
import com.sams.batchfile.service.PChannelManagerService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.PChannelManagerMapper;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.result.ChannelManagerResult;

@Service
public class PChannelManagerServiceImpl implements PChannelManagerService{

	@Autowired
	private PChannelManagerMapper pChannelManagerMapper;
	@Override
	public PChannelManager selectByKey(Object key) {
		Long cmId = Long.parseLong(key.toString());
		return pChannelManagerMapper.selectByPrimaryKey1(cmId);
	}

	@Override
	public int save(PChannelManager entity) {
		return pChannelManagerMapper.insert1(entity);
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(PChannelManager entity) {
		// TODO Auto-generated method stub
		return pChannelManagerMapper.updateByPrimaryKey1(entity);
	}

	@Override
	public int updateNotNull(PChannelManager entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PChannelManager> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChannelManagerResult> findDataGrid(
			PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return pChannelManagerMapper.findChannelManagerCondition(pageInfo, condition);
	}

	@Override
	public ChannelManagerResult selectChannelManagerResult(String cmId) {
		return pChannelManagerMapper.selectChannelManagerResult(cmId);
	}

	@Override
	public int selectChannelManagerCount(Map<String, Object> map) {
		return pChannelManagerMapper.selectChannelManagerCount(map);
	}

}
