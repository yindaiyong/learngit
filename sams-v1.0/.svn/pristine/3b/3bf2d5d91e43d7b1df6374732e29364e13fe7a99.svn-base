<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> .dialog-button { padding: 5px; text-align: center; background-color: ' #E0ECFF;' } </style>
<head>
<title>多币种交易详情</title>
<script type="text/javascript">

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<table class="easyui-datagrid" style="width:auto;height:auto;">
	<thead>
	<tr>
        <th field="currencyType" align= "center"  width="110">币种</th>
        <th field="amount" align= "center"  width="100">金额</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${list}" var="item">
		<tr>
			<td>${item.CURRENCYTYPE }</td>
			<td>${item.APPLICATIONAMOUNT }</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</body>
</html>