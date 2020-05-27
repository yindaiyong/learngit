<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
$(function(){
	$('#icColType').combobox({
		panelHeight: 'auto',//自适应
	     valueField: 'id',//绑定字段ID
	     textField: 'name',//绑定字段Name
	     onLoadSuccess:function(){
	         $(".combo").click(function(){
	            $(this).prev().combobox("showPanel");
	        });
	     },
	     data :[{"id":"A","name":"A(数字字符型)"},{"id":"C","name":"C(字符型)"},{"id":"N","name":"N(数值型)"},{"id":"TEXT","name":"TEXT(不定长文本)"}]
	});
	$('#icColRequ').combobox({
		panelHeight: 'auto',//自适应
	     valueField: 'id',//绑定字段ID
	     textField: 'name',//绑定字段Name
	     onLoadSuccess:function(){
	         $(".combo").click(function(){
	            $(this).prev().combobox("showPanel");
	        });
	     },
	     data :[{"id":"1","name":"必填"},{"id":"0","name":"非必填"}]
	});
	setComboxValue("icRequOnOff");
	setComboxValue("icRuleOnOff");
	var type = '${type}';
	if(type == 2){
		$('#addAndEditCsdcCol').find('input').attr('readonly',true);
	}
	$('#addAndEditCsdcCol').form({
		url:'${ctx}/csdcCol/saveCsdcColInfoEdit',
		onSubmit : function() {
            progressLoad();
            var isValid = $(this).form('validate');
            if (!isValid) {
                progressClose();
            }
            return isValid;
        },
        success : function(result) {
            progressClose();
            result = $.parseJSON(result);
            if (result.success) {
            	parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                $.messager.alert({
                    title : '提示',
                    msg : result.msg,
                    timeout : 2000
                });
            } else {
                parent.$.messager.alert('错误', result.msg, 'error');
            }
        }
	});
});
function setComboxValue(id){
	$("#"+id+"").combobox({
		panelHeight: 'auto',//自适应
	     valueField: 'id',//绑定字段ID
	     textField: 'name',//绑定字段Name
	     onLoadSuccess:function(){
	         $(".combo").click(function(){
	            $(this).prev().combobox("showPanel");
	        });
	     },
	     data :[{"id":"01","name":"开"},{"id":"00","name":"关"}]
	});
}
</script>
     <div data-options="region:'center',border:false" style ="background-color : #E0ECFF;overflow:auto;height:auto;padding:5px;">
   		<form id = "addAndEditCsdcCol" method = "post">
   		 <table style = "margin-bottom: 30px;">
               <tr>
                   <th>接口名称:</th>
                   <td><input id="icInterfaceCode" style = "width:300px;" name="icInterfaceCode" class="easyui-combobox" value = "${icInterfaceCode }" data-options="
				url:'${ctx}/combobox/queryBaseCsdc',
				valueField:'ID',
				textField:'NAME',required:true" /></td>
               </tr>
           </table>
   			<table style="margin: auto;text-align:center">
			<tr>
				<td>字段名</td>
				<td>字段描述</td>
				<td>类型</td>
				<td>长度</td>
				<td>小数位</td>
				<td>是否必填</td>
				<td>格式化</td>
				<td>数据字典</td>
				<td>默认值</td>
				<td>必填开关</td>
				<td>规则开关</td>
			</tr>
			<tr>
				<td><input type="hidden" id = "icId" name = "icId" value = ${entity.icId }><input style="width:120px;" class="easyui-validatebox" data-options="required:true"  id="icColName" name ="icColName" value = "${entity.icColName}"></td>
				<td><input style="width:120px;" class="easyui-validatebox"  id="icColDesc" name ="icColDesc"  value = "${entity.icColDesc}"></td>
				<td><input style="width:120px;" class="easyui-combobox" data-options="required:true"   id="icColType" name ="icColType"  value = "${entity.icColType}"></td>
				<td><input style="width:120px;" class="easyui-validatebox" data-options="required:true"  id="icColLength" name ="icColLength"  value = "${entity.icColLength}"></td>
				<td><input style="width:120px;" class="easyui-validatebox" data-options="required:true"  id="icColDecimal" name ="icColDecimal"  value = "${entity.icColDecimal}"></td>
				<td><input style="width:120px;" class="easyui-combobox" data-options="required:true"   id="icColRequ" name ="icColRequ"  value = "${entity.icColRequ}"></td>
				<td><input style="width:120px;" class="easyui-validatebox"  id="icColRule" name ="icColRule" value = "${entity.icColRule}"></td>
				<td><input style="width:120px;" class="easyui-validatebox"  id="icColDict" name ="icColDict" value = "${entity.icColDict}"></td>
				<td><input style="width:120px;" class="easyui-validatebox"  id="icColValue" name ="icColValue" value = "${entity.icColValue}"></td>
				<td><input style="width:120px;" class="easyui-combobox" data-options="required:true"   id="icRequOnOff" name ="icRequOnOff"  value = "${entity.icRequOnOff}"></td>
				<td><input style="width:120px;" class="easyui-combobox" data-options="required:true"   id="icRuleOnOff" name ="icRuleOnOff"  value = "${entity.icRuleOnOff}"></td>
			</tr>
		</table>
   		</form>
   	</div>
