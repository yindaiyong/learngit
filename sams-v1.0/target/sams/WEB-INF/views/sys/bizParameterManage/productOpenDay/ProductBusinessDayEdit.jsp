<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/calendar.css" />
<script type="text/javascript">
    //保存展示在前台的天数list
    var chooseListEdit = new Array();
    var toServer = "";

    $(function() {
        /*初始化 市场 年份 月份信息*/
        yearVal = '${yearVal}';
        monIndex = '${monIndex}';
        channelCode = '${channelCode}';
        channelName = '${channelName}';
        productCode = '${productCode}';
        productName = '${productName}';
        opType = '${opType}'

        $('#filter_cdYear').textbox({
            value:yearVal,
            readonly:true
        })
        $('#filter_cdMonth').textbox({
            value:monIndex+"月",
            readonly:true
        })
		if('022' == opType){
            $('#filter_opType').textbox({
                value:'申购',
                readonly:true
            })
		}
		if('024' == opType){
            $('#filter_opType').textbox({
                value:'赎回',
                readonly:true
            })
		}
        if('130' == opType){
            $('#filter_opType').textbox({
                value:'认购确认',
                readonly:true
            })
        }
        if('124' == opType){
            $('#filter_opType').textbox({
                value:'赎回确认',
                readonly:true
            })
        }

        $('#chooseChannelName').textbox({
            value:channelName,
            readonly:true
        })
        $('#chooseProductName').textbox({
            value:productName,
            readonly:true
        })

        $('#save_choose_channel_code').textbox({
            value:channelCode,
            readonly:true
        })
        $('#save_choose_product_code').textbox({
            value:productCode,
            readonly:true
        })

        /*获取当前需要回显的日期数据信息*/
        transDateList = '${dataList}';
		$.each(JSON.parse(transDateList),function(index,value){
            chooseListEdit.push(value);
		});

		var oneDay = chooseListEdit[0].split("-");
		initChenkDate(oneDay[1]);
		toChooseDate(oneDay[1]);
        monInfoList = '${monInfoList}';
        makeMonList(monInfoList);

        addToSaveDiv()

        function makeMonList(val){

            $.each(JSON.parse(val),function(index,value){
                var checkStatu = value.checkStatus;
                var updateUser = value.updateUser;
                var action = value.updateAction;
                var index = value.monIndex;
                $('.easyui-calendar').calendar({
                    year: oneDay[0],
                    month:oneDay[1]
                })
                // chooseList.push(value);
                //判断后台数据

                if (checkStatu == "00") {
                    $("#check-status").text('未复核');
                    $("#check-status").css('color','#6699ff');
                }
                if (checkStatu == "01") {
                    $("#check-status").text('已复核');
                    $("#check-status").css('color','#00CD00');
                }
                if (checkStatu == "02") {
                    $("#check-status").text('未设置');
                    $("#check-status").css('color','#FF0000');
                }
                // $("#check-status"+index).text(checkStatu);
                $("#update-name").text(updateUser);
                $("#update-step").text(getAction(action));

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

        /*每月全选按钮点击事件*/
        $("#checkAll").click(function () {
            var mmm = monIndex;
            var tf = $(this).prop('checked');
            //判断当前月份的数据是否已经被选中
            var now=new Date();
            var d = new Date(now.getFullYear(),mmm,0);
            var yearV = $('#filter_cdYear').textbox("getValue");
            var days=d.getDate();
            //组装当月所有的日期数据
            var thisMon = new Array();
            for(var i = 1;i<=days;i++){
                var dd = yearV+"-"+mmm+"-"+i;
                thisMon.push(dd);
            }
            //判断当前的需要添加的list中是否有存在数据（清除缓存区以及整个list）
            for(var z in thisMon){
                var a = chooseListEdit.indexOf(thisMon[z]);
                if(tf){
                    if (a == -1) {
                        //不包含
                        chooseListEdit.push(thisMon[z]);
                    }
                }else{
                    if (a != -1) {
                        //包含
                        chooseListEdit.splice(a, 1);
                    }
                }
            }
            initChenkDate(mmm);
            addToSaveDiv();

        })

        //初始化渲染
        function initChenkDate(mon){
            //遍历所有日期
            $('#dateChoose').calendar({
                formatter: function(date) {
                    //遍历当前月所有的时间
                    var y1 = date.getFullYear();
                    var m1 = date.getMonth() + 1;
                    var d1 = date.getDate();
                    //遍历前台需要展示的选中日期数据
                    //判断当前list中是否包含该日期
                    var yy = y1 + "-" + m1 + "-" + d1;
                    var a = chooseListEdit.indexOf(yy);
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
		function addToSaveDiv() {
            toServer = "";
            for (var z in chooseListEdit) {
                var ddd = chooseListEdit[z];
                if(ddd.split("-")[2] != '0'){
                    toServer+=ddd+",";
                }
            }
            $("#save_choose_date_dom").textbox({
                value:toServer
            })
        }
        //点击一个日期触发事件
        function toChooseDate(mon) {
            $('#dateChoose').calendar({
                onSelect: function(date) {
                    //获取当前点击的日期
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    //先去判断list中是否已经选中
                    var zz = y + "-" + m + "-" + d;
                    var a = chooseListEdit.indexOf(zz);
                    //遍历一次当前所有选中的数据，放入隐藏框
                    if (m == mon) {
                        if (a == -1) {
                            //不包含
                            chooseListEdit.push(zz);
                        } else {
                            chooseListEdit.splice(a, 1);
                        }
                    }
                    addToSaveDiv()
                    //遍历所有日期
                    $('#dateChoose').calendar({
                        formatter: function(date) {
                            //遍历当前月所有的时间
                            var y1 = date.getFullYear();
                            var m1 = date.getMonth() + 1;
                            var d1 = date.getDate();
                            //遍历前台需要展示的选中日期数据
                            //判断当前list中是否包含该日期
                            var yy = y1 + "-" + m1 + "-" + d1;
                            var a = chooseListEdit.indexOf(yy);
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
					<td align="center">
						渠道:
						<input id="chooseChannelName" name="chooseChannelCode"
							   style="width: 200px; height: 20px;" class="easyui-textbox">
						</input>
						产品:
						<input id="chooseProductName" name="chooseProductCode"
							   style="width: 200px; height: 20px;" class="easyui-textbox">
						</input>


					</td>
				</tr>
				<tr>
					<%--市场 & 年份 都从后台查询--%>
					<td align="center">
						年度:
						<input id="filter_cdYear" name="filter_cdYear" style="width: 110px; height: 20px;"
							   class="easyui-textbox">
						</input>
						月度:
						<input id="filter_cdMonth" name="filter_cdYear" style="width: 110px; height: 20px;"
							   class="easyui-textbox">
						</input>
						业务:
						<input id="filter_opType" name="filter_cdYear" style="width: 110px; height: 20px;"
							   class="easyui-textbox">
						</input>
					</td>
				</tr>
			</table>
			<table align="center" cellspacing="10" >
				<tr>
					<td>
						<div class="choose-month-title" id="check-div">
							<table><tr>
								<td><input type="checkbox" id="checkAll"></td>
								<td width="50"><span id="check-status"></span></td>
								<td width="50"><span id="update-name"></span></td>
								<td ><span id="update-step"></span></td>
							</tr></table>
							<div class="easyui-calendar" id="dateChoose" data-options="weeks:['日','一','二','三','四','五','六'],
					months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']">
							</div>
						</div>
					</td>
				</tr>
			</table>
           <%-- <table style="margin: auto">
                <tr>
                    <td align="center">
                        <a href="javascript:void(0);" id="showAllCheckDateButton"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>
                        <a href="javascript:void(0);" id="closeThisDialogButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
                    </td>
                </tr>
            </table>--%>
			<div id="saveChooseDate" style="visibility: hidden;">
				<input id="save_choose_date_dom"
					   style="width: 150px; height: 20px;" class="easyui-textbox">
				<input id="save_choose_channel_code"
					   style="width: 150px; height: 20px;" class="easyui-textbox">
				<input id="save_choose_product_code"
					   style="width: 150px; height: 20px;" class="easyui-textbox">
			</div>

		</form>
	</div>
</div>