package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.CheckFileUpload;

public interface CheckFileUploadMapper {
    int insert(CheckFileUpload record);

    int insertSelective(CheckFileUpload record);
    
    public List<CheckFileUpload> findCheckFileUploadCondition(@Param("pageInfo")PageInfo pageInfo,@Param("condition") Map<String, Object> condition);
    
    public String selectTransDateMaxLineNumber(@Param("TRADEDATE")String tradeDate);
    
    public int insertUploadSuccessData(List<Map<String, Object>> insertList);
}