<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="上传预约挂号(或取消预约挂号)结果" description="上传预约挂号(或取消预约挂号)结果"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:panelbox key="查询条件">
	<powersi:editorlayout>
	        <powersi:editorlayout-row>
		        <powersi:codeselect codeType="bka504" id="bka504" name="q_bka504" key="查询类型"></powersi:codeselect>
		        <powersi:textfield id="bac002" name="q_bac002"  placeholder="请输入病人身份证号查询"  label="病人身份证"/>
				<powersi:textfield id="aac003" name="q_aac003" placeholder="请输入医生姓名查询"  label="医生名称"/>
	        </powersi:editorlayout-row>
	        <powersi:buttons>						
	            <input type="button" name="query" value="查询" class="button" onclick="selectAppointment();" />
		        <input type="button" name="clear" value="重置" class="button" onclick="clearall();" />
	        </powersi:buttons>	
		</powersi:editorlayout>
	
</powersi:panelbox>

<powersi:panelbox key="预约挂号(或取消预约挂号)结果">
	<%-- <powersi:panelbox-toolbar>
		<powersi:button id="btn_enter" key="button_ok" onclick="insertOrderInfo()" />
		<powersi:button id="btn_delete" key="button_delete" onclick="deleteOrderInfo()" />
		<powersi:button id="btSaveOrderInfo" label="上传信息" onclick="saveOrderInfo()" />
		<powersi:button id="btSelectFee" label="查询已保存" onclick="selectAppointment()" />
	</powersi:panelbox-toolbar>
	<powersi:editorlayout>
		<powersi:editorlayout-row>
			<powersi:codeselect codeType="bka504" id="a_bka504" name="appointmentdto.bka504" key="类型" required="true"></powersi:codeselect>
			<powersi:textfield id="a_bka508" name="appointmentdto.bka508"  key="bka508" required="true"  label="预约号"/>
			<powersi:textfield id="a_bac003" name="appointmentdto.bac003"  key="bac003" required="true"  label="病人姓名"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="a_bac002" name="appointmentdto.bac002"  key="bac002" required="true" label="病人身份证"/>
			<powersi:textfield id="a_aae005" name="appointmentdto.aae005"  key="aae005" required="true" label="病人联系电话"/>
			<powersi:codeselect codeType="aac004" id="a_bac004" name="appointmentdto.bac004" key="病人性别"></powersi:codeselect>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="a_aae006" name="appointmentdto.aae006"  key="aa006"  label="病人联系地址"/>
			<powersi:codeselect id="a_aaz307" name="inHospitalDTO.aaz307"
						label="医院科室" cssClass="select2" list="#request.bka019List"
						headerKey="" headerValue="请选择..." required="true"
						onchange="selectbka503_a()" showValue="true" />
			<powersi:codeselect id="a_bka503" name="inHospitalDTO.bka503"
						label="医院医师" cssClass="select2" list="#request.bka503List"
						headerKey="" headerValue="请选择..." required="true"/>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:textfield id="a_bae030" name="appointmentdto.bae030"  key="bae030"  required="true"  label="预约就诊日期" mask="date" format="dateFmt:'yyyy-MM-dd'"/>
			<powersi:textfield id="a_bka030" name="appointmentdto.bka030"  key="bka030"  required="true"  label="预约就诊接诊时间段"/>
			<powersi:textarea id="a_aae013" name="appointmentdto.aae013" key="aae013" label="说明"></powersi:textarea>
		</powersi:editorlayout-row>
	</powersi:editorlayout> --%>
	
	<powersi:datagrid id="a_grid"  exportFileName="'医院预约挂号查询'"  height="100%"  useCount="false"  checkbox="true" 
       		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true"  pageSize="20">
		<powersi:datagrid-column name="bka504" code="bka504" display="业务类别" width="10%" render="renderDictionary"/>
		<powersi:datagrid-column name="bka508" display="预约号" width="10%" />
		<powersi:datagrid-column name="bac003" display="病人姓名" width="10%" />
		<powersi:datagrid-column name="bac002" display="病人身份证" width="15%" />
		<powersi:datagrid-column name="aae005" display="病人联系电话" width="15%" />
		<powersi:datagrid-column name="bac004" code="aac004" display="病人性别" width="6%"  render="renderDictionary"/>
		<powersi:datagrid-column name="aae006" display="病人联系地址" width="20%" />
		<powersi:datagrid-column name="bka503" display="医生编号" width="10%" />
		<powersi:datagrid-column name="aac003" display="医生姓名" width="10%" />
		<powersi:datagrid-column name="bae030" display="预约就诊日期" width="8%" />
		<powersi:datagrid-column name="bka030" display="预约就诊接诊时间段" width="15%" />
		<powersi:datagrid-column name="aae013" display="说明" width="20%"/>
	</powersi:datagrid>
</powersi:panelbox>

<powersi:form id="inputOrderInfo" namespace="/inhospital" action="AppointmentManagerAction!saveAppointmentInfos.action">
	<powersi:hidden id="orderinfo" name="orderinfo" />
</powersi:form>

<script type="text/javascript">
/*加载医保医师*/
function selectbka503_a() {
	//入院科室
	var a_aaz307 = $("#a_aaz307").val();
	if (powersi.isnull(a_aaz307)) {
		return;
	}
	
	$("#a_bka503").empty();
	$("#a_bka503").append("<option value='' >" + "请选择..." + "</option>");
	$("#a_bka503").change();
	
	postJSON(
			"${rootPath}/inhospital/AppointmentManagerAction!loadBka503List.action",
			{
				"doctorSourceInfoDTO.aaz307" : a_aaz307
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
					$("#a_bka503").append(a.join(''));
				}
				if (!powersi.isnull(json.message)) {
					alert(json.message);
				}
			});
}


	function insertOrderInfo() {
		var bka503Obj = document.getElementById("a_bka503"); 
		var index =bka503Obj.selectedIndex;
		var bka503Text = bka503Obj.options[index].text;
		var bka503Value = bka503Obj.options[index].value;
		
		if (powersi.isnull($("#a_bka508").val())) {
			alert("未录入预约号");
			return;
		}
		if (powersi.isnull($("#a_bac003").val())) {
			alert("未录入病人姓名");
			return;
		}
		if (powersi.isnull($("#a_bac002").val())) {
			alert("未录入病人身份证");
			return;
		}
		if (powersi.isnull($("#a_bka503").val())) {
			alert("未录入预约医生编号");
			return;
		}
		/* if (powersi.isnull($("#a_aac003").val())) {
			alert("未录入预约医生姓名");
			return;
		} */
		if (powersi.isnull($("#a_bae030").val())) {
			alert("未录入预约就诊日期");
			return;
		}
		if (powersi.isnull($("#a_bka030").val())) {
			alert("未录入预约就诊接诊时间段");
			return;
		}
		
		var bka503Arry = bka503Value.split("|"); 
		
		var bka504 = $("#a_bka504").val();
		var bka508 = $("#a_bka508").val();
		var bac003 = $("#a_bac003").val();
		var bac002 = $("#a_bac002").val();
		var aae005 = $("#a_aae005").val();
		var bac004 = $("#a_bac004").val();
		var aae006 = $("#a_aae006").val();
		var bka503 = bka503Arry[0];
		var aac003 = bka503Text;
		var bae030 = $("#a_bae030").val();
		var bka030 = $("#a_bka030").val();
		var aae013 = $("#a_aae013").val();
		
		/* var bka504_name =""; 
		if(bka504 == "1"){
			bka504_name="预约挂号";
		}else{
			bka504_name = "取消预约挂号";
		}
		
		var bac004_name="";
		if(bac004 == "1"){
			bac004_name = "男";
		}else{
			bac004_name ="女";
		} */
		
		var jsonOrderInfo = {
				"bka504" : bka504,
				"bka508" : bka508,
				"bac003" : bac003,
				"bac002" : bac002,
				"aae005" : aae005,
				"bac004" : bac004,
				"aae006" : aae006,
				"bka503" : bka503,
				"aac003" : aac003,
				"bae030" : bae030,
				"bka030" : bka030,
				"aae013" : aae013
		};
		a_grid.add(jsonOrderInfo);
		$("#a_bka508").focus();
		$("#a_bka508").val("");
		$("#a_bac003").val("");
		$("#a_bac002").val("");
		$("#a_aae005").val("");
		$("#a_aae006").val("");
		$("#a_bka503").val("");
		$("#a_aac003").val("");
		$("#a_bae030").val("");
		$("#a_bka030").val("");
		$("#a_aae013").val("");
	}
	
	function deleteOrderInfo() {
		if (typeof (a_grid.getSelectedRows()) == "undefined"
				|| a_grid.getSelectedRows() == null
				|| a_grid.getSelectedRows() == "") {
			alert("请选择要删除的信息");
			return;
		}
		if (confirm("是否删除已选择的信息?") == true) {
			a_grid.deleteSelectedRow();
			$("#btSaveOrderInfo").removeAttr("disabled");
		}
	}
	
	/*上传信息*/
	function saveOrderInfo() {
		if (a_grid.getRowsCount() <= 0) {
			alert("请先录入信息");
			return;
		}
		$("#btSaveOrderInfo").attr("disabled", "disabled");
		var orderinfo = a_grid.getData();
		orderinfo = powersi.tostring(orderinfo);
		$("#orderinfo").val(encodeURI(orderinfo));
		var saveItemData = $("#inputOrderInfo").serialize();
		postJSON(
				"${rootPath}/inhospital/AppointmentManagerAction!saveAppointmentInfos.action",
				saveItemData, importSaveInfo);
	}
	
	function importSaveInfo(json) {
		if (!checkJSONResult(json)) {
			$("#btSaveOrderInfo").removeAttr("disabled");
			return;
		}
		if (!powersi.isnull(json.message)) {
			alert(json.message);
		}
	}
	
	/*查询已保存的预约挂号*/
	function selectAppointment() {
		var bka504=$("#bka504").val();
		var bac002=$("#bac002").val();
		var aac003=$("#aac003").val();
		postJSON("${rootPath}/inhospital/AppointmentManagerAction!queryAppointmentInfos.action",
				{'process_tag':'save',
				  "bka504":bka504,
				  "bac002":bac002,
				  "aac003":aac003,
				  },
				function(json){
					if(!checkJSONResult(json)){
					    return;
				    }
		    	//加载结果集到datagrid
				a_grid.loadData(json.data);
				$("#btSaveOrderInfo").attr("disabled", "disabled");
		});
	}

</script>