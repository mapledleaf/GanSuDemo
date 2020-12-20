<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<powersi:html>
<head>
<powersi:head title="电子处方录入" />
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
				<powersi:button id="btnSave" label="保存" onclick="saveDate();"
					disabled="false" />
				<powersi:button cssClass="button" id="btn_print" key="打印" onclick="printreport()"  disabled="true" ></powersi:button>
				<powersi:button id="btnReset" key="重置"
					onclick="resetpage();" disabled="false" />
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="querystring" name="querystring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="getPerson(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac002" key="社会保障号码" name="diagnoseInfoDTO.aac002" readonly="true" />
					<powersi:textfield id="aac001" name="diagnoseInfoDTO.aac001" label="电脑号" readonly="true" />
					<powersi:textfield id="aac003" key="姓名" name="diagnoseInfoDTO.aac003" readonly="true" />
					
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="业务信息">
			<powersi:editorlayout cols="8">
				<tr>
				    <powersi:codeselect id="aac004" name="diagnoseInfoDTO.aac004" label="性别"  codeType="aac004" disabled="true" />
					<powersi:textfield id="aac006" name="diagnoseInfoDTO.aac006" key="出生日期" disabled="true"  />	
					<powersi:codeselect id="bka004_name" key="人员类别" name="diagnoseInfoDTO.bka004" codeType="bka004" disabled="true" />
					<powersi:textfield id="aaz217" key="就医登记号" 	name="diagnoseInfoDTO.aaz217" readonly="true" /> 
					
				</tr> 
				<tr>
					<powersi:textfield id="bka008" key="单位名称" name="diagnoseInfoDTO.bka008" readonly="true" />
					<powersi:textfield id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001" readonly="true" />
					<powersi:codeselect id="baa027" key="人员所属统筹区" name="diagnoseInfoDTO.baa027" codeType="aaa027" disabled="true" />
					<powersi:textfield id="bka026_name" key="疾病诊断" name="bka026_name" disabled="true"/>
					
				</tr>
				<tr>
					<powersi:textfield id="bka006_name" key="待遇类型" name="bka006_name" disabled="true" />
					<powersi:hidden id="bka006" name="diagnoseInfoDTO.bka006" />
					<td align="right" class="tdLabel">科室</td>
					<td><select id="bka019" name="diagnoseInfoDTO.bka019" 
					onfocus="loadBka019Data()" onchange="selectbka021()"></select>
					<powersi:codeselect id="bka021" name="diagnoseInfoDTO.bka021"  label="病区"  
					    list="#{'': ''}"   onchange="selectbka503()"/> 
					<powersi:codeselect id="bka503" name="diagnoseInfoDTO.bka503"  label="医保医师" 
					    list="#{'': ''}"  /> 
				</tr>
				<tr>
				    <powersi:textfield id="bka025" key="挂号" name="diagnoseInfoDTO.bka025" />
				    <powersi:textfield id="bka043" key="医院处方号" name="diagnoseInfoDTO.bka043" />
				    <powersi:textfield id="ake1id" key="电子处方流水号" name="kad5DTO.ake1id"  disabled="true" />
				    <td colspan="2"></td>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		<tags:electronicPres_datagrid />
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="bka017" name="diagnoseInfoDTO.bka017" />
		<powersi:hidden id="arg_name" name="arg_name" />
		<powersi:hidden id="bka004" name="diagnoseInfoDTO.bka004" />
		<%-- <powersi:hidden id="baa027" name="diagnoseInfoDTO.baa027" /> --%>
		<powersi:hidden id="bka017_reg" name="diagnoseInfoDTO.bka017" />	
		<powersi:hidden id="bka005" key="公务员级别" name="diagnoseInfoDTO.bka005" />
		<powersi:hidden id="aaz267" key="选点序列号" name="diagnoseInfoDTO.aaz267" />
		<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130"
			key="aka130" value="13" />
		<powersi:hidden id="aae140" key="险种" name="diagnoseInfoDTO.aae140" value="310" />
		<powersi:hidden id="aab019" name="diagnoseInfoDTO.aab019" />
		<powersi:hidden id="bka026" name="diagnoseInfoDTO.bka026" />
		<powersi:hidden id="personData" name="personData" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
	window.onload=function() {
		var curDate = new Date();
		var month = curDate.getMonth() + 1 < 10 ? "0" + (curDate.getMonth() + 1)
				: curDate.getMonth() + 1 ;
		var day = curDate.getDate() < 10 ? "0" + (curDate.getDate()) : curDate
				.getDate();
		var hours = curDate.getHours() < 10 ? "0" + (curDate.getHours())
				: curDate.getHours();
		var min = curDate.getMinutes() < 10 ? "0" + (curDate.getMinutes())
				: curDate.getMinutes();
		var seconds = curDate.getSeconds() < 10 ? "0" + (curDate.getSeconds())
				: curDate.getSeconds();
		var curDateStr = curDate.getFullYear() +"-"+ month +"-"+ day +" "+ hours +":" + min +":" + seconds;
		$("#bka017").val(curDateStr); 
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

	
	

	function saveDate() {
		if (!checkForm()) {
			return;
		}
		if ($("#aac001").val() == '' || $("#aac001").val() == -1) {
			popupAlert("请录入参保人信息后再保存！");
			return $("#queryString").focus();
		} 
	
		var saveItemData = $("#bizForm").serialize();
		
		if(gridElectDetail.getData()==null||gridElectDetail.getData().length<=0){
	    	popupAlert("请录入电子处方信息后再保存！");
	    	return;
	    }
		
		var bka020=$("#bka019").find("option[value='"+$("#bka019").val()+"']").html();
		var bka022=$("#bka021").find("option[value='"+$("#bka021").val()+"']").html();
		var bka504=$("#bka503").find("option[value='"+$("#bka503").val()+"']").html();
	    var electData= powersi.tostring(gridElectDetail.getData());
		postJSON( "${rootPath}/medicare/HospElectpresAction!electPresSave.action?diagnoseInfoDTO.bka020="+encodeURI(encodeURI(bka020))
				+"&diagnoseInfoDTO.bka022="+encodeURI(encodeURI(bka022))
				+"&diagnoseInfoDTO.bka502="+encodeURI(encodeURI(bka504))
				+"&kad5List="+encodeURI(encodeURI(electData)),
				saveItemData ,
		  		function(json){
			if (!checkJSONResult(json)) {
				
				return;
			}
			if(json.data){
				
				$("#ake1id").val(json.data);
				popupInfo("电子处方保存成功，流水号为："+json.data);
				$("#btnSave").attr("disabled", true);
				$("#btn_print").attr("disabled", false);
				return;
			}
			popupInfo(json.message);
		});
	}
	function resetpage() {
		$(":text").val("");
		$("select").val("");
		$("#btnSave").attr("disabled", false);
		$("#btnCalc1").attr("disabled", true);
		$("#btn_print").attr("disabled", true);
		//入院科室
		$("#bka019").empty();
		//入院病区
		$("#bka021").empty();
		//医保医师
		$("#bka503").empty();
		$("#querystring").attr("disabled", false);
		gridElectDetail.reset();
		$("#querystring").focus();
		
	}

	function getPerson(para) {
		if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			if(para == "readic"){
				
			}else{
				$("#bke548").val('');
			}
			var indi_id = "0";
			postJSON(
					"${rootPath}/diagnose/GetPersonInfoAction!getPersonSpInfo.action?diagnoseInfoDTO.arg_name="
							+ $("#arg_name").val()
							+ "&diagnoseInfoDTO.arg_value="
							+ $("#querystring").val()
							+ "&diagnoseInfoDTO.bka006=131"
							+ "&diagnoseInfoDTO.bka017="
							+ $("#bka017").val() 
							+ "&diagnoseInfoDTO.aka130="
							+ $("#aka130").val()
							+ "&diagnoseInfoDTO.aae140="
							+ $("#aae140").val()
							+ "&diagnoseInfoDTO.bke548="
							+ $("#bke548").val(),
					function(json) {
						$("#bke548").val('');
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data.personinfo.length > 1) {
							choosepersonlist(querystring);
						} else {
							$.each(json.data.personinfo[0], function(key,
									value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
							
							$("#personData").val(powersi.tostring(json.data.personinfo[0]));
							$("#aae140").val(json.data.factorinfo[0].aae140);
							$("[name='diagnoseInfoDTO.baa027']").val(json.data.personinfo[0].baa027);
							$("[name='diagnoseInfoDTO.bka004']").val(json.data.personinfo[0].bka004);
							if (!powersi.isnull($("#aac001").val())) {
								if (json.data.spinfo&&json.data.spinfo.length > 1) {
									var personinfo = powersi.tostring(json.data.spinfo);
									popupDialog(
											{
												url : "${rootPath}/pages/biz/medicare/diagnose/chooseTreatmentInfo.jsp?personinfo="
														+ encodeURI(encodeURI(personinfo)),
												onClosed : function() {
													var ret = this.returnValue;
													if (ret != null) {
														$("#aaz267").val(ret.aaz267);
														$("#bka026_name").val(ret.aka121);
														$("#bka006").val(ret.bka006);
														$("#bka006_name").val(ret.bka155);
														$("#bka026").val(ret.bka026);
													} else {
														$("#aac001").val('');
														$("#aac003").val('');
														$("#aac002").val('');
														$("#aac006").val('');
														$("#bka008").val('');
														$("#aab001").val('');
														$("#aac004").val('');
														$("#baa027").val('');
														$("#bka004").val('');
													}
												}
											}, 500, 800);
								} else if(json.data.spinfo&&json.data.spinfo.length==1){
									$("#aaz267").val(json.data.spinfo[0].aaz267);
									$("#bka026").val(json.data.spinfo[0].bka026);
									$("#bka026_name").val(json.data.spinfo[0].aka121);
									$("#bka006").val(json.data.spinfo[0].bka006);
									$("#bka006_name").val(json.data.spinfo[0].bka155);
								}else{
									alert("参保人未申请门特门慢，不允许办理电子处方录入");
								}
							}
						}						
						if(json.data.msg!=null){
							alert("参保人基金已冻结，原因是："+json.data.msg);
					 	} 
					});
		}
	}
	
	
	function choosepersonlist(querystring){
		popupDialog(
				{
					url : "${rootPath}/diagnose/GetPersonInfoAction!chooseperson.action?diagnoseInfoDTO.arg_value="
						+ querystring,
					onClosed : function() {
						var ret = this.returnValue;
						if (ret != null) {
							indi_id = ret.aac001;
							postJSON(
									"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name=indi_id&diagnoseInfoDTO.arg_value="
											+ indi_id
											+ "&diagnoseInfoDTO.bka006=131"
											+ "&diagnoseInfoDTO.bka017="
											+ $("#bka017").val()
											+ "&diagnoseInfoDTO.aka130="
											+ $("#aka130").val()
											+ "&diagnoseInfoDTO.aae140="
											+ $("#aae140").val(),
									function(json) {
										if (!checkJSONResult(json)) {
											return;
										}
										$.each(json.data.personinfo[0], function(key,value) {
											if (!powersi.isnull(value)) {
												$("#" + key).val(value);
											}
										});
										$("#personData").val(powersi.tostring(json.data.personinfo[0]));
										$("#aae140").val(json.data.factorinfo[0].aae140);
										$("[name='diagnoseInfoDTO.baa027']").val(json.data.personinfo[0].baa027);
										$("#aaz267").val(json.data.spinfo[0].serial_apply);
										$("[name='diagnoseInfoDTO.bka004']").val(json.data.personinfo[0].bka004);
										if (!powersi.isnull($("#aac001").val())) {
											if (json.data.spinfo&&json.data.spinfo.length > 1) {
												var personinfo = powersi.tostring(json.data.spinfo);
												popupDialog(
														{
															url : "${rootPath}/pages/biz/medicare/diagnose/chooseTreatmentInfo.jsp?personinfo="
																	+ encodeURI(encodeURI(personinfo)),
															onClosed : function() {
																var ret = this.returnValue;
																if (ret != null) {
																	$("#aaz267").val(ret.aaz267);
																	$("#bka026_name").val(ret.aka121);
																	$("#bka006").val(ret.bka006);
																	$("#bka006_name").val(ret.bka155);
																	$("#bka026").val(ret.bka026);
																	$("#bka017_reg").val($("#bka017").val());
																	$("#bka017").prop("disabled", true);
																} else {
																	$("#aac001").val('');
																	$("#aac003").val('');
																	$("#aac002").val('');
																	$("#aac006").val('');
																	$("#bka008").val('');
																	$("#aab001").val('');
																	$("#aac004").val('');
																	$("#baa027").val('');
																	$("#bka004").val('');
																}
															}
														}, 500, 800);
											} else if(json.data.spinfo&&json.data.spinfo.length==1) {
												$("#aaz267").val(json.data.spinfo[0].aaz267);
												$("#bka026_name").val(json.data.spinfo[0].aka121);
												$("#bka006").val(json.data.spinfo[0].bka006);
												$("#bka006_name").val(json.data.spinfo[0].bka155);
												$("#bka026").val(json.data.spinfo[0].bka026);
												$("#bka017_reg").val($("#bka017").val());
												$("#bka017").prop("disabled", true);
											}else{
												alert("参保人未申请门特门慢业务，不允许办理电子处方录入");
											}
										}
									});
						}
					}
				}, 500, 600);
	}

	 function printreport(){
	        var saveItemData = $("#bizForm").serialize();
	        var electDetailData= powersi.tostring(gridElectDetail.getData());
	        var personData=$("#personData").val();
	    	 popupDialog(
			{
				url : "${rootPath}/medicare/HospElectpresAction!setElectronicReport.action?kad5DTO.ake1id="+$("#ake1id").val()
						+"&diagnoseInfoDTO.bka019="+$("#bka019").find("option[value='"+$("#bka019").val()+"']").html()
						+"&diagnoseInfoDTO.bka025="+$("#bka025").val()
						+"&diagnoseInfoDTO.bka026="+$("#bka026_name").val()
						+"&diagnoseInfoDTO.bka006="+$("#bka006_name").val()
						+"&diagnoseInfoDTO.bka043="+$("#bka043").val()
						+"&personData="+personData
						+"&electDetailData="+encodeURI(encodeURI(electDetailData)),
				onClosed : function() {

				}
			}, 500, 800); 
	}
	 
	 /*加載科室數據*/
	function loadBka019Data(){
		 
		postJSON(
				"${rootPath}/medicare/HospElectpresAction!loadBka019List.action",null, function(json) {
					if (!checkJSONResult(json)) {
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
						$("#bka019").append(a.join(''));
					}
					if (!powersi.isnull(json.message)) {
						alert(json.message);
					}
		});
	}
	    
	 /*加载病区*/
	function selectbka021() {
		//入院科室
		var bka019 = $("#bka019").val();
		//入院病区
		$("#bka021").empty();
		//入院床位号
		$("#bka023").empty();
		//医保医师
		$("#bka503").empty();
		$("#bka021").append("<option value='' ></option>");
		$("#bka023").append("<option value='' ></option>");
		$("#bka503").append("<option value='' ></option>");
		$("#bka021").change();
		$("#bka023").change();
		$("#bka503").change();
		if (powersi.isnull(bka019)) {
			return;
		}
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!loadBka021List.action",
				{
					"inHospitalDTO.bka019" : bka019
				}, function(json) {
					if (!checkJSONResult(json)) {
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
						alert(json.message);
					}
				});
	}
	
	/*加载医保医师*/
	function selectbka503() {
		//入院科室
		var bka019 = $("#bka019").val();
		if (powersi.isnull(bka019)) {
			return;
		}
		//入院病区
		var bka021 = $("#bka021").val();
		if (powersi.isnull(bka021)) {
			return;
		}
		$("#bka503").empty();
		$("#bka503").append("<option value='' ></option>");
		$("#bka503").change();
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!loadBka503List.action",
				{
					"inHospitalDTO.bka019" : bka019
				}, function(json) {
					if (!checkJSONResult(json)) {
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
						$("#bka503").append(a.join(''));
					}
					if (!powersi.isnull(json.message)) {
						alert(json.message);
					}
				});
	}	

	</script>
</body>
</powersi:html>