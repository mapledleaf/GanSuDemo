<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script type="text/javascript" src="<%=path%>/resource/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/md5.js"></script>
<title>SOAP接口</title>
<style type="text/css">
.body {
	padding: 20px;
	FONT: 12px/16px Tahoma, Arial, sans-serif;
}

.center {
	text-align: center;
}

.textarea {
	width: 850px;
	border: 1px solid #c5e2f2;
	overflow: visible;
	margin-top: 10px;
	margin-bottom: 10px;
}

.button {
	VERTICAL-ALIGN: middle;
	BORDER-BOTTOM: #c5e2f2 1px solid;
	BORDER-LEFT: #c5e2f2 1px solid;
	WIDTH: 90px;
	MARGIN-BOTTOM: 5px;
	BACKGROUND: #cde4f2;
	HEIGHT: 30px;
	MARGIN-LEFT: 5px;
	BORDER-TOP: #c5e2f2 1px solid;
	CURSOR: pointer;
	BORDER-RIGHT: #c5e2f2 1px solid;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#param').val("");
		$('#result').val("");

		//$('#username').focus();
	});

	function submitData() {
		/*
		var userName = $('#username').val();
		if (userName.length == 0) {
			alert('请输入用户名！');

			$('#username').focus();
			return;
		}

		var pwd = $('#password').val();
		if (pwd.length == 0) {
			alert('请输入密码！');

			$('#password').focus();
			return;
		}
		pwd = "{md5}" + hex_md5(pwd).toUpperCase();
		*/
		
		var param = $('#param').val();
		if (param.length == 0) {
			alert('请输入请求参数！');

			$('#param').focus();
			return;
		}

		$('#result').val("");

		//var data = "params=" + param;
		var data = param;
		
		var err = null;

		var result = jQuery.ajax( {
					type : "POST",
					contentType : "text/xml; charset=UTF-8",
					url : "<%=path%>/soap",
					data : data,
					beforeSend: function(xhr){
            			//xhr.setRequestHeader("username", userName);
            			//xhr.setRequestHeader("password", pwd);
        			},
					dataType : "xml",
					processData : false,
					async : false,
					global : false,
					cache : false,
					error : function(xhr, st, e) {
						if (xhr.status != "200") {
							err = "错误码:" + xhr.status + " 错误文本:"
									+ xhr.statusText || "";
						} else {
							err = xhr.responseText;
						}
						alert("发送请求异常：" + err);
					}
				}).responseText;

		$('#result').val(result);
	}
</script>
</head>

<body>
	<div align="center">
	    <div>
	        <textarea id="param" name="param" class="textarea" cols="20" rows="20"
	        title="输入参数">
	        </textarea>
	    </div>
	    <div>
	        <input class="button" id="btnSubmit" type="button" value="提交数据" onclick="submitData()" />
	    </div>
	    <div>
	        <textarea id="result" name="result" class="textarea" cols="20" rows="20"
	        title="输出参数">
	        </textarea>
	    </div>
    </div>
</body>

</html>