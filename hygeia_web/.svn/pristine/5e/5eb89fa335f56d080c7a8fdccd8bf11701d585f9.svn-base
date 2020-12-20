<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
	<%
		// NTS20052100118 业务系统增加超范围用药标识传输 杨世明 20200521
	%>
<head>
<powersi:head title="异地就医费用记帐" />
<script type="text/javascript"
	src="${rootPath}/pages/biz/medicare/inhospital/InputFee.js?V201706161"></script>
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
<%@ include file="/pages/util/header/combogrid.jsp"%>
<style>
.showpay {
	height: 30px !important;
	font-size: 20px !important;
	text-align: right !important;
}
</style>
</head>
<body>
	<powersi:form id="inputFeeYdjy" namespace="/inhospital"
		action="InhospitalManagerAction!inputFeeYdjy.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSelectFee" label="查询费用" onclick="selectFeeYdjy()" />
				<powersi:button id="btSaveFee" label="保存费用" onclick="saveFeeYdjy()" />
				<powersi:button id="btTryCalcTreat" label="试算费用"
 					onclick="tryCalcTreatYdjy()" />
				<powersi:button id="btFeeReset" label="重置" onclick="feeReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,15%,8%,12%,8%,12%,10%,15%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="" cssClass="select2" 
						list="#{'aac002':'社会保障号','aaz217':'就医登记号','akc190':'住院号' }" />
					<td>
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring" title="请输入信息回车"
						placeholder="请输入信息回车！" readonly="false" onkeyup="findAaz217Ydjy()"
						buttonDisabled="false" />
					</td>
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />	
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="业务信息">
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:hidden id="kcd1id" name="inHospitalDTO.kcd1id" />
				<powersi:hidden id="bka001" name="inHospitalDTO.bka001" />
				<powersi:hidden id="bka035" name="inHospitalDTO.bka035" />
				<powersi:hidden id="feeinfo" name="feeinfo" />
				<powersi:hidden id="caa027" name="inHospitalDTO.caa027" value="4303-zg"/>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
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
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="bac001_name"
						name="inHospitalDTO.bac001_name" label="公务员级别" readonly="true" />
					<powersi:textfield id="bka035_name"
						name="inHospitalDTO.bka035_name" label="人员类别" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="入院科室" readonly="true" />
					<powersi:textfield id="bka022" name="inHospitalDTO.bka022"
						label="入院病区" readonly="true" />
					<powersi:textfield id="ake020" name="inHospitalDTO.ake020"
						label="床位号" readonly="true" />
					<powersi:textfield id="ake022_name"
						name="inHospitalDTO.ake022_name" label="医保医师" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" readonly="true" />
					<powersi:textfield id="bkz101"
						name="inHospitalDTO.bkz101" label="入院诊断" readonly="true" />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="bka012" name="inHospitalDTO.bka012" />
	</powersi:form>
	<powersi:panelbox cssStyle="display:none;">
		<powersi:form id="feeformYdjy" namespace="/inhospital"
			action="InhospitalManagerAction!feeSelectYdjy.action">
			<powersi:hidden id="kcd1id_hid" name="inHospitalDTO.kcd1id_hid" />
			<powersi:hidden id="aaz217_hid" name="inHospitalDTO.aaz217_hid" />
		</powersi:form>
	</powersi:panelbox>
<!-- 	<div id="divTab2" class="row"> -->
<!-- 		<div class="col-8"> -->
			<powersi:panelbox key="费用明细">
				<powersi:panelbox-toolbar>
					<powersi:button id="btn_enter"  key="button_ok"     label="确定" onclick="insertfee()" />
					<powersi:button id="btn_delete" key="button_delete" label="删除" onclick="deletefee()" />
				</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="4%,7%,1%,10%,4%,8%,5%,8%,5%,8%,6%,8%,8%,5%,4%,9%">
					<powersi:editorlayout-row>
						<powersi:codeselect id="sidx" name="inHospitalDTO.searchType"
							label="查询码" codeType="sidx" onchange="changeSearchType(this)"
							displayonly="false" />
						<powersi:textfield id="stext" name="stext" label="" maxlength="30"
							readonly="false" />
						<powersi:hidden id="bkm017" name="bkm017" />	
						<powersi:hidden id="bkc194" name="bkc194" />
						<powersi:hidden id="bkc109" name="bkc109" />
						<powersi:hidden id="ake001" name="ake001" />
						<powersi:hidden id="aka063" name="aka063" />
						<powersi:hidden id="aka065" name="aka065" />
						<powersi:hidden id="ake003" name="ake003" />
						<powersi:hidden id="ake005" name="ake005" />
						<powersi:hidden id="aka070" name="aka070" />
						<powersi:hidden id="bka510" name="inHospitalDTO.bka510" />
						<powersi:hidden id="aka074" name="aka074" />
						<powersi:hidden id="bka073" name="bka073" />
						<powersi:hidden id="aka057" name="aka057" />
						<powersi:hidden id="ake003_name" name="ake003_name" />
						<powersi:hidden id="aka065_name" name="aka065_name" />
						
						<powersi:textfield id="ake006" name="ake006" label="名称"
							disabled="true" />
						<powersi:textfield id="bka040" name="bka040" label="单价"
							onkeydown="dealKeyDown(this)" required="true" />
						<powersi:textfield id="akc226" name="akc226" label="数量"
							onkeydown="dealKeyDown(this)" required="true" />
						<powersi:textfield id="aae019" name="aae019" label="金额"
							onkeydown="dealKeyDown(this)" required="true" />
						<td>
							<label for="bkz103" class="textLabel">
								<span class="required requiredLabel">*</span>
								<span>超范围用药</span>
							</label>
						</td>
						<td>
							<select id="bkz103" name="bkz103" onkeydown="dealKeyDown(this)">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
						<powersi:textfield id="ake007" name="ake007" label="日期" mask="date"
							onchange="onchangeAke007(this)" readonly="false" required="true" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
<%-- 				<powersi:panelbox key="查询费用条件"> --%>
<%-- 					<powersi:form id="feeform" namespace="/inhospital" action="InhospitalManagerAction!feeSelect.action"> --%>
<%-- 						<powersi:editorlayout cols="5%,8%,8%,11%,8%,11%,5%,10%,8%,11%,5%,10%"> --%>
<%-- 							<powersi:editorlayout-row> --%>
<%-- 								<powersi:textfield id="ake006" name="inHospitalDTO.ake006" --%>
<%-- 									label="名称" /> --%>
<%-- 								<powersi:textfield id="aae030_1" name="inHospitalDTO.aae030" --%>
<%-- 									label="开始日期" mask="date" /> --%>
<%-- 								<powersi:textfield id="aae031" name="inHospitalDTO.aae031" --%>
<%-- 									label="结束日期" mask="date" /> --%>
<%-- 								<powersi:codeselect id="aka063" name="inHospitalDTO.aka063" --%>
<%-- 									label="类型" cssClass="select1" codeType="aka063" --%>
<%-- 									required="false" displayonly="false" showValue="true" --%>
<%-- 									headerKey="" headerValue="" /> --%>
<%-- 							    <powersi:codeselect id="bka075" name="inHospitalDTO.bka075" --%>
<%-- 								    label="处方医师" cssClass="select2" list="#request.bka075List" --%>
<%-- 								    headerKey="" headerValue="请选择..." showValue="false"  onchange="getvalue();"/> --%>
<!-- 								<td class="tdLabel">排序</td> -->
<!-- 							    <td> <select id="sorder" name="inHospitalDTO.tipsinfo" class="select"> -->
<!-- 							          <option value="by_bka051_1">费用倒序</option> -->
<!-- 							          <option value="by_bka051_2">费用正序</option> -->
<!-- 							        </select> </td> -->
<%-- 							</powersi:editorlayout-row> --%>
<%-- 						</powersi:editorlayout>			 --%>
<%-- 						<powersi:hidden id="kcd1id_hid" name="inHospitalDTO.kcd1id_hid" /> --%>
<%-- 						<powersi:hidden id="aaz217_hid" name="inHospitalDTO.aaz217_hid" /> --%>
<%-- 					</powersi:form> --%>
<%-- 				</powersi:panelbox> --%>
				<powersi:datagrid id="grid" formId="feeformYdjy" delayLoad="true" showReload="false" 
					autoWidth="true" enabledSort="false" alternatingRow="true" checkbox="true" height="300"
					colDraggable="false" totalRender="renderTotal" pageSize="30">
					<powersi:hidden name="aka065" />
					<powersi:hidden name="ake003" />
					<powersi:hidden name="isExist" value="1"/>
					<powersi:datagrid-column name="ake005" display="编码" width="10%" />
					<powersi:datagrid-column name="ake006" display="名称" width="17%" />
					<powersi:datagrid-column name="aka065_name" display="等级" width="5%" />
					<powersi:datagrid-column name="bka073" display="厂家" width="14%" />
					<powersi:datagrid-column name="aka070" display="剂型" width="6%" code="aka070" />
					<powersi:datagrid-column name="aka074" display="规格" width="6%" />
					<powersi:datagrid-column name="bka040" display="单价" width="6%" />
					<powersi:datagrid-column name="akc226" display="数量" width="6%" />
					<powersi:datagrid-column name="aae019" display="金额" width="6%" />
					<powersi:datagrid-column name="bkz103" display="超范围用药" width="6%" render="renderBkz103"/>
					<powersi:datagrid-column name="ake007" display="日期" width="10%" />
					<powersi:datagrid-column name="aka057" display="先自付比例" width="6%" />
					<powersi:datagrid-column name="bka892" display="保存标识" hide="true" />
					<powersi:datagrid-column name="ake001" display="中心目录编码" hide="true" />
					<powersi:datagrid-column name="bkm017" display="药监本位码" hide="true"/>
					<powersi:datagrid-column name="bkc194" display="匹配ID" hide="true" />
					<powersi:datagrid-column name="bkc109" display="异名ID" hide="true" />
					<powersi:datagrid-column name="aaz213" display="费用序列号" hide="true" />
					<powersi:datagrid-column name="bka062" display="退费对应费用序列号" hide="true" />
					<powersi:datagrid-column name="bka059" display="退费金额" hide="true" />
					<%-- <powersi:datagrid-column name="aaz231" display="社保三大目录ID" hide="true" />
					<powersi:datagrid-column name="aaz277" display="医疗机构三大目录ID" hide="true" /> --%>
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
		var aaa027 = $("#aaa027").val().substring(0,4);

		var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType=py&ake007="
				+ $("#ake007").val()
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
				colModel : [ {'columnName' : 'rownumber', 'width' : '4', 'label' : '#'}, {
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
					"columnName" : "bka040",
					"width" : "12",
					"label" : "单价"
				}, {
					"columnName" : "aka070_name",
					"width" : "12",
					"label" : "剂型"
				}, {
					"columnName" : "aka074",
					"width" : "14",
					"label" : "规格"
				} ],
				select : function(event, ui) {
					var data = ui.item;
					if (!selKzh04(data)) {
						return false;
					}
					$("#bkm017").val(data["bkm017"]);//药监本位码
					$("#bkc194").val(data["bkc194"]);//匹配ID
					$("#bkc109").val(data["bkc109"]);//异名ID
					$("#ake001").val(data["ake001"]);//中心目录编码
					$("#aka063").val(data["aka063"]);//
					$("#aka065").val(data["aka065"]);//收费项目等级编码(等级)
					$("#aka065_name").val(data["aka065_name"]);//收费项目等级名称
					$("#ake003").val(data["ake003"]);//三大目录类别编码(类别)
					$("#ake003_name").val(data["ake003_name"]);//三大目录类别名称
					$("#ake005").val(data["ake005"]);//医疗机构三大目录编码(编码)
					$("#ake006").val(data["ake006"]);//医疗机构三大目录名称(名称)
					$("#aka070").val(data["aka070"]);//医院目录剂型(剂型)
					$("#aka074").val(data["aka074"]);//医院目录规格(规格)
					$("#bka073").val(data["bka073"]);//医院目录厂家(厂家)
					$("#bka040").val(data["bka040"]);//医院目录单价(单价)
					$("#aka057").val(data["aka057"]);//先自付比例
					$("#bka040").focus();
					return false;
				}
			});
		});

		function findaaz217SnydByBka100() {
			var inputFeeData = $("#inputFeeYdjy").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAaz217Ydjy.action",
					inputFeeData, function(json) {
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
						price += parseFloat(row['bka040']);
						usage += parseFloat(row['akc226']);
						money += parseFloat(row['aae019']);
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

		/*查询就医登记信息*/
		function findAaz217Ydjy() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				var argName = $("#argName").val();
				feeReset();
				$("#querystring").val(querystring);
				$("#argName").val(argName);
				$("#argName").change();
				if (powersi.isnull(querystring)) {
					return;
				}
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findAaz217Ydjy.action",
							{"inHospitalDTO.querystring" : querystring,
						     "inHospitalDTO.argName" : argName
							}, function(json) {
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
							$("#btSaveFee").prop("disabled", false);
						});
			}
		}

		/*查询已保存的费用*/
		function selectFeeYdjy() {
			var aaz217_hid = powersi.trim($("#aaz217_hid").val());
			if (powersi.isnull(aaz217_hid)) {
				return;
			}
			$("#feeformYdjy").submit();
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
		function saveFeeYdjy() {
			if (grid.getRowsCount() <= 0) {
				popupAlert("请先录入费用");
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
			var saveItemData = $("#inputFeeYdjy").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!inputFeeYdjy.action",
					saveItemData, importFeeSaveInfo);
		}

		function importFeeSaveInfo(json) {
			if (!checkJSONResultNew(json)) {
				$("#btSaveFee").prop("disabled", false);
				return;
			}
			if (!powersi.isnull(json.message)) {
				popupInfo(json.message);
			}
		}

		/*费用计算(试算)*/
		function tryCalcTreatYdjy() {
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			feeItemReset();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!tryCalcTreatYdjy.action?inHospitalDTO.aaz217=" + aaz217,
					function(json) {
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
					});
		}

		function changeSearchType(sidx) {
			actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
					+ sidx.value + "&ake007=" + $("#ake007").val();
			$("#stext").combogrid("option", "url", actionUrl);
		}

		function onchangeAke007(para) {
			var ake007 = $("#ake007").val();
			var bka017 = $("#bka017").val();
			if (ake007 < bka017) {
				popupAlert("费用日期不能小于入院日期");
				return $("#ake007").focus();
			}
			actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
					+ $("#sidx").val() + "&ake007=" + $("#ake007").val();
			$("#stext").combogrid("option", "url", actionUrl);
		}

		function dealKeyDown(para) {
			if (window.event.keyCode == "13") {
				var fieldName = para.id;
				if ("bka040" == fieldName) {
					var bka040 = $("#bka040").val();
					if (isNaN(bka040)) {
						popupAlert("单价不能为字符！");
						return $("#bka040").focus();
					}
					if (!powersi.isnull(bka040)) {
						if (parseFloat(bka040) <= 0) {
							popupAlert("单价不能小于等于0！");
							return $("#bka040").focus();
						}
					}
					return $("#akc226").focus();
				}
				if ("akc226" == fieldName) {
					var bka040 = $("#bka040").val();
					var akc226 = $("#akc226").val();
					if (isNaN(akc226)) {
						popupAlert("数量不能为字符！");
						return $("#akc226").focus();
					}
					if (!powersi.isnull(akc226)) {
						if (parseFloat(akc226) == 0) {
							popupAlert("数量不能等于0！");
							return $("#akc226").focus();
						}
					}
					if (!powersi.isnull(akc226) && !powersi.isnull(bka040)) {
						$("#aae019").val(
								(parseFloat(akc226) * parseFloat(bka040))
										.toFixed(2));
					}
					return $("#aae019").focus();
				}
				if ("aae019" == fieldName) {
					var aae019 = $("#aae019").val();
					if (isNaN(aae019)) {
						popupAlert("金额不能为字符！");
						return $("#aae019").focus();
					}
					if (!powersi.isnull(aae019)) {
						if (parseFloat(aae019) == 0) {
							popupAlert("金额不能等于0！");
							return $("#aae019").focus();
						}
					}
					return $("#bkz103").focus();
				}

				if("bkz103" === fieldName){
					insertfee();
				}
				if ("todayCollect" == fieldName) {
					showMoney();
				}
			}
		}
		
		function insertfee() {
			if (powersi.isnull($("#ake006").val())) {
				popupAlert("未录入药品！");
				return;
			}
			if (powersi.isnull($("#aae019").val())) {
				popupAlert("未录入金额！");
				return;
			}
			if (parseFloat($("#aae019").val()) == 0) {
				popupAlert("金额不能等于0！");
			}
			if (isNaN($("#aae019").val()) * 1) {
				popupAlert("金额输入有误！");
				$("#aae019").focus();
				return;
			}
			var bkm017 = $("#bkm017").val();
			var bkc194 = $("#bkc194").val();
			var bkc109 = $("#bkc109").val();
			var ake001 = $("#ake001").val();
			if (powersi.isnull($("#aka065").val())) {
				popupAlert("收费项目等级不能为空！");
				return;
			}
			var aka065_name = $("#aka065_name").val();
			var ake003_name = $("#ake003_name").val();
			var aka063 = $("#aka063").val();
			var aka065 = $("#aka065").val();
			var ake003 = $("#ake003").val();
			var ake005 = $("#ake005").val();
			var ake006 = $("#ake006").val();
			var bka073 = $("#bka073").val();
			var aka070 = $("#aka070").val();
			var aka074 = $("#aka074").val();
			var bka040 = parseFloat($("#bka040").val());
			var akc226 = parseFloat($("#akc226").val());
			var bka074 = $("#bka075").val();
			var bka075 = $("#bka075 option:selected").text();
			var input_ver = (parseFloat($("#aae019").val()) * 1.0000)
					.toFixed(2);
			var ake007 = $("#ake007").val();
			var aka057 = $("#aka057").val();
			var bka892 = "0";
			var bkz103 = $("#bkz103").val();
			var jsonFee = {
				"bkm017" : bkm017,
				"ake003_name" : ake003_name,
				"aka065_name" : aka065_name,
				"aka063" : aka063,
				"aka065" : aka065,
				"ake003" : ake003,
				"bkc194" : bkc194,
				"bkc109" : bkc109,
				"ake001" : ake001,
				"ake005" : ake005,
				"ake006" : ake006,
				"bka073" : bka073,
				"aka070" : aka070,
				"aka074" : aka074,
				"bka040" : bka040,
				"akc226" : akc226,
				"aae019" : input_ver,
				"ake007" : ake007,
				"aka057" : aka057,
				"bka892" : bka892,
				"bka074" : bka074,
				"bka075" : bka075,
				"bkz103" : bkz103
			};
			grid.add(jsonFee);
			$("#stext").focus();
			$("#stext").val("");
			$("#ake003_name").val("");
			$("#aka065_name").val("");
			$("#ake003").val("");
			$("#aka063").val("");
			$("#aka065").val("");
			$("#bkc194").val("");
			$("#bkc109").val("");
			$("#ake001").val("");
			$("#ake005").val("");
			$("#ake006").val("");
			$("#bka073").val("");
			$("#aka070").val("");
			$("#aka074").val("");
			$("#bka040").val("");
			$("#akc226").val("");
			$("#aae019").val("");
			$("#aka057").val("");
			$("#bkz103").val("0");
		}
		
		function deletefee() {
			if (typeof (grid.getSelectedRows()) == "undefined"
					|| grid.getSelectedRows() == null
					|| grid.getSelectedRows() == "") {
				popupAlert("请选择要删除的费用！");
				return;
			}
			popupConfirm("是否删除已选择的费用?", "提示", function(isOk){
				if (isOk) {
					var rows = grid.getSelectedRows();
					valideDel(grid.getSelectedRows())
					//valideDelFeeSetMeal(grid.getSelectedRows())
				}
			});
		}
		
		function showMoney(){
			var todayCollect = $("#todayCollect").val();
			var bka831 = $("#bka831").val();
			var bka245 = $("#bka245").val();
			if (isNaN(todayCollect)) {
				popupAlert("今收取金额不能为字符！");
				return $("#todayCollect").focus();
			}
			if (parseFloat(todayCollect) < 0) {
				popupAlert("今收取金额不能小于0！");
				return $("#todayCollect").focus();
			}
			if (powersi.isnull(todayCollect)) {
				todayCollect = 0;
			}
			if (powersi.isnull(bka245)) {
				bka245 = 0.0;
			}
			$("#needFind").val((parseFloat(bka245) + parseFloat(todayCollect) - parseFloat(bka831)).toFixed(2));
		}

		/**
		 * 显示超范围用药
		 * @param rowdata 行数据
		 * @param index 索引值
		 * @param value 码值
		 * @returns {string}
		 */
		function renderBkz103(rowdata, index, value){
			if(value === "0"){
				return "否";
			}
			if(value === "1"){
				return "是";
			}
		}
	</script>
</body>
</powersi:html>