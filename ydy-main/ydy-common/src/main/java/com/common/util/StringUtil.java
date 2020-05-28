package com.common.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StringUtil {

    public static boolean isNotNull(Object obj) {
        return obj == null ? false : true;
    }

    public static boolean isNotEmpty(Object obj) {
        return (obj == null || obj.toString().trim().equals("")) ? false : true;
    }

    public static String formatNumber(String format, Object obj, RoundingMode rm) {
        if (!StringUtil.isNotNull(obj))
            obj = 0;
        DecimalFormat df = new DecimalFormat(format);
        df.setRoundingMode(rm);
        return df.format(obj);
    }

    public static String toString(Object obj) {
        if (!isNotEmpty(obj))
            return "";
        return obj.toString();
    }

    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);

        for (i = 0; i < src.length(); i++) {

            j = src.charAt(i);

            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    private static Map<Character, String> mapHtml = new HashMap<Character, String>() {
        private static final long serialVersionUID = -4969551400165818468L;
        {
            put('<', "lt");
            put('>', "gt");
        }
    };

    public static String escapeHtmlTag(String str) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(str.length());
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            String entityName = mapHtml.get(c);
            if (entityName == null || entityName.equals("")) {
                tmp.append(c);
            } else {
                tmp.append('&');
                tmp.append(entityName);
                tmp.append(';');
            }
        }
        return tmp.toString();
    }
    
	public static String firstLetterToUpper(String str){
		if (str == null || "".equals(str)) return str;
        char[] array = str.toCharArray();
        array[0] -= 32;
        return String.valueOf(array);
    }
	
	public static boolean isNotEmpty(Object... objs) {
        if (objs == null) {
            return false;
        }
        for (Object o : objs) {
            if (!isNotEmpty(o)) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean containElement(String[] arr, String str){
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].equals(str)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}
}
