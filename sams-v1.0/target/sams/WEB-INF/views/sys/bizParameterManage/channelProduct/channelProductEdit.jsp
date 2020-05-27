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
        	
        	 $('#cpChannelCode').combobox({  
                 onChange: function (row) {
            	     var channelCode = $("#cpChannelCode").combobox("getValue"); 
                	 $('#cpChannelProductCode').combobox({
                     	url: '${ctx}/combobox/queryUsedProductInfoComBox',
                         onBeforeLoad:function(param){
                         	param.channelCode = channelCode;
 					     },
 					    valueField:'ID',
 				        textField:'NAME'
                     });
            	     var name = $("#cpChannelCode").combobox("getText"); 
                  	 $('#ciChannelName').textbox('setValue',name.replace(/\([^\)]*\)/g,""));
                 }

             });
        	 
        	 $('#cpChannelProductCode').combobox({  
        		 onSelect: function (row) { 
        			 var name=row.NAME;
                 	 $('#piChannelProductName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                 }  
             });
        	 
        	 $('#cpTaProductCode').combobox({  
        		    onSelect: function (row){  
        		    var name=row.NAME;
                 	$('#cpTaProductName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                 }  
             });
             
        	//是否成立赋值 
   	            var isSetUps = '${entity.cpIsSetUp}'.split(",");
   	            //获取复选框
   	            var cpIsSetUp = $("input[name='cpIsSetUp']");
   	                //循环acctTypes与复选框，当acctTypes的值与复选框中value一致，状态选中
   	   		         for(var i=0;i<isSetUps.length;i++){
   	   		            for(var j=0;j<cpIsSetUp.length;j++){
   	   		                if(isSetUps[i]==cpIsSetUp[j].value)
   	   		                 cpIsSetUp[j].checked = true;
   	   		            }
   	   		         }
        	 
            $('#editChannelProduct').form({
                url : '${ctx}/channelProduct/editSubmit',
                onSubmit : function() {
                    progressLoad();
                    var isValid = $(this).form('validate');
                    var cpChannelCode = $("#cpChannelCode").combobox("getValue"); 
          	        var ciChannelName = $("#ciChannelName").textbox("getValue"); 
          	        var cpChannelProductCode = $("#cpChannelProductCode").combobox("getValue"); 
        	        var piChannelProductName = $("#piChannelProductName").textbox("getValue");
        	        var cpTaProductCode = $("#cpTaProductCode").combobox("getValue"); 
        	        var cpTaProductName = $("#cpTaProductName").textbox("getValue"); 
        	        if(cpChannelCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '渠道编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
        	        if(ciChannelName==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '渠道名称必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
        	        if(cpChannelProductCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '代销端产品编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
        	        if(piChannelProductName==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '代销端产品名称必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
        	        if(cpTaProductCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : 'TA端产品代码必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
        	        if(cpTaProductName==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : 'TA端产品名称必填',
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

        function openIconAllHtml(){
       		window.open("${staticPath}/icon/group/icon-all.html");
       	}
</script>
	
<style type = "text/css">
	.myself-gridDiv {
		width:  530px !important;
	}
</style>
	<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv myself-gridDiv" data-options="region:'center',border:false">
        <form id="editChannelProduct" method="post" >
			<table  style="margin: auto">
				<tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input class="easyui-combobox"  style = "width:250px;" id="cpChannelCode"   name ="cpChannelCode" value="${entity.cpChannelCode}" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/>
					    <input id="cpId" name="cpId" type="hidden" value="${entity.cpId}">
					    <input id="cpCuser" name="cpCuser" type="hidden" value="${entity.cpCuser}">
					    <input id="cpCtime" class="easyui-datetimebox" name="cpCtime" type="hidden" value="${entity.cpCtime}" data-options="showSeconds:true">
					    <input id="cpUuser" name="cpUuser" type="hidden" value="${entity.cpUuser}">
					    <input id="cpUtime" name="cpUtime" type="hidden" value="${entity.cpUtime}">
					    <input id="cpCheckState" name="cpCheckState" type="hidden" value="${entity.cpCheckState}"></td>
					    <input id="cpValidFlag" name="cpValidFlag" type="hidden" value="${entity.cpValidFlag}"></td>
				</tr>
				<tr>
					<td>渠道名称:<span style="color:red">*</span></td>
					<td><input id="ciChannelName" class="easyui-textbox" name="ciChannelName" type="text" value="${entity.ciChannelName}" readonly="readonly" data-options="validType:'length[1,200]'"></td>
				</tr>
				
				<tr>
				    <td>代销端产品代码:<span style="color:red">*</span></td>
				    <td><input class="easyui-combobox"  style = "width:250px;"   id="cpChannelProductCode"   name ="cpChannelProductCode" value="${entity.cpChannelProductCode}" data-options="
					     url:'${ctx}/combobox/queryUsedProductInfoComBox' ,
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/></td>
			    </tr>
			    
			    <tr>
				    <td>代销端产品名称:<span style="color:red">*</span></td>
				    <td><input class="easyui-textbox" style = "width:250px;"  id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" value="${entity.piChannelProductName}" readonly="readonly" data-options="validType:'length[1,200]'"/></td>
			    </tr>
				<tr>
					<td>TA端产品代码:</td>
					<td><input id="cpTaProductCode" name="cpTaProductCode"
						class="easyui-combobox" style = "width:400px;" value="${entity.cpTaProductCode}"  data-options="
					url:'${ctx}/combobox/queryAllTaFundInfoComBox',
					valueField:'ID',
					textField:'NAME',
					required:true,
					"/></td>
				</tr>
				
				<tr>
					<td>TA端产品名称:<span style="color:red">*</span></td>
					<td><input id="cpTaProductName" name="cpTaProductName"  class="easyui-textbox" type="text" style="width: 400px" value="${entity.cpTaProductName}" readonly="readonly" data-options="validType:'length[1,200]'"></td>
				</tr>
				
				<tr>
					<td>TA端产品是否已成立:<span style="color:red">*</span></td>
					<td>
					<input type="radio" name="cpIsSetUp" value="1"/><span>是</span>
					<input type="radio" style="margin-left: 30px" name="cpIsSetUp" value="0" /><span>否</span>
					</td>
				</tr>
				</table>
		</form>
    </div>
</div>