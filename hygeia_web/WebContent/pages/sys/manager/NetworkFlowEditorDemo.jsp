<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String key = request.getParameter("key");
	if(key == null){
		key = "demo";
	}
%>
<powersi:html>
<head>
<powersi:head title="网络流程编辑器(demo)" />
</head>
<body>
<%@include file="NetworkFlowEditorTool.jsp" %>
<script type="text/javascript">
var flowState = 0;
$(function() {
	var key = "<%=key%>";
	var data = flowData[key];
	if(!data){
		data = {};
	}
	
	$('#myflow').myflow({
		center: false,
		left: left,
		top: top,
		restore : data,
		tools : {
			save : {
				onclick : function(data) {$('#myflow_data_textarea').val(data);}
			}
		}
	});
	
	$('#myflow_data_textarea').val(getFlowData());
	
	$('#myflow_test1').click(function(){
		flowState ++;
		setFlowState(flowState);
	});
	
	$('#myflow_test2').click(function(){
		checkNetwork();
	});
});
</script>
</body>
</powersi:html>