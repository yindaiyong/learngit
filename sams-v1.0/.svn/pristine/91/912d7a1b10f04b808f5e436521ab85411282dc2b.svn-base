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
<title>代销端渠道产品关系管理</title>
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
     //渠道产品关系级联   
   	 $('#ciChannelCode').combobox({  
         onChange: function (row) {
        	 var channelCode = $("#ciChannelCode").combobox("getValue"); 
             $('#piChannelProductCode').combobox({
             	url: '${ctx}/combobox/queryUsedProductInfoComBox',
                onBeforeLoad:function(param){
                 	param.channelCode = channelCode;
	            },
	         valueField:'ID',
             textField:'NAME'
             });
         }
     });
		
		dataGrid = $('#dataGrid').datagrid({
							url : '${ctx}/channelProduct/getChannelProductData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'cpId',
							sortName : 'cp_ctime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'cpId',
								field : 'cpId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '渠道编号',
								field : 'cpChannelCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '渠道名称',
								field : 'ciChannelName',
								width : 300,
								halign : 'center',
								align : 'center',
								sortable : true
							} ]], 
							columns : [[{
								title : '渠道端产品编号',
								field : 'cpChannelProductCode',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '渠道端产品名称',
								field : 'piChannelProductName',
								width : 300,
								halign : 'center',
								align : 'center',
								sortable : true
							},{
								title : 'TA端产品编号',
								field : 'cpTaProductCode',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : 'TA端产品名称',
								field : 'cpTaProductName',
								width : 300,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '批次号',
								field : 'cpBatchNumber',
								width : 80,
								halign : 'center',
								align : 'center',
							} , {
								title : '复核状态',
								field : 'cpCheckState',
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
							},{
								title : '操作',
								field : 'cpAction',
								width : 80,
								halign : 'center',
								align : 'center'
								,formatter : function(value) {
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
							} , {
								title : '操作人',
								field : 'cpCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '操作时间',
								field : 'cpCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'cpUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '复核时间',
								field : 'cpUtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}  ] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								$("#dataGrid").datagrid('clearSelections'); 
							}
						});
  	});
	

	//产品渠道关联关系新增 
	 function addChannelProduct() {
	         parent.$.modalDialog({
	           title : '渠道产品管理新增',
	            width : 580,
	            height : 280,
	            resizable : false,
	            href : '${ctx}/channelProduct/addPage',
	            buttons : [ {
	                text : '提交',
	                iconCls : 'icon-save',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#addChannelProduct');
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
	 
	//产品渠道关联关系修改 
	 function editChannelProduct() {
		 var rows = dataGrid.datagrid('getSelections');
		 var cpId = rows[0].cpId;
		 var cpAction = rows[0].cpAction;
		 var cpCheckState = rows[0].cpCheckState;
	            if(rows.length!=1){
	            	$.messager.show({
	            		title:'温馨提示',
	            		msg:'请选择一条数据进行修改',
	            		timeout:3000,
	            		showType:'fade',
	            		style:{
	            			right:'',
	            			bottom:''
	            		}
	            	});
	            	return
	            }else{
                    checkProductCode(cpId,toEditPage)
                }
	    }

	    //判断产品编号是否有效
	    function checkProductCode(cpId,func) {
            //新增一次后台当前产品的有效性查询
            $.getJSON('${ctx}/channelProduct/productInfoGet?cpId='+cpId, null, function (result) {
                var z = result.msg;
                if(z == '1'){
                    parent.$.messager.confirm('询问', '选择的产品编号不存在，是否删除？', function(b) {
                        if (b) {
                            $.post('${ctx}/channelProduct/deleteErrorData', {
                                cpId : cpId
                            }, function(result) {
                                if (result.success) {
                                    parent.$.messager.alert('提示', result.msg, 'info');
                                    dataGrid.datagrid('reload');
                                }
                            }, 'JSON');
                        }
                    });
                }else{
                    func(cpId)
                }
            })
        }

	    //进入修改页面
	    function toEditPage(cpId) {
            parent.$.modalDialog({
                title : '渠道产品管理修改',
                width : 580,
                height : 280,
                resizable : false,
                href : '${ctx}/channelProduct/editPage?cpId=' + cpId,
                buttons : [ {
                    text : '提交',
                    iconCls : 'icon-save',
                    handler : function() {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;
                        var f = parent.$.modalDialog.handler.find('#editChannelProduct');
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
	 
	//产品渠道关联关系查看 
	 function viewChannelProduct() {
	            var rows = dataGrid.datagrid('getSelections');
	            var cpId = rows[0].cpId;
	            if(rows.length!=1){
	            	$.messager.show({
	            		title:'温馨提示',
	            		msg:'请选择一条数据进行查看',
	            		timeout:3000,
	            		showType:'fade',
	            		style:{
	            			right:'',
	            			bottom:''
	            		}
	            	});
	             return;
	            }else{
                    checkProductCode(cpId,toViewPage)
	            }
	    }
        //查询展示页面数据
	    function toViewPage(cpId) {
            parent.$.modalDialog({
                title : '渠道产品管理查看',
                width : 580,
                height : 280,
                resizable : false,
                href : '${ctx}/channelProduct/viewPage?cpId=' + cpId,
                buttons : [ {
                    text : '取消',
                    iconCls : 'icon-cancel',
                    handler : function() {
                        parent.$.modalDialog.handler.dialog('close');
                    }
                }  ]
            });

        }
	
	//渠道产品关联信息删除
		function delChannelProduct() {
			var rows = dataGrid.datagrid('getSelections');
			var cpId ;
        	var cpIds= []; 
        	var cpIds= new Array();  
			if (rows.length == 0) {
				$.messager.show({
					title : '提示',
					msg : '请选择需要删除的数据',
					timeout : 3000,
					showType : 'fade',
					style : {
						right : '',
						bottom : ''
					}
				});
			}else {
				for (var i = rows.length - 1; i >= 0; i--) {
					var cpCheckState = rows[i].cpCheckState;
					var cpValidFlag = rows[i].cpValidFlag;
					if(cpCheckState=='00'){
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
					  cpIds.push(rows[i].cpId);
					}
	            }
					
			}
			parent.$.messager.confirm('询问', '您确认删除选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelProduct/delSubmit', {
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
	 
	  //查询
	 function searchFun() {
         var parmFilter = $.serializeObject($('#searchForm'));
         dataGrid.datagrid('load', parmFilter);
     }
	 //重置 
	 function cleanFun() {
         $('#searchForm input').val('');
         dataGrid.datagrid('load', {});
     }
	 
</script>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'north',border:false,title:'查询区域'"
		align="center" style="height: 120px;">
        <form id="searchForm">
            <table align="center">
                <tr>
                    <th>渠道名称:</th>
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="ciChannelCode"   name ="filter_ciChannelCode"  data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME',required:false"></td>
                    <th>渠道端产品名称:</th>
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="piChannelProductCode"   name ="filter_piChannelProductCode"  data-options="
					url:'${ctx}/combobox/queryUsedProductInfoComBox',
					onBeforeLoad:function(param){
					        param.channelCode = '';
					},
					valueField:'ID',
					textField:'NAME',required:false"></td>
                    <th>复核状态:</th>
                    <td><input id="cpCheckState" name="filter_cpCheckState" class="easyui-combobox" style = "width:150px;" data-options="
					url:' ${ctx}/sysDict/checkStateGroup',
					valueField:'dictCode',
					textField:'dictName'"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'代销端渠道产品关系管理列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">关系新增</a>
            <a onclick="editChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">关系修改</a>
            <a onclick="viewChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">关系查看</a>
            <a onclick="delChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">关系删除</a>
            <a onclick="checkChannelProduct();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">关系复核</a>
    </div>
</body>
</html>