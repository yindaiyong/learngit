package com.sams.test.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.ShapeTypes;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel2CharUtils {
	
	private static XSSFWorkbook wb = new XSSFWorkbook();
    private XSSFSheet sheet = null;
	
	public static void main(String[] args) throws Exception {
		Excel2CharUtils u = new Excel2CharUtils();
		//创建单条柱形图
		u.createBarChart();
		
	}
	/**
	 * @throws Exception 
	 * 导出excel生成条形图   
	 * @Title: createBarChart   
	 * @author: yindy 2020年6月9日 下午3:42:53
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	public void  createBarChart() throws Exception{ 
		// 字段名
        List<String> fldNameArr = new ArrayList<String>();
        // 标题
        List<String> titleArr = new ArrayList<String>();
        // 模拟数据
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> dataMap1 = new HashMap<String, Object>();
        dataMap1.put("value1", "1");
        dataMap1.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value4", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value5", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value6", Math.floor(Math.random() * 100) + "");
        dataMap1.put("value7", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap2 = new HashMap<String, Object>();
        dataMap2.put("value1", "1");
        dataMap2.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value4", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value5", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value6", Math.floor(Math.random() * 100) + "");
        dataMap2.put("value7", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap3 = new HashMap<String, Object>();
        dataMap3.put("value1", "1");
        dataMap3.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value4", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value5", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value6", Math.floor(Math.random() * 100) + "");
        dataMap3.put("value7", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap4 = new HashMap<String, Object>();
        dataMap4.put("value1", "1");
        dataMap4.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value4", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value5", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value6", Math.floor(Math.random() * 100) + "");
        dataMap4.put("value7", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap5 = new HashMap<String, Object>();
        dataMap5.put("value1", "1");
        dataMap5.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value4", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value5", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value6", Math.floor(Math.random() * 100) + "");
        dataMap5.put("value7", Math.floor(Math.random() * 100) + "");
        Map<String, Object> dataMap6 = new HashMap<String, Object>();
        dataMap6.put("value1", "1");
        dataMap6.put("value2", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value3", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value4", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value5", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value6", Math.floor(Math.random() * 100) + "");
        dataMap6.put("value7", Math.floor(Math.random() * 100) + "");
        fldNameArr.add("value1");
        fldNameArr.add("value2");
        fldNameArr.add("value3");
        fldNameArr.add("value4");
        fldNameArr.add("value5");
        fldNameArr.add("value6");
        fldNameArr.add("value7");
        titleArr.add("月");
        titleArr.add("月放款规模");
        titleArr.add("月存量规模");
        titleArr.add("月放款规模");
        titleArr.add("月存量规模");
        titleArr.add("月放款规模");
        titleArr.add("月存量规模");
        dataList.add(dataMap1);
        dataList.add(dataMap2);
        dataList.add(dataMap3);
        dataList.add(dataMap4);
        dataList.add(dataMap5);
        dataList.add(dataMap6);
        
        sheet = wb.createSheet("sheet1");
        // 获取sheet名称
        String sheetName = sheet.getSheetName();
        Map<String, Object> mergeTitleMap = new HashMap<String, Object>();
        Map<String, Object> biasMap = new HashMap<String, Object>();
        
        mergeTitleMap.put("MERGETITLE", Arrays.asList("年,2015,2015,2016,2016,2017,2017".split(",")));
        biasMap.put("STARTCOL", 0);
        biasMap.put("ENDCOL", 1);
        biasMap.put("STARTROW", 0);
        biasMap.put("ENDROW", 2);
        //生成数据表
        drawSheet0Table(sheet, titleArr, fldNameArr, dataList,mergeTitleMap,biasMap);
        
        FileOutputStream out = new FileOutputStream(new File("E:/excel/aa.xlsx"));
        wb.write(out);
        out.close();
        
	}
	/**
	 * 生成数据图表   
	 * @Title: drawSheet0Table   
	 * @author: yindy 2020年6月10日 上午9:52:45
	 * @param: @param sheet 
	 * @param: @param titleArr 标题集合（不需合并的）
	 * @param: @param fldNameArr 数据对应的键值，该顺序与标题顺序一致
	 * @param: @param dataList 数据集合
	 * @param: @param mergeTitleMap 合并单元格map,包含开始行列，结束行列;包含合并之后的标题名称集合MERGETITLE,为null,标题不合并
	 * @param: @param biasMap 划斜线map,为null,不合并
	 * @return: void      
	 * @throws
	 */
	private void drawSheet0Table(XSSFSheet sheet, List<String> titleArr, List<String> fldNameArr,
            List<Map<String, Object>> dataList,Map<String, Object> mergeTitleMap,Map<String, Object> biasMap) {
        // 初始化表格样式
        List<CellStyle> styleList = tableStyle();
        
        //设置标题
        int k = 0 ;
        k = setSheetTitle(mergeTitleMap,styleList,titleArr);
        //设置sheet的某个单元格格式划斜杠
        lineAtion(biasMap);
        
        // 填充数据
        for (int i = 0; i < dataList.size(); i++) {
            // 获取每一项的数据
            Map<String, Object> data = dataList.get(i);
            // 设置每一行的字段标题和数据
            XSSFRow rowi = sheet.createRow(i + 1 + k);
            for (int j = 0; j < fldNameArr.size(); j++) {
                // 判断是否是标题字段列
                if (j == 0) {
                    rowi.createCell(j).setCellValue((String) data.get(fldNameArr.get(j)));
                    // 设置左边字段样式
                    sheet.getRow(i+1+k).getCell(j).setCellStyle(styleList.get(1));
                } else {
                    rowi.createCell(j).setCellValue(Double.valueOf((String) data.get(fldNameArr.get(j))));
                    // 设置数据样式
                    sheet.getRow(i + 1 + k).getCell(j).setCellStyle(styleList.get(1));
                }
            }
        }
	}
	/**
	 * 设置标题样式 
	 * @Title: setSheetTitle   
	 * @author: yindy 2020年6月10日 上午9:46:51
	 * @param: @param mergeTitleMap
	 * @param: @param styleList
	 * @param: @param titleArr      
	 * @return: void      
	 * @throws
	 */
	private int setSheetTitle(Map<String, Object> mergeTitleMap, List<CellStyle> styleList, List<String> titleArr) {
		// 根据数据创建excel第一行标题行
        XSSFRow row0 = sheet.createRow(0);
        int k = 0;
		if(MapUtils.isNotEmpty(mergeTitleMap)){
			k = 1 ;
	        XSSFCell cell ;
	        List<String> mergeTitle = (List<String>) MapUtils.getObject(mergeTitleMap, "MERGETITLE");
	        int size = mergeTitle.size();
	        for (int i = 0; i < size; i++) {//设置表头-标题
	            cell = row0.createCell(i);
	            cell.setCellValue(mergeTitle.get(i));
	            row0.getCell(i).setCellStyle(styleList.get(0));
	        }
	        
	        //设置合并的标题头(注意：横向合并的时候，标题头单元格必须长度和内容单元格一致否则合并时会把其他标题头单元格内容挤掉)
	        sheet.addMergedRegion(new CellRangeAddress(0,0,1,2));//横向：合并第一行的第2列到第4列
	        sheet.addMergedRegion(new CellRangeAddress(0,0,3,4));//横向：合并第一行的第2列到第4列
	        sheet.addMergedRegion(new CellRangeAddress(0,0,5,6));//横向：合并第一行的第2列到第4列
//	        sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));//纵向：合并第一列的第1行和第2行第
//	        sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));//纵向：合并第二列的第1行和第2行第
	 
	        //设置对应的合并单元格标题
	        XSSFRow row = sheet.createRow(1);
	        for (int i = 0; i < size; i++) {
	            cell = row.createCell((short)i);
	            cell.setCellValue(titleArr.get(i));
	            //设置样式
	            row.getCell(i).setCellStyle(styleList.get(0));
	        }
        }else{
        	for (int i = 0; i < titleArr.size(); i++) {
                // 设置标题
                row0.createCell(i).setCellValue(titleArr.get(i));
                // 设置标题行样式
                row0.getCell(i).setCellStyle(styleList.get(0));
            }
        }
		return k;
	}
	/**
	 * 给单元格划斜线  
	 * @Title: lineAtion   
	 * @author: yindy 2020年6月10日 上午9:22:16
	 * @param: @param biasMap      
	 * @return: void      
	 * @throws
	 */
	private void lineAtion(Map<String, Object> biasMap) {
        if(MapUtils.isNotEmpty(biasMap)){
	        CreationHelper helper = wb.getCreationHelper();
	        XSSFDrawing drawing = sheet.createDrawingPatriarch();
	        ClientAnchor anchor = helper.createClientAnchor();
	        int startCol = MapUtils.getIntValue(biasMap, "STARTCOL");
	        int endCol = MapUtils.getIntValue(biasMap, "ENDCOL");
	        int startRow = MapUtils.getIntValue(biasMap, "STARTROW");
	        int endRow = MapUtils.getIntValue(biasMap, "ENDROW");
	        // 设置斜线的开始位置
	        anchor.setCol1(startCol); //列开始
	        anchor.setRow1(startRow); //行开始
	        // 设置斜线的结束位置
	        anchor.setCol2(endCol); //行结束
	        anchor.setRow2(endRow); //列结束
	        XSSFSimpleShape shape = drawing.createSimpleShape((XSSFClientAnchor) anchor);
	        // 设置形状类型为线型
	        shape.setShapeType(ShapeTypes.LINE);
	        // 设置线宽
	        shape.setLineWidth(0.5);
	        // 设置线的风格
	        shape.setLineStyle(0);
	        // 设置线的颜色
	        shape.setLineStyleColor(0, 0, 0);
        }
	}
	/**
	 * 设置sheet样式   
	 * @Title: tableStyle   
	 * @author: yindy 2020年6月10日 上午9:15:25
	 * @param: 第一个有背景色,第二个没有背景色      
	 * @return: List<CellStyle>      
	 */
	private static List<CellStyle> tableStyle() {
        List<CellStyle> cellStyleList = new ArrayList<CellStyle>();
        // 标题样式
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        //颜色
        font.setColor(Font.DEFAULT_CHARSET);
        
        style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN); // 下边框
        style.setBorderLeft(BorderStyle.THIN);// 左边框
        style.setBorderTop(BorderStyle.THIN);// 上边框
        style.setBorderRight(BorderStyle.THIN);// 右边框
        style.setAlignment(HorizontalAlignment.CENTER);
        cellStyleList.add(style);
        
        CellStyle style1 = wb.createCellStyle();
        style1.setBorderBottom(BorderStyle.THIN); // 下边框
        style1.setBorderLeft(BorderStyle.THIN);// 左边框
        style1.setBorderTop(BorderStyle.THIN);// 上边框
        style1.setBorderRight(BorderStyle.THIN);// 右边框
        style1.setAlignment(HorizontalAlignment.CENTER);
        cellStyleList.add(style1);
        return cellStyleList;
	}
}
