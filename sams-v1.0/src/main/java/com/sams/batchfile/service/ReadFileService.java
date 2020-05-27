package com.sams.batchfile.service;

import java.util.Map;

import com.sams.custom.model.PchannelInfo;

public interface ReadFileService {
	
	public Map<String, Object> readIndexFile(String filePath,String date, PchannelInfo channelInfo);

	public Map<String,Object> readFile(String filePath,String fileType,String date,PchannelInfo channelInfo);
}
