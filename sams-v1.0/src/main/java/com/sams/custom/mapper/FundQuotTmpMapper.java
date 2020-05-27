package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.custom.model.FundQuotTmp;

public interface FundQuotTmpMapper {
    int truncateFundQuotTmp();

    int insertSelective(List<FundQuotTmp> list);

    public List<Map<String, Object>> selectAllFundQuotTmp(@Param("fqChannelCode") String fqChannelCode,@Param("fqExchangeDate") String fqExchangeDate);
}