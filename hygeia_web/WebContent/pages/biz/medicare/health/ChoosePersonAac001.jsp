<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="选择参保人信息" target="_self" />
</head>
<body>
	<powersi:form id="choosePersonAac001Form" namespace="/medicare"
		action="ExaminationAction!choosePersonAac001.action">
		<powersi:panelbox key="查询条件" cssStyle="display:none;">
			<powersi:hidden id="indi_id" name="setMealDTO.indi_id" />
			<powersi:hidden id="idcard" name="setMealDTO.idcard" />
			<powersi:hidden id="insr_code" name="setMealDTO.insr_code" />
			<powersi:hidden id="persontype" name="setMealDTO.persontype" value="1"/>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="查询结果">
		<powersi:datagrid id="row" formId="choosePersonAac001Form"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="choosePersonAac001" pageSize="30">
			<powersi:datagrid-column name="aac003" display="姓名" width="10%" />
			<powersi:datagrid-column name="aac001" display="电脑号" width="25%" />
			<powersi:datagrid-column name="aac002" display="社会保障号码" width="20%" />
			<powersi:datagrid-column name="aab001" display="单位编码" width="10%" />
			<powersi:datagrid-column name="aab069_name" display="单位名称" width="35%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		function choosePersonAac001(rowdata, rowid, rowobj) {
			var personAac001 = new Object();
			personAac001.aac001 = rowdata['aac001'];
			if (personAac001) {
				setDialogReturn(personAac001);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>