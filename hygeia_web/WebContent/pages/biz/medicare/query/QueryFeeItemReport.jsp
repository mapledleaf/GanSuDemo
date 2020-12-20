<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
    //NTS20091700125 住院统计待遇类型指标加入住院实质性治疗统计条件 陈洁  20200918
	String handle = (String) request.getAttribute("handle");
	String title = "";
	String bka130Filter = "";
	String codeFilterValue = "";
	if ("yd".equals(handle)) {
		title = "药店费用明细清单";
		bka130Filter = "data_value IN  ('10')";
		codeFilterValue = "data_value like '10_'";
	} else if ("mz".equals(handle)) {
		title = "门诊费用明细清单";
		bka130Filter = "data_value IN ('11','51')";
		codeFilterValue = "data_value IN ('110','511','114')";
	} else if ("zy".equals(handle)) {
		title = "住院费用明细清单";
		bka130Filter = "data_value IN  ('12')";
		codeFilterValue = "data_value in ('120','121','122','128','129','12X','12M','12S')";
	} else if ("mt".equals(handle)) {
		title = "门特费用明细清单";
		bka130Filter = "data_value IN  ('13')";
		codeFilterValue = "data_value like '13_'";
	} else if ("jtbc".equals(handle)) {
		title = "家庭病床费用明细清单";
		bka130Filter = "data_value IN  ('14')";
		codeFilterValue = "data_value like '14_'";
	} else if ("syzy".equals(handle)) {
		title = "家庭病床费用明细清单";
		bka130Filter = "data_value IN  ('52')";
		codeFilterValue = "data_value like '52_'";
	} else if ("dbty".equals(handle)) {
		title = "大病特药费用明细清单";
		bka130Filter = "data_value IN  ('18')";
		codeFilterValue = "data_value like '18_'";
	}
%>
<html>
<powersi:head title="<%=title%>" />
<body>
	<powersi:form id="mainForm">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:hidden id="reportId" name="reportId" />
			<powersi:panelbox-toolbar>
				<powersi:button id="queryId"  label="检 索" buttonIcon="icon-search" onclick="queryData()" />
				<powersi:button id="printId"  label="打 印" buttonIcon="icon-print"  onclick="print()" />
				<powersi:button id="exportId" label="导 出" buttonIcon="icon-download" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row cols="8">
					<powersi:codeselect id="caa027" name="bizQueryDto.caa027" label="中心系统"
						required="true" codeType="caa027" headerKey="0" />
					<powersi:codeselect id="aka130" name="bizQueryDto.aka130" label="业务类型"
						required="true" codeType="aka130" codeFilter="<%=bka130Filter%>"
						headerKey="0" onchange="changeBka006()" />
					<powersi:codeselect id="aae140" name="bizQueryDto.aae140" label="险种类型"
						codeType="aae140" codeFilter="DATA_VALUE IN (310,340,390,510)" />
					<powersi:textfield id="bka090" name="bizQueryDto.fromdate" label="结算开始日期" 
						 format="dateFmt:'yyyy-MM-dd HH:mm:ss'" mask="date"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row cols="6">
					<powersi:codeselect id="aaa027" name="bizQueryDto.aaa027" label="参保人统筹区"
						codeType="aaa027" cssClass="select2" required="true" value="430399"/>
					<powersi:codeselect id="bka006" name="bizQueryDto.bka006" codeType="bka006"
						label="待遇类型" codeFilter="<%=codeFilterValue%>" headerKey=""
						headerValue="全部" />
					<powersi:codeselect id="aae014" name="bizQueryDto.aae014" label="操作人姓名"
						list="#request.userInfo" showValue="false" headerKey="" 
						headerValue="请选择..." />
					<powersi:textfield id="bka091" name="bizQueryDto.todate" label="结算结束日期" 
						mask="date" format="dateFmt:'yyyy-MM-dd HH:mm:ss'" />
				</powersi:editorlayout-row>
				<powersi:hidden name="hadle" value="<%=handle%>" />
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="报表列表">
		<div id="frame_div">
			<div id="reportShow" style="margin-left: 2%;"></div>
		</div>
	</powersi:panelbox>
</body>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<script type="text/javascript">
	$(function() {
		 var myDate = new Date();
		 var year = myDate.getFullYear();
		 var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		 var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		 var hour = myDate.getHours() < 10 ? "0" + myDate.getHours() : myDate.getHours();
		 var minute = myDate.getMinutes() < 10 ? "0" + myDate.getMinutes() : myDate.getMinutes();
		 var second = myDate.getSeconds() < 10 ? "0" + myDate.getSeconds() : myDate.getSeconds();
		 $("#bka090").val(year+"-"+month+"-"+"01 00:00:00");
		 $("#bka091").val(year+"-"+month+"-"+day+" 23:59:59");
		//获取页面查询参数
		getParams = function() {
			var params = {};
			var sel = $(".tableEditor input[type=text],.select");
			$.each(sel, function(i, n) {
				var id = $(n).attr('id');
				var v = $(n).attr('ligeruiid') ? liger.get(id).getValue() : $(n).val();
				v = v == '' || v == '请选择...' ? null : v;
				params[id] = v;
			});
			return params;
		}

		//报表显示
		showReport = function(selector, reportID) {
			var url = rootPath + "/downloadReportHtmlServlet.download?reportID=" + reportID;
			selector.load(url, function(response, status, xhr) {
				if (status != "success") {
					selector.html("加载失败");
					alert("加载失败");
					return;
				} else {
					$("iframe[reportID='" + reportID + "']").height(selector.height() * 1.5);
				}
			});
		}

		//导出按钮
		$("#exportId").click(function() {
			var reportId = $("#reportId").val();
			if (reportId && "none" != reportId) {
				location.href = rootPath + "/downloadReportFileServlet.download?reportID=" + reportId;
			} else {
				popupAlert("无导出内容。", "提示", "warn");
			} 
		});
	});
	
	var powerPrint = null; //打印对象
	var reportId = null;//报表ID
	var czyList = [];
	
	function queryData(){
		if (!checkFormValidtion()) 
			return;
		var queryItemData = $("#mainForm").serialize();
		postJSON("${rootPath}/query/BizQueryAction!createFeeItemReport.action",queryItemData,
				function(json){
					if(!checkJSONResult(json))
					    return;
		 			loadHtml(json.data);
				});
	}
	
	function loadHtml(id){
		$("#reportId").val(id);
		$("#reportShow").load("${rootPath}/downloadReportHtmlServlet.download?reportID=" + id,
				function(response, status, xhr) {
					if (status != "success") {
						$("#reportDom").html("加载失败");
						alert("加载失败");
						return;
					}
					powerPrint = $("#reportShow").PowerPrint({
						name : '<%=title%>'
					});
				});
	}
	
	function print() {
		if (powerPrint == null) {
			alert("请先点击【检 索】按钮!");
		}
		powerPrint.preview();
	}
</script>
</html>