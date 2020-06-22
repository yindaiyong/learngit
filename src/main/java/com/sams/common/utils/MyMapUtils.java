package com.sams.common.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName MyMapUtils
 * 描述 :
 * @Author weijunjie
 * @Date 2019/12/23 10:44
 */
public class MyMapUtils extends MapUtils {

    /**
     * @Description 获取map中的数据去执行sql条件（数据有null）
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String getStringToSqlByTrim(Map map, Object key){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return null;
            }else{
                return string.trim();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @Description 获取map中的数据去执行sql条件（数据有null）
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String getStringForDefault(Map map, Object key,String defaultValue){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return defaultValue;
            }else{
                return string.trim();
            }
        }catch (Exception e){
            e.printStackTrace();
            return defaultValue;
        }

    }

    /**
     * @Description 获取map中的数据去执行sql条件（数据有null）
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String getStringToSql(Map map, Object key){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return null;
            }else{
                return string;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description 获取map中的数据去除空格
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String getStringByTrim(Map map, Object key){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return "";
            }else{
                return string.trim();
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @Description 获取map中的数据去除空格
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String getStringNotNull(Map map, Object key){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return "";
            }else{
                return string;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @Description 获取map中的数据去打印log日志
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String getStringToLog(Map map, Object key){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return "null";
            }else{
                return string;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "null";
        }

    }

    /**
     * @Description 获取map中的数据去除空格
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static String[] getStringArrayBySplit(Map map, Object key,String sp){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return new String[]{""};
            }else{
                if(string.contains(sp)){
                    return string.split(sp);
                }else{
                    return new String[]{string};
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return new String[]{""};
        }
    }

    /**
     * @Description 获取map中bigDecimal数据
     * @Author weijunjie
     * @Date 2019/12/23 10:45
     **/
    public static BigDecimal getBigDecimal(Map map, Object key){
        try {
            String string = getString(map, key);
            if(StringUtils.isBlank(string)){
                return new BigDecimal("0");
            }else{
                return new BigDecimal(string);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BigDecimal("0");
        }
    }
}
