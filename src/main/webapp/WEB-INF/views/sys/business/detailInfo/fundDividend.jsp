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
<title>分红信息</title>
<script type="text/javascript">
var fundDividendData = "";
$(function(){
	
	//渠道产品关系级联     created by wangchao 20200610 渠道产品级联关系设置 
	 $('#dividend-channel-info').combobox({  
        onChange: function (row) {
       	 var channelCode = $("#dividend-channel-info").combobox("getValue"); 
            $('#dividend-channel-product').combobox({
            	url: '${ctx}/combobox/queryAllProductInfoComBox',
               onBeforeLoad:function(param){
                	param.channelCode = channelCode;
	            },
				valueField:'ID',
			    textField:'NAME'
            });
        }
    });
	
	//加载页面表格
	fundDividendData = $("#fundDividendData").datagrid({
		url : '${ctx}/additional/queryfundDividendInfo',
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
			title : '交易确认日期',
			field : 'TRANSACTIONCFMDATE',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '结算币种',
			field : 'CURRENCYTYPE',
			width : 100,
			halign : 'center',
			align : 'center',<!--created by wangchao 20200610 前端排序控件属性控制效果的优化 -->
			sortable : true,
			formatter : function(value){
				if("156" == value) return value+"(人民币)";
				if("840" == value) return value+"(美元)";
				return value;
			}
		},{
			title : '分红日/发放日',
			field : 'DIVIDENTDATE',
			width : 140,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '基金账户红利资金',
			field : 'DIVIDENDAMOUNT',
			width : 120,
			halign : 'center',
			align : 'center',
			sortable : true
		},{
			title : '除权日',
			field : 'XRDATE',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '基金代码',
			field : 'FUNDCODE',
			width : 90,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '投资人基金账号',
			field : 'TAACCOUNTID',
			width : 120,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '投资人基金交易账号',
			field : 'TRANSACTIONACCOUNTID',
			width : 150,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '权益登记日期',
			field : 'REGISTRATIONDATE',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '销售人代码',
			field : 'DISTRIBUTORCODE',
			width : 100,
			halign : 'center',
			align : 'center',
			sortable : true
		},{
			title : '网点号码',
			field : 'BRANCHCODE',
			width : 100,
			halign : 'center',
			align : 'center',
			sortable : true
		},{
			title : '分红单位',
			field : 'DRAWBONUSUNIT',
			width : 100,
			halign : 'center',
			align : 'center',
			sortable : true
		},{
			title : 'TA确认交易流水号',
			field : 'TASERIALNO',
			width : 200,
			halign : 'center',
			align : 'center',
            sortable : true
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
	var parmFilter = $.serializeObject($('#fund-dividend-searchForm'));
	fundDividendData.datagrid('load', parmFilter);
}

function clean(){
	$('#fund-dividend-searchForm').form('reset');
	fundDividendData.datagrid('load',{});
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false,title:'查询区域'"
	align="center" style="height: 120px;">
	 <form id="fund-dividend-searchForm">
	 <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
	  <tr>
		 <td align="center">
                                            查询渠道:<input class="easyui-combobox"  style = "width:200px;" prompt="请选择..." id="dividend-channel-info"   name ="filter_channelCode" value = "${channelCode }" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" >
					查询产品:<input class="easyui-combobox"  style = "width:200px;"  id="dividend-channel-product"   name ="filter_productCode" data-options="
								url:'${ctx}/combobox/queryAllProductInfoComBox',
					            onBeforeLoad:function(param){
					                 param.channelCode = '';
					            },
					            valueField:'ID',
					            textField:'NAME'," >
					投资人基金交易账户:<input class="easyui-textbox" id = "dividend-transactionAccountId" name = "filter_transactionAccountId"/>
					基金账户:<input class="easyui-textbox"  id = "dividend-taaccountid" name = "filter_taAccountId"/>
   	                                                 查询日期:<input style= "width:120px;" class="easyui-datebox" id="acco-failed-Date" name="filter_transDate"/>
   	      </td>
   	 </tr>
   	 
   	<tr>
   	   <td align="center">
            <a onclick="accouReconSearch();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clean();">重置</a>
       </td>
    </tr>
   </table>
 </form>
</div>
   
<div data-options="region:'center',border:true,title:'分红数据查询'" >
   <table id="fundDividendData" data-options="fit:true,border:false"></table>
</div>
</body>
</html>