<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String path = request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<script type="text/javascript">

	window.onload = function() {
		$("#aka020").focus();
	}

	function getDiseaseInfo(rowdata,rowid,rowobj) {
		
		var dise = new Object();
		dise.aaz164 = rowdata['aaz164'];
		dise.aka120 = rowdata['aka120'];
		dise.aka121 = rowdata['aka121'];
		dise.aaz149 = rowdata['aaz149'];
		
		if(dise){
			setDialogReturn(dise);
		}
		
		setTimeout("closeDialog();", 500);

	}
</script>
<powersi:head title="疾病查询" />
<powersi:panelbox key="panel_result" title="查询条件">
<powersi:form name="mainForm" action="MedOrgComQueryAction!queMedOrgInfo.action" namespace="/medicarebiz">
		<powersi:editorlayout cols="4">
			<powersi:textfield id="aka120" name="diseaseDirDTO.aka120"
				key="aka120" />
			<powersi:textfield id="aka121" name="diseaseDirDTO.aka121"
				key="aka121" />
			<powersi:hidden id="aka035" name="diseaseDirDTO.aka035" key="aka035" />
			<tr>
				<powersi:textfield id="aka020" name="diseaseDirDTO.aka020"
					key="aka020" />
				<powersi:textfield id="aka021" name="diseaseDirDTO.aka021"
					key="aka021" />
			</tr>
			<tr>
				<powersi:buttons cols="4">
					<powersi:submit id="btSubmit" key="button_query" />
				</powersi:buttons>
			</tr>
		</powersi:editorlayout>
	</powersi:form>
</powersi:panelbox>
<powersi:panelbox key="panel_result" title="查询结果">
	<powersi:datagrid id="row" formId="mainForm" delayLoad="true" showReload="false" pageSize="10" isMultiSelect="false"
		checkbox="true" isScroll="true" alternatingRow="true" enabledSort="false"  onSelectRow="getDiseaseInfo" >
		<powersi:datagrid-column name="aka120" key="aka120" label="疾病编码"
			frozen="true" />
		<powersi:datagrid-column name="aka121" key="aka121" label="疾病名称" width="100%"
			frozen="true" />
	</powersi:datagrid>
</powersi:panelbox>
<%-- <div class="div_center" style="display: none">
	<powersi:button cssClass="button" key="button_ok"
		onclick="getDiseaseInfo();" />
	<powersi:button cssClass="button" key="button_cancel"
		onclick="window.close();" />
</div>
<div id="div1" style="display: none">
	<powersi:textfield id="aka122" name="diseaseDirDTO.aka122" key="aka122" />
	<!-- 有效状态，默认为 1-->
</div> --%>
<powersi:errors />
</html>