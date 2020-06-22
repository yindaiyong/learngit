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
							pageSize : 100,
							pageList : [ 100, 200, 300, 400, 500 ],
							columns : [[{
                                    title : '渠道编号',
                                    field : 'ciChannelCode',
                                    width : 130,
                                    halign : 'center',
                                    align : 'center'
                                }, {
                                    title : '渠道名称',
                                    field : 'ciChannelName',
                                    width : 200,
                                    halign : 'center',
                                    align : 'center'
                                },{
								title : '市场类型',
								field : 'ciMarketCode',
								width : 160,
								halign : 'center',
								align : 'center',
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
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
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
							},{
                                title : '初始化',
                                field : 'ciOpsManagerName',
                                width : 160,
                                halign : 'center',
                                align : 'center',
                                formatter:function(value,rec,index){
                                    var cCode = rec.ciChannelCode;
                                    return '<a class="editcls" onclick="rollBack(\''+cCode+'\')" href="javascript:void(0)">清除数据</a>';
                                }
                            }] ],
							onLoadSuccess: function (data) {
								//去掉全选框 
								//$(".datagrid-header-check").html(""); 
								//重新加载时候去掉已选中数据 
								$("#dataGrid").datagrid('clearSelections');
                                $('.editcls').linkbutton({text:'清除数据',plain:true,iconCls:'icon-standard-control-repeat'});
							}
						});
  	});

    function rollBack(channelCode) {
        parent.$.messager.confirm('询问', '您确认清除'+channelCode+'渠道的数据吗？（操作不可逆！）', function(b) {
            if (b) {
                $.post('${ctx}/channelInfo/rollBackChannelInfo', {
                    channelCode:channelCode
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
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
                    <td><input id="ciChannelCode" name="filter_ciChannelCode" class="easyui-combobox" style = "width:200px;" /></td>
                    <th>市场类型:</th>
                    <td><input id="ciMarketCode" name="filter_ciMarketCode" class="easyui-combobox" style = "width:200px;" /></td>
                    <th>复核状态:</th>
                    <td><input id="ciCheckState" name="filter_ciCheckState" class="easyui-combobox" style = "width:200px;" /></td>
                </tr>
                <tr>
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">重置</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'代销端渠道信息列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
    </div>
</body>
</html>