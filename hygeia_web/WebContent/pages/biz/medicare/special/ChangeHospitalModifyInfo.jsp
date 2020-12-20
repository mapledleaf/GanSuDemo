<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();

%>
<powersi:html>
	<head>
		<powersi:head title="转院人员信息修改"/>
	</head>
<body>
	<powersi:form id="detailForm" disabled="true">
		<powersi:hidden id="ake017" name="mediSpecDto.ake017" value="002001" />
		<powersi:hidden id="bka006" name="mediSpecDto.bka006" />
		<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
		<powersi:editorlayout cols="12%,12%,10%,13%,10%,16%,12%,15%">
			<tr>
			    <powersi:textfield  id="aac003"  name="mediSpecDto.aac003"  label="姓名" readonly="true"/>
				<powersi:textfield  id="aac004"  name="mediSpecDto.aac004"  label="性别" readonly="true"/>
				<powersi:textfield  id="aac006"  name="mediSpecDto.aac006"  label="出生日期" readonly="true" />
				<powersi:textfield  id="aaz267"  name="mediSpecDto.aaz267"  label="序列号" readonly="true" />
			</tr>
			<tr>
				<powersi:textfield  id="aab069" name="mediSpecDto.aab069" label="单位名称" readonly="true" colspan="3"/>
				<powersi:codeselect id="bka004" name="mediSpecDto.bka004" label="人员类别"  codeType="bka004" disabled="true" />
				<powersi:codeselect  id="bka006" name="mediSpecDto.bka006"  label="待遇类型 " readonly="true" codeType="bka006" disabled="true"/>
			</tr>
			<tr>
				<powersi:textfield  id="akb021" name="mediSpecDto.akb021" label="申请医院" readonly="true" colspan="3"/>
				<powersi:textfield  id="bke129" name="mediSpecDto.bke129" label="申请时间" readonly="true"/>
				<powersi:textfield  id="bke128" name="mediSpecDto.bke128" label="申请人"  readonly="false"/>
			</tr>
			<tr>
				<powersi:textfield  id="bka020" name="mediSpecDto.bka020" label="科室"  readonly="true"/>
				<powersi:textfield  id="bka023" name="mediSpecDto.bka023" label="床位号" readonly="true" />
				<powersi:textfield  id="bka025" name="mediSpecDto.bka025" label="住院号" readonly="true"/>
				<td colspan="2"></td>
			</tr>
			<tr>
				<powersi:textfield  id="ake014" name="mediSpecDto.ake014" label="转出时间" readonly="true" colspan="3"/>
				<powersi:textfield  id="ake016" name="mediSpecDto.ake016" label="转入时间" readonly="true" colspan="3"/>
			</tr>
			<tr>
				<powersi:textfield  id="akc172" name="mediSpecDto.akc172"   label="转入医院" readonly="true"  colspan="3"/>
				<powersi:textfield  id="bke127" name="mediSpecDto.bke127" label="病人意见" readonly="false"  colspan="3"/>
			</tr>
			<tr>
				<powersi:textarea  id="bke011" name="mediSpecDto.bke011" label="病情摘要及转诊理由"  readonly="false" rows="2" colspan="3"/>
				<powersi:textarea  id="bke010" name="mediSpecDto.bke010"  label="转入医院医务科意见" readonly="false" rows="2" colspan="3"/>
			</tr>
			<tr>
				<powersi:textfield  id="bke058" name="mediSpecDto.bke058" label="审批标志" readonly="true"/>
				<powersi:textfield  id="bke053" name="mediSpecDto.bke053"  label="审批人"  readonly="true"/>
				<powersi:textfield  id="bke048" name="mediSpecDto.bke048" label="审批时间" readonly="true"/>
				<td colspan="2"></td>
			</tr>
			<tr>
				<powersi:textfield  id="bke024" name="audit_opinion" label="中心审核意见" readonly="true"  colspan="7"/>
			</tr>
			<tr>
				<powersi:textfield  id="aae016" name="mediSpecDto.aae016" label="审核标志" readonly="true"/>
				<powersi:textfield  id="bke021" name="mediSpecDto.bke021"  label="审核人"  readonly="true"/>
				<powersi:textfield  id="bke022" name="mediSpecDto.bke022" label="审核时间" readonly="true"/>
				<td colspan="2"></td>
			</tr>
			<tr>
				<powersi:textfield  id="aae013" name="mediSpecDto.aae013" label="备注" readonly="false" colspan="7"/>
			</tr>
		</powersi:editorlayout>
		<div class="div_center">
			<%-- <powersi:button key="button_print" label="打印" onclick="print()"/> --%>
			<powersi:button id="button_save" key="button_save" label="保存" onclick="saveModi()"></powersi:button>
			<powersi:button id="button_delete" key="button_delete" label="删除" onclick="delbutton()"/>
			<powersi:button key="button_exit" label="退出" onclick="closeDialog()"/>
		</div>
	</powersi:form>
	<powersi:errors/>
	<script type="text/javascript">
	window.onload = function(){
		bottonDisa();
	}
		var curItemData = "";
		$(document).ready(function() {
			curItemData = $("#detailForm").serialize();
		});
		//删除人员信息
		function delbutton() {
			var aaz267 = $("#aaz267").val();
			var saveItemData = $("#detailForm").serialize();
			if (!confirm("你确认删除本次申请信息吗?")) {
				return;
			}
			postJSON(
					"${rootPath}/medicare/ChangeHospitalBusApplyAction!deleteSpeBsInfo.action",{"mediSpecDto.aaz267":aaz267}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
					    closeDialog();	
					});
		}
		//保存修改信息
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
					"${rootPath}/medicare/ChangeHospitalBusApplyAction!saveModiSpeBsInfo.action",	saveItemData, afterSave);
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
		
		function bottonDisa(){
			var bke058 = $("#bke058").val();
			var aae016 = $("#aae016").val();
			if(bke058 == "审核通过" || aae016 == "复审通过"){
				$("#button_save").attr("disabled",true);
				$("#button_delete").attr("disabled",true);
			}
		}
	</script>
</body>
</powersi:html>