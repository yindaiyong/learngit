package com.sams.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sams.sys.model.result.SysUserRes;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.common.annotation.DataSourceChange;
import com.sams.common.constant.Const;
import com.sams.common.exception.ServiceException;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.RandomizingID;
import com.sams.common.utils.StringUtils;
import com.sams.common.utils.security.Digests;
import com.sams.common.utils.security.Encodes;
import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseServiceImpl;
import com.sams.sys.mapper.SysUserMapper;
import com.sams.sys.mapper.SysUserRoleMapper;
import com.sams.sys.model.SysUser;
import com.sams.sys.model.SysUserRole;
import com.sams.sys.model.result.SysUserVo;
import com.sams.sys.service.SysUserService;

@Service
public class SysUserServiceNewImpl extends BaseServiceImpl<SysUser> implements SysUserService {

	private static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceNewImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @DataSourceChange(Const.DATASOURCE_MASTER)
    public SysUser findUserByLoginName(String username) {
        return sysUserMapper.findUserByLoginName(username);
    }

	@Override
	public List findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		List<SysUserRes> list = sysUserMapper.findUserPageCondition(pageInfo,condition);
		try {
			list.get(0).getUserId();
		} catch (Exception e) {
			list.remove(0);
		}
		return list;
	}

	@Override
	public int addUser(SysUserVo sysUserVo) {
		entryptPassword(sysUserVo);
		sysUserVo.setCreateDate(DateUtils.getSysTimestamp());
		SysUser sysUser = new SysUser();
		try{
			PropertyUtils.copyProperties(sysUser, sysUserVo);
		}catch (Exception e) {
			LOGGER.error("数据转换异常{}",e);
			throw new ServiceException("数据转换异常{}",e);
		}
		int countSize=sysUserMapper.selectAll().size();
		RandomizingID random = new RandomizingID("", "yyyyMMdd", 2, false);
		sysUser.setUserId(random.genNewId());
		int count = sysUserMapper.insertSelective(sysUser);
		if( count == 1){
			sysUser = sysUserMapper.findUserByLoginName(sysUser.getLoginName());
			//保存角色信息
			String roleIds = sysUserVo.getRoleIds();
			if (StringUtils.isNotBlanks(roleIds)) {
				SysUserRole sysUserRole = new SysUserRole();
				String userId = sysUser.getUserId();
				String[] roleIdArr = roleIds.split(",");
				for (String roleId : roleIdArr) {
					if (StringUtils.isNotBlanks(roleId)) {
						sysUserRole.setUserId(userId);
						sysUserRole.setRoleId(roleId);
						sysUserRoleMapper.insert(sysUserRole);
					}
				}
			}
		}
		return count;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(SysUserVo sysUserVo) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		sysUserVo.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(sysUserVo.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		sysUserVo.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(SysUser sysUser, String newPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		sysUser.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(newPassword.getBytes(), salt, HASH_INTERATIONS);
		sysUser.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	
	@Override
	public boolean checkPassword(SysUser user, String oldPwd) {
		byte[] salt = Encodes.decodeHex(user.getSalt());
		byte[] hashPassword = Digests.sha1(oldPwd.getBytes(), salt, HASH_INTERATIONS);
		if (user.getPassword().equals(Encodes.encodeHex(hashPassword))) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void updateUserPassword(SysUser sysUser, String pwd) {
		entryptPassword(sysUser, pwd);
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
	}

	@Override
	public SysUserVo findUserVoById(String userId) {
		return sysUserMapper.findUserVoById(userId);
	}

	@Override
	public int updateUserAllAndRole(SysUser sysUser, String roleIds) {
		int count = super.updateAll(sysUser);
		if (roleIds != null) {
			//删除所有角色
			sysUserRoleMapper.deleteByUserId(sysUser.getUserId());
			//保存新角色
			if (StringUtils.isNotBlanks(roleIds)) {
				SysUserRole sysUserRole = new SysUserRole();
				String userId = sysUser.getUserId();
				String[] roleIdArr = roleIds.split(",");
				for (String roleId : roleIdArr) {
					if (StringUtils.isNotBlanks(roleId)) {
						sysUserRole.setUserId(userId);
						sysUserRole.setRoleId(roleId);
						sysUserRoleMapper.insert(sysUserRole);
					}
				}
			}
		}
		return count;
	}

}
