<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("userName", BizHelper.getUserName());
%>
<powersi:html>
<head>
	<powersi:head title="修改项目目录" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button cssClass="button" label="保 存" buttonIcon="icon-ok" onclick="save()"/>
					<powersi:button cssClass="button" label="取 消" buttonIcon="icon-repeat" onclick="javascript:closeDialog();"/>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="ake005" name="hospCataDto.ake005" label="医院项目编码" required="true" readonly="true"/>
				<powersi:textfield id="ake006" name="hospCataDto.ake006" label="医院项目名称" required="true" validate = "maxSize[100]"/>
				<powersi:codeselect id="ake003" name="hospCataDto.ake003" label="目录类别"  required="true" key="ake003" codeType="ake003" 
					headerKey="-1" codeFilter="data_value in ('2','3') " />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc140" name="hospCataDto.bkc140" label="单价" required="true" validate="money"/>
				<powersi:textfield id="bkm021" name="hospCataDto.bkm021" label="别名" validate = "maxSize[50]"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkm006" name="hospCataDto.bkc139" label="规格型号"  validate = "maxSize[300]"/>
				<powersi:textfield id="aka062" name="hospCataDto.aka062" label="英文名称" validate = "maxSize[300]"/>
				<powersi:textfield id="bkm022" name="hospCataDto.bkm022" label="商品名" validate = "maxSize[50]"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc141" name="hospCataDto.bkc141" label="生产单位" validate = "maxSize[200]"/>
				<powersi:textfield id="bkm019" name="hospCataDto.bkm019" label="产地" validate = "maxSize[100]"/>
				<powersi:textfield id="bkm025" name="hospCataDto.bkm025" label="备注" colspan="3" validate = "maxSize[100]"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc002" name="hospCataDto.bkc002" label="经办人" required="true" readonly="true" />
				<powersi:textfield id="bkc003" name="hospCataDto.bkc003" label="经办人工号" required="true" readonly="true" />
				<powersi:textfield id="aae036" name="hospCataDto.aae036" label="经办时间" required="true" readonly="true" mask="date" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="hospCataDto.akb020" />
				<powersi:hidden id="aae100" name="hospCataDto.aae100" />
				<powersi:hidden id="ka40id" name="hospCataDto.ka40id" />
				<powersi:hidden id="aae016" name="hospCataDto.aae016" />
				<powersi:hidden id="caa027" name="hospCataDto.caa027" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
</body>
<script type="text/javascript">
	var curItemData = "";
	window.onload = function(){
	 	curItemData = $("#mainform").serialize();
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#bkc002').val("${userName}");
		$('#bkc003').val("${loginUser}");
		$('#aae036').val(year + "-" + month + "-" + day);
	}
	
	function save() {
		if (!checkFormValidtion()) 
			return;
		if(!valid())
	   		return;
		var saveItemData = $("#mainform").serialize();
		if (saveItemData == curItemData) {
			alert("没有修改，无需保存！");
			return;
		}
		postJSON("${rootPath}/medicare/HospCataAction!updateHospCata.action", saveItemData, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			if (json.errortype > 0) {
				popupAlert(ret.message, "提示", "error");
			} else {
				popupAlert("保存成功", "提示", "info", function() {
					closeDialog();
				});
			}
		});
	}
	    
	function valid(){
		var strValid=/^[0-9]{1,8}(($)|([.][0-9]{1,4}))$/;
		var bkc140 = $("#bkc140").val();
		if(!strValid.test(bkc140)){
			popupInfo("单价的格式有误！[范围：0-99999999.9999]");
			return false;
		}
		return true;
	}
</script>

</powersi:html>
