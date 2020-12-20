<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="身份信息校验" target="_self" />
</head>
<body>
	<powersi:form id="identifyForm" namespace="/common" action="CommonManagerAction!identifyCard.action">
	
		<powersi:panelbox key="查询条件" allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button id="btnUpdate" key="修改密码" onclick="updateBtn();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,8%,10%,9%,12%,11%,13%,11%,14%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="diagnoseInfoDTO.arg_name"
						label="查询条件" cssClass="select2"
						list="#{'aac002':'社会保障号'}" />
					<td><powersi:textfield id="querystring"
							name="querystring" title="请输入信息回车" placeholder="请输入信息回车"
							readonly="false" onkeydown="getPersonInfo(this)"/></td>
					<powersi:textfield id="aac002" key="社会保障号码"
						name="diagnoseInfoDTO.aac002" readonly="true" />
					<powersi:textfield id="bka008" key="单位名称"
						name="diagnoseInfoDTO.bka008" readonly="true" />
					<powersi:textfield id="aac003" key="姓名"
						name="diagnoseInfoDTO.aac003" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:editorlayout cols="8">
			 <tr>
				<powersi:textfield id="bacu18" name="diagnoseInfoDTO.bacu18" key="个人账户余额"
					readonly="true" cssStyle="color:red;" />
				<powersi:codeselect id="bka035" name="diagnoseInfoDTO.bka035_name" key="人员类别"
					headerKey="0" headerValue="" codeType="bka035" disabled="true" />
			    <powersi:hidden id="bka035" name="diagnoseInfoDTO.bka035" />
				<powersi:codeselect id="aac004" key="性别" name="diagnoseInfoDTO.aac004_name" 
					headerKey="0" headerValue="" codeType="aac004" disabled="true" />
				<powersi:textfield id="aac006" key="出生日期"
					name="diagnoseInfoDTO.aac006" readonly="true" />
			</tr>
			<tr>
				<powersi:textfield id="baa027_name" name="diagnoseInfoDTO.aac004_name" key="所属中心"
					readonly="true"/>
				<powersi:codeselect id="bac001" key="公务员级别" name="diagnoseInfoDTO.bac001" 
					headerKey="0" headerValue="" codeType="bac001" disabled="true" />
				<powersi:hidden id="bac001" name="diagnoseInfoDTO.bac001" />
				<powersi:textfield id="bka888" key="基金冻结情况" name="diagnoseInfoDTO.bka888" readonly="true"/>
				<powersi:textfield id="aac001" name="diagnoseInfoDTO.aac001"
					label="电脑号" readonly="true" value=""/>
			</tr>
		</powersi:editorlayout>
		
		<powersi:panelbox key="修改身份证密码" id="updateBlock" style="display : none"  allowCollapse="false">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row >
					<powersi:password id="aaz507" key="密码" name="inHospitalDTO.aaz507" readonly="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row >
					<powersi:password id="aaz508" key="新密码" name="inHospitalDTO.aaz508" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row >
					<powersi:password id="aaz510" key="再次确认新密码" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:buttons cols="2">
						<powersi:button id="btnSubmit" key="确   认" onclick="checkPasswordIdentify();" style="margin-left:600px" />
				</powersi:buttons>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130" value="11" />
		<powersi:hidden id="bka006" name="diagnoseInfoDTO.bka006" value="110" />
		<powersi:hidden id="aaz509" name="inHospitalDTO.aaz509" value="1" />
		<powersi:hidden id="queryss" name="inHospitalDTO.aac002"  />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
	<script type="text/javascript">	
		var objCard=null;
		window.onload = function() {
			objCard = document.getElementById("cardControl");
			$("#btnUpdate").attr("disabled", true);
		}	
		function submitss(){
				$("#aaz507").focus();
				var password = readPass(1);	
				if (powersi.isnull(password)) {
					if (confirm("是否重新输入密码") == true) {
						$("#aaz507").val('');
						$("#aaz508").val('');
						$("#aaz510").val('');
						setTimeout("submitss()",500);
					}
					return;
				}
				$("#aaz507").val(password);
				setTimeout("getPassword()",500);	
		}				
		function getPassword(){
			$("#aaz508").focus();
			var newPassword= readPass(4);	
			if (powersi.isnull(newPassword)) {
				if (confirm("是否重新输入密码") == true) {
					$("#aaz507").val('');
					$("#aaz508").val('');
					$("#aaz510").val('');
					setTimeout("submitss()",500);
				}
				return;
			}
			$("#aaz508").val(newPassword);			
			setTimeout("getAgainPassword()",500);
		}
		function getAgainPassword(){
			$("#aaz510").focus();
			var newPassword= readPass(2);
			if (powersi.isnull(newPassword)) {
				if (confirm("是否重新输入密码") == true) {
					$("#aaz507").val('');
					$("#aaz508").val('');
					$("#aaz510").val('');
					setTimeout("submitss()",500);
				}
				return;
			}
			$("#aaz510").val(newPassword);
		}
		function checkPasswordIdentify() {
			var aaz508=	$("#aaz508").val();
			var aaz510=	$("#aaz510").val();
			if(aaz508 != aaz510){
				if (confirm("两次密码输入不一致，是否重新输入密码!") == true) {
					$("#aaz507").val('');
					$("#aaz508").val('');
					$("#aaz510").val('');
					setTimeout("submitss()",500);
				}
				return;
			}
			var identifyForm = $("#identifyForm").serialize();
			postJSON("${rootPath}/common/CommonManagerAction!identifyCard.action",
					identifyForm, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						var jsonData = json.data.ind;
						alert(json.data.msg);
						if (jsonData == '1') {
							if (confirm("密码错误，是否重新输入密码!") == true) {
								$("#aaz507").val('');
								$("#aaz508").val('');
								$("#aaz510").val('');
								setTimeout("submitss()",500);
								return;
							}
						}
						init();
					});
			
		}
	function init(){
		$("#aaz507").val('');
		$("#aaz508").val('');
		$("#aaz510").val('');
		$("#bacu18").val('');
		$("#aac002").val('');
		$("#aac003").val('');
		$("#bka008").val('');
		$("#bka035").val('');
		$("#aac004").val('');
		$("#aac006").val('');
		$("#baa027_name").val('');
		$("#bac001").val('');
		$("#bka888").val('');
		$("#aac001").val('');
		$("#updateBlock").hide();
		$("#btnUpdate").attr("disabled", true);
	}
	
	function updateBtn(){
		$("#updateBlock").show();
		 $("#btnUpdate").attr("disabled",true); 
		$("#queryss").val(powersi.trim($("#querystring").val()));
		setTimeout("submitss()",500);
	}
	
	function getPersonInfo(para){
		if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			if('aac002'==$("#argName").val()){
				identify(querystring);
			} else {
					getPerson();	
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
			resetpage();
			$("#bka006").val(bka006);
			$("#bka006").change();
			$("#querystring").val(querystring);
			if (para == "readic") {
				$("#argName").val("bka100");
			} else {
				$("#bke548").val('');
			}
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
							+ "&diagnoseInfoDTO.aaz509="
							+ $("#aaz509").val()
							+ "&diagnoseInfoDTO.bke548="
							+ $("#bke548").val(),
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
								}
								if(key=="bka888"){
									if(value!="正常")
										$("#aac008").css("color","red");
									else
										$("#aac008").css("color","black");
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
							$("#btnUpdate").attr("disabled", false);
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
											+ "&diagnoseInfoDTO.aaz509="
											+ $("#aaz509").val()
											+ "&diagnoseInfoDTO.aka130="
											+ $("#aka130").val(),
									function(json) {
										if (!checkJSONResult(json)) {
											return;
										}
										if (json.errortype == '0') {
											$.each(json.data.personinfo, function(key,
													value) {
												if (!powersi.isnull(value)) 
													$("#" + key).val(value);
												if(key=="aac008"){
													if(value!="正常参保")
														$("#aac008").css("color","red");
													else
														$("#aac008").css("color","black");
												}
											});
											$("#bka006_reg").val($("#bka006").val());
											$("#bka006").prop("disabled", true);
											if ("0" == json.data.personinfo.bac001){
												$("#bac001").val("000");
											}
										}
										$("#btnUpdate").attr("disabled", false);
										if (!powersi.isnull(json.message)) 
											popupInfo(json.message);
									});
						}
					}
				}, 500, 600);
	}

	/*重置界面*/
	function resetpage() {
		init();
		$("#querystring").focus();
	}
	</script>
</body>
</powersi:html>