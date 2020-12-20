<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="选择工伤认定医疗期" target="_self" />
</head>
<body>
	<powersi:form id="chooseMedicalPeriodInjuryForm" namespace="/common"
		action="CommonManagerAction!chooseMedicalPeriodInjury.action">
		<powersi:panelbox key="查询条件" cssStyle="display:none;">
			<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="工伤医疗期列表">
		<powersi:datagrid id="grid" formId="chooseMedicalPeriodInjuryForm"
			showReload="false" checkbox="true" isMultiSelect="false"
			enabledSort="false" onSelectRow="choosePersonMedicalPeriodInjury"
			pageSize="30">
			<powersi:datagrid-column name="aac001" display="电脑号" width="150" />
			<powersi:datagrid-column name="aac003" display="姓名" width="150" />
			<powersi:datagrid-column name="aaz127" display="工伤认定ID" width="150" />
			<powersi:datagrid-column name="blz516" display="医疗期ID" width="150" />
			<powersi:datagrid-column name="alc011" display="认定书编号" width="150" />
			<powersi:datagrid-column name="aae030" display="医疗期开始" width="100" />
			<powersi:datagrid-column name="aae031" display="医疗期结束" width="100" />
			<powersi:datagrid-column name="alc022" display="伤害部位" width="150" />
			<powersi:datagrid-column name="alc020" display="事故发生日期" width="100" />
			<powersi:datagrid-column name="blc586" code="blc586"
				display="劳鉴医疗期类型" width="100" />
			<powersi:datagrid-column name="aab004" display="事故发生单位名称" width="150" />
			<powersi:datagrid-column name="aab001" display="事故发生单位ID" width="150" />
			<powersi:datagrid-column name="alc035" display="劳动能力鉴定书编号"
				width="150" />
			<powersi:datagrid-column name="ala017" code="ala017" display="职业病"
				width="200" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		function choosePersonMedicalPeriodInjury(rowdata, rowid, rowobj) {
			var personAac001 = new Object();
			personAac001.aac001 = rowdata['aac001'];
			personAac001.bka042 = rowdata['aaz127'];//工伤认定ID
			personAac001.bka507 = rowdata['blz516'];//医疗期ID
			if (personAac001) {
				setDialogReturn(personAac001);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>