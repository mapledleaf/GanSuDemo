<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<powersi:html>
<head>
<powersi:head title="门慢收费" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="bizForm" method="post">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:radio id="operFlag" name="operFlag"
					list="#{'1': '收费操作 ', '2':'改费操作'}" value='1' colspan="2"
					onclick="dealoperFlag()" />
				<powersi:button id="btnCalc0" key="button_calc0" onclick="calc(0);"
					disabled="true" />
				<powersi:button id="btnCalc1" key="button_calc1" onclick="calc(1);"
					disabled="true" />
				<powersi:button id="btnFund" key="基金状态查询"
					onclick="fundStatusQuery()" disabled="false" />
				<powersi:button id="btnReset" key="button_clear"
					onclick="resetpage();" disabled="false" />
				<powersi:button id="btnPrint" key="button_print"
					onclick="printreport();" disabled="false" />
				<powersi:button id="btnPrintBill" key="发票打印"
					onclick="billContent();" disabled="false" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring" name="querystring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="getPerson(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac002" key="社会保障号码"
						name="diagnoseInfoDTO.aac002" readonly="true" />
					<powersi:textfield id="aac001" name="diagnoseInfoDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="aac003" key="姓名"
						name="diagnoseInfoDTO.aac003" readonly="true" />
					<powersi:hidden id="reduceflag" key="退改费类型"
						name="diagnoseInfoDTO.reduceflag" value="0" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<tags:mediBizInfo_sp_charge />
		<tags:mediFeeInfo_datagrid />
		<tags:mediHisPayInfo />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
		<powersi:hidden id="bae410" name="diagnoseInfoDTO.bae410" value="03"/>
		<powersi:hidden id="comm_subsys_flag"
			name="diagnoseInfoDTO.comm_subsys_flag" value="1" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
	$(document).ready(function() {
		var curDate = new Date();
		var month = curDate.getMonth()+1 < 10 ?  "0"+(curDate.getMonth()+1) :curDate.getMonth()+1+"";
		var day = curDate.getDate() < 10 ? "0"+(curDate.getDate()) :curDate.getDate()+"";
		var curDateStr = curDate.getFullYear()+month+day;
		$("#bka017").val(curDateStr);
		$("#bka051").val(curDateStr);
		 
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
		if (operFlag == 1 ) {
			$("#stext").attr("disabled",false);
			$("#bka056").attr("disabled",false);
			$("#bka057").attr("disabled",false);
			$("#bka058").attr("disabled",false);
			$("#btn_enter").attr("disabled",false);
			$("#btn_delete").attr("disabled",false);
			$("#reduceflag").val("0");//收费
		}else{
			$("#stext").attr("disabled",true);
			$("#bka056").attr("disabled",true);
			$("#bka057").attr("disabled",true);
			$("#bka058").attr("disabled",true);
			$("#btn_enter").attr("disabled",true);
			$("#btn_delete").attr("disabled",true);
			$("#reduceflag").val("1");//退费
		}
	}
	
	function isNotBlank(str) {
		if (typeof (str) == "undefined" || str == null || str == "") {
			return false;
		} else {
			return true;
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
		if (str == "0") {
			var saveItemData = $("#bizForm").serialize();
			postJSON( "${rootPath}/medicare/DiagnoseRegisterAction!diagnoseCalcSave.action",
					saveItemData,
			  		importCalcInfo);
		} else {
			var saveItemData = $("#bizForm").serialize();
			postJSON( "${rootPath}/medicare/DiagnoseRegisterAction!diagnoseCalcEnPay.action",
					saveItemData,
			  		importCalcInfo);
		}
	}
	
	function importCalcInfo(json){
		if(!checkJSONResult(json)){
		    return;
	    }
		popupInfo(json.message);
		if(json.message == '门诊试算成功！'){
			$("#em_jsq").val('');
			document.all.em_jsq.focus();
			$("#btnCalc1").attr("disabled", false);
		}
		if(json.message == '门诊收费成功！'){
			var aaz217 = json.data[0].aaz217;
			$("#aaz217").val(aaz217);
			$("#btnCalc0").attr("disabled", true);
			$("#btnCalc1").attr("disabled", true);
			$("#btnPrint").attr("disabled", false);
			$("#operFlag").attr("disabled", false);
			$("#querystring").attr("disabled", true);
		}
		var ld_allmoney = json.data[0].allpay;
		var ld_zfy = json.data[0].akc264;
		var ld_fund_pay = json.data[0].bka832;
		var ld_self_com = json.data[0].bka831;
		var ld_self_own = json.data[0].cash_pay_own;
		var ld_cash = json.data[0].akb067;
		var ld_hosp_pay = json.data[0].bka842;
		
		$("#st_zfy").val( (parseFloat(ld_allmoney) ).toFixed(2));
		$("#st_bcfy").val( (parseFloat(ld_zfy) ).toFixed(2));
		$("#st_jjzf").val( parseFloat(ld_fund_pay).toFixed(2));
		$("#st_self_com").val( (parseFloat(ld_self_com)).toFixed(2) );
		$("#st_self_own").val( parseFloat(ld_self_own).toFixed(2) );
		$("#st_xjzf").val( (parseFloat(ld_cash)).toFixed(2));
		$("#st_hosp_pay").val( parseFloat(ld_hosp_pay).toFixed(2));
	}
	
	function resetpage(){
		$("#querykey").val('');
		$("#aaz267").val('');
		$("#aae030").val('');
		$("#aae031").val('');
		$("#aac001").val('');
		$("#aac003").val('');
		$("#aac002").val('');
		$("#aac006").val('');
		$("#bka004").val('');
		$("#bka004_name").val('');
		$("#bka008").val('');
		$("#aab001").val('');
		$("#bka017").val('');
		$("#bka025").val('');
		$("#ttt").val('');
		$("#aaz217").val('');
		$("#bka026_name").val('');
		$("#bka026").val('');
		$("#bka021").val('');
		$("#bka019").val('');
		$("#bka003").val('');
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
		$("#btnSave").attr("disabled", false);
		$("#btnCalc0").attr("disabled", true);
		$("#btnCalc1").attr("disabled", true);
		$("#operFlag").attr("disabled", false);
		$("#querystring").attr("disabled", false);
		$("#querystring").val('');
		gridFeeDetail.reset();
		$("#querykey").focus();
 		$("#reduceflag").val("0");
		$("input[type='radio'][name=operFlag][value='1']").attr("checked",true);
		$("#stext").attr("disabled", false);
		$("#bka056").attr("disabled", false);
		$("#bka057").attr("disabled", false);
		$("#bka058").attr("disabled", false);
	}

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
	
	function billContent() {
			//测试
			//$("#aaz217").val("sr100120170906000001");

			var aaz217 = $('#aaz217').val();
			var bae410 = "03";
			postJSON(
					"${rootPath}/comminter/InvoiceManagerAction!queryKab1.action",
					{
						"kab1DTO.bae410" : "03"
					},
					function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data.kab1DTO.length <= 0) {
							popupAlert("门慢未领用发票无法打印发票");
						} else {
							popupDialog(
									{
										url : "${rootPath}/comminter/InvoiceManagerAction!updateAndGetKab1.action?kab1DTO.bae410="
												+ bae410
												+ "&flag=1&kab1DTO.aaz217="
												+ aaz217,
										onClosed : function() {
										}
									}, 600, 1000);
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
	
	function getPerson(para) {
		if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			afterChoosePerson();
		}
	}
	
	function afterChoosePerson(){
		popupDialog({url : "${rootPath}/diagnose/GetPersonInfoAction!getPersonBusi.action?diagnoseInfoDTO.arg_name="
			+ $("#arg_name").val()+ "&diagnoseInfoDTO.arg_value="+ $("#querystring").val()
			+ "&diagnoseInfoDTO.aka130=" + $("#aka130").val(),
			onClosed: function() {
				var ret = this.returnValue;
				if(ret!=null){
					var akb020 = ret.akb020;
					var aaz217 = ret.aaz217;
					postJSON( "${rootPath}/medicare/GetPersonInfoAction!getPersonBusiDetail.action?akb020="+akb020+"&aaz217="+aaz217,
					  		function(json){
								if(!checkJSONResult(json)){
								    return;
							    }
								var operFlag = $(':radio[name="operFlag"]:checked').val();
								$("#aac001").val(json.data[0].aac001);
								$("#aac003").val(json.data[0].aac003);
								$("#aac002").val(json.data[0].aac002);
								$("#aac006").val(json.data[0].aac006);
								$("[name='diagnoseInfoDTO.bka004']").val(json.data[0].bka004);
								$("[name='diagnoseInfoDTO.baa027']").val(json.data[0].baa027);
								$("#bka008").val(json.data[0].bka008);
								$("#aab001").val(json.data[0].aab001);
								$("#aka130").val(json.data[0].aka130);
								$("#bka017").val(json.data[0].bka017);
								$("#bka025").val(json.data[0].bka025); 
								$("#aaz217").val(json.data[0].aaz217);
								$("#bka026").val(json.data[0].bka026);
								$("#aka121").val(json.data[0].aka121);
								$("[name='diagnoseInfoDTO.bka006']").val(json.data[0].bka006);
								$("#bka021").val(json.data[0].bka021);
								$("#bka019").val(json.data[0].bka019);
								$("#bka003").val(json.data[0].bka003);
								$("#bka001").val(json.data[0].bka001);
								$("#kcd1id").val(json.data[0].kcd1id);
								$("#akb020").val(json.data[0].akb020);
								$("#bka039").val(json.data[0].bka039);
								$("#bka043").val(json.data[0].bka043);
								$("#bka020").val(json.data[0].bka020);
								$("#bka022").val(json.data[0].bka022);
								$("#aae140").val(json.data[0].aae140);
								$("#btnCalc0").attr("disabled", false);
								$("#ttt").attr("readonly", true);
								$("#bka017").attr("readonly", true);
								$("#bka006").attr("readonly", true);
								$("#operFlag").attr("disabled", true);
								if(operFlag=="1"){
									return $("#stext").focus();
								}else{
									//退费，获取就医登记号和费用批次号，装载明细到前台选择
									if(json.data[0].bka001>1){
										popupDialog({
											url:  "${rootPath}/pages/biz/medicare/diagnose/chooseDiagnoseFee.jsp?aaz217="+$("#aaz217").val()+"&kcd1id="+$("#kcd1id").val()+"&bka039="+$("#bka039").val(),
											onClosed: function() {
												var ret = this.returnValue;
												if(ret!=null){
													fee_batch = ret.bka001;
													$("#reducefeebatch").val(fee_batch);													
													postJSON( "${rootPath}/diagnose/GetPersonInfoAction!getBusiFeeInfo.action?aaz217="+$("#aaz217").val()+"&kcd1id="+$("#kcd1id").val()+"&bka001="+fee_batch+"&bka039="+$("#bka039").val(),
															new Object(),
															function(json){
																if(!checkJSONResult(json)){
																	return;
																}
																if(json.data=="no"){
																	alert("没有找到相关的数据！");
																}else{
																	gridFeeDetail.loadData(json.data.data1);
																}
														}
													);
												}
											}
										},500,800);
									}else{
										$("#reducefeebatch").val("1");
										postJSON( "${rootPath}/diagnose/GetPersonInfoAction!getBusiFeeInfo.action?aaz217="+$("#aaz217").val()+"&kcd1id="+$("#kcd1id").val()+"&bka001=1&bka039="+$("#bka039").val(),
												new Object(),
												function(json){
													if(!checkJSONResult(json)){
														return;
													}
													if(json.data=="no"){
														alert("没有找到相关的数据！");
													}else{
														gridFeeDetail.loadData(json.data.data1);
													}
												}
										);
									}
								}
							}
					);
				}else{
					resetpage();
					alert("没有检索到参保人的就医信息！");
				} 
			}
		}, 500, 600);		
	}
</script>
</body>
</powersi:html>