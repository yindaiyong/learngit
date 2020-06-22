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
<title>非居民用户信息展示</title>
</head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		//初始化查询条件  channelCode查询条件
		 $('#ciChannelCode').combobox({
			 url:"${ctx}/combobox/queryAllChannelInfo", //访问controller的路径
			    valueField:'ID',
			    textField:'NAME',
			    multiple : false,
                panelHeight:200,
		        onShowPanel : function () {
		            $('#ciChannelCode').combobox('clear');
		            $('#ciChannelCode').combobox('reload','${ctx}/combobox/queryAllChannelInfo');
		        },
		 });

		 //数据展示信息
		dataGrid = $('#dataGrid')
				.datagrid({
							url : '${ctx}/unResidentInfo/getUnResidentInfo',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'ciId',
							sortName : '',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ []],
							 columns : [[{
                                 title : '渠道编号',
                                 field : 'channelCode',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '申请单编号',
                                 field : 'appSheetSerialNo',
                                 width : 200,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             }, {
                                 title : '交易发生日期',
                                 field : 'transactionDate',
                                 width : 100,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             }, {
                                 title : '交易发生时间',
                                 field : 'transactionTime',
                                 width : 100,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '销售人代码',
                                 field : 'distributorCode',
                                 width : 160,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             }, {
                                 title : '个人/机构标志',
                                 field : 'individualOrInstitution',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return "0(机构)";
                                     }
                                     if (value == "1") {
                                         return "1(个人)";
                                     }
                                     if (value == "2") {
                                         return "2(产品)";
                                     }else {
                                         return value;
                                     }
                                 }
                             }, {
                                 title : '调查规则',
                                 field : 'surveyMethod',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return "0(新开账户)";
                                     }
                                     if (value == "1") {
                                         return "1(存量账户)";
                                     }else{
                                         return value;
                                     }
                                 }
                             }, {
                                 title : '取得投资人声明',
                                 field : 'getInvestCerFlag',
                                 width : 100,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return "否";
                                     }
                                     if (value == "1") {
                                         return "是";
                                     }else{
                                         return value;
                                     }
                                 }

                             }, {
                                 title : '非居民标识',
                                 field : 'nonResiFlag',
                                 width : 200,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return "0(仅为中国税收居民)";
                                     }
                                     if (value == "1") {
                                         return "1(仅为非居民)";
                                     }
                                     if (value == "2") {
                                         return "2(同为中国和其它国税收居民)";
                                     }
                                     if (value == "3") {
                                         return "3(不配合客户)";
                                     }else{
                                         return value;
                                     }
                                 }
                             }, {
                                 title : '现居国家',
                                 field : 'livingCountry',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },/*{
                                 title : '现居地址',
                                 field : 'livingAddress3',
                                 width : 250,
                                 halign : 'center',
                                 align : 'center'
                             },*/ {
                                 title : '税收居民国',
                                 field : 'taxCountry',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             }, {
                                 title : '增删标识',
                                 field : 'addFlag',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return value+"(作废)";
                                     }
                                     if (value == "1") {
                                         return value+"(新增)";
                                     }else{
                                         return value;
                                     }
                                 }
                             },{
                                 title : '详情信息',
                                 field : 'errorDec',
                                 width : 800,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value){
                                     if(value == null || value === null){
                                         return "<span ></span>";
                                     }else{
                                         return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                                     }
                                 }
                             } ] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								// $("#dataGrid").datagrid('clearSelections');
							}
						});
  	                });
    //查询
    function searchFun() {
        var parmFilter = $.serializeObject($('#searchForm'));
        dataGrid.datagrid('load', parmFilter);
    }
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
        <form id="searchForm">
            <table align="center">
                <tr>
                    <th>渠道名称:</th>
                    <td><input id="ciChannelCode" name="filter_ciChannelCode" class="easyui-combobox" style = "width:200px;" /></td>
                    <th>交易日期：</th>
                    <td><input style= "width:120px;" class="easyui-datebox" id="trans-failed-Date" name="filter_transDate"/></td>
                </tr>
                <tr><td colspan="5" align="center">
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                </td></tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'非居民信息展示'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
    </div>
</body>
</html>