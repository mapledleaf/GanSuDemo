<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@page
	import="com.powersi.hygeia.framework.ParameterMapping,com.powersi.sys.util.UserKindHelper,com.powersi.sys.user.dto.UserKindDTO,com.powersi.hygeia.framework.util.UtilFunc"%>
<%@page import="com.powersi.hygeia.web.util.SessionHelper"%>
<%
	String path = request.getContextPath();
	String year = String.valueOf(new java.util.Date().getYear() + 1900);
	//获取机构名称
	String orgName = ParameterMapping.getStringByCode(
			"center_org_name", "");
	//获取公司名称
	String corpName = ParameterMapping.getStringByCode("companyname",
			"");
	if (orgName.equals(corpName)) {
		corpName = "";
	}
	//是否支持验证码
	request.setAttribute("useCheckCode", ParameterMapping.getUseCheckCode());
	
	String systemName = ParameterMapping.getSystemName();
	//是否使用公司浏览器
	String autoLogin = "0";
	String useBrowser = request.getParameter("useBrowser");
	if (useBrowser == null) {
		useBrowser = "false";
	}
	//获取跳转url
	String url = request.getParameter("url");
	if (url == null) {
		url = "";
	}

	//获取用户类别
	UserKindDTO dto = null;
	String loginType = (String) request.getAttribute("loginType");
	if (loginType == null || loginType.length() == 0) {
		loginType = (String) request.getParameter("loginType");
		if (loginType != null && loginType.length() > 0) {
			useBrowser = "true";
		}
	}
	//判断是否自动登录
	if ("true".equals(useBrowser)) {
		autoLogin = "true".equals(request.getAttribute("autoLogin"))
				? "1"
				: "0";
	}

	request.setAttribute("useBrowser", useBrowser);

	if (loginType != null && loginType.trim().length() > 0) {
		dto = UserKindHelper.findType(loginType);
	}
	String actionName = "";
	String loginUserName = "";
	String loginSystemName = "";
	String pwdTip = "";
	//设置缺省的登录页面
	if (dto == null) {
		dto = UserKindHelper.findType("admin");
		loginType = "admin";
	}

	String userKind = "9";
	if (dto != null) {
		actionName = path + "/login/" + dto.getLoginType() + ".action";
		loginUserName = dto.getLoginName();
		loginSystemName = dto.getKindDesc();

		userKind = String.valueOf(dto.getUserKind());

		if (dto.getUserKind() == 2) {
			systemName = systemName + "企业端";
		}
	} else {
		actionName = path + "/login/login.action";
		loginUserName = "登录名";
		loginSystemName = "个人用户";

		userKind = "1";
	}

	String passwordUrl = "";
	String passwordStyle = "";
	if (dto != null && dto.getUserKind() == 1) {
		passwordUrl = "<a href=\""
				+ path
				+ "/login/OpenRetrievePassword.action\" target=\"_self\" style=\"vertical-align:middle;\">找回密码？</a>";
		passwordStyle = "style=\"width:85px;\"";
	}

	//处理用户名
	String loginUser = request.getParameter("loginuser");
	if (UtilFunc.isEmpty(loginUser)) {
		loginUser = (String) request.getAttribute("loginUser");
	}

	//非公司浏览器从cookie获取登录名
	if (!"true".equals(useBrowser) && UtilFunc.isEmpty(loginUser)) {
		javax.servlet.http.Cookie cookie = com.powersi.hygeia.web.util.WebHelper
				.getCookie(request, "LoginUser");
		if (cookie != null && cookie.getValue() != null) {
			loginUser = java.net.URLDecoder.decode(cookie.getValue(),
					"utf-8");
			//删除历史版本的旧cookie
			if ("loginUser".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	if (UtilFunc.isEmpty(loginUser)) {
		loginUser = "";
	} else {
		//简单处理xss攻击
		loginUser = UtilFunc.encodeXml(loginUser);
	}

	//处理错误信息
	String errorMessage = "";
	if (request.getAttribute("javax.servlet.error.message") != null) {
		errorMessage = (String) request
				.getAttribute("javax.servlet.error.message");
	}
	if (request.getAttribute("hygeiaErrors") != null) {
		errorMessage = (String) request.getAttribute("hygeiaErrors");
	}
	if(errorMessage == null || errorMessage.length() == 0){
		//检查主数据源是否正常
		if(!com.powersi.hygeia.framework.util.DBFunc.isValidDataSource()){
			errorMessage = "系统出现异常，数据源无法连接";
		}
	}
	errorMessage = errorMessage.trim();

	//桌面模式
	String useDesktop = "desktop".equals(ParameterMapping
			.getStringByCode("main_page", "main")) ? "true" : "false";

	//提交按钮（防止公司浏览器劫持提交）
	String btnSubmit = "true".equals(useDesktop)
			? "ButtonSubmit"
			: "ButtonOK";
	//解决公司浏览器   1、进入系统后弹窗的每日一题弹不出来。 2、智能联机帮助在窗口中显示不出来。
	btnSubmit = "ButtonSubmit";
	useBrowser = "false";
	//非公司浏览器自动登录跳转
	if (!"true".equals(useBrowser)) {
		com.powersi.hygeia.framework.UserInfo userInfo = SessionHelper
				.getUserInfo(request);
		boolean isRelogin = false;
		String action = request.getParameter("action");
		if(action != null && ("relogin".equals(action) || "exit".equals(action))){
			isRelogin = true;
		}
		//注销不会清理当前请求的用户信息，所以需要判断注销时间是否为空
		//重登录需要判断是否存在错误信息
		if (isRelogin == false && userInfo != null && userInfo.isValidUser()
				&& userInfo.getLogoutDate() == null && errorMessage.isEmpty()) {
			String redirect = null;
			if (url == null || url.length() == 0) {
				redirect = "/main.action";
			} else {
				redirect = UtilFunc.decodeBase64URL(url);
			}
			response.sendRedirect(path + redirect);
		}
	}
%>
<powersi:html>
<head>
<powersi:head title="<%=systemName%>" />
<link type="text/css" rel="stylesheet" href="${rootPath}/login/gdyb/login.css" charset="utf-8" />
<script src="${rootPath}/resource/js/md5.js" type="text/javascript"></script>
<script src="${strutsPath}/js/plugins/jquery.backstretch.min.js" type="text/javascript"></script>
<style type="text/css">
.curdate, .login-bottom{font-size: 16px;}
.btn-sbumit{font-size:20px; font-weight: 500; height: 40px;}
.login-tools{position: absolute; bottom: 35px;}
.login-inputs{padding-top: 0;}
.form-title{text-align: center; font-size: 18px; padding:15px; color: #00428e;}
.alert{padding: 5px 15px; width: 300px; margin: 5px auto; font-size: 14px; border-radius: 0;}

#VerifyCode {border-right-width: 0;}
#VerifyImage {float: left; width: 75px; height: 40px; border: none; cursor: pointer;}

.has-error.form-item {border: 1px solid #d44950 !important;}
.has-error.form-item label,.help-block {color: #d44950;}

.help-block{width: 300px; margin-top: 0; margin-bottom: 0; margin-left:40px; height: 20px; color: #d44950; font-size:12px; float:left;}
.form-item { margin-bottom: 0; }
.form-item input{padding-top:0; padding-bottom:0; line-height: 40px; color: #000;}
.form-group{ margin-bottom: 10px; float: left;}
</style>
<script type="text/javascript">
try{
	// 登录页面不允许嵌入到iframe
	if(window.top !== window.self) { 
		//不允许嵌套在main.jsp中
		if($.isFunction(window.top.__openMenu__)){
			window.top.location = window.location;
		}
	}
	
	if (parent && typeof parent.setChildPage === 'function') {
		parent.setChildPage('login');
	}
}catch(e){}
var useBrowser = "<%=useBrowser%>" == "true";
document.oncontextmenu = function () { return false; };
</script>
</head>
<body>
	<div class="login-top">
		<div class="login-logo"></div>
		<div class="login-top-right">
			<div class="curdate" id="curdate"></div>
		</div>
	</div>
	<div class="login-center" id="loginCenter">
		<div class="login-inputs">
			<form name="loginform" id="loginform" action="<%=actionName%>" method="post">
				<div class="form-title"><%=systemName%></div>
				<div class="alert alert-danger" id="div-error" style="display:none;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<span id="messageInfo"><%=errorMessage %></span>
				</div>
				<div class="form-group">
					<div class="form-item">
						<span>用户名</span> 
						<input id="UserName" name="loginUser" value="<%=loginUser %>" tabindex="1" type="text" size="25" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<div class="form-item">
						<span>密码</span> 
						<input id="UserPassword" name="inputPwd" tabindex="2" type="password" value="" size="25" autocomplete="off">
					</div>
				</div>
				
				<s:if test="#request.useCheckCode==true">
					<div class="form-group">
						<div class="form-item" style="width:140px;float:left;">
							<span>验证码</span> <input id="VerifyCode" name="verifycode"
							class="required form-control vcode" tabindex="3" type="text"
							value="" size="25" autocomplete="off" maxlength="4">
						</div>
						<img id="VerifyImage" class="vcodeimg" name="verifyImage"
							onclick="javascript:refeshCode();" title="看不清换一张" height="32"
							placeholder="请填入右图中的4位验证码"
							width="70" style="cursor: pointer;"> 
						<label for="vcode" id="VerifyLabel"class="vcode-label">看不清换一张 </label>
					</div>
	            </s:if>
				
				<button class="btn-sbumit" type="submit" id="<%=btnSubmit%>" onclick="savePwd();" data-loading-text="登录中...">登 录</button>
				
				<s:if test="#request.useBrowser=='true'">
					<input type="hidden" id="RemoteCallUrl" name="RemoteCallUrl" value="ProcessAll" />
	   				<input type="hidden" id="RemoteCallMethod" name="RemoteCallMethod" value="Hygeia" />
					<input type="hidden" name="user_kind" id="user_kind" value="<%=userKind %>" />
					<input type="hidden" name="auto_login" id="auto_login" value="<%=autoLogin %>" />
					<input type="hidden" id="AddData" name="AddData" value="user_kind,auto_login" />
				</s:if>
				<div class="hidden">
					<input type="checkbox" id="SaveLogin" value="1" checked="checked" name="saveLogin" /> 
					<label for="SaveLogin">记住我的用户名</label>
				</div>
				<input type="hidden" name="password" id="EncryptedPassword" />
				<input type="hidden" id="url" name="url" value="<%=url %>" />
			</form>
			<div class="login-tools">
				<a href="javascript:window.external.AddFavorite(window.location.href,document.title);"
					class="tool-icon-01" title="加入收藏(Ctrl+D)"><span>加入收藏</span></a> <a
					href="javascript:void(0)" class="tool-icon-02" title="设为主页"><span>设为主页</span></a>
				<a href="<%=path %>/resource/setup/setup.exe" class="tool-icon-03" title="下载社会保险管理信息系统浏览器"><span>下载</span></a>
				<a class="tool-icon-04" title="打开客服平台"  onclick="openKfpt()"><span>联系客服</span></a>
			</div>
		</div>
	</div>
	<div class="login-bottom">技术支持：创智和宇信息技术股份有限公司</div>
	<script type="text/javascript">
	function openKfpt(){
		__isPing(__customer_service);
	};
	var __customer_service=function(t){  //获取接口服务模块信息
		var req = {"func_id": "customer_service","data":{"param":"<%=loginUser%>"}};
		call_agent_manage(req,function(obj){
			if (!obj.success_flag) {
				alert(obj.error_info);
				return;
			}
		});
	};
	</script>
	<script type="text/javascript">
	$(function() {
		var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");  
		var Stamp = new Date();
		var  s ="今天是："+ Stamp.getFullYear() + "年"+(Stamp.getMonth() + 1) +"月"+Stamp.getDate()+ "日"+ " " + dayNames[Stamp.getDay()];
		$("#curdate").html(s);
		
		$(".login-bottom").backstretch(rootPath + "/login/gdyb/images/bottom.png");
		
		if($.trim($('#messageInfo').text()).length > 0){
			$('#div-error').show();
		} else{
			$('#div-error').hide();
		}
		
		if($.browser.msie && $.browser.version && $.browser.version < 8){
			alert('您的IE浏览器版本过低，要正常使用系统，请升级浏览器到IE8及以上版本。');
		}
		
		//处理自定义浏览器自动登录
		if(useBrowser){
			if("1" === "<%=autoLogin%>"){
				$('#loginForm').attr('action', '');
				$('#<%=btnSubmit%>').click();
				return;
			}
		}
		
		$('#loginform').validate({
			errorElement: 'span',
            errorClass: 'help-block',
            focusInvalid: true,
            rules: {
            	loginUser: {
                    required: true
                },
                inputPwd: {
                    required: false
                },
                verifycode: {
                    required: true,
                    digits: false,
                    minlength: 4,
                    maxlength: 4
                }
            },

            messages: {
            	loginUser: {
                    required: "请输入用户名."
                },
                inputPwd: {
                	required: "请输入密码."
                },
                verifycode: {
                    required: "请输入验证码."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-item').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').find('.form-item').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
            	var el = element.closest('.form-group');
                el.append(error);
            },

            submitHandler: function (form) {
            	$('#messageInfo').html('');
				$('#div-error').hide();
				
				if(useBrowser && "false" == "<%=useDesktop%>"){
					$('#<%=btnSubmit%>').click();
				} else {
					var username = S("UserName").value;
					if(username.indexOf("'") >= 0 || username.indexOf('"') >= 0){
						alert('用户名包含非法字符，请检查');
						return;
					}
					
					$('#EncryptedPassword').val("{md5}" + hex_md5($('#UserPassword').val()).toUpperCase());
		 	        $('#UserPassword').val("");
		 	        
		 	       	$('#<%=btnSubmit%>').button('loading');
		 	       	setRunning(false);
		 	        form.submit();	
				}
            }
        });
		
		if(document.getElementById("VerifyCode") != null){
			$("#VerifyImage").attr("src",function(){
				var timesamp = new Date().getTime();
				return rootPath + "/login/verifycode.action?timesamp="+timesamp;
			});
			
			$("#VerifyCode").attr("autocomplete", "off");
			
			$("#VerifyImage").click(function(){
				var timesamp = new Date().getTime();
				document.getElementById("VerifyImage").src= rootPath + "/login/verifycode.action?timesamp="+timesamp;
			});
			
			$("#VerifyLabel").click(function(){
				var timesamp = new Date().getTime();
				document.getElementById("VerifyImage").src= rootPath + "/login/verifycode.action?timesamp="+timesamp;
			});
		}
		
		if(!useBrowser){
			if($("#UserName").val() == "") {
				S("UserName").focus();
			} else {
				$("#UserPassword").focus();
			}	
		}
	});
	
	function savePwd() {
		$('#EncryptedPassword').val(
			"{md5}" + hex_md5($('#UserPassword').val()).toUpperCase());
		return true;
	}
	</script>
</body>
</powersi:html>