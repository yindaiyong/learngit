<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>代销端渠道信息管理</title>
</head>
<script type="text/javascript">
	var dataGrid;
	var filetypedata; 
	$(function() {
		 $.ajax({
			url:'${ctx}/sysDict/fundStatusGroup1',
		    data:{},
		    type:"POST",
		    async: false,
		    success:function (data){
		    	var result = eval('(' + data + ')');
		    	filetypedata = result;
		   }
		}); 
		
		var channelCode = '${channelCode}';
		var transDate = '${transDate}';
		var url = '${ctx}/marketHanding/getMarketHandingDetail';
		dataGrid = $('#dataGrid').datagrid({ 
							url : url,
							fit : false,
							fitColumns : false,
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'fmId',
							sortName : '',
							onClickRow: onClickRow,    //点击单元格触发的事件（重要
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,400, 500 ],
							queryParams:{
								filter_channelCode:channelCode,
								filter_transDate:transDate},
							columns : [[{
								field :'fmId',
								checkbox : 'true'
							}, {
								title : '交易日期',
								field : 'fmBusinessDate',
								width : 150,
								halign : 'center',
								align : 'center'
							},{
								title : '渠道编码',
								field : 'fmChannelCode',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '基金名称',
								field : 'fmFundName',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true
							},{
								title : '基金代码',
								field : 'fmFundCode',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '基金状态',
								field : 'fmFundStatus',
								width : 160,
								sortable: true,
								align : 'center',
								formatter:function(value,row){
									  for(var i=0; i<filetypedata.length; i++){
										if (filetypedata[i].FMFUNDSTATUS == value) 
											return filetypedata[i].FMFUNDSTATUSNAME;
									}  
									 return value; 
									 /* return row.fmFundStatus; */
								},
								editor:{
									type:"combobox",
									options:{
										editable:false,//禁止combobox输入
										data:filetypedata,
										valueField:'FMFUNDSTATUS',
										textField:'FMFUNDSTATUSNAME',
										/* url:'${ctx}/sysDict/fundStatusGroup1', */
										panelHeight: 'auto',
										required:true
									}
								}
							},{
								title : '基金规模',
								field : 'fmFundSize',
								width : 160,
								halign : 'center',
								align : 'center',
								
							},{
								title : '基金总份数',
								field : 'fmTotalFundVol',
								width : 160,
								halign : 'center',
								align : 'center',
								
							},{
								title : '基金单位净值',
								field : 'fmNAV',
								width : 160,
								halign : 'center',
								align : 'center',
								
							},{
								title : '基金净值日期',
								field : 'fmUpdateDate',
								width : 160,
								halign : 'center',
								align : 'center',
								
							},{
								title : '净值类型',
								field : 'fmNetValueType',
								width : 160,
								halign : 'center',
								align : 'center',
								
							}] ],
							toolbar : '#toolbar',
							//加载成功
							onLoadSuccess: function (data) {
								//去掉标题上的复选框
							   $(".datagrid-header-check").html("");
							},
							onAfterEdit:function(index,row){
								row.editing = false;
								$('#dataGrid').datagrid('updateRow',{
									index: index,
									row:{}
								});
								//选择完之后取消编辑,防止获取得数据为编辑之前的
								$('#dataGrid').datagrid('refreshRow', index);
								$('#dataGrid').datagrid('cancelEdit', index);
							},
							onCancelEdit:function(index,row){
								row.editing = false;
								$('#dataGrid').datagrid('updateRow',{
									index: index,
									row:{}
								});
							},
						});
  	});
	
	var editIndex = undefined;
	function onClickRow(index){
		if (editIndex != index){
			if (editIndex != undefined){
				$('#dataGrid').datagrid('endEdit', editIndex);
				$('#dataGrid').datagrid('cancelEdit', editIndex);
			}
		}
		$('#dataGrid').datagrid('beginEdit', index);
		editIndex = index;
	}
	
	  //保存
	function saveFundMarket(){
	    	//获得表格数据
			var data = dataGrid.datagrid('getSelections');
			if (data.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要保存的数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			}
			for(var i = 0; i < data.length; i++){
				//create by yindy 20190617 获得combobox的选择值，原代码fundstatus为初始值
				var ed = $('#dataGrid').datagrid("getEditor",{index: $("#dataGrid").datagrid("getRowIndex",data[i]),field:"fmFundStatus"});
				if(ed != null){
					//获得还处于编辑中的值
					var value = $(ed.target).combobox("getValue");
					data[i].fmFundStatus = $(ed.target).combobox("getValue");
				}else{
					//获得编辑之后的值
					var value = data[i].fmFundStatus;
				}
				if(isNaN(value)){
					//获得没有编辑过的值
					value = data[i].fmFundStatus;
				}
			}
			$.messager.progress({
		 		title : '提示',
		 		text : '正在处理文件，请等待....'
		 	});
	    	$.ajax({
	            url:'${ctx}/marketHanding/updateStatus',    //请求的url地址
	            dataType:"json",   //返回格式为json
	            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	            data:{"data" : JSON.stringify(data)},    //参数值
	            type:"POST",   //请求方式
	            success :function(result){
	            	parent.$.modalDialog.handler.dialog('close');
	                parent.$.modalDialog.openner_dataGrid.datagrid('reload');
	            	if("success" == result.msg){
	            		$.messager.progress('close');
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
	            },
	        });
	}
</script>
<table id="dataGrid" data-options="fit:true,border:false"></table>
<div  id="toolbar">
    	  <a id = "update" onclick="saveFundMarket();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-reload">保存</a>
</div>
</html>