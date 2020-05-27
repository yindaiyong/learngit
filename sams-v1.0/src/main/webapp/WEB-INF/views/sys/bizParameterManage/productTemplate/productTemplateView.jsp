<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
        $(function() {
        	//初始化产品类型 
            var ptProYieldType = ${entity.ptProYieldType};
      	    if (ptProYieldType == "0") {
      	        $("input[name='ptProYieldType'][value='0']").attr("checked",true); 
      	    }else if(ptProYieldType == "1"){
      	        $("input[name='ptProYieldType'][value='1']").attr("checked",true); 
      	    }
      	    
      	    var ptFeeFlag = ${entity.ptFeeFlag};
    	    if (ptFeeFlag == "0") {
    	        $("input[name='ptFeeFlag'][value='0']").attr("checked",true); 
    	    }else if(ptFeeFlag == "1"){
    	        $("input[name='ptFeeFlag'][value='1']").attr("checked",true); 
    	    }
      	    
      	    var ptIndiBuyFlag = ${entity.ptIndiBuyFlag};
   	        if (ptIndiBuyFlag == "0") {
   	            $("input[name='ptIndiBuyFlag'][value='0']").attr("checked",true); 
   	        }else if(ptIndiBuyFlag == "1"){
   	            $("input[name='ptIndiBuyFlag'][value='1']").attr("checked",true); 
   	        }
   	        
   	        var ptInstBuyFlag = ${entity.ptInstBuyFlag};
  	        if (ptInstBuyFlag == "0") {
  	           $("input[name='ptInstBuyFlag'][value='0']").attr("checked",true); 
  	        }else if(ptInstBuyFlag == "1"){
  	           $("input[name='ptInstBuyFlag'][value='1']").attr("checked",true); 
  	        }
   	    
   	        var ptProCfmWay = ${entity.ptProCfmWay};
            if (ptProCfmWay == "1") {
                $("input[name='ptProCfmWay'][value='1']").attr("checked",true);
                //置灰输入N值得输入框
                $("#dayN").textbox({disabled:true});
            }else{
                //赋值数据
                $("#tnRadio").val(ptProCfmWay);
                $("input[name='ptProCfmWay'][value="+ptProCfmWay+"]").attr("checked",true);
                $("#dayN").textbox({disabled:true});
                $("#dayN").textbox({value:ptProCfmWay});
            }
   	            
      	  var ptAnnuCalDays = ${entity.ptAnnuCalDays};
  	        if (ptAnnuCalDays == "365") {
  	           $("input[name='ptAnnuCalDays'][value='365']").attr("checked",true); 
  	        }else if(ptAnnuCalDays == "360"){
  	            $("input[name='ptAnnuCalDays'][value='360']").attr("checked",true); 
  	        }
        });

        function openIconAllHtml(){
       		window.open("${staticPath}/icon/group/icon-all.html");
       	}

        //设置单选框改变事件
        $('input[type=radio][name=ptProCfmWay]').change(function () {
            if(this.value == 1){
                //T+1  置灰输入框
                $("#dayN").textbox({disabled:true});
            }else {
                //T+N 取消置灰输入框
                $("#dayN").textbox({disabled:false});
                $("#dayN").textbox({required:true});
            }
        })
        //天数修改触发事件
        $(function(){
            $('#dayN').numberbox({
                onChange: function (n, o) {
                    $("#tnRadio").val(n);
                    $("input[name='ptProCfmWay'][value="+n+"]").attr("checked",true);
                }
            });
        });
</script>
	
	<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
 <form id="editProduct" method="post" >
          <div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;">
		    <div title = "模板定义">
			<table style="background-color : #E0ECFF ;width:520px;">
				<tr style="padding-bottom: 5px;">
					<td>产品类型:<span style="color:red">*</span></td>
					<td><input id="piProductType" name="piProductType" class="easyui-combobox" style = "width:160px;height:20px;" value="${entity.piProductType}" data-options="
					url:' ${ctx}/sysDict/productTypeGroup',
					valueField:'dictCode',
					textField:'dictName'" required="required" disabled="disabled"/>
					</td>
					<td style="padding-left:52px;">模板名称:<span style="color:red">*</span></td>
					<td><input id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" type="text" value="${entity.piChannelProductName}" style="width: 150px" disabled="disabled"> <input id="piInstId" name="piInstId" type="hidden" value="${entity.piInstId}" style="width: 150px"> <input id="piIndiId" name="piIndiId" type="hidden" value="${entity.piIndiId}" style="width: 150px"></td>
				</tr>
				<tr>
					<td>个人购买:</td>
					<td>
					    <input type="radio" name="ptIndiBuyFlag" value="1" disabled="disabled"/>是
					    <input type="radio" name="ptIndiBuyFlag" value="0" disabled="disabled"/>否
					</td>
					
					<td style="padding-left:52px;">机构购买:</td>
					<td>
					    <input type="radio" name="ptInstBuyFlag" value="1" disabled="disabled"/>是
					    <input type="radio" name="ptInstBuyFlag" value="0" disabled="disabled"/>否
					</td>
				</tr>
			</table>
	    </div>
	</div>
			<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;">
				<div title = "个人参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>首次购买最低金额（元）:</td>
							<td><input id="piIndiMinBidsAmt" name="piIndiMinBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinBidsAmt}" data-options="min:0,required:true,precision:2" disabled="disabled" /></td>
							<td style="padding-left:25px;">首次购买级差金额（元）:</td>
							<td><input id="piIndiBidsDiffAmt" name="piIndiBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiBidsDiffAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>首次购买最高金额（元）:</td>
							<td><input id="piIndiMaxBidsAmt" name="piIndiMaxBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMaxBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
							<td style="padding-left:25px;">最高账面份额（份）:</td>
							<td><input id="piIndiMaxVol" name="piIndiMaxVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMaxVol}" disabled="disabled"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最低金额（元）:</td>
							<td><input id="piIndiMinAppBidsAmt" name="piIndiMinAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinAppBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
							<td style="padding-left:25px;">追加购买级差金额（元）:</td>
							<td><input id="piIndiAppBidsDiffAmt" name="piIndiAppBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiAppBidsDiffAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最高金额（元）:</td>
							<td><input id="piIndiMaxAppBidsAmt" name="piIndiMaxAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMaxAppBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>最低赎回份额（份）:</td>
							<td><input id="piIndiMinRedeemVol" name="piIndiMinRedeemVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinRedeemVol}" disabled="disabled"/></td>
							<td style="padding-left:25px;">赎回级差（份）:</td>
							<td><input id="piIndiRedeemDiff" name="piIndiRedeemDiff" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiRedeemDiff}" disabled="disabled"/></td>
						</tr><tr style="padding-bottom: 5px;">
							<td>最低账面份额（份）:</td>
							<td><input id="piIndiMinVol" name="piIndiMinVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinVol}" disabled="disabled"/></td>
						</tr>
					</table>
				</div>
				</div>
				<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top: 10px">
				  <div title = "机构参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>首次购买最低金额（元）:</td>
							<td><input id="piInstMinBidsAmt" name="piInstMinBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
							<td style="padding-left:25px;">首次购买级差金额（元）:</td>
							<td><input id="piInstBidsDiffAmt" name="piInstBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstBidsDiffAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>首次购买最高金额（元）:</td>
							<td><input id="piInstMaxBidsAmt" name="piInstMaxBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMaxBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
							<td style="padding-left:25px;">最高账面份额（份）:</td>
							<td><input id="piInstMaxVol" name="piInstMaxVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMaxVol}" disabled="disabled"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最低金额（元）:</td>
							<td><input id="piInstMinAppBidsAmt" name="piInstMinAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinAppBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
							<td style="padding-left:25px;">追加购买级差金额（元）:</td>
							<td><input id="piInstAppBidsDiffAmt" name="piInstAppBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstAppBidsDiffAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最高金额（元）:</td>
							<td><input id="piInstMaxAppBidsAmt" name="piInstMaxAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMaxAppBidsAmt}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>最低赎回份额（份）:</td>
							<td><input id="piInstMinRedeemVol" name="piInstMinRedeemVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinRedeemVol}" disabled="disabled"/></td>
							<td style="padding-left:25px;">赎回级差（份）:</td>
							<td><input id="piInstRedeemDiff" name="piInstRedeemDiff" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstRedeemDiff}" disabled="disabled"/></td>
						</tr><tr style="padding-bottom: 5px;">
							<td>最低账面份额（份）:</td>
							<td><input id="piInstMinVol" name="piInstMinVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinVol}" disabled="disabled"/></td>
						</tr>
						
					</table>
				</div>
			</div>
			
			<div class="easyui-accordion" data-options="multiple:true" class="easyui-textbox" style="width:520px;background-color : #E0ECFF;border:none;">
				<div title = "模板参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>募集开始日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piIpoBeginDate" name="piIpoBeginDate" value="${entity.piIpoBeginDate}" disabled="disabled" required="required"/></td>
							<td style="padding-left:25px;">募集结束日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piIpoEndDate" name="piIpoEndDate"  value="${entity.piIpoEndDate}" disabled="disabled" required="required"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>产品成立日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piProSetupDate" name="piProSetupDate" value="${entity.piProSetupDate}" disabled="disabled" required="required"/></td>
							<td style="padding-left:25px;">产品到期日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piProEndDate" name="piProEndDate" value="${entity.piProEndDate}" disabled="disabled" required="required"/></td>
						</tr>
						
						<tr>
							<td>渠道代销费费率:</td>
							<td><input style="width:140px;" id="ptChannelRate" name="ptChannelRate" class="easyui-numberbox" type="number" style="width:100px;"  value="${entity.ptChannelRate}" disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
				            
				            <td style="padding-left:25px;">产品收益率:</td>
							<td><input style="width:140px;" id="ptProYield" name="ptProYield" class="easyui-numberbox" type="number" value="${entity.ptProYield}" style="width:100px;" value="0" data-options="min:0,required:true,precision:2" disabled="disabled"/></td>
				       </tr>
				       
						<tr>
						    <td>缴款方式:</td>
							<td><input id="ptPayType" name="ptPayType" class="easyui-combobox" value="${entity.ptPayType}" style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/payTypeGroup',
					            valueField:'DICTCODE',
					            textField:'DICTNAME'" disabled="disabled"/>
							</td>
							
							<td style="padding-left:25px;">支付币种:</td>
							<td><input id="piCurrency" name="piCurrency" class="easyui-combobox" value="${entity.piCurrency}" style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/currencyGroup',
					            valueField:'dictCode',
					            textField:'dictName'" disabled="disabled"/>
							</td>
				       </tr>
				       
				       <tr>
							<td>渠道代销费费率:</td>
							<td><input style="width:140px;" id="ptChannelRate" name="ptChannelRate" class="easyui-numberbox" type="number" value="${entity.ptChannelRate}" readonly="readonly" data-options="min:0,required:true,precision:2"/></td>
							<%-- <td style="padding-left:25px;">产品收益率:</td>
							<td><input style="width:140px;" id="ptProYield" name="ptProYield" class="easyui-numberbox" type="number" value="${entity.ptProYield}" style="width:100px;" value="0" data-options="min:0,required:true,precision:2"/></td> --%>
				       </tr>
				</table>
				
			<table style="background-color : #E0ECFF;width:520px;">
			   <!--  <tr>
				    <td>受益类别计算方式:</td>
					<td><input type="radio"  name="ptProCalculateType" value="1" />默认计算方式</td>
					<td><input type="radio"  name="ptProCalculateType" value="0" />自动计算方式</td>
				</tr> -->
				<tr>
				    <td>确认方式:</td>
					<td><input type="radio" name="ptProCfmWay" value="1"  disabled="disabled"/>T+1确认</td>
					<td><input type="radio" name="ptProCfmWay" id = "tnRadio" disabled="disabled"/>T+N确认
						<input id="dayN" class="easyui-numberbox" type="number" style="width:50px;" data-options="min:2"/>
					</td>
					
				</tr>
				<tr>
					<td>是否含认购费:</td>
					<td><input type="radio" name="ptFeeFlag" value="1"  disabled="disabled"/>是</td>
					<td><input type="radio" name="ptFeeFlag" value="0" disabled="disabled"/>否</td>
				</tr>
				<tr>
					<td>收益计算方式:</td>
					<td><input type="radio" name="ptProYieldType" value="0" disabled="disabled"/>算头不算尾</td>
					<td><input type="radio" name="ptProYieldType" value="1" disabled="disabled"/>算头又算尾</td>
				</tr>
				<tr>
					<td>年化计算天数:</td>
					<td><input type="radio" name="ptAnnuCalDays" value="365" disabled="disabled"/>365</td>
					<td><input type="radio" name="ptAnnuCalDays" value="360" disabled="disabled"/>360</td>
				</tr>
					</table>
				</div>
			</div>
		</form>
    </div>
</div>