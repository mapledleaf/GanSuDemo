<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="工伤住院登记" />
</head>
<body>
	<powersi:form id="registerInjury" namespace="/inhospital"
		action="InhospitalManagerAction!registerInjury.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveRegister" label="保存登记"
					onclick="saveRegister()" />
				<powersi:button id="btFundStatusQuery" label="基金状态"
					onclick="fundStatusQuery()" />
				<powersi:button id="btcumulative" label="查询累计"
					onclick="cumulativeQuery()" />
				<powersi:button id="btRegisterReset" label="重置"
					onclick="registerInjuryReset()" />
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bka006" name="inHospitalDTO.bka006"
						label="待遇类型" cssClass="select2" codeType="bka006"
						codeFilter="data_value like '42_'" required="true"
						displayonly="false" showValue="true" />
					<powersi:textfield id="tracestring" name="tracestring" label=""
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="findAac001Injury()" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
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
		<powersi:panelbox key="上次住院">
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="akb021_last"
						name="inHospitalDTO.akb021_last" label="医院名称" readonly="true" />
					<powersi:textfield id="aka121_last"
						name="inHospitalDTO.aka121_last" label="诊  断" readonly="true" />
					<powersi:textfield id="bka017_last"
						name="inHospitalDTO.bka017_last" label="入院日期" readonly="true" />
					<powersi:textfield id="bka032_last"
						name="inHospitalDTO.bka032_last" label="出院日期" readonly="true" />
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
					<powersi:textfield id="alc011" name="inHospitalDTO.alc011"
						label="认定书编号" readonly="true" />
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="医疗期开始日期" readonly="true" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="医疗期结束日期" readonly="true" />
					<powersi:textfield id="alc020" name="inHospitalDTO.alc020"
						label="事故发生日期" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aab004" name="inHospitalDTO.aab004"
						label="事故发生单位" readonly="true" />
					<powersi:textfield id="bka042" name="inHospitalDTO.bka042"
						label="工伤认定信息ID" readonly="true" />
					<powersi:textfield id="alc035" name="inHospitalDTO.alc035"
						label="劳动能力鉴定书编号" readonly="true" />
					<powersi:textfield id="bka507" name="inHospitalDTO.bka507"
						label="医疗期ID" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka017" name="inHospitalDTO.bka017"
						label="入院日期" mask="date" required="true" readonly="false" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
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
					<powersi:codeselect id="aaz065" name="inHospitalDTO.aaz065"
						label="银行" codeType="aaz065" showValue="true" />
					<powersi:textfield id="aae009" name="inHospitalDTO.aae009"
						label="户名" />
					<powersi:textfield id="aae010" name="inHospitalDTO.aae010"
						label="银行账号" />
					<powersi:textfield id="aae010_confirm"
						name="inHospitalDTO.aae010_confirm" label="账号确认" />
					<powersi:hidden id="bka509" name="inHospitalDTO.bka509" />
					<powersi:hidden id="bka040" name="inHospitalDTO.bka040" />
					<powersi:hidden id="bka510" name="inHospitalDTO.bka510" />
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
		<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
		<powersi:hidden id="bka415" name="inHospitalDTO.bka415" />
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
			var bka017 = powersi.trim($("#bka017").val());
			if (powersi.isnull(bka017)) {
				popupAlert("请输入入院日期!");
				return;
			}
			var bka006 = powersi.trim($("#bka006").val());
			if (powersi.isnull(bka006)) {
				popupAlert("请先选择待遇类型!");
				return;
			}
			registerInjuryReset();
			$("#bka017").val(bka017);
			$("#bka006").val(bka006);
			$("#bka006").change();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findAac002Injury("");
		}

		/*累计查询*/
		function cumulativeQuery() {
			var aac001 = powersi.trim($("#aac001").val());
			if (powersi.isnull(aac001)) {
				return;
			}
			var baa027 = powersi.trim($("#baa027").val());
			if (powersi.isnull(baa027)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!cumulativeQuery.action?inHospitalDTO.aac001="
								+ aac001 + "&inHospitalDTO.baa027=" + baa027,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {

							} else {

							}
						}
					}, 400, 600);
		}

		/*初始化*/
		function init() {
			saveRegisterFlag = false;
			$("#aaz065").prop("disabled", false);
			$("#aae009").prop("disabled", false);
			$("#aae010").prop("disabled", false);
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
		function registerInjuryReset() {
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
		function findAac001Injury() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				$("#querystring").val("");
				$("#bka977_name").val("");
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入有效查询条件!");
					return;
				}
				var bka017 = powersi.trim($("#bka017").val());
				if (powersi.isnull(bka017)) {
					popupAlert("请输入入院日期!");
					return;
				}
				var bka006 = powersi.trim($("#bka006").val());
				if (powersi.isnull(bka006)) {
					popupAlert("请先选择待遇类型!");
					return;
				}
				$("#tracestring").prop("disabled", true);
				findAac002Injury(tracestring);
				$("#tracestring").prop("disabled", false);
			}
		}

		/*工伤住院登记界面要支持：身份证号码、电脑号、社保卡号*/
		function findAac002Injury(tracestring) {
			if (powersi.isnull(tracestring)) {
				$("#querystring").val("");
			} else {
				$("#querystring").val(tracestring);
			}
			var registerInjuryData = $("#registerInjury").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001Injury.action",
					registerInjuryData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no" && json.data.length > 1) {
							if (powersi.isnull(tracestring)) {
								chooseAac001Injury(json.message);
								$("#querystring").val(json.message);
								$("#tracestring").val(json.message);
							} else {
								chooseAac001Injury(tracestring);
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
		function chooseAac001Injury(querystring) {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseMedicalPeriodInjury.action?inHospitalDTO.querystring="
								+ querystring,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								accessAac001(retValue.aac001, retValue.bka042,
										retValue.bka507);
							}
						}
					}, 500, 800);
		}

		/*根据电脑号查询参保信息*/
		function accessAac001(aac001, bka042, bka507) {
			if (powersi.isnull(powersi.trim(aac001))) {
				return;
			}
			if (powersi.isnull(powersi.trim(bka042))) {
				return;
			}
			$("#bke548").val("");
			$("#querystring").val("");
			$("#tracestring").val("");
			$("#bka042").val("");
			$("#bka507").val("");
			$("#bka977_name").val("1");
			$("#aac001").val(powersi.trim(aac001));
			$("#bka042").val(powersi.trim(bka042));
			$("#bka507").val(powersi.trim(bka507));
			$("#tracestring").val(powersi.trim(aac001));
			var registerInjuryData = $("#registerInjury").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001Injury.action",
					registerInjuryData, function(json) {
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
			var bka510 = powersi.trim($("#bka510").val());
			if (!powersi.isnull(bka510)) {
				$("#aaz065").prop("disabled", true);
				$("#aae009").prop("disabled", true);
				$("#aae010").prop("disabled", true);
			} else {
				$("#aaz065").prop("disabled", false);
				$("#aae009").prop("disabled", false);
				$("#aae010").prop("disabled", false);
			}
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

		/*保存工伤住院登记*/
		function saveRegister() {
			if (!checkForm()) {
				return;
			}
			var bka510 = powersi.trim($("#bka510").val());
			if (powersi.isnull(bka510)) {
				var aaz065 = powersi.trim($("#aaz065").val());
				var aae009 = powersi.trim($("#aae009").val());
				var aae010 = powersi.trim($("#aae010").val());
				var aae010_confirm = powersi.trim($("#aae010_confirm").val());
				if (!powersi.isnull(aae010) && aae010 != aae010_confirm) {
					popupAlert("您录入的银行账号和确认账号不一致!");
					return;
				}
				$("#aae010").val(aae010)
				$("#aae010_confirm").val(aae010_confirm)
				$("#bka509").val(aaz065)
				$("#bka040").val(aae009)
				$("#bka510").val(aae010)
			}
			inputOff();
			var aac003 = powersi.trim($("#aac003").val());
			if (!confirm("您确认保存[" + aac003 + "]的工伤住院登记信息吗?")) {
				inputOn();
				return;
			}
			inputOn();
			$("#btSaveRegister").prop("disabled", true);
			var registerInjuryData = $("#registerInjury").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!registerInjury.action",
					registerInjuryData, function(json) {
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