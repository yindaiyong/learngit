<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>T+N产品分配</title>
</head>
<script type="text/javascript">
//初始化查询条件 
 var dataGrid;
 $(function() {
	 $('#taChannelCode').combobox({
		    url:"${ctx}/combobox/queryTaFundInfoComBox?fundCode=" +''+'&channelCode='+'', //访问controller的路径
		    method:'get',
	        mode:'remote',
			valueField:'ID',
		    textField:'NAME',
		    multiple : false,
		        onShowPanel : function () {
		           var channelCode = $("#channelCode").combobox("getValue");	
	           var erFundCode = $("#erFundCode").combobox("getValue");
	           $('#taChannelCode').combobox('clear');
	           $('#taChannelCode').combobox('reload','${ctx}/combobox/queryTaFundInfoComBox?fundCode=' +erFundCode+'&channelCode='+channelCode);
	       },
	}); 
	 
	 //查询条件 一个代销端产品所对应的ta端产品代码 级联关系 
	 $('#erTaFundCode').combobox({
		    url:"${ctx}/combobox/queryTaFundInfoComBox?fundCode=" +''+'&channelCode='+'', //访问controller的路径
		    method:'get',
	        mode:'remote',
			valueField:'ID',
		    textField:'NAME',
		    multiple : false,
	        onShowPanel : function () {
	           var channelCode = $("#channelCode").combobox("getValue");	
	           var erFundCode = $("#erFundCode").combobox("getValue");
	           $('#erTaFundCode').combobox('clear');
	           $('#erTaFundCode').combobox('reload','${ctx}/combobox/queryTaFundInfoComBox?fundCode=' +erFundCode+'&channelCode='+channelCode);
	       },
	});

		//渠道产品关系级联   
		$('#channelCode').combobox({  
		     onChange: function (row) {
		    	 debugger;
		   	 var channelCode = $("#channelCode").combobox("getValue"); 
		        $('#erFundCode').combobox({
		        	url: '${ctx}/combobox/queryTnFundInfoComBox',
		           onBeforeLoad:function(param){
		            	param.channelCode = channelCode;
		           },
		        valueField:'ID',
		        textField:'NAME'
		        });
		    }
		});

	dataGrid = $('#dataGrid').datagrid({
		    url : '${ctx}/productAssignment/getProductAssignmentData',
	        striped : true,
			rownumbers : true,
			pagination : true,
			idField : 'erId',
			sortName : 'd.ER_APPLICATION_AMOUNT,d.ER_APP_SHEET_SERIAL_NO',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
					400, 500 ],
					columns : [ [{
						field :'ck',
						checkbox : 'true',
					}, {
						field : 'erId',
						width : 200,
						align : 'center',
						hidden: 'true'
						}, {
						title:'渠道编号',
						field : 'erChannelCode',
						width : 100,
						align : 'center'
						}, {
						title:'申请单编号',
						field : 'erAppSheetSerialNo',
						width : 200,
						align : 'center'
						},{
						title:'产品代码',
						field : 'erFundCode',
						width : 120,
						align : 'center'
						},{
						title:'交易日期',
						field : 'erTransDate',
						width : 120,
						align : 'center'
						},{
						title:'交易金额',
						field : 'erApplicationAmount',
						width : 150,
						align : 'center'
						},{
						title:'投资人基金交易账号',
						field : 'erTransactionAccountId',
						width : 200,
						align : 'center'
						},{
						title:'T+N产品代码',
						field : 'erTaProductCode',
						width : 200,
						align : 'center'
						},{
							title:'批次号',
							field : 'erBatchNo',
							width : 100,
							align : 'center'
							}] ],
			toolbar : '#toolbar',
			onLoadSuccess: function (data) {
				//去掉标题上的复选框
				/* $(".datagrid-header-check").html(""); */
				$("#dataGrid").datagrid('clearSelections');
			}/* ,
			onSelect:function(){
				var rows = dataGrid.datagrid('getSelections');
				var erFundCode= rows[0].erFundCode;
		    	var erChannelCode= rows[0].erChannelCode;
		    	$('#taChannelCode').combobox({
		    	    url:"${ctx}/combobox/queryTaFundInfoComBox?fundCode=" +erFundCode+'&channelCode='+erChannelCode, //访问controller的路径
		    	    method:'get',
		            mode:'remote',
		    		valueField:'ID',
		    	    textField:'NAME',
		    	    multiple : false,
		    	    onSelect: function (row) { 
	        			 var name=row.NAME;
	                 	 var value = name.substring(name.lastIndexOf('-') + 1);
	        	 		 $('#cpBatchNumber').val(value);
	                }  
		        });
			} */
		});
	});

//条件查询
function searchFun() {
    var parmFilter = $.serializeObject($('#searchForm'));
    dataGrid.datagrid('load', parmFilter);
    var erChannelCode= $("#channelCode").combobox("getValue"); 
	var erFundCode= $("#erFundCode").combobox("getValue");
    $('#taChannelCode').combobox({
	    url:"${ctx}/combobox/queryTaFundInfoComBox?fundCode=" +erFundCode+'&channelCode='+erChannelCode, //访问controller的路径
	    method:'get',
        mode:'remote',
		valueField:'ID',
	    textField:'NAME',
	    multiple : false,
	    onSelect: function (row) { 
			 var name=row.NAME;
         	 var value = name.substring(name.lastIndexOf('-') + 1);
	 		 $('#cpBatchNumber').val(value);
        }  
    });
}
//重置 
function cleanFun() {
    $('#searchForm input').val('');
    dataGrid.datagrid('load', {});
}

//确认分配
function certainAssign(){
	//获取id 
	 var rows = dataGrid.datagrid('getSelections');
	 var batchNumber = $('#cpBatchNumber').val();
	 var erId;
	 var taChannelCode = $('#taChannelCode').combobox('getValue');
	 //created by  wangchao 20200507 加校验 如果没有选择T+N产品则不允许进行分配 
	 if(taChannelCode == ''){
		 $.messager.show({
				title : '提示',
				msg : '请选择需要分配的T+N产品',
				timeout : 3000,
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
			return;
	 }
	 for(var i=0;i<rows.length;i++){
		 erId= rows[i].erId;
	//获得勾选TA产品编码
		$.ajax({
	        url:"${ctx}/productAssignment/certainAssign",    //请求的url地址
	        dataType:"json",   //返回格式为json
	        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	        data:{"taChannelCode":taChannelCode,"erId":erId,"batchNumber":batchNumber},    //参数值
	        type:"POST",   //请求方式
	        success :function(result){
	        	$.messager.progress('close');
	           var parmFilter = $.serializeObject($('#searchForm'));
	           dataGrid.datagrid('load', parmFilter);
	        	dataGrid.datagrid("reload");
	        	if("success" == result.msg){
	        		$.messager.show({
	                    title : '提示',
	                    msg : "处理完成！",
	                    timeout : 2000
	                });
	        	}else{
	        		$.messager.progress('close');
	        		$.messager.show({
	                    title : '提示',
	                    msg : "处理失败",
	                    timeout : 2000
	                });
	        	}
	        }
	    });
	 }
}

//批量发送TA
function batchSendToTA(){
	var date = $("#erTransactionDate").datebox("getValue");
	$.messager.progress({
		title : '提示',
		text : '处理中,请等待....如有问题,请联系管理员。'
	});
	$.ajax({
        url:"${ctx}/dayEnd/batchSendToTA",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"date":date},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.progress('close');
        	var parmFilter = $.serializeObject($('#searchForm'));
            dataGrid.datagrid('load', parmFilter);
        	if(result.success){
        		$.messager.alert({
                    title : '提示',
                    msg :(result.msg == "" ? "处理完成":result.msg),
                    timeout : 2000
                });
        	}
        }
     });
}


</script>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
		<form id="searchForm">
			<table align="center" data-options="fit:true,border:false"
				style="padding-top: 10px;">
				<tr>
					<th>交易日期:</th>
					<td><input class="easyui-datebox" id="erTransactionDate"
						name="filter_erTransactionDate" /></td>
					<th>代销渠道:</th>
					<%-- <td><input class="easyui-combobox"  style = "width:250px;"  id="channelCode"   name ="filter_channelCode"  data-options="
								url:'${ctx}/combobox/queryChannelInfo',
								valueField:'ID',
								textField:'NAME',required:false"></td> --%>		
								
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="channelCode"   name ="filter_channelCode"  data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME',required:false"></td>
                    
				</tr>
				<tr>
				<th>产品名称:</th>
					<%-- <td><input id="erFundCode" name="filter_erFundCode"
						class="easyui-combobox" style = "width:250px;"  data-options="
					url:'${ctx}/combobox/queryTnFundInfoComBox',
					valueField:'ID',
					textField:'NAME' "/><input type = "hidden" id = "cpBatchNumber" name = "cpBatchNumber"/></td> --%>
				<td><input id="erFundCode" name="filter_erFundCode"
						class="easyui-combobox" style = "width:250px;"  data-options="
					url:'${ctx}/combobox/queryTnFundInfoComBox',
					onBeforeLoad:function(param){
					        param.channelCode = '';
					},
					valueField:'ID',
					textField:'NAME',required:false"/><input type = "hidden" id = "cpBatchNumber" name = "cpBatchNumber"/></td>
				<th>TA产品:</th>
				<td>
				<input id="erTaFundCode" name="filter_erTaFundCode" class="easyui-combobox" style = "width:400px;"/>
				</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-search',plain:true"
						onclick="searchFun();">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel',plain:true"
						onclick="cleanFun();">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'T+N产品分配列表'">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
			  T+N产品名称:<input id="taChannelCode" name="taChannelCode" class="easyui-combobox" style = "width:400px;"/>
            <a onclick="certainAssign();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-ok'">确认分配</a>
				<a onclick="batchSendToTA();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-ok'">日终处理流程批量调起（只调起需要分配T+N并且分配完T+N的渠道）</a>
	</div>
</body>
</html>