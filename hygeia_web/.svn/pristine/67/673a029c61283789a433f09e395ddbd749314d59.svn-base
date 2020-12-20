<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="异地就医住院修改" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<powersi:form id="modifyRegisterYdjy" namespace="/inhospital"
		action="InhospitalManagerAction!modifyRegisterYdjy.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveModifyRegister" label="保存修改"
					onclick="saveModifyRegisterYdjy()" />
				<powersi:button id="btModifyRegisterReset" label="重置"
					onclick="modifyRegisterYdjyReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,15%,8%,12%,8%,12%,10%,15%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="" cssClass="select2"
						list="#{'aac002':'社会保障号','aaz217':'就医登记号' }" />
					<td><powersi:textfield id="querystring"
							name="inHospitalDTO.querystring" title="请输入信息回车"
							placeholder="请输入信息回车！" readonly="false"
							onkeydown="findAaz217Ydjy()" buttonDisabled="false" /></td>
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="单位名称" readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="住院信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden id="bka019_hid" name="inHospitalDTO.bka019_hid" />
					<powersi:hidden id="bka021_hid" name="inHospitalDTO.bka021_hid" />
					<powersi:hidden id="ake020_hid" name="inHospitalDTO.ake020_hid" />
					<powersi:hidden id="ake022_hid" name="inHospitalDTO.ake022_hid" />
					<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
					<powersi:hidden id="akc193" name="inHospitalDTO.akc193" />
					<powersi:hidden id="bka006" name="inHospitalDTO.bka006" />
					<powersi:hidden id="bka016" name="inHospitalDTO.bka016" />
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:hidden id="fin_disease1" name="inHospitalDTO.fin_disease1" />
					<powersi:hidden id="fin_disease2" name="inHospitalDTO.fin_disease2" />
					
					<powersi:textfield id="akb021" name="inHospitalDTO.akb021"
						label="医院名称" readonly="true" />
					<powersi:textfield id="aka130_name"
						name="inHospitalDTO.aka130_name" label="业务类型" readonly="true" />
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" readonly="true" />
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" required="true" readonly="false" />
					<powersi:textfield id="bkz101"
						name="inHospitalDTO.bkz101" label="入院诊断" title="请输入入院诊断"
						placeholder="请输入入院诊断" onkeydown="keydown(this)" required="true"
						readonly="true" buttonText="选     择" buttonId="bka026_button"
						buttonDisabled="false" onbuttonclick="chooseBkz101()" />
					<powersi:codeselect id="akf001" name="inHospitalDTO.akf001"
						label="入院科室" cssClass="select2" list="#request.akf001List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectBka021()" />
					<powersi:codeselect id="bka021" name="inHospitalDTO.bka021"
						label="入院病区" cssClass="select2" list="#request.bka021List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectAke020()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="ake020" name="inHospitalDTO.ake020"
						label="床位号" cssClass="select2" list="#request.ake020List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectAke022()" />
					<powersi:codeselect id="ake022" name="inHospitalDTO.ake022"
						label="医保医师" cssClass="select2" list="#request.ake022List"
						headerKey="" headerValue="请选择..." />
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217"
						label="就医登记号" readonly="true" />
					<powersi:textfield id="bka015" name="inHospitalDTO.bka015"
						label="登记人" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="fin_disease1_name" name="inHospitalDTO.fin_disease1_name"
						label="第一副诊断" readonly="true" buttonText="选     择" buttonId="dis1_button"
						buttonDisabled="false" onbuttonclick="chooseFinDisease('1')" />
					<powersi:textfield id="fin_disease2_name" name="inHospitalDTO.fin_disease2_name"
						label="第二副诊断" readonly="true" buttonText="选     择" buttonId="dis2_button"
						buttonDisabled="false" onbuttonclick="chooseFinDisease('2')" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
<%-- 		<powersi:panelbox key="其它诊断信息"> --%>
<%-- 			<powersi:panelbox-toolbar> --%>
<%-- 				<powersi:button id="btn_selDiagnoseinfo" label="其它诊断" --%>
<%-- 					key="button_query" onclick="chooseGridDiagnoseinfo()" /> --%>
<%-- 				<powersi:button id="btn_deleteDiagnoseinfo" label="删除" --%>
<%-- 					key="button_delete" onclick="doDelete()" /> --%>
<%-- 			</powersi:panelbox-toolbar> --%>
<%-- 			<powersi:datagrid id="gridDiagnoseinfo" delayLoad="true" --%>
<%-- 				showReload="false" autoWidth="true" enabledSort="false" --%>
<%-- 				alternatingRow="true" checkbox="true" colDraggable="false" --%>
<%-- 				height="200" pageSize="10" rownumbers="true"> --%>
<%-- 				<powersi:datagrid-column display="序号" name="bke558" width="0%" --%>
<%-- 					hide="true" /> --%>
<%-- 				<powersi:datagrid-column display="编码" name="aka120" width="40%" /> --%>
<%-- 				<powersi:datagrid-column display="名称" name="aka121" width="60%" /> --%>
<%-- 				<powersi:datagrid-column display="类型" name="bke559" width="0%" --%>
<%-- 					hide="true" /> --%>
<%-- 			</powersi:datagrid> --%>
<%-- 			<powersi:hidden id="diagnoseinfo" name="diagnoseinfo" /> --%>
<%-- 		</powersi:panelbox> --%>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var firstLoad = true;
		$(document).ready(function() {
			$("#querystring").focus();
		});

		function findModifyRegisteraaz217YdjyByBka100() {
			var modifyRegisterData = $("#modifyRegisterYdjy").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAaz217Ydjy.action",
					modifyRegisterData, function(json) {
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
						$("#akf001").change();
						$("#bka021").change();
					});
		}

		function saveModifyRegisterYdjy() {
			if (!checkForm()) {
				return;
			}
			var aaz217 = powersi.trim($("#aaz217").val());
			if (powersi.isnull(aaz217)) {
				return;
			}
			$("#btSaveModifyRegister").prop("disabled", true);
			var modifyRegisterSnydData = $("#modifyRegisterYdjy").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!modifyRegisterYdjy.action",
					modifyRegisterSnydData, function(json) {
						$("#btSaveModifyRegister").prop("disabled", false);
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

		function modifyRegisterYdjyReset() {
			$("#btReset").click();
			//入院科室
			$("#akf001").val("");
			//入院病区
			$("#bka021").empty();
			//入院床位号
			$("#ake020").empty();
			//医保医师
			$("#ake022").empty();
			$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake020").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka021").change();
			$("#ake020").change();
			$("#ake022").change();
			$("#akf001").change();
			$("#fin_disease1").val("");
			$("#fin_disease2").val("");
			$("#fin_disease1_name").val("");
			$("#fin_disease2_name").val("");
			firstLoad = true;
		}

		function findAaz217Ydjy() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				var argName = $("#argName").val()
				if (powersi.isnull(querystring)) {
					return;
				}
				modifyRegisterYdjyReset();
				$("#querystring").val(querystring);
				$("#argName").val(argName);
				$("#argName").change();
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findAaz217Ydjy.action",
						{
							"inHospitalDTO.querystring" : querystring,
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
							$("#akf001").change();
							$("#bka021").change();
						});
			}
		}

		/*加载医保医师*/
		function selectAke022() {
			//入院科室
			var akf001 = $("#akf001").val();
			if (powersi.isnull(akf001)) {
				return;
			}
			//入院病区
			var bka021 = $("#bka021").val();
			if (powersi.isnull(bka021)) {
				return;
			}
			//入院床位号
			var ake020 = $("#ake020").val();
			$("#ake022").empty();
			$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake022").change();
			if (powersi.isnull(ake020)) {
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
							$("#ake022").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
						var ake022_hid = $("#ake022_hid").val();
						if (!powersi.isnull(ake022_hid)) {
							$("#ake022").val(ake022_hid);
							$("#ake022").change();
						}
					});
		}
		/*加载床位号*/
		function selectAke020() {
			//入院科室
			var akf001 = $("#akf001").val();
			if (powersi.isnull(akf001)) {
				return;
			}
			//入院病区
			var bka021 = $("#bka021").val();
			//入院床位号
			$("#ake020").empty();
			//医保医师
			$("#ake022").empty();
			
			$("#ake020").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake020").change();
			$("#ake022").change();
			if (powersi.isnull(bka021)) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadAke020List.action",
					{
						"inHospitalDTO.bka021" : bka021
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
							$("#ake020").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
						var ake020_hid = $("#ake020_hid").val();
						var bka021_hid = $("#bka021_hid").val();
						if (!powersi.isnull(ake020_hid)) {
							if(bka021==bka021_hid) {	
								var a = [];
								a.push('<option value="');
								a.push(ake020_hid);
								a.push('"');
								a.push(">");
								a.push(ake020_hid);
								a.push("</option>");
								$("#ake020").append(a.join(''));
							}
							$("#ake020").val(ake020_hid);
							$("#ake020").change();
						}
					});
		}


		/*加载病区*/
		function selectBka021() {
			var akf001 = $("#akf001").val();//入院科室
			$("#bka021").empty();//入院病区
			$("#ake020").empty();//入院床位号
			$("#ake022").empty();//医保医师
			
			$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake020").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
			
			$("#bka021").change();
			$("#ake020").change();
			$("#ake022").change();
			if (powersi.isnull(akf001)) {
				return;
			}
			
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadBka021List.action",
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

// 		function initDiagnoseinfo() {
// 			var diagnoseinfo = gridDiagnoseinfo.getData();
// 			diagnoseinfo = powersi.tostring(diagnoseinfo);
// 			$("#diagnoseinfo").val(encodeURI(diagnoseinfo));
// 		}

// 		var num_diagnose_sn = 1;
// 		function chooseGridDiagnoseinfo() {
// 			var bka006 = $("#bka006").val();
// 			var caa027 = $("#caa027").val();
// 			if ("121" == bka006) {
// 				postJSON(
// 						"${rootPath}/inhospital/InhospitalManagerAction!is106Disease.action?ka06dto.aka120="
// 								+ $("#akc193").val(),
// 						function(json) {
// 							if (!checkJSONResultNew(json)) {
// 								return;
// 							}
// 							var url = "";
// 							if ("1" == json.data) {
// 								url = "${rootPath}/inhospital/InhospitalManagerAction!choose106SubDisease.action"
// 										+ "?ka06dto.aka120="
// 										+ $("#akc193").val();
// 							} else {
// 								url = "${rootPath}/common/CommonManagerAction!chooseDisease.action?ka06dto.aka035=2";
// 							}
// 							popupDialog({
// 								url : url,
// 								onClosed : function() {
// 									var retValue = this.returnValue;
// 									if (retValue) {

// 										var jsonFee = {
// 											"aka120" : retValue.aka120,
// 											"aka121" : retValue.aka121,
// 											"bke558" : num_diagnose_sn++,
// 											"bke559" : "1"
// 										};
// 										gridDiagnoseinfo.add(jsonFee);
// 									}
// 								}
// 							}, 500, 600);
// 						});
// 			} else {
// 				popupDialog(
// 						{
// 							url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
// 							onClosed : function() {
// 								var retValue = this.returnValue;
// 								if (retValue) {

// 									var jsonFee = {
// 										"aka120" : retValue.aka120,
// 										"aka121" : retValue.aka121,
// 										"bke558" : num_diagnose_sn++,
// 										"bke559" : "1"
// 									};
// 									gridDiagnoseinfo.add(jsonFee);
// 								}
// 							}
// 						}, 500, 600);
// 			}
// 		}

// 		function doDelete() {
// 			if (typeof (gridDiagnoseinfo.getSelectedRows()) == "undefined"
// 					|| gridDiagnoseinfo.getSelectedRows() == null
// 					|| gridDiagnoseinfo.getSelectedRows() == "") {
// 				popupAlert("请选择要删除的其它诊断", "提示", "info");
// 				return;
// 			}
// 			popupConfirm("是否删除已选择的其它诊断?", "确认", function(isOk) {
// 				gridDiagnoseinfo.deleteSelectedRow();
// 			});
// 		}
		
		/*选择疾病*/
		function chooseBkz101() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#bkz101").val(retValue.aka121);
								$("#akc193").val(retValue.aka120);
								return $("#bka021").focus();
							} else {
								$("#bkz101").val("");
								$("#akc193").val("");
								return $("#bkz101").focus();
							}
						}
					}, 500, 600);
		}
		
		/*多疾病录入*/
		function chooseFinDisease(index) {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								if(index == "1"){
									$("#fin_disease1").val(retValue.aka120);
									$("#fin_disease1_name").val(retValue.aka121);
									return $("#fin_disease1_name").focus();
								}
								if(index == "2"){
									$("#fin_disease2").val(retValue.aka120);
									$("#fin_disease2_name").val(retValue.aka121);
									return $("#fin_disease2_name").focus();
								}
							} else {
								if(index == "1"){
									$("#fin_disease1").val("");
									$("#fin_disease1_name").val("");
									return $("#fin_disease1_name").focus();
								}
								if(index == "2"){
									$("#fin_disease2").val("");
									$("#fin_disease2_name").val("");
									return $("#fin_disease2_name").focus();
								}
							}
						}
					}, 500, 600);
		}
	</script>
</body>
</powersi:html>