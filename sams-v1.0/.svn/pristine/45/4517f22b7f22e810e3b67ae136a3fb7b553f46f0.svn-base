package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import com.sams.custom.model.AccountStat;

public interface AccountStatMapper {
	

    int insert(AccountStat record);

    int insertSelective(AccountStat record);

    int updateByPrimaryKeySelective(AccountStat record);

    int updateByPrimaryKey(AccountStat record);
    
    AccountStat selectAccountStatus(Map<String, Object> tempAccount);
    
    AccountStat qrySendTaAccountStatus(Map<String, Object> tempAccount);

	String queryTAaccountInfo(Map<String, Object> account);

	String selectTAttrustcustagency(Map<String, Object> query);

	void updateOpenStat(Map<String, Object> account);

	List<Map<String, Object>> selectCheckAccountContract(
			Map<String, Object> inputMap);
}