package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;

public interface ContractMapper {
    int deleteContractTmpDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    int deleteContractReqDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    int deleteContractCfmDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    
    int selectContractTmpCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    int selectContractReqCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    int selectContractCfmCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);

    int insertByBatchContractTmp(List<Map<String,Object>> contractList);
    int insertByBatchContractReq(List<Map<String,Object>> contractList);
    int insertByBatchContractCfm(List<Map<String,Object>> contractList);

    List<Map<String,Object>> selectContractTmp(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    List<Map<String,Object>> selectContractReq(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate,@Param("VALIDFLAG") String validFlag);
    List<Map<String,Object>> selectContractCfm(Map<String,Object> inputMap);
    
    int updateGenerateStaAndTime(Map<String,Object> inputMap);
    int updateSendStaAndTime(Map<String,Object> inputMap);
	public List<Map<String,Object>> getElContractData(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	void batchUpdateContractReqData(
			List<Map<String, Object>> retList); 
			
	int upReturnBackElContract(List<Map<String,Object>> list);
	
	List<Map<String, Object>> queryContractInfo(Map<String, Object> inputMap);


}
