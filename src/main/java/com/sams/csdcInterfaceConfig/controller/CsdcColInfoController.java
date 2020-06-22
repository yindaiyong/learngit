package com.sams.csdcInterfaceConfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sams.common.constant.Const;
import com.sams.common.utils.ExportExcelUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.csdcInterfaceConfig.service.CsdcInterfaceService;
import com.sams.custom.model.InterfaceColInfo;
/**
 * @ClassName:  CsdcColInfoController   
 * @Description:中登接口字段信息   
 * @author: yindy
 * @date:   2019年5月22日 下午6:07:21   
 *
 */
@Controller
@RequestMapping(value = "/csdcCol")
public class CsdcColInfoController extends BaseController{
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(CsdcColInfoController.class);
	
	@Autowired
	private CsdcInterfaceService csdcInterfaceService;

	@RequestMapping(method = RequestMethod.GET)
	public String  csdcColPage(){
		return "sys/csdcInfo/csdcColInfoList";
	}
	
	/**
	 * 接口字段信息列表  
	 * @Title: csdcInfoBaseList   
	 * @author: yindy 2019年5月23日 上午8:09:37
	 * @param: @param page
	 * @param: @param rows
	 * @param: @param sort
	 * @param: @param order
	 * @param: @param request
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/csdcColInfoList")
	@ResponseBody
	public PageInfo csdcInfoBaseList(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(csdcInterfaceService.csdcInfoColList(pageInfo,condition));
		return pageInfo;
	}
	
	/**
	 * 新增 编辑 查看 页面   
	 * @Title: viewAndEditPage   
	 * @author: yindy 2019年5月23日 上午8:10:13
	 * @param: @param model
	 * @param: @param icId
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value = "/viewAndEditPage", method = RequestMethod.GET)
	public String viewAndEditPage(Model model,long icId,String type,String icInterfaceCode){
		InterfaceColInfo colInfo = csdcInterfaceService.queryCsdcColInfoByPrimary(icId);
		model.addAttribute("type",type);
		model.addAttribute("entity",colInfo);
		model.addAttribute("icInterfaceCode", icInterfaceCode);
		return "sys/csdcInfo/csdcColEditAndView";
	}
	
	@RequestMapping(value = "/saveCsdcColInfoEdit", method = RequestMethod.POST)
	@ResponseBody
	public Object saveCsdcColInfoEdit(InterfaceColInfo interfaceColInfo){
		LOGGER.info("保存的接口字段信息为："+interfaceColInfo);
		String currentName = getLoginName();
		LOGGER.info("当前操作用户为："+currentName);
		String msg = csdcInterfaceService.insertOrUpdateCsdcColInfo(interfaceColInfo,currentName);
		return resultSuccess(msg);
	}
	
	
	/**
	 * 导出中登接口字段信息   
	 * @Title: exportCsdcColInfo   
	 * @author: yindy 2019年5月28日 上午9:03:31
	 * @param: @param iiInterfaceCode
	 * @param: @param response
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/exportCsdcColInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object exportCsdcColInfo(String iiInterfaceCode,HttpServletResponse response){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("iiInterfaceCode", iiInterfaceCode);
		PageInfo page = new PageInfo();
		page.setSort("ic_utime");
		List<InterfaceColInfo> colInfo = csdcInterfaceService.csdcInfoColList(page,condition);
		LOGGER.info("导出的数据为数量为："+(colInfo == null ? 0: colInfo.size()));
		if(!CollectionUtils.isEmpty(colInfo)){
			String fileName = colInfo.get(0).getIiInterfaceName();
			new ExportExcelUtils().wirteExcel(response, fileName+Const.fileName, Const.titleColumn, Const.titleName, Const.titleSize, colInfo, fileName);
		}
		return resultSuccess("");
	}
	
}
