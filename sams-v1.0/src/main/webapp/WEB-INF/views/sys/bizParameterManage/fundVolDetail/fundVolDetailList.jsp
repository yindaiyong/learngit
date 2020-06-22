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
<title>份额明细信息展示</title>
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
							url : '${ctx}/fundVolDetail/getAllFundVolDetail',
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
                                 field : 'fvdChannelCode',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '交易日期',
                                 field : 'fvdTransDate',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '持有人可用基金份数',
                                 field : 'fvdAvailableVol',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '基金总份数',
                                 field : 'fvdTotalVolOfDistributor',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '交易确认日期',
                                 field : 'fvdTransactionCfmDate',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '基金代码',
                                 field : 'fvdFundCode',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '投资人基金交易账号',
                                 field : 'fvdTransactionAccountId',
                                 width : 200,
                                 halign : 'center',
                                 align : 'center'
                             }, {
                                 title : '销售人代码',
                                 field : 'fvdDistributorCode',
                                 width : 100,
                                 halign : 'center',
                                 align : 'center'
                             }, {
                                 title : '投资人基金账号',
                                 field : 'fvdTAAccountId',
                                 width : 100,
                                 halign : 'center',
                                 align : 'center',
                             },{
                                 title : '基金冻结总份数',
                                 field : 'fvdTotalFrozenVol',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '网点号码',
                                 field : 'fvdBranchCode',
                                 width : 80,
                                 halign : 'center',
                                 align : 'center'
                             },{
                                 title : '收费方式',
                                 field : 'fvdShareClass',
                                 width : 150,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return "0（前收费）";
                                     }
                                     else if (value == "1") {
                                         return "1（后收费）";
                                     }else{
                                         return value;
                                     }

                                 }
                             },{
                                 title : '明细标志',
                                 field : 'fvdDetailFlag',
                                 width : 150,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true,
                                 formatter : function(value) {
                                     if (value == "0") {
                                         return "0（非明细）";
                                     }
                                     else if (value == "1") {
                                         return "1（明细）";
                                     }else{
                                         return value;
                                     }

                                 }
                             },{
                                 title : '份额注册日期',
                                 field : 'fvdShareRegisterDate',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '可赎回日期',
                                 field : 'fvdAllowRedemptDate',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
                                 sortable : true
                             },{
                                 title : '实际可赎回日期',
                                 field : 'fvdRelAllowRedemptDate',
                                 width : 90,
                                 halign : 'center',
                                 align : 'center',
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
                    <th>注册日期：</th>
                    <td><input style= "width:200px;" class="easyui-datebox" id="trans-failed-Date" name="filter_transDate"/></td>
                </tr>
                <tr>
                    <th>交易日期:</th>
                    <td><input style= "width:200px;" class="easyui-datebox" id="trans-cfm-Date" name="filter_transDateCfm"/></td>
                    <th>可赎回日期：</th>
                    <td><input style= "width:200px;" class="easyui-datebox" id="trans-allow-Date" name="filter_transDateAllow"/></td>
                </tr>
                <tr><td colspan="5" align="center">
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                </td></tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'份额明细数据'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
    </div>
</body>
</html>