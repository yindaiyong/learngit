package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.FundMarketCustom;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.result.MarketHandingResult;
import com.sams.sys.model.result.SysRoleVo;
import com.sitco.yt.manage.models.D_account_position;

public interface FundMarketService  {

	public Map<String,Object> selectByPiIpoBeginDate(Map<String,String> inputMap);
	
	public String selectGtLimit(D_account_position dAccountPosition);
	
	public void changeProductInfoFlag(Map<String, String> inputMap);
	
	public List<String> selectAllUsedChannels(String tradeDate);
	
	public List<String> selectAllUpdateChannels(String tradeDate);
	
	public long selectByConditionCount(Map<String,Object> map);
	
	public int updateCodeAndType(List<FundMarketCustom> list);
	
	PProductInfo selectByProductCode(String productCode);
	
	List<PProductInfo> selectByProductCodeList(Map<String,Object> map);

	
	public Map<String, Object> selectPfGslFundCodeInCome(Map<String, Object> map);
	
	public Map<String, Object> getIncomeInfo(Map<String, Object> map);
	
	public Map<String,Object> dataProcessing(Map<String,String> map);
	
	public Map<String,Object> selectFundMarketInfo(Map<String,Object> map);
	
	public Map<String,Object> updateSendStatus(Map<String, Object> map);

}
