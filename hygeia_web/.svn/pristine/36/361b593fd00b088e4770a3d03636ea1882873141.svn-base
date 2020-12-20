<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="datagrid选择" />
<script src="${rootPath }/pages/sample/webui/data-fee.js" charset="utf-8"></script>
<script type="text/javascript">
//复制费用数据，datagird的操作会修改原始数据，所以每个datagrid使用费用数据的副本，保证数据互不影响
var feedata1 = [], feedata2 = [], feedata3 = [], feedata4 = [], feedata5 = [], feedata6 = [];
$.each(feedata, function(n, row){
	feedata1.push($.extend({}, row));
	feedata2.push($.extend({}, row));
	feedata3.push($.extend({}, row));
	feedata4.push($.extend({}, row));
	feedata5.push($.extend({}, row));
	feedata6.push($.extend({}, row));
});
</script>
</head>
<body>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-th-list red"></i> 多选(无按钮)
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
					<i class="icon-th green"></i> 多选(checkbox)
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3"> 
					<i class="icon-th-large blue"></i> 单选(无按钮)
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane4" id="tab4"> 
					<i class="icon-reorder yellow"></i> 单选(radio)
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane5" id="tab5"> 
					<i class="icon-list-ul orange"></i> 分组多选
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane6" id="tab6"> 
					<i class="icon-list-ol pink"></i> 分页多选
				</a>
			</li>
		</ul>
		
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid1" data="feedata1"
					allowUnSelectRow="true">
					<powersi:toolbar>
						<powersi:toolbar-item text="按住ctrl键实现多选" icon="info3" />
						<powersi:toolbar-item text="允许取消选择(allowUnSelectRow=true)" icon="info4" />
						
						<powersi:toolbar-item id="rows1" text="选择行集合" icon="tick1" click="itemClick" position="right" />
						<powersi:toolbar-item id="row1" text="选择行" icon="tick2" click="itemClick" position="right" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane2" class="tab-pane">
				<powersi:datagrid id="grid2" data="feedata2"
					checkbox="true" selectRowButtonOnly="true" isChecked="checkFee">
					<powersi:toolbar>
						<powersi:toolbar-item text="只有点击checkbox才能选择(selectRowButtonOnly=true)" icon="info" />
						<powersi:toolbar-item text="支持选择按钮勾选(isChecked)" icon="info4" />
						
						<powersi:toolbar-item id="rows2" text="选择行集合" icon="tick1" click="itemClick" position="right" />
						<powersi:toolbar-item id="row2" text="选择行" icon="tick2" click="itemClick" position="right" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
				
			</div>
			<div id="tab-pane3" class="tab-pane">
				<powersi:datagrid id="grid3" data="feedata3"
					isMultiSelect="false" selectRowButtonOnly="false">
					<powersi:toolbar>
						<powersi:toolbar-item text="支持点击行选择(selectRowButtonOnly=false)" icon="info" />
						
						<powersi:toolbar-item id="rows3" text="选择行集合" icon="tick1" click="itemClick" position="right" />
						<powersi:toolbar-item id="row3" text="选择行" icon="tick2" click="itemClick" position="right" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane4" class="tab-pane">
				<powersi:datagrid id="grid4" data="feedata4"
					checkbox="true" isMultiSelect="false" isCheckable="checkFee">
					<powersi:toolbar>
						<powersi:toolbar-item text="支持点击行选择(selectRowButtonOnly=false)" icon="info3" />
						<powersi:toolbar-item text="支持选择按钮禁用(isCheckable)" icon="info4" />
						
						<powersi:toolbar-item id="rows4" text="选择行集合" icon="tick1" click="itemClick" position="right" />
						<powersi:toolbar-item id="row4" text="选择行" icon="tick2" click="itemClick" position="right" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane5" class="tab-pane">
				<powersi:datagrid id="grid5" data="feedata5" pageSize="100"
					checkbox="true" groupColumnName="ake006" groupCheckbox="true"
					mergeColumnNames="['checkbox', 'ake001', 'ake006']">
					<powersi:toolbar>
						<powersi:toolbar-item text="支持分组多选(groupCheckbox=true)" icon="info3" />
						<powersi:toolbar-item text="支持单元格合并(mergeColumnNames)" icon="info4" />
						<powersi:toolbar-item text="支持checkbox合并" icon="info5" />
						
						<powersi:toolbar-item id="rows5" text="选择行集合" icon="tick1" click="itemClick" position="right" />
						<powersi:toolbar-item id="row5" text="选择行" icon="tick2" click="itemClick" position="right" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane6" class="tab-pane">
				<powersi:datagrid id="grid6" data="feedata6"
					checkbox="true" isChecked="isSelected" onCheckRow="selRow" onCheckAllRow="selAllRow">
					<powersi:toolbar>
						<powersi:toolbar-item text="支持分页多选(isChecked,onCheckRow,onCheckAllRow)" icon="info" />
						
						<powersi:toolbar-item id="rows6" text="选择行集合" icon="tick1" click="itemClick" position="right" />
						<powersi:toolbar-item id="row6" text="选择行" icon="tick2" click="itemClick" position="right" />
						<powersi:toolbar-item id="allrows6" text="选择所有行集合" icon="tick3" click="itemClick" position="right" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="ake001" display="项目药品编码" width="120" frozen="true" />
					<powersi:datagrid-column name="ake006" display="项目药品名称" width="100%" minWidth="150" />
					<powersi:datagrid-column name="pka051" display="费用发生时间" width="150" />
					<powersi:datagrid-column name="pka054" display="规格" width="100" />
					<powersi:datagrid-column name="pka056" display="单价" width="100" />
					<powersi:datagrid-column name="pka057" display="用量" width="100" />
					<powersi:datagrid-column name="pka058" display="金额" width="100" />
				</powersi:datagrid>
			</div>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
$(function() {
	/*分组需要手工排序*/
	grid5.changeSort('ake006', 'asc');
});

function itemClick(item) {
	var id = item['id'];
	var data;
	if(id == 'row1'){
		data = grid1.getSelectedRow();
	} else if(id == 'rows1'){
		data = grid1.getSelectedRows();
	} else if(id == 'row2'){
		data = grid2.getSelectedRow();
	} else if(id == 'rows2'){
		data = grid2.getSelectedRows();
	} else if(id == 'row3'){
		data = grid3.getSelectedRow();
	} else if(id == 'rows3'){
		data = grid3.getSelectedRows();
	} else if(id == 'row4'){
		data = grid4.getSelectedRow();
	} else if(id == 'rows4'){
		data = grid4.getSelectedRows();
	} else if(id == 'row5'){
		data = grid5.getSelectedRow();
	} else if(id == 'rows5'){
		data = grid5.getSelectedRows();
	} else if(id == 'row6'){
		data = grid6.getSelectedRow();
	} else if(id == 'rows6'){
		data = grid6.getSelectedRows();
	} else if(id == 'allrows6'){
		data = getSelectedRows();
	} else {
		return;
	}
	
	if(!data || powersi.length(data) == 0) {
		popupAlert('没有选择');
		return;
	};
	
	//getSelectedRows返回array，getSelectedRow返回map
	var title = powersi.isarray(data) ? '记录数' : '字段数';
	popupInfo(powersi.tostring(powersi.extract(data, ["ake001", "ake006"])), title + '(' + powersi.length(data) + ')');
}
//grid2 金额小于10的自动勾选
//grid4 金额小于10的可以勾选
function checkFee(rowdata){
	return parseFloat(rowdata['pka058']) < 10;
}

//begin分页多选支持
/*存放以费用序列号aaz213为key，行数据为value的已选择行集合*/
var selectedRows = {};
/*行的主键*/
var selectedKey = "aaz213";

function isSelected(row) {
	return selectedRows[row[selectedKey]] != null ? true : false;
}

function addSelected(row) {
    if(!isSelected(row)){
    	/*获取的数据必须格式化，否则会影响datagrid*/
    	selectedRows[row[selectedKey]] = $.ligerMethos.Grid.formatRow(row);    	
    }
}

function removeSelected(row) {
    if(isSelected(row)){
    	delete selectedRows[row[selectedKey]];
    }
}

function selRow(checked, row) {
    if (checked) 
    	addSelected(row);
    else 
    	removeSelected(row);
}

function selAllRow(checked) {
    for (var rowid in this.records) {
        if(checked)
            addSelected(this.records[rowid]);
        else
            removeSelected(this.records[rowid]);
    }
}

function getSelectedRows(){
	var rows = [];
	$.each(selectedRows, function(key,  row){
		rows.push(row);
	});
	return rows;
}
//end分页多选支持
</script>
</body>
</powersi:html>