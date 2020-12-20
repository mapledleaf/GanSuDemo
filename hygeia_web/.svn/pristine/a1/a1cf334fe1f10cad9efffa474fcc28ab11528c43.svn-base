<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	java.util.List data = com.powersi.hygeia.framework.util.DBHelper
			.executeList("select file_name, min(datawindow_name) as dw_name from sys_datawindow where file_name like '%.pbd' group by file_name");
	request.setAttribute("dat", data);
	request.setAttribute("null", new ArrayList());
%>
<powersi:html>
<head>
<powersi:head title="测试下载全部pbd" debug="false" compressed="true" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<table>
		<c:forEach items="${dat}" var="entry" varStatus="status">
			<tr>
				<td><c:out value="${status.count}" />.</td>
				<td><c:out value="${entry.file_name}" /></td>
				<td><c:out value="${entry.dw_name}" /></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:forEach items="${dat}" var="entry" varStatus="status">
			<object id="dw_${status.count}" name="dw_${status.count}"
				height="100" width="100%" codebase=""
				classid="CLSID:CCCC1503-CCCC-1000-8000-080009AC61A9">
				<param name='SourceFileName'
					value='${rootPath }/resource/datawindow/${entry.file_name}' />
				<param name='DataWindowObject' value='${entry.dw_name}' />
				<param name='SuppressEvents' value='0' />
				<param name='VScrollBar' value='1' />
				<param name='HScrollBar' value='1' />
				<param name='HSplitScroll' value='1' />
				<param name='LiveScroll' value='1' />
				<script type="text/javascript">
					try {
						loadDataWindowFail('100%', '200');
					} catch (e) {
					}
				</script>
			</object>
		</c:forEach>

	</div>
	<powersi:errors />
</body>
</powersi:html>