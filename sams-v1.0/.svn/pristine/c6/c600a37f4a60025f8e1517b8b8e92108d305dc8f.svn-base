package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.mapper.CloseDateMapper;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.ProductOpenDay;
import com.sams.custom.model.ProductTemplate;

public interface ProductOpenDayService extends BaseService<ProductOpenDay>{

    public List<ProductOpenDay> findDataGrid(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    public int updateAllProOpenDay(List<ProductOpenDay> proOpenDayList);
    
    public boolean checkTransDateIsOpenDay(Map<String,Object> qryMap);
    
    public String checkOpenDayType(Map<String,Object> qryMap);
    
    public int selectMonthOpenDayCount(Map<String,Object> qryMap);
}
