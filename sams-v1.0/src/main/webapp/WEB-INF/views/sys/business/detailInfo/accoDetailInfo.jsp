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
<title>账户信息</title>
<script type="text/javascript">
var accoDataGrid = "";
var accoType = "";
$(function(){
	accoType = '${type}';
	var url = '${ctx}/dayEnd/queryFailedInfo';
	//加载页面表格
	accoDataGrid = $("#accouDetailData").datagrid({
		url : url,
		striped : true,
		pagination : true,
		pageSize : 10,
        sortName : 'TRANSDATE',
        sortOrder : 'desc',
		pageList : [10,20, 40, 50, 100, 200, 300,
				400, 500 ],
		queryParams:{
			filter_type:accoType},
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
			title : '申请单编号',
			field : 'APPSHEETSERIALNO',
			width : 200,
			halign : 'center',
			align : 'center',
            sortable : true
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
			title : '投资人基金交易账号',
			field : 'TRANSACTIONACCOUNTID',
			width : 140,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '投资人基金账号',
			field : 'TAACCOUNTID',
			width : 140,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '交易类型',
			field : 'BUSINESSCODE',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true,
			formatter : function(value){
				if("001" == value)return "开户";
				if("002" == value || "009" == value)return "销户";
				if("003" == value)return "修改资料";
			}
		},{
			title : '交易发生日期',
			field : 'TRANSACTIONDATE',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
			title : '详细信息',
			field : 'ERRORDEC',
			width : 500,
			halign : 'center',
			align : 'center',
            nowrap:false,
            formatter : function(value){
        		if(value == null || value === null){
            		return "<span ></span>";
        		}else{
        			if(value.indexOf("成功") != -1){
                		return "<span title='" + value + "'>" + value + "</span>";
                	}
                	return "<span style = 'color:red;font-weight:bold;'  title='" + value + "'>" + value + "</span>";
        		}
   	 		}
		},{
			title : '处理结果',
			field : 'RETURNCODE',
			width : 150,
			halign : 'center',
			align : 'center',
            sortable : true,
			formatter : function(value){
				if("0000" == value){
					return "处理成功";
				}else{
					return "处理失败";
				}
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
	$("#acco-success-failed").combobox({
	     panelHeight: 'auto',//自适应
	     valueField: 'id',//绑定字段ID
	     textField: 'name',//绑定字段Name
	     onLoadSuccess:function(){
	         $(".combo").click(function(){
	            $(this).prev().combobox("showPanel");
	        });
	     },
	     data :[{
	         "id":"",
	         "name":"请选择"
	     },{
	         "id":"0000",
	         "name":"成功"
	     },{
	         "id":"9999",
	         "name":"失败"
	     }]
	});
});

//查询
function accouSearch (){
	var parmFilter = $.serializeObject($('#acco-searchForm'));
	accoDataGrid.datagrid('load', parmFilter);
}
//重置
function clean(){
	$('#acco-searchForm').form('reset');
    accoDataGrid.datagrid('load', {filter_type : "0"});
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false,title:'查询区域'"
	align="center" style="height: 120px;">
	 <form id="acco-searchForm">
	 <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
	  <tr>
		 <td align="center">
                                            查询渠道:<input class="easyui-combobox"  style = "width:200px;" prompt="请选择..." id="acco-channel-info"   name ="filter_channelCode" value = "${channelCode }" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" >
					<input type = "hidden" value = "${type }" name = "filter_type"/>
			         证件号:<input class="easyui-textbox" style = "width:200px;" id = "certificateno" name = "filter_certificateno"/>
   	                                 投资人基金交易账户:<input class="easyui-textbox" style = "width:200px;" id = "transactionAacountId" name = "filter_transactionAacountId"/>
   	                                基金账户:<input class="easyui-textbox" id = "acco-taaccountid" name = "filter_taAccountId"/>                      
   	      </td>
   	 </tr>
   	 
   	  <tr>
		 <td align="center">
   	                               查询日期:<input style= "width:120px;" class="easyui-datebox" id="acco-failed-Date" name="filter_transDate"/>
   	                               处理结果:<input style= "width:120px;" class="easyui-combobox" id="acco-success-failed" name="filter_handel"/>
   	      </td>
   	 </tr>
   	 
   	<tr>
   	   <td align="center">
            <a id = "accou-Data-search" onclick="accouSearch();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clean();">重置</a>
       </td>
    </tr>
   </table>
 </form>
</div>
   
<div data-options="region:'center',border:true,title:'账户交易数据查询'" >
   <table id="accouDetailData" data-options="fit:true,border:false"></table>
</div>
</body>
</html>
   
   