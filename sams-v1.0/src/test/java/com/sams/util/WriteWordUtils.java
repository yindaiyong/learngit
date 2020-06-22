//package com.sams.util;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.collections.MapUtils;
//
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.Image;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.rtf.RtfWriter2;
//
//public class WriteWordUtils {
//
//	public static void exportDoc() {
//		try {
//			//设置纸张大小
//			Document document = new Document(PageSize.A4);
//			// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
//			File file = new File("E:\\excel\\aa.doc");
//			RtfWriter2.getInstance(document, new FileOutputStream(file));
//			document.open();
//			
//			//获得基本字体设置
//			Map<String, Font> fontMap = getFontList();
//			
//			StringBuilder title = new StringBuilder("合作机构名称\n数据尽调分析报告总览");
//			//把内容加入docment
//			document = addContextToDocument(document,title,Element.ALIGN_CENTER,MapUtils.getObject(fontMap, "TITLE") );
//			
//			
//			StringBuilder context = new StringBuilder("小微金融数据尽调小组\n报告生成日期");
//			context.append(new Date().getYear())
//				.append("年")
//				.append(new Date().getMonth())
//				.append("月")
//				.append(new Date().getDay())
//				.append("日");
//			//把内容加入docment
//			document = addContextToDocument(document,context,Element.ALIGN_CENTER,MapUtils.getObject(fontMap, "PAGE"));
//			
//			document = addContextToDocument(document,new StringBuilder("\n\n\n\n"),Element.ALIGN_CENTER,MapUtils.getObject(fontMap, "PAGE"));
//			
//			//添加图片
//			document = addPictureToDocument(document,"E:\\excel","Sheet3chart.png");
//			
//			document.close();
//		}catch(Exception e ){
//			
//		}
//	}
//	
//	/**
//	 * 添加图片到文档中
//	 * @Title: addPictureToDocument   
//	 * @author: yindy 2020年6月12日 下午2:37:05
//	 * @param: @param document
//	 * @param: @param imgPath
//	 * @param: @param imgName
//	 * @param: @return      
//	 * @return: Document      
//	 * @throws
//	 */
//	private static Document addPictureToDocument(Document document,String imgPath,String imgName) {
//		try {
//			Image img = Image.getInstance(imgPath + File.separator + imgName);
//			img.setAbsolutePosition(0, 0);
//			img.setAlignment(Image.ALIGN_CENTER);// 设置图片显示位置
//	//		img.scalePercent(30);// 表示显示的大小为原尺寸的50%
//	//		img.scaleAbsolute(60, 60);// 直接设定显示尺寸
//	//		img.scalePercent(25, 12);//图像高宽的显示比例
//	//		img.setRotation(30);//图像旋转一定角度
//			document.add(img);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return document;
//	}
//
//	/**
//	 * 设置文档段落内容   
//	 * @Title: addContextToDocument   
//	 * @author: yindy 2020年6月12日 下午2:37:36
//	 * @param: @param document
//	 * @param: @param context
//	 * @param: @param align
//	 * @param: @param font
//	 * @param: @return      
//	 * @return: Document      
//	 * @throws
//	 */
//	private static Document addContextToDocument(Document document, StringBuilder context,
//			int align, Object font) {
//		Paragraph page = new Paragraph(context.toString());
//		//设置标题格式对齐方式
//		page.setAlignment(Element.ALIGN_CENTER);
//		page.setFont((Font)font);
//		//空行
//		page.setSpacingBefore(2);
//		page.setSpacingAfter(4);
//		// 设置第一行空的列数
//		page.setFirstLineIndent(8);
//		try {
//			document.add(page);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		return document;
//	}
//
//	/**
//	 * 获得段落格式   
//	 * @Title: getFontList   
//	 * @author: yindy 2020年6月12日 下午2:38:07
//	 * @param: @return      
//	 * @return: Map<String,Font>      
//	 * @throws
//	 */
//	private static Map<String, Font> getFontList() {
//		Map<String, Font> fontMap = new HashMap<String, Font>();
//		BaseFont baseFont  = null ;
//		//获得基本字体设置
//		try {
//			baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		//标题
//		Font titleFont = new Font(baseFont, 28, Font.BOLD);
//		fontMap.put("TITLE", titleFont);
//		
//		// 正文字体风格
//		Font contextFont = new Font(baseFont, 16, Font.NORMAL);
//		fontMap.put("PAGE", contextFont);
//		
//		return fontMap;
//	}
//	
//	/**
//	 * 
//	 * <dependency>
//			<groupId>com.lowagie</groupId>
//			<artifactId>itext</artifactId>
//			<version>2.1.7</version>
//			</dependency>
//			<dependency>
//			<groupId>com.lowagie</groupId>
//			<artifactId>itext-rtf</artifactId>
//			<version>2.1.7</version>
//			</dependency>
//			<dependency>
//			<groupId>com.itextpdf</groupId>
//			<artifactId>itext-asian</artifactId>
//			<version>5.2.0</version>
//		</dependency>
//	 */
//}
