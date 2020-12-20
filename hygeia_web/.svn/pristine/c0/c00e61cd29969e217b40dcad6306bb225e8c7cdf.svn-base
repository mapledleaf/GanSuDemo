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
	
	
 	//4404 珠海//4418 清远
	String model = aaa027.substring(0,4);
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
			<powersi:panelbox-toolbar>
				<powersi:button id="query" label="检 索" onclick="doQuery()" />
				<powersi:button id="save" label="申 报" onclick="doSave()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row cols="6">
					<powersi:hidden id="akb020" name="declarePayDTO.akb020" />
					<powersi:hidden id="bka210" name="declarePayDTO.bka210" />
					<powersi:hidden id="bae008" name="declarePayDTO.bae008" />
					<powersi:hidden id="bka211" name="declarePayDTO.bka211" />

					<powersi:hidden id="aaa027" name="declarePayDTO.aaa027" />
					<powersi:hidden id="bka097" name="declarePayDTO.bka097" />
					<powersi:hidden id="bka098" name="declarePayDTO.bka098" />
					<!--  单据数-->
					<powersi:hidden id="bka095" name="declarePayDTO.bka095" value="0" />
						
					<powersi:hidden id="tabSelect" />
					
				</powersi:editorlayout-row>
				<powersi:editorlayout-row cols="6">
					<powersi:textfield id="akb021" label="医院名称" readonly="true" />
											
					<powersi:codeselect id="aae140" name="declarePayDTO.aae140"
						label="申报类型" required="true" codeType="sblx" headerKey="-1"
						codeFilter="data_value in ('310','391','410','510')" />
					
					<powersi:textfield id="bka090" label="申报日期"
						name="declarePayDTO.bka090" mask="date" required="true"
						format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="bka091" label="至"
						name="declarePayDTO.bka091" mask="date" required="true"
						format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka096" name="declarePayDTO.bka096"
						label="申报说明" colspan="7" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

		<powersi:panelbox key="panel_result" title="报表列表">
			<powersi:tabbedpanel id="divTabs">
				<powersi:tab id="tab2" target="divTab2" label="结算申报一级表" />
				<powersi:tab id="tab3" target="divTab3" label="结算申报二级表" />
				<powersi:tab id="tab4" target="divTab4" label="结算申报三级表" />
			
				<div id="divTab2">
			        <powersi:button label="打 印"
						onclick="printDW(1)" />
					<div id="frame_div">
						<div id="reportFirst" style="margin-left: 2%; margin-right: -20%;"></div>
					</div>
				</div>
			
				<div id="divTab3">
				    <powersi:button label="打 印"
						onclick="printDW(2)" />
					<iframe name="secondframe" id="secondframe" 
						src="${rootPath}/pages/biz/medicare/declare/month_decl_report_second.jsp" 
						 width="100%" height="450"   allowTransparency="true"></iframe> 
				</div>
			
				<div id="divTab4">
					 <div id="ybDiv" style="display:none;">
				    	<powersi:form id="detailForm" namespace="/medicare"
						action="DeclarePayAction!queryDeclDetail.action">	
						<powersi:datagrid id="detailGrid" showReload="false" enabledSort="false"
							pageSize="200" delayLoad="true" showExportExcel2007="true" formId="detailForm"
							showExportPdf="true" exportFileName="'医疗保险定点医疗机构医疗费月结算申报表（三级表）'" >
							<powersi:datagrid-column name="name" label="姓名" />
							<powersi:datagrid-column name="indi_id" label="个人电脑号"  />
							<powersi:datagrid-column name="pers_type" label="人员类别" />
							<powersi:datagrid-column name="hospital_name" label="医院名称" />
							<powersi:datagrid-column name="dept_name" label="科室" />
							<powersi:datagrid-column name="area_name" label="病区" />
							<powersi:datagrid-column name="patient_id" label="住院登记号" />
							<powersi:datagrid-column name="begin_date" label="入院日期" />
							<powersi:datagrid-column name="end_date" label="出院日期" />
							<powersi:datagrid-column name="disease" label="出院诊断" />
							<powersi:datagrid-column name="treatment_type" label="待遇类型" />
							<powersi:datagrid-column name="in_days" label="住院天数" />
							<powersi:datagrid-column name="total_fee" label="医疗费总金额" />
							<powersi:datagrid-column name="self_pay" label="自费金额" />
							<powersi:datagrid-column name="part_self" label="部分项目自付金额" />
							<powersi:datagrid-column name="qfx_pay" label="起付标准金额" />
							<powersi:datagrid-column name="medi_self_pay" label="医保共付段（自付金额）" />
							<powersi:datagrid-column name="medi_acc_pay" label="医保共付段（统筹记帐）" />
							<powersi:datagrid-column name="grave_self_pay" label="大病共付段（自付金额）" />
							<powersi:datagrid-column name="grave_acc_pay" label="大病共付段（大病记帐）" />
							<powersi:datagrid-column name="exc_grave_pay" label="超大病段金额" />
							<powersi:datagrid-column name="bsmedi_pay" label="基药补助" />
							<powersi:datagrid-column name="sup_acc_pay" label="补助/补充记帐金额" />
							<powersi:datagrid-column name="acc_sum_pay" label="记帐金额合计" />
							<powersi:datagrid-column name="remark" label="备注" />
						</powersi:datagrid>
					</powersi:form>
				    </div>
				    
				     <div id="gsDiv" style="display:none;">
				    	<powersi:form id="gsForm" namespace="/medicare"
						action="DeclarePayAction!queryDeclDetail.action">	
						<powersi:datagrid id="gsGrid" showReload="false" enabledSort="false"
							pageSize="200" delayLoad="true" showExportExcel2007="true" formId="gsForm"
							showExportPdf="true" exportFileName="'医疗保险定点医疗机构医疗费月结算申报表（三级表）'" >
							<powersi:datagrid-column name="name" label="姓名" />
							<powersi:datagrid-column name="indi_id" label="个人电脑号"  />
							<powersi:datagrid-column name="treatment_type" label="待遇类别" />
							<powersi:datagrid-column name="pers_type" label="人员类别" />
							<powersi:datagrid-column name="patient_id" label="住院号" />
							<powersi:datagrid-column name="begin_date" label="起始日期" />
							<powersi:datagrid-column name="end_date" label="截止日期" />
							<powersi:datagrid-column name="icd" label="诊断" />
							<powersi:datagrid-column name="beds" label="住院天数" />
							<powersi:datagrid-column display="总费用">
								<powersi:datagrid-column name="fee_all" label="合计" />
								<powersi:datagrid-column name="fee_all_item" label="医疗项目费用" />
								<powersi:datagrid-column name="fee_all_medi" label="医疗康复项目" />
								<powersi:datagrid-column name="fee_all_job" label="职业康复项目" />
								<powersi:datagrid-column name="fee_all_help" label="辅助器具项目费用" />
							</powersi:datagrid-column>
							<powersi:datagrid-column display="统筹记帐费用">
								<powersi:datagrid-column name="fee_pay" label="合计" />
								<powersi:datagrid-column name="fee_pay_item" label="医疗项目费用" />
								<powersi:datagrid-column name="fee_pay_medi" label="医疗康复项目" />
								<powersi:datagrid-column name="fee_pay_job" label="职业康复项目" />
								<powersi:datagrid-column name="fee_pay_help" label="辅助器具项目费用" />
								<powersi:datagrid-column name="self_pay" label="自费金额" />
							</powersi:datagrid-column>
							<powersi:datagrid-column name="remark" label="备注" />
						</powersi:datagrid>
					</powersi:form>
				    </div>
				    
				     <div id="syDiv" style="display:none;">
				    	<powersi:form id="syForm" namespace="/medicare"
						action="DeclarePayAction!queryDeclDetail.action">	
						<powersi:datagrid id="syGrid" showReload="false" enabledSort="false"
							pageSize="200" delayLoad="true" showExportExcel2007="true" formId="syForm"
							showExportPdf="true" exportFileName="'医疗保险定点医疗机构医疗费月结算申报表（三级表）'" >
							<powersi:datagrid-column name="name" label="姓名" />
							<powersi:datagrid-column name="indi_id" label="个人电脑号"  />
							<powersi:datagrid-column name="pers_type" label="人员类别" />
							<powersi:datagrid-column name="hospital_name" label="医院名称" />
							<powersi:datagrid-column name="dept_name" label="科室" />
							<powersi:datagrid-column name="area_name" label="病区" />
							<powersi:datagrid-column name="patient_id" label="住院登记号" />
							<powersi:datagrid-column name="begin_date" label="入院日期" />
							<powersi:datagrid-column name="end_date" label="出院日期" />
							<powersi:datagrid-column name="disease" label="出院诊断" />
							<powersi:datagrid-column name="treatment_type" label="待遇类型" />
							<powersi:datagrid-column name="in_days" label="住院天数" />
							<powersi:datagrid-column name="total_fee" label="医疗费总金额" />
							<powersi:datagrid-column name="self_pay" label="自费金额" />
							<powersi:datagrid-column name="part_self" label="部分项目自付金额" />
							<powersi:datagrid-column name="medi_self_pay" label="自付金额" />
							<powersi:datagrid-column name="medi_acc_pay" label="统筹记帐" />
							<powersi:datagrid-column name="remark" label="备注" />
						</powersi:datagrid>
					</powersi:form>
				    </div>
				</div>
			</powersi:tabbedpanel>
		</powersi:panelbox>


	<powersi:errors />

	<script type="text/javascript">

	var powerPrint = null; //打印对象
	var strIdFirst = null;
	var strIdSecond = null;
	/* var strIdThird = null; */
	window.onload = function(){
	 	$('#akb020').val("<%=hospital_id%>");
	 	$('#akb021').val("<%=hospital_name%>");
	 	
	 	$('#bka090').val('<%=strFromDate%>');
		$('#bka091').val('<%=strToDate%>');
		$('#aaa027').val('<%=aaa027%>');
		$('#bka097').val('<%=loginUser%>');
		$('#bka098').val('<%=userName%>');
		
	 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
			popupAlert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
	 	
	}
	
	function loadHtml(){
		$("#reportFirst").html("正在加载。。。");
		$("#reportFirst").load("<%=path%>/downloadReportHtmlServlet.download?bizID="+strIdFirst, function(response,status,xhr){
			if( status != "success" ){
				$("#reportFirst").html("加载失败");
				popupError("加载失败");
				return;
			}
			powerPrint = $("#reportFirst").PowerPrint({name:'医疗机构医疗费月结算申报表（一级表）'});
		});
		
		var secondframe = document.getElementById("secondframe");
		secondframe.contentWindow.loadHtml('secondBizId','reportSecond',strIdSecond,'医疗机构医疗费月结算申报表（二级表）');
		
		/* var thirdframe = document.getElementById("thirdframe");
		thirdframe.contentWindow.loadHtml('thirdBizId','reportThird',strIdThird,'医疗机构医疗费月结算申报表（三级表）'); */
	}

	function doQuery(){
		 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
		 
     	var aae140 = $('#aae140').val();
		//据申报操作日期区间检索医疗费用申报受理信息
     	$('#bka210').val("1");
     	$('#bka211').val("0");
		var data = $("#queryForm").serialize();
		
		//alert(data);
		postJSON("<%=path%>/medicare/DeclarePayAction!verifyCenterDecl.action",data, function(json){
			if(!checkJSONResult(json)){
				cleanAll();
			    return;
		    }
		    popupAlert("操作成功！");
			strIdFirst = json.data.firstId;
			strIdSecond = json.data.secondId;
		   /*  strIdThird = json.data.thirdId; */
			$("#reportFirst").html("正在加载。。。");
			loadHtml();
			//detailGrid.loadData(json.data.third);
			
			if(aae140 == '410'){
				var ybDiv = document.getElementById("ybDiv");
			 	ybDiv.style.display="none";
				var gsDiv = document.getElementById("gsDiv");
				gsDiv.style.display="block";
				var syDiv = document.getElementById("syDiv");
				syDiv.style.display="none";
				gsGrid.loadData(json.data.third);
			}else if(aae140 == '510'){
				var ybDiv = document.getElementById("ybDiv");
			 	ybDiv.style.display="none";
				var gsDiv = document.getElementById("gsDiv");
				gsDiv.style.display="none";
				var syDiv = document.getElementById("syDiv");
				syDiv.style.display="block";
				syGrid.loadData(json.data.third);
			}else{
				var ybDiv = document.getElementById("ybDiv");
			 	ybDiv.style.display="block";
				var gsDiv = document.getElementById("gsDiv");
				gsDiv.style.display="none";
				var syDiv = document.getElementById("syDiv");
				syDiv.style.display="none";
				detailGrid.loadData(json.data.third);
			}
  		}); 
		
	}
	
	
	function doSave(){
		 //校验必填项
     	if(!checkFormValidtion())
     	{
	  		return;
		}
		 
    	if($("#bka095").val() != '' || $("#bka095").val() != null ){
    		if(isNaN($("#bka095").val())){
        		popupAlert("申报单据数必须是数字！");
    	    	return;
       		}
		}
    	
    	var aae140 = $('#aae140').val();
		 //据申报操作日期区间检索医疗费用申报受理信息
     	$('#bka210').val("1");
     	$('#bka211').val("1");
		var data = $("#queryForm").serialize();
		postJSON("<%=path%>/medicare/DeclarePayAction!verifyCenterDecl.action",
					data, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}

						 popupSuccess("操作成功！");
						strIdFirst = json.data.firstId;
						strIdSecond = json.data.secondId;
						/* strIdThird = json.data.thirdId; */
						$("#reportFirst").html("正在加载。。。");
						loadHtml();
						//detailGrid.loadData(json.data.third);
						
						if(aae140 == '410'){
							var ybDiv = document.getElementById("ybDiv");
						 	ybDiv.style.display="none";
							var gsDiv = document.getElementById("gsDiv");
							gsDiv.style.display="block";
							var syDiv = document.getElementById("syDiv");
							syDiv.style.display="none";
							gsGrid.loadData(json.data.third);
						}else if(aae140 == '510'){
							var ybDiv = document.getElementById("ybDiv");
						 	ybDiv.style.display="none";
							var gsDiv = document.getElementById("gsDiv");
							gsDiv.style.display="none";
							var syDiv = document.getElementById("syDiv");
							syDiv.style.display="block";
							syGrid.loadData(json.data.third);
						}else{
							var ybDiv = document.getElementById("ybDiv");
						 	ybDiv.style.display="block";
							var gsDiv = document.getElementById("gsDiv");
							gsDiv.style.display="none";
							var syDiv = document.getElementById("syDiv");
							syDiv.style.display="none";
							detailGrid.loadData(json.data.third);
						}
					});

		}
	
	function printDW (type)  {
		if (powerPrint == null) {
			popupAlert("请先点击加载按钮!");
			return;
		}
		if(type ==1){
			powerPrint.preview();
		}else if(type ==2){
			var secondframe = document.getElementById("secondframe");
			secondframe.contentWindow.printDW();
		}else if(type ==3){
			var thirdframe = document.getElementById("thirdframe");
			thirdframe.contentWindow.printDW();
		}
	}
	
	function downloadfile (type)  {
		
		if(type ==1){
			if(strIdFirst == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				location.href = rootPath+"/downloadReportFileServlet.download?bizID="+strIdFirst;
			}
		}else if(type ==2){
			if(strIdSecond == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				location.href = rootPath+"/downloadReportFileServlet.download?bizID="+strIdSecond;
			}
		}else if(type ==3){
			if(strIdThird == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				location.href = rootPath+"/downloadReportFileServlet.download?bizID="+strIdThird;
			}
		}
	}
	
	/* function dw1_print() {
		
		var tabSelect = $('#tabSelect').val();
		if (tabSelect == "" || tabSelect == "tab2") {
			if(strIdFirst == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				powerPrint = $("#reportFirst").PowerPrint({name:'结算申报一级表'});
				powerPrint.preview();
			}
		}else if (tabSelect == "tab3") {
			if(strIdSecond == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				powerPrint = $("#reportSecond").PowerPrint({name:'结算申报二级表'});
				powerPrint.preview();
			}
		}else if (tabSelect == "tab4") {
			if(strIdThird == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				powerPrint = $("#reportThird").PowerPrint({name:'结算申报三级表'});
				powerPrint.preview();
			}
		}
		
		
	} */
	
	
	/* function downloadfile(){
		var tabSelect = $('#tabSelect').val();
		if (tabSelect == "" || tabSelect == "tab2") {
			if(strIdFirst == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				location.href = rootPath+"/downloadReportFileServlet.download?bizID="+strIdFirst;
			}
		}else if (tabSelect == "tab3") {
			if(strIdSecond == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				location.href = rootPath+"/downloadReportFileServlet.download?bizID="+strIdSecond;
			}
		}else if (tabSelect == "tab4") {
			if(strIdThird == null){
				popupAlert("请先查询报表！");
				return;
			}else{
				location.href = rootPath+"/downloadReportFileServlet.download?bizID="+strIdThird;
			}
		}
		
	} */
	
	$(function(){	
		bindAfterSelectTab('#divTabs', showTabsEvent);
	});
	
	function showTabsEvent(newTabId, oldTabId){
		$('#tabSelect').val(newTabId);
	}
	
	function cleanAll(){
		$("#reportFirst").html("");
		$("#reportSecond").html("");
		$("#reportThird").html("");
	}
	</script>
</body>
</powersi:html>