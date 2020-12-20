<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<powersi:html>
<head>
<powersi:head title="家庭病床收费" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
	<powersi:form id="bizForm" namespace="/diagnose"
		action="DiagnoseRegisterAction!register.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:radio id="operFlag" name="operFlag"
					list="#{'1': '收费操作 ', '2':'改费操作'}" value='1' colspan="2"
					onclick="dealoperFlag()" />
				<powersi:button id="btnCalc0" key="button_calc0" onclick="calc(0);"
					disabled="false" />
				<powersi:button id="btnCalc1" key="button_calc1" onclick="calc(1);"
					disabled="true" />
				<powersi:button id="btnFund" key="基金状态查询"
					onclick="fundStatusQuery()" disabled="false" />
				<powersi:button id="btnReset" key="button_clear"
					onclick="resetpage();" disabled="false" />
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
				<powersi:button id="btnPrint" key="button_print"
					onclick="printreport();" disabled="false" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,12%,8%,12%,8%,12%,8%,12%,8%,12%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="arg_name" name="diagnoseInfoDTO.arg_name"
						label="查询条件" cssClass="select2"
						list="#{'aac002':'身份证号码' }" />
					<td colspan="2"><powersi:textfield id="querystring" name="querystring" 
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="getPersonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" /></td>
					<powersi:textfield id="aac002" key="社会保障号码"
						name="diagnoseInfoDTO.aac002" readonly="true" />
					<powersi:textfield id="bka008" key="单位名称"
						name="diagnoseInfoDTO.bka008" readonly="true" />
					<powersi:textfield id="aac003" key="姓名"
						name="diagnoseInfoDTO.aac003" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<tags:mediBizInfo_bizinfo_datagrid/>
		<tags:mediBizInfo_diagnose_charge_fm />
		<div id="divTab2" class="row">
			<div class="col-8">
		   <tags:mediFeeInfo_datagrid_fm />
			</div>
		    <div class="col-4">
		    <tags:mediHisPayInfo />
		    </div>
		</div>
		<powersi:hidden id="reduceflag" key="退改费类型"
			name="diagnoseInfoDTO.reduceflag" value="0" />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="caa027" name="inHospitalDTO.caa027" value="4303-zg"/>
		<powersi:hidden id="aaa027" name="diagnoseInfoDTO.aaa027" />
		<!-- TS19072500248 - 【需求开发】读卡进行密码签名验证 ，前台获取 ，后台验证  陈洁 20190801  -->
		<powersi:hidden id="bkz300" name="diagnoseInfoDTO.bkz300" />  
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
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

	/*读卡*/
	function iReadCardBase() {
		$("#bke548").val("");
		$("#querystring").val("");
		$("#arg_name").val("");
		loadCardControl();
		if (objCard.object != null) {
			var bke548 = null;// 读卡返回
			bke548 = objCard.ReadCardBase();
			$("#bke548").val(bke548);
		}
	}

	/*读卡获取后台信息*/
	function readic() {
		var aae030 = powersi.trim($("#aae030").val());
		var ake007 = powersi.trim($("#ake007").val());
		var bka006 = powersi.trim($("#bka006").val());
		resetpage();
		if (!powersi.isnull(aae030)) {
			$("#aae030").val(aae030);
		}
		if (!powersi.isnull(ake007)) {
			$("#ake007").val(ake007);
		}
		if (!powersi.isnull(bka006)) {
			$("#bka006").val(bka006);
		}
		iReadCardBase();
		if (powersi.isnull($("#bke548").val())) {
			return;
		}
		var bizFormData = $("#bizForm").serialize();
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!readic.action",
				bizFormData, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data != "no") {
						if (!powersi.isnull(json.data.bka100)) {
							$("#arg_name").val("bka100");
							$("#querystring").val(json.data.bka100);
							getPerson("readic");
							$("#arg_name").val("");
						}
					}
				});
	}
	
	
	function dealoperFlag() {
		var operFlag = $(':radio[name="operFlag"]:checked').val();
		if (operFlag == 1) {
			$("#stext").attr("disabled", false);
			$("#bka056").attr("disabled", false);
			$("#bka057").attr("disabled", false);
			$("#bka058").attr("disabled", false);
			$("#btn_enter").attr("disabled", false);
			$("#btn_delete").attr("disabled", false);
			$("#reduceflag").val("0");//收费
		} else {
			$("#stext").attr("disabled", true);
			$("#bka056").attr("disabled", true);
			$("#bka057").attr("disabled", true);
			$("#bka058").attr("disabled", true);
			$("#btn_enter").attr("disabled", true);
			$("#btn_delete").attr("disabled", true);
			$("#reduceflag").val("1");//退费
		}
	}

	function calc(str) {
		if (!checkForm()) {
			return;
		}

		if ($("#aac001").val() == '' || $("#aac001").val() == -1) {
			popupAlert("请先检索到人员信息后再录入费用计算！");
			return $("#queryString").focus();
		}

		var feeinfo = gridFeeDetail.getData();
		feeinfo = powersi.tostring(feeinfo);
		$('#feeinfo').val(encodeURI(feeinfo));
		if (gridFeeDetail.getRowsCount() <= 0) {
			popupAlert("请先录入费用后再计算！");
			return;
		} 
		if (str == "0") {
			var saveItemData = $("#bizForm").serialize();
			postJSON(
					"${rootPath}/medicare/DiagnoseRegisterAction!diagnoseCalcSave.action",
					saveItemData, importCalcInfo);
		} else {
			var saveItemData = $("#bizForm").serialize();
			postJSON(
					"${rootPath}/medicare/DiagnoseRegisterAction!diagnoseCalcEnPay.action",
					saveItemData, importCalcInfo);
		}
	}

	function importCalcInfo(json) {
		if (!checkJSONResult(json)) {
			return;
		}
		popupInfo(json.message);
		if (json.message == '门诊试算成功！') {
			$("#em_jsq").val('');
			document.all.em_jsq.focus();
			$("#btnCalc1").attr("disabled", false);
		}
		if (json.message == '门诊收费成功！') {
			var aaz217 = json.data[0].aaz217;
			$("#aaz217").val(aaz217);
			$("#btnCalc0").attr("disabled", true);
			$("#btnCalc1").attr("disabled", true);
			$("#btnPrint").attr("disabled", false);
			$("#querystring").attr("disabled", true);
			//打印结算单
			printreport();
		}
		var ld_allmoney = json.data[0].allpay;
		var ld_zfy = json.data[0].akc264;
		var ld_fund_pay = json.data[0].bka832;
		var ld_self_com = json.data[0].bka831;
		var ld_self_own = json.data[0].cash_pay_own;
		var ld_cash = json.data[0].akb067;
		var ld_zh = json.data[0].akb066;
		var ld_hosp_pay = json.data[0].bka842;
		$("#st_zfy").val((parseFloat(ld_allmoney)).toFixed(2));
		$("#st_bcfy").val((parseFloat(ld_zfy)).toFixed(2));
		$("#st_jjzf").val(parseFloat(ld_fund_pay).toFixed(2));
		$("#st_self_com").val((parseFloat(ld_self_com)).toFixed(2));
		$("#st_self_own").val(parseFloat(ld_self_own).toFixed(2));
		$("#st_xjzf").val((parseFloat(ld_cash)).toFixed(2));
		$("#em_grzhzf").val((parseFloat(ld_zh)).toFixed(2));
		$("#st_hosp_pay").val(parseFloat(ld_hosp_pay).toFixed(2));
	}

	function init() {
		$("#querystring").val('');
		/*签名串在页面刷新时进行清空 陈洁 20190801  */
		$("#bkz300").val('');
		$("#aaz267").val('');
		$("#aae030").val('');
		$("#aae031").val('');
		$("#aac001").val('');
		$("#aac003").val('');
		$("#aac002").val('');
		$("#aac006").val('');
		$("#bka035").val('');
		$("#bka035_name").val('');
		$("#bka008").val('');
		$("#aab001").val('');
		$("#aae005").val('');
		$("#aaz217").val('');
		$("#akc193").val('');
		$("#bkz101").val('');
		$("#bka021").val('');
		$("#akf001").val('');
		$("#bka003").val('');

		var curDate = new Date();
		var month = curDate.getMonth() + 1 < 10 ? "0"
				+ (curDate.getMonth() + 1) : curDate.getMonth() + 1;
		var day = curDate.getDate() < 10 ? "0" + (curDate.getDate())
				: curDate.getDate();
		var hours = curDate.getHours() < 10 ? "0" + (curDate.getHours())
				: curDate.getHours();
		var min = curDate.getMinutes() < 10 ? "0" + (curDate.getMinutes())
				: curDate.getMinutes();
		var seconds = curDate.getSeconds() < 10 ? "0"
				+ (curDate.getSeconds()) : curDate.getSeconds();
		var curDateStr = curDate.getFullYear() + "-" + month + "-" + day
				+ " " + hours + ":" + min + ":" + seconds;
		$("#aae030").val(curDateStr);
		$("#ake007").val(curDateStr);
		$("#aae030").prop("disabled", false);
		$("#aae030_reg").val('');

		$("#st_zfy").val('0.00');
		$("#st_jjzf").val('0.00');
		$("#st_self_com").val('0.00');
		$("#st_self_own").val('0.00');
		$("#st_bcfy").val('0.00');
		$("#st_hosp_pay").val('0.00');
		$("#em_grzhzf").val('0.00');
		$("#st_last_balance").val('0.00');
		$("#st_xjzf").val('0.00');
		$("#em_jsq").val('0.00');
		$("#st_out").val('0.00');

		$("#ake006").val('');
		$("#aka063").val('');
		$("#bka052").val('');
		$("#bka054").val('');
		$("#bka056").val('');
		$("#bka057").val('');
		$("#bka058").val('');
		$("#aka069").val('');
		$("#baa027_name").val('');

		$("#btnSave").attr("disabled", false);
		$("#btnCalc0").attr("disabled", false);
		$("#btnCalc1").attr("disabled", true);
		$("#stext").attr("readonly", false);

		$("#bka006").val("140");
		$("#bka006").change();
		$("#bka006").attr("disabled", false);
		$("#bka006_reg").val('');

		//入院科室
		$("#akf001").val("");
		//入院病区
		$("#bka021").empty();
		//医保医师
		$("#bka503").empty();
		$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
		$("#bka503").append("<option value='' >" + "请选择..." + "</option>");
		$("#bka021").change();
		$("#bka503").change();
		$("#akf001").change();

		$("#querystring").attr("disabled", false);
		$("#querystring").val('');
		gridFeeDetail.reset();

		$("#querystring").focus();
	}

	/*重置界面*/
	function resetpage() {
		$("#btReset").click();
		init();
	}

	function getPersonInfo(para){
		if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			if("0" == $("#reduceflag").val()){
				getPerson();
			}else{
				afterChoosePerson();
			}
		}
	}
	
	
	function getPerson(para) {
			var bka006 = powersi.trim($("#bka006").val());
			if (powersi.isnull(bka006)) {
				alert("请选择待遇类型再获取人员信息!");
				return;
			}
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件!");
				return;
			}
			var aae030 = powersi.trim($("#aae030").val());
			var argname=$("#arg_name").val();
			resetpage();
			if (!powersi.isnull(aae030)) {
				$("#aae030").val(aae030);
			}
			$("#bka006").val(bka006);
			$("#bka006").change();
			$("#querystring").val(querystring);
			$("#arg_name").val(argname);
			if (para == "readic") {
				$("#arg_name").val("bka100");
			} else {
				$("#bke548").val('');
			}
			var indi_id = "0";
			postJSON(
					"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name="
							+ $("#arg_name").val()
							+ "&diagnoseInfoDTO.arg_value="
							+ $("#querystring").val()
							+ "&diagnoseInfoDTO.bka006="
							+ $("#bka006").val()
							+ "&diagnoseInfoDTO.aae030="
							+ $("#aae030").val()
							+ "&diagnoseInfoDTO.aka130="
							+ $("#aka130").val()
							+ "&diagnoseInfoDTO.bke548="
							+ $("#bke548").val()
							+ "&diagnoseInfoDTO.bkz300="
							+ $("#bkz300").val(),
					function(json) {
						$("#bke548").val('');
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.message == 'multi-row') {
							choosepersonlist(querystring);
							return;
						}
						if (json.errortype == '0') {
							$.each(json.data.personinfo, function(key,
									value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
									$("[name='diagnoseInfoDTO." + key +"']").val(value);
								}
							});
							$("#aae030_reg").val($("#aae030").val());
							$("#bka006_reg").val($("#bka006").val());
							$("#aae030").prop("disabled", true);
							$("#bka006").prop("disabled", true);
							if ("0" == json.data.personinfo.bac001){
								$("#bac001").val("000");
							}
							if (json.data.clinicapplyinfo != null){
								$("#aaz267").val(json.data.clinicapplyinfo.aaz267);
							}
						} 
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
		}

	function choosepersonlist(querystring) {
		popupDialog(
				{
					url : "${rootPath}/diagnose/GetPersonInfoAction!chooseperson.action?diagnoseInfoDTO.arg_value="
							+ querystring,
					onClosed : function() {
						var ret = this.returnValue;
						if (ret) {
							indi_id = ret.aac001;
							postJSON(
									"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name=aac001&diagnoseInfoDTO.arg_value="
											+ indi_id
											+ "&diagnoseInfoDTO.bka006="
											+ $("#bka006").val()
											+ "&diagnoseInfoDTO.aae030="
											+ $("#aae030").val()
											+ "&diagnoseInfoDTO.aka130="
											+ $("#aka130").val(),
									function(json) {
												$("#bke548").val('');
												if (!checkJSONResult(json)) {
													return;
												}
												if (json.errortype == '0') {
													$.each(json.data.personinfo, function(key,
															value) {
														if (!powersi.isnull(value)) {
															$("#" + key).val(value);
															$("[name='diagnoseInfoDTO." + key +"']").val(value);
														}
													});
													$("#aae030_reg").val($("#aae030").val());
													$("#bka006_reg").val($("#bka006").val());
													$("#aae030").prop("disabled", true);
													$("#bka006").prop("disabled", true);
													if ("0" == json.data.personinfo.bac001){
														$("#bac001").val("000");
													}
													if (json.data.clinicapplyinfo != null){
														$("#aaz267").val(json.data.clinicapplyinfo.aaz267);
													}
												} 
												if (!powersi.isnull(json.message)) {
													popupInfo(json.message);
												}
									});
						}
					}
				}, 500, 600);
	}
	
	
	function afterChoosePerson() {
		popupDialog(
				{
					url : "${rootPath}/diagnose/GetPersonInfoAction!getPersonBusi.action?diagnoseInfoDTO.arg_name="
							+ $("#arg_name").val()
							+ "&diagnoseInfoDTO.arg_value="
							+ $("#querystring").val()
							+ "&diagnoseInfoDTO.aka130="
							+ $("#aka130").val()
							+ "&diagnoseInfoDTO.bka006="
							+ $("#bka006").val(),
					onClosed : function() {
						var ret = this.returnValue;
						if (ret != null) {
							var akb020 = ret.akb020;
							var aaz217 = ret.aaz217;
							var bka006 = ret.bka006;
							postJSON(
									"${rootPath}/medicare/GetPersonInfoAction!getPersonBusiDetail.action?akb020="
											+ akb020 + "&aaz217=" + aaz217 + "&aka130=" + aka130,
									function(json) {
										if (!checkJSONResult(json)) {
											return;
										}

										var operFlag = $(
												':radio[name="operFlag"]:checked')
												.val();
										//回显科室，病区，医师
										var bka020;
										if(json.data.bizinfo[0].bka020!=""){
											bka020 = json.data.bizinfo[0].bka020;
										}else{
											bka020 = "请选择...";
										}
										$("#select2-chosen-1").text(bka020);
										var bka022;
										if(json.data.bizinfo[0].bka022!=""){
											bka022 = json.data.bizinfo[0].bka022;
										}else{
											bkc154 = "请选择...";
										}
										$("#select2-chosen-2").text(bka022);
										var ake022;
										if(json.data.bizinfo[0].ake022!=""){
											ake022 = json.data.bizinfo[0].ake022;
										}else{
											ake022 = "请选择...";
										}
										$("#select2-chosen-3").text(ake022);

										$("#aac001").val(
												json.data.bizinfo[0].aac001);
										$("#aac003").val(
												json.data.bizinfo[0].aac003);
										$("#aac002").val(
												json.data.bizinfo[0].aac002);
										$("#aac006").val(
												json.data.bizinfo[0].aac006);
										$("#aac004").val(
												json.data.bizinfo[0].aac004);
										$("[name='diagnoseInfoDTO.bka035']")
												.val(json.data.bizinfo[0].bka035);
										$("[name='diagnoseInfoDTO.baa027']")
												.val(json.data.bizinfo[0].baa027);
										$("#bka008").val(
												json.data.bizinfo[0].bka008);
										$("#bac001").val(
												json.data.bizinfo[0].bac001);
										$("#aka130").val(
												json.data.bizinfo[0].aka130);
										$("#aae030").val(
												json.data.bizinfo[0].aae030);
										$("#aae030_reg").val(
												json.data.bizinfo[0].aae030);
										$("#ake007").val(
												json.data.bizinfo[0].aae030);
										$("[name='diagnoseInfoDTO.bka006']")
												.val(json.data.bizinfo[0].bka006);
										$("#bka006_reg").val(
												json.data.bizinfo[0].bka006);
										$("#bkz101").val(
												json.data.bizinfo[0].bkz101);
										$("#bka025").val(
												json.data.bizinfo[0].bka025);
										$("#aaz217").val(
												json.data.bizinfo[0].aaz217);
										$("#akc193").val(
												json.data.bizinfo[0].akc193);
										$("[name='diagnoseInfoDTO.akf001']").val(json.data.bizinfo[0].akf001);
										$("[name='diagnoseInfoDTO.bka021'] option:selected").val(json.data.bizinfo[0].bka021);
										$("#bka003").val(
												json.data.bizinfo[0].bka003);
										$("#bka001").val(
												json.data.bizinfo[0].bka001);
										$("#kc21id").val(
												json.data.bizinfo[0].kc21id);
										$("#akb020").val(
												json.data.bizinfo[0].akb020);
										$("#bka039").val(
												json.data.bizinfo[0].bka039);
										$("#aae005").val(
												json.data.bizinfo[0].aae005);
										$("#bka020").val(
												json.data.bizinfo[0].bka020);
										$("#bka022").val(
												json.data.bizinfo[0].bka022);
										$("#aae140").val(
												json.data.bizinfo[0].aae140);
						
										$("#btnCalc0").attr("disabled",
												false);
										$("#ttt").attr("readonly", true);
										$("#aae030").attr("disabled", true);
										$("#bka006").attr("disabled", true);
										$("#operFlag").attr("disabled",
												true);
										if (operFlag == "1") {
											return $("#stext").focus();
										} else {
											//退费，获取就医登记号和费用批次号，装载明细到前台选择
											if (json.data == "no") {
												alert("没有找到就医明细数据，请检查是否还未收费！");
											} else {
												gridFeeDetail
														.loadData(json.data.feeinfo);
											}
										}
									});
							
						}
					}
				}, 500, 600);
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
							+ aac001 + "&inHospitalDTO.aae140=" + aae140+ "&inHospitalDTO.caa027=" 
							+ caa027 + "&inHospitalDTO.aka130=" + aka130,
					onClosed : function() {
						var retValue = this.returnValue;
						if (retValue) {

						} else {

						}
					}
				}, 400, 700);
	}
	
	/*打印结算单*/
	function printreport() {
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
	</script>
</body>
</powersi:html>