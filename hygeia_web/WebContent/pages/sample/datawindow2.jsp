<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%
	//获取全部配置
	String dwList = com.powersi.hygeia.web.util.JsonHelper.toJson(com.powersi.hygeia.framework.cache.DBCacheManager.getInstance().getCache("datawindow").getData());
	
	//获取aa10配置
	String dwAa10 = com.powersi.hygeia.web.util.JsonHelper.toJson((java.util.Map) com.powersi.hygeia.framework.cache.DBCacheManager.getInstance().getCache("datawindow").find("dw_aa10", java.util.Collections.EMPTY_MAP));
%>
<powersi:html>
<head>
<powersi:head title="动态数据窗口" />
</head>
<body>
<powersi:form name="logForm" disabled="true">
	<powersi:panelbox title="选择条件">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:select id="fileName" name="fileName" label="文件名" cssClass="select2" required="true" />
				<powersi:select id="objectName" name="objectName" label="对象名" cssClass="select2" required="true" />
				<powersi:editorlayout-label>
					<a href="javascript:showConfigInfo()" class="linkbutton" title="显示详细配置信息">数据库配置</a>
				</powersi:editorlayout-label>
				<powersi:editorlayout-input>
					<powersi:textfield id="configInfo" name="configInfo" readonly="true" />
				</powersi:editorlayout-input>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="datawindowName" name="datawindowName" label="数据窗口名" readonly="true" />
				<powersi:textfield id="columnNames" name="columnNames" label="列名集合" colspan="3" readonly="true" title="红色表示配置跟实际数据窗口不一致" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox title="数据窗口">
		<powersi:datawindow id="dw_1" width="100%" height="100%" name="dw_aa10" value=""></powersi:datawindow>
	</powersi:panelbox>
</powersi:form>
<powersi:errors />
<script type="text/javascript">
var dwList = <%=dwList%>;
var dwAa10 = <%=dwAa10%>;
var fileMap = {};
var objMap = {};
$(function(){
	//按文件名和对象名分组存放数据窗口
	$.each(dwList, function(n, value){
		var lst = fileMap[value["file_name"]];
		if(lst == null){
			lst = [];
			fileMap[value["file_name"]] = lst;
		}
		lst.push(value);
		
		objMap[value["datawindow_name"]] = value;
	});
	
	//对象名变化
	$('#objectName').change(function() {
		chgDatawindow(objMap[$(this).val()]);
	});
	
	//文件名变化
	$('#fileName').change(function() {
		$('#objectName').empty();
		var objs = [];
		$.each(fileMap[$(this).val()], function(n, value){
			objs.push('<option value="' + value["datawindow_name"] + '">');
			objs.push(value["datawindow_object"]);
			objs.push('</options>');
		});
		$('#objectName').html(objs.join('')).change();
	});
	
	//填充文件名
	var files = [];	
	
	//优先显示datawindow.pbd
	if(fileMap["datawindow.pbd"] != null){
		files.push('<option value="datawindow.pbd">datawindow.pbd</option>');
	}
	$.each(fileMap, function(key, value){
		if(key != 'datawindow.pbd')
			files.push('<option value="' + key + '">' + key + '</option>');
	});
	$('#fileName').html(files.join('')).change();
});

function chgDatawindow(cfg){
	var dw_1 = S('dw_1');
	if(cfg && cfg["datawindow_name"]) {
		//切换数据窗口
		dw_1.SourceFileName = rootPath + "/resource/datawindow/" + cfg["file_name"];
		dw_1.DataWindowObject = cfg["datawindow_object"];
		
		//显示配置信息
		$('#datawindowName').val(cfg['datawindow_name'] || '');
		var cols = getDataWindowColumns('dw_1');
		if(cols.length == 0){
			$('#columnNames').val(cfg["datawindow_object"] + "不存在");
		} else{
			$('#columnNames').val(cols);
		}
		
		//比较数据库配置与实际列集合是否相同
		if(cols.length == 0 || cols != cfg["column_list"]){
			$('#columnNames').addClass('textError');
		} else{
			$('#columnNames').removeClass('textError');
		}
		
		$('#configInfo').val(powersi.tostring(cfg));
	} else{
		dw_1.SourceFileName = "";
		dw_1.DataWindowObject = "";
		
		$('#datawindowName').val('');
		$('#columnNames').val('');
		$('#configInfo').val('');
	}
}

function showConfigInfo(){
	popupInfo($('#configInfo').val());
}
</script>
</body>
</powersi:html>