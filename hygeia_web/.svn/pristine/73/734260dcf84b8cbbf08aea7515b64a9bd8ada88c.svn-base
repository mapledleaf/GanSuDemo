<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("akb020",BizHelper.getAkb020());
%>
<powersi:html>
<head>
<powersi:head title="身份信息校验" target="_self" />
</head>
<body>
	<powersi:form id="identifyForm" namespace="/common" action="CommonManagerAction!identifyCard.action">
	
		<powersi:panelbox key="查询条件" allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button id="btnUpdate" key="重置密码" onclick="changePassword();" />
				<powersi:button id="buttonshow" key="查看照片" onclick="popdiv();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,8%,10%,9%,12%,11%,13%,11%,14%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="diagnoseInfoDTO.arg_name"
						label="查询条件" cssClass="select2"
						list="#{'aac002':'社会保障号'}" />
					<td><powersi:textfield id="querystring"
							name="querystring" title="请输入信息回车" placeholder="请输入身份证"
							readonly="false" /></td>
					
				<powersi:hidden id="imgPhone"  />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>	
		<powersi:hidden id="akb020"  value="${akb020}"/>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
	<script type="text/javascript">	
		var cameraInfo = null;
		//重置密码
		function changePassword(){
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效身份证");
				return;
			}
			if (querystring.length!=15 && querystring.length!=18) {
				alert("身份证长度不对");
				return;
			}
			if (confirm("是否重置密码") == true) {
				takePhoto();
				$("#btnUpdate").attr("disabled", true);
			}
		}		
		
		function popdiv(){
			var divs;
			divs=divs+"<div><img width='300' height='200' src='"+($("#imgPhone").val())+"' /></div>";
			popupDiv(divs, "拍摄图片", 600);
		}
		
	</script>
</body>
</powersi:html>