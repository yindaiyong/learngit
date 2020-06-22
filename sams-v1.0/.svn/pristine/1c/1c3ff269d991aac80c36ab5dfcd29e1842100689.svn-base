package com.sams.custom.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.AccountReqCfm;

public interface AccountReqCfmMapper {
    int deleteByPrimaryKey(Long arcId);

    int insert(AccountReqCfm record);

    int insertSelective(AccountReqCfm record);

    AccountReqCfm selectByPrimaryKey(Long arcId);

    int updateByPrimaryKeySelective(AccountReqCfm record);

    int updateByPrimaryKey(AccountReqCfm record);

	void deleteCfmByCodeAndDate(Map<String, Object> query);

	int insertByBatchCfm(List<Map<String, Object>> list);

	List<Map<String, Object>> selectAccountCfmData(Map<String, Object> query);

	int updateGenerateStaAndTime(Map<String, Object> intoMap);
	
	List<Map<String,Object>> selectCheckAccountContract(Map<String, Object> inputMap);

	int updateSendStaAndTime(Map<String, Object> intoMap);
	
	public List<AccountReqCfm> selectByChannelAndSeriaNo(Map<String, Object> map);
    
	public BigDecimal selectCount(Map<String,String> map);
	
	Map<String, Object> selectByTAAccountId(@Param("TAACCOUNTID") String TAACCOUNTID,@Param("CHANNELCODE")String channelCode);

	Integer queryAccountSamsSerialnoCount(Map<String, Object> query);

}