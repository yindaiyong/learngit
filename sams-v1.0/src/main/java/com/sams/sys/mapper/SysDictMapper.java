package com.sams.sys.mapper;

import com.sams.sys.model.SysDict;
import com.sams.sys.model.result.SysDictVo;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysDictMapper extends Mapper<SysDict> {

    //查找全部
    List<SysDictVo> findDicAlltVo(@Param("dictType")String dictType);
    
    //查找fundStatus
    List<Map<String,Object>> findFundStatus(@Param("dictType")String dictType);
    

    //通过条件查询
    List<SysDict> findByCondition(@Param("condition")Map<String, Object> condition);
    
    String findDictVo(@Param("dictType") String dictType);
    
    
    String findDictBydictCodeVo(@Param("dictType")String dictType,@Param("dictCode")String dictCode);

    int findDictCount(@Param("dictType")String dictType,@Param("dictCode")String dictCode);
    
	List<SysDict> findChildByDictType(String dictType);
	
	public List<Map<String, Object>> queryTaTcapType();

	List<SysDictVo> findDealTypeByChannelCode(@Param("CHANNELCODE") String channelCode);
	
	List<Map<String, Object>> getRoleId();
	
    List<Map<String,Object>> getSysDictInfo(@Param("dictType")String dictType);

    List<Map<String,Object>> selectByDictName(@Param("dictName") String dictName);
}