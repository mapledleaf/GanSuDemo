<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="统一门户同步" />
<script type="text/javascript">
	function doSync(syncType, sybcText) {
		if (!popupConfirm("您确认执行" + sybcText + "吗?")) {
			return;
		}

		postJSON(rootPath + "/manager/DeptManager!syncUifiedPortal.action", {
			"type" : syncType
		}, syncSuccess);
	}
	function syncSuccess(json) {
		if (!checkJSONResult(json)) {
			return;
		}

		alert(json.message);
	}
</script>
</head>
<body>
	<powersi:groupbox title="统一门户同步操作">
		<div style="text-align: center; padding: 20px;">
			<input type="button" class="button" value="全部完整同步"
				onclick="doSync('all', this.value)" /> <input type="button"
				class="button" value="部门完整同步" onclick="doSync('dept', this.value)" />
			<input type="button" class="button" value="用户完整同步"
				onclick="doSync('user', this.value)" />
		</div>
	</powersi:groupbox>

	<powersi:errors />
</body>
</powersi:html>