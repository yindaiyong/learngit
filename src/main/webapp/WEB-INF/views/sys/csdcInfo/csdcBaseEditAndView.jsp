<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
	$(function() {
		//给combox赋值
		$('#iiInterfaceVer').combobox({
		     panelHeight: 'auto',//自适应
		     valueField: 'id',//绑定字段ID
		     textField: 'name',//绑定字段Name
		     onLoadSuccess:function(){
		         $(".combo").click(function(){
		            $(this).prev().combobox("showPanel");
		        });
		     },
		     data :[{
		         "id":"20",
		         "name":"20"
		     },{
		         "id":"21",
		         "name":"21"
		     }],
		});
		
		var type = '${type}';
		if(type != 0){ //不是新增
			$('#iiInterfaceCode').attr('readOnly',true);
		}
		if(type==2){ //查看
			$('#iiInterfaceName').attr('readOnly',true);
			$('#iiInterfaceVer').attr('readOnly',true);
			$('#iiInterfaceDesc').attr('readOnly',true);
		}
		
		$('#csdcBaseEditAndView').form({
			url:'${ctx}/csdc/saveCsdcBaseInfoEdit',
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
</script>
	<div data-options="region:'center',border:false" style ="background-color : #E0ECFF;overflow:auto;height:auto;padding:5px;">
		<form id ="csdcBaseEditAndView" method="post">
			<table style="margin: auto">
				<tr>
					<td>接口编号:</td>
					
					<td><input type="hidden" id = "iiId" name = "iiId" value = "${entity.iiId}"/><input class="easyui-validatebox" data-options="required:true,validType:['length[0,8]']"  id="iiInterfaceCode" name ="iiInterfaceCode" type= "text" value = "${entity.iiInterfaceCode}"/></td>
				<tr>
				<tr>
					<td>接口名称:</td>
					
					<td><input class="easyui-validatebox" data-options="required:true"  id="iiInterfaceName" name ="iiInterfaceName"  type= "text" value ="${entity.iiInterfaceName}"/></td>
				<tr>
				<tr>
					<td>接口版本:</td>
					
					<td><input class="easyui-combobox" data-options="required:true,validType:['length[0,2]']"   id="iiInterfaceVer" name ="iiInterfaceVer"  type= "text" value ="${entity.iiInterfaceVer}"/></td>
				<tr>
				<tr>
					<td>接口描述:</td>
					
					<td><textarea id="iiInterfaceDesc" name ="iiInterfaceDesc"  style ="height:100px;width:200px;">${entity.iiInterfaceDesc}</textarea></td>
					
				<tr>
			</table>
		</form>
	</div>
