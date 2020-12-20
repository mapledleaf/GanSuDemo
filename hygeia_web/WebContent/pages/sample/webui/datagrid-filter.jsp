<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid过滤"/>
<script src="${rootPath }/pages/sample/webui/data-fee.js" charset="utf-8" type="text/javascript"></script>
</head>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-desktop red"></i> 服务端过滤排序
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
					<i class="icon-laptop green"></i> 客户端过滤排序
				</a>
			</li>
		</ul>
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid1" url="${rootPath }/sample/Sample!queryCodetable.action"
					showExportExcel="true" exportFileName="'业务代码'"
					showSort="true" showFilter="true">
					<powersi:toolbar>
						<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
					</powersi:toolbar>
					<powersi:datagrid-column display="代码类型" name="code_type" width="30%" minWidth="100" />
					<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="100" />
					<powersi:datagrid-column display="代码SQL" name="code_sql" width="40%" minWidth="120" align="left"/>
				</powersi:datagrid>
			</div>
			<div id="tab-pane2" class="tab-pane">
				<powersi:datagrid id="grid2" showExportExcel="true" exportFileName="'费用汇总'" 
					data="feedata" 
					totalRender="renderTotal" pageSize="10"
					showSort="true" showFilter="true"
					mergeColumnNames="['ake003', 'ake001', 'ake006']">
					<powersi:toolbar>
						<powersi:toolbar-item id="add2" text="新增行" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del2" text="删除行" icon="delete" click="itemClick" />
						<powersi:toolbar-item id="update2" text="更新行" icon="edit" click="itemClick" />
						<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake003" display="项目药品类型" width="100" frozen="true" render="renderFeeType"/>
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" totalSummary="{'render': renderCount}" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" totalSummary="{'type': 'sum,count,max,min,avg', align: 'left'}" />
				</powersi:datagrid>
			</div>
		</div>
	</div>
	<powersi:script>
		<powersi:combobox id="cbFeeType" valueFieldID="valFeeType" data="feetype" initValue="" initText=""></powersi:combobox>
			
		var feeTypeMap = {};
		$.each(feetype, function(i, fee){
			feeTypeMap[powersi.tostring(fee['id'])] = fee['text'];
		});
		
		<powersi:dataform id="cond1" prefixID="search1_toolbar_" inputWidth="100" 
			showClose="true" closeText="重置"
			sort="code_type,code_name">
			<powersi:dataform-field key="code_type" />
			<powersi:dataform-field key="code_name" />
			<powersi:dataform-field key="code_sql" display="代码SQL" />
		</powersi:dataform>
		
		<powersi:dataform id="cond2" prefixID="search2_toolbar_" inputWidth="80" labelWidth="50"
			showClose="true" closeText="重置"
			sort="ake003 asc,ake006 asc,pka051 asc,pka058 desc">
			<powersi:dataform-field name="ake003" display="类型" width="90" type="combobox" options="cbFeeType" operator="==" />
			<powersi:dataform-field name="pka058" display="金额" width="50" type="number" operator=">=" />
			<powersi:dataform-field name="pka058" display="-" rightToken="" labelAlign="left" labelWidth="10" width="50" type="number" operator="<=" />
			<powersi:dataform-field name="pka051" display="日期" width="100" type="date" options="{format:'yyyy-MM-dd'}" operator=">=" />
			<powersi:dataform-field name="pka051" display="-" rightToken="" labelAlign="left" labelWidth="10" width="100" type="date" options="{format:'yyyy-MM-dd'}" operator="<=" />
		</powersi:dataform>
	</powersi:script>
	<powersi:errors />
<script type="text/javascript">
function renderFeeType(row, index, value){
	return feeTypeMap[value] || value;
}

$(function(){
	//绑定工具栏
	grid1.bindCondition(cond1, 'search');
	
	//页面宽度大于1024px绑定到工具栏否则绑定到条件栏
	var panel = null;
	if(getPageWidth() > 1024){
		panel = 'search';
	}
	grid2.bindCondition(cond2, panel);
	
	//绑定费用类型选择触发检索
	$.ligerui.get('search2_toolbar_ake003').bind('selected', function(newvalue, newtext){
		var searchBtn = $.ligerui.get('search2_toolbar_search');
		if(searchBtn){
			searchBtn.click();
		}
	});
});

function sortGrid1() {
	grid1.sort('code_type,code_name');
}

function sortGrid2() {
	grid2.sort('ake003 asc,ake006 asc,pka051 asc,pka058 desc');
}

//当前列合计对象suminf{sum,count,avg,min,max}
//data表示当前页数据
function renderCount(suminf, column, data) {
	var total = 0, page = 0;
	if(grid2 && $.isFunction(grid2.isDeleted)) {
		$.each(feedata, function(n, row){
			if(!grid2.isDeleted(row))
				total += parseFloat(row['pka058'] || '0');
		});
		$.each(data, function(n, row) {
			if(!grid2.isDeleted(row))
				page += parseFloat(row['pka058'] || '0');
		});
	}
	
    return '<div class="textLeft">本页记录数:' + suminf.count + '</div><div class="textLeft">小计:' + page.toFixed(2) + '</div><div class="textLeft">合计:' + total.toFixed(2) + '</div></div>';
}
/*
 *allData没有包含数据变化，需要自行处理
 *currentData包含了已经删除的数据，需要自行判断
 */
function renderTotal(allData, currentData){
	var money = 0;
	var price = 0;
	var usage = 0;
	var kinds = {};
	
	//计算本地全部数据
	if(powersi.rows_length(allData['rows'])){
		if(grid2 && $.isFunction(grid2.isDeleted)){
			$.each(allData['rows'], function(n, row){
				if(!grid2.isDeleted(row)) {
					price += parseFloat(row['pka056'] || '0');
					usage += parseFloat(row['pka057'] || '0');
					money += parseFloat(row['pka058'] || '0');
					
					kinds[row['ake001']] = '';
				}
			});	
		}
	}
	
	var a = [];
	a.push('<div class="divCenter textSuccess">');
	
	a.push('<span>');
	a.push('总金额：<b>');
	a.push(money.toFixed(2));
	a.push('</b>');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('总用量：<b>');
	a.push(usage.toFixed(2));
	a.push('</b>');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('平均单价：<b>');
	a.push((feedata.length == 0) ? 0 : (price / feedata.length).toFixed(4));
	a.push('</b>');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('平均费用：<b>');
	a.push((usage == 0) ? 0 : (money / usage).toFixed(4));
	a.push('</b>');
	a.push('</span>');
	
	a.push('&nbsp;&nbsp;');
	
	a.push('<span>');
	a.push('共 <b>');
	a.push(powersi.length(kinds));
	a.push('</b> 项');
	a.push('</span>');
	
	a.push('</div>');
	
	return a.join('');
}

//ligerGrid会修改原始数据
function itemClick(item) {
	var id = item['id'];
	if(id == 'add2'){
		grid2.addRow($.extend({}, feedata[0]));
	} else if(id == 'del2'){
		if(grid2.getRowsCount() > 0){
			grid2.deleteRow(0);
		}
	} else if(id == 'update2'){
		if(grid2.getRowsCount() > 0){
			grid2.updateRow(0, {
				pka056: 100,
				pka057: 5,
				pka058: 500
			});
			//更新行不会刷新显示，需要显式更新
			grid2.reRender();
		}
	} else if(id == 'sort1'){
		//grid1.showSort();
	} else if(id == 'sort2'){
		//grid2.showSort();
	}
}
</script>
</body>
</powersi:html>