<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String userName = BizHelper.getUserName();
	String loginUser = BizHelper.getLoginUser();
	String akb020 = BizHelper.getAkb020();
%>
<powersi:html>
<head>
<powersi:head title="领取发票" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" disabled="true">
		<powersi:editorlayout cols="4" >
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="4">
					<powersi:button id="btSave" label="领 取" onclick="save()"/>
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="kab1Dto.akb020" value="<%=akb020 %>"/>
				<powersi:hidden id="bka033" name="kab1Dto.bka033" value="<%=loginUser %>"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:codeselect id="bae410" name="kab1Dto.bae410"
					key="bae410" codeType="bae410" label="票据类型" required="true"/>
				<powersi:textfield id="bae415" name="kab1Dto.bae415"
					key="bae415" label="票据前缀"  required="true"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bka034" name="kab1Dto.bka034"
					key="bka034" label="操 作 人" required="true" 
					value="<%=userName %>" readonly="true"/>
				<powersi:codeselect id="bka014" key="bka014" label="领 用 人" 
					name ="kab1Dto.bka014" list="#request.userInfo" required="true" onchange="setBka015()"/>
				<powersi:hidden id="bka015" name="kab1Dto.bka015"/>	
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bae411" name="kab1Dto.bae411"
					key="bae411" label="起始号码" required="true" validate="number"/>
				<powersi:textfield id="bae412" name="kab1Dto.bae412"
					key="bae412" label="终止号码" required="true" validate="number"/>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
window.onload = function(){
	 var option = $("#bka014 option:selected");
     var bka015 = option.text();
     $("#bka015").val(bka015);
}

	function save(){
		 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
		 var bae411 = $("#bae411").val();
		 var bae412 = $("#bae412").val();
		 if(parseInt(bae411)>parseInt(bae412)){
			 popupAlert("起始号码不能大于结束号码;")
			 $("#bae411").focus();
			 return;
		 }
		 var option = $("#bka015 option:selected");
     	 var saveItemData = $("#mainForm").serialize();
         postJSON("${rootPath}/medicare/InvoiceManagerAction!receiptInvoice.action",
					saveItemData, saveSuccess);
		}

		function saveSuccess(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			popupInfo(json.data);
			if(json.data=="领取发票成功！"){
				closeDialog();
			}
		}
		
		function setBka015(){
			 var option = $("#bka014 option:selected");
		     var bka015 = option.text();
		     $("#bka015").val(bka015);
		}
	
</script>
