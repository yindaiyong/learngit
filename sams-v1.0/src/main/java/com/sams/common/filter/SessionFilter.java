package com.sams.common.filter;

import com.sams.common.constant.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		//获取请求路径
		String requestURI = request.getRequestURI();
		//登陆超时
		if (request.getSession(false) == null || session.getAttribute(Const.SESSION_USER_ID) == null) {
			//Ajax请求
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				response.setHeader("sessionStatus", "timeout");
				return ;
			}
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
