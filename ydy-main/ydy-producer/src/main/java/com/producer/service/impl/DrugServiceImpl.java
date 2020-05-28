package com.producer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dto.DrugDto;
import com.producer.mapper.DrugDtoMapper;
import com.producer.service.DrugService;

@Service
public class DrugServiceImpl implements DrugService{
	
	@Autowired
	private DrugDtoMapper drugDtoMapper;

	@Override
	public List<Object> getDrugList(DrugDto drug) {
		return drugDtoMapper.getDrugList(drug);
	}

}
