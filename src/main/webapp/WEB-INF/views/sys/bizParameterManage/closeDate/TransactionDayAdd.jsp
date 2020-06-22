<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/calendar.css" />
<script type="text/javascript">
    //保存展示在前台的天数list
    var chooseListAdd = new Array();
    var toServer = "";
    $(function() {
        function addToSaveDiv() {
            toServer = "";
            for (var z in chooseListAdd) {
                toServer+=chooseListAdd[z]+",";
            }
            $("#save_choose_date_dom").textbox({
                value:toServer
            })
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
            $.getJSON('${ctx}/transactionDay/getMonInfoList?yearVal='+yearVal+'&marketVal='+marketVal+'&type=2', null, function (result) {
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
            $.getJSON('${ctx}/transactionDay/getDateList?yearVal='+yearVal+'&marketVal='+marketVal+'&type=2', null, function (result) {
                chooseListAdd = new Array()
                chooseListAdd = result
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
                var yearVal = $('#filter_cdYear').combobox("getValue");
                chooseListAdd = new Array();
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
                chooseListAdd = new Array();
                var marketVal = $('#filter_cdMarketCode').combobox("getValue");
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
            addToSaveDiv();
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
                    var a = chooseListAdd.indexOf(yy);
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
                    var a = chooseListAdd.indexOf(zz);
                    if (m == mon) {
                        if (a == -1) {
                            //不包含
                            chooseListAdd.push(zz);
                        } else {
                            chooseListAdd.splice(a, 1);
                        }
                    }
                    addToSaveDiv();
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
                            var a = chooseListAdd.indexOf(yy);
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
                },
                // 禁用不是当前月份的日期选择
                validator: function(date) {
                    var m2 = date.getMonth() + 1;
                    if (m2 == mon) {
                        // true 可以进行点击复选  false 展示  不能进行选择
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    })
</script>

<div id="fundModelPanel" style="overflow: auto">
	<div class="gridDiv" data-options="region:'center',border:false">
		<form id="addChannelProductReal" method="post">
			<table style="margin: auto" cellspacing="15">
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
				</tr>
			</table>
			<table align="center" cellspacing="3" style="margin: 20px 20px ">
				<tr>
					<td>
						<div class="choose-month-title" id="check-div1">
							<table><tr>
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
			<div id="saveChooseDate" style="visibility: hidden;">
				<input id="save_choose_date_dom"
					   style="width: 150px; height: 20px;" class="easyui-textbox">
			</div>
		</form>
	</div>
</div>