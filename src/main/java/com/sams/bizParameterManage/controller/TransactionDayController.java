package com.sams.bizParameterManage.controller;

import com.sams.batchfile.service.ProTypeOpenDayService;
import com.sams.batchfile.service.TransactionDayService;
import com.sams.common.constant.Const;
import com.sams.common.shiro.ShiroUser;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.model.PProductInfo;
import com.sams.sys.model.SysUser;
import com.sams.sys.service.SysDictService;
import com.sams.sys.service.SysUserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName TransactionDayController
 * 描述 : 交易日controller
 * @Author weijunjie
 * @Date 2019/11/25 9:15
 */
@Controller
@RequestMapping("/transactionDay")
public class TransactionDayController extends BaseController {

    @Autowired
    private TransactionDayService transactionDayService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private InterfaceColInfoMapper interfaceColInfoMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;
    
    @Autowired
    private ProTypeOpenDayService proTypeOpenDayService;

    /**------------------------渠道非交易日相关controller--Start----------------------------**/
    @RequestMapping(method = RequestMethod.GET)
    public String manager(Model model) {

        model.addAttribute("userName",getCurrentUser().getLoginName());
        return "sys/bizParameterManage/closeDate/TransactionDayList";
    }

    public String getThisYear(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        String thisYear = sf.format(new Date());
        return thisYear;
    }

    /**
     * @Description 渠道交易日列表
     * @Author weijunjie
     * @Date 2019/11/26 14:29
     **/
    @RequestMapping("/getDateList")
    @ResponseBody
    public Object getDateList(String yearVal,String marketVal,String type){
//        初始值判断数据
        yearVal = StringUtils.isNotBlank(yearVal)?yearVal:getThisYear();
        marketVal = StringUtils.isNotBlank(marketVal)?marketVal:"001";
        type = StringUtils.isNotBlank(type)?type:"1";
        HashMap<String, Object> hashMap = showTransactionDay(yearVal, marketVal,type);
        Object dateList = hashMap.get("dateList");
        return dateList;
    }

    /**
     * @Description
     * @Author weijunjie
     * @Date 2019/11/26 14:29
     **/
    @RequestMapping("/getMonInfoList")
    @ResponseBody
    public Object getMonInfoList(String yearVal,String marketVal,String type){
        yearVal = StringUtils.isNotBlank(yearVal)?yearVal:getThisYear();
        marketVal = StringUtils.isNotBlank(marketVal)?marketVal:"001";
        type = StringUtils.isNotBlank(type)?type:"1";
        HashMap<String, Object> hashMap = showTransactionDay(yearVal, marketVal,type);
        Object monInfoList = hashMap.get("monInfoList");
        return monInfoList;
    }

    /**
     * @Description 查询市场类型下该年度所有非交易日配置 (type = 1 查询展示页面  type = 2 )
     * @Author weijunjie
     * @Date 2019/11/25 11:11
     **/
    public HashMap<String, Object> showTransactionDay(String yearVal,String marketVal,String type){
        return transactionDayService.showTransactionDay(yearVal,marketVal,type);
    }

    /**
     * @Description 查询市场类型下该年度所有非交易日配置 (type = 1 查询展示页面  type = 2 )
     * @Author weijunjie
     * @Date 2019/11/25 11:11
     **/
    public HashMap<String, Object> showDateOneMon(String yearVal,String marketVal,String type,String mon){
        return transactionDayService.showDateOneMon(yearVal,marketVal,type,mon);
    }

    /**
     * @Description 获取需要展示的年份列表 （当前年份前两年，后七年（十年跨度））
     * @Author weijunjie
     * @Date 2019/11/25 11:12
     **/
    @RequestMapping("/getTheYearList")
    @ResponseBody
    public JSONArray getTheYearList(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        String thisYear = sf.format(new Date());
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        if(StringUtils.isNotBlank(thisYear)){
            for(int i = -2;i<=7;i++){
                HashMap<String, String> hashMap = new HashMap<>();
                int yearInt = Integer.parseInt(thisYear) + i;
                hashMap.put("yearName",yearInt+"年");
                hashMap.put("yearVal",yearInt+"");
                hashMaps.add(hashMap);
            }
        }
        return JSONArray.fromObject(hashMaps);
    }

    /**
     * @Description 获取市场类型数据
     * @Author weijunjie
     * @Date 2019/11/25 13:47
     **/
    @RequestMapping("/getMarketType")
    @ResponseBody
    public JSONArray getMarketType(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("marketVal","001");
        jsonObject.put("marketName","银行市场");
        jsonArray.add(jsonObject);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("marketVal","002");
        jsonObject2.put("marketName","券商市场");
        jsonArray.add(jsonObject2);
        return jsonArray;
    }

    /**
     * @Description 新增页面
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping("/addPage")
    public String addPage(String yearVal,String marketVal,Model model){
        /*HashMap<String, Object> hashMap = showTransactionDay(yearVal,marketVal,"2");
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));*/
        return "sys/bizParameterManage/closeDate/TransactionDayAdd";
    }

    /**
     * @Description 单月信息修改页面
     * @Author weijunjie
     * @Date 2019/11/27 17:49
     **/
    @RequestMapping("/editPage")
    public String editPage(String yearVal,String marketVal,Model model,String monIndex){
        HashMap<String, Object> hashMap = showDateOneMon(yearVal,marketVal,"2",monIndex);
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");
        model.addAttribute("yearVal",yearVal);
        model.addAttribute("monIndex",monIndex);
        model.addAttribute("marketVal",marketVal);
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));

        return "sys/bizParameterManage/closeDate/TransactionDayEdit";
    }

    /**
     * @Description 单月信息查看页面
     * @Author weijunjie
     * @Date 2019/11/27 17:49
     **/
    @RequestMapping("/viewPage")
    public String viewPage(String yearVal,String marketVal,Model model,String monIndex){
        HashMap<String, Object> hashMap = showDateOneMon(yearVal,marketVal,"1",monIndex);
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");
        model.addAttribute("yearVal",yearVal);
        model.addAttribute("monIndex",monIndex);
        model.addAttribute("marketVal",marketVal);
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));

        return "sys/bizParameterManage/closeDate/TransactionDayView";
    }

    /**
     * @Description 新增日期确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/addSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object addSubmit(String yearVal,String marketVal,String days){
        String s = transactionDayService.saveDate(yearVal, marketVal, days, getCurrentUser());
        //同步新增到产品类型工作日,新增券商工作日时同步  20200619 
        String msg = "";
        if("002".equals(marketVal)){
        	msg = proTypeOpenDayService.syncInsert2ProductTypeOpenDay(yearVal,days,getLoginName());
        	logger.info("同步插入产品类型工作日的结果为{}",msg);
        }
            return resultSuccess(s + msg);
    }

	/**
     * @Description 修改日期确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object editSubmit(String yearVal,String marketVal,String days,String monVal){
        String s = transactionDayService.updateDate(yearVal, marketVal, days, getCurrentUser(),monVal);
        return resultSuccess(s);
    }

    /**
     * @Description 复核月份确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/checkSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object checkSubmit(String yearVal,String marketVal,String mons){
        String s = transactionDayService.checkMon(yearVal, marketVal, mons, getCurrentUser());
        return resultSuccess(s);
    }

    /**
     * @Description 删除月份确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/delSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object delSubmit(String yearVal,String marketVal,String mons){
        String s = transactionDayService.delMon(yearVal, marketVal, mons, getCurrentUser());
        return resultSuccess(s);
    }

    /**
     * 获取当前登录用户对象
     * @return
     */
    public SysUser getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        SysUser currentUser = sysUserService.selectByKey(user.userId);
        return currentUser;
    }
    /**------------------------渠道非交易日相关controller--End----------------------------**/

    /**------------------------产品非交易日相关controller--Start----------------------------**/
    @RequestMapping(value = "/showProductDayPage",method = RequestMethod.GET)
    public String showProductDayPage(Model model) {
        model.addAttribute("userName",getCurrentUser().getLoginName());
        return "sys/bizParameterManage/productOpenDay/ProductTransactionDayList";
    }

    /**
     * @Description 获取产品月份信息列表
     * @Author weijunjie
     * @Date 2020/1/6 13:18
     **/
    @RequestMapping(value = "/getProductMonInfoList")
    @ResponseBody
    public Object getProductMonInfoList(String yearVal,String channelCode,String productCode,
                                        String type,String opType){
        yearVal = StringUtils.isNotBlank(yearVal)?yearVal:getThisYear();
        if(StringUtils.isNotBlank(productCode)){
            channelCode = StringUtils.isNotBlank(channelCode)?channelCode:null;
        }else{
            channelCode = StringUtils.isNotBlank(channelCode)?channelCode:getOneInUseChannelCode("1","ID",null);
            productCode = StringUtils.isNotBlank(productCode)?productCode:getOneInUseChannelCode("1","ID",channelCode);
        }
        type = StringUtils.isNotBlank(type)?type:"1";
        HashMap<String, Object> hashMap = transactionDayService.showProductTransactionDay(yearVal,channelCode,productCode,type,opType);
        Object monInfoList = hashMap.get("monInfoList");
        return monInfoList;
    }

    /**
     * @Description
     * @Author weijunjie
     * @Date 2020/1/6 13:14
     **/
    @RequestMapping(value = "/getProductDayList")
    @ResponseBody
    public Object getProductDayList(String yearVal,String channelCode,String productCode,
                                    String type,String opType){
        yearVal = StringUtils.isNotBlank(yearVal)?yearVal:getThisYear();
        if(StringUtils.isNotBlank(productCode)){
            channelCode = StringUtils.isNotBlank(channelCode)?channelCode:null;
        }else{
            channelCode = StringUtils.isNotBlank(channelCode)?channelCode:getOneInUseChannelCode("1","ID",null);
            productCode = StringUtils.isNotBlank(productCode)?productCode:getOneInUseChannelCode("1","ID",channelCode);
        }
        type = StringUtils.isNotBlank(type)?type:"1";
        HashMap<String, Object> hashMap = transactionDayService.showProductTransactionDay(yearVal,channelCode,productCode,type,opType);
        Object dateList = hashMap.get("dateList");
        return dateList;
    }

    /**
     * @Description 获取随机一条在用的渠道code
     * @Author weijunjie
     * @Date 2019/12/2 14:37
     **/
    @RequestMapping("/getOneChannelInfo")
    @ResponseBody
    public Object getOneChannelInfo(){
        String id = getOneInUseChannelCode("1", "ID", null);
        return resultSuccess(id);
    }

    /**
     * @Description 获取随机一条在用的渠道的产品code
     * @Author weijunjie
     * @Date 2019/12/2 14:37
     **/
    @RequestMapping("/getOneProductInfo")
    @ResponseBody
    public Object getOneProductInfo(String channelCode){
        String id = getOneInUseChannelCode("1", "ID", channelCode);
        return resultSuccess(id);
    }

    /**
     * @Description 获取到一个在用渠道的信息
     * @Author weijunjie
     * @Date 2019/11/29 11:18
     **/
    public String getOneInUseChannelCode(String type,String key,String cCode){
        String resString = "";
        if("1".equals(type)){
            //获取渠道信息
            List<Map<String, Object>> channelCodeList = getChannelCodeList();
            if(null!=channelCodeList && channelCodeList.size()>0){
                resString = MapUtils.getString(channelCodeList.get(0),key);
            }
            return resString;
        }else{
            //通过渠道code获取一条产品信息数据
            List<Map<String, Object>> channelCodeList = getProductCodeByChannel(cCode);
            if(null!=channelCodeList && channelCodeList.size()>0){
                resString = MapUtils.getString(channelCodeList.get(0),key);
            }
            return resString;
        }
    }

    /**
     * @Description 获取在用渠道产品
     * @Author weijunjie
     * @Date 2019/11/29 11:07
     **/
    @RequestMapping("/getProductCodeByChannel")
    @ResponseBody
    public List<Map<String, Object>> getProductCodeByChannel(String channelCode){
        List<Map<String, Object>> list = productInfoMapper.queryUsedProductInfoComBox(channelCode);
        return list;
    }

    /**
     * @Description 获取所有渠道
     * @Author weijunjie
     * @Date 2019/11/29 11:08
     **/
    public List<Map<String, Object>> getChannelCodeList(){
        List<Map<String, Object>> list = interfaceColInfoMapper.queryChannelInfoComBox();
        return list;
    }

    /**
     * @Description 通过产品code获取对应的渠道信息
     * @Author weijunjie
     * @Date 2019/11/29 16:17
     **/
    @RequestMapping("/getChannelInfoByP")
    @ResponseBody
    public JSONArray getChannelInfoByP(String productCode){
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        if(StringUtils.isNotBlank(productCode)){
            if(productCode.indexOf("-") != -1){
                String[] split = productCode.split("-");
                productCode =split[0];
            }
            PProductInfo pProductInfo = productInfoMapper.selectByProductCode(productCode);
            if(null != pProductInfo){
                hashMap.put("",pProductInfo.getPiChannelCode());
                //获取渠道名称
            }
        }

        return JSONArray.fromObject(hashMaps);
    }

    /**
     * @Description 产品交易日新增页面
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping("/addProductPage")
    public String addProductPage(){
        return "sys/bizParameterManage/productOpenDay/ProductTransactionDayAdd";
    }

    /**
     * @Description 新增日期确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/addProductSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object addProductSubmit(String yearVal,String channelCode,String productCode,
                                   String days,String opType){
        System.out.println("yearVal="+yearVal+"marketVal="+channelCode+"productCode="+productCode+
                "days="+ days);
        String s = transactionDayService.saveProductDate(yearVal, channelCode,productCode, days, getCurrentUser(),opType);
        return resultSuccess(s);
    }

    /**
     * @Description 单月信息修改页面
     * @Author weijunjie
     * @Date 2019/11/27 17:49
     **/
    @RequestMapping("/editProductPage")
    public String editProductPage(String yearVal,String channelCode,String productCode,
                                  Model model,String monIndex,String channelName,String productName,
                                  String type,String opType) throws Exception{
        HashMap<String, Object> hashMap = transactionDayService.showProductDateOneMon(yearVal,channelCode,productCode,type,monIndex,opType);
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");
        String cc= URLDecoder.decode(channelName, "utf-8");
        String pp= URLDecoder.decode(productName, "utf-8");
        model.addAttribute("yearVal",yearVal);
        model.addAttribute("monIndex",monIndex);
        model.addAttribute("channelName",cc);
        model.addAttribute("productName",pp);
        model.addAttribute("channelCode",channelCode);
        model.addAttribute("productCode",productCode);
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));
        model.addAttribute("opType",opType);
        if(Const.OP_TYPE_000.equals(opType)){
            return "sys/bizParameterManage/productOpenDay/ProductTransactionDayEdit";
        }else{
            return "sys/bizParameterManage/productOpenDay/ProductBusinessDayEdit";
        }
    }

    /**
     * @Description 修改日期确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/editProductSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object editProductSubmit(String yearVal,String channelCode,String productCode,
                                    String days,String monVal,String opType){
        String s = transactionDayService.updateProductDate(yearVal, channelCode,productCode, days, getCurrentUser(),monVal,opType);
        return resultSuccess(s);
    }

    /**
     * @Description 单月信息查看页面
     * @Author weijunjie
     * @Date 2019/11/27 17:49
     **/
    @RequestMapping("/viewProductPage")
    public String viewProductPage(String yearVal,String channelCode,String productCode,
                                  Model model,String monIndex,String channelName,String productName,
                                  String type,String opType) throws Exception{
        HashMap<String, Object> hashMap = transactionDayService.showProductDateOneMon(yearVal,channelCode,productCode,type,monIndex,opType);
        Object dateList = hashMap.get("dateList");
        Object monInfoList = hashMap.get("monInfoList");

        String cc= URLDecoder.decode(channelName, "utf-8");
        String pp= URLDecoder.decode(productName, "utf-8");
        model.addAttribute("yearVal",yearVal);
        model.addAttribute("monIndex",monIndex);
        model.addAttribute("channelName",cc);
        model.addAttribute("productName",pp);
        model.addAttribute("channelCode",channelCode);
        model.addAttribute("productCode",productCode);
        model.addAttribute("dataList",JSONArray.fromObject(dateList));
        model.addAttribute("monInfoList",JSONArray.fromObject(monInfoList));
        model.addAttribute("opType",opType);
        if(Const.OP_TYPE_000.equals(opType)){
            return "sys/bizParameterManage/productOpenDay/ProductTransactionDayView";
        }
        else{
            return "sys/bizParameterManage/productOpenDay/ProductBusinessDayView";
        }
    }

    /**
     * @Description 复核月份确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/checkProductSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object checkProductSubmit(String yearVal,String channelCode,String productCode,
                                     String mons,String opType){
        String s = transactionDayService.checkProductMon(yearVal,channelCode,productCode,mons,getCurrentUser(),opType);
        return resultSuccess(s);
    }

    /**
     * @Description 删除月份确认
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping(value = "/delProductSubmit",method = RequestMethod.POST)
    @ResponseBody
    public Object delProductSubmit(String yearVal,String channelCode,String productCode,
                                   String mons,String opType){
        String s = transactionDayService.delProductMon(yearVal, channelCode,productCode, mons, getCurrentUser(),opType);
        return resultSuccess(s);
    }

    /**-----------------产品业务交易日相关接口--------------------------**/

    /**
     * @Description 业务开放日页面
     * @Author weijunjie
     * @Date 2020/1/6 14:49
     **/
    @RequestMapping(value = "/showBusinessOpenDayPage",method = RequestMethod.GET)
    public String showBusinessOpenDayPage(Model model) {
        model.addAttribute("userName",getCurrentUser().getLoginName());
        return "sys/bizParameterManage/productOpenDay/ProductBusinessDayList";
    }

    /**
     * @Description 产品业务开放日新增页面
     * @Author weijunjie
     * @Date 2019/11/25 11:26
     **/
    @RequestMapping("/addBusinessOpenDayPage")
    public String addBusinessOpenDayPage(){
        return "sys/bizParameterManage/productOpenDay/ProductBusinessDayAdd";
    }

    /**
     * @Description 获取交易业务类型
     * @Author weijunjie
     * @Date 2019/11/25 13:47
     **/
    @RequestMapping("/getOpenDayType")
    @ResponseBody
    public JSONArray getOpenDayType(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("opTypeVal",Const.OP_TYPE_022);
        jsonObject.put("opTypeName","申购");
        jsonArray.add(jsonObject);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("opTypeVal",Const.OP_TYPE_024);
        jsonObject2.put("opTypeName","赎回");
        jsonArray.add(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("opTypeVal",Const.OP_TYPE_130);
        jsonObject3.put("opTypeName","认购确认");
        jsonArray.add(jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("opTypeVal",Const.OP_TYPE_124);
        jsonObject4.put("opTypeName","赎回确认");
        jsonArray.add(jsonObject4);
        return jsonArray;
    }

}
