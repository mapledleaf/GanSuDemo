<%@page import="com.powersi.hygeia.framework.BusiContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String userId = BusiContext.getUserInfo().getUserId();
%>
<powersi:html>
<head>
<powersi:head title="消息查看" target="_self"/>
</head>
<body>
	<powersi:editorlayout cssClass="tableEditor" cols="25%,75%">
		<powersi:editorlayout-head title="消息查看"></powersi:editorlayout-head>
		<powersi:editorlayout-row>
			<powersi:editorlayout-label>
				接收人
			</powersi:editorlayout-label>
			<powersi:editorlayout-input>
				${msMap.receive_mans}
			</powersi:editorlayout-input>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:editorlayout-label>
				标 题
			</powersi:editorlayout-label>
			<powersi:editorlayout-input>
				${msMap.ms_title}
			</powersi:editorlayout-input>
		</powersi:editorlayout-row>
		<powersi:editorlayout-row>
			<powersi:editorlayout-label>
				内 容
			</powersi:editorlayout-label>
			<powersi:editorlayout-input>
				<textarea name="tbMessage" class="textarea textReadonly" rows="18" cols="20" id="tbMessage" style="border: none; overflow-x: hidden; width: 99%" readonly="readonly">${msMap.ms_content}</textarea>
			</powersi:editorlayout-input>
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<div class="textInfo textRight">
		${msMap.sender_man} ${msMap.send_date}
	</div>
	
	<div class="divButton">
		<c:if test="${msMap.ms_type != '0'}">
			<powersi:button id="btReply" value="答 复" onclick="javascript:sendMsg('1')" buttonIcon="icon-reply"></powersi:button>
			<powersi:button id="btAllReply" value="全部答复" onclick="javascript:sendMsg('2')" buttonIcon="icon-reply-all"></powersi:button>
		</c:if>
		<powersi:button id="btForward" value="转 发" onclick="javascript:sendMsg('3')" buttonIcon="icon-share-alt"></powersi:button>
		<powersi:button id="btClose" key="button_close" onclick="javascript:closeDialog()"></powersi:button>
	</div>
	<powersi:errors />
<script type="text/javascript">
	var msId = '${msMap.ms_id}';
	function sendMsg(sendType){
		popupDialog(rootPath + "/message/MessageAction!sendMessage.action?init=flag&close=true&type=" + sendType + "&msid=" + msId, 500, 620);
	}
</script>
</body>
</powersi:html>