<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="费用套餐管理" />
</head>
<body>
	<powersi:form id="queryFeeSetMealIndexForm" namespace="/medicare" 
		action="HospManageAction!feeSetMealIndex.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit"   key="button_query" />
				<powersi:button id="btnAdd" 	label="新增" onclick="doAdd()"/>
				<powersi:button id="btnEnable"  label="修改" onclick="doEdit()"/>
				<powersi:button id="btnDel" 	label="启用" onclick="doDel(1)"/>
				<powersi:button id="btnDel" 	label="停用" onclick="doDel(0)"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,10%,8%,10%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkh059" name="bkh059" label="套餐名称" />
					<powersi:codeselect id="aae100" key="aae100" name="aae100"
						codeType="aae100" value="1" headerKey="" headerValue="全部" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="业务列表">
		<powersi:datagrid id="grid" formId="queryFeeSetMealIndexForm" delayLoad="false" showReload="false" 
			enabledSort="false" alternatingRow="true" isMultiSelect="true" checkbox="true" 
			onDblClickRow="dbSelectRow">
			<powersi:datagrid-column name="bkh059" display="套餐名称" width="150" />
			<powersi:datagrid-column name="aaa027" display="中心编码" width="120" />
			<powersi:datagrid-column name="aka130" display="业务类别" code="aka130" width="80" />
			<powersi:datagrid-column name="aka020" display="拼音助记码" width="80" />
			<powersi:datagrid-column name="aka021" display="五笔助记码" width="80" />
			<powersi:datagrid-column name="bke206" display="修改人" width="80" />
			<powersi:datagrid-column name="bke204" display="操作时间" width="80" format="dateFmt:'yyyy-MM-dd'" />
			<powersi:datagrid-column name="akb020" display="医院编号" width="80" />
			<powersi:datagrid-column name="aae100" display="状态" render="renderAae100" width="150" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#querystring").focus();
		});

		function itemClick(item) {
			var itemname = item['id'];
			if (itemname == 'add_btn') {
				popupAlert('add');
				doAdd();
			} else if (itemname == 'edit_btn') {
				doEdit();
			} else if (itemname == 'cancel') {
				cancel();
			} else if (itemname == 'import') {
				upload();
			} else if (itemname == 'export') {
				download();
			}
		}

		function dbSelectRow(rowdata, rowid, rowobj) {
			grid.selectRow(rowid, "select");
			doEdit();
		}

		function doAdd() {
			openDialogWithParam(
					"${rootPath}/medicare/HospManageAction!feeSetMealDetail.action",
					"param1", 700, 1000, function(ret) {
						if (ret) {
							if (ret == "1") {
								grid.reload();
							}
						}
					});
		}

		function doEdit() {
			var data = grid.getData();
			data = powersi.tostring(data);
			if (powersi.isempty(data)) {
				popupAlert("没有数据！");
				return;
			}

			var rows = grid.getSelectedRows();
			if (rows.length != 1) {
				popupAlert('请选择一行记录修改!');
				return;
			}
			var data = rows[0];

			openDialogWithParam(
					"${rootPath}/medicare/HospManageAction!feeSetMealDetail.action?bkh015="
							+ data.bkh015, "param1", 700, 1000, function(ret) {
						if (ret) {
							if (ret == "1") {
								grid.reload();
							}
						}
					});

		}

		function doDel(type) {

			var data = grid.getData();
			data = powersi.tostring(data);
			if (powersi.isempty(data)) {
				popupAlert("没有数据！");
				return;
			}

			var rows = grid.getSelectedRows();
			if (rows.length < 1) {
				popupAlert('请选择需要操作的记录!');
				return;
			}

			var bkh015sT = [];
			$.each(rows, function(i, row) {
				bkh015sT.push(row['bkh015']);
			});

			var bkh015s = bkh015sT.join(',');

			postJSON(
					"${rootPath}/medicare/HospManageAction!feeSetMealDetailDel.action?bkh015s="
							+ bkh015s + "&type=" + type, "", function(json) {
						if (!checkJSONResult(json)) {
							return;
						}

						if (json.errortype == 0) {

							var len = powersi.length(json.data);
							if (len < 0) {
								popupAlert("失败=" + json.data.message);
								return;
							}
							var suss = json.data.suss;
							if (suss == 1) {
								popupAlert("操作成功", 5000);
								grid.reload();
							} else {
								popupAlert(json.data.message);
							}

						} else {
							var mes = json.message;
							popupAlert(mes);
						}
					});
		}

		function renderAae100(row, index, value) {
			if (value == "0") 
				return "无效";
			 else 
				return "有效";
		}
	</script>
</body>
</powersi:html>