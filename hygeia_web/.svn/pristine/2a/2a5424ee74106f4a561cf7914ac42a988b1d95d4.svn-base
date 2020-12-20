<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="结算单展示" target="_self" />
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
	<powersi:form id="settlementReportForm" namespace="/common" action="SettlementReportAction!settlementReport.action">
		<powersi:panelbox key="发票操作">
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button id="printsettlementreportid" value="打印" onclick="printsettlementreport()" />
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout>
				<powersi:editorlayout-row>
					<powersi:hidden id="aaz217" name="kab3DTO.aaz217" />
					<powersi:hidden id="bka445" name="kab3DTO.bka445" />
					<powersi:hidden id="reportIDfirst" name="reportIDfirst" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:groupbox title="结算单信息">
			<div id="frame_div">
				<div id="settlementReportdivid"
					style="margin-left: 8%;"></div>
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
			$("#settlementReportdivid").load("${rootPath}/downloadReportHtmlServlet.download?reportID=" + reportID, function(response, status, xhr) {
						if (status != "success") {
							$("#settlementReportdivid").html("加载失败");
							return;
						}
						powerPrint = $("#settlementReportdivid").PowerPrint({
							name : '医保结算单'
						});
						
						//powerPrint.setup(true,true,'${rootPath}/resource/images/bill/clincxt.jpg')
						//powerPrint.preview(true,true,'${rootPath}/resource/images/bill/clincxt.jpg');
					});
		
			setTimeout("printsettlementreport()",1000);
		}

		function querysettlementfirst() {
			reportID = $("#reportIDfirst").val();
			if (powersi.isnull(reportID)) {
				return;
			}
			loadHtml();
		}
		//打印设计design
		function printsettlementreport() {
			if (powerPrint == null) {
				return;
			}
			var bka445=$("#bka445").val();
			if("1"== bka445)
				powerPrint.preview(true,true,'${rootPath}/resource/images/bill/clincxt.jpg');
			else
				powerPrint.preview(true,true,'${rootPath}/resource/images/bill/hospitalxt.jpg');
			
			closeDialog();
		}
		
		/**
		 * 打印预览
		 * isTable 是否是表格  该参数可随意
		 * isTaoda 是否为套打
		 * imgUrl 套打背景图片地址
		 */
		/* powerPrint.preview(isTable, isTaoda, imgUrl)
		 
		//打印设置
		powerPrint.setup(isTable, isTaoda, imgUrl)
		 
		//打印设计
		powerPrint.design(isTable, isTaoda, imgUrl)
		 
		//直接打印
		powerPrint.print(isTable, isTaoda, imgUrl) */
	</script>
</body>
</powersi:html>