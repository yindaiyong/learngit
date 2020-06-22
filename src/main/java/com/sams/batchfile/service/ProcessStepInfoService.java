package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.ProcessStepInfo;

public interface ProcessStepInfoService {
	
	public Map<String, Object> selectByProcessStepInfo(String flowCode,String stepCode,Integer operType,String specialFile) throws Exception;

	public ProcessStepInfo selectStepInfo(Map<String,Object> qryStepMap);

	public Map<String, Object> checkProcessStepInfoCount(List<ProcessStepInfo> list);

	public List<DayEndProcessor> queryProcessStat(PageInfo pageInfo, Map<String, Object> condition);

	public List<ProcessStepInfo> selectByFlowCode(String branchCode);
	
	public List<Map<String,Object>> processStepInfo(Map<String, Object> processStepMap);

	public void dataRoolback(String channelCode,String transDate);

	public String returnProcessStep(String branchCode ,String transDate,String retStep ,String channelCode,String returnProcessStep);

	public List<String> queryHasHandelChannel(List<String> channelCodeList,
			String date,String flowCode);

}