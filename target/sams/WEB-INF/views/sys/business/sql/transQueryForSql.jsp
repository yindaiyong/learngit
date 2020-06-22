<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css"> .dialog-button { padding: 5px; text-align: center; /*background-color: #E0ECFF;*/ } </style>
    <head>
<title>查看业务取值对应的sql</title>

<script type="text/javascript">
    var dataGrid = "";
    $(function(){
        //加载页面表格
        dataGrid = $("#aa").datagrid({
            url : '',
            striped : true,
            pagination : true,
            pageSize : 100,
            pageList : [100, 200, 300,
                400, 500 ],
            columns : [[ {
                title : '序号',
                field : 'id',
                width : 50,
                halign : 'center',
                align : 'center'
            },{
                title : '对应的错误提示',
                field : 'errorMsg',
                width : 420,
                halign : 'center',
                align : 'center'
            },{
                title : '对应的取值分类',
                field : 'queryType',
                width : 270,
                halign : 'center',
                align : 'center',
            },{
                title : '取值sql名称',
                field : 'name',
                width : 600,
                halign : 'center',
                align : 'center',
                formatter : function(value){
                	if(value.indexOf("--") != -1){
                		return value;
                	}else{
                    	return "<a href='#'>"+value+"</a>";
                	}
                }
            },{
                title : '',
                field : 'url',
                width : 10,
                halign : 'center',
                align : 'center',
                hidden:true,
            }
            ]],
            //点击单元格
            onClickCell : function(rowIndex, field, value){
                if("name" != field || value.indexOf("--") != -1){
                    return false;
                }
                var rows = $("#aa").datagrid("getRows");
                var row = rows[rowIndex];//rowIndex为行号
                var url = row.url;
                window.open('${ctx}/sql/'+url);
            }
        });
        $.getJSON("${ctx}/sql/list.json",function (data) {
        	debugger;
            $('#aa').datagrid('loadData',data);
        });
    });
</script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'center',border:true,title:'确认数据取值sql列表'" >
    <table id="aa" data-options="fit:true,border:false"></table>
</div>
</body>
</html>