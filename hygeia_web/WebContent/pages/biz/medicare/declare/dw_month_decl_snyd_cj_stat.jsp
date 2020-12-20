<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
var rootPath = "${rootPath}";

 function loadHtml(hiddenid,str,id,tablename){
			$("#"+hiddenid).val(id);
			 $("#"+str).load("<%=path%>/downloadReportHtmlServlet.download?bizID=" + id,
				function(response, status, xhr) {
					if (status != "success") {
						popupAlert("加载失败");
						return;
					}
					powerPrint = $("#" + str).PowerPrint({
						name : tablename
					});

				});

	}

	function printDW() {
		if (powerPrint == null) {
			popupAlert("请先点击加载按钮!");
		}
		powerPrint.preview();
		return;

	}
</script>
<head>
</head>
<body>
		<powersi:hidden id="bizId4" name="bizId" />
		<div id="dw_month_decl_snyd_cj_stat"
			style="margin-left: 2%; margin-right: -10%;"></div>
		<div id="blankarea" style="height: 100px; padding: 15px;"></div>
		<h4 style="text-align: center; padding-right: 25px;"></h4>

</body>
<script type="text/javascript"
	src="<%=path%>/resource/report/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/report/js/LodopFuncs.js"></script>
</html>