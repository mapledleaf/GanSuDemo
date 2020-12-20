<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath(); 

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaz267value = request.getAttribute("mediSpecDto.aaz267")==null?"0":request.getAttribute("mediSpecDto.aaz267").toString();
%>

<powersi:html>
<body>
	<powersi:head title="门特特殊业务申请" />
	<powersi:form id="mainForm" namespace="/medicare"
		action="MtmmSpecialApplyAction!queryMediPersonInfo.action">
		<div id="div1" style="display: none">
			<powersi:hidden id="akb020" name="mediSpecDto.akb020" key="akb020"/>
			<powersi:hidden id="akb021" name="mediSpecDto.akb021" key="akb021"/>
			<powersi:hidden id="aae140" name="inHospitalDTO.aae140" value="310"/>
			<powersi:hidden id="indi_id" name="indi_id" value="indi_id" />
			<powersi:hidden id="idcard" name="idcard" value="idcard" />
		</div>
		<powersi:groupbox title="人员信息">
			<powersi:panelbox-toolbar>
				<powersi:button id="button_save" key="保存"
					onclick="saveInfo()" />
				<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo()" buttonText="读     卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" />
					<powersi:textfield label="性别" id="aac004_name" readonly="true" />
					<powersi:hidden id="aac004" name="mediSpecDto.aac004" readonly="true" />
					<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
					<powersi:textfield label="人员类别" id="aac066" name="aac066"
						key="aac066" readonly="true" />
				</powersi:editorlayout-row>
	
				<powersi:editorlayout-row>
					<powersi:hidden id="aac001" name="mediSpecDto.aac001"></powersi:hidden>
					<powersi:textfield label="身份证号" id="aac002"
						name="mediSpecDto.aac002" key="aac002" readonly="true" />
					<powersi:textfield label="出生日期" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aac006"
						name="mediSpecDto.aac006" key="aac006" readonly="true" />
					<powersi:textfield label="工作日期" format="dateFmt:'yyyy-MM-dd'"
						id="aac007" name="mediSpecDto.aac007" key="aac007" readonly="true" />
					<powersi:textfield label="所属单位" id="aab069"
						name="mediSpecDto.aab069" key="aab069" readonly="true" colspan="3" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>

		<powersi:groupbox title="申请信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="有效期开始" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aae030"
						name="mediSpecDto.aae030" key="aae030" required="true" />
					<powersi:textfield label="有效期结束" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aae031"
						name="mediSpecDto.aae031" key="aae031" required="false"/>
					<powersi:select label="疾病名称" id="bke003" name="mediSpecDto.bke003"
						key="bke003" required="true" />
					<td><powersi:button key="button" value="选择" id="disease"
							disabled="true" onclick="chooseDis()" /></td>
					<td colspan=""></td>
					
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="申请人电话" id="bke016"
						name="mediSpecDto.bke016" key="bke016" required="true"
						validate="integer" maxlength="20" />
					<powersi:textfield label="申请医师" id="bke017"
						name="mediSpecDto.bke017" key="bke017" required="true"
						maxlength="20" />
					<powersi:textfield label="录入人" id="aae011"
						name="mediSpecDto.aae011" key="aae011" readonly="true" />
					<powersi:textfield label="录入时间" id="aae036"
						name="mediSpecDto.aae036" key="aae036" readonly="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="医院意见" id="bke013"
						name="mediSpecDto.bke013" key="bke013" required="true" colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="备注" id="aae013" name="mediSpecDto.aae013"
						key="aae013" required="false" readonly="false" colspan="7" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>

	</powersi:form>
	<script type="text/javascript">
	
	function saveInfo(){

		var saveItemData = $("#mainForm").serialize();
		if($("#arg_value").val() ==""){
			popupAlert("请先输入申请人信息查询!");
			return;
		}
		if(!checkForm()){
			return;
		}
		//校验开始日期必须小于或等于结束日期
		if($("#aae030").val() != "" && $("#aae031").val() != ""){
			if($("#aae030").val() > $("#aae031").val()){
				popupAlert("开始日期必须小于或等于结束日期!");
				return;
			}
		}
		if(!confirm("是否保存申请信息!")){
			return;
		}else{
			if($("#aae031").val()!=""){
				if(is_admit_date >= $("#aae031").val()){
					if(!confirm("此人已经有截止至"+is_admit_date+"同类门特项目记录,~r~n是否继续!")){
						return;
					}
				}
			}
		}
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!saveSpeBsInfo.action",saveItemData, afterSaveItem);
	}

	function afterSaveItem(json) {
		if (!checkJSONResult(json)) {
			return;
		} else {
			if (json.data == "") {
				popupAlert("保存信息失败!");
				clearall();//重置
			} else {
		     	popupInfo(json.data);
		     	$("#button_save").attr("disabled",true);
		     	$("#fundStatus").attr("disabled",true);
			}
		}
		
	}
	
		
						
	</script>
</body>
</powersi:html>