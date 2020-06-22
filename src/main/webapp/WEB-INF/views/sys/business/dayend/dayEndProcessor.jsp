<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> .dialog-button { padding: 5px; text-align: center; /*background-color: #E0ECFF;*/ } </style>
<head>
<title>日终处理页面</title>
<script type="text/javascript">
var dataGrid = "";
$(function(){
	//给combox赋值
	setComboboxValue("dataProcessState");
	setComboboxValue("confirmProcessState");
	setComboboxValue("sendProcessState");
	setFileTypeCombo("onlyProcessFile");
	var date = $('#handelDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	//加载页面表格
	dataGrid = $("#dayEndGrid").datagrid({
		url : '${ctx}/dayEnd/processorStepList',
		striped : true,
		pagination : true,
		idField : 'channelCode',
		pageSize : 100,
		pageList : [100, 200, 300,
				400, 500 ],
		queryParams:{filter_date:date},
		columns : [[ {
			field :'ck',
			checkbox : 'true',
		},{
			field : 'branchCode',
			hidden:true
		},{
			field : 'confirmBranchCode',
			hidden:true
		},{
			field : 'confirmBranchcode',
			hidden:true
		},{
			field : 'processStep',
			hidden:true
		},{
			field : 'confirmProcessStep',
			hidden:true
		},{
			field : 'confirmProcessStat',
			hidden:true
		},{
			field : 'processStat',
			hidden:true
		},{
			title : '当前日期',
			field : 'currentDate',
			width : 80,
			halign : 'center',
			align : 'center'
		},{
			title : '渠道编号',
			field : 'channelCode',
			width : 100,
			halign : 'center',
			align : 'center'
		},{
			title : '渠道名称',
			field : 'channelName',
			width : 100,
			halign : 'center',
			align : 'center'
		},{
			title : '生成文件步骤',
			field : 'confirmProcessStepName',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				return"<a href='#'>"+(value == null ? "" : value)+"</a>"
			}
		},{
			title : '生成文件状态',
			field : 'confirmProcessStatName',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				if(value == '成功') return '生成文件成功';
				if(value == '失败') return "<span style = 'color:red;font-weight:bold;' title='生成文件失败'>生成文件失败 </span>";
				return value;
			}
		},{
			title : '发送文件状态',
			field : 'sendProcessStatName',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				if(value == '成功') return '发送文件成功';
				if(value == '失败') return "<span style = 'color:red;font-weight:bold;' title='发送文件失败 '>发送文件失败</span>";
				return value;
			}
		},{
			title : '数据处理步骤',
			field : 'processStepName',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				return"<a href='#'>"+(value == null ? "" : value)+"</a>"
			}
		},{
			title : '数据处理状态',
			field : 'processStatName',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				if(value == '成功') return '数据处理成功';
				if(value == '失败') return "<span style = 'color:red;font-weight:bold;' title='数据处理失败 '>数据处理失败 </span>";
				return value;
			}
		},{
			title : '错误信息',
			field : 'errorMessage',
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
		},{
			title : '账户成功数',
			field : 'accountSuccess',
			width : 100,
			halign : 'center',
			align : 'center',
		},{
			title : '账户失败数',
			field : 'accountFailed',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				return"<a href='#'>"+(value == "0" ? value : "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>")+"</a>"
			}
		},{
			title : '交易成功数',
			field : 'transSuccess',
			width : 100,
			halign : 'center',
			align : 'center',
		},{
			title : '交易失败数',
			field : 'transFailed',
			width : 100,
			halign : 'center',
			align : 'center',
			formatter : function(value){
				return"<a href='#'>"+(value == "0" ? value : "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>")+"</a>"
			}
		},{
			title : '处理时间',
			field : 'processStartTime',
			width : 200,
			halign : 'center',
			align : 'center',
		},{
			title : '操作人',
			field : 'userName',
			width : 200,
			halign : 'center',
			align : 'center',
		}
		
		]],
		toolbar : '#toolbar',
		//错误行变红
		rowStyler:function(index,row) {
			/* var processStat = row.processStat;
			var confirmProcessStat = row.confirmProcessStat;
			var sendProcessStat = row.sendProcessStat;
			if("00" == processStat || "00" == confirmProcessStat || "00" == sendProcessStat){
				return 'color:red;font-weight:bold;';
			} */
			 
		},
		//点击单元格
		onClickCell : function(rowIndex, field, value){
			var rows = $("#dayEndGrid").datagrid("getRows");
			var row = rows[rowIndex];//rowIndex为行号
			//数据处理流程
			if("processStepName" == field){
				processorDialog(row,0);
			}
			//确认文件生成流程
			if("confirmProcessStepName" == field){
				processorDialog(row,1);
			}
			//账户错误弹框
			if("accountFailed" == field && "0" != value){
				failedDialog(row,0);
			}
			//交易错误数据
			if("transFailed" == field && "0" != value){
				failedDialog(row,1);
			}
		}
	});
});
//弹框
function failedDialog (data,type){
	//type 0 账户  1  交易
	var channelCode = data.channelCode;
	var transDate = data.currentDate;
	var title = "账户";
	if(type == "1"){title = "交易"}
	parent.$.modalDialog({
        title : channelCode+"-"+transDate+"-"+title+"处理错误信息",
		width:1200,
        height :350,
        modal: true,
        resizable:true,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/dayEnd/transAccoFailedPage?channelCode='+channelCode+"&transDate="+transDate+"&type="+type
	});
	
}

//弹出分红数据列表
function endDaySendProcessor(){
	var channelCodes = getSelectCodes();
	var date = $('#handelDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	parent.$.modalDialog({
        title : "查看分红数据",
		width:1000,
        height :500,
        modal: true,
        resizable:true,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/dayEnd/queryParticipationProfit?channelCodes='+channelCodes+"&date="+date,
	});
}

//弹出处理步骤弹框
function processorDialog (data,type){
	var channelCode = data.channelCode;
	var transDate = data.currentDate;
	var branchCode = data.branchCode;
	var processStep = data.processStep;
	var processStat = data.processStat;
	var width = 840;
	var height = 200;
	var title = "申请数据处理流程";
	if(type == 1){
		branchCode = data.confirmBranchCode;   
		processStep = data.confirmProcessStep; 
		processStat = data.confirmProcessStat; 
		width = 810;
		height = 125;
		title = "确认数据处理流程";
	}
	if(processStat == "01"){
		processStep = -1;
	}
	if(processStep == null )return;
	parent.$.modalDialog.openner_dataGrid = dataGrid;
	parent.$.modalDialog({
        title : title,
		width:width,
        height :height,
        modal: true,
        resizable:true,
        cache: false,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/dayEnd/processStepPage?branchCode='+branchCode+"&errorStep="+processStep+"&channelCode="+channelCode+"&transDate="+transDate
	});
}
//给下拉框赋值
function setComboboxValue(id){
	$("#"+id+"").combobox({
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
	         "id":"00",
	         "name":"处理失败"
	     },{
	         "id":"01",
	         "name":"处理成功"
	     },{
	         "id":"02",
	         "name":"未处理"
	     }]
	});
}
//设置单独处理文件下拉框
function setFileTypeCombo(id){
	$("#"+id+"").combobox({
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
	         "id":"01",
	         "name":"账户申请文件"
	     },{
	         "id":"03",
	         "name":"交易申请文件"
	     },{
	         "id":"43",
	         "name":"电子合同申请文件"
	     },{
	         "id":"R1",
	         "name":"非居民申请文件"
	     }]
	});
}
//查询
function searchInterface(){
	var parmFilter = $.serializeObject($('#searchForm'));
    var date = $('#startDate').datebox('getValue');
    $('#handelDate').datebox('setValue', date);
    dataGrid.datagrid('load', parmFilter);
}
//重置
function cleanInterface(){
	$('#searchForm input').val('');
	dataGrid.datagrid('load', {});
}
//日终确认处理
function endDayConfirmProcessor(){
	//获得勾选渠道
	var channelCodes = getSelectCodes();
	var date = $('#handelDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	var hasHandel = checkChannelHasHandel(channelCodes,date,"333333");
	var handelState = hasHandel.obj;
	var handelMsg = hasHandel.msg;
    var cCodes = handelMsg.split(",");
    var alertMsg = getAlertMsg(cCodes,date);
    if(handelState){
		$.messager.confirm("询问", alertMsg, function (b) {
			if(b){
				endDayConfirmHandel(channelCodes,date);
			}else{
				return ;
			}		 
		});
	}else{
		endDayConfirmHandel(channelCodes,date);
	}
}

//日终确认文件生成
function endDayConfirmHandel(channelCodes,date){
	$.messager.progress({
		title : '提示',
		text : '处理中,请等待....如有问题,请联系管理员。'
	});
	$.ajax({
        url:"${ctx}/dayEnd/confirmProcessor",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCodes" : channelCodes,"date":date},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.progress('close');
        	dataGrid.datagrid("load",{filter_date:date});
        	if("success" == result.msg){
        		alertMsg(result);
        	}else{
        		$.messager.show({
                    title : '提示',
                    msg : "处理失败",
                    timeout : 2000
                });
        	}
        },
    });
}



//日终数据处理
function endDayDataProcessor(){
	//获得勾选渠道
	var channelCodes = getSelectCodes();
	var date = $('#handelDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	/* 20200428 点错重做了，加上确认框 */
	var hasHandel = checkChannelHasHandel(channelCodes,date,"111111");
	var handelState = hasHandel.obj;
	var handelMsg = hasHandel.msg;
    var cCodes = handelMsg.split(",");
    var alertMsg = getAlertMsg(cCodes,date);

    if(handelState){
		$.messager.confirm("询问", alertMsg, function (b) {
			if(b){
				endDayDataHandel(channelCodes,date);
			}else{
				return ;
			}		 
		});
	}else{
		endDayDataHandel(channelCodes,date);
	}
}

//重复处理弹框信息

function getAlertMsg(cCodes,date){
	var alertMsg = '';
    if(cCodes.length>0){
        var msg ='';
        for(var i =0;i<cCodes.length;i++){
            if(i%2 == 0){
                msg+=cCodes[i]+',';
            }else{
                msg+=cCodes[i]+',<br/>';
            }
        }
        if(cCodes.length%2 == 0){
            msg += "<br/>"+date+"日,<br/><span style = 'color:red;'>已经处理过,是否再次处理？</span>"
        }else{
            msg += "<br/><br/>"+date+"日,<br/><span style = 'color:red;'>已经处理过,是否再次处理？</span>"
        }
        alertMsg = '<div style="margin-left:50px">'+msg+'</div>';
    }
    return alertMsg;
}

//实际日终处理数据
function endDayDataHandel (channelCodes,date){
	$.messager.progress({
		title : '提示',
		text : '处理中,请等待....如有问题,请联系管理员。'
	});
	$.ajax({
        url:"${ctx}/dayEnd/dataProcessor",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCodes":channelCodes,"date":date,fileType:""},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.progress('close');
        	dataGrid.datagrid("load",{filter_date:date});
        	if("success" == result.msg){
        		alertMsg(result);
        	}else{
        		$.messager.show({
                    title : '提示',
                    msg : "处理失败",
                    timeout : 2000
                });
        	}
        },
    });
}
//判断勾选的渠道，选择的日期，有没有处理过
function checkChannelHasHandel (channelCodes,date,flowCode){
	var ret;
	$.ajax({
        url:"${ctx}/dayEnd/channelHasHandel",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCodes":channelCodes,"date":date,"flowCode":flowCode},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	ret = result;
        }
	});
	return ret;
}
//处理信息提示
function alertMsg(result){
	var data = result.data;
	var msg = "";
	data.forEach(function(item,index){
		if(item.msg != "success"){
			msg+=item.msg+"<br/>";
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
//获得勾选的渠道编码
function getSelectCodes (){
	var channelCode = [];
	var rows =  $('#dayEndGrid').datagrid('getSelections');
	$.each(rows,function(index,value){
		channelCode.push(value.channelCode);
	});
	return channelCode.join(",");
}
//单独处理文件 
function onlyProcessFile(){
	var rows =  $('#dayEndGrid').datagrid('getSelections');
	var fileType = $('#onlyProcessFile').combobox('getValue');
	if(rows.length != 1){
		$.messager.alert({
            title : '警告',
            msg : "<span style = 'color:red;font-size:16px;'>此操作需勾选渠道,且只能勾选一个渠道!</span>",
            timeout : 2000
        });
		return ;
	}
	if(fileType ==""){
		$.messager.alert({
            title : '警告',
            msg : "<span style = 'color:red;font-size:16px;'>请选择要单独操作的文件类型!</span>",
            timeout : 2000
        });
		return ;
	}
	var channelCodes = rows[0].channelCode;
	var date = $('#handelDate').datebox('getValue').replace(/\-/g, "");
	$.messager.progress({
		title : '提示',
		text : '处理中,请等待....如有问题,请联系管理员。'
	});
	$.ajax({
        url:"${ctx}/dayEnd/dataProcessor",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCodes":channelCodes,"date":date,fileType:fileType},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.progress('close');
        	dataGrid.datagrid("load",{filter_date:date});
        	if("success" == result.msg){
        		alertMsg(result);
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

//生成并且发送94文件
function writeAndSend94File(){
	var date = $('#handelDate').datebox('getValue').replace(/\-/g, "");
	$.messager.progress({
		title : '提示',
		text : '处理中,请等待....如有问题,请联系管理员。'
	});
	$.ajax({
        url:"${ctx}/dayEnd/writeAndSend94File",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"date":date},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.progress('close');
        	if(result.success){
        		$.messager.alert({
                    title : '提示',
                    msg : result.obj,
                    timeout : 2000
                });
        	}else{
        		$.messager.alert({
                    title : '提示',
                    msg : "处理失败",
                    timeout : 2000
                });
        	}
        },
    });
}

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 140px;">
        <form id="searchForm">
            <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
                <tr>
                    <td align="center">
                    	渠道编号:<input class="easyui-combobox"  style = "width:200px;"  id="channelCode"   name ="filter_channelCode" value = "" data-options="
						url:'${ctx}/combobox/queryChannelInfo',
						valueField:'ID',
						textField:'NAME'" >
                    	数据处理状态:<input id="dataProcessState" name="filter_dataProcessState" class="easyui-combobox"/>
                    	生成文件状态:<input id="confirmProcessState" name="filter_confirmProcessState" class="easyui-combobox"/>
                    	发送文件状态:<input id="sendProcessState" name="filter_sendProcessState" class="easyui-combobox"/>
						日期:<input class="easyui-datebox" id="startDate" name="filter_date" value = "20190419"/>
					</td>
				<tr>
                    <td align="center">
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchInterface();">查询</a>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanInterface();">重置</a>
                    	<!-- <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="test();">测试</a> -->
                    </td>
                </tr>
                <tr>
                <td>
                	单独处理文件类型:<input id="onlyProcessFile" name="filter_onlyProcessFile" class="easyui-combobox"/>
                <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="onlyProcessFile();">单独处理文件开始（<span style = "color:red">请慎重操作</span>）</a>
                </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'日终处理列表'" >
        <table id="dayEndGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        	处理日期:<input style= "width:120px;" class="easyui-datebox" id="handelDate" name="handel_date" value = "19000101"/>
            <a id = "end-day-confirm-processor" style = "margin-left:140px;" onclick="endDayConfirmProcessor();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">生成(T-1)日申请数据确认</a>
            <a id = "end-day-send-processor" onclick="endDaySendProcessor();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">发送(T-1)日申请数据确认</a>
            <a id = "end-day-data-processor" style = "margin-left:10px;" onclick="endDayDataProcessor();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">T日数据处理</a>
            <!-- <a id = "end-day-data-processor" style = "margin-left:30px;" onclick="writeAndSend94File();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">发送T日94文件</a> -->
    </div>
</body>
</html>