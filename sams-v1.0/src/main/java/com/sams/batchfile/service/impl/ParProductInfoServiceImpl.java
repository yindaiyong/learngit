package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import com.sams.common.exception.ExceptionConStants;
import com.sams.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.ParProductInfoService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.model.PProductInfo;

@Service
public class ParProductInfoServiceImpl implements ParProductInfoService{

	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Autowired
	public SysDictService sysDictService;

	@Override
	public PProductInfo selectByKey(Object key) {
		Long piId = Long.parseLong(key.toString());
		return productInfoMapper.selectByPrimaryKey1(piId);
	}

	@Override
	public int save(PProductInfo entity) {
		return productInfoMapper.insert1(entity);
	}

	@Override
	public int delete(Object key) {
		Long piId = Long.parseLong(key.toString());
		return productInfoMapper.deleteByPrimaryKey1(piId);
	}

	@Override
	public int updateAll(PProductInfo entity) {
		return productInfoMapper.updateByPrimaryKey1(entity);
	}

	@Override
	public int updateNotNull(PProductInfo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PProductInfo> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PProductInfo> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return productInfoMapper.findPProductInfoCondition(pageInfo,condition);
	}

	@Override
	public PProductInfo selectByProductCode(String productCode) {
		return productInfoMapper.selectByProductCode(productCode);
	}
	
	@Override
	public List<PProductInfo> selectByProductCodeList(Map<String,Object> map) {
		return productInfoMapper.selectByProductCodeList(map);
	}

	@Override
	public Map<String, Object> updateAllAboutProduct(Map<String, Object> inputMap) {
		return null;
	}

	@Override
	public long selectByConditionCount(Map<String, Object> map) {
		return productInfoMapper.selectByConditionCount(map);
	}

	@Override
	public int selectProductCount(String channelProductCode) {
		return productInfoMapper.selectProductCount(channelProductCode);
	}

	@Override
	public int selectTaProductCount(String fundCode) {
		return productInfoMapper.selectTaProductCount(fundCode);
	}

	@Override
	public PProductInfo selectProductList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productInfoMapper.selectProductList(map);
	}

	@Override
	public int updateConnetBatchNo(String productCode,
			String batchNo,String newBatchNo) {
		return productInfoMapper.updateConnetBatchNo(productCode,batchNo,newBatchNo);
	}

	@Override
	public List<PProductInfo> selectBatchProductInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productInfoMapper.selectBatchProductInfo(map);
	}

	/**
	 * @Description 子渠道编号非法验证
	 * @Author weijunjie
	 * @Date 2020/1/17 11:16
	 **/
	@Override
	public String checkSubChannelCode(String channelCode,String subChannelCode){
		String msg = ExceptionConStants.retCode_0000;
		if(null != subChannelCode&& !"".equals(subChannelCode)) {
			Map<String, Object> channelCurrentMap = sysDictService.getChannelCurrent("channelCurrent");
			String channelCurrent = "";
			for (Map.Entry<String, Object> entry : channelCurrentMap.entrySet()) {
				channelCurrent += (channelCode + entry.getKey() + ",");
			}
			//字典表数据验证
			if (!channelCurrent.contains(subChannelCode)) {
				msg = channelCode + subChannelCode + "与字典表配置不一致";
			}
		}else{
			msg = "参数异常channelCode="+channelCode + "subChannelCode="+subChannelCode;
		}
		return msg;
	}

	/**
	 * @Description  子渠道信息录入时校验渠道中是否已有该子渠道，防止重复
	 * @Author wangchao
	 * @Date 2020/2/05 11:16
	 **/
	@Override
	public int countProductSubChannelCode(Map<String, Object> qryMap) {
		return productInfoMapper.countProductSubChannelCode(qryMap);
	}

}
