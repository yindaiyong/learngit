<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
$(function() {
	
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
               

    //个人开户类型下拉多选
    var acctTypes = '${entity.ciPerAcctType}'.split(",");
    createCombobox('grIdentityType','grIdentityType',acctTypes);
    //机构开户类型下拉多选
    var orgAcctTypes = '${entity.ciOrgAcctType}'.split(",");
    createCombobox('jgIdentityType','zzIdentityType',orgAcctTypes);
    //94文件
    var otherFiles = '${entity.ciOtherFileType}'.split(",");
    createCombobox('94FileType','fundType',otherFiles);
    //26文件
    var other26Files = '${entity.ciVolDetailType}'.split(",");
    createCombobox('26FileType','fundType',other26Files);
    //公共方法
    function createCombobox(id,key,acctTypes) {
        $('#'+id).combobox({
            url: '${ctx}/sysDict/getSysDictForCombobox?dictName='+key+'&type=show',//对应的ashx页面的数据源
            valueField: 'DICTCODE',//绑定字段ID
            textField: 'DICTNAME',//绑定字段Name
            multiple: true,
            editable:false,
            formatter: function (row) {
                var opts = $(this).combobox('options');
                var id = row[opts.valueField];
                var value = row[opts.textField];
                return '<input type="checkbox" disabled="disabled" class="combobox-checkbox" style="font-weight: bolder;" id="' + id + '">' + value
            },

            onSelect: function (row) {
                // var opts = $(this).combobox('options');
                // var el = opts.finder.getEl(this, row[opts.valueField]);
                // el.find('input.combobox-checkbox')._propAttr('checked', true);
            },
            onUnselect: function (row) {
                // var opts = $(this).combobox('options');
                // var el = opts.finder.getEl(this, row[opts.valueField]);
                // el.find('input.combobox-checkbox')._propAttr('checked', false);
            },
            onLoadSuccess:function (data) {
                var opts = $(this).combobox('options');

                var select = new Array();
                for(var i=0;i<acctTypes.length;i++){
                    var el = opts.finder.getEl(this, acctTypes[i]);
                    el.find('input.combobox-checkbox')._propAttr('checked', true);
                    select.push(acctTypes[i])
                }
                $(this).combobox('setValues',select);
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
					<td>渠道编号:</td>
					<td><input id="ciChannelCode" name="ciChannelCode" class="easyui-textbox" type="text" value="${entity.ciChannelCode}" disabled="disabled"> <input id="ciId" name="ciId" type="hidden" value="${entity.ciId}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道名称:</td>
					<td><input id="ciChannelName" name="ciChannelName" class="easyui-textbox" type="text" value="${entity.ciChannelName}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>代销机构编号:</td>
					<td><input id="ciSaleAgentCode" name="ciSaleAgentCode" class="easyui-textbox" type="text" value="${entity.ciSaleAgentCode}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>上海信托编号:</td>
					<td><input id="ciSalesPerson" name="ciSalesPerson" class="easyui-textbox" type="text" value="${entity.ciSalesPerson}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道业务经理名称:</td>
					<td><input id="ciBizManagerName" name="ciBizManagerName" class="easyui-textbox" type="text" value="${entity.ciBizManagerName}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道业务经理手机:</td>
					<td><input id="ciBizManagerPhone" name="ciBizManagerPhone" class="easyui-textbox" type="text" value="${entity.ciBizManagerPhone}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道业务经理Email:</td>
					<td><input id="ciBizManagerMail" name="ciBizManagerMail" class="easyui-textbox" type="text" value="${entity.ciBizManagerMail}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道运维经理名称:</td>
					<td><input id="ciOpsManagerName" name="ciOpsManagerName" class="easyui-textbox" type="text" value="${entity.ciOpsManagerName}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道运维经理手机:</td>
					<td><input id="ciOpsManagerPhone" name="ciOpsManagerPhone" class="easyui-textbox" type="text" value="${entity.ciOpsManagerPhone}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>渠道运维经理Email:</td>
					<td><input id="ciOpsManagerMail" name="ciOpsManagerMail" class="easyui-textbox" type="text" value="${entity.ciOpsManagerMail}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>所属市场:</td>
					<td><input id="ciMarketCode" name="ciMarketCode" value="${entity.ciMarketCode}" class="easyui-combobox" style = "width:150px;height:20px;" data-options="
					url:' ${ctx}/sysDict/marketCodeGroup',
					valueField:'dictCode',
					textField:'dictName'" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>中登版本:</td>
					<td><input id="ciCsdcVer" name="ciCsdcVer" value="${entity.ciCsdcVer}" class="easyui-combobox" style = "width:150px;height:20px;" data-options="
					url:' ${ctx}/sysDict/csdcVerGroup',
					valueField:'dictCode',
					textField:'dictName'" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>电子合同校验方式:</td>
					<td><input id="ciEconVerifyType" name="ciEconVerifyType" value="${entity.ciEconVerifyType}" class="easyui-combobox" style = "width:150px;height:20px;" data-options="
					url:' ${ctx}/sysDict/econVerifyTypeGroup',
					valueField:'dictCode',
					textField:'dictName'" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>深圳通接收路径:</td>
					<td><input id="ciSztRecvPath" name="ciSztRecvPath" class="easyui-textbox" type="text" value="${entity.ciSztRecvPath}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>深圳通发送路径:</td>
					<td><input id="ciSztSendPath" name="ciSztSendPath" class="easyui-textbox" type="text" value="${entity.ciSztSendPath}" disabled="disabled"></td>
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
					<td><input type="checkBox" name="ciDealFile" value="01" disabled="disabled"/>账户申请(01文件)</td>
					<td><input type="checkBox" name="ciDealFile" value="03" disabled="disabled"/>交易申请(03文件)</td>
				</tr>
				
				<tr>
				    <td></td>
					<td><input type="checkBox" name="ciDealFile" value="R1" disabled="disabled"/>非居民涉税信息(R1文件)</td>
					<td><input type="checkBox" name="ciDealFile" value="43" disabled="disabled"/>电子合同文件(43文件)</td>
				</tr>
				
				<tr>
					<td>确认方式:</td>
					<td><input type="radio" name="ciProCfmWay" value="0" disabled="disabled"/>T确认</td>
					<td><input type="radio" name="ciProCfmWay" value="1" disabled="disabled"/>T+1确认</td>
					<td><input type="radio" name="ciProCfmWay" value="2" disabled="disabled"/>T+N确认</td>
				</tr>
				
			</table>
		</form>
    </div>
</div>