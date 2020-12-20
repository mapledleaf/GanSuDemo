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
					price += parseFloat(row['bka040']);
					usage += parseFloat(row['akc226']);
					money += parseFloat(row['aae019']);
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

	/* var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType=py&ake007="
			+ curDateStr;//initCtrlInHospitalDTO */
	var actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType=py&ake007="
	   + curDateStr+"&type=2"+"&inHospitalDTO.caa027="+$("#caa027").val();//套餐+"&caa027="+$("#caa027").val()
	   
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
				'columnName' : 'bka040',
				'width' : '12',
				'label' : '单价'
			}, {
				'columnName' : 'aka070',
				'width' : '12',
				'label' : '剂型'
			}, {
				'columnName' : 'aka074',
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
				$("#aka070").val(data["aka070"]);//医院目录剂型(剂型)
				$("#aka074").val(data["aka074"]);//医院目录规格(规格)
				$("#bka073").val(data["bka073"]);//医院目录厂家(厂家)
				$("#bka040").val(data["bka040"]);//医院目录单价(单价)
				$("#aka057").val(data["aka057"]);//先自付比例
				$("#bkc106").val(data["bkc106"]);//居民先支付比例
				$("#ake001").val(data["ake001"]);//目录编码
				$("#ake002").val(data["ake002"]);//目录名称
				$("#aka063").val(data["aka063"]);
				$("#bka040").focus();
				return false; 
			}
		});
	});

	function insertfee() {
		if (powersi.isnull($("#ake006").val())) {
			alert("未录入药品");
			return;
		}

		if (powersi.isnull($("#aae019").val())) {
			alert("未录入金额");
			return;
		}

		if (parseFloat($("#aae019").val()) <= 0) {
			alert("金额不能小于等于0");
			return;
		}

		if (isNaN($("#aae019").val()) * 1) {
			alert("金额输入有误");
			$("#aae019").focus();
			return;
		}

		var bkc194 = $("#bkc194").val();
		var bkc109 = $("#bkc109").val();
		var aka065 = $("#aka065").val();
		var ake003 = $("#ake003").val();
		var ake005 = $("#ake005").val();
		var ake006 = $("#ake006").val();
		var bka073 = $("#bka073").val();
		var aka070 = $("#aka070").val();
		var aka074 = $("#aka074").val();
		var bka040 = formatDecimal($("#bka040").val());
		var akc226 = formatDecimal($("#akc226").val());
		var input_ver = $("#aae019").val();
		var ake007 = $("#ake007").val();
		var aka057 = $("#aka057").val();
		if(powersi.isnull(aka057)){
			$("#aka057").val("0");
		}
	    aka057 = parseFloat($("#aka057").val()).toFixed(2);
		var bkc106 = $("#bkc106").val();
		var ake001 = $("#ake001").val();
		var ake002 = $("#ake002").val();
		var aka063 = $("#aka063").val();
		var bkz103 = $("#bkz103").val();
		var jsonFee = {
			"bkc194" : bkc194,
			"bkc109" : bkc109,
			"aka065" : aka065,
			"ake003" : ake003,
			"ake005" : ake005,
			"ake006" : ake006,
			"bka073" : bka073,
			"aka070" : aka070,
			"aka074" : aka074,
			"bka040" : bka040,
			"akc226" : akc226,
			"aae019" : parseFloat(input_ver).toFixed(2) * 1.0000,
			"ake007" : ake007,
			"bkc106" : bkc106,
			"aka057" : aka057,
			"ake001" : ake001,
			"ake002" : ake002,
			"aka063" : aka063,
			"bkz103" : bkz103
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
		$("#bka073").val("");
		$("#aka070").val("");
		$("#aka074").val("");
		$("#bka040").val("");
		$("#akc226").val("");
		$("#aae019").val("");
		$("#aka057").val("");
		$("#bkc106").val("");
		$("#ake001").val("");
		$("#ake002").val("");
		$("#aka063").val("");
		$("#bkz103").val("0");

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
				+ sidx.value + "&ake007=" + $("#ake007").val();
		$("#stext").combogrid("option", "url", actionUrl);
	}

	function onchangeake007(para) {
		var ake007 = $("#ake007").val();
		var aae030 = $("#aae030").val();
		if (ake007 < aae030) {
			alert("费用日期不能小于门诊日期");
			return $("#ake007").focus();
		}
		actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
				+ $("#sidx").val() + "&ake007=" + $("#ake007").val();
		$("#stext").combogrid("option", "url", actionUrl);
	}

	function dealKeyDown(para) {
		if (window.event.keyCode == '13') {
			var fieldName = para.id;
			if ('bka040' == fieldName) {
				var bka040 = $("#bka040").val();
				if (isNaN(bka040)) {
					alert("单价不能为字符");
					return $("#bka040").focus();
				}
				if (!powersi.isnull(bka040)) {
					if (parseFloat(bka040) <= 0) {
						alert("单价不能小于等于0");
						return $("#bka040").focus();
					}
				}
				return $("#akc226").focus();
			}
			if ('akc226' == fieldName) {
				var bka040 = $("#bka040").val();
				var akc226 = $("#akc226").val();
				if (isNaN(akc226)) {
					alert("数量不能为字符");
					return $("#akc226").focus();
				}
				if (!powersi.isnull(akc226)) {
					if (parseFloat(akc226) <= 0) {
						alert("数量不能小于等于0");
						return $("#akc226").focus();
					}
				}
				if (!powersi.isnull(akc226) && !powersi.isnull(bka040)) {
					$("#aae019").val((parseFloat(akc226) * parseFloat(bka040)).toFixed(2));
				}
				return $("#aae019").focus();
			}

			if ('em_jsq' == fieldName) {
				var todayCollect = $("#em_jsq").val();
				var grzfje3 = $("#st_xjzf").val();
				if (isNaN(todayCollect)) {
					alert("金额不能为字符");
					return $("#em_jsq").focus();
				}
				if (parseFloat(todayCollect) <= 0) {
					alert("金额不能小于等于0");
					return $("#em_jsq").focus();
				}
				if (powersi.isnull(todayCollect)) {
					todayCollect = 0;
				}
				$("#st_out").val((parseFloat(todayCollect) - parseFloat(grzfje3)).toFixed(2));
			}

			if ("aae019" === fieldName) {
				return $("#bkz103").focus();
			}

			if ("bkz103" === fieldName) {
				insertfee();
			}
		}
	}
	
	//新增行后跳转至最后一行
 	function scrollToRowLast(rowdata){
 		gridFeeDetail.scrollToRow(rowdata.__index);
 	}

	/**
	 * 显示超范围用药
	 * @param rowdata 行数据
	 * @param index 索引值
	 * @param value 码值
	 * @returns {string}
	 */
	function renderBkz103(rowdata, index, value){
		if(value === "0"){
			return "否";
		}
		if(value === "1"){
			return "是";
		}
	}
</script>
<powersi:panelbox key="费用明细信息" allowCollapse="false">
	<powersi:panelbox-toolbar>
		<powersi:button id="btn_enter" key="button_ok" onclick="insertfee()" />
		<powersi:button id="btn_delete" key="button_delete"
			onclick="deletefee()" />
	</powersi:panelbox-toolbar>
	<powersi:editorlayout cols="4%,7%,1%,10%,4%,8%,5%,8%,5%,8%,6%,8%,8%,5%,4%,9%">
		<powersi:editorlayout-row>
			<powersi:codeselect id="sidx" name="searchType" label="查询码"
				codeType="sidx" onchange='changeSearchType(this)'
				displayonly="false" headerValue="py" />
			<powersi:textfield id="stext" name="stext" label="" maxlength="30" readonly="false" />
			<powersi:hidden id="ake001" name="ake001" />
			<powersi:hidden id="ake002" name="ake002" />
			<powersi:hidden id="ake003" name="ake003" />
			<powersi:hidden id="ake005" name="ake005" />
			<powersi:textfield id="ake006" name="ake006" label="名称" disabled="true" />
			<powersi:hidden id="aka070" name="aka070" />
			<powersi:hidden id="aka074" name="aka074" />
			<powersi:hidden id="bka073" name="bka073" />
			<powersi:hidden id="aka057" name="aka057" />
			<powersi:hidden id="bkc106" name="bkc106" />
			<powersi:hidden id="aka063" name="aka063" />
			<powersi:textfield id="bka040" name="bka040" label="单价"
				onkeydown="dealKeyDown(this)" onkeyup="num(this)" />
			<powersi:textfield id="akc226" name="akc226" label="数量"
				onkeydown="dealKeyDown(this)" onkeyup="num(this)" />
			<powersi:textfield id="aae019" name="aae019" label="金额" readonly="true"
				onkeydown="dealKeyDown(this)" />
			<td>
				<label for="bkz103" class="textLabel">
					<span class="required requiredLabel">*</span>
					<span>超范围用药</span>
				</label>
			</td>
			<td>
				<select id="bkz103" name="bkz103" onkeydown="dealKeyDown(this)">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td>
			<powersi:textfield id="ake007" name="ake007" label="日期" mask="date"
				onchange="onchangeake007(this)" readonly="false" />
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<powersi:datagrid id="gridFeeDetail" height="93%" delayLoad="true" onAfterAddRow="scrollToRowLast"
		showReload="false" autoWidth="true" alternatingRow="true"
		checkbox="true" colDraggable="false" usePager="false"
		totalRender="renderTotal">
		<powersi:datagrid-column name="ake005" display="编码" width="10%" />
		<powersi:datagrid-column name="ake006" display="名称" width="18%" />
		<powersi:datagrid-column name="bka073" display="厂家" width="16%" />
		<powersi:datagrid-column name="aka070" display="剂型" width="6%" />
		<powersi:datagrid-column name="aka074" display="规格" width="6%" />
		<powersi:datagrid-column name="bka040" display="单价" width="6%" />
		<powersi:datagrid-column name="akc226" display="数量" width="6%" />
		<powersi:datagrid-column name="aae019" display="金额" width="6%" />
		<powersi:datagrid-column name="ake007" display="日期" width="8%" />
		<powersi:datagrid-column name="bkz103" display="超范围用药" width="6%" render="renderBkz103"/>
		<powersi:datagrid-column name="aka057" display="先自付比例" width="6%" />
	</powersi:datagrid>
	<powersi:hidden id="feeinfo" name="feeinfo" />
</powersi:panelbox>