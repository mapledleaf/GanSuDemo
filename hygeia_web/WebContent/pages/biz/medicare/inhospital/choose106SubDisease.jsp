<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="选择疾病诊断" target="_self" />
</head>
<body>
	<powersi:form id="chooseDiseaseForm" namespace="/inhospital"
		action="InhospitalManagerAction!choose106SubDisease.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:hidden id="aka035" name="ka06dto.aka035" />
				<powersi:hidden id="aka120106" name="aka120106" value="" />
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="13%,27%,13%,47%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aka120" name="ka06dto.aka120" key="aka120" />
					<powersi:textfield id="aka121" name="ka06dto.aka121" key="aka121" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aka020" name="ka06dto.aka020" key="aka020" />
					<powersi:textfield id="aka021" name="ka06dto.aka021" key="aka021" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="row" formId="chooseDiseaseForm" delayLoad="true"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="chooseDisease" pageSize="20">
			<powersi:datagrid-column name="aka120" key="aka120" width="40%" />
			<powersi:datagrid-column name="aka121" key="aka121" width="60%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#aka120106").val($("#aka120").val());
			$("#aka120").val("");
			$("#aka020").focus();
		});

		function chooseDisease(rowdata, rowid, rowobj) {
			var disease = new Object();
			disease.aaz164 = rowdata['aaz164'];
			disease.aka120 = rowdata['aka120'];
			disease.aka121 = rowdata['aka121'];
			if (disease) {
				setDialogReturn(disease);
			}
			setTimeout("closeDialog();", 500);
		}
		
	</script>
</body>
</powersi:html>