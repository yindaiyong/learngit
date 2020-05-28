package com.common;

import java.util.HashMap;
import java.util.Map;

import com.common.util.JsonUtil;

public class BaseController {

	public String resultSuccess(Object msg,Object data,Object status) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("msg", msg);
		retMap.put("Data", data);
		retMap.put("state", status);
		return JsonUtil.getJson(retMap);
	}
}
