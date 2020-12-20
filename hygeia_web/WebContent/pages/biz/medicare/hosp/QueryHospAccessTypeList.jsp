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
<powersi:head title="医院接入方式查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
<powersi:form id="accessTypeForm" name="accessTypeForm" namespace="/medicare"
		action="HospAccessMgrAction!queryAccessTypeList.action">
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:panelbox-toolbar>
				<%-- <powersi:button id="btQuery" label="查 询" onclick="query()"/> --%>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button id="btAdd" label="新 增"  onclick="doAdd()" />
				<powersi:button name="btDel" label="删 除" id="btDel" onclick="doDel()" />
				<powersi:button name="btClear" label="重 置" id="btClear" onclick="clearall()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout cols="8">
	        <powersi:editorlayout-row>
		        <powersi:textfield id="bae300" name="hospitalAccessTypeDTO.bae300"  key="bae300" placeholder="请输入医院接入方式流水号查询"  label="医院接入方式流水号"/>
	        	<td colspan="5">
	        		<powersi:hidden id="akb020" name="hospitalAccessTypeDTO.akb020" />
	        	</td>
	        </powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
</powersi:form>		
	<powersi:groupbox title="查询结果">
		<a>双击单条信息修改明细。</a>
        <powersi:datagrid id="grid_AccessType_Query" formId="accessTypeForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'医院接入方式信息'">
			  <powersi:datagrid-column key="operate" render="renderOperate" width="100" frozen="true" />
		      <powersi:datagrid-column name="bae300"  display="医院接入方式流水号" frozen="true" width="160" minWidth="100"/>
		      <powersi:datagrid-column name="akb020"  display="医院编号" frozen="true" width="120" minWidth="100"/>
		      <powersi:datagrid-column name="akb021"  display="医院名称" width="200" minWidth="100"/>
		      <powersi:datagrid-column name="bae301"  display="接入方式" width="150" minWidth="100" code="bae301" render="renderDictionary"/>
		      <powersi:datagrid-column name="bae302"  display="录入人" width="100" minWidth="50" />
		      <powersi:datagrid-column name="bae303"  display="录入时间" width="150" format="{0,date,yyyy-MM-dd}"/>
		      <powersi:datagrid-column name="bae313"  display="备注" width="200" minWidth="50" />
		      <powersi:datagrid-column name="deltag"  display="可以删除标记" width="20" minWidth="20" hide="true"/>
		</powersi:datagrid>
	</powersi:groupbox>
	
	<tags:transCode/>
	<powersi:errors />

<script type="text/javascript">
/* function query()
{
	var bae300 = $("#bae300").val();
	postJSON("${rootPath}/medicare/HospAccessMgrAction!queryAccessTypeList.action",{"hospitalAccessTypeDTO.bae300": bae300}, 
			function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
	    	//加载结果集到datagrid
			grid_AccessType_Query.loadData(json.data);
	});
} */
window.onload = function(){
	
	$('#akb020').val("<%=hospital_id%>");
 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
		popupAlert("医院编码不能为空，请检查登陆信息！");
    	return;
	}
 	grid_AccessType_Query.reload();
}

function clearall(){
	$('#bae300').val("");
}

function doAdd() {
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/hosp/HospAccessTypeNew.jsp",
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessType_Query.reload(true);
			}
		}, screen.height, screen.width);
		
}
	
	//双击信息列表，修改接入方式信息
	function dbSelectRow(rowdata, rowid, rowobj){
		var row = grid_AccessType_Query.getRow(rowid);
		var bae300 = row['bae300'];
		
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessTypeEdit.action?hospitalAccessTypeDTO.bae300="+bae300,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessType_Query.reload(true);
			}
		}, screen.height, screen.width);
	}

	function doDel() {
		
		//获取多选，全部勾选的数据
		var rows = grid_AccessType_Query.getSelectedRows();
		if(powersi.isempty(rows)){
			popupAlert("请选择医院接入方式信息！");
			
			return;
		}
		var invalid = false;
  		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row){
			if(row['deltag'] == "1"){
				invalid = true;
				grid_AccessType_Query.select(row);
				popupAlert(row['bae300']+':该条存在关联的医院接入计划数据,不能删除！');
				grid_AccessType_Query.reload(true);
				return false;
			}
		});
  		
  		if(invalid){
  			return;
  		}
		
		if (!confirm("您确认删除选择的医院接入方式吗?")) {
        return;
    	}
		
		var data = powersi.tostring(rows);
		postJSON("${rootPath}/medicare/HospAccessMgrAction!delAccessType.action",
			{
			"data" : data
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupInfo(json.message);
				grid_AccessType_Query.reload(true);
		});

	}
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="接入计划明细" class="linkButton"');
		a.push(' onclick="queryAccessPlanList(');
		a.push(index);
		a.push(')"');
		a.push(" />");

		/* a.push("&nbsp;&nbsp;");

		a.push('<input type="button" value="新增接入计划" class="linkButton"');
		a.push(' onclick="saveAccessPlan(');
		a.push(index);
		a.push(')"');
		a.push(" />"); */

		return a.join('');
	}

	function queryAccessPlanList(index){
		var row = grid_AccessType_Query.getRow(index);
		var bae300 = row['bae300'];
		var bae302 = row['bae302'];
		var bae303 = row['bae303'];
		//window.location.href="${rootPath}/medicare/HospAccessMgrAction!queryAccessPlanList.action?hospitalAccessPlanDTO.bae300="+bae300;
		//window.open("${rootPath}/medicare/HospAccessMgrAction!queryAccessPlanList.action?hospitalAccessPlanDTO.bae300="+bae300);
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryAccessPlanListByBae300.action?hospitalAccessPlanDTO.bae300="+bae300
					+"&hospitalAccessTypeDTO.bae302="+bae302+"&hospitalAccessTypeDTO.bae303="+bae303,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessType_Query.reload(true);
			}
		}, screen.height, screen.width);
	}
	
	/* function saveAccessPlan(index){
		var row = grid_AccessType_Query.getRow(index);
		var bae300 = row['bae300'];
		
		alert(bae300+"弹出新增接入计划页面");
	} */
</script>
</body>
</powersi:html>