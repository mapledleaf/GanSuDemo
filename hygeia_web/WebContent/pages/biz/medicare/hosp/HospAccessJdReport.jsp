<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<powersi:html>
<powersi:head title="API接口开发与联调进度上报" />
<body>
<powersi:form id="reportForm" name="reportForm">
	<powersi:panelbox title="API接口开发与联调进度上报表">
		<powersi:panelbox-toolbar>
			<powersi:button id="btAdd" label="进度上报"  onclick="saveJdReportInfo()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout>
			<powersi:editorlayout-row>
				<powersi:textfield id="his_developers" name="jdReportDTO.his_developers" label="HIS开发商"   required="true" />
				<powersi:textfield   id="currentTime"  label="填表时间"     readonly="true" required="true"/>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	<powersi:codetable id="apijdList" codeType="api_jd_flag">
			</powersi:codetable>

	<powersi:groupbox title="进度上报列表">
		<div id="tab-pane" class="tab-pane">
		<powersi:hidden id="mzye_type" value="1"/>
	        <powersi:datagrid id="mzlist" 	height="100%" enabledEdit="true" clickToEdit="true" usePager="false" mergeColumnNames="['level2_value']">
				<powersi:datagrid-column display="一级业务类型编码"    name="level1_id" width="80" hide="true"/>			      
				<powersi:datagrid-column display="一级业务类型"    name="level1_value" width="80" hide="true"/>			      
				<powersi:datagrid-column display="业务类型编码"    name="level2_id" width="80" hide="true"/>			      
				<powersi:datagrid-column display="业务类型"    name="level2_value" width="120" frozen="true"/>			      
				<powersi:datagrid-column display="编码"    name="level3_id" width="80" />			      
				<powersi:datagrid-column display="接口名称"    name="level3_value" width="180"  />			      
				<powersi:datagrid-column display="计划完成时间"    name="plan_complete_time" width="100" editor="{type: 'date'}"/>
				<powersi:datagrid-column display="开发完成情况" name="develop_result" width="100" render="renderFlag" editor="{type: 'select', data:apijdList}"/>			      
				<powersi:datagrid-column display="联调完成情况"    name="debug_result" width="100" render="renderFlag" editor="{type: 'select', data:apijdList}"/>
				<powersi:datagrid-column display="联调业务号(就医登记号)"    name="ywno" width="120"  editor="{type: 'string'}"/>			      
				<powersi:datagrid-column display="备注"    name="remark" width="300"  editor="{type: 'string'}" align="left"/>		      
			</powersi:datagrid>
		</div>
	</powersi:groupbox>
	</powersi:form>
	<powersi:errors />

<script type="text/javascript">
function renderFlag(rowdata, index, value) {
	if (value === '0') {
		return '<span title="未完成"><i class="icon-remove textError"></i></span>';
	} else {
		return '<span title="已完成"><i class="icon-ok textSuccess"></i></span>';
	}
}

window.onload = function(){
	var currentTime = new Date();    
	
	$('#currentTime').val(currentTime.toLocaleDateString());
	postJSON("${rootPath}/medicare/HospAccessMgrAction!queryJdReportInfo.action",afterDetail);
}

function afterDetail(json){
	if(!checkJSONResult(json)){
	   return;
	}
	 $.each(json.data.his_developers,function(key,value){
		 $("#"+key).val(value);
	 });
	mzlist.loadData(json.data.reportList);
}


function saveJdReportInfo() {
	if(!checkForm()){
		return;
	}  
	
	var data = mzlist.getAllData();
	var saveItemData = powersi.tostring(data);
	var url = "${rootPath}/medicare/HospAccessMgrAction!saveJdReportInfo.action";
	var his_developers = $("#his_developers").val();
	var objList = "reportList="+saveItemData+"&jdReportDTO.his_developers="+his_developers;
	postJSON(url,objList,afterSaveItem);
}

function afterSaveItem(json){
	if (!checkJSONResult(json)) {
		return;
	} else {
		if (json.data == "") {
			popupError("保存信息失败!");
			return;
		} else {
		     popupInfo(json.data);
		     postJSON("${rootPath}/medicare/HospAccessMgrAction!queryJdReportInfo.action",afterDetail);
		}
	}
}
	
</script>
</body>
</powersi:html>