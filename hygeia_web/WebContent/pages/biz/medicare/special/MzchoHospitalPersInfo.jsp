<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
		String aaa027=BizHelper.getAaa027().substring(0,4);
%>
<powersi:html>
	<head>
		<powersi:head title="门诊选点人员信息" target="_self"/>
	</head>
	<body>
		<powersi:form id="detailForm" disabled="true">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield  id="aac003"  name="mediSpecDto.aac003"  label="姓名" readonly="true"/>
					 <powersi:textfield id="aac004" name="mediSpecDto.aac004"   label="性别"  readonly="true"/>
					<powersi:textfield  id="aac001"  name="mediSpecDto.aac001"  label="个人电脑号" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield  id="aac006"  name="mediSpecDto.aac006"  label="出生日期"  readonly="true" />
					<powersi:textfield  id="aac002"  name="mediSpecDto.aac002"  label="身份证号"  readonly="true" />
				    <powersi:textfield  id="aka083" name="mediSpecDto.aka083"  label="待遇类型" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield  id="aaz267"  name="mediSpecDto.aaz267"  label="序列号" readonly="true" />
					<powersi:textfield  id="bka004" name="mediSpecDto.bka004" label="人员类别" readonly="true" />
					<powersi:textfield  id="bmc016" name="mediSpecDto.bmc016" label="预产期" mask="date" disabled="true"
					readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield  id="akb021" name="mediSpecDto.akb021" label="申请医院" readonly="true" colspan="5"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				     <powersi:textfield id="aae016" name="mediSpecDto.aae016" label="审核标志" readonly="true"/>
					<powersi:textfield  id="bkb102" name="mediSpecDto.bkb102"  label="初审人"  readonly="true"/>
					<powersi:textfield  id="bkb101" name="mediSpecDto.bkb101" label="初审时间" readonly="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield  id="aae014" name="mediSpecDto.aae014"  label="复审人"  readonly="true"/>
					<powersi:textfield  id="aae015" name="mediSpecDto.aae015" label="复审时间" readonly="true"/>
					<powersi:textfield  id="bkb103" name="mediSpecDto.bkb103" label="审核意见" readonly="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030" name="mediSpecDto.aae030"  label="开始时间" format="dateFmt:'yyyy-MM-dd'" readonly="true"/>
					<powersi:textfield id="aae031" name="mediSpecDto.aae031"  label="结束时间"  readonly="true"/>
					<powersi:textfield id="aae127" name="mediSpecDto.aae127"  label="申请时间"  readonly="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield  id="aae013" name="mediSpecDto.aae013" label="备注" readonly="true" colspan="5"/>
					<powersi:hidden id="akb020" name="mediSpecDto.akb020"  label="医院编码"  readonly="true"/>
					<powersi:hidden id="bka006" name="mediSpecDto.bka006"  label="待遇类型"  readonly="true"/>
					<powersi:hidden id="aka130" name="mediSpecDto.aka130" key="aka130" value="11" />
					<powersi:hidden id="bkb100" name="mediSpecDto.bkb100"  label="申请方式"  readonly="true"/>
					<powersi:hidden id="aae011" name="mediSpecDto.aae011"  label="录入人"  readonly="true"/>
					<powersi:hidden id="aka084" name="mediSpecDto.aka084" label="特殊业务标识"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<powersi:editorlayout cols="20%,80%">
				<powersi:editorlayout-row>
					<powersi:checkbox key="两病三师签约" name="aaz308" id="aaz308"
						onchange="verifyTeamQualifying()" />
					<powersi:hidden id="team_tag" name="mediSpecDto.team_tag"
						key="两病三师签约标记" value="0" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<div class="div_center">
				<powersi:button id="button_save" key="button_save" label="保存" onclick="savebutton()"/>
				<powersi:button id="button_exit" key="button_exit" label="退出" onclick="closeDialog()"/>
			</div>
		</powersi:form>
		
<script type="text/javascript">
 window.onload=function(){
	 //根据统筹区 区分珠海两病三师
	 var aaa027 = <%=aaa027%>;
	 var aka084 = $("#aka084").val();
	 if(aaa027 == "4404" && $("#aka083").val() =="110"){ 
		 if(aka084 !=null && aka084 !=""){
			$("#aaz308").prop("checked", true); 
			$("#aaz308").prop("disabled", true);
			$("#button_save").prop("disabled", true);
		 }
	 }else{
		 $("#aaz308").remove();
		 $("label[for='aaz308']").remove();
		 $("#button_save").remove();
	 }
}; 
	
	var curItemData = "";
	$(document).ready(function() {
		curItemData = $("#detailForm").serialize();
	});


function savebutton(){
		var flag_update="1";
		var saveItemData = $("#detailForm").serialize();
		if (saveItemData == curItemData) {
			popupAlert("没有修改,无需保存!");
			return;
		}
		
		postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!updateSqInfo.action",saveItemData,saveinfo_after);
}
	
function saveinfo_after(json){
	if (!checkJSONResult(json)) {
		return;
	} 
	if (json.data.length<=0){
		   popupAlert('修改失败！');
	}else {
	     popupInfo(json.data);
	     $("#button_save").attr("disabled",true);
	}
   
}

function verifyTeamQualifying() {
	if ($("#aaz308").is(':checked')) {
		var tracestring = powersi.trim($("#aac001").val());
		if (powersi.isnull(tracestring)) {
			popupAlert("先获取参保人信息！");
			$("#aaz308").prop("checked", false);
			$("#team_tag").val("0");
			return;
		}
		postJSON(
				"${rootPath}/medicare/MzchoHospitalBusApplyAction!verifyTeamQualifying.action",
				{
					"mediSpecDto.aac001" : $("#aac001").val()
					
				}, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.errortype == 0) {
						if (json.data == 1) {
							$("#aaz308").prop("checked", true);
							$("#team_tag").val("1");
						} else {
							$("#aaz308").prop("checked", false);
							$("#team_tag").val("0");
							popupInfo(json.message);
						}
					} else {
						$("#aaz308").prop("checked", false);
						$("#team_tag").val("0");
						popupInfo(json.message);
						return;
					}
				});
	} else {
		$("#aaz308").prop("checked", false)
		$("#team_tag").val("0");
	}

}


/* function delbutton() {
	var aaz267 = $("#aaz267").val();
	var saveItemData = $("#mainForm").serialize();
	if (!confirm("你确认删除本次申请信息吗?")) {
		return;
	}
	postJSON(
			"${rootPath}/medicare/MzchoHospitalBusApplyAction!deleteMzchoHospitalInfo.action",{"mediSpecDto.aaz267":aaz267}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupAlert(json.message);
			    closeDialog();	
			});
} */


</script>
	</body>
</powersi:html>