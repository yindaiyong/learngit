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
                 	 var value = name.substring(name.lastIndexOf('-') + 1);
        	 		 $('#cpBatchNumber').val(value);
                 }  
             });
        	 
        	 $('#cpTaProductCode').combobox({
        		    onSelect: function (row){
        		    var name=row.NAME;
                 	$('#cpTaProductName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                 }  
             });
        	 
            $('#addChannelProduct').form({
                url : '${ctx}/channelProduct/addSubmit',
                onSubmit : function() {
                    progressLoad();
                    var isValid = $(this).form('validate');
                    //cpChannelCode ciChannelName cpChannelProductCode cpChannelProductName cpTaProductCode cpTaProductName
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
        <form id="addChannelProduct" method="post" >
			<table  style="margin: auto">
				<tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input class="easyui-combobox"  style = "width:250px;" id="cpChannelCode"   name ="cpChannelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/></td>
				</tr>
				<tr>
					<td style="width: 120px">渠道名称:<span style="color:red">*</span></td>
					<td><input id="ciChannelName" class="easyui-textbox" name="ciChannelName" style = "width:250px;"  data-options="validType:'length[1,200]'"></td>
				</tr>
				
				<tr>
				    <td>代销端产品代码:<span style="color:red">*</span></td>
				    <%--get方式传参 <td><input class="easyui-combobox"  style = "width:250px;"   id="cpChannelProductCode"   name ="cpChannelProductCode" value = "" data-options="
					     method:'get',
     	                 mode:'remote',
					     url:'${ctx}/combobox/queryProductInfoComBox?channelCode=' +'' ,
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/></td> --%>
					 <td><input class="easyui-combobox"  style = "width:250px;"   id="cpChannelProductCode"   name ="cpChannelProductCode" value = "" data-options="
					     url:'${ctx}/combobox/queryUsedProductInfoComBox',
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     "/></td>
			    </tr>
			    
			    <tr>
				    <td >代销端产品名称:<span style="color:red">*</span></td>
				    <td><input type="text" id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" style = "width:250px;"  data-options="validType:'length[1,200]'"/></td>
			    </tr>
				<tr>
					<td >TA端产品代码:<span style="color:red">*</span></td>
					<td><input id="cpTaProductCode" name="cpTaProductCode"
						class="easyui-combobox" style = "width:400px;"  data-options="
					url:'${ctx}/combobox/queryAllTaFundInfoComBox',
					valueField:'ID',
					textField:'NAME',
					required:true,
					"/></td>
				</tr>
				
				<tr>
					<td >TA端产品名称:<span style="color:red">*</span></td>
					<td><input id="cpTaProductName" name="cpTaProductName" style = "width:400px;" class="easyui-textbox" data-options="validType:'length[1,300]'"></td>
				</tr>
				
				<tr>
					<td>TA端产品是否已成立:<span style="color:red">*</span></td>
					<td>
					<input type="radio" name="cpIsSetUp" value="1"  checked="checked"/><span>是</span>
					<input type="radio" style="margin-left: 30px" name="cpIsSetUp" value="0" /><span>否</span>
					<input type = "hidden" name="cpBatchNumber" id = "cpBatchNumber"/>
					</td>
				</tr>
				</table>
		</form>
    </div>
</div>