<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String aaa027_name = BizHelper.getAaa027Name();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>

<powersi:html>
<head>
<powersi:head title="两病三师团队备案申请" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="tDisThreeMTeamDto.akb020" />
				<powersi:hidden id="aaa027" name="aaa027" />
				<powersi:hidden id="aae100" name="tDisThreeMTeamDto.aae100" value="1" />
				<powersi:hidden id="aae016" name="tDisThreeMTeamDto.aae016" value="0" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button id="btSave" label="保 存" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="aaa027_name" label="统筹区" readonly="true" />
				<powersi:textfield id="akb021" label="医院名称" readonly="true" />
				<powersi:codeselect id="bkc156" name="bkc156"
						label="医院科室" cssClass="select2" list="#request.bka019List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectbka503()" showValue="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:hidden id="bka802" name="tDisThreeMTeamDto.bka802" />
				<powersi:hidden id="bka804" name="tDisThreeMTeamDto.bka804" />
				<powersi:codeselect id="bka801" name="tDisThreeMTeamDto.bka801"
						label="专科医师" cssClass="select2" list="#request.bka503List"
						headerKey="" headerValue="请选择..."  required="true" onchange="setSelectValue(this,'bka802')"/>
				<powersi:codeselect id="bka803" name="tDisThreeMTeamDto.bka803"
						label="全科医师" cssClass="select2" list="#request.bka503List"
						headerKey="" headerValue="请选择..." required="true" onchange="setSelectValue(this,'bka804')"/>
				<powersi:textfield id="bka805" name="tDisThreeMTeamDto.bka805"
					label="护师士" required="true" validate="maxSize[10]" />
				
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textfield id="aae011" name="tDisThreeMTeamDto.aae011"
					label="经办人工号" required="true" readonly="true" />
				<powersi:textfield id="bae100" name="tDisThreeMTeamDto.bae100" label="经办人"
					required="true" readonly="true" />
				<powersi:textfield id="aae036" name="tDisThreeMTeamDto.aae036"
					label="经办时间" mask="date" required="true" readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bkm025" name="tDisThreeMTeamDto.bkm025" label="备注"
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
   		 	$('#aaa027').val("<%=aaa027%>");
			$('#aaa027_name').val("<%=aaa027_name%>");
   		  	$('#bae100').val("<%=userName%>");
          	$('#aae011').val("<%=loginUser%>");
          	$('#aae036').val(year+"-"+month+"-"+day);
          	
          	if($("#akb020").val() == '' || $("#akb020").val() == null ){
    			popupAlert("医院编码不能为空，请检查登陆信息！");
    	    	return;
    		}
   		
   		}
		
	    function save(){
	    	 //校验必填项
	     	if(!checkFormValidtion())
	     	{
		  		return;
			}
	         var saveItemData = $("#mainform").serialize();
	         postJSON("<%=path%>/medicare/TeamVsSelfPayApplyAction!saveTwoDiseaseThreeMasterApply.action",
					saveItemData, saveSuccess);
		}

		function saveSuccess(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			popupInfo(json.data);
			closeDialog();
		}
		
		/*加载医保医师*/
		function selectbka503() {
			//入院科室
			var bkc156 = $("#bkc156").val();
			if (powersi.isnull(bkc156)) {
				return;
			}
			
			$("#bka801").empty();
			$("#bka801").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka803").empty();
			$("#bka803").append("<option value='' >" + "请选择..." + "</option>");
			//$("#bka503").change();
			
			postJSON(
					"${rootPath}/medicare/TeamVsSelfPayApplyAction!loadBka503List.action",
					{
						"bkc156" : bkc156
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no") {
							var a = [];
							$.each(json.data, function(key, value) {
								a.push('<option value="');
								a.push(key);
								a.push('"');
								a.push(">");
								a.push(value);
								a.push("</option>");
							});
							$("#bka801").append(a.join(''));
							$("#bka803").append(a.join(''));
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
		}
		
		function setSelectValue(obj,id){
			var index =obj.selectedIndex;
			var text = obj.options[index].text;
			var value = obj.options[index].value;
			$("#"+id).val(text);
		}
	</script>
</body>
</powersi:html>
