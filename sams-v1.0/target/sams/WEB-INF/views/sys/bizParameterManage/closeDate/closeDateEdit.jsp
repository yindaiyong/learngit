<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

		var year = '${year}';
		var month = '${month}';
		var cdMarketCode = '${cdMarketCode}';
		$("#year").find("option[value=" + year + "]").attr("selected", true);
		$("#month").find("option[value=" + month + "]").attr("selected", true);
		$("#cdMarketCode").find("option[value=" + cdMarketCode + "]").attr(
				"selected", true);

		var year = $("#year").val();
		var month = $("#month").val();
		
		var cdMarketCode = $("#cdMarketCode").val();
		$("#leftId").empty();
		$("#rightId").empty();
		$.ajax({
					url : "${ctx}/closeDate/getEdit2",
					dataType : "json",
					type : "post",
					data : {
						year : year,
						month : month,
						cdMarketCode : cdMarketCode
					},
					success : function(data) {
						for ( var x in data) {
							$("#leftId").prepend(
									"<option value=\""+x+"\">" + data[x]
											+ "</option>");
						}
					},
					error : function(data) {
						alert("查询失败");
					}
				});

		$.ajax({
					url : "${ctx}/closeDate/getEdit1",
					dataType : "json",
					type : "post",
					data : {
						year : year,
						month : month,
						cdMarketCode : cdMarketCode
					},
					success : function(data) {
						for ( var x in data) {
							$("#rightId").prepend(
									"<option value=\""+x+"\">" + data[x]
											+ "</option>");
						}
					},
					error : function(data) {
						alert("查询失败");
					}
				});
	});

	function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate() + 1;
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d);
	}

	function searchFun() {
		var year = $('#year').combobox('getValue');
		var month = $('#month').combobox('getValue');
		$("#leftId").empty();
		$("#rightId").empty();
		$.ajax({
					url : "${ctx}/closeDate/getEdit2",
					dataType : "json",
					type : "post",
					data : {
						year : year,
						month : month
					},
					success : function(data) {
						for ( var x in data) {
							$("#leftId").prepend(
									"<option value=\""+x+"\">" + data[x]
											+ "</option>");
						}
					},
					error : function(data) {
						parent.$.messager.alert('错误', '工作日查询失败', 'error');
					}
				});

		$.ajax({
					url : "${ctx}/closeDate/getEdit1",
					dataType : "json",
					type : "post",
					data : {
						year : year,
						month : month
					},
					success : function(data) {
						for ( var x in data) {
							$("#rightId").prepend(
									"<option value=\""+x+"\">" + data[x]
											+ "</option>");
						}
					},
					error : function(data) {
						parent.$.messager.alert('错误', '非工作日查询失败', 'error');
					}
				});

	}
	function toRight() {
		var leftId = $("#leftId").val;
		$("#rightId").prepend(
				"<option value=\""+leftId+"\">" + leftId + "</option>");
	}

	//左移按钮
	function toLeft() {
		var a = $("#rightId option:selected");
		$("#leftId").append($("#rightId option:selected"));
		var al = a.val();
		//ll.push(al);
		$('#leftId option').sort(function(a, b) {
			var aText = $(a).text().toUpperCase();
			var bText = $(b).text().toUpperCase();
			if (aText > bText)
				return 1;
			if (aText < bText)
				return -1;
			return 0;
		}).appendTo('#leftId');
	};
	//右移按钮	
	function toRight() {
		var b = $("#leftId option:selected")
		$("#rightId").append($("#leftId option:selected"));
		var bl = b.val();
		//rr.push(bl);
		$('#rightId option').sort(function(a, b) {
			var aText = $(a).text().toUpperCase();
			var bText = $(b).text().toUpperCase();
			if (aText > bText)
				return 1;
			if (aText < bText)
				return -1;
			return 0;
		}).appendTo('#rightId');
	}
</script>

<div id="fundModelPanel" style="overflow: auto">
	<div class="gridDiv" data-options="region:'center',border:false">
		<form id="addChannelProductReal" method="post">
			<table style="margin: auto">
				<tr>
					<th>年度:</th>
					<td><select id="year" style="width: 150px"
						class="easyui-combobox" disabled="disabled">
							<option value='2015'>2015</option>
							<option value='2016'>2016</option>
							<option value='2017'>2017</option>
							<option value='2018'>2018</option>
							<option value='2019'>2019</option>
							<option value='2020'>2020</option>
							<option value='2021'>2021</option>
							<option value='2022'>2022</option>
							<option value='2023'>2023</option>
							<option value='2024'>2024</option>
							<option value='2025'>2025</option>
							<option value='2026'>2026</option>
							<option value='2027'>2027</option>
							<option value='2028'>2028</option>
							<option value='2029'>2029</option>
							<option value='2030'>2030</option>
					</select></td>
					<th>月度:</th>
					<td><select id="month" style="width: 150px"
						class="easyui-combobox" disabled="disabled">
							<option value='01'>01</option>
							<option value='02'>02</option>
							<option value='03'>03</option>
							<option value='04'>04</option>
							<option value='05'>05</option>
							<option value='06'>06</option>
							<option value='07'>07</option>
							<option value='08'>08</option>
							<option value='09'>09</option>
							<option value='10'>10</option>
							<option value='11'>11</option>
							<option value='12'>12</option>
					</select></td>
					<th>市场名称:</th>

					<td><select id="cdMarketCode" name="ciMarketCode"
						style="width: 150px; height: 20px;" class="easyui-combobox" disabled="disabled">
							<option value="001">银行市场</option>
							<option value="002">券商市场</option>
					</select></td>
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-search',plain:true"
						onclick="searchFun();">查询</a>
					<td></td>
				</tr>

			</table>
			<table align="center">
				<tr>
					<td>工作日:</td>
					<td></td>
					<td>非工作日:</td>
				</tr>
				<tr>
					<td><select id="leftId" style="width: 280px" multiple
						size="15">
					</select></td>
					<td style="width: 80px; text-align: center;"><a
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-hamburg-right',plain:true"
						onclick="toRight();"></a> <br /> <br /> <a
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-hamburg-left',plain:true"
						onclick="toLeft();"></a></td>
					<td><select id="rightId" style="width: 280px" multiple
						size="15">
					</select></td>
				</tr>

				<tr>
					<!-- <input type="button" value="保存" onclick="save()" style="width:40px;"/> -->
				</tr>
			</table>
		</form>
	</div>
</div>