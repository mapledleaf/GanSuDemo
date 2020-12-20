<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<powersi:head title="门诊选点登记表" />
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
	var reportBizId = null;//业务报表编号
	window.onload = function(){
		reportBizId = $("#bizIDfir").val();
		if(reportBizId == "" || reportBizId == null){
			popupAlert("请先进行选点操作!");
			closeDialog();
		}
		loadHtml();
	}
	
	function loadHtml(){
		if (powersi.isnull(reportBizId)) {
			return;
		}
		$("#reportShow").html("正在加载。。。");
		$("#reportShow").load("<%=path%>/downloadReportHtmlServlet.download?bizID=" 
				+ reportBizId,function(response,status,xhr){
		if( status != "success" ){
			$("#reportShow").html("加载失败");
				popupAlert("加载失败");
				return;
			}
			powerPrint = $("#reportShow").PowerPrint({name:'门诊选点登记表'});
		});
	}
	
	function printDW() {
		if(powerPrint == null) {
			popupAlert("请先点击加载按钮!");
		}
		powerPrint.preview();
	}
	
	function exportDW() {
		location.href = rootPath+"/downloadReportFileServlet.download?bizID=" + reportBizId;
	}

</script>
<body>
	<powersi:form id="mainForm" name="mainForm" action=""
		namespace="/medicare">
		<div align="center">
			<powersi:hidden id="bizIDfir" name="mediSpecDto.bizIDfir" />
			<powersi:buttons>
				<powersi:button id="dw1_pt" value="打印门诊选点登记表" onclick="printDW()" />
				<powersi:button id="dw1_dc" value="导出门诊选点登记表" onclick="exportDW()" />
			</powersi:buttons>
		</div>
		<powersi:groupbox title="门诊选点登记表">
			<div id="frame_div">
				<div id="reportShow" style="margin-left: 2%; margin-right: -10%;"></div>
			</div>
		</powersi:groupbox>
	</powersi:form>
</body>
</html>