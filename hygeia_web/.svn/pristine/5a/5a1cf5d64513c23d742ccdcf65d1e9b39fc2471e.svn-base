<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="打印信息展示" target="_self" />
<script type="text/javascript"
	src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<style type="text/css">
#frame_div {
	
	width: 100%;
	height: auto;
	border: 1px solid #00F;
	overflow: auto;
}

</style>
</head>
<body>
	<powersi:form id="settlementReportForm" namespace="/common"
		action="SettlementReportAction!settlementReport.action">
		<powersi:groupbox title="申请单信息">
			<powersi:hidden id="reportIDfirst" name="reportIDfirst" value="${reportIDfirst}"/>
			<powersi:hidden id="reportIDsecond" name="reportIDsecond" />
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button id="printsettlementreportid" value="打印申请单"
						onclick="printsettlementreport()" />
					<powersi:a class="button" href="javascript:void(0)"
						onclick="downloadsettlementreport()">下载申请单</powersi:a>
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<div id="frame_div">
				<div id="settlementReportdivid"
					style="margin-left: 1%; margin-right: -20%; margin-top: 1%;"></div>
			</div>
		</powersi:groupbox>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		var powerPrint = null; //打印对象
		var reportID = null;//报表ID

		$(document).ready(function() {
			querysettlementfirst();
		});

		function loadHtml() {
			if (powersi.isnull(reportID)) {
				return;
			}
			$("#settlementReportdivid").load(
					"${rootPath}/downloadReportHtmlServlet.download?reportID="
							+ reportID, function(response, status, xhr) {
						if (status != "success") {
							$("#settlementReportdivid").html("加载失败");
							return;
						}
						powerPrint = $("#settlementReportdivid").PowerPrint({
							name : '申请单'
						});
					});
		}

		function querysettlementfirst() {
			reportID = $("#reportIDfirst").val();
			if (powersi.isnull(reportID)) {
				return;
			}
			loadHtml();
		}

		function querysettlementsecond() {
			reportID = $("#reportIDsecond").val()
			if (powersi.isnull(reportID)) {
				return;
			}
			loadHtml();
		}

		function downloadsettlementreport() {
			if (powersi.isnull(reportID)) {
				return;
			}
			location.href = "${rootPath}/downloadReportFileServlet.download?reportID="
					+ reportID;
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