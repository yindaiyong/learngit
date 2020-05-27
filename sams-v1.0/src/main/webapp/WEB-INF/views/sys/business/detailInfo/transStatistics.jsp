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
<title>交易数据统计</title>
<script type="text/javascript">
var dataGrid = "";
$(function(){
	var randomNu = (new Date().getTime())^Math.random();
	var parmFilter = $.serializeObject($('#trans-statisticsForm'));
	//交易数据汇总
	dataGrid = $('#transStatisticsData').datagrid({
		url : '${ctx}/dayEnd/transStatistics',
		pagination : true,
		pageSize : 10,
		pageList : [10,20, 40, 50, 100, 200, 300,
				400, 500 ],
		rownumbers : true,
		fit :false,
		type:"POST",
		border : false,
		queryParams : parmFilter,
		columns : [[
        {title:'渠道编码',field : 'CHANNELCODE',width : 100,halign:'center',align:'center'},
        {title:'渠道名称',field : 'CHANNELNAME',width : 130,halign:'center',align:'center'},
        {title:'产品代码',field : 'FUNDCODE',width : 100,halign:'center',align:'center'},
        {title:'认购总额',field : 'RG',width : 80,halign:'center',align:'center',formatter : function(value,row){
        	return getValue(value,row);
        }},
        {title:'认购确认',field : 'CONFIRMRG',width : 100,halign:'center',align:'center',formatter : function(value,row){
        	return getValue(value,row);
        }},
        {title:'申购总额',field : 'SG',width : 80,halign:'center',align:'center'},
        {title:'申购确认',field : 'CONFIRMSG',width : 100,halign:'center',align:'center'},
        {title:'赎回总额',field : 'SH',width : 80,halign:'center',align:'center',formatter : function(value,row){
        	return getValue(value,row);
        }},
        {title:'赎回确认',field : 'CONFIRMSH',width : 100,halign:'center',align:'center',formatter : function(value,row){
        	return getValue(value,row);
        }},
        {title:'成立总额',field : 'CONFIRMCL',width : 100,halign:'center',align:'center',formatter : function(value,row){
        	return getValue(value,row);
        }},
        {title:'到期总额',field : 'CONFIRMDQ',width : 100,halign:'center',align:'center'},
        {title:'强赎总额',field : 'CONFIRMQS',width : 100,halign:'center',align:'center'},
        ]],
        //点击单元格
		onClickCell : function(rowIndex, field, value){
			var rows = $("#transStatisticsData").datagrid("getRows");
			var row = rows[rowIndex];//rowIndex为行号
			var flag = false;
			if("8" == row.PRODUCTTYPE &&　value != undefined){
				flag = true;
			}
			//认购
			if("RG" == field && flag){
				detailDialog(row,'020',"认购金额");
			}
			//认购确认
			if("CONFIRMRG" == field && flag){
				detailDialog(row,'120',"认购结果金额");
			}
			//赎回
			if("SH" == field && flag){
				detailDialog(row,'024',"赎回金额");
			}
			//赎回确认
			if("CONFIRMSH" == field && flag){
				detailDialog(row,'124',"赎回结果金额");
			}
			//成立
			if("CONFIRMCL" == field && flag){
				detailDialog(row,'130',"成立金额");
			}
		}
	});
});
//转换格式
function getValue(value,row){
	var productType = row.PRODUCTTYPE;
	if(productType == '8'){return "<a href='#'>"+(value == undefined ? "" : "<span style = 'font-weight:bold;' title='" + value + "'>" + value + "</span>")+"</a>"}
	return value == undefined ? "" : value;
}

//多币种详情展示
function detailDialog(row,businessCode,title){
	var channelCode = row.CHANNELCODE;
	var fundcode = row.FUNDCODE;
	var startDate = $('#startDate').datebox('getValue').replace(/\-/g, "");
	var endDate = $('#endDate').datebox('getValue').replace(/\-/g, "");
	if("120,130,124".indexOf(businessCode) != -1){
		startDate = $('#confirmStartDate').datebox('getValue').replace(/\-/g, "");
		endDate = $('#confirmEndDate').datebox('getValue').replace(/\-/g, "");
	}
	
	parent.$.modalDialog({
        title : fundcode+"-【"+title+"】详细信息",
		width:250,
        height :150,
        modal: true,
        resizable:true,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/dayEnd/getMultipleDetail?channelCode='+channelCode
        		+"&fundCode="+fundcode
        		+"&businessCode="+businessCode
        		+"&startDate="+startDate
        		+"&endDate="+endDate
	});
	
}

//查询
function queryTransStatisticsData(){
	var parmFilter = $.serializeObject($('#trans-statisticsForm'));
    dataGrid.datagrid('load', parmFilter);
}
//交易发生日期
function transDate (type){
	var startDate = $('#startDate').datebox('getValue');//.replace(/\-/g, "");
	var endDate = $('#endDate').datebox('getValue');
	startDate = changeYearMonthDay(startDate,type);
	endDate = changeYearMonthDay(endDate,type);
	$('#startDate').datebox('setValue',startDate);
	$('#endDate').datebox('setValue',endDate);
} 
//交易确认日期
function confirmDate (type){
	var confirmStartDate = $('#confirmStartDate').datebox('getValue');//.replace(/\-/g, "");
	var confirmEndDate = $('#confirmEndDate').datebox('getValue');
	confirmStartDate = changeYearMonthDay(confirmStartDate,type);
	confirmEndDate = changeYearMonthDay(confirmEndDate,type);
	$('#confirmStartDate').datebox('setValue',confirmStartDate);
	$('#confirmEndDate').datebox('setValue',confirmEndDate)
}

//下拉多选
$(function(){
	var id = 'trans-statistics-product';
	var url = '${ctx}/combobox/queryAllProductInfoComBox';
	$('#'+id).combobox({
        url: url,//对应的ashx页面的数据源
        method: 'POST',
        valueField: 'ID',//绑定字段ID
        textField: 'NAME',//绑定字段Name
        width:250,
        multiple: true,
        formatter: function (row) {
            var opts = $(this).combobox('options');
            var id = row[opts.valueField];
            var value = row[opts.textField];
            return '<input type="checkbox" class="combobox-checkbox" id="' + id + '">' + value
        },
        
        onSelect: function (row) {
            var opts = $(this).combobox('options');
            var el = opts.finder.getEl(this, row[opts.valueField]);
            el.find('input.combobox-checkbox')._propAttr('checked', true);
        },
        onUnselect: function (row) {
            var opts = $(this).combobox('options');
            var el = opts.finder.getEl(this, row[opts.valueField]);
            el.find('input.combobox-checkbox')._propAttr('checked', false);
        } 
    });
});
//导出
function exportTransStatistics (){
	var parmFilter = $.serializeObject($('#trans-statisticsForm'));
	var data =  JSON.stringify(parmFilter);
	window.location.href = "${ctx}/downloadFile/exportTransStatistics?data="+data
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询区域'"
	align="center" style="height: 130px;">
	 <form id="trans-statisticsForm">
		 <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
			<tr>
			<td>查询渠道:<input class="easyui-combobox"  style = "width:250px;"  id="channel_info"   name ="filter_CHANNELCODE" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" ></td>
				<td>
					交易开始日期:<input class="easyui-datebox" id="startDate" name="filter_STARTDATE" value = "19000101"/>
				</td>
				<td>
					交易结束日期:<input class="easyui-datebox" id="endDate" name="filter_ENDDATE" value = "19000101"/>
				</td>
			</tr>
			<tr>
				<td>
					查询产品:<input class="easyui-combobox" id="trans-statistics-product" name ="filter_FUNDCODE"/>
				</td>
				<td>
					确认开始日期:<input class="easyui-datebox" id="confirmStartDate" name="filter_CONFIRMSTARTDATE" value = "19000101"/>
				</td>
				<td>
					确认结束日期:<input class="easyui-datebox" id="confirmEndDate" name="filter_CONFIRMENDDATE" value = "19000101"/>
				</td>
			</tr>
			
			<tr>
			<td>
				<a id = "query-transsattistics-data" onclick="queryTransStatisticsData();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
				<a id = "previous-tarns-date" onclick="transDate(-1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-undo">上一个交易日期</a>
				<a id = "previous-confirm-date" onclick="confirmDate(-1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-undo">上一个确认日期</a>
			</td>
			<td>
				<a id = "next-tarns-date" onclick="transDate(1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">下一个交易日期</a>
				<a id = "next-confirm-date" onclick="confirmDate(1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">下一个确认日期</a>
			</td>
			<td>
				<a id = "next-confirm-date" onclick="exportTransStatistics();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">导出</a>
			</td>
			</tr>
			<tr>
			
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'渠道交易金额统计'" >
			<table id="transStatisticsData"></table>
	</div>
</body>
</html>