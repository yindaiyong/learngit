package com.sams.bizParameterManage.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.utils.MyMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelProductRealService;
import com.sams.common.constant.Const;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.ChannelProductRelation;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.custom.model.result.ChannelProductInfo;
import com.sams.custom.model.result.ChannelProductResult;
import com.sams.sys.model.SysUser;

@Controller
@RequestMapping("/channelProductReal")
public class ChannelProductRealController extends BaseController{

	@Autowired
	private ChannelProductRealService channelProductRealService;

	/**
	 * 产品收益率页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/channelProductReal/channelProductRealList";
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
	@RequestMapping(value = "/getchannelProductRealData")
	@ResponseBody
	public PageInfo getParProductTempInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.get("cprFundCode")!=null){
			if(MyMapUtils.getStringArrayBySplit(condition,"cprFundCode","-").length>1){
				condition.put("cprBatchNumber", MyMapUtils.getStringArrayBySplit(condition,"cprFundCode","-")[1]);
			}
			condition.put("cprFundCode", MyMapUtils.getStringArrayBySplit(condition,"cprFundCode","-")[0]);
		}else{
			condition.put("cprBatchNumber", null);
		}
		pageInfo.setPageResult(channelProductRealService.findDataGrid(pageInfo,
				condition));
		return pageInfo;
	}

	/**
	 * 产品收益率页面新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/channelProductReal/channelProductRealAdd";
	}

	/**
	 * 产品收益率页面新增确认
	 * 
	 * @return
	 */
	@RequestMapping("/addSubmit")
	@ResponseBody
	public Object addSubmit(ChannelProductResult entity) {
		String msg = "";
		ChannelProductRelation channelProductReal = new ChannelProductRelation();
		channelProductReal.setCprChannelCode(entity.getCprChannelCode() == null ? "": entity.getCprChannelCode() );
		channelProductReal.setCprBatchNumber(entity.getCprFundCode().split("-")[1] == null ? "": entity.getCprFundCode().split("-")[1]);
		channelProductReal.setCprFundCode(entity.getCprFundCode().split("-")[0] == null ? "": entity.getCprFundCode().split("-")[0]);
		channelProductReal.setCprSectionNumber(entity.getCprSectionNumber() == null ? "": entity.getCprSectionNumber());
		channelProductReal.setCprFundRate(entity.getCprFundRate() == null ? new BigDecimal("0") : entity.getCprFundRate());
		channelProductReal.setCprStartMoney(entity.getCprStartMoney() == null ? "": entity.getCprStartMoney());
		channelProductReal.setCprEndMoney(entity.getCprEndMoney() == null ? "": entity.getCprEndMoney());
		channelProductReal.setCprCheckState(Const.BUSINESS_STUTAS_00);
		channelProductReal.setCprValidFlag(Const.BUSINESS_STUTAS_01);
		SysUser user=getCurrentUser();
		channelProductReal.setCprCuser(user.getLoginName());
		channelProductReal.setCprCtime(getSysDate());
		channelProductReal.setCprAction(Const.BUSINESS_STUTAS_01);
		Map<String,Object> map = Maps.newHashMap();
		map.put("CHANNELCODE", entity.getCprChannelCode());
		map.put("FUNDCODE", entity.getCprFundCode().split("-")[0]);
		map.put("SECTIONNUMBER", entity.getCprSectionNumber());
		map.put("BATCHNUMBER", entity.getCprFundCode().split("-")[1]);
		int usedReal = channelProductRealService.selectUsedReal(map);
		if(usedReal>0){
			msg = "该批次号的产品已有"+entity.getCprSectionNumber()+"类型的收益率";
			return resultSuccess(msg);
		}
		int num = channelProductRealService.save(channelProductReal);
		if (num == 1) {
			msg =Const.ACTION_ADD+"成功";
		} else {
			msg =Const.ACTION_ADD+"失败";
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品收益率页面修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(String cprId, String piId, Model model) {
		Map<String, Long> map = Maps.newHashMap();
		map.put("cprId", Long.parseLong(cprId));
		map.put("piId", Long.parseLong(piId));
		ChannelProductResult channelProductResult = channelProductRealService
				.selectChannelManagerResult(map);
		channelProductResult.setCprFundCode(channelProductResult.getCprFundCode()+"-"+channelProductResult.getCprBatchNumber());
		model.addAttribute("entity", channelProductResult);
		return "sys/bizParameterManage/channelProductReal/channelProductRealEdit";
	}

	/**
	 * 产品收益率页面修改确认
	 * 
	 * @return
	 */
	@RequestMapping("/editSubmit")
	@ResponseBody
	public Object editSubmit(ChannelProductResult entity) {
		String msg = "";
		ChannelProductRelation channelProductReal = channelProductRealService.selectByKey(entity.getCprId().toString());
		channelProductReal.setCprChannelCode(entity.getCprChannelCode() == null ? "": entity.getCprChannelCode());
		channelProductReal.setCprBatchNumber(entity.getCprFundCode().split("-")[1] == null ? "": entity.getCprFundCode().split("-")[1]);
		channelProductReal.setCprFundCode(entity.getCprFundCode().split("-")[0] == null ? "": entity.getCprFundCode().split("-")[0]);
		channelProductReal.setCprSectionNumber(entity.getCprSectionNumber() == null ? "": entity.getCprSectionNumber());
		channelProductReal.setCprFundRate(entity.getCprFundRate() == null ? new BigDecimal("0") : entity.getCprFundRate());
		channelProductReal.setCprStartMoney(entity.getCprStartMoney() == null ? "": entity.getCprStartMoney());
		channelProductReal.setCprEndMoney(entity.getCprEndMoney() == null ? "": entity.getCprEndMoney());
		channelProductReal.setCprCheckState(Const.BUSINESS_STUTAS_00);
		channelProductReal.setCprValidFlag(entity.getCprValidFlag()==null?"":entity.getCprValidFlag());
		SysUser user=getCurrentUser();
		channelProductReal.setCprUuser(user.getLoginName());
		channelProductReal.setCprUtime(getSysDate());
		channelProductReal.setCprAction(Const.BUSINESS_STUTAS_01);
		channelProductReal.setCprUuser(null);
		channelProductReal.setCprUtime(null);
		Map<String,Object> map = Maps.newHashMap();
		map.put("CHANNELCODE", entity.getCprChannelCode());
		map.put("FUNDCODE", entity.getCprFundCode().split("-")[0]);
		map.put("SECTIONNUMBER", entity.getCprSectionNumber());
		map.put("BATCHNUMBER", entity.getCprFundCode().split("-")[1]);
		/*int usedReal = channelProductRealService.selectUsedReal(map);
		if(usedReal>0){
			msg = "该批次号的产品已有"+entity.getCprSectionNumber()+"类型的收益率";
			return resultSuccess(msg);
		}*/
		int num = channelProductRealService.updateAll(channelProductReal);
		if (num == 1) {
			msg = Const.ACTION_EDIT+"成功";
		} else {
			msg = Const.ACTION_EDIT+"失败";
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品收益率页面查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(String cprId, String piId, Model model) {
		Map<String, Long> map = Maps.newHashMap();
		map.put("cprId", Long.parseLong(cprId));
		map.put("piId", Long.parseLong(piId));
		ChannelProductResult channelProductResult = channelProductRealService
				.selectChannelManagerResult(map);
		channelProductResult.setCprFundCode(channelProductResult.getCprFundCode()+"-"+channelProductResult.getCprBatchNumber());
		model.addAttribute("entity", channelProductResult);
		return "sys/bizParameterManage/channelProductReal/channelProductRealView";
	}

	/**
	 * 产品收益率删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object delSubmit(String cprIds) {
		String cprIdsa = cprIds.substring(1, cprIds.length()-1);
		String[] cprIdss = cprIdsa.split(",");
		String msg = "";
		for(int i=0;i<cprIdss.length;i++){
			ChannelProductRelation channelProductReal = channelProductRealService.selectByKey(cprIdss[i]);
			channelProductReal.setCprCheckState(Const.BUSINESS_STUTAS_00);
			SysUser user=getCurrentUser();
			channelProductReal.setCprCuser(user.getLoginName());
			channelProductReal.setCprCtime(getSysDate());
			channelProductReal.setCprValidFlag(Const.BUSINESS_STUTAS_00);
			channelProductReal.setCprAction(Const.BUSINESS_STUTAS_00);
			channelProductReal.setCprUtime(null);
			channelProductReal.setCprUuser(null);
			int num = channelProductRealService.updateAll(channelProductReal);
			if (num == 1) {
				msg = Const.ACTION_DEL+"成功";
			} else {
				msg = Const.ACTION_DEL+"失败";
			}	
		}
		return resultSuccess(msg);
	}

	/**
	 * 产品收益率复核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object checkSubmit(String cprIds) {
		String cprIdsa = cprIds.substring(1, cprIds.length()-1);
		String[] cprIdss = cprIdsa.split(",");
		String msg = "";
		for(int i=0;i<cprIdss.length;i++){
			ChannelProductRelation channelProductReal = channelProductRealService.selectByKey(cprIdss[i]);
			channelProductReal.setCprCheckState(Const.BUSINESS_STUTAS_01);
			SysUser user=getCurrentUser();
			channelProductReal.setCprUuser(user.getLoginName());
			channelProductReal.setCprUtime(getSysDate());
			int num = channelProductRealService.updateAll(channelProductReal);
			if (num == 1) {
				msg = Const.ACTION_CHECK+"成功";
			} else {
				msg = Const.ACTION_CHECK+"失败";
			}	
		}
		return resultSuccess(msg);
	}
}
