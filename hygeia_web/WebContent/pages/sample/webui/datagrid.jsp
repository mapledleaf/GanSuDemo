<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String colName1 = "代码名称1";
	request.setAttribute("colName2", "代码名称2");
%>
<powersi:html>
<head>
<powersi:head title="datagrid示例"/>
</head>
<body>
	<powersi:panelbox key="panel_result" title="代码列表(1.条件栏 2.标题栏 3.工具栏 4.明细栏 5.汇总栏 6.分页栏)">
		<powersi:datagrid id="grid" url="${rootPath }/sample/Sample!queryCodetable.action" 
			exportFileName="getExportName" showExportExcel="true" showExportExcel2007="true" showExportPdf="true"
			title="标题栏(setTilte可动态修改)" delayLoad="true"
			checkbox="true" selectRowButtonOnly="true" isCheckable="isCheckable" isMultiSelect="false"
			totalRender="renderTotal"
			heightDiff="-50"
			summaryExp="sum(decode(code_sql, null, 0, 1)) as sum_sql,  sum(decode(code_sql, null, 1, 0)) as sum_detail">
			<powersi:toolbar>
				<powersi:toolbar-item id="title" icon="info" text="工具栏文本(setItemText可动态修改)" title="使用setItemText可动态修改"></powersi:toolbar-item>
				<powersi:toolbar-item id="click" icon="edit" text="工具栏按钮<span class='hotkey shift'>B</span>" title="使用setItemEnabled, setItemDisabled可禁用启用" click="itemClick" position="right"></powersi:toolbar-item>
				<powersi:toolbar-item id="hide" icon="delete" text="隐藏工具栏按钮<span class='hotkey alt'>H</span>" title="隐藏工具栏按钮setItemVisible" click="itemClick" position="right"></powersi:toolbar-item>
				<powersi:toolbar-item id="show" icon="add" text="显示工具栏按钮<span class='hotkey ctrl'>S</span>" title="显示工具栏按钮setItemVisible" click="itemClick" position="right"></powersi:toolbar-item>
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" frozen="true" />
			<powersi:datagrid-column display="${colName2 }" name="code_type" width="150" />
			<powersi:datagrid-column display="代码名称" name="code_name" width="40%" minWidth="100" />
			<powersi:datagrid-column display="代码SQL(点击显示详情)" name="code_sql" width="60%" minWidth="120" align="left" render="renderSql" totalSummary="{'render': renderTotalSql}" />
		</powersi:datagrid>
	</powersi:panelbox>
	<div class="divButton">
		<powersi:button id="btn" cssClass="btn btn-success" value="使用heightDiff实现高度自适应" buttonIcon="icon-text-height" data-hotkey="alt+1"></powersi:button>
		<powersi:button id="btnExport1" value="缺省导出" key="button_export" onclick="grid.exportData()" data-hotkey="alt+2"></powersi:button>
		<powersi:button id="btnExport2" value="Excel导出" buttonIcon="icon-fa fa-file-excel-o" onclick="grid.exportExcel()" data-hotkey="alt+3"></powersi:button>
		<powersi:button id="btnExport3" value="Excel2007导出" buttonIcon="icon-fa fa-file-excel-o" onclick="grid.exportExcel2007()" data-hotkey="alt+4"></powersi:button>
		<powersi:button id="btnExport4" value="pdf导出" buttonIcon="icon-fa fa-file-pdf-o" onclick="grid.exportPdf()" data-hotkey="alt+5"></powersi:button>
		<powersi:button id="btnReset" key="button_reset" onclick="grid.reset()" data-hotkey="alt+6"></powersi:button>
	</div>
	<powersi:errors />
	<div id="xjgrid"></div>
	<powersi:script>
		function getCondition(){
			//生成aaa027下拉框
			<powersi:combobox id="cbAaa027" valueFieldID="valAaa027" selectBoxHeight="200" codeType="aaa027_list">
			</powersi:combobox>
			
			//初始化aaa027选择
			var data = cbAaa027.data;
			if(data){
				cbAaa027['initValue'] = data[0]['id'];
				cbAaa027['initText'] = data[0]['text'];
			}
			
			//初始化查询日期
			var today = new Date();
			<powersi:dataform id="cond" prefixID="search_panel_" inputWidth="80" labelWidth="80" showClose="true" closeText="清除">
				<powersi:dataform-field name="center_id" display="选择中心" required="true" type="combobox" options="cbAaa027"></powersi:dataform-field>
				<powersi:dataform-field key="code_type"></powersi:dataform-field>
				<powersi:dataform-field key="code_name"></powersi:dataform-field>
				<powersi:dataform-field name="query_date" display="查询日期" width="100" required="true" type="date" options="{format:'yyyyMMdd', initValue: today}"></powersi:dataform-field>
			</powersi:dataform>
			
			return cond;
		}
	</powersi:script>
<script type="text/javascript">
$(function() {
	//绑定搜索条件到面板
	grid.bindCondition(getCondition());
	
	//绑定费用类型选择触发检索
	$.ligerui.get('search_panel_center_id').bind('selected', function(){
		$.ligerui.get('search_panel_search').click();
	});
	
	//需要绑定条件后再检索数据
	grid.reload(true);
	
	//绑定工具栏快捷方式
	addHotkey("shift+b", "工具栏按钮", function(event){
		popupMessage("shift+b");
	});
	addHotkey("ctrl+s", "显示工具栏按钮", function(event){
		popupMessage("ctrl+s");
		grid.setItemVisible('click', true);
	});
	addHotkey("alt+h", "隐藏工具栏按钮", function(event){
		popupMessage("alt+h");
		grid.setItemVisible('click', false);
	});
	//removeHotkey("alt+h");
});

function getExportName() {
	return '代码表';
}

function renderTotal(allData, currentData){
	if(!grid || !grid.getSummary){
		return "";
	}
	
	//计算本页结果
	var cnt = 0;
	$.each(allData['rows'], function(n, row){
		if(grid.getRowStatus(row) !== 'delete' && powersi.length(row['code_sql']) > 0){
			cnt ++;
		}
	});
	var a = [];
	a.push('<div class="textCenter textSuccess">');
	
	//获取汇总结果
	var summary = grid.getSummary();
	if(summary){
		a.push('<span class="textPrimary">');
		a.push('总 <b>');
		a.push(grid.getTotal());
		a.push('</b> 项');
		a.push('</span>');
		
		a.push('&nbsp;&nbsp;');
		
		a.push('<span class="textPrimary">');
		a.push('自定义 <b>');
		a.push(summary['sum_sql'] || 0);
		a.push('</b> 项');
		a.push('</span>');
		
		a.push('&nbsp;&nbsp;');
		
		a.push('<span class="textPrimary">');
		a.push('明细 <b>');
		a.push(summary['sum_detail'] || 0);
		a.push('</b> 项');
		a.push('</span>');
		
		a.push('&nbsp;&nbsp;');
	}
	
	a.push('<span>');
	a.push('本页 <b>');
	a.push(powersi.length(grid.getData()));
	a.push('</b> 项');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('自定义 <b>');
	a.push(cnt);
	a.push('</b> 项');
	a.push('</span>');
	//获取附加结果
	var result = grid.getResult();
	if(result){
		a.push('<div class="floatRight textError">');
		a.push('&nbsp;&nbsp;');
		
		a.push('<span>');
		a.push('运行耗时：');
		a.push(result['elapsed_time']);
		a.push('ms</span>');
		
		a.push('&nbsp;&nbsp;');
		
		a.push('<span>');
		a.push('服务器时间：');
		a.push(result['server_time'] || '');
		a.push('</span>');
		
		a.push('</div>');
	}
	
	a.push('</div>');
	
	return a.join('');
}

function renderTotalSql(totalObj){
	//计算本页结果
	var cnt = 0, total = 0;
	$.each(grid.getData(), function(n, row){
		if(!grid.isDeleted(row) && powersi.length(row['code_sql']) > 0){
			cnt ++;
		}
	});
	
	var summary = grid.getSummary();
	if(summary && summary['sum_sql']){
		total = summary['sum_sql'];
	}
	
	var a = [];
	a.push('<div class="textCenter">');
	a.push('<span>');
	a.push('小计：');
	a.push(cnt);
	a.push(' 项');
	a.push('</span>');
	a.push('&nbsp;&nbsp;&nbsp;&nbsp;');
	a.push('<span>');
	a.push('合计：');
	a.push(total);
	a.push(' 项');
	a.push('</span>');
	a.push("</div>");
	return a.join('');
}

function isCheckable(row) {
	return powersi.length(row['code_sql']) == 0;
}

function itemClick(item){
	var id = item.id;
	if(id == 'show'){
		grid.setItemVisible('click', true);
	} else if(id == 'hide'){
		grid.setItemVisible('click', false);
	} else {
		alert(powersi.tostring(item));	
	}
}

function renderOperate(row, index, value){
	var a = [];
	
	a.push('<input type="button" value="编辑" class="linkButton"');
	a.push(' onclick="doEdit(');
	a.push(index);
	a.push(')"');
	if(powersi.length(row['code_sql']) > 0){
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	
	a.push('<input type="button" value="删除" class="linkButton"');
	a.push(' onclick="doDel(');
	a.push(index);
	a.push(')"');
	a.push(" />");
	
	return a.join('');
}

function doEdit(index){
	popupInfo(powersi.tostring(grid.getRow(index)), '编辑');
}

function doDel(index){
	var row = grid.getRow(index);
	grid.deleteRow(row);
	popupSuccess(powersi.tostring(row), '删除');
}

function renderSql(row, index, value, column){
	var a = [];
	
	a.push('<div style="height:100%;width:100%;overflow:hidden;cursor:pointer;"');
	a.push(' onclick="popupInfo($(this).text(), \'' + row['code_name'] + '\')">');
	a.push(value);
	a.push("</div>");
	
	return a.join('');
}
</script>
</body>
</powersi:html>