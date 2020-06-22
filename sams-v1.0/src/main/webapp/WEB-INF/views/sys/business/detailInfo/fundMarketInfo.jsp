<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> .dialog-button { padding: 5px; text-align: center; /*background-color: ' #E0ECFF;'*/ } </style>
<head>
<title>行情信息</title>
<script type="text/javascript">
var accoDataGrid = "";
var accoType = "";

$(function(){

    //渠道产品关系级联
    $('#piChannelCode').combobox({
        onChange: function (row) {
            var channelCode = $("#piChannelCode").combobox("getValue");
            $('#piChannelProductCode').combobox({
                url: '${ctx}/combobox/queryAllProductInfoComBox',
                onBeforeLoad:function(param){
                    param.channelCode = channelCode;
                },
                valueField:'ID',
                textField:'NAME'
            });
        }
    });

	var url = '${ctx}/marketHanding/getFundMarketInfo';
	//加载页面表格
	accoDataGrid = $("#accouDetailData").datagrid({
		url : url,
		striped : true,
		pagination : true,
		pageSize : 10,
        sortName : 'fmBusinessDate',
        sortOrder : 'desc',
		pageList : [10,20, 40, 50, 100, 200, 300,
				400, 500 ],
		queryParams:{
			filter_type:accoType},
		columns : [[{
			title : '渠道编码',
			field : 'fmChannelCode',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
            title : '渠道名称',
            field : 'channelName',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
			title : '产品编号',
			field : 'fmFundCode',
			width : 100,
			halign : 'center',
			align : 'center',
            sortable : true
		},{
            title : '产品名称',
            field : 'fmFundName',
            width : 180,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '产品类型',
            field : 'theProductType',
            width : 120,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '操作日期',
            field : 'fmBusinessDate',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '基金状态',
            field : 'fmFundStatus',
            width : 120,
            halign : 'center',
            align : 'center',
            sortable : true,
            formatter : function(value){
                if(value == null || value === null || typeof(value)=="undefined"){
                    return "(无效状态)";
                }else{
					if(value == '0'){
					    return value+'(可申购赎回)';
					}else if(value == '1'){
                        return value+'(发行)';
					}else if(value == '4'){
                        return value+'(停止申购赎回)';
                    }else if(value == '5'){
                        return value+'(停止申购)';
                    }else if(value == '6'){
                        return value+'(停止赎回)';
                    }else if(value == '8'){
                        return value+'(基金终止)';
                    }else if(value == '9'){
                        return value+'(基金封闭)';
                    }else{
					    return value+'(无效状态)';
					}
                }
            }
        },{
            title : '基金单位净值',
            field : 'fmNAV',
            width : 100,
            halign : 'center',
            align : 'center'
        },{
            title : '基金净值日期',
            field : 'fmUpdateDate',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '累计产品单位净值',
            field : 'fmAccumulativeNav',
            width : 120,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '净值类型',
            field : 'fmNetValueType',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true,
            formatter : function(value){
                if(value == null || value === null || typeof(value)=="undefined"){
                    return "(无效状态)";
                }else{
                    if(value == '0'){
                        return value+'(普通净值)';
                    }else if(value == '1'){
                        return value+'(申购净值)';
                    }else if(value == '2'){
                        return value+'(赎回净值)';
                    }else{
                        return value+'(无效状态)';
                    }
                }
            }
        },{
            title : '基金规模',
            field : 'fmFundSize',
            width : 100,
            halign : 'center',
            align : 'center'
        },{
            title : '结算币种',
            field : 'fmCurrencyType',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true,
            formatter : function(value){
                if(value == null || value === null || typeof(value)=="undefined"){
                    return "(暂不支持)";
                }else{
                    if(value == '156'){
                        return value+'(人民币)';
                    }else if(value == '840'){
                        return value+'(美元)';
                    }else{
                        return value+'(暂不支持)';
                    }
                }
            }
        },{
            title : '募集开始日期',
            field : 'fmIpostartDate',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '募集结束日期',
            field : 'fmIpoendDate',
            width : 100,
            halign : 'center',
            align : 'center',
            sortable : true
        },{
            title : '货币基金万份收益',
            field : 'fmFundIncome',
            width : 130,
            halign : 'center',
            align : 'center'
        },{
            title : '货币基金七日年化',
            field : 'fmYield',
            width : 130,
            halign : 'center',
            align : 'center'
        }]]
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
			 查询渠道:<input class="easyui-combobox"  style = "width:200px;" prompt="请选择..." id="piChannelCode"   name ="filter_channelCode" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" >
			 产品名称:<input class="easyui-combobox"  style = "width:250px;"  id="piChannelProductCode"   name ="filter_fundCode" value = ""  data-options="
					     url:'${ctx}/combobox/queryAllProductInfoComBox',
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',
					     "/>
			 产品类型:<input class="easyui-combobox"  style = "width:250px;"  id="fundType"   name ="filter_fundType" value = ""  data-options="
					     url:'${ctx}/combobox/queryFundTypeComBox',
					     valueField:'ID',
					     textField:'NAME'"/>
			 查询日期:<input style= "width:120px;" class="easyui-datebox" id="piTransDate" name="filter_transDate"/>
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
   
<div data-options="region:'center',border:true,title:'行情明细数据查询'" >
   <table id="accouDetailData" data-options="fit:true,border:false"></table>
</div>
</body>
</html>
   
   