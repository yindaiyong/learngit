package com.producer.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.dto.DrugDto;
import com.common.util.RedisUtil;
import com.common.util.JsonUtil;
import com.common.util.WebUrl;
import com.producer.service.DrugService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = WebUrl.PRODUCERURL)
public class DrugController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DrugController.class);
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private RedisTemplate<Serializable, Object> redisTemplate;


	@GetMapping(value = WebUrl.GETDRUG)
    public String getDrugList(DrugDto drug){
//		List<Object> drugList = new ArrayList<Object>();
//		RedisUtil util = new RedisUtil(redisTemplate);
		//String
//		studyString(util);
		//set
//		studySet(util);
		/*
		 *  drugList =
		 * drugService.getDrugList(drug);
		 * System.out.println("输出数据库拿到的药品信息集合:"+drugList); util.lSet("drug",
		 * drugList,20); System.out.println("把拿到的集合放入redis缓存："); List<Object> drugRedis
		 * = util.lGet("drug", 10, 20); System.out.println("通过缓存拿到的集合为："+drugRedis);
		 */
		return JsonUtil.getJson(null);
    }
	/**
	 * set
	 * @param util
	 */
	private void studySet(RedisUtil util) {
		//hset 
		util.hset("set", "a", "A");
		//hmset
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("b", "B");
		map.put("c", "C");
		util.hmset("set", map);
		//hdel
		util.hdel("set", "a");
		
	}

	/**
	 * String
	 * @param util
	 */
	private void studyString(RedisUtil util) {
		//set key 
		util.set("string", "Hello world!");
		//get key 
		String value = (String) util.get("string");
		System.out.println("string : "+ value);
		//getrange
		String rangeValue = (String) util.getRange("string", 2, 3);
		System.out.println("string : "+ rangeValue);
		//getset
		String getset = (String) util.getSet("string", "你好！");
		System.out.println("string : "+getset);
		//append 
		util.append("string", "Hello World!");
		
	}
}
