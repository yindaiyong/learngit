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
<title>代销端渠道客户经理关系</title>
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
	 $('#cmChannelCode').combobox({
		 url:"${ctx}/combobox/queryChannelInfo", //访问controller的路径
		    valueField:'ID',
		    textField:'NAME',
		    multiple : false,
	        onShowPanel : function () {
	            $('#cmChannelCode').combobox('clear');
	            $('#cmChannelCode').combobox('reload','${ctx}/combobox/queryChannelInfo');
	        },
	 });
    
    $('#cmManagerName').combobox({
		 url:"${ctx}/combobox/queryManagerNameComBox", //访问controller的路径
		    valueField:'NAME',
		    textField:'NAME',
		    multiple : false,
	        onShowPanel : function () {
	            $('#cmManagerName').combobox('clear');
	            $('#cmManagerName').combobox('reload','${ctx}/combobox/queryManagerNameComBox');
	        },
	 });
    
    $('#ciBizManagerPhone').combobox({
		 url:"${ctx}/combobox/queryManagerPhoneComBox", //访问controller的路径
		    valueField:'PHONE',
		    textField:'PHONE',
		    multiple : false,
	        onShowPanel : function () {
	            $('#ciBizManagerPhone').combobox('clear');
	            $('#ciBizManagerPhone').combobox('reload','${ctx}/combobox/queryManagerPhoneComBox');
	        },
	 });
    
    $('#cmCheckState').combobox({
		 url:"${ctx}/sysDict/checkStateGroup", //访问controller的路径
		    valueField:'dictCode',
		    textField:'dictName',
		    multiple : false,
	        onShowPanel : function () {
	            $('#cmCheckState').combobox('clear');
	            $('#cmCheckState').combobox('reload','${ctx}/sysDict/checkStateGroup');
	        },
	 });
		
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/channelManager/getChannelManagerData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'cmId',
							sortName : 'cmCtime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								field :'ck',
								checkbox : 'true',
							},{
								title : 'cmId',
								field : 'cmId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '渠道编号',
								field : 'cmChannelCode',
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
								title : '客户经理编号',
								field : 'cmManagerCode',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
							}]], 
							columns : [[ {
								title : '客户经理名称',
								field : 'cmManagerName',
								width : 140,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '客户经理手机号',
								field : 'ciBizManagerPhone',
								width : 160,
								halign : 'center',
								align : 'center',
								sortable : true,
							},{
								title : '开始时间',
								field : 'cmStartDate',
								width : 140,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '结束时间',
								field : 'cmEndDate',
								width : 140,
								halign : 'center',
								align : 'center',
								sortable : true
							},/*  {
								title : '启停状态',
								field : 'cmValidFlag',
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
								field : 'cmCheckState',
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
								field : 'cmAction',
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
									if (value == "06") {
										return "复制";
									}
								}
							},{
								title : '操作人',
								field : 'cmCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '操作时间',
								field : 'cmCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'cmUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '复核时间',
								field : 'cmUtime',
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
	
    //渠道客户经理关系新增
	 function addChannelManager() {
	         parent.$.modalDialog({
	           title : '代销端渠道客户经理新增',
	            width : 552,
	            height : 280,
	            resizable : false,
	            href : '${ctx}/channelManager/addPage',
	            buttons : [ {
	                text : '提交',
	                iconCls : 'icon-save',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#addChannelManager');
	                    f.submit(); 
	                }
	            },{
	                text : '取消',
	                iconCls : 'icon-cancel',
	                handler : function() {
	                	 parent.$.modalDialog.handler.dialog('close');
	                	$.modalDialog.handler = undefined;
	                }
	            } ]
	        }); 
	    }
    
	 //渠道客户经理关系修改
	 function editChannelManager() {
		 var rows = dataGrid.datagrid('getSelections');
		 cmId = rows[0].cmId;
		 cmValidFlag = rows[0].cmValidFlag;
		 cmCheckState = rows[0].cmCheckState;
		 cmAction= rows[0].cmAction;
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
	        /* else if(cmValidFlag =='01'){
	        	$.messager.show({
            		title:'温馨提示',
            		msg:'在职状态无法修改',
            		timeout:3000,
            		showType:'fade',
            		style:{
            			right:'',
            			bottom:''
            		}
            	});
	        	return 
	        } else if(cmAction !="01" && cmAction !="02"){
	        	if(cmCheckState =='00'){
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
	            title : '代销端渠道客户经理修改',
	            width : 552,
	            height : 280,
	            resizable : false,
	            href : '${ctx}/channelManager/editPage?cmId=' + cmId,
	            buttons : [ {
	            	text : '提交',
	            	iconCls : 'icon-save',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#editChannelManager');
	                    f.submit(); 
	                }
	            },{
	                text : '取消',
	                iconCls : 'icon-cancel',
	                handler : function() {
	                	parent.$.modalDialog.handler.dialog('close');
	                	$.modalDialog.handler = undefined;
	                }
	            } ]
	        });
	    }
	 
	//渠道客户经理关系查看 
	 function viewChannelManager() {
		 var rows = dataGrid.datagrid('getSelections');
         cmId = rows[0].cmId;
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
                 title : '代销端渠道客户经理查看',
                 width : 552,
                 height : 280,
                 resizable : false,
                 href : '${ctx}/channelManager/viewPage?cmId=' + cmId,
                 buttons : [ {
                     text : '取消',
                     iconCls : 'icon-cancel',
                     handler : function() {
                     	 parent.$.modalDialog.handler.dialog('close');
                     	$.modalDialog.handler = undefined;
                     }
                 }  ]
             }); 
         }
	}
	//渠道客户经理停用  
		function stopChannelManager() {
			var rows = dataGrid.datagrid('getSelections');
			var cmId ;
        	var cmIds= []; 
        	var cmIds= new Array();  
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
					var cmCheckState = rows[i].cmCheckState;
					if(cmCheckState!='01'){
						$.messager.show({
							title : '提示',
							msg : '未复核状态不能停用数据',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return 
					}else{
						cmIds.push(rows[i].cmId);
					}
	             }	
			}
			parent.$.messager.confirm('询问', '您确认停用数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelManager/stopSubmit', {
						cmIds : JSON.stringify(cmIds)
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
		function startChannelManager() {
			var rows = dataGrid.datagrid('getSelections');
			var cmId ;
        	var cmIds= []; 
        	var cmIds= new Array();  
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
					var cmCheckState = rows[i].cmCheckState;
					if(cmCheckState!='01'){
						$.messager.show({
							title : '提示',
							msg : '未复核状态不能启用数据',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return 
					}else{
						cmIds.push(rows[i].cmId);
					}
	             }	
			}
			parent.$.messager.confirm('询问', '您确认启用选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelManager/startSubmit', {
						cmIds : JSON.stringify(cmIds)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});		
		}
		
		 //渠道客户经理逻辑删除 
		function delChannelManager() {
			var rows = dataGrid.datagrid('getSelections');
			var cmId ;
        	var cmIds= []; 
        	var cmIds= new Array();  
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
					var cmValidFlag = rows[i].cmValidFlag;
					var cmCheckState = rows[i].cmCheckState;
					/* if(cmValidFlag =='01'){
						$.messager.show({
							title : '提示',
							msg : '渠道经理在职无法删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return 
					} */if(cmCheckState !='01'){/* else if(cmValidFlag !='10' || cmCheckState !='01'){ */
						$.messager.show({
							title : '提示',
							msg : '只有经理关系已复核才能删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return; 
					 }else{
						cmIds.push(rows[i].cmId); 
					 }
	                }
			}
			parent.$.messager.confirm('询问', '您确认删除选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelManager/delSubmit', {
						cmIds : JSON.stringify(cmIds)
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
		function checkChannelManage() {
			var rows = dataGrid.datagrid('getSelections');
			var cmId ;
        	var cmIds= []; 
        	var cmIds= new Array(); 
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
					var cmCuser = rows[i].cmCuser;
				    if(cmCuser=='<shiro:principal/>'){
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
		            	cmIds.push(rows[i].cmId); 
		             }
	             }
			}
			parent.$.messager.confirm('询问', '您确认复核选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/channelManager/checkSubmit', {
						cmIds : JSON.stringify(cmIds)
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
                    <td><input id="cmChannelCode" name="filter_cmChannelCode" class="easyui-combobox" style = "width:180px;" /></td>
                    <th>客户经理名称:</th>
                    <td><input id="cmManagerName" name="filter_cmManagerName" class="easyui-combobox" style = "width:150px;" /></td>
                </tr>
                <tr>
               <!--  <th>启用状态:</th>
                    <td><input id="cmValidFlag" name="filter_cmValidFlag" class="easyui-combobox" style = "width:150px;" data-options="
		                    valueField: 'label',
		                    textField: 'value',
		                    data: [{
			                        label: '10',
			                        value: '已停用'
		                         },{
			                        label: '01',
			                        value: '已启用'
		                         }]"/></td> -->
                    <th>客户经理手机号:</th>
                    <td> <input id="ciBizManagerPhone" name="filter_ciBizManagerPhone" class="easyui-combobox" style = "width:150px;"/></td>
                    <th>复核状态:</th>
                    <td><input id="cmCheckState" name="filter_cmCheckState" class="easyui-combobox" style = "width:150px;" /></td>
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
    <div data-options="region:'center',border:true,title:'代销端渠道客户经理关系列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addChannelManager();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">关系新增</a>
            <a onclick="editChannelManager();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">关系修改</a>
            <a onclick="viewChannelManager();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">关系查看</a>
           <!--  <a onclick="stopChannelManager();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">关系停用</a> -->
            <a onclick="delChannelManager();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">关系删除</a>
            <!-- <a onclick="startChannelManager();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">关系启用</a> -->
            <a onclick="checkChannelManage();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">关系复核</a>
    </div>
</body>
</html>