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
                    	url: '${ctx}/combobox/queryUsedProductInfoComBox',
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
        	
            $('#addChannelProductReal').form({
                url : '${ctx}/channelProductReal/addSubmit',
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
        <form id="addChannelProductReal" method="post" >
			<table  class="grid">
				<tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td>
						<input class="easyui-combobox"  style = "width:250px;" prompt="请选择"  id="cprChannelCode"   name ="cprChannelCode" value = "" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',required:true" >
						
					</td>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td>代销端产品代码:<span style="color:red">*</span></td>
					<td><input class="easyui-combobox"  style = "width:250px;" prompt="请选择"  id="cprFundCode"   name ="cprFundCode" value = "" data-options="
					     url:'${ctx}/combobox/queryProductInfoComBox' ,
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',required:true" />
					</td>
					<td>代销端产品名称:</td>
					<td><input id="cprFundName" name="cprFundName" class="easyui-textbox"></td>
				</tr>
				
				<tr>
					<td>受益级别:<span style="color:red">*</span></td>
					<td><input id="cprSectionNumber" name="cprSectionNumber" value="0" class="easyui-textbox" data-options="required:true"/></td>
					<td>产品收益率:<span style="color:red">*</span></td>
					<td><input id="cprFundRate" name="cprFundRate" class="easyui-numberbox" data-options="required:true,precision:4"></td>
				</tr>
				
				<tr>
					<td>起始金额:<span style="color:red">*</span></td>
					<td><input id="cprStartMoney" name="cprStartMoney" value="0" class="easyui-numberbox" data-options="required:true"/></td>
					<td>终止金额:<span style="color:red">*</span></td>
					<td><input id="cprEndMoney" name="cprEndMoney" value="9999999999" class="easyui-numberbox" data-options="required:true"/></td>
				</tr>
				</table>
		</form>
    </div>
</div>