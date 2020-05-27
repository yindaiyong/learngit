package com.sams.custom.mapper;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.AccountReq;
import com.sams.custom.model.DFxqUnresidentData;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DFxqUnresidentDataMapper {
    int deleteByPrimaryKey(BigDecimal fudId);

    int insert(DFxqUnresidentData record);

    int insertSelective(DFxqUnresidentData record);

    DFxqUnresidentData selectByPrimaryKey(BigDecimal fudId);

    int updateByPrimaryKeySelective(DFxqUnresidentData record);

    int updateByPrimaryKey(DFxqUnresidentData record);

    int insertByBatch(List<DFxqUnresidentData> list);

    List<DFxqUnresidentData> selectDataByType(@Param("channelCode") String channelCode,
                                              @Param("type")String type,
                                              @Param("date")String date);

    List<DFxqUnresidentData> getAllDataToShow(@Param("condition") Map<String, Object> condition,
                                              @Param("pageInfo")PageInfo pageInfo);

    AccountReq selectAccountReq(@Param("channelCode")String channelCode,@Param("certId")String certId,
                                @Param("certType")String certType,@Param("type")String type);

    int updateDowloadTime(@Param("channelCode") String channelCode,
                                 @Param("type")String type,
                                 @Param("date")String date,
                                 @Param("dowTime")String dowTime);

}