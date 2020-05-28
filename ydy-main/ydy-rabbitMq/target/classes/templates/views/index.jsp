<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.js"></script> 
</head>
<script type="text/javascript">
$(function(){
	//建立websocket连接
	var ws = null;
	if(ws==null){
		ws = new WebSocket("ws://kaif-9798:9003/chat");
	}
	//监听打开事件
	ws.onopen = function(){
		$.messager.alert('info','聊天通道已开启!','info');
	}
	//接收后台传过来的json数据,并展示
	ws.onmessage = function (evt) {
		var data = $.parseJSON(evt.data);
		alert("date");
	}
	//关闭连接
	$("#close").on('click',function(e){
		if(ws != null){
            ws.close();
            ws = null;
		}
	});
	//发送消息
	$("#dianji").on('click',function(e){
		if(ws == null){
			 alert("连接已断开");
		 }
		var message = "发送消息！";
		ws.send(JSON.stringify(message));
	});
	
});

</script>
<body>
	<div>
		<button id ="dianji">点击</button>
		<button id ="close">关闭</button>
	</div>
	
	<div id = "show" style = "width:500px;height:500px;border:1px dashed #000;">
			
	</div>
</body>
</html>