<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="datagrid编辑" />
</head>
<body>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-desktop red"></i> 单元格编辑
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
					<i class="icon-laptop green"></i> 行编辑
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane3" id="tab3"> 
					<i class="icon-tablet blue"></i> 弹窗编辑 
				</a>
			</li>
			
			<li>
				<a data-toggle="tab" href="#tab-pane4" id="tab4"> 
					<i class="icon-mobile-phone yellow"></i> 明细编辑
				</a>
			</li>
		</ul>
		<div class="tab-content content">
			<!-- 获取有效标志的码表 -->
			<powersi:codetable id="validList" codeType="valid_flag"></powersi:codetable>
				
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid1" url="${rootPath }/sample/Sample!queryCodetable.action"
					showExportExcel="true" exportFileName="'业务代码1'"
					enabledEdit="true" clickToEdit="true">
					<powersi:toolbar>
						<powersi:toolbar-item id="add1" text="新增" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del1" text="删除" icon="delete" click="itemClick" />
						<powersi:toolbar-item id="chg1" text="查看修改" icon="info" click="itemClick" />
						<powersi:toolbar-item id="save1" text="保存修改" icon="save" click="itemClick" />
						<powersi:toolbar-item id="cancel1" text="取消修改" icon="cancel" click="itemClick" />
						
						<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
					</powersi:toolbar>
					<powersi:datagrid-column display="代码类型" name="code_type" width="120" editor="{type: 'string'}"/>
					<powersi:datagrid-column display="代码名称" name="code_name" width="40%" minWidth="120" editor="{type: 'string'}"/>
					<powersi:datagrid-column display="代码SQL1" name="code_sql" width="30%" minWidth="120" align="left" editor="{type:'popup', onButtonClick: popupSql, autoOpen: false, editable: true}" />
					<powersi:datagrid-column display="代码SQL2" name="code_sql" width="30%" minWidth="120" align="left" editor="{type:'popup', onButtonClick: popupSql, autoOpen: true}" />
					<powersi:datagrid-column display="有效标志1" name="valid_flag" width="80" render="renderValidFlag" type="int" editor="{type: 'select', data:validList}" isSort="false" isFilter="false" />
					<powersi:datagrid-column display="有效标志2" name="valid_flag" width="150" render="renderValidRadio" type="int" />
					<powersi:datagrid-column display="生效日期" name="code_date" width="100" editor="{type: 'date'}" isSort="false" isFilter="false" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane2" class="tab-pane">
				<powersi:datagrid id="grid2" url="${rootPath }/sample/Sample!queryCodetable.action"
					showExportExcel="true" exportFileName="'业务代码2'" 
					enabledEdit="true" clickToEdit="false" onBeforeSubmitEdit="beforeSubmitEdit" rowAttrRender="gridRowRender">
					<powersi:toolbar>
						<powersi:toolbar-item id="add2" text="新增" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del2" text="删除" icon="delete" click="itemClick" />
						<powersi:toolbar-item id="chg2" text="查看修改" icon="info" click="itemClick" />
						<powersi:toolbar-item id="save2" text="保存修改" icon="save" click="itemClick" />
						<powersi:toolbar-item id="cancel2" text="取消修改" icon="cancel" click="itemClick" />
						
						<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
					</powersi:toolbar>
					<powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate1" />
					<powersi:datagrid-column display="代码类型" name="code_type" width="120" editor="{type: 'string'}"/>
					<powersi:datagrid-column display="代码名称" name="code_name" width="30%" minWidth="120" editor="{type: 'string'}"/>
					<powersi:datagrid-column display="代码SQL" name="code_sql" width="60%" minWidth="120" align="left" editor="{type:'popup', onButtonClick: popupSql}" />
					<powersi:datagrid-column display="有效标志" name="valid_flag" width="80" render="renderValidFlag" editor="{type: 'select', data:validList}"/>
					<powersi:datagrid-column display="生效日期" name="code_date" width="100" editor="{type: 'date'}"/>
				</powersi:datagrid>
			</div>
			<div id="tab-pane3" class="tab-pane">
				<powersi:datagrid id="grid3" url="${rootPath }/sample/Sample!queryCodetable.action"
					showExportExcel="true" exportFileName="'业务代码3'" 
					enabledEdit="true" clickToEdit="false" rowAttrRender="gridRowRender">
					<powersi:toolbar>
						<powersi:toolbar-item id="add3" text="新增" icon="add" click="itemClick" />
						<powersi:toolbar-item id="del3" text="删除" icon="delete" click="itemClick" />
						<powersi:toolbar-item id="chg3" text="查看修改" icon="info" click="itemClick" />
						<powersi:toolbar-item id="save3" text="保存修改" icon="save" click="itemClick" />
						<powersi:toolbar-item id="cancel3" text="取消修改" icon="cancel" click="itemClick" />
						
						<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
					</powersi:toolbar>
					<powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate2" />
					<powersi:datagrid-column display="代码类型" name="code_type" width="120" />
					<powersi:datagrid-column display="代码名称" name="code_name" width="50%" minWidth="120" />
					<powersi:datagrid-column display="代码SQL" name="code_sql" width="50%" minWidth="120" align="left" />
					<powersi:datagrid-column display="有效标志" name="valid_flag" width="80" render="renderValidFlag" />
					<powersi:datagrid-column display="生效日期" name="code_date" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane4" class="tab-pane">
				<div style="text-align:center;margin:0 auto;padding-top:100px;font-size:24px;font-weight:bold;" class="textWarning">
					操作复杂，使用麻烦，不推荐使用。
					<br>
					如果需要可以参考明细表单示例，结合弹窗编辑操作实现。
				</div>
			</div>
		</div>
	</div>
	<div class="hidden">
		<div id="sqlDlg">
			<div class="divCentet">
				<powersi:textarea id="sqlArea" name="sqlArea" rows="10" cols="10" maxlength="1000"></powersi:textarea>
			</div>
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btnSqlOk" key="button_ok" onclick="saveSql()" />
				<powersi:button id="btnSqlClose" key="button_cancel" onclick="closeDlg()" />
			</div>
		</div>
		
		<div id="editDlg">
			<powersi:form id="editForm">
				<powersi:editorlayout cols="4">
					<powersi:editorlayout-row>
						<powersi:textfield id="edit_code_type" name="code_type" label="码表类型" required="true" maxlength="50" colspan="3"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="edit_code_name" name="code_name" label="码表名称" required="true" colspan="3"></powersi:textfield>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textarea id="edit_code_sql" name="code_sql" label="码表SQL" rows="10" maxlength="1000"  colspan="3" placeholder="select data_value, display_value from sys_code_table_detail where code_type = ? order by dis_order"></powersi:textarea>
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield id="edit_code_date" name="code_date" label="生效日期" mask="date"></powersi:textfield>
						<powersi:radio codeType="valid_flag" key="valid_flag" name="valid_flag" id="edit_valid_flag" required="false" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
				<div class="space-y"></div>
				<div class="divButton">
					<powersi:button id="btnSave" key="button_ok" onclick="saveEdit()"></powersi:button>
					<powersi:button id="btnCancel" key="button_cancel" onclick="closeDlg()"></powersi:button>
				</div>
			</powersi:form>
		</div>
		
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
				<div class="space-y"></div>
				<div class="divButton">
					<powersi:button id="btnChangeClose" onclick="closeDlg()" key="button_close" />
				</div>
			</powersi:form>
		</div>
	</div>
	<powersi:script>
		<powersi:dataform id="cond" prefixID="" inputWidth="80" labelWidth="80">
			<powersi:dataform-field key="code_type" />
			<powersi:dataform-field key="code_name" />
		</powersi:dataform>
	</powersi:script>
<powersi:errors />
<script type="text/javascript">
$(function(){
	//绑定搜索条件到工具栏
	cond['prefixID'] = 'search1_';
	grid1.bindCondition(cond, 
		'search');
	
	cond['prefixID'] = 'search2_';
	grid2.bindCondition(cond, 
		'search');
	
	cond['prefixID'] = 'search3_';
	grid3.bindCondition(cond, 
		'search');
});

/*使用图标显示有效或者无效*/
function renderValidFlag(rowdata, index, value) {
	if (value === '0') {
		return '<span title="无效"><i class="icon-remove textError"></i></span>';
	} else if(value === '1'){
		return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
	} else {
		return '<span title="未配置"><i class="icon-check-empty textInfo"></i></span>';
	}
}

function renderValidRadio(rowdata, index, value) {
	var a = [];
	var checked = '';
	var clicked = '';
	$.each(validList, function(i, row){
		if(row["id"] == value){
			checked = ' checked="checked"';
		} else {
			checked = "";
		}
		clicked = ' onclick="clickValidRadio(\'' + rowdata["__id"] + '\', \'valid_flag\', \'' + row["id"] + '\')"';
		a.push('<label><input name="' + rowdata["__id"] + '_validid" type="radio" value="' + row["id"] + '"' + clicked + checked + '/>&nbsp;' + row["text"] + '&nbsp;&nbsp;</label>');
	});
	
	return a.join('');
}

function clickValidRadio(rowid, name, value){
	var grid = grid1;
	//直接修改
	//grid.updateCell(name, value, rowid);
	
	//判断值修改，并增加红色图标显示修改状态
	var row = grid.getRow(rowid);
	if(row){
		if(row[name] != value){
			grid.updateCell(name, value, rowid);
			
			for (var i = 0; i < grid.columns.length; i++) {
				var column = grid.columns[i]; 
			    if (column.name == name) {
			    		grid.setCellEdited(rowid, column, true);
			    }
			}
		}
	}
}

var dlg;
//ligerGrid会修改原始数据
function itemClick(item) {
	var id = item['id'];
	if(id == 'add1'){
		grid1.addRow({}, grid1.getRow(0), true);
	} else if(id == 'del1'){
		var row = grid1.getSelectedRow();
		if(!row){
			alert('请选择要删除的行');
		} else{
			if(confirm('您确认删除[' + (row['code_name'] || '') + ']吗？')) {
				grid1.deleteRow(row);
			}
		}
	} else if(id == 'save1'){
		save(grid1);
	} else if(id == 'cancel1'){
		cancel(grid1);
	} else if(id == 'chg1'){
		doViewChange(grid1);
	} else if(id == 'add2'){
		//标识是新增状态（方便确定和取消时判断）
		grid2.addRow({'__new': true}, grid2.getRow(0), true);
		
		/*为了控制不会新增空行，限制一次只能新增一条，新增确定或者取消后方能再新增*/
		grid2.setItemDisabled(id);
		beginEdit(0);
	} else if(id == 'del2'){
		var row = grid2.getSelectedRow();
		if(!row){
			alert('请选择要删除的行');
		} else{
			if(confirm('您确认删除[' + (row['code_name'] || '') + ']吗？')) {
				grid2.deleteRow(row);
			}
		}
	} else if(id == 'save2'){
		save(grid2);
	} else if(id == 'cancel2'){
		cancel(grid2);
	} else if(id == 'chg2'){
		doViewChange(grid2);
	} else if(id == 'add3'){
		doAdd();
	} else if(id == 'del3'){
		var row = grid3.getSelectedRow();
		if(!row){
			alert('请选择要删除的行');
		} else{
			doDel(grid3.getRowIndex(row));
		}
	} else if(id == 'save3'){
		save(grid3);
	} else if(id == 'cancel3'){
		cancel(grid3);
	} else if(id == 'chg3'){
		doViewChange(grid3);
	} else {
		return;
	}
}

function doViewChange(grid){
	clearForm('#changeFrom');
	
	var json = grid.getChangesJSON();
	$.each(json, function(key, value){
		$('#' +key + '_data').val(value);
	});
	
	dlg = popupDiv('#changeDlg', '查看数据修改', 600, {
		showMax: true,
		isHidden: false
	});
}

//所有popupDiv使用close模式，显式设置isHidden为false
function closeDlg() {
	if(dlg){
		dlg.close();
		dlg = null;
	}
}

//弹出sql编辑
var sqleditor = null;
function popupSql(editor) {
	if(!editor){
		return;
	}
	//编辑器参数
	//editor.editParm
	//alert(powersi.tostring(editor.editParm.record));
	
	//保存编辑器
	sqleditor = editor;
	
	$('#sqlArea').val(sqleditor.getValue());	
	
	dlg = popupDiv('#sqlDlg', '代码SQL', 400, {
		showMax: true, 
		isHidden: false
	});
}

//保存sql编辑
function saveSql() {
	var data = $('#sqlArea').val();
	if(sqleditor){
		sqleditor.setValue(data);
		sqleditor.setText(data);
		
		//alert(powersi.tostring(sqleditor.editParm.record));
	}
	
	closeDlg();
}

//保存
function save(grid){
	//检查修改状态(只有行编辑才有作用)
	if(!grid.checkEditing()){
		return false;
	}
	
	//结束修改
	grid.endEdit();
	
	//获取修改
	var data = grid.getChangesJSON();
	
	//检查修改
	if(powersi.isempty(data)){
		alert('没有修改，无需保存');
		return;
	}
	
	//检查数据有效性
	var isvalid = true;
	var status = null;
	var hasnew = false;
	var msgs = [];
	$.each(grid.getChangeRows(), function(n, row){
		//修改状态 delete删除 add新增 update修改
		status = grid.getRowStatus(row);
		if(status != 'delete'){
			if(powersi.isempty(row['code_type'])){
				isvalid = false;
				msgs.push('代码类型不能为空');
			}
			if(powersi.isempty(row['code_name'])){
				isvalid = false;
				msgs.push('代码名称不能为空');
			}
			
			if(!isvalid){
				//显示错误信息
				alert(msgs.join('\n'));
				
				//跳转到错误行
				grid.select(row);
				grid.scrollToRow(row);
				return false;
			}
		}
		
		if(status == 'add'){
			hasnew = true;
		}
	});
	
	if(!isvalid) return;
	
	if(confirm("您确认保存修改吗?")) {
		postAjax(rootPath + "/sample/Sample!save.action", data, function(json){
			if(!checkResult(json)){
				return;
			}
			
			alert(json.message);
			
			//如果存在新增数据且需要后台生成主键的情况下，需要自行处理新增主键更新
			if(hasnew){
				//第一种方式 后台返回id(map: key使用code_type value使用id)
				//假设主键字段名是id
				/*
				var keys = json.data;
				var id = null;
				$.each(grid1.records, function(rowid, row){
					id = keys[row["code_type"]];
					if(id)
						row["id"] = id;
				});
				*/
				
				//第二种方式直接刷新
				//刷新可以不需要调用接受数据修改，所以直接返回
				/*
				grid1.reload(true);
				return;
				*/
			}
			
			//接受改变，刷新显示
			grid.acceptChanges();
			grid.reRender();
		});
	}
}

//取消
function cancel(grid) {
	//结束修改
	grid.endEdit();
	
	//获取修改
	var data = grid.getChangesJSON();
	
	//检查修改
	if(powersi.isempty(data)){
		alert('没有修改，无需取消');
		return;
	}
	
	if(confirm("您确认取消修改吗?")) {
		grid.reload(true);
	}
}


/*红色标识修改的数据*/
function gridRowRender(rowdata, rowid){
	if ($.ligerMethos.Grid.hasStatusChanged(rowdata)) {
		return getColorStyle('error');
	}
}

/////////////////////////////////////////////////////////////////////////////////////////
//grid2
/////////////////////////////////////////////////////////////////////////////////////////
/*输出操作*/
function renderOperate1(row, index, value) {
	var a = [];
	if (!row._editing){
		a.push('<input type="button" value="编辑" class="linkButton"');
		a.push(' onclick="beginEdit(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="删除" class="linkButton"');
		a.push(' onclick="deleteRow(');
		a.push(index);
		a.push(')"');
		a.push(" />");
	} else {
		a.push('<input type="button" value="确定" class="linkButton textError"');
		a.push(' onclick="endEdit(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="取消" class="linkButton textError"');
		a.push(' onclick="cancelEdit(');
		a.push(index);
		a.push(')"');
		a.push(" />");
	}
	
	return a.join('');
}

function addRow(){
	grid2.addRow({}, grid2.getRow(0), true);
	/*为了控制不会新增空行，限制一次只能新增一条，新增确定或者取消后方能再新增*/
	grid2.setItemDisabled('add2');
	beginEdit(0);
}

function deleteRow(index) {
	var row = grid2.getRow(index);
	
	if(confirm('您确认删除[' + (row['code_name'] || '') + ']吗？')) {
		grid2.deleteRow(row);
	}
}

function beginEdit(index) { 
    grid2.beginEdit(index);
}

function cancelEdit(index) {
	try{
		grid2.cancelEdit(index);
		    
	    var row = grid2.getRow(index);
	    if(row['__new']){
	    	grid2.deleteRow(index);
	    	//解除新增锁定
	    	grid2.setItemEnabled('add2');
	    }
	} catch(e){
		alert(e.message);
	}
}

function endEdit(index) {
    try{
	    if(grid2.endEdit(index) == false){
	    	return false;
	    }
	    
	    var row = grid2.getRow(index);
    	if(row['__new']) {
	    	delete row['__new'];
	    	//解除新增锁定
	    	grid2.setItemEnabled('add2');
	    }
    	
    	return true;
	} catch(e){
		alert(e.message);
		return false;
	}
}

function beforeSubmitEdit(editdata) {
	//检查数据修改
	if(!grid2.hasEditChanged(editdata)){
		alert('数据没有修改，请取消编辑操作');
		return false;
	}
	
	//检查参数有效性
	if($.trim(editdata.newdata['code_type']) == ''){
    	alert('代码类型不能为空');
    	return false;
    }
	
	if($.trim(editdata.newdata['code_name']) == ''){
    	alert('代码名称不能为空');
    	return false;
    }
	
	//设置修改颜色，突出显示修改状态
	grid2.setRowColor(editdata.record, powersi.color('error'));
	return true;
}

/////////////////////////////////////////////////////////////////////////////////////////
//grid2
/////////////////////////////////////////////////////////////////////////////////////////
/*输出操作*/
function renderOperate2(row, index, value) {
	var a = [];
	a.push('<input type="button" value="编辑" class="linkButton"');
	a.push(' onclick="doEdit(');
	a.push(index);
	a.push(')"');
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	
	a.push('<input type="button" value="删除" class="linkButton"');
	a.push(' onclick="doDel(');
	a.push(index);
	a.push(')"');
	a.push(" />");
	
	return a.join('');
}
//定义最后编辑行
var lasteditrow;
function doAdd() {
	clearForm('#editForm');
	
	//记录编辑行的索引
	lasteditrow = -1;
	
	dlg = popupDiv('#editDlg', '编辑数据', 600, {
		showMax: true,
		isHidden: false
	});
}

function doEdit(index){
	clearForm('#editForm');
	
	var row = grid3.getRow(index);
	json2form('#editForm', row);
	
	//记录编辑行的索引
	lasteditrow = grid3.getRowIndex(row);
	
	dlg = popupDiv('#editDlg', '编辑数据', 600, {
		showMax: true,
		isHidden: false
	});
}

function doDel(index){
	var row = grid3.getRow(index);
	
	if(confirm('您确认删除[' + (row['code_name'] || '') + ']吗？')) {
		grid3.deleteRow(row);
	}
}

function saveEdit() {
	//检查表单有效性
	if(!checkForm('#editForm')){
		return;
	}
	
	//更新表单数据到表格对象
	var data = form2json('#editForm');
	var row;
	if(lasteditrow < 0){
		row = grid3.addRow(data, grid3.getRow(0), true);
	} else {
		row = grid3.updateRow(lasteditrow, data);
	}
	
	//红色标识修改
	grid3.setRowColor(row, powersi.color('error'));
	
	closeDlg();
}
</script>
</body>
</powersi:html>