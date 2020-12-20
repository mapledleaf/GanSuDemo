<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String db = request.getParameter("db");
	if(db == null || db.length() == 0){
		db = "oracle";
	}
	request.setAttribute("db", db);
	request.setAttribute("id", "mongodb".equals(db) ? "_id" : "");
%>
<powersi:html>
<head>
<powersi:head title="登录日志分析" />
</head>
<body>
	<powersi:panelbox title="登录日志" key="panel_query" allowAddition="true">
		<powersi:form id="queryForm" disabled="true">
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row addition="false">
					<powersi:textfield id="begin_date" name="begin_date" key="begin_date" mask="datetime" required="true" />
					<powersi:textfield id="end_date" name="end_date" key="end_date" mask="datetime" required="true" />
					<powersi:editorlayout-button colspan="2">
						<powersi:button type="submit" id="btViewReuslt" value="查询登录日志" title="查询登录分析日志(sys_login_log)"
					onclick="viewResult()"  key="button_refresh" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:checkboxlist id="user_kind" name="user_kind" key="user_kind" codeType="user_kind" checkAllButton="true" />
					<powersi:textfield id="login_user" name="login_user" key="login_user"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="user_name" name="user_name" key="user_name"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<div class="space"></div>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="登录明细一览" />
		<powersi:tab id="tab2" target="divTab2" label="登录用户一览" />
		<powersi:tab id="tab3" target="divTab3" label="登录时间点一览" />
		<div id="divTab1">
			<powersi:datagrid id="gridTime" url="${rootPath}/manager/LoginLogAnalysis.action?type=time"
				frozen="false" delayLoad="true" rowAttrRender="gridRowRender" useCount="false"
				detail="{onShowDetail: showDetail4Time, height:'auto'}" onDblClickRow="dblClickRow4Time"
				exportFileName="'登录明细'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
				<powersi:datagrid-column key="user_kind" name="user_kind_name" code="user_kind" data="user_kind" width="100" />
				<powersi:datagrid-column key="login_user" name="login_user" width="100" />
				<powersi:datagrid-column key="user_name" name="user_name" width="100" />
				<powersi:datagrid-column key="login_address" name="login_address" width="100" />
				<powersi:datagrid-column key="login_date" name="login_date" width="150" />
				<powersi:datagrid-column key="logout_date" name="logout_date" width="150" />
				<powersi:datagrid-column key="login_flag" name="login_flag_name" code="login_flag" data="login_flag" width="80" render="renderFlag" />
				<powersi:datagrid-column key="remark" name="remark" width="100%" />
			</powersi:datagrid>
		</div>
		<div id="divTab2">
			<powersi:datagrid id="gridCaller" url="${rootPath}/manager/LoginLogAnalysis.action?type=caller"
				frozen="false" delayLoad="true" useCount="false"
				detail="{onShowDetail: showDetail4Caller, height:'auto'}" onDblClickRow="dblClickRow4Caller"
				exportFileName="'登录用户'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column key="user_kind" name="user_kind_name" code="user_kind" data="user_kind" width="100" />
					<powersi:datagrid-column key="login_user" name="login_user" minWidth="100" width="50%" />
					<powersi:datagrid-column key="user_name" name="user_name" minWidth="100" width="50%" />
					<powersi:datagrid-column display="登录次数" name="login_num" width="100" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="成功次数" name="success_num" width="100" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="失败次数" name="error_num" width="100" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="首次登录时间" name="min_login_date" width="150" />
					<powersi:datagrid-column display="末次登录时间" name="max_login_date" width="150" />
					<powersi:datagrid-column display="首次注销时间" name="min_logout_date" width="150" />
					<powersi:datagrid-column display="末次注销时间" name="max_logout_date" width="150" />
			</powersi:datagrid>
		</div>
		<div id="divTab3">
			<powersi:datagrid id="gridDate" url="${rootPath}/manager/LoginLogAnalysis.action?type=date"
				frozen="false" delayLoad="true" useCount="false"
				detail="{onShowDetail: showDetail4Date, height:'auto'}" onDblClickRow="dblClickRow4Date"
				exportFileName="'登录时间点'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column display="时间点" name="login_date" width="150" />
					<powersi:datagrid-column display="登录人数" name="login_sum" width="25%" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="登录次数" name="login_num" width="25%" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="成功次数" name="success_num" width="25%" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="失败次数" name="error_num" width="25%" format="{0,number,###,###,###}" />
			</powersi:datagrid>
		</div>
	</powersi:tabbedpanel>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="detailDlg">
			<form id="detailForm">
				<powersi:editorlayout cols="6">
					<powersi:editorlayout-row>
						<powersi:textfield key="login_id" id="dlg_login_id" readonly="true"></powersi:textfield>
						<powersi:textfield key="login_address" id="dlg_login_address" readonly="true"></powersi:textfield>
						<powersi:textfield key="login_flag" id="dlg_login_flag_name" name="login_flag_name" readonly="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield key="user_kind" id="dlg_kind_name" name="user_kind_name" readonly="true"></powersi:textfield>
						<powersi:textfield key="login_user" id="dlg_login_user" readonly="true"></powersi:textfield>
						<powersi:textfield key="user_name" id="dlg_user_name" readonly="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield key="login_date" id="dlg_login_date" readonly="true"></powersi:textfield>
						<powersi:textfield key="logout_date" id="dlg_logout_date" readonly="true"></powersi:textfield>
						<powersi:textfield key="remark" id="dlg_remark" readonly="true"></powersi:textfield>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</form>
		</div>
	</div>
<powersi:script>
	function showDetail4Time(row, detailPanel,callback) {
		try{
			var suffix = '_' + row['sql_no'];
			var rowIndex = gridTime.getRowIndex(row);
			
			//生成form
			var form = $('#detailForm').clone(true).attr('id', 'detail_form' + suffix);
			
			//复制data
			json2form(form, row);
			
			//复制id
			form.find("[id]").each(function(){
				var id = $(this).attr('id');
				var nid = $(this).attr('name') + suffix;
				form.find('label[for="' + id + '"]').attr('for', nid);
				$(this).attr("id", nid);
			});
			
			//处理css
			var $panel = $(detailPanel);
			form.find('table').css({width: $panel.width() - 10, margin: '10px 0 10px 10px'});
			
			//显示form
			$panel.append(form);
		}catch(ex){
			alert(ex.message);
		}
	}
	
	function dblClickRow4Time(data, rowid, rowdata){
		gridTime.toggleDetail(rowdata);
	}
	
	function createDetailGridOption(){
		<powersi:datagrid id="gridOption" url="${rootPath }/manager/LoginLogAnalysis.action?type=time"
			isScroll="false" pageSize="10" frozen="false" delayLoad="true" useCount="false"
			rowAttrRender="gridRowRender" onDblClickRow="dblClickRow4CallerDetail"
			showExportExcel="true" exportFileName="'登录明细'">
			<powersi:datagrid-column key="user_kind" name="user_kind_name" code="user_kind" data="user_kind" width="100" />
			<powersi:datagrid-column key="login_user" name="login_user" width="100" />
			<powersi:datagrid-column key="user_name" name="user_name" width="100" />
			<powersi:datagrid-column key="login_address" name="login_address" width="150" />
			<powersi:datagrid-column key="login_date" name="login_date" width="150" />
			<powersi:datagrid-column key="logout_date" name="logout_date" width="150" />
			<powersi:datagrid-column key="login_flag" name="login_flag_name" code="login_flag" data="login_flag" width="80" render="renderFlag" />
			<powersi:datagrid-column key="remark" name="remark" width="100%" />
		</powersi:datagrid>
		
		return gridOption;
	}
	
	function showDetail4Caller(row, detailPanel, callback) {
		    var div = document.createElement('div');
		    var width = $(detailPanel).append(div).width() - 15;

			var gridOption = createDetailGridOption();
		    gridOption['width'] = width;
		    
		    var json = form2json('#queryForm');
		    json['user_id'] = row['user_id'];
		    json['user_kind'] = row['user_kind'];
		    gridOption['parms'] = json;
		    
		    var grid = $(div).ligerGrid(gridOption);
		    $(div).wrap('<div style="margin:10px"></div>');

		    grid.refreshSize();
		    grid.reload(true);
	}
		
	function dblClickRow4Caller(data, rowid, rowdata){
		gridCaller.toggleDetail(rowdata);
	}
	
	function showDetail4Date(row, detailPanel, callback) {
		    var div = document.createElement('div');
		    var width = $(detailPanel).append(div).width() - 15;

			var gridOption = createDetailGridOption();
		    gridOption['width'] = width;
		    
		    var json = form2json('#queryForm');
		    json['begin_date'] = row['login_date'] + ' 00:00:00';
		    json['end_date'] = row['login_date'] + '23:59:59';
		    gridOption['parms'] = json;
		    
		    var grid = $(div).ligerGrid(gridOption);
		    $(div).wrap('<div style="margin:10px"></div>');

		    grid.refreshSize();
		    grid.reload(true);
	}
		
	function dblClickRow4Date(data, rowid, rowdata){
		gridDate.toggleDetail(rowdata);
	}
	
	var dlgDetail = null;
	function dblClickRow4CallerDetail(data, rowid, rowdata) {
		json2form('#detailForm', data);
		
		if(dlgDetail){
			dlgDetail.show();
		} else{
			dlgDetail = popupDiv("#detailDlg", '详细信息', 800, {showMax: true});
		}
	}
</powersi:script>
<script type="text/javascript">
	$(function(){
		$('#begin_date').val(moment().startOf('month').format("YYYY-MM-DD HH:mm:ss"));
		$('#end_date').val(moment().endOf('month').format("YYYY-MM-DD HH:mm:ss"));
	});
	
	function gridRowRender(rowdata, rowid){
		if (rowdata['login_flag'] == '0') {
			return getColorStyle('error');
		}
	}
	
	function renderFlag(rowdata, index, value) {
		if (rowdata['login_flag'] == '0') {
			return '<span title="' + value + '"><i class="icon-remove"></i></span>';
		} else {
			return '<span title="' + value + '"><i class="icon-ok textSuccess"></i></span>';
		}
	}
	
	function viewResult() {
		if(!checkForm('#queryForm')){
			return;
		}
		
		var json = form2json('#queryForm');
		gridTime.setParms(json);
		gridCaller.setParms(json);
		gridDate.setParms(json);
		
		gridTime.reset();
		gridCaller.reset();
		gridDate.reset();
		
		gridTime.reload(true);
		gridCaller.reload(true);
		gridDate.reload(true);
	}
</script>
</body>
</powersi:html>