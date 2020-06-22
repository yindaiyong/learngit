package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import com.sams.common.utils.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.FundMarketCfmMapper;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.PchannelInfo;
@Service
public class FundMarketCfmServierImpl implements FundMarketCfmServier {
	
	@Autowired
	public FundMarketCfmMapper fundMarketCfmMapper;

	@Override
	public int deleteFundMarketCFM(FundMarketCfm fundMarketCfm) {
		// TODO Auto-generated method stub
		fundMarketCfmMapper=(FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
		return fundMarketCfmMapper.deleteFundMarketCFM(fundMarketCfm);
	}

	@Override
	public int insert(FundMarketCfm record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Map<String,Object> inputMap) {
		fundMarketCfmMapper=(FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
		return fundMarketCfmMapper.insertSelective(inputMap);
	}

	@Override
	public Map<String,Object> selectFundMarketCfm(FundMarketCfm fundMarketCfm) {
		Map<String,Object> retMap=Maps.newHashMap();
		retMap = checkFundMarketCfm(fundMarketCfm);
		if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
		return retMap;
	}
	
	public Map<String,Object> checkFundMarketCfm(FundMarketCfm fundMarketCfm){
		Map<String,Object> retMap=Maps.newHashMap();
		fundMarketCfmMapper=(FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
		FundMarketCfm fundmarketCfm = fundMarketCfmMapper.selectFundMarketCfm(fundMarketCfm).get(0);
		if(fundmarketCfm==null){
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("retMsg", "通过"+fundMarketCfm+"拿到的fundMarketCfm为空");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("fundMarketCfm", fundMarketCfm);
		}
		return retMap;
		
	}

	@Override
	public int updateByPrimaryKey(FundMarketCfm fundMarketCfm) {
		fundMarketCfmMapper=(FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
		return fundMarketCfmMapper.updateByPrimaryKey(fundMarketCfm);
	}
	
	
	@Override
	public int updateByChannelCode(FundMarketCfm fundMarketCfm) {
		fundMarketCfmMapper=(FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
		return fundMarketCfmMapper.updateByChannelCode(fundMarketCfm);
	}


	@Override
	public int updateSendStatus(FundMarketCfm fundMarketCfm) {
		fundMarketCfmMapper=(FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
		return fundMarketCfmMapper.updateSendStatus(fundMarketCfm);
	}

	@Override
	public String fundCfmJudgeTrade(Map<String, Object> map) {
		String fundStatus = fundMarketCfmMapper.selectFundStatus(map);
		return fundStatus;
	}

	@Override
	public String selectHoldShares(Map<String, Object> inputMap) {
		return fundMarketCfmMapper.selectHoldShares(inputMap);
	}

	@Override
	public FundMarketCfm selectByPrimaryKey(Long fmId) {
		return fundMarketCfmMapper.selectByPrimaryKey(fmId);
	}

	@Override
	public FundMarketCfm selectFundMarketCfmInfo(FundMarketCfm fundMarketCfm) {
		return fundMarketCfmMapper.selectFundMarketCfmInfo(fundMarketCfm);
	}

	@Override
	public List<Map<String, Object>> findMarketDetailCondition(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return fundMarketCfmMapper.findMarketDetailCondition(pageInfo, condition);
	}

	/**
	 * @Description 获取行情数据信息
	 * @Author weijunjie
	 * @Date 2020/4/29 11:26
	 **/
	public List<FundMarketCfm> selectFundMarketInfo(PageInfo pageInfo, Map<String, Object> condition){
		PageHelperUtils.startPage(pageInfo);
        String transDate = MapUtils.getString(condition, "transDate","");
        if(StringUtils.isNotBlank(transDate)){
            String t = transDate.replaceAll("-", "");
            condition.put("transDate",t);
        }
        return fundMarketCfmMapper.selectFundMarketInfo(pageInfo, condition);
	}
}