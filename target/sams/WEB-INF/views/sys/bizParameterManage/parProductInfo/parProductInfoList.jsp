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
   //初始化查询条件 
	 /*  $('#piChannelProductCode').combobox({
		 url:"${ctx}/combobox/queryAllProductInfoComBox", //访问controller的路径
		    valueField:'ID',
		    textField:'NAME',
		    multiple : false,
	        onShowPanel : function () {
	            $('#piChannelProductCode').combobox('clear');
	            $('#piChannelProductCode').combobox('reload','${ctx}/combobox/queryAllProductInfoComBox');
	        },
	   });  */
	   
	 //渠道产品关系级联     
	 $('#piChannelCode').combobox({  
         onChange: function (row) {
        	 var channelCode = $("#piChannelCode").combobox("getValue"); 
             $('#piChannelProductCode').combobox({
             	url: '${ctx}/combobox/queryAllProductInfoComBox',
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
							url : '${ctx}/parProductInfo/getParProductInfoData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'piId',
							sortName : 'piCtime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'piId',
								field : 'piId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},{
								title : '渠道编号',
								field : 'piChannelCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							},  {
								title : '代销端产品代码',
								field : 'piChannelProductCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '代销端产品名称',
								field : 'piChannelProductName',
								width : 300,
								halign : 'center',
								align : 'center',
								sortable : true
							}]], 
							columns : [[ {
								hidden : 'true',
								field : 'piProductType',
								width : 300,
								halign : 'center',
								align : 'center',
                                sortable : true
							} ,{
								title : '产品类型',
								field : 'productTypeName',
								width : 300,
								halign : 'center',
								align : 'center',
                                sortable : true
							},{
								title : '募集开始日',
								field : 'piIpoBeginDate',
								width : 80,
								halign : 'center',
								align : 'center',
                                sortable : true
							}, {
								title : '募集结束日',
								field : 'piIpoEndDate',
								width : 80,
								halign : 'center',
								align : 'center',
                                sortable : true
							},  {
								title : '产品成立日',
								field : 'piProSetupDate',
								width : 80,
								halign : 'center',
								align : 'center',
                                sortable : true
							},  {
								title : '产品到期日',
								field : 'piProEndDate',
								width : 80,
								halign : 'center',
								align : 'center',
                                sortable : true
							}, {
								title : '批次号',
								field : 'piBatchNumber',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '启停状态',
								field : 'piValidFlag',
								width : 80,
								halign : 'center',
								align : 'center'
								,formatter : function(value) {
									if (value == "00") {
										return "产品到期";
									}
									if (value == "99") {
										return "已停用";
									}
									if (value == "01") {
										return "已启用";
									}
									if (value == "02") {
										return "到期继续发送行情";
									}
									if (value == "10") {
										return "已停用";
									}
								}
							},{
								title : '复核状态',
								field : 'piCheckState',
								width : 80,
								halign : 'center',
								align : 'center'
								,formatter : function(value) {
									if (value == "00") {
										return "未复核";
									}
									if (value == "01") {
										return "已复核";
									}
								}
							}, {
								title : '操作',
								field : 'piAction',
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
										return "停用";
									}
									if (value == "05") {
										return "启用";
									}
									if (value == "06") {
										return "复制";
									}
								}
							},{
								title : '操作人',
								field : 'piCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '操作时间',
								field : 'piCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'piUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核时间',
								field : 'piUtime',
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
	
  //产品新增页面
	function addProductInfo() {
        parent.$.modalDialog({
           title : '产品信息新增',
           resizable : false,
           width : 500,
           height: 320,
           href : '${ctx}/parProductInfo/addPage',
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
	 
	//产品修改页面 
	 function editProductInfo() {
		 var rows = dataGrid.datagrid('getSelections');
		 var piId = rows[0].piId;
		 var piValidFlag = rows[0].piValidFlag;
		 var piCheckState =rows[0].piCheckState;
		 var oriIpoBeginDate =rows[0].piIpoBeginDate;
		/*  var oriIpoBeginDate = $("#piIpoBeginDate").val(); */
		    //获取当前时间的年月日 yyyy-mm-dd
         var date = new Date(); 
         var y = date.getFullYear()
         var m = date.getMonth() + 1
         m = m < 10 ? '0' + m : m
         var d = date.getDate()
         d = d < 10 ? ('0' + d) : d
         var currTime=y+m+d;
		  piAction =rows[0].piAction;
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
	            }
	        /* else if(piValidFlag =='01'){
	        	$.messager.show({
            		title:'温馨提示',
            		msg:'启用状态无法修改',
            		timeout:3000,
            		showType:'fade',
            		style:{
            			right:'',
            			bottom:''
            		}
            	});
	        	return 
	        } else if(piAction !="01" && piAction !="02" && piAction !="06"){
	        	if(piCheckState =='00'){
	        		$.messager.show({
	            		title:'温馨提示',
	            		msg:'未复核状态无法修改',
	            		timeout:3000,
	            		showType:'fade',
	            		style:{
	            			right:'',
	            			bottom:''
	            		}
	            	});
		        	return 	
	        	}
	        }*/
	           if(oriIpoBeginDate<=currTime){
	        	parent.$.messager.confirm('询问', '该产品已经开始募集，是否继续修改？', function(b) {
					if (b) {
                        openToEdit(piId);
					} 
				});
	            }else{
                   openToEdit(piId)
                }
	
	    }
	    //修改信息页面弹窗
	    function openToEdit(piId){
            parent.$.modalDialog({
                title : '代销端产品信息修改',
                width : 552,
                height : 580,
                resizable : false,
                href : '${ctx}/parProductInfo/editPage?piId=' + piId,
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
	//产品信息复制 
	 function copyProductInfo() {
	            var rows = dataGrid.datagrid('getSelections');
	            piId = rows[0].piId;
	            if(rows.length!=1){
	            	$.messager.show({
	            		title:'温馨提示',
	            		msg:'请选择一条数据进行复制',
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
	    	            title : '代销端产品信息复制',
	    	            width : 552,
	    	            height : 580,
	    	            resizable : false,
	    	            href : '${ctx}/parProductInfo/copyPage?piId=' + piId,
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
	 function viewProductInfo() {
	            var rows = dataGrid.datagrid('getSelections');
	            piId = rows[0].piId;
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
	     	            title : '代销端产品信息查看',
	     	            width : 552,
	     	            height : 580,
	     	            resizable : false,
	     	            href : '${ctx}/parProductInfo/viewPage?piId=' + piId,
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
	 function delProductInfo() {
			var rows = dataGrid.datagrid('getSelections');
			var piId ;
        	var piIds= []; 
        	var piIds= new Array();  
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
					 var piValidFlag = rows[i].piValidFlag;
					 var piCheckState = rows[i].piCheckState;
					 if(piValidFlag=='01'){
							$.messager.show({
								title : '提示',
								msg : '启用状态无法删除',
								timeout : 3000,
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							return; 
						}else if(piValidFlag !='10' || piCheckState !='01'){
							$.messager.show({
								title : '提示',
								msg : '只有停用已复核才能删除',
								timeout : 3000,
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							return; 
						}else{
							piIds.push(rows[i].piId);
						}
		         }
			}
			parent.$.messager.confirm('询问', '您确认删除选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/parProductInfo/delete', {
						piIds : JSON.stringify(piIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});		
		}
	 
	 //产品信息复核 
		function checkProductInfo() {
			var rows = dataGrid.datagrid('getSelections');
			var piId ;
        	var piIds= []; 
        	var piIds= new Array();  
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
					var piCuser = rows[i].piCuser;
					if(piCuser=='<shiro:principal/>'){
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
		                piIds.push(rows[i].piId);
		             }
                }
			}
			parent.$.messager.confirm('询问', '您确认复核选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/parProductInfo/checkSubmit', {
						piIds : JSON.stringify(piIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});		
		}
	 
		//产品信息暂停
		 function stopProductInfo() {
			    var rows = dataGrid.datagrid('getSelections');
				var piId ;
	        	var piIds= []; 
	        	var piIds= new Array(); 
				if (rows.length == 0) {
					$.messager.show({
						title : '提示',
						msg : '请选择需要停用的数据',
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
						var piCheckState = rows[i].piCheckState;
						if(piCheckState!='01'){
							$.messager.show({
								title : '提示',
								msg : '未复核状态无法停用',
								timeout : 3000,
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							return 
						}else{
							piIds.push(rows[i].piId);
						}
		            }
				}
				parent.$.messager.confirm('询问', '您确认停用选中数据吗？', function(b) {
					if (b) {
						$.post('${ctx}/parProductInfo/stopSubmit', {
							piIds : JSON.stringify(piIds)
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							}
						}, 'JSON');
					} 
				});		
		 }
		
		//产品信息启用 
		 function startProductInfo() {
			 var rows = dataGrid.datagrid('getSelections');
				var piId ;
	        	var piIds= []; 
	        	var piIds= new Array(); 
				if (rows.length == 0) {
					$.messager.show({
						title : '提示',
						msg : '请选择需要启用的数据',
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
						var piCheckState = rows[i].piCheckState;
						if(piCheckState!='01'){
							$.messager.show({
								title : '提示',
								msg : '未复核状态无法启用',
								timeout : 3000,
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
							return 
						}else{
							piIds.push(rows[i].piId);
						}
		            }
				}
				parent.$.messager.confirm('询问', '您确认启用选中数据吗？', function(b) {
					if (b) {
						$.post('${ctx}/parProductInfo/startSubmit', {
							piIds : JSON.stringify(piIds)
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
					<th>代销渠道:</th>
					<td><input id="piChannelCode" name="filter_piChannelCode" class="easyui-combobox" style = "width:200px;" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME'"/>
					</td>
                    <th>产品名称:</th>
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="piChannelProductCode"   name ="filter_piChannelProductCode" value = ""  data-options="
					     url:'${ctx}/combobox/queryAllProductInfoComBox',
					     onBeforeLoad:function(param){
					        param.channelCode = '';
					     },
					     valueField:'ID',
					     textField:'NAME',
					     "/></td>
                    <th>产品类型:</th>
                    <td><input class="easyui-combobox"  style = "width:250px;"  id="piProductType"   name ="filter_piProductType" value = "" data-options="
		                    url:'${ctx}/sysDict/productTypeGroup',
		                    valueField: 'dictCode',
		                    textField: 'dictName'"/>
		            </td>
                </tr>
                <tr>
                   <th>启用状态:</th>
                    <td><input id="piValidFlag" name="filter_piValidFlag" class="easyui-combobox" style = "width:150px;" data-options="
		                    valueField: 'label',
		                    textField: 'value',
		                    data: [{
			                        label: '10',
			                        value: '已停用'
		                         },{
			                        label: '01',
			                        value: '已启用'
		                         },{
			                        label: '02',
			                        value: '到期继续发送行情'
			                     },{
			                        label: '00',
			                        value: '产品到期'
		                    }]"/></td>
                    <th>复核状态:</th>
                    <td><input id="piCheckState" name="filter_piCheckState" class="easyui-combobox" style = "width:150px;" data-options="
					url:' ${ctx}/sysDict/checkStateGroup',
					valueField:'dictCode',
					textField:'dictName'"/></td>
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
    <div data-options="region:'center',border:true,title:'代销端产品信息管理列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">产品新增</a>
            <a onclick="editProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">产品修改</a>
            <a onclick="viewProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">产品查看</a>
            <a onclick="delProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">产品删除</a>
            <a onclick="checkProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">产品复核</a>
            <a onclick="stopProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">产品停用</a>
            <a onclick="startProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">产品启用</a>
            <a onclick="copyProductInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">复制</a>
    </div>
</body>
</html>