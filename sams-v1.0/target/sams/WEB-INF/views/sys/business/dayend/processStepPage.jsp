<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
    $(function(){
        /*
        $("${item.psiId}".menubutton('options').menu).menu({
        onClick: function (item) {
            //item 的相关属性参见API中的menu
            alert(item.text);
        }
    })
	 */
        list = '${list}';
        entity = '${entity}';
        transDate = '${transDate}';
        errorStep = '${errorStep}';
        channelCode = '${channelCode}';
        if(errorStep == -1){
            $.each(JSON.parse(list),function(index,value){
                var id = value.psiId;
                $("#"+id+"").css('background-color','#009900');
                $("#"+id+"").css('width','120px');
                $("#"+id+"").css('height','60px');
                $("#"+id+"").css('color','#000000');
                $("#"+id+"").css('margin','5px 5px');
            });
        }else{
            $.each(JSON.parse(list),function(index,value){
                var id = value.psiId;
                //根据配置步骤判断颜色新显示
                var step = value.psiProcessStep;
                if(step < errorStep){
                    $("#"+id+"").css('background-color','#009900');
                    $("#"+id+"").css('width','120px');
                    $("#"+id+"").css('height','60px');
                    $("#"+id+"").css('color','#000000');
                    $("#"+id+"").css('margin','5px 5px');
                }else if(step == errorStep){
                    $("#"+id+"").css('background-color','#CB0909');
                    $("#"+id+"").css('width','120px');
                    $("#"+id+"").css('height','60px');
                    $("#"+id+"").css('color','#000000');
                    $("#"+id+"").css('margin','5px 5px');
                    $("#"+id+"").css('-moz-user-select','none');
                }else if(step > errorStep){
                    $("#"+id+"").css('background-color','#D8D8D8');
                    $("#"+id+"").css('width','120px');
                    $("#"+id+"").css('height','60px');
                    $("#"+id+"").css('color','#000000');
                    $("#"+id+"").css('margin','5px 5px');
                    // css: body { '-moz-user-select': 'none';
                }

            });
        }
    });
    //强制通过
    function forcedBy(){
        //隐藏按钮框
        // $(".menu-content").css('display','none');
        parent.$.messager.confirm('警告', '是否强制通过该步骤？<span style = "color:red;">强制通过后请联系管理员确认数据。</span>', function(b) {
            if (b) {
                execuFunc(1);
            }else {
                parent.$.messager.show({
                    title : '提示',
                    msg : '操作取消！'
                });
            }
        });
    }
    //重做该步
    function reform (){
        //隐藏按钮框
        // $(".menu-content").css('display','none');
        parent.$.messager.confirm('警告', '是否重做该步骤？<span style = "color:red;">重做后请联系管理员确认数据。</span>', function(b) {
            if (b) {
                execuFunc(0);
            }else {
                parent.$.messager.show({
                    title : '提示',
                    msg : '操作取消！'
                });
            }
        });
    }
    //返回上步
    function returnStep(){
        alert("该功能还未实现，敬请期待！！！");
    }

    //执行方法
    function execuFunc(type){
        var branchCode = JSON.parse('${list}')[0].psiFlowCode;
        var transdate = transDate;
        $.messager.progress({
            title : '提示',
            text : '正在处理文件，请等待....'
        });
        $.modalDialog.handler.dialog('close');
        $.ajax({
            url:"${ctx}/dayEnd/forceErrorStep",    //请求的url地址
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"branchCode" : branchCode,"errorStep":parseInt(errorStep),"channelCode":channelCode,"transDate":transDate,"operType":type},    //参数值
            type:"POST",   //请求方式
            success :function(result){
                $.messager.progress('close');
                parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                $.messager.show({
                    title : '提示',
                    msg : result.msg == "" ? "处理完成" : result.msg,
                    timeout : 2000
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
</style>
<c:forEach items="${entity}" var = "item" varStatus="status" >
    <c:choose>
        <c:when test="${item.psiProcessStep eq errorStep }">
            <div class = "easyui-menubutton myself-easyuibtn" style="border-radius:50%; " onselectstart="return false" data-options="menu:'#children-${item.psiId}',hasDownArrow:false,showEvent:'dblclick',minWidth:100 " id ="${item.psiId}">
                <div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900;letter-spacing:2px;font-family:Serif;">
                        ${item.psiProcessName }
                </div>
                    <%--<span style = "font-size :20px;font-family:Georgia;">${item.psiProcessName }</span>--%>
                <div id="children-${item.psiId}" data-options="minWidth:100,width:100" class = "children" style="width: 100px;position:absolute;top:100%;left:0;z-index :9999;display:none;" >
                    <c:if test="${item.psiProcessStep eq errorStep }">
                        <div id="btnReform" data-options="iconCls:'icon-undo',minWidth:100,width:100" style="/*width: 100px;/*height: 30px;font-size :20px;font-weight:bold;*/" onclick = "reform();" title="重做该步" >重做该步</div>
                        <div id="btnforceBy" data-options="iconCls:'icon-redo',minWidth:100,width:100" style="/*width: 100px;height: 30px;font-size :20px;font-weight:bold;*/" onclick = "forcedBy();" title="强制通过" >强制通过</div>
                    </c:if>
                </div>   <!-- style="width:35px;height:35px;border-radius:50%;background-color:#D8D8D8;text-align:center;float:left;" -->
            </div>
        </c:when>
        <c:otherwise>
            <div class = "easyui-linkbutton myself-easyuibtn" style="border-radius:50%; " onselectstart="return false" data-options="plain:true" id ="${item.psiId}">
                <div class="spanDiv" style = "width: 45px;height:45px;font-size :18px;font-weight: 900; letter-spacing:2px;font-family:Serif;">
                        ${item.psiProcessName }
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    <%--<div class = "easyui-menubutton myself-easyuibtn" style="border-radius:50%; " onselectstart="return false" data-options="menu:'#children-${item.psiId}',hasDownArrow:false,showEvent:'dblclick' " id ="${item.psiId}"><span style = "font-size :23px;">${item.psiProcessName }</span>
            <div id="children-${item.psiId}" class = "children" style="width: auto;height:auto;padding-left:35px;position:absolute;top:100%;left:0;z-index:9999;display:none;" >
                <c:if test="${item.psiProcessStep eq errorStep }">
                    <button id="btnReform"  class = "children" onclick = "reform();">重做该步</button><br/><br/>
                    <button id="btnforceBy" class = "children" onclick = "forcedBy();">强制通过</button><br/>
                </c:if>
            </div>   <!-- style="width:35px;height:35px;border-radius:50%;background-color:#D8D8D8;text-align:center;float:left;" -->
    </div>--%>

    <c:if test="${!status.last}">
        <c:if test="${item.psiProcessStep > errorStep && errorStep ne '-1' }">
            <img src="${ctx}/static/images/gray.jpg" width="25px;" height="25px;" align="center">
        </c:if>
        <c:if test="${item.psiProcessStep eq errorStep && errorStep ne '-1' }">
            <img src="${ctx}/static/images/red.jpg" width="25px;" height="25px;" align="center">
        </c:if>
        <c:if test="${item.psiProcessStep < errorStep && errorStep ne '-1' }">
            <img src="${ctx}/static/images/green.jpg" width="25px;" height="25px;" align="center">
        </c:if>
        <c:if test="${errorStep eq '-1' }">
            <img src="${ctx}/static/images/green.jpg" width="25px;" height="25px;" align="center">
        </c:if>
    </c:if>
</c:forEach>
