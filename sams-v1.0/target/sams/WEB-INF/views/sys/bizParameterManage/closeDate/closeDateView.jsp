<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript">
	$(function() {
	     var year = '${year}';
	     var month = '${month}';
	     var cdMarketCode = '${cdMarketCode}';
		 $("#year").find("option[value="+year+"]").attr("selected",true);
		 $("#month").find("option[value="+month+"]").attr("selected",true);
		 $("#cdMarketCode").find("option[value="+cdMarketCode+"]").attr("selected",true);
		 
		 var year = $("#year").val();
		 var month = $("#month").val();
		 var cdMarketCode = $("#cdMarketCode").val();
		$("#leftId").empty(); 
		$("#rightId").empty(); 
			$.ajax({
				 url: "${ctx}/closeDate/getEdit2",
	        	 dataType: "json",
	        	 type:"post",
	        	 data:{
	        		 year: year,
	        		 month: month,
	        		 cdMarketCode:cdMarketCode
	        		  },
				success : function(data) {
					
					for (var x in data)
					{
						$("#leftId").prepend("<option value=\""+x+"\">"+data[x]+"</option>");
					} 
				},
				error:function(data) {
					
					alert("查询失败");
				}
			});
			
			$.ajax({
				 url: "${ctx}/closeDate/getEdit1",
	        	 dataType: "json",
	        	 type:"post",
	        	 data:{
	        		 year: year,
	        		 month: month,
	        		 cdMarketCode:cdMarketCode
	        		  },
				success : function(data) {
					
					for (var x in data)
					{
						$("#rightId").prepend("<option value=\""+x+"\">"+data[x]+"</option>");
					} 
				},
				error:function(data) {
					
					alert("查询失败");
				}
			});
	});
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
						disabled="disabled" class="easyui-combobox">
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
					<th>市场:</th>
					<td><select id="cdMarketCode" name="ciMarketCode"
						style="width: 150px; height: 20px;" disabled="disabled"
						class="easyui-combobox">
							<option value="001">银行市场</option>
							<option value="002">券商市场</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>
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
					<td>
						<select id="leftId" style="width: 280px" multiple
						size="15"></select>
					</td>
					<td style="width: 120px; text-align: center;"></td>
					<td>
						<select id="rightId" style="width: 280px" multiple
						size="15"></select>
					</td>
				</tr>
				<tr></tr>
			</table>
		</form>
	</div>
</div>