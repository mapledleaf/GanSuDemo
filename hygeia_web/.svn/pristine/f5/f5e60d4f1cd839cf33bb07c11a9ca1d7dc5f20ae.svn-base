<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@page import="com.powersi.hygeia.framework.BusiContext"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@page import="com.powersi.hygeia.framework.UserInfo"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String year = String.valueOf(new java.util.Date().getYear() + 1900);
	String systemName = ParameterMapping.getSystemName();
	String orgName = ParameterMapping.getStringByCode("center_org_name", "创智和宇");
	UserInfo userInfo = BusiContext.getUserInfo();
	String loginUser = UtilFunc.getString(userInfo, "login_user", "登录名");
	String userName = UtilFunc.getString(userInfo, "user_name", "用户名");
%>
<powersi:html>
<head>
<powersi:head title="<%=systemName %>" />
<link type="text/css" rel="stylesheet" href="${strutsPath}/js/plugins/ace/css/ace.min.css" charset="utf-8" />
<script src="${rootPath}/resource/js/md5.js" type="text/javascript"></script>
<script src="${strutsPath}/js/plugins/jquery.backstretch.min.js" type="text/javascript"></script>
<style type="text/css">
body{padding:0;margin:0;}
body, table, form, button{font-family: "Microsoft YaHei";font-size: 13px;}
.page-lock{top:45%;left:50%;position:absolute;margin-top:-140px;margin-left:-260px;}
.page-lock .page-logo{margin-bottom:15px;font-size:20px;color:#eb4545;text-align: center;}
.page-lock .page-body{width:500px;padding:10px;background:url(${rootPath}/resource/images/lock/bg-white.png) repeat;}
.page-lock .page-body:after,.page-lock .page-body:before{display:table;content:"";line-height:0;}
.page-lock .page-body:after{clear:both;}
.page-lock .page-footer{margin-top:10px;text-align:center !important;font-size:13px;color:#eaeaea;}
.page-lock img.page-lock-img{float:left;width:200px;height:auto;}
.page-lock .page-lock-info{float:right;width:240px;margin-top:40px;}
.page-lock .page-lock-info .input-group{margin:28px 0; width:200px;}
.page-lock .page-lock-info input{background:#fff;font-size:14px;padding:6px 4px 7px 4px;}
.user-name{margin-top:-5px;color:#fff;font-size:18px;margin-bottom:10px;}
.login-user{color:#fff;font-size:14px;margin-top:-5px;margin-bottom:10px;}
.locked{font-size:14px;margin-bottom:10px;color:#eee;}
#input-pwd{height:34px;line-height:28px;padding-top:0;padding-bottom:0;padding-left:31px;}
.input-icon > [class*='icon-']{font-size:16px;margin-top:2px;;vertical-align: baseline;color:#fcac6f;z-index:10;}
</style>
<script type="text/javascript">
$(function(){
	var imgurl = rootPath + "/resource/images/lock/";
	var isIE8 = !! navigator.userAgent.match(/MSIE 8.0/);
	if(isIE8){
		$.backstretch(imgurl + "1.jpg");
	} else {
		$.backstretch([imgurl + "1.jpg", imgurl + "2.jpg", imgurl + "3.jpg", imgurl + "4.jpg"], {
			fade: 1000,
			duration: 5000
		});
	}
	
	$("#button-pwd").click(function(){
		unlock();
	});
	$("#input-pwd").keydown(function (e) {
        if (e.keyCode != 13) {
            return;
        }
		unlock();
		
        e.returnValue = false;
        return false;
    }).focus();
});
function unlock(){
	try{
		var pwd = "{md5}" + hex_md5($('#input-pwd').val()).toUpperCase();
		postJSON(rootPath + "/login/settings!verifyPwd.action", {
			'pwd': pwd
		}, function(json){
			if(!checkJSONResult(json)){
				return;
			}
			var win = self.parent;
		    if(win && typeof win.unlockScreen === 'function'){
		    	win.unlockScreen();
		    }
		});
	} catch(e){
		alert(e.message);
	}
}
</script>
</head>
<body>
	<div class="page-lock">
	    <div class="page-logo">
	      	<%=systemName %>
	    </div>
	    <div class="page-body">
	    	<img class="page-lock-img" src="${rootPath}/resource/images/lock/bg-lock.png" alt="系统锁定">
			<div class="page-lock-info">
			  <div class="user-name"><%=userName %></div>
			  <div class="login-user"><%=loginUser %></div>
			  <div class="locked">输入密码解除锁定</div>
			  <div class="input-group">
			  	<div class="input-icon">
					<i class="icon-lock"></i>
					<input id="input-pwd" type="password" class="form-control" title="输入登录密码，解除屏幕锁定">
				</div>
				<span class="input-group-btn">
			  		<button id="button-pwd" type="button" class="btn btn-info btn-sm"><i class="icon-signin bigger-120"></i></button>
			  	</span>
			  </div>
			</div>
	    </div>
	    <div class="page-footer">
	      <%=year %> &copy; <%=orgName %> 创智和宇
	    </div>
  </div>
<powersi:errors />
</body>
</powersi:html>