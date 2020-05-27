package com.sams.batchfile.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.CheckFileUtil;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.common.ITask;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.AccountReqTmpService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.ProcessStepInfoService;
import com.sams.batchfile.service.ReadFileService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.ListUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.RandomizingID;
import com.sams.common.utils.Reflections;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.AccountInfoMapper;
import com.sams.custom.mapper.AccountReqCfmMapper;
import com.sams.custom.mapper.AccountReqTmpMapper;
import com.sams.custom.mapper.AccountStatMapper;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.mapper.ExchangeReqTmpMapper;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.mapper.ProductInfoMapper;
import com.sams.custom.model.AccountStat;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProcessStepInfo;
import com.sams.sys.service.SysDictService;
import com.sams.wsdl.impl.CallS100031;
import com.sams.wsdl.impl.CallS100040;
/**
 * @ClassName:  AccountReqTmpServiceImpl   
 * @author: yindy
 * @date:   2020年3月19日 下午3:26:20   
 *
 */
@Service
public class AccountReqTmpServiceImpl implements ITask<ResultBean<String>, Integer>, AccountReqTmpService {

	
	private static Logger LOGGER = LoggerFactory.getLogger(AccountReqTmpServiceImpl.class);
	
	@Autowired
	private  FundMarketProcessorService fundMarketProcessorService;
	
	@Autowired
	private ProcessStepInfoService processStepInfoService;
	
	@Autowired
	private AccountReqTmpMapper accountReqTmpMapper;
	
	@Autowired
	private  ReadFileService readFileService;
	
	@Autowired
	private FileDataService fileDataService;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private PchannelInfoMapper pchannelInfoMapper;
	
	@Autowired
	private AccountStatMapper accountStatMapper;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper;
	
	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	@Autowired
	private AccountReqCfmMapper accountReqCfmMapper;
	
	@Autowired
	private ExchangeReqTmpMapper exchangeReqTmpMapper;
	
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	@Autowired
	private SysDictService sysDictService;
	
	@Override
	public int deleteByChannelCodeBusinessDate(String artChannelCode,
			String artBusinessDate) {
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		return accountReqTmpMapper.deleteByChannelCodeBusinessDate(artChannelCode, artBusinessDate);
	}
	
	@Override
	public int selectByChannelCodeBusinessDateCount(String channelCode,
			String businessDate) {
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		return accountReqTmpMapper.selectByChannelCodeBusinessDateCount(channelCode, businessDate);
	}
	
	
	@Override
	public int insertByBatch(List<Map<String, Object>> attachmentTables) {
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		return accountReqTmpMapper.insertByBatch(attachmentTables);
	}
	
	/**
	 * 删除并插入用户临时表   
	 * @Title: dealTempData   
	 * @author: yindy 2019年6月19日 上午11:08:40
	 * @param: @param map channelcode 渠道号 transdate 交易日期 
	 * @param: @return     返回处理结果  
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String,Object> dealTempData(Map<String,Object> map){
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String businessDate=(String)map.get("TRANSDATE");
		LOGGER.info(channelCode+"渠道,"+businessDate+"删除并插入账户申请临时表！");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("RETVALUE");
		int selectCount=selectByChannelCodeBusinessDateCount(channelCode,businessDate);
		int deleteCount=deleteByChannelCodeBusinessDate(channelCode,businessDate);
		int insertCount=0;
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatch(list);
			if(list.size()==insertCount){
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00005);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00001);
		}
		return retMap;
	}

	/**
	 * 删除并插入账户申请表   
	 * @Title: dealReqData   
	 * @author: yindy 2019年6月19日 上午11:10:55
	 * @param: @param map channelcode 渠道号 transdate 交易日期 
	 * @param: @return      返回处理结果  
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> dealReqData(Map<String, Object> map) {
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode=(String)map.get("CHANNELCODE");
		String businessDate=(String)map.get("TRANSDATE");
		List<Map<String,Object>> list=(List<Map<String, Object>>) map.get("retValue");
		int selectCount=selectByChannelCodeBusinessDateCountReq(channelCode,businessDate);
		int deleteCount=deleteByChannelCodeBusinessDateReq(channelCode,businessDate);
		int insertCount=0;
		LOGGER.info(channelCode+"渠道,"+businessDate+"删除并插入账户申请表！");
		if(selectCount==deleteCount&&list.size()>0){
			insertCount=insertByBatchReq(list);
			if(list.size()==insertCount){
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00005);
			}
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00001);
		}
		return retMap;
	}

	@Override
	public int deleteByChannelCodeBusinessDateReq(String artChannelCode,
			String artBusinessDate) {
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		return accountReqTmpMapper.deleteByChannelCodeBusinessDateReq(artChannelCode, artBusinessDate);
	}

	@Override
	public int selectByChannelCodeBusinessDateCountReq(String channelCode,
			String businessDate) {
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		return accountReqTmpMapper.selectByChannelCodeBusinessDateCountReq(channelCode, businessDate);
	}

	@Override
	public int insertByBatchReq(List<Map<String, Object>> attachmentTables) {
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		return accountReqTmpMapper.insertByBatchReq(attachmentTables);
	}
	
	
	/**
	 * 解析文件   
	 * @Title: analyDawnLoadFile   
	 * @author: yindy 2019年10月10日 下午3:53:37
	 * @param: @param intomap
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> analyDawnLoadFile(Map<String,Object> intoMap){
		LOGGER.info("解析文件开始，传入参数为："+(intoMap == null ? null : intoMap.toString()));
		Map<String, Object> retMap = new HashMap<String, Object>();
		try{
			String transDate = (String)intoMap.get("TRANSDATE");
			String specialFile = (String)intoMap.get("SPECIALFILE");
			PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
			String channelCode  = channelInfo.getCiChannelCode();
			retMap = analyIndexFile(intoMap);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				return retMap;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("CHANNELINFO", channelInfo);
			map.put("TRANSDATE", transDate);
			map.put("CHANNELCODE", channelCode);
			map.put("SPECIALFILE", specialFile);
			LOGGER.info("解析正式文件的参数为:"+(map == null ? null : map.toString()));
			retMap = analyOfficiFile(map);
			return retMap;
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("解析文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "analyIndexFile方法,解析文件异常,请联系管理员!");
			return retMap;
		}
	}
	
	
	/**
	 * 读取和解析正式文件数据      
	 * @Title: analyOfficiFile   
	 * @author: yindy 2019年6月19日 上午11:12:42
	 * @param: @param intomap channelcode 渠道号 transdate 交易日期  channelinfo 渠道信息
	 * @param: @return   返回解析结果   
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> analyOfficiFile(Map<String,Object> intomap) {
		fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
		exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		String transDate = (String)intomap.get("TRANSDATE");
		String specialFile = (String)intomap.get("SPECIALFILE");
		PchannelInfo channelInfo = (PchannelInfo)intomap.get("CHANNELINFO");
		String channelCode  = channelInfo.getCiChannelCode();
		
		//查询需要解析的文件
		String fileTypes = channelInfo.getCiDealFile();
		LOGGER.info(channelCode+"渠道配置的需要解析的文件为："+fileTypes);
		if(!StringUtils.isEmpty(specialFile)){
			fileTypes = specialFile;
			LOGGER.info(channelCode+"单独处理的特殊文件类型为:"+specialFile);
		}
		List<String> fileTypeList = Arrays.asList(fileTypes.split(","));
		LOGGER.info(channelCode+"需要设置文件类型为:"+fileTypeList);
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		for(String fileType:fileTypeList){
			String filePath = fileDataService.getDictInfo().get(Const.FTP_LOCALDIR)+File.separator+Const.FTP_DOWNLOAD+File.separator;
			//读取正式文件
			String path = filePath+channelCode+File.separator+transDate;
			LOGGER.info(channelCode+"读取"+fileType+"正式文件的路径为："+path);
			readFileService = (ReadFileService) SpringUtils.getBean(ReadFileService.class);
			retMap = readFileService.readFile(path,fileType,transDate,channelInfo);
			
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}

			//删除原有数据
			intomap.put("CHANNELCODE", channelCode);
			intomap.put("BUSINESSTYPE", fileType);
			exchangeProcessorService.deleteExchangeData(intomap);
			
			
			if(retMap.get("datalist") != null && ((List)retMap.get("datalist")).size() > 0){
				Map<String, Object> insertMap = new HashMap<String, Object>();
				List<Map<String,Object>> data = (List<Map<String, Object>>) retMap.get("datalist");
				//补齐插入临时表字段
				data = addFieldValue(data,channelCode,transDate);
				insertMap.put("BUSINESSTYPE", fileType);
				insertMap.put("CHANNELCODE", channelCode);
				insertMap.put("TRANSDATE", transDate);
				insertMap.put("RETVALUE", data);
				//LOGGER.info("写入数据库的信息为"+insertMap.toString());
				//插入临时表
				LOGGER.info(channelCode+"渠道"+transDate+"解析完"+fileType+"数据,开始插入临时表！");
				retMap = exchangeProcessorService.insertExchangeData(insertMap);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
				
			}
		}
		
		retMap.put("CHANNELINFO", channelInfo);
		retMap.put("TRANSDATE", transDate);
		retMap.put("CHANNELCODE", channelCode);
		retMap.put("USERNAME", (String)intomap.get("USERNAME"));
		retMap.put("SPECIALFILE", specialFile);
		LOGGER.info(channelCode+"渠道,"+transDate+"解析正式文件结束！");
		return retMap;
	}

	private List<Map<String, Object>> addFieldValue(
			List<Map<String, Object>> data, String key, String date) {
		LOGGER.info("在解析完的数据中插入时间"+date+"和渠道编码"+key);
		for (Map<String, Object> map : data) {
			map.put("CHANNELCODE", key);
			map.put("BUSINESSDATE", date);
		}
		return data;
	}

	/**
	 * 读取并解析索引文件     
	 * @Title: analyIndexFile   
	 * @author: yindy 2019年6月19日 上午11:15:10
	 * @param: @param intoMap channelcode 渠道号 transdate 交易日期  channelinfo 渠道信息
	 * @param: @return     返回处理结果 
	 */
	@Override
	public Map<String, Object> analyIndexFile(Map<String, Object> intoMap) {
		Map<String,Object> retMap = new HashMap<String, Object>();
		String transDate = MapUtils.getString(intoMap, "TRANSDATE", "");
		PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
		String channelCode  = channelInfo.getCiChannelCode();
		
		fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
		Object path = fileDataService.getDictInfo().get(Const.FTP_LOCALDIR);
		if(path == null){
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0013));
			retMap.put("retMsg", "查询数据字典"+ExceptionConStants.retMsg_RF0013);
		}
		
		//读取索引文件  读取文件
		String filePath = path+File.separator+Const.FTP_DOWNLOAD+File.separator+channelCode+ File.separator + transDate ;
		LOGGER.info(channelCode+"读取索引文件的路径为:"+filePath);
		readFileService = (ReadFileService) SpringUtils.getBean(ReadFileService.class);
		retMap = readFileService.readIndexFile(filePath, transDate,channelInfo);
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
		LOGGER.info(channelCode+"校验索引文件结果:"+(retMap == null ? null : retMap.toString()));
		
		retMap.put("CHANNELINFO", channelInfo);
		retMap.put("TRANSDATE", transDate);
		retMap.put("CHANNELCODE", channelCode);
		retMap.put("USERNAME", (String)intoMap.get("USERNAME"));
		retMap.put("SPECIALFILE", "");
		LOGGER.info(channelCode+"渠道,"+transDate+"解析索引文件完成！");
		return retMap;
	}

	/**
	 * 用户数据基础规则校验  
	 * @Title: checkAccoBasicData   
	 * @author: yindy 2019年6月19日 上午11:16:34
	 * @param: @param map channelcode 渠道号 transdate 交易日期  channelinfo 渠道信息
	 * @param: @return 返回校验结果 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> checkAccoBasicData(Map<String, Object> map){
		boolean flag = true; //判断是否有检验失败的
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		try {
			
			Object date = map.get("TRANSDATE");
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String channelCode = channelInfo.getCiChannelCode();
			String	csdcver = channelInfo.getCiCsdcVer();
			
			retMap.put("TRANSDATE", date);
			retMap.put("CHANNELINFO", channelInfo);
			
			List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
			
			//查询临时表用户数据
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("TRANSDATE", date);
			queryMap.put("CHANNELCODE", channelCode);
			accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
			List<Map<String, Object>> listAccount = accountReqTmpMapper.selectAccoTempByDate(queryMap);
			LOGGER.info("根据渠道编号:"+channelCode+",交易日期:"+date+"查询的用户信息的数量为："+(listAccount == null ? null : listAccount.size()));
			
			//校验有无重复数据      现在做法所有字段分组
			retMap = checkAccountSameData(queryMap);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
			
			fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
			String businessCodes = (String)fileDataService.getDictInfo().get("BUSINESSCODES");
			LOGGER.info(channelCode+"配置的业务编码为:"+businessCodes);
			
			for (Map<String, Object> tempAccount : listAccount) {
				//获得交易类型  
				String businessCode = (String)tempAccount.get("BUSINESSCODE");
				LOGGER.info("此条用户申请的业务编码为:"+businessCode);
				
				if(StringUtils.isEmpty(businessCode.trim()) || !businessCodes.contains(businessCode)){
					LOGGER.info(channelCode+"账户申请中有业务编码为空或者不正确的编码的记录,请修改");
					//yindaiyong 20200110  不停下继续执行
					tempAccount.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					tempAccount.put("ERRORDEC", ExceptionConStants.retMsg_A00019);
					//去除空格
					Map<String, Object> tempMap = new HashMap<String, Object>();
					for (String key : tempAccount.keySet()) {
						Object value = tempAccount.get(key);
						tempMap.put(key, (value == null ? "" : value.toString().trim()));
					}
					retList.add(tempMap);
					flag = false;
					continue;
				}
				
				//获得需要查询的接口字段的键值
				Map<String,Object> query = Maps.newHashMap();
				query.put("ICINTERFACECODE",Strings.padStart(Const.BUSINESS_001+businessCode+csdcver, 8, '0'));
				interfaceColInfoMapper = (InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
				List<InterfaceColInfo> colList = interfaceColInfoMapper.selectByInterfaceCodeList(query);
				if(CollectionUtils.isEmpty(colList)){
					LOGGER.info(channelCode+"根据"+query+"查询的接口字段为空！");
					return ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00017);
				}
				
				Map<String,InterfaceColInfo> colInfoMap = FileDataUtil.exchangeListToMap(colList);
				
				//对用户信息进行基础校验
				Map<String,Object> valInfoMap=Maps.newHashMap();
				valInfoMap.put("TABLENAME", "D_ACCOUNT_REQ_TMP");
				valInfoMap.put("TRANSDATE", date);
				valInfoMap.put("CHANNELCODE", channelCode);
				valInfoMap.put("APPSHEETSERIALNO", (String)tempAccount.get("APPSHEETSERIALNO"));
				exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
				tempAccount = exchangeProcessorService.baseVerify(tempAccount,colInfoMap,valInfoMap);
				//基础校验有异常
				if(ExceptionConStants.retCode_9999.equals(tempAccount.get("retCode"))) {return tempAccount;}
	
				//特殊校验处理
				sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
				String noCheckPhoneChannel = sysDictService.findDictVo(Const.NO_CHECK_PHONE_CHANNEL);
				tempAccount = CheckFileUtil.specialCheckHandel(tempAccount,channelInfo,noCheckPhoneChannel);
				
				//判断基础校验是否有错误
				if(!Strings.isNullOrEmpty((String)tempAccount.get("ERRORDEC"))){
					tempAccount.put("VALIDFLAG",Const.BUSINESS_STUTAS_00);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_9999);
					flag = false;
				}else{
					tempAccount.put("VALIDFLAG",Const.BUSINESS_STUTAS_01);
					tempAccount.put("RETURNCODE", ExceptionConStants.retCode_0000);
				}
				
				String taSeriNo = new RandomizingID("", "yyyyMMddHHmmss", 4, false).genNewId();
				LOGGER.info(channelCode+"随机生成的TA确认流水号为："+taSeriNo);
				tempAccount.put("TASERIALNO", taSeriNo);
				tempAccount.put("TATRANSSTATUS", Const.BUSINESS_STUTAS_00);
				tempAccount.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_00);
				
				retList.add(tempAccount);
				
			}
			//插入
			Map<String,Object> insertMap =Maps.newHashMap();
			insertMap.put("CHANNELCODE", channelCode);
			insertMap.put("TRANSDATE", date);
			insertMap.put("retValue", retList);
			
			//插入申请表
			if(retList.size() > 0 ){ 
				retMap = dealReqData(insertMap);
			}else{
				deleteByChannelCodeBusinessDateReq(channelCode,(String)date);
			}
			
			
			if(!flag){
				retMap.put("retCode",ExceptionConStants.retCode_A00015);
				retMap.put("retMsg", "账户申请"+ExceptionConStants.retMsg_A00015);
			}else{
				retMap.put("TRANSDATE", date);
				retMap.put("CHANNELINFO", channelInfo);
				retMap.put("CHANNELCODE", channelCode);
			}
			LOGGER.info(channelCode+"渠道,"+date+"账户基础校验结束！");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("账户基础校验错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "checkAccoBasicData方法,账户基础校验异常,请联系管理员!");
			return retMap;
		}
	} 
	

	/**
	 * 校验有无重复数据   
	 * @Title: checkAccountSameData   
	 * @author: yindy 2019年6月19日 上午11:24:50
	 * @param: @param queryMap channelcode 渠道号 transdate 日期
	 * @return: Map<String,Object>      校验结果
	 */
	private Map<String, Object> checkAccountSameData(Map<String, Object> queryMap) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		accountReqTmpMapper = (AccountReqTmpMapper)SpringUtils.getBean(AccountReqTmpMapper.class);
		Integer count = accountReqTmpMapper.checkAccountSameData(queryMap);
		if(count != null){
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00016));
			retMap.put("retMsg", "根据"+queryMap+"查询的"+ExceptionConStants.retMsg_A00016);
			return retMap;
		}
		return ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
	}

	/**
	 * 用户申请数据业务校验  
	 * @Title: checkAccountApplyBusinessData   
	 * @author: yindy 2019年4月26日 上午10:45:11
	 * @param: @param map channelcode 渠道号  transdate 日期 channelinfo 渠道信息
	 * @return: Map<String,Object>  返回校验结果    
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> checkAccoBusinessData(Map<String, Object> map){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		try {
		
			accountReqTmpMapper = (AccountReqTmpMapper)SpringUtils.getBean(AccountReqTmpMapper.class);
			map.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
			
			//查询基础校验成功的数据
			List<Map<String, Object>> accountList = accountReqTmpMapper.selectAccoInfoByDate(map);
			LOGGER.info("查询的账户信息数量为："+(accountList == null ? 0:accountList.size()));
			for (Map<String, Object> account : accountList) {
				//基础校验没有错误的数据
				String businessType = MapUtils.getString(account, "BUSINESSCODE", "");
				LOGGER.info("用户申请的业务代码为"+businessType);
				switch (businessType) {
					case Const.BUSINESS_001:
						account = checkBusiness001(account);
						break;
					case Const.BUSINESS_003:
						account = checkBusiness003(account);
						break;
					case Const.BUSINESS_002:
						account = checkBusiness002(account);
						break;
					case Const.BUSINESS_009:
						account = checkBusiness009(account);
						break;
				}
				//把数据类型为BigDecial的值进行转换
				account = FileDataUtil.changeBigDecialValue(account);
				retList.add(account);
			}
			//批量跟新
			if(retList.size() > 0){
				accountReqTmpMapper.batchUpdateAccountReqData(retList);
			}
			returnMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			map.putAll(returnMap);
			LOGGER.info("账户业务校验完成,返回的数据："+(returnMap == null ? null : returnMap.toString()));
			return map;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("账户业务校验错误"+error);
			returnMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			returnMap.put("retMsg", "checkAccoBusinessData方法,账户业务校验异常,请联系管理员!");
			return returnMap;
		}
	}
	
	
	/**
	 * 发送校验完成的用户申请数据到TA系统      
	 * @Title: sendAccoDataToTA   
	 * @author: yindy 2019年6月19日 上午11:29:26
	 * @param: @param map channelcode 渠道号  transdate 日期 channelinfo 渠道信息
	 * @param: @return  返回处理结果    
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> sendAccoDataToTA(
			Map<String, Object> map) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			
			PchannelInfo channelInfo = (PchannelInfo)map.get("CHANNELINFO");
			String channelCode = channelInfo.getCiChannelCode();
			String transDate = (String)map.get("TRANSDATE");
			
			//查询正确的申请发送TA
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("CHANNELCODE", channelCode);
			query.put("TRANSDATE", transDate);
			query.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
			query.put("TATRANSSTATUS", Const.BUSINESS_STUTAS_00);
			
			accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
			accountReqCfmMapper = (AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
			List<Map<String, Object>> accountList = accountReqTmpMapper.selectAccoInfoByDate(query);
			LOGGER.info(channelCode+"通过"+query+"查询的可发送TA的数据数量为:"+(accountList == null ? 0:accountList.size()));
			
			boolean flag = false; //判断TA有无返回失败的
			for (Map<String, Object> account : accountList) {
				//把业务校验已经成功的过滤掉
				if(account.get("ERRORDEC") != null && !Strings.isNullOrEmpty((String)account.get("ERRORDEC"))){
					continue;
				}
				//发送TA系统
				String businessCode = (String)account.get("BUSINESSCODE");
				switch(businessCode) {
					case Const.BUSINESS_001:
						retMap = sendBusiness001(account);
						
						if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
							//调用接口抛出异常直接返回
							return retMap;
						}else{
							if((boolean) retMap.get("FLAG")){
								flag = (boolean) retMap.get("FLAG");
							}
						}
						break;
						
					case Const.BUSINESS_003:
						retMap = sendBusiness003(account);
						
						if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {
							return retMap;
						}else{
							if((boolean) retMap.get("FLAG")){
								flag = (boolean) retMap.get("FLAG");
							}
						}
						break;
						
					case Const.BUSINESS_002:
						
						sendBusiness002(account);
						break;
						
					case Const.BUSINESS_009:
						
						sendBusiness002(account);
						break;
						
					default:
						LOGGER.info(account+"该条账户申请的业务编码"+businessCode+"不正确！");
						break;
				}
			}
			if(flag){
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
				retMap.put("retMsg", "发送TA核心有返回失败数据，请核对。");
			}else{
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			}
			
			retMap.put("CHANNELCODE", channelCode);
			retMap.put("TRANSDATE", transDate);
			retMap.put("CHANNELINFO", channelInfo);
			LOGGER.info(channelCode+"渠道"+transDate+"账户发送TA结束！");
			return retMap;
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("账户发送TA错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "sendAccoDataToTA方法,账户发送TA异常,请联系管理员!");
			return retMap;
		}
	}
	

	/**
	 * 销户调用接口   
	 * @Title: sendBusiness002   
	 * @author: yindy 2019年5月14日 下午2:56:19
	 * @param: @param account 用户信息
	 * @return: Map<String,Object>   返回处理结果   
	 * @throws
	 */
	private Map<String, Object> sendBusiness002(Map<String, Object> account) {
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		accountStatMapper = (AccountStatMapper)SpringUtils.getBean(AccountStatMapper.class);
		accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
		
		account.put("OPENACCOUNTSTAT", Const.BUSINESS_STUTAS_00);
		account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
		LOGGER.info("销户修改账户状态表！");
		accountStatMapper.updateOpenStat(account);
		
		account.put("RETCODE", ExceptionConStants.retCode_0000);
		account.put("ERRORDEC", "销户成功！");
		retList.add(account);
		LOGGER.info("销户修改账户申请表！");
		accountReqTmpMapper.batchUpdateAccountReqData(retList);
		
		return account;
	}

	/**
	 * 账户查询数据插入确认表  
	 * @Title: queryAndInsertCfm   
	 * @author: yindy 2019年6月19日 下午1:11:30
	 * @param: @param intoMap channelcode 渠道号  transdate 日期 channelinfo 渠道信息
	 * @param: @return   返回处理结果   
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public  Map<String, Object> queryAndInsertCfm(Map<String, Object> intoMap) {
		Map<String,Object> retMap=Maps.newHashMap();
		try {
		
			PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
			String transDate = (String)intoMap.get("TRANSDATE");
			String channelCode = channelInfo.getCiChannelCode();
			//删除当天数据根据日期和渠道编号
			accountReqCfmMapper = (AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
			accountReqTmpMapper = (AccountReqTmpMapper) SpringUtils.getBean(AccountReqTmpMapper.class);
			exchangeProcessorService = (ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
	
			
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("CHANNELCODE", channelCode);
			query.put("TRANSDATE", transDate);
			LOGGER.info(channelCode+"渠道，删除"+transDate+"账户确认数据！");
			accountReqCfmMapper.deleteCfmByCodeAndDate(query);
			
			//获得当前日期需要出确认数据的账户申请日期
			String marketCode = channelInfo.getCiMarketCode();
			String queryDate = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate),marketCode);
			//根据渠道号和日期查询申请表数据
			query.put("TRANSDATE", queryDate);
			List<Map<String, Object>> list = accountReqTmpMapper.selectAccoInfoByDate(query);
			LOGGER.info("通过"+query+"查询的需要插入账户确认表的数据数量为"+ (list == null ? null : list.size()));
			retMap = ListUtils.checkListIsEmpty(list, ExceptionConStants.retCode_9999);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				LOGGER.info("根据"+query+"查询申请表没有数据");
			}
			
			//补齐其他字段
			list = addFiledToCfm(channelInfo,list,transDate);
			
			//插入确认表 
			int insertCount = 0;
			if(!CollectionUtils.isEmpty(list)){ 
				insertCount = accountReqCfmMapper.insertByBatchCfm(list);
			}
			if(list.size()==insertCount){
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			}else{
				retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00007);
				retMap.put("retMsg", ExceptionConStants.retMsg_A00007+",查询出的数量为"+list.size()+"插入的数量为"+insertCount);
			}
			retMap.put("CHANNELINFO", channelInfo);
			retMap.put("TRANSDATE", transDate);
			retMap.put("CHANNELCODE", channelCode);
			LOGGER.info(channelCode+"渠道"+transDate+"把账户申请数据插入账户确认表完成");
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("账户确认错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "queryAndInsertCfm方法,账户确认异常,请联系管理员!");
			return retMap;
		}
	}

	/**
	 * 补齐确认表缺少字段    
	 * @Title: addFiledToCfm   
	 * @author: yindy 2019年6月19日 下午1:16:33
	 * @param: @param channelInfo 渠道信息
	 * @param: @param list 数据集
	 * @param: @param transDate 交易日期
	 * @return: List<Map<String,Object>>      返回处理好之后的结果集
	 * @throws
	 */
	private List<Map<String, Object>> addFiledToCfm(PchannelInfo channelInfo,
			List<Map<String, Object>> list, String transDate) {
		for (Map<String, Object> map : list) {
			map.put("TRANSACTIONCFMDATE", transDate);
			map.put("TRANSDATE", transDate);
			map.put("FROMTAFLAG", "0");
		}
		return list;
	}

	/**
	 * 发送客户资料到客户修改资料接口  
	 * @Title: sendBusiness003   
	 * @author: yindy 2019年4月29日 上午8:44:14
	 * @param: @param account 用户数据
	 * @return: Map<String,Object>       返回修改资料结果
	 * @throws
	 */
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	private Map<String, Object> sendBusiness003(Map<String, Object> account) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		retMap.put("FLAG", false); // 判断有没有发TA失败的
		LOGGER.info("修改账户资料的信息:"+account);
		//调用S100031接口   调用S100031 传入FLAG=1 表示修改客户资料
		account.put("FLAG", "1");
		CallS100031 s100031 = new CallS100031();
		try {
			Map<String, Object> resultMap = s100031.callS100031(account);
			LOGGER.info("调用S100031接口修改资料的返回结果为："+resultMap);
			
			Object retCode = resultMap.get("retCode");
			Object retMsg = resultMap.get("retMsg");
			
			if(ExceptionConStants.retCode_0000.equals(retCode)){
				retMsg = "修改资料成功！";
				account.put("TAACCOUNTID", MapUtils.getString(resultMap, "TAACCOUNTID", MapUtils.getString(account, "TAACCOUNTID")));
			}else{
				retMap.put("FLAG", true);
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			}
			account.put("RETURNCODE", retCode);
			account.put("ERRORDEC", retMsg);
			
			account.put("TATRANSTATUS", Const.BUSINESS_STUTAS_01);
			account.put("TATRANSTIME",DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
			accountReqTmpMapper = (AccountReqTmpMapper)SpringUtils.getBean(AccountReqTmpMapper.class);
			accountReqTmpMapper.updateAccoReq(account);
			//修改账户信息表
			insertOrUpdateAccountInfo(Const.BUSINESS_003,account);
			return retMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("调用S100031接口错误"+error);
			retMap.put("retCode", ExceptionConStants.retCode_9999);
			retMap.put("retMsg", "sendBusiness003方法,调用S100031修改资料接口异常,请联系管理员！");
			return retMap;
		}
	}

	/**
	 * @param account 
	 * @param businessCode 
	 * @Title: updateAccountInfo   
	 * @author: yindy 2020年3月26日 上午9:20:07
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	private void insertOrUpdateAccountInfo(String businessCode, Map<String, Object> account) {
		//001 插入  003修改
		accountInfoMapper = (AccountInfoMapper) SpringUtils.getBean(AccountInfoMapper.class);
		if(Const.BUSINESS_003.equals(businessCode)){
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			list.add(account);
			accountInfoMapper.updateAccountInfo(list);
		}else if(Const.BUSINESS_001.equals(businessCode)){
			accountInfoMapper.insertAccountInfo(account);
		}
	}

	/**
	 * 发送开户数据到TA
	 * @Title: sendBusiness001   
	 * @author: yindy 2019年4月29日 上午8:43:21
	 * @param: @param account 用户数据
	 * @return: Map<String,Object>    返回调用接口之后的结果   
	 */
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	private Map<String, Object> sendBusiness001(Map<String, Object> account) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		// 判断有没有发TA失败的
		retMap.put("FLAG", false); 
		LOGGER.info("开户的数据为:"+account);
		
		//查询渠道客户经理编号
		pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		String managerCode  = pchannelInfoMapper.selectManagerCode((String)account.get("CHANNELCODE"));
		account.put("MANAGERNO", managerCode);
		LOGGER.info(account.get("CHANNELCODE")+"渠道客户经理编号为:"+managerCode);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		accountStatMapper = (AccountStatMapper)SpringUtils.getBean(AccountStatMapper.class);
		
		try {
			//既开通账户  又开通网上渠道
			if(Const.BUSINESS_STUTAS_00.equals(account.get("OPENCHANNELSTAT"))){ 
				CallS100031 s100031 = new CallS100031();
				// 调用S100031开户接口
				resultMap = s100031.callS100031(account);
				LOGGER.info("调用S100031接口新开通用户的返回结果为:"+resultMap);
				
				if(ExceptionConStants.retCode_0000.equals(resultMap.get("retCode"))){
					account.put("TAACCOUNTID", resultMap.get("TAACCOUNTID"));
					LOGGER.info("调用S100031开户成功后,插入状态表的用户信息为:"+(account == null ? null : account.toString()));
					//插入用户状态表 
					insertAccountStatus(account);
					
					if("该证件号码已经注册，请直接登录！".equals(resultMap.get("retMsg"))){
						account.put("FLAG", "1");
						Map<String,Object> returnMap = s100031.callS100031(account);
						LOGGER.info("线下开过户的,线上再次开户,调用修改客户资料接口,主要修改证件有效期的结果为:"+returnMap);
					}
					
					CallS100040 s100040 = new CallS100040();
					// 调用S100040开通网上交易渠道
					resultMap  = s100040.callS100040(account);
					LOGGER.info("调用S100040接口开通网上交易渠道的返回结果为："+resultMap);
					if(ExceptionConStants.retCode_0000.equals(resultMap.get("retCode")) || 
							Const.OPEN_CHANNEL_RET_CODE.equals(resultMap.get("retCode"))){
						resultMap.put("retMsg", "网上开户成功！");
						resultMap.put("retCode",ExceptionConStants.retCode_0000);
						account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
					}else{
						account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_00);
					}
					//跟新账户状态表
					LOGGER.info("调用S100040开通网上交易成功后,插入状态表的用户信息为:"+account);
					accountStatMapper.updateOpenStat(account);
				}else{
					retMap.put("FLAG", true);
					account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				}
			}else{ //只开通网上渠道
				CallS100040 s100040 = new CallS100040();
				// 调用S100040开通网上交易渠道
				resultMap = s100040.callS100040(account);
				LOGGER.info("调用S100040接口开通网上交易渠道的返回结果为："+resultMap);
				
				if(ExceptionConStants.retCode_0000.equals(resultMap.get("retCode")) || Const.OPEN_CHANNEL_RET_CODE.equals(resultMap.get("retCode"))){
					resultMap.put("retMsg", "网上开户成功！");
					resultMap.put("retCode",ExceptionConStants.retCode_0000);
					account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
					
					account.put("FLAG", "1");
					CallS100031 s100031 = new CallS100031();
					Map<String , Object> returnMap = s100031.callS100031(account);
					LOGGER.info("线下开过户的,线上再次开户,调用修改客户资料接口,主要修改证件有效期的结果为:"+returnMap);
				}else{
					account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_00);
					account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				}
				
				LOGGER.info("调用S100040开通网上交易成功后,插入状态表的用户信息为:"+account);
				accountStatMapper.updateOpenStat(account);
			}
			
			Object retCode = resultMap.get("retCode");
			Object retMsg = resultMap.get("retMsg");
			
			if(!ExceptionConStants.retCode_0000.equals(retCode)){
				retMap.put("FLAG", true);
			}
			if(ObjectUtils.isEmpty(retMsg)){
				retMsg = "网上开户成功!";
			}
			account.put("RETURNCODE", retCode);
			account.put("ERRORDEC", retMsg);
			account.put("TATRANSTATUS", Const.BUSINESS_STUTAS_01);
			account.put("TATRANSTIME",DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
			accountReqTmpMapper = (AccountReqTmpMapper)SpringUtils.getBean(AccountReqTmpMapper.class);
			accountReqTmpMapper.updateAccoReq(account);
			LOGGER.info("开户接口调用结束");
			return retMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("调用接口错误"+error);
			retMap.put("retCode", ExceptionConStants.retCode_9999);
			retMap.put("retMsg", "sendBusiness001方法,S100031开户/S100040开通网上交易异常,请联系管理员！");
			return retMap;
		}
		
	}


	/**
	 * 校验开户申请的数据 
	 * @Title: checkBusiness01   
	 * @author: yindy 2019年4月25日 下午1:31:26
	 * @param: @param tempAccount 用户数据
 	 * @return: Map<String,Object> 返回校验完成的数据      
	 * @throws
	 */
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	private Map<String, Object> checkBusiness001(Map<String, Object> account) {
		Map<String, Object> accountStatus = getLocalAndTAAccountStatus(account);
		Object localStatus = accountStatus.get("LOCALACCOUNTSTATUS");
		Object TAaccountId = accountStatus.get("TAACCOUNTID");
		Object trustAgencyNo = accountStatus.get("TRUSTAGENCYNO");
		if(localStatus == null){//本地没有该渠道该用户
			account = checkTAaccountStatus(account,TAaccountId,trustAgencyNo);
		}else{ //本地有该渠道该用户
			account = checkLocalAccountStatus(account,localStatus);
		}
		return account;
	}

	/**
	 * 本地有用户记录
	 * @Title: checkLocalAccountStatus   
	 * @author: yindy 2019年5月6日 下午5:15:31
	 * @param: @param account 用户信息
	 * @param: @param localStatus 本地用户状态信息
	 * @return: Map<String,Object>     返回校验完的用户信息  
	 */
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	private Map<String, Object> checkLocalAccountStatus(
			Map<String, Object> account, Object localStatus) {
		AccountStat localAccStatus = (AccountStat)localStatus;
		LOGGER.info("校验本地用户,本地用户信息为"+localAccStatus);
		if(Const.BUSINESS_STUTAS_01.equals(localAccStatus.getAsOpenAccountStat())){
			//重复开户  
			account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			account.put("ERRORDEC", ExceptionConStants.retMsg_A00001);
			account.put("TAACCOUNTID", localAccStatus.getAsTaAccountId());
			account.put("RETURNCODE", ExceptionConStants.retCode_0000);
			account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
			LOGGER.info(localAccStatus.getAsTaAccountId()+"该用户重复开户。");
		}else{
			
			account.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
			account.put("RETURNCODE", ExceptionConStants.retCode_0000);
			account.put("ERRORDEC", ExceptionConStants.retMsg_A00006);
			account.put("TAACCOUNTID", localAccStatus.getAsTaAccountId());
			
			//修改状态表用户状态
			account.put("OPENACCOUNTSTAT", Const.BUSINESS_STUTAS_01);
			account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
			accountStatMapper = (AccountStatMapper) SpringUtils.getBean(AccountStatMapper.class);
			LOGGER.info("修改用户状态为开户状态！");
			accountStatMapper.updateOpenStat(account);
		}
		LOGGER.info("校验本地用户状态结束！");
		return account;
	}

	/**
	 * 本地没有用户记录  
	 * @Title: checkTAaccountStatus   
	 * @author: yindy 2019年5月6日 下午5:16:00
	 * @param: @param account 用户信息
	 * @param: @param TAaccountId 基金账号
	 * @param: @param trustAgencyNo 开通的渠道号
	 * @return: Map<String,Object>      返回校验之后的用户信息 
	 */
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	private Map<String, Object> checkTAaccountStatus(
			Map<String, Object> account, Object TAaccountId,
			Object trustAgencyNo) {
		if(TAaccountId == null){ //TA没有该用户
			LOGGER.info(account+"该用户为新开户，并且需要开通网上交易");
			account.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
		}else{
			account.put("TAACCOUNTID", TAaccountId);
			if(trustAgencyNo == null){ //没有开通网上交易  新开户  只开通网上交易
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				//插入状态表的状态为未开通渠道
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_00);
				//插入本地状态表
				insertAccountStatus(account);
				//插入申请表为01 区分只发开通网上交易接口
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
			}else{
				//重复开户  
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				account.put("RETURNCODE", ExceptionConStants.retCode_0000);
				account.put("ERRORDEC", ExceptionConStants.retMsg_A00001);
				LOGGER.info(TAaccountId+"该用户重复开户。");
				//插入本地状态表
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
				LOGGER.info("TA系统有"+TAaccountId+"用户,代销系统没有，插入代销系统用户状态表！");
				insertAccountStatus(account);
			}
		}
		LOGGER.info("校验申请开户数据是否在TA存在结束！");
		return account;
	}

	/**
	 * 插入本地状态表   
	 * @Title: insertAccountStatus   
	 * @author: yindy 2019年4月28日 上午8:48:59
	 * @param: @param account 用户信息
	 */
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	private void insertAccountStatus(Map<String, Object> account) {
		String channelCode = (String)account.get("CHANNELCODE");
		String individualOrInstitution = (String)account.get("INDIVIDUALORINSTITUTION");
		String certificatetype = (String)account.get("CERTIFICATETYPE");
		String certificateno = (String)account.get("CERTIFICATENO");
		String TAaccountId = (String)account.get("TAACCOUNTID");
		String openchannelstat = (String)account.get("OPENCHANNELSTAT");
		String transactionAccountId = (String)account.get("TRANSACTIONACCOUNTID");
		String investorName = (String)account.get("INVESTORNAME");
		
		AccountStat accountStatus = new AccountStat();
		accountStatus.setAsChannelCode(channelCode);
		accountStatus.setAsIndividualOrInstitution(individualOrInstitution);
		accountStatus.setAsCertificateType(certificatetype);
		accountStatus.setAsCertificateNo(certificateno);
		accountStatus.setAsTaAccountId(TAaccountId);
		accountStatus.setAsOpenAccountStat(Const.BUSINESS_STUTAS_01);
		accountStatus.setAsOpenChannelStat(openchannelstat);
		accountStatus.setAsFirstTradeFlag(Const.RESOURCE_TATUS_ON);
		accountStatus.setAsTransactionAccountId(transactionAccountId);
		accountStatus.setAsInvestorName(investorName); //姓名
		LOGGER.info("插入用户状态表的数据为"+accountStatus.toString());
		accountStatMapper = (AccountStatMapper) SpringUtils.getBean(AccountStatMapper.class);
		//查询是否已存在该用户,不存在插入
		AccountStat accountStat = accountStatMapper.selectAccountStatus(account);
		if(accountStat == null ){
			accountStatMapper.insertSelective(accountStatus);
			//插入账户信息表
			insertOrUpdateAccountInfo(Const.BUSINESS_001, account);
		}
	}

	/**
	 * 获得本地和TA的用户状态以及是否开通对应渠道状态   
	 * @Title: getLocalAndTAAccountStatus   
	 * @author: yindy 2019年4月28日 上午8:28:56
	 * @param: @param account 用户信息 
	 * @return: Map<String,Object>    LOCALACCOUNTSTATUS 本地状态信息    TAACCOUNTID 理财账号  TRUSTAGENCYNO网上交易渠道号
	 * @throws
	 */
	@Override
	public  Map<String, Object> getLocalAndTAAccountStatus(
			Map<String, Object> account) {
		Map<String, Object> retStatus = new HashMap<String, Object>();
		accountStatMapper = (AccountStatMapper) SpringUtils.getBean(AccountStatMapper.class);
		//根据四要素查询本地用户状态
		AccountStat accountStat = accountStatMapper.selectAccountStatus(account);
		LOGGER.info("查询的本地该渠道"+account.get("CHANNELCODE")+"的用户状态为："+accountStat);
		//查询TA表，看该用户有没有开通账号
		String taAccountid = accountStatMapper.queryTAaccountInfo(account);
		LOGGER.info("通过用户的证件类型"+MapUtils.getString(account, "CERTIFICATETYPE", "")+
				MapUtils.getString(account, "CERTIFICATENO", "")+"证件号"+
				MapUtils.getString(account, "INDIVIDUALORINSTITUTION", "")+"客户类型"+
				"查询TA的用户的基金账号"+taAccountid);
		//查询是否开通网上交易
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("CHANNELCODE", account.get("CHANNELCODE"));
		query.put("TAACCOUNTID", taAccountid);
		String trustAgencyNo = accountStatMapper.selectTAttrustcustagency(query);
		retStatus.put("LOCALACCOUNTSTATUS", accountStat);
		retStatus.put("TAACCOUNTID", taAccountid);
		retStatus.put("TRUSTAGENCYNO", trustAgencyNo);
		return retStatus;
	}

	/**
	 * 校验销户申请的数据 
	 * @Title: checkBusiness01   
	 * @author: yindy 2019年4月25日 下午1:31:26
	 * @param: @param tempAccount
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkBusiness002(Map<String, Object> account){
		account.put("TATRANSFLAG", Const.BUSINESS_STUTAS_00); //不发TA
		Map<String, Object> accountStatus = getLocalAndTAAccountStatus(account);
		Object localStatus = accountStatus.get("LOCALACCOUNTSTATUS");
		
		if(localStatus == null){
			LOGGER.info("销户本地不存在该用户记录！");
			account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
			account.put("ERRORDEC", ExceptionConStants.retMsg_A00002);
			account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_00);
			account.put("RETURNCODE", ExceptionConStants.retCode_9999);
			
			
		}else{
			AccountStat accountStat = (AccountStat)localStatus;
			account.put("TAACCOUNTID", accountStat.getAsTaAccountId());
			if(Const.BUSINESS_STUTAS_01.equals(accountStat.getAsOpenAccountStat())){
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				//account.put("ERRORDEC", accountStat.getAsTaAccountId()+ExceptionConStants.retMsg_A00005);
				Map<String, Object> inputMap = new HashMap<String, Object>();
				inputMap.put("CHANNELCODE", account.get("CHANNELCODE"));
				inputMap.put("TAACCOUNTID", accountStat.getAsTaAccountId());
				inputMap.put("TRANSACTIONACCOUNTID", accountStat.getAsTransactionAccountId());
				
				 //查询持仓  该持仓查询的非货币类产品的持仓,如果该客户只购买了其他类型的产品,销户会成功,合同会置失效,这个是不对的
				productInfoMapper = (ProductInfoMapper) SpringUtils.getBean(ProductInfoMapper.class);
				Map<String,Object> actSharesMap = productInfoMapper.selectPositionByFundAcco(inputMap);
				LOGGER.info("根据"+(inputMap == null? null: inputMap.toString()+"查询的持仓数据为"+(actSharesMap == null ? null : actSharesMap.toString())));
				if(actSharesMap != null){
					String availablevol = MapUtils.getString(actSharesMap, "F_REMAINSHARES", "0");//可用份额
					String unConfirmBal = MapUtils.getString(actSharesMap, "F_UNCONFIRMBAL", "0");//未确认份额
					
					if((new BigDecimal(availablevol).compareTo(new BigDecimal("0"))>0)||(new BigDecimal(unConfirmBal).compareTo(new BigDecimal("0"))>0)){//还有份额或者未确认份额，不能进行注销
						account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
						account.put("RETURNCODE", ExceptionConStants.retCode_9999);
						account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
						account.put("ERRORDEC", accountStat.getAsTaAccountId()+ExceptionConStants.retMsg_A00004);
						LOGGER.info(accountStat.getAsTaAccountId()+"中还有份额"+availablevol+",或者未确认份额"+unConfirmBal+",请赎回后，在进行销户操作。");
						return account;
					}
				}
				
				contractInfoMapper = (ContractInfoMapper) SpringUtils.getBean(ContractInfoMapper.class);
				//修改合同状态
				contractInfoMapper.upContractFlagForCloseAcco((AccountStat)localStatus);
				
				account.put("RETURNCODE", ExceptionConStants.retCode_0000);
				
			}else{
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
				account.put("ERRORDEC", accountStat.getAsTaAccountId()+ExceptionConStants.retMsg_A00003);
				account.put("RETURNCODE", ExceptionConStants.retCode_9999);
				LOGGER.info(accountStat.getAsTaAccountId()+ExceptionConStants.retMsg_A00003);
			}
		}
		return account;
	}

	/**
	 * 校验修改资料申请的数据 
	 * @Title: checkBusiness03   
	 * @author: yindy 2019年4月25日 下午1:31:26
	 * @param: @param tempAccount 用户信息
	 * @return: Map<String,Object>   返回校验结果   
	 * @throws
	 */
	private Map<String, Object> checkBusiness003(Map<String, Object> account) {
		accountReqTmpMapper = (AccountReqTmpMapper)SpringUtils.getBean(AccountReqTmpMapper.class);
		Map<String, Object> accountStatus = getLocalAndTAAccountStatus(account);
		Object localStatus = accountStatus.get("LOCALACCOUNTSTATUS");
		if(localStatus == null){
			LOGGER.info("账户状态表没有数据");
			//查询交易申请表,如果该日期有开户数据,就不做失败处理
			int count = accountReqTmpMapper.selectAccountReqOpenCount(account);
			if(count > 0){
				LOGGER.info("账户申请表有开户数据");
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
			}else{
				LOGGER.info("账户申请表没有开户数据");
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_00);
				account.put("RETURNCODE", ExceptionConStants.retCode_9999);
				account.put("ERRORDEC", ExceptionConStants.retMsg_A00002);
				LOGGER.info(localStatus+ExceptionConStants.retMsg_A00002);
			}
			
		}else{
			AccountStat accountStat = (AccountStat)localStatus;
			account.put("TAACCOUNTID", accountStat.getAsTaAccountId());
			if(Const.BUSINESS_STUTAS_01.equals(accountStat.getAsOpenAccountStat())){
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_01);
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
			}else{
				account.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				account.put("OPENCHANNELSTAT", Const.BUSINESS_STUTAS_01);
				account.put("RETURNCODE", ExceptionConStants.retCode_9999);
				account.put("ERRORDEC", ExceptionConStants.retMsg_A00003+",不能进行资料修改！");
				LOGGER.info(accountStat.getAsTaAccountId()+ExceptionConStants.retMsg_A00003);
			}
		}
		return account;
	}

	/**
	 * 校验销户申请的数据 
	 * @Title: checkBusiness09   
	 * @author: yindy 2019年4月25日 下午1:31:26
	 * @param: @param tempAccount
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> checkBusiness009(Map<String, Object> account){
		return checkBusiness002(account);
	}

	/**
	 * 执行线程方法   
	 * @Title: execute   
	 * @author: yindy 2019年6月18日 上午9:58:30
	 * @param: @param channelCode
	 * @param: @param intoMap
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public ResultBean<String> execute(String channelCode, Map<String, Object> intoMap) {
		intoMap.put("CHANNELCODE", channelCode);
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		intoMap.put("CHANNELINFO", channelInfo);
		//获得当前处理的日期
		String transDate = (String)intoMap.get("TRANSDATE"); //交易日期
		String specialFile = (String)intoMap.get("SPECIALFILE"); //特殊处理文件
		String errorStep = (String)intoMap.get("ERRORSTEP");  //错误步骤
		ResultBean<String> resultBean = ResultBean.newInstance();
		//校验是否有发送TA数据
		FTPUtils ftp = new FTPUtils();
		Map<String, Object> checkMap = ftp.checkAccountReqAndExchangeReq(transDate, channelCode);
		if(!ExceptionConStants.retCode_0000.equals(checkMap.get("retCode")) 
				&& StringUtils.isEmpty(specialFile)
				&& StringUtils.isEmpty(errorStep)){
			LOGGER.info(channelCode+"渠道,已有数据发送TA,禁止重复操作！");
			resultBean.setMsg(channelCode+"渠道,"+MapUtils.getString(checkMap, "retMsg", "已有数据发送TA,禁止重复操作！"));
		}else{
			//判断该渠道该日期是否是工作日，不是工作日不作处理
			boolean flag = exchangeProcessorService.checkDateIsWork(transDate,channelCode);
			if(flag){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("CHANNELINFO", channelInfo);
				map.putAll(intoMap);
				accountProcessor(map);
				resultBean.setData(channelCode);
			}else{
				LOGGER.info(channelCode+"渠道,"+transDate+"为非工作日。");
				resultBean.setMsg(channelCode+"渠道,"+transDate+"为非工作日。");
			}
		}
		
        return resultBean;
	}
	
	/**
	 * 用户的数据处理流程   
	 * @Title: accountProcessor   
	 * @author: yindy 2019年5月10日 下午3:41:20
	 * @param: @param intoMap      
	 * @return: void      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public void accountProcessor(Map<String, Object> intoMap){
		LOGGER.info("日终数据处理流程开始,当前时间为:"+DateUtils.getDate());
		String userName = (String)intoMap.get("USERNAME");
		String specialFile = (String)intoMap.get("SPECIALFILE"); //特殊处理文件
		String errorStep = (String)intoMap.get("ERRORSTEP");  //错误步骤
		Integer operType = (Integer)intoMap.get("OPERTYPE"); //操作步骤   从做该步 强制通过 
		String channelCode = (String)intoMap.get("CHANNELCODE");
		LOGGER.info(userName+"操作特殊单独处理文件:"+specialFile);
		Long processorStart = DateUtils.getOracleSysDate().getTime();
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<ProcessStepInfo> specialList = new ArrayList<ProcessStepInfo>();//存储单独处理文件步骤]
		
		
		try {
			List<ProcessStepInfo> list=(List<ProcessStepInfo>)processStepInfoService.selectByProcessStepInfo(Const.ACCOUNT_DATA_STEP,errorStep,operType,null).get("LISTREQ");
			if(!StringUtils.isEmpty(specialFile)){
				specialList = (List<ProcessStepInfo>)processStepInfoService.selectByProcessStepInfo(Const.ACCOUNT_DATA_STEP,errorStep,operType,specialFile).get("LISTREQ");
			}
			
			//特殊处理步骤
			Object[] inputObj = new Object[1];
			inputObj[0] = intoMap;
			for(ProcessStepInfo stepInfo:list){
				String packageName = stepInfo.getPsiPackageName();
				String className = stepInfo.getPsiClassName();
				String methodName = stepInfo.getPsiMethodName();
				String processName = stepInfo.getPsiProcessName();
				LOGGER.info("该步操作为："+processName);
				LOGGER.info("输入参数为:"+JSONObject.toJSONString(inputObj[0]));
				Class<?> clazz=Class.forName(packageName+"."+className);
//				Long start = DateUtils.getOracleSysDate().getTime();
                long start = System.currentTimeMillis();
                Object obj = new Object();
				
				if(specialList != null && specialList.size() > 0 && list.size() != specialList.size()){
					if(specialList.contains(stepInfo)){
						obj = Reflections.invokeMethodByName(clazz, methodName, inputObj);
						LOGGER.info("特殊处理文件步骤返回结果。。。。");
					}else{
						Map<String, Object> tmpMap = new HashMap<String, Object>();
						tmpMap.putAll(intoMap);
						tmpMap.put("retCode", "0000");
						tmpMap.put("retMsg", "特殊处理跳过步骤。。。");
						obj = tmpMap;
					}
				}else{
					obj = Reflections.invokeMethodByName(clazz, methodName, inputObj);
				}
				
				LOGGER.info("输出参数为:"+JSONObject.toJSONString(obj));
				LOGGER.warn("------------------------"+processName+"步骤消耗的的时间为:"+(System.currentTimeMillis()-start)+"ms--------------------");
				retMap.put("PROCESSSTEPINFO", stepInfo);
				retMap.put("PROCESSSTARTTIME", DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
				retMap.put("BRANCHCODE", Const.ACCOUNT_DATA_STEP);
				retMap.put("TRADEDATE", intoMap.get("TRANSDATE"));
				retMap.put("USERNAME", userName);
				retMap.putAll((Map<String, Object>)obj);
				LOGGER.info(processName+"处理结果："+obj);
				fundMarketProcessorService = (FundMarketProcessorService) SpringUtils.getBean(FundMarketProcessorService.class);
				fundMarketProcessorService.insertProcessLog(retMap, channelCode);
				if(!ExceptionConStants.retCode_0000.equals(((Map<String, Object>)obj).get("retCode"))) {
					break;
				}
				inputObj[0] = obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("日终数据处理流程出错"+error);
		}
		LOGGER.info("日终数据处理流程完成,当前时间为:"+DateUtils.getDate());
		LOGGER.warn("整个日终数据处理 耗时为:"+(DateUtils.getOracleSysDate().getTime()-processorStart)+"ms");
	}

	@Override
	public BigDecimal selectCount(Map<String,String> map) {
		return accountReqCfmMapper.selectCount(map);
	}

	@Override
	public List<Object> queryFailedInfo(PageInfo pageInfo,Map<String, Object> condition) {
        /*pageInfo.setOrder(PageInfo.DESC);
        pageInfo.setSort("TRANSDATE");*/
	    PageHelperUtils.startPage(pageInfo);
		List<Object> retList= new ArrayList<Object>();
		String type = (String)condition.get("type");
		String transDate = (String)condition.get("transDate");
		if(!StringUtils.isEmpty(transDate)){
			transDate = transDate.replaceAll("-", "");
		}
		//order by TRANSDATE desc
		condition.put("transDate", transDate);
		if("0".equals(type)){ //账户
			retList = accountReqTmpMapper.queryFailedInfo(pageInfo,condition);
			LOGGER.info("通过"+(condition == null ? null : condition.toString())+"查询账户数据");
		}else{ //交易
			retList = exchangeReqTmpMapper.queryFailedInfo(pageInfo,condition);
			LOGGER.info("通过"+(condition == null ? null : condition.toString())+"查询交易数据");
		}
		return retList;
	}
	
	
	@Override
	public List<Object> queryFalseFileData(PageInfo pageInfo,Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		List<Object> retList= new ArrayList<Object>();
		String type = (String)condition.get("type");
		String transDate = (String)condition.get("transDate");
		if(!StringUtils.isEmpty(transDate)){
			transDate = transDate.replaceAll("-", "");
		}
		condition.put("transDate", transDate);
		if("0".equals(type)){ //账户
			retList = accountReqTmpMapper.queryAccountFailedInfo(pageInfo,condition);
		}else{ //交易 returnCode=9999
			if(null ==condition.get("returnCode") || "".equals((String)condition.get("returnCode"))){
				condition.put("returnCode", "9999");
			}
			retList = exchangeReqTmpMapper.queryTransFailedInfo(pageInfo,condition);
		}
		return retList;
	}
	
	/**
	 * 交易申请数据修改   
	 * @Title: updateTransData   
	 * @author: 王超 2020年4月23日 上午10:08:40
	 * @param: @param Map<String,Object> id sysdate 
	 * @param: @return     返回处理结果  int
	 */
	
	@Override
	public int updateTransData(List<Map<String,Object>> list){
		return exchangeReqTmpMapper.updateTransData(list); 
	}

	@Override
	public int updateAccountData(List<Map<String,Object>> list) {
		return accountReqTmpMapper.updateAccountData(list);
	}

}
