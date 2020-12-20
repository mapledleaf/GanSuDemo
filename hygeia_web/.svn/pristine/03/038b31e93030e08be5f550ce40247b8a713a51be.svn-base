<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@page import="com.powersi.hygeia.framework.UserInfo"%>
<%@page import="com.powersi.hygeia.framework.BusiContext"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="角色权限查询和导出" />
</head>
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/user" action="UserRightAction!queryRoleList">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:codeselect id="searchUserDtodep" name="searchUserDto.dep" label="所属部门" list="#request.deptList" headerKey="" headerValue="请选择..." />
					<powersi:textfield id="searchUserDtoroleName" name="searchUserDto.roleName" label="角色名" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" title="角色列表">
		<powersi:datagrid id="grid" formId="queryForm" 
			onDblClickRow="gridDblClickRow" checkbox="true" selectRowButtonOnly="true"
			showExportExcel="true" exportFileName="getExportFileName">
			<powersi:toolbar>
				<powersi:toolbar-item id="export" text="导出角色权限列表" icon="export" click="exportInfo" />
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" width="50" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="角色名称" name="role_name" width="30%" minWidth="150" />
			<powersi:datagrid-column display="所属部门" name="dept_name" width="30%" minWidth="150" />
			<powersi:datagrid-column display="角色描述" name="role_desc" width="40%" minWidth="150" />
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
		a.push(' onclick="viewRight(');
		a.push(row['role_id']);
		a.push(')"');
		a.push(" />");
		
		return a.join('');
	}
	
	function getExportFileName(){
		return "角色列表";
	}
	
	function gridDblClickRow(rowdata, rowindex, rowobj) {
		viewRight(rowdata['role_id']);
	}
	
	function viewRight(id){
		popupDialog(rootPath + "/manager/MenuManager!showMenuTree.action?role_id="+id, 550, 500);
	}
	
	function exportInfo(){
		try{
			var sel = grid.getSelectedRows();
			if(powersi.rows_length(sel) == 0){
				alert("请先勾选要导出的记录");
				return;
			}
			
			//cols使用字符串和数组返回的数据有区别
			//字符串 array[value]
			//数组   array[map]
			var rows = powersi.rows_extract(sel, ['role_id']);
			postDownload(rootPath + "/user/UserRightAction!exportRoleInfo.action", {
				'roleIds': powersi.tostring(rows)
			});
		} catch(e){
			alert(e.message);
		}
	}
</script>
</body>
</powersi:html>
