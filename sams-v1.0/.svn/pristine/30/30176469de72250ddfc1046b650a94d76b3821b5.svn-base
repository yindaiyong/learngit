package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.CloseDate;

public interface CloseDateService{
	public Map<String,Object> checkChannelInfoTradeDate(Map<String,Object> inputMap);
	
    public int selectByMarketCodeAndcdCloseDate(CloseDate closeDate);
    
    public List<CloseDate> findDataGrid(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    public List<CloseDate> findClodeDateByCodeDate(Map<String,String> map);
    
    public int deleteCloseDate(String year,String month,String marketCode); 
    
    public int save(CloseDate entity);
    
    public int updateByPrimaryKey(List<CloseDate> list);
			
}
