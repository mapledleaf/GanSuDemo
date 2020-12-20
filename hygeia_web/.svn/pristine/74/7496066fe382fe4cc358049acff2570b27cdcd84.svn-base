<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String aaa027 = BizHelper.getAaa027();
	String bka006Filter = " data_value like '140%' ";
	String loginUser = BizHelper.getLoginUser().toUpperCase();
	// TS20031600121/NTS20042700215 【需求开发】湘潭医保电子凭证两定（接口）、中心业务系统改造 杨世明 20200417
	if(loginUser.endsWith("TEST")){
		request.setAttribute("argName", "#{'aac001':'电脑号','aac002':'社会保障号'}");
	}else{
		request.setAttribute("argName", "#{'aac002':'社会保障号'}");
	}
%>
<powersi:html>
<head>
<powersi:head title="家庭病床登记" />
<script type="text/javascript"
	src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<powersi:form id="registerHomeSickbed" namespace="/inhospital"
		action="InhospitalManagerAction!registerHomeSickbed.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="readele_btn" key="扫码" />
				<powersi:button id="btSaveRegister" label="保存登记"
					key="button_save_register" onclick="saveRegisterHomeSickbed()" />
				<powersi:button id="btFundStatusQuery" label="基金状态"
					key="button_query" onclick="fundStatusQuery()" />
				<powersi:button id="btcumulative" label="查询累计" key="button_query"
					onclick="cumulativeQuery()" />
				<powersi:button id="btRegisterReset" label="重置" key="button_reset"
					onclick="resetpage()" />
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,10%,15%,8%,12%,8%,12%,10%,15%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="" cssClass="select2" list="${argName }" />
					<td><powersi:textfield id="querystring"
							name="inHospitalDTO.querystring" title="请输入信息回车"
							placeholder="请输入信息回车!" readonly="false"
							onkeyup="findAac001HomeSickbed()" buttonText="读卡"
							buttonId="readic_button" buttonDisabled="false" /></td>
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="单位名称" readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="基本信息(点击展开可查看)" isCollapse="true">
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
					<powersi:textfield id="bka888_name"
						name="inHospitalDTO.bka888_name" label="基金状况" readonly="true" />
					<powersi:textfield id="bacu18" name="inHospitalDTO.bacu18"
						label="帐户余额" readonly="true" />
					<powersi:textfield id="aac148_name"
						name="inHospitalDTO.aac148_name" label="补助类型" readonly="true" />
					<powersi:textfield id="aac013_name"
						name="inHospitalDTO.aac013_name" label="人员状态" readonly="true" />
					<powersi:textfield id="bac001_name"
						name="inHospitalDTO.bac001_name" label="公务员级别" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="baa027_name"
						name="inHospitalDTO.baa027_name" label="人员所属中心" readonly="true" />
					<powersi:textfield id="aaa129" name="inHospitalDTO.aaa129"
						label="区/县" readonly="true" />
					<powersi:textfield id="baf013_name"
						name="inHospitalDTO.baf013_name" label="乡镇/街道" readonly="true" />
					<powersi:textfield id="aaf030_name"
						name="inHospitalDTO.aaf030_name" label="村/社区" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aaz267" name="inHospitalDTO.aaz267"
						label="申请号" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="上次住院">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="akb021_last"
						name="inHospitalDTO.akb021_last" label="医院名称" readonly="true" />
					<powersi:textfield id="aka121_last"
						name="inHospitalDTO.aka121_last" label="诊  断" readonly="true" />
					<powersi:textfield id="aae030_last"
						name="inHospitalDTO.aae030_last" label="入院日期" readonly="true" />
					<powersi:textfield id="aae031_last"
						name="inHospitalDTO.aae031_last" label="出院日期" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="本次登记">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden id="caa027" name="inHospitalDTO.caa027"
						value="4303-zg" />
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
					<powersi:hidden id="bka026" name="inHospitalDTO.bka026" />
					<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
					<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
					<powersi:hidden id="bka977_name" name="inHospitalDTO.bka977_name" />
					<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
					<powersi:hidden id="aka042" name="inHospitalDTO.aka042" value="0" />
					<powersi:hidden id="aka130" name="inHospitalDTO.aka130" />
					<powersi:hidden id="isInpPsw" value="true" />
					<powersi:hidden id="aae013" name="inHospitalDTO.aae013" />
					<powersi:hidden id="akc193" name="inHospitalDTO.akc193" />
					<powersi:hidden id="aab999" name="inHospitalDTO.aab999"
						label="单位管理码" />
					<powersi:hidden id="aab019" name="inHospitalDTO.aab019"
						label="单位类型" />
					<powersi:hidden id="bke054" name="inHospitalDTO.bke054"
						label="一站式标识" />
					<powersi:hidden id="bke301" name="inHospitalDTO.bke301" />
					<powersi:hidden id="aka101" name="inHospitalDTO.aka101" />
					<!-- TS19072500248 - 【需求开发】读卡进行密码签名验证 ，前台获取 ，后台验证  陈洁 20190801  -->
		            <powersi:hidden id="bkz300" name="inHospitalDTO.bkz300" />  

					<powersi:textfield id="aaa027_name"
						name="inHospitalDTO.aaa027_name" label="医保分中心" readonly="true" />
					<powersi:textfield id="akb021" name="inHospitalDTO.akb021"
						label="医院名称" readonly="true" />
					<powersi:textfield id="aka130_name"
						name="inHospitalDTO.aka130_name" label="业务类型" readonly="true" />
					<powersi:textfield id="aae140_name"
						name="inHospitalDTO.aae140_name" label="险种类型" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" mask="date" required="true" readonly="false" />
					<powersi:codeselect id="bka006" name="inHospitalDTO.bka006"
						label="待遇类型" cssClass="select2" codeType="bka006"
						codeFilter="data_value like '14_'"
						codeLocal="${inHospitalDTO.aaa027}" required="true"
						displayonly="false" showValue="false" headerKey="0" />
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" onkeydown="keydown(this)" required="true"
						readonly="false" />
					<powersi:textfield id="bkz101" name="inHospitalDTO.bkz101"
						label="入院诊断" title="请输入入院诊断" onkeydown="keydown(this)"
						required="true" readonly="true" buttonText="选择"
						buttonId="bka026_button" buttonDisabled="false"
						onbuttonclick="chooseDisease()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="akf001" name="inHospitalDTO.akf001"
						label="入院科室" cssClass="select2" list="#request.akf001List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectBka021()" showValue="false" />
					<powersi:codeselect id="bka021" name="inHospitalDTO.bka021"
						label="入院病区" cssClass="select2" list="#request.bka021List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectAke020()" showValue="false" />
					<powersi:codeselect id="ake020" name="inHospitalDTO.ake020"
						label="床位号" cssClass="select2" list="#request.ake020List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectAke022()" showValue="false" />
					<powersi:codeselect id="ake022" name="inHospitalDTO.ake022"
						label="主治医生" cssClass="select2" list="#request.ake022List"
						headerKey="" headerValue="请选择..." showValue="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka245" name="inHospitalDTO.bka245"
						label="预付款" readonly="false" />
					<powersi:codeselect id="bka061" name="inHospitalDTO.bka061"
						label="病情严重程度" cssClass="select2" readonly="false"
						codeType="bka061" headerKey="" headerValue="请选择..."
						showValue="false" codeLocal="${inHospitalDTO.aaa027}" />
					<powersi:textfield id="aae004" name="inHospitalDTO.aae004"
						label="联系人" readonly="false" />
					<powersi:textfield id="aae005" name="inHospitalDTO.aae005"
						label="联系电话" readonly="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae006" name="inHospitalDTO.aae006"
						label="联系地址" readonly="false" />
					<powersi:textfield id="ake024" name="inHospitalDTO.ake024"
						label="病情备注" readonly="false" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:panelbox key="其它诊断信息">
			<powersi:panelbox-toolbar>
				<powersi:button id="btn_selDiagnoseinfo" label="其它诊断"
					onclick="chooseGridDiagnoseinfo()" key="button_query" />
				<powersi:button id="btn_deleteDiagnoseinfo" label="删除"
					onclick="doDelete()" key="button_delete" />
			</powersi:panelbox-toolbar>
			<powersi:datagrid id="gridDiagnoseinfo" delayLoad="true"
				showReload="false" autoWidth="true" enabledSort="false"
				alternatingRow="true" checkbox="true" colDraggable="false"
				height="200" pageSize="100" rownumbers="true">
				<powersi:datagrid-column display="序号" name="bke558" width="0%"
					hide="true" />
				<powersi:datagrid-column display="编码" name="aka120" width="40%" />
				<powersi:datagrid-column display="名称" name="aka121" width="60%" />
				<powersi:datagrid-column display="类型" name="bke559" width="0%"
					hide="true" />
			</powersi:datagrid>
			<powersi:hidden id="diagnoseinfo" name="diagnoseinfo" />
		</powersi:panelbox>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		var saveRegisterHomeSickbedFlag = false;
		$(document).ready(function() {
			$("#querystring").focus();
		});

		function readIcCard() {
			getPerson($("#querystring").val());
		}

		/*初始化*/
		function init() {
			saveRegisterHomeSickbedFlag = false;
			$("#bkz300").val("");
			$("#bke548").val("");
			$("#querystring").val("");
			$("#bka977_name").val("");
			$("#querystring").prop("disabled", false);
			$("#btSaveRegister").prop("disabled", true);
			//入院科室
			$("#akf001").val("");
			//入院病区
			$("#bka021").empty();
			//入院床位号
			$("#ake020").empty();
			//医保医师
			$("#ake022").empty();
			$("#aac002").val("");
			$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake020").append("<option value='' >" + "请选择..." + "</option>");
			$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka021").change();
			$("#ake020").change();
			$("#ake022").change();
			$("#akf001").change();
			$("#argName option:selected").val('aac002');
			$("#argName").change();
			$("#argName").val("aac002");
			$("#argName").change();
			gridDiagnoseinfo.reset();
		}

		/*重置界面*/
		function resetpage() {
			var aac003 = powersi.trim($("#aac003").val());
			$("#btReset").click();
			init();
		}

		function keydown(param) {
			if (event.keyCode == "13") {
				var filed_name = param.id;
				if ("bkz101" == filed_name) {
					return chooseDisease();
				}
				if ("bka018" == filed_name) {
					return choosebka018();
				}
			}
		}

		/*选择疾病*/
		function chooseDisease() {
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								$("#bkz101").val(retValue.aka121);
								$("#bka026").val(retValue.aka120);
								return $("#bka021").focus();
							} else {
								$("#bkz101").val("");
								$("#bka026").val("");
								return $("#bkz101").focus();
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
			$("#querystring").prop("disabled", false);
		}

		/*不可录入状态*/
		function inputOff() {
			/*规避重复操作处理_不可用*/
			$("#querystring").prop("disabled", true);
		}

		/*查询参保信息*/
		function findAac001HomeSickbed() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					popupAlert("请输入有效查询条件!");
					return;
				}
				var argName = $("#argName").val();
				if ('aac002' == argName) {//如果是身份证获取人员信息，需先校验身份信息
					checkIcCard(querystring);
				} else {
					getPerson(querystring);
				}
			}
		}

		/*登记界面要支持：社会保障号码、电脑号、社会保障号码*/
		function getPerson(querystring) {
			var argName = $("#argName").val();
			$("#argName").val(argName);
			$("#argName").change();
			$("#querystring").val(querystring);
			if ('aac002' == argName) {
				$("#aac002").val(querystring);
			}
			var registerHomeSickbedData = $("#registerHomeSickbed").serialize();
			$("#querystring").attr("disabled", true);
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001HomeSickbed.action",
					registerHomeSickbedData, function(json) {
						if (!checkJSONResultNew(json)) {
							init();
							return;
						}
						if (json.data != "no" && json.data.length > 1) {
							if (powersi.isnull(querystring)) {
								chooseAac002HomeSickbed(json.message);
								$("#querystring").val(argName,json.message);
							} else {
								chooseAac002HomeSickbed(argName,querystring);
							}
							return;
						}
						if (json.data != "no") {
							$.each(json.data, function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
								if(key=="bka888_name"){
									if(value!="正常") {
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
		function chooseAac002HomeSickbed(argName,querystring) {
			var aka130 = powersi.trim($("#aka130").val());
			if (powersi.isnull(aka130)) {
				return;
			}
			var caa027 = powersi.trim($("#caa027").val());
			if (powersi.isnull(caa027)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!choosePersonAac001.action"
							+ "?inHospitalDTO.argName=" + argName 
							+ "&inHospitalDTO.querystring=" + querystring 
							+ "&inHospitalDTO.aka130=" + aka130
							+ "&inHospitalDTO.caa027=" + caa027,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								accessAac001(retValue.aac001,retValue.aaz267);
							}
						}
					}, 500, 600);
			/* popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!choosePersonAac001HomeSickbed.action?inHospitalDTO.querystring="
								+ querystring,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								accessAac001(retValue.aac001, retValue.aaz267);
							}
						}
					}, 400, 800); */
		}

		/*根据电脑号查询参保信息*/
		function accessAac001(aac001, aaz267) {
			if (powersi.isnull(powersi.trim(aac001))) {
				return;
			}
// 			if (powersi.isnull(powersi.trim(aaz267))) {
// 				return;
// 			}
			$("#bke548").val("");
			$("#querystring").val("");
			$("#querystring").val("");
			$("#bka977_name").val("1");
			$("#aac001").val(powersi.trim(aac001));
			$("#aaz267").val(powersi.trim(aaz267));
			$("#querystring").val(powersi.trim(aac001));
			$("#argName").val("aac001");
			$("#argName").change();
			var registerHomeSickbedData = $("#registerHomeSickbed").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001HomeSickbed.action",
					registerHomeSickbedData, function(json) {
						$("#bka977_name").val("");
						if (!checkJSONResultNew(json)) {
							init();
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
			$("#bka006").val("140");
			$("#btSaveRegister").prop("disabled", false);
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
					});
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
			var caa027 = powersi.trim($("#caa027").val());
			if (powersi.isnull(caa027)) {
				return;
			}
			var aka130 = powersi.trim($("#aka130").val());
			if (powersi.isnull(aka130)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!queryPersonFund.action?inHospitalDTO.aac001="
								+ aac001
								+ "&inHospitalDTO.aae140="
								+ aae140
								+ "&inHospitalDTO.caa027="
								+ caa027
								+ "&inHospitalDTO.aka130=" + aka130,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {

							} else {

							}
						}
					}, 400, 700);
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
			var caa027 = powersi.trim($("#caa027").val());
			if (powersi.isnull(caa027)) {
				return;
			}
			var aka130 = powersi.trim($("#aka130").val());
			if (powersi.isnull(aka130)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!cumulativeQuery.action?inHospitalDTO.aac001="
								+ aac001
								+ "&inHospitalDTO.baa027="
								+ baa027
								+ "&inHospitalDTO.caa027="
								+ caa027
								+ "&inHospitalDTO.aka130=" + aka130,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {

							} else {

							}
						}
					}, 400, 600);
		}

		/*保存家庭病床登记*/
		function saveRegisterHomeSickbed() {
			if (!checkForm()) {
				return;
			}
			inputOff();
			var aac003 = powersi.trim($("#aac003").val());

			if($("#argName").val()==="qrcode"){
				$("#aae013").val("1");
			}

			popupConfirm(
					"您确认保存[" + aac003 + "]的住院登记信息吗?",
					"确认",
					function(isOk) {
						if (isOk) {
							initDiagnoseinfo();
							$("#btSaveRegister").prop("disabled", true);
							var registerHomeSickbedData = $(
									"#registerHomeSickbed").serialize();
							postJSON(
									"${rootPath}/inhospital/InhospitalManagerAction!registerHomeSickbed.action",
									registerHomeSickbedData, function(json) {
										$("#btSaveRegister").prop("disabled",
												false);
										if (!checkJSONResultNew(json)) {
											return;
										}
										if (json.data != "no") {
											$.each(json.data, function(key,
													value) {
												if (!powersi.isnull(value)) {
													$("#" + key).val(value);
												}
											});
										}
										afterSaveRegister();
										saveRegisterHomeSickbedFlag = true;
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

		function initDiagnoseinfo() {
			var diagnoseinfo = gridDiagnoseinfo.getData();
			diagnoseinfo = powersi.tostring(diagnoseinfo);
			$("#diagnoseinfo").val(encodeURI(diagnoseinfo));
		}

		var num_diagnose_sn = 1;
		function chooseGridDiagnoseinfo() {
			var bka006 = $("#bka006").val();

			var url = "";
			var baa027 = $("#baa027").val();
			var centerId = "0";
			if (baa027 != '' && baa027 != undefined) {
				centerId = baa027.substr(0, 4);
			}

			//106种单病种，查询当前大病种下的小病种作为副诊断信息
			//106种单病种，不需要申请 【20180227 新增需求】
			var aaz266 = $("#aaz266").val();
			var is106 = ("1" == aaz266 ? true : false);

			if (is106) {
				var aka120 = $("#bka026").val();
				url = "${rootPath}/common/CommonManagerAction!chooseDisease.action?ka06dto.caa027="
						+ $("#caa027").val() + "&aka120=" + aka120;
			} else {
				url = "${rootPath}/common/CommonManagerAction!chooseDisease.action?ka06dto.caa027="
						+ $("#caa027").val();
			}
			popupDialog({
				url : url,
				onClosed : function() {
					var retValue = this.returnValue;
					if (retValue) {

						var jsonFee = {
							"aka120" : retValue.aka120,
							"aka121" : retValue.aka121,
							"bke558" : num_diagnose_sn++,
							"bke559" : "1"
						};
						gridDiagnoseinfo.add(jsonFee);
					}
				}
			}, 500, 600);
		}

		function doDelete() {
			if (typeof (gridDiagnoseinfo.getSelectedRows()) == "undefined"
					|| gridDiagnoseinfo.getSelectedRows() == null
					|| gridDiagnoseinfo.getSelectedRows() == "") {
				popupAlert("请选择要删除的其它诊断", "提示", "info");
				return;
			}
			popupConfirm("是否删除已选择的其它诊断?", "确认", function(isOk) {
				gridDiagnoseinfo.deleteSelectedRow();
			});
		}
	</script>
</body>
</powersi:html>