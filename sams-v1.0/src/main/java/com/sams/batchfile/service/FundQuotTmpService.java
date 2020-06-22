package com.sams.batchfile.service;

import java.util.Map;

import com.sams.custom.model.FundMarketCustom;
import com.sams.custom.model.FundQuotTmp;

public interface FundQuotTmpService {
	
    int truncateFundQuotTmp();

    public Map<String, Object> insertFundQuotTmp(Map<String, Object> inputMap);
    
    public Map<String, Object> selectAllFundQuotTmp(Map<String, Object> inputMap);
    
    public Map<String, Object> getStatusBasedOnDate(String tradeDate,FundMarketCustom fundMarketCustom);
    
    public Map<String,Object> gtLimit(FundMarketCustom fundMarketCustom);
    
    public String getForwardDay(String tradeDate, String marketCode);
    
    public String getQuarterForwardDay(String tradeDate, String marketCode) ;

}
