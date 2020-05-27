package com.sams.business.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.utils.MyMapUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.ExchangeReqTmpService;
import com.sams.batchfile.service.FundAccountReconCfmService;
import com.sams.common.constant.Const;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.mapper.AccountInfoMapper;
import com.sams.custom.mapper.AccountReqTmpMapper;
import com.sams.custom.mapper.DealInfoEditMapper;
import com.sams.custom.mapper.FundDividendCfmMapper;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.model.DealInfoEdit;
import com.sams.sys.model.SysUser;
import com.sams.custom.mapper.FundAccountReconCfmMapper;

@Controller
@RequestMapping(value = "/additional")
public class AdditionalController extends BaseController{
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(AdditionalController.class);
	
	@Autowired
	private  ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private  ExchangeReqTmpService exchangeReqTmpService;
	
	@Autowired
	private  InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private DealInfoEditMapper dealInfoEditMapper;
	
	@Autowired
	private FundAccountReconCfmMapper fundAccountReconCfmMapper;
	
	@Autowired
	private FundDividendCfmMapper  fundDividendCfmMapper;
	
	@Autowired
	private FundAccountReconCfmService fundAccountReconCfmService ;
	
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	@Autowired
	private AccountReqTmpMapper accountReqTmpMapper;
	
	/**
	 * 账户补录页面   
	 * @Title: accountAdditionalPage   
	 * @author: yindy 2019年6月11日 上午8:50:13
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/account")
	public String accountAdditionalPage(Model model,String channelCode){
		return "sys/business/detailInfo/accountAdditional";
	}
	
	
	
	/**
	 * 
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @return Map
	 * */
	@RequestMapping(value = "/getAdditionalInfo")
	@ResponseBody
	public PageInfo getChannelInfoData(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		if(condition.get("iveTransDate") == null  || StringUtils.isEmpty(MyMapUtils.getString(condition,"iveTransDate"))){
			condition.put("iveTransDate", DateUtils.getDate(DateUtils.FORMAT_STR_DATE10));
		}
		PageHelperUtils.startPage(pageInfo);
		pageInfo.setPageResult(dealInfoEditMapper.findInfoCondition(pageInfo,condition));
		return pageInfo;
	}
	
	/**
	 * 修改数据
	 * 
	 * @return
	 */
    @RequestMapping("/addSubmit")
    @ResponseBody
    public Object addSubmit(@RequestBody DealInfoEdit entity) {
    	SysUser user=getCurrentUser();
    	String colNewValue = entity.getIveNewColValue();
    	Integer length = Integer.valueOf(entity.getIveColLength());
    	if(colNewValue.length() > length){
    		return resultSuccess("修改的值的长度过长,重新修改!");
    	}else{
    		colNewValue = FileDataUtil.getParameterCN(colNewValue,length);
    		entity.setIveNewColValue(colNewValue);
    	}
    	entity.setIveCuser(user.getLoginName());
    	entity.setIveCtime(DateUtils.getOracleSysDate());
//    	entity.setIveUuser(" ");
    	entity.setIveValidFlag("00");
    	entity.setIveValidFlag(Const.BUSINESS_STUTAS_00);
    	String msg = "";
		int num = dealInfoEditMapper.updateByPrimaryKeySelective(entity);
		if(num > 0 ){
			msg = Const.ACTION_EDIT+"成功!";
		}
    	return resultSuccess(msg); 
    }
	
	/**
	 * 将数据更新到目标表
	 * 
	 * @return
	 */
    @RequestMapping("/updateSubmit")
    @ResponseBody
    public Object updateSubmit(String ciIds) {
    	String ciIdsa = ciIds.substring(1, ciIds.length()-1);
		String[] ciIdss = ciIdsa.split(",");
		String msg ="";
		for(int i=0;i<ciIdss.length;i++){
			Long ciId = Long.parseLong(ciIdss[i].toString());
			DealInfoEdit entity = dealInfoEditMapper.selectByPrimaryKey(ciId);
			Map<String,Object> inputMap =Maps.newHashMap();
			String tableName=entity.getIveTableName();
			inputMap.put("TABLENAME", tableName); 
			inputMap.put("COLCODE", entity.getIveColCode());
			inputMap.put("COLVALUE", entity.getIveNewColValue()==null ? "" : entity.getIveNewColValue());
			String condition="";
			if("D_EXCHANGE_REQ_TMP".equals(tableName)){
				condition=" ERT_CHANNEL_CODE = '"+entity.getIveChannelCode()+"' and trim(ERT_APP_SHEET_SERIAL_NO)='"+entity.getIveAppSheetSerialNo().trim()+"' and ERT_TRANS_DATE='"+entity.getIveTransDate()+"'";
			}else{
				condition=" ART_CHANNEL_CODE = '"+entity.getIveChannelCode()+"' and trim(ART_APP_SHEET_SERIAL_NO)='"+entity.getIveAppSheetSerialNo().trim()+"' and ART_TRANS_DATE='"+entity.getIveTransDate()+"'";
			}
			inputMap.put("CONDITION", condition);
			int num=dealInfoEditMapper.updateTagTable(inputMap);
			if(num==1){
				msg="更新成功";
			}else{
				msg="更新失败";
			}
		}
    	return resultSuccess(msg); 
    }
    
    //
    @RequestMapping("/checkSubmit")
    @ResponseBody
    public Object checkSubmit(String ciIds) {
    	String userName = getLoginName();
    	String[] ciIdss = ciIds.replaceAll("&quot;", "").split(",");
    	List<DealInfoEdit> check = new ArrayList<DealInfoEdit>();
    	//查询
    	String msg = "复核成功！";
    	List<DealInfoEdit> list = dealInfoEditMapper.selectinfoByIds(ciIdss);
    	for (DealInfoEdit dealInfoEdit : list) {
    		if(Const.BUSINESS_STUTAS_01.equals(dealInfoEdit))continue;
			if(userName.equals(dealInfoEdit.getIveCuser())){
				msg = "修改和复核人不能为同一个！";
			}else{
				DealInfoEdit dto = new DealInfoEdit();
				dto.setIveId(dealInfoEdit.getIveId());
				dto.setIveUuser(userName);
				dto.setIveValidFlag(Const.BUSINESS_STUTAS_01);
				check.add(dto);
			}
		}
    	//复核
    	if(check.size() > 0){
    		int count = dealInfoEditMapper.checkInfo(check);
    	}
    	return resultSuccess(msg);
    }
    
    
    @RequestMapping("/accountReconPage")
	public String accountReconPage(){
		return "sys/business/detailInfo/accountRecon";
	}
    
    /**
     * 查询对账数据   
     * @Title: queryAccontReconInfo   
     * @author: yindy 2020年3月17日 下午4:00:08
     * @param: @param page
     * @param: @param rows
     * @param: @param sort
     * @param: @param order
     * @param: @param request
     * @param: @return      
     * @return: PageInfo      
     * @throws
     */
    @RequestMapping(value = "/queryAccontReconInfo")
	@ResponseBody
    public PageInfo queryAccontReconInfo(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		PageHelperUtils.startPage(pageInfo);
		pageInfo.setPageResult(fundAccountReconCfmMapper.queryAccontReconInfo(pageInfo,condition));
		return pageInfo;
    }
    
    @RequestMapping("/fundDividendPage")
	public String fundDividendPage(){
		return "sys/business/detailInfo/fundDividend";
	}
    
    /**
     * 查询分红数据  
     * @Title: queryfundDividendInfo   
     * @author: yindy 2020年3月17日 下午3:59:43
     * @param: @param page
     * @param: @param rows
     * @param: @param sort
     * @param: @param order
     * @param: @param request
     * @param: @return      
     * @return: PageInfo      
     * @throws
     */
    @RequestMapping(value = "/queryfundDividendInfo")
	@ResponseBody
    public PageInfo queryfundDividendInfo(Integer page, Integer rows,
			String sort, String order, HttpServletRequest request){
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		PageHelperUtils.startPage(pageInfo);
		pageInfo.setPageResult(fundDividendCfmMapper.queryfundDividendInfo(pageInfo,condition));
		return pageInfo;
    }
    
    /**
     * 手动备份数据  
     * @Title: test   
     * @author: yindy 2020年3月17日 下午1:18:03
     * @param: @param monthNum
     * @param: @param channelCode
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    @RequestMapping(method = RequestMethod.POST,value = "/backUpData")
    @ResponseBody
    public Object backUpData(int monthNum,String channelCode) throws Exception{
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("MONTHNUM", "-"+monthNum);
		map.put("CHANNELCODE", channelCode);
		fundAccountReconCfmService.backUpAccountRecon(map);
		LOGGER.info("{}渠道，手动备份对账数据，保留{}个月数据。",channelCode,monthNum+1);
    	return resultSuccess("");
    }
    
    
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test() throws Exception{
    	return "";
    }
    
    
    /**
     * 确认数据取值sql列表   
     * @Title: lookSql   
     * @author: yindy 2020年5月20日 下午4:05:00
     * @param: @param model
     * @param: @return
     * @param: @throws Exception      
     * @return: Object      
     * @throws
     */
    @RequestMapping(value = "/lookSql")					
    public Object lookSql(Model model) throws Exception{
    	return "sys/business/sql/transQueryForSql";				
    }					

}