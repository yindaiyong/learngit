package com.producer.service;

import java.util.List;

import com.common.dto.DrugDto;

public interface DrugService {

	List<Object> getDrugList(DrugDto drug);

}
