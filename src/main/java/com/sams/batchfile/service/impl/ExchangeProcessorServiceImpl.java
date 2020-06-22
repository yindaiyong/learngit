package com.sams.batchfile.service.impl;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sams.custom.mapper.*;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.CheckFileUtil;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.AccountReqTmpService;
import com.sams.batchfile.service.ContractService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.ExchangeReqTmpService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.ProcessStepInfoService;
import com.sams.batchfile.service.ResidentTaxInformService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.Reflections;
import com.sams.common.web.PageInfo;
import com.sams.custom.model.ChannelInfo;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.DealInfoEdit;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.ParticipationProfit;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProcessStepInfo;
import com.sams.sys.service.SysDictService;
/**
 * @ClassName:  ExchangeProcessorServiceImpl   
 * @author: yindy
 * @date:   2020年3月19日 下午3:35:39   
 *
 */
@Service
public class ExchangeProcessorServiceImpl implements ExchangeProcessorService {

	
	private static Logger LOGGER = LoggerFactory.getLogger(ExchangeProcessorServiceImpl.class);
	@Autowired
	private AccountReqTmpService accountReqTmpService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ExchangeReqTmpService exchangeReqTmpService;
	
	@Autowired
	private ResidentTaxInformService residentTaxInformService;
	
	@Autowired
	private ResidentTaxInformMapper residentTaxInformMapper;
	
	@Autowired
	private FileDataService fileDataService;
	
	@Autowired
	private AccountReqCfmMapper accountReqCfmMapper;
	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private PchannelInfoMapper pchannelInfoMapper;
	
	@Autowired
	private CloseDateMapper closeDateMapper;
	
	@Autowired
	private ChannelInfoMapper channelInfoMapper;
	
	@Autowired
	private ContractMapper contractMapper;
	
	@Autowired
	private FundAccountReconCfmMapper fundAccountReconCfmMapper;
	
	@Autowired
	private FundDividendCfmMapper fundDividendCfmMapper;
	
	@Autowired
	private ProcessStepInfoService processStepInfoService;
	
	@Autowired
	private DayEndTransDateMapper dayEndTransDateMapper;
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private ExchangeReqCfmMapper exchangeReqCfmMapper;
	
	@Autowired
	private DealInfoEditMapper  dealInfoEditMapper;
	
	@Autowired
	private FundMarketProcessorService fundMarketProcessorService;
	
	@Autowired
	private ProductOpenDayMapper productOpenDayMapper;
	
	@Autowired
	private FundMarketCfmMapper fundMarketCfmMapper;
	
	FTPUtils ftp = new FTPUtils();
	
	
	@Override
	public Map<String, Object> insertExchangeData(Map<String, Object> map) {
		String businessType=(String)map.get("BUSINESSTYPE");
		Map<String,Object> retMap=Maps.newHashMap();
		switch (businessType) {
		case Const.FILE_TYPE_01:
			retMap=accountReqTmpService.dealTempData(map);
			break;
		case Const.FILE_TYPE_03:
			retMap=exchangeReqTmpService.saveDealTmpData(map);
			break;
		case Const.FILE_TYPE_R1:
			retMap=residentTaxInformService.saveDealTmpData(map);
			break;
		case Const.FILE_TYPE_43:
			retMap=contractService.saveDealTmpData(map);
			break;	
		default:
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00009);
			break;
		}
		return retMap;
	}
	
	
	@Override
	public Map<String, Object> deleteExchangeData(Map<String, Object> map) {
		String businessType=(String)map.get("BUSINESSTYPE");
		Map<String,Object> retMap=Maps.newHashMap();
		String channelCode =(String)map.get("CHANNELCODE");
		String businessDate=(String)map.get("TRANSDATE");
		switch (businessType) {
		case Const.FILE_TYPE_01:
			accountReqTmpService.deleteByChannelCodeBusinessDate(channelCode,businessDate);
			break;
		case Const.FILE_TYPE_03:
			exchangeReqTmpService.deleteTransTmpDate(channelCode, businessDate);
			break;
		case Const.FILE_TYPE_R1:
			residentTaxInformService.deleteTresidentTaxInformTmpDate(channelCode, businessDate);
			break;
		case Const.FILE_TYPE_43:
			contractService.deleteContractTmpDate(channelCode, businessDate);
			break;	
		default:
			ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00009);
			break;
		}
		return retMap;
	}

	@Override
	public Map<String, Object> insertExchangeDataReqTable(
			Map<String, Object> map) {
		String businessType=(String)map.get("businessType");
		Map<String,Object> retMap=Maps.newHashMap();
		switch (businessType) {
		case Const.FILE_TYPE_01:
			retMap=accountReqTmpService.dealReqData(map);
			break;
		case Const.FILE_TYPE_03:
			retMap=exchangeReqTmpService.saveDealReqData(map);
			break;
		case Const.FILE_TYPE_R1:
			retMap=residentTaxInformService.saveDealReqData(map);
			break;
		case Const.FILE_TYPE_44:
			retMap=contractService.saveDealReqData(map);
			break;	
		default:
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_T00009);
			break;
		}
		return retMap;
	}
	/**
	 * 基本规则的具体校验 
	 * @Title: checkAndTrans   
	 * @author: yindy 2019年4月25日 下午1:32:03
	 * @param: @param tempAccount
	 * @param: @param colInfoMap
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@Override
	public  Map<String, Object> baseVerify(Map<String, Object> tempData, Map<String, InterfaceColInfo> colInfoMap,Map<String, Object> infoData){
		Map<String, Object> retMap = new HashMap<String, Object>();
		StringBuilder message = new StringBuilder("");
		StringBuilder errorCode = new StringBuilder("");
		if(CollectionUtils.isEmpty(colInfoMap)) {return tempData;}
		for (String key : tempData.keySet()) {
			boolean falg=false; //记录该字段是否有错误
			StringBuilder msg = new StringBuilder(); //记录该字段错误信息
			String value = MapUtils.getString(tempData, key, "");
			if(Const.NO_CHECK_FIELD.contains(key)){
				continue ;
			}
			Integer length = Integer.valueOf(colInfoMap.get(key).getIcColLength()); //长度
			String ruleOnOff = colInfoMap.get(key).getIcRuleOnOff(); //规则开关
			String reqOnOff = colInfoMap.get(key).getIcRequOnOff(); //必填开关
			String dict = colInfoMap.get(key).getIcColDict(); //字典值
			String format = colInfoMap.get(key).getIcColRule(); //规则正则
			String require = colInfoMap.get(key).getIcColRequ(); //是否必填
			String colDecimal = colInfoMap.get(key).getIcColDecimal(); //小数位
			String colName = colInfoMap.get(key).getIcColDesc(); //字段描述
			String type = colInfoMap.get(key).getIcColType(); //字段类型
			//校验长度 
			try {
				retMap = CheckFileUtil.checkFieldLength(value, key, length);
			} catch (Exception e) {
				String error = FileDataUtil.getErrorMsg(e);
				LOGGER.error("基础校验出错"+error);
				retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
				retMap.put("retMsg", "baseVerify方法,校验字段长度抛出异常,请联系管理员!");
				return retMap;
			}
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
				errorCode.append(retMap.get("retCode")).append(";");
				message.append(retMap.get("RETVALUE")).append(";");
				msg.append(retMap.get("RETVALUE")).append(";");
				falg=true;
			}
			value = (value == null ? value : value.trim());
			if(Const.BUSINESS_STUTAS_01.equals(ruleOnOff)){
				//校验字段类型
				retMap = CheckFileUtil.checkFieldType(value, key, type);
				if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
					errorCode.append(retMap.get("retCode")).append(";");
					message.append(retMap.get("RETVALUE")).append(";");
					msg.append(retMap.get("RETVALUE")).append(";");
					falg=true;
				}
				
				//校验字典
				retMap = CheckFileUtil.checkDict(value, key, dict);
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))) {
					errorCode.append(retMap.get("retCode")).append(";");
					message.append(retMap.get("RETVALUE")).append(";");
					msg.append(retMap.get("RETVALUE")).append(";");
					falg=true;
				}
				//校验格式
				retMap = CheckFileUtil.checkFieldformat(value,key, format);
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))){
					errorCode.append(retMap.get("retCode")).append(";");
					message.append(retMap.get("RETVALUE")).append(";");
					msg.append(retMap.get("RETVALUE")).append(";");
					falg=true;
				}
			}
			if(Const.BUSINESS_STUTAS_01.equals(reqOnOff)){
				
				//校验必填
				retMap = CheckFileUtil.checkFieldRequire(value, key,require);
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode"))){
					errorCode.append(retMap.get("retCode")).append(";");
					message.append(retMap.get("RETVALUE")).append(";");
					msg.append(retMap.get("RETVALUE")).append(";");
					falg=true;
				}
			}
			if(!"0".equals(colDecimal)){
				tempData.put(key, CheckFileUtil.transNumberValue(value, colDecimal,length));
			}else{
				tempData.put(key, value);
			}
			//校验有错误，插入补录表
			if(falg&&infoData!=null){
				Map<String,Object> inputMap=Maps.newHashMap();
				inputMap.putAll(infoData);
				Map<String,String> colMap=Const.mapColToMap;
				String colKey=(String)colMap.get(key);
				inputMap.put("COLCODE", colKey==null?key:colKey);
				inputMap.put("COLVALUE", value);
				inputMap.put("COLNAME", colName);
				inputMap.put("COLLENGTH", String.valueOf(length));
				inputMap.put("VALIDFLAG", Const.BUSINESS_STUTAS_00);
				inputMap.put("ERRORDEC", msg.toString());
				gettablecol(inputMap);
			}
		}
		
		tempData.put("retCode", errorCode);
		tempData.put("retMsg", message.toString());
		tempData.put("ERRORDEC", message.toString());
		//message 为 null RETVALUE 没有值
		LOGGER.info("该条记录错误信息为:"+message.toString());
		return tempData;
	}
	
	public void gettablecol(Map<String,Object> inputMap){
		dealInfoEditMapper = (DealInfoEditMapper) SpringUtils.getBean(DealInfoEditMapper.class);
		Map<String,Object> tableInfo=dealInfoEditMapper.selectTableColName(inputMap);
		DealInfoEdit dealInfoEdit=new DealInfoEdit();
		dealInfoEdit.setIveChannelCode((String)inputMap.get("CHANNELCODE"));
		dealInfoEdit.setIveTransDate((String)inputMap.get("TRANSDATE"));
		dealInfoEdit.setIveAppSheetSerialNo((String)inputMap.get("APPSHEETSERIALNO"));
		dealInfoEdit.setIveTableName((String)tableInfo.get("TABLE_NAME"));
		dealInfoEdit.setIveColCode((String)tableInfo.get("COLUMN_NAME"));
		dealInfoEdit.setIveColName((String)inputMap.get("COLNAME"));
		dealInfoEdit.setIveColLength((String)inputMap.get("COLLENGTH"));
		dealInfoEdit.setIveValidFlag((String)inputMap.get("VALIDFLAG"));
		dealInfoEdit.setIveColValue((String)inputMap.get("COLVALUE"));
		dealInfoEdit.setIveErrorDec((String)inputMap.get("ERRORDEC"));
		dealInfoEdit.setIveCuser("sams");
		dealInfoEdit.setIveCtime(DateUtils.getOracleSysDate());
		if(dealInfoEdit.getIveTableName().equals("D_ACCOUNT_REQ_TMP")||dealInfoEdit.getIveTableName().equals("D_EXCHANGE_REQ_TMP")){
			LOGGER.info("插入补录信息表的信息为："+dealInfoEdit);
			dealInfoEditMapper.insert(dealInfoEdit);
		}
	}
	
	@Override
	public Map<String, Object> serialNoSamsCheck (Map<String, Object> map){
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
		String type = MapUtils.getString(map, "TYPE", "");
		Map<String, Object> query = new HashMap<String, Object>(map);
		Integer count = 0 ;
		switch (type) {
			case Const.FILE_TYPE_02:
				accountReqCfmMapper = (AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
				count = accountReqCfmMapper.queryAccountSamsSerialnoCount(query);
				break;
			case Const.FILE_TYPE_04:
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
				count = exchangeReqCfmMapper.queryExchenageSamsSerialnoCount(query);
				break;
			case Const.FILE_TYPE_05:
				fundAccountReconCfmMapper = (FundAccountReconCfmMapper) SpringUtils.getBean(FundAccountReconCfmMapper.class);
				count = fundAccountReconCfmMapper.queryAccountReconSamsSerialnoCount(query);
				break;
			case Const.FILE_TYPE_06:
				fundDividendCfmMapper = (FundDividendCfmMapper) SpringUtils.getBean(FundDividendCfmMapper.class);
				count = fundDividendCfmMapper.queryDividendSamsSerialnoCount(query);
				break;
			case Const.FILE_TYPE_R2:
				residentTaxInformMapper = (ResidentTaxInformMapper) SpringUtils.getBean(ResidentTaxInformMapper.class);
				count = residentTaxInformMapper.queryResidentSamsSerialnoCount(query);
				break;
			case Const.FILE_TYPE_26:
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
				count = exchangeReqCfmMapper.queryFundVolDetailSamsSerialnoCount(query);
				break;
			default :
				break;
		
		}
		if(count != null && count > 0 ){
			retMap.putAll(ExceptionConStants.getRetObjects(ExceptionConStants.retCode_T00109, type+ExceptionConStants.retMsg_T00109));
		}
		return retMap;
	}
	
	/**
	 * 查询用户确认数据   
	 * @Title: queryAccountSendData   
	 * @author: yindy 2019年5月8日 上午10:12:36
	 * @param: @param retMap type 类型 transdate 时间
	 * @return:     返回查询的结果集
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public List<Map<String, Object>> querySendData(Map<String, Object> intoMap) {
		fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
		Map<String,Object> dictInfo = fileDataService.getDictInfo();
		String type = MapUtils.getString(intoMap, "TYPE", "");
		PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
		Map<String, Object> query = Maps.newHashMap();
		query.putAll(intoMap);
		List<Map<String,Object>> cfmList = new ArrayList<Map<String,Object>>();
		//查询要发送的用户数据
		switch (type) {
			case Const.FILE_TYPE_02:
				query.put("SQLNAME",dictInfo.get(Const.CFM_MAPPING_FIELD_02));
				accountReqCfmMapper = (AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
				cfmList = accountReqCfmMapper.selectAccountCfmData(query);
				LOGGER.info("通过"+query+"查询的02写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()) );
				break;
			case Const.FILE_TYPE_04:
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
				cfmList = exchangeReqCfmMapper.selectExchangeReqCfmData(query);
				LOGGER.info("通过"+query+"查询的04写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_05:
				query.put("SQLNAME",dictInfo.get(Const.CFM_MAPPING_FIELD_05));
				fundAccountReconCfmMapper = (FundAccountReconCfmMapper) SpringUtils.getBean(FundAccountReconCfmMapper.class);
				cfmList = fundAccountReconCfmMapper.selectFundAccountReconCfm(query);
				LOGGER.info("通过"+query+"查询的05写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_06:
				query.put("SQLNAME",dictInfo.get(Const.CFM_MAPPING_FIELD_06));
				fundDividendCfmMapper = (FundDividendCfmMapper) SpringUtils.getBean(FundDividendCfmMapper.class);
				cfmList = fundDividendCfmMapper.selectfundDividendCfm(query);
				LOGGER.info("通过"+query+"查询的06写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_44:
				query.put("SQLNAME",dictInfo.get(Const.CFM_MAPPING_FIELD_44));
				contractMapper = (ContractMapper) SpringUtils.getBean(ContractMapper.class);
				cfmList = contractMapper.selectContractCfm(query);
				LOGGER.info("通过"+query+"查询的44写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_R2:
				query.put("SQLNAME",dictInfo.get(Const.CFM_MAPPING_FIELD_R2));
				residentTaxInformMapper = (ResidentTaxInformMapper) SpringUtils.getBean(ResidentTaxInformMapper.class);
				cfmList = residentTaxInformMapper.selectResidentTaxInformCfm(query);
				LOGGER.info("通过"+query+"查询的R2写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_94:
				query.put("SQLNAME",dictInfo.get(Const.CFM_MAPPING_FIELD_94));
				String fundType = channelInfo.getCiOtherFileType();
				List<String> types = Arrays.asList(fundType.split(","));
				query.put("FUNDTYPES", types); //需要查询的产品类型
				LOGGER.info("需要生成94文件的产品类型:"+fundType);
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
				cfmList = exchangeReqCfmMapper.select94BeforeHandDataCfm(query);
				LOGGER.info("通过"+query+"查询的94写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_26:
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
                cfmList = exchangeReqCfmMapper.select26VolDetailDataCfm(query);
				LOGGER.info("通过"+query+"查询的26写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			case Const.FILE_TYPE_07:
				fundMarketCfmMapper = (FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
                cfmList = fundMarketCfmMapper.getFundMarketDataInfo(query);
				LOGGER.info("通过"+query+"查询的07写入文件的数据数量为:"+(cfmList == null ? 0:cfmList.size()));
				break;
			default :
				break;
		}
		return cfmList;
	}

	/**
	 * 修改确认文件生成时间及状态  
	 * @Title: updateGenerateStaAndTime   
	 * @author: yindy 2019年5月9日 下午2:22:56
	 * @param: @param intoMap type 文件类型
	 * @return:      返回修改的条数
	 */
	@Override
	public int updateGenerateStaAndTime(Map<String, Object> intoMap) {
		String type = MapUtils.getString(intoMap, "TYPE", "");
		int count= 0;
		switch (type) {
			case Const.FILE_TYPE_02:
				accountReqCfmMapper = (AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
				count = accountReqCfmMapper.updateGenerateStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_04:
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
				count = exchangeReqCfmMapper.updateGenerateStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_05:
				fundAccountReconCfmMapper = (FundAccountReconCfmMapper) SpringUtils.getBean(FundAccountReconCfmMapper.class);
				count = fundAccountReconCfmMapper.updateGenerateStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_06:
				fundDividendCfmMapper = (FundDividendCfmMapper) SpringUtils.getBean(FundDividendCfmMapper.class);
				count = fundDividendCfmMapper.updateGenerateStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_44:
				contractMapper = (ContractMapper) SpringUtils.getBean(ContractMapper.class);
				count = contractMapper.updateGenerateStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_R2:
				residentTaxInformMapper = (ResidentTaxInformMapper) SpringUtils.getBean(ResidentTaxInformMapper.class);
				count = residentTaxInformMapper.updateGenerateStaAndTime(intoMap);
				break;
            case Const.FILE_TYPE_26:
                FundVolDetailCfmMapper fundVolDetailCfmMapper = (FundVolDetailCfmMapper) SpringUtils.getBean(FundVolDetailCfmMapper.class);
                count = fundVolDetailCfmMapper.updateGenerateStaAndTime(intoMap);
                break;
            case Const.FILE_TYPE_07:
            	FundMarketCfmMapper fundMarketCfmMapper = (FundMarketCfmMapper) SpringUtils.getBean(FundMarketCfmMapper.class);
                count = fundMarketCfmMapper.updateGenerateStaAndTime(intoMap);
                break;
		}
		return count;
	}
	
	
	/**
	 * @throws Exception 
	 * 组装并发送用户确认文件   
	 * @Title: buildAndSendAccountData   
	 * @author: yindy 2019年5月8日 下午2:17:34
	 * @param: @param retMap
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	@SuppressWarnings("unchecked")
	public Map<String, Object> buildAndWriteData(Map<String, Object> intoMap){
		String businessDate = (String)intoMap.get("TRANSDATE");
		PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
		String csdcVer = channelInfo.getCiCsdcVer();
		String channelCode = channelInfo.getCiChannelCode();
		String type = MapUtils.getString(intoMap, "TYPE", "");
		List<Map<String,Object>> retValue = (List<Map<String, Object>>) intoMap.get("retValue");
		intoMap.remove("retValue");
		Map<String, Object> retMap = new HashMap<String, Object>();
		//获得写入数据行
		interfaceColInfoMapper = (InterfaceColInfoMapper) SpringUtils.getBean(InterfaceColInfoMapper.class);
		fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
		//设置要查询的接口字段的键值
		retMap.put("ICINTERFACECODE", Const.fileTypeMap.get(type+csdcVer));
		List<InterfaceColInfo> listColInfo = interfaceColInfoMapper.selectByInterfaceCodeList(retMap);
		retMap = FileDataUtil.buildDataLine(retValue,type,csdcVer,interfaceColInfoMapper,fileDataService);
		LOGGER.info("写入文件的拼接好的数据结果");
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
		//获得写入地址
		fileDataService = (FileDataService) SpringUtils.getBean(FileDataService.class);
		String filePath = fileDataService.getDictInfo().get(Const.FTP_LOCALDIR)+File.separator+Const.FTP_UPLOAD+File.separator;
		String path = filePath+channelCode+File.separator+businessDate;
		LOGGER.info(type+"文件写入文件的地址为"+path);
		if(!new File(path).exists()){
			new File(path).mkdirs();
		}
		//获得文件名称
		String fileName = fileDataService.getFileName(type, businessDate, channelInfo);
		//OK文件名称
		String okFileName = fileDataService.getOkFileName(fileName);
		//索引文件名称
		String indexFileName = fileDataService.getIndexFileName(type, businessDate, channelInfo);
		//索引文件ok文件
		String indexOkFileName = fileDataService.getOkFileName(indexFileName);
		//索引文件信息
		String indexInfo = fileDataService.buildIndexfileInfo(type, businessDate, channelInfo);
		
		File file = new File(path+File.separator+fileName);
		File okFile = new File(path+File.separator+okFileName);
		File indexFile = new File(path+File.separator+indexFileName);
		File indexOkFile = new File(path+File.separator+indexOkFileName);
		//获得头部信息
		String topInfo = fileDataService.buildFileTopInfo(type,businessDate,channelInfo).toString();
		//字段信息 
		String fileNameInfo = fileDataService.buildFileNameInfo(listColInfo);
		//数据行信息
		String dataVal = MapUtils.getString(retMap, "retValue", "");
		//组装
		String fileInfo = FileDataUtil.getFileInfo(topInfo, fileNameInfo, dataVal);
		
		//写入
		retMap = FileDataUtil.writeFile(file, fileInfo);									//生成业务文件
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
		retMap = FileDataUtil.writeFile(indexFile,indexInfo);								//生成业务索引文件
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
		//20200522 去掉，上传文件时限正式，后OK，这个地方没影响
		/*try {
			Thread.sleep(1000);
			LOGGER.info("生成正式"+type+"文件后睡眠1s");
		} catch (InterruptedException e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}*/
		retMap = FileDataUtil.writeFile(okFile, "");										//生成业务.OK文件
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}	
		retMap = FileDataUtil.writeFile(indexOkFile,"");									//生成业务索引.OK文件
		if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
		
		//修改确认表生成文件时间
		if(!Const.FILE_TYPE_94.equals(type)){
			//如果没有数值,不修改状态
			if(retValue != null && retValue.size()> 0 ){
				int count = updateGenerateStaAndTime(intoMap);
				LOGGER.info(type+"文件查询的数量为："+retValue.size() +"修改状态的数量为："+count);
				if(count != retValue.size()){
					retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_A00011);
				}else{
					retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
				}
			}
			
		}
		retMap.put("DATACOUNT", (retValue == null ? 0 : retValue.size()));
		intoMap.putAll(retMap);
		LOGGER.info(channelCode+"渠道"+businessDate+"生成"+type+"文件结束！");
		return intoMap;
	}

	/**
	 * 上传文件并修改发送标志   
	 * @Title: uploadAndUpStat   
	 * @author: yindy 2019年5月10日 上午8:59:02
	 * @param: @param intoMap
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,  rollbackFor=Exception.class)
	public Map<String, Object> uploadAndUpStat(Map<String, Object> intoMap) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
		
			//如果是单独文件，单独传一个参数 FILETYPES
			intoMap.put("FILETYPE", intoMap.get("FILETYPES"));
	    	if(FTPUtils.client==null){
	    		FTPUtils.getInstance();
	    	}
			retMap = ftp.uploadConFile(intoMap);
			LOGGER.info("文件的上传结果为:"+retMap);
			if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) return retMap;
			//如果不是单独文件      修改发送状态和标志
			if(intoMap.get("FILETYPES") != null ) return retMap;
			PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
			String dealfile = channelInfo.getCiDealFile();
			List<String> list = new ArrayList<String>();
			String[] arr = dealfile.split(",");
			for (String a : arr) {
				list.add(a);
			}
			//20200420  判断渠道信息表中需要发送26文件产品类型字段是否为null 不为null去验证更新26文件发送状态
			if(StringUtils.isNotBlank(channelInfo.getCiVolDetailType())){
				list.add(Const.FILE_TYPE_26);
			}
			list.add(Const.FILE_TYPE_05);
			list.add(Const.FILE_TYPE_06);
			sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
			Map<String, Object> fileMap = sysDictService.findSaleToTaMapper(Const.FILETYPE);
			for (String str : list) {
				intoMap.put("TYPE", fileMap.get(str));
				LOGGER.info("修改"+str+"对应的文件的上传时间及状态！");
				updateSendStaAndTime(intoMap);
			}
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("上传文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "uploadAndUpStat方法,上传文件异常,请联系管理员!");
			return retMap;
		}
	}

	/**
	 * 修改发送时间和发送标志
	 * @Title: updateSendStaAndTime   
	 * @author: yindy 2019年5月10日 上午9:02:38
	 * @param: @param intoMap
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	private void updateSendStaAndTime(Map<String, Object> intoMap) {
		String type = (String)intoMap.get("TYPE");
		switch (type) {
			case Const.FILE_TYPE_02:
				accountReqCfmMapper = (AccountReqCfmMapper) SpringUtils.getBean(AccountReqCfmMapper.class);
				accountReqCfmMapper.updateSendStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_04:
				exchangeReqCfmMapper = (ExchangeReqCfmMapper) SpringUtils.getBean(ExchangeReqCfmMapper.class);
				exchangeReqCfmMapper.updateSendStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_05:
				fundAccountReconCfmMapper = (FundAccountReconCfmMapper) SpringUtils.getBean(FundAccountReconCfmMapper.class);
				fundAccountReconCfmMapper.updateSendStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_06:
				fundDividendCfmMapper = (FundDividendCfmMapper) SpringUtils.getBean(FundDividendCfmMapper.class);
				fundDividendCfmMapper.updateSendStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_44:
				contractMapper = (ContractMapper) SpringUtils.getBean(ContractMapper.class);
				contractMapper.updateSendStaAndTime(intoMap);
				break;
			case Const.FILE_TYPE_R2:
				residentTaxInformMapper = (ResidentTaxInformMapper) SpringUtils.getBean(ResidentTaxInformMapper.class);
				residentTaxInformMapper.updateSendStaAndTime(intoMap);
				break;
            case Const.FILE_TYPE_26:

                FundVolDetailCfmMapper fundVolDetailCfmMapper = (FundVolDetailCfmMapper) SpringUtils.getBean(FundVolDetailCfmMapper.class);
                fundVolDetailCfmMapper.updateSendStaAndTime(intoMap);
                break;
		}
	}

	/**
	 * 获得下一个工作日  
	 * @Title: getNextWorkDay   
	 * @author: yindy 2019年5月13日 上午9:31:00
	 * @param: @param date
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public String getNextWorkDay(String date,String marketCode) {
		String retDate = "";
		//校验时否是工作日
		CloseDate closedate = new CloseDate();
		closedate.setCdCloseDate(date);
		closedate.setCdMarketCode(marketCode);
		closeDateMapper=SpringUtils.getBean(CloseDateMapper.class);
		int count = closeDateMapper.selectByMarketCodeAndcdCloseDate(closedate);
		if(count > 0){
			retDate = date;
			return retDate;
		}else{
			//获得下N个日期
			String nextDay = DateUtils.getCurrentNextDay(date);
			retDate = nextDay;
			return getNextWorkDay(nextDay,marketCode);
		}
	}
	
	
	/**
	 * 获得上一个工作日  
	 * @Title: getNextWorkDay   
	 * @author: yindy 2019年5月13日 上午9:31:00
	 * @param: @param date
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public String getLastWorkDay(String date,String marketCode) {
		//校验时否是工作日
		String retDate="";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TRANSDATE", date);
		map.put("MARKETCODE", marketCode);
		
		CloseDate closedate = new CloseDate();
		closedate.setCdCloseDate(date);
		closedate.setCdMarketCode(marketCode);
		closeDateMapper=SpringUtils.getBean(CloseDateMapper.class);
		int count = closeDateMapper.selectByMarketCodeAndcdCloseDate(closedate);
		if(count > 0){
			retDate=date;
			return retDate;
		}else{
			//获得下N个日期
			String nextDay = DateUtils.getLastDay(date);
			retDate=nextDay;
			return getLastWorkDay(nextDay,marketCode);
		}
	}
	
	
	/**
	 * 获得该交易周期的首日   
	 * @Title: getFirstOpenDay   
	 * @author: yindy 2020年2月18日 上午9:57:18
	 * @param: @param channelCode
	 * @param: @param fundCode
	 * @param: @param transDate
	 * @param: @param businessCode
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public String getFirstOpenDay(String channelCode, String fundCode,
			String transDate, String businessCode,String marketCode){
		String lastWorkDay = getLastWorkDay(DateUtils.getLastDay(transDate), marketCode);
		//判断上一个工作日是否是赎回的日期
		Map<String, Object> query = new LinkedHashMap<String, Object>();
		query.put("CHANNELCODE", channelCode);
		query.put("FUNDCODE",fundCode);
		query.put("TRANSDATE",lastWorkDay);
		query.put("BUSINESSCODE",businessCode);
		int count = productOpenDayMapper.queryIsOpenDay(query);
		if(count == 0 ){
			return transDate;
		}else{
			transDate = lastWorkDay;
			return getFirstOpenDay(channelCode, fundCode, transDate, businessCode, marketCode);
		}
	}
	
	
	/**
	 * 判断处理日期是否是工作日  
	 * @Title: checkDateIsWork   
	 * @author: yindy 2019年6月17日 下午3:22:54
	 * @param: @param date
	 * @param: @param chanelCode
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	@Override
	public boolean checkDateIsWork(String date,String channelCode){
		boolean result = false;
		pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		//校验时否是工作日
		String marketCode = channelInfo.getCiMarketCode();
		CloseDate closedate = new CloseDate();
		closedate.setCdCloseDate(date);
		closedate.setCdMarketCode(marketCode);
		closeDateMapper=SpringUtils.getBean(CloseDateMapper.class);
		int count = closeDateMapper.selectByMarketCodeAndcdCloseDate(closedate);
		if(count > 0){
			result = true;
		}
		return result ;
	}
	
	
	/**
	 * 计算确认日期
	 * @Title: getCfmDay   
	 * @author: 
	 * @param: @param date
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public String getCfmDay(String channelCode){
		ChannelInfo channelInfo=channelInfoMapper.selectChannelInfo(channelCode);
		String day="";
		if(channelInfo!=null){
			day=channelInfo.getCiProCfmWay()==null?"1":(String)channelInfo.getCiProCfmWay();
		}else{
			day="1";
		}
		return day;
	}
	
	/**
	 * 计算确认日期
	 * @Title: check94ApplyBasicData   
	 * @author: 
	 * @param: @param fundCodeMap
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public Map<String, Object> check94ApplyBasicData(Map<String, Object> fundCodeMap){
		Map<String,Object> retMap=Maps.newHashMap();
		
		return retMap;
	}

	
	@Override
	public List<DayEndProcessor> processorStepList(PageInfo pageInfo,
			Map<String, Object> condition) {

		String date = MapUtils.getString(condition, "date", DateUtils.getDate());
		condition.put("transDate", date.replaceAll("-", ""));
		//查询确认数据
		List<DayEndProcessor> list = processStepInfoService.queryProcessStat(pageInfo,condition);
		LOGGER.info("查询的日终页面的结果集数量为:"+(list == null ? 0:list.size()));
		handelMsgAndDate(list);
		LOGGER.info("处理之后的日终页面的结果集数量为:"+(list == null ? 0:list.size()));
		return list;
	}


	/**
	 * 处理错误信息   
	 * @Title: handelMsgAndDate   
	 * @author: yindy 2019年5月31日 下午4:16:19
	 * @param: @param List<DayEndProcessor>
	 * @return: List<DayEndProcessor>      
	 * @throws
	 */
	private void  handelMsgAndDate(List<DayEndProcessor> List) {
		for (DayEndProcessor dto : List) {
			StringBuilder msg = new StringBuilder();
			String dataMsg = dto.getErrorMessage(); //数据处理错误信息
			String confirmMsg = dto.getConfirmErrorMessage(); //生成文件错误信息
			String sendMessage = dto.getSendErrorMessage(); //发送文件错误信息
			String businessdate = dto.getBusinessDate(); //操作时间
			String processStep = dto.getProcessStep(); //数据处理步骤
			String confirmCrocessStep = dto.getConfirmProcessStep(); //生成文件步骤
			String maxProcessStep = dto.getMaxProcessStep(); //数据处理最大步骤
			String maxConfirmProcessStep = dto.getMaxConfirmProcessStep(); //生成文件最大步骤
			if(!StringUtils.isEmpty(dataMsg)){ //错误信息不为空
				msg.append(dto.getProcessStepName()+"--"+ dataMsg).append("<br/>");
			}else if(StringUtils.isEmpty(dataMsg) && !StringUtils.isEmpty(processStep)){ //错误信息为空,但是查到的最新步骤数不是最后一步
				if(!maxProcessStep.equals(processStep)){
					LOGGER.info("查询的最新步骤为："+processStep+",配置的最大步骤数为："+maxProcessStep);
					//0 表示数据处理   1 表示生成文件
					msg.append("申请数据处理中...").append("<br/>");
					dto = setErrorMessage(dto,processStep,0);
				}
				dto.setProcessStepName("查看");
			}
			if(!StringUtils.isEmpty(confirmMsg)){
				msg.append(dto.getConfirmProcessStepName()+"--"+ confirmMsg).append("<br/>");
			}else if(StringUtils.isEmpty(confirmMsg) && !StringUtils.isEmpty(confirmCrocessStep)){
				dto.setConfirmProcessStepName("查看");
				if(!maxConfirmProcessStep.equals(confirmCrocessStep)){
					LOGGER.info("查询的最新步骤为："+processStep+",配置的最大步骤数为："+maxProcessStep);
					msg.append("确认数据生成中...").append("<br/>");
					dto = setErrorMessage(dto,confirmCrocessStep,1);
				}
			}
			if(!StringUtils.isEmpty(sendMessage)){ //发送错误信息不为空
				msg.append(dto.getSendProcessStepName()+"--"+ sendMessage);
			}
			if(StringUtils.isEmpty(businessdate)){
				dto.setCurrentDate(DateUtils.getDate(DateUtils.FORMAT_STR_DATE8));
			}else{
				dto.setCurrentDate(businessdate);
			}
			dto.setErrorMessage(msg.toString());
		}
	}


	/**
	 * @param dto 
	 * 统一处理异常没捕捉到的错误信息
	 * @Title: setErroeMessage   
	 * @author: yindy 2019年7月22日 下午3:06:09
	 * @param: @param processStep
	 * @param: @param i
	 * @param: @return      
	 * @return: DayEndProcessor      
	 * @throws
	 */
	private DayEndProcessor setErrorMessage(DayEndProcessor dto, String processStep, int i) {
		//0 表示数据处理   1 表示生成文件
		if(i == 0 ){
			dto.setProcessStat("00");
			dto.setProcessStatName("申请处理中");
			dto.setProcessStep(FileDataUtil.getIntParameter(String.valueOf((Integer.valueOf(processStep)+1)),2));
		}else{
			dto.setConfirmProcessStat("00");
			dto.setConfirmProcessStatName("确认处理中");
			dto.setConfirmProcessStep(FileDataUtil.getIntParameter(String.valueOf((Integer.valueOf(processStep)+1)),2));
		}
		return dto;
	}


	/**
	 * 查询分红数据 
	 * @Title: queryProfitList   
	 * @author: yindy 2019年7月18日 上午11:22:29
	 * @param: @param pageInfo
	 * @param: @param channelCodes
	 * @param: @param date
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public List<ParticipationProfit> queryProfitList(PageInfo pageInfo, String channelCodes,
			String date) {
		List<String> channelCodeList = new ArrayList<String>();
		if(StringUtils.isEmpty(channelCodes)){
			channelCodeList = pchannelInfoMapper.selectAllChannels();
		}else{
			channelCodeList = Arrays.asList(channelCodes.split(","));
		}
		//根据渠道 时间  查询 交易确认表142 数据   分红表 143 数据
		/**
		 * 产品类型	分红类型	文件		业务编码	
		 *  红七    	 中期		06		143
		 *  固收		中期		06		143		
		 *  固收		到期		04+06	150+143
		 *  固收		部分返本	04		150
		 *  T+N		到期		04		142
		 *  丰利		中期		06		143
		 *  封闭净值	到期		04		142
		 */
		//查询渠道下的142、150的数据
		List<ParticipationProfit> transProfitList = fundDividendCfmMapper.selectTransProfitList(channelCodeList,date);
		LOGGER.info("根据"+channelCodeList.toString()+"日期"+date+"查询到的到期和清盘数据数量为："
				+(transProfitList ==null ? null : transProfitList.size()));
		Map<String, BigDecimal> transMap = new HashMap<String, BigDecimal>();
		if(!CollectionUtils.isEmpty(transProfitList)){ //有中期分红数据
			transMap = changeToMap(transProfitList,null);
		}
		//查询出渠道下单个产品的收益总额
		List<ParticipationProfit> dividendList = fundDividendCfmMapper.selectDividendList(channelCodeList,date);
		LOGGER.info("根据"+channelCodeList.toString()+"日期"+date+"查询到的分红数据数量为："
				+(dividendList ==null ? null : dividendList.size()));
		//转换
		Map<String, BigDecimal> dividendMap = new HashMap<String, BigDecimal>();
		if(!CollectionUtils.isEmpty(dividendList)){ //有中期分红数据
			dividendMap = changeToMap(dividendList,null);
		}
		
		//组装数据
		List<ParticipationProfit> retList = getReturnList(transProfitList,transMap,dividendList,dividendMap,null);
		return retList;
	}

	/**
	 * 组装返回分红数据  
	 * @Title: getReturnList   
	 * @author: yindy 2019年12月13日 下午2:10:13
	 * @param: @param transProfitList
	 * @param: @param transMap
	 * @param: @param dividendList
	 * @param: @param dividendMap
	 * @param: @param type
	 * @param: @return      
	 * @return: List<ParticipationProfit>      
	 * @throws
	 */
	private List<ParticipationProfit> getReturnList(
			List<ParticipationProfit> transProfitList,
			Map<String, BigDecimal> transMap,
			List<ParticipationProfit> dividendList,
			Map<String, BigDecimal> dividendMap, Integer type) {
		List<ParticipationProfit> retList = new ArrayList<ParticipationProfit>();
		if(!CollectionUtils.isEmpty(transProfitList)){ //有到期分红数据
			LOGGER.info("有到期分红数据！");
			for (ParticipationProfit transDto : transProfitList) {
				String key = transDto.getChannelCode()+"-"+transDto.getFundCode();
				if(type != null ){ //详细区分，精确到每个合同
					key = transDto.getChannelCode()+"-"+transDto.getFundCode()+"-"+transDto.getInContract();
				}
				BigDecimal earningsAmount = dividendMap.get(key); //06文件收益
				String productType = transDto.getProductType(); //产品类型
				transDto.setProfitNature("到期分红");
				if(earningsAmount == null && !Const.FUND_TYPE_04.equals(productType)){ //T+N到期
					BigDecimal application = transDto.getApplicationAmount() == null ? new BigDecimal(0) : transDto.getApplicationAmount();
					earningsAmount = transDto.getTotalAmount().subtract(application);
				}else{ //固收到期
					BigDecimal total = transDto.getTotalAmount();
					if(total.compareTo(transDto.getApplicationAmount()) != 0){
						transDto.setProfitNature("部分返本");
						total = total.add(earningsAmount== null ? new BigDecimal(0) : earningsAmount);
						earningsAmount = transDto.getEarningsAmount().add(earningsAmount== null ? new BigDecimal(0) : earningsAmount);
					}else{
						total = total.add(earningsAmount== null ? new BigDecimal(0) : earningsAmount);
					}
					transDto.setTotalAmount(total);
				}
				transDto.setEarningsAmount(earningsAmount);
				retList.add(transDto);
				
			}
			if(!CollectionUtils.isEmpty(dividendList)){ //有中期  或者固收到期
				//排除固收到期的   剩下的
				for (ParticipationProfit dividendDto : dividendList) {
					String key = dividendDto.getChannelCode()+"-"+dividendDto.getFundCode();
					if(type != null){
						key = dividendDto.getChannelCode()+"-"+dividendDto.getFundCode()+"-"+dividendDto.getInContract();
					}
					if(!transMap.containsKey(key)){
						dividendDto.setProfitNature("中期分红");
						retList.add(dividendDto);
					}
				}
			}
		}else{
			if(!CollectionUtils.isEmpty(dividendList)){ //只有中期分配
				for (ParticipationProfit dto : dividendList) {
					dto.setProfitNature("中期分红");
					retList.add(dto);
				}
			}
		}
		return retList;
	}


	/**
	 * 把收益转成channelcode-fundcode键earningsAmount 收益金额 为值的map   
	 * @Title: changeToMap   
	 * @author: yindy 2019年7月18日 下午2:42:30
	 * @param: @param dividendList
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, BigDecimal> changeToMap(
			List<ParticipationProfit> list,Integer type) {
		Map<String, BigDecimal> retMap = new HashMap<String, BigDecimal>();
		for (ParticipationProfit dto : list) {
			String key = dto.getChannelCode()+"-"+dto.getFundCode();
			if(type != null ){
				key = dto.getChannelCode()+"-"+dto.getFundCode()+"-"+dto.getInContract();
			}
			retMap.put(key, dto.getEarningsAmount());
		}
		return retMap;
	}

	@Override
	public List<ParticipationProfit> queryProfitDetail(String channelCode,
			String date, String fundCode) {
		//精确到个人
		List<ParticipationProfit> transProfitdetail = fundDividendCfmMapper.selectTransProfitDetail(channelCode,date,fundCode);
		LOGGER.info("根据"+channelCode+"日期"+date+"产品"+fundCode+"查询到的到期和清盘数据数量为："
				+(transProfitdetail ==null ? null : transProfitdetail.size()));
		Map<String, BigDecimal> transMap = new HashMap<String, BigDecimal>();
		if(!CollectionUtils.isEmpty(transProfitdetail)){ //有中期分红数据
			transMap = changeToMap(transProfitdetail,1);
		}
		
		List<ParticipationProfit> dividenddetail = fundDividendCfmMapper.selectDividendDetail(channelCode,date,fundCode);
		LOGGER.info("根据"+channelCode+"日期"+date+"产品"+fundCode+"查询到的分红数量为："
				+(dividenddetail ==null ? null : dividenddetail.size()));
		//转换
		Map<String, BigDecimal> dividendMap = new HashMap<String, BigDecimal>();
		if(!CollectionUtils.isEmpty(dividenddetail)){ //有中期分红数据
			dividendMap = changeToMap(dividenddetail,1);
		}
		List<ParticipationProfit> retList = getReturnList(transProfitdetail,transMap,dividenddetail,dividendMap,1);
		
		return retList;
	}


	/**
	 * 上传文件
	 * @Title: execute   
	 * @author: yindy 2019年7月22日 下午1:55:30
	 * @param: @param channelCode
	 * @param: @param params
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public ResultBean<String> checkSendFile(String channelCode, Map<String, Object> params) {
		pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		Map<String, Object> intoMap = new HashMap<String, Object>();
		//获得当前处理的日期 
		String transDate = (String)params.get("TRANSDATE");
		//判断该渠道该日期是否是工作日，不是工作日不作处理
		boolean flag = checkDateIsWork(transDate,channelCode);
		ResultBean<String> resultBean = ResultBean.newInstance();
		if(flag){
			intoMap.put("CHANNELINFO", channelInfo);
			intoMap.put("CHANNELCODE", channelCode);
			intoMap.putAll(params);
			sendFileProcessor(intoMap);
			resultBean.setData(channelCode);
		}else{
			LOGGER.info(channelCode+"渠道,"+transDate+"为非工作日。");
			resultBean.setMsg(channelCode+"渠道,"+transDate+"为非工作日。");
		}
		return resultBean;
	}

	/**
	 * 上传文件流程  
	 * @Title: sendFileProcessor   
	 * @author: yindy 2019年7月25日 下午2:25:11
	 * @param: @param params      
	 * @return: void      
	 * @throws
	 */
	private void sendFileProcessor(Map<String, Object> intoMap) {
		LOGGER.info("上传文件处理流程开始,当前时间为:"+DateUtils.getDate());
		String userName = (String)intoMap.get("USERNAME");
		String specialFile = (String)intoMap.get("FILETYPES");
		Long confirmStart = DateUtils.getOracleSysDate().getTime();
		LOGGER.info(userName+"操作特殊单独处理文件:"+specialFile);
		List<ProcessStepInfo> specialList = new ArrayList<ProcessStepInfo>();//存储单独处理文件步骤
		try{
			Map<String, Object> retMap = new HashMap<String, Object>();
			List<ProcessStepInfo> list = (List<ProcessStepInfo>)processStepInfoService.selectByProcessStepInfo(Const.SEND_FILE_STEP,null,null,null).get("LISTREQ");
			if(!StringUtils.isEmpty(specialFile)){
				specialList = (List<ProcessStepInfo>)processStepInfoService.selectByProcessStepInfo(Const.SEND_FILE_STEP,null,null,specialFile).get("LISTREQ");
			}
			Object[] inputObj = new Object[1];
			inputObj[0] = intoMap;
			for(ProcessStepInfo stepInfo:list){
				String packageName = stepInfo.getPsiPackageName();
				String className = stepInfo.getPsiClassName();
				String methodName = stepInfo.getPsiMethodName();
				String processName = stepInfo.getPsiProcessName();
				LOGGER.info("该步操作为："+processName);
				LOGGER.info("输入参数为:"+JSONObject.toJSONString(inputObj[0]));
				Long start = DateUtils.getOracleSysDate().getTime();
				Class<?> clazz=Class.forName(packageName+"."+className);
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
				LOGGER.warn("------------------------"+processName+"步骤消耗的的时间为:"+(DateUtils.getOracleSysDate().getTime()-start)+"ms--------------------");
				LOGGER.info("输出参数为:"+JSONObject.toJSONString(obj));
				retMap.put("PROCESSSTEPINFO", stepInfo);
				retMap.put("PROCESSSTARTTIME", DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
				retMap.put("BRANCHCODE", Const.SEND_FILE_STEP);
				retMap.put("TRADEDATE", intoMap.get("TRANSDATE"));
				retMap.put("USERNAME", userName);
				retMap.putAll((Map<String, Object>)obj);
				fundMarketProcessorService = (FundMarketProcessorService) SpringUtils.getBean(FundMarketProcessorService.class);
				fundMarketProcessorService.insertProcessLog(retMap, MapUtils.getString(intoMap, "CHANNELCODE", ""));
				if(!ExceptionConStants.retCode_0000.equals(((Map<String, Object>)obj).get("retCode"))) {
					LOGGER.info("处理结果："+obj);
					break;
				}
				inputObj[0] = obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("上传文件处理流程出错"+error);
		}
		LOGGER.info("上传文件处理流程完成,当前时间为:"+DateUtils.getDate());
		LOGGER.warn("整个上传文件耗时为:"+(DateUtils.getOracleSysDate().getTime()-confirmStart)+"ms");
	}


	/**
	 * 查询交易统计数据
	 * @Title: selectTransStatistics   
	 * @author: yindy 2019年8月19日 上午8:24:15
	 * @param: @param pageInfo
	 * @param: @param map
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> selectTransStatistics(PageInfo pageInfo,
			Map<String, Object> map) {
		PageHelperUtils.startPage(pageInfo);
		//拆分产品代码
		String fundCode = MapUtils.getString(map, "FUNDCODE", "");
		if(!StringUtils.isEmpty(fundCode)){
			List<String> fundCodes = Arrays.asList(fundCode.split(","));
			map.put("FUNDCODES", fundCodes);
		}
		List<Map<String, Object>> list = exchangeReqCfmMapper.selectTransStatistics(map);
		return list;
	}


	@Override
	public List<Map<String, Object>> getMultipleDetail(
			String channelCode,
			String fundCode, 
			String businessCode, 
			String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CHANNELCODE", channelCode);
		map.put("FUNDCODE", fundCode);
		map.put("BUSINESSCODE", businessCode);
		map.put("STARTDATE", startDate);
		map.put("ENDDATE", endDate); 
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(!Const.BUSINESS_020.equals(businessCode) && !Const.BUSINESS_024.equals(businessCode)){
			//查确认表
			if(Const.BUSINESS_120.equals(businessCode))businessCode = Const.BUSINESS_020;
			if(Const.BUSINESS_124.equals(businessCode))businessCode = Const.BUSINESS_024;
			map.put("BUSINESSCODE", businessCode);
			list = exchangeReqCfmMapper.getMultipleCfmDetail(map);
		}else{ //查申请表
			list = exchangeReqCfmMapper.getMultipleDetail(map);
		}
		return list;
	}

	/**
	 * @Description 多文件上传
	 * @Author weijunjie
	 * @Date 2020/5/13 9:52
	 **/
	public String uploadFiles(String channelCode, InputStream is, String fileType,String fileName){
        PchannelInfo pchannelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
        try {
            ftp.uploadFileToFtp(pchannelInfo,is,fileType,fileName);
            LOGGER.info("{}文件上传成功",fileName);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("{}文件上传发生异常：{}",fileName,e.getMessage());
            return "error";
        }finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

	}

    public List<Map<String, String>> getFileStatusForPage(PageInfo pageInfo,String channelCode, String businessDate, String upType){
        PageHelperUtils.startPage(pageInfo);
	    List<Map<String, String>> maps = checkFileStatus(channelCode, businessDate, upType);
        pageInfo.setTotal(maps.size());
        return maps;
    }

    /**
	 * @Description 校验文件在ftp服务器存在的状态
	 * @Author weijunjie
	 * @Date 2020/5/13 14:41
	 **/
    public List<Map<String, String>> checkFileStatus(String channelCode, String businessDate,String upType){
        //组装返回前端表格list数据
        List<Map<String, String>> resArray = new ArrayList<>();
        //获取访问对应的文件夹路径
        String upDir = upType.equals("3")?Const.FTP_REMOTE_DOWNLOAD_DIR:Const.FTP_REMOTE_UPLOAD_DIR;
        boolean checkBak = upType.equals("3");
        PchannelInfo pchannelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
        try {
            ArrayList<String> allFilesNameToUse = ftp.getAllFilesNameToUse(pchannelInfo, businessDate, upType);
            //判断当前获取的文件路径在ftp目录下是否存在
            upType = upType.equals("1")?"07":"0";

            for(String fileName:allFilesNameToUse){
                //组装前端展示数据信息
                Map<String, String> hashMap = new HashMap<>();
                String url1 = ftp.checkFileByUrl(pchannelInfo, upType, fileName, upDir);
                if(StringUtils.isNotBlank(url1)){
                    hashMap.put("channelCode",channelCode);
                    hashMap.put("channelName",pchannelInfo.getCiChannelName());
                    hashMap.put("fileName",fileName);
                    hashMap.put("fileDate",businessDate);
                    hashMap.put("fileUrl",url1);
                    hashMap.put("fileStatus","文件存在");
                }else{
                    //添加.bak数据校验
                    if(checkBak){
                        fileName+=Const.FILE_BAK;
                        String url2 = ftp.checkFileByUrl(pchannelInfo, upType, fileName, upDir);
                        if(StringUtils.isNotBlank(url2)){
                            hashMap.put("channelCode",channelCode);
                            hashMap.put("channelName",pchannelInfo.getCiChannelName());
                            hashMap.put("fileName",fileName);
                            hashMap.put("fileDate",businessDate);
                            hashMap.put("fileUrl",url1);
                            hashMap.put("fileStatus","文件存在已处理");
                        }
                    }
                }
                if(null != hashMap && hashMap.keySet().size()>0){
                    resArray.add(hashMap);
                }
            }
            return resArray;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                ftp.getCloseFtpClient();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * @Description 获取本地文件的localurl
     * @Author weijunjie
     * @Date 2020/5/15 8:42
     **/
    public List<Map<String,String>> getFileLocalUrls(String channelCode,String tradeDate,List<Map<String,String>> fileUrlMaps){
        return ftp.getLocalFileUrl(channelCode,tradeDate,fileUrlMaps);
    }


}
