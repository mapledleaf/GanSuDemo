<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="出院登记" />
<style>
.showpay {
	height: 25px !important;
	font-size: 18px !important;
	text-align: right !important;
}
</style>
</head>
<body>
	<powersi:form id="outRegister" namespace="/inhospital"
		action="InhospitalManagerAction!outRegister.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btTryCalcTreat" label="计算费用"
					onclick="outRegisterTryCalcTreat()" />
				<powersi:button id="btSaveOutRegister" label="保存登记"
					onclick="saveOutRegister()" />
				<powersi:button id="btFundStatusQuery" label="基金状态"
					onclick="fundStatusQuery()" />
				<powersi:button id="btResetOutRegister" label="重置"
					onclick="resetOutRegister()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring"
						name="inHospitalDTO.querystring" label="" title="请输入信息回车"
						placeholder="请输入信息回车" readonly="false"
						onkeydown="findOutRegisterAaz217()" buttonText="读卡"
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
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="bka005_name"
						name="inHospitalDTO.bka005_name" label="级  别" readonly="true" />
					<powersi:textfield id="bka004_name"
						name="inHospitalDTO.bka004_name" label="人员类别" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="入院科室" readonly="true" />
					<powersi:textfield id="bka022" name="inHospitalDTO.bka022"
						label="入院病区" readonly="true" />
					<powersi:textfield id="bka025" name="inHospitalDTO.bka025"
						label="住院号" readonly="true" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />
					<powersi:hidden id="kcd1id" name="inHospitalDTO.kcd1id" />
					<powersi:hidden id="bka001" name="inHospitalDTO.bka001" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka017" name="inHospitalDTO.bka017"
						label="入院日期" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />
					<powersi:textfield id="bka026_name"
						name="inHospitalDTO.bka026_name" label="入院诊断" readonly="true" />
					<powersi:textfield id="bka503_name"
						name="inHospitalDTO.bka503_name" label="医保医师" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka031_name"
						name="inHospitalDTO.bka031_name" label="出院诊断" title="请输入出院诊断"
						onkeydown="keydown(this)" required="true" readonly="true"
						buttonText="选择" buttonId="bka031_button" buttonDisabled="false"
						onbuttonclick="choosebka031()" />
					<powersi:hidden id="bka031" name="inHospitalDTO.bka031" />
					<powersi:textfield id="bka032" name="inHospitalDTO.bka032"
						label="出院日期" onchange="onchangebka032(this)" mask="date"
						required="true" readonly="false" />
					<powersi:hidden id="bka890" name="bka890" />
					<powersi:textfield id="bka043" name="inHospitalDTO.bka043"
						label="出院说明" readonly="false" />
					<powersi:textfield id="bka018" name="inHospitalDTO.bka018"
						label="多诊断" title="请输入出院多诊断" onkeydown="keydown(this)"
						required="false" readonly="true" buttonText="选择"
						buttonId="bka018_button" buttonDisabled="false"
						onbuttonclick="choosebka018()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row id="bka898_str">
					<powersi:codeselect id="bka898" name="inHospitalDTO.bka898"
					key="缴费档次" codeType="bka898"  disabled="true"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="统计数据">
			<powersi:editorlayout cols="8%,25%,8%,25%,8%,26%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bkf001" name="inHospitalDTO.bkf001"
						label="血型" codeType="bkf001" required="false" displayonly="false"
						cssClass="select2" showValue="true" />
					<powersi:codeselect id="bkf002" name="inHospitalDTO.bkf002"
						label="入院方式" codeType="bkf002" required="true" displayonly="false"
						cssClass="select2" showValue="true" />
					<powersi:codeselect id="bkf003" name="inHospitalDTO.bkf003"
						label="入院情况" codeType="bkf003" required="true" displayonly="false"
						cssClass="select2" showValue="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bkf004" name="inHospitalDTO.bkf004"
						label="出院转归情况" codeType="bkf004" required="true"
						displayonly="false" cssClass="select2" showValue="true" />
					<powersi:textfield id="bkf005" name="inHospitalDTO.bkf005"
						label="抢救次数" readonly="false" />
					<powersi:textfield id="bkf006" name="inHospitalDTO.bkf006"
						label="抢救成功次数" readonly="false" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="结算信息">
			<powersi:editorlayout cols="8%,12%,8%,12%,8%,12%,8%,12%,8%,12%">
				<powersi:editorlayout-row>
					<powersi:textfield id="akc264" name="inHospitalDTO.akc264"
						label="总的费用" cssClass="showpay" value="0.00" disabled="true" />
					<powersi:textfield id="bka832" name="inHospitalDTO.bka832"
						label="基金支付" cssClass="showpay" value="0.00" disabled="true" />
					<powersi:textfield id="bka831" name="inHospitalDTO.bka831"
						label="自付金额" cssClass="showpay" value="0.00" disabled="true" />
					<powersi:textfield id="cash_pay_own"
						name="inHospitalDTO.cash_pay_own" label="自费金额" cssClass="showpay"
						value="0.00" disabled="true" />
					<powersi:textfield id="aka151" name="inHospitalDTO.aka151"
						label="起付金额" cssClass="showpay" value="0.00" disabled="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka842" name="inHospitalDTO.bka842"
						label="医院分担" cssClass="showpay" value="0.00" disabled="true" />
					<powersi:textfield id="akb066" name="inHospitalDTO.akb066"
						label="个账支付" cssClass="showpay" value="0.00" disabled="true" />
					<powersi:textfield id="akb067" name="inHospitalDTO.akb067"
						label="现金支付" cssClass="showpay" value="0.00" disabled="true" />
					<powersi:textfield id="todayCollect" label="今收取" cssClass="showpay"
						value="0.00" onkeydown="dealKeyDown(this)" name="todayCollect" />
					<powersi:textfield id="needFind" label="需找回" cssClass="showpay"
						value="0.00" disabled="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		var aaa027 = $("#aaa027").val().substring(0,4);
		$(document).ready(function() {
			$("#querystring").focus();
			//珠海出院登记添加缴费档次显示
			if(aaa027 != null && aaa027 != '4404'){
				$("#bka898_str").remove();
			}
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
			resetOutRegister();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findOutRegisterAaz217ByBka100();
		}

		function findOutRegisterAaz217ByBka100() {
			var outRegisterData = $("#outRegister").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findOutRegisterAaz217.action",
					outRegisterData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
					});
		}

		function keydown(param) {
			if (event.keyCode == "13") {
				var filed_name = param.id;
				if ("bka031_name" == filed_name) {
					return choosebka031();
				}
				if ("bka018" == filed_name) {
					return choosebka018();
				}
			}
		}

		function onchangebka032(para) {
			var bka032 = $("#bka032").val();
			if (powersi.isnull(bka032)) {
				return $("#bka032").focus();
			}
			var bka017 = $("#bka017").val();
			var bka890 = $("#bka890").val();
			if (bka032 < bka017) {
				popupAlert("出院日期不能小于入院日期");
				return $("#bka032").focus();
			}
			if (bka032 > bka890) {
				popupAlert("出院日期不能大于当前日期");
				return $("#bka032").focus();
			}
			return $("#bka043").focus();
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

		function dealKeyDown(para) {
			if (window.event.keyCode == "13") {
				var filed_name = para.id;
				if ("todayCollect" == filed_name) {
					var todayCollect = $("#todayCollect").val();
					var grzfje3 = $("#grzfje3").val();
					if (isNaN(todayCollect)) {
						popupAlert("金额不能为字符");
						return $("#todayCollect").focus();
					}
					if (parseFloat(todayCollect) <= 0) {
						popupAlert("金额不能小于等于0");
						return $("#todayCollect").focus();
					}
					if (powersi.isnull(todayCollect)) {
						todayCollect = 0;
					}
					$("#needFind").val(
							(parseFloat(todayCollect) - parseFloat(grzfje3))
									.toFixed(2));
				}
			}
		}

		/*选择出院疾病诊断*/
		function choosebka031() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#bka031_name").val(retValue.aka121);
								$("#bka031").val(retValue.aka120);
								return $("#bka032").focus();
							} else {
								$("#bka031_name").val("");
								$("#bka031").val("");
								return $("#bka031_name").focus();
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
								$("#bka018").change();
								return $("#bka018").focus();
							} else {
								$("#bka018").val("");
								$("#bka018").change();
								return $("#bka018").focus();
							}
						}
					}, 500, 600);
		}

		/*查询在院登记业务信息*/
		function findOutRegisterAaz217() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					return;
				}
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findOutRegisterAaz217.action?inHospitalDTO.querystring="
								+ querystring, function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							$.each(json.data, function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
						});
			}
		}

		/*费用计算(试算)*/
		function outRegisterTryCalcTreat() {
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
			feeItemReset();
			$("#btTryCalcTreat").prop("disabled", true);
			var dataOutRegister = $("#outRegister").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!outRegisterTryCalcTreat.action",
					dataOutRegister, function(json) {
						$("#btTryCalcTreat").prop("disabled", false);
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

		/*重置界面*/
		function resetOutRegister() {
			$("#btReset").click();
			feeItemReset();
			$("#querystring").prop("disabled", false);
			$("#btTryCalcTreat").prop("disabled", false);
			$("#btSaveOutRegister").prop("disabled", false);
			$("#bkf001").val("");
			$("#bkf002").val("");
			$("#bkf003").val("");
			$("#bkf004").val("");
			$("#bkf001").change();
			$("#bkf002").change();
			$("#bkf003").change();
			$("#bkf004").change();
		}

		/*支付金额重置*/
		function feeItemReset() {
			$("#akc264").val("0.0");
			$("#bka832").val("0.0");
			$("#bka831").val("0.0");
			$("#cash_pay_own").val("0.0");
			$("#aka151").val("0.0");
			$("#bka842").val("0.0");
			$("#akb066").val("0.0");
			$("#akb067").val("0.0");
			$("#todayCollect").val("0.0");
			$("#needFind").val("0.0");
		}

		/*保存出院*/
		function saveOutRegister() {
			if (!checkForm()) {
				return;
			}
			var aac003 = powersi.trim($("#aac003").val());
			if (!confirm("您确认保存[" + aac003 + "]的出院登记吗?")) {
				return;
			}
			$("#btSaveOutRegister").prop("disabled", true);
			var dataOutRegister = $("#outRegister").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!outRegister.action",
					dataOutRegister, function(json) {
						$("#btSaveOutRegister").prop("disabled", false);
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
						afterSaveOutRegister();
					});
		}

		function afterSaveOutRegister() {
			$("#querystring").prop("disabled", false);
			$("#btTryCalcTreat").prop("disabled", true);
			$("#btSaveOutRegister").prop("disabled", true);
		}
	</script>
</body>
</powersi:html>