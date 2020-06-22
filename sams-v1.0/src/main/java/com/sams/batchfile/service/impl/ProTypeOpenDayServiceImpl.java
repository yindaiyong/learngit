package com.sams.batchfile.service.impl;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2020/6/17 14:49
 * @Description:
 */

import org.apache.commons.lang3.StringUtils;

import com.sams.batchfile.service.ProTypeOpenDayService;
import com.sams.batchfile.service.TransactionDayService;
import com.sams.common.constant.Const;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ProTypeOpenDayMapper;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.ProTypeOpenDay;
import com.sams.sys.model.SysUser;
import com.sams.sys.service.SysDictService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProTypeOpenDayServiceImpl implements ProTypeOpenDayService {
	
	private Logger logger = LoggerFactory.getLogger(ProTypeOpenDayServiceImpl.class);
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
    private ProTypeOpenDayMapper proTypeOpendayMpper;

	@Autowired
	private TransactionDayService transactionDayService;
	
    @Override
    public List<Map<String, Object>> getProductTypeCombox() {
        //查询配参数
    	String openProType = sysDictService.findDictVo(Const.OPEN_PRO_TYPE);
    	Map<String,Object> optype = new HashMap<String, Object>();
    	optype.put("OPTYPE", StringUtils.isEmpty(openProType) ? null : Arrays.asList(openProType.split(",")));
    	List<Map<String, Object>> list = proTypeOpendayMpper.getProductTypeCombox(optype);
    	return list;
    }


	@Override
	public Map<String, Object> getMonthAndDayList(String productType,String yearVal,String type) {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("ptoProductType",productType);
		condition.put("ptoYear",yearVal);
		
		PageHelperUtils.startPage(new PageInfo());
		List<ProTypeOpenDay> dataGrid = proTypeOpendayMpper.findProductOpenCondition(new PageInfo(), condition);		
		//查询哪些月份有过配置
        List<ProTypeOpenDay> notAddMonthList = proTypeOpendayMpper.selectMonList(yearVal, productType);
        
        return makePrroductTypeDayInfo(dataGrid,notAddMonthList,yearVal,type);
	}


	/**
	 * 对查询的日期数据和月份状态数据进行转换   
	 * @Title: makePrroductTypeDayInfo   
	 * @author: yindy 2020年6月18日 上午9:28:33
	 * @param: @param dataGrid
	 * @param: @param monList
	 * @param: @param yearVal
	 * @param: @param type
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> makePrroductTypeDayInfo(
			List<ProTypeOpenDay> dataGrid,
			List<ProTypeOpenDay> monList, String yearVal, String type) {
		HashMap<String, Object> resToJsp = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<HashMap<String,Object>> monInfoList = new ArrayList<>();
        ArrayList<String> monIndexList = new ArrayList<>();
        //组装月份展示数据信息 (随机选择该月一条数据)
        if(null != monList && monList.size() > 0){
            for(int i=1;i<=12;i++){
                for(ProTypeOpenDay typeOpenDay:monList){
                    if(Integer.parseInt(typeOpenDay.getPtoMonth()) == i){
                        HashMap<String, Object> monInfo = makeOneMonInfo(typeOpenDay,
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
                    List<String> allCloseDayByMonth = transactionDayService.getAllCloseDayByMonth(yearVal, i + "");
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
                        List<String> allCloseDayByMonth = transactionDayService.getAllCloseDayByMonth(yearVal, i + "");
                        dateList.addAll(allCloseDayByMonth);
                    }
                }
            }
        }
        resToJsp.put("monInfoList",monInfoList);

        if(dataGrid.size() >0){
            for(ProTypeOpenDay typeOpenDay:dataGrid){
                //组装前端需要回显的配置日期数据
                String theDate = transactionDayService.makeDateByCloseDate(typeOpenDay.getPtoOpenDay(),"yyyyMMdd","yyyy-M-d");
                if(StringUtils.isNotBlank(theDate)){
                    dateList.add(theDate);
                }
            }
        }
        HashSet<String> dateSet = new HashSet(dateList);
        resToJsp.put("dateList",dateSet);
        return resToJsp;
	}


	private HashMap<String, Object> makeOneMonInfo(Object obj,
			String type, String monIndex) {
		HashMap<String,Object> monInfo = new HashMap<>();
		if("2".equals(type)){
            monInfo.put("checkStatus","02");
            monInfo.put("updateUser","无");
            monInfo.put("updateAction","无");
            monInfo.put("monIndex",monIndex);
            return monInfo;
        }
		if(obj instanceof ProTypeOpenDay){
			ProTypeOpenDay typeOpenDay = (ProTypeOpenDay)obj;
			monInfo.put("checkStatus",typeOpenDay.getPtoCheckState());
            if(StringUtils.isNotBlank(typeOpenDay.getPtoUuser())){
                monInfo.put("updateUser",typeOpenDay.getPtoUuser());
            }else{
                monInfo.put("updateUser",typeOpenDay.getPtoCuser());
            }
            monInfo.put("updateAction",typeOpenDay.getPtoAction());
            monInfo.put("monIndex",monIndex);
		}
		return monInfo;
	}


	@Override
	public String saveDate(String yearVal, String productType, String days,
			String currentUser) {
		logger.info("{}用户操作新增产品类型{}工作日的日期为{}",currentUser,productType,days);
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("ptoProductType",productType);
		condition.put("ptoYear",yearVal);
		
		PageHelperUtils.startPage(new PageInfo());
		List<ProTypeOpenDay> dataGrid = proTypeOpendayMpper.findProductOpenCondition(new PageInfo(), condition);
		if(null != dataGrid && dataGrid.size() >0){
            logger.info("当前数据库中已存在当月的数据，请选择月份修改");
            return "当前数据库中已存在当年的数据,请选择修改";
        }else{
        	List<ProTypeOpenDay> typeOpenDay = getInsertProductTypeOpenDay(days,productType,currentUser);
        	int i = proTypeOpendayMpper.insertByBatch(typeOpenDay);
            if(i>0){
                return "成功";
            }else{
                return "新增失败，联系管理员";
            }
        }
	}

	/**
	 * 把新增数据转成可插入数据   
	 * @Title: getInsertProductTypeOpenDay   
	 * @author: yindy 2020年6月18日 下午1:10:00
	 * @param: @param days
	 * @param: @param productType
	 * @param: @param currentUser
	 * @param: @return      
	 * @return: List<ProTypeOpenDay>      
	 * @throws
	 */
	private List<ProTypeOpenDay> getInsertProductTypeOpenDay(String days,
			String productType, String currentUser) {
		List<ProTypeOpenDay> insertList= new ArrayList<ProTypeOpenDay>();
		if(!StringUtils.isEmpty(days)){
			String[] split = days.split(",");
			int length = split.length;
			for(int i = 0;i < length;i++){
				ProTypeOpenDay typeOpenDay = new ProTypeOpenDay();
				String openday = transactionDayService.makeDateByCloseDate(split[i],"yyyy-M-d","yyyyMMdd");
				String year = openday.substring(0, 4);
		        String month = openday.substring(4, 6);
		        
		        typeOpenDay.setPtoProductType(productType);
		        typeOpenDay.setPtoYear(year);
		        typeOpenDay.setPtoMonth(month);
		        typeOpenDay.setPtoOpenDay(openday);
		        typeOpenDay.setPtoCuser(currentUser);
		        typeOpenDay.setPtoCtime(new Date());
		        typeOpenDay.setPtoAction("01");
		        typeOpenDay.setPtoCheckState("00");
		        
		        insertList.add(typeOpenDay);
			}
		}
		
		return insertList;
	}


	/**
	 * 产品类型工作日单月修改或者查看   
	 * @Title: showDateOneMon   
	 * @author: yindy 2020年6月18日 下午1:54:19
	 * @param: @param yearVal
	 * @param: @param productType
	 * @param: @param string
	 * @param: @param monIndex
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public HashMap<String, Object> showDateOneMon(String yearVal,
			String productType, String type, String mon) {
		HashMap<String, Object> resToJsp = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<HashMap<String,Object>> monInfoList = new ArrayList<>();
        //组装查询参数信息
        Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("ptoProductType",productType);
		condition.put("ptoYear",yearVal);
		String SelectMon = mon.length()<2?"0"+mon:mon;
		condition.put("ptoMonth",SelectMon);
		
		PageHelperUtils.startPage(new PageInfo());
		List<ProTypeOpenDay> dataGrid = proTypeOpendayMpper.findProductOpenCondition(new PageInfo(), condition);

        if(null != dataGrid && dataGrid.size()>0){
            for(ProTypeOpenDay typeOpenDay : dataGrid){
                HashMap<String,Object> monInfo = new HashMap<>();
                monInfo.put("checkStatus",typeOpenDay.getPtoCheckState());
                String uname = StringUtils.isNotBlank(typeOpenDay.getPtoUuser())?typeOpenDay.getPtoUuser():typeOpenDay.getPtoCuser();
                monInfo.put("updateUser",uname);
                monInfo.put("updateAction",typeOpenDay.getPtoAction());
                monInfo.put("monIndex",mon+"");
                monInfoList.add(monInfo);
            }
            for(ProTypeOpenDay typeOpenDay : dataGrid){
                //组装前端需要回显的配置日期数据
                String theDate = transactionDayService.makeDateByCloseDate(typeOpenDay.getPtoOpenDay(),"yyyyMMdd","yyyy-M-d");
                if(StringUtils.isNotBlank(theDate)){
                    dateList.add(theDate);
                }
            }
        }else{
            HashMap<String,Object> monInfo = new HashMap<>();
            monInfo.put("checkStatus","02");
            monInfo.put("updateUser","无");
            monInfo.put("updateAction","无");
            monInfo.put("monIndex",mon+"");
            monInfoList.add(monInfo);
            if("1".equals(type)){
                dateList.add(yearVal+"-"+mon+"-0");
            }else{
                //编辑
                List<String> allCloseDayByMonth = transactionDayService.getAllCloseDayByMonth(yearVal, mon + "");
                dateList.addAll(allCloseDayByMonth);
            }
        }
        resToJsp.put("monInfoList",monInfoList);
        resToJsp.put("dateList",dateList);
        return resToJsp;
	}


	@Override
	public String updateDate(String yearVal, String productType, String days,
			String currentUser, String monVal) {
		logger.info("{}用户操作更新产品类型{}工作日的日期为{}",currentUser,productType,days);
		List<ProTypeOpenDay> typeOpenDay = getInsertProductTypeOpenDay(days,productType,currentUser);
		//删除当月
		if(monVal.length() == 1 ){
			monVal = "0" + monVal;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productType", productType);
		map.put("month", monVal);
		proTypeOpendayMpper.deleteProductTypeOpenDay(map);
		int i = proTypeOpendayMpper.insertByBatch(typeOpenDay);
        if(i>0){
            return "成功";
        }else{
            return "新增失败，联系管理员";
        }
	}


	@Override
	public String checkMon(String yearVal, String productType, String mons,
			String loginName) {
		logger.info("{}用户操作复核产品类型{}工作日的月份为{}",loginName,productType,mons);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("YEAR", yearVal);
		map.put("PRODUCTTYPE", productType);
		map.put("USERNAME", loginName);
		String[] months = mons.split(",");
		List<String> list = new ArrayList<String>();
		int length = months.length;
		for (int i = 0; i < length; i++) {
			String month = months[i];
			if(month.length() == 1 ){
				month = "0" + month;
			}
			list.add(month);
		}
		map.put("MONTHS", list);
		int i = proTypeOpendayMpper.checkByBatch(map);
        if(i>0){
            return "成功";
        }else{
            return "复核失败,联系管理员";
        }
	}


	@Override
	public String delMon(String yearVal, String productType, String mons) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productType", productType);
		List<String> monthList = new ArrayList<String>();
		String[] months = mons.split(",");
		int length = months.length;
		for (int i = 0; i < length; i++) {
			String month = months[i];
			if(month.length() == 1 ){
				month = "0" + month;
			}
			monthList.add(month);
		}
		map.put("MONTHS", monthList);
		int i = proTypeOpendayMpper.deleteProductTypeOpenDay(map);
        if(i>0){
            return "成功";
        }else{
            return "复核失败,联系管理员";
        }
	}

	/**
	 * 插入市场工作日时同步插入产品类型工作日   
	 * @Title: syncInsert2ProductTypeOpenDay   
	 * @author: yindy 2020年6月19日 下午1:19:25
	 * @param: @param yearVal
	 * @param: @param days
	 * @param: @param loginName
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public String syncInsert2ProductTypeOpenDay(String yearVal, String days,
			String loginName) {
		//查询配参数
    	String openProType = sysDictService.findDictVo(Const.OPEN_PRO_TYPE);
    	if(!StringUtils.isEmpty(openProType)){
    		List<String> typeList = Arrays.asList(openProType.split(","));
    		StringBuilder msg = new StringBuilder();
    		for (String type : typeList) {
    			msg.append("产品类型")
	    			.append(type)
	    			.append(saveDate(yearVal, type, days, loginName));
			}
    		return msg.toString();
    	}else{
    		return "没有可配置产品类型开放日的产品类型!";
    	}
	}
	
	
}
