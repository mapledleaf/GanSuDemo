<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<powersi:html>
<head>
<powersi:head title="操作日志查询" />
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" action="QueryOperateLog" namespace="/manager">
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
					<powersi:hidden id="beginDate" name="beginDate" key="begin_date" mask="datetime" required="true" />
					<powersi:hidden id="endDate" name="endDate" key="end_date" mask="datetime" required="true" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:checkboxlist list="#request.codeUserKind" name="userKind" value="#request.userKind" key="user_kind" required="true" checkAllButton="true" />
					<powersi:radio list="%{#{'all':'全部','1':'成功','2':'失败'}}" name="operateFlag" key="operate_flag" required="true" />
					<powersi:textfield id="remark" name="remark" key="remark"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:textfield id="loginUser" name="loginUser" key="login_user"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="userName" name="userName" key="user_name"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="operateName" name="operateName" key="operate_name"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="日志列表">
		<powersi:datagrid id="grid" formId="queryForm" 
			onDblClickRow="gridDblClickRow" rowAttrRender="gridRowRender" 
			exportFileName="getExportFileName" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:datagrid-column key="operate" width="50" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column key="userinfo">
				<powersi:datagrid-column key="user_kind" name="kind_name" width="100" />
				<powersi:datagrid-column key="login_user" name="login_user" width="100" />
				<powersi:datagrid-column key="user_name" name="user_name" width="100" />
			</powersi:datagrid-column>
			<powersi:datagrid-column display="操作信息">
				<powersi:datagrid-column key="operate_name" name="operate_name" width="50%" minWidth="150" />
				<powersi:datagrid-column display="操作时间" name="begin_date" width="150" />
				<powersi:datagrid-column key="operate_flag" name="operate_flag_name" code="operate_flag" data="operate_flag" width="80" render="renderFlag" />
				<powersi:datagrid-column key="remark" name="remark" width="50%" minWidth="100" />
			</powersi:datagrid-column>
		</powersi:datagrid>
	</powersi:panelbox>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="logDlg">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-head title="操作日志详细信息">
				</powersi:editorlayout-head>
				<powersi:editorlayout-row>
					<powersi:textfield key="operate_id" id="dlg_operate_id" readonly="true"></powersi:textfield>
					<powersi:textfield key="login_id" id="dlg_login_id" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield key="login_user" id="dlg_login_user" readonly="true"></powersi:textfield>
					<powersi:textfield key="user_name" id="dlg_user_name" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield key="user_kind" id="dlg_kind_name" readonly="true"></powersi:textfield>
					<powersi:textfield label="操作标志" id="dlg_operate_flag_name" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield key="begin_date" id="dlg_begin_date" readonly="true"></powersi:textfield>
					<powersi:textfield key="end_date" id="dlg_end_date" readonly="true"></powersi:textfield>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="操作参数" id="dlg_operate_param" readonly="true" colspan="3" rows="10"></powersi:textarea>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea key="remark" id="dlg_remark" readonly="true" colspan="3" rows="10"></powersi:textarea>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
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
	/*使用图标显示成功或者失败*/
	function renderFlag(rowdata, index, value) {
		if (rowdata['operate_flag'] == '2') {
			return '<span title="' + value + '"><i class="icon-remove"></i></span>';
		} else if(rowdata['operate_flag'] == '1'){
			return '<span title="' + value + '"><i class="icon-ok textSuccess"></i></span>';
		} else {
			return '<span title="' + value + '"><i class="icon-spinner textWarning"></i></span>';
		}
	}
	/*红色显示错误记录*/
	function gridRowRender(rowdata, rowid){
		if (rowdata['operate_flag'] == '2') {
			return getColorStyle('error');
		}
	}
	/*双击查看明细*/
	function gridDblClickRow(rowdata, rowindex, rowobj) {
		showLogInfo(rowdata);
	}
	/*获取明细信息*/
	function doView(index) {
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
			dlg = dlg = popupDiv("#logDlg", "查看操作日志", 600, {modal: false, showMax: true});
		}
	}
</script>
</body>
</powersi:html>