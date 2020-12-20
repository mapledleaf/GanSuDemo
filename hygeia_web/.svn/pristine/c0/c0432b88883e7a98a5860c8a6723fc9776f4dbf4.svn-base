<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@page
	import="com.powersi.hygeia.framework.BusiContext"%>
<%
	String userKind = BusiContext.getUserInfo().getUserKind();
	if("1".equals(userKind)){
		request.setAttribute("showRight", false);
	} else {
		request.setAttribute("showRight", true);
	}
%>
<powersi:html>
<head>
<powersi:head title="查看用户信息" target="_self" />
</head>
<body>
	<powersi:form id="userForm" name="userForm" disabled="true">
		<powersi:editorlayout cols="4">
			<powersi:editorlayout-row>
				<powersi:editorlayout-group>用户基本信息</powersi:editorlayout-group>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="login_user" id="login_user" value="${user.login_user}"
					key="login_user" maxlength="20" readonly="true"></powersi:textfield>
				<powersi:textfield name="user_name" id="user_name" value="${user.user_name}"
					label="用户名" maxlength="30" readonly="true" />
			</powersi:editorlayout-row>
			<s:if test="#request.showRight==true">
			<powersi:editorlayout-row>
				<powersi:codeselect id="grade_id" name="grade_id" value="${user.grade_id}" codeType="grade_id"
					label="用户级别" displayonly="true" cssClass="select2"/>
				<powersi:codeselect name="staff_role" id="staff_role" value="${user.staff_role}" codeType="staff_role"
					label="操作岗位" displayonly="true"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:codeselect name="center_id" id="center_id" value="${user.center_id}" codeType="aaa027"
					label="所属中心" displayonly="true" />
				<powersi:codeselect name="staff_level_name" id="staff_level" value="${user.staff_level}" codeType="staff_level"
					label="操作级别" displayonly="true" />
			</powersi:editorlayout-row>
			</s:if>
			<s:if test="#request.showOrg==true">
			<powersi:editorlayout-row>
				<powersi:textfield name="org_name" id="org_name" value="${user.org_name}"
					label="${labelOrg }" readonly="true" colspan="3" />
			</powersi:editorlayout-row>
			</s:if>
			<powersi:editorlayout-row>
				<powersi:editorlayout-group>用户附加信息</powersi:editorlayout-group>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="postal_code" id="postal_code" value="${user.postal_code}"
					key="postal_code" maxlength="6" validate="zipcode" readonly="false" />
				<powersi:textfield name="address" id="address" key="address" value="${user.address}"
					readonly="false" maxlength="100" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="office_phone" id="office_phone" value="${user.office_phone}"
					key="office_phone" maxlength="20" validate="telephone"
					readonly="false" />
				<powersi:textfield name="fax" id="fax" key="fax" readonly="false" value="${user.fax}"
					maxlength="20" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="mobile_phone" id="mobile_phone" value="${user.mobile_phone}"
					key="mobile_phone" maxlength="11" validate="mobilephone"
					readonly="false" />
				<powersi:textfield name="home_phone" id="home_phone" value="${user.home_phone}"
					key="home_phone" maxlength="20" validate="telephone" readonly="false" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="email" id="email" key="email" value="${user.email}"
					maxlength="100" colspan="3" validate="email" readonly="false" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textarea name="remark" id="remark" maxlength="100" value="${user.remark}"
					rows="3" key="remark" colspan="3" readonly="false" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		<div class="divButton">
			<powersi:button type="submit" id="btSave" key="button_save" onclick="saveInfo()" />
			<powersi:button type="button" id="btPwd" value="修改密码" buttonIcon="icon-key" onclick="chgPwd()" />
			<powersi:button type="button" id="btClose" key="button_close" onclick="closeDlg()" />
		</div>
	</powersi:form>
	<powersi:errors />
<script type="text/javascript">
	function saveInfo() {
		var form = $("#userForm");
		if(!checkForm(form)){
			return;
		}
		
		$(":text[readonly]").prop('disabled', true);
		postAjax(rootPath + '/login/settings!updateUserInfo.action', form2json(form), function(res){
			$(":text[readonly]").prop('disabled', false);
			if(!checkResult(res)){
				return;
			}
			
			popupSuccess('保存用户信息成功');
		});
	}
	
	function closeDlg(){
		closeDialog();
	}
	
	function chgPwd() {
		openDialog(rootPath + "/user/ChangePassword.action?method=open", 420, 430);
	}
</script>
</body>
</powersi:html>