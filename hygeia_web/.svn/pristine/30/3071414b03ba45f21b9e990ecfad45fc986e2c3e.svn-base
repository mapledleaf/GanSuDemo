<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<head>
<powersi:head title="医师异动申请" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="14%,11%,14%,11%,14%,11%,14%,11%" >
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="8">
					<powersi:button id="btSave" label="保 存" onclick="saveChangApply()" />
					<powersi:button id="btClose" label="取 消" onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aac003" name="hospInfoDto.aac003" label="姓名" 
					readonly="true" />
				<powersi:codeselect id="bkc277" name="hospInfoDto.bkc277" label="性别" 
					codeType="aac004" displayonly="true"/>
				<powersi:textfield id="bkc279" name="hospInfoDto.bkc279" label="身份证号" 
					readonly="true" />
				<powersi:textfield id="bkc269" name="hospInfoDto.bkc269" label="医生档案Id"
					 readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkm048" name="hospInfoDto.bkm048" label="原医疗机构ID" 
					readonly="true" />
				<powersi:textfield id="bkm049" name="hospInfoDto.bkm049" label="原医疗机构名称" 
					readonly="true" />
				<powersi:textfield id="bkm070" name="hospInfoDto.bkm070" label="转入医疗机构ID" 
					required="true" />
				<powersi:textfield id="bkm071" name="hospInfoDto.bkm071" label="转入医疗机构名称"
					required="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkm027" name="hospInfoDto.bkm027" label="经办人工号" 
					readonly="true" />
				<powersi:textfield id="bkm028" name="hospInfoDto.bkm028" label="经办人"
				 	readonly="true" />
				<powersi:textfield id="bkm026" name="hospInfoDto.bkm026" label="经办时间" 
					readonly="true" />
			</powersi:editorlayout-row>
			<powersi:hidden id="bkc274" name="hospInfoDto.bkc274" label="医生编号" />
		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
$(function(){
	$("#bkm026").val("<%=DateFunc.dateToString(new Date(), "yyyy-MM-dd") %>");
	$("#bkm027").val("<%=BizHelper.getLoginUser() %>");
	$("#bkm028").val("<%=BizHelper.getUserName() %>");
	$("#bkm048").val("<%=BizHelper.getAkb020() %>");
	$("#bkm049").val("<%=BizHelper.getAkb021() %>");
})

function saveChangApply(){
 	if(!checkFormValidtion())
  		return;
    var saveItemData = $("#mainform").serialize();
    postJSON("${rootPath}/medicare/HospManageAction!saveChangApply.action",
			saveItemData, function(json){
		    	if (!checkJSONResult(json)) 
					return;
		    	alert(json.data);
				closeDialog();
    		});
}
</script>
