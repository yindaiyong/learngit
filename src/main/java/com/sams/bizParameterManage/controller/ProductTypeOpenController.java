package com.sams.bizParameterManage.controller;

import com.alibaba.fastjson.JSON;
import com.sams.batchfile.service.ProTypeOpenDayService;
import com.sams.common.constant.Const;
import com.sams.common.web.controller.BaseController;
import com.sams.sys.mapper.SysDictMapper;
import com.sams.sys.service.SysDictService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:产品类型工作日设置
 * @author:
 * @date:
 */
@Controller
@RequestMapping(value = "/productType")
public class ProductTypeOpenController extends BaseController{


    @Autowired
    private ProTypeOpenDayService proTypeOpenDayService;
    
    @Autowired
	private SysDictService sysDictService;
    
    @Autowired
	private SysDictMapper sysDictMapper;
    
    
    /**
     * 返回产品类型工作日配置界面
     * @Title: productTypePage
     * @author: yindy 2020/6/17 13:52
     * @param: []
     * @return: java.lang.String
     * @throws
     */
    @RequestMapping()
    public String productTypePage(Model model){
    	//用于复核时判断
    	model.addAttribute("userName",getLoginName());
        return "sys/bizParameterManage/productType/productTypeOpenList";
    }

    /**
     * 查询产品类型工作日配置界面产品类型下拉
     * @Title: getProductTypeCombox   
     * @author: yindy 2020年6月17日 下午2:57:14
     * @param: @return      
     * @return: Object      
     * @throws
     */
    @RequestMapping(value = "/getProductTypeCombox")
    @ResponseBody
    public Object getProductTypeCombox(){
        List<Map<String,Object>> list = proTypeOpenDayService.getProductTypeCombox();
        return JSON.toJSON(list);
    }
    
    /**
     * 获得界面配置数据  
     * @Title: getDateList   
     * @author: yindy 2020年6月18日 上午9:26:15
     * @param: @param productType
     * @param: @param yearVal
     * @param: @param type
     * @param: @return      
     * @return: Object      
     * @throws
     */
    @RequestMapping("/getDateList")
    @ResponseBody
    public Object getDateList(String productType,String yearVal,String type){
    	Map<String, Object> map = getMonthAndDayList(productType,yearVal,type);
    	Object object = map.get("dateList");
    	return object;
    }
    /**
     * 获得界面月份状态数据   
     * @Title: getMonInfoList   
     * @author: yindy 2020年6月18日 上午9:26:42
     * @param: @param productType
     * @param: @param yearVal
     * @param: @param type
     * @param: @return      
     * @return: Object      
     * @throws
     */
	@RequestMapping("/getMonInfoList")
    @ResponseBody
    public Object getMonInfoList(String productType,String yearVal,String type){
    	Map<String, Object> map = getMonthAndDayList(productType,yearVal,type);
    	Object object = map.get("monInfoList");
    	return object;
    }
	
	/**
	 * 日期数据和月份状态组合   
	 * @Title: getMonthAndDayList   
	 * @author: yindy 2020年6月18日 上午9:27:23
	 * @param: @param productType
	 * @param: @param yearVal
	 * @param: @param type
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
    public Map<String, Object> getMonthAndDayList(String productType,String yearVal, String type) {
		return proTypeOpenDayService.getMonthAndDayList(productType,yearVal,type);
	}
    
    /**
     * 产品类型工作日新增页面   
     * @Title: addPage   
     * @author: yindy 2020年6月18日 上午11:35:26
     * @param: @return      
     * @return: String      
     * @throws
     */
    @RequestMapping("/addPage")
    public String addPage(){
    	return "sys/bizParameterManage/productType/productTypeOpenAdd";
    }
    
    /**
     * 新增产品类型工作日提交
     * @Title: addSubmit   
     * @author: yindy 2020年6月18日 上午11:40:24
     * @param: @param yearVal
     * @param: @param productType
     * @param: @param days
     * @param: @return      
     * @return: Object      
     * @throws
     */
    @RequestMapping(value = "/addSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object addSubmit(String yearVal,String productType,String days){
        String s = proTypeOpenDayService.saveDate(yearVal, productType, days, getLoginName());
            return resultSuccess(s);
    }
    
    /**
     * 产品类型工作日修改   
     * @Title: editPage   
     * @author: yindy 2020年6月18日 下午1:50:30
     * @param: @param yearVal
     * @param: @param marketVal
     * @param: @param model
     * @param: @param monIndex
     * @param: @return      
     * @return: String      
     */
    @RequestMapping("/editPage")
    public String editPage(String yearVal,String productType,Model model,String monIndex){
        HashMap<String, Object> hashMap = proTypeOpenDayService.showDateOneMon(yearVal,productType,"2",monIndex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dictType", Const.FUND_TYPE);
        map.put("dictCode", productType);
        String productName = sysDictMapper.findByCondition(map).get(0).getDictName();
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");
        model.addAttribute("yearVal",yearVal);
        model.addAttribute("monIndex",monIndex);
        model.addAttribute("productName",productName);
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));

        return "sys/bizParameterManage/productType/productTypeOpenEdit";
    }
    
    /**
     * 查看界面   
     * @Title: viewPage   
     * @author: yindy 2020年6月18日 下午3:02:16
     * @param: @param yearVal
     * @param: @param marketVal
     * @param: @param model
     * @param: @param monIndex
     * @param: @return      
     * @return: String      
     * @throws
     */
    @RequestMapping("/viewPage")
    public String viewPage(String yearVal,String productType,Model model,String monIndex){
        HashMap<String, Object> hashMap = proTypeOpenDayService.showDateOneMon(yearVal,productType,"1",monIndex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dictType", Const.FUND_TYPE);
        map.put("dictCode", productType);
        String productName = sysDictMapper.findByCondition(map).get(0).getDictName();
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");
        model.addAttribute("yearVal",yearVal);
        model.addAttribute("monIndex",monIndex);
        model.addAttribute("productName",productName);
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));

        return "sys/bizParameterManage/productType/productTypeOpenView";
    }
    
    /**
     * 提交修改 
     * @Title: editSubmit   
     * @author: yindy 2020年6月18日 下午3:09:43
     * @param: @param yearVal
     * @param: @param marketVal
     * @param: @param days
     * @param: @param monVal
     * @param: @return      
     * @return: Object      
     * @throws
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object editSubmit(String yearVal,String productType,String days,String monVal){
        String s = proTypeOpenDayService.updateDate(yearVal, productType, days, getLoginName(),monVal);
        return resultSuccess(s);
    }


    /**
     * @Description 复核月份确认
     **/
    @RequestMapping(value = "/checkSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object checkSubmit(String yearVal,String productType,String mons){
        String s = proTypeOpenDayService.checkMon(yearVal, productType, mons, getLoginName());
        return resultSuccess(s);
    }

    /**
     * @Description 删除月份确认
     **/
    @RequestMapping(value = "/delSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object delSubmit(String yearVal,String productType,String mons){
    	logger.info("{}用户操作删除产品类型{}工作日的月份为{}",getLoginName(),productType,mons);
        String s = proTypeOpenDayService.delMon(yearVal, productType, mons);
        return resultSuccess(s);
    }
    
    
}
