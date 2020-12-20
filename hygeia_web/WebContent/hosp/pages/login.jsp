<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%
	request.setAttribute("userKind", "3");
	request.setAttribute("loginType", "hosp");
%>
<jsp:forward page="/pages/login.jsp" />