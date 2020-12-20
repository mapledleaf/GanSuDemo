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
<powersi:head title="医院接入计划查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
<powersi:form id="accessPlanForm" name="accessPlanForm" namespace="/medicare"
		action="HospAccessMgrAction!queryAccessPlanList.action">
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button name="btDel" label="删 除" id="btDel" onclick="doDel()" />
				<powersi:button name="btClear" label="重 置" id="btClear" onclick="clearall()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout cols="8">
	        <powersi:editorlayout-row>
		        <powersi:textfield id="bae300" name="hospitalAccessPlanDTO.bae300"  key="bae300" placeholder="请输入医院接入方式流水号查询"  label="医院接入方式流水号"/>
		        <powersi:textfield id="bae300" name="hospitalAccessPlanDTO.bae310"  key="bae310" placeholder="请输入医院接入计划流水号查询"  label="医院接入计划流水号"/>
	        	<td colspan="2">
	        		<powersi:hidden id="akb020" name="hospitalAccessTypeDTO.akb020" />
	        	</td>
	        </powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
</powersi:form>		
	<powersi:groupbox title="查询结果">
		<a>双击单条信息修改明细。</a>
        <powersi:datagrid id="grid_AccessPlan_Query" formId="accessPlanForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'医院接入计划信息'">
			  <powersi:datagrid-column key="operate" render="renderOperate" width="100" frozen="true" />
		      <powersi:datagrid-column name="bae300"  display="医院接入方式流水号" frozen="true" width="160" minWidth="100"/>
		      <powersi:datagrid-column name="bae310"  display="医院接入计划流水号" frozen="true" width="160" minWidth="100"/>
		      <powersi:datagrid-column name="bae304"  display="实施内容编号" width="160" minWidth="50" />
		      <powersi:datagrid-column name="bae305"  display="实施内容名称" width="200" minWidth="50" />
		      <powersi:datagrid-column name="bae306"  display="阶段" width="100" minWidth="50" code="bae306" render="renderDictionary"/>
		      <powersi:datagrid-column name="bae307"  display="计划开始时间"  width="150" format="{0,date,yyyy-MM-dd}"/>
		      <powersi:datagrid-column name="bae308"  display="计划时间" width="60" minWidth="50"/>
		      <powersi:datagrid-column name="bae302"  display="录入人" width="100" minWidth="50" />
		      <powersi:datagrid-column name="bae303"  display="录入时间" width="150" format="{0,date,yyyy-MM-dd}"/>
		      <powersi:datagrid-column name="bae313"  display="备注" width="200" minWidth="50" />
		      <powersi:datagrid-column name="deltag"  display="可以删除标记" width="20" minWidth="20" hide="true"/>
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
 	grid_AccessPlan_Query.reload();
}

function clearall(){
	$('#bae300').val("");
	$('#bae310').val("");
}

/* function doAdd() {
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/hosp/HospAccessPlanNew.jsp",
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessPlan_Query.reload(true);
			}
		}, screen.height, screen.width);
		
} */
	
	//双击信息列表，修改接入方式信息
	function dbSelectRow(rowdata, rowid, rowobj){
		var row = grid_AccessPlan_Query.getRow(rowid);
		var bae310 = row['bae310'];
		
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessPlanEdit.action?hospitalAccessPlanDTO.bae310="+bae310,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessPlan_Query.reload(true);
			}
		}, screen.height, screen.width);
	}

	function doDel() {
		
		//获取多选，全部勾选的数据
		var rows = grid_AccessPlan_Query.getSelectedRows();
		if(powersi.isempty(rows)){
			popupAlert("请选择医院接入计划信息！");
			
			return;
		}
		var invalid = false;
  		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row){
			if(row['deltag'] == "1"){
				invalid = true;
				grid_AccessPlan_Query.select(row);
				popupAlert(row['bae310']+':该条存在关联的医院接入计划进度数据,不能删除！');
				grid_AccessPlan_Query.reload(true);
				return false;
			}
		});
  		
  		if(invalid){
  			return;
  		}
		
		if (!confirm("您确认删除选择的医院接入计划吗?")) {
        return;
    	}
		
		var data = powersi.tostring(rows);
		postJSON("${rootPath}/medicare/HospAccessMgrAction!delAccessPlan.action",
			{
			"data" : data
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupInfo(json.message);
				grid_AccessPlan_Query.reload(true);
		});

	}
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="接入进度明细" class="linkButton"');
		a.push(' onclick="queryAccessScheduleList(');
		a.push(index);
		a.push(')"');
		a.push(" />"); 

		return a.join('');
	}

	function queryAccessScheduleList(index){
		var row = grid_AccessPlan_Query.getRow(index);
		var bae310 = row['bae310'];
		var bae310 = row['bae310'];
		var bae304 = row['bae304'];
		var bae305 = row['bae305'];
		var bae306 = row['bae306'];
		var bae307 = row['bae307'];
		var bae308 = row['bae308'];
		var bae302 = row['bae302'];
		var bae303 = row['bae303'];
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryAccessScheduleListByBae310.action?hospitalAccessScheduleDTO.bae310="+bae310
			+"&hospitalAccessPlanDTO.bae304="+bae304
			+"&hospitalAccessPlanDTO.bae305="+bae305
			+"&hospitalAccessPlanDTO.bae306="+bae306
			+"&hospitalAccessPlanDTO.bae307="+bae307
			+"&hospitalAccessPlanDTO.bae308="+bae308
			+"&hospitalAccessPlanDTO.bae302="+bae302
			+"&hospitalAccessPlanDTO.bae303="+bae303
			,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessPlan_Query.reload(true);
			}
		}, screen.height, screen.width);
	}
</script>
</body>
</powersi:html>