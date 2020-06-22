<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/global.jsp" %>
<script type="text/javascript">
    $(function () {
        /* 收益计算方式: */
        var piProYieldType = ${entity.piProYieldType};
        if (piProYieldType == "0") {
            $("input[name='piProYieldType'][value='0']").attr("checked", true);
        } else if (piProYieldType == "1") {
            $("input[name='piProYieldType'][value='1']").attr("checked", true);
        }

        /*  是否含认购费: */
        var piFeeFlag = ${entity.piFeeFlag};
        if (piFeeFlag == "0") {
            $("input[name='piFeeFlag'][value='0']").attr("checked", true);
        } else if (piFeeFlag == "1") {
            $("input[name='piFeeFlag'][value='1']").attr("checked", true);
        }
        /* 个人购买: */
        var piIndiBuyFlag = ${entity.piIndiBuyFlag};
        if (piIndiBuyFlag == "0") {
            $("input[name='piIndiBuyFlag'][value='0']").attr("checked", true);
        } else if (piIndiBuyFlag == "1") {
            $("input[name='piIndiBuyFlag'][value='1']").attr("checked", true);
        }
        /* 机构购买: */
        var piInstBuyFlag = ${entity.piInstBuyFlag};
        if (piInstBuyFlag == "0") {
            $("input[name='piInstBuyFlag'][value='0']").attr("checked", true);
        } else if (piInstBuyFlag == "1") {
            $("input[name='piInstBuyFlag'][value='1']").attr("checked", true);
        }
        /*  确认方式: */
        var piProCfmWay = ${entity.piProCfmWay};
        if (piProCfmWay == "1") {
            $("input[name='piProCfmWay'][value='1']").attr("checked", true);
            //置灰输入N值得输入框
            $("#dayN").textbox({disabled: true});
        } else {
            //赋值数据
            $("#tnRadio").val(piProCfmWay);
            $("input[name='piProCfmWay'][value=" + piProCfmWay + "]").attr("checked", true);
            $("#dayN").textbox({value: piProCfmWay});
            $("#dayN").textbox({disabled: true});
        }
        /* 受益类别计算方式: */
        /* var piProCalculateType =
        ${entity.piProCalculateType};
       	        if (piProCalculateType == "0") {
       	          $("input[name='piProCalculateType'][value='0']").attr("checked",true); 
       	        }else if(piProCalculateType == "1"){
       	          $("input[name='piProCalculateType'][value='1']").attr("checked",true); 
       	    } */
        /* 年化计算天数  */
        var piAnnuCalDays = ${entity.piAnnuCalDays};
        if (piAnnuCalDays == "365") {
            $("input[name='piAnnuCalDays'][value='365']").attr("checked", true);
        } else if (piAnnuCalDays == "360") {
            $("input[name='piAnnuCalDays'][value='360']").attr("checked", true);
        }
    });

    /* 子渠道赋值 */
    var subChannelCodes = '${entity.piSubChannelCode}'.split(",");
    //获取复选框
    var piSubChannelCode = $("input[name='piSubChannelCode']");
    //循环acctTypes与复选框，当acctTypes的值与复选框中value一致，状态选中
    for (var i = 0; i < subChannelCodes.length; i++) {
        for (var j = 0; j < piSubChannelCode.length; j++) {
            if (subChannelCodes[i] == piSubChannelCode[j].value)
                piSubChannelCode[j].checked = true;
        }
    }

    function openIconAllHtml() {
        window.open("${staticPath}/icon/group/icon-all.html");
    }
</script>

<div id="fundModelPanel" style="background-color : #E0ECFF;overflow:auto">
    <div class="gridDiv" data-options="region:'center',border:false">
        <form id="editProduct" method="post">
            <table>
                <tr>
                    <td>代销渠道:<span style="color:red">*</span></td>
                    <td><input id="piChannelCode" name="piChannelCode" class="easyui-combobox" style="width:200px;"
                               data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'" required="required" disabled="disabled" value="${entity.piChannelCode }"/>
                    </td>
                    <td>产品类型:<span style="color:red">*</span></td>
                    <td><input id="piProductType" name="piProductType" value="${entity.piProductType}"
                               class="easyui-combobox" style="width:160px;height:20px;" data-options="
					url:' ${ctx}/sysDict/productTypeGroup',
					valueField:'dictCode',
					textField:'dictName'" required="required" disabled="disabled"/>
                    </td>
                </tr>
                <tr style="padding-bottom: 5px;">
                    <td>产品代码:<span style="color:red">*</span></td>
                    <td><input id="piChannelProductCode" name="piChannelProductCode" class="easyui-textbox" type="text"
                               value="${entity.piChannelProductCode}" style="width: 150px" required="required"
                               disabled="disabled"/>
                        <input id="piId" name="piId" type="hidden" value="${entity.piId}" style="width: 150px">
                        <input id="piCuser" name="piCuser" type="hidden" value="${entity.piCuser}">
                        <input id="piCtime" name="piCtime" type="hidden" value="${entity.piCtime}">
                        <input id="piUuser" name="piUuser" type="hidden" value="${entity.piUuser}">
                        <input id="piUtime" name="piUtime" type="hidden" value="${entity.piUtime}">
                        <input id="piValidFlag" name="piValidFlag" type="hidden" value="${entity.piValidFlag}">
                        <input id="piCheckState" name="piCheckState" type="hidden" value="${entity.piCheckState}"></td>
                    <td>产品名称:<span style="color:red">*</span></td>
                    <td><input id="piChannelProductName" name="piChannelProductName" class="easyui-textbox" type="text"
                               value="${entity.piChannelProductName}" style="width: 150px" required="required"
                               disabled="disabled"></td>
                </tr>
                <tr style="padding-bottom: 5px;">

                </tr>
                <tr>
                    <td>个人购买:</td>
                    <td>
                        <input type="radio" name="piIndiBuyFlag" value="1" disabled="disabled"/>是
                        <input type="radio" name="piIndiBuyFlag" value="0" disabled="disabled"/>否
                    </td>
                    <td>机构购买:</td>
                    <td>
                        <input type="radio" name="piInstBuyFlag" value="1" disabled="disabled"/>是
                        <input type="radio" name="piInstBuyFlag" value="0" disabled="disabled"/>否
                    </td>
                </tr>
            </table>

            <div class="easyui-accordion" data-options="multiple:true"
                 style="width:520px;background-color : #E0ECFF;border:none;">
                <div title="个人">
                    <table style="background-color : #E0ECFF;">
                        <tr style="padding-bottom: 5px;">
                            <td>首次购买最低金额（元）:</td>
                            <td><input id="piIndiMinBidsAmt" name="piIndiMinBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiMinBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                            <td style="padding-left:25px;">首次购买级差金额（元）:</td>
                            <td><input id="piIndiBidsDiffAmt" name="piIndiBidsDiffAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiBidsDiffAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>首次购买最高金额（元）:</td>
                            <td><input id="piIndiMaxBidsAmt" name="piIndiMaxBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiMaxBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                            <td style="padding-left:25px;">最高账面份额（份）:</td>
                            <td><input id="piIndiMaxVol" name="piIndiMaxVol" class="easyui-numberbox" type="number"
                                       style="width:100px;" value="${entity.piIndiMaxVol}" disabled="disabled"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>追加购买最低金额（元）:</td>
                            <td><input id="piIndiMinAppBidsAmt" name="piIndiMinAppBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiMinAppBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                            <td style="padding-left:25px;">追加购买级差金额（元）:</td>
                            <td><input id="piIndiAppBidsDiffAmt" name="piIndiAppBidsDiffAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiAppBidsDiffAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>追加购买最高金额（元）:</td>
                            <td><input id="piIndiMaxAppBidsAmt" name="piIndiMaxAppBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiMaxAppBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>最低赎回份额:</td>
                            <td><input id="piIndiMinRedeemVol" name="piIndiMinRedeemVol" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiMinRedeemVol}"
                                       disabled="disabled"/></td>
                            <td style="padding-left:25px;">赎回级差（份）:</td>
                            <td><input id="piIndiRedeemDiff" name="piIndiRedeemDiff" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piIndiRedeemDiff}"
                                       disabled="disabled"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>最低账面份额（份）:</td>
                            <td><input id="piIndiMinVol" name="piIndiMinVol" class="easyui-numberbox" type="number"
                                       style="width:100px;" value="${entity.piIndiMinVol}" disabled="disabled"/></td>
                        </tr>
                    </table>
                </div>
                <div title="机构">
                    <table style="background-color : #E0ECFF;">
                        <tr style="padding-bottom: 5px;">
                            <td>首次购买最低金额（元）:</td>
                            <td><input id="piInstMinBidsAmt" name="piInstMinBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstMinBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                            <td style="padding-left:25px;">首次购买级差金额（元）:</td>
                            <td><input id="piInstBidsDiffAmt" name="piInstBidsDiffAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstBidsDiffAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>首次购买最高金额（元）:</td>
                            <td><input id="piInstMaxBidsAmt" name="piInstMaxBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstMaxBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                            <td style="padding-left:25px;">最高账面份额（份）:</td>
                            <td><input id="piInstMaxVol" name="piInstMaxVol" class="easyui-numberbox" type="number"
                                       style="width:100px;" value="${entity.piInstMaxVol}" disabled="disabled"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>追加购买最低金额（元）:</td>
                            <td><input id="piInstMinAppBidsAmt" name="piInstMinAppBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstMinAppBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                            <td style="padding-left:25px;">追加购买级差金额（元）:</td>
                            <td><input id="piInstAppBidsDiffAmt" name="piInstAppBidsDiffAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstAppBidsDiffAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>追加购买最高金额（元）:</td>
                            <td><input id="piInstMaxAppBidsAmt" name="piInstMaxAppBidsAmt" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstMaxAppBidsAmt}"
                                       disabled="disabled" data-options="min:0,required:true,precision:2"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>最低赎回份额:</td>
                            <td><input id="piInstMinRedeemVol" name="piInstMinRedeemVol" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstMinRedeemVol}"
                                       disabled="disabled"/></td>
                            <td style="padding-left:25px;">赎回级差（份）:</td>
                            <td><input id="piInstRedeemDiff" name="piInstRedeemDiff" class="easyui-numberbox"
                                       type="number" style="width:100px;" value="${entity.piInstRedeemDiff}"
                                       disabled="disabled"/></td>
                        </tr>
                        <tr style="padding-bottom: 5px;">
                            <td>最低账面份额（份）:</td>
                            <td><input id="piInstMinVol" name="piInstMinVol" class="easyui-numberbox" type="number"
                                       style="width:100px;" value="${entity.piInstMinVol}" disabled="disabled"/></td>
                        </tr>

                    </table>
                </div>
            </div>
            <div class="easyui-accordion" data-options="multiple:true"
                 style="width:520px;background-color : #E0ECFF;border:none;margin-top:10px">
                <div title="产品参数设置">
                    <table style="background-color : #E0ECFF;width:520px;">
                        <tr>
                            <td>
                                募集开始日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox"
                                                                             id="piIpoBeginDate" name="piIpoBeginDate"
                                                                             value="${entity.piIpoBeginDate}"
                                                                             required="required" editable="fasle"
                                                                             disabled="disabled"/>
                            </td>
                            <td>
                                募集结束日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox"
                                                                             id="piIpoEndDate" name="piIpoEndDate"
                                                                             value="${entity.piIpoEndDate}"
                                                                             required="required" editable="fasle"
                                                                             disabled="disabled"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                产品成立日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox"
                                                                             id="piProSetupDate" name="piProSetupDate"
                                                                             value="${entity.piProSetupDate}"
                                                                             required="required" editable="fasle"
                                                                             disabled="disabled"/>
                            </td>
                            <td>
                                产品到期日:<span style="color:red">*</span><input style="width:153px;" class="easyui-datebox"
                                                                             id="piProEndDate" name="piProEndDate"
                                                                             value="${entity.piProEndDate}"
                                                                             required="required" editable="fasle"
                                                                             disabled="disabled"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <span>最高募集金额:</span><span style="color:red">*</span><input id="piMaxAmountRaised"
                                                                                           name="piMaxAmountRaised"
                                                                                           class="easyui-numberbox"
                                                                                           type="number"
                                                                                           value="${entity.piMaxAmountRaised}"
                                                                                           disabled="disabled"
                                                                                           data-options="min:0,required:true,precision:2">
                            </td>
                            <td>
                                <span>基金总份额:</span><span style="color:red">*</span><input id="piProductTotalShare"
                                                                                          name="piProductTotalShare"
                                                                                          class="easyui-numberbox"
                                                                                          type="number"
                                                                                          value="${entity.piProductTotalShare}"
                                                                                          disabled="disabled"
                                                                                          data-options="min:0,required:true,precision:2">
                            </td>
                        </tr>

                        <tr>
                            <td>缴款方式:
                                <input id="piPayType" name="piPayType" class="easyui-combobox"
                                       value="${entity.piPayType}" style="width:140px;height:20px;" data-options="
					            url:' ${ctx}/sysDict/payTypeGroup',
					            valueField:'DICTCODE',
					            textField:'DICTNAME'" disabled="disabled"/>
                            </td>
                            <!-- <td>支付币种:</td> -->
                            <td>支付币种:<input id="piCurrencyType" name="piCurrencyType" class="easyui-combobox"
                                            value="${entity.piCurrencyType}" style="width:140px;height:20px;"
                                            data-options="
					            url:' ${ctx}/sysDict/currencyGroup',
					            valueField:'dictCode',
					            textField:'dictName'" disabled="disabled"/>
                            </td>
                        </tr>

                        <tr>
                            <td>渠道代销费费率:<input class="easyui-numberbox" style="width:153px;" id="piChannelRate"
                                               name="piChannelRate" type="number" value="${entity.piChannelRate}"
                                               data-options="min:0,required:true,precision:2" disabled="disabled"/></td>
                            <td>批次号:<input class="easyui-numberbox" style="width:width:140px;height:20px;"
                                           id="piBatchNumber" name="piBatchNumber" type="number"
                                           value="${entity.piBatchNumber}" disabled="disabled"/></td>

                        </tr>

                    </table>
                    <table style="background-color : #E0ECFF;width:520px;">
                        <!--  <tr>
                             <td>受益类别计算方式:</td>
                             <td><input type="radio"  name="piProCalculateType" value="1" disabled="disabled"/>默认计算方式</td>
                             <td><input type="radio"  name="piProCalculateType" value="0" disabled="disabled"/>自动计算方式</td>
                         </tr> -->
                        <tr>
                            <td>收益计算方式:</td>
                            <td><input type="radio" name="piProYieldType" value="0" disabled="disabled"/>算头不算尾</td>
                            <td><input type="radio" name="piProYieldType" value="1" disabled="disabled"/>算头又算尾</td>
                        </tr>
                        <tr>
                            <td>是否含认购费:</td>
                            <td><input type="radio" name="piFeeFlag" value="1" disabled="disabled"/>是</td>
                            <td><input type="radio" name="piFeeFlag" value="0" disabled="disabled"/>否</td>
                        </tr>
                        <tr>
                            <td>确认方式:</td>
                            <td><input type="radio" name="piProCfmWay" value="1" disabled="disabled"/>T+1确认</td>
                            <td><input type="radio" name="piProCfmWay" id="tnRadio"/>T+N确认
                                <input id="dayN" class="easyui-numberbox" type="number" style="width:50px;"
                                       data-options="min:2"/>
                            </td>
                        </tr>
                        <tr>
                            <td>年化计算天数:</td>
                            <td><input type="radio" name="piAnnuCalDays" value="365" disabled="disabled"/>365</td>
                            <td><input type="radio" name="piAnnuCalDays" value="360" disabled="disabled"/>360</td>
                        </tr>
                        <tr>
                            <c:if test="${not empty subChannelInfo}">
                        <tr>
                            <td>子渠道信息：</td>
                            </td><td rowspan="10" colspan="10"><input name="piSubChannelCode" disabled="disabled" class="easyui-combobox" value="${subChannelInfo.subChannelCode}" style = "width:350px;" data-options="
						url:'${ctx}/channelInfo/selectSubChannelList?channelCode=${subChannelInfo.channelCode}',
						valueField:'AGENCYNO',
						textField:'AGENCYNAME'" />
                        </td>
                        </tr>
                        </c:if>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>