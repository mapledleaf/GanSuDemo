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

	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String strFromDate = dformat.format(fromDate);
	String strToDate = dformat.format(toDate);
	
%>
<powersi:html>
<head>
<powersi:head title="结算申报查询" />
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
				<powersi:editorlayout-row cols="6">
					<powersi:hidden id="akb020" name="declarePayDTO.akb020" />
					<powersi:hidden id="bka210" name="declarePayDTO.bka210" />
					<powersi:hidden id="bae008" name="declarePayDTO.bae008" />

					<powersi:hidden id="aaa027" name="declarePayDTO.aaa027" />
					<powersi:hidden id="bka097" name="declarePayDTO.bka097" />
					<powersi:hidden id="bka098" name="declarePayDTO.bka098" />
					
					<powersi:hidden id="tabSelect" />
					
				</powersi:editorlayout-row>

				<powersi:editorlayout-row cols="6">

					<powersi:textfield id="bka090" label="申报日期"
						name="declarePayDTO.bka090" mask="date" required="true"
						format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="bka091" label="至"
						name="declarePayDTO.bka091" mask="date" required="true"
						format="dateFmt:'yyyy-MM-dd'" />
					<powersi:editorlayout-button colspan="4">
						<powersi:button id="query" label="检 索" onclick="doQuery()" />
						<powersi:button id="remove" label="取 消" onclick="doRemove()" />
						<%-- <powersi:button id="btnPrint" label="打 印" onclick="dw1_print()" />
						<powersi:button label="导 出" onclick="downloadfile()" /> --%>
					</powersi:editorlayout-button>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
				</powersi:panelbox>

			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="申报基本信息" />
				<powersi:tab id="tab2" target="divTab2" label="申报汇总表" />
				<powersi:tab id="tab3" target="divTab3" label="城职汇总表" />
				<powersi:tab id="tab4" target="divTab4" label="城职明细表" />
				<powersi:tab id="tab5" target="divTab5" label="城居汇总表" />
				<powersi:tab id="tab6" target="divTab6" label="城居明细表" />

				<div id="divTab1">
					<a>双击显示报表详细信息</a>
					<powersi:datagrid id="declGrid" showReload="false"
						onDblClickRow="dbSelectRow">
						<powersi:datagrid-column name="decl_sn" label="业务交接号" />
						<powersi:datagrid-column name="decl_type" label="申报类型" />
						<powersi:datagrid-column name="decl_start" label="申报起始日期" />
						<powersi:datagrid-column name="decl_end" label="申报终止日期" />
						<powersi:datagrid-column name="decl_note" label="申报说明" />
						<powersi:datagrid-column name="decl_staff" label="申报人" />
						<powersi:datagrid-column name="accept_note" label="受理说明" />
						<powersi:datagrid-column name="accept_staff" label="受理人" />
						<powersi:datagrid-column name="accept_date" label="受理时间" />
						<powersi:datagrid-column name="bka109_name" label="处理状态" />
					</powersi:datagrid>
				</div>
				<div id="divTab2">
					<powersi:button label="打 印"
						onclick="printDW(1)" />
					<powersi:button label="导 出" onclick="downloadfile(1)" />
					<powersi:groupbox title="申报汇总表">
						<div id="frame_div">
							<div id="dw_month_decl_snyd_main" style="margin-left: 2%; margin-right: -20%;"></div>
						</div>
					</powersi:groupbox>
				</div>

				<div id="divTab3">
					<powersi:button label="打 印"
						onclick="printDW(2)" />
					<powersi:button label="导 出" onclick="downloadfile(2)" />
					<powersi:groupbox title="城职汇总表">
						<iframe name="actframe" id="actframe" 
						src="${rootPath}/pages/biz/medicare/declare/dw_month_decl_snyd_cz_stat.jsp" 
						 width="100%" height="450"   allowTransparency="true"></iframe>			
					</powersi:groupbox>
				</div>

				<div id="divTab4">
				    <powersi:button label="打 印"
						onclick="printDW(3)" />
					<powersi:button label="导 出" onclick="downloadfile(3)" />
					<powersi:groupbox title="城职明细表">
						<iframe name="actframe2" id="actframe2" 
						src="${rootPath}/pages/biz/medicare/declare/dw_month_decl_snyd_cz_detail.jsp" 
						 width="100%" height="450"   allowTransparency="true"></iframe>
					</powersi:groupbox>
				</div>
				<div id="divTab5">
				    <powersi:button label="打 印"
						onclick="printDW(4)" />
					<powersi:button label="导 出" onclick="downloadfile(4)" />
					<powersi:groupbox title="城居汇总表">
						<iframe name="actframe3" id="actframe3" 
						src="${rootPath}/pages/biz/medicare/declare/dw_month_decl_snyd_cj_stat.jsp" 
						 width="100%" height="450"   allowTransparency="true"></iframe>
					</powersi:groupbox>
				</div>
				<div id="divTab6">
				    <powersi:button label="打 印"
						onclick="printDW(5)" />
					<powersi:button label="导 出" onclick="downloadfile(5)" />
					<powersi:groupbox title="城居明细表">
						<iframe name="actframe4" id="actframe4" 
						src="${rootPath}/pages/biz/medicare/declare/dw_month_decl_snyd_cj_detail.jsp" 
						 width="100%" height="450"   allowTransparency="true"></iframe>		
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
		$('#bka097').val('<%=userName%>');
		$('#bka098').val('<%=loginUser%>');
		
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
     	cleanAll();
		 //据申报操作日期区间检索医疗费用申报受理信息
     	$('#bka210').val("3");
		var data = $("#queryForm").serialize();
		postJSON("<%=path%>/medicare/DeclarePayAction!verifySNYDDecl.action",data, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		    declGrid.loadData(json.data);
  		});
	}
	
	//双击信息列表
  	function dbSelectRow(rowdata, rowid, rowobj){
  	//取结果集里面的业务交接号
		var bae008 = rowdata['decl_sn'];
  		if(bae008 == '' || bae008 == null){
  			popupAlert("申报受理号不能为空！");
	    	return;
  		}
  		
  		$('#bae008').val(bae008);
  		 //根据申报号查询申报受理报表记录信息
     	$('#bka210').val("4");
  		
  		var data = $("#queryForm").serialize();
		postJSON("<%=path%>/medicare/DeclarePayAction!verifySNYDDecl.action",data, function(json){
			if(!checkJSONResult(json)){
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
	
  //选择行事件
	function doRemove(){
		//获取单行，第一次勾选的数据
  		var rows = declGrid.getSelectedRow();
	  //处理状态
	  	var bka109 = rows['deal_phase'];
	  	var bae008 = rows['decl_sn'];
  		if(bae008 == '' || bae008 == null){
  			popupAlert("申报受理号不能为空！");
	    	return;
  		}
  		
  		if( bka109 != '01' ){
	 		popupAlert("不是未受理状态，不能取消！");
	    	return;
	 	}
	 	if (!confirm("您确认取消申报吗?")) {
            return;
        }
	 	
		 //取消申报
    	$('#bka210').val("2");
    	$('#bae008').val(bae008);
			var data = $("#queryForm").serialize();
			postJSON("<%=path%>/medicare/DeclarePayAction!cancelSNYDDecl.action",
					data, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.data);
						declGrid.reset();
						$("#reportFirst").html("");
						$("#reportSecond").html("");
						$("#reportThird").html("");
						$("#reportFourth").html("");
						$("#reportFifth").html("");

					});
		}
  
	function loadHtml(){
		
		$("#dw_month_decl_snyd_main").html("正在加载。。。");
		$("#dw_month_decl_snyd_main").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdFirst, function(response,status,xhr){
			if( status != "success" ){
				$("#dw_month_decl_snyd_main").html("加载失败");
				popupError("加载失败");
				return;
			}
			powerPrint = $("#dw_month_decl_snyd_main").PowerPrint({name:'广东省医疗保险异地就医医疗费用结算申报汇总表'});
		});
		
		var actframe = document.getElementById("actframe");
		actframe.contentWindow.loadHtml('bizId2','dw_month_decl_snyd_cz_stat',strIdSecond,'广东省医疗保险异地就医医疗费用结算申报表（职工医保）');
		
		var actframe2 = document.getElementById("actframe2");
		actframe2.contentWindow.loadHtml('bizId3','dw_month_decl_snyd_cz_detail',strIdThird,'广东省医疗保险异地就医医疗费用结算申报表（职工医保）');
		
		var actframe3 = document.getElementById("actframe3");
		actframe3.contentWindow.loadHtml('bizId4','dw_month_decl_snyd_cj_stat',strIdFourth,'广东省医疗保险异地就医医疗费用结算申报表（城乡居民医保）');
		
		var actframe4 = document.getElementById("actframe4");
		actframe4.contentWindow.loadHtml('bizId5','dw_month_decl_snyd_cj_detail',strIdFifth,'广东省医疗保险异地就医医疗费用结算申报明细表（城乡居民医保）');
		
		$("#divTabs").tabs("select", 1);
	}
	
	
	function printDW (type)  {
		if (powerPrint == null) {
			popupAlert("请先点击加载按钮!");
			return;
		}
		if(type ==1){
			powerPrint.preview();
		}else if(type ==2){
			var actframe = document.getElementById("actframe");
			actframe.contentWindow.printDW();
		}else if(type ==3){
			var actframe2 = document.getElementById("actframe2");
			actframe2.contentWindow.printDW();
		}else if(type ==4){
			var actframe3 = document.getElementById("actframe3");
			actframe3.contentWindow.printDW();
		}else if(type ==5){
			var actframe4 = document.getElementById("actframe4");
			actframe4.contentWindow.printDW();
		}
	}
	
	/* function dw1_print() {

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

	} */
	
	
	function downloadfile (type)  {
		
		if(type ==1){
			if (strIdFirst == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdFirst;
			}
		}else if(type ==2){
			if (strIdSecond == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdSecond;
			}
		}else if(type ==3){
			if (strIdThird == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdThird;
			}
		}else if(type ==4){
			if (strIdFourth == null) {
				popupAlert("请先查询报表！");
				return;
			} else {
				location.href = rootPath
						+ "/downloadReportFileServlet.download?bizID="
						+ strIdFourth;
			}
		}else if(type ==5){
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

	/* function downloadfile() {
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

	} */

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