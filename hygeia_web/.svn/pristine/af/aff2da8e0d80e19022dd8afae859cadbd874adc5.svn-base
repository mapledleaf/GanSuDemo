<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="选择手术治疗方式" target="_self" />
</head>
<body>
	<powersi:form id="chooseOperationForm" namespace="/common"
		action="CommonManagerAction!chooseOperation.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="13%,27%,13%,47%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aka127" name="ka14dto.aka127" label="编码" />
					<powersi:textfield id="aka128" name="ka14dto.aka128" label="名称" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="row" formId="chooseOperationForm" delayLoad="true"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="chooseOperation" pageSize="20">
			<powersi:datagrid-column name="aka127" label="编码" width="40%" />
			<powersi:datagrid-column name="aka128" label="名称" width="60%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#aka127").focus();
		});

		function chooseOperation(rowdata, rowid, rowobj) {
			var operation = new Object();
			operation.aka127 = rowdata['aka127'];
			operation.aka128 = rowdata['aka128'];
			if (operation) {
				setDialogReturn(operation);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>