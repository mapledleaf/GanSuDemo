<%@page import="java.util.Date"%>
<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("akb020", BizHelper.getAkb020());
	request.setAttribute("akb021", BizHelper.getAkb021());
	request.setAttribute("aaa027", BizHelper.getAaa027());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("aaa027Name", BizHelper.getAaa027Name());
	request.setAttribute("aae016", request.getParameter("aae016"));
	request.setAttribute("bkc029", DateFunc.dateToString(new Date(), "yyyy-MM-dd"));

%>
<powersi:html>
<head>
<powersi:head title="新增 科室信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="hospInfoDto.akb020" value="${akb020 }"/>
				<powersi:hidden id="aaa027" name="hospInfoDto.aaa027" value="${aaa027 }"/>
				<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1" />
				<powersi:hidden id="aae016" name="hospInfoDto.aae016" value="${aae016 }"/>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="8">
					<powersi:button id="btSave" label="保 存" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="aaa027_name" label="统筹区" readonly="true" value="${aaa027Name }"/>
				<powersi:textfield id="akb021" name="hospInfoDto.akb021" label="医院名称" 
					readonly="true" value="${akb021 }"/>
				<powersi:codeselect id="bke510" name="hospInfoDto.bke510" label="科室类别" 
					codeType="stat_type" required="true"/>
				<powersi:textfield id="bkc156" name="hospInfoDto.bkc156" label="科室编码" 
					required="true" validate="maxSize[10]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc157" name="hospInfoDto.bkc157"  label="科室名称" 
					required="true" validate="maxSize[40]" />
				<powersi:codeselect id="bke509" name="hospInfoDto.bke509" label="是否医技科室" 
					required="true" codeType="cure_flag" />
				<powersi:textfield id="bke505" name="hospInfoDto.bke505"  label="床位数"
					validate="integer,maxSize[8]" />
				<powersi:textfield id="bke506" name="hospInfoDto.bke506"  label="医生或药师人数" 
					validate="integer,maxSize[5]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bke507" name="hospInfoDto.bke507" label="护士人数" 
					validate="integer,maxSize[5]" />
				<powersi:codeselect id="bkf01" name="hospInfoDto.bkf01" label="诊疗科室名称" 
					codeType="bkf01" cssClass="select2"/>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bke504" name="hospInfoDto.bke504" label="服务范围"
					cols="2" colspan="8" validate="maxSize[80]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bke508" name="hospInfoDto.bke508" label="科室简介"
					cols="2" colspan="8" validate="maxSize[180]" />
			</powersi:editorlayout-row>
            
            <powersi:editorlayout-row>
				<powersi:textarea id="aae013" name="hospInfoDto.aae013" label="备注"
					cols="2" colspan="8" validate="maxSize[400]" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc028" name="hospInfoDto.bkc028" label="经办人工号" 
					readonly="true" value="${loginUser }"/>
				<powersi:textfield id="bkc027" name="hospInfoDto.bkc027" label="经办人"
					readonly="true" value="${userName }"/>
				<powersi:textfield id="bkc029" name="hospInfoDto.bkc029" label="经办时间" 
					readonly="true" value="${bkc029 }"/>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
    function save(){
     	if (!checkFormValidtion())
	  		return;
     	if (!valid())
     		return;
        var saveItemData = $("#mainform").serialize();
        postJSON("${rootPath}/medicare/HospManageAction!saveHospDept.action",
				saveItemData, saveSuccess);
	}

	function saveSuccess(json) {
		if (!checkJSONResult(json)) {
			return;
		}
		popupInfo(json.data);
		closeDialog();
	}
	
	function valid(){
		var bke505 = $('#bke505').val();
		var bke506 = $('#bke506').val();
		var bke507 = $('#bke507').val();
		if(bke505<0){
			popupAlert("床位数不能小于0!");
			return false;
		}
		if(bke506<0){
			popupAlert("医生或药师人数不能小于0！");
			return false;
		}
		if(bke507<0){
			popupAlert("护士人数不能小于0！");
			return false;
		}
		return true;
	}
</script>

