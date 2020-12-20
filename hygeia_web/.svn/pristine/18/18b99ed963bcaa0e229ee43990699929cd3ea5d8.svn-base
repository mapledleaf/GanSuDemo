<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("hospital_id", BizHelper.getAkb020());
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("caa027", request.getParameter("caa027"));
	request.setAttribute("ake003", "'1'");
%>

<powersi:html>
<head>
<powersi:head title="新增药品目录" target="_self" />
</head>
<body>
<powersi:form id="mainform" disabled="true">
	<powersi:editorlayout cols="8">
		<powersi:editorlayout-row>
			<powersi:editorlayout-button colspan="8">
				<powersi:button buttonIcon="icon-ok" 	 label="保 存" onclick="save()" />
				<powersi:button buttonIcon="icon-repeat" label="取 消" onclick="javascript:closeDialog();"/>
			</powersi:editorlayout-button>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:hidden id="akb020" name="hospCataDto.akb020" />
			<powersi:hidden id="aae100" name="hospCataDto.aae100" value="1" />
			<powersi:hidden id="aae016" name="hospCataDto.aae016" value="0" />
			<powersi:hidden id="caa027" name="hospCataDto.caa027" value="${caa027}" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
   			<powersi:textfield id="ake005" name="hospCataDto.ake005" label="医院药品编码" 
   				required="true" validate="maxSize[20]" />
			<powersi:textfield id="ake006" name="hospCataDto.ake006" label="医院药品名称" 
				required="true" validate = "maxSize[100]" onchange="changeSearchType(this)" />
			<powersi:codeselect id="ake003" name="hospCataDto.ake003" label="目录类别" codeType="ake003" headerKey="-1"
				required="true" codeFilter="data_value in ('1') " />
			<powersi:textfield id="aka062" name="hospCataDto.aka062" label="英文名称" validate = "maxSize[300]"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="bkm017" name="hospCataDto.bkm017" label="药监本位码" validate="maxSize[20]" 
				required="true"/>
			<powersi:codeselect id="bkm005" name="hospCataDto.bkm005" label="医院剂型" 
				cssClass="select2" required="true" codeType="aka070" showValue="true" />
			<powersi:textfield id="bkm006" name="hospCataDto.bkc139" label="规格型号" required="true" validate="maxSize[300]" />
			<powersi:textfield id="bkc140" name="hospCataDto.bkc140" label="单价" required="true" validate="money" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="bkm020" name="hospCataDto.bkm020" label="包装规格" validate="maxSize[200]" />
			<powersi:textfield id="bkm014" name="hospCataDto.bkm014" label="包装价格" validate="money" />
			<powersi:textfield id="bkc141" name="hospCataDto.bkc141" label="生产单位" validate="maxSize[200]" />
			<powersi:textfield id="bkm019" name="hospCataDto.bkm019" label="产地" validate="maxSize[100]" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="bkm007" name="hospCataDto.bkm007" label="批准文号" validate="maxSize[100]" />
			<powersi:textfield id="bkm009" name="hospCataDto.bkm009" label="批准日期" mask="date" format="dateFmt:'yyyy-MM-dd'" />
			<powersi:textfield id="bkm022" name="hospCataDto.bkm022" label="商品名" validate="maxSize[50]" />
			<powersi:textfield id="bkm021" name="hospCataDto.bkm021" label="别名" validate="maxSize[50]" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:codeselect id="aka064" name="hospCataDto.aka064" key="aka064" label="处方用药标识" codeType="yes_no" />
			<powersi:codeselect id="aka067" name="hospCataDto.aka067" key="aka067" label="危重病用标志" codeType="yes_no" />
			<powersi:codeselect id="aka066" name="hospCataDto.aka066" key="aka066" label="毒麻精贵标志" codeType="yes_no" />
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="bkm025" name="hospCataDto.bkm025" label="备注1" validate = "maxSize[100]" colspan="5"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="bkc002" name="hospCataDto.bkc002" label="经办人" required="true" readonly="true" />
			<powersi:textfield id="bkc003" name="hospCataDto.bkc003" label="经办人工号" required="true" readonly="true" />
			<powersi:textfield id="aae036" name="hospCataDto.aae036" label="经办时间" mask="date" required="true" readonly="true" />
		</powersi:editorlayout-row>
	</powersi:editorlayout>
</powersi:form>
<powersi:errors />
<script type="text/javascript" src="${strutsPath}/include/combogrid.js"></script>
<script type="text/javascript">
	window.onload = function(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#akb020').val("${hospital_id}");
		$('#bkc002').val("${userName}");
		$('#bkc003').val("${loginUser}");
      	$('#aae036').val(year+"-"+month+"-"+day);
	}
       		
    var actionUrl = "${rootPath}/medicare/HospCataAction!queryDrugGBCode.action";
	//本位码输入候选项
	$(document).ready(function() {
		$("#bkm017").combogrid({
			minLength : 1,
			autoChoose : false,
			searchIcon : true,
			alternate : true,
			okIcon : true,
			width : '500px',
			hight : '300px',
			url : actionUrl,
			colModel : [ {
				'columnName' : 'rownumber',
				'width' : '10',
				'label' : ''
			}, {
				'columnName' : 'ake105',
				'width' : '20',
				'label' : '本位码'
			}, {
				'columnName' : 'ake106',
				'width' : '50',
				'label' : '名称'
			}, {
				'columnName' : 'ala026',
				'width' : '20',
				'label' : '产地'
			} ],
			select : function(event, ui) {
				$(this).val(ui.item.ake105);
				$("#bkm006").val(ui.item.ake008);
				$("#bkm005").val(ui.item.aka070);
				$("#select2-chosen-1").html(ui.item.aka070Name) ;
				return false;
			}
		});
	});
	
	function changeSearchType(sidx) {
		var ake006 = encodeURIComponent(sidx.value);
		$("#bkm017").combogrid("option", "url", actionUrl + "?ake006=" + ake006);
	}
	
	function save() {
		if (!checkFormValidtion()) {
			return;
		}
		/* if ($("#bkm017").next(".notok-icon").length > 0) {
			popupAlert("您输入的本位码没有对应的药品信息", "提示", "error");
			return;
		} */
		var saveItemData = $("#mainform").serialize();
		postJSON("${rootPath}/medicare/HospCataAction!saveHospCata.action", saveItemData, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			popupAlert(json.data);
			closeDialog();
		});
	}
</script>
</body>
</powersi:html>
