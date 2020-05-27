package com.sams.bizParameterManage.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.CloseDateService;
import com.sams.common.constant.Const;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.CloseDate;
import com.sams.sys.model.SysUser;

@Controller
@RequestMapping("/closeDate")
public class CloseDateController extends BaseController {

	@Autowired
	private CloseDateService closeDateService;

	/**
	 * 非交易日页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/closeDate/closeDateList";
	}

	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param title
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCloseDateData")
	@ResponseBody
	public PageInfo getChannelInfoData(Integer page, Integer rows, String sort,
			String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(closeDateService.findDataGrid(pageInfo,
				condition));
		return pageInfo;
	}

	

	/**
	 * 非交易日页面新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/closeDate/closeDateAdd";
	}

	/**
	 * 非交易日页面修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Model model, String cdMarketCode,String cdYear,String cdMonth) {
		Map<String,String> query = Maps.newHashMap();
	    model.addAttribute("year", cdYear);
	    model.addAttribute("month", cdMonth);
	    model.addAttribute("cdMarketCode", cdMarketCode);
		return "sys/bizParameterManage/closeDate/closeDateEdit";
	}
	
	/**
	 * 非交易日页面查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(Model model, String cdMarketCode,String cdYear,String cdMonth) {
		Map<String,String> query = Maps.newHashMap();
	    model.addAttribute("year", cdYear);
	    model.addAttribute("month", cdMonth);
	    model.addAttribute("cdMarketCode", cdMarketCode);
		return "sys/bizParameterManage/closeDate/closeDateView";
	}
	
	/**
	 * 获取某年某月每一天日期
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getEdit1")
	@ResponseBody
	public JSONObject getEdit1(String year, String month,String cdMarketCode) {
		Map<String, Object> map1 = new LinkedHashMap<>();
		Map<String, Object> map2 = new LinkedHashMap<>();
		Map<String,String> query = Maps.newHashMap();
		query.put("cdMarketCode", cdMarketCode);
		query.put("cdYear", year);
		query.put("cdMonth", month);
		List<CloseDate> list = closeDateService.findClodeDateByCodeDate(query);
		int intYear = Integer.parseInt(year);
		int intMonth = Integer.parseInt(month);
		for(CloseDate closeDate:list){
			String date = closeDate.getCdCloseDate();
			int dayNo = DateUtils.getDayofweek(intYear+month+date.substring(6,8));
			//当天日期是星期几
			String s = getDay(dayNo);
			map2.put(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8), date+" "+s);
		}
		
		Integer num = getDaysByYearMonth(intYear, intMonth);
		for(int i=1;i<=num;i++){
			String day = "";
			if (i < 10) {
				day = "0" + i;
			} else {
				day = i + "";
			}
			String date = year+"-"+month+"-"+day;
			int dayNo = DateUtils.getDayofweek(intYear+month+day);
			String s = getDay(dayNo);
			if(!map2.containsKey(date)){
				map1.put(date, year+month+day+" "+s);
			}
		}
		JSONObject json = new JSONObject();
		JSONObject js = json.fromObject(map2);
		return js;
	}

	/**
	 * 获取某年某月每一天日期
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getEdit2")
	@ResponseBody
	public JSONObject getEdit2(String year, String month,String cdMarketCode) {
		Map<String, Object> map1 = new LinkedHashMap<>();
		Map<String, Object> map2 = new LinkedHashMap<>();
		Map<String,String> query = Maps.newHashMap();
		query.put("cdMarketCode", cdMarketCode);
		query.put("cdYear", year);
		query.put("cdMonth", month);
		int intYear = Integer.parseInt(year);
		int intMonth = Integer.parseInt(month);
		List<CloseDate> list = closeDateService.findClodeDateByCodeDate(query);
		for(CloseDate closeDate:list){
			String date = closeDate.getCdCloseDate();
			int dayNo = DateUtils.getDayofweek(intYear+month+date.substring(6,8));
			//当天日期是星期几
			String s = getDay(dayNo);
			map2.put(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8), date+" "+s);
		}
		Integer num = getDaysByYearMonth(intYear, intMonth);
		for(int i=1;i<=num;i++){
			String day = "";
			if (i < 10) {
				day = "0" + i;
			} else {
				day = i + "";
			}
			String date = year+"-"+month+"-"+day;
			int dayNo = DateUtils.getDayofweek(intYear+month+day);
			String s = getDay(dayNo);
			if(!map2.containsKey(date)){
				map1.put(date, year+month+day+" "+s);
			}
		}
		JSONObject json = new JSONObject();
		JSONObject js = json.fromObject(map1);
		return js;
	}
	
	/**
	 * 获取某年某月每一天日期
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getDays1")
	@ResponseBody
	public static JSONObject getDays1(String year, String month) {
		Map<String, Object> map1 = new LinkedHashMap<>();
		Map<String, Object> map2 = new LinkedHashMap<>();
		try {
			int intYear = Integer.parseInt(year);
			int intMonth = Integer.parseInt(month);
			Integer num = getDaysByYearMonth(intYear, intMonth);
			for (int i = 1; i <= num; i++) {
				String day = "";
				if (i < 10) {
					day = "0" + i;
				} else {
					day = i + "";
				}
				int dayNo = DateUtils.getDayofweek(intYear+month+day);
				String s = getDay(dayNo);
				if (s.contains("六") || s.contains("日")) {
					map2.put(year + "-" + month + "-" + day, year+month+day + " " + s);
				} else {
					map1.put(year + "-" + month + "-" + day, year+month+day + " " + s);
				}
			}
			JSONObject json = new JSONObject();
			JSONObject js = json.fromObject(map1);
			json.fromObject(map2);
			return js;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某年某月每一天日期
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getDays2", method = RequestMethod.POST)
	@ResponseBody
	public static JSONObject getDays2(String year, String month) {
		Map<String, Object> map1 = new LinkedHashMap<>();
		Map<String, Object> map2 = new LinkedHashMap<>();
		JSONObject j = new JSONObject();
		try {
			int intYear = Integer.parseInt(year);
			int intMonth = Integer.parseInt(month);
			Integer num = getDaysByYearMonth(intYear, intMonth);
			for (int i = 1; i <= num; i++) {
				String day = "";
				if (i < 10) {
					day = "0" + i;
				} else {
					day = i + "";
				}
				int dayNo = DateUtils.getDayofweek(intYear+month+ day);
				String s = getDay(dayNo);
				if (s.contains("六") || s.contains("日")) {
					map2.put(year + "-" + month + "-" + day, year+month+day + " " + s);
				} else {
					map1.put(year + "-" + month + "-" + day, year+month+day + " " + s);
				}
			}
			JSONObject json = new JSONObject();
			json.fromObject(map1);
			json.fromObject(map2);
			JSONObject js = json.fromObject(map2);
			return js;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static String getDay(int num) {
		String s = "";
		if (1 == num) {
			s = "星期日";
		} else if (2 == num) {
			s = "星期一";
		} else if (3 == num) {
			s = "星期二";
		} else if (4 == num) {
			s = "星期三";
		} else if (5 == num) {
			s = "星期四";
		} else if (6 == num) {
			s = "星期五";
		} else if (7 == num) {
			s = "星期六";
		}
		return s;
	}

	/**
	 * 非交易日信息删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@Param("cdYear")String cdYear,@Param("cdMonth")String cdMonth,@Param("cdMarketCode")String cdMarketCode) {
		Map<String,String> map = Maps.newHashMap();
		map.put("cdMarketCode", cdMarketCode);
		map.put("cdYear", cdYear);
		map.put("cdMonth", cdMonth);
		List<CloseDate> list = closeDateService.findClodeDateByCodeDate(map);
		for(CloseDate closeDate:list){
			SysUser user=getCurrentUser();
			closeDate.setCdCuser(user.getLoginName());
			closeDate.setCdCtime(getSysDate());
			closeDate.setCdAction(Const.BUSINESS_STUTAS_00);
			closeDate.setCdCheckState(Const.BUSINESS_STUTAS_00);
	    }
	    int num = closeDateService.updateByPrimaryKey(list);
	    String msg = "非交易日已删除";
		return resultSuccess(msg);
	}

	/**
	 * 非交易日信息复核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object checkSubmit(@Param("cdYear")String cdYear,@Param("cdMonth")String cdMonth,@Param("cdMarketCode")String cdMarketCode) {
		Map<String,String> map = Maps.newHashMap();
		map.put("cdMarketCode", cdMarketCode);
		map.put("cdYear", cdYear);
		map.put("cdMonth", cdMonth);
		String msg = "";
		int num = 0;
		List<CloseDate> list = closeDateService.findClodeDateByCodeDate(map);
		for(CloseDate closeDate:list){
			SysUser user=getCurrentUser();
			closeDate.setCdUuser(user.getLoginName());
			closeDate.setCdUtime(getSysDate());
			closeDate.setCdCheckState(Const.BUSINESS_STUTAS_01);
	    }
		num = closeDateService.updateByPrimaryKey(list);
		
		if(Const.BUSINESS_STUTAS_00.equals(list.get(0).getCdAction())){
			 num = closeDateService.deleteCloseDate(cdYear, cdMonth, cdMarketCode);
		}
			msg = Const.ACTION_CHECK+"成功";
		return resultSuccess(msg);
	}

	/**
	 * 非交易日信息新增
	 * 
	 * @return
	 */
	@RequestMapping("/addSubmit")
	@ResponseBody
	public Object addSubmit(@Param("righttable") String righttable,
			@Param("cdMarketCode") String cdMarketCode) {
		String righttableSub = righttable.substring(1, righttable.length() - 1);
		String[] righttables = righttableSub.split(",");
		int num=0;
		String msg = "";
		String date = righttables[0].replaceAll("&quot;", "").replaceAll("-","");
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		Map<String,String> map = Maps.newHashMap();
		map.put("cdMarketCode", cdMarketCode);
		map.put("cdYear", year);
		map.put("cdMonth", month);
		List<CloseDate> list = closeDateService.findClodeDateByCodeDate(map);
		if(list.size()>0){
			msg=year+"年"+month+"月"+"该市场已有非交易日数据";
			return resultSuccess(msg);
		}
		for (int i = 0; i < righttables.length; i++) {
			 date = righttables[i].replaceAll("&quot;", "").replaceAll("-","");
			 year = date.substring(0, 4);
			 month = date.substring(4, 6);
			CloseDate closeDate = new CloseDate();
			closeDate.setCdYear(year);
			closeDate.setCdMonth(month);
			closeDate.setCdCloseDate(date);
			closeDate.setCdMarketCode(cdMarketCode);
			SysUser user=getCurrentUser();
			closeDate.setCdCuser(user.getLoginName());
			closeDate.setCdCtime(getSysDate());
			closeDate.setCdAction(Const.BUSINESS_STUTAS_01);
			closeDate.setCdCheckState(Const.BUSINESS_STUTAS_00);
			num =closeDateService.save(closeDate);
		}
		if (num == 1) {
			msg = Const.ACTION_ADD+"成功";
		} else {
			msg = Const.ACTION_ADD+"失败";
		}
		return resultSuccess(msg);
	}
	
	
	/**
	 * 非交易日信息修改
	 * 
	 * @return
	 */
	@RequestMapping("/editSubmit")
	@ResponseBody
	public Object editSubmit(@Param("righttable") String righttable,
			@Param("cdMarketCode") String cdMarketCode,@Param("yearval") String yearval,@Param("monthval") String monthval) {
		String righttableSub = righttable.substring(1, righttable.length() - 1);
		String[] righttables = righttableSub.split(",");
		int num=0;
		String msg = "";
		closeDateService.deleteCloseDate(yearval, monthval, cdMarketCode);
		for (int i = 0; i < righttables.length; i++) {
			String date = righttables[i].replaceAll("&quot;", "").replaceAll("-","");
			CloseDate closeDate = new CloseDate();
			closeDate.setCdYear(yearval);
			closeDate.setCdMonth(monthval);
			closeDate.setCdCloseDate(date);
			closeDate.setCdMarketCode(cdMarketCode);
			SysUser user=getCurrentUser();
			closeDate.setCdCuser(user.getLoginName());
			closeDate.setCdCtime(getSysDate());
			closeDate.setCdAction(Const.BUSINESS_STUTAS_02);
			closeDate.setCdCheckState(Const.BUSINESS_STUTAS_00);
			closeDate.setCdUuser(null);
			closeDate.setCdUtime(null);
			num =closeDateService.save(closeDate);
		}
		if (num == 1) {
			msg = Const.ACTION_EDIT+"成功";
		} else {
			msg = Const.ACTION_EDIT+"失败";
		}
		return resultSuccess(msg);
	}
	
}
