<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String Path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
%>
<powersi:html>
<head>
<powersi:head title="实施计划验收申请" />
</head>
<body>

	<powersi:form id="queryForm" namespace="/actualize"
		action="ActualizeManageAction!queryActualize.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" />
				<powersi:button id="btSubmit" label="验收申请表" onclick="report()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkf318" name="kfd6Dto.bkf318" key="bkf318"
						label="计划名称" />
					<powersi:textfield id="bae101" name="kfd6Dto.bae101" key="bae101"
						label="计划建立人" />
					<powersi:codeselect id="aae100" name="kfd6Dto.aae100" key="aae100"
						codeType="plan_flag" label="计划状态" />
					<powersi:textfield id="bkf320" label="计划开始时间" name="kfd6Dto.bkf320"
						mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="kfd3Dto.akb020" />
					<powersi:hidden id="bkf306" name="kfd3Dto.bkf306" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>


	<powersi:panelbox iconClass="icon-cog" title="医院实施计划信息"
		allowCollapse="false">
		<powersi:datagrid id="gridList" formId="queryForm" frozen="false">
			<powersi:datagrid-column key="operate" render="renderOperate"
				onDblClickRow="dbSelectRow" width="10%" frozen="true" />
			<powersi:datagrid-column display="实施计划名称" name="bkf318" width="10%" />
			<powersi:datagrid-column display="建立计划时间" name="bkf319" width="20%" />
			<powersi:datagrid-column display="计划总体开始时间" name="bkf320" width="20%" />
			<powersi:datagrid-column display="计划预计结束时间" name="bkf321" width="20%" />
			<powersi:datagrid-column display="计划状态" name="aae100" width="10%" code="plan_flag"/>
			<powersi:datagrid-column display="计划建立人" name="bae101" width="10%" />
			<powersi:datagrid-column display="实施计划医院表ID" name="bkf307" hide="true" />
			<powersi:datagrid-column display="实施计划ID" name="bkf306" hide="true" />
			<powersi:datagrid-column display="计划状态" name="aae100v" hide="true" />
		</powersi:datagrid>
	</powersi:panelbox>

	<powersi:errors />

</body>
</powersi:html>
<script>
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="提交申请验收" class="linkButton"');
		a.push(' onclick="saveApply(');
		a.push(index);
		a.push(')"');
		if (row['aae100v'] == '2'||row['aae100v'] == '3') {
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		return a.join('');
	}
	function saveApply(index) {
		var row = gridList.getRow(index);
		var bkf307 = row['bkf307'];
		postJSON("${rootPath}/actualize/ActualizeManageAction!saveActualizeApply.action?bkf307="
						+ bkf307, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.errortype == 0) {
						var len = powersi.length(json.data);
						if (len < 0) {
							popupAlert("申请失败=" + json.data.message);
							return;
						}
						var suss = json.data.suss;
						if (suss == 1) {
							popupAlert("申请验收成功", 5000);
							gridList.reload();
						} else {
							popupAlert(json.data.message);
						}
					} else {
						var mes = json.message;
						popupAlert(mes);
					}
				});
	}
	
	function report(){
		var row = null;
		row = gridList.getSelectedRow();
		if (row == null) {
			popupAlert("请双击一条业务记录！", 3000);
			return;
		}
		var aae100 = row.aae100v;
		if(aae100==1){
			popupAlert("本计划还未申请验收成功！", 3000);
			return;
		}
		var bkf307 = row.bkf307;
		popupDialog(
				{
					url : "${rootPath}/actualize/ActualizeManageAction!applyReport.action?bkf307="
							+ bkf307,
					onClosed : function() {
	
					}
				}, 600, 1000);
	}
	
</script>