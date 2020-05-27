<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> .dialog-button { padding: 5px; text-align: center; background-color: ' #E0ECFF;' } </style>
<head>
<title>对账信息</title>
<script type="text/javascript">
var accouReconData = "";
$(function(){
	//加载页面表格
	accouReconData = $("#accouReconData").datagrid({
		url : '${ctx}/additional/queryAccontReconInfo',
		striped : true,
		pagination : true,
		pageSize : 10,
		pageList : [10,20, 40, 50, 100, 200, 300,
				400, 500 ],
		columns : [[{
			title : '渠道编码',
			field : 'CHANNELCODE',
			width : 100,
			halign : 'center',
			align : 'center',
			sortable : true
		},{
			title : '渠道名称',
			field : 'CHANNELNAME',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '持有人可用基金份数',
			field : 'AVAILABLEVOL',
			width : 150,
			halign : 'center',
			align : 'center'
		},{
			title : '基金总份数',
			field : 'TOTALVOLOFDISTRIBUTORINTA',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '货币基金未付收益金额',
			field : 'UNDISTRIBUTEMONETARYINCOME',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '每日新增收益',
			field : 'GUARANTEEDAMOUNT',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '交易确认日期',
			field : 'TRANSACTIONCFMDATE',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '基金代码',
			field : 'FUNDCODE',
			width : 100,
			halign : 'center',
			align : 'center'
		},{
			title : '投资人基金交易账号',
			field : 'TRANSACTIONACCOUNTID',
			width : 160,
			halign : 'center',
			align : 'center'
		},{
			title : '销售人代码',
			field : 'DISTRIBUTORCODE',
			width : 90,
			halign : 'center',
			align : 'center'
		},{
			title : '投资人基金账号',
			field : 'TAACCOUNTID',
			width : 120,
			halign : 'center',
			align : 'center'
		},{
			title : '网点号码',
			field : 'BRANCHCODE',
			width : 100,
			halign : 'center',
			align : 'center',
		},{
			title : '收费方式',//0-前收费，1-后收费
			field : 'SHARECLASS',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true,
			formatter : function(value){
				if(value == '0') return value+"(前收费)";
				if(value == '1') return value+"(后收费)";
			}
		},{
			title : '明细标志',//0-非明细，1-明细
			field : 'DETAILFLAG',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true,
			formatter : function(value){
				if(value == '0') return value+"(非明细)";
				if(value == '1') return value+"(明细)";
			}
		}
		]],
		toolbar : '#toolbar',
		//加载成功
		onLoadSuccess: function (data) {
			//去掉标题上的复选框
		   $(".datagrid-header-check").html("");
		},
	});
});

//查询
function accouReconSearch (){
	var parmFilter = $.serializeObject($('#acco-recon-searchForm'));
	accouReconData.datagrid('load', parmFilter);
}

function clean(){
	$('#acco-recon-searchForm').form('reset');
	accouReconData.datagrid('load',{});
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false,title:'查询区域'"
	align="center" style="height: 120px;">
	 <form id="acco-recon-searchForm">
	 <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
	  <tr>
		 <td align="center">
                                            查询渠道:<input class="easyui-combobox"  style = "width:200px;" prompt="请选择..." id="acco-channel-info"   name ="filter_channelCode" value = "${channelCode }" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" >
					查询产品:<input class="easyui-combobox"  style = "width:200px;"  id="recon-channel-product"   name ="filter_productCode" data-options="
								url:'${ctx}/combobox/queryAllProductInfoComBox',
								valueField:'ID',
								textField:'NAME'" >
					投资人基金交易账户:<input class="easyui-textbox" id = "recon-transactionAccountId" name = "filter_transactionAccountId"/>
					基金账户:<input class="easyui-textbox" id = "recon-taaccountid" name = "filter_taAccountId"/>
   	                                                 查询日期:<input style= "width:120px;" class="easyui-datebox" id="acco-failed-Date" name="filter_transDate"/>
   	      </td>
   	 </tr>
   	 
   	<tr>
   	   <td align="center">
   	    历史:<input type ="checkbox" name="filter_his" value = "his" style = "width:20px;margin-top:5px;"/>
            <a onclick="accouReconSearch();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clean();">重置</a>
       </td>
    </tr>
   </table>
 </form>
</div>
   
<div data-options="region:'center',border:true,title:'对账数据查询'" >
   <table id="accouReconData" data-options="fit:true,border:false"></table>
</div>
</body>
</html>