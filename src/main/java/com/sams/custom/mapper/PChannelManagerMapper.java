package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.result.ChannelManagerResult;

public interface PChannelManagerMapper extends BaseMapper<ChannelManagerResult>{
    int deleteByPrimaryKey1(Long cmId);

    int insert1(PChannelManager record);

    int insertSelective1(PChannelManager record);

    PChannelManager selectByPrimaryKey1(Long cmId);
    
    PChannelManager selectByChannelCode1(Map<String,Object> map);

    int updateByPrimaryKeySelective1(PChannelManager record);

    int updateByPrimaryKey1(PChannelManager record);
    
	List<ChannelManagerResult> findChannelManagerCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	public ChannelManagerResult selectChannelManagerResult(String cmId);
	
	public List<Map<String, Object>> selectTaManager();
	
	int selectChannelManagerCount(Map<String,Object> map);
}