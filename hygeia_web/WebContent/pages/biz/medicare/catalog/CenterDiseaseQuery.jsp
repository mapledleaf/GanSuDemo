<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("akb021", BizHelper.getAkb021());
	request.setAttribute("aaa027Name", BizHelper.getAaa027Name());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<powersi:head title="中心疾病信息查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HospCataAction!queryCenterDisease.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search" />
				<powersi:button id="btExport" label="导 出" buttonIcon="icon-signout" onclick="grid.exportExcel2007()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="diseaseDto.caa027" label="选择中心" 
						codeType="caa027" headerKey="0"/>
					<powersi:textfield id="aka120" name="diseaseDto.aka120" label="疾病编码" />
					<powersi:textfield id="aka121" name="diseaseDto.aka121" label="疾病名称" />
					<powersi:textfield id="akb021" label="医院名称" readonly="true" value="${akb021 }"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="疾病目录列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" showReload="false" frozen="false" 
			enabledEdit="true" showExportExcel="true" showExportExcel2007="true" showExportPdf="true" 
			exportFileName="'医保疾病信息'">
            <powersi:datagrid-column name="aka120" display="疾病编码" />
			<powersi:datagrid-column name="aka121" display="疾病名称" />
			<powersi:datagrid-column name="aka021" display="五笔码" />
			<powersi:datagrid-column name="aka020" display="首拼码" />
			<powersi:datagrid-column name="aae100" display="有效标志" code="valid_flag" />
			<powersi:datagrid-column name="aae030" display="生效日期" format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column name="aae031" display="失效日期" format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column name="bke961" display="疾病费用标准" />
			<powersi:datagrid-column name="aae013" display="备注" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</html>