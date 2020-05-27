package com.sams.batchfile.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sams.batchfile.service.CloseDateService;
import com.sams.batchfile.service.TransactionDayService;
import com.sams.common.constant.Const;
import com.sams.common.utils.DateUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.CloseDateMapper;
import com.sams.custom.mapper.ProductOpenDayMapper;
import com.sams.custom.mapper.ProductTemplateMapper;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.ProductOpenDay;
import com.sams.sys.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName TransactionServiceImpl
 * 描述 : 交易日配置service
 * @Author weijunjie
 * @Date 2019/11/25 11:29
 */
@Service
public class TransactionServiceImpl implements TransactionDayService {

    @Autowired
    private CloseDateService closeDateService;

    @Autowired
    private CloseDateMapper closeDateMapper;

    @Autowired
    private ProductTemplateMapper productTemplateMapper;

    @Autowired
    private ProductOpenDayMapper productOpenDayMapper;

    public final String TRANSACTION_DAY_NULL = "02";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description 根据年份&市场类型获取对应的非交易日配置信息
     * @Author weijunjie
     * @Date 2019/11/25 14:45
     **/
    public HashMap<String, Object> showTransactionDay(String yearVal,String marketVal,String type){
        //组装查询参数信息
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("cdMarketCode",marketVal);
        condition.put("cdYear",yearVal);
        //查询当前请求的操作是查看还是新增

//        condition.put("cdMonth","1");
        List<CloseDate> dataGrid = closeDateService.findDataGrid(new PageInfo(), condition);
        //查询哪些月份有过配置
        List<CloseDate> notAddMonthList = closeDateMapper.selectMonList(yearVal, marketVal);

        return makeTranscationDayInfo(dataGrid,notAddMonthList,yearVal,type);
    }

    /**
     * @Description 单月数据展示 OR 修改
     * @Author weijunjie
     * @Date 2019/11/27 16:32
     **/
    public HashMap<String, Object> showDateOneMon(String yearVal,String marketVal,String type,String mon){
        HashMap<String, Object> resToJsp = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<HashMap<String,Object>> monInfoList = new ArrayList<>();
        //组装查询参数信息
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("cdMarketCode",marketVal);
        condition.put("cdYear",yearVal);
        String SelectMon = mon.length()<2?"0"+mon:mon;
        condition.put("cdMonth",SelectMon);
        List<CloseDate> dataGrid = closeDateService.findDataGrid(new PageInfo(), condition);

        if(null != dataGrid && dataGrid.size()>0){
            for(CloseDate closeDate:dataGrid){
                HashMap<String,Object> monInfo = new HashMap<>();
                monInfo.put("checkStatus",closeDate.getCdCheckState());
                String uname = StringUtils.isNotBlank(closeDate.getCdUuser())?closeDate.getCdUuser():closeDate.getCdCuser();
                monInfo.put("updateUser",uname);
                monInfo.put("updateAction",closeDate.getCdAction());
                monInfo.put("monIndex",mon+"");
                monInfoList.add(monInfo);
            }
            for(CloseDate closeDate:dataGrid){
                //组装前端需要回显的配置日期数据
                String theDate = makeDateByCloseDate(closeDate.getCdCloseDate(),"yyyyMMdd","yyyy-M-d");
                if(StringUtils.isNotBlank(theDate)){
                    dateList.add(theDate);
                }
            }
        }else{
            HashMap<String,Object> monInfo = new HashMap<>();
            monInfo.put("checkStatus",TRANSACTION_DAY_NULL);
            monInfo.put("updateUser","无");
            monInfo.put("updateAction","无");
            monInfo.put("monIndex",mon+"");
            monInfoList.add(monInfo);
            if("1".equals(type)){
                dateList.add(yearVal+"-"+mon+"-0");
            }else{
                //编辑
                List<String> allCloseDayByMonth = getAllCloseDayByMonth(yearVal, mon + "");
                dateList.addAll(allCloseDayByMonth);
            }
        }
        resToJsp.put("monInfoList",monInfoList);
        resToJsp.put("dateList",dateList);
        return resToJsp;
    }

    /**
     * @Description 组装需要返回前端的数据信息
     * @Author weijunjie
     * @Date 2019/11/25 14:54
     **/
    public HashMap<String, Object> makeTranscationDayInfo(List<CloseDate> dataGrid,List<CloseDate> monList,
                                                          String yearVal,String type){
        HashMap<String, Object> resToJsp = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<HashMap<String,Object>> monInfoList = new ArrayList<>();
        ArrayList<String> monIndexList = new ArrayList<>();
        //组装月份展示数据信息 (随机选择该月一条数据)
        if(null != monList && monList.size() > 0){
            for(int i=1;i<=12;i++){
                for(CloseDate closeDate:monList){
                    if(Integer.parseInt(closeDate.getCdMonth()) == i){
                        HashMap<String, Object> monInfo = makeOneMonInfo(closeDate,
                                "1", i + "");
                        monInfoList.add(monInfo);
                        monIndexList.add(i+"");
                    }
                }
            }
        }else{
            //年度都没有配置  回显月份信息
            for(int i=1;i<=12;i++){
                HashMap<String, Object> monInfo = makeOneMonInfo(null,
                        "2", i + "");
                monInfoList.add(monInfo);
                //判断当前是新增还是查看
                if("2".equals(type)){
                    List<String> allCloseDayByMonth = getAllCloseDayByMonth(yearVal, i + "");
                    dateList.addAll(allCloseDayByMonth);
                }
            }

        }
        //获取当前monIndexList中所有的值
        if(null != monIndexList && monIndexList.size()>0){
            for(int i=1;i<=12;i++){
                if(monIndexList.indexOf(i+"") == -1){
                    HashMap<String, Object> monInfo = makeOneMonInfo(null,
                            "2", i + "");
                    monInfoList.add(monInfo);
                    //判断当前是新增还是查看  新增当前月份添加数据信息
                    if("2".equals(type)){
                        List<String> allCloseDayByMonth = getAllCloseDayByMonth(yearVal, i + "");
                        dateList.addAll(allCloseDayByMonth);
                    }
                }
            }
        }
        resToJsp.put("monInfoList",monInfoList);

        if(dataGrid.size() >0){
            for(CloseDate closeDate:dataGrid){
                //组装前端需要回显的配置日期数据
                String theDate = makeDateByCloseDate(closeDate.getCdCloseDate(),"yyyyMMdd","yyyy-M-d");
                if(StringUtils.isNotBlank(theDate)){
                    dateList.add(theDate);
                }
            }
        }
        HashSet<String> dateSet = new HashSet(dateList);
        resToJsp.put("dateList",dateSet);
        return resToJsp;

    }

    /**
     * @Description 保存前台选中的日期数据信息
     * @Author weijunjie
     * @Date 2019/11/27 15:01
     **/
    public String saveDate(String yearVal,String marketVal,String days,SysUser sysUser){
        //获取对应的年份渠道数据
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("cdMarketCode",marketVal);
        condition.put("cdYear",yearVal);
        List<CloseDate> dataGrid = closeDateService.findDataGrid(new PageInfo(), condition);

        ArrayList<String> oldDays = new ArrayList<>();
        for(CloseDate closeDate:dataGrid){
            oldDays.add(closeDate.getCdCloseDate());
        }
        //解析获取所有的日期数据信息
        ArrayList<String> newDays = breakDateByDays(days);
        if(null != dataGrid && dataGrid.size() >0){
            logger.info("当前数据库中已存在当月的数据，请选择月份修改");
            return "当前数据库中已存在当年的数据，请选择修改";
        }else{
           //执行新增操作 拼接数据信息
            ArrayList<CloseDate> closeDates = new ArrayList<>();
            for(String closeDay:newDays){
                CloseDate closeDate = makeCloseDateByDate(closeDay,marketVal,sysUser,"01");
                closeDates.add(closeDate);
            }
            int i = closeDateMapper.insertByBatch(closeDates);
            if(i>0){
                return "成功";
            }else{
                return "新增失败，联系管理员";
            }


        }

        // 新的中有 老的中没有 表示新增  反之表示删除
        /*ArrayList<String> addDate = compareList(newDays, oldDays);
        ArrayList<String> delDate = compareList(oldDays,newDays);
        if((null == addDate ||addDate.size() == 0) && (null == delDate ||delDate.size() == 0)){
            //没有改变  不做操作
            logger.info("非交易日期没有更新操作，不做任何处理");
        }else{
            if(addDate.size() > 0){

            }
            if(delDate.size() > 0){

            }
        }*/
    }

    /**
     * @Description 更新单月数据信息
     * @Author weijunjie
     * @Date 2019/11/28 9:06
     **/
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public String updateDate(String yearVal,String marketVal,String days,SysUser sysUser,String mon){
        try {
            Map<String,Object> condition = new HashMap<>();
            condition.put("cdMarketCode",marketVal);
            condition.put("cdYear",yearVal);
            String SelectMon = mon.length()<2?"0"+mon:mon;
            condition.put("cdMonth",SelectMon);
            List<CloseDate> dataGrid = closeDateService.findDataGrid(new PageInfo(), condition);

            ArrayList<String> oldDays = new ArrayList<>();
            for(CloseDate closeDate:dataGrid){
                oldDays.add(closeDate.getCdCloseDate());
            }
            //解析获取所有的日期数据信息
            ArrayList<String> newDays = breakDateByDays(days);
            // 两个list都为null  表明没有相差的数据
            ArrayList<String> aList = compareList(newDays, oldDays);
            ArrayList<String> bList = compareList(oldDays, newDays);
            if((null != aList && aList.size() != 0) || (null != bList && bList.size() !=0)){
                //证明数据有更新
                if(aList.size() > 0){
                    //使用insert 这些数据
                    ArrayList<CloseDate> saveList = new ArrayList<>();
                    for(String closeDay:aList){
                        CloseDate closeDate = makeCloseDateByDate(closeDay,marketVal,sysUser,"02");
                        saveList.add(closeDate);
//                    closeDateService.save(closeDate);
                    }
                    closeDateMapper.insertByBatch(saveList);
                }
                if(bList.size() > 0){
                    //使用delete 这些数据
                    for(String closeDay:bList){
                        CloseDate closeDate = makeCloseDateByDate(closeDay,marketVal,sysUser,"00");
                        closeDateMapper.deleteCloseDateByDate(closeDate);
                    }
                }
                //更新当月数据全部为修改状态
                HashMap<String, String> mapToUpdateByBatch = getMapToUpdateByBatch(marketVal, yearVal, mon,
                        sysUser.getLoginName(), "02", Const.NORMAL_CHECK_STATUS_00,"1",
                        null,null,null);
                int i = closeDateMapper.updateStatusByMonthIndex(mapToUpdateByBatch);
                if(i != 0){
                    return "更新成功";
                }else{
                    return "更新失败，参数验证失败";
                }
            }else{
                //数据没有更新  不做任何操作
                return "数据没有更新,不做任何操作";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "数据更新异常";
        }
    }

    /**
     * @Description 复核操作
     * @Author weijunjie
     * @Date 2019/11/28 11:20
     **/
    public String checkMon(String yearVal,String marketVal,String mons,SysUser sysUser){
        //查询当前月份的数据的操作步骤（删除步骤进行物理删除操作）
        List<CloseDate> notAddMonthList = closeDateMapper.selectMonList(yearVal, marketVal);
        String[] split = mons.split(",");
        int z = 0;
        for(int i = 0;i<split.length;i++){
            String m = split[i].length()<2?"0"+split[i]:split[i];
            HashMap<String, String> mapToUpdateByBatch = getMapToUpdateByBatch(marketVal, yearVal, split[i],
                    sysUser.getLoginName(), null, Const.NORMAL_CHECK_STATUS_01,"1",
                    null,null,null);
            z += closeDateMapper.updateStatusByMonthIndex(mapToUpdateByBatch);

            for(CloseDate closeDate:notAddMonthList){
                if(m.equals(closeDate.getCdMonth()) && "00".equals(closeDate.getCdAction())){
                    //执行删除整月操作
                    closeDateMapper.deleteCloseDate(yearVal,m,marketVal);
                    break;
                }
            }
        }
        if(z>0){
            return "复核成功";
        }else{
            return "复核失败。联系管理员";
        }

    }

    /**
     * @Description 删除操作
     * @Author weijunjie
     * @Date 2019/11/28 11:20
     **/
    public String delMon(String yearVal,String marketVal,String mons,SysUser sysUser){
        String[] split = mons.split(",");
        int z = 0;
        for(int i = 0;i<split.length;i++){
            HashMap<String, String> mapToUpdateByBatch = getMapToUpdateByBatch(marketVal, yearVal, split[i],
                    sysUser.getLoginName(), "00", Const.NORMAL_CHECK_STATUS_00,"1",
                    null,null,null);
            z += closeDateMapper.updateStatusByMonthIndex(mapToUpdateByBatch);
        }
        if(z>0){
            return "删除成功";
        }else{
            return "删除失败。联系管理员";
        }

    }

    /**
     * @Description 获取根据条件更新状态的数据
     * @Author weijunjie
     * @Date 2019/12/2 11:23
     **/
    public HashMap<String,String> getMapToUpdateByBatch(String marketVal,String yearVal,
                                                        String mon,String userName,String action,
                                                        String checkStatus,String type,
                                                        String channelCode,String productCode,String opType){
        HashMap<String, String> hashMap = new HashMap<>();
        if("1".equals(type)){
            hashMap.put("marketVal",marketVal);
        }else{
            hashMap.put("channelCode",channelCode);
            hashMap.put("productCode",productCode);
            //新增产品开放日类型字段
            hashMap.put("opType",opType);
        }
        hashMap.put("yearVal",yearVal);
        mon = mon.length() ==1 ?"0"+mon:mon;
        hashMap.put("mon",mon);
        hashMap.put("userName",userName);
        hashMap.put("action",action);
        hashMap.put("checkStatus",checkStatus);

        return hashMap;
    }

    /**
     * @Description 组装closeDate数据
     * @Author weijunjie
     * @Date 2019/11/27 18:29
     **/
    public CloseDate makeCloseDateByDate(String closeDay,String cdMarketCode,SysUser user,String action){
        String year = closeDay.substring(0, 4);
        String month = closeDay.substring(4, 6);
        CloseDate closeDate = new CloseDate();
        closeDate.setCdYear(year);
        closeDate.setCdMonth(month);
        closeDate.setCdCloseDate(closeDay);
        closeDate.setCdMarketCode(cdMarketCode);
        closeDate.setCdCuser(user.getLoginName());
        closeDate.setCdCtime(getSysDate());
        closeDate.setCdAction(action);
        closeDate.setCdCheckState(Const.BUSINESS_STUTAS_00);
        return closeDate;
    }

    public void selectCloseDateByAction(ArrayList<String> days,String action){

    }

    /**
     * @Description 比较两个list中有差异的数据信息
     * @Author weijunjie
     * @Date 2019/11/27 15:20
     **/
    public ArrayList<String> compareList(ArrayList<String> aList,ArrayList<String> bList){
        ArrayList<String> res = new ArrayList<>();
        for(String a:aList){
            if(bList.indexOf(a) == -1){
                //a中有  b 中没有
                res.add(a);
            }
        }
        return res;
    }

    /**
     * @Description 解析出对应选择的时间
     * @Author weijunjie
     * @Date 2019/11/27 15:11
     **/
    public ArrayList<String> breakDateByDays(String days){
        ArrayList<String> strings = new ArrayList<>();
        String[] split = days.split(",");
        for(int i=0;i<split.length;i++){
            strings.add(makeDateByCloseDate(split[i],"yyyy-M-d","yyyyMMdd"));
        }
        return strings;
    }


    /**
     * @Description 根据closeDate组装前端需要展示的日期数据（2019-1-1 这种格式） f1 -> f2 格式
     * @Author weijunjie
     * @Date 2019/11/25 15:25
     **/
    public String makeDateByCloseDate(String cdCloseDate,String format1,String format2){
        SimpleDateFormat sf1 = new SimpleDateFormat(format1);
        Date date = null;
        String time = "";
        try {
            date = sf1.parse(cdCloseDate);
            if(null != date){
                SimpleDateFormat sf2 = new SimpleDateFormat(format2);
                time = sf2.format(date);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("时间格式转换异常{}",e);
        }
        return time;
    }

    /**
     * @Description 获取当前月内所有的周六周日
     * @Author weijunjie
     * @Date 2019/11/26 17:17
     **/
    public List<String> getAllCloseDayByMonth(String year,String month){
        List<String> weekDayList = new ArrayList<>();
        if(month.length() ==1) month = "0"+month;
        String aa =year+"-"+month;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        Date date=null;
        try {
            date = sf.parse(aa);
        }catch (Exception e){
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int days1 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i = 1;i<=days1;i++){
            String d = i<10?"0"+i:""+i;
            int dayofweek = dateTimetoWeek(year+month+d,"yyyyMMdd");
            if(dayofweek == 1|| dayofweek == 7){
                String theDate = makeDateByCloseDate(year+month+d,"yyyyMMdd","yyyy-M-d");
                weekDayList.add(theDate);
            }
        }
        return weekDayList;

    }

    /**
     * @Description 获取某个日期是周几
     * @Author weijunjie
     * @Date 2019/11/26 17:53
     **/
    public int dateTimetoWeek(String date,String format){
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date sfDdate = null;
        try {
            sfDdate = sf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(sfDdate);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static void main(String args[]){
        TransactionServiceImpl ts = new TransactionServiceImpl();
        String days = "2019-9-12,2019-8-9,2019-12-12,";
        List<String> allCloseDayByMonth = ts.breakDateByDays(days);
        System.out.println(allCloseDayByMonth);
    }

    public Date getSysDate(){
        Date date = productTemplateMapper.getSysDate();
        return date;
    }

    /**---------------------------产品非工作日相关service---------------------------------**/

    public HashMap<String, Object> getProductSelectHashMap(String yearVal, String channelCode,
                                                           String productCode,String opType){
        //查询月份信息数据
        HashMap<String, Object> selectMonListMap = new HashMap<>();
        selectMonListMap.put("YEAR",yearVal);
        selectMonListMap.put("CHANNELCODE",channelCode);
        if(productCode.contains("-")){
            String[] split = productCode.split("-");
            selectMonListMap.put("PRODUCTCODE",split[0]);
            selectMonListMap.put("BATCHNO",split[1]);
        }else{
            selectMonListMap.put("PRODUCTCODE",productCode);
            selectMonListMap.put("BATCHNO","1");
        }
        //新增参数交易日类型
        selectMonListMap.put("OPTYPE",opType);
        return selectMonListMap;
    }


    public HashMap<String, Object> showProductTransactionDay(String yearVal, String channelCode,
                                                             String productCode,String type,String opType){
        //前台传进来的产品编号 格式  编号-批次号，需要解析
        HashMap<String, Object> selectMonListMap = getProductSelectHashMap(yearVal,channelCode,productCode,opType);
        List<ProductOpenDay> monList = productOpenDayMapper.selectOpenDayMonList(selectMonListMap);

        //查询已配置的日期数据信息
        Map<String,Object> condition = getSelectCondition(channelCode,productCode,yearVal,null,opType);
        PageInfo pageInfo = new PageInfo();
        List<ProductOpenDay> dataGrid = productOpenDayMapper.findDataGrid(pageInfo, condition);

        //组装查询到的所有数据信息
        HashMap<String, Object> hashMap = makeProductOpenDayInfo(dataGrid, monList, yearVal, type,opType);
        return hashMap;
    }

    /**
     * @Description 组装需要返回前端的数据信息(产品工作日配置)
     * @Author weijunjie
     * @Date 2019/11/25 14:54
     **/
    public HashMap<String, Object> makeProductOpenDayInfo(List<ProductOpenDay> dataGrid,List<ProductOpenDay> monList,
                                                          String yearVal,String type,String opType){
        HashMap<String, Object> resToJsp = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<HashMap<String,Object>> monInfoList = new ArrayList<>();
        ArrayList<String> monIndexList = new ArrayList<>();
        //组装月份展示数据信息 (随机选择该月一条数据)
        if(null != monList && monList.size() > 0){
            for(int i=1;i<=12;i++){
                for(ProductOpenDay productOpenDay:monList){
                    if(Integer.parseInt(productOpenDay.getPoMonth()) == i){
                        HashMap<String, Object> monInfo = makeOneMonInfo(productOpenDay,
                                "1", i + "");
                        monInfoList.add(monInfo);
                        monIndexList.add(i+"");
                    }
                }
            }
        }else{
            //年度都没有配置  回显月份信息
            for(int i=1;i<=12;i++){
                HashMap<String, Object> monInfo = makeOneMonInfo(null,
                        "2", i + "");
                monInfoList.add(monInfo);
                //判断当前是新增还是查看
                if("2".equals(type)){
                    List<String> allCloseDayByMonth = getAllCloseDayByMonth(yearVal, i + "");
                    dateList.addAll(allCloseDayByMonth);
                }
            }
        }
        //获取当前monIndexList中所有的值
        if(null != monIndexList && monIndexList.size()>0){
            for(int i=1;i<=12;i++){
                if(monIndexList.indexOf(i+"") == -1){
                    HashMap<String, Object> monInfo = makeOneMonInfo(null,
                            "2", i + "");
                    monInfoList.add(monInfo);
                    //判断当前是新增还是查看  新增当前月份添加数据信息
                    if("2".equals(type)){
                        List<String> allCloseDayByMonth = getAllCloseDayByMonth(yearVal, i + "");
                        dateList.addAll(allCloseDayByMonth);
                    }
                }
            }
        }
        resToJsp.put("monInfoList",monInfoList);

        if(dataGrid.size() >0){
            for(ProductOpenDay productOpenDay:dataGrid){
                //组装前端需要回显的配置日期数据
                String theDate = makeDateByCloseDate(productOpenDay.getPoCloseDay(),"yyyyMMdd","yyyy-M-d");
                if(StringUtils.isNotBlank(theDate)){
                    dateList.add(theDate);
                }
            }
        }
        HashSet<String> dateSet = new HashSet(dateList);
        resToJsp.put("dateList",dateSet);
        return resToJsp;

    }

    /**
     * @Description 传入一个条数据信息，返回要展示到前端的数据信息
     * @Author weijunjie
     * @Date 2019/11/29 14:02
     **/
    public HashMap<String,Object> makeOneMonInfo(Object obj,String type,String monIndex){
        HashMap<String,Object> monInfo = new HashMap<>();
        if("2".equals(type)){
            monInfo.put("checkStatus",TRANSACTION_DAY_NULL);
            monInfo.put("updateUser","无");
            monInfo.put("updateAction","无");
            monInfo.put("monIndex",monIndex);
            return monInfo;
        }
        if(obj instanceof CloseDate){
            CloseDate closeDate = (CloseDate)obj;
            monInfo.put("checkStatus",closeDate.getCdCheckState());
            if(StringUtils.isNotBlank(closeDate.getCdUuser())){
                monInfo.put("updateUser",closeDate.getCdUuser());
            }else{
                monInfo.put("updateUser",closeDate.getCdCuser());
            }
            monInfo.put("updateAction",closeDate.getCdAction());
            monInfo.put("monIndex",monIndex);
        }
        if(obj instanceof ProductOpenDay){
            ProductOpenDay productOpenDay = (ProductOpenDay)obj;
            monInfo.put("checkStatus",productOpenDay.getPoCheckState());
            if(StringUtils.isNotBlank(productOpenDay.getPoUuser())){
                monInfo.put("updateUser",productOpenDay.getPoUuser());
            }else{
                monInfo.put("updateUser",productOpenDay.getPoCuser());
            }
            monInfo.put("updateAction",productOpenDay.getPoAction());
            monInfo.put("monIndex",monIndex);
        }
        return monInfo;
    }

    /**
     * @Description 新增非工作日配置
     * @Author weijunjie
     * @Date 2019/12/3 9:11
     **/
    public String saveProductDate(String yearVal,String channelCode,String productCode,
                                  String days,SysUser sysUser,String opType){
        //判断当前年份 下是否已经有配置的数据
        HashMap<String, Object> selectMonListMap = getProductSelectHashMap(yearVal,channelCode,productCode,opType);
        List<ProductOpenDay> monList = productOpenDayMapper.selectOpenDayMonList(selectMonListMap);
        if(monList.size() > 0){
            return "当前年份已经配置数据信息，请选择月份修改";
        }else{
            ArrayList<String> strings = new ArrayList<>();
            String[] split = days.split(",");
            for(int i=0;i<split.length;i++){
                strings.add(makeDateByCloseDate(yearVal+"-"+split[i],"yyyy-M-d","yyyyMMdd"));
            }
            ArrayList<ProductOpenDay> saveList = new ArrayList<>();
            for(String closeDay:strings){
                ProductOpenDay productOpenDay = makeProductOpenDay(channelCode, productCode,
                        closeDay, sysUser.getUserName(),"01",opType);
                saveList.add(productOpenDay);
            }
            try {
                int i = productOpenDayMapper.insertByBatch(saveList);
                if(i>0){
                    return "新增成功";
                }else{
                    return "新增失败";
                }
            }catch (Exception e){
                return "新增异常，联系管理员";
            }

        }
//        String[] split = days.split(",");

    }

    /**
     * @Description 删除某个月的所有日期数据
     * @Author weijunjie
     * @Date 2019/11/29 17:37
     **/
    public String delProductMon(String yearVal,String channelCode,String productCode,
                                String mons,SysUser sysUser,String opType){
        String batchNo = "";
        if(StringUtils.isNotBlank(productCode) && productCode.contains("-")){
            batchNo = productCode.split("-")[1];
            productCode = productCode.split("-")[0];

        }
        String[] split = mons.split(",");
        int z = 0;
        for(int i = 0;i<split.length;i++){
            HashMap<String, String> mapToUpdateByBatch = getMapToUpdateByBatch(null, yearVal, split[i],
                    sysUser.getLoginName(), "00", Const.NORMAL_CHECK_STATUS_00,"2",
                    channelCode,productCode,opType);
            mapToUpdateByBatch.put("batchNo",batchNo);
            z += productOpenDayMapper.updateStatusByMonthIndex(mapToUpdateByBatch);
        }
        if(z>0){
            return "删除成功";
        }else{
            return "删除失败。联系管理员";
        }
    }

    /**
     * @Description 复核产品非交易日信息
     * @Author weijunjie
     * @Date 2019/12/2 8:58
     **/
    public String checkProductMon(String yearVal,String channelCode,String productCode,
                                  String mons,SysUser sysUser,String opType){
        //查询当前月份的数据的操作步骤（删除步骤进行物理删除操作）
        //前台传进来的产品编号 格式  编号-批次号，需要解析
        String batchNo = "";
        if(StringUtils.isNotBlank(productCode) && productCode.contains("-")){
            batchNo = productCode.split("-")[1];
            productCode = productCode.split("-")[0];

        }
        HashMap<String, Object> selectMonListMap = getProductSelectHashMap(yearVal,channelCode,productCode,opType);
        List<ProductOpenDay> monList = productOpenDayMapper.selectOpenDayMonList(selectMonListMap);
//        List<CloseDate> notAddMonthList = closeDateMapper.selectMonList(yearVal, marketVal);
        String[] split = mons.split(",");
        int z = 0;
        for(int i = 0;i<split.length;i++){
            String m = split[i].length()<2?"0"+split[i]:split[i];
            HashMap<String, String> mapToUpdateByBatch = getMapToUpdateByBatch(null, yearVal, split[i],
                    sysUser.getLoginName(), null, Const.NORMAL_CHECK_STATUS_01,"2",
                    channelCode,productCode,opType);
            mapToUpdateByBatch.put("batchNo",batchNo);
            z += productOpenDayMapper.updateStatusByMonthIndex(mapToUpdateByBatch);
            for(ProductOpenDay productOpenDay:monList){
                if(m.equals(productOpenDay.getPoMonth()) && "00".equals(productOpenDay.getPoAction())){
                    //组装删除当前数据的hashmap
                    HashMap<String, Object> delHashMap = new HashMap<>();
                    delHashMap.put("channelCode",productOpenDay.getPoChannelCode());
                    delHashMap.put("productCode",productOpenDay.getPoProductCode());
                    delHashMap.put("batchNo",productOpenDay.getPoBatchNo());
                    delHashMap.put("year",productOpenDay.getPoYear());
                    delHashMap.put("month",productOpenDay.getPoMonth());
                    delHashMap.put("opType",productOpenDay.getPoOpenDayType());
                    productOpenDayMapper.deleteProOpenDay(delHashMap);
                    break;
                }
            }
        }
        if(z>0){
            return "复核成功";
        }else{
            return "复核失败。联系管理员";
        }
    }

    /**
     * @Description 获取反显当前选中月份的日期数据信息
     * @Author weijunjie
     * @Date 2019/12/2 9:23
     **/
    public HashMap<String, Object> showProductDateOneMon(String yearVal,String channelCode,String productCode,
                                                         String type,String mon,String opType){
        HashMap<String, Object> resToJsp = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<HashMap<String,Object>> monInfoList = new ArrayList<>();
        //组装查询参数信息
        //查询已配置的日期数据信息
        Map<String,Object> condition = getSelectCondition(channelCode,productCode,yearVal,mon,opType);
        PageInfo pageInfo = new PageInfo();
        List<ProductOpenDay> dataGrid = productOpenDayMapper.findDataGrid(pageInfo, condition);

        if(null != dataGrid && dataGrid.size()>0){
            ProductOpenDay productOpenD =dataGrid.get(0);
            HashMap<String,Object> monInfo = new HashMap<>();
            monInfo.put("checkStatus",productOpenD.getPoCheckState());
            String uname = StringUtils.isNotBlank(productOpenD.getPoUuser())
                    ?productOpenD.getPoUuser():productOpenD.getPoCuser();
            monInfo.put("updateUser",uname);
            monInfo.put("updateAction",productOpenD.getPoAction());
            monInfo.put("monIndex",mon+"");
            monInfoList.add(monInfo);
            for(ProductOpenDay productOpenDay:dataGrid){
                //组装前端需要回显的配置日期数据
                String theDate = makeDateByCloseDate(productOpenDay.getPoCloseDay(),
                        "yyyyMMdd","yyyy-M-d");
                if(StringUtils.isNotBlank(theDate)){
                    dateList.add(theDate);
                }
            }
        }else{
            HashMap<String,Object> monInfo = new HashMap<>();
            monInfo.put("checkStatus",TRANSACTION_DAY_NULL);
            monInfo.put("updateUser","无");
            monInfo.put("updateAction","无");
            monInfo.put("monIndex",mon+"");
            monInfoList.add(monInfo);
            if(dateList.size() == 0){
                dateList.add(yearVal+"-"+mon+"-0");
            }
        }
        resToJsp.put("monInfoList",monInfoList);
        resToJsp.put("dateList",dateList);
        return resToJsp;
    }

    /**
     * @Description 更新产品非工作日配置
     * @Author weijunjie
     * @Date 2019/12/2 10:38
     **/
    public String updateProductDate(String yearVal,String channelCode,String productCode,
                                    String days,SysUser sysUser,String mon,String opType){
        try {
            Map<String,Object> condition = getSelectCondition(channelCode,productCode,yearVal,mon,opType);
            PageInfo pageInfo = new PageInfo();
            List<ProductOpenDay> dataGrid = productOpenDayMapper.findDataGrid(pageInfo, condition);

            ArrayList<String> oldDays = new ArrayList<>();
            for(ProductOpenDay productOpenDay:dataGrid){
                oldDays.add(productOpenDay.getPoCloseDay());
            }
            //解析获取所有的日期数据信息
            ArrayList<String> newDays = breakDateByDays(days);
            // 两个list都为null  表明没有相差的数据
            ArrayList<String> aList = compareList(newDays, oldDays);
            ArrayList<String> bList = compareList(oldDays, newDays);
            if((null != aList && aList.size() != 0) || (null != bList && bList.size() !=0)){
                //证明数据有更新
                if(aList.size() > 0){
                    //使用insert 这些数据
                    ArrayList<ProductOpenDay> saveList = new ArrayList<>();
                    for(String closeDay:aList){
                        ProductOpenDay productOpenDay = makeProductOpenDay(channelCode, productCode,
                                closeDay, sysUser.getUserName(),"01",opType);
                        saveList.add(productOpenDay);
//                    closeDateService.save(closeDate);
                    }
                    productOpenDayMapper.insertByBatch(saveList);
                }
                if(bList.size() > 0){
                    //使用delete 这些数据
                    for(String closeDay:bList){
                        ProductOpenDay productOpenDay = makeProductOpenDay(channelCode, productCode,
                                closeDay, sysUser.getUserName(), "00",opType);
                        productOpenDayMapper.deleteCloseDateByDate(productOpenDay);
                    }
                }
                //更新当月数据全部为修改状态
                HashMap<String, String> mapToUpdateByBatch = getMapToUpdateByBatch(null, yearVal, mon,
                        sysUser.getLoginName(), "02", Const.NORMAL_CHECK_STATUS_00,"2",
                        channelCode,productCode.split("-")[0],opType);
//                productCode.split("-")[1];
                mapToUpdateByBatch.put("batchNo",productCode.split("-")[1]);
                int i = productOpenDayMapper.updateStatusByMonthIndex(mapToUpdateByBatch);
                if(i != 0){
                    return "更新成功";
                }else{
                    return "更新失败，参数验证失败";
                }
            }else{
                //数据没有更新  不做任何操作
                return "数据没有更新,不做任何操作";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "数据更新异常";
        }
    }

    /**
     * @Description 获取查询数据的的hashmap
     * @Author weijunjie
     * @Date 2019/12/2 10:49
     **/
    public HashMap<String,Object> getSelectCondition(String channelCode,String productCode,
                                                     String yearVal,String mon,String opType){
        HashMap<String,Object> condition = new HashMap<String,Object>();
        condition.put("channelCode",channelCode);
        if(productCode.indexOf("-") != -1){
            String[] split = productCode.split("-");
            condition.put("productCode",split[0]);
            condition.put("batchNo",split[1]);
        }else{
            condition.put("productCode",productCode);
        }
//        condition.put("productCode",productCode);
        condition.put("year",yearVal);
        condition.put("opType",opType);
        if (StringUtils.isNotBlank(mon)){
            mon = mon.length() ==1 ?"0"+mon:mon;
            condition.put("month",mon);
        }
        return condition;
    }

    /**
     * @Description 组装产品非工作日配置实体类
     * @Author weijunjie
     * @Date 2019/12/2 11:11
     **/
    public ProductOpenDay makeProductOpenDay(String channelCode,String productCode,String date,
                                             String userName,String action,String opType){
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        ProductOpenDay openDay = new ProductOpenDay();
        openDay.setPoChannelCode(channelCode);
        openDay.setPoProductCode(productCode.split("-")[0]);
        openDay.setPoYear(year);
        openDay.setPoMonth(month);
        openDay.setPoCloseDay(date);
        openDay.setPoBatchNo(productCode.split("-")[1]);
        openDay.setPoCuser(userName);
        openDay.setPoCtime(getSysDate());
        openDay.setPoAction(action);
        openDay.setPoCheckState(Const.BUSINESS_STUTAS_00);
        //新增开放日类型字段
        openDay.setPoOpenDayType(opType);
        return openDay;
    }
}
