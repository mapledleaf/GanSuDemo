<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="系统日志查询" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" action="QuerySystemLog!query" namespace="/manager">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:date id="datetimeRange" startField="beginDate" endField="endDate" dateLimit="{'days': 30}" timePicker="true"
							 mask="datetime"  label="选择时间" required="true" colspan="2">
						<powersi:date-range label="今天" startDate="moment().startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="昨天" startDate="moment().subtract(1, 'days').startOf('day')" endDate="moment().subtract(1, 'days').endOf('day')" />
						<powersi:date-range label="最近三天" startDate="moment().subtract(2, 'days').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="最近一周" startDate="moment().subtract(6, 'days').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="本周" startDate="moment().startOf('week').startOf('day')" endDate="moment().endOf('day')" />
						<powersi:date-range label="本月" startDate="moment().startOf('month').startOf('day')" endDate="moment().endOf('day')" />
					</powersi:date>
					<powersi:hidden id="beginDate" name="beginDate"  key="begin_date" required="true" />
					<powersi:hidden id="endDate" name="endDate"  key="end_date" required="true" />
					<powersi:editorlayout-button colspan="3">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:checkboxlist list="#request.codeLogLevel" id="logLevel" name="logLevel" value="#request.logLevel" label="日志级别" required="true" colspan="3" checkAllButton="true" />
					<powersi:textfield id="serverName" name="serverName" label="服务器名称"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="logName" name="logName"  label="日志名称"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="logMessage" name="logMessage"  label="日志消息"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="logException" name="logException"  label="日志异常"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="operateName" name="operateName"  label="操作名称"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="loginUser" name="loginUser"  key="login_user"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="userAddress" name="userAddress"  label="用户地址"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="日志列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			onDblClickRow="gridDblClickRow" rowAttrRender="gridRowRender"
			exportFileName="getExportFileName" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:datagrid-column key="operate" width="50" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column name="server_name" display="服务器" width="100" />
			<powersi:datagrid-column name="log_date" display="日志时间"  width="150" />
			<powersi:datagrid-column name="log_level_name" display="日志级别" width="80" code="log_level" data="log_level"/>
			<powersi:datagrid-column name="log_message" display="日志消息" width="50%" minWidth="200" isSort="false" />
			<powersi:datagrid-column name="log_exception" display="日志异常" width="50%" minWidth="200" isSort="false" />
			<powersi:datagrid-column name="operate_name" key="operate_name"  width="100" />
			<powersi:datagrid-column name="operate_action" display="操作地址" width="100" />
			<powersi:datagrid-column name="log_name" display="日志名称"  width="100" />
			<powersi:datagrid-column name="login_user" display="登录名" width="100" />
			<powersi:datagrid-column name="user_address" display="用户地址" width="100" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="logDlg">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-head title="系统日志详细信息">
				</powersi:editorlayout-head>
				<powersi:editorlayout-row>
					<powersi:textfield label="日志编号" id="dlg_log_sn" readonly="true"></powersi:textfield>
					<powersi:textfield label="日志时间" id="dlg_log_date" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="日志级别" id="dlg_log_level_name" readonly="true"></powersi:textfield>
					<powersi:textfield label="服务器" id="dlg_server_name" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="日志名称" id="dlg_log_name" readonly="true" colspan="3"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="登录名" id="dlg_login_user" readonly="true"></powersi:textfield>
					<powersi:textfield label="用户地址" id="dlg_user_address" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="操作名称" id="dlg_operate_name" readonly="true"></powersi:textfield>
					<powersi:textfield label="业务编号" id="dlg_biz_id" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="操作地址" id="dlg_operate_action" readonly="true" colspan="3"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="日志消息" id="dlg_log_message" readonly="true" colspan="3" rows="10"></powersi:textarea>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="日志异常" id="dlg_log_exception" readonly="true" colspan="3" rows="10"></powersi:textarea>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
	/*错误红色标注*/
	function gridRowRender(rowdata, rowid){
		if (rowdata['log_level'] >= '5') {
			return getColorStyle('error');
		}
	}
	
	/*根据参数生成导出文件名*/
	function getExportFileName() {
		var filename = '系统操作日志(' + $('#beginDate').val().substring(0, 10)
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
			dlg = popupDiv("#logDlg", "查看系统日志", 600, {modal: false, showMax: true});
		}
	}
</script>
</body>
</powersi:html>