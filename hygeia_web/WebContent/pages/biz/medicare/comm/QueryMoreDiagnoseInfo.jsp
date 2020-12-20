<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
//TS19052700141   【需求开发】结算云（医院端）出院结算界面和住院信息查询界面需要显示多诊断信息  
//新增提供多诊断查看界面  陈洁   20190529
%>
<powersi:html>
<head>
<powersi:head title="个人住院多诊断查看" target="_self" />
</head>
<body>
	<powersi:form id="queryForm"
		action="InhospitalManagerAction!queryDiagnoseInfos.action" namespace="/inhospital">
	<powersi:groupbox title="查询结果">
	    <powersi:hidden id="aaz217" name="inHospitalDTO.aaz217" />
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			showReload="false"  enabledSort="false" >
			<powersi:datagrid-column name="aka120" display="疾病编码" width="300"/>
			<powersi:datagrid-column name="aka121" display="疾病名称" width="300"/>
		</powersi:datagrid>
	</powersi:groupbox>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		window.onload = function(){
			var myDate = new Date();
			$("#queryForm").submit();
		}
		</script>
</body>
</powersi:html>