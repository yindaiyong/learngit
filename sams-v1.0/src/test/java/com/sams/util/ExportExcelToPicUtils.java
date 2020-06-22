//package com.sams.util;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//
//import javax.imageio.ImageIO;
//
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.spire.xls.Workbook;
//import com.spire.xls.Worksheet;
//import com.spire.xls.collections.WorksheetsCollection;
//
//
//public class ExportExcelToPicUtils {
//
//	private static Logger LOGGER = LoggerFactory.getLogger(ExportExcelToPicUtils.class);
//	
//	
//	/**
//	 * 把excel的sheet转成图片 
//	 * @Title: excel2Picture   
//	 * @author: yindy 2020年6月12日 上午9:06:35
//	 * @param: @param dir  图片的位置
//	 * @param: @param fromFileName  读取的excel名称
//	 * @param: @param toFileName  生成的图片名称
//	 * @param: @param pictureType  生成的图片类型    
//	 */
//	public static void excel2Picture(String dir,String fromFileName,String toFileName,String pictureType){
//		//加载excel文件
//		Workbook wb = new Workbook();
//		String fromFile = dir + File.separatorChar + fromFileName;
//		LOGGER.info("读取的excel的完整路径为{}",fromFile);
//        wb.loadFromFile(fromFile);
//        
//        //获得excel工作表
//        WorksheetsCollection collection = wb.getWorksheets();
//        int size = collection.size();
//        LOGGER.info("读取的excel的sheet数量为{}",size);
//        //数据表生成图片
//        if(size == 1 ){
//        	Worksheet sheet = collection.get(0);
//        	String toFile = dir + File.separator + toFileName + pictureType;
//        	LOGGER.info("生成的图片的全路径为{}",toFile);
//        	long startTime = new Date().getTime();
//        	sheet.saveToImage(toFile);
//        	LOGGER.info("生成的图片的时间为{}",((new Date().getTime()) - startTime ) / 1000 + "s" );
//        }else{
//        	//经试验，当sheet超过3个就生成不了图片了
//    		for ( int i = 0 ; i < size; i++){
//    			Worksheet sheet = collection.get(i);
//    			String name = sheet.getName();
//    			String toFile = dir + File.separator + name ;
//    			if( i <= 2){
//                	LOGGER.info("生成的图片的第{}个sheet的全路径为{}",i,toFile);
//                	long startTime = new Date().getTime();
//                	sheet.saveToImage(toFile + pictureType);
//                	LOGGER.info("生成的图片的第{}个sheet的时间为{}",i,((new Date().getTime()) - startTime ) / 1000 + "s" );
//    			}else{
//    				sheet.saveToPdf(toFile + ".pdf");
//    				File file = new File(toFile + ".pdf");
//			        try {
//			            PDDocument doc = PDDocument.load(file);
//			            PDFRenderer renderer = new PDFRenderer(doc);
//			            int pageCount = doc.getNumberOfPages();
//			            for (int j = 0; j < pageCount; j++) {
//			                BufferedImage image = renderer.renderImageWithDPI(j, 144); // Windows native DPI
//			                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
//			                ImageIO.write(image, "png", new File(toFile + pictureType));
//			            }
//			         //删除pdf
//			         file.delete();
//			        } catch (IOException e) {
//			            e.printStackTrace();
//			        }
//    			}
//            	
//            }
//        }
//	}
//	
//	/**
//	 * 把excel中的图表转成图片   
//	 * @Title: excelChart2Picture   
//	 * @author: yindy 2020年6月12日 上午10:36:27
//	 * @param: @param dir excel路径
//	 * @param: @param fromFileName excel名称
//	 * @param: @param toFileName 图片名称
//	 * @param: @param pictureType    图片类型  
//	 */
//	public static void excelChart2Picture(String dir,String fromFileName,String toFileName,String pictureType){
//		//加载excel文件
//		Workbook wb = new Workbook();
//		String fromFile = dir + File.separatorChar + fromFileName;
//		LOGGER.info("读取的excel的完整路径为{}",fromFile);
//        wb.loadFromFile(fromFile);
//        
//        //获得excel工作表
//        WorksheetsCollection collection = wb.getWorksheets();
//        int size = collection.size();
//        LOGGER.info("读取的excel的sheet数量为{}",size);
//		//图表生成图片
//        if(size == 1 ){
//        	Worksheet sheet = collection.get(0);
//        	String toFile = dir + File.separator + toFileName + "chart" + pictureType;
//        	LOGGER.info("生成的图表图片的全路径为{}",toFile);
//        	long startTime = new Date().getTime();
//        	try {
//        		BufferedImage image = wb.saveChartAsImage(sheet, 0);
//				ImageIO.write(image,"png", new File(toFile));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        	LOGGER.info("生成的图表图片的时间为{}",((new Date().getTime()) - startTime ) / 1000 + "s" );
//        }else{
//        	for ( int i = 0 ; i < size; i++){
//    			Worksheet sheet = collection.get(i);
//    			String name = sheet.getName();
//    			String toFile = dir + File.separator + name + "chart" + pictureType ;
//    			long startTime = new Date().getTime();
//    			BufferedImage image = null;
//    			try {
//    				image = wb.saveChartAsImage(sheet, 0);
//    				ImageIO.write(image,"png", new File(toFile));
//				} catch (Exception e) {
//					e.printStackTrace();
//					continue;
//				}
//            	LOGGER.info("生成的图表图片的时间为{}",((new Date().getTime()) - startTime ) / 1000 + "s" );
//        	}
//        }
//	}
//	
//	/**
//	 * <dependency>
//			<groupId>org.apache.pdfbox</groupId>
//			<artifactId>fontbox</artifactId>
//			<version>2.0.9</version>
//		</dependency>
//		<!-- https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox -->
//		<dependency>
//			<groupId>org.apache.pdfbox</groupId>
//			<artifactId>pdfbox</artifactId>
//			<version>2.0.9</version>
//		</dependency>
//		<!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
//		<dependency>
//			<groupId>commons-logging</groupId>
//			<artifactId>commons-logging</artifactId>
//			<version>1.2</version>
//		</dependency>
//	 */
//}
