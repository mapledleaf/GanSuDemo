<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();	
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String fromdate = request.getParameter("bizQueryDto.fromdate");
	fromdate = fromdate.substring(0, 10);
	String aaz217 = request.getParameter("bizQueryDto.aaz217");
	String one = request.getParameter("bizQueryDto.one");
	String secfalg = request.getParameter("bizQueryDto.secfalg");
%>
<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 TransitionalEN" "http:www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="费用明细" />
<script type="text/javascript"
	src="<%=path%>/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"	src="<%=path%>/resource/report/js/LodopFuncs.js"></script>
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
	var reportID = null;//报表ID
	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");
		$('#aaz217').val("<%=aaz217%>");
		$('#fromdate').val("<%=fromdate%>");
		$('#one').val("<%=one%>");
		$('#secfalg').val("<%=secfalg%>");
		var akb020 = $('#akb020').val();
		var aaz217 = $('#aaz217').val();
		var fromdate = $('#fromdate').val();
		var one =$('#one').val();
		var secfalg =$('#secfalg').val();
		$("#todate").val(fromdate);
		
		var url = "<%=path%>/query/BizQueryAction!queryFeeDetailTableYdjy.action";
		var queryItemData = $("#feeDetailForm").serialize();
		queryItemData+="&isFysj="+$("#ckb1").is(":checked");
		postJSON(url,queryItemData,
				function(json){
					if(!checkJSONResult(json)){
					    return;
				    }
		 			loadHtml(json.data);
				});
	}
	
	function loadHtml(id){
		$("#reportId").val(id);
		$("#reportShow").load("<%=path%>/downloadReportHtmlServlet.download?reportID=" + id,
				function(response, status, xhr) {
					if (status != "success") {
						$("#reportDom").html("加载失败");
						alert("加载失败");
						return;
					}
					powerPrint = $("#reportShow").PowerPrint({
						name : '费用明细请单'
					});
					setTimeout(function(){
						$("#frame_div").attr("style","margin:20px 0;width:100%;height:auto;border:0px solid #00F;overflow:visible;");
					},100);
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
		if(!checkForm){
			return;
		}
		if($("#fromdate").val() > $("#todate").val()){
			alert("开始时间必须小于结束时间!");
			return;
		}
		var url = "<%=path%>/query/BizQueryAction!queryFeeDetailTableYdjy.action";
		var queryItemData = $("#feeDetailForm").serialize();
		queryItemData+="&isFysj="+$("#ckb1").is(":checked");
		queryItemData +="&g_stat_name="+$("#g_stat_name").is(":checked");
		queryItemData +="&g_his_item_name="+$("#g_his_item_name").is(":checked");
		postJSON(url,queryItemData,
				function(json){
					if(!checkJSONResult(json)){
					    return;
				    }
		 			loadHtml(json.data);
				});
	}
	
	$(function(){
		$("#c_groupby input").click(function(){
			var cbid = $(this).attr("id");
			$("#c_groupby input").each(function(i, n){
				var cb = $(n);
				if(cbid != cb.attr("id"))
					cb.attr("checked", false);
			});
			setTimeout(function(){
				btn_fun();
			},50);
		});
		
		//导出按钮
		$("#export").click(function() {
			var bizID = $("#bizId").val();
			if (bizID && "none" != bizID) {
				location.href = rootPath + "/downloadReportFileServlet.download?bizID=" + bizID;
			} else {
				popupAlert("无导出内容。", "提示", "warn");
			}
		});
		
	});
</script>
<body>
		<powersi:form id="feeDetailForm" action="" namespace="/medicare">
		<powersi:hidden id="reportId" name="reportId" />
		<powersi:hidden id="aaz217" name="bizQueryDto.aaz217" />
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
			<div id="c_groupby">明细分组：<input type="checkbox" id="g_stat_name"/>费用类别&nbsp;&nbsp;<input type="checkbox" id="g_his_item_name"/>名称</div>
			<div id="frame_div">
				<div id="reportShow" style="margin-left: 2%; margin-right: -10%;"></div>
			</div>
		</powersi:groupbox>
	</powersi:form>
	<powersi:codetable id="aka063" codeType="aka063"></powersi:codetable>
	<powersi:errors />
</body>
</powersi:html>