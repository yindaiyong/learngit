package com.sams.batchfile.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.common.ITask;
import com.sams.batchfile.common.ReadFileUtil;
import com.sams.batchfile.common.ResultBean;
import com.sams.batchfile.service.AccountReqCfmService;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.batchfile.service.FileDataService;
import com.sams.batchfile.service.FundMarketProcessorService;
import com.sams.batchfile.service.ProcessStepInfoService;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.Reflections;
import com.sams.custom.mapper.PchannelInfoMapper;
import com.sams.custom.mapper.ProcessStepInfoMapper;
import com.sams.custom.model.AccountReqCfm;
import com.sams.custom.model.PchannelInfo;
import com.sams.custom.model.ProcessStepInfo;
import com.sams.sys.service.SysDictService;
/**
 * @ClassName:  AccountReqCfmServiceImpl   
 * @author: yindy
 * @date:   2020年3月19日 下午3:20:03   
 *
 */
@Service
public class AccountReqCfmServiceImpl implements ITask<ResultBean<String>, Integer>, AccountReqCfmService{

	
	private static Logger LOGGER = LoggerFactory.getLogger(AccountReqCfmServiceImpl.class);
	
	@Autowired
	private ProcessStepInfoService processStepInfoService;
	
	@Autowired
	private FundMarketProcessorService fundMarketProcessorService;
	
	@Autowired
	private ProcessStepInfoMapper processStepInfoMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Autowired
	private  PchannelInfoMapper  pchannelInfoMapper;
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private FileDataService fileDataService;
	
	@Override
	public List<AccountReqCfm> selectByChannelAndSeriaNo(Map<String, Object> map) {
		return null;
	}

	/**
	 * 执行日终的确认处理流程  
	 * @Title: execute   
	 * @author: yindy 2019年6月19日 上午9:21:25
	 * @param: @param channelCode 渠道编号
	 * @param: @param params transdate 日期   
	 * @return:  执行过的渠道号
	 */
	@Override
	public ResultBean<String> execute(String channelCode, Map<String, Object> params) {
		pchannelInfoMapper = (PchannelInfoMapper) SpringUtils.getBean(PchannelInfoMapper.class);
		PchannelInfo channelInfo = pchannelInfoMapper.queryChannelInfoByChannelCode(channelCode);
		Map <String,Object> intoMap = Maps.newHashMap();
		//获得当前处理的日期
		String transDate = (String)params.get("TRANSDATE");
		ResultBean<String> resultBean = ResultBean.newInstance();
		//获得上一个工作日的最新的操作记录是否是最后一个步骤并且是成功的
		boolean isSuccessEndStep = checkLastWorkDayIsSuccess(channelInfo,transDate);
		if(isSuccessEndStep){
			//判断该渠道该日期是否是工作日，不是工作日不作处理
			boolean flag = exchangeProcessorService.checkDateIsWork(transDate,channelCode);
			if(flag){
				intoMap.put("CHANNELINFO", channelInfo);
				intoMap.put("CHANNELCODE", channelCode);
				intoMap.putAll(params);
				//错误步骤
				String errorStep = (String)intoMap.get("ERRORSTEP");
				//操作步骤   重做该步 强制通过 
				Integer operType = (Integer)intoMap.get("OPERTYPE"); 
				LOGGER.info("开始调用确认文件处理方法，传入的参数为："+(intoMap == null ? null : intoMap.toString())+"错误步骤："+operType+",操作类型："+operType);
				confirmProcessor(intoMap,errorStep,operType);
				resultBean.setData(channelCode);
			}else{
				LOGGER.info(channelCode+"渠道,"+transDate+"为非工作日。");
				resultBean.setMsg(channelCode+"渠道,"+transDate+"为非工作日。");
			}
		}else{
			LOGGER.info(channelCode+"渠道,上一工作日还有数据未处理结束!");
			resultBean.setMsg(channelCode+"渠道,上一工作日还有数据未处理结束!");
		}
		
        return resultBean;
	}
	/**
	 * 校验该渠道上一工作日数据时否处理完成   
	 * @Title: checkLastWorkDayIsSuccess   
	 * @author: yindy 2019年12月10日 下午3:47:32
	 * @param: @param channelInfo
	 * @param: @param transDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	private boolean checkLastWorkDayIsSuccess(PchannelInfo channelInfo,
			String transDate) {
		String channelCode = channelInfo.getCiChannelCode();
		String marketCode = channelInfo.getCiMarketCode();
		String queryDate = exchangeProcessorService.getLastWorkDay(DateUtils.getLastDay(transDate),marketCode);
		Map<String, Object> map = processStepInfoMapper.queryDiffValueForMaxToLastStep(channelCode,queryDate);
		LOGGER.info(channelCode+"渠道"+queryDate+"最大的步骤数与最新的步骤数之差及状态为"+(map == null ? null : map.toString()));
		if(map == null || ("0".equals(MapUtils.getString(map, "STEP")) && "01".equals(MapUtils.getString(map, "STAT")))){
			return true;
		}
		return false;
	}

	/**
	 * 查询确认数据并生成文件 
	 * @Title: selectAndWriteFile   
	 * @author: yindy 2019年6月6日 下午1:16:34 
	 * @param: @param intoMap channelinfo 渠道号  fileTypes 要生成的文件类型  transdate 时间
	 * @return: Map<String,Object>    返回执行结果   
	 */
	@Override
	public Map<String, Object> selectAndWriteFile (Map<String, Object> intoMap){
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
		
			PchannelInfo channelInfo = (PchannelInfo)intoMap.get("CHANNELINFO");
			String fileTypes = (String)intoMap.get("FILETYPES");
			Map<String, Object> fileMap = new HashMap<String, Object>();
			//获得界面勾选的特定生成的文件
			List<String> list = FileDataUtil.getPageCheckFile(fileTypes);
			LOGGER.info(channelInfo.getCiChannelCode()+"渠道需要单独生成的文件类型为："+fileTypes);
			if(CollectionUtils.isEmpty(list)){
				//获得读取的要生成的文件类型
				list  = FileDataUtil.getReadFile(channelInfo,list);
				sysDictService = (SysDictService)SpringUtils.getBean(SysDictService.class);
				//查询勾选文件与生成文件映射
				fileMap = sysDictService.findSaleToTaMapper(Const.FILETYPE);
			}
			retMap.putAll(intoMap);
			//备份文件
			//backUpAndDelFile(channelCode,businessDate);
			LOGGER.info("需要生成文件的集合为："+(list == null ? null : list.toString()));
			for (String str : list) {
				String type = "";
				//前端勾选为空，读映射关系
				if(StringUtils.isEmpty(fileTypes)){ 
					type = (String) fileMap.get(str);
					retMap.put("TYPE", type);
				}else{
					type = str;
					retMap.put("TYPE",str);
				}
				exchangeProcessorService = (ExchangeProcessorService)SpringUtils.getBean(ExchangeProcessorService.class);
				
				//校验查询的渠道 taserilno 当天的数据有重复  20200110 yindaiyong   生产上出现重复
				Map<String, Object> map = exchangeProcessorService.serialNoSamsCheck(retMap);
				if(!ExceptionConStants.retCode_0000.equals(map.get("retCode"))) {return map;}
				
				//根据时间和文件类型查询要生成文件 的数据内容
				List<Map<String, Object>> dataList = exchangeProcessorService.querySendData(retMap);
				try {
					retMap.put("retValue", dataList); 
					// 组装并写出文件
					retMap = exchangeProcessorService.buildAndWriteData(retMap);
					if(!ExceptionConStants.retCode_0000.equals(retMap.get("retCode"))) {return retMap;}
				} catch (Exception e) {
					String error = FileDataUtil.getErrorMsg(e);
					LOGGER.error("生成文件错误"+error);
					retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
					retMap.put("retMsg", "selectAndWriteFile方法,生成文件异常,请联系管理员!");
					return retMap;
				}
			}
			retMap.putAll(intoMap);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000));
			return retMap;
			
		} catch (Exception e) {
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("生成文件错误"+error);
			retMap.putAll(ExceptionConStants.getRetObject(ExceptionConStants.retCode_9999));
			retMap.put("retMsg", "selectAndWriteFile方法,生成文件异常,请联系管理员!");
			return retMap;
		}
	}


	/**
	 * 确认 步骤
	 * @Title: confirmProcessor   
	 * @author: yindy 2019年5月22日 下午3:49:38
	 * @param: @param intoMap      
	 * @return: void      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public  void confirmProcessor(Map<String, Object> intoMap,String errorStep,Integer operType) {
		fundMarketProcessorService = (FundMarketProcessorService) SpringUtils.getBean(FundMarketProcessorService.class);
		LOGGER.info("日终确认处理流程开始,当前时间为:"+DateUtils.getDate());
		String userName = (String)intoMap.get("USERNAME");
		String specialFile = (String)intoMap.get("FILETYPES");
		String channelCode = (String)intoMap.get("CHANNELCODE");
		LOGGER.info(userName+"操作特殊单独处理文件:"+specialFile);
		Long confirmStart = DateUtils.getOracleSysDate().getTime();
		Map<String, Object> retMap = new HashMap<String, Object>();
		//存储单独处理文件步骤
		List<ProcessStepInfo> specialList = new ArrayList<ProcessStepInfo>();
		try {
			List<ProcessStepInfo> list = (List<ProcessStepInfo>)processStepInfoService.selectByProcessStepInfo(Const.ACCOUNT_CONFIRM_STEP,errorStep,operType,null).get("LISTREQ");
			if(!StringUtils.isEmpty(specialFile)){
				if(Const.FILE_TYPE_26.equals(specialFile)) {specialFile = Const.FILE_TYPE_05;}
				specialList = (List<ProcessStepInfo>)processStepInfoService.selectByProcessStepInfo(Const.ACCOUNT_CONFIRM_STEP,errorStep,operType,specialFile).get("LISTREQ");
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
				inputObj[0] = obj;
				LOGGER.warn("------------------------"+processName+"步骤消耗的的时间为:"+(DateUtils.getOracleSysDate().getTime()-start)+"ms--------------------");
				LOGGER.info("输出参数为:"+JSONObject.toJSONString(obj));
				retMap.put("PROCESSSTEPINFO", stepInfo);
				retMap.put("PROCESSSTARTTIME", DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
				retMap.put("BRANCHCODE", Const.ACCOUNT_CONFIRM_STEP);
				retMap.put("TRADEDATE", intoMap.get("TRANSDATE"));
				retMap.put("USERNAME", userName);
				retMap.putAll((Map<String, Object>)obj);
				fundMarketProcessorService.insertProcessLog(retMap,channelCode);
				if(!ExceptionConStants.retCode_0000.equals(((Map<String, Object>)obj).get("retCode"))) {
					LOGGER.info("处理结果："+obj);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = FileDataUtil.getErrorMsg(e);
			LOGGER.error("日终确认处理流程出错"+error);
		}
		LOGGER.info("日终确认处理流程完成,当前时间为:"+DateUtils.getDate());
		LOGGER.warn("整个日终确认耗时为:"+(DateUtils.getOracleSysDate().getTime()-confirmStart)+"ms");
	}
	
	/**
	 * 备份文件   
	 * @Title: backUpFile   
	 * @author: yindy 2019年7月29日 下午5:19:48
	 * @param: @param channelCode
	 * @param: @param businessDate      
	 * @return: void      
	 * @throws
	 */
	@Override
	public void backUpAndDelFile(String channelCode, String businessDate) {
		fileDataService = (FileDataService)SpringUtils.getBean(FileDataService.class);
		String filePath = fileDataService.getDictInfo().get(Const.FTP_LOCALDIR)+File.separator+Const.FTP_UPLOAD+File.separator;
		String path = filePath+channelCode+File.separator+businessDate;
		//备份上次的文件，然后删除
		ReadFileUtil.backupFile(path,filePath+"bakup"+File.separator+channelCode+File.separator+businessDate);
		ReadFileUtil.delFile(new File(path));
	}

}