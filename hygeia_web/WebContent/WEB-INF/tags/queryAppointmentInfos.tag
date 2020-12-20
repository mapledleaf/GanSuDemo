<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="预约挂号信息维护" description="预约挂号信息维护"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>


<script type="text/javascript">
function query()
{
	var bac002 = document.getElementById("q_bac002").value;
	var aac003 = document.getElementById("q_aac003").value;
	var bka504 = $("#q_bka504").val();
	var bae029 = $("#q_bae029").val();
	postJSON("${rootPath}/inhospital/AppointmentManagerAction!queryAppointmentInfos.action",{"bac002": bac002,"aac003":aac003,"bka504":bka504,"bae029":bae029}, 
			function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
	    	//加载结果集到datagrid
			grid_Appointment_Query.loadData(json.data);
	});
}

function clearall(){
	$('#q_bac002').val("");
	$('#q_aac003').val("");
	$('#q_bka504').val("");
}

function renderOperate1(row, index, value) {
	var a = [];
	a.push('<input type="button" value="取消" class="linkButton"');
	a.push(' onclick="cancelYygh(');
	a.push(index);
	a.push(')"');
	if (row['bka504'] == '2') {
		a.push(' disabled="disabled"');
	}
	a.push(" />"); 
	
	a.push("&nbsp;&nbsp;");
	
	a.push('<input type="button" value="停诊" class="linkButton"');
	a.push(' onclick="stopYygh(');
	a.push(index);
	a.push(')"');
	if (row['bka504'] == '2' || row['bae029'] == '1') {
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	
	a.push('<input type="button" value="改诊" class="linkButton"');
	a.push(' onclick="changeYygh(');
	a.push(index);
	a.push(')"');
	if (row['bka504'] == '2' || row['bae029'] == '2') {
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	return a.join('');
}

function cancelYygh(index){
	var row = grid_Appointment_Query.getRow(index);
	var bac003 = row['bac003'];
	var bac002 = row['bac002'];
	var bac004 = row['bac004'];
	var aae005 = row['aae005'];
	var aae006 = row['aae006'];
	var aaz307 = row['aaz307'];
	var bka503 = row['bka503'];
	var aac003 = row['aac003'];
	var aae030 = row['aae030'];
	var bae031 = row['bae031'];
	var bae032 = row['bae032'];
	var bka508 = row['bka508'];
	
	bac003 = encodeURI(encodeURI(bac003));
	aae006 = encodeURI(encodeURI(aae006));
	aac003 = encodeURI(encodeURI(aac003));
	bae031 = encodeURI(encodeURI(bae031));
	
	popupDialog({
		url: "${rootPath}/inhospital/AppointmentManagerAction!initSaveAppointmentInfo.action?appointmentInfoDTO.bac003="+bac003
		+"&appointmentInfoDTO.bac002="+bac002
		+"&appointmentInfoDTO.bac004="+bac004
		+"&appointmentInfoDTO.aae005="+aae005
		+"&appointmentInfoDTO.aae006="+aae006
		+"&appointmentInfoDTO.aaz307="+aaz307
		+"&appointmentInfoDTO.bka503="+bka503
		+"&appointmentInfoDTO.aac003="+aac003
		+"&appointmentInfoDTO.aae030="+aae030
		+"&appointmentInfoDTO.bae031="+bae031
		+"&appointmentInfoDTO.bae032="+bae032
		+"&appointmentInfoDTO.bka508="+bka508
		,
		onClosed: function() {
			var ret = this.returnValue;
			grid_Appointment_Query.reload(true);
			query();
		}
	}, screen.height, screen.width);
}
	
function stopYygh(index){
	var row = grid_Appointment_Query.getRow(index);
	var bac003 = row['bac003'];
	var bac002 = row['bac002'];
	var bac004 = row['bac004'];
	var aae005 = row['aae005'];
	var aae006 = row['aae006'];
	var aaz307 = row['aaz307'];
	var bka503 = row['bka503'];
	var aac003 = row['aac003'];
	var aae030 = row['aae030'];
	var bae031 = row['bae031'];
	var bae032 = row['bae032'];
	var bka508 = row['bka508'];
	var bae029 = '1';
	
	bac003 = encodeURI(encodeURI(bac003));
	aae006 = encodeURI(encodeURI(aae006));
	aac003 = encodeURI(encodeURI(aac003));
	bae031 = encodeURI(encodeURI(bae031));
	
	popupDialog({
		url: "${rootPath}/pages/biz/medicare/inhospital/saveAppointmentException.jsp?appointmentInfoDTO.bac003="+bac003
		+"&appointmentInfoDTO.bac002="+bac002
		+"&appointmentInfoDTO.bac004="+bac004
		+"&appointmentInfoDTO.aae005="+aae005
		+"&appointmentInfoDTO.aae006="+aae006
		+"&appointmentInfoDTO.aaz307="+aaz307
		+"&appointmentInfoDTO.bka503="+bka503
		+"&appointmentInfoDTO.aac003="+aac003
		+"&appointmentInfoDTO.aae030="+aae030
		+"&appointmentInfoDTO.bae031="+bae031
		+"&appointmentInfoDTO.bae032="+bae032
		+"&appointmentInfoDTO.bka508="+bka508
		+"&appointmentInfoDTO.bae029="+bae029
		,
		onClosed: function() {
			var ret = this.returnValue;
			grid_Appointment_Query.reload(true);
			query();
		}
	}, screen.height, screen.width);
}

function changeYygh(index){
	var row = grid_Appointment_Query.getRow(index);
	var bac003 = row['bac003'];
	var bac002 = row['bac002'];
	var bac004 = row['bac004'];
	var aae005 = row['aae005'];
	var aae006 = row['aae006'];
	var aaz307 = row['aaz307'];
	var bka503 = row['bka503'];
	var aac003 = row['aac003'];
	var aae030 = row['aae030'];
	var bae031 = row['bae031'];
	var bae032 = row['bae032'];
	var bka508 = row['bka508'];
	var bae029 = '2';
	
	bac003 = encodeURI(encodeURI(bac003));
	aae006 = encodeURI(encodeURI(aae006));
	aac003 = encodeURI(encodeURI(aac003));
	bae031 = encodeURI(encodeURI(bae031));
	
	popupDialog({
		url: "${rootPath}/pages/biz/medicare/inhospital/saveAppointmentException.jsp?appointmentInfoDTO.bac003="+bac003
		+"&appointmentInfoDTO.bac002="+bac002
		+"&appointmentInfoDTO.bac004="+bac004
		+"&appointmentInfoDTO.aae005="+aae005
		+"&appointmentInfoDTO.aae006="+aae006
		+"&appointmentInfoDTO.aaz307="+aaz307
		+"&appointmentInfoDTO.bka503="+bka503
		+"&appointmentInfoDTO.aac003="+aac003
		+"&appointmentInfoDTO.aae030="+aae030
		+"&appointmentInfoDTO.bae031="+bae031
		+"&appointmentInfoDTO.bae032="+bae032
		+"&appointmentInfoDTO.bka508="+bka508
		+"&appointmentInfoDTO.bae029="+bae029
		,
		onClosed: function() {
			var ret = this.returnValue;
			grid_Appointment_Query.reload(true);
			query();
		}
	}, screen.height, screen.width);
}


</script>

	<powersi:groupbox title="查询条件">
		<powersi:editorlayout>
	        <powersi:editorlayout-row>
		        <powersi:codeselect codeType="bka504" id="q_bka504" name="q_bka504" key="查询类型"></powersi:codeselect>
		        <powersi:textfield id="q_bac002" name="q_bac002"  key="q_bac002" placeholder="请输入病人身份证号查询"  label="病人身份证"/>
				<powersi:textfield id="q_aac003" name="q_aac003"  key="q_aac003" placeholder="请输入预约医生姓名查询"  label="预约医生姓名"/>
	        </powersi:editorlayout-row>
	        <tr>
	        	<td align="right" class="tdLabel">异动类型</td>
				<td>
				<select id="q_bae029" name="q_bae029" class="select">
					<option value="">请选择</option>
					<option value="1">停诊</option>
					<option value="2">改诊</option>
				</select>
				</td>
	        </tr>
	        <powersi:buttons>						
	            <input type="button" name="query" value="查询" class="button" onclick="query();" />
		        <input type="button" name="clear" value="重置" class="button" onclick="clearall();" />
	        </powersi:buttons>	
		</powersi:editorlayout>
	</powersi:groupbox>
		
	<powersi:groupbox title="业务信息">
		<powersi:datagrid id="grid_Appointment_Query"  exportFileName="'医院预约挂号信息'"  height="100%"  useCount="false" 
         		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true"  >
	       	  <powersi:datagrid-column key="operate" render="renderOperate1" width="120" frozen="true" />
	       	   <powersi:datagrid-column name="bka504"  display="挂号类别"  width="150" minWidth="50" code="bka504" render="renderDictionary"/>
	       	  <powersi:datagrid-column name="bae029_name"  display="异动类型" width="100" minWidth="50"/>
		      <powersi:datagrid-column name="bac003"  display="病人姓名" width="120" minWidth="100"/>
		      <powersi:datagrid-column name="bac002"  display="病人身份证号码"  width="150" minWidth="100"/>
		      <powersi:datagrid-column name="bac004"  display="性别" width="100" minWidth="50" code="aac004" render="renderDictionary"/>
		      <powersi:datagrid-column name="bka508"  display="预约号"  width="150" minWidth="60"/>
		      <powersi:datagrid-column name="aae005"  display="联系电话" width="100" minWidth="50" />
		      <powersi:datagrid-column name="aae006"  display="联系地址" width="170" minWidth="100"/>
		      <powersi:datagrid-column name="aaz307"  display="科室编号" width="60" minWidth="50" />
		      <powersi:datagrid-column name="aaz386"  display="科室名称" width="60" minWidth="50" />
		      <powersi:datagrid-column name="bka503"  display="医生编号" width="60" minWidth="50" />
		      <powersi:datagrid-column name="aac003"  display="医生姓名" width="60" minWidth="50" />
		      <powersi:datagrid-column name="aae030"  display="接诊日期"  width="100" minWidth="50"/>
		      <powersi:datagrid-column name="bae031"  display="接诊班次"  width="100" minWidth="50"/>
		      <powersi:datagrid-column name="bae032"  display="接诊时间段"  width="120" minWidth="50"/>
		      <powersi:datagrid-column name="aae013"  display="说明" width="200" minWidth="50" />
		      <powersi:datagrid-column name="bae013"  display="停诊异动说明" width="200" minWidth="50" />
		      <powersi:datagrid-column name="aae034"  display="改诊后接诊日期" width="100" minWidth="50" />
		      <powersi:datagrid-column name="bae035"  display="改诊后接诊班次" width="50" minWidth="50" />
		      <powersi:datagrid-column name="bae036"  display="改诊后接诊时间段" width="120" minWidth="50" />
		      <powersi:datagrid-column name="bka509"  display="改诊后预约号" width="80" minWidth="50" />
		      <powersi:datagrid-column name="bae029"  display="异动编码" width="20" minWidth="50" hide="true"/>
		</powersi:datagrid>
	</powersi:groupbox>
