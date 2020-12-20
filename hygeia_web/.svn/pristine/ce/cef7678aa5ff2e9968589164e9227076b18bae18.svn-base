<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="回退申请" />
</head>
<body>
	<powersi:form id="backApply" namespace="/particular"
		action="InhospitalManagerAction!applyBack.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btnApplyBack" label="保存"
					onclick="saveApplyBack()" />
					<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>

			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="就医登记号"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="findBaseInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac002" key="社会保障号码"
						name="inHospitalDTO.aac002" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="aac003" key="姓名"
						name="inHospitalDTO.aac003" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="基本信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别"  readonly="true"/>
						<powersi:hidden id="aac004" name="inHospitalDTO.aac004" />
						
					<powersi:textfield id="bka004_name"
						name="inHospitalDTO.bka004_name" label="人员类别" readonly="true" />						
						<powersi:hidden id="bka004" name="inHospitalDTO.bka004" />
					
					<powersi:textfield id="bka006_name"
						name="inHospitalDTO.bka006_name" label="待遇类型" readonly="true" />	
						<powersi:hidden id="bka006" name="inHospitalDTO.bka006" />
					
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="入院科室" readonly="true" />
						<powersi:hidden id="bka019" name="inHospitalDTO.bka019" />
					
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka022" name="inHospitalDTO.bka022"
						label="入院病区" readonly="true" />
						<powersi:hidden id="bka021" name="inHospitalDTO.bka021" />
								
					<powersi:textfield id="bka026_name"
						name="inHospitalDTO.bka026_name" label="入院诊断" readonly="true" />
						
					<powersi:textfield id="akc264"
						name="inHospitalDTO.akc264" label="医疗总费用" readonly="true" />
						
					<powersi:textfield id="bka811"
						name="inHospitalDTO.bka811" label="医保记账金额" readonly="true" />
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="bka032"
							name="inHospitalDTO.bka032" label="结算时间" readonly="true" />
							
					<powersi:codeselect id="aae101" name="inHospitalDTO.aae101"
						label="申请类别" codeType="amc060" showValue="true" required="true"/>
				</powersi:editorlayout-row>
	
			<powersi:editorlayout-row>
					<powersi:textarea label="申请理由" id="bka820"
						name="inHospitalDTO.bka820" colspan="8" required="true" />
			</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		
		<powersi:hidden id="bka025" name="inHospitalDTO.bka025" />
		<powersi:hidden id="bka501" name="inHospitalDTO.bka501" />
		<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
		<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
		<powersi:hidden id="aaz217" name="inHospitalDTO.aaz217" />
		<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="bka895" name="inHospitalDTO.bka895" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var objCard = null;
		$(document).ready(function() {
			$("#tracestring").focus();
			initInputParam();
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
								$("#bka895").val("bka100");
								$("#tracestring").val(json.data.bka100);
								findBaseInfo("readic");
								$("#arg_name").val("");
							}
						}
					});
		}    
			
		function initInputParam(){
			$("#btnApplyBack").prop("disabled", true);
		}

		function findBaseInfo(para) {
			if (window.event.keyCode == 13 || para == 'readic') {
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
			$("#querystring").val(tracestring);
			var backApply = $("#backApply").serialize();
			postJSON(
					"${rootPath}/inhospital/InhospitalManagerAction!findBizinfo.action",
					backApply, function(json) {
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
		
		function afterFindInfo() {
			$("#btnApplyBack").prop("disabled", false);
		}
		
		function saveApplyBack() {	
			if (!checkForm()) {
				return;
			}
			$("#btnApplyBack").prop("disabled", true);
			var registerData = $("#backApply").serialize();
			postJSON("${rootPath}/inhospital/InhospitalManagerAction!applyBack.action",
					registerData, function(json) {
						$("#btnApplyBack").prop("disabled", false);
						if (!checkJSONResult(json)) {
							return;
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
							$("#btReset").click();
							$("#btnApplyBack").prop("disabled", true);
						}
					});
		}		
	</script>
</body>
</powersi:html>