package com.sams.custom.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.result.ChannelProductInfo;
import com.sams.custom.model.result.ChannelProductResult;

public interface ChannelProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_PRODUCT
     *
     * @mbggenerated Fri May 17 16:39:19 CST 2019
     */
    int deleteByPrimaryKey(Long cpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_PRODUCT
     *
     * @mbggenerated Fri May 17 16:39:19 CST 2019
     */
    int insert(ChannelProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_PRODUCT
     *
     * @mbggenerated Fri May 17 16:39:19 CST 2019
     */
    ChannelProduct selectByPrimaryKey(Long cpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_PRODUCT
     *
     * @mbggenerated Fri May 17 16:39:19 CST 2019
     */
    List<ChannelProduct> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_PRODUCT
     *
     * @mbggenerated Fri May 17 16:39:19 CST 2019
     */
    int updateByPrimaryKey(ChannelProduct record);
    
    List<ChannelProductInfo> findChannelProductCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    public ChannelProductInfo selectChannelProductInfo(String cpId);
    
    public List<ChannelProduct> selectProductByChannel(Map<String,Object> map);
    
    int selectChannelProductCount(String channelCode);
    
    int selectProductCount(String channelCode);
    
    List<ChannelProduct> selectChannelProduct(Map<String, Object> map);
    
    int updateChannelProduct(List<Map<String,Object>> list);

    ChannelProduct selectCodeByName(@Param("productName") String productName);
    
}