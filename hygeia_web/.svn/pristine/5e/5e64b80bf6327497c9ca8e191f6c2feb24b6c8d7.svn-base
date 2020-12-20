<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@page import="com.powersi.biz.medicare.yygh.pojo.KE01"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="java.util.Date"%>
<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	KE01 doctorSourceInfoDTO = (KE01)request.getAttribute("doctorSourceInfoDTO");
	String aaz307 = doctorSourceInfoDTO.getAaz307();
	String aaz386 = doctorSourceInfoDTO.getAaz386();
	Date bae030D = doctorSourceInfoDTO.getAae030();
	String aae030 = DateFunc.dateToString(bae030D, "yyyy-MM-dd");
	String bae031 = doctorSourceInfoDTO.getBae031();
	String aac003 = doctorSourceInfoDTO.getAac003();
	String bka503 = doctorSourceInfoDTO.getBka503();
			
%>
<powersi:html>
<head>
<powersi:head title="提交预约挂号" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
	<powersi:panelbox key="挂号号源信息">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button id="btSave" label="提 交" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textfield id="akb020" name="appointmentInfoDTO.aka020" label="医院编码" readonly="true" required="true"/>
				<powersi:textfield id="aaz386" name="appointmentInfoDTO.aaz386" label="医院科室" readonly="true" required="true"/>
				<powersi:textfield id="aac003" name="appointmentInfoDTO.aac003" label="医生姓名" readonly="true" required="true"/>
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:textfield id="aae030" name="appointmentInfoDTO.aae030" label="接诊日期" readonly="true" format="dateFmt:'yyyy-MM-dd'" required="true"/>
				<powersi:textfield id="bae031" name="appointmentInfoDTO.bae031" label="接诊班次" readonly="true" required="true"/>
				<powersi:codeselect id="bka030_s" name="bka030_s" label="接诊时间段" cssClass="select2" list="#request.bka030List"  required="true"/>
			</powersi:editorlayout-row>
			
			<powersi:editorlayout-row>
				<powersi:hidden id="aaz307" name="appointmentInfoDTO.aaz307"/>
				<powersi:hidden id="bae032" name="appointmentInfoDTO.bae032"/>
				<powersi:hidden id="bka503" name="appointmentInfoDTO.bka503"/>
				<powersi:hidden id="bka504" name="appointmentInfoDTO.bka504" value="1"/>
				<powersi:hidden id="bka508" name="appointmentInfoDTO.bka508" value=""/>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox key="病人信息">
		<powersi:editorlayout>
		<powersi:editorlayout-row>
			<powersi:textfield id="bac003" name="appointmentInfoDTO.bac003"  key="bac003" required="true" label="病人姓名"/>
			<powersi:textfield id="bac002" name="appointmentInfoDTO.bac002"  key="bac002" required="true" label="病人身份证"/>
			<powersi:textfield id="aae005" name="appointmentInfoDTO.aae005"  key="aae005" required="true" label="病人联系电话"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:codeselect codeType="aac004" id="a_bac004" name="appointmentInfoDTO.bac004" key="病人性别"></powersi:codeselect>
			<powersi:textfield id="aae006" name="appointmentInfoDTO.aae006"  key="aa006"  label="病人联系地址"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textarea id="aae013" name="appointmentInfoDTO.aae013" label="说明"
					cols="2" colspan="5" validate="maxSize[400]" />
		</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	</powersi:form>

	<powersi:errors />
	<script type="text/javascript">
    $(document).ready(function(){
	    $('#akb020').val("<%=hospital_id%>");
	    $('#aaz307').val("<%=aaz307%>");
	    $('#aaz386').val("<%=aaz386%>");
	    $('#aac003').val("<%=aac003%>");
	    $('#aae030').val("<%=aae030%>");
	    $('#bae031').val("<%=bae031%>");
	    $('#bka503').val("<%=bka503%>");
    });
    
    function save(){
    	var bka030Obj = document.getElementById("bka030_s"); 
		var index =bka030Obj.selectedIndex;
		var bka030Text = bka030Obj.options[index].text;
		var bka030Value = bka030Obj.options[index].value;
		
		$("#bae032").val(bka030Text);
    	
    	 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
         var saveItemData = $("#mainform").serialize();
         
         if (!confirm("您确认提交该条预约挂号吗?")) {
     		return;
     	}
         
         postJSON("<%=path%>/inhospital/AppointmentManagerAction!submitAppointment.action",
					saveItemData, saveSuccess);
		}

		function saveSuccess(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			//alert(json.data);
			closeDialog();
		}
	</script>
</body>
</powersi:html>
