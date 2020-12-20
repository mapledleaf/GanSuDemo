<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("akb020", BizHelper.getAkb020());
	request.setAttribute("pkm005", BizHelper.getLoginUser());
	request.setAttribute("pkm006", BizHelper.getUserName());
	request.setAttribute("aac001", request.getParameter("aac001"));
	request.setAttribute("aac002", request.getParameter("aac002"));
	request.setAttribute("aac003", URLDecoder.decode(request.getParameter("aac003"), "UTF-8"));
	
	String bpd595 = "#{'2':'左手食指','1':'左手大拇指','3':'左手中指','4':'左手无名指','5':'左手小拇指'," +
					  "'7':'右手食指','6':'右手大拇指','8':'右手中指','9':'右手无名指','10':'右手小拇指'}";
	request.setAttribute("bpd595", bpd595);
%>
<powersi:html>
<powersi:head title="指静脉建模" />
<body>
	<powersi:form id="mainform" namespace="/hosp"
		action="EquipmentAction!modeling.action">
		<powersi:panelbox key="panel_query" title="操作">
			<powersi:panelbox-toolbar>
				<powersi:button id="btnEnable" label="建 模" onclick="fingerVeins()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="6">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac003" name="kc90Dto.aac003" label="姓名" value="${aac003 }"/>
					<powersi:textfield id="aac001" name="kc90Dto.aac001" label="个人编号"   value="${aac001 }" readonly="true"/>
					<powersi:textfield id="aac002" name="kc90Dto.aac002" label="社会保障号" value="${aac002 }" readonly="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bpd562" name="kc90Dto.bpd562" label="身份证" value="${aac002 }"/>
					<powersi:codeselect id="bpd595" label="手指部位"  name="kc90Dto.bpd595"
					 	list="${bpd595 }" />
					 <powersi:codeselect id="bpd598" label="是否代理人"  name="kc90Dto.bpd598"
					 	list="#{'0':'否','1':'是'}" onchange="changeBpd598()"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bpd561" name="kc90Dto.bpd561" label="代理人性别" disabled="true"/>
					<powersi:textfield id="bpd599" name="kc90Dto.bpd599" label="代理人姓名" disabled="true"/>
					<powersi:textfield id="bpd560" name="kc90Dto.bpd560" label="代理人身份证" disabled="true"/>
					
				</powersi:editorlayout-row>
				<powersi:hidden id="bpd596" name="kc90Dto.bpd596" label="生物特征值" />
				<powersi:hidden id="bpd597" name="kc90Dto.bpd597" label="生物特征图像" />
				<powersi:hidden id="bpd594" name="kc90Dto.bpd594" label="生物特征类型" value="2"/>
				<powersi:hidden id="akb020" name="kc90Dto.akb020" label="医疗机构编号" value="${akb020 }"/>
				<powersi:hidden id="pkm005" name="kc90Dto.pkm005" label="采集人编码"   value="${pkm005 }"/>
				<powersi:hidden id="pkm006" name="kc90Dto.bpd596" label="采集人姓名"   value="${pkm006 }"/>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
</body>
</powersi:html>
<script type="text/javascript">
$(function(){
	__isPing();
});

function fingerVeins(){
	if ($("#ic_i_psw").length == 0) {
		$(document.body).append("<div id='ic_i_psw' style='display:none;'></div>");
	}
	var _ic_i_psw_dialog = popupDiv("#ic_i_psw", "正在采集指静脉,请稍后。。。", 300, {
		showToggle : false,
		isDrag : false
	});
	$(".l-dialog #ic_i_psw").parents("tr").hide();
	setTimeout(function() {
		call_agent_service({
			"func_id" : "500000"
		}, function(obj) {
			if(!obj.success_flag){
				_ic_i_psw_dialog.hide();
				alert("请检查设备是否连接正确,错误信息："+obj.error_info);
			}
			$("#bpd596").val(obj.data.feature);
			$("#bpd597").val(obj.data.image);
			_ic_i_psw_dialog.hide();
			modeling();
		});
	}, 100);
}

function modeling() {
	var saveItemData = $("#mainform").serialize();
	postJSON("${rootPath}/hosp/EquipmentAction!modeling.action",
			saveItemData,function(json) {
			if(json.errortype!="0"){
				if (!checkJSONResult(json)) 
					return;
			}
			var bpd591 = json.data;
			if(bpd591=="0"){
				alert("建模成功！");
				closeDialog();
			}else{
				alert("建模失败！");
			}
	});
}

function changeBpd598(){
	var bpd598 = $("#bpd598").val();
	if(bpd598=="1"){
		$("#bpd561").attr("disabled", false);
		$("#bpd599").attr("disabled", false);
		$("#bpd560").attr("disabled", false);
	}else{
		$("#bpd561").attr("disabled", true);
		$("#bpd599").attr("disabled", true);
		$("#bpd560").attr("disabled", true);
	}
}
</script>
