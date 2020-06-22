<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
var entity ='${entity}'
</script>
	
	<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editChannelManager" method="post" >
			<table  style="margin: auto">
				<tr>
					<td>渠道代码:</td>
					<td><input id="cmChannelCode"   name ="cmChannelCode" value="${entity.cmChannelCode}"   class="easyui-combobox"  style = "width:250px;" prompt="请选择"  value = "" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',required:true" disabled="disabled">
					    <input id="cmId" name="cmId" type="hidden" value="${entity.cmId}"></td>
					    <input id="cmCuser" name="cmCuser" type="hidden" value="${entity.cmCuser}">
					    <input id="cmCtime" name="cmCtime" type="hidden" value="${entity.cmCtime}">
					    <input id="cmUuser" name="cmUuser" type="hidden" value="${entity.cmUuser}">
					    <input id="cmUtime" name="cmUtime" type="hidden" value="${entity.cmUtime}">
					    <input id="cmValidFlag" name="cmValidFlag" type="hidden" value="${entity.cmValidFlag}">
					    <input id="cmCheckState" name="cmCheckState" type="hidden" value="${entity.cmCheckState}"></td>
				</tr>
				<tr>
					<td>渠道名称:</td>
					<td><input id="ciChannelName" name="ciChannelName" class="easyui-textbox" type="text"  value="${entity.ciChannelName}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>客户经理代码:</td>
					<td><input id="cmManagerCode" name="cmManagerCode" class="easyui-textbox" type="text"  value="${entity.cmManagerCode}" disabled="disabled"></td>
				</tr>
				
				<tr>
					<td>客户经理名称:</td>
					<td><input id="cmManagerName" name="cmManagerName" class="easyui-textbox" type="text"  value="${entity.cmManagerName}" disabled="disabled"></td>
				</tr>
				
				<tr>
				    <td>开始时间:</td>
				    <td><input class="easyui-datebox" id="cmStartDate" name="cmStartDate" value="${entity.cmStartDate}" disabled="disabled"/></td>
			    </tr>
			    
			    <tr>
				    <td>结束时间:</td>
				    <td><input class="easyui-datebox" id="cmEndDate" name="cmEndDate"  value="${entity.cmEndDate}" disabled="disabled"/></td>
			    </tr>
				</table>
				
		</form>
    </div>
</div>