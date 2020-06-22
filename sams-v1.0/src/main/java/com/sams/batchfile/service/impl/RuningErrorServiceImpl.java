package com.sams.batchfile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.RuningErrorService;
import com.sams.custom.mapper.RuningErrorMapper;
import com.sams.custom.model.RuningError;

@Service
public class RuningErrorServiceImpl implements RuningErrorService{

	@Autowired
	private RuningErrorMapper runingMapper;
	@Override
	public void insertRuningError(RuningError runingError) {
		runingMapper.insertSelective(runingError);
	}

}
