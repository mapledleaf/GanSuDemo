<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="医疗费用明细信息" description="医疗费用明细信息"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<script type="text/javascript" src="${rootPath}/pages/biz/medicare/diagnose/InputFee.js?20170615001"></script>
<script type="text/javascript">
	var centerId = '439900';
	var curDate = new Date();
	var month = curDate.getMonth() + 1 < 10 ? "0"
			+ (curDate.getMonth() + 1) : curDate.getMonth() + 1;
	var day = curDate.getDate() < 10 ? "0" + (curDate.getDate())
			: curDate.getDate();
	var hours = curDate.getHours() < 10 ? "0"
			+ (curDate.getHours()) : curDate.getHours();
	var min = curDate.getMinutes() < 10 ? "0"
			+ (curDate.getMinutes()) : curDate.getMinutes();
	var seconds = curDate.getSeconds() < 10 ? "0"
			+ (curDate.getSeconds()) : curDate.getSeconds();
	var curDateStr = curDate.getFullYear() + "-" + month + "-"
			+ day + " " + hours + ":" + min + ":" + seconds;
	$("#searchType").val("py");

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

	var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType=py&bka051="
	   + curDateStr+"&healthposts_flag=1"+"&inHospitalDTO.caa027="+$("#caa027").val();
	   
	jQuery(document).ready(function() {
		$("#stext").combogrid({
			minLength : 1,
			autoChoose : false,
			searchIcon : true,
			alternate : true,
			width : '650px',
			url : actionUrl,
			colModel : [ 
			{
				'columnName' : 'aka065_name',
				'width' : '8',
				'label' : '等级'
			}, {
				'columnName' : 'ake005',
				'width' : '20',
				'label' : '编码'
			}, {
				'columnName' : 'ake006',
				'width' : '30',
				'label' : '名称'
			}, {
				'columnName' : 'bka056',
				'width' : '12',
				'label' : '单价'
			}, {
				'columnName' : 'bka052_name',
				'width' : '12',
				'label' : '剂型'
			}, {
				'columnName' : 'bka054',
				'width' : '12',
				'label' : '规格'
			} ],
			select : function(event, ui) {
				var data = ui.item;
				if(!selKzh04(data))
				{
					$("#btnCalc0").attr("disabled", false);
					return false;					
				}
				$("#bkc194").val(data["bkc194"]);//匹配ID
				$("#bkc109").val(data["bkc109"]);//异名ID
				$("#aka065").val(data["aka065"]);//收费项目等级编码(等级)
				$("#ake003").val(data["ake003"]);//三大目录类别编码(类别)					
				$("#ake005").val(data["ake005"]);//医疗机构三大目录编码(编码)
				$("#ake006").val(data["ake006"]);//医疗机构三大目录名称(名称)
				$("#bka052").val(data["bka052"]);//医院目录剂型(剂型)
				$("#bka054").val(data["bka054"]);//医院目录规格(规格)
				$("#bka053").val(data["bka053"]);//医院目录厂家(厂家)
				$("#bka056").val(data["bka056"]);//医院目录单价(单价)
				$("#aka057").val(data["aka057"]);//先自付比例
				$("#bkc106").val(data["bkc106"]);//居民先支付比例
				$("#ake001").val(data["ake001"]);
				$("#ake002").val(data["ake002"]);
				$("#aka063").val(data["aka063"]);
				$("#bka056").focus();
				return false;
			}
		});
	});

	function insertfee() {
		if (powersi.isnull($("#ake006").val())) {
			alert("未录入药品");
			return;
		}

		if (powersi.isnull($("#bka058").val())) {
			alert("未录入金额");
			return;
		}

		if (parseFloat($("#bka058").val()) <= 0) {
			alert("金额不能小于等于0");
			return;
		}

		if (isNaN($("#bka058").val()) * 1) {
			alert("金额输入有误");
			$("#bka058").focus();
			return;
		}

		var bkc194 = $("#bkc194").val();
		var bkc109 = $("#bkc109").val();
		var aka065 = $("#aka065").val();
		var ake003 = $("#ake003").val();
		var ake005 = $("#ake005").val();
		var ake006 = $("#ake006").val();
		var bka053 = $("#bka053").val();
		var bka052 = $("#bka052").val();
		var bka054 = $("#bka054").val();
		var bka056 = parseFloat($("#bka056").val()).toFixed(2);
		var bka057 = parseFloat($("#bka057").val());
		var input_ver = $("#bka058").val();
		var bka051 = $("#bka051").val();
		var aka057 = $("#aka057").val();
		if(powersi.isnull(aka057)){
			$("#aka057").val("0");
		}
	    aka057 = parseFloat($("#aka057").val()).toFixed(2);
		var bkc106 = $("#bkc106").val();
		var ake001 = $("#ake001").val();
		var ake002 = $("#ake002").val();
		var aka063 = $("#aka063").val();

		var jsonFee = {
			"bkc194" : bkc194,
			"bkc109" : bkc109,
			"aka065" : aka065,
			"ake003" : ake003,
			"ake005" : ake005,
			"ake006" : ake006,
			"bka053" : bka053,
			"bka052" : bka052,
			"bka054" : bka054,
			"bka056" : bka056,
			"bka057" : bka057,
			"bka058" : parseFloat(input_ver).toFixed(2) * 1.0000,
			"bka051" : bka051,
			"bkc106" : bkc106,
			"aka057" : aka057,
			"ake001" : ake001,
			"ake002" : ake002,
			"aka063" : aka063
		};

		gridFeeDetail.add(jsonFee);

		$("#stext").focus();
		$("#stext").val("");
		$("#bkc194").val("");
		$("#bkc109").val("");
		$("#aka065").val("");
		$("#ake003").val("");
		$("#ake005").val("");
		$("#ake006").val("");
		$("#bka053").val("");
		$("#bka052").val("");
		$("#bka054").val("");
		$("#bka056").val("");
		$("#bka057").val("");
		$("#bka058").val("");
		$("#aka057").val("");
		$("#bkc106").val("");
		$("#ake001").val("");
		$("#ake002").val("");
		$("#aka063").val("");

		$("#btnSave").attr("disabled", true);
		$("#btnCalc0").attr("disabled", false);
	}

	function deletefee() {
		if (typeof (gridFeeDetail.getSelectedRows()) == "undefined"
				|| gridFeeDetail.getSelectedRows() == null
				|| gridFeeDetail.getSelectedRows() == "") {
			alert('请选择要删除的费用');
			return;
		}
		if (confirm("是否删除已选择的费用?") == true) {
			gridFeeDetail.deleteSelectedRow();
		}
	}

	function changeSearchType(sidx) {
		actionUrl = '${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType='
				+ sidx.value +"&healthposts_flag=1"+ "&bka051=" + $("#bka051").val();
		$("#stext").combogrid("option", "url", actionUrl);
	}

	function onchangebka051(para) {
		var bka051 = $("#bka051").val();
		var bka017 = $("#bka017").val();
		if (bka051 < bka017) {
			alert("费用日期不能小于门诊日期");
			return $("#bka051").focus();
		}
		actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
				+ $("#sidx").val() +"&healthposts_flag=1"+ "&bka051=" + $("#bka051").val();
		$("#stext").combogrid("option", "url", actionUrl);
	}

	function dealKeyDown(para) {
		if (window.event.keyCode == '13') {
			var filed_name = para.id;
			if ('bka056' == filed_name) {
				var bka056 = $("#bka056").val();
				if (isNaN(bka056)) {
					alert("单价不能为字符");
					return $("#bka056").focus();
				}
				if (!powersi.isnull(bka056)) {
					if (parseFloat(bka056) <= 0) {
						alert("单价不能小于等于0");
						return $("#bka056").focus();
					}
				}
				return $("#bka057").focus();
			}
			if ('bka057' == filed_name) {
				var bka056 = $("#bka056").val();
				var bka057 = $("#bka057").val();
				if (isNaN(bka057)) {
					alert("数量不能为字符");
					return $("#bka057").focus();
				}
				if (!powersi.isnull(bka057)) {
					if (parseFloat(bka057) <= 0) {
						alert("数量不能小于等于0");
						return $("#bka057").focus();
					}
				}
				if (!powersi.isnull(bka057) && !powersi.isnull(bka056)) {
					$("#bka058").val(parseFloat(bka057) * parseFloat(bka056));
				}
				return $("#bka058").focus();
			}

			if ('todayCollect' == filed_name) {
				var todayCollect = $("#todayCollect").val();
				var grzfje3 = $("#grzfje3").val();
				if (isNaN(todayCollect)) {
					alert("金额不能为字符");
					return $("#todayCollect").focus();
				}
				if (parseFloat(todayCollect) <= 0) {
					alert("金额不能小于等于0");
					return $("#todayCollect").focus();
				}
				if (powersi.isnull(todayCollect)) {
					todayCollect = 0;
				}
				$("#needFind").val(
						(parseFloat(todayCollect) - parseFloat(grzfje3))
								.toFixed(2));
			}

			if ('bka058' == filed_name) {
				insertfee();
			}
		}
	}
	
	//新增行后跳转至最后一行
 	function scrollToRowLast(rowdata){
 		gridFeeDetail.scrollToRow(rowdata.__index);
 	}
</script>
<powersi:panelbox key="费用明细信息">
	<powersi:panelbox-toolbar>
		<powersi:button id="btn_enter" key="button_ok" onclick="insertfee()" />
		<powersi:button id="btn_delete" key="button_delete"
			onclick="deletefee()" />
	</powersi:panelbox-toolbar>
	<powersi:editorlayout
		cols="5%,7%,1%,13%,5%,17%,3%,9%,3%,9%,3%,9%,5%,11%">
		<powersi:editorlayout-row>
			<powersi:codeselect id="sidx" name="searchType" label="查询码"
				codeType="sidx" onchange='changeSearchType(this)'
				displayonly="false" headerValue="py" />
			<powersi:textfield id="stext" name="stext" label="" maxlength="30"
				readonly="false" />
			<powersi:hidden id="ake001" name="ake001" />
			<powersi:hidden id="ake002" name="ake002" />
			<powersi:hidden id="ake003" name="ake003" />
			<powersi:hidden id="ake005" name="ake005" />
			<powersi:textfield id="ake006" name="ake006" label="名称"
				disabled="true" />
			<powersi:hidden id="bka052" name="bka052" />
			<powersi:hidden id="bka054" name="bka054" />
			<powersi:hidden id="bka053" name="bka053" />
			<powersi:hidden id="aka057" name="aka057" />
			<powersi:hidden id="bkc106" name="bkc106" />
			<powersi:hidden id="aka063" name="aka063" />
			<powersi:textfield id="bka056" name="bka056" label="单价"
				onkeydown="dealKeyDown(this)" />
			<powersi:textfield id="bka057" name="bka057" label="数量"
				onkeydown="dealKeyDown(this)" />
			<powersi:textfield id="bka058" name="bka058" label="金额"
				onkeydown="dealKeyDown(this)" />
			<powersi:textfield id="bka051" name="bka051" label="日期" mask="date"
				onchange="onchangebka051(this)" readonly="false" />
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<powersi:datagrid id="gridFeeDetail" height="85%" delayLoad="true" onAfterAddRow="scrollToRowLast"
		showReload="false" autoWidth="true" alternatingRow="true"
		checkbox="true" colDraggable="false" pageSize="30"
		totalRender="renderTotal">
		<powersi:datagrid-column name="ake005" display="编码" width="10%" />
		<powersi:datagrid-column name="ake006" display="名称" width="18%" />
		<powersi:datagrid-column name="bka053" display="厂家" width="16%" />
		<powersi:datagrid-column name="bka052" display="剂型" width="6%" />
		<powersi:datagrid-column name="bka054" display="规格" width="6%" />
		<powersi:datagrid-column name="bka056" display="单价" width="6%" />
		<powersi:datagrid-column name="bka057" display="数量" width="6%" />
		<powersi:datagrid-column name="bka058" display="金额" width="6%" />
		<powersi:datagrid-column name="bka051" display="日期" width="8%" />
		<powersi:datagrid-column name="aka057" display="先自付比例" width="6%" />
	</powersi:datagrid>
	<powersi:hidden id="feeinfo" name="feeinfo" />
</powersi:panelbox>