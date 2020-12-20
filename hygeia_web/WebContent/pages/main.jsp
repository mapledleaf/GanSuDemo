<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@page import="java.util.Calendar,java.net.URLEncoder,java.lang.StringBuilder"%>
<%@page
import="com.powersi.hygeia.framework.GlobalContext,com.powersi.hygeia.framework.UserInfo,com.powersi.hygeia.framework.BusiContext,com.powersi.hygeia.framework.CodetableMapping,com.powersi.hygeia.framework.util.UtilFunc,com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
	String welcomeUrl = com.powersi.hygeia.framework.ParameterMapping.getStringByCode("welcome_url",
			"/pages/welcome.jsp");
	if (!welcomeUrl.startsWith("/") && !welcomeUrl.startsWith("http")) {
		welcomeUrl = "/" + welcomeUrl;
	}
	
	String systemName = com.powersi.hygeia.framework.ParameterMapping.getSystemName();
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
	String akb021 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAkb021();
	String akb020 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAkb020();
	if (akb021 == null) {
		akb021 = "";
	}
	if (akb020 == null) {
		akb020 = "";
	}
	akb021 = "【" + akb021 + "】【" + akb020 + "】";
	StringBuilder sb = new StringBuilder();
	sb.append(path);
	sb.append("/login/logout.action?");
	sb.append("logintype=");
	sb.append(loginType);
	if (loginType.length() == 0 || loginType.equals("login")) {
		sb.append("&userkind=");
		sb.append(userKind);
	}
	sb.append("&loginuser=");

	loginUser = URLEncoder.encode(loginUser, GlobalContext.getCharset());

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
	StringBuilder sbUser = new StringBuilder();
	sbUser.append("用户名\t：").append(userName);
	if (user != null && user.isValidUser()) {
		sbUser.append("\n统筹区\t：").append(
				CodetableMapping.getDisplayValue("aaa027_list", UtilFunc.getString(user, "center_id"), ""));
		sbUser.append("\n所属部门：")
				.append(CodetableMapping.getDisplayValue("sys_dept", UtilFunc.getString(user, "dept_id"), ""));
		sbUser.append("\n操作级别：").append(
				CodetableMapping.getDisplayValue("staff_level", UtilFunc.getString(user, "staff_level"), ""));
		sbUser.append("\n操作岗位：").append(
				CodetableMapping.getDisplayValue("staff_role", UtilFunc.getString(user, "staff_role"), ""));
	}
	String userInfo = sbUser.toString();

	String menuRender = (String)request.getAttribute("menuRender");
	if(menuRender == null || menuRender.isEmpty()){
		request.setAttribute("menuRender", "[]");
	}
	//消息轮询设置
	String messageEnable = com.powersi.hygeia.framework.ParameterMapping.getStringByCode("message_flag", "0");
	String messageInterval = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("message_query_interval", "180");

	//统一门户标志
	String unifiedFlag = com.powersi.sys.util.SingleSignOnHelper.isEnabled() ? "true" : "false";

	//是否显示搜索
	String searchClass = "1".equals(com.powersi.hygeia.framework.ParameterMapping.getStringByCode("show_search", "0")) ? "" : "hidden";
	
	//桌面不需要主界面做消息轮询
	if ("desktop".equals(com.powersi.hygeia.framework.ParameterMapping.getStringByCode("main_page"))) {
		messageEnable = "0";
	}
%>
<!DOCTYPE html>
<html>
<head>
	<powersi:head title="<%=systemName %>" watermark="false"  />
    <link rel="stylesheet" href="<%=path %>/struts/js/plugins/layui/css/layui.css" charset="utf-8" media="all" />
    <link rel="stylesheet" href="<%=path %>/resource/admin/style/admin.css" charset="utf-8" media="all" />
    <link rel="stylesheet" href="<%=path %>/resource/admin/style/main.css" charset="utf-8" media="all" />
    <script src="<%=path %>/struts/js/plugins/pace/pace.min.js" charset="utf-8" type="text/javascript"></script>
    <style type="text/css">
  	.divMsg{height:90px;display:table;width:99%;margin:0 auto;background:url('${strutsPath}/css/images/ligerui/win/information.png') no-repeat left center;}
	.divMsg a{display:table-cell;width:280px;vertical-align:middle;font-size:14px !important;text-align:left;padding-left:60px;}
    </style>
</head>
<body class="layui-layout-body">
<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect="">
                	<a href="javascript:;" layadmin-event="flexible" lay-tips="侧边伸缩"> 
                		<i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                	</a>
                </li>
                 <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" layadmin-event="menu" lay-tips="菜单列表"> 
                		<i class="layui-icon layui-icon-more"></i>
                	</a>
                </li>
                
                <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" lay-tils="收藏夹" id="LAY_app_favorite_icon">
                    	<i class="layui-icon layui-icon-star"></i>
                	</a>
                    <dl class="layui-nav-child" id="LAY_app_favorite_list" lay-filter="layadmin-favorite-list">
                        <!-- 收藏夹列表 -->
                    </dl>
                </li>
                
                <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" layadmin-event="home" lay-tips="欢迎页">
                		<i class="layui-icon layui-icon-home"></i>
                	</a>
                </li>
                <li  class="layui-nav-item" >
					<a onclick="openKfpt()" href="javascript:void(0)" lay-tips="联系客服">
						<i class="layui-icon layui-icon-face-smile-fine"></i> 
						<span class="badge badge-purple hide" id="badgeBulletin">0</span>
					</a>
				</li>
                <li class="layui-nav-item" lay-unselect="">
		            <a href="javascript:;" layadmin-event="refresh" lay-tips="刷新页面">
		              <i class="layui-icon layui-icon-refresh-1"></i>
		            </a>
		        </li>
            </ul>
            <div class="layui-layout-center">
				<%=akb021 %>
			</div>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
            	<li class="layui-nav-item <%=searchClass %>" lay-unselect="" style="margin-right: 20px;">
                	<input id="LAY-app-search" type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search"
                		layadmin-event="search" lay-action="/pages/search.jsp?keywords=">
                </li>                
                <li class="layui-nav-item" lay-unselect="">
                    <a href="javascript:;" layadmin-event="message" lay-tips="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>
                        <!-- 如果有新消息，则显示数字 -->
                        <span id="LAY-app-message-dot" class="layui-badge">0</span>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" layadmin-event="theme" lay-tips="主题设置">
                		<i class="layui-icon layui-icon-theme"></i>
                	</a>
                </li>
                <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" layadmin-event="fullscreen" lay-tips="全屏显示">
                		<i class="layui-icon layui-icon-screen-full"></i>
                	</a>
                </li>
                <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" id="LAY-app-user">
                    	<i class="layui-icon layui-icon-user"></i>
                    	<cite><%=userName %></cite>
                	</a>
                    <dl class="layui-nav-child" id="LAY_app_tool_list">
                        <dd layadmin-event="userinfo">
                            <a>用户信息</a>
                        </dd>
                        <dd layadmin-event="changepwd">
                            <a>修改密码</a>
                        </dd>
                        <hr />
                        <dd layadmin-event="relogin">
                            <a>重登录</a>
                        </dd>
                        <dd layadmin-event="logout">
                            <a>退出</a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item" lay-unselect="">
                	<a href="javascript:;" layadmin-event="logout" style="padding: 0 12px" lay-tips="退出系统">
                		<i class="layui-icon layui-icon-close"></i>
                	</a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo">
                    <span><%=systemName %></span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all"
                    id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                  	<!-- 菜单列表 -->
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down layui-icon-more-vertical" id="LAY_app_tabsmore">
                <ul class="layui-nav layadmin-tabs-select"
                    lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect="">
                    	<a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="refresh">
                                <a href="javascript:;">刷新当前标签页</a>
                            </dd>
                            <hr/>
                            <dd layadmin-event="openThisTabs">
                                <a href="javascript:;">在新窗口打开</a>
                            </dd>
                            <hr/>
                            <dd layadmin-event="closeThisTabs">
                                <a href="javascript:;" class="red">关闭当前标签页</a>
                            </dd>
                            <dd layadmin-event="closeOtherTabs">
                                <a href="javascript:;" class="red">关闭其它标签页</a>
                            </dd>
                            <dd layadmin-event="closeAllTabs">
                                <a href="javascript:;" class="red">关闭全部标签页</a>
                            </dd>
                            <hr/>
                            <dd class="layui-form" lay-filter="layadmin-pagetabs-form">
                            	<!-- 打开标签页列表  -->
                                <select lay-search lay-filter="layadmin-pagetabs-select" id="LAY_app_tabsselect">
                                    <option value="">搜索标签页</option>
                                    <option value="WELCOME">欢迎页</option>
                                </select>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true"
                 lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="WELCOME" lay-attr="<%=path %><%=welcomeUrl%>" lay-tips="欢迎页"
                        class="layui-this">
                        <i class="layui-icon layui-icon-home"></i>
                        欢迎页
                     </li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="<%=path %><%=welcomeUrl%>" frameborder="0" data-tabid="0"
                        class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
        
        <!-- 菜单列表 -->
        <div id="LAY_app_popupmenu" style="display:none;"></div>
        
         <!-- 消息列表 -->
        <div id="LAY_app_popupmessage" style="display:none;">
        	<div id="LAY_app_messageview" class="layui-card-body noMargin noScroll noPadding">
				<div class="divCenter" style="border-bottom: 1px solid #eee;">
					<div class="btn-group" style="padding: 15px 0;">
						<a type="button" class="btn btn-default" role="button"
							lay-tips="打开公告中心" style="width: 80px"
							lay-href="/message/BulletinManagerAction!list.action">公告中心</a> <a
							type="button" class="btn btn-default" role="button"
							lay-tips="打开消息中心" style="width: 80px"
							lay-href="/message/MessageAction!managerMessage.action">消息中心</a> <a
							type="button" class="btn btn-default hidden" role="button"
							lay-tips="打开待办任务" style="width: 80px"
							lay-href="/pages/sys/message/TaskList.jsp">待办任务</a>
					</div>
				</div>
				<div class="divCenter red" id="LAY_app_messagenone" style="margin-top:30px" style="display:none;">
					暂无最新的公告和消息
				</div>
				<div id="LAY_app_messagelist" style="height: 200px; overflow-x: hidden; overflow-y: auto; display: none;">
					<ul class="list inset">
					</ul>
				</div>
			</div>
        </div>

		<!-- 收藏夹 -->
        <div id="LAY_app_favorite" style="display:none;"></div>
        
        <!-- 题库答题 -->
        <div id="LAY_app_answer_shadow" style="display:none;"></div>
    	<div id="LAY_app_answer_content" style="display:none;"></div>

		<!-- 派工单确认面板 -->
        <div id="LAY_app_job_order_mask" style="display:none;"></div>
        <div id="LAY_app_job_order" style="display:none;"></div>

		<div id="LAY_app_card_control" style="display: none">
			<object id="cardControl" width="0" height="0" classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5"
					onerror="alert('控件初始化失败!')">
			</object>
		</div>

		<!-- 派工单建议面板 -->
        <div id="LAY_app_suggestion_mask" style="display:none;"></div>
        <div id="LAY_app_suggestion" style="display:none;"></div>

        <!-- 回款单确认面板 -->
        <div id="LAY_app_payment_collection_mask" style="display:none;"></div>
        <div id="LAY_app_payment_collection" style="display:none;"></div>

        <!-- 回款单信息回填面板 -->
        <div id="LAY_app_payment_info_mask" style="display:none;"></div>
        <div id="LAY_app_payment_info" style="display:none;"></div>
    </div>
</div>
<script src="<%=path %>/struts/js/plugins/layui/layui.js" charset="utf-8" type="text/javascript"></script>
<script src="<%=path %>/resource/admin/modules/main.js" charset="utf-8" type="text/javascript"></script>
<script src="<%=path %>/struts/include/ztree.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript">
function openKfpt(){
	__isPing(__customer_service);
};
var __customer_service=function(t){  //获取接口服务模块信息
	var req = {"func_id": "customer_service","data":{"param":"<%=akb020%>"}};
	call_agent_manage(req,function(obj){
		if (!obj.success_flag) {
			alert(obj.error_info);
			return;
		}
	});
};
</script>
<script type="text/javascript">
	try{
		var systemname = "<%=systemName%>";
		var basepath = "<%=path %>";
		var reloginurl = "<%=reloginUrl %>";
		var welcomeurl = "<%=welcomeUrl %>";
		var loginid = "<%=loginId %>";
		var username = "<%=userName %>";
		var menuRender = ${menuRender};
		var bookmarkEnabled = '${bookmarkEnabled}';
		var bookmarkList = '${bookmarkList}';
		var favoriteList = [];
		
		var messageEnable = '<%=messageEnable%>' == '1';
	  	var messageInterval = parseFloat('<%=messageInterval%>');
	  	var messageList = '<%=request.getAttribute("messageList")%>';
	  	
		if(bookmarkEnabled !== 'true'){
			bookmarkList = $.cookie('bookmark') || '';
		}
		
		if(bookmarkList != null && bookmarkList.length > 0){
			favoriteList = powersi.tojson('[' + bookmarkList + ']');;
		}
		
		if(systemname && systemname.length > 15){
			$(".layui-logo").css("font-size", "12px");
		}
	} catch(ex){
		alert(ex.message);
	}
</script>
<script>
    layui.config({
        base: '<%=path %>/resource/admin/'
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'element', 'form'], function() {
    	var $ = layui.$, form = layui.form;
    	$.extend(layui.setter, {
        	appname: systemname
           	,contextpath: basepath
           	,reloginurl: reloginurl
           	,welcomeurl: welcomeurl
           	,loginid: loginid
           	,username: username
    	});
    	
    	setTimeout(function() {
    		initMenuTree();
        	layui.element.render('nav');
        	
        	initFavorite();
        	initMessage();
    	}, 0);
    	
    	$(window).resize(function() {
    		resizePopupMenu();
    		resizePopupMessage();
    	});
    });
</script>
<!-- 快速指南(不支持IE8) -->
<!--[if gte IE 9]><!-->
 <link href="${strutsPath }/js/plugins/intro/css/introjs.min.css" charset="utf-8" rel="stylesheet" media="all" />
 <script src="${strutsPath }/js/plugins/intro/js/intro.min.js" charset="utf-8" type="text/javascript"></script>
 <style type="text/css">
 	.layui-layout-admin .layui-header, .layui-side, .layui-layout-admin .layui-logo, .layadmin-pagetabs{position: absolute;}
 	.layui-layout-admin .layui-layout-center{margin: 0 auto; text-align: center; height: 50px; line-height: 50px; color: #000; font-weight: 500;}
 	.introjs-tooltiptext{font-size: 14px; color: #333; margin-top: 10px;}
 	.introjs-skipbutton{float: left;}
 	.introjs-bullets{margin-top: 5px;}
 	.introjs-tooltipbuttons{margin-top: 5px;}
 </style>
 <script type="text/javascript">
 	$('#LAY_app_tool_list dd[layadmin-event="relogin"]').before('<dd layadmin-event="guide"><a>快速指南</a></dd><hr />');
 	$(function() {
 		getUserConfig("quick_guide", "0", function(res) {
 			if(res.data && res.data == "0"){
 //				openFirstUsed();
 //				saveUserConfig("quick_guide", "1");
 			}
 		});
 	});
</script>
 <!--<![endif]-->
 <!--题库答题 start -->
<%--<script src="<%=path %>/resource/js/answerControl.js" charset="utf-8" type="text/javascript"></script>--%>
<script src="<%=path %>/resource/js/html2canvas.js" charset="utf-8" type="text/javascript"></script>
<%--<script src="<%=path %>/resource/js/confirmPanel.js" charset="utf-8" type="text/javascript"></script>--%>

<style type="text/css">
#LAY_app_answer_shadow{ 
		display: none; 
		position: absolute; 
		top: 0%; 
		left: 0%; 
		width: 100%; 
		height: 100%; 
		background-color: black; 
		z-index:1001; 
		-moz-opacity: 0.3; 
		opacity:.30; 
		filter: alpha(opacity=30);
	}
#LAY_app_answer_content{ 
		display: none; 
		position: absolute; 
		top: 25%; 
		left: 32%; 
		width: 40%; 
		min-height: 50px;
		padding: 8px;
		border: 8px solid #E8E9F7; 
		background-color: white; 
		z-index:1002; 
		overflow: auto;
		font-size:16px;
	}
#LAY_app_answer_content p{ 
		padding: 2px;
	}

#LAY_app_job_order_mask{
    display: none;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: black;
    z-index:1003;
    -moz-opacity: 0.3;
    opacity:.30;
    filter: alpha(opacity=30);
}

#LAY_app_job_order{
	display: none;
	position: absolute;
	top: 25%;
	left: 32%;
	width: 40%;
	min-height: 50px;
	padding: 8px;
	border: 8px solid #E8E9F7;
	background-color: white;
	z-index:1004;
	overflow: auto;
	font-size:16px;
}

#LAY_app_suggestion_mask{
    display: none;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: black;
    z-index:1005;
    -moz-opacity: 0.3;
    opacity:.30;
    filter: alpha(opacity=30);
}

#LAY_app_suggestion{
    display: none;
    position: absolute;
    top: 25%;
    left: 32%;
    width: 40%;
    min-height: 50px;
    padding: 8px;
    border: 8px solid #E8E9F7;
    background-color: white;
    z-index:1006;
    overflow: auto;
    font-size:16px;
}

#LAY_app_payment_collection_mask{
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index:1007;
	-moz-opacity: 0.3;
	opacity:.30;
	filter: alpha(opacity=30);
}

#LAY_app_payment_collection{
	display: none;
	position: absolute;
	top: 25%;
	left: 10%;
	width: 80%;
	min-height: 50px;
	padding: 8px;
	border: 8px solid #E8E9F7;
	background-color: white;
	z-index:1008;
	overflow: auto;
	font-size:16px;
}

#LAY_app_payment_info_mask{
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index:1009;
	-moz-opacity: 0.3;
	opacity:.30;
	filter: alpha(opacity=30);
}

#LAY_app_payment_info{
	display: none;
	position: absolute;
	top: 25%;
	left: 32%;
	width: 40%;
	min-height: 50px;
	padding: 8px;
	border: 8px solid #E8E9F7;
	background-color: white;
	z-index:1010;
	overflow: auto;
	font-size:16px;
}

.payment_list{
	border-collapse: collapse;
	width: 100%;
}

.payment_list td{
	border: solid #000 1px;
	text-align: center;
}

.payment_list tbody td{
	color:red;
}
</style>
<!--题库答题 end-->
</body>
</html>