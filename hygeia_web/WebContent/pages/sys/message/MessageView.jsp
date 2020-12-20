<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
	<powersi:head title="消息查看" />
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
		<powersi:button id="btBack" key="button_return" onclick="javascript:history.back(1)"></powersi:button>
	</div>
	<powersi:errors />
</body>
</powersi:html>