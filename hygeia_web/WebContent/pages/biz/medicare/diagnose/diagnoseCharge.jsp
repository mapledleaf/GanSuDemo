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

	String loginUser = BizHelper.getLoginUser().toUpperCase();
	boolean typetest=false;
	if(loginUser.endsWith("TEST")){
		// TS20031600121/NTS20042700216 【需求开发】湘潭医保电子凭证两定（接口）、中心业务系统改造 杨世明 20200417
		request.setAttribute("argName", "#{'aac001':'电脑号'}");
		typetest=true;
	}else{
		request.setAttribute("argName", "#{'aac001':'电脑号','aac002':'社会保障号'}");
		typetest=false;
	}
	//RQ20031925648-门诊两病业务退费报错_add by ljl 20200320_退费不进两病疾病的校验


	//【NTS20050800118】电子凭证动态库开发	 钟声 2020/05/05
	request.setAttribute("loginUser",BizHelper.getLoginUser());
	request.setAttribute("loginUserName",BizHelper.getUserName());
	//NTS20051900637 业务系统增加超范围用药标识传输 杨世明 20200520
%>
<powersi:html>
	<head>
		<powersi:head title="门诊收费" />
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
	<powersi:form id="bizForm" namespace="/diagnose"
				  action="DiagnoseRegisterAction!register.action">
		<powersi:panelbox key="查询条件" allowCollapse="false">
			<powersi:panelbox-toolbar>
<%--				<powersi:radio id="operFlag" name="operFlag"--%>
<%--							   list="#{'1': '收费操作 ', '2':'改费操作'}" value='1' colspan="2"--%>
<%--							   onclick="dealoperFlag()" />--%>
				<!--  【NTS20050800118】电子凭证动态库开发 唤醒扫码机 by 钟声 20200505 -->
				<%--                <div id="operFlag">--%>
				<%--                    <td>电子凭证</td>--%>
				<%--					<!-- 【NTS20052500228】使用支付宝设备点击二维码获取人员信息时，--%>
				<%--                        只需要操作人员选择二维码还是刷脸的形式直接唤醒设备进行扫二维码或刷脸操作。--%>
				<%--                        不需要参保人员在支付宝设备上进行选择。 龚喜洋  2020/05/26 -->--%>
				<%--					<select id="transType" class="select" onchange="" style="width:100px;">--%>
				<%--						<option value="0" selected="" style="width: 50px">微信解码</option>--%>
				<%--						<option value="1" style="width: 50px" selected>支付宝解码</option>--%>
				<%--						<option value="2" style="width: 50px" >支付宝刷脸</option>--%>
				<%--					</select>--%>
				<%--                </div>&nbsp;--%>
				<powersi:hidden id="btnVoucher" label="获取电子凭证" onclick="callScanMachine()" disabled="false" />
				<!--  end  -->

				<powersi:button id="btnCalc0" key="button_calc0" onclick="calc(0);" disabled="true" />
				<powersi:button id="btnCalc1" key="button_calc1" onclick="calc(1);" disabled="true" />
				<powersi:hidden id="btnCalc2" label="扫码结算" onclick="calc(2);" disabled="true" />
				<powersi:hidden id="readele_diaBtn" key="扫码" />
				<powersi:hidden id="btnFace" key="人脸采集" disabled="true" onclick="javascript:doPhoto();" />
				<powersi:hidden id="btnFund" key="基金状态查询" onclick="fundStatusQuery()" disabled="false" />
				<powersi:hidden id="btnReset" key="button_clear" onclick="resetpage();" disabled="false" />
				<powersi:hidden id="btnPrint" key="button_print" onclick="printreport();" disabled="false" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="7%,12%,5%,8%,8%,13%,9%,13%,9%,13%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="diagnoseInfoDTO.arg_name"
										label="查询条件" cssClass="select2" list="${argName }" />
					<td colspan="2"><powersi:textfield id="querystring"
													   name="querystring" title="请输入信息回车" placeholder="请输入信息回车"
													   readonly="false" onkeydown="getPersonInfo(this)" buttonText="读卡"
													   buttonId="readic_button" buttonDisabled="false"
					/></td>
					<powersi:textfield id="aac002" key="社会保障号码"
									   name="diagnoseInfoDTO.aac002" readonly="true" />
					<powersi:textfield id="bka008" key="单位名称"
									   name="diagnoseInfoDTO.bka008" readonly="true" />
					<powersi:textfield id="aac003" key="姓名"
									   name="diagnoseInfoDTO.aac003" readonly="true" />
					<powersi:hidden id="calcType" value="0"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:editorlayout cols="9%,16%,9%,16%,9%,16%,10%,15%">
			<tr>

				<powersi:textfield id="bacu18" name="diagnoseInfoDTO.bacu18" key="个人账户余额"
								   readonly="true" cssStyle="color:red;" />
				<powersi:codeselect id="bka035" name="diagnoseInfoDTO.bka035_name" key="人员类别"
									headerKey="0" headerValue="" codeType="bka035" disabled="true" />
				<powersi:hidden id="bka035" name="diagnoseInfoDTO.bka035" />
				<powersi:textfield id="aac004" key="性别" name="diagnoseInfoDTO.aac004_name"
								   headerKey="0" headerValue="" codeType="aac004" disabled="true" />
				<powersi:hidden id="aac004" name="diagnoseInfoDTO.aac004" />
				<powersi:textfield id="aac006" key="出生日期"
								   name="diagnoseInfoDTO.aac006" readonly="true" />
			</tr>
			<tr>
				<powersi:textfield id="baa027_name" name="diagnoseInfoDTO.aac004_name" key="所属中心"
								   readonly="true"/>
				<powersi:hidden id="baa027" name="diagnoseInfoDTO.baa027" />
				<powersi:codeselect id="bac001" key="公务员级别" name="diagnoseInfoDTO.bac001"
									headerKey="0" headerValue="" codeType="bac001" disabled="true" />
				<powersi:hidden id="bac001" name="diagnoseInfoDTO.bac001" />
				<powersi:textfield id="bka888" key="基金冻结情况" name="diagnoseInfoDTO.bka888"
								   readonly="true"/>
				<powersi:textfield id="aac001" name="diagnoseInfoDTO.aac001"
								   label="电脑号" readonly="true" value=""/>
			</tr>
			<tr>
				<powersi:codeselect id="bka006" name="diagnoseInfoDTO.bka006" key="待遇类型"
									codeType="bka006"
									disabled="true" />
				<powersi:textfield id="aae030" name="diagnoseInfoDTO.aae030" mask="date"
								   key="门诊日期" onKeyDown="keyDown(this)" readonly="true"/>
				<powersi:textfield id="aaz217" key="就医登记号"
								   name="diagnoseInfoDTO.aaz217" readonly="true" />
				<powersi:textfield id="aae005" key="联系电话"
								   name="diagnoseInfoDTO.aae005" readonly="true" />
			</tr>
			<tr>
				<powersi:textfield id="bkz101" key="疾病诊断" readonly="true"
								   name="diagnoseInfoDTO.bkz101" buttonText="..." buttonId="disinse"
								   onKeyDown="keyDown(this)" onbuttonclick="chooseDis('bka026')"
								   required="true" value="普通疾病"/>
				<powersi:textfield id="akf001" name="diagnoseInfoDTO.akf001"
								   key="科室" readonly="true" />
				<powersi:textfield id="bka021" name="diagnoseInfoDTO.bka021"
								   key="病区"  readonly="true" disabled="true"/>
				<powersi:textfield id="ake022" name="diagnoseInfoDTO.ake022"
								   key="医保医师" readonly="true"  disabled="true"/>
			</tr>
			<powersi:hidden id="bka006_reg" name="diagnoseInfoDTO.bka006" />
			<powersi:hidden id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001" />
			<powersi:hidden id="aab019" name="diagnoseInfoDTO.aab019" />
			<powersi:hidden id="aaz267" key="选点序列号" name="diagnoseInfoDTO.aaz267" />
			<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130"
							key="aka130" value="11" />
			<powersi:hidden id="aae140" name="diagnoseInfoDTO.aae140" key="险种" />
			<powersi:hidden id="bka001" key="费用批次" name="diagnoseInfoDTO.bka001" />
			<powersi:hidden id="bka039" key="完成情况" name="diagnoseInfoDTO.bka039" />
			<powersi:hidden id="akc193" name="diagnoseInfoDTO.akc193" value="00000" />
			<powersi:hidden id="bka022" key="病区名称" name="diagnoseInfoDTO.bka022" />
			<powersi:hidden id="bka020" key="科室名称" name="diagnoseInfoDTO.bka020" />
			<powersi:hidden id="bka016" name="diagnoseInfoDTO.bka016" />
			<powersi:hidden id="bka011" name="diagnoseInfoDTO.bka011" />
			<powersi:hidden id="bka012" name="diagnoseInfoDTO.bka012" />
			<powersi:hidden id="kc21id" key="主键" name="diagnoseInfoDTO.kc21id" />
		</powersi:editorlayout>
		<tags:mediFeeInfo_datagrid />
		<tags:mediHisPayInfo/>
		<powersi:hidden id="reduceflag" key="退改费类型" name="diagnoseInfoDTO.reduceflag" value="0" />
		<powersi:hidden id="isreset" key="是否重置首诊医院" name="diagnoseInfoDTO.isreset" value="0" />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="caa027" name="inHospitalDTO.caa027" value="4303-zg"/>
		<powersi:hidden id="aaa027" name="diagnoseInfoDTO.aaa027" />
		<powersi:hidden id="aae013" name="diagnoseInfoDTO.aae013" />
		<powersi:hidden id="akb020" name="diagnoseInfoDTO.akb020" value="${akb020 }"/>



		<!-- TS19032800229 - 【需求开发】电子社保卡应用相关功能模块改造  增加订单号  modified 675 2019年3月28日  -->
		<powersi:hidden id="bizorder" name="diagnoseInfoDTO.bizorder" />
		<powersi:hidden id="bka100" name="diagnoseInfoDTO.bka100" />
		<!-- TS19072500248 - 【需求开发】读卡进行密码签名验证 ，前台获取 ，后台验证  陈洁 20190801  -->
		<powersi:hidden id="bkz300" name="diagnoseInfoDTO.bkz300" />
		<powersi:hidden id="argnames"/>
		<!-- TS19120500156 - 【需求开发】城乡居民门诊两病全省需求---湘潭结算云_add by 李嘉伦 20191217 -->
		<powersi:hidden id="aka120_mzlb" />
		<powersi:hidden id="aka121_mzlb" />
		<powersi:hidden id="aaz267_mzlb" />
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
	<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
	<!--  【NTS20050800118】电子凭证动态库开发 电子凭证接口请求js by 钟声 20200505 -->
	<script type="text/javascript" src="${rootPath}/resource/js/medicaretag/electronicVoucher.js"></script>

	<script type="text/javascript">
		var cameraInfo = null;
		var isReadCard = "0";//1为读卡标识 2为扫码标识

		/* TS19032800229 - 【需求开发】电子社保卡应用相关功能模块改造  默认不扫码结算  modified 675 2019年3月28日  */
		window.onload = function() {
			$("#btnCalc2").hide();
			$("#aae030").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
			$("#ake007").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
			$("#bka006").val("110");
		}

		function dealoperFlag() {
			var operFlag = $(':radio[name="operFlag"]:checked').val();
			if (operFlag == 1) {
				$("#stext").attr("disabled", false);
				$("#bka056").attr("disabled", false);
				$("#bka057").attr("disabled", false);
				$("#bka058").attr("disabled", false);
				$("#akf001").attr("disabled", false);
				$("#bka021").attr("disabled", false);
				$("#ake022").attr("disabled", false);
				$("#btn_enter").attr("disabled", false);
				$("#btn_delete").attr("disabled", false);
				$("#reduceflag").val("0");//收费
			} else {
				$("#stext").attr("disabled", true);
				$("#bka056").attr("disabled", true);
				$("#bka057").attr("disabled", true);
				$("#bka058").attr("disabled", true);
				$("#akf001").attr("disabled", true);
				$("#bka021").attr("disabled", true);
				$("#ake022").attr("disabled", true);
				$("#btn_enter").attr("disabled", true);
				$("#btn_delete").attr("disabled", true);
				$("#reduceflag").val("1");//退费
			}
			init();
		}

		function calc(str) {
			if("1" == str){
				$("#idType").val("1");
			}
			var saveItemData = $("#bizForm").serialize();
			postJSON("${rootPath}/medicare/DiagnoseRegisterAction!diagnoseCalcSave.action",saveItemData, importCalcInfo);
		}

		function importCalcInfo(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			popupSuccess(json.message, "提 示", function(){
				if (json.message == '门诊收费成功！') {
					//【NTS20051200240】湘潭-医保电子凭证支付通知需求  支付通知 by 钟声 2020/5/13
					if(electronicVoucher !== null){
						sendMessage( "settle",true,"${loginUser}","${loginUserName}",
								$("#st_bcfy").val(),$("#em_grzhzf").val(),$("#st_jjzf").val());
					}
					printreport();
				}
			});
			if (json.message == '门诊试算成功！') {
				$("#em_jsq").val('');
				document.all.em_jsq.focus();
				$("#btnCalc1").attr("disabled", false);
				if(isReadCard=="2"){ /* TS19032800229 - 【需求开发】电子社保卡应用相关功能模块改造  扫码结算  modified 675 2019年3月28日  */
					$("#btnCalc2").attr("disabled", false);
				}

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

			if(isReadCard=="2"){ /* TS19032800229 - 【需求开发】电子社保卡应用相关功能模块改造  扫码结算  modified 675 2019年3月28日  */
				$("#btnCalc1").hide();
				$("#btnCalc2").show();
				$("#bizorder").val(json.data[0].bizorder);
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
			$("#bka888").val('');
			$("#dlrimg").attr("src","")
			$("#bka035").val('');
			$("#bka035_name").val('');
			$("#bac001").val('');
			$("#bacu18").val('');

			$("#bka008").val('');
			$("#aab001").val('');
			$("#aae005").val('');
			$("#aaz217").val('');
			$("#bka021").val('');
			$("#akf001").val('');
			$("#bka003").val('');
			$("#aae030").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
			$("#ake007").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
			$("#aae030").prop("disabled", false);

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
			$("#aae013").val('');
			$("#baa027_name").val('');

			$("#btnFace").attr("disabled", true);
			$("#btnSave").attr("disabled", false);
			$("#btnCalc0").attr("disabled", false);
			$("#btnCalc1").attr("disabled", false);
			$("#btnCalc2").attr("disabled", false);
			$("#readele_diaBtn").attr("disabled", false);

			$("#stext").attr("readonly", false);

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
			$("#aab019").val('');

			$("#querystring").attr("disabled", false);
			$("#querystring").val('');
			gridFeeDetail.reset();
			if(<%= typetest %>){
				$("#argName option:selected").val('aac001');
				$("#argnames").val('aac001');
			}else{
				$("#argName option:selected").val('aac002');
				$("#argnames").val('aac002');
			}
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
			$("#save").attr("disabled", false);
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
				/*  if('aac002'==$("#argName").val()){
					checkIcCard(querystring);
				} else {  */
				// if ("0" == $("#reduceflag").val())
				getPerson();
				// 	else
				// 		afterChoosePerson();
				// /*  }  */
			}
		}

		function getPerson(para) {
			// var bka006 = powersi.trim($("#bka006").val());
			// if (powersi.isnull(bka006)) {
			// 	alert("请选择待遇类型再获取人员信息!");
			// 	return;
			// }
			// var querystring = powersi.trim($("#querystring").val());
			// if (powersi.isnull(querystring)) {
			// 	alert("请输入有效查询条件!");
			// 	return;
			// }
			// $("#bka006").val(bka006);
			// $("#bka006").change();
			// $("#querystring").val(querystring);
			// if (para == "readic") {
			// 	$("#argName").val("bka100");
			// } else {
			// 	$("#bke548").val('');
			// }
			// $("#querystring").attr("disabled", true);
			// var indi_id = "0";
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
					+ "&diagnoseInfoDTO.isreset="
					+ $("#isreset").val()
					+ "&diagnoseInfoDTO.bke548="
					+ $("#bke548").val()
					+ "&diagnoseInfoDTO.bka100="
					+ "2"
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
						/* if (json.data.personinfo.nocardreminderflag == "1"){
                            var kz07 = JSON.parse(json.data.personinfo.kz07info);
                            popupAlert("社保卡已制卡未领取, 请及时领取！" + "领卡地址：" + kz07.baz013 + ", 银行联系电话：" + kz07.baz015);
                        } */
						if (json.errortype == '0') {
							if("340" == json.data.personinfo.aae140){
								popupAlert("离休人员请办理离休门诊业务！");
								$("#querystring").attr("disabled", false);
								init();
								return;
							}
							$.each(json.data.personinfo, function(key,
																  value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
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
							$("#bka006_reg").val($("#bka006").val());
							$("#bka006").prop("disabled", true);
							if ("0" == json.data.personinfo.bac001){
								$("#bac001").val("000");
							}
							if (json.data.clinicapplyinfo != null){
								$("#aaz267").val(json.data.clinicapplyinfo.aaz267);
							}
							//用身份证时需要采集图片
							//【NTS20051200240】湘潭-医保电子凭证支付通知需求 electronicVoucher === null 代表没有使用电子凭证 by 钟声 2020/5/13
							if(electronicVoucher === null && $("#argName").val()=="aac002" && isReadCard=="0"){
								doPhoto();
								$("#btnFace").attr("disabled", false);
							}

							//TS19120500156 - 【需求开发】城乡居民门诊两病全省需求---湘潭结算云 _add by ljl 20191217
							if(json.data.spinfo != null){
								$("#aaz267_mzlb").val(json.data.spinfo[0].aaz267);
								$("#aka120_mzlb").val(json.data.spinfo[0].aka120);
								$("#aka121_mzlb").val(json.data.spinfo[0].aka121);
								var aaz267 = $("#aaz267_mzlb").val()
								if (!powersi.isnull(aaz267)){
									$("#bkz101").val($("#aka121_mzlb").val());
									$("#akc193").val($("#aka120_mzlb").val());
									$("#aaz267").val(aaz267);
								}
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
											if (!checkJSONResult(json)) {
												$("#querystring").attr("disabled", false);
												init();
												return;
											}
											if (json.errortype == '0') {
												if("340" == json.data.personinfo.aae140){
													popupAlert("离休人员请办理离休门诊业务！");
													$("#querystring").attr("disabled", false);
													init();
													return;
												}
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
												$("#bka006_reg").val($("#bka006").val());
												$("#bka006").prop("disabled", true);
												if ("0" == json.data.personinfo.bac001){
													$("#bac001").val("000");
												}
												//用身份证时需要采集图片
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

		function readIcCard() {
			isReadCard = "1";
			if ("0" == $("#reduceflag").val()) {
				getPerson();
			} else {
				afterChoosePerson();
			}
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
								var aka130 = ret.aka130;
								postJSON(
										"${rootPath}/medicare/GetPersonInfoAction!getPersonBusiDetail.action?akb020="
										+ akb020 + "&aaz217=" + aaz217 + "&aka130=" + aka130,
										function(json) {
											if (!checkJSONResult(json)) {
												return;
											}
											var operFlag = $(':radio[name="operFlag"]:checked').val();
											//回显科室，病区，医师
											var bka020;
											if(json.data.bizinfo[0].bka020!=""){
												bka020 = json.data.bizinfo[0].bka020;
											}else{
												bka020 = "请选择...";
											}
											$("#select2-chosen-2").text(bka020);
											var bka022;
											if(json.data.bizinfo[0].bka022!=""){
												bka022 = json.data.bizinfo[0].bka022;
											}else{
												bkc154 = "请选择...";
											}
											$("#select2-chosen-3").text(bka022);
											var ake022;
											if(json.data.bizinfo[0].ake022!=""){
												ake022 = json.data.bizinfo[0].ake022;
											}else{
												ake022 = "请选择...";
											}
											$("#select2-chosen-4").text(ake022);

											$("#aac001").val(json.data.bizinfo[0].aac001);
											$("#aac003").val(json.data.bizinfo[0].aac003);
											$("#aac002").val(json.data.bizinfo[0].aac002);
											$("#aac006").val(json.data.bizinfo[0].aac006);
											$("#aac004").val(json.data.bizinfo[0].aac004);
											$("#bka035").val(json.data.bizinfo[0].bka035);
											$("#baa027_name").val(json.data.bizinfo[0].baa027);
											$("#bka008").val(json.data.bizinfo[0].bka008);
											$("#bacu18").val(json.data.bizinfo[0].bacu18);
											$("#bac001").val(json.data.bizinfo[0].bac001);
											$("#aka130").val(json.data.bizinfo[0].aka130);
											$("#aae030").val(json.data.bizinfo[0].aae030);
											$("#ake007").val(json.data.bizinfo[0].aae030);
											$("[name='diagnoseInfoDTO.bka006']").val(json.data.bizinfo[0].bka006);
											$("#bka006_reg").val(json.data.bizinfo[0].bka006);
											$("#bkz101").val(json.data.bizinfo[0].bkz101);
											$("#bka025").val(json.data.bizinfo[0].bka025);
											$("#aaz217").val(json.data.bizinfo[0].aaz217);
											$("#akc193").val(json.data.bizinfo[0].akc193);
											$("[name='diagnoseInfoDTO.akf001']").val(json.data.bizinfo[0].akf001);
											$("[name='diagnoseInfoDTO.bka021'] option:selected").val(json.data.bizinfo[0].bka021);

											$("#bka003").val(json.data.bizinfo[0].bka003);
											$("#bka001").val(json.data.bizinfo[0].bka001);
											$("#kc21id").val(json.data.bizinfo[0].kc21id);
											$("#akb020").val(json.data.bizinfo[0].akb020);
											$("#bka039").val(json.data.bizinfo[0].bka039);
											$("#aae005").val(json.data.bizinfo[0].aae005);
											$("#bka020").val(json.data.bizinfo[0].bka020);
											$("#bka022").val(json.data.bizinfo[0].bka022);
											$("#aae140").val(json.data.bizinfo[0].aae140);

											$("#bka020").attr("disabled", true);
											$("#btnCalc0").attr("disabled", false);
											$("#aae030").attr("disabled", true);
											$("#bka006").attr("disabled", true);
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
			popupDialog(
					{
						url : "${rootPath}/common/SettlementReportNewCenterAction!settlementReport.action?inHospitalDTO.aaz217="
								+ aaz217,
						onClosed : function() {
							init();
						}
					}, 600, 800);
		}

		//校验两病
		function checkMzlbInfo() {
			//退费不进校验
			if ("0" !== $("#reduceflag").val()) {
				return true;
			}
			//两病 CXMZLB001 高血压（门诊两病）、CXMZLB002 糖尿病（门诊两病）
			var aaz217 = $('#aaz217').val();
			var bka026 = $("#akc193").val();
			var bka026_name = $("#bkz101").val();
			var bka026_sp = $("#aka120_mzlb").val();
			var bka026_spname = $("#aka121_mzlb").val();
			if (!powersi.isnull(bka026_sp)){
				//1.审批有信息，使用审批内两病病种，通过
				if (bka026 == bka026_sp){
					return true;
				}
				//2.审批有信息，使用审批外两病病种，卡住
				if (bka026 != bka026_sp&&(bka026 =='CXMZLB001'||bka026 =='CXMZLB002')){
					popupAlert("此参保人门诊两病备案信息为["+bka026_sp+"]" +bka026_spname +"，无法办理["+bka026+"]" + bka026_name + " 业务，请核实！", "错误", "error");
					return false;
				}
				//3.审批有信息，使用审批外其他病种，通过
				if (bka026 != bka026_sp&&!(bka026 =='CXMZLB001'||bka026 =='CXMZLB002')){
					return true;
				}
			}else{
				//4.审批无信息，使用其他病种，通过
				if (!(bka026 =='CXMZLB001'||bka026 =='CXMZLB002')){
					return true;
				}else{
					//5.审批无信息，使用两病病种，卡住
					popupAlert("此参保人没有已审核通过的[" + bka026 + "]" + bka026_name + " 门诊两病审批信息，请核实！", "错误", "error");
					return false;
				}
			}
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
	</body>
</powersi:html>