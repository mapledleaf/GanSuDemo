<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page
	import="com.powersi.sys.util.UserKindHelper,com.powersi.sys.user.dto.UserKindDTO,com.powersi.hygeia.framework.util.UtilFunc"%>
<%
	UserKindDTO userKindDto = UserKindHelper.get(request.getParameter("user_kind"));
	if(userKindDto == null){
		throw new com.powersi.hygeia.framework.exception.HygeiaException("无效的用户类别");
	}
	
	com.powersi.hygeia.framework.UserInfo user = com.powersi.hygeia.framework.BusiContext.getUserInfo();
	
	String centerFilter = "1 = 0";
	String staffLevel = UtilFunc.getString(user, "staff_level", "9");
	if(user.isSuperUser() || staffLevel.equals("1")){
		centerFilter = "1 = 1";
	} else {
		if(staffLevel.equals("2")){
			centerFilter = "aaa027 in (" + UtilFunc.getString(user, "center_list", "0") + ")";	
		} else {
			centerFilter = "aaa027 = " + UtilFunc.getString(user, "center_id", "0");
		}
	}
	
	String centerRequired = "false";
	int userKind = userKindDto.getUserKind().intValue();
	if(userKind == 8){
		centerRequired = "true";
		centerFilter = centerFilter + " and substr(aaa027, 5, 2) <> '00'";
		request.setAttribute("userType", "2");
	} else if(userKind == 2){
		request.setAttribute("userType", "3");
		request.setAttribute("userLevel", "6");
	} else {
		request.setAttribute("userType", "6");
		request.setAttribute("userLevel", "9");
	}
	
	request.setAttribute("roleId", userKindDto.getRoleId());
	
	request.setAttribute("userKind", userKindDto.getUserKind().toString());
	request.setAttribute("userKindName", userKindDto.getKindName());
	
	int gradeId = Integer.parseInt(UtilFunc.getString(user, "grade_id", "0"));
	if(user.isSuperUser()){
		gradeId = 9;
	}
	request.setAttribute("gradeId", gradeId);
%>
<powersi:html>
<head>
<powersi:head title="用户管理" />
<script src="${rootPath}/resource/js/md5.js" type="text/javascript"></script>
</head>
<body class="container-fluid">
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/user" action="UserManager!queryUserList">
			<input type="hidden" id="userKind" name="user_kind" value="${userKind }" />
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="loginUser" name="login_user"  key="login_user" />
					<powersi:textfield id="userName" name="user_name"  key="user_name" />
					<powersi:textfield id="orgName" name="org_name"  label="${userKindName }名" />
					<powersi:editorlayout-button colspan="2">
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<div class="space"></div>
	<div class="row">
		<div class="col-6">
			<powersi:panelbox key="panel_result" title="用户列表" allowCollapse="false">
				<powersi:datagrid id="gridList" formId="queryForm" frozen="false"
					rowAttrRender="gridRowRender" onSelectRow="selectRow" onLoadedData="gridLoadedData"
					showExportExcel="true" exportFileName="'用户列表'" >
					<powersi:datagrid-column key="login_user" name="login_user" width="80" />
					<powersi:datagrid-column key="user_name" name="user_name" width="50%" />
					<powersi:datagrid-column display="${userKindName }名" name="org_name" width="50%" />
					<powersi:datagrid-column display="用户状态" name="user_sta_name" width="60" render="renderSta" code="staff_sta" data="user_sta"/>
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
		<div class="col-6">
			<powersi:panelbox iconClass="icon-user" title="用户信息" allowCollapse="false" id="detailPanel">
					<powersi:panelbox-toolbar>
						<ul class="nav nav-tabs" id="tabs">
							<li class="active">
								<a data-toggle="tab" href="#tab1">
									<i class="icon-th green"></i>
									用户信息
								</a>
							</li>
							<li id="liOrg">
								<a data-toggle="tab" href="#tab2">
									<i class="icon-th-list red"></i>
									${userKindName }列表
								</a>
							</li>
						</ul>
					</powersi:panelbox-toolbar>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<powersi:form id="userForm">
								<input type="hidden" name="user_kind" id="user_kind" value="${userKind }" />
								<input type="hidden" name="user_type" id="user_type" value="${userType }" />
								<input type="hidden" name="user_id" id="user_id" />
								<powersi:editorlayout cols="4">
									<powersi:editorlayout-row>
										<powersi:editorlayout-group>
											用户基本信息
										</powersi:editorlayout-group>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="login_user" id="login_user"
											key="login_user" maxlength="10" required="true" />
										<powersi:password name="password" id="password" label="登录密码"
											maxlength="20" title="不修改登录密码，请保留此字段为空" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="user_name" id="user_name"
											key="user_name" maxlength="25" required="true" />
										<powersi:spinner name="dis_order" id="dis_order" maxlength="6"
											key="dis_order" required="true" validate="integer,min[0],max[999999]" 
											min="0" max="999999" mouseWheel="true" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:codeselect name="center_id" id="center_id" required="<%=centerRequired %>"
											label="所属中心" codeType="aaa027_list" colspan="3" codeFilter="<%=centerFilter %>"
											cssClass="select2" />
									</powersi:editorlayout-row>
									<s:if test='#request.userKind=="8"'>
									<powersi:editorlayout-row>
										<powersi:radio id="user_level" name="user_level" colspan="3" required="true" key="staff_level" codeType="staff_level" codeFilter="data_value >= 4"></powersi:radio>
									</powersi:editorlayout-row>
									</s:if>
									<s:else>
										<powersi:hidden id="user_level" name="user_level" value="${userLevel }" />
									</s:else>
									<powersi:editorlayout-row>
										<powersi:textfield label="所属${userKindName }" name="org_name" id="org_name" colspan="3" 
											readonly="true" required="true"
											buttonId="btnSelectOrg" buttonTitle="选择用户所属${userKindName }" onbuttonclick="openOrgDlg()"></powersi:textfield>
										<powersi:hidden name="org_id" type="hidden" id="org_id" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:codeselect name="role_list" id="role_list" colspan="3" cssClass="select2"
											label="角色列表" data-placeholder="请选择角色(多选)"
											multiple="true" list="#request.roleList" listKey="role_id" listValue="role_name" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:editorlayout-group>
											用户附加信息
										</powersi:editorlayout-group>
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="postal_code" id="postal_code"
											key="postal_code" maxlength="6" validate="zipcode" />
										<powersi:textfield name="address" id="address" key="address"
											maxlength="100" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="office_phone" id="office_phone"
											key="office_phone" maxlength="20" validate="telephone" />
										<powersi:textfield name="fax" id="fax" key="fax"
											maxlength="20" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="mobile_phone" id="mobile_phone"
											key="mobile_phone" maxlength="11" validate="mobilephone" />
										<powersi:textfield name="home_phone" id="home_phone"
											key="home_phone" maxlength="20" validate="telephone" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textfield name="email" id="email" key="email"
											maxlength="100" colspan="3" validate="email" />
									</powersi:editorlayout-row>
									<powersi:editorlayout-row>
										<powersi:textarea name="remark" id="remark" maxlength="100"
											rows="4" key="remark" colspan="3" />
									</powersi:editorlayout-row>
								</powersi:editorlayout>
							</powersi:form>
						</div>
						<div class="tab-pane" id="tab2">
							<powersi:datagrid id="gridOrgList" frozen="false" checkbox="true" heightDiff="-45"
								usePager="false" enabledSort="false" dataAction="local">
								<powersi:toolbar>
									<powersi:toolbar-item id="add" text="添加${userKindName }" icon="add2" click="itemClick" />
									<powersi:toolbar-item id="del" text="移除${userKindName }" icon="delete2" click="itemClick" />
								</powersi:toolbar>
								<powersi:datagrid-column display="${userKindName }编号" name="org_id" width="30%" />
								<powersi:datagrid-column display="${userKindName }名称" name="org_name" width="70%" />
							</powersi:datagrid>
						</div>
					</div>
			</powersi:panelbox>
			<div class="divButton" id="btnPanel">
				<powersi:button id="btAdd" key="button_add" onclick="doAdd()"></powersi:button>
				<powersi:button id="btCopy" key="button_copy" onclick="doCopy()"></powersi:button>
				<powersi:button id="btSave" key="button_save" onclick="doSave()"></powersi:button>
				<s:if test='#request.gradeId>=9'>
					<powersi:button id="btDel" key="button_delete" onclick="doDel()"></powersi:button>
				</s:if>
				<powersi:button id="btCancel" key="button_cancel" onclick="doCancel()"></powersi:button>
				<powersi:button id="btMenuTree" key="button_view" value="查看菜单权限" onclick="doMenuTree()" title="查看菜单权限" />
			</div>
		</div>
	</div>
<powersi:errors />
<!-- 隐藏区 -->
<div class="hidden">
	<div id="dlgOrg">
		<powersi:datagrid id="gridOrgDlg" frozen="false" checkbox="false" height="350" delayLoad="true" 
			onDblClickRow="doDlgOk"
			url="${rootPath }/user/UserManager!queryOrgList.action?user_kind=${userKind }">
			<powersi:datagrid-column display="${userKindName }编号" name="org_id" width="20%" />
			<powersi:datagrid-column display="${userKindName }代码" name="org_code" width="20%" />
			<powersi:datagrid-column display="${userKindName }名称" name="org_name" width="60%" />
		</powersi:datagrid>
		<div class="space-y"></div>
		<div class="divButton">
			<powersi:button id="btnDlgOk" key="button_ok" onclick="doDlgOk()"></powersi:button>
			<powersi:button id="btnDlgCancel" key="button_cancel" onclick="doDlgCancel()"></powersi:button>
		</div>
	</div>
	<div id="dlgSel">
		<powersi:datagrid id="gridSelDlg" frozen="false" checkbox="true" height="350" delayLoad="true" 
			isChecked="isSelected" onCheckRow="selRow" onCheckAllRow="selAllRow"
			url="${rootPath }/user/UserManager!queryOrgList.action?user_kind=${userKind }">
			<powersi:datagrid-column display="${userKindName }编号" name="org_id" width="20%" />
			<powersi:datagrid-column display="${userKindName }代码" name="org_code" width="20%" />
			<powersi:datagrid-column display="${userKindName }名称" name="org_name" width="60%" />
		</powersi:datagrid>
		<div class="space-y"></div>
		<div class="divButton">
			<powersi:button id="btnSelOk" key="button_ok" onclick="doSelOk()"></powersi:button>
			<powersi:button id="btnSelCancel" key="button_cancel" onclick="doSelCancel()"></powersi:button>
		</div>
	</div>
</div>
<script type="text/javascript">
var userKind = '${userKind}';
var userKindName = '${userKindName}';
var roleId = '${roleId}';

var NEW_USERID = "0";

var _curItemId = "";
var _curItemData = "";

function setSize() {
	try{
		var winheight = $(window).height();
		var detailPanel = $('#detailPanel');
		detailPanel.height(winheight - detailPanel.offset().top - 50);
	} catch(ex){
		alert(ex.message);
	}
}

$(function(){
	setSize();
	$(window).resize(setSize);
	
	var condPanel = {
			validate: false, labelWidth: 80, showClose: false,
			fields: [
			   { name: 'org_code', label: userKindName + '代码',  width:160, newline: false},
			   { name: 'org_name', label: userKindName + '名称', width:160, newline: false}
	]};
	
	condPanel['prefixID'] = 'search_org_';
	gridOrgDlg.bindCondition(condPanel);
	
	condPanel['prefixID'] = 'search_sel_';
	gridSelDlg.bindCondition(condPanel);
	
	/*医院用户不需要选择组织列表*/
	if(userKind == '3'){
		$('#liOrg').hide();
	}
});

function renderSta(rowdata, index, value) {
	if (rowdata['user_sta'] === '0') {
		return '<span title="禁用"><i class="icon-remove textError"></i></span>';
	} else {
		return '<span title="启用"><i class="icon-ok textSuccess"></i></span>';
	}
}

function gridRowRender(rowdata, rowid){
	if (rowdata['user_sta'] === '0') {
		return getColorStyle('error');
	}
}

function selectRow(rowdata, rowid, rowobj){
	var userId = rowdata['user_id'];
	if(userId == _curItemId){
		return;
	}
	
	getUserInfo(userId);
}

function gridLoadedData(grid){
	if(grid.getRowsCount() > 0){
		grid.select(0, true);//触发选择事件
	} else {
		doAdd();
	}
}

function getUserInfo(userId){
    doReset();
    _curItemId = userId;
    
    postAjax(rootPath + "/user/UserManager!getUser.action", {"user_kind": userKind, "user_id": userId}, showUserInfo);
}

function showUserInfo(json) {
    if (!checkResult(json)) {
        return;
    }

    var data = json.data;
    if(!powersi.isvalue(data)){
        popupError('获取用户信息失败');
        return;
    }
    
    $('#btnPanel :button').show();
    json2form('#userForm', data);
   
    if(powersi.isvalue(json.data.org_list) && json.data.org_list.length > 0){
    	gridOrgList.addRows(json.data.org_list);
    } 
    gridOrgList.acceptChanges();
    
    _curItemData = $('#userForm').serialize() + "&org_list=" + getSelectOrgs();
}

function doAdd() {
	doReset();
    _curItemId = NEW_USERID;

    if(isDataGrid(gridList)) {
    	var row = gridList.getSelectedRow();
    	if(row)
    		gridList.unselect();
    }
    
    $('#btSave').show();
    
    $('#login_user').focus();
}

function doReset() {
	_curItemId = NEW_USERID;
	$('#btnPanel :button:not(#btMenuTree)').hide();

	resetForm('#userForm');
	
	//重置隐藏数据
	$('#user_id').val(NEW_USERID);
    $('#dis_order').val("0");
    $('#org_id').val('');
    
    if(isDataGrid(gridOrgList)){
    	gridOrgList.reset();	
    }
}

function doCopy() {
    $('#user_id').val(NEW_USERID);
    
    $('#login_user').val($('#login_user').val() + " 复制");
    $('#user_name').val("");
    
    _curItemId = NEW_USERID;

    if(gridList) {
    	var row = gridList.getSelectedRow();
    	if(row)
    		gridList.unselect();
    }
    
    $('#btnPanel :button').hide();
    $('#btSave').show();
    
    $('#login_user').focus();
}

function doSave() {
	var action = null;
	var orgs = getSelectOrgs();
    var saveItemData = $("#userForm").serialize() + "&org_list=" + getSelectOrgs();
    
    if (_curItemId != NEW_USERID) {
        if (saveItemData == _curItemData) {
        	popupWarn("没有修改，不需保存");
            return;
        }

        if(powersi.check("password", "str")){
	       if(!confirm('您确认修改用户登录密码吗?')){
	        	return;
	       }
        }
    }
   
    if(!checkForm("#userForm")){
    	return;
    }
    
    var pwd = $('#password').val();
    if(pwd != ''){
    	 $('#password').val(hex_md5(pwd).toUpperCase());
    	 saveItemData = $("#userForm").serialize() + "&org_list=" + orgs;
    	 $('#password').val('');
    }
    
    postAjax(rootPath + "/user/UserManager!saveUser.action", saveItemData, saveSuccess);
}

function saveSuccess(json){
    if(!checkResult(json)){
	    return;
    }

    alert(json.message);
    
    var userId = json.data.user_id;
    $('#user_id').val(userId);
    
    var form = form2json('#userForm');
    if(_curItemId == NEW_USERID){
    	var row = gridList.addRow(form);
    	gridList.select(row, false);//不触发选择事件
    	gridList.scrollToRow(row);
    } else{
    	gridList.updateRow(gridList.getSelected(), form);
    }
	
    _curItemId = userId;
    
    var orgs = getSelectOrgs();
    _curItemData = $("#userForm").serialize() + "&org_list=" + orgs;
    
    $('#btAdd').show();
    $('#btCopy').show();
    $('#btCancel').show();
    $('#btDel').show();
}

function doDel() {
	if (_curItemId == NEW_USERID) {
		popupWarn("没有保存，不能删除");
        return;
    }
    
    if (!confirm("您确认删除用户[" + $('#login_user').val() + "]吗?")) {
        return;
    }
    
    var row = gridList.getSelectedRow();
    postAjax(rootPath + "/user/UserManager!deleteUser.action", _curItemData, function(res){
    	 if(!checkResult(res)){
    		 return;
    	  }

    	 alert(res.message);
    	 
    	 gridList.deleteRow(row);
    	 gridLoadedData(gridList);
    });
}

function doCancel(){
	getUserInfo(_curItemId);
}

function doMenuTree(){
	var data = $('#role_list').select2('data');
	
	var a = [];
	var b = [];
	$.each(data, function(i, sel){
		if(sel.id != roleId){
			a.push(sel.id);
			b.push(sel.text);
		}
	});
	
	//添加缺省角色
	a.push(roleId);
	
	var param = {
		'roles': a.join(','),
		'rolenames': b.join(','),
		'staff_name' : $('#user_name').val(),
		'login_user' : $('#login_user').val(),
		'userid': $('#user_id').val()
	};
	
	popupDialogWithParam(rootPath + "/pages/sys/manager/MenuTree.jsp", param, 550, 500);
}

function getSelectOrgs() {
	var rows = gridOrgList.getData();
	return powersi.tostring(rows);
}

/*选择所属组织*/
var dlgOrg = null;
function closeOrgDlg(){
	if(dlgOrg){
		dlgOrg.hide();
	}
}

function openOrgDlg(){
	if(userKind == "8"){
		var json = form2json('#userForm');
		
		if(powersi.length(json['center_id']) == 0){
			popupAlert('请先选择所属中心');
			return;
		}
		
		if(powersi.length(json['user_level']) == 0){
			popupAlert('请先选择用户级别');
			return;
		}
		
		gridOrgDlg.setParm('center_id', json['center_id']);
		gridOrgDlg.setParm('org_level', json['user_level']);
	}
	
	if(dlgOrg){
		//取消全部选择
		gridOrgDlg.selectRow(false);
		
		if(userKind == "8"){
			gridOrgDlg.reload(true);
		}
		dlgOrg.show();
	} else {
		dlgOrg = popupDiv("#dlgOrg", '选择所属' + userKindName, 600);
		
		gridOrgDlg.reload(true);
	}
}

function doDlgOk() {
	var row = gridOrgDlg.getSelectedRow();
	if(row == null || row['org_id'] == null){
		popupWarn('请选择' + userKindName);
		return;
	}
	
	//取消全部选择
	gridOrgDlg.selectRow(false);

	$('#org_id').val(row['org_id']);
	$('#org_name').val(row['org_name']);
	
	closeOrgDlg();
}

function doDlgCancel() {
	closeOrgDlg();
}

/*选择组织列表*/
function itemClick(item){
	var itemid = item["id"];
	if(itemid == "add"){
		openSelDlg();
	} else if(itemid == "del"){
		gridOrgList.deleteSelectedRow();
	}
}

/*存放以org_id为key，行数据为value的已选择行集合*/
var selectedRows = {};
/*行的主键*/
var selectedKey = "org_id";
/*最大选择数*/
var selectedMax = 100;

function isSelected(row) {
	return selectedRows[row[selectedKey]] != null ? true : false;
}

function addSelected(row) {
    if(!isSelected(row)){
    	/*获取的数据必须格式化，否则会影响datagrid*/
    	selectedRows[row[selectedKey]] = gridSelDlg.formatRow(row);    	
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

var dlgSel = null;
function closeSelDlg(){
	if(dlgSel){
		dlgSel.hide();
	}
}

function openSelDlg(){
	selectedRows = {};
	$.each(gridOrgList.getData(), function(i, row){
		selectedRows[row['org_id']] = row;
	});
	
	if(userKind == "8"){
		var json = form2json('#userForm');
		
		if(powersi.length(json['center_id']) == 0){
			popupAlert('请先选择所属中心');
			return;
		}
		
		if(powersi.length(json['user_level']) == 0){
			popupAlert('请先选择用户级别');
			return;
		}
		
		gridSelDlg.setParm('center_id', json['center_id']);
		gridSelDlg.setParm('org_level', json['user_level']);
	}
	
	if(dlgSel){
		if(userKind == "8"){
			gridSelDlg.reload(true);
		}
		
		//重画grid
		gridSelDlg.reRender();
		
		dlgSel.show();
	} else {
		dlgSel = popupDiv("#dlgSel", '选择' + userKindName + '列表', 600);
		
		gridSelDlg.reload(true);
	}
	
	/*包含checkbox的datagrid需要每次刷新显示*/
	gridSelDlg.refreshSize();
}

function doSelOk() {
	var rows = [];
	$.each(selectedRows, function(key, row){
		rows.push(row);
	});
	
	if(rows.length > selectedMax){
		popupWarn('总共选择了 ' + rows.length + ' 个' + userKindName + '，\n最多只支持选择' + selectedMax + ' 个。');
		return;
	}
	
	gridOrgList.set('data', rows);
	
	selectedRows = {};
	
	closeSelDlg();
}

function doSelCancel() {
	closeSelDlg();
}
</script>
</body>
</powersi:html>