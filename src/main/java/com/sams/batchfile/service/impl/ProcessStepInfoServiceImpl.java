package com.sams.batchfile.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sams.batchfile.service.*;
import com.sams.common.scanner.SpringUtils;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.utils.RandomizingID;
import com.sams.common.web.PageInfo;
import com.sams.custom.mapper.ChannelInfoMapper;
import com.sams.custom.mapper.ContractInfoMapper;
import com.sams.custom.model.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.sams.common.constant.Const;
import com.sams.common.exception.ExceptionConStants;
import com.sams.custom.mapper.ProcessStepInfoMapper;
import com.sams.sys.service.SysDictService;

@Service
public class ProcessStepInfoServiceImpl implements ProcessStepInfoService{
	private static Logger LOGGER = LoggerFactory.getLogger(ProcessStepInfoServiceImpl.class);
	
    @Autowired
    private ProcessStepInfoMapper processStepInfoMapper;

    @Autowired
    private ChannelInfoMapper channelInfoMapper;
    
    @Autowired
    private AccountReqTmpService accountReqTmpService;
    
    @Autowired
    private ExchangeReqTmpService exchangeReqTmpService;
    
    @Autowired
    private ContractInfoMapper contractInfoMapper;
    
    @Autowired
	private SysDictService sysDictService;
    
    
	
	/**
	 * 根据流程编号获取流程配置信息
	 * @param flowCode 处理流程编码
	 * @return List<P_process_step_info> 信息集合
	 */
	@Override
	public Map<String,Object> selectByProcessStepInfo(String flowCode,String stepCode,Integer operType,String specialFile)throws Exception {
		Map<String,Object> retMap = Maps.newHashMap();
		ProcessStepInfo pProcessStepInfo=new ProcessStepInfo();
		pProcessStepInfo.setPsiFlowCode(flowCode);
		pProcessStepInfo.setPsiProcessStep(stepCode);
		pProcessStepInfo.setOperType(operType);
		pProcessStepInfo.setSpecialFile(specialFile);
		List<ProcessStepInfo> listReq=processStepInfoMapper.selectByFlowCode(pProcessStepInfo);
		retMap.put("LISTREQ", listReq);
		return retMap;
	}
	
	/*public Map<String,Object> checkProcessStepInfoList(ProcessStepInfo pProcessStepInfo){
		Map<String,Object> retMap = Maps.newHashMap();
		List<ProcessStepInfo> listReq=processStepInfoMapper.selectByFlowCode(pProcessStepInfo);
		if(listReq==null||listReq.size()==0){
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00004);
			retMap.put("retMsg", "根据流程编号获取流程配置信息拿到的集合为空");
		}else{
			retMap = ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000);
			retMap.put("listReq", listReq);
		}
		
		return retMap;
	}*/

	/**
	 * 校验流程配置信息是否为空
	 * @param list<P_process_step_info> 流程配置信息信息集合
	 * @return Map<String, Object> 信息集合
	 */
	@Override
	public Map<String, Object> checkProcessStepInfoCount(List<ProcessStepInfo> list) {
		Map<String, Object> retMap=new HashMap<String, Object>();
		LOGGER.info("校验根据交易日期、产品类型获取非交易日开始");
		if(list.size()==0){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00001);
			LOGGER.info("校验根据交易日期、产品类型获取非交易日信息记录集为0");
		}else if(list==null){
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_S00006);
			LOGGER.info("校验根据交易日期、产品类型获取非交易日信息记录集为空");
		}else{
			retMap=ExceptionConStants.getRetObject(ExceptionConStants.retCode_0000,list);
		}
		LOGGER.info("校验根据交易日期、产品类型获取非交易日结束");
		return retMap;
	}

	@Override
	public List<DayEndProcessor> queryProcessStat(PageInfo pageInfo, Map condition) {
		PageHelperUtils.startPage(pageInfo);
		return processStepInfoMapper.queryStepInfo(Const.ACCOUNT_DATA_STEP,Const.ACCOUNT_CONFIRM_STEP,Const.SEND_FILE_STEP,condition);
	}

	@Override
	public List<ProcessStepInfo> selectByFlowCode(String branchCode) {
		ProcessStepInfo step = new ProcessStepInfo();
		step.setPsiFlowCode(branchCode);
		return processStepInfoMapper.selectByFlowCode(step);
	}

	/**
	 * 文件处理步骤信息
	 * @param processStepMap 流程配置信息信息集合
	 * @return Map<String, Object> 信息集合
	 */
	@Override
	public List<Map<String, Object>> processStepInfo(Map<String, Object> processStepMap) {
		return processStepInfoMapper.processStepInfo(processStepMap);
	}

	/**
	 * 步骤表单个处理步骤
	 * @param qryStepMap 流程配置信息信息集合
	 * @return Map<String, Object> 信息集合
	 */
	@Override
	public ProcessStepInfo selectStepInfo(Map<String, Object> qryStepMap) {
		return processStepInfoMapper.selectStepInfo(qryStepMap);
	}

	/**
	 * T日数据回退
	 * @Title: dataRoolback   
	 * @author: wangchao 2019年11月11日 下午18:13:40
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@Override
	public void dataRoolback(String channelCode,String transDate) {
		//删除账户申请表数据
		int accountSum = accountReqTmpService.deleteByChannelCodeBusinessDateReq(channelCode, transDate);
		LOGGER.info("渠道"+channelCode+"日期"+transDate+"的账户申请数据已删除"+accountSum+"条");
		//删除交易申请表数据
		int transSum = exchangeReqTmpService.deleteTransReqDate(channelCode, transDate);
		LOGGER.info("渠道"+channelCode+"日期"+transDate+"的交易申请数据已删除"+transSum+"条");
		//删除合同信息
		int contractSum = contractInfoMapper.deleteContractInfo(channelCode, transDate);
		LOGGER.info("渠道"+channelCode+"日期"+transDate+"的合同申请数据已删除"+contractSum+"条");
	}

	/**
	 * @Description 返回渠道下处理流程的某一个节点
	 * @Author weijunjie
	 * @Date 2020/4/21 10:38
	 **/
	public String returnProcessStep(String branchCode ,String transDate,String retStep ,String channelCode,String username){
		//删除处理流程表中步骤编号大于该步骤的处理
		transDate = transDate.contains("-")?transDate.replaceAll("-",""):transDate;
        int i = processStepInfoMapper.returnStep(branchCode, transDate, retStep, channelCode);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sf.format(DateUtils.getOracleSysDate());
        //判断当前步骤的处理结果状态
        ProcessingState processingState = processStepInfoMapper.selectStepState(branchCode, transDate, retStep, channelCode);
        RandomizingID randomizingID = new RandomizingID("yyyyMMddHHmmss",7,false);
        String errCode = randomizingID.genNewId();
        if(Integer.parseInt(processingState.getPsProcessStart()) ==1 ){
            //更新该步骤的操作信息以及状态
            if(StringUtils.isBlank(processingState.getPsErrorCode())){
                //添加一条错误信息数据
                RuningErrorService errorService = (RuningErrorService) SpringUtils.getBean(RuningErrorService.class);
                RuningError runningError = new RuningError();
                runningError.setReId(new BigDecimal(errCode));
                runningError.setReErrorCode(errCode);
                runningError.setReErrorMessage("手动回到该步");
                runningError.setReBranchCode(branchCode);
                runningError.setReErrorTime(DateUtils.getDate(DateUtils.FORMAT_STR_DATETIME19));
                runningError.setReProcessStep(retStep);
                errorService.insertRuningError(runningError);
                processStepInfoMapper.updateStepStatus(branchCode, transDate, retStep, channelCode,username,date,"00",errCode);
            }
        }
        if(i>0){
            return "success";
        }else{
            return "回到上步操作异常";
        }
    }

	@Override
	public List<String> queryHasHandelChannel(List<String> channelCodeList,
			String date,String flowCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CHANNELCODELIST", channelCodeList);
		map.put("TRANSDATE", date);
		map.put("FLOWCODE", flowCode);
		//数据处理步骤为配置节点,查询配置的节点,暂时的有效节点为1-8,判断逻辑为大于等于配置的节点
		String alertStep = sysDictService.findDictVo(Const.ALERT_STEP);
		if(!StringUtils.isEmpty(alertStep) && Const.ACCOUNT_DATA_STEP.equals(flowCode)){
			int length = alertStep.length();
			if(length ==1){
				alertStep = "0" + alertStep;
			}
			map.put("ALERTSTEP", alertStep);
		}
		return processStepInfoMapper.queryHasHandelChannel(map);
	}

}