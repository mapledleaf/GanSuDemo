<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="医院病区管理" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="TestAction!queryTest.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aka120" name="testDTO.aka120"
						label="编码" />
					<powersi:textfield id="aka121" name="testDTO.aka121"
						label="名称" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="科室列表"
		allowCollapse="false">
		<a>双击单条查询病区信息。</a>
			<powersi:datagrid id="deptid" formId="queryForm" delayLoad="true" enabledSort="false"
				showReload="false" >
				<powersi:datagrid-column name="aka120" label="编码" />
				<powersi:datagrid-column name="aka121" label="名称" />
			</powersi:datagrid>
	</powersi:panelbox>


	<powersi:errors />

	<script type="text/javascript">

	
	</script>
</body>
</powersi:html>