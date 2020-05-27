package com.sams.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.exception.ServiceException;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.web.Tree;
import com.sams.common.web.service.BaseServiceImpl;
import com.sams.sys.mapper.SysDictMapper;
import com.sams.sys.model.SysDict;
import com.sams.sys.model.result.SysDictVo;
import com.sams.sys.service.SysDictService;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements
		SysDictService {

	private static Logger LOGGER = LoggerFactory
			.getLogger(SysDictServiceImpl.class);

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public List<SysDictVo> findDictAllVo(String dictType) {
		return sysDictMapper.findDicAlltVo(dictType);
	}
	
	@Override
	public List<Map<String,Object>> findFundStatus(String dictType) {
		return sysDictMapper.findFundStatus(dictType);
	}

	@Override
	public String findDictVo(String dictType) {
		return sysDictMapper.findDictVo(dictType);
	}
	

	@Override
	public String findDictBydictCodeVo(String dictType, String dictCode) {
		return sysDictMapper.findDictBydictCodeVo(dictType, dictCode);
	}

	@Override
	public List<Tree> findDictAllTree(String dictType) {
		List<Tree> TreeList = new ArrayList<Tree>();
		List<SysDictVo> dictList = sysDictMapper.findDicAlltVo(dictType);
		for (SysDictVo sysDictVo : dictList) {
			if (sysDictVo != null) {
				Tree tree = new Tree();
				tree.setId(sysDictVo.getDictId());
				tree.setText(sysDictVo.getDictName());
				tree.setPid(sysDictVo.getDictPid());
				//查询当前节点对应的是否有子节点数据
				String id =sysDictVo.getDictId();
				for(SysDictVo ss : dictList){
					if(StringUtils.isNotBlank(ss.getDictPid())
							&& ss.getDictPid().equals(id)){
						tree.setState(false);
						break;
					}
				}
				Map<String, String> treeAttributes = new HashMap<String, String>();
				treeAttributes.put("dictCode", sysDictVo.getDictCode());
				tree.setAttributes(treeAttributes);
				TreeList.add(tree);
			}
		}
		return TreeList;
	}

	@Override
	public int save(SysDict sysDict) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("dictType", sysDict.getDictType());
		condition.put("dictCode", sysDict.getDictCode());
		List<SysDict> sysDicts = sysDictMapper.findByCondition(condition);
		// 校验字典数据是否冲突
		if (sysDicts != null && sysDicts.size() > 0) {
			throw new ServiceException("操作失败，请检查：类型|参数，是否已存在");
		}
		return sysDictMapper.insert(sysDict);
	}

	@Override
	public int updateAll(SysDict sysDict) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("dictType", sysDict.getDictType());
		condition.put("dictCode", sysDict.getDictCode());
		List<SysDict> sysDicts = sysDictMapper.findByCondition(condition);
		// 校验字典数据是否冲突
		if (sysDicts != null && sysDicts.size() > 0) {
			for (SysDict dict : sysDicts) {
				if (dict != null
						&& !dict.getDictId().equals(sysDict.getDictId())) {
					throw new ServiceException("操作失败，请检查：类型|参数，是否已存在");
				}
			}

		}
		return super.updateAll(sysDict);
	}

	/**
	 * 通过字典名称查找字典数据信息
	 * 
	 * @param fundTypeName
	 *           
	 * @return Map<String, Object> 
	 */
	public static Map<String, Object> findDictVoByDictType(String fundTypeName) {
		Map<String, Object> retMap = Maps.newHashMap();
		SysDictService sysDictService = (SysDictService) SpringUtils
				.getBean(SysDictService.class);
		// TODO 方法名称修改
		String dictCode = sysDictService.findDictVo(fundTypeName);
		if (dictCode == null || ("").equals(dictCode)) {
			retMap = ExceptionConStants
					.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("retMsg", "通过fundTypeName" + fundTypeName
					+ "没有在字典表里找到dictCode");
		} else {
			retMap = ExceptionConStants
					.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("dictCode", dictCode);

		}
		return retMap;
	}
	
	/**
	 * 通过字典名称查找字典数据信息
	 * 
	 * @param fundTypeName fundType
	 *           
	 * @return Map<String, Object> 
	 */
	public static Map<String,Object> findDictVoByTypeAndName(String fundTypeName,String fundType){
		Map<String,Object> retMap = Maps.newHashMap();
		SysDictService sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
		String dictCode = sysDictService.findDictBydictCodeVo(fundTypeName, fundType);
		if(dictCode==null||("").equals(dictCode)){
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("dictCode", dictCode);
			retMap.put("retMsg", "通过fundTypeName"+fundTypeName+"和fundType"+fundType+"没有在字典表里找到dictCode");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("dictCode", dictCode);
		}
		return retMap;
	}

	@Override
	public Map<String, Object> findSaleToTaMapper(String dictType) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<SysDict> list = sysDictMapper.findChildByDictType(dictType);
		for (SysDict sysDict : list) {
			retMap.put(sysDict.getDictCode(), sysDict.getDictType());
		}
		return retMap;
	}

	@Override
	public int findDictCount(String dictType, String dictCode) {
		return sysDictMapper.findDictCount(dictType,dictCode);
	}
	
	
	@Override
	public List<SysDictVo> findDealTypeByChannelCode(String channelCode){
		return sysDictMapper.findDealTypeByChannelCode(channelCode);
	}

	@Override
	public List<Map<String, Object>> queryTaTcapType() {
		return sysDictMapper.queryTaTcapType();
	}

	@Override
	public List<Map<String, Object>> getRoleId() {
		
		return sysDictMapper.getRoleId();
	}
	
	 /**
     * 查询产品类型的数据字典
     * @param id
     * @return
     */
	@Override
    public Object  productTypes(HttpServletRequest request,HttpServletResponse response){
    	List<SysDictVo> fundStatusList= findDictAllVo("fundType");
    	for(int i=0;i<fundStatusList.size();i++){
    		if(fundStatusList.get(i).getDictCode()==null||"".equals(fundStatusList.get(i).getDictCode())){
    			fundStatusList.remove(i);
    		}
    	}
		return com.alibaba.fastjson.JSONArray.parse(JSONObject.toJSONString(fundStatusList));
    }

	@Override
	public List<Map<String,Object>> getSysDictInfo(String dictType) {
		 List<Map<String,Object>> sysDictList = sysDictMapper.getSysDictInfo(dictType);
		return sysDictList;
	}

	@Override
	public Map<String,Object> getChannelCurrent(String dictType) {
		 Map<String,Object> currentMap = new HashMap<String,Object>();
		 List<Map<String,Object>> sysDictList = getSysDictInfo(dictType);
		 for(int i=0;i<sysDictList.size();i++){
			 currentMap.put((String) sysDictList.get(i).get("DICTCODE"), sysDictList.get(i).get("DICTTYPE"));
		 }
		return currentMap;
	}

	@Override
	public List<Map<String,Object>> selectByDictName(String dictName){
		return sysDictMapper.selectByDictName(dictName);
	}
}
