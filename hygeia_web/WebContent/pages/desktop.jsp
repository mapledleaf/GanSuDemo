<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar,java.net.URLEncoder,java.lang.StringBuffer"%>
<%@page import="com.powersi.hygeia.framework.BusiContext,com.powersi.hygeia.framework.util.UtilFunc,com.powersi.hygeia.framework.UserInfo"%>
<%@page import="com.powersi.sys.util.UserKindHelper,com.powersi.sys.user.dto.UserKindDTO,com.powersi.sys.util.MessageHelper,com.powersi.hygeia.web.util.JsonHelper"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String path = request.getContextPath();
	String imagesPath = path + "/resource/desktop/images";

	String systemName = com.powersi.hygeia.framework.ParameterMapping
			.getSystemName();
	String serverName = request.getServerName();
	UserInfo user = BusiContext.getUserInfo();
	String userName = user.getUserName();
	String loginId = UtilFunc.getString(user, "login_id", "0");
	String loginUser = user.getLoginUser();
	String userKind = user.getUserKind();
	UserKindDTO userKindDto = UserKindHelper.get(userKind);
	
	StringBuffer sb = new StringBuffer();
	sb.append(path);
	sb.append("/login/logout.action?action=relogin");
	if(userKindDto != null){
		sb.append("&logintype=");
		sb.append(userKindDto.getLoginType());
	}
	sb.append("&userkind=");
	sb.append(userKind);
	sb.append("&loginuser=");
	sb.append(URLEncoder.encode(loginUser, "GBK"));
	sb.append("&loginid=");
	sb.append(loginId);
	String reloginUrl = sb.toString();
	
	//应用列表
	String apps = com.powersi.sys.manager.action.AppManagerAction
			.getApps();
	if (apps == null || apps.length() == 0) {
		apps = "[]";
	}
	
	//背景列表
	String bgs = com.powersi.sys.manager.action.AppManagerAction.getWallpapers();
	if(bgs == null || bgs.length() == 0){
		bgs = "[]";
	}
	
	//消息设置
	String messageEnable = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("message_flag", "0");
	String messageInterval = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("message_query_interval", "180");

	String messageList = JsonHelper.toJson(MessageHelper.queryMessageList(true));
	
	//任务设置
	String taskInterval = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("task_query_interval", "180");
	String taskEnable = Integer.parseInt(taskInterval) > 0 ? "1" : "0";
	
	//公告设置
	String bulletinList = JsonHelper.toJson(MessageHelper.getBulletinlList(10));
		
	//统一门户
	String unifiedFlag = com.powersi.sys.util.SingleSignOnHelper
			.isEnabled() ? "true" : "false";
	
	//自定义桌面设置
	java.util.Map settings = com.powersi.sys.user.action.UserSettingsAction.getConfig();
	String desktopIconAlign = UtilFunc.getString(settings, "desktop_icon_align", "center");
	String desktopIconSize = UtilFunc.getString(settings, "desktop_icon_size", "small");
	String desktopWallpaper = UtilFunc.getString(settings, "desktop_background_image", ParameterMapping.getStringByCode("desktop_background_image", "Mac Lion.jpg"));
%>
<powersi:html>
<head>
<powersi:head title="<%=systemName%>" />
<script src="${strutsPath}/js/plugins/jquery.backstretch.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${strutsPath}/js/plugins/bootstrap/js/bootstrap-bootbox.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${strutsPath}/include/datepicker.js" type="text/javascript" charset="utf-8"></script>
<script src="${strutsPath}/js/plugins/jquery.slimscroll.min.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

html,body {
	overflow: hidden;
}

body {
	width: 100%;
	height: 100%;
	background: #1f53a0;
	font-size: 14px;
	padding: 0;
	margin: 0;
}

a {
	text-decoration: none !important;
}

i {
	font-size: 16px;
}

.btn {
	padding: 6px 12px;
}

.l-case-title {
	font-weight: bold;
	margin-top: 20px;
	margin-bottom: 20px;
}

#winlinks {
	position: absolute;
	left: 0;
	top: 0;
	padding: 0;
	margin: 0;
}

#winlinks ul {
	position: relative;
}

#winlinks li {
	width: 120px;
	height: 120px;
	position: absolute;
	z-index: 101;
	list-style: none;
	text-align: center;
	margin: 10px;
	cursor: pointer;
}

#winlinks li.l-over {
	
}

#winlinks li img {
	width: 64px;
	height: 64px;
}

#winlinks li span {
	margin-top: 5px;
	line-height: 18px;
	text-align: center;
	font-size: 13px;
	color: #fff;
	font-weight: 700;
	word-break: break-all;
	text-shadow: 1px 1px 1px #000, 0px 0px 1px #000, 0px 0px 1px #000, 0px
		0px 1px #000;
	display: inline-block;
	width: 100%;
}

#winlinks li.l-over span {
	
}

#winlinks li.l-over div.bg {
	display: block;
	border-radius: 0;
	border: #ddd 2px solid;
	background: url(<%=imagesPath%>/bg-white.png);
}

#winlinks li div.bg {
	display: none;
	position: absolute;
	top: -4px;
	left: -2px;
	right: -2px;
	z-index: 0;
	width: 126px;
	height: 122px;
}

.big-icon#winlinks li {
	width: 130px;
	height: 140px;
}

.big-icon#winlinks li img {
	width: 96px;
	height: 96px;
}

.big-icon#winlinks li div.bg {
	width: 136px;
	height: 142px;
}

.l-taskbar {
	background: url(<%=imagesPath%>/bg-white.png);
	border-top: 1px solid rgba(255, 255, 255, 0.4);
	height: 40px;
	line-height: 40px;
	left: 80px;
}

.l-taskbar-tasks {
	margin: 0;
}

.l-taskbar-task {
	height: 36px;
	line-height: 36px;
	margin: 2px;
	background: none;
	border-color: transparent;
	transition: all 0.30s ease-in-out;
	-webkit-transition: all 0.30s ease-in-out;
	-moz-transition: all 0.30s ease-in-out;
	border-radius: 0;
	outline: none;
}

.l-taskbar-task-active {
	background: url(<%=imagesPath%>/bg-white.gif);
	border: #aaa 1px solid;
}

.l-taskbar-task.l-taskbar-task-over {
	background: url(<%=imagesPath%>/bg-white.png);
	border: #aeb5be 1px solid;
}

.l-taskbar-task-content {
	margin-left: 40px;
	color: #fff;
	font-size: 13px;
	font-weight: 400;
	text-shadow: 1px 1px 1px #000, 0px 0px 1px #000, 0px 0px 1px #000, 0px
		0px 1px #000;
}

.l-taskbar-task-active>.l-taskbar-task-content {
	color: #fff;
}

.l-taskbar-task-over>.l-taskbar-task-content {
	color: #cd0200;
}

.l-taskbar-task-icon {
	top: -3px;
	left: 6px;
	background-image: none;
	width: 36px;
	height: 36px;
}

.l-taskbar-task-icon img {
	width: 32px;
	height: 32px;
}

.taskbar-btn {
	z-index: 12000;
	color: #fff;
	height: 40px;
	cursor: pointer;
}

.taskbar-btn>i {
	font-size: 16px;
	line-height: 40px;
	text-align: center;
	cursor: pointer;
	padding: 0 5px;
}

#taskbar_start,#taskbar_desktop {
	background: url(<%=imagesPath%>/bg-white.png);
	border-top: 1px solid rgba(255, 255, 255, 0.4);
	width: 40px;
	position: fixed;
	bottom: 0;
	padding: 4px;
}

.l-taskbar-task-img {
	width: 32px;
	height: 32px;
}

#taskbar_start {
	left: 0px;
}

#taskbar_desktop {
	left: 40px;
}

#taskbar_time {
	position: fixed;
	bottom: 0;
	right: 10px;
	width: 80px;
	cursor: default;
	text-align: center;
	padding: 2px;
	font-size: 12px;
}

#taskbar_notice {
	position: fixed;
	bottom: 0;
	right: 90px;
}

.notice-list{
	overflow: auto;
}
#taskbar_min {
	position: fixed;
	bottom: 0;
	right: 0px;
	width: 10px;
	background: url(<%=imagesPath%>/bg-white.png);
	border-left: 1px solid rgba(250, 250, 250, 0.3);
}

#taskbar_desktop.l-over {
	background: url(<%=imagesPath%>/bg-white.gif);
}

#taskbar_min.l-over,#taskbar_time.l-over,#taskbar_notice.l-over {
	filter: Alpha(opacity = 50);
	opacity: 0.5;
}

#taskbar_start.l-over {
	
}

.l-dialog {
	-moz-box-shadow: none;
	-webkit-box-shadow: none;
	box-shadow: none;
}

.l-dialog-tc {
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	background: #f9f9f9 !important;
	padding-left: 10px;
}

.l-dialog,.l-dialog-cl,.l-dialog-cc,.l-dialog-cr {
	border-top: 1px solid #ddd;
}

.l-dialog-tl,.l-dialog-tc,.l-dialog-tr {
	border-bottom: 1px solid #ddd;
}

.l-dialog-br,.l-dialog-bl,.l-dialog-cl,.l-dialog-cr,.l-dialog-bl,.l-dialog-bc,.l-dialog-br,.l-dialog-tl,.l-dialog-tr
	{
	background: #ddd;
}

.l-dialog-title {
	text-align: center;
	padding: 0;
}

.l-icon-layout {
	background: url('${strutsPath}/icon/layout-3.png') no-repeat center;
}

.l-icon-desktop {
	background: url('${strutsPath}/icon/desktop.png') no-repeat center;
}

.l-icon-check {
	background: url('${strutsPath}/icon/tick.png') no-repeat center;
}

.dropdown-toggle,.dropdown-menu {
	z-index: 11000;
}

.modal-dialog {
	width: 600px;
	margin: 10px auto;
}

.popup-mask {
	position: absolute;
	z-index: -10;
	width: 100%;
	height: 100%;
	left: 0;
	bottom: 0;
	background: transparent;
}

#startmenu {
	margin: 0;
	padding: 0;
	z-index: 11000;
	width: 340px;
	height: 370px;
	bottom: 40px;
	background: #fff;
}

#startmenu_container {
	height: 328px;
}

#startmenu .startmenu-title {
	background-color: #f9f9f9;
	border-bottom: 1px solid #ddd;
	margin: 0;
	padding: 0 42px 0 40px;
	position: relative;
	height: 40px;
	line-height: 40px;
	overflow: hidden;
	font-size: 14px;
}

#startmenu .startmenu-title .username {
	position: absolute;
	left: 10px;
	top: 0;
}

#startmenu .startmenu-title .logout {
	position: absolute;
	right: 10px;
	top: 0;
}

#startmenu .startmenu-title i {
	font-size: 16px;
}

#startmenu .startmenu-container {
	padding: 0;
	overflow: auto;
	position: relative;
	border-top: 1px solid #FFF;
}

#startmenu ul {
	position: relative;
}

#startmenu li {
	width: 80px;
	cursor: pointer;
	height: 80px;
	position: absolute;
	list-style: none;
	text-align: center;
	margin: 10px;
}

#startmenu li.l-over {
	
}

#startmenu li img {
	width: 40px;
	height: 40px;
}

#startmenu li span {
	margin-top: 5px;
	line-height: 18px;
	text-align: center;
	font-size: 13px;
	color: #000;
	word-break: break-all;
	display: inline-block;
	width: 100%;
}

#startmenu li.l-over span {
	
}

#startmenu li.l-over div.bg {
	display: block;
	border-radius: 0;
	background: #e5f3fb;
	border: 1px solid #70c0e7;
}

#startmenu li div.bg {
	display: none;
	position: absolute;
	top: -4px;
	left: -4px;
	right: -4px;
	z-index: -10;
	width: 92px;
	height: 92px;
	background: url(<%=imagesPath%>/bg-white.gif);
}

#wallpaper_content {
	overflow: auto;
	height: 500px;
}

#wallpaper_content img {
	height: 143px;
}

#wallpaper_content a.thumbnail {
	border-width: 2px;
	border-color: #eee;
}

#wallpaper_content a.thumbnail:hover {
	border-color: #66afe9;
}

#wallpaper_content a.thumbnail.selected {
	border-color: #f59942 !important;
}

#datepicker_popover {
	z-index: 11000;
	position: fixed;
	bottom: 40px;
	right: 0;
	left: auto;
	top: auto;
}

#datepicker_title,#notice_title {
	text-align: left;
	background-color: #f9f9f9;
	border-bottom: 1px solid #e1e1e1;
	color: #1e0fbe;
	height: 40px;
	line-height: 40px;
	padding: 0 14px;
	cursor: pointer;
}

#notice_popover {
	z-index: 11000;
	position: fixed;
	bottom: 40px;
	right: 0px;
	left: auto;
	top: 0;
	width: 330px;
	max-width: 330px;
	overflow: auto;
	display: block;
}

.popup-msg {
	height: 90px;
	display: table;
	width: 99%;
	margin: 0 auto;
	background: url('${strutsPath}/css/images/ligerui/win/information.png')
		no-repeat left center;
}

.popup-msg a {
	display: table-cell;
	width: 240px;
	vertical-align: middle;
	font-size: 14px !important;
	text-align: left;
	padding-left: 60px;
}

#datepicker_popover,#notice_popover,#startmenu {
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, 0.5);
	box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.2);
}

#notice_popover .popover-content {
	padding: 0 14px;
}

.notice-count{
	vertical-align: baseline;
}

.notice-list{
	margin-bottom: 0;
	background-color: #fff;
}

.notice-list li{
	list-style: none;
	line-height: 28px;
	height: 28px;
}

.notice-list li:hover{
	background-color: #e5f3fb;
}

.notice-list li .col1 {
	float: left;
	text-align: left;
  	width: 220px;
  	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.notice-list li .col2 {
  float: right;
  text-align:right;
  color: #999;
  font-size: 12px;
}

.slimScrollBar{
	background-color: #bbb !important;
}

.icon-animated-bell {
	display:inline-block;
	-moz-animation:ringing 2.0s 5 ease 1.0s;
	-webkit-animation:ringing 2.0s 5 ease 1.0s;
	-o-animation:ringing 2.0s 5 ease 1.0s;
	-ms-animation:ringing 2.0s 5 ease 1.0s;
	animation:ringing 2.0s 5 ease 1.0s;
	-moz-transform-origin:50% 0;
	-webkit-transform-origin:50% 0;
	-o-transform-origin:50% 0;
	-ms-transform-origin:50% 0;
	transform-origin:50% 0
}
@-moz-keyframes ringing {
	0% {
	-moz-transform:rotate(-15deg)
}
2% {
	-moz-transform:rotate(15deg)
}
4% {
	-moz-transform:rotate(-18deg)
}
6% {
	-moz-transform:rotate(18deg)
}
8% {
	-moz-transform:rotate(-22deg)
}
10% {
	-moz-transform:rotate(22deg)
}
12% {
	-moz-transform:rotate(-18deg)
}
14% {
	-moz-transform:rotate(18deg)
}
16% {
	-moz-transform:rotate(-12deg)
}
18% {
	-moz-transform:rotate(12deg)
}
20% {
	-moz-transform:rotate(0deg)
}
}@-webkit-keyframes ringing {
	0% {
	-webkit-transform:rotate(-15deg)
}
2% {
	-webkit-transform:rotate(15deg)
}
4% {
	-webkit-transform:rotate(-18deg)
}
6% {
	-webkit-transform:rotate(18deg)
}
8% {
	-webkit-transform:rotate(-22deg)
}
10% {
	-webkit-transform:rotate(22deg)
}
12% {
	-webkit-transform:rotate(-18deg)
}
14% {
	-webkit-transform:rotate(18deg)
}
16% {
	-webkit-transform:rotate(-12deg)
}
18% {
	-webkit-transform:rotate(12deg)
}
20% {
	-webkit-transform:rotate(0deg)
}
}@-ms-keyframes ringing {
	0% {
	-ms-transform:rotate(-15deg)
}
2% {
	-ms-transform:rotate(15deg)
}
4% {
	-ms-transform:rotate(-18deg)
}
6% {
	-ms-transform:rotate(18deg)
}
8% {
	-ms-transform:rotate(-22deg)
}
10% {
	-ms-transform:rotate(22deg)
}
12% {
	-ms-transform:rotate(-18deg)
}
14% {
	-ms-transform:rotate(18deg)
}
16% {
	-ms-transform:rotate(-12deg)
}
18% {
	-ms-transform:rotate(12deg)
}
20% {
	-ms-transform:rotate(0deg)
}
}@keyframes ringing {
	0% {
	transform:rotate(-15deg)
}
2% {
	transform:rotate(15deg)
}
4% {
	transform:rotate(-18deg)
}
6% {
	transform:rotate(18deg)
}
8% {
	transform:rotate(-22deg)
}
10% {
	transform:rotate(22deg)
}
12% {
	transform:rotate(-18deg)
}
14% {
	transform:rotate(18deg)
}
16% {
	transform:rotate(-12deg)
}
18% {
	transform:rotate(12deg)
}
20% {
	transform:rotate(0deg)
}
</style>
</head>
<body>
	<div class="l-tab-loading" style="display:block;"></div>
	
    <div id="winlinks">
        <ul>
        </ul>
    </div>
    
   	<div id="taskbar_start" title="开始" class="taskbar-btn dropup">
   		<img id="img_start" src="<%=imagesPath%>/start.png" class="l-taskbar-task-img dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false" />
		<div id="startmenu" class="dropdown-menu">        	
		 	<iframe src="about:blank" frameborder="0" class="popup-mask"></iframe>
		
		    <div id="startmenu_title" class="startmenu-title clearfix">
		        <div class="startmenu-title-item username">
		        	<a href="javascript:void(0);" onclick="doUser();" title="用户设置">
		        		<i class="icon-user"></i>
		        		<%=userName %>
		        	</a>
		        </div>
		        <div class="startmenu-title-item logout">
		            <a href="javascript:void(0);" onclick="doExit();" title="安全退出">
		            	<i class="icon-off"></i>
		            	退出
		            </a>
		        </div>
		    </div>
		    <div id="startmenu_container" class="startmenu-container">
				<div id="startmenu_app" class="startmenu-app clearfix"
					unselectable="on" onselectstart="return false;">
					<ul>
					</ul>
				</div>
			</div>
 		</div>
   	</div>
   	<div id="taskbar_desktop" class="taskbar-btn" title="显示桌面">
   		<img src="<%=imagesPath%>/screen.png" class="l-taskbar-task-img" />
   	</div>
   	<div id="taskbar_notice" class="taskbar-btn" title="通知中心">
   		<i class="fa fa-bell"></i>
   	</div>
   	<div id="taskbar_time" class="taskbar-btn">
   	</div>
   	<div id="taskbar_min" class="taskbar-btn" title="显示桌面">
   	</div>
   	<div class="popover fade in" id="notice_popover" style="display: none;">
   		<iframe src="about:blank" frameborder="0" class="popup-mask"></iframe>
   		<h3 class="popover-title" id="notice_title">
   			通知中心
   		</h3>
   		<div class="popover-content" id="notice_content">
   			<powersi:panelbox allowCollapse="false">
   				<powersi:panelbox-title>
					<a href="javascript:openNotice('bulletin')" title="公告列表" class="orange"><i class='icon-bullhorn'></i>公告[<span id="notice_bulletin_count" class="notice-count">0</span>]</a>
				</powersi:panelbox-title>
   				<powersi:panelbox-toolbar>
					<a href="javascript:openNotice('bulletin')" title="公告列表"><i class="icon-external-link green"></i></a>
				</powersi:panelbox-toolbar>
   				<ul class="notice-list" id="notice_bulletin">
   				</ul>
   			</powersi:panelbox>
   			<powersi:panelbox allowCollapse="false">
   				<powersi:panelbox-title>
					<a href="javascript:openNotice('task')" title="任务列表" class="orange"><i class='icon-tasks'></i>任务[<span id="notice_task_count" class="notice-count">0</span>]</a>
				</powersi:panelbox-title>
   				<powersi:panelbox-toolbar>
					<a href="javascript:openNotice('task')" title="打开"><i class="icon-external-link green"></i></a>
				</powersi:panelbox-toolbar>
   				<ul class="notice-list" id="notice_task">
   				</ul>
   			</powersi:panelbox>
   		</div>
   	</div>
   	<div class="popover fade in" id="datepicker_popover" style="display: none;">
   		<iframe src="about:blank" frameborder="0" class="popup-mask"></iframe>
   		<h3 class="popover-title" id="datepicker_title"></h3>
   		<div class="popover-content" id="datepicker_content"></div>
   	</div>
   	<div class="hidden">
   		<div id="wallpaper_dlg">
   			<div class="container-fluid">
   				<div class="row" id="wallpaper_content">
   				</div>
   			</div>
   		</div>
   	</div>
<script type="text/javascript">
	var systemname = '<%=systemName%>';
	
	var apps = <%= apps %>;
	var bgs = <%= bgs %>;
	
	var messageEnable = '<%=messageEnable%>' == '1';
	var messageInterval = parseInt('<%=messageInterval%>');
	var messageList = <%=messageList%>;
	var bulletinList = <%=bulletinList%>;
	var taskEnable = '<%=taskEnable%>' == '1';
	var taskInterval = parseInt('<%=taskInterval%>');
	
	var imagesPath = '<%=imagesPath%>';
	
	var desktopIconAlign = "<%=desktopIconAlign%>";
	var desktopIconSize = "<%=desktopIconSize%>";
	var desktopWallpaper = "<%=desktopWallpaper%>";
	
	var customLink = {
			'user':{
				'url': '/login/settings!queryUserInfo.action',
				'title': '用户信息',
				'icon': 'list-user.png',
				'isMax': false,
				'height': 450,
				'width': 600
			},
			'message': {
				'url': '/message/MessageAction!managerMessage.action',
				'title': '消息列表',
				'icon': 'query-mail.png',
				'isMax': false
			},
			'bulletin': {
				'url': '/message/BulletinManagerAction!list.action',
				'title': '公告列表',
				'icon': 'speaker-list.png',
				'isMax': false
			},
			'task': {
				'url': '/pages/sys/message/TaskList.jsp',
				'title': '任务列表',
				'icon': 'task.png',
				'isMax': false
			}
	};
	
	var links = [];
	$(function() {
		//初始化桌面
	    try {
	    	init();
	    	
	    	$.ligerui.win.removeTaskbar = function() {};
	        $.ligerui.win.createTaskbar();
	        
	        linksInit();
	        menusInit();
	        contextMenuInit();
	
	        $(window).resize(onResize);
	        onResize();
	
	        bootbox.setDefaults({
	            locale: 'zh_CN'
	        });
	
	        taskbarInit();
	    } catch(ex) {
		}
	
	    //关闭等待
	    $('.l-tab-loading').hide();
	
	    //桌面背景
	    setBackground(desktopWallpaper);
	    
	  	//消息处理
        setTimeout('initMessage()', 0);
	});

	function setBackground(bg){
		$.backstretch(imagesPath + "/wallpaper/" + bg, {
	        fade: 2000
	    });
	}
	
	function init() {
		try{
			$.each(apps, function(i, app){
				var link = {};
				
				if(powersi.length(app['app_ico']) == 0  || app['app_ico'].indexOf("://") < 0){
					link['icon'] =  imagesPath + '/appico/' + (app['app_ico'] || 'application.png');
				} else {
					link['icon'] = 	app['app_ico'];
				}
				
				link['title'] = app['app_name'];
				link['desc'] = app['app_desc'] || app['app_name'];
				link['url'] = app['app_url'];
								
				link['showDesktop'] = app['desktop_flag'] != '0';
				link['showMenubar'] = app['menubar_flag'] != '0';
				try{
					if(powersi.length(app['app_feature']) > 0){
						$.extend(link, powersi.tojson(app['app_feature']));
					}
				} catch(fex){
					alert(fex.message);
				}
				
				links.push(link);
			});
		} catch(ex){}
	}
	
	var appDefault = {
		width : 800,
		height : 600,
		showMax : true,
		showMin : true,
		showToggle : false,
		isResize : true,
		modal : false,
		slide : false,
		isHidden: false
	};
	
	var wins = {};
	function openLink(link) {
	    if (!link) {
	        return;
	    }

	    var url = link.url;
	    var title = link.title;
	    var icon = link.icon;
	    var win = wins[url];

	    if (win) {
	        win.active();
	        $.ligerui.win.activeTask(win);
	    } else {
	        var param = $.extend({
	            "onClosed": function() {
	                delete wins[url];
	            }
	        },
	        appDefault, link);
	        if(powersi.length(url) > 0  && url.charAt(0) == '/'){
	        	param.url = rootPath + url;
			}
	        param.height += dialogTitleHeight;
	        win = $.ligerDialog.open(param);

	        wins[url] = win;

	        if (link.isMax != false && link.showMax != false) win.max();

	        var task = jQuery.ligerui.win.tasks[win.id];
	        if (task) {
	        	if(powersi.length(icon) == 0){
					icon =  imagesPath + '/appico/application.png';
				} else if (icon.indexOf("/") < 0){
					icon =  imagesPath + '/appico/' + icon;
				}
	        	
	        	$(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');

	            $(task).attr('title', title);

	            task.menu = $.ligerMenu({
	                items: [{
	                    text: '取消',
	                    id: 'cancel'
	                },
	                {
	                    line: true
	                },
	                {
	                    text: '关闭',
	                    id: 'close',
	                    click: function() {
	                        win.close();
	                    },
	                    icon: 'cross'
	                },
	                {
	                    text: '关闭其他',
	                    id: 'closeother',
	                    click: function() {
	                        closeWins(win.id);
	                    }
	                },
	                {
	                    text: '关闭全部',
	                    id: 'closeall',
	                    click: function() {
	                        closeWins();
	                    }
	                }]
	            });

	            task.on('contextmenu',
	            function(e) {
	                if (!task.menu) return;
	                task.menu.show({
	                    top: e.pageY - task.menu.menu.height(),
	                    left: e.pageX
	                });
	                $(task.menu.menu).focus();
	                return false;
	            });
	        }
	    }

	    return win;
	}
	
	function onResize() {
	    var linksHeight = $(window).height() - $('.l-taskbar').height();
	    var linksWidth = $(window).width();
	    
	    var noticeHeight = (parseInt((linksHeight - 40) / 2) - 42);
	    $('.notice-list').each(function(){
	    	var that = $(this);
	    	if(that.parent().hasClass('slimScrollDiv')){
	    		that.slimScroll({
	    			destroy: true
	    		});
	    	}
	    	
	    	that.slimScroll({
				height: noticeHeight + 'px',
				size: '5px'
			});
	    });
	    
	    var winlinks = $("#winlinks");
	    winlinks.height(linksHeight);
	    winlinks.width(linksWidth);
	    
	    var li = winlinks.find('li:first');
	    if(li.length == 0){
	    	return;
	    }
	   	
	    if(desktopIconSize == "big"){
	    	winlinks.addClass("big-icon");
	    } else{
	    	winlinks.removeClass("big-icon");
	    }
	    
	    var linkWidth = li.width() + 20, linkHeight = li.height() + 20;
	    if(winlinks.hasClass('big-icon') && desktopIconAlign == "center"){
	    	linkWidth += 30;
	    	linkHeight += 30;
	    }
	    var marginX = 30;
	    var top = 0;
        var left = 0;
        var right = 0;
	    var colMaxNumber = (desktopIconAlign == "center") ? parseInt((linksWidth - 120) / (linkWidth + marginX)) : parseInt((linksHeight - 20) / linkHeight);
	    var cols = 0;
	    for (var i = 0, j = 0, l = links.length; i < l; i++) {
	        var link = links[i];
	        if (link && link['showDesktop'] !== false) {
	            var jlink = $("li[linkindex=" + i + "]", winlinks);
	            if(desktopIconAlign == "center") {
	            	top = parseInt(j / colMaxNumber) * linkWidth;
	    	        left = (j % colMaxNumber) * linkHeight + (j % colMaxNumber) * marginX;
	    	        right = "auto";
	            } else if(desktopIconAlign == "right") {
	            	top = (j % colMaxNumber) * linkHeight;
		            right = parseInt(j / colMaxNumber) * linkWidth;
		            left = "auto";
	            } else {
	            	top = (j % colMaxNumber) * linkHeight;
		            left = parseInt(j / colMaxNumber) * linkWidth;
		            right = "auto";
	            }
	            
	            //if (isNaN(top) || isNaN(left)) continue;
	            j ++;
	            jlink.css({
	                top: top,
	                left: left,
	                right: right
	            });
	            cols ++;
	        }
	    }
	   
	    var ul = winlinks.find('ul:first');
	    if(desktopIconAlign == "center"){
	    	left = (linksWidth - (cols > colMaxNumber ? colMaxNumber : cols) * (linkWidth + marginX)) / 2;
	    	top = (linksHeight - (parseInt(cols / colMaxNumber) + (cols % colMaxNumber == 0 ? 0 : 1)) * linkWidth) / 2;
	    	right = "auto";
	    }else if(desktopIconAlign == "right"){
	    	left = "auto";
	    	top = 10;
	    	right = 0;
	    } else {
	    	left = 0,
	    	top = 10,
	    	right = "auto";
	    }
	    
	    ul.css({
    		left: left,
    		top: top,
    		right: right
    	});
	}

	function linksInit() {
	    var winlinksul = $("#winlinks ul");
	    for (var i = 0, l = links.length; i < l; i++) {
	        var link = links[i];
	        if (!link || link['showDesktop'] === false) {
	            continue;
	        }

	        var jlink = $("<li></li>");
	        jlink.attr("linkindex", i);
	        jlink.attr('title', (link["desc"] || link["title"]));
	        jlink.append("<img src='" + link["icon"] + "' />");
	        jlink.append("<span>" + link["title"] + "</span>");
	        jlink.append("<div class='bg'></div>");
	        jlink.hover(function() {
	            $(this).addClass("l-over");
	        },
	        function() {
	            $(this).removeClass("l-over");
	        }).click(function() {
	            var linkindex = $(this).attr("linkindex");
	            var link = links[linkindex];
	            if (link) {
	                openLink(link);
	            }
	        });
	        jlink.appendTo(winlinksul);
	    }
	}

	function menusInit() {
	    var linkWidth = 110,
	    linkHeight = 110;
	    var colMaxNumber = 3;
	    var winlinksul = $("#startmenu_app ul");
	    for (var i = 0, j = 0, l = links.length; i < l; i++) {
	        var link = links[i];
	        if (!link || link['showMenubar'] === false) {
	            continue;
	        }

	        var jlink = $("<li></li>");
	        jlink.attr("linkindex", i);
	        jlink.attr('title', (link.desc || link.title));

	        var top = parseInt(j / colMaxNumber) * linkWidth,
	        left = (j % colMaxNumber) * linkHeight;
	        if (isNaN(top) || isNaN(left)) continue;
	        j ++;
	        jlink.css({
	            top: top,
	            left: left
	        });

	        jlink.append("<img src='" + link["icon"] + "' />");
	        jlink.append("<span>" + link["title"] + "</span>");
	        jlink.append("<div class='bg'></div>");
	        jlink.hover(function() {
	            $(this).addClass("l-over");
	        },
	        function() {
	            $(this).removeClass("l-over");
	        }).click(function() {
	            var linkindex = $(this).attr("linkindex");
	            var link = links[linkindex];
	            if (link) {
	                openLink(link);
	            }
	        });
	        jlink.appendTo(winlinksul);
	    }
	}

	function setDesktopIconAlign(flag){
		desktopIconAlign = flag;
		
		onResize();
		
		saveUserConfig('desktop_icon_align', flag);
	}
	
	function setDesktopIconSize(flag){
		desktopIconSize = flag;
		
		onResize();
		
		saveUserConfig('desktop_icon_size', flag);
	}
	
	var wallpaperDlg = null;
	var wallpaperContent = null;
	function setDesktopWallpaper() {
		if(wallpaperDlg){
			wallpaperDlg.show();
		} else{
			wallpaperContent = $('#wallpaper_content');
			
			var a = [];
			var p = imagesPath + "/wallpaper/";
			$.each(bgs, function(i, bg){
				a.push('<div class="col-3"><a class="thumbnail" data="' + bg + '" href="javascript:selectBg(\'' + bg + '\')"><img src="' + p + bg + '" alt="..."></img></a></div>');
			});
			wallpaperContent.html(a.join(''));
			
			$('a.thumbnail[data="' + desktopWallpaper + '"]').addClass("selected");
			
			wallpaperDlg = $.ligerDialog.open({
				target: $('#wallpaper_dlg'),
				title: '选择桌面背景',
				width : 800,
				height : 'auto',
				showMin: false,
				showMax : false,
				showToggle : true,
				isResize : false,
				modal : false
			});
		}
	}
	function selectBg(bg){
		if(desktopWallpaper == bg){
			return;
		}
		
		$('a.thumbnail[data="' + desktopWallpaper + '"]').removeClass("selected");
		desktopWallpaper = bg;
		$('a.thumbnail[data="' + desktopWallpaper + '"]').addClass("selected");
		setBackground(bg);
		
		saveUserConfig("desktop_background_image", bg);
	}
	
	function saveUserConfig(code, value){
		try{
			setRunning(false);
			postJSON(rootPath + "/login/settings!saveConfig.action", {
				"code": code,
				"value": value
			});
			setRunning(true);
		} catch(ex){
			
		}
	}
	
	var contextMenu;
	function contextMenuInit() {
	    contextMenu = $.ligerMenu({
	        items: [
			{
			    text: '桌面布局',
			    id: 'layout',
			    icon: 'layout',
			    children: [
				{
				    text: '大图标',
				    id: 'big',
				    icon: 'check',
				    click: function() {
				    	setDesktopIconSize('big');
				    }
				},
				{
					text: '小图标',
				    id: 'small',
				    icon: 'check',
				    click: function() {
				    	setDesktopIconSize('small');
				    }
				},
				 {
				    line: true
				 },
			     {
		            text: '左对齐',
		            id: 'left',
		            icon: 'check',
		            click: function() {
		            	setDesktopIconAlign('left');
		            }
		        },
		        {
		        	text: '居中对齐',
		            id: 'center',
		            icon: 'check',
		            click: function() {
		            	setDesktopIconAlign('center');
		            }
		        },
		        {
		        	text: '右对齐',
		            id: 'right',
		            icon: 'check',
		            click: function() {
		            	setDesktopIconAlign('right');
		            }
		        }
		        ],
			},
			{
	            text: '桌面背景',
	            id: 'wallpaper',
	            icon: 'photograph',
	            click: function() {
	                setDesktopWallpaper();
	            }
	        },
			{
			    line: true,
			    id: 'l0'
			},
	        {
	            text: '显示桌面',
	            id: 'desktop',
	            icon: 'desktop',
	            click: function() {
	                showDesktop();
	            }
	        },
	        {
	            text: '关闭全部',
	            id: 'closeall',
	            icon: 'cross',
	            click: function() {
	                closeWins();
	            }
	        },
	        {
	            line: true,
	            id: 'l1'
	        },
	        {
	            text: '进入全屏',
	            id: 'fullscreen',
	            icon: 'communication',
	            click: function() {
	                toggleFullscreen();
	            }
	        },
	        {
	            line: true,
	            id: 'l2'
	        },
	        {
	            text: '退出系统',
	            id: 'exit',
	            icon: 'logout',
	            click: function() {
	                doExit();
	            }
	        }]
	    });

	    if (!supportFullscreen()) {
	        contextMenu.hideItem('l1');
	        contextMenu.hideItem('fullscreen');
	    }

	    $(document).on('contextmenu', function(e) {
	        if (!contextMenu) return;
	        
	        var top = e.pageY - $(contextMenu.menu).height();
	        if (top < 0) {
	            top = 0;
	        }

	        contextMenu.setMenuText('fullscreen', isFullscreen() ? '退出全屏': '进入全屏');

	        contextMenu.show({
	            top: top,
	            left: e.pageX
	        });
	        
	        var layoutMenuId = $(">.l-menu-item[menuitemid=\"layout\"]", contextMenu.menu.items).attr('ligeruimenutemid');
	        var layoutMenuItems = $(".l-menu[ligeruiparentmenuitemid=" + layoutMenuId + "] >.l-menu-inner");
	        layoutMenuItems.find(">.l-menu-item >.l-icon-check").removeClass("l-icon-check");
	        if(desktopIconSize == 'big'){
	        	$('>.l-menu-item[menuitemid="big"] >.l-menu-item-icon:first', layoutMenuItems).addClass('l-icon-check');
	        } else {
	        	$('>.l-menu-item[menuitemid="small"] >.l-menu-item-icon:first', layoutMenuItems).addClass('l-icon-check');
	        }
	        if(desktopIconAlign == 'center'){
	        	$('>.l-menu-item[menuitemid="center"] >.l-menu-item-icon:first', layoutMenuItems).addClass('l-icon-check');
	        } else if(desktopIconAlign == 'right'){
	        	$('>.l-menu-item[menuitemid="right"] >.l-menu-item-icon:first', layoutMenuItems).addClass('l-icon-check');
	        } else {
	        	$('>.l-menu-item[menuitemid="left"] >.l-menu-item-icon:first', layoutMenuItems).addClass('l-icon-check');
	        }
	        
	        $(contextMenu.menu).focus();
	        return false;
	    });
	}

	function taskbarInit() {
	    //任务栏按钮
	    $('.taskbar-btn').hover(function() {
	        $(this).addClass("l-over");
	    },
	    function() {
	        $(this).removeClass("l-over");
	    });

	    //显示桌面
	    $('#taskbar_desktop,#taskbar_min').click(function() {
	        showDesktop();
	    });

	    //开始菜单
	    $('.dropdown-toggle').dropdown();

	    //通知控件
	    $("#taskbar_notice").click(function() {
	    	$('> i', this).removeClass('icon-animated-bell');
	    	
	    	$('#notice_popover').toggle('slow',
	        	function() {
		            if ($(this).is(':visible')) {
		                $('body').on('click.notice',
		                function() {
		                    $('#notice_popover').hide();
		                    $('body').off('click.notice');
		                });
		            } else {
		                $('body').off('click.notice');
		            }
	        }).on('click.notice',
		        function(e) {
		            e.stopPropagation();
		        });
	    });

	    //日历控件
	    $('#datepicker_content').datepicker({
	        language: "zh-CN",
	        todayHighlight: true
	    });

	    $('#datepicker_title').click(function() {
	        $('#datepicker_content').datepicker('setUTCDate', new Date());
	    });

	    $("#taskbar_time").click(function() {
	        $('#datepicker_title').html($('#taskbar_time').attr('title'));
	        $('#datepicker_popover').toggle('slow',
	        function() {
	            if ($(this).is(':visible')) {
	                $('body').on('click.datepicker',
	                function() {
	                    $('#datepicker_popover').hide();
	                    $('body').off('click.datepicker');
	                });
	            } else {
	                $('body').off('click.datepicker');
	            }
	        }).on('click.datepicker',
	        function(e) {
	            e.stopPropagation();
	        });
	    });

	    //时间控件
	    showTime();
	    setInterval(showTime, 5 * 1000);
	}

	function showDesktop() {
	    for (var p in $.ligerui.win.tasks) {
	        var win = liger.get(p);
	        if (win) {
	            win.min();
	        }
	    }
	}

	function closeWins(winId) {
	    for (var p in $.ligerui.win.tasks) {
	        var win = liger.get(p);
	        if (win) {
	            if (!winId || winId != win.id) {
	                win.close();
	            }
	        }
	    }
	}

	var dayOfWeek = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
	var taskbarTime = $('#taskbar_time');
	function showTime() {
	    var now = new Date();
	    var h = now.getHours() < 10 ? ("0" + now.getHours()) : now.getHours();
	    var m = now.getMinutes() < 10 ? ("0" + now.getMinutes()) : now.getMinutes();

	    taskbarTime.html(h + ":" + m + "<br />" + now.getFullYear() + "/" + (now.getMonth() + 1) + "/" + now.getDate());

	    taskbarTime.attr("title", now.getFullYear() + "年" + (now.getMonth() + 1) + "月" + now.getDate() + "日" + " " + dayOfWeek[now.getDay()]);
	}

	function supportFullscreen() {
	    var doc = document.documentElement;
	    return ('requestFullscreen' in doc) || ('msRequestFullscreen' in doc) || ('mozRequestFullScreen' in doc && document.mozFullScreenEnabled) || ('webkitRequestFullScreen' in doc);
	}

	function isFullscreen() {
	    if (!document.fullscreenElement && !document.mozFullScreenElement && !document.webkitFullscreenElement && !document.msFullscreenElement) {
	        return false;
	    } else {
	        return true;
	    }
	}

	function toggleFullscreen() {
	    if (!isFullscreen()) {
	        if (document.documentElement.requestFullscreen) {
	            document.documentElement.requestFullscreen();
	        } else if (document.documentElement.msRequestFullscreen) {
	            document.documentElement.msRequestFullscreen();
	        } else if (document.documentElement.mozRequestFullScreen) {
	            document.documentElement.mozRequestFullScreen();
	        } else if (document.documentElement.webkitRequestFullscreen) {
	            document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
	        }
	    } else {
	        if (document.exitFullscreen) {
	            document.exitFullscreen();
	        } else if (document.msExitFullscreen) {
	            document.msExitFullscreen();
	        } else if (document.mozCancelFullScreen) {
	            document.mozCancelFullScreen();
	        } else if (document.webkitExitFullscreen) {
	            document.webkitExitFullscreen();
	        }
	    }
	}

	function doUser() {
		openLink(customLink['user']);
	}

	function handleLogout(type) {
	    if (type == 1) {
	        setTimeout('window.location = "<%=path%>/login/logout.action?action=exit&loginid=<%=loginId%>";', 0);
	    } else {
	        setTimeout('window.location = "<%=reloginUrl %>";', 0);
	    }
	}

	function doRelogin() {
	    blockPage();
	    bootbox.confirm("您确定要注销[<%=systemName%>]吗?",
	    function(result) {
	        unblockPage();
	        if (result) {
	            handleLogout(0);
	        }
	    });
	}

	function doExit() {
	    blockPage();
	    bootbox.confirm("您确定要退出[<%=systemName%>]吗?",
	    function(result) {
	        unblockPage();
	        if (result) {
	            //handleLogout(1);
	        	handleLogout(0);
	        }
	    });
	}
	
	function openNotice(type) {
		if(type == 'bulletin' || type == '公告'){
			openLink(customLink['bulletin']);
		} else if(type == 'message' || type == '消息') {
			openLink(customLink['message']);
		} else if(type == 'task' || type == '任务'){
			openLink(customLink['task']);
		}
		
		$("#taskbar_notice").click();
	}
	
	function prettyDate(date){
		var l = powersi.length(date);
		if(l <= 10)
			return date || '';
		
		var s = powersi.trim(date.replace(/-/g,"/").replace(/[TZ]/g," "));
		if(s.length > 19){
			s = s.substr(0, 19);
		}
		
		var d = new Date(s);
		var second_diff = (((new Date()).getTime() - d.getTime()) / 1000);
		var day_diff = Math.floor(second_diff / 86400);

		if(day_diff >= -1 && day_diff <= 0){
			/*
			return ( 
			second_diff < 60 && "刚才" ||
			second_diff < 120 && "1 分钟前" ||
			second_diff < 3600 && Math.floor( second_diff / 60 ) + " 分钟前" ||
			second_diff < 7200 && "1 小时前" ||
			second_diff < 86400 && Math.floor( second_diff / 3600 ) + " 小时前");
			*/
			return '<span class="red">' + date.substr(11, 5) + '</span>';
		}
		
		if(day_diff == 1){
			return '<span class="orange">昨天</span>';
		}
		if(day_diff == 2){
			return '<span class="orange">前天</span>';
		}
		if(day_diff == 3){
			return '<span class="orange">两天前</span>';
		}
		
		return date.substr(0, 10);
	}
	
	function renderBulletinList(bulletins){
    	if(!powersi.isvalue(bulletins) || !powersi.isarray(bulletins) || bulletins.length == 0){
    		return;
    	}
    	var a = [];
    	for(var idx in bulletins){
    		var bulletin = bulletins[idx];
    		if(!bulletin) continue;
    		
    		$("#bulletin_" + bulletin["bulletin_id"]).remove();
    		
    		a.push("<li id=\"bulletin_" + bulletin["bulletin_id"] + "\">");
    		a.push("<a href=\"javascript:viewBulletin('");
        	a.push(bulletin.bulletin_url);
        	a.push("');\" title=\"");
        	a.push(bulletin.bulletin_title);
        	a.push("\" class=\"clearfix\"");
        	a.push(">");
        	a.push('<div class="col1">');
        	a.push(bulletin.bulletin_title);
        	a.push("</div>");
        	a.push('<div class="col2">');
        	a.push(prettyDate(bulletin.audit_date));
        	a.push("</div>");
        	a.push("</a>");
        	a.push("</li>");
		}
    	//增量更新
    	$('#notice_bulletin').prepend(a.join(""));
    	$('#notice_bulletin_count').html($('#notice_bulletin > li').length);
	}
	
	function viewBulletin(bulletinUrl){
		popupDialog(rootPath + "/message/BulletinManagerAction!bulletinInfo.action?bulletinId=" + bulletinUrl, 600, 600);
	}
	
	function renderTaskList(tasks){
    	if(!powersi.isvalue(tasks) || !powersi.isarray(tasks) || tasks.length == 0){
    		return;
    	}
    	
    	var a = [];
    	for(var idx in tasks){
    		var task = tasks[idx];
    		if(!task) continue;
    		a.push("<li id=\"task_" + task["task_id"] + "\">");
    		a.push("<a href=\"javascript:viewTask('" + task.task_url + "', '" +  task.task_name + "');\"");
        	a.push(" title=\"");
        	a.push(task.task_desc);
        	a.push("\" class=\"clearfix\"");
        	a.push(">");
        	a.push('<div class="col1">');
        	a.push(task.task_name);
        	a.push("</div>");
        	a.push('<div class="col2"><span class="badge">');
        	a.push(task.task_count);
        	a.push("</span></div>");
        	a.push("</a>");
        	a.push("</li>");
		}
    	//完全更新
    	$('#notice_task').html(a.join(""));
    	$('#notice_task_count').html($('#notice_task > li').length);
    	
    	if(a.length > 0){
    		$('#taskbar_notice > i').addClass('icon-animated-bell');
    	}
	}
	
	function viewTask(url, title){
		openLink({
			"url": url,
			"title": title
		});
	}
	
	var messageDlgs = {};
    function initMessage() {
    	try{
			if(messageList && powersi.isarray(messageList)){
				popupMessageList(messageList);
			}

			if(bulletinList && powersi.isarray(bulletinList)){
				renderBulletinList(bulletinList);
			}
			
			if(taskEnable) {
				if(taskInterval < 60){
    	  			taskInterval = 60;
    	  		}
    	  		
    	  		setTimeout("pollingTask()", 0);
			}
			
			if(messageEnable){
    	  		if(messageInterval < 30){
    	  			messageInterval = 30;
    	  		}
    	  		
    	  		setTimeout("pollingMessage()", messageInterval * 1000);
    	  	}
    	} catch(ex){alert(ex.message);}
    }
    
    function openMsgDialog(url, height, width) {
    	var dlg = messageDlgs[url];
    	if(dlg){
    		dlg.close();
    	}
    	
    	popupDialog(rootPath + "/" + url, height, width);
    }
    
    function showMsgPopup(msg){
    	try{
    		if(!msg || !msg.diag_url){
        		return;
        	}
        	
        	var a = [];
    		a.push("<div class='popup-msg'>");
    		a.push("<a href='javascript:void(0);' onclick='openMsgDialog(\"");
    		a.push(msg.diag_url);
    		a.push("\", " + msg.diag_height + "," + msg.diag_width);
    		a.push(")'>");
    		a.push(msg.ms_content);
    		a.push("</a></div>");
    		
    		var dlg = messageDlgs[msg.diag_url];
    		if(dlg){
    			dlg.set('content', a.join(''));
    			dlg.active();
    		} else{
    			dlg = $.ligerDialog.open({
    				title: (powersi.length(msg.ms_title) > 0) ? msg.ms_title : systemname,
    				content: a.join(''),
    				showType: 'slide',
    	            modal: false,
    	            width: 'auto',
    	            height: 'auto',
    	            fixedType: 'se',
    	            type: 'none',
    	            isDrag: true,
    	            isResize: false,
    	            showMax: false,
    	            showToggle: false,
    	            showMin: false,
    	            isHidden: false,
    	            onClosed: function(){
    	            	delete messageDlgs[msg.diag_url];
    	            }
    			});
    			messageDlgs[msg.diag_url] = dlg;
    		}
    	} catch(ex){
    		alert(ex.message);
    	}
    }
    
    function popupMessageList(messages){
    	if(!powersi.isvalue(messages) || !powersi.isarray(messages) || messages.length == 0){
    		return;
    	}
    	
    	var a = [];
    	var pos, bid;
    	$.each(messages, function(i, message){
    		showMsgPopup(message);
    		
    		if(message.ms_type == '21' && powersi.length(message['diag_url']) > 0){
    			pos = message['diag_url'].indexOf('bulletinId=');
    			
    			if(pos >= 0){
    				bid = message['diag_url'].substring(pos + 11);
    				if(bid.length > 0){
    					a.push({
            				'bulletin_title': message['ms_content'],
            				'bulletin_id': bid,
            				'bulletin_url': bid,
            				'sender_man': message['sender_man'],
            				'audit_date': message['send_date']
            			});	
    				}
    			}
    		}
    	});
    	
    	if(a.length > 0){
    		renderBulletinList(a);
    	}
    }
     
	function pollingMessage() {
    	try{
    	    return jQuery.ajax({
    	        url: rootPath + "/message/MessageAction!pollingMessage.action",
    	        data: null,
    	        dataType: "json",
    	        processData: true,
    	        type: "POST",
    	        global: false,
    	        error: function(jqXHR, textStatus, errorThrown) {
    	        	
    	        },
    	        success: function(json, textStatus, jqXHR) {
    	        	if(checkResult(json, false)){
    	        		popupMessageList(json.data);
    	    	    }
    	        }, 
    	        complete: function(jqXHR, textStatus) {
    	        	setTimeout("pollingMessage()", messageInterval * 1000);
    	        }
    	  });
    	} catch(ajaxException){
    		//alert(ajaxExcption.message);
    	}
    }
	
	function pollingTask() {
    	try{
    	    return jQuery.ajax({
    	        url: rootPath + "/message/MessageAction!pollingTask.action",
    	        data: null,
    	        dataType: "json",
    	        processData: true,
    	        type: "POST",
    	        global: false,
    	        error: function(jqXHR, textStatus, errorThrown) {
    	        	
    	        },
    	        success: function(json, textStatus, jqXHR) {
    	        	if(checkResult(json, false)){
    	        		renderTaskList(json.data);	
    	        	}
    	        }, 
    	        complete: function(jqXHR, textStatus) {
    	        	var now = new Date();
    	        	var last = (now.getHours() < 10 ? '0' : '') + now.getHours() + ':' + (now.getMinutes() < 10 ? '0' : '') + now.getMinutes() + ':' + (now.getSeconds() < 10 ? '0' : '') + now.getSeconds();
    	        	$('#taskbar_notice').attr('title', '通知中心 ' + last);
    	        	
    	        	setTimeout("pollingTask()", taskInterval * 1000);
    	        } 
    	  });
    	} catch(ajaxException){
    		//alert(ajaxExcption.message);
    	}
    }
</script>
</body>
</powersi:html>