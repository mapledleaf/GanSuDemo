<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@page import="com.powersi.biz.medicare.yygh.pojo.KE02"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();

	String bac002 = request.getParameter("appointmentInfoDTO.bac002");
	String aae005 = request.getParameter("appointmentInfoDTO.aae005");
	String bac004 = request.getParameter("appointmentInfoDTO.bac004");
	String aae030 = request.getParameter("appointmentInfoDTO.aae030");
	String bae032 = request.getParameter("appointmentInfoDTO.bae032");
	String bka508 = request.getParameter("appointmentInfoDTO.bka508");
	String bka503 = request.getParameter("appointmentInfoDTO.bka503");
	String aaz307 = request.getParameter("appointmentInfoDTO.aaz307");
	String bae029 = request.getParameter("appointmentInfoDTO.bae029");
	
	String bac003 = URLDecoder.decode(request.getParameter("appointmentInfoDTO.bac003"), "UTF-8");//解决中文乱码
	String aae006 = URLDecoder.decode(request.getParameter("appointmentInfoDTO.aae006"), "UTF-8");//解决中文乱码
	String aac003 = URLDecoder.decode(request.getParameter("appointmentInfoDTO.aac003"), "UTF-8");//解决中文乱码
	String bae031 = URLDecoder.decode(request.getParameter("appointmentInfoDTO.bae031"), "UTF-8");//解决中文乱码
			
%>
<powersi:html>
<head>
<powersi:head title="预约挂号停诊异动" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
<powersi:panelbox key="预约挂号信息">
	<powersi:panelbox-toolbar>
		<powersi:button id="btSaveOrderInfo" label="提交" onclick="saveAppointmentExcepInfos()" />
	</powersi:panelbox-toolbar>
	
	<powersi:editorlayout>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_bae029_name" name="e_bae029_name"  label="异动类型" disabled="true"/>
			<powersi:hidden id="e_bae029" name="appointmentInfoDTO.bae029"  label="异动类型"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_bka508" name="appointmentInfoDTO.bka508"  required="true"  label="预约号" readonly="true"/>
			<powersi:textfield id="e_bac003" name="appointmentInfoDTO.bac003"  required="true"  label="病人姓名" readonly="true"/>
			<powersi:textfield id="e_bac002" name="appointmentInfoDTO.bac002"  required="true" label="病人身份证" readonly="true"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_aae005" name="appointmentInfoDTO.aae005"  label="病人联系电话" readonly="true"/>
			<powersi:textfield id="e_bac004_name" name="bac004_name" label="病人性别" readonly="true"/>
			<powersi:textfield id="e_aae006" name="appointmentInfoDTO.aae006"  label="病人联系地址" readonly="true"/>
			<powersi:hidden id="e_bac004" name="appointmentInfoDTO.bac004" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_aac003" name="appointmentInfoDTO.aac003" label="医院医师" required="true" readonly="true"/>
			<powersi:textfield id="e_bka503" name="appointmentInfoDTO.bka503" label="医生编码" required="true" readonly="true"/>
			<powersi:textfield id="e_aae030" name="appointmentInfoDTO.aae030"  required="true"  label="就诊日期" readonly="true"/>
			<powersi:hidden id="e_aaz307" name="appointmentInfoDTO.aaz307" label="科室编码"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_bae031" name="appointmentInfoDTO.bae031"  required="true"  label="就诊班次" readonly="true"/>
			<powersi:textfield id="e_bae032" name="appointmentInfoDTO.bae032"  required="true"  label="就诊时间段" readonly="true"/>
			<powersi:textarea id="e_bae013" name="appointmentInfoDTO.bae013"  label="停诊异动说明"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_aae034" name="appointmentInfoDTO.aae034"  label="改诊后就诊日期" mask="date" format="dateFmt:'yyyy-MM-dd'"/>
			<powersi:textfield id="e_bae035" name="appointmentInfoDTO.bae035"  label="改诊后预约班次"/>
			<powersi:textarea id="e_bae036" name="appointmentInfoDTO.bae036"  label="改诊后就诊时间"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textarea id="e_bka509" name="appointmentInfoDTO.bka509"  label="改诊后预约号"/>
		</powersi:editorlayout-row>
	</powersi:editorlayout>
</powersi:panelbox>
</powersi:form>
</body>
<script type="text/javascript">
$(document).ready(function(){
    $('#e_bac003').val("<%=bac003%>");
    $('#e_bac002').val("<%=bac002%>");
    $('#e_aae005').val("<%=aae005%>");
    $('#e_bac004').val("<%=bac004%>");
    $('#e_aae006').val("<%=aae006%>");
    $('#e_aac003').val("<%=aac003%>");
    $('#e_aaz307').val("<%=aaz307%>");
    $('#e_bka503').val("<%=bka503%>");
    $('#e_aae030').val("<%=aae030%>");
    $('#e_bae031').val("<%=bae031%>");
    $('#e_bae032').val("<%=bae032%>");
    $('#e_bka508').val("<%=bka508%>");
    $('#e_bae029').val("<%=bae029%>");
   
    var bac004 = $("#e_bac004").val();
    if(bac004=="1"){
    	$("#e_bac004_name").val("男");
    }else if(bac004=="2"){
    	$("#e_bac004_name").val("女");
    }else{
    	$("#e_bac004_name").val("未知");
    }
    var bae029 = $("#e_bae029").val();
    if(bae029==1){
    	$("#e_bae029_name").val("停诊");
    }else if(bae029==2){
    	$("#e_bae029_name").val("改诊");
    }
    
});

/*上传信息*/
function saveAppointmentExcepInfos() {
	 //校验必填项
   	if(!checkFormValidtion())
   	{
 		return;
	}
	var bae029 = $("#e_bae029").val();
	if(bae029 == '2'){
		var aae034= $("#e_aae034").val();
		var bae035= $("#e_bae035").val();
		var bae036= $("#e_bae036").val();
		var bka509= $("#e_bka509").val();
		
		if(aae034=='' || aae034==null){
			popupAlert("改诊请填写,改诊后的就诊日期");
			return;
		}
		if(bae035=='' || bae035==null){
			popupAlert("改诊请填写,改诊后的预约班次");
			return;
		}
		if(bae036=='' || bae036==null){
			popupAlert("改诊请填写,改诊后的就诊时间");
			return;
		}
		if(bka509=='' || bka509==null){
			popupAlert("改诊请填写,改诊后的预约号");
			return;
		}
		
	}
    var saveItemData = $("#mainform").serialize();
    if (!confirm("您确认通知该条预约挂号停诊异动吗?")) {
		return;
	}
    postJSON("<%=path%>/inhospital/AppointmentManagerAction!saveAppointmentExcepInfos.action",
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
