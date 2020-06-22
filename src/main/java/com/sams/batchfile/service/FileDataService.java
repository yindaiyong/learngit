package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.model.SysDict;
import com.sitco.yt.manage.models.P_channel_info;
import com.sitco.yt.manage.models.P_interface_col_info;

public interface FileDataService {


	public Map<String,Object> selectChannelInfoByChannelCode(String channelid);
	
	public StringBuilder buildFileTopInfo(String type,  String date ,PchannelInfo pChannelInfo);
	
	public String buildFileNameInfo(List<InterfaceColInfo> list);
	
	public String buildIndexfileInfo(String type, String date ,PchannelInfo pChannelInfo);
	
	public String getFileName(String type,String date , PchannelInfo pChannelInfo);
	
	public String getIndexFileName(String type,String date , PchannelInfo pChannelInfo);
	
	public String getOkFileName(String fileName);
	
    public Map<String,Object> getFileDataInfo(Map<String,Object> inputMap);
	
	public Map<String,Object> fileDataAssembly(Map<String,Object> map);
	
	public StringBuilder buildFileColFileds(Map<String,String> map,StringBuilder stringBuilder);

	public Map<String,Object> downLoadFile(Map<String, Object> map);
	
	public Map<String,Object> getDictInfo();
	
	public List<PchannelInfo> queryHandelChannelInfo(Map channelInfo);

	public List<Map<String, Object>> queryErrorDetail(String channelCode,
			String date);
	
	public String getUpdateDate(String tradeDate,int num,String channelCode);
	
	public Map<String,Object> checkFiledData(Map<String,Object> inputMap);

	public List<Map<String,Object>> getCnNameByfundCode(String fileType);

	public List<Map<String,Object>> getColumnsTitle(String fileType);
	
}
