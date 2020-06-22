package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.AccountReq;

import org.apache.ibatis.annotations.Param;

public interface AccountReqTmpMapper {
	
	
    int deleteByChannelCodeBusinessDate(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);

    int selectByChannelCodeBusinessDateCount(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    
    int insertByBatch(List<Map<String,Object>> attachmentTables);
    
    int deleteByChannelCodeBusinessDateReq(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);

    int selectByChannelCodeBusinessDateCountReq(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);
    
    int insertByBatchReq(List<Map<String,Object>> attachmentTables);

	List<Map<String,Object>> selectAccoTempByDate(Map<String, Object> map);
	
	List<Map<String,Object>> selectAccoInfoByDate(Map<String, Object> map);
	
	int batchUpdateAccountReqData(@Param(value = "list") List<Map<String,Object>> accountList);

	Integer checkAccountSameData(Map<String, Object> queryMap);

	void updateAccoReq(Map<String, Object> account);

	List<Object> queryFailedInfo(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	List<Object> queryAccountFailedInfo(@Param("pageInfo") PageInfo pageInfo,@Param("condition")Map<String, Object> condition);
	
	public int updateAccountData(List<Map<String,Object>> list);

	int selectAccountReqOpenCount(Map<String, Object> account);

	List<Map<String, Object>> selectAccountReq(Map<String, Object> map);

	Map<String, Object> queryPaymentAccountInfo(Map<String, Object> tradeMap);

}