<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<powersi:head title="重置密码" />
<script type="text/javascript">
	$(document).ready(function() {
	  $(".linkButton").hover(
	  	function () {
	    	$(this).addClass("linkButton_hover");
	   	},
	    function () {
	   	 	$(this).removeClass("linkButton_hover");
	  });
	});
	
  	function query(){
  		/*
  		var loginUser = $('#loginUser').val() || "";
  		var userName = $('#userName').val() || "";
  		if(loginUser.length == 0 && userName.length == 0){
  			alert("请输入登录名或用户名");
  			$('#loginUser').focus();
  			return false;
  		}*/
  		
		return true;
	}
	
	function resetSuccess(json) {
		if(!checkJSONResult(json)){
			return;
	    }
		
		var userId = json.data.user_id;
		if(userId === undefined || userId == null){
			return;
		}
		
		$("#reset" + userId).attr("disabled", "disabled");
		
		$("#err" + userId).text("0");
		$("#pwdstate" + userId).text("");
		
		alert(json.message);
	}
	
	function resetPassword(userid, username, userkind){
		if(!confirm("您确定要重置用户[" + username + "]的密码吗?")){
			return;
		}
		
		postJSON("${rootPath}/user/ResetPassword.action", {"userId": userid, "userKind": userkind}, resetSuccess);
	}
	
	function unlockSuccess(json) {
		if(!checkJSONResult(json)){
			return;
	    }
		
		var userId = json.data.user_id;
		if(userId === undefined || userId == null){
			return;
		}
		
		$("#unlock" + userId).attr("disabled", "disabled");
		
		$("#err" + userId).text("0");
		$("#lockstate" + userId).text("");
		$("#reason" + userId).text("");
		
		alert(json.message);
	}
	
	function unlockUser(userid, username, userkind){
		if(!confirm("您确定要解锁用户[" + username + "]吗?")){
			return;
		}
		
		postJSON("${rootPath}/user/UnlockUser.action", {"userId": userid, "userKind": userkind}, unlockSuccess);
	}
</script>
</head>
<body>
	<powersi:form namespace="/user" action="queryUserList">
		<powersi:groupbox title="查询条件">
			<powersi:editorlayout cols="4">
				<tr>
					<!-- powersi:radio name="userKind" key="user_kind"  list="#request.codeUserKind"  colspan="3" required="true" /-->
					<div id="radio_671072412">
   					 <td class="tdLabel"><label for="radio_671072412" class="label"><span class="required">*</span>用户类别</label></td>
					<td class="tdInput" colspan="3"><input type="radio" name="userKind" id="radio_6710724122" checked="checked" value="2" class="validate[required] radio" label="用户类别"/><label for="radio_6710724122">单位</label>
					
					</div>
				</tr>
				<tr>
					<powersi:textfield name="loginUser" id="loginUser" key="login_user" />
					<powersi:textfield name="userName" id="userName" key="user_name" />
				</tr>
				<tr>
					<powersi:buttons cols="4">
						<powersi:submit type="submit"  onclick="return query();" key="button_query" />
					</powersi:buttons>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
	</powersi:form>
	
	<powersi:groupbox title="查询结果">
		<display:table name="rsList" id="row" requestURI="/user/queryUserList.action">
			<display:column property="page_rowno" title="序号" />
			<display:column property="login_user" title="登录名" />
			<display:column property="user_name" title="用户名" />
			<display:column title="密码状态">
				<span id="pwdstate${row.user_id}">${ row.password_flag == '1' ? "******" : ""}</span>
			</display:column>
			<display:column title="密码错误次数">
				<div id="err${row.user_id}">${ row.password_errorcount}</div>
			</display:column>
			<display:column title="锁定状态">
				<span id="lockstate${row.user_id}">${ row.lock_state == '1' ? "锁定" : ""}</span>
			</display:column>
			<display:column title="锁定原因">
				<div id="reason${row.user_id}">${ row.lock_reason}</div>
			</display:column>
			<display:column title="操作">
				<input type="button" value="重置密码" class="linkButton"
					id="reset${row.user_id}"
					onclick="resetPassword('${row.user_id}','${row.user_name}','${row.user_kind}')"
					${row.password_flag== '0' && row.password_errorcount=='0' ? "disabled='disabled'" : ""} />
			</display:column>
			<display:column title="操作">
				<input type="button" value="用户解锁" class="linkButton"
					id="unlock${row.user_id}"
					onclick="unlockUser('${row.user_id}','${row.user_name}','${row.user_kind}')"
					${row.lock_state !='1' ? "disabled='disabled'" : ""} />
			</display:column>
		</display:table>
	</powersi:groupbox>
	<powersi:errors />
</body>
</html>