<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>测试100个JSP</title>
<script type="text/javascript">
function open_win1(){
	for(var i = 0; i < 100; i ++) {
		window.open("<%=path%>/pages/sample/checkbrowser.jsp");
	}
}
function open_win2(){
	for(var i = 0; i < 100; i ++) {
		window.open("<%=path%>/pages/sample/testdw.jsp");
	}
}
</script>
</head>
<body>
<input type=button value="打开jsp窗口" onclick="open_win1()">
<input type=button value="打开dw窗口" onclick="open_win2()">
</body>
</html>