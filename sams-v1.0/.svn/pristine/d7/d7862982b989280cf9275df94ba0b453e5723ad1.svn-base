package com.sams.bizParameterManage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.utils.MyMapUtils;
import com.sams.common.utils.StringUtils;
import com.sams.custom.model.PProductInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.ChannelProductService;
import com.sams.batchfile.service.ParProductInfoService;
import com.sams.common.constant.Const;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.ChannelProductInfo;
import com.sams.sys.model.SysUser;
import com.sams.sys.service.SysDictService;

@Controller
@RequestMapping("/channelProduct")
public class ChannelProductController extends BaseController{

	@Autowired
	private ChannelProductService channelProductService;
	
	@Autowired
	public ChannelInfoService  channelInfoService;
	
	@Autowired
	public ParProductInfoService parProductInfoService;
	/**
	 * 代销渠道产品关系管理页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/channelProduct/channelProductList";
	}
	
	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getChannelProductData")
	@ResponseBody
	public PageInfo getParProductTempInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.get("piChannelProductCode")!=null){
			if(MyMapUtils.getStringArrayBySplit(condition,"piChannelProductCode","-").length>1){
				condition.put("piBatchNumber", MyMapUtils.getStringArrayBySplit(condition,"piChannelProductCode","-")[1]);
			}
			condition.put("piChannelProductCode", MyMapUtils.getStringArrayBySplit(condition,"piChannelProductCode","-")[0]);
		}else{
			condition.put("piBatchNumber", null);
		}
		pageInfo.setPageResult(channelProductService.findChannelProductCondition(pageInfo, condition));
		return pageInfo;
	}
	
	/**
	 * 代销渠道产品关系管理新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/channelProduct/channelProductAdd";
	}
	
	/**
	 * 代销渠道产品关系管理新增确认
	 * 
	 * @return
	 */
    @RequestMapping("/addSubmit")
    @ResponseBody
    public Object addSubmit(ChannelProductInfo entity) {
    	String msg = "";
    	int channelCount = channelInfoService.selectByCode(entity.getCpChannelCode());
    	if(channelCount==0){
    		msg="该渠道不存在";
    		return resultSuccess(msg); 
    	}
    	int productCount = parProductInfoService.selectProductCount(entity.getCpChannelProductCode().split("-")[0]);
    	if(productCount==0){
    		msg="该代销产品不存在";
    		return resultSuccess(msg); 
    	}
    	int taChannelCount = parProductInfoService.selectTaProductCount(entity.getCpTaProductCode());
    	if(taChannelCount==0){
    		msg="该TA产品不存在";
    		return resultSuccess(msg); 
    	}
    	ChannelProduct channelProduct = new ChannelProduct();
    	channelProduct.setCpChannelCode(entity.getCpChannelCode()==null?"":entity.getCpChannelCode());
    	channelProduct.setCpChannelProductCode(entity.getCpChannelProductCode().split("-")[0]==null?"":entity.getCpChannelProductCode().split("-")[0]);
    	channelProduct.setCpTaProductCode(entity.getCpTaProductCode()==null?"":entity.getCpTaProductCode());
    	channelProduct.setCpTaProductName(entity.getCpTaProductName()==null?"":entity.getCpTaProductName());
    	channelProduct.setCpIsSetUp(entity.getCpIsSetUp()==null?"":entity.getCpIsSetUp());
    	SysUser user=getCurrentUser();
    	channelProduct.setCpCuser(user.getLoginName());
    	channelProduct.setCpCtime(getSysDate());
    	channelProduct.setCpCheckState(Const.BUSINESS_STUTAS_00);
    	channelProduct.setCpAction(Const.BUSINESS_STUTAS_01);
    	channelProduct.setCpValidFlag(Const.BUSINESS_STUTAS_01);
    	String batchNumber = entity.getCpBatchNumber(); //批次号
    	Map map = Maps.newHashMap();
    	map.put("FUNDCODE", channelProduct.getCpChannelProductCode());
    	map.put("CHANNELCODE", channelProduct.getCpChannelCode());
    	map.put("TAFUNDCODE", channelProduct.getCpTaProductCode());
    	map.put("BATCHNUMBER", batchNumber);
    	channelProduct.setCpBatchNumber(batchNumber);
    	//特殊判断西南证券
    	/*if(!"TTTNETXNZQ".equals(entity.getCpChannelCode()) && !"1".equals(batchNumber)){
    		msg = "选择的产品批次号不为1,请修改代销端产品批次号!";
    		return resultSuccess(msg); 
    	}*/
    	List<ChannelProduct> list = channelProductService.selectChannelProduct(map);
    	if(list==null||list.size()==0){
    		int num = channelProductService.save(channelProduct);
        	if(num==1){
        		msg="新增成功";
        	}else{
        		msg="新增失败";
        	}
    	}else{
    		msg="该对应关系已存在";
    	}
    	
	return resultSuccess(msg); 
    }
    
    /**
   	 *代销渠道产品关系管理修改
   	 *
   	 * @return
   	 */
   	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
   	public String editPage(Model model, String cpId) {
   		ChannelProductInfo entity = channelProductService.selectChannelProductInfo(cpId);
   		entity.setCpChannelProductCode(entity.getCpChannelProductCode()+"-"+entity.getCpBatchNumber());
   		model.addAttribute("entity",entity);
   		return "sys/bizParameterManage/channelProduct/channelProductEdit";
   	}

	/**
	 *代销渠道产品关系是否存在
	 *
	 * @return
	 */
	@RequestMapping(value = "/productInfoGet")
	@ResponseBody
	public Object productInfoGet(String cpId) {
		//判断对应的产品code是否存在正常使用
        ChannelProduct channelProduct = channelProductService.selectByKey(cpId);
		String s = "0";
		if(null != channelProduct && StringUtils.isNotBlanks(channelProduct.getCpChannelProductCode())){
            PProductInfo pp = parProductInfoService.selectByProductCode(channelProduct.getCpChannelProductCode());
            if(null == pp){
                s = "1";
            }
        }
		return resultSuccess(s);
	}

    /**
     *代销渠道产品关系是否存在
     *
     * @return
     */
    @RequestMapping(value = "/deleteErrorData")
    @ResponseBody
    public Object deleteErrorData(String cpId) {
        //判断对应的产品code是否存在正常使用
        ChannelProduct channelProduct = channelProductService.selectByKey(cpId);
        int delete = 0;
        if(null != channelProduct && StringUtils.isNotBlanks(channelProduct.getCpChannelProductCode())){
            PProductInfo pp = parProductInfoService.selectByProductCode(channelProduct.getCpChannelProductCode());
            if(null == pp){
                //产品表查不到该产品code数据，执行物理删除操作
                delete = channelProductService.delete(cpId);
            }
        }
        if(delete >0){
            return resultSuccess("删除成功");
        }else{
            return resultSuccess("删除失败");
        }

    }

   	/**
   	 * 代销渠道客户经理关系修改确认
   	 * 
   	 * @return
   	 */
       @RequestMapping("/editSubmit")
       @ResponseBody
       public Object editSubmit(ChannelProductInfo entity) {
    	   String msg = "";
    	   int channelCount = channelInfoService.selectByCode(entity.getCpChannelCode());
       	if(channelCount==0){
       		msg="该渠道不存在";
       		return resultSuccess(msg); 
       	}
       	int productCount = parProductInfoService.selectProductCount(entity.getCpChannelProductCode().split("-")[0]);
       	if(channelCount==0){
       		msg="该代销产品不存在";
       		return resultSuccess(msg); 
       	}
       	int taChannelCount = parProductInfoService.selectTaProductCount(entity.getCpTaProductCode());
       	if(taChannelCount==0){
       		msg="该TA产品不存在";
       		return resultSuccess(msg); 
       	}
       	ChannelProduct channelProduct = new ChannelProduct();
       	channelProduct.setCpId(entity.getCpId()==null?0:entity.getCpId());
       	channelProduct.setCpChannelCode(entity.getCpChannelCode()==null?"":entity.getCpChannelCode());
       	channelProduct.setCpBatchNumber(entity.getCpChannelProductCode().split("-")[1]==null?"":entity.getCpChannelProductCode().split("-")[1]);
       	channelProduct.setCpChannelProductCode(entity.getCpChannelProductCode().split("-")[0]==null?"":entity.getCpChannelProductCode().split("-")[0]);
       	channelProduct.setCpTaProductCode(entity.getCpTaProductCode()==null?"":entity.getCpTaProductCode());
       	channelProduct.setCpTaProductName(entity.getCpTaProductName()==null?"":entity.getCpTaProductName());
       	channelProduct.setCpIsSetUp(entity.getCpIsSetUp()==null?"":entity.getCpIsSetUp());
       	channelProduct.setCpCuser(entity.getCpCuser()==null?"":entity.getCpCuser());
       	channelProduct.setCpCtime(entity.getCpCtime());
       	channelProduct.setCpValidFlag(entity.getCpValidFlag()==null?"":entity.getCpValidFlag());
       	SysUser user=getCurrentUser();
    	channelProduct.setCpCuser(user.getLoginName());
    	channelProduct.setCpCtime(getSysDate());
    	channelProduct.setCpCheckState(Const.BUSINESS_STUTAS_00);
    	channelProduct.setCpAction(Const.BUSINESS_STUTAS_02);
    	channelProduct.setCpUuser(null);
    	channelProduct.setCpUtime(null);
       	int num = channelProductService.updateAll(channelProduct);
       	if(num==1){
       		msg="修改成功";
       	}else{
       		msg="修改失败";
       	}
       	return resultSuccess(msg); 
       }
       
       /**
      	 *代销渠道产品关系管理查看
      	 * 
      	 * @return
      	 */
      	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
      	public String viewPage(Model model, String cpId) {
      		ChannelProductInfo entity = channelProductService.selectChannelProductInfo(cpId);
      		entity.setCpChannelProductCode(entity.getCpChannelProductCode()+"-"+entity.getCpBatchNumber());
      		model.addAttribute("entity",entity);
      		return "sys/bizParameterManage/channelProduct/channelProductView";
      	}
      	
      	/**
    	 * 代销渠道产品关系管理删除
    	 * 
    	 * @return
    	 */
    	@RequestMapping(value = "/delSubmit", method = RequestMethod.POST)
    	@ResponseBody
    	public Object delSubmit(String cpIds) {
    		String cpIdsa = cpIds.substring(1, cpIds.length()-1);
    		String[] cpIdss = cpIdsa.split(",");
    		String msg ="";
    		SysUser user=getCurrentUser();
    		for(int i=0;i<cpIdss.length;i++){
    			ChannelProduct channelProduct = channelProductService.selectByKey(cpIdss[i]);
        		channelProduct.setCpValidFlag(Const.BUSINESS_STUTAS_00);
        		channelProduct.setCpCheckState(Const.BUSINESS_STUTAS_00);
        		channelProduct.setCpAction(Const.BUSINESS_STUTAS_00);
        		channelProduct.setCpCuser(user.getLoginName());
        		channelProduct.setCpCtime(getSysDate());
        		channelProduct.setCpUuser(null);
            	channelProduct.setCpUtime(null);
        		int num = channelProductService.updateAll(channelProduct);
        		if(num==1){
        			msg="渠道产品关系已删除";
        		}else{
        			msg="操作失败";
        		}
    		}
    		return resultSuccess(msg); 
    	}
    	
    	/**
    	 * 代销渠道产品关系管理复核
    	 * 
    	 * @return
    	 */
    	@RequestMapping(value = "/checkSubmit", method = RequestMethod.POST)
    	@ResponseBody
    	public Object checkSubmit(String cpIds) {
    		String cpIdsa = cpIds.substring(1, cpIds.length()-1);
    		String[] cpIdss = cpIdsa.split(",");
    		String msg ="";
    		SysUser user=getCurrentUser();
    		for(int i=0;i<cpIdss.length;i++){
    			ChannelProduct channelProduct = channelProductService.selectByKey(cpIdss[i]);
        		channelProduct.setCpCheckState(Const.BUSINESS_STUTAS_01);
        		channelProduct.setCpUuser(user.getLoginName());
        		channelProduct.setCpUtime(getSysDate());
        		int num = channelProductService.updateAll(channelProduct);
        		if(num==1){
        			msg="渠道关系已复核";
        		}else{
        			msg="操作失败";
        		}
    		}
    		return resultSuccess(msg); 
    	}
}
