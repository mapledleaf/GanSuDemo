<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<powersi:html>
<head>
<powersi:head title="医院预约挂号管理" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
	<br>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="上传医院预约挂号号源" />
		<powersi:tab id="tab2" target="divTab2" label="预约挂号" />
		<powersi:tab id="tab3" target="divTab3" label="取消预挂号" />
		<%-- <powersi:tab id="tab4" target="divTab4" label="查询预约挂号（或取消预挂号）结果" />
		<powersi:tab id="tab5" target="divTab5" label="上传预约挂号停诊异动信息" /> --%>
		
		<div id="divTab1">
			<tags:saveDoctorSourceInfos />
		</div>
		
		<div id="divTab2">
			<tags:submitAppointment />
		</div>
		
		<div id="divTab3">
			<tags:queryAppointmentInfos />
		</div>
		
		<%-- <div id="divTab4">
			<tags:saveAppointmentInfos />
		</div>
		
		<div id="divTab5">
			<tags:saveAppointmentStopErrorInfos />
		</div> --%>

	</powersi:tabbedpanel>
	
	<tags:transCode/>
	<powersi:errors />

<script type="text/javascript">
</script>
</body>
</powersi:html>