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
<title>反洗钱非居民信息页面</title>
    <script type="text/javascript" src="${staticPath}/js/fileDownload.js" charset="utf-8"></script>
</head>


    <script type="text/javascript">
    $(function(){
        $("#uploadFile1").click(function () {
            var fileType = $("#fileType").combobox("getValue");
            var file = $("#file1").filebox("files")[0];
            var upChannelCode = $("#upChannelCode").combobox("getValue");
            if (file === null || typeof (file) == "undefined") {
                $.messager.alert('提示','请选择要上传的文件','warning');
                return;
            }
            if (upChannelCode == null) {
                $.messager.alert('提示','请选择上传文件的渠道','warning');
                return;
            }
            //执行ajax远程文件上传操作
            var fd = new FormData();
            fd.append("file", file);
            fd.append("type", fileType+"");
            fd.append("channelCode", upChannelCode+"");
            parent.$.messager.confirm("询问", "您确认上传反洗钱信息文件吗？", function(b) {
                if (b) {
                    uploadFile(fd);
                }
            });
        })

        function uploadFile(fd) {
            $.messager.progress({
                title : '提示',
                text : '正在上传文件，请等待....'
            });
            $.ajax({
                url:"${ctx}/downloadFile/uploadFile",
                type:"post",
                data:fd,
                cache: false,
                processData: false,
                contentType: false,
                success:function(data){
                    var resMsg = JSON.parse(data).msg
                    if("success" == resMsg){
                        $.messager.progress('close');
                        $.messager.show({
                            title : '提示',
                            msg : "上传保存成功！",
                            timeout : 2000
                        });
                    }else{
                        $.messager.progress('close');
                        $.messager.alert('错误', resMsg,'error');
                    }
                },
                error:function(e){
                    $.messager.alert("错误","服务器异常，请稍后重试！！！<br/>"+e.message,'error');
                }
            });
        };

        //数据展示信息
        dataGrid = $('#dataGrid')
            .datagrid({
                url : '${ctx}/downloadFile/getAgentPaymentData',
                striped : true,
                rownumbers : true,
                pagination : true,
                idField : 'ciId',
                sortName : '',
                pageSize : 10,
                pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
                    400, 500 ],
                frozenColumns : [ []],
                columns : [[{
                    title : '渠道编号',
                    field : 'fudChannelCode',
                    width : 90,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '渠道名称',
                    field : 'fudChannelName',
                    width : 120,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '产品编号',
                    field : 'fudProductCode',
                    width : 90,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '产品名称',
                    field : 'fudProductName',
                    width : 120,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '确认份额',
                    field : 'fudShareCfm',
                    width : 60,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '份额确认日期',
                    field : 'fudShareCfmDate',
                    width : 80,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '客户类型',
                    field : 'fudCustomerType',
                    width : 100,
                    halign : 'center',
                    align : 'center',
                    formatter : function(value) {
                        if (value == "1") {
                            return "个人（1）";
                        }
                        if (value == "0") {
                            return "机构（0）";
                        }else{
                            return value;
                        }
                    }
                },{
                    title : '客户名称',
                    field : 'fudCustomerName',
                    width : 90,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '证件类型',
                    field : 'fudCertificateType',
                    width : 30,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '证件号码',
                    field : 'fudCertificateNo',
                    width : 120,
                    halign : 'center',
                    align : 'center',
                    formatter : function(value) {
                        if(value != '' && value != null && value.length > 8){
                            return value.replace(/^(.{4})(?:\w+)(.{4})$/, "$1****$2");
                        }else{
                            return value;
                        }
                    }
                },{
                    title : '受益级别',
                    field : 'fudBenefitLevel',
                    width : 90,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '缴款日期',
                    field : 'fudPaymentDate',
                    width : 90,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '证件有效始',
                    field : 'fudCertificateStart',
                    width : 150,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '证件有效终',
                    field : 'fudCertificateEnd',
                    width : 150,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '上传日期',
                    field : 'fudUploadTime',
                    width : 150,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '下载日期',
                    field : 'fudDownloadTime',
                    width : 150,
                    halign : 'center',
                    align : 'center'
                } ] ],
                toolbar : '#toolbar',
                onLoadSuccess: function (data) {
                }
            });
        $('#fileType').combobox({
            editable:false,
            panelHeight: 'auto',
            panelMaxHeight: 154
        });
        $('#selectType').combobox({
            editable:false,
            panelHeight: 'auto',
            panelMaxHeight: 154
        });
    })

    function dowTemplate() {
        var fileType = $("#fileType").combobox("getValue");
        var url="${ctx}/downloadFile/downloadExcelTemplate?type="+fileType;
        if(fileType == "1" || fileType == "0" || fileType == "2"){
            $.fileDownload(url,{
                httpMethod: 'get',
                prepareCallback:function(url){
                    $('#ExcelBtn').text('Excel准备中...')
                },
                successCallback:function(url){
                    $('#ExcelBtn').text('导出Excel')
                },
                failCallback: function (html, url) {
                    $('#ExcelBtn').text('导出Excel')
                }
            });
        }else{
            $.messager.alert("错误","选择需要下载模板的类型<br/>",'error');
        }

    }

    //查询
    function searchFun() {
        var parmFilter = $.serializeObject($('#searchForm'));
        dataGrid.datagrid('load', parmFilter);
    }
    //下载 （先去查询当前条件下是否有数据）
    function downloadFile() {
        $.messager.progress({
            title : '提示',
            text : '正在下载文件，请等待....'
        });
        var dUrl = "${ctx}/downloadFile/downloadExcelByChannel";
        var channelCode = $('#selectChannelCode').combobox("getValue");
        var date = $('#theDate').val();
        var type = $('#selectType').combobox("getValue");
        var fd = new FormData();
        if (type === null || type == ""){
            $.messager.progress('close');
            $.messager.alert('提示','请选择文件下载的类型 个人OR渠道','warning');
            return;
        }
        $("#disType").val(type);
        if (channelCode != null || channelCode != ""){
            $("#disChannelCode").val(channelCode);
        }
        if (date != null || date != ""){
            $("#disDate").val(date);
        }
        fd.append("type", type+"");
        fd.append("channelCode", channelCode+"");
        fd.append("date", date+"");

        $.ajax({
            url:"${ctx}/downloadFile/checkData",
            type:"post",
            data:fd,
            cache: false,
            processData: false,
            contentType: false,
            success:function(data){
                var resMsg = JSON.parse(data).msg
                if("success" == resMsg){
                    $("#downloadExcel").attr("action",dUrl);
                    $("#downloadExcel").submit();
                    $.messager.progress('close');
                }else{
                    $.messager.progress('close');
                    $.messager.alert('提示', resMsg,'error');
                }
            },
            error:function(e){
                $.messager.progress('close');
                $.messager.alert("错误","服务器异常，请稍后重试！！！<br/>"+e.message,'error');
            }
        });
    }

</script>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,title:'操作文件区域'"
		align="center" style="height: 200px;">
        <table>
            <tr>
                <td>
                    类型:
                    <select id="fileType" class="easyui-combobox" name="dept" style="width:100px;">
                        <option value="0">机构</option>
                        <option value="1">个人</option>
                        <option value="2">混合</option>
                    </select>
                    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="dowTemplate()" id="dowTemplate" >模板下载</a>
                </td>
                <td>
                    上传渠道:
                    <input class="easyui-combobox"  style = "width:200px;" id="upChannelCode" name ="filter_ciChannelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     validType:'length[1,50]',
					     "/>
                </td>
                <td>
                    <input class="easyui-filebox" id="file1" style="width:200px" data-options="buttonText:'选择文件', accept:'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' "/>
                    <a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-hamburg-up',plain:true" id="uploadFile1">上传</a>
                </td>
            </tr>
            <tr><td colspan="10">
                <form id="searchForm">
                    <table align="center">
                        <tr>
                            <th>渠道名称:</th>
                            <td>
                                <input class="easyui-combobox"  style = "width:200px;" id="selectChannelCode" name ="filter_channelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     validType:'length[1,50]',
					     "/>
                            </td>
                            <th>上传日期</th>
                            <td>
                                <input id="theDate" type="text" name ="filter_date" class="easyui-datebox" >
                            </td>
                            <th>数据类型</th>
                            <td><select id="selectType" style="width: 150px"
                                        class="easyui-combobox" name ="filter_type">
                                <option value='0'>机构</option>
                                <option value='1'>个人</option>
                                <option value='2'>混合</option>
                            </select></td>
                        </tr>
                        <tr><td colspan="5" align="center">
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-hamburg-down',plain:true" onclick="downloadFile();">下载</a>
                        </td></tr>
                    </table>
                </form>
                <form name="downloadFrame" id="downloadExcel" style="display: none;" frameborder="0">
                    <input type="text" id="disChannelCode" name="channelCode">
                    <input type="text" id="disDate" name="date">
                    <input type="text" id="disType" name="type">
                </form>
            </td></tr>
        </table>
    </div>
    <div data-options="region:'center',border:true,title:'数据展示'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    </div>
    <div id="toolbar" style="display: none;">
    </div>
</body>
</html>