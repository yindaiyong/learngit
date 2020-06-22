<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
    $(function() {

        $('#piChannelCode').combobox({
            onSelect: function (row) {
                var code=row.ID;
                //通过判断渠道的批次号开关来判断批次号是否可输入
                $.ajax({
                    url:"${ctx}/channelInfo/selectChannelInfo",    //请求的url地址
                    dataType:"json",   //返回格式为json
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data:{"channelCode":code},    //参数值
                    type:"POST",   //请求方式
                    success :function(result){
                        if("1" != result.ciBatchNoOnOff){
                            $('#piBatchNumber').textbox('setValue', '1');
                            $('#piBatchNumber').textbox('readonly',true);
                            $('#piBatchNumber').textbox('color','#C4C4C4');
                        }else{
                            $('#piBatchNumber').textbox('setValue', ' ');
                            $('#piBatchNumber').textbox('readonly',false);
                        }
                    },
                });

                //通过渠道编号进行子渠道的勾选
                $.ajax({
                    url:"${ctx}/channelInfo/selectSubChannelList",    //请求的url地址
                    dataType:"json",   //返回格式为json
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data:{"channelCode":code},    //参数值
                    type:"POST",   //请求方式
                    success :function(result){
                        //初始化
                        $("#subChannelTr").html('');
                        var channelChildCode = '<td>';
                        var channelChildName = '<td>多币种子渠道 :</td>'
                        var ids = new Array();
                        //$("#subChannelTr").html(channelChildName);
                        channelChildCode +='<input id="subChannelCode" style = "width:350px;" name="piSubChannelCode" class="easyui-combobox"/></td>';
                        if(result.length == 0){
                            $("#subChannelTr").html('');
                        }else{
                            channelChildName = channelChildName+channelChildCode;
                            $("#subChannelTr").html(channelChildName);
                        }
                        //添加html组件之后重新加载下拉框数据
                        initCombobox(code)
                    },
                });

            }
        });

        function initCombobox(channelCode){
            $("#subChannelCode").combobox({
                url:"${ctx}/channelInfo/selectSubChannelList?channelCode="+channelCode,
                valueField:'AGENCYNO',
                textField:'AGENCYNAME',
                panelHeight: 'auto'
            });
        }


        $('#addProduct').form({
            url : '${ctx}/parProductInfo/addSubmit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                var piProductType = $("#piProductType").combobox("getValue");
                var piChannelCode = $("#piChannelCode").combobox("getValue");
                var piChannelProductCode = $("#piChannelProductCode").textbox("getValue");
                var piChannelProductName = $("#piChannelProductName").textbox("getValue");
                var piBatchNumber = $("#piBatchNumber").textbox("getValue");
                var piMaxAmountRaised = $("#piMaxAmountRaised").numberbox("getValue");
                if(piProductType==''){
                    $.messager.alert({
                        title : '提示',
                        msg : '模板类型必填',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }
                if(piChannelCode==''){
                    $.messager.alert({
                        title : '提示',
                        msg : '代销渠道必填',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }
                if(piChannelProductCode==''){
                    $.messager.alert({
                        title : '提示',
                        msg : '产品代码必填',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }else if(piChannelProductCode.length>6){
                    $.messager.alert({
                        title : '提示',
                        msg : '产品代码不超6位',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }
                if(piChannelProductName==''){
                    $.messager.alert({
                        title : '提示',
                        msg : '产品名称必填',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }
                if(piBatchNumber==''){
                    $.messager.alert({
                        title : '提示',
                        msg : '批次号必填',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }
                if(piMaxAmountRaised==''){
                    $.messager.alert({
                        title : '提示',
                        msg : '最高募集金额必填',
                        timeout : 5000
                    });
                    isValid = false;
                    progressClose();
                    return isValid;
                }
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    var piId = result.obj;
                    var msg = result.msg;
                    if(null !=piId){
                        parent.$.modalDialog({
                            title : '代销端产品信息修改',
                            width : 552,
                            height : 580,
                            resizable : false,
                            href : '${ctx}/parProductInfo/editPage?piId=' + piId,
                            buttons : [ {
                                text : '提交',
                                iconCls : 'icon-save',
                                handler : function() {
                                    var f = parent.$.modalDialog.handler.find('#editProduct');
                                    f.submit();
                                }
                            },{
                                text : '取消',
                                iconCls : 'icon-cancel',
                                handler : function() {
                                    parent.$.modalDialog.handler.dialog('close');
                                }
                            } ]
                        });
                    }else{
                        $.messager.show({
                            title : '提示',
                            msg : result.msg,
                            timeout : 3000
                        });
                    }
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });

    });

    function openIconAllHtml(){
        window.open("${staticPath}/icon/group/icon-all.html");
    }
</script>

<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
	<div class="gridDiv" data-options="region:'center',border:false">
		<form id="addProduct" method="post" >
			<table id="productInfoTable" style="margin: auto">
				<tr>
					<td>模板类型:<span style="color:red">*</span></td>
					<td><input id="piProductType" name="piProductType" class="easyui-combobox" style = "width:200px;" data-options="
					url:'${ctx}/combobox/queryTempalteComBox',
					valueField:'CODE',
					textField:'NAME'" required="required"/>
					</td>

				</tr>
				<tr>
					<td>代销渠道:<span style="color:red">*</span></td>
					<td><input id="piChannelCode" name="piChannelCode" class="easyui-combobox" style = "width:200px;" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" required="required" editable="false"/>
					</td>

				</tr>
				<tr> <!-- 长度在1到17位，只能输入数字和字母 -->
					<td>代销端产品代码:<span style="color:red">*</span></td>
					<td><input id="piChannelProductCode" name="piChannelProductCode" class="easyui-textbox" type="text" required="required" data-options="validType:'length[1,17]'," ></td>
				</tr>

				<tr> <!-- 长度在1到200位 -->
					<td>代销端产品名称:<span style="color:red">*</span></td>
					<td><input id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" type="text" required="required" data-options="validType:'length[1,200]'" ></td>
				</tr>
				<tr> <!-- 长度在1到10位，只能输入数字和字母 -->
					<td>批次号:<span style="color:red">*</span></td>
					<td><input id="piBatchNumber" name="piBatchNumber" class="easyui-numberbox" type="number" required="required" data-options="validType:'length[1,10]'" ></td>
				</tr>
				<tr> <!-- 长度在1到10位，只能输入数字和字母 -->
					<td>最高募集金额:<span style="color:red">*</span></td>
					<td><input id="piMaxAmountRaised" name="piMaxAmountRaised" value="10000000000" class="easyui-numberbox" type="number" data-options="validType:'length[1,14]',min:0,required:true,precision:2"></td>
				</tr>
				<tr> <!-- 长度在1到10位，只能输入数字和字母 -->
					<td>基金总份额:<span style="color:red">*</span></td>
					<td><input id="piProductTotalShare" name="piProductTotalShare" value="0" class="easyui-numberbox" type="number" data-options="min:0,required:true,precision:2"></td>
				</tr>
				<tr id="subChannelTr">

				</tr>
			</table>
		</form>
	</div>
</div>