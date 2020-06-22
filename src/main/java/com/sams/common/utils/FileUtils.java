package com.sams.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;

import com.google.common.collect.Maps;
import com.sams.common.exception.ExceptionConStants;

/**
 * 文件处理工具
 * @author hq 2016-8-24 22:46:33
 *
 */
public class FileUtils {
	
	/**
	 * 写文件
	 * @param content
	 * @param saveFilePath
	 */
	public static void writeFile(String content, String saveFilePath) {
		try {
			FileOutputStream out = new FileOutputStream(saveFilePath); // 输出文件路径
			out.write(content.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读文件
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		String content = "";
		try {
			FileInputStream in = new FileInputStream(filePath); // 读取文件路径
			byte bs[] = new byte[in.available()];
			in.read(bs);
			content = new String(bs);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 获取windows环境下的磁盘空间大小
	 * @param String fileName
	 * @return
	 */
	public static float getDiskSpaceUsed (String fileName){
		File file = new File(fileName);
		//磁盘剩余空间的大小
		float totalDiskSpace = file.getTotalSpace()/1024/1024/1024;
		float freeDiskSpace = file.getFreeSpace()/1024/1024/1024;
		float usedDiskSpace = totalDiskSpace-freeDiskSpace; 
		float diskSpaceUsed = usedDiskSpace/totalDiskSpace;
		return diskSpaceUsed;
	}
	
	public static void ifMakeDir(String url){
		File myDir = new File(url+File.separator);
		if(!myDir.exists()){
			myDir.mkdirs();
		}
	}
	
	/**
	 * 获取操作系统的名称
	 * @return
	 */
	public static String getSystemName(){
		String systemName = System.getProperties().getProperty("os.name");
		return systemName;
	}
	
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 将本地文件进行备份
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: void
	 * @throws Exception 
	 */
	public static Map<String,Object> copyFile(String url,String businessDate){
		Map<String,Object> retMap =Maps.newHashMap();
		File[]  files = new File(url).listFiles();
		if(files!=null&&files.length!=0){
			 String copyUrl = new File(url).getParent()+"/"+"copy"+businessDate;
			 FileUtils.ifMakeDir(copyUrl);
			//删除拷贝路径下的全部文件
			 File[] copyFiles = new File(copyUrl).listFiles();
			 if(copyFiles!=null&&copyFiles.length!=0){
				 for(File f:copyFiles){
					 new File(copyUrl+File.separator+f.getName()).delete();
				 }
			 }
				 //将目录文件拷贝
				 File[] ffiles = new File(url).listFiles();
				 for(File f:ffiles){
					 File source = new File(url+File.separator+f.getName());
					 File dest = new File(copyUrl+File.separator+f.getName());
					 FileChannel inputChannel;
					try {
						inputChannel = new FileInputStream(source).getChannel();
						FileChannel outputChannel = new FileOutputStream(dest).getChannel();
					    try {
							outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						} catch (IOException e) {
							e.printStackTrace();
						} 
					     new File(url+File.separator+f.getName()).delete();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				 }
				 
				 //将目录文件删除
				 if(ffiles!=null&&ffiles.length!=0){
					 for(File f:ffiles){
						 new File(ffiles+File.separator+f.getName()).delete();
					 }
				 }
				 retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);    
		}else{
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);   
		}
		return retMap;
	}
	
	public static void main(String[] args) {
		FileUtils f= new FileUtils();
		System.out.println(System.getProperties().getProperty("os.name"));
		System.out.println(System.getProperties().getProperty("os.version"));
		System.out.println(System.getProperties().getProperty("user.dir"));
		//System.out.println(f.getDiskSpaceUsed();
	}
}
