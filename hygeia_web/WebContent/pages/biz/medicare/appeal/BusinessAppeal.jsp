<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="processTag" tagdir="/WEB-INF/tags"%>
<%@page
	import="com.powersi.hygeia.framework.util.UtilFunc,com.powersi.hygeia.framework.BusiContext"%>
<%
	String path = request.getContextPath();

	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String time = dformat.format(d);
	String str_date_end = time;
	String str_date_begin = time.substring(0, 8) + "01";
%>

<powersi:html>
<head>
<powersi:head title="业务自查申诉" target="_self" />
</head>
<body>
	<powersi:form name="mainForm" id="mainForm"
		action="/DeclarePayAction!getBusinessAppealInfo.action"
		namespace="/medicare">
		<powersi:hidden name="apa709" value="4" />
		<powersi:hidden name="bpd406" value="-1" />
		<powersi:hidden id="aac001" name="appealDTO.aac001"></powersi:hidden>
		<powersi:hidden id="apa709" name="appealDTO.apa709"></powersi:hidden>
		<powersi:panelbox iconClass="icon-inbox" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" />
				<powersi:button label="重 置" onclick="initInfo()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8" id="conditiontable">
				<powersi:editorlayout-row>
					<powersi:hidden id="querystring" />
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="findAac001()" buttonDisabled="false"/>

					<powersi:textfield label="姓名" id="aac003" name="appealDTO.aac003"
						key="aac003" readonly="true" />
					<powersi:textfield label="身份证号" id="aac002" name="appealDTO.aac002"
						key="aac002" readonly="true" />

					<powersi:codeselect id="bpd402" label="风险等级"
						name="appealDTO.bpd402" codeType="drl_level" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="sjlx" label="时间类型" name="appealDTO.sjlx"
						codeType="sjlx" />
					<powersi:textfield id="beginDate" name="appealDTO.beginDate"
						label="从" mask="date" format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="endDate" name="appealDTO.endDate" label="至"
						mask="date" format="dateFmt:'yyyy-MM-dd'" />
					<powersi:codeselect id="ape139" name="appealDTO.ape139"
						label="申诉标志" codeType="ape139" headerKey="-1" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-inbox" title="待申诉列表">
		<powersi:datagrid id="grid" formId="mainForm" delayLoad="true"
			enabledSort="false" pageSize="100" height="100%"
			enabledExportExcel="true" exportFileName="'待申诉列表'">
			<powersi:datagrid-column display="操作" frozen="true" render="renderCZ"
				width="6%" minWidth="40" />

			<powersi:datagrid-column display="申诉状态" frozen="true" name="ape139"
				code="ape139" />
			<powersi:datagrid-column display="过期提醒" frozen="true"
				render="renderState" />
			<powersi:datagrid-column display="姓名" name="aac003" />
			<powersi:datagrid-column display="证件号码" name="aac002" />
			<powersi:datagrid-column display="人员类别" name="akc021" code="bka004" />
			<powersi:datagrid-column display="性别" name="aac004" code="aac004" />
			<powersi:datagrid-column display="待遇类型" name="bka003" code="bka006" />
			<powersi:datagrid-column display="出院主要疾病诊断" name="akc185" />
			<powersi:datagrid-column display="结算时间" name="akc194"
				format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column display="医疗类别" name="aka130" code="aka130" />
			<powersi:datagrid-column display="入院日期" name="aae030" />
			<powersi:datagrid-column display="出院日期" name="aae031" />
			<powersi:datagrid-column display="申诉截止时间" name="bpd405"
				format="{0,date,yyyy-MM-dd}" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
	$(document).ready(function() {
		 if($('#beginDate').val()==""){
			 initInfo();
	   	 }
	});

	function initInfo(){
		$("#sjlx").val(3);
		$("#beginDate").val("<%=str_date_begin%>");
		$("#endDate").val("<%=str_date_end%>");
		//$('#ape139').val(getQueryString('ape139'));
		grid.reset();
	}

		function renderState(row, index, value) {
			var a = [];
			var ts = parseInt(row["ts"]);
			if (ts > 0) {
				a.push(" <i class='red'> <span>余" + ts + "天</span></i>");
			} else {
				a.push("<i class='green'> <span >已过期</span></i>");
			}
			return a.join('');
		}

		function renderCZ(row, index, value) {
			var a = [];
			a.push('<a onclick="editinfo(\'');
			a.push(row["aaz217"]);
			a.push('\',');
			a.push(row["aaz328"]);
			a.push(')"> ');
			a.push('处理');
			a.push(" </a>");
			return a.join('');
		}
		
		function editinfo(aaz217, aaz328){
			var apa709 = "";
			popupDialog("${rootPath}/medicare/DeclarePayAction!getBusinessAppealDetail.action?aaz217="
							+ aaz217 + "&apa709=" + apa709, screen.height,
					screen.width);
		}

		function saveRemark() {
			var rows = gridKa52.getSelectedRows();
			if (powersi.isempty(rows)) {
				popupAlert("请选择申诉明细信息！");
				return;
			}
		}

		/*查询参保信息*/
		function findAac001() {
			if (window.event.keyCode == 13) {
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入信息回车！");
					return;
				}
				$("#tracestring").attr("disabled", "disabled");
			
				$("#querystring").val(tracestring);
				
				var argName = "indi_id";
		        var argValue = $("#querystring").val();

		        if(argValue.length  > 10){
					argName = "idcard";
				}else{
					argName = "indi_id";
				}
				
				postJSON(rootPath +"/medicare/OutBizAction!getPersonInfo.action?outBizDTO.arg_name="+argName+"&outBizDTO.arg_value="+$("#querystring").val(),
				  		function(json){
							if(!checkJSONResult(json)){
							    return;
						    }
							
							if (json.data != "no") {
								$.each(json.data, function(key, value) {
									if (!powersi.isnull(value)) {
										$("#" + key).val(value);
									}
								});
							}else{
								popupQuestion("没有获取到人员信息！",3000);
							}
				});
				
				$("#tracestring").removeAttr("disabled");
			}
		}
	</script>
</body>
</powersi:html>