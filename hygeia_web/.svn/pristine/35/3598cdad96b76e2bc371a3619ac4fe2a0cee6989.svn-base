<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="转诊转院申请" />
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="referralTransferApplyid" namespace="/particular"
		action="ParticularManagerAction!referralTransferApply.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btreferralTransferApply" label="保存申请"
					onclick="savereferralTransferApply()" />
				<powersi:button id="btfundStatusQuery" label="基金状态"
					onclick="fundStatusQuery()" />
				<powersi:button id="btreferralTransferApplyReset" label="重置"
					onclick="referralTransferApplyReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>

			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bka600" name="inHospitalDTO.bka600"
						label="类型" codeType="bka600" codeLocal="${inHospitalDTO.aaa027}"
						onchange="checkbka600()" required="true" />
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="findBaseInfo()" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:textfield label="单位名称" id="aab069"
						name="inHospitalDTO.aab069" readonly="true" colspan="6" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="出生日期" id="aac006"
						name="inHospitalDTO.aac006" readonly="true" />
					<powersi:textfield label="科室" id="bka020"
							name="inHospitalDTO.bka020" readonly="true" />
					<powersi:textfield label="床位号" id="bka023"
							name="inHospitalDTO.bka023" readonly="true" />
					<powersi:textfield label="住院号" id="bka025"
							name="inHospitalDTO.bka025" readonly="true" />
					
				</powersi:editorlayout-row>

			</powersi:editorlayout>
		</powersi:groupbox>

		<powersi:panelbox title="详细信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="转入医院" id="akc172"
						name="inHospitalDTO.akc172" required="true" buttonText="选择"
						buttonId="hospse" onbuttonclick="chooseToHosp()"
						placeholder="请选择转入医院" readonly="true" />
					<powersi:textfield label="经办人" id="aae011"
						name="inHospitalDTO.aae011" readonly="true" />
					<powersi:textfield label="申请人姓名" id="bke128"
						name="inHospitalDTO.bke128" required="false" readonly="false" />
					<powersi:textfield label="申请日期" id="bke129"
						name="inHospitalDTO.bke129" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="病人意见" id="bke127"
						name="inHospitalDTO.bke127" required="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="转出医院医务科意见" id="bke008"
						name="inHospitalDTO.bke008" required="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="病情摘要" id="bke009"
						name="inHospitalDTO.bke009" required="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="备注" id="aae013"
						name="inHospitalDTO.aae013" colspan="8" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="akb020" name="inHospitalDTO.akb020" />
		<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
		<powersi:hidden id="aaz217" name="inHospitalDTO.aaz217" />
		<powersi:hidden id="ake017" name="inHospitalDTO.ake017" />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var savereferralTransferApplyFlag = false;
		var objCard = null;

		$(document).ready(function() {
			$("#tracestring").focus();
			initInputParam();
			//2017-12-06 珠海转诊转院需求 判断该医院是否为可转出医院
			if ($("#aaa027").val() == '440400') {
				postJSON("${rootPath}/particular/ParticularManagerAction!TransOutInhospital.action",
						function(json) {
					if (!checkJSONResult(json)) {
							return;
					}
					if (powersi.isnull(json.data)) {
						popupAlert("该医院不是【可转出】医院，不能办理转诊转院业务！");
						$("#btreferralTransferApply").prop("disabled", true);
					}
				});
			}
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
			$("#tracestring").val("");
			loadCardControl();
			if (objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}

		/*读卡获取后台信息*/
		function readic() {
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findInfo();
		}

		/*重置界面*/
		function referralTransferApplyReset() {
			var aac003 = powersi.trim($("#aac003").val());
			if (!powersi.isnull(aac003) && !savereferralTransferApplyFlag) {
				if (!confirm("界面已加载数据，您确认要重置吗？")) {
					return;
				}
			}
			$("#btReset").click();
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

		function initInputParam() {
			$('#bke127').val("同意");
			$('#bke008').val("同意");
			var aac003 = powersi.trim($("#aac003").val());
			if (!powersi.isnull(aac003)) {
				$("#bke128").val(aac003);
			}
			if($("#aaa027").val()=="440400"){
				 //隐藏科室控件
				$("#bka020").hide(); //隐藏控件框
				$("[for=bka020]").hide(); //隐藏控件文字
				$("#popupbutton_bka020").hide(); //隐藏控件按钮
				
				//隐藏床位号控件
				$("#bka023").hide(); //隐藏控件框
				$("[for=bka023]").hide(); //隐藏控件文字
				$("#popupbutton_bka023").hide(); //隐藏控件按钮
				
				//隐藏住院号控件
				$("#bka025").hide(); //隐藏控件框
				$("[for=bka025]").hide(); //隐藏控件文字
				$("#popupbutton_bka025").hide(); //隐藏控件按钮
			}
		}

		function findBaseInfo() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				$("#querystring").val("");
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入有效查询条件！");
					return;
				}
				$("#tracestring").prop("disabled", true);
				findInfo(tracestring);
				$("#tracestring").prop("disabled", false);
			}
		}

		function findInfo(tracestring) {
			if (powersi.isnull(powersi.trim($("#bka600").val()))) {
				popupAlert("请先选择类型！");
				return;
			}
			if (powersi.isnull(tracestring)) {
				$("#querystring").val("");
			} else {
				$("#querystring").val(tracestring);
			}
			var referralTransferApplyData = $("#referralTransferApplyid")
					.serialize();
			if ($("#aaa027").val() == "441800") {
				postJSON(
						"${rootPath}/particular/ParticularManagerAction!findPersonLastInhospInfo.action",
						referralTransferApplyData, function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							if (json.data != "no") {
								$.each(json.data, function(key, value) {
									if (!powersi.isnull(value)) {
										$("#" + key).val(value);
									}
								});
								afterFindInfo();
							}
							if (!powersi.isnull(json.message)) {
								popupInfo(json.message);
							}
						});
			}
			//珠海转诊转院：不需要判断上次的住院业务/门诊，只需要判断这个人是否正常缴费，正常参保
			if ($("#aaa027").val() == "440400") {
				postJSON(
						"${rootPath}/inhospital/InhospitalManagerAction!findAac001.action",
						referralTransferApplyData, function(json) {
							if (!checkJSONResult(json)) {
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
								$("#btreferralTransferApply").prop("disabled",true);
							}
						});
			}
		}

		function afterFindInfo() {
			$("#btreferralTransferApply").prop("disabled", false);
			initInputParam();
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
			var referralTransferApplyData = $("#referralTransferApplyid")
					.serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findAac001.action",
					referralTransferApplyData, function(json) {
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
							$("#btreferralTransferApply")
									.prop("disabled", true);
						}
					});
		}
		function checkbka600() {
		}

		//选择转入医院
		function chooseToHosp() {
			var aaa027 = $("#aaa027").val();
			popupDialog(
					{
						url : "${rootPath}/pages/biz/medicare/particular/chooseHospInfo.jsp?aaa027="
								+ aaa027,
						onClosed : function() {
							var ret = this.returnValue;
							if (ret != null) {
								var akb020 = ret.akb020; //转入医院编码
								var aab069 = ret.aab069; //转入医院名称
								//document.getElementById("akc172").value = aab069;
								//document.getElementById("ake017").value = akb020;
								var akb020_old = powersi.trim($("#akb020")
										.val());
								if (akb020_old == akb020) {
									popupAlert("请选择和当前不同的医院进行转诊转院！");
									$("#akc172").val("");
									$("#ake017").val("");
									$("#akc172").focus();
									return;
								}
								$("#akc172").val(aab069);
								$("#ake017").val(akb020);
							}
						}
					}, 500, 600);
		}

		function savereferralTransferApply() {
			if (!checkForm()) {
				return;
			}
			//需要校验所填信息    后续补作业
			var aac003 = powersi.trim($("#aac003").val());
			if (!confirm("您确认保存[" + aac003 + "]的转诊转院登记信息吗?")) {
				return;
			}
			if($("#aaa027").val() == "440400"){
					//2017-12-08 判断转入医院是否为“可转入”
					postJSON(
							"${rootPath}/particular/ParticularManagerAction!TransInInhospital.action",
							{
								"ake017" : $("#ake017").val()
							}, function(json) {
								if (!checkJSONResult(json)) {
									return;
								}
								if (powersi.isnull(json.data)) {
									popupAlert("所选转入医院不是【可转入】医院，不能办理转诊转院业务！");
									$("#btreferralTransferApply").prop("disabled", true);
									return;
								}else{
									$("#btreferralTransferApply").prop("disabled", true);
									var registerData = $("#referralTransferApplyid").serialize();
									postJSON(
											"${rootPath}/particular/ParticularManagerAction!referralTransferApply.action",
											registerData, function(json) {
												$("#btreferralTransferApply").prop("disabled", false);
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
												afterSavereferralTransferApply();
												savereferralTransferApplyFlag = true;
												if (!powersi.isnull(json.message)) {
													popupInfo(json.message);
												}
											});
								}
							});
			}else{
			$("#btreferralTransferApply").prop("disabled", true);
			var registerData = $("#referralTransferApplyid").serialize();
			postJSON(
					"${rootPath}/particular/ParticularManagerAction!referralTransferApply.action",
					registerData, function(json) {
						$("#btreferralTransferApply").prop("disabled", false);
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
						afterSavereferralTransferApply();
						savereferralTransferApplyFlag = true;
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
			}
		}

		function afterSavereferralTransferApply() {
			$("#btreferralTransferApply").prop("disabled", true);
		}
	</script>
</body>
</powersi:html>