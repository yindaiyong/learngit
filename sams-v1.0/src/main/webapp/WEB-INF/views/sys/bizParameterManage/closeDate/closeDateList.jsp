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
<title>市场非交易日管理</title>
</head>
<script type="text/javascript">
	var dataGrid;
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
		
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}/closeDate/getCloseDateData',
			fit : true,
			fitColumns : true,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'cdId',
			sortName : '',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				title : 'cdId',
				field : 'cdId',
				width : 150,
				halign : 'center',
				align : 'center',
				hidden : 'true'
			}, {
				title : '市场名称',
				field : 'cdMarketCode',
				width : 150,
				halign : 'center',
				align : 'center',
				formatter : function(value) {
					if (value == "001") {
						return "银行市场";
					}
					if (value == "002") {
						return "券商市场";
					}
				}
			}, {
				title : '年度',
				field : 'cdYear',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				title : '月度',
				field : 'cdMonth',
				width : 160,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				title : '非交易日日期',
				field : 'cdCloseDate',
				width : 160,
				halign : 'center',
				align : 'center',
				sortable : true,
			} ] ],
			columns : [ [ {
				title : '复核状态',
				field : 'cdCheckState',
				width : 100,
				halign : 'center',
				align : 'center',
				formatter : function(value) {
					if (value == "00") {
						return "未复核";
					}
					if (value == "01") {
						return "已复核";
					}
				}
			}, {
				title : '操作',
				field : 'cdAction',
				width : 80,
				halign : 'center',
				align : 'center',
				formatter : function(value) {
					if (value == "00") {
						return "删除";
					}
					if (value == "01") {
						return "新增";
					}
					if (value == "02") {
						return "修改";
					}
					if (value == "03") {
						return "复核";
					}
					if (value == "04") {
						return "停用";
					}
					if (value == "05") {
						return "启用";
					}
				}
			} ,{
				title : '操作人',
				field : 'cdCuser',
				width : 80,
				halign : 'center',
				align : 'center'
			}, {
				title : '操作时间',
				field : 'cdCtime',
				width : 140,
				halign : 'center',
				align : 'center'
			}, {
				title : '复核时间',
				field : 'cdUtime',
				width : 140,
				halign : 'center',
				align : 'center'
			}, {
				title : '复核人',
				field : 'cdUuser',
				width : 80,
				halign : 'center',
				align : 'center'
			} ] ],
			toolbar : '#toolbar'
		});
	});

	function addSave() {
		var f = parent.$.modalDialog.handler.find('#cdMarketCode');
		var cdMarketCode = f.combobox('getValue');
		var righttable = [];
		var b = parent.$.modalDialog.handler.find('#rightId option');
		b.each(function() {
			var v = $(this).val();//这句获取当前遍历到的option的值
			righttable.push(v);
		})

		$.ajax({
			url : "${ctx}/closeDate/addSubmit",
			dataType : "json",
			type : "post",
			data : {
				righttable : JSON.stringify(righttable),
				cdMarketCode : cdMarketCode
			},
			success : function(result) {
                if (result.success) {
                	parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    $.messager.show({
                        title : '提示',
                        msg : result.msg,
                        timeout : 1000
                    });
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
			},
		});
	}

	function addCloseDate() {
		parent.$.modalDialog({
			title : '市场非交易日设置新增',
			width : 800,
			height : 400,
			href : '${ctx}/closeDate/addPage',
			buttons : [ {
				text : '提交',
				iconCls : 'icon-save',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					addSave();
					parent.$.modalDialog.handler.dialog('close');
					searchFun();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
	}

	function editSave() {
		var f = parent.$.modalDialog.handler.find('#cdMarketCode');
		var cdMarketCode = f.combobox('getValue');
		var righttable = [];
		var b = parent.$.modalDialog.handler.find('#rightId option');

		b.each(function() {
			var v = $(this).val();//这句获取当前遍历到的option的值
			righttable.push(v);
		});
		
		var y=parent.$.modalDialog.handler.find('#year');
		var m=parent.$.modalDialog.handler.find('#month');
		
		var year = y.combobox('getValue');
		var month = m.combobox('getValue');
		$.ajax({
			url : "${ctx}/closeDate/editSubmit",
			dataType : "json",
			type : "post",
			data : {
				righttable:JSON.stringify(righttable),
				cdMarketCode:cdMarketCode,
				yearval:year,
				monthval:month
			},
			success : function(data) {
				$.messager.show({
					title : '提示',
					msg : '修改成功',
					timeout : 1000
				});
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
			},
			error : function(data) {
				parent.$.messager.alert('错误', '修改失败', 'error');
			}
		});
	}

	function editCloseDate() {
		var rows = dataGrid.datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.show({
				title : '提示',
				msg : '请选择一条数据进行修改',
				timeout : 3000,
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
		} else {
			cdId = rows[0].cdId;
			cdMarketCode = rows[0].cdMarketCode;
			cdYear = rows[0].cdYear;
			cdMonth = rows[0].cdMonth;
			parent.$.modalDialog({
				title : '市场非交易日设置修改',
				width : 800,
				height : 400,
				href : '${ctx}/closeDate/editPage?cdMarketCode=' + cdMarketCode
						+ '&cdYear=' + cdYear + '&cdMonth=' + cdMonth,
				buttons : [ {
					text : '提交',
					iconCls : 'icon-save',
					handler : function() {
						parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
						editSave();
						parent.$.modalDialog.handler.dialog('close');
						searchFun();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				} ]
			});
		}
	}

	function viewCloseDate() {
			var rows = dataGrid.datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.show({
					title : '温馨提示',
					msg : '请选择一条数据进行查看',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}else{
			cdId = rows[0].cdId;
			cdMarketCode = rows[0].cdMarketCode;
			cdYear = rows[0].cdYear;
			cdMonth = rows[0].cdMonth;
		parent.$.modalDialog({
			title : '市场非交易日设置查看',
			width : 800,
			height : 400,
			href : '${ctx}/closeDate/viewPage?cdMarketCode=' + cdMarketCode
					+ '&cdYear=' + cdYear + '&cdMonth=' + cdMonth,
			buttons : [ {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			} ]
		});
			}
	}

	function delCloseDate() {
		var rows = dataGrid.datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.show({
				title : '提示',
				msg : '请选择需要删除的数据',
				timeout : 3000,
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
		} else {
			var cdCheckState = rows[0].cdCheckState;
			var cdYear = rows[0].cdYear;
			var cdMonth = rows[0].cdMonth;
			var cdMarketCode = rows[0].cdMarketCode;
		/* 	if(cdCheckState=='01'){
				$.messager.show({
					title : '提示',
					msg : '已复核数据不允许删除',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}else{ */
				parent.$.messager.confirm('询问', '您确认删除'+cdYear+'年'+cdMonth+'月的数据吗？', function(b) {
					if (b) {
						$.post('${ctx}/closeDate/delete', {
							cdYear : cdYear,
							cdMonth : cdMonth,
							cdMarketCode : cdMarketCode
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							}
						}, 'JSON');
					} 
				});
			/* } */
		}
	}
	
	function checkCloseDate(){
		var rows = dataGrid.datagrid('getSelections');
		if (rows.length ==0) {
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
		} else {
			var cdMarketCode = rows[0].cdMarketCode;
			if(cdCheckState=='01'){
				$.messager.show({
					title : '提示',
					msg : '已复核数据不能再复核',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}else{
				var cdCuser = rows[0].cdCuser;
				if(cdCuser=='<shiro:principal/>'){
					$.messager.show({
						title : '提示',
						msg : '您的的复核人与操作人相同，请更换复核用户',
						timeout : 3000,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					return;
				}
			var cdId = rows[0].cdId;
			var cdCheckState = rows[0].cdCheckState;
			var cdYear = rows[0].cdYear;
			var cdMonth = rows[0].cdMonth;
			var cdMarketCode = rows[0].cdMarketCode;
			var cdCloseDate= rows[0].cdCloseDate;
			parent.$.messager.confirm('询问', '您确认复核'+cdYear+'年'+cdMonth+'月的数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/closeDate/checkSubmit', {
						cdYear : cdYear,
						cdMonth : cdMonth,
						cdMarketCode : cdMarketCode
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
		}
	}

	function searchFun() {
		var parmFilter = $.serializeObject($('#searchForm'));
		dataGrid.datagrid('load', parmFilter);
	}

	function cleanFun() {
		$('#searchForm').form('reset');
		dataGrid.datagrid('load', {});
	}
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
		<form id="searchForm">
			<table data-options="fit:true,border:false"
				style="padding-top: 10px;">
				<tr>
					<td align="center">市场名称:
					    <select id="filter_cdMarketCode" name="filter_cdMarketCode"
						style="width: 150px; height: 20px;" class="easyui-combobox">
							<option value=''>请选择</option>
							<option value="001">银行市场</option>
							<option value="002">券商市场</option>
						</select>
						年度:
						<select id="filter_cdYear" name="filter_cdYear" style="width: 150px"
						class="easyui-combobox">
						    <option value=''>请选择</option>
							<option value='2015'>2015</option>
							<option value='2016'>2016</option>
							<option value='2017'>2017</option>
							<option value='2018'>2018</option>
							<option value='2019'>2019</option>
							<option value='2020'>2020</option>
							<option value='2021'>2021</option>
							<option value='2022'>2022</option>
							<option value='2023'>2023</option>
							<option value='2024'>2024</option>
							<option value='2025'>2025</option>
							<option value='2026'>2026</option>
							<option value='2027'>2027</option>
							<option value='2028'>2028</option>
							<option value='2029'>2029</option>
							<option value='2030'>2030</option>
					   </select>
						月度:
						<select id="filter_cdMonth" name="filter_cdMonth" style="width: 150px"
						class="easyui-combobox">
							<option value=''>请选择</option>
							<option value='01'>01</option>
							<option value='02'>02</option>
							<option value='03'>03</option>
							<option value='04'>04</option>
							<option value='05'>05</option>
							<option value='06'>06</option>
							<option value='07'>07</option>
							<option value='08'>08</option>
							<option value='09'>09</option>
							<option value='10'>10</option>
							<option value='11'>11</option>
							<option value='12'>12</option>
					</select>
						复核状态:
						<select id="filter_cdCheckState" name="filter_cdCheckState" style="width: 150px"
						class="easyui-combobox">
							<option value=''>请选择</option>
							<option value='00'>未复核</option>
							<option value='01'>已复核</option>
						</select>
				</td>
				</tr>
				<tr>
					<td align="center"><a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-search',plain:true"
						onclick="searchFun();">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel',plain:true"
						onclick="cleanFun();">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'非交易日管理列表'">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
			<a onclick="addCloseDate();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">非交易日新增</a>
			<a onclick="editCloseDate();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'">非交易日修改</a>
			<a onclick="viewCloseDate();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-tip'">非交易日查看</a>
			<a onclick="delCloseDate();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-del'">非交易日删除</a>
			<a onclick="checkCloseDate();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-ok'">非交易日复核</a>
	</div>
</body>
</html>