<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>

<powersi:html>
<head>
<powersi:head title="新增体检项目目录" target="_self" />
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:editorlayout cols="10%,15%,15%,15%,15%,15%">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="6">
					<powersi:button cssClass="button" label="保 存" onclick="save()"></powersi:button>
					<powersi:button cssClass="button" label="取 消"
						onclick="javascript:closeDialog();"></powersi:button>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>

				<powersi:textfield id="bkh027" name="healthDTO.bkh027"
					label="项目编码" required="true" validate="maxSize[20]" />
				<powersi:textfield id="bkh028" name="healthDTO.bkh028"
					label="项目名称" required="true" validate="maxSize[100]" />
				<powersi:codeselect id="bkh046" name="healthDTO.bkh046" key="bkh046"
					label="统计类别" codeType="bkh046" cssClass="select2"
					required="true"  />

			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkh044" name="healthDTO.bkh044" label="单价"
					required="true" />
				<powersi:textfield id="bkh045" name="healthDTO.bkh045" label="标准单位"
					validate="maxSize[20]" />
				<powersi:textfield id="bkh103" name="healthDTO.bkh103" label="生效时间"
					mask="date" required="true" readonly="false" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkh104" name="healthDTO.bkh104" label="失效时间"
					mask="date" required="true" readonly="false" />
				<powersi:codeselect id="bkh048" name="healthDTO.bkh048" key="bkh048"
					label="是否离休体检项目" list="#{'1':'不是','2':'是'}" headerKey="0"
					required="true" />
				<powersi:textfield id="aae013" name="healthDTO.aae013" label="备注"
					validate="maxSize[300]" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bke205" name="healthDTO.bke205" label="经办人工号"
					required="true" readonly="true" />
				<powersi:textfield id="bke206" name="healthDTO.bke206" label="经办人"
					validate="maxSize[30]" required="true" readonly="true" />
				<powersi:textfield id="bke204" name="healthDTO.bke204" label="经办时间"
				 required="true" readonly="true" mask="date"/>
			</powersi:editorlayout-row>


			<powersi:editorlayout-row>
				<powersi:hidden id="akb020" name="healthDTO.akb020" label="医院编码" />
				<powersi:hidden id="bkh068" name="healthDTO.bkh068" label="序号" />
				<powersi:hidden id="aka021" name="healthDTO.aka021" label="五笔码" />
				<powersi:hidden id="aka020" name="healthDTO.aka020" label="首拼码" />
				<powersi:hidden id="bke207" name="healthDTO.bke207" label="审核时间" value="0" />
				<powersi:hidden id="bke208" name="healthDTO.bke208" label="审核人工号" />
				<powersi:hidden id="bke209" name="healthDTO.bke209" label="审核人" />
				<powersi:hidden id="aae016" name="healthDTO.aae016" label="审核标志" value="0" />
				<powersi:hidden id="bkh047" name="healthDTO.bkh047" label="版本号" value="0"/>
				<powersi:hidden id="aae100" name="healthDTO.aae100" label="有效标志 (0:无效 1:有效)" value="1" />
				<powersi:hidden id="bka044" name="healthDTO.bka044" label="上传标志"  value="0"/>
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
	  	$('#bke206').val("<%=userName%>");
      	$('#bke205').val("<%=loginUser%>");
		$('#bke204').val(year + "" + month + "" + day);

		if ($("#akb020").val() == '' || $("#akb020").val() == null) {
			popupAlert("医院编码不能为空，请检查登陆信息！");
			return;
		}
	}

	function save() {
		if(!checkFormValidtion())
     	{
	  		return;
		}
		if(!valid()){
    		return;
    	}
		var saveItemData = $("#mainform").serialize();
		postJSON("${rootPath}/medicare/HealthAction!saveHealthCata.action", saveItemData, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			alert(json.data);
			if (json.data == '保存目录信息成功！') {
				closeDialog();
			}
		});
		
		}

		function valid() {
			var strValid = /^[0-9]{1,8}(($)|([.][0-9]{1,4}))$/;
			var bkh044 = $("#bkh044").val();
			if (!strValid.test(bkh044)) {
				popupAlert("单价的格式有误！[范围：0-99999999.9999]");
				return false;
			}
			return true;
		}
	</script>
</body>
</powersi:html>
