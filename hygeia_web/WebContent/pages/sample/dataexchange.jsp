<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="数据交换接口" />
<style type="text/css">
.textarea {
	width: 90%;
	border: 1px solid #c5e2f2;
	overflow: visible;
	margin-top: 5px;
	margin-bottom: 5px;
	font-size: 12px;
	font-family: "Microsoft YaHei","SimSun","宋体","Arial Narrow";
	height: 300px
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
		$('#btnSubmit').prop("disabled", true);

		showRunning(true);
		
		//var data = "params=" + param;
		var data = param;
	
		jQuery.ajax({
			type : "POST",
			contentType : "text/xml; charset=UTF-8",
			url : rootPath + "/DataExchange",
			data : data,
			beforeSend : function(xhr) {
				//xhr.setRequestHeader("username", userName);
				//xhr.setRequestHeader("password", pwd);
			},
			dataType : "xml",
			processData : false,
			async : true,
			global : true,
			cache : false,
			error: function(jqXHR, textStatus, errorThrown) {
	        	showRunning(false);
	        	$('#btnSubmit').prop("disabled", false);
	        	
	            var msg = null;
	            if (jqXHR.status != "200") {
	            	if(jqXHR.status == "12007" || jqXHR.status == "12029"){
	            		msg = "服务器连接失败(" + jqXHR.status +  jqXHR.statusText + ").";
	            	} else if(jqXHR.status == "12002") {
	            		msg = "服务器连接超时(" + jqXHR.status +  jqXHR.statusText + ").";
	            	} else if(jqXHR.status == "12152") {
	            		msg = "连接被服务器关闭(" + jqXHR.status +  jqXHR.statusText + ").";
	            	} else {
	            		msg = "错误码:" + jqXHR.status + " 错误文本:" + jqXHR.statusText || "";
	            	}
	            } else {
	            	msg = jqXHR.responseText;
	            }
	            
	            alert(msg);
	        },
	        success: function(json, textStatus, jqXHR) {
	        	showRunning(false);
	        	$('#btnSubmit').prop("disabled", false);
	        	
	        	try{
	        		$('#result').val(jqXHR.responseText);
	        	} catch(callbackError){alert(callbackError.message);}
	        }
		});
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

</powersi:html>