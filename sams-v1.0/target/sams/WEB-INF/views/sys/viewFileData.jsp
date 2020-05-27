<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>确认文件查看</title>
<script type="text/javascript">
    var showTypeList = new Array();
	$(function(){
		var date = $('#startDate').datebox('getValue');
		var channelId = $("#search_channelId").combobox('getValue');
		 //统一方法动态拼接title------------------开始
        var typeList = new Array();
        typeList.push("07");
        typeList.push("02");
        typeList.push("04");
        typeList.push("05");
        typeList.push("06");
        typeList.push("44");
        typeList.push("R2");
        typeList.push("94");
        typeList.push("26");
        /*for (var tt in typeList) {
            var typ = typeList[tt];
            makeDatagridTitle(typ);
		}*/
        //拼接方法
        function makeDatagridTitle(fileType,channelId,date) {
            var tableColumns = new Array();
            $.getJSON('${ctx}/viewFileData/getFileTitle?type='+fileType, null, function (result) {//通过方法获得后台数据，该方法不唯一。
                for (var i = 0; i < result.length; i++) {//遍历获得的后台数据，这是需要动态显示的表头数据源
                    //长度 后台根据字段长度动态获取
                    var wid = parseInt(result[i].titleWidth);
                    var columnField = result[i].titleField;
                    //拼接列标题名
                    var titleName = result[i].titleNameEn+"<br/>"+result[i].titleNameCn;
                    //特殊判断 ！！！
                    var tableColumn;
					if(fileType == "07" && columnField == 'FUNDSTATUS'){
                        tableColumn = {
                            field: columnField, title:titleName , width: wid,
							halign:'center',align:'center',autoRowHeight:false,
                            formatter:function(value){
                                if(value == "0"){return "可申购赎回";}
                                else if(value == "1"){return "发行";}
                                else if(value == "4"){return "停止申购赎回";}
                                else if(value == "5"){return "停止申购";}
                                else if(value == "6"){return "停止赎回";}
                                else if(value == "8"){return "基金终止";}
                                else if(value == "9"){return "基金封闭";}
                                else{return value;}
                            }
                        };
					}else{
                        tableColumn = {
                            field: columnField, title:titleName , width: wid,
							halign:'center',align:'center',autoRowHeight:false
                        };
					}
                    tableColumns.push(tableColumn);//将需要动态显示的列，存入列数组中
                }
                initTable(tableColumns,fileType,channelId,date);
            });

        }
		//加载开始获取开启的数据
		// 默认开启的数据
        makeDatagridTitle(typeList[0],channelId,date);
        showTypeList.push(typeList[0]);
        //点击单个面板触发后台查询操作
        $("#accordionDiv").accordion({
            onSelect:function(title,index){
                var date = $('#startDate').datebox('getValue');
                var channelId = $("#search_channelId").combobox('getValue');
                makeDatagridTitle(typeList[index],channelId,date);
				showTypeList.push(typeList[index]);
			},
            onUnselect:function(title,index	){
                var local = $.inArray(typeList[index], showTypeList); //根据元素值查找下标，不存在返回-1
                showTypeList.splice(local,1);//根据下标删除一个元素   1表示删除一个元素
			}
		})

		 //统一方法动态拼接title------------------结束

        function initTable(tableColumns,fileType,theChannelId,theDate) {
            $('#'+fileType+'confirmFile').datagrid({
                url : '${ctx}/viewFileData/getFileData',
                pagination : true,
                pageSize : 10,
                pageList : [10,20, 40, 50, 100, 200, 300,
                    400, 500 ],
                rownumbers : true,
                fit :false,
                border : false,
                queryParams : {
                    date:theDate,
                    channelId : theChannelId,
                    type:fileType,
                },
                columns: [
                    tableColumns,//通过js动态生成，这是关键。
                ]
            });
        }
		$("#toSelectId").click(function(){
            var startDate = $('#startDate').datebox('getValue');
            var channelId = $("#search_channelId").combobox('getValue');
            showTypeList
            for (var tt in showTypeList) {
                // var date = $('#startDate').datebox('getValue');
                var typ = showTypeList[tt];
                makeDatagridTitle(typ,channelId,startDate);
            }
		})
		// $("#tb").appendTo('.datagrid-toolbar');
	});
	
	//按条件查询
	function queryConfirmFileData(){

	}
	// test 
	function test (){
		$.ajax({
		    url: "${ctx}/additional/test",
		    dataType:"json",   //返回格式为json
	        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	        data:{},    //参数值
	        type:"POST",   //请求方式
	        success :function(result){
	        	debugger;
	        	var msg = result.msg;
	        	msg = msg.replace(/\n/g,"<br/>");
	        	$.messager.alert({
	        		width:'auto',// 定义消息窗口的宽度。默认是 250。
	        		height:'auto',// 定义消息窗口的高度。默认是 100。
	        		title:"",// 头部面板上显示的标题文本。
	        		msg:msg,// 要显示的消息文本。
	        	});
	        },
		});
	}
	 //测试
	 /* $(function(){
		 $("#uploadFile").click(function () {
				debugger;
		        var file = $("#file1").filebox("files");
		        //执行ajax远程文件上传操作
		        var fd = new FormData();
		        fd.append("file", file);
		        $.ajax({
	                url:"${ctx}/additional/test",
	                type:"post",
	                data:fd,
	                cache: false,
	                processData: false,
	                contentType: false,
	                success:function(data){
	                	
	                },
	            });
		  })
	 }); */

</script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>渠道编号<input class="easyui-combobox"  style = "width:250px;"  id="search_channelId"   name ="search_channelId" value = "TTTNETB3" data-options="
					url:'${ctx}/combobox/queryChannelInfo',
					valueField:'ID',
					textField:'NAME',required:true" ></td>
				<td>
					日期:<input class="easyui-datebox" id="startDate" name="startDate" value = "20190419"/>
				</td>
				<td><input class="redbutton" type="button" id="toSelectId" value="查询" <%--onclick="queryConfirmFileData()"--%>></td>
				 <td><input class="redbutton" type="button" id="getTest" value="测试" onclick="test()"></td>
				<!--<td>
                    <input class="easyui-filebox" id="file1" multiple="true" style="width:200px" data-options="buttonText:'选择文件',"/>
                    <a href="javascript:void(0);" class="easyui-linkbutton"	data-options="iconCls:'icon-hamburg-up',plain:true" id="uploadFile">上传</a>
                </td> -->
			</tr>
		</table>
	</div>
	<div class="easyui-accordion" id="accordionDiv" data-options="multiple:true" style="width:99%">
		<div id="07div" title="07文件内容列表:">
			<table id="07confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "02文件内容列表:">
			<table id="02confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "04文件内容列表:">
			<table id="04confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "05文件内容列表:">
			<table id="05confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "06文件内容列表:">
			<table id="06confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "44文件内容列表:">
			<table id="44confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "R2文件内容列表:">
			<table id="R2confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "94文件内容列表:">
			<table id="94confirmFile" style="height:300px;"></table>
		</div>
		<div class="theAccordion" title= "26文件内容列表:">
			<table id="26confirmFile" style="height:300px;"></table>
		</div>
	</div>
	
</body>
<iframe class="filename" :src="/WEB-INF/views/sys/阿里巴巴Java开发手册泰山版.pdf" width='100%' height='600' frameborder='1' ></iframe>
</html>