<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="费用记帐" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
<script type="text/javascript"
	src="${rootPath}/pages/biz/medicare/inhospital/InputFee.js?V201706161"></script>
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
	<powersi:form id="inputFee" namespace="/inhospital"
		action="InhospitalManagerAction!inputFee.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveFee" label="保存费用" onclick="saveFee()" />
				<powersi:button id="btTryCalcTreat" label="试算费用"
					onclick="tryCalcTreat()" />
				<powersi:button id="btSelectFee" label="查询费用" onclick="selectFee()" />
				<powersi:button id="btFundStatusQuery" label="基金状态"
					onclick="fundStatusQuery()" />
				<powersi:button id="btFeeReset" label="重置" onclick="feeReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring"
						name="inHospitalDTO.querystring" label="查询条件" title="请输入信息回车"
						placeholder="请输入信息回车" readonly="false" onkeydown="findaaz217()"
						buttonText="读卡" buttonId="readic_button" buttonDisabled="false"
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
		<powersi:panelbox key="业务信息">
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
					<powersi:hidden id="feeinfo" name="feeinfo" />
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
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
	</powersi:form>
	<powersi:panelbox cssStyle="display:none;">
		<powersi:form id="feeform" namespace="/inhospital"
			action="InhospitalManagerAction!feeSelect.action">
			<powersi:hidden id="kcd1id_hid" name="inHospitalDTO.kcd1id_hid" />
			<powersi:hidden id="aaz217_hid" name="inHospitalDTO.aaz217_hid" />
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="费用明细">
		<powersi:panelbox-toolbar>
			<!-- <powersi:button id="btn_dr" value="导入费用" onclick="drfee()" /> -->
			<powersi:button id="btn_enter" key="button_ok" onclick="insertfee()" />
			<powersi:button id="btn_delete" key="button_delete"
				onclick="deletefee()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout
			cols="5%,7%,2%,12%,5%,15%,5%,8%,5%,8%,5%,8%,5%,10%">
			<powersi:editorlayout-row>
				<powersi:codeselect id="sidx" name="inHospitalDTO.searchType"
					label="查询码" codeType="sidx" onchange="changeSearchType(this)"
					displayonly="false" />
				<powersi:textfield id="stext" name="stext" label="" maxlength="30"
					readonly="false" />
				<powersi:hidden id="bkc194" name="bkc194" />
				<powersi:hidden id="bkc109" name="bkc109" />
				<powersi:hidden id="ake001" name="ake001" />
				<powersi:hidden id="aka065" name="aka065" />
				<powersi:hidden id="ake003" name="ake003" />
				<powersi:hidden id="ake005" name="ake005" />
				<powersi:textfield id="ake006" name="ake006" label="名称"
					disabled="true" />
				<powersi:hidden id="bka052" name="bka052" />
				<powersi:hidden id="bka054" name="bka054" />
				<powersi:hidden id="bka053" name="bka053" />
				<powersi:hidden id="aka057" name="aka057" />
				<powersi:hidden id="ake003_name" name="ake003_name" />
				<powersi:hidden id="aka065_name" name="aka065_name" />
				<powersi:textfield id="bka056" name="bka056" label="单价"
					onkeydown="dealKeyDown(this)" required="true" />
				<powersi:textfield id="bka057" name="bka057" label="数量"
					onkeydown="dealKeyDown(this)" required="true" />
				<powersi:textfield id="bka058" name="bka058" label="金额"
					onkeydown="dealKeyDown(this)" required="true" />
				<powersi:textfield id="bka051" name="bka051" label="日期" mask="date"
					onchange="onchangebka051(this)" readonly="false" required="true" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		<powersi:datagrid id="grid" formId="feeform" delayLoad="true"
			showReload="false" autoWidth="true" enabledSort="false"
			alternatingRow="true" checkbox="true" colDraggable="false"
			totalRender="renderTotal" pageSize="30">
			<powersi:datagrid-column name="bkc194" display="匹配ID" width="1%"
				hide="true" />
			<powersi:datagrid-column name="bkc109" display="异名ID" width="1%"
				hide="true" />
			<powersi:hidden name="aka065" />
			<powersi:datagrid-column name="aka065_name" display="等级" width="6%" />
			<powersi:hidden name="ake003" />
			<powersi:datagrid-column name="ake003_name" display="类别" width="6%" />
			<powersi:datagrid-column name="ake005" display="编码" width="10%" />
			<powersi:datagrid-column name="ake006" display="名称" width="17%" />
			<powersi:datagrid-column name="bka053" display="厂家" width="15%" />
			<powersi:datagrid-column name="bka052" code="bka052" display="剂型"
				width="6%" />
			<powersi:datagrid-column name="bka054" display="规格" width="6%" />
			<powersi:datagrid-column name="bka056" display="单价" width="6%" />
			<powersi:datagrid-column name="bka057" display="数量" width="6%" />
			<powersi:datagrid-column name="bka058" display="金额" width="6%" />
			<powersi:datagrid-column name="bka051" display="日期" width="8%" />
			<powersi:datagrid-column name="aka057" display="先自付比例" width="6%" />
			<powersi:datagrid-column name="bka892" display="保存标识" width="1%"
				hide="true" />
			<powersi:datagrid-column name="ake001" display="中心目录编码" width="1%"
				hide="true" />
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
		/* var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType=py&bka051="
				+ $("#bka051").val(); */
		var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType=py&bka051="
				+ $("#bka051").val()
				+ "&inHospitalDTO.caa027="
				+ $("#caa027").val() + "&type=2";//2套餐
		$(document).ready(function() {
			$("#querystring").focus();
			feeItemReset();
			$("#stext").combogrid({
				minLength : 1,
				autoChoose : false,
				searchIcon : true,
				alternate : true,
				width : "650px",
				url : actionUrl,
				colModel : [ {
					"columnName" : "aka065_name",
					"width" : "8",
					"label" : "等级"
				}, {
					"columnName" : "ake005",
					"width" : "20",
					"label" : "编码"
				}, {
					"columnName" : "ake006",
					"width" : "30",
					"label" : "名称"
				}, {
					"columnName" : "bka056",
					"width" : "12",
					"label" : "单价"
				}, {
					"columnName" : "bka052_name",
					"width" : "12",
					"label" : "剂型"
				}, {
					"columnName" : "bka054",
					"width" : "14",
					"label" : "规格"
				} ],
				select : function(event, ui) {
					var data = ui.item;
					if (!selKzh04(data)) {
						return false;
					}
					$("#bkc194").val(data["bkc194"]);//匹配ID
					$("#bkc109").val(data["bkc109"]);//异名ID
					$("#ake001").val(data["ake001"]);//中心目录编码
					$("#aka065").val(data["aka065"]);//收费项目等级编码(等级)
					$("#aka065_name").val(data["aka065_name"]);//收费项目等级名称
					$("#ake003").val(data["ake003"]);//三大目录类别编码(类别)
					$("#ake003_name").val(data["ake003_name"]);//三大目录类别名称
					$("#ake005").val(data["ake005"]);//医疗机构三大目录编码(编码)
					$("#ake006").val(data["ake006"]);//医疗机构三大目录名称(名称)
					$("#bka052").val(data["bka052"]);//医院目录剂型(剂型)
					$("#bka054").val(data["bka054"]);//医院目录规格(规格)
					$("#bka053").val(data["bka053"]);//医院目录厂家(厂家)
					$("#bka056").val(data["bka056"]);//医院目录单价(单价)
					$("#aka057").val(data["aka057"]);//先自付比例
					$("#bka056").focus();
					return false;
				}
			});
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
			feeReset();
			iReadCardBase();
			var bke548 = $("#bke548").val();
			if (powersi.isnull(bke548)) {
				return;
			}
			findaaz217ByBka100();
		}

		function findaaz217ByBka100() {
			var inputFeeData = $("#inputFee").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findaaz217.action",
					inputFeeData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
						$("#btSaveFee").prop("disabled", false);
					});
		}

		function renderTotal(allData, currentData) {
			var money = 0;
			var price = 0;
			var usage = 0;
			var kinds = {};
			if (powersi.rows_length(allData['rows'])) {
				$.each(allData['rows'], function(n, row) {
					if (row['__status'] !== "delete") {
						price += parseFloat(row['bka056']);
						usage += parseFloat(row['bka057']);
						money += parseFloat(row['bka058']);
						kinds[row['ake005']] = '';
					}
				});
			}
			var a = [];
			a.push('<div class="divCenter textSuccess">');
			a.push('<span>');
			a.push('总的金额：<b>');
			a.push(money.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('&nbsp;&nbsp;');
			a.push('<span>');
			a.push('总的用量：<b>');
			a.push(usage.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('&nbsp;&nbsp;');
			a.push('<span>');
			a.push('共 <b>');
			a.push(powersi.length(kinds));
			a.push('</b> 项费用');
			a.push('</span>');
			a.push('</div>');
			return a.join('');
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

		/*查询就医登记信息*/
		function findaaz217() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					return;
				}
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findaaz217.action?inHospitalDTO.querystring="
								+ querystring, function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							$.each(json.data, function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
							$("#btSaveFee").prop("disabled", false);
						});
			}
		}

		/*查询已保存的费用*/
		function selectFee() {
			var kcd1id_hid = powersi.trim($("#kcd1id_hid").val());
			if (powersi.isnull(kcd1id_hid)) {
				return;
			}
			var aaz217_hid = powersi.trim($("#aaz217_hid").val());
			if (powersi.isnull(aaz217_hid)) {
				return;
			}
			
			var aac002 = $("#aac002").val();
			var bka017 = $("#bka017").val();
			var kcd1id = $("#kcd1id").val();

			postJSON(
					"${rootPath}/comminter/DetermineFeeAction!queryAac002.action?inHospitalDTO.aac002="
							+ aac002_1
							+ "&inHospitalDTO.bka017="
							+ bka017_1
							+ "&inHospitalDTO.kcd1id="
							+ kcd1id_1,
					function(json) {
						if (json.data.aac002List.length > 0) {
							popupDialog(
									{
										url : "${rootPath}/pages/biz/medicare/comminter/determineFee.jsp?inHospitalDTO.aac002="
												+ $(
														"#aac002")
														.val()
												+ "&inHospitalDTO.kcd1id="
												+ $(
														"#kcd1id")
														.val()
												+ "&inHospitalDTO.bka017="
												+ $(
														"#bka017")
														.val(),
										onClosed : function() {
										}
									},
									screen.height,
									screen.width);
						}
					});
			$("#feeform").submit();
		}

		/*重置界面*/
		function feeReset() {
			$("#btReset").click();
			$("#kcd1id_hid").val("");
			$("#aaz217_hid").val("");
			grid.reset();
			feeItemReset();
			$("#btSaveFee").prop("disabled", false);
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

		/*保存费用*/
		function saveFee() {
			if (grid.getRowsCount() <= 0) {
				popupAlert("请先录入费用！");
				return;
			}
			var flag = false;
			var feeinfo = grid.getData();
			/*获取新增费用*/
			var feeinfoAdd = grid.getAdded();
			/*页面处理重复保存*/
			if (feeinfoAdd != '[]' && feeinfoAdd != '') {
				feeinfo = feeinfoAdd;
			} else {
				bka892 = feeinfo[0]['bka892'];
				if (bka892 == 1) {
					flag = true;
				}
			}
			if (flag) {
				popupAlert("不存在需要保存的费用明细！");
				return;
			}
			feeinfo = powersi.tostring(feeinfo);
			$("#feeinfo").val(encodeURI(feeinfo));
			$("#btSaveFee").prop("disabled", true);
			var saveItemData = $("#inputFee").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!inputFee.action",
					saveItemData, importFeeSaveInfo);
		}

		function importFeeSaveInfo(json) {
			$("#btSaveFee").prop("disabled", false);
			if (!checkJSONResult(json)) {
				return;
			}
			$("#btSaveFee").prop("disabled", true);
			$("#btTryCalcTreat").prop("disabled", false);
			if (!powersi.isnull(json.message)) {
				popupInfo(json.message);
			}
		}

		/*费用计算(试算)*/
		function tryCalcTreat() {
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
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!tryCalcTreat.action?inHospitalDTO.kcd1id="
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
							popupInfo(json.message);
						}
					});
		}

		function changeSearchType(sidx) {
			actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
					+ sidx.value + "&bka051=" + $("#bka051").val();
			$("#stext").combogrid("option", "url", actionUrl);
		}

		function onchangebka051(para) {
			var bka051 = $("#bka051").val();
			var bka017 = $("#bka017").val();
			if (bka051 < bka017) {
				popupAlert("费用日期不能小于入院日期！");
				return $("#bka051").focus();
			}
			actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
					+ $("#sidx").val() + "&bka051=" + $("#bka051").val();
			$("#stext").combogrid("option", "url", actionUrl);
		}

		function dealKeyDown(para) {
			if (window.event.keyCode == "13") {
				var filed_name = para.id;
				if ("bka056" == filed_name) {
					var bka056 = $("#bka056").val();
					if (isNaN(bka056)) {
						popupAlert("单价不能为字符！");
						return $("#bka056").focus();
					}
					if (!powersi.isnull(bka056)) {
						if (parseFloat(bka056) <= 0) {
							popupAlert("单价不能小于等于0！");
							return $("#bka056").focus();
						}
					}
					return $("#bka057").focus();
				}
				if ("bka057" == filed_name) {
					var bka056 = $("#bka056").val();
					var bka057 = $("#bka057").val();
					if (isNaN(bka057)) {
						popupAlert("数量不能为字符！");
						return $("#bka057").focus();
					}
					if (!powersi.isnull(bka057)) {
						if (parseFloat(bka057) == 0) {
							popupAlert("数量不能等于0！");
							return $("#bka057").focus();
						}
					}
					if (!powersi.isnull(bka057) && !powersi.isnull(bka056)) {
						$("#bka058").val(
								(parseFloat(bka057) * parseFloat(bka056))
										.toFixed(2));
					}
					return $("#bka058").focus();
				}
				if ("bka058" == filed_name) {
					var bka058 = $("#bka058").val();
					if (isNaN(bka058)) {
						popupAlert("金额不能为字符！");
						return $("#bka058").focus();
					}
					if (!powersi.isnull(bka058)) {
						if (parseFloat(bka058) == 0) {
							popupAlert("金额不能等于0！");
							return $("#bka058").focus();
						}
					}
					insertfee();
				}
				if ("todayCollect" == filed_name) {
					var todayCollect = $("#todayCollect").val();
					var akb067 = $("#akb067").val();
					if (isNaN(todayCollect)) {
						popupAlert("今收取金额不能为字符！");
						return $("#todayCollect").focus();
					}
					if (parseFloat(todayCollect) <= 0) {
						popupAlert("今收取金额不能小于等于0！");
						return $("#todayCollect").focus();
					}
					if (powersi.isnull(todayCollect)) {
						todayCollect = 0;
					}
					$("#needFind").val(
							(parseFloat(todayCollect) - parseFloat(akb067))
									.toFixed(2));
				}
			}
		}

		function drfee() {
			popupDialog(
					{
						url : "${rootPath}/pages/biz/medicare/comm/InHospitalFeeDr.jsp",
						onClosed : function() {

						}
					}, 200, 600);
		}

		function insertfee() {
			if (powersi.isnull($("#ake006").val())) {
				popupAlert("未录入药品！");
				return;
			}
			if (powersi.isnull($("#bka058").val())) {
				popupAlert("未录入金额！");
				return;
			}
			if (parseFloat($("#bka058").val()) == 0) {
				popupAlert("金额不能等于0！");
			}
			if (isNaN($("#bka058").val()) * 1) {
				popupAlert("金额输入有误！");
				$("#bka058").focus();
				return;
			}
			var bkc194 = $("#bkc194").val();
			var bkc109 = $("#bkc109").val();
			var ake001 = $("#ake001").val();
			if (powersi.isnull($("#aka065").val())) {
				popupAlert("收费项目等级不能为空！");
				return;
			}
			var aka065_name = $("#aka065_name").val();
			var ake003_name = $("#ake003_name").val();
			var aka065 = $("#aka065").val();
			var ake003 = $("#ake003").val();
			var ake005 = $("#ake005").val();
			var ake006 = $("#ake006").val();
			var bka053 = $("#bka053").val();
			var bka052 = $("#bka052").val();
			var bka054 = $("#bka054").val();
			var bka056 = parseFloat($("#bka056").val());
			var bka057 = parseFloat($("#bka057").val());
			var input_ver = (parseFloat($("#bka058").val()) * 1.0000)
					.toFixed(2);
			var bka051 = $("#bka051").val();
			var aka057 = $("#aka057").val();
			var bka892 = "0";
			var jsonFee = {
				"ake003_name" : ake003_name,
				"aka065_name" : aka065_name,
				"aka065" : aka065,
				"ake003" : ake003,
				"bkc194" : bkc194,
				"bkc109" : bkc109,
				"ake001" : ake001,
				"ake005" : ake005,
				"ake006" : ake006,
				"bka053" : bka053,
				"bka052" : bka052,
				"bka054" : bka054,
				"bka056" : bka056,
				"bka057" : bka057,
				"bka058" : input_ver,
				"bka051" : bka051,
				"aka057" : aka057,
				"bka892" : bka892
			};
			grid.add(jsonFee);
			$("#stext").focus();
			$("#stext").val("");
			$("#ake003_name").val("");
			$("#aka065_name").val("");
			$("#ake003").val("");
			$("#aka065").val("");
			$("#bkc194").val("");
			$("#bkc109").val("");
			$("#ake001").val("");
			$("#ake005").val("");
			$("#ake006").val("");
			$("#bka053").val("");
			$("#bka052").val("");
			$("#bka054").val("");
			$("#bka056").val("");
			$("#bka057").val("");
			$("#bka058").val("");
			$("#aka057").val("");
		}

		function deletefee() {
			if (typeof (grid.getSelectedRows()) == "undefined"
					|| grid.getSelectedRows() == null
					|| grid.getSelectedRows() == "") {
				popupAlert("请选择要删除的费用！");
				return;
			}
			if (confirm("是否删除已选择的费用？") == true) {
				grid.deleteSelectedRow();
			}
		}
	</script>
</body>
</powersi:html>