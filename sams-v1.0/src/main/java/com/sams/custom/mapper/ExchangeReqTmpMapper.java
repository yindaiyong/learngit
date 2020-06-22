package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ExchangeReq;
import com.sams.custom.model.PchannelInfo;

public interface ExchangeReqTmpMapper {
	int deleteTransTmpDate(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	int deleteTransReqDate(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	int deleteTransCfmDate(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	
	int selectTransTmpCount(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	int selectTransReqCount(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	int selectTransCfmCount(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	
	int insertByBatchTransTmp(List<Map<String,Object>> TransTmpList);
	int insertByBatchTransReq(List<Map<String,Object>> TransReqList);
	int insertByBatchTransCfm(List<Map<String,Object>> TransCfmList); 
	
	void updatePrementDataStat();
	
	
	int batchUpdateNotExchangeReqData(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	
	int batchUpdateAppSheetSerialNoRepeat(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	
	int batchUpdateExchangeReqData(@Param(value = "list") List<Map<String,Object>> attachmentTables);
	
	
	int updateExchangeReqData(Map<String,Object> updateMap); 
	
	int selectFirstTradeCount(Map<String,Object> inputMap); 
	
	List<Map<String,Object>> selectTransTmpInfo(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	
	List<Map<String,Object>> selectTransReqInfo(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE,@Param("BUSINESSCODE")String businessCode);
	
	List<ExchangeReq> findTnDataGrid(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	List<Map<String,Object>> selectTransReqSendTaData(Map<String, Object> map);
	
	List<Map<String,Object>> getTNSmallContractsBeTraded(@Param("CHANNELCODE")String channelCode,@Param("TRANSDATE")String TRANSDATE);
	
	List<Map<String,Object>> selectPreMentTradeInfo022(Map<String, Object> map);
	int selectPreMentTradeInfo022Count(Map<String, Object> map);
	
	List<Map<String,Object>> selectPreMentTradeInfo024(Map<String, Object> map);
	int selectPreMentTradeInfo024Count(Map<String, Object> map);
	
	List<Map<String,Object>> selectPreMentTradeInfoError(Map<String, Object> map);
	List<Map<String,Object>> selectPreMentTradeInfo020(Map<String, Object> map);
	
	List<Map<String,Object>> selectSubscribeResult(Map<String, Object> map);
	
	Map<String,Object> selectConfirmInfo(@Param("TASERIALNO")String TASerialNo);
	
	List<Map<String,Object>> selectRedeemResult(Map<String, Object> map);
	
	List<Map<String,Object>> selectTransEndandDivi(Map<String, Object> map);
	
	String selectDatabaseTime();
	
	List<Object> queryFailedInfo(@Param("pageInfo") PageInfo pageInfo,@Param("condition")Map<String, Object> condition);
	
	List<Object> queryTransFailedInfo(@Param("pageInfo") PageInfo pageInfo,@Param("condition")Map<String, Object> condition);
	
	String selectPaperExchangeVol(@Param("FUNDCODE")String fundCode,@Param("ACCOUNTID")String accountId, @Param("TRANSDATE") String transDate);
	
	List<String> selectTransLastDateTaSerialNo(Map<String, Object> map);
	
	int selectCheckTaTconfirmData(Map<String, Object> map);
	
	int selectCheckExchangereqData(Map<String, Object> map);
	
	int selectCheckBusinessCodeCount(Map<String, Object> map);
	
	int updatePrementData(Map<String, Object> map);
	
    int updateTransData(List<Map<String,Object>> list);
    
	List<Map<String, Object>> select022024TradeCfmList(Map<String, Object> queryMap);
	
	int select022024TradeCount(Map<String, Object> queryMap);
	
	List<Map<String, Object>> selectSubscribeGQResult(
			Map<String, Object> map);
	int updateIsSetUpForFundType(Map<String, Object> map);
	
	int select130cfmDiffValueCount(Map<String, Object> queryMap);
	
	List<Map<String, Object>> queryEx022024TradeCfmList(
			
			Map<String, Object> queryMap);
	List<Map<String, Object>> selectmultiCurrencyResult(
			Map<String, Object> selectMap);
	
	int queryEx020024TradeCount(Map<String, Object> queryMap);
	List<Map<String, Object>> queryMuTradeCfmList(Map<String, Object> queryMap);
	
	List<Map<String, Object>> query024CfmData(Map<String, Object> selectMap);
	
	int batchUpdateMultipToFailed(List<Map<String, Object>> updateList);
	
	List<String> queryMultipleRedeemFund(Map<String, Object> queryMap);
	
	List<Map<String, Object>> queryOneAccoMoreTrans(Map<String, Object> queryMap);
	
	List<Map<String, Object>> queryAllTransList(Map<String, Object> queryMap);
	
	List<Map<String, Object>> getTransDate024Exchange(Map<String, Object> qryMap);
	
	List<Map<String, Object>> get024ExchangeByTaAccountId(Map<String, Object> qryMap);
	
	public String getSumVol024Exchange(Map<String, Object> qryMap);
	
	Map<String, Object> getExchangeTmpById(@Param("ID")String Id);
	
	int upReturnBackExchangeData(List<Map<String,Object>> list);
	
	Map<String, Object> query052OriginalTrans(
			Map<String, Object> transactionTemp);
	
	String query052OriginalRequestNo(Map<String, Object> originalMap);
	
	int selectCurrencyFTransCount(Map<String, Object> query);

    int updateBusinessCodeForpartialAllotment(Map<String, Object> queryMap);
    
	int queryCfmCount(Map<String, Object> selectMap);
	
	List<Map<String, Object>> selectFirstTransInfo(@Param("CHANNELCODE")String channelCode,
			@Param("TRANSDATE")String transDate);
	
	List<Map<String, Object>> query142150ConfirmData(
			Map<String, Object> selectMap);
}