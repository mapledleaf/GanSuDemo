<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@page
	import="com.powersi.hygeia.web.useragent.*,com.powersi.hygeia.web.util.WebHelper,com.powersi.hygeia.framework.util.UtilFunc"%>
<%
	UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

	StringBuilder sb = new StringBuilder();
	
	sb.append("<p>浏览器名称: ").append(userAgent.getBrowser().getName()).append("</p>");
	sb.append("<p>浏览器标识: ").append(userAgent.getBrowser()).append("</p>");
	sb.append("<p>浏览器版本: ").append(userAgent.getBrowserVersion() == null ? "" : userAgent.getBrowserVersion()).append("</p>");
	/*BrowserType:WEB_BROWSER MOBILE_BROWSER TEXT_BROWSER EMAIL_CLIENT ROBOT TOOL APP UNKNOWN*/
	sb.append("<p>浏览器类型: ").append(userAgent.getBrowser().getBrowserType()).append("</p>");
	sb.append("<p>浏览器厂商: ").append(userAgent.getBrowser().getManufacturer()).append("</p>");
	/*RenderingEngine:TRIDENT WORD GECKO WEBKIT PRESTO MOZILLA KHTML OTHER*/
	sb.append("<p>浏览器引擎: ").append(userAgent.getBrowser().getRenderingEngine()).append("</p>");
	sb.append("<p>浏览器组: ").append(userAgent.getBrowser().getGroup()).append("</p>");
	
	String browserinfo = sb.toString();
	sb.delete(0, sb.length());

	sb.append("<p>操作系统标识: ").append(userAgent.getOperatingSystem()).append("</p>");
	sb.append("<p>操作系统厂商: ").append(userAgent.getOperatingSystem().getManufacturer()).append("</p>");
	/*DeviceType:COMPUTER  MOBILE TABLET GAME_CONSOLE DMR UNKNOWN*/
	sb.append("<p>操作系统设备: ").append(userAgent.getOperatingSystem().getDeviceType()).append("</p>");
	sb.append("<p>操作系统组: ").append(userAgent.getOperatingSystem().getGroup()).append("</p>");
	
	String osinfo = sb.toString();
	sb.delete(0, sb.length());

	sb.append("<p>是否移动设备: ").append(userAgent.getOperatingSystem().isMobileDevice()).append("</p>");
	sb.append("<p>是否ie: ").append(Browser.IE.equals(userAgent.getBrowser().getGroup())).append("</p>");
	sb.append("<p>是否win: ").append(OperatingSystem.WINDOWS.equals(userAgent.getOperatingSystem().getGroup())).append("</p>");
	sb.append("<p>是否ios: ").append(OperatingSystem.IOS.equals(userAgent.getOperatingSystem().getGroup())).append("</p>");

	String checkinfo = sb.toString();
	sb.delete(0, sb.length());
	
	String requestInfo = UtilFunc.replace(WebHelper.printRequestHeader(request), "\n", "<br/>");
	String clientAddress = WebHelper.getRemoteAddr(request);
%>
<powersi:html>
<head>
<powersi:head title="浏览器信息" />
<style type="text/css">
.info{
	word-wrap:break-word;
	width: 100%;
	font-size: 1.1em;
}
</style>
<script type="text/javascript">
function init() {
	var txt = [];
	txt.push("<p>Browser CodeName: " + navigator.appCodeName + "</p>");
	txt.push("<p>Browser Name: " + navigator.appName + "</p>");
	txt.push("<p>Browser Version: " + navigator.appVersion + "</p>");
	txt.push("<p>Cookies Enabled: " + navigator.cookieEnabled + "</p>");
	txt.push("<p>Browser Language: " + (navigator.language || navigator.systemLanguage) + "</p>");
	txt.push("<p>Browser Online: " + navigator.onLine + "</p>");
	txt.push("<p>Platform: " + navigator.platform + "</p>");
	txt.push("<p>User Agent: " + navigator.userAgent + "</p>");

	$("#javascript").html(txt.join(""));
	
	$("#jquery").html("<p>jQuery Browser: " + powersi.tostring($.browser) + "</p>");
	
	if(isActivex()){
		var a = [];
		a.push("<p>IE版本号: " + window.external.getFileVersion("C:\\Program Files\\Internet Explorer\\iexplore.exe") + "</p>");
		a.push("<p>控件版本号: " + window.external.getFileVersion("%this%") + "</p>");
		$("#activex").html(a.join(""));
	}
	
	{
		var a = [];
		a.push("<p>window.screen.availHeight: " + window.screen.availHeight + "</p>");
		a.push("<p>window.screen.availWidth: " + window.screen.availWidth + "</p>");
		a.push("<p>window.screen.colorDepth: " + window.screen.colorDepth + "</p>");
		a.push("<p>window.screen.pixelDepth: " + window.screen.pixelDepth + "</p>");
		a.push("<p>window.screen.height: " + window.screen.height + "</p>");
		a.push("<p>window.screen.width: " + window.screen.width + "</p>");
		a.push("<p>window.orientation: " + window.orientation + "</p>");
		
		$("#screen").html(a.join(""));
	}
	
	{
		var a = [];
		a.push("<p>pageHeight: " + getPageHeight() + "</p>");
		a.push("<p>pageWidth: " + getPageWidth() + "</p>");
		a.push("<p>topHeight: " + getTopWindow().getPageHeight() + "</p>");
		a.push("<p>topWidth: " + getTopWindow().getPageWidth() + "</p>");
		a.push("<p>maxHeight: " + Open_Window_Height + "</p>");
		a.push("<p>maxWidth: " + Open_Window_Width + "</p>");
		a.push("<p>window.height: " + $(window).height() + "</p>");
		a.push("<p>window.width: " + $(window).width() + "</p>");
		a.push("<p>document.height: " + $(document).height() + "</p>");
		a.push("<p>document.width: " + $(document).width() + "</p>");
		a.push("<p>window.scrollTop: " + $(window).scrollTop() + "</p>");
		$("#page").html(a.join(""));
	}
	
	{
		if(powersi.supportTouch){
			var a = [];
			a.push("<p>Touch: " + powersi.supportTouch() + "</p>");
			a.push("<p>Canvas: " + powersi.supportCanvas() + "</p>");
			a.push("<p>SVG: " + powersi.supportSvg() + "</p>");
			a.push("<p>VML: " + powersi.supportVml() + "</p>");
			
			a.push("<p>ApplicationCache: " + powersi.supportApplicationCache() + "</p>");
			a.push("<p>LocalStorage: " + powersi.supportLocalStorage() + "</p>");
			a.push("<p>SssionStorage: " + powersi.supportSssionStorage() + "</p>");
			$("#support").html(a.join(""));
		}
	}
}

$(function(){
	init();
	$(window).resize(init);
});
</script>
</head>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1">
					<i class="icon-desktop red"></i>
					客户端信息
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2">
					<i class="icon-laptop green"></i>
					服务端信息
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3">
					<i class="icon-tablet yellow"></i>
					请求响应信息
				</a>
			</li>
		</ul>
	</div>
	<div class="tab-content">
		<div id="tab-pane1" class="tab-pane active">
			<powersi:panelbox title="javascript" iconClass="icon-html5">
				<div id="javascript" class="info">
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="page" iconClass="icon-file-alt">
				<div id="page" class="info">
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="screen" iconClass="icon-fullscreen">
				<div id="screen" class="info">
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="support" iconClass="icon-ok-circle">
				<div id="support" class="info">
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="css" iconClass="icon-css3">
				<div id="css" class="info">
					<p>
					<!--[if IE]>
						CSS版本: 
					<![endif]-->
					<!--[if IE 6]>
						IE6
					<![endif]-->
					<!--[if IE 7]>
						IE7
					<![endif]-->
					<!--[if IE 8]>
						IE8
					<![endif]-->
					<!--[if IE 9]>
						IE9
					<![endif]-->
					<!--[if gt IE 9]>
						IE9更高级的版本
					<![endif]-->
					<!--[if !IE]>
						非IE浏览器
					<![endif]-->
					</p>
					<script type="text/javascript">
						if(document.documentMode) {
							document.write('<p>文档模式: ' + document.documentMode + '<p/>');	
						}
					</script>
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="jquery" iconClass="icon-rocket">
				<div id="jquery" class="info">
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="activex" iconClass="icon-plane">
				<div id="activex" class="info">
				</div>
			</powersi:panelbox>
		</div>

		<div id="tab-pane2" class="tab-pane">
			<powersi:panelbox title="浏览器" iconClass="icon-laptop">
				<div class="info">
					<%= browserinfo %>
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="操作系统" iconClass="icon-windows">
				<div class="info">
					<%= osinfo %>
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="检查判断" iconClass="icon-check">
				<div class="info">
					<%= checkinfo %>
				</div>
			</powersi:panelbox>
		</div>

		<div id="tab-pane3" class="tab-pane">
			<powersi:panelbox title="请求信息" iconClass=" icon-terminal">
				<div class="info">
					<%=requestInfo %>
					client-address = <%=clientAddress %>
				</div>
			</powersi:panelbox>
			<powersi:panelbox title="响应信息" iconClass=" icon-terminal">
			</powersi:panelbox>
		</div>
	</div>
<powersi:errors />
</body>
</powersi:html>