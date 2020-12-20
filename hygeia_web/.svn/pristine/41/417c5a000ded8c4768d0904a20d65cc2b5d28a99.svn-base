<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,java.lang.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.powersi.com.cn/tags" prefix="powersi"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ include file="/pages/util/header.jsp"%>
<link type="text/css" rel="stylesheet"
	href="<%=rootPath%>/resource/js/jqueryvalid/style/validator.css"></link>
<script src="<%=rootPath %>/resource/js/jquery.js"
	type="text/javascript"></script>
<script src="<%=rootPath %>/resource/js/jqueryvalid/formValidator.js"
	type="text/javascript"></script>
<script
	src="<%=rootPath %>/resource/js/jqueryvalid/formValidatorRegex.js"
	type="text/javascript"></script>
<title>完善密保</title>
<style type="text/css">
.tableFrame input{width: 80%;}
.tableFrame select{width: 80%;}
</style>
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
    
    $("#question_1").formValidator().inputValidator({
        min: 1,    
        onerror: "密保问题1不能为空"
    });
    $("#question_2").formValidator().inputValidator({
        min: 1,
        onerror: "密保问题2不能为空"
    });
    $("#question_3").formValidator().inputValidator({
        min: 1,
        onerror: "密保问题3不能为空"
    });
    $("#answer_1").formValidator().inputValidator({
        min: 1,
        max: 200,
        onerror: "答案1长度不能为空并且不超过100字"
    });
    $("#answer_2").formValidator().inputValidator({
        min: 1,
        max: 200,
        onerror: "答案2长度不能为空并且不超过100字"
    });
    $("#answer_3").formValidator().inputValidator({
        min: 1,
        max: 200,
        onerror: "答案3长度不能为空并且不超过100字"
    });
    $("#password").formValidator().inputValidator({
        min: 1,
        max: 100,
        onerror: "完善密保问题必须输入登录密码"
    });
}); 
</script>
</head>
<body>
	<div style="text-align: center;">
		<s:form action="saveRetrieveQuestion" namespace="/user" method="post"
			id="Form1" autocomplete="off" theme="simple">
			<table class="tableFrame">
				<thead>
					<tr>
						<th colspan="2">完善用户密保问题</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<s:if test="#request.dto.idcard != null">
							<td class="tdLabel">身份证号码</td>
							<td class="tdInput">${dto.idcard}</td>
						</s:if>
						<s:hidden name="dto.person_id" value="%{dto.person_id}" id="card"></s:hidden>
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
						<td class="tdLabel"><font color='#FF0000'> * </font>登录密码：</td>
						<td class="tdInput"><s:password id="password" name="password"></s:password>
						</td>
					</tr>
					<tr>
						<td class="tdLabel"></td>
						<td style="color: blue; text-align: left" align="left">
							请您认真准确的填写自己的密保问题,密保问题和答案以后将作为找回密码的依据</td>
					</tr>
				</tbody>
			</table>
			<div class="divButton">
				<s:submit id="btnSave" value="保 存" align="center" cssClass="button" />
			</div>
		</s:form>
	</div>
	<powersi:errors />
</body>
</html>