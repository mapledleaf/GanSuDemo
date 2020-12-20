<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String path = request.getContextPath();
	String aaz267 = request.getParameter("aaz267");
%>
<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 TransitionalEN" "http:www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="打印门特门慢申请信息" />
<script type="text/javascript"
	src="<%=path%>/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/report/js/LodopFuncs.js"></script>
<style type="text/css">
#frame_div {
	margin: 20px 0;
	width: 100%;
	height: auto;
	border: 1px solid #00F;
	overflow: auto;
}
</style>
<script type="text/javascript">
	var powerPrint = null; //打印对象
	var reportID = null;//报表ID
	window.onload = function(){
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!printApplyInfo.action?mediSpecDto.aaz267=<%=aaz267%>","",
				function(json){
					if(!checkJSONResult(json)){
					    return;
				    }
		 			loadHtml(json.data);
				});
	}
	
	function loadHtml(id){
		$("#bizId").val(id);
		$("#reportShow").load("<%=path%>/downloadReportHtmlServlet.download?bizID=" + id,
				function(response, status, xhr) {
					if (status != "success") {
						$("#reportDom").html("加载失败");
						popupInfo("加载失败");
						return;
					}
					powerPrint = $("#reportShow").PowerPrint({
						name : '门特门慢申请单'
					});
				});
	}

	function print() {
		if (powerPrint == null) {
			popupInfo("请先点击加载按钮!");
		}
		powerPrint.preview();
	}
</script>
<body>	
	<powersi:form id="mainForm" name="mainForm">
		<powersi:hidden id="bizId" name="bizId" />
		<powersi:buttons>
			<powersi:button id="printInfo" value="打印申请单" onclick="print()" />
			<powersi:button id="closeWindow" value="关闭" onclick="closeDialog()" />
		</powersi:buttons>
		<powersi:groupbox title="门特门慢申请信息">
			<div id="frame_div">
				<div id="reportShow" style="margin-left: 2%; margin-right: -10%;"></div>
			</div>
		</powersi:groupbox>
	</powersi:form>
</body>
</powersi:html>