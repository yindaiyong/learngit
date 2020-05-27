package com.sams.csdcInterfaceConfig.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.csdcInterfaceConfig.service.CsdcInterfaceService;
import com.sams.custom.model.InterfaceInfo;

/**
 * @ClassName:  CsdcInfoController   
 * @Description:中登接口  
 * @author: yindy
 * @date:   2019年5月20日 下午1:03:31   
 *
 */
@Controller
@RequestMapping("/csdc")
public class CsdcInfoController extends BaseController{

	
	private static Logger LOGGER = LoggerFactory.getLogger(CsdcInfoController.class);
	
	@Autowired
	private CsdcInterfaceService csdcInterfaceService;
	
	/**
	 * 中登接口基本信息页面  
	 * @Title: csdcPage   
	 * @author: yindy 2019年5月20日 下午1:08:59
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String  csdcPage(){
		return "sys/csdcInfo/csdcInfoBaseList";
	}
	
	/**
	 * 查询中登接口基本信息   
	 * @Title: csdcInfoBaseList   
	 * @author: yindy 2019年5月20日 下午2:21:57
	 * @param: @param page
	 * @param: @param rows
	 * @param: @param sort
	 * @param: @param order
	 * @param: @param request
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/csdcInfoBaseList")
	@ResponseBody
	public PageInfo csdcInfoBaseList(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(csdcInterfaceService.csdcInfoBaseList(pageInfo,condition));
		return pageInfo;
	}
	
	
	/**
	 * 新增 查看和编辑页面   
	 * @Title: viewAndEditPage   
	 * @author: yindy 2019年5月22日 上午9:33:15
	 * @param: @param model
	 * @param: @param interfaceInfo
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/viewAndEditPage", method = RequestMethod.GET)
	public String viewAndEditPage(Model model,long iiId,String type){
		InterfaceInfo interfaceInfo = csdcInterfaceService.queryCsdcBaseInfoByPrimary(iiId);
		LOGGER.info("查看的接口信息为："+interfaceInfo);
		model.addAttribute("entity",interfaceInfo);
		model.addAttribute("type",type);
		return "sys/csdcInfo/csdcBaseEditAndView";
	}
	
	
	/**
	 * 保存新增和编辑结果   
	 * @Title: saveCsdcBaseInfoEdit   
	 * @author: yindy 2019年5月22日 下午1:25:10
	 * @param: @param interfaceInfo
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/saveCsdcBaseInfoEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object saveCsdcBaseInfoEdit(InterfaceInfo interfaceInfo){
		LOGGER.info("修改后的接口信息为："+interfaceInfo.toString());
		String userName = getLoginName();
		String msg = csdcInterfaceService.insertOrUpdateCsdcBaseInfo(interfaceInfo,userName);
		return resultSuccess(msg);
	}
	
	
}
