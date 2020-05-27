<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
    <script type="text/javascript">

    var dataGrid;
    var orgTree;

    $(function() {
    	orgTree = $('#orgTree').tree({
        	url : '${ctx}/sysOrg/allTree/json',
            parentField : 'pid',
            lines : true,
            checkbox : true,
            onCheck : function(node, checked){
        		searchFun();
        	}
        });

        dataGrid = $('#dataGrid').datagrid({
            url : '${ctx}/sysUser/dataGrid/json',
            fit : true,
            fitColumns : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'userId',
            sortName : 'create_date',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            
            frozenColumns : [ [ {
                title : '用户Id',
                field : 'userId',
                width : 40,
                hidden : false
            },{
                width : '120',
                title : '登录名',
                field : 'loginName',
                sortable : true
            },{
                width : '120',
                title : '姓名',
                field : 'userName',
                sortable : true
            } ] ],
            columns : [ [ {
                width : '180',
                title : '创建时间',
                field : 'createDate',
                sortable : true
            },{
                width : '60',
                title : '性别',
                field : 'sex',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case "1":
                        return '男';
                    case "2":
                        return '女';
                    }
                }
            },{
                width : '180',
                title : '电话',
                field : 'phone',
                sortable : true,
                formatter : function(value) {
                    if(value != '' && value != null && value.length > 7){
                        return value.replace(/^(.{3})(?:\w+)(.{4})$/, "$1****$2");
                    }else{
                        return value;
                    }
                }
            },{
                width : '100',
                title : '状态',
                field : 'userStatus',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case "1":
                        return '正常';
                    case "0":
                        return '停用';
                    }
                }
            },{
                width : '200',
                title : '描述',
                field : 'description'
            },{
                field : 'action',
                title : '操作',
                width : 200,
                formatter : function(value, row, index) {
                    var str = '';
                    var permissionCount = 0 ;
                        <shiro:hasPermission name="sys:user:edit">
                        	permissionCount == 1 ? str += '&nbsp;|&nbsp;' : permissionCount ++ ;
                            str += $.formatString('<a class="user-easyui-linkbutton-edit" onclick="editFun(\'{0}\');" >编辑</a>', row.userId);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sys:user:del">
                       		permissionCount == 1 ? str += '&nbsp;|&nbsp;' : permissionCount ++ ;
                            str += $.formatString('<a class="user-easyui-linkbutton-del" onclick="deleteFun(\'{0}\');" >删除</a>', row.userId);
                        </shiro:hasPermission>
                    return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
            toolbar : '#toolbar'
        });
    });

    function addFun() {
        parent.$.modalDialog({
            title : '用户添加',
            width : 800,
            height : 650,
            href : '${ctx}/sysUser/addPage',
            buttons : [ {
                text : '确认',
                iconCls:'icon-save',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            },
            {
                text : '取消',
                iconCls:'icon-cancel',
                handler : function() {
                  	parent.$.modalDialog.handler.dialog('close');
                	$.modalDialog.handler = undefined;
                }
            } ]
        });
    }

    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].user_id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您确定要删除当前用户？', function(b) {
            if (b) {
                var currentUserId = '${sessionScope.session_userId}';/*当前登录用户的ID*/
                if (currentUserId != id) {
                    progressLoad();
                    $.post('${ctx}/sysUser/delete', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            dataGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                } else {
                    parent.$.messager.show({
                        title : '提示',
                        msg : '不可以删除自己！'
                    });
                }
            }
        });
    }

    function editFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
       
        parent.$.modalDialog({
            title : '用户编辑',
            width : 800,
            height : 650,
            href : '${ctx}/sysUser/editPage?id=' + id,
            buttons : [ {
                text : '确认',
                iconCls:'icon-save',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            },
            {
                text : '取消',
                iconCls:'icon-cancel',
                handler : function() {
                  	parent.$.modalDialog.handler.dialog('close');
                	$.modalDialog.handler = undefined;
                }
            } ]
        });
    }

  	//获取机构树所有选中项Id(以,分隔)
    function getOrgCheckId(){
    	var checknodes = orgTree.tree('getChecked');
        var orgIds = [];
        for ( var i = 0; i < checknodes.length; i++) {
            orgIds.push(checknodes[i].id);
        }
		/*var orgIds;
        for ( var i = 0; i < checknodes.length; i++) {
	        if (i == 0){
	        	orgIds = checknodes[i].id;
	        } else {
	    		orgIds = orgIds + "," + checknodes[i].id;
	        }
        }*/
        return orgIds;
    }

    function searchFun() {
    	var parmFilter = $.serializeObject($('#searchForm'));
		<%-- for(parm in parmFilter){--%>
		<%-- 	alert(parm + ":" + parmFilter[parm]);--%>
		<%-- }--%>
		//获取机构树勾选项
		var orgIds = getOrgCheckId();
		if (orgIds){
			parmFilter['filter_orgId'] = orgIds;
		}
        dataGrid.datagrid('load', parmFilter);
    }

    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
        //取消机构树勾选
        var checknodes = orgTree.tree('getChecked');
        for (var i = 0; i < checknodes.length; i++) {
	        orgTree.tree('uncheck',checknodes[i].target);
        }
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'查询区域'" align="center" style="height: 120px; ">
        <form id="searchForm">
            <table data-options="fit:true,border:false" style="padding-top: 10px;">
                <tr>
                    <td>用户名:<input name="filter_loginName" class="easyui-textbox" prompt="请输入用户名"/></td>
                    <td>姓名:<input name="filter_userName" class="easyui-textbox" prompt="请输入用户姓名"/></td>
                    </tr>
                                 <tr>
             <td></td>
             <td></td>
             </tr>
                    <tr>
                     <td align="right">
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    </td>
                    <td align="left"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'用户列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:true,split:false,title:'组织机构'"  style="width:150px;overflow: hidden; ">
        <ul id="orgTree" style="width:160px;margin: 10px 10px 10px 10px">
        </ul>
    </div>
    <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="sys:user:add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加用户</a>
        </shiro:hasPermission>
    </div>
</body>
</html>