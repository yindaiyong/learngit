<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
        $(function() {
        	//dateBox控件加清除按钮
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
        	
        	$('#ciChannelCode').combobox({  
          		 onSelect: function (row) { 
          			 var name=row.NAME;
                   	 $('#ciChannelName').textbox('setValue', name.replace(/\([^\)]*\)/g,""));
                   }  
               });

   		    //处理文件赋值
   	            var dealFiles = '${entity.ciDealFile}'.split(",");
   	            //获取复选框
   	            var ciDealFile = $("input[name='ciDealFile']");
   	                //循环acctTypes与复选框，当acctTypes的值与复选框中value一致，状态选中
   	   		         for(var i=0;i<dealFiles.length;i++){
   	   		            for(var j=0;j<ciDealFile.length;j++){
   	   		                if(dealFiles[i]==ciDealFile[j].value)
   	   		                 ciDealFile[j].checked = true;
   	   		            }
   	   		         }
   	                
   	   		 //确认方式赋值 
   	   	            var proCfmWays = '${entity.ciProCfmWay}'.split(",");
   	   	            //获取复选框
   	   	            var ciProCfmWay = $("input[name='ciProCfmWay']");
   	   	                //循环acctTypes与复选框，当acctTypes的值与复选框中value一致，状态选中
   	   	   		         for(var i=0;i<proCfmWays.length;i++){
   	   	   		            for(var j=0;j<ciProCfmWay.length;j++){
   	   	   		                if(proCfmWays[i]==ciProCfmWay[j].value)
   	   	   		                 ciProCfmWay[j].checked = true;
   	   	   		            }
   	   	   		         }



            $('#editChannelInfo').form({
                url : '${ctx}/channelInfo/editSubmit',
                onSubmit : function() {
                	progressLoad();
                    var isValid = $(this).form('validate');
                    var ciChannelCode = $("#ciChannelCode").textbox("getValue"); 
                    var ciChannelName = $("#ciChannelName").textbox("getValue"); 
                    var ciSaleAgentCode = $("#ciSaleAgentCode").textbox("getValue"); 
                    var ciSalesPerson = $("#ciSalesPerson").textbox("getValue"); 
                    var ciBizManagerName = $("#ciBizManagerName").textbox("getValue"); 
                    var ciBizManagerPhone = $("#ciBizManagerPhone").textbox("getValue"); 
                    var ciOpsManagerMail = $("#ciOpsManagerMail").textbox("getValue"); 
                    var ciMarketCode = $("#ciMarketCode").combobox("getValue"); 
                    var ciCsdcVer = $("#ciCsdcVer").combobox("getValue"); 
                    var ciEconVerifyType = $("#ciEconVerifyType").combobox("getValue"); 
                    if(ciChannelCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '渠道编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciChannelName==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '渠道名称必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciSaleAgentCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '代销机构编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciSalesPerson==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '上海信托编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    /* if(ciBizManagerName==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '业务经理名称必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciBizManagerPhone==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '业务经理手机号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciOpsManagerMail==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '业务经理Email必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    } */
                    if(ciMarketCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '所属市场必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciCsdcVer==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '中登版本必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(ciEconVerifyType==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '电子合同校验方式必填',
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
                    if (result.success) {
                    	 parent.$.modalDialog.handler.dialog('close');
                         parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                        $.messager.show({
                            title : '提示',
                            msg : result.msg,
                            timeout : 1000
                        });
                    } else {
                        parent.$.messager.alert('错误', result.msg, 'error');
                    }
                }
            });

            //个人开户类型下拉多选
            var acct = '${entity.ciPerAcctType}'+'';
            if(acct == null || acct == ''){
                createCombobox('grIdentityType','grIdentityType',null);
			}else{
                var acctTypes = acct.split(",");
                createCombobox('grIdentityType','grIdentityType',acctTypes);
			}

            //机构开户类型下拉多选
            var org = '${entity.ciOrgAcctType}'+'';
            if(org == null || org == ''){
                createCombobox('jgIdentityType','zzIdentityType',null);
            }else{
                var orgAcctTypes = org.split(",");
                createCombobox('jgIdentityType','zzIdentityType',orgAcctTypes);
            }
            //94文件
            var other = '${entity.ciOtherFileType}'+'';
            if(other == null || other == ''){
                createCombobox('94FileType','fundType',null);
            }else{
                var otherFiles = other.split(",");
                createCombobox('94FileType','fundType',otherFiles);
            }
            //26文件
            var other26 = '${entity.ciVolDetailType}'+'';
            if(other26 == null || other26 == ''){
                createCombobox('26FileType','fundType',null);
            }else{
                var other26Files = other26.split(",");
                createCombobox('26FileType','fundType',other26Files);
            }

            //公共方法
            function createCombobox(id,key,acctTypes) {
                $('#'+id).combobox({
                    url: '${ctx}/sysDict/getSysDictForCombobox?dictName='+key+'&type=edit',//对应的ashx页面的数据源
                    valueField: 'DICTCODE',//绑定字段ID
                    textField: 'DICTNAME',//绑定字段Name
                    multiple: true,
                    formatter: function (row) {
                        var opts = $(this).combobox('options');
                        var id = row[opts.valueField];
                        var value = row[opts.textField];
                        return '<input type="checkbox" class="combobox-checkbox" id="' + id + '">' + value
                    },

                    onSelect: function (row) {
                        var opts = $(this).combobox('options');
                        var el = opts.finder.getEl(this, row[opts.valueField]);
                        el.find('input.combobox-checkbox')._propAttr('checked', true);
                    },
                    onUnselect: function (row) {
                        var opts = $(this).combobox('options');
                        var el = opts.finder.getEl(this, row[opts.valueField]);
                        el.find('input.combobox-checkbox')._propAttr('checked', false);
                    },
                    onLoadSuccess:function (data) {
                        if(acctTypes != null){
                            var opts = $(this).combobox('options');
                            var select = new Array();
                            for(var i=0;i<acctTypes.length;i++){
                                var el = opts.finder.getEl(this, acctTypes[i]);
                                el.find('input.combobox-checkbox')._propAttr('checked', true);
                                select.push(acctTypes[i])
                            }
                            if(select.length != 0){
                                $(this).combobox('setValues',select);
                            }else{
                                $(this).combobox('clear');
                            }
						}

                    }
                });
            }
        });

        function openIconAllHtml(){
       		window.open("${staticPath}/icon/group/icon-all.html");
       	}
</script>
	
	<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editChannelInfo" method="post" >
			<table  style="margin: auto">
			    <tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input class="easyui-combobox"  style = "width:250px;" id="ciChannelCode"   name ="ciChannelCode" value="${entity.ciChannelCode}" data-options="
					     url:'${ctx}/combobox/queryTAChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/>
					    <input id="ciId" name="ciId" type="hidden" value="${entity.ciId}">
					    <input id="ciCuser" name="ciCuser" type="hidden" value="${entity.ciCuser}">
					    <input id="ciCtime" class="easyui-datetimebox" name="ciCtime" type="hidden" value="${entity.ciCtime}" data-options="showSeconds:true">
					    <input id="ciUuser" name="ciUuser" type="hidden" value="${entity.ciUuser}">
					    <input id="ciUtime" name="ciUtime" type="hidden" value="${entity.ciUtime}">
					    <input id="ciValidFlag" name="ciValidFlag" type="hidden" value="${entity.ciValidFlag}">
					    <input id="ciCheckState" name="ciCheckState" type="hidden" value="${entity.ciCheckState}"></td>
				</tr>
				<%-- <tr>
					<td>渠道编号:<span style="color:red">*</span></td>
					<td><input id="ciChannelCode"  name="ciChannelCode" class="easyui-textbox" type="text" value="${entity.ciChannelCode}" required="required" data-options="validType:'length[1,50]'," > 
					    <input id="ciId" name="ciId" type="hidden" value="${entity.ciId}">
					    <input id="ciCuser" name="ciCuser" type="hidden" value="${entity.ciCuser}">
					    <input id="ciCtime" class="easyui-datetimebox" name="ciCtime" type="hidden" value="${entity.ciCtime}" data-options="showSeconds:true">
					    <input id="ciUuser" name="ciUuser" type="hidden" value="${entity.ciUuser}">
					    <input id="ciUtime" name="ciUtime" type="hidden" value="${entity.ciUtime}">
					    <input id="ciValidFlag" name="ciValidFlag" type="hidden" value="${entity.ciValidFlag}">
					    <input id="ciCheckState" name="ciCheckState" type="hidden" value="${entity.ciCheckState}"></td>
				</tr> --%>
				<tr>
					<td>渠道名称:<span style="color:red">*</span></td>
					<td><input id="ciChannelName" name="ciChannelName" class="easyui-textbox" type="text" value="${entity.ciChannelName}" required="required" data-options="validType:'length[1,200]'" ></td>
				</tr>
				<tr>
					<td>代销机构编号:<span style="color:red">*</span></td>
					<td><input id="ciSaleAgentCode" name="ciSaleAgentCode" class="easyui-textbox" type="text" value="${entity.ciSaleAgentCode}" required="required" ></td>
				</tr>
				<tr>
					<td>上海信托编号:<span style="color:red">*</span></td><!-- 长度在1到8位，只能输入数字和字母 -->
					<td><input id="ciSalesPerson" name="ciSalesPerson" class="easyui-textbox" type="text" value="${entity.ciSalesPerson}" required="required" data-options="validType:'length[1,8]'," ></td>
				</tr>
				<tr>
					<td>渠道业务经理名称:</td>
					<td><input id="ciBizManagerName" name="ciBizManagerName" class="easyui-textbox" type="text" value="${entity.ciBizManagerName}"  data-options="validType:'length[1,200]'"></td>
				</tr>
				<tr>
					<td>渠道业务经理手机:</td>
					<td><input id="ciBizManagerPhone" name="ciBizManagerPhone" class="easyui-textbox" type="text" value="${entity.ciBizManagerPhone}"  data-options="validType:'mobile'"></td>
				</tr>
				<tr>
					<td>渠道业务经理Email:</td>
					<td><input id="ciBizManagerMail" name="ciBizManagerMail" class="easyui-textbox" type="text" value="${entity.ciBizManagerMail}"  data-options="validType:'email'"></td>
				</tr>
				<tr>
					<td>渠道运维经理名称:</td>
					<td><input id="ciOpsManagerName" name="ciOpsManagerName" class="easyui-textbox" type="text" value="${entity.ciOpsManagerName}" data-options="validType:'length[1,200]'"></td>
				</tr>
				<tr>
					<td>渠道运维经理手机:</td>
					<td><input id="ciOpsManagerPhone" name="ciOpsManagerPhone" class="easyui-textbox" type="text" value="${entity.ciOpsManagerPhone}" data-options="validType:'mobile'"></td>
				</tr>
				<tr>
					<td>渠道运维经理Email:</td>
					<td><input id="ciOpsManagerMail" name="ciOpsManagerMail" class="easyui-textbox" type="text" value="${entity.ciOpsManagerMail}" data-options="validType:'email'"></td>
				</tr>
				<tr>
					<td>所属市场:<span style="color:red">*</span></td>
					<td><input id="ciMarketCode" name="ciMarketCode" value="${entity.ciMarketCode}" class="easyui-combobox" style = "width:150px;height:20px;" data-options="
					url:' ${ctx}/sysDict/marketCodeGroup',
					valueField:'dictCode',
					textField:'dictName'" required="required"/>
					</td>
				</tr>
				<tr>
					<td>中登版本:<span style="color:red">*</span></td>
					<td><input id="ciCsdcVer" name="ciCsdcVer" value="${entity.ciCsdcVer}" class="easyui-combobox" style = "width:150px;height:20px;" data-options="
					url:' ${ctx}/sysDict/csdcVerGroup',
					valueField:'dictCode',
					textField:'dictName'" required="required"/>
					</td>
				</tr>
				
				<tr>
					<td>电子合同校验方式:<span style="color:red">*</span></td>
					<td><input id="ciEconVerifyType" name="ciEconVerifyType" value="${entity.ciEconVerifyType}" class="easyui-combobox" style = "width:150px;height:20px;" data-options="
					url:' ${ctx}/sysDict/econVerifyTypeGroup',
					valueField:'dictCode',
					textField:'dictName'" required="required"/>
					</td>
				</tr>
				<tr>
					<td>深圳通接收路径:</td>
					<td><input id="ciSztRecvPath" name="ciSztRecvPath" class="easyui-textbox" type="text" value="${entity.ciSztRecvPath}"></td>
				</tr>
				<tr>
					<td>深圳通发送路径:</td>
					<td><input id="ciSztSendPath" name="ciSztSendPath" class="easyui-textbox" type="text" value="${entity.ciSztSendPath}"></td>
				</tr>
				<tr>
					<td>个人开户类型:</td>
					<td><input id="grIdentityType" name="ciPerAcctType" class="easyui-combobox" style = "width:200px;height:20px;" />
					</td>
				</tr>
				<tr>
					<td>机构开户类型:</td>
					<td><input id="jgIdentityType" name="ciOrgAcctType" class="easyui-combobox" style = "width:200px;height:20px;" />
					</td>
				</tr>
				<tr>
					<td>94文件类型:</td>
					<td><input id="94FileType" name="ciOtherFileType" class="easyui-combobox" style = "width:200px;height:20px;" />
					</td>
				</tr>
				<tr>
					<td>26文件类型:</td>
					<td><input id="26FileType" name="ciVolDetailType" class="easyui-combobox" style = "width:200px;height:20px;" />
					</td>
				</tr>
				</table>
				<table style="margin: auto">

				<tr>
					<td>处理文件:</td>
					<td><input type="checkBox" name="ciDealFile" value="01" />账户申请(01文件)</td>
					<td><input type="checkBox" name="ciDealFile" value="03" />交易申请(03文件)</td>
				</tr>
				<tr>
				    <td></td>
					<td><input type="checkBox" name="ciDealFile" value="R1" />非居民涉税信息(R1文件)</td>
					<td><input type="checkBox" name="ciDealFile" value="43" />电子合同文件(43文件)</td>
				</tr>
				
				<tr>
					<td>确认方式:</td>
					<td><input type="radio" name="ciProCfmWay" value="0"  />T确认</td>
					<td><input type="radio" name="ciProCfmWay" value="1"  />T+1确认</td>
					<td><input type="radio" name="ciProCfmWay" value="2" />T+N确认</td>
				</tr>
			</table>
		</form>
    </div>
</div>