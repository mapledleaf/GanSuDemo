<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaa027 = BizHelper.getAaa027();

	java.util.Date d = new java.util.Date();
	java.util.Date toDate = d;
	d = DateFunc.addMonths(d, -1);
	java.util.Date fromDate = DateFunc.getFirstDateOfMonth(d);
	toDate = DateFunc.getLastDateOfMonth(d);

	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String strFromDate = dformat.format(fromDate);
	String strToDate = dformat.format(toDate);
%>
<powersi:html>
<head>
<powersi:head title="结算申报" />
</head>
<body>
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

	<powersi:form id="queryForm" namespace="/medicare"
		action="DeclarePayAction!verifyCenterDecl.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="declarePayDTO.akb020" />
					<powersi:hidden id="oper_flag" name="declarePayDTO.oper_flag" />
					<powersi:hidden id="save_flag" name="declarePayDTO.save_flag" />
					<powersi:hidden id="aaa027" name="declarePayDTO.aaa027" />
					<powersi:hidden id="bkc027" name="declarePayDTO.bkc027" />
					<powersi:hidden id="bkc028" name="declarePayDTO.bkc028" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row cols="6">
					<powersi:textfield id="decl_sn" label="业务交接号"
						name="declarePayDTO.decl_sn" required="true" />
					<powersi:editorlayout-button colspan="4">
						<powersi:button id="query" label="检 索" onclick="doQuery()" />
						<powersi:button id="btnPrint" label="打 印" onclick="dw1_print()" />
						<powersi:button label="导 出" onclick="downloadfile()" />
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab2" target="divTab2" label="申报汇总表" />
			<powersi:tab id="tab3" target="divTab3" label="城职汇总表" />
			<powersi:tab id="tab4" target="divTab4" label="城职明细表" />
			<powersi:tab id="tab5" target="divTab5" label="城居汇总表" />
			<powersi:tab id="tab6" target="divTab6" label="城居明细表" />

			<div id="divTab2">

				<powersi:groupbox title="申报汇总表">
					<div id="frame_div">
						<div id="reportFirst" style="margin-left: 2%; margin-right: -20%;"></div>
					</div>
				</powersi:groupbox>
			</div>

			<div id="divTab3">
				<powersi:groupbox title="城职汇总表">
					<div id="frame_div">
						<div id="reportSecond"
							style="margin-left: 2%; margin-right: -20%;"></div>
					</div>
				</powersi:groupbox>
			</div>

			<div id="divTab4">
				<powersi:groupbox title="城职明细表">
					<div id="frame_div">
						<div id="reportThird"
							style="margin-left: 2%; margin-right: -20%;"></div>
					</div>
				</powersi:groupbox>
			</div>
			<div id="divTab5">
				<powersi:groupbox title="城居汇总表">
					<div id="frame_div">
						<div id="reportFourth"
							style="margin-left: 2%; margin-right: -20%;"></div>
					</div>
				</powersi:groupbox>
			</div>
			<div id="divTab6">
				<powersi:groupbox title="城居明细表">
					<div id="frame_div">
						<div id="reportFifth"
							style="margin-left: 2%; margin-right: -20%;"></div>
					</div>
				</powersi:groupbox>
			</div>
		</powersi:tabbedpanel>

	</powersi:form>


	<powersi:errors />

	<script type="text/javascript">

	var powerPrint = null; //打印对象
	var strIdFirst = null;
	var strIdSecond = null;
	var strIdThird = null;
	var strIdfourth = null;
	var strIdFifth = null;
	window.onload = function(){
	 	$('#akb020').val("<%=hospital_id%>");
	 	$('#akb021').val("<%=hospital_name%>");
	 	
	 	$('#bka090').val('<%=strFromDate%>');
		$('#bka091').val('<%=strToDate%>');
		$('#aaa027').val('<%=aaa027%>');
		$('#bkc027').val('<%=userName%>');
		$('#bkc028').val('<%=loginUser%>');
		
	 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
			popupAlert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
	}
	
	
	function doQuery(){
		 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
		 
		//据申报操作日期区间检索医疗费用申报受理信息
     	$('#oper_flag').val("9");
		var data = $("#queryForm").serialize();
		
		//popupAlert(data);
		postJSON("<%=path%>/medicare/DeclarePayAction!verifySNYDDecl.action",
					data, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupSuccess("操作成功！");
						strIdFirst = json.data.firstId;
						strIdSecond = json.data.secondId;
					    strIdThird = json.data.thirdId;
					    strIdFourth = json.data.fourthId;
					    strIdFifth = json.data.fifthId;
						$("#reportFirst").html("正在加载。。。");
						loadHtml();
						}); 

		}
	
	function loadHtml(){
		$("#reportFirst").html("正在加载。。。");
		$("#reportFirst").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdFirst, function(response,status,xhr){
			if( status != "success" ){
				$("#reportFirst").html("加载失败");
				popupError("加载失败");
				return;
			}
		});
		$("#reportSecond").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdSecond, function(response,status,xhr){
			if( status != "success" ){
				$("#reportSecond").html("加载失败");
				popupError("加载失败");
				return;
			}
		});
		$("#reportThird").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdThird, function(response,status,xhr){
			if( status != "success" ){
				$("#reportThird").html("加载失败");
				popupError("加载失败");
				return;
			}
		});
		$("#reportFourth").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdFourth, function(response,status,xhr){
			if( status != "success" ){
				$("#reportThird").html("加载失败");
				popupError("加载失败");
				return;
			}
		});
		$("#reportFifth").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdFifth, function(response,status,xhr){
			if( status != "success" ){
				$("#reportThird").html("加载失败");
				popupError("加载失败");
				return;
			}
		});
	}
	
	function dw1_print() {

		var tabSelect = $('#tabSelect').val();
		if (tabSelect == "" || tabSelect == "tab2") {
			if (strIdFirst == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				powerPrint = $("#reportFirst").PowerPrint({
					name : '申报汇总表'
				});
				powerPrint.preview();
			}
		} else if (tabSelect == "tab3") {
			if (strIdSecond == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				powerPrint = $("#reportSecond").PowerPrint({
					name : '申报汇总表'
				});
				powerPrint.preview();
			}
		} else if (tabSelect == "tab4") {
			if (strIdThird == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				powerPrint = $("#reportThird").PowerPrint({
					name : '城职明细表'
				});
				powerPrint.preview();
			}
		} else if (tabSelect == "tab5") {
			if (strIdFourth == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				powerPrint = $("#reportFourth").PowerPrint({
					name : '城居汇总表'
				});
				powerPrint.preview();
			}
		} else if (tabSelect == "tab6") {
			if (strIdFifth == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				powerPrint = $("#reportFifth").PowerPrint({
					name : '城居明细表'
				});
				powerPrint.preview();
			}
		}

	}

	function downloadfile() {
		var tabSelect = $('#tabSelect').val();
		if (tabSelect == "" || tabSelect == "tab2") {
			if (strIdFirst == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdFirst;
			}
		} else if (tabSelect == "tab3") {
			if (strIdSecond == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdSecond;
			}
		} else if (tabSelect == "tab4") {
			if (strIdThird == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdThird;
			}
		} else if (tabSelect == "tab5") {
			if (strIdFourth == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdFourth;
			}
		} else if (tabSelect == "tab6") {
			if (strIdFifth == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdFifth;
			}
		}

	}

	$(function() {
		bindAfterSelectTab('#divTabs', showTabsEvent);
	});

	function showTabsEvent(newTabId, oldTabId) {
		$('#tabSelect').val(newTabId);
	}

	function cleanAll() {
		$("#reportFirst").html("");
		$("#reportSecond").html("");
		$("#reportThird").html("");
		$("#reportFourth").html("");
		$("#reportFifth").html("");
	}
	</script>
</body>
</powersi:html>