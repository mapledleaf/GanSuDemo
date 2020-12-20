<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@page
	import="java.util.Calendar,java.net.URLEncoder,java.lang.StringBuffer"%>
<%@page
	import="com.powersi.hygeia.framework.UserInfo,com.powersi.hygeia.framework.BusiContext,com.powersi.hygeia.framework.CodetableMapping,com.powersi.hygeia.framework.util.UtilFunc"%>
<%
	String path = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	String basePath = path;
	String welcomeUrl = com.powersi.hygeia.framework.ParameterMapping.getStringByCode("welcome_url", "/pages/welcome.jsp");
	if(!welcomeUrl.startsWith("/") && !welcomeUrl.startsWith("http")){
		welcomeUrl = "/" + welcomeUrl;
	}
	
	String systemName = com.powersi.hygeia.framework.ParameterMapping
			.getSystemName();
	String serverName = request.getServerName();
	int curYear = Calendar.getInstance().get(Calendar.YEAR);
	String userName = (String) request.getAttribute("userName");
	if (userName == null) {
		userName = "";
	}
	String userKind = (String) request.getAttribute("userKind");
	if (userKind == null || userKind.trim().length() == 0) {
		userKind = "0";
	}
	String loginUser = (String) request.getAttribute("loginUser");
	if (loginUser == null) {
		loginUser = "";
	}
	String loginType = (String) request.getAttribute("loginType");
	if (loginType == null || loginType.trim().length() == 0) {
		loginType = "admin";
	}

	String loginId = (String) request.getAttribute("loginId");
	if (loginId == null || loginId.trim().length() == 0) {
		loginId = "";
	}

	StringBuffer sb = new StringBuffer();
	sb.append(basePath);
	sb.append("/login/logout.action?action=relogin");
	sb.append("&logintype=");
	sb.append(loginType);
	if (loginType.length() == 0 || loginType.equals("login")) {
		sb.append("&userkind=");
		sb.append(userKind);
	}
	sb.append("&loginuser=");

	loginUser = URLEncoder.encode(loginUser, "GBK");
	sb.append(loginUser);
	sb.append("&loginid=");
	sb.append(loginId);

	String reloginUrl = sb.toString();

	String questionUrl = "";
	if (userKind.equals("1")) {
		questionUrl = "<a href=\"javascript:void(0);\" onclick=\"doQuestion();\">完善密保</a>&nbsp;&nbsp;|&nbsp;&nbsp;";
	}

	String startMenu = (String) request.getAttribute("startMenu");
	if (startMenu == null || startMenu.trim().length() == 0) {
		startMenu = "";
	}

	UserInfo user = BusiContext.getUserInfo();
	StringBuffer sbUser = new StringBuffer();
	sbUser.append("用户名\t：").append(userName);
	if (user != null && user.isValidUser()) {
		sbUser.append("\n统筹区\t：").append(
				CodetableMapping.getDisplayValue("aaa027_list",
						UtilFunc.getString(user, "center_id"), ""));
		sbUser.append("\n所属部门：").append(
				CodetableMapping.getDisplayValue("sys_dept",
						UtilFunc.getString(user, "dept_id"), ""));
		sbUser.append("\n操作级别：").append(
				CodetableMapping.getDisplayValue("staff_level",
						UtilFunc.getString(user, "staff_level"), ""));
		sbUser.append("\n操作岗位：").append(
				CodetableMapping.getDisplayValue("staff_role",
						UtilFunc.getString(user, "staff_role"), ""));
	}
	String userInfo = sbUser.toString();

	String messageEnable = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("message_flag", "0");
	String messageInterval = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("message_query_interval", "180");

	//统一门户标志
	String unifiedFlag = com.powersi.sys.util.SingleSignOnHelper
			.isEnabled() ? "true" : "false";
%>
<powersi:html>
<head>
<powersi:head title="<%=systemName %>" />
<script src="${strutsPath}/include/ace.js" type="text/javascript"></script>
<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
<link rel="stylesheet" id='ymSkinQq' type="text/css" href="${rootPath}/resource/js/ymPrompt/skin/qq/ymPrompt.css" />
<script type="text/javascript" src="${rootPath}/resource/js/ymPrompt/ymPrompt.js"></script>
<style type="text/css">
html,body{padding: 0;margin: 0;}
body{overflow:hidden;height:100%;max-height:100%;width:100%;background-color:transparent;}
body{scrollbar-face-color:#efefef; scrollbar-3dlight-color:#c1c1c1; scrollbar-darkshadow-color:#c1c1c1;scrollbar-base-color:#c1c1c1;scrollbar-shadow-color: #fff; scrollbar-highlight-color: #fff;  scrollbar-track-color:#fff; scrollbar-arrow-color:#4a6184;}
body, table, form, button {font-family: "Microsoft YaHei" !important;font-size: 13px;}
input[type="text"]{font-size: 13px;}
.ztree *{font-size: 13px;}

.navbar-brand{height: 45px;}
.user-info{max-width:200px;}

#sidebar-shortcuts{height: 32px; line-height: 32px; padding-left:0;padding-right:0;}
.sidebar-shortcuts-large{height:26px; line-height:26px;padding:2px 0;}
#sidebar-shortcuts-large >.btn{line-height: 16px; width:40px;}
#sidebar-shortcuts-mini{line-height: 15px; background: #f9f9f9;}
#sidebar-shortcuts-mini >.btn{padding:7px !important;}

.sidebar,.sidebar:before{width: 180px;}
.main-content{margin-left: 180px;}
.breadcrumbs.fixed,.breadcrumbs.breadcrumbs-fixed{left:180px;}
.menu-min .nav-list>li>a>.menu-text {width: 174px;}
.menu-min .nav-list>li>a.dropdown-toggle>.menu-text {width: 174px;}
.menu-min .nav-list>li>.submenu {width: 175px;}
.menu-min .sidebar-shortcuts-large{width: 184px; height:34px; line-height:34px;}
.navbar-container{padding-right:0;padding-left:0px;}
#system{font-size: 18px;}
#favorite-ul{overflow:auto; height: 400px;}
#favorite-ul .badge{right:9px;top:7px;background-color: #ffb752 !important;}
#menu-ul{overflow:auto;height:400px;}
#menu-ul li{display:inline;overflow:hidden;white-space: nowrap;}
a.menu {font-size:13px; color: #428bca !important;}
.nav-list>li .submenu>li>a{border-top:0;border-bottom:1px dotted #e4e4e4;}
.nav-list>li>.submenu li>.submenu>li>.submenu>li>.submenu>li>a{margin-left:20px;padding-left:54px}

.nav-list>li>.submenu:before{display:none;}
.nav-line {position:absolute;left:18px;border-left:1px dotted #9dbdd6;height:36px;width:1px;}
.nav-list>li>.submenu li>.submenu>li .nav-line{height:33px}
#search-li .nav-line{height:39px;}
.nav-list>li>.submenu>li:before{display:none;}

.nav-list > li > a > [class*='icon-']:first-child{
	font-size: 14px;
	color: #478fca;
}
.icon-home {
	font-size: 16px !important;
	color: #ff892a !important;
}
.nav-list > li > a{
	background-color: #fafafa; 
}
.nav-list > li > a:hover{
	background-color: #fff; 
}
.nav-list > li {
	border-bottom-color: #eee;
}
.dropdown-navbar>li>a .badge {margin-top: 4px;}

#search-div{border:none; background-color:#fbfbfb;font-size:13px;padding-top:7px;padding-bottom:9px;padding-left:37px;height:39px;}
#menu-filter{width: 150px; font-size: 13px; background-color: transparent; z-index: 2000;float:left;}
#search-close{font-size: 18px; margin-top:5px;margin-right:11px;float:right;}
.menu-min #menu-filter{width: 125px;}
.menu-min #search-div{padding-left:17px;}

.l-tab-links{background:#fafafa;height:32px;line-height:32px;border:0;}
.l-tab-links ul{padding:0;margin:0;height:32px;line-height:32px;border-bottom: 1px solid #ddd;overflow:visible;}
.l-tab-links li{padding:0;margin:0 0 -1px 0;height:32px;line-height:32px;overflow:visible;background:transparent;border-right:1px solid #ddd;border-top:0;border-bottom:1px solid #ddd;border-left:0;}
.l-tab-links li.l-selected{background:#ffffff;border-bottom:1px solid #fff;overflow:visible;}
.l-tab-links li a{color:#999;margin-right:24px;line-height:inherit;}
.l-tab-links li a:hover{color:#ff4500;}
.l-tab-links li.l-selected a{color:#333;}
.l-tab-links .icon-home{font-size:16px;}
.l-tab-links [class *= 'icon-']{margin-right:5px;font-size:14px;}
.l-tab-links-item-left,.l-tab-links-item-right,.l-selected .l-tab-links-item-left,.l-selected .l-tab-links-item-right{display:none;}
.l-tab-links-item-close{background:url(${strutsPath}/js/plugins/ligerui/skins/Gray/images/layout/icon-close.gif);}
.l-tab-links-item-close-over{background:url(${strutsPath}/js/plugins/ligerui/skins/Gray/images/layout/icon-close-over.gif);}
.l-tab-links-left, .l-tab-links-right{margin-top: 6px;}
.l-tab-links-left{background:url(${strutsPath}/js/plugins/ligerui/skins/Silvery/images/layout/tabs-tools.gif) 0px 0px;}
.l-tab-links-right{background:url(${strutsPath}/js/plugins/ligerui/skins/Silvery/images/layout/tabs-tools.gif) -51px 0px;}
.l-tab-links-left-over{ background-position:-17px 0px;}
.l-tab-links-right-over{ background-position:-68px 0px;}
.l-tab-links-left-invalid{ background-position:-34px 0px;}
.l-tab-links-right-invalid{ background-position:-85px 0px;}
.l-menu {font-size:12px;background-color: #fefefe;background-clip: padding-box;border: 1px solid #ccc;border: 1px solid rgba(0, 0, 0, .15);-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);box-shadow: 0 6px 12px rgba(0, 0, 0, .175);}

#tab-list{font-size: 13px;}
#tab-list .l-grid-row-cell-inner{height: 28px; line-height: 28px;}
#tab-list .l-grid-row-cell-btn-checkbox{margin-top: 7px;}
#tab-list .l-grid-hd-cell-btn-checkbox{margin-top: 8px;}
#tab-list .l-grid-hd-cell-inner{height: 29px; line-height: 29px;}
#tabs-dialog td div{font-size:13px;}
#tab-filter{width: 120px; }

.ui-sortable, .ui-sortable li{margin: 0;}
#favorite-layout .l-layout-center a:focus,#favorite-layout .l-layout-center a:hover{text-decoration: none;}
#favorite-list li { cursor: default; line-height: 28px; height: 28px; list-style: none; text-align: left; padding-left: 10px;}
#favorite-list .ui-draggable-dragging { padding-left: 10px; }
#favorite-list .ui-state-default { border-bottom: 1px solid #ddd; margin-bottom: 0; position: relative;}
#favorite-list .ui-state-hover { background: rgb(224, 236, 255);}
#favorite-list .ui-text{margin-left: 10px; color: rgb(51, 97, 153); }

#favorite-list .icon-minus { position: absolute; right: 10px; top: 7px;}
#favorite-filter{width: 120px;}
#favorite-remove-all i{margin-right: 0;}
#favorite-dialog .modal-footer span{vertical-align: middle;}

#loading-text{font-size:13px;font-weight:500;vertical-align:middle;}

.ui-dialog .ui-dialog-titlebar {padding: 0.5em;font-size: 13px;font-weight: bold;color: rgb(66, 139, 202);background-color: rgb(239, 243, 248);}
.ui-dialog .ui-dialog-title {font-size: 13px;}
.ui-dialog-titlebar-close {top: 0;margin-bottom: 1.1em;}
.ui-dialog .modal-body {padding: 5px;}
.ui-dialog .modal-footer {margin-top: 0;}
.ui-dialog-title > [class *="icon-"] {padding-right: 0.5em;}
.button > [class *="icon-"] {padding-right: 0.5em;font-size: 13px;}

.modal-dialog{width: 600px;margin: 10px auto;}
/*message*/
.ym-window{font-family: "Microsoft YaHei", "simsun";}
.divMsg{text-align: center;vertical-align:middle;padding:25px;}
.divMsg a{color: blue; font-weight: bold !important; text-decoration:none;}
</style>
<!--[if lt IE 9]>
<link rel="stylesheet" href="${strutsPath}/js/plugins/ace/css/ace-ie.min.css" />
<style type="text/css">
body, table, form, .navtab, .ztree *,.ym-window{font-family: "tahoma", "arial" !important;}
</style>
<![endif]-->
</head>
<body>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="javascript:void(0)" class="navbar-brand" id="system">
					<i class="icon-eye-open"></i>
					<%=systemName%>
				</a>
			</div><!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey hidden">
						<a onclick="doTaskList()" href="javascript:void(0)" title="查看任务">
							<i class="icon-tasks"></i> 
							<span class="badge badge-purple hide" id="badgeTask">0</span>
						</a>
					</li>
					<li class="blue hidden">
						<a onclick="doBulletinList()" href="javascript:void(0)" title="查看通知">
							<i class="icon-bell-alt icon-animated-bell"></i> 
							<span class="badge badge-purple hide" id="badgeBulletin">0</span>
						</a>
					</li>

					<li class="green hidden">
						<a onclick="doMessageList()" href="javascript:void(0)" title="查看消息">
							<i class="icon-envelope icon-animated-vertical"></i>
							<span class="badge badge-success hide" id="badeMessage">0</span>
						</a>
					</li>

					<li class="light-blue">
						<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false">
							<i class="icon-user"></i>
							<span class="user-info"> 
								欢迎,<br /> <%=userName%>
							</span>
							<i class="icon-caret-down"></i>
						</a>

						<ul class="user-menu pull-right dropdown-menu">
							<li>
								<a href="javascript:void(0);" onclick="doUserInfo();"><i class="icon-user"></i>用户信息</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="doPassword();"><i class="icon-key"></i>修改密码</a>
							</li>
							<!--
							<li class="divider"></li>
							<li>
								<a href="javascript:void(0);" onclick="doFavorite();"><i class="icon-star"></i>管理收藏夹</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="doTabList();"><i class="icon-th-large"></i>管理标签页</a>
							</li>
							-->
							<li class="divider"></li>
							<li>
								<a href="javascript:void(0);" onclick="doMenuMap();"><i class="icon-reorder"></i>菜单导航图</a>
							</li>
							<!-- fix css bug -->
							<li class="hidden"></li>
						</ul>
					</li>
					<li class="light-purple">
						<a href="javascript:void(0);" onclick="doLockScreen();" title="锁定屏幕"><i class="icon-lock"></i></a>
					</li>
					<li class="purple">
						<a href="javascript:void(0);" onclick="doRelogin();" title="重新登录"><i class="icon-signout"></i></a>
					</li>
					<li class="red">
						<a href="javascript:void(0);" onclick="doExit();" title="安全退出"><i class="icon-off"></i></a>
					</li>
				</ul><!-- /.ace-nav -->
			</div><!-- /.navbar-header -->
		</div><!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="javascript:void(0)"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button id="btn-sidebar" class="btn btn-success" type="button">
							<i class="icon-chevron-sign-left"></i>
						</button>

						<button id="btn-navbar" class="btn btn-info" type="button">
							<i class="icon-chevron-sign-up"></i>
						</button>
						
						<!-- 
						<button id="btn-search" class="btn btn-purple" type="button">
							<i class="icon-search"></i>
						</button>
 						 -->
						<button id="btn-refresh" class="btn btn-danger" type="button">
							<i class="icon-refresh"></i>
						</button>
						
						<!--
						<button id="btn-star" class="btn btn-success" type="button">
							<i class="icon-star"></i>
						</button>
						 -->
						 
						<button id="btn-tabs" class="btn btn-warning" type="button">
							<i class="icon-th"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>
						<span class="btn btn-warning"></span>
						<span class="btn btn-info"></span>
						<span class="btn btn-danger"></span>
					</div>
				</div>
				
				<!-- #sidebar-shortcuts -->
				<ul class="nav nav-list">
					<li id="m0">
						<a href="javascript:void(0);" onclick="clickMenu(0)" class="orange"> 
							<i class="icon-home"></i> 
							<span class="menu-text">
								欢迎页
							</span>
						</a>
					</li>

					<li id="favorite-li" class="hidden">
						<a href="javascript:void(0)" class="dropdown-toggle"> 
							<i class="icon-star-empty"></i> 
								<span class="menu-text"> 
									收藏夹
									<span id="favorite-count" class="badge badge-info" title="收藏夹个数">0</span>
								</span> 
							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu" id="favorite-ul">
							
						</ul>
					</li>

					<li id="menu-li" class="hidden">
						 <a href="javascript:void(0)" class="dropdown-toggle">
							<i class="icon-list"></i>
						 	<span class="menu-text">
						 		功能菜单
								<span class="badge badge-info" id="menu-count" title="功能菜单个数">0</span>
						 	</span>
						 	<b class="arrow icon-angle-down"></b>
						 </a>
						 <ul class="submenu" id="menu-ul">
						 </ul>
					 </li>
					 
					 <li id="sidebar-lock" style="display:none;">
					 	<a href="javascript:void(0);" onclick="doLockScreen()" class="menu"> 
							<i class="icon-lock"></i> 
							<span class="menu-text">
								锁定屏幕 
							</span>
						</a>
					 </li>
					 <li id="sidebar-relogin" style="display:none;">
					 	<a href="javascript:void(0);" onclick="doRelogin()" class="menu"> 
							<i class="icon-signout"></i> 
							<span class="menu-text">
								重新登录
							</span>
						</a>
					 </li>
					 <li id="sidebar-exit" style="display:none;">
					 	<a href="javascript:void(0);" onclick="doExit()" class="menu"> 
							<i class="icon-off"></i> 
							<span class="menu-text">
								安全退出
							</span>
						</a>
					 </li>
				</ul><!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-toggle">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>
			</div>

			<div class="main-content">
				<div id="nav-tabs">
					<div tabid="0" title="欢迎页" style="height:300px">
	          			<iframe frameborder="0" name="0" id="home"></iframe>
	       			</div>
				</div>
			</div><!-- /.main-content -->
		</div><!-- /.main-container-inner -->
	</div><!-- /.main-container -->
	<div id="hide-container" class="hidden">
		<div id="tabs-dialog">
			<div>
				<div id="tab-list"></div>
			</div><!-- /.tabs-body -->
			<div class="space-y"></div>
			<div>
				<div class="row">
					<div class="col-xs-4 text-left">
						 <input id="tab-filter" type="text" class="search" placeholder="搜索标签页" title="输入关键字,按回车键显示符合条件的数据" />
					</div>
					<div class="col-xs-8 text-right">
						<button type="button" class="btn btn-xs btn-primary" onclick="clickTabList('select')" title="选择选定的标签页">
							<i class="icon-hand-right"></i>
							选 择
						</button>
						<button type="button" class="btn btn-xs btn-primary" onclick="clickTabList('reload')" title="刷新勾选的标签页">
							<i class="icon-refresh"></i>
							刷 新
						</button>
						<button type="button" class="btn btn-xs btn-primary" onclick="clickTabList('close')" title="删除勾选的标签页">
							<i class="icon-trash"></i>
							删 除
						</button>
						<button type="button" class="btn btn-xs btn-success" onclick="closeTabList()" title="关闭">
							<i class="icon-remove"></i>
							关 闭
						</button>
					</div>
				</div>
			</div><!-- /.tabs-footer -->
		</div><!-- /.tabs-dialog -->
		
		<div id="favorite-dialog">
			<div>
				<div id="favorite-layout">
					<div position="left" title="功能菜单树">
						<div class="overflow-auto">
							<ul id="favorite-tree" class="ztree"></ul>
						</div>
					</div>
					<div position="center" title='已选择 <span id="favorite-count-dlg">0</span> 项<div class="l-layout-header-toolbar"><a href="javascript:void(0);" id="favorite-remove-all" onclick="clearFavoriteSel()" title="取消全部选择"><i class="icon-trash red"></i></a></div>'>
						<ul class="ui-sortable" id="favorite-list"></ul>
					</div>
				</div>
			</div><!-- /.favorite-body -->
			<div class="space-y"></div>
			<div>
				<div class="row">
					<div class="col-xs-8 text-left">
						 <input id="favorite-filter" type="text" class="search" placeholder="搜索菜单" title="输入关键字,按回车键显示符合条件的数据" />	
						 <span class="text-info"><i class="icon-info-sign bigger-130"></i> 按住已选择项拖动，可以调整顺序</span>
					</div>
					<div class="col-xs-4 text-right">
						<button type="button" class="btn btn-xs btn-primary" onclick="saveFavorite()" title="保存收藏夹">
							<i class="icon-ok"></i>
							确 定
						</button>
						<button type="button" class="btn btn-xs btn-success" onclick="closeFavorite()" title="关闭收藏夹">
							<i class="icon-remove"></i>
							取 消
						</button>
					</div>
				</div>
			</div><!-- /.favorite-footer -->
		</div><!-- /.favorite-dialog -->
		
		<div id="loading-dialog">
			<img src="${strutsPath }/css/images/busy.gif" alt="" />
			<span id="loading-text">
				&nbsp;系统正在处理&nbsp;&nbsp;请稍候...
			</span>
		</div>
		
		<div id="lock-dialog"> 
    		<iframe id="frame-lock" name="frame-lock" frameborder="0" width="100%" height="100%" style="display:block;position:absolute;top:0;left:0;z-index:10000;"></iframe> 
		</div>
	</div><!-- /.hide-container -->
	<script type="text/javascript">
		try{
			var systemname = "<%=systemName%>";
			var basepath = "<%=basePath %>";
			var reloginurl = "<%=reloginUrl %>";
			var welcomeurl = "<%=welcomeUrl %>";
			var loginid = "<%=loginId %>";
			var menuRender = '${menuRender}';
			var bookmarkEnabled = '${bookmarkEnabled}';
			var bookmarkList = '${bookmarkList}';
			var favoriteList = [];
			if(bookmarkEnabled !== 'true'){
				bookmarkList = $.cookie('bookmark') || '';
			}
			
			if(powersi.length(bookmarkList) > 0){
				favoriteList = powersi.tojson('[' + bookmarkList + ']');
			}
		} catch(ex){
			alert(ex.message);
		}
	</script>
	<script type="text/javascript">
	var curmenuid = "0";
	var loginpage = false;
	var exitflag = false;
	var tabs = null;
	var frmheight = 0;
	var lockscreen = false;
	$(function () {
	    try {
	        $(window).resize(setSize);
	        setSize();

	        initLockScreen();
	        //initSettings();
	        
	        renderShortcuts();
	        renderMenu();
	        //renderFavorite();
	        renderTabs();
	        
	      	//消息处理
	        //setTimeout('initMessage()', 0);
	    } catch (e) {
	        alert(e.message);
	    }
	});

	function setSize() {
		var height = $(window).height();
		var width = $(window).width();
		var navheight = $('#navbar').height();
		var ctrlheight = 0;
		if($('#navbar').is(':hidden')){
			navheight = 0;
			ctrlheight = ($('#sidebar-exit').height() + 1) * 3;
		}
		
	    if(tabs){
	    	tabs.setHeight(height - navheight);
	    }
	    
	    $('#lock-dialog').width(width).height(height);
	    
	    var menuheight = height - navheight - ctrlheight - 180;
	    $("#menu-ul").height(menuheight);
	    $('#favorite-ul').height(menuheight);
	}
	
	function setConfig(key, value, expires){
		$.cookie(key, value, {
			'expires' :  expires,
			'path': rootPath + '/login/'
		});
	}
	
	function getConfig(key){
		return $.cookie(key);
	}
	
	function initSettings(){
		try{
			if(getConfig('sidebar-collapsed') === 'true'){
				setSidebar(true);
			}
			
			if(getConfig('navbar-collapsed') === 'true'){
				setNavbar(true);
			}
		} catch(e){
			alert(e.message);
		}
	}

	var grid = null;
	var gridData = null;
	var gridSearch = null;
	function renderTabs() {
	    try {
	    	//初始化导航标签页
	        $('#nav-tabs').ligerTab({
	        	height: $(window).height() - $('#navbar').height(),
	        	contextmenu: false
	        });
	        
	    	//获取tab对象
	        tabs = $("#nav-tabs").ligerGetTabManager();
	        
	        //初始化右键菜单
	        tabs.tab.menu = $.ligerMenu({ width: 150, 
	        	items: [
	        	       	{ 
	        	       		text: '刷新', id: 'reload', click: function () {
	        	       			tabs._menuItemClick.apply(tabs, arguments);
	        	       		},
	        	       		icon: 'refresh'
	                    },
	        	       	{ 
	                    	text: '取消', id: 'cancel'
                    	},
                    	{
	        				line: true
                    	},
	        			/*
                    	{
	        				text: '添加收藏夹', id:'add', click: function(){
	        					addFavorite(tabs.actionTabid);
	        				},
	        				icon: 'star-plus'
	        			},
	        			{
	        				text: '管理收藏夹', id:'edit', click: function(){
	        					doFavorite();
	        				}
	        			},
	        			{
	        	       		line: true
	        	       	},
	        	       	*/
	        			{ 
	        	        	text: '关闭标签页', 
	        	        	id: 'close', 
	        	        	click: function () {
	        	        		tabs._menuItemClick.apply(tabs, arguments);
	        	        	},
	        	        	icon: 'cross'
	        	       	},
	        	       	{ 
	        	       		text: '关闭其他标签页', id: 'closeother', click: function () {
	        	       			tabs._menuItemClick.apply(tabs, arguments);
	        	       		}
	        	       	},
	        	       	{ 
	        	       		text: '关闭全部标签页', id: 'closeall', click: function () {
	        	       			tabs._menuItemClick.apply(tabs, arguments);
	        	       		}
	        	       	}
	        	 	]
	        });
	        
	        //初始化标签列表
	       	grid = $("#tab-list").ligerGrid({
                columns: [
					{ display: '序号', name: 'tabidx', type: 'int', width: 50},
                    { display: '标签名称', name: 'name', width: '50%'},
	                { display: '标签描述', name: 'desc', width: '50%'}
	                ], 
                height: 360,
                width: '100%',
                rowHeight: 28,
                headerRowHeight: 29,
                pageSizeOptions: [5, 10, 20],
                pageStatMessage: '显示 {from} - {to}，共 {total} 条',
                checkbox: true,
                rownumbers: true,
                showReload: false
            });
	       
	        //初始化搜索标签对话框
	        $("#tab-filter").keydown(function (e) {
	            if (e.keyCode != 13) {
	                return;
	            }

	            try{
	            	$(this).addClass('loader');
	            	
	 	            gridFilter = $.trim($(this).val()) || '';
	 	            if(gridFilter == ''){
	 	            	grid.loadData(gridData);
	 	            } else {
	 	            	grid.options.data = $.extend(true, {}, gridData);
	 		            grid.loadData(doGridFilter());
	 	            }
	 	            $(this).removeClass('loader').focus();
	            } catch(e){
	            	alert(e.message);
	            }

	            e.returnValue = false;
	            return false;
	        });

	        /*
	      	if(favoriteList.length > 0){
	      		//展开收藏夹
	      		$("#favorite-li").addClass('open');
		      	$("#favorite-ul").show();
	      	} else{
	      		//展开功能菜单
	      		$("#menu-li").addClass('open');
		      	$("#menu-ul").show();
	      	}*/
	      	
	      	//展开功能菜单
      		/*
	      	$("#menu-li").addClass('open');
	      	$("#menu-ul").show();
	        */
	        
	      	//初始化欢迎页
	        initTab(0);
	        $('#home').attr('src', basepath + welcomeurl);
	    } catch (e) {
	        alert(e.message);
	    }
	}

	function setSidebar(collapsed){
		var c = collapsed || false;
	    var e = document.getElementById("sidebar");
	    //var d = document.getElementById("sidebar-collapse").querySelector('[class*="icon-"]');
	    var d = document.getElementById("sidebar-toggle").querySelector('[class*="icon-"]');
	    var b = d.getAttribute("data-icon1");
	    var a = d.getAttribute("data-icon2");
	    if (c) {
	        ace.addClass(e, "menu-min");
	        ace.removeClass(d, b);
	        ace.addClass(d, a);
	        
	        setConfig('sidebar-collapsed', 'true', 365);
	        
	        $('#btn-sidebar i').attr('class', 'icon-chevron-sign-right');
	    } else {
	        ace.removeClass(e, "menu-min");
	        ace.removeClass(d, a);
	        ace.addClass(d, b);
	        
	        setConfig('sidebar-collapsed', 'false', -1);
	        
	        $('#btn-sidebar i').attr('class', 'icon-chevron-sign-left');
	    }
	    
	    setTimeout("setSize()", 0);
	}
	
	var ctrls = ["#sidebar-lock", "#sidebar-relogin", "#sidebar-exit"];
	function setNavbar(collapsed){
		if(collapsed){
			$('#navbar').hide("slow", setSize);
    		$('#btn-navbar i').attr('class', 'icon-chevron-sign-down');
    		$.each(ctrls, function(i, ctrl){
    			$(ctrl).show();
    		});
    		setConfig('navbar-collapsed', 'true', 365);
    	} else{
    		$('#navbar').show("slow", setSize);
    		$('#btn-navbar i').attr('class', 'icon-chevron-sign-up');
    		$.each(ctrls, function(i, ctrl){
    			$(ctrl).hide();
    		});
    		setConfig('navbar-collapsed', 'false', -1);
    	}
	}
	
	function renderShortcuts() {
	    try {
	    	$('#sidebar-toggle').attr('title', '显示或者隐藏侧边栏').on('click', function () {
	        	var min = $('#sidebar').hasClass('menu-min');
	        	setSidebar(!min);
	        });
	        $('#btn-sidebar').attr('title', '显示或者隐藏侧边栏').on('click', function () {
	        	var min = $('#sidebar').hasClass('menu-min');
	        	setSidebar(!min);
	        });
	        $('#btn-navbar').attr('title', '显示或者隐藏导航栏').on('click', function () {
	        	setNavbar($('#navbar').is(':visible'));
	        });
	        $('#btn-search').attr('title', '显示或者隐藏搜索菜单栏').on('click', function () {
	        	$('#search-li').toggleClass('hidden');
	        });

	        $('#btn-refresh').attr('title', '重新加载').on('click', function () {
	            reloadMenu();
	        });
	        
	        $('#btn-star').attr('title', '打开收藏夹管理').on('click', function() {
	        	doFavorite();
	        });
	        
	        $('#btn-tabs').attr('title', '打开标签页管理').on('click', function () {
	        	doTabList();
	        });
	    } catch (e) {
	    }
	}

	var menuMap = {};
	var urlMap = {};
	var imgMap = {};
	var menuList = null;
	var menuCount = 0;
	var urlCount = 0;
	var menuTree = [];
	function renderMenu() {
	    try {
	        if (menuRender == null || menuRender.length == 0) {
	            return;
	        }
	        menuList = powersi.tojson(menuRender);
	        menuRender.length = 0;
	        menuRender = null;

	        menuCount = (menuList == null) ? 0 : menuList.length;
	        if (menuCount < 1) {
	            return;
	        }

	        var rootMenu = 0;
	        var firstFolder = null;
	        for (var i = 0; i < menuCount; i++) {
	            var menu = menuList[i];
	            menuMap[menu.id] = menu;
	            if (menu.data && powersi.length(menu.data) > 0) {
	                urlMap[menu.data] = menu;
	                urlCount ++;
	            }
	            
	            if (menu.img && powersi.length(menu.img) > 0) {
	                imgMap[menu.id] = menu.img;
	            }
	            
	            if(menu.pId == 0){
	            	rootMenu = menu.id;
	            }
	        }
	        $('#menu-count').html(urlCount);

	        urlMap['WELCOME'] = {
	            id: 0,
	            pId: null,
	            name: '欢迎页',
	            data: welcomeurl
	        };

	        for (var i = 0; i < menuCount; i++) {
	            var menu = menuList[i];
	            if(menu.id == rootMenu || menu.pId != rootMenu){
	            	continue;
	            }
	            
	            menuMap[menu.id] = menu;
	            if (menu.data && powersi.length(menu.data) > 0) {
	            	menuTree.push('<li id="m' + menu.id + '">');
	            	menuTree.push('<a href="javascript:void(0);" class="menu" onclick="clickMenu(' + menu.id + ')">');
	            	if(menu.img && menu.img.length > 0){
	            		menuTree.push(menu.img);
	            	} else {
	            		menuTree.push('<i class="icon-file-alt"></i>');
	            	}
	            	menuTree.push('\n<span class="menu-text">\n');
	            	menuTree.push(menu.name);
	            	menuTree.push("\n</span>");
	            	menuTree.push("</a>");
	            	menuTree.push("</li>");
	            } else {
	            	if(firstFolder == null){
	            		firstFolder = "m" + menu.id;
	            	}
	            	menuTree.push('<li id="m' + menu.id + '">');
	            	menuTree.push('<a href="javascript:void(0);" class="dropdown-toggle">');
	            	
	            	menuTree.push('<i class="icon-folder-close-alt green"></i>');
	            	
	            	menuTree.push('\n<span class="menu-text green">\n');
	            	menuTree.push(menu.name);
	            	menuTree.push("\n</span>");
	            	menuTree.push("</a>");
	            	
	            	menuTree.push('<ul class="submenu">');
	            	renderMenuTree(menu);
	            	menuTree.push('</ul>');
	            	menuTree.push('</li>');
	            }
	        }
	        
	        $('#sidebar-lock').before(menuTree.join(""));
	        
	        //自动打开一个文件夹
	        if(firstFolder != null){
	        	$('#' + firstFolder).addClass('open').find(' > ul.submenu').show();
	        }
	        
	        //$('.nav-list > li > a > [class*="icon-"]:first-child').addClass('icon-fixed-width');
	        /*
	        menuTree.push('<li id="search-li" class="hidden">');
	       	menuTree.push('<span class="nav-line"></span>');
	        menuTree.push('	<div id="search-div">');
	        menuTree.push('		<input id="menu-filter" type="text" class="search" placeholder="搜索菜单" title="输入关键字,按回车键显示符合条件的数据"></input>');
	        menuTree.push('		<button id="search-close" title="关闭搜索菜单栏" type="button" class="close">&times;</button>');
	        menuTree.push('	</div><div style="clear:both;"></div>');
	        menuTree.push('</li>');
	        
	        renderMenuTree({
	            'id': 0
	        });

	        $('#menu-ul').html(menuTree.join(''));

	        menuTree.length = 0;
	        menuTree = null;

	        $("#search-close").bind('click', function(){
	        	$("ul#menu-ul li.hidden").removeClass('hidden');
	        	$('#search-li').addClass('hidden');
	        	$("#menu-count").text(urlCount);
	        });
	        
	        $("#menu-filter").keydown(function (e) {
	            if (e.keyCode != 13) {
	                return;
	            }

	            try{
	            	var filter = $.trim($(this).val()), count = 0;
	 	            $(this).addClass('loader').prop('disabled', true);
	 	            
	 	            var cnt = 0;
	 	            if (filter == '') {
	 	                $("ul#menu-ul li.hidden").removeClass('hidden');
	 	                count = urlCount;
	 	            } else {
	 	                $("ul#menu-ul li:gt(0)").each(function () {
	 	                    if ($(this).html().search(new RegExp(filter, "i")) < 0) {
	 	                        $(this).addClass("hidden");
	 	                    } else {
	 	                        $(this).removeClass("hidden");
	 	                        cnt++;
	 	                        if($(this).attr('role') == 'menu'){
	 	                        	count ++;
	 	                        }
	 	                    }
	 	                });
	 	                
	 	                if (cnt == 0 && filter.length > 0) {
	 		                bootbox.alert('找不到 ' + filter + ' 的菜单。');
	 		            }
	 	            }

	 	            $("#menu-count").text(count);

	 	            $(this).removeClass('loader').prop('disabled', false).focus();
	            } catch(e){
	            	alert(e.message);
	            }
	           
	            e.returnValue = false;
	            return false;
	        });*/
	    } catch (e) {
	    	alert(e.message);
	    }
	}

	function renderMenuTree(pMenu) {
	    for (var i = 0; i < menuCount; i++) {
	        var menu = menuList[i];
	        if (menu.pId != pMenu.id) {
	            continue;
	        }

	        if (!menu.data || menu.data.length == 0) {
	            menuTree.push('<li>');
	            menuTree.push('<span class="nav-line"></span>');
	            menuTree.push('<a href="javascript:void(0)" class="dropdown-toggle" title="' + menu.name + '">');
	            menuTree.push('<i class="icon-double-angle-right"></i>');
	            menuTree.push(menu.name);
	            // menuTree.push('<b class="arrow icon-angle-down"></b>');
	            menuTree.push('</a>');

	            menuTree.push('<ul class="submenu">');
	            renderMenuTree(menu);
	            menuTree.push('</ul>');

	            menuTree.push('</li>');
	        } else {
	            menuTree.push('<li role="menu"');
	            menuTree.push(' id="m' + menu.id + '">');
	            menuTree.push('<span class="nav-line"></span>');
	            menuTree.push('<a href="javascript:void(0)" class="menu" onclick="javascript:clickMenu(' + menu.id + ')" title="' + menu.name + '">');
	            menuTree.push('<b class="icon-circle-arrow-right"></b>');
	            
	            var img = imgMap[menu.id];
	            if(img != null && img.length > 0){
	            	menuTree.push(img);	
	            	menuTree.push("&nbsp;");
	            }
	            
	            menuTree.push(menu.name);
	            menuTree.push('</a>');
	            menuTree.push('</li>');
	        }
	    }
	}
	
	function addTab(tabid, text, url) {
   		var currentTabid = tabs.getSelectedTabItemID();
   		if (currentTabid == tabid){
           	//tabs.reload(currentTabid);
           	return;
        }
   		
   		if(tabs.isTabItemExist(tabid)){
   			selectTab(tabid);
   			return;
   		} else{
   			//showLoading(true);
   		}
   		
   		tabs.addTabItem({tabid : tabid, 
   			text: text, 
   			url: url
   		});
   		
   		initTab(tabid);
    }

	function initTab(tabid) {
		try{
			if(tabid == 0){
				loadFrame();
	    		$('#home').bind("load", loadFrame);
	    		
	    		$("#nav-tabs ul li[tabid='0']").attr('title', systemname).find('a').prepend('<i class="icon-home orange"></i>');
			} else {
				var f = $("iframe[name='" + tabid + "']").first();
		    	if(f != null){
		    		loadFrame();
		    		f.bind("load", loadFrame);
		    	}
		    	
		    	var title = "";
	    		var menu = menuMap[tabid];
	    		if(menu){
		    		title = getMenuDesc(menu);
	    		}
		    	
	    		var img = imgMap[tabid];
	    		if(img == null || img.length == 0){
	    			img = '<i class="icon-file-alt"></i>';
	    		}
		    	$("#nav-tabs ul li[tabid='" + tabid + "']").attr('title', title).find('a').prepend(img);
			}
		} catch(e){
			alert(e.message);
		}
    }
	
	function selectTab(tabid) {
		try{
			tabs.selectTabItem(tabid);
			
			//保证选择的tab可见
			var leftPos = $("li[tabid=0]", tabs.tab.links.ul).position().left || 0;
	        var btnWidth = $(".l-tab-links-left", tabs.tab.links).width() || 0;
	        var tabitem = $("li[tabid=" + tabid + "]", tabs.tab.links.ul);
	        tabs.tab.links.ul.animate({ left: -1 * (parseInt(tabitem.position().left) - leftPos - btnWidth)});
		} catch(e){
			alert(e.message);
		}
	}
	
	function loadFrame() {
	    showLoading(false, 400);

	    if (loginpage) {
	        showLoading(false);
	        loginpage = false;
	        alert("登录已经超时，请重新登录[" + systemname + "]。");
	        setTimeout("doRelogin()", 0);
	    }
	}

	function clickMenu(menuId){
		var menu = (menuId == 0) ? urlMap["WELCOME"] : menuMap[menuId];
		if(!menu){
			return;
		}
		
		doMenu(menu.id, menu.name, menu.data);
	}
	
	function doMenu(menuId, menuName, menuUrl) {
    	var url = null;
    	if(menuUrl != null && menuUrl.charAt(0) != '/' && menuUrl.length >= 7){
    		var s = menuUrl.substring(0, 7).toLowerCase();
    		if(s == "http://" || s == "https:/" || s == "file://" || s.substring(0, 6) == "ftp://"){
    			url = menuUrl;
    		}
    	}
    	
    	if(url == null){
    		url= basepath + menuUrl;
    	}
    	
        addTab(menuId, menuName, url);
    }
	
	function reloadMenu(menuId) {
		if(!powersi.isvalid(menuId)){
			tabs.reload(tabs.getSelectedTabItemID());
		} else {
			if(tabs.isTabItemExist(menuId)){
				tabs.reload(menuId);
			}	
		}
	}

	function closeMenu(menuId){
		if(menuId == 0){
			return false;
		}
		
		if(tabs.isTabItemExist(menuId)){
			tabs.removeTabItem(menuId);
			return true;
		}
		
		return flase;
	}
	
	function getMenuDesc(menu){
		if(!menu.desc){
			var a = [];
	        var pMenu = menu;
	        while (pMenu) {
	            a.push(pMenu.name);
	            pMenu = menuMap[pMenu.pId];
	        }
	        menu.desc = a.reverse().join(' - ');
	        a.length = 0;
		}
		
		return menu.desc;
	}
	
	function __openMenu__(url, param, options) {
	    try {
	        var menu = urlMap[url];
	        if (!menu) {
	            return;
	        }

	        var isReload = (options && (options.reload == true)) ? true : false;
        	var isExist = tabs.isTabItemExist(menu.id);
        	if(isExist){
        		if(!isReload){
        			tabs.selectTabItem(menu.id);
        			return;
        		}
        		
        		tabs.removeTabItem(menu.id);
        	}
        	
        	var menuUrl = url;
        	if(param && param != ''){
        		if(url.indexOf("?") > 0){
        			menuUrl += "&";
        		} else {
        			menuUrl += "?";
        		}
        		menuUrl += param;
        	}
        	doMenu(menu.id, menu.name, menuUrl);
	    } catch (e) {
	        alert(e.message);
	    }
	}

	function __closeMenu__(url) {
	    try {
	        if (powersi.length(url) == 0) {
	        	tabs.removeSelectedTabItem();
	        } else {
	            var menu = urlMap[url];
	            if (!menu) {
	                return;
	            }
	            tabs.removeTabItem(menu.id);
	        }
	    } catch (e) {
	        alert(e.message);
	    }
	}

	function __openWindow__(url, param, options) {
		try{
        	var winTitle = "";
        	if(options && options.title){
        		winTitle = options.title || "";
        	}
        	if(winTitle.length == 0){
        		alert('打开窗口标题不能为空。');
        		return;
        	}
        	
        	var winId = 0 - (new Date()).getTime();
        	
        	var menuUrl = url;
        	if(param && param != ''){
        		if(url.indexOf("?") > 0){
        			menuUrl += "&";
        		} else {
        			menuUrl += "?";
        		}
        		menuUrl += param;
        	}
        	doMenu(winId, winTitle, menuUrl);
    	} catch(e){
    		alert(e.message);
    	}
	}

	function __closeWindow__(url) {
		
	}
	
	function __refreshMenu__(url, param) {
	    try {
	    	var menu = urlMap[url];
	        if (!menu) {
	            return;
	        }

        	var isExist = tabs.isTabItemExist(menu.id);
        	if(!isExist){
        		return;
        	}
        	
        	var src = null;
        	var menuUrl = menu.data;
        	if(menuUrl != null && menuUrl.charAt(0) != '/' && menuUrl.length >= 7){
        		var s = menuUrl.substring(0, 7).toLowerCase();
        		if(s == "http://" || s == "https:/" || s == "file://" || s.substring(0, 6) == "ftp://"){
        			src = menuUrl;
        		}
        	}
        	
        	if(src == null){
        		src = basepath + menuUrl;
        	}
        	
        	if(param && param != ''){
        		if(src.indexOf("?") > 0){
        			src += "&";
        		} else {
        			src += "?";
        		}
        		src += param;
        	}
        	
        	var frm = $('iframe[name="' + menu.id + '"]');
        	if(frm){
        		frm.attr('src',  src);
        	}
	    } catch (e) {
	        alert(e.message);
	    }
	}
	
	function doMenuMap() {
		doMenu('map', '菜单导航图', '/pages/map.jsp');
	}
	
	var dlgTab = null;
	function doTabList() {
		//初始化grid数据
	   	 initGridData();
	   	 //设置grid数据
	   	 grid.loadData(gridData);
	   	 //去掉全选勾
	   	 unselectCheckAll(grid);
	   	 
	   	 //显示对话框	        	
	   	 if(dlgTab){
	   		 dlgTab.show();
	   	 } else {
	   		 dlgTab = popupDiv('#tabs-dialog', '标签页管理器', 620);
	   	 }
	}
	
	function closeTabList() {
		if(dlgTab){
			dlgTab.hide();
		}	
	}
	
	function initGridData(){
		try{
			if(gridData != null){
				gridData.Rows.length = 0;
				gridData = null;
			}
			   	 
			gridData = {'Rows': []};
			   	 
		   	 $("li", tabs.tab.links).each(function (i, item) {
		   		 gridData.Rows.push({
		   			 tabidx: i + 1, 
		   			 tabid: $(this).attr('tabid'),
		   			 name: $(this).find('a').text(),
		   			 desc:$(this).attr('title')
		   			});
		   	 });
		} catch(e){
			alert(e.message);
		}
	}
	
	function doGridFilter() {
		if (!grid) return null;
		if(gridFilter == null || gridFilter == ''){
			return true;
		}
		
        var clause = function (rowdata, rowindex) {
            return rowdata.desc.indexOf(gridFilter) > -1;
        };
        return clause; 
	}
	
	function unselectCheckAll(gridObj){
		//去掉全选勾
		$(gridObj.element).find('.l-grid-header-table').find('.l-checked').removeClass('l-checked');
	}
	
	function clickTabList(type){
		if(!grid){
			return;
		}
		
		if(type == 'select'){
			var row = grid.getSelectedRow();
			if(row == null){
				alert("请选定一个标签页");
				return;
			}
			selectTab(row["tabid"]);
		} else {
			var rows = grid.getSelectedRows();
			if(rows == null || rows.length == 0){
				alert('请勾选需要操作的标签页');
				return;
			}
			
			if(type == 'reload'){
				for(var i = 0; i < rows.length; i ++){
					reloadMenu(rows[i]['tabid']);
					grid.unselect(rows[i]);
				}
				//去掉全选勾
				unselectCheckAll(grid);
			} else if(type == 'close'){
				for(var i = 0; i < rows.length; i ++){
					closeMenu(rows[i]['tabid']);
					grid.deleteRow(rows[i]);
				}
				
				//初始化grid数据
				initGridData();
				
				//设置grid数据
				grid.loadData(gridData);
			}
		}
	}
	
	var dlgFavorite;
	function doFavorite(){
		initFavoriteSel();
		
		clearFavoriteSel();
		
		$.each(favoriteList, function(index, value) { 
			addFavoriteSel(value);
		}); 
		
		//显示对话框
		if(dlgFavorite){
			dlgFavorite.show();
		} else{
			dlgFavorite = popupDiv('#favorite-dialog', '收藏夹管理器', 620);
		}
	}
	
	function renderFavorite(){
		try{
			var len = favoriteList.length;
			var pad = len.toString().length;
			var a = [];
			var menu = null;
			for(var i = 0; i < len; i ++){
				menu = menuMap[favoriteList[i]];
				if(menu){
					a.push('<li role="menu">');
					a.push('	<span class="nav-line"></span>');
					a.push('	<a class="menu" href="javascript:void(0)" title="' + getMenuDesc(menu) + '" onclick="javascript:clickMenu(' + menu.id + ')">');
					a.push('		<i class="icon-double-angle-right"></i>');
					a.push('		<b class="icon-circle-arrow-right"></b>');
					a.push('		<span class="badge">' + powersi.lpad((i + 1).toString(), pad, '0') + '</span>');
					a.push(menu.name);
					a.push('	</a>');
					a.push('</li>');
				}
			}
			
			$('#favorite-count').text(len);
			$('#favorite-ul').html(a.join(''));
		} catch(e){
			alert(e.message);
		}
	}
	
	function saveFavorite(){
		var a = [];
		$('#favorite-list li').each(function(){
			a.push($(this).attr('id').substring(1));	
		});
		
		if(powersi.tostring(a) == powersi.tostring(favoriteList)){
			alert('没有修改，无需保存');
			return;
		}
		
		favoriteList = a.slice();
		renderFavorite();
		setTimeout("storeFavorite()", 0);
		
		closeFavorite();
	}
	
	function closeFavorite(){
		if(dlgFavorite){
			dlgFavorite.hide();
		}
	}
	
	function addFavorite(menuId) {
		var len = favoriteList.length;
		for(var i = 0; i < len; i ++){
			if(favoriteList[i] == menuId){
				return;
			}
		}
		
		favoriteList.push(menuId);
		renderFavorite();
		setTimeout("storeFavorite()", 0);
	}
	
	function storeFavorite(){
		var str = favoriteList.join(',');
		if(bookmarkEnabled == 'true'){
			postJSON(rootPath + "/login/settings!saveBookmark.action", {
				"bookmark": str
			});
		} else {
			setConfig('bookmark', str, 365);
		}
	}
	
	var favoriteTree = null;
	var _lastFind = null;
	var _nodeList = [];
	function initFavoriteSel(){
		if(favoriteTree){
			return;
		}
		
	    try{
	    	//初始化收藏夹布局
	        $("#favorite-layout").width(597).ligerLayout({
	        	height: 360,
	        	leftWidth: 300,
	        	allowLeftResize: false
	        });
	        $('#favorite-tree').height(300);
	        
	      	//初始化收藏夹搜索
	        $("#favorite-filter").keydown(function (e) {
	            if (e.keyCode != 13) {
	                return;
	            }

	            try{
	            	var that = $(this);
	            	that.addClass('loader');
	            	
	            	if(favoriteTree){
                		var txt = powersi.trim(that.val());
                		var len = powersi.length(_nodeList);
                		if(_lastFind !== txt) {
                			_lastFind = txt;
                			
                			for( var i = 0; i < len; i++) {
                				_nodeList[i].highlight = false;
                				favoriteTree.updateNode(_nodeList[i]);
                			}
                    		_nodeList.length = 0;
                    		
                    		if(txt.length > 0){
                    			_nodeList = favoriteTree.getNodesByParamFuzzy("name", txt, null);
                    		}
                    		
                    		len = powersi.length(_nodeList);
                    		for( var i = 0; i < len; i ++) {
                    			_nodeList[i].highlight = true;
                    			favoriteTree.updateNode(_nodeList[i]);
   	            			}
                		}
                		
                		if(txt.length > 0){
	                   		if(len > 0){
	                   			var sel = 0;
	                   			if(len > 1){
	                   				var curNodes =  favoriteTree.getSelectedNodes();
	                       			if(curNodes.length > 0){
	                       				var curIdx =  getTreeOrder("favorite-tree", curNodes[0]);
	                       				for(var i = 0; i < len; i ++){
	                       					var idx = getTreeOrder("favorite-tree", _nodeList[i]);
	                       					if(idx > curIdx){
	                       						sel = i;
	                       						break;
	                       					}
	                       				}
	                       			}
	                   			}
	                       		
	                   			var node = _nodeList[sel];
	           					if(node.isParent){
	           						favoriteTree.expandNode(node, true, false, true, false);
	           					} else {
	           						favoriteTree.expandNode(node.getParentNode(), true, false, true, false);
	  	            			}
	           					
	           					favoriteTree.selectNode(node, false);
	                   		} else {
	                   			alert('没有找到"' + txt + '"对应的项。');
	                   		}
                		}
                	}
	            	
	 	            that.removeClass('loader').focus();
	            } catch(e){
	            	alert(e.message);
	            }

	            e.returnValue = false;
	            return false;
	        });
	      
	    	var root = -1;
	    	var trees = [];
	    	trees.push({"id": 0, "pId": root, "name": systemname, "open": true, "font": {"color": "#f00"}, "iconSkin" : "system"});
	        if (powersi.isarray(menuList)) {
	            $.each(menuList, function(idx, menu){
	                var treeNode = menu;
	                if(menu.data && menu.data.length > 0){
	                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
	                	treeNode["ismenu"] = "1";
	                }
	                treeNode["iconSkin"] = "right";
	                
	                trees.push(treeNode);
	            });//end for menus
	        }
	        
	        var upSetting = {
    			view: {
    				fontCss: getTreeFont,
    				autoCancelSelected: false,
    				selectedMulti: false,
    				dblClickExpand: true,
    				showLine: false,
    				expandSpeed: 0
    			},
    			data: {
    				simpleData: {
   					enable: true,
   					rootPId: root
    				}
    			},
				callback: {
					onClick: onFavoriteTreeClick
				}
	    	};
	        $.fn.zTree.init($("#favorite-tree"), upSetting, trees);
	        trees.length = 0;
	        
	        favoriteTree = $.fn.zTree.getZTreeObj("favorite-tree");
	        
	        //允许拖放排序
	        $("#favorite-list").sortable();
	    } catch(e){
	    	alert(e.message);
	    }
	}
	
    function onFavoriteTreeClick(event, treeId, treeNode, clickFlag){
    	if(treeNode.ismenu == "1"){
    		addFavoriteSelByNode(treeNode);
    	} else {
    		//设置focus为true时，滚动条乱跳，应该是弹窗对话框的问题导致的，暂时屏蔽自动聚焦的功能
    		//expandNode(treeNode, expandFlag, sonSign, focus, callbackFlag)
    		favoriteTree.expandNode(treeNode, !treeNode.open, false, false, false);
    	}
    }
    
    function addFavoriteSel(menuId){
    	var treeNode = favoriteTree.getNodeByParam("id", menuId);
    	if(treeNode){
    		addFavoriteSelByNode(treeNode);
    	}
    }
    
    function addFavoriteSelByNode(treeNode) {
    	if(treeNode == null){
    		return;
    	}
    	
    	var a = [];
    	a.push('<li id="r' + treeNode.id + '" class="ui-state-default ui-element ui-draggable">');
    	a.push('<i class="icon-sort blue" title="按住已选择项拖动，可以调整顺序"></i>');
    	a.push('<span class="ui-text">' + treeNode.name + '</span>');
    	a.push('<a href="javascript:void(0);" onclick="removeFavoriteSel(' + treeNode.id + ')"><i class="icon-minus blue" title="取消选择"></i></a>');
    	a.push('</li>');
    	
    	$("#favorite-list").append(a.join(''));
    	
    	$("#r" + treeNode.id ).hover(function() {
    		$( this ).addClass("ui-state-hover" );
    	}, function() {
    		$( this ).removeClass("ui-state-hover" );
    	}).dblclick(function(){
    		$(this).find('a:first').click();
    	});
    	
    	favoriteTree.hideNode(treeNode);
    	updateFavoriteCount();
    }
    
    function removeFavoriteSel(menuId){
    	var treeNode = favoriteTree.getNodeByParam("id", menuId);
    	if(treeNode){
    		favoriteTree.showNode(treeNode);
    	} else {
    		//alert("找不到" + menuId);
    		return;
    	}
    	
    	 $("#r" + menuId).remove();
    	 updateFavoriteCount();
    }
    
    function clearFavoriteSel(){
    	$('#favorite-list').html('');
    	favoriteTree.showNodes(favoriteTree.getNodesByParam("isHidden", true));
    	updateFavoriteCount();
    }
    
    function updateFavoriteCount(){
    	var size = $('#favorite-list li').size();
    	$('#favorite-count-dlg').text(size);
    }
    
	function setChildPage(pageName) {
	    if (pageName === 'login') {
	        loginpage = true;
	        exitflag = true;
	        showLoading(false);
	    }
	}

	function doLockScreen(){
		blockPage();
		bootbox.confirm("您确定要锁定[" + systemname + "]吗?<br /><span class='textError'><i class='icon-info-sign bigger-120'></i> 如果长时间不使用，建议您锁定计算机(<i class='icon-windows'></i> + L)。</span>",
				function (result) {
				unblockPage();
	            if (result) {
	            	lockScreen();
	            }
	        });
	}
	
	function lockScreen(){
		setConfig('lock-screen', 'true', null);
		lockscreen = true;
		$('#frame-lock').attr('src', rootPath + "/pages/lock.jsp");
		$.blockUI({
			css: { 
		        padding:        0, 
		        margin:         0, 
		        width:          '100%', 
		        height:			'100%',
		        top:            '0', 
		        left:           '0', 
		        textAlign:      'center', 
		        color:          '#000', 
		        border:         'none', 
		        backgroundColor:'#fff', 
		        cursor:         'wait' 
		    },
		    message: $('#lock-dialog')
	    });
	}
	
	function unlockScreen(){
		setConfig('lock-screen', 'false', -1);
		lockscreen = false;
		$.unblockUI();
	}
	
	function initLockScreen(){
		if(getConfig('lock-screen') === 'true'){
			lockScreen();
		}
	}
	
	function doPassword() {
		popupDialog(rootPath + "/user/ChangePassword.action?method=open", 200, 430);
	}
	
	function doUserInfo() {
		popupDialog(rootPath + "/login/settings!queryUserInfo.action", 450, 600);
	}

	function handleLogout(type) {
	    curmenuid = "-1";
	    exitflag = true;
	    if (type == 1) {
	        setTimeout('window.location = "' + basepath + '/login/logout.action?action=exit&loginid=' + loginid + '";', 0);
	    } else {
	        setTimeout('window.location = "' + reloginurl + '";', 0);
	    }
	}

	function doRelogin() {
		blockPage();
	    bootbox.confirm("您确定要重登录[" + systemname + "]吗?",
	        function (result) {
	    		unblockPage();
	            if (result) {
	                handleLogout(0);
	            }
	        });
	}

	function doExit() {
		blockPage();
	    bootbox.confirm("您确定要退出[" + systemname + "]吗?",
	        function (result) {
	    		unblockPage();
	    		if (result) {
	                handleLogout(1);
	            }
	        });
	}

	function doTaskList() {
		$("#badgeTask").hide();
	}
	
	function doBulletinList() {
		$("#badgeBulletin").hide();
		openMenu("/message/BulletinManagerAction!list.action");
	}
	
	function doMessageList() {
		$("#badgeMessage").hide();
		openMenu("/message/MessageAction!managerMessage.action");
	}
	
	function showLoading(show, deplay) {
	    try {
	        if (show === undefined || show === null) {
	            show = true;
	        }

	        if(lockscreen){
	        	return;
	        }
	        
	        if (show == true) {
	            $.blockUI({
	                message: $('#loading-dialog'),
	               	css: {
						border: '1px solid #95b8e7',
						padding: '10px 30px',
						backgroundColor: '#ffffff',
						width: 'auto',
						"min-width": '150px',
						color: '#3776c5',
						"-webkit-box-shadow": '2px 2px 3px #cccccc',
	                	"-moz-box-shadow": '2px 2px 3px #cccccc',
	                	"box-shadow": '2px 2px 3px #cccccc'
	               	},
	               	/*baseZ: 50000,*/
					overlayCSS: {
						backgroundColor: '#000',
	                    opacity: 0.05,
	                    cursor: 'wait'
					}
	            });
	        } else {
	            var option = {};
	            if (deplay === undefined || deplay === null) {
	                deplay = 0;
	            }
	            option.fadeOut = deplay;
	            $.unblockUI(option);

	            $(document).css('cursor', 'auto');
	        }
	    } catch (e) {
	        alert(e.message);
	    }
	}
	
	var _messageTimer = null;
    function initMessage() {
    	try{
    		var str = '<%=request.getAttribute("messageList")%>';
			if(str !== 'undefined' && str !== null && str.length > 0){
				popupMessageList(powersi.tojson(str));
			}
			
			var _messageEnable = '<%=messageEnable%>' == '1';
    	  	var _messageInterval = parseFloat('<%=messageInterval%>');
    	  	if(_messageEnable){
    	  		if(_messageInterval < 10){
    	  			_messageInterval = 60;
    	  		}
    	  		
    	  		_messageTimer = setInterval(pollingMessage, _messageInterval * 1000);
    	  	}
    	} catch(ex){alert(ex.message);}
    }
    
    function openMsgDialog(url, height, width) {
    	ymPrompt.close();
    	
    	popupDialog(rootPath + "/" + url, height, width);
    }
    
    function popupMessageList(messages){
    	if(!powersi.isvalue(messages) || !powersi.isarray(messages) || messages.length == 0){
    		return;
    	}
    	
    	for(var idx in messages){
			var msg = messages[idx];
			var a = [];
			a.push("<div class='divMsg'>");
			a.push("<a href='javascript:void(0);' onclick='openMsgDialog(\"");
			a.push(msg.diag_url);
			a.push("\", " + msg.diag_height + "," + msg.diag_width);
			a.push(")'>");
			a.push(msg.ms_content);
			a.push("</a></div>");
			
			ymPrompt.win({
				title: systemname,
				message: a.join(""),
				fixPosition:true,
				winPos:'rb',
				showMask:false,
				width: 260,
				height: 150,
				icoCls: '',
				btn: null});
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
    	        	pollingSuccess(json);
    	        }
    	  });
    	} catch(ajaxException){
    		//alert(ajaxExcption.message);
    	}
    }
    
    function pollingSuccess(json) {
    	if(!checkJSONResult(json, false)){//不显示错误信息
		    return;
	    }

    	popupMessageList(json.data);
    }
	</script>
</body>
</powersi:html>