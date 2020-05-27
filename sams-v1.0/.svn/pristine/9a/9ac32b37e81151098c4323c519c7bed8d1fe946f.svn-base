package com.sams.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.StringUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Tree;
import com.sams.common.web.service.BaseServiceImpl;
import com.sams.sys.mapper.SysRoleMapper;
import com.sams.sys.mapper.SysRoleResourceMapper;
import com.sams.sys.mapper.SysUserRoleMapper;
import com.sams.sys.model.SysRole;
import com.sams.sys.model.SysRoleResource;
import com.sams.sys.model.result.SysRoleVo;
import com.sams.sys.service.SysRoleService;

@Service
public class SysRoleServiceNewImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceNewImpl.class);

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    
    @Override
	public List<Tree> findRoleAllTree() {
		List<Tree> TreeList = new ArrayList<Tree>();
		List<SysRole> roleList = sysRoleMapper.findRoleAll();
		for (SysRole sysRole : roleList) {
			if (sysRole != null) {
				Tree tree = new Tree();
				tree.setId(sysRole.getRoleId());
				tree.setText(sysRole.getRoleName());
				TreeList.add(tree);
			}
		}
		return TreeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> findRoleIdAndNameByUserId(String userId) {
		return sysRoleMapper.findRoleIdAndNameByUserId(userId);
	}

	@Override
	public List<SysRoleVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return sysRoleMapper.findRolePageCondition(pageInfo,condition);
	}

	@Override
	public int updateRoleResourceByRoleId(String roleId, String resourceIds) {
		int count = 0;
		sysRoleResourceMapper.deleteByRoleId(roleId);
		String[] resourceIdArr = resourceIds.split(",");
		for(String resourceId : resourceIdArr){
			if(StringUtils.isNotBlanks(resourceId)){
				count += sysRoleResourceMapper.insert(new SysRoleResource(roleId, resourceId));
			}
		}
		return count ;
	}
}
