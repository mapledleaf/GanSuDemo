<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String userKind = request.getParameter("userkind");
	String userKindFilter = "";
	if(userKind != null && userKind.length() > 0) {
		userKindFilter = "user_kind in (" + userKind + ")";
	}
%>
<powersi:html>
<head>
<powersi:head title="用户锁定" />
</head>
<body>
	<powersi:panelbox key="panel_query">
		<powersi:form id="queryForm" namespace="/user" action="queryUserListForLock">
			<powersi:editorlayout cols="10%,20%,10%,15%,10%,15%,20%">
				<powersi:editorlayout-row>
					<powersi:radio name="userKind" key="user_kind" codeType="user_kind" codeFilter="<%=userKindFilter %>" codeSort="user_kind desc" required="true" />
					<powersi:textfield name="loginUser" id="loginUser" key="login_user" validate="groupRequired[query]" placeholder="登录名,用户名任填一项" />
					<powersi:textfield name="userName" id="userName" key="user_name" validate="groupRequired[query]" placeholder="登录名,用户名任填一项" />
					<powersi:editorlayout-button>
						<powersi:submit id="btSubmit" key="button_query" />
						<powersi:reset id="btReset" key="button_reset" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	
	<powersi:panelbox key="panel_result" title="用户列表">
		<powersi:datagrid id="grid" formId="queryForm" rowAttrRender="gridRowRender" delayLoad="true" fixedCellHeight="false">
			<powersi:datagrid-column key="operate" width="130" frozen="true" isSort="false" isExport="false" render="renderOperate" />
			<powersi:datagrid-column key="login_user" name="login_user" width="150" frozen="true" />
			<powersi:datagrid-column key="user_name" name="user_name" width="150" frozen="true" />
			<powersi:datagrid-column key="lock_state" name="lock_state_name" code="lock_state" data="lock_state" width="100" render="renderLockState" />
			<powersi:datagrid-column key="lock_time" name="lock_time" width="150" />
			<powersi:datagrid-column key="lock_reason" name="lock_reason" width="100%" minWidth="100" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="reasonDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请填写锁定原因</span></div>
				<div style="margin: 5px 0 15px 0;">
					<powersi:textarea id="reason" name="reason" rows="5" maxlength="100"></powersi:textarea>
				</div>
				<div class="textRight">
					<powersi:button id="btLock" onclick="doLockUser()" key="button_lock" />
					<powersi:button id="btCancel" onclick="closeReason()" key="button_close" />
				</div>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		$(':radio[name="userKind"]:first').prop('checked', true);
	});

	function gridRowRender(rowdata, rowid){
		if (rowdata['lock_state'] == '1') {
			return getColorStyle('error');
		}
	}
	
	function renderOperate(row, index, value){
		var a = [];
		a.push('<input type="button" value="用户锁定" class="linkButton"');
		a.push(' onclick="lockUser(');
		a.push(index);
		a.push(')"');
		if(row['lock_state'] == '1'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="button" value="用户解锁" class="linkButton"');
		a.push(' onclick="unlockUser(');
		a.push(index);
		a.push(')"');
		if(row['lock_state'] != '1'){
			a.push(' disabled="disabled"');
		}
		a.push(" />");
		
		return a.join('');
	}
	
	function renderLockState(row, index, value){
		if (row['lock_state'] == '1') {
			return '<span title="已锁定"><i class="icon-lock"></i></span>';
		} else {
			return '<span title="未锁定">&nbsp;</span>';
		}
	}
	
    var dlg = null;
    var selectUser = null;
    var selectIndex = null;
	function lockUser(index){
		var row = grid.getRow(index);
		selectUser = {
			userId: row["user_id"],
			userKind: row["user_kind"],
			userName: row["user_name"],
			reason: ''
		};
		
		selectIndex = index;
		$("#reason").val("");
		
		if(dlg){
			dlg.show();
		} else{
			dlg = popupDiv("#reasonDlg", '锁定原因', 350);
		}
		
		$("#reason").focus();
	}
	
    function closeReason(){
    	if(dlg){
			dlg.hide();
    	}
    }
    
	function doLockUser(){
		if(!selectUser){
			return;
		}
		
		selectUser.reason = $('#reason').attr('value');
		
		if($.trim(selectUser.reason) == ""){
			alert('锁定原因不能为空!');
			$('#reason').focus();
			return;
		}
		
		closeReason();
		
		postJSON(rootPath + "/user/LockUser.action", selectUser, postSuccess);
	}
		
	function unlockUser(index){
		var row = grid.getRow(index);
		selectUser = {
			userId: row["user_id"],
			userKind: row["user_kind"],
			userName: row["user_name"],
			reason: ''
		};
		selectIndex = index;
		
		if(!confirm("您确定要解锁用户[" + row["user_name"] + "]吗?")){
			return;
		}
		
		postJSON(rootPath + "/user/UnlockUser.action", selectUser, postSuccess);
	}
	
	function postSuccess(json) {
		selectUser = null;
		
		if(!checkJSONResult(json) || !json.data) {
			return;
		}
		
		grid.updateRow(selectIndex, json.data);
		
		//grid的updateRow不会触发row属性变化事件，手工重置row的style(冻结和非冻结都需要重置)
		grid.setRowColor(selectIndex, powersi.color(json.data['lock_state'] == '1' ? 'error' : ''));
		
		//以下代码可以实现重新渲染所有row属性
		//grid.reRender();
		//grid.select(selectIndex);
		
		selectIndex = null;
		alert(json.message);
	}
</script>
</body>
</powersi:html>