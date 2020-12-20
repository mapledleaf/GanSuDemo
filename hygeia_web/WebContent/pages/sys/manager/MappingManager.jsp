<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="缓存视图" />
<style type="text/css">
body{
	padding: 0;
}
.info {
	text-align: left;
	overflow: auto;
	word-break:break-all;
}
#db ol{padding-left: 20px;}
#db li > a{text-decoration: none;}
#db li > div{margin-left: 15px;}
</style>
</head>
<body>
	<div class="tabbable" id="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-fa fa-globe red"></i> 全局信息
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
					<i class="icon-fa fa-database green"></i> 数据库信息
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3"> 
					<i class="icon-fa fa-server blue"></i> 服务器信息
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane4" id="tab4"> 
					<i class="icon-fa fa-list yellow"></i> 缓存信息
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane6" id="tab6"> 
					<i class="icon-fa fa-history pink"></i> 版本信息
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane5" id="tab5"> 
					<i class="icon-fa fa-file-text-o orange"></i> 日志信息
				</a>
			</li>
		</ul>
		
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<div class="info">
					<s:if test='#request.contextInfo != null'>
						<%=request.getAttribute("contextInfo")%>
					</s:if>
				</div>
			</div>
			<div id="tab-pane2" class="tab-pane">
				<div id="db" class="info">
					<s:if test='#request.databaseInfo != null'>
						<%=request.getAttribute("databaseInfo")%>
					</s:if>
				</div>
			</div>
			<div id="tab-pane3" class="tab-pane">
				<div class="info">
					<s:if test='#request.serverInfo != null'>
						<%=request.getAttribute("serverInfo")%>
					</s:if>
				</div>
			</div>
			<div id="tab-pane4" class="tab-pane">
				<s:if test='#request.mappingInfo != null'>
					<div class="info">
						<table class="tableFrame">
							<thead>
								<tr>
									<th>缓存信息</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div><%=request.getAttribute("mappingInfo")%></div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</s:if>
				<s:else>
					<div style="padding:0 2px">
						<powersi:datagrid id="gridCache" name="info" useAjax="false" dataAction="local" rowAttrRender="gridRowRender"
						exportFileName="'缓存列表'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
							<powersi:toolbar>
								<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
							</powersi:toolbar>
							<powersi:datagrid-column display="缓存名" name="name" width="75%" minWidth="150" exportWidth="400" />
							<powersi:datagrid-column display="缓存大小" name="size" width="150" type="number" exportWidth="100" />
							<powersi:datagrid-column display="刷新次数" name="times" width="150" type="int" exportWidth="100" />
							<powersi:datagrid-column display="备注" name="remark" width="25%" minWidth="150" render="renderRemark" isSort="false" exportWidth="200" />
						</powersi:datagrid>
					</div>
				</s:else>
			</div>
			<div id="tab-pane5" class="tab-pane">
				<div class="info">
					<s:if test='#request.logInfo != null'>
						<%=request.getAttribute("logInfo")%>
					</s:if>
				</div>
			</div>
			
			<div id="tab-pane6" class="tab-pane">
				<div style="padding:0 2px">
					<powersi:datagrid id="gridJar" name="jarInfo" useAjax="false" dataAction="local" totalRender="renderTotal"
							exportFileName="'版本列表'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
						<powersi:toolbar>
							<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
						</powersi:toolbar>
						<powersi:datagrid-column display="文件名" name="file_name" width="75%" minWidth="150" exportWidth="200" />
						<powersi:datagrid-column display="版本号" name="file_version" width="25%" minWidth="150" exportWidth="150" />
						<powersi:datagrid-column display="文件大小" name="file_length" width="150" type="number" />
						<powersi:datagrid-column display="修改时间" name="last_modified" width="150"  />
					</powersi:datagrid>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="msgDlg">
			<powersi:textarea id="msgArea" readonly="true" cssClass="bordered" rows="20"></powersi:textarea>
		</div>
	</div>
	
	<powersi:script>
		<powersi:dataform id="condCache" prefixID="search1_toolbar_" inputWidth="200" labelWidth="50"
			showClose="false" sort="name">
			<powersi:dataform-field name="name" display="缓存名" />
		</powersi:dataform>
		
		<powersi:dataform id="condJar" prefixID="search2_toolbar_" inputWidth="200" labelWidth="50"
			showClose="false" sort="file_name">
			<powersi:dataform-field name="file_name" display="文件名" />
		</powersi:dataform>
	</powersi:script>
<powersi:errors />
<script type="text/javascript">
$(function(){
	setSize();
	$(window).resize(setSize);
	
	gridCache.bindCondition(condCache, 'search');
	gridJar.bindCondition(condJar, 'search');
 });
 
function setSize() {
	var height = getPageHeight() - 50;
	$(".info").height(height);
}
 
function toggleDetail(id) {
	var obj = document.getElementById(id);
	if(obj){
		if(obj.style.display == 'none'){
			obj.style.display = '';
		} else{
			obj.style.display = 'none';
		}
	}
}

function gridRowRender(rowdata, rowid){
	if(powersi.length(rowdata['remak']) > 0) {
		return getColorStyle('error');
	}
}

function renderRemark(row, index, value){
	if(value == null || value == ''){
		return "";
	} else{
		return '<a href="javascript:showRemark(' + index + ')">' + value + '</a>';
	}
}

var dlg = null;
function showRemark(index){
	var row = gridCache.getRow(index);
	$('#msgArea').val(row['message']);
	if(dlg){
		dlg.show();
	} else{
		dlg = popupDiv('#msgDlg', '错误信息', 600);
	}
}

function renderTotal(allData, currentData){
	var fileLen = 0;
	var lastDate = "";
	//计算本地全部数据
	var size = powersi.rows_length(allData['rows']);
	if(size > 0){
		$.each(allData['rows'], function(n, row){
			if(row['__status'] !== "delete") {
				fileLen += powersi.tonumber(row['file_length']);
				if(row["last_modified"] > lastDate){
					lastDate = row["last_modified"];
				}
			}
		});	
	}
	
	var a = [];
	a.push('<div class="divCenter textError">');
	
	a.push('<span>');
	a.push('共 <b>');
	a.push(size);
	a.push('</b> 项 ');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('文件大小合计 <b>');
	a.push(powersi.tothousands(fileLen));
	a.push('</b> 字节');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('最后更新时间 <b>');
	a.push(lastDate || "");
	a.push('</b>');
	a.push('</span>');
	
	a.push('</div>');
	
	return a.join('');
}
</script>
</body>
</powersi:html>