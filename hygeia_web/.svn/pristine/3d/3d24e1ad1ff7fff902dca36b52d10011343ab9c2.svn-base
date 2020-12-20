<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="医院接入进度查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
<powersi:form id="accessScheduleForm" name="accessScheduleForm" namespace="/medicare"
		action="HospAccessMgrAction!queryAccessScheduleList.action">
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" />
				<powersi:button name="btDel" label="删 除" id="btDel" onclick="doDel()" />
				<powersi:button name="btClear" label="重 置" id="btClear" onclick="clearall()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout cols="8">
	        <powersi:editorlayout-row>
		        <powersi:textfield id="bae300" name="hospitalAccessScheduleDTO.bae310"  key="bae310" placeholder="请输入医院接入计划流水号查询"  label="医院接入计划流水号"/>
		        <powersi:textfield id="bae320" name="hospitalAccessScheduleDTO.bae320"  key="bae320" placeholder="请输入医院接入进度流水号查询"  label="医院接入进度流水号"/>
	        	<td colspan="2">
	        		<powersi:hidden id="akb020" name="hospitalAccessTypeDTO.akb020" />
	        	</td>
	        </powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
</powersi:form>		
	<powersi:groupbox title="查询结果">
		<a>双击单条信息修改明细。</a>
        <powersi:datagrid id="grid_AccessSchedule_Query" formId="accessScheduleForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'医院接入进度信息'">
		      <powersi:datagrid-column name="bae310"  display="医院接入计划流水号" frozen="true" width="200" minWidth="100"/>
		      <powersi:datagrid-column name="bae320"  display="医院接入进度流水号" frozen="true" width="200" minWidth="100"/>
		      <powersi:datagrid-column name="bae309"  display="进度情况" width="160" minWidth="50" code="bae309" render="renderDictionary"/>
		      <powersi:datagrid-column name="bae302"  display="填报人" width="100" minWidth="50" />
		      <powersi:datagrid-column name="bae303"  display="填报时间" width="150" format="{0,date,yyyy-MM-dd}"/>
		      <powersi:datagrid-column name="bae313"  display="备注" width="200" minWidth="50" />
		</powersi:datagrid>
	</powersi:groupbox>
	
	<tags:transCode/>
	<powersi:errors />

<script type="text/javascript">
window.onload = function(){
	
	$('#akb020').val("<%=hospital_id%>");
 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
		popupAlert("医院编码不能为空，请检查登陆信息！");
    	return;
	}
 	grid_AccessSchedule_Query.reload();
}

function clearall(){
	$('#bae310').val("");
	$('#bae320').val("");
}

/* function doAdd() {
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/hosp/HospAccessScheduleNew.jsp",
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessSchedule_Query.reload(true);
			}
		}, screen.height, screen.width);
		
} */
	
	//双击信息列表，修改接入方式信息
	function dbSelectRow(rowdata, rowid, rowobj){
		var row = grid_AccessSchedule_Query.getRow(rowid);
		var bae320 = row['bae320'];
		
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessScheduleEdit.action?hospitalAccessScheduleDTO.bae320="+bae320,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessSchedule_Query.reload(true);
			}
		}, screen.height, screen.width);
	}

	function doDel() {
		//获取多选，全部勾选的数据
		var rows = grid_AccessSchedule_Query.getSelectedRows();
		if(powersi.isempty(rows)){
			popupAlert("请选择医院接入进度信息！");
			return;
		}
		if (!confirm("您确认删除选择的医院接入计划吗?")) {
        return;
    	}
		
		var data = powersi.tostring(rows);
		postJSON("${rootPath}/medicare/HospAccessMgrAction!delAccessSchedule.action",
			{
			"data" : data
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupInfo(json.message);
				grid_AccessSchedule_Query.reload(true);
		});

	}
</script>
</body>
</powersi:html>