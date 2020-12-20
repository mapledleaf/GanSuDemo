<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String reportID = (String)request.getAttribute("reportID");
%>
<powersi:html>
<head>
<powersi:head title="电子处方笺" target="_self" />
<script type="text/javascript"
	src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<style type="text/css">
#frame_div {
	margin: 20px 0;
	width: 100%;
	height: auto;
	border: 1px solid #00F;
	overflow: auto;
}
</style>
</head>
<body>
	<powersi:form id="settlementReportForm">
		<powersi:panelbox key="操作">
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button id="printsettlementreportid" value="打印"
						onclick="printsettlementreport()" />
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout>
				<powersi:editorlayout-row>
					<powersi:hidden id="reportID" value="<%=reportID %>" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<div id="frame_div">
			<div id="electronicReportdivid"
				style="margin-left: 5%;"></div>
		</div>
	
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var powerPrint = null; //打印对象
		var reportID = null;//报表ID
		
		$(document).ready(function() {
			loadHtml();
		});

		function loadHtml() {
			reportID = $("#reportID").val();
			if (powersi.isnull(reportID)) {
				return;
			}
			$("#electronicReportdivid").load(
					"${rootPath}/downloadReportHtmlServlet.download?reportID="
							+ reportID, function(response, status, xhr) {
						if (status != "success") {
							$("#electronicReportdivid").html("加载失败");
							return;
						}
						powerPrint = $("#electronicReportdivid").PowerPrint({
							name : '电子处方笺'
						});
					});
		}

		function printsettlementreport() {
			if (powerPrint == null) {
				return;
			}
			powerPrint.preview();
		}
	</script>
</body>
</powersi:html>