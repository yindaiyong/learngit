package com.sams.junit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import com.sams.batchfile.common.FileDataUtil;
import com.sams.common.utils.RandomizingID;
import com.sams.sys.model.SysOrg;

public class Test {
	
	public static volatile String URL = "XXXX";
	
	public static String aaa = Test.URL+"2222";
	
	public static void main(String[] args) throws InterruptedException {
//		String msg = getMsg();
//		System.out.println("打印:msg-->"+msg);
//		BigDecimal aa = new BigDecimal("1000.66").subtract(new BigDecimal("33.33").multiply(new BigDecimal("1.0000")));
//		System.out.println(aa);
//		String bb = FileDataUtil.exchangeRedixPoint(aa.toString(), 2, 16);
//		System.out.println(bb);
		System.out.println(URL);
		URL = "dddd";
		Test t = new Test();
		System.out.println(t.aaa);
	}

	private static String getMsg() {
		try {
			int i = 1/0;
			return "try";
		} catch (Exception e) {
			StringWriter stringWriter= new StringWriter();
	        PrintWriter writer= new PrintWriter(stringWriter);
	        e.printStackTrace(writer);
	        StringBuffer buffer= stringWriter.getBuffer();
			return buffer.toString();
		}
	}
}






