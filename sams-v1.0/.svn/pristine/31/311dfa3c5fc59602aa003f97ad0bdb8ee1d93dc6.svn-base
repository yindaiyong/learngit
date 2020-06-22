package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import com.sams.custom.model.ProcessingState;

import org.apache.ibatis.annotations.Param;

import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.ProcessStepInfo;

public interface ProcessStepInfoMapper {
	List<ProcessStepInfo>selectByFlowCode(ProcessStepInfo pProcessStepInfo);
	
	public List<Map<String,Object>> processStepInfo(Map<String, Object> processStepMap);

	List<DayEndProcessor> queryStepInfo(@Param("branchCode")String branchCode,@Param("confirmBranchCode")String confirmBranchCode,@Param("sendBranchCode")String sendBranchCode, @Param("condition")Map condition);

	String selectCurrentHandelDate(String channelCode);
	
	public ProcessStepInfo selectStepInfo(Map<String,Object> qryStepMap);

	Map<String, Object> queryDiffValueForMaxToLastStep(@Param("CHANNELCODE")String channelCode, @Param("TRANSDATE")String transdate);

	int returnStep(@Param("branchCode") String branchCode ,@Param("transDate") String transDate,
				   @Param("retStep") String retStep ,@Param("channelCode") String channelCode);

	int updateStepStatus(@Param("branchCode") String branchCode ,@Param("transDate") String transDate,
						 @Param("retStep") String retStep ,@Param("channelCode") String channelCode,
						 @Param("cUser") String cUser,@Param("cTime") String cTime,
						 @Param("status") String status,@Param("errorCode")String errorCode);

	ProcessingState selectStepState(@Param("branchCode") String branchCode , @Param("transDate") String transDate,
										 @Param("retStep") String retStep , @Param("channelCode") String channelCode);

	List<String> queryHasHandelChannel(Map<String, Object> map);
}