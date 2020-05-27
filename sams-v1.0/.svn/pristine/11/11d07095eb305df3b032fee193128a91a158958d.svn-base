package com.sams.business.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.AccountReqTmpService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.mapper.ContractMapper;
import com.sams.custom.mapper.ExchangeReqTmpMapper;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.model.SysUser;

@Controller
@RequestMapping("/fileDataUpdate")
public class FileDataUpdateController extends BaseController{
	
	@Autowired
	private AccountReqTmpService accountReqTmpService;
	
	@Autowired
	private ExchangeReqTmpMapper exchangeReqTmpMapper;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper; 
	
	@Autowired
	private ContractMapper contractMapper; 

	/**
	 * 交易失败数据修改页面   
	 * @Title: queryTransFailedPage   
	 * @author: wangchao 2019年9月24日 下午2:00:25
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value= "/transFileData" , method = RequestMethod.GET)
	public String  queryTransFailedPage(Model model){
		model.addAttribute("type","1");
		return "sys/business/detailInfo/transDataUpdate";
	}
	
	/**
	 * 账户失败数据修改页面   
	 * @Title: queryTransFailedPage   
	 * @author: wangchao 2019年9月24日 下午2:00:25
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value= "/accountFileData" , method = RequestMethod.GET)
	public String  queryAccountFailedPage(Model model){
		model.addAttribute("type","0");
		return "sys/business/detailInfo/accountDataUpdate";
	}
	
	/**
	 * 账户、交易失败数据查询
	 * @Title: queryFalseTransData   
	 * @author: wangchao 2019年9月24日 下午2:05:25
	 * @param: @return      
	 * @return: PageInfo      
	 * @throws
	 */
	@RequestMapping(value = "/queryFalseFileData")
	@ResponseBody
	public PageInfo  queryFalseFileData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(accountReqTmpService.queryFalseFileData(pageInfo,condition));
		return pageInfo;
	}
	
	
	/**
	 * 账户、交易失败数据通过 
	 * @Title: updateFileData   
	 * @author: wangchao 2019年9月24日 下午2:00:25
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value= "/updateFileData" , method = RequestMethod.POST)
	@ResponseBody
	public Object  updateFileData(@Param("Ids")String Ids,@Param("type")String type){
		String msg = "";
		int count = 0;
		SysUser user=getCurrentUser();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		//去除掉参数Ids的[]
		String stringId = Ids.substring(1, Ids.length()-1);
		String[] argsId = stringId.split(",");
		//拼接所有需要修改的参数信息
		for(int i=0;i<argsId.length;i++){
			Map<String,Object> paramMap = Maps.newHashMap();
			paramMap.put("ID", argsId[i]);//id
			paramMap.put("USER", user.getLoginName());//操作人
			paramMap.put("SYSDATE", getSysDate());//操作时间
			list.add(paramMap);
		}
		
		if(Const.TransDataFlag.equals(type)){
			//交易申请数据更新
			count = accountReqTmpService.updateTransData(list);
		}else if(Const.AccountDataFlag.equals(type)){
			//账户申请数据更新
			count = accountReqTmpService.updateAccountData(list);
		}
		if(count == Const.updateSuccess){
			msg="操作成功";
		}else{
			msg="操作失败";
		}
		return resultSuccess(msg); 
	}
	
	/**
	 *交易数据回退
	 * @Title: returnBackFileData   
	 * @author: wangchao 2020年2月5日 下午2:00:25
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value= "/returnBackTransData" , method = RequestMethod.POST)
	@ResponseBody
	public Object  returnBackTransData(@Param("ID")String Ids){
		String msg = "";
		SysUser user=getCurrentUser();
		
		
		//去除掉参数Ids的[]
		String stringId = Ids.substring(1, Ids.length()-1);
		String[] argsId = stringId.split(",");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<argsId.length;i++){
			Map<String,Object> updateMap = Maps.newHashMap();
			Map<String,Object> transDataMap = exchangeReqTmpMapper.getExchangeTmpById(argsId[i]);
			updateMap.put("CHANNELCODE", MapUtils.getString(transDataMap, "CHANNELCODE",""));
			updateMap.put("BUSINESSDATE", MapUtils.getString(transDataMap, "BUSINESSDATE",""));
			updateMap.put("INCONTRACT", MapUtils.getString(transDataMap, "INCONTRACT",""));
			updateMap.put("TRANSACTIONACCOUNTID", MapUtils.getString(transDataMap, "TRANSACTIONACCOUNTID",""));
			updateMap.put("ERRORDEC", "手动置失败");
			updateMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			updateMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
			updateMap.put("ID", argsId[i]);
			list.add(updateMap);
		}

        //修改产品信息
		int exchangeInt = exchangeReqTmpMapper.upReturnBackExchangeData(list);

		//修改合同信息
		contractInfoMapper.upReturnBackContract(list);
		
		//修改电子合同信息
		contractMapper.upReturnBackElContract(list);
		
		if(exchangeInt == Const.updateSuccess){
			msg="回退成功";
		}else{
			msg="回退失败";
		}
		return resultSuccess(msg); 
	}
	
}
