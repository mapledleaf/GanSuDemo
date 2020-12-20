<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="修改病床信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="4">
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
				<powersi:hidden id="aae100" name="hospInfoDto.aae100" />
				<powersi:hidden id="aae016" name="hospInfoDto.aae016" />
				<powersi:hidden id="bkc153" name="hospInfoDto.bkc153" />
				<powersi:hidden id="bkc156" name="hospInfoDto.bkc156" />
				<powersi:hidden id="kac9id" name="hospInfoDto.kac9id" />
				<powersi:hidden id="bkc028" name="hospInfoDto.bkc028" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="4">
					<powersi:button id="btSave" label="保 存" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>


			<powersi:editorlayout-row>
				<powersi:textfield id="akb021" label="医院名称" readonly="true" />
				<powersi:textfield id="bkc154" name="hospInfoDto.bkc154"
					label="病区名称" required="true" readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc161" name="hospInfoDto.bkc161"
					label="病床编号" required="true" readonly="true"
					validate="integer,maxSize[10]" />
				<powersi:textfield id="bkc162" name="hospInfoDto.bkc162"
					label="病床房间" required="true" validate="integer,maxSize[20]" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc163" name="hospInfoDto.bkc163"
					label="床位类型" required="true" codeType="bkc163" />
				<powersi:textfield id="bkc164" name="hospInfoDto.bkc164"
					label="床位单价" validate="number" required="true" />

			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:codeselect id="bkc171" name="hospInfoDto.bkc171"
						codeType="bkc171" label="占用标志" required="true"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkc027" name="hospInfoDto.bkc027" label="经办人"
					required="true" readonly="true" />
				<powersi:textfield id="bkc029" name="hospInfoDto.bkc029"
					label="经办时间" mask="date" required="true" readonly="true" />
			</powersi:editorlayout-row>

		</powersi:editorlayout>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">

			var curItemData = "";
  	     $(document).ready(function(){
  	         curItemData = $("#mainform").serialize();
  	         
  	       $('#akb021').val("<%=hospital_name%>");
         	if($("#akb020").val() == '' || $("#akb020").val() == null ){
	   			popupAlert("医院编码不能为空，请检查登陆信息！");
	   	    	return;
   			}
  	     });

    function save(){
    	 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
         var saveItemData = $("#mainform").serialize();
         //把页面的值跟初始化页面值进行比较
         if(saveItemData == curItemData ){
		    popupAlert("没有修改，无需保存！");
		    return;
		}
         postJSON("<%=path%>/medicare/HospManageAction!updateHospBed.action",
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
</body>
</powersi:html>
