package com.sams.batchfile.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sams.common.utils.*;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.AccountReqCfmService;
import com.sams.batchfile.service.AccountReqTmpService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.ExchangeReqTmpService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FileInterfaceFieldService;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.batchfile.service.ReadFileService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.AccountStatMapper;
import com.sams.custom.mapper.ChannelProductRelationMapper;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.mapper.ExchangeReqCfmMapper;
import com.sams.custom.mapper.ExchangeReqTmpMapper;
import com.sams.custom.mapper.FundMarketCfmMapper;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.mapper.ProductSalePrarmsMapper;
import com.sams.custom.model.AccountStat;
import com.sams.custom.model.ExchangeReq;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PProductInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProductSalePrarms;
import com.sams.sys.model.result.SysDictVo;
import com.sams.sys.service.SysDictService;
import com.sams.wsdl.impl.CallS100041;
import com.sams.wsdl.impl.CallS100434;
import com.sams.wsdl.impl.CallS100442;
import com.sams.wsdl.impl.CallS100454;
import com.sams.wsdl.impl.CallS100512;

@Service
public class ExchangeReqTmpServiceImpl implements ExchangeReqTmpService{

	private static Logger LOGGER = LoggerFactory.getLogger(ExchangeReqTmpServiceImpl.class);
	@Autowired
	private ExchangeReqTmpMapper exchangeReqTmpMapper;
	
	@Autowired
	private ExchangeReqCfmMapper exchangeReqCfmMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	 
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private AccountStatMapper accountStatMapper;
	
	@Autowired
	private FundMarketCfmMapper fundMarketCfmMapper;
	
	@Autowired
	private PchannelInfoMapper pchannelInfoMapper;
	
	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper;
	
	@Autowired
	private ChannelProductRelationMapper channelProductRelationMapper;
	
	@Autowired
	private AccountReqTmpService accountReqTmpService;
	
	@Autowired
	private FundMarketCfmServier fundMarketCfmServier;
	
	@Autowired
	private FileDataService fileDataService;
	
	@Autowired
	private ReadFileService readFileService;
	
	@Autowired
	private FileInterfaceFieldService fileInterfaceFieldService;
	
	@Autowired
	private AccountReqCfmService accountReqCfmService;
	
	@Autowired 
	private SysDictService sysDictService;
	
	
	@Autowired
	private ProductSalePrarmsMapper productSalePrarmsMapper;
	
	
	/**
	 * 清理交易临时表条数
	 * @Title: deleteTransTmpDate   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int deleteTransTmpDate(String channelCode,String businessDate) {
		return exchangeReqTmpMapper.deleteTransTmpDate(channelCode, businessDate);
	}
	
	/**
	 * 清理交易申请表条数
	 * @Title: deleteTransReqDate   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int deleteTransReqDate(String channelCode,String businessDate) {
		return exchangeReqTmpMapper.deleteTransReqDate(channelCode, businessDate);
	}
	
	/**
	 * 清理交易确认表条数
	 * @Title: deleteTransReqDate   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  
	 * @return: Map<String, Object>      
	 */
	@Override
	public int deleteTransCfmDate(String channelCode,String businessDate) {
		return exchangeReqTmpMapper.deleteTransCfmDate(channelCode, businessDate);
	}
	
	/**
	 * 查询交易申请临时表条数
	 * @Title: selectTransTmpCount   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param:  channelCode 渠道编号  transDate 交易日期  
	 * @return: int     
	 */
	@Override
	public int selectTransTmpCount(String channelCode,String businessDate) {
		return exchangeReqTmpMapper.selectTransTmpCount(channelCode, businessDate);
	}
	
	/**
	 * 查询交易申请的申请表条数
	 * @Title: selectTransReqCount   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param:   channelCode 渠道编号  transDate 交易日期  
	 * @return: int    
	 */
	@Override
	public int selectTransReqCount(String channelCode,String businessDate) {
		return exchangeReqTmpMapper.selectTransReqCount(channelCode, businessDate);
	}
	
	
	/**
	 * 查询交易申请的申请表条数
	 * @Title: selectTransReqCount   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param:   channelCode 渠道编号  transDate 交易日期  
	 * @return: int    
	 */
	@Override
	public int selectTransCfmCount(String channelCode,String businessDate) {
		return exchangeReqTmpMapper.selectTransCfmCount(channelCode, businessDate);
	}

	/**
	 * 批量出入交易申请的临时表
	 * @Title: insertByBatchTransTmp   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: attachmentTables
	 * @return:int   
	 */
	@Override
	public int insertByBatchTransTmp(List<Map<String, Object>> attachmentTables) {
		return exchangeReqTmpMapper.insertByBatchTransTmp(attachmentTables);
	}
	
	/**
	 * 批量出入交易申请的申请表
	 * @Title: insertByBatchTransReq   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: attachmentTables
	 * @return:int   
	 */
	@Override
	public int insertByBatchTransReq(List<Map<String, Object>> attachmentTables) {
		return exchangeReqTmpMapper.insertByBatchTransReq(attachmentTables);
	}
	
	/**
	 * 批量出入交易申请的确认表
	 * @Title: insertByBatchTransCfm   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: attachmentTables
	 * @return:int   
	 */
	@Override
	public int insertByBatchTransCfm(List<Map<String, Object>> attachmentTables) {
		//转换ORIGINALSERIALNO 和 FROMTAFLAG 字段
		LOGGER.info("转换"+Const.RE_SERIALNO_BUSICODE+"的ORIGINALSERIALNO和FROMTAFLAG的字段值");
		for (Map<String, Object> map : attachmentTables) {
			String businesscode = (String)map.get("BUSINESSCODE");
			if(Const.RE_SERIALNO_BUSICODE.contains(businesscode)){
				String taSeriNo = new RandomizingID("", "yyyyMMddHHmmss", 4, false).genNewId();
				map.put("ORIGINALSERIALNO", taSeriNo);
				map.put("FROMTAFLAG", "1");
			}
		}
		return exchangeReqTmpMapper.insertByBatchTransCfm(attachmentTables);
	}
	
	
	/**
	 * 交易申请数据入库临时表
	 * @Title: saveDealTmpData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String,Object> saveDealTmpData(Map<String,Object> map){
		LOGGER.info("交易申请临时数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectTransTmpCount(channelCode,transDate);
		LOGGER.info(channelCode+"交易申请临时表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteTransTmpDate(channelCode,transDate);
		LOGGER.info(channelCode+"交易申请临时表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchTransTmp(list);
			LOGGER.info(channelCode+"保存交易申请数据入临时表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info(channelCode+"交易申请数据入临时表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info(channelCode+"交易申请数据入临时表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00006);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00002);
			LOGGER.info(channelCode+"交易申请数据入申请表失败,原因为："+ExceptionConStants.retCode_T00002);
		}
		LOGGER.info(channelCode+"交易申请临时数据入库结束处理");
		return retMap;
	}
	
	/**
	 * 交易申请数据入库申请表
	 * @Title: saveDealReqData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> saveDealReqData(Map<String, Object> map) {
		LOGGER.info("交易申请数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectTransReqCount(channelCode,transDate);
		LOGGER.info(channelCode+"交易申请表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteTransReqDate(channelCode,transDate);
		LOGGER.info(channelCode+"交易申请表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchTransReq(list);
			LOGGER.info(channelCode+"保存交易申请数据入库申请表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info(channelCode+"交易申请数据入库申请表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info(channelCode+"交易申请数据入库申请表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00005);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00001);
			LOGGER.info(channelCode+"交易申请数据入申请表失败,原因为："+ExceptionConStants.retMsg_T00001);
		}
		return retMap;
	}
	
	
	/**
	 * 交易申请数据入库申请表
	 * @Title: saveDealCfmData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> saveDealCfmData(Map<String, Object> map) {
		LOGGER.info("交易确认数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectTransCfmCount(channelCode,transDate);
		LOGGER.info(channelCode+"交易确认表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteTransCfmDate(channelCode,transDate);
		LOGGER.info(channelCode+"交易确认表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchTransCfm(list);
			LOGGER.info(channelCode+"保存交易确认数据入库确认表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info(channelCode+"交易确认数据入库确认表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info(channelCode+"交易确认数据入库确认表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00006);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00001);
			LOGGER.info(channelCode+"交易确认数据入确认表失败,原因为："+ExceptionConStants.retMsg_T00001);
		}
		return retMap;
	}

	/**
	 * 交易申请数据基础规则校验
	 * @Title: checkTransactionApplyBasicData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> checkTransactionApplyBasicData(Map<String, Object> map){
		boolean flag = true; //判断是否有检验失败的
		Map<String, Object> retMap=Maps.newHashMap();
		List<Map<String, Object>> retList = Lists.newArrayList();
		try {
		
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			interfaceColInfoMapper=(InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
			fileInterfaceFieldService=(FileInterfaceFieldService) SpringUtils.getBean(FileInterfaceFieldService.class);
			LOGGER.info("交易申请税基础校验开始处理");
			String transDate = (String)map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String channelCode = channelInfo.getCiChannelCode();
			
			//查询交易申请临时表数据
			exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
			List<Map<String, Object>> transTmpList = exchangeReqTmpMapper.selectTransTmpInfo(channelCode, transDate);
			LOGGER.debug("根据渠道编号为："+channelCode+"交易日期为："+transDate+"，查询交易申请临时表信息为："+(transTmpList == null ? 0:transTmpList.size())+"条");
			
			fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
			String businessCodes = (String)fileDataService.getDictInfo().get("BUSINESSCODES");
			LOGGER.info(channelCode+"配置的业务编码为:"+businessCodes);
			
			for (Map<String, Object> transTmp : transTmpList) {
				//获得交易类型  
				String businessCode = StringUtils.getStringValue((String)transTmp.get("BUSINESSCODE"));
				//获得交易类型  
				LOGGER.info(channelCode+"此条申请的业务编码为:"+businessCode);
				if(StringUtils.isEmpty(businessCode.trim()) || !businessCodes.contains(businessCode)){
					LOGGER.info("交易申请中有业务编码为空或者不正确的编码的记录");
					//yindaiyong 20200110  不停下继续执行
					transTmp.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					transTmp.put("RETURNCODE", ExceptionConStants.retCode_9999);
					transTmp.put("ERRORDEC", ExceptionConStants.retMsg_A00019);
					Map<String, Object> tempMap = new HashMap<String, Object>();
					for (String key : transTmp.keySet()) {
						Object value = transTmp.get(key);
						tempMap.put(key, (value == null ? "" : value.toString().trim()));
					}
					retList.add(tempMap);
					flag = false;
					continue;
				}
				
				//在接口信息表中获得字段定义  
				Map<String,InterfaceColInfo> colInfoMap =fileInterfaceFieldService.selectColInfoMap(Const.FILE_TYPE_03, businessCode, channelInfo);
				LOGGER.info(channelCode+"交易申请获取接口表信息为："+colInfoMap);
				//基础校验
			   Map<String,Object> valInfoMap=Maps.newHashMap();
			   valInfoMap.put("TABLENAME", "D_EXCHANGE_REQ_TMP");
			   valInfoMap.put("TRANSDATE", transDate);
			   valInfoMap.put("CHANNELCODE", channelCode);
			   valInfoMap.put("APPSHEETSERIALNO", (String)transTmp.get("APPSHEETSERIALNO"));
			   transTmp = exchangeProcessorService.baseVerify(transTmp,colInfoMap,valInfoMap);
				//基础校验有异常
				if(!Strings.isNullOrEmpty((String)transTmp.get("ERRORDEC"))){
					transTmp.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					transTmp.put("RETURNCODE", ExceptionConStants.retCode_9999);
					flag = false;
				}else{
					transTmp.put("VALIDFLAG",Const.BUSINESS_STUTAS_01);
					transTmp.put("RETURNCODE", ExceptionConStants.retCode_0000);
				}
				LOGGER.info(channelCode+"交易申请基础校验信息为："+transTmp);
				
				String taSeriNo = new RandomizingID("", "yyyyMMddHHmmss", 4, false).genNewId();
				LOGGER.info("交易随机生成的TA确认流水号为："+taSeriNo);
				transTmp.put("ORIGINALSERIALNO", taSeriNo);
				   
				retList.add(transTmp);
			}
			if(retList != null && transTmpList != null &&(retList.size() != transTmpList.size())){
				LOGGER.info("交易申请信息基础校验数据与临时表记录数据行不一致,处理异常请检查");
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00045));
				return retMap;
			}
			retMap.put("CHANNELCODE", channelCode);
			retMap.put("TRANSDATE", transDate);
			retMap.put("CHANNELINFO", channelInfo);
			retMap.put("RETVALUE", retList);
			if(retList != null && retList.size() > 0 ){
				LOGGER.info(channelCode+"需要入库交易正式表数据为："+retList.size()+"条，继续向下执行");
				retMap=saveDealReqData(retMap);
			}else{
				deleteTransReqDate(channelCode,transDate);
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				return retMap;
			}else{
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			map.putAll(retMap);
			if(!flag){
				map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00015));
				map.put("retMsg", "交易申请"+ExceptionConStants.retMsg_A00015);
				return map;
			}
			
			int notExchangeReqData=exchangeReqTmpMapper.batchUpdateNotExchangeReqData(channelCode, transDate);
			LOGGER.info("根据渠道编号："+channelCode+",将交易日期为："+transDate+"不是本渠道的产品更新为不处理状态，更新的条数为"+notExchangeReqData);
			if(notExchangeReqData>0){
				LOGGER.info(transDate+"交易的渠道编号为："+channelCode+"检查出有未配置的产品："+notExchangeReqData+"笔");
				map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00069));
				return map;
			}
			
			
			int appSheetSerialNoRepeat=exchangeReqTmpMapper.batchUpdateAppSheetSerialNoRepeat(channelCode, transDate);
			LOGGER.info("根据渠道编号："+channelCode+",将交易日期为："+transDate+"是本渠道的交易申请单编号重复的更新为不处理状态，更新的条数为"+appSheetSerialNoRepeat);
			if(appSheetSerialNoRepeat>0){
				LOGGER.info(transDate+"交易的渠道编号为："+channelCode+"检查出交易申请单编号重复的产品："+appSheetSerialNoRepeat+"笔");
				map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00091));
				return map;
			}
			LOGGER.info(channelCode+"渠道,"+transDate+"交易基础校验结束！");
			return map;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("交易基础校验错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkTransactionApplyBasicData方法,交易基础校验异常,请联系管理员!");
			return retMap;
		}
	}
	
	/**
	 * 交易申请校验行情状态
	 * @Title: checkMarketSata   
	 * @author: wanshunbin 2019年7月2日 上午15:02:22
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 businessCode 交易编码  fundCode 产品类型
	 * @return: Map<String, Object>      
	 */
	public  Map<String, Object> checkMarketSata(Map<String, Object> map){

		  LOGGER.info("交易申请行情校验开始处理");
		  String channelCode = MapUtils.getString(map,"CHANNELCODE");
		  String transDate = MapUtils.getString(map,"TRANSDATE");
		  String businessCode = MapUtils.getString(map,"BUSINESSCODE");
		  String fundCode = MapUtils.getString(map,"FUNDCODE");
		  
		  Map<String,Object> fundStatusMap=Maps.newHashMap();
		  fundStatusMap.put("CHANNELCODE", channelCode);
		  fundStatusMap.put("FUNDCODE", fundCode);
		  fundStatusMap.put("TRANSDATE", transDate);
		  
		  fundMarketCfmServier=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
		  String fundStatus=fundMarketCfmServier.fundCfmJudgeTrade(fundStatusMap);
		  LOGGER.info("根据渠道："+channelCode+"产品:"+fundCode+"交易日期:"+transDate+"获取产品当日交易状态为："+fundStatus);
		  if(fundStatus==null){
			  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_E00002));
			  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			  map.put("ERRORDEC", ExceptionConStants.retMsg_E00002);
			  map.put("RETURNCODE", ExceptionConStants.retCode_9999);
			  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_E00002); 
			  return map;
		  }
		  
		  switch (businessCode) {
		  case Const.BUSINESS_020:
			  //产品基金状态为发行，可认购 
			  if(Const.PUBLISH.equals(fundStatus)){
				  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0371));
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				  map.put("ERRORDEC", ExceptionConStants.retMsg_0371);
				  map.put("RETURNCODE", ExceptionConStants.retCode_0000);
				  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0371);
			  }else{ //产品基金状态不为发行，不可认购 
				  
				  //查询产品类型 
				  Map<String, Object> queryMap = new HashMap<String, Object>();
				  queryMap.put("CHANNELCODE", channelCode);
				  queryMap.put("PRODUCTCODE", fundCode);
				  queryMap.put("BATCHNO", "1");
				  productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
				  PProductInfo productInfo =  productInfoMapper.selectByProductCodeBatchNo(queryMap);
				  String fundType = productInfo.getPiProductType(); //产品类型
				  if(Const.FUND_TYPE_08.equals(fundType) 
						  && (Const.CANNOT_REDEMPTION.equals(fundStatus) || Const.CAN_PURCHASE_REDEMPTION.equals(fundStatus))){ //多币种运作期申购行情发的认购交易
					  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0371));
					  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
					  map.put("ERRORDEC", ExceptionConStants.retMsg_0371);
					  map.put("RETURNCODE", ExceptionConStants.retCode_0000);
					  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0371); 
				  }else{
					  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0361));
					  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					  map.put("ERRORDEC", ExceptionConStants.retMsg_0361);
					  map.put("RETURNCODE", ExceptionConStants.retCode_9999);
					  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0361);
				  }
			  }
		   break;
		  case Const.BUSINESS_022:
			  //产品基金状态为可申购赎回或停止赎回，可申购
			  if(Const.CAN_PURCHASE_REDEMPTION.equals(fundStatus)|| Const.CANNOT_REDEMPTION.equals(fundStatus)){
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				  if(fundStatus.equals( Const.CAN_PURCHASE_REDEMPTION)){
					  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0370));
					  map.put("ERRORDEC", ExceptionConStants.retMsg_0370);
					  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0370);
				  }else{
					  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0382));
					  map.put("ERRORDEC", ExceptionConStants.retMsg_0382);
					  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0382);
				  }
				  map.put("RETURNCODE", ExceptionConStants.retCode_0000);
			  }else{//产品基金状态不为可申购赎回或停止赎回，不可申购
				  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0362));
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				  map.put("ERRORDEC", ExceptionConStants.retMsg_0362);
				  map.put("RETURNCODE", ExceptionConStants.retCode_9999);
				  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0362); 
			  }
		   break;
		  case Const.BUSINESS_024:
			  //产品基金状态为可申购赎回或停止申购，可赎回
			  if(Const.CAN_PURCHASE_REDEMPTION.equals(fundStatus)|| Const.CANNOT_PURCHASE.equals(fundStatus)){
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				  if(fundStatus.equals( Const.CAN_PURCHASE_REDEMPTION)){
					  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0370));
					  map.put("ERRORDEC", ExceptionConStants.retMsg_0370);
					  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0370);
				  }else{
					  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0381));
					  map.put("ERRORDEC", ExceptionConStants.retMsg_0381);
					  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0381);
				  }
				  map.put("RETURNCODE", ExceptionConStants.retCode_0000);
			  }else{//产品基金状态不为可申购赎回或停止申购，不可赎回
				  map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0363));
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				  map.put("ERRORDEC", ExceptionConStants.retMsg_0363);
				  map.put("RETURNCODE", ExceptionConStants.retCode_9999);
				  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+ExceptionConStants.retMsg_0363); 
			  }
		   break;
		   //yindy 20200224  撤单交易
		  case Const.BUSINESS_052:
			  if(Const.CANNOT_PURCHASE_REDEMPTION.equals(fundStatus) ||
					  Const.FUND_TERMINATION.equals(fundStatus) || 
					  Const.FUND_SEALING.equals(fundStatus)){
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				  map.put("ERRORDEC", "不可以发送撤单交易");
				  map.put("RETURNCODE", ExceptionConStants.retCode_9999);
			  }else{
				  map.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				  map.put("ERRORDEC", "可以发送撤单交易");
				  map.put("RETURNCODE", ExceptionConStants.retCode_0000);
			  }
			  LOGGER.info(channelCode+fundCode+"产品当日交易状态为："+fundStatus);
			  break;
		  }
		  
		  LOGGER.info(channelCode+"交易申请行情校验结束处理");
		  return map;
		 
	}
	
	
	
	/**
	 * 交易申请校验账户状态
	 * @Title: checkAccountstat   
	 * @author: wanshunbin 2019年7月2日 上午15:02:22
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 businessCode 交易编码  fundCode 产品类型
	 * @return: Map<String, Object>      
	 */
	public Map<String, Object> checkAccountstat(Map<String, Object> transactionTemp){
		String channelCode = MapUtils.getString(transactionTemp,"CHANNELCODE");
		LOGGER.info(channelCode+"交易申请账户校验开始处理");
		//交易账户状态校验
		Map<String,Object> inputMap=Maps.newHashMap();
		inputMap.put("CHANNELCODE", channelCode);
		inputMap.put("TRANSACTIONACCOUNTID", MapUtils.getString(transactionTemp,"TRANSACTIONACCOUNTID"));
		
		LOGGER.info("交易校验账户的查询条件为：渠道"+MapUtils.getString(inputMap, "CHANNELCODE", "")
				+"客户类型"+MapUtils.getString(inputMap, "INDIVIDUALORINSTITUTION", "")
				+"投资人基金账号(TA返回)"+MapUtils.getString(inputMap, "TAACCOUNTID", "")
				+"投资人基金交易账号"+MapUtils.getString(inputMap, "TRANSACTIONACCOUNTID", ""));
		accountStatMapper=(AccountStatMapper) SpringUtils.getBean(AccountStatMapper.class);
		AccountStat accountStat=accountStatMapper.selectAccountStatus(inputMap);
		LOGGER.info(channelCode+"渠道查询的本地账户信息为:"+accountStat);
		if(accountStat==null){
			transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00047));
			transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_0316);
			transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_T00047);
			transactionTemp.put("TASERIALNO", "");
			LOGGER.info(channelCode+"无此账户不允许交易");
			transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00099));
			return transactionTemp;
		}
		
		if(Const.BUSINESS_STUTAS_01.equals(accountStat.getAsOpenAccountStat())&&Const.BUSINESS_STUTAS_01.equals(accountStat.getAsOpenChannelStat())){
			transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
			transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			transactionTemp.put("ACCOUNTSTAT",accountStat);
		}else{
			transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00010));
			LOGGER.info(channelCode+"获取账户可交易状态，理财账户或者交易渠道未开通，处理失败");
		}
		
		LOGGER.info(channelCode+"交易申请账户校验结束处理");
		return transactionTemp;
	}
	
	
	/**
	 * 交易申请数据转换
	 * @Title: checkTransactionApplyBusinessData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> checkTransactionApplyBusinessData(Map<String, Object> map) throws Exception {
		//老系统产品类型 1.丰利B类  2.丰利D类   3.红宝石   4.固收类  5.T+N 6.QDII 7.净值类 8.多币种 9.FOF
		boolean flag = true; 
		LOGGER.info("交易申请数据转换开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		try {
		
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
			
			String channelCode = (String)map.get("CHANNELCODE");
			String transDate = (String)map.get("TRANSDATE");
			String retMsg="";
			//052 撤单 撤单编码需放在前面处理 
			List<String> businessCodeList =Arrays.asList(Const.BUSINESS_052,Const.BUSINESS_024,Const.BUSINESS_020,Const.BUSINESS_022);  //交易业务编码
			for(String businessCode:businessCodeList){
				
				//对多币种同一批次的同一个合同不允许有不同币种赎回   20200211 
				if(Const.BUSINESS_024.equals(businessCode)){
					LOGGER.info("{}渠道,赎回交易,处理多币种类把同一个客户 同一个产品 {}的不同币种的赎回,该赎回周期只允许一个币种成功!",channelCode,transDate);
					boolean checkFlag = upToFailedByDifCurrencyType(channelCode,transDate,businessCode);
					if(!checkFlag){
						LOGGER.info("{}渠道,{}日期,多币种赎回交易有币种错误问题。");
						flag = checkFlag;
						retMsg = "多币种美月盈赎回币种错误!";
					}
				}
				
				//查询交易申请表数据
				List<Map<String, Object>> transReqList = exchangeReqTmpMapper.selectTransReqInfo(channelCode, transDate,businessCode);
				LOGGER.info("根据渠道编号："+channelCode+",将交易日期为："+transDate+",需要处理的交易申请数据"+(transReqList==null?0:transReqList.size())+"条");
				for (Map<String, Object> transactionTemp : transReqList) {
					String fundCode=(String)transactionTemp.get("FUNDCODE");
					transactionTemp.put("CHANNELCODE", channelCode);
					transactionTemp.put("TRANSDATE", transDate);
					
					Map<String,Object> marketSataMap=checkMarketSata(transactionTemp);
					String validFlag=(String)marketSataMap.get("VALIDFLAG");
					if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
						marketSataMap.put("FLAG", "01");
						retList.add(marketSataMap);
						LOGGER.info(channelCode+"行情状态异常，不可交易");
						retMsg=(String)marketSataMap.get("ERRORDEC");
						flag = false;
						continue;
					}
					
					Map<String,Object> accountstatMap=checkAccountstat(transactionTemp);
					validFlag=(String)accountstatMap.get("VALIDFLAG");
					if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
						retList.add(accountstatMap);
						LOGGER.info(channelCode+"账户状态异常，不可交易");
						retMsg=(String)accountstatMap.get("retMsg");
						flag = false;
						continue;
					}
					transactionTemp.putAll(accountstatMap); 
					
					Map<String,Object> inputMap=Maps.newHashMap();
					inputMap.put("FUNDCODE", fundCode);
					inputMap.put("CHANNELCODE", channelCode);
					ChannelProductRelationMapper channelProductRelationMapper=(ChannelProductRelationMapper) SpringUtils.getBean(ChannelProductRelationMapper.class);
					inputMap.put("APPLICATIONAMOUNT", MapUtils.getString(transactionTemp, "APPLICATIONAMOUNT", MapUtils.getString(transactionTemp, "APPLICATIONVOL", "")));
					Map<String,Object>  sectionNumberAndFundRate=channelProductRelationMapper.selectSectionNumberAndFundRate(inputMap);
					
					LOGGER.info("根据渠道编号:"+channelCode+",产品带代码"
					+fundCode+"购买/赎回金额"+MapUtils.getString(transactionTemp, "APPLICATIONAMOUNT", MapUtils.getString(transactionTemp, "APPLICATIONVOL", ""))+"查询的收益级别和收益率为："
					+(sectionNumberAndFundRate==null ? "":sectionNumberAndFundRate.toString()));
					
					String fundPrix = fundCode.substring(0, 4);
					//20200302 yindy  购买才需要判断收益率
					if(!Const.NO_CHECK_YIELD_FUND.contains(fundPrix) && ( 
							Const.BUSINESS_022.equals(businessCode) || 
							Const.BUSINESS_020.equals(businessCode))) {
						if(sectionNumberAndFundRate!=null){
							//受益级别
							transactionTemp.put("SECTIONNUMBER",  MapUtils.getString(sectionNumberAndFundRate, "SECTIONNUMBER", ""));
							transactionTemp.put("FUNDRATE",  MapUtils.getString(sectionNumberAndFundRate, "FUNDRATE", ""));
						}else{
							transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00090));
							transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_9999);
							transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_T00090);
							transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
							retMsg=ExceptionConStants.retMsg_T00090;
							LOGGER.info(channelCode+"收益级别异常，不可交易");
							flag = false;
							retList.add(transactionTemp);
							continue;
						}
					}
					
					productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
					Map<String,Object> proYieldMap=productInfoMapper.selectProYield(channelCode,fundCode);
					if(proYieldMap==null||proYieldMap.isEmpty()){
						LOGGER.info(channelCode+"根据渠道编号、产品编号，获得产品设置无记录");
						transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00019));
						transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_9999);
						transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_T00019);
						transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						retMsg=ExceptionConStants.retMsg_T00019;
						flag = false;
						retList.add(transactionTemp);
						continue;
					}
					LOGGER.info(channelCode+"根据渠道编号、产品编号，获得产品设置成功");
					LOGGER.info("{}渠道,把产品信息放入交易记录,重点关注TA代码{},批次号{}",channelCode,MapUtils.getObject(proYieldMap, "TAPRODUCTCODE"),MapUtils.getObject(proYieldMap, "BATCHNO"));
					transactionTemp.putAll(proYieldMap);
					
					Map<String,Object> maxPaperVolMap=checkMaxPaperVol(transactionTemp,fundCode,channelCode,transDate,businessCode);
					validFlag=(String)maxPaperVolMap.get("VALIDFLAG");
					if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
						retList.add(maxPaperVolMap);
						retMsg=(String)maxPaperVolMap.get("ERRORDEC");
						LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
						flag = false;
						continue;
					}
					
					String originalSerialno = MapUtils.getString(transactionTemp, "ORIGINALSERIALNO", new RandomizingID("", "yyyyMMddHH", 8, false).genNewId());
					
					String productType =(String)proYieldMap.get("PRODUCTTYPE");
					sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
					String dealType = sysDictService.findDictVo(productType+Const.DEALTYPE);//交易类型
					transactionTemp.put("BATCHNO", (String)proYieldMap.get("BATCHNO"));
					switch (businessCode) {
					case Const.BUSINESS_020:
						if(dealType.indexOf(Const.BUSINESS_020)!=-1){
							transactionTemp = checkTrans020(transactionTemp);
							validFlag=(String)transactionTemp.get("VALIDFLAG");
							if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
								retMsg=(String)transactionTemp.get("ERRORDEC");
								flag = false;
							}
							LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
							transactionTemp.put("TASERIALNO",  originalSerialno);
							transactionTemp.put("TRANSDATE", transDate);
							retList.add(transactionTemp);
						}else{
							flag = false;
							transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
							transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0007));
							transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_9999);
							transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_List0007);
							retMsg= ExceptionConStants.retMsg_List0007;
							LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
							transactionTemp.put("TASERIALNO", "");
							retList.add(transactionTemp);
						}
						break;
					case Const.BUSINESS_022:
						if(dealType.indexOf(Const.BUSINESS_022)!=-1){
							transactionTemp = checkTrans022(transactionTemp);
							validFlag=(String)transactionTemp.get("VALIDFLAG");
							if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
								retMsg=(String)transactionTemp.get("ERRORDEC");
								LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
								flag = false;
							}
							transactionTemp.put("TASERIALNO",  originalSerialno);
							transactionTemp.put("TRANSDATE", transDate);
							retList.add(transactionTemp);
						}else{
							flag = false;
							transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
							transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0008));
							transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_9999);
							transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_List0008);
							retMsg= ExceptionConStants.retMsg_List0008;
							LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
							transactionTemp.put("TASERIALNO", "");
							validFlag=(String)transactionTemp.get("VALIDFLAG");
							if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
								flag = false;
							}
							
							retList.add(transactionTemp);
						}
						break;
					case Const.BUSINESS_024:
						if(dealType.indexOf(Const.BUSINESS_024)!=-1){
							transactionTemp = checkTrans024(transactionTemp);
							validFlag=(String)transactionTemp.get("VALIDFLAG");
							if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
								retMsg=(String)transactionTemp.get("ERRORDEC");
								LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
								flag = false;
							}
							transactionTemp.put("TASERIALNO",  originalSerialno);
							transactionTemp.put("TRANSDATE", transDate);
							AccountStat accountStat=(AccountStat)transactionTemp.get("ACCOUNTSTAT");
							transactionTemp.put("TAACCOUNTID", accountStat.getAsTaAccountId());
							retList.add(transactionTemp);
						}else{
							flag = false;
							transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
							transactionTemp.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0009));
							transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_9999);
							transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_List0009);
							retMsg= ExceptionConStants.retMsg_List0009;
							LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
							transactionTemp.put("TASERIALNO", "");
							retList.add(transactionTemp);
						}
						break;
						// 撤单交易    20200224 yindy
					case Const.BUSINESS_052:
						//  查询该撤单交易撤销的原申请单号号是否已经确认
						transactionTemp = checkTrans052(transactionTemp);
						validFlag=(String)transactionTemp.get("VALIDFLAG");
						if(Const.BUSINESS_STUTAS_00.equals(validFlag)){
							retMsg=(String)transactionTemp.get("ERRORDEC");
							LOGGER.info("渠道："+channelCode+"产品："+fundCode+retMsg);
							flag = false;
						}
						transactionTemp.put("TASERIALNO",  originalSerialno);
						transactionTemp.put("TRANSDATE", transDate);
						AccountStat accountStat=(AccountStat)transactionTemp.get("ACCOUNTSTAT");
						transactionTemp.put("TAACCOUNTID", accountStat.getAsTaAccountId());
						retList.add(transactionTemp);
						break;
					}
				}
			}
			
			if(retList != null && retList.size() > 0){
				exchangeReqTmpMapper.batchUpdateExchangeReqData(retList);
			}
			Map<String,Object> accountMinVolMap = new HashMap<String,Object>();
			LOGGER.info("赎回开始校验最低账面份额：",channelCode,transDate);
		    accountMinVolMap = checkAccountMinVol(map);
			boolean minVolFlag = (boolean)accountMinVolMap.get("024MINVOLFLAG");
		    if(!minVolFlag){
				LOGGER.info(channelCode+"赎回校验最低账面份额异常，不可交易");
				retMsg=(String)accountMinVolMap.get("ERRORDEC");
				flag = false;
			}
			retMap.putAll(map);
			
			if(!flag){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00020));
				retMap.put("retMsg", "交易申请发生异常为:"+retMsg+",请检查");
			}else{
				retMap.putAll(map);
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			LOGGER.info(channelCode+"渠道"+transDate+"交易业务校验结束！");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("交易业务校验错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkTransactionApplyBusinessData方法,交易业务校验异常,请联系管理员!");
			return retMap;
		}
	}
	
	/**
	 * 052处理撤单交易
	 * @Title: checkTrans052   
	 * @author: yindy 2020年2月24日 下午1:10:36
	 * @param: @param transactionTemp
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkTrans052(
			Map<String, Object> transactionTemp) {
		//查询是否有申请过来的原申请单号为确认的交易 ？
		Map<String, Object> map =  exchangeReqTmpMapper.query052OriginalTrans(transactionTemp);
		if(map == null || map.isEmpty()){
			transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			transactionTemp.put("ERRORDEC", ExceptionConStants.retMsg_T00114);
			transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_9999);
		}else{
			transactionTemp.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
			transactionTemp.put("ERRORDEC", "可以撤单！");
			transactionTemp.put("RETURNCODE", ExceptionConStants.retCode_0000);
		}
		return transactionTemp;
	}

	/**
	 *    
	 * @Title: upToFailedByDifCurrencyType   
	 * @author: yindy 2020年2月11日 下午5:26:57
	 * @param: @param channelCode
	 * @param: @param transDate
	 * @param: @param businessCode      
	 * @return: void      
	 * @throws
	 */
	private boolean upToFailedByDifCurrencyType(String channelCode,
			String transDate, String businessCode) {
		//取出多币种当天赎回校验成功并且同一客户 同一产品 多比的数据
		boolean flag = true;
		Map<String, Object> queryMap = new LinkedHashMap<String, Object>();
		queryMap.put("CHANNELCODE", channelCode);
		queryMap.put("TRANSDATE", transDate);
		queryMap.put("BUSINESSCODE", businessCode);
		exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
		//1、找出今天的多币种赎回交易的产品
		List<String> fundList = exchangeReqTmpMapper.queryMultipleRedeemFund(queryMap);
		LOGGER.info("{}渠道的{}的多币种赎回产品为{}",channelCode,transDate,(fundList == null ? "" : fundList.toString()));
		if(fundList == null || fundList.size() == 0 ){
			LOGGER.info("{}渠道，{}没有多币种赎回产品",channelCode,transDate);
			return true;
		}
		//2.循环产品判断当前日期是否是该产品赎回周期的首日
		for (String fundCode : fundList) {
			//判断该产品该日期是否是赎回日期的首日
			Map<String, Object> isFirstMap = checkIsFirstOpenDay(channelCode,fundCode,transDate);
			LOGGER.info("{}渠道,{}产品,判断是否是赎回周期的首日的结果{}",channelCode,fundCode,(isFirstMap == null ? "" : isFirstMap.toString()));
			queryMap.put("FUNDCODE", fundCode);
			if(MapUtils.getBooleanValue(isFirstMap, "ISFIRST")){
				//找出该产品   一个人赎回多笔的
				List<Map<String, Object>> multipleList = exchangeReqTmpMapper.queryOneAccoMoreTrans(queryMap);
				if(multipleList == null || multipleList.size() == 0){
					LOGGER.info("{}渠道,{}产品,{}日期,没有一个客户有多比赎回的交易",channelCode,fundCode,transDate);
					continue;
				}
				//找出同一客户 第一笔赎回币种不同的交易
				List<Map<String, Object>> oneAccoDiffFirstCurrencyList  = getOneAccoDiffFirstCurrency(multipleList);
				//修改这些交易为失败
				if(oneAccoDiffFirstCurrencyList != null && oneAccoDiffFirstCurrencyList.size() > 0 ){
					flag = false;
					exchangeReqTmpMapper.batchUpdateMultipToFailed(oneAccoDiffFirstCurrencyList);
					LOGGER.info("{}渠道,{}产品,{}日期,多币种赎回做失败处理,需要更新的数量为{}",channelCode,fundCode,transDate,oneAccoDiffFirstCurrencyList.size());
					continue;
				}
				LOGGER.info("{}渠道,{}产品,{}日期,同一客户没有不同币种的赎回交易",channelCode,fundCode,transDate);
			}else{
				//获得该产品赎回周期的首日
				queryMap.put("FIRSTDAY", MapUtils.getString(isFirstMap, "FIRSTDAY"));
				//查询出该产品今天交易在该周期内是否已经做过交易
				List<Map<String, Object>> allTransList = exchangeReqTmpMapper.queryAllTransList(queryMap);
				Set<String> accSet = new HashSet<String>(); //记录第一次赎回的基金账号
				List<Map<String, Object>> diffCompFirst = new ArrayList<Map<String,Object>>();//记录这次币种与上次不一致的记录
				for (Map<String, Object> map : allTransList) {
					String acco = MapUtils.getString(map, "TRANSACTIONACCOUNTID");
					String currencyType = MapUtils.getString(map, "CURRENCYTYPE",Const.CURRENCY_USD_CODE);
					String firstAcco = MapUtils.getString(map, "FIRSTTRANSACTIONACCOUNTID");
					String firstCurrencyType = MapUtils.getString(map, "FIRSTCURRENCYTYPE",Const.CURRENCY_USD_CODE);
					if(StringUtils.isEmpty(firstAcco)){ //左关联开始的账户是空，表示第一次赎回
						accSet.add(acco);
					}else{
						//记录这次币种与上次不一致的记录
						if(!currencyType.equals(firstCurrencyType)){
							diffCompFirst.add(map);
						}
					}
				}
				
				if(accSet!= null && accSet.size() > 0 ){
					queryMap.put("TRANSACCOLIST", accSet);
					List<Map<String, Object>> multipleList = exchangeReqTmpMapper.queryOneAccoMoreTrans(queryMap);
					if(multipleList == null || multipleList.size() == 0){
						LOGGER.info("{}渠道,{}产品,{}日期,没有一个客户有多比赎回的交易",channelCode,fundCode,transDate);
					}else{
						List<Map<String, Object>> oneAccoDiffFirstCurrencyList  = getOneAccoDiffFirstCurrency(multipleList);
						//修改这些交易为失败
						if(oneAccoDiffFirstCurrencyList != null && oneAccoDiffFirstCurrencyList.size() > 0 ){
							flag = false;
							exchangeReqTmpMapper.batchUpdateMultipToFailed(oneAccoDiffFirstCurrencyList);
							LOGGER.info("{}渠道,{}产品,{}日期,多币种赎回做失败处理,需要更新的数量为{}",channelCode,fundCode,transDate,oneAccoDiffFirstCurrencyList.size());
						}
					}
				}
				
				//更新与上次不一致的为错误
				if(diffCompFirst != null && diffCompFirst.size() > 0 ){
					flag = false;
					exchangeReqTmpMapper.batchUpdateMultipToFailed(diffCompFirst);
					LOGGER.info("{}渠道,{}产品,{}日期,多币种赎回做失败处理,需要更新的数量为{}",channelCode,fundCode,transDate,diffCompFirst.size());
				}
			}
		}
		return flag;
	}

	/**
	 * @param multipleList 
	 * 找出对于同一用户 与第一笔赎回交易不同币种的交易记录  
	 * @Title: getOneAccoDiffFirstCurrency   
	 * @author: yindy 2020年2月17日 上午10:47:46
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> getOneAccoDiffFirstCurrency(List<Map<String, Object>> multipleList) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		String acco = "001";
		String currency = "001";
		for (Map<String, Object> map : multipleList) {
			String transactionacco = MapUtils.getString(map, "TRANSACTIONACCOUNTID");
			String currencyType = MapUtils.getString(map, "CURRENCYTYPE",Const.CURRENCY_USD_CODE);
			if(!acco.equals(transactionacco)){
				acco = transactionacco;
				currency = currencyType;
			}
			if(!currency.equals(currencyType)){
				returnList.add(map);
			}
		}
		return returnList;
	}

	/**
	         判断该日期该产品是否是该赎回周期的首日   
	 * @Title: checkIsFirstOpenDay   
	 * @author: yindy 2020年2月18日 上午9:16:43
	 * @param: @param channelCode
	 * @param: @param fundCode
	 * @param: @param transDate
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkIsFirstOpenDay(String channelCode, String fundCode, String transDate) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		pchannelInfoMapper=(PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		//判断该渠道该产品的上一个工作日是否是赎回日期,如果是,一致往前判断,如果不是,那该日期就是首日
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		//获得渠道,日期,产品获得该周期的首日
		String marketCode = channelInfo.getCiMarketCode();
		String firstOpenDay = exchangeProcessorService.getFirstOpenDay(channelCode,fundCode,transDate,Const.BUSINESS_024,marketCode);
		if(transDate.equals(firstOpenDay)){
			retMap.put("ISFIRST", true);
			retMap.put("FIRSTDAY", transDate);
		}else{
			retMap.put("ISFIRST", false);
			retMap.put("FIRSTDAY", firstOpenDay);
		}
		return retMap;
		
	}

	/**
	 * 校验最高账面份额
	 * @Title: checkMaxPaperVol   
	 * @author: wanshunbin 2019年7月4日 下午17:59:17
	 * @param: Map<String, Object> map  
	 * @return: Map<String, Object>      
	 */
	public Map<String,Object> checkMaxPaperVol(Map<String,Object> map,String fundCode,String channelCode,String transDate,String businessCode){
		LOGGER.info("校验最高账面份额开始");
		AccountStat accountStat =(AccountStat)map.get("ACCOUNTSTAT");
		String taAccountId=accountStat.getAsTaAccountId();
		exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
		productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
		String paperExchangeVol=exchangeReqTmpMapper.selectPaperExchangeVol(fundCode, taAccountId,transDate);
	    Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("CHANNELCODE", channelCode);
		inputMap.put("TAACCOUNTID", taAccountId);
		inputMap.put("FUNDCODE", fundCode);
		inputMap.put("TRANSACTIONACCOUNTID", accountStat.getAsTransactionAccountId());
		BigDecimal count=new BigDecimal(0);
		//查询产品类型 
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("CHANNELCODE", channelCode);
		queryMap.put("PRODUCTCODE", fundCode);
		queryMap.put("BATCHNO", "1");
		PProductInfo productInfo =  productInfoMapper.selectByProductCodeBatchNo(queryMap);
		String fundType = productInfo.getPiProductType(); //产品类型
		Map<String,Object> actSharesMap = new HashMap<String, Object>();
		productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
		if(!StringUtils.isEmpty(fundType) && (Const.FUND_TYPE_123.contains(fundType) || Const.FUND_TYPE_00.equals(fundType)) ){
			//查询持仓
			actSharesMap = productInfoMapper.selectPositionByFundAcco(inputMap);
		}else if (!StringUtils.isEmpty(fundType) && Const.FUND_TYPE_89.contains(fundType)){
			contractInfoMapper = (ContractInfoMapper)SpringUtils.getBean(ContractInfoMapper.class);
			List<Map<String,Object>> contractList = 
					contractInfoMapper.selectContractInfoList(taAccountId,fundCode,null,channelCode,accountStat.getAsTransactionAccountId(),null,null);
			Map<String, Object> contractMap = new HashMap<String, Object>();
			if(contractList != null && contractList.size() > 0 ){
				contractMap = contractList.get(0);
				String taProductCode = MapUtils.getString(contractMap, "PRODUCTCODE");
				String inContract = MapUtils.getString(contractMap, "INCONTRACT");
				actSharesMap = productInfoMapper.queryRemainShares(taProductCode,inContract);
			}
		}else{
			actSharesMap = productInfoMapper.selectNoCurrenyPosition(inputMap);
		}
		
		
		LOGGER.info("通过："+(inputMap == null ? "" : inputMap.toString())+"查询持仓的结果为："+(actSharesMap == null ? null : actSharesMap.toString()));
		if(actSharesMap!=null){
			LOGGER.info(channelCode+"---"+taAccountId+"有持仓！");
			String availableVol = MapUtils.getString(actSharesMap, "F_REMAINSHARES", "0");//可用份额
			String unConfirmBal = MapUtils.getString(actSharesMap, "F_UNCONFIRMBAL", "0");//未确认份额
			BigDecimal availableVolBig = new BigDecimal(availableVol);
			BigDecimal unConfirmBalBig = new BigDecimal(unConfirmBal);
			BigDecimal paperExchangeVolBig = new BigDecimal(paperExchangeVol);
			count = count.add(availableVolBig).add(unConfirmBalBig).add(paperExchangeVolBig);
		}
		String indiMaxVol=StringUtils.isBlanksTransZero(map.get("INDIMAXVOL"));//个人最高账面份额
		String instMaxVol=StringUtils.isBlanksTransZero(map.get("INSTMAXVOL"));//机构最高账面份额
		
		
		//多币种取交易申请币种对应的产品信息校验 
		if(Const.FUND_TYPE_08.equals(fundType)){
			//查询该币种对应的自取到和产品信息由误配置
			productSalePrarmsMapper = (ProductSalePrarmsMapper)SpringUtils.getBean(ProductSalePrarmsMapper.class);
			String currencyType = MapUtils.getString(map, "CURRENCYTYPE", Const.CURRENCY_USD_CODE);
			Integer subCount = productSalePrarmsMapper.queryCountSubChannelByCurrencyType(channelCode,fundCode,currencyType);
			if(subCount == null || subCount == 0){ //该币种没有配置相应的子渠道和产品参数
				map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00111));
				map.put("RETURNCODE", ExceptionConStants.retCode_9999);
				map.put("ERRORDEC", ExceptionConStants.retMsg_T00111);
				LOGGER.info("{}渠道,{}产品,{}币种,没有设置币种相应的产品信息！",channelCode,fundCode,currencyType);
				return map;
			}
		}
		
		
		//多币种,根据币种查询产品配置信息
		ProductSalePrarms product  = getProductSaleParams(map,fundType,channelCode,fundCode);
		if(product != null){
			indiMaxVol = product.getPspIndiMaxVol().toString();
			instMaxVol = product.getPspInstMaxVol().toString();
		}
		
		//交易个人和机构标识非必填,修改为取账户里面的
		String indiorinst= accountStat.getAsIndividualOrInstitution();//(String)map.get("INDIVIDUALORINSTITUTION");
		
		if(Const.INDIVIDUAL.equals(indiorinst)){
			if(!Const.BUSINESS_024.equals(businessCode)){
				if(count.compareTo(new BigDecimal(indiMaxVol))==1){
					map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00053));
					map.put("RETURNCODE", ExceptionConStants.retCode_9999);
					map.put("ERRORDEC", ExceptionConStants.retMsg_T00053);
					map.put("TASERIALNO", "");
					LOGGER.info((inputMap == null ? "" : inputMap.toString())+"校验个人最高账面份额不通过");
				}
			}
			if(Const.BUSINESS_024.equals(businessCode)){
				if(actSharesMap == null 
						|| ("0".equals(MapUtils.getString(actSharesMap, "F_REMAINSHARES", "0")))){
					map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00018));
					map.put("RETURNCODE", ExceptionConStants.retCode_9999);
					map.put("ERRORDEC", ExceptionConStants.retMsg_T00018);
					map.put("TASERIALNO", "");
					LOGGER.info((inputMap == null ? "" : inputMap.toString())+"个人持仓/可用份额为空");
				}
			}
		}
		
		if(Const.INSTITUTION.equals(indiorinst)){
			if(!Const.BUSINESS_024.equals(businessCode)){
				if(count.compareTo(new BigDecimal(instMaxVol))==1){
					map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00053));
					map.put("RETURNCODE", ExceptionConStants.retCode_9999);
					map.put("ERRORDEC", ExceptionConStants.retMsg_T00053);
					map.put("TASERIALNO", "");
					LOGGER.info((inputMap == null ? "" : inputMap.toString())+"校验机构最高账面份额不通过");
				}
			}
			
			if(Const.BUSINESS_024.equals(businessCode)){
				if(actSharesMap == null 
						|| ("0".equals(MapUtils.getString(actSharesMap, "F_REMAINSHARES", "0")))){
					map.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00018));
					map.put("RETURNCODE", ExceptionConStants.retCode_9999);
					map.put("ERRORDEC", ExceptionConStants.retMsg_T00018);
					map.put("TASERIALNO", "");
					LOGGER.info((inputMap == null ? "" : inputMap.toString())+"机构持仓/可用份额为空");
				}
			}
		}
		return map;
	}
	
	/**
	 * 根据渠道代码  产品代码  币种  查询多币种产品信息   
	 * @Title: getProductSaleParams   
	 * @author: yindy 2020年1月16日 下午6:06:21
	 * @param: @param map
	 * @param: @param fundType
	 * @param: @param channelCode
	 * @param: @param fundCode
	 * @param: @return      
	 * @return: ProductSalePrarms      
	 * @throws
	 */
	@Override
	public  ProductSalePrarms getProductSaleParams(Map<String, Object> map,
			String fundType, String channelCode, String fundCode) {
		ProductSalePrarms product = null;
		if(Const.FUND_TYPE_08.equals(fundType)){
			String currencyType = MapUtils.getString(map, "CURRENCYTYPE", Const.CURRENCY_USD_CODE);
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("CHANNELCODE", channelCode);
			query.put("CURRENCYTYPE", currencyType);
			query.put("FUNDCODE", fundCode);
			productSalePrarmsMapper = (ProductSalePrarmsMapper)SpringUtils.getBean(ProductSalePrarmsMapper.class);
			product = productSalePrarmsMapper.queryProductParamInfo(query);
		}
		return product;
	}

	/**
	 * 发送货币类交易   
	 * @Title: sendMoneyTransToTa   
	 * @author: yindy 2019年12月10日 上午10:23:26
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> sendMoneyTransToTa(Map<String, Object> map){
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		String money = sysDictService.findDictVo(Const.MONEY);
		map.put("FUNDTYPES", Arrays.asList(money.split(",")));
		LOGGER.info("查询并发送货币类交易到TA的产品类型为:"+money);
		return sendTransactionApplyDataToTA(map);
	}
	
	/**
	 * 发送非货币类交易  
	 * @Title: sendNoMoneyTransToTa   
	 * @author: yindy 2019年12月10日 上午10:23:48
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> sendNoMoneyTransToTa(Map<String, Object> map){
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		String noMoney = sysDictService.findDictVo(Const.NO_MONEY);
		map.put("FUNDTYPES", Arrays.asList(noMoney.split(",")));
		LOGGER.info("查询并发送非货币类交易到TA产品类型为:"+noMoney);
		return sendTransactionApplyDataToTA(map);
	}
	/**
	 * 分配T+N产品  
	 * @Title: allotTNfundCode   
	 * @author: yindy 2019年12月10日 上午10:27:59
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> allotTNfundCode(Map<String, Object> map){
		String channelCode = MapUtils.getString(map, "CHANNELCODE");
		String transDate = MapUtils.getString(map, "TRANSDATE");
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(map);
		exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
		List<Map<String,Object>> tnSmallContList = exchangeReqTmpMapper.getTNSmallContractsBeTraded(channelCode,transDate);
		if(tnSmallContList.size()>0){
			LOGGER.info(transDate+"交易的渠道编号为："+channelCode+"检查出有T+N小额合同交易："+tnSmallContList.size()+"笔");
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00051));
		}else {
			LOGGER.info(transDate+"交易的渠道编号为："+channelCode+"没有需要分配的T+N产品！");
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		}
		return retMap;
	}
	
	
	
	/**
	 * 将数据发送至TA，并保存到确认表中
	 * @Title: sendTransactionApplyDataToTA   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> sendTransactionApplyDataToTA(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		try {
		
			CallS100454 s100454 = new CallS100454();
			CallS100434 s100434 = new CallS100434();
			String channelCode = MapUtils.getString(map,"CHANNELCODE");
			String transDate = MapUtils.getString(map,"TRANSDATE");
			boolean falg=true;
			LOGGER.info(channelCode+"交易发TA开始");
			//查询正确的申请发送TA
			exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
			contractInfoMapper= (ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
			productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
			List<Map<String, Object>> transtList = exchangeReqTmpMapper.selectTransReqSendTaData(map);
			LOGGER.info(channelCode+"查询的需要发送TA的交易数据条数为:"+(transtList == null ? 0:transtList.size()));
			//没通过校验的不发TA
			for (Map<String, Object> trans : transtList) {
				String businessCode=StringUtils.getStringValue((String)trans.get("BUSINESSCODE"));
				String fundCode=StringUtils.getStringValue((String)trans.get("FUNDCODE"));
				//029编码没有经过业务校验  没有给到批次号，这里给默认值，下面查产品信息
				String batchNo=MapUtils.getString(trans, "BATCHNO", "1");
				
				Map<String,Object> productInfoMap=Maps.newHashMap();
				productInfoMap.put("PRODUCTCODE", fundCode);
				productInfoMap.put("CHANNELCODE", channelCode);
				productInfoMap.put("BATCHNO", batchNo);
				PProductInfo productInfo=productInfoMapper.selectByProductCodeBatchNo(productInfoMap);
				LOGGER.info("通过"+(productInfoMap == null ? "":productInfoMap.toString())+"查询的产品信息为:"+(productInfo == null ? "": productInfo.toString()));
				trans.put("PRODUCTINFO", productInfo);
				
				String fundType = productInfo.getPiProductType(); //产品类型
				
				if(Const.BUSINESS_024.equals(businessCode)){
					/**
					 * 多币种,子渠道号与赎回币种对应的子渠道号不一致,调用接口修改合同
					 * 20200117 定的方案
					 * 
					 * 2020214 换成修改划款客户信息的币种字段
					 */
					if(Const.FUND_TYPE_08.equals(fundType)){
						String currencyType = MapUtils.getString(trans, "CURRENCYTYPE");
						if(!StringUtils.isEmpty(currencyType)){
							String inContract = MapUtils.getString(trans,"INCONTRACT");//内部合同编号
							productSalePrarmsMapper=(ProductSalePrarmsMapper) SpringUtils.getBean(ProductSalePrarmsMapper.class);
							//查询购买时的币种
							String transactionAccountid = MapUtils.getString(trans,"TRANSACTIONACCOUNTID");
							String firstCurrencyType = productSalePrarmsMapper.queryFirstCurrencyType(channelCode, transactionAccountid, inContract);
							LOGGER.info("购买合同对应的币种为:{},赎回币种对应的币种为:{}",firstCurrencyType,currencyType);
							if(!currencyType.equals(firstCurrencyType)){
								LOGGER.info("购买时的币种与赎回的不一致,需要调用修改接口");
								//调用修改接口
								CallS100512 s100512 = new CallS100512();
								Map<String, Object> s100512map = s100512.callS100512(trans);
								LOGGER.info("{}渠道调用修改币种接口返回结果为{}",channelCode,s100512map);
								String s100512Code = MapUtils.getString(s100512map, "retCode");
								if(!ExceptionConStants.retCode_0000.equals(s100512Code)){
									falg=false;
									LOGGER.info(channelCode+"调用S100512接口返回失败");
									Map<String,Object> updateMap = new HashMap<String,Object>();
									updateMap.put("APPSHEETSERIALNO", StringUtils.getStringValue((String)trans.get("APPSHEETSERIALNO")));
									updateMap.put("TAERRORDETAIL", MyMapUtils.getStringToSqlByTrim(s100512map,"retMsg"));
									updateMap.put("RETURNCODE", MyMapUtils.getStringToSqlByTrim(s100512map,"retCode"));
									updateMap.put("ERRORDEC", MyMapUtils.getStringToSqlByTrim(s100512map,"retMsg"));
									updateMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
									updateMap.put("CHANNELCODE", channelCode);
									updateMap.put("TRANSDATE", transDate);
									updateMap.put("FUNDCODE",fundCode);
									exchangeReqTmpMapper.updateExchangeReqData(updateMap);
									continue ;
								}
							}
						}
					}
					Map<String,Object> s100434Map = s100434.callS100434(trans);
					LOGGER.info(channelCode+"s100434赎回调用返回结果：" + s100434Map);
					String s100434Result = StringUtils.getStringValue((String)s100434Map.get("RetCode"));
					if(ExceptionConStants.retCode_0000.equals(s100434Result)){
						LOGGER.info(channelCode+"调用S100434接口返回成功");
						Map<String,Object> updateMap = new HashMap<String,Object>();
						updateMap.put("APPSHEETSERIALNO", StringUtils.getStringValue((String)trans.get("APPSHEETSERIALNO")));
						updateMap.put("TAERRORDETAIL", MyMapUtils.getStringToSqlByTrim(s100434Map,"RetMsg"));
						updateMap.put("TATRANSSTATUS", Const.SEND_TO_TA_434);
						updateMap.put("ERRORDEC", Const.REDEEM_SUCCESS);
						updateMap.put("RETURNCODE", MyMapUtils.getStringToSqlByTrim(s100434Map,"RetCode"));
						updateMap.put("TARETSERIALNO", MyMapUtils.getStringToSqlByTrim(s100434Map,"REQUESTNO"));
						updateMap.put("CHANNELCODE", channelCode);
						updateMap.put("TRANSDATE", transDate);
						updateMap.put("FUNDCODE",fundCode);
						exchangeReqTmpMapper.updateExchangeReqData(updateMap);
					}else{
						falg=false;
						LOGGER.info(channelCode+"调用S100434接口返回失败");
						Map<String,Object> updateMap = new HashMap<String,Object>();
						updateMap.put("APPSHEETSERIALNO", StringUtils.getStringValue((String)trans.get("APPSHEETSERIALNO")));
						updateMap.put("TAERRORDETAIL", MyMapUtils.getStringToSqlByTrim(s100434Map,"RetMsg"));
						updateMap.put("RETURNCODE", MyMapUtils.getStringToSqlByTrim(s100434Map,"RetCode"));
						updateMap.put("ERRORDEC", MyMapUtils.getStringToSqlByTrim(s100434Map,"RetMsg"));
						updateMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
						updateMap.put("CHANNELCODE", channelCode);
						updateMap.put("TRANSDATE", transDate);
						updateMap.put("FUNDCODE",fundCode);
						exchangeReqTmpMapper.updateExchangeReqData(updateMap);
					}
	
				}else if (Const.BUSINESS_020.equals(businessCode) || Const.BUSINESS_022.equals(businessCode)){
					//发送454  442 交易接口 
					Map<String,Object> s100454Map  = new HashMap<String, Object>();
					String appSheetSerialNo = MapUtils.getString(trans, "APPSHEETSERIALNO");
					String taTransStatus = MapUtils.getString(trans, "TATRANSSTATUS");
					String reserverno = MapUtils.getString(trans, "TASERIALNO"); //预约编号
					String errorDec = null; //页面展示信息
					String validflag = null; //成功失败标识
					String inContract = MapUtils.getString(trans, "INCONTRACT") ; //内部合同号
					String outContract = MapUtils.getString(trans, "OUTCONTRACT") ; //外部合同号
					if(taTransStatus == null){ //没调用过TA接口
						LOGGER.info("接口s100454处理开始！");
						s100454Map = s100454.callS100454(trans); //调用454接口
						LOGGER.info(channelCode+"s100454是否含费新合同结果：" + s100454Map);
						//返回的状态
						String retCode = MapUtils.getString(s100454Map, "RETCODE",ExceptionConStants.retCode_0000);
						String retMsg = MapUtils.getString(s100454Map, "RETMSG");
						String taAccountId = MapUtils.getString(s100454Map, "TAACCOUNTID");
						reserverno = MapUtils.getString(s100454Map, "RESERVERNO");
						if(ExceptionConStants.retCode_0000.equals(retCode)){ //调用454成功
							LOGGER.info(channelCode+"调用S100454接口返回成功");
							errorDec = Const.BUY_APPEND_SUCCESS;
							taTransStatus = Const.SEND_TO_TA_454;
							inContract = MapUtils.getString(s100454Map,"CONTRACTSERIALNO","");
							outContract = MapUtils.getString(s100454Map,"TRUSTCONTRACTID","");
							
							String taFundCode= MapUtils.getString(trans,"TAPRODUCTCODE",""); 
							String transActionAccount = MapUtils.getString(trans,"TRANSACTIONACCOUNTID","");
							
							List<Map<String, Object>> contracList = contractInfoMapper.selectContractInfoList(taAccountId, fundCode, taFundCode, channelCode, transActionAccount,inContract,"");
							if (contracList == null || contracList.size() == 0) {
								LOGGER.info(channelCode+","+taAccountId+","+fundCode+","+"首次购买成功！");
								//插入合同表
								Map<String,Object> inputMap = new HashMap<String,Object>();
								inputMap.put("TAACCOUNTID",taAccountId);
								inputMap.put("FUNDCODE",fundCode);
							    inputMap.put("INCONTRACT",inContract);
							    inputMap.put("OUTCONTRACT",outContract);
							    inputMap.put("TRANSACTIONACCOUNT",MapUtils.getString(trans, "TRANSACTIONACCOUNTID"));
							    inputMap.put("DISTRIBUTORCODE",MapUtils.getString(trans, "DISTRIBUTORCODE"));
							    inputMap.put("BRANCHCODE",MapUtils.getString(trans, "BRANCHCODE"));
							    inputMap.put("CHANNELCODE",channelCode);
							    inputMap.put("BATCHNO",batchNo);
							    inputMap.put("PRODUCTCODE",MapUtils.getString(trans, "TAPRODUCTCODE"));
							    inputMap.put("APPSHEETSERIALNO",appSheetSerialNo);
							    inputMap.put("VALIDFLAG","01");
							    inputMap.put("INSERTDATE",MapUtils.getString(trans, "BUSINESSDATE"));
							    
							    LOGGER.info(channelCode+"调用S100454成功,插入合同");
								contractInfoMapper.insertContractInfo(inputMap);
								errorDec = Const.BUY_FIRST_SUCCESS;
							}
						}else{
							LOGGER.info(channelCode+"调用S100454接口返回失败");
							falg = false;
							validflag = Const.BUSINESS_STUTAS_00;
							errorDec = retMsg;
						}
						//跟新交易申请表
						Map<String,Object> updateMap = new HashMap<String,Object>();
						updateMap.put("APPSHEETSERIALNO", appSheetSerialNo);
						updateMap.put("CHANNELCODE", channelCode);
						updateMap.put("TRANSDATE", transDate);
						updateMap.put("FUNDCODE",fundCode);
						
						updateMap.put("ERRORDEC", errorDec);
						updateMap.put("VALIDFLAG",validflag);
						updateMap.put("TAERRORDETAIL", retMsg);
						updateMap.put("RETURNCODE", retCode);
						updateMap.put("TATRANSSTATUS", taTransStatus);
						updateMap.put("TARETSERIALNO", reserverno);
						updateMap.put("TAACCOUNTID", taAccountId);
						updateMap.put("CHANNELCODE", channelCode);
						updateMap.put("TRANSDATE", transDate);
						updateMap.put("INCONTRACT",inContract);
						updateMap.put("OUTCONTRACT",outContract);
						exchangeReqTmpMapper.updateExchangeReqData(updateMap);
						LOGGER.info("接口s100454处理结束！");
						
						if(ExceptionConStants.retCode_0000.equals(retCode)){ //454成功
							//调用442接口的数据
							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap.put("CHANNELCODE", channelCode);
							dataMap.put("FUNDCODE", fundCode);
							dataMap.put("TRANSDATE", transDate);
							dataMap.put("RESERVERNO", reserverno);
							falg = handelS1000442(trans,dataMap,falg);
						}
					}else if(Const.SEND_TO_TA_454.equals(taTransStatus)){ //发454成功442失败的
						//调用442接口
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("CHANNELCODE", channelCode);
						dataMap.put("FUNDCODE", fundCode);
						dataMap.put("TRANSDATE", transDate);
						dataMap.put("RESERVERNO", reserverno);
						falg = handelS1000442(trans,dataMap,falg);
					}
				}else if (Const.BUSINESS_052.equals(businessCode)){ //撤单交易
					LOGGER.info("{}渠道,{}时间,{}产品,撤单交易",channelCode,transDate,fundCode);
					//查询是否有申请过来的原申请单号为确认的交易 ？
					String retCode = "";
					String msg = "";
					String taTransStatus = MapUtils.getString(trans, "TATRANSSTATUS");
					Map<String, Object> originalMap =  exchangeReqTmpMapper.query052OriginalTrans(trans);
					if(originalMap != null && !originalMap.isEmpty()){
						trans.put("FLAG", "2"); //撤单标识
						String requestno = exchangeReqTmpMapper.query052OriginalRequestNo(originalMap);
						trans.put("REQUESTNO", requestno);
						// 调用41
						Map<String,Object> s100041Map  = new HashMap<String, Object>();
						LOGGER.info("撤单--撤销交易");
						CallS100041 s100041 = new CallS100041();
						s100041Map = s100041.callS100041(trans);
						LOGGER.info("调用S100041接口返回的结果为{}",(s100041Map == null ? "" : s100041Map.toString()));
						retCode = MapUtils.getString(s100041Map, "retCode");
						msg = MapUtils.getString(s100041Map, "retMsg");
						taTransStatus = Const.SEND_TO_TA_041;
						// 判断撤销的是首次 还是追加  查合同表，有数据，首次，没数据  非首次  
						int count  = contractInfoMapper.select052TransIsFirst(originalMap);
						if(count > 0 && ExceptionConStants.retCode_0000.equals(retCode)){ //合同和交易同一天插入 为首次购买
							//把合同修该失效
							int num = contractInfoMapper.update052Contract(originalMap);
							LOGGER.info("撤单把合同置失效的而数量为:{}",num);
						}
						//修改交易数据
						if(ExceptionConStants.retCode_0000.equals(retCode)){
							Map<String,Object> updateMap = new HashMap<String,Object>();
							updateMap.put("APPSHEETSERIALNO", StringUtils.getStringValue((String)trans.get("APPSHEETSERIALNO")));
							updateMap.put("TAERRORDETAIL", msg);
							updateMap.put("TATRANSSTATUS", taTransStatus);
							updateMap.put("ERRORDEC", Const.CANCEL_SUCCESS);
							updateMap.put("RETURNCODE", retCode);
							updateMap.put("CHANNELCODE", channelCode);
							updateMap.put("TRANSDATE", transDate);
							updateMap.put("FUNDCODE",fundCode);
							LOGGER.info("撤单交易,把该条{}交易置成功！",updateMap.toString());
							exchangeReqTmpMapper.updateExchangeReqData(updateMap);
							//把原始交易置失效 
							originalMap.put("ERRORDEC", "撤单"+trans.get("APPSHEETSERIALNO")+"对应的原始交易!");
							originalMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
							updateMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
							LOGGER.info("撤单交易,把原交易置失败！");
							exchangeReqTmpMapper.updateExchangeReqData(originalMap);
						}else{
							LOGGER.info("撤单交易调用接口失败！");
							falg=false;
							Map<String,Object> updateMap = new HashMap<String,Object>();
							updateMap.put("APPSHEETSERIALNO", StringUtils.getStringValue((String)trans.get("APPSHEETSERIALNO")));
							updateMap.put("TAERRORDETAIL", msg);
							updateMap.put("RETURNCODE", retCode);
							updateMap.put("ERRORDEC", msg);
							updateMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
							updateMap.put("CHANNELCODE", channelCode);
							updateMap.put("TRANSDATE", transDate);
							updateMap.put("FUNDCODE",fundCode);
							exchangeReqTmpMapper.updateExchangeReqData(updateMap);
						}
					
					}
					
				}else{
					//业务编码不在020 022 024 052 中全部失败  20200114 国泰君安发送029编码 修改
					falg = false;
					Map<String, Object> updateMap = new HashMap<String, Object>();
					updateMap.put("APPSHEETSERIALNO", StringUtils.getStringValue((String)trans.get("APPSHEETSERIALNO")));
					updateMap.put("TAERRORDETAIL", "交易失败，该业务类型暂不支持！");
					updateMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
					updateMap.put("ERRORDEC", "交易失败，该业务类型暂不支持！");
					updateMap.put("CHANNELCODE", channelCode);
					updateMap.put("TRANSDATE", transDate);
					updateMap.put("FUNDCODE",fundCode);
					updateMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					exchangeReqTmpMapper.updateExchangeReqData(updateMap);
				}
			}
			
			if(falg){
				retMap.putAll(map);
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}else{
				retMap.putAll(map);
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00052)); 
			}
			LOGGER.info(channelCode+"渠道"+transDate+"交易发TA结束");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("交易发送TA错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "sendTransactionApplyDataToTA方法,交易发送TA异常,请联系管理员!");
			return retMap;
		}
	}

	//调用442接口处理逻辑
	private boolean handelS1000442(Map<String, Object> trans,Map<String, Object> dataMap, boolean flag) {
		LOGGER.info("接口s100442处理开始！");
		String appSheetSerialNo = MapUtils.getString(trans, "APPSHEETSERIALNO");
		String channelCode = MapUtils.getString(dataMap, "CHANNELCODE");
		String fundCode = MapUtils.getString(dataMap, "FUNDCODE");
		String reserverno = MapUtils.getString(dataMap, "RESERVERNO");
		String transDate = MapUtils.getString(dataMap, "TRANSDATE");
		
		//调用442接口
	    CallS100442 s100442 = new CallS100442();
	    trans.put("RESERVERNO", reserverno);
		Map<String, Object> s100442Map = s100442.callS100442(trans);
		LOGGER.info(channelCode+"s100442缴款方式调用结果：" + s100442Map);
		String retCode = MapUtils.getString(s100442Map, "RETCODE",ExceptionConStants.retCode_0000);
		String retMsg = MapUtils.getString(s100442Map, "RETMSG","");
		String taAccountId = MapUtils.getString(s100442Map, "TAACCOUNTID");
		
		Map<String, Object> commonMap = new HashMap<String, Object>();
	    commonMap.put("CHANNELCODE",channelCode);
	    commonMap.put("APPSHEETSERIALNO",appSheetSerialNo);
	    commonMap.put("RETURNCODE",retCode);
	    commonMap.put("TRANSDATE",transDate);
	    commonMap.put("FUNDCODE",fundCode);
	    
		if(ExceptionConStants.retCode_0000.equals(retCode)){ //442成功
			LOGGER.info(channelCode+","+taAccountId+","+fundCode+","+"442缴款方式调用成功！");
			Map<String, Object> updateMap= new HashMap<String, Object>();
			updateMap.put("VALIDFLAG","01");
			updateMap.put("TATRANSSTATUS",Const.SEND_TO_TA_442);
			updateMap.put("TAERRORDETAIL", retMsg.equals("")?"新合同签署成功！":retMsg);
			updateMap.put("TARETSERIALNO", MapUtils.getString(s100442Map, "RESERVENO", reserverno));
			updateMap.putAll(commonMap);
			exchangeReqTmpMapper.updateExchangeReqData(updateMap);
		}else{ //442失败
			flag=false;
			LOGGER.info(channelCode+","+taAccountId+","+fundCode+","+"442缴款方式调用失败！");
			Map<String,Object> upMap = new HashMap<String,Object>();
			upMap.put("ERRORDEC", retMsg);
			upMap.put("TAERRORDETAIL", retMsg);
			upMap.put("VALIDFLAG","00");
			upMap.putAll(commonMap);
			exchangeReqTmpMapper.updateExchangeReqData(upMap);
		}
		LOGGER.info("接口s100442处理结束！");
		return flag;
	}

	/**
	 * 发送94文件步骤，   
	 * @Title: send94FileDate   
	 * @author: yindy 2019年10月29日 下午1:21:37
	 * @param: @param map
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String,Object> send94FileDate(Map<String, Object> map){
		Map<String,Object> retMap=Maps.newHashMap();
		try {
			
			String channelCode=(String)map.get("CHANNELCODE");
			LOGGER.info(channelCode+"发送94文件开始！");
			String transDate=(String)map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String dealFile=channelInfo.getCiOtherFileType();  //需要生成94文件的产品类型
			if(!StringUtils.isEmpty(dealFile)){
				Map<String,Object> intoMap=Maps.newHashMap();
				intoMap.put("FILETYPES", Const.FILE_TYPE_94);
				intoMap.put("CHANNELCODE", channelCode);
				intoMap.put("TRANSDATE", transDate);
				intoMap.put("CHANNELINFO", channelInfo);
				accountReqCfmService=(AccountReqCfmService) SpringUtils.getBean(AccountReqCfmService.class);
				exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
				readFileService=(ReadFileService) SpringUtils.getBean(ReadFileService.class);
				retMap = accountReqCfmService.selectAndWriteFile(intoMap);
				LOGGER.info(channelCode+"查询并写出94文件结果为："+(retMap == null ? "" : retMap.toString()));
				//校验94文件
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
				Map<String, Object> intoMap1 = check94Num(retMap,channelInfo,transDate);
				if(!ExceptionConStants.retCode_0000.equals(intoMap1.get("retCode"))) {return intoMap1;}
				retMap = exchangeProcessorService.uploadAndUpStat(intoMap1);
				LOGGER.info(channelCode+"上传94文件结果为："+(retMap == null ? "" : retMap.toString()));
			}else{
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			retMap.put("CHANNELCODE", channelCode);
			retMap.put("TRANSDATE", transDate);
			retMap.put("CHANNELINFO", channelInfo);
			LOGGER.info(channelCode+"发送94文件结束！");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("生成发送94文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "send94FileDate方法,生成发送94文件异常,请联系管理员!");
			return retMap;
		}
	}
	
	/**
	 * 校验94文件数量   
	 * @Title: check94Num   
	 * @author: yindy 2019年12月10日 上午8:49:01
	 * @param: @param retMap
	 * @param: @param channelInfo
	 * @param: @param transDate
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> check94Num(Map<String, Object> retMap,
			PchannelInfo channelInfo, String transDate) {
		String channelCode = channelInfo.getCiChannelCode();
		exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		fileDataService=(FileDataService) SpringUtils.getBean(FileDataService.class);
		//文件写完之后,校验数量是否正确,读取文件和查询的数量比较
		String filePath = MyMapUtils.getString(fileDataService.getDictInfo(),"ftplocaldir")+File.separator
				+Const.FTP_UPLOAD+File.separator+channelCode+File.separator+transDate;
		LOGGER.info("查看的文件路径为:"+filePath);
		Map<String, Object> returnMap = readFileService.readFile(filePath,"94", transDate,channelInfo);
		Object obj = MapUtils.getObject(returnMap, "datalist");
		int readNum = 0;
		if(obj!= null){
			List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
			readNum = list.size();
		}
		LOGGER.info("校验94文件，读取的生成的文件里面的数据数量为:"+readNum);
		//查询的数量
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("TYPE", "94");
		queryMap.put("CHANNELCODE", channelCode);
		queryMap.put("CHANNELINFO", channelInfo);
		queryMap.put("TRANSDATE", transDate);
		int queryNum = 0;
		List<Map<String, Object>> dataList = exchangeProcessorService.querySendData(queryMap);
		if(dataList != null){
			queryNum = dataList.size();
		}
		LOGGER.info("校验94文件，读取的数据库里面的数据数量为:"+queryNum);
		//比较
		if(readNum != queryNum){
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00026));
		}
		return retMap;
	}

	@Override
	public Map<String, Object> saveTransactionData(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		boolean flag020 = true;
		boolean flagError020022024 = true;
		boolean flag130 = true;
		boolean flag142 = true;
		boolean flag150 = true;
		
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		try {
		
			String channelCode=(String)map.get("CHANNELCODE");
			String transDate=(String)map.get("TRANSDATE");
			exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			exchangeReqTmpMapper = (ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
	
			sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
			//查询该渠道下所拥有产品 配置的交易类型
			List<SysDictVo>  sysDictList= sysDictService.findDealTypeByChannelCode(channelCode);
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");//渠道基本信息
			String lastDate=exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate), (String)channelInfo.getCiMarketCode());
			
			String lastDateRuby=exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(lastDate), (String)channelInfo.getCiMarketCode());
			String batchNoOnOff=channelInfo.getCiBatchNoOnOff();
			Map<String,Object> selectMap = new HashMap<String,Object>();
			selectMap.put("CHANNELCODE", channelCode);
			selectMap.put("TRANSDATE", transDate);
			selectMap.put("LASTDATE", lastDate);
			selectMap.put("LASTDATERUBY", lastDateRuby);
			selectMap.put("BATCHNOONOFF", batchNoOnOff);
			
			LOGGER.info("交易确认查询"+channelCode+"配置的交易类型的数量为："+(sysDictList == null ? 0 : sysDictList.size()));
			for(SysDictVo sysDictVo:sysDictList){
				String productType = sysDictVo.getDictType().substring(0,1); //产品类型
				String businessCode = sysDictVo.getDictCode(); //业务类型
				//一次性取出所有的120数据（固收、T+N、封闭净值,丰利F类）
				if(businessCode.indexOf(Const.BUSINESS_120)!=-1 && flag020){
					LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"开始获取成功的120确认数据");
					List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectPreMentTradeInfo020(selectMap);
					retList.addAll(tradeInfoList);
					LOGGER.info(channelCode+"获取成功的120确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
					flag020=false;
				}
				
				if(flagError020022024){
					LOGGER.info(channelCode+"开始获取错误确认数据");
					List<Map<String,Object>> tradeInfoListError = exchangeReqTmpMapper.selectPreMentTradeInfoError(selectMap);
					retList.addAll(tradeInfoListError);
					LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取错误确认数据结束,获取的数据数量为:"+(tradeInfoListError == null ? 0:tradeInfoListError.size()));
					flagError020022024=false;
				}
				
				if(businessCode.indexOf(Const.BUSINESS_122)!=-1){
					LOGGER.info(channelCode+"开始获取122确认数据");
					selectMap.put("PRODUCTTYPE", productType);
					selectMap.put("BUSINESSCODE", Const.BUSINESS_022);
					//权益类   FOF 类 7,9 产品 确认日期不是T+1需要特殊处理   
					if(Const.FUND_TYPE_79.contains(productType)){
						LOGGER.info(channelCode+"权益类产品122确认开始"); 
						Map<String, Object> queryMap = new HashMap<String, Object>();
						queryMap.put("CHANNELCODE", channelCode);
						queryMap.put("PRODUCTTYPE", productType);
						queryMap.put("BUSINESSCODE", Const.BUSINESS_022);
						//获取需要当前日期确认的交易日期
						productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
						List<Map<String, Object>> cfmWayList = productInfoMapper.selectProductCfmWay(queryMap);
						if(cfmWayList == null || cfmWayList.size() == 0 ){ //没有权益类/FOF类可用产品
							LOGGER.info("权益类/FOF类可用产品为0");
						}else{
							//计算出每个产品对应的确认日期
							List<Map<String, Object>> cfmDateList = getCfmDate(cfmWayList,transDate,(String)channelInfo.getCiMarketCode());
							//查询需要出确认的交易数据数量，通过产品代码和交易日期，渠道
							queryMap.put("CFMDATELIST", cfmDateList);
							int count = exchangeReqTmpMapper.select022024TradeCount(queryMap);
							LOGGER.info(channelCode+"通过"+(queryMap == null ? null : queryMap.toString())+"获取权益类/FOF类122确认数据的数量为:"+count);
							if(count ==0 ){
								LOGGER.info("权益类/FOF类产品"+transDate+"没有成功的需要确认的申购交易！");
							}else{
								queryMap.put("TRANSDATE", transDate);
								List<Map<String, Object>> list = exchangeReqTmpMapper.select022024TradeCfmList(queryMap);
								LOGGER.info(channelCode+"通过"+(queryMap == null ? null : queryMap.toString())+"获取成功的权益类/FOF类122确认数据结束,获取的数据数量为:"+(list == null ? 0:list.size()));
								if(list!=null && list.size()==count){
									retList.addAll(list);
								}else{
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00073));
									retMap.put("retMsg", "权益类/FOF类产品,"+ExceptionConStants.retMsg_T00073);
									return retMap;
								}
							}
						}
					}else if (Const.FUND_TYPE_08.equals(productType)){ // 多币种  
						//  TODO  暂时没有022 申请  
	//					List<Map<String, Object>> list = exchangeReqTmpMapper.queryEx022024TradeCfmList(null);
						
					}else{
						//查询出需要进行申购确认的交易流水号数据（产品信息表、关联关系表和交易申请表进行关联，用lastDate进行扫描交易流水号）
						List<String> taSerialNosList= exchangeReqTmpMapper.selectTransLastDateTaSerialNo(selectMap);
						//查询出需要进行申购确认的条数（产品信息表、关联关系表和交易申请表进行关联，用lastDate进行扫描交易条数）
						int checkTaTconfirmData= exchangeReqTmpMapper.selectPreMentTradeInfo022Count(selectMap);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取成功的122确认数据的数量为:"+checkTaTconfirmData);
						if(taSerialNosList != null && taSerialNosList.size() > 0 && checkTaTconfirmData > 0){
							//用拿到的交易流水号和TA表TA_TREQUEST、TA_TCONFIRM进行关联取值（注：没用lastDate关联TA,只用了交易流水号）
							List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectPreMentTradeInfo022(selectMap);
							LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取成功的122确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
							if(tradeInfoList!=null&&tradeInfoList.size()==checkTaTconfirmData){
								retList.addAll(tradeInfoList);
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00073));
								return retMap;
							}
						}
					}
					
				}
				
				if(businessCode.indexOf(Const.BUSINESS_124)!=-1){
					LOGGER.info(channelCode+"开始获取124确认数据");
					selectMap.put("PRODUCTTYPE", productType);
					selectMap.put("BUSINESSCODE", Const.BUSINESS_024);
					//权益类   FOF类 7,9产品 确认日期不是T+1需要特殊处理   
					if(Const.FUND_TYPE_79.contains(productType)){
						LOGGER.info(channelCode+"权益类/FOF类产品124确认开始");
						Map<String, Object> queryMap = new HashMap<String, Object>();
						queryMap.put("CHANNELCODE", channelCode);
						queryMap.put("PRODUCTTYPE", productType);
						queryMap.put("BUSINESSCODE", Const.BUSINESS_024);
						//获取需要当前日期确认的交易日期
						productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
						List<Map<String, Object>> cfmWayList = productInfoMapper.selectProductCfmWay(queryMap);
						if(cfmWayList == null || cfmWayList.size() == 0 ){ //没有权益类可用产品
							LOGGER.info("权益类/FOF类可用产品为0");
						}else{
							//计算出每个产品对应的确认日期
							List<Map<String, Object>> cfmDateList = getCfmDate(cfmWayList,transDate,(String)channelInfo.getCiMarketCode());
							//查询需要出确认的交易数据数量，通过产品代码和交易日期，渠道
							queryMap.put("CFMDATELIST", cfmDateList);
							int count = exchangeReqTmpMapper.select022024TradeCount(queryMap);
							LOGGER.info(channelCode+"通过"+(queryMap == null ? null : queryMap.toString())+"获取权益类/FOF类成功的124确认数据的数量为:"+count);
							if(count ==0 ){
								LOGGER.info("权益类产品"+transDate+"没有需要确认的成功的赎回交易！");
							}else{
								queryMap.put("TRANSDATE", transDate);
								List<Map<String, Object>> list  = exchangeReqTmpMapper.select022024TradeCfmList(queryMap);
								LOGGER.info(channelCode+"通过"+(queryMap == null ? null : queryMap.toString())+"获取权益类/FOF类成功的124确认数据结束,获取的数据数量为:"+(list == null ? 0:list.size()));
								if(list!=null && list.size()==count){
									retList.addAll(list);
								}else{
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00101));
									retMap.put("retMsg", "权益类/FOF类产品,"+ExceptionConStants.retMsg_T00101);
									return retMap;
								}
							}
						}
					}else if (Const.FUND_TYPE_08.equals(productType)){// 多币种
						//多币种运作期获取124数据
						Map<String, Object> queryMap = new HashMap<String, Object>();
						queryMap.put("CHANNELCODE", channelCode);
						queryMap.put("PRODUCTTYPE", productType);
						queryMap.put("TRANSDATE", transDate);
						productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
						//获得产品和确认天数N集合
						List<Map<String, Object>> fundCfmWayList = productInfoMapper.selectProductCfmWay(queryMap);
						if(fundCfmWayList == null || fundCfmWayList.size() == 0 ){ //没有多币种类可用产品
							LOGGER.info("多币种类可用产品为0");
						}else{
							queryMap.put("FUNDLIST", fundCfmWayList);
							queryMap.put("CONFIRMTYPE", Const.BUSINESS_024);
							List<Map<String, Object>> fundOpenDate = productInfoMapper.queryTransConfirmDay(queryMap);
							if(fundOpenDate == null || fundOpenDate.size() == 0 ){
								LOGGER.info("{}渠道,没有配置开放日期。",channelCode);
							}else{
								queryMap.put("CONFIRMTYPE", Const.BUSINESS_124);
								List<Map<String, Object>> fundConfirmDate = productInfoMapper.queryTransConfirmDay(queryMap);
								if(fundConfirmDate == null || fundConfirmDate.size() == 0){
									LOGGER.info("{}渠道,多币种类可用产品没有设置赎回确认日期,请前往相应界面设置！",channelCode);
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00110));
									return retMap;
								}
								//获得确认日期是今天的产品
								List<Map<String, Object>> fundList = getCfmFundList(channelInfo,transDate,fundCfmWayList,fundConfirmDate);
								if(fundList == null || fundList.size() == 0 ){
									LOGGER.info("{}渠道,多币种类没有需要{}出赎回结果的产品！",channelCode,transDate);
								}else{
									//2.找出小于这天最近的认购日期
									queryMap.put("FUNDLIST", fundList);
									queryMap.put("CONFIRMTYPE", Const.BUSINESS_024);
									//获得产品--结束时间
									List<Map<String, Object>> fundDatelist = productInfoMapper.queryTransOpenDays(queryMap);//getFundTransDaysList(fundList,Const.BUSINESS_022,transDate,channelInfo);
									if(fundDatelist == null || fundDatelist.size() == 0 ){
										LOGGER.info("{}没有配置产品的{}开放日 ！",channelCode,Const.BUSINESS_024);
									}else{
										//关联TA查出认购结果数据
										Map<String, Object> query = new HashMap<String, Object>();
										query.put("CHANNELCODE", channelCode);
										query.put("FUNDDATELIST", fundDatelist);
										query.put("BUSINESSCODE", Const.BUSINESS_024);
										//查询赎回数据数量,查询上个月的赎回数据关联TA
										int count = exchangeReqTmpMapper.queryEx020024TradeCount(query);
										LOGGER.info("通过"+(query == null ? "" : query.toString())+"查询的多币种产品需要确认的赎回数量为"+count);
										if(count == 0 ){
											LOGGER.info(channelCode+"渠道,上个开放区间没有需要确认的多币种赎回数据。");
										}else{
											query.put("TRANSDATE", transDate);
											List<Map<String, Object>> list  = exchangeReqTmpMapper.queryEx022024TradeCfmList(query);
											LOGGER.info("通过"+(query == null ? "" : query.toString())+"查询的多币种产品赎回成功的数据数量为"+list.size());
											if(list != null && list.size() == count){
												retList.addAll(list);
											}else{
												retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00107));
												return retMap;
											}
										}
									}
								}
							}
						}
					}else{
					    if(Const.FUND_TYPE_03.equals(productType)){
							selectMap.put("LASTDATE", (String)selectMap.get("LASTDATERUBY"));
						}
					    //查询出需要进行赎回确认的交易流水号（产品信息表、关联关系表和交易申请表进行关联，用lastDate进行扫描交易流水号）注：红宝石七天用的T-2日
						List<String>  taSerialNosList = exchangeReqTmpMapper.selectTransLastDateTaSerialNo(selectMap);
						//查询出需要进行赎回确认的条数（产品信息表、关联关系表和交易申请表进行关联，用lastDate进行扫描交易条数）
						int checkTaTconfirmData= exchangeReqTmpMapper.selectPreMentTradeInfo024Count(selectMap);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取成功的124确认数据的数量为:"+checkTaTconfirmData);
						if(taSerialNosList != null && taSerialNosList.size() > 0 && checkTaTconfirmData>0){
							//用拿到的交易流水号和TA表TA_TREQUEST、TA_TCONFIRM进行关联取值（注：没用lastDate关联TA,只用了交易流水号）
							List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectPreMentTradeInfo024(selectMap);
							LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取成功的124确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
							if(tradeInfoList != null && tradeInfoList.size() == checkTaTconfirmData){
								retList.addAll(tradeInfoList);
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00101));
								return retMap;
							}
						}
					    if(Const.FUND_TYPE_03.equals(productType)){
							selectMap.put("LASTDATE", lastDate);
						}
					}
				}
	
	
				if(businessCode.indexOf(Const.BUSINESSCODE_130)!=-1&&flag130){
					if(Const.FUND_TYPE_0456.contains(productType)){
						selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_130);
						selectMap.put("PRODUCTTYPE", productType);
						selectMap.put("BUSINFLAG", "01");
						selectMap.put("LASTDATE", lastDate);
						selectMap.put("TRANSDATE", transDate);
						LOGGER.info(channelCode+"开始获取130确认数据");
						int checkTaTconfirmData= exchangeReqTmpMapper.selectCheckTaTconfirmData(selectMap);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取130确认数据的数量为:"+checkTaTconfirmData);
						if(checkTaTconfirmData >0 ){
							List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectSubscribeResult(selectMap);
							LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取130确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
							if(tradeInfoList != null && tradeInfoList.size() == checkTaTconfirmData){
								retList.addAll(tradeInfoList);
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00075));
								return retMap;
							}
						}
					}
					// 7权益类  9 fof  
					if(Const.FUND_TYPE_79.contains(productType)){
						//权益类需要转换
						selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_130);
						selectMap.put("PRODUCTTYPE", productType);
						selectMap.put("BUSINFLAG", "01");
						selectMap.put("LASTDATE", lastDate);
						selectMap.put("TRANSDATE", transDate);
						LOGGER.info(channelCode+"权益类/fof开始获取130确认数据");
						int checkTaTconfirmData= exchangeReqTmpMapper.selectCheckTaTconfirmData(selectMap);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"权益类/fof获取130确认数据的数量为:"+checkTaTconfirmData);
						if(checkTaTconfirmData >0 ){
							List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectSubscribeGQResult(selectMap);
							LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取权益/fof130确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
							if(tradeInfoList != null && tradeInfoList.size() == checkTaTconfirmData){
								retList.addAll(tradeInfoList);
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00104));
								return retMap;
							}
						}
					}
					
					if(Const.FUND_TYPE_08.equals(productType)){  //多币种    
						//多币种需要转换
						selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_130);
						selectMap.put("PRODUCTTYPE", productType);
						selectMap.put("BUSINFLAG", "01");
						selectMap.put("LASTDATE", lastDate);
						selectMap.put("TRANSDATE", transDate);
						LOGGER.info(channelCode+"多币种开始获取130确认数据");
						int checkTaTconfirmData= exchangeReqTmpMapper.selectCheckTaTconfirmData(selectMap);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"多币种获取130确认数据的数量为:"+checkTaTconfirmData);
						if(checkTaTconfirmData >0 ){
							List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectmultiCurrencyResult(selectMap);
							LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取多币种130确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
							if(tradeInfoList != null && tradeInfoList.size() == checkTaTconfirmData){
								retList.addAll(tradeInfoList);
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00106));
								return retMap;
							}
						}
						
						
						//多币种运作期获取130数据
						Map<String, Object> queryMap = new HashMap<String, Object>();
						queryMap.put("CHANNELCODE", channelCode);
						queryMap.put("PRODUCTTYPE", productType);
						queryMap.put("TRANSDATE", transDate);
						productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
						//获得产品和确认天数N集合
						List<Map<String, Object>> fundCfmWayList = productInfoMapper.selectProductCfmWay(queryMap);
						if(fundCfmWayList == null || fundCfmWayList.size() == 0 ){ //没有多币种类可用产品
							LOGGER.info("{}渠道,多币种类可用产品为0",channelCode);
						}else{
							queryMap.put("FUNDLIST", fundCfmWayList);
							//20200311 yindy 如果没有配置开放日就不需要走下面的取值逻辑
							queryMap.put("CONFIRMTYPE", Const.BUSINESS_022);
							List<Map<String, Object>> fundOpenDate = productInfoMapper.queryTransConfirmDay(queryMap);
							if(fundOpenDate == null || fundOpenDate.size() == 0 ){
								LOGGER.info("{}渠道,没有配置开放日期。",channelCode);
							}else{
								queryMap.put("CONFIRMTYPE", Const.BUSINESSCODE_130);
								List<Map<String, Object>> fundConfirmDate = productInfoMapper.queryTransConfirmDay(queryMap);
								if(fundConfirmDate == null || fundConfirmDate.size() == 0){
									LOGGER.info("{}渠道,多币种类所有产品没有设置认购确认日期,请前往相应界面设置！",channelCode);
									retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00110));
									return retMap;
								}else{
									//20200512 多个产品,其中一个配置了,另一个没配置,另行判断
									Map<String, Object> checkMap = checkOpenAndConfirmDay(fundCfmWayList);
									if(!ExceptionConStants.retCode_0000.equals(checkMap.get("retCode"))){
										LOGGER.info("{}渠道,多币种类部分产品没有设置认购确认日期,请前往相应界面设置！",channelCode);
										retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00110));
										return retMap;
									}
								}
								//获得确认日期是今天的产品
								List<Map<String, Object>> fundList = getCfmFundList(channelInfo,transDate,fundCfmWayList,fundConfirmDate);
								if(fundList == null || fundList.size() == 0 ){
									LOGGER.info("{}渠道,多币种类没有需要{}出认购结果的产品！",channelCode,transDate);
								}else{
									//2.找出小于这天最近的认购日期
									queryMap.put("FUNDLIST", fundList);
									queryMap.put("CONFIRMTYPE", Const.BUSINESS_022);
									//获得产品-结束时间
									List<Map<String, Object>> fundDatelist = productInfoMapper.queryTransOpenDays(queryMap);//getFundTransDaysList(fundList,Const.BUSINESS_022,transDate,channelInfo);
									if(fundDatelist == null || fundDatelist.size() == 0 ){
										LOGGER.info("{}没有配置产品的{}开放日 ！",channelCode,Const.BUSINESS_022);
									}else{
										//关联TA查出认购结果数据
										Map<String, Object> query = new HashMap<String, Object>();
										query.put("CHANNELCODE", channelCode);
										query.put("FUNDDATELIST", fundDatelist);
										query.put("BUSINESSCODE", Const.BUSINESS_020);
										int count = exchangeReqTmpMapper.queryEx020024TradeCount(query);
										LOGGER.info("通过"+(query == null ? "" : query.toString())+"查询的多币种产品需要确认的认购结果数量为"+count);
										if(count == 0 ){
											LOGGER.info(channelCode+"渠道,上一个开放区间没有需要确认的多币种认购结果数据。");
										}else{
											query.put("TRANSDATE", transDate);
											query.put("PRODUCTTYPE", productType);
											query.put("LASTDATE", lastDate);
											List<Map<String, Object>> list  = exchangeReqTmpMapper.queryMuTradeCfmList(query);
											LOGGER.info("通过"+(queryMap == null ? "" : queryMap.toString())+"查询的多币种产品认购结果成功的数据数量为"+list.size());
											if(list != null && list.size() == count){
												retList.addAll(list);
											}else{
												retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00106));
												return retMap;
											}
										}
									}
								}
							}
						}
					}
				}
				
				if(businessCode.indexOf(Const.BUSINESSCODE_142)!=-1&&flag142){
					if(Const.FUND_TYPE_056.contains(productType)){
						LOGGER.info(channelCode+"开始获取142确认数据");
						selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_142);
						selectMap.put("PRODUCTTYPE", productType);
						int checkTaTconfirmData= exchangeReqTmpMapper.selectCheckExchangereqData(selectMap);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取142确认数据的数量为:"+checkTaTconfirmData);
						if(checkTaTconfirmData > 0){
							List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectRedeemResult(selectMap);
							LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取142确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
							if(tradeInfoList != null && tradeInfoList.size()==checkTaTconfirmData){
								retList.addAll(tradeInfoList);
							}else{
								retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00074));
								return retMap;
							}
								flag142=false;
						}
					}
				}
				
				if(businessCode.indexOf(Const.BUSINESSCODE_150)!=-1&&flag150){
					if(Const.FUND_TYPE_04.equals(productType)){
						LOGGER.info(channelCode+"开始获取150确认数据");
						selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_150);
						List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.selectTransEndandDivi(selectMap);
						retList.addAll(tradeInfoList);
						LOGGER.info(channelCode+"通过"+(selectMap == null ? null : selectMap.toString())+"获取150确认数据结束,获取的数据数量为:"+(tradeInfoList == null ? 0:tradeInfoList.size()));
						flag150=false;
					}
				}
			}
			
			LOGGER.info(channelCode+"交易申请数据补足数据入库确认表处理结束");
			retMap.putAll(map);
			retMap.put("RETVALUE", retList);
			if(retList != null  && retList.size() > 0){
				retMap=saveDealCfmData(retMap);
			}
			 
			//后置校验130数据的申请金额与确认金额一致   去除选择美元金额的  
			Map<String, Object> queryMap = new HashMap<String, Object>();
			LOGGER.info(channelCode+"开始后置校验130数据的申请金额和确认金额");
			queryMap.put("CHANNELCODE", channelCode);
			queryMap.put("TRANSDATE", transDate);
			int count = exchangeReqTmpMapper.select130cfmDiffValueCount(queryMap);
			if(count > 0){
				LOGGER.info(channelCode+"130数据的申请金额和确认金额不一致的数量为:"+count);
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00105));
				return retMap;
			}
	
			//固收部分返本150改成142编码  并且分红数据不给数据
			int upcount = exchangeReqTmpMapper.updateBusinessCodeForpartialAllotment(queryMap);
			LOGGER.info("{}渠道,修改部分返本的业务编码150为142的数量为：",channelCode,upcount);
	
			retMap.putAll(map);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("交易确认错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "saveTransactionData方法,交易确认异常,请联系管理员!");
			return retMap;
		}
	}

	/**
	 * 判断多币种美月盈产品有对应的开放日必须有对应的确认日期   
	 * @Title: checkOpenAndConfirmDay   
	 * @author: yindy 2020年5月12日 下午3:56:36
	 * @param: @param fundCfmWayList
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkOpenAndConfirmDay(
			List<Map<String, Object>> fundCfmWayList) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		for (Map<String, Object> map : fundCfmWayList) {
			Integer open022count = MapUtils.getInteger(map, "OPEN022COUNT");
			Integer open024count = MapUtils.getInteger(map, "OPEN024COUNT");
			Integer confirm130count = MapUtils.getInteger(map, "CONFIRM130COUNT");
			Integer confirm124count = MapUtils.getInteger(map, "CONFIRM124COUNT");
			
			if((open022count != null && open022count > 0 && (confirm130count == null || confirm130count == 0 ))
					|| (open024count != null && open024count > 0 && (confirm124count == null || confirm124count == 0 ))){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00110));
				return  retMap;
			}
		}
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		return  retMap;
	}

	/**
	 * @param fundConfirmDate fundcode -- 20191121
	 * @param fundCfmWayList  fundcode -- N
	 * 获得需要transdate获得赎回确认的产品集合   
	 * @Title: get024CfmFundList   
	 * @author: yindy 2020年1月6日 下午2:57:45
	 * @param: @param channelInfo
	 * @param: @param transDate
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	private List<Map<String, Object>> getCfmFundList(PchannelInfo channelInfo,
			String transDate, List<Map<String, Object>> fundCfmWayList, List<Map<String, Object>> fundConfirmDate) {
		List<Map<String, Object>> fundList = new ArrayList<Map<String, Object>>();
		//产品确认填数转为map 
		Map<String, Object> fundCfmMap = new HashMap<String, Object>();
		for(Map<String, Object> map : fundCfmWayList){
			fundCfmMap.put(MapUtils.getString(map, "FUNDCODE"),map.get("CFMDAY"));
		}
		//求出该产品的确认日期加N等于今天的产品
		for (Map<String, Object> map : fundConfirmDate) {
			String fundCode = MapUtils.getString(map, "FUNDCODE");
			String cfmDate = MapUtils.getString(map, "CONFIRMDAY");
			int cfmWay = MapUtils.getIntValue(fundCfmMap, fundCode);
			for (int i = 0; i < cfmWay; i++) {
				cfmDate = exchangeProcessorService.getNextWorkDay(DateUtils.getNextDay(cfmDate,"1"), channelInfo.getCiMarketCode());
			}
			if(transDate.equals(cfmDate)){//确认日期为今天
				Map<String, Object> fundmap = new HashMap<String, Object>();
				fundmap.put("FUNDCODE", fundCode);
				fundList.add(fundmap);
			}
		}
		return fundList;
	}

	/**
	 * 获得产品的交易日期   
	 * @Title: getCfmDate   
	 * @author: yindy 2019年11月20日 下午4:53:37
	 * @param: @param cfmWayList
	 * @param: @param transDate
	 * @param: @param ciMarketCode
	 * @param: @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> getCfmDate(
			List<Map<String, Object>> cfmWayList, String transDate,
			String ciMarketCode) {
		exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : cfmWayList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			String cfmDate = transDate;
			String fundCode = MapUtils.getString(map, "FUNDCODE", "");
			Integer cfmWay = MapUtils.getInteger(map, "CFMDAY", 1);
			for (int i = 0; i < cfmWay; i++) {
				cfmDate = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(cfmDate), ciMarketCode);
			}
			returnMap.put("FUNDCODE", fundCode);
			returnMap.put("TRANSDATE", cfmDate);
			returnList.add(returnMap);
		}
		return returnList;
	}

	@Override
	public Map<String, Object> updateTransactionData(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		try {
		
			String channelCode=(String)map.get("CHANNELCODE");
			String transDate=(String)map.get("TRANSDATE");
			exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
			productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
			exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
			contractInfoMapper = (ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
			
			//查询该渠道下所拥有产品 配置的交易类型
			List<SysDictVo>  sysDictList= sysDictService.findDealTypeByChannelCode(channelCode);
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");//渠道基本信息
			String lastDate=exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate), (String)channelInfo.getCiMarketCode());
		    //获取T-2个工作日
		    String lastT2WorkDay = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(lastDate), channelInfo.getCiMarketCode());
		    
			Map<String,Object> selectMap = new HashMap<String,Object>();
			selectMap.put("CHANNELCODE", channelCode);
			selectMap.put("TRANSDATE", transDate);
			selectMap.put("LASTDATE", lastDate);
			for(SysDictVo sysDictVo:sysDictList){
				String dealType=sysDictVo.getDictCode();
				String productType=sysDictVo.getDictType().substring(0,1);
				
				if(dealType.indexOf(Const.BUSINESS_124)!=-1){
					if(Const.FUND_TYPE_09.equals(productType)){
						//份额为0，合同置失效 
						List<Map<String, Object>>  list = exchangeReqTmpMapper.query024CfmData(selectMap);
						if(list != null && list.size() > 0 ){
							//更新合同为无效
							int count = contractInfoMapper.updateContractForFof(list);
							LOGGER.info("{}渠道,FOF类,份额为0 的产品的合同置失效的数量为{}!",channelCode,count);
						}
						
					}
				}
				
				if(dealType.indexOf(Const.BUSINESSCODE_142)!=-1){
					selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_142);
					selectMap.put("PRODUCTTYPE", productType);
					//20200513
					int checkTaTconfirmData = exchangeReqTmpMapper.queryCfmCount(selectMap);//exchangeReqTmpMapper.selectCheckExchangereqData(selectMap);
					LOGGER.info("{}渠道{}的142数据数量为{}",transDate,channelCode,checkTaTconfirmData);
					if(checkTaTconfirmData>0){
						List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.query142150ConfirmData(selectMap);//exchangeReqTmpMapper.selectRedeemResult(selectMap);
						//销毁合同
						if(productType.equals(Const.FUND_TYPE_00)){ // 丰利F到期日给数据
							lastT2WorkDay = transDate;
							contractInfoMapper.updateContractFlagAndMsgforF(tradeInfoList);
							LOGGER.info("{}渠道,丰利F到期",channelCode);
						}else{
							int count = contractInfoMapper.updateContractFlagAndMsg(tradeInfoList);
							LOGGER.info("{}渠道,强制赎回产品的合同置失效的数量为{}!",channelCode,tradeInfoList.size());
						}
						//更新产品到期日
						int count1 = productInfoMapper.updateProSetAndEndDate(tradeInfoList,lastT2WorkDay);
						LOGGER.info("{}渠道,强制赎回产品更新产品到期日,更新的数量为{}!",channelCode,count1);
					}
				}
				
				if(dealType.indexOf(Const.BUSINESSCODE_150)!=-1){
					selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_150);
					selectMap.put("PRODUCTTYPE", productType);
					int checkTaTconfirmData = exchangeReqTmpMapper.queryCfmCount(selectMap);
					LOGGER.info("{}渠道{}的150数据数量为{}",transDate,channelCode,checkTaTconfirmData);
					if(checkTaTconfirmData>0){
						//20200513 
						List<Map<String,Object>> tradeInfoList = exchangeReqTmpMapper.query142150ConfirmData(selectMap);//exchangeReqTmpMapper.selectTransEndandDivi(selectMap);
						if(tradeInfoList.size()>0){
							//销毁合同
							int count = contractInfoMapper.updateContractFlagAndMsg(tradeInfoList);
							LOGGER.info("{}渠道,基金清盘产品的合同置失效的数量为{}!",channelCode,count);
							//更新产品到期日
							int count1 = productInfoMapper.updateProSetAndEndDate(tradeInfoList,lastDate);
							LOGGER.info("{}渠道,基金清盘产品更新产品到期日,更新的数量为{}!",channelCode,count1);
						}
					}
				}
				
				if(dealType.indexOf(Const.BUSINESSCODE_130)!=-1){
					String batchNoOnOff=channelInfo.getCiBatchNoOnOff();
					selectMap.put("BUSINESSCODE", Const.BUSINESSCODE_130);
					selectMap.put("PRODUCTTYPE", productType);
					selectMap.put("BATCHNOONOFF", batchNoOnOff);
					//前置  有130数据才修改 20200408 
					int count130 = exchangeReqTmpMapper.queryCfmCount(selectMap);
					LOGGER.info("{}渠道,产品类型{}的130确认数据的数量为{}",channelCode,productType,count130);
					if(count130 > 0 ){
						//更新状态为03  下次不取出这些数据并入成立数据
						int count = exchangeReqTmpMapper.updatePrementData(selectMap);
						LOGGER.info("{}渠道,发送130成立数据后跟新状态为3,更新的数量为{}",channelCode,count);
						//修改关联关系的TA端产品是否已成立为已成立   
						//20200119 讨论 所有产品都更新
						int count1 = exchangeReqTmpMapper.updateIsSetUpForFundType(selectMap);
						LOGGER.info("{}渠道,更新成立后的产品关联关系为已成立,更新的数量为{}",channelCode,count1);
					}
				}
			}
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			retMap.putAll(map);
			LOGGER.info("修改合同、交易、产品信息、产品关联关系状态结束！");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("修改合同状态错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "updateTransactionData方法,修改合同状态异常,请联系管理员!");
			return retMap;
		}
	}
	
	/**
	 * 获取收益类别
	 * @Title: getFundYield   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  
	 * @return: Map<String, Object>      
	 */
	private Map<String, Object> getFundYield(Map<String, Object> tempAccount){
		LOGGER.info("获取收益类别开始。");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)tempAccount.get("CHANNELCODE");
		String fundCode=(String)tempAccount.get("FUNDCODE");
		String applicationAmount = MapUtils.getString(tempAccount, "APPLICATIONAMOUNT", "");
		BigDecimal applicationAmountBig = new BigDecimal(applicationAmount);

		String sectionNumber="";
		String fundRate="";
		String proYieldType=(String)tempAccount.get("PROYIELDTYPE");
		LOGGER.info(channelCode+",产品"+fundCode+",交易金额"+applicationAmount+",收益类别"+proYieldType);
		if(Const.BUSINESS_STUTAS_01.equals(proYieldType)){
			List<Map<String,Object>>  yieldGradeList =channelProductRelationMapper.selectYieldGrade(channelCode,fundCode);
			if(yieldGradeList==null||yieldGradeList.isEmpty()){
				LOGGER.info(channelCode+"为自动计算受益类型时，根据渠道编号、产品编号，获得产品设置的受益率与级别失败");
				retMap.put("SECTIONNUMBER", "0");
				retMap.put("FUNDRATE", StringUtils.getStringValue((String)tempAccount.get("PROYIELD")));
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00020));
				return retMap;
			}
			
			for(Map<String,Object> mapSylAndSylbgsSigle : yieldGradeList){
				String minAmount = StringUtils.getStringValue((String)mapSylAndSylbgsSigle.get("STARTMONEY"));
				String maxAmount = StringUtils.getStringValue((String)mapSylAndSylbgsSigle.get("ENDMONEY"));
				BigDecimal minAmountBig = new BigDecimal(minAmount);
				BigDecimal maxAmountBig = new BigDecimal(maxAmount);
				if(!applicationAmount.equals("")&&!applicationAmount.equals("0")&&applicationAmountBig.compareTo(minAmountBig)>=0&&applicationAmountBig.compareTo(maxAmountBig)<0){
					//收益类别
					sectionNumber = StringUtils.getStringValue((String)mapSylAndSylbgsSigle.get("SECTIONNUMBER"));
					//收益率
					fundRate = StringUtils.getStringValue((String)mapSylAndSylbgsSigle.get("FUNDRATE"));
					LOGGER.info(channelCode+"为自动计算受益类型时，根据渠道编号、产品编号，获得产品设置的受益率与级别成功");
				}
			}	
			
		}else{
			//收益类别
			sectionNumber = "0";
			//收益率
			fundRate = StringUtils.getStringValue(String.valueOf(tempAccount.get("PROYIELD")));
		}
		
		retMap.put("SECTIONNUMBER", sectionNumber);
		retMap.put("FUNDRATE", "".equals(fundRate)?"":fundRate);
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		LOGGER.info("获取收益类别结束。");
		return retMap;
	}
	
	
	
	/**
	 * 认购逻辑处理
	 * @Title: checkTrans020
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  
	 * @return: Map<String, Object>      
	 */
	private Map<String, Object> checkTrans020(Map<String, Object> tempAccount) throws Exception {
		
		String channelCode=(String)tempAccount.get("CHANNELCODE");
		LOGGER.info(channelCode+"交易认购逻辑开始。");
		String fundCode=(String)tempAccount.get("FUNDCODE");
		productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
		Map<String,Object> productInfoMap=productInfoMapper.selectProYield(channelCode,fundCode);
		String feeFlag=(String)productInfoMap.get("FEEFLAG"); //是否含认购费  含费用标识。1:是 0：否
		
		String indiorinst=(String)tempAccount.get("INDIVIDUALORINSTITUTION");//个人或者机构
		AccountStat accountStat=(AccountStat)tempAccount.get("ACCOUNTSTAT");
		indiorinst=accountStat.getAsIndividualOrInstitution();
		String openChannelStat=accountStat.getAsOpenAccountStat();
		BigDecimal applicationAmount = (BigDecimal)tempAccount.get("APPLICATIONAMOUNT");//申请金额或赎回份数
		
		tempAccount.put("OPENCHANNELSTAT", openChannelStat);
		String indibuyFlag= StringUtils.getStringValue((String)tempAccount.get("INDIBUYFLAG"));
        String instbuyFlag= StringUtils.getStringValue((String)tempAccount.get("INSTBUYFLAG"));
        
        
        //多币种,根据币种查询产品配置信息
        String fundType = MapUtils.getString(productInfoMap, "PRODUCTTYPE");
        ProductSalePrarms product  = getProductSaleParams(tempAccount,fundType,channelCode,fundCode);
        if(product != null){
        	indibuyFlag = product.getPspIndiBuyFlag();
        	instbuyFlag = product.getPspInstBuyFlag();
        }
        // 是否含费标识  不判断级差  
		if(Const.INDIVIDUAL.equals(indiorinst)){
			LOGGER.info("个人不含认购费交易");
        	 if(!Const.INDIVIDUAL.equals(indibuyFlag)){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0005));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_List0005);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
        	 }
        	 
             Map<String,Object> inputMap=Maps.newHashMap();
             inputMap.put("TRANSACTIONACCOUNTID", (String)tempAccount.get("TRANSACTIONACCOUNTID"));
             inputMap.put("FUNDCODE", fundCode);
             inputMap.put("CHANNELCODE", channelCode);
             exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
             int firstTradeCount= exchangeReqTmpMapper.selectFirstTradeCount(inputMap);
			
			if(firstTradeCount==0){
				LOGGER.info(channelCode+"，产品"+fundCode+","+MapUtils.getString(tempAccount, "TRANSACTIONACCOUNTID", "")+"个人首次购买校验");
				String indiMinBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMINBIDSAMT"));//个人首次购买最低金额
				String indiBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIBIDSDIFFAMT"));//个人首次购买级差金额
				String indiMaxBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMAXBIDSAMT"));//个人首次购买最高金额
				
				BigDecimal indiMinBidsAmtBig=new BigDecimal(indiMinBidsAmt);
				BigDecimal indiBidsDiffAmtBig=new BigDecimal(indiBidsDiffAmt);
				BigDecimal indiMaxBidsAmtBig=new BigDecimal(indiMaxBidsAmt);
				
				if(product != null){
					indiMinBidsAmtBig = product.getPspIndiMinBidsAmt();
					indiBidsDiffAmtBig = product.getPspIndiBidsDiffAmt();
					indiMaxBidsAmtBig = product.getPspIndiMaxBidsAmt();
				}
				
				
				if(applicationAmount.compareTo(indiMinBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00025));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00025);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(indiMaxBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00026));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00026);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(indiBidsDiffAmtBig)[1];
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00060));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00060);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
				
				
			}else{
				LOGGER.info(channelCode+"，产品"+fundCode+","+MapUtils.getString(tempAccount, "TRANSACTIONACCOUNTID", "")+"个人追加购买校验");
				String indiMinAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMINAPPBIDSAMT"));//个人追加购买最低金额
				String indiAppBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIAPPBIDSDIFFAMT"));//个人追加购买级差金额
				String indiAaxAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMAXAPPBIDSAMT"));//个人追加购买最高金额
				
				BigDecimal indiMinAppBidsAmtBig=new BigDecimal(indiMinAppBidsAmt);
				BigDecimal indiAppBidsDiffAmtBig=new BigDecimal(indiAppBidsDiffAmt);
				BigDecimal indiAaxAppBidsAmtBig=new BigDecimal(indiAaxAppBidsAmt);
				

				if(product != null){
					indiMinAppBidsAmtBig = product.getPspIndiMinAppBidsAmt();
					indiAppBidsDiffAmtBig = product.getPspIndiAppBidsDiffAmt();
					indiAaxAppBidsAmtBig = product.getPspIndiMaxAppBidsAmt();
				}
				
				if(applicationAmount.compareTo(indiMinAppBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00054));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00054);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(indiAaxAppBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00055));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00055);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(indiAppBidsDiffAmtBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00061));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00061);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
				
			}
		}
		
		
		
		if(Const.INSTITUTION.equals(indiorinst)){
			LOGGER.info("机构不含认购费交易");
	       	 if(!Const.INDIVIDUAL.equals(instbuyFlag)){
				tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0006));
				tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_List0006);
				tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
				return tempAccount;
	       	 }
             Map<String,Object> inputMap=Maps.newHashMap();
             inputMap.put("TRANSACTIONACCOUNTID", (String)tempAccount.get("TRANSACTIONACCOUNTID"));
             inputMap.put("FUNDCODE", fundCode);
             inputMap.put("CHANNELCODE", channelCode);
             exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
             int firstTradeCount= exchangeReqTmpMapper.selectFirstTradeCount(inputMap);
			
	       	 
			if(firstTradeCount==0){
				LOGGER.info(channelCode+"，产品"+fundCode+","+MapUtils.getString(tempAccount, "TRANSACTIONACCOUNTID", "")+"机构首次购买校验");
				String instMinBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMINBIDSAMT"));//机构首次购买最低金额
				String instBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTBIDSDIFFAMT"));//机构首次购买级差金额
				String instMaxBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMAXBIDSAMT"));//机构首次购买最高金额
				
				BigDecimal instMinBidsAmtBig=new BigDecimal(instMinBidsAmt);
				BigDecimal instBidsDiffAmtBig=new BigDecimal(instBidsDiffAmt);
				BigDecimal instMaxBidsAmtBig=new BigDecimal(instMaxBidsAmt);
				
				if(product != null){
					instMinBidsAmtBig = product.getPspInstMinBidsAmt();
					instBidsDiffAmtBig = product.getPspInstBidsDiffAmt();
					instMaxBidsAmtBig = product.getPspInstMaxBidsAmt();
				}
				
				if(applicationAmount.compareTo(instMinBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00056));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00056);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(instMaxBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00057));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00057);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(instBidsDiffAmtBig)[1];
				
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00062));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00062);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
			}else{
				LOGGER.info(channelCode+"，产品"+fundCode+","+MapUtils.getString(tempAccount, "TRANSACTIONACCOUNTID", "")+"机构追加购买校验");
				String instMinAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMINAPPBIDSAMT"));//机构追加购买最低金额
				String instAppBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTAPPBIDSDIFFAMT"));//机构追加购买级差金额
				String instMaxAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMAXAPPBIDSAMT"));//机构追加购买最高金额
				
				BigDecimal instMinAppBidsAmtBig=new BigDecimal(instMinAppBidsAmt);
				BigDecimal instAppBidsDiffAmtBig=new BigDecimal(instAppBidsDiffAmt);
				BigDecimal instMaxAppBidsAmtBig=new BigDecimal(instMaxAppBidsAmt);
				
				
				if(product != null){
					instMinAppBidsAmtBig = product.getPspInstMinAppBidsAmt();
					instAppBidsDiffAmtBig = product.getPspInstAppBidsDiffAmt();
					instMaxAppBidsAmtBig = product.getPspInstMaxAppBidsAmt();
				}
				
				if(applicationAmount.compareTo(instMinAppBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00058));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00058);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(instMaxAppBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00059));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00059);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(instAppBidsDiffAmtBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00063));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00063);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
			}
		}
		LOGGER.info(channelCode+"交易认购逻辑结束。");
		return tempAccount;
	}
	
	
	
	/**
	 * 申购逻辑处理
	 * @Title: checkTrans022   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  
	 * @return: Map<String, Object>      
	 */
	private Map<String, Object> checkTrans022(Map<String, Object> tempAccount) throws Exception {
		
		String channelCode=(String)tempAccount.get("CHANNELCODE");
		LOGGER.info(channelCode+"交易申购逻辑结束。");
		String fundCode=(String)tempAccount.get("FUNDCODE");
		productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
		Map<String,Object> productInfoMap=productInfoMapper.selectProYield(channelCode,fundCode);
		String feeFlag=(String)productInfoMap.get("FEEFLAG"); //认购费
		
		String indiorinst=(String)tempAccount.get("INDIVIDUALORINSTITUTION");//个人或者机构
		AccountStat accountStat=(AccountStat)tempAccount.get("ACCOUNTSTAT");
		indiorinst=accountStat.getAsIndividualOrInstitution();
		String openChannelStat=accountStat.getAsOpenAccountStat();
		BigDecimal applicationAmount = (BigDecimal)tempAccount.get("APPLICATIONAMOUNT");//申请金额或赎回份数
		
		tempAccount.put("OPENCHANNELSTAT", openChannelStat);
		
		String indibuyFlag= StringUtils.getStringValue((String)tempAccount.get("INDIBUYFLAG"));
        String instbuyFlag= StringUtils.getStringValue((String)tempAccount.get("INSTBUYFLAG"));
		
        
        //多币种 查询产品信息
        String fundType = MapUtils.getString(productInfoMap, "PRODUCTTYPE");
        ProductSalePrarms product  = getProductSaleParams(tempAccount,fundType,channelCode,fundCode);
        if(product != null){
        	indibuyFlag = product.getPspIndiBuyFlag();
        	instbuyFlag = product.getPspInstBuyFlag();
        }
        
		if(Const.INDIVIDUAL.equals(indiorinst)){
			 LOGGER.info("个人不含认购费交易");
	       	 if(!Const.INDIVIDUAL.equals(indibuyFlag)){
				tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0005));
				tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_List0005);
				tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
				return tempAccount;
	       	 }
	       	 
             Map<String,Object> inputMap=Maps.newHashMap();
             inputMap.put("TRANSACTIONACCOUNTID", (String)tempAccount.get("TRANSACTIONACCOUNTID"));
             inputMap.put("FUNDCODE", fundCode);
             inputMap.put("CHANNELCODE", channelCode);
             exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
             int firstTradeCount= exchangeReqTmpMapper.selectFirstTradeCount(inputMap);
			if(firstTradeCount==0){
				String indiMinBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMINBIDSAMT"));//个人首次购买最低金额
				String indiBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIBIDSDIFFAMT"));//个人首次购买级差金额
				String indiMaxBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMAXBIDSAMT"));//个人首次购买最高金额
				
				BigDecimal indiMinBidsAmtBig=new BigDecimal(indiMinBidsAmt);
				BigDecimal indiBidsDiffAmtBig=new BigDecimal(indiBidsDiffAmt);
				BigDecimal indiMaxBidsAmtBig=new BigDecimal(indiMaxBidsAmt);
				
				
				if(product != null){
					indiMinBidsAmtBig = product.getPspIndiMinBidsAmt();
					indiBidsDiffAmtBig = product.getPspIndiBidsDiffAmt();
					indiMaxBidsAmtBig = product.getPspIndiMaxBidsAmt();
				}
				
				if(applicationAmount.compareTo(indiMinBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00025));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00025);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(indiMaxBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00026));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00026);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(indiBidsDiffAmtBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00060));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00060);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
				
				
			}else{
				String indiMinAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMINAPPBIDSAMT"));//个人追加购买最低金额
				String indiAppBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIAPPBIDSDIFFAMT"));//个人追加购买级差金额
				String indiAaxAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INDIMAXAPPBIDSAMT"));//个人追加购买最高金额
				
				BigDecimal indiMinAppBidsAmtBig=new BigDecimal(indiMinAppBidsAmt);
				BigDecimal indiAppBidsDiffAmtBig=new BigDecimal(indiAppBidsDiffAmt);
				BigDecimal indiAaxAppBidsAmtBig=new BigDecimal(indiAaxAppBidsAmt);
				
				if(product != null){
					indiMinAppBidsAmtBig = product.getPspIndiMinAppBidsAmt();
					indiAppBidsDiffAmtBig = product.getPspIndiAppBidsDiffAmt();
					indiAaxAppBidsAmtBig = product.getPspIndiMaxAppBidsAmt();
				}
				
				if(applicationAmount.compareTo(indiMinAppBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00054));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00054);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(indiAaxAppBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00055));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00055);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(indiAppBidsDiffAmtBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00061));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00061);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
			}
		}
		
		
		
		if(Const.INSTITUTION.equals(indiorinst)&&feeFlag.equals("0")){
			 LOGGER.info("机构不含认购费交易");
	       	 if(!Const.INDIVIDUAL.equals(instbuyFlag)){
				tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_List0006));
				tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_List0006);
				tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
				return tempAccount;
	       	 }
	       	 
	        Map<String,Object> inputMap=Maps.newHashMap();
	        inputMap.put("TRANSACTIONACCOUNTID", (String)tempAccount.get("TRANSACTIONACCOUNTID"));
	        inputMap.put("FUNDCODE", fundCode);
	        inputMap.put("CHANNELCODE", channelCode);
	        exchangeReqTmpMapper=(ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
            int firstTradeCount= exchangeReqTmpMapper.selectFirstTradeCount(inputMap);
			if(firstTradeCount==0){
				String instMinBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMINBIDSAMT"));//机构首次购买最低金额
				String instBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTBIDSDIFFAMT"));//机构首次购买级差金额
				String instMaxBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMAXBIDSAMT"));//机构首次购买最高金额
				
				BigDecimal instMinBidsAmtBig=new BigDecimal(instMinBidsAmt);
				BigDecimal instBidsDiffAmtBig=new BigDecimal(instBidsDiffAmt);
				BigDecimal instMaxBidsAmtBig=new BigDecimal(instMaxBidsAmt);
				
				if(product != null){
					instMinBidsAmtBig = product.getPspInstMinBidsAmt();
					instBidsDiffAmtBig = product.getPspInstBidsDiffAmt();
					instMaxBidsAmtBig = product.getPspInstMaxBidsAmt();
				}
				
				
				if(applicationAmount.compareTo(instMinBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00056));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first +ExceptionConStants.retMsg_T00056);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(instMaxBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00057));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00057);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(instBidsDiffAmtBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00062));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_first+ExceptionConStants.retMsg_T00062);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
			}else{
				String instMinAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMINAPPBIDSAMT"));//机构追加购买最低金额
				String instAppBidsDiffAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTAPPBIDSDIFFAMT"));//机构追加购买级差金额
				String instMaxAppBidsAmt=StringUtils.isBlanksTransZero(productInfoMap.get("INSTMAXAPPBIDSAMT"));//机构追加购买最高金额
				
				BigDecimal instMinAppBidsAmtBig=new BigDecimal(instMinAppBidsAmt);
				BigDecimal instAppBidsDiffAmtBig=new BigDecimal(instAppBidsDiffAmt);
				BigDecimal instMaxAppBidsAmtBig=new BigDecimal(instMaxAppBidsAmt);
				
				if(product != null){
					instMinAppBidsAmtBig = product.getPspInstMinAppBidsAmt();
					instAppBidsDiffAmtBig = product.getPspInstAppBidsDiffAmt();
					instMaxAppBidsAmtBig = product.getPspInstMaxAppBidsAmt();
				}
				
				if(applicationAmount.compareTo(instMinAppBidsAmtBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00058));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00058);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				

				if(applicationAmount.compareTo(instMaxAppBidsAmtBig) ==1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00059));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00059);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=applicationAmount.divideAndRemainder(instAppBidsDiffAmtBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if( DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00063));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00063);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
			}
		}
		LOGGER.info(channelCode+"交易申购逻辑结束。");
		return tempAccount;
	
	}
	
	
	/**
	 * 赎回交易逻辑处理
	 * @Title: checkTrans024   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  
	 * @return: Map<String, Object>      
	 */
	private Map<String, Object> checkTrans024(Map<String, Object> tempAccount) throws Exception {
		String channelCode=(String)tempAccount.get("CHANNELCODE");
		
		LOGGER.info(channelCode+"交易赎回逻辑开始。");
		String fundCode=(String)tempAccount.get("FUNDCODE");
		productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
		contractInfoMapper=(ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
		Map<String,Object> productInfoMap=productInfoMapper.selectProYield(channelCode,fundCode);
		String feeFlag=(String)productInfoMap.get("FEEFLAG");
		Map<String,Object> actInfoMap=getContractAndAccountInfo(tempAccount);
		if(!ExceptionConStants.retCode_0000.equals(actInfoMap.get("retCode"))){
			tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00100);
			return tempAccount;
		}
		String shares = StringUtils.isBlanksTransZero(actInfoMap.get("F_REMAINSHARES"));//可用份额
		BigDecimal usableShares = new BigDecimal(shares);
		
		BigDecimal redeemShares = new BigDecimal(StringUtils.isBlanksTransZero(tempAccount.get("APPLICATIONVOL")));//赎回份额
		//上面有个一样的方法   这个注释掉   yindaiyong 20200211
//		Map<String,Object> productInfoMap=productInfoMapper.selectProYield(channelCode,fundCode);
		tempAccount.put("OPENCHANNELSTAT",actInfoMap.get("OPENCHANNELSTAT"));
		tempAccount.put("INCONTRACT",actInfoMap.get("INCONTRACT"));
		tempAccount.put("OUTCONTRACT",actInfoMap.get("OUTCONTRACT"));
		LOGGER.info("赎回的份额为："+redeemShares+"持仓为："+usableShares);
        
		//是否全额赎回
		if(usableShares.compareTo(redeemShares) == 0){
			tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
			return tempAccount;
		}
		
		//是否超过可用额度
		if(redeemShares.compareTo(usableShares) == 1){
			tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00015));
			tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
			tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00015);
			return tempAccount;
		}
		

		//判断产品信息表中几个值
		if(redeemShares.compareTo(usableShares) == -1){
			//0-机构，1-个人   从账户信息取
			String indiorinst = MapUtils.getString(actInfoMap, "INDIVIDUALORINSTITUTION", (String)tempAccount.get("INDIVIDUALORINSTITUTION"));
			AccountStat accountStat=(AccountStat)tempAccount.get("ACCOUNTSTAT");
			indiorinst=accountStat.getAsIndividualOrInstitution();
			if(Const.INDIVIDUAL.equals(indiorinst)){
				//个人最低赎回份额
				String indiMinRedeemVol=StringUtils.isBlanksTransZero(productInfoMap.get("PI_INDI_MIN_REDEEM_VOL"));
				//个人赎回级差
				String indiRedeemDiff=StringUtils.isBlanksTransZero(productInfoMap.get("PI_INDI_REDEEM_DIFF"));
				

				
				BigDecimal indiMinRedeemVolBig=new BigDecimal(indiMinRedeemVol);
				BigDecimal indiRedeemDiffBig=new BigDecimal(indiRedeemDiff);
				
				if(redeemShares.compareTo(indiMinRedeemVolBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00065));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00065);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999); 
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig = redeemShares.divideAndRemainder(indiRedeemDiffBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if(DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){ //校验级差
						//查询这个用户这个产品的T日的所有赎回的和,如果等于持仓,级差不做失败处理
						String volSum = contractInfoMapper.querySumAppliCationVol(tempAccount);
						if(usableShares.compareTo(new BigDecimal(volSum)) == 0 ){
							tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
							tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
							return tempAccount;
						}
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00067));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00067);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
				
			}
			
			if(Const.INSTITUTION.equals(indiorinst)){
				//机构最低赎回份额
				String instMinRedeemVol=StringUtils.isBlanksTransZero(productInfoMap.get("PI_INST_MIN_REDEEM_VOL"));	
				//机构赎回级差
				String instRedeemDiff=StringUtils.isBlanksTransZero(productInfoMap.get("PI_INST_REDEEM_DIFF"));
				
				BigDecimal instMinRedeemVolBig=new BigDecimal(instMinRedeemVol);
				BigDecimal instRedeemDiffBig=new BigDecimal(instRedeemDiff);
				
				if(redeemShares.compareTo(instMinRedeemVolBig) == -1){
					tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00066));
					tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00066);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					return tempAccount;
				}
				
				BigDecimal DiffAmtBig=redeemShares.divideAndRemainder(instRedeemDiffBig)[1];
				//是否含费   不含费判断级差
				if(feeFlag.equals("0")){
					if(DiffAmtBig.compareTo(BigDecimal.ZERO)!=0){
						//查询这个用户这个产品的T日的所有赎回的和,如果等于持仓,级差不做失败处理
						String volSum = contractInfoMapper.querySumAppliCationVol(tempAccount);
						if(usableShares.compareTo(new BigDecimal(volSum)) == 0 ){
							tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
							tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
							return tempAccount;
						}
						tempAccount.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00068));
						tempAccount.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_T00068);
						tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
						return tempAccount;
					}
				}
				
			}
		}
		LOGGER.info(channelCode+"交易赎回逻辑结束。");
		return tempAccount;
	}
	
	
	

	
	
	/**
	 * @Title: getContractAndAccountInfo   
	 * @author: yindy 2020年3月19日 下午3:45:06
	 * @param: @param tempAccount
	 * @param: @return      
	 */
	@Override
	public Map<String, Object> getContractAndAccountInfo(Map<String, Object> tempAccount){
		Map<String,Object> retMap=Maps.newHashMap();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		String channelCode=StringUtils.getStringValue((String)tempAccount.get("CHANNELCODE"));
		String fundCode=StringUtils.getStringValue((String)tempAccount.get("FUNDCODE"));
		String taProductCode=StringUtils.getStringValue((String)tempAccount.get("TAPRODUCTCODE")); 
		productInfoMapper=(ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
		contractInfoMapper=(ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
		accountReqTmpService=(AccountReqTmpService) SpringUtils.getBean(AccountReqTmpService.class);
		//判断是否首次交易
		Map<String,Object> outMap=accountReqTmpService.getLocalAndTAAccountStatus(tempAccount);
		AccountStat accountStat=(AccountStat)outMap.get("LOCALACCOUNTSTATUS");
		if(accountStat==null){
			LOGGER.info((tempAccount == null ? "" : tempAccount.toString())+"基金账户不存在！");
			retMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00047));
			return retMap;
		}else{
			retMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
		}
		String openAccountStat=accountStat.getAsOpenAccountStat();
		String openChannelStat=accountStat.getAsOpenChannelStat();
		
		String TAAccountId=accountStat.getAsTaAccountId();
		LOGGER.info(channelCode+"基金交易账号为："+TAAccountId);
		String transactionAccountId=StringUtils.getStringValue((String)tempAccount.get("TRANSACTIONACCOUNTID"));
		LOGGER.info("投资人基金账号为："+transactionAccountId);
		//获取基金性质
		Map<String,Object> outRetMap=productInfoMapper.selectFundCodePropertyByFundCode(taProductCode);
		if(outRetMap==null||outRetMap.isEmpty()){
			retMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00049));
			return retMap;
		}
		String fundProperty=outRetMap.get("C_PROPERTY")==null?"0":MyMapUtils.getString(outRetMap,"C_PROPERTY");
		
		
		//获取合同信息
		retMap.put("TAACCOUNTID", TAAccountId);
		retMap.put("FUNDCODE", taProductCode);
		retMap.put("CHANNELCODE", channelCode);
		retMap.put("TRANSACTIONACCOUNT", transactionAccountId);
		contractInfoMapper = (ContractInfoMapper)SpringUtils.getBean(ContractInfoMapper.class);
		Map<String,Object> contractInfoMap=contractInfoMapper.selectContractInfo(retMap);
		String inContract="";
		String outContract="";
		if(contractInfoMap!=null&&!contractInfoMap.isEmpty()){
			 inContract = (String)contractInfoMap.get("INCONTRACT");//内部合同号
			 outContract = (String)contractInfoMap.get("OUTCONTRACT");//外部合同号
		}
		

		
		Map<String,Object> actInfoMap = new HashMap<String,Object>();
		actInfoMap.put("CHANNELCODE", channelCode);
		actInfoMap.put("FUNDCODE", fundCode);
		actInfoMap.put("TAACCOUNTID", TAAccountId);
		actInfoMap.put("L_CONTRACTSERIALNO", inContract);
		actInfoMap.put("C_TRUSTCONTRACTID", outContract);
		actInfoMap.put("BATCHNO", (String)tempAccount.get("BATCHNO"));
		
		if("0".equals(fundProperty)){//0 普通类   2 货币类
			LOGGER.info("根据"+(actInfoMap == null ? null : actInfoMap.toString())+"查询普通类产品的持仓");
			actInfoMap = productInfoMapper.selectCommonSharesByFundAcco(actInfoMap);
			retMap.put("F_REMAINSHARES", MapUtils.getString(actInfoMap, "F_REMAINSHARES", "0"));
			LOGGER.info("查询结果为："+(actInfoMap == null ? null : actInfoMap.toString()));
		}else if(fundProperty.equals("2")){
			LOGGER.info("根据"+(actInfoMap == null ? null : actInfoMap.toString())+"查询货币类产品的持仓");
			actInfoMap = productInfoMapper.selectActSharesByFundAcco(actInfoMap);
			retMap.put("F_REMAINSHARES", MapUtils.getString(actInfoMap, "F_REMAINSHARES", "0"));
			LOGGER.info("查询结果为："+(actInfoMap == null ? null : actInfoMap.toString()));
		}
		//如果产品为fof类可用份额走视图
		//查询产品类型 
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("CHANNELCODE", channelCode);
		queryMap.put("PRODUCTCODE", fundCode);
		queryMap.put("BATCHNO", "1");
		PProductInfo productInfo =  productInfoMapper.selectByProductCodeBatchNo(queryMap);
		String fundType = productInfo.getPiProductType(); //产品类型
		if(Const.FUND_TYPE_89.contains(fundType)){
			//查询可用份额  
			/***
			 * 20200512
			 *  现有如下问题待确认：
			 *  FOF类产品可赎回日期13号，
			 *  12号的行情为可申购赎回，
			 *  12号给的26文件有可用份额
			 */
			pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
			exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			Map<String, Object> selectMap = new HashMap<String, Object>();
			String transDate = MapUtils.getString(tempAccount, "TRANSDATE");
			PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
			String lastDate = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate), (String)channelInfo.getCiMarketCode());
			
			selectMap.put("CHANNELCODE", channelCode);
			selectMap.put("FUNDCODE", MapUtils.getObject(tempAccount, "FUNDCODE"));
			selectMap.put("TAACCOUNTID", TAAccountId);
			selectMap.put("TRANSDATE", transDate);
			selectMap.put("LASTDATE", lastDate);
			Map<String, Object> map = productInfoMapper.queryRelRemainShares(selectMap);
			retMap.put("F_REMAINSHARES", MapUtils.getString(map, "F_REMAINSHARES", "0"));
			LOGGER.info("查询结果为："+(actInfoMap == null ? null : actInfoMap.toString()));
		}

		
		retMap.put("OPENCHANNELSTAT", openChannelStat);
		retMap.put("OPENACCOUNTSTAT", openAccountStat);
		retMap.put("INCONTRACT", inContract);
		retMap.put("OUTCONTRACT", outContract);
		retMap.put("TAPRODUCTCODE", taProductCode);
		retMap.put("INDIVIDUALORINSTITUTION", accountStat.getAsIndividualOrInstitution());
		return retMap;
	}	
	
	@Override
	public BigDecimal selectCount(Map<String,String> map) {
		return exchangeReqCfmMapper.selectCount(map);
	}

	@Override
	public List<ExchangeReq> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return exchangeReqTmpMapper.findTnDataGrid(pageInfo,condition);
	}
	
	public static void main(String[] args) {
		
		//测试修改合同渠道号   ttrustcontractdetails
		// 3470891 3470844  3470847  3470850  3470888 
//		Map<String, Object> map = new HashMap<String, Object>();
//		CallS100510 s100510 = new CallS100510();
//		map.put("TRANSACTIONDATE", "20200120");
//		map.put("TRANSACTIONTIME", "173532"); 
//		map.put("INCONTRACT", "3472396");
//		map.put("CHANNELCODE", "TTTNETB3");
//		map.put("SUBCHANNELCODE", "TTTNETB3USD");
//		Map<String, Object> s100510map = s100510.callS100510(map);
//		System.out.println(s100510map.toString());
		
		//1	34943	156	000	[空回款账户]	SH0000076546  taccobank	
		/*Map<String, Object> map = new HashMap<String, Object>();
		CallS100512 s100512 = new CallS100512();
		map.put("CHANNELCODE", "TTTNETB3");
		map.put("TRANSACTIONDATE", "20200213");
		map.put("TRANSACTIONTIME", "173532");
		map.put("CONTRACTNO", "34943");
		map.put("CURRENCYTYPE", "156");
		Map<String, Object> s100512map = s100512.callS100512(map);
		System.out.println(s100512map.toString());*/
		
//		CallS100038 s100038 = new CallS100038();
//		Map<String, Object> s100038Map = s100038.callS100038(new HashMap<String, Object>());
//		System.out.println(s100038Map.toString());
		
	}
	/**
	 * @Description: 通过产品类型及ta交易账号查询该类型产品账户的持仓
	 * channelCode 渠道编号，taAccountId  ta基金交易账号，productType 产品类型
	 * @return Map<String, Object>
	 * @author wangchao 20200215
	 */
	@Override
	public Map<String, Object> getRemainshares(Map<String, Object> qryMap ) {
    	String fundType = MyMapUtils.getString(qryMap,"FUNDTYPE");
    	String taAccountId = MyMapUtils.getString(qryMap,"TAACCOUNTID");
    	String fundCode = MyMapUtils.getString(qryMap,"FUNDCODE"); 
    	String channelCode = MyMapUtils.getString(qryMap,"CHANNELCODE");
    	String transActionAccountId = MyMapUtils.getString(qryMap,"TRANSACTIONACCOUNTID");
    	productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
    	contractInfoMapper = (ContractInfoMapper)SpringUtils.getBean(ContractInfoMapper.class);
    	
    	Map<String,Object> actSharesMap = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(fundType) && (Const.FUND_TYPE_123.contains(fundType) || Const.FUND_TYPE_00.equals(fundType))){
			//查询持仓
			actSharesMap = productInfoMapper.selectPositionByFundAcco(qryMap);
		}else if (!StringUtils.isEmpty(fundType) && Const.FUND_TYPE_89.contains(fundType)){
			List<Map<String,Object>> contractList = 
					contractInfoMapper.selectContractInfoList(taAccountId,fundCode,null,channelCode,transActionAccountId,null,null);
			Map<String, Object> contractMap = new HashMap<String, Object>();
			if(contractList != null && contractList.size() > 0 ){
				contractMap = contractList.get(0);
				String taProductCode = MapUtils.getString(contractMap, "PRODUCTCODE");
				String inContract = MapUtils.getString(contractMap, "INCONTRACT");
				actSharesMap = productInfoMapper.queryRemainShares(taProductCode,inContract);
				return actSharesMap;
			} 
		}else{
			actSharesMap = productInfoMapper.selectNoCurrenyPosition(qryMap);
		}
		return actSharesMap;
	}

	/**
	 * @Description: 校验最低账面份额
	 * @return Map<String, Object>
	 * @author wangchao 20200215
	 */
	public  Map<String,Object>  checkAccountMinVol(Map<String,Object> qryMap){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,Object> Exchange024Map = new HashMap<String,Object>();
		Map<String,Object> qry024ByTaAccountIdMap = new HashMap<String,Object>();
		Map<String,Object> actSharesMap = new HashMap<String,Object>();
		Map<String,Object> get024ByTaAccountIdMap = new HashMap<String,Object>();
		List<Map<String, Object>> retList = Lists.newArrayList();
		boolean flag = true;
		qry024ByTaAccountIdMap.put("CHANNELCODE", MyMapUtils.getString(qryMap,"CHANNELCODE"));
		qry024ByTaAccountIdMap.put("TRANSDATE", MyMapUtils.getString(qryMap,"TRANSDATE"));
		//获取渠道当天基础校验成功的024交易所有的产品、账户
		List<Map<String, Object>> exchange024List = exchangeReqTmpMapper.getTransDate024Exchange(qryMap);
		if(null!=exchange024List && exchange024List.size()>0){			
			for(int i=0;i<exchange024List.size();i++){
				Exchange024Map=exchange024List.get(i);
				//个人或机构类型
				String indiOrInst = MyMapUtils.getString(Exchange024Map,"INDIORINST");
				//个人最低账面份额
				String indiMinVol = MyMapUtils.getString(Exchange024Map,"INDIMINVOL");
				//机构最低账面份额
				String instMinVol = MyMapUtils.getString(Exchange024Map,"INSTMINVOL");
				//获取该账户产品的持仓F_REMAINSHARES
				actSharesMap = getRemainshares(Exchange024Map);
				//账户可用份额
				String remainShares = MyMapUtils.getString(actSharesMap,"F_REMAINSHARES","0");
				qry024ByTaAccountIdMap.put("FUNDCODE", MyMapUtils.getString(Exchange024Map,"FUNDCODE"));
				qry024ByTaAccountIdMap.put("TAACCOUNTID", MyMapUtils.getString(Exchange024Map,"TAACCOUNTID"));
				//查询一个渠道下一个账户一个产品以及对应的TA产品当天的赎回信息 
				//注释：由于上述的exchange024List已经判空，所以get024ByTaAccountIdList必定有值
				List<Map<String, Object>> get024ByTaAccountIdList = exchangeReqTmpMapper.get024ExchangeByTaAccountId(qry024ByTaAccountIdMap);
				//查询查询一个渠道下一个账户一个产品所有基础校验成功的赎回总额
				String sumApplicationVol =  exchangeReqTmpMapper.getSumVol024Exchange(qry024ByTaAccountIdMap);
				//sumApplicationVol的值与该账户持仓一致，按全额赎回处理，赎回交易全部成功
				if(new BigDecimal(sumApplicationVol).compareTo(new BigDecimal(remainShares)) ==0){
					continue;
				}
				BigDecimal sumApplicationVolBig = new BigDecimal("0");
				for(int k=0;k<get024ByTaAccountIdList.size();k++){
					get024ByTaAccountIdMap = get024ByTaAccountIdList.get(k);
					//拿到排序后该笔赎回交易总额 例如：3笔交易 50W、30W、20W，第二笔的sumApplicationVol为80W,第三笔sumApplicationVol100W
					sumApplicationVolBig = sumApplicationVolBig.add(new BigDecimal(MapUtils.getString(get024ByTaAccountIdMap, "APPLICATIONVOL")));
					if(Const.INDIVIDUAL.equals(indiOrInst)){
						//个人最低账面份额判断  sumApplicationVol>remainShares-indiMinVol,可用份额不足，做失败处理
						if(sumApplicationVolBig.compareTo(new BigDecimal(remainShares).subtract(new BigDecimal(indiMinVol))) ==1){
							//做失败处理
							flag=false;
							sumApplicationVolBig=sumApplicationVolBig.subtract(new BigDecimal(MapUtils.getString(get024ByTaAccountIdMap, "APPLICATIONVOL")));
							get024ByTaAccountIdMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
							get024ByTaAccountIdMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
							get024ByTaAccountIdMap.put("ERRORDEC", ExceptionConStants.retMsg_T00015);
							retList.add(get024ByTaAccountIdMap);
						}
					}
					//机构最低账面份额判断 sumApplicationVol>remainShares-instMinVol,可用份额不足，做失败处理
					if(Const.INSTITUTION.equals(indiOrInst)){
						if(sumApplicationVolBig.compareTo(new BigDecimal(remainShares).subtract(new BigDecimal(instMinVol))) ==1){
							//做失败处理
							flag=false;
							get024ByTaAccountIdMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
							get024ByTaAccountIdMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
							get024ByTaAccountIdMap.put("ERRORDEC", ExceptionConStants.retMsg_T00015);
							retList.add(get024ByTaAccountIdMap);
						}
					}
					
				}
				//更新交易申请表数据
				if(retList != null && retList.size() > 0 ){
					exchangeReqTmpMapper.batchUpdateExchangeReqData(retList);
				}
			}
			if(!flag){
				retMap.put("ERRORDEC", ExceptionConStants.retMsg_T00015);
			}
		}
		retMap.put("024MINVOLFLAG", flag);
		return retMap;	
	}	
}