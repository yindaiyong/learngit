package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ChannelProductRelation;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.result.ChannelManagerResult;

public interface CloseDateMapper {
    int selectByMarketCodeAndcdCloseDate(CloseDate closeDate);
    
    List<CloseDate> findCloseDateCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    int insert(CloseDate record);

    int updateByPrimaryKey(List<CloseDate> list);
    
    public List<CloseDate> findClodeDateByCodeDate(Map<String,String> map);
    
    int deleteCloseDate(@Param("YEAR")String year, @Param("MONTH")String month,@Param("MARKETCODE")String marketCode);

    List<CloseDate> selectMonList(@Param("YEAR")String year, @Param("MARKETCODE")String marketCode);

    int updateStatusByMonthIndex(Map<String,String> map);

    /**
     * @Description 删除某一天的非交易日配置（对于已经配置过交易日的月份，所有本月的删除新增 = 修改）
     * @Author weijunjie
     * @Date 2019/11/28 9:15
     **/
    int deleteCloseDateByDate(CloseDate closeDate);

    int insertByBatch(List<CloseDate> list);
    
}