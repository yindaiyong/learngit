package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.DealInfoEdit;
import com.sams.custom.model.PchannelInfo;

public interface DealInfoEditMapper {
    int deleteByPrimaryKey(Long iveId);

    int insert(DealInfoEdit record);

    int insertSelective(DealInfoEdit record);

    DealInfoEdit selectByPrimaryKey(Long iveId);
    
    int updateTagTable(Map<String,Object> inputMap);

    int updateByPrimaryKeySelective(DealInfoEdit record);

    int updateByPrimaryKey(DealInfoEdit record);
    
    Map<String,Object> selectTableColName(Map<String,Object> inputMap);
    
    List<PchannelInfo> findInfoCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	List<DealInfoEdit> selectinfoByIds(@Param("ciIdss")String[] ciIdss);

	int checkInfo(@Param("check")List<DealInfoEdit> check);
}