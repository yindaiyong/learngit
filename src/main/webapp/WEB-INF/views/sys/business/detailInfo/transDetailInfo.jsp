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
<title>交易信息</title>
<script type="text/javascript">
var transDataGrid = "";
var transType = "";
$(function(){
	
	//渠道产品关系级联     created by wangchao 20200610 渠道产品级联关系设置 
	 $('#trans-channel-info').combobox({  
        onChange: function (row) {
       	 var channelCode = $("#trans-channel-info").combobox("getValue"); 
            $('#trans-channel-product').combobox({
            	url: '${ctx}/combobox/queryAllProductInfoComBox',
               onBeforeLoad:function(param){
                	param.channelCode = channelCode;
	            },
				valueField:'ID',
			    textField:'NAME'
            });
        }
    });
	
	transType = '${type}';
	var url = '${ctx}/dayEnd/queryFailedInfo';
		//加载页面表格
		transDataGrid = $("#transDetailData").datagrid({
			url : url,
			striped : true,
			pagination : true,
			pageSize : 10,
            sortName : 'TRANSDATE',
            sortOrder : 'desc',
			pageList : [10,20, 40, 50, 100, 200, 300,
					400, 500 ],
			queryParams:{
				filter_type:transType},
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
				title : '基金代码',
				field : 'FUNDCODE',
				width : 100,
				halign : 'center',
				align : 'center',
                sortable : true
			},{
				title : '产品类型',
				field : 'PRODUCTTYPE',
				width : 100,
				halign : 'center',
				align : 'center',
                sortable : true
			},{
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
				title : '投资人基金交易账号',
				field : 'TRANSACTIONAACOUNTID',
				width : 200,
				halign : 'center',
				align : 'center'
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
                sortable : true
			},{
				title : '交易发生日期',
				field : 'TRANSACTIONDATE',
				width : 100,
				halign : 'center',
				align : 'center',
                sortable : true
			},{
				title : '交易币种',
				field : 'CURRENCYTYPE',
				width : 100,
				halign : 'center',
				align : 'center',
                sortable : true,
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
				title : '合同号',
				field : 'OUTCONTRACT',
				width : 200,
				halign : 'center',
				align : 'center'
			},{
				title : '交易结果',
				field : 'ERRORDEC',
				width : 450,
				halign : 'center',
				align : 'center',
                sortable : true,
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
				width : 450,
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
			//加载成功
			onLoadSuccess: function (data) {
				//去掉标题上的复选框
			   $(".datagrid-header-check").html("");
			}
			
		});
	//给combobox赋值
	$("#trans-success-failed").combobox({
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
function transSearch (){
	var parmFilter = $.serializeObject($('#trans-searchForm'));
	transDataGrid.datagrid('load', parmFilter);
}
//重置
function clean(){
	$('#trans-searchForm').form('reset');
	transDataGrid.datagrid('load', {filter_type : "1"});
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false,title:'查询区域'"
	align="center" style="height: 120px;">
	 <form id="trans-searchForm">
	 <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
    <tr>
         <td align="center">
			 查询渠道:<input class="easyui-combobox"  prompt="请选择..." style = "width:200px;"  id="trans-channel-info"   name ="filter_channelCode" value = "${channelCode }" data-options="
								url:'${ctx}/combobox/queryChannelInfo',
								valueField:'ID',
								textField:'NAME'" >
			查询产品:<input class="easyui-combobox"  style = "width:200px;"  id="trans-channel-product"   name ="filter_productCode" data-options="
								url:'${ctx}/combobox/queryAllProductInfoComBox',
					            onBeforeLoad:function(param){
					                 param.channelCode = '';
					            },
					            valueField:'ID',
					            textField:'NAME'" >
								<input type = "hidden" value = "${type }" name = "filter_type"/>
			投资人基金交易账户:<input class="easyui-textbox" style = "width:200px;" id = "transactionAacountId" name = "filter_transactionAacountId"/>
			基金账户:<input class="easyui-textbox" id = "trans-taaccountid" name = "filter_taAccountId"/>
   	      </td>
    </tr>
    
    <tr>
         <td align="center">
		   	查询日期:<input style= "width:160px;" class="easyui-datebox" id="trans-failed-Date" name="filter_transDate"/>
		   	处理结果:<input style= "width:160px;" class="easyui-combobox" id="trans-success-failed" name="filter_handel"/>
		          交易类型:<input class="easyui-combobox"  prompt="请选择..." style = "width:200px;"  id="trans-business-code"   name ="filter_businessCode"  data-options="
								url:'${ctx}/sysDict/businessCodeGroup',
								valueField:'dictCode',
								textField:'dictName'" >
	                     产品类型:<input class="easyui-combobox"  style = "width:250px;"  id="productType"   name ="filter_productType" value = "" data-options="
		                    url:'${ctx}/sysDict/productTypeGroup',
		                    valueField: 'dictCode',
		                    textField: 'dictName'"/>
   	     </td>
   	</tr>
   	
   	<tr><td align="center">
       <a id = "trans-Data-search" onclick="transSearch();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clean();">重置</a>
       </td>
    </tr>
   </table>
 </form>
</div>
   
<div data-options="region:'center',border:true,title:'交易数据查询'" >
   <table id="transDetailData" data-options="fit:true,border:false"></table>
</div>
</body>
</html>
   
   