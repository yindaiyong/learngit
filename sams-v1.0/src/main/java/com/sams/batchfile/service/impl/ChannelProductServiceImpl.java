package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.ChannelProductService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ChannelProductMapper;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.result.ChannelProductInfo;

@Service
public class ChannelProductServiceImpl implements ChannelProductService{

	@Autowired
	private ChannelProductMapper channelProductMapper;
	
	@Override
	public ChannelProduct selectByKey(Object key) {
		Long cpId = Long.parseLong(key.toString());
		return channelProductMapper.selectByPrimaryKey(cpId);
	}

	@Override
	public int save(ChannelProduct entity) {
		return channelProductMapper.insert(entity);
	}

	@Override
	public int delete(Object key) {
		Long cpId = Long.parseLong((String)key);
		return channelProductMapper.deleteByPrimaryKey(cpId);
	}

	@Override
	public int updateAll(ChannelProduct entity) {
		return channelProductMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateNotNull(ChannelProduct entity) {
		return 0;
	}

	@Override
	public List<ChannelProduct> selectByExample(Object example) {
		return null;
	}

	@Override
	public List<ChannelProductInfo> findChannelProductCondition(
			PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return channelProductMapper.findChannelProductCondition(pageInfo, condition);
	}

	@Override
	public ChannelProductInfo selectChannelProductInfo(String cpId) {
		return channelProductMapper.selectChannelProductInfo(cpId);
	}

	@Override
	public int selectChannelProductCount(String channelCode) {
		return channelProductMapper.selectChannelProductCount(channelCode);
	}

	@Override
	public int selectProductCount(String channelCode) {
		return channelProductMapper.selectProductCount(channelCode);
	}

	@Override
	public List<ChannelProduct> selectChannelProduct(Map<String, Object> map) {
		return channelProductMapper.selectChannelProduct(map);
	}

}
