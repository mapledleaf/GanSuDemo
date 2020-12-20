<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="工伤就医回退" />
</head>
<body>
	<powersi:form id="queryInHospitalInjury" namespace="/inhospital"
		action="InhospitalManagerAction!queryInHospitalInjury.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
				<powersi:button id="btResetView" label="重置" onclick="resetView()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="开始日期" required="true" mask="date" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="结束日期" required="true" mask="date" />
					<powersi:radio id="bka891" name="inHospitalDTO.bka891" label="结算标识"
						codeType="bka891" required="true" />
					<powersi:textfield id="querystring"
						name="inHospitalDTO.querystring" label="" title="请输入信息回车"
						placeholder="请输入信息回车" readonly="false"
						onkeydown="queryInHospitalBizs()" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" />
					<powersi:textfield id="bka025" name="inHospitalDTO.bka025"
						label="住院号" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
	</powersi:form>
	<powersi:panelbox key="业务列表">
		<powersi:datagrid id="grid" formId="queryInHospitalInjury"
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
			<powersi:datagrid-column name="bka004" display="人员类别" code="bka004"
				width="120" />
			<powersi:datagrid-column name="aka130" display="业务类别" code="aka130"
				width="80" />
			<powersi:datagrid-column name="bka006_name" display="待遇类型"
				width="150" />
			<powersi:datagrid-column name="bka017" display="业务开始日期" width="80" />
			<powersi:datagrid-column name="bka026_name" display="入院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="bka032" display="业务结束日期" width="80" />
			<powersi:datagrid-column name="bka031_name" display="出院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="bka025" display="住院号" width="120" />
			<powersi:datagrid-column name="bka045" display="业务结算日期" width="80" />
			<powersi:datagrid-column name="aae140" display="险种类型" code="aae140"
				width="150" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#querystring").focus();
		});

		var objCard = null;
		/*加载控件*/
		function loadCardControl() {
			try {
				if (objCard == null || objCard.object == null) {
					objCard = document.getElementById("cardControl");
					if (objCard.object == null) {
						popupAlert("请先安装社保卡控件!");
					}
				}
			} catch (e) {
				popupAlert("请先安装社保卡控件!");
			}
		}

		/*读卡*/
		function iReadCardBase() {
			$("#bke548").val("");
			$("#querystring").val("");
			loadCardControl();
			if (objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}

		/*读卡获取后台信息*/
		function readic() {
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			$("#queryInHospitalInjury").submit();
			$("#bke548").val("");
		}

		function queryInHospitalBizs() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				$("#queryInHospitalInjury").submit();
			}
		}

		function resetView() {
			$("#btReset").click();
			grid.reset();
		}

		function renderOperate(row, index, value) {
			var a = [];
			a.push('<input type="button" value="取消工伤住院" class="linkButton"');
			a.push(' onclick="doCancelRegister(');
			a.push(index);
			a.push(')"');
			if (row['bka039'] == '1' || row['bka891'] == '1') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="取消工伤出院" class="linkButton"');
			a.push(' onclick="doCancelOutRegister(');
			a.push(index);
			a.push(')"');
			if (row['bka039'] == '0' || row['bka891'] == '1') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="取消工伤结算" class="linkButton"');
			a.push(' onclick="doCancelOutCharge(');
			a.push(index);
			a.push(')"');
			if (row['bka039'] == '0' || row['bka891'] == '0') {
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
			if (!confirm("您确认取消" + aac003 + "的工伤出院结算吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!cancelOutCharge.action?inHospitalDTO.kc21id="
							+ kc21id + "&inHospitalDTO.aaz217=" + aaz217,
					function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
						grid.deleteRow(index);
					});
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
			if (!confirm("您确认取消" + aac003 + "的工伤出院登记吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!cancelOutRegister.action?inHospitalDTO.kcd1id="
							+ kcd1id + "&inHospitalDTO.aaz217=" + aaz217,
					function(json) {
						if (!checkJSONResult(json)) {
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
			if (!confirm("您确认取消" + aac003 + "的工伤住院登记吗?")) {
				return;
			}
			//读卡验证
			loadCardControl();
			var bke548 = null;// 读卡返回
			if (objCard != null && objCard.object != null) {
				bke548 = objCard.ReadCardBase();
			}
			var cancelRegisterData = {
				"inHospitalDTO.kcd1id" : kcd1id,
				"inHospitalDTO.aaz217" : aaz217,
				"inHospitalDTO.aac002" : aac002,
				"inHospitalDTO.bke548" : bke548
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!cancelRegister.action",
					cancelRegisterData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
						grid.deleteRow(index);
					});
		}
	</script>
</body>
</powersi:html>