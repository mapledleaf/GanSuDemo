<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String path = request.getContextPath();
%>
<powersi:html>
<head>
<powersi:head title="新增业务子类" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="4">
			<powersi:editorlayout-button colspan="4">
				<powersi:button id="btSave" label="保 存" onclick="save()" />
				<powersi:button id="btClose" label="取 消"
					onclick="javascript:closeDialog();" />
			</powersi:editorlayout-button>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bkf300" name="kfd1Dto.bkf300" key="bkf300"
					label="业务大类描述" list="#request.bizInfo" required="true" onchange="setBkf301()" />
				<powersi:hidden id="bkf301" name="kfd1Dto.bkf301" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkf302" name="kfd1Dto.bkf302" label="业务子类id"
					required="true" />
				<powersi:textfield id="bkf303" name="kfd1Dto.bkf303" label="业务子类编码"
					required="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bkf304" name="kfd1Dto.bkf304" label="业务子类描述"
					cols="1" colspan="4" validate="maxSize[200]" required="true" />
			</powersi:editorlayout-row>

		</powersi:editorlayout>
	</powersi:form>

	<powersi:errors />

	<script type="text/javascript">
	window.onload = function(){
		 var option = $("#bkf300 option:selected");
	     var bkf301 = option.text();
	     $("#bkf301").val(bkf301);
	}
	
	  function save(){
	    	 //校验必填项
	     	if(!checkFormValidtion())
	     	{
		  		return;
			}
	     	 var option = $("#bkf301 option:selected");
	         var saveItemData = $("#mainform").serialize();
	         postJSON("<%=path%>/actualize/ActualizeManageAction!saveActualizeModel.action",
					saveItemData, saveSuccess);
		}

		function saveSuccess(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			popupAlert(json.data);
			closeDialog();
		}

		function setBkf301() {
			var option = $("#bkf300 option:selected");
			var bkf301 = option.text();
			$("#bkf301").val(bkf301);
		}
	</script>
</body>
</powersi:html>