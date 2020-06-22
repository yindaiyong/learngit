package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.sams.custom.model.FundMarketCustom;
import com.sitco.yt.manage.models.D_account_position;


public interface FundMarketMapper {
	FundMarketCustom selectfundMarketIpo (FundMarketCustom fundMarketCustom) throws Exception;

	FundMarketCustom selectfundMarketPro(FundMarketCustom fundMarketCustom) throws Exception;

	String selectGtLimit(D_account_position dAccountPosition);
	
	List<String> selectAllUsedChannels(String tradeDate);
	
	public Map<String, Object> selectPfGslFundCodeInCome(Map<String, Object> map);
	
	public Map<String, Object> getIncomeInfo(Map<String, Object> map);
	
	public List<String> selectAllUpdateChannels(String tradeDate);

	public Map<String,Object> selectFundMarketInfo(Map<String,Object> map);
	
	List<FundMarketCustom> selectFundMarketList(Map<String, Object> map);
}
