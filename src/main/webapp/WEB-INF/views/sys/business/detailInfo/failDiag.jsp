<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
$(function(){
	var type = '${type}';
	var channelCode = '${channelCode}';
	var transDate = '${transDate}';
	var url = "${ctx}/dayEnd/transAccoFailed";
	if(type == "0"){ //账户
		//加载页面表格
		dataGrid = $("#DetailData").datagrid({
			url : url,
			striped : true,
			pagination : true,
			pageSize : 10,
			pageList :[10],
			queryParams:{
				type:type,channelCode:channelCode,transDate:transDate},
			columns : [[{
				title : '申请单编号',
				field : 'APPSHEETSERIALNO',
				width : 200,
				halign : 'center',
				align : 'center'
			},{
				title : '投资人姓名',
				field : 'INVESTORNAME',
				width : 140,
				halign : 'center',
				align : 'center'
			},{
				title : '投资人证件号',
				field : 'CERTIFICATENO',
				width : 200,
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
				title : '交易发生日期',
				field : 'TRANSACTIONDATE',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '投资人基金交易账号',
				field : 'TRANSACTIONACCOUNTID',
				width : 140,
				halign : 'center',
				align : 'center'
			},{
				title : '交易类型',
				field : 'BUSINESSCODE',
				width : 100,
				halign : 'center',
				align : 'center',
				formatter : function(value){
					if("001" == value)return "开户";
					if("002" == value || "009" == value)return "销户";
					if("003" == value)return "修改资料";
				}
			},{
				title : '投资人电话号码',
				field : 'TELNO',
				width : 140,
				halign : 'center',
				align : 'center',
                formatter : function(value) {
                    if(value != '' && value != null && value.length > 7){
                        return value.replace(/^(.{3})(?:\w+)(.{4})$/, "$1****$2");
                    }else{
                        return value;
                    }
                }
			},{
				title : '渠道编码',
				field : 'CHANNELCODE',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '渠道名称',
				field : 'CHANNELNAME',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '错误信息',
				field : 'ERRORDEC',
				width : 500,
				halign : 'center',
				align : 'center',
                formatter : function(value){
                    if(value == null || value === null){
                        return "<span ></span>";
                    }else{
                        return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                    }
                }
			}
			]],
			//加载成功
			onLoadSuccess: function (data) {
				//去掉标题上的复选框
			   $(".datagrid-header-check").html("");
			},
		});
	}
	
	if(type == "1"){ //交易
		//加载页面表格
		dataGrid = $("#DetailData").datagrid({
			url : url,
			striped : true,
			pagination : true,
			pageSize : 10,
			pageList :[10],
			queryParams:{
				type:type,channelCode:channelCode,transDate:transDate},
			columns : [[{
				title : '申请单编号',
				field : 'APPSHEETSERIALNO',
				width : 200,
				halign : 'center',
				align : 'center'
			},{
				title : '投资人姓名',
				field : 'INVESTORNAME',
				width : 140,
				halign : 'center',
				align : 'center'
			},{
				title : '投资人证件号',
				field : 'CERTIFICATENO',
				width : 200,
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
				title : '基金账号',
				field : 'TAACCOUNTID',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '交易类型',
				field : 'BUSINESSCODE',
				width : 100,
				halign : 'center',
				align : 'center',
			},{
				title : '交易发生日期',
				field : 'TRANSACTIONDATE',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '基金代码',
				field : 'FUNDCODE',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '渠道编码',
				field : 'CHANNELCODE',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '渠道名称',
				field : 'CHANNELNAME',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '交易币种',
				field : 'CURRENCYTYPE',
				width : 100,
				halign : 'center',
				align : 'center',
				formatter : function(value){
                    if(typeof(value)!="undefined" && value != null){
                        if("156" == value)return value+"(人民币)";
                        if("840" == value)return value+"(美元)";
                        return value+"(暂不支持)";
                    }else{
                        return "(暂不支持)";
                    }
				}
				
			},{
				title : '交易金额',
				field : 'APPLICATIONAMOUNT',
				width : 100,
				halign : 'center',
				align : 'center'
			},{
				title : '交易结果',
				field : 'ERRORDEC',
				width : 450,
				halign : 'center',
				align : 'center',
                formatter : function(value){
                    if(value == null || value === null){
                        return "<span ></span>";
                    }else{
                        return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                    }
                }
			}
			]],
			//加载成功
			onLoadSuccess: function (data) {
				//去掉标题上的复选框
			   $(".datagrid-header-check").html("");
			}
		});
	}
});
</script>
<table id="DetailData" data-options="fit:true,border:false"></table>