<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="系统应用管理" />
<style type="text/css">
#grid .l-grid-row-cell-inner {
	line-height: 36px;
}
</style>
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="false">
		<powersi:form id="queryForm" namespace="/manager"
			action="AppManager!query">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="app_code" name="app_code" label="应用代码" />
					<powersi:textfield id="app_name" name="app_name" label="应用名称" />
					<powersi:textfield id="app_vendor" name="app_vendor" label="开发商" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>

	<powersi:panelbox key="panel_result" title="应用列表">
		<powersi:datagrid id="grid" formId="queryForm"
			rowAttrRender="gridRowRender" rowHeight="36" 
			exportFileName="'系统应用'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:toolbar>
				<powersi:toolbar-item id="add" text="新增应用" icon="add"
					click="itemClick" title="新增系统应用" />
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" width="120" frozen="true"
				isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="应用代码" name="app_code" width="100" />
			<powersi:datagrid-column display="应用图标" name="app_ico" width="60"
				isExport="false" render="renderIco" />
			<powersi:datagrid-column display="应用名称" name="app_name" width="100%"
				minWidth="100" />
			<powersi:datagrid-column display="应用标签" name="app_tag" width="100" />
			<powersi:datagrid-column display="开发商" name="app_vendor" width="100" />
			<powersi:datagrid-column display="显示序号" name="dis_order" width="100" />
			<powersi:datagrid-column display="最后修改时间" name="last_date"
				width="150" />
			<powersi:datagrid-column display="有效标志" name="valid_flag" width="60"
				render="renderFlag" />
		</powersi:datagrid>
	</powersi:panelbox>

	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="detailDlg">
			<form id="detailForm">
				<powersi:hidden id="detail_app_id" name="app_id" />
				<powersi:editorlayout cols="4">
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_app_code" name="app_code"
							label="应用代码" required="true" maxlength="20" placeholder="使用首拼或者英文标识"></powersi:textfield>
						<powersi:textfield id="detail_dis_order" name="dis_order"
							label="显示序号" required="true"
							validate="integer,min[0],max[999999]" maxlength="6" min="0"
							max="999999" mouseWheel="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_app_name" name="app_name"
							label="应用名称" colspan="3" required="true" maxlength="50"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea id="detail_app_desc" name="app_desc"
							label="应用描述" colspan="3" maxlength="500" rows="5"></powersi:textarea>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_app_ico" name="app_ico"
							label="图标URL" colspan="3" required="true" maxlength="500"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_app_url" name="app_url"
							label="应用URL" colspan="3" required="true" maxlength="500"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_app_feature" name="app_feature"
							label="窗口特性" colspan="3" maxlength="500"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_app_tag" name="app_tag" label="应用标签"
							maxlength="20"></powersi:textfield>
						<powersi:textfield id="detail_app_vendor" name="app_vendor"
							label="开发商" maxlength="20"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:radio id="detail_desktop_flag" name="desktop_flag"
							label="桌面" codeType="show_flag" required="true"></powersi:radio>
						<powersi:radio id="detail_taskbar_flag" name="menubar_flag"
							label="菜单栏" codeType="show_flag" required="true"></powersi:radio>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:codeselect id="detail_app_grade" name="app_grade"
							label="应用等级" codeType="grade_id" required="true"
							cssClass="select2"></powersi:codeselect>
						<powersi:radio id="detail_valid_flag" name="valid_flag"
							label="有效标志" codeType="valid_flag" required="true"></powersi:radio>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<div class="space-y"></div>
				<div class="divButton">
					<powersi:button id="btnDetailOk" key="button_save" onclick="save()" />
					<powersi:button id="btnDtailClose" key="button_close"
						onclick="closeDlg()" />
				</div>
			</form>
		</div>
		<div id="roleDlg">
			<div id="divRoleGrant">
				<powersi:datagrid id="gridRole" usePager="false" checkbox="true"
					frozen="false" selectRowButtonOnly="false" height="400"
					groupColumnName="dept_id" groupCheckbox="true"
					groupRender="gridGroupRender">
					<powersi:datagrid-column display="角色名称" name="role_name"
						width="30%" minWidth="100" />
					<powersi:datagrid-column display="角色描述" name="role_desc"
						width="30%" minWidth="100" />
					<powersi:datagrid-column display="所属部门" name="dept_name"
						width="40%" minWidth="150" />
				</powersi:datagrid>
			</div>
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btShowRole" key="button_view"
					onclick="showRoleGrant()" value="查看已选择角色" />
				<powersi:button id="btRoleGrant" key="button_save"
					onclick="saveRole()" />
				<powersi:button id="btnRoleClose" key="button_close"
					onclick="closeRoleDlg()" />
			</div>
		</div>
	</div>

	<powersi:errors />
	<script type="text/javascript">
		function gridRowRender(rowdata, rowid) {
			if (rowdata['valid_flag'] == '0') {
				return getColorStyle('error');
			}
		}

		function renderOperate(row, index, value) {
			var a = [];

			a.push('<input type="button" value="编辑" class="linkButton"');
			a.push(' onclick="editRow(');
			a.push(index);
			a.push(')"');
			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="授权" class="linkButton"');
			a.push(' onclick="roleRow(');
			a.push(index);
			a.push(')"');

			if (row['app_grade'] == '0') {
				a.push(' disabled="disabled" title="所有用户可以操作，无需授权"');
			}

			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="删除" class="linkButton"');
			a.push(' onclick="deleteRow(');
			a.push(index);
			a.push(')"');
			a.push(" />");

			return a.join('');
		}

		function renderFlag(rowdata, index, value) {
			if (value === '0') {
				return '<span title="无效"><i class="icon-remove textError"></i></span>';
			} else {
				return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
			}
		}

		function renderIco(rowdata, index, value) {
			return '<img src="' + rootPath + '/resource/desktop/images/appico/' + value + '" style="width: 32px; height: 32px;"></img>';
		}

		function itemClick(item) {
			var itemname = item['id'];
			if (itemname == 'add') {
				addRow();
			}
		}

		var dlg = null;
		function addRow() {
			clearForm('#detailForm');
			json2form('#detailForm', {
				'app_id' : '',
				'dis_order' : '1000',
				'desktop_flag' : '1',
				'menubar_flag' : '1',
				'app_grade' : '1',
				'valid_flag' : '1'
			});

			if (dlg) {
				dlg.show();
			} else {
				dlg = popupDiv("#detailDlg", '', 600);
			}
			dlg.set('title', '新增应用');
		}

		function closeDlg() {
			if (dlg) {
				dlg.hide();
			}
		}

		function save() {
			if (!checkForm('#detailForm')) {
				return;
			}

			var data = form2json('#detailForm');
			postAjax(rootPath + '/manager/AppManager!save.action', data,
					function(res) {
						if (!checkResult(res)) {
							return;
						}

						alert(res.message);

						data['last_date'] = res.data['last_date'];
						if (powersi.length(data['app_id']) == 0) {
							data['app_id'] = res.data['app_id'];
							grid.addRow(data);
						} else {
							grid.updateRow(grid.getSelectedRow(), data);
						}

						closeDlg();
					});
		}

		function editRow(index) {
			var row = grid.getRow(index);
			if (row) {
				grid.select(row);

				clearForm('#detailForm');
				json2form('#detailForm', row);

				if (dlg) {
					dlg.show();
				} else {
					dlg = popupDiv("#detailDlg", '', 600);
				}
				dlg.set('title', '编辑应用');
			}
		}

		function deleteRow(index) {
			var row = grid.getRow(index);
			if (row) {
				if (!confirm('您确认删除' + row['app_name'] + '[' + row['app_code']
						+ ']吗？')) {
					return;
				}

				postAjax(rootPath + '/manager/AppManager!delete.action', grid
						.formatRow(row), function(res) {
					if (!checkResult(res)) {
						return;
					}

					alert(res.message);

					grid.deleteRow(row);
				});
			}
		}

		function gridGroupRender(groupvalue, groupdata, groupdisplay) {
			try {
				var deptName = groupdata[0]['dept_name'] || '';
				if (deptName.length == 0) {
					deptName = '其他';
				}
				return deptName + '[' + groupdata.length + ']';
			} catch (e) {
				alert(e.message);
			}
		}

		var roleDlg = null;
		function closeRoleDlg() {
			if (roleDlg) {
				roleDlg.hide();
			}
		}

		var selectedRoles = [];
		function roleRow(index) {
			var row = grid.getRow(index);
			if (row) {
				var roles = postSync(rootPath
						+ '/manager/AppManager!getRoleList.action', {
					'app_id' : row['app_id']
				});
				if (!checkResult(roles)) {
					return;
				}

				if (roleDlg) {
					gridRole.selectRow(false);

					roleDlg.show();
				} else {
					var allRole = postSync(rootPath
							+ '/manager/AppManager!getAllRole.action');
					if (!checkResult(allRole)) {
						return;
					}
					gridRole.setData(allRole.data);
					gridRole.loadData();

					roleDlg = popupDiv("#roleDlg", '角色授权', 600);
				}

				gridRole.refreshSize();

				var rows = gridRole.getRows();
				$.each(rows, function(i, r) {
					var roleId = r['role_id'];
					$.each(roles.data, function(j, role) {
						if (role['role_id'] == roleId) {
							gridRole.select(r);
							return false;
						}
					});
				});

				grid.select(row);
				selectedRoles = powersi.rows_extract(
						gridRole.getSelectedRows(), 'role_id');
			}
		}

		function showRoleGrant() {
			var rows = gridRole.getSelectedRows();
			if (powersi.rows_length(rows) == 0) {
				alert("没有选择");
			} else {
				alert(powersi.rows_extract(rows, 'role_name').join("\r\n"));
			}
		}

		function saveRole() {
			var roles = powersi.rows_extract(gridRole.getSelectedRows(),
					'role_id');
			var deletes = [], adds = [];
			$.each(selectedRoles, function(i, role) {
				if (powersi.inarray(role, roles) < 0) {
					deletes.push(role);
				}
			});
			$.each(roles, function(i, role) {
				if (powersi.inarray(role, selectedRoles) < 0) {
					adds.push(role);
				}
			});
			if (deletes.length == 0 && adds.length == 0) {
				alert('没有修改，无需保存');
				return;
			}

			var data = {
				'app_id' : grid.getSelectedRow()['app_id'],
				'delete' : deletes.join(','),
				'insert' : adds.join(',')
			};

			postAjax(rootPath + '/manager/AppManager!saveRoleList.action',
					data, function(res) {
						if (!checkResult(res)) {
							return;
						}

						selectedRoles = roles;

						alert(res.message);

						closeRoleDlg();
					});
		}
	</script>
</body>
</powersi:html>