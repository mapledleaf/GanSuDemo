<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="检查浏览器" />
<script type="text/javascript">
$(document).ready(function(){
	$("#info").text(powersi.tostring($.browser) + " version:" + $.browser.version);
	
	if(isActivex()){
		var a = [];
		a.push("");
		a.push("文件版本号：");
		a.push("IE:" + window.external.getFileVersion("C:\\Program Files\\Internet Explorer\\iexplore.exe"));
		a.push("控件:" + window.external.getFileVersion("%this%"));
		$("#activex").html(a.join("<br />"));
	}
});
</script>
</head>
<body>
<powersi:panelbox title="浏览器信息">
<div id="info">
</div>
<div id="ie">
<!--[if IE]>
CSS版本：
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
<script type="text/javascript">
if(document.documentMode) {
	document.write('<br />IE文档模式：' + document.documentMode + '<br />');	
}
</script>
<div id="activex">
</div>
</div>
</powersi:panelbox>
<powersi:errors />
</body>
</powersi:html>