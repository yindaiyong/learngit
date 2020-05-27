<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
       
</script>
	
	<div id ="fundModelPanel" style ="overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editChannelProductReal" method="post" >
        			<table  class="grid">
				<tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input id="cprId" name="cprId" type="hidden" value="${entity.cprId}" readonly="readonly">
						<input class="easyui-combobox"  style = "width:250px;" readonly="readonly" prompt="请选择"  id="cprChannelCode"   name ="cprChannelCode" value = "${entity.cprChannelCode}" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',required:true" >
						
					</td>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td>代销端产品代码:<span style="color:red">*</span></td>
					<td><input class="easyui-combobox"  style = "width:250px;" readonly="readonly" prompt="请选择"  id="cprFundCode"   name ="cprFundCode" value = "${entity.cprFundCode}" data-options="
					     url:'${ctx}/combobox/queryProductInfoComBox',
					     valueField:'ID',
					     textField:'NAME',required:true" />
					</td>
					<td>代销端产品名称:</td>
					<td><input id="cprFundName" name="cprFundName" readonly="readonly" class="easyui-textbox" value="${entity.piChannelProductName}"></td>
				</tr>
				
				<tr>
					<td>受益级别:<span style="color:red">*</span></td>
					<td><input id="cprSectionNumber" name="cprSectionNumber" readonly="readonly" value="${entity.cprSectionNumber}" class="easyui-textbox" data-options="required:true"/></td>
					<td>产品收益率:<span style="color:red">*</span></td>
					<td><input id="cprFundRate" name="cprFundRate" readonly="readonly"  value="${entity.cprFundRate}" class="easyui-numberbox" data-options="required:true,precision:4"></td>
				</tr>
				
				<tr>
					<td>起始金额:<span style="color:red">*</span></td>
					<td><input id="cprStartMoney" name="cprStartMoney" readonly="readonly"  value="${entity.cprStartMoney}" class="easyui-numberbox" data-options="required:true"/></td>
					<td>终止金额:<span style="color:red">*</span></td>
					<td><input id="cprEndMoney" name="cprEndMoney" readonly="readonly" value="${entity.cprEndMoney}" class="easyui-numberbox" data-options="required:true"/></td>
				</tr>
				</table>
        
				
		</form>
    </div>
</div>