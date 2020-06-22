package com.sams.common.local;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.sams.batchfile.service.impl.FileDataServiceImpl;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.FileUtils;
import com.sams.custom.mapper.FundQuotTmpMapper;
import com.sams.sys.model.SysDict;

public class LocalUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(FileDataServiceImpl.class);
	public static boolean checkDiskSize(String FTP_LOCAL_UPLOAD_DIR){
		boolean flag  =false;
		String systemName = FileUtils.getSystemName();
		//windows环境下的磁盘空间大小判断
		if(systemName.toLowerCase().contains("window".toLowerCase())){
			float diskSpaceUsed = FileUtils.getDiskSpaceUsed(FTP_LOCAL_UPLOAD_DIR.substring(0,2));
			if(diskSpaceUsed>0.85){
				flag =false;
			}else{
				 flag=true;
			}
			//retMap.put("flag", flag);
		}else{
			return true;
		}
		return flag;
	}
}
