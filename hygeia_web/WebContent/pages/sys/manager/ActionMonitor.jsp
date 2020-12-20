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
<powersi:head title="操作日志分析" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body>
	<powersi:form id="queryForm" disabled="true">
		<powersi:panelbox title="操作日志" key="panel_query" allowAddition="true">
			<powersi:panelbox-toolbar>
				<powersi:button type="submit" id="btViewReuslt" value="查询操作日志" title="查询操分析日志(sys_monitor_action)"
						onclick="viewResult()"  key="button_refresh" />
				<powersi:button type="button" id="btServer" value="导入服务器日志" title="从服务器获取日志文件导入操作日志表"
					onclick="openServerDlg()"  key="button_import" />
				<powersi:button type="button" id="btLocal" value="导入本地文件" title="从本地获取日志文件导入操作日志表"
					onclick="openLocalDlg()"  key="button_upload" />
				<powersi:button type="button" id="btClearDatabase" value="清理操作日志" title="清空操作分析日志(sys_monitor_action)" 
						onclick="clearDatabaseLog()" key="button_clean" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row addition="false">
					<powersi:textfield id="begin_date" name="begin_date" key="begin_date" mask="datetime" />
					<powersi:textfield id="end_date" name="end_date" key="end_date" mask="datetime" />
					<powersi:textfield id="action_url" name="action_text" label="操作地址"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="action_caller" name="action_error" label="执行方法"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:editorlayout-label>
						<powersi:label>总耗时</powersi:label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-input>
						<div class="input-group">
							<powersi:textfield id="totaltime_min" name="totaltime_min" validate="integer" title="最小值" />
							<span class="input-group-addon">-</span>
							<powersi:textfield id="totaltime_max" name="totaltime_max" validate="integer" title="最大值" />
						</div>
					</powersi:editorlayout-input>
					<powersi:textfield id="action_param" name="action_param" label="参数"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="action_result" name="action_result" label="结果"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
					<powersi:textfield id="action_view" name="action_view" label="视图"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	
	<div class="space"></div>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="运行时间一览" />
		<powersi:tab id="tab2" target="divTab2" label="调用频度一览" />
		<powersi:tab id="tab3" target="divTab3" label="访问时间点一览" />
		<div id="divTab1">
			<powersi:datagrid id="gridTime" url="${rootPath}/manager/ActionMonitor!getRunPerform.action?type=time&db=${db }"
				frozen="false" delayLoad="true" rowAttrRender="gridRowRender" useCount="false"
				detail="{onShowDetail: showDetail4Time, height:'auto'}" onDblClickRow="dblClickRow4Time"
				exportFileName="'操作运行时间'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
				<powersi:datagrid-column display="操作编号" name="action_no" width="150" hide="true" />
				<powersi:datagrid-column display="日志级别" name="log_level_name" width="80" code="log_level" data="log_level"/>
				<powersi:datagrid-column display="发生时间" name="action_date" width="150" />
				<powersi:datagrid-column display="总耗时(ms)" name="action_totaltime" width="100" format="{0,number,###,###,###}" />
				<powersi:datagrid-column display="操作地址" name="action_url" width="34%" minWidth="150" />
				<powersi:datagrid-column display="操作方法" name="action_caller" width="33%" minWidth="150" />
				<powersi:datagrid-column display="操作参数" name="action_param" width="33%" minWidth="150" isSort="false" />
				<powersi:datagrid-column display="操作结果" name="action_result" width="100" />
				<powersi:datagrid-column display="操作视图" name="action_view" width="150" />
			</powersi:datagrid>
		</div>
		<div id="divTab2">
			<powersi:datagrid id="gridCaller" url="${rootPath}/manager/ActionMonitor!getRunPerform.action?type=caller&db=${db }"
				frozen="false" delayLoad="true" useCount="false"
				detail="{onShowDetail: showDetail4Caller, height:'auto'}" onDblClickRow="dblClickRow4Caller"
				exportFileName="'操作调用频度'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column display="执行方法" name="action_caller" width="100%" minWidth="150" data="${id }" />
					<powersi:datagrid-column display="调用次数" name="action_num" width="100" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="运行耗时">
						<powersi:datagrid-column display="总运行耗时" name="action_totaltime_total" width="100" format="{0,number,###,###,###}" />
						<powersi:datagrid-column display="平均运行耗时" name="action_totaltime_avg" width="100" format="{0,number,###,###,###}" />
						<powersi:datagrid-column display="最长运行耗时" name="action_totaltime_max" width="100" format="{0,number,###,###,###}" />
						<powersi:datagrid-column display="最短运行耗时" name="action_totaltime_min" width="100" format="{0,number,###,###,###}" />
					</powersi:datagrid-column>
					<powersi:datagrid-column display="发生时间">
						<powersi:datagrid-column display="首次发生时间" name="action_begin_date" width="150" />
						<powersi:datagrid-column display="末次发生时间" name="action_end_date" width="150" />
					</powersi:datagrid-column>
			</powersi:datagrid>
		</div>
		<div id="divTab3">
			<powersi:datagrid id="gridDate" url="${rootPath}/manager/ActionMonitor!getRunPerform.action?type=date&db=${db }"
				frozen="false" delayLoad="true" useCount="false"
				detail="{onShowDetail: showDetail4Date, height:'auto'}" onDblClickRow="dblClickRow4Date"
				exportFileName="'操作访问时间点'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column display="时间点" name="action_date" width="120" data="${id }"/>
					<powersi:datagrid-column display="调用次数" name="action_num" width="100" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="运行耗时">
						<powersi:datagrid-column display="总运行耗时" name="action_totaltime_total" width="25%" format="{0,number,###,###,###}" />
						<powersi:datagrid-column display="平均运行耗时" name="action_totaltime_avg" width="25%" format="{0,number,###,###,###}" />
						<powersi:datagrid-column display="最长运行耗时" name="action_totaltime_max" width="25%" format="{0,number,###,###,###}" />
						<powersi:datagrid-column display="最短运行耗时" name="action_totaltime_min" width="25%" format="{0,number,###,###,###}" />
					</powersi:datagrid-column>
			</powersi:datagrid>
		</div>
	</powersi:tabbedpanel>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="uploadServerDlg">
			<div class="textLeft"><label class="textInfo" for="server_file"><i class="icon-info-sign"></i> 请指定服务器日志文件名</label></div>
			<div style="margin: 5px 0 10px 0;">
				<input type="text" id="server_file" class="text" value="baseaction.log" title="服务器日志文件名" />
			</div>
			<div class="textLeft"><label for="txtSkipDateServer" class="textInfo"><i class="icon-time"></i> 日志开始时间(格式2008-08-08 08:08:08,888)</label></div>
			<div style="margin:5px 0 10px 0;">
				<input type="text" name="skipdate" id="txtSkipDateServer" class="text" title="日志导入开始时间，解决日志重复导入" placeholder="缺省导入全部日志"></input>
			</div>
			<div class="divButton">
				<div class="floatLeft">
					<input type="checkbox" name="server-trunc" value="true" id="cbxCleanServer" class="checkbox" />
 					<label for="cbxCleanServer" class="checkboxErrorLabel">删除已上传日志</label> 
 				</div>
 				<div class="floatRight">
					<powersi:button id="btUploadServerFile" onclick="uploadServerFile()" key="button_import" title="导入指定日志文件名的服务器日志" />
					<powersi:button id="btClearServer" onclick="clearServerLog()" key="button_clean" title="清理指定日志文件名的服务器日志" />
					<powersi:button id="btServerClose" onclick="closeServerDlg()" key="button_close" />
				</div>
			</div>
		</div>
		
		<div id="uploadLocalDlg">
			<div class="textLeft"><label class="textInfo" for="access_file"><i class="icon-info-sign"></i> 请选择本地日志文件</label></div>
			<div style="margin:5px 0 20px 0;">
				<input type="file" name="uploads" id="access_file" class="file" />
			</div>
			<div class="textLeft"><label for="txtSkipDateLocal" class="textInfo"><i class="icon-time"></i> 日志开始时间(格式2008-08-08 08:08:08,888)</label></div>
			<div style="margin:5px 0 10px 0;">
				<input type="text" name="skipdate" id="txtSkipDateLocal" class="text" title="日志导入开始时间，解决日志重复导入" placeholder="缺省导入全部日志"></input>
			</div>
			<div class="divButton">
				<div class="floatLeft">
					<input type="checkbox" name="local-trunc" value="true" id="cbxCleanLocal" class="checkbox" />
 					<label for="cbxCleanLocal" class="checkboxErrorLabel">删除已上传日志</label> 
 				</div>
 				<div class="floatRight">
					<powersi:button id="btUpLoadLocalFile" onclick="uploadLocalFile()" key="button_import" title="导入选定的本地日志文件" />
					<powersi:button id="btLocalClose" onclick="closeLocalDlg()" key="button_close" />
				</div>
			</div>
		</div>
		
		<div id="detailDlg">
			<form id="detailForm">
				<powersi:editorlayout cols="6">
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_action_no" name="action_no" label="操作编号" readonly="true"></powersi:textfield>
						<powersi:textfield id="detail_action_date" name="action_date" label="发生时间" readonly="true"></powersi:textfield>
						<powersi:textfield id="detail_log_level_name" name="log_level_name" label="日志级别" readonly="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_action_totaltime" name="action_totaltime" label="总耗时" readonly="true"></powersi:textfield>
						<powersi:textfield id="detail_action_caller" name="action_caller" label="操作调用者" colspan="3" readonly="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_action_url" name="action_url" label="操作地址" readonly="true" colspan="5"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="detail_action_result" name="action_result" label="操作结果" readonly="true"></powersi:textfield>
						<powersi:textfield id="detail_action_view" name="action_view" label="操作视图" colspan="3" readonly="true"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea id="detail_action_text" name="action_param" label="操作参数" rows="10" colspan="5" readonly="true"></powersi:textarea>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</form>
		</div>
	</div>
<powersi:script>
	var db = '<%=db %>';
	function showDetail4Time(row, detailPanel,callback) {
		try{
			var suffix = '_' + row['action_no'];
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
		<powersi:datagrid id="gridOption" url="${rootPath }/manager/ActionMonitor!getRunPerform.action?type=time&db=${db }"
			isScroll="false" pageSize="10" frozen="false" delayLoad="true" useCount="false"
			rowAttrRender="gridRowRender" onDblClickRow="dblClickRow4CallerDetail"
			showExportExcel="true" exportFileName="'操作运行时间'">
			<powersi:datagrid-column display="操作编号" name="action_no" width="150" hide="true" />
			<powersi:datagrid-column display="日志级别" name="log_level_name" width="80" code="log_level" data="log_level" hide="true" />
			<powersi:datagrid-column display="发生时间" name="action_date" width="150" />
			<powersi:datagrid-column display="总耗时(ms)" name="action_totaltime" width="100" format="{0,number,###,###,###}" />
			<powersi:datagrid-column display="操作地址" name="action_url" width="34%" minWidth="150" />
			<powersi:datagrid-column display="操作方法" name="action_caller" width="33%" minWidth="150" />
			<powersi:datagrid-column display="操作参数" name="action_param" width="33%" minWidth="150" isSort="false" />
			<powersi:datagrid-column display="操作结果" name="action_result" width="100" />
			<powersi:datagrid-column display="操作视图" name="action_view" width="150" />
		</powersi:datagrid>
		
		return gridOption;
	}
	
	function showDetail4Caller(row, detailPanel, callback) {
		    var div = document.createElement('div');
		    var width = $(detailPanel).append(div).width() - 15;

			var gridOption = createDetailGridOption();
		    gridOption['width'] = width;
		    
		    var json = form2json('#queryForm');
		    json['action_caller'] = '"' + row['action_caller'] + '"';
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
		    json['begin_date'] = row['action_date'] + ':00';
		    json['end_date'] = row['action_date'] + ':59';
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
	function gridRowRender(rowdata, rowid){
		if (rowdata['log_level'] >= '4') {
			return getColorStyle('error');
		}
	}
	
	function uploadLocalFile() {
		var fileName = $('#access_file').val();
		if(powersi.isempty(fileName)){
			alert("请选择要导入的本地日志文件");
			return;
		}
		
		if(confirm("您确认导入文件" + fileName + "吗?") != true){
			return;
		}
		
		showRunning(true);
		var fileUpload = $('#access_file');
		fileUpload.fileupload({
	        url: rootPath + "/manager/ActionMonitor!importLogFile.action",
	        formData: {'type': 'local', 
	        	'clearflag': $('#cbxCleanLocal').prop('checked') ? 'true' : 'false',
	        	'db': db, 
	        	'skipdate': $('#txtSkipDateLocal').val()
	        },
	        dataType: 'json',
	        autoUpload: false,
	        replaceFileInput: false,
	        done: function (e, data) {
	        	if(data.result.errortype == "0"){
	        		//成功后重置input file
	        		fileUpload.fileupload("destroy");
	        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
	        	}
	        	
	            showPageError(data.result);
	        },
	        fail: function (e, data) {
	            showPageError(data.message);
	        }
	    });
		
		closeLocalDlg();
		
		fileUpload.fileupload('send', {
	        fileInput: fileUpload
	    }).complete(function (result, textStatus, jqXHR) {showRunning(false);});
	}
	
	function uploadServerFile() {
		var access_file = $('#server_file').val();
		if (access_file == "") {
			alert("请输入服务器日志文件名");
			return;
		}
		
		closeServerDlg();
		
		postAjax(rootPath + "/manager/ActionMonitor!importLogFile.action", {
				"type":"server", "filename": access_file, 
				'clearflag': $('#cbxCleanServer').prop('checked') ? 'true' : 'false',
				'db': db,
				'skipdate': $('#txtSkipDateServer').val()
				}, 
				function(json){ showPageError(json);}
		);
	}
	
	function clearServerLog() {
		var access_file = $('#server_file').val();
		if (access_file == "") {
			alert("请输入服务器日志文件名");
			return;
		}
		
		if(!confirm('您确认清理服务器日志文件吗？')){
			return;
		}
		
		postAjax(rootPath + "/manager/ActionMonitor!clearPerform.action", {"filename":access_file, "db": db}, function(json){
			alert(json.message);
		});
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
	
	function clearDatabaseLog() {
		if(!confirm('您确认清理数据库日志吗？')){
			return;
		}
		
		postAjax(rootPath + "/manager/ActionMonitor!clearPerform.action", {"db": db}, function(json){
			alert(json.message);
		});
	}
	
	var dlgServer = null;
	function closeServerDlg(){
    	if(dlgServer){
    		dlgServer.hide();
    	}
    }
	
	function openServerDlg(){
		if(dlgServer){
			dlgServer.show();
		} else {
			dlgServer = popupDiv("#uploadServerDlg", '导入服务器文件', 350);			
		}
	}
	
	var dlgLocal = null;
	function closeLocalDlg(){
    	if(dlgLocal){
    		dlgLocal.hide();
    	}
    }
	
	function openLocalDlg(){
		if(dlgLocal){
			dlgLocal.show();
		} else{
			dlgLocal = popupDiv("#uploadLocalDlg", '导入本地文件', 350);			
		}
	}
</script>
</body>
</powersi:html>