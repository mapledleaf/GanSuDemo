<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%@page import="java.util.Date"%>
<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	boolean isPic = "1".equals(ParameterMapping.getStringByCode("need_pictures","1")) ;
	request.setAttribute("akb020",BizHelper.getAkb020());	
	//NTS20102600223 电子凭证机具设备管控  陈诗豪 20201027
	request.setAttribute("akb021",BizHelper.getAkb021());
	request.setAttribute("aaa027",BizHelper.getAaa027());
	
	Object cameraInfo = session.getAttribute("cameraInfo");
	String loginUser = BizHelper.getLoginUser().toUpperCase();
	boolean typetest=false;
	if(loginUser.endsWith("TEST")){
		// TS20031600121/NTS20042700215 【需求开发】湘潭医保电子凭证两定（接口）、中心业务系统改造 杨世明 20200417
		request.setAttribute("argName", "#{'aac001':'电脑号'}");
		typetest=true;
	}else{
		request.setAttribute("argName", "#{'aac001':'电脑号','aac002':'社会保障号'}");
		typetest=false;
	}
	 //【NTS20050800118】电子凭证动态库开发	 钟声 2020/05/05
    request.setAttribute("loginUser",BizHelper.getLoginUser());
    request.setAttribute("loginUserName",BizHelper.getUserName());

    //NTS20051900637 业务系统增加超范围用药标识传输 杨世明 20200520
%>
<powersi:html>
<head>
	<powersi:head title="门诊实质性治疗收费" />
	<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
	<%-- 【NTS20050800118】电子凭证动态库开发	NationECCode_ocx动态库注册 钟声 2020/05/05--%>
    <div style="display: none;">
        <object id="NationECCode_ocx"
                codeBase="${rootPath}/resource/cab/NationECCode_ocx.cab#version=1,0,0,1"
                width="0" height="0" border="0"
                classid="clsid:8EEF7302-1FC8-4BA0-8EA5-EC29FDBCA45B"></object>
    <%-- 【NTS20120101270】医保系统新增获取扫码设备SN码校验医保电子凭证设备准入需求 陈诗豪 2020/12/02--%>           
        <object id="idNldata" classid="CLSID:FEB51B1F-4C74-4305-8806-65605D529E22" codebase="NLDatastream_COM.cab#version=1,0,0,1"></object>
		        
    </div>        
    </div>
	<powersi:form id="bizForm" namespace="/diagnose"
		action="DiagnoseRegisterAction!register.action">
		<powersi:panelbox key="查询条件" allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:radio id="operFlag" name="operFlag"
					list="#{'1': '收费操作 ', '2':'改费操作'}" value='1' colspan="2"
					onclick="dealoperFlag()" />
					<!--  【NTS20050800118】电子凭证动态库开发 唤醒扫码机 by 钟声 20200505 -->
                <div id="operFlag">
                    <td>电子凭证</td>
					<!-- 【NTS20052500228】使用支付宝设备点击二维码获取人员信息时，
                        只需要操作人员选择二维码还是刷脸的形式直接唤醒设备进行扫二维码或刷脸操作。
                        不需要参保人员在支付宝设备上进行选择。 龚喜洋  2020/05/26 -->
					<select id="transType" class="select" onchange="" style="width:100px;">
						<option value="0" selected="" style="width: 50px">微信解码</option>
						<option value="1" style="width: 50px" selected>支付宝解码</option>
						<option value="2" style="width: 50px" >支付宝刷脸</option>
					</select>
                </div>&nbsp;
                <powersi:button id="btnVoucher" label="获取电子凭证" onclick="callScanMachine()" disabled="false" />
                <!--  end  -->	
				<powersi:button id="btnCalc0" key="button_calc0" onclick="calc(0);" disabled="true" />
				<powersi:button id="btnCalc1" key="button_calc1" onclick="calcReadic(1);" disabled="true" />
				<powersi:button id="readele_diaBtn" key="扫码" />
				<powersi:button id="btnFace"  key="人脸采集" disabled="true" onclick="javascript:doPhoto();" />
				<powersi:button id="btnFund"  key="基金状态查询" onclick="fundStatusQuery()" disabled="false" />
				<powersi:button id="btnReset" key="button_clear" onclick="resetpage();" disabled="false" />
				<powersi:button id="btnPrint" key="button_print" onclick="printreport();" disabled="false" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="7%,12%,5%,8%,8%,13%,9%,16%,6%,13%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="diagnoseInfoDTO.arg_name"
						label="查询条件" cssClass="select2" list="${argName }" />
					<td colspan="2"><powersi:textfield id="querystring" name="querystring" 
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="getPersonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false" /></td>
					<powersi:textfield id="aac002" key="社会保障号码"
						name="diagnoseInfoDTO.aac002" readonly="true" />
					<powersi:textfield id="bka008" key="单位名称"
						name="diagnoseInfoDTO.bka008" readonly="true" />
					<powersi:textfield id="aac003" key="姓名"
						name="diagnoseInfoDTO.aac003" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<tags:mediBizInfo_diagnose_substantivetreatment />
		<tags:mediFeeInfo_datagrid_substantivetreatment />
		<tags:mediHisPayInfo />
		<powersi:hidden id="reduceflag" key="退改费类型"
			name="diagnoseInfoDTO.reduceflag" value="0" />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="aaa027" name="diagnoseInfoDTO.aaa027" />
		<powersi:hidden id="caa027" name="inHospitalDTO.caa027" value="4303-zg"/>
		<powersi:hidden id="akb020" name="diagnoseInfoDTO.akb020" value="${akb020 }"/>
		<powersi:hidden id="argnames"/>
		<powersi:hidden id="bka100" name="diagnoseInfoDTO.bka100" />
		<!-- TS19072500248 - 【需求开发】读卡进行密码签名验证 ，前台获取 ，后台验证  陈洁 20190801  -->
		<powersi:hidden id="bkz300" name="diagnoseInfoDTO.bkz300" />
		 <!--  【NTS20050800118】电子凭证动态库开发 支付宝扫码机唯一流水号存储 by 钟声 20200505 -->
        <powersi:hidden id="outBizNo" />
		<!--【NTS20051100306】 【医保】通过电子凭证办理的待遇业务需要有相关记录，确保后续能够统计查询  龚喜洋  2020/06/04 -->
		<powersi:hidden id="aka242" name="diagnoseInfoDTO.aka242" value="0"/>
		<!--【NTS20071500412】 【医保】通过电子凭证办理的结算需要发送消息通知，需要传电子凭证，身份证，姓名给后台  方然青  2020/07/20 -->
		<powersi:hidden id="ecIndexNo" name="diagnoseInfoDTO.ecIndexNo" value=""/>
		<!--【NTS20082000023】 【医保】通过电子凭证办理的结算需要发送消息电子凭证中台回流接口  陈诗豪  2020/08/20 -->
		<powersi:hidden id="idNo" name="diagnoseInfoDTO.idNo" value=""/>
		<powersi:hidden id="idType" name="diagnoseInfoDTO.idType" value=""/>
		<powersi:hidden id="ecToken" name="diagnoseInfoDTO.ecToken" value=""/>
		<!--【NTS20120101270】 【医保】医保系统新增获取扫码设备SN码校验医保电子凭证设备准入需求  陈诗豪  2020/12/02 -->
		<powersi:hidden id="sn_code" name="diagnoseInfoDTO.sn_code" value=""/>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
<!--  【NTS20050800118】电子凭证动态库开发 电子凭证接口请求js by 钟声 20200505 -->
<script type="text/javascript" src="${rootPath}/resource/js/medicaretag/electronicVoucher.js"></script>
<script type="text/javascript">
	var cameraInfo = '<%=cameraInfo%>';
	var isReadCard = "0";
	function readIcCard(){
		isReadCard = "1";
		if("0" == $("#reduceflag").val()){
			getPerson();
		}else{
			afterChoosePerson();
		}
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
		init();
	}

	function calc(str) {
		if (!checkForm()){
			return;
		}

		if($("#argName").val()==="qrcode"){
			$("#aae013").val("1");
		}

		if ($("#aac001").val() == '' || $("#aac001").val() == -1) {
			popupAlert("请先检索到人员信息后再录入费用计算！");
			return $("#queryString").focus();
		}
		if(<%=isPic%>){
			//【NTS20050800118】电子凭证动态库开发  电子凭证屏蔽照片 钟声 2020/05/13
			if(electronicVoucher === null && $("#argName").val()=="aac002" && $("#reduceflag").val()=="0" &&
				($("#dlrimg").attr("src")=="" || $("#dlrimg").attr("src")==undefined)  
				&& isReadCard=="0"){
				popupAlert("未检测到照片，请重新采集！");
				return;
			}
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
		popupSuccess(json.message, "提 示", function(){
			if (json.message == '门诊收费成功！') {
				printreport();
				//【NTS20051200240】湘潭-医保电子凭证支付通知需求  支付通知 by 钟声 2020/5/13
				if(electronicVoucher !== null){
					sendMessage( "settle",true,"${loginUser}","${loginUserName}",
							$("#st_bcfy").val(),$("#em_grzhzf").val(),$("#st_jjzf").val());
				}
			}
		});
		if (json.message == '门诊试算成功！') {
			$("#em_jsq").val('');
			document.all.em_jsq.focus();
			$("#btnCalc1").attr("disabled", false);
		}
		if (json.message == '门诊收费成功！') {
			var aaz217 = json.data[0].aaz217;
			$("#aaz217").val(aaz217);
			$("#btnFace").attr("disabled", true);
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
		$("#st_bcfy").val((parseFloat(ld_zfy)).toFixed(2));
		$("#st_jjzf").val(parseFloat(ld_fund_pay).toFixed(2));
		$("#st_self_com").val((parseFloat(ld_self_com)).toFixed(2));
		$("#st_self_own").val(parseFloat(ld_self_own).toFixed(2));
		$("#st_xjzf").val((parseFloat(ld_cash)).toFixed(2));
		$("#em_grzhzf").val((parseFloat(ld_zh)).toFixed(2));
	}

	function init() {
		$("#querystring").val('');
		/*签名串在页面刷新时进行清空 陈洁 20190801  */
		$("#bkz300").val('');
		$("#aaz267").val('');
		$("#aae030").val('');
		$("#aae031").val('');
		$("#aac001").val('');
		$("#aac002").val('');
		$("#aac003").val('');
		$("#aac004").val('');
		$("#aac006").val('');
		$("#dlrimg").attr("src","")
		$("#bka035").val('');
		$("#bka035_name").val('');
		$("#bka008").val('');
		$("#aab001").val('');
		$("#aae005").val('');
		$("#aaz217").val('');
		$("#akc193").val('');
		$("#bkz101").val('');
		$("#bka021").val('');
		$("#bka003").val('');
		$("#bacu18").val('');
		$("#bac001").val('');
		$("#bka888").val('');
		$("#akb081").val('');
		$("#bkm030").val('');
		$("#bkm031").val('');

		$("#aae030").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
		$("#ake007").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
		$("#aae030").prop("disabled", false);
		$("#aae030_reg").val('');

		$("#st_jjzf").val('0.00');
		$("#st_self_com").val('0.00');
		$("#st_self_own").val('0.00');
		$("#st_bcfy").val('0.00');
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

		$("#btnFace").attr("disabled", true);
		$("#btnSave").attr("disabled", false);
		$("#btnCalc0").attr("disabled", false);
		$("#btnCalc1").attr("disabled", true);
		$("#stext").attr("readonly", false);
		if(<%= typetest %>){
			$("#argName option:selected").val('aac001');
			$("#argnames").val('aac001');
		}else{
			$("#argName option:selected").val('aac002');
			$("#argnames").val('aac002');
		}
		$("#querystring").attr("disabled", false);
		$("#querystring").val('');
		gridFeeDetail.reset();
		$("#querystring").focus();

		//电子凭证 钟声
		electronicVoucher = null;
		//【NTS20051100306】 【医保】通过电子凭证办理的待遇业务需要有相关记录，确保后续能够统计查询  龚喜洋  2020/06/04
		$("#aka242").val("0");
		$("#ecIndexNo").val("");
		$("#idNo").val("");
		$("#idType").val("");
		$("#ecToken").val("");
	}

	/*重置界面*/
	function resetpage() {
		init();
	}

	function getPersonInfo(para){
		if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			isReadCard = "0";
			if('aac002' == $("#argnames").val()){
				$("#argName option:first").prop('selected', 'selected'); 
				$("#argName option:selected").val('aac002');
				$("#argName").change();
				$("#argName").val('aac002');
				$("#argName").change();
			}
			if('aac002'==$("#argName").val()){
				__isPing(identify(querystring));
			} else {
				if("0" == $("#reduceflag").val())
					getPerson();
				else 
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
			if (!powersi.isnull(aae030)) {
				$("#aae030").val(aae030);
			}
			$("#bka006").val(bka006);
			$("#bka006").change();
			$("#querystring").val(querystring);
			if (para == "readic") {
				$("#argName").val("bka100");
			} else {
				$("#bke548").val('');
			}
			$("#querystring").attr("disabled", true);
			var indi_id = "0";
			postJSON(
					"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name="
							+ $("#argName").val()
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
							+ "&diagnoseInfoDTO.bka100="
							+ $("#bka100").val()
							+ "&diagnoseInfoDTO.bkz300="
							+ $("#bkz300").val(),
					function(json) {
						$("#bke548").val('');
						if (!checkJSONResult(json)) {
							$("#querystring").attr("disabled", false);
							init();
							return;
						}
						if (json.message == 'multi-row') {
							choosepersonlist(querystring);
							return;
						}
						if (json.data.personinfo.nocardreminderflag == "1"){
							var kz07 = JSON.parse(json.data.personinfo.kz07info);
							popupAlert("社保卡已制卡未领取, 请及时领取！" + "领卡地址：" + kz07.baz013 + ", 银行联系电话：" + kz07.baz015);
						}
						if (json.errortype == '0') {
							$.each(json.data.personinfo, function(key,
									value) {
								if (!powersi.isnull(value)) 
									$("#" + key).val(value);
								if(key=="bka888"){
									if(value!="正常") {
										$("#bka888").css("color","red");
										//TS19051400133 结算云（医院端）-  （门诊业务管理、住院业务管理、生育住院管理、家庭病床管理、异地就医管理）-  
										//办业务读卡后，如果此人的基金是冻结状态，则弹出一个提示框提示一下这个人基金冻结 cj 20190515
										popupAlert("您好!此参保人基金冻结状态为【已冻结】,将不能进行基金报销,请知悉!");
									}else{
										$("#bka888").css("color","black");
									}
								}
							});
							$("#aae030_reg").val($("#aae030").val());
							$("#bka006_reg").val($("#bka006").val());
							$("#aae030").prop("disabled", true);
							$("#bka006").prop("disabled", true);
							if ("0" == json.data.personinfo.bac001){
								$("#bac001").val("000");
							}
							if (json.data.spinfo != null){
								var spinfo = json.data.spinfo[0];
								$("#aaz267").val(spinfo.aaz267);
								$("#akc193").val(spinfo.aka120);
								$("#bkz101").val(spinfo.aka121);
								$("#bkm030").val(spinfo.aae030);
								$("#bkm031").val(spinfo.aae031);
								$("#akb081").val(spinfo.akb081);
							}
							//【NTS20051200240】湘潭-医保电子凭证支付通知需求 electronicVoucher === null 代表没有使用电子凭证 by 钟声 2020/5/13
							if(electronicVoucher === null && $("#argName").val()=="aac002" && isReadCard=="0"){
								doPhoto();
								$("#btnFace").attr("disabled", false);
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
										$("#querystring").attr("disabled", false);
										init();
										return;
									}
									if (json.errortype == '0') {
										$.each(json.data.personinfo, function(key,
												value) {
											if (!powersi.isnull(value)) 
												$("#" + key).val(value);
											if(key=="bka888"){
												if(value!="正常")
													$("#bka888").css("color","red");
												else
													$("#bka888").css("color","black");
											}
										});
										$("#aae030_reg").val($("#aae030").val());
										$("#bka006_reg").val($("#bka006").val());
										$("#aae030").prop("disabled", true);
										$("#bka006").prop("disabled", true);
										if ("0" == json.data.personinfo.bac001){
											$("#bac001").val("000");
										}
										if (json.data.spinfo != null){
											var spinfo = json.data.spinfo[0];
											$("#aaz267").val(spinfo.aaz267);
											$("#akc193").val(spinfo.aka120);
											$("#bkz101").val(spinfo.aka121);
											$("#bkm030").val(spinfo.aae030);
											$("#bkm031").val(spinfo.aae031);
											$("#akb081").val(spinfo.akb081);
										}
										//【NTS20051200240】湘潭-医保电子凭证支付通知需求 electronicVoucher === null 代表没有使用电子凭证 by 钟声 2020/5/13
										if(electronicVoucher === null && $("#argName").val()=="aac002" && isReadCard=="0"){
											doPhoto();
											$("#btnFace").attr("disabled", false);
										}
									} 
									if (!powersi.isnull(json.message)) 
										popupInfo(json.message);
							});
						}
					}
				}, 500, 600);
	}
	
	
	function afterChoosePerson() {
		popupDialog(
				{
					url : "${rootPath}/diagnose/GetPersonInfoAction!getPersonBusi.action?diagnoseInfoDTO.arg_name="
						    + $("#argnames").val()
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
							var aka130 = ret.aka130;
							postJSON(
									"${rootPath}/medicare/GetPersonInfoAction!getPersonBusiDetail.action?akb020="
											+ akb020 + "&aaz217=" + aaz217 + "&aka130=" + aka130,
									function(json) {
										if (!checkJSONResult(json)) 
											return;
										var bizinfo = json.data.bizinfo[0];
										$("#aac002").val(bizinfo.aac002);
										$("#bka008").val(bizinfo.bka008);
										$("#aac003").val(bizinfo.aac003);
										
										$("#bacu18").val(bizinfo.bacu18);
										$("#bka035").val(bizinfo.bka035);
										$("#aac004").val(bizinfo.aac004);
										$("#aac006").val(bizinfo.aac006);
										
										$("#baa027_name").val(bizinfo.baa027);
										$("#bac001").val(bizinfo.bac001);
										$("#bka888").val(bizinfo.bka888);
										$("#aac001").val(bizinfo.aac001);
										
										$("#bka006").val(bizinfo.bka006);
										$("#aae030").val(bizinfo.aae030);
										$("#bkz101").val(bizinfo.bkz101);
										$("#aae005").val(bizinfo.aae005);
										$("#aaz217").val(bizinfo.aaz217);
										
										
										var speinfo = json.data.speinfo[0];
										$("#bkm030").val(speinfo.aae030);
										$("#bkm031").val(speinfo.aae031);
										$("#akb081").val(speinfo.akb081);
						
										$("#btnCalc0").attr("disabled",false);
										$("#aae030").attr("disabled", true);
										$("#bka006").attr("disabled", true);
										var operFlag = $(':radio[name="operFlag"]:checked').val();
										if (operFlag == "1") {
											return $("#stext").focus();
										} else {
											//退费，获取就医登记号和费用批次号，装载明细到前台选择
											if (json.data == "no") {
												alert("没有找到就医明细数据，请检查是否还未收费！");
											} else {
												gridFeeDetail.loadData(json.data.feeinfo);
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
		popupDialog({
					url : "${rootPath}/common/SettlementReportNewCenterAction!settlementReport.action?inHospitalDTO.aaz217="
							+ aaz217,
					onClosed : function() {
						init();
					}
				}, 600, 1000);
	}
	
	//【NTS20050800118】电子凭证动态库开发   用于获取扫码机返回的电子凭证,并进行查询 钟声 2020/04/29
    function callScanMachine(){
        var transType  = $("#transType").val();
        if(transType !=='0'){//获取唯一业务流水号  outBizNo
            var outBizNo = getOutBizNo();
            $("#outBizNo").val(outBizNo);
        }
        //NTS20102600223 电子凭证机具设备管控  陈诗豪 20201027
        var bka021 = "${akb021}";
        var aaa027 = "${aaa027}";
        var resultMap =  machineScanning(transType,"${loginUser}","${loginUserName}","${akb020}","01301",outBizNo,bka021,aaa027);
        if(resultMap.code===0){
            var idNo ;
            var ecIndexNo;
            var idType;
			var ecToken;
            if(transType === '0'){
                electronicVoucher = resultMap.data;
                idNo = electronicVoucher.idNo;
                ecIndexNo = electronicVoucher.ecIndexNo;
                idType = electronicVoucher.idType;
				ecToken = electronicVoucher.ecToken;
            }else {
                electronicVoucher = resultMap.extra;
                idNo = electronicVoucher.idNo;
                ecIndexNo = electronicVoucher.ecIndexNo;
                idType = electronicVoucher.idType;
				ecToken = electronicVoucher.ecToken;
            }
            $("#ecIndexNo").val(ecIndexNo);
            $("#idNo").val(idNo);
			$("#ecToken").val(ecToken);
			$("#idType").val(idType);
            $("#aka242").val("1");
            $("#querystring").val(idNo);
            $("#argName option:first").prop('selected', 'selected');
            $("#argName option:selected").val('aac002');
			//【NTS20050800118】电子凭证动态库开发  退费操作 钟声 2020/05/12
			if ("0" == $("#reduceflag").val()) {
				getPerson();
			} else {
				afterChoosePerson();
			}
        }else {
            //失败
            electronicVoucher = null;
            alert(resultMap.message);
        }
    }
</script>
