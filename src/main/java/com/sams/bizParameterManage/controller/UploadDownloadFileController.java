package com.sams.bizParameterManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FxqUnResidentService;
import com.sams.common.constant.Const;
import com.sams.common.utils.PublicCreateExcelUtils;
import com.sams.common.utils.ServletUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.controller.BaseController;
import com.sams.custom.model.result.CreateExcelBean;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName UploadDownloadFileController
 * 描述 : 上传下载文件controller
 * @Author weijunjie
 * @Date 2020/1/19 13:18
 */
@RequestMapping("/downloadFile/")
@Controller
public class UploadDownloadFileController extends BaseController {


	private static Logger LOGGER = LoggerFactory.getLogger(UploadDownloadFileController.class);

	@Autowired
	private ExchangeProcessorService exchangeProcessorService;

    @Autowired
    private FxqUnResidentService fxqUnResidentService;

    /**
     * @Description 进入反洗钱非居民信息页面
     * @Author weijunjie
     * @Date 2020/2/6 13:52
     **/
    @RequestMapping("toAgentIncomePayPage")
    public String toAgentIncomePayPage(){
        return "sys/bizParameterManage/fileInteractive/AgentIncomePayPage";
    }

    /**
     * @Description 进入上传文件到ftp页面
     * @Author weijunjie
     * @Date 2020/2/6 13:52
     **/
    @RequestMapping("toAddFileToFtp")
    public String toAddFileToFtp(){
        return "sys/bizParameterManage/fileInteractive/addFileToFtpPage";
    }

    /**
     * @Description 下载文件模板数据 type = 1 个人  type = 2 机构
     * @Author weijunjie
     * @Date 2020/1/19 13:30
     **/
    @RequestMapping("downloadExcelTemplate")
    public Object downloadExcelTemplate(String type,HttpServletResponse response){
        String fileName = "个人反洗钱信息模板";
        Workbook template = fxqUnResidentService.getTemplate(type);
        try {
            SimpleDateFormat sf = new SimpleDateFormat("MMddHHmmss");
            if("2".equals(type)){
                fileName = "反洗钱非居民信息模板";
            }else if("0".equals(type)){
                fileName = "机构反洗钱信息模板";
            }
            writeExcelToWeb(template,response,fileName+sf.format(new Date())+".xlsx");
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return resultSuccess("下载文件异常");
        }
    }

    /**
     * @Description  数据文件上传
     * @Author weijunjie
     * @Date 2020/2/6 14:31
     **/
    @ResponseBody
    @RequestMapping(value = "uploadFile",method = RequestMethod.POST)
    public Object uploadFile(MultipartFile file,String type,String channelCode) throws Exception {
        InputStream inputStream = file.getInputStream();
        String s = fxqUnResidentService.importFxqFile(inputStream, type,channelCode);
        return resultSuccess(s);
    }

    /**
     * @Description 根据渠道下载个人or机构的数据 type = 1 个人  type = 2 机构
     * @Author weijunjie
     * @Date 2020/1/19 13:30
     **/
    @RequestMapping("downloadExcelByChannel")
    public Object downloadExcelByChannel(String type,String channelCode,String date,HttpServletResponse response){
        String fileName = "个人反洗钱数据导出";
        Workbook template = fxqUnResidentService.downloadByType(type,channelCode,date);
        try {
            SimpleDateFormat sf = new SimpleDateFormat("MMddHHmmss");
            if("0".equals(type)){
                fileName = "机构反洗钱数据导出";
            }
            if("2".equals(type)){
                fileName = "反洗钱数据导出";
            }
            writeExcelToWeb(template,response,fileName+sf.format(new Date())+".xlsx");
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return resultSuccess("下载文件异常");
        }
    }

    /**
     * @Description 展示当前按照条件查询到的反洗钱非居民信息信息
     * @Author weijunjie
     * @Date 2019/9/24 13:17
     **/
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "getAgentPaymentData")
    @ResponseBody
    public PageInfo getAgentPaymentData(Integer page, Integer rows,
                                      String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(fxqUnResidentService.getAllFxqDataToShow(condition,pageInfo));
        return pageInfo;
    }

    /**
     * @Description 展示当前按照条件查询到的反洗钱非居民信息信息
     * @Author weijunjie
     * @Date 2019/9/24 13:17
     **/
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "checkData")
    @ResponseBody
    public Object checkData(HttpServletRequest request,String type,String channelCode,String date) {
        String res = fxqUnResidentService.checkExcelDateRow(type,channelCode,date);
        return resultSuccess(res);
    }


    /**
     * @Description 测试excel文件下载功能
     * @Author weijunjie
     * @Date 2020/2/5 14:37
     **/
    @RequestMapping("testDownloadExcel")
    @ResponseBody
    public Object testDownloadExcel(HttpServletResponse response){
        //导出测试
        String msg = "下载文件异常";
        try {
            ArrayList<CreateExcelBean> createExcelBeans = new ArrayList<>();
            for(int d = 0;d<3;d++){
                CreateExcelBean createExcelBean = new CreateExcelBean();
                LinkedHashMap<String,String> jsonObject = new LinkedHashMap();
                jsonObject.put("aaa","姓名");
                jsonObject.put("bbb","年龄");
                jsonObject.put("ccc","大小");
                createExcelBean.setSheetTitle(jsonObject);
                ArrayList<Map<String,Object>> jsonObjects = new ArrayList<>();
                for(int i = 0;i<10;i++){
                    Map<String,Object> json = new HashMap<>();
                    json.put("aaa","姓名"+i);
                    json.put("bbb","年龄"+i);
                    json.put("ccc","大小"+i);
                    jsonObjects.add(json);
                }
                createExcelBean.setDataList(jsonObjects);
                createExcelBean.setSheetName("sheet"+d);
                createExcelBeans.add(createExcelBean);
            }
            Workbook cccc = PublicCreateExcelUtils.createWorkbook(createExcelBeans);

            writeExcelToWeb(cccc,response,"测试服务器文件下载.xlsx");
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return resultSuccess(msg);
        }
    }

    @RequestMapping(value = "exportTransStatistics", method = RequestMethod.GET)
	@ResponseBody
	public Object exportTransStatistics(String data,HttpServletResponse response){
    	data = StringEscapeUtils.unescapeHtml3(data).replace("filter_", "");
    	Map<String,Object> map = JSONObject.parseObject(data);
    	List<Map<String, Object>> list  = exchangeProcessorService.selectTransStatistics(new PageInfo(), map);
		LOGGER.info("导出的数据为数量为："+list.size());
		try {
			writeExcelToWeb(PublicCreateExcelUtils.createWorkbook(Const.titleStatisticsMap,null, list, "交易金额"),response,"交易数据汇总.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSuccess("");
	}
}