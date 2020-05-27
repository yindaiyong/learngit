package com.sams.bizParameterManage.controller;

import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.FundVolDetailCfmMapper;
import com.sams.custom.model.FundVolDetailCfm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FundVolDetailController
 * 描述 : 份额确认明细controller
 * @Author weijunjie
 * @Date 2020/2/7 9:50
 */
@Controller
@RequestMapping("/fundVolDetail/")
public class FundVolDetailController {

    @Autowired
    private FundVolDetailCfmMapper fundVolDetailCfmMapper;

    /**
     * @Description 份额明细展示页面
     * @Author weijunjie
     * @Date 2020/2/7 10:16
     **/
    @RequestMapping("toFundVolDetailPage")
    public String toFundVolDetailPage(){
        return "sys/bizParameterManage/fundVolDetail/fundVolDetailList";
    }

    /**
     * @Description 根据条件获取份额明细数据
     * @Author weijunjie
     * @Date 2020/2/7 10:16
     **/
    @RequestMapping("getAllFundVolDetail")
    @ResponseBody
    public PageInfo getAllFundVolDetail(Integer page, Integer rows,
                                        String sort, String order, HttpServletRequest request){
        /*sort = sort == null?"FVD_CHANNEL_CODE, FVD_TRANS_DATE":sort;
        order = order == null ? "DESC":order;*/
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        PageHelperUtils.startPage(pageInfo);
        List<FundVolDetailCfm> fundVolDetails = new ArrayList<>();
        String transDate = (String)condition.get("transDate");
        String transDateCfm = (String)condition.get("transDateCfm");
        String transDateAllow = (String)condition.get("transDateAllow");
        if(!StringUtils.isEmpty(transDate)){
            transDate = transDate.replaceAll("-", "");
        }
        if(!StringUtils.isEmpty(transDateCfm)){
            transDateCfm = transDateCfm.replaceAll("-", "");
        }
        if(!StringUtils.isEmpty(transDateAllow)){
            transDateAllow = transDateAllow.replaceAll("-", "");
        }
        condition.put("transDate", transDate);
        condition.put("transDateCfm", transDateCfm);
        condition.put("transDateAllow", transDateAllow);
        fundVolDetails = fundVolDetailCfmMapper.selectFundVolDetail(pageInfo, condition);
        pageInfo.setPageResult(fundVolDetails);
        return pageInfo;
    }
}
