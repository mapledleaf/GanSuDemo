<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="提交预约挂号" description="提交预约挂号"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<script type="text/javascript">
/*查询已保存的预约挂号号源*/
function queryDS() {
	var bka503=$("#ds_bka503").val();
	var aaz307=$("#ds_aaz307").val();
	var aac003=$("#ds_aac003").val();
	var aae030=$("#ds_aae030").val();
	postJSON("${rootPath}/inhospital/AppointmentManagerAction!queryDoctorSourceInfos.action", 
			{
				"doctorSourceInfoDTO.aaz307":aaz307,
				"doctorSourceInfoDTO.bka503":bka503,
				"doctorSourceInfoDTO.aae030":aae030,
				"doctorSourceInfoDTO.aac003":aac003
			},
			function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
	    	//加载结果集到datagrid
			ds_grid.loadData(json.data);
	});
}

function clearall(){
	$('#ds_bka503').val("");
	$('#ds_aaz307').val("");
	$('#ds_aac003').val("");
}

function renderOperate(row, index, value) {
	var a = [];
	a.push('<input type="button" value="预约" class="linkButton"');
	a.push(' onclick="toSubmitAppointment(');
	a.push(index);
	a.push(')"');
	a.push(" />"); 
	return a.join('');
}

function toSubmitAppointment(index){
	var row = ds_grid.getRow(index);
	var aaz307 = row['aaz307'];
	var aaz386 = row['aaz386'];
	var bka503 = row['bka503'];
	var aac003 = row['aac003'];
	var acd231 = row['acd231'];
	var bac045 = row['bac045'];
	var abk027 = row['abk027'];
	var aae030 = row['aae030'];
	var bae031 = row['bae031'];
	var bae032 = row['bae032'];
	var bae587 = row['bae587'];
	var bae588 = row['bae588'];
	var bae589 = row['bae589'];
	var akc225 = row['akc225'];
	var aae013 = row['aae013'];
	if(bae588=='0'){
		alert("已经没有剩余号源可以预约了！")
		return;
	}
	
	aaz386 = encodeURI(encodeURI(aaz386));
	aac003 = encodeURI(encodeURI(aac003));
	popupDialog({
		url: "${rootPath}/inhospital/AppointmentManagerAction!toSubmitAppointment.action?doctorSourceInfoDTO.aaz307="+aaz307
		+"&doctorSourceInfoDTO.aaz386="+aaz386
		+"&doctorSourceInfoDTO.bka503="+bka503
		+"&doctorSourceInfoDTO.aac003="+aac003
		+"&doctorSourceInfoDTO.acd231="+acd231
		+"&doctorSourceInfoDTO.abk027="+abk027
		+"&doctorSourceInfoDTO.aae030="+aae030
		+"&doctorSourceInfoDTO.bae031="+bae031
		+"&doctorSourceInfoDTO.bae032="+bae032
		+"&doctorSourceInfoDTO.bae587="+bae587
		+"&doctorSourceInfoDTO.bae588="+bae588
		+"&doctorSourceInfoDTO.bae589="+bae589
		+"&doctorSourceInfoDTO.akc225="+akc225
		+"&doctorSourceInfoDTO.aae013="+aae013
		,
		onClosed: function() {
			//var ret = this.returnValue;
			//grid_AccessPlan_Query.reload(true);
			queryDS();
		}
	}, screen.height, screen.width);
	
}

</script>

	<powersi:groupbox title="查询条件">
		<powersi:editorlayout cols="8">
	        <powersi:editorlayout-row>
		        <powersi:textfield id="ds_aaz307" name="ds_aaz307"  placeholder="请输入医院科室编码查询"  label="医院科室编码"/>
				<powersi:textfield id="ds_bka503" name="ds_bka503"  placeholder="请输入预约医生编码查询"  label="预约医生编码"/>
				<powersi:textfield id="ds_aac003" name="ds_aac003"  placeholder="请输入预约医生姓名查询"  label="预约医生姓名"/>
				<powersi:textfield id="ds_aae030" name="ds_aae030"  mask="date" 
						format="dateFmt:'yyyy-MM-dd'"  placeholder="请输入接诊日期查询"  label="接诊日期"/>
	        </powersi:editorlayout-row>
	         <powersi:editorlayout-row>
	        <powersi:buttons cols="8">						
	            <input type="button" name="query" value="查询" class="button" onclick="queryDS();" />
		        <input type="button" name="clear" value="重置" class="button" onclick="clearall();" />
	        </powersi:buttons>	
	         </powersi:editorlayout-row>
		</powersi:editorlayout>
		        							
		</powersi:groupbox>
		
		<powersi:groupbox title="预约挂号号源">
			<powersi:datagrid id="ds_grid"  exportFileName="'医院预约挂号号源'"  height="100%"  useCount="false"
	       		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true"  pageSize="20">
		       	<powersi:datagrid-column key="operate" render="renderOperate" width="80" frozen="true" />
				<powersi:datagrid-column name="aaz307" display="科室编号" width="10%" />
				<powersi:datagrid-column name="aaz386" display="科室名称" width="15%" />
				<powersi:datagrid-column name="bka503" display="医生编号" width="10%" />
				<powersi:datagrid-column name="aac003" display="医生姓名" width="10%" />
				<powersi:datagrid-column name="acd231" display="职称" width="10%" code="bkc272"/>
				<powersi:datagrid-column name="bac045" display="专业" width="10%" hide="true"/>
				<powersi:datagrid-column name="abk027" display="个人简介" width="10%" hide="true"/>
				<powersi:datagrid-column name="aae030" display="接诊日期" width="10%" />
				<powersi:datagrid-column name="bae031" display="接诊班次" width="8%" />
				<powersi:datagrid-column name="bae032" display="接诊时间段" width="15%" />
				<powersi:datagrid-column name="bae587" display="总号源数" width="6%" />
				<powersi:datagrid-column name="bae588" display="剩余号源" width="6%" />
				<powersi:datagrid-column name="bae589" display="出诊地点" width="20%" />
				<powersi:datagrid-column name="akc225" display="挂号费标准" width="6%" />
				<powersi:datagrid-column name="aae013" display="说明" width="20%"/>
			</powersi:datagrid>
		
	</powersi:groupbox>

<powersi:form id="doctorsource">
	<powersi:hidden id="aaz386" name="doctorSourceInfoDTO.aaz386"/>
	<powersi:hidden id="bka503" name="doctorSourceInfoDTO.bka503"/>
	<powersi:hidden id="aac003" name="doctorSourceInfoDTO.aac003"/>
	<powersi:hidden id="acd231" name="doctorSourceInfoDTO.acd231"/>
	<powersi:hidden id="abk027" name="doctorSourceInfoDTO.abk027"/>
	<powersi:hidden id="aae030" name="doctorSourceInfoDTO.aae030"/>
	<powersi:hidden id="bae031" name="doctorSourceInfoDTO.bae031"/>
	<powersi:hidden id="bae032" name="doctorSourceInfoDTO.bae032"/>
	<powersi:hidden id="bae587" name="doctorSourceInfoDTO.bae587"/>
	<powersi:hidden id="bae588" name="doctorSourceInfoDTO.bae588"/>
	<powersi:hidden id="bae589" name="doctorSourceInfoDTO.bae589"/>
	<powersi:hidden id="akc225" name="doctorSourceInfoDTO.akc225"/>
	<powersi:hidden id="aae013" name="doctorSourceInfoDTO.aae013"/>
</powersi:form>
