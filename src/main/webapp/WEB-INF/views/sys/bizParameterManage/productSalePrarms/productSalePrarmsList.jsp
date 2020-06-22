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
<title>代销端产品信息管理</title>
</head>
<script type="text/javascript">
	var dataGrid;
	$(function() {
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
	/* 	
   //初始化查询条件 
	 $('#pspChannelProductCode').combobox({
		 url:"${ctx}/combobox/queryAllProductInfoComBox", //访问controller的路径
		    valueField:'ID',
		    textField:'NAME',
		    multiple : false,
	        onShowPanel : function () {
	            $('#pspChannelProductCode').combobox('clear');
	            $('#pspChannelProductCode').combobox('reload','${ctx}/combobox/queryAllProductInfoComBox');
	        },
	 }); */
   
	//渠道产品关系级联     
	 $('#pspChannelCode').combobox({  
         onChange: function (row) {
        	 var channelCode = $("#pspChannelCode").combobox("getValue"); 
             $('#pspChannelProductCode').combobox({
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
							url : '${ctx}/productSalePrarms/getProductSalePrarmsData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'pspId',
							sortName : 'psp_ctime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'pspId',
								field : 'pspId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},{
								title : '渠道编号',
								field : 'pspChannelCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							},  {
								title : '代销端产品代码',
								field : 'pspChannelProductCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '币种',
								field : 'pspCurrencyType',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							},]], 
							columns : [[  {
								title : '启停状态',
								field : 'pspValidFlag',
								width : 80,
								halign : 'center',
								align : 'center',
								formatter : function(value) {
									if (value == "01") {
										return "已启用";
									}
									if (value == "00") {
										return "未启用";
									}
								}
							}, {
								title : '复核状态',
								field : 'pspCheckFlag',
								width : 140,
								halign : 'center',
								align : 'center',
								formatter : function(value) {
									if (value == "01") {
										return "已复核";
									}
									if (value == "00") {
										return "未复核";
									}
								}
							} ,{
								title : '操作人',
								field : 'pspCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '操作时间',
								field : 'pspCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							} ,{
								title : '复核人',
								field : 'pspUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '复合时间',
								field : 'pspUtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}   ] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								$("#dataGrid").datagrid('clearSelections'); 
							}
						});
  	});
	
  //多币种产品信息新增页面
	function addProductsaleParam() {
        parent.$.modalDialog({
           title : '多币种信息新增',
           resizable : false,
           width : 550,
           height: 520,
           href : '${ctx}/productSalePrarms/addPage',
           buttons : [ {
               text : '提交',
               iconCls : 'icon-save',
               handler : function() {
                   parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                   var f = parent.$.modalDialog.handler.find('#addProduct');
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
	 
	//多币种产品信息修改页面
	 function editProductsaleParam() {
		 var rows = dataGrid.datagrid('getSelections');
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
         	return 
         }else{
    		 pspId = rows[0].pspId;
        	 parent.$.modalDialog({
 	            title : '多币种信息修改',
 	            width : 552,
 	            height : 520,
 	            resizable : false,
 	            href : '${ctx}/productSalePrarms/editPage?pspId=' + pspId,
 	            buttons : [ {
 	                text : '提交',
 	                iconCls : 'icon-save',
 	                handler : function() {
 	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
 	                    var f = parent.$.modalDialog.handler.find('#editProduct');
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

	    }
	 
	
	//产品查看页面 
	 function viewProductsaleParam() {
	            var rows = dataGrid.datagrid('getSelections');
	            pspId = rows[0].pspId;
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
	            	return 
	            }else{
	            	 parent.$.modalDialog({
	     	            title : '多币种信息查看',
	     	            width : 552,
	     	            height : 520,
	     	            resizable : false,
	     	            href : '${ctx}/productSalePrarms/viewPage?pspId=' + pspId,
	     	            buttons : [ {
	     	                text : '取消',
	     	                iconCls : 'icon-cancel',
	     	                handler : function() {
	     	                	parent.$.modalDialog.handler.dialog('close');
	     	                }
	     	            } ]
	     	        });
	            }
	    }
	
	 //产品信息删除
	 function delProductsaleParam() {
			var rows = dataGrid.datagrid('getSelections');
			var pspId ;
        	var pspIds= []; 
        	var pspIds= new Array();  
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
				return;
			}else{
				for (var i = rows.length - 1; i >= 0; i--) {
					pspIds.push(rows[i].pspId);
				}
				
				parent.$.messager.confirm('询问', '您确认删除选中数据吗？', function(b) {
					if (b) {
						$.post('${ctx}/productSalePrarms/delete', {
							pspIds : JSON.stringify(pspIds)
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							}
						}, 'JSON');
					} 
				});
				
			}
			
		}
	 
	 //产品信息复核
	 function checkProductsaleParam() {
			var rows = dataGrid.datagrid('getSelections');
			var pspId ;
        	var pspIds= []; 
        	var pspIds= new Array();  
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
					var pspCuser = rows[i].pspCuser;
					if(pspCuser=='<shiro:principal/>'){
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
						pspIds.push(rows[i].pspId);	
					}
				}
			parent.$.messager.confirm('询问', '您确认复核选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/productSalePrarms/check', {
						pspIds : JSON.stringify(pspIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
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
					<th>代销渠道:</th>
					<td><input id="pspChannelCode" name="filter_pspChannelCode" class="easyui-combobox" style = "width:200px;" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'"/>
					</td>
                    <th>产品名称:</th>
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="pspChannelProductCode"   name ="filter_pspChannelProductCode" value = "" data-options="
					url:'${ctx}/combobox/queryUsedProductInfoComBox',
					onBeforeLoad:function(param){
					        param.channelCode = '';
					},
					valueField:'ID',
					textField:'NAME',required:false"/></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td></td>
                    <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">重置</a>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'多币种产品信息管理列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addProductsaleParam();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">多币种新增</a>
            <a onclick="editProductsaleParam();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">多币种修改</a>
            <a onclick="viewProductsaleParam();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">多币种查看</a>
            <a onclick="delProductsaleParam();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">多币种删除</a>
            <a onclick="checkProductsaleParam();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">多币种复核</a>
    </div>
</body>
</html>