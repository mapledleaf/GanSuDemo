<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	 
	String param = request.getParameter("param");
	if(param == null){
		param = "";
	}
 
%>

<powersi:html>
<head>
<powersi:head title="预付款编辑" target="_self" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
<script type="text/javascript" src="${rootPath}/resource/js/inHospUtils.js"></script>
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:panelbox key="病人信息">
			<powersi:panelbox-toolbar>
				<powersi:button cssClass="button" key="button_save" label="保存" onclick="save()" />
				<powersi:button cssClass="button" key="button_cancel" label="关闭"
					onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:hidden id="aaz217" name="inHospitalDTO.aaz217" />
					<powersi:hidden id="akb020" name="inHospitalDTO.akb020" />
					<powersi:hidden id="aka130" name="inHospitalDTO.aka130" />

					<powersi:textfield id="aac003" name="inHospitalDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:textfield id="aac004_name"
						name="inHospitalDTO.aac004_name" label="性  别" readonly="true" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="身份证" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka008" name="inHospitalDTO.bka008"
						label="单位名称" readonly="true" />
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="入院日期" readonly="true" />
					<powersi:textfield id="bka035_name"
						name="inHospitalDTO.bka035_name" label="人员类别" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:panelbox key="床位信息">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="bka020" name="inHospitalDTO.bka020"
						label="住院科室" readonly="true" />
					<powersi:textfield id="bka022" name="inHospitalDTO.bka022"
						label="病区" readonly="true" />
					<powersi:textfield id="ake020" name="inHospitalDTO.ake020"
						label="床位" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="操作">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="bkn568" name="bkn568" label="本次缴纳金额"
						required="true" />
					<powersi:textfield id="bka245"
						name="inHospitalDTO.bka245" label="缴纳前金额" readonly="true" />
					<powersi:textfield id="bka245end" name="inHospitalDTO.bka245"
						label="缴纳后金额" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="操作记录">
		<powersi:panelbox-toolbar>
			<powersi:button cssClass="button" label="打印预付款" key="button_print"
				onclick="doPrint();"/>
		</powersi:panelbox-toolbar>
		<powersi:datagrid id="grid" delayLoad="true" showReload="false"
			enabledSort="false" alternatingRow="true" colDraggable="false"
			isMultiSelect="false" checkbox="true" usePager="false"
			rownumbers="true" pageSize="100" height="250">

			<powersi:datagrid-column name="bkn568" display="缴费金额" width="25%" />
			<powersi:datagrid-column name="curforegift" display="当前余额" width="25%" />
			<powersi:datagrid-column name="bkn565" display="操作员" width="25%" />
			<powersi:datagrid-column name="bkn149" display="缴费时间" width="25%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
$(function(){
	//对话框需要获取主窗口句柄
	var param = getDialogParam();
	
	if(!param){
		param = '<%=param%>';
		if(param == ''){
			param = null;
		}
	}
	 
	 
});
	window.onload = function(){
		getBkn2DtoList();
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
 
 
	}

	function save() {
		if (!checkFormValidtion()) {
			return;
		}

		var saveItemData = $("#mainform").serialize();

		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!foregiftEdit.action",
					saveItemData, function(json) {
					if (!checkJSONResultNew(json)) {
						return;
					}

					if (json.errortype == 0) {
						var len = powersi.length(json.data);
						if (len < 0) {
							popupAlert("失败=" + json.data.message, "错误", "error");
							return;
						}
						var suss = json.data.suss;
						if (suss == 1) {
							getBkn2DtoList();
							//更新缴纳后金额
							$("#bka245end").val(parseFloat($("#bkn568").val())+parseFloat($("#bka245").val()));
							popupAlert("保存成功", "提示", "info");
						} else {
							popupAlert(json.data.message, "提示", "info");
						}

					} else {
						popupAlert(json.data.message, "提示", "info");
					}
				});
	}

	
	function getBkn2DtoList() {
		grid.reset();
		var params = '';
		params = "type=getBkn2DtoList&inHospitalDTO.aaz217="
				+ $("#aaz217").val() + "&inHospitalDTO.akb020="
				+ $("#akb020").val();
		var url = "${rootPath}/inhospital/InhospitalManagerAction!foregiftEdit.action";

		postJSON(url, params, afterGetBkn2DtoList);
	}

	
	function afterGetBkn2DtoList(json) {
		if (!checkJSONResultNew(json)) {
			return;
		}
		grid.loadData(json.data);
	}

	function doPrint() {
		var data = grid.getData();
		data = powersi.tostring(data);
		if (powersi.isempty(data)) {
			popupAlert("没有数据！", "提示", "info");
			return;
		}
		var rows = grid.getSelectedRows();
		if (rows.length != 1) {
			popupAlert("请选择一行记录打印!", "提示", "info");
			return;
		}
		var data = rows[0];

		openDialogWithParam(
				"${rootPath}/inhospital/InhospitalManagerAction!foregiftEdit.action?type=printForegiftDetail&inHospitalDTO.aaz217="
						+ data.aaz217 + "&bkn2Id=" + data.id, "param1", 500,
				800, function(ret) {

				});
		}
	
</script>
</body>
</powersi:html>
