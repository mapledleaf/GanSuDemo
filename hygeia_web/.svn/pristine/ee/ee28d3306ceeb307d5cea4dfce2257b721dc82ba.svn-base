<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
%>
<powersi:html>
<powersi:codetable id="bka006List" codeType="bka006" />
<powersi:head title="病案信息管理" />
<body>
	<powersi:form id="queryForm" namespace="/query" action="BizQueryAction!queryAllBusinessInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="queryId"  	 label="查  询"		buttonIcon="icon-search"   onclick="hospitalQuery()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,15%,8%,12%,8%,12%,9%,7%,13%">
				<tr>
					<powersi:codeselect codeType="caa027" id="caa027"
						name="bizQueryDto.caa027" label="中心系统" headerKey="0" />
					<powersi:codeselect id="arg_name" label="人员信息" name="bizQueryDto.arg_name"
						list="#{'':'请选择...','idcard':'社会保障号','name':'姓名','serial_no':'就医登记号','patient_id':'医院业务号(住院号)'}" />
					<td colspan="2" class="tdLabel">
						<powersi:textfield id="arg_value" placeholder="请输入信息回车.."
							name="bizQueryDto.arg_value" onkeydown="queryByPersonInfo()" />
					</td>
					<td align="right"><input type="radio" id="clinicDate" name="radio_date" 
						value="rad_fromdate" checked="checked" onchange="changeDatetype()" />
						<label id="inhospID">入院时间</label></td>
					<powersi:textfield id="fromdate" label="开始时间" name="bizQueryDto.fromdate" 
						mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</tr>
				<tr>
					<powersi:codeselect id="zys001" label="在院状态" name="bizQueryDto.zys001" 
						list="#{'1':'在院','2':'出院'}" onchange="changButDis()" />
					<powersi:codeselect codeType="aka130" id="aka130" name="bizQueryDto.aka130"
						 label="业务类型" codeFilter="12" headerKey="0" />
					<powersi:codeselect id="center_flag" onchange="showGrid()" name="bizQueryDto.center_flag"
						 label="查询平台" list="#{'0':'结算云平台','1':'核心业务平台' }" />
					<td align="right" class="tdLabel"> <input type="radio" id="endclinicDate" name="radio_date" 
						value="rad_todate" onchange="changeDatetype()"/>
						<label id="settleDate" for="endclinicDate">结算时间</label></td>
					<powersi:textfield id="todate" label="结束时间" mask="date" 
						name="bizQueryDto.todate" format="dateFmt:'yyyy-MM-dd'" />
				</tr>
				<tr>
					<powersi:codeselect label="结算状态" id="zys002" name="bizQueryDto.zys002"
						list="#{'1':'未结算','2':'已结算'}" onchange="settleStatus()" />
					<powersi:codeselect label="待遇类型" codeType="bka006" id="bka006" 
						name="bizQueryDto.bka006" />
					<powersi:codeselect codeType="aae140" id="aae140" name="bizQueryDto.aae140" label="险种类型" />
					<td colspan="2"></td>
				</tr>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="bizQueryDto.akb020" />
					<powersi:hidden id="aae100" name="bizQueryDto.aae100" value="1" />
					<powersi:hidden id="trade_type" name="bizQueryDto.trade_type" value="zy" />
					<powersi:hidden id="timeType_flag" name="bizQueryDto.timeType_flag" value="1" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<div class="row">
		<powersi:panelbox key="panel_result" title="医院业务信息" allowCollapse="false">
			<powersi:datagrid id="InhospBizGridId" formId="queryForm" delayLoad="true" usePager="true"
				selectRowButtonOnly="true" enabledSort="false" pageSizeOptions="[5,30,50]" >
				<powersi:datagrid-column key="operate" render="renderOperate" width="250" frozen="true" />
				<powersi:datagrid-column name="aac003" label="姓名"	  	 				width="100" frozen="true" />
				<powersi:datagrid-column name="aka130" label="业务类型"	code="aka130"	width="80"  frozen="true" />
				<powersi:datagrid-column name="bka035" label="人员类别"	code="bka035"	width="80"  frozen="true" />
				<powersi:datagrid-column name="aaz217" label="就医登记号"  width="150" />
				<powersi:datagrid-column name="aac001" label="个人电脑号" />
				<powersi:datagrid-column name="aac002" label="公民身份号码" />
				<powersi:datagrid-column name="aac004" label="性别"  	code="aac004" 	width="60" />
				<powersi:datagrid-column name="aae005" label="联系电话" />
				<powersi:datagrid-column name="aaa027" label="拨付中心"	code="aaa027" />
				<powersi:datagrid-column name="bka006" label="待遇类别" code="bka006" render="_render" />
				<powersi:datagrid-column name="aae030" label="入院时间"  format="{0,date,yyyy-MM-dd}" />
				<powersi:datagrid-column name="bkz101" label="入院诊断" />
				<powersi:datagrid-column name="aae031" label="出院时间"  format="{0,date,yyyy-MM-dd}" />
				<powersi:datagrid-column name="bkz102" label="出院诊断" />
				<powersi:datagrid-column name="akc190" label="住院号"   />
				<powersi:datagrid-column name="bka022" label="病区"			width="80" />
				<powersi:datagrid-column name="bka020" label="科室"			width="80" />
				<powersi:datagrid-column name="ake020" label="床位号"		width="80" />
				<powersi:datagrid-column name="bka008" label="单位名称" />
				<powersi:datagrid-column name="akc194" label="结算时间"		format="{0,date,yyyy-MM-dd}" />
				<powersi:datagrid-column name="baa027" label="人员所属中心"	width="80" code="aaa027"  />
				<powersi:datagrid-column name="bka034" label="结算人"	   />
			</powersi:datagrid>
		</powersi:panelbox>
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
		settleStatus();
		changButDis();
	}
	
	function showGrid(){
		hospitalQuery();
	}
	
	function hospitalQuery(){
		$("#queryForm").submit();
	}
	
	function queryByPersonInfo() {
		if (window.event.keyCode == 13) {
			hospitalQuery();
		}
	}
	
	function exportData(){
		InhospBizGridId.exportExcel();
	}
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="查看病案首页信息" class="linkButton"');
		a.push(' onclick="openEditPage(');
		a.push(index);
		a.push(')"');
		//a.push(' disabled="disabled"');
		a.push(" />");
		a.push("&nbsp;&nbsp;");
		a.push('<input type="button" value="录入病案首页信息" class="linkButton"');
		a.push(' onclick="openNewPage(');
		a.push(index);
		a.push(')"');
		//a.push(' disabled="disabled"');
		a.push(" />");
		return a.join('');
	}
	
	function openNewPage(index){
		var row = InhospBizGridId.getRow(index);
		var aaz217 = row.aaz217;
		var akb020 = row.akb020;
		popupDialog({
			url: "${rootPath}/medicalpage/MedicalPageAction!queryMedicalInfo.action?medicalBasisDto.aaz217="+
				aaz217+"&medicalBasisDto.akb020="+akb020+"&medicalBasisDto.finflag=1",
			onClosed: function() {
				InhospBizGridId.reload();
			}
		},screen.height, screen.width);
	}
	
	function openEditPage(index){
		var row = InhospBizGridId.getRow(index);
		var aaz217 = row.aaz217;
		var akb020 = row.akb020;
		popupDialog({
			url: "${rootPath}/medicalpage/MedicalPageAction!queryMedicalInfoByEdit.action?medicalBasisDto.aaz217="+
				aaz217+"&medicalBasisDto.akb020="+akb020+"&medicalBasisDto.finflag=0",
			onClosed: function() {
				InhospBizGridId.reload();
			}
		}, screen.height, screen.width);
	}
	
	//出院、入院显示控制
	function changButDis() {
		var zys001 = $("#zys001 option:selected").val();
		if(zys001 == "1" || zys001 == ""){
			$("#inhospID").text("入院时间");
			$("#zys002").empty();
			$("#zys002").append("<option value='1' >" + "未结算" + "</option>");
			//在院状态将结算时间控制为未结算
			$("#zys002").val("1");
			$("#timeType_flag").val("1");
			settleStatus();
		}else {
			$("#inhospID").text("出院时间");
			$("#zys002").empty();
			$("#zys002").append("<option value='1' checked >" + "未结算" + "</option>");
			$("#zys002").append("<option value='2' >" + "已结算" + "</option>");
			$("#zys002").attr("disabled", false);
			$("#timeType_flag").val("2");
		}
		
	}
	//结算时间的显示控制
	function settleStatus() {
		var zys001 = $("#zys001 option:selected").val();
		if ($("#zys002").val() == "1") {
			$("#endclinicDate").attr("disabled", true);
			$("#settleDate").attr("disabled", true);
			$("#clinicDate").attr("checked",true);
			if(zys001=="1")
				$("#timeType_flag").val("1");
			else
				$("#timeType_flag").val("2");
		} else {
			$("#endclinicDate").attr("disabled", false);
			$("#settleDate").attr("disabled", false);
			$("#settleId").attr("disabled", false);
			$("#timeType_flag").val("02");
		}
	}
	
	function changeDatetype() {
		var rad_value = $(':radio[name="radio_date"]:checked').val();
		var zys001 = $("#zys001 option:selected").val();
		var zys002 = $("#zys002 option:selected").val();
		if('rad_fromdate'==rad_value){
			if(zys001=="1")
				$("#timeType_flag").val("1");//入院时间
			if(zys001=="2"){
				if(zys002=="1")
					$("#timeType_flag").val("2");//出院时间
					else
						$("#timeType_flag").val("02");//出院时间
			}
		}
		if('rad_todate'==rad_value){
			$("#timeType_flag").val("03");
		}
	}

	//渲染金额格式
	function renderFormatMoney(rowdata,index,value,arguments) {
		if(value!=null&&value !=""){
			value = powersi.tonumber(value).toFixed(2);
			return value;
		}else{
			return "";
		}
	}
	
	/* 字典项渲染函数  解决loadData不能自动加载字典问题*/
	function renderDictionary(rowdata, index, value,arguments) {
		if(value!=null&&value !=""){
			var list = window[arguments.code];
			for(var i in list){
				if(list[i].id == value  ){
					return list[i].text;
				}
			}
			return '<span title="未匹配到字典项">'+value+'</span>';
		}else{
			return "";
		}
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