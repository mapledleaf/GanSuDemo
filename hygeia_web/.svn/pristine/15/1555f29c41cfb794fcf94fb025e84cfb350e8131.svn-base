<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="转诊转院申请修改" target="_self" />
</head>
<body>
	<powersi:form id="checkReferralTransferform">
		<powersi:groupbox key="panel_result" title="个人信息">
		    <powersi:panelbox-toolbar>
			    <powersi:button id="btSaveReferralTransfer" label="保存" 
			        onclick="saveReferralTransfer()" />
			    <powersi:button id="btDeleteReferralTransfer" label="删除" 
			        onclick="deleteReferralTransfer()" />
			    <powersi:button id="btClose" label="取 消" 
			        onclick="javascript:closeDialog();" />
		    </powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
				    <powersi:codeselect id="bka600" name="inHospitalDTO.bka600"
						label="类型" codeType="bka600" codeLocal="${inHospitalDTO.aaa027}"
						readonly="true" />
					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" readonly="true" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号" readonly="true" />
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
						label="转入医院" required="true"
						buttonId="hospse" readonly="true"  />
					<powersi:textfield id="aae011" name="inHospitalDTO.aae011"
						label="经办人" readonly="true" />
					<powersi:textfield id="bke128" name="inHospitalDTO.bke128"
						label="申请人姓名" required="false" readonly="false" />
					<powersi:textfield id="bke129" name="inHospitalDTO.bke129" 
						label="申请日期" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke127" name="inHospitalDTO.bke127"
						 label="病人意见" required="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke008" name="inHospitalDTO.bke008"
						 label="转出医院医务科意见" required="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke009" name="inHospitalDTO.bke009" 
						 label="病情摘要" required="true" colspan="8" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="aae013" name="inHospitalDTO.aae013"
						  label="备注" colspan="8" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
		<powersi:hidden id="ake017" name="inHospitalDTO.ake017" />
		<powersi:hidden id="aaz267" name="inHospitalDTO.aaz267" />
		<powersi:hidden id="bke058" name="inHospitalDTO.bke058" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
	    //需要判断该条记录状态来判断是否已经审核完成  若审核完成需要置该按钮为灰色
	    $(document).ready(function() {
	    	var bke058 = $("#bke058").val();
	    	if(!powersi.isnull(bke058)&&bke058!='0'){
	    		$("#btDeleteReferralTransfer").prop("disabled", true);
	    	}
		});
	    
	    //修改转入医院信息
		function chooseToHosp() {
			var aaa027 = $("#aaa027").val();
			popupDialog(
					{
						url : "${rootPath}/pages/biz/medicare/particular/chooseHospInfo.jsp?aaa027="+aaa027,
						onClosed : function() {
							var ret = this.returnValue;
							if (ret != null) {
								var akb020 = ret.akb020;   //转入医院编码
								var aab069 = ret.aab069;   //转入医院名称
								document.getElementById("ake017").value = akb020;
								document.getElementById("akc172").value = aab069;
							}
						}
					}, 500, 600);
		}
	    
	    //保存修改过的转诊转院申请信息
	    function saveReferralTransfer(){
	    	if (!checkForm()) {
				return;
			}
			var saveItemData = $("#checkReferralTransferform").serialize();
			$("#btSaveReferralTransfer").prop("disabled", true);
			postJSON("${rootPath}/particular/ParticularManagerAction!updateReferralTransferInfo.action",
					saveItemData, importUpdateBackInfo);
	    }
	    
	    //保存返回信息
	    function importUpdateBackInfo(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			if (!powersi.isnull(json.message)) {
				popupInfo(json.message);
			}
			$("#btSaveReferralTransfer").prop("disabled", false);
			closeDialog();
		}
	    
	    //删除申请（误操作等,点删除时不再对数据进行校验）
	    function deleteReferralTransfer(){
			var saveItemData = $("#checkReferralTransferform").serialize();
			$("#btDeleteReferralTransfer").prop("disabled", true);
			postJSON("${rootPath}/particular/ParticularManagerAction!deleteReferralTransferInfo.action",
					saveItemData, importDeleteBackInfo);
	    }
	    
	    //删除申请返回信息
	    function importDeleteBackInfo(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			if (!powersi.isnull(json.message)) {
				popupInfo(json.message);
			}
			$("#btDeleteReferralTransfer").prop("disabled", false);
			closeDialog();
		}
	</script>
</body>
</powersi:html>