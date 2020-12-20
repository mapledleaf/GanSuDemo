<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%
	String path = request.getContextPath();
	String systemName = com.powersi.hygeia.framework.ParameterMapping
			.getSystemName();
	String orgName = com.powersi.hygeia.framework.ParameterMapping
			.getStringByCode("center_org_name", "");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=systemName%></title>
	<style type="text/css">
body {
	width: 100%;
	text-align: center;
	margin: 0 auto;
	font-family: Tahoma, Verdana, Arial, sans-serif;
	margin: 0 auto;
}
div {
	padding-top: 100px;
}
</style>
</head>
<body>
	<div>
		<h2>
			欢迎使用<%=systemName%></h2>
		<p>
			<%=orgName%>
		</p>
	</div>
</body>
</html>