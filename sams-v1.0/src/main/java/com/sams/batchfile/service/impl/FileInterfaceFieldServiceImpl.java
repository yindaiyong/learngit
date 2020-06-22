package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.FileInterfaceFieldService;
import com.sams.common.scanner.SpringUtils;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
/**
 * @ClassName:  FileInterfaceFieldServiceImpl   
 * @Description: 文件接口字段组件业务层
 * @author: yindy
 * @date:   2019年4月10日 下午2:54:33   
 *
 */
@Service
public class FileInterfaceFieldServiceImpl implements FileInterfaceFieldService{

	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Override
	public InterfaceColInfo selectFileInterfaceField(Map interfaceCode) {
		interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
		InterfaceColInfo listReq=interfaceColInfoMapper.selectByInterfaceCode(interfaceCode);
		return listReq;
	}
	
	@Override
	public List<InterfaceColInfo> selectFileInterfaceFieldOrder(Map interfaceCode) {
		interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
		List<InterfaceColInfo> listReq=interfaceColInfoMapper.selectByInterfaceCodeList(interfaceCode);
		return listReq;
	}
	
	@Override
	public Map<String,InterfaceColInfo> selectFileInterfaceFieldList(Map interfaceCode) {
		Map<String,InterfaceColInfo> filedMap=Maps.newHashMap();
		interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
		List<InterfaceColInfo> listReq=interfaceColInfoMapper.selectByInterfaceCodeList(interfaceCode);
		for(InterfaceColInfo interfaceColInfo:listReq){
			filedMap.put(interfaceColInfo.getIcColName().toUpperCase(), interfaceColInfo);
		}
		return filedMap;
	}
	
	@Override
	public Map<String,InterfaceColInfo> selectColInfoMap(String fileType,String businessCode,PchannelInfo channelInfo){
		Map<String,InterfaceColInfo> retMap = Maps.newHashMap();
		Map<String,Object> inputMap = Maps.newHashMap();
		String CSDCVer = channelInfo.getCiCsdcVer();
		inputMap.put("ICINTERFACECODE",Strings.padStart(fileType+businessCode+CSDCVer, 8, '0').replaceAll("R", "9"));
		retMap= FileDataUtil.exchangeListToMap(interfaceColInfoMapper.selectByInterfaceCodeList(inputMap));
		return retMap;
	}
}
