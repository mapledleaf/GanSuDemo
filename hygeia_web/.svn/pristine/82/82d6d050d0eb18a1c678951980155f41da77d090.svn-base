<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.util.DBFunc, com.powersi.hygeia.framework.util.UtilFunc"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=yes">
<title>活动数据库连接</title>
<style type="text/css">
html, body{font-family:arial,sans-serif;font-size:14px;line-height:1.5;background:#fff;}
li{margin-bottom: 20px;}
ol{font-size: 16px;}
li > a{font-size: 16px; text-decoration: none;}
li > div{margin-left: 15px; font-size: 14px;}
</style>
</head>
<body>
<%
java.sql.Connection conn = null;
try{
	try{
		conn = DBFunc.getConnection();
	} catch(java.lang.Throwable ex){
		out.println(ex);
	}
	
	java.util.Set<String> set = DBFunc.getActiveConnections();
	out.println("<h2>Active Connections(");
	out.println(set.size());
	out.println(")</h2>");
	
	out.println("<ol>");
	int i = 0;
	for(String name : set){
		out.println("<li>");
		int idx = name.indexOf("):");
		if(idx >= 0){
			out.println("<a href=\"javascript:toggleDetail('d" + i + "')\">");
			out.println(name.substring(0, idx + 1));
			out.println("</a>");
			
			out.println("<div id=\"d" + i + "\" style=\"display:none\">");
			out.println(UtilFunc.replace(name.substring(idx + 2).trim(), "\n", "<br/>"));
			out.println("</div>");
		} else{
			out.println("<div>");
			out.println(UtilFunc.replace(name, "\n", "<br/>"));
			out.println("</div>");
		}
		
		out.println("</li>");
		
		i ++;
	}
	out.println("</ol>");
} finally{
	DBFunc.closeConnection(conn);
}
%>
<script type="text/javascript">
function toggleDetail(id){
	var obj = document.getElementById(id);
	if(obj){
		if(obj.style.display == 'none'){
			obj.style.display = '';
		} else{
			obj.style.display = 'none';
		}
	}
}
</script>
</body>
</html>