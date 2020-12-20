<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
//TS19112600047      【需求开发】离休人员刷卡提示消费记录（结算云）
//新增30天消费界面 赵银溪   20191201
%>
<powersi:html>
<head>
<powersi:head title="30天消费记录"  target="_self" />
</head>
<body>
	<powersi:form id="queryForm"
		action="GetPersonInfoAction!queryChargeretire.action" namespace="/diagnose">
	<powersi:groupbox title="查询结果">
 	    <powersi:hidden id="aac001" name="diagnoseInfoDTO.aac001" /> 
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			showReload="false"  enabledSort="false" >
			<powersi:datagrid-column name="akc194" display="刷卡时间" width="100"/>
			<powersi:datagrid-column name="akb021" display="医疗机构" width="100"/>
			<powersi:datagrid-column name="jjje" display="医保支付金额" width="100"/>
			<powersi:datagrid-column name="grje" display="个人自付金额" width="100"/>
			<powersi:datagrid-column name="je" display="总金额" width="100"/>
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