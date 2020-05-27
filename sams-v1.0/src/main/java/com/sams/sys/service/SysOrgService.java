package com.sams.sys.service;

import java.util.List;

import com.sams.common.web.Tree;
import com.sams.common.web.service.BaseService;
import com.sams.sys.model.SysOrg;
import com.sams.sys.model.result.SysOrgVo;

/**
 * 机构管理
 * @author hq 2016-08-29 14:28:40
 */
public interface SysOrgService extends BaseService<SysOrg>{
	
	/**
	 * 获取所有机构树
	 * @return
	 */
	public List<Tree> findOrgAllTree();

	/**
     * 查询所有机构
     *
     * @return
     */
	List<SysOrgVo> findAllOrgVo();
	
}
