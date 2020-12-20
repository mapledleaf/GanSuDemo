<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String  bae410 = request.getParameter("bae410");
%>
<powersi:html>
<head>
<powersi:head title="发票详情"  target="_self" />
<style>
.showpay {
	height: 25px !important;
	font-size: 18px !important;
	text-align: right !important;
}
</style>
</head>
<body>
	<powersi:form id="updateKab1id" namespace="/comminter"
		action="InvoiceManagerAction!updateAndGetKab1.action">
		<powersi:panelbox key="发票详细">
			<powersi:editorlayout-row>
			   <powersi:hidden id="kab1id" name="kab1DTO.kab1id" />
			   <powersi:hidden id="aaz217" name="kab3DTO.aaz217" />
			   <powersi:hidden id="bae412" name="kab1DTO.bae412"/>
			   <powersi:hidden id="kab3id" name="kab3DTO.kab3id"/>
			</powersi:editorlayout-row>
			<powersi:panelbox-toolbar>
				<powersi:button id="btnPrint" key="button_print"
					onclick="printreport('1');" disabled="false" />
				<powersi:button id="btnDesign" key="模板设计(调试专用)"
					onclick="design()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout>
				<tr>
					<td><powersi:textfield id="bill" name="bill" label="当前发票号码设置" /></td>
					<td><powersi:button id="saveBtn" label="设置" onclick="updateBill()" /></td>
				</tr>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bae410" name="kab1DTO.bae410"
						label="发票类型" codeType="bae410" displayonly="true"/>
					<powersi:textfield id="bae413" name="kab1DTO.bae413"
						label="发票号码" readonly="true"/>
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="aac003" name="kab3DTO.aac003"
						label="姓名" readonly="true" />
						<powersi:textfield id="aac002" name="kab3DTO.aac002"
						label="社会保障号"  readonly="true" />
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="aae019" name="kab3DTO.aae019"
						label="总金额" readonly="true" />
						<powersi:textfield id="aae020" name="kab3DTO.aae020"
						label="基金支付" readonly="true" />
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="aae024" name="kab3DTO.aae024"
						label="现金支付" readonly="true" />
					<powersi:hidden id="bka020" name="kab3DTO.bka020" 
					     label="住院科室" />
				</powersi:editorlayout-row>
				
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	
	<powersi:hidden id="reportIDfirst" name="reportIDfirst" />
	<div id="frame_div"  style="display:none">
		<div id="invoiceReportdivid"
			style="margin-left: 2%; margin-right: -20%;"></div>
	</div>
	
	<powersi:errors />
	<script type="text/javascript">
	var powerPrint = null; //打印对象
	var reportID =  null;
	var flag = "1";
		$(function(){
			var bae410 = '<%=bae410%>';
			if(bae410 == '02'){
				$("bka020").show();
			}
		});
		
	    function updateBill(){
	    	var bae412 = $("#bae412").val();
	    	var bae413 = $("#bae413").val();
	    	var bill = $("#bill").val();
	    	if(!powersi.isnull(bill)){
		    	if(bill > bae412){
		    		popupAlert("设置发票号的最大序号为："+bae412);
		    		return;
		    	}
		    	if(bill < bae413 ){
		    		popupAlert("设置发票号不能小于当前发票号！");
		    		return;
		    	}
	    		$("#bae413").val(bill);
	    		$("#updateKab1id").submit();
	    	}
	    }
	    
	    function printreport(str) {
	    	flag = str;
			var kab1id = $('#kab1id').val();
			if (powersi.isnull(kab1id)) {
				return;
			}
			var aaz217 = $('#aaz217').val();
			var bae410 = $('#bae410').val();
			var bae413 = $('#bae413').val();
			var kab3id = $('#kab3id').val();
					
			postJSON("${rootPath}/common/BillReportAction!billReport.action?kab3DTO.aaz217="
						+ aaz217+"&kab3DTO.bae413="+bae413+"&kab3DTO.bae410="+bae410+
						"&kab3DTO.kab1id="+kab1id+"&kab3DTO.kab3id="+kab3id,
                    afterPrint);
		}
	    
	    function afterPrint(json) {
            if (!checkJSONResult(json)) {
                  return;
            }
            //加载结果集到datagrid
            $("#reportIDfirst").val(json.data);
            loadHtml();
      }
	    
    function loadHtml(){
		//reportID 或者 bizID 参数
		reportID =  $("#reportIDfirst").val();
		$("#invoiceReportdivid").load("${rootPath}/downloadReportHtmlServlet.download?reportID="+reportID, function(response,status,xhr){
			if( status != "success" ){
				$("#invoiceReportdivid").html("加载失败");
				popupAlert("加载失败");
				return;
			}
			powerPrint = $("#invoiceReportdivid").PowerPrint({
				name : '医疗发票',
				printCallback: function(printTimes){
					if(printTimes > 0) {
						//popupAlert(printTimes);
						//调用业务处理的js函数
						processBizAfterPrint();
					}
				},
			});
			if(flag == '1')
				powerPrint.preview(true,true,'${rootPath}/resource/images/bill/bill.jpg');
		});
		
	}
    
    /** 打印模板设计 **/
    function design(){
    	flag = '0'
    	printreport();
		if(powerPrint == null) {
			popupAlert("请先点击加载按钮!");
		}
		
		//打印设计
	 	powerPrint.setup(true, true, '${rootPath}/resource/images/bill/bill.jpg');
	}
    
    /**打印后业务处理**/
    function processBizAfterPrint(){
    	popupAlert("进入processBizAfterPrint");
    	var kab1id = $('#kab1id').val();
    	var kab3id = $('#kab3id').val();
    	postJSON("${rootPath}/common/BillReportAction!processBiz.action",
				{
					"kab3DTO.kab3id" : kab3id,
					"kab3DTO.kab1id" : kab1id
				}, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					popupInfo(json.message);
					setTimeout("closeDialog();", 500);
				});
    }
    
    
	</script>
	<script type="text/javascript" src="${rootPath}/resource/report/js/powerprint.min.js"></script>
	<script type="text/javascript" src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
</body>
</powersi:html>