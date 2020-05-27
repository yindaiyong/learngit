package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import com.sams.custom.model.AccountInfo;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
    int deleteByPrimaryKey(Long aiId);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Long aiId);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
    
    int deleteAccountInfo();

	int insertAllAccountInfo();

	List<Map<String, Object>> selectAccountStatus();

	int updateAccountInfo(List<Map<String, Object>> upList);

	int insertAccountInfo(Map<String, Object> account);

	AccountInfo selectAccountInfo(@Param("channelCode")String channelCode, @Param("certId")String certId,
                                  @Param("certType")String certType, @Param("type")String type);
}