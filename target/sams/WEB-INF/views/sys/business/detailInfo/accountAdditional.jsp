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
<title>信息补入</title>
</head>
<script type="text/javascript">
	var accoDataGrid;
	var transDataGrid;
	$(function() {
		accoDataGrid = $('#accoDataGrid')
				.edatagrid({
							url : '${ctx}/additional/getAdditionalInfo',
							striped : false,
							rownumbers : true,
							pagination : true,
							singleSelect : false,
							idField : 'iveId',
							width:'98%',
							sortName : '',
							pageSize : 10,
							queryParams:{filter_iveTableName:"D_ACCOUNT_REQ_TMP"},
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							columns : [ [ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'iveId',
								field : 'iveId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},{
								title : 'iveColLength',
								field : 'iveColLength',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '渠道编号',
								field : 'iveChannelCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							},  {
								title : '交易日期',
								field : 'iveTransDate',
								width : 80,
								halign : 'center',
								align : 'center',
								sortable : true,
							},{
								title : '申请编号',
								field : 'iveAppSheetSerialNo',
								width : 200,
								halign : 'center',
								align : 'center'
							}, {
								title : '字段编码',
								field : 'iveColCode',
								width : 160,
								halign : 'center',
								align : 'center'
							}, {
								title : '字段名称',
								field : 'iveColName',
								width : 160,
								halign : 'center',
								align : 'center'
							}, {
								title : '错误信息',
								field : 'iveErrorDec',
								width : 250,
								halign : 'center',
								align : 'center',
                                formatter : function(value){
                                    if(value == null || value === null){
                                        return "<span ></span>";
                                    }else{
                                        return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                                    }
                                }
							}, 
							{
								title : '原值',
								field : 'iveColValue',
								width : 140,
								halign : 'center',
								align : 'center'
							},{
								title : '更新值',
								field : 'iveNewColValue',
								width : 160,
								halign : 'center',
								align : 'center',
								editor: 'text'
							}, {
								title : '创建/修改时间',
								field : 'iveCtime',
								width : 120,
								halign : 'center',
								align : 'center'
							},{
								title : '创建/修改人',
								field : 'iveCuser',
								width : 100,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核时间',
								field : 'iveUtime',
								width : 120,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'iveUuser',
								width : 100,
								halign : 'center',
								align : 'center'
							},{
								title : '复核状态',
								field : 'iveValidFlag',
								width : 100,
								halign : 'center',
								align : 'center',
								formatter : function(value){
									if(value == "00")return "未复核";
									if(value == "01")return "已复核";
								}
							} ] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉标题上的复选框
							   //$(".datagrid-header-check").html("");
							},
							onAfterEdit : function(rowIndex, rowData, changes){
                                updateDealEdit(rowData,0);
							},
                        onDblClickCell : function(index, field, value) {
                            $(this).edatagrid('editRow', index);
                            var currentEdatagrid = $(this);
                                $('.datagrid-editable .textbox,.datagrid-editable .datagrid-editable-input,.datagrid-editable .textbox-text').bind('keydown', function(e){
                                    var code = e.keyCode || e.which;
                                    if(code == 13){
                                        $(currentEdatagrid).datagrid('acceptChanges');
                                        $(currentEdatagrid).datagrid('endEdit', index);
                                    }
                                })
                            },
                    onClickCell:function(index, field, value) {
                            var currentEdatagrid = $(this);
                            $(currentEdatagrid).datagrid('cancelEdit', index);
                        //添加代码结束
                            }
						});
		transDataGrid = $('#transDataGrid')
			.edatagrid({
					url : '${ctx}/additional/getAdditionalInfo',
					striped : true,
					rownumbers : true,
					pagination : true,
					singleSelect : false,
					idField : 'iveId',
					sortName : '',
					width:'99%',
					pageSize : 10,
					queryParams:{filter_iveTableName:"D_EXCHANGE_REQ_TMP"},
					pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
							400, 500 ],
					columns : [ [ {
						field :'ck',
						checkbox : 'true',
					},{
						title : 'iveId',
						field : 'iveId',
						width : 150,
						halign : 'center',
						align : 'center',
						hidden : 'true'
					},  {
						title : '渠道编号',
						field : 'iveChannelCode',
						width : 130,
						halign : 'center',
						align : 'center',
						sortable : true
					},  {
						title : '交易日期',
						field : 'iveTransDate',
						width : 80,
						halign : 'center',
						align : 'center',
						sortable : true,
					},{
						title : '申请编号',
						field : 'iveAppSheetSerialNo',
						width : 200,
						halign : 'center',
						align : 'center'
					}, {
						title : '字段编码',
						field : 'iveColCode',
						width : 160,
						halign : 'center',
						align : 'center'
					}, {
						title : '字段名称',
						field : 'iveColName',
						width : 160,
						halign : 'center',
						align : 'center'
					}, {
						title : '错误信息',
						field : 'iveErrorDec',
						width : 250,
						halign : 'center',
						align : 'center',
                        formatter : function(value){
                            if(value == null || value === null){
                                return "<span ></span>";
                            }else{
                                return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                            }
                        }
					} , {
						title : '原值',
						field : 'iveColValue',
						width : 140,
						halign : 'center',
						align : 'center'
					},{
						title : '更新值',
						field : 'iveNewColValue',
						width : 160,
						halign : 'center',
						align : 'center',
						editor: 'text'
					}, {
						title : '创建/修改时间',
						field : 'iveCtime',
						width : 120,
						halign : 'center',
						align : 'center'
					}, {
						title : '创建/修改人',
						field : 'iveCuser',
						width : 100,
						halign : 'center',
						align : 'center'
					}, {
						title : '复核时间',
						field : 'iveUtime',
						width : 120,
						halign : 'center',
						align : 'center'
					}, {
						title : '复核人',
						field : 'iveUuser',
						width : 100,
						halign : 'center',
						align : 'center'
					},{
						title : '复核状态',
						field : 'iveValidFlag',
						width : 100,
						halign : 'center',
						align : 'center',
						formatter : function(value){
							if(value == "00")return "未复核";
							if(value == "01")return "已复核";
						}
					} ] ],
					toolbar : '#toolbar1',
					onLoadSuccess: function (data) {
						//去掉标题上的复选框
					   //$(".datagrid-header-check").html("");
					},
					onAfterEdit : function(rowIndex, rowData, changes){ //updateUrl:'${ctx}/additional/addSubmit',
						updateDealEdit(rowData,1);
					},
                onDblClickCell : function(index, field, value) {
            $(this).edatagrid('editRow', index);
            var currentEdatagrid = $(this);
            $('.datagrid-editable .textbox,.datagrid-editable .datagrid-editable-input,.datagrid-editable .textbox-text').bind('keydown', function(e){
                var code = e.keyCode || e.which;
                if(code == 13){
                    $(currentEdatagrid).datagrid('acceptChanges');
                    $(currentEdatagrid).datagrid('endEdit', index);
                }
            })
        },
        onClickCell:function(index, field, value) {
            var currentEdatagrid = $(this);
            $(currentEdatagrid).datagrid('cancelEdit', index);
            //添加代码结束
        }
				});
  	});
	
	//跟新数据
	 function updateInfo(type) {
		 var transDate = $('#iveTransDate').datebox('getValue');
		 var channelCode = $('#iveChannelCode').combobox('getValue');
		 if(type == 0 ){
			var rows = accoDataGrid.datagrid('getSelections');
		 }else{
			 var rows = transDataGrid.datagrid('getSelections');
		 }
			if (rows.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要更新的数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			} else {
	        	var ciId ;
	        	var ciIds= []; 
	        	var ciIds= new Array();  
                for (var i = rows.length - 1; i >= 0; i--) {
                	if(rows[i].iveValidFlag == "00"){
                		$.messager.alert({
        					title : '提示',
        					msg : '数据未复核，请先复核！',
        					timeout : 3000,
                		});
                		return ;
                	}
                	ciIds.push(rows[i].iveId);
                }
					parent.$.messager.confirm('询问', '您确认将选中数据更新吗？', function(b) {
						if (b) {
							$.post('${ctx}/additional/updateSubmit', {
								ciIds : JSON.stringify(ciIds)
							}, function(result) {
								if (result.success) {
									parent.$.messager.alert('提示', result.msg, 'info');
									if(type == 0){
										accoDataGrid.datagrid('load', {filter_iveTableName:"D_ACCOUNT_REQ_TMP",filter_iveChannelCode :channelCode,filter_iveTransDate : transDate});
									}else{
								         transDataGrid.datagrid('load', {filter_iveTableName:"D_EXCHANGE_REQ_TMP",filter_iveChannelCode :channelCode,filter_iveTransDate : transDate});
									}
								}
							}, 'JSON');
						} 
					});
			}
	 }
	 //复核
	 function checkInfo(type){
		 var transDate = $('#iveTransDate').datebox('getValue');
		 var channelCode = $('#iveChannelCode').combobox('getValue');
		 if(type == 0 ){
			var rows = accoDataGrid.datagrid('getSelections');
		 }else{
			var rows = transDataGrid.datagrid('getSelections');
		 }
		 if (rows.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要复核的数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
		 }else{
			 var ciIds= [];
			 for (var i = rows.length - 1; i >= 0; i--) {
				 if(rows[i].iveValidFlag != '01'){
					 ciIds.push(rows[i].iveId);
				 }
             }
			 $.post('${ctx}/additional/checkSubmit', {
					ciIds:JSON.stringify(ciIds.join(","))
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						if(type == 0){
							accoDataGrid.datagrid('load', {filter_iveTableName:"D_ACCOUNT_REQ_TMP",filter_iveChannelCode :channelCode,filter_iveTransDate : transDate});
						}else{
					         transDataGrid.datagrid('load', {filter_iveTableName:"D_EXCHANGE_REQ_TMP",filter_iveChannelCode :channelCode,filter_iveTransDate : transDate});
						}
					}
				}, 'JSON');
		 }
	 }
	 //查询
	 function searchFun() {
		 debugger;
		 var channelCode = $('#iveChannelCode').combobox('getValue');
		 var transDate = $('#iveTransDate').datebox('getValue');
		 var account = {filter_iveTableName : "D_ACCOUNT_REQ_TMP",filter_iveChannelCode :channelCode, filter_iveTransDate : transDate};
		 accoDataGrid.datagrid('load', account);
		 var trans = {filter_iveTableName : "D_EXCHANGE_REQ_TMP",filter_iveChannelCode :channelCode, filter_iveTransDate : transDate};
		 transDataGrid.datagrid('load', trans);
     }
	 
	 function cleanFun() {
         $('#searchForm input').val('');
         accoDataGrid.datagrid('load', {filter_iveTableName:"D_ACCOUNT_REQ_TMP"});
         transDataGrid.datagrid('load', {filter_iveTableName:"D_EXCHANGE_REQ_TMP"});
     }
	 
	 function updateDealEdit(rowData,type){
		 $.ajax({
				    type : "post",
				    dataType : "json",
				    url: '${ctx}/additional/addSubmit',
				    cache: false,
					contentType : 'application/json',
					dataType : 'json',
				    data : JSON.stringify(rowData),
				    success : function(result) {
						$.messager.show({
							title : '提示',
							msg : result.msg,
							timeout : 2000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
				    }
			});
	 }

</script>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="border:false,title:'查询区域'"
		align="center" style = "height:30px;padding:5px 0px">
       <form id="searchForm">
            <table align="center">
                <tr>
                    <td align="center">
                                                 渠道编号:<input id="iveChannelCode" name="filter_iveChannelCode" class="easyui-combobox" style = "width:150px;" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'ID'"/>
					交易日期:<input class="easyui-datebox" id="iveTransDate" name="filter_iveTransDate" />
					</td>
					 <td align="center">
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </div> 
    <div class="easyui-accordion" data-options="multiple:true," style="width:99%">
	    <div data-options="region:'center',collapsed:false,collapsible:true,border:true,title:'账户信息补录列表'" style = "height:420px">
	        <table id="accoDataGrid" data-options="border:false"></table>
	    </div>
	    <div data-options="region:'center',collapsed:true,collapsible:true,border:true,title:'交易信息补录列表'" style = "height:420px">
	        <table id="transDataGrid" data-options="border:false"></table>
	    </div>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="updateInfo(0);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">更新目标数据</a>
            <a onclick="checkInfo(0);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">复核</a>
    </div>
    <div id="toolbar1" style="display: none;">
            <a onclick="updateInfo(1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">更新目标数据</a>
            <a onclick="checkInfo(1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">复核</a>
    </div>
</body>
</html>