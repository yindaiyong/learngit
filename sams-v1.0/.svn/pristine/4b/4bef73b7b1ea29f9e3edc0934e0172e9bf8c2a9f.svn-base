package com.sams.sys.service;

import java.util.List;

import com.sams.common.web.Tree;
import com.sams.common.web.service.BaseService;
import com.sams.sys.model.SysResource;
import com.sams.sys.model.result.SysResourceVo;

/**
 * 资源管理
 * @author hq 2016-8-12 15:28:51
 */
public interface SysResourceService extends BaseService<SysResource>{
	
	/**
	 * 获取所有资源树
	 * @return
	 */
	public List<Tree> findAllResouceTree(String resourceType);
	
	/**
	 * 获取用户菜单(平滑数据格式)--通过用户Id
	 * @param userId
	 * @return
	 */
	public List<Tree> findTreeByUserId(String userId, String resourceType);
	
	/**
	 * 通过角色Id获取资源id
	 * @param roleId
	 * @return
	 */
	List<String> findResourceIdByRoleId(String roleId);
	
	/**
	 * 通过角色Id获取资源编号(自定义)
	 * @param roleId
	 * @param resourceStatus
	 * @return
	 */
	List<String> findPermCodeListByRoleId(String roleId, String resourceStatus);
	
	/**
     * 查询所有资源
     *
     * @return
     */
	List<SysResourceVo> findAllResourceVo();

	 /**
	 * 通过角色Id获取拥有权限的资源Id
	 * @return
	 */
	List<String> findResourceIdListByRoleId(String roleId);
	
	
	/**
     * 查询所有资源列表
     *
     * @return
     */
	List<SysResource> findAllResourceVoList();

}
