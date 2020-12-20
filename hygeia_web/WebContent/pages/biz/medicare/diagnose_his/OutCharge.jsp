<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="出院结算" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
<style>
.showpay {
	height: 25px !important;
	font-size: 18px !important;
	text-align: right !important;
}
</style>
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="outCharge" namespace="/inhospital"
		action="InhospitalManagerAction!outCharge.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btTryCalcTreat"    label="费用试算"   onclick="outChargeTryCalcTreat();" key="button_calc0"/>
				<powersi:button id="btCalcOutCharge"   label="费用结算"   onclick="saveOutCharge();" key="button_calc1"/>
				<powersi:button id="btoutChargePrint"  label="打印结算单" onclick="outChargePrint();" key="button_print"/>
				<powersi:button id="btFundStatusQuery" label="基金状态"   onclick="fundStatusQuery();" key="button_query"/>
				<powersi:button id="btoutChargeReset"  label="重置"      onclick="outChargeReset();" key="button_reset"/>
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="0%,10%,10%,10%,15%,10%,10%,10%,25%">
				<powersi:editorlayout-row>
					<td>
						<powersi:codeselect id="caa027" key="caa027" name="inHospitalDTO.caa027" 
							cssClass="select2" codeType="caa027" 
							required="true" headerKey="0" />
					</td>	
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="查询条件" cssClass="select2" 
						list="#{'akc190':'住院号','aac002':'身份证号码','aaz217':'就医登记号' }" />
					<td colspan="2">
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring" title="请输入信息回车"
							placeholder="请输入信息回车！" readonly="false" onkeyup="findAaz217()"
							buttonText="读卡" buttonId="readic_button" buttonDisabled="false" />
					</td>		
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="住院信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden id="akc196" name="inHospitalDTO.akc196" />
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
					<powersi:hidden id="kcd1id" name="inHospitalDTO.kcd1id" />
					<powersi:hidden id="bka001" name="inHospitalDTO.bka001" />
					<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
					<powersi:hidden id="bke549" name="inHospitalDTO.bke549" />
					<powersi:hidden id="bke547" name="inHospitalDTO.bke547" />
					<powersi:hidden id="aae240" name="inHospitalDTO.aae240" />
					<powersi:hidden id="aaz267" name="inHospitalDTO.aaz267" />
					<powersi:hidden id="pcardinfo" name="inHospitalDTO.pcardinfo" />
					<powersi:hidden id="ppayinfo" name="inHospitalDTO.ppayinfo" />
					<powersi:hidden id="akf001" name="inHospitalDTO.akf001" />
					
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="身份证号" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="aaa027_name"
						name="inHospitalDTO.aaa027_name" label="医保分中心" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="akb021" name="inHospitalDTO.akb021"
						label="医院名称" readonly="true" />
					<powersi:textfield id="aka130_name"
						name="inHospitalDTO.aka130_name" label="业务类型" readonly="true" />
					<powersi:textfield id="aae140_name"
						name="inHospitalDTO.aae140_name" label="险种类型" readonly="true" />
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="aac006"
						name="inHospitalDTO.aac006" label="出生日期" readonly="true" />
					<powersi:textfield id="bka035_name" name="inHospitalDTO.bka035_name" 
						label="人员类别" readonly="true" />
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="入院科室" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka022" name="inHospitalDTO.bka022"
						label="入院病区" readonly="true" />
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" readonly="true" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />	
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />
					<powersi:textfield id="bkz101" name="inHospitalDTO.bkz101" 
						label="入院诊断" readonly="true" />
					<powersi:textfield id="ake022_name" name="inHospitalDTO.ake022_name" 
						label="主治医生" readonly="true" />
					<powersi:textfield id="bkz102"
						name="inHospitalDTO.bkz102" label="出院诊断" title="请输入出院诊断"
						onkeydown="keydown(this)" required="true" readonly="true"
						buttonText="选择" buttonId="bkz102_button" buttonDisabled="false"
						onbuttonclick="chooseAkc196()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="ake021" name="inHospitalDTO.ake021"
						label="出院诊断医生" required="true" list="#request.ake022List"
						displayonly="false" cssClass="select2" showValue="false" 
						headerKey="" headerValue="请选择..." />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="出院日期" onchange="onchangeAae031(this)" mask="date"
						required="true" readonly="false" />
					<powersi:codeselect id="bka066" name="inHospitalDTO.bka066"
						label="出院转归情况" codeType="bka066" required="true"
						displayonly="false" cssClass="select2" showValue="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox cssStyle="display:none;">
		<powersi:form id="fundform" namespace="/inhospital"
			action="InhospitalManagerAction!queryFundPay.action">
			<powersi:hidden id="kcd1id_hid" name="inHospitalDTO.kcd1id_hid" />
			<powersi:hidden id="aaz217_hid" name="inHospitalDTO.aaz217_hid" />
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
		<powersi:editorlayout cols="9%,11%,8%,12%,8%,12%,8%,12%,8%,12%">
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
	<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
	<script type="text/javascript">
		var objCard = null;
		var aaa027 = $("#aaa027").val().substring(0,4);
		$(document).ready(function() {
			$("#aae031").val(new Date().format("yyyyMMdd"));
			$("#querystring").focus();
			$("#btoutChargePrint").prop("disabled", true);
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
			findOutChargeaaz217ByBka100();
		}

		/*查询就医登记信息*/
		function findOutChargeaaz217ByBka100() {
			var outChargeData = $("#outCharge").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findOutChargeaaz217.action",
					outChargeData, function(json) {
						if (!checkJSONResultNew(json)) {
							return;
						}
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
						$("#btTryCalcTreat").prop("disabled", false);
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

		/*费用计算(试算)*/
		function outChargeTryCalcTreat(queryFlag) {
			if (!checkForm()) 
				return;
			var kcd1id = powersi.trim($("#kcd1id").val());
			if (powersi.isnull(kcd1id)) 
				return;
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) 
				return;
			
			/*非持卡结算*/
			if ("0" != queryFlag) {
				$("#bke548").val("");
				$("#bke549").val("");
				$("#pcardinfo").val("");
				$("#ppayinfo").val("");
			}
			grid.reset();
			feeItemReset();
			$("#btTryCalcTreat").prop("disabled", true);
			var dataOutCharge = $("#outCharge").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!outChargeTryCalcTreat.action",
					dataOutCharge, function(json) {
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
						/*非持卡结算*/
						if ("0" != queryFlag) {
							queryFundPay("0");
						}
						$("#btCalcOutCharge").prop("disabled", false);
					});
		}

		/*结算收费保存*/
		function saveOutCharge() {
			if (!checkForm()) 
				return;
			var kcd1id = powersi.trim($("#kcd1id").val());
			if (powersi.isnull(kcd1id)) 
				return;
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) 
				return;
			if($("#bke548").val()!='')//刷
			  iDoDebit();
			$("#btCalcOutCharge").prop("disabled", true);
			var dataOutCharge = $("#outCharge").serialize();
			postJSON("${rootPath}/inhospital/InhospitalManagerAction!outCharge.action",
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
						queryFundPay("1");
						afterSaveOutCharge();
					});
		}

		function afterSaveOutCharge() {
			$("#querystring").prop("disabled", false);
			$("#btoutChargePrint").prop("disabled", false);
			$("#btTryCalcTreat").prop("disabled", true);
			$("#btCalcOutCharge").prop("disabled", true);
		}

		function queryFundPay(bka891) {
			if (powersi.isnull(bka891)) {
				return;
			}
			$("#bka891").val(bka891)
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
			$("#btTryCalcTreat").prop("disabled", true);
			$("#btCalcOutCharge").prop("disabled", true);
			$("#ake021").empty();
			$("#select2-chosen-1").text("请选择...");
			$("#select2-chosen-2").text("请选择...");
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
		function outChargePrint() {
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

		/*查询就医登记信息*/
		function findAaz217() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				$("#bke549").val("");
				$("#pcardinfo").val("");
				$("#ppayinfo").val("");
				var querystring = powersi.trim($("#querystring").val());
				var argName = $("#argName").val();
				if (powersi.isnull(querystring)) {
					return;
				}
				$("#querystring").prop("disabled", true);
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findOutChargeAaz217.action",
							{"inHospitalDTO.querystring" : querystring,
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
							$("#btTryCalcTreat").prop("disabled", false);
							selectAke020();
						});
			}
		}
		
		/*选择出院疾病诊断*/
		function chooseAkc196() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#akc196_name").val(retValue.aka121);
								$("#akc196").val(retValue.aka120);
								return $("#aae031").focus();
							} else {
								$("#akc196_name").val("");
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
			var bka017 = $("#bka017").val();
			var bka890 = $("#bka890").val();
			if (aae031 < bka017) {
				popupAlert("出院日期不能小于入院日期");
				return $("#aae031").focus();
			}
			if (aae031 > bka890) {
				popupAlert("出院日期不能大于当前日期");
				return $("#aae031").focus();
			}
			return $("#bka043").focus();
		}
		
		/*加载出院诊断医生*/
		function selectAke020() {
			//入院科室
			var akf001 = $("#akf001").val();
			if (powersi.isnull(akf001)) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadAke022List.action",
					{
						"inHospitalDTO.akf001" : akf001
					}, function(json) {
						if (!checkJSONResultNew(json)) {
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
							$("#ake021").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
		}
	</script>
</body>
</powersi:html>