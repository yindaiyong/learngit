package com.sams.custom.mapper;

import com.sams.custom.model.RuningError;

public interface RuningErrorMapper {
    int deleteByPrimaryKey(Long reId);

    int insert(RuningError record);

    int insertSelective(RuningError record);

    RuningError selectByPrimaryKey(Long reId);

    int updateByPrimaryKeySelective(RuningError record);

    int updateByPrimaryKey(RuningError record);
}