<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String userKind = request.getParameter("userkind");
	String userKindFilter = "";
	if (userKind != null && userKind.length() > 0) {
		userKindFilter = "user_kind in (" + userKind + ")";
	}
%>
<powersi:html>
<head>
<powersi:head title="重置密码" />
</head>
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/user" action="queryUserList">
			<powersi:editorlayout cols="10%,20%,10%,15%,10%,15%,20%">
				<powersi:editorlayout-row>
<%-- 					<powersi:radio name="userKind" key="user_kind" codeType="user_kind" --%>
<%-- 						codeFilter="<%=userKindFilter%>" codeSort="user_kind desc" --%>
<%-- 						required="true" /> --%>
					<powersi:hidden name="userKind" key="user_kind" value="9"/>
					<powersi:textfield name="loginUser" id="loginUser" key="login_user"
						validate="groupRequired[query]" placeholder="登录名,用户名任填一项" />
					<powersi:textfield name="userName" id="userName" key="user_name"
						validate="groupRequired[query]" placeholder="登录名,用户名任填一项" />
					<powersi:editorlayout-button>
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>

	<powersi:panelbox key="panel_result" title="用户列表">
		<powersi:datagrid id="grid" formId="queryForm"
			rowAttrRender="gridRowRender" delayLoad="true"
			fixedCellHeight="false">
			<powersi:datagrid-column key="operate" width="180" frozen="true"
				isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column key="login_user" name="login_user"
				width="150" frozen="true" />
			<powersi:datagrid-column key="user_name" name="user_name" width="150"
				frozen="true" />
			<powersi:datagrid-column display="密码状态" name="password_flag"
				width="100" render="renderPasswordFlag" />
			<powersi:datagrid-column display="密码错误次数" name="password_errorcount"
				width="100" />
			<powersi:datagrid-column key="lock_state" name="lock_state_name"
				code="lock_state" data="lock_state" width="100"
				render="renderLockState" />
			<powersi:datagrid-column key="lock_reason" name="lock_reason"
				width="100%" minWidth="100" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(function() {
			$(':radio[name="userKind"]:first').prop('checked', true);
		});

		function gridRowRender(rowdata, rowid) {
			if (rowdata['lock_state'] == '1') {
				return getColorStyle('error');
			}
		}

		function renderOperate(row, index, value) {
			var a = [];
			a
					.push('<input type="button" value="清除错误" class="linkButton" title="清除密码错误次数"');
			a.push(' onclick="clearPasswordError(');
			a.push(index);
			a.push(')"');
			if (row['password_errorcount'] == '0') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="重置密码" class="linkButton"');
			a.push(' onclick="resetPassword(');
			a.push(index);
			a.push(')"');
			if (row['password_flag'] == '0') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="用户解锁" class="linkButton"');
			a.push(' onclick="unlockUser(');
			a.push(index);
			a.push(')"');
			if (row['lock_state'] != '1') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

			return a.join('');
		}

		function renderPasswordFlag(row, index, value) {
			if (row['password_flag'] == '1') {
				return '<span title="密码已修改" class="textError"><i class="icon-key"></i></span>';
			} else {
				return '<span title="密码未修改">&nbsp;</span>';
			}
		}

		function renderLockState(row, index, value) {
			if (row['lock_state'] == '1') {
				return '<span title="已锁定"><i class="icon-lock"></i></span>';
			} else {
				return '<span title="未锁定">&nbsp;</span>';
			}
		}

		function resetPassword(index) {
			var row = grid.getRow(index);
			if (!confirm("您确定要重置用户[" + row['user_name'] + "]的密码吗?")) {
				return;
			}

			postJSON(rootPath + "/user/ResetPassword.action", {
				"userId" : row['user_id'],
				"userKind" : row['user_kind']
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}

				grid.updateRow(index, {
					'password_errorcount' : '0',
					'password_flag' : '0'
				});

				alert(json.message);
			});
		}

		function unlockUser(index) {
			var row = grid.getRow(index);
			if (!confirm("您确定要解锁用户[" + row['user_name'] + "]吗?")) {
				return;
			}

			postJSON(rootPath + "/user/UnlockUser.action", {
				"userId" : row['user_id'],
				"userKind" : row['user_kind']
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}

				grid.updateRow(index, {
					'lock_state' : '0',
					'lock_reason' : ''
				});

				grid.setRowColor(index, powersi.color(''));

				alert(json.message);
			});
		}

		function clearPasswordError(index) {
			var row = grid.getRow(index);
			if (!confirm("您确定要清除用户[" + row["user_name"] + "]的密码错误次数吗?")) {
				return;
			}

			postJSON(rootPath + "/user/ClearPasswordError.action", {
				"userId" : row['user_id'],
				"userKind" : row['user_kind']
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}

				grid.updateRow(index, {
					'password_errorcount' : '0'
				});

				alert(json.message);
			});
		}
	</script>
</body>
</powersi:html>