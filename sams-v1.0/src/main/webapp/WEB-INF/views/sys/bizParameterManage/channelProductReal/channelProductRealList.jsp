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
<title>产品收益率设置</title>
</head>
<script type="text/javascript">
	var dataGrid;
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
		
		//初始化
		 $('#cprCheckState').combobox({
		     multiple: true,
		     panelHeight: 'auto',//自适应
		     valueField: 'id',//绑定字段ID
		     textField: 'name',//绑定字段Name
		     onLoadSuccess:function(){
		         $(".combo").click(function(){
		                  $(this).prev().combobox("showPanel");
		        });
		    },
		     data:[{
		         "id":"00",
		         "name":"未复核"
		     },{
		         "id":"01",
		         "name":"已复核"
		     }],
		     formatter: function (row) {
		         var opts = $(this).combobox('options');
		         return '<input type="checkbox" class="combobox-checkbox" style="margin:0 5px;vertical-align: -2px" id="' + row[opts.valueField] + '">' + row[opts.textField]
		     },
		   //获取数据URL 
		     //选择树节点触发事件 
		     onSelect: function (row) {
		         var opts = $(this).combobox('options');
		         var el = opts.finder.getEl(this, row[opts.valueField]);
		         el.find('input.combobox-checkbox')._propAttr('checked', true);
		     },
		     onUnselect: function (row) {
		         var opts = $(this).combobox('options');
		         var el = opts.finder.getEl(this, row[opts.valueField]);
		         el.find('input.combobox-checkbox')._propAttr('checked', false);
		     }
		 }) ;
		 //渠道产品关系级联   
		 $('#filter_cprChannelCode').combobox({  
	         onChange: function (row) {
	        	 var channelCode = $("#filter_cprChannelCode").combobox("getValue"); 
                 $('#filter_cprFundCode').combobox({
                 	url: '${ctx}/combobox/queryUsedProductInfoComBox',
                     onBeforeLoad:function(param){
                     	param.channelCode = channelCode;
					 },
					valueField:'ID',
				    textField:'NAME'
                 });
	         }
	     });
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/channelProductReal/getchannelProductRealData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'cprId',
							sortName : 'cpr_ctime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field :'ck',
								checkbox : 'true',
							}, {
								title : 'cprId',
								field : 'cprId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},{
								title : 'piId',
								field : 'piId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '代销端产品代码',
								field : 'cprFundCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '代销端产品名称',
								field : 'piChannelProductName',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '渠道编号',
								field : 'cprChannelCode',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
							}]], 
							columns : [[{
								title : '收益级别',
								field : 'cprSectionNumber',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '产品收益率',
								field : 'cprFundRate',
								width : 80,
								halign : 'center',
								align : 'center',
								sortable : true,
							} , {
								title : '起始金额',
								field : 'cprStartMoney',
								width : 200,
								halign : 'center',
								align : 'center'
							},{
								title : '终止金额',
								field : 'cprEndMoney',
								width : 200,
								halign : 'center',
								align : 'center',
								sortable : true,
							}, {
								title : '批次号',
								field : 'cprBatchNumber',
								width : 80,
								halign : 'center',
								align : 'center',
								sortable : true,
							},{
								title : '复核状态',
								field : 'cprCheckState',
								width : 100,
								halign : 'center',
								align : 'center',
								formatter : function(value) {
									if (value == "00") {
										return "未复核";
									}
									if (value == "01") {
										return "已复核";
									}
								}
							}, {
								title : '操作',
								field : 'cprAction',
								width : 80,
								halign : 'center',
								align : 'center',
								formatter : function(value) {
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
										return "暂停";
									}
									if (value == "05") {
										return "启用";
									}
								}
							}, {
								title : '操作人',
								field : 'cprCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '操作时间',
								field : 'cprCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'cprUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核时间',
								field : 'cprUtime',
								width : 140,
								halign : 'center',
								align : 'center'
							} ] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								$("#dataGrid").datagrid('clearSelections'); 
							}
						});
  	})
	
     //产品收益率设置新增
	 function addChannelProduct() {
	         parent.$.modalDialog({
	           title : '产品收益率设置新增',
	            width : 800,
	            height : 280,
	            resizable : false,
	            href : '${ctx}/channelProductReal/addPage',
	            buttons : [ {
	            	text : '确认',
					iconCls : 'icon-save',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#addChannelProductReal');
	                    f.submit(); 
	                }
	            },{
	            	text : '取消',
	            	iconCls : 'icon-cancel',
	                handler : function() {
	                	parent.$.modalDialog.handler.dialog('close');
	                }
	            } ]
	        }); 
	    }
	 
	 //产品收益率设置修改
	 function editChannelProduct() {
			var rows = dataGrid.datagrid('getSelections');
			var cprId = rows[0].cprId;
			var piId = rows[0].piId;
			var cprAction = rows[0].cprAction;
			var cprcheckState = rows[0].cprcheckState;
			if (rows.length !=1) {
				$.messager.show({
					title : '提示',
					msg : '请选择一条数据进行修改',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			} else if(cprAction !='01'){
				if(cprcheckState !='01'){
					$.messager.show({
						title : '提示',
						msg : '收益率未复核无法修改',
						timeout : 3000,
						showType : 'fade',
						style : {
							right : '',
							bottom : ''
						}
					});
					return;
				}
			}
		        parent.$.modalDialog({
		            title : '产品收益率设置修改',
		            width : 800,
		            height : 280,
		            resizable : false,
		            href : '${ctx}/channelProductReal/editPage?cprId=' + cprId+'&piId='+piId,
		            buttons : [ {
		            	text : '确认',
						iconCls : 'icon-save',
		                handler : function() {
		                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
		                    var f = parent.$.modalDialog.handler.find('#editChannelProductReal');
		                    f.submit(); 
		                }
		            },{
		            	text : '取消',
		            	iconCls : 'icon-cancel',
		                handler : function() {
		                	parent.$.modalDialog.handler.dialog('close');
		                }
		            } ]
		        });
	    }
	 
	//产品收益率设置查看
	 function viewChannelProduct() {
		 var rows = dataGrid.datagrid('getSelections');
			if (rows.length !=1) {
				$.messager.show({
					title : '提示',
					msg : '请选择一条数据进行查看',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			} else {
				var cprId = rows[0].cprId;
				var piId = rows[0].piId;
		        parent.$.modalDialog({
		            title : '产品收益率设置查看',
		            width : 800,
		            height : 280,
		            resizable : false,
		            href : '${ctx}/channelProductReal/viewPage?cprId=' + cprId+'&piId='+piId,
		            buttons : [ {
		                text : '取消',
		                iconCls : 'icon-cancel',
		                handler : function() {
		                	parent.$.modalDialog.handler.dialog('close');
		                }
		            }  ]
		        });
			}
	    }
		//渠道产品信息复核 
		function checkChannelProduct() {
			
			var rows = dataGrid.datagrid('getSelections');
			var cpId ;
     	var cpIds= []; 
     	var cpIds= new Array();  
			if (rows.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要复核的数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
				return;
			}else{
				for (var i = rows.length - 1; i >= 0; i--) {
					var cpCuser = rows[i].cpCuser;
					if(cpCuser=='<shiro:principal/>'){
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
					 }else{
						cpIds.push(rows[i].cpId); 
					 }
             }
			}
			 parent.$.messager.confirm('询问', '您确认复核选中数据吗？', function(b) {
				 if (b) {
						$.post('${ctx}/channelProduct/checkSubmit', {
							cpIds : JSON.stringify(cpIds)
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							}
						}, 'JSON');
					} 		
			 });
		}
	 
	//产品收益率设置删除
	 function delChannelProduct() {
		 var rows = dataGrid.datagrid('getSelections');
		 var cprId ;
	     var cprIds= []; 
	     var cprIds= new Array();  
			if (rows.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择一条数据进行修改',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			} else {
				for (var i = rows.length - 1; i >= 0; i--) {
					var cprCheckState = rows[i].cprCheckState;
					if(cprCheckState=='00'){
						$.messager.show({
							title : '提示',
							msg : '未复核数据不允许删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return;
					}else{
						cprIds.push(rows[i].cprId); 
					 }
             }
			}
			 parent.$.messager.confirm('询问', '您是否要删除当前收益率？', function(b) {
				 if (b) {
						$.post('${ctx}/channelProductReal/delSubmit', {
							cprIds : JSON.stringify(cprIds)
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							}
						}, 'JSON');
					}    
		      });
	    }
	 
	//产品收益率设置复核 
	 function checkChannelProduct() {
		 var rows = dataGrid.datagrid('getSelections');
		 var cprId ;
	     var cprIds= []; 
	     var cprIds= new Array();  
			if (rows.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择一条数据进行复核',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}else{
				for (var i = rows.length - 1; i >= 0; i--) {
					var cprId = rows[i].cprId;
					var cprCuser = rows[i].cprCuser;
					if(cprCuser=='<shiro:principal/>'){
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
					}else{
						cprIds.push(rows[i].cprId);
					}
				}
			} 
			parent.$.messager.confirm('询问', '您是否要复核当前收益率？', function(b) {
				if (b) {
					$.post('${ctx}/channelProductReal/checkSubmit', {
						cprIds : JSON.stringify(cprIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
	        });    
	    }
	//查询
	 function searchFun() {
         var parmFilter = $.serializeObject($('#searchForm'));
         dataGrid.datagrid('load', parmFilter);
     }
	 
	//重置 
	 function cleanFun() {
		 $('#searchForm').form('reset');
         dataGrid.datagrid('load', {});
     }
	 
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
        <form id="searchForm">
            <table data-options="fit:true,border:false"
				style="padding-top: 10px;">
                <tr>
                    <td>
                   	 渠道名称:
                   	 <input class="easyui-combobox"  style = "width:250px;" prompt="请选择"  id="filter_cprChannelCode"   name ="filter_cprChannelCode" value = "" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',required:false" />
					 产品名称:
					<input class="easyui-combobox"  style = "width:250px;" prompt="请选择"  id="filter_cprFundCode"   name ="filter_cprFundCode" value = "" data-options="
					     url:'${ctx}/combobox/queryUsedProductInfoComBox' ,
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',required:false" />
					 复核状态:
					 <select id="filter_cprCheckState" name="filter_cprCheckState" style="width: 150px"
						class="easyui-combobox">
							<option value=''>请选择</option>
							<option value='00'>未复核</option>
							<option value='01'>已复核</option>
						</select>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'产品收益率设置列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">收益率新增</a>
            <a onclick="editChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">收益率修改</a>
            <a onclick="viewChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">收益率查看</a>
            <a onclick="delChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">收益率删除</a>
            <a onclick="checkChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">收益率复核</a>
    </div>
</body>
</html>