package com.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static String defaultFormat = "yyyy-MM-dd HH:mm:ss:SSSS";
    
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    
    public static final String FORMAT_DATETIME_SHORT = "yyyy-MM-dd";
    
    public static final String FORMAT_YIBAO_A_16 = "yyyyMMdd/HHmmss";
    public static final String FORMAT_YIBAO_B_8 = "yyyyMMdd";

    public static String formatDate(Object obj, String format) {
        if (!StringUtil.isNotEmpty(obj))
            return "";
        if (!StringUtil.isNotEmpty(format))
            format = defaultFormat;

        return formatDate(obj, format, format);
    }

    public static String formatDate(Object obj, String formFormat, String toFormat) {
        if (!StringUtil.isNotEmpty(obj))
            return "";
        if (!StringUtil.isNotEmpty(formFormat))
            formFormat = defaultFormat;
        if (!StringUtil.isNotEmpty(toFormat))
            toFormat = defaultFormat;

        String s = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formFormat);
        try {
            Date date = sdf.parse(obj.toString());
            sdf = new SimpleDateFormat(toFormat);
            s = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return s;
    }

    public static String formatDate(Date date, String toFormat) {
      if (!StringUtil.isNotEmpty(date))return null;
        SimpleDateFormat sdf = new SimpleDateFormat(toFormat);
        return sdf.format(date);
    }

    public static void main(String[] args) {
//        String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
//        System.out.println(date + "\n" + new Date());
    	System.out.println(getTodayStart());
    }

    public static Date string2Date(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //return null;
    }
    
    public static Date string2Date(String str, String format) {
    	if (!StringUtil.isNotEmpty(str) || !StringUtil.isNotEmpty(format)) return null;
    	if (!StringUtil.isNotEmpty(format)) format = defaultFormat;
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //return null;
    }
    
    public static Timestamp getTimestamp(String strTimestamp){
        if(null == strTimestamp || "".equals(strTimestamp)){
            return null;
        }
        try {
            Date date = new SimpleDateFormat(FORMAT_DATETIME).parse(strTimestamp);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static Timestamp getTimestamp(String strTimestamp, String format) {
        if (null == strTimestamp || "".equals(strTimestamp)) {
            return null;
        }
        try {
            Date date = new SimpleDateFormat(format).parse(strTimestamp);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static String getTodayStart(){
    	return formatDate(new Date(), "yyyy-MM-dd") + " 00:00:00";
    }
    
	/**
	 * string 转为 date
	 * 
	 * @param args
	 */
	public static Timestamp stringFormatDate(String time, String sec) {
		if (time == null || time.equals(""))
			return null;
		DateFormat format = new SimpleDateFormat(sec);
		format.setLenient(false);
		// 要转换字符串 str_test
		String strTest = time;
		try {
			Timestamp ts = new Timestamp(format.parse(strTest).getTime());
			return ts;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public static Date stringFormatDate(String dates) {
		if (dates == null || dates.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(dates);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Date stringFormatDateBySec(String dates, String sec) {
		if (dates == null || dates.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(sec);
		try {
			Date date = sdf.parse(dates);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String stringFormatDateString(String dates) {
		if (dates == null || dates.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String date = sdf.format(sdf.parse(dates));
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date()); // 得到当前时间，但返回的类型是String
		return date;
	}
	
	public static Date convert2Date(String str) {
		if (!StringUtil.isNotEmpty(str)) return null;
		try {
			return DateUtil.string2Date(str);
		} catch (Exception e) { }
		try {
			return new Date(Long.parseLong(str));
		} catch (Exception e) { }
		try {
			return DateUtil.string2Date(str, DateUtil.FORMAT_DATETIME_SHORT);
		} catch (Exception e) {}
		return null;
	}
}
