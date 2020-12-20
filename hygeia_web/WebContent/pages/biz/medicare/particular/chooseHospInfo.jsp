<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<powersi:head title="选择医疗机构信息" target="_self" />
<%
	String path = request.getContextPath();
	String aaa027 = request.getParameter("aaa027");
%>
<powersi:html>

<powersi:form name="mainForm" namespace="/particular"
	action="ParticularManagerAction!queryHospInfo.action">
	<powersi:groupbox title="查询条件">
		<powersi:editorlayout cols="6">
		   <powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout-row>
				<powersi:hidden id="aaa027" name="hospDTO.aaa027" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="akb020" name="hospDTO.akb020" label="机构编码" />
				<powersi:textfield id="aab069" name="hospDTO.aab069" label="机构名称" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:groupbox>
	<powersi:groupbox title="查询结果">
		<powersi:datagrid id="grid" formId="mainForm" delayLoad="true"
			showReload="false" checkbox="true" isMultiSelect="false" 
			enabledSort="false" onSelectRow="chooseHospInfo" pageSize="10">
			<powersi:datagrid-column name="akb020" display="医疗机构编号" />
			<powersi:datagrid-column name="aab069" display="机构名称" />
			<powersi:datagrid-column name="bkc110" display="机构级别" code=""/>
		</powersi:datagrid>
	</powersi:groupbox>
</powersi:form>
<script type="text/javascript">
window.onload = function(){
	$("#aaa027").val("<%=aaa027%>");
}

function chooseHospInfo(rowdata, rowid, rowobj) {
	var hosp = {};
	hosp.akb020 = rowdata["akb020"];//医院编码
	hosp.aab069 = rowdata["aab069"];//医院名称
	hosp.bkc110 = rowdata["bkc110"];//医院级别
	setDialogReturn(hosp);
	setTimeout("closeDialog();", 500);
}
</script>
</powersi:html>
