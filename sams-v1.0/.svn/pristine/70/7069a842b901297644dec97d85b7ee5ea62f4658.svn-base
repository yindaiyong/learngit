package com.sams.batchfile.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.service.ProductSalePrarmsService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductSalePrarms;
import com.sams.custom.mapper.ProductSalePrarmsMapper;

@Service
public class ProductSalePrarmsServiceImpl  implements ProductSalePrarmsService{

	@Autowired
	private ProductSalePrarmsMapper  productSalePrarmsMapper;
	@Override
	public ProductSalePrarms selectByKey(Object key) {
		return productSalePrarmsMapper.selectByPrimaryKey(new BigDecimal(key.toString()));
	}

	@Override
	public int save(ProductSalePrarms entity) {
		return productSalePrarmsMapper.insert(entity);
	}

	@Override
	public int delete(Object key) {
		return productSalePrarmsMapper.deleteByPrimaryKey(new BigDecimal(key.toString()));
	}

	@Override
	public int updateAll(ProductSalePrarms entity) {
		// TODO Auto-generated method stub
		return productSalePrarmsMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int updateNotNull(ProductSalePrarms entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductSalePrarms> selectByExample(Object example) {
		
		return null;
	}
	
	@Override
	public List<ProductSalePrarms> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return productSalePrarmsMapper.findProductSalePrarmsCondition(pageInfo,condition);
	}
	
	@Override
	public int countProductSalePrarms(Map<String,Object> qryMap){
		return productSalePrarmsMapper.countProductSalePrarms(qryMap);
	}
}
