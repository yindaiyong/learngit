package com.sams.custom.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.ChannelInfo;
import com.sams.custom.model.PchannelInfo;

public interface PchannelInfoMapper {
	
	public PchannelInfo queryChannelInfoByChannelCode(@Param("ciChannelCode") String channelCode);

	public List<PchannelInfo> queryHandelChannelInfo(Map channelInfo);
	
	Map<String,Object> selectFundTypeByChannelCodeAndFundcode(@Param("CHANNELCODE") String channelCode,@Param("FUNDCODE") String fundCode,@Param("BUSINESSDATE")String businessDate);

	public String selectManagerCode(String channelCode);

	public List<Map<String, Object>> queryErrorDetail(Map<String, Object> map);
	
	List<PchannelInfo> findPchannelInfoCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_INFO
     *
     * @mbggenerated Tue May 14 13:49:05 CST 2019
     */
    int deleteByPrimaryKey(Long ciId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_INFO
     *
     * @mbggenerated Tue May 14 13:49:05 CST 2019
     */
    int insert(PchannelInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_INFO
     *
     * @mbggenerated Tue May 14 13:49:05 CST 2019
     */
    PchannelInfo selectByPrimaryKey(Long ciId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_INFO
     *
     * @mbggenerated Tue May 14 13:49:05 CST 2019
     */
    List<PchannelInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_CHANNEL_INFO
     *
     * @mbggenerated Tue May 14 13:49:05 CST 2019
     */
    int updateByPrimaryKey(PchannelInfo record);
    
    int selectByCode(String ciChannelCode);
    
    public PchannelInfo selectByChannelCode(Map<String,Object> map) ;

	public List<String> selectAllChannels();
	
	List<Map<String, Object>> queryChanneleNameComBox();
	
	List<Map<String, Object>> queryManagerNameComBox();
	
	List<Map<String, Object>> queryManagerPhoneComBox();
	
	List<Map<String, Object>> queryTAChannelInfo();

	public List<String> selectBatchSendChannel(@Param("date")String date,@Param("stopStep")String stopStep,@Param("errorCode")String errorCode);

	public List<PchannelInfo> querySend94File();

	public List querySend94FileChannel(@Param("channelCodeList")List channelCodeList, @Param("date")String date);
	
	
}