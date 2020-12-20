<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="医生专家" target="_self" />
</head>
<body>
	<powersi:form id="chooseDoctorForm" namespace="/medicare" action="MzchoHospitalBusApplyAction!chooseDoctor.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="20%,80%">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkc275" name="mediSpecZHDto.bkc275" label="按名称" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="row" formId="chooseDoctorForm" delayLoad="true"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="chooseDoctor" pageSize="10">		
			<powersi:datagrid-column name="bkc274" label="专家流水号" width="50%" />
			<powersi:datagrid-column name="bkc275" label="专家名称" width="50%" />
			<powersi:datagrid-column name="bkc406" hide="true" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#aaa000").focus();
		});

		function chooseDoctor(rowdata, rowid, rowobj) {
			var doctor = new Object();
			doctor.bkc275 = rowdata['bkc275'];
			doctor.bkc274 = rowdata['bkc274'];
			doctor.bkc406 = rowdata['bkc406'];
			if (doctor) {
				setDialogReturn(doctor);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>