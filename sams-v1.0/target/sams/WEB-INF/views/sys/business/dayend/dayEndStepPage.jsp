<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
$(function(){
    //初始化加载  清除所有数据
    $(".myself-easyuibtn").html('');
    $(".children").remove();
	var list = '${list}';
	var processStep = '${processStep}';
	var confirmProcessStep = '${confirmProcessStep}';
	var sendProcessStep = '${sendProcessStep}';

	$.each(JSON.parse(list),function(index,value){
		var psiProcessStep = value.psiProcessStep;
		var psiFlowCode = value.psiFlowCode;
		if(psiFlowCode == "111111"){
		    if(processStep != ""){
                if(processStep == '-1'){
                    $("#"+psiProcessStep+psiFlowCode+"").html('<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900; letter-spacing:2px;font-family:Serif;margin: 12px 1px;">\n' +
                        '\t\t\t\t\t\t\t'+value.psiProcessName+'\n' +
                        '\t\t\t\t\t</div>');
                    $("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
                    $("#"+psiProcessStep+psiFlowCode+"").css('width','120px');
                    $("#"+psiProcessStep+psiFlowCode+"").css('height','60px');
                    $("#"+psiProcessStep+psiFlowCode+"").css('color','#000000');
                    $("#"+psiProcessStep+psiFlowCode+"").append('<div id="children-'+psiProcessStep+psiFlowCode+'" data-options="minWidth:100,width:100" class = "children" style="width: 100px;position:absolute;top:100%;left:0;z-index :9999;display:none;" >\n' +
                        '                        <div id="returnStep'+psiProcessStep+psiFlowCode+'" data-options="iconCls:\'icon-undo\',minWidth:100,width:100" style="/*width: 100px;/*height: 30px;font-size :20px;font-weight:bold;*/" onclick = "returnStep(\''+psiProcessStep+'\');" title="回到该步" >回到该步</div>\n' +
                        '\t\t\t\t\t</div>');
                }else{
                    if(processStep > psiProcessStep){
                        $("#"+psiProcessStep+psiFlowCode+"").html('<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900; letter-spacing:2px;font-family:Serif;margin: 12px 1px;">\n' +
                            '\t\t\t\t\t\t\t'+value.psiProcessName+'\n' +
                            '\t\t\t\t\t</div>');
                        $("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
                        $("#"+psiProcessStep+psiFlowCode+"").css('width','120px');
                        $("#"+psiProcessStep+psiFlowCode+"").css('height','60px');
                        $("#"+psiProcessStep+psiFlowCode+"").css('color','#000000');
                        $("#"+psiProcessStep+psiFlowCode+"").append('<div id="children-'+psiProcessStep+psiFlowCode+'" data-options="minWidth:100,width:100" class = "children" style="width: 100px;position:absolute;top:100%;left:0;z-index :9999;display:none;" >\n' +
                            '                        <div id="returnStep'+psiProcessStep+psiFlowCode+'" data-options="iconCls:\'icon-undo\',minWidth:100,width:100" style="/*width: 100px;/*height: 30px;font-size :20px;font-weight:bold;*/" onclick = "returnStep(\''+psiProcessStep+'\');" title="回到该步" >回到该步</div>\n' +
                            '\t\t\t\t\t</div>');
                    }else if (processStep == psiProcessStep){
                        $("#"+psiProcessStep+psiFlowCode+"").html('<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900; letter-spacing:2px;font-family:Serif;margin: 12px 1px;">\n' +
                            '\t\t\t\t\t\t\t'+value.psiProcessName+'\n' +
                            '\t\t\t\t\t</div>');
                        $("#"+psiProcessStep+psiFlowCode+"").css('background-color','#CB0909');
                        $("#"+psiProcessStep+psiFlowCode+"").css('width','120px');
                        $("#"+psiProcessStep+psiFlowCode+"").css('height','60px');
                        $("#"+psiProcessStep+psiFlowCode+"").css('color','#000000');
                    }else{
                        $("#"+psiProcessStep+psiFlowCode+"").html('<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900; letter-spacing:2px;font-family:Serif;margin: 12px 1px;">\n' +
                            '\t\t\t\t\t\t\t'+value.psiProcessName+'\n' +
                            '\t\t\t\t\t</div>');
                        $("#"+psiProcessStep+psiFlowCode+"").css('width','120px');
                        $("#"+psiProcessStep+psiFlowCode+"").css('height','60px');
                        $("#"+psiProcessStep+psiFlowCode+"").css('color','#000000');
                    }
                }
			}else{
                $("#"+psiProcessStep+psiFlowCode+"").html('<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900; letter-spacing:2px;font-family:Serif;margin: 12px 1px;">\n' +
                    '\t\t\t\t\t\t\t'+value.psiProcessName+'\n' +
                    '\t\t\t\t\t</div>');
                $("#"+psiProcessStep+psiFlowCode+"").css('width','120px');
                $("#"+psiProcessStep+psiFlowCode+"").css('height','60px');
                $("#"+psiProcessStep+psiFlowCode+"").css('color','#000000');
			}

		}
		if(psiFlowCode == "222222" && confirmProcessStep != ""){
			if(confirmProcessStep == '-1'){
				$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
			}else{
				if(confirmProcessStep > psiProcessStep){
					$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
				}else if (confirmProcessStep == psiProcessStep){
					$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#CB0909');
				}
			}
		}
		if(psiFlowCode == "333333"){
			if(sendProcessStep == '-1'){
				$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
			}else{
				if(sendProcessStep > psiProcessStep){
					$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
				}else if(sendProcessStep == psiProcessStep){
					$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#CB0909');
				}
			}
		}
	});
});
//返回上步
var channelCode = '${channelCode}';
var date = '${date}';
function returnStep(stepId){
    // alert(stepId+"该功能还未实现，敬请期待！！！");
    parent.$.messager.confirm('警告', '是否回到该步骤？<span style = "color:red;">回到该步将清除之前操作！</span>', function(b) {
        if (b) {
            $.messager.progress({
                title : '提示',
                text : '正在处理中，请等待....'
            });
            $.modalDialog.handler.dialog('close');
            $.ajax({
                url:"${ctx}/dayEnd/returnStep",    //请求的url地址
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:{"branchCode" : '111111',"channelCode":channelCode,"transDate":date,"retStep":stepId},    //参数值
                type:"POST",   //请求方式
                success :function(result){
                    $.messager.progress('close');
                    // parent.$.modalDialog.refData;
                    parent.$.modalDialog.refData.click();
                    $.messager.show({
                        title : '提示',
                        msg : result.msg == "" ? "处理完成" : result.msg,
                        timeout : 4000
                    });
                }
            });
        }else {
            parent.$.messager.show({
                title : '提示',
                msg : '操作取消！'
            });
        }
    });
}
</script>
<style type = "text/css">
	.myself-easyuibtn {
		height: 70px !important;
		width:  70px !important;
	}
	.myself-easyuibtn1 {
		height: 70px !important;
		width:  70px !important;
	}
</style>
	<div style="width:90%;margin-left: 50px;margin-top: 30px" >
		<c:forEach items="${entity}" var = "item" varStatus="status" >
			<c:choose>
				<c:when test="${item.psiFlowCode ne '111111' }">
					<div class = "easyui-linkbutton myself-easyuibtn1" style="border-radius:50%; background-color:#D8D8D8; margin-top: 15px;" onselectstart="return false" data-options="plain:true" id ="${item.psiProcessStep}${item.psiFlowCode}">
						<div style = "width: 45px;height:45px;font-size :18px;font-weight: 900;letter-spacing:2px;font-family:Serif; margin: 12px 1px;">
								${item.psiProcessName }
						</div>
					</div>
				</c:when>
				<c:when test="${processStep eq '' || empty processStep }">
					<div class = "easyui-linkbutton myself-easyuibtn1" style="border-radius:50%; background-color:#D8D8D8; margin-top: 15px;" onselectstart="return false" data-options="plain:true" id ="${item.psiProcessStep}${item.psiFlowCode}">
						<div style = "width: 45px;height:45px;font-size :18px;font-weight: 900;letter-spacing:2px;font-family:Serif; margin: 12px 1px;">
								${item.psiProcessName }
						</div>
					</div>
				</c:when>
				<c:when test="${item.psiProcessStep eq processStep }">
					<div class = "easyui-linkbutton myself-easyuibtn" style="border-radius:50%; background-color:#D8D8D8; margin-top: 15px;" onselectstart="return false" data-options="plain:true" id ="${item.psiProcessStep}${item.psiFlowCode}">
					</div>
				</c:when>
				<c:when test="${item.psiProcessStep lt processStep || '-1' eq processStep}">
					<div class = "easyui-menubutton myself-easyuibtn" style="border-radius:50%; background-color:#D8D8D8; margin-top: 15px;" onselectstart="return false" data-options="menu:'#children-${item.psiProcessStep}${item.psiFlowCode}',hasDownArrow:false,showEvent:'dblclick',minWidth:100 " id ="${item.psiProcessStep}${item.psiFlowCode}">
					</div>
				</c:when>
				<c:otherwise>
					<div class = "easyui-linkbutton myself-easyuibtn" style="border-radius:50%; background-color:#D8D8D8; margin-top: 15px;" onselectstart="return false" data-options="plain:true" id ="${item.psiProcessStep}${item.psiFlowCode}">
					</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${!status.last}">
			<c:choose>
				<c:when test="${ item.psiProcessStep eq '-1' }">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center" >
				</c:when>
				<%--全部成功--%>
				<c:when test="${confirmProcessStep eq '-1' && item.psiFlowCode eq '222222'}">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${processStep eq '-1' && item.psiFlowCode eq '111111'}">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${sendProcessStep eq '-1' && item.psiFlowCode eq '333333'}">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<%--部分成功--%>
				<c:when test="${item.psiProcessStep eq confirmProcessStep && item.psiFlowCode eq '222222' }">
					<img src="${ctx}/static/images/red.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${item.psiProcessStep eq processStep && item.psiFlowCode eq '111111'}">
					<img src="${ctx}/static/images/red.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${item.psiProcessStep eq sendProcessStep && item.psiFlowCode eq '333333'}">
					<img src="${ctx}/static/images/red.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${item.psiProcessStep < confirmProcessStep && item.psiFlowCode eq '222222'}">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${item.psiProcessStep < processStep && item.psiFlowCode eq '111111'}">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:when test="${item.psiProcessStep < sendProcessStep && item.psiFlowCode eq '333333'}">
					<img src="${ctx}/static/images/green.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:when>
				<c:otherwise>
					<img src="${ctx}/static/images/gray.jpg" style="margin-top: 15px;" width="25px;" height="25px;" align="center">
				</c:otherwise>
			</c:choose>
			</c:if>
		</c:forEach>

		<%--<c:forEach items="${entity}" var = "item">--%>

			<%--<div style="width:65px;height:45px;border-radius:50%;background-color:#D8D8D8;text-align:center;padding-top:20px;float:left;margin-top: 20px;" id ="${item.psiProcessStep}${item.psiFlowCode}">${item.psiProcessName }</div>
			<div style = "float:left;height:5px;margin-top:45px;"><HR width=20 SIZE=3 NOSHADE></div>--%>
		<%--</c:forEach>--%>
	</div>
