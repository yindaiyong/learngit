package com.sams.bizParameterManage.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ParProductInfoService;
import com.sams.batchfile.service.ProductSalePrarmsService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.utils.MyMapUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.mapper.ProductSalePrarmsMapper;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductSalePrarms;
import com.sams.sys.model.SysUser;


@Controller
@RequestMapping("/productSalePrarms")
public class ProductSalePrarmsController extends BaseController{

	@Autowired
	public ProductSalePrarmsService  productSalePrarmsService;
	
	@Autowired
	public ProductSalePrarmsMapper  productSalePrarmsMapper;
	
	@Autowired
	private ParProductInfoService  parProductInfoService;
	/**
	 * 多币种产品信息参数表页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/productSalePrarms/productSalePrarmsList";
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
	@RequestMapping(value = "/getProductSalePrarmsData")
	@ResponseBody
	public PageInfo getProductSalePrarmsData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.get("pspChannelProductCode")!=null){
			condition.put("pspChannelProductCode", MyMapUtils.getStringArrayBySplit(condition,"pspChannelProductCode","-")[0]);
		}
		pageInfo.setPageResult(productSalePrarmsService.findDataGrid(pageInfo,
				condition));
		return pageInfo;
	}
	
	/**
	 * 多币种产品信息新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/productSalePrarms/productSalePrarmsAdd";
	}
	
	 /**
		 * 多币种产品信息新增确认
		 * 
		 * @return
		 */
	    @RequestMapping("/addSubmit")
	    @ResponseBody
	    public Object addSubmit( ProductSalePrarms entity) {
	             
	    	String msg = "";
	    	String channelCode = entity.getPspChannelCode();
	    	String currentType = entity.getPspCurrencyType();
	    	//String subChannelCode = entity.getPspSubChannelCode()==null?"": entity.getPspSubChannelCode();
	    	String channelProductCode = entity.getPspChannelProductCode().split("-")[0];
	    	entity.setPspChannelProductCode(channelProductCode);
	    	
	    	Map<String ,Object> qryMap = new HashMap<String ,Object>();
	    	qryMap.put("channelCode", channelCode);
	    	qryMap.put("channelProductCode", channelProductCode);
	    	qryMap.put("currentType", currentType);
	    	//qryMap.put("subChannelCode", subChannelCode);
	    	
	    	
	    	//校验子渠道编号是否与字典表配置一致
	    	/*String checkSubCOde = parProductInfoService.checkSubChannelCode(channelCode, subChannelCode);
	    	if(ExceptionConStants.retCode_0000  !=checkSubCOde){
	    		return resultSuccess(checkSubCOde);
	    	}*/
	    	
	    	//校验该多币种信息是否已存在表中
	    	int count = productSalePrarmsService.countProductSalePrarms(qryMap);
	    	if(count>0){
	    		msg="该多币种产品信息已存在";
	    		return resultSuccess(msg); 
	    	}
	    	
	    	//校验子渠道编号是否已在产品信息中存在
	    	int productCount = parProductInfoService.countProductSubChannelCode(qryMap);
	    	if(productCount>0){
	    		msg="该多币种信息已存在于产品信息表中";
	    		return resultSuccess(msg); 
	    	}
	    	
	    	SysUser user=getCurrentUser();
	    	entity.setPspCuser(user.getLoginName());
	    	entity.setPspCtime(getSysDate());
	    	entity.setPspValidFlag(Const.BUSINESS_STUTAS_01);
	    	entity.setPspCheckFlag(Const.BUSINESS_STUTAS_00);

	    	int num =productSalePrarmsService.save(entity);
	    	if(num==1){
	    		msg="新增成功";
	    	}else{
	    		msg="新增失败";
	    	}
	    	return resultSuccess(msg); 
	    }
	    
	    /**
		 * 多币种产品信息修改
		 * 
		 * @return
		 */
		@RequestMapping(value = "/editPage", method = RequestMethod.GET)
		public String editPage(String pspId,Model model ) {
			ProductSalePrarms entity = productSalePrarmsService.selectByKey(pspId);
			model.addAttribute("entity",entity);
			return "sys/bizParameterManage/productSalePrarms/productSalePrarmsEdit";
		}
	  
		 /**
			 * 多币种产品信息修改确认
			 * 
			 * @return
			 */
		    @RequestMapping("/editSubmit")
		    @ResponseBody
		    public Object editSubmit( ProductSalePrarms entity) {
		             
		    	String msg = "";
		    	String channelCode = entity.getPspChannelCode();
		    	String currentType = entity.getPspCurrencyType();
		    	//String subChannelCode = entity.getPspSubChannelCode()==null?"": entity.getPspSubChannelCode();
		    	String channelProductCode =entity.getPspChannelProductCode().split("-")[0];
		    	
		    	Map<String ,Object> qryMap = new HashMap<String ,Object>();
		    	qryMap.put("channelCode", channelCode);
		    	qryMap.put("channelProductCode", channelProductCode);
		    	qryMap.put("currentType", currentType);
		    	//qryMap.put("subChannelCode", subChannelCode);
		    	
		    	//校验子渠道编号是否与字典表配置一致
		    	/*String checkSubCOde = parProductInfoService.checkSubChannelCode(channelCode, subChannelCode);
		    	if(ExceptionConStants.retCode_0000  !=checkSubCOde){
		    		return resultSuccess(checkSubCOde);
		    	}*/
		    	
		    	//校验子渠道编号是否已在产品信息中存在
		    	int productCount = parProductInfoService.countProductSubChannelCode(qryMap);
		    	if(productCount>0){
		    		msg="该多币种信息已存在于产品信息表中";
		    		return resultSuccess(msg); 
		    	}
		    	SysUser user=getCurrentUser();
		    	entity.setPspCuser(user.getLoginName());
		    	entity.setPspCtime(getSysDate());
		    	entity.setPspValidFlag(Const.BUSINESS_STUTAS_01);
		    	entity.setPspCheckFlag(Const.BUSINESS_STUTAS_00);
		    	
		    	int num =productSalePrarmsService.updateAll(entity);
		    	if(num==1){
		    		msg="修改成功";
		    	}else{
		    		msg="修改失败";
		    	}
		    	return resultSuccess(msg); 
		    }
		    
		    /**
			 * 多币种产品信息查看
			 * 
			 * @return
			 */
			@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
			public String viewPage(String pspId,Model model ) {
				ProductSalePrarms entity = productSalePrarmsService.selectByKey(pspId);
				model.addAttribute("entity",entity);
				return "sys/bizParameterManage/productSalePrarms/productSalePrarmsView";
			}
			
			 /**
			 * 多币种产品信息删除
			 * 
			 * @return
			 */
			@RequestMapping(value = "/delete",method = RequestMethod.POST )
			@ResponseBody
			public Object delete(String pspIds) {
				String piIdsa = pspIds.substring(1, pspIds.length()-1);
				String[] piIdss = piIdsa.split(",");
				String msg ="";
				SysUser user=getCurrentUser();
				for(int i=0;i<piIdss.length;i++){
					int num = productSalePrarmsService.delete(piIdss[i]);
					if(num==1){
						msg="删除成功";
					}else{
						msg="删除失败";
					}
				}
				return resultSuccess(msg); 
			}
			
			 /**
			 * 多币种产品信息复核
			 * 
			 * @return
			 */
			@RequestMapping(value = "/check",method = RequestMethod.POST )
			@ResponseBody
			public Object check(String pspIds) {
				String piIdsa = pspIds.substring(1, pspIds.length()-1);
				String[] piIdss = piIdsa.split(",");
				String msg ="";
				SysUser user=getCurrentUser();
				for(int i=0;i<piIdss.length;i++){
					ProductSalePrarms entity = productSalePrarmsMapper.selectProSalePrarmsById(new BigDecimal(piIdss[i].toString()));
					entity.setPspUuser(user.getLoginName());
			    	entity.setPspUtime(getSysDate());
					entity.setPspCheckFlag(Const.BUSINESS_STUTAS_01);
					int num =productSalePrarmsService.updateAll(entity);
			    	if(num==1){
			    		msg="复核成功";
			    	}else{
			    		msg="复核失败";
			    	}
				}
				return resultSuccess(msg); 
			}

}