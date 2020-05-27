package com.sams.common.web.controller;

import com.sams.common.shiro.ShiroUser;
import com.sams.common.web.Result;
import com.sams.custom.mapper.ProductTemplateMapper;
import com.sams.sys.model.SysUser;
import com.sams.sys.service.SysUserService;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础 controller
 * @author hq 2016-6-31 21:12:18
 */
public abstract class BaseController extends Controller{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private ProductTemplateMapper productTemplateMapper;
    
    /**
	 * 获取当前用户session
	 * @return session
	 */
	public static Session getSession(){
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}
    
    /**
	 * 获取当前用户对象shiro
	 * @return shirouser
	 */
	public static ShiroUser getShiroUser(){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
    /**
     * 获取当前登录用户对象
     * @return
     */
    public SysUser getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        SysUser currentUser = sysUserService.selectByKey(user.userId);
        return currentUser;
    }
    
    /**
     * 获取当前登录用户id
     * @return
     */
    public String getUserId() {
        return getShiroUser().getUserId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getLoginName() {
        return getShiroUser().getLoginName();
    }
    
    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object resultSuccess(String msg) {
    	Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
    
    public Date getSysDate(){
    	 Date date = productTemplateMapper.getSysDate();
    	 return date;
    }

    /**
     * Excel文件输出到浏览器提供下载
     *
     * @param workBook
     * @param response
     * @param fileName
     * @throws Exception
     */
    public static void writeExcelToWeb(Workbook workBook, HttpServletResponse response, String fileName) throws Exception {
        OutputStream out = null;
        try {
            response.reset();// 清空输出流
            String filename8859 = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            String filenameutf8 = null;
            try {
                filenameutf8 = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + filename8859 + ";filename*=utf-8''" + filenameutf8);//设定输出文件头
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            out = response.getOutputStream();
            workBook.write(out);

        } catch (Exception e) {

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new Exception();
                }
            }
        }
    }

    
}