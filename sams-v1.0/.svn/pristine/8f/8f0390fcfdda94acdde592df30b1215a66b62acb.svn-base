package com.sams.batchfile.service.impl;

import com.sams.batchfile.service.UnResidentInfoService;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ResidentTaxInformMapper;
import com.sams.custom.model.result.UnResidentInfoRes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UnResidentInfoServiceImpl
 * 描述 : 非居民信息展示serviceImpl
 * @Author weijunjie
 * @Date 2019/9/24 13:36
 */
@Service
public class UnResidentInfoServiceImpl implements UnResidentInfoService {

    private static Logger log = LoggerFactory.getLogger(UnResidentInfoServiceImpl.class);
    @Autowired
    private ResidentTaxInformMapper residentTaxInformMapper;

    /**
     * @Description 获取符合条件的非居民信息展示数据
     * @Author weijunjie
     * @Date 2019/9/24 13:49
     **/
    public List<UnResidentInfoRes> getAllUnResidentInfo(PageInfo pageInfo, Map<String, Object> condition){
        PageHelperUtils.startPage(pageInfo);
        List<UnResidentInfoRes> unResidentInfoRes = new ArrayList<>();
        String transDate = (String)condition.get("transDate");
        if(!StringUtils.isEmpty(transDate)){
            transDate = transDate.replaceAll("-", "");
        }
        condition.put("transDate", transDate);
        unResidentInfoRes = residentTaxInformMapper.selectUnResidentInfo(pageInfo, condition);
        return unResidentInfoRes;
    }
}
