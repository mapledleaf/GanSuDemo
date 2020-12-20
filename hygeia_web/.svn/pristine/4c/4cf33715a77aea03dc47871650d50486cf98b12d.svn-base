<%@ page import="java.util.Date"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute("hospital_id", BizHelper.getAkb020());
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("caa027", request.getParameter("caa027"));
	request.setAttribute("aae036", DateFunc.dateToString(new Date(), "yyyy-MM-dd"));
%>
<powersi:html>
<head>
<powersi:head title="新增疾病目录" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="8">
					<powersi:button cssClass="button" label="保 存" onclick="save()"/>
					<powersi:button cssClass="button" label="取 消" onclick="javascript:closeDialog();"/>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aka420" name="diseaseDto.aka420" label="医院疾病编码" required="true" validate = "maxSize[20]"/>
				<powersi:textfield id="aka421" name="diseaseDto.aka421" label="医院疾病名称" required="true" validate = "maxSize[100]"/>
				<powersi:textfield id="aka122" name="diseaseDto.aka122" label="疾病分类"/>
				<powersi:textfield id="aka062" name="diseaseDto.aka062" label="英文化学名"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aae013" name="diseaseDto.aae013" label="备注" colspan="5" validate = "maxSize[100]"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc002" name="diseaseDto.bkc002" label="经办人" required="true" readonly="true" />
				<powersi:textfield id="bkc003" name="diseaseDto.bkc003" label="经办人工号" required="true" readonly="true" />
				<powersi:textfield id="aae036" name="diseaseDto.aae036" label="经办时间" required="true" readonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="diseaseDto.akb020" />
				<powersi:hidden id="aae100" name="diseaseDto.aae100" value="1" />
				<powersi:hidden id="caa027" name="diseaseDto.caa027"/>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
</body>
<script type="text/javascript">
	window.onload = function(){
		$('#akb020').val("${hospital_id}");
		$('#bkc002').val("${userName}");
		$('#bkc003').val("${loginUser}");
		$('#caa027').val("${caa027}");
		$('#aae036').val("${aae036}");
	}

	function save() {
		if(!checkFormValidtion()) 
	  		return;
		var saveItemData = $("#mainform").serialize();
		postJSON("${rootPath}/medicare/HospCataAction!saveHospDisease.action",saveItemData, function(json) {
   				if (!checkJSONResult(json)) {
   					return;
   				}
   				popupInfo(json.data);
   				if(json.data == '保存目录信息成功！'){
   					closeDialog();
   				}
   			});
	}
</script>
</powersi:html>
