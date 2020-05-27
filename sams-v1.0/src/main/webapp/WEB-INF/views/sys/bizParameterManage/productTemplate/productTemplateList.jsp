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
<title>代销端产品模板配置</title>
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
		//初始化
		 $('#ptId').combobox({
			 url:"${ctx}/combobox/queryTempalteCodeComBox", //访问controller的路径
			    valueField:'CODE',
			    textField:'NAME',
			    multiple : false,
			    onShowPanel : function () {
		            $('#ptId').combobox('clear');
		            $('#ptId').combobox('reload','${ctx}/combobox/queryTempalteCodeComBox');
		        },
		 });
		 
		
		 $('#ptProductCode').combobox({
			 url:"${ctx}/sysDict/productTypeGroup", //访问controller的路径
			    valueField:'dictCode',
			    textField:'dictName',
			    multiple : false,
		        onShowPanel : function () {
		            $('#ptProductCode').combobox('clear');
		            $('#ptProductCode').combobox('reload','${ctx}/sysDict/productTypeGroup');
		        },
		 });
		 
		 
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/productTemplate/getParProductTempInfoData',
							striped : true,
							rownumbers : true,
							pagination : true,
							idField : 'ptId',
							sortName : 'pt_ctime',
							sortOrder : 'desc',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [{
								field :'ck',
								checkbox : 'true',
							}, {
								title : 'ptId',
								field : 'ptId',
								width : 150,
								halign : 'center',
								align : 'center',
								hidden : 'true'
							},  {
								title : '模板编码',
								field : 'ptTemplateCode',
								width : 130,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '模板名称',
								field : 'ptTemplateName',
								width : 300,
								halign : 'center',
								align : 'center',
								sortable : true
							},{
								title : '产品类型',
								field : 'productTypeName',
								width : 300,
								halign : 'center',
								align : 'center',
								sortable : true
							}, {
								title : '产品类型',
								field : 'ptProductCode',
								width : 160,
								halign : 'center',
								align : 'center',
								hidden : 'true',
								formatter : function(value) {
									if (value == "1") {
										return "丰利B类";
									}
									if (value == "2") {
										return "丰利D类";
									}
									if (value == "3") {
										return "红宝石7天";
									}
									if (value == "4") {
										return "固收类";
									}
									if (value == "5") {
										return "封闭净值类";
									}
									if (value == "6") {
										return "T+N";
									}
									if (value == "7") {
										return "权益类";
									}
								}
							}]], 
							columns : [[/* {
								title : '启停状态',
								field : 'ptValidFlag',
								width : 140,
								halign : 'center',
								align : 'center'
								,formatter : function(value) {
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
								field : 'ptCheckState',
								width : 140,
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
								field : 'ptAction',
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
							}, {
								title : '操作人',
								field : 'ptCuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '操作时间',
								field : 'ptCtime',
								width : 140,
								halign : 'center',
								align : 'center'
							}, {
								title : '复核人',
								field : 'ptUuser',
								width : 80,
								halign : 'center',
								align : 'center'
							},{
								title : '复核时间',
								field : 'ptUtime',
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
  	});
	
    //模板信息新增页面 
	 function addProductTemplate() {
	         parent.$.modalDialog({
	           title : '模板信息新增',
	            width : 552,
	            height : 580,
	            resizable : false,
	            href : '${ctx}/productTemplate/addPage',
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
	         refresh();
	    }
    
	 //模板信息修改页面  
	 function editProductTemplate() {
		 var rows = dataGrid.datagrid('getSelections');
		 ptId = rows[0].ptId;
		 ptValidFlag = rows[0].ptValidFlag;
		 ptAction = rows[0].ptAction;
		 ptCheckState = rows[0].ptCheckState;
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
         	return;
		 }/* else if(ptValidFlag =='01'){
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
	        }else if(ptAction !="01" && ptAction !="02"){
	        	if(ptCheckState =='00'){
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
	        	title : '模板信息修改',
	            width : 552,
	            height : 580,
	            resizable : false,
	            href : '${ctx}/productTemplate/editPage?ptId=' + ptId,
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
	 
	
	//模板信息查看页面 
	 function viewProductTemplate(ptId) {
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
       	return;
       	}else{
    	  ptId = rows[0].ptId;
    	  parent.$.modalDialog({
	            title : '模板信息查看',
	            width : 552,
	            height : 580,
	            resizable : false,
	            href : '${ctx}/productTemplate/viewPage?ptId=' + ptId,
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
	      
	 //模板数据逻辑删除 
		function delProductTemplate() {
			var rows = dataGrid.datagrid('getSelections');
			var ptTemplateCode ;
        	var ptTemplateCodes= []; 
        	var ptTemplateCodes= new Array();  
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
					var ptValidFlag = rows[i].ptValidFlag;
					var ptCheckState = rows[i].ptCheckState;
					/* if(ptValidFlag =='01'){
						$.messager.show({
							title : '提示',
							msg : '模板启用无法删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return; 
					}else */ if(ptCheckState !='01'){/* else if(ptValidFlag !='10' || ptCheckState !='01'){ */
						$.messager.show({
							title : '提示',
							msg : '只有模板已复核才能删除',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return; 
					}else{
						ptTemplateCodes.push(rows[i].ptTemplateCode);
					}
                }
			}
			parent.$.messager.confirm('询问', '您确认删除选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/productTemplate/delete', {
						ptTemplateCodes : JSON.stringify(ptTemplateCodes)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});
		}
	 
	 	 //模板数据启用 
		function startProductTemplate() {
			var rows = dataGrid.datagrid('getSelections');
			var ptTemplateCode;
        	var ptTemplateCodes= []; 
        	var ptTemplateCodes= new Array();  
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
					var ptCheckState = rows[i].ptCheckState;
					if(ptCheckState!='01'){
						$.messager.show({
							title : '提示',
							msg : '模板未复核无法启用',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return
					}else{
						ptTemplateCodes.push(rows[i].ptTemplateCode);	
					}
	             }	
			}
			parent.$.messager.confirm('询问', '您确认启用选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/productTemplate/startSubmit', {
						ptTemplateCodes : JSON.stringify(ptTemplateCodes)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});		
		}
		 
		 
		 //模板数据停用  
		function stopProductTemplate() {
			var rows = dataGrid.datagrid('getSelections');
			var ptTemplateCode;
        	var ptTemplateCodes= []; 
        	var ptTemplateCodes= new Array();  
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
					var ptCheckState = rows[i].ptCheckState;
					if(ptCheckState!='01'){
						$.messager.show({
							title : '提示',
							msg : '模板未复核无法停用',
							timeout : 3000,
							showType : 'fade',
							style : {
								right : '',
								bottom : ''
							}
						});
						return
					}else{
						ptTemplateCodes.push(rows[i].ptTemplateCode);	
					}
	             }	
			}
			parent.$.messager.confirm('询问', '您确认停用选中数据吗？', function(b) {
				if (b) {
					$.post('${ctx}/productTemplate/stopSubmit', {
						ptTemplateCodes : JSON.stringify(ptTemplateCodes)
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
					}, 'JSON');
				} 
			});		
		} 

	 
    //模板信息复核
	 function checkProductTemplate() {
		 var rows = dataGrid.datagrid('getSelections');
		 var ptTemplateCode ;
     	 var ptTemplateCodes= []; 
     	 var ptTemplateCodes= new Array();
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
				var ptCuser = rows[i].ptCuser;
				if(ptCuser=='<shiro:principal/>'){
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
					ptTemplateCodes.push(rows[i].ptTemplateCode); 
				 }
            }
		 }
     	parent.$.messager.confirm('询问', '您是否要复核模板？', function(b) {
            if (b) {
                progressLoad();
                $.post('${ctx}/productTemplate/checkSubmit', {
                	ptTemplateCodes : JSON.stringify(ptTemplateCodes)
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        dataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }else {
             parent.$.messager.show({
                 title : '提示',
                 msg : '操作失败'
             });
         }
        });   
	   }
    
    //刷新查询下拉列表
    function refresh(){
		 $('#ptProductCode').combobox({
			 url:"${ctx}/sysDict/productTypeGroup", //访问controller的路径
			    valueField:'dictCode',
			    textField:'dictName'   
		 });
		 
		 $('#ptTemplateCode').combobox({
			 url:"${ctx}/combobox/queryTempalteCodeComBox", //访问controller的路径
			    valueField:'CODE',
			    textField:'CODE'   
		 });
		 
		 $('#ptTemplateName').combobox({
			 url:"${ctx}/combobox/queryTempalteNameComBox", //访问controller的路径
			    valueField:'NAME',
			    textField:'NAME'   
		 })	
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
                    <th>模板名称:</th>
                    <td><input id="ptId" name="filter_ptTemplateCode" class="easyui-combobox" style = "width:250px;" /></td>
                    <!-- <th>模板名称:</th>
                    <td><input id="ptTemplateName" name="filter_ptTemplateName" class="easyui-combobox" style = "width:150px;" /></td> -->
                    <th>产品类型:</th>
                    <td><input id="ptProductCode" name="filter_ptProductCode"
						class="easyui-combobox" style = "width:150px;" /></td>
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
    <div data-options="region:'center',border:true,title:'代销端产品模板配置列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
        <input id="ptIds" name="ptIds" type="hidden">
    </div>
    <div id="toolbar" style="display: none;">
            <a onclick="addProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">模板新增</a>
            <a onclick="editProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">模板修改</a>
            <a onclick="viewProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">模板查看</a>
            <a onclick="delProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">模板删除</a>
          <!--   <a onclick="stopProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">模板停用</a>
            <a onclick="startProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">模板启用</a> -->
            <a onclick="checkProductTemplate();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">模板复核</a>
    </div>
</body>
</html>