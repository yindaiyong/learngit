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
<title>代销端渠道信息管理</title>
</head>
<title>交易信息</title>
<script type="text/javascript">
var transDataGrid = "";
var transType = "";
	$(function() {
		 //渠道产品关系级联     created by wangchao 20200610 渠道产品级联关系设置 
		 $('#trans-channel-info').combobox({  
	         onChange: function (row) {
	        	 var channelCode = $("#trans-channel-info").combobox("getValue"); 
	             $('#trans-channel-product').combobox({
	             	url: '${ctx}/combobox/queryAllProductInfoComBox',
	                onBeforeLoad:function(param){
	                 	param.channelCode = channelCode;
		            },
					valueField:'ID',
				    textField:'NAME'
	             });
	         }
	     });
		
		transType = '${type}';
		var url = '${ctx}/fileDataUpdate/queryFalseFileData';
		transDataGrid = $('#transDetailData')
				.datagrid({
					url : url,
					striped : true,
					singleSelect:false,
					rownumbers : true,
					pagination : true,
					pageSize : 10,
					pageList : [10,20, 40, 50, 100, 200, 300,
							400, 500 ],
					queryParams:{
						filter_type:transType},
					columns : [[{
						field :'ck',
						checkbox : 'true',
					},{
						field : 'ID',
						width : 100,
						halign : 'center',
						align : 'center',
						hidden: 'true'
					},{
                        title : '交易结果',
                        field : 'ERRORDEC',
                        width : 450,
                        halign : 'center',
                        align : 'center',
                        sortable : true,
                        formatter : function(value){
                            if(typeof(value)!="undefined" && value != null){
                                return "<span title='" + value + "'>" + value + "</span>";
                            }else{
                                return '';
                            }

                        }
                    },{
						title : '渠道编码',
						field : 'CHANNELCODE',
						width : 100,
						halign : 'center',
						align : 'center',
                        sortable : true
					},{
						title : '渠道名称',
						field : 'CHANNELNAME',
						width : 100,
						halign : 'center',
						align : 'center',
                        sortable : true
					},{
						title : '基金代码',
						field : 'FUNDCODE',
						width : 100,
						halign : 'center',
						align : 'center',
						sortable : true
					},{
						title : '申请单编号',
						field : 'APPSHEETSERIALNO',
						width : 200,
						halign : 'center',
						align : 'center',
						sortable : true
					},{
						title : '投资人姓名',
						field : 'INVESTORNAME',
						width : 140,
						halign : 'center',
						align : 'center',
						sortable : true
					},{
						title : '投资人证件号',
						field : 'CERTIFICATENO',
						width : 200,
						halign : 'center',
						align : 'center',
						sortable : true,
                        formatter : function(value) {
                            if(value != '' && value != null && value.length > 8){
                                return value.replace(/^(.{4})(?:\w+)(.{4})$/, "$1****$2");
                            }else{
                                return value;
                            }
                        }
					},{
						title : '投资人基金交易账号',
						field : 'TRANSACTIONAACOUNTID',
						width : 200,
						halign : 'center',
						align : 'center',
						sortable : true
					},{
						title : '基金账号',
						field : 'TAACCOUNTID',
						width : 100,
						halign : 'center',
						align : 'center',
						sortable : true
					},{
						title : '交易类型',
						field : 'BUSINESSCODE',
						width : 100,
						halign : 'center',
						align : 'center',
                        sortable : true
					},{
						title : '交易发生日期',
						field : 'TRANSACTIONDATE',
						width : 100,
						halign : 'center',
						align : 'center',
                        sortable : true
					},{
						title : '交易币种',
						field : 'CURRENCYTYPE',
						width : 100,
						halign : 'center',
						align : 'center',
                        sortable : true,
						formatter : function(value){
                            if(typeof(value)!="undefined" && value != null){
                                if("156" == value)return value+"(人民币)";
                                if("840" == value)return value+"(美元)";
                                return value+"(暂不支持)";
                            }else{
                                return "(暂不支持)";
                            }

						}
						
					},{
						title : '交易金额',
						field : 'APPLICATIONAMOUNT',
						width : 100,
						halign : 'center',
						align : 'center',
                        sortable : true
					},{
						title : '合同号',
						field : 'OUTCONTRACT',
						width : 200,
						halign : 'center',
						align : 'center',
                        sortable : true
					},{
						field : 'RETURNCODE',
						width : 200,
						halign : 'center',
						align : 'center',
						hidden: 'true'
					}
					]],
					toolbar : '#toolbar',//加载成功
					onLoadSuccess: function (data) {
						//去掉标题上的复选框
					   /* $(".datagrid-header-check").html(""); */
					   //重新加载时候去掉已选中数据 
					   $("#dataGrid").datagrid('clearSelections'); 
					   var transReturnCode = $("#trans_returnCode").combobox("getValue"); 
	      			   if(transReturnCode !='0000'){
	         				$('#return').hide();
	         				$('#update').show();
	      			   }else{
	         				$('#return').show();
	         				$('#update').hide();
	      			   }
					}
						});
		//给combobox赋值
		$("#trans_returnCode").combobox({
		     panelHeight: 'auto',//自适应
		     valueField: 'id',//绑定字段ID
		     textField: 'name',//绑定字段Name
		     onLoadSuccess:function(){
		         $(".combo").click(function(){
		            $(this).prev().combobox("showPanel");
		        });
		     },
		     data :[{
		         "id":"0000",
		         "name":"成功"
		     },{
		         "id":"9999",
		         "name":"失败",
		         "selected":true
		     }]
		});
		
		$('#trans_returnCode').combobox({
      		 onSelect: function (row) { 
      			 var transReturnCode=row.id;
      			 if(transReturnCode !='0000'){
      				$('#return').hide();
      				$('#update').show();
      			 }else{
      				$('#return').show();
      				$('#update').hide();
    			   }
               }  
           });
});	
	
	 //查询
	function transSearch (){
		var parmFilter = $.serializeObject($('#trans-searchForm'));
		transDataGrid.datagrid('load', parmFilter);
	}
	 
	//重置
	function clean(){
		$('#trans-searchForm').form('reset');
		transDataGrid.datagrid('load', {filter_type : "1"});
	}
	 
	    //交易通过处理 
		function updateTransData() {
	    	var rows = $('#transDetailData').datagrid('getSelections');
	    	var Ids= []; 
        	var Ids= new Array();
        	var type = '01';
			/* if (rows.length != 1) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要通过的单条数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			} */
			//created by wangchao 20200423 交易失败的可以批量通过
			for (var i = rows.length - 1; i >= 0; i--) {
				var returnode = rows[0].RETURNCODE;
				if (returnode == "0000") { 
					$.messager.show({
						title : '提示',
						msg : '只有交易处理失败才可通过',
						timeout : 3000,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					return;
				}else{
					Ids.push(rows[i].ID);
				}
            }
			parent.$.messager.confirm('询问', '您确认通过选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/fileDataUpdate/updateFileData', {
						Ids : JSON.stringify(Ids),
						type :type
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							$('#transDetailData').datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
	    
		//交易回退处理 
		function returnBackTransData() {
	    	var rows = $('#transDetailData').datagrid('getSelections');
	    	var Ids= []; 
        	var Ids= new Array();
        	var type = '01';
	    	/* if (rows.length != 1) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要通过的单条数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			} */
			//created by wangchao 20200423 交易失败的可以批量通过

			var returnode = rows[0].RETURNCODE;
			for (var i = rows.length - 1; i >= 0; i--) {
				var returnode = rows[0].RETURNCODE;
				if (returnode != "0000") { 
					$.messager.show({
						title : '提示',
						msg : '只有交易处理成功才可回退',
						timeout : 3000,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					return;
				}else{
					Ids.push(rows[i].ID);
				}
            }
			
			parent.$.messager.confirm('询问', '您确认回退选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/fileDataUpdate/returnBackTransData', {
						Ids : JSON.stringify(Ids),
						type :type
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							$('#transDetailData').datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
	 
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
	 <form id="trans-searchForm">
	 <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
    <tr>
         <td align="center">
			 查询渠道:<input class="easyui-combobox"  prompt="请选择..." style = "width:200px;"  id="trans-channel-info"   name ="filter_channelCode" value = "${channelCode }" data-options="
								url:'${ctx}/combobox/queryChannelInfo',
								valueField:'ID',
								textField:'NAME'" >
			查询产品:<input class="easyui-combobox"  style = "width:200px;"  id="trans-channel-product"   name ="filter_productCode" data-options="
								url:'${ctx}/combobox/queryAllProductInfoComBox',
					            onBeforeLoad:function(param){
					                 param.channelCode = '';
					            },
					            valueField:'ID',
					            textField:'NAME'" >
								<input type = "hidden" value = "${type }" name = "filter_type"/>
			投资人基金交易账户:<input class="easyui-textbox" style = "width:200px;" id = "transactionAacountId" name = "filter_transactionAacountId"/>
   	     
   	      </td>
    </tr>
    
    <tr>
         <td align="center">
		   	查询日期:<input style= "width:160px;" class="easyui-datebox" id="trans-failed-Date" name="filter_transDate"/>
		   	处理结果:<input style= "width:160px;" class="easyui-combobox" id="trans_returnCode" name="filter_returnCode"/>
                                    交易类型:<input class="easyui-combobox"  prompt="请选择..." style = "width:200px;"  id="trans-business-code"   name ="filter_businessCode"  data-options="
								url:'${ctx}/sysDict/businessCodeGroup',
								valueField:'dictCode',
								textField:'dictName'" >   	     
   	     </td>
   	</tr>
   	
   	<tr><td align="center">
       <a id = "trans-Data-search" onclick="transSearch();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" iconCls="icon-search">查询</a>
       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clean();">重置</a>
       </td>
    </tr>
   </table>
 </form>
    </div>
    <div data-options="region:'center',border:true,title:'交易失败数据查询'" >
   <table id="transDetailData" data-options="fit:true,border:false"></table>
</div>
    <div id="toolbar" style="display: none;">
            <a id="update"  onclick="updateTransData();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">交易通过</a>
            <a id="return"  onclick="returnBackTransData();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">交易回退</a>
    </div>
</body>
</html>