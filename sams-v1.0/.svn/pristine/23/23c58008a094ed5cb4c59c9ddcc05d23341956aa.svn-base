package com.sams.bizParameterManage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.PChannelManagerService;
import com.sams.common.constant.Const;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.Result;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.PChannelManager;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.result.ChannelManagerResult;
import com.sams.sys.model.SysUser;

@Controller
@RequestMapping("/channelManager")
public class ChannelManagerController extends BaseController{

	@Autowired
	private PChannelManagerService channelManagerService;

	/**
	 * 代销渠道客户经理关系页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/channelManager/channelManagerList";
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
	@RequestMapping(value = "/getChannelManagerData")
	@ResponseBody
	public PageInfo getParProductTempInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(channelManagerService.findDataGrid(pageInfo,condition));
		return pageInfo;
	}
	
	/**
	 * 代销渠道客户经理关系新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "sys/bizParameterManage/channelManager/channelManagerAdd";
	}
	
	/**
	 * 代销渠道客户经理关系新增确认
	 * 
	 * @return
	 */
    @RequestMapping("/addSubmit")
    @ResponseBody
    public Object addSubmit(ChannelManagerResult entity) {
    	SysUser user=getCurrentUser();
    	String msg = "";
    	Map<String,Object> map = Maps.newHashMap();
    	map.put("channelCode", entity.getCmChannelCode());
    	map.put("managerCode", entity.getCmManagerCode());
    	int channelManagerCount = channelManagerService.selectChannelManagerCount(map);
    	if(channelManagerCount>0){
    		msg="该经理已在该渠道下任职";
    		return resultSuccess(msg); 
    	}
    	PChannelManager manager = new PChannelManager();
    	manager.setCmChannelCode(entity.getCmChannelCode()==null?"":entity.getCmChannelCode());
    	manager.setCmManagerCode(entity.getCmManagerCode()==null?"":entity.getCmManagerCode());
    	manager.setCmManagerName(entity.getCmManagerName()==null?"":entity.getCmManagerName());
    	manager.setCmStartDate(entity.getCmStartDate()==null?"":entity.getCmStartDate().replaceAll("-", ""));
    	manager.setCmEndDate(entity.getCmEndDate()==null?"":entity.getCmEndDate().replaceAll("-", ""));
    	manager.setCmCuser(user.getLoginName());
    	manager.setCmCtime(getSysDate());
    	manager.setCmCheckState(Const.BUSINESS_STUTAS_00);
    	manager.setCmValidFlag(Const.BUSINESS_STUTAS_01);
    	manager.setCmAction(Const.BUSINESS_STUTAS_01);
    	int num = channelManagerService.save(manager);
        	if(num==1){
        		msg="新增成功";
        	}else{
        		msg="新增失败";
        	}
    	return resultSuccess(msg); 
    }
    
    /**
	 *代销渠道客户经理关系修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Model model, String cmId) {
		ChannelManagerResult entity = channelManagerService.selectChannelManagerResult(cmId);
		entity.setCmStartDate(entity.getCmStartDate().substring(0,4)+"-"+entity.getCmStartDate().substring(4,6)+"-"+entity.getCmStartDate().substring(6,8));
		if(entity.getCmEndDate()!=null){
			entity.setCmEndDate(entity.getCmEndDate().substring(0,4)+"-"+entity.getCmEndDate().substring(4,6)+"-"+entity.getCmEndDate().substring(6,8));
		}
		model.addAttribute("entity",entity);
		
		return "sys/bizParameterManage/channelManager/channelManagerEdit";
	}
	
	/**
	 * 代销渠道客户经理关系修改确认
	 * 
	 * @return
	 */
    @RequestMapping("/editSubmit")
    @ResponseBody
    public Object editSubmit(ChannelManagerResult entity) {
    	SysUser user=getCurrentUser();
    	String msg = "";
    	PChannelManager manager = channelManagerService.selectByKey(entity.getCmId()) ;
    	manager.setCmChannelCode(entity.getCmChannelCode()==null?"":entity.getCmChannelCode());
    	manager.setCmManagerCode(entity.getCmManagerCode()==null?"":entity.getCmManagerCode());
    	manager.setCmManagerName(entity.getCmManagerName()==null?"":entity.getCmManagerName());
    	manager.setCmStartDate(entity.getCmStartDate()==null?"":entity.getCmStartDate().replaceAll("-", ""));
    	manager.setCmEndDate(entity.getCmEndDate()==null?"":entity.getCmEndDate().replaceAll("-", ""));
    	manager.setCmCuser(user.getLoginName());
    	manager.setCmCtime(getSysDate());
    	manager.setCmCheckState(Const.BUSINESS_STUTAS_00);
    	manager.setCmAction(Const.BUSINESS_STUTAS_02);
    	manager.setCmUuser(null);
    	manager.setCmUtime(null);
    	int num = channelManagerService.updateAll(manager);
    	if(num==1){
    		msg="修改成功";
    	}else{
    		msg="修改失败";
    	}
    	return resultSuccess(msg); 
    }
    
    /**
	 * 代销渠道客户经理关系查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(Model model, String cmId) {
		ChannelManagerResult entity = channelManagerService.selectChannelManagerResult(cmId);
		entity.setCmStartDate(entity.getCmStartDate().substring(0,4)+"-"+entity.getCmStartDate().substring(4,6)+"-"+entity.getCmStartDate().substring(6,8));
		if(entity.getCmEndDate()!=null){
			entity.setCmEndDate(entity.getCmEndDate().substring(0,4)+"-"+entity.getCmEndDate().substring(4,6)+"-"+entity.getCmEndDate().substring(6,8));
		}		model.addAttribute("entity",entity);
		return "sys/bizParameterManage/channelManager/channelManagerView";
	}
	
	
	/**
	 * 代销渠道客户经理关系停用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stopSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object stopSubmit(String cmIds) {
		String cmIdsa = cmIds.substring(1, cmIds.length()-1);
		String[] cmIdss = cmIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<cmIdss.length;i++){
			PChannelManager manager = channelManagerService.selectByKey(cmIdss[i]) ;
			manager.setCmValidFlag(Const.BUSINESS_STUTAS_10);
			manager.setCmCheckState(Const.BUSINESS_STUTAS_00);
			manager.setCmAction(Const.BUSINESS_STUTAS_04);
			manager.setCmCuser(user.getLoginName());
	    	manager.setCmCtime(getSysDate());
	    	manager.setCmUuser(null);
	    	manager.setCmUtime(null);
			int num = channelManagerService.updateAll(manager);
			if(num==1){
				msg="关系已停用";
			}else{
				msg="操作失败";
			}	
		}
		return resultSuccess(msg); 
	}
	
	/**
	 * 代销渠道客户经理关系启用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/startSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object startSubmit(String cmIds) {
		String cmIdsa = cmIds.substring(1, cmIds.length()-1);
		String[] cmIdss = cmIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<cmIdss.length;i++){
			PChannelManager manager = channelManagerService.selectByKey(cmIdss[i]) ;
			manager.setCmValidFlag(Const.BUSINESS_STUTAS_01);
			manager.setCmCheckState(Const.BUSINESS_STUTAS_00);
			manager.setCmAction(Const.BUSINESS_STUTAS_05);
			manager.setCmCuser(user.getLoginName());
	    	manager.setCmCtime(getSysDate());
	    	manager.setCmUuser(null);
	    	manager.setCmUtime(null);
			int num = channelManagerService.updateAll(manager);
			if(num==1){
				msg="关系已启用";
			}else{
				msg="操作失败";
			}
		}
		
		return resultSuccess(msg); 
	}
	
	/**
	 * 代销渠道客户经理关系删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object delSubmit(String cmIds) {
		String cmIdsa = cmIds.substring(1, cmIds.length()-1);
		String[] cmIdss = cmIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<cmIdss.length;i++){
			PChannelManager manager = channelManagerService.selectByKey(cmIdss[i]) ;
			manager.setCmValidFlag(Const.BUSINESS_STUTAS_00);
			manager.setCmCheckState(Const.BUSINESS_STUTAS_00);
			manager.setCmAction(Const.BUSINESS_STUTAS_00);
			manager.setCmCuser(user.getLoginName());
	    	manager.setCmCtime(getSysDate());
	    	manager.setCmUtime(null);
	    	manager.setCmUuser(null);
			int num = channelManagerService.updateAll(manager);
			if(num==1){
				msg="关系已删除";
			}else{
				msg="操作失败";
			}	
		}
		return resultSuccess(msg); 
	}
	
	/**
	 *  代销渠道客户经理关系复核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object checkSubmit(String cmIds) {
		String cmIdsa = cmIds.substring(1, cmIds.length()-1);
		String[] cmIdss = cmIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<cmIdss.length;i++){
			PChannelManager manager = channelManagerService.selectByKey(cmIdss[i]) ;
			manager.setCmCheckState(Const.BUSINESS_STUTAS_01);
			manager.setCmUuser(user.getLoginName());
	    	manager.setCmUtime(getSysDate());
			int num = channelManagerService.updateAll(manager);
			if(num==1){
				msg="关系已复核";
			}else{
				msg="操作失败";
			}
		}
		return resultSuccess(msg);  
	}
	
}
