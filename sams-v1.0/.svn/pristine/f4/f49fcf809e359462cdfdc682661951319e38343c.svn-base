package com.sams.common.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by 吴菊红 on 2017/7/3.
 */
public class ExportExcelUtils {
    private SXSSFWorkbook workbook = null;
    //表头字体
    private String titleFontType = "Arial Unicode MS";
    //表头背景色
    private String titleBackColor = "C1FBEE";
    //表头字号
    private short titleFontSize = 12;
    //添加自动筛选的列 如 A:M
    private String address = "";
    //正文字体
    private String contentFontType = "Arial Unicode MS";
    //正文字号
    private short contentFontSize = 12;
    //Float类型数据小数位
    private String floatDecimal = ".00";
    //Double类型数据小数位
    private String doubleDecimal = ".00";
    //设置列的公式
    private String colFormula[] = null;
    
    DecimalFormat floatDecimalFormat=new DecimalFormat(floatDecimal);
    DecimalFormat doubleDecimalFormat=new DecimalFormat(doubleDecimal);

    /**
     * 设置表头字体.
     * @param titleFontType
     */
    public void setTitleFontType(String titleFontType) {
        this.titleFontType = titleFontType;
    }
    /**
     * 设置表头背景色.
     * @param titleBackColor 十六进制
     */
    public void setTitleBackColor(String titleBackColor) {
        this.titleBackColor = titleBackColor;
    }
    /**
     * 设置表头字体大小.
     * @param titleFontSize
     */
    public void setTitleFontSize(short titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
    /**
     * 设置表头自动筛选栏位,如A:AC.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 设置正文字体.
     * @param contentFontType
     */
    public void setContentFontType(String contentFontType) {
        this.contentFontType = contentFontType;
    }
    /**
     * 设置正文字号.
     * @param contentFontSize
     */
    public void setContentFontSize(short contentFontSize) {
        this.contentFontSize = contentFontSize;
    }
    /**
     * 设置float类型数据小数位 默认.00
     * @param doubleDecimal 如 ".00"
     */
    public void setDoubleDecimal(String doubleDecimal) {
        this.doubleDecimal = doubleDecimal;
    }
    /**
     * 设置doubel类型数据小数位 默认.00
     * @param floatDecimalFormat 如 ".00
     */
    public void setFloatDecimalFormat(DecimalFormat floatDecimalFormat) {
        this.floatDecimalFormat = floatDecimalFormat;
    }
    /**
     * 设置列的公式
     * @param colFormula  存储i-1列的公式 涉及到的行号使用@替换 如A@+B@
     */
    public void setColFormula(String[] colFormula) {
        this.colFormula = colFormula;
    }
    /**
     * 写excel.
     * @param titleColumn  对应bean的属性名
     * @param titleName   excel要导出的表名
     * @param titleSize   列宽
     * @param dataList  数据
     */
    public void wirteExcel(HttpServletResponse response, String fileName,String titleColumn[], String titleName[], int titleSize[], List<?> dataList,String sheetName){
        workbook = new SXSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        Sheet sheet = workbook.createSheet(sheetName);
        try {
            //写入excel的表头
            Row titleNameRow = workbook.getSheet(sheetName).createRow(0);
            //设置样式
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle = (CellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize);
            titleStyle = (CellStyle) setColor(titleStyle, titleBackColor, (short)10);

            for(int i = 0;i < titleName.length;i++){
                sheet.setColumnWidth(i, titleSize[i]*256);    //设置宽度
                Cell cell = titleNameRow.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleName[i].toString());
            }

            //为表头添加自动筛选
            if(!"".equals(address)){
                CellRangeAddress c = (CellRangeAddress) CellRangeAddress.valueOf(address);
                sheet.setAutoFilter(c);
            }

            //通过反射获取数据并写入到excel中
            if(dataList!=null&&dataList.size()>0){
                //设置样式
            	CellStyle dataStyle = workbook.createCellStyle();
                titleStyle = (CellStyle) setFontAndBorder(titleStyle, contentFontType, (short) contentFontSize);

                if(titleColumn.length>0){
                    for(int rowIndex = 1;rowIndex<=dataList.size();rowIndex++){
                        Object obj = dataList.get(rowIndex-1);     //获得该对象
                        Class clsss = obj.getClass();     //获得该对对象的class实例
                        Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                        for(int columnIndex = 0;columnIndex<titleColumn.length;columnIndex++){
                            String title = titleColumn[columnIndex].toString().trim();
                            if(!"".equals(title)){  //字段不为空
                                //使首字母大写
                                String UTitle = Character.toUpperCase(title.charAt(0))+ title.substring(1, title.length()); // 使其首字母大写;
                                String methodName  = "get"+UTitle;

                                // 设置要执行的方法
                                Method method = clsss.getDeclaredMethod(methodName);

                                //获取返回类型
                                String returnType = method.getReturnType().getName();

                                String data = method.invoke(obj)==null?"":method.invoke(obj).toString();
                                Cell cell = dataRow.createCell(columnIndex);
                                if(data!=null&&!"".equals(data)){
                                    if("int".equals(returnType)){
                                        cell.setCellValue(Integer.parseInt(data));
                                    }else if("long".equals(returnType)){
                                        cell.setCellValue(Long.parseLong(data));
                                    }else if("float".equals(returnType)){
                                        cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
                                    }else if("double".equals(returnType)){
                                        cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
                                    }else{
                                        cell.setCellValue(data);
                                    }
                                }
                            }else{   //字段为空 检查该列是否是公式
                                if(colFormula!=null){
                                    String sixBuf = colFormula[columnIndex].replace("@", (rowIndex+1)+"");
                                    Cell cell = dataRow.createCell(columnIndex);
                                    cell.setCellFormula(sixBuf.toString());
                                }
                            }
                        }
                    }

                }
            }
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("UTF-8"),"ISO8859_1"));
            OutputStream out = response.getOutputStream();
            
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将16进制的颜色代码写入样式中来设置颜色
     * @param style  保证style统一
     * @param color 颜色：66FFDD
     * @param index 索引 8-64 使用时不可重复
     * @return
     */
    public CellStyle setColor(CellStyle style, String color, short index){
        if(color!=""&&color!=null){
            //转为RGB码
            int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制
            int g = Integer.parseInt((color.substring(2,4)),16);
            int b = Integer.parseInt((color.substring(4,6)),16);
            //自定义cell颜色
            //Palatte customPalette =  workbook.getXSSFWorkbook();
            //palette.setColorAtIndex((short)index, (byte) r, (byte) g, (byte) b);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        }
        return style;
    }

    /**
     * 设置字体并加外边框
     * @param style  样式
     * @param style  字体名
     * @param style  大小
     * @return
     */
    public CellStyle setFontAndBorder(CellStyle style,String fontName,short size){
        Font font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setFontName(fontName);
        font.setBold(true);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        style.setBorderTop(CellStyle.BORDER_THIN);//上边框
        style.setBorderRight(CellStyle.BORDER_THIN);//右边框
        return style;
    }
    
    
    public void wirteExcel(HttpServletResponse response, String fileName,String titleColumn[], String titleName[], int titleSize[], List<?> dataList,String sheetName,String absolutePath ,String newFileName){
        workbook = new SXSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        Sheet sheet = workbook.createSheet(sheetName);
        try {
            //写入excel的表头
            Row titleNameRow = workbook.getSheet(sheetName).createRow(0);
            //设置样式
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle = (CellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize);
            titleStyle = (CellStyle) setColor(titleStyle, titleBackColor, (short)10);

            for(int i = 0;i < titleName.length;i++){
                sheet.setColumnWidth(i, titleSize[i]*256);    //设置宽度
                Cell cell = titleNameRow.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleName[i].toString());
            }

            //为表头添加自动筛选
            if(!"".equals(address)){
                CellRangeAddress c = (CellRangeAddress) CellRangeAddress.valueOf(address);
                sheet.setAutoFilter(c);
            }

            //通过反射获取数据并写入到excel中
            if(dataList!=null&&dataList.size()>0){
                //设置样式
            	CellStyle dataStyle = workbook.createCellStyle();
                titleStyle = (CellStyle) setFontAndBorder(titleStyle, contentFontType, (short) contentFontSize);

                if(titleColumn.length>0){
                    for(int rowIndex = 1;rowIndex<=dataList.size();rowIndex++){
                        Object obj = dataList.get(rowIndex-1);     //获得该对象
                        Class clsss = obj.getClass();     //获得该对对象的class实例
                        Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                        for(int columnIndex = 0;columnIndex<titleColumn.length;columnIndex++){
                            String title = titleColumn[columnIndex].toString().trim();
                            if(!"".equals(title)){  //字段不为空
                                //使首字母大写
                                String UTitle = Character.toUpperCase(title.charAt(0))+ title.substring(1, title.length()); // 使其首字母大写;
                                String methodName  = "get"+UTitle;

                                // 设置要执行的方法
                                Method method = clsss.getDeclaredMethod(methodName);

                                //获取返回类型
                                String returnType = method.getReturnType().getName();

                                String data = method.invoke(obj)==null?"":method.invoke(obj).toString();
                                Cell cell = dataRow.createCell(columnIndex);
                                if(data!=null&&!"".equals(data)){
                                    if("int".equals(returnType)){
                                        cell.setCellValue(Integer.parseInt(data));
                                    }else if("long".equals(returnType)){
                                        cell.setCellValue(Long.parseLong(data));
                                    }else if("float".equals(returnType)){
                                        cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
                                    }else if("double".equals(returnType)){
                                        cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
                                    }else{
                                        cell.setCellValue(data);
                                    }
                                }
                            }else{   //字段为空 检查该列是否是公式
                                if(colFormula!=null){
                                    String sixBuf = colFormula[columnIndex].replace("@", (rowIndex+1)+"");
                                    Cell cell = dataRow.createCell(columnIndex);
                                    cell.setCellFormula(sixBuf.toString());
                                }
                            }
                        }
                    }

                }
            }
            File dir = new File(absolutePath);
			if (!dir.exists()) dir.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath + newFileName);//指定路径与名字和格式
            workbook.write(fileOutputStream);//将数据写出去
            fileOutputStream.close();//关闭输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 读excel.
     */
    public void readExcel(HttpServletResponse response,String fileName,String saveUrl){
    	File file = new File(saveUrl);
    	try {
    		InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((fileName+".xlsx").getBytes("UTF-8"),"ISO8859_1"));
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            
             // 这里主要关闭。
            os.close();
            inputStream.close();
    		/*BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
    		//POIFSFileSystem fs = new POIFSFileSystem(in);
    		Workbook workbook = ExcelUtils.create(in);
			response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((fileName+".xls").getBytes("UTF-8"),"ISO8859_1"));
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();*/
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static Workbook create(InputStream inp)  { 
        if(! inp.markSupported()) { 
            inp = new PushbackInputStream(inp, 8); 
        } 
        try {
			if(POIFSFileSystem.hasPOIFSHeader(inp)) { 
			    return new HSSFWorkbook(inp); 
			}
			if(POIXMLDocument.hasOOXMLHeader(inp)) { 
	            return new XSSFWorkbook(OPCPackage.open(inp)); 
	        } 
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return null;
      } 
}
