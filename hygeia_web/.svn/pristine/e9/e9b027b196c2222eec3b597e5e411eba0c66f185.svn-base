<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute("clusters", com.powersi.hygeia.framework.ClusterMapping.getCodetable());
%>
<powersi:html>
<head>
<powersi:head title="服务器日志查询" />
</head>
<body>
	<powersi:panelbox key="panel_query" title="服务器日志">
		<powersi:panelbox-toolbar>
			<powersi:button key="button_refresh" value="刷新日志列表" id="btRefresh" onclick="refreshData() "/>
			<powersi:button key="button_download" value="批量日志下载" id="btDownload" onclick="downloadLogs()" cssClass="btn btn-success" />
			<powersi:button key="button_clean" value="清理全部日志" id="btClear" onclick="clearAll()" cssClass="btn btn-danger" />
		</powersi:panelbox-toolbar>
		<powersi:form id="form1" action="QuerySystemLog" namespace="/manager">
			<powersi:editorlayout cols="10%,20%,10%,20%,10%,30%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="serverId" name="serverId" label="服务器"
						list="#request.clusters" listKey="key" listValue="value" onchange="selectServer()" cssClass="select2">
					</powersi:codeselect>
					<powersi:textfield id="hostName" name="hostName" label="主机名" readonly="true" />
					<powersi:textfield id="logPath" name="logPath" label="日志路径" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" title="日志列表">
		<powersi:datagrid id="grid" formId="form1" dataAction="local" checkbox="true" 
			exportFileName="'syslog'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column key="file_name" name="file_name" width="50%" minWidth="150" />
			<powersi:datagrid-column key="file_length" name="file_length" width="50%" minWidth="100" type="int" />
			<powersi:datagrid-column key="last_modified" name="last_modified" width="150" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	$(function(){
		$('#form1 .text').each(function(){
			var that = $(this);
			that.attr('title', that.val());
		});
	});

	var serverUrl = "";
	function createProxy(url){
		if(serverUrl == ""){
			return url;
		} else {
			return rootPath + "/ProcessCluster?url=" + encode64(encodeURI(serverUrl + url));	
		}
	}
	
	function selectServer(){
		serverUrl = $('#serverId').val();
		
		postAjax(createProxy(rootPath + '/manager/QuerySystemLog.action'), {'server': 'true'}, function(res){
			if(res.data){
				$('#hostName').val(res.data["host_name"] || '');
				$('#logPath').val(res.data["log_path"] || '');
			} else{
				$('#hostName').val('');
				$('#logPath').val(res.message || '');
			}
			
			//为了避免服务器产生多个session，不要同时异步调用
			grid.set('url', createProxy(rootPath + '/manager/QuerySystemLog.action'));
		});
	}
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<a href="');
		a.push(createProxy(rootPath + '/manager/ViewSystemLog.action?fileName=' + row['file_name']));
		a.push('" title="查看或下载日志文件">查看</a>');
		
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="清理" class="linkButton" title="清理日志文件"');
		a.push(' onclick="clearServerFile(');
		a.push(index);
		a.push(')"');
		
		a.push(" />");
		
		return a.join('');
	}
	
	function clearServerFile(index){
		var row = grid.getRow(index);
		var fileName = row["file_name"];
		if(!confirm("您确认清理 " + fileName + " 吗？")){
			return;
		}
		
		postJSON(createProxy(rootPath + "/manager/ClearSystemLog.action"), {"fileName": fileName}, function(json){
			if(!checkJSONResult(json)){
				return;
			}
			
			grid.updateCell("file_length", 0, index);
			
			alert(json.message);
		});
	}
	
	function clearAll(){
		if(!confirm("您确认清理所有日志文件吗？")){
			return;
		}
		
		postJSON(createProxy(rootPath + "/manager/ClearSystemLog.action"), {"fileName": "all"}, function(json){
			if(!checkJSONResult(json)){
				return;
			}
		
			refreshData();
		});
	}
	
	function refreshData() {
		if(grid){
			//清除状态
			grid.reset();
			
			//强制刷新
			grid.reload(true);
		}
	}
	
	function downloadLogs() {
		var files = "";
		var tip = "";
		if($(".l-grid-hd-row", grid.grid.gridheader).hasClass('l-checked')){
			tip = "请确认是否下载全部日志文件？";
			files = "all";
		} else {
			var rows = grid.getSelectedRows();
			if(powersi.rows_length(rows) == 0){
				popupAlert("请选择需要下载的日志文件");
				return;
			}
			
			var a = powersi.rows_extract(rows, "file_name");
			tip = "请确认是否下载以下日志文件：\n" + a.join("\n");
			files = a.join(",");
		}
		
		popupConfirm(tip, "提示", function(yes){
			if(yes){
				postDownload(createProxy(rootPath + "/manager/ViewSystemLog.action"), {"zip": "true", "fileName": files});
			}
		});
	}
</script>
</body>
</powersi:html>