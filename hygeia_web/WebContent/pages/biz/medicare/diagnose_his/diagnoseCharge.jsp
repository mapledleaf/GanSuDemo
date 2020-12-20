<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<powersi:html>
<head>
<powersi:head title="门诊收费" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="bizForm" namespace="/universal"
		action="DiagnoseRegisterAction_HIS!register.action">
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
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
					<input type="hidden" id="akc190" value=""/>
					<powersi:textfield id="querystring" name="querystring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="getPersonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
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
		<tags:mediBizInfo_diagnose />
		<div id="divTab2" class="row">
			<div class="col-8">
		    <tags:mediFeeInfo_datagrid />
			</div>
		    <div class="col-4">
		    <tags:mediHisPayInfo />
		    </div>
		</div>
		<powersi:hidden id="reduceflag" key="退改费类型"
			name="diagnoseInfoDTO.reduceflag" value="0" />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
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
						"${rootPath}/universal/DiagnoseRegisterAction_HIS!diagnoseCalcSave.action",
						saveItemData, importCalcInfo);
			} else {
				var saveItemData = $("#bizForm").serialize();
				var akc190 = $("#akc190").val();
				postJSON(
						"${rootPath}/universal/DiagnoseRegisterAction_HIS!diagnoseCalcEnPay.action?akc190="+akc190,
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

			$("#bka006").val("110");
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
				resetpage();
				if (!powersi.isnull(aae030)) {
					$("#aae030").val(aae030);
				}
				$("#bka006").val(bka006);
				$("#bka006").change();
				$("#querystring").val(querystring);
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
								+ $("#bke548").val(),
						function(json) {
							$("#bke548").val('');
							if (!checkJSONResult(json)) {
								return;
							}
							if (json.message == 'multi-row') {
								choosepersonlist(querystring);
								//获取登记业务信息
								openLabe();
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
								//获取登记业务信息
								openLabe();
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
										"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name=indi_id&diagnoseInfoDTO.arg_value="
												+ indi_id
												+ "&diagnoseInfoDTO.bka006="
												+ $("#bka006").val()
												+ "&diagnoseInfoDTO.aae030="
												+ $("#aae030").val()
												+ "&diagnoseInfoDTO.aka130="
												+ $("#aka130").val(),
										function(json) {
											if (!checkJSONResult(json)) {
												return;
											}
											$.each(json.data,function(key, value) {
													if (!powersi.isnull(value)) {
															$("#" + key).val(value);
													}
										     });
											$("#aae140").val(json.data.factorinfo.aae140);
											$("[name='diagnoseInfoDTO.baa027']").val(json.data.baa027);
											if ($("#bka006").val().substring(0, 3) != '11R') {							
												try{
													$("#aaz267").val(json.data.clinicapplyinfo.aaz267);
												}catch (e) {
													$("#aaz267").val(1);
												}	
											}
											//end 
											$("[name='diagnoseInfoDTO.bka035']").val(json.data.personinfo.bka035);
											$("#aae030_reg").val($("#aae030").val());
											$("#aae030").prop("disabled", true);
											$("#bka025").focus();
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
		
		
		//打开购药业务查询页面
		function openLabe(){
			var operFlag = $(':radio[name="operFlag"]:checked').val();
			//退费，返回
			if (operFlag == 2 ) {
				return;
			}
			//获取页面人员电脑号
			var aac001 = $("#aac001").val();
			//获取险种
			var aae140 = $("#aae140").val();
			var surl = "${rootPath}/universal/DiagnoseQueryAction_HIS!mZCheckIsExPersonInfo.action?aac001="+encodeURI(encodeURI(aac001,"UTF-8"));
			var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!mZQueryPersonInfo.action?aae140="+encodeURI(encodeURI(aae140,"UTF-8"))+"&aac001="+encodeURI(encodeURI(aac001,"UTF-8"));
			//访问后台，如果没有人员则不谈框
			postJSON(surl,{},function(retVal){
				if(retVal == null || retVal.data.length == 0){
					popupAlert("不存在此人信息，请通过登记信息抓取获取!", "提示", "info");
				}else{
					var dlg = popupDialog({
						url: url,
						onClosed: function(){
							var ret = this.returnValue;
							//循环遍历
							gridFeeDetail.reset();
							for(var index in ret){
								var element = ret[index];
								$("#bkc194").val(ret[index].bkc194);//匹配ID
								$("#bkc109").val(ret[index].bkc109);//异名ID
								$("#aka065").val(ret[index].aka065);//收费项目等级编码(等级)
								$("#ake003").val(ret[index].ake003);//三大目录类别编码(类别)		
								$("#ake005").val(ret[index].ake005);//医疗机构三大目录编码(编码)
								$("#ake006").val(ret[index].ake006);//医疗机构三大目录名称(名称)
								$("#bka073").val(ret[index].bka073);//医院目录厂家(厂家)
								$("#aka070").val(ret[index].aka070);//医院目录剂型(剂型)
								$("#aka074").val(ret[index].aka074);//医院目录规格(规格)
								$("#bka040").val(ret[index].bka040);//医院目录单价(单价)
								$("#akc226").val(ret[index].akc226);//数量
								$("#aae019").val(ret[index].aae019);//金额
								$("#ake007").val(ret[index].ake007);//费用发生日期
								$("#bkc106").val(ret[index].bkc106);//居民先支付比例
								$("#aka057").val(ret[index].aka057);//先自付比例
								$("#ake001").val(ret[index].ake001);//目录编码
								$("#ake002").val(ret[index].ake002);//目录名称
								$("#aka063").val(ret[index].aka063);//项目类别
								insertfee();
							}       
							//试算按钮可点
							if(ret != null && ret != ''){
								$("#btnCalc0").attr("disabled", false);
								gridFeeDetail.checkbox='false';
							}
						}
					}, 800, 700);
				}
			});
		};
		
		
	</script>
</body>
</powersi:html>