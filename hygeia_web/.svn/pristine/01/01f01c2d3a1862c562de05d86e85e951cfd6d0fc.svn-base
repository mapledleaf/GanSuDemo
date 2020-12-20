<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String key = request.getParameter("key");
	String data = com.powersi.sys.manager.util.MenuFlowUtil.getDataByKey(key);
	if(data == null || data.length() == 0){
		data = "{}";
	} else {
		data = data.trim();
	}
%>
<powersi:html>
<head>
<powersi:head title="菜单流程编辑器" /></head>
<body>
<%@include file="MenuFlowEditorTool.jsp" %>
<script type="text/javascript">
	$(function() {
		var data = <%=data%>;
		
		$('#myflow').myflow({
			center: false,
			left: left,
			top: top,
			restore : data,
			tools : {
				save : {
					onclick : function(json) {$('#myflow_data_textarea').val(json);}
				}
			}
		});
		
		$('#myflow_data_textarea').val(getFlowData());
	});
</script>
</body>
</powersi:html>