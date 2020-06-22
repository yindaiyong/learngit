package com.sams.sys.mapper;

import java.util.List;
import java.util.Map;

import com.sams.sys.model.result.SysUserRes;
import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.sams.common.annotation.DataSourceChange;
import com.sams.common.constant.Const;
import com.sams.common.web.PageInfo;
import com.sams.sys.model.SysUser;
import com.sams.sys.model.result.SysUserVo;

public interface SysUserMapper extends Mapper<SysUser>{
	
	/**
	 * 用户列表
	 * @param pageInfo 
	 * @param pageInfo
	 * @return
	 */
	List<SysUserRes> findUserPageCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	/**
     * 通过登录用户名查询用户
     *
     * @param loginname
     * @return
     */
	 @DataSourceChange(Const.DATASOURCE_MASTER)
	SysUser findUserByLoginName(String loginname);
	
	/**
	 * 通过用户Id查找用户信息
	 * @param userId
	 * @return
	 */
	SysUserVo findUserVoById(String userId);
	
}