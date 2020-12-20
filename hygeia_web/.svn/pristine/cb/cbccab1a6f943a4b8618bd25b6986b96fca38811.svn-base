<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
%>
<powersi:html>
<head>
<powersi:head title="发票退领" />
</head>
<body>
	<powersi:form id="backForm" namespace="/medicare"
		action="InvoiceManagerAction!backInvoice.action">
		<powersi:panelbox key="panel_query" title="发票退领">
		<a>注：退领发票只能退领未使用的全部发票</a>
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" label="退领" onclick="backInvoice()"/>
				<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:hidden id="kab1id" name="kab1Dto.kab1id" label="票据ID"/>
					<powersi:hidden id="bae414" name="kab1Dto.bae414" label="使用标识"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bae410" name="kab1Dto.bae410"
						key="bae410" label="发票类型" codeType="bae410"
					    disabled="true"/>
					<powersi:textfield id="bae415" name="kab1Dto.bae415"
						key="bae415" label="发票前缀" readonly="true"  />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bae411" name="kab1Dto.bae411"
						key="bae411" label="起始号码" readonly="true"  />
					<powersi:textfield id="bae412" name="kab1Dto.bae412"
						key="bae412" label="终止号码" readonly="true"  />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bae413" name="kab1Dto.bae413"
							key="bae413" label="当前号码" readonly="true" />
					<powersi:textfield id="akc226" name="kab1Dto.akc226"
							key="akc226" label="剩余数量" readonly="true"  />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="aae013" name="kab4Dto.aae013"
						key="aae013" label="退领说明" colspan="3"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script>
function backInvoice(){
	if(!checkFormValidtion())
		{
			return;
	}
	if (!confirm("您确认退领票号为"+$("#bae413").val()+"至"+$("#bae412").val()+"的所有发票吗？")) {
        return;
    }
	var saveItemData = $("#backForm").serialize();
	postJSON("<%=path%>/medicare/InvoiceManagerAction!backInvoice.action",
			saveItemData, saveSuccess);
}

function saveSuccess(json) {
	if (!checkJSONResult(json)) {
		return;
	}
	popupInfo(json.data);
	closeDialog();
}

</script>