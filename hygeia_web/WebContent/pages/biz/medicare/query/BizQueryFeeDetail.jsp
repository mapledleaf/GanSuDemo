<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String reportId = (String)request.getAttribute("reportId");
%>
<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 TransitionalEN" "http:www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="费用明细" />
<style type="text/css">
#frame_div {
	margin: 20px 0;
	width: 100%;
	height: auto;
	border: 1px solid #00F;
	overflow: auto;
}
</style>
<body>
		<powersi:form id="feeDetailForm" action="" namespace="/medicare">
		<powersi:hidden id="center_flag" name="bizQueryDto.center_flag"/>
		<powersi:hidden id="aaz217" name="bizQueryDto.aaz217"/>
		<powersi:groupbox title="查询条件">
			<table>
				<tr>
					<td>
						<powersi:checkbox label="费用时间段设置" id="ckb1" name="ckb1" onclick="ckb1_fun()" />
					</td>
					<td>&nbsp;&nbsp;</td>
					<td>
						<powersi:textfield id="fromdate" label="开始时间" name="bizQueryDto.fromdate"  mask="date" format="dateFmt:'yyyy-MM-dd'" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>
						<powersi:textfield id="todate" label="结束时间" name="bizQueryDto.todate" mask="date"  format="dateFmt:'yyyy-MM-dd'"  />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="right">
						<powersi:button cssClass="button" value="设置" buttonIcon="icon-cog" name="but1" id="but1" disabled="true" onclick="btn_fun()" />
						<powersi:button id="printInfo" buttonIcon="icon-print" value="打印" onclick="print()" />
						<powersi:button id="export" buttonIcon="icon-download" label="导出" />
						<powersi:button id="closeWindow" buttonIcon="icon-remove" value="关闭" onclick="closeDialog()" />
					</td>
					
				</tr>
			</table>
		</powersi:groupbox>
		<powersi:groupbox title="费用明细清单">	
			<div id="c_groupby">明细分组:
				<powersi:radio id="radioId" name="bizQueryDto.operate" list="#{'0':'费用类别','1':'名称','2':'全部' }"/>
			</div>
			<div id="frame_div">
				<div id="reportShow" style="margin-left: 2%; margin-right: -10%;"></div>
			</div>
		</powersi:groupbox>
	</powersi:form>
	<powersi:codetable id="aka063" codeType="aka063"></powersi:codetable>
	<powersi:errors />
</body>
<script type="text/javascript" src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript" src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<script type="text/javascript">
	var powerPrint = null; //打印对象
	var reportID = null;//报表ID
	window.onload = function(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$("#fromdate").val(year+"-"+month+"-"+"01");
	  	$("#todate").val(year+"-"+month+"-"+day);
	  	btn_fun();
	}
	
	function loadHtml(reportId){
		$("#export").val(reportId);
		if(reportId=="null")
			return;
		$("#reportShow").load("${rootPath}/downloadReportHtmlServlet.download?reportID=" + reportId,
				function(response, status, xhr) {
					if (status != "success") {
						$("#reportDom").html("加载失败");
						alert("加载失败");
						return;
					}
					powerPrint = $("#reportShow").PowerPrint({
						name : '费用明细请单'
					});
				});
	}

	function print() {
		if (powerPrint == null) {
			alert("请先点击加载按钮!");
		}
		powerPrint.preview();
	}
	
	function ckb1_fun(){
	    	var ckbValue = $(':checkbox[name="ckb1"]:checked').val();
	    	if(ckbValue){
	    		document.all('fromdate').disabled = false;
	    		document.all('todate').disabled = false;
	    		document.all('but1').disabled = false;
	    	}
	    	else{
	    		document.all('fromdate').disabled = true;
	    		document.all('todate').disabled = true;
	    		document.all('but1').disabled = true;
	    	}
		}
	//按时间段设置重查一遍
	function btn_fun(){
		if(!checkForm)
			return;
		if($("#fromdate").val() > $("#todate").val()){
			alert("开始时间必须小于结束时间!");
			return;
		}
		
		var queryItemData = $("#feeDetailForm").serialize();
		postJSON("${rootPath}/query/BizQueryAction!queryFeeDetailTable.action", queryItemData,
				function (json){
					if(!checkJSONResult(json))
					    return;
					loadHtml(json.data);
			 });
	}
	
	$(function(){
		$("#c_groupby input").click(function(){
				btn_fun();
		});
		
		//导出按钮
		$("#export").click(function() {
			var reportID = $("#export").val();
			if (reportID && "none" != reportID) {
				location.href = rootPath + "/downloadReportFileServlet.download?reportID=" + reportID;
			} else {
				popupAlert("无导出内容。", "提示", "warn");
			}
		});
		
	});
</script>
</powersi:html>