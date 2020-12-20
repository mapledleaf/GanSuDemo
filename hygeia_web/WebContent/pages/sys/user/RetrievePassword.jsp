<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.lang.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.powersi.com.cn/tags" prefix="powersi"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ include file="/pages/util/header.jsp"%>
<link type="text/css" rel="stylesheet" href="<%=rootPath%>/resource/js/jqueryvalid/style/validator.css"></link>
<script src="<%=rootPath %>/resource/js/jquery.js" type="text/javascript"></script>
<script src="<%=rootPath %>/resource/js/jqueryvalid/formValidator.js" type="text/javascript"></script>
<script src="<%=rootPath %>/resource/js/jqueryvalid/formValidatorRegex.js" type="text/javascript"></script>
<title>找回密码</title>
<script type="text/javascript">
$(document).ready(function() {
    $.formValidator.initConfig({
        formid: "Form1",
        onerror: function(msg) {
            alert(msg);
            $("#save").removeAttr('disabled');
            return false;
        },
        onsuccess: function() {
            $("#save").attr("disabled", 'true');
            //showRunning(true);
            return true;
        }
    });
    if (document.getElementById("verifycode") != null) {
        $("#verifyImage").attr("src",
        function() {
            var timesamp = new Date().getTime();
            return "<s:url value='/login/verifycode.action'/>?timesamp=" + timesamp;
        });

        $("#verifycode").formValidator().inputValidator({
            min: 4,
            max: 4,
            onerror: "您输入的验证码无效,请确认"
        }).regexValidator({
            regexp: "username",
            datatype: "enum",
            onerror: "验证码格式不正确"
        });

        $("#verifycode").attr("autocomplete", "off");

        $("#verifyImage").click(function() {
            var timesamp = new Date().getTime();
            document.getElementById("verifyImage").src = "<s:url value='/login/verifycode.action'/>?timesamp=" + timesamp;
        });
    }
    $("#mail").formValidator({
        onfocus: "例如156468495@qq.com"
    }).regexValidator({
        regexp: "email",
        datatype: "enum",
        onerror: "邮箱格式不正确"
    });
    $("#mobile").formValidator({
        onfocus: "例如:18711153105"
    }).regexValidator({
        regexp: "mobile",
        datatype: "enum",
        onerror: "手机号码格式不正确"
    });
    
    $('#email').focus();
    
    var msg = $('#msg').val();
    if(msg != null && msg.length > 0) {
    	alert(msg);
    	$('#msg').val('');
    	window.location.href = "person.action?loginUser=" + $('#card').val();
    }
}); 
</script>
</head>
<body>
	<div style="text-align: center;">
		<s:form action="pwdRetrieveByRegister" namespace="/login" method="post"
			id="Form1" autocomplete="off" theme="simple">
			<table class="tableFrame" style="width: 50%; margin: auto;"
				align="center">
				<thead>
					<tr>
						<th colspan="2">找回密码</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="tdLabel">身份证号码</td>
						<td class="tdInput"><s:property value="idcard" /></td>
						<s:hidden name="idcard" id="card"></s:hidden>
					</tr>
					<td class="tdLabel">姓名</td>
					<td class="tdInput"><s:property value="name" /></td>
					<s:hidden name="name" id="name"></s:hidden>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 电子邮箱：
						</td>
						<td class="tdInput"><s:textfield name="email" id="mail"
								maxlength="50" cssStyle="width: 90%" />
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 手机号码：
						</td>
						<td class="tdInput"><s:textfield id="mobile"
								cssStyle="ime-mode: disabled; width: 90%" name="mobile_phone"
								maxlength="11" /></td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 验证码：</td>
						<td class="tdInput"><input type="text" size="4" maxlength="4"
							name="verifycode" id="verifycode" value="" class="textinput"
							style="width: 40px" title="请填入右图中的数字"
							style="vertical-align:middle;" /> <img
							name="verifyImage" id="verifyImage" alt="" title="点击图片，刷新验证码"
							style="cursor: pointer; vertical-align: middle;" />
						</td>
					</tr>
					<tr>
						<td class="tdLabel"></td>
						<td style="color: blue; text-align: left" align="left">
							请您真实地录入注册时的各项信息，我们将在校验通过后重置您的登录密码。</td>
					</tr>
				</tbody>
			</table>
			<div class="divButton">
				<s:submit id="btnSave" value="提 交" align="center" cssClass="button" />
				<s:hidden id="msg" name="msg"></s:hidden>
			</div>
		</s:form>
	</div>
	<powersi:errors />
</body>
</html>