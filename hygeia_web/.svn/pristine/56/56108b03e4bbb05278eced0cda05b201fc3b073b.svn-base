<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute("userKindList", new String[]{"2", "3", "9"});
%>
<powersi:html>
<head>
<powersi:head title="用户通讯录" />
</head>
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/user" action="queryAddressList">
			<powersi:editorlayout cols="10%,20%,10%,15%,10%,15%,20%">
				<powersi:editorlayout-row>
					<powersi:checkboxlist name="user_kind" key="user_kind" codeType="user_kind" required="true" value="#request.userKindList" />
					<powersi:textfield name="org_name" id="org_name" key="org_name" />
					<powersi:textfield name="user_name" id="user_name" key="user_name" />
					<powersi:editorlayout-button>
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="用户列表">
		<powersi:datagrid id="grid" formId="queryForm" enabledExportExcel="true" exportFileName="'用户通讯录'">
			<powersi:datagrid-column key="user_name" name="user_name" width="100" frozen="true" />
			<powersi:datagrid-column display="机构名" name="org_name" width="150" frozen="true" />
			<powersi:datagrid-column display="移动电话" name="mobile_phone" width="100" />
			<powersi:datagrid-column display="办公电话" name="office_phone" width="100" />
			<powersi:datagrid-column display="传真电话" name="fax" width="120" />
			<powersi:datagrid-column display="邮箱地址" name="email" width="150" />
			<powersi:datagrid-column display="邮政编码" name="postal_code" width="80" />
			<powersi:datagrid-column display="通讯地址" name="address" width="100%" minWidth="150"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
</script>
</body>
</powersi:html>