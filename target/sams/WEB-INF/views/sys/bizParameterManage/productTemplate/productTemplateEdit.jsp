<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
        $(function() {
          	//初始化产品类型 
      		 $('#piProductType').combobox({
   			 url:"${ctx}/sysDict/productTypeGroup", //访问controller的路径
   			    valueField:'dictCode',
   			    textField:'dictName'   
   		    });
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
				$("#dayN").textbox({disabled:false});
				$("#dayN").textbox({required:true});
				$("#dayN").textbox({value:ptProCfmWay});
   	        }
   	            
   	        /* var ptProCalculateType = ${entity.ptProCalculateType};
     	    if (ptProCalculateType == "0") {
     	        $("input[name='ptProCalculateType'][value='0']").attr("checked",true); 
     	    }else if(ptProCalculateType == "1"){
     	        $("input[name='ptProCalculateType'][value='1']").attr("checked",true); 
     	    } */
     	    
     	   var ptAnnuCalDays = ${entity.ptAnnuCalDays};
    	    if (ptAnnuCalDays == "365") {
    	        $("input[name='ptAnnuCalDays'][value='365']").attr("checked",true); 
    	    }else if(ptAnnuCalDays == "360"){
    	        $("input[name='ptAnnuCalDays'][value='360']").attr("checked",true); 
    	    }
    	    
            /*个人首次购买最低金额（元） */
            $("#piIndiMinBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMinBidsAmt = $("#piIndiMinBidsAmt").numberbox("getValue");
                	if(piIndiMinBidsAmt==''){
                   	 $('#piIndiMinBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
            /*个人首次购买级差金额（元） */
            $("#piIndiBidsDiffAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiBidsDiffAmt = $("#piIndiBidsDiffAmt").numberbox("getValue");
                	if(piIndiBidsDiffAmt==''){
                   	 $('#piIndiBidsDiffAmt').numberbox('setValue', '1');
                   }	
                }
                
             });
            
            /*个人首次购买最高金额（元) */
            $("#piIndiMaxBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMaxBidsAmt = $("#piIndiMaxBidsAmt").numberbox("getValue");
                	if(piIndiMaxBidsAmt==''){
                   	 $('#piIndiMaxBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
        
            /*个人最高账面份额（份） */
            $("#piIndiMaxVol").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMaxVol = $("#piIndiMaxVol").numberbox("getValue");
                	if(piIndiMaxVol==''){
                   	 $('#piIndiMaxVol').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*个人追加购买最低金额（元） */
            $("#piIndiMinAppBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMinAppBidsAmt = $("#piIndiMinAppBidsAmt").numberbox("getValue");
                	if(piIndiMinAppBidsAmt==''){
                   	 $('#piIndiMinAppBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*个人追加购买级差金额（元） */
            $("#piIndiAppBidsDiffAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiAppBidsDiffAmt = $("#piIndiAppBidsDiffAmt").numberbox("getValue");
                	if(piIndiAppBidsDiffAmt==''){
                   	 $('#piIndiAppBidsDiffAmt').numberbox('setValue', '1');
                   }	
                }
                
             });
            
            /*个人追加购买最高金额（元） */
            $("#piIndiMaxAppBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMaxAppBidsAmt = $("#piIndiMaxAppBidsAmt").numberbox("getValue");
                	if(piIndiMaxAppBidsAmt==''){
                   	 $('#piIndiMaxAppBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*个人最低赎回份额 */
            $("#piIndiMinRedeemVol").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMinRedeemVol = $("#piIndiMinRedeemVol").numberbox("getValue");
                	if(piIndiMinRedeemVol==''){
                   	 $('#piIndiMinRedeemVol').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*个人赎回级差（份） */
            $("#piIndiRedeemDiff").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiRedeemDiff = $("#piIndiRedeemDiff").numberbox("getValue");
                	if(piIndiRedeemDiff==''){
                   	 $('#piIndiRedeemDiff').numberbox('setValue', '1');
                   }	
                }
                
             });
            
            /*个人最低账面份额（份） */
            $("#piIndiMinVol").numberbox({
                onChange: function (newValue, oldValue) {
                    var piIndiMinVol = $("#piIndiMinVol").numberbox("getValue");
                	if(piIndiMinVol==''){
                   	 $('#piIndiMinVol').numberbox('setValue', '0');
                   }	
                }
            }); 
            
            /*机构首次购买最低金额（元） */
            $("#piInstMinBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMinBidsAmt = $("#piInstMinBidsAmt").numberbox("getValue");
                	if(piInstMinBidsAmt==''){
                   	 $('#piInstMinBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
            /*机构首次购买级差金额（元） */
            $("#piInstBidsDiffAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstBidsDiffAmt = $("#piInstBidsDiffAmt").numberbox("getValue");
                	if(piInstBidsDiffAmt==''){
                   	 $('#piInstBidsDiffAmt').numberbox('setValue', '1');
                   }	
                }
                
             });
            
            /*机构首次购买最高金额（元) */
            $("#piInstMaxBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMaxBidsAmt = $("#piInstMaxBidsAmt").numberbox("getValue");
                	if(piInstMaxBidsAmt==''){
                   	 $('#piInstMaxBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
        
            /*机构最高账面份额（份） */
            $("#piInstMaxVol").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMaxVol = $("#piInstMaxVol").numberbox("getValue");
                	if(piInstMaxVol==''){
                   	 $('#piInstMaxVol').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*机构追加购买最低金额（元） */
            $("#piInstMinAppBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMinAppBidsAmt = $("#piInstMinAppBidsAmt").numberbox("getValue");
                	if(piInstMinAppBidsAmt==''){
                   	 $('#piInstMinAppBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*机构追加购买级差金额（元） */
            $("#piInstAppBidsDiffAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstAppBidsDiffAmt = $("#piInstAppBidsDiffAmt").numberbox("getValue");
                	if(piInstAppBidsDiffAmt==''){
                   	 $('#piInstAppBidsDiffAmt').numberbox('setValue', '1');
                   }	
                }
                
             });
            
            /*机构追加购买最高金额（元） */
            $("#piInstMaxAppBidsAmt").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMaxAppBidsAmt = $("#piInstMaxAppBidsAmt").numberbox("getValue");
                	if(piInstMaxAppBidsAmt==''){
                   	 $('#piInstMaxAppBidsAmt').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*机构最低赎回份额 */
            $("#piInstMinRedeemVol").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMinRedeemVol = $("#piInstMinRedeemVol").numberbox("getValue");
                	if(piInstMinRedeemVol==''){
                   	 $('#piInstMinRedeemVol').numberbox('setValue', '0');
                   }	
                }
                
             });
            
            /*机构赎回级差（份） */
            $("#piInstRedeemDiff").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstRedeemDiff = $("#piInstRedeemDiff").numberbox("getValue");
                	if(piInstRedeemDiff==''){
                   	 $('#piInstRedeemDiff').numberbox('setValue', '1');
                   }	
                }
                
             });
            
            /*机构最低账面份额（份） */
            $("#piInstMinVol").numberbox({
                onChange: function (newValue, oldValue) {
                    var piInstMinVol = $("#piInstMinVol").numberbox("getValue");
                	if(piInstMinVol==''){
                   	 $('#piInstMinVol').numberbox('setValue', '0');
                   }	
                }
            });     	    
     	   
            $('#editProduct').form({
                url : '${ctx}/productTemplate/editSubmit',
                onSubmit : function() {
                	 progressLoad();
                	 //获取个人购买参数
                     var ptIndiBuyFlag = $('input:radio[name="ptIndiBuyFlag"]:checked').val();
                     //允许个人购买 个人参数设置全部必填 
                     if(ptIndiBuyFlag=='1'){
                     	 $("#piIndiMinBidsAmt").textbox({required:true});
                     	 $("#piIndiBidsDiffAmt").textbox({required:true});
                     	 $("#piIndiMaxBidsAmt").textbox({required:true});
                     	 $("#piIndiMaxVol").textbox({required:true});
                     	 $("#piIndiMinAppBidsAmt").textbox({required:true});
                     	 $("#piIndiAppBidsDiffAmt").textbox({required:true});
                     	 $("#piIndiMaxAppBidsAmt").textbox({required:true});
                     	 $("#piIndiMinRedeemVol").textbox({required:true});
                     	 $("#piIndiRedeemDiff").textbox({required:true});
                     	 $("#piIndiMinVol").textbox({required:true});	
                     }else{  //不允许个人购买 个人参数设置全部非必填
                     	 $("#piIndiMinBidsAmt").textbox({required:false});
         	           	 $("#piIndiBidsDiffAmt").textbox({required:false});
         	           	 $("#piIndiMaxBidsAmt").textbox({required:false});
         	           	 $("#piIndiMaxVol").textbox({required:false});
         	           	 $("#piIndiMinAppBidsAmt").textbox({required:false});
         	           	 $("#piIndiAppBidsDiffAmt").textbox({required:false});
         	           	 $("#piIndiMaxAppBidsAmt").textbox({required:false});
         	           	 $("#piIndiMinRedeemVol").textbox({required:false});
         	           	 $("#piIndiRedeemDiff").textbox({required:false});
         	           	 $("#piIndiMinVol").textbox({required:false});		
                     }
                     //获取机构购买参数
                     var ptInstBuyFlag = $('input:radio[name="ptInstBuyFlag"]:checked').val();
                     //允许机构购买 机构参数设置全部必填 
                     if(ptInstBuyFlag=='1'){
                     	 $("#piInstMinBidsAmt").textbox({required:true});
                     	 $("#piInstBidsDiffAmt").textbox({required:true});
                     	 $("#piInstMaxBidsAmt").textbox({required:true});
                     	 $("#piInstMaxVol").textbox({required:true});
                     	 $("#piInstMinAppBidsAmt").textbox({required:true});
                     	 $("#piInstAppBidsDiffAmt").textbox({required:true});
                     	 $("#piInstMaxAppBidsAmt").textbox({required:true});
                     	 $("#piInstMinRedeemVol").textbox({required:true});
                     	 $("#piInstRedeemDiff").textbox({required:true});
                     	 $("#piInstMinVol").textbox({required:true});	
                     }else{  //不允许机构购买 机构参数设置全部非必填
                     	 $("#piInstMinBidsAmt").textbox({required:false});
         	           	 $("#piInstBidsDiffAmt").textbox({required:false});
         	           	 $("#piInstMaxBidsAmt").textbox({required:false});
         	           	 $("#piInstMaxVol").textbox({required:false});
         	           	 $("#piInstMinAppBidsAmt").textbox({required:false});
         	           	 $("#piInstAppBidsDiffAmt").textbox({required:false});
         	           	 $("#piInstMaxAppBidsAmt").textbox({required:false});
         	           	 $("#piInstMinRedeemVol").textbox({required:false});
         	           	 $("#piInstRedeemDiff").textbox({required:false});
         	           	 $("#piInstMinVol").textbox({required:false});	
                     }
                     var isValid = $(this).form('validate');
                     var piProductType = $("#piProductType").combobox("getValue"); 
                     var piChannelProductName = $("#piChannelProductName").textbox("getValue"); 
                     var piIpoBeginDate = $("#piIpoBeginDate").datebox("getValue"); 
                     var piIpoEndDate = $("#piIpoEndDate").datebox("getValue");  
                     var piProSetupDate = $("#piProSetupDate").datebox("getValue");  
                     var piProEndDate = $("#piProEndDate").datebox("getValue");  
                     var ptPayType = $("#ptPayType").combobox("getValue"); 
                     var piCurrency = $("#piCurrency").combobox("getValue"); 
                     if(piProductType==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '产品类型必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(piChannelProductName==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '模板名称必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(piIpoBeginDate=='' ||piIpoEndDate=='' || piProSetupDate=='' || piProEndDate==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '募集开始日,募集结束日,产品成立日,产品到日期必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(piIpoEndDate<piIpoBeginDate){
                     	$.messager.alert({
                             title : '提示',
                             msg : '募集结束日应大于等于募集开始日',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(piProSetupDate<=piIpoEndDate){
                     	$.messager.alert({
                             title : '提示',
                             msg : '产品成立日应大于募集结束日',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(piProEndDate!=''){
                     	if(piProEndDate<=piProSetupDate){
                         	$.messager.alert({
                                 title : '提示',
                                 msg : '产品到期日应大于产品成立日',
                                 timeout : 5000
                             });
                         	isValid = false;
                         	progressClose();
                         	return isValid;
                         }	
                     }
                     if(ptPayType==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '缴款方式必填',
                             timeout : 5000
                         });
                     	isValid = false;
                     	progressClose();
                     	return isValid;
                     }
                     if(piCurrency==''){
                     	$.messager.alert({
                             title : '提示',
                             msg : '支付币种必填',
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
        $('input:radio[name="ptIndiBuyFlag"]').click(function(){
            var ptIndiBuyFlag = $('input:radio[name="ptIndiBuyFlag"]:checked').val();
            //允许个人购买 个人参数设置全部必填 
            if(ptIndiBuyFlag=='1'){
            	 $("#piIndiMinBidsAmt").textbox({required:true});
            	 $("#piIndiBidsDiffAmt").textbox({required:true});
            	 $("#piIndiMaxBidsAmt").textbox({required:true});
            	 $("#piIndiMaxVol").textbox({required:true});
            	 $("#piIndiMinAppBidsAmt").textbox({required:true});
            	 $("#piIndiAppBidsDiffAmt").textbox({required:true});
            	 $("#piIndiMaxAppBidsAmt").textbox({required:true});
            	 $("#piIndiMinRedeemVol").textbox({required:true});
            	 $("#piIndiRedeemDiff").textbox({required:true});
            	 $("#piIndiMinVol").textbox({required:true});	
            }else{  //不允许个人购买 个人参数设置全部非必填
            	 $("#piIndiMinBidsAmt").textbox({required:false});
	           	 $("#piIndiBidsDiffAmt").textbox({required:false});
	           	 $("#piIndiMaxBidsAmt").textbox({required:false});
	           	 $("#piIndiMaxVol").textbox({required:false});
	           	 $("#piIndiMinAppBidsAmt").textbox({required:false});
	           	 $("#piIndiAppBidsDiffAmt").textbox({required:false});
	           	 $("#piIndiMaxAppBidsAmt").textbox({required:false});
	           	 $("#piIndiMinRedeemVol").textbox({required:false});
	           	 $("#piIndiRedeemDiff").textbox({required:false});
	           	 $("#piIndiMinVol").textbox({required:false});		
            }
        });
        
      //机构购买标志触发事件
        $('input:radio[name="ptInstBuyFlag"]').click(function(){
            var ptInstBuyFlag = $('input:radio[name="ptInstBuyFlag"]:checked').val();
            //允许机构购买 机构参数设置全部必填 
            if(ptInstBuyFlag=='1'){
            	 $("#piInstMinBidsAmt").textbox({required:true});
            	 $("#piInstBidsDiffAmt").textbox({required:true});
            	 $("#piInstMaxBidsAmt").textbox({required:true});
            	 $("#piInstMaxVol").textbox({required:true});
            	 $("#piInstMinAppBidsAmt").textbox({required:true});
            	 $("#piInstAppBidsDiffAmt").textbox({required:true});
            	 $("#piInstMaxAppBidsAmt").textbox({required:true});
            	 $("#piInstMinRedeemVol").textbox({required:true});
            	 $("#piInstRedeemDiff").textbox({required:true});
            	 $("#piInstMinVol").textbox({required:true});	
            }else{  //不允许机构购买 机构参数设置全部非必填
            	 $("#piInstMinBidsAmt").textbox({required:false});
	           	 $("#piInstBidsDiffAmt").textbox({required:false});
	           	 $("#piInstMaxBidsAmt").textbox({required:false});
	           	 $("#piInstMaxVol").textbox({required:false});
	           	 $("#piInstMinAppBidsAmt").textbox({required:false});
	           	 $("#piInstAppBidsDiffAmt").textbox({required:false});
	           	 $("#piInstMaxAppBidsAmt").textbox({required:false});
	           	 $("#piInstMinRedeemVol").textbox({required:false});
	           	 $("#piInstRedeemDiff").textbox({required:false});
	           	 $("#piInstMinVol").textbox({required:false});	
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
					textField:'dictName'" required="required"//></td>
					<td style="padding-left:52px;">模板名称:<span style="color:red">*</span></td>
					<td><input id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" type="text" value="${entity.piChannelProductName}" style="width: 150px" > 
					<input id="piInstId" name="piInstId" type="hidden" value="${entity.piInstId}" style="width: 150px"> 
					<input id="piIndiId" name="piIndiId" type="hidden" value="${entity.piIndiId}" style="width: 150px"> 
					<input id="ptTemplateCode" name="ptTemplateCode" type="hidden" value="${entity.ptTemplateCode}" style="width: 150px">
					<input id="piValidFlag" name="piValidFlag" type="hidden" value="${entity.piValidFlag}" style="width: 150px">
					<input id="piCheckState" name="piCheckState" type="hidden" value="${entity.piCheckState}" style="width: 150px">
					<input id="piCtime" class="easyui-datetimebox" name="piCtime" type="hidden" value="${entity.piCtime}" data-options="showSeconds:true">
					<input id="piCuser" name="piCuser" type="hidden" value="${entity.piCuser}" style="width: 150px">
					<input id="piUtime" name="piUtime" type="hidden" value="${entity.piUtime}" style="width: 150px">
					<input id="piUuser" name="piUuser" type="hidden" value="${entity.piUuser}" style="width: 150px"></td>
				</tr>
				<tr>
					<td>个人购买:</td>
					<td>
					    <input type="radio" name="ptIndiBuyFlag" value="1" checked/>是
					    <input type="radio" name="ptIndiBuyFlag" value="0" />否
					</td>
					<td style="padding-left:52px;">机构购买:</td>
					<td>
					    <input type="radio" name="ptInstBuyFlag" value="1" checked/>是
					    <input type="radio" name="ptInstBuyFlag" value="0" />否
					</td>
				</tr>
			</table>
	    </div>
	</div>
			<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top:10px">
				<div title = "个人参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>首次购买最低金额（元）:</td>
							<td><input id="piIndiMinBidsAmt" name="piIndiMinBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinBidsAmt}" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">首次购买级差金额（元）:</td>
							<td><input id="piIndiBidsDiffAmt" name="piIndiBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiBidsDiffAmt}" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>首次购买最高金额（元）:</td>
							<td><input id="piIndiMaxBidsAmt" name="piIndiMaxBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMaxBidsAmt}" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">最高账面份额（份）:</td>
							<td><input id="piIndiMaxVol" name="piIndiMaxVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMaxVol}" data-options="min:0,"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最低金额（元）:</td>
							<td><input id="piIndiMinAppBidsAmt" name="piIndiMinAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinAppBidsAmt}" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">追加购买级差金额（元）:</td>
							<td><input id="piIndiAppBidsDiffAmt" name="piIndiAppBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiAppBidsDiffAmt}" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最高金额（元）:</td>
							<td><input id="piIndiMaxAppBidsAmt" name="piIndiMaxAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMaxAppBidsAmt}" data-options="min:0,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>最低赎回份额（份）:</td> 
							<td><input id="piIndiMinRedeemVol" name="piIndiMinRedeemVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinRedeemVol}" data-options="min:0"/></td>
							<td style="padding-left:25px;">赎回级差（份）:</td>
							<td><input id="piIndiRedeemDiff" name="piIndiRedeemDiff" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiRedeemDiff}" data-options="min:1"/></td>
						</tr><tr style="padding-bottom: 5px;">
							<td>最低账面份额（份）:</td>
							<td><input id="piIndiMinVol" name="piIndiMinVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinVol}" data-options="min:0"/></td>
						</tr>
					</table>
				</div>
				</div>
				<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top:10px">
				<div title = "机构参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>首次购买最低金额（元）:</td>
							<td><input id="piInstMinBidsAmt" name="piInstMinBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinBidsAmt}" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">首次购买级差金额（元）:</td>
							<td><input id="piInstBidsDiffAmt" name="piInstBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstBidsDiffAmt}" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>首次购买最高金额（元）:</td>
							<td><input id="piInstMaxBidsAmt" name="piInstMaxBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMaxBidsAmt}" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">最高账面份额（份）:</td>
							<td><input id="piInstMaxVol" name="piInstMaxVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMaxVol}" data-options="min:0"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最低金额（元）:</td>
							<td><input id="piInstMinAppBidsAmt" name="piInstMinAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinAppBidsAmt}" data-options="min:0,precision:2"/></td>
							<td style="padding-left:25px;">追加购买级差金额（元）:</td>
							<td><input id="piInstAppBidsDiffAmt" name="piInstAppBidsDiffAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstAppBidsDiffAmt}" data-options="min:1,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>追加购买最高金额（元）:</td>
							<td><input id="piInstMaxAppBidsAmt" name="piInstMaxAppBidsAmt" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMaxAppBidsAmt}" data-options="min:0,precision:2"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>最低赎回份额（份）:</td>
							<td><input id="piInstMinRedeemVol" name="piInstMinRedeemVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinRedeemVol}" data-options="min:0"/></td>
							<td style="padding-left:25px;">赎回级差（份）:</td>
							<td><input id="piInstRedeemDiff" name="piInstRedeemDiff" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstRedeemDiff}" data-options="min:1"/></td>
						</tr><tr style="padding-bottom: 5px;">
							<td>最低账面份额（份）:</td>
							<td><input id="piInstMinVol" name="piInstMinVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piInstMinVol}" data-options="min:0"/></td>
						</tr>
						
					</table>
				</div>
			</div>
			
			<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top:10px">
				<div title = "模板参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<td>募集开始日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piIpoBeginDate" name="piIpoBeginDate" value="${entity.piIpoBeginDate}" required="required" editable="fasle"/></td>
							<td style="padding-left:25px;">募集结束日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piIpoEndDate" name="piIpoEndDate"  value="${entity.piIpoEndDate}" required="required" editable="fasle"/></td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>产品成立日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piProSetupDate" name="piProSetupDate" value="${entity.piProSetupDate}" required="required" editable="fasle"/></td>
							<td style="padding-left:25px;">产品到期日:<span style="color:red">*</span></td>
							<td><input style="width:140px;" class="easyui-datebox" id="piProEndDate" name="piProEndDate" value="${entity.piProEndDate}" required="required" editable="fasle"/></td>
						</tr>
						
						<tr>
						    <td>缴款方式:</td>
							<td><input id="ptPayType" name="ptPayType" class="easyui-combobox" value="${entity.ptPayType}" style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/payTypeGroup',
					            valueField:'DICTCODE',
					            textField:'DICTNAME'" />
							</td>
							
							<td style="padding-left:25px;">支付币种:</td>
							<td ><input id="piCurrency" name="piCurrency" class="easyui-combobox" value="${entity.piCurrency}" style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/currencyGroup',
					            valueField:'dictCode',
					            textField:'dictName'"/>
							</td>
				       </tr>
				       
				       <tr>
							<td>渠道代销费费率:</td>
							<td><input style="width:140px;" id="ptChannelRate" name="ptChannelRate" class="easyui-numberbox" type="number" value="${entity.ptChannelRate}" data-options="min:0,required:true,precision:2"/></td>
							<%-- <td style="padding-left:25px;">产品收益率:</td>
							<td><input style="width:140px;" id="ptProYield" name="ptProYield" class="easyui-numberbox" type="number" value="${entity.ptProYield}" style="width:100px;" value="0" data-options="min:0,required:true,precision:2"/></td> --%>
				       </tr>
				</table>
				
			<table style="background-color : #E0ECFF;width:520px;">
			    <!-- <tr>
				    <td>受益类别计算方式:</td>
					<td><input type="radio"  name="ptProCalculateType" value="1" />默认计算方式</td>
					<td><input type="radio"  name="ptProCalculateType" value="0" />自动计算方式</td>
				</tr> -->
				<tr>
				    <td>确认方式:</td>
					<td><input type="radio" name="ptProCfmWay" value="1" checked />T+1确认</td>
					<td><input type="radio" id = "tnRadio" name="ptProCfmWay" />T+N确认
						<input id="dayN" class="easyui-numberbox" type="number" style="width:50px;" data-options="min:2"/>
					</td>
			
					
				</tr>
				<tr>
					<td>是否含认购费:</td>
					<td><input type="radio" name="ptFeeFlag" value="1" checked/>是</td>
					<td><input type="radio" name="ptFeeFlag" value="0" />否</td>
				</tr>
				<tr>
					<td>收益计算方式:</td>
					<td><input type="radio" name="ptProYieldType" value="0" />算头不算尾</td>
					<td><input type="radio" name="ptProYieldType" value="1" checked/>算头又算尾</td>
				</tr>
				<tr>
					<td>年化计算天数:</td>
					<td><input type="radio" name="ptAnnuCalDays" value="365" />365</td>
					<td><input type="radio" name="ptAnnuCalDays" value="360" />360</td>
				</tr>
					</table>
				</div>
			</div>
		</form>
    </div>
</div>