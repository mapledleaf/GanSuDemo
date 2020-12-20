<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<script type="text/javascript" src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript" src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
/* 	java.util.Date d = new java.util.Date();
	java.util.Date toDate = d;
	d = DateFunc.addMonths(d, -1);
	java.util.Date fromDate = DateFunc.getFirstDateOfMonth(d);

	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String strFromDate = dformat.format(fromDate);
	String strToDate = dformat.format(toDate); */
%>
<powersi:html>
<head>
<powersi:head title="门诊特定病种查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="MzchoHospitalBusApplyAction!queryDiseaseInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" key="button_query" onclick="search()"/>
				<powersi:button id="button_printout" key="打印登记表" onclick="printOut()"></powersi:button> 
				<powersi:button id="button_printout" key="导出登记表" onclick="grid.exportExcel2007()()"></powersi:button> 
				<powersi:button id="button_cancel" key="取消登记" onclick="cancelInfo()"></powersi:button>	
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="bka007" name="mediSpecZHDto.bka007"  key="bka007" label="病种名称" />
					<powersi:codeselect id="aae016" name="mediSpecZHDto.aae016" key="aae016" codeType="aae016_specail" label="审核标志" />
					<powersi:textfield id="aae030" name="mediSpecZHDto.aae030" label="病种开始时间"  mask="date" format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="aae031" name="mediSpecZHDto.aae031" label="病种结束时间"  mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aac002" name="mediSpecZHDto.aac002"  key="aac002" label="身份证号" />
					<powersi:textfield id="aae037" name="mediSpecZHDto.aae037" label="经办开始时间"  mask="date" format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="aae038" name="mediSpecZHDto.aae038" label="经办结束时间"  mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="expertInfoDto.akb020" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="病种列表">
		<powersi:datagrid id="grid" formId="queryForm"
					selectRowButtonOnly="true"  title="珠海市医疗保险门诊特定病种申报认定登记表"
					showExportExcel2007="true" exportFileName="'珠海市医疗保险门诊特定病种申报认定登记表'">
			<powersi:datagrid-column name="ace001" 		key="ace001" 	  label="编号"      		 			  />
			<powersi:datagrid-column name="aac003" 		key="aac003" 	  label="姓名"      					  />
			<powersi:datagrid-column name="aac002" 		key="aac002" 	  label="身份证号" 					  />
			<powersi:datagrid-column name="aae005" 		key="aae005" 	  label="联系电话"      				  />						
			<powersi:datagrid-column name="bka007" 		key="bka007" 	  label="病种名称"  					  />
			<powersi:datagrid-column name="aka121" 		key="aka121" 	  label="疾病诊断"  					  />
			<powersi:datagrid-column name="aae030" 		key="aae030" 	  label="病种开始时间" 					  />
			<powersi:datagrid-column name="aae031" 		key="aae031" 	  label="病种结束时间" 					  />
			<powersi:datagrid-column name="aaa000" 		key="aaa000"      label="认定专家" 				      />
			<powersi:datagrid-column name="aae140_name" key="aae140_name" label="参保险种"  				      />
			<powersi:datagrid-column name="aae100" 		key="aae100" 	  label="是否有效" code="valid_flag"  	  />
			<powersi:datagrid-column name="aae036" 		key="aae036" 	  label="经办时间" 					  />
			<powersi:datagrid-column name="aae016" 		key="aae016" 	  label="审核标志" code="aae016_specail" />
		</powersi:datagrid>
	</powersi:panelbox>

	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
   <%-- $('#aae030').val('<%=strFromDate%>');
		$('#aae031').val('<%=strToDate%>');   --%>
		$('#akb020').val("<%=hospital_id%>");		
		if($("#akb020").val() == '' || $("#akb020").val() == null ){
			popupAlert("医院编码不能为空，请检查登陆信息！");
	    	return;
		}
		
		var hospital_id = $("#akb020").val();
		//刷新
		grid.reload(true);
	}
	
	function search(){
		$("#queryForm").submit();
	}
	
	 
	//打印导出
	function printOut(){
		var bka007=$("#bka007").val();
		var aae016=$("#aae016").val();
		var aae030=$("#aae030").val();
		var aae031=$("#aae031").val();
		var aac002=$("#aac002").val();
		var aae037=$("#aae037").val();
		var aae038=$("#aae038").val();
		popupDialog(
				{
					url : "${rootPath}/medicare/MzchoHospitalBusApplyAction!printOut.action?mediSpecZHDto.bka007="+encodeURI(encodeURI(bka007))
							+"&mediSpecZHDto.aae016="+aae016+"&mediSpecZHDto.aae030="+aae030+"&mediSpecZHDto.aae031="+aae031+"&mediSpecZHDto.aac002="+aac002
							+"&mediSpecZHDto.aae037="+aae037+"&mediSpecZHDto.aae038="+aae038,
					onClosed : function() {
						
					}
				}, 500, 800);
	} 
	
	
	/*取消登记*/
	function cancelInfo(){
		var row = grid.getSelected();
		if(row==null){
			popupAlert("请先选择一行数据！");
		}
		var ace001=row['ace001'];
		var aac002=row['aac002'];
		if(!confirm("是否取消当前病种登记信息!")){
			return;
		}
		postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!cancelInfo.action",
				{
				"mediSpecZHDto.ace001" : ace001,
				"mediSpecZHDto.aac002" : aac002
				}, 
				function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);							
						grid.reload();	
					});
			}	
	</script>
</body>
</powersi:html>