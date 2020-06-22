package com.sams.batchfile.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sams.sys.model.SysUser;

/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2020/6/17 14:43
 * @Description:
 */
public interface ProTypeOpenDayService {

    public List<Map<String,Object>> getProductTypeCombox();

	public Map<String, Object> getMonthAndDayList(String productType,String yearVal, String type);

	public String saveDate(String yearVal, String productType, String days,
			String currentUser);

	public HashMap<String, Object> showDateOneMon(String yearVal,
			String productType, String string, String monIndex);

	public String updateDate(String yearVal, String productType, String days,
			String currentUser, String monVal);

	public String checkMon(String yearVal, String productType, String mons,
			String loginName);

	public String delMon(String yearVal, String productType, String mons);

	public String syncInsert2ProductTypeOpenDay(String yearVal, String days,
			String loginName);
}
