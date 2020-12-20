<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page
	import="com.powersi.hygeia.framework.UserInfo,com.powersi.hygeia.framework.BusiContext,com.powersi.hygeia.framework.CodetableMapping,com.powersi.hygeia.framework.ParameterMapping,com.powersi.hygeia.framework.util.UtilFunc"%>
<%
	String closeFlag = (String)request.getAttribute("close");
	String disabeldUserKind = (String)request.getAttribute("disabeldUserKind");
	UserInfo user = BusiContext.getUserInfo();
	String deptId = "0";
	if (Integer.parseInt(UtilFunc.getString(user, "grade_id", "0")) < 4) {
		deptId = UtilFunc.getString(user, "dept_id", "-1");
	}
	String deptName = "";
	if("0".equals(deptId)){
		deptName = ParameterMapping.getStringByCode(
				"center_org_name", ParameterMapping.getSystemName());
	} else {
		deptName = CodetableMapping.getDisplayValue("sys_dept", deptId);
	}
%>
<powersi:html>
<head>
<powersi:head title="编辑公告" target="_self" />
<style type="text/css">
.delbutton{
	margin-left: 2px;
	margin-right: 2px;
}
</style>
</head>
<body>
	<powersi:form id="bulletinForm" method="post" namespace="/message" action="BulletinEditAction!save" 
		enctype="multipart/form-data" focusElement="bulletinTitle">
		<div id="main" class="scroll-auto">
			<powersi:editorlayout cols="4">
				<powersi:editorlayout-row>
					<powersi:date id="datetimeRange" startField="effectDate" endField="expireDate" minDate="moment()" opens="left"
							 mask="datetime"  label="有效期限" required="true" colspan="3">
					</powersi:date>
					<powersi:hidden id="effectDate" key="effect_date"
						name="sysBulletin.effectDate" maxlength="20" required="true" mask="datetime" />
					<powersi:hidden id="expireDate" key="expire_date"
						name="sysBulletin.expireDate" maxlength="20" required="true" mask="datetime" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row class="hidden">
					<powersi:radio id="bulletinType" name="sysBulletin.bulletinType" key="bulletin_type" 
						codeType="bulletin_type" required="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="disOrder" key="dis_order"  name="sysBulletin.disOrder" maxlength="6"
							required="true" validate="integer" />
					<powersi:radio id="sysBulletinuserKind" codeType="bulletin_user_kind" name="sysBulletin.userKind" required="true" key="bulletin_user_kind" />
				</powersi:editorlayout-row>
				 <powersi:editorlayout-row>
					<powersi:hidden codeType="bulletin_reply_flag" name="sysBulletin.replyFlag"  key="reply_flag" required="true" colspan="3"  />
				</powersi:editorlayout-row> 
				<powersi:editorlayout-row>
					<powersi:codeselect key="bulletin_receive_users" codeType="aaa027" name="sysBulletin.receiveUsers" id="receiveUsers" colspan="3" required="true" readonly="true" buttonId="btnSelectUp"  /><!-- onbuttonclick="selReceives()" -->
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bulletinTitle" name="sysBulletin.bulletinTitle"
							maxlength="100" key="bulletin_title" colspan="3" required="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<td class="tdLabel"><powersi:label for="bulletinContent" required="true" key="bulletin_content" /></td>
					<td class="tdInput" colspan="3"><powersi:textarea
							id="bulletinContent" name="sysBulletin.bulletinContent" 
							cssStyle="height:240px;" required="true" maxlength="1000"></powersi:textarea>
					</td>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<td class="tdLabel"><powersi:label for="bulletinContent" value="公告附件" /></td>
					<td class="tdInput" colspan="3">
						<div id="attachDivBody">
							<s:if test="#request.fileList.size > 0">
	                             <s:iterator value="#request.fileList">
	                             	<div>
	                             		[<a class="delbutton" href="javascript:void(0);" onclick="delFile('<s:property value="file_id"/>')">删除</a>]
	                             		<a  onClick="javascript:location.href='${rootPath}/message/BulletinManagerAction!getFile.action?storeid='+encodeURI(encodeURI('<s:property value="file_desc"/>'))+'&bid=<s:property value="bulletin_id"/>&fid=<s:property value="file_id"/>' " class="filename" id="file_<s:property value="file_id"/>"><s:property value="file_name"/></a>
	                             	</div>
	                             </s:iterator>
	                        </s:if>
						</div>
						<span style="color: #004080;">提示：最多可以添加5个附件，每个附件最大为10M。</span> 
					</td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<powersi:buttons>
				<c:choose>
					<c:when test="${sysBulletin.auditType == '1' }">
						<c:if test="${sysBulletin.auditFlag != '1' }">
							<powersi:button id="btAudit" onclick="doAudit(true)" value="审核通过" cssClass="btn btn-success" buttonIcon="icon-check" />
							<powersi:button id="btNotPass" onclick="doAudit(false)" value="审核不通过" cssClass="btn btn-danger" buttonIcon="icon-ban-circle" />
							<powersi:button id="btSave" onclick="doSave()" value="保存修改" cssClass="btn btn-info" buttonIcon="icon-ok" />
							<s:hidden id="auditOpinion" name="sysBulletin.auditOpinion"></s:hidden>
						</c:if>
					</c:when>
					<%-- <c:when test="${sysBulletin.auditType == '2' }">
						<c:if test="${sysBulletin.secondAuditFlag != '1' }">
							<powersi:button id="btAudit" onclick="doAudit(true)" value="审签通过" cssClass="btn btn-success" buttonIcon="icon-check" />
							<powersi:button id="btNotPass" onclick="doAudit(false)" value="审签不通过" cssClass="btn btn-danger" buttonIcon="icon-ban-circle" />
							<powersi:button id="btSave" onclick="doSave()" value="保存修改" cssClass="btn btn-info" buttonIcon="icon-ok" />
							<s:hidden id="auditOpinion" name="sysBulletin.secondAuditOpinion"></s:hidden>
						</c:if>
					</c:when> --%>
					<c:otherwise>
						<powersi:button id="btSave" key="button_save" onclick="doSave()" />
					</c:otherwise>
				</c:choose>
				
				<powersi:button id="btClose" key="button_close" onclick="closeWin()" />
				
				<powersi:hidden id="receiveString" name="sysBulletin.receiveString" />
				<s:hidden id="bulletinId" name="sysBulletin.bulletinId"></s:hidden>
				<s:hidden id="fileBatch" name="sysBulletin.fileBatch"></s:hidden>
				<s:hidden id="auditType" name="sysBulletin.auditType"></s:hidden>
				<s:hidden id="delFiles" name="delFiles"></s:hidden>
			</powersi:buttons>
		</div>
	</powersi:form>
	<!-- 隐藏区 -->
	<div class="hidden">
		<div id="auditDlg">
			<div class="textLeft"><span class="textInfo"><i class="icon-info-sign"></i> 请填写审批意见</span></div>
			<div style="margin: 5px 0 15px 0;">
				<powersi:textarea id="opinion" name="opinion" rows="5" maxlength="500"></powersi:textarea>
			</div>
			<div class="textRight">
				<powersi:hidden id="auditFlag" name="auditFlag" />
				<powersi:button id="btSaveAudit" onclick="saveAudit()" value="保存" />
				<powersi:button id="btCloseDlg" onclick="closeDlg()" key="button_close" />
			</div>
		</div>
	</div>
	<powersi:errors />
<script type="text/javascript">
	var _curItemData = "";
	var auditType = $('#auditType').val();
	var auditName = '';
	var userKind = '';
	$(document).ready(function(){
		$('#main').height(getPageHeight() - 50);
		
		if(!isPopupDialog()){
			$('#btClose').html('<i class="icon-undo"></i>重 置');
		}
		
		var radio = $('input[name="sysBulletin.userKind"]').filter(':checked');
		if(radio.length > 0){
			userKind = radio.val();
		}
		if(($('#bulletinId').val() || '') == '0'){
			var title = '新增公告';
			
			if(userKind == "2"){
				title += "(单位)";
				$("#receiveString").val('[{"center_id": "0", "receive_org": "0", "receive_staff": "0", "receive_man": "所有参保单位"}]');
				$("#receiveUsers").val("所有参保单位");
			} else if(userKind == "3"){
				title += "(医院)";
				$("#receiveString").val('[{"center_id": "0", "receive_org": "0", "receive_staff": "1,2,3", "receive_man": "所有医疗机构"}]');
				$("#receiveUsers").val("所有医疗机构");
			} else if(userKind == "9"){
				title += "(中心)";
				$("#receiveString").val('[{"center_id": "0", "receive_org": "<%=deptId%>", "receive_staff": "0", "receive_man": "<%=deptName%>"}]');
				$("#receiveUsers").val("<%=deptName%>");
			} else if(userKind == "0") {
				title += "(公共)";
				$("#receiveString").val('[{"center_id": "0", "receive_org": "0", "receive_staff": "0", "receive_man": "所有用户"}]');
				$("#receiveUsers").val("所有用户");
			}
			
			document.title = title;
		} else {
			if(auditType == '1'){
				document.title = '审核公告';
				auditName = '审核';
			} else if(auditType == '2') {
				document.title = '审签公告';
				auditName = '审签';
			} else{
				auditType = '0';
				document.title = '编辑公告';	
			}
		}
		
		//公共公告隐藏接收人选择按钮
		if(userKind == "0") {
			$('#receiveUsers').prop('readonly', false).removeClass('textReadonly').css({'border-width': '1px'}).next().hide();
		}
		
		if("<%= disabeldUserKind %>" === "true"){
			$("input[name='sysBulletin.userKind']:radio").each(function(){
				if($(this).prop("checked") == false){
					$(this).prop("disabled", true).hide().next().hide();
				} else {
					//$(this).next().css("color", "#00f");
				}
			});
		}
		
		if("<%=closeFlag%>" === "true"){
			var bulletinId = "${bulletinId}";
			if(powersi.length(bulletinId) > 0){
				if(!isPopupDialog()){
					alert('保存成功');
				}
				closeWin(bulletinId);
			} else {
				closeWin(true);
			}
		} else {
			if(document.all && !!window.ActiveXObject && !window.XMLHttpRequest){
				$("#bulletinContent").height(200);
			}
			
			_curItemData = $("#bulletinForm").serialize();
			
			if(!isActivex()){
				$("a.filename").attr("target", "_blank");
			}
			
			addAttach();
		}
	});
	
	function checkFileChange(){
		if($('#delFiles').val().length > 0){
			return true;
		}
		var chg = false;
		$('input:file').each(function(){
			if($(this).val().length > 0){
				chg = true;
				return false;
			}
		});
		
		return chg;
	}
	
	function doSave(){
		var saveItemData = $("#bulletinForm").serialize();
		
        if (saveItemData == _curItemData && checkFileChange() == false) {
        	alert("没有修改，不需保存");
        	return;
        }
        
        var radio = $('input[name="sysBulletin.userKind"]').filter(':checked');
		if(radio.length == 0){
			alert("选择人员类型不能为空");
			return;
		}
		
		/* var replyFlag = $('input[name="sysBulletin.replyFlag"]').filter(':checked');
		if(replyFlag.length == 0){
			alert("回执不能为空");
			return;
		} */
		
		
		if (!powersi.check("receiveUsers", "str")) {
			alert("请选择接收者");
			powersi.focus("receiveUsers");
			return false;
		}
        
        if(powersi.val("expireDate").length > 0){
        	if (powersi.val("effectDate") > powersi.val("expireDate")) {
    			alert("失效时间不能小于生效时间");
    			powersi.focus("expireDate");
    			return false;
    		}	
        }
		
		var tmp = powersi.val("bulletinTitle");
		if (tmp.length == 0) {
			alert("请输入公告标题。");
			powersi.focus("bulletinTitle");
			return false;
		}
		
		if (tmp.length > 100) {
			alert("公告标题过长(不能超过100个字符)。");
			powersi.focus("bulletinTitle");
			return false;
		}
		
		if (!powersi.check("bulletinContent", "str")) {
			alert("请输入公告内容。");
			powersi.focus("bulletinContent");
			return false;
		}
		
		if(powersi.obj("bulletinType1").checked){
			var s = powersi.val("bulletinContent");
			if(s.length < 8 || (s.substring(0, 7) != "http://" && s.substring(0, 8) != "https://")){
				alert("外部链接的公告内容必须是有效的http链接地址");
				return false;
			}
		}
		
		if (!confirm("您确认保存公告信息吗?")) {
			powersi.focus("bulletinContent");
			return false;
		}
		
		//postJSON("SaveBulletinInfo.action", saveItemData, saveSuccess);
		//return false;
		
	 	return S('bulletinForm').submit();
	}
	
	var dlg = null;
	function doAudit(flag) {
		if(dlg){
			dlg.show();
		} else{
			dlg = popupDiv('#auditDlg', '保存审批意见', 350);
			$('#opinion').val($('#auditOpinion').val());
		}
		
		$('#auditFlag').val(flag ? '1' : '2');
		
		$('#btSaveAudit').html(auditName + (flag ? '通过' : '不通过'));
	}
	
	function closeDlg() {
		if(dlg){
			dlg.hide();
		}
	}
	
	function saveAudit() {
		var bulletinId = $('#bulletinId').val();
  		var bulletinTitle = $('#bulletinTitle').val();
  		var auditFlag = $('#auditFlag').val();
  		var opinion = $('#opinion').val();
  		if(auditFlag == "2" && powersi.length(opinion) == 0){
  			alert(auditName + "不通过必须输入审批意见");
  			return;
  		}
  		
  		if (!confirm("您确认" + auditName + (auditFlag == "2" ? "不通过" : "通过") + "公告[" + bulletinTitle + "]吗?")) {
            return;
        }
  		
  		postJSON(rootPath + "/message/BulletinManagerAction!" + (auditType == "1" ? "audit" : "secondAudit") + ".action", {
	  			"bulletinId": bulletinId,
	  			"auditFlag": auditFlag,
	  			"auditOpinion": opinion }, function(json){
  			if(!checkJSONResult(json)){
			    return;
		    }

		    alert(json.message);
		  
		    closeDlg();
		    
		    setDialogReturn(bulletinId);
		    closeDialog();
  		});
	}
	/*
	function saveSuccess(json){
		if(!checkJSONResult(json)){
		    return;
	    }
		
		alert(json.message);
		
		closeWin(true);
	}
	*/
	function closeWin(flag) {
		if(isPopupDialog()){
			if(flag){
				setDialogReturn(flag);
			}
			
			setTimeout("closeDialog();", 500);
		} else {
			window.location = rootPath + "/message/BulletinEditAction.action?userKind=" + userKind;
		}
	}

	var MaxFileCount = 5;
	var MaxFileSize = 10;
	var aid = 1;
	function checkFile(filename) { 
		if(filename == "") {
			return true;
		}
		
		var filesize = 0;
		try{
			filesize = window.external.getFileSize(filename);
		}catch(ex) {
			return true;
		}
		
		if (filesize < 0) {
			alert(filename + "\r\n文件不存在。");
			return false;
		}
		
		if (filesize > MaxFileSize*1024*1024) {
			alert("附件太大，不能超过" + MaxFileSize + "兆。");
			return false;
		}
		
		return true;
	}
	
	function addAttach() {
		if ($('#attachDivBody > div').length >= MaxFileCount) {
			//powersi.obj('btnAdd').disabled = true;
			return;
		}
		
		var id = aid;
		var a = [];
		a.push('<div id="attachDivFile_' + id + '">');
		a.push('<input type="file" id="attach_' + id + '" name="uploads" class="file" onchange="insertAttach(\'' + id + '\')" />');
		a.push('<span id="delbutton_' + id + '"></span>');
		a.push('<span id="localfile_' + id + '"></span>');
		a.push('</div>'); 
		
		aid ++;
		document.getElementById('attachDivBody').insertAdjacentHTML("beforeEnd", a.join(""));
	}

	function insertAttach(id){
		var path = powersi.obj('attach_' + id).value;
		if(path == '') {
			return false;
		}
		
		if (!checkFile(path)) {
			delAttach(id);
			return;
		}
		
		var localfile = powersi.obj('attach_' + id).value.substr(powersi.obj('attach_' + id).value.replace(/\\/g, '/').lastIndexOf('/') + 1);
		
		var rep = false;
		$(".filename").each(function(){
			if($(this).text() == localfile) {
				rep = true;
				alert('附件已经存在[' + localfile + ']。');
				return false;		
			}
		});
		if (rep) {
			delAttach(id);
			return;
		}
		
		powersi.obj('delbutton_' + id).innerHTML = '[<a class="delbutton" href="javascript:void(0);" onclick="delAttach(' + id + ')">删除</a>]';
		
		powersi.obj('localfile_' + id).innerHTML = localfile;
		powersi.obj('localfile_' + id).className = 'filename';
			
		powersi.obj('attach_' + id).style.display = "none";
		
		addAttach();
		return;
	}

	function delAttach(id){
		powersi.obj('attachDivBody').removeChild(powersi.obj('attach_' + id).parentNode);
		
		if (powersi.obj('attachDivBody').innerHTML == '') {
			addAttach();
		}
		if (powersi.obj('attach_' + (aid - 1)) == null || powersi.obj('attach_' + (aid - 1)).value != '') {
			addAttach();
		}
	}
	
	function delFile(id){
		if (!confirm("您确认删除已经保存的附件吗?")) {
			return;
		}
		
		powersi.val('delFiles', ',' + id + powersi.val('delFiles'));
		powersi.obj('attachDivBody').removeChild(powersi.obj('file_' + id).parentNode);
		
		if (powersi.obj('attachDivBody').innerHTML == '') {
			addAttach();
		}
		
		if (powersi.obj('attach_' + (aid - 1)) == null || powersi.obj('attach_' + (aid - 1)).value != '') {
			addAttach();
		}
	}
	
	function selReceives(){
		var radio = $('input[name="sysBulletin.userKind"]').filter(':checked');
		if(radio.length == 0){
			alert("请先选择用户类别");
			return;
		}
		var type = radio.val();//2是单位，3是医院，9是中心
		
		var name = "";
		if(type == "2"){
			name = "Corp";
		} else if(type == "3"){
			name = "Hosp";
		} else if(type == "9") {
			name = "Center";
		} else{
			return;
		}
		
		var param = {
					"receive_string" : $("#receiveString").val(), 
					"receive_users" : $("#receiveUsers").val()
		};
		var url = rootPath + "/pages/sys/message/BulletinSel" + name + "User.jsp";
		popupDialogWithParam({
  			url: url,
  			showMax: false,
  			onClosed: function(){
  				var retVal = this.returnValue;
  				if(powersi.isvalue(retVal)){
  					$("#receiveString").val(retVal.receive_string || "");
  			  		$("#receiveUsers").val(retVal.receive_users || "");
  				}
  			}
  		}, param);
	}
</script>
</body>
</powersi:html>