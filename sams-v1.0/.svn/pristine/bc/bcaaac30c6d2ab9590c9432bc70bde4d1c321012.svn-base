package com.sams.csdcInterfaceConfig.service;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.InterfaceInfo;

public interface CsdcInterfaceService {

	List<InterfaceInfo> csdcInfoBaseList(PageInfo pageInfo, Map<String, Object> condition);

	Integer queryCsdcBaseInfo(InterfaceInfo interfaceInfo);

	int insert(InterfaceInfo interfaceInfo);

	InterfaceInfo queryCsdcBaseInfoByPrimary(long iiId);

	int updateCsdcBaseInfo(InterfaceInfo interfaceInfo);

	String insertOrUpdateCsdcBaseInfo(InterfaceInfo interfaceInfo,String userName);

	List<InterfaceColInfo> csdcInfoColList(PageInfo pageInfo, Map<String, Object> condition);

	InterfaceColInfo queryCsdcColInfoByPrimary(long icId);

	String insertOrUpdateCsdcColInfo(InterfaceColInfo interfaceColInfo,String userName);

	List<DayEndProcessor> selectDayEndChannelStatus(String date);

}
