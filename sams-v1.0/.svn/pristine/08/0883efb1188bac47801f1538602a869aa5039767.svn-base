package com.sams.batchfile.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseService;
import com.sams.custom.model.ChannelProduct;
import com.sams.custom.model.CheckFileUpload;
import com.sams.custom.model.result.ChannelProductInfo;

public interface CheckFileUploadService extends BaseService<CheckFileUpload>{

	/**
	 * 校验文件是否上传成功数据显示
	 * @Title: findCheckFileUploadCondition    
	 * @param page
	 *            当前第几页
	 * @param rows
	 *            每页显示的记录数
	 * @param
	 * @return Map
	 * */
	List<CheckFileUpload> findCheckFileUploadCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	/**
	 * 读取深圳通日志文件进行上传文件与否的判断
	 * @Title: readLog   
	 * @author: 王超 2020年4月26日 下午1:55:17
	 * @return: String      
	 */
	Map<String,Object> readLog(String tradeDate,String loginName);
	
	/**
	 * 获取处理日期最大的行号
	 * @Title: selectTransDateMaxLineNumber   
	 * @author: 王超 2020年4月28日 下午1:55:17
	 * @return: Long      
	 */
	String selectTransDateMaxLineNumber(String tradeDate);
	
	/**
	 * 批量保存上传成功的文件数据
	 * @Title: saveUploadSuccessData   
	 * @author: 王超 2020年4月28日 下午2:55:17
	 * @param: Map<String, Object> map  CHANNELCODE 渠道编号  TRADEDATE 交易日期  FILENAME 文件名称
	 * @return: Map<String, Object>      
	 */
	public int insertUploadSuccessData(List<Map<String, Object>> insertList);
}
