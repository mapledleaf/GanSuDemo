<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String param = request.getParameter("param");
	if (param == null) {
		param = "";
	}
%>
<powersi:html>
<head>
<powersi:head title="费用套餐编辑" target="_self" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
	<powersi:form id="mainform" disabled="true">
		<powersi:panelbox key="基本信息">
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:editorlayout-button colspan="10">
						<powersi:button cssClass="button" label="保 存" id="btSaveFee" onclick="save()"/>
						<powersi:button cssClass="button" label="取 消" onclick="javascript:closeDialog();"/>
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="bkh015" name="bkh015"
						value="${kzh04Dto.bkh015}" />
					<powersi:hidden id="akb020" name="akb020"
						value="${kzh04Dto.akb020}" />
					<powersi:hidden id="feeinfo" name="feeinfo" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bkh059" name="bkh059" label="套餐名称"
						value="${kzh04Dto.bkh059}" required="true" />
					<powersi:textfield id="bkh058" name="bkh058" label="套餐编码"
						value="${kzh04Dto.bkh058}" />
					<powersi:codeselect id="aka130" key="aka130" name="aka130"
						codeType="aka130" value="${kzh04Dto.aka130}" />
					<powersi:textfield id="aka020" name="aka020" label="拼音助记码"
						value="${kzh04Dto.aka020}" />
					<powersi:textfield id="aka021" name="aka021" label="五笔助记码"
						value="${kzh04Dto.aka021}" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="费用明细">
		<powersi:panelbox-toolbar>
			<powersi:button id="btn_enter" key="button_ok" onclick="insertfee()" />
			<powersi:button id="btn_delete" key="button_delete"
				onclick="deletefee()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout
			cols="5%,7%,2%,12%,5%,15%,5%,8%,5%,8%,5%,8%,5%,10%,7%,10%">
			<powersi:editorlayout-row>
				<powersi:codeselect id="sidx" name="searchType" label="查询码"
					codeType="sidx" onchange="changeSearchType(this)"
					displayonly="false" />
				<powersi:textfield id="stext" name="stext" label="" maxlength="30"
					readonly="false" />
				<powersi:hidden id="bkc194" name="bkc194" />
				<powersi:hidden id="bkc109" name="bkc109" />
				<powersi:hidden id="ake001" name="ake001" />
				<powersi:hidden id="aka065" name="aka065" />
				<powersi:hidden id="ake003" name="ake003" />
				<powersi:hidden id="ake005" name="ake005" />
				<powersi:textfield id="ake006" name="ake006" label="名称"
					disabled="true" />
				<powersi:hidden id="bka052" name="bka052" />
				<powersi:hidden id="bka054" name="bka054" />
				<powersi:hidden id="bka053" name="bka053" />
				<powersi:hidden id="aka057" name="aka057" />
				<powersi:hidden id="bka051" name="bka051" value="${currDateStr}" />
				<powersi:textfield id="bka056" name="bka056" label="单价"
					onkeydown="dealKeyDown(this)" />
				<powersi:textfield id="bka080" name="bka080" label="优惠金额"
					onkeydown="dealKeyDown(this)" mask="number" value="0" />
				<powersi:textfield id="bka057" name="bka057" label="数量"
					onkeydown="dealKeyDown(this)" />
				<powersi:textfield id="bka058" name="bka058" label="金额"
					onkeydown="dealKeyDown(this)" />
				<powersi:codeselect id="bka060" name="bka060" label="用药标志"
					codeType="bka060" />
				<!-- ediku -->
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		<powersi:datagrid id="grid" delayLoad="true" showReload="false"
			autoWidth="true" enabledSort="false" usePager="false"
			alternatingRow="true" checkbox="true" colDraggable="false"
			totalRender="renderTotal" pageSize="100">
			<powersi:datagrid-column name="bkc194" display="匹配ID" width="1%"
				hide="true" />
			<powersi:datagrid-column name="bkc109" display="异名ID" width="1%"
				hide="true" />
			<powersi:datagrid-column name="ake005" display="编码" width="10%" />
			<powersi:datagrid-column name="ake006" display="名称" width="17%" />
			<powersi:datagrid-column name="bka053" display="厂家" width="15%" />
			<powersi:datagrid-column name="bka052" display="剂型" width="6%" />
			<powersi:datagrid-column name="bka054" display="规格" width="6%" />
			<powersi:datagrid-column name="bka056" display="单价" width="6%" />
			<powersi:datagrid-column name="bka080" display="优惠金" width="6%" />
			<powersi:datagrid-column name="bka057" display="数量" width="6%" />
			<powersi:datagrid-column name="bka058" display="金额" width="6%" />
			<powersi:datagrid-column name="aka057" display="先自付比例" width="0%"
				hide="true" />
			<powersi:datagrid-column name="bka892" display="保存标识" width="0%"
				hide="true" />
			<powersi:datagrid-column name="ake001" display="中心目录编码" width="0%"
				hide="true" />
			<powersi:datagrid-column name="bka060" display="用药标志" width="6%" />
			<powersi:datagrid-column name="aka065" code="aka065" display="等级"
				width="6%" />
			<powersi:datagrid-column name="ake003" code="ake003" display="类别"
				width="6%" hide="true" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatchedKzh04.action?searchType=py&bka051="
		+ $("#bka051").val();
	
	window.onload = function() {
		
	}
	$(function(){
		//对话框需要获取主窗口句柄
		var param = getDialogParam();
		if(!param){
			param = '<%=param%>';
			if (param == '') {
				param = null;
			}
		}
		$("#querystring").focus();
		$("#stext").combogrid({
			minLength : 1,
			autoChoose : false,
			searchIcon : true,
			alternate : true,
			width : "650px",
			url : actionUrl,
			colModel : [ {
				"columnName" : "aka065",
				"width" : "10",
				"label" : "等级"
			}, {
				"columnName" : "ake005",
				"width" : "20",
				"label" : "编码"
			}, {
				"columnName" : "ake006",
				"width" : "30",
				"label" : "名称"
			}, {
				"columnName" : "bkc140",
				"width" : "12",
				"label" : "单价"
			}, {
				"columnName" : "bkm005",
				"width" : "12",
				"label" : "剂型"
			}, {
				"columnName" : "bkm006",
				"width" : "12",
				"label" : "规格"
			} ],
			select : function(event, ui) {
				var data = ui.item;
				$("#bkc194").val(data["bkc194"]);//匹配ID
				$("#bkc109").val(data["bkc109"]);//异名ID
				$("#ake001").val(data["ake001"]);//中心目录编码
				$("#aka065").val(data["aka065"]);//收费项目等级编码(等级)
				$("#ake003").val(data["ake003"]);//三大目录类别编码(类别)					
				$("#ake005").val(data["ake005"]);//医疗机构三大目录编码(编码)
				$("#ake006").val(data["ake006"]);//医疗机构三大目录名称(名称)
				$("#bka052").val(data["bkm005"]);//医院目录剂型(剂型)
				$("#bka054").val(data["bkm006"]);//医院目录规格(规格)
				$("#bka053").val(data["bkc141"]);//医院目录厂家(厂家)
				$("#bka056").val(data["bkc140"]);//医院目录单价(单价)
				$("#aka057").val(data["aka057"]);//先自付比例
				$("#bka056").focus();
				return false;
			}
		});
		$("#sidx").val("py");
		if ($("#bkh015").val() != "") {
			loadPsTemplateFee();
		}
	});

		function save() {
			if (!checkFormValidtion()) {
				return;
			}

			if (grid.getRowsCount() <= 0) {
				popupAlert("请先录入费用");
				return;
			}
			var feeinfo = grid.getData();
			feeinfo = powersi.tostring(feeinfo);
			$("#feeinfo").val(encodeURI(feeinfo));
			var saveItemData = $("#mainform").serialize();
			postJSON(
					"${rootPath}/medicare/HospManageAction!feeSetMealDetail.action",
					saveItemData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}

						if (json.errortype == 0) {

							var len = powersi.length(json.data);
							if (len < 0) {
								popupAlert("失败=" + json.data.message);
								return;
							}
							var suss = json.data.suss;
							if (suss == 1) {
								popupAlert("保存成功");

								setDialogReturn("1");
								closeDialog();
							} else {
								popupAlert(json.data.message);
							}

						} else {
							var mes = json.message;
							popupAlert(mes);
						}
					});
		}

		function changeSearchType(sidx) {
			actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatchedKzh04.action?searchType="
					+ sidx.value + "&bka051=" + $("#bka051").val();
			$("#stext").combogrid("option", "url", actionUrl);
		}

		function onchangebka051(para) {
			var bka051 = $("#bka051").val();

			actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatchedKzh04.action?searchType="
					+ $("#sidx").val() + "&bka051=" + $("#bka051").val();
			$("#stext").combogrid("option", "url", actionUrl);
		}

		function dealKeyDown(para) {
			if (window.event.keyCode == "13") {
				var filed_name = para.id;
				if ("bka056" == filed_name) {
					var bka056 = $("#bka056").val();
					if (isNaN(bka056)) {
						popupAlert("单价不能为字符");
						return $("#bka056").focus();
					}
					if (!powersi.isnull(bka056)) {
						if (parseFloat(bka056) <= 0) {
							popupAlert("单价不能小于等于0");
							return $("#bka056").focus();
						}
					}
					return $("#bka057").focus();
				}
				if ("bka080" == filed_name) {
					var bka056 = $("#bka056").val();
					var bka080 = $("#bka080").val();
					if (isNaN(bka056)) {
						popupAlert("单价不能为字符");
						return $("#bka056").focus();
					}
					if (!powersi.isnull(bka056)) {
						if (parseFloat(bka056) <= 0) {
							popupAlert("单价不能小于等于0");
							return $("#bka056").focus();
						}
					}
					if (isNaN(bka080)) {
						popupAlert("优惠金不能为字符");
						return $("#bka080").focus();
					}
					return $("#bka057").focus();
				}
				if ("bka057" == filed_name) {
					var bka056 = $("#bka056").val();
					var bka057 = $("#bka057").val();
					var bka080 = $("#bka080").val();
					if (isNaN(bka057)) {
						popupAlert("数量不能为字符");
						return $("#bka057").focus();
					}
					if (!powersi.isnull(bka057)) {
						if (parseFloat(bka057) == 0) {
							popupAlert("数量不能等于0");
							return $("#bka057").focus();
						}
					}
					if (isNaN(bka080)) {
						popupAlert("优惠金不能为字符");
						return $("#bka080").focus();
					}
					if (!powersi.isnull(bka057) && !powersi.isnull(bka056)) {
						$("#bka058")
								.val(
										(parseFloat(bka057) * (parseFloat(bka056) - parseFloat(bka080)))
												.toFixed(2));
					}
					return $("#bka058").focus();
				}
				if ("bka058" == filed_name) {
					var bka058 = $("#bka058").val();
					if (isNaN(bka058)) {
						popupAlert("金额不能为字符");
						return $("#bka058").focus();
					}
					if (!powersi.isnull(bka058)) {
						if (parseFloat(bka058) == 0) {
							popupAlert("金额不能等于0");
							return $("#bka058").focus();
						}
					}
					insertfee();
				}
				if ("todayCollect" == filed_name) {
					var todayCollect = $("#todayCollect").val();
					var akb067 = $("#akb067").val();
					if (isNaN(todayCollect)) {
						popupAlert("今收取金额不能为字符");
						return $("#todayCollect").focus();
					}
					if (parseFloat(todayCollect) <= 0) {
						popupAlert("今收取金额不能小于等于0");
						return $("#todayCollect").focus();
					}
					if (powersi.isnull(todayCollect)) {
						todayCollect = 0;
					}
					$("#needFind").val(
							(parseFloat(todayCollect) - parseFloat(akb067))
									.toFixed(2));
				}
			}
		}

		function drfee() {
			popupDialog(
					{
						url : "${rootPath}/pages/biz/medicare/comm/InHospitalFeeDr.jsp",
						onClosed : function() {

						}
					}, 200, 600);
		}

		function insertfee() {
			if (powersi.isnull($("#ake006").val())) {
				popupAlert("未录入药品");
				return;
			}
			if (powersi.isnull($("#bka058").val())) {
				popupAlert("未录入金额");
				return;
			}
			if (parseFloat($("#bka058").val()) == 0) {
				popupAlert("金额不能等于0");
			}
			if (isNaN($("#bka058").val()) * 1) {
				popupAlert("金额输入有误");
				$("#bka058").focus();
				return;
			}
			var bkc194 = $("#bkc194").val();
			var bkc109 = $("#bkc109").val();
			var ake001 = $("#ake001").val();
			var aka065 = $("#aka065").val();
			var ake003 = $("#ake003").val();
			var ake005 = $("#ake005").val();
			var ake006 = $("#ake006").val();
			var bka053 = $("#bka053").val();
			var bka052 = $("#bka052").val();
			var bka054 = $("#bka054").val();
			var bka056 = parseFloat($("#bka056").val());
			var bka080 = $("#bka080").val();
			var bka057 = parseFloat($("#bka057").val());
			var input_ver = (parseFloat($("#bka058").val()) * 1.0000)
					.toFixed(2);

			var aka057 = $("#aka057").val();
			var bka060 = $("#bka060").val();
			var bka892 = "0";
			var jsonFee = {
				"bkc194" : bkc194,
				"bkc109" : bkc109,
				"ake001" : ake001,
				"aka065" : aka065,
				"ake003" : ake003,
				"ake005" : ake005,
				"ake006" : ake006,
				"bka080" : bka080,
				"bka053" : bka053,
				"bka052" : bka052,
				"bka054" : bka054,
				"bka056" : bka056,
				"bka057" : bka057,
				"bka058" : input_ver,
				"aka057" : aka057,
				"bka892" : bka892,
				"bka060" : bka060
			};
			grid.add(jsonFee);
			$("#stext").focus();
			$("#stext").val("");
			$("#bkc194").val("");
			$("#bkc109").val("");
			$("#ake001").val("");
			$("#aka065").val("");
			$("#ake003").val("");
			$("#ake005").val("");
			$("#ake006").val("");
			$("#bka053").val("");
			$("#bka052").val("");
			$("#bka054").val("");
			$("#bka056").val("");
			$("#bka080").val("0");
			$("#bka057").val("");
			$("#bka058").val("");
			$("#aka057").val("");
			$("#bka060").val("");
		}

		function deletefee() {
			if (typeof (grid.getSelectedRows()) == "undefined"
					|| grid.getSelectedRows() == null
					|| grid.getSelectedRows() == "") {
				popupAlert("请选择要删除的费用");
				return;
			}
			if (confirm("是否删除已选择的费用?") == true) {
				grid.deleteSelectedRow();
			}
		}

		function renderTotal(allData, currentData) {
			var money = 0;
			var price = 0;
			var usage = 0;
			var kinds = {};
			if (powersi.rows_length(allData['rows'])) {
				$.each(allData['rows'], function(n, row) {
					if (row['__status'] !== "delete") {
						price += parseFloat(row['bka056']);
						usage += parseFloat(row['bka057']);
						money += parseFloat(row['bka058']);
						kinds[row['ake005']] = '';
					}
				});
			}
			var a = [];
			a.push('<div class="divCenter textSuccess">');
			a.push('<span>');
			a.push('总的金额：<b>');
			a.push(money.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('&nbsp;&nbsp;');
			a.push('<span>');
			a.push('总的用量：<b>');
			a.push(usage.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('&nbsp;&nbsp;');
			a.push('<span>');
			a.push('共 <b>');
			a.push(powersi.length(kinds));
			a.push('</b> 项费用');
			a.push('</span>');
			a.push('</div>');
			return a.join('');
		}

		function loadPsTemplateFee() {
			var params = '';
			params += "type=1&bkh015=" + $("#bkh015").val();
			postJSON(
					"${rootPath}/medicare/HospManageAction!loadPsTemplateSel.action",
					params, callbackloadPsTemplateFee);
		}

		function callbackloadPsTemplateFee(json) {
			if (!checkJSONResult(json)) {
				return;
			} else {
			}
			if (json.errortype == 0) {
				var suss = json.data.suss;
				if (suss == 1) {
					var rowsize = json.data.feeinfo.length;
					if (rowsize > 0) {
						var feeinfot = json.data.feeinfo;
						grid.loadData(feeinfot);
					} else {
						popupAlert("获取费用信息失败！");
					}
				} else {
					popupAlert(json.data.message);
				}
			} else {
				var mes = json.message;
				popupAlert(mes);
			}
		}
	</script>
</body>
</powersi:html>
