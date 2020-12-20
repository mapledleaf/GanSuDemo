<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="医疗费用明细信息" description="医疗费用明细信息"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<script type="text/javascript" src="${rootPath}/pages/biz/medicare/diagnose/InputFee.js?20170615001"></script>
<script type="text/javascript">
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
					price += parseFloat(row['price']);
					usage += parseFloat(row['dosage']);
					money += parseFloat(row['money']);
					kinds[row['his_item_code']] = '';
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
				$("#his_item_code").val(data["ake005"]);//医疗机构三大目录编码(编码)
				$("#his_item_name").val(data["ake006"]);//医疗机构三大目录名称(名称)
				$("#model").val(data["aka070"]);//医院目录剂型(剂型)
				$("#standard").val(data["aka074"]);//医院目录规格(规格)
				$("#factory").val(data["bka073"]);//医院目录厂家(厂家)
				$("#price").val(data["bka040"]);//医院目录单价(单价)
				$("#item_code").val(data["ake001"]);
				$("#ake002").val(data["ake002"]);
				$("#price").focus();
				return false;
			}
		});
	});

	function insertfee() {
		if (powersi.isnull($("#his_item_name").val())) {
			alert("未录入药品");
			return;
		}

		if (powersi.isnull($("#money").val())) {
			alert("未录入金额");
			return;
		}

		if (parseFloat($("#money").val()) <= 0) {
			alert("金额不能小于等于0");
			return;
		}

		if (isNaN($("#money").val()) * 1) {
			alert("金额输入有误");
			$("#money").focus();
			return;
		}

		var bkc194 = $("#bkc194").val();
		var bkc109 = $("#bkc109").val();
		var aka065 = $("#aka065").val();
		var ake003 = $("#ake003").val();
		var his_item_code = $("#his_item_code").val();
		var his_item_name = $("#his_item_name").val();
		var factory = $("#factory").val();
		var model = $("#model").val();
		var standard = $("#standard").val();
		var price = formatDecimal($("#price").val());
		var dosage = formatDecimal($("#dosage").val());
		var input_ver = $("#money").val();
		var fee_date = $("#fee_date").val();
		var item_code = $("#item_code").val();
		var ake002 = $("#ake002").val();

		var jsonFee = {
			"bkc194" : bkc194,
			"bkc109" : bkc109,
			"aka065" : aka065,
			"ake003" : ake003,
			"his_item_code" : his_item_code,
			"his_item_name" : his_item_name,
			"factory" : factory,
			"model" : model,
			"standard" : standard,
			"price" : price,
			"dosage" : dosage,
			"money" : parseFloat(input_ver).toFixed(2) * 1.0000,
			"input_date" : fee_date,


			"item_code" : item_code,
			"ake002" : ake002
		};

		gridFeeDetail.add(jsonFee);

		$("#stext").focus();
		$("#stext").val("");
		$("#bkc194").val("");
		$("#bkc109").val("");
		$("#aka065").val("");
		$("#ake003").val("");
		$("#his_item_code").val("");
		$("#his_item_name").val("");
		$("#factory").val("");
		$("#model").val("");
		$("#standard").val("");
		$("#price").val("");
		$("#dosage").val("");
		$("#money").val("");
		//$("#aka057").val("");
		//$("#bkc106").val("");
		$("#item_code").val("");
		$("#ake002").val("");
		//$("#aka063").val("");

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
				+ sidx.value + "&ake007=" + $("#fee_date").val();
		$("#stext").combogrid("option", "url", actionUrl);
	}

	function onchangeake007(para) {
		var fee_date = $("#fee_date").val();
		var diagnose_date = $("#diagnose_date").val();
		if (fee_date < diagnose_date) {
			alert("费用日期不能小于门诊日期");
			return $("#fee_date").focus();
		}
		actionUrl = "${rootPath}/common/CommonManagerAction!chooseHospitalCatalogMatched.action?searchType="
				+ $("#sidx").val() + "&ake007=" + $("#fee_date").val();
		$("#stext").combogrid("option", "url", actionUrl);
	}

	function dealKeyDown(para) {
		if (window.event.keyCode == '13') {
			var filed_name = para.id;
			if ('price' == filed_name) {
				var price = $("#price").val();
				if (isNaN(price)) {
					alert("单价不能为字符");
					return $("#price").focus();
				}
				if (!powersi.isnull(price)) {
					if (parseFloat(price) <= 0) {
						alert("单价不能小于等于0");
						return $("#price").focus();
					}
				}
				return $("#dosage").focus();
			}
			if ('dosage' == filed_name) {
				var price = $("#price").val();
				var dosage = $("#dosage").val();
				if (isNaN(dosage)) {
					alert("数量不能为字符");
					return $("#dosage").focus();
				}
				if (!powersi.isnull(dosage)) {
					if (parseFloat(dosage) <= 0) {
						alert("数量不能小于等于0");
						return $("#dosage").focus();
					}
				}
				if (!powersi.isnull(dosage) && !powersi.isnull(price)) {
					$("#money").val((parseFloat(dosage) * parseFloat(price)).toFixed(2));
				}
				return $("#money").focus();
			}

			if('em_jsq' == filed_name){
				var em_jsq = $("#em_jsq").val();
				var st_xjzf = $("#st_xjzf").val();
				if(isNaN(em_jsq)){
					alert("金额不能为字符！");
					return $("#em_jsq").focus();
				}
				if(parseFloat(em_jsq)<=0){
					alert("金额不能小于0！");
					return $("#em_jsq").focus();
				}

				$("#st_out").val( (parseFloat(em_jsq)-parseFloat(st_xjzf)).toFixed(2) );
			}

			if ('money' == filed_name) {
				insertfee();
			}
		}
	}
	
	//新增行后跳转至最后一行
 	function scrollToRowLast(rowdata){
 		gridFeeDetail.scrollToRow(rowdata.__index);
 	}
</script>
<powersi:panelbox key="费用明细信息" allowCollapse="false">
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
			<powersi:hidden id="item_code" name="item_code" />
			<powersi:hidden id="ake002" name="ake002" />
			<powersi:hidden id="ake003" name="ake003" />
			<powersi:hidden id="his_item_code" name="his_item_code" />
			<powersi:textfield id="his_item_name" name="his_item_name" label="名称"
				disabled="true" />
			<powersi:hidden id="model" name="model" />
			<powersi:hidden id="standard" name="standard" />
			<powersi:hidden id="factory" name="factory" />
			<powersi:textfield id="price" name="price" label="单价" onkeyup="num(this)"
				onkeydown="dealKeyDown(this)" />
			<powersi:textfield id="dosage" name="dosage" label="数量" onkeyup="num(this)"
				onkeydown="dealKeyDown(this)" />
			<powersi:textfield id="money" name="money" label="金额" readonly="true"
				onkeydown="dealKeyDown(this)" />
			<powersi:textfield id="fee_date" name="fee_date" label="日期" mask="date"
				onchange="onchangeake007(this)" readonly="false" />
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<powersi:datagrid id="gridFeeDetail" height="93%" delayLoad="true" onAfterAddRow="scrollToRowLast"
		showReload="false" autoWidth="true" alternatingRow="true"
		checkbox="true" colDraggable="false" usePager="false"
		totalRender="renderTotal">
		<powersi:datagrid-column name="his_item_code" display="编码" width="10%" />
		<powersi:datagrid-column name="his_item_name" display="名称" width="18%" />
		<powersi:datagrid-column name="factory" display="厂家" width="16%" />
		<powersi:datagrid-column name="model" display="剂型" width="6%" />
		<powersi:datagrid-column name="standard" display="规格" width="6%" />
		<powersi:datagrid-column name="price" display="单价" width="6%" />
		<powersi:datagrid-column name="dosage" display="数量" width="6%" />
		<powersi:datagrid-column name="money" display="金额" width="6%" />
		<powersi:datagrid-column name="input_date" display="日期" width="8%" />
		<powersi:datagrid-column name="item_code" display="中心编码" width="8%" hide="true"/>
		<powersi:datagrid-column name="medi_item_type" display="中心编码类别" width="8%" hide="true"/>
        <powersi:datagrid-column name="ake003" display="中心编码类别" width="8%" hide="true"/>
	</powersi:datagrid>
	<powersi:hidden id="feeinfo" name="feeinfo" />
</powersi:panelbox>