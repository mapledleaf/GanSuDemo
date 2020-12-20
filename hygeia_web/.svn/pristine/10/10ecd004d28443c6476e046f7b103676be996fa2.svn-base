<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
 //TS19090300069   【需求开发】异地就医登记报错（该医院没有在城乡居民处进行审核操作，无法进行居民异地就医），登记人员为职工。
 //添加人员类别 bka035   陈洁  20190905
%>
<powersi:html>
<head>
<powersi:head title="异地住院登记" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<powersi:form id="registerYdjy" namespace="/inhospital"
		action="InhospitalManagerAction!registerYdjy.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveRegister" label="保存登记"
					onclick="saveregisterYdjy()" />
				<powersi:button id="btRegisterReset" label="重置"
					onclick="resetpage()" />
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,15%,8%,12%,8%,12%,10%,15%">
				<powersi:editorlayout-row>
					<powersi:hidden id="aab301" name="inHospitalDTO.aab301" />
					<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
					<powersi:hidden id="bka977_name" name="inHospitalDTO.bka977_name" />
					<powersi:hidden id="bka005" name="inHospitalDTO.bka005" value="000" />
					<powersi:hidden id="aae139" name="inHospitalDTO.aae139" value="1" />
					<powersi:hidden id="caa027" name="inHospitalDTO.caa027" value="4303-zg" />
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:hidden id="akc193" name="inHospitalDTO.akc193" />
					<powersi:hidden id="aac004" name="inHospitalDTO.aac004" />
					<powersi:hidden id="bka035" name="inHospitalDTO.bka035" />
					<powersi:hidden id="fin_disease1" name="inHospitalDTO.fin_disease1" />
					<powersi:hidden id="fin_disease2" name="inHospitalDTO.fin_disease2" />
					<powersi:hidden id="outchargeid" value="1"/><!-- 住院结算读卡返回aac002 -->
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="" cssClass="select2" list="#{'aac002':'社会保障号'}" />
					<td>
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring"
							title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
							onkeyup="findAac001Ydjy()" buttonText="读卡" buttonId="readic_button"
							buttonDisabled="false" />
					</td>
					<powersi:textfield id="aaz500" name="inHospitalDTO.aaz500"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
 		<powersi:panelbox key="基本信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
 						label="电脑号" readonly="true" />	
					<powersi:textfield id="bka035_name"
						name="inHospitalDTO.bka035_name" label="人员类别" readonly="true" />
					<powersi:textfield id="bka010" name="inHospitalDTO.bka010"
 						label="住院次数" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka888"
						name="inHospitalDTO.bka888" label="基金状况" readonly="true" />
					<powersi:textfield id="akc252"
						name="inHospitalDTO.akc252" label="帐户余额" readonly="true" />
					<powersi:textfield id="aac008_name"
						name="inHospitalDTO.aac008_name" label="人员状态" readonly="true" />
					<powersi:textfield id="bac001_name"
						name="inHospitalDTO.bac001_name" label="公务员级别" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="yka119" name="inHospitalDTO.yka119"
						label="基本医疗本次支付限额" readonly="true" />
					<powersi:textfield id="yka121" name="inHospitalDTO.yka121"
						label="大病医疗本次支付限额" readonly="true" />
					<powersi:textfield id="yka123" name="inHospitalDTO.yka123"
						label="公务员本次支付限额" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="本次登记">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="akb021" name="inHospitalDTO.akb021"
						label="医院名称" readonly="true" />
					<powersi:textfield id="aka130_name"
						name="inHospitalDTO.aka130_name" label="业务类型" readonly="true" />
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" mask="date" required="true" readonly="false" />
					<powersi:codeselect id="bka006" name="inHospitalDTO.bka006"
						label="待遇类型" cssClass="select2" codeType="bka006"
						codeFilter="data_value in ('120')" required="true" headerKey="0"
						displayonly="false" showValue="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" onkeydown="keydown(this)" required="true"
						readonly="false" />
					<powersi:textfield id="bkz101"
						name="inHospitalDTO.bkz101" label="入院诊断" title="请输入入院诊断"
						placeholder="请输入入院诊断" onkeydown="keydown(this)" required="true"
						readonly="true" buttonText="选     择" buttonId="bka026_button"
						buttonDisabled="false" onbuttonclick="chooseBkz101()" />
					<powersi:codeselect id="akf001" name="inHospitalDTO.akf001"
						label="入院科室" cssClass="select2" list="#request.akf001List"
						headerKey="" headerValue="请选择..." required="false"
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
					<powersi:textfield id="baz113" name="inHospitalDTO.baz113"
						label="联系地址" readonly="false" />
					<powersi:textfield id="aae013" name="inHospitalDTO.aae013"
						label="病情备注" readonly="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="fin_disease1_name"
						label="第一副诊断" readonly="true" buttonText="选     择" buttonId="dis1_button"
						buttonDisabled="false" onbuttonclick="chooseFinDisease('1')" />
					<powersi:textfield id="fin_disease2_name"
						label="第二副诊断" readonly="true" buttonText="选     择" buttonId="dis2_button"
						buttonDisabled="false" onbuttonclick="chooseFinDisease('2')" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
<%-- 		<powersi:panelbox key="其它诊断信息">		 --%>
<%-- 			<powersi:panelbox-toolbar> --%>
<%-- 				<powersi:button id="btn_selDiagnoseinfo" label="其它诊断" key="button_query" onclick="chooseGridDiagnoseinfo()" /> --%>
<%-- 				<powersi:button id="btn_deleteDiagnoseinfo" label="删除" key="button_delete" onclick="doDelete()" /> --%>
<%-- 			</powersi:panelbox-toolbar> --%>
<%-- 		 	<powersi:datagrid id="gridDiagnoseinfo"  delayLoad="true" showReload="false"  --%>
<%-- 		 		autoWidth="true" enabledSort="false" alternatingRow="true" checkbox="true"  --%>
<%-- 		 		colDraggable="false" height="200" pageSize="10" rownumbers="true" >  --%>
<%-- 		 		<powersi:datagrid-column display="序号" name="bke558" width="0%" hide="true"  /> --%>
<%-- 	            <powersi:datagrid-column display="编码" name="aka120" width="40%"  /> --%>
<%-- 	            <powersi:datagrid-column display="名称" name="aka121" width="60%" /> --%>
<%-- 	            <powersi:datagrid-column display="类型" name="bke559" width="0%" hide="true"  /> --%>
<%--      		</powersi:datagrid> --%>
<%--     		<powersi:hidden id="diagnoseinfo" name="diagnoseinfo" /> --%>
<%-- 		</powersi:panelbox> --%>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		var saveRegisterFlag = false;
		$(document).ready(function() {
			$("#querystring").focus();
		});

		/*初始化*/
		function init() {
			saveRegisterFlag = false;
			$("#bke548").val("");
			$("#querystring").val("");
			$("#bka977_name").val("");
			$("#aac001").val("");
			$("#aac002").val("");
			$("#aac003").val("");
			$("#fin_disease1").val("");
			$("#fin_disease2").val("");
			$("#fin_disease1_name").val("");
			$("#fin_disease2_name").val("");
			$("#querystring").prop("disabled", false);
			$("#btSaveRegister").prop("disabled", true);
			//参保地统筹区			
			$("#baa027").val("");
			//待遇类型
 			//$("#bka006").val("");
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
			$("#bka006").change();
			$("#baa027").change();
		}

		/*重置界面*/
		function resetpage() {
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
					return $("#bkz101").focus();
				}
				if ("bkz101" == filed_name) {
					return chooseBkz101();
				}
// 				if ("bka018" == filed_name) {
// 					return choosebka018();
// 				}
			}
		}

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

		/*可录入状态*/
		function inputOn() {
			$("#querystring").prop("disabled", false);
		}

		/*不可录入状态*/
		function inputOff() {
			/*规避重复操作处理_不可用*/
			$("#querystring").prop("disabled", true);
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
							$("#ake022").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
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
							$("#ake020").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
		}

		/*加载病区*/
		function selectBka021() {
			//入院科室
			var akf001 = $("#akf001").val();
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
			if (powersi.isnull(akf001)) {
				return;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!loadBka021List.action",
					{
						"inHospitalDTO.akf001" : akf001
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
		function findAac001Ydjy() {
			if (window.event.keyCode == 13) {
				var querystring = powersi.trim($("#querystring").val());
				var baa027 = powersi.trim($("#baa027").val());
				if (powersi.isnull(querystring)) {
					popupAlert("请输入有效查询条件!");
					return;
				}
				resetpage();
				$("#querystring").val(querystring);
				$("#argName").val("aac002");
				$("#argName").change();
				$("#baa027").val(baa027);
				var baa027 = $("#baa027").val();
				if (powersi.isnull(baa027)) {
					popupAlert("请选择参保地统筹区!");
					return;
				}
				$("#aab301").val($("#baa027").val());
				findAac002Snyd(querystring);
			}
		}

		function readIcCard(){
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				popupAlert("请输入有效查询条件!");
				return;
			}
			var baa027 = $("#baa027").val();
			if (powersi.isnull(baa027)) {
				popupAlert("请选择参保地统筹区!");
				return;
			}
			$("#aab301").val($("#baa027").val());
			findAac002Snyd(querystring);
		} 
				
		/*省内异地住院登记界面要支持：身份证号码、电脑号、社保卡号*/
		function findAac002Snyd(querystring) {
			if (powersi.isnull(querystring)) {
				$("#querystring").val("");
			} else {
				$("#querystring").val(querystring);
			}
			var registerYdjyData = $("#registerYdjy").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001Ydjy.action",
					registerYdjyData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no" && json.data.length > 1) {
							if (powersi.isnull(querystring)) {
								chooseAac002(json.message);
								$("#querystring").val(json.message);
							} else {
								chooseAac002(querystring);
							}
							return;
						}
						if (json.data != "no") {
							$.each(json.data, function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
								if(key=="bka888"){
									// TS19072500260 - 【需求开发】结算云医院端 - 异地就医登记界面，读取人员信息时提示冻结，但是显示的是基金未冻结
									// add by shanqingpeng 2019/07/26
									if(value.trim() != "基金未冻结") {
										//TS19051400133 结算云（医院端）-  （门诊业务管理、住院业务管理、生育住院管理、家庭病床管理、异地就医管理）-  
										//办业务读卡后，如果此人的基金是冻结状态，则弹出一个提示框提示一下这个人基金冻结 cj 20190515
										popupAlert("您好!此参保人基金冻结状态为【已冻结】,将不能进行基金报销,请知悉!");
									}
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
			$("#bka977_name").val("1");
			$("#aac001").val(powersi.trim(aac001));
			$("#querystring").val(powersi.trim(aac001));
			var registerYdjyData = $("#registerYdjy").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001Ydjy.action",
					registerYdjyData, function(json) {
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
		function saveregisterYdjy() {
			if (!checkForm()) {
				return;
			}
			inputOff();
			var aac003 = powersi.trim($("#aac003").val());
			popupConfirm("您确认保存[" + aac003 + "]的异地住院登记信息吗?", "确认", function(isOk){
				if(isOk){
					//initDiagnoseinfo();
					$("#btSaveRegister").prop("disabled", true);
					var registerYdjyData = $("#registerYdjy").serialize();
					postJSON(
							"${rootPath}/inhospital/InhospitalManagerAction!registerYdjy.action",
							registerYdjyData, function(json) {
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
				inputOn();
			});
		}

		function afterSaveRegister() {
			$("#btSaveRegister").prop("disabled", true);
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
// 			if("121"==bka006){
// 				postJSON(
// 						"${rootPath}/inhospital/InhospitalManagerAction!is106Disease.action?ka06dto.aka120="+$("#akc193").val(),
// 						function(json) {
// 							if (!checkJSONResultNew(json)) {
// 								return;
// 							}
// 							var url = "";
// 							if("1"==json.data){
// 								url = "${rootPath}/inhospital/InhospitalManagerAction!choose106SubDisease.action"
// 								+ "?ka06dto.aka120="+$("#akc193").val();
// 							}else{
// 								url = "${rootPath}/common/CommonManagerAction!chooseDisease.action?ka06dto.aka035=2";
// 							}
// 								popupDialog({
// 									url : url,
// 									onClosed : function() {
// 										var retValue = this.returnValue;
// 										if (retValue) {
					
// 											var jsonFee = {
// 												"aka120" : retValue.aka120,
// 												"aka121" : retValue.aka121,
// 												"bke558" : num_diagnose_sn++,
// 												"bke559" : "1"
// 											};
// 											gridDiagnoseinfo.add(jsonFee);
// 										}
// 									}
// 								}, 500, 600);
// 						});
// 			}else{
// 				popupDialog({
// 					url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
// 					onClosed : function() {
// 						var retValue = this.returnValue;
// 						if (retValue) {
	
// 							var jsonFee = {
// 								"aka120" : retValue.aka120,
// 								"aka121" : retValue.aka121,
// 								"bke558" : num_diagnose_sn++,
// 								"bke559" : "1"
// 							};
// 							gridDiagnoseinfo.add(jsonFee);
// 						}
// 					}
// 				}, 500, 600);
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
		
	</script>
</body>
</powersi:html>