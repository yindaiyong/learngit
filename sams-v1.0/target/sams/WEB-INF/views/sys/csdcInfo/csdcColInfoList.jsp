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
<title>中登接口字段信息</title>
<script type="text/javascript">
var dataGrid = "";
var row = -1;
$(function(){
	//加载页面表格
	dataGrid = $("#csdcColGrid").datagrid({
		url : '${ctx}/csdcCol/csdcColInfoList',
		striped : true,
		pagination : true,
		singleSelect : true,
		idField : 'icId',
		sortName : 'ic_utime',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
				400, 500 ],
		columns : [[ {
			field :'ck',
			checkbox : 'true',
		},{
			field : 'icId',
			hidden:true
		},{
			title : '字段编号',
			field : 'icColCode',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '字段名',
			field : 'icColName',
			width : 200,
			halign : 'center',
			align : 'center'
		},{
			title : '接口名称',
			field : 'iiInterfaceName',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '字段描述',
			field : 'icColDesc',
			width : 200,
			halign : 'center',
			align : 'center'
		},{
			title : '字段类型',
			field : 'icColType',
			width : 140,
			halign : 'center',
			align : 'center',
			formatter:function(value){
				if(value == "C"){return "C(字符型)";}
				if(value == "A"){return "A(数字字符型)";}
				if(value == "N"){return "N(数值型)";}
				if(value == "TEXT"){return "TEXT(不定长文本)";}
			}
		},{
			title : '长度',
			field : 'icColLength',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '小数位',
			field : 'icColDecimal',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '是否必填',
			field : 'icColRequ',
			width : 140,
			halign : 'center',
			align : 'center',
			formatter:function(value){
				if(value == "1"){return "必填";}
				if(value == "0"){return "非必填";}
				if(value == null || value == ""){return "未设置";}
			}
		},{
			title : '格式化',
			field : 'icColRule',
			width : 140,
			halign : 'center',
			align : 'center',
		},{
			title : '数据字典',
			field : 'icColDict',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '处理顺序',
			field : 'icColOrder',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '必填开关',
			field : 'icRequOnOff',
			width : 140,
			halign : 'center',
			align : 'center',
			formatter:function(value){
				if(value == "01"){return "开";}
				if(value == "00"){return "关";}
				if(value == null || value == ""){return "未设置";}
			}
		},{
			title : '规则开关',
			field : 'icRuleOnOff',
			width : 140,
			halign : 'center',
			align : 'center',
			formatter:function(value){
				if(value == "01"){return "开";}
				if(value == "00"){return "关";}
				if(value == null || value == ""){return "未设置";}
			}
		},/* {
			title : '创建时间',
			field : 'icCtime',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '创建人',
			field : 'icCuser',
			width : 140,
			halign : 'center',
			align : 'center'
		}, */{
			title : '修改时间',
			field : 'icUtime',
			width : 140,
			halign : 'center',
			align : 'center'
		},{
			title : '修改人',
			field : 'icUuser',
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
	            $('#csdcColGrid').datagrid("unselectAll");//取消选中当前所有行
	            $('#csdcColGrid').datagrid("selectRow", rowIndex);//选中当前点击的行
	            row = rowIndex;
			}else{
				$('#csdcColGrid').datagrid("unselectAll");//取消选中当前所有行
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
function viewAndEditCsdcColInfo(type){
	var icId = -1;
	var icInterfaceCode ="";
	if(type != 0 ){
		var data = dataGrid.datagrid('getSelections')[0];
		if(data == undefined){
			$.messager.alert('提示', "请选择一条记录", 'info');
			return false;
		}
		icId = data.icId;
		icInterfaceCode = data.icInterfaceCode;
	}
	var buttons = [{
        text : '保存',
        iconCls:'icon-save',
        handler : function() {
            parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
            var f = parent.$.modalDialog.handler.find('#addAndEditCsdcCol');
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
        title : '中登接口字段信息修改',
		width:1400,
        height :230,
        modal: true,
        resizable:true,
        style : 'background-color : #E0ECFF;',
        href : '${ctx}/csdcCol/viewAndEditPage?icId='+icId+"&type="+type+"&icInterfaceCode="+icInterfaceCode,
        buttons :buttons
	});
}
//删除
function delCsdcColInfo(){
	//获得选中行
	var data = dataGrid.datagrid('getSelections')[0];
	var icId = data.icId;
	parent.$.messager.confirm('警告', '是否删除该条记录？', function(b) {
        if (b) {
            progressLoad();
            $.post('${ctx}/csdcCol/delCsdcColInfo', {
            	icId : icId
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
//复核
function checkCsdcColInfo(){
	//获得选中行
	var data = dataGrid.datagrid('getSelections')[0];
	var icId = data.icId;
	 progressLoad();
     $.get('${ctx}/csdcCol/checkCsdcColInfo', {
     	icId : icId
     }, function(result) {
         if (result.success) {
             parent.$.messager.alert('提示', result.msg, 'info');
             dataGrid.datagrid('reload');
         }
         progressClose();
     }, 'JSON');
}
//导入
function importCsdcColInfo(){
	$.messager.alert('提示', "导入", 'info');
}
//导出
function exportCsdcColInfo(){
	var iiInterfaceCode = $('#iiInterfaceCode').combobox('getValue');
	if(iiInterfaceCode == ""){
		$.messager.alert('警告', "请选择需要导出的接口名称", 'error');
		return ;
	}
	window.location.href = "${ctx}/csdcCol/exportCsdcColInfo?iiInterfaceCode="+iiInterfaceCode
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 50px;">
        <form id="searchForm">
            <table>
                <tr>
                    <th>接口名称:</th>
                    <td><input id="iiInterfaceCode" name="filter_iiInterfaceCode" class="easyui-combobox" style="width:250px;" data-options="url:'${ctx}/combobox/queryBaseCsdc',
				valueField:'ID',
				textField:'NAME'"/></td>
                    <th>字段名:</th>
                    <td><input id="icColName" name="filter_icColName" class="easyui-textbox" prompt="请输入字段名称"/></td>
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
    <div data-options="region:'center',border:true,title:'中登接口字段信息'" >
        <table id="csdcColGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
            <!-- <a id = "csdc-col-add" onclick="viewAndEditCsdcColInfo(0);"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增</a> -->
            <a id = "csdc-col-edit" onclick="viewAndEditCsdcColInfo(1);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit' ">修改</a>
            <!-- <a id = "csdc-col-view" onclick="viewAndEditCsdcColInfo(2);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip',disabled:true">查看</a> -->
<!--             <a id = "csdc-col-import" onclick="importCsdcColInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">导入</a>-->
            <a id = "csdc-col-export" onclick="exportCsdcColInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">导出</a> 
    </div>
</body>
</html>