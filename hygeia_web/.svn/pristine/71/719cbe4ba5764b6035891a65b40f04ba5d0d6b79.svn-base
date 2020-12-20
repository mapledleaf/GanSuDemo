<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="省内异地中途结算" />
<style>
.showpay {
	height: 25px !important;
	font-size: 18px !important;
	text-align: right !important;
}
</style>
</head>
<body>
	<powersi:form id="outCharge" namespace="/inhospital"
		action="MidwayChargeAction!midwayChargeSnyd.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveFee" label="上传费用"
					onclick="outRegisterSnydInputFee()" />
				<powersi:button id="btTryCalcTreat" label="计算费用"
					onclick="outChargeSnydTryCalcTreat()" />
				<powersi:button id="btCalcOutCharge" label="中途结算"
					onclick="saveOutCharge()" />
				<powersi:button id="btoutChargePrint" label="打印结算单"
					onclick="outChargeSnydPrint()" />
				<powersi:button id="btFundStatusQuery" label="基金状态"
					onclick="fundStatusQuery()" />
				<powersi:button id="btoutChargeReset" label="重置"
					onclick="outChargeReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring"
						name="inHospitalDTO.querystring" label="" title="请输入信息回车"
						placeholder="请输入信息回车" readonly="false"
						onkeydown="findOutChargeaaz217()" buttonText="读卡"
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
					<powersi:hidden id="aka130" name="inHospitalDTO.aka130"/>
					<powersi:textfield id="aae140_name"
						name="inHospitalDTO.aae140_name" label="险种类型" readonly="true" />
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
					<powersi:hidden id="bka006" name="inHospitalDTO.bka006" />
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="bka005_name"
						name="inHospitalDTO.bka005_name" label="级  别" readonly="true" />
					<powersi:hidden id="bka005" name="inHospitalDTO.bka005" />
					<powersi:textfield id="bka004_name"
						name="inHospitalDTO.bka004_name" label="人员类别" readonly="true" />
					<powersi:hidden id="bka004" name="inHospitalDTO.bka004" />
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
					<powersi:hidden id="bka026" name="inHospitalDTO.bka026" label="入院诊断编码"/>
					<powersi:textfield id="bka026_name"
						name="inHospitalDTO.bka026_name" label="入院诊断" readonly="true" />
					<powersi:textfield id="bka503_name"
						name="inHospitalDTO.bka503_name" label="医保医师" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="ykc675" name="inHospitalDTO.ykc675" value="3"/>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="bke549" name="inHospitalDTO.bke549" />
		<powersi:hidden id="pcardinfo" name="inHospitalDTO.pcardinfo" />
		<powersi:hidden id="ppayinfo" name="inHospitalDTO.ppayinfo" />
	</powersi:form>
	<powersi:panelbox cssStyle="display:none;">
		<powersi:form id="fundform" namespace="/inhospital"
			action="InhospitalManagerAction!queryFundPay.action">
			<powersi:hidden id="kcd1id_hid" name="inHospitalDTO.kcd1id_hid" />
			<powersi:hidden id="aaz217_hid" name="inHospitalDTO.aaz217_hid" />
			<powersi:hidden id="bka891" name="inHospitalDTO.bka891" />
		</powersi:form>
	</powersi:panelbox>

	<powersi:panelbox key="更改信息">
		<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
			<powersi:editorlayout-row>
				<powersi:textfield id="aaz217_1" name="aaz217_1" label="新就医登记号" readonly="true" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>

	<powersi:panelbox key="基金支付">
		<powersi:datagrid id="grid" formId="fundform" height="85%"
			delayLoad="true" showReload="false" autoWidth="true"
			enabledSort="false" alternatingRow="true" colDraggable="false"
			isScroll="false" totalRender="renderTotal" usePager="false">
			<powersi:datagrid-column name="aad006" display="支付基金" width="33%" />
			<powersi:datagrid-column name="aae019_upper" display="支付金额大写"
				width="33%" />
			<powersi:datagrid-column name="aae019_lower" display="支付金额小写"
				width="34%" />
		</powersi:datagrid>
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
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		$(document).ready(function() {
			$("#querystring").focus();
			$("#btoutChargePrint").prop("disabled", true);
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
			$("#bke549").val("");
			$("#pcardinfo").val("");
			$("#ppayinfo").val("");
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
			outChargeReset();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findOutChargeaaz217SnydByBka100();
		}

		/*查询就医登记信息*/
		function findOutChargeaaz217SnydByBka100() {
			var outRegisterData = $("#outCharge").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findOutRegisteraaz217Snyd.action",
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

		/*写卡*/
		function iDoDebit() {
			if (objCard.object != null) {
				$("#bke548").val("");
				$("#bke549").val("");
				var bke549 = null;// 写卡返回
				var cardInfo = null;
				var payInfo = null;
				cardInfo = $("#pcardinfo").val();
				payInfo = $("#ppayinfo").val();
				if (powersi.isnull(cardInfo)) {
					return;
				}
				if (powersi.isnull(payInfo)) {
					return;
				}
				setTimeout(function() {
					bke549 = objCard.iDoDebit(cardInfo, payInfo);
					$("#bke549").val(bke549);
				}, 0);
			}
		}

		function renderTotal(allData, currentData) {
			var aae019 = 0;
			if (powersi.rows_length(allData['rows'])) {
				$.each(allData['rows'], function(n, row) {
					if (row['__status'] !== "delete") {
						aae019 += parseFloat(row['aae019']);
					}
				});
			}
			var a = [];
			a.push('<div class="divCenter textSuccess">');
			a.push('<span>');
			a.push('基金支付总额：<b>');
			a.push(aae019.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('</div>');
			return a.join('');
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

		/*基金状态*/
		function fundStatusQuery() {
			var aac001 = powersi.trim($("#aac001").val());
			if (powersi.isnull(aac001)) {
				popupAlert("请读卡！");
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
						}
					}, 400, 700);
		}

		/*费用计算(试算)*/
		function outChargeSnydTryCalcTreat() {
			var kcd1id = powersi.trim($("#kcd1id").val());
			if (powersi.isnull(kcd1id)) {
				return;
			}
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			grid.reset();
			feeItemReset();
			$("#btTryCalcTreat").prop("disabled", true);
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!outRegisterSnydTryCalcTreat.action?inHospitalDTO.kcd1id="
							+ kcd1id + "&inHospitalDTO.aaz217=" + aaz217,
					function(json) {
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
							popupAlert(json.message);
						}
						queryFundPay("0");
					});
		}

		/*中途结算*/
		function saveOutCharge() {
			var kcd1id = powersi.trim($("#kcd1id").val());
			if (powersi.isnull(kcd1id)) {
				return;
			}
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			feeItemReset();
			//iReadCardBase();
			//outChargeSnydTryCalcTreat();
			//iDoDebit();
			$("#btCalcOutCharge").prop("disabled", true);
			var dataOutCharge = $("#outCharge").serialize();
			postJSON(
					"${rootPath}/inhospital/MidwayChargeAction!midwayChargeSnyd.action",
					dataOutCharge, function(json) {
						$("#btCalcOutCharge").prop("disabled", false);
						if (!checkJSONResult(json)) {
							return;
						}
						$("#aaz217_1").val(json.data);
						popupInfo(json.message);
						queryFundPay("1");
						afterSaveOutCharge();
					});
		}

		function afterSaveOutCharge() {
			$("#querystring").prop("disabled", false);
			$("#btoutChargePrint").prop("disabled", false);
		}

		function queryFundPay(bka891) {
			if (powersi.isnull(bka891)) {
				return;
			}
			$("#bka891").val(bka891);
			var kcd1id_hid = powersi.trim($("#kcd1id_hid").val());
			if (powersi.isnull(kcd1id_hid)) {
				return;
			}
			var aaz217_hid = powersi.trim($("#aaz217_hid").val());
			if (powersi.isnull(aaz217_hid)) {
				return;
			}
			$("#fundform").submit();
		}

		/*重置界面*/
		function outChargeReset() {
			$("#btReset").click();
			feeItemReset();
			$("#aaz217_hid").val('');
			$("#kcd1id_hid").val('');
			$("#querystring").prop("disabled", false);
			$("#btoutChargePrint").prop("disabled", true);
			$("#btCalcOutCharge").prop("disabled", false);
			grid.reset();
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

		/*打印结算单*/
		function outChargeSnydPrint() {
			var aaz217 = $('#aaz217').val();
			if (powersi.isnull(aaz217)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/SettlementReportAction!settlementReport.action?inHospitalDTO.aaz217="
								+ aaz217,
						onClosed : function() {

						}
					}, 600, 1000);
		}

		function outRegisterSnydInputFee() {
			var kcd1id = powersi.trim($("#kcd1id").val());
			if (powersi.isnull(kcd1id)) {
				return;
			}
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			var aac003 = powersi.trim($("#aac003").val());
			if (powersi.isnull(aac003)) {
				return;
			}
			if (!confirm("您确认要上传" + aac003 + "的费用信息吗?")) {
				return;
			}
			$("#btSaveFee").prop("disabled", true);
			var outRegisterSnydData = $("#outCharge").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!outRegisterSnydInputFee.action",
					outRegisterSnydData, function(json) {
						$("#btSaveFee").prop("disabled", false);
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
					});
		}
		
		/*查询就医登记信息*/
		function findOutChargeaaz217() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					return;
				}
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findOutRegisteraaz217Snyd.action?inHospitalDTO.querystring="
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
	</script>
</body>
</powersi:html>