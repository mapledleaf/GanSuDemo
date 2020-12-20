<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.Timestamp,java.text.SimpleDateFormat,java.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
%>

<powersi:html>
<head>
<powersi:head title="生育登记" />
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="mainFrom" action="" namespace="/medicare">
		<powersi:groupbox title="生育保险参保人员信息">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSave" value="登 记" onclick="doSave()" />
				<powersi:button id="btReset" value="重 置" onclick="doReset()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />

					<powersi:textfield label="姓名" id="aac003" name="taDTO.aac003"
						key="aac003" readonly="true" required="true" />
					<powersi:textfield label="身份证号" id="aac002" name="taDTO.aac002"
						key="aac002" readonly="true" required="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac004_name" label="性别" readonly="true" />
					<powersi:textfield id="aae030" label="参保日期" name="taDTO.aae030"
						readonly="true" />
					<powersi:textfield id="ljys" label="累计缴费月数" name="taDTO.ljys"
						readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aab001" label="单位管理码" name="taDTO.aab001"
						readonly="true" required="true" />
					<powersi:textfield id="aab069" label="单位名称" name="taDTO.aab069"
						readonly="true" />
					<powersi:codeselect codeType="aab019" id="aab019"
						name="taDTO.aab019" label="单位类型" disabled="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>

		<powersi:groupbox title="生育待遇资格登记信息">
			<powersi:editorlayout cols="6">

				<powersi:editorlayout-row>
					<powersi:textfield id="amc021" name="taDTO.amc021" label="准生证号"
						validate="maxSize[30]" colspan="3" required="true"/>
					<powersi:codeselect codeType="amc027" id="amc027"
						name="taDTO.amc027" label="晚育标志" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:textfield id="ylks" name="taDTO.ylks" label="医疗开始时间"
						required="true" readonly="false" mask="date" format="dateFmt:'yyyy-MM-dd'"/>
					<powersi:textfield id="yljs" name="taDTO.yljs" label="医疗结束时间"
						required="true" readonly="true" />
					<powersi:textfield id="bmc013" name="taDTO.bmc013" label="预产期"
						mask="date" format="dateFmt:'yyyy-MM-dd'" required="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="联系电话" id="aae005" name="taDTO.aae005"
						required="true" validate="mobilephone" />
					<powersi:textfield label="联系地址" id="aae006" name="taDTO.aae006"
						required="true" colspan="3"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="aac001" name="taDTO.aac001" />
					<powersi:hidden id="aaz157" name="taDTO.aaz157" />
					<powersi:hidden id="aaz159" name="taDTO.aaz159" />
					<powersi:hidden id="aaz107" name="taDTO.aaz107" />
					<powersi:hidden id="akb020" name="taDTO.akb020" />
					<powersi:hidden id="right" name="taDTO.right" />
					<powersi:hidden id="lxys" name="taDTO.lxys" />
					<powersi:hidden id="baa020" name="taDTO.baa020" />
					<powersi:hidden id="baa027" name="taDTO.baa027" />
					<powersi:hidden id="aac004" name="taDTO.aac004" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
	</powersi:form>

	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#ylks').val(year + "-" + month + "-" + day);
		
		var ldDate = new Date();
		ldDate.setMonth(ldDate.getMonth()+11);
		year = ldDate.getFullYear();
		month = (ldDate.getMonth()+1).toString().length == 1?"0"+(ldDate.getMonth()+1).toString():ldDate.getMonth().toString();
		day = ldDate.getDate().toString().length == 1?"0"+ldDate.getDate().toString():ldDate.getDate().toString();
		$('#yljs').val(year + "-" + month + "-" + day);
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
		var bizFormData = $("#mainFrom").serialize();
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
							findAac001("readic");
							$("#arg_name").val("");
						}
					}
				});
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
	   //调用业务申请人员基本信息action
	   postJSON("<%=path%>/medicare/MaternityRegHospAction!getPersonInfo.action",
				{
					"taDTO.arg_name" : indi_id,
					"taDTO.arg_value" : argValue
				}, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data.personinfo.length > 1) {//一个身份证对应多个电脑号，弹出选择窗口
						var personinfo = powersi.tostring(json.data.personinfo);
						popupDialog(
						{
							url : rootPath
									+ "/pages/biz/medicare/comm/ChoosePerson.jsp?personinfo="
									+ encodeURI(encodeURI(personinfo)),
							onClosed : function() {
								var ret = this.returnValue;
								if (ret != null) {
									indi_id = ret.aac001;//电脑号
									postJSON(
									rootPath
											+ "/medicare/MaternityRegHospAction!getPersonInfo.action?taDTO.arg_name=indi_id&taDTO.arg_value="
											+ indi_id,
									function(json) {
										if (!checkJSONResult(json)) {
											return;
										}
										$.each(json.data.personinfo[0],
											function(key, value) {
												if (!powersi.isnull(value)) {
													$("#" + key).val(value);
												}
											});
									});
								}
							}
						}, 400, 500);
					}else{
						$.each(json.data.personinfo[0],
							function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
					}
					//任务：TS18041800127    tangmin 2018/4/18
					$("#aae005").val("");
					if($("#aac004").val() != '2'){
						popupAlert("生育资格登记只限女性，请重新输入！");
						$("#btSave").attr("disabled","disabled");
					}else{
						$("#btSave").removeAttr("disabled");
					}
				});
	}
	
	function doSave(){
		 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
		 
     	if ($("#aac001").val() == '' || $("#aac001").val() == null) {
			popupAlert("人员信息不能为空！");
			return;
		}
         var saveItemData = $("#mainFrom").serialize();
         postJSON("<%=path%>/medicare/MaternityRegHospAction!approbateInfSave.action",
					saveItemData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data == '1') {
							popupSuccess("保存成功！");
							doReset();
							//location.reload(true);
						} else {
							popupAlert("保存失败！");
						}
					});

		}
		
		function doReset(){
			$("#tracestring").val("");
			$("#aac003").val("");
			$("#aac002").val("");
			$("#aac004").val("");
			$("#aac004_name").val("");
			$("#aae030").val("");
			$("#ljys").val("");
			$("#aab001").val("");
			$("#aab069").val("");
			$("#aab019").val("");
			$("#amc021").val("");
			$("#amc027").val("");
			$("#bmc013").val("");
			$("#aae005").val("");
			$("#aae006").val("");
			
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
			var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
			$('#ylks').val(year + "-" + month + "-" + day);
			
			var ldDate = new Date();
			ldDate.setMonth(ldDate.getMonth()+11);
			year = ldDate.getFullYear();
			month = (ldDate.getMonth()+1).toString().length == 1?"0"+(ldDate.getMonth()+1).toString():ldDate.getMonth().toString();
			day = ldDate.getDate().toString().length == 1?"0"+ldDate.getDate().toString():ldDate.getDate().toString();
			$('#yljs').val(year + "-" + month + "-" + day);
		}
	</script>
</body>
</powersi:html>