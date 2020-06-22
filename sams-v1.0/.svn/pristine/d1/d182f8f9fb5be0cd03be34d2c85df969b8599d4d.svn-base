package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.ProductOpenDayService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ProductOpenDayMapper;
import com.sams.custom.model.ProductOpenDay;

@Service
public class ProductOpenDayServiceImpl implements ProductOpenDayService{
	
	private static Logger LOGGER = LoggerFactory
			.getLogger(FundMarketProcessorServiceImpl.class);
	
	@Autowired
	private ProductOpenDayMapper productOpenDayMapper;
	
	/**
	 * 开放日数据查询
	 * @Title: execute   
	 * @author: wangchao 2019年11月20日 下午2:21:25
	 * @param: @param PageInfo pageInfo
	 * @param: @param  Map<String, Object> condition  
	 * @return:  List<ProductOpenDay>
	 */
	@Override
	public List<ProductOpenDay> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return productOpenDayMapper.findDataGrid(pageInfo, condition);
	}

	@Override
	public ProductOpenDay selectByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(ProductOpenDay entity) {
		return productOpenDayMapper.insert(entity);
	}

	@Override
	public int delete(Object key) {
		return productOpenDayMapper.deleteProOpenDay((Map<String,Object>) key);
	}

	@Override
	public int updateAll(ProductOpenDay entity) {
		return productOpenDayMapper.updateProOpenDay(entity);
	}

	@Override
	public int updateNotNull(ProductOpenDay entity) {
		
		return 0;
	}

	@Override
	public List<ProductOpenDay> selectByExample(Object example) {
		return productOpenDayMapper.qryProOpenDayList((Map<String,Object>)example);
	}

	@Override
	public int updateAllProOpenDay(List<ProductOpenDay> proOpenDayList) {
		return productOpenDayMapper.updateAllProOpenDay(proOpenDayList);
	}

	
	/**
	 * 判断日期是否是开放日
	 * @Title: checkTransDateIsOpenDay   
	 * @author: 王超 2020年1月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  productCode 产品代码  batchNo 批次号 tradeDate 交易日
	 * @return: Map<String, Object>      
	 */
	@Override
	public boolean checkTransDateIsOpenDay(Map<String, Object> qryMap) {
		String channelCode =MapUtils.getString(qryMap, "channelCode","");
		String productCode =MapUtils.getString(qryMap, "productCode",""); 
		String batchNo =MapUtils.getString(qryMap, "batchNo",""); 
		String tradeDate =MapUtils.getString(qryMap, "tradeDate",""); 
		boolean flag = false;
		
		List<ProductOpenDay> openDayList =  selectByExample(qryMap);
		
		if(null != openDayList && openDayList.size()>0){
			flag = false;
			LOGGER.info("渠道"+channelCode+batchNo+"批次产品"+productCode+tradeDate+"不是产品工作日");
		}else{
			flag=true;
			LOGGER.info("渠道"+channelCode+batchNo+"批次产品"+productCode+tradeDate+"是产品工作日");
		}
		return flag;
	}

	
	@Override
	public String checkOpenDayType(Map<String, Object> qryMap) {
		List<ProductOpenDay> openDayList =  selectByExample(qryMap);
		String openDayType = "";
		if(null != openDayList && openDayList.size()!=0){
			for(ProductOpenDay p:openDayList){
				openDayType = openDayType+p.getPoOpenDayType()+",";
			}
		}
		return openDayType;
	}

	/**
	 * 判断传入日期所在月份的非开放日天数
	 * @Title: selectMonthOpenDayCount   
	 * @author: 王超 2020年4月27日 下午2:45:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  productCode 产品代码  batchNo 批次号 tradeDate 交易日
	 * @return: Map<String, Object>      
	 */
	@Override
	public int selectMonthOpenDayCount(Map<String, Object> qryMap) {
		return productOpenDayMapper.selectMonthOpenDayCount(qryMap);
	}

}
