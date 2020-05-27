package com.sams.batchfile.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileInterfaceFieldService;
import com.sams.batchfile.service.ResidentTaxInformService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ListUtils;
import com.sams.common.utils.RandomizingID;
import com.sams.common.utils.StringUtils;
import com.sams.custom.mapper.ChannelInfoMapper;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.mapper.ResidentTaxInformMapper;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;

@Service
public class ResidentTaxInformServiceImpl implements ResidentTaxInformService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ResidentTaxInformServiceImpl.class);
	
	@Autowired
	private ResidentTaxInformMapper residentTaxInformMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private ChannelInfoMapper channelInfoMapper;
	
	@Autowired
	private FileInterfaceFieldService fileInterfaceFieldService; 
	
	
	/**
	 * 清理非居民临时表条数
	 * @Title: deleteTresidentTaxInformTmpDate   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int deleteTresidentTaxInformTmpDate(String channelCode,String transDate) {
		return residentTaxInformMapper.deleteTresidentTaxInformTmpDate(channelCode, transDate);
	}
	
	/**
	 * 清理非居民申请表条数
	 * @Title: deleteTresidentTaxInformReqDate   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int deleteTresidentTaxInformReqDate(String channelCode,String transDate) {
		return residentTaxInformMapper.deleteTresidentTaxInformReqDate(channelCode, transDate);
	}
	
	/**
	 * 清理非居民确认表条数
	 * @Title: deleteTresidentTaxInformCfmDate   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int deleteTresidentTaxInformCfmDate(String channelCode,String transDate) {
		return residentTaxInformMapper.deleteTresidentTaxInformCfmDate(channelCode, transDate);
	}
	
	/**
	 * 查询非居民临时表条数
	 * @Title: selecTresidentTaxInformTmpCount   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int selecTresidentTaxInformTmpCount(String channelCode,String transDate) {
		return residentTaxInformMapper.selecTresidentTaxInformTmpCount(channelCode, transDate);
	}
	
	/**
	 * 查询非居民申请表条数
	 * @Title: selecTresidentTaxInformReqCount   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int selecTresidentTaxInformReqCount(String channelCode,String transDate) {
		return residentTaxInformMapper.selecTresidentTaxInformReqCount(channelCode, transDate);
	}
	
	/**
	 * 查询非居民确认表条数
	 * @Title: selecTresidentTaxInformCfmCount   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int selecTresidentTaxInformCfmCount(String channelCode,String transDate) {
		return residentTaxInformMapper.selecTresidentTaxInformCfmCount(channelCode, transDate);
	}

	/**
	 * 保存非居民临时表数据
	 * @Title: insertByBatchTresidentTaxInformTmp   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: tresidentTaxInformTmpList
	 * @return: int      
	 */
	@Override
	public int insertByBatchTresidentTaxInformTmp(List<Map<String, Object>> tresidentTaxInformTmpList) {
		// TODO Auto-generated method stub
		return residentTaxInformMapper.insertByBatchTresidentTaxInformTmp(tresidentTaxInformTmpList);
	}
	
	/**
	 * 保存非居民申请表数据
	 * @Title: insertByBatchTresidentTaxInformReq   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: tresidentTaxInformReqList
	 * @return: int      
	 */
	@Override
	public int insertByBatchTresidentTaxInformReq(List<Map<String, Object>> tresidentTaxInformReqList) {
		return residentTaxInformMapper.insertByBatchTresidentTaxInformReq(tresidentTaxInformReqList);
	}
	
	
	/**
	 * 保存非居民确认表数据
	 * @Title: insertByBatchTresidentTaxInformCfm   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: tresidentTaxInformCfmList
	 * @return: int      
	 */
	@Override
	public int insertByBatchTresidentTaxInformCfm(List<Map<String, Object>> tresidentTaxInformCfmList) {
		return residentTaxInformMapper.insertByBatchTresidentTaxInformCfm(tresidentTaxInformCfmList);
	}
	
	
	/**
	 * 非居民数据入库临时表
	 * @Title: saveDealTmpData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String,Object> saveDealTmpData(Map<String,Object> map){
		LOGGER.info("非居民涉税临时数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selecTresidentTaxInformTmpCount(channelCode,transDate);
		LOGGER.info("非居民涉税临时表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteTresidentTaxInformTmpDate(channelCode,transDate);
		LOGGER.info("非居民涉税临时表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchTresidentTaxInformTmp(list);
			LOGGER.info("保存非居民涉税数据入临时表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info("非居民涉税数据入临时表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("非居民涉税数据入临时表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00007);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00003);
			LOGGER.info("非居民涉税数据入申请表失败,原因为："+ExceptionConStants.retMsg_T00003);
		}
		LOGGER.info("非居民涉税临时数据入库结束处理");
		return retMap;
	}
	
	/**
	 * 非居民数据入库申请表
	 * @Title: saveDealCfMData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> saveDealReqData(Map<String, Object> map) {
		LOGGER.info("非居民涉税申请数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		
		int selectCount=selecTresidentTaxInformReqCount(channelCode,transDate);
		LOGGER.info("非居民涉税申请表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteTresidentTaxInformReqDate(channelCode,transDate);
		LOGGER.info("非居民涉税申请表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchTresidentTaxInformReq(list);
			LOGGER.info("保存非居民涉税数据入库申请表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info("非居民涉税数据入库申请表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("非居民涉税数据入库申请表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00040);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00041);
			LOGGER.info("非居民涉税数据入申请表失败,原因为："+ExceptionConStants.retMsg_T00041);
		}
		LOGGER.info("非居民涉税申请数据入库结束处理");
		return retMap;
	}
	
	
	/**
	 * 非居民数据入库正式表
	 * @Title: saveDealCfMData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> saveDealCfMData(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		LOGGER.info("非居民涉税确认数据开始处理");
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selecTresidentTaxInformCfmCount(channelCode, transDate);
		LOGGER.info("非居民涉税确认表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteTresidentTaxInformCfmDate(channelCode, transDate);
		LOGGER.info("非居民涉税确认表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchTresidentTaxInformCfm(list);
			LOGGER.info("保存非居民涉税数据入库确认表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info("非居民涉税数据入库确认表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("非居民涉税数据入库确认表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00030);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00031);
			LOGGER.info("非居民涉税数据入确认表失败,原因为："+ExceptionConStants.retMsg_T00031);
		}
		LOGGER.info("非居民涉税确认数据入库结束处理");
		return retMap;
	}

	/**
	 * 非居民数据基础规则校验
	 * @Title: checkResidentTaxInformApplyBasicData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> checkResidentTaxInformApplyBasicData(Map<String, Object> map) {
		boolean flag = true;
		Object date = map.get("TRANSDATE");
		Map<String, Object> retMap=Maps.newHashMap();
		List<Map<String, Object>> retList = Lists.newArrayList();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		try {
		
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
			fileInterfaceFieldService=(FileInterfaceFieldService) SpringUtils.getBean(FileInterfaceFieldService.class);
			LOGGER.info("非居民涉税基础校验开始处理");
	
			String transDate = MapUtils.getString(map,"TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String channelCode = channelInfo.getCiChannelCode();
			
			//查询非居民涉税临时表数据
			residentTaxInformMapper=(ResidentTaxInformMapper) SpringUtils.getBean(ResidentTaxInformMapper.class);
			List<Map<String, Object>> residentTaxInformTmpList = residentTaxInformMapper.selectResidentTaxInformTmp(channelCode, transDate);
			//retMap=ListUtils.checkListIsEmpty(residentTaxInformTmpList, ExceptionConStants.retCode_T00038);
			LOGGER.info("获取非居民涉税临时表数据成功,数据条数为："+residentTaxInformTmpList.size()+"条");
			
			for (Map<String, Object> residentTaxInformTmpMap : residentTaxInformTmpList) {
				//在接口信息表中获得字段定义  
				Map<String,InterfaceColInfo> colInfoMap = fileInterfaceFieldService.selectColInfoMap(Const.FILE_TYPE_R1, "000", channelInfo);
				LOGGER.debug("非居民涉税获取接口表信息为："+colInfoMap);
				//基础校验
				residentTaxInformTmpMap = exchangeProcessorService.baseVerify(residentTaxInformTmpMap,colInfoMap,null);
				
				//处理业务可处理状态 出错的将errordec复值
				if(!Strings.isNullOrEmpty(MapUtils.getString(residentTaxInformTmpMap, "ERRORDEC", ""))){
					residentTaxInformTmpMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					residentTaxInformTmpMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
					flag = false;
				}else{
					residentTaxInformTmpMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_01);
					residentTaxInformTmpMap.put("RETURNCODE", ExceptionConStants.retCode_0000);
				}
	
				residentTaxInformTmpMap=StringUtils.getValueMap(residentTaxInformTmpMap);
				LOGGER.debug("非居民涉税基础校验信息为："+residentTaxInformTmpMap);
				residentTaxInformTmpMap = FileDataUtil.changeBigDecialValue(residentTaxInformTmpMap);
				retList.add(residentTaxInformTmpMap);
			}
			
			if(retList.size()!=residentTaxInformTmpList.size()){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00039));
				LOGGER.info("非居民涉税信息基础校验数据与临时表记录数据行不一致,处理异常请检查");
				return retMap;
			}
			LOGGER.info("非居民涉税信息基础校验结束处理");
			if(residentTaxInformTmpList.size()!=0){
				retMap.put("RETVALUE", retList);
				retMap.put("TRANSDATE", transDate);
				retMap.put("CHANNELCODE", channelCode);
				retMap=saveDealReqData(retMap);
			}
			if(!flag){
				retMap.put("retCode",ExceptionConStants.retCode_A00015);
				retMap.put("retMsg", "非居民涉税"+ExceptionConStants.retMsg_A00015);
	//			return retMap;
			}else{
				retMap.put("TRANSDATE", date);
				retMap.put("CHANNELINFO", channelInfo);
				retMap.put("CHANNELCODE", channelCode);
			}
			map.putAll(retMap);
			return map;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("非居民基础校验错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkResidentTaxInform..方法,非居民基础校验异常,请联系管理员!");
			return retMap;
		}
	}

	/**
	 * 非居民数据数据转换入库确认表
	 * @Title: saveResidentTaxInformCfmData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> saveResidentTaxInformCfmData(Map<String, Object> map) {
		LOGGER.info("非居民涉税补足入确认表处理开始");
		Map<String,Object> retMap=Maps.newHashMap();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		try {
		
			String transDate=(String)map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			
			String channelCode=channelInfo.getCiChannelCode();
			residentTaxInformMapper=(ResidentTaxInformMapper) SpringUtils.getBean(ResidentTaxInformMapper.class);
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			
			//获得当前日期需要出确认数据的账户申请日期
			String marketCode = channelInfo.getCiMarketCode();
			String queryDate = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate),marketCode);
			
			//根据渠道号和日期查询申请表数据
			List<Map<String, Object>> residentTaxInformReqList = residentTaxInformMapper.selectResidentTaxInformReq(channelCode, queryDate);
			LOGGER.info("获取非居民涉税申请表数据成功,数据条数为："+residentTaxInformReqList.size()+"条");
			
			List<Map<String,Object>> retList=Lists.newArrayList();
			for(Map<String,Object> transReqMap : residentTaxInformReqList){
				transReqMap.put("TRANSACTIONCFMDATE", transDate);
				transReqMap.put("BUSINESSDATE", transDate);
				RandomizingID random = new RandomizingID("", "yyyyMMddHHmmss", 4, false);
		        transReqMap.put("TASERIALNO", random.genNewId());//TA确认交易流水号
		        String VALIDFLAG=(String)transReqMap.get("VALIDFLAG");
		        transReqMap.put("RETURNCODE", MapUtils.getString(transReqMap, "RETURNCODE",Const.BUSINESS_STUTAS_01.equals(VALIDFLAG)?ExceptionConStants.retCode_0000:ExceptionConStants.retCode_9999));
		        transReqMap.put("ERRORDETAIL", MapUtils.getString(transReqMap, "ERRORDETAIL", ExceptionConStants.retCode_0000.equals(transReqMap.get("RETURNCODE")) ? "成功" :"失败"));
		        retList.add(transReqMap);
			}
			LOGGER.info("非居民涉税补足入确认表处理结束");
			retMap.put("RETVALUE", retList);
			retMap.put("TRANSDATE",transDate);
			retMap.put("CHANNELCODE",channelCode);
			if(retList != null  && retList.size() > 0){
				retMap=saveDealCfMData(retMap);
			}
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				return retMap;
			}else{
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			map.putAll(retMap);
			return map;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("非居民确认错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "saveResidentTaxInformCfmData方法,非居民确认异常,请联系管理员!");
			return retMap;
		}
	}
}