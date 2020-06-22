<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.sams.common.constant.Global"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<%
	request.setAttribute("adminPath", Global.getAdminPath());
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海国际信托代销管理系统管理首页</title>
<style type="text/css">
.tabs {
  list-style-type: none;
  color: #c31a23;
  height: 26px;
  margin: 0px;
  padding: 0px;
  padding-left: 4px;
  width: 50000px;
  border-style: solid;
  border-width: 0 0 1px 0;
  
}
</style>
<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var index_menu_tree;

    $(function() {
        
    	$(".index_top_linkbutton").hover(
			function () {
				$(this).css({color:"#0A0A0A"});
			},
			function () {
				$(this).css({color:"#FFF"});
			}
		);
    	
        index_layout = $('#index_layout').layout({
            fit : true
        });
        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'icon-refresh',
                handler : function() {
            		refreshTab(index_tabs);
                }
            }, {
                iconCls : 'icon-del',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });
        index_menu_tree = $('#menutree').tree({
            url : '${ctx}/sysResource/userMenuTree/json',
            parentField : 'pid',
            lines : false,
            onLoadSuccess: function (node, data) {
            	var rooNode = $('#menutree').tree('getRoots');
                var sum = rooNode.length;
                for (var i = 0 ; i < sum ; i++){
                	if( i > 2 ){
                		$("#menutree").tree("collapse", rooNode[i].target);
                	}
                }
            }, 
            onClick : function(node) {
            	if (node.attributes && node.attributes.url) {
            		var url = '${ctx}' + node.attributes.url;
            		addTab({
            			url : url,
            			title : node.text,
            			iconCls : node.iconCls
            		});
            	}
            }	
        });
    });

    /**     
     *刷新tab 
     *example: {tabTitle:'tabTitle',url:'refreshUrl'} 
     *如果tabTitle为空，则默认刷新当前选中的tab 
     *如果url为空，则默认以原来的url进行reload 
     */  
    function refreshTab(cfg){  
        var refresh_tab = cfg.tabTitle?$('#index_tabs').tabs('getTab',cfg.tabTitle):$('#index_tabs').tabs('getSelected');  
        if(refresh_tab && refresh_tab.find('iframe').length > 0){
            var _refresh_ifram = refresh_tab.find('iframe')[0];
            var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
            _refresh_ifram.contentWindow.location.href=refresh_url;
        }  
    }

    function addTab(params) {
    	var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
    }

    function logout(){
        $.messager.confirm('提示','确定要退出?',function(r){
            if (r){
                progressLoad();
                $.post('${ctx}${adminPath}/logout', function(result) {
                    if(result.success){
                        progressClose();
                        window.location.href='${ctx}';
                    }
                }, 'json');
            }
        });
    }

    function editUserPwd() {
        parent.$.modalDialog({
            title : '修改密码',
            width : 260,
            height : 220,
            href : '${ctx}/sysUser/editPwdPage',
            buttons : [ {
                text : '提交',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                    f.submit();
                }
            } ]
        });
    }

    function userView() {
    	parent.$.modalDialog({
            title : '个人信息',
            width : 500,
            height : 400,
            href : '${ctx}/sysUser/currentUserView',
            buttons : [ {
                text : '保存',
                iconCls:'icon-save',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            } ]
        });
        
    }
    

  	//a:link {color: #FFF}     /* 未访问的链接 */
	//a:visited {color: #00FF00}  /* 已访问的链接 */
	//a:hover {color: #FF00FF}    /* 当有鼠标悬停在链接上 */
	//a:active {color: #0000FF}   /* 被选择的链接 */
    function menuOnShowHandler(){
    	setColoe("#user_select_menu","#0A0A0A");
	}
    function menuOnHideHandler(){
    	setColoe("#user_select_menu","#fff");
	}
	function setColoe(selector,colorHex){
		$(selector).css("color",colorHex);
	}
	function setTheme(themeName, alias){
		$.cookie("themeName", themeName, { expires: 30 });
		document.getElementById("themeCss").setAttribute("href", "${easyuiPath}/themes/" + themeName + "/easyui.css"); 
		$.messager.show({
            title : '操作提醒',
            msg : '切换系统主题：' + alias,
            timeout : 1000
        });
		//刷新Tabs
		var cfg = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
		refreshTab(cfg);
	}

</script>
</head>
<body>
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${staticPath}/images/core/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <div id="index_layout">
        <div data-options="region:'north',border:false" style=" overflow: hidden;">
            <div id="header"  style="color: #b41f2f">
            <img src="${ctx}/static/images/logo.png" width="405" height="60" style="margin-left: 30px;"/>
           		<!-- <span style="margin-left: 10px; color: #fff; font-size: 28px; font-weight:bold;">上 海 国 际 信 托 代 销 管 理 系 统</span> -->
           		<span style="color: #3F4752; font-size: 18px; font-weight:bold;"></span>
				<span style="float: right; padding-right: 20px;">&nbsp;
	    			<a href="javascript:void(0)" id="user_select_menu" class="easyui-menubutton"  data-options="menu:'#menu_select', iconCls:'icon-cologne-user'" style="color: #fff"><shiro:principal/></a>   
					<div id="menu_select" style="width:150px;" data-options="noline:true,onShow:menuOnShowHandler,onHide:menuOnHideHandler">   
					    	<div onclick="javascript:userView()" data-options="iconCls:'icon-standard-vcard'">个人信息</div>
					    	<div onclick="javascript:editUserPwd()" class="my_menu_select" data-options="iconCls:'icon-standard-user-edit'">修改密码</div>  
					    	<div class="menu-sep"></div>
							<div>
								<span>系统主题</span>
								<div style="width:150px;">
								<div onclick="javascript:setTheme('gray','灰色(默认)')">灰色(默认)</div>
								<div onclick="javascript:setTheme('default','蓝色')">蓝色</div>
								<div onclick="javascript:setTheme('black','黑色')">黑色</div>
								<div onclick="javascript:setTheme('bootstrap','bootstrap')">bootstrap</div>
								<div onclick="javascript:setTheme('material','material')">material</div>
								<div onclick="javascript:setTheme('metro','metro')">metro</div>
								<div onclick="javascript:setTheme('metro-blue','metro-blue')">metro-blue</div>
								<div onclick="javascript:setTheme('metro-gray','metro-gray')">metro-gray</div>
								<div onclick="javascript:setTheme('metro-green','metro-green')">metro-green</div>
								<div onclick="javascript:setTheme('metro-orange','metro-orange')">metro-orange</div>
								<div onclick="javascript:setTheme('metro-red','metro-red')">metro-red</div>
								<div onclick="javascript:setTheme('ui-cupertino','ui-cupertino')">ui-cupertino</div>
								<div onclick="javascript:setTheme('ui-dark-hive','ui-dark-hive')">ui-dark-hive</div>
								<div onclick="javascript:setTheme('ui-pepper-grinder','ui-pepper-grinder')">ui-pepper-grinder</div>
								<div onclick="javascript:setTheme('ui-sunny','ui-sunny')">ui-sunny</div>
								</div>
							</div>
					</div>
					<a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton index_top_linkbutton" style="color: #fff" plain="true" icon="icon-cologne-login" >安全退出</a>
	    		</span>
    		</div>
            
        </div>
        <div data-options="region:'west',split:true" title="主导航" style="width: 230px; overflow: hidden;overflow-y:auto; padding:0px;">
			<div style="padding: 0px 0px 0px 0px; ">
               		<ul id="menutree"/>
            </div>
        </div>
        <div data-options="region:'center'" style="overflow: hidden;">
            <div id="index_tabs">
                <div title="首页" iconCls="icon-home" data-options="border:false" style="overflow: hidden;">
					<!--<iframe src="http://blog.csdn.net/hq" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>-->
                  <!--   <script src='https://git.oschina.net/hq/sams/widget_preview'></script> -->
                    <style>
                        .pro_name a{color: #4183c4;}
                        .osc_git_title{background-color: #d8e5f1;}
                        .osc_git_box{background-color: #fafafa;}
                        .osc_git_box{border-color: #ddd;}
                        .osc_git_info{color: #666;}
                        .osc_git_main a{color: #4183c4;}
                        
                        /*  */
                        .index-children{
							width : 325px;
							height :190px;
							float:left;
							margin-left:100px;
						}
						.index-children p {
							font-size : 14px;
						}
                    </style>
                    
                    <!--  -->
                    <div style = "width :99%;height:99%;overflow-y: auto;">
                  	<div class = "index-children">
	                  <h2>0、丰利F</h2>
						<p>
						认购申请(020) : 募集期 <br>
						认购确认(120) : 募集期,T+1日给到确认<br>
						认购结果(130) : 成立日给到确认<br>
						强制赎回(142) : 到期日+1给到确认<br>
						红利发放(143) : 每个月的15号给到确认,只针对已成立未到期的产品<br>
						</p>
				 </div>
				 <div class = "index-children">
                  <h2>1、丰利B</h2>
                  	<p>
                  	申购申请(022) : 运作期<br>
                  	赎回申请(024) : 运作期<br>
                  	申购确认(122) : 运作期,T+1日给到确认<br>
                  	赎回确认(124) : 运作期,T+1日给到确认<br>
                  	红利发放(143) : 每个月的15号给到确认,只针对有份额的用户<br>
                  	</p>
                  	</div>
				 <div class = "index-children">
                  <h2>2、丰利D</h2>
                  <p>
                  	申购申请(022) : 运作期<br>
                  	赎回申请(024) : 运作期<br>
                  	申购确认(122) : 运作期,T+1日给到确认<br>
                  	赎回确认(124) : 运作期,T+1日给到确认<br>
                  	红利发放(143) : 每个月的15号给到确认,只针对有份额的用户<br>
                  	</p>
                  	</div>
				 <div class = "index-children">
                  <h2>3、红宝石7天</h2>
                  <p>
                  	申购申请(022) : 每周二<br>
                  	赎回申请(024) : 每周一<br>
                  	申购确认(122) : T+1日给到确认<br>
                  	赎回确认(124) : T+2日给到确认<br>
                  	红利发放(143) : 每个季度的末月15号给到确认,只针对有份额的用户<br>
                  </p>
                  </div>
				 <div class = "index-children">
                  <h2>4、固收</h2>
                  	<p>
                 	认购申请(020) : 募集期 <br>
					认购确认(120) : 募集期,T+1日给到确认<br>
					认购结果(130) : 成立日+1日给到确认<br>
					到期清盘(150) : 到期日+1给到确认<br>
					红利发放(143) : 不定期分红,只针对已成立未到期的产品<br>
					</p>
					</div>
				 <div class = "index-children">
                  <h2>5、封闭净值</h2>
                  	<p>
                  	认购申请(020) : 募集期 <br>
					认购确认(120) : 募集期,T+1日给到确认<br>
					认购结果(130) : 成立日+1日给到确认<br>
					强制赎回(142) : 到期日+2给到确认<br>
				  	</p>
				  	</div>
				 <div class = "index-children">
                  <h2>6、T+N</h2>
                  <p>
                  	认购申请(020) : 募集期 <br>
					认购确认(120) : 募集期,T+1日给到确认<br>
					认购结果(130) : 成立日+1日给到确认<br>
					强制赎回(142) : 到期日+2给到确认<br>
				  </p>
				  </div>
				 <div class = "index-children">
                  <h2>7、权益类</h2>
                   <p>
                  	认购申请(020) : 募集期 <br>
                  	申购申请(022) : 运作期<br>
                  	赎回申请(024) : 运作期<br>
					认购确认(120) : 募集期,T+1日给到确认<br>
					申购确认(122) : 运作期,T+N日给到确认<br>
                  	赎回确认(124) : 运作期,T+N日给到确认<br>
					认购结果(130) : 成立日+1日给到确认<br>
					红利发放(143) : 不定期,只针对已成立未到期的产品<br>
				  </p>
                  </div>
				 <div class = "index-children">
                  <h2>8、多币种美月盈</h2>
					<p>
					认购申请(020) : 募集期 ,运作期<br>
                  	赎回申请(024) : 运作期<br>
					认购确认(120) : 募集期、运作期,T+1日给到确认<br>
                  	赎回确认(124) : 运作期,T+N日给到确认<br>
					认购结果(130) : 成立日+1日给到确认;运作期,T+N日给到确认<br>
					红利发放(143) : 不定期,只针对已成立未到期的产品<br>
					</p>
					</div>
				 <div class = "index-children">
				  <h2>9、FOF</h2>
					<p>
					认购申请(020) : 募集期<br>
					申购申请(022) : 运作期<br>
                  	赎回申请(024) : 运作期<br>
				 	认购确认(120) : 募集期,T+1日给到确认<br>
                 	申购确认(122) : 运作期,T+N日给到确认<br>
                  	赎回确认(124) : 运作期,T+N日给到确认<br>
					认购结果(130) : 成立日+1日给到确认<br>
					</p>
					</div>
				 </div>
                    
                <!--  -->
                </div>
            </div>
        </div>
        <div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #b41f2f" >上海信托版权所有 &copy; Copyright © 2019-2020</div>
    </div>

    <!--[if lte IE 7]>
    <div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
    <![endif]-->

    <style>
        /*ie6提示*/
        #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
    
</body>
</html>