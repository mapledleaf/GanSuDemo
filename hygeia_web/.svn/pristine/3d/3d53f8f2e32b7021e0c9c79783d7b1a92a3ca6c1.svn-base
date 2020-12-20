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
	<powersi:form id="queryForm" action="MaternityRegHospAction!approbateInfQuery" namespace="/medicare">
	<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btQuery" value="查询" onclick="doQuery()"/>
				<powersi:button id="btReset" value="重 置" onclick="doReset()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield label="姓名" id="aac003" name="taDTO.aac003"
						readonly="true" />
					<powersi:textfield label="身份证号" id="aac002" name="taDTO.aac002"
						readonly="true" />
					<powersi:textfield label="电脑号" id="aac001" name="taDTO.aac001"
						readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				  <powersi:codeselect id="bmc066" name="taDTO.bmc066"
						label="审核标志" codeType="audit_flag" />
					<powersi:codeselect id="bmc053" name="taDTO.bmc053"
						codeType="valid_flag" label="有效标志"  headerKey="-1"/>
					<powersi:textfield id="ylks" label="开始时间"
						name="taDTO.ylks" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="yljs" label="结束时间"
						name="taDTO.yljs" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
			</powersi:form>

	<powersi:panelbox key="panel_result" title="结果列表">
	<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" enabledSort="false"
			showReload="false" frozen="false" 
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'生育资格登记信息'" >
			<powersi:datagrid-column display="姓名" name="aac003"  />	
			<powersi:datagrid-column display="身份证号" name="aac002" width="140" />
			<powersi:datagrid-column name="aab069" display="单位名称" width="200"/>
			<powersi:datagrid-column name="bmc013" display="预产期" />
			<powersi:datagrid-column name="amc021"  display="准生证号"/>
			<powersi:datagrid-column name="aae030"  display="医疗开始时间"/>
			<powersi:datagrid-column name="aae031"  display="医疗结束时间"/>
			<powersi:datagrid-column name="bmc066"  display="审核标志" code="audit_flag"/>
			<powersi:datagrid-column name="bmc053"  display="有效标志" code="valid_flag"/>
			<powersi:datagrid-column name="aae127"  display="操作时间"/>
		</powersi:datagrid>
		</powersi:panelbox>

	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#yljs').val(year + "-" + month + "-" + day);
		
		var ldDate = new Date();
		ldDate.setMonth(ldDate.getMonth()- 1);
		year = ldDate.getFullYear();
		month = (ldDate.getMonth()+1).toString().length == 1?"0"+(ldDate.getMonth()+1).toString():ldDate.getMonth().toString();
		day = ldDate.getDate().toString().length == 1?"0"+ldDate.getDate().toString():ldDate.getDate().toString();
		$('#ylks').val(year + "-" + month + "-" + day);
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
		   postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action",
					{
						"mediSpecDto.arg_name" : indi_id,
						"mediSpecDto.arg_value" : argValue
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
												+ "/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action?mediSpecDto.arg_name=indi_id&mediSpecDto.arg_value="
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
					});
		}
	
	function doQuery(){
		$("#queryForm").submit();
	}
	
	function doReset(){
		$("#tracestring").val("");
		$("#aac003").val("");
		$("#aac002").val("");
		$("#aac001").val("");
		$("#bmc066").val("");
		$("#bmc053").val("1");
		
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#yljs').val(year + "-" + month + "-" + day);
		
		var ldDate = new Date();
		ldDate.setMonth(ldDate.getMonth()- 1);
		year = ldDate.getFullYear();
		month = (ldDate.getMonth()+1).toString().length == 1?"0"+(ldDate.getMonth()+1).toString():ldDate.getMonth().toString();
		day = ldDate.getDate().toString().length == 1?"0"+ldDate.getDate().toString():ldDate.getDate().toString();
		$('#ylks').val(year + "-" + month + "-" + day);
	}
	
	</script>
</body>
</powersi:html>