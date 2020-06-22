package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.InterfaceInfo;

public interface InterfaceColInfoMapper {

    InterfaceColInfo selectByInterfaceCode(Map interfaceCode);
    
    InterfaceColInfo queryCsdcColInfoByPrimary(long icId);
    
    List<InterfaceColInfo> selectByInterfaceCodeList(Map interfaceCode);
    
    List<InterfaceColInfo> csdcInfoColList(@Param("pageInfo")PageInfo pageInfo,@Param("condition")Map<String, Object> condition);
    
    Integer queryCsdcColInfo(InterfaceColInfo interfaceColInfo);
    
    Integer queryMaxOrderByInterfaceCode(String iiInterfaceCode);
    
    //基本信息

    int insert(InterfaceInfo record);

    int updateByPrimaryKey(InterfaceInfo record);

	List<InterfaceInfo> csdcInfoBaseList(@Param("pageInfo")PageInfo pageInfo,@Param("condition")Map<String, Object> condition);

	Integer queryCsdcBaseInfo(InterfaceInfo interfaceInfo);

	InterfaceInfo queryCsdcBaseInfoByPrimary(long iiId);

	List<Map<String, Object>> queryBaseCsdcCombox();

	int updateCsdcColInfo(InterfaceColInfo interfaceColInfo);

	int insertColInfo(InterfaceColInfo interfaceColInfo);

	String selectNextSequence();

	List<Map<String, Object>> queryCardTypeCombox(@Param("type")String type);

	List<Map<String, Object>> queryChannelInfoComBox();
	
	List<Map<String, Object>> queryAllChannelInfoComBox();

	List<InterfaceColInfo> selectColInfoByCode(String code);

	Long selectRandomSequence();
	
	Long selectOutContractSequence();

}