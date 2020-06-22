package com.sams.batchfile.service;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.result.UnResidentInfoRes;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UnResidentInfoService
 * 描述 : 非居民用户信息service
 * @Author weijunjie
 * @Date 2019/9/24 13:21
 */
public interface UnResidentInfoService {

    /**
     * @Description 展示非居民信息数据
     * @Author weijunjie
     * @Date 2019/9/24 13:50
     **/
    public List<UnResidentInfoRes> getAllUnResidentInfo(PageInfo pageInfo, Map<String, Object> condition);
}
