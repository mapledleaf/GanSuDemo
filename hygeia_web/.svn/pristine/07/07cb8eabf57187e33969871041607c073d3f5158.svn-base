<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String zxTime = request.getParameter("zxTime");
	String jsyTime = request.getParameter("jsyTime");
	String redisTime = request.getParameter("redisTime");
	String bizFlag = request.getParameter("bizFlag");
	String policyType = request.getParameter("policyType");
	String aaa027 = request.getParameter("aaa027");
%>
<powersi:html>
<head>
<powersi:head title="业务缓存数据跟踪" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/manager"
		action="CacheTrackManager!queryMissInfoDetail.action">
		<powersi:hidden id="zxTime" name="zxTime"/>
		<powersi:hidden id="jsyTime" name="jsyTime"/>
		<powersi:hidden id="redisTime" name="redisTime"/>
		<powersi:hidden id="bizFlag" name="bizFlag"/>
		<powersi:hidden id="policyType" name="policyType"/>
		<powersi:hidden id="aaa027" name="aaa027"/>
	</powersi:form>
	
	<div class="tab-content">
	<div class="tab-pane active" id="tab5">
	<powersi:groupbox title="数据列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" height="410" rowAttrRender="gridRowRender"
			enabledEdit="false" clickToEdit="false" >
			<powersi:datagrid-column display="主键ID" name="id" data="id" width="10%"/>
			<powersi:datagrid-column display="描述" name="remark" data="remark" width="40%"/>
			<powersi:datagrid-column display="限额" name="treat_limit" data="treat_limit" width="10%"  hide="false"/>
			<powersi:datagrid-column display="更新时间" name="bka973" data="bka973" width="20%"/>
			<powersi:datagrid-column display="生效时间" name="effect_date" data="effect_date" width="20%"/>
			<powersi:datagrid-column display="失效时间" name="expire_date" data="expire_date" width="20%"/>
		</powersi:datagrid>
	</powersi:groupbox>
	</div>
	</div>
	
	<powersi:errors />
<script type="text/javascript">
	$(function() {
		$('#zxTime').val("<%=zxTime%>");
		$('#jsyTime').val("<%=jsyTime%>");
		$('#redisTime').val("<%=redisTime%>");
		$('#bizFlag').val("<%=bizFlag%>");
		$('#policyType').val("<%=policyType%>");
		$('#aaa027').val("<%=aaa027%>");
		$("#queryForm").submit();
	});
	
	function gridRowRender(rowdata, rowid){
		return getColorStyle('error');
	}
</script>
</body>
</powersi:html>