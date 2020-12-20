<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String aaa027_name = BizHelper.getAaa027Name();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>

<powersi:html>
<head>
<powersi:head title="新增 病区信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="hospInfoDto.akb020" />
				<powersi:hidden id="aaa027" name="hospInfoDto.aaa027" />
				<powersi:hidden id="aae100" name="hospInfoDto.aae100" value="1" />
				<powersi:hidden id="aae016" name="hospInfoDto.aae016" />
				<powersi:hidden id="bkc156" name="hospInfoDto.bkc156" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button id="btSave" label="保 存" onclick="save()" buttonIcon="icon-save"/>
					<powersi:button id="btClose" label="取 消" buttonIcon="icon-remove"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="aaa027_name" label="统筹区" readonly="true" />
				<powersi:textfield id="akb021" label="医院名称" readonly="true"
					colspan="3" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc157" name="hospInfoDto.bkc157"
					label="科室名称" readonly="true" required="true" />
				<powersi:textfield id="bkc153" name="hospInfoDto.bkc153"
					label="病区编码" required="true" validate="maxSize[8]" />
				<powersi:textfield id="bkc154" name="hospInfoDto.bkc154"
					label="病区名称" required="true" validate="maxSize[40]" />
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
	<script type="text/javascript">
  		window.onload = function(){
  			var date = new Date();
   			this.year = date.getFullYear();
   			this.month = date.getMonth() + 1;
   			this.date = date.getDate();
   			this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
   			this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
   			this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
   			//2018-10-11 00:00:00
   			var currentTime = this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;
   			$('#bkc029').val(currentTime);
   			
   		 	$('#akb020').val("<%=hospital_id%>");
   		 	$('#akb021').val("<%=hospital_name%>");
   		 	$('#aaa027').val("<%=aaa027%>");
			$('#aaa027_name').val("<%=aaa027_name%>");
   		  	$('#bkc027').val("<%=userName%>");
          	$('#bkc028').val("<%=loginUser%>");
          	
          	if($("#akb020").val() == '' || $("#akb020").val() == null ){
    			alert("医院编码不能为空，请检查登陆信息！");
    	    	return;
    		}
   		
   		}
		
	    function save(){
	    	 //校验必填项
	     	if(!checkFormValidtion())
	     	{
		  		return;
			}
	         var saveItemData = $("#mainform").serialize();
	         postJSON("<%=path%>/medicare/HospManageAction!saveHospArea.action",
					saveItemData, saveSuccess);
		}

		function saveSuccess(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			alert(json.data);
			closeDialog();
		}
	</script>
</body>
</powersi:html>
