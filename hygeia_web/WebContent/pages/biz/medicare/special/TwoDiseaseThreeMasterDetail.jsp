<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String hospital_name = BizHelper.getAkb021();
	String aaa027_name = BizHelper.getAaa027Name();
%>
<powersi:html>
<head>
<powersi:head title="修改申请信息" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="tDisThreeMTeamDto.akb020" />
				<powersi:hidden id="aaz308" name="tDisThreeMTeamDto.aaz308" />
				<powersi:hidden id="aae100" name="tDisThreeMTeamDto.aae100" />
				<powersi:hidden id="aae016" name="tDisThreeMTeamDto.aae016" />
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
						headerKey="" headerValue="请选择..." 
						onchange="selectbka503()" showValue="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:hidden id="bka802" name="tDisThreeMTeamDto.bka802" />
				<powersi:hidden id="bka804" name="tDisThreeMTeamDto.bka804" />
				<powersi:codeselect id="bka801" name="tDisThreeMTeamDto.bka801"
						label="专科医师" cssClass="select2" list="#request.bka503List"
						headerKey="" headerValue="请选择..."  required="true" onchange="setSelectValue(this,'bka802')"/>
				<powersi:codeselect id="bka803" name="tDisThreeMTeamDto.bka803"
						label="全科医师" cssClass="select2" list="#request.bka503List1"
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
					label="经办时间" required="true" readonly="true" />
			</powersi:editorlayout-row>

			<powersi:editorlayout-row>
				<powersi:textarea id="bkm025" name="tDisThreeMTeamDto.bkm025" label="备注"
					cols="2" colspan="5" validate="maxSize[400]" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>


	</powersi:form>

	<powersi:errors />
	<script type="text/javascript">
	//定义全局变量
	var curItemData = "";
	var bka801value;
	var bka801text;
	var bka803value;
	var bka803text;
 		
    $(document).ready(function(){
        curItemData = $("#mainform").serialize();
        
	 	$('#akb021').val("<%=hospital_name%>");
		$('#aaa027_name').val("<%=aaa027_name%>");
      	
      	if($("#akb020").val() == '' || $("#akb020").val() == null ){
			popupAlert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
      	
    });
		
    function save(){
    	 //校验必填项
     	 if(!checkFormValidtion())
     	{
	  		return;
		} 
         var saveItemData = $("#mainform").serialize();
         if(saveItemData == curItemData ){
		    popupAlert("没有修改，无需保存！");
		    return;
		 }
         postJSON("${rootPath}/medicare/TeamVsSelfPayApplyAction!updateTowDisThreeMApply.action",
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
			
			//清空之前先保留之前的value和text
			var bka801Obj = document.getElementById("bka801");
			var bka803Obj = document.getElementById("bka803");
			
			var index =bka801Obj.selectedIndex;
			bka801text = bka801Obj.options[index].text;
			bka801value = bka801Obj.options[index].value;
			
			var index1 =bka803Obj.selectedIndex;
			bka801text = bka803Obj.options[index1].text;
			bka803value = bka803Obj.options[index1].value;
			
			$("#bka801").empty();
			$("#bka801").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka803").empty();
			$("#bka803").append("<option value='' >" + "请选择..." + "</option>");
			$("#bka503").change();
			
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
			
			if(id=="bka804"){
				$("#bka801").find("option[value='"+bka801value+"']").attr("selected",true); 
				$("#bka801").val(bka801value)
			}
			if(id=="bka802"){
				$("#bka803").find("option[value='"+bka803value+"']").attr("selected",true); 
				$("#bka803").val(bka803value)
			}
		}
	</script>
</body>
</powersi:html>
