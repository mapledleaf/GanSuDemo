<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	String recvUsers = com.powersi.hygeia.web.util.TextHelper.getText("bulletin_receive_users", "公告接收者");
%> --%>
<powersi:html>
<head>
<powersi:head title="查看公告" target="_self" />
<style type="text/css">
.ellipsis {
	white-space: nowrap;
}
.tr_user_even {
	color: #3B5998;
}
.tr_user_odd {
	color: #3B5998;
}
#tabUser .td_user {
	white-space: nowrap;
	text-align: left;
	border-bottom: dotted 1px #b1b5b8;
}
</style>
</head>
<body>
	<powersi:form id="bulletinForm" action="null" disabled="true">
		<div class="textCenter">
			<table class="tableEditor" align="center">
				<colgroup>
					<col width="15%" />
					<col width="85%" />
				</colgroup>
				<thead>
					<tr>
						<th colspan="2">
							<span>${sysBulletin.bulletinTitle}</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<%-- <td class="tdLabel">
						<c:choose>
							<c:when test="${viewFlag == true}">
								<powersi:button type="button" id="btViewUser" onclick="javascript:doViewUser()" label="<%=recvUsers %>" />
							</c:when>
							<c:otherwise>
								<span><%=recvUsers %></span>
							</c:otherwise>
						</c:choose>
						</td>
						<td class="tdInput"><textarea id="receiveUsers" name="receiveUsers"
								style="width: 99%; border: none; overflow-x: hidden; overflow-y: auto;" 
								readonly="readonly">${sysBulletin.receiveUsers}</textarea></td>
					</tr> --%>
					<tr>
						<td class="tdLabel">
							公告内容
						</td>
						<td class="tdInput"><textarea id="bulletinContent" name="bulletinContent"
								style="width: 99%; height: 240px; border: none; overflow-x: hidden;"
								readonly="readonly">${sysBulletin.bulletinContent}</textarea></td>
					</tr>
					<tr>
						<td class="tdLabel">
							公告附件
						</td>
						<td class="tdInput">
							<s:if test="#request.fileList.size > 0">
	                             <s:iterator value="#request.fileList">
	                             	<div><a class="filename"   
	           onClick="javascript:location.href='${rootPath}/message/BulletinManagerAction!getFile.action?storeid='+encodeURI(encodeURI('<s:property value="file_desc"/>'))+'&bid=<s:property value="bulletin_id"/>&fid=<s:property value="file_id"/>' " >   
<s:property value="file_name"/></a>
	                            <%--  href="${rootPath}/message/BulletinManagerAction!getFile.action?bid=<s:property value="bulletin_id"/>&storeid=<s:property value="file_desc"/>&fid=<s:property value="file_id"/>" --%>		
	                             	</div>
	                             </s:iterator>
                        	</s:if>
						</td>
					</tr>
					<%-- <tr style="height: 50px">
						<td class="tdLabel" id="tdReplyColumn">
							公告回执
						</td>
						<td class="tdInput" id="tdRelpy">
							<c:choose>
							   <c:when test="${viewFlag == 'true'}">
							   	<div>
							   		<powersi:radio id="replyFlag" codeType="bulletin_reply_flag" disabled="true" name="replyFlag" />
							   	</div>
							   </c:when>
							   <c:otherwise>
							   	<c:if test="${sysBulletin.replyFlag == '1'}">
									<input type="checkbox" id="replyCheck" value="1" class="checkbox"/><label for="replyCheck">回执确认</label>
								</c:if>
								<c:if test="${sysBulletin.replyFlag == '2'}">
									<powersi:textarea id="replyContent" name="replyContent" cssStyle="height:40px;" rows="3" required="true" maxlength="500"></powersi:textarea>
								</c:if>
							   </c:otherwise>
							</c:choose>
						</td>
					</tr> --%>
				</tbody>
			</table>
			<div style="color: #00f; text-align: right; width:98%;">
				${sysBulletin.senderMan} ${sysBulletin.bulletinDate}
			</div>
			<div class="divButton">
				<%-- <c:if test="${viewFlag != 'true' && sysBulletin.auditFlag == '1' && sysBulletin.secondAuditFlag == '1'  }">
					<c:if test="${sysBulletin.replyFlag == '1' || sysBulletin.replyFlag == '2'}">
						<powersi:button type="button" id="btSaveReply" key="button_ok" onclick="javascript:doSaveReply()" value="保存回执" />
					</c:if>
				</c:if> --%>
				<powersi:button type="button" id="btClose" key="button_close" onclick="closeDialog()"></powersi:button>
				<input type="hidden" id="bulletinId" name="bulletinId" value="${sysBulletin.bulletinId }">
			</div>
		</div>
	</powersi:form>
	<c:if test="${viewFlag == true}">
		<div id="divUser" style="display: none; overflow: hidden;">
			<div style="color: #ff5200;text-align: center;font-size: 14px;font-weight:bold;padding-bottom:5px;border-bottom:#dedede 1px solid;">
	        <%-- 	<%=recvUsers %> --%>
	        </div>
			<div style="height: 350px; overflow: auto; margin-top:5px;">
				<table class="tablePanel" id="tabUser">
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="row" style="text-align: center;padding-top:10px;border-top:#dedede 1px solid;">
				<div style="width:120px;position: absolute;left:10px;">
					<div id="divUserFilter"></div>
				</div>
				<powersi:button id="btUserClose" key="button_close" onclick="doUserClose();" />
			</div>
		</div>
		
		<script src="${rootPath}/resource/js/jquery.quickfilter.js" type="text/javascript"></script>
		<script type="text/javascript">	
			var _userInit = false;
			function doViewUser(){
				if(_userInit){
					doUserOpen();
				} else {
					postJSON(rootPath + "/message/BulletinEditAction!getReceiveUsers.action?type=0", {
						"bulletinId" : 	$("#bulletinId").val()
					}, viewSucc);
				}
			}
			function viewSucc(json){
				if(!checkJSONResult(json)){
				    return;
			    }
				
				var a = [];
				$.each(json.data, function(n, user){
					a.push("<tr class='");
					a.push(n % 2 == 0 ? 'tr_user_even' : 'tr_user_odd');
					a.push("'><td class='td_user'>");
					a.push(user.receive_man);
					a.push("</td></tr>\n");
				});
				
				$("#tabUser tbody").html(a.join(""));
				
				_userInit = true;
				
				$('#tabUser tbody tr').quickfilter({
		        	srcElement: '#tabUser tbody tr',
	                attached: '#divUserFilter',
	                <%-- inputText: '搜索 <%=recvUsers%>', 
	                labelText: ''--%>
	            });
				
				doUserOpen();
			}
			
			var dlg = null;
			function doUserOpen(){
				if(dlg){
					dlg.show();
				} else{
					<%-- dlg = popupDiv("#divUser", '<%=recvUsers%>', 450); --%>
				}
			}
			
			function doUserClose(){
				dlg.hide();
			}
		</script>
	</c:if>
<script type="text/javascript">
var replyFlag = "${sysBulletin.replyFlag}";
$(document).ready(function(){
	if(!isActivex()){
		$("a.filename").attr("target", "_blank");
	} 
	
	var viewFlag = '${viewFlag}';
	if(viewFlag === 'true'){
		$('#replyFlag' + '${sysBulletin.replyFlag }').prop('checked', true);
	}
	
	var replyDate = "${replyDate}";
	var replyDisabled = "${replyDisabled}";
	var replyContent = "${replyContent}";
	
	if(replyDate != null && replyDate.length > 0){
		$('#tdRelpy').attr("title", "保存回执时间：" + replyDate);
	}
	
	$('#replyContent').val(replyContent);
	
	if(replyDisabled != null && replyDisabled.length > 0){
		disabledReply();
	
		$('#btClose').focus();
	} else {
		if(replyFlag == "1"){
			$('#replyCheck').focus();
		} else if(replyFlag == "2") {
			$('#replyContent').focus();
		}
		
		if(viewFlag != 'true' && replyFlag != '0'){
			$('#tdReplyColumn').css('color', '#f00');
		}
	}
});

function disabledReply(){
	$('#btSaveReply').hide();
	
	if(replyFlag == "1"){
		$('#replyCheck').prop("checked", true).prop("disabled", true);	
	} else if(replyFlag == "2") {
		$('#replyContent').prop("readonly", true).addClass("textReadonly");
	}
	
	$('#tdReplyColumn').css('color', 'inherit');
}
function doSaveReply(){
	var replyInfo = "";
	if(replyFlag == "1"){
		if($('#replyCheck').prop("checked") != true){
			alert("请您勾选回执确认");
			$('#replyCheck').focus();
			return false;
		}
	} else if(replyFlag == "2"){
		replyInfo = $("#replyContent").val();
		if(replyInfo.length == 0){
			alert("请输入回执意见");
			$('#replyContent').focus();
			return false;
		}
		
		if(replyInfo.length > 500){
			alert("您输入的回执意见有" + replyInfo.length + "个字，请保留到500个字以内");
			$('#replyContent').focus();
			return false;
		}
	} else {
		return false;
	}
	
	if (!confirm("您确认保存回执吗?")) {
		return false;
	}
	
	postJSON(rootPath + "/message/BulletinEditAction!addReplyInfo.action", {
		"bulletinId" : 	$("#bulletinId").val(),
		"tipFlag": replyFlag,
		"replyContent": replyInfo
	}, saveSucc);
}

function saveSucc(json){
	if(!checkJSONResult(json)){
	    return;
    }

    alert(json.message);
    
    disabledReply();
}

function getFile(){
	alert(bid);
	alert(storeid);
	alert(fid);
}
</script>
</body>
</powersi:html>