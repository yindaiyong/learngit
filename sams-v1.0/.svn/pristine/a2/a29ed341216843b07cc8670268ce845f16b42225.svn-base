package com.sams.csdcInterfaceConfig.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.batchfile.common.FileDataUtil;
import com.sams.batchfile.service.ExchangeProcessorService;
import com.sams.common.exception.ExceptionConStants;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.csdcInterfaceConfig.service.CsdcInterfaceService;
import com.sams.custom.mapper.InterfaceColInfoMapper;
import com.sams.custom.model.DayEndProcessor;
import com.sams.custom.model.InterfaceColInfo;
import com.sams.custom.model.InterfaceInfo;

@Service
public class CsdcInterfaceServiceImpl implements CsdcInterfaceService {

	
	@Autowired
	private InterfaceColInfoMapper interfaceColInfoMapper;
	
	@Autowired
	private ExchangeProcessorService exchangeProcessorService;
	
	@Override
	public List<InterfaceInfo> csdcInfoBaseList(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return interfaceColInfoMapper.csdcInfoBaseList(pageInfo, condition);
	}

	@Override
	public Integer queryCsdcBaseInfo(InterfaceInfo interfaceInfo) {
		return  interfaceColInfoMapper.queryCsdcBaseInfo(interfaceInfo);
	}

	@Override
	public int insert(InterfaceInfo interfaceInfo) {
		return  interfaceColInfoMapper.insert(interfaceInfo);
	}


	@Override
	public InterfaceInfo queryCsdcBaseInfoByPrimary(long iiId) {
		return  interfaceColInfoMapper.queryCsdcBaseInfoByPrimary(iiId);
	}

	@Override
	public int updateCsdcBaseInfo(InterfaceInfo interfaceInfo) {
		return  interfaceColInfoMapper.updateByPrimaryKey(interfaceInfo);
	}

	@Override
	public String insertOrUpdateCsdcBaseInfo(InterfaceInfo interfaceInfo,String userName) {
		String msg = "";
		String prefix = "";
		Long iiId = interfaceInfo.getIiId();
		if(iiId != null){
			prefix = ExceptionConStants.UPDATE;
		}else{
			prefix = ExceptionConStants.INSERT;
		}
		//查询是否有重复数据
		Integer count = queryCsdcBaseInfo(interfaceInfo);
		if(count.intValue() == 0 ){
			int num  = 0;
			if(iiId != null){
				//修改
				interfaceInfo.setIiUuser(userName);
				interfaceInfo.setIiUtime(DateUtils.getOracleSysDate());
				num = updateCsdcBaseInfo(interfaceInfo);
			}else{
				//插入
				interfaceInfo.setIiCuser(userName);
				interfaceInfo.setIiCtime(DateUtils.getOracleSysDate());
				num = insert(interfaceInfo);
			}
			if(num >0){
				msg = prefix+ExceptionConStants.RETMSG_CSDC02;
			}else{
				msg = prefix+ExceptionConStants.RETMSG_CSDC03;
			}
		}else{
			msg = ExceptionConStants.RETMSG_CSDC01;
		}
		return msg;
	}

	@Override
	public List<InterfaceColInfo> csdcInfoColList(PageInfo pageInfo,
			Map<String, Object> condition) {
		PageHelperUtils.startPage(pageInfo);
		return interfaceColInfoMapper.csdcInfoColList(pageInfo, condition);
	}

	@Override
	public InterfaceColInfo queryCsdcColInfoByPrimary(long icId) {
		return  interfaceColInfoMapper.queryCsdcColInfoByPrimary(icId);
	}

	@Override
	public String insertOrUpdateCsdcColInfo(InterfaceColInfo interfaceColInfo,String userName) {
		String msg = "";
		String prefix = "";
		Long icId = interfaceColInfo.getIcId();
		if(icId != null){
			prefix = ExceptionConStants.UPDATE;
		}else{
			prefix = ExceptionConStants.INSERT;
		}
		//查询是否有重复数据
		Integer count = queryCsdcColInfo(interfaceColInfo);
		if(count.intValue() == 0 ){
			int num  = 0;
			if(icId != null){
				//修改
				interfaceColInfo.setIcUuser(userName);
				num = updateCsdcColInfo(interfaceColInfo);
			}else{
				//插入
				interfaceColInfo.setIcCuser(userName);
				num = insertColInfo(interfaceColInfo);
			}
			if(num >0){
				msg = prefix+ExceptionConStants.RETMSG_CSDC02;
			}else{
				msg = prefix+ExceptionConStants.RETMSG_CSDC03;
			}
		}else{
			msg = ExceptionConStants.RETMSG_CSDC01;
		}
		return msg;
	}

	private int insertColInfo(InterfaceColInfo interfaceColInfo) {
		//查询顺序
		Integer order = interfaceColInfoMapper.queryMaxOrderByInterfaceCode(interfaceColInfo.getIiInterfaceCode());
		order = order+1;
		interfaceColInfo.setIcColOrder(String.valueOf(order));
		interfaceColInfo.setIcColCode(FileDataUtil.getIntParameter(String.valueOf(order),6));
		interfaceColInfo.setIcCtime(DateUtils.getOracleSysDate());
		return  interfaceColInfoMapper.insertColInfo(interfaceColInfo);
	}

	private int updateCsdcColInfo(InterfaceColInfo interfaceColInfo) {
		//查询顺序
		interfaceColInfo.setIcUtime(DateUtils.getOracleSysDate());
		return  interfaceColInfoMapper.updateCsdcColInfo(interfaceColInfo);
	}

	private Integer queryCsdcColInfo(InterfaceColInfo interfaceColInfo) {
		InterfaceColInfo info = new InterfaceColInfo();
		info = interfaceColInfo;
		info.setIcColName(info.getIcColName().toLowerCase());
		return  interfaceColInfoMapper.queryCsdcColInfo(interfaceColInfo);
	}

	@Override
	public List<DayEndProcessor> selectDayEndChannelStatus(String date) {
		if(StringUtils.isEmpty(date)){
			date = DateUtils.getDate(DateUtils.FORMAT_STR_DATE8);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", date);
		List<DayEndProcessor> list = exchangeProcessorService.processorStepList(new PageInfo(),map);
		for (DayEndProcessor dto : list) {
			if("00".equals(dto.getProcessStat()) || "00".equals(dto.getConfirmProcessStat()) 
					|| "00".equals(dto.getSendProcessStat())){
				dto.setFinalStat("00");
			}else if("01".equals(dto.getProcessStat()) && "01".equals(dto.getConfirmProcessStat())
					&& "01".equals(dto.getSendProcessStat())){
				dto.setFinalStat("01");
			}else{
				dto.setFinalStat("02");
			}
		}
		return list;
	}
}
