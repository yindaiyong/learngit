package com.sams.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.utils.MyMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.ExchangeReqTmpService;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.mapper.ExchangeReqTmpMapper;

@Controller
@RequestMapping("/productAssignment")
public class TNProductAssController  extends BaseController{

	@Autowired
	private ExchangeReqTmpMapper exchangeReqTmpMapper;
	
	@Autowired
	private ExchangeReqTmpService exchangeReqTmpService;
	
	
	/**
	 * T+N产品分配页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/business/productAssignment";
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
	@RequestMapping(value = "/getProductAssignmentData")
	@ResponseBody
	public PageInfo getProductAssignmentData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request,String tradeDate) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		String erTransactionDate=condition.get("erTransactionDate")==null? DateUtils.getDate(DateUtils.FORMAT_STR_DATE8):MyMapUtils.getStringNotNull(condition,"erTransactionDate").replaceAll("-", "");
//		if(!erTransactionDate.equals("")){
			condition.put("erTransactionDate", erTransactionDate);
			pageInfo.setPageResult(exchangeReqTmpService.findDataGrid(pageInfo,condition));
//		}
		return pageInfo;
	}
	
	/**
	 * 确认分配  
	 * @Title: queryTaFundInfoComBox   
	 * @author: wangchao 2019年6月18日 上午11:29:46
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@RequestMapping(value = "/certainAssign", method = RequestMethod.POST)
	@ResponseBody
	public Object certainAssign(String taChannelCode,String erId,String batchNumber){
		Map<String,Object> updateMap = Maps.newHashMap();
		updateMap.put("TAPRODUCTCODE", taChannelCode);
		updateMap.put("ERID", erId);
		updateMap.put("BATCHNUMBER", batchNumber);
		exchangeReqTmpMapper.updateExchangeReqData(updateMap);
		return resultSuccess("success");
	}
	
	
}
