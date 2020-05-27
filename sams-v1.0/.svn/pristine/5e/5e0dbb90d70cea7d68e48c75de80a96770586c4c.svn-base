<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> .dialog-button { padding: 5px; text-align: center; background-color: ' #E0ECFF;' } </style>
<head>
<title>中登接口基本信息</title>
<script type="text/javascript">
var dataGrid = "";
var row = -1;
$(function(){
	//加载页面表格
	dataGrid = $("#csdcBaseGrid").datagrid({
		url : '${ctx}/csdc/csdcInfoBaseList',
		fit : true,
		fitColumns : true,
		striped : true,
		pagination : true,
		singleSelect : true,
		idField : 'iiId',
		sortOrder : 'desc',
		sortName : 'ii_utime',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
				400, 500 ],
		columns : [[ {
			field :'ck',
			checkbox : 'true',
			name :'ck',
		},{
			field : 'iiId',
			hidden:true
		},{
			title : '接口编号',
			field : 'iiInterfaceCode',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '接口名称',
			field : 'iiInterfaceName',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '接口版本信息',
			field : 'iiInterfaceVer',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '接口描述',
			field : 'iiInterfaceDesc',
			width : 140,
			halign : 'center',
			align : 'center'
		},/* {
			title : '创建人',
			field : 'iiCuser',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '创建时间',
			field : 'iiCtime',
			width : 140,
			halign : 'center',
			align : 'center'
		}, */{
			title : '修改人',
			field : 'iiUuser',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '修改时间',
			field : 'iiUtime',
			width : 140,
			halign : 'center',
			align : 'center'
		}
		]],
		toolbar : '#toolbar',
		//加载成功
		onLoadSuccess: function (data) {
			//去掉标题上的复选框
		   $(".datagrid-header-check").html("");
		},
		onClickRow: function (rowIndex, rowData) {
			if(rowIndex != row){
	            $('#csdcBaseGrid').datagrid("unselectAll");//取消选中当前所有行
	            $('#csdcBaseGrid').datagrid("selectRow", rowIndex);//选中当前点击的行
	            row = rowIndex;
			}else{
				$('#csdcBaseGrid').datagrid("unselectAll");//取消选中当前所有行
				row = -1;
			}
        },
	});
});

//查询
function searchInterface(){
	var parmFilter = $.serializeObject($('#searchForm'));
    dataGrid.datagrid('load', parmFilter);
}
//重置
function cleanInterface(){
	$('#searchForm input').val('');
}
//新增0  修改1  查看2
function viewAndEditCsdcBaseInfo(type){
	var iiId = -1;
	if(type != 0 ){
		var data = dataGrid.datagrid('getSelections')[0];
		if(data == undefined){
			$.messager.alert('提示', "请选择一条记录", 'info');
			return false;
		}
		iiId = data.iiId;
	}
	var buttons = [{
        text : '保存',
        iconCls:'icon-save',
        handler : function() {
            parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
            var f = parent.$.modalDialog.handler.find('#csdcBaseEditAndView');
            f.submit(); 
            
        }
    },{
        text : '关闭',
        iconCls:'icon-cancel',
        handler : function() {
        	parent.$.modalDialog.handler.dialog('close');
        }
    }];
	if(type==2){
		buttons= [{
	        text : '关闭',
	        iconCls:'icon-cancel',
	        handler : function() {
	        	parent.$.modalDialog.handler.dialog('close');
	        }
	    }];
	}
	parent.$.modalDialog({
        title : '中登接口基本信息修改',
        width : 400,
        height :275,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/csdc/viewAndEditPage?iiId='+iiId+"&type="+type,
        buttons :buttons
	});
}
//删除
function delCsdcBaseInfo(){
	//获得选中行
	var data = dataGrid.datagrid('getSelections')[0];
	var iiId = data.iiId;
	parent.$.messager.confirm('警告', '是否删除该条记录？', function(b) {
        if (b) {
            progressLoad();
            $.post('${ctx}/csdc/delCsdcBaseInfo', {
            	iiId : iiId
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
             msg : '操作取消！'
         });
     }
    });
}

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 50px;">
        <form id="searchForm">
            <table>
                <tr>
                    <th>接口名称:</th>
                    <td><input id="iiInterfaceName" name="filter_iiInterfaceName" class="easyui-textbox" prompt="请输入接口名称"/></td>
                    <td>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchInterface();">查询</a>
                    </td>
                    <td>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanInterface();">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'中登接口基本信息'" >
        <table id="csdcBaseGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <!-- <a id = "csdc-base-add" onclick="viewAndEditCsdcBaseInfo(0);"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a> -->
            <a id = "csdc-base-edit" onclick="viewAndEditCsdcBaseInfo(1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit' ">修改</a>
            <!-- <a id = "csdc-base-del" onclick="delCsdcBaseInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del',disabled:true">删除</a> -->
            <!-- <a id = "csdc-base-view" onclick="viewAndEditCsdcBaseInfo(2);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip',disabled:true">查看</a> -->
    </div>
</body>
</html>