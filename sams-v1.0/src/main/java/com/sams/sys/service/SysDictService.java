package com.sams.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.Tree;
import com.sams.common.web.service.BaseService;
import com.sams.sys.model.SysDict;
import com.sams.sys.model.result.SysDictVo;

/**
 * 字典管理
 *
 * @author hq 2016-8-12 15:28:51
 */
public interface SysDictService extends BaseService<SysDict> {

    //查找字典
    List<SysDictVo> findDictAllVo(String dictType);
    
    
    /**
	 * 获取所有字典树
	 * @return
	 */
	public List<Tree> findDictAllTree(String dictType);
	
	
	
	public String findDictVo(String dictType);
	
	
	public String findDictBydictCodeVo(String dictType,String dictCode);
	
	public int findDictCount(String dictType,String dictCode);
	
	public Map<String, Object> findSaleToTaMapper(String dictType);
	
	public List<Map<String,Object>> findFundStatus(String dictType);
	
	public List<Map<String,Object>> queryTaTcapType();
	
	public List<Map<String,Object>> getRoleId();

	List<SysDictVo> findDealTypeByChannelCode(String channelCode);
	
	public Object  productTypes(HttpServletRequest request,HttpServletResponse response);
	
	List<Map<String,Object>> getSysDictInfo(String dictType);
	
	Map<String,Object>  getChannelCurrent(String dictType);

	public List<Map<String,Object>> selectByDictName(String dictName);
	
}
