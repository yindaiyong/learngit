package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.custom.model.ChannelInfo;

public interface ChannelInfoMapper {
    int deleteByPrimaryKey(Long ciId);

    int insert(ChannelInfo record);

    int insertSelective(ChannelInfo record);

    List<Map<String,Object>> selectByPrimaryKey(@Param("CHANNELCODE") String channelCode);
    
    ChannelInfo selectChannelInfo(@Param("CHANNELCODE") String channelCode);

    int updateByPrimaryKeySelective(ChannelInfo record);

    int updateByPrimaryKey(ChannelInfo record);

    List<Map<String,Object>> getAllChannelDataCount(String date);
    
    public List<Map<String, Object>> selectSubChannelList(@Param("CHANNELCODE") String channelCode);

    public List<Map<String, Object>> selectSubChannelToShow(@Param("CHANNELCODE") String channelCode);
}