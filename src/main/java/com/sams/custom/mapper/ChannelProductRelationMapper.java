package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ChannelProductRelation;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.ChannelProductResult;

public interface ChannelProductRelationMapper {
    int deleteByPrimaryKey(Long cprId);

    int insert(ChannelProductRelation record);

    int insertSelective(ChannelProductRelation record);

    ChannelProductRelation selectByPrimaryKey(Long cprId);
    
    ChannelProductRelation selectByChannelCodeAndFunCode(Map<String,Object> map);

    int updateByPrimaryKeySelective(ChannelProductRelation record);

    int updateByPrimaryKey(ChannelProductRelation record);
    
    List<Map<String,Object>> selectYieldGrade(@Param("CHANNELCODE") String channelCode,@Param("FUNDCODE") String fundCode);
    
    List<Map<String,Object>> selectProductInfoByChannelCode(@Param("CHANNELCODE") String channelCode);
    
    List<Map<String,Object>> selectchannelProductCodeBytaProductCode(@Param("FUNDCODE") String fundCode,@Param("CHANNELCODE") String channelCode);
    
    List<ChannelManagerResult> findProductRealCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    public ChannelProductResult selectProductRealResult(Map<String,Long> map);
    
    Map<String,Object> selectSectionNumberAndFundRate(Map<String,Object> map);
    
    public int selectUsedReal(Map<String, Object> map);
    
}