<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sams.common.constant.Global"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	request.setAttribute("adminPath", Global.getAdminPath());
%>
<html>
	<head>
		<title>上海国际信托代销管理系统</title>
		<script src="${ctx}/static/plugins/easyui/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css" />
		<script type="text/javascript">
			$(function() {
				$("document").ready(function(){
					//防止在frame里面出现登录页面
					if(top.location != self.location){
						top.location.href=self.location.href;
					}
				});
			});
			var captcha;
			/* function refreshCaptcha(){
				document.getElementById("img_captcha").src="${ctx}/static/images/kaptcha.jpg?t=" + Math.random() * 9000 + 1000;
			} */
		</script>
	</head>
	<body style="background-image: url('${ctx}/static/images/timg.jpg');background-size:100% 100%;background-repeat:no-repeat;">
		<div class="main">
		<img src="${ctx}/static/images/logo.png" width="405" height="60" style="margin-left: 30px;"/>
			<div class="login">
				<div class="inset">
					<form id="loginForm" action="${ctx}${adminPath}/login" method="post">
						<div>
							<h2>${msg}</h2>
							<span><label>用户名</label></span>
							<span><input type="text" name="username" class="textbox" ></span>
						</div>
						<div>
							<span><label>密码</label></span>
							<span><input type="password" name="password" class="password" ></span>
						</div>
						<%-- <div>
							<span><label>验证码</label></span>
									<span style="padding-top: 5px">
										<input type="text" name="captcha" class="text" value="" style="width: 50%;margin: 0px"/>
										<img alt="验证码" src="${ctx}/static/images/kaptcha.jpg" style="height:43px;width:39%;margin-left: 3px;vertical-align:bottom;" title="点击更换" id="img_captcha" onclick="javascript:refreshCaptcha();" />
									</span>
						</div> --%>
						<div class="sign">
							<input type="submit" value="登录" class="submit" />
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="copy-right">
			<p>上海信托版权所有 &copy; Copyright © 2019-2020 </p>
		</div>
	</body>
</html>