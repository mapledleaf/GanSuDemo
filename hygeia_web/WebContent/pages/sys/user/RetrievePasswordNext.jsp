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
            $("#btave").removeAttr('disabled');
            return false;
        },
        onsuccess: function() {
            $("#btsave").attr("disabled", 'true');
            //showRunning(true);
            return true;
        }
    });
    $("#card").formValidator({
        onshow: "请输入身份证,如果尾数为'X'请大写",
        onfocus: "身份证长度为15-18位"
    }).inputValidator({
        min: 15,
        max: 18,
        onerror: "身份证长度为15-18位"
    });
    
    $("#name").formValidator({
        onshow: "请输入姓名",
        onfocus: "姓名不能为空,并且长度小于20"
    }).inputValidator({
        min: 1,
        max: 20,
        onerror: "用户名不能为空,并且长度小于20"
    });
    
    $("#card").focus();
}); 
</script>
</head>
<body>
<div style="text-align: center;">
	<s:form action="chkPwdRetrieveState" namespace="/login" method="post" id="Form1" autocomplete="off" theme="simple">
		<table class="tableFrame" style="width: 50%; margin: auto;"
			align="center">
			<thead>
				<tr>
					<th colspan="2">找回密码</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="tdLabel"><font color='#FF0000'> * </font> 身份证号码</td>
					<td class="tdInput"><s:textfield name="idcard" id="card" maxlength="18" cssStyle="width: 90%;"></s:textfield>
				</td>
				<tr>
					<td class="tdLabel"><font color='#FF0000'> * </font> 姓名</td>
					<td class="tdInput"><s:textfield name="name" id="name" maxlength="20" cssStyle="width: 90%"></s:textfield>
				</td>
				</tr>						
			</tbody>
		</table>
		<div class="divButton">
			<s:submit id="btnSave" value="下一步" align="center" cssClass="button" />
		</div>
	</s:form>
</div>
<powersi:errors />
</body>
</html>