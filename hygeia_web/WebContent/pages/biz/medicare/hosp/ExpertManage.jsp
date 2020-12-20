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
<powersi:head title="专家库管理" />
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HospManageAction!queryExpertInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" key="button_query" onclick="search()" />
				<powersi:button id="btAdd" label="新 增" onclick="doAdd()" />
				<powersi:button id="btnEnable" label="修改" onclick="doEdit()" />
				<powersi:button id="btRemove" label="取消" onclick="doRemove()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc275" name="expertInfoDto.bkc275"
						key="bkc275" label="专家姓名" />
					<powersi:codeselect id="aae100" name="expertInfoDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志" />
					<powersi:codeselect id="aae016" name="expertInfoDto.aae016"
						key="aae016" codeType="aae016_expert" label="审核标志" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="expertInfoDto.akb020" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="专家列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			enabledSort="false" showReload="false" checkbox="false"
			onDblClickRow="dbSelectRow" showExportExcel="true"
			showExportExcel2007="true" showExportPdf="true"
			exportFileName="'医院专家信息'">
			<powersi:datagrid-column name="bkc275" key="bkc275" label="专家姓名"
				frozen="true" />
			<powersi:datagrid-column name="bkc274" key="bkc274" label="编号"
				frozen="true" />
			<powersi:datagrid-column name="bkc157" key="bkc157" label="专家所属科室"
				frozen="true" />
			<powersi:datagrid-column name="bkc272" key="bkc272" label="专家职称"
				code="bkc272" />
			<powersi:datagrid-column name="bkc277" key="bkc277" label="专家性别"
				code="aac004" />
			<powersi:datagrid-column name="bkc280" key="bkc280" label="移动电话号码" />
			<powersi:datagrid-column name="bkc279" key="bkc279" label="身份证" />
			<powersi:datagrid-column name="aae100" key="aae100" label="有效标志"
				code="valid_flag" />
			<powersi:datagrid-column name="aae014" key="aae014" label="维护工号" />
			<powersi:datagrid-column name="bae101" key="bae101" label="维护人姓名" />
			<powersi:datagrid-column name="aae015" key="aae015" label="维护时间"
				format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column name="aae016" key="aae016" label="审核标志"
				code="aae016_expert" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">

	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");
			if ($("#akb020").val() == '' || $("#akb020").val() == null) {
				popupAlert("医院编码不能为空，请检查登陆信息！");
				return;
			}
			var hospital_id = $("#akb020").val();
			grid.reload(true);
	}

	function search() {
		$("#queryForm").submit();
	}

	function doRemove() {
		//获取多选，全部勾选的数据
		var row = grid.getSelectedRow();

		if (powersi.isempty(row)) {
			popupAlert("请选择专家信息！");
			return;
		}

		var invalid = false;
		//判断结果集是否为空，为空下面循环取值会抛异常
		if (row['aae016'] == '复审通过') {
			invalid = true;
			grid.select(row);
			popupAlert('复审通过的专家信息不能取消！');
			grid.reload(true);
			return false;
		}

		if (row['aae100'] == '无效') {
			invalid = true;
			grid.select(row);
			popupAlert('无效的专家信息不能取消！');
			grid.reload(true);
			return false;
		}

		if (invalid) {
			return;
		}

		if (!confirm("您确认取消选择的专家信息吗?")) {
			return;
		}

		var akb020 = $("#akb020").val();
		postJSON(
				"${rootPath}/medicare/HospManageAction!expertRemove.action?bkc406="
						+ row['bkc406'], function(json) {
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

	function doAdd() {
		openDialogWithParam(
				"${rootPath}/medicare/HospManageAction!expertDetail.action?flag=1",
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
			alert("没有数据！");
			return;
		}
		var row = grid.getSelectedRow();
		if (row['aae100'] == '无效') {
			alert('无效的专家信息不能修改！');
			return;
		}
		openDialogWithParam(
				"${rootPath}/medicare/HospManageAction!expertDetail.action?flag=1&bkc406="
						+ row['bkc406'], "param1", 700, 1000, function(ret) {
					if (ret) {
						if (ret == "1") {
							grid.reload();
						}
					}
				});
	}

	function dbSelectRow(rowdata, rowid, rowobj) {
		grid.selectRow(rowid, "select");
		doEdit();
	}
</script>
