<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String path = request.getContextPath();
%>
<powersi:html>
<head>
<powersi:head title="发票管理" />
</head>
<body>
	<powersi:form id="invoiceForm" name="invoiceForm" namespace="/medicare"
		action="InvoiceManagerAction!queryInvoicesInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button id="btAdd" label="领 票"  onclick="doAdd()" />
				<powersi:button id="btCancel" label="作 废"  onclick="doCancel()" />
				<powersi:button id="btBack" label="退 领"  onclick="doBack()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bae410" key="票据类型"
						name="kab1Dto.bae410" codeType="bae410" />
					<powersi:codeselect id="bae414" key="使用标识"
						name="kab1Dto.bae414" codeType="bae414" />
					<powersi:textfield id="bka015" name="kab1Dto.bka015" label="领用人姓名"/>
					<powersi:textfield id="bka034" name="kab1Dto.bka034" label="操作人姓名"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="发票领用记录"
		allowCollapse="false">
		<powersi:datagrid id="invoiceid" formId="invoiceForm" delayLoad="false" enabledSort="false"
			showReload="false" showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'发票领用记录'">
			<powersi:datagrid-column name="bae410" key="bae410" label="票据类型" code="bae410" frozen="true"/>
			<powersi:datagrid-column name="bae414" key="bae414" label="使用标识" code="bae414" frozen="true"/>
			<powersi:datagrid-column name="bae413" key="bae413" label="当前号码" frozen="true"/>
			<powersi:datagrid-column name="bae415" key="bae415" label="票据前缀" />
			<powersi:datagrid-column name="bae411" key="bae411" label="起始号码" />
			<powersi:datagrid-column name="bae412" key="bae412" label="终止号码" />
			<powersi:datagrid-column name="akc226" key="akc226" label="剩余数量" />
			<powersi:datagrid-column name="bka015" key="bka015" label="领用人" />
			<powersi:datagrid-column name="bka013" key="bka013" label="领用日期" format="{0,date,yyyy-MM-dd}"/>
			<powersi:datagrid-column name="bka034" key="bka034" label="发票人" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
	function doAdd(){
  		popupDialog({
			url: "<%=path%>/medicare/InvoiceManagerAction!queryPersons.action",
			onClosed: function() {            
				invoiceid.reload(true);
			}
		}, 300, 500);
	}
	
	//发票作废
	function doCancel(){
		var row = invoiceid.getSelectedRow();
		if(powersi.isempty(row)){
			popupAlert("请选择一条发票领用记录！");
			return;
		}
		var bae414 = row['bae414'];
		if(bae414 == "退领" ){
			popupAlert("已退领的发票不能再次作废！");
			return;
		}
		if(bae414 == "用完" ){
			popupAlert("已使用完的发票不能在此作废,请去发票重打/补打界面办理作废处理！");
			return;
		}
		var kab1id = row['kab1id'];
  		popupDialog({
			url: "<%=path%>/medicare/InvoiceManagerAction!queryInvoiceById.action?kab1id="+kab1id+"&bz=cancel",
			onClosed: function() {            
				invoiceid.reload(true);
			}
		}, 300, 500);
	}
	function doBack(){
		var row = invoiceid.getSelectedRow();
		if(powersi.isempty(row)){
			popupAlert("请选择一条发票领用记录！");
			return;
		}
		var bae414 = row['bae414'];
		if(bae414 == "用完" ){
			popupAlert("已用完的发票不能退领！");
			return;
		}
		if( bae414 == "退领"){
			popupAlert("已退领的发票不能再次退领！");
			return;
		}
		
		var kab1id = row['kab1id'];
  		popupDialog({
			url: "<%=path%>/medicare/InvoiceManagerAction!queryInvoiceById.action?kab1id="+kab1id+"&bz=back",
			onClosed: function() {            
				invoiceid.reload(true);
			}
		}, 300, 500);
	}
</script>
