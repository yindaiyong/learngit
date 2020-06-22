package com.sams.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.sams.custom.model.result.CreateExcelBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @ClassName PublicCreateExcelUtils
 * 描述 : 公共创建excel方法
 * @Author weijunjie
 * @Date 2020/2/5 13:30
 */
public class PublicCreateExcelUtils {

    /**-------------------------------单sheet-------------------------------------**/

    /**
     * @Description excel列序固定，与标题插入顺序一致
     * @Author weijunjie
     * @Date 2020/2/5 13:59
     **/
    public static JSONObject createOrderTitle(LinkedHashMap<String,String> titleMap){
        JSONObject jsonObject = new JSONObject(true);
        for(String key:titleMap.keySet()){
            jsonObject.put(key,titleMap.get(key));
        }
        return jsonObject;
    }

    /**
     * @Description excel数据取值
     * @Author weijunjie
     * @Date 2020/2/5 13:59
     **/
    public static List<JSONObject> createDataList( List<Map<String,Object>> dataList){
        List<JSONObject> objects = new ArrayList<>();
        if(null == dataList){
            return objects;
        }
        for(Map map:dataList){
            JSONObject theMap = JSONObject.parseObject(JSONObject.toJSONString(map));
            objects.add(theMap);
        }
        return objects;
    }

    /**
     * @Description 创建workbook (map)
     * @Author weijunjie
     * @Date 2020/2/5 13:31
     **/
    public static Workbook createWorkbook(LinkedHashMap<String,String> titleMap,
                                          Map<String,Integer> widthMap,
                                          List<Map<String,Object>> dataList,
                                          String sheetName){
        JSONObject jsonObject = createOrderTitle(titleMap);
        List<JSONObject> objects = new ArrayList<>();
        for(Map map:dataList){
            JSONObject theMap = JSONObject.parseObject(JSONObject.toJSONString(map));
            objects.add(theMap);
        }
        JSONObject widthObj = JSONObject.parseObject(JSONObject.toJSONString(widthMap));
        return createWorkbook(jsonObject,widthObj,objects,sheetName);
    }

    /**
     * @Description 传入数据组装workbook
     * @Author weijunjie
     * @Date 2020/1/20 9:04
     **/
    public static Workbook createWorkbook(JSONObject titleMap,JSONObject widthMap, List<JSONObject> dataList, String sheetName){
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet(sheetName);
        boolean widthFlag = true;
        if(null == widthMap){
            widthFlag = false;
        }
        //首行写入title数据，获取对应的key值
        int z = 0;
        SXSSFRow titleRow = sheet.createRow(0);
        for(String key:titleMap.keySet()){
            SXSSFCell cell = titleRow.createCell(z);
            String val = titleMap.getString(key);
            if(!widthFlag){
                sheet.setColumnWidth(z, (val.length()+1) * 2 * 256);
            }else{
                int width = widthMap.getInteger(key);
                sheet.setColumnWidth(z, width);
            }
            cell.setCellValue(val);
            z++;
        }
        for(int i = 1;i<=dataList.size();i++){
            SXSSFRow row = sheet.createRow(i);
            //按照插入顺序获取对应的所有数据信息
            JSONObject data = dataList.get(i-1);
            int j = 0;
            for(String key:titleMap.keySet()){
                SXSSFCell cell = row.createCell(j);
                String val = data.getString(key);
                cell.setCellValue(val);
                j++;
            }
        }
        return workbook;
    }

    /**---------------------------------多sheet-------------------------------**/

    /**
     * @Description 创建多sheetWorkbook
     * @Author weijunjie
     * @Date 2020/2/5 14:10
     **/
    public static Workbook createWorkbook(List<CreateExcelBean> createExcelBeans){
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        int sheetIndex = 0;
        for(CreateExcelBean createExcelBean:createExcelBeans){
            JSONObject titleMap = createOrderTitle(createExcelBean.sheetTitle);
            List<JSONObject> dataList = createDataList(createExcelBean.dataList);
            String sheetName = StringUtils.isNotBlank(createExcelBean.getSheetName())
                    ?createExcelBean.getSheetName():"未命名"+sheetIndex;
            sheetIndex++;
            SXSSFSheet sheet = workbook.createSheet(sheetName);
            JSONObject widthObj = new JSONObject();
            JSONObject typeObj = new JSONObject();
            Map<String,Integer> widthMap = createExcelBean.getWidthMap();
            Map<String,Integer> typeMap = createExcelBean.getTypeMap();
            //判断是否指定对应列的宽度数据（没有指定默认  根据列标题的长度自适应）
            boolean widthFlag = true;
            if(null == widthMap){
                widthFlag = false;
            }else{
                widthObj = JSONObject.parseObject(JSONObject.toJSONString(widthMap));
            }
            //判断是否指定对应列的宽度数据（没有指定默认  根据列标题的长度自适应）
            boolean typeFlag = true;
            if(null == typeMap){
                typeFlag = false;
            }else{
                typeObj = JSONObject.parseObject(JSONObject.toJSONString(typeMap));
            }
            //首行写入title数据，获取对应的key值
            int z = 0;
            SXSSFRow titleRow = sheet.createRow(0);
            for(String key:titleMap.keySet()){
                SXSSFCell cell = titleRow.createCell(z);
                String val = titleMap.getString(key);
                if(!widthFlag){
                    sheet.setColumnWidth(z, (val.length()+1) * 2 * 256);
                }else{
                    int width = widthObj.getInteger(key);
                    sheet.setColumnWidth(z, width);
                }
                if(!typeFlag){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                }else{
                    int cellType = typeObj.getInteger(key);
                    cell.setCellType(cellType);
                }
                cell.setCellValue(val);
                z++;
            }
            for(int i = 1;i<=dataList.size();i++){
                SXSSFRow row = sheet.createRow(i);
                //按照插入顺序获取对应的所有数据信息
                JSONObject data = dataList.get(i-1);
                int j = 0;
                for(String key:titleMap.keySet()){
                    SXSSFCell cell = row.createCell(j);
                    String val = data.getString(key);
                    cell.setCellValue(val);
                    j++;
                }
            }
        }
        return workbook;
    }

    /**
     * @Description 获取一种颜色的单元格格式
     * @Author weijunjie
     * @Date 2020/3/17 16:20
     **/
    public static CellStyle getOnStyle(Workbook workbook,short var1){
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(var1);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        return style;
    }

    /**
     * @Description 机构模板特殊生成  &　支持生成双模板
     * @Author weijunjie
     * @Date 2020/3/17 15:26
     **/
    public static Workbook createOrgTemp(JSONObject titleMap,JSONObject widthMap, List<JSONObject> dataList, String sheetName,CreateExcelBean Sheet2){
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        if(null != Sheet2) {
            //生成两个sheet的模板
            JSONObject titleMap1 = createOrderTitle(Sheet2.getSheetTitle());
            List<JSONObject> dataList1 = createDataList(Sheet2.getDataList());
            String sheetName1 = StringUtils.isNotBlank(Sheet2.getSheetName())
                    ? Sheet2.getSheetName() : "个人模板";
            SXSSFSheet sheet = workbook.createSheet(sheetName1);
            //首行写入title数据，获取对应的key值
            int z = 0;
            SXSSFRow titleRow = sheet.createRow(0);
            for (String key : titleMap1.keySet()) {
                SXSSFCell cell = titleRow.createCell(z);
                String val = titleMap1.getString(key);
                sheet.setColumnWidth(z, (val.length() + 1) * 2 * 256);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(val);
                z++;
            }
            for (int i = 1; i <= dataList1.size(); i++) {
                SXSSFRow row = sheet.createRow(i);
                //按照插入顺序获取对应的所有数据信息
                JSONObject data = dataList1.get(i - 1);
                int j = 0;
                for (String key : titleMap1.keySet()) {
                    SXSSFCell cell = row.createCell(j);
                    String val = data.getString(key);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(val);
                    j++;
                }
            }
        }
        SXSSFSheet sheet = workbook.createSheet(sheetName);

        CellStyle SKY_BLUE = getOnStyle(workbook,IndexedColors.SKY_BLUE.getIndex());
        CellStyle CORAL = getOnStyle(workbook,IndexedColors.CORAL.getIndex());
        CellStyle GREEN = getOnStyle(workbook,IndexedColors.GREEN.getIndex());
        CellStyle LAVENDER = getOnStyle(workbook,IndexedColors.LAVENDER.getIndex());
        CellStyle LEMON_CHIFFON = getOnStyle(workbook,IndexedColors.LEMON_CHIFFON.getIndex());
        CellStyle GREY_40_PERCENT = getOnStyle(workbook,IndexedColors.GREY_40_PERCENT.getIndex());

//        SKY_BLUE   CORAL   GREEN   LAVENDER   LEMON_CHIFFON  GREY_40_PERCENT

        boolean widthFlag = true;
        if(null == widthMap){
            widthFlag = false;
        }
        //机构导入数据模板特殊生成（title占据前三行）
        int r = 0;
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFRow row2 = sheet.createRow(1);
            SXSSFRow row3 = sheet.createRow(2);
            for(String key:titleMap.keySet()){
                SXSSFCell cell = titleRow.createCell(r);
                String val = titleMap.getString(key);
                if(!widthFlag){
                    sheet.setColumnWidth(r, (val.length()+1) * 2 * 256);
                }else{
                    int width = widthMap.getInteger(key);
                    sheet.setColumnWidth(r, width);
                }
                if(r<76){
                    cell.setCellValue(val);
                }else if(r >= 76 && r<=92){
                    //第一行执行一次插入  后续单元格不插入value
                    if(r == 76){
                        //第一行插入标题
                        cell.setCellValue("客户为股份有限公司、有限责任公司（非国有控股企业）的受益所有人");
                    }
                    //第二行写入

                    SXSSFCell cell2 = row2.createCell(r);
                    cell2.setCellValue(val);
                    if(r>=90){
                        if(r == 90){
                            cell2.setCellValue("股东名单（持股比例超过25%及以上）");
                        }
                        //第三行写入
                        SXSSFCell cell3 = row3.createCell(r);
                        cell3.setCellValue(val);
                        cell3.setCellStyle(SKY_BLUE);
                    }
                    cell2.setCellStyle(SKY_BLUE);
                    cell.setCellStyle(SKY_BLUE);
                    //设置颜色

                }else if(r>=93 && r<=102){
                    if(r == 93){
                        //第一行插入标题
                        cell.setCellValue("客户为合伙企业的受益所有人");
                    }
                    //第二行写入
                    SXSSFCell cell2 = row2.createCell(r);
                    cell2.setCellValue(val);
                    cell2.setCellStyle(CORAL);
                    cell.setCellStyle(CORAL);
                }else if(r>=103 && r<=112){
                    if(r == 103){
                        //第一行插入标题
                        cell.setCellValue("客户为信托的受益所有人");
                    }
                    //第二行写入
                    SXSSFCell cell2 = row2.createCell(r);
                    cell2.setCellValue(val);
                    cell2.setCellStyle(GREEN);
                    cell.setCellStyle(GREEN);
                }else if(r>=113 && r<=122){
                    if(r == 113){
                        //第一行插入标题
                        cell.setCellValue("客户为基金的受益所有人");
                    }
                    //第二行写入
                    SXSSFCell cell2 = row2.createCell(r);
                    cell2.setCellValue(val);
                    cell2.setCellStyle(LAVENDER);
                    cell.setCellStyle(LAVENDER);
                }else if(r>=123 && r<=131){
                    if(r == 123){
                        //第一行插入标题
                        cell.setCellValue("客户为非政府控制的企事业单位的受益所有人");
                    }
                    //第二行写入
                    SXSSFCell cell2 = row2.createCell(r);
                    cell2.setCellValue(val);
                    cell2.setCellStyle(LEMON_CHIFFON);
                    cell.setCellStyle(LEMON_CHIFFON);
                }else if(r>=132 && r<=140){
                    if(r == 132){
                        //第一行插入标题
                        cell.setCellValue("客户为个体工商户、个人独资企业、不具备法人资格的专业服务机构");
                    }
                    //第二行写入
                    SXSSFCell cell2 = row2.createCell(r);
                    cell2.setCellValue(val);
                    cell2.setCellStyle(GREY_40_PERCENT);
                    cell.setCellStyle(GREY_40_PERCENT);
                } else{
                    cell.setCellValue(val);
                }
                //设置颜色
                r++;
            }
        //合并单元格操作
        for(int z = 0;z<titleMap.size();z++){
            if(z<76){
                //合并前三行
                CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 2, z, z);
                sheet.addMergedRegion(cellRangeAddress);
            }else if(z >= 76 && z<=92){
                //第一行合并操作
                if(z == 76){
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 76, 92);
                    sheet.addMergedRegion(cellRangeAddress);
                    CellRangeAddress cell1 = new CellRangeAddress(1, 2, z, z);
                    sheet.addMergedRegion(cell1);
                }else{
                    if(z<90){
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 2, z, z);
                        sheet.addMergedRegion(cellRangeAddress);
                    }else if(z == 90){
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 90, 92);
                        sheet.addMergedRegion(cellRangeAddress);
                    }else{

                    }
                }
            }else if(z>=93 && z<=102){
                if(z == 93){
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 93, 102);
                    sheet.addMergedRegion(cellRangeAddress);
                }
                CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 2, z, z);
                sheet.addMergedRegion(cellRangeAddress);
            }else if(z>=103 && z<=112){
                if(z == 103){
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 103, 112);
                    sheet.addMergedRegion(cellRangeAddress);
                }
                CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 2, z, z);
                sheet.addMergedRegion(cellRangeAddress);
            }else if(z>=113 && z<=122){
                if(z == 113){
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 113, 122);
                    sheet.addMergedRegion(cellRangeAddress);
                }
                CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 2, z, z);
                sheet.addMergedRegion(cellRangeAddress);
            }else if(z>=123 && z<=131){
                if(z == 123){
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 123, 131);
                    sheet.addMergedRegion(cellRangeAddress);
                }
                CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 2, z, z);
                sheet.addMergedRegion(cellRangeAddress);
            }else if(z>=132 && z<=140){
                if(z == 132){
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 132, 140);
                    sheet.addMergedRegion(cellRangeAddress);
                }
                CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 2, z, z);
                sheet.addMergedRegion(cellRangeAddress);
            }
        }

        for(int i = 3;i<dataList.size()+3;i++){
            SXSSFRow row = sheet.createRow(i);
            //按照插入顺序获取对应的所有数据信息
            JSONObject data = dataList.get(i-3);
            int j = 0;
            for(String key:titleMap.keySet()){
                SXSSFCell cell = row.createCell(j);
                String val = data.getString(key);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(val);
                j++;
            }
        }
        return workbook;
    }



    /**
     * @Description
     * @Author weijunjie
     * @Date 2020/3/17 17:56
     **/
    public static Workbook createOrgTemp(LinkedHashMap<String,String> titleMap,
                                         Map<String,Integer> widthMap,
                                         List<Map<String,Object>> dataList,
                                         String sheetName,
                                         CreateExcelBean Sheet2){
        JSONObject jsonObject = new JSONObject(true);
        for(String key:titleMap.keySet()){
            jsonObject.put(key,titleMap.get(key));
        }
        List<JSONObject> dataList1 = createDataList(dataList);
        Workbook cccc = PublicCreateExcelUtils.createOrgTemp(jsonObject,null,dataList1,sheetName,Sheet2);
        return cccc;
    }

    /**
     * @Description 测试生成调用
     * @Author weijunjie
     * @Date 2020/2/5 14:27
     **/
    public static void main(String[] args){
        try {
            File file = new File("D:\\sams\\testFile\\下载文ALL.xlsx");
            OutputStream out = new FileOutputStream(file);
            //导出测试
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();

            LinkedHashMap<String,String> jsonO = FxqTitleUtil.getAIPTitleForOrgan();
            JSONObject jsonObject = new JSONObject(true);
            for(String key:jsonO.keySet()){
                jsonObject.put(key,jsonO.get(key));
            }
            jsonObjects.add(jsonObject);
            CreateExcelBean createExcelBean = new CreateExcelBean();
            createExcelBean.setSheetTitle(FxqTitleUtil.getAIPTitleForPersonal());
            createExcelBean.setSheetName("个人");
            Workbook cccc = PublicCreateExcelUtils.createOrgTemp(jsonObject,null,jsonObjects,"123",createExcelBean);
            cccc.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
