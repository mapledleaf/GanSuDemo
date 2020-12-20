<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%
	String path = request.getContextPath(); 

	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyyMMdd");
	String time = dformat.format(d); 
	String str_date_beg_p = time.substring(0,6)+"01";
	String str_date_end_p = time;
%>
<powersi:html>
<base target="_self">
<head>
	<powersi:head title="村卫生室结算拨付" />
</head>
<powersi:codetable id="aka130List" codeType="aka130" />
<powersi:codetable id="bka006List" codeType="bka006" />
<powersi:codetable id="bka035List" codeType="bka035" />
<powersi:codetable id="aaa157List" codeType="aaa157" />
<powersi:codetable id="aaa027List" codeType="aaa027" />
<body>
	<powersi:form id="mainForm" name="mainForm" action="MedicalVillagePayAction!getPayData.action" namespace="/medicarepay">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btQuery" key="button_query" value="查询" onclick="query()" title="Alt+Q" data-hotkey="Alt+Q"/>
			</powersi:panelbox-toolbar>
			<powersi:hidden id="aae140" name="dto.aae140" value="390"/>
			<powersi:hidden id="bizinfo" name="dto.bizinfo" />
			<powersi:hidden id="bke228" name="dto.bke228" />
			<powersi:editorlayout cols="8">	
				<tr>
					<td align="right" class=tdLabel>结算起止时间</td>
					<td colspan="3">	
						<input type="text" id="aae030" name="dto.aae030" value="<%=str_date_beg_p %>"  style="display:none;">
						<input type="text" id="aae031" name="dto.aae031" value="<%=str_date_end_p %>"  style="display:none;">
						<input type="text" id="startField" placeholder="aae030" style="display:none;">
						<input type="text" id="endField" placeholder="aae031" style="display:none;">
						<powersi:date id="dateRange" drops="down" mask="date"
							startField="aae030" endField="aae031">
							<powersi:date-range label="今天" startDate="moment()" endDate="moment()" />
							<powersi:date-range label="昨天"
								startDate="moment().subtract(1, 'days')" endDate="moment().subtract(1, 'days')" />
							<powersi:date-range label="最近三天"
								startDate="moment().subtract(2, 'days')" endDate="moment()" />
							<powersi:date-range label="最近一周"
								startDate="moment().subtract(6, 'days')" endDate="moment()" />
							<powersi:date-range label="本周"
								startDate="moment().startOf('week')" endDate="moment()" />
							<powersi:date-range label="本月"
								startDate="moment().startOf('month')" endDate="moment()" />
							<powersi:date-range label="上个月"
								startDate="moment().subtract(1, 'months').startOf('month')"
								endDate="moment().subtract(1, 'months').endOf('month')" />
						</powersi:date>
					</td>
					<td align="right" class=tdLabel>拨付状态</td>
					<td>
						<select id="bkc008"  style= "width:80 " name="dto.bkc008" onchange="do_change()">
							<option value="0" selected="selected">未拨付</option>
							<option value="1">已拨付</option>
						</select>
					</td>
					<td colspan="2"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox key="panel_result">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSave" key="button_save" value="保 存"  onclick="doSave()" title="Alt+S" data-hotkey="Alt+S"/>	
				<powersi:button id="btCancel" key="button_cancel" value="取消"  onclick="doCancel()" disabled="true" title="Alt+D" data-hotkey="Alt+D" />
				<powersi:button id="btPrint" key="button_print" value="打印拨付单"  onclick="doPrint()" disabled="true" title="Alt+P" data-hotkey="Alt+P" />	
			</powersi:panelbox-toolbar>
			<powersi:tabbedpanel  id="divTabs">
				<powersi:tab id="tab1" target="divTab1" label="基金结算信息" />
				<powersi:tab id="tab2" target="divTab2" label="基金结算信息" />
				<powersi:tab id="tab3" target="divTab3" label="基金结算表" />	
				<powersi:tab id="tab4" target="divTab4" label="业务明细" />			
				<div id="divTab1">
					<powersi:datagrid id="dataList" name="dataList" showExportExcel="true" showReload="false" widthDiff="-5" 
						 checkbox="true" rowDraggable="false"  usePager="false" >
						<powersi:datagrid-column display="姓名" name="aac003" />
						<powersi:datagrid-column display="医院名称" name="akb021" align="left" />
						<powersi:datagrid-column display="人员类别" name="bka035" render="renderCode" />
						<powersi:datagrid-column display="业务类型" name="aka130" render="renderCode" />
						<powersi:datagrid-column display="待遇类型" name="bka006" render="renderCode" />
						<powersi:datagrid-column display="总金额" name="akc264" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="基金支出金额" name="akc266" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="结算日期" name="akc194" />
						<powersi:datagrid-column display="诊断疾病" name="bkz102" align="left"/>
						<powersi:datagrid-column display="床日数" name="bka030" align="right" totalSummary="{'render': renderCount}" />
						<powersi:datagrid-column display="拨付中心" name="daa027" align="left" render="renderCode" />
						<powersi:datagrid-column display="业务经办中心" name="aaa027" align="left" render="renderCode" />
						<powersi:datagrid-column display="人员所属中心" name="baa027" align="left" render="renderCode" />
						<powersi:datagrid-column display="登账号" name="bke100" />
						<powersi:datagrid-column display="单位id" name="aab001" hide="true" />
						<powersi:datagrid-column display="人员id" name="aac001" hide="true" />
						<powersi:datagrid-column display="医院编码" name="akb020" hide="true" />
					</powersi:datagrid>
				</div>
				<div id="divTab2" >
					<powersi:datagrid id="payList" name="payList" showExportExcel="true" showReload="false" widthDiff="-5" 
						 checkbox="true" rowDraggable="false"  usePager="false" >
						<powersi:datagrid-column key="operate" width="80" frozen="true" isSort="false" isExport="false" render="renderOperate" />
						<powersi:datagrid-column display="拨付单号" name="bke228" />
						<powersi:datagrid-column display="乡镇卫生院名称" name="bke130" align="left" />
						<powersi:datagrid-column display="结算人次" name="pay_rc" align="right" totalSummary="{'render': renderCount}" />
						<powersi:datagrid-column display="医疗总费用" name="akc264" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="登账金额" name="akc266" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="审核扣减金额" name="akc265" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="实际拨付金额" name="akb081" align="right" totalSummary="{'render': renderSum}" />
					</powersi:datagrid>
				</div>
				<div id="divTab3">
					<div class="frame_div">
						<iframe id="report_0" style="width: 90%;"></iframe>
					</div>
				</div>
				<div id="divTab4">
					<powersi:datagrid id="bizList" name="bizList" showExportExcel="true" showReload="false" widthDiff="-5" 
						 checkbox="false" rowDraggable="true" pageSize="100">
						<powersi:datagrid-column display="个人编号" name="aac001" />
						<powersi:datagrid-column display="姓名" name="aac003" />
						<powersi:datagrid-column display="医院名称" name="akb021" align="left" />
						<powersi:datagrid-column display="总金额" name="all_money" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="记账金额" name="reg_money" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="现金" name="cash_money" align="right" totalSummary="{'render': renderSum}" />
						<powersi:datagrid-column display="结算日期" name="akc194" />
						<powersi:datagrid-column display="人员类别" name="bka035"  render="renderCode"/>
						<powersi:datagrid-column display="业务类型" name="aka130"  render="renderCode"/>
						<powersi:datagrid-column display="待遇类型" name="bka006"  render="renderCode"/>
						<powersi:datagrid-column display="诊断疾病" name="bkz102" align="left"/>
						<powersi:datagrid-column display="床日数" name="bka030" align="right" totalSummary="{'render': renderCount}" />
						<powersi:datagrid-column display="业务序列号" name="aaz217" />
					</powersi:datagrid>
				</div>
			</powersi:tabbedpanel>
		</powersi:panelbox>
	</powersi:form>
  	<powersi:errors />
</body>
<script type="text/javascript">
window.onload = function(){
	$("#ui-id-1").prepend("<i class='icon-desktop green'>&nbsp;</i>"); 
	$("#ui-id-2").prepend("<i class='icon-desktop green'>&nbsp;</i>"); 
	$("#ui-id-3").prepend("<i class='icon-bar-chart red'>&nbsp;</i>");
	$("#ui-id-4").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");
	$("#tab2").css("display","none");
	$("#tab4").css("display","none");
	do_change();
	loadClodopModule();
}
//渲染码表
function renderCode(rowdata, index, value, column) {
	var str='';
	var list = window[column.columnname + "List"];
	if(column.columnname == "baa027" || column.columnname == "daa027"){
		list = window["aaa027List"];
	}
	if(!list){
		list = $("#" + column.columnname + " option");
	}
	$.each(list, function(i, n){
		if(value==list[i].id){
			str= list[i].text;
		}
	});
	return str;
}

//金额求和
function renderSum(suminf, column, cell) {
return '<b>' + toPrice(suminf.sum.toFixed(2)) + '</b>';
}

//金额千位符格式化
function toPrice(num) {       
	num = num.toString().split(".");        
	num[0] = num[0].replace(new RegExp('(\\d)(?=(\\d{3})+$)','ig'),"$1,");        
	return num.join(".");     
}

//人次(人数)求和
function renderCount(suminf, column, cell) {
  return '<b>' + suminf.sum + '</b>';
}

//渲染表格中的操作按钮
function renderOperate(row, index, value) {
	var a = [];
	a.push('<input type="button" value="查看明细" class="linkButton"');
	a.push(' onclick="popupDetail(');
	a.push(index);
	a.push(')"');
	a.push(" />");
	return a.join('');
}

//查看明细
function popupDetail(index){
	var data = payList.getRow(index);
	if(data){
	   	var bke228 = data['bke228'];
	   	$('#bke228').val(bke228);
		var bizinfo = $("#mainForm").serialize();
	    if(bizinfo == null){
	    	return;
	    }
	    postJSON("<%=path%>/medicarepay/MedicalVillagePayAction!getDetailData.action",bizinfo,initDetailPage);
	}
}

function initDetailPage(json){
	if(!checkJSONResult(json)){
		return;
	}
	if (json.data=="no"){
      	popupAlert("没有找到相关的数据！", "提示", "warn");
    }else{
    	var i_report_sum = $("#report_0");
    	i_report_sum.data(json.data.listreport);
    	i_report_sum.contents().find("body").html(json.data.listreport);

    	bizList.reset();
    	bizList.setData(json.data.detaillist);
    	bizList.loadData();
    	
      	$("#divTabs").tabs( "select" , 2 );
    } 
}

function do_change(){
	if ($('#bkc008').val() == "1"){//已制单
		$("#tab1").css("display","none");
		$("#tab2").css("display","inline");
		$("#tab4").css("display","inline");
	}else{
		$("#tab1").css("display","inline");
		$("#tab2").css("display","none");
		$("#tab4").css("display","none");
	}
}
//查询
function query(){
	if (!checkFormValidtion()) {//校验必填项
		return;
	}

   	$('#bke228').val("");
	if($('#bkc008').val() == "1"){
		payList.reset();
		$("#btSave").attr("disabled", true);
		$("#btCancel").attr("disabled", false);
		$("#btPrint").attr("disabled", false);
		$("#btExport").attr("disabled", false);
	}else{
		dataList.reset();
		$("#btSave").attr("disabled", false);
		$("#btCancel").attr("disabled", true);
		$("#btPrint").attr("disabled", true);
		$("#btExport").attr("disabled", true);
	}
	
	var bizinfo = $("#mainForm").serialize();
    if(bizinfo == null){
    	return;
    }
    postJSON("<%=path%>/medicarepay/MedicalVillagePayAction!getPayData.action",bizinfo,initPayData);
}

function initPayData(json){
	if(!checkJSONResult(json)){
        return;
	}
	if (json.data=="no"){
		popupAlert("没有找到相关的数据！", "提示", "warn");
	}else{
		if($('#bkc008').val() == "1"){
			payList.setData(json.data);
			payList.loadData();
			$("#divTabs").tabs( "select" , 1 );
		}else{
			dataList.setData(json.data);
			dataList.loadData();
			$("#divTabs").tabs( "select" , 0 );
		}
	}  
}

//保存
function doSave(){
	var bizinfo = dataList.getSelectedRows();
	if (bizinfo.length<=0){
		popupAlert("请先选择需要结算的记录！", "提示", "warn");
	 	return;
	}else{
	 	bizinfo = powersi.tostring(bizinfo);
		
		$('#bizinfo').val(bizinfo);
		
		var forminfo = $("#mainForm").serialize();
	  	if(forminfo == null){
			return;
	  	}
	  	postJSON("<%=path%>/medicarepay/MedicalVillagePayAction!saveData.action",forminfo,initSaveData);
	}
}

function initSaveData(json){
	if(!checkJSONResult(json)){
		return;
	}
	
	var i_report_sum = $("#report_0");
	i_report_sum.data(json.data);
	i_report_sum.contents().find("body").html(json.data.listreport);
	
	dataList.deleteSelectedRow();
	$("#divTabs").tabs( "select" , 2 );
	$("#btSave").attr("disabled", true);
	$("#btPrint").attr("disabled", false);
	$("#btExport").attr("disabled", false);
	popupAlert("保存成功！", "提示", "warn");
}

//取消
function doCancel(){
	var bizinfo = payList.getSelectedRows();
	if (bizinfo.length<=0){
		popupAlert("请先选择需要取消结算的记录！", "提示", "warn");
	 	return;
	}else{
	 	bizinfo = powersi.tostring(bizinfo);
		
		$('#bizinfo').val(bizinfo);
		
		var forminfo = $("#mainForm").serialize();
	  	if(forminfo == null){
			return;
	  	}
	  	postJSON("<%=path%>/medicarepay/MedicalVillagePayAction!updData.action",forminfo,initUpdData);
	}
}

function initUpdData(json){
	if(!checkJSONResult(json)){
		return;
	}
	
	payList.deleteSelectedRow();
	$("#divTabs").tabs( "select" , 1 );
	$("#btSave").attr("disabled", true);
	$("#btPrint").attr("disabled", true);
	$("#btExport").attr("disabled", true);
	popupAlert("取消成功！", "提示", "warn");
}

function doPrint(){
	var divNum = powersi.tostring($("#divTabs").getTab().navid);
	if(divNum != "tab3"){
		popupAlert("非基金结算表，不能在此打印！", "提示", "warn");
	}else{
		var i_report_sum = $("#report_0");
		i_report_sum.contents().find("body").PowerPrint({
			name : "乡镇对村卫生室医疗生育保险基金支出单"
		}).preview();
	}
}
</script>
</powersi:html>