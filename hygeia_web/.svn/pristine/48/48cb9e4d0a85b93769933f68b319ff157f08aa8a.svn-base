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
	String bae310 = request.getParameter("hospitalAccessScheduleDTO.bae310");
%>

<powersi:html>
<head>
<powersi:head title="新增 医院接入进度信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button id="btSave" label="保 存" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bae310" name="hospitalAccessScheduleDTO.bae310" label="医院接入计划流水号" required="true" readonly="true"
					cols="2" colspan="5" validate="maxSize[400]" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textfield id="akb020" name="hospitalAccessTypeDTO.akb020" label="医院编码" readonly="true" />
				<powersi:textfield id="akb021" name="hospitalAccessTypeDTO.akb021" label="医院名称" readonly="true" />
				<powersi:codeselect id="bae309" name="hospitalAccessScheduleDTO.bae309" label="实施内容" codeType="bae309" required="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="bkc028" name="bkc028" label="经办人工号" required="true" readonly="true" />
				<powersi:textfield id="bae302" name="hospitalAccessScheduleDTO.bae302" label="经办人" required="true" readonly="true" />
				<powersi:textfield id="bae303" name="hospitalAccessScheduleDTO.bae303" label="经办时间" mask="date" required="true" readonly="true" />
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textarea id="bae313" name="hospitalAccessScheduleDTO.bae313" label="备注"
					cols="2" colspan="5" validate="maxSize[400]" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>
	
	<powersi:errors />
	<script type="text/javascript">
  		window.onload = function(){
   			var myDate = new Date();
    		var year = myDate.getFullYear();
    		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
    		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();

   			
   		 	$('#akb020').val("<%=hospital_id%>");
   		 	$('#akb021').val("<%=hospital_name%>");
   		  	$('#bae302').val("<%=userName%>");
          	$('#bkc028').val("<%=loginUser%>");
          	$('#bae303').val(year+"-"+month+"-"+day);
          	
          	if($("#akb020").val() == '' || $("#akb020").val() == null ){
    			popupAlert("医院编码不能为空，请检查登陆信息！");
    	    	return;
    		}
   			$('#bae310').val("<%=bae310%>");
   		}
  		
	    function save(){
	    	 //校验必填项
	     	if(!checkFormValidtion())
	     	{
		  		return;
			}
	         var saveItemData = $("#mainform").serialize();
	         postJSON("<%=path%>/medicare/HospAccessMgrAction!saveAccessSchedule.action",
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
