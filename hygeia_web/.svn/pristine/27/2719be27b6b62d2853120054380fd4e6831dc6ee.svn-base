<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="请求日志分析" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body>
	<powersi:form id="queryForm" disabled="true">
		<powersi:panelbox title="请求日志" key="panel_query" allowAddition="true">
			<powersi:panelbox-toolbar>
				<powersi:button type="submit" id="btViewReuslt" value="查询请求日志" title="查询请求分析日志(sys_monitor_access)"
					onclick="viewResult()"  key="button_refresh" />
				
				<powersi:button type="button" id="btServer" value="导入服务器日志" title="从服务器获取日志文件导入请求日志表"
					onclick="openServerDlg()"  key="button_import" />
					
				<powersi:button type="button" id="btLocal" value="导入本地文件" title="从本地获取日志文件导入请求日志表"
					onclick="openLocalDlg()"  key="button_upload" />
				
				<powersi:button type="button" id="btClearDatabase" value="清理请求日志" title="清空请求分析日志(sys_monitor_access)" 
					onclick="clearDatabaseLog()" key="button_clean" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row addition="false">
					<powersi:textfield id="begin_date" name="begin_date" key="begin_date" mask="datetime" />
					<powersi:textfield id="end_date" name="end_date" key="end_date" mask="datetime" />
					<powersi:textfield id="req_url" name="req_url" label="请求地址"
						placeholder="支持表达式" title="空格( )表示AND，竖线(|)表示OR，减号(-)表示NOT，双引号(\"x\")表示=x，双引号(\"\")表示NULL" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row addition="true">
					<powersi:checkboxlist id="req_type" name="req_type" label="请求类型" colspan="5"
						list="#{'R':'Resource','J':'JSP','A':'Action','S':'Service'}" listKey="key" listValue="value" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<div class="space"></div>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="运行时间一览" />
		<powersi:tab id="tab2" target="divTab2" label="内存变化一览" />
		<powersi:tab id="tab3" target="divTab3" label="访问时间点一览" />
		<powersi:tab id="tab4" target="divTab4" label="访问频度一览" />
		<div id="divTab1">
			<powersi:datagrid id="gridTime" url="${rootPath}/manager/AccessMonitor!getRunPerform.action?type=time"
				frozen="false" delayLoad="true"
				exportFileName="'请求运行时间'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
				
				<powersi:datagrid-column display="请求号" name="req_no" width="150" hide="true" />
				<powersi:datagrid-column display="请求类型" name="req_type" width="80" />
				<powersi:datagrid-column display="请求资源" name="req_url" width="100%" minWidth="150" />
				<powersi:datagrid-column display="运行耗时(ms)" name="run_time" width="150" format="{0,number,###,###,###}" />
				<powersi:datagrid-column display="开始时间" name="begin_time" width="180" />
				<powersi:datagrid-column display="结束时间" name="end_time" width="180" />
			</powersi:datagrid>
		</div>
		<div id="divTab2">
			<powersi:datagrid id="gridJvm" url="${rootPath}/manager/AccessMonitor!getRunPerform.action?type=jvm"
				frozen="false" delayLoad="true"
				exportFileName="'请求内存变化'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
				
					<powersi:datagrid-column display="请求号" name="req_no" width="150" hide="true" />
					<powersi:datagrid-column display="请求类型" name="req_type" width="80" />
					<powersi:datagrid-column display="请求资源" name="req_url" width="100%" minWidth="150" />
					<powersi:datagrid-column display="请求时间" name="begin_time" width="180" />
					<powersi:datagrid-column display="开始使用率(%)" name="begin_used" width="100" format="{0,number,##.00%}" />
					<powersi:datagrid-column display="结束使用率(%)" name="end_used" width="100" format="{0,number,##.00%}" />
					<powersi:datagrid-column display="内存变化" name="run_jvm" width="100" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="开始内存" name="begin_jvm" width="150" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="结束内存" name="end_jvm" width="150" format="{0,number,###,###,###}" />
			</powersi:datagrid>
		</div>
		<div id="divTab3">
			<powersi:datagrid id="gridAcc" url="${rootPath}/manager/AccessMonitor!getRunPerform.action?type=acc"
				frozen="false" delayLoad="true"
				exportFileName="'请求内存变化'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
				
					<powersi:datagrid-column display="时间点" name="req_time_mi" width="150" />
					<powersi:datagrid-column display="请求数" name="req_count" width="100" />
					<powersi:datagrid-column display="内存数" name="jvm_total" width="150" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="最大可用数" name="max_free" width="50%" minWidth="150" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="最小可用数" name="min_free" width="50%" minWidth="150" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="最大使用率(%)" name="max_used" width="100" format="{0,number,##.00%}" />
					<powersi:datagrid-column display="最小使用率(%)" name="min_used" width="100" format="{0,number,##.00%}" />
			</powersi:datagrid>
		</div>
		<div id="divTab4">
			<powersi:datagrid id="gridFunc" url="${rootPath}/manager/AccessMonitor!getRunPerform.action?type=func"
				frozen="false" delayLoad="true"
				exportFileName="'请求内存变化'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column display="请求资源" name="req_url" width="100%" minWidth="150" />
					<powersi:datagrid-column display="请求类型" name="req_type" width="80" />
					<powersi:datagrid-column display="请求数" name="req_num" width="100" />
					<powersi:datagrid-column display="最长执行耗时(ms)" name="req_time_max" width="150" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="最短执行耗时(ms)" name="req_time_min" width="150" format="{0,number,###,###,###}" />
					<powersi:datagrid-column display="平均执行耗时(ms)" name="req_time_avg" width="150" format="{0,number,###,###,###}" />
			</powersi:datagrid>
		</div>
	</powersi:tabbedpanel>

	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="uploadServerDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请指定服务器日志文件名</span></div>
			<div style="margin: 5px 0 20px 0;">
				<input type="text" id="server_file" class="text" value="monitoring.log" title="服务器日志文件名" />
			</div>
			<div class="textRight">
				<powersi:button id="btUploadServerFile" onclick="uploadServerFile()" key="button_import" title="导入指定日志文件名的服务器日志" />
				<powersi:button id="btClearServer" onclick="clearServerLog()" key="button_clean" title="清理指定日志文件名的服务器日志" />
				<powersi:button id="btServerClose" onclick="closeServerDlg()" key="button_close" />
			</div>
		</div>
		
		<div id="uploadLocalDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择本地日志文件</span></div>
			<div style="margin: 5px 0 20px 0;">
				<input type="file" name="uploads" id="access_file" class="file" />
			</div>
			<div class="textRight">
				<powersi:button id="btUpLoadLocalFile" onclick="uploadLocalFile()" key="button_import" title="导入选定的本地日志文件" />
				<powersi:button id="btLocalClose" onclick="closeLocalDlg()" key="button_close" />
			</div>
		</div>
	</div>
<script type="text/javascript">
	function uploadLocalFile() {
		var fileName = $('#access_file').val();
		if(powersi.isempty(fileName)){
			alert("请选择要导入的本地日志文件");
			return;
		}
		
		if(confirm("您确认导入文件" + fileName + "吗?") != true){
			return;
		}
		
		closeLocalDlg();
		
		showRunning(true);
		
		try{
			var fileUpload = $('#access_file');
			fileUpload.fileupload({
		        url: rootPath + "/manager/AccessMonitor!makeLogList4File.action?type=local",
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
			fileUpload.fileupload('send', {
		        fileInput: fileUpload
		    }).complete(function (result, textStatus, jqXHR) {showRunning(false);});
		} catch(ex){
			alert(ex.message);
			showRunning(false);
		}
	}
	
	function uploadServerFile() {
		var access_file = $('#server_file').val();
		if (access_file == "") {
			alert("请输入服务器日志文件名");
			return;
		}
		
		closeServerDlg();
		
		postAjax(rootPath + "/manager/AccessMonitor!makeLogList4File.action?type=server", {"filename": access_file}, function(json){
			alert(json.message);
		});
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
		
		postAjax(rootPath + "/manager/AccessMonitor!clearPerformRs.action?filename="+access_file, function(json){
			alert(json.message);
		});
	}
	
	function viewResult() {
		if(!checkForm('#queryForm')){
			return;
		}
		
		var json = form2json('#queryForm');
		
		gridTime.setParms(json);
		gridJvm.setParms(json);
		gridAcc.setParms(json);
		gridFunc.setParms(json);
		
		gridTime.reset();
		gridJvm.reset();
		gridAcc.reset();
		gridFunc.reset();
		
		gridTime.reload(true);
		gridJvm.reload(true);
		gridAcc.reload(true);
		gridFunc.reload(true);
	}
	
	function clearDatabaseLog() {
		if(!confirm('您确认清理数据库日志吗？')){
			return;
		}
		
		postAjax(rootPath + "/manager/AccessMonitor!clearPerformRs.action", function(json){
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