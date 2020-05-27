package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;

public interface FileInterfaceFieldService {
	
	InterfaceColInfo selectFileInterfaceField(Map interfaceColInfo);
	List<InterfaceColInfo> selectFileInterfaceFieldOrder(Map interfaceColInfo);
	Map<String,InterfaceColInfo> selectFileInterfaceFieldList(Map interfaceCode);
	public Map<String,InterfaceColInfo> selectColInfoMap(String fileType,String businessCode,PchannelInfo channelInfo);
}
