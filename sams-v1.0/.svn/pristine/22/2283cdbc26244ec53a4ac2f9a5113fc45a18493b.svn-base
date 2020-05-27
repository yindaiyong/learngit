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
<title>行情处理明细</title>
</head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		 //设置时间（当前日期的下一天）
		 var currTime = new Date();   
	     $("#tradeDate").datebox("setValue",myformatter(currTime));
	     var tradeDate = $('#tradeDate').datebox('getValue');
	     $("#psBusinessDate").datebox("setValue",myformatter(currTime));
	     var psBusinessDate = $('#psBusinessDate').datebox('getValue');
		//初始化
		 $('#psProcessStart').combobox({
		     panelHeight: 'auto',//自适应
		     valueField: 'id',//绑定字段ID
		     textField: 'name',//绑定字段Name
		     onLoadSuccess:function(){
		         $(".combo").click(function(){
		                  $(this).prev().combobox("showPanel");
		        });
		    },
		     data:[{
		         "id":"",
		         "name":"请选择"
		     },{
		         "id":"00",
		         "name":"处理失败"
		     },{
		         "id":"01",
		         "name":"处理成功"
		     }]/* ,
		     formatter: function (row) {
		         var opts = $(this).combobox('options');
		         return '<input type="checkbox" class="combobox-checkbox" style="margin:0 5px;vertical-align: -2px" id="' + row[opts.valueField] + '">' + row[opts.textField]
		     },
		   //获取数据URL 
		     //选择树节点触发事件 
		     onSelect: function (row) {
		         var opts = $(this).combobox('options');
		         var el = opts.finder.getEl(this, row[opts.valueField]);
		         el.find('input.combobox-checkbox')._propAttr('checked', true);
		     },
		     onUnselect: function (row) {
		         var opts = $(this).combobox('options');
		         var el = opts.finder.getEl(this, row[opts.valueField]);
		         el.find('input.combobox-checkbox')._propAttr('checked', false);
		     } */
		 }); 
		dataGrid = $('#dataGrid').datagrid(
						{
							url : '${ctx}/marketHanding/getMarketHandingData?tradeDate=' + tradeDate,
							fit : true,
							fitColumns : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'psChannelCode',
							sortName : '',
							pageSize : 30,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,400, 500 ],
							frozenColumns : [ [{
								field :'ck',
								checkbox : 'true',
							}, {
								title : '渠道编号',
								field : 'psChannelCode',
								width : 150,
								halign : 'center',
								align : 'center'/*,
								 hidden : 'true' */
							},{
								title : '当前处理日期',
								field : 'psBusinessDate',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '渠道名称',
								field : 'ciChannelName',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
								formatter : function(value){
									return"<a href='#'>"+(value == null ? "" : value)+"</a>"
								}
							},{
								title : '处理步骤',
								field : 'psProcessStep',
								width : 140,
								halign : 'center',
								align : 'center',
								formatter : function(value) {
									if (value == "01") {
										return "<a href='#'>数据处理</a>";
									}
									if (value == "02") {
										return "<a href='#'>文件生成</a>";
									}
									if (value == "03") {
										return "<a href='#'>文件发送</a>";
									}
									if (value == "04") {
										return "<a href='#'>文件发送</a>";
									}
								}
							}, {
								title : '处理状态',
								field : 'psProcessStart',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
								formatter : function(value) {
									if (value == "01") {
										return "处理成功";
									}
									if (value == "00") {
										return "处理失败";
									}
								}
							} ]], 
							columns : [[ {
								title : '错误信息',
								field : 'reErrorMessage',
								width : 200,
								halign : 'center',
								align : 'center',
                                formatter : function(value){
                                    if(value == null || value === null){
                                        return "<span ></span>";
                                    }else{
                                        return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                                    }
                                }
							}] ],
							toolbar : '#toolbar',
							//加载成功
							onLoadSuccess: function (data) {
								//去掉标题上的复选框
							  /*  $(".datagrid-header-check").html(""); */
							},
							rowStyler:function(index,row) {
								var psProcessStart = row.psProcessStart;
								if("00" == psProcessStart){
									return 'color:red;font-weight:bold;';
								}
							},
							//点击单元格
							onClickCell : function(rowIndex, field, value){
								var rows = $("#dataGrid").datagrid("getRows");
								var row = rows[rowIndex];//rowIndex为行号
								if("psProcessStep" == field){
									processorDialog(row,0);
								}
								
								if("ciChannelName" == field){
									channelDetail(row,0);
								}
							}
						});
  	});
	
	//行情处理流程图 
	function processorDialog (data,type){
		var channelCode = data.psChannelCode;
		var transDate = data.psBusinessDate;
		var processStep = data.psProcessStep;
		var processStart = data.psProcessStart;
		var title = "行情处理流程";
		var width = 380;
		if(processStep == null )return;
		parent.$.modalDialog.openner_dataGrid = dataGrid;
		parent.$.modalDialog({
	        title : title,
			width : width,
	        height :105,
	        modal: true,
	        resizable:true,
	        style : 'background-color : #E0ECFF;',
	        href : '${ctx}/marketHanding/marketStepPage?channelCode='+channelCode+"&transDate="+transDate+"&processStep="+processStep+"&processStart="+processStart,
		});
	}
	
	//渠道行情明细
	function channelDetail (data,type){
		var channelCode = data.psChannelCode;
		var transDate = data.psBusinessDate;
		var width = 1350;
		var title = "渠道行情明细";
		parent.$.modalDialog.openner_dataGrid = dataGrid;
		parent.$.modalDialog({
	        title : title,
			width:width,
	        height :400,
	        modal: true,
	        resizable:true,
	        style : 'background-color : #E0ECFF;',
	        href : '${ctx}/marketHanding/channelDetail?channelCode='+channelCode+"&transDate="+transDate
		});
	}
	
	 function myformatter(date){
         var y = date.getFullYear();
         var m = date.getMonth()+1;
         var d = date.getDate()+1;
         return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
     }
	//行情处理
	 function marketHanding(){
	 	//获得勾选渠道
		 var tradeDateStr = $('#tradeDate').datebox('getValue');
		 var tradeDate = tradeDateStr.replace(/\-/g, "");
	 	$.messager.progress({
	 		title : '提示',
	 		text : '正在处理文件，请等待....'
	 	});
	 	$.ajax({
	         url:"${ctx}/marketHanding/marketProcessing",    //请求的url地址
	         dataType:"json",   //返回格式为json
	         async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	         data:{"tradeDate":tradeDate},    //参数值
	         type:"POST",   //请求方式
	         success :function(result){
	         	$.messager.progress('close');
	         	$('#psBusinessDate').datebox('setValue', tradeDateStr);
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
	                     msg : "处理失败",
	                     timeout : 2000
	                 });
	         	}
	         }
	     });
	 }
	 
	//重新生成
	 function reCteate(){
	 	//获得勾选渠道
	 	 var psChannelCodes = getSelectCodes();
	 	 if (psChannelCodes.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请勾选所需处理的渠道',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return false;
			}
		 var tradeDateStr = $('#tradeDate').datebox('getValue');
		 var tradeDate = tradeDateStr.replace(/\-/g, "");
	 	$.messager.progress({
	 		title : '提示',
	 		text : '正在处理文件，请等待....'
	 	});
	 	$.ajax({
	         url:"${ctx}/marketHanding/reCreate",    //请求的url地址
	         dataType:"json",   //返回格式为json
	         async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	         data:{"psChannelCodes":psChannelCodes,"tradeDate":tradeDate,"flag":"true"},    //参数值
	         type:"POST",   //请求方式
	         success :function(result){
        		$.messager.progress('close');
	         	$('#psBusinessDate').datebox('setValue', tradeDateStr);
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
	                     msg : "处理失败",
	                     timeout : 2000
	                 });
	         	}
	         },
	     });
	 }
	
		//重新发送 
	 function reSend(){
	 	//获得勾选渠道
	 /* 	var psChannelCodes = getSelectCodes(); */
	 	var psChannelCodes = [];
		var rows = dataGrid.datagrid('getSelections');
		var sendFlag = true;
		if (rows.length == 0) {
			$.messager.show({
				title : '提示',
				msg : '请勾选所需处理的渠道',
				timeout : 3000,
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			});
			return false;
		}
		$.each(rows,function(index,value){
			if(value.psProcessStart !='01' && value.psProcessStep<'03'){
				$.messager.show({
					title : '提示',
					msg : value.psChannelCode+'未处理成功不允许发送文件,请重新选择 ',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				sendFlag = false;
				return false;
			}
			psChannelCodes.push(value.psChannelCode);
		});
		 var psChannelCodes = psChannelCodes.join(",");
		 var tradeDateStr = $('#tradeDate').datebox('getValue');
		 var tradeDate = tradeDateStr.replace(/\-/g, "");
		 
		 if(sendFlag){
			 $.messager.progress({
			 		title : '提示',
			 		text : '正在处理文件，请等待....'
			 	});
			 	$.ajax({
			         url:"${ctx}/marketHanding/reSend",    //请求的url地址
			         dataType:"json",   //返回格式为json
			         async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			         data:{"psChannelCodes":psChannelCodes,"tradeDate":tradeDate},    //参数值
			         type:"POST",   //请求方式
			         success :function(result){
		        	    $.messager.progress('close');
			         	$('#psBusinessDate').datebox('setValue', tradeDateStr);
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
			                     msg : "处理失败",
			                     timeout : 2000
			                 });
			         	}
			         },
			     });
		 }else{
			 return false;
		 }
	 }
	 
	 
	 function searchFun() {
		 var psBusinessDate = $('#psBusinessDate').datebox('getValue');
		 $('#tradeDate').datebox('setValue', psBusinessDate);
         var parmFilter = $.serializeObject($('#searchForm'));
         dataGrid.datagrid('load', parmFilter);
     }
	 
	 function cleanFun() {
         $('#searchForm input').val('');
         dataGrid.datagrid('load', {});
     }
	 
	 function getSelectCodes (){
		var psChannelCodes = [];
		var rows = dataGrid.datagrid('getSelections');
		$.each(rows,function(index,value){
			psChannelCodes.push(value.psChannelCode);
		});
		return psChannelCodes.join(",");
	}	 
	 
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询区域'"	align="center" style="height: 120px;">
		<form id="searchForm">
			<table  data-options="fit:true,border:false" style="padding-top: 10px;">
				<tr>
					<td align="center">
						日期:<input class="easyui-datebox" id="psBusinessDate" name="filter_psBusinessDate" />
						处理状态:<input id="psProcessStart" name="filter_psProcessStart"	class="easyui-combobox" />
						代销渠道:<input class="easyui-combobox"  style = "width:250px;"  id="channelCode"   name ="filter_channelCode"  data-options="
								url:'${ctx}/combobox/queryChannelInfo',
								valueField:'ID',
								textField:'NAME',required:false">
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-search',plain:true"	onclick="searchFun();">查询</a> 
				    	<a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-cancel',plain:true"	onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'行情处理明细列表'">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
                                处理日期:<input class="easyui-datebox"  id="tradeDate" name="tradeDate" value = "20190419"  style= "width:120px; "/>
           <!--  <a onclick="marketHanding();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:' icon-metro-32-spinner4'">行情处理</a> -->
			<a onclick="reCteate();" href="javascript:void(0);" 	class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-metro-32-wrench'">行情生成</a>
			<a onclick="reSend();" href="javascript:void(0);" class="easyui-linkbutton"	data-options="plain:true,iconCls:'icon-metro-32-undo2'">行情发送</a>
	</div>
</body>
</html>