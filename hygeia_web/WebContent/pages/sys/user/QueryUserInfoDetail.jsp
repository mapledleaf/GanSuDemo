<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<powersi:head title="查看用户信息" />
</head>
<body>
	<powersi:editorlayout cols="4">
		<powersi:editorlayout-head title="用户基本信息" />
		<powersi:editorlayout-row>
			<powersi:textfield name="user.login_user" id="login_user"
				key="login_user" maxlength="20" readonly="true"></powersi:textfield>
			<powersi:textfield name="user.staff_name" id="staff_name"
				key="staff_name" maxlength="30" readonly="true" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield name="user.dis_order" id="dis_order"
				key="dis_order" maxlength="10" readonly="true" />
			<powersi:textfield name="user.staff_sta_name" id="staff_sta_name"
				key="staff_sta" readonly="true" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="grade_name" name="user.grade_name"
				label="用户级别" readonly="true" />
			<powersi:textfield name="user.staff_role_name" id="staff_role_name"
				label="操作岗位" readonly="true"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield name="user.center_name" id="center_name" label="所属中心"
				readonly="true" />
			<powersi:textfield name="user.staff_level_name" id="staff_level_name"
				label="操作级别" readonly="true" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:hidden name="user.dept_id" id="dept_id" />
			<powersi:textfield name="user.dept_name" id="dept_name" key="dept_id"
				readonly="true" colspan="3" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield name="user.postal_code" id="postal_code"
				key="postal_code" maxlength="6" validate="zipcode" readonly="true" />
			<powersi:textfield name="user.address" id="address" key="address"
				readonly="true" maxlength="100" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield name="user.office_phone" id="office_phone"
				key="office_phone" maxlength="20" validate="telephone"
				readonly="true" />
			<powersi:textfield name="user.fax" id="fax" key="fax" readonly="true"
				maxlength="20" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield name="user.mobile_phone" id="mobile_phone"
				key="mobile_phone" maxlength="11" validate="mobilephone"
				readonly="true" />
			<powersi:textfield name="user.home_phone" id="home_phone"
				key="home_phone" maxlength="20" validate="telephone" readonly="true" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield name="user.email" id="email" key="email"
				maxlength="100" colspan="3" validate="email" readonly="true" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textarea name="user.remark" id="remark" maxlength="100"
				rows="3" key="remark" colspan="3" readonly="true" />
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<div class="divButton">
		<powersi:button type="button" id="btExport" key="button_export" onclick="exportInfo()" />
		<powersi:button type="button" id="btClose" key="button_close" onclick="closeDlg()" />
	</div>
	<powersi:errors />
<script type="text/javascript">
	function exportInfo() {
		postDownload(rootPath + "/user/UserInfoAction!exportUserInfoXls.action", {
			'staff_id': "${staff_id}"
		});
	}
	
	function closeDlg(){
		closeDialog();
	}
</script>
</body>
</powersi:html>