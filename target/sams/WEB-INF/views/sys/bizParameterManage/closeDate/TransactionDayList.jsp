<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/calendar.css" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
        <title>交易日设置页面</title>
    </head>
    <script type="text/javascript">
        //保存展示在前台的天数list
        var chooseList = new Array();
        $(function() {
            /*获取当前需要回显的日期数据信息*/
            /*transDateList = '${dataList}';
            $.each(JSON.parse(transDateList),function(index,value){
                chooseList.push(value);
            });
            /!*获取当前需要回显的月份信息数据*!/
            monInfoList = '${monInfoList}';
            makeMonList(monInfoList);*/
            loginUsername = '${userName}';

            function makeMonList(val){
                $.each(JSON.parse(val),function(index,value){
                    var checkStatu = value.checkStatus;
                    var updateUser = value.updateUser;
                    var action = value.updateAction;
                    var index = value.monIndex;
                    // chooseList.push(value);
                    //判断后台数据

                    if (checkStatu == "00") {
                        $("#check-status"+index).text('未复核');
                        $("#check-status"+index).css('color','#6699ff');
                    }
                    if (checkStatu == "01") {
                        $("#check-status"+index).text('已复核');
                        $("#check-status"+index).css('color','#00CD00');
                    }
                    if (checkStatu == "02") {
                        $("#check-status"+index).text('未设置');
                        $("#check-status"+index).css('color','#FF0000');
                    }
                    // $("#check-status"+index).text(checkStatu);
                    $("#update-name"+index).text(updateUser);
                    $("#update-step"+index).text(getAction(action));

                });
            }

            function getAction(value){
                if (value == "00") {
                    return "删除";
                }
                if (value == "01") {
                    return "新增";
                }
                if (value == "02") {
                    return "修改";
                }
                if (value == "03") {
                    return "复核";
                }
                if (value == "04") {
                    return "停用";
                }
                if (value == "05") {
                    return "启用";
                }
                if (value == "无") {
                    return "无";
                }
            }
            function onchangeCombox(marketVal,yearVal){
                $('.easyui-calendar').calendar({
                    year: yearVal
                })
                $.getJSON('${ctx}/transactionDay/getMonInfoList?yearVal='+yearVal+'&marketVal='+marketVal+'&type=1', null, function (result) {
                    for (var i = 0; i < result.length; i++) {
                        var checkStatu = result[i].checkStatus;
                        var updateUser = result[i].updateUser;
                        var action = result[i].updateAction;
                        var index = result[i].monIndex;
                        //判断后台数据
                        if (checkStatu == "00") {
                            $("#check-status"+index).text('未复核');
                            $("#check-status"+index).css('color','#6699ff');
                        }
                        if (checkStatu == "01") {
                            $("#check-status"+index).text('已复核');
                            $("#check-status"+index).css('color','#00CD00');
                        }
                        if (checkStatu == "02") {
                            $("#check-status"+index).text('未设置');
                            $("#check-status"+index).css('color','#FF0000');
                        }
                        // $("#check-status"+index).text(checkStatu);
                        $("#update-name"+index).text(updateUser);
                        $("#update-step"+index).text(getAction(action));
                    }
                })
                //获取后台数据信息
                $.getJSON('${ctx}/transactionDay/getDateList?yearVal='+yearVal+'&marketVal='+marketVal+'&type=1', null, function (result) {
                    //重新加载一次
                    chooseList = new Array()
                    chooseList = result
                    var mons = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
                    for (var i in mons) {
                        var mon = mons[i];
                        //初始化渲染
                        initChenkDate(mon);
                    }
                })
            }

            /*查询按钮点击事件*/
            $("#serchTransactionDay").click(function () {
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                var yearVal = $('#filter_cdYear').combobox("getValue");
                onchangeCombox(marketVal,yearVal);
            })

            /*下拉框默认选中  首次进入该页面看到的数据信息  市场 = 001 年度 = 当年*/
            $('#filter_cdMarketCode').combobox({
                onLoadSuccess:function(){
                    $('#filter_cdMarketCode').combobox("setValue","001");
                },
                onSelect: function(data) {
                    // var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                    var yearVal = new Date().getFullYear();
                    if($('#filter_cdYear').combobox.length > 0){
                        yearVal = $('#filter_cdYear').combobox("getValue");
                    }
                    onchangeCombox(data.marketVal,yearVal);
                }
            });
            var theDate = new Date();
            var theYear = theDate.getFullYear();
            $('#filter_cdYear').combobox({
                onLoadSuccess:function(){
                    $('#filter_cdYear').combobox("setValue",theYear);
                },
                onSelect: function(data) {
                    var marketVal = "001";
                    if($('#filter_cdYear').combobox.length > 0){
                        marketVal = $('#filter_cdMarketCode').combobox("getValue");
                    }
                    onchangeCombox(marketVal,data.yearVal);
                }
            });
            /*日历插件回显后台选中日期操作*/
            //公共调用方法
            var mons = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
            for (var i in mons) {
                var mon = mons[i];
                //初始化渲染
                initChenkDate(mon);
                //点击触发选择事件
                toChooseDate(mon);
            }
            //初始化渲染
            function initChenkDate(mon){
                //遍历所有日期
                $('#dateChoose' + mon).calendar({
                    formatter: function(date) {
                        //遍历当前月所有的时间
                        var y1 = date.getFullYear();
                        var m1 = date.getMonth() + 1;
                        var d1 = date.getDate();
                        //遍历前台需要展示的选中日期数据
                        //判断当前list中是否包含该日期
                        var yy = y1 + "-" + m1 + "-" + d1;
                        var a = chooseList.indexOf(yy);
                        if(m1 == mon){
                            if (a == -1) {
                                return yy.split("-")[2];
                            } else {
                                return '<div style="background:#009900; color: #000000;font-weight:800">' + d1 + '</div>';
                            }
                        }else{
                            return d1;
                        }

                    }
                });
            }

            //点击一个日期触发事件
            function toChooseDate(mon) {
                $('#dateChoose' + mon).calendar({
                    onSelect: function(date) {
                        //获取当前点击的日期
                        var y = date.getFullYear();
                        var m = date.getMonth() + 1;
                        var d = date.getDate();
                        //先去判断list中是否已经选中
                        var ttt = y + "-" + m + "-" + d;
                        var zz = y + "-" + m + "-" + d;
                        var a = chooseList.indexOf(zz);
                        if (m == mon) {
                            if (a == -1) {
                                //不包含
                                chooseList.push(zz);
                            } else {
                                chooseList.splice(a, 1);
                            }
                        }
                        //遍历所有日期
                        $('#dateChoose' + mon).calendar({
                            formatter: function(date) {
                                //遍历当前月所有的时间
                                var y1 = date.getFullYear();
                                var m1 = date.getMonth() + 1;
                                var d1 = date.getDate();
                                //遍历前台需要展示的选中日期数据
                                //判断当前list中是否包含该日期
                                var yy = y1 + "-" + m1 + "-" + d1;
                                var a = chooseList.indexOf(yy);
                                if(m1 == mon){
                                    if (a == -1) {
                                        return yy.split("-")[2];
                                    } else {
                                        return '<div style="background:red; color: #000000;font-weight:800">' + d1 + '</div>';
                                    }
                                }else{
                                    return d1;
                                }

                            }
                        });
                    },
                    // 禁用不是当前月份的日期选择
                    validator: function(date) {
                        var m2 = date.getMonth() + 1;
                        if (m2 == mon) {
                            // true 可以进行点击复选  false 展示  不能进行选择
                            return false;
                        } else {
                            return false;
                        }
                    }
                });
            }


            //展示数据方法
            function showChooseDate() {
                var a = JSON.stringify(chooseList);
                if (chooseList.length > 0) {
                    alert(a);
                } else {
                    alert("没有选定数据")
                }
            }

            //设置复选框的操作
            $(".my-checkbox").click(function () {
                //防止触发事件冒泡
                var event = arguments.callee.caller.caller.arguments[0];
                event.stopPropagation();
                var tf = $(this).prop('checked');
                var vv = $(this).val();
                //如果当前选中 改变div底色 #ffab3f
                if(tf){
                    $("#check-div"+vv).css('background-color','#ffab3f');
                }else{
                    $("#check-div"+vv).css('background-color','#FFFFFF');
                }
            })
            //展示所有复选框的数据信息
            function showAllCheckVal(){
                //循环获取所有复选框选中的value值
                var serialNos = getAllCheckedMonth();
                alert(serialNos);
            }
            //获取当前所有的选中月份集合方法
            function getAllCheckedMonth(){
                var serialNos = $("input[name='monthCheck']:checked").map(function () {
                    return $(this).val();
                }).get().join(',');
                return serialNos.split(",");
            }
            //div点击选中事件
            $(".choose-month-title").click(function () {
                var vv = $(this).attr("id");
                clickChooseDiv(vv.split('div')[1])
            })

            function clickChooseDiv(val){
                var ckId = "#checkbox-month"+val;
                var serialNos = getAllCheckedMonth();
                var a = serialNos.indexOf(val+"");
                var ckStatus = a == -1?true:false;
                checkBoxCheck(ckId,ckStatus,val)
            }

            //设置复选框反选操作 div背景色刷新
            function checkBoxCheck(id,st,vv){
                var tf = $(id).prop('checked');
                var divColor = tf ?'#FFFFFF':'#ffab3f';
                $(id).prop("checked", !tf)
                $("#check-div"+vv).css('background-color',divColor);
            }
            //初始化所有复选框状态
            function initCheckBox(cls){
                $(cls).prop("checked", false)
                $(".choose-month-title").css('background-color','#FFFFFF');
            }

            //新增按钮点击事件
            $("#addDatePage").click(function () {
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                var yearVal = $('#filter_cdYear').combobox("getValue");
                parent.$.modalDialog({
                    title : '市场交易日设置新增',
                    width : 1200,
                    height : 630,
                    href : '${ctx}/transactionDay/addPage?marketVal='+marketVal+'&yearVal='+yearVal,
                    buttons : [ {
                        text : '提交',
                        iconCls : 'icon-save',
                        handler : function() {
                            addSubFun();
                            parent.$.modalDialog.handler.dialog('close');
                            // $("#serchTransactionDay").click();
                        }
                    }, {
                        text : '取消',
                        iconCls : 'icon-cancel',
                        handler : function() {
                            parent.$.modalDialog.handler.dialog('close');
                            $("#serchTransactionDay").click();
                        }
                    } ]
                });

            })

            //新增按钮确认事件
            function addSubFun() {
                var marketVal = parent.$.modalDialog.handler.find('#filter_cdMarketCode').combobox("getValue");
                var yearVal = parent.$.modalDialog.handler.find('#filter_cdYear').combobox("getValue");
                var toServer = parent.$.modalDialog.handler.find("#save_choose_date_dom").textbox("getValue");
                // marketVal = marketVal == "银行市场"?"001":"002";
                // yearVal = yearVal.split("年")[0];
                if(toServer != ""){
                    $.ajax({
                        url : "${ctx}/transactionDay/addSubmit",
                        dataType : "json",
                        type : "post",
                        data : {
                            days : toServer,
                            marketVal : marketVal,
                            yearVal:yearVal
                        },
                        success : function(result) {
                            if (result.success) {
                                $.messager.show({
                                    title : '提示',
                                    msg : result.msg,
                                    timeout : 1000
                                });
                                $("#serchTransactionDay").click();
                            } else {
                                parent.$.messager.alert('错误', result.msg, 'error');
                            }
                        },
                        error : function(data) {
                            parent.$.messager.alert('错误', '新增失败', 'error');
                        }
                    });
                }else{
                    parent.$.messager.alert('错误', '无选择数据，无法提交', 'error');
                }

            }

            //修改按钮点击事件
            $("#updateDatePage").click(function () {
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                var yearVal = $('#filter_cdYear').combobox("getValue");
                //获取当前多选框选中的月份数据
                //获取当前所有的选中月份集合方法
                    var serialNos = $("input[name='monthCheck']:checked").map(function () {
                        return $(this).val();
                    }).get().join(',');
                    if(serialNos.length == 0){
                        $.messager.show({
                            title : '提示',
                            msg : '修改请选择一条数据信息',
                            timeout : 1000
                        });
                        return
                    }
                    var monArray = serialNos.split(",");
                    if(monArray.length != 1){
                        $.messager.show({
                            title : '提示',
                            msg : '修改请选择一条数据信息',
                            timeout : 1000
                        });
                        return
                    }else{
                        var checkMon = monArray[0];
                        parent.$.modalDialog({
                            title : '市场交易日修改',
                            width : 600,
                            height : 400,
                            href : '${ctx}/transactionDay/editPage?marketVal='+marketVal+
                            '&yearVal='+yearVal+"&monIndex="+checkMon,
                            buttons : [ {
                                text : '提交',
                                iconCls : 'icon-save',
                                handler : function() {
                                    editSubFun();
                                    parent.$.modalDialog.handler.dialog('close');


                                }
                            }, {
                                text : '取消',
                                iconCls : 'icon-cancel',
                                handler : function() {
                                    parent.$.modalDialog.handler.dialog('close');
                                    $("#serchTransactionDay").click();
                                }
                            } ]
                        });
                    }

            })

            //修改按钮确认事件
            function editSubFun() {
                var marketVal = parent.$.modalDialog.handler.find('#filter_cdMarketCode').textbox("getValue");
                var yearVal = parent.$.modalDialog.handler.find('#filter_cdYear').textbox("getValue");
                var monVal = parent.$.modalDialog.handler.find('#filter_cdMonth').textbox("getValue");
                var toServer = parent.$.modalDialog.handler.find("#save_choose_date_dom").textbox("getValue");
                marketVal = marketVal == "银行市场"?"001":"002";
                yearVal = yearVal.split("年")[0];
                monVal = monVal.split("月")[0];
                if(toServer != ""){
                    $.ajax({
                        url : "${ctx}/transactionDay/editSubmit",
                        dataType : "json",
                        type : "post",
                        data : {
                            days : toServer,
                            marketVal : marketVal,
                            yearVal:yearVal,
                            monVal:monVal
                        },
                        success : function(result) {
                            if (result.success) {
                                $.messager.show({
                                    title : '提示',
                                    msg : result.msg,
                                    timeout : 1000
                                });
                                $("#serchTransactionDay").click();
                            } else {
                                parent.$.messager.alert('错误', result.msg, 'error');
                            }
                        },
                        error : function(data) {
                            parent.$.messager.alert('错误', '修改失败', 'error');
                        }
                    });
                }else{
                    parent.$.messager.alert('错误', '无选择数据，无法提交', 'error');
                }

            }


            //查看按钮点击事件
            $("#showDatePage").click(function () {
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                var yearVal = $('#filter_cdYear').combobox("getValue");
                //获取当前多选框选中的月份数据
                //获取当前所有的选中月份集合方法
                var serialNos = $("input[name='monthCheck']:checked").map(function () {
                    return $(this).val();
                }).get().join(',');
                if(serialNos.length == 0){
                    $.messager.show({
                        title : '提示',
                        msg : '请选择一条数据查看',
                        timeout : 1000
                    });
                    return
                }
                var monArray = serialNos.split(",");
                if(monArray.length != 1){
                    $.messager.show({
                        title : '提示',
                        msg : '请选择一条数据查看',
                        timeout : 1000
                    });
                    return
                }else{
                    var checkMon = monArray[0];
                    parent.$.modalDialog({
                        title : '市场交易日查看',
                        width : 600,
                        height : 400,
                        href : '${ctx}/transactionDay/viewPage?marketVal='+marketVal+
                        '&yearVal='+yearVal+"&monIndex="+checkMon,
                        buttons : [ {
                            text : '确认',
                            iconCls : 'icon-save',
                            handler : function() {
                                parent.$.modalDialog.handler.dialog('close');
                                $("#serchTransactionDay").click();

                            }
                        }, {
                            text : '取消',
                            iconCls : 'icon-cancel',
                            handler : function() {
                                parent.$.modalDialog.handler.dialog('close');
                                $("#serchTransactionDay").click();
                            }
                        } ]
                    });
                }

            })

            //复核按钮点击事件
            $("#checkDateButton").click(function () {
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                var yearVal = $('#filter_cdYear').combobox("getValue");
                var name = loginUsername;

                //获取当前多选框选中的月份数据
                //获取当前所有的选中月份集合方法
                var serialNos = $("input[name='monthCheck']:checked").map(function () {
                    return $(this).val();
                }).get().join(',');
                if(serialNos.length == 0){
                    $.messager.show({
                        title : '提示',
                        msg : '复核请选择数据信息',
                        timeout : 1000
                    });
                    return
                }
                //复核人判断
                var names = new Array();
                var index = serialNos.split(",");
                for(var i in index){
                    names.push($("#update-name"+index[i]).text());
                }
                if(names.indexOf(name) != -1){
                    $.messager.show({
                        title : '提示',
                        msg : '您的的复核人与操作人相同，请更换复核用户',
                        timeout : 3000,
                        showType : 'fade',
                        style : {
                            right : '',
                            bottom : ''
                        }
                    });
                    return;
                }

                //复核状态判断
                var ckState = new Array();
                // var index = serialNos.split(",");
                for(var i in index){
                    ckState.push($("#check-status"+index[i]).text());
                }
                if(ckState.indexOf('已复核') != -1){
                    $.messager.show({
                        title : '提示',
                        msg : '已复核数据不能再次复核',
                        timeout : 3000,
                        showType : 'fade',
                        style : {
                            right : '',
                            bottom : ''
                        }
                    });
                    return;
                }

                //操作状态判断
                var ckAction = new Array();
                // var index = serialNos.split(",");
                for(var i in index){
                    ckAction.push($("#update-step"+index[i]).text());
                }
                if(ckAction.indexOf('无') != -1){
                    $.messager.show({
                        title : '提示',
                        msg : '未设置数据无法复核',
                        timeout : 3000,
                        showType : 'fade',
                        style : {
                            right : '',
                            bottom : ''
                        }
                    });
                    return;
                }
                //重置复选框选择按钮
                var cls = $(".my-checkbox");
                initCheckBox(cls);
                parent.$.messager.confirm('询问', '您确认复核'+yearVal+'年'+serialNos+'月的数据吗？', function(b) {
                    if (b) {
                        $.post('${ctx}/transactionDay/checkSubmit', {
                            marketVal : marketVal,
                            yearVal:yearVal,
                            mons:serialNos
                        }, function(result) {
                            if (result.success) {
                                parent.$.messager.alert('提示', result.msg, 'info');
                                $("#serchTransactionDay").click();
                            }
                        }, 'JSON');
                    }
                });
            })

            //删除按钮点击事件
            $("#deleteDateButton").click(function () {
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
                var yearVal = $('#filter_cdYear').combobox("getValue");
                //获取当前多选框选中的月份数据
                //获取当前所有的选中月份集合方法
                var serialNos = $("input[name='monthCheck']:checked").map(function () {
                    return $(this).val();
                }).get().join(',');
                //操作状态判断
                var ckAction = new Array();
                var index = serialNos.split(",");
                for(var i in index){
                    ckAction.push($("#update-step"+index[i]).text());
                }
                if(ckAction.indexOf('无') != -1 || ckAction.indexOf('删除') != -1){
                    $.messager.show({
                        title : '提示',
                        msg : '选择数据状态有误，无法执行删除操作',
                        timeout : 3000,
                        showType : 'fade',
                        style : {
                            right : '',
                            bottom : ''
                        }
                    });
                    return;
                }

                if(serialNos.length == 0){
                    $.messager.show({
                        title : '提示',
                        msg : '删除请选择数据信息',
                        timeout : 1000
                    });
                    return
                }
                //重置复选框选择按钮
                var cls = $(".my-checkbox");
                initCheckBox(cls);
                parent.$.messager.confirm('询问', '您确认删除'+yearVal+'年'+serialNos+'月的数据吗？', function(b) {
                    if (b) {
                        $.post('${ctx}/transactionDay/delSubmit', {
                            marketVal : marketVal,
                            yearVal:yearVal,
                            mons:serialNos
                        }, function(result) {
                            if (result.success) {
                                parent.$.messager.alert('提示', result.msg, 'info');
                                $("#serchTransactionDay").click();
                            }
                        }, 'JSON');
                    }
                });

            })

            $("#checkAllbox").click(function () {
                //反选所有的
                var mons = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
                for (var i in mons) {
                    var ckId = "#checkbox-month"+mons[i];
                    var tf = $(ckId).prop('checked');
                    var divColor = tf ?'#FFFFFF':'#ffab3f';
                    $(ckId).prop("checked", !tf)
                    $("#check-div"+mons[i]).css('background-color',divColor);
                }
            })
        })

        /*function searchFun() {
            $(function () {
                //获取下拉框选中的属性值
            })
        }*/


    </script>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false,title:'查询区域'"
     align="center" style="height: 85px;">
    <form id="searchForm">
        <table data-options="fit:true,border:false"
               style="padding-top: 10px;">
            <tr>
                <%--市场 & 年份 都从后台查询--%>
                <td align="center">市场名称:
                    <select id="filter_cdMarketCode" name="filter_cdMarketCode"
                            style="width: 150px; height: 20px;" class="easyui-combobox" data-options="
					url:' ${ctx}/transactionDay/getMarketType',
					valueField:'marketVal',
					textField:'marketName'">
                    </select>
                    年度:
                    <select id="filter_cdYear" name="filter_cdYear" style="width: 150px; height: 20px;"
                            class="easyui-combobox" data-options="
					url:' ${ctx}/transactionDay/getTheYearList',
					valueField:'yearVal',
					textField:'yearName'">
                    </select>
                </td>
                <td align="center"><a href="javascript:void(0);"
                                      class="easyui-linkbutton"
                                      data-options="iconCls:'icon-search',plain:true"
                                      id="serchTransactionDay">查询</a></td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center',border:true,title:'交易日管理列表'">
    <div id="toolbar">
        <a id="addDatePage" href="javascript:void(0);"
           class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">交易日新增</a>
        <a id="updateDatePage" href="javascript:void(0);"
           class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-edit'">交易日修改</a>
        <a id="showDatePage" href="javascript:void(0);"
           class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-tip'">交易日查看</a>
        <a id="deleteDateButton" href="javascript:void(0);"
           class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-del'">交易日删除</a>
        <a id="checkDateButton" href="javascript:void(0);"
           class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-ok'">交易日复核</a>
        <a id="checkAllbox" href="javascript:void(0);"
           class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-standard-contrast-low'">全选/反选</a>
    </div>
    <div>
        <table cellspacing="10" style="margin: 17px 45px ">
            <tr>
                <td>
                    <div class="choose-month-title" id="check-div1">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="1" id="checkbox-month1" ></td>
                            <td width="50"><span id="check-status1"></span></td>
                            <td width="50"><span id="update-name1"></span></td>
                            <td ><span id="update-step1"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose1" data-options="month:1,weeks:['日','一','二','三','四','五','六'],
					months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']">
                        </div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div2">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="2" id="checkbox-month2" ></td>
                            <td width="50"><span id="check-status2"></span></td>
                            <td width="50"><span id="update-name2"></span></td>
                            <td ><span id="update-step2"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose2" data-options="month:2,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div3">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="3" id="checkbox-month3" ></td>
                            <td width="50"><span id="check-status3"></span></td>
                            <td width="50"><span id="update-name3"></span></td>
                            <td ><span id="update-step3"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose3" data-options="month:3,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div4">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="4" id="checkbox-month4" ></td>
                            <td width="50"><span id="check-status4"></span></td>
                            <td width="50"><span id="update-name4"></span></td>
                            <td ><span id="update-step4"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose4" data-options="month:4,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div5">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="5" id="checkbox-month5" ></td>
                            <td width="50"><span id="check-status5"></span></td>
                            <td width="50"><span id="update-name5"></span></td>
                            <td ><span id="update-step5"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose5" data-options="month:5,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div6">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="6" id="checkbox-month6" ></td>
                            <td width="50"><span id="check-status6"></span></td>
                            <td width="50"><span id="update-name6"></span></td>
                            <td ><span id="update-step6"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose6" data-options="month:6,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="choose-month-title" id="check-div7">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="7" id="checkbox-month7" ></td>
                            <td width="50"><span id="check-status7"></span></td>
                            <td width="50"><span id="update-name7"></span></td>
                            <td ><span id="update-step7"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose7" data-options="month:7,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div8">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="8" id="checkbox-month8" ></td>
                            <td width="50"><span id="check-status8"></span></td>
                            <td width="50"><span id="update-name8"></span></td>
                            <td ><span id="update-step8"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose8" data-options="month:8,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div9">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="9" id="checkbox-month9" ></td>
                            <td width="50"><span id="check-status9"></span></td>
                            <td width="50"><span id="update-name9"></span></td>
                            <td ><span id="update-step9"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose9" data-options="month:9,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div10">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="10" id="checkbox-month10" ></td>
                            <td width="50"><span id="check-status10"></span></td>
                            <td width="50"><span id="update-name10"></span></td>
                            <td ><span id="update-step10"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose10" data-options="month:10,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div11">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="11" id="checkbox-month11" ></td>
                            <td width="50"><span id="check-status11"></span></td>
                            <td width="50"><span id="update-name11"></span></td>
                            <td ><span id="update-step11"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose11" data-options="month:11,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>
                <td>
                    <div class="choose-month-title" id="check-div12">
                        <table><tr>
                            <td width="20"><input class="my-checkbox" type="checkbox" name = "monthCheck" value="12" id="checkbox-month12"></td>
                            <td width="50"><span id="check-status12"></span></td>
                            <td width="50"><span id="update-name12"></span></td>
                            <td ><span id="update-step12"></span></td>
                        </tr></table>
                        <div class="easyui-calendar" id="dateChoose12" data-options="month:12,weeks:['日','一','二','三','四','五','六'],
				months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']"></div>
                    </div>
                </td>

            </tr>
        </table>
    </div>
</div>
<%--展示日交易日日期信息数据 （根据上述条件查询）--%>

</body>
</html>