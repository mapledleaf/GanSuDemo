<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="生育就医回退" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<powersi:form id="queryInHospitalMaternity" namespace="/inhospital"
		action="InhospitalManagerAction!queryInHospitalMaternity.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" key="button_query" onclick="doQuery();"/>
				<powersi:button id="btResetView" label="重置" onclick="resetView()" key="button_reset"/>
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
				<powersi:button id="btnExport3" label="导出"  onclick="grid.exportExcel2007()" key="button_export"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,10%,10%,8%,16%,4%,12%,10%,10%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="开始日期" required="true" mask="date" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="结束日期" required="true" mask="date" />
					<powersi:radio id="bka891" name="inHospitalDTO.bka891" label="结算标识"
						codeType="bka891" required="true" />
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="" cssClass="select2" 
						list="#{'akc190':'住院号','aac002':'社会保障号','aaz217':'就医登记号' }" />
					<td colspan="2">
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring"
							title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
							onkeydown="goQuery()" buttonDisabled="false"  />
					</td>	
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
	</powersi:form>
	<powersi:panelbox key="业务列表">
		<powersi:datagrid id="grid" formId="queryInHospitalMaternity"
			delayLoad="true" showReload="false" enabledSort="false"
			alternatingRow="true" colDraggable="false" usePager="false">
			<powersi:datagrid-column key="operate" render="renderOperate"
				width="248" frozen="true" />
			<powersi:datagrid-column name="akb020" display="医院编号" width="80" />
			<powersi:datagrid-column name="aaz217" display="就医登记号" width="150" />
			<powersi:datagrid-column name="aac001" display="电脑号" width="120" />
			<powersi:datagrid-column name="aac003" display="姓名" width="120" />
			<powersi:datagrid-column name="aac004" display="性别" code="aac004"
				width="40" />
			<powersi:datagrid-column name="aac002" display="社会保障号码" width="150" />
			<powersi:datagrid-column name="bka035" display="人员类别" code="bka035"
				width="120" />
			<powersi:datagrid-column name="aka130" display="业务类别" code="aka130"
				width="80" />
			<powersi:datagrid-column name="bka006" display="待遇类型" code="bka006"
				width="150" />
			<powersi:datagrid-column name="aae030" display="业务开始日期" width="80" />
			<powersi:datagrid-column name="bkz101" display="入院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="aae031" display="业务结束日期" width="80" />
			<powersi:datagrid-column name="bkz102" display="出院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="akc190" display="住院号" width="120" />
			<powersi:datagrid-column name="akc194" display="业务结算日期" width="80" />
			<powersi:datagrid-column name="aae140" display="险种类型" code="aae140"
				width="150" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#querystring").focus();
		});

		function doQuery(){
			var bka8911 = $("#bka8911").prop('checked');
// 			if(bka8911||"true"==bka8911){
// 				if($("#querystring").val()==""){
// 					popupAlert("请输入查询条件！");
// 					return;
// 				}
// 				grid.reset();
// 			}
			$("#queryInHospitalMaternity").submit();
		}
		
		function goQuery(){
			if (window.event.keyCode == "13") {
				doQuery();
			}
		}
		
		function resetView() {
			$("#btReset").click();
			$("#querystring").val("");
			$("#argName").val("akc190");
			$("#argName").change();
			grid.reset();
		}

		function renderOperate(row, index, value) {
			var a = [];
			a.push('<input type="button" value="取消生育住院" class="linkButton"');
			a.push(' onclick="doCancelRegister(');
			a.push(index);
			a.push(')"');
			if (row['bka891'] == '1') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

// 			a.push("&nbsp;&nbsp;");

// 			a.push('<input type="button" value="取消生育出院" class="linkButton"');
// 			a.push(' onclick="doCancelOutRegister(');
// 			a.push(index);
// 			a.push(')"');
// 			if (row['bka039'] == '0' || row['bka891'] == '1') {
// 				a.push(' disabled="disabled"');
// 			}
// 			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="取消生育结算" class="linkButton"');
			a.push(' onclick="doCancelOutCharge(');
			a.push(index);
			a.push(')"');
			if (row['bka891'] == '0') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

			return a.join('');
		}

		function doCancelOutCharge(index) {
			var row = grid.getRow(index);
			var kc21id = row['kc21id'];
			if (powersi.isnull(kc21id)) {
				return;
			}
			var aaz217 = row['aaz217'];
			if (powersi.isnull(aaz217)) {
				return;
			}
			var aac003 = row['aac003'];
			if (powersi.isnull(aac003)) {
				return;
			}
			if(popupConfirm("您确认取消" + aac003 + "的生育出院结算吗?","确认", function(isOk){
				if(isOk){
					postJSON(
							"${rootPath}/inhospital/InhospitalManagerAction!cancelOutCharge.action?inHospitalDTO.kc21id="
									+ kc21id + "&inHospitalDTO.aaz217=" + aaz217,
							function(json) {
								if (!checkJSONResultNew(json)) {
									return;
								}
								popupInfo(json.message);
								grid.deleteRow(index);
							});
				}
			}));
		}

		function doCancelOutRegister(index) {
			var row = grid.getRow(index);
			var kcd1id = row['kcd1id'];
			if (powersi.isnull(kcd1id)) {
				return;
			}
			var aaz217 = row['aaz217'];
			if (powersi.isnull(aaz217)) {
				return;
			}
			var aac003 = row['aac003'];
			if (powersi.isnull(aac003)) {
				return;
			}
			if (!confirm("您确认取消" + aac003 + "的生育出院登记吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!cancelOutRegister.action?inHospitalDTO.kcd1id="
							+ kcd1id + "&inHospitalDTO.aaz217=" + aaz217,
					function(json) {
						if (!checkJSONResultNew(json)) {
							return;
						}
						popupInfo(json.message);
						grid.deleteRow(index);
					});
		}

		function doCancelRegister(index) {
			var row = grid.getRow(index);
			var kcd1id = row['kcd1id'];
			if (powersi.isnull(kcd1id)) {
				return;
			}
			var aaz217 = row['aaz217'];
			if (powersi.isnull(aaz217)) {
				return;
			}
			var aac003 = row['aac003'];
			if (powersi.isnull(aac003)) {
				return;
			}
			var aac002 = row['aac002'];
			if (powersi.isnull(aac002)) {
				return;
			}
			if(popupConfirm("您确认取消" + aac003 + "的住院登记吗?", "确认", function(isOk){
				if(isOk){
					var bke548 = null;// 读卡返回
					var cancelRegisterData = {
						"inHospitalDTO.kcd1id" : kcd1id,
						"inHospitalDTO.aaz217" : aaz217,
						"inHospitalDTO.aac002" : aac002,
						"inHospitalDTO.bke548" : bke548
					}
					postJSON(
							"${rootPath}/inhospital/InhospitalManagerAction!cancelRegister.action",
							cancelRegisterData, function(json) {
								if (!checkJSONResultNew(json)) {
									return;
								}
								popupInfo(json.message);
								grid.deleteRow(index);
							});
				}
			}));
		}
	</script>
</body>
</powersi:html>