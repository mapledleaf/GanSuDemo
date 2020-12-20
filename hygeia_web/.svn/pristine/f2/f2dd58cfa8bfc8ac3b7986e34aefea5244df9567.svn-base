<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="上传预约挂号停诊异动信息" description="上传预约挂号停诊异动信息"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<powersi:panelbox key="预约挂号停诊异动信息">
	<powersi:panelbox-toolbar>
		<powersi:button id="btn_enter" key="button_ok" onclick="insertOrderErrorStopInfo()" />
		<powersi:button id="btn_delete" key="button_delete" onclick="deleteOrderErrorStopInfo()" />
		<powersi:button id="btSaveOrderErrorStopInfo" label="上传信息" onclick="saveOrderErrorStopInfo()" />
	</powersi:panelbox-toolbar>
	<powersi:editorlayout>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_bka508" name="appointmentdto.bka508"  key="bka508" required="true"  label="预约号"/>
			<powersi:textfield id="e_bac003" name="appointmentdto.bac003"  key="bac003" required="true"  label="病人姓名"/>
			<%-- <powersi:textfield id="bka051" name="bka051" label="日期" mask="date"
				onchange="onchangebka051(this)" readonly="false" required="true" /> --%>
			<powersi:textfield id="e_bac002" name="appointmentdto.bac002"  key="bac002" required="true" label="病人身份证"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:codeselect id="e_aaz307" name="inHospitalDTO.aaz307"
						label="医院科室" cssClass="select2" list="#request.bka019List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectbka503_e()" showValue="true" />
			<powersi:codeselect id="e_bka503" name="inHospitalDTO.bka503"
						label="医院医师" cssClass="select2" list="#request.bka503List"
						headerKey="" headerValue="请选择..." required="true"/>
			<%-- <powersi:textfield id="e_bka503" name="appointmentdto.bka503"  key="bka503"  required="true"  label="预约医生编号"/>
			<powersi:textfield id="e_aac003" name="appointmentdto.aac003"  key="aac003"  required="true"  label="预约医生姓名"/> --%>
			<powersi:textfield id="e_bae030" name="appointmentdto.bae030"  key="bae030"  required="true"  label="预约就诊日期" mask="date" format="dateFmt:'yyyy-MM-dd'"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="e_bka030" name="appointmentdto.bka030"  key="bka030"  required="true"  label="预约就诊接诊时间段"/>
			<powersi:textarea id="e_aae013" name="appointmentdto.aae013" key="aae013" label="说明"></powersi:textarea>
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	
	<powersi:datagrid id="e_grid" formId="orderErrorStopInfoform" delayLoad="true"
		showReload="false" autoWidth="true" enabledSort="false"
		alternatingRow="true" checkbox="true" colDraggable="false"
		pageSize="30">
		<powersi:datagrid-column name="bka508" display="预约号" width="10%" />
		<powersi:datagrid-column name="bac003" display="病人姓名" width="10%" />
		<powersi:datagrid-column name="bac002" display="病人身份证" width="15%" />
		<powersi:datagrid-column name="bka503" display="医生编号" width="10%" />
		<powersi:datagrid-column name="aac003" display="医生姓名" width="10%" />
		<powersi:datagrid-column name="bae030" display="预约就诊日期" width="8%" />
		<powersi:datagrid-column name="bka030" display="预约就诊接诊时间段" width="15%" />
		<powersi:datagrid-column name="aae013" display="说明" width="20%"/>
	</powersi:datagrid>
</powersi:panelbox>

<powersi:form id="inputOrderErrorStopInfo" namespace="/inhospital" action="AppointmentManagerAction!saveAppointmentInfos.action">
	<powersi:hidden id="ordererrorstopinfo" name="ordererrorstopinfo" />
</powersi:form>

<script type="text/javascript">
/*加载医保医师*/
function selectbka503_e() {
	//入院科室
	var e_aaz307 = $("#e_aaz307").val();
	if (powersi.isnull(e_aaz307)) {
		return;
	}
	
	$("#e_bka503").empty();
	$("#e_bka503").append("<option value='' >" + "请选择..." + "</option>");
	$("#e_bka503").change();
	
	postJSON(
			"${rootPath}/inhospital/AppointmentManagerAction!loadBka503List.action",
			{
				"doctorSourceInfoDTO.aaz307" : e_aaz307
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
					$("#e_bka503").append(a.join(''));
				}
				if (!powersi.isnull(json.message)) {
					alert(json.message);
				}
			});
}

	function insertOrderErrorStopInfo() {
		var bka503Obj = document.getElementById("e_bka503"); 
		var index =bka503Obj.selectedIndex;
		var bka503Text = bka503Obj.options[index].text;
		var bka503Value = bka503Obj.options[index].value;
		
		if (powersi.isnull($("#e_bka508").val())) {
			alert("未录入预约号");
			return;
		}
		if (powersi.isnull($("#e_bac003").val())) {
			alert("未录入病人姓名");
			return;
		}
		if (powersi.isnull($("#e_bac002").val())) {
			alert("未录入病人身份证");
			return;
		}
		if (powersi.isnull($("#e_bka503").val())) {
			alert("未录入预约医生编号");
			return;
		}
	/* 	if (powersi.isnull($("#e_aac003").val())) {
			alert("未录入预约医生姓名");
			return;
		} */
		if (powersi.isnull($("#e_bae030").val())) {
			alert("未录入预约就诊日期");
			return;
		}
		if (powersi.isnull($("#e_bka030").val())) {
			alert("未录入预约就诊接诊时间段");
			return;
		}
		
		var bka503Arry = bka503Value.split("|"); 
		var bka508 = $("#e_bka508").val();
		var bac003 = $("#e_bac003").val();
		var bac002 = $("#e_bac002").val();
		var bka503 = bka503Arry[0];
		var aac003 = bka503Text;
		var bae030 = $("#e_bae030").val();
		var bka030 = $("#e_bka030").val();
		var aae013 = $("#e_aae013").val();
		
		
		var jsonOrderErrorStopInfo = {
				"bka508" : bka508,
				"bac003" : bac003,
				"bac002" : bac002,
				"bka503" : bka503,
				"aac003" : aac003,
				"bae030" : bae030,
				"bka030" : bka030,
				"aae013" : aae013
		};
		e_grid.add(jsonOrderErrorStopInfo);
		$("#e_bka508").focus();
		$("#e_bka508").val("");
		$("#e_bac003").val("");
		$("#e_bac002").val("");
		$("#e_bka503").val("");
		$("#e_aac003").val("");
		$("#e_bae030").val("");
		$("#e_bka030").val("");
		$("#e_aae013").val("");
	}
	
	function deleteOrderErrorStopInfo() {
		if (typeof (e_grid.getSelectedRows()) == "undefined"
				|| e_grid.getSelectedRows() == null
				|| e_grid.getSelectedRows() == "") {
			alert("请选择要删除的信息");
			return;
		}
		if (confirm("是否删除已选择的信息?") == true) {
			e_grid.deleteSelectedRow();
		}
	}
	
	/*上传信息*/
	function saveOrderErrorStopInfo() {
		if (e_grid.getRowsCount() <= 0) {
			alert("请先录入信息");
			return;
		}
		$("#btSaveOrderErrorStopInfo").attr("disabled", "disabled");
		var ordererrorstopinfo = e_grid.getData();
		ordererrorstopinfo = powersi.tostring(ordererrorstopinfo);
		$("#ordererrorstopinfo").val(encodeURI(ordererrorstopinfo));
		var saveItemData = $("#inputOrderErrorStopInfo").serialize();
		postJSON(
				"${rootPath}/inhospital/AppointmentManagerAction!saveAppointmentExcepInfos.action",
				saveItemData, importSaveInfo);
	}
	
	function importSaveInfo(json) {
		if (!checkJSONResult(json)) {
			$("#btSaveOrderErrorStopInfo").removeAttr("disabled");
			return;
		}
		if (!powersi.isnull(json.message)) {
			alert(json.message);
		}
	}
</script>
