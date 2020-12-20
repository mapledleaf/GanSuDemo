<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="用户类型维护" />
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
</head>
<body>
	<powersi:panelbox key="panel_query" allowAddition="false">
		<powersi:form id="queryForm" namespace="/user" action="UserKindAction">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="userKind" name="user_kind" label="用户类别" />
					<powersi:textfield id="kindName" name="kind_name" label="类别名称" />
					<powersi:combobox id="cbValidFlag" label="有效标志" valueFieldID="validFlag" codeType="valid_flag" isMultiSelect="true">
						<powersi:hidden id="validFlag" name="valid_flag" value="1" />
					</powersi:combobox>
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="类别列表">
		<powersi:datagrid id="grid" formId="queryForm" rowAttrRender="gridRowRender"
			showExportExcel="true" exportFileName="'用户类别'">
			<powersi:toolbar>
				<powersi:toolbar-item id="import" text="导入用户类别" icon="import" click="itemClick" title="导入用户类别"/>
				<powersi:toolbar-item id="export" text="导出用户类别" icon="export" click="itemClick" title="导出用户类别"/>
			</powersi:toolbar>
			<powersi:datagrid-column key="operate" width="50" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column display="用户类别" name="user_kind" width="60" />
			<powersi:datagrid-column display="类别名称" name="kind_name" width="50%" minWidth="100" />
			<powersi:datagrid-column display="类别描述" name="kind_desc" width="50%" minWidth="100" />
			<powersi:datagrid-column display="登录显示" name="login_name" width="100" />
			<powersi:datagrid-column display="登录类型" name="login_type" width="100" />
			<powersi:datagrid-column display="用户等级" name="grade_name" width="60" code="grade_id" data="grade_id" />
			<powersi:datagrid-column display="缺省角色" name="role_name" width="100" />
			<powersi:datagrid-column display="开始菜单" name="menu_name" width="100" />
			<powersi:datagrid-column display="有效标志" name="valid_flag" width="60" isExport="false" render="renderFlag" />
		</powersi:datagrid>
	</powersi:panelbox>
	
	<div class="hidden">
		<div id="editDlg">
			<powersi:form id="editForm">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="tabs1">
						<li class="active">
							<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
								<i class="icon-desktop red"></i> 基本信息
							</a>
						</li>
			
						<li>
							<a data-toggle="tab" href="#tab-pane2" id="tab2"> 
								<i class="icon-laptop green"></i> 登录SQL
							</a>
						</li>
						
						<li>
							<a data-toggle="tab" href="#tab-pane3" id="tab3"> 
								<i class="icon-tablet blue"></i> 查找SQL 
							</a>
						</li>
						
						<li>
							<a data-toggle="tab" href="#tab-pane4" id="tab4"> 
								<i class="icon-mobile-phone yellow"></i> 查询SQL
							</a>
						</li>
					</ul>
					<div class="tab-content content">
						<div id="tab-pane1" class="tab-pane active">
							<powersi:editorlayout cols="4" id="editTable">
								<powersi:editorlayout-row>
									<powersi:hidden id="edit_id" name="id"></powersi:hidden>
									<powersi:spinner id="edit_user_kind" name="user_kind" label="用户类别" required="true" min="1" max="9"></powersi:spinner>
									<powersi:spinner id="edit_dis_order" name="dis_order" label="显示序号" required="true" min="0" max="9999"></powersi:spinner>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield id="edit_kind_name" name="kind_name" label="类别名称" required="true" maxlength="20"></powersi:textfield>
									<powersi:textfield id="edit_kind_desc" name="kind_desc" label="类型描述" required="true" maxlength="100"></powersi:textfield>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield id="edit_login_name" name="login_name" label="登录显示" required="true" maxlength="15"></powersi:textfield>
									<powersi:textfield id="edit_login_ype " name="login_type" label="登录类型" required="true" maxlength="15"></powersi:textfield>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield id="edit_password_tip" name="password_tip" label="密码提示" maxlength="50" colspan="3"></powersi:textfield>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:codeselect id="edit_role_id" name="role_id" label="缺省角色" colspan="3" cssClass="select2" list="#request.roleList" headerKey="0" headerValue="无"></powersi:codeselect>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:textfield id="edit_menu_name" name="menu_name" label="开始菜单" colspan="3" buttonId="btnSelectMenu" onbuttonclick="selMenu()"></powersi:textfield>
									<powersi:hidden id="edit_start_menu" name="start_menu"></powersi:hidden>
								</powersi:editorlayout-row>
								<powersi:editorlayout-row>
									<powersi:codeselect id="edit_garde_id" name="grade_id" label="用户等级" codeType="grade_id" cssClass="select2"></powersi:codeselect>
									<powersi:radio codeType="valid_flag" key="valid_flag" name="valid_flag" id="edit_valid_flag" required="true" />
								</powersi:editorlayout-row>
							</powersi:editorlayout>
						</div>
						<div id="tab-pane2" class="tab-pane">
							<powersi:textarea id="edit_login_sql" name="login_sql" maxlength="4000" rows="10" title="登录sql（使用login_user作为检索条件)"></powersi:textarea>
						</div>
						<div id="tab-pane3" class="tab-pane">
							<powersi:textarea id="edit_find_sql" name="find_sql" maxlength="4000" rows="10" title="查找sql（使用user_id作为检索条件）"></powersi:textarea>
						</div>
						<div id="tab-pane4" class="tab-pane">
							<powersi:textarea id="edit_query_sql" name="query_sql" maxlength="4000" rows="10" title="查询sql（使用login_user和user_name作为检索条件)"></powersi:textarea>
						</div>
					</div>
				</div>
			</powersi:form>
			
			<div class="space-y"></div>
			<div class="divButton">
				<powersi:button id="btSave" key="button_save" onclick="doSave()"></powersi:button>
				<powersi:button id="btCancel" key="button_cancel" onclick="closeDlg()"></powersi:button>
			</div>
		</div>
		
		<div id="uploadDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请选择用户类别文件（使用json格式)</span></div>
			<div style="margin: 5px 0 20px 0;">
				<input type="file" id="fileUpload" name="uploads" class="file" />
			</div>
			
			<div class="floatLeft">
				<input id="forceUpdate" name="forceUpdate" class="checkbox" type="checkbox">
				<label class="checkboxLabel textError" for="forceUpdate">覆盖已存在的数据</label>
			</div>
			
			<div class="floatRight">
				<powersi:button id="btUpload" key="button_import" cssClass="btn btn-success" />
				<powersi:button id="btCloseUpload" onclick="closeUploadDlg()" key="button_close" />
			</div>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
function gridRowRender(rowdata, rowid){
	if (rowdata['valid_flag'] == '0') {
		return getColorStyle('error');
	}
}

function renderFlag(rowdata, index, value) {
	if (value === '0') {
		return '<span title="无效"><i class="icon-remove textError"></i></span>';
	} else {
		return '<span title="有效"><i class="icon-ok textSuccess"></i></span>';
	}
}

/*输出操作*/
function renderOperate(row, index, value) {
	var a = [];
	
	a.push('<input type="button" value="编辑" class="linkButton"');
	a.push(' onclick="editRow(');
	a.push(index);
	a.push(')"');
	a.push(" />");
	
	return a.join('');
}

var dlg;
var rowIndex;
function editRow(index){
	var row = grid.getRow(index);
	if(!row){
		return;
	}
	
	rowIndex = index;
	
	clearForm('#editForm');
	json2form('#editForm', row);
	if(dlg){
		dlg.show();
	} else {
		dlg = popupDiv('#editDlg', '编辑用户类别', 600);
		$('#editForm textarea').height($('#editTable').innerHeight());
	}
}

function closeDlg(){
	if(dlg){
		dlg.hide();
	}
}

function selMenu() {
	var param = {
		'rolenames': '已选择菜单：' + $('#edit_menu_name').val(),
		'staff_name': $('#edit_kind_desc').val(),
		'roles': 'allmenu',
		'title': '选择开始菜单',
		'tip': '<span class="textError">取消开始菜单请选择红色标识的菜单根节点</span>',
		'check': {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		}
	};
		
	popupDialogWithParam({
		url:rootPath + "/pages/sys/manager/MenuTree.jsp", 
		onClosed: function(){
			 var ret = this.returnValue || returnValue;

			 if(ret){
			    var menus = powersi.tojson(ret);
			    
			    if(powersi.length(menus) > 0){
			    	if(menus[0].id == '0'){
			    		$('#edit_menu_name').val('');
				    	$('#edit_start_menu').val('');
			    	} else {
			    		$('#edit_menu_name').val(menus[0].name || '');
				    	$('#edit_start_menu').val(menus[0].id || '');
			    	}
			    }
			 }
		}
	}, param, 550, 500);
}

function doSave() {
	if(!checkForm('#editForm')){
		return;
	}
	
	if(!confirm('您确认保存吗?')){
		return;
	}
	
	var data = form2json('#editForm');
	postAjax(rootPath + "/user/UserKindAction!save.action", data, function(res){
		if(!checkResult(res)){
			return;
		}
		
		data['id'] = data['user_kind'];
		grid.updateRow(rowIndex, data);
		
		alert(res.message);
		
		closeDlg();
	});
}

function itemClick(item){
	var itemname = item['id'];
	if(itemname == 'import'){
		upload();
	} else if(itemname == 'export'){
		download();
	}
}

function download(){
	postDownload(rootPath + '/user/UserKindAction!download.action', form2json('#queryForm'));
}

var dlgUpload = null;
function closeUploadDlg(){
	if(dlgUpload){
		dlgUpload.hide();
	}
}

function upload(){
	if(dlgUpload){
		dlgUpload.show();
	} else{
		dlgUpload = popupDiv("#uploadDlg", '导入文件', 350);
	}
}

//ajax文件上传
function initFileUpload(){
	var fileUpload = $("#fileUpload");
	try{
		fileUpload.fileupload({
	        url: rootPath + '/user/UserKindAction!upload.action',
	        dataType: 'json',
	        autoUpload: false,
	        replaceFileInput: false,
	        done: function (e, data) {
	        	if(data.result.errortype == "0"){
	        		//成功后重置input file
	        		fileUpload.fileupload("destroy");
	        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
	        		
	        		grid.reload(true);
	        		
	        		initFileUpload();
	        	}
	        	
	            showPageError(data.result);
	        },
	        fail: function (e, data) {
	            showPageError(data.message);
	        }
	    });
	}
	catch(ex){
		alert(ex.message + '\r\n' + '请检查是否包含头文件fileupload.js');
	}
}

//初始化文件上传
$(function(){
	initFileUpload();
	
	$("#btUpload").click(function(){
		var fileName = $('#fileUpload').val();
		if(powersi.isempty(fileName)){
			alert("请选择文件");
			return;
		}
		
		var forceUpdate = $('#forceUpdate').prop('checked');
		var tip = "您确认导入[" + fileName + "]吗?";
		if(forceUpdate){
			tip += '\r\n\r\n\r\n' + '请注意：操作会覆盖已存在的数据，建议您最好导出数据作为备份。';
		}
		
		if(confirm(tip) != true){
			return;
		}
		
		closeUploadDlg();
		
		showRunning(true);
		
		var data = {
			'forceUpdate': forceUpdate ? 'true' : 'false'	
		};
		
		var fileUpload = $('#fileUpload');
		fileUpload.fileupload('send', {
	        fileInput: fileUpload,
	        formData: data
	    }).complete(function (result, textStatus, jqXHR) {showRunning(false);});
	});
});
</script>
</body>
</powersi:html>