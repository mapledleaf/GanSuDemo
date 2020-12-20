<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="医疗费用明细信息" description="医疗费用明细信息"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<script type="text/javascript">
	var centerId = '439900';
	var actionUrl = rootPath
			+ '/medicarebiz/FeeInfoAction!queryCatalog.action?centerid='
			+ centerId + '&searchType=py';
	jQuery(document).ready(function() {
		$("#stext").combogrid({
			minLength : 1,
			autoChoose : false,
			searchIcon : true,
			alternate : true,
			width : '600px',
			url : actionUrl,
			colModel : [ {
				'columnName' : 'ake003n',
				'width' : '10',
				'label' : '项目等级'
			}, {
				'columnName' : 'ake001',
				'width' : '20',
				'label' : '编码'
			}, {
				'columnName' : 'ake002',
				'width' : '30',
				'label' : '名称'
			}, {
				'columnName' : 'bka052n',
				'width' : '15',
				'label' : '剂型'
			}, {
				'columnName' : 'bke213',
				'width' : '10',
				'label' : '规格'
			}, {
				'columnName' : 'aae013',
				'width' : '15',
				'label' : '备注'
			} ],
			select : function(event, ui) {
				var data = ui.item;
				$("#ake001").val(data["ake001"]);//社保ID
				$("#ake006").val(data["ake002"]);//社保目录名称 
				$("#aka063").val(data["aka063"]);//项目类别
				$("#ake003").val(data["ake003"]);//收费等级
				$("#bka052").val(data["bka052"]);//剂型
				$("#ake003").val(data["ake003"]);//目录类别
				$("#bka054").val(data["bke213"]);//规格
				$("#bka054").val(data["bke213"]);//规格
				$("#aka069").val(data["aka057"]);//先自付比例(数字)
				$("#bke215").val(data["bke215"]);//先自付比例
				$("#bka056").focus();
				return false;
			},
			showGrid : function() {
				$('#dw_cash_hospital').hide();
			},
			hideGrid : function() {
				$('#dw_cash_hospital').show();
			}
		});
		$("#querykey").focus();
	});

	//取当前时间，格式为,yyyy-mm-dd hh:mm:ss
	function GetDate() {
		var d, s;
		d = new Date();
		s = d.getFullYear() + "-"; //取年份
		if (d.getMonth() + 1 < 10) {
			s = s + "0" + (d.getMonth() + 1) + "-";//取月份
		} else {
			s = s + (d.getMonth() + 1) + "-";//取月份
		}

		if (d.getDate() < 10) {
			s += "0" + d.getDate() + " "; //取日期
		} else {
			s += d.getDate() + " "; //取日期
		}
		s += d.getHours() + ":"; //取小时
		if (d.getMinutes() < 10) {
			s += "0" + d.getMinutes() + ":"; //取分
		} else {
			s += d.getMinutes() + ":";
		}
		if (d.getSeconds() < 10) {
			s += "0" + d.getSeconds();
		} else {
			s += d.getSeconds(); //取秒
		}
		return (s);
	}

	//删除费用信息
	function deleteFee() {
		var ll_currow;
		ll_currow = document.all.dw_cash_hospital.getrow();
		if (ll_currow > 0) {
			if (confirm("是否删除当前记录?") == true) {
				document.all.dw_cash_hospital.deleterow(ll_currow);
			}
		}
	}

	function insert_dw(para) {

		if (event.keyCode == '13') {
			try {
				var e = window.event || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					e.stopPropagation();
				}
				event.returnValue = false;
			} catch (ex) {
			}

			var filed_name = para.id;
			if ('bka058' == filed_name) {

				if ($("#ake001").val() == '') {
					alert("未录入药品！");
					return;
				}
				if ($("#bka058").val() == '') {
					alert("未录入金额！");
					return;
				}
				
				if(parseFloat($("#bka058").val())<=0){
					alert("金额不能小于0！");
					return;
				}
				
				var ll_row = 0;
				if (isNaN($("#bka058").val()) * 1) {
					alert("金额输入有误！");
					document.all.bka058.focus();
					return;
				}
				if ($("#aka063").find("option:selected").val() == '017'
						&& $("#akc226").val() == 0) {
					alert('床位费必须录入数量！')
					document.all.akc226.focus();
					return;
				}

				var input_ver = $("#bka058").val();
				item_code = $("#ake001").val();
				for (i = 0; i <= document.all.dw_cash_hospital.rowcount(); i++) {
					item_code_temp = document.all.dw_cash_hospital.getitemstring(
							i, "ake001", 0, true);
				}
				if (ll_row <= 0) {
					ll_row = document.all.dw_cash_hospital.InsertRow(0);
				}

				var ake003n = $("#ake003").find("option:selected").text();
				var ake003c = $("#ake003").find("option:selected").val();
				var aka063n = $("#aka063").find("option:selected").text();
				var aka063c = $("#aka063").find("option:selected").val();
				var ake003n = $("#ake003").find("option:selected").text();
				var ake003c = $("#ake003").find("option:selected").val();

				if ($("#akc226").val() == '') {
					document.all.dw_cash_hospital.setitem(ll_row, "akc226",
							parseFloat("0.00")); //数量(akc226)
				} else {
					document.all.dw_cash_hospital.setitem(ll_row, "akc226",
							parseFloat($("#akc226").val())); //数量(akc226)
				}

				document.all.dw_cash_hospital.setitem(ll_row, "ake003", ake003c); //目录等级(ake003)
				document.all.dw_cash_hospital.setitem(ll_row, "ake003n", ake003n); //目录等级名称(ake003)
				document.all.dw_cash_hospital.setitem(ll_row, "aka063", aka063c); //类型(aka063)
				document.all.dw_cash_hospital.setitem(ll_row, "aka063n", aka063n); //类型名称(aka063)
				/* document.all.dw_cash_hospital.setitem(ll_row, "ake003", ake003c);//三大目录类别 (ake003)
				document.all.dw_cash_hospital.setitem(ll_row, "ake003n", ake003n);//三大目录类别 名称(ake003) */
				document.all.dw_cash_hospital.setitem(ll_row, "ake006", $("#ake006").val()); //医疗机构目录名称(ake006)
				document.all.dw_cash_hospital.setitem(ll_row, "bka052", $("#bka052").val()); //剂型(bka052)

				document.all.dw_cash_hospital.setitem(ll_row, "bka054", $("#bka054").val()); //规格(bka054)
				document.all.dw_cash_hospital.setitem(ll_row, "bka058",input_ver * 1.0000); //金额  (aae019)
				
				document.all.dw_cash_hospital.setitem(ll_row, "bka056",parseFloat($("#bka056").val())); //单价
				document.all.dw_cash_hospital.setitem(ll_row, "bka057",parseFloat($("#bka057").val())); //用量

				document.all.dw_cash_hospital.setitem(ll_row, "aka069", $("#aka069").val());//自付比例(aka069) 
				document.all.dw_cash_hospital.setitem(ll_row, "ake001", $("#ake001").val());//社保目录 id(aaz277)

				document.all.dw_cash_hospital.setitem(ll_row, "bke215", $("#bke215").val());//自付类型 defray_type
				document.all.dw_cash_hospital.setitem(ll_row, "bka051", GetDate());//费用发生时间
				document.all.dw_cash_hospital.accepttext();
				document.all.dw_cash_hospital.scrolltorow(ll_row);
				document.all.dw_cash_hospital.SetRow(ll_row);
				$("#stext").focus();
				$("#stext").val("");

				$("#ake006").val("");
				$("#aka063").val("");
				$("#bka052").val("");
				$("#bka054").val("");
				$("#bka056").val("");
				$("#bka057").val("");
				$("#bka058").val("");
				$("#aka069").val("");

				$("#btnSave").attr("disabled", true);
				$("#btnCalc0").attr("disabled", false);
			}
		}
	}

	function changeSearchType(sel) {
		actionUrl = rootPath
				+ '/medicarebiz/FeeInfoAction!queryCatalog.action?centerid='
				+ centerId + '&searchType=' + sel.value + '&bka035=0';
		$("#stext").combogrid("option", "url", actionUrl);

	}
	function selectall(obj) {
		/* obj.select(); */
	}

	function checkbiz() {
		if ($("#aac001").val() == '' || $("#aac001").val() == -1) {
			alert("请正确录入业务信息后，再录入费用。");
			return $("#queryString").focus();
		}
	}

	function dealkeydown(para) {
		if (event.keyCode == '13') {
			try {
				var e = window.event || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					e.stopPropagation();
				}
				event.returnValue = false;
			} catch (ex) {
			}

			var filed_name = para.id;

			if ('bka056' == filed_name) {
				var bka056 = $("#bka056").val();
				
				if(isNaN(bka056)){
					alert("单价不能为字符！");
					return $("#bka056").focus();
				}
				
				if (bka056 !== '') {
					if(parseFloat(bka056)<=0){
						alert("单价不能小于0！");
						return $("#bka056").focus();
					}
				}
				return $("#bka057").focus();
			}
			if ('bka057' == filed_name) {
				var bka056 = $("#bka056").val();
				var bka057 = $("#bka057").val();

				if(isNaN(bka057)){
					alert("数量不能为字符！");
					return $("#bka057").focus();
				}
				
				if (bka057 !== '') {
					if(parseFloat(bka057)<=0){
						alert("数量不能小于0！");
						return $("#bka057").focus();
					}
				}
				
				if (bka057 !== '' && bka056 !== '') {
					$("#bka058").val(parseFloat(bka057) * parseFloat(bka056));
				}
				return $("#bka058").focus();
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
		}
	}
</script>
<s:groupbox title="费用信息">
	<s:editorlayout cols="14">
		<tr>
			<td><select id="sidx" onchange='changeSearchType(this)'
				style="width: auto;">
					<option value="py">首拼码</option>
					<option value="wb">五笔码</option>
					<option value="name">名称</option>
					<option value="code">编码</option>
			</select></td>
			<td colspan="2"><input type="text" class="text" id="stext" value="" onfocus="checkbiz()" maxlength="30" style="width: 90%;" /></td>
			<s:hidden id="ake001" key="社会目录id" name="ake001"/>
			<s:textfield id="ake006" label="药品名称" colspan="2" disabled="true" />
			<s:codeselect codeType="aka063" id="aka063" key="aka063" disabled="true" />
			<%-- <s:codeselect codeType="ake003" id="ake003" key="ake003" disabled="true" />
			<s:codeselect codeType="ake003" id="ake003" key="ake003" disabled="true" /> --%>
		</tr>
		<tr>
			<s:codeselect codeType="bka052" id="bka052" key="剂型" readonly="true" />
			<!--<s:textfield id="bka052" key="剂型" /> -->
			<s:textfield id="bka054" key="规格" readonly="true" name="bka054"/>
			<s:textfield id="bka056" key="单价" onkeydown="dealkeydown(this)" name="bka056" />
			<s:textfield id="bka057" key="数量" onkeydown="dealkeydown(this)" name="bka057" />
			<s:textfield id="bka058" onfocus="selectall(bka058);" key="金额" onkeydown="insert_dw(this)" name="bka058"/>
			<s:textfield id="aka069" key="自付比例" readonly="true" name="bka069"/>
			<s:hidden id="bke215" key="支付比例" name="bke215"/>
			<td>
			<s:button key="button_ok" id="btn_enter" onclick="insert_dw()" />
			</td><td>
			<s:button key="button_delete" id="btn_delete" onclick="deleteFee()" />
			</td>
		</tr>
	</s:editorlayout>
</s:groupbox>


	<table class="table_frame">
		<div style="height: 130px;">
			<s:datawindow id="dw_cash_hospital" value="datafee" height="150" groupCalc="groupCalc" name="dw_cash_hospital" clicked="sortClicked;selectClicked;checkboxClicked;" />
		</div>
	</table>
	<s:hidden id="feeinfo" name="feeinfo" />

