package com.sams.custom.mapper;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.FundVolDetailCfm;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FundVolDetailCfmMapper {
    int deleteByPrimaryKey(Long fvdId);

    int insert(FundVolDetailCfm record);

    int insertSelective(FundVolDetailCfm record);

    FundVolDetailCfm selectByPrimaryKey(Long fvdId);

    int updateByPrimaryKeySelective(FundVolDetailCfm record);

    int updateByPrimaryKey(FundVolDetailCfm record);

    /**
     * @Description 查询TA视图数据，获取份额明细表信息
     * @Author weijunjie
     * @Date 2020/1/7 15:30
     **/
    List<FundVolDetailCfm> getFundVolDetailFromTA(@Param("channelCode") String channelCode,
                                                        @Param("transDate") String transDate,
                                                        @Param("productTypes") String... productTypes);
    int insertByBatch(List<FundVolDetailCfm> record);

    int deleteByTransDate(@Param("channelCode") String channelCode,@Param("transDate") String transDate);

    /**
     * @Description 更新文件生成状态以及时间
     * @Author weijunjie
     * @Date 2020/1/8 13:57
     **/
    int updateGenerateStaAndTime(Map<String,Object> inputMap);

    /**
     * @Description 更新文件生成状态以及时间
     * @Author weijunjie
     * @Date 2020/1/8 13:57
     **/
    int updateSendStaAndTime(Map<String,Object> inputMap);

    List<FundVolDetailCfm> selectFundVolDetail(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	int checkRedeemAvailableVol(@Param("CHANNELCODE")String channelCode, @Param("TRANSDATE")String transDate,
			@Param("LASTDATE")String lastDate, @Param("NEXTDATE") String nextDate);

	int checkRedeemCfmTotalVol(@Param("CHANNELCODE")String channelCode, @Param("TRANSDATE")String transDate,
			@Param("LASTDATE")String lastDate);

	int querySetUpDataCount(@Param("CHANNELCODE")String channelCode, @Param("TRANSDATE")String transDate,
			@Param("PRODUCTTYPES")String[] productTypes);

	List<String> queryNotWorkAllowDate(@Param("CHANNELCODE")String channelCode, @Param("TRANSDATE")String transDate);

	void updateRelAllowDate(@Param("UPDATEDATELIST") List<Map<String, Object>> updateDateList);


}