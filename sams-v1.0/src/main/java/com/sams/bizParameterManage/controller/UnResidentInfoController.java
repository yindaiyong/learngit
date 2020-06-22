package com.sams.bizParameterManage.controller;

import com.sams.batchfile.service.UnResidentInfoService;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UnResidentInfoController
 * 描述 : 非居民信息展示
 * @Author weijunjie
 * @Date 2019/9/24 13:13
 */
@RequestMapping("/unResidentInfo")
@Controller
public class UnResidentInfoController extends BaseController {

    @Autowired
    private UnResidentInfoService unResidentInfoService;
    /**
     * @Description 进入非居民信息展示页面
     * @Author weijunjie
     * @Date 2019/9/24 13:15
     **/
    @RequestMapping(method = RequestMethod.GET)
    public String unResidentInfoPage(){
        return "sys/bizParameterManage/unResidentInfo/unResidentInfoList";
    }

    /**
     * @Description 展示当前按照条件查询到的非居民信息数据
     * @Author weijunjie
     * @Date 2019/9/24 13:17
     **/
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/getUnResidentInfo")
    @ResponseBody
    public PageInfo getUnResidentInfo(Integer page, Integer rows,
                                              String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(unResidentInfoService.getAllUnResidentInfo(pageInfo,condition));
        return pageInfo;
    }
}
