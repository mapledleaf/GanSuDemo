<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="dubbo测试" />
<script type="text/javascript">
	function callChuangZhi(method){
		postAjax(rootPath + "/sample/Sample!callChuangZhi.action", {
			"method": method,
			"text": $("okText1").val()
		}, function(ret){
			if(checkResult(ret)) {
				alert(powersi.tostring(ret.data));
			}
		});
	}
	
	function callYinHai() {
		postAjax(rootPath + "/sample/Sample!callYinHai.action", {
			"text": $('#okText2').val()
		}, function(ret){
			if(checkResult(ret)) {
				alert(powersi.tostring(ret.data));
			}
		});
	}
	
</script>
</head>
<body>
	<powersi:groupbox title="创智测试">
		<input type="text" id="okText1" value="" style="width: 100px;" placeholder="输入文本" />
		<input type="button" value="sayok" class="button" onclick="callChuangZhi('sayok')" />
		<input type="button" value="queryAa10" class="button" onclick="callChuangZhi('queryAa10')" />
		<input type="button" value="queryTexts" class="button" onclick="callChuangZhi('queryTexts')" />
		<input type="button" value="queryCodetable" class="button" onclick="callChuangZhi('queryCodetable')" />
	</powersi:groupbox>
	
	<br />
	
	<powersi:groupbox title="银海测试">
		<input type="text" id="okText2" value="" style="width: 100px;" placeholder="输入文本" />
		<input type="button" value="sayok" class="button"
			onclick="callYinHai()" />
	</powersi:groupbox>
	
	<powersi:errors />
</body>
</powersi:html>