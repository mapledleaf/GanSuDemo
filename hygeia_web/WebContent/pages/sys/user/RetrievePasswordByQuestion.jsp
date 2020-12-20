<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,java.lang.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.powersi.com.cn/tags" prefix="powersi"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ include file="/pages/util/header.jsp"%>
<link type="text/css" rel="stylesheet"
	href="<%=rootPath%>/resource/js/jqueryvalid/style/validator.css"></link>
<script src="<%=rootPath%>/resource/js/jquery.js"
	type="text/javascript"></script>
<script src="<%=rootPath%>/resource/js/jqueryvalid/formValidator.js"
	type="text/javascript"></script>
<script
	src="<%=rootPath%>/resource/js/jqueryvalid/formValidatorRegex.js"
	type="text/javascript"></script>
<title>找回密码</title>
<script type="text/javascript">
    $(document).ready(function() {
        $.formValidator.initConfig({
            formid: "Form1",
            onerror: function(msg) {
                alert(msg);
                $("#btnSave").removeAttr('disabled');
                return false;
            },
            onsuccess: function() {
                $("#btnSave").attr("disabled", 'true');
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
        $("#question_1").formValidator().inputValidator({
            min: 1,
            onerror: "密保问题不能为空"
        });
        $("#question_2").formValidator().inputValidator({
            min: 1,
            onerror: "密保问题不能为空"
        });
        $("#question_3").formValidator().inputValidator({
            min: 1,
            onerror: "密保问题不能为空"
        });
        $("#answer_1").formValidator().inputValidator({
            min: 1,
            max: 200,
            onerror: "答案长度不能为空并且不超过100字"
        });
        $("#answer_2").formValidator().inputValidator({
            min: 1,
            max: 200,
            onerror: "答案长度不能为空并且不超过100字"
        });
        $("#answer_3").formValidator().inputValidator({
            min: 1,
            max: 200,
            onerror: "答案长度不能为空并且不超过100字"
        });
        
        var msg = $('#msg').val();
        if (msg != null && msg.length > 0) {
            alert(msg);
            $('#msg').val('');
            window.location.href = "person.action?loginUser=" + $('#card').val();
        }
    });
</script>
</head>
<body>
	<div style="text-align: center;">
		<s:form action="pwdRetrieveByQuestion" namespace="/login"
			method="post" id="Form1" autocomplete="off" theme="simple">
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
						<td class="tdInput"><s:property value="dto.idcard" /></td>
						<s:hidden name="dto.idcard" id="card"></s:hidden>
					</tr>
					<td class="tdLabel">姓名</td>
					<td class="tdInput"><s:property value="name" /></td>
					<s:hidden name="name" id="name"></s:hidden>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 问题一：</td>
						<td class="tdInput"><powersi:codeselect codeType="question_1"
								name="dto.question_1" id="question_1"></powersi:codeselect>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font>答案一：</td>
						<td class="tdInput"><s:textfield name="dto.answer_1"
								id="answer_1"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 问题二：</td>
						<td class="tdInput"><powersi:codeselect codeType="question_2"
								name="dto.question_2" id="question_2"></powersi:codeselect>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font>答案二：</td>
						<td class="tdInput"><s:textfield name="dto.answer_2"
								id="answer_2"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 问题三：</td>
						<td class="tdInput"><powersi:codeselect codeType="question_3"
								name="dto.question_3" id="question_3"></powersi:codeselect>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font>答案三：</td>
						<td class="tdInput"><s:textfield name="dto.answer_3"
								id="answer_3"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"><font color='#FF0000'> * </font> 验证码：</td>
						<td class="tdInput"><input type="text" size="4" maxlength="4"
							name="verifycode" id="verifycode" value="" class="textinput"
							style="width: 40px" title="请填入右图中的数字"
							style="vertical-align:middle;" /> <img name="verifyImage"
							id="verifyImage" alt="" title="点击图片，刷新验证码"
							style="cursor: pointer; vertical-align: middle;" /></td>
					</tr>
					<tr>
						<td class="tdLabel"></td>
						<td style="color: blue; text-align: left" align="left">
							请您真实地录入密保问题的各项信息，我们将在校验通过后重置您的登录密码。</td>
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