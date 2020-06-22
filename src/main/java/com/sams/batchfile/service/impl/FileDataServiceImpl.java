package com.sams.batchfile.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sams.common.utils.MyMapUtils;
import com.sams.custom.mapper.InterfaceColInfoMapper;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sams.batchfile.common.CheckFileUtil;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.common.ReadFileUtil;
import com.sams.batchfile.service.AccountReqCfmService;
import com.sams.batchfile.service.ChannelInfoService;
import com.sams.batchfile.service.CloseDateService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FileInterfaceFieldService;
import com.sams.batchfile.service.FundMarketCfmServier;
import com.sams.batchfile.service.FundMarketService;
import com.sams.batchfile.service.ParProductInfoService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.ftp.FTPUtils;
import com.sams.common.local.LocalUtils;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.FileUtils;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.model.CloseDate;
import com.sams.custom.model.FundMarketCfm;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.PchannelInfo;
import com.sams.sys.mapper.SysDictMapper;
import com.sams.sys.model.SysDict;
import com.sams.sys.service.SysDictService;
/**
 * 文件数据处理业务层
 * @ClassName:  FileDataServiceImpl   
 * @author: yindy
 * @date:   2019年4月10日 下午3:59:30   
 */
@Service
public class FileDataServiceImpl implements FileDataService{

	private static Logger LOGGER = LoggerFactory.getLogger(FileDataServiceImpl.class);
	
	@Autowired
	private SysDictMapper sysDictMapper;
	
	@Autowired
	private PchannelInfoMapper pchannelInfoMapper;
	
	@Autowired
	private FundMarketCfmServier fundMarketCfmServier;
	
	@Autowired
	private FileInterfaceFieldService fileInterfaceFieldService;
	
	
	@Autowired
	private FundMarketService fundMarketService;
	
	@Autowired
	private CloseDateService closeDateServiceImpl;
	
	@Autowired
	private ChannelInfoService channelInfoService;
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private ParProductInfoService parProductInfoService;
	
	@Autowired
	private FundMarketCfmServier fundMarketCfmService;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private AccountReqCfmService accountReqCfmService;

    @Autowired
    private InterfaceColInfoMapper interfaceColInfoMapper;
	
	FTPUtils ftp=new FTPUtils();
	
	
	/**
	 * 根据渠道编号查询渠道信息
	 * @Title: queryChannelInfoByChannelId   
	 * @author: yindy 2019年4月12日 上午8:23:16
	 * @param: @param channelid
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public Map<String,Object> selectChannelInfoByChannelCode(String channelCode) {
		Map<String,Object> retMap=Maps.newHashMap();
		retMap = checkPchannelInfo(channelCode);
		if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
		return retMap;
	}
	
	public Map<String,Object> checkPchannelInfo(String channelCode){
		Map<String,Object> retMap=Maps.newHashMap();
		pchannelInfoMapper = (PchannelInfoMapper)SpringUtils.getBean(PchannelInfoMapper.class);
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		if(channelInfo==null){
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("retMsg", "通过"+channelCode+"拿到的channelInfo为空");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("CHANNELINFO", channelInfo);
		}
		return retMap;
		
	}
	/**
	 * 组装发送文件的头部信息  
	 * @Title: buildFileTopInfo   
	 * @author: yindy 2019年4月10日 下午3:47:58
	 * @param: @param type 文件类型
	 * @param: @param pChannelInfo 渠道信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public StringBuilder buildFileTopInfo(String type, String date , PchannelInfo channelInfo) {
		StringBuilder top = new StringBuilder();
		String rn = Const.FILE_ENTER;
		String csdcVersion = "";
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		String exchangeCsdcChannel = sysDictService.findDictVo(Const.EXCHANGE_CSDC_CHANNEL);
		if(Const.FILE_TYPE_94.equals(type) || (!StringUtils.isEmpty(exchangeCsdcChannel) && exchangeCsdcChannel.contains(channelInfo.getCiChannelCode()))){
			csdcVersion = Const.FILE_VER_20;
		}else {
			csdcVersion = channelInfo.getCiCsdcVer();
		}
		
		
		
		top.append(Const.FILE_OFDCFDAT).append(rn)
			.append(csdcVersion).append(rn) // 中登版本
			.append(channelInfo.getCiSalesPerson()).append(rn) //发送人编码
			.append(channelInfo.getCiSaleAgentCode()).append(rn) //接收人编号
			.append(date).append(rn) //日期
			.append(Const.FILE_999).append(rn) //汇总表号
			.append(type).append(rn) //文件类型
			.append(channelInfo.getCiSalesPerson()).append(rn)  // 发送人编码
			.append(channelInfo.getCiSaleAgentCode()).append(rn); //接收人编号
		return top;
	}
	
	/**
	 * 获得写入文件的字段信息  
	 * @Title: buildFileNameInfo   
	 * @author: yindy 2019年4月11日 下午1:54:13
	 * @param: @param list
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public String buildFileNameInfo(List<InterfaceColInfo> list) {
		StringBuilder fileNameInfo = new StringBuilder();
		String rn = Const.FILE_ENTER;
		fileNameInfo.append(FileDataUtil.getIntParameter(String.valueOf(list.size()),3)).append(rn);
		//格式化  拼接
		for (InterfaceColInfo colInfo : list) {
			String value = colInfo.getIcColName().toUpperCase();
			fileNameInfo.append(value).append(rn);
		}
		return fileNameInfo.toString();
	}
	
	/**
	 * 组装数据文件的字段信息  
	 * @Title: buildFileTopInfo   
	 * @author: yindy 2019年4月10日 下午3:47:58
	 * @param: @param type 文件类型
	 * @param: @param pChannelInfo 渠道信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public StringBuilder buildFileColFileds(Map<String,String> map,StringBuilder stringBuilder) {
		String rn = Const.FILE_ENTER;
		stringBuilder.append(FileDataUtil.getIntParameter(String.valueOf(map.size()), 3)).append(rn);
		for(int i=1;i<=map.size();i++){
			stringBuilder.append(((String)map.get(i+"")).toUpperCase()).append(rn);
		}
		return stringBuilder;
	}


	/**
	 * 组装发送索引文件的信息  
	 * @Title: buildIndexfileInfo   
	 * @author: yindy 2019年4月10日 下午3:49:35
	 * @param: @param type 文件类型
	 * @param: @param pChannelInfo 渠道信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public String buildIndexfileInfo(String type,String date , PchannelInfo pChannelInfo) {
		StringBuilder index = new StringBuilder();
		sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
		String rn  = Const.FILE_ENTER;
		String num = "";
		StringBuilder fileName = new StringBuilder();
		String clientCode = pChannelInfo.getCiSaleAgentCode();
		String salasPerson = pChannelInfo.getCiSalesPerson();
		String csdv = pChannelInfo.getCiCsdcVer();
		
		String exchangeCsdcChannel = sysDictService.findDictVo(Const.EXCHANGE_CSDC_CHANNEL);
		csdv = ReadFileUtil.exchangeCsdc(pChannelInfo, exchangeCsdcChannel);
		
		if(Const.FILE_TYPE_07.equals(type) || Const.FILE_TYPE_44.equals(type) || Const.FILE_TYPE_94.equals(type)){
			num = Const.FILE_001;
			fileName.append(Const.FILE_OFD_+salasPerson+Const.FIlE_+clientCode+Const.FIlE_+date+Const.FIlE_+type+Const.FILE_TXT).append(rn);
		}else{
			//根据勾选获得索引文件内容 
			Map<String, Object> fileMap = sysDictService.findSaleToTaMapper(Const.FILETYPE);
			List<String> list = FileDataUtil.getReadFile(pChannelInfo, new ArrayList<String>());
			int size = 0;
			if(list.contains(Const.FILE_TYPE_43)){
				size = list.size()-1;
			}else{
				size = list.size();
			}
			num = FileDataUtil.getIntParameter(String.valueOf(size),3);
			String prex = Const.FILE_OFD_+salasPerson+Const.FIlE_+clientCode+Const.FIlE_+date+Const.FIlE_;
			for (String str : list) {
				String fileType = (String)fileMap.get(str);
				if(Const.FILE_TYPE_44.equals(fileType))continue;
				fileName.append(prex).append(fileType).append(Const.FILE_TXT).append(rn);
			}
		}
		index.append(Const.FILE_OFDCFIDX).append(rn)
			.append(csdv).append(rn) //中登版本
			.append(salasPerson).append(rn) //发送人代码
			.append(clientCode).append(rn) //接收人编号
			.append(date).append(rn) //日期
			.append(num).append(rn)
			.append(fileName)
			.append(Const.FILE_OFDCFEND);
		return index.toString();
	}


	/**
	 * 获得文件名称   
	 * @Title: getFileName   
	 * @author: yindy 2019年4月10日 下午3:51:08
	 * @param: @param type 文件类型
	 * @param: @param pChannelInfo 渠道信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public String getFileName(String type, String date ,PchannelInfo pChannelInfo) {
		StringBuilder name = new StringBuilder();
		String clientCode = pChannelInfo.getCiSaleAgentCode();
		String salasPerson = pChannelInfo.getCiSalesPerson();
		if(Const.RECV_FILE_TYPE.contains(type)){
			name.append(Const.FILE_OFD+Const.FIlE_+clientCode+Const.FIlE_+salasPerson+Const.FIlE_+date+Const.FIlE_+type+Const.FILE_TXT);
		}else if(Const.SEND_FILE_TYPE.contains(type)){
			name.append(Const.FILE_OFD_+salasPerson+Const.FIlE_+clientCode+Const.FIlE_+date+Const.FIlE_+type+Const.FILE_TXT);
		}
		return name.toString();
	}

	/**
	 * 获得索引文件名称   
	 * @Title: getIndexFileName   
	 * @author: yindy 2019年4月10日 下午3:52:46
	 * @param: @param type 文件类型
	 * @param: @param pChannelInfo 渠道信息
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public String getIndexFileName(String type, String date ,PchannelInfo pChannelInfo) {
		String clientCode = pChannelInfo.getCiSaleAgentCode();
		String salasPerson = pChannelInfo.getCiSalesPerson(); //发送人
		StringBuilder name = new StringBuilder();
		if(Const.RECV_FILE_TYPE.contains(type)){
			name.append(Const.FIlE_+clientCode+Const.FIlE_+salasPerson+Const.FIlE_+date+Const.FILE_TXT);
		}else if(Const.SEND_FILE_TYPE.contains(type)){
			name.append(Const.FIlE_+salasPerson+Const.FIlE_+clientCode+Const.FIlE_+date+Const.FILE_TXT);
		}
		if(Const.FILE_TYPE_44.equals(type) || Const.FILE_TYPE_43.equals(type)){
			name.insert(0, Const.FILE_OFG);
		}else if(Const.FILE_TYPE_07.equals(type)){
			name.insert(0, Const.FILE_OFJ);
		}else if(Const.FILE_TYPE_94.equals(type)){
			name.insert(0, Const.FILE_OFY);
		}else{
			name.insert(0, Const.FILE_OFI);
		}
		return name.toString();
	}
	/**
	 * 获得.ok文件名称   
	 * @Title: getOkFileName   
	 * @author: yindy 2019年4月10日 下午3:54:51
	 * @param: @param fileName 对应的文件名称
	 * @return: String      
	 * @throws
	 */
	@Override
	public String getOkFileName(String fileName) {
		StringBuilder name = new StringBuilder(fileName);
		return name.append(Const.FILE_OK).toString();
	}
	
	
	public Map<String,Object> checkFiledData(Map<String,Object> inputMap){
		LOGGER.info("根据接口字段基本信息校验数据记录步骤开始处理");

		//查询渠道信息
		String channelCode = (String)inputMap.get("CHANNELCODE");
//		boolean caninsertFundMarketCFM = true;
		Map<String,Object> mapInfo = selectChannelInfoByChannelCode(channelCode);
		PchannelInfo channelInfo = (PchannelInfo)mapInfo.get("CHANNELINFO");
		
		List<Map<String, Object>> list=(List<Map<String, Object>>) inputMap.get("RETVALUE");
		List<Map<String,Object>> outputList=Lists.newArrayList();
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		//输出信息
		Map<String,Object> outputMap = new HashMap<String, Object>();
		outputMap.put("CHANNELCODE", channelCode);
		outputMap.put("TRADEDATE", MapUtils.getString(inputMap, "TRADEDATE", ""));
		
		//行情信息
		FundMarketCfm fundMarketCfm = new FundMarketCfm();
		fundMarketCfm.setFmChannelCode(channelCode);
		fundMarketCfm.setFmBusinessDate(MapUtils.getString(inputMap,"TRADEDATE"));
		
		fundMarketCfmService=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
		fileInterfaceFieldService=(FileInterfaceFieldService) SpringUtils.getBean(FileInterfaceFieldService.class);
		fundMarketService=(FundMarketService) SpringUtils.getBean(FundMarketService.class);
		exchangeProcessorService=(ExchangeProcessorService) SpringUtils.getBean(ExchangeProcessorService.class);
		
		//先删除行情确认表该渠道下处理日期的数据
		fundMarketCfmService.deleteFundMarketCFM(fundMarketCfm);
		
		Map<String,Object> insertFundMarketCFMMap=Maps.newHashMap();
		insertFundMarketCFMMap.put("FMBUSINESSDATE", MyMapUtils.getString(inputMap,"TRADEDATE"));
		insertFundMarketCFMMap.put("FMCHANNELCODE", channelCode);
		insertFundMarketCFMMap.put("FMDATAGENERATE", DateUtils.getOracleSysDate());
		
		boolean checkFiledData=true;
		Map<String,InterfaceColInfo> filedMap = Maps.newHashMap();
		
		//获得该字段的接口字段信息
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("ICINTERFACECODE", "7"+channelInfo.getCiCsdcVer());
		if(filedMap.size()==0){
			filedMap = fileInterfaceFieldService.selectFileInterfaceFieldList(paramMap);
		} 
		
		//浦发银行的固收类和T+N万份收益和七日年化取值
		String tradeDate = MapUtils.getString(inputMap, "TRADEDATE", "");//页面输入的行情日期
		
		//获取T-1个工作日
		String lastT1WorkDay = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(tradeDate), channelInfo.getCiMarketCode()); 
				LOGGER.info(channelCode+"渠道的上一个工作日为"+lastT1WorkDay);
		//获取T-2个工作日
		String lastT2WorkDay = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(lastT1WorkDay), channelInfo.getCiMarketCode());
		         LOGGER.info(channelCode+"渠道的上两个工作日为"+lastT2WorkDay);
		//行情记录
		Map<String,Object> outFileMap = null;
		
		Map<String,Object> incomeYieldMap = null;
		
		Map<String,Object> netValueMap = null;
		
		
		
		if(null!=list && list.size() != 0){
			for (Map<String, Object> dataMap : list) {
				
				String fundType = MapUtils.getString(dataMap, "PRODUCTTYPE", "");//产品类型
				String remainShares = MapUtils.getString(dataMap, "REMAINSHARES", "0");//TA产品代码对应持仓总额
				String taProductCode = MapUtils.getString(dataMap, "TAPRODUCTCODE", "");//TA产品代码
				String maxAmountRaised = MapUtils.getString(dataMap, "MAXAMOUNTRAISED", "");//产品募集金额
				String fundCode = MapUtils.getString(dataMap, "PRODUCTCODE", "");//代销端产品代码
				String proSetupDate = MapUtils.getString(dataMap, "PROSETUPDATE", "");//产品成立日

				outFileMap = new HashMap<String, Object>();
				for(String key:dataMap.keySet()){
					if(Const.notUsedCols.contains(key)){
						continue;
					};
					
					InterfaceColInfo colInfo = filedMap.get(key);
					//获得数据集的字段对应的值
					String value=MapUtils.getString(dataMap, key, "");
					
					//created by wangchao20200507,没有值的才给默认值
					if(null == value|| "".equals(value)){
						//有默认值的先赋默认值
						if(null != colInfo.getIcColValue()){
							value = colInfo.getIcColValue();
						}else{
							value="";
						}
					}
					
					
	//				Map<String,Object> openDaysMap = Maps.newHashMap();
					//UpdateDate 基金净值日期  货币类取T-2 ,封闭净值类、权益类、FOF类、多币种类运作期取ta表数据，其他类型取T-1
					if(Const.UpdateDate.equals(key.toUpperCase())){
						if(Const.FUND_TYPE_01.equals(fundType)||Const.FUND_TYPE_02.equals(fundType)||Const.FUND_TYPE_03.equals(fundType)){						
							value = lastT2WorkDay;
							LOGGER.info(fundCode+"的基金净值日期为"+lastT2WorkDay);
						}else if(Const.FUND_TYPE_05.equals(fundType)
								  ||Const.FUND_TYPE_07.equals(fundType)
	                              ||Const.FUND_TYPE_08.equals(fundType)
	                              ||Const.FUND_TYPE_09.equals(fundType)){
							if(Integer.parseInt(tradeDate)>Integer.parseInt(proSetupDate)){
								netValueMap = Maps.newHashMap();
								netValueMap.put("productCode", taProductCode);
							    netValueMap = fundMarketService.selectPfGslFundCodeInCome(netValueMap);
								if(null != netValueMap && !netValueMap.isEmpty()){
									if(null!=netValueMap.get("D_DATE") && !"".equals(netValueMap.get("D_DATE"))){
										value = MapUtils.getString(netValueMap, "D_DATE", "");	
										LOGGER.info(fundCode+"的基金净值日期为"+value);	
									}else{
										LOGGER.info(fundCode+"查询ta净值日期的sql数据的D_DATE值为空");
										retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00018,fundCode+"查询ta净值日期的sql数据的D_DATE值为空");	
										return retMap;
									}
									
								}else{
									LOGGER.info(fundCode+"查询ta净值日期的sql返回值为空");
									retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00018,fundCode+ExceptionConStants.retMsg_S00018);	
									return retMap;
								}	
							}else{
								value = lastT1WorkDay;	
							}
							
						}else{
							value = lastT1WorkDay;	
							LOGGER.info(fundCode+"的基金净值日期为"+lastT1WorkDay);
						}
					}
					
					//NAV 基金单位净值 权益类产品、多币种类、FOF类和净值类产品净值运作期需要从crm_tfundday里面获取最近一个日期的
					if(Const.StringNAV.equals(key.toUpperCase())){
						if(Const.FUND_TYPE_05.equals(fundType)||Const.FUND_TYPE_07.equals(fundType)
								                              ||Const.FUND_TYPE_08.equals(fundType)
								                              ||Const.FUND_TYPE_09.equals(fundType)){
							if(Integer.parseInt(tradeDate)>Integer.parseInt(proSetupDate)){
								netValueMap = Maps.newHashMap();
								netValueMap.put("productCode", taProductCode);
							    netValueMap = fundMarketService.selectPfGslFundCodeInCome(netValueMap);
								if(null != netValueMap && !netValueMap.isEmpty()){
									if(null!=netValueMap.get("F_NETVALUE") &&  !"".equals(netValueMap.get("F_NETVALUE"))){
										value = MapUtils.getString(netValueMap, "F_NETVALUE", "");	
										LOGGER.info(fundCode+"的基金净值为"+value);	
									}else{
										LOGGER.info(fundCode+"查询ta净值的sql数据的F_NETVALUE值为空");
										retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00019,fundCode+"查询ta净值的sql数据的F_NETVALUE值为空");
										return retMap;
									}
									
								}else{
									LOGGER.info(fundCode+"查询ta净值的sql返回值为空");
									retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00019,fundCode+ExceptionConStants.retMsg_S00019);
									return retMap;
								}	
							}else{
								value = colInfo.getIcColValue();
							}
						}
					}
					//TODO AccumulativeNAV 20200121 国君需要累计单位净值
					if("ACCUMULATIVENAV".equals(key.toUpperCase())){
						netValueMap = Maps.newHashMap();
						netValueMap.put("productCode", taProductCode);
					    netValueMap = fundMarketService.selectPfGslFundCodeInCome(netValueMap);
					    if(null != netValueMap && !netValueMap.isEmpty()){
					    	//cereted by wangchao20200509基金累计净值为空时，给默认值1
							value = MapUtils.getString(netValueMap, "F_TOTALNETVALUE", "1");	
							LOGGER.info(fundCode+"的基金累计净值为"+value);
						}
					}
					
					//七日年化和万份收益取值
					if(Const.FundIncome.equals(key.toUpperCase())||Const.Yield.equals(key.toUpperCase())){
						incomeYieldMap = Maps.newHashMap();
						incomeYieldMap.put("C_FUNDCODE", taProductCode);
						incomeYieldMap.put("channelCode", channelCode);
						
						if(Const.FUND_TYPE_01.equals(fundType)||Const.FUND_TYPE_02.equals(fundType)||Const.FUND_TYPE_03.equals(fundType)||Const.FUND_TYPE_00.equals(fundType)){						
							incomeYieldMap.put("D_DATE", lastT2WorkDay);
							incomeYieldMap = fundMarketService.getIncomeInfo(incomeYieldMap);
							
						} else {
							incomeYieldMap.put("D_DATE", lastT1WorkDay);
							incomeYieldMap = fundMarketService.selectPfGslFundCodeInCome(incomeYieldMap);
						}
						
						if ("0".equals(remainShares)) {
							if(Const.FundIncome.equals(key.toUpperCase())){
								value = "";
								LOGGER.info(fundCode+"持仓为0,万份收益为0");
								
							} else if(Const.Yield.equals(key.toUpperCase())){
								value = "";
								LOGGER.info(fundCode+"持仓为0,七日年化为0");
							}
							
						} else {
							if(incomeYieldMap != null && !incomeYieldMap.isEmpty()){
								if(Const.FUND_TYPE_01.equals(fundType)||Const.FUND_TYPE_02.equals(fundType)||Const.FUND_TYPE_03.equals(fundType)){
									//货币类的万份收益不能为0
									if(Double.parseDouble(MapUtils.getString(incomeYieldMap, "F_INCOMEUNIT", "0.00"))==0&&Const.FundIncome.equals(key.toUpperCase())){
										retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00013,fundCode+ExceptionConStants.retMsg_S00013);
										return retMap;
									}else if(Const.FundIncome.equals(key.toUpperCase())){
										value = MapUtils.getString(incomeYieldMap, "F_INCOMEUNIT", "0");
										LOGGER.info(fundCode+"万份收益为"+value);
									}
									
									//货币类的七日年化不能为0
									if(Double.parseDouble(MapUtils.getString(incomeYieldMap, "F_INCOMERATIO", "0.00"))==0&&Const.Yield.equals(key.toUpperCase())){
										retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00015,fundCode+ExceptionConStants.retMsg_S00015);
										return retMap;
									}else if(Const.Yield.equals(key.toUpperCase())){
										value = MapUtils.getString(incomeYieldMap, "F_INCOMERATIO", "0");
										LOGGER.info(fundCode+"七日年化为"+value);
									}
									
								}else{
									if(Const.FundIncome.equals(key.toUpperCase())){
										value = MapUtils.getString(incomeYieldMap, "F_INCOMEUNIT", "0");
										
									}else if(Const.Yield.equals(key.toUpperCase())){
										value = MapUtils.getString(incomeYieldMap, "F_INCOMERATIO", "0");
									}
								}
								
							} else {
								
								if (Const.FUND_TYPE_01.equals(fundType) || Const.FUND_TYPE_02.equals(fundType)
										|| Const.FUND_TYPE_03.equals(fundType)) {
									
									// 货币类的万份收益不能为空
									if (Const.FundIncome.equals(key.toUpperCase())) {
										retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00012,
												fundCode + ExceptionConStants.retMsg_S00012);
										return retMap;
									
									}
		
									// 货币类的七日年化不能为空
									if (Const.Yield.equals(key.toUpperCase())) {
										retMap = ExceptionConStants.getRetObjects(ExceptionConStants.retCode_S00014,
												fundCode + ExceptionConStants.retMsg_S00014);
										return retMap;
										
									}
								} else {
									value = "";
								}
							}

						}
						
					}
					
					//行情确认表插入数据字段
					insertFundMarketCFMMap.put("FM"+key, value);
					
					//字段长度
					Integer colLength = Integer.parseInt(colInfo.getIcColLength());
					//字段小数位
					String colDecimal = colInfo.getIcColDecimal();
					Integer decimalLen = 0;
					if(!StringUtils.isEmpty(colDecimal)){
						decimalLen = Integer.parseInt(colDecimal);
					}
					
					//数据类型
					String colType = colInfo.getIcColType();
					//基金净值日期、定时定额申购日期、权益登记日期、除权日、基金发起人、交易价格、分红日/发放日、下一开放日这几个字段特殊处理  不能补0只能补空格
					if(decimalLen != 0 && !Const.specialProcessing.contains(key)){
						value = FileDataUtil.exchangeRedixPoint(value, decimalLen, colLength);
						
					}else{
						if((Const.FILED_TYPE_A.equals(colType) || Const.FILED_TYPE_N.equals(colType))&&!Const.specialProcessing.contains(key)){
							value = FileDataUtil.getParameterNumCN(value, colLength);
						}else{
							value = FileDataUtil.getParameterCN(value, colLength);
						}
					}
					
					String icColOrder=colInfo.getIcColOrder();
					try {
						retMap = CheckFileUtil.checkfileInterfaceField(value,key,colInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String retCode=MapUtils.getString(retMap, "retCode", "");
					if(!ExceptionConStants.retCode_0000.equals(retCode)){
						outputMap.putAll(retMap);
						outputMap.put("retMsg", MyMapUtils.getStringToLog(dataMap,"ChannelCode")+"渠道的"+MyMapUtils.getStringToLog(dataMap,"FundName")+"产品,"+MyMapUtils.getStringToLog(retMap,"RETVALUE"));
						checkFiledData=false;
						return outputMap;
					}
					outFileMap.put(icColOrder, (Object)value);
				}
				outFileMap.put("CSDCVER", "7"+MapUtils.getString(dataMap, "CSDCVER", Const.FILE_VER_21));
				outFileMap.put("CHANNELCODE", MapUtils.getString(dataMap, "CHANNELCODE", ""));
				outFileMap.put("TRADEDATE", MapUtils.getString(dataMap, "TRADEDATE", ""));
				outFileMap.put("FUNDCODE", MapUtils.getString(dataMap, "FUNDCODE", ""));
				
				//个人当日累计购买最大金额IndiDayMaxSumBuy 法人当日累计购买最大金额 InstDayMaxSumBuy 最高募集金额-（该产品累计持仓*净值）
	//			String IndiDayMaxSumBuy = MapUtils.getString(insertFundMarketCFMMap, "fm"+Const.IndiDayMaxSumBuy, "0"); 
				//String NAV = MapUtils.getString(insertFundMarketCFMMap, "FM"+Const.StringNAV, Const.NAV); 
				String NAV = MyMapUtils.getStringForDefault(insertFundMarketCFMMap, "FM"+Const.StringNAV, Const.NAV);
				
				//最高募集金额(保留两位小数)
				BigDecimal maxSumBuy = new BigDecimal(maxAmountRaised).subtract(new BigDecimal(remainShares).multiply(new BigDecimal(NAV))).setScale(2, BigDecimal.ROUND_UP);
				InterfaceColInfo colInfoIndiDayMaxSumBuy = filedMap.get(Const.IndiDayMaxSumBuy);
				InterfaceColInfo colInfoInstDayMaxSumBuy = filedMap.get(Const.InstDayMaxSumBuy);
				//最高募集金额负数的情况给0
				if(maxSumBuy.compareTo(new BigDecimal("0"))==-1){
					maxSumBuy = new BigDecimal("0");
				}
				
				//行情状态表入库map
				insertFundMarketCFMMap.put("FM"+Const.IndiDayMaxSumBuy, maxSumBuy);
				insertFundMarketCFMMap.put("FM"+Const.InstDayMaxSumBuy, maxSumBuy);
				String IndimaxSumBuy = FileDataUtil.exchangeRedixPoint(maxSumBuy.toString(), Integer.parseInt(colInfoIndiDayMaxSumBuy.getIcColDecimal()), Integer.parseInt(colInfoIndiDayMaxSumBuy.getIcColLength()));
				String InstmaxSumBuy = FileDataUtil.exchangeRedixPoint(maxSumBuy.toString(), Integer.parseInt(colInfoInstDayMaxSumBuy.getIcColDecimal()), Integer.parseInt(colInfoInstDayMaxSumBuy.getIcColLength()));
				
				LOGGER.info(fundCode+"个人当日累计购买最大金额IndiDayMaxSumBuy 法人当日累计购买最大金额 InstDayMaxSumBuy"+maxSumBuy);

				try {
					retMap = CheckFileUtil.checkfileInterfaceField(InstmaxSumBuy,Const.IndiDayMaxSumBuy,colInfoIndiDayMaxSumBuy);
					if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))){
						retMap.put("retMsg", channelCode+"渠道的"+fundCode+"产品,"+MyMapUtils.getStringToLog(retMap,"RETVALUE"));
						return retMap;
					}

				} catch (Exception e) {
					e.printStackTrace();
					return ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999,e.getMessage()); 
				}
				//数据文件信息map
				outFileMap.put(colInfoIndiDayMaxSumBuy.getIcColOrder(), (Object)IndimaxSumBuy);
				outFileMap.put(colInfoInstDayMaxSumBuy.getIcColOrder(), (Object)InstmaxSumBuy);
				LOGGER.info("第五步校验数据的输出参数为"+outputMap.toString());
				outputList.add(outFileMap);
				//行情确认表数据插入
				int flag=insertFundMarketCFM(insertFundMarketCFMMap);
				if(flag==0){
					outputMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00009));
					return outputMap;
				}
			}
		}
		outputMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000,outputList));
		return outputMap;
	}
	
	public Map<String,Object> checkOutputList(List<Map<String,Object>> outputList){
		Map<String,Object> retMap = Maps.newHashMap();
		if(outputList==null||outputList.size()==0){
			retMap = ExceptionConStants
					.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("retMsg", "根据接口字段基本信息校验数据记录步骤时输出的list为空");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("outputList", outputList);
		}
		return retMap;
	}
	
	
	

	/**
	 * 获得写入文件的数据信息
	 * @Title: getFileDataInfo   
	 * @author: yindy 2019年4月10日 下午3:57:38
	 * @param: @param list
	 * @param: @param map
	 * @param: @return      
	 * @return: HashMap<String,Object>      
	 * @throws
	 */
	@Override
	public Map<String,Object> getFileDataInfo(Map<String,Object> inputMap) {
		List<Map<String, Object>> list=(List<Map<String, Object>>) inputMap.get("RETVALUE");
		int listCount = (list==null?0:list.size());
		Map<String,Object> retMap = new HashMap<String, Object>();
		boolean caninsertFundMarketCFM=true;
		List<Map<String,Object>> outputList=Lists.newArrayList();
		Map<String,String> filedsMap = Maps.newHashMap();
		String channelCode=MapUtils.getString(inputMap, "CHANNELCODE","");
		String businessDate=MapUtils.getString(inputMap, "TRADEDATE","");

		String fileType="";
		LOGGER.info("接口字段基本信息校验完毕数据已拿到");
		StringBuilder dataInfo = new StringBuilder();
		dataInfo.append(FileDataUtil.exchangeRedixPoint(listCount+"", 0, 8));
		dataInfo.append(Const.FILE_ENTER);
		if(null != list && list.size() > 0){
			for (Map<String, Object> dataMap : list) {
				channelCode=(String)dataMap.get("CHANNELCODE");
				businessDate=(String)dataMap.get("TRADEDATE");
				fileType=(String)dataMap.get("CSDCVER");
				String fundCode=(String)dataMap.get("FUNDCODE");
				
				Map<String,Object> outputMap = new HashMap<String, Object>();
				Map<String,Object> paramMap = Maps.newHashMap();
				paramMap.put("ICINTERFACECODE", fileType);
				retMap = checkColOrderList(paramMap);
				if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
				List<InterfaceColInfo> colOrderList = (List)retMap.get("colOrderList");
				Map<String,Object> insertFundMarketCFMMap=Maps.newHashMap();
				insertFundMarketCFMMap.put("FMBUSINESSDATE", businessDate);
				insertFundMarketCFMMap.put("FMCHANNELCODE", channelCode);
				insertFundMarketCFMMap.put("FMDATAGENERATE", DateUtils.getOracleSysDate());
				for(InterfaceColInfo interfaceColInfo:colOrderList){
					String value=MyMapUtils.getString(dataMap,interfaceColInfo.getIcColOrder());
					filedsMap.put(interfaceColInfo.getIcColOrder(), interfaceColInfo.getIcColName());
					insertFundMarketCFMMap.put("FM"+interfaceColInfo.getIcColName(), value);
					dataInfo.append(value);
				}
				dataInfo.append(Const.FILE_ENTER);
			}
		}else{
			Map<String,Object> paramMap = Maps.newHashMap();
			fileType=MapUtils.getString(inputMap, "CSDCVER");
			paramMap.put("ICINTERFACECODE", fileType);
			retMap = checkColOrderList(paramMap);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
			List<InterfaceColInfo> colOrderList = (List)retMap.get("colOrderList");
			for(InterfaceColInfo interfaceColInfo:colOrderList){				
				filedsMap.put(interfaceColInfo.getIcColOrder(), interfaceColInfo.getIcColName());			
			}
		}

		dataInfo.append(Const.FILE_OFDCFEND);
		LOGGER.info("行情文件行情数据拼接完毕");
		retMap.put("RETVALUE", dataInfo);
		if(caninsertFundMarketCFM){
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			retMap.put("COLFILEDS", filedsMap);
			retMap.put("CHANNELCODE",channelCode);
			retMap.put("LISTTOTAL",listCount);
			if(fileType.length()==3){
				fileType="0"+fileType.substring(0, 1);
			}
			retMap.put("FILETYPE",fileType);
			retMap.put("TRADEDATE",businessDate);
			
		}else{
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00009));
		}
		retMap.put("TRADEDATE", businessDate);
		LOGGER.info("第六步输出参数为日期:"+businessDate+"渠道"+channelCode+"数据量"+list.size()+"条");
		return retMap;
	}
	
	public Map<String,Object> checkColOrderList(Map<String,Object> paramMap){
		Map<String,Object> retMap=Maps.newHashMap();
		SysDictService sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
		fileInterfaceFieldService=(FileInterfaceFieldService) SpringUtils.getBean(FileInterfaceFieldService.class);
		List<InterfaceColInfo> colOrderList = fileInterfaceFieldService.selectFileInterfaceFieldOrder(paramMap);
		if(colOrderList==null||colOrderList.equals("")){
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("retMsg", "通过"+paramMap+"查到的InterfaceColInfo集合为空");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("colOrderList", colOrderList);
		}
		return retMap;
	}
	
	public int insertFundMarketCFM(Map<String,Object> insertFundMarketCFMMap){
		FundMarketCfmServier fundMarketCfmServier=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
		return fundMarketCfmServier.insertSelective(insertFundMarketCFMMap);
	}
	
	public int deleteFundMarketCFM(String ChannelCode,String BusinessDate,String FundCode){
		FundMarketCfmServier fundMarketCfmServier=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
		FundMarketCfm fundMarketCfm=new FundMarketCfm();
		fundMarketCfm.setFmChannelCode(ChannelCode);
		fundMarketCfm.setFmBusinessDate(BusinessDate);
		fundMarketCfm.setFmFundCode(FundCode);
		return fundMarketCfmServier.deleteFundMarketCFM(fundMarketCfm);
	}


	@Override
	public Map<String, Object> fileDataAssembly(Map<String, Object> map) {
		SysDictService sysDictService=(SysDictService) SpringUtils.getBean(SysDictService.class);
		String FtpLocalUploadDir= sysDictService.findDictVo("ftplocaldir")+File.separator+Const.FTP_UPLOAD+File.separator;
		Map<String,Object> retMap = Maps.newHashMap();
		int listTotal=(int)map.get("LISTTOTAL");
		boolean flag = LocalUtils.checkDiskSize(FtpLocalUploadDir);
		if(flag){
			LOGGER.info("磁盘空间没有超过使用范围");
			map.put("FILETYPE", Const.FILE_TYPE_07);
			retMap=generateFile(map,FtpLocalUploadDir);
			if(!(ExceptionConStants.retCode_0000).equals(retMap.get("retCode")))return retMap;
		}else{
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_LA0001);
			LOGGER.info(((String)retMap.get("FtpLocalUploadDir")).substring(0,2)+"空间不足，请清理磁盘空间再进行操作");
		}
		
		if(listTotal==updateGenerateStatus(map)){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
		}else{
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00010);
		}
		retMap.putAll(map);
		retMap.put("TRANSDATE", retMap.get("TRADEDATE"));
		retMap.put("FILETYPE", Const.FILE_TYPE_07);
		channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
		PchannelInfo info =  channelInfoService.queryChannelInfoByChannelCode(MyMapUtils.getStringToSql(retMap,"CHANNELCODE"));
		retMap.put("CHANNELINFO", info);
		return retMap;
	}
	
	
	public Map<String,Object> generateFile(Map<String,Object> map,String FtpLocalUploadDir){
		accountReqCfmService=(AccountReqCfmService) SpringUtils.getBean(AccountReqCfmService.class);
		Map<String,Object> retMap = new HashMap<String,Object>();
		Map<String,Object> intoMap = new HashMap<String,Object>();
		
		String channelCode = (String)map.get("CHANNELCODE");//渠道编号
		String businessDate = (String)map.get("TRADEDATE");//处理日期
		String fileType =(String)map.get("FILETYPE");//文件类型
		PchannelInfo channelInfo = (PchannelInfo)selectChannelInfoByChannelCode(channelCode).get("CHANNELINFO");//渠道信息
		
		intoMap.put("CHANNELINFO", channelInfo);
		intoMap.put("FILETYPES", fileType);
		intoMap.put("CHANNELCODE", channelCode);
		intoMap.put("TRANSDATE", businessDate);
		
		//查询确认数据并生成文件
		retMap = accountReqCfmService.selectAndWriteFile(intoMap);
		retMap.putAll(map);
		/*//数据文件的头部信息
		StringBuilder stringBuilder = this.buildFileTopInfo(fileType, businessDate, channelInfo);
		//拿到数据文件需要拼接的字段的信息
		Map<String,String> ColFiledsMap = (Map)map.get("COLFILEDS");
		//数据文件的字段信息
		stringBuilder = this.buildFileColFileds(ColFiledsMap, stringBuilder);
		//拿到数据文件字段的数据信息
		String data = MapUtils.getString(map,"RETVALUE");
		String dataInfo = stringBuilder.toString()+data;
		//备份文件
		//accountReqCfmService.backUpAndDelFile(channelCode,businessDate);
		FileUtils.copyFile(FtpLocalUploadDir+File.separator+channelInfo.getCiChannelCode()+File.separator+businessDate, businessDate);
	    dataFileName =FtpLocalUploadDir+File.separator+channelInfo.getCiChannelCode()+File.separator+businessDate+File.separator+this.getFileName(fileType, businessDate, channelInfo);
		//将数据文件名称新建成文件
		File dataFile = new File(dataFileName);
		FileUtils.ifMakeDir(dataFileName.substring(0,dataFileName.lastIndexOf(File.separator)));
		//将数据信息写入数据文件
		map=FileDataUtil.writeFile(dataFile, dataInfo);
		if(!(ExceptionConStants.retCode_0000).equals(map.get("retCode")))return map;
		//索引文件名称
		String indexFileName =FtpLocalUploadDir+File.separator+channelInfo.getCiChannelCode()+File.separator+businessDate+File.separator+this.getIndexFileName(fileType, businessDate, channelInfo);
		//索引文件内容
		String indexInfo = this.buildIndexfileInfo(fileType, businessDate, channelInfo);
		//将索引文件名称新建成文件
	    File indexFile = new File(indexFileName);
	    //将索引文件信息写入索引文件
	  	map=FileDataUtil.writeFile(indexFile, indexInfo);
	  	if(!(ExceptionConStants.retCode_0000).equals(map.get("retCode")))return map;
	  	try {
			Thread.sleep(1000);
			LOGGER.info("生成正式07文件后睡眠1s");
		} catch (InterruptedException e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
	  //数据ok文件名称
	  	String dataOkName = this.getOkFileName(dataFileName);
	    //将数据文件的ok文件名称新建成文件
	    File dataOk = new File(dataOkName);
	    //将数据ok文件信息写入数据ok文件
	    map=FileDataUtil.writeFile(dataOk, "");
	    if(!(ExceptionConStants.retCode_0000).equals(map.get("retCode")))return map;
	    //索引ok文件名称
	  	String indexOkName = this.getOkFileName(indexFileName);
	    //将ok文件名称新建成文件
	    File indexOk = new File(indexOkName);
	    //将索引ok文件信息写入索引ok文件
	    map=FileDataUtil.writeFile(indexOk, "");*/
		return retMap;
	    
	}
	
	public int updateGenerateStatus(Map<String, Object> map){
		fundMarketCfmServier=(FundMarketCfmServier) SpringUtils.getBean(FundMarketCfmServier.class);
		String channelCode = (String)map.get("ChannelCode");
		String businessDate = (String)map.get("BusinessDate");
//		String fileType =(String)map.get("FileType");
		FundMarketCfm fundMarketCfm =new FundMarketCfm();
		fundMarketCfm.setFmChannelCode(channelCode);
		fundMarketCfm.setFmBusinessDate(businessDate);
		fundMarketCfm.setFmGenerateStatus("01");
		fundMarketCfm.setFmGenerateFileTime(DateUtils.getOracleSysDate());
		return fundMarketCfmServier.updateByChannelCode(fundMarketCfm);
	}
	
	/**
	 * 下载渠道的申请文件 
	 * @Title: downLoadApplyData   
	 * @author: yindy 2019年4月16日 上午8:55:17
	 * @param: @param date
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public Map<String, Object> downLoadFile(Map<String, Object> intoMap) {
		Map<String, Object> retMap = new HashMap<String, Object>(); 
		String channelCode = (String)intoMap.get("CHANNELCODE");
		pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		LOGGER.info("通过渠道编码"+channelCode+"查询的渠道信息为："+channelInfo);
		if(channelInfo == null ){
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_RF0012));
			retMap.put("retMsg", "通过渠道编码"+channelCode+ExceptionConStants.retMsg_RF0012);
		}
		intoMap.put("CHANNELINFO", channelInfo);
		try {
	    	if(FTPUtils.client==null){
	    		ftp.getInstance();
	    	}
	    	retMap = ftp.downloadConFile(intoMap);
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("下载文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "downLoadFile方法,下载文件异常,请联系管理员!");
			return retMap;
		}
		LOGGER.info("通过"+intoMap.toString()+"下载文件的结果为:"+retMap.toString());
		intoMap.putAll(retMap);
		return intoMap;
	}
	
	/**
	 * 查询需要处理用户文件的渠道   
	 * @Title: queryHandelChannelInfo   
	 * @author: yindy 2019年4月15日 上午11:13:56
	 * @param: @param channelInfo
	 * @param: @return      
	 * @return:      
	 * @throws
	 */
	@Override
	public List<PchannelInfo> queryHandelChannelInfo(Map channelInfo) {
		List<PchannelInfo> list = pchannelInfoMapper.queryHandelChannelInfo(channelInfo);
		return list;
	}

	/**
	 * 获得字典的参数
	 * @Title: getFTPConnectInfo   
	 * @author: yindy 2019年4月16日 上午9:42:59
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@Override
	public Map<String, Object> getDictInfo() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		List<SysDict> list = sysDictMapper.findByCondition(new HashMap<String, Object>());
		for (SysDict sysDict : list) {
			retMap.put(sysDict.getDictType(), sysDict.getDictCode());
		}
		return retMap;
	}

	public List<Map<String, Object>> queryErrorDetail(String channelCode,
			String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CHANNELCODE", channelCode);
		map.put("DATE", date);
		List<Map<String, Object>> list = pchannelInfoMapper.queryErrorDetail(map);
		return list;
	}

	/**
	 * UpdateDate 基金净值日期  货币类取T-2 其他类型取T-1
	 * @Title: getUpdateDate   
	 * @author: wangc 2019年4月16日 上午9:42:59
	 * @param: @return      
	 * @return: String    
	 * @throws
	 */
	public String getUpdateDate(String tradeDate,int num,String channelCode){		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			Date date = sf.parse(tradeDate);
			c.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, num);
		String updateDate = sf.format(c.getTime());
		CloseDate closeDate = new CloseDate();
		channelInfoService=(ChannelInfoService) SpringUtils.getBean(ChannelInfoService.class);
		Map<String,Object> codeMap = Maps.newHashMap();
		codeMap.put("CHANNELCODE", channelCode);
		PchannelInfo info =  channelInfoService.selectByChannelCode(codeMap);
		closeDate.setCdCloseDate(updateDate);
		closeDate.setCdMarketCode(info.getCiMarketCode());
		closeDateServiceImpl=(CloseDateService) SpringUtils.getBean(CloseDateService.class);
		//返回值 0 为交易日  1 不为交易日
		int closeNum = closeDateServiceImpl.selectByMarketCodeAndcdCloseDate(closeDate);
		//如果不是交易日，继续向前取日期，直至该天为交易日
		if(closeNum!=0){
			updateDate = getUpdateDate(tradeDate,num-1,channelCode);
		}
		return updateDate;
	}

    /**
     * @Description 通过文件类型编号获取对应字段的中文名 & 长度 or 其他信息
     * @Author weijunjie
     * @Date 2019/11/14 11:09
     **/
    public List<Map<String,Object>> getColumnsTitle(String fileType){
        List<Map<String,Object>> fundMsg= new ArrayList<>();
        String fundCode = getFundCodeByFileType(fileType, "21");
        if("error".equals(fundCode)){
            return fundMsg;
        }else{
            List<InterfaceColInfo> interfaceColInfos = interfaceColInfoMapper.selectColInfoByCode(fundCode);
            for(int i = 1;i<interfaceColInfos.size()+1;i++){
                HashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
                for(InterfaceColInfo interfaceColInfo :interfaceColInfos){
                    int o = Integer.parseInt(interfaceColInfo.getIcColOrder());
                    if(i == o){
                        String icColLength = interfaceColInfo.getIcColLength();
                        int a =Integer.parseInt(icColLength);
                        int b = interfaceColInfo.getIcColName().length();
                        int z = a*10 > b*10?a*10:b*10;
                        hashMap.put("titleNameEn",interfaceColInfo.getIcColName().toLowerCase());
						hashMap.put("titleNameCn",interfaceColInfo.getIcColDesc());
						hashMap.put("titleField",interfaceColInfo.getIcColName().toUpperCase());
						//长度超过500，定义长度最大为500
						z = z > 500?500:z;
                        hashMap.put("titleWidth",z);
                        fundMsg.add(hashMap);
                        break;
                    }
                }
            }
            return fundMsg;
        }
    }

	/**
	 * @Description 通过文件类型编号获取对应字段的中文名 & 长度 or 其他信息
	 * @Author weijunjie
	 * @Date 2019/11/14 11:09
	 **/
	public List<Map<String,Object>> getCnNameByfundCode(String fileType){
        List<Map<String,Object>> fundMsg= new ArrayList<>();
	    String fundCode = getFundCodeByFileType(fileType, "21");
        if("error".equals(fundCode)){
            return fundMsg;
        }else{
            List<InterfaceColInfo> interfaceColInfos = interfaceColInfoMapper.selectColInfoByCode(fundCode);
            HashMap<String, Object> titleHashMapCn = new HashMap<>();
//            HashMap<String, Object> titleHashMapLen = new HashMap<>();
            for(InterfaceColInfo interfaceColInfo :interfaceColInfos){
                titleHashMapCn.put(interfaceColInfo.getIcColName().toUpperCase(), interfaceColInfo.getIcColDesc());
//                titleHashMapLen.put(interfaceColInfo.getIcColName().toUpperCase(),interfaceColInfo.getIcColLength());
            }
            fundMsg.add(titleHashMapCn);
            return fundMsg;
        }
    }

	/**
	 * @Description 通过文件类型获取对应的中等接口版本号（默认21）
	 * @Author weijunjie
	 * @Date 2019/11/14 11:11
	 **/
	public String getFundCodeByFileType(String s,String version){
		version = version == null?"21":version;
		String res = "";
		switch (s){
			case "44":res = "044000";break;
			case "04":res = "004130";break;
			case "07":res = "000007";break;
			case "02":res = "002101";break;
			case "01":res = "001001";break;
			case "03":res = "003020";break;
			case "43":res = "043000";break;
			case "R1":res = "091000";break;
			case "05":res = "005000";break;
            case "06":res = "006143";break;
            case "R2":res = "092000";break;
            case "94":res = "094022";break;
			case "26":res = "026000";break;
			default:res = "error";break;
		}
		if(res.equals("error")){return res;}
		else{ return res+version;}
	}
	
}
