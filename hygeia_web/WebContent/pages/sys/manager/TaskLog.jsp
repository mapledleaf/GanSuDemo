<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="任务日志查询" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" action="TaskManager!queryLog" namespace="/manager">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:date id="datetimeRange" startField="beginDate" endField="endDate"
							 mask="datetime"  label="选择时间" required="true" colspan="3">
						<powersi:date-range label="今天" startDate="moment().startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="昨天" startDate="moment().subtract(1, 'days').startOf('day')" endDate="moment().subtract(1, 'days').endOf('day')" />
						<powersi:date-range label="最近三天" startDate="moment().subtract(2, 'days').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="最近一周" startDate="moment().subtract(6, 'days').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="本周" startDate="moment().startOf('week').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="本月" startDate="moment().startOf('month').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="上个月" 
								startDate="moment().subtract(1, 'months').startOf('month').startOf('day')" 
								endDate="moment().subtract(1, 'months').endOf('month').endOf('day')" />
					</powersi:date>
					<powersi:hidden id="beginDate" name="beginDate"  key="begin_date" mask="datetime" required="true" />
					<powersi:hidden id="endDate" name="endDate"  key="end_date" mask="datetime" required="true" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="taskName" name="task_nameordesc"  label="任务名称和描述"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:checkboxlist id="taskState" name="task_state" label="任务状态" codeType="task_state" required="true" />
					<powersi:textfield id="instanceName" name="instance_name" label="实例名称"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="taskParam" name="task_param"  label="任务参数"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="taskResult" name="task_result"  label="任务结果"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="taskRemark" name="task_remark"  label="任务备注"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="日志列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			onDblClickRow="gridDblClickRow" rowAttrRender="gridRowRender"
			exportFileName="getExportFileName" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column name="instance_name" display="实例名称" width="200" frozen="true" />
			<powersi:datagrid-column name="task_name" display="任务名称" width="150" frozen="true" />
			<powersi:datagrid-column name="task_desc" display="任务描述" width="150" />
			<powersi:datagrid-column name="task_date" display="任务时间"  width="150" />
			<powersi:datagrid-column name="begin_date" display="开始时间"  width="150" />
			<powersi:datagrid-column name="end_date" display="结束时间"  width="150" />
			<powersi:datagrid-column name="task_runtime" display="运行耗时(秒)"  width="100" />
			<powersi:datagrid-column name="task_state_name" display="任务状态" width="80" code="task_state" data="task_state" />
			<powersi:datagrid-column name="task_param" display="任务参数" width="150" />
			<powersi:datagrid-column name="task_result" display="任务结果" width="150" />
			<powersi:datagrid-column name="task_remark" display="任务备注" minWidth="300" width="100%" exportWidth="150" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="logDlg">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-head title="任务日志详细信息">
				</powersi:editorlayout-head>
				<powersi:editorlayout-row>
					<powersi:textfield label="任务编号" id="dlg_task_id" readonly="true"></powersi:textfield>
					<powersi:textfield label="任务时间" id="dlg_task_date" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="任务名称" id="dlg_task_name" readonly="true"></powersi:textfield>
					<powersi:textfield label="任务描述" id="dlg_task_desc" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="任务状态" id="dlg_task_state_name" readonly="true"></powersi:textfield>
					<powersi:textfield label="实例名称" id="dlg_instance_name" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="开始时间" id="dlg_begin_date" readonly="true"></powersi:textfield>
					<powersi:textfield label="结束时间" id="dlg_end_date" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="任务参数" id="dlg_task_param" readonly="true" colspan="3" rows="5"></powersi:textarea>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="任务结果" id="dlg_task_result" readonly="true" colspan="3" rows="5"></powersi:textarea>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="任务备注" id="dlg_task_remark" readonly="true" colspan="3" rows="5"></powersi:textarea>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
	/*错误红色标注*/
	function gridRowRender(rowdata, rowid){
		if (rowdata['task_state'] == '2') {
			return getColorStyle('error');
		}
	}
	
	/*根据参数生成导出文件名*/
	function getExportFileName() {
		var filename = '任务操作日志(' + $('#beginDate').val().substring(0, 10)
				+ '_' + $('#endDate').val().substring(0, 10) + ')';
		return filename.replace(/-/g, "");
	}
	
	/*输出操作*/
	function renderOperate(row, index, value) {
		return "<input type='button' value='查看' title='查看明细信息' class='linkButton' onclick='doView("
				+ index + ")' />";
	}
	
	/*双击查看明细*/
	function gridDblClickRow(rowdata, rowindex, rowobj) {
	    showLogInfo(rowdata);
	}
	
	/*查看明细*/
	function doView(index){
		var row = grid.getRow(index);
		showLogInfo(row);
	}
	
	/*查看明细信息*/
	var dlg = null;
	function showLogInfo(data) {
		if(data == null){
			return;
		}
		
		for ( var k in data) {
			$('#dlg_' + k).val(data[k]);
		}
		
		if(dlg){
			dlg.show();
		} else{
			dlg = dlg = popupDiv("#logDlg", "查看任务日志", 600, {modal: false, showMax: true});
		}
	}
	
	$(function(){
		$(':checkbox[name="task_state"]').prop('checked', true);
		
		//$('#begin_date').val('');
	});
</script>
</body>
</powersi:html>