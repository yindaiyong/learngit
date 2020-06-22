package com.sams.bizParameterManage.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.utils.MyMapUtils;
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
import com.sams.batchfile.service.ProductOpenDayService;
import com.sams.common.constant.Const;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.ProductOpenDay;
import com.sams.sys.model.SysUser;

@Controller
@RequestMapping("/productOpenDay")
public class ProductOpenDayController extends BaseController {

	@Autowired
	private CloseDateService closeDateService;
	
	@Autowired
	private ProductOpenDayService ProductOpenDayService;

	/**
	 * 产品工作日页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/productOpenDay/productOpenDayList";
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
	@RequestMapping(value = "/getProductOpenDayData")
	@ResponseBody
	public PageInfo getProductOpenDayData(Integer page, Integer rows, String sort,
			String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.get("productCode")!=null){
			condition.put("batchNo", MyMapUtils.getStringArrayBySplit(condition,"productCode","-")[1]);
			condition.put("productCode", MyMapUtils.getStringArrayBySplit(condition,"productCode","-")[0]);
		}else{
			condition.put("batchNo", null);
		}
		pageInfo.setPageResult(ProductOpenDayService.findDataGrid(pageInfo,
				condition));
		return pageInfo;
	}

	

	/**
	 * 产品工作日页面新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/productOpenDay/productOpenDayAdd";
	}

	/**
	 * 产品工作日页面修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Model model, String channelCode,String year,String month,String productCode,String batchNo) {
		Map<String,String> query = Maps.newHashMap();
	    model.addAttribute("year", year);
	    model.addAttribute("month", month);
	    model.addAttribute("channelCode", channelCode);
	    model.addAttribute("productCode", productCode+"-"+batchNo);
		return "sys/bizParameterManage/productOpenDay/productOpenDayEdit";
	}
	
	/**
	 * 产品工作日页面查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(Model model, String channelCode,String year,String month,String productCode,String batchNo) {
		Map<String,String> query = Maps.newHashMap();
	    model.addAttribute("year", year);
	    model.addAttribute("month", month);
	    model.addAttribute("channelCode", channelCode);
	    model.addAttribute("productCode", productCode+"-"+batchNo);
		return "sys/bizParameterManage/productOpenDay/productOpenDayView";
	}
	
	/**
	 * 工作日编辑页面 非工作日以及工作日的数据取值
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getEditData")
	@ResponseBody
	public JSONObject getEditData(String year, String month,String channelCode,String productCode,String flag) {
		Map<String, Object> mapLeft = new LinkedHashMap<>();
		Map<String, Object> mapRight = new LinkedHashMap<>();
		Map<String,String> qryMap = Maps.newHashMap();
		qryMap.put("channelCode", channelCode);
		qryMap.put("productCode", productCode.split("-")[0]);
		qryMap.put("year", year);
		qryMap.put("month", month);
		qryMap.put("batchNo", productCode.split("-")[1]);
		List<ProductOpenDay> qryProOpenDayList  = ProductOpenDayService.selectByExample(qryMap);
		int intYear = Integer.parseInt(year);
		int intMonth = Integer.parseInt(month);
		for(ProductOpenDay openDay:qryProOpenDayList){
			String date = openDay.getPoCloseDay();
			int dayNo = DateUtils.getDayofweek(intYear+month+date.substring(6,8));
			//当天日期是星期几
			String s = getDay(dayNo);
			mapRight.put(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8), date+" "+s);
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
			if(!mapRight.containsKey(date)){
				mapLeft.put(date, year+month+day+" "+s);
			}
		}
		JSONObject json = new JSONObject();
		JSONObject jsObj = null;
		if("right".equals(flag) ){
			jsObj = json.fromObject(mapRight);
		}else if("left".equals(flag) ){
			jsObj = json.fromObject(mapLeft);
		}
		return jsObj;
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
	@RequestMapping(value = "/getRightDays")
	@ResponseBody
	public static JSONObject getRightDays(String year, String month) {
		Map<String, Object> rigthMap = new LinkedHashMap<>();
		JSONObject json = new JSONObject();
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
				rigthMap.put(year + "-" + month + "-" + day, year+month+day + " " + s);
			}
			JSONObject jsRight = json.fromObject(rigthMap);
			return jsRight;
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
	 * 非工作日信息删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@Param("channelCode")String channelCode,@Param("year")String year,@Param("month")String month,@Param("productCode")String productCode,@Param("batchNo")String batchNo) {
		Map<String,String> qryMap = Maps.newHashMap();
		
		qryMap.put("year", year);
		qryMap.put("month", month);
		qryMap.put("channelCode", channelCode);
		qryMap.put("productCode", productCode);
		qryMap.put("batchNo", batchNo);
		
		List<ProductOpenDay> proOpenDayList = ProductOpenDayService.selectByExample(qryMap);
		
		for(ProductOpenDay openDay:proOpenDayList){
			SysUser user=getCurrentUser();
			openDay.setPoCuser(user.getLoginName());
			openDay.setPoCtime(getSysDate());
			openDay.setPoAction(Const.BUSINESS_STUTAS_00);
			openDay.setPoCheckState(Const.BUSINESS_STUTAS_00);
	    }
		
	    int num = ProductOpenDayService.updateAllProOpenDay(proOpenDayList);
	    String msg = "非工作日已删除";
		return resultSuccess(msg);
	}

	/**
	 * 非工作日信息复核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object checkSubmit(@Param("channelCode")String channelCode,@Param("year")String year,@Param("month")String month,@Param("productCode")String productCode,@Param("batchNo")String batchNo) {
        Map<String,String> qryMap = Maps.newHashMap();
		String msg = "";
		
		qryMap.put("year", year);
		qryMap.put("month", month);
		qryMap.put("channelCode", channelCode);
		qryMap.put("productCode", productCode);
		qryMap.put("batchNo", batchNo);
		
		List<ProductOpenDay> proOpenDayList = ProductOpenDayService.selectByExample(qryMap);
		
		for(ProductOpenDay openDay:proOpenDayList){
			SysUser user=getCurrentUser();
			openDay.setPoUuser(user.getLoginName());
			openDay.setPoUtime(getSysDate());
			openDay.setPoCheckState(Const.BUSINESS_STUTAS_01);
	    }
		
	    int num = ProductOpenDayService.updateAllProOpenDay(proOpenDayList);

		if(Const.BUSINESS_STUTAS_00.equals(proOpenDayList.get(0).getPoAction())){
			ProductOpenDayService.delete(qryMap);
		}
		msg = Const.ACTION_CHECK+"成功";
		return resultSuccess(msg);
	}

	/**
	 * 非工作日信息新增
	 * 
	 * @return
	 */
	@RequestMapping("/addSubmit")
	@ResponseBody
	public Object addSubmit(@Param("righttable") String righttable,
			@Param("channelCode") String channelCode,
			@Param("productCode") String productCode) {
		String righttableSub = righttable.substring(1, righttable.length() - 1);
		String[] righttables = righttableSub.split(",");
		int num=0;
		String msg = "";
		SysUser user=getCurrentUser();
		String date = righttables[0].replaceAll("&quot;", "").replaceAll("-","");
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		Map<String,String> qryMap = Maps.newHashMap();
		qryMap.put("channelCode", channelCode);
		qryMap.put("productCode", productCode.split("-")[0]);
		qryMap.put("year", year);
		qryMap.put("month", month);
		qryMap.put("batchNo", productCode.split("-")[1]);
		List<ProductOpenDay> qryProOpenDayList  = ProductOpenDayService.selectByExample(qryMap);
		if(qryProOpenDayList.size()>0){
			msg=year+"年"+month+"月"+"该批次产品已有非工作日";
			return resultSuccess(msg);
		}
		for (int i = 0; i < righttables.length; i++) {
			 date = righttables[i].replaceAll("&quot;", "").replaceAll("-","");
			 year = date.substring(0, 4);
			 month = date.substring(4, 6);
			 ProductOpenDay openDay = new ProductOpenDay();
			 openDay.setPoChannelCode(channelCode);
			 openDay.setPoProductCode(productCode.split("-")[0]);
			 openDay.setPoYear(year);
			 openDay.setPoMonth(month);
			 openDay.setPoCloseDay(date);
			 openDay.setPoBatchNo(productCode.split("-")[1]);
			 openDay.setPoCuser(user.getLoginName());
			 openDay.setPoCtime(getSysDate());
			 openDay.setPoAction(Const.BUSINESS_STUTAS_01);
			 openDay.setPoCheckState(Const.BUSINESS_STUTAS_00);
			 num =ProductOpenDayService.save(openDay);
		}
		if (num == 1) {
			msg = Const.ACTION_ADD+"成功";
		} else {
			msg = Const.ACTION_ADD+"失败";
		}
		return resultSuccess(msg);
	}
	
	
	/**
	 * 非工作日信息修改
	 * 
	 * @return
	 */
	@RequestMapping("/editSubmit")
	@ResponseBody
	public Object editSubmit(@Param("righttable") String righttable,
			@Param("channelCode") String channelCode,
			@Param("productCode") String productCode) {
		//先删除当前渠道下该批次产品的非工作日
		Map<String,Object> qryMap = Maps.newHashMap();
		String righttableSub = righttable.substring(1, righttable.length() - 1);
		String[] righttables = righttableSub.split(",");
		int num=0;
		String msg = "";
		SysUser user=getCurrentUser();
		String date = righttables[0].replaceAll("&quot;", "").replaceAll("-","");
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		qryMap.put("channelCode", channelCode);
		qryMap.put("productCode", productCode.split("-")[0]);
		qryMap.put("year", year);
		qryMap.put("month", month);
		qryMap.put("batchNo", productCode.split("-")[1]);
		ProductOpenDayService.delete(qryMap);
		
		Object retObj = addSubmit(righttable,channelCode,productCode);
		return retObj;
	}
	
}
