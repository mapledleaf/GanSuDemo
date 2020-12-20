<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="转诊转院审批" target="_self" />
</head>
<body>
	<powersi:form id="checkReferralTransferform">
		<powersi:groupbox key="panel_result" title="个人信息">
		    <powersi:panelbox-toolbar>
			    <powersi:button id="btCheckNotPass" label="审批不通过" 
			        onclick="checkNotPass()" />
			    <powersi:button id="btClose" label="取 消" 
			        onclick="javascript:closeDialog();" />
		    </powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
					<powersi:textfield id="aab069" name="inHospitalDTO.aab069" 
					    label="单位名称"  readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac006" name="inHospitalDTO.aac006"
						label="出生日期"  readonly="true" />
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="科室" readonly="true" />
					<powersi:textfield id="bka023" name="inHospitalDTO.bka023" 
						label="床位号" readonly="true" />
					<powersi:textfield id="bka025" name="inHospitalDTO.bka025"
						label="住院号" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:panelbox title="详细信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="akc172" name="inHospitalDTO.akc172"
						label="转入医院" buttonId="hospse" readonly="true" />
					<powersi:textfield id="aae011" name="inHospitalDTO.aae011"
						label="经办人" readonly="true" />
					<powersi:textfield id="bke128" name="inHospitalDTO.bke128"
						label="申请人姓名" readonly="true" />
					<powersi:textfield id="bke129" name="inHospitalDTO.bke129" 
						label="申请日期" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke127" name="inHospitalDTO.bke127"
						 label="病人意见" readonly="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke008" name="inHospitalDTO.bke008"
						 label="转出医院医务科意见" readonly="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke009" name="inHospitalDTO.bke009" 
						 label="病情摘要" readonly="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="aae013" name="inHospitalDTO.aae013"
						  label="备注" readonly="true"  colspan="8" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="aaz267" name="inHospitalDTO.aaz267" />
		<powersi:hidden id="bke058" name="inHospitalDTO.bke058" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
	    //审批不通过
	    function checkNotPass(){
	    	$("#bke058").val('2');   //通过不通过标志
	    	if (!checkForm()) {
				return;
			}
			var saveItemData = $("#checkReferralTransferform").serialize();
			$("#btCheckNotPass").prop("disabled", true);
			postJSON("${rootPath}/particular/ParticularManagerAction!updateReferralTransferInfo.action",
					saveItemData, importCheckNotPassBackInfo);
	    }
	    
	    //审批不通过返回
	    function importCheckNotPassBackInfo(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			if (!powersi.isnull(json.message)) {
				popupInfo(json.message);
			}
			$("#btCheckNotPass").prop("disabled", false);
			closeDialog();
		}
	</script>
</body>
</powersi:html>