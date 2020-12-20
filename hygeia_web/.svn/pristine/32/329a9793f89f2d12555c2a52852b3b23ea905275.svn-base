<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
    String path = request.getContextPath();
	String akb020 = "002001";//(String) request.getAttribute("akb020");
	String aaz217 = "002001160622757467";//(String) request.getAttribute("aaz217");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<powersi:head title="结算单信息查询" />

<script type="text/javascript" src="<%=path%>/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript" src="<%=path%>/resource/report/js/LodopFuncs.js"></script>
<style type="text/css">
	#frame_div{
		margin:20px 0;
		width: 100%;
		height: auto;
		border: 1px solid #00F;
		overflow: auto;
	}
</style>

<script type="text/javascript">
	function dw1_print() {
		preview();
	}
	
	function dw0_exp() {
		downloadfile();
	}
	
	var powerPrint = null; //打印对象
	var reportID = null;//报表ID

	function loadHtml(){
		$("#reportDom").html("正在加载。。。");
		$("#reportDom").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+'MZJSD'+$("#aaz217").val(), function(response,status,xhr){
			if( status != "success" ){
				$("#reportDom").html("加载失败");
				popupError("加载失败");
				return;
			}
			powerPrint = $("#reportDom").PowerPrint({name:'门诊结算单'});
		});
	};
	
	//生成菜单报表
	function createReport(flag) {
		$("#reportDom").html("正在加载。。。");
		$("#reportDom").load("<%=path%>/medicare/settlement!createSettlementReport.action?clinicSettlementBaseDTO.aaz217="+$("#aaz217").val()+"&clinicSettlementBaseDTO.akb020="+$("#akb020").val(), function(response,status,xhr){
			if( status != "success" ){
				$("#reportDom").html("加载失败");
				popupError("加载失败");
				return;
			}
		});
	};

	//打印预览
	function preview() {
		if(powerPrint == null) {
			popupAlert("请先点击加载按钮!");
		}
		powerPrint.preview( );
	}
	
	function downloadfile(){
		location.href = rootPath+"/downloadReportFileServlet.download?bizID="+'MZJSD'+$("#aaz217").val();
	}
	
	$(function(){
		$("#aaz217").val("<%=aaz217%>");
		$("#akb020").val("<%=akb020%>");
		
	 	/* createReport();
		loadHtml();  */
	});

</script>


<body>
	<powersi:form id="mainForm" name="mainForm" action=""
		namespace="/medicare">
		<div align="center">
			<powersi:hidden id="akb020" name="akb020"></powersi:hidden>
			<powersi:hidden id="aaz217" name="aaz217"></powersi:hidden>
			<h3><a href="javascript:createReport();">生成报表</a>&nbsp;&nbsp;<a href="javascript:loadHtml();">点击加载</a></h3>
			<powersi:buttons>
				<powersi:button id="btnPrint" key="button_print" onclick="dw1_print()" />
				<%-- <powersi:button id="btnExport" key="button_export" onclick="dw0_exp()" /> --%>
				<a href="javascript:void(0)" onclick="downloadfile()" class="button">下 载</a>
			</powersi:buttons>
		</div>
		<powersi:groupbox title="结算单信息">
			<div id="frame_div">
				<div id="reportDom" style="margin-left: 10%;" ></div>
			</div>
		</powersi:groupbox>
		
	</powersi:form>
</body>
</html>