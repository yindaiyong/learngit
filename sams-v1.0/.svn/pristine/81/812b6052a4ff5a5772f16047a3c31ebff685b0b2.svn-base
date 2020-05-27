package com.sams.common.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.sams.common.exception.ExceptionConStants;

public class ListUtils {
	
	public static Map<String, Object> checkListIsEmpty(List<Map<String, Object>> list,String retCode){
		Map<String, Object> retMap=Maps.newHashMap();
		if(list==null || list.size()==0){
			retMap.putAll(ExceptionConStants.getRetObject(retCode));
			return retMap;
		}else{
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		}
		return retMap;
	}

}
