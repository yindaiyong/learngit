package com.sams.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.common.constant.Const;
import com.sams.common.utils.StringUtils;
import com.sams.common.web.Tree;
import com.sams.common.web.service.BaseServiceImpl;
import com.sams.sys.mapper.SysResourceMapper;
import com.sams.sys.mapper.SysUserRoleMapper;
import com.sams.sys.model.SysResource;
import com.sams.sys.model.result.SysResourceVo;
import com.sams.sys.service.SysResourceService;

@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    @Autowired
    private SysResourceMapper sysResourceMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Override
	public List<Tree> findAllResouceTree(String resourceType) {
		List<Tree> TreeList = new ArrayList<Tree>();
		List<SysResourceVo> resourceList = sysResourceMapper.findResourceVo(resourceType);
		for (SysResourceVo sysResourceVo : resourceList){
			if (sysResourceVo != null) {
				Tree tree = new Tree();
				tree.setId(sysResourceVo.getResourceId());
				String currentPid = sysResourceVo.getResourcePid();
				if (StringUtils.isNotBlanks(currentPid)) {
					tree.setPid(currentPid.toString());
				}
				tree.setText(sysResourceVo.getResourceName());
				tree.setIconCls(sysResourceVo.getIcon());
				Map<String,String> treeAttributes = new HashMap<String,String>();
				treeAttributes.put("url", sysResourceVo.getUrl());
				treeAttributes.put("resouceType", sysResourceVo.getResourceType());
				if (sysResourceVo.getCountAllChild() == 0){ //叶子节点
					treeAttributes.put("noteType", "left");
				}
				tree.setAttributes(treeAttributes);
				TreeList.add(tree);
			}
		}
		return TreeList;
	}
    
    @Override
	public List<Tree> findTreeByUserId(String userId, String resourceType) {
    	//用户拥有权限的所有菜单Id
		List<Tree> userMenuList = new ArrayList<Tree>();
		//获取用户所有角色
		List<String> userRoleIdLis = sysUserRoleMapper.findRoleIdListByUserId(userId);
		for(String roleId : userRoleIdLis){//遍历角色,获取菜单资源Id
			List<SysResourceVo> resourceVoList = sysResourceMapper.findResourceByRoleId(roleId,resourceType);
			for(SysResourceVo sysResourceVo : resourceVoList){
				if(sysResourceVo != null){
					Tree tree = new Tree();
					tree.setId(sysResourceVo.getResourceId());
					String currentPid = sysResourceVo.getResourcePid();
					if(StringUtils.isNotBlanks(currentPid)){
						tree.setPid(currentPid.toString());
					}
					tree.setText(sysResourceVo.getResourceName());
					tree.setIconCls(sysResourceVo.getIcon());
					Map<String,String> TreeAttributes = new HashMap<String,String>();
					TreeAttributes.put("url", sysResourceVo.getUrl());
					tree.setAttributes(TreeAttributes);
					if(!userMenuList.contains(tree)){
						userMenuList.add(tree);
					}
				}
			}
		}
		return userMenuList;
	}
	
	@Override
	public List<String> findPermCodeListByRoleId(String roleId ,String resourceStatus) {
		return sysResourceMapper.findPermCodeListByRoleId(roleId, resourceStatus);
	}

	@Override
	public List<SysResourceVo> findAllResourceVo() {
		List<SysResourceVo> sysResourceVoList = sysResourceMapper.findResourceVo("1");
		//设置state属性
		for (SysResourceVo sysResourceVo : sysResourceVoList) {
			if (Const.RESOURCE_MENU.equals(sysResourceVo.getResourceType())){//菜单类型
				if (sysResourceVo.getCountAllChild() > 0 ){
					if (sysResourceVo.getCountMenuChild()==null) {
						sysResourceVo.setState(true);
					} else {
						sysResourceVo.setState(false);
					}
				}
			}
		}
		return sysResourceVoList;
	}

	@Override
	public List<String> findResourceIdByRoleId(String roleId) {
		return sysResourceMapper.findResourceIdByRoleId(roleId);
	}

	@Override
	public List<String> findResourceIdListByRoleId(String roleId) {
		return sysResourceMapper.findResourceIdByRoleId(roleId);
	}
	
    /**
	 * 判断是否为叶子节点
	 * @param resourceId
	 * @return
	 */
	public boolean isLeft(String resourceId){
		return sysResourceMapper.countChildByPid(resourceId)==0?true:false;
	}

	/**
     * 查询所有资源列表
     *
     * @return
     */
	@Override
	public List<SysResource> findAllResourceVoList() {
		
		return sysResourceMapper.findAllResourceVoList();
	}

}
