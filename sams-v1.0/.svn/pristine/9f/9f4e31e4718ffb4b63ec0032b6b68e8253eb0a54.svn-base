package com.sams.batchfile.service;

import com.sams.custom.model.CloseDate;
import com.sams.sys.model.SysUser;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TransactionDayService
 * 描述 : 交易日service
 * @Author weijunjie
 * @Date 2019/11/25 11:28
 */
public interface TransactionDayService {

    public HashMap<String, Object> showTransactionDay(String yearVal, String marketVal,String type);

    public String saveDate(String yearVal,String marketVal,String days,SysUser sysUser);

    public String updateDate(String yearVal,String marketVal,String days,SysUser sysUser,String mon);

    public HashMap<String, Object> showDateOneMon(String yearVal,String marketVal,String type,String mon);

    public String checkMon(String yearVal,String marketVal,String mons,SysUser sysUser);

    public String delMon(String yearVal,String marketVal,String mons,SysUser sysUser);

    public HashMap<String, Object> showProductTransactionDay(String yearVal, String channelCode,String productCode,String type,String opType);

    public String delProductMon(String yearVal,String channelCode,String productCode,String mons,SysUser sysUser,String opType);

    public String checkProductMon(String yearVal,String channelCode,String productCode,String mons,SysUser sysUser,String opType);

    public HashMap<String, Object> showProductDateOneMon(String yearVal,String channelCode,String productCode,String type,String mon,String opType);

    public String updateProductDate(String yearVal,String channelCode,String productCode,String days,SysUser sysUser,String mon,String opType);

    public String saveProductDate(String yearVal,String channelCode,String productCode,String days,SysUser sysUser,String opType);
    
    public List<String> getAllCloseDayByMonth(String year,String month);
    
    public String makeDateByCloseDate(String cdCloseDate,String format1,String format2);
}
