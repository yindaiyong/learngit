package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.custom.model.AccountStat;

public interface ContractInfoMapper {
    Map<String,Object> selectContractInfo(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectContractList(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectContractInfoList(@Param("TAACCOUNTID")String TAAccountId,@Param("FUNDCODE")String fundCode,@Param("TAFUNDCODE")String taFundCode,@Param("CHANNELCODE")String channelCode,@Param("TRANSACTIONACCOUNT")String transActionAccount,@Param("INCONTRACT")String inContract,@Param("OUTCONTRACT")String outContract);
    
    int insertContractInfo(Map<String,Object> inputMap);
     
    int updateContractFlag(Map<String,Object> inputMap);
    
    int updateContractFlagAndMsg(@Param(value = "list") List<Map<String,Object>> attachmentTables);
    
    List<Map<String,Object>> selectFixedIncome(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectFixedIncomeConut(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectRubyIncome(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectRubyIncomeCount(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectCashFixedIncome(Map<String,Object> inputMap);
    
    List<Map<String,Object>> selectCashFixedIncomeCount(Map<String,Object> inputMap);
    
	void upContractFlagForCloseAcco(AccountStat localStatus);
	
	Map<String, String> selectFirstTradeContractInfo(Map<String,Object> inputMap);
	
	List<String> selectSalesFund(Map<String,Object> inputMap);
	
	List<String> selectSalesFundDividend(Map<String,Object> inputMap);
	
	List<String> selectSalesFund123(Map<String,Object> inputMap);
	
	List<String> selectInContracts(Map<String,Object> inputMap);
	
	int deleteContractInfo(@Param("CHANNELCODE")String channelCode,@Param("BUSINESSDATE")String businessDate);

    List<Map<String,Object>> selectFixedIncomeGQ(Map<String,Object> inputMap);

	List<Map<String, Object>> selectmultiCurrencyIncome(
			Map<String, Object> fixedIncomeParam);

	int upReturnBackContract(List<Map<String, Object>> list);
	
	int updateContractForFof(List<Map<String, Object>> list);


	String querySumAppliCationVol(Map<String, Object> tempAccount);

	int update052Contract(Map<String, Object> map);

	int select052TransIsFirst(Map<String, Object> map);

	List<String> queryCurrencyFFund(Map<String, Object> query);

	List<Map<String, Object>> queryCurrencyFDividend(
			Map<String, Object> fixedIncomeParam);

	void updateContractFlagAndMsgforF(List<Map<String, Object>> tradeInfoList);
}