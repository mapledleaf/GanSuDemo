<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String path = request.getContextPath();
%>
<powersi:html>
<head>
<powersi:head title="发票使用查询" />
</head>
<body>
	<powersi:form id="invoiceForm" name="invoiceForm" namespace="/medicare"
		action="InvoiceManagerAction!queryUseInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bae410" name="kab2Dto.bae410"
						key="bae410" label="票据类型" codeType="bae410"/>
					<powersi:codeselect id="bae416" name="kab2Dto.bae416"
						key="bae416" label="使用类型" codeType="bae416"/>
					<powersi:textfield id="bae413" name="kab2Dto.bae413"
						key="bae413" label="发票号码" />
					<powersi:textfield id="bka015" name="kab2Dto.bka015"
						key="bka015" label="使用人"  />	
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="发票使用记录"
		allowCollapse="false">
		<powersi:datagrid id="invoiceid" formId="invoiceForm" delayLoad="false" enabledSort="false"
			showReload="false" showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'发票使用记录'" autoWidth="true">
			<powersi:datagrid-column name="bae410" key="bae410" label="票据类型" code="bae410"/>
			<powersi:datagrid-column name="bae416" key="bae416" label="使用类型" code="bae416"/>
			<powersi:datagrid-column name="bae413" key="bae413" label="发票号码" />
			<powersi:datagrid-column name="bka015" key="bka015" label="使用人" />
			<powersi:datagrid-column name="aae012" key="aae012" label="作废人" />
			<powersi:datagrid-column name="bka065" key="bka065" label="使用日期" format="{0,date,yyyy-MM-dd}"/>
			<powersi:datagrid-column name="aae013" key="aae013" label="备注说明" width="300" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>

