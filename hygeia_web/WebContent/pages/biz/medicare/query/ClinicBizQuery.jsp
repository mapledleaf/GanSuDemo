<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	//购药信息和门诊信息、门特信息共用一个页面
	//TS19051500118  医院端--医院管理子系统--医疗信息查询--住院（门诊、门特、特药）费用查询，查询结果需要增加两列，显示居民所在的乡镇、村 组   
	//AAF014 乡镇街道 
    //AAZ071 村社区
    //BAF005 组 cj 20190515
    
    //TS19061900163  住院信息查询 界面增加一列信息（补助类型aac148）。
    //陈洁 20190627
    String aka130=request.getParameter("aka130");
	System.out.println("aka130: " + aka130);
    String codeFilter=" data_value IN ("+aka130+")";
    String codeValue=aka130.substring(0,2);
    String title = codeValue.equals("11")?"'门诊信息查询'":codeValue.equals("10")?"'药店信息查询'":codeValue.equals("13")?"'门特信息查询'":"'业务信息查询'";
	String hospital_id = BizHelper.getAkb020();
    String hospital_name = BizHelper.getAkb021();
    String aaa027 = BizHelper.getAaa027();
    String trade_type = request.getParameter("trade_type");//业务类型：yd 药店信息  mz 门诊信息    mt 门特信息
	String bka006 = "";
    if("10".equals(codeValue)){
    	bka006 = "DATA_VALUE IN ('100')";
    }else if("11".equals(codeValue)){
    	bka006 = "DATA_VALUE IN ('110','114')";
    }else if("13".equals(codeValue)){
    	bka006 = "DATA_VALUE IN ('131')";
    }else if("18".equals(codeValue)){
    	bka006 = "DATA_VALUE IN ('180')";
    }
%>
<powersi:html>
<powersi:head title="门诊类业务信息查询" />
<body>
	<powersi:form id="queryForm" namespace="/query" action="BizQueryAction!queryAllBusinessInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="queryId"  	  label="查询(Q)"		buttonIcon="icon-search"   onclick="hospitalQuery()"/>
				<powersi:button id="settlementId" label="结算清单(F)"  	buttonIcon="icon-ticket"   onclick="settlementReportPrint()"/>
				<powersi:button id="costId"       label="费用清单(P)" 	buttonIcon="icon-ticket"   onclick="feeDetailTable()"/>
				<powersi:button id="fundId" 	  label="基金查询(F)"	buttonIcon="icon-money"    onclick="fundStatusInfo()"/>
				<powersi:button id="exportId"	  label="导出(E)" 	    buttonIcon="icon-download" onclick="exportData()"/>
				<powersi:button id="printing"	  label="发票套打(E)" 	buttonIcon="icon-download" onclick="printingData()"/>				
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,15%,8%,12%,8%,12%,9%,7%,13%">
				<tr>
					<powersi:codeselect codeType="caa027" id="caa027" name="bizQueryDto.caa027"
						 label="中心系统" headerKey="0"/>
					<powersi:codeselect id="arg_name" label="人员信息"  name="bizQueryDto.arg_name"
					 list="#{'':'请选择...','idcard':'社会保障号','name':'姓名','serial_no':'就医登记号','patient_id':'医院业务号(住院号)'}" />
					<td colspan="2" class="tdLabel" >
						<powersi:textfield id="arg_value" placeholder="请输入信息回车.." 
						name="bizQueryDto.arg_value" onkeydown="queryByPersonInfo()"/></td>
					<td align="right"><input type="radio" id="clinicDate"
						name="radio_date" value="rad_fromdate" 
						checked="checked" onchange="changeDatetype()"/>就诊时间</td>
					<powersi:textfield id="fromdate" label="开始时间"
						name="bizQueryDto.fromdate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</tr>
				<powersi:editorlayout-row>
					<powersi:codeselect codeType="aka130" id="aka130" name="bizQueryDto.aka130" headerKey="0"
						 label="业务类型" codeFilter="<%=codeFilter %>" value="<%=codeValue %>"/>
					<powersi:codeselect label="待遇类型" id="bka006" codeType="bka006"
						name="bizQueryDto.bka006" codeFilter="<%=bka006 %>"/>
			        <powersi:codeselect id="center_flag" onchange="showGrid(this.value)"
						name="bizQueryDto.center_flag" label="查询平台"
						list="#{'0':'结算云平台','1':'核心业务平台' }" />
			        <td align="right"><input type="radio" id="endclinicDate"
						name="radio_date" value="rad_todate" 
						onchange="changeDatetype()"/>结算时间</td>
					<powersi:textfield id="todate" label="结束时间"
						name="bizQueryDto.todate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect codeType="aae140" id="aae140" name="bizQueryDto.aae140" label="险种类型" />
					<powersi:codeselect id="baa027" name="bizQueryDto.baa027" label="参保人统筹区"
						codeType="aaa027" cssClass="select2" />
					<% if("13".equals(codeValue)){ %>
						<powersi:codeselect id="bke054" name="bizQueryDto.bke054" label="一站式标志"
						codeType="bke054" cssClass="select2" />
					<% } %>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
	 				<powersi:hidden id="akb020" name="bizQueryDto.akb020" />
					<powersi:hidden id="aae100" name="bizQueryDto.aae100" value="1"/>
					<powersi:hidden id="trade_type" name="bizQueryDto.trade_type" value="<%=trade_type %>" />
					<powersi:hidden id="timeType_flag" name="bizQueryDto.timeType_flag" value="1" />
					<powersi:hidden id="zfytotal" />
					<powersi:hidden id="zrstotal" />
					<powersi:hidden id="yydfpaytotal" />
					<powersi:hidden id="grzhpaytotal" />
					<powersi:hidden id="xjpaytotal" />
					<powersi:hidden id="sypaytotal" />
					<powersi:hidden id="tcpaytotal" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<div class="row">
		<div class="col-9">
			<powersi:panelbox key="panel_result" title="医院业务信息" allowCollapse="false">
				<powersi:datagrid id="ClinicbizGridId" formId="queryForm" delayLoad="true" usePager="true"  
					enabledSort="false" selectRowButtonOnly="true"  onDblClickRow="onDblClickRowQuery"
					totalRender="renderTotal" showExportExcel2007="false" exportFileName="<%=title%>" >
					<powersi:datagrid-column key="operate" render="renderOperate"
						width="50" frozen="true" />
					<powersi:datagrid-column name="aac003" label="姓名" width="100" frozen="true"/>
					<powersi:datagrid-column name="aka130" label="业务类型" code="aka130" width="80" frozen="true"/>
					<powersi:datagrid-column name="bka035" label="人员类别" code="bka035" width="80" frozen="true"/>
					<powersi:datagrid-column name="aaz217" label="就医登记号" width="150"/>
					<powersi:datagrid-column name="aac001" label="个人电脑号" />
					<powersi:datagrid-column name="aac002" label="公民身份号码" width="150"/>
					<powersi:datagrid-column name="aac004" label="性别" code="aac004" width="60"/>
					<powersi:datagrid-column name="allpay" label="总费用" width="60" render="renderFormatMoney"/>
					<powersi:datagrid-column name="fund_pay" label="统筹支付" width="60" render="renderFormatMoney"/>
					<powersi:datagrid-column name="db_pay" label="大病支付" width="60" render="renderFormatMoney"/>
					<powersi:datagrid-column name="jiating_pay" label="家庭账户支付" width="60" render="renderFormatMoney"/>
					<powersi:datagrid-column name="self_pay" label="现金支付" width="60" render="renderFormatMoney"/>
					<powersi:datagrid-column name="acct_pay" label="账户金支付" width="100" render="renderFormatMoney"/>
					<%if(codeValue.equals("11")){%>
						<powersi:datagrid-column name="shengyu_pay" label="生育基金支付" 	 width="100" render="renderFormatMoney"/>
						<powersi:datagrid-column name="lixiu_pay" label="离休基金支付金额" width="100" render="renderFormatMoney"/>
					<% } %>
					<%if(codeValue.equals("13")){%>
						<powersi:datagrid-column name="official_pay" label="公务员基金支付" 	 width="100" render="renderFormatMoney"/>
					<% } %>
					<powersi:datagrid-column name="real_pay" label="医保记账金额" render="renderFormatMoney"/>
					<powersi:datagrid-column name="aae030" label="就诊日期" format="{0,date,yyyy-MM-dd}"/>
					<powersi:datagrid-column name="bka006" label="待遇类别" code="bka006" />
					<powersi:datagrid-column name="bkz101" label="疾病诊断" />
					<powersi:datagrid-column name="bka008" label="单位名称" />
					<powersi:datagrid-column name="akc194" label="结算时间" format="{0,date,yyyy-MM-dd}" />
					<powersi:datagrid-column name="bka015" label="操作人" />
					<powersi:datagrid-column name="aaa027" label="辖区" code="aaa027" width="160" />
					<powersi:datagrid-column name="aaf014" label="乡镇/街道" />
					<powersi:datagrid-column name="aaz071" label="村/社区" />
					<powersi:datagrid-column name="baf005" label="组" />
					<powersi:datagrid-column name="aac148" label="补助类型"  code="aac148"/>
					<powersi:datagrid-column name="aka030" label="报账类型" />
					<%if(codeValue.equals("13")){%>
						<powersi:datagrid-column name="bkc290" label="指静脉认证结果" width="100" render="renderBkc290"/>
					<% } %>
					<%if(codeValue.equals("10")){%>
						<powersi:datagrid-column name="baf313" label="联系人" width="100"/>
						<powersi:datagrid-column name="aae005" label="联系电话" width="100"/>
					<% } %>
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
    	<div class="col-3">
	  		<powersi:panelbox iconClass="icon-cog" title="费用信息" allowCollapse="false">
				<powersi:datagrid id="ClinicfeeGridId"  delayLoad="true" showReload="false" 
					totalRender="feeTotal" onDblClickRow="feeDetailTable">
					<powersi:datagrid-column name="aka063" label="费用类别" code="aka063" width="100" />
					<powersi:datagrid-column name="aae019" label="费用" width="80"/>
				</powersi:datagrid>
			</powersi:panelbox>
	    </div>
    </div>
	<powersi:errors />
</body>
<script type="text/javascript">
	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");
		$('#akb021').val("<%=hospital_name%>");
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$("#fromdate").val(year+"-"+month+"-"+"01");
		$("#todate").val(year+"-"+month+"-"+day);
	}
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="照片" class="linkButton"');
		a.push(' onclick="showImage(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		return a.join('');
	}
	
	function showImage(index){
		var row = ClinicbizGridId.getRow(index);
		var kc21id =  row['kc21id'];
		var aaz217 =  row['aaz217'];
		popupDialog({
				url: "${rootPath}/query/BizQueryAction!showImage.action?bizQueryDto.kc21id="+kc21id+
						"&bizQueryDto.aaz217="+aaz217,
				onClosed: function() {
					 
				}
			}, 180, 300);
	}
	
	//查询按钮
	function hospitalQuery(){
		var saveItemData = $("#queryForm").serialize();
		var url = "${rootPath}/query/BizQueryAction!querySum.action";
		postJSON(url,saveItemData,function(json){
			if(!checkJSONResult(json)){
				return;
			}
			$.each(json.data, function(key, value) {
				$("#" + key).val(value);
			});
			ClinicfeeGridId.reset();
			$("#queryForm").submit();
		});
	}

	//双击行查询费用
	function onDblClickRowQuery(index){
		var row = ClinicbizGridId.getRow(index);
		var kc21id =  row['kc21id'];
		var aaz217 = row.aaz217;
		var caa027 = $("#caa027").val();
		var center_flag = $("#center_flag").val();
		postJSON("${rootPath}/query/BizQueryAction!queryBizFeeInfo.action",{
					"bizQueryDto.kc21id" : kc21id,
					"bizQueryDto.caa027" : caa027,
					"bizQueryDto.aaz217" : aaz217,
					"bizQueryDto.center_flag" : center_flag },function (json){
						 if(!checkJSONResult(json)){
							    return;
						 	}
						ClinicfeeGridId.loadData(json.data);
					 });
	}
	
	
	//查费用清单
	function feeDetailTable(){
		var row= ClinicbizGridId.getSelectedRow();
		if (row == null){
			popupAlert("请选择一条业务记录！", "提示", "info");
			return;
		}
		var aaz217 = row.aaz217;
		var caa027 = $("#caa027").val();
		var center_flag =$('#center_flag').val();
		
		var actionUrl = "${rootPath}/query/BizQueryAction!queryFeeDetailTable.action?bizQueryDto.aaz217="+aaz217+
			"&bizQueryDto.center_flag="+center_flag+"&bizQueryDto.caa027="+caa027;
		popupDialog(actionUrl,500,1000);
	}
	
	//打印结算单
	function settlementReportPrint() {
		var row= ClinicbizGridId.getSelectedRow();
		if (row == null) {
			popupAlert("请双击一条业务记录！", "提示", "info");
			return;
		}
		var aaz217 = row.aaz217;
		if (powersi.isnull(aaz217)) {
			return;
		}
		var center_flag = $("#center_flag").val();
		popupDialog(
				{
					url : "${rootPath}/common/SettlementReportNewCenterAction!settlementReport.action?inHospitalDTO.aaz217="
							+ aaz217+"&inHospitalDTO.caa027="+$("#caa027").val()
							+"&inHospitalDTO.center_flag="+center_flag,
					onClosed : function() {

					}
				}, 600, 800);
	}
	
	//发票套打
	function printingData() {
		 var row= ClinicbizGridId.getSelectedRow();
		if (row == null) {
			popupAlert("请双击一条业务记录！", "提示", "info");
			return;
		}
		var aaz217 = row.aaz217; 
		if (powersi.isnull(aaz217)) {
			return;
		}
		 popupDialog(
				{
					url : "${rootPath}/common/BillReportAction!printingReport.action?kab3DTO.aaz217="+ aaz217+"&kab3DTO.bka445=1",
					onClosed : function() {
					}
				}, 600, 800); 
		
		/* postJSON("${rootPath}/common/BillReportAction!printingReport.action?kab3DTO.aaz217="+ aaz217+"&kab3DTO.bka445=1",function (json){
				 if(!checkJSONResult(json)){
					    return;
				 	}
			 }); */
	}
	
	//基金状态查询
	function fundStatusInfo(){
		var row = ClinicbizGridId.getSelectedRow();
		if (row == null) {
			popupAlert("请选择要查询的数据!", "提示", "info");
			return;
		}
		var aac001 = row.aac001;
		var aae140 = powersi.trim($("#aae140").val());
		popupDialog(
				{url : "${rootPath}/common/CommonManagerAction!queryPersonFund.action?inHospitalDTO.aac001="
						+ aac001 + "&inHospitalDTO.aae140=" + aae140+"&inHospitalDTO.aka130=11&inHospitalDTO.caa027="+$("#caa027").val(),
				}, 400, 700);
	}
	
	//导出execl
	function exportData(){
		ClinicbizGridId.exportExcel();
	}
	
	//切换查询的时间类型时触发
	function changeDatetype() {
		var rad_value = $(':radio[name="radio_date"]:checked').val();
		if('rad_fromdate'==rad_value){
			$("#timeType_flag").val("1");
		}
		if('rad_todate'==rad_value){
			$("#timeType_flag").val("03");
		}
	}
	
	//切换查询平台时触发
	function showGrid(index){
		if("0"== $("#center_flag").val()){
			$("#printing").show();
		}else{
			$("#printing").hide();
		}
		//hospitalQuery();
	}
	
	//人员信息录入框回车键
	function queryByPersonInfo() {
		if (window.event.keyCode == 13) {
			hospitalQuery();
		}
	}
	
	function renderFormatMoney(rowdata,index,value,arguments) {
		if(value!=null&&value !=""){
			value = powersi.tonumber(value).toFixed(2);
			return value;
		}else{
			return "";
		}
	}
	
	function feeTotal(allData, currentData){
		var sum = 0;
		$.each(allData['rows'], function(n, row){
			if(ClinicfeeGridId.getRowStatus(row) != 'delete'){
				sum = FloatAdd(sum, row.aae019);
			}
		});
		var a= [];
		
		a.push('<div class="textCenter">');
		
		a.push('费用总计：');
    	a.push(sum);
    	a.push(' 元');
    	a.push('</b> ');
		a.push('</span>');
		a.push('</div>');
		return a.join('');
	}
	
	function renderBkc290(rowdata, index, value){
		if(value=="1") 
			return "认证通过";
		else if(value=="2") 
			return "认证不通过";
		else return "未采集";
	}
	
	function renderTotal(allData, currentData)
	{
		var a = [];
		a.push('<div class="divCenter textSuccess">');
		
		a.push('<span>');
		a.push('总费用合计：<b>');
		a.push($("#zfytotal").val());
		a.push('</b>');
		a.push('</span>');
		
		a.push('&nbsp;&nbsp;');
		
		a.push('<span>');
		a.push('总人数：<b>');
		a.push($("#zrstotal").val());
		a.push('</b>');
		a.push('</span>');
		
		/* a.push('&nbsp;&nbsp;');
		
		a.push('<span>');
		a.push('医院垫付合计：<b>');
		a.push($("#yydfpaytotal").val());
		a.push('</b>');
		a.push('</span>'); */
        a.push('&nbsp;&nbsp;');		
        a.push('<span>');
		a.push('个人账户支付合计：<b>');
		a.push($("#grzhpaytotal").val());
		a.push('</b>');
		a.push('</span>');
		a.push('&nbsp;&nbsp;');		
		a.push('<span>');
		a.push('现金支付合计：<b>');
		a.push($("#xjpaytotal").val());
		a.push('</b>');
		a.push('</span>');		
		if($("#aka130").val()=="51"){
			a.push('&nbsp;&nbsp;');		
			a.push('<span>');
			a.push('生育基金支付：<b>');
			a.push($("#sypaytotal").val());
			a.push('</b>');
			a.push('</span>');
		}
		if($("#aka130").val()=="13"){
			a.push('&nbsp;&nbsp;');		
			a.push('<span>');
			a.push('统筹基金支付：<b>');
			a.push($("#tcpaytotal").val());
			a.push('</b>');
			a.push('</span>');
		}
	  	return a.join('');
	}
	
	function FloatAdd(arg1, arg2) {
		var r1, r2, m;
		try {
			r1 = arg1.toString().split(".")[1].length
		} catch (e) {
			r1 = 0
		}
		try {
			r2 = arg2.toString().split(".")[1].length
		} catch (e) {
			r2 = 0
		}
		m = Math.pow(10, Math.max(r1, r2))
		return to2bits((arg1 * m + arg2 * m) / m, 2);
	}

	function to2bits(flt, pos) {
		var rd = 1;
		for (var i = 1; i <= parseInt(pos); i++) {
			rd = rd * 10;
		}
		if (parseFloat(flt) == flt)
			return Math.round(flt * rd) / rd;
		else
			return 0;
	}
</script>
</powersi:html>