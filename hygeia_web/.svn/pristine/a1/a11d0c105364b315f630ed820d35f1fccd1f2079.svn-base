<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
<head>
<powersi:head title="门特(门慢)特殊业务修改" target="_self" />
</head>
<body>
	<powersi:form id="detailForm" name="detailForm">
		<powersi:editorlayout cols="8">
		   <powersi:editorlayout-row>
				<powersi:textfield label="姓名"    id="aac003" name="mediSpecDto.aac003" readonly="true" />
				<powersi:codeselect label="性别"    id="aac004" name="mediSpecDto.aac004" readonly="true" codeType="aac004"/>
				<powersi:textfield label="出生日期" id="aac006" name="mediSpecDto.aac006" readonly="true" />
				<powersi:textfield label="序列号"   id="aaz267" name="mediSpecDto.aaz267" readonly="true" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="身份证号" id="aac002" name="mediSpecDto.aac002" colspan="3" readonly="true" />
				<powersi:codeselect label="人员类别" id="bka004" name="mediSpecDto.bka004" codeType="bka004" disabled="true" />
				<td colspan="2"></td>
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="医院名称" id="akb021" name="mediSpecDto.akb021" colspan="3" readonly="true" />
				<powersi:textfield label="所属单位" id="aab069" name="mediSpecDto.aab069" colspan="3" readonly="true" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:hidden id="aka083" name="mediSpecDto.aka083"/>
				<powersi:hidden id="bka006" name="mediSpecDto.bka006"/>
				<powersi:hidden id="bke003" name="mediSpecDto.bke003"/>
				<powersi:textfield label="门特项目" id="bka155" name="mediSpecDto.bka155" colspan="3" readonly="true" />
				<powersi:textfield label="疾病名称" id="aka121" name="mediSpecDto.aka121" readonly="true" />
				<powersi:textfield label="有效期开始" id="aae030" name="mediSpecDto.aae030" readonly="true" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="就诊科室" id="bke015" name="mediSpecDto.bke015" readonly="false" />
				<powersi:textfield label="科室电话" id="bke014" name="mediSpecDto.bke014" readonly="false" />
				<powersi:textfield label="联系电话" id="bke016" name="mediSpecDto.bke016" readonly="false" />
				<powersi:textfield label="有效期结束" id="aae031" name="mediSpecDto.aae031" readonly="true" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="诊断医师" id="bke017" name="mediSpecDto.bke017" readonly="false" />
				<powersi:textfield label="主任医生" id="bke018" name="mediSpecDto.bke018" readonly="false" />
				<powersi:textfield label="医院意见" id="bke013" name="mediSpecDto.bke013" readonly="false" />
				<powersi:textfield label="医院意见时间" id="bke034" name="mediSpecDto.bke034" readonly="true" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="病情摘要及诊断" id="bke011" name="mediSpecDto.bke011" readonly="false" rows="2" colspan="7" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="诊治方案及项目构成" id="bke012" name="mediSpecDto.bke012" readonly="false" rows="2" colspan="7" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:hidden id="aae016" name="mediSpecDto.aae016"></powersi:hidden>
				<powersi:textfield label="审核标志" id="aae016" name="mediSpecDto.check_flag" readonly="true" />
				<powersi:textfield label="初审人" id="bke021" name="mediSpecDto.bke021" readonly="true" />
				<powersi:textfield label="初审时间" id="bke022" name="mediSpecDto.bke022" readonly="true" />
				<powersi:textfield label="复审人" id="aae014" name="mediSpecDto.aae014" readonly="true" />
		   </powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield label="复审时间" id="aae015" name="mediSpecDto.aae015" readonly="true" />
				<powersi:textfield label="备注" id="aae013" name="mediSpecDto.aae013" readonly="false"  colspan="5" />
		   </powersi:editorlayout-row>
		</powersi:editorlayout>
		<div class="div_center">
			<powersi:button id="save"   key="button_save" label="保存" onclick="saveModi()"/>
			<powersi:button id="delete" key="button_delete" label="删除" onclick="delbutton()" />
			<powersi:button key="button_exit" label="退出" onclick="closeDialog()" />
		</div>
	</powersi:form>
	<powersi:errors />
<script type="text/javascript">
	window.onload = function(){
		var check_flag = $("#aae016").val();
//		popupAlert(check_flag);
		if(check_flag == '2' || check_flag =='4'){
			$("#save").attr("disabled",true);
			$("#delete").attr("disabled",true);
		}
	}
		var curItemData = "";
		$(document).ready(function() {
			curItemData = $("#detailForm").serialize();
		});

		function delbutton() {
			var aaz267 = $("#aaz267").val();
			var saveItemData = $("#mainForm").serialize();
			if (!confirm("你确认删除本次申请信息吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/medicare/MtmmSpecialApplyAction!deleteSpeBsInfo.action",{"mediSpecDto.aaz267":aaz267}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
					    closeDialog();	
					});
		}
		
		function saveModi() {
			if (!checkFormValidtion()) {
				return;
			}
			var saveItemData = $("#detailForm").serialize();
			if (saveItemData == curItemData) {
				popupAlert("没有修改,无需保存!");
				return;
			}
			postJSON(
					"${rootPath}/medicare/MtmmSpecialApplyAction!saveModiSpeBsInfo.action",	saveItemData, afterSave);
		}
		function afterSave(json){
			if (!checkJSONResult(json)) {
				return;
			} else {
				if (json.message == "") {
					popupAlert("保存信息失败!");
					clearall();//重置
				} else {
				     popupInfo(json.message);
				     closeDialog();
				}
			}	
		}
	
	</script>	
</body>
</powersi:html>
