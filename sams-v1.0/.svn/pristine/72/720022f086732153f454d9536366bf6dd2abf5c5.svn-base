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
<title>添加文件到ftp目录</title>
    <script type="text/javascript" src="${staticPath}/js/fileDownload.js" charset="utf-8"></script>
</head>


    <script type="text/javascript">
    $(function(){
        $("#uploadFile1").click(function () {
            // var fileType = $("#fileType").combobox("getValue");
            var date = $('#theUpDate').val();
            var file = $("#file1").filebox("files");
            var fileNames = $("#file1").filebox("getValue");
            var upChannelCode = $("#upChannelCode").combobox("getValue");
            if (fileNames == null || typeof (fileNames) == "undefined" || fileNames == "") {
                $.messager.alert('提示','请选择要上传的文件','warning');
                return;
            }

            if (upChannelCode == null) {
                $.messager.alert('提示','请选择上传文件的渠道','warning');
                return;
            }
            //执行ajax远程文件上传操作
            var fd = new FormData();
            for (var i = 0; i < file.length; i++) {
                fd.append("files[]", file[i]);
            }
            fd.append("businessDate", date+"");
            fd.append("channelCode", upChannelCode+"");
            parent.$.messager.confirm("询问", "您确认上传渠道申请文件吗？", function(b) {
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
                url:"${ctx}/viewFileData/uploadFileToFtp",
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
                        //查询条件框 赋值查询
                        $('#selectChannelCode').combobox("setValue",fd.get("channelCode"));
                        $('#theDate').datebox("setValue",fd.get("businessDate"));
                        $('#theType').combobox("setValue","3");
                        //执行查询
                        $("#serchBtn").click();
                    }else{
                        $.messager.progress('close');
                        $.messager.alert('错误', resMsg,'error');
                    }
                },
                error:function(e){
                    $.messager.progress('close');
                    $.messager.alert("错误","服务器异常，请稍后重试！！！<br/>"+e.message,'error');
                }
            });
        };

        //数据展示信息
        dataGrid = $('#dataGrid')
            .datagrid({
                url : '${ctx}/viewFileData/showUploadFileStatus',
                striped : true,
                rownumbers : true,
                pagination : true,
                pageSize : 20,
                selectOnCheck:true,
                frozenColumns : [ [{
                    field :'ck',
                    checkbox : 'true'
                }]],
                columns : [[{
                    title : '渠道编号',
                    field : 'channelCode',
                    width : 150,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '渠道名称',
                    field : 'channelName',
                    width : 150,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '文件名称',
                    field : 'fileName',
                    width : 250,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '日期',
                    field : 'fileDate',
                    width : 120,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '状态',
                    field : 'fileStatus',
                    width : 200,
                    halign : 'center',
                    align : 'center'
                },{
                    title : '文件所在位置',
                    field : 'fileUrl',
                    width : 600,
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
        //获取需要下载的文件列表
        var rows = dataGrid.datagrid('getSelections');
        var ckNames = "";
        for(var i =0;i<rows.length;i++){
            var ckName = rows[i].fileName;
            ckNames+=(ckName+',');
        }
        //
        $.messager.progress({
            title : '提示',
            text : '正在下载文件，请等待....'
        });
        var dUrl = "${ctx}/viewFileData/downloadFile";
        var channelCode = $('#selectChannelCode').combobox("getValue");
        var date = $('#theDate').val();
        var type = $('#theType').combobox("getValue");
        var fd = new FormData();
        if (type === null || type == "" ||date === null || date == "" || channelCode === null || channelCode == ""){
            $.messager.progress('close');
            $.messager.alert('提示','请选择需要下载文件的条件','warning');
            return;
        }
        if(type === '3'){
            $.messager.progress('close');
            $.messager.alert('提示', "仅支持下载确认文件、行情文件。重新选择",'error');
            return;
        }
        if(ckNames === ''){
            $.messager.progress('close');
            $.messager.alert('提示', "请选择需要下载的文件名",'error');
            return;
        }

        $("#disType").val(type);
        if (channelCode != null || channelCode != ""){
            $("#disChannelCode").val(channelCode);
        }
        if (date != null || date != ""){
            $("#disDate").val(date);
        }
        $("#ckNames").val(ckNames);
        fd.append("type", type+"");
        fd.append("channelCode", channelCode+"");
        fd.append("businessDate", date+"");
        fd.append("ckNames", ckNames+"");

        $.ajax({
            url:"${ctx}/viewFileData/checkFileDow",
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
		align="center" style="height: 150px;">
        <table>
            <tr>

                <td>
                    上传渠道:
                    <input class="easyui-combobox"  style = "width:200px;" id="upChannelCode" name ="channelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     validType:'length[1,50]',
					     "/>
                </td>
                <td>
                    上传日期
                    <input id="theUpDate" type="text" name ="businessDate" class="easyui-datebox" >
                </td>
                <td>
                    <input class="easyui-filebox" id="file1" style="width:200px" data-options="buttonText:'选择文件',multiple:true<%--, accept:'text/plain'--%> "/>
                    <a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-hamburg-up',plain:true" id="uploadFile1">上传</a>
                </td>
            </tr>
            <tr><td colspan="10">
                <form id="searchForm">
                    <table align="center">
                        <tr>
                            <th>渠道名称:</th>
                            <td>
                                <input class="easyui-combobox"  style = "width:200px;" id="selectChannelCode" name ="channelCode" data-options="
					     url:'${ctx}/combobox/queryChannelInfo',
					     valueField:'ID',
					     textField:'NAME',
					     validType:'length[1,50]',
					     "/>
                            </td>
                            <th>上传日期</th>
                            <td>
                                <input id="theDate" type="text" name ="businessDate" class="easyui-datebox" >
                            </td>
                            <th>文件类型</th>
                            <td>
                                <select id="theType" class="easyui-combobox" name="type" style="width:100px;">
                                    <option value="3">申请文件</option>
                                    <option value="2">确认文件</option>
                                    <option value="1">行情文件</option>
                                </select>
                            </td>
                        </tr>
                        <tr><td colspan="5" align="center">
                            <a href="javascript:void(0);" class="easyui-linkbutton" id="serchBtn" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-hamburg-down',plain:true" onclick="downloadFile();">下载</a>
                        </td></tr>
                    </table>
                </form>
                <form name="downloadFrame" id="downloadExcel" style="display: none;" frameborder="0">
                    <input type="text" id="disChannelCode" name="channelCode">
                    <input type="text" id="disDate" name="businessDate">
                    <input type="text" id="disType" name="type">
                    <input type="text" id="ckNames" name="ckNames">
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