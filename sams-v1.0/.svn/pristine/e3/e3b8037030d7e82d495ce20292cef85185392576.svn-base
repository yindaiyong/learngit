package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductTemplate;

public interface ProductTemplateService extends BaseService<ProductTemplate>{
	public List<ProductTemplate> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);
	public List<ProductTemplate> findByTemplateCode(String templateCode);
	public int deleteByTemplateCode(ProductTemplate productTemplate);
	public int updateById(ProductTemplate productTemplate);
	public int findByTemplateByCode(String ptTemplateCode);
	public List<ProductTemplate> findByProductCode(String productCode);
	public String selectNextCodeSequence();
}
