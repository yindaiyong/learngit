package com.sams.custom.mapper;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ProductSalePrarms;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProductSalePrarmsMapper {
    int deleteByPrimaryKey(BigDecimal pspId);

    int insert(ProductSalePrarms record);

    int insertSelective(ProductSalePrarms record);

    ProductSalePrarms selectByPrimaryKey(BigDecimal pspId);

    int updateByPrimaryKeySelective(ProductSalePrarms record);

    int updateByPrimaryKey(ProductSalePrarms record);

	ProductSalePrarms queryProductParamInfo(Map<String, Object> query);
	
	ProductSalePrarms selectProSalePrarmsById(@Param("ID")BigDecimal id);

	

	Integer queryCountSubChannelByCurrencyType(@Param("CHANNELCODE")String channelCode,
			@Param("FUNDCODE")String fundCode, @Param("CURRENCYTYPE")String currencyType);

	String queryBuyTradeChannel(@Param("CHANNELCODE")String channelCode,
			@Param("TRANSACTIONACCOUNTID")String transactionAccountid, @Param("INCONTRACT")String inContract);
	
	List<ProductSalePrarms> findProductSalePrarmsCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	public int countProductSalePrarms(Map<String,Object> qryMap);

	String queryFirstCurrencyType(@Param("CHANNELCODE")String channelCode, @Param("TRANSACTIONACCOUNTID")String transactionAccountid, @Param("INCONTRACT")String inContract);
}