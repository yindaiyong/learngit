package com.sams.business.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sams.batchfile.service.CheckFileUploadService;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.MyMapUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.sys.model.SysUser;

@Controller
@RequestMapping(value = "/checkFileUpload")
public class CheckFileUploadController extends BaseController{
	
	@Autowired
	private CheckFileUploadService checkFileUploadService;
	
	/**
	 * 校验文件是否上传成功跳转页面
	 * @Title: manager   
	 * @author: 王超 2020年4月26日 上午8:55:17
	 * @return: String      
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String manager() {
		return "sys/bizParameterManage/checkFileUpload/checkFileUploadList";
	}
	

	/**
	 * 校验文件是否上传成功数据显示
	 * @Title: getCheckFileUploadData   
	 * @author: 王超 2020年4月26日 上午10:55:17 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param
	 * @return Map
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCheckFileUploadData")
	@ResponseBody
	public PageInfo getCheckFileUploadData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request,String tradeDate) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		//日期yyyy-mm-dd变成yyyymmdd格式
		String transDate=condition.get("TRANSDATE")==null? "":MyMapUtils.getStringNotNull(condition,"TRANSDATE").replaceAll("-", "");
	    if(null==condition.get("TRANSDATE")){
	    	condition.put("TRANSDATE", tradeDate);
	    }else{
	    	condition.put("TRANSDATE", transDate);
	    }
		pageInfo.setPageResult(checkFileUploadService.findCheckFileUploadCondition(pageInfo,condition));
		return pageInfo;
	}
	
	/**
	 * 读取深圳通日志文件进行上传文件与否的判断
	 * @Title: manager   
	 * @author: 王超 2020年4月26日 下午1:55:17
	 * @return: String      
	 */
    @RequestMapping(value = "/readLog", method = RequestMethod.POST)
    @ResponseBody
    public Object readLog(String tradeDate) {
    	Map<String,Object> retMap = new HashMap<String, Object>();
    	SysUser user = getCurrentUser();
    	String loginName = user.getLoginName();
    	retMap = checkFileUploadService.readLog(tradeDate,loginName);
    	if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))){
    		return resultSuccess(retMap.get("retMsg")+"");
    	}
    	return resultSuccess("success"); 
    }
	
}
