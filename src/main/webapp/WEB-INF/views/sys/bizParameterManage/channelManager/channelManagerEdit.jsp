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
        	
        	$('#cmChannelCode').combobox({  
                 onSelect: function (row) {
               	 var options = $(this).combobox('options');
               	 var name = row.NAME
             	 $('#ciChannelName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                }
            });
       	 
       	 $('#cmManagerCode').combobox({  
       	        onSelect: function (row) {
         	    var options = $(this).combobox('options');
         	    var name = row.NAME
        	    $('#cmManagerName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                }
            }); 
        	
            $('#editChannelManager').form({
                url : '${ctx}/channelManager/editSubmit',
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

        function openIconAllHtml(){
       		window.open("${staticPath}/icon/group/icon-all.html");
       	}
</script>
	
	<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editChannelManager" method="post" >
			<table  style="margin: auto">
				<tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input id="cmChannelCode"   name ="cmChannelCode" value="${entity.cmChannelCode}"   class="easyui-combobox"  style = "width:250px;" prompt="请选择"  value = "" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     " >
					    <input id="cmId" name="cmId" type="hidden" value="${entity.cmId}"></td>
					    <input id="cmCuser" name="cmCuser" type="hidden" value="${entity.cmCuser}">
					    <input id="cmCtime" name="cmCtime" type="hidden" value="${entity.cmCtime}">
					    <input id="cmUuser" name="cmUuser" type="hidden" value="${entity.cmUuser}">
					    <input id="cmUtime" name="cmUtime" type="hidden" value="${entity.cmUtime}">
					    <input id="cmValidFlag" name="cmValidFlag" type="hidden" value="${entity.cmValidFlag}">
					    <input id="cmCheckState" name="cmCheckState" type="hidden" value="${entity.cmCheckState}"></td>
				</tr>
				<tr>
					<td>渠道名称:<span style="color:red">*</span></td>
					<td><input id="ciChannelName" name="ciChannelName" class="easyui-textbox" type="text"  value="${entity.ciChannelName}" data-options="validType:'length[1,200]'"></td>
				</tr>
				<tr>
					<td>客户经理代码:<span style="color:red">*</span></td>
					<td><input id="cmManagerCode"   name ="cmManagerCode"  class="easyui-combobox" value="${entity.cmManagerCode}"  style = "width:250px;" prompt="请选择"  value = "" data-options="
					     url:'${ctx}/combobox/queryTaManagerComBox',
					     valueField:'CODE',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "></td>
				</tr>
				
				<tr>
					<td>客户经理名称:<span style="color:red">*</span></td>
					<td><input id="cmManagerName" name="cmManagerName" class="easyui-textbox" type="text"  value="${entity.cmManagerName}" data-options="validType:'length[1,200]'"></td>
				</tr>
				
				<tr>
				    <td>开始时间:<span style="color:red">*</span></td>
				    <td><input class="easyui-datebox" id="cmStartDate" name="cmStartDate" value="${entity.cmStartDate}" editable="fasle"/></td>
			    </tr>
			    
			    <tr>
				    <td>结束时间:</td>
				    <td><input class="easyui-datebox" id="cmEndDate" name="cmEndDate"  value="${entity.cmEndDate}" editable="fasle"/></td>
			    </tr>
				</table>
				
		</form>
    </div>
</div>