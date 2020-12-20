<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
	String userName = BizHelper.getUserName();
	String loginUser = BizHelper.getLoginUser();
%>
<powersi:html>
<head>
<powersi:head title="发票作废管理" />
</head>
<body>
	<powersi:form id="cancelForm" namespace="/medicare"
		action="InvoiceManagerAction!cancelInvoice.action">
		<powersi:panelbox key="panel_query" title="发票作废">
			<a>注：本界面只提供未使用发票的作废</a>
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" label="作 废" onclick="saveCancelInfo()"/>
				<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:hidden id="kab1id" name="kab1Dto.kab1id" label="票据ID"/>
					<powersi:hidden id="akb020" name="kab1Dto.akb020" label="akb020"/>
					<powersi:hidden id="bka014" name="kab1Dto.bka014" label="使用人ID"/>
					<powersi:hidden id="bka015" name="kab1Dto.bka015" label="使用人姓名"/>
					<powersi:hidden id="userName" name="kab1Dto.username" label="作废人姓名" 
						value="<%=userName %>"/>
					<powersi:hidden id="loginUser" name="kab1Dto.loginuser" label="作废人姓名工号" 
						value="<%=loginUser %>"/>
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
					<powersi:textfield id="start_number" name="kab1Dto.start_number"
						key="start_number" label="作废起始号码" readonly="true"/>
					<powersi:textfield id="end_number" name="kab1Dto.end_number" value=""
						key="end_number" label="作废终止号码" required="true" 
						validate="number"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="aae013" name="kab1Dto.aae013" colspan="5"
						key="aae013" label="作废说明" required="true"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
	$(function(){
		$("#start_number").val($("#bae413").val());
	});
	function saveCancelInfo() {
		 if(parseInt($("#start_number").val())>parseInt($("#end_number").val())){
			 popupAlert("起始号码不能大于结束号码!");
			 $("#end_number").focus();
			 return;
		 }
		 if(parseInt($("#end_number").val())<parseInt($("#bae411").val()) 
				 || parseInt($("#end_number").val())>parseInt($("#bae412").val())){
			 popupAlert("不能作废你没有的号码!");
			 $("#end_number").focus();
			 return;
		 }
		 
		 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
         var saveItemData = $("#cancelForm").serialize();
         postJSON("<%=path%>/medicare/InvoiceManagerAction!cancelInvoice.action?kab1Dto.bae410="+$("#bae410").val(),
				saveItemData, saveSuccess);
	}
	
	function saveSuccess(json) {
		if (!checkJSONResult(json)) {
			return;
		}
		popupInfo(json.data,3000);
		closeDialog();
	}
	
</script>