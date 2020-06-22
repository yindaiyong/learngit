<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
var dataGrid = "";
date = '${date}';
channelCode = '${channelCode}';
fundCode = '${fundCode}';
//加载页面表格
dataGrid = $("#profitDetailGrid").datagrid({
	url : '${ctx}/dayEnd/queryProfitDetail',
	striped : true,
	fitColumns: false,
	pagination : false,
	nowrap : false,
	idField : 'channelCode',
	queryParams:{"date":date,"channelCode":channelCode,"fundCode" : fundCode},
	columns : [[{
		title : '用户名称',
		field : 'investorName',
		width : 100,
		halign : 'center',
		align : 'center'
	},{
		title : '基金账号',
		field : 'taAccountId',
		width : 120,
		halign : 'center',
		align : 'center'
	},{
		title : '证件号',
		field : 'certificateno',
		width : 150,
		halign : 'center',
		align : 'center',
		formatter : function(value) {
            if(value != '' && value != null && value.length > 8){
                return value.replace(/^(.{4})(?:\w+)(.{4})$/, "$1****$2");
            }else{
                return value;
            }
        }
	},{
		title : '产品编码',
		field : 'fundCode',
		width : 100,
		halign : 'center',
		align : 'center',
		formatter : function(value){
			return value;
		}
	},{
		title : '本金',
		field : 'applicationAmount',
		width : 100,
		halign : 'center',
		align : 'center'
	},{
		title : '收益',
		field : 'earningsAmount',
		width : 100,
		halign : 'center',
		align : 'center',
		formatter : function(value){
			return value;
		}
	},{
		title : '总额',
		field : 'totalAmount',
		width : 100,
		halign : 'center',
		align : 'center'
	},
	{
		title : '合同号',
		field : 'inContract',
		width : 100,
		halign : 'center',
		align : 'center'
	},
	/* {
		title : '流水号',
		field : 'originalSerialNo',
		width : 150,
		halign : 'center',
		align : 'center'
	}, */
	]]
});
</script>
 
<div class="easyui-accordion" data-options="multiple:false">
    <div data-options="region:'center',collapsed:false,collapsible:false">
        <table id="profitDetailGrid" data-options="border:false"></table>
    </div>
 </div>
