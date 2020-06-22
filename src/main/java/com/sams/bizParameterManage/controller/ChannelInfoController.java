package com.sams.bizParameterManage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sams.batchfile.service.RollBackChannelService;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.ChannelProductService;
import com.sams.common.constant.Const;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.model.SysUser;
import com.sams.sys.service.SysDictService;


@Controller
@RequestMapping("/channelInfo")
public class ChannelInfoController extends BaseController{

	@Autowired
	private ChannelInfoService channelInfoService;
	
	@Autowired
	public SysDictService  sysDictService;
	
	@Autowired
	public ChannelProductService channelProductService;

	@Autowired
	private RollBackChannelService rollBackChannelService;

	/**
	 * 产品信息模板页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/channelInfo/channelInfoList";
	}
	

	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getChannelInfoData")
	@ResponseBody
	public PageInfo getChannelInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(channelInfoService.findDataGrid(pageInfo,condition));
		return pageInfo;
	}
	
	/**
	 * 代销渠道基本信息管理新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage(Model model,HttpServletRequest request,HttpServletResponse response) {

		//默认个人  机构  开户证件类型勾选
		model.addAttribute("ciPerAcctType", "0,4,6,A,B");
		model.addAttribute("ciOrgAcctType", "1");
		Object productTypes = sysDictService.productTypes(request,response);
		model.addAttribute("productTypes", productTypes);
			
		return "sys/bizParameterManage/channelInfo/channelInfoAdd";
	}
	
	/**
	 * 代销渠道基本信息管理新增确认
	 *
	 * @return
	 */
    @RequestMapping("/addSubmit")
    @ResponseBody
    public Object addSubmit(PchannelInfo entity) {
    	String msg = "";
    	int marketCodeCount = sysDictService.findDictCount("marketCode", entity.getCiMarketCode());
    	if(marketCodeCount!=1){
    		msg="所属市场不存在";
    		return resultSuccess(msg); 
    	}
    	int csdcVerCount = sysDictService.findDictCount("csdcVer", entity.getCiCsdcVer());
    	if(csdcVerCount!=1){
    		msg="中登版本不存在";
    		return resultSuccess(msg); 
    	}
    	int econVerifyTypeCount = sysDictService.findDictCount("econVerifyType", entity.getCiEconVerifyType());
    	if(econVerifyTypeCount!=1){
    		msg="电子合同校验方式不存在";
    		return resultSuccess(msg); 
    	}
    	SysUser user=getCurrentUser();
    	entity.setCiCuser(user.getLoginName());
    	entity.setCiCtime(getSysDate());
    	entity.setCiValidFlag(Const.BUSINESS_STUTAS_01);
    	entity.setCiCheckState(Const.BUSINESS_STUTAS_00);
    	entity.setCiAction(Const.BUSINESS_STUTAS_01);
    	//下拉多选框选中字段判断
        checkComboboxFileType(entity);
    	int num = channelInfoService.selectByCode(entity.getCiChannelCode());
    	if(num==0){
    		num = channelInfoService.save(entity);
        	if(num==1){
        		msg="新增成功";
        	}else{
        		msg="新增失败";
        	}
    	}else{
    		msg="渠道号已经存在";
    	}
    	
    	return resultSuccess(msg); 
    }

    /**
     * @Description 判断清除首字符为,的数据
     * @Author weijunjie
     * @Date 2020/3/13 17:49
     **/
    public void checkComboboxFileType(PchannelInfo entity){
    	//个人开户类型
        entity.setCiPerAcctType(changeTypeString(entity.getCiPerAcctType()));
		//机构开户类型
        entity.setCiOrgAcctType(changeTypeString(entity.getCiOrgAcctType()));
		//94文件产品类型
        entity.setCiOtherFileType(changeTypeString(entity.getCiOtherFileType()));
		//26文件产品类型
        entity.setCiVolDetailType(changeTypeString(entity.getCiVolDetailType()));
	}

	/**
	 * @Description 首字符判断替换
	 * @Author weijunjie
	 * @Date 2020/3/16 9:17
	 **/
	public String changeTypeString(String type){
    	if(org.apache.commons.lang3.StringUtils.isBlank(type) || type.length() <=1){
    		if(",".equals(type)){
    			return null;
			}else{
				return type;
			}
    	}else{
            String substring = type.substring(0, 1);
            if(",".equals(substring)){
                return type.substring(1, type.length());
            }else{
                return type;
            }
        }
	}
    
    /**
	 * 代销渠道基本信息管理修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(Model model, String ciId,HttpServletRequest request,HttpServletResponse response) {
		PchannelInfo entity = channelInfoService.selectByKey(ciId);
		model.addAttribute("entity",entity);
		Object productTypes = sysDictService.productTypes(request,response);
		model.addAttribute("productTypes", productTypes);
		return "sys/bizParameterManage/channelInfo/channelInfoEdit";
	}
	
	/**
	 * 代销渠道基本信息管理修改确认
	 * 
	 * @return
	 */
    @RequestMapping("/editSubmit")
    @ResponseBody
    public Object editSubmit(PchannelInfo entity) {
    	String msg = "";
    	int marketCodeCount = sysDictService.findDictCount("marketCode", entity.getCiMarketCode());
    	if(marketCodeCount!=1){
    		msg="所属市场不存在";
    		return resultSuccess(msg); 
    	}
    	int csdcVerCount = sysDictService.findDictCount("csdcVer", entity.getCiCsdcVer());
    	if(csdcVerCount!=1){
    		msg="中登版本不存在";
    		return resultSuccess(msg); 
    	}
    	int econVerifyTypeCount = sysDictService.findDictCount("econVerifyType", entity.getCiEconVerifyType());
    	if(econVerifyTypeCount!=1){
    		msg="电子合同校验方式不存在";
    		return resultSuccess(msg); 
    	}
    	SysUser user=getCurrentUser();
    	entity.setCiCuser(user.getLoginName());
    	entity.setCiCtime(getSysDate());
    	entity.setCiAction(Const.BUSINESS_STUTAS_02);
    	entity.setCiCheckState(Const.BUSINESS_STUTAS_00);
    	entity.setCiUuser(null);
    	entity.setCiUtime(null);
        checkComboboxFileType(entity);
    	int num = channelInfoService.updateAll(entity);
    	if(num==1){
    		msg="修改成功";
    	}else{
    		msg="修改失败";
    	}
    	return resultSuccess(msg); 
    }
    
    /**
	 * 代销渠道基本信息管理查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/viewPage", method = RequestMethod.GET)
	public String viewPage(Model model, String ciId,HttpServletRequest request,HttpServletResponse response) {
		PchannelInfo entity = channelInfoService.selectByKey(ciId);
		model.addAttribute("entity",entity);
		Object productTypes = sysDictService.productTypes(request,response);
		model.addAttribute("productTypes", productTypes);
		return "sys/bizParameterManage/channelInfo/channelInfoView";
	}
	
	/**
	 * 代销渠道基本信息停用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stopSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object stopSubmit(String ciIds) {
		String ciIdsa = ciIds.substring(1, ciIds.length()-1);
		String[] ciIdss = ciIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<ciIdss.length;i++){
			PchannelInfo entity = channelInfoService.selectByKey(ciIdss[i]);
	    	entity.setCiCuser(user.getLoginName());
	    	entity.setCiCtime(getSysDate());
	    	entity.setCiAction(Const.BUSINESS_STUTAS_04);
			entity.setCiValidFlag(Const.BUSINESS_STUTAS_10);
			entity.setCiCheckState(Const.BUSINESS_STUTAS_00);
			entity.setCiUuser(null);
	    	entity.setCiUtime(null);
			int num = channelInfoService.updateAll(entity);
			if(num==1){
				msg="渠道已停用";
			}else{
				msg="操作失败";
			}
		}
	
		return resultSuccess(msg); 
	}
	
	/**
	 * 代销渠道基本信息启用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/startSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object startSubmit(String ciIds) {
		String ciIdsa = ciIds.substring(1, ciIds.length()-1);
		String[] ciIdss = ciIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<ciIdss.length;i++){
			PchannelInfo entity = channelInfoService.selectByKey(ciIdss[i]);
	    	entity.setCiCuser(user.getLoginName());
	    	entity.setCiCtime(getSysDate());
	    	entity.setCiAction(Const.BUSINESS_STUTAS_05);
			entity.setCiValidFlag(Const.BUSINESS_STUTAS_01);
			entity.setCiCheckState(Const.BUSINESS_STUTAS_00);
			entity.setCiUuser(null);
	    	entity.setCiUtime(null);
			int num = channelInfoService.updateAll(entity);
			if(num==1){
				msg="渠道已启用";
			}else{
				msg="操作失败";
			}
		}
		
		return resultSuccess(msg); 
	}
	
	/**
	 * 代销渠道基本信息删除 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object delSubmit(String ciIds) {
		String ciIdsa = ciIds.substring(1, ciIds.length()-1);
		String[] ciIdss = ciIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<ciIdss.length;i++){
			PchannelInfo entity = channelInfoService.selectByKey(ciIdss[i]);
			int channelProductCount = channelProductService.selectChannelProductCount(entity.getCiChannelCode());
			if(channelProductCount>0){
				msg="渠道产品关联关系存在，无法删除";
				return resultSuccess(msg); 
			}
			int productCount = channelProductService.selectProductCount(entity.getCiChannelCode());
			if(productCount>0){
				msg="渠道产下有产品在用，无法删除";
				return resultSuccess(msg); 
			}
	    	entity.setCiCuser(user.getLoginName());
	    	entity.setCiCtime(getSysDate());
	    	entity.setCiAction(Const.BUSINESS_STUTAS_00);
			entity.setCiValidFlag(Const.BUSINESS_STUTAS_00);
			entity.setCiCheckState(Const.BUSINESS_STUTAS_00);
			entity.setCiUuser(null);
	    	entity.setCiUtime(null);
			int num = channelInfoService.updateAll(entity);
			if(num==1){
				msg="渠道已删除";
			}else{
				msg="操作失败";
			}
		}
		
		return resultSuccess(msg); 
	}
	
	/**
	 * 代销渠道基本信息复核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkSubmit", method = RequestMethod.POST)
	@ResponseBody
	public Object checkSubmit(String ciIds) {
		String ciIdsa = ciIds.substring(1, ciIds.length()-1);
		String[] ciIdss = ciIdsa.split(",");
		String msg ="";
		SysUser user=getCurrentUser();
		for(int i=0;i<ciIdss.length;i++){
			PchannelInfo entity = channelInfoService.selectByKey(ciIdss[i]);
	    	entity.setCiUuser(user.getLoginName());
	    	entity.setCiUtime(getSysDate());
			entity.setCiCheckState(Const.BUSINESS_STUTAS_01);
			int num = channelInfoService.updateAll(entity);
			if(num==1){
				msg="渠道已复核";
			}else{
				msg="操作失败";
			}
		}
		return resultSuccess(msg); 
	}
	
	/**
	 * 查询渠道信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectChannelInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object selectChannelInfo(String channelCode) {
		 Map<String,Object> map = Maps.newHashMap();
		 map.put("CHANNELCODE", channelCode);
		 PchannelInfo channelInfo =  channelInfoService.selectByChannelCode(map);
		 return channelInfo; 	
	}
	
	/**
	 * 查询子渠道信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectSubChannelList")
	@ResponseBody
	public Object selectSubChannelList(String channelCode) {
		 List<Map<String, Object>> subChannelList =  channelInfoService.selectSubChannelToShow(channelCode);
		 return com.alibaba.fastjson.JSONArray.parse(JSONObject.toJSONString(subChannelList));
	}

	/**
	 * @Description 查询渠道当天所有的申请数据
	 * @Author weijunjie
	 * @Date 2019/12/6 16:57
	 **/
	@RequestMapping(value = "/getAllChannelDataCount")
	@ResponseBody
	public Object getAllChannelDataCount(String theDate) {
        String thisDay = DateUtils.getDate("yyyyMMdd");
        if(StringUtils.isNotBlanks(theDate)){
            if(theDate.contains("-")){
                String[] s = theDate.split("-");
                thisDay = s[0] + (s[1].length()<2?"0"+s[1]:s[1])+(s[2].length()<2?"0"+s[2]:s[2]);
            }
        }
        List<Map<String, Object>> allChannelDataCount = channelInfoService.getAllChannelDataCount(thisDay);
		return allChannelDataCount;
	}

	/**
	 * @Description 进入回滚渠道数据页面
	 * @Author weijunjie
	 * @Date 2019/12/17 11:15
	 **/
	@RequestMapping("/toRollBackChannelPage")
	public String toRollBackChannelPage(){
		return "sys/bizParameterManage/channelInfo/channelForRollBack";
	}

	/**
	 * @Description 渠道数据回滚操作请求接口
	 * @Author weijunjie
	 * @Date 2019/12/17 11:15
	 **/
	@RequestMapping(value = "/rollBackChannelInfo",method = RequestMethod.POST)
	@ResponseBody
	public Object rollBackChannelInfo(String channelCode){
        String s = rollBackChannelService.rollbackChannel(channelCode);
        return resultSuccess(s);
	}
	
}
