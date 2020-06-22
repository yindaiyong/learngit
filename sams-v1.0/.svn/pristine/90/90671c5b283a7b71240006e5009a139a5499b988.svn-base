package com.sams.custom.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.ProductTemplate;

public interface ProductTemplateMapper {
    int deleteByPrimaryKey(Long ptId);

    int insert1(ProductTemplate record);

    int insertSelective(ProductTemplate record);

    ProductTemplate selectByPrimaryKey1(Long ptId);

    int updateByPrimaryKeySelective1(ProductTemplate record);

    int updateByPrimaryKey1(ProductTemplate record);
    
    List<ProductTemplate> findProductTemplateCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
    
    public List<ProductTemplate> findByTemplateCode(String ptTemplateCode);
    
    public int deleteByTemplateCode(String ptTemplateCode);
    
    public int findByTemplateByCode(String ptTemplateCode);
    
    public List<ProductTemplate> findByProductCode(String productCode);
    
    public String selectNextCodeSequence();
    
    List<ProductTemplate> findAllTemplate(PageInfo pageInfo, Map<String, Object> condition);
    
    List<Map<String, Object>> queryTempalteCodeComBox();
    
    List<Map<String, Object>> queryTempalteNameComBox();
    
    List<Map<String, Object>> queryTempalteComBox();
    
    Date getSysDate();
    
    
    
}