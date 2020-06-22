package com.sams.batchfile.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.ProcessingStateService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ProcessingStateMapper;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.ProcessingState;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.MarketHandingResult;
@Service
public class ProcessingStateServiceImpl implements ProcessingStateService{

	@Autowired
	private ProcessingStateMapper ProcessingStateMapper;

	@Override
	public void insertProcessingState(ProcessingState processingState) {
		ProcessingStateMapper.insertSelective(processingState);
	}

	@Override
	public ProcessingState selectByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(ProcessingState entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(ProcessingState entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(ProcessingState entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProcessingState> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public PageInfo findMarketHandingCondition(PageInfo pageInfo,
			Map<String, Object> condition) {
		List<MarketHandingResult> list = ProcessingStateMapper.findMarketHandingCondition(pageInfo, condition);
		Map<String,MarketHandingResult> map = Maps.newHashMap();
		List<MarketHandingResult> ProcessingStateList = new ArrayList<MarketHandingResult>();
		
		for(MarketHandingResult p:list){
			map.put(p.getPsChannelCode(), p);
		}
				Iterator it = map.entrySet().iterator() ;
		while (it.hasNext())
		{
		Map.Entry entry = (Map.Entry) it.next() ;
		Object value = entry.getValue();
		ProcessingStateList.add((MarketHandingResult)value);
		}
		//分页  和按条件查询
	    PageInfo page  = pageAndCondition(ProcessingStateList,pageInfo,condition);
		return page;
	}*/
	
	
	public List<MarketHandingResult> findMarketHandingCondition(
			PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return ProcessingStateMapper.findMarketHandingCondition(pageInfo, condition);
	}
	
	/**
	 * 查询和分页  
	 * @Title: pageAndCondition   
	 * @author: yindy 2019年5月31日 下午3:08:57
	 * @param: @param retList
	 * @param: @param pageInfo
	 * @param: @param condition
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	private PageInfo pageAndCondition(
			List<MarketHandingResult> retList, PageInfo pageInfo,
			Map<String, Object> condition) {
		//分页
		List<Object> returnList = FileDataUtil.getPage(pageInfo,retList);
		pageInfo.setRows(returnList);
		pageInfo.setTotal(retList.size());
		return pageInfo;
	}

	@Override
	public List<MarketHandingResult> findMarketHandGuiList(String tradeDate){
		List<MarketHandingResult> list  = new ArrayList<>();
		list = ProcessingStateMapper.findMarketHandGuiList(tradeDate);
		return list;
	}

	@Override
	public List<MarketHandingResult> findMarketHandingHistory(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return ProcessingStateMapper.findMarketHandingHistory(pageInfo, condition);
	}

	@Override
	public List<String> checkChannelCodeList(Map<String, Object> qryMap) {
		List<String> channelCodeList = ProcessingStateMapper.checkChannelCodeList(qryMap);
		return channelCodeList;
	}
	
}
