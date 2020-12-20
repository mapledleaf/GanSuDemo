<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	request.setAttribute("clusters", com.powersi.hygeia.framework.ClusterMapping.getCodetable());
	
	request.setAttribute("clustersEnabled", String.valueOf(com.powersi.hygeia.framework.ClusterMapping.isClusters()));
%>
<powersi:html>
<head>
<powersi:head title="服务器信息" />
<style type="text/css">
html,body{
	overflow:hidden;
}
.info{
	text-align: left;
	overflow: auto;
	word-break:break-all;
}
</style>
<s:if test="#request.clustersEnabled == 'true'">
<script type="text/javascript">
 function refreshAll(){
	 popupConfirm("确认刷新全部应用缓存吗？", "提示", function(yes){
		 if(yes){
			 $('#btAll').prop('disabled', true);
			 popupDialog(rootPath + "/RefreshAction", 600, 800);
			 $('#btAll').removeProp('disabled');
		 }
	 });
 }
</script>
</s:if>
</head>
<body>
	<powersi:panelbox key="panel_query" title="服务器">
		<powersi:form id="form1" disabled="true">
			<powersi:editorlayout cols="10%,40%,50%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="serverId" name="serverId" label="服务器"
						list="#request.clusters" listKey="key" listValue="value" onchange="selectServer()" cssClass="select2">
					</powersi:codeselect>
					<powersi:editorlayout-button>
						<powersi:hidden name="refresh" value="true" />
							<powersi:button value="刷新应用缓存" id="btRefresh" key="button_refresh" onclick="refreshApp()" />
							<s:if test="#request.clustersEnabled == 'true'">
								<powersi:button value="刷新集群缓存" id="btAll" key="button_sync" onclick="refreshAll()" cssClass="btn btn-danger" />
							</s:if>
							<powersi:button value="配置集群服务器" id="btCluster" onclick="configCluster()" buttonIcon="icon-fa fa-server" cssClass="btn btn-success" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<div class="divCenter">
		<iframe frameborder="0" name="mainFrm" id="mainFrm" src="${rootPath }/manager/MappingManager.action" width="100%" height="100"></iframe>
	</div>
	<div class="hidden">
		<powersi:codetable id="validList" codeType="valid_flag"></powersi:codetable>
		<div id="dlg">
			<powersi:datagrid id="grid" rowAttrRender="gridRowRender" height="300" dataAction="local"
				usePager="false" showReload="false" 
				pageStatMessage="<span class='textError' style='padding-right:100px'>使用服务器内网地址和端口，不需要配置应用名</span><span>共 {total} 条</span>"
				exportFileName="'集群配置'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true"
				allowUnSelectRow="true" rowDraggable="true" enabledEdit="true">
				<powersi:toolbar>
					<powersi:toolbar-item id="add" text="新增" icon="add2" click="itemClick" title="新增服务器"/>
					<powersi:toolbar-item id="del" text="删除" icon="delete2" click="itemClick" title="删除服务器(按住ctrl键可多选)"/>
					<powersi:toolbar-item id="up" text="上移" icon="up" click="itemClick" title="上移(按住行号可以拖拽)"/>
					<powersi:toolbar-item id="down" text="下移" icon="down" click="itemClick" title="下移(按住行号可以拖拽)"/>
					<powersi:toolbar-item id="sort" text="自动命名" icon="sort2" click="itemClick" title="自动生成服务器名称"/>
					<powersi:toolbar-item id="cancel" text="取消" icon="cross2" click="itemClick" title="取消修改" position="right" />
					<powersi:toolbar-item id="save" text="保存" icon="tick2" click="itemClick" title="保存修改" position="right" />
				</powersi:toolbar>
				<powersi:datagrid-column display="服务器名称" name="cluster_name" width="100" editor="{type: 'string'}"/>
				<powersi:datagrid-column display="服务器地址" name="cluster_url" width="100%" editor="{type: 'string'}"/>
				<powersi:datagrid-column display="响应时间" name="response_time" width="60" />
				<powersi:datagrid-column display="有效标志" name="valid_flag" width="60" render="renderFlag" editor="{type: 'select', data:validList}"/>
			</powersi:datagrid>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
$(function(){
	addHighlyAdaptive('mainFrm');
});
 
function createProxy(url){
	var serverUrl = $('#serverId').val();
	if(serverUrl == ""){
		return url;
	} else {
		return rootPath + "/ProcessCluster?url=" + encode64(encodeURI(serverUrl + url));	
	}
}

function selectServer(){
	$('#mainFrm').attr('src', createProxy(rootPath + "/manager/MappingManager.action?tm=" + powersi.timestamp()));
}

function refreshApp(){
	$('#mainFrm').attr('src', createProxy(rootPath + "/manager/MappingManager.action?refresh=true&tm=" + powersi.timestamp()));
}

var dlg = null;
function configCluster(){
	if(dlg){
		dlg.show();
	} else{
		grid.setUrl(rootPath + '/manager/ClusterManager!query.action?ping=true');
		dlg = popupDiv('#dlg', '集群服务器配置', 600);
	}
}

function renderFlag(rowdata, index, value) {
	if (value === '0') {
		return '<span title="无效"><i class="icon-remove textError"></i></span>';
	} else {
		return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
	}
}

function gridRowRender(rowdata, rowid){
	if (rowdata['valid_flag'] === '0') {
		return getColorStyle('error');
	}
}

function itemClick(item){
	var itemid = item["id"];
	
	//结束编辑状态
	grid.endEdit();
	
	if(itemid == "add"){
		grid.addEditRow({'valid_flag': '1'});
	} else if(itemid == "del"){
		grid.deleteSelectedRow();
	} else if(itemid == "up"){
		grid.up(grid.getSelected());
	} else if(itemid == "down"){
		grid.down(grid.getSelected());
	} else if(itemid == "sort"){
		sortCluster();
	} else if(itemid == "cancel"){
		grid.reload(true);
	} else if(itemid == "save"){
		saveCluster();
	}
}

function sortCluster() {
	var rows = grid.getRows();
	var len = powersi.length(rows).toString().length;
	
	var i = 0;
	var colidx = grid.getColumnIndex('cluster_name');
	$.each(rows, function(rowid, row){
		i ++;
		
		grid.updateCell(colidx, 'server' + powersi.lpad(i, len, '0'), rowid);
		grid.setCellEdited(row, colidx, true);
	});
}

function saveCluster() {
	grid.endEdit();
	
	if(powersi.length(grid.getChangeRows())== 0){
		popupInfo('没有修改，无需保存', 5000);
		return;
	}
	
	var rows = grid.getAllData();
	var a = [];
	$.each(rows, function(n, row){
		if(powersi.length(row['cluster_name']) == 0){
			a.push('第 ' + (n + 1) + ' 行服务器名不能为空');
		}
		if(powersi.length(row['cluster_url']) == 0){
			a.push('第 ' + (n + 1) + ' 行服务器URL不能为空');
		}
	});
	if(a.length > 0){
		popupAlert(a.join('\n'));
		return;
	}
	
	postAjax(rootPath + '/manager/ClusterManager!save.action', {data: powersi.tostring(rows)}, function(res){
		if(!checkResult(res)){
			return;
		}
		
		window.location.href = window.location.href;
	});
}
</script>
</body>
</powersi:html>