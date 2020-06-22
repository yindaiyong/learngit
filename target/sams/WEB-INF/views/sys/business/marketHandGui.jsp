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
<title>中登接口基本信息</title>
<style type="text/css">
.gui{
	width :100px;
 	height:40px; 
 	background-color :#009900 ;
 	float:left;
 	margin:5px 5px 5px 5px;
 	text-align:center;
 	font-weight:bold;
 	padding-top:20px;
 	vertical-align:middle;
}
</style>
<script type="text/javascript">
$(function(){
	var data = '${data}';
	$.each(JSON.parse(data),function(index,value){
		var id = value.channelCode;
		var finalStat = value.finalStat;
		var color = "";
		if("00" == finalStat){
			color = "#CB0909";
		}else if ("01" == finalStat){
			color = "#009900";
		}else{
			color = "#D8D8D8";
		}
		$("#"+id+"").css('background-color',color);
	});
});
//按日期查询
function endDayDataReload (){
	//location.reload() ;
	var date = $('#serchDate').datebox('getValue');
	location.href = "${ctx}/dayEnd/GUI?date="+date
}
//查看步骤
function viewErrorStep(channelCode,processStep,processStat,confirmProcessStep,confirmProcessStat){
	if(processStat =='01'){
		processStep = '-1';
	}
	if(confirmProcessStat =='01'){
		confirmProcessStep = '-1';
	}
	parent.$.modalDialog({
        title : "日终流程",
		width:950,
        height :400,
        modal: true,
        resizable:true,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/dayEnd/dayEndStepPage?processStep='+processStep+"&confirmProcessStep="+confirmProcessStep
	});
}
function specialConfirmWrite(){
	var fileType = [];
	$("input[name='file']:checked").each(function(i){
	       var value = $(this).val();
	       fileType.push(value);
	});
	var fileTypes = fileType.join(",");
	if(fileTypes == ""){
		$.messager.alert({
            title : '提示',
            msg : "请至少勾选一个要生成的文件类型",
        });
		return;
	}
	//获得渠道号
	var channelCodes = $('#channel_info').combobox('getValue')
	//获得要生成的日期
	var date = $('#serchDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	$.messager.progress({
		title : '提示',
		text : '正在处理文件，请等待....'
	});
	$.ajax({
        url:"${ctx}/dayEnd/confirmProcessor",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCodes" : channelCodes,"date":date,"fileTypes":fileTypes},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.progress('close');
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
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'条件区域'"
		align="left" style="height: 120px;">
		<table data-options="fit:true,border:false"	style="padding-top: 10px;">

         <tr>
         	<td style="padding-left: 20px;">
				行情日期:<input class="easyui-datebox" id="tradeDate" name="tradeDate" value = "${date }"/>
        		<a id = "end-day-data-processor" onclick="endDayDataReload();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
			</td>
		</tr>
		</table>
	</div>
	<div data-options="region:'center',border:true,title:'行情图形展现区域'">
		<div id= "dayEndGui" style = "border:1px dashed #000;width:98%;height:90%;margin:10px 0px 0px 10px;">
		    <c:forEach items="${list}" var="item">
		    	<div class= "gui" id = "${item.channelCode}"> </div>
		    </c:forEach>
	    </div>
	</div>
</body>
</html>