<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	String key = request.getParameter("key");
	if(key == null){
		key = "demo";
	}
%>
<powersi:html>
<head>
<powersi:head title="网络流程查看器(demo)" />
<%@include file="NetworkFlowInclude.jsp" %>
</head>
<body>
	<div class="divButton">
		<powersi:button type="submit" id="btCheck" value="检 测" onclick="doCheckNetwork()"  key="button_refresh" />
		<powersi:button type="button" id="btReset" value="重 置" onclick="doResetFlow()" key="button_clean" />
	</div>
	<div id="myflow"></div>
<powersi:errors />
<script type="text/javascript">
	$(function() {
		var key = "<%=key%>";
		var data = flowData[key];
		if(!data){
			data = {};
		} 
		
		$('#myflow').myflow($.extend(true,{
			top: 30,
			restore : data,
			editable: false,
			click: clickPoint
		}));
	});
	
	function clickPoint(props, node){
		if(!props || !props["text"] || !props["url"]) return;
		
		if(props["disabled"]) return;
		if(powersi.length(props["url"]["value"]) == 0) return;
		//alert(powersi.tostring(props["url"]["value"]));
		alert(node.toJson());
	}
	
	function doCheckNetwork() {
		checkNetwork();
	}
	
	function doResetFlow() {
		setFlowState(0);
	}
</script>
</body>
</powersi:html>