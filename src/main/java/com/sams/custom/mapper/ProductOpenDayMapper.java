package com.sams.custom.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.ProductOpenDay;

public interface ProductOpenDayMapper {
    int insert(ProductOpenDay record);

    int insertSelective(ProductOpenDay record);
    
    public List<ProductOpenDay> findDataGrid(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    public List<ProductOpenDay> qryProOpenDayList(Map<String, Object> qryMap);
    
    public int deleteProOpenDay(Map<String, Object> qryMap);
    
    public int updateProOpenDay(ProductOpenDay openDay);
    
    public int updateAllProOpenDay(List<ProductOpenDay> proOpenDayList);

    List<ProductOpenDay> selectOpenDayMonList(HashMap<String,Object> map);

    int updateStatusByMonthIndex(Map<String,String> map);

    int insertByBatch(List<ProductOpenDay> productOpenDays);

    int deleteCloseDateByDate(ProductOpenDay productOpenDay);

	int queryIsOpenDay(Map<String, Object> query);
	
	public int selectMonthOpenDayCount(Map<String,Object> qryMap);
}