package com.sams.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.sams.custom.model.ChannelInfo;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.AccountReqTmpService;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.ExchangeReqTmpService;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.FileUtils;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.service.impl.SysDictServiceImpl;


public class FTPUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(FTPUtils.class);
	public static  FTPClient client;
	public int connectCount=0;
	public static FTPUtils singleFtp;
	@Autowired
	private ChannelInfoService channelInfoService;
	@Autowired
	private FundMarketCfmServier fundMarketCfmServier;
	@Autowired
	private AccountReqTmpService accountReqTmpService;
	@Autowired
	private ExchangeReqTmpService exchangeReqTmpService;
	
	public FTPUtils() {
		super();
	}
 
	//单例模式：懒汉模式
	public static void getInstance() {
		if(client == null){
			client = new FTPClient();
		}
	}
	
	public static void getCloseFtpClient() {
		closeFtpClient();
	}

	/**
	 * @author: wangchao 2019年4月8日 上午9:13:49
	 * @Description: 校验FTP服务器是否可连接  
	 * @param:  hostname ip地址，port 端口号
	 * @return: retMap<String,Object>      
	 */
	public Map<String,Object> checkConnect(String hostname,int port){
		Map<String,Object> retMap = Maps.newHashMap();
		try {
			//设置三十秒超时时间
			client.setConnectTimeout(30000);
			client.connect(hostname, port);
			int replyCode1 = client.getReplyCode();
			if(220 ==replyCode1){
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				LOGGER.info("ftpClient允许连接");
			}else{
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0001);
				LOGGER.info("ftpClient拒绝连接,iP为:"+hostname+",端口为:"+port);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0001,e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午9:26:49
	 * @Description: 校验FTP服务器用户名密码是否正确  
	 * @param:  username 用户名，password 密码
	 * @return: retMap<String,Object>      
	 */
	
	public Map<String,Object> checkLogin(String username,String password){
		Map<String,Object> retMap = Maps.newHashMap();
		try {
			client.login(username, password);
			int replyCode1 = client.getReplyCode();
			if(230 ==replyCode1){
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				LOGGER.info("ftpClient允许登陆");
			}else{
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0002);
				LOGGER.info("ftpClient拒绝登陆,用户名为:"+username+"密码为:"+password);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0002,e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午9:45:40
	 * @Description: 校验FTP服务器的连接状态
	 * @param:null
	 * @return: retMap<String,Object>      
	 */
	public Map<String,Object> checkStatus(){
		Map<String,Object> retMap = Maps.newHashMap();
		try {
			String status= client.getStatus();
			int replyCode1 = client.getReplyCode();
			if(!"".equals(status)){
				client.enterLocalPassiveMode();
				client.setFileType(FTP.BINARY_FILE_TYPE);
				/*设置编码格式*/
				client.setControlEncoding("UTF-8");
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				LOGGER.info("ftpClient状态可用");
			}else{
				client.disconnect();
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0003);
				LOGGER.info("ftpClient状态不可用");
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0003,e.getMessage());
		}
		return retMap;
	}

	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 进入FTP服务器
	 * @param: hostname ip地址，port 端口号 username 用户名，password 密码
	 * @return: FTPClient      
	 */
	public Map<String,Object> getFtpClient(String hostname, int port,String username,String password){
		Map<String,Object> retMap = Maps.newHashMap();
		//判断是否可连接 
		retMap = checkConnect(hostname, port);
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
		//判断是否可登入
		retMap = checkLogin(username, password);
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
		//判断当前FTP是否可用
		retMap = checkStatus();
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
		return retMap;
	}
	
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:44:56
	 * @Description: 校验是否在传入参数的目录
	 * @param:url:路径
	 * @return: boolean      
	 */
	public boolean changeWorkingDirectory(String url) throws IOException{
		    boolean flag = false;
			String directory = url.substring(0, url.lastIndexOf("/") +1);
	        // 如果远程目录不存在，则递归创建远程服务器目录
	        if (!directory.equalsIgnoreCase("/") ) {
	            int start = 0;
	            int end = 0;
	            if (directory.startsWith("/")) {
	                start = 1;
	            } else {
	                start = 0;
	            }
	            end = directory.indexOf("/", start);
	            String path = "";
	            String paths = "";
	            while (true) {
	                String subDirectory = new String(url.substring(start, end).getBytes("GBK"), "iso-8859-1");
	                path = path + "/" + subDirectory;
	                if (!existFile(path)) {
	                	makeDirectory(subDirectory);
	                	flag =client.changeWorkingDirectory(path);
	                } else {
	                    flag =client.changeWorkingDirectory(path);
	                }
	                paths = paths + "/" + subDirectory;
	                start = end + 1;
	                end = directory.indexOf("/", start);
	                // 检查所有目录是否创建完毕
	                if (end <= start) {
	                    break;
	                }
	            }
	        }
		    return flag;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午11:13:49
	 * @Description: 判断ftp服务器文件是否存在    
	 * @param:file:path 路径
	 * @return: boolean   
	 */
	public boolean existFile(String path) throws IOException {
	        boolean flag = false;
	        FTPFile[] ftpFileArr = client.listFiles(path);
	        if (ftpFileArr.length > 0) {
	            flag = true;
	        }
	        return flag;
	    }
	
	/**
	 * @author: wangchao 2019年4月8日 上午11:13:49
	 * @Description: 创建目录
	 * @param:file:dir 路径
	 * @return: Map<String,Object>   
	 */
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = client.makeDirectory(dir);
        } catch (Exception e) {
            e.printStackTrace();
			LOGGER.info(e.getMessage());
        }
        return flag;
    }

    /**
	 * @author: wangchao 2019年4月8日 上午11:13:49
	 * @Description: 判断文件是否有数据
	 * @param:file:文件
	 * @return: Map<String,Object>   
	 */
	public Map<String,Object> checkAndStoreFile(String url,String ftpurl,String channelCode,String businessDate,String fileType){
		Map<String,Object> retMap = Maps.newHashMap();
		//根据路径拿到文件
		File file = new File(url);
		retMap = checkFile(file);
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
		try {
			//传输本地数据到FTP
			retMap = openFileIsSave(file, ftpurl,channelCode,businessDate, fileType);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0010,"文件上传异常,异常信息为:"+e.getMessage());
		}
		return retMap;
	}
	/**
	 * @author: wangchao 2019年4月8日 上午11:13:49
	 * @Description: 判断文件是否为空是否有数据
	 * @param:file:文件
	 * @return:  Map<String,Object>   
	 */
	public Map<String,Object> checkFile(File file){
		Map<String,Object> retMap = Maps.newHashMap();
		if(file.getName().contains(".OK")){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			return retMap;
		}else{
			if(file!=null&&file.length()!=0){
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				LOGGER.info("FTP拿到的文件"+file.getName()+"不为空且有数据");
			}else{
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0007);
				LOGGER.info(file.getName()+"文件为空");
			}
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 下午1:17:49
	 * @Description: 判断FTP文件是否有数据
	 * @param:file:FTP文件
	 * @return: boolean   
	 * @throws IOException 
	 */
	/*public Map<String,Object> checkFTPFile(String path,String businessDate){
		Map<String,Object> retMap = Maps.newHashMap();
		boolean containBusinessDate = false;
		try {
			FTPFile[] files = client.listFiles(path);
			if(files==null||files.length==0){
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0013);
				retMap.put("retMsg", "FTP路径为:"+path+"的文件为空");
				LOGGER.info("FTP相对应与本地相对应目录下的文件不存在");
				return retMap;
			}
			for(FTPFile ftpFile:files){
				if(ftpFile.getName().toUpperCase().indexOf(Const.FILE_OK)!=-1
						||ftpFile.getName().indexOf(Const.FILE_BAK)!=-1){
					continue;
				} 
				if(ftpFile.getName().indexOf(businessDate)!=-1){
					containBusinessDate =true;
				}
			}
			
			if(!containBusinessDate){
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0022);
				LOGGER.info("遍历"+path+"目录,未搜寻到"+businessDate+"日期的文件");
			}else{
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}
		} catch (IOException e) {
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0013);
			retMap.put("retMsg", e.getMessage());
		}
		return retMap;
	}
	*/
	/**
	 * @author: wangchao 2019年4月8日  下午1:50:41
	 * @Description: 校验本地文件列表名称与预期列表名称是否一致
	 * @param:file:文件
	 * @return: localArrayList本地列表名称    nameList预期列表名称
	 */
	public Map<String,Object> checkUploadNameList(List<String> localArrayList,List<String> nameList){
		Map<String,Object> retMap =Maps.newHashMap();
		for(String name:nameList){
			if(!localArrayList.contains(name)){
				LOGGER.info("本地上传文件夹中不包含"+name+"文件");
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0019);  return retMap;
			}else{
				retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}
		}
		return retMap;
	}
	
	
	/**
	 * @author: wangchao 2019年4月8日  下午1:50:41
	 * @Description: 上传完毕之后校验FTP文件是否与预期文件相一致
	 * @param:file:文件
	 * @return: localArrayList本地列表名称    nameList预期列表名称
	 */
	public Map<String,Object> checkFTPUploadFile(List<String> nameList, String ftpUrl){
		Map<String,Object> retMap =Maps.newHashMap();
		boolean fileDataExist = true;//单个文件是否存在
		String retMsg = "";
		//校验FTP路径中文件是否与预估文件名称一致
		try{
		  for(int i = 0; i < nameList.size(); i++){
				InputStream is = null;
				//OK文件不进行校验
				if(nameList.get(i).toUpperCase().contains(Const.FILE_OK)){
					continue;
				}
				String ftpFilePath = ftpUrl+nameList.get(i);
				String smallWrite = nameList.get(i).split("\\.")[0]+"."+(nameList.get(i).split("\\.")[1].toLowerCase());
				//字符串以.切割需要转译,FTP.DEFAULT_CONTROL_ENCODING=ISO-8859-1,判断该文件是否存在于FTP (.TXT)
				is = client.retrieveFileStream(new String(ftpFilePath.getBytes("GBK"),FTP.DEFAULT_CONTROL_ENCODING));
				//nameList中都是.TXT,有些渠道发过来的是小写的.txt,需要再次校验 
				if(is == null || client.getReplyCode() == FTPReply.FILE_UNAVAILABLE){
					ftpFilePath = ftpUrl+smallWrite;
					is = client.retrieveFileStream(new String(ftpFilePath.getBytes("GBK"),FTP.DEFAULT_CONTROL_ENCODING));
				}
				//如果文件输入流为空或者 FTPReply.FILE_UNAVAILABLE=550 未执行请求的操作,该文件不存在报错
				if (is == null || client.getReplyCode() == FTPReply.FILE_UNAVAILABLE){
					fileDataExist = false;
					retMsg = retMsg+","+nameList.get(i);
					LOGGER.info(nameList.get(i)+"文件在"+ftpUrl+"路径下不存在");
				}
				//如果文件输入流不为空,该文件存在,继续校验
				if (is != null) {
					LOGGER.info(nameList.get(i)+"文件在"+ftpUrl+"路径下存在");
	                is.close();
	                client.completePendingCommand();
	                retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
	            }
				
				//校验FTP文件是否全部存在，不存在则返回错误信息
				if(!fileDataExist){
					return retMap= ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0023,retMsg+"文件在"+ftpUrl+"路径下不存在");
				}
		   }
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap= ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0020,e.getMessage());
			
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日  下午1:50:41
	 * @Description: 传输本地数据到FTP
	 * @param:file:文件
	 * @return: FileInputStream   
	 */
	public Map<String,Object> openFileIsSave(File file,String ftpurl,String channelCode,String businessDate,String fileType){
		Map<String,Object> retMap =Maps.newHashMap();
		try {
			FileInputStream is = null;
			//单个文件上传成功标志
			boolean flag = false;
			//是否所有文件上传成功标志
			boolean uploadFlag = true;
			File[] localList = file.listFiles();
			//本地文件
			List<String> localArrayList = new ArrayList<String>();
			for(int i = 0; i < localList.length; i++){
				localArrayList.add(localList[i].getName());
			}
			//通过渠道勾选的文件名称逻辑获取需要上传的文件名称
			List<String> nameList = getUploadNameList(channelCode, businessDate, fileType);
			LOGGER.info(channelCode+"通过渠道勾选的文件名称逻辑获取需要上传的文件名称为"+nameList.toString());
			//校验本地文件是否包含逻辑获取的名称
			retMap = checkUploadNameList(localArrayList, nameList);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))return retMap;
			List<File> fileDataList = new ArrayList<File>();
			List<File> fileOkList = new ArrayList<File>();
			for(int i = 0; i < localList.length; i++){
				if(!nameList.contains(localList[i].getName())){
					continue;
				}
				//解决深圳通上扫描OK文件早于数据文件的问题 20200224 王超
				if(localList[i].getName().toUpperCase().contains(Const.FILE_OK)){
					fileOkList.add(localList[i]);
				}else{
					fileDataList.add(localList[i]);
				}
		    }
			
			LOGGER.info(channelCode+"数据文件及索引文件开始上传");
			for(int i = 0; i < fileDataList.size(); i++){
				 is = new FileInputStream(fileDataList.get(i));
				LOGGER.info(fileDataList.get(i).getName()+"文件输入流创建成功");
				String path = ftpurl+fileDataList.get(i).getName();
				flag = client.storeFile(path, is);
				if(flag){
					LOGGER.info(fileDataList.get(i).getName()+"上传成功");	
				}else{
					uploadFlag = false;
					LOGGER.info(fileDataList.get(i).getName()+"上传失败");
				}
				is.close();
			}
			
			try {
				Thread.sleep(1000);
				LOGGER.info(channelCode+"数据文件上传完毕，沉睡一秒上传OK文件");
			} catch (InterruptedException e) {
				LOGGER.info(e.getMessage());
				e.printStackTrace();
				LOGGER.info(e.getMessage());
				return ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999,e.getMessage());			
			}
			
			LOGGER.info(channelCode+"数据文件及索引文件的OK文件开始上传");
			for(int i = 0; i < fileOkList.size(); i++){
				 is = new FileInputStream(fileOkList.get(i));
				LOGGER.info(fileOkList.get(i).getName()+"文件输入流创建成功");
				String path = ftpurl+fileOkList.get(i).getName();
				flag = client.storeFile(path, is);
				if(flag){
					LOGGER.info(fileOkList.get(i).getName()+"上传成功");	
				}else{
					uploadFlag = false;
					LOGGER.info(fileOkList.get(i).getName()+"上传失败");
				}
				is.close();
			}
			
			//判断是否是上传失败的情况，如果有失败的，返回错误信息，重新上传
			if(!uploadFlag){
				return ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0023,ExceptionConStants.retMsg_FT0023);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999,e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 下午5:02:21
	 * @Description: 检查输入的FTPUrl在FTP上是否存在，不存在则循环创建目录，创建完成校验文件并上传
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: string 0:上传失败 1：上传成功
	 * @throws Exception 
	 */
	public Map<String,Object> checkFTPisPositiveCompletion(String url,String FTPUrl,String channelCode,String businessDate,String fileType){
		Map<String,Object> retMap = Maps.newHashMap();
		boolean createDirFlag = false;
		boolean ftpPathIsExist = false;
		//检查FTPClient是否处于连接状态
		try {
			//校验FTP目录是否存在
			ftpPathIsExist =client.changeWorkingDirectory(FTPUrl);
			//不存在则调用循环创建目录的方法
			if(!ftpPathIsExist){
				//检查目录是否为当前路径下，不在则循环创建目录
				createDirFlag= changeWorkingDirectory(FTPUrl);
				//检查本地目录下是否有文件，有则创建文件输入流
				if(createDirFlag){
					//检查本地目录下是否有文件，有则创建文件输入流并上传文件
					retMap = checkAndStoreFile(url, FTPUrl, channelCode, businessDate, fileType);
					if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))
					return retMap;
				}else{
					return ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0004);
				}
			}else{
				//检查本地目录下是否有文件，有则创建文件输入流并上传文件
				retMap = checkAndStoreFile(url, FTPUrl, channelCode, businessDate, fileType);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode")))
				return retMap;
			}
			
		} catch (IOException e) {
			LOGGER.info("循环创建目录的方法出错，调用listFiles方法出错");
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap= ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0004,e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 下午5:02:21
	 * @Description: 获取FTP参数
	 * @return: Map<String,Object> map
	 */
	public Map<String,Object> chechDictBydictCode(){
		Map<String,Object> output = Maps.newHashMap();
		Map<String,Object> retMap = Maps.newHashMap();
		List<String> list = new ArrayList<String>();
		list.add(Const.FTP_IP);
		list.add(Const.FTP_PORT);
		list.add(Const.FTP_USER);
		list.add(Const.FTP_PASSWD);
		list.add(Const.FTP_LOCALDIR);
		list.add(Const.FTP_REMOTE);
		list.add(Const.FUND_SEND_END_DATE);
		list.add(Const.FTP_LOG_DIR);
		list.add(Const.FTP_LOG_PREFIX);
		list.add(Const.FTP_LOG_POSTFIX);
		list.add(Const.FTP_FILE_DIR);
		for(int i=0;i<list.size();i++){
			output = SysDictServiceImpl.findDictVoByDictType(list.get(i));
			if(!(ExceptionConStants.retCode_0000).equals(output.get("retCode")))return retMap;
			retMap.put(list.get(i), (String)output.get("dictCode"));
			retMap.putAll(output);
		}
		return retMap;
	}
	
	
	
	/**
	 * @author: wangchao 2019年4月8日 下午5:02:21
	 * @Description: FTP上传操作步骤集合
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: string 0:上传失败 1：上传成功
	 * @throws Exception 
	 */
	public Map<String,Object> uploadConFile(Map<String,Object> map){
		Map<String,Object> retMap = Maps.newHashMap();
		try{
			retMap.putAll(map);
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			
			String channelCode = MapUtils.getString(map, "CHANNELCODE","");
			
			String fileType = MapUtils.getString(map, Const.FUND_FILETYPE,"");
			String tradeDate = MapUtils.getString(map,"TRADEDATE","");
			if(""==tradeDate){
				 tradeDate = MapUtils.getString(map,"TRANSDATE","");
			}
			
//			String userName = MapUtils.getString(map,"USERNAME","");
			
			Map<String,Object> dictMap = chechDictBydictCode();
			if(!(ExceptionConStants.retCode_0000).equals(dictMap.get("retCode")))return dictMap;
			
			String ftplocaldir =MapUtils.getString(dictMap, Const.FTP_LOCALDIR,"");
			
			String ftpremote =MapUtils.getString(dictMap, Const.FTP_REMOTE,"/");
			
			String url = ftplocaldir+File.separator+Const.FTP_UPLOAD+File.separator+channelCode+File.separator+tradeDate+File.separator;
			String FTPUrl = channelInfo.getCiSztSendPath(); //深证通发送路径
			if(StringUtils.isEmpty(FTPUrl)){
				/*FTPUrl = ftpremote+Const.FTP_UPLOAD+"/"+channelCode+"/"+businessDate+"/";*/
				FTPUrl = ftpremote+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/";
				/*FTPUrl = "/"+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/";*/
				//上海农商行特殊处理
				if(Const.CHANNELCODESHNS.equals(channelCode)&&Const.FILE_TYPE_07.equals(fileType)){
			    //上传07文件在fundday文件夹下
				FTPUrl = ftpremote+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/"+Const.UPLOADSHNS07+"/";
					/*FTPUrl = "/"+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/"+Const.UPLOADSHNS07+"/";*/
				}else if(Const.CHANNELCODESHNS.equals(channelCode)&&!Const.FILE_TYPE_07.equals(fileType)){
					//上传其他文件在confirm文件夹下
					FTPUrl = ftpremote+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/"+Const.UPLOADSHNSOTHER+"/";
					/*FTPUrl = "/"+Const.FTP_REMOTE_UPLOAD_DIR+"/"+channelCode+"/"+Const.UPLOADSHNSOTHER+"/";*/
				}
			}else{
				if(!FTPUrl.endsWith("/") && !FTPUrl.endsWith(File.separator)){
					FTPUrl+="/";
				}
			}
	        LOGGER.info("本地数据路径为"+url);	
	        LOGGER.info("FTP上传路径为"+FTPUrl);
	       
	        //校验FTP连接
	        retMap =  checkFTPConnect(dictMap);
	        if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))) 
	        	return retMap;
	        //检查输入的FTPUrl在FTP上是否存在，不存在则循环创建目录，创建完成校验文件并上传
			retMap = checkFTPisPositiveCompletion(url, FTPUrl, channelCode, tradeDate, fileType);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap= ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999,e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年10月25日 上午午9:02:21
	 * @Description:校验FTP连接是否成功
	 * @param:Map<String,Object> intoMap
	 */
	public Map<String,Object> checkFTPConnect(Map<String,Object> intoMap){
		Map<String,Object> retMap = Maps.newHashMap();
		String hostname=MapUtils.getString(intoMap, Const.FTP_IP,"");//FTP地址
		int port =Integer.parseInt(MapUtils.getString(intoMap, Const.FTP_PORT,"0"));//FTP端口号
		String username =MapUtils.getString(intoMap, Const.FTP_USER,"");//FTP用户名
		String password =MapUtils.getString(intoMap, Const.FTP_PASSWD,"");//FTP密码
		
		 //FTP没有实例化先实例化
        if(null == client){
        	getInstance();
        }
        
		//判断FTP是否已连接
		if (!client.isConnected()) {
			//重新连接FTP服务器
			retMap = getFtpClient(hostname, port, username, password);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))){
				//重连三次
				for(int i=1;connectCount<=3;i++){
					connectCount=++connectCount;
					retMap=checkFTPConnect(intoMap);
					LOGGER.info("重连第"+connectCount+"次,返回数据为:"+retMap.toString());
					if((ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))break;
				}
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))) return retMap;
			}
	    }else{
	    	return ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);	
	    }
		return retMap;
		
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 整个下载过程的入口
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: void
	 * @throws Exception 
	 */
	public Map<String,Object> downloadConFile(Map<String,Object> map){
		Map<String,Object> retMap = Maps.newHashMap();
		retMap.putAll(map);
		
		PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
		String channelCode = MapUtils.getString(map, "CHANNELCODE","");
		String specialFile = MapUtils.getString(map, "SPECIALFILE","");
		String businessDate =MapUtils.getString(map, "TRANSDATE","");
		Map<String,Object> dictMap = chechDictBydictCode();
		if(!(ExceptionConStants.retCode_0000).equals(dictMap.get("retCode")))return dictMap;
		
		String ftplocaldir =MapUtils.getString(dictMap, Const.FTP_LOCALDIR,"");
		
		String ftpremote =MapUtils.getString(dictMap, Const.FTP_REMOTE,"/");
		
		String url = ftplocaldir+Const.FTP_DOWNLOAD+File.separator+channelCode+File.separator+businessDate+File.separator;
		String FTPUrl = channelInfo.getCiSztRecvPath(); //深证通接收路径
		if(StringUtils.isEmpty(FTPUrl)){
			FTPUrl = ftpremote+Const.FTP_REMOTE_DOWNLOAD_DIR+"/"+channelCode+"/";
			/*FTPUrl = "/"+Const.FTP_REMOTE_DOWNLOAD_DIR+"/"+channelCode+"/";*/
		}else{
			if(!FTPUrl.endsWith("/") && !FTPUrl.endsWith(File.separator)){
				FTPUrl+="/";
			}
		}
		LOGGER.info("FTP下载路径为"+FTPUrl);
		LOGGER.info("本地下载路径为"+url);
		
	
		//校验FTP连接
		retMap = checkFTPConnect(dictMap);
		if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))
			return retMap;
		//校验在FTP中是否存在FTPurl这个目录，有再校验这个目录下的是否有文件
		FileUtils.ifMakeDir(url);
		 /*retMap = getAndCheckFTPFile(FTPUrl,url,channelCode,businessDate,specialFile);
		if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))
			return retMap;*/
		//下载并校验本地文件
		retMap = retrieveAndCheckFile(FTPUrl,url,channelCode,businessDate,specialFile);
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 校验在FTP中是否存在FTPurl这个目录，有再校验这个目录下的是否有文件
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: void
	 * @throws Exception 
	 */
	public Map<String,Object> getAndCheckFTPFile(String FTPurl,String url,String channelCode,String businessDate,String specialFile){
		Map<String,Object> retMap =Maps.newHashMap();
		try {
			    //校验传入的url是否存在,不存在则创建目录
			    FileUtils.ifMakeDir(url);
				//校验该FTPurl下是否有文件及文件是否为空
				/*retMap = checkFTPFile(FTPurl,businessDate);
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;*/
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0012,e.getMessage());
		}
		return retMap;
	}

	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 判断渠道下 交易确认表以及账户确认表是否有发送TA的数据
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: void
	 * @throws Exception 
	 */
	public Map<String,Object> checkAccountReqAndExchangeReq(String businessDate,String channelCode){
		Map<String,Object> retMap =Maps.newHashMap();
		accountReqTmpService=(AccountReqTmpService) SpringUtils.getBean(AccountReqTmpService.class);
		exchangeReqTmpService=(ExchangeReqTmpService) SpringUtils.getBean(ExchangeReqTmpService.class);
		Map<String,String> accountMap = Maps.newHashMap();
		accountMap.put("arcTransactionDate", businessDate);
		accountMap.put("arcChannelCode", channelCode);
		BigDecimal countAccount =  accountReqTmpService.selectCount(accountMap);
		Map<String,String> changeMap = Maps.newHashMap();
		changeMap.put("ercTransactionDate", businessDate);
		changeMap.put("ercChannelCode", channelCode);
		BigDecimal changeAccount =  exchangeReqTmpService.selectCount(changeMap);
		int account = countAccount.compareTo(new BigDecimal("0"));
		int change = changeAccount.compareTo(new BigDecimal("0"));
		if(account==1){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0017);	return retMap;
		}
		if(change==1){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0018);	return retMap;
		}
		retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		return retMap;
	}
	
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 获取文件名称
	 * @param: channelCode 渠道号, businessDate 交易日期,fileType 文件类型
	 * @return:  Map<String,Object>
	 * @throws Exception 
	 */
	public Map<String,Object> getNameMaps(String channelCode,String businessDate,String fileType){
		Map<String,Object> retMap =Maps.newHashMap();
		channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
		PchannelInfo info =  channelInfoService.queryChannelInfoByChannelCode(channelCode);
		String dealFile = info.getCiDealFile();
		String file26Type = info.getCiVolDetailType();
		String[] dealFiles = dealFile.split(",");
		List<String> nameList = new ArrayList<String>();
		String saleAgent = info.getCiSaleAgentCode();
		String salesPerson = info.getCiSalesPerson();
		Map<String,String> IndexNameMap = uploadIndexFileName(saleAgent,businessDate,salesPerson);
		Map<String,String> dataNameMap = uploadDataFileName(saleAgent,businessDate,fileType,salesPerson);
		retMap.put("INDEXNAMEMAP", IndexNameMap);
		retMap.put("DATANAMEMAP", dataNameMap);
		retMap.put("DEALFILES", dealFiles);
		retMap.put("SALEAGENT", saleAgent);
		retMap.put("SALESPERSON", salesPerson);
		retMap.put("FILE26TYPE", file26Type);
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 获取上传文件名称
	 * @param: channelCode 渠道号, businessDate 交易日期,fileType 文件类型
	 * @return:  Map<String,Object>
	 * @throws Exception 
	 */
	public List<String> getUploadNameList(String channelCode,String businessDate,String fileType){
		Map<String,Object> retMap =Maps.newHashMap();
		List<String> nameList = new ArrayList<String>();
		retMap = getNameMaps(channelCode, businessDate, fileType);
		Map<String,String> IndexNameMap = (Map)retMap.get("INDEXNAMEMAP");
		Map<String,String> dataNameMap = (Map)retMap.get("DATANAMEMAP");
		String file26Type = (String)retMap.get("FILE26TYPE");
		String saleAgent = (String)retMap.get("SALEAGENT");
		String salesPerson = (String)retMap.get("SALESPERSON");
		String[] dealFiles = (String[])retMap.get("DEALFILES");
		List<String> getDealList = Arrays.asList(dealFiles);
		List<String> dealList = new ArrayList<String>();
		dealList.add(Const.FILE_TYPE_05);
		dealList.add(Const.FILE_TYPE_06);
		
		if(null!=file26Type&& !"".equals(file26Type)){
			dealList.add(Const.FILE_TYPE_26);
		}
		for(int i=0;i<getDealList.size();i++){
			if(Const.FILE_TYPE_43.equals(getDealList.get(i))){
				dealList.add(Const.FILE_TYPE_44);
			}else if(Const.FILE_TYPE_01.equals(getDealList.get(i))){
				dealList.add(Const.FILE_TYPE_02);
			}else if(Const.FILE_TYPE_03.equals(getDealList.get(i))){
				dealList.add(Const.FILE_TYPE_04);
			}else if(Const.FILE_TYPE_R1.equals(getDealList.get(i))){
				dealList.add(Const.FILE_TYPE_R2);
			}
		}
		if(Const.FILE_TYPE_07.equals(fileType)){
			nameList.add(IndexNameMap.get("OFJ"));
			nameList.add(IndexNameMap.get("OFJOK"));
			nameList.add(dataNameMap.get("OFD"));
			nameList.add(dataNameMap.get("OFDOK"));
			
		}else if(Const.FILE_TYPE_94.equals(fileType)){
			nameList.add(IndexNameMap.get("OFY"));
			nameList.add(IndexNameMap.get("OFYOK"));
			nameList.add(dataNameMap.get("OFD"));
			nameList.add(dataNameMap.get("OFDOK"));
		}else if(!fileType.isEmpty() && Const.ALLFILETYPE.contains(fileType)){
			if(Const.FILE_TYPE_44.equals(fileType)){
				nameList.add(IndexNameMap.get("OFG"));
				nameList.add(IndexNameMap.get("OFGOK"));
			}else{
				nameList.add(IndexNameMap.get("OFI"));
				nameList.add(IndexNameMap.get("OFIOK"));
			}
			nameList.add(dataNameMap.get("OFD"));
			nameList.add(dataNameMap.get("OFDOK"));
		}else{
			for(int i=0;i<dealList.size();i++){
				Map<String,String> dataNameMapOther = uploadDataFileName(saleAgent,businessDate,dealList.get(i),salesPerson);
				nameList.add(dataNameMapOther.get(Const.FILE_OFD));
				nameList.add(dataNameMapOther.get(Const.FILE_OFD_OK));
				if(Const.FILE_TYPE_44.equals(dealList.get(i))){
					nameList.add(IndexNameMap.get(Const.FILE_OFG));
					nameList.add(IndexNameMap.get(Const.FILE_OFG_OK));
				}else{
					if(!nameList.contains("OFI_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT")){
						nameList.add(IndexNameMap.get(Const.FILE_OFI));
					}else if(!nameList.contains("OFI_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT.OK")){
						nameList.add(IndexNameMap.get(Const.FILE_OFI_OK));
					}
				}
			}
			
			
		}
		LOGGER.info(channelCode+"逻辑获取需要上传文件名称"+nameList.toString());
		return nameList;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 获取上传索引文件名称
	 * @param: channelCode 渠道号, businessDate 交易日期,fileType 文件类型
	 * @return:  Map<String,Object>
	 * @throws Exception 
	 */
	public Map<String,String> uploadIndexFileName(String saleAgent,String businessDate,String salesPerson){
		Map<String,String> retMap =Maps.newHashMap();
		retMap.put("OFI", "OFI_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT");//OFJ_SH_BXB_20190304.TXT
		retMap.put("OFIOK", "OFI_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT.OK");
		retMap.put("OFG", "OFG_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT");
		retMap.put("OFGOK", "OFG_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT.OK");
		retMap.put("OFJ", "OFJ_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT");
		retMap.put("OFJOK", "OFJ_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT.OK");
		retMap.put("OFY", "OFY_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT");
		retMap.put("OFYOK", "OFY_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+".TXT.OK");
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 获取下载索引文件名称
	 * @param: channelCode 渠道号, businessDate 交易日期,fileType 文件类型
	 * @return:  Map<String,Object>
	 * @throws Exception 
	 */
	public Map<String,String> downloadIndexFileName(String saleAgent,String businessDate,String salesPerson){
		Map<String,String> retMap =Maps.newHashMap();
		retMap.put("OFI", "OFI_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT");//OFJ_SH_BXB_20190304.TXT
		retMap.put("OFIOK", "OFI_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT.OK");
		retMap.put("OFG", "OFG_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT");
		retMap.put("OFGOK", "OFG_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT.OK");
		retMap.put("OFJ", "OFJ_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT");
		retMap.put("OFJOK", "OFJ_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT.OK");
		retMap.put("OFY", "OFY_"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT");
		retMap.put("OFYOK", "OFY"+saleAgent+"_"+salesPerson+Const.FIlE_+businessDate+".TXT.OK");
		return retMap;
	}

	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 获取上传数据文件名称
	 * @param: channelCode 渠道号, businessDate 交易日期,fileType 文件类型
	 * @return:  Map<String,Object>
	 * @throws Exception 
	 */
	public Map<String,String> uploadDataFileName(String saleAgent,String businessDate,String fileType,String salesPerson){
		Map<String,String> retMap =Maps.newHashMap();
		retMap.put("OFD", "OFD_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+"_"+fileType+".TXT");//OFD_SH_BXB_20190304_07.TXT
		retMap.put("OFDOK", "OFD_"+salesPerson+Const.FIlE_+saleAgent+"_"+businessDate+"_"+fileType+".TXT.OK");
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 获取下载数据文件名称
	 * @param: channelCode 渠道号, businessDate 交易日期,fileType 文件类型
	 * @return:  Map<String,Object>
	 * @throws Exception 
	 */
	public Map<String,String> downloadDataFileName(String saleAgent,String businessDate,String fileType,String salesPerson){
		Map<String,String> retMap =Maps.newHashMap();
		retMap.put("OFD", "OFD_"+saleAgent+Const.FIlE_+salesPerson+Const.FIlE_+businessDate+"_"+fileType+".TXT");//OFD_SH_BXB_20190304_07.TXT
		retMap.put("OFDOK", "OFD_"+saleAgent+Const.FIlE_+salesPerson+Const.FIlE_+businessDate+"_"+fileType+".TXT.OK");
		return retMap;
	}

	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 判断渠道下预计的文件个数以及文件名称是否与FTP路径下的相匹配
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: void
	 * @throws Exception 
	 */
	public Map<String,Object> checkChannelDetailFile(String FTPurl,String url,String channelCode,String businessDate){
		FTPFile[] FTPFiles;
		Map<String,Object> retMap =Maps.newHashMap();
		List<String> nameList = new ArrayList<String>();
		retMap = getNameMaps(channelCode, businessDate, "");
		String saleAgent = (String)retMap.get("SALEAGENT");
		String salesPerson = (String)retMap.get("SALESPERSON");
		String[] dealFiles = (String[])retMap.get("DEALFILES");
		int total = 0;
		Map<String,String> indexNameMap = downloadIndexFileName(saleAgent,businessDate,salesPerson);
		//43文件是单独的一个索引文件 OFG开头
		for(int i=0;i<dealFiles.length;i++){
			Map<String,String> dataNameMapOther = downloadDataFileName(saleAgent,businessDate,dealFiles[i],salesPerson);
			nameList.add(dataNameMapOther.get("OFD"));
			if(Const.FILE_TYPE_43.equals(dealFiles[i])){
				nameList.add(indexNameMap.get("OFG"));//OFG_009_SH_20190416.TXT 格式
			}else{
				if(nameList.contains(indexNameMap.get("OFI"))){
					nameList.remove(indexNameMap.get("OFI"));
					nameList.add(indexNameMap.get("OFI"));
				}else{
					nameList.add(indexNameMap.get("OFI"));
				}
			}
		}
		try {
			/*FTPFiles = client.listFiles(FTPurl);
			
			List<FTPFile> FtpList = new ArrayList<>();
			List<String> FtpNameList = new ArrayList<>();
			//包含.OK和.bak后缀的文件不算数
			for(FTPFile f:FTPFiles){
				if(!f.getName().toUpperCase().contains(Const.FILE_OK)&&!f.getName().toLowerCase().contains(Const.FILE_BAK) && f.getName().contains(businessDate)&& f.getName().toUpperCase().contains(".TXT")){
					FtpList.add(f);
					FtpNameList.add(f.getName().toUpperCase());
				}
			}
			for(int i = 0; i < nameList.size(); i++){
				if(FtpNameList.contains(nameList.get(i).toUpperCase())){
					retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				}else{
					retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0016);
					return retMap;
			    }
		    }*/
			boolean allFileDataNotExists = false;//FTP是否存在当天的文件
			boolean fileDataExist = true;//单个文件是否存在
			String  retMsg = ""; 
			
			//校验FTP路径中文件是否与预估文件名称一致
			for(int i = 0; i < nameList.size(); i++){
				InputStream is = null;
				//OK文件不进行校验
				if(nameList.get(i).toUpperCase().contains(Const.FILE_OK)){
					continue;
				}
				String ftpFilePath = FTPurl+nameList.get(i);
				String smallWrite = nameList.get(i).split("\\.")[0]+"."+(nameList.get(i).split("\\.")[1].toLowerCase());
				//字符串以.切割需要转译,FTP.DEFAULT_CONTROL_ENCODING=ISO-8859-1,判断该文件是否存在于FTP (.TXT)
				is = client.retrieveFileStream(new String(ftpFilePath.getBytes("GBK"),FTP.DEFAULT_CONTROL_ENCODING));
				//nameList中都是.TXT,有些渠道发过来的是小写的.txt,需要再次校验 
				if(is == null || client.getReplyCode() == FTPReply.FILE_UNAVAILABLE){
					ftpFilePath = FTPurl+smallWrite;
					is = client.retrieveFileStream(new String(ftpFilePath.getBytes("GBK"),FTP.DEFAULT_CONTROL_ENCODING));
				}
				//如果文件输入流为空或者 FTPReply.FILE_UNAVAILABLE=550 未执行请求的操作,该文件不存在报错
				if (is == null || client.getReplyCode() == FTPReply.FILE_UNAVAILABLE){
					fileDataExist = false;
					retMsg = retMsg+","+nameList.get(i);
					LOGGER.info(nameList.get(i)+"文件在"+FTPurl+"路径下不存在");
				}
				//如果文件输入流不为空,该文件存在,继续校验
				if (is != null) {
					allFileDataNotExists = true;
					LOGGER.info(nameList.get(i)+"文件在"+FTPurl+"路径下存在");
	                is.close();
	                client.completePendingCommand();
	                retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
	            }
		    }
			//校验处理日期FTP上是否文件全都不在
			if(!allFileDataNotExists){
				LOGGER.info("遍历"+FTPurl+"目录,未搜寻到"+businessDate+"日期的文件");
				return ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0022);
			}
			//判断文件是否缺失
			if(!fileDataExist){
				return ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0016,retMsg+"在"+FTPurl+"路径下不存在");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999,e.getMessage());
		}
		retMap.put("nameList", nameList);
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 将FTPurl目录下的所有文件都下载到url本地目录，并校验url下的文件是否为空
	 * @param:hostname:ip地址 port:端口号 username:用户名 password：密码  url:路径
	 * @return: void
	 * @throws Exception 
	 */
	public Map<String,Object> retrieveAndCheckFile(String FTPurl,String url,String channelCode,String businessDate,String specialFile){
		FileOutputStream os = null;
		boolean flag =false;
		List<String> FTPUrlList  = new ArrayList<String>();
		Map<String,Object> retMap =Maps.newHashMap();
		if(StringUtils.isEmpty(specialFile)){
			retMap = checkAccountReqAndExchangeReq(businessDate,channelCode);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
		}
		FTPFile[] FTPFiles;
		try {
			//判断渠道下预计的文件个数以及文件名称是否与FTP路径下的相匹配
			retMap = checkChannelDetailFile(FTPurl,url,channelCode,businessDate);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
			List<String> nameList = (List<String>)(retMap.get("nameList"));
			//将目录下所有的文件进行备份（只备份前一次的）
			retMap = FileUtils.copyFile(url,businessDate);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
			
			for(int i=0;i<nameList.size();i++){
				if(nameList.get(i).contains(Const.FILE_BAK)){
					continue;
				}
				if(nameList.get(i).contains(businessDate) && nameList.get(i).toUpperCase().contains(".TXT")){
					String FTPFilePath = FTPurl+nameList.get(i);//FTP文件路径
					//渠道发来的文件中有.txt结尾的，下不到.TXT的，下.txt的
					String smallWrite = nameList.get(i).split("\\.")[0]+"."+(nameList.get(i).split("\\.")[1].toLowerCase());
					//下载到本地的文件路径
					File ftpFile = new File(url+nameList.get(i));
					os = new FileOutputStream(ftpFile);
					os.flush();
					//下载文件
					flag = client.retrieveFile(FTPFilePath,os);
					//下不到.TXT的，下.txt的
					if(!flag){
						FTPFilePath = FTPurl+smallWrite;
						flag = client.retrieveFile(FTPurl+smallWrite,os);
					}
					
					if(flag){
						LOGGER.info(FTPFilePath+"已下载到本地");
						FTPUrlList.add(FTPFilePath);
					}
					
				}
			}
/*			FTPFiles = client.listFiles(FTPurl);
			for(int i = 0; i < FTPFiles.length; i++){
				if(FTPFiles[i].getName().contains(businessDate) && FTPFiles[i].getName().toUpperCase().contains(".TXT")){
					File ftpFile = new File(url+FTPFiles[i].getName());
					os = new FileOutputStream(ftpFile);
					os.flush();
					flag = client.retrieveFile(FTPurl+FTPFiles[i].getName(),os);
					FTPUrlList.add(FTPFiles[i].getName());
				}
			}*/
			os.close();
			LOGGER.info("文件输出流已关闭");
			//将下载完毕的文件名称变为.bak后缀
			for(String FTPFilePath:FTPUrlList){
			    File ftpFile = new File(FTPFilePath);
		        File ftpFileBak = new File(FTPFilePath+Const.FILE_BAK);
		        client.rename(FTPFilePath, FTPFilePath+Const.FILE_BAK);
		        LOGGER.info(FTPFilePath+"文件已变为.bak后缀");
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			return retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_FT0013,e.getMessage());
		}	
		return retMap;
	}

	
	public Map<String,Object> checkUploadFTPFile(Map<String,Object> infoMap){
		PchannelInfo channelInfo = (PchannelInfo)infoMap.get("CHANNELINFO");
		String channelCode = channelInfo.getCiChannelCode();
		String tradeDate = MapUtils.getString(infoMap,"TRADEDATE","");
		String fileType = MapUtils.getString(infoMap,Const.FUND_FILETYPE,"");
		
		Map <String,Object> retMap = Maps.newHashMap();
		Map<String,Object> dictMap = chechDictBydictCode();
		if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
		//校验FTP连接
		retMap = checkFTPConnect(dictMap);
		
		String ftplocaldir =(String)dictMap.get(Const.FTP_LOCALDIR);
		String ftpremote =(String)dictMap.get(Const.FTP_REMOTE);
		String url = ftplocaldir+Const.FTP_DOWNLOAD+File.separator+channelCode+File.separator+tradeDate+File.separator;
		String FTPUrl = channelInfo.getCiSztRecvPath(); //深证通接收路径
		if(StringUtils.isEmpty(FTPUrl)){
			FTPUrl = ftpremote+Const.FTP_REMOTE_DOWNLOAD_DIR+"/"+channelCode+"/";
		}else{
			if(!FTPUrl.endsWith("/") && !FTPUrl.endsWith(File.separator)){
				FTPUrl+="/";
			}
		}
		LOGGER.info("FTP下载路径为"+FTPUrl);
		LOGGER.info("本地下载路径为"+url);
		
		//通过渠道勾选的文件名称逻辑获取需要上传的文件名称
		List<String> nameList = getUploadNameList(channelCode, tradeDate, fileType);
		LOGGER.info("通过渠道勾选的文件名称逻辑获取需要上传的文件名称为"+nameList.toString());
		retMap = checkFTPUploadFile(nameList,  FTPUrl);
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 下午5:02:21
	 * @Description: 基金行情信息确认表发送状态修改
	 * @param:Map<String, Object> map
	 * @return: int
	 * @throws Exception 
	 */
	public Map<String,Object> updateSendStatus(Map<String, Object> map){
		fundMarketCfmServier=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
		String channelCode = (String)map.get("CHANNELCODE");
		String businessDate = (String)map.get("TRANSDATE");
		FundMarketCfm fundMarketCfm =new FundMarketCfm();
		fundMarketCfm.setFmChannelCode(channelCode);
		fundMarketCfm.setFmBusinessDate(businessDate);
		fundMarketCfm.setFmSendStatus("01");
		fundMarketCfm.setFmSendFileTime(DateUtils.getOracleSysDate());
		int listTotal = fundMarketCfmServier.updateSendStatus(fundMarketCfm);
		Map<String,Object> retMap = Maps.newHashMap();
		retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		retMap.putAll(map);
		return retMap;
	}
	
	/**
	 * @author: wangchao 2019年4月8日 上午10:13:49
	 * @Description: 关闭FTP服务器的连接
	 * @param:
	 * @return: void
	 */
	public static void closeFtpClient(){
		try {
			if(client != null){
				client.disconnect();
				client =null;
				LOGGER.info("FTP连接已关闭");
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * @Description 上传文件至ftp指定目录
	 * @Author weijunjie
	 * @Date 2020/5/8 14:14
	 **/
	public void uploadFileToFtp(PchannelInfo channelInfo, InputStream is,String fileType,String fileName) throws Exception{
	    //FTP连接测试
        Map<String,Object> dictMap = chechDictBydictCode();
        checkFTPConnect(dictMap);
        String FTPUrl = getFtpUrl(dictMap,channelInfo, fileType,Const.FTP_REMOTE_DOWNLOAD_DIR);
        if(FTPUtils.client==null){
            FTPUtils.getInstance();
        }
		try {
			Boolean ftpPathIsExist =client.changeWorkingDirectory(FTPUrl);
			Boolean createDirFlag= changeWorkingDirectory(FTPUrl);
//			//执行文件上传操作
            FTPUrl+=fileName;
			LOGGER.info("上传路径为"+FTPUrl);
            boolean b = false;
			if(ftpPathIsExist && createDirFlag){
			    b = client.storeFile(FTPUrl, is);
            }
			LOGGER.info("上传结果为"+b);

		}catch (Exception e){
			e.printStackTrace();
		}finally {
            is.close();
        }

	}

	/**
	 * @Description 获取ftp位置路径
	 * @Author weijunjie
	 * @Date 2020/5/13 10:54
	 **/
	public String getFtpUrl(Map<String,Object> dictMap,PchannelInfo channelInfo, String fileType,String upType){
        String ftpremote =MapUtils.getString(dictMap, Const.FTP_REMOTE,"/");
        String channelCode = channelInfo.getCiChannelCode();
        String FTPUrl = channelInfo.getCiSztSendPath(); //深证通发送路径
        if(StringUtils.isEmpty(FTPUrl)){
            FTPUrl = ftpremote+upType+"/"+channelCode+"/";
            //上海农商行特殊处理
            if(Const.CHANNELCODESHNS.equals(channelCode)&&Const.FILE_TYPE_07.equals(fileType)){
                FTPUrl = ftpremote+upType+"/"+channelCode+"/"+Const.UPLOADSHNS07+"/";
            }else if(Const.CHANNELCODESHNS.equals(channelCode)&&!Const.FILE_TYPE_07.equals(fileType)){
                FTPUrl = ftpremote+upType+"/"+channelCode+"/"+Const.UPLOADSHNSOTHER+"/";
            }
        }else{
            if(!FTPUrl.endsWith("/") && !FTPUrl.endsWith(File.separator)){
                FTPUrl+="/";
            }
        }
        return FTPUrl;
    }

    /**
     * @Description 根据文件列表类型  获取文件列表name 申请文件索引文件
     * @Author weijunjie
     * @Date 2020/5/13 11:36
     **/
    public ArrayList<String> getAllFilesNameToUse(PchannelInfo channelInfo,String businessDate,String sendType){
//        String channelCode = channelInfo.getCiChannelCode();
        ArrayList<String> fileNames = new ArrayList<>();
        ArrayList<String> fileTypeList = new ArrayList<>();
        if("1".equals(sendType)){
            //行情
            String fileType = Const.FILE_TYPE_07;
            fileTypeList.add(fileType);
        }else if("2".equals(sendType)){
            //确认
            String fileTypes = Const.SEND_FILE_TYPE.replace("07,","");
            for(String s:fileTypes.split(",")){
                fileTypeList.add(s);
            }
        }else{
            //申请
            String fileTypes = Const.RECV_FILE_TYPE;
            for(String s:fileTypes.split(",")){
                fileTypeList.add(s);
            }
        }
        String saleAgent = channelInfo.getCiSaleAgentCode();
        String salesPerson = channelInfo.getCiSalesPerson();
        //数据文件
        for(String s:fileTypeList){
            Map<String,String> dataNameMap = uploadDataFileName(saleAgent,businessDate,s,salesPerson);
            if("3".equals(sendType)){
                dataNameMap = uploadDataFileName(salesPerson,businessDate,s,saleAgent);
            }
            for(String key:dataNameMap.keySet()){
                String fileName = MapUtils.getString(dataNameMap,key,"");
//                if(!fileName.contains("OK")){
                    fileNames.add(fileName);
//                }
            }
        }
        //索引文件文件名处理
        Map<String,String> IndexNameMap = uploadIndexFileName(saleAgent,businessDate,salesPerson);
        if("3".equals(sendType)){
            IndexNameMap = uploadIndexFileName(salesPerson,businessDate,saleAgent);
        }
        //索引文件 07 索引文件为 OFJ OFJOK
        if("1".equals(sendType)){
            for(String key:IndexNameMap.keySet()){
                if("OFJ".equals(key) || "OFJOK".equals(key)){
                    String fileName = MapUtils.getString(IndexNameMap,key,"");
//                    if(!fileName.contains("OK")){
                        fileNames.add(fileName);
//                    }
                }
            }
        }else{
            for(String key:IndexNameMap.keySet()){
                if("OFJ".equals(key) || "OFJOK".equals(key)){
                }else{
                    String fileName = MapUtils.getString(IndexNameMap,key,"");
//                    if(!fileName.contains("OK")){
                        fileNames.add(fileName);
//                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * @Description 判断路径下文件是否存在 文件存在则返回文件对应的全路径位置
     * @Author weijunjie
     * @Date 2020/5/13 11:01
     **/
    public String checkFileByUrl(PchannelInfo channelInfo, String fileType,String fileName,String checkDir){
        Map<String,Object> dictMap = chechDictBydictCode();
        checkFTPConnect(dictMap);
        String FTPUrl = getFtpUrl(dictMap,channelInfo, fileType,checkDir);
        FTPUrl+=fileName;
        try {
            boolean b = existFile(FTPUrl);
            if(b){
                return FTPUrl;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description 输入地址获取文件流数据 获取文件流数据
     * @Author weijunjie
     * @Date 2020/5/14 9:45
     **/
    public InputStream dowFileFormFtp(String FTPUrl) throws Exception{
        Map<String,Object> dictMap = chechDictBydictCode();
        checkFTPConnect(dictMap);
        InputStream inputStream = client.retrieveFileStream(FTPUrl);
        InputStream res = inputStream;
//        inputStream.close();
        getCloseFtpClient();
        return res;
    }

    /**
     * @Description 获取本地文件生成路径
     * @Author weijunjie
     * @Date 2020/5/15 8:36
     **/
    public List<Map<String,String>> getLocalFileUrl(String channelCode,String tradeDate,List<Map<String,String>> fileUrlMaps){
        List<Map<String,String>> resMap = new ArrayList<>();
        Map<String,Object> dictMap = chechDictBydictCode();
        String ftplocaldir =MapUtils.getString(dictMap, Const.FTP_LOCALDIR,"");
        String url = ftplocaldir+File.separator+Const.FTP_UPLOAD+File.separator+channelCode+File.separator+tradeDate+File.separator;
        for(Map<String,String> map:fileUrlMaps){
            String fileName = MapUtils.getString(map,"fileName");
            String localFileUrl = url+fileName;
            Map<String,String> m = new HashMap<>();
            m.put("fileName",fileName);
            m.put("fileUrl",localFileUrl);
            resMap.add(m);
        }
        LOGGER.info("获取下载的本地路径为："+JSONObject.toJSONString(resMap));
        getCloseFtpClient();
        return resMap;
    }
}

