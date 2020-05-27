package com.sams.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.utils.MyMapUtils;
import com.sams.common.utils.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sams.batchfile.service.ContractService;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;

@Controller
@RequestMapping("/elContract")
public class ElContractController {
	
	@Autowired
	private ContractService contractService;

	/**
	 * 电子合同信息页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/business/elContract";
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
	@RequestMapping(value = "/getElContractData")
	@ResponseBody
	public PageInfo getElContractData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request,String tradeDate) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.containsKey("PRODUCTCODE")){
			condition.put("PRODUCTCODE", MyMapUtils.getStringArrayBySplit(condition,"PRODUCTCODE","-")[0]);
		}
		if(condition.containsKey("ECTRANSDATE")){
			condition.put("ECTRANSDATE", MyMapUtils.getStringNotNull(condition,"ECTRANSDATE").replaceAll("-", ""));
		}
		pageInfo.setPageResult(contractService.getElContractData(pageInfo, condition));
		return pageInfo;
	}
}
