<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
    String path = request.getContextPath();
%>
<html>
<head>
<script type="text/javascript">
    var rootPath = "${rootPath}"; //${rootPath} 根据框架定义的不同，参数也不同。
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script type="text/javascript" src="<%=path%>/resource/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript" src="<%=path%>/resource/report/js/LodopFuncs.js"></script>
<style type="text/css">
	#frame_div{
		margin:20px 0;
		width: 100%;
		height: 350px;
		border: 1px solid #F00;
		overflow: auto;
	}
</style>
</head>

<body style="zoom:1;font-family:'adobe-clean',sans-serif;">

<h2 style="margin-left: 20%"><font color="#009999">演示动态加载PowerMarker生成的报表，并进行打印操作</font></h2>
<h3><font color="#990099">1. 动态加载PowerMarker生成的html到下面的红色div内部</font>&nbsp;<a href="javascript:createReport();">生成报表</a>&nbsp;&nbsp;<a href="javascript:loadHtml();">点击加载</a></h3>
<h3><font color="#990099">2. 操作：</font>
	&nbsp;&nbsp;&nbsp;<a href="javascript:preview()">打印预览</a>
	&nbsp;&nbsp;&nbsp;<a href="javascript:print()">直接打印</a>
	&nbsp;&nbsp;&nbsp;<a href="javascript:setup()">打印设置</a>
	&nbsp;&nbsp;&nbsp;<a href="javascript:design()">打印设计</a>
</h3>
<div id="frame_div">
	<div id="reportDom" style="margin-left: 10%;" ></div>
</div>

<script language="JavaScript">
var powerPrint = null; //打印对象
var reportID = null;//报表ID
function loadHtml(){
	$("#reportDom").html("正在加载。。。");
	//reportID 或者 bizID 参数
	$("#reportDom").load("<%=path%>/downloadReportHtmlServlet.download?bizID=1", function(response,status,xhr){
		if( status != "success" ){
			$("#reportDom").html("加载失败");
			alert("加载失败");
			return;
		}
		powerPrint = $("#reportDom").PowerPrint({name:'系统菜单报表'});
	});
};

//打印预览
function preview() {
	if(powerPrint == null) {
		alert("请先点击加载按钮!");
	}
	powerPrint.preview( );
}
//直接打印
function print(){
	if(powerPrint == null) {
		alert("请先点击加载按钮!");
	}
	powerPrint.print( );
}
//打印设置
function setup(){
	if(powerPrint == null) {
		alert("请先点击加载按钮!");
	}
	powerPrint.setup( );
}
//打印设计
function design(){
	if(powerPrint == null) {
		alert("请先点击加载按钮!");
	}
	powerPrint.design( );
}

//生成菜单报表
function createReport(flag) {
	$("#reportDom").html("正在加载。。。");
	$("#reportDom").load("<%=path%>/sample/report!createReport.action?flag="+flag, function(response,status,xhr){
		if( status != "success" ){
			$("#reportDom").html("加载失败");
			alert("加载失败");
			return;
		}
	});
};
</script>
</body>       
</html>