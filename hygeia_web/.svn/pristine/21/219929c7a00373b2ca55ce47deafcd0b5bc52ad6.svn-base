<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@page import="com.powersi.hygeia.framework.UserInfo"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@page import="com.powersi.hygeia.framework.BusiContext"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	String systemName = ParameterMapping.getSystemName();
	UserInfo userInfo = BusiContext.getUserInfo();
	String userName = BusiContext.getUserInfo().getUserName();
	String loginUser = BusiContext.getUserInfo().getLoginUser();
	String akb020 = BizHelper.getAkb020();
	if("2".equals(BusiContext.getUserInfo().getUserKind())){
		systemName = systemName + "企业端";
	}
	
	String validUser = String.valueOf(BusiContext.getUserInfo().isValidUser());
%>
<powersi:html>
<head>
<powersi:head title="" />
<style type="text/css">
html, body{
	overflow: hidden;
	padding: 0;
	margin: 0;
}
.navbar{
	margin:0;
	overflow: hidden;
	padding-left:0;
	padding-right:0;
	border-width:0;
	border-radius:0;
	-webkit-box-shadow:none;
	box-shadow:none;
	min-height:40px;
	height: 40px;
	background:#438eb9;
}
.navbar-container{
	padding-left:10px;
	padding-right:10px;
	height: 100%;
}
.navbar-header {
	
}
.navbar-brand {
	font-size: 1.3em;
	font-weight: 500;
	color: #fff !important;
	height: auto;
	padding: 10px 0;
}
#navbar-buttons{
	overflow: hidden;
	line-height: 40px;
}
#navbar-user {
	font-size: 1.05em;
	color: #fff !important;
	padding-right: 10px;
	height: auto;
}
#icon-user {
	font-size: 16px;
}
.navbar-btn{
	color: #fff !important;
	font-size: 1em;
	padding: 0 0 0 10px;
	height: auto;
}
#navbar-user{
	border-right: 1px solid #fff;
}

#navbar-theme{
	border-left: 1px solid #fff;
	margin-left: 10px;
	font-size: 1em;
	vertical-align: top;
}

#selectTheme, #selectSize {
	width:auto;
	height:24px;
	color:#000;
	line-height:24px;
}

a:hover{
	text-decoration: none;
}
.fa {
	font-size: 14px;
}
.fa-animated-vertical {
	display:inline-block;
	-moz-animation:vertical 2.0s 5 ease 2.0s;
	-webkit-animation:vertical 2.0s 5 ease 2.0s;
	-o-animation:vertical 2.0s 5 ease 2.0s;
	-ms-animation:vertical 2.0s 5 ease 2.0s;
	animation:vertical 2.0s 5 ease 2.0s
}
</style>
</head>
<body>
	<div id="navbar" class="navbar">
		<div class="navbar-container">
			<div class="floatLeft">
				<a href="javascript:void(0)" class="navbar-brand">
					<i class="icon-eye-open"></i>
						<%=systemName%>
				</a>
			</div>
			<div id="navbar-buttons" class="floatRight">
				<a href="javascript:void(0)" id="navbar-user" title="用户信息">
					<i class="icon-user" id="icon-user"></i>
						欢迎，<%=userName %>
				</a>
				<a href="javascript:openKfpt();" id="navbar-lxkf" title="联系客服" class="navbar-btn" >
					<i class="fa fa-whatsapp"></i>
				</a>
				<a id="navbar-task" title="任务列表" class="navbar-btn">
					<i class="fa fa-tasks fa-animated-vertical"></i>
				</a>
				<a id="navbar-bulletin" title="通知列表" class="navbar-btn">
					<i class="fa fa-bell"></i>
				</a>
				<a id="navbar-message" title="消息列表" class="navbar-btn">
					<i class="fa fa-envelope"></i>
				</a>
				<a id="navbar-menumap" title="菜单导航" class="navbar-btn">
					<i class="fa fa-reorder"></i>
				</a>
				<a id="navbar-setting" title="系统选项" class="navbar-btn">
					<i class="fa fa-cog"></i>
				</a>
				<a id="navbar-theme" class="navbar-btn">
					<label for="selectSize" title="选择文字大小">文本</label>
		            <select id="selectSize" title="选择文字大小">
		                <option value="small">小</option>
		                <option value="medium">中</option>
		                <option value="large">大</option>
		            </select>
		            
					<label for="selectTheme" title="选择主题">主题</label>
		            <select id="selectTheme" title="选择主题">
		                <option value="blue">蓝色</option>
		                <option value="white">白色</option>
		                <option value="gray">灰色</option>
		                <option value="pink">粉色</option>
		                <option value="green">绿色</option>
		                <option value="yellow">黄色</option>
		            </select>
				</a>
			</div>
		</div>
	</div>
<script type="text/javascript">
function openKfpt(){
	__isPing(__customer_service);
};
var __customer_service=function(t){  //获取接口服务模块信息
	var req = {"func_id": "customer_service","data":{"param":"<%=akb020 %>"}};
	call_agent_manage(req,function(obj){
		if (!obj.success_flag) {
			alert(obj.error_info);
			return;
		}
	});
};
</script>
<script type="text/javascript">
	$(function(){
		if(isActivex()){
			var bg = window.external.getConfigString("AccentColor");
			if(powersi.length(bg) > 0){
				$('#navbar').css('background', bg);
			}
			
			$('#navbar-setting').click(function() {
				openMenu('CONFIG');
			});
		} else {
			$('#navbar-setting').hide();
		}
		
		$('#navbar-task').click(function(){
			openMenu('/pages/sys/message/TaskList.jsp');
		});
		
		$('#navbar-bulletin').click(function(){
			openMenu('/message/BulletinManagerAction!list.action');
		});
		
		$('#navbar-message').click(function(){
			openMenu("/message/MessageAction!managerMessage.action");
		});
		
		$('#navbar-user').click(function() {
			openDialog(rootPath + "/login/settings!queryUserInfo.action", 450, 600);
		});
		
		$('#navbar-menumap').click(function(){
			openDialog(rootPath + '/pages/map.jsp');
		});
		
		if('<%= validUser%>' !== 'true'){
			$('#navbar-buttons').hide();
		} else {
			$('#selectSize').val(getTextSize());
			$('#selectSize').change(function(){
	    		var textSize = $(this).val();
	    		setTextSize(textSize);
	    		if(isActivex()){
					try{
						window.external.setLargeText(textSize == 'large');
						window.external.runAllScript('try{setTextSize("' + textSize + '");}catch(ex){}');
					} catch(ex){
						//忽略错误
					}	
				}
	    		
	    		saveUserConfig('web_text_size', (textSize == 'small') ? '' : textSize);
	    		
	    		$(this).blur();
	    	});
			
			$('#selectTheme').val(getCustomTheme());
			$('#selectTheme').change(function(){
	    		var themeName = $(this).val();
	    		setCustomTheme(themeName);
	    		if(isActivex()){
					try{
						window.external.runAllScript('try{setCustomTheme("' + themeName + '");}catch(ex){}');
					} catch(ex){
						//忽略错误
					}	
				}
	    		
	    		saveUserConfig('web_theme_name', themeName != 'blue' ? themeName : '');
	    		
	    		$(this).blur();
	    	});
		}
	});
	
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
</script>
</body>
</powersi:html>