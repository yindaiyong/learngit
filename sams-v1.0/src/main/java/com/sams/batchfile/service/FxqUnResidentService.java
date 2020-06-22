package com.sams.batchfile.service;

import com.sams.common.web.PageInfo;
import com.sams.custom.model.DFxqUnresidentData;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FxqUnResidentService
 * 描述 :
 * @Author weijunjie
 * @Date 2020/3/17 14:06
 */
public interface FxqUnResidentService {

    public Workbook getTemplate(String type);

    public String importFxqFile(InputStream inputStream, String type,String channelCode);

    public Workbook downloadByType(String type,String channelCode,String date);

    public String checkExcelDateRow(String type,String channelCode,String date);

    public List<DFxqUnresidentData> getAllFxqDataToShow(Map<String, Object> condition, PageInfo pageInfo);

}
