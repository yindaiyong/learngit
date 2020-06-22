package com.sams.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sams.common.mapper.JsonMapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sams.common.shiro.ShiroUser;
import com.sams.common.utils.DateUtils;
import com.sams.common.utils.IPUtil;
import com.sams.common.utils.PageHelperUtils;
import com.sams.common.web.PageInfo;
import com.sams.common.web.service.BaseServiceImpl;
import com.sams.sys.mapper.SysLogMapper;
import com.sams.sys.model.SysLog;
import com.sams.sys.service.SysLogService;

import eu.bitwalker.useragentutils.UserAgent;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public List findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        PageHelperUtils.startPage(pageInfo);
        return sysLogMapper.findLogPageCondition(pageInfo,condition);
    }


    /**
     * 保存日志
     * @param request
     * @param log
     */
    @Override
    public void save(HttpServletRequest request, Boolean saveParament, SysLog log) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String os=userAgent.getOperatingSystem().getName();	//获取客户端操作系统
        String browser=userAgent.getBrowser().getName();	//获取客户端浏览器
        String operationCode=StringUtils.substringAfter(request.getRequestURI(),request.getContextPath());	//操作代号
		String requestParam=(new JsonMapper()).toJson(request.getParameterMap());	//请求参数
        SysLog sysLog =  new SysLog(log.getLogType(),log.getDescription());
        sysLog.setCreateDate(log.getCreateDate() != null ? log.getCreateDate() : DateUtils.getOracleSysDate());
        sysLog.setOperateCode(operationCode);
        if (saveParament){
            sysLog.setRequestParam(requestParam);
        }
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(shiroUser != null){
            sysLog.setUserId(shiroUser.getUserId());
            sysLog.setLoginName(shiroUser.getUserName());
        } else {    //未登录
            sysLog.setLoginName(log.getLoginName());
        }
        sysLog.setOs(os);
        sysLog.setBrowser(browser);
        String ip = IPUtil.getIpAddress(request);
        sysLog.setIp(ip);
        try {
            //sysLog.setMac(IPUtil.getMACAddress(ip));
        	sysLog.setMac("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        sysLog.setField1(sysLog.getField1()==null?"":sysLog.getField1());
        sysLog.setField2(sysLog.getField2()==null?"":sysLog.getField2());
        sysLog.setField3(sysLog.getField3()==null?"":sysLog.getField3());
        sysLog.setLogFlag(sysLog.getLogFlag()==null?"":sysLog.getLogFlag());
        sysLog.setRequestParam(sysLog.getRequestParam()==null?"":sysLog.getRequestParam());
        sysLog.setExecuteTime(sysLog.getExecuteTime()==null?DateUtils.getOracleSysDate().getTime():sysLog.getExecuteTime());
        super.save(sysLog);
    }
    
    @Override
	public void save(HttpServletRequest request, SysLog sysLog) {
		this.save(request, true, sysLog);
	}

}
