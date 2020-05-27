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
		
		//初始化查询条件 
		 $('#ciChannelCode').combobox({
			 url:"${ctx}/combobox/queryAllChannelInfo", //访问controller的路径
			    valueField:'ID',
			    textField:'NAME',
			    multiple : false,
		        onShowPanel : function () {
		            $('#ciChannelCode').combobox('clear');
		            $('#ciChannelCode').combobox('reload','${ctx}/combobox/queryAllChannelInfo');
		        },
		 });
		
		 /* $('#ciChannelName').combobox({
			 url:"${ctx}/combobox/queryChanneleNameComBox", //访问controller的路径
			    valueField:'NAME',
			    textField:'NAME',
			    multiple : false,
		        onShowPanel : function () {
		            $('#ciChannelName').combobox('clear');
		            $('#ciChannelName').combobox('reload','${ctx}/combobox/queryChanneleNameComBox');
		        },
		 }); */
		 
		 $('#ciMarketCode').combobox({
			 url:"${ctx}/sysDict/marketCodeGroup", //访问controller的路径
			    valueField:'dictCode',
			    textField:'dictName',
			    multiple : false,
		        onShowPanel : function () {
		            $('#ciMarketCode').combobox('clear');
		            $('#ciMarketCode').combobox('reload','${ctx}/sysDict/marketCodeGroup');
		        },
		 });
		 
		 $('#ciCheckState').combobox({
			 url:"${ctx}/sysDict/checkStateGroup", //访问controller的路径
			    valueField:'dictCode',
			    textField:'dictName',
			    multiple : false,
		        onShowPanel : function () {
		            $('#ciCheckState').combobox('clear');
		            $('#ciCheckState').combobox('reload','${ctx}/sysDict/checkStateGroup');
		        },
		 });
		dataGrid = $('#dataGrid')
				.datagrid({
							url : '${ctx}/channelInfo/getChannelInfoData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'ciId',
							sortName : 'ci_ctime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'ciId',
								field : 'ciId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '渠道编号',
								field : 'ciChannelCode',
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
							}, {
								title : '渠道业务经理',
								field : 'ciBizManagerName',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
							}]], 
							columns : [[ {
								title : '渠道运维经理',
								field : 'ciOpsManagerName',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '市场类型',
								field : 'ciMarketCode',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
								formatter : function(value) {
									if (value == "001") {
										return "银行市场";
									}
									if (value == "002") {
										return "债券市场";
									}
								}
							} ,{
								title : '接口版本',
								field : 'ciCsdcVer',
								width : 140,
								halign : 'center',
								align : 'center'
							},/* {
								title : '启停状态',
								field : 'ciValidFlag',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
								formatter : function(value) {
									if (value == "00") {
										return "已停用";
									}
									if (value == "10") {
										return "已停用";
									}
									if (value == "01") {
										return "已启用";
									}
								}
							}, */{
								title : '复核状态',
								field : 'ciCheckState',
								width : 140,
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
								field : 'ciAction',
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
								}
							}, {
								title : '操作人',
								field : 'ciCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '操作时间',
								field : 'ciCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'ciUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核时间',
								field : 'ciUtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}] ],
							toolbar : '#toolbar',
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								$("#dataGrid").datagrid('clearSelections'); 
							}
						});
  	});
	
	//渠道信息新增
	 function addChannelInfo() {
	         parent.$.modalDialog({
	            title : '代销端渠道信息新增',
	            width : 552,
	            height : 580,
	            resizable : false,
	            href : '${ctx}/channelInfo/addPage',
	            buttons : [ {
	                text : '提交',
	                iconCls : 'icon-save',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#addChannelInfo');
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
	 
	//渠道信息修改
	 function editChannelInfo(ciId) {
		 var rows = dataGrid.datagrid('getSelections');
		 ciId = rows[0].ciId;
		 ciValidFlag = rows[0].ciValidFlag;
		 ciCheckState = rows[0].ciCheckState;
		 ciAction = rows[0].ciAction;;
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
	/*         else if(ciValidFlag =='01'){
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
	        } else if(ciAction !="01" && ciAction !="02"){
	        	if(ciCheckState =='00'){
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
	        } */
	        parent.$.modalDialog({
	            title : '代销端渠道信息修改',
	            width : 552,
	            height : 580,
	            resizable : false,
	            href : '${ctx}/channelInfo/editPage?ciId=' + ciId,
	            buttons : [ {
	            	text : '提交',
	            	iconCls : 'icon-save',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#editChannelInfo');
	                    f.submit(); 
	                }
	            },{
	                text : '取消',
	                iconCls : 'icon-cancel',
	                handler : function() {
	               	    parent.$.modalDialog.handler.dialog('close');
	                }
	            }  ]
	        });
	    }
	 
	//渠道信息查看
	 function viewChannelInfo(ciId) {
	            var rows = dataGrid.datagrid('getSelections');
	            ciId = rows[0].ciId;
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
	    	            title : '代销端渠道信息查看',
	    	            width : 552,
	    	            height : 580,
	    	            resizable : false,
	    	            href : '${ctx}/channelInfo/viewPage?ciId=' + ciId,
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
	 
	   //渠道信息删除
		function delChannelInfo() {
			var rows = dataGrid.datagrid('getSelections');
			var ciId ;
        	var ciIds= []; 
        	var ciIds= new Array();  
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
				return
			}else{
				for (var i = rows.length - 1; i >= 0; i--) {
	            	var ciValidFlag = rows[i].ciValidFlag;
	    			var ciCheckState = rows[i].ciCheckState;
	    			/* if(ciValidFlag=='01'){
						$.messager.show({
							title : '提示',
							msg : '启用数据不允许删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return;
					} *//* else if(ciValidFlag !='10' || ciCheckState !='01'){ */
					if(ciCheckState !='01'){
						$.messager.show({
							title : '提示',
							msg : '只有渠道已复核才能删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return; 
					}else{
						ciIds.push(rows[i].ciId);
					}
	            }
			}
			parent.$.messager.confirm('询问', '您确认删除选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelInfo/delSubmit', {
						ciIds : JSON.stringify(ciIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
	   
		//渠道信息停用 
		function stopChannelInfo() {
			var rows = dataGrid.datagrid('getSelections');
			var ciId ;
        	var ciIds= []; 
        	var ciIds= new Array();  
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
				for(var i = rows.length - 1; i >= 0; i--){
					var ciCheckState = rows[i].ciCheckState;
					if(ciCheckState!='01'){
						$.messager.show({
							title : '提示',
							msg : '渠道未复核无法停用',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return;
					}else{
						ciIds.push(rows[i].ciId);
					}
				}
			}
			parent.$.messager.confirm('询问', '您确认停用选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelInfo/stopSubmit', {
						ciIds : JSON.stringify(ciIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
		
		//渠道信息启用 
		function startChannelInfo() {
			var rows = dataGrid.datagrid('getSelections');
			var ciId ;
        	var ciIds= []; 
        	var ciIds= new Array();  
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
			}else{
				for(var i = rows.length - 1; i >= 0; i--){
					var ciCheckState = rows[i].ciCheckState;
					if(ciCheckState!='01'){
						$.messager.show({
							title : '提示',
							msg : '渠道未复核无法启用',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return;
					}else{
						ciIds.push(rows[i].ciId);
					}
				}
			}
			parent.$.messager.confirm('询问', '您确认启用选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelInfo/startSubmit', {
						ciIds : JSON.stringify(ciIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});		
		}
		
		//渠道信息复核 
		function checkChannelInfo() {
			var rows = dataGrid.datagrid('getSelections');
			var ciId ;
        	var ciIds= []; 
        	var ciIds= new Array();  
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
				return
			}else{
				for (var i = rows.length - 1; i >= 0; i--) {
					var ciCuser = rows[i].ciCuser;
					if(ciCuser=='<shiro:principal/>'){
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
						ciIds.push(rows[i].ciId);
					}
                }
			}
			parent.$.messager.confirm('询问', '您确认复核选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelInfo/checkSubmit', {
						ciIds : JSON.stringify(ciIds)
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
                    <td><input id="ciChannelCode" name="filter_ciChannelCode" class="easyui-combobox" style = "width:250px;" /></td>
                    <!-- <th>渠道名称:</th>
                    <td><input id="ciChannelName" name="filter_ciChannelName" class="easyui-combobox" style = "width:150px;" /></td> -->
                    <th>市场类型:</th>
                    <td><input id="ciMarketCode" name="filter_ciMarketCode" class="easyui-combobox" style = "width:150px;" /></td>
                    <th>复核状态:</th>
                    <td><input id="ciCheckState" name="filter_ciCheckState" class="easyui-combobox" style = "width:150px;" /></td>
                </tr>
                <tr>
               <!--  <th>启用状态:</th>
                    <td><input id="ciValidFlag" name="filter_ciValidFlag" class="easyui-combobox" style = "width:150px;" data-options="
		                    valueField: 'label',
		                    textField: 'value',
		                    data: [{
			                        label: '10',
			                        value: '已停用'
		                         },{
			                        label: '01',
			                        value: '已启用'
		                         }]"/></td> -->
                </tr>
                
                <tr>
                
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
    <div data-options="region:'center',border:true,title:'代销端渠道信息管理列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">渠道新增</a>
            <a onclick="editChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">渠道修改</a>
            <a onclick="viewChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">渠道查看</a>
            <a onclick="delChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">渠道删除</a>
            <!-- <a onclick="stopChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">渠道停用</a>
            <a onclick="startChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">渠道启用</a> -->
            <a onclick="checkChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">渠道复核</a>
            <!-- <a onclick="delChannelInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">物理删除</a> -->
    </div>
</body>
</html>