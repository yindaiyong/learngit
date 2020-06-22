package com.sams.common.utils;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sams.common.constant.Const;
import com.sams.common.scanner.SpringUtils;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.mapper.ProductTemplateMapper;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static ProductTemplateMapper productTemplateMapper;
	
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd hh:mm:ss"};
	
	public static final String FORMAT_STR_DATE8 = "yyyyMMdd";
	public static final String FORMAT_STR_DATE10 = "yyyy-MM-dd";
	public static final String FORMAT_STR_DATETIME19 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_STR_DATETIME19_2 = "yyyy/MM/dd HH:mm:ss";
	public static final String FORMAT_STR_TIME4 = "HHmm";
	public static final String FORMAT_STR_DAY6 = "yyyyMM";
	public static final String FORMAT_STR_TIME14 = "yyyyMMddHHmmss";
	public static final String FORMAT_STR_TIME17 = "yyyyMMddHHmmssSSS";
	
	public static final SimpleDateFormat DATEFORMATE_DATE8 = new SimpleDateFormat(FORMAT_STR_DATE8);
	public static final SimpleDateFormat DATEFORMATE_DATE10 = new SimpleDateFormat(FORMAT_STR_DATE10);
	public static final SimpleDateFormat DATEFORMATE_DATETIME19 = new SimpleDateFormat(FORMAT_STR_DATETIME19);
	public static final SimpleDateFormat DATEFORMATE_TIME4 = new SimpleDateFormat(FORMAT_STR_TIME4);
	public static final SimpleDateFormat DATEFORMATE_TIME14 = new SimpleDateFormat(FORMAT_STR_TIME14);
	public static final SimpleDateFormat DATEFORMATE_DATE6 = new SimpleDateFormat(FORMAT_STR_DAY6);

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(getOracleSysDate(), pattern);
	}
	
	
	public static String getCurrentNextDay(String tradeDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			Date date = sf.parse(tradeDate);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		return sf.format(c.getTime());
	}
	
	
	public static String getLastDay(String tradeDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			Date date = sf.parse(tradeDate);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, -1);
		return sf.format(c.getTime());
	}
	
	public static String getNextDay(String tradeDate,String nextDay){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			Date date = sf.parse(tradeDate);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH,Integer.valueOf(nextDay));
		return sf.format(c.getTime());
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(getOracleSysDate(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(getOracleSysDate(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(getOracleSysDate(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(getOracleSysDate(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(getOracleSysDate(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(getOracleSysDate(), "E");
	}
	
	/**
	*@param date 是为则默认今天日期、可自行设置“2013-06-03”格式的日期
	*@return  返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
	*/
	public static int getDayofweek(String date){
	   Calendar cal = Calendar.getInstance();
	   if (date.equals("")) {
	    cal.setTime(DateUtils.getOracleSysDate());
	   }else {
	    cal.setTime(getDateBYYYYMMDD(date.trim()));
	   }
	   //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
	   //System.out.print(sdf.format(cal.getTime()));   
	   return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static Date getDateBYYYYMMDD(String dd)
	 {
	  SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
	  Date date;
	  try {
	   date = sd.parse(dd);
	  } catch (ParseException e) {
	   date = null;
	   e.printStackTrace();
	  }
	  return date;
	 }

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = getOracleSysDate().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
    
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 判断字符串是否是日期
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try{
			format.parse(timeString);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * 格式化时间
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}
	
	/**
	 * 获取系统时间Timestamp
	 * @return
	 */
	public static Timestamp getSysTimestamp(){
		return new Timestamp(getOracleSysDate().getTime());
	}
	
	/**
	 * 获取系统时间Date
	 * @return
	 */
	public static Date getSysDate(){
		
		return getOracleSysDate();
	}
	
	
	/**
	 * 生成时间随机数 
	 * @return
	 */
	public static String getDateRandom(){
		String s=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(getOracleSysDate());
		return s;
	}
	
	
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
	    List<Date> result = new ArrayList<Date>();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd)) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	
	/**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		 if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			 return true;
		 }
		 Calendar date = Calendar.getInstance();
		 date.setTime(nowTime);
		 Calendar begin = Calendar.getInstance();
		 begin.setTime(startTime);
		 Calendar end = Calendar.getInstance();
		 end.setTime(endTime);
		 if (date.after(begin) && date.before(end)) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	
	/**
	* 判断时间是否在[startTime, endTime]区间，注意时间格式要一致
	* @param nowTime
	* @param startTime
	* @param endTime
	* @return
	*/
	public static boolean isEffectiveDate(String nowTime, String startTime, String endTime,String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date nowDate = null;
		Date startDate = null;
		Date endDate = null;
		try {
			nowDate = df.parse(nowTime);
			startDate = df.parse(startTime);
			endDate = df.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (nowDate.getTime() == startDate.getTime()|| nowDate.getTime() == endDate.getTime()) {
			return true;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(nowDate);
		Calendar begin = Calendar.getInstance();
		begin.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* 判断时间是否在(startTime, endTime)区间，注意时间格式要一致
	* @param nowTime
	* @param startTime
	* @param endTime
	* @return
	*/
	public static boolean isEffectiveDate1(String nowTime, String startTime, String endTime,String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date nowDate = null;
		Date startDate = null;
		Date endDate = null;
		try {
			nowDate = df.parse(nowTime);
			startDate = df.parse(startTime);
			endDate = df.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar date = Calendar.getInstance();
		date.setTime(nowDate);
		Calendar begin = Calendar.getInstance();
		begin.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean compareDate(String nowTime, String startTime,String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date nowDate = null;
		Date startDate = null;
		try {
			nowDate = df.parse(nowTime);
			startDate = df.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (nowDate == null || startDate == null){
			return false;
		}else{
			return nowDate.getTime() > startDate.getTime();
		}

	}
	
	/**
	 * 取得季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}
	
	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	
	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
 
		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}
	
	
	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
 
		int season = 0;
 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}
	
	
	public static boolean isAfterDate(String date){
		try{
			String date1 = DATEFORMATE_DATE8.format(getOracleSysDate());
			Date day1 = DATEFORMATE_DATE8.parse(date1);
			
		    Date from = DATEFORMATE_DATE8.parse(date);
		    Calendar after = Calendar.getInstance();
		    after.setTime(from);
		    Date day2 = after.getTime(); 
		    if(day1.getTime() >= day2.getTime()){
		    	return true;
		    }else{
		    	return false;
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	* @author yangsy
	* 将短时间格式字符串转换为时间 yyyy-MM-dd 
	* 
	* @param strDate
	* @return
	*/
	public static Date strToDate(String strDate,String aFmt) {
		SimpleDateFormat formatter = new SimpleDateFormat(aFmt);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	} 
	
	public static Date getOracleSysDate(){
	 productTemplateMapper = (ProductTemplateMapper)SpringUtils.getBean(ProductTemplateMapper.class);
   	 Date date = productTemplateMapper.getSysDate();
   	 return date;
   }
	
	/**
	* @author wangchao 
	* 获取当前系统时间
	* @param 
	* @return
	*/
	public static Date getNowSysDate(){
		return new Date();
	}
}
