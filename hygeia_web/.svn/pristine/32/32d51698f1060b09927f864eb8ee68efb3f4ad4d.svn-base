<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="选择异地生育疾病诊断" target="_self" />
</head>
<body>
	<powersi:form id="chooseDiseaseForm" namespace="/common"
				  action="CommonManagerAction!chooseDisease_remote.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="13%,27%,13%,47%">
				<powersi:editorlayout-row>
					<powersi:textfield readonly="true" label="医保中心编号" value="439900" />
					<powersi:textfield readonly="true" label="医保中心名称" value="湖南省省直" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="row" formId="chooseDiseaseForm" delayLoad="true"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="chooseDisease" usePager="false">
			<powersi:datagrid-column name="icd" key="icd" width="40%" label="疾病编码"/>
			<powersi:datagrid-column name="disease" key="disease" width="60%" label="疾病名称" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btSubmit").click();
		});

		function chooseDisease(rowdata, rowid, rowobj) {
			var disease = new Object();
			disease.icd = rowdata['icd'];
			disease.disease = rowdata['disease'];
			if (disease) {
				setDialogReturn(disease);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>