package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.DResidentTaxInformReq;
import com.sams.custom.model.result.UnResidentInfoRes;

import org.apache.ibatis.annotations.Param;

public interface ResidentTaxInformMapper {
	int deleteTresidentTaxInformTmpDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
	int deleteTresidentTaxInformReqDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
	int deleteTresidentTaxInformCfmDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
	 
    int selecTresidentTaxInformTmpCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    int selecTresidentTaxInformReqCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    int selecTresidentTaxInformCfmCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    
    int insertByBatchTresidentTaxInformTmp(List<Map<String,Object>> tresidentTaxInformTmpList);
	int insertByBatchTresidentTaxInformReq(List<Map<String,Object>> tresidentTaxInformReqList);
	int insertByBatchTresidentTaxInformCfm(List<Map<String,Object>> tresidentTaxInformCfmList);
	
    List<Map<String,Object>> selectResidentTaxInformTmp(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    List<Map<String,Object>> selectResidentTaxInformReq(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    List<Map<String,Object>> selectResidentTaxInformCfm(Map<String,Object> inputMap);
    
    int updateGenerateStaAndTime(Map<String,Object> inputMap);
    int updateSendStaAndTime(Map<String,Object> inputMap);

    List<UnResidentInfoRes> selectUnResidentInfo(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    Integer queryResidentSamsSerialnoCount(Map<String, Object> query);

    DResidentTaxInformReq selectResidentTaxInfo(@Param("channelCode")String channelCode, @Param("type")String type,
                                                @Param("accountId")String accountId);
}