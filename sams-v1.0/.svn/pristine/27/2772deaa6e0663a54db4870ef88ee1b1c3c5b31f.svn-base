package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.ChannelProductRealService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ChannelProductMapper;
import com.sams.custom.mapper.ChannelProductRelationMapper;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.ChannelProductRelation;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.ChannelProductResult;

@Service
public class ChannelProductRealServiceImpl implements ChannelProductRealService{

	@Autowired
	private ChannelProductRelationMapper  channelProductRealMapper;
	@Autowired
	private ChannelProductMapper  channelProductMapper;
	@Override
	public ChannelProductRelation selectByKey(Object key) {
		Long cprId = Long.parseLong((String)key);
		return channelProductRealMapper.selectByPrimaryKey(cprId);
	}

	@Override
	public int save(ChannelProductRelation entity) {
		return channelProductRealMapper.insert(entity);
	}

	@Override
	public int delete(Object key) {
		Long cprId = Long.parseLong((String)key);
		return channelProductRealMapper.deleteByPrimaryKey(cprId);
	}

	@Override
	public int updateAll(ChannelProductRelation entity) {
		return channelProductRealMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateNotNull(ChannelProductRelation entity) {
		return 0;
	}

	@Override
	public List<ChannelProductRelation> selectByExample(Object example) {
		return null;
	}

	@Override
	public List<ChannelManagerResult> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return channelProductRealMapper.findProductRealCondition(pageInfo, condition);
	}

	@Override
	public ChannelProductResult selectChannelManagerResult(Map<String,Long> map) {
		return channelProductRealMapper.selectProductRealResult(map);
	}

	@Override
	public List<ChannelProduct> selectProductRealByChannel(
			Map<String, Object> map) {
		return channelProductMapper.selectProductByChannel(map);
	}

	@Override
	public int selectUsedReal(Map<String, Object> map) {
		return channelProductRealMapper.selectUsedReal(map);
	}

}
