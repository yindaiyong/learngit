package com.sams.batchfile.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sams.batchfile.service.ProductTemplateService;
import com.sams.common.constant.Const;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ProductTemplateMapper;
import com.sams.custom.model.ProductTemplate;
import com.sams.custom.model.result.MarketHandingResult;

@Service
public class ProductTemplateServiceImpl implements ProductTemplateService{

	@Autowired
	private ProductTemplateMapper productTemplateMapper;
	@Override
	public ProductTemplate selectByKey(Object key) {
		Long ptId = Long.parseLong(key.toString());
		return productTemplateMapper.selectByPrimaryKey1(ptId);
	}

	@Override
	public int save(ProductTemplate entity) {
		return productTemplateMapper.insert1(entity);
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(ProductTemplate entity) {
		return productTemplateMapper.updateByPrimaryKey1(entity);
	}

	@Override
	public int updateNotNull(ProductTemplate entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductTemplate> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProductTemplate> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return productTemplateMapper.findProductTemplateCondition(pageInfo,condition);
	}

	/*@Override
	public List<ProductTemplate> findDataGrid(PageInfo pageInfo,
			Map<String, Object> condition) {
		if(condition==null || condition.size()==0){
			condition.put("ptTemplateCode", "");
			condition.put("ptTemplateName", "");
			condition.put("ptProductCode", "");
		}
		List<ProductTemplate> list = productTemplateMapper.findAllTemplate(condition);
		Iterator<ProductTemplate> listIter = list.iterator();
		while (listIter.hasNext()){
			ProductTemplate productTemplate = listIter.next(); 
			if(Const.BUSINESS_STUTAS_00.equals(productTemplate.getPtValidFlag()) && Const.BUSINESS_STUTAS_01.equals(productTemplate.getPtCheckState())){
				listIter.remove();
			}
		}
		
		List<ProductTemplate> temList = new ArrayList<ProductTemplate>();
		Map<String,ProductTemplate> map= Maps.newHashMap();
		for(ProductTemplate p:list){
			map.put(p.getPtTemplateCode(), p);
		}
		Iterator it = map.entrySet().iterator() ;
		while (it.hasNext()){
		Map.Entry entry = (Map.Entry) it.next() ;
		Object value = entry.getValue();
		temList.add((ProductTemplate)value);
		}
		pageInfo.setTotal(temList.size());
		PageHelperUtils.startPage(pageInfo);
		return temList;
	}*/

	@Override
	public List<ProductTemplate> findByTemplateCode(String templateCode) {
		return productTemplateMapper.findByTemplateCode(templateCode);
	}

	@Override
	public int deleteByTemplateCode(ProductTemplate productTemplate) {
		return productTemplateMapper.updateByPrimaryKey1(productTemplate);
	}

	@Override
	public int updateById(ProductTemplate productTemplate) {
		return 0;
	}

	@Override
	public int findByTemplateByCode(String ptTemplateCode) {
		return productTemplateMapper.findByTemplateByCode(ptTemplateCode);
	}

	@Override
	public List<ProductTemplate> findByProductCode(String productCode) {
		return productTemplateMapper.findByProductCode(productCode);
	}

	@Override
	public String selectNextCodeSequence() {
		return productTemplateMapper.selectNextCodeSequence();
	}

}
