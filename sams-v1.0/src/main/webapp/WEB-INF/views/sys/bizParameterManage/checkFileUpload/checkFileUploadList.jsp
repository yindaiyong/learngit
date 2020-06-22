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
<title>校验文件上传</title>
</head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
    	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
        buttons.splice(1, 0, {
            text: '清除',
            handler: function (target) {
                $(target).datebox("setValue","");
            }
        });
        $('.easyui-datebox').datebox({
            buttons: buttons
        });
        
        //设置时间（当前日期）
		var currTime = new Date();   
	    $("#tradeDate").datebox("setValue",myformatter(currTime));
	    var tradeDate = $('#tradeDate').datebox('getValue');
	    $("#TRANSDATE").datebox("setValue",myformatter(currTime));
	    var transDate = $('#TRANSDATE').datebox('getValue');
        
         //初始化查询条件 
		 $('#CHANNELCODE').combobox({
			 url:"${ctx}/combobox/queryAllChannelInfo", //访问controller的路径
			    valueField:'ID',
			    textField:'NAME',
			    multiple : false,
		        onShowPanel : function () {
		            $('#CHANNELCODE').combobox('clear');
		            $('#CHANNELCODE').combobox('reload','${ctx}/combobox/queryAllChannelInfo');
		        },
		 });
        
		dataGrid = $('#dataGrid')
				.datagrid({
							url : '${ctx}/checkFileUpload/getCheckFileUploadData?tradeDate=' + tradeDate,
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'ID',
							sortName : 'CFU_CHANNEL_CODE,CFU_LiNE_NUMBER',//按照渠道和行数排序 
							sortOrder : 'DESC',
							pageSize : 30,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
									columns :  [[ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'ID',
								field : 'ID',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '渠道编号',
								field : 'cfuChannelCode',
								width : 130,
								halign : 'center',
								align : 'center'
							}, {
								title : '文件名称',
								field : 'cfuFileName',
								width : 300,
								halign : 'center',
								align : 'center'
							}, {
								title : '交易日期',
								field : 'cfuTransDate',
								width : 160,
								halign : 'center',
								align : 'center'
							}, {
								title : '日志行',
								field : 'cfuLineNumber',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '上传时间 ',
								field : 'cfuSuccessTime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '上传标识',
								field : 'cfuUploadFlag',
								width : 160,
								halign : 'center',
								align : 'center',
								formatter : function(value) {
									if (value == "01") {
										return "成功";
									}
									if (value == "00") {
										return "失败";
									}
								}
							} ,{
								title : '操作人',
								field : 'cfuCuser',
								width : 140,
								halign : 'center',
								align : 'center'
							},{
								title : '操作时间',
								field : 'cfuCtime',
								width : 140,
								halign : 'center',
								align : 'center'
								
							}] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								$("#dataGrid").datagrid('clearSelections'); 
							}
						});
  	});
	
	//读取深圳通日志 
	 function readSZTLog() {
		 var tradeDateStr = $('#tradeDate').datebox('getValue');
		 var tradeDate = tradeDateStr.replace(/\-/g, "");
		 $.messager.progress({
		 	title : '提示',
		 	text : '正在处理，请等待....'
		 });
	$.ajax({
         url:"${ctx}/checkFileUpload/readLog",    //请求的url地址
         dataType:"json",   //返回格式为json
         async:true,//请求是否异步，默认为异步，这也是ajax重要特性
         data:{"tradeDate":tradeDate},    //参数值
         type:"POST",   //请求方式
         success :function(result){
         	$.messager.progress('close');
         	$('#TRANSDATE').datebox('setValue', tradeDateStr);
            var parmFilter = $.serializeObject($('#searchForm'));
            dataGrid.datagrid('load', parmFilter);
         	dataGrid.datagrid("reload");
         	if("success" == result.msg){
         		$.messager.show({
                     title : '提示',
                     msg : "处理完成！",
                     timeout : 2000
                 });
         	}else{
         		$.messager.progress('close');
         		$.messager.show({
                     title : '提示',
                     msg : result.msg,
                     timeout : 8000
                 });
         	}
         }
     });
  }
	
	 function myformatter(date){
         var y = date.getFullYear();
         var m = date.getMonth()+1;
         var d = date.getDate();
         return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
     } 
	 
	 //查询
	 function searchFun() {
		 var transDate = $('#TRANSDATE').datebox('getValue');
		 $('#tradeDate').datebox('setValue', transDate);
         var parmFilter = $.serializeObject($('#searchForm'));
         dataGrid.datagrid('load', parmFilter);
     }
	 
	 //重置 
	 function cleanFun() {
         $('#searchForm input').val('');
         dataGrid.datagrid('load', {});
     }
	 
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
        <form id="searchForm">
            <table align="center">
                <tr></tr>
                <tr>
                <th>渠道名称:</th>
                    <td><input id="CHANNELCODE" name="filter_CHANNELCODE" class="easyui-combobox" style = "width:200px;" /></td>
                    <th>查询日期:</th>
                    <td><input id="TRANSDATE" name="filter_TRANSDATE" class="easyui-datebox" style = "width:150px;" /></td>
                </tr>
                <tr></tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">重置</a>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'校验文件上传列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
                                处理日期:<input class="easyui-datebox"  id="tradeDate" name="tradeDate" value = "20190419"  style= "width:120px; "/>
            <a onclick="readSZTLog();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">读取日志</a>
    </div>
</body>
</html>