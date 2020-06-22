<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
        $(function() {
        	//dateBox控件加清除按钮
        	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
            buttons.splice(1, 0, {
                text: '清除',
                handler: function (target) {
                    $(target).datebox("setValue","");
                }
            });
            $('.easyui-datebox').datebox({
                buttons: buttons
            });
        	
        	//初始化加载
        	$('#cprChannelCode').combobox({  
        		onChange: function (row) {
           	        var channelCode = $("#cprChannelCode").combobox("getValue"); 
                    $('#cprFundCode').combobox({
                    	url: '${ctx}/combobox/queryProductInfoComBox',
                        onBeforeLoad:function(param){
                        	param.channelCode = channelCode;
					     },
					    valueField:'ID',
				        extField:'NAME'
                    });
                }

            });
        	
        	$('#cprFundCode').combobox({  
        		onSelect: function (row) {  
        			var name = row.NAME;
                	$('#cprFundName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                }  
            }); 
            $('#editChannelProductReal').form({
                url : '${ctx}/channelProductReal/editSubmit',
                onSubmit : function() {
                	 progressLoad();
                	 var isValid = $(this).form('validate');
                	 var cprChannelCode = $("#cprChannelCode").combobox('getValue');
                     var cprFundCode = $("#cprFundCode").combobox('getValue');
                     var cprSectionNumber = $("#cprSectionNumber").textbox('getValue');
                     var cprFundRate = $("#cprFundRate").textbox('getValue');
                     var cprStartMoney = $("#cprStartMoney").numberbox('getValue');
                     var cprEndMoney = $("#cprEndMoney").numberbox('getValue');
                     if(cprChannelCode==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '渠道编号必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(cprFundCode==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '代销端产品代码必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(cprSectionNumber==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '受益级别必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(cprFundRate==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '收益率必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(cprStartMoney==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '起始金额必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(cprEndMoney==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '终止金额必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
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
                        $.messager.show({
                            title : '提示',
                            msg : result.msg,
                            timeout : 1000
                        });
                    } else {
                        parent.$.messager.alert('错误', result.msg, 'error');
                    }
                }
            });
        });

</script>
	
	<div id ="fundModelPanel" style ="overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editChannelProductReal" method="post" >
			<table  class="grid">
				<tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input id="cprId" name="cprId" type="hidden" value="${entity.cprId}">
					    <input id="cprCuser" name="cprCuser" type="hidden" value="${entity.cprCuser}">
					    <input id="cprCtime" name="cprCtime" type="hidden" value="${entity.cprCtime}">
					    <input id="cprUuser" name="cprUuser" type="hidden" value="${entity.cprUuser}">
					    <input id="cprUtime" name="cprUtime" type="hidden" value="${entity.cprUtime}">
					    <input id="cprValidFlag" name="cprValidFlag" type="hidden" value="${entity.cprValidFlag}">
						<input class="easyui-combobox"  style = "width:250px;" prompt="请选择"  id="cprChannelCode"   name ="cprChannelCode" value = "${entity.cprChannelCode}" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',required:true" >
						
					</td>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td>代销端产品代码:<span style="color:red">*</span></td>
					<td><input class="easyui-combobox"  style = "width:250px;" prompt="请选择"  id="cprFundCode"   name ="cprFundCode" value = "${entity.cprFundCode}" data-options="
					     url:'${ctx}/combobox/queryProductInfoComBox' ,
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',required:true" />
					</td>
					<td>代销端产品名称:</td>
					<td><input id="cprFundName" name="cprFundName" class="easyui-textbox" value="${entity.piChannelProductName}"></td>
				</tr>
				
				<tr>
					<td>受益级别:<span style="color:red">*</span></td>
					<td><input id="cprSectionNumber" name="cprSectionNumber" value="${entity.cprSectionNumber}" class="easyui-textbox" data-options="required:true"/></td>
					<td>产品收益率:<span style="color:red">*</span></td>
					<td><input id="cprFundRate" name="cprFundRate"  value="${entity.cprFundRate}" class="easyui-numberbox" data-options="required:true,precision:4"></td>
				</tr>
				
				<tr>
					<td>起始金额:<span style="color:red">*</span></td>
					<td><input id="cprStartMoney" name="cprStartMoney"  value="${entity.cprStartMoney}" class="easyui-numberbox" data-options="required:true"/></td>
					<td>终止金额:<span style="color:red">*</span></td>
					<td><input id="cprEndMoney" name="cprEndMoney"  value="${entity.cprEndMoney}" class="easyui-numberbox" data-options="required:true"/></td>
				</tr>
				</table>
				
		</form>
    </div>
</div>