<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="住院登记" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="register" namespace="/inhospital"
		action="InhospitalManagerAction!register.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveRegister" label="保存登记" key="button_save_register" 
					onclick="saveRegister()" />
				<powersi:button id="btFundStatusQuery" label="基金状态" key="button_query"
					onclick="fundStatusQuery()" />
				<powersi:button id="btcumulative" label="查询累计" key="button_query"
					onclick="cumulativeQuery()" />
				<powersi:button id="btRegisterReset" label="重置" key="button_reset"
					onclick="registerReset()" />
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="0%,10%,10%,10%,15%,10%,10%,10%,15%">
				<powersi:editorlayout-row>
					<td>
						<powersi:codeselect id="caa027" key="caa027" name="inHospitalDTO.caa027"
						 cssClass="select2" codeType="caa027" required="true" headerKey="0" />
					</td>
					<powersi:codeselect id="argName" name="inHospitalDTO.argName"
						label="查询条件" cssClass="select2" list="#{'aac002':'身份证号码' }" />
					<td colspan="2">
						<powersi:textfield id="tracestring" name="tracestring"
							title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
							onkeyup="findAac001()" buttonText="读卡" buttonId="readic_button"
							buttonDisabled="false"  />
					</td>
						<powersi:textfield id="bkm902"  label="住院号:"
						required="true" onkeydown="keydown(this)"/>	
					<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="基本信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="身份证号" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
 						label="电脑号" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka035_name"
						name="inHospitalDTO.bka035_name" label="人员类别" readonly="true" />
				    <powersi:textfield id="bka010" name="inHospitalDTO.bka010"
						label="住院次数" readonly="true" />
					<powersi:textfield id="bka888_name"
						name="inHospitalDTO.bka888_name" label="基金状况" readonly="true" />
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="所属单位" readonly="true" />
					<%-- <powersi:textfield id="bac032_name"
 						name="inHospitalDTO.bac032_name" label="困难人员类别" readonly="true" /> --%>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac148_name"
						name="inHospitalDTO.aac148_name" label="补助类型" readonly="true" />
					<powersi:textfield id="aac013_name"
						name="inHospitalDTO.aac013_name" label="人员状态" readonly="true" />
					<powersi:textfield id="bac001_name"
						name="inHospitalDTO.bac001_name" label="公务员级别" readonly="true"  />
					<powersi:textfield id="baa027_name"
						name="inHospitalDTO.baa027_name" label="人员所属中心" readonly="true"  />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aaa129"
						name="inHospitalDTO.aaa129" label="区/县" readonly="true" />
					<powersi:textfield id="aaf013_name"
						name="inHospitalDTO.aaf013_name" label="乡镇/街道" readonly="true" />
					<powersi:textfield id="aab069_name"
						name="inHospitalDTO.aab069_name" label="村/社区" readonly="true" />
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
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
					<powersi:hidden id="akc193" name="inHospitalDTO.akc193" />
					<powersi:hidden id="aaz266" value="" />
					<powersi:hidden id="applytype" name="inHospitalDTO.applyType" />
					<powersi:hidden id="aac148" name="inHospitalDTO.aac148" />
					<powersi:hidden id="bac001" name="inHospitalDTO.bac001" />
					
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
						codeFilter="data_value like '12_' and data_value <> '126' and data_value <> '124'"
						codeLocal="${inHospitalDTO.aaa027}" required="true"
						displayonly="false" showValue="true" headerKey="0"/>
					<powersi:textfield id="akc190" name="inHospitalDTO.akc190"
						label="住院号" onkeydown="keydown(this)" required="true"
						readonly="true" />
					<powersi:textfield id="bkz101"
						name="inHospitalDTO.bkz101" label="入院诊断" title="请输入入院诊断"
						onkeydown="keydown(this)" required="true"
						readonly="true" buttonText="选择" buttonId="bka026_button"
						buttonDisabled="false" onbuttonclick="chooseDisease('bkz101', 'akc193', 'akf001')" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="akf001" name="inHospitalDTO.akf001"
						label="入院科室"  required="true" readonly="true" />	
					<powersi:textfield id="bka021" name="inHospitalDTO.bka021"
						label="入院病区"  required="true" readonly="true" />		
					<powersi:textfield id="ake020" name="inHospitalDTO.ake020"
						label="床位号"  required="true" readonly="true" />			
					<powersi:codeselect id="ake022" name="inHospitalDTO.ake022"
						label="主治医生" cssClass="select2" list="#request.ake022List"
						headerKey="" headerValue="请选择..." showValue="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka245" name="inHospitalDTO.bka245"
						label="预付款"  readonly="false" />
					<powersi:codeselect id="bka061" name="inHospitalDTO.bka061"
						label="病情严重程度" cssClass="select2" readonly="false" codeType="bka061"
						headerKey="" headerValue="请选择..." showValue="true"
						codeLocal="${inHospitalDTO.aaa027}" />
					<powersi:textfield id="aae004" name="inHospitalDTO.aae004"
						label="联系人" readonly="false" />
					<powersi:textfield id="aae005" name="inHospitalDTO.aae005"
						label="联系电话" readonly="false" required="true"/>	
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae006" name="inHospitalDTO.aae006"
						label="联系地址" colspan="3" readonly="false" />
					<powersi:textfield id="ake024" name="inHospitalDTO.ake024"
						label="病情备注" colspan="3" readonly="false" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="其它诊断信息">		
			<powersi:panelbox-toolbar>
				<powersi:button id="btn_selDiagnoseinfo" label="其它诊断" key="button_query" onclick="chooseGridDiagnoseinfo()" />
				<powersi:button id="btn_deleteDiagnoseinfo" label="删除" key="button_delete" onclick="doDelete()" />
			</powersi:panelbox-toolbar>
		 	<powersi:datagrid id="gridDiagnoseinfo"  delayLoad="true" showReload="false" 
		 		autoWidth="true" enabledSort="false" alternatingRow="true" checkbox="true" 
		 		colDraggable="false" height="200" pageSize="10" rownumbers="true" > 
		 		<powersi:datagrid-column display="序号" name="bke558" width="0%" hide="true"  />
	            <powersi:datagrid-column display="编码" name="aka120" width="40%"  />
	            <powersi:datagrid-column display="名称" name="aka121" width="60%" />
	            <powersi:datagrid-column display="类型" name="bke559" width="0%" hide="true"  />
     		</powersi:datagrid>
    		<powersi:hidden id="diagnoseinfo" name="diagnoseinfo" />
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
		<powersi:hidden id="aab001" name="inHospitalDTO.aab001" />
		<powersi:hidden id="aac006" name="inHospitalDTO.aac006" />
		<powersi:hidden id="bka977_name" name="inHospitalDTO.bka977_name" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
	    var aaa027 = $("#aaa027").val().substring(0,4);

		var saveRegisterFlag = false;
		var objCard = null;
		$(document).ready(function() {
			$("#tracestring").focus();
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

		/*读卡获取后台信息*/
		function readic() {
			var aae030 = powersi.trim($("#aae030").val());
			registerReset();
			if (!powersi.isnull(aae030)) {
				$("#aae030").val(aae030);
			}
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findAac002("");
		}

		/*读卡*/
		function iReadCardBase() {
			loadCardControl();
			if (objCard != null && objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}

		/*初始化*/
		function init() {
			saveRegisterFlag = false;
			$("#aaa027").val("");
			$("#akc193").val("");
			$("#baa027").val("");
			$("#aae140").val("");
			$("#bke548").val("");
			$("#querystring").val("");
			$("#tracestring").val("");
			$("#bka977_name").val("");
			$("#aac001").val("");
			$("#aac002").val("");
			$("#aac003").val("");
			$("#tracestring").prop("disabled", false);
			$("#btSaveRegister").prop("disabled", true);
			//待遇类型
			$("#bka006").val("120");
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
			$("#alc023").val("2");
			$("#bka510").val("1");
			$("#bka061").val("");
			$("#bka061").change();
			
			gridDiagnoseinfo.reset();
		}

		/*重置界面*/
		function registerReset() {
			$("#btReset").click();
			init();
		}

		function keydown(param) {
			if (event.keyCode == "13") {
				var filed_name = param.id;
				if ("akc190" == filed_name) {
					var akc190 = powersi.trim($("#akc190").val());
					if (powersi.isnull(akc190)) {
						return $("#akc190").focus();
					}
					return $("#bkz101").focus();
				}
				if ("bkz101" == filed_name) {
					return chooseDisease('bkz101', 'akc193', 'akf001');
				}
				if ("bka018" == filed_name) {
					return choosebka018();
				}
				if("bkm902" == filed_name){
					queryHospInfo();
				}
			}
		}

		/*
		 * 待遇类型判断
		 */
		var res = true;
		function selectbka006() {
			var register = $("#btSaveRegister").prop("disabled");
			var registerData = $("#register").serialize();
			if (res) {
				res = register;
			}
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!selectbka006.action",
					registerData, function(json) {
						if (!checkJSONResultNew(json)) {
							$("#btSaveRegister").prop("disabled", true);
							return;
						}
						$("#btSaveRegister").prop("disabled", res);
					});
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
			$("#tracestring").prop("disabled", false);
		}

		/*不可录入状态*/
		function inputOff() {
			/*规避重复操作处理_不可用*/
			$("#tracestring").prop("disabled", true);
		}

		/*查询参保信息*/
		function findAac001() {
			if (window.event.keyCode == 13) {
				var tracestring = powersi.trim($("#tracestring").val());
				var argName = powersi.trim($("#argName").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入有效查询条件!");
					return;
				}
				var aae030 = powersi.trim($("#aae030").val());
				registerReset();
				if (!powersi.isnull(aae030)) {
					$("#aae030").val(aae030);
				}
				$("#tracestring").val(tracestring);
				$("#argName").val(argName);
				$("#tracestring").prop("disabled", true);
				findAac002(tracestring);
				$("#tracestring").prop("disabled", false);
			}
		}

		/*住院登记界面要支持：身份证号码、电脑号、社保卡号*/
		function findAac002(tracestring) {
			if (powersi.isnull(tracestring)) {
				$("#querystring").val("");
			} else {
				$("#querystring").val(tracestring);
			}
			var registerData = $("#register").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001.action",
					registerData, function(json) {
						if (!checkJSONResultNew(json)) {
							return;
						}
						if (json.data != "no" && json.data.length > 1) {
							if (powersi.isnull(tracestring)) {
								chooseAac002(json.message);
								$("#querystring").val(json.message);
								$("#tracestring").val(json.message);
							} else {
								chooseAac002(tracestring);
							}
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
			$("#tracestring").val("");
			$("#bka977_name").val("1");
			$("#aac001").val(powersi.trim(aac001));
			$("#tracestring").val(powersi.trim(aac001));
			var registerData = $("#register").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001.action",
					registerData, function(json) {
						$("#bka977_name").val("");
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
						afterFindAac001();
					});
		}

		function afterFindAac001() {
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
			var akf001 = $("#akf001").val();	//入院科室
			$("#bka021").empty();	//入院病区
			$("#ake020").empty();	//入院床位号
			$("#ake022").empty();	//医保医师
			
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
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!cumulativeQuery.action?inHospitalDTO.aac001="
								+ aac001 + "&inHospitalDTO.baa027=" + baa027,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {

							} else {

							}
						}
					}, 400, 600);
		}

		/*保存住院登记*/
		function saveRegister() {
			if (!checkForm()) {
				return;
			}
			inputOff();
			var aac003 = powersi.trim($("#aac003").val());
			
			popupConfirm("您确认保存[" + aac003 + "]的住院登记信息吗?", "确认", function(isOk){
				if(isOk){
					initDiagnoseinfo();
					$("#btSaveRegister").prop("disabled", true);
					var registerData = $("#register").serialize();
					var akc190 = $("#akc190").val();
					postJSON(
							"${rootPath}/universal/InhospitalManagerAction_HIS!register.action?akc190="+akc190,
							registerData, function(json) {
								$("#btSaveRegister").prop("disabled", false);
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
			if (baa027 != ''&& baa027 != undefined) {
				centerId = baa027.substr(0, 4);
			}

			//106种单病种，查询当前大病种下的小病种作为副诊断信息
			//106种单病种，不需要申请 【20180227 新增需求】
			var aaz266 = $("#aaz266").val();
			var is106 = ("1"==aaz266?true:false);
			
			if(is106){
				var aka120 = $("#bka026").val();
				url = "${rootPath}/common/CommonManagerAction!chooseDisease.action?ka06dto.caa027="
						+ $("#caa027").val()
						+ "&aka120=" + aka120;
			}else{
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
		
		//查询结算云临时表 住院登记信息
		function queryHospInfo(){
			//获取住院号
			var akc190 = $("#bkm902").val();//键入的住院号
			if(akc190 == null || akc190 == ''){
				popupAlert("请输入住院号！");
				return;
			}
			//获取姓名
			var aac001 = $("#aac001").val();
			//清屏
			//doClear();
			var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryInHospitalPersonInfo.action?akc190="+akc190+"&aac001="+encodeURI(encodeURI(aac001,"UTF-8"));
			postJSON(url,{},function(ret){
				//记录结算云这边的住院登记信息
				var personinfo = ret.data[0];
				if(personinfo == null || personinfo == ''){
					popupAlert("无此人登记信息！");
					return;
				}

				$("#akc190").val(personinfo.akc190);//住院号
				$("#akf001").val(personinfo.akf001);//入院科室
				$("#bka021").val(personinfo.bka022);//入院病区
				$("#ake020").val(personinfo.bka023);//床位号
				arr = personinfo.aae036.split(" ");
				var inhosp = arr[0].replace(/-/g,"");
				$("#bka017").val(inhosp);//入院登记时间
				//$("#aae005").val(personinfo.);//联系电话 
				//$("#bka026_name").val("其他");//入院诊断
				//$("#bka026").val("30");//入院诊断
				$("#akc190").val(personinfo.akc190);
			});
		}
		
	</script>
</body>
</powersi:html>