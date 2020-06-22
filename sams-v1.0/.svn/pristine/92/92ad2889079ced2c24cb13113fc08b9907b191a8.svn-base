<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
$(function(){
	var list = '${list}';
	var errorStep = '${errorStep}'; //当前处理步骤
	var processStart = '${processStart}';//处理状态
	$.each(JSON.parse(list),function(index,value){
		var psiProcessStep = value.psiProcessStep;
		var psiFlowCode = value.psiFlowCode;
		
		$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
		//处理步骤为处理结束并且处理成功，直接为绿色并跳出本次循环
		if('04' == errorStep && "01" == processStart ){
			$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
			$("#dayEnd").css('background-color','#009900');
			 return true;//相当于java的continue
		}
		
		//处理步骤等于当前处理步骤并且处理状态为失败则红色，并跳出循环不再向下处理
		if("00" == processStart && psiProcessStep == errorStep ){
			$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#FF0000');	
	        return false;
		}else if("01" == processStart && psiProcessStep == errorStep ){
			//处理步骤等于当前处理步骤并且处理状态为成功则绿色，并跳出循环不再向下处理
			$("#"+psiProcessStep+psiFlowCode+"").css('background-color','#009900');
			return false;//相当于java的break
		}
	});
});

</script>
	<%--<c:forEach items="${entity}" var = "item">
		<div style="width:65px;height:65px;border-radius:50%;background-color:#D8D8D8;text-align:center;line-height:65px;float:left;" id ="${item.psiProcessStep}${item.psiFlowCode}">${item.psiProcessName }</div>
		<img src="${ctx}/static/images/gray.jpg" style="float:left;" width="20px;" height="25px;" align="center">

		&lt;%&ndash;<div style = "float:left;height:5px;margin-top:25px;"><HR width=20 SIZE=3 NOSHADE></div>&ndash;%&gt;
	</c:forEach>--%>
	<c:forEach items="${entity}" var = "item" varStatus="status" >
		<div class = "easyui-linkbutton" style="border-radius:50%;width:65px;height:65px;background-color:#D8D8D8;" onselectstart="return false" data-options="plain:true" id ="${item.psiProcessStep}${item.psiFlowCode}">
			<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900;letter-spacing:2px;font-family:Serif;">
					${item.psiProcessName }
			</div>
		</div>


		<c:if test="${item.psiProcessStep > errorStep && errorStep ne '-1' }">
			<img src="${ctx}/static/images/gray.jpg" width="20px;" height="25px;" align="center">
		</c:if>
		<c:if test="${item.psiProcessStep eq errorStep && errorStep ne '-1' }">
			<c:choose>
				<c:when test="${item.psiProcessStep eq '02' && processStart eq '01'}">
					<img src="${ctx}/static/images/gray.jpg" width="20px;" height="25px;" align="center">
				</c:when>
				<c:otherwise>
					<img src="${ctx}/static/images/red.jpg" width="20px;" height="25px;" align="center">
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${item.psiProcessStep < errorStep && errorStep ne '-1' }">
			<img src="${ctx}/static/images/green.jpg" width="20px;" height="25px;" align="center">
		</c:if>
		<c:if test="${errorStep eq '-1' }">
			<img src="${ctx}/static/images/green.jpg" width="20px;" height="25px;" align="center">
		</c:if>
		<c:if test="${status.last}">
			<div class = "easyui-linkbutton" style="border-radius:50%;width:65px;height:65px;background-color:#D8D8D8;" onselectstart="return false" data-options="plain:true" id = "dayEnd">
				<div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900;letter-spacing:2px;font-family:Serif;">
					处理结束
				</div>
			</div>
		</c:if>
</c:forEach>
	

