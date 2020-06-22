package com.sams.bizParameterManage.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ParProductInfoService;
import com.sams.batchfile.service.ProductTemplateService;
import com.sams.common.constant.Const;
import com.sams.common.utils.RandomizingID;
import com.sams.common.utils.ServletUtils;
import com.sams.common.utils.StringUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductTemplate;
import com.sams.custom.model.result.ParTemplateResult;
import com.sams.sys.model.SysDict;
import com.sams.sys.model.SysUser;
import com.sams.sys.service.SysDictService;

@Controller
@RequestMapping("/productTemplate")
public class ProductTemplateController extends BaseController{
	
	@Autowired
	private ProductTemplateService productTemplateService;
	
	@Autowired
	private ParProductInfoService parProductInfoService;
	
	@Autowired
	public SysDictService  sysDictService;
	
	

	/**
	 * 产品信息模板页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/productTemplate/productTemplateList";
	}

	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param title
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getParProductTempInfoData")
	@ResponseBody
	public PageInfo getParProductTempInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(productTemplateService.findDataGrid(pageInfo,
				condition));
		return pageInfo;
	}
	
	/**
	 * 模板信息新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/productTemplate/productTemplateAdd";
	}
	
	/**
	 * 模板信息新增
	 * 
	 * @return
	 */
    @RequestMapping("/addSubmit")
    @ResponseBody
    public Object addSubmit(ParTemplateResult entity) {
    	String msg = "";
    	int fundTypeCount = sysDictService.findDictCount("fundType", entity.getPiProductType());
    	if(fundTypeCount!=1){
    		msg="产品类型不存在";
    		return resultSuccess(msg); 
    	}
    	String ptTemplateCode  = productTemplateService.selectNextCodeSequence();
    		ProductTemplate instProductTemplate = new ProductTemplate();
        	ProductTemplate indiProductTemplate = new ProductTemplate();
        	indiProductTemplate.setPtTemplateCode(ptTemplateCode);
        	indiProductTemplate.setPtTemplateName(entity.getPiChannelProductName());
        	indiProductTemplate.setPtProductCode(entity.getPiProductType());
        	indiProductTemplate.setPtIndividualOrInstitution("0");
        	indiProductTemplate.setPtMinBidsAmt(entity.getPiIndiMinBidsAmt());
        	indiProductTemplate.setPtBidsDiffAmt(entity.getPiIndiBidsDiffAmt());
        	indiProductTemplate.setPtMaxBidsAmt(entity.getPiIndiMaxBidsAmt());
        	indiProductTemplate.setPtMinAppBidsAmt(entity.getPiIndiMinAppBidsAmt());
        	indiProductTemplate.setPtAppBidsDiffAmt(entity.getPiIndiAppBidsDiffAmt());
        	indiProductTemplate.setPtMaxAppBidsAmt(entity.getPiIndiMaxAppBidsAmt());
        	indiProductTemplate.setPtMinRedeemVol(entity.getPiIndiMinRedeemVol());
        	indiProductTemplate.setPtRedeemDiff(entity.getPiIndiRedeemDiff());
        	indiProductTemplate.setPtMaxVol(entity.getPiIndiMaxVol());
        	indiProductTemplate.setPtMinVol(entity.getPiIndiMinVol());
        	indiProductTemplate.setPtIpoBeginDate(entity.getPiIpoBeginDate().replaceAll("-", ""));
        	indiProductTemplate.setPtIpoEndDate(entity.getPiIpoEndDate().replaceAll("-", ""));
        	indiProductTemplate.setPtProSetupDate(entity.getPiProSetupDate().replaceAll("-", ""));
        	indiProductTemplate.setPtProEndDate(entity.getPiProEndDate()==""?Const.defaultTime:entity.getPiProEndDate().replaceAll("-", ""));
        	indiProductTemplate.setPtMaxAmountRaised(entity.getPiIndiMaxAmountRaised());
        	indiProductTemplate.setPtMinAmountRaised(entity.getPiIndiMinAmountRaised());
        	indiProductTemplate.setPtChannelRate(new BigDecimal(entity.getPtChannelRate())==null?new BigDecimal("0"):new BigDecimal(entity.getPtChannelRate()));
        	indiProductTemplate.setPtPayType(entity.getPtPayType()==null?"":entity.getPtPayType());
        	indiProductTemplate.setPtProYieldType(entity.getPtProYieldType()==null?"":entity.getPtProYieldType());
        	indiProductTemplate.setPtProCalculateType(entity.getPtProCalculateType()==null?"":entity.getPtProCalculateType());
        	indiProductTemplate.setPtFeeFlag(entity.getPtFeeFlag()==null?"":entity.getPtFeeFlag());
        	indiProductTemplate.setPtIndiBuyFlag(entity.getPtIndiBuyFlag()==null?"":entity.getPtIndiBuyFlag());
        	indiProductTemplate.setPtInstBuyFlag(entity.getPtInstBuyFlag()==null?"":entity.getPtInstBuyFlag());
        	indiProductTemplate.setPtProCfmWay(entity.getPtProCfmWay()==null?"":entity.getPtProCfmWay());
        	indiProductTemplate.setPtAnnuCalDays(entity.getPtAnnuCalDays()==null?"":entity.getPtAnnuCalDays());
        	indiProductTemplate.setPtCurrency(entity.getPiCurrency()==null?"":entity.getPiCurrency());
        	indiProductTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
        	SysUser user=getCurrentUser();
        	indiProductTemplate.setPtCuser(user.getLoginName());
        	indiProductTemplate.setPtCtime(getSysDate());
        	indiProductTemplate.setPtAction(Const.BUSINESS_STUTAS_01);
        	indiProductTemplate.setPtValidFlag(Const.BUSINESS_STUTAS_10);
        	indiProductTemplate.setPtProYield(new BigDecimal(entity.getPtProYield()==null?"0":entity.getPtProYield()));
        	
        	instProductTemplate.setPtTemplateCode(ptTemplateCode);
        	instProductTemplate.setPtTemplateName(entity.getPiChannelProductName());
        	instProductTemplate.setPtProductCode(entity.getPiProductType());
        	instProductTemplate.setPtIndividualOrInstitution("1");
        	instProductTemplate.setPtMinBidsAmt(entity.getPiInstMinBidsAmt());
        	instProductTemplate.setPtBidsDiffAmt(entity.getPiInstBidsDiffAmt());
        	instProductTemplate.setPtMaxBidsAmt(entity.getPiInstMaxBidsAmt());
        	instProductTemplate.setPtMinAppBidsAmt(entity.getPiInstMinAppBidsAmt());
        	instProductTemplate.setPtAppBidsDiffAmt(entity.getPiInstAppBidsDiffAmt());
        	instProductTemplate.setPtMaxAppBidsAmt(entity.getPiInstMaxAppBidsAmt());
        	instProductTemplate.setPtMinRedeemVol(entity.getPiInstMinRedeemVol());
        	instProductTemplate.setPtRedeemDiff(entity.getPiInstRedeemDiff());
        	instProductTemplate.setPtMaxVol(entity.getPiInstMaxVol());
        	instProductTemplate.setPtMinVol(entity.getPiInstMinVol());
        	instProductTemplate.setPtChannelRate(entity.getPiInstChannelRate());
        	instProductTemplate.setPtIpoBeginDate(entity.getPiIpoBeginDate().replaceAll("-", ""));
        	instProductTemplate.setPtIpoEndDate(entity.getPiIpoEndDate().replaceAll("-", ""));
        	instProductTemplate.setPtProSetupDate(entity.getPiProSetupDate().replaceAll("-", ""));
        	instProductTemplate.setPtProEndDate(entity.getPiProEndDate()==""?Const.defaultTime:entity.getPiProEndDate().replaceAll("-", ""));
        	instProductTemplate.setPtMaxAmountRaised(entity.getPiInstMaxAmountRaised());
        	instProductTemplate.setPtMinAmountRaised(entity.getPiInstMinAmountRaised());
        	instProductTemplate.setPtPayType(entity.getPtPayType()==null?"":entity.getPtPayType());
        	instProductTemplate.setPtProYieldType(entity.getPtProYieldType()==null?"":entity.getPtProYieldType());
        	instProductTemplate.setPtProCalculateType(entity.getPtProCalculateType()==null?"":entity.getPtProCalculateType());
        	instProductTemplate.setPtFeeFlag(entity.getPtFeeFlag()==null?"":entity.getPtFeeFlag());
        	instProductTemplate.setPtIndiBuyFlag(entity.getPtIndiBuyFlag()==null?"":entity.getPtIndiBuyFlag());
        	instProductTemplate.setPtInstBuyFlag(entity.getPtInstBuyFlag()==null?"":entity.getPtInstBuyFlag());
        	instProductTemplate.setPtProCfmWay(entity.getPtProCfmWay()==null?"":entity.getPtProCfmWay());
        	instProductTemplate.setPtAnnuCalDays(entity.getPtAnnuCalDays()==null?"":entity.getPtAnnuCalDays());
        	instProductTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
        	instProductTemplate.setPtCuser(user.getLoginName());
        	instProductTemplate.setPtCtime(getSysDate());
        	instProductTemplate.setPtCurrency(entity.getPiCurrency()==null?"":entity.getPiCurrency());
        	instProductTemplate.setPtValidFlag(Const.BUSINESS_STUTAS_01);
        	instProductTemplate.setPtAction(Const.BUSINESS_STUTAS_01);
        	indiProductTemplate.setPtProYield(new BigDecimal(entity.getPtProYield()==null?"0":entity.getPtProYield()));
        	int i = productTemplateService.save(instProductTemplate);
        	i = productTemplateService.save(indiProductTemplate);
        	if(i==1){
        		msg= "添加成功！";
        	}else{
        		msg= "添加失败！";
        	}
    	    return resultSuccess(msg); 
    }
	
	/**
	 * 模板信息修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Model model, String ptId) {
		Long intPiId =Long.parseLong(ptId);
		ProductTemplate product = productTemplateService.selectByKey(intPiId);
		ParTemplateResult entity = (ParTemplateResult)getParTemplateResult(product.getPtTemplateCode());
	    model.addAttribute("entity",entity);
		return "sys/bizParameterManage/productTemplate/productTemplateEdit";
	}
	
	
	/**
	 * 模板信息修改确认
	 * 
	 * @return
	 */
    @RequestMapping("/editSubmit")
    @ResponseBody
    public Object editSubmit(ParTemplateResult entity) {
    	ProductTemplate instProductTemplate = new ProductTemplate();
    	ProductTemplate indiProductTemplate = new ProductTemplate();
    	indiProductTemplate.setPtId(entity.getPiIndiId());
    	indiProductTemplate.setPtTemplateCode(entity.getPtTemplateCode().toString());
    	indiProductTemplate.setPtTemplateName(entity.getPiChannelProductName());
    	indiProductTemplate.setPtProductCode(entity.getPiProductType());
    	indiProductTemplate.setPtIndividualOrInstitution("0");
    	indiProductTemplate.setPtMinBidsAmt(entity.getPiIndiMinBidsAmt());
    	indiProductTemplate.setPtBidsDiffAmt(entity.getPiIndiBidsDiffAmt());
    	indiProductTemplate.setPtMaxBidsAmt(entity.getPiIndiMaxBidsAmt());
    	indiProductTemplate.setPtMinAppBidsAmt(entity.getPiIndiMinAppBidsAmt());
    	indiProductTemplate.setPtAppBidsDiffAmt(entity.getPiIndiAppBidsDiffAmt());
    	indiProductTemplate.setPtMaxAppBidsAmt(entity.getPiIndiMaxAppBidsAmt());
    	indiProductTemplate.setPtMinRedeemVol(entity.getPiIndiMinRedeemVol());
    	indiProductTemplate.setPtRedeemDiff(entity.getPiIndiRedeemDiff());
    	indiProductTemplate.setPtMaxVol(entity.getPiIndiMaxVol());
    	indiProductTemplate.setPtMinVol(entity.getPiIndiMinVol());
    	indiProductTemplate.setPtIpoBeginDate(entity.getPiIpoBeginDate().replaceAll("-", ""));
    	indiProductTemplate.setPtIpoEndDate(entity.getPiIpoEndDate().replaceAll("-", ""));
    	indiProductTemplate.setPtProSetupDate(entity.getPiProSetupDate().replaceAll("-", ""));
    	indiProductTemplate.setPtProEndDate(entity.getPiProEndDate()==""?Const.defaultTime:entity.getPiProEndDate().replaceAll("-", ""));
    	indiProductTemplate.setPtMaxAmountRaised(entity.getPiIndiMaxAmountRaised());
    	indiProductTemplate.setPtMinAmountRaised(entity.getPiIndiMinAmountRaised());
    	indiProductTemplate.setPtChannelRate(new BigDecimal(entity.getPtChannelRate())==null?new BigDecimal("0"):new BigDecimal(entity.getPtChannelRate()));
    	indiProductTemplate.setPtPayType(entity.getPtPayType()==null?"":entity.getPtPayType());
    	indiProductTemplate.setPtProYieldType(entity.getPtProYieldType()==null?"":entity.getPtProYieldType());
    	indiProductTemplate.setPtFeeFlag(entity.getPtFeeFlag()==null?"":entity.getPtFeeFlag());
    	indiProductTemplate.setPtIndiBuyFlag(entity.getPtIndiBuyFlag()==null?"":entity.getPtIndiBuyFlag());
    	indiProductTemplate.setPtInstBuyFlag(entity.getPtInstBuyFlag()==null?"":entity.getPtInstBuyFlag());
    	indiProductTemplate.setPtProCfmWay(entity.getPtProCfmWay()==null?"":entity.getPtProCfmWay());
    	indiProductTemplate.setPtAnnuCalDays(entity.getPtAnnuCalDays()==null?"":entity.getPtAnnuCalDays());
    	indiProductTemplate.setPtCurrency(entity.getPiCurrency()==null?"":entity.getPiCurrency());
    	indiProductTemplate.setPtValidFlag(entity.getPtValidFlag()==null?"":entity.getPtValidFlag());
    	indiProductTemplate.setPtProCalculateType(entity.getPtProCalculateType());
    	SysUser user=getCurrentUser();
    	indiProductTemplate.setPtCuser(user.getLoginName());
    	indiProductTemplate.setPtCtime(getSysDate());
    	indiProductTemplate.setPtAction(Const.BUSINESS_STUTAS_02);
    	indiProductTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
    	indiProductTemplate.setPtValidFlag(entity.getPiValidFlag());
    	indiProductTemplate.setPtCtime(getSysDate());
    	indiProductTemplate.setPtCuser(user.getLoginName());
    	indiProductTemplate.setPtUtime(null);
    	indiProductTemplate.setPtUuser(null);
    	
    	instProductTemplate.setPtId(entity.getPiInstId());
    	instProductTemplate.setPtTemplateCode(entity.getPtTemplateCode().toString());
    	instProductTemplate.setPtTemplateName(entity.getPiChannelProductName());
    	instProductTemplate.setPtProductCode(entity.getPiProductType());
    	instProductTemplate.setPtIndividualOrInstitution("1");
    	instProductTemplate.setPtMinBidsAmt(entity.getPiInstMinBidsAmt());
    	instProductTemplate.setPtBidsDiffAmt(entity.getPiInstBidsDiffAmt());
    	instProductTemplate.setPtMaxBidsAmt(entity.getPiInstMaxBidsAmt());
    	instProductTemplate.setPtMinAppBidsAmt(entity.getPiInstMinAppBidsAmt());
    	instProductTemplate.setPtAppBidsDiffAmt(entity.getPiInstAppBidsDiffAmt());
    	instProductTemplate.setPtMaxAppBidsAmt(entity.getPiInstMaxAppBidsAmt());
    	instProductTemplate.setPtMinRedeemVol(entity.getPiInstMinRedeemVol());
    	instProductTemplate.setPtRedeemDiff(entity.getPiInstRedeemDiff());
    	instProductTemplate.setPtMaxVol(entity.getPiInstMaxVol());
    	instProductTemplate.setPtMinVol(entity.getPiInstMinVol());
    	instProductTemplate.setPtChannelRate(entity.getPiInstChannelRate());
    	instProductTemplate.setPtIpoBeginDate(entity.getPiIpoBeginDate().replaceAll("-", ""));
    	instProductTemplate.setPtIpoEndDate(entity.getPiIpoEndDate().replaceAll("-", ""));
    	instProductTemplate.setPtProSetupDate(entity.getPiProSetupDate().replaceAll("-", ""));
    	instProductTemplate.setPtProEndDate(entity.getPiProEndDate()==""?Const.defaultTime:entity.getPiProEndDate().replaceAll("-", ""));
    	instProductTemplate.setPtMaxAmountRaised(entity.getPiInstMaxAmountRaised());
    	instProductTemplate.setPtMinAmountRaised(entity.getPiInstMinAmountRaised());
    	instProductTemplate.setPtPayType(entity.getPtPayType()==null?"":entity.getPtPayType());
    	instProductTemplate.setPtProYieldType(entity.getPtProYieldType()==null?"":entity.getPtProYieldType());
    	instProductTemplate.setPtFeeFlag(entity.getPtFeeFlag()==null?"":entity.getPtFeeFlag());
    	instProductTemplate.setPtIndiBuyFlag(entity.getPtIndiBuyFlag()==null?"":entity.getPtIndiBuyFlag());
    	instProductTemplate.setPtInstBuyFlag(entity.getPtInstBuyFlag()==null?"":entity.getPtInstBuyFlag());
    	instProductTemplate.setPtProCfmWay(entity.getPtProCfmWay()==null?"":entity.getPtProCfmWay());
    	instProductTemplate.setPtAnnuCalDays(entity.getPtAnnuCalDays()==null?"":entity.getPtAnnuCalDays());
    	instProductTemplate.setPtCuser(user.getLoginName());
    	instProductTemplate.setPtCtime(getSysDate());
    	instProductTemplate.setPtCurrency(entity.getPiCurrency()==null?"":entity.getPiCurrency());
    	instProductTemplate.setPtValidFlag(entity.getPtValidFlag()==null?"":entity.getPtValidFlag());
    	instProductTemplate.setPtProCalculateType(entity.getPtProCalculateType());
    	instProductTemplate.setPtAction(Const.BUSINESS_STUTAS_02);
    	instProductTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
    	instProductTemplate.setPtValidFlag(entity.getPiValidFlag());
    	instProductTemplate.setPtCtime(getSysDate());
    	instProductTemplate.setPtCuser(user.getLoginName());
    	instProductTemplate.setPtUtime(null);
    	instProductTemplate.setPtUuser(null);
    	int i=0;
				
    	i = productTemplateService.updateAll(instProductTemplate);
    	i = productTemplateService.updateAll(indiProductTemplate);
    	String msg = "";
    	if(i==1){
    		msg= "修改成功！";
    	}else{
    		msg= "修改失败！";
    	}
    	return resultSuccess(msg); 
    }
	/**
	 * 模板信息查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(Model model, String ptId) {
		Long intPiId =Long.parseLong(ptId);
		ProductTemplate product = productTemplateService.selectByKey(intPiId);
		List<ProductTemplate> list = productTemplateService.findByTemplateCode(product.getPtTemplateCode());
		ParTemplateResult entity = new ParTemplateResult();
		for(ProductTemplate p:list){
			if("0".equals(p.getPtIndividualOrInstitution())){
				entity.setPiIndiId(p.getPtId());
				entity.setPiProductType(p.getPtProductCode());
				entity.setPiChannelProductName(p.getPtTemplateName());
				entity.setPiChannelProductCode(p.getPtId().toString());
				entity.setPiIndiMinBidsAmt(p.getPtMinBidsAmt());
				entity.setPiIndiBidsDiffAmt(p.getPtBidsDiffAmt());
				entity.setPiIndiMaxBidsAmt(p.getPtMaxBidsAmt());
				entity.setPiIndiMinAppBidsAmt(p.getPtMinAppBidsAmt());
				entity.setPiIndiAppBidsDiffAmt(p.getPtAppBidsDiffAmt());
				entity.setPiIndiMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiIndiMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiIndiMinRedeemVol(p.getPtMinRedeemVol());
				entity.setPiIndiRedeemDiff(p.getPtRedeemDiff());
				entity.setPiIndiMaxVol(p.getPtMaxVol());
				entity.setPiIndiMinVol(p.getPtMinVol());
				entity.setPiIndiChannelRate(p.getPtChannelRate());
				entity.setPtPayType(p.getPtPayType());
				entity.setPiIpoBeginDate(p.getPtIpoBeginDate().substring(0,4)+"-"+p.getPtIpoBeginDate().substring(4,6)+"-"+p.getPtIpoBeginDate().substring(6,8));
				entity.setPiIpoEndDate(p.getPtIpoEndDate().substring(0,4)+"-"+p.getPtIpoEndDate().substring(4,6)+"-"+p.getPtIpoEndDate().substring(6,8));
				entity.setPiProSetupDate(p.getPtProSetupDate().substring(0,4)+"-"+p.getPtProSetupDate().substring(4,6)+"-"+p.getPtProSetupDate().substring(6,8));
				entity.setPiProEndDate(p.getPtProEndDate()==null?"":p.getPtProEndDate().substring(0,4)+"-"+p.getPtProEndDate().substring(4,6)+"-"+p.getPtProEndDate().substring(6,8));
				entity.setPtProYieldType(p.getPtProYieldType());
				entity.setPtFeeFlag(p.getPtFeeFlag());
				entity.setPtProCfmWay(p.getPtProCfmWay());
				entity.setPtAnnuCalDays(p.getPtAnnuCalDays());
				entity.setPiIndiMaxAmountRaised(p.getPtMaxAmountRaised());
				entity.setPiIndiMinAmountRaised(p.getPtMinAmountRaised());
				entity.setPtIndiBuyFlag(p.getPtIndiBuyFlag());
				entity.setPtInstBuyFlag(p.getPtInstBuyFlag());
				entity.setPtChannelRate(p.getPtChannelRate().toString());
				entity.setPiCurrency(p.getPtCurrency());
				entity.setPtProCalculateType(p.getPtProCalculateType());
			}else if("1".equals(p.getPtIndividualOrInstitution())){
				entity.setPiInstId(p.getPtId());
				entity.setPiInstMinBidsAmt(p.getPtMinBidsAmt());
				entity.setPiInstBidsDiffAmt(p.getPtBidsDiffAmt());
				entity.setPiInstMaxBidsAmt(p.getPtMaxBidsAmt());
				entity.setPiInstMinAppBidsAmt(p.getPtMinAppBidsAmt());
				entity.setPiInstAppBidsDiffAmt(p.getPtAppBidsDiffAmt());
				entity.setPiInstMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiInstMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiInstMinRedeemVol(p.getPtMinRedeemVol());
				entity.setPiInstRedeemDiff(p.getPtRedeemDiff());
				entity.setPiInstMaxVol(p.getPtMaxVol());
				entity.setPiInstMinVol(p.getPtMinVol());
				entity.setPiInstChannelRate(p.getPtChannelRate());
				entity.setPiInstMaxAmountRaised(p.getPtMaxAmountRaised());
				entity.setPiInstMinAmountRaised(p.getPtMinAmountRaised());
				entity.setPiValidFlag(p.getPtValidFlag());
				entity.setPiCheckState(p.getPtCheckState());
				entity.setPiCtime(p.getPtCtime());
				entity.setPiCuser(p.getPtCuser());
			}
			model.addAttribute("entity",entity);
		}
		return "sys/bizParameterManage/productTemplate/productTemplateView";
	}
	
	/**
	 * 模板信息删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST )
	@ResponseBody
	public Object delete(@Param("ptTemplateCodes")String ptTemplateCodes) {
		String codes = (ptTemplateCodes.replaceAll("&quot;", ""));
		String ptTemplateCodesa = codes.substring(1, codes.length()-1);
		String[] ptTemplateCodess = ptTemplateCodesa.split(",");
		String msg = "";
		SysUser user=getCurrentUser();
		for(int i=0;i<ptTemplateCodess.length;i++){
			List<ProductTemplate> list = productTemplateService.findByProductCode(ptTemplateCodess[i]);
			for(ProductTemplate productTemplate:list){
				Map map =Maps.newHashMap();
				map.put("piTemplateCode", productTemplate.getPtTemplateCode());
				//查询使用模板的产品个数
				Long productCount = parProductInfoService.selectByConditionCount(map);
				if(productCount!=0){
					msg="已有产品使用该模板，不允许删除";
					return resultSuccess(msg); 
				}
				productTemplate.setPtValidFlag(Const.BUSINESS_STUTAS_00);
				productTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
				productTemplate.setPtAction(Const.BUSINESS_STUTAS_00);
				productTemplate.setPtCuser(user.getLoginName());
				productTemplate.setPtCtime(getSysDate());
				productTemplate.setPtUuser(null);
				productTemplate.setPtUtime(null);
				int count = productTemplateService.deleteByTemplateCode(productTemplate);
				if(count==1){
					msg="删除成功";
				}else{
					msg="删除失败";
				}
			}
			
		}
		return resultSuccess(msg); 
	}
	
	/**
	 * 模板信息启用 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/startSubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object startSubmit(@Param("ptTemplateCodes")String ptTemplateCodes) {
		String codes = (ptTemplateCodes.replaceAll("&quot;", ""));
		String ptTemplateCodesa = codes.substring(1, codes.length()-1);
		String[] ptTemplateCodess = ptTemplateCodesa.split(",");
		String msg = "";
		SysUser user=getCurrentUser();
		for(int i=0;i<ptTemplateCodess.length;i++){
			List<ProductTemplate> list = productTemplateService.findByProductCode(ptTemplateCodess[i]);
			for(ProductTemplate productTemplate:list){
				productTemplate.setPtValidFlag(Const.BUSINESS_STUTAS_01);
				productTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
				productTemplate.setPtAction(Const.BUSINESS_STUTAS_05);
				productTemplate.setPtCuser(user.getLoginName());
				productTemplate.setPtCtime(getSysDate());
				productTemplate.setPtUuser(null);
				productTemplate.setPtUtime(null);
				int count = productTemplateService.deleteByTemplateCode(productTemplate);
				if(count==1){
					msg="启用成功";
				}else{
					msg="启用失败";
				}
			}
			
		}
		return resultSuccess(msg); 
	}
	
	/**
	 * 模板信息停用 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stopSubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object stopSubmit(@Param("ptTemplateCodes")String ptTemplateCodes) {
		String codes = (ptTemplateCodes.replaceAll("&quot;", ""));
		String ptTemplateCodesa = codes.substring(1, codes.length()-1);
		String[] ptTemplateCodess = ptTemplateCodesa.split(",");
		String msg = "";
		SysUser user=getCurrentUser();
		for(int i=0;i<ptTemplateCodess.length;i++){
			List<ProductTemplate> list = productTemplateService.findByProductCode(ptTemplateCodess[i]);
			for(ProductTemplate productTemplate:list){
				productTemplate.setPtValidFlag(Const.BUSINESS_STUTAS_10);
				productTemplate.setPtCheckState(Const.BUSINESS_STUTAS_00);
				productTemplate.setPtAction(Const.BUSINESS_STUTAS_04);
				productTemplate.setPtCuser(user.getLoginName());
				productTemplate.setPtCtime(getSysDate());
				productTemplate.setPtUuser(null);
				productTemplate.setPtUtime(null);
				int count = productTemplateService.deleteByTemplateCode(productTemplate);
				if(count==1){
					msg="停用成功";
				}else{
					msg="停用失败";
				}
			}
			
		}
		return resultSuccess(msg); 
	}
	
	/**
	 * 模板信息复核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object checkSubmit(@Param("ptTemplateCodes")String ptTemplateCodes) {
		String codes = (ptTemplateCodes.replaceAll("&quot;", ""));
		String ptTemplateCodesa = codes.substring(1, codes.length()-1);
		String[] ptTemplateCodess = ptTemplateCodesa.split(",");
		String msg = "";
		SysUser user=getCurrentUser();
		for(int i=0;i<ptTemplateCodess.length;i++){
			List<ProductTemplate> list = productTemplateService.findByProductCode(ptTemplateCodess[i]);
			for(ProductTemplate productTemplate:list){
				productTemplate.setPtCheckState(Const.BUSINESS_STUTAS_01);
				productTemplate.setPtUuser(user.getLoginName());
				productTemplate.setPtUtime(getSysDate());
				int count = productTemplateService.deleteByTemplateCode(productTemplate);
				if(count==1){
					msg="复核成功";
				}else{
					msg="复核失败";
				}
			}
			
		}
		return resultSuccess(msg);
	}
	
	/**
	 * 通过模板编号封装一个ParTemplateResult类
	 * 
	 * @return ParTemplateResult
	 */
	@RequestMapping(value = "/getParTemplateResult",method = RequestMethod.POST )
	@ResponseBody
	public Object getParTemplateResult(String ptTemplateCode){
		List<ProductTemplate> list = productTemplateService.findByTemplateCode(ptTemplateCode);
		ParTemplateResult entity = new ParTemplateResult();
		for(ProductTemplate p:list){
			if("0".equals(p.getPtIndividualOrInstitution())){
				entity.setPiIndiId(p.getPtId());
				entity.setPtTemplateCode(ptTemplateCode);
				entity.setPiProductType(p.getPtProductCode());
				entity.setPiChannelProductName(p.getPtTemplateName());
				entity.setPiChannelProductCode(p.getPtId().toString());
				entity.setPiIndiMinBidsAmt(p.getPtMinBidsAmt());
				entity.setPiIndiBidsDiffAmt(p.getPtBidsDiffAmt());
				entity.setPiIndiMaxBidsAmt(p.getPtMaxBidsAmt());
				entity.setPiIndiMinAppBidsAmt(p.getPtMinAppBidsAmt());
				entity.setPiIndiAppBidsDiffAmt(p.getPtAppBidsDiffAmt());
				entity.setPiIndiMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiIndiMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiIndiMinRedeemVol(p.getPtMinRedeemVol());
				entity.setPiIndiRedeemDiff(p.getPtRedeemDiff());
				entity.setPiIndiMaxVol(p.getPtMaxVol());
				entity.setPiIndiMinVol(p.getPtMinVol());
				entity.setPiIndiChannelRate(p.getPtChannelRate());
				entity.setPtPayType(p.getPtPayType());
				entity.setPiIpoBeginDate(p.getPtIpoBeginDate().substring(0,4)+"-"+p.getPtIpoBeginDate().substring(4,6)+"-"+p.getPtIpoBeginDate().substring(6,8));
				entity.setPiIpoEndDate(p.getPtIpoEndDate().substring(0,4)+"-"+p.getPtIpoEndDate().substring(4,6)+"-"+p.getPtIpoEndDate().substring(6,8));
				entity.setPiProSetupDate(p.getPtProSetupDate().substring(0,4)+"-"+p.getPtProSetupDate().substring(4,6)+"-"+p.getPtProSetupDate().substring(6,8));
				entity.setPiProEndDate(p.getPtProEndDate()==null?"":p.getPtProEndDate().substring(0,4)+"-"+p.getPtProEndDate().substring(4,6)+"-"+p.getPtProEndDate().substring(6,8));
				entity.setPtProYieldType(p.getPtProYieldType());
				entity.setPtFeeFlag(p.getPtFeeFlag());
				entity.setPtProCfmWay(p.getPtProCfmWay());
				entity.setPtAnnuCalDays(p.getPtAnnuCalDays());
				entity.setPiIndiMaxAmountRaised(p.getPtMaxAmountRaised());
				entity.setPiIndiMinAmountRaised(p.getPtMinAmountRaised());
				entity.setPtIndiBuyFlag(p.getPtIndiBuyFlag());
				entity.setPtInstBuyFlag(p.getPtInstBuyFlag());
				entity.setPtChannelRate(p.getPtChannelRate().toString());
				entity.setPiCurrency(p.getPtCurrency());
				entity.setPtProCalculateType(p.getPtProCalculateType());
				entity.setPtProYield(p.getPtProYield()==null?"":p.getPtProYield().toString());
			}else if("1".equals(p.getPtIndividualOrInstitution())){
				entity.setPiInstId(p.getPtId());
				entity.setPiInstMinBidsAmt(p.getPtMinBidsAmt());
				entity.setPiInstBidsDiffAmt(p.getPtBidsDiffAmt());
				entity.setPiInstMaxBidsAmt(p.getPtMaxBidsAmt());
				entity.setPiInstMinAppBidsAmt(p.getPtMinAppBidsAmt());
				entity.setPiInstAppBidsDiffAmt(p.getPtAppBidsDiffAmt());
				entity.setPiInstMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiInstMaxAppBidsAmt(p.getPtMaxAppBidsAmt());
				entity.setPiInstMinRedeemVol(p.getPtMinRedeemVol());
				entity.setPiInstRedeemDiff(p.getPtRedeemDiff());
				entity.setPiInstMaxVol(p.getPtMaxVol());
				entity.setPiInstMinVol(p.getPtMinVol());
				entity.setPiInstChannelRate(p.getPtChannelRate());
				entity.setPiInstMaxAmountRaised(p.getPtMaxAmountRaised());
				entity.setPiInstMinAmountRaised(p.getPtMinAmountRaised());
				entity.setPiValidFlag(p.getPtValidFlag());
				entity.setPiCheckState(p.getPtCheckState());
				entity.setPiCtime(p.getPtCtime());
				entity.setPiCuser(p.getPtCuser());
			}
		}
		return entity;
   }
}
