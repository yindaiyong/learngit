<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>确认文件查看</title>
<script type="text/javascript">
	$(function(){
		var tradeDate = $('#tradeDate').datebox('getValue');
		//07文件展示
		$('#07confirmFile').datagrid({
			url : '${ctx}/fundMarket/tradeDate',
			pagination : false,
			rownumbers : true,
			fit :false,
			type:"POST",
			border : false,
			data : {
				"tradeDate":tradeDate
			}
		});
		$("#tb").appendTo('.datagrid-toolbar');
	});

	//按条件查询
	function start(){
		var tradeDate = $('#tradeDate').datebox('getValue');
		$('#07confirmFile').datagrid('load', {
			tradeDate : tradeDate,
		});
	}
	
	function reCreateClick(){
		var tradeDate = $('#tradeDate').datebox('getValue');
		var channels = $('#channelCodes').val();
		$.ajax({
	        url: "${ctx}/fundMarket/reCreate",
	        // 提交的页面
	        data: {"tradeDate":tradeDate,"channels":channels},
	        // 从表单中获取数据
	        type: "POST",
	        // 设置请求类型为"POST"，默认为"GET"
	        async: false,
	        beforeSend: function(){
	        	
	        },
	    });
	}
	
	function reSendClick(){
		var tradeDate = $('#tradeDate').datebox('getValue');
		var channels = $('#channelCodes').val();
		$.ajax({
	        url: "${ctx}/fundMarket/reSend",
	        // 提交的页面
	        data: {"tradeDate":tradeDate,"channels":channels},
	        // 从表单中获取数据
	        type: "POST",
	        // 设置请求类型为"POST"，默认为"GET"
	        async: false,
	        beforeSend: function(){
	        	
	        },
	    });
	}
	
	function clickTest(){
		var date = $("#tradeDate").val();
	       $.ajax({
	         url:"${ctx}/account/test",    //请求的url地址
	         dataType:"json",   //返回格式为json
	         async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	         data:{"date":date},    //参数值
	         type:"POST",   //请求方式
	       });
	} 
</script>
</head>
<body>
	<div style ="width:1000px;height:1000px;align:center;position:absolute;left:25%;
top:25%;">
		<table >
			<tr>
				<td>
					日期:<input class="easyui-datebox" id="tradeDate" name="tradeDate"/>
				</td>
				<td><input class="redbutton" type="button" id="getTest" value="开始" onclick="start()"></td>
										<td>
							<button id="test" onclick="clickTest()">测试</button>
						</td>
			</tr>
			<tr>
				<td><input class="redbutton" type="button" id="reCreate" value="重新生成" onclick="reCreateClick()"></td>
				<td><input class="redbutton" type="button" id="reSend" value="重新发送" onclick="reSendClick()"></td>
			</tr>
			<tr>
				<td><span>渠道号</span><input type="text" id="channelCodes" value="">例：AAAA,BBB 多个渠道重跑以逗号分隔</td>
			</tr>
		</table>
		<table id="07confirmFile" style="height:25px;"></table>
	</div>
	
	
	

</body>
</html>