<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="datagrid汇总" />
<script src="${rootPath }/pages/sample/webui/data-fee.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript">
//复制费用数据，datagird的操作会修改原始数据，所以每个datagrid使用费用数据的副本
var feedata1 = [], feedata2 = [], feedata3 = [];
$.each(feedata, function(n, row){
	feedata1.push($.extend({}, row));
	feedata2.push($.extend({}, row));
	feedata3.push($.extend({}, row));
});
</script>
</head>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-desktop red"></i> Footer 汇总
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
					<i class="icon-laptop green"></i> Summary 汇总
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3"> 
					<i class="icon-tablet blue"></i> Group 汇总
				</a>
			</li>
		</ul>
		
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid1" showExportExcel="true" exportFileName="'表格汇总'" data="feedata1" pageSize="10">
					<powersi:toolbar>
						<powersi:toolbar-item id="add1" text="新增行" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del1" text="删除行" icon="delete" click="itemClick" />
						<powersi:toolbar-item id="update1" text="更新行" icon="edit" click="itemClick" />
						
						<powersi:toolbar-item id="allchg1" text="全部修改" icon="info4" click="itemClick" position="right"/>
						<powersi:toolbar-item id="pagechg1" text="本页修改" icon="info3" click="itemClick" position="right"/>
						
						<powersi:toolbar-item id="alldata1" text="全部数据" icon="info2" click="itemClick" position="right"/>
						<powersi:toolbar-item id="pagedata1" text="本页数据" icon="info1" click="itemClick" position="right"/>
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" totalSummary="{'render': renderCount}"/>
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" totalSummary="{'type': 'sum,count,max,min,avg', align: 'left'}"/>
				</powersi:datagrid>
			</div>
			<div id="tab-pane2" class="tab-pane">
				<powersi:datagrid id="grid2" showExportExcel="true" exportFileName="'表格汇总'" data="feedata2" 
					totalRender="renderTotal"
					mergeColumnNames="['ake001', 'ake006']">
					<powersi:toolbar>
						<powersi:toolbar-item id="add2" text="新增行" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del2" text="删除行" icon="delete" click="itemClick" />
						<powersi:toolbar-item id="update2" text="更新行" icon="edit" click="itemClick" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane3" class="tab-pane">
				<powersi:datagrid id="grid3" showExportExcel="true" exportFileName="'表格汇总'" data="feedata3" pageSize="50"
					groupColumnName="ake006" groupRender="renderGroup" rownumbersRender="renderRownumbers"
					mergeColumnNames="['ake001', 'ake006']">
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" forzen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" totalSummary="{'render': renderAvg}"/>
					<powersi:datagrid-column name="pka057" display="用量" width="100" totalSummary="{'render': renderSum}"/>
					<powersi:datagrid-column name="pka058" display="金额" width="100" totalSummary="{'render': renderSum}"/>
				</powersi:datagrid>
			</div>
		</div>
	</div>
	<div class="hidden">
		<div id="changeDlg">
			<powersi:form id="changeFrom" disabled="true">
				<powersi:editorlayout cols="25%,75%">
					<powersi:editorlayout-row>
						<powersi:textarea id="update_data" name="update_data" label="修改数据" rows="10" readonly="true"></powersi:textarea>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea id="insert_data" name="insert_data" label="新增数据" rows="10" readonly="true"></powersi:textarea>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea id="delete_data" name="delete_data" label="删除数据" rows="10" readonly="true"></powersi:textarea>
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:form>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
$(function() {
	/*分组手工排序*/
	rownumbers = {};
	grid3.changeSort('ake006', 'asc');
	
	/*单元格合并*/
	grid2.changeSort('ake001', 'asc');
	
	groupCollapse(grid3);
});

function groupCollapse(grid){
	grid.gridbody.find('.l-grid-group-togglebtn').click().parent().css({'width': '100%'}).closest('table').css({'width': '100%'});
}

var rownumbers = {};
function renderRownumbers(rowdata){
	var k = rowdata['ake006'];
	var n = rownumbers[k];
	if(!n){
		n = powersi.length(rownumbers) + 1;
		rownumbers[k] = n;
	}
	return n;
}

var dlg;
//ligerGrid会修改原始数据
function itemClick(item) {
	var id = item['id'];
	if(id == 'add1'){
		grid1.addRow($.extend({}, feedata[0]));
	} else if(id == 'del1'){
		if(grid1.getRowsCount() > 0){
			grid1.deleteRow(0);
		}
	} else if(id == 'update1'){
		if(grid1.getRowsCount() > 0){
			grid1.updateRow(0, {
				pka056: 100,
				pka057: 5,
				pka058: 500
			});
			//更新行不会刷新显示，需要显式更新
			grid1.reRender();
		}
	}else if(id == 'pagedata1'){
		alert(grid1.getData().length);
	}else if(id == 'alldata1'){
		alert(grid1.getAllData().length);
	}else if(id == 'pagechg1'){
		clearForm('#changeFrom');
		var json = grid1.getChangesJSON();
		$.each(json, function(key, value){
			$('#' +key + '_data').val(value);
		});
		if(dlg){
			dlg.show();
		} else {
			dlg = popupDiv('#changeDlg', '查看数据修改', 600);
		}
	}else if(id == 'allchg1'){
		clearForm('#changeFrom');
		var json = grid1.getChangesJSON(true);
		$.each(json, function(key, value){
			$('#' +key + '_data').val(value);
		});
		if(dlg){
			dlg.show();
		} else {
			dlg = popupDiv('#changeDlg', '查看数据修改', 600);
		}
	}else if(id == 'add2'){
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
	}
}

//当前列合计对象suminf{sum,count,avg,min,max}
//data表示当前页数据
function renderCount(suminf, column, data) {
	var total = 0, page = 0;
	$.each(feedata, function(n, row){
		if(row['__status'] !== "delete")
			total += parseFloat(row['pka058'] || '0');
	});
	$.each(data, function(n, row) {
		if(row['__status'] !== "delete")
			page += parseFloat(row['pka058'] || '0');
	});
  return '<div class="textLeft">本页记录数:' + suminf.count + '</div><div class="textLeft">小计:' + page.toFixed(2) + '</div><div class="textLeft">合计:' + total.toFixed(2) + '</div></div>';
}

function renderAvg(suminf, column, cell) {
    return '<b>' + suminf.avg.toFixed(4) + '</b>';
}

function renderSum(suminf, column, cell) {
    return '<b>' + suminf.sum.toFixed(2) + '</b>';
}

function renderGroup(value, groupData){
	var money = 0;
	var price = 0;
	var usage = 0;
	
	//计算分组数据
	if(powersi.rows_length(groupData)){
		$.each(groupData, function(n, row){
			if(row['__status'] !== "delete") {
				price += parseFloat(row['pka056'] || '0');
				usage += parseFloat(row['pka057'] || '0');
				money += parseFloat(row['pka058'] || '0');
			}
		});	
	}
	
	var a = [];
	a.push('<div class="floatRight textInfo">');
	
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
	a.push(powersi.length(groupData));
	a.push('</b> 项');
	a.push('</span>');
	
	a.push('</div>');
	
	return '项目药品： ' + value + '' + a.join('');
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
	
	//alert(powersi.tostring(allData));
	//alert(powersi.tostring(currentData));
		
	//计算本地全部数据
	if(powersi.rows_length(allData['rows'])){
		$.each(allData['rows'], function(n, row){
			if(row['__status'] !== "delete") {
				price += parseFloat(row['pka056']);
				usage += parseFloat(row['pka057']);
				money += parseFloat(row['pka058']);
				
				kinds[row['ake001']] = '';
			}
		});	
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
</script>
</body>
</powersi:html>