<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
        $(function() {
        	//初始化datebox，加清除按钮
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
         
            //查询渠道下产品信息、子渠道信息
         	 $('#pspChannelCode').combobox({  
               onSelect: function (row) {
              	 var channelCode = row.ID;
                   if (row != null) {
                   	//查询渠道下子渠道信息
                       $('#pspSubChannelCode').combobox({
                       	url: '${ctx}/combobox/querySubChannelComBox',
                           onBeforeLoad:function(param){
                           	param.channelCode = channelCode;
   					     },
   					    valueField:'AGENCYNO',
   					    textField:'AGENCYNAME'
                       });
                     //查询渠道下产品信息
                       $('#pspChannelProductCode').combobox({
                        	url: '${ctx}/combobox/queryUsedProductInfoComBox',
                            onBeforeLoad:function(param){
                            	param.channelCode = channelCode;
    					     },
    					    valueField:'ID',
   					    textField:'NAME'
                        });
                   }
               }
           });
            
           var curr_time = new Date();
     	   var str = curr_time.getFullYear()+"-";
     	   str += curr_time.getMonth()+1+"-";
     	   str += curr_time.getDate()+"-";
     	   str += curr_time.getHours()+":";
     	   str += curr_time.getMinutes()+":";
     	   str += curr_time.getSeconds();
            
            $('#editProduct').form({
                url : '${ctx}/productSalePrarms/editSubmit',
                onSubmit : function() {
                    progressLoad();
                    //获取个人购买参数
                    var pspIndiBuyFlag = $('input:radio[name="pspIndiBuyFlag"]:checked').val();
                    //允许个人购买 个人参数设置全部必填 
                    if(pspIndiBuyFlag=='1'){
                    	 $("#pspIndiMinBidsAmt").textbox({required:true});
                    	 $("#pspIndiBidsDiffAmt").textbox({required:true});
                    	 $("#pspIndiMaxBidsAmt").textbox({required:true});
                    	 $("#pspIndiMaxVol").textbox({required:true});
                    	 $("#pspIndiMinAppBidsAmt").textbox({required:true});
                    	 $("#pspIndiAppBidsDiffAmt").textbox({required:true});
                    	 $("#pspIndiMaxAppBidsAmt").textbox({required:true});
                    	/*  $("#pspIndiMinRedeemVol").textbox({required:true});
                    	 $("#pspIndiRedeemDiff").textbox({required:true});
                    	 $("#pspIndiMinVol").textbox({required:true});	 */
                    }else{  //不允许个人购买 个人参数设置全部非必填
                    	 $("#pspIndiMinBidsAmt").textbox({required:false});
        	           	 $("#pspIndiBidsDiffAmt").textbox({required:false});
        	           	 $("#pspIndiMaxBidsAmt").textbox({required:false});
        	           	 $("#pspIndiMaxVol").textbox({required:false});
        	           	 $("#pspIndiMinAppBidsAmt").textbox({required:false});
        	           	 $("#pspIndiAppBidsDiffAmt").textbox({required:false});
        	           	 $("#pspIndiMaxAppBidsAmt").textbox({required:false});
        	           	 /* $("#pspIndiMinRedeemVol").textbox({required:false});
        	           	 $("#pspIndiRedeemDiff").textbox({required:false});
        	           	 $("#pspIndiMinVol").textbox({required:false});		 */
                    }
                    //获取机构购买参数
                    var pspInstBuyFlag = $('input:radio[name="pspInstBuyFlag"]:checked').val();
                    //允许机构购买 机构参数设置全部必填 
                    if(pspInstBuyFlag=='1'){
                    	 $("#pspInstMinBidsAmt").textbox({required:true});
                    	 $("#pspInstBidsDiffAmt").textbox({required:true});
                    	 $("#pspInstMaxBidsAmt").textbox({required:true});
                    	 $("#pspInstMaxVol").textbox({required:true});
                    	 $("#pspInstMinAppBidsAmt").textbox({required:true});
                    	 $("#pspInstAppBidsDiffAmt").textbox({required:true});
                    	 $("#pspInstMaxAppBidsAmt").textbox({required:true});
                    	/*  $("#pspInstMinRedeemVol").textbox({required:true});
                    	 $("#pspInstRedeemDiff").textbox({required:true});
                    	 $("#pspInstMinVol").textbox({required:true}); */	
                    }else{  //不允许机构购买 机构参数设置全部非必填
                    	 $("#pspInstMinBidsAmt").textbox({required:false});
        	           	 $("#pspInstBidsDiffAmt").textbox({required:false});
        	           	 $("#pspInstMaxBidsAmt").textbox({required:false});
        	           	 $("#pspInstMaxVol").textbox({required:false});
        	           	 $("#pspInstMinAppBidsAmt").textbox({required:false});
        	           	 $("#pspInstAppBidsDiffAmt").textbox({required:false});
        	           	 $("#pspInstMaxAppBidsAmt").textbox({required:false});
        	           	 /* $("#pspInstMinRedeemVol").textbox({required:false});
        	           	 $("#pspInstRedeemDiff").textbox({required:false});
        	           	 $("#pspInstMinVol").textbox({required:false});	 */
                    }
                    var pspChannelCode = $("#pspChannelCode").combobox("getValue"); 
                    var pspChannelProductCode = $("#pspChannelProductCode").combobox("getValue"); 
                    var pspCurrencyType = $("#pspCurrencyType").combobox("getValue"); 
                    var pspPayType = $("#pspPayType").combobox("getValue"); 
                    if(pspChannelCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '渠道编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(pspChannelProductCode==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '产品编号必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(pspCurrencyType==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '支付币种必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }
                    if(pspPayType==''){
                    	$.messager.alert({
                            title : '提示',
                            msg : '缴款方式必填',
                            timeout : 5000
                        });
                    	isValid = false;
                    	progressClose();
                    	return isValid;
                    }  
                },
                success : function(result) {
                    progressClose();
                    result = $.parseJSON(result);
                    if (result.success) {
                    	parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                        parent.$.modalDialog.handler.dialog('close');
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

        });

      //个人购买标志触发事件
        $('input:radio[name="pspIndiBuyFlag"]').click(function(){
            var pspIndiBuyFlag = $('input:radio[name="pspIndiBuyFlag"]:checked').val();
            //允许个人购买 个人参数设置全部必填 
            if(ptIndiBuyFlag=='1'){
            	 $("#pspIndiMinBidsAmt").textbox({required:true});
            	 $("#pspIndiBidsDiffAmt").textbox({required:true});
            	 $("#pspIndiMaxBidsAmt").textbox({required:true});
            	 $("#pspIndiMaxVol").textbox({required:true});
            	 $("#pspIndiMinAppBidsAmt").textbox({required:true});
            	 $("#pspIndiAppBidsDiffAmt").textbox({required:true});
            	 $("#pspIndiMaxAppBidsAmt").textbox({required:true});
            	/*  $("#pspIndiMinRedeemVol").textbox({required:true});
            	 $("#pspIndiRedeemDiff").textbox({required:true});
            	 $("#pspIndiMinVol").textbox({required:true});	 */
            }else{  //不允许个人购买 个人参数设置全部非必填
            	 $("#pspIndiMinBidsAmt").textbox({required:false});
	           	 $("#pspIndiBidsDiffAmt").textbox({required:false});
	           	 $("#pspIndiMaxBidsAmt").textbox({required:false});
	           	 $("#pspIndiMaxVol").textbox({required:false});
	           	 $("#pspIndiMinAppBidsAmt").textbox({required:false});
	           	 $("#pspIndiAppBidsDiffAmt").textbox({required:false});
	           	 $("#pspIndiMaxAppBidsAmt").textbox({required:false});
	           	/*  $("#pspIndiMinRedeemVol").textbox({required:false});
	           	 $("#pspIndiRedeemDiff").textbox({required:false});
	           	 $("#pspIndiMinVol").textbox({required:false});		 */
            }
        });
        
      //机构购买标志触发事件
        $('input:radio[name="pspInstBuyFlag"]').click(function(){
            var pspInstBuyFlag = $('input:radio[name="pspInstBuyFlag"]:checked').val();
            //允许机构购买 机构参数设置全部必填 
            if(pspInstBuyFlag=='1'){
            	 $("#pspInstMinBidsAmt").textbox({required:true});
            	 $("#pspInstBidsDiffAmt").textbox({required:true});
            	 $("#pspInstMaxBidsAmt").textbox({required:true});
            	 $("#pspInstMaxVol").textbox({required:true});
            	 $("#pspInstMinAppBidsAmt").textbox({required:true});
            	 $("#pspInstAppBidsDiffAmt").textbox({required:true});
            	 $("#pspInstMaxAppBidsAmt").textbox({required:true});
            	/*  $("#pspInstMinRedeemVol").textbox({required:true});
            	 $("#pspInstRedeemDiff").textbox({required:true});
            	 $("#pspInstMinVol").textbox({required:true});	 */
            }else{  //不允许机构购买 机构参数设置全部非必填
            	 $("#pspInstMinBidsAmt").textbox({required:false});
	           	 $("#pspInstBidsDiffAmt").textbox({required:false});
	           	 $("#pspInstMaxBidsAmt").textbox({required:false});
	           	 $("#pspInstMaxVol").textbox({required:false});
	           	 $("#pspInstMinAppBidsAmt").textbox({required:false});
	           	 $("#pspInstAppBidsDiffAmt").textbox({required:false});
	           	 $("#pspInstMaxAppBidsAmt").textbox({required:false});
	           /* 	 $("#pspInstMinRedeemVol").textbox({required:false});
	           	 $("#pspInstRedeemDiff").textbox({required:false});
	           	 $("#pspInstMinVol").textbox({required:false});	 */
            }
        });

            /* 个人购买: */
  	        var pspIndiBuyFlag = ${entity.pspIndiBuyFlag};
	        if (pspIndiBuyFlag == "0") {
	            $("input[name='pspIndiBuyFlag'][value='0']").attr("checked",true); 
	        }else if(pspIndiBuyFlag == "1"){
	            $("input[name='pspIndiBuyFlag'][value='1']").attr("checked",true); 
	        }
	        /* 机构购买: */
	        var pspInstBuyFlag = ${entity.pspInstBuyFlag};
	        if (pspInstBuyFlag == "0") {
	           $("input[name='pspInstBuyFlag'][value='0']").attr("checked",true); 
	        }else if(pspInstBuyFlag == "1"){
	           $("input[name='pspInstBuyFlag'][value='1']").attr("checked",true); 
	        }
        function openIconAllHtml(){
       		window.open("${staticPath}/icon/group/icon-all.html");
       	}
        
</script>
	
	<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editProduct" method="post" >
          <div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;">
		    <div title = "多币种产品">
			<table style="background-color : #E0ECFF ;width:520px;">
			    <input id="pspId" name="pspId"  value = "${entity.pspId}"   class="easyui-numberbox" type="hidden" style="width:100px;" />
				<tr style="padding-bottom: 5px;">
						<td>渠道编号:<span style="color:red">*</span></td>
					     <td><input class="easyui-combobox"  style = "width:150px;" id="pspChannelCode"   value = "${entity.pspChannelCode}" name ="pspChannelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/></td>
				        <td>代销端产品代码:<span style="color:red">*</span></td>
					 <td><input class="easyui-combobox"  style = "width:150px;"   id="pspChannelProductCode"   name ="pspChannelProductCode"  value = "${entity.pspChannelProductCode}" data-options="
					     url:'${ctx}/combobox/queryUsedProductInfoComBox',
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',
					     required:true,
					     validType:'length[1,50]',
					     "/></td>
				</tr>
				<tr>
									
				</tr>
				
						<%-- <td >子渠道编号:<span style="color:red">*</span></td>
						<td><input class="easyui-combobox"  style = "width:150px;"   id="pspSubChannelCode"   name ="pspSubChannelCode"  value = "${entity.pspSubChannelCode}"  data-options="
					     url:'${ctx}/combobox/querySubChannelComBox',
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'AGENCYNO',
					     textField:'AGENCYNAME',
					     required:true,
					     validType:'length[1,50]',
					     "/></td> --%>
				 <tr>	
						 <td>支付币种:</td>
						<td><input id="pspCurrencyType" name="pspCurrencyType" class="easyui-combobox"  value = "${entity.pspCurrencyType}"   style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/currencyGroup',
					            valueField:'dictCode',
					            textField:'dictName'"  required="required"/>
						</td>
						<td>缴款方式:<span style="color:red">*</span></td>
						<td><input id="pspPayType" name="pspPayType"  value = "${entity.pspPayType}"   class="easyui-combobox" style = "width:140px;height:20px;" data-options="
				            url:' ${ctx}/sysDict/payTypeGroup',
				            valueField:'DICTCODE',
				            textField:'DICTNAME'" required="required"/>
						</td>
				  </tr>
				  
				  <tr>
					<td>个人购买:</td>
					<td><input type="radio" name="pspIndiBuyFlag" value="1" />是
					         <input type="radio" name="pspIndiBuyFlag" value="0" />否
					</td>
					<td style="padding-left:52px;">机构购买:</td> 
					<td>
					    <input type="radio" name="pspInstBuyFlag" value="1" />是
					    <input type="radio" name="pspInstBuyFlag" value="0" />否
					</td>
				</tr>
			</table>
	    </div>
	</div>
			<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top: 10px">
				<div title = "个人参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>首次购买最低金额（元）:</td>
							<td><input id="pspIndiMinBidsAmt" name="pspIndiMinBidsAmt"  value = "${entity.pspIndiMinBidsAmt}"   class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">首次购买级差金额（元）:</td>
							<td><input id="pspIndiBidsDiffAmt" name="pspIndiBidsDiffAmt"  value = "${entity.pspIndiBidsDiffAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="0"  data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>首次购买最高金额（元）:</td>
							<td><input id="pspIndiMaxBidsAmt" name="pspIndiMaxBidsAmt"  value = "${entity.pspIndiMaxBidsAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" min="0" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">最高账面份额（份）:</td>
							<td><input id="pspIndiMaxVol" name="pspIndiMaxVol"  value = "${entity.pspIndiMaxVol}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" min="0" data-options="min:0"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最低金额（元）:</td>
							<td><input id="pspIndiMinAppBidsAmt" name="pspIndiMinAppBidsAmt"  value = "${entity.pspIndiMinAppBidsAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">追加购买级差金额（元）:</td>
							<td><input id="pspIndiAppBidsDiffAmt" name="pspIndiAppBidsDiffAmt"  value = "${entity.pspIndiAppBidsDiffAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最高金额（元）:</td>
							<td><input id="pspIndiMaxAppBidsAmt" name="pspIndiMaxAppBidsAmt"  value = "${entity.pspIndiMaxAppBidsAmt}"   class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
						</tr>
						<%-- <tr style="padding-bottom: 5px;">
							<td>最低赎回份额（份）:</td>
							<td><input id="pspIndiMinRedeemVol" name="pspIndiMinRedeemVol"  value = "${entity.pspIndiMinRedeemVol}"   class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0"/></td>
							<td style="padding-left:25px;">赎回级差（份）:</td>
							<td><input id="pspIndiRedeemDiff" name="pspIndiRedeemDiff"  value = "${entity.pspIndiRedeemDiff}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0"/></td>
						</tr><tr style="padding-bottom: 5px;">
							<td>最低账面份额（份）:</td>
							<td><input id="pspIndiMinVol" name="pspIndiMinVol"   value = "${entity.pspIndiMinVol}"  class="easyui-numberbox" type="number"  style="width:100px;" value="0" data-options="min:0"/></td>
						</tr> --%>
					</table>
				</div>
				</div>
				<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top: 10px">
				<div title = "机构参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>首次购买最低金额（元）:</td>
							<td><input id="pspInstMinBidsAmt" class="easyui-numberbox" name="pspInstMinBidsAmt"   value = "${entity.pspInstMinBidsAmt}"  type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">首次购买级差金额（元）:</td>
							<td><input id="pspInstBidsDiffAmt" name="pspInstBidsDiffAmt" class="easyui-numberbox"  value = "${entity.pspInstBidsDiffAmt}"  type="number" style="width:100px;" value="1" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>首次购买最高金额（元）:</td>
							<td><input id="pspInstMaxBidsAmt" name="pspInstMaxBidsAmt" class="easyui-numberbox"  value = "${entity.pspInstMaxBidsAmt}"  type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">最高账面份额（份）:</td>
							<td><input id="pspInstMaxVol" name="pspInstMaxVol" class="easyui-numberbox"  value = "${entity.pspInstMaxVol}"  type="number" style="width:100px;" value="0" data-options="min:0"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最低金额（元）:</td>
							<td><input id="pspInstMinAppBidsAmt" name="pspInstMinAppBidsAmt"  value = "${entity.pspInstMinAppBidsAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">追加购买级差金额（元）:</td>
							<td><input id="pspInstAppBidsDiffAmt" name="pspInstAppBidsDiffAmt"  value = "${entity.pspInstAppBidsDiffAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="1" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最高金额（元）:</td>
							<td><input id="pspInstMaxAppBidsAmt" name="pspInstMaxAppBidsAmt"  value = "${entity.pspInstMaxAppBidsAmt}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0,precision:2"/></td>
						</tr>
						<%-- <tr style="padding-bottom: 5px;">
							<td>最低赎回份额（份）:</td>
							<td><input id="pspInstMinRedeemVol" name="pspInstMinRedeemVol"  value = "${entity.pspInstMinRedeemVol}" class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0"/></td>
							<td style="padding-left:25px;">赎回级差（份）:</td>
							<td><input id="pspInstRedeemDiff" name="pspInstRedeemDiff"  value = "${entity.pspInstRedeemDiff}"  class="easyui-numberbox" type="number" style="width:100px;" value="0" data-options="min:0"/></td>
						</tr><tr style="padding-bottom: 5px;">
							<td>最低账面份额（份）:</td>
							<td><input id="pspInstMinVol" name="pspInstMinVol"  value = "${entity.pspInstMinVol}"  class="easyui-numberbox" type="number"  style="width:100px;" value="0" data-options="min:0"/></td>
						</tr> --%>
						
					</table>
				</div>
			</div>
				</div>
			</div>
		</form>
    </div>
</div>