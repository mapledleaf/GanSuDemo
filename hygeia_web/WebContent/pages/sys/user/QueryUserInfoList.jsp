<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@page import="com.powersi.hygeia.framework.UserInfo"%>
<%@page import="com.powersi.hygeia.framework.BusiContext"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	UserInfo user = BusiContext.getUserInfo();
	String centerFilter = "1 = 0";
	String staffLevel = UtilFunc.getString(user, "staff_level", "9");
	if(user.isSuperUser() || staffLevel.equals("1")){
		centerFilter = "";
	} else {
		if(staffLevel.equals("2")){
			centerFilter = "aaa027 in (" + UtilFunc.getString(user, "center_list", "0") + ")";	
		} else {
			centerFilter = "aaa027 = " + UtilFunc.getString(user, "center_id", "0");
		}
	}
	request.setAttribute("staffLevel", staffLevel);
	String centerRequired = "false";
%>
<powersi:html>
<head>
<powersi:head title="用户信息查询和导出" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" namespace="/user" action="UserInfoAction!queryList">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:codeselect id="searchUserDtoaaa027" name="searchUserDto.aaa027" label="所属中心"
						codeType="aaa027_list" codeFilter="<%=centerFilter %>" required="<%=centerRequired %>" 
						cssClass="select2" />
					<powersi:codeselect id="searchUserDtodep" name="searchUserDto.dep" label="所属部门" list="#request.deptList" headerKey="" headerValue="请选择..."/>
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="searchUserDtologinUser" name="searchUserDto.loginUser" label="登录名" />
					<powersi:textfield id="searchUserDtouserName" name="searchUserDto.userName" label="用户名" />
					<powersi:codeselect id="searchUserDtostaffLevel" name="searchUserDto.staffLevel" label="操作级别"
						codeType="staff_level" cssClass="select2" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" title="用户列表">
		<powersi:datagrid id="grid" formId="queryForm" 
			onDblClickRow="gridDblClickRow" checkbox="true" selectRowButtonOnly="true"
			showExportExcel="true" exportFileName="getExportFileName">
			<powersi:toolbar>
				<powersi:toolbar-item id="export" text="导出用户信息列表" icon="export" click="exportInfo" />
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" width="50" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column key="login_user" name="login_user" width="100" />
			<powersi:datagrid-column key="staff_name" name="staff_name" width="100" />
			<powersi:datagrid-column display="所属中心" name="center_name" width="50%" minWidth="150" />
			<powersi:datagrid-column display="所属部门" name="dept_name" width="50%" minWidth="150" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	$(function(){
		 //手动初始化部门下拉框
	  	initSelect2('#searchUserDtodep', {
	  		formatSelection: function(state){
	  			return $.trim(state.text);
	  		},
	  		dropdownAutoWidth: true
	  	});
	});
	
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="查看" class="linkButton"');
		a.push(' onclick="viewUserDetail(');
		a.push(row['staff_id']);
		a.push(')"');
		a.push(" />");
		
		return a.join('');
	}
	
	function getExportFileName(){
		return "用户列表";
	}
	
	function gridDblClickRow(rowdata, rowindex, rowobj) {
		viewUserDetail(rowdata['staff_id']);
	}

	function viewUserDetail(staffId){
		 popupDialog(rootPath + "/user/UserInfoAction!queryUserInfo.action?staff_id="+staffId, 480, 600);
	}
  
	function exportInfo(){
		try{
			var sel = grid.getSelectedRows();
			if(powersi.rows_length(sel) == 0){
				alert("请先勾选要导出的记录");
				return;
			}
			
			var rows = powersi.rows_extract(sel, 'staff_id');
			postDownload(rootPath + "/user/UserInfoAction!exportUserInfoXls.action", {
				'staff_id': powersi.tostring(rows)
			});
		} catch(e){
			alert(e.message);
		}
	}
</script>
</body>
</powersi:html>