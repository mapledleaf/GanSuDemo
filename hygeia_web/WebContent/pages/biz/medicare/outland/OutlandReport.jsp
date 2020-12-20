<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String SummyReport = (String)request.getAttribute("SummyReport");
	String JuMSummyReport = (String)request.getAttribute("JuMSummyReport");
	String ZhiGSummyReport = (String)request.getAttribute("ZhiGSummyReport");
	String JuMDetailReport = (String)request.getAttribute("JuMDetailReport");
	String ZhiGDetailReport = (String)request.getAttribute("ZhiGDetailReport");
%>
<powersi:html>
<head>
<powersi:head title="跨省外来就医月度结算报表" target="_self" />
<script type="text/javascript"
	src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<style type="text/css">
.frame_div {
	margin: 20px 0;
	width: 100%;
	height: auto;
	border: 1px solid #00F;
	overflow: auto;
}
.center {
	margin-left: 10%; 
	/* margin-right: -20%; */
}
</style>
</head>
<body>
	<powersi:form id="declForm">
		<powersi:panelbox key="条件设置">
			<powersi:panelbox-toolbar>
				<powersi:button id="printReport" label="获取月结清分相关报表"  onclick="print()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<tr>
					 <powersi:textfield id="yearMonth" required="true"  name="yearMonth" label="结算所属年月" validate = "integer,max[209912],min[190001],maxSize[6],minSize[6]"
					 	onclick="WdatePicker({dateFmt:'yyyyMM'})" />
					 <td colspan="8"></td>
				</tr>
				<powersi:hidden id="SummyReport" value="<%=SummyReport %>"/>
				<powersi:hidden id="JuMSummyReport" value="<%=JuMSummyReport %>"/>
				<powersi:hidden id="ZhiGSummyReport" value="<%=ZhiGSummyReport %>"/>
				<powersi:hidden id="JuMDetailReport" value="<%=JuMDetailReport %>"/>
				<powersi:hidden id="ZhiGDetailReport" value="<%=ZhiGDetailReport %>"/>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:tabbedpanel id="divTabs" >
		<powersi:tab id="tab1" target="paneltab1" label="医院申报汇总表" />
		<powersi:tab id="tab2" target="paneltab2" label="医院申报汇总表(城乡居民医保)" />
		<powersi:tab id="tab3" target="paneltab3" label="医院申报汇总表(职工医保)" />
		<powersi:tab id="tab4" target="paneltab4" label="医院申报明细表(城乡居民医保)" />
		<powersi:tab id="tab5" target="paneltab5" label="医院申报明细表(职工医保)" />
		<div id="paneltab1" >
			<powersi:panelbox >
			   	 <powersi:panelbox-toolbar>
				 	<powersi:button id="oneid" label="打印汇总表"
						onclick="SummyReport()" />
			     </powersi:panelbox-toolbar>
			     <powersi:groupbox>
					<div id="one_div" class="frame_div" >
						<div id="SummyReportId" class="center"></div>
					</div>
				</powersi:groupbox>
			</powersi:panelbox>
		</div>
		<div id="paneltab2">
			<powersi:panelbox >
			   	 <powersi:panelbox-toolbar>
				 	<powersi:button id="twoid"  label="打印居民汇总表" 
				 		onclick="JuMSummyReport()" />
			     </powersi:panelbox-toolbar>
			     <powersi:groupbox>
					<div id="two_div" class="frame_div">
						<div id="JuMSummyReportId" class="center"></div>
					</div>
				</powersi:groupbox>
			</powersi:panelbox>
		</div>
		<div id="paneltab3">
			<powersi:panelbox >
			   	 <powersi:panelbox-toolbar>
				 	<powersi:button id="threeid"  label="打印职工汇总表" 
				 		onclick="ZhiGSummyReport()" />
			     </powersi:panelbox-toolbar>
			     <powersi:groupbox>
					<div id="three_div" class="frame_div">
						<div id="ZhiGSummyReportId" class="center"></div>
					</div>
				</powersi:groupbox>
			</powersi:panelbox>
		</div>
		<div id="paneltab4">
			<powersi:panelbox >
			   	 <powersi:panelbox-toolbar>
				 	<powersi:button id="fourid"  label="打印居民明细表"
				 		onclick="JuMDetailReport()" />
			     </powersi:panelbox-toolbar>
			     <powersi:groupbox>
					<div id="four_div" class="frame_div">
						<div id="JuMDetailReportId" class="center"></div>
					</div>
				</powersi:groupbox>
			</powersi:panelbox>
		</div>
		<div id="paneltab5">
			<powersi:panelbox >
			   	 <powersi:panelbox-toolbar>
				 	<powersi:button id="fiveid"  label="打印职工明细表" 
				 		onclick="ZhiGDetailReport()" />
			     </powersi:panelbox-toolbar>
			     <powersi:groupbox>
					<div id="five_div" class="frame_div">
						<div id="ZhiGDetailReportId" class="center"></div>
					</div>
				</powersi:groupbox>
			</powersi:panelbox>
		</div>
	</powersi:tabbedpanel>
		
	<powersi:errors />
	<script type="text/javascript">
		var onePowerPrint = null; 
		var twoPowerPrint = null;
		var thrPowerPrint = null;
		var fouPowerPrint = null;
		var fivPowerPrint = null;
		
		function loadHtml() {
			var SummyReport = $("#SummyReport").val();
			var JuMSummyReport = $("#JuMSummyReport").val();
			var ZhiGSummyReport = $("#ZhiGSummyReport").val();
			var JuMDetailReport = $("#JuMDetailReport").val();
			var ZhiGDetailReport = $("#ZhiGDetailReport").val();
			if (!powersi.isnull(SummyReport)) {
				$("#SummyReportId").load(
						"${rootPath}/downloadReportHtmlServlet.download?reportID="
								+ SummyReport, function(response, status, xhr) {
							if (status != "success") {
								$("#SummyReportId").html("加载失败");
								return;
							}
							onePowerPrint = $("#SummyReportId").PowerPrint({
								name : '广东省跨省异地就医月结医院申报确认汇总表(待定稿)'
							});
						});
			}
			if (!powersi.isnull(JuMSummyReport)) {
				$("#JuMSummyReportId").load(
						"${rootPath}/downloadReportHtmlServlet.download?reportID="
								+ JuMSummyReport, function(response, status, xhr) {
							if (status != "success") {
								$("#JuMSummyReportId").html("加载失败");
								return;
							}
							twoPowerPrint = $("#JuMSummyReportId").PowerPrint({
								name : '广东省跨省异地就医月结医院申报表(城乡居民医保)(待定稿)'
							});
						});
			}
			if (!powersi.isnull(ZhiGSummyReport)) {
				$("#ZhiGSummyReportId").load(
						"${rootPath}/downloadReportHtmlServlet.download?reportID="
								+ ZhiGSummyReport, function(response, status, xhr) {
							if (status != "success") {
								$("#ZhiGSummyReportId").html("加载失败");
								return;
							}
							thrPowerPrint = $("#ZhiGSummyReportId").PowerPrint({
								name : '广东省跨省异地就医月结医院申报表(职工医保)(待定稿)'
							});
						});
			}
			if (!powersi.isnull(JuMDetailReport)) {
				$("#JuMDetailReportId").load(
						"${rootPath}/downloadReportHtmlServlet.download?reportID="
								+ JuMDetailReport, function(response, status, xhr) {
							if (status != "success") {
								$("#JuMDetailReportId").html("加载失败");
								return;
							}
							fouPowerPrint = $("#JuMDetailReportId").PowerPrint({
								name : '广东省跨省异地就医医疗机构月结申报明细表(城乡居民医保)(待定稿)'
							});
						});
			}
			if (!powersi.isnull(ZhiGDetailReport)) {
				$("#ZhiGDetailReportId").load(
						"${rootPath}/downloadReportHtmlServlet.download?reportID="
								+ ZhiGDetailReport, function(response, status, xhr) {
							if (status != "success") {
								$("#ZhiGDetailReportId").html("加载失败");
								return;
							}
							fivPowerPrint = $("#ZhiGDetailReportId").PowerPrint({
								name : '广东省跨省异地就医医疗机构月结申报明细表(职工医保)(待定稿)'
							});
						});
			}
		}

		function SummyReport() {
			if (onePowerPrint != null) {
				onePowerPrint.preview();
			}
		}
		function JuMSummyReport() {
			if (twoPowerPrint != null) {
				twoPowerPrint.preview();
			}
		}
		function ZhiGSummyReport() {
			if (thrPowerPrint != null) {
				thrPowerPrint.preview();
			}
		}
		function JuMDetailReport() {
			if (fouPowerPrint != null) {
				fouPowerPrint.preview();
			}
		}
		function ZhiGDetailReport() {
			if (fivPowerPrint != null) {
				fivPowerPrint.preview();
			}
		}
		
		function print(){
			if(!checkFormValidtion()){
		  		return;
			}
			var yearMonth = $("#yearMonth").val();
			postJSON("${rootPath}/medicare/OutDeclAction!printReport.action",{
							"yearMonth":yearMonth
						},function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							if(json.data.SummyReport!=null){
								$("#SummyReport").val(json.data.SummyReport);
							}
							if(json.data.JuMSummyReport!=null){
								$("#JuMSummyReport").val(json.data.JuMSummyReport);
							}
							if(json.data.ZhiGSummyReport!=null){
								$("#ZhiGSummyReport").val(json.data.ZhiGSummyReport);
							}
							if(json.data.JuMDetailReport!=null){
								$("#JuMDetailReport").val(json.data.JuMDetailReport);
							}
							if(json.data.ZhiGDetailReport!=null){
								$("#ZhiGDetailReport").val(json.data.ZhiGDetailReport);
							}
							loadHtml();
						});
		}
	</script>
</body>
</powersi:html>