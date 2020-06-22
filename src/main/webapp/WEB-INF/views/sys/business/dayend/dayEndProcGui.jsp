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
function viewErrorStep(processStep,processStat,confirmProcessStep,confirmProcessStat,sendProcessStep,sendProcessStat,channelCode){
    var date = $('#serchDate').datebox('getValue');
    if(processStat =='01'){
		processStep = '-1';
	}
	if(confirmProcessStat =='01'){
		confirmProcessStep = '-1';
	}
	if(sendProcessStat =='01'){
		sendProcessStep = '-1';
	}
    parent.$.modalDialog.refData = $("#end-day-data-processor");
	parent.$.modalDialog({
        title : "日终流程",
		width:960,
        height :400,
        modal: true,
        resizable:true,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/dayEnd/dayEndStepPage?processStep='+processStep+"&confirmProcessStep="+confirmProcessStep+"&sendProcessStep="+sendProcessStep+"&date="+date+"&channelCode="+channelCode
	});

}
//重新生成
function specialConfirmFile(){
	var fileType = [];
	$("input[name='file']:checked").each(function(i){
	       var value = $(this).val();
	       fileType.push(value);
	});
	var fileTypes = fileType.join(",");
	if(fileTypes == ""){
		$.messager.alert({
            title : '提示',
            msg : "请勾选一个要生成的文件类型",
        });
		return;
	}
	return fileTypes;
}

//单独生成文件
function specialConfirmWrite(){
	var fileTypes = specialConfirmFile();
	if(fileTypes == undefined)return ;
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
//发送文件
function specialConfirmSend (){
	var fileTypes = specialConfirmFile();
	if(fileTypes == undefined) return ;
	//获得渠道号
	var channelCodes = $('#channel_info').combobox('getValue')
	//获得要生成的日期
	var date = $('#serchDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	$.messager.progress({
		title : '提示',
		text : '正在上传文件，请等待....'
	});
	$.ajax({
        url:"${ctx}/dayEnd/sendFile",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCodes" : channelCodes,"transDate":date,"fileTypes":fileTypes},    //参数值
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

//数据回退
function dataRoolback (){
	//获得渠道号
	var channelCode = $('#channel_info').combobox('getValue')
	//获得要生成的日期
	var date = $('#serchDate').datebox('getValue');
	date = date.replace(/\-/g, "");
	
	parent.$.messager.confirm('询问', '您确认回退数据吗？', function(b) {
		if (b) {
			$.post("${ctx}/dayEnd/dataRoolback",  {
				channelCode : channelCode,
				transDate : date
			}, function(result) {
				if (result.success) {
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
			}, 'JSON');
		} 
	});
}

//迁移备份数据
function backUpData (){
	var channelCode = $('#channel_info').combobox('getValue');
	var monthNum = $('#channel-monthNum').combobox('getValue')-1;
	$.ajax({
        url:"${ctx}/additional/backUpData",    //请求的url地址
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"channelCode" : channelCode,"monthNum":monthNum},    //参数值
        type:"POST",   //请求方式
        success :function(result){
        	$.messager.alert({
                title : '提示',
                msg : "迁移完成",
                timeout : 2000
            });
        }
	});
}

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'条件区域'"
		align="left" style="height: 120px;">
		<table data-options="fit:true,border:false"	style="padding-top: 10px;">
		<tr>
			<td align="center" style="padding-left: 20px;">
	         需要生成的文件类型:<input type = "radio" id="file-02" name="file" value="02" checked>02文件
	         <input type = "radio" id="file-04" name="file" value="04">04文件
	         <input type = "radio" id="file-05" name="file" value="05">05文件
	         <input type = "radio" id="file-06" name="file" value="06">06文件
	         <input type = "radio" id="file-44" name="file" value="44">44文件
	         <input type = "radio" id="file-R2" name="file" value="R2">R2文件
	         <input type = "radio" id="file-94" name="file" value="94">94文件
	         <input type = "radio" id="file-26" name="file" value="26">26文件
	         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         生成文件渠道:<input class="easyui-combobox"  style = "width:250px;"  id="channel_info"   name ="channel_info" value = "TTTNETB3" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME',required:true" >
	         <a id = "special_Confirm_Write" onclick="specialConfirmWrite();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">生成确认文件</a>
	         <a id = "special_Confirm_send" onclick="specialConfirmSend();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">发送确认文件</a>
         	 <a id = "special_roolback" onclick="dataRoolback();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-redo">T日数据回退</a>
         	<br/>
         	</td>
         </tr>
         <tr>
         	<td style="padding-left: 20px;">
				操作日期:<input class="easyui-datebox" id="serchDate" name="serchDate" value = "${date }"/>
        		<a id = "end-day-data-processor" onclick="endDayDataReload();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
        		<c:if test="${userName eq 'admin'}">
   	   			保留<select id ="channel-monthNum" class="easyui-combobox" style ="width:50px;" >
				   	 <option value="2">2</option>
				   	 <option value="3">3</option>
				   	 <option value="4">4</option>
				   	 <option value="5">5</option>
				   	 <option value="6">6</option>
				   	 <option value="7">7</option>
				   	 <option value="8">8</option>
				    </select>个月数据
				    <a class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search" onclick="backUpData()">迁移数据</a>
				 </c:if>   
			</td>
		</tr>
		</table>
	</div>
	<div data-options="region:'center',border:true,title:'日终图形展现区域'">
		<div id= "dayEndGui" style = "border:1px dashed #000;width:98%;height:90%;margin:10px 0px 0px 10px;">
		    <c:forEach items="${list}" var="item">
		    	<div class= "gui" style="cursor:pointer;" onselectstart="return false" id = "${item.channelCode}" onclick = "viewErrorStep('${item.processStep}','${item.processStat}','${item.confirmProcessStep}','${item.confirmProcessStat}','${item.sendProcessStep}','${item.sendProcessStat}','${item.channelCode}')">${item.channelName}</div>
		    </c:forEach>
	    </div>
	</div>
</body>
</html>