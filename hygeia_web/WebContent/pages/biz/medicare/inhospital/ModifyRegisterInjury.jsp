<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="工伤住院修改" />
</head>
<body>
	<powersi:form id="modifyRegisterInjury" namespace="/inhospital"
		action="InhospitalManagerAction!modifyRegisterInjury.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveModifyRegister" label="保存修改"
					onclick="saveModifyRegisterInjury()" />
				<powersi:button id="btModifyRegisterReset" label="重置"
					onclick="modifyRegisterInjuryReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring"
						name="inHospitalDTO.querystring" label="" title="请输入信息回车"
						placeholder="请输入信息回车" readonly="false"
						onkeydown="findModifyRegisteraaz217Injury()" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="住院信息">
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aaa027_name"
						name="inHospitalDTO.aaa027_name" label="医保分中心" readonly="true" />
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:textfield id="akb021" name="inHospitalDTO.akb021"
						label="医院名称" readonly="true" />
					<powersi:textfield id="aka130_name"
						name="inHospitalDTO.aka130_name" label="业务类型" readonly="true" />
					<powersi:textfield id="aae140_name"
						name="inHospitalDTO.aae140_name" label="险种类型" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka017" name="inHospitalDTO.bka017"
						label="入院日期" readonly="true" />
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
					<powersi:textfield id="bka025" name="inHospitalDTO.bka025"
						label="住院号" required="true" />
					<powersi:textfield id="bka026_name"
						name="inHospitalDTO.bka026_name" label="入院诊断" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bka019" name="inHospitalDTO.bka019"
						label="入院科室" cssClass="select2" list="#request.bka019List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectbka021()" showValue="true" />
					<powersi:codeselect id="bka021" name="inHospitalDTO.bka021"
						label="入院病区" cssClass="select2" list="#request.bka021List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectbka023()" />
					<powersi:codeselect id="bka023" name="inHospitalDTO.bka023"
						label="床位号" cssClass="select2" list="#request.bka023List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectbka503()" />
					<powersi:codeselect id="bka503" name="inHospitalDTO.bka503"
						label="医保医师" cssClass="select2" list="#request.bka503List"
						headerKey="" headerValue="请选择..." />
					<powersi:hidden id="bka019_hid" name="inHospitalDTO.bka019_hid" />
					<powersi:hidden id="bka021_hid" name="inHospitalDTO.bka021_hid" />
					<powersi:hidden id="bka023_hid" name="inHospitalDTO.bka023_hid" />
					<powersi:hidden id="bka503_hid" name="inHospitalDTO.bka503_hid" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka014" name="inHospitalDTO.bka014"
						label="登记人工号" readonly="true" />
					<powersi:textfield id="bka015" name="inHospitalDTO.bka015"
						label="登记人" readonly="true" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />
					<powersi:textfield id="kcd1id" name="inHospitalDTO.kcd1id"
						label="UUID" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		var firstLoad = true;
		$(document).ready(function() {
			$("#querystring").focus();
		});

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
			modifyRegisterInjuryReset();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findModifyRegisteraaz217InjuryByBka100();
		}

		function findModifyRegisteraaz217InjuryByBka100() {
			var modifyRegisterData = $("#modifyRegisterInjury").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findModifyRegisteraaz217Injury.action",
					modifyRegisterData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
						$("#bka019").change();
					});
		}

		function saveModifyRegisterInjury() {
			if (!checkForm()) {
				return;
			}
			var kcd1id = powersi.trim($("#kcd1id").val());
			if (powersi.isnull(kcd1id)) {
				return;
			}
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			$("#btSaveModifyRegister").prop("disabled", true);
			var modifyRegisterInjuryData = $("#modifyRegisterInjury")
					.serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!modifyRegisterInjury.action",
					modifyRegisterInjuryData, function(json) {
						$("#btSaveModifyRegister").prop("disabled", false);
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no") {
							$.each(json.data, function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
		}

		function modifyRegisterInjuryReset() {
			$("#btReset").click();
			//入院科室
			$("#bka019").val("");
			//入院病区
			$("#bka021").empty();
			//入院床位号
			$("#bka023").empty();
			//医保医师
			$("#bka503").empty();
			$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka023").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka503").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka021").change();
			$("#bka023").change();
			$("#bka503").change();
			$("#bka019").change();
			firstLoad = true;
		}

		function findModifyRegisteraaz217Injury() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					return;
				}
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findModifyRegisteraaz217Injury.action?inHospitalDTO.querystring="
								+ querystring, function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							$.each(json.data, function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
							$("#bka019").change();
						});
			}
		}

		/*加载医保医师*/
		function selectbka503() {
			//入院科室
			var bka019 = $("#bka019").val();
			if (powersi.isnull(bka019)) {
				return;
			}
			//入院病区
			var bka021 = $("#bka021").val();
			if (powersi.isnull(bka021)) {
				return;
			}
			//入院床位号
			var bka023 = $("#bka023").val();
			$("#bka503").empty();
			$("#bka503").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka503").change();
			if (powersi.isnull(bka023)) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadBka503List.action",
					{
						"inHospitalDTO.bka019" : bka019
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no") {
							var a = [];
							$.each(json.data, function(key, value) {
								a.push('<option value="');
								a.push(key);
								a.push('"');
								a.push(">");
								a.push(value);
								a.push("</option>");
							});
							$("#bka503").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
						var bka503_hid = $("#bka503_hid").val();
						if (!powersi.isnull(bka503_hid)) {
							$("#bka503").val(bka503_hid);
							$("#bka503").change();
						}
					});
		}

		/*加载床位号*/
		function selectbka023() {
			//入院科室
			var bka019 = $("#bka019").val();
			if (powersi.isnull(bka019)) {
				return;
			}
			//入院病区
			var bka021 = $("#bka021").val();
			//入院床位号
			$("#bka023").empty();
			//医保医师
			$("#bka503").empty();
			$("#bka023").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka503").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka023").change();
			$("#bka503").change();
			if (powersi.isnull(bka021)) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadBka023List.action",
					{
						"inHospitalDTO.bka021" : bka021
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no") {
							var a = [];
							$.each(json.data, function(key, value) {
								a.push('<option value="');
								a.push(key);
								a.push('"');
								a.push(">");
								a.push(value);
								a.push("</option>");
							});
							$("#bka023").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
						var bka023_hid = $("#bka023_hid").val();
						if (!powersi.isnull(bka023_hid)) {
							if (firstLoad) {
								var a = [];
								a.push('<option value="');
								a.push(bka023_hid);
								a.push('"');
								a.push(">");
								a.push(bka023_hid);
								a.push("</option>");
								$("#bka023").append(a.join(''));
								firstLoad = false;
							}
							$("#bka023").val(bka023_hid);
							$("#bka023").change();
						}
					});
		}

		/*加载病区*/
		function selectbka021() {
			//入院科室
			var bka019 = $("#bka019").val();
			//入院病区
			$("#bka021").empty();
			//入院床位号
			$("#bka023").empty();
			//医保医师
			$("#bka503").empty();
			$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka023").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka503").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka021").change();
			$("#bka023").change();
			$("#bka503").change();
			if (powersi.isnull(bka019)) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadBka021List.action",
					{
						"inHospitalDTO.bka019" : bka019
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no") {
							var a = [];
							$.each(json.data, function(key, value) {
								a.push('<option value="');
								a.push(key);
								a.push('"');
								a.push(">");
								a.push(value);
								a.push("</option>");
							});
							$("#bka021").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
						var bka021_hid = $("#bka021_hid").val();
						if (!powersi.isnull(bka021_hid)) {
							$("#bka021").val(bka021_hid);
							$("#bka021").change();
						}
					});
		}
	</script>
</body>
</powersi:html>