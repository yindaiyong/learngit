<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>电子合同信息查看</title>
</head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		//给combox赋值
		setComboboxValue("VALIDFLAG");
		dataGrid = $('#dataGrid').datagrid(
						{
							url : '${ctx}/elContract/getElContractData' ,
							striped : true,
							pagination : true,
							idField : 'psChannelCode',
							sortName : 'CR_ID',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,400, 500 ],
							columns : [[{
								field :'ck',
								checkbox : 'true',
							},{
								field : 'ID',
								halign : 'center',
								align : 'center',
								hidden : 'true' 
							}, {
								title : '渠道编号',
								field : 'CHANNELCODE',
								width : 120,
								halign : 'center',
								align : 'center',
                                sortable : true
							},{
								title : '产品编号',
								field : 'FUNDCODE',
								width : 80,
								halign : 'center',
								align : 'center',
                                sortable : true
							},{
								title : '交易日期',
								field : 'BUSINESSDATE',
								width : 80,
								halign : 'center',
								align : 'center',
                                sortable : true
							},{
								title : '投资人基金交易账号',
								field : 'TRANSACTIONACCOUNTID',
								width : 160,
								halign : 'center',
								align : 'center'
							},{
								title : '业务代码',
								field : 'BUSINESSCODE',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true,
								formatter : function(value){
									if(value == '401') return '合同签订';
									if(value == '402') return '合同补正';
									if(value == '403') return '合同撤销';
									return value;
								}
							},{
								title : '合同类型',
								field : 'CONTRACTTYPE',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true,
								formatter : function(value){
									if(value == '1') return '电子签名合同';
									if(value == '2') return '纸质合同';
									return value;
								}
							},{
								title : '流水号',
								field : 'SERIALNO',
								width : 200,
								halign : 'center',
								align : 'center'
							},{
								title : '版本号',
								field : 'VERSION',
								width : 160,
								halign : 'center',
								align : 'center'
							},{
								title : '签署类型',
								field : 'SIGNTYPE',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true,
								formatter : function(value){
									if(value == '1') return '新签合同';
									if(value == '2') return '补签合同';
									return value;
								}
							},{
								title : '销售人代码',
								field : 'DISTRIBUTORCODE',
								width : 160,
								halign : 'center',
								align : 'center'
							},{
								title : '投资人户名',
								field : 'INVESTORNAME',
								width : 160,
								halign : 'center',
								align : 'center'
							},{
								title : '个人/机构标志',
								field : 'INDIVIDUALORINSTITUTION',
								width : 160,
								halign : 'center',
								align : 'center' ,
								sortable : true,
								formatter : function(value){
									if(value == '0') return '机构';
									if(value == '1') return '个人';
									return value;
								} 
							},{
								title : '个人证件类型及机构证件类型',
								field : 'CERTIFICATETYPE',
								width : 200,
								halign : 'center',
								align : 'center' ,
								sortable : true,
								formatter : function(value,row,index){
									if(row.INDIVIDUALORINSTITUTION=='1'){
										if(value == '0') return '身份证';
										if(value == '1') return '护照';
										if(value == '2') return '军官证';
										if(value == '3') return '士兵证';
										if(value == '4') return '港澳居民内地通行证';
										if(value == '5') return '户口本';
										if(value == '6') return '外国护照';
										if(value == '7') return '其它';
										if(value == '8') return '文职证';
										if(value == '9') return '警官证';
										if(value == 'A') return '台胞证';	
									}
									
									if(row.INDIVIDUALORINSTITUTION=='0'){
										if(value == '0') return '组织机构代码证';
										if(value == '1') return '营业执照';
										if(value == '2') return '行政机关';
										if(value == '3') return '社会团体';
										if(value == '4') return '军队';
										if(value == '5') return '武警';
										if(value == '6') return '下属机构(具有主管单位批文号)';
										if(value == '7') return '基金会';
										if(value == '8') return '其它';
									}

										
									return value;
								} 
							},{
								title : '投资人证件号码',
								field : 'CERTIFICATENO',
								width : 160,
								halign : 'center',
								align : 'center',
                                formatter : function(value) {
                                    if(value != '' && value != null && value.length > 8){
                                        return value.replace(/^(.{4})(?:\w+)(.{4})$/, "$1****$2");
                                    }else{
                                        return value;
                                    }
                                }
							},{
								title : '交易发生日期',
								field : 'TRANSACTIONDATE',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true
							},{
								title : '交易发生时间',
								field : 'TRANSACTIONTIME',
								width : 160,
								halign : 'center',
								align : 'center'
							},{
								title : '受理方式',
								field : 'ACCEPTMETHOD',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true,
								formatter : function(value){
									if(value == '0') return '柜台';
									if(value == '2') return '网上';
									if(value == '5') return '其他';
									return value;
								} 
							},{
								title : '受理地点',
								field : 'ACCEPTPLACE',
								width : 360,
								halign : 'center',
								align : 'center'
							},{
								title : '最后更新日',
								field : 'ALTERNATIONDATE',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true
							},{
								title : '处理结果',
								field : 'VALIDFLAG',
								width : 160,
								halign : 'center',
								align : 'center',
                                sortable : true,
								formatter : function(value){
									if(value == '00') return '失败';
									if(value == '01') return '成功';
									return value;
								} 
							},{
								title : '错误信息',
								field : 'ERRORDEC',
								width : 800,
								halign : 'center',
								align : 'center',
                                formatter : function(value){
                                    if(value == null || value === null){
                                        return "<span ></span>";
                                    }else{
                                        return "<span style = 'color:red;font-weight:bold;' title='" + value + "'>" + value + "</span>";
                                    }
                                }
							} ]], 
							toolbar : '#toolbar',
							//加载成功
							onLoadSuccess: function (data) {
								//去掉标题上的复选框
							   $(".datagrid-header-check").html("");
							}
						});
  	});
	 
	 function searchFun() {
         var parmFilter = $.serializeObject($('#searchForm'));
         dataGrid.datagrid('load', parmFilter);
     }
	 
	 function cleanFun() {
         $('#searchForm input').val('');
         dataGrid.datagrid('load', {});
     }
	 
	//给下拉框赋值
	 function setComboboxValue(id){
	 	$("#"+id+"").combobox({
	 	     panelHeight: 'auto',//自适应
	 	     valueField: 'id',//绑定字段ID
	 	     textField: 'name',//绑定字段Name
	 	     onLoadSuccess:function(){
	 	         $(".combo").click(function(){
	 	            $(this).prev().combobox("showPanel");
	 	        });
	 	     },
	 	     data :[{
	 	         "id":"",
	 	         "name":"请选择"
	 	     },{
	 	         "id":"00",
	 	         "name":"失败"
	 	     },{
	 	         "id":"01",
	 	         "name":"成功"
	 	     }]
	 	});
	 }
	 
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询区域'"	align="center" style="height: 120px;">
		<form id="searchForm">
			<table  data-options="fit:true,border:false" style="padding-top: 10px;">
				 <tr align="center">
                    <th>渠道名称:</th>
                    <td><input class="easyui-combobox"  style = "width:200px;"  id="CHANNELCODE"   name ="filter_CHANNELCODE"  data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME',required:false"></td>
                    <th>渠道端产品名称:</th>
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="PRODUCTCODE"   name ="filter_PRODUCTCODE"  data-options="
					url:'${ctx}/combobox/queryUsedProductInfoComBox',
					onBeforeLoad:function(param){
					        param.channelCode = '';
					},
					valueField:'ID',
					textField:'NAME',required:false"></td>
                </tr>
                
                <tr>
                <th>交易日期:</th>
                    <td><input class="easyui-datebox" id="ECTRANSDATE" name="filter_ECTRANSDATE" /></td>
                <th>处理结果:</th>
                    <td><input id="VALIDFLAG" name="filter_VALIDFLAG" class="easyui-combobox"/></td>
                </tr>
				<tr>
				    <td></td>
				    <td></td>
					<td align="center">
						<a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-search',plain:true"	onclick="searchFun();">查询</a> 
				    	<a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-cancel',plain:true"	onclick="cleanFun();">重置</a>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'电子合同信息列表'">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>