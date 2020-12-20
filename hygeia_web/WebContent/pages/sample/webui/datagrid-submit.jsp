<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	/*获取数据，演示在jsp中实现，业务代码必须在action实现*/
	com.powersi.sys.message.dao.BulletinDAO dao = com.powersi.hygeia.framework.util.BeanHelper
			.getBean(com.powersi.sys.message.dao.BulletinDAO.class);

	//查询条件
	com.powersi.sys.message.dto.SearchBulletinDTO dto = new com.powersi.sys.message.dto.SearchBulletinDTO();
	dto.setBeginDate("2001-01-01 00:00:00");
	dto.setEndDate("2020-01-01 00:00:00");
	dto.setQueryType("4");
	dto.setAuditFlag(new java.util.ArrayList<String>());
	dto.getAuditFlag().add("0");
	dto.getAuditFlag().add("1");
	dto.setSecondAuditFlag(new java.util.ArrayList<String>());
	dto.getSecondAuditFlag().add("0");
	dto.getSecondAuditFlag().add("1");
	
	//分页数据，使用服务端分页
	com.powersi.sys.util.PagerHelper.initPagination(request);
	java.util.List lst = dao.queryList(dto);
	for(int i = 0; i < lst.size(); i ++){
		java.util.Map map = (java.util.Map)lst.get(i);
		//把日期时间型转化成日期型
		map.put("effect_date", com.powersi.hygeia.framework.util.DateFunc.dateToString((java.sql.Timestamp)(map.get("effect_date")), "yyyyMMdd"));
		map.put("expire_date", com.powersi.hygeia.framework.util.DateFunc.dateToString((java.sql.Timestamp)(map.get("expire_date")), "yyyyMMdd"));
	}
	request.setAttribute("rs", com.powersi.sys.util.PagerHelper
			.getPaginatedList(lst));
	
	//全部数据，使用客户端分页
	request.setAttribute("rsAll", dao.queryList(dto));
%>
<powersi:html>
<head>
<powersi:head title="datagrid提交(非ajax)" />
</head>
<body>
	<powersi:codetable id="auditCode" codeType="audit_flag">
		var auditMap = {};
		$.each(auditCode, function(i, value){
			auditMap[value['id']] = value['text'];
		});
	</powersi:codetable>
	<powersi:codetable id="secondCode" codeType="second_audit_flag"></powersi:codetable>
	<powersi:script>
		var staffList = [
         	     {"id": "100", "text": "系统管理员"}, 
         	     {"id": "101", "text": "创智和宇"},
         	     {"id": "102", "text": "用户测试"}
        ];
	</powersi:script>
	
	<powersi:panelbox title="公告列表" key="panel_result">
		<powersi:form id="queryForm" namespace="/" action="/pages/sample/webui/datagrid-submit.jsp">
			<powersi:panelbox-toolbar>
				<div class="divButton">
					<div class="btn-group">
						<input type="button" value="刷新数据" onclick="reload();" class="btn btn-danger" />
					</div>
					<div class="btn-group">
						<input type="button" value="获取指定列" onclick="sel1();" class="btn btn-success" /> 
						<input type="button" value="获取选择行1" onclick="sel2();" class="btn btn-success" /> 
						<input type="button" value="获取选择行2" onclick="sel3();" class="btn btn-success" />
						<input type="button" value="获取所有行" onclick="sel4();" class="btn btn-success" />
						<input type="button" value="获取记录数" onclick="count();" class="btn btn-success" /> 
						<input type="button" value="获取修改" onclick="chg();" class="btn btn-success" />
					</div>
					<div class="btn-group">
						<input type="button" value="删除选择行1" onclick="del1();" class="btn btn-warning" />
						<input type="button" value="删除选择行2" onclick="del2();" class="btn btn-warning" />
						<input type="button" value="新增行" onclick="add1();" class="btn btn-warning" />
						<input type="button" value="追加行" onclick="add2();" class="btn btn-warning" />
						<input type="button"  value="更新行" onclick="update();" class="btn btn-warning" />
					</div>
				</div>
			</powersi:panelbox-toolbar>
		</powersi:form>
		
		<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-desktop red"></i> 服务端分页
				</a>
			</li>

			<li>
				<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
					<i class="icon-laptop green"></i> 客户端分页
				</a>
			</li>
		</ul>
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid" rowAttrRender="gridRowRender"
					name="rs" useAjax="false" formId="queryForm"
					checkbox="true" isChecked="checkRowFlag" isCheckable="checkRowStatus" selectRowButtonOnly="true" 
					enabledEdit="true" clickToEdit="true" onBeforeEdit="checkEditEnable"
					exportFileName="getExportFileName" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					
					<powersi:toolbar>
						<powersi:toolbar-item id="reload" text="刷新" title="重新加载数据"  icon="refresh" click="itemClick" />
						<powersi:toolbar-item id="sel1" text="选择列" title="获取指定列根据复选框" icon="info" click="itemClick" />
						<powersi:toolbar-item id="sel2" text="选择行1" title="获取选择行根据复选框" icon="info1" click="itemClick" />
						<powersi:toolbar-item id="sel3" text="选择行2" title="获取选择行根据点击" icon="info2" click="itemClick" />
						<powersi:toolbar-item id="sel4" text="所有行" title="获取所有行" icon="info3" click="itemClick" />
						<powersi:toolbar-item id="count" text="记录数" title="获取记录数" icon="info4" click="itemClick" />
						<powersi:toolbar-item id="del2" text="删除行1" title="删除选择行根据复选框" icon="delete1" click="itemClick" />
						<powersi:toolbar-item id="del2" text="删除行2" title="删除选择行根据点击" icon="delete2" click="itemClick" />
						<powersi:toolbar-item id="add1" text="新增行" title="在表格前面增加行" icon="add1" click="itemClick" />
						<powersi:toolbar-item id="add2" text="追加行" title="在表格后面增加行" icon="add2" click="itemClick" />
						<powersi:toolbar-item id="update" text="更新行" title="更新指定行数据" icon="edit" click="itemClick" />
					</powersi:toolbar>
					
					<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" frozen="true" />
					<powersi:datagrid-column name="bulletin_title" key="bulletin_title" render="renderTitle" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="sender_man" display="发布人" width="100" valueField="sender_staff" editor="{type:'popup', autoOpen: true, onButtonClick: popupStaff}"/>
					<powersi:datagrid-column name="send_date" display="发布时间" width="150" format="yyyy-MM-dd hh:mm:ss" editor="{type:'date', showTime: true}" />
					<powersi:datagrid-column name="user_kind_name" key="user_kind" data="user_kind" code="bulletin_user_kind" width="80" />
					<powersi:datagrid-column name="effect_date" key="effect_date" width="100" editor="{type:'date'}"/>
					<powersi:datagrid-column name="expire_date" key="expire_date" width="100" editor="{type:'popup', onButtonClick: popupDate}"/>
					<powersi:datagrid-column name="audit_flag" key="audit_flag" width="100" render="renderAuditFlag" editor="{type:'select', data: auditCode}"/>
					<powersi:datagrid-column name="audit_man" display="审核人" width="100" />
					<powersi:datagrid-column name="second_audit_flag_name" key="second_audit_flag" code="second_audit_flag" data="second_audit_flag" width="100" />
					<powersi:datagrid-column name="second_audit_man" display="审签人" width="100" />
				</powersi:datagrid>
			</div>
			<div id="tab-pane2" class="tab-pane">
				<powersi:datagrid id="gridAll" rowAttrRender="gridRowRender"
					name="rsAll" useAjax="false"
					checkbox="true" isChecked="checkRowFlag" isCheckable="checkRowStatus" selectRowButtonOnly="false" 
					exportFileName="getExportFileName" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
					<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80" frozen="true" />
					<powersi:datagrid-column name="bulletin_title" key="bulletin_title" render="renderTitle" width="100%" minWidth="150" frozen="true" />
					<powersi:datagrid-column name="sender_man" display="发布人" width="100" valueField="sender_staff" editor="{type:'popup', autoOpen: true, onButtonClick: popupStaff}"/>
					<powersi:datagrid-column name="send_date" display="发布时间" width="150" format="yyyy-MM-dd hh:mm:ss" editor="{type:'date', showTime: true}" />
					<powersi:datagrid-column name="user_kind_name" key="user_kind" data="user_kind" code="bulletin_user_kind" width="80" />
					<powersi:datagrid-column name="effect_date" key="effect_date" width="150" editor="{type:'date'}"/>
					<powersi:datagrid-column name="expire_date" key="expire_date" width="150" editor="{type:'popup', onButtonClick: popupDate}"/>
					<powersi:datagrid-column name="audit_flag" key="audit_flag" width="100" render="renderAuditFlag" editor="{type:'select', data: auditCode}"/>
					<powersi:datagrid-column name="audit_man" display="审核人" width="100" />
					<powersi:datagrid-column name="second_audit_flag_name" key="second_audit_flag" code="second_audit_flag" data="second_audit_flag" width="100" />
					<powersi:datagrid-column name="second_audit_man" display="审签人" width="100" />
				</powersi:datagrid>
				<powersi:script>
					<powersi:combobox id="cbUserKind" valueFieldID="valUserKind" codeType="bulletin_user_kind"></powersi:combobox>
					<powersi:dataform id="cond" prefixID="search_toolbar_" inputWidth="90" labelWidth="70"
						showClose="true" closeText="重置"
						sort="send_date asc,user_kind desc">
						<powersi:dataform-field name="user_kind" display="公告类型" width="90" type="combobox" options="cbUserKind" operator="==" />
						<powersi:dataform-field name="bulletin_title" display="公告标题" width="90" type="string" operator="like" />
						<powersi:dataform-field name="send_date" display="发布日期" width="100" type="date" options="{format:'yyyy-MM-dd'}" operator=">=" />
						<powersi:dataform-field name="send_date" display="-" rightToken="" labelAlign="left" labelWidth="10" width="100" type="date" options="{format:'yyyy-MM-dd'}" operator="<=" />
					</powersi:dataform>
				</powersi:script>
			</div>
		</div>
	</div>
	</powersi:panelbox>
	<powersi:errors />
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="staffDlg">
			<div style="margin: 5px 0 20px 0;">
				<powersi:combobox id="cbStaff" valueFieldID="staff_id" data="staffList">
					<powersi:hidden id="staff_id" name="staff_id" value=""/>
				</powersi:combobox>
			</div>
			
			<div class="textCenter">
				<powersi:button id="btnStaffOk" key="button_ok" onclick="selectStaff()"/>
				<powersi:button id="btnStaffClose" onclick="closeDlg()" key="button_cancel" />
			</div>
		</div>
		
		<div id="dateDlg">
			<div style="margin: 5px 0 20px 0;">
				<powersi:textfield id="txtDate" name="txtDate" mask="date"></powersi:textfield>
			</div>
			
			<div class="textCenter">
				<powersi:button id="btnDateOk" key="button_ok" onclick="selectDate()"/>
				<powersi:button id="btnDateClose" onclick="closeDlg()" key="button_cancel" />
			</div>
		</div>
	</div>
<script type="text/javascript">
$(function(){
	//绑定datagrid查询
	gridAll.bindCondition(cond);
});

function gridRowRender(rowdata, rowid){
	if (rowdata['audit_flag'] == '1') {
		return getColorStyle('success');
	}
}

function getExportFileName() {
	return '公告列表';
}

function renderOperate(row, index, value){
	var a = [];
	a.push('<input type="button" value="编辑" class="linkButton"');
	a.push(' onclick="doEdit(');
	a.push(index);
	a.push(')"');
	if(row['audit_flag'] == '1'){
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	
	a.push('<input type="button" value="删除" class="linkButton"');
	a.push(' onclick="doDel(');
	a.push(index);
	a.push(')"');
	if(row['audit_flag'] == '1'){
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	
	return a.join('');
}

function itemClick(item) {
	eval(item['id'] + '()');
}

function renderTitle(row, index, value) {
	return '<a href="#" onclick="javascript:doView(\'' + row['bulletin_url'] + '\')" title="' + value + '">' + value + '</a>';
}

function renderAuditFlag(row, index, value) {
	return auditMap[value];
}

/*勾选广州医保发布的未审核记录*/
function checkRowFlag(row) {
	return checkRowStatus(row) && row['sender_man'] == '广州医保';
}
/*只有未审核记录才允许复选框*/
function checkRowStatus(row) {
	return row['audit_flag'] == '0';
}
/*只有未审核记录才允许编辑*/
function checkEditEnable(editParm) {
	/*if(editParm['column']['name'] == 'send_date') {
		return checkRowStatus(editParm['record']);
	}
	
	return true;
	*/
	
	return checkRowStatus(editParm['record']);
}

var dlg;
function popupStaff() {
	//alert(powersi.tostring(grid.editor.editParm));
	var row = grid.editor.editParm['record'];
	//后台没有返回发送人工号，为了测试默认使用系统管理员
	cbStaff.setValue(row['sender_staff'] || '100');
	
	dlg = popupDiv('#staffDlg', '选择操作员', 200, {
			isHidden: false,
			onClosed: function(){
				grid.endEdit();
			}
	});
}

function selectStaff() {
	//alert(cbStaff.getText() + '\r\n' + cbStaff.getValue());
	var row = grid.editor.editParm['record'];
	if(cbStaff.getValue() != row['sender_staff']) {
		//手动更新编辑器(显示的是文本，隐藏的是值，与真实数据是相反的!!!)
		grid.editor.input.setValue(cbStaff.getValue());
		grid.editor.input.setText(cbStaff.getText());
	}
	
	closeDlg();
}

function popupDate() {
	var row = grid.editor.editParm['record'];	
	$('#txtDate').val(grid.editor.editParm.value);
	
	dlg = popupDiv('#dateDlg', '选择日期', 200, {
			isHidden: false,
			onClosed: function(){
				grid.endEdit();
			}
	});
}

function selectDate() {
	grid.editor.input.setValue($('#txtDate').val());
	closeDlg();
}

function closeDlg() {
	if(dlg){
		dlg.close();
		dlg = null;
	}
}

function doView(bulletinUrl) {
	popupInfo('view:' + bulletinUrl);
}
function doAdd() {
	popupInfo('add');
}

function doEdit(index) {
	var row = grid.getRow(index);
	popupInfo(powersi.tostring(row));
}

function doDel(index) {
	popupAlert('delete:' + index);
	grid.deleteRow(index);
}

function reload(){
	grid.loadData(true);
}

function sel1() {
	var rows = grid.getSelectedRows();
	alert(powersi.tostring(powersi.rows_extract(rows, "bulletin_id")));
	alert(powersi.tostring(powersi.rows_extract(rows, ["bulletin_id", "bulletin_title"])));
}

function sel2() {
	var rows = grid.getSelectedRows();
	alert(powersi.tostring(rows));
}

function sel3() {
	var row = grid.getSelectedRow();
	alert(powersi.tostring(row));
}

function sel4() {
	var rows = grid.getData();
	alert(powersi.tostring(rows));
}

function del1() {
	grid.deleteRow(grid.getSelectedRow());
}

function del2() {
	grid.deleteSelectedRow();
}

function add1() {
	grid.addRow({}, grid.getRow(0), true);
}

function add2() {
	grid.addRow({});
}

function count() {
	alert(grid.getRowsCount());
}

function update() {
	var row = grid.getSelectedRow();
	if (row == null) {
		alert("请选择需要更新的行");
		return;
	}

	var name = prompt("请输入公告标题", "");
	if (powersi.isvalue(name)) {
		grid.updateRow(row, {
			'bulletin_title': name
		});
	} else {
		alert("公告标题不能为空");
	}
}

function insert() {
	var data = {
		"bulletin_title" : '新增标题',
		"bulletin_id" : "-1"
	};

	alert(insertTable('row', data));
}

function chg() {
	alert(powersi.tostring(grid.getChangesJSON()));
}
</script>
</body>
</powersi:html>