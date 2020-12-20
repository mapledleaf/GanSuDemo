<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%
	String path = request.getContextPath();
%>

<powersi:html>
<head>
<powersi:head title="项目信息" target="_self" />
</head>
<body>

<powersi:form id="form"  namespace="/medicare" action="/health/ExaminationAction!queryCusionInfo.action">
<powersi:panelbox key="查询条件" cssStyle="display:none">
	<powersi:hidden id="aaz217" key="体检登记号" name="setMealDTO.aaz217"/>
	<powersi:hidden id="flag" key="套餐内外标志" name="setMealDTO.flag"/>
	<powersi:hidden id="center_flag" key="套餐内外标志" name="setMealDTO.center_flag"/>
</powersi:panelbox>
</powersi:form>
<powersi:panelbox>
				<powersi:panelbox-toolbar>
			</powersi:panelbox-toolbar>
				<powersi:datagrid id="cusionGrid" height="85%"  formId="form"
				delayLoad="false" showReload="false" autoWidth="true" 
				alternatingRow="true" checkbox="false" colDraggable="true"
				pageSize="200">
					<powersi:datagrid-column name="bkh022" display="费用序列号" width="15%"/>
					<powersi:datagrid-column name="bkh046" display="统计类别 " width="20%" />
					<powersi:datagrid-column name="bkh030" display="体检项目名称" width="30%"/>
					<powersi:datagrid-column name="bka040" display="单价" width="12%" />
					<powersi:datagrid-column name="akc226" display="数量" width="10%" />	
					<powersi:datagrid-column name="aae019" display="金额" width="15%" />	
	
		</powersi:datagrid> 
	</powersi:panelbox>


<div class="div_center">
	<powersi:button cssClass="button" label="关闭" onclick="javascript:closeDialog();"/>
</div>
<powersi:errors />
<script type="text/javascript">


</script>
</body>
</powersi:html>
