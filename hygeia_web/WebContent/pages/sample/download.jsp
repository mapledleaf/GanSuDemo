<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="下载测试" />
<script type="text/javascript">
	function downloadFile(){
		if(!checkForm()){
			return;
		}
		
		alert(quietDownload($('#downloadUrl').val(), $('#localPath').val(), $('#localFile').val()));
	}
	
	function downloadBatch(){
		if(!checkForm()){
			return;
		}
		
		var beginDate = new Date();
		for(var i = 0; i < 100; i ++){
			quietDownload($('#downloadUrl').val(), $('#localPath').val(), $('#localFile').val());	
		}
		alert(new Date().getTime() - beginDate.getTime());
	}
</script>
</head>
<body>
	<powersi:form disabled="true" namespace="/sample" action="Sample">
		<powersi:groupbox title="测试下载">
			<powersi:editorlayout cols="30%,70%">
				<tr>
					<powersi:textfield id="downloadUrl" name="downloadUrl" label="下载地址"
						required="true" placeholder="输入相对地址或者绝对地址"></powersi:textfield>
				</tr>
				<tr>
					<powersi:textfield id="localPath" name="localPath" label="本地路径"
						required="true" placeholder="输入本地保存目录"></powersi:textfield>
				</tr>
				<tr>
					<powersi:textfield id="localFile" name="localFile" label="本地文件名"
						required="true" placeholder="输入保存文件名"></powersi:textfield>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		<div class="divButton">
			<input type="button" value="测试下载" onclick="downloadFile()" class="button" />
			<input type="button" value="循环下载" onclick="downloadBatch()" class="button" />
		</div>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>