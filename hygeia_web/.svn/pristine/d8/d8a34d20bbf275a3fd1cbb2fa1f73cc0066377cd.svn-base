<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<head>
<powersi:head title="中心药品目录查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HospCataAction!queryCenterCata.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search" />
				<powersi:button id="btExport" label="导 出" buttonIcon="icon-signout" onclick="grid.exportExcel2007()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="hospCataDto.caa027" label="选择中心" 
						headerKey="0" codeType="caa027"/>
					<powersi:codeselect id="ake003" name="hospCataDto.ake003" label="目录类别" 
						headerKey="1,4,5" headerValue="请选择.." codeType="ake003"
						codeFilter="data_value in ('1','4','5') "/>
					<powersi:textfield  id="bkc144" name="hospCataDto.bkc144" label="目录编码" />
					<powersi:textfield  id="bkc143" name="hospCataDto.bkc143" label="目录名称" />
					<powersi:codeselect id="aae100" name="hospCataDto.aae100" label="有效标志" 
						codeType="valid_flag" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="中心药品目录列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			showReload="false" frozen="false" enabledEdit="true"
			showExportExcel="true" showExportExcel2007="true" enabledSort="false"
			showExportPdf="true" exportFileName="'医保药品目录信息'">
			<powersi:datagrid-column name="bkc144" label="目录编码" />
			<powersi:datagrid-column name="bkc143" label="目录名称" />
			<powersi:datagrid-column name="ake003" display="目录类别" code="ake003" />
			<powersi:datagrid-column name="aka063" display="统计类别" code="aka063" />
			<powersi:datagrid-column name="aka021" display="五笔码" />
			<powersi:datagrid-column name="aka020" display="首拼码" />
			<powersi:datagrid-column name="aka065" display="目录等级" code="aka065" />
			<powersi:datagrid-column name="aka070" display="剂型" code="aka070" />
			<powersi:datagrid-column name="bke215" label="职工支付比例" />
			<powersi:datagrid-column name="bke216" label="居民支付比例" />
			<powersi:datagrid-column name="bke217" label="离休支付比例" />
			<powersi:datagrid-column name="aae100" display="有效标志" code="valid_flag" />
			<powersi:datagrid-column name="aae030" display="生效日期" format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column name="aae031" display="失效日期" format="{0,date,yyyy-MM-dd}" />
			<powersi:datagrid-column name="ama011" display="生育目录" code="yes_no" />
			<powersi:datagrid-column name="aka022" display="医疗目录" code="yes_no" />
			<powersi:datagrid-column name="bkc189" display="儿童目录" code="yes_no" />
			<powersi:datagrid-column name="bkc115" display="居民门诊目录" code="yes_no" />
			<powersi:datagrid-column name="bkc190" display="基本药物目录" code="yes_no" />
			<powersi:datagrid-column name="aka064" display="非处方用药" code="yes_no" />
			<powersi:datagrid-column name="bkc101" display="行政级别" code="bkc101" />
			<powersi:datagrid-column name="bkc102" display="限制范围" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>