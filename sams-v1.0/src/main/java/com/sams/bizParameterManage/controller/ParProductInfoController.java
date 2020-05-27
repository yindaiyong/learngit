package com.sams.bizParameterManage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.sams.common.exception.ExceptionConStants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.FundQuotTmpService;
import com.sams.batchfile.service.ParProductInfoService;
import com.sams.batchfile.service.ProductTemplateService;
import com.sams.common.constant.Const;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.MyMapUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductTemplate;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.ParTemplateResult;
import com.sams.sys.controller.SysDictController;
import com.sams.sys.model.SysUser;
import com.sams.sys.model.result.SysDictVo;
import com.sams.sys.service.SysDictService;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/parProductInfo")
public class ParProductInfoController extends BaseController{

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private ParProductInfoService parProductInfoService;

	@Autowired
	public SysDictService  sysDictService;

	@Autowired
	private ProductTemplateService productTemplateService;

	@Autowired
	private ChannelInfoService channelInfoService;

	/**
	 * 产品信息模板页面
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/parProductInfo/parProductInfoList";
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
	@RequestMapping(value = "/getParProductInfoData")
	@ResponseBody
	public PageInfo getParProductInfoData(Integer page, Integer rows,
										  String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(parProductInfoService.findDataGrid(pageInfo,
				condition));
		return pageInfo;
	}

	/**
	 * 代销端产品信息新增
	 *
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/parProductInfo/parProductInfoAdd";
	}

	/**
	 * 代销端产品信息修改
	 *
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Model model, String piId) {
		Long intPiId =Long.parseLong(piId);
		PProductInfo entity = parProductInfoService.selectByKey(intPiId);
		entity.setPiIpoBeginDate(entity.getPiIpoBeginDate().substring(0,4)+"-"+entity.getPiIpoBeginDate().substring(4,6)+"-"+entity.getPiIpoBeginDate().substring(6,8));
		entity.setPiIpoEndDate(entity.getPiIpoEndDate().substring(0,4)+"-"+entity.getPiIpoEndDate().substring(4,6)+"-"+entity.getPiIpoEndDate().substring(6,8));
		entity.setPiProSetupDate(entity.getPiProSetupDate().substring(0,4)+"-"+entity.getPiProSetupDate().substring(4,6)+"-"+entity.getPiProSetupDate().substring(6,8));
		entity.setPiProEndDate(entity.getPiProEndDate()==null?"":entity.getPiProEndDate().substring(0,4)+"-"+entity.getPiProEndDate().substring(4,6)+"-"+entity.getPiProEndDate().substring(6,8));
		model.addAttribute("entity",entity);

		model.addAttribute("subChannelInfo",showSubChannelCode(entity));
		return "sys/bizParameterManage/parProductInfo/parProductInfoEdit";
	}

	/**
	 * @Description 子渠道反显公共调用方法
	 * @Author weijunjie
	 * @Date 2020/1/16 10:02
	 **/
	public JSONObject showSubChannelCode(PProductInfo entity){
	    //获取当前渠道是否有子渠道数据
        List<Map<String, Object>> subChannelList = channelInfoService.selectSubChannelList(entity.getPiChannelCode());
        JSONObject jsonObject = new JSONObject();
        String subChannelCode = entity.getPiSubChannelCode();
        String channelCode = entity.getPiChannelCode();
        if(subChannelList.size()>0){
            //有子渠道数据
            if(StringUtils.isBlank(subChannelCode)){
                jsonObject.put("subChannelCode",null);
                jsonObject.put("channelCode",channelCode);
            }else{
                jsonObject.put("subChannelCode",subChannelCode);
                jsonObject.put("channelCode",channelCode);
            }
            return jsonObject;
        }else{
            return null;
        }


        /*JSONArray jsonArray = new JSONArray();
        try {
            //获取所有支持的子渠道
            List<Map<String, Object>> subChannelList = channelInfoService.selectSubChannelList(entity.getPiChannelCode());
            JSONArray allSubList = JSONArray.parseArray(JSONObject.toJSONString(subChannelList));
            //解析json数据文件

            JSONObject jsonObj1 = new JSONObject();
            if(StringUtils.isNotBlank(subChannelCode)){
                JSONObject jsonObject = JSONObject.parseObject(subChannelCode);
                for(String key:jsonObject.keySet()){
                    String[] values = jsonObject.getString(key).split("_");
                    jsonObj1.put(values[0],values[1]);
                }
            }else{
                jsonObj1.put("null","null");
            }

            for(Object obj :allSubList){
                JSONObject jsonObj = JSONObject.parseObject(JSONObject.toJSONString(obj));
                String key = jsonObj.getString("AGENCYNO");
                if(jsonObj1.keySet().contains(key)){
                    String val = jsonObj1.getString(key);
                    jsonObj.put("checkBoxChecked","1");
                    jsonObj.put("jkCode",val);
                    jsonObj.put("AGENCYNO",key+"_"+val);
                }else{
                    jsonObj.put("checkBoxChecked","0");
                    jsonObj.put("jkCode","0");
                }

                jsonArray.add(jsonObj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;*/
	}

	/**
	 * 代销端产品信息复制
	 *
	 * @return
	 */
	@RequestMapping(value = "/copyPage", method = RequestMethod.GET)
	public String copyPage(Model model, String piId) {
		Long intPiId =Long.parseLong(piId);
		PProductInfo entity = parProductInfoService.selectByKey(intPiId);
		entity.setPiIpoBeginDate(entity.getPiIpoBeginDate().substring(0,4)+"-"+entity.getPiIpoBeginDate().substring(4,6)+"-"+entity.getPiIpoBeginDate().substring(6,8));
		entity.setPiIpoEndDate(entity.getPiIpoEndDate().substring(0,4)+"-"+entity.getPiIpoEndDate().substring(4,6)+"-"+entity.getPiIpoEndDate().substring(6,8));
		entity.setPiProSetupDate(entity.getPiProSetupDate().substring(0,4)+"-"+entity.getPiProSetupDate().substring(4,6)+"-"+entity.getPiProSetupDate().substring(6,8));
		entity.setPiProEndDate(entity.getPiProEndDate()==null?"":entity.getPiProEndDate().substring(0,4)+"-"+entity.getPiProEndDate().substring(4,6)+"-"+entity.getPiProEndDate().substring(6,8));
		//处理产品勾选子渠道显示
		model.addAttribute("subChannelInfo",showSubChannelCode(entity));
		model.addAttribute("entity",entity);
		return "sys/bizParameterManage/parProductInfo/parProductInfoCopy";
	}

	/**
	 * 代销端产品信息查看
	 *
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(Model model, String piId) {
		Long intPiId =Long.parseLong(piId);
		PProductInfo product = parProductInfoService.selectByKey(intPiId);
		product.setPiIpoBeginDate(product.getPiIpoBeginDate().substring(0,4)+"-"+product.getPiIpoBeginDate().substring(4,6)+"-"+product.getPiIpoBeginDate().substring(6,8));
		product.setPiIpoEndDate(product.getPiIpoEndDate().substring(0,4)+"-"+product.getPiIpoEndDate().substring(4,6)+"-"+product.getPiIpoEndDate().substring(6,8));
		product.setPiProSetupDate(product.getPiProSetupDate().substring(0,4)+"-"+product.getPiProSetupDate().substring(4,6)+"-"+product.getPiProSetupDate().substring(6,8));
		product.setPiProEndDate(product.getPiProEndDate()==null?"":product.getPiProEndDate().substring(0,4)+"-"+product.getPiProEndDate().substring(4,6)+"-"+product.getPiProEndDate().substring(6,8));
		//处理产品勾选子渠道显示
		model.addAttribute("subChannelInfo",showSubChannelCode(product));
		model.addAttribute("entity",product);
		return "sys/bizParameterManage/parProductInfo/parProductInfoView";
	}


	/**
	 * 模板信息新增
	 *
	 * @return
	 */
	@RequestMapping("/addSubmit")
	@ResponseBody
	public Object addSubmit(PProductInfo entity) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("piChannelProductCode", entity.getPiChannelProductCode());
		String batchNumber = entity.getPiBatchNumber();
		String channelCode = entity.getPiChannelCode();
		String productCode = entity.getPiChannelProductCode();
		if(StringUtils.isEmpty(batchNumber)){
			batchNumber = "1";
			entity.setPiBatchNumber(batchNumber);
		}
		map.put("piBatchNumber", batchNumber);
		map.put("CHANNELCODE", channelCode);
		String msg = "";
		Long count = parProductInfoService.selectByConditionCount(map);
		if(count>0){
			msg = "该渠道此批次的产品代码已存在";
			return resultSuccess(msg);
		}
		List<ProductTemplate> list = productTemplateService.findByProductCode(entity.getPiProductType());
		if(list==null||list.size()==0){
			msg = "该模板不存在";
			return resultSuccess(msg);
		}
		for(ProductTemplate p:list){
			if("0".equals(p.getPtIndividualOrInstitution())){
				entity.setPiTemplateCode(p.getPtTemplateCode());
				entity.setPiProductType(p.getPtProductCode());
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
				entity.setPiChannelRate(p.getPtChannelRate());
				entity.setPiPayType(p.getPtPayType());
				entity.setPiCurrencyType(p.getPtCurrency());
				entity.setPiIpoBeginDate(p.getPtIpoBeginDate());
				entity.setPiIpoEndDate(p.getPtIpoEndDate());
				entity.setPiProSetupDate(p.getPtProSetupDate());
				entity.setPiProEndDate(p.getPtProEndDate());
				entity.setPiProYieldType(p.getPtProYieldType());
				entity.setPiFeeFlag(p.getPtFeeFlag());
				entity.setPiProCfmWay(p.getPtProCfmWay());
				entity.setPiAnnuCalDays(p.getPtAnnuCalDays());
				entity.setPiMinIndiAmountRaised(p.getPtMinAmountRaised());
				entity.setPiMaxIndiAmountRaised(p.getPtMaxAmountRaised());
				entity.setPiIndiBuyFlag(p.getPtIndiBuyFlag());
				entity.setPiInstBuyFlag(p.getPtInstBuyFlag());
				entity.setPiChannelRate(new BigDecimal(p.getPtChannelRate().toString()));
				entity.setPiProCalculateType(p.getPtProCalculateType());
				SysUser user=getCurrentUser();
				entity.setPiCheckState(Const.BUSINESS_STUTAS_00);
				entity.setPiValidFlag(Const.BUSINESS_STUTAS_01);
				entity.setPiAction(Const.BUSINESS_STUTAS_01);
				entity.setPiCuser(user.getLoginName());
				entity.setPiCtime(getSysDate());
			}else if("1".equals(p.getPtIndividualOrInstitution())){
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
				entity.setPiMinInstAmountRaised(p.getPtMinAmountRaised());
				entity.setPiMaxInstAmountRaised(p.getPtMaxAmountRaised());
			}
		}

		if(null !=entity.getPiSubChannelCode() && ""!=entity.getPiSubChannelCode() ){
			//处理产品的子渠道
			String subChannelCodeRes = makeSunChannelCode(entity);
			if(!ExceptionConStants.retCode_0000.equals(subChannelCodeRes)){
				return resultSuccess(subChannelCodeRes);
			}
		}

		int i = parProductInfoService.save(entity);
		if(i==1){
			msg= "添加成功！";
		}else{
			msg= "添加失败！";
			return resultSuccess(msg,null);
		}
		Map<String,Object> qryMap = Maps.newHashMap();
		qryMap.put("productCode", productCode);
		qryMap.put("batchNumber", batchNumber);
		qryMap.put("channelCode", channelCode);
		qryMap.put("checkState", "00");
		PProductInfo productInfo = parProductInfoService.selectProductList(qryMap);
		String piId = productInfo.getPiId().toString();
		return resultSuccess(msg,piId);
	}

	/**
	 * @Description 处理子渠道数据公共方法
	 * @Author weijunjie
	 * @Date 2020/1/16 9:45
	 **/
	public String makeSunChannelCode(PProductInfo entity){
		//20200226 修改 不去验证子渠道有效性  拿到数据直接存入数据库
		String msg = ExceptionConStants.retCode_0000;
		return msg;
	}


	/**
	 * 代销端产品信息修改确认
	 *
	 * @return
	 */
	@RequestMapping("/editSubmit")
	@ResponseBody
	public Object editSubmit(PProductInfo entity) {
		String msg = "";
		String batchNumber = entity.getPiBatchNumber();
		String channelCode = entity.getPiChannelCode();
		if(StringUtils.isEmpty(batchNumber)){
			batchNumber = "1";
			entity.setPiBatchNumber(batchNumber);
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("piChannelProductCode", entity.getPiChannelProductCode());
		map.put("piBatchNumber", batchNumber);
		map.put("CHANNELCODE", channelCode);
		map.put("id", entity.getPiId());
		Long sum = parProductInfoService.selectByConditionCount(map);
		if(sum>0){
			msg = "该渠道此批次的产品代码已存在";
			return resultSuccess(msg);
		}
		int count = sysDictService.findDictCount("fundType", entity.getPiProductType());
		if(count!=1){
			msg="产品类型不存在";
			return resultSuccess(msg);
		}
		int ptPayTypeCount = sysDictService.findDictCount("payType", entity.getPiPayType());
		int ptCurrencyCount = sysDictService.findDictCount("currency", entity.getPiCurrencyType());
		if(ptCurrencyCount!=1){
			msg="支付币种不存在";
			return resultSuccess(msg);
		}
		PProductInfo product = parProductInfoService.selectByKey(entity.getPiId());
		entity.setPiIpoBeginDate(entity.getPiIpoBeginDate().replaceAll("-", ""));
		entity.setPiIpoEndDate(entity.getPiIpoEndDate().replaceAll("-", ""));
		entity.setPiProSetupDate(entity.getPiProSetupDate().replaceAll("-", ""));
		entity.setPiProEndDate(entity.getPiProEndDate()==null?"":entity.getPiProEndDate().replaceAll("-", ""));
		SysUser user=getCurrentUser();
		entity.setPiAction(Const.BUSINESS_STUTAS_02);
		entity.setPiCheckState(Const.BUSINESS_STUTAS_00);
		entity.setPiCuser(user.getLoginName());
		entity.setPiCtime(getSysDate());
		entity.setPiUuser(null);
		entity.setPiUtime(null);
		/*if(null !=entity.getPiSubChannelCode() && ""!=entity.getPiSubChannelCode() ){
			//处理产品的子渠道
			String subChannelCodeRes = makeSunChannelCode(entity);
			if(!ExceptionConStants.retCode_0000.equals(subChannelCodeRes)){
				return resultSuccess(subChannelCodeRes);
			}
		}*/
		int num = parProductInfoService.updateAll(entity);
		if(num==1){
			//修改关联关系批次号
			if(!product.getPiBatchNumber().equals(entity.getPiBatchNumber())){
				parProductInfoService.updateConnetBatchNo(entity.getPiChannelProductCode(),product.getPiBatchNumber(),entity.getPiBatchNumber());
			}
			msg="修改成功";
		}else{
			msg="修改失败";
		}
		return resultSuccess(msg);
	}


	/**
	 * 产品信息删除
	 *
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST )
	@ResponseBody
	public Object delete(String piIds) {
		String piIdsa = piIds.substring(1, piIds.length()-1);
		String[] piIdss = piIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<piIdss.length;i++){
			PProductInfo product = parProductInfoService.selectByKey(piIdss[i]);
			product.setPiValidFlag(Const.BUSINESS_STUTAS_99);
			product.setPiAction(Const.BUSINESS_STUTAS_00);
			product.setPiCuser(user.getLoginName());
			product.setPiCtime(getSysDate());
			product.setPiCheckState(Const.BUSINESS_STUTAS_00);
			product.setPiUuser(null);
			product.setPiUtime(null);
			int num = parProductInfoService.updateAll(product);
			if(num==1){
				msg="删除成功";
			}else{
				msg="删除失败";
			}
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品信息复核
	 *
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object checkSubmit(String piIds) {
		String piIdsa = piIds.substring(1, piIds.length()-1);
		String[] piIdss = piIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<piIdss.length;i++){
			PProductInfo entity = parProductInfoService.selectByKey(piIdss[i]);
			entity.setPiCheckState(Const.BUSINESS_STUTAS_01);
			entity.setPiUuser(user.getLoginName());
			entity.setPiUtime(getSysDate());
			int num = parProductInfoService.updateAll(entity);
			if(num==1){
				msg="复核成功";
			}else{
				msg="复核失败";
			}
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品信息停用
	 *
	 * @return
	 */
	@RequestMapping(value = "/stopSubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object stopSubmit(String piIds) {
		String piIdsa = piIds.substring(1, piIds.length()-1);
		String[] piIdss = piIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<piIdss.length;i++){
			PProductInfo entity = parProductInfoService.selectByKey(piIdss[i]);
			entity.setPiValidFlag(Const.BUSINESS_STUTAS_10);
			entity.setPiCheckState(Const.BUSINESS_STUTAS_00);
			entity.setPiAction(Const.BUSINESS_STUTAS_04);
			entity.setPiCuser(user.getLoginName());
			entity.setPiCtime(getSysDate());
			entity.setPiUuser(null);
			entity.setPiUtime(null);
			int num = parProductInfoService.updateAll(entity);
			if(num==1){
				msg="停用成功";
			}else{
				msg="停用失败";
			}
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品信息启用
	 *
	 * @return
	 */
	@RequestMapping(value = "/startSubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object startSubmit(String piIds) {
		String piIdsa = piIds.substring(1, piIds.length()-1);
		String[] piIdss = piIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<piIdss.length;i++){
			PProductInfo entity = parProductInfoService.selectByKey(piIdss[i]);
			entity.setPiValidFlag(Const.BUSINESS_STUTAS_01);
			entity.setPiCheckState(Const.BUSINESS_STUTAS_00);
			entity.setPiAction(Const.BUSINESS_STUTAS_05);
			entity.setPiCuser(user.getLoginName());
			entity.setPiCtime(getSysDate());
			entity.setPiUuser(null);
			entity.setPiUtime(null);
			int num = parProductInfoService.updateAll(entity);
			if(num==1){
				msg="启用成功";
			}else{
				msg="启用失败";
			}
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品信息复制提交
	 *
	 * @return
	 */
	@RequestMapping(value = "/copySubmit",method = RequestMethod.POST )
	@ResponseBody
	public Object copySubmit(PProductInfo entity) {
		String msg = "";
		String batchNumber = entity.getPiBatchNumber();
		String channelCode = entity.getPiChannelCode();
		if(StringUtils.isEmpty(batchNumber)){
			batchNumber = "1";
			entity.setPiBatchNumber(batchNumber);
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("piChannelProductCode", entity.getPiChannelProductCode());
		map.put("piBatchNumber", batchNumber);
		map.put("CHANNELCODE", channelCode);
		Long sum = parProductInfoService.selectByConditionCount(map);
		if(sum>0){
			msg = "该渠道此批次的产品代码已存在";
			return resultSuccess(msg);
		}
		int fundTypecount = sysDictService.findDictCount("fundType", entity.getPiProductType());
		if(fundTypecount!=1){
			msg="产品类型不存在";
			return resultSuccess(msg);
		}
		int ptCurrencyCount = sysDictService.findDictCount("currency", entity.getPiCurrencyType());
		if(ptCurrencyCount!=1){
			msg="支付币种不存在";
			return resultSuccess(msg);
		}
		if(StringUtils.isEmpty(entity.getPiBatchNumber())){
			entity.setPiBatchNumber("1");
		}
		entity.setPiIpoBeginDate(entity.getPiIpoBeginDate().replaceAll("-", ""));
		entity.setPiIpoEndDate(entity.getPiIpoEndDate().replaceAll("-", ""));
		entity.setPiProSetupDate(entity.getPiProSetupDate().replaceAll("-", ""));
		entity.setPiProEndDate(entity.getPiProEndDate()==null?"":entity.getPiProEndDate().replaceAll("-", ""));
		SysUser user=getCurrentUser();
		entity.setPiCuser(user.getLoginName());
		entity.setPiCtime(getSysDate());
		entity.setPiUtime(null);
		entity.setPiUuser("");
		entity.setPiCheckState(Const.BUSINESS_STUTAS_00);
		entity.setPiValidFlag(Const.BUSINESS_STUTAS_01);
		entity.setPiAction(Const.BUSINESS_STUTAS_06);
		/*if(null !=entity.getPiSubChannelCode() && ""!=entity.getPiSubChannelCode() ){
			//处理产品的子渠道
			String subChannelCodeRes = makeSunChannelCode(entity);
			if(!ExceptionConStants.retCode_0000.equals(subChannelCodeRes)){
				return resultSuccess(subChannelCodeRes);
			}
		}*/

		int i = parProductInfoService.save(entity);
		if(i==1){
			msg= "复制成功！";
		}else{
			msg= "复制失败！";
		}
		return resultSuccess(msg);
	}

}
