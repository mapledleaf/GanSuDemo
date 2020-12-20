<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="processTag" tagdir="/WEB-INF/tags"%>

<powersi:html>
<head>
<powersi:head title="医院自查申诉" target="_self"/>
<script src="${rootPath}/resource/js/common.js" type="text/javascript"></script>
<link href="${rootPath}/resource/css/common.css" rel="stylesheet">
<script type="text/javascript">
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

function renderJKLX(row,index,value){
	var bpd402 = row['bpd402'];
	var a = [];
	a.push("<div>");
	var drl_def_name = row["drl_def_name"];
	a.push('<i class="');
	if (bpd402 == '0'){
		a.push("red icon-bolt");
	}else if (bpd402 == '1'){
		a.push("green icon-fire");
	}else if (bpd402 == '2'){
		a.push('icon-warning-sign"');
		a.push(' style="color:#fcc92f"');
	}
	a.push('" title="' + drl_def_name +
	'"> ');
	a.push("  <span>（");
	a.push(row['bpd402_name']);
	a.push("） </span></i>");
	a.push("</div>");
	return a.join('');
}

function renderCZ(row,index,value){
		var a = [];
		a.push('<a onclick="editinfo(');
		a.push(row["aaz328"]);
		a.push(',');
		a.push(row["aaz010"]);
		a.push(')"> ');
		a.push('处理');
		a.push(" </a>");
		return a.join('');
}

function editinfo(aaz328, aaz010){
	popupDialog('${rootPath}/hosp/appealAction!getHospitalAppealHandle.action?'+
			'aaz328='+aaz328+"&aaz010="+aaz010, 
			screen.height, screen.width);
 }
</script>
</head>
<body style="overflow: hidden">
	<powersi:form name="mainForm" id="mainForm" action="/hosp/appealAction!getHospitalAppealInfo.action" namespace="/hospform">
		<powersi:panelbox iconClass="icon-inbox" title="查询条件">
			<powersi:panelbox-toolbar>
						<powersi:submit id="btSubmit" key="button_query"/>
						<powersi:button key="button_reset" onclick="initForm($('#mainForm'))" />
		  </powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8" id="conditiontable" >
				<powersi:editorlayout-row>
					<processTag:aaa027Choose/>
					<powersi:textfield id="beginDate" name="beginDate" label="监控日期" mask="date" />
					<powersi:textfield id="endDate" name="endDate" label="至" mask="date" />
					<powersi:codeselect id="bpd402" label="风险等级" name="bpd402" codeType="drl_level"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row >
					<processTag:rulesChoose/>
					<powersi:codeselect label="申诉标志" name="ape139" codeType="ape139" value="${ape139 }"/>
					<powersi:editorlayout-input colspan="4" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox  title="待申诉列表">	
		<powersi:datagrid id ="grid" formId="mainForm" delayLoad="true" 
			pageSize="100" height="100%" enabledExportExcel="true" exportFileName="'待申诉列表'">
			<powersi:datagrid-column display="操作" render="renderCZ" 
				width="10%" minWidth="40" />
			<powersi:datagrid-column display="风险等级" name="bpd402_name" data="bpd402" code="drl_level" render="renderBpd402"
				width="10%" minWidth="40" />				
			<powersi:datagrid-column display="监控类型" name="aaa153_name" data="aaa153" code="aaa153"
				width="20%" minWidth="40" />
			<powersi:datagrid-column display="监控日期" name="abb057" format="{0,date,yyyy-MM-dd}"
				width="15%" minWidth="40" />
			<powersi:datagrid-column display="违反规则" name="drl_def_name"
				width="30%" minWidth="40" />
			<powersi:datagrid-column display="申诉截止时间" name="bpd405" format="{0,date,yyyy-MM-dd}"
				width="15%" minWidth="40" />
		</powersi:datagrid>
	</powersi:panelbox>
		
	<powersi:errors />
</body>
</powersi:html>