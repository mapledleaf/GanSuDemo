<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="添加回执" target="_self" />
<style type="text/css">
.delbutton{
	margin-left: 2px;
	margin-right: 2px;
}
</style>
<script type="text/javascript">
	$(function(){
		var replyDate = "${replyDate }";
		var replyDisabled = "${replyDisabled}";
		
		if(replyDate != null && replyDate.length > 0){
			$('#replyTip').html(replyDate);
		}
		
		if(replyDisabled != null && replyDisabled.length > 0){
			$('#btReply').hide();
			$('#replyContent').val("${replyContent}").prop("readonly", true).addClass("textReadonly");
			
			$('#btClose').focus();
		} else {
			$('#replyContent').focus();
		}
	});
	
	function replyBul(){
		var saveItemData = $("#replyForm").serialize();
		
		var tmp = powersi.val("replyContent");
		var len = tmp.length;
		if (len == 0) {
			alert("请输入回执内容!");
			powersi.focus("replyContent");
			return false;
		}
		
		if(len > 500){
			alert("回执内容过长(" + len + "个)，最多只支持500个!");
			powersi.focus("replyContent");
			return false;
		}
		
		if (!confirm("您确认保存回执吗?")) {
			powersi.focus("replyContent");
			return false;
		}
		
		postJSON(rootPath + "/message/BulletinEditAction!addReplyInfo.action" , saveItemData, saveSuccess);
	}
	
	function saveSuccess(json){
		if(!checkJSONResult(json)){
		    return;
	    }
		
		alert(json.message);
		closeWin();
	}
	
	function closeWin() {
		window.close();
	}
</script>
</head>
<body>
<powersi:form id="replyForm" method="post" namespace="/message" action="BulletinEditAction!save">
	<table class="tableEditor">
		<colgroup>
			<col width="18%" />
			<col width="32%" />
			<col width="20%" />
			<col width="30%" />
		</colgroup>
		<tr style="padding-top: 50px">
			<td class="tdLabel"><label for="bulletinContent" class="label"><span class="required">*</span>回执意见</label></td>
			<td class="tdInput" colspan="3">
				<powersi:textarea id="replyContent" name="replyContent" 
					cssStyle="height:200px;" required="true"></powersi:textarea>
				<input type="hidden" value="${bulletinId}" name="bulletinId"/>
				<input type="hidden" value="2" name="tipFlag"/>
			</td>
		</tr>
	</table>
	<div style="color:blue;text-align: right;" id="replyTip"></div>
	<div class="divButton">
		<input type="button" value="保 存" class="button" id="btReply"
			name="btReply" onclick="replyBul()" />
		<input type="button" value="关 闭" class="button" id="btClose"
			name="btClose" onclick="closeWin()" />
	</div>
</powersi:form>
<powersi:errors />
</body>
</powersi:html>