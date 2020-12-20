<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%> 
<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
<powersi:head title="门诊选点业务修改" />
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="mainForm">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="门诊登记表" onclick="clinicRegister()" />
				<powersi:button id="fundStatus" key="基金状态查询"
					onclick="fundStatusInfo()" />
				<powersi:button id="button_save" key="保存" onclick="saveInfo()" />
				<powersi:button key="重置" onclick="clearall()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac001" name="mediSpecDto.aac001" key="电脑号"
						readonly="true" required="true" />
					<powersi:textfield id="aac003" name="mediSpecDto.aac003" key="姓名"
						readonly="true" required="true" />
					<powersi:textfield id="aac002" name="mediSpecDto.aac002" key="身份证号"
						readonly="true" required="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac006" name="mediSpecDto.aac006" key="出生日期"
						readonly="true"  format="dateFmt:'yyyy-MM-dd'"/>
					<powersi:textfield id="dq11" name="mediSpecDto.dq11" key="当前年龄"
						readonly="true" />
					<powersi:textfield id="aab001" name="mediSpecDto.aab001" key="单位编号"
						readonly="true" required="true"/>
					<powersi:textfield id="aab069" name="mediSpecDto.aab069" key="单位名称"
						readonly="true" colspan="3" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect id="busType" label="改点类型"
							name="mediSpecDto.busType" onchange="changeDate()"
							list="#{'':'请选择...','1':'年度变更','2':'单位/住址变更'}"/>
					<powersi:textfield id="akb020" name="mediSpecDto.akb020" key="医院编号"
						readonly="true" required="true" />
					<powersi:textfield id="akb021" name="mediSpecDto.akb021"
						key="申请医院名称" readonly="true" required="true" />
					<powersi:textfield id="bka004_name" 
						key="人员类别" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka006" name="mediSpecDto.bka006" key="待遇类型"
						readonly="true" required="true" />
					<powersi:textfield id="aae030" name="mediSpecDto.aae030" key="开始时间"
						readonly="true" required="true" />
					<powersi:textfield id="aae031" name="mediSpecDto.aae031" key="结束时间"
						readonly="true" required="true" />
					<powersi:textfield id="bmc016" name="mediSpecDto.bmc016" key="预产期"
						readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae011" name="mediSpecDto.aae011" key="录入人"
						readonly="true" />
					<powersi:textfield id="aae036" name="mediSpecDto.aae036" key="录入时间"
						readonly="true" />
					<powersi:textfield id="aae127" name="mediSpecDto.aae127" key="申请时间"
						readonly="true" />
					<powersi:textfield id="bke672" name="mediSpecDto.bke672"
						key="医院意见时间" readonly="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke013" name="mediSpecDto.bke013" key="医院意见"
						value="同意" colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="aae013" name="mediSpecDto.aae013" key="备注"
						validate="maxSize[50]" colspan="7" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<powersi:editorlayout cols="20%,80%">
				<powersi:editorlayout-row>
					<powersi:checkbox key="两病三师签约" name="aaz308" id="aaz308" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:hidden id="aae140" name="mediSpecDto.aae140"/>
		<powersi:hidden id="aaz267" name="mediSpecDto.aaz267"/>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
	</powersi:form>
	<script type="text/javascript">
	window.onload = function(){
		
	    $('#akb020').val("<%=hospital_id%>");
	 	$('#akb021').val("<%=hospital_name%>");
	 	$('#aaa027').val("<%=aaa027%>");
		$('#aae011').val("<%=userName%>");
		
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#aae036').val(year+"-"+month+"-"+day);
		$('#bke672').val(year+"-"+month+"-"+day);
		
		$("#aaz308").attr("disabled","disabled");
		$("#button_save").attr("disabled",true);
	    $("#fundStatus").attr("disabled",true);
	}
    
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
		$("#tracestring").val("");
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
		var bizFormData = $("#mainForm").serialize();
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!readic.action",
				bizFormData, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data != "no") {
						if (!powersi.isnull(json.data.aac002)) {
							$("#arg_name").val("aac002");
							$("#tracestring").val(json.data.aac002);
							queryPresonInfo("readic");
							$("#arg_name").val("");
						}
					}
				});
	}

	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	//保存申请信息
	function saveInfo(){
		if(!checkFormValidtion()){
			return;
		}
		var bizinfo = $("#mainForm").serialize();
		postJSON("<%=path%>/medicare/MzchoHospitalBusApplyAction!updateClinicBus.action",bizinfo,saveinfo_after);
	}
	
	function saveinfo_after(json){
		if (!checkJSONResult(json)) {
			return;
		} 
		popupInfo(json.data);
		clearall();
	}
	
	function CompareDate(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		
		var strToday = year+"-"+month+"-"+day;
		var strDate = year + "-" + "06" + "-" + "30";
		if(new Date(strToday.replace(/-/g,"\/")) > new Date(strDate.replace(/-/g,"\/"))){
			return 1;
		}else{
			return -1;
		}
	}
	 

	//计算参保人年龄
	function age(){
		var myDate = new Date();
		var year = myDate.getFullYear();
	
		var birthday = document.getElementById("aac006").value.substr(0,4);
		var age = (year - birthday);
		document.getElementById("dq11").value = age;				
	}
	
	//门诊登记表
	function clinicRegister(){
		var aac001 = powersi.trim($("#aac001").val());
		if (powersi.isnull(aac001)) {
			popupAlert("人员信息不能为空,请先获取人员信息!");
			return;
		}
		
		popupDialog({
			url: "<%=path%>/medicare/MzchoHospitalBusApplyAction!getChangeApplyHosp.action?mediSpecDto.aac001="
								+ aac001,
						onClosed : function() {

						}
					}, 450, 800);

		}
		//基金状态查询
		function fundStatusInfo() {
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

		function clearall() {
			$("#busType").val("");
			$("#bka006").val("");
			$("#aae030").val("");
			$("#aae031").val("");
			$("#aae127").val("");
			$("#aac001").val("");
			$("#aac002").val("");
			$("#aac003").val("");
			$("#aac004").val("");
			$("#bka004_name").val("");
			$("#aac006").val("");
			$("#aab069").val("");
			$("#aab001").val("");
			$("#aae013").val("");
			$("#bmc016").val("");
			$("#dq11").val("");
			$("#tracestring").val("");
			$("#aaz308").attr("checked",false);
			$("#button_save").attr("disabled", true);
			$("#fundStatus").attr("disabled", true);
		}
		//查询业务申请人员基本信息
		function queryPresonInfo(para){
		   if (para == "readic" || window.event.keyCode == 13) {
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					return;
				}
				$("#tracestring").attr("disabled", "disabled");
				findAac002(tracestring);
				$("#tracestring").removeAttr("disabled");
			}
		}
		function findAac002(tracestring){
			    var indi_id = 0;
				var argValue = tracestring.substr(0);
			   postJSON("<%=path%>/medicare/MzchoHospitalBusApplyAction!queryMzBusDetailInfo.action",
						{
							"mediSpecDto.arg_name" : indi_id,
							"mediSpecDto.arg_value" : argValue,
							"mediSpecDto.aac001" : argValue
						}, function(json){
							if (!checkJSONResult(json)) {
								return;
							}
							$.each(json.data,function(key, value) {
										if (!powersi.isnull(value) && value!=null ) {
											if(key=="bka006"){
												if(value=="110"){
													$("#bka006").val("普通门(急)诊");
												}else if(value=="511") {
													$("#bka006").val("产前检查");
												}
											}else if(key=="aka084"){
												if(value="Q"){
													$("#aaz308").attr("checked",true);
												}
											}else{
												$("#" + key).val(value);
											}
											$("#fundStatus").attr("disabled",false);
											age();
										}
									});
						} );
			   
			}
		
		function changeDate(){
			var aac001 = powersi.trim($("#aac001").val());
			if (powersi.isnull(aac001)) {
				$("#busType").val("");
				popupAlert("请先获取原门诊定点信息!");
				return;
			}
			var busType =  $("#busType option:selected").val();
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+2).toString();
			var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
			if(busType=="1"){
				if(parseInt(month)<4 || parseInt(month)>6){
					popupAlert("年度变更开放时间为本年度4-6月份！");
					$("#busType").val("");
					return;
				}
				$('#aae030').val(year+"-"+"07-01");
				$('#aae031').val("2099-01-01");
				$("#button_save").attr("disabled",false);
			}else if(busType=="2"){
				popupAlert("请保存变更资料备查！");
				$('#aae030').val(year+"-"+(parseInt(month)+1)+"-01");
				$('#aae031').val("2099-01-01");
				$("#button_save").attr("disabled",false);
			}else{
				$('#aae030').val("");
				$('#aae031').val("");
				$("#button_save").attr("disabled",true);
			}
		}
	</script>
</body>
</powersi:html>