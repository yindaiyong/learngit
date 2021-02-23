package com.producer.mapper;

import java.util.List;

import com.common.dto.DrugDto;

public interface DrugDtoMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(DrugDto record);

    int insertSelective(DrugDto record);

    DrugDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DrugDto record);

    int updateByPrimaryKey(DrugDto record);

	List<Object> getDrugList(DrugDto drug);
}