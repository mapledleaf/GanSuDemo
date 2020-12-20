<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@page import="com.powersi.biz.medicare.yygh.pojo.KE02"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();

	KE02 appointmentInfoDTO = (KE02) request.getAttribute("appointmentInfoDTO");
	String bac003 = appointmentInfoDTO.getBac003();
	String bac002 = appointmentInfoDTO.getBac002();
	String aae005 = appointmentInfoDTO.getAae005();
	String bac004 = appointmentInfoDTO.getBac004();
	String aae006 = appointmentInfoDTO.getAae006();
	String aac003 = appointmentInfoDTO.getAac003();
	String aae030 = DateFunc.dateToString(appointmentInfoDTO.getAae030(), "yyyy-MM-dd");
	String bae031 = appointmentInfoDTO.getBae031();
	String bae032 = appointmentInfoDTO.getBae032();
	String bka503 = appointmentInfoDTO.getBka503();
	String aaz307 = appointmentInfoDTO.getAaz307();
			
%>
<powersi:html>
<head>
<powersi:head title="取消预约挂号" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
<powersi:panelbox key="预约挂号信息">
	<powersi:panelbox-toolbar>
		<powersi:button id="btSaveOrderInfo" label="取消挂号" onclick="saveAppointmentInfo()" />
	</powersi:panelbox-toolbar>
	
	<powersi:editorlayout>
		<powersi:editorlayout-row>
			<powersi:textfield id="sa_bka508" name="appointmentInfoDTO.bka508"  required="true"  label="预约号" readonly="true"/>
			<powersi:textfield id="sa_bac003" name="appointmentInfoDTO.bac003"  required="true"  label="病人姓名" readonly="true"/>
			<powersi:textfield id="sa_bac002" name="appointmentInfoDTO.bac002"  required="true" label="病人身份证" readonly="true"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="sa_aae005" name="appointmentInfoDTO.aae005"  label="病人联系电话" readonly="true"/>
			<powersi:textfield id="sa_bac004_name" name="bac004_name" label="病人性别" readonly="true"/>
			<powersi:textfield id="sa_aae006" name="appointmentInfoDTO.aae006"  label="病人联系地址" readonly="true"/>
			<powersi:hidden id="sa_bac004" name="appointmentInfoDTO.bac004" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="sa_aac003" name="appointmentInfoDTO.aac003" label="医院医师" required="true" readonly="true"/>
			<powersi:textfield id="sa_bka503" name="appointmentInfoDTO.bka503" label="医生编码" required="true" readonly="true"/>
			<powersi:textfield id="sa_aae030" name="appointmentInfoDTO.aae030"  required="true"  label="就诊日期" readonly="true"/>
			<powersi:hidden id="sa_aaz307" name="appointmentInfoDTO.aaz307" label="科室编码"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="sa_bae031" name="appointmentInfoDTO.bae031"  required="true"  label="就诊班次" readonly="true"/>
			<powersi:textfield id="sa_bae032" name="appointmentInfoDTO.bae032"  required="true"  label="就诊时间段" readonly="true"/>
			<powersi:textarea id="sa_aae013" name="appointmentInfoDTO.aae013"  label="说明"/>
		</powersi:editorlayout-row>
	</powersi:editorlayout>
</powersi:panelbox>
</powersi:form>
</body>
<script type="text/javascript">
$(document).ready(function(){
    $('#sa_bac003').val("<%=bac003%>");
    $('#sa_bac002').val("<%=bac002%>");
    $('#sa_aae005').val("<%=aae005%>");
    $('#sa_bac004').val("<%=bac004%>");
    $('#sa_aae006').val("<%=aae006%>");
    $('#sa_aac003').val("<%=aac003%>");
    $('#sa_aaz307').val("<%=aaz307%>");
    $('#sa_bka503').val("<%=bka503%>");
    $('#sa_aae030').val("<%=aae030%>");
    $('#sa_bae031').val("<%=bae031%>");
    $('#sa_bae032').val("<%=bae032%>");
   
    var bac004 = $("#sa_bac004").val();
    if(bac004=="1"){
    	$("#sa_bac004_name").val("男");
    }else if(bac004=="2"){
    	$("#sa_bac004_name").val("女");
    }else{
    	$("#sa_bac004_name").val("未知");
    }
    
});

/*上传信息*/
function saveAppointmentInfo() {
	 //校验必填项
   	if(!checkFormValidtion())
   	{
 		return;
	}
    var saveItemData = $("#mainform").serialize();
    
    if (!confirm("您确认取消该条预约挂号吗?")) {
		return;
	}
    postJSON("<%=path%>/inhospital/AppointmentManagerAction!saveAppointmentInfo.action",
					saveItemData, saveSuccess);
}

function saveSuccess(json) {
	if (!checkJSONResult(json)) {
		return;
	}
	closeDialog();
}
	
</script>
</powersi:html>
