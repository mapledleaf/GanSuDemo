<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String aaa027 = BizHelper.getAaa027();
	System.out.print(aaa027);
%>
<powersi:html>
<head>
<powersi:head title="住院回退" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="queryInHospitalForm" namespace="/inhospital"
		action="InhospitalManagerAction!queryInHospital.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query"/>
				<powersi:button id="btResetView" label="重置" onclick="resetView()" key="button_reset"/>
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
				<powersi:button id="btnExport3" label="导出"  onclick="grid.exportExcel2007()" key="button_export"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,10%,10%,8%,12%,8%,12%,10%,10%">
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="开始日期" required="true" mask="date" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="结束日期" required="true" mask="date" />
					<powersi:radio id="bka891" name="inHospitalDTO.bka891" label="结算标识"
						codeType="bka891" required="true" />
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="查询条件" cssClass="select2" 
						list="#{'akc190':'住院号','aac002':'社会保障号','aaz217':'就医登记号' }" />
					<td colspan="2">
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring"
							title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
							buttonText="读卡" buttonId="readic_button"
							buttonDisabled="false"  />
					</td>	
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
	</powersi:form>
	<powersi:panelbox key="业务列表">
		<powersi:datagrid id="grid" formId="queryInHospitalForm"
			showExportExcel="true" exportFileName="getExportName" 
			delayLoad="true" showReload="false" enabledSort="false"
			alternatingRow="true" colDraggable="false" usePager="false">
			<powersi:datagrid-column key="operate" render="renderOperate"
				width="200" frozen="true" />
<%-- 			<powersi:datagrid-column name="bka507" display="审核状态" code="bka507" --%>
<%-- 				width="80" /> --%>
			<powersi:datagrid-column name="akb020" display="医院编号" width="80" />
			<powersi:datagrid-column name="aaz217" display="就医登记号" width="150" />
			<powersi:datagrid-column name="aac001" display="电脑号" width="120" />
			<powersi:datagrid-column name="aac003" display="姓名" width="120" />
			<powersi:datagrid-column name="aac004" display="性别" code="aac004"
				width="40" />
			<powersi:datagrid-column name="aac002" display="身份证号码" width="150" />
			<powersi:datagrid-column name="bka035" display="人员类别" code="bka035"
				width="120" />
			<powersi:datagrid-column name="aka130" display="业务类别" code="aka130"
				width="80" />
			<powersi:datagrid-column name="bka006" display="待遇类型" code="bka006"
				width="150" />
			<powersi:datagrid-column name="aae030" display="业务开始日期" width="80" />
			<powersi:datagrid-column name="bkz101" display="入院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="aae031" display="业务结束日期" width="80" />
			<powersi:datagrid-column name="bkz102" display="出院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="akc190" display="住院号" width="120" />
			<powersi:datagrid-column name="akc194" display="业务结算日期" width="150" />
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
		function getExportName(){
			return '住院回退信息';
		}
		/*读卡*/
		function iReadCardBase() {
			$("#bke548").val("");
			$("#querystring").val("");
			loadCardControl();
			if (objCard != null && objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}

		/*读卡获取后台信息*/
		function readic() {
			resetView();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			$("#queryInHospitalForm").submit();
			$("#bke548").val("");
		}

		function queryInHospitalBizs() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				$("#queryInHospitalForm").submit();
			}
		}

		function resetView() {
			$("#btReset").click();
			grid.reset();
		}

		function renderOperate(row, index, value) {
			var a = [];
			a.push('<input type="button" value="取消住院登记" class="linkButton"');
			a.push(' onclick="doCancelRegister(');
			a.push(index);
			a.push(')"');
			if (row['bka039'] == '1' || row['bka891'] == '1' || row['bka012']!=null) {
				a.push(' disabled="disabled"');
			}
			a.push(" />");

// 			a.push("&nbsp;&nbsp;");

// 			a.push('<input type="button" value="取消出院登记" class="linkButton"');
// 			a.push(' onclick="doCancelOutRegister(');
// 			a.push(index);
// 			a.push(')"');
// 			if (row['bka039'] == '0' || row['bka891'] == '1') {
// 				a.push(' disabled="disabled"');
// 			}
// 			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="取消出院结算" class="linkButton"');
			a.push(' onclick="doCancelOutCharge(');
			a.push(index);
			a.push(')"');
			if (row['bka039'] == '0' || row['bka891'] == '0') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");
			
			var aaa027 = <%=aaa027%>;
			return a.join('');
		}
		
		function doMidwayCharge(index) {
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
			var bka012 = row['bka012'];
			if (powersi.isnull(bka012)) {
				return;
			}
			var aac002 = row['aac002'];
			var doMidwayChargeData = {
					"inHospitalDTO.kcd1id" : kcd1id,
					"inHospitalDTO.aaz217" : aaz217,
					"inHospitalDTO.aac002" : aac002,
					"inHospitalDTO.aac003" : aac003,
					"inHospitalDTO.bka012" : bka012
				}
			if (!confirm("您确认取消" + aac003 + "的中途结算吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/MidwayChargeAction!doMidwayCharge.action",
					doMidwayChargeData,function(json) {
						if (!checkJSONResultNew(json)) {
							return;
						}
						popupInfo(json.message);
						grid.deleteRow(index);
					});
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
			var aac002 = row['aac002'];
			if (powersi.isnull(aac002)) {
				return;
			}
			var caa027 = row['caa027'];
			if (powersi.isnull(caa027)) {
				return;
			}
			if(popupConfirm("您确认取消" + aac003 + "的出院结算吗?", "确认", function(isOk){
				if(isOk){
					//读卡验证
					//loadCardControl();
					var bke548 = null;// 读卡返回
					if (objCard != null && objCard.object != null) {
						bke548 = objCard.ReadCardBase();
					}
					var cancelRegisterData = {
						"inHospitalDTO.kc21id" : kc21id,
						"inHospitalDTO.aaz217" : aaz217,
						"inHospitalDTO.aac002" : aac002,
						"inHospitalDTO.bke548" : bke548,
						"inHospitalDTO.caa027" : caa027
					}
					postJSON(
							"${rootPath}/inhospital/InhospitalManagerAction!cancelOutCharge.action",
							cancelRegisterData, function(json) {
								if (!checkJSONResultNew(json)) {
									return;
								}
								popupInfo(json.message);
								grid.deleteRow(index);
							});
				}
			}));
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
			if (!confirm("您确认取消" + aac003 + "的出院登记吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!cancelOutRegister.action?inHospitalDTO.kcd1id="
							+ kcd1id + "&inHospitalDTO.aaz217=" + aaz217,
					function(json) {
						if (!checkJSONResultNew(json)) {
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
			var caa027 = row['caa027'];
			if (powersi.isnull(caa027)) {
				return;
			}
			var akc190 = row['akc190'];
			if (powersi.isnull(akc190)) {
				return;
			}
			if(popupConfirm("您确认取消" + aac003 + "的住院登记吗?", "确认", function(isOk){
				if(isOk){
					//读卡验证
					//loadCardControl();
					var bke548 = null;// 读卡返回
					if (objCard != null && objCard.object != null) {
						bke548 = objCard.ReadCardBase();
					}
					var cancelRegisterData = {
						"inHospitalDTO.kcd1id" : kcd1id,
						"inHospitalDTO.aaz217" : aaz217,
						"inHospitalDTO.aac002" : aac002,
						"inHospitalDTO.bke548" : bke548,
						"inHospitalDTO.caa027" : caa027,
						"inHospitalDTO.akc190" : akc190
					}
					postJSON(
							"${rootPath}/universal/InhospitalManagerAction_HIS!cancelRegister.action?hospInfoflag=1",
							cancelRegisterData, function(json) {
								if (!checkJSONResultNew(json)) {
									return;
								}
								popupInfo(json.message);
								grid.deleteRow(index);
							});
				}
			}));
			
		}
	</script>
</body>
</powersi:html>