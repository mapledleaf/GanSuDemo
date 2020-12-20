<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("akb020", BizHelper.getAkb020());
	request.setAttribute("akb021", BizHelper.getAkb021());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("aaa027Name", BizHelper.getAaa027Name());
%>
<powersi:html>
<head>
<powersi:head title="修改科室信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:hidden id="bkc155" name="hospInfoDto.bkc155" />
				<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
				<powersi:hidden id="aaa027" name="hospInfoDto.aaa027" />
				<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1" />
				<powersi:hidden id="aae016" name="hospInfoDto.aae016" value="0" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button id="btSave" label="保 存" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="aaa027_name" label="统筹区" readonly="true" value="${aaa027Name }"/>
				<powersi:textfield id="akb021" label="医院名称" readonly="true" value="${akb021 }"/>
				<powersi:codeselect id="bke510" name="hospInfoDto.bke510"
					label="科室类别" codeType="stat_type" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc156" name="hospInfoDto.bkc156"
					label="科室编码"  validate="integer,maxSize[10]" />
				<powersi:textfield id="bkc157" name="hospInfoDto.bkc157"
					label="科室名称"  validate="maxSize[40]" />
				<powersi:codeselect id="bke509" name="hospInfoDto.bke509"
					label="是否医技科室" required="true" codeType="cure_flag" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bke505" name="hospInfoDto.bke505" label="床位数"
					validate="integer,maxSize[8]" />
				<powersi:textfield id="bke506" name="hospInfoDto.bke506"
					label="医生或药师人数" validate="integer,maxSize[5]" />
				<powersi:textfield id="bke507" name="hospInfoDto.bke507"
					label="护士人数" validate="integer,maxSize[5]" />
			</powersi:editorlayout-row>
			
			<powersi:codeselect id="bkf01" name="hospInfoDto.bkf01" cssClass="select2"
					label="诊疗科室名称" codeType="bkf01" />

			<powersi:editorlayout-row>
				<powersi:textarea id="bke504" name="hospInfoDto.bke504" label="服务范围"
					cols="2" colspan="5" validate="maxSize[80]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bke508" name="hospInfoDto.bke508" label="科室简介"
					cols="2" colspan="5" validate="maxSize[180]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc028" name="hospInfoDto.bkc028"
					label="经办人工号" required="true" readonly="true" />
				<powersi:textfield id="bkc027" name="hospInfoDto.bkc027" label="经办人"
					required="true" readonly="true" />
				<powersi:textfield id="bkc029" name="hospInfoDto.bkc029"
					label="经办时间" mask="date" required="true" readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="aae013" name="hospInfoDto.aae013" label="备注"
					cols="2" colspan="5" validate="maxSize[400]" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
	var curItemData = "";
	$(document).ready(function(){
		curItemData = $("#mainform").serialize();
	});
	
   function save(){
    	if(!checkFormValidtion())
  			return;
        var saveItemData = $("#mainform").serialize();
        if(saveItemData == curItemData ){
	    	popupAlert("没有修改，无需保存！");
	    	return;
		}
        postJSON("${rootPath }/medicare/HospManageAction!updateHospDept.action",
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

