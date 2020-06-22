package com.sams.custom.mapper;

import com.sams.custom.model.ChannelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RollBackChannelMapper {

    int deleteFromProductInfo(@Param("channelCode") String channelCode);

    int deleteFromChannelProduct(@Param("channelCode") String channelCode);

    int deleteFromCPR(@Param("channelCode") String channelCode);

    int deleteFromAccountStat(@Param("channelCode") String channelCode);

    int deleteFromContractInfo(@Param("channelCode") String channelCode);

    int deleteFromAccountReq(@Param("channelCode") String channelCode);

    int deleteFromAccountReqCfm(@Param("channelCode") String channelCode);

    int deleteFromExchangeReq(@Param("channelCode") String channelCode);

    int deleteFromExchangeReqCfm(@Param("channelCode") String channelCode);

    int deleteFromContractReq(@Param("channelCode") String channelCode);

    int deleteFromContractCfm(@Param("channelCode") String channelCode);

    int deleteFromRTIR(@Param("channelCode") String channelCode);

    int deleteFromRTIC(@Param("channelCode") String channelCode);

    int deleteFromDividendCfm(@Param("channelCode") String channelCode);

    int deleteFromPOD(@Param("channelCode") String channelCode);

    int deleteFromAccountRecon(@Param("channelCode") String channelCode);

    int deleteFromFundVolCfm(@Param("channelCode") String channelCode);

    int deleteFromAccountInfo(@Param("channelCode") String channelCode);

}