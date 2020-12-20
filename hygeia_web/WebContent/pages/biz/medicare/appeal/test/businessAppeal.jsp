<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="processTag" tagdir="/WEB-INF/tags"%>
<%@page import="com.powersi.hygeia.framework.util.UtilFunc,com.powersi.hygeia.framework.BusiContext"%>
<%
	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat(
			"yyyyMMdd");
	String time = dformat.format(d);
	String str_date_end = time;
	String str_date_begin = time.substring(0, 6) + "01";
%>

<powersi:html>
<head>
<powersi:head title="业务自查申诉" target="_self"/>
<script src="${rootPath}/resource/js/common.js" type="text/javascript"></script>
<link href="${rootPath}/resource/css/common.css" rel="stylesheet">
<script type="text/javascript">
function initInfo(){
	$("#sjlx").val(3);
	$("#beginDate").val("<%=str_date_begin%>");
	$("#endDate").val("<%=str_date_end%>");
	$('#ape139').val(getQueryString('ape139'));
}

function renderState(row,index,value){
	var a = [];
	var ts = parseInt(row["ts"]);
	if(ts > 0 ){
		a.push(" <i class='red'> <span>余"+ts+"天</span></i>");
	}else{
		a.push("<i class='green'> <span >已过期</span></i>");
	}
	return a.join('');
}

function renderCZ(row,index,value){
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
	popupDialog('${rootPath}/hosp/appealAction!getBusinessAppealDetail.action?aaz217='+aaz217+"&aaz328="+aaz328, 
			screen.height, screen.width);
 }
</script>
</head>
<body style="overflow: hidden">
	<powersi:form name="mainForm" id="mainForm" action="/hosp/appealAction!getBusinessAppealInfo.action" namespace="/hospform">
		<powersi:hidden name="apa709" value="4" />
		<powersi:hidden name="bpd406" value="0" />
		<powersi:panelbox iconClass="icon-inbox" title="查询条件">
		    <powersi:panelbox-toolbar>
		    	<powersi:submit id="btSubmit" key="button_query"/>
				<powersi:button key="button_reset" onclick="initForm($('#mainForm'), initInfo)" />
		    </powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8" id="conditiontable" >
				<powersi:editorlayout-row>
					<processTag:aaa027Choose/>
					<processTag:personChoose/>
					<powersi:codeselect id="bpd402" label="风险等级" name="bpd402" codeType="drl_level"/>					
					<processTag:rulesChoose/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row >
					<powersi:codeselect id="sjlx" label="时间类型" name="sjlx" codeType="sjlx"/>
					<powersi:textfield id="beginDate" name="beginDate" label="从" mask="date" />
					<powersi:textfield id="endDate" name="endDate" label="至" mask="date" />
					<powersi:codeselect id="ape139" name="ape139" label="申诉标志" codeType="ape139" value="${ape139 }"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox  title="待申诉列表">	
		<powersi:datagrid id ="grid" formId="mainForm" delayLoad="true" 
			pageSize="100" height="100%" enabledExportExcel="true" exportFileName="'待申诉列表'">
			<powersi:datagrid-column display="操作" frozen="true" render="renderCZ" 
				width="6%" minWidth="40" />					
			<powersi:datagrid-column display="申诉状态" frozen="true" name="ape139_name" data="ape139" code="ape139"
				width="6%" minWidth="40" />
			<powersi:datagrid-column display="过期提醒" frozen="true" render="renderState" 
				width="8%" minWidth="40" />
			<powersi:datagrid-column display="风险等级" frozen="true" render="renderBpd402" 
				width="8%" minWidth="40" />
			<powersi:datagrid-column display="姓名" name="aac003" 
				width="10%" minWidth="80" />
			<powersi:datagrid-column display="证件号码" name="aac002" 
				width="14%" minWidth="120" />
			<powersi:datagrid-column display="人员类别" name="akc021_name" data="akc021" code="akc021" 
				width="8%" minWidth="60" />
			<powersi:datagrid-column display="性别" name="aac004_name" data="aac004" code="aac004" 
				width="6%" minWidth="40" />
			<powersi:datagrid-column display="待遇类型" name="bka003_name" data="bka003" code="bka003" 
				width="6%" minWidth="40" />
			<powersi:datagrid-column display="出院主要疾病诊断" name="akc185" 
				width="14%" minWidth="40" />				
			<powersi:datagrid-column display="结算时间" name="akc194" 
				width="12%" minWidth="40" />
			<powersi:datagrid-column display="科室" name="aae386" 
				width="10%" minWidth="40" />
			<powersi:datagrid-column display="医师" name="aaz263_name" 
				width="10%" minWidth="40" />
			<powersi:datagrid-column display="医疗类别" name="aka130_name" data="aka130" code="aka130" 
				width="6%" minWidth="40" />
			<powersi:datagrid-column display="入院日期" name="aae030" 
				width="8%" minWidth="40" />
			<powersi:datagrid-column display="出院日期" name="aae031" 
				width="8%" minWidth="40" />
			<powersi:datagrid-column display="申诉截止时间" name="bpd405" format="{0,date,yyyy-MM-dd}"
				width="8%" minWidth="40" />
		</powersi:datagrid>
	</powersi:panelbox>
		
	<powersi:errors />
</body>
<script type="text/javascript">
$(document).ready(function() {
	 if($('#beginDate').val()==""){
		 initInfo();
   	 }
});
</script>
</powersi:html>