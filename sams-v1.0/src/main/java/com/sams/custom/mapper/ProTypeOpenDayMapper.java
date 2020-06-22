package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ProTypeOpenDay;

public interface ProTypeOpenDayMapper {
    int insert(ProTypeOpenDay record);

    int insertSelective(ProTypeOpenDay record);

	List<Map<String, Object>> getProductTypeCombox(Map<String, Object> map);

	List<ProTypeOpenDay> findProductOpenCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	List<ProTypeOpenDay> selectMonList(@Param("YEAR")String yearVal, @Param("PRODUCTTYPE")String productType);

	int insertByBatch(@Param("TYPEOPENDAY")List<ProTypeOpenDay> typeOpenDay);

	int deleteProductTypeOpenDay(Map<String, Object> map );

	int checkByBatch(Map<String, Object> map );
}