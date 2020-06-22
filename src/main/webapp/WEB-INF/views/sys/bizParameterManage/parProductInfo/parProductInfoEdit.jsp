<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
    $(function(){
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
        $('#piChannelCode').combobox({
            onSelect: function (row) {
                var code=row.ID;
                $.ajax({
                    url:"${ctx}/channelInfo/selectChannelInfo",    //请求的url地址
                    dataType:"json",   //返回格式为json
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data:{"channelCode":code},    //参数值
                    type:"POST",   //请求方式
                    success :function(result){
                        if("1" != result.ciBatchNoOnOff){
                            //开关不开启情况不允许修改
                            $('#piBatchNumber').textbox('readonly',true);
                            $('#piBatchNumber').textbox('textbox').css('background','#e6ebee');
                            $('#piBatchNumber').textbox('textbox').css('color','#8b9098');
                        }else{
                            //允许修改
                            // $('#piBatchNumber').textbox('setValue', ' ');
                            $('#piBatchNumber').textbox('readonly',false);

                        }
                    },
                });
            }
        });

        $('#piTemplateCode').combobox({
            onChange: function (newValue, oldValue) {
                var piTemplateCode = $('#piTemplateCode').combobox('getValue')
                $.ajax({
                    url:"${ctx}/productTemplate/getParTemplateResult",    //请求的url地址
                    dataType:"json",   //返回格式为json
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data:{"ptTemplateCode":piTemplateCode},    //参数值
                    type:"POST",   //请求方式
                    success :function(data){
                        /*个人参数设置" */
                        $('#piIndiMaxBidsAmt').textbox('setValue', data.piIndiMaxBidsAmt);/*首次购买最低金额（元） */
                        $('#piIndiBidsDiffAmt').textbox('setValue', data.piIndiBidsDiffAmt);/*首次购买级差金额（元） */
                        $('#piIndiMinBidsAmt').textbox('setValue', data.piIndiMinBidsAmt);/*首次购买最高金额（元) */
                        $('#piIndiMaxVol').textbox('setValue', data.piIndiMaxVol);/*最高账面份额（份） */
                        $('#piIndiMinAppBidsAmt').textbox('setValue', data.piIndiMinAppBidsAmt);/*追加购买最低金额（元） */
                        $('#piIndiAppBidsDiffAmt').textbox('setValue', data.piIndiAppBidsDiffAmt);/*追加购买级差金额（元） */
                        $('#piIndiMaxAppBidsAmt').textbox('setValue', data.piIndiMaxAppBidsAmt);/*追加购买最高金额（元） */
                        $('#piIndiMaxAppBidsAmt').textbox('setValue', data.piIndiMaxAppBidsAmt);/*追加购买最高金额（元） */
                        $('#piIndiMinRedeemVol').textbox('setValue', data.piIndiMinRedeemVol);/*最低赎回份额: */
                        $('#piIndiRedeemDiff').textbox('setValue', data.piIndiRedeemDiff);/*最低赎回份额（份） */
                        $('#piIndiMinVol').textbox('setValue', data.piIndiMinVol);/*最低账面份额（份） */
                        /*机构参数设置" */
                        $('#piInstMinBidsAmt').textbox('setValue', data.piInstMinBidsAmt);/*首次购买最低金额（元） */
                        $('#piInstBidsDiffAmt').textbox('setValue', data.piInstBidsDiffAmt);/*首次购买级差金额（元） */
                        $('#piInstMaxBidsAmt').textbox('setValue', data.piInstMaxBidsAmt);/*首次购买最高金额（元) */
                        $('#piInstMaxVol').textbox('setValue', data.piInstMaxVol);/*最高账面份额（份） */
                        $('#piInstMinAppBidsAmt').textbox('setValue', data.piInstMinAppBidsAmt);/*追加购买最低金额（元） */
                        $('#piInstAppBidsDiffAmt').textbox('setValue', data.piInstAppBidsDiffAmt);/*追加购买级差金额（元） */
                        $('#piInstMaxAppBidsAmt').textbox('setValue', data.piInstMaxAppBidsAmt);/*追加购买最高金额（元） */
                        $('#piInstMinRedeemVol').textbox('setValue', data.piInstMinRedeemVol);/*追加购买最高金额（元） */
                        $('#piInstMinRedeemVol').textbox('setValue', data.piInstMinRedeemVol);/*最低赎回份额: */
                        $('#piInstRedeemDiff').textbox('setValue', data.piInstRedeemDiff);/*赎回级差（份）） */
                        $('#piInstMinVol').textbox('setValue', data.piInstMinVol);/*赎回级差（份）） */
                        /*产品参数设置" */
                        $('#piIpoBeginDate').textbox('setValue', data.piIpoBeginDate);/*募集开始日: */
                        $('#piIpoEndDate').textbox('setValue', data.piIpoEndDate);/*募集结束日: */
                        $('#piProSetupDate').textbox('setValue', data.piProSetupDate);/*项目成立日:*/
                        $('#piProEndDate').textbox('setValue', data.piProEndDate);/*项目到期日: */
                        $('#piChannelRate').textbox('setValue', data.ptChannelRate);/*渠道代销费费率: */
                        /*缴款方式: */
                        $('#piPayType').combobox({
                            onChange: function (newValue, oldValue) {
                                $('#piPayType').textbox('setValue', data.piPayType);
                            }
                        });
                        /*支付币种:*/
                        $('#piCurrencyType').combobox({
                            onChange: function (newValue, oldValue) {
                                $('#piCurrencyType').textbox('setValue', data.piCurrencyType);
                            }
                        });
                        /* 受益类别计算方式: */
                        /* var piProCalculateType = data.ptProCalculateType;
                          if (piProCalculateType == "0") {
                              $("input[name='piProCalculateType'][value='0']").attr("checked",true);
                          }else if(piProCalculateType == "1"){
                              $("input[name='piProCalculateType'][value='1']").attr("checked",true);
                          } */
                        /* 收益计算方式: */
                        var piProYieldType = data.ptProYieldType;
                        if (piProYieldType == "0") {
                            $("input[name='piProYieldType'][value='0']").attr("checked",true);
                        }else if(piProYieldType == "1"){
                            $("input[name='piProYieldType'][value='1']").attr("checked",true);
                        }
                        /*  是否含认购费: */
                        var piFeeFlag = data.ptFeeFlag;
                        if (piFeeFlag == "0") {
                            $("input[name='piFeeFlag'][value='0']").attr("checked",true);
                        }else if(piFeeFlag == "1"){
                            $("input[name='piFeeFlag'][value='1']").attr("checked",true);
                        }
                        /* 个人购买: */
                        var piIndiBuyFlag = data.ptIndiBuyFlag;
                        if (piIndiBuyFlag == "0") {
                            $("input[name='piIndiBuyFlag'][value='0']").attr("checked",true);
                        }else if(piIndiBuyFlag == "1"){
                            $("input[name='piIndiBuyFlag'][value='1']").attr("checked",true);
                        }
                        /* 机构购买: */
                        var piInstBuyFlag = data.ptInstBuyFlag;
                        if (piInstBuyFlag == "0") {
                            $("input[name='piInstBuyFlag'][value='0']").attr("checked",true);
                        }else if(piInstBuyFlag == "1"){
                            $("input[name='piInstBuyFlag'][value='1']").attr("checked",true);
                        }
                        /*  确认方式: */
                        var piProCfmWay = data.piProCfmWay;
                        if (piProCfmWay == "1") {
                            $("input[name='piProCfmWay'][value='1']").attr("checked",true);
                            //置灰输入N值得输入框
                            $("#dayN").textbox({disabled:true});
                        }else{
                            //赋值数据
                            $("#tnRadio").val(piProCfmWay);
                            $("input[name='piProCfmWay'][value="+piProCfmWay+"]").attr("checked",true);
                            $("#dayN").textbox({disabled:false});
                            $("#dayN").textbox({required:true});
                            $("#dayN").textbox({value:piProCfmWay});
                        }
                        /* 年化计算天数  */
                        var piAnnuCalDays = data.ptAnnuCalDays;
                        if (piAnnuCalDays == "365") {
                            $("input[name='piAnnuCalDays'][value='365']").attr("checked",true);
                        }else if(piAnnuCalDays == "360"){
                            $("input[name='piAnnuCalDays'][value='360']").attr("checked",true);
                        }
                    }
                });
            },
        });
        /* 收益计算方式: */
        var piProYieldType = ${entity.piProYieldType};//==null?"0":${entity.piProYieldType} ;
        if (piProYieldType == "0") {
            $("input[name='piProYieldType'][value='0']").attr("checked",true);
        }else if(piProYieldType == "1"){
            $("input[name='piProYieldType'][value='1']").attr("checked",true);
        }
        /*  是否含认购费: */
        var piFeeFlag = ${entity.piFeeFlag};
        if (piFeeFlag == "0") {
            $("input[name='piFeeFlag'][value='0']").attr("checked",true);
        }else if(piFeeFlag == "1"){
            $("input[name='piFeeFlag'][value='1']").attr("checked",true);
        }
        /* 个人购买: */
        var piIndiBuyFlag = ${entity.piIndiBuyFlag};
        if (piIndiBuyFlag == "0") {
            $("input[name='piIndiBuyFlag'][value='0']").attr("checked",true);
        }else if(piIndiBuyFlag == "1"){
            $("input[name='piIndiBuyFlag'][value='1']").attr("checked",true);
        }
        /* 机构购买: */
        var piInstBuyFlag = ${entity.piInstBuyFlag};
        if (piInstBuyFlag == "0") {
            $("input[name='piInstBuyFlag'][value='0']").attr("checked",true);
        }else if(piInstBuyFlag == "1"){
            $("input[name='piInstBuyFlag'][value='1']").attr("checked",true);
        }
        /*  确认方式: */
        var piProCfmWay = ${entity.piProCfmWay};
        if (piProCfmWay == "1") {
            $("input[name='piProCfmWay'][value='1']").attr("checked",true);
            //置灰输入N值得输入框
            $("#dayN").textbox({disabled:true});
        }else{
            //赋值数据
            $("#tnRadio").val(piProCfmWay);
            $("input[name='piProCfmWay'][value="+piProCfmWay+"]").attr("checked",true);
            $("#dayN").textbox({disabled:false});
            $("#dayN").textbox({required:true});
            $("#dayN").textbox({value:piProCfmWay});
        }
        /* 受益类别计算方式: */
        /* var piProCalculateType = ${entity.piProCalculateType};
       	        if (piProCalculateType == "0") {
       	          $("input[name='piProCalculateType'][value='0']").attr("checked",true); 
       	        }else if(piProCalculateType == "1"){
       	          $("input[name='piProCalculateType'][value='1']").attr("checked",true); 
       	    } */
        /* 年化计算天数  */
        var piAnnuCalDays = ${entity.piAnnuCalDays};
        if (piAnnuCalDays == "365") {
            $("input[name='piAnnuCalDays'][value='365']").attr("checked",true);
        }else if(piAnnuCalDays == "360"){
            $("input[name='piAnnuCalDays'][value='360']").attr("checked",true);
        }


        //获取个人购买标志
        var piIndiBuyFlag = $('input:radio[name="piIndiBuyFlag"]:checked').val();
        //允许个人购买 个人参数设置全部必填
        if(piIndiBuyFlag=='1'){
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
        //获取机构购买标志
        var piInstBuyFlag = $('input:radio[name="piInstBuyFlag"]:checked').val();
        //允许机构购买 机构参数设置全部必填
        if(piInstBuyFlag=='1'){
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

    $('#editProduct').form({
        url : '${ctx}/parProductInfo/editSubmit',
        onSubmit : function() {
            progressLoad();
            //获取个人购买标志
            var piIndiBuyFlag = $('input:radio[name="piIndiBuyFlag"]:checked').val();
          //允许个人购买 个人参数设置全部必填
            if(piIndiBuyFlag=='1'){
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
            //获取机构购买标志
            var piInstBuyFlag = $('input:radio[name="piInstBuyFlag"]:checked').val();
            //允许机构购买 机构参数设置全部必填
            if(piInstBuyFlag=='1'){
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
            var piChannelCode = $("#piChannelCode").combobox("getValue");
            var piChannelProductCode = $("#piChannelProductCode").textbox("getValue");
            var piChannelProductName = $("#piChannelProductName").textbox("getValue");
            var piIpoBeginDate = $("#piIpoBeginDate").datebox("getValue");
            var piIpoEndDate = $("#piIpoEndDate").datebox("getValue");
            var piProSetupDate = $("#piProSetupDate").datebox("getValue");
            var piProEndDate = $("#piProEndDate").datebox("getValue");
            var piBatchNumber = $("#piBatchNumber").numberbox("getValue");

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
            if(piIpoBeginDate=='' ||piIpoEndDate=='' || piProSetupDate=='' || piProEndDate==''){
                $.messager.alert({
                    title : '提示',
                    msg : '募集开始日,募集结束日,产品成立日,产品到期日必填',
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

            if(piBatchNumber =''){
                $.messager.alert({
                    title : '提示',
                    msg : '批次号不能为空',
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

    //个人购买标志触发事件
    $('input:radio[name="piIndiBuyFlag"]').click(function(){
        var piIndiBuyFlag = $('input:radio[name="piIndiBuyFlag"]:checked').val();
        //允许个人购买 个人参数设置全部必填
        if(piIndiBuyFlag=='1'){
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
    $('input:radio[name="piInstBuyFlag"]').click(function(){
        var piInstBuyFlag = $('input:radio[name="piInstBuyFlag"]:checked').val();
        //允许机构购买 机构参数设置全部必填
        if(piInstBuyFlag=='1'){
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

    function retSetTime(){
        var piIpoBeginDate = $("#piProEndDate").datebox("getText");
    }

    //设置单选框改变事件
    $('input[type=radio][name=piProCfmWay]').change(function () {
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
                $("input[name='piProCfmWay'][value="+n+"]").attr("checked",true);
            }
        });
    });
</script>

<div id ="fundModelPanel" style ="background-color : #E0ECFF;overflow:auto">
	<div class="gridDiv" data-options="region:'center',border:false">
		<form id="editProduct" method="post" >
			<div class="easyui-accordion" data-options="multiple:true" style="width:530px;background-color : #E0ECFF;border:none;margin-top: 10px">
				<div title = "产品定义">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr style="padding-bottom: 5px;">
							<%-- <td>模板类型:<span style="color:red">*</span></td>
                            <td><input id="piTemplateCode" name="piTemplateCode" class="easyui-combobox" style = "width:200px;" data-options="
                            url:'${ctx}/combobox/queryTempalteComBox',
                            valueField:'CODE',
                            textField:'NAME'" required="required" value="${entity.piTemplateCode}"/>
                            </td> --%>
							<td>代销渠道:<span style="color:red">*</span></td>
							<td><input id="piChannelCode" name="piChannelCode" class="easyui-combobox" style = "width:200px;" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" required="required" value = "${entity.piChannelCode }" editable="false" readonly="readonly"/>
							</td>
							<td>产品类型:<span style="color:red">*</span></td>
							<td><input id="piProductType" name="piProductType" value="${entity.piProductType}" class="easyui-combobox" style = "width:160px;height:20px;" data-options="
					url:' ${ctx}/sysDict/productTypeGroup',
					valueField:'dictCode',
					textField:'dictName'" required="required"/>
							</td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>产品代码:<span style="color:red">*</span></td>
							<td><input id="piChannelProductCode" name="piChannelProductCode" class="easyui-textbox" type="text" value="${entity.piChannelProductCode}" style="width: 150px" required="required" data-options="validType:'length[1,17]',">
								<input id="piId" name="piId" type="hidden" value="${entity.piId}" style="width: 150px">
								<input id="piCuser" name="piCuser" type="hidden" value="${entity.piCuser}">
								<input id="piCtime" class="easyui-datetimebox" name="piCtime" type="hidden" value="${entity.piCtime}" data-options="showSeconds:true">
								<input id="piUuser" name="piUuser" type="hidden" value="${entity.piUuser}">
								<input id="piUtime" name="piUtime" type="hidden" value="${entity.piUtime}">
								<input id="piValidFlag" name="piValidFlag" type="hidden" value="${entity.piValidFlag}">
								<input id="piCheckState" name="piCheckState" type="hidden" value="${entity.piCheckState}"></td>
							<td>产品名称:<span style="color:red">*</span></td>
							<td><input id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" type="text" value="${entity.piChannelProductName}" style="width: 150px" required="required" data-options="validType:'length[1,200]'"></td>
						</tr>
						<%-- <tr style="padding-bottom: 5 px;">
                            <td>代销渠道:<span style="color:red">*</span></td>
                            <td><input id="piChannelCode" name="piChannelCode" class="easyui-combobox" style = "width:200px;" data-options="
                            url:'${ctx}/combobox/queryChannelInfo',
                            valueField:'ID',
                            textField:'NAME'" required="required" value = "${entity.piChannelCode }" editable="false"/>
                            </td>
                        </tr> --%>
						<tr>
							<td>个人购买:</td>
							<td>
								<input type="radio" name="piIndiBuyFlag" value="1" />是
								<input type="radio" name="piIndiBuyFlag" value="0" />否
							</td>
							<td>机构购买:</td>
							<td><input type="radio" name="piInstBuyFlag" value="1" />是
								<input type="radio" name="piInstBuyFlag" value="0" />否
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top: 10px">
				<div title = "个人参数设置">
					<table style="background-color : #E0ECFF;">
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
							<td><input id="piIndiRedeemDiff" name="piIndiRedeemDiff"  class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiRedeemDiff}" data-options="min:1"/></td>
						</tr><tr style="padding-bottom: 5px;">
						<td>最低账面份额（份）:</td>
						<td><input id="piIndiMinVol" name="piIndiMinVol" class="easyui-numberbox" type="number" style="width:100px;" value="${entity.piIndiMinVol}" data-options="min:0"/></td>
					</tr>
					</table>
				</div>
			</div>
			<div class="easyui-accordion" data-options="multiple:true" style="width:520px;background-color : #E0ECFF;border:none;margin-top: 10px">
				<div title = "机构参数设置">
					<table style="background-color : #E0ECFF;">
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
							<td>最低赎回份额:</td>
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
				<div title = "产品参数设置">
					<table style="background-color : #E0ECFF;width:520px;">
						<tr>
							<td>
								募集开始日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox" id="piIpoBeginDate" name="piIpoBeginDate" value="${entity.piIpoBeginDate}" required="required" editable="fasle"/>
								                                        <input id="oriIpoBeginDate" name="oriIpoBeginDate" type="hidden" value="${entity.piIpoBeginDate}">
							</td>
							<td>
								募集结束日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox" id="piIpoEndDate" name="piIpoEndDate" value="${entity.piIpoEndDate}" required="required" editable="fasle"/>
							</td>
						</tr>
						<tr>
							<td>
								产品成立日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox" id="piProSetupDate" name="piProSetupDate" value="${entity.piProSetupDate}" required="required" editable="fasle"/>
							</td>
							<td>
								产品到期日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox" id="piProEndDate" name="piProEndDate" value="${entity.piProEndDate}" required="required"   data-options="onSelect:retSetTime" editable="fasle"/>
							</td>
						</tr>

						<tr>
							<td>
								<span>最高募集金额:</span><span style="color:red">*</span><input id="piMaxAmountRaised" name="piMaxAmountRaised" class="easyui-numberbox" type="number" value="${entity.piMaxAmountRaised}"  data-options="validType:'length[1,14]',min:0,required:true,precision:2">
							</td>
							<td>
								<span>基金总份额:</span><span style="color:red">*</span><input id="piProductTotalShare" name="piProductTotalShare" class="easyui-numberbox" type="number" value="${entity.piProductTotalShare}" data-options="min:0,required:true,precision:2">
							</td>
						</tr>

						<tr>
							<td>缴款方式:
								<input id="piPayType" name="piPayType" class="easyui-combobox" value="${entity.piPayType}" style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/payTypeGroup',
					            valueField:'DICTCODE',
					            textField:'DICTNAME'" />
							</td>
							<!-- <td>支付币种:</td> -->
							<td>支付币种:<input id="piCurrencyType" name="piCurrencyType" class="easyui-combobox" value="${entity.piCurrencyType}" style = "width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/currencyGroup',
					            valueField:'dictCode',
					            textField:'dictName'"/>
							</td>
						</tr>

						<tr>
							<td>渠道代销费费率:<input class="easyui-numberbox" style="width:140px;height:20px;" id="piChannelRate" name="piChannelRate" type="number"  value="${entity.piChannelRate}" data-options="min:0,required:true,precision:2"/></td>
							<td>批次号:<input class="easyui-numberbox" style="width:140px;height:20px;" id="piBatchNumber" name="piBatchNumber" type="number"  value="${entity.piBatchNumber}" data-options="required:true"/></td>

						</tr>

					</table>
					<table style="background-color : #E0ECFF;width:520px;">
						<!--  <tr>
                             <td>受益类别计算方式:</td>
                             <td><input type="radio"  name="piProCalculateType" value="1" />默认计算方式</td>
                             <td><input type="radio"  name="piProCalculateType" value="0" />自动计算方式</td>
                         </tr> -->
						<tr >
							<td>收益计算方式:</td>
							<td><input type="radio" name="piProYieldType" value="0" />算头不算尾</td>
							<td><input type="radio" name="piProYieldType" value="1"/>算头又算尾</td>
						</tr>
						<tr style="padding-bottom: 5 px;">
							<td>是否含认购费:</td>
							<td><input type="radio" name="piFeeFlag" value="1" />是</td>
							<td><input type="radio" name="piFeeFlag" value="0" />否</td>
						</tr>
						<tr>
							<td>确认方式:</td>
							<td><input type="radio" name="piProCfmWay" value="1"  />T+1确认</td>
							<td><input type="radio" name="piProCfmWay" id = "tnRadio" />T+N确认
								<input id="dayN" class="easyui-numberbox" type="number" style="width:50px;" data-options="min:2"/>
							</td>
						</tr>
						<tr>
							<td>年化计算天数:</td>
							<td><input type="radio" name="piAnnuCalDays" value="365" />365</td>
							<td><input type="radio" name="piAnnuCalDays" value="360" />360</td>
						</tr>
						<c:if test="${not empty subChannelInfo}">
							<tr>
								<td>子渠道信息：</td>
								</td><td rowspan="10" colspan="10"><input name="piSubChannelCode" id="subChannelCombobox" class="easyui-combobox" value="${subChannelInfo.subChannelCode}" style = "width:350px;" data-options="
						url:'${ctx}/channelInfo/selectSubChannelList?channelCode=${subChannelInfo.channelCode}',
						valueField:'AGENCYNO',
						textField:'AGENCYNAME',
						panelHeight: 'auto'" />
							</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</form>
	</div>
</div>