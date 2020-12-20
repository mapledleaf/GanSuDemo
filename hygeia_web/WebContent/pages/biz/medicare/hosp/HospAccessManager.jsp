<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>

<powersi:html>
<body>
	<powersi:head title="医院接入查询" />
	
	<!-- 查询条件 -->
	<powersi:form id="queryForm" name="accessTypeForm" namespace="/medicare"
		action="HospAccessMgrAction!queryAccessTypeList.action">		
		<powersi:panelbox  title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_query" value="查询" id="btQuery" onclick="query();" />
				<powersi:button key="button_reset" value="重置" onclick="clearall()" />
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
	
	<!-- 查询结果 start -->
	<div class="row">
		
		<div class="col-5">
		<powersi:groupbox title="接入方式">
			<a>双击单条信息修改明细。</a>
			<powersi:datagrid id="grid_AccessTypeList" exportFileName="'医院接入方式查询'"  height="100%" 
						checkbox="false" onDblClickRow="dbSelectRow" onSelectRow="selectRow" 
					  	useCount="false" showExportExcel="true" enabledSort="true" delayLoad="true"  >
					<powersi:toolbar>
						<powersi:toolbar-item id="add_type" text="新增" icon="add" click="doAddType" />
						<powersi:toolbar-item id="del_type" text="删除" icon="delete" click="doDelType" />
					</powersi:toolbar>
					<powersi:datagrid-column name="bae300"  display="医院接入方式流水号" width="180" minWidth="100"/>
					<powersi:datagrid-column name="akb020"  display="医院编号" width="120" minWidth="100"/>
					<powersi:datagrid-column name="akb021"  display="医院名称" width="200" minWidth="100"/>
					<powersi:datagrid-column name="bae301"  display="接入方式" width="150" minWidth="100" code="bae301" render="renderDictionary"/>
					<powersi:datagrid-column name="bae302"  display="录入人" width="100" minWidth="50" />
					<powersi:datagrid-column name="bae303"  display="录入时间" width="150" format="{0,date,yyyy-MM-dd}"/>
					<powersi:datagrid-column name="bae313"  display="备注" width="200" minWidth="50" />
			</powersi:datagrid>
		</powersi:groupbox>
		</div>
		
		<div class="col-7">
		<powersi:form id="preAddPlan">
			<powersi:hidden id="t_bae300" />
		</powersi:form>
		<powersi:groupbox title="接入计划">
		<a>双击单条信息修改明细。</a>
			<powersi:datagrid id="grid_AccessPlanList" delayLoad="true" frozen="false" checkbox="false" onDblClickRow="dbSelectRow1"
					onSelectRow="selectRow1" showExportExcel="true" exportFileName="'接入计划表'" height="35%">
				<powersi:toolbar>
					<powersi:toolbar-item id="add_plan" text="新增" icon="add" click="doAddPlan" />
					<powersi:toolbar-item id="del_plan" text="删除" icon="delete" click="doDelPlan" />
				</powersi:toolbar>
				<powersi:datagrid-column name="bae310"  display="医院接入计划流水号" frozen="true" width="180" minWidth="100"/>
				<powersi:datagrid-column name="bae304"  display="实施内容编号" width="160" minWidth="50" />
				<powersi:datagrid-column name="bae305"  display="实施内容名称" width="200" minWidth="50" />
				<powersi:datagrid-column name="bae306"  display="阶段" width="100" minWidth="50" code="bae306" render="renderDictionary"/>
				<powersi:datagrid-column name="bae307"  display="计划开始时间"  width="150" format="{0,date,yyyy-MM-dd}"/>
				<powersi:datagrid-column name="bae308"  display="计划时间" width="60" minWidth="50"/>
				<powersi:datagrid-column name="bae302"  display="录入人" width="100" minWidth="50" />
				<powersi:datagrid-column name="bae303"  display="录入时间" width="150" format="{0,date,yyyy-MM-dd}"/>
				<powersi:datagrid-column name="bae313"  display="备注" width="200" minWidth="50" />
			</powersi:datagrid>
		</powersi:groupbox>
		<powersi:form id="preAddSchedule">
			<powersi:hidden id="p_bae310" />
		</powersi:form>
		<powersi:groupbox title="接入进度">
		<a>双击单条信息修改明细。</a>
			<powersi:datagrid id="grid_AccessScheduleList" delayLoad="true" frozen="false" checkbox="true" onDblClickRow="dbSelectRow2"
					showExportExcel="true" exportFileName="'接入进度表'" >
				<powersi:toolbar>
					<powersi:toolbar-item id="add_schedule" text="新增" icon="add" click="doAddSchedule" />
					<powersi:toolbar-item id="del_schedule" text="删除" icon="delete" click="doDelSchedule" />
				</powersi:toolbar>
				<powersi:datagrid-column name="bae320"  display="医院接入进度流水号" frozen="true" width="180" minWidth="100"/>
				<powersi:datagrid-column name="bae309"  display="进度情况" width="120" minWidth="50" code="bae306" render="renderDictionary"/>
				<powersi:datagrid-column name="bae302"  display="录入人" width="120" minWidth="50" />
				<powersi:datagrid-column name="bae303"  display="录入时间" width="150" format="{0,date,yyyy-MM-dd}"/>
				<powersi:datagrid-column name="bae313"  display="备注" width="260" />
			</powersi:datagrid>
		</powersi:groupbox>
		</div>
	</div>
	<!-- 查询结果 end -->
	
	<tags:transCode/>
	<powersi:errors/>

<script type="text/javascript">
window.onload = function(){
	$('#akb020').val("<%=hospital_id%>");
 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
		popupInfo("医院编码不能为空，请检查登陆信息！");
    	return;
	}
 	grid_AccessTypeList.reload();
}

function query()
{
	var bae300 = $("#bae300").val();
	grid_AccessTypeList.setParm("hospitalAccessTypeDTO.bae300",bae300);
	grid_AccessTypeList.set("url", rootPath + '/medicare/HospAccessMgrAction!queryAccessTypeList.action');

	grid_AccessPlanList.reset();
	grid_AccessScheduleList.reset();
}

function clearall(){
	$('#bae300').val("");
}

function doAddType() {
	popupDialog({
		url: "${rootPath}/pages/biz/medicare/hosp/HospAccessTypeNew.jsp",
		onClosed: function() {
			var ret = this.returnValue;
			grid_AccessTypeList.reload(true);
		}
	}, screen.height, screen.width);
	
}

//双击信息列表，修改接入方式信息
function dbSelectRow(rowdata, rowid, rowobj){
	var row = grid_AccessTypeList.getRow(rowid);
	var bae300 = row['bae300'];
	
	popupDialog({
		url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessTypeEdit.action?hospitalAccessTypeDTO.bae300="+bae300,
		onClosed: function() {
			var ret = this.returnValue;
			grid_AccessTypeList.reload(true);
			grid_AccessPlanList.reset();
			grid_AccessScheduleList.reset();
		}
	}, screen.height, screen.width);
}

function doDelType() {
	
	//获取多选，全部勾选的数据
	var rows = grid_AccessTypeList.getSelectedRows();
	if(powersi.isempty(rows)){
		popupInfo("请选择医院接入方式信息！");
		
		return;
	}
	var invalid = false;
		//判断结果集是否为空，为空下面循环取值会抛异常
	$.each(rows, function(i, row){
		if(row['deltag'] == "1"){
			invalid = true;
			grid_AccessTypeList.select(row);
			popupInfo(row['bae300']+':该条存在关联的医院接入计划数据,不能删除！');
			grid_AccessTypeList.reload(true);
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
			grid_AccessTypeList.reload(true);
	});

}

/** 非空字符判断 **/
function isNotBlank(str) {
	if (typeof (str) == "undefined" || str == null || str == "") {
		return false;
	} 
	else {
		return true;
	}
}
	
	//双击信息列表，修改接入计划信息
	function dbSelectRow1(rowdata, rowid, rowobj){
		var row = grid_AccessPlanList.getRow(rowid);
		var bae310 = row['bae310'];
		
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessPlanEdit.action?hospitalAccessPlanDTO.bae310="+bae310,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessPlanList.reload(true);
				grid_AccessScheduleList.reset();
			}
		}, screen.height, screen.width);
	}
	
	function doAddPlan(){
		var bae300 = $('#t_bae300').val();
		if(!isNotBlank(bae300)){
			popupAlert("请先选择对应的接入方式，再新增接入计划");
			return;
		}
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/hosp/HospAccessPlanNew.jsp?hospitalAccessPlanDTO.bae300="+bae300,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessPlanList.reload(true);
			}
		}, screen.height, screen.width);
	}
	
	function doDelPlan(){
		//获取多选，全部勾选的数据
		var rows = grid_AccessPlanList.getSelectedRows();
		if(powersi.isempty(rows)){
			popupAlert("请选择医院接入计划信息！");
			return;
		}
		var invalid = false;
  		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row){
			if(row['deltag'] == "1"){
				invalid = true;
				grid_AccessPlanList.select(row);
				popupAlert(row['bae310']+':该条存在关联的医院接入计划进度数据,不能删除！');
				grid_AccessPlanList.reload(true);
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
				grid_AccessPlanList.reload(true);
		});
	}
	
	//双击信息列表，修改接入进度信息
	function dbSelectRow2(rowdata, rowid, rowobj){
		var row = grid_AccessScheduleList.getRow(rowid);
		var bae320 = row['bae320'];
		
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessScheduleEdit.action?hospitalAccessScheduleDTO.bae320="+bae320,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessScheduleList.reload(true);
			}
		}, screen.height, screen.width);
	}
	
	function doAddSchedule(){
		var bae310 = $('#p_bae310').val();
		if(!isNotBlank(bae310)){
			popupAlert("请先选择对应的接入计划，再新增接入进度");
			return;
		}
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/hosp/HospAccessScheduleNew.jsp?hospitalAccessScheduleDTO.bae310="+bae310,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessScheduleList.reload(true);
			}
		}, screen.height, screen.width);
	}
	
	function doDelSchedule(){
		//获取多选，全部勾选的数据
		var rows = grid_AccessScheduleList.getSelectedRows();
		if(powersi.isempty(rows)){
			popupAlert("请选择医院接入进度信息！");
			return;
		}
		if (!confirm("您确认删除选择的医院接入进度吗?")) {
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
				grid_AccessScheduleList.reload(true);
		});
	}
	
	
	
	/** 选择某一条接入方式查看详情（接入方式、进度） **/
	function selectRow(rowdata, rowid, rowobj){
		showDetail(rowdata);
	}
	
	function showDetail(rowdata){
		if(rowdata){
			var q_bae300 =String(rowdata["bae300"]);
			if(q_bae300== "undefined"){
				q_bae300="null";
			}
			grid_AccessPlanList.set("url", rootPath + '/medicare/HospAccessMgrAction!queryAccessPlanList.action?hospitalAccessPlanDTO.bae300='+q_bae300);
			grid_AccessScheduleList.reset();
			$("#t_bae300").val(q_bae300);
		} 
	}
	
	function selectRow1(rowdata, rowid, rowobj){
		showDetail1(rowdata);
	}
	function showDetail1(rowdata){
		if(rowdata){
			var q_bae310 =String(rowdata["bae310"]);
			grid_AccessScheduleList.set("url", rootPath + '/medicare/HospAccessMgrAction!queryAccessScheduleList.action?hospitalAccessScheduleDTO.bae310='+q_bae310);
			$("#p_bae310").val(q_bae310);
		}
	}

</script>
</body>
</powersi:html>