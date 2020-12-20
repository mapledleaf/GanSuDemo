<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="异地就医出院结算" />
<script type="text/javascript"
	src="${rootPath}/resource/js/clinicUtils.js"></script>
<style>
.showpay {
	height: 25px !important;
	font-size: 18px !important;
	text-align: right !important;
}
</style>
</head>
<body>
	<powersi:form id="outChargeSnyd" namespace="/inhospital"
		action="InhospitalManagerAction!outChargeSnyd.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btTryCalcTreat" label="试算费用"
 					onclick="outChargeYdjyTryCalcTreat()" />
				<powersi:button id="btCalcOutCharge" label="结算收费"
					onclick="saveOutChargeYdjy()" />
				<powersi:button id="btoutChargeSnydPrint" label="打印结算单"
					onclick="outChargeSnydPrint()" />
				<powersi:button id="btoutChargeSnydReset" label="重置"
					onclick="outChargeYdjyReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,15%,8%,12%,8%,12%,10%,15%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="" cssClass="select2" 
						list="#{'aac002':'社会保障号','aaz217':'就医登记号' }" />
					<td>
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring" title="请输入信息回车"
						placeholder="请输入信息回车！" readonly="false" onkeyup="findOutChargeAaz217Ydjy()"
						buttonText="读卡" buttonId="readic_button" buttonDisabled="false" />
					</td>
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="住院信息">
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
				<powersi:hidden id="kcd1id" name="inHospitalDTO.kcd1id" />
				<powersi:hidden id="bka001" name="inHospitalDTO.bka001" />
				<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
				<powersi:hidden id="bke549" name="inHospitalDTO.bke549" />
				<powersi:hidden id="pcardinfo" name="inHospitalDTO.pcardinfo" />
				<powersi:hidden id="ppayinfo" name="inHospitalDTO.ppayinfo" />
				<powersi:hidden id="akc193" name="inHospitalDTO.akc193" />
				<powersi:hidden id="akc196" name="inHospitalDTO.akc196" />
				<powersi:hidden id="outchargeid" value="1"/><!-- 住院结算读卡返回aac002 -->
				<powersi:editorlayout-row>
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="akb021" name="inHospitalDTO.akb021"
						label="医院名称" readonly="true" />
					<powersi:textfield id="aka130_name"
						name="inHospitalDTO.aka130_name" label="业务类型" readonly="true" />
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="bac001_name"
						name="inHospitalDTO.bac001_name" label="公务员级别" readonly="true" />
					<powersi:textfield id="bka035_name"
						name="inHospitalDTO.bka035_name" label="人员类别" readonly="true" />
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="入院科室" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka021" name="inHospitalDTO.bka021"
						label="入院病区" readonly="true" />
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" readonly="true" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bkz101"
						name="inHospitalDTO.bkz101" label="入院诊断" readonly="true" />
					<powersi:textfield id="bkz102" name="inHospitalDTO.bkz102"
						label="出院诊断" title="请输入出院诊断" onkeydown="keydown(this)"
						required="true" readonly="true" buttonText="选择"
						buttonId="bkz102_button" buttonDisabled="false"
						onbuttonclick="chooseAkc196()" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="出院日期" onchange="onchangeAae031(this)" mask="date"
						required="true" readonly="false" />
					<powersi:codeselect id="bka066" name="inHospitalDTO.bka066"
						label="出院转归情况" codeType="bka066" required="true"
						displayonly="false" cssClass="select2" showValue="false" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox cssStyle="display:none;">
		<powersi:form id="fundform" namespace="/inhospital"
			action="InhospitalManagerAction!queryFundPay.action">
			<powersi:hidden id="aae139" name="inHospitalDTO.aae139" value="1"/>
			<powersi:hidden id="aaz217_hid" name="inHospitalDTO.aaz217" />
<%-- 			<powersi:hidden id="akc252" name="inHospitalDTO.akc252" /> --%>
			<powersi:hidden id="bka006_hid" name="inHospitalDTO.bka006" />
			<powersi:hidden id="akc196_hid" name="inHospitalDTO.akc196" />
			<powersi:hidden id="aae031_hid" name="inHospitalDTO.aae031" />
			<powersi:hidden id="bke550" name="inHospitalDTO.bke550" />
			<powersi:hidden id="aaz500" name="inHospitalDTO.aaz500" />
			<powersi:hidden id="bka891" name="inHospitalDTO.bka891" />
		</powersi:form>
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
					label="医院垫付" cssClass="showpay" value="0.00" disabled="true" />
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
			$("#btoutChargeSnydPrint").prop("disabled", true);
			$("#btTryCalcTreat").prop("disabled", true);
			$("#btCalcOutCharge").prop("disabled", true);
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

		/*查询就医登记信息*/
		function findOutChargeaaz217SnydByBka100() {
			var outChargeData = $("#outChargeSnyd").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findOutChargeAaz217Ydjy.action",
					outChargeData, function(json) {
						if (!checkJSONResultNew(json)) {
							return;
						}
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
						var aae030 = $("#aae030").val();
						if(aae030.length>=10){
							$("#aae030").val(aae030.replace(/-/g,"").substring(0, 8));
						}
						$("#btTryCalcTreat").prop("disabled", false);
						$("#btCalcOutCharge").prop("disabled", false);
					});
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

		
		/*费用计算(试算)*/
		function outChargeYdjyTryCalcTreat() {
			if (!checkForm()) {
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
					"${rootPath}/inhospital/InhospitalManagerAction!outChargeYdjyTryCalcTreat.action?inHospitalDTO.aaz217=" + aaz217
							+ "&inHospitalDTO.akc196="+$("#akc196").val() + "&inHospitalDTO.aae031="+$("#aae031").val(),
					function(json) {
						$("#btTryCalcTreat").prop("disabled", false);
						if (!checkJSONResultNew(json)) {
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
						queryFundPay("0");
						$("#btCalcOutCharge").prop("disabled", false);
					});
		}

		/*结算收费保存*/
		function saveOutChargeYdjy() {
			if (!checkForm()) {
				return;
			}
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			var aaz500 = powersi.trim($("#aaz500").val());
			var bke550 = powersi.trim($("#bke550").val());
			feeItemReset();
			queryFundPay("1");
			$("#btCalcOutCharge").prop("disabled", true);
			var dataOutCharge = $("#outChargeSnyd").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!outChargeYdjy.action?inHospitalDTO.aaz500=" + aaz500+"&inHospitalDTO.bke550="+bke550,
					dataOutCharge, function(json) {
						$("#btCalcOutCharge").prop("disabled", false);
						if (!checkJSONResultNew(json)) {
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
						
						afterSaveOutCharge();
					});
		}

		function afterSaveOutCharge() {
			$("#querystring").prop("disabled", false);
			$("#btoutChargeSnydPrint").prop("disabled", false);
			$("#btTryCalcTreat").prop("disabled", true);
			$("#btCalcOutCharge").prop("disabled", true);
		}

		function queryFundPay(bka891) {
			if (powersi.isnull(bka891)) {
				return;
			}
			$("#bka891").val(bka891)
			$("#aaz217_hid").val($("#aaz217").val());
			$("#bka006_hid").val($("#bka006").val());
			$("#akc196_hid").val($("#akc196").val());
			$("#aae031_hid").val($("#aae031").val());
			$("#fundform").submit();
		}

		/*重置界面*/
		function outChargeYdjyReset() {
			$("#btReset").click();
			$("#bka066").val('');
			$("#bka066").change();
			feeItemReset();
			$("#aaz217_hid").val('');
			$("#kcd1id_hid").val('');
			$("#querystring").prop("disabled", false);
			$("#btoutChargeSnydPrint").prop("disabled", true);
			$("#btTryCalcTreat").prop("disabled", true);
			$("#btCalcOutCharge").prop("disabled", true);
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
						url : "${rootPath}/common/SettlementReportAction!settlementReportYdjy.action"
							+"?bizQueryDTO.aae030=" + $("#aae030").val()
							+"&bizQueryDTO.aae031=" + $("#aae031").val()
							+"&bizQueryDTO.aaz217=" + aaz217,
						onClosed : function() {

						}
					}, 600, 1000);
		}

		/*查询就医登记信息*/
		function findOutChargeAaz217Ydjy() {
			if (window.event.keyCode == 13) {
				findOutChargeAaz217Byaac002Ydjy();
			}
		}
		
		function readIcCard(){		
			findOutChargeAaz217Byaac002Ydjy();
		} 
		
		function findOutChargeAaz217Byaac002Ydjy(){
			//$("#bke548").val("");
			$("#bke549").val("");
			$("#pcardinfo").val("");
			$("#ppayinfo").val("");
			var querystring = powersi.trim($("#querystring").val());
			var argName = $("#argName").val();
			if (powersi.isnull(querystring)) {
				return;
			}
			outChargeYdjyReset();
			$("#querystring").val(querystring);
			$("#argName").val(argName);
			$("#argName").change();
			$("#querystring").prop("disabled", true);
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findOutChargeAaz217Ydjy.action",
							{
								"inHospitalDTO.querystring" : querystring,
								"inHospitalDTO.argName" : argName
							}, function(json) {
						$("#querystring").prop("disabled", false);
						if (!checkJSONResultNew(json)) {
							return;
						}
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
						var aae030 = $("#aae030").val();
						if(aae030.length>=10){
							$("#aae030").val(aae030.replace(/-/g,"").substring(0, 8));
						}
						$("#btTryCalcTreat").prop("disabled", false);
						$("#btCalcOutCharge").prop("disabled", false);
					});
		}
		
		/*选择出院疾病诊断*/
		function chooseAkc196() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#bkz102").val(retValue.aka121);
								$("#akc196").val(retValue.aka120);
								return $("#aae031").focus();
							} else {
								$("#bkz102").val("");
								$("#akc196").val("");
								return $("#akc196_name").focus();
							}
						}
					}, 500, 600);
		}

		function onchangeAae031(para) {
			var aae031 = $("#aae031").val();
			if (powersi.isnull(aae031)) {
				return $("#aae031").focus();
			}
			var aae030 = $("#aae030").val();
			var bka890 = $("#bka890").val();
			if (aae031 < aae030) {
				popupAlert("出院日期不能小于入院日期");
				return $("#aae031").focus();
			}
			if (aae031 > bka890) {
				popupAlert("出院日期不能大于当前日期");
				return $("#aae031").focus();
			}
		}

	</script>
</body>
</powersi:html>