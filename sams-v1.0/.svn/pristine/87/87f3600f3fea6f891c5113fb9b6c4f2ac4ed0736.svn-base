<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
var dataGrid = "";
date = '${date}';
channelCodes = '${channelCodes}';
//加载页面表格
dataGrid = $("#participationProfitGrid").datagrid({
	url : '${ctx}/dayEnd/queryProfitList',
	striped : true,
	fitColumns: false,
	pagination : false,
	nowrap : false,
	idField : 'channelCode',
	queryParams:{"date":date,"channelCodes":channelCodes},
	columns : [[{
		title : '渠道编号',
		field : 'channelCode',
		width : 125,
		halign : 'center',
		align : 'center'
	},{
		title : '渠道名称',
		field : 'channelName',
		width : 125,
		halign : 'center',
		align : 'center'
	},{
		title : '产品编码',
		field : 'fundCode',
		width : 125,
		halign : 'center',
		align : 'center'
	},{
		title : '分红性质',
		field : 'profitNature',
		width : 125,
		halign : 'center',
		align : 'center',
		formatter : function(value){
			return value;
		}
	},{
		title : '本金',
		field : 'applicationAmount',
		width : 125,
		halign : 'center',
		align : 'center'
	},{
		title : '收益',
		field : 'earningsAmount',
		width : 125,
		halign : 'center',
		align : 'center',
		formatter : function(value){
			return value;
		}
	},{
		title : '总额',
		field : 'totalAmount',
		width : 125,
		halign : 'center',
		align : 'center'
	},{
		title : '查看',
		field : 'look',
		width : 100,
		halign : 'center',
		align : 'center',
		formatter : function(value){
			return "<a href='#'>查看</a>";
		}
	}
	]],
	onLoadSuccess : function(data){
		if(data.rows.length == 0 ){
			$(this).datagrid('appendRow', {channelCode: '<div style="text-align:center;color:red;font-weight:bold;">没有相关的分红数据!</div>' }).datagrid('mergeCells', { index: 0, field: 'channelCode', colspan: 8 });
		}
	},
	//点击单元格
	onClickCell : function(rowIndex, field, value){
		if("look" != field){
			return false;
		}
		var rows = $("#participationProfitGrid").datagrid("getRows");
		var row = rows[rowIndex];//rowIndex为行号
		var channelCode = row.channelCode;
		var channelName = row.channelName;
		var profitNature = row.profitNature; //分红性质
		var fundCode = row.fundCode;
		$('#detail').dialog({
		    title: channelName+"-"+fundCode+"-"+profitNature+"详情",
		    width: 800,
		    height: 400,
		    closed: false,
		    cache: false,
		    href: '${ctx}/dayEnd/queryProfitPage?channelCode='+channelCode+"&fundCode="+fundCode+"&date="+date,
		    modal: true
		});
	}
	
});
//取消
function endDaysendCanel(){
	$.modalDialog.handler.dialog('close');
}
//确定发送
function endDaysendOk (){
	parent.$.messager.confirm('警告', '是否查看完毕,确认发送文件？', function(b) {
        if (b) {
			$.modalDialog.handler.dialog('close');
			$.messager.progress({
				title : '提示',
				text : '正在上传,请等待....'
			});
			$.ajax({
		        url:"${ctx}/dayEnd/sendFile",    //请求的url地址
		        dataType:"json",   //返回格式为json
		        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
		        data:{"channelCodes":channelCodes,"transDate":date},    //参数值
		        type:"POST",   //请求方式
		        success :function(result){
		        	$.messager.progress('close');
		        	parent.$.modalDialog.openner_dataGrid.datagrid("load",{filter_date:date});
		        	$.messager.progress('close');
		        	alertMsg(result);
		        }
			});
        }else {
        	parent.$.messager.show({
             title : '提示',
             msg : '操作取消！',
             timeout : 2000
         });
     }
    });
	
}

//处理信息提示
function alertMsg(result){
	var data = result.data;
	var msg = "";
	data.forEach(function(item,index){
		if(item.msg != "success"){
			msg+=item.msg;
		}
	});
	if(msg == ""){
		$.messager.alert({
            title : '提示',
            msg : "处理完成！",
            timeout : 2000
        });
	}else{
		$.messager.alert({
            title : '提示',
            msg : msg,
            timeout : 2000
        });
	}
}
</script>
 <div class="easyui-accordion" data-options="multiple:false">
    <div data-options="region:'center',collapsed:false,collapsible:false,title:'分红数据列表'" style = "height:415px">
        <table id="participationProfitGrid" data-options="border:false"></table>
    </div>
    <div data-options="region:'south',collapsed:false,collapsible:false,border:false">
    	<span style= "color:red;padding-left:30%;font-weight:bold">检查分红数据无误后,点击下方确认按钮发送文件,如有疑问,请联系管理员</span><br/>
    	<a id = "end-day-send-ok" onclick="endDaysendOk();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" style= "margin-left:35%;" iconCls="icon-ok">确定</a>
        <a id = "end-day-send-canel" onclick="endDaysendCanel();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" style= "margin-left:5%;" iconCls="icon-cancel">取消</a>
    </div>
</div>
<div id = "detail"></div>
