<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<powersi:head title="体检费用及结算单查询" />
<body>
	<powersi:form id="quryForm" namespace="/medicare" action="/health/ExaminationAction!findHealthInfo.action">
		<powersi:panelbox key="查询条件">
		
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
				<powersi:button id="btChecklist" label="结算清单" onclick="doPrint()" />
				<powersi:button id="btExport" label="导出" onclick="grid.exportExcel2007()" />
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="5%,15%,10%,8%,10%,10%,10%,9%,8%,16%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bkh012" name="setMealDTO.bkh012"   label="人员类别" cssClass="select2" codeType="bkh012" headerKey="" headerValue="请选择..."  showValue="false" />
					<powersi:codeselect label="结算状态" id="bkh017" name="setMealDTO.bkh017" list="#{'1':'未结算','2':'已结算'}" />
					<powersi:textfield id="bkh016" key="登记日期" name="setMealDTO.aae030" mask="date" />
					<powersi:textfield id="ake010" key="结算日期" name="setMealDTO.aae031" mask="date" />
					<powersi:textfield id="aab069" name="setMealDTO.aab069" label="单位名称"  />	
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="arg_name" label="人员信息" name="setMealDTO.arg_name" cssClass="select2"
						list="#{'':'请选择...','idcard':'身份证号码','indi_id':'个人电脑号','insr_code':'社保卡号','patient_id':'体检登记号'}" />
					<td colspan="2"><powersi:textfield id="querystring" name="setMealDTO.querystring"  buttonText="读取身份证"/></td>
					<powersi:codeselect id="flag"  name="setMealDTO.flag"
					label="查询平台" list="#{'0':'结算云平台','1':'核心业务平台' }"/>
				
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>		
	</powersi:form>
	
	
	<div class="row">
		<div class="col-9" style="width:70%">
			<div id="yyDiv">
				<powersi:panelbox key="panel_result" title="医院业务信息" allowCollapse="false">
					<powersi:datagrid id="grid" formId="quryForm" delayLoad="true" showReload="false" onDblClickRow="onDblClickRow">
						<powersi:datagrid-column key="operate" render="renderOperate"  frozen="true"  width="200"/>
						<powersi:datagrid-column name="akb021" label="医疗机构名称" />
						<powersi:datagrid-column name="bkh012" label="体检类型" />
						<powersi:datagrid-column name="aac003" label="姓名" />
						<powersi:datagrid-column name="aac004" label="性别" code="aac004"/>
						<powersi:datagrid-column name="aac002" label="身份证号" width="150"/>
						<powersi:datagrid-column name="aab069" label="单位名称" />
						<powersi:datagrid-column name="bkh201" label="总费用" />
						<powersi:datagrid-column name="bkh205" label="基金支付" />
						<powersi:datagrid-column name="bkh203" label="个人自付" />
						<powersi:datagrid-column name="bkh016" label="登记日期" />
						<powersi:datagrid-column name="ake010" label="结算日期" />
						<powersi:datagrid-column name="bac001" label="公务员等级" />
						<powersi:datagrid-column name="aac001" label="个人电脑号" />
						<powersi:datagrid-column name="aaz217" label="体检登记号" width="150"/>
				    </powersi:datagrid>
				</powersi:panelbox>
			</div>
		</div>
		<div class="col-3" style="width:30%">
			<powersi:panelbox iconClass="icon-cog" title="费用信息" allowCollapse="false" >
					<powersi:datagrid id="centerCata" formId="" showReload="false" >
						<powersi:datagrid-column name="bkh046" label="费用类别" code="bkh046" />
						<powersi:datagrid-column name="aae019" label="费用"/>	
					</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</div>
	
<powersi:errors />
<script type="text/javascript">
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="查看套餐内" class="linkButton"');
		a.push(' onclick="doQuerySet(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		
		a.push("&nbsp;&nbsp;");
		a.push('<input type="button" value="查看套餐外" class="linkButton"');
		a.push(' onclick="doQueryOut(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		return a.join('');
	}
	
	
	
	function doQuerySet(index){
		var row = grid.getRow(index);
		var center_flag = $("#flag").val();
		popupDialog(
				{
					url : "${rootPath}/health/ExaminationAction!queryCusionInfo.action?setMealDTO.aaz217="+row['aaz217']+"&setMealDTO.flag=0&setMealDTO.center_flag="+center_flag,
					onClosed : function() {
					}
				}, 500, 700); 
		
	}
	
	function doQueryOut(index){
		var center_flag = $("#flag").val();
		var row = grid.getRow(index);
		popupDialog(
				{
					url : "${rootPath}/health/ExaminationAction!queryCusionInfo.action?setMealDTO.aaz217="+row['aaz217']+"&setMealDTO.flag=1&setMealDTO.center_flag="+center_flag,
					onClosed : function() {
					}
				}, 500, 700); 
	}
	
	function onDblClickRow(index){
		var row = grid.getRow(index);
		var aaz217 = row['aaz217'];
		var center_flag = $("#flag").val();
		centerCata.reset();
		postJSON("${rootPath}/health/ExaminationAction!queryBizFeeInfo.action",{
			"setMealDTO.center_flag" : center_flag,
			"setMealDTO.aaz217" 	 : aaz217},function(json){
				if (!checkJSONResult(json)) 
					return;
				centerCata.loadData(json.data);
			});
	}
	
	function doPrint(){
		var row = grid.getSelectedRow();
		if (row == null) {
			alert("请单击一条业务记录！");
			return;
		}
		var aaz217 = row.aaz217;
		var center_flag = $("#flag").val();
		var actionUrl = "${rootPath}/health/ExaminationAction!settlementReport.action"+
		"?setMealDTO.aaz217="+aaz217+"&setMealDTO.center_flag="+center_flag;
		popupDialog(actionUrl, 600, 1000);
		
	}
	
</script>
</body>
</powersi:html>