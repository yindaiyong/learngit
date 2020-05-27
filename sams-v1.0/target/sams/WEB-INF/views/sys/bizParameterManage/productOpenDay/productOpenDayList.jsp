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
<title>市场产品非工作日管理</title>
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
        
      //渠道产品二级联动 
    	$('#channelCode').combobox({  
            onSelect: function (row) {
           	 var channelCode = row.ID;
                if (row != null) {
                    $('#productCode').combobox({
                    	url: '${ctx}/combobox/queryUsedProductInfoComBox',
                        onBeforeLoad:function(param){
                        	param.channelCode = channelCode;
    				     },
    				    valueField:'ID',
    				    textField:'NAME'
                    });
                }
            }

        });
		
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}/productOpenDay/getProductOpenDayData',
			fit : true,
			fitColumns : true,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'poId',
			sortName : '',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				title : 'poId',
				field : 'poId',
				width : 150,
				halign : 'center',
				align : 'center',
				hidden : 'true'
			}, {
				title : '渠道编号',
				field : 'poChannelCode',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			},{
				title : '产品编号',
				field : 'poProductCode',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			},{
				title : '批次号',
				field : 'poBatchNo',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				title : '年度',
				field : 'poYear',
				width : 130,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				title : '月度',
				field : 'poMonth',
				width : 160,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				title : '非产品非工作日日期',
				field : 'poDate',
				width : 160,
				halign : 'center',
				align : 'center',
				sortable : true,
			} ] ],
			columns : [ [ {
				title : '复核状态',
				field : 'poCheckState',
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
				field : 'poAction',
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
				field : 'poCuser',
				width : 80,
				halign : 'center',
				align : 'center'
			}, {
				title : '操作时间',
				field : 'poCtime',
				width : 200,
				halign : 'center',
				align : 'center'
			}, {
				title : '复核时间',
				field : 'poUtime',
				width : 200,
				halign : 'center',
				align : 'center'
			}, {
				title : '复核人',
				field : 'poUuser',
				width : 80,
				halign : 'center',
				align : 'center'
			} ] ],
			toolbar : '#toolbar'
		});
	});

	function addSave() {
		var channel = parent.$.modalDialog.handler.find('#channelCode');
		var product = parent.$.modalDialog.handler.find('#productCode');
		var channelCode = channel.combobox('getValue');
		var productCode = product.combobox('getValue');
		var righttable = [];
		var b = parent.$.modalDialog.handler.find('#rightId option');
		b.each(function() {
			var v = $(this).val();//这句获取当前遍历到的option的值
			righttable.push(v);
		})

		$.ajax({
			url : "${ctx}/productOpenDay/addSubmit",
			dataType : "json",
			type : "post",
			data : {
				righttable : JSON.stringify(righttable),
				channelCode : channelCode,
				productCode : productCode
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

	function addProductOpendDay() {
		parent.$.modalDialog({
			title : '渠道产品产品非工作日设置新增',
			width : 1200,
			height : 450,
			href : '${ctx}/productOpenDay/addPage',
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
		var channel = parent.$.modalDialog.handler.find('#channelCode');
		var product = parent.$.modalDialog.handler.find('#productCode');
		var channelCode = channel.combobox('getValue');
		var productCode = product.combobox('getValue');
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
			url : "${ctx}/productOpenDay/editSubmit",
			dataType : "json",
			type : "post",
			data : {
				righttable : JSON.stringify(righttable),
				channelCode : channelCode,
				productCode : productCode
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

	function editProductOpendDay() {
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
		    year = rows[0].poYear;
		    month = rows[0].poMonth;
		    channelCode = rows[0].poChannelCode;
		    productCode = rows[0].poProductCode;
		    batchNo = rows[0].poBatchNo;
			parent.$.modalDialog({
				title : '渠道产品产品非工作日设置修改',
				width : 1200,
				height : 450,
				href : '${ctx}/productOpenDay/editPage?year=' + year
						+ '&month=' + month + '&channelCode=' + channelCode
						+ '&productCode=' + productCode
						+ '&batchNo=' + batchNo,
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

	function viewProductOpendDay() {
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
			year = rows[0].poYear;
		    month = rows[0].poMonth;
		    channelCode = rows[0].poChannelCode;
		    productCode = rows[0].poProductCode;
		    batchNo = rows[0].poBatchNo;
			parent.$.modalDialog({
				title : '渠道产品产品非工作日设置查看',
				width : 1200,
				height : 450,
				href : '${ctx}/productOpenDay/viewPage?year=' + year
						+ '&month=' + month + '&channelCode=' + channelCode
						+ '&productCode=' + productCode
						+ '&batchNo=' + batchNo,
				buttons : [{
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				} ]
			});
			}
	}

	function delProductOpendDay() {
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
			year = rows[0].poYear;
		    month = rows[0].poMonth;
		    channelCode = rows[0].poChannelCode;
		    productCode = rows[0].poProductCode;
		    batchNo = rows[0].poBatchNo;
			parent.$.messager.confirm('询问', '您确认删除'+year+'年'+month+'月的数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/productOpenDay/delete', {
						year : year,
						month : month,
						channelCode : channelCode,
						productCode : productCode,
						batchNo : batchNo
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
	
	function checkProductOpendDay(){
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
			var poCheckState = rows[0].poCheckState;
			if(poCheckState=='01'){
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
				var poCuser = rows[0].poCuser;
				if(poCuser=='<shiro:principal/>'){
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
			year = rows[0].poYear;
		    month = rows[0].poMonth;
		    channelCode = rows[0].poChannelCode;
		    productCode = rows[0].poProductCode;
		    batchNo = rows[0].poBatchNo;
			parent.$.messager.confirm('询问', '您确认复核'+year+'年'+month+'月的数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/productOpenDay/checkSubmit', {
						year : year,
						month : month,
						channelCode : channelCode,
						productCode : productCode,
						batchNo : batchNo
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
				    <td></td>
					<th style="width: 20px">渠道编号:</th>
					<td><input class="easyui-combobox"  style = "width:250px;" id="channelCode"   name ="filter_channelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     validType:'length[1,50]',
					     "/></td>
				     <th>代销端产品代码:</th>
					 <td><input class="easyui-combobox"  style = "width:250px;"   id="productCode"   name ="filter_productCode" value = "" data-options="
					     url:'${ctx}/combobox/queryUsedProductInfoComBox',
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',
					     validType:'length[1,50]',
					     "/></td>
				</tr>
				<tr>
						<th >年度:</th>
						<td>
							<select id="year" name="filter_year" style="width: 150px"
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
					   </td>
						<th>月度:</th>
						<td>
							<select id="month" name="filter_month" style="width: 150px"
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
						</td>
						
						<th>复核状态:</th>
						<td>
							<select id="checkState" name="filter_checkState" style="width: 150px"
							class="easyui-combobox">
								<option value=''>请选择</option>
								<option value='00'>未复核</option>
								<option value='01'>已复核</option>
							</select>
						</td>
				</td>
				</tr>
				<tr>
				<td></td>
				<td></td>
				<td></td>
					<td align="center"><a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-search',plain:true"
						onclick="searchFun();">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel',plain:true"
						onclick="cleanFun();">重置</a>
				    </td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'产品非工作日管理列表'">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
			<a onclick="addProductOpendDay();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">产品非工作日新增</a>
			<a onclick="editProductOpendDay();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'">产品非工作日修改</a>
			<a onclick="viewProductOpendDay();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-tip'">产品非工作日查看</a>
			<a onclick="delProductOpendDay();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-del'">产品非工作日删除</a>
			<a onclick="checkProductOpendDay();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-ok'">产品非工作日复核</a>
	</div>
</body>
</html>