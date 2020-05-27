package com.sams.custom.mapper;

import org.apache.ibatis.annotations.Param;

import com.sams.custom.model.DayEndTransDate;

public interface DayEndTransDateMapper {
    int deleteByPrimaryKey(Long dtId);

    int insert(DayEndTransDate record);

    int insertSelective(DayEndTransDate record);

    DayEndTransDate selectByPrimaryKey(Long dtId);

    int updateByPrimaryKeySelective(DayEndTransDate record);

    int updateByPrimaryKey(DayEndTransDate record);

	DayEndTransDate selectByChannelCode(@Param("channelCode")String channelCode);

	void updateByChannelCode(DayEndTransDate dto);
}