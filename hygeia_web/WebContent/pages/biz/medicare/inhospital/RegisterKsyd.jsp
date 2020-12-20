<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="跨省异地就医入院登记" />
</head>
<body>
	<powersi:form id="registerKsyd" namespace="/inhospital"
		action="InhospitalManagerAction!registerKsyd.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveRegister" label="保存登记"
					onclick="saveRegisterKsyd()" />
				<powersi:button id="btRegisterReset" label="重置"
					onclick="registerKsydReset()" />
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label=""
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="findAac001Ksyd()" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
					<powersi:textfield id="aab301_name"
						name="inHospitalDTO.aab301_name" label="参保地统筹区" readonly="true" />
					<powersi:hidden id="aab301" name="inHospitalDTO.aab301" />
					<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="基本信息">
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="bka004_name"
						name="inHospitalDTO.bka004_name" label="人员类别" readonly="true" />
					<powersi:textfield id="bka005_name"
						name="inHospitalDTO.bka005_name" label="级  别" readonly="true" />
					<powersi:hidden id="bka005" name="inHospitalDTO.bka005" value="000" />
					<powersi:textfield id="aac013_name"
						name="inHospitalDTO.aac013_name" label="人员状态" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka888_name"
						name="inHospitalDTO.bka888_name" label="基金状况" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />
					<powersi:textfield id="bac032_name"
						name="inHospitalDTO.bac032_name" label="困难人员类别" readonly="true" />
					<powersi:textfield id="bka010" name="inHospitalDTO.bka010"
						label="住院次数" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="本次登记">
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
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka017" name="inHospitalDTO.bka017"
						label="入院日期" mask="date" required="true" readonly="false" />
					<powersi:codeselect id="bka006" name="inHospitalDTO.bka006"
						label="待遇类型" cssClass="select2" codeType="bka006"
						codeFilter="data_value like '82_'" required="true"
						displayonly="false" showValue="true" />
					<powersi:textfield id="bka025" name="inHospitalDTO.bka025"
						label="住院号" onkeydown="keydown(this)" required="true"
						readonly="false" />
					<powersi:textfield id="bka026_name"
						name="inHospitalDTO.bka026_name" label="入院诊断" title="请输入入院诊断"
						placeholder="请输入入院诊断" onkeydown="keydown(this)" required="true"
						readonly="true" buttonText="选     择" buttonId="bka026_button"
						buttonDisabled="false" onbuttonclick="choosebka026()" />
					<powersi:hidden id="bka026" name="inHospitalDTO.bka026" />
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
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="yzz014" name="inHospitalDTO.yzz014"
						label="异地就医备案号" readonly="true" />
					<powersi:codeselect id="ykc679" name="inHospitalDTO.ykc679"
						label="住院原因" codeType="ykc679" required="true" displayonly="false"
						showValue="true" />
					<powersi:codeselect id="ykc680" name="inHospitalDTO.ykc680"
						label="补助类型" codeType="ykc680" displayonly="false"
						showValue="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="baf313" name="inHospitalDTO.baf313"
						label="联系人" readonly="false" />
					<powersi:textfield id="aae005" name="inHospitalDTO.aae005"
						label="联系电话" readonly="false" />
					<powersi:textfield id="baz113" name="inHospitalDTO.baz113"
						label="联系地址" readonly="false" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka043" name="inHospitalDTO.bka043"
						label="病情备注" colspan="3" readonly="false" />
					<powersi:textfield id="bka018" name="inHospitalDTO.bka018"
						label="多诊断" title="请输入入院多诊断" placeholder="请输入入院多诊断"
						onkeydown="keydown(this)" required="false" readonly="true"
						buttonText="选择" buttonId="bka018_button" buttonDisabled="false"
						colspan="3" onbuttonclick="choosebka018()" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="aac006" name="inHospitalDTO.aac006" />
		<powersi:hidden id="bka977_name" name="inHospitalDTO.bka977_name" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		var saveRegisterFlag = false;
		$(document).ready(function() {
			$("#tracestring").focus();
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
			loadCardControl();
			if (objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}

		/*读卡获取后台信息*/
		function readic() {
			registerKsydReset();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findAac002Ksyd("");
		}

		/*初始化*/
		function init() {
			saveRegisterFlag = false;
			$("#bke548").val("");
			$("#querystring").val("");
			$("#tracestring").val("");
			$("#bka977_name").val("");
			$("#aac001").val("");
			$("#aac002").val("");
			$("#aac003").val("");
			$("#tracestring").prop("disabled", false);
			$("#btSaveRegister").prop("disabled", true);
			//待遇类型
			$("#bka006").val("");
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
			$("#bka006").change();
		}

		/*重置界面*/
		function registerKsydReset() {
			var aac003 = powersi.trim($("#aac003").val());
			if (!powersi.isnull(aac003) && !saveRegisterFlag) {
				if (!confirm("界面已加载数据,您确认要重置吗?")) {
					return;
				}
			}
			$("#btReset").click();
			init();
		}

		function keydown(param) {
			if (event.keyCode == "13") {
				var filed_name = param.id;
				if ("bka025" == filed_name) {
					var bka025 = powersi.trim($("#bka025").val());
					if (powersi.isnull(bka025)) {
						return $("#bka025").focus();
					}
					return $("#bka026_name").focus();
				}
				if ("bka026_name" == filed_name) {
					return choosebka026();
				}
				if ("bka018" == filed_name) {
					return choosebka018();
				}
			}
		}

		/*选择疾病*/
		function choosebka026() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#bka026_name").val(retValue.aka121);
								$("#bka026").val(retValue.aka120);
								return $("#bka021").focus();
							} else {
								$("#bka026_name").val("");
								$("#bka026").val("");
								return $("#bka026_name").focus();
							}
						}
					}, 500, 600);
		}

		/*多疾病录入*/
		function choosebka018() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDiseases.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#bka018").val(retValue.bka018);
								return $("#bka018").focus();
							} else {
								$("#bka018").val("");
								return $("#bka018").focus();
							}
						}
					}, 500, 600);
		}

		/*可录入状态*/
		function inputOn() {
			$("#tracestring").prop("disabled", false);
		}

		/*不可录入状态*/
		function inputOff() {
			/*规避重复操作处理_不可用*/
			$("#tracestring").prop("disabled", true);
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
					});
		}

		/*查询参保信息*/
		function findAac001Ksyd() {
			if (window.event.keyCode == 13) {
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入有效查询条件!");
					return;
				}
				$("#tracestring").prop("disabled", true);
				findAac002Ksyd(tracestring);
				$("#tracestring").prop("disabled", false);
			}
		}

		/*省内异地住院登记界面要支持：身份证号码、电脑号、社保卡号*/
		function findAac002Ksyd(tracestring) {
			if (powersi.isnull(tracestring)) {
				$("#querystring").val("");
			} else {
				$("#querystring").val(tracestring);
			}
			var registerKsydData = $("#registerKsyd").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001Ksyd.action",
					registerKsydData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no" && json.data.length > 1) {
							if (powersi.isnull(tracestring)) {
								chooseAac002(json.message);
								$("#querystring").val(json.message);
								$("#tracestring").val(json.message);
							} else {
								chooseAac002(tracestring);
							}
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
						afterFindAac001();
					});
		}

		/*查询并选择参保信息*/
		function chooseAac002(querystring) {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!choosePersonAac001.action?inHospitalDTO.querystring="
								+ querystring,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								accessAac001(retValue.aac001);
							}
						}
					}, 500, 600);
		}

		/*根据电脑号查询参保信息*/
		function accessAac001(aac001) {
			if (powersi.isnull(powersi.trim(aac001))) {
				return;
			}
			$("#bke548").val("");
			$("#querystring").val("");
			$("#tracestring").val("");
			$("#bka977_name").val("1");
			$("#aac001").val(powersi.trim(aac001));
			$("#tracestring").val(powersi.trim(aac001));
			var registerKsydData = $("#registerKsyd").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001Ksyd.action",
					registerKsydData, function(json) {
						$("#bka977_name").val("");
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
						afterFindAac001();
					});
		}

		function afterFindAac001() {
			$("#btSaveRegister").prop("disabled", false);
		}

		/*基金状态*/
		function fundStatusQuery() {
			var aac001 = powersi.trim($("#aac001").val());
			if (powersi.isnull(aac001)) {
				return;
			}
			var aae140 = powersi.trim($("#aae140").val());
			if (powersi.isnull(aae140)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!queryPersonFund.action?inHospitalDTO.aac001="
								+ aac001 + "&inHospitalDTO.aae140=" + aae140,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {

							} else {

							}
						}
					}, 400, 700);
		}

		/*保存省内异地住院登记*/
		function saveRegisterKsyd() {
			if (!checkForm()) {
				return;
			}
			inputOff();
			var aac003 = powersi.trim($("#aac003").val());
			if (!confirm("您确认保存[" + aac003 + "]的跨省异地就医入院登记信息吗?")) {
				inputOn();
				return;
			}
			inputOn();
			$("#btSaveRegister").prop("disabled", true);
			var registerKsydData = $("#registerKsyd").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!registerKsyd.action",
					registerKsydData, function(json) {
						$("#btSaveRegister").prop("disabled", false);
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
						afterSaveRegister();
						saveRegisterFlag = true;
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
		}

		function afterSaveRegister() {
			$("#btSaveRegister").prop("disabled", true);
		}
	</script>
</body>
</powersi:html>