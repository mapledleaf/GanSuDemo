<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String reportId = (String)request.getAttribute("reportId");
%>
<powersi:html>
<head>
<powersi:head title="打印导出登记表" target="_self" />
<script type="text/javascript" src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript" src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
</head>
<body>
	<powersi:form id="queryForm">
		<powersi:panelbox key="操作">
			<powersi:panelbox-toolbar>
				<powersi:button id="printout_button" label="打印" onclick="printsettlementreport()" />
				<powersi:hidden id="reportId" value="<%=reportId %>" />	
			</powersi:panelbox-toolbar>
			
		</powersi:panelbox>
	</powersi:form>
	 <powersi:groupbox title="打印登记表">
			<div id="frame_div" >
				<div id="applyReportdivid" style="margin-left: 5%;"></div>
			</div>
		</powersi:groupbox>
	<powersi:errors />
	<script type="text/javascript">
		var powerPrint = null; //打印对象
		var reportID = null;//报表ID
	 	$(document).ready(function() {
	 		print();
			}); 
	 
		function loadHtml(){			
			if (powersi.isnull(reportID)) {
				return;
			}
			$("#applyReportdivid").load(
						"${rootPath}/downloadReportHtmlServlet.download?reportID="+ reportID, function(response, status, xhr) {				
							if (status != "success") {
								$("#applyReportdivid").html("加载失败");
								return;
							}								
							powerPrint = $("#applyReportdivid").PowerPrint({
								name : '珠海市医疗保险门诊特定病种申报认定登记表'
							});
							
						});							
			}
		function print() {
			reportID = $("#reportId").val();
			if (powersi.isnull(reportID)) {
				return;
			}
			loadHtml();
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