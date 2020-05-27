package com.sams.batchfile.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.sams.batchfile.service.ContractService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileInterfaceFieldService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.AccountReqCfmMapper;
import com.sams.custom.mapper.AccountStatMapper;
import com.sams.custom.mapper.ContractMapper;
import com.sams.custom.mapper.ExchangeReqCfmMapper;
import com.sams.custom.mapper.ExchangeReqTmpMapper;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
@Service
public class ContractServiceImpl implements ContractService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);
	
	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private ExchangeReqCfmMapper exchangeReqCfmMapper;
	
	@Autowired
	private AccountReqCfmMapper accountReqCfmMapper;
	
	@Autowired
	private FileInterfaceFieldService fileInterfaceFieldService;
	
	@Autowired
	private ExchangeReqTmpMapper exchangeReqTmpMapper;
	
	@Autowired
	private AccountStatMapper accountStatMapper;
	

	@Override
	public int deleteContractTmpDate(String channelCode,String businessDate) {
		return contractMapper.deleteContractTmpDate(channelCode, businessDate);
	}
	
	@Override
	public int deleteContractReqDate(String channelCode,String businessDate) {
		return contractMapper.deleteContractReqDate(channelCode, businessDate);
	}
	
	@Override
	public int deleteContractCfmDate(String channelCode,String businessDate) {
		return contractMapper.deleteContractCfmDate(channelCode, businessDate);
	}
	
	@Override
	public int selectContractTmpCount(String channelCode,String businessDate) {
		return contractMapper.selectContractTmpCount(channelCode, businessDate);
	}
	
	@Override
	public int selectContractReqCount(String channelCode,String businessDate) {
		return contractMapper.selectContractReqCount(channelCode, businessDate);
	}
	
	@Override
	public int selectContractCfmCount(String channelCode,String businessDate) {
		return contractMapper.selectContractCfmCount(channelCode, businessDate);
	}

	@Override
	public int insertByBatchContractTmp(List<Map<String, Object>> attachmentTables) {
		return contractMapper.insertByBatchContractTmp(attachmentTables);
	}


	@Override
	public int insertByBatchContractReq(List<Map<String, Object>> attachmentTables) {
		return contractMapper.insertByBatchContractReq(attachmentTables);
	}
	
	@Override
	public int insertByBatchContractCfm(List<Map<String, Object>> attachmentTables) {
		return contractMapper.insertByBatchContractCfm(attachmentTables);
	}
	
	/**
	 * 合同信息数据入库临时表
	 * @Title: saveDealTmpData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String,Object> saveDealTmpData(Map<String,Object> map){
		LOGGER.info("合同信息临时数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectContractTmpCount(channelCode,transDate);
		LOGGER.info("合同信息临时表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteContractTmpDate(channelCode,transDate);
		LOGGER.info("合同信息临时表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchContractTmp(list);
			LOGGER.info("保存合同信息数据入临时表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info("合同信息数据入临时表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("合同信息数据入临时表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00008);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00004);
			LOGGER.info("合同信息数据入申请表失败,原因为："+ExceptionConStants.retMsg_T00004);
		}
		LOGGER.info("合同信息临时数据入库结束处理");
		return retMap;
	}

	/**
	 * 合同信息数据入库申请表
	 * @Title: saveDealReqData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> saveDealReqData(Map<String, Object> map) {
		LOGGER.info("合同信息申请数据入库开始处理");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectContractReqCount(channelCode,transDate);
		LOGGER.info("合同信息申请表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteContractReqDate(channelCode,transDate);
		LOGGER.info("合同信息申请表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchContractReq(list);
			LOGGER.info("保存合同信息数据入库申请表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info("合同信息数据入库申请表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("合同信息数据入库申请表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00005);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00001);
			LOGGER.info("合同信息数据入申请表失败,原因为："+ExceptionConStants.retCode_T00001);
		}
		LOGGER.info("合同信息申请数据入库结束处理");
		return retMap;
	}
	
	/**
	 * 合同信息数据入库确认表
	 * @Title: saveDealCfmData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息 retValue 基础校验数据
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> saveDealCfmData(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		LOGGER.info("合同信息确认数据开始处理");
		String channelCode=(String)map.get("CHANNELCODE");
		String transDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectContractCfmCount(channelCode,transDate);
		LOGGER.info("合同信息确认表查询存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+selectCount);
		int deleteCount=deleteContractCfmDate(channelCode,transDate);
		LOGGER.info("合同信息确认表清理存在的数据，渠道为："+channelCode+",交易日期为："+transDate+",数据条数："+deleteCount);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchContractCfm(list);
			LOGGER.info("保存合同信息数据入库确认表,入库数据为："+insertCount+"条");
			if(list.size()==insertCount){
				LOGGER.info("合同信息数据入库确认表成功");
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				LOGGER.info("合同信息数据入库确认表失败,需入库条数为："+list.size()+"实际入库数据条数:"+insertCount);
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00035);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00036);
			LOGGER.info("合同信息数据入确认表失败,原因为："+ExceptionConStants.retCode_T00036);
		}
		LOGGER.info("合同信息确认数据入库结束处理");
		return retMap;
	}
	
	/**
	 * 合同信息数据基础规则校验
	 * @Title: checkContractApplyBasicData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> checkContractApplyBasicData(Map<String, Object> map) {
		Map<String, Object> retMap = Maps.newHashMap();
		boolean flag = true; //判断是否有检验失败的
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		List<Map<String, Object>> retList = Lists.newArrayList();
		try {
		
			exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
			fileInterfaceFieldService=(FileInterfaceFieldService) SpringUtils.getBean(FileInterfaceFieldService.class);
			contractMapper=(ContractMapper) SpringUtils.getBean(ContractMapper.class);
			LOGGER.info("合同信息基础校验开始处理");
			String transDate = (String)map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String channelCode = channelInfo.getCiChannelCode();
			
			List<Map<String, Object>> contractTmpList = contractMapper.selectContractTmp(channelCode, transDate);
			LOGGER.debug("合同信息查询临时表信息为："+contractTmpList.size()+"条");
			LOGGER.info("获取合同信息临时表数据成功,数据条数为："+contractTmpList.size()+"条");
			
			for (Map<String, Object> contractTmpMap : contractTmpList) {
				//在接口信息表中获得字段定义  
				Map<String,InterfaceColInfo> colInfoMap =fileInterfaceFieldService.selectColInfoMap(Const.FILE_TYPE_43, "000", channelInfo);
				LOGGER.debug("合同信息获取接口表信息为："+colInfoMap);
				//基础校验
				contractTmpMap = exchangeProcessorService.baseVerify(contractTmpMap,colInfoMap,null);
				
				//处理业务可处理状态 出错的将errordec复值
				if(!Strings.isNullOrEmpty(MapUtils.getString(contractTmpMap,"ERRORDEC"))){
					contractTmpMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					contractTmpMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
					flag = false;
				}else{
					contractTmpMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_01);
					contractTmpMap.put("RETURNCODE", ExceptionConStants.retCode_0000);
				}
				LOGGER.debug("合同信息基础校验信息为："+contractTmpMap);
				retList.add(contractTmpMap);
			}
			
			if(retList.size()!=contractTmpList.size()){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00043));
				LOGGER.info("合同信息基础校验数据与临时表记录数据行不一致,处理异常请检查");
				return retMap;
			}
			
			retMap.put("RETVALUE", retList);
			retMap.put("TRANSDATE",transDate);
			retMap.put("CHANNELCODE",channelCode);
			if(retList.size() > 0){
				retMap=saveDealReqData(retMap);
			}
			map.putAll(retMap);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				return retMap;
			}else{
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			if(!flag){
				map.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00015));
				map.put("retMsg", "电子合同申请"+ExceptionConStants.retMsg_A00015);
			}else{
				map.putAll(retMap);
			}
			LOGGER.info("电子合同基础校验结束！");
			return map;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("电子合同基础校验错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkContractApplyBasicData方法,电子合同基础校验异常,请联系管理员!");
			return retMap;
		}
	}
	/**
	 * 电子合同确认   
	 * @Title: saveContractCfmData   
	 * @author: yindy 2019年10月11日 上午10:22:11
	 * @param: @param map
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@Override
	public Map<String, Object> saveContractCfmData(Map<String, Object> map){
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			
			String businessDate=(String)map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String channelCode= channelInfo.getCiChannelCode();
			String salesPerson = channelInfo.getCiSalesPerson();
			
			//获得当前日期需要出确认数据的账户申请日期
			String marketCode = channelInfo.getCiMarketCode();
			exchangeProcessorService = (ExchangeProcessorService)SpringUtils.getBean(ExchangeProcessorService.class);
			String queryDate = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(businessDate),marketCode);
			//根据渠道号和日期查询申请表数据
			contractMapper=(ContractMapper) SpringUtils.getBean(ContractMapper.class);
			List<Map<String, Object>> contractReqList = contractMapper.selectContractReq(channelCode, queryDate,null);
			LOGGER.info(channelCode+"渠道,"+queryDate+"申请表插入确认表的确认数据的数量为："+(contractReqList == null ? null : contractReqList.toString()));
			for (Map<String, Object> contract : contractReqList) {
				String validFlag = (String)contract.get("VALIDFLAG");
				if(Const.BUSINESS_STUTAS_01.equals(validFlag)){
					contract.put("CHECKCONTRACTSTAT", Const.BUSINESS_STUTAS_01);
					contract.put("SECONDSTATUS", "1");
					contract.put("THIRDSTATUS", "1");
				}else{
					contract.put("CHECKCONTRACTSTAT", Const.BUSINESS_STUTAS_00);
					contract.put("SECONDSTATUS", "2");
					contract.put("THIRDSTATUS", "2");
				}
				contract.put("CHECKCONTRACTDETAIL", (String)contract.get("ERRORDEC"));
				contract.put("FUNDMANAGERCODE", salesPerson);
				contract.put("CUSTODIANCODE", salesPerson);
				contract.put("TRANSACTIONCFMDATE", businessDate);
				contract.put("BUSINESSDATE", businessDate);
			}
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			if(contractReqList.size() > 0){
				LOGGER.info(channelCode+"渠道"+businessDate+"电子合同插入确认表！");
				Map<String, Object> intomap = new HashMap<String, Object>();
				intomap.put("RETVALUE", contractReqList);
				intomap.put("CHANNELCODE", channelCode);
				intomap.put("TRANSDATE", businessDate);
				intomap.put("CHANNELINFO", channelInfo);
				retMap=saveDealCfmData(intomap);
			}
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				return retMap;
			}else{
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
				retMap.putAll(map);
			}
			LOGGER.info("电子合同插入确认表结束！");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("电子合同确认错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "saveContractCfmData方法,电子合同确认异常,请联系管理员!");
			return retMap;
		}
	}

	 
	/**
	 * 合同信息数据转换
	 * @Title: checkContractBusinessData   
	 * @author: wanshunbin 2019年4月16日 上午8:55:17
	 * @param: Map<String, Object> map  channelCode 渠道编号  transDate 交易日期  channelInfo 渠道基本信息
	 * @return: Map<String, Object>      
	 */
	@Override
	public Map<String, Object> checkContractBusinessData(Map<String, Object> map) {
		LOGGER.info("合同信息业务校验开始");
		Map<String,Object> retMap = Maps.newHashMap();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		try {
			String businessDate = (String) map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo) map.get("CHANNELINFO");
			String channelCode = channelInfo.getCiChannelCode(); // 渠道编码
			String verifyType = channelInfo.getCiEconVerifyType(); // 是否需要检测电子合同
			//20200426 电子合同校验规则修改 详见 ..\代销管理系统\02项目管理\6.业务流程vsd\电子合同校验流程图.png
			if(Const.BUSINESS_STUTAS_00.equals(verifyType)){
				//00 合法性校验，这里不需要做处理
				LOGGER.info("{}渠道设置的电子合同的校验规则为:合法性校验,这里不做逻辑处理",channelCode);
			}else if(Const.BUSINESS_STUTAS_02.equals(verifyType)){
				//02 开户联合校验 
				Map<String, Object> accountMap = checkAccountContract(channelCode,businessDate);
			}else if(Const.BUSINESS_STUTAS_03.equals(verifyType)){
				//03 交易联合校验  
				Map<String, Object> transMap = checkTransContract(channelCode,businessDate);
				if(!ExceptionConStants.retCode_0000.equals(MapUtils.getString(transMap, "retCode"))){
					retMap.put("retCode", MapUtils.getString(transMap, "retCode"));
					retMap.put("retMsg", ExceptionConStants.retMsg_T00122);
				}
			}else if(Const.BUSINESS_STUTAS_01.equals(verifyType)){
				//01 账户交易联合校验 
				Map<String, Object> accountMap = checkAccountContract(channelCode,businessDate);
				Map<String, Object> transMap = checkTransContract(channelCode,businessDate);
				if(!ExceptionConStants.retCode_0000.equals(MapUtils.getString(transMap, "retCode"))){
					retMap.put("retCode", MapUtils.getString(transMap, "retCode"));
					retMap.put("retMsg", ExceptionConStants.retMsg_T00122);
				}
			}
			
			retMap.putAll(map);
			LOGGER.info(channelCode+"渠道,"+businessDate+"电子合同业务校验结束!");
			return retMap;
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("合同联合校验错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkContractBusinessData方法,合同联合校验异常,请联系管理员!");
			return retMap;
		}
	}
	/**
	 * 交易联合校验   
	 * @Title: checkTransContract   
	 * @author: yindy 2020年4月27日 下午4:00:54
	 * @param: @param channelCode
	 * @param: @param businessDate
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkTransContract(String channelCode,
			String transDate) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		List<Map<String, Object>> updateList = new ArrayList<Map<String,Object>>();
		boolean returnFlag = true;
		
		//查询首次交易数据
		exchangeReqTmpMapper = (ExchangeReqTmpMapper) SpringUtils.getBean(ExchangeReqTmpMapper.class);
		List<Map<String, Object>> transReqList = exchangeReqTmpMapper.selectFirstTransInfo(channelCode, transDate);
		LOGGER.info("渠道{}查询的{}日,首次交易的申请数据数量为：{}",channelCode,transDate,(transReqList == null ? "0" : transReqList.size()));
		
		if(transReqList != null ){
			for (Map<String, Object> transMap : transReqList) {
				Map<String,Object> inputMap = Maps.newHashMap();
				inputMap.putAll(transMap);
				//查询合同表
				contractMapper = (ContractMapper) SpringUtils.getBean(ContractMapper.class);
				List<Map<String,Object>> contractList = contractMapper.queryContractInfo(inputMap);
				if(contractList != null && contractList.size() > 0){
					//多条合同,只要有一条合同成功，交易就是成功的。
					boolean flag = false; //判断开户有无成功的
					for(Map<String,Object> map : contractList){
						String retCode = MapUtils.getString(map,"VALIDFLAG");
						if(Const.BUSINESS_STUTAS_01.equals(retCode)){
							flag = true;
						}
					}
					if(!flag){
						LOGGER.info("根据合同数据校验校验交易数据,合同没有成功的,所以交易失败。");
						transMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						transMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
						transMap.put("ERRORDEC", ExceptionConStants.retMsg_T00122);
						updateList.add(transMap);
					}
					
				}else{
					LOGGER.info("{}条交易首次购买没有对应的合同数据,该交易做失败处理。",inputMap.toString());
					transMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
					transMap.put("RETURNCODE", ExceptionConStants.retCode_9999);
					transMap.put("ERRORDEC", ExceptionConStants.retMsg_T00122);
					updateList.add(transMap);
				}
			}
		}
		
		if(updateList != null && updateList.size() > 0 ){
			LOGGER.info(channelCode+"渠道,"+transDate+"更新交易申请表数据条数"+updateList.size());
			returnFlag = false;
			exchangeReqTmpMapper.batchUpdateExchangeReqData(updateList);
		}
		
		if(!returnFlag){
			LOGGER.info("渠道{},电子合同与交易联合校验,电子合同有失败的,所以交易有置失败的",channelCode);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
		}
		
		return retMap;
	}

	/**
	 * 账户验证电子合同  
	 * @Title: checkAccountContract   
	 * @author: yindy 2020年4月27日 下午3:22:07
	 * @param: @param channelCode
	 * @param: @param businessDate
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkAccountContract(String channelCode,
			String businessDate) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		List<Map<String, Object>> updateList = new ArrayList<Map<String,Object>>();
		boolean returnFlag = true;
		
		contractMapper = (ContractMapper) SpringUtils.getBean(ContractMapper.class);
		List<Map<String, Object>> contractReqList = contractMapper.selectContractReq(channelCode, businessDate, Const.BUSINESS_STUTAS_01);
		LOGGER.info("获取合同信息申请表数据成功,数据条数为："+(contractReqList == null ? null : contractReqList.size())+"条");
		if(contractReqList != null ){
			for (Map<String, Object> contractMap : contractReqList) {
				//查询开户信息
				Map<String,Object> inputMap = Maps.newHashMap();
				inputMap.putAll(contractMap);
				inputMap.put("BUSINESSCODE",Const.BUSINESS_001);
				LOGGER.info("根据合同数据查询账户数据参数为:"+(inputMap == null ? null : inputMap.toString()));
				
				accountStatMapper = (AccountStatMapper) SpringUtils.getBean(AccountStatMapper.class);
				List<Map<String,Object>> accountList = accountStatMapper.selectCheckAccountContract(inputMap);
				LOGGER.info("根据合同数据{}查询账户数据数量为:{}",(accountList == null ? "0" : accountList.size()));
				
				if(accountList != null && accountList.size() > 0){
					LOGGER.info("合同有对应的开户成功记录");
				}else{
					LOGGER.info("根据合同数据校验账户数据失败,原因为没有查到相关联的账户开户数据");
					contractMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00032));
					contractMap.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					contractMap.put("ERRORDEC",ExceptionConStants.retMsg_T00032);
					updateList.add(contractMap);
				}
			}
		}
		
		if(updateList != null && updateList.size() > 0 ){
			returnFlag = false;
			LOGGER.info(channelCode+"渠道,"+businessDate+"更新电子合同申请表数据条数"+updateList.size());
			contractMapper.batchUpdateContractReqData(updateList);
		}
		
		if(!returnFlag){
			LOGGER.info("渠道{},电子合同与账户联合校验,电子合同有失败的",channelCode);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
		}
		return retMap;
	}
	
	
	/**
	 * 查询电子合同申请表数据信息
	 * @Title: getElContractData   
	 * @author: wangchao 2019年9月24日 下午1:55:17
	 * @param: PageInfo  Map<String, Object> condition
	 * @return: Map<String, Object>      
	 */
	public List<Map<String,Object>> getElContractData(
			PageInfo pageInfo, Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return contractMapper.getElContractData(pageInfo, condition);
	}
}