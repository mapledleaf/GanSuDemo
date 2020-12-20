<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
         Date date = new Date();
         SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMddHHmmss");
         String dqrq = dformat.format(date);
         String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
         String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>

<powersi:head title="体检费用结算" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
<script type="text/javascript"
	src="${rootPath}/pages/biz/medicare/diagnose/InputFee.js?V2017050909"></script>
<script type="text/javascript"
	src="${rootPath}/resource/js/clinicUtils.js"></script>
<body topmargin="0">
	<powersi:form id="bizForm" method="post">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button id="btnCalc" label="查询费用" key="button_calc1"
						onclick="getFeeInfo();"  />	
					<powersi:button id="btnCalc0" label="费用保存" key="button_calc1"
						onclick="saveFeeInfo();"  />	
					<powersi:button id="btnCalc1" label="费用试算" key="button_calc1"
						onclick="calc(0);"  />
					<powersi:button id="btnCalc2" label="费用结算" key="button_calc1"
						onclick="calc(1);" disabled="true" />
					<powersi:button id="btChecklist" label="结算清单" onclick="doPrint()" />	
					<powersi:button id="btnReset" key="button_clear"
						onclick="doClear();" label="清屏" disabled="false" />
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="0%,10%,10%,10%,15%,10%,10%,10%,25%">
				<powersi:editorlayout-row>
					<td><powersi:codeselect codeType="caa027" id="caa027"
							name="setMealDTO.caa027" required="true" headerKey="0"
							label="中心系统" onKeyDown="goNext('arg_name')" /></td>
					<td><select id="argName" class="select"  name="argName">
							<option value="aac002">身份证号码</option>
							<option value="aac001">个人电脑号</option>
							<option value="bka100">社保卡号</option>
					</select>
				
					</td>
					<td colspan="2"><powersi:textfield id="querystring" name="setMealDTO.querystring"
							title="请输入信息回车" placeholder="请输入信息回车！" buttonText="读卡" 
							onkeydown="findAac001(this)" buttonId="readic_button"  /></td>
					
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:panelbox key="panel_result" title="查询结果" allowCollapse="false">
			<powersi:editorlayout cols="10%,15%,10%,10%,10%,10%,10%,8%,5%,15%">
				<tr>
					<powersi:textfield id="akb021" key="体检医院" readonly="true"/>
					<powersi:hidden id="akb020"    key="体检医院id" name="setMealDTO.akb020" readonly="true"/>
					<powersi:textfield id="bkh059" key="体检套餐"  readonly="true"/>
					<powersi:hidden id="bkh015" key="体检套餐id" name="setMealDTO.bkh015" readonly="true"/>
					<powersi:textfield id="aac003" key="姓名" name="setMealDTO.aac003" readonly="true"/>
					<powersi:textfield id="aac004_name" key="性别"  readonly="true"/>
					<powersi:hidden id="aac004" key="性别id" name="setMealDTO.aac004" readonly="true"/>
					<powersi:textfield id="aac002" key="身份证" name="setMealDTO.aac002" readonly="true"/>
				</tr>
				<tr>
					<powersi:textfield id="aab069" key="单位名称" name="setMealDTO.aab069" readonly="true"/>
					<powersi:textfield id="aaz217" key="体检登记号" name="setMealDTO.aaz217" readonly="true" cssStyle="width:180px"/>
				</tr>
			<powersi:hidden id="bkh014" key="业务序列号" name="setMealDTO.bkh014" readonly="true"/>
			<powersi:hidden id="bkh016" key="登记日期" name="setMealDTO.bkh016" readonly="true"/>
			<powersi:hidden id = "feeinfo" key="feeinfo"/>
			<powersi:hidden id = "cusioninfo" key="cusioninfo"/>
			</powersi:editorlayout>
 		</powersi:panelbox>
 </powersi:form>		
	 <powersi:panelbox key="体检明细信息">
	
		<powersi:panelbox-toolbar>
			<powersi:button id="btn_enter" key="button_ok" label="确定" onclick="insertfee()" />
			<powersi:button id="btn_delete" key="button_delete" label="删除" onclick="deletefee()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout
			cols="5%,12%,0%,13%,5%,13%,5%,8%,5%,9%,5%,9%,5%,10%">
			<powersi:editorlayout-row>
					<powersi:codeselect id="sidx" name="setMealDTO.searchType"
					label="查询码" codeType="sidx" onchange="changeSearchType(this)"
					displayonly="false"  headerKey="1"/>
				<powersi:textfield id="stext" name="stext" label="" maxlength="30"
					readonly="false" />
				<powersi:textfield id="bkh030" name="bkh030" label="名称"
					disabled="true"  />
				<powersi:textfield id="bkh076" name="bkh076" label="单价" readonly="true"/>
				<powersi:textfield id="bka057" name="bka057" label="数量"
					onkeydown="dealKeyDown(this)" />
				<powersi:textfield id="bka058" name="bka058" label="金额"  readonly="true" onkeydown="dealKeyDown(this)" />
				<powersi:textfield id="curDateStr" name="curDateStr" label="日期" mask="date"  readonly="false"/>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		
		<powersi:panelbox cssStyle="display:none;">
			<powersi:form id="feeform" namespace="/health"
				action="ExaminationAction!getFeeInfo.action">
			     <powersi:hidden id="aaz217s"    key="体检登记号(副)" name="setMealDTO.aaz217s" readonly="true"/>
				
			</powersi:form>
		</powersi:panelbox> 
		 <powersi:datagrid id="grid" formId="feeform" height="35%" onAfterAddRow="scrollToRowLast"
			delayLoad="true" showReload="false" autoWidth="true" 
			alternatingRow="true" checkbox="true" colDraggable="false" enabledEdit="true" clickToEdit="true"
			pageSize="200" totalRender="renderTotal" render="renderOperate1">
			<powersi:datagrid-column name="bkh046_name" display="统计类别" width="12%" />
			<powersi:datagrid-column name="bkh046" display="统计类id" width="12%"  hide="true" />
			<powersi:datagrid-column name="bkh029" display="医院项目编码"  hide="true"/>
			<powersi:datagrid-column name="bkh030" display="医院项目名称" width="20%" />
			<powersi:datagrid-column name="bkh027" display="中心项目id"   hide="true"/>
			<powersi:datagrid-column name="bkh028" display="中心项目名称" width="20%" />
			<powersi:datagrid-column name="bka040" display="单价" width="10%" />
			<powersi:datagrid-column name="akc226" display="数量" width="5%" />
			<powersi:datagrid-column name="aae019" display="金额" width="12%" />
			<powersi:datagrid-column name="ake007" display="发生时间" width="10%" />
			<powersi:datagrid-column name="bkh026_name" display="是否套餐内" width="15%" />
			<powersi:datagrid-column name="bkh026" display="是否套餐内id" hide="true"/>
			<powersi:datagrid-column name="bka892" display="保存标识" hide="true"/>
		</powersi:datagrid> 
	</powersi:panelbox >
	<div class="row" id="jielun">
	
			<powersi:panelbox key="体检结论信息">
				<powersi:panelbox-toolbar>
			<powersi:button id="bt_delete" key="button_delete" label="删除" onclick="deletecusion()" />
			</powersi:panelbox-toolbar>
				<powersi:datagrid id="cusionGrid" height="35%" onAfterAddRow="scrollToRowLast"
				delayLoad="true" showReload="false" autoWidth="true" 
				alternatingRow="true" checkbox="false" colDraggable="true"
				pageSize="200">
					<powersi:datagrid-column name="bkh029" display="医院项目编码" hide="true" />
					<powersi:datagrid-column name="bkh030" display="医院项目名称" width="10%" />
					<powersi:datagrid-column name="bkh027" display="中心项目id" hide="true" />
					<powersi:datagrid-column name="bkh028" display="中心项目名称" width="10%" />
					<powersi:datagrid-column name="bkh149" display="结论编码" hide="true" />
					<powersi:datagrid-column name="bkh150" display="结论名称" width="10%" />
					<powersi:datagrid-column name="bkh151" display="结论内容" width="70%" />	
	
		</powersi:datagrid> 
	</powersi:panelbox>
	
</div>
	
	<powersi:panelbox title="所有费用信息（单位：元）">
		<powersi:editorlayout cols="10">
			<tr>
				<powersi:textfield id="bkh201" key="总费用" name="setMealDTO.bkh201"  disabled="true" cssClass="showpay" value="0.00" />
				<powersi:textfield id="bkh205" key="统筹支付" name="setMealDTO.bkh205" disabled="true" cssClass="showpay" value="0.00" />
				<powersi:textfield id="bkh204" key="个人账户支付" name="setMealDTO.bkh204" disabled="true" cssClass="showpay" value="0.00" />
				<powersi:textfield id="bkh203" key="现金支付" name="setMealDTO.bkh203" disabled="true" cssClass="showpay" value="0.00" />
			</tr>
		</powersi:editorlayout>
		<powersi:editorlayout-row>
	
			<!-- 临时字段 -->
			<powersi:hidden id = "bkh046_name"/>
			<powersi:hidden id = "bkh046"/>
			<powersi:hidden id = "bkh027"/>
			<powersi:hidden id = "bkh028"/>
			<powersi:hidden id = "bkh029"/>
			<powersi:hidden id = "bkh030"/>
			<powersi:hidden id = "bkh026" key="是否套餐内"/>
			<powersi:hidden id = "bkh026_name" key="是否套餐内id"/>
		</powersi:editorlayout-row>
		
	</powersi:panelbox>

</body>
<script type="text/javascript">
var objCard = null;
var saveRegisterSpecialFlag = false;
var curDate = new Date();
 var month = curDate.getMonth() + 1 < 10 ? "0"
		+ (curDate.getMonth() + 1) : curDate.getMonth() + 1;
var day = curDate.getDate() < 10 ? "0" + (curDate.getDate())
		: curDate.getDate();
	var curDateStr = curDate.getFullYear()+""+month+day;
	var actionUrl = "";
	actionUrl="${rootPath}/health/HealthAction!queryItemMatch.action?healthDTO.aae030="+curDateStr+"&healthDTO.flag=1&healthDTO.searchType=py";
	$(document).ready(function() {
		 doClear();
		$("#curDateStr").val(curDateStr);
		$("#querystring").focus();
		$("#stext").combogrid({
			minLength : 1,
			autoChoose : false,
			searchIcon : true,
			alternate : true,
			width : '750px',
			url : actionUrl,
			colModel : [ 
			{
				'columnName' : 'bkh046_name',
				'width' : '14',
				'label' : '统计类别'
			}, {
				'columnName' : 'bkh046',
				'width' : '14',
				'label' : '统计类别标识'
			},{
				'columnName' : 'bkh029',
				'width' : '14',
				'label' : '医院项目编码'
			}, {
				'columnName' : 'bkh030',
				'width' : '14',
				'label' : '医院项目名称'
			},{
				'columnName' : 'bkh027',
				'width' : '14',
				'label' : '中心体检项目编码'
			},{
				'columnName' : 'bkh028',
				'width' : '14',
				'label' : '中心体检项目名称'
			},{
				'columnName' : 'bkh076',
				'width' : '12',
				'label' : '单价'
			}], 
			select : function(event, ui) {
				var data = ui.item;
				$("#bkh029").val(data["bkh029"]);
				$("#bkh030").val(data["bkh030"]);
				$("#bkh076").val(data["bkh076"]);
				$("#bkh046").val(data["bkh046"]);
				$("#bkh046_name").val(data["bkh046_name"]);
				$("#bkh027").val(data["bkh027"]);//中心体检项目编码
				$("#bkh028").val(data["bkh028"]);//中心体检项目名称
				$("#bka057").focus();
				return false;
			}
		});
	
		
	});
	
	

	function init() {
		doClear();
	}
	
	function changeSearchType(sidx) {
		actionUrl="${rootPath}/health/HealthAction!queryItemMatch.action?healthDTO.aae030="+curDateStr+"&healthDTO.flag=1&healthDTO.searchType="+sidx.value;
		$("#stext").combogrid("option", "url", actionUrl);
	}
	
	function readIcCard() {
		var querystring = powersi.trim($("#querystring").val());
		getPerson(querystring);
	}
	
	
	
	/*查询参保信息*/
	function findAac001(para){
		if (para == "readic" || event.keyCode == '13') {
			var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			if('aac002' == $("#argName").val()){
				$("#argName option:first").prop('selected', 'selected'); 
				$("#argName option:selected").val('aac002');
				$("#argName").change();
				$("#argName").val('aac002');
				$("#argName").change();
			}
			if('aac002'==$("#argName").val()){
				checkIcCard(querystring);
				__isPing(identify(querystring));
			} else {
					getPerson(querystring);
			}
		}
	}
	
	//获取费用信息
	function getFeeInfo(){
		var aaz217 = $("#aaz217").val();
		if(powersi.isnull(aaz217)){
			 return;                    
		}
		$("#aaz217s").val(aaz217);
		grid.reset();
		$("#feeform").submit();
		
	}
	
	
	function getPerson(querystring){
		var arg_name = $("#argName").val();
		var bkh011 = $("#bkh011").val();
		//查询人员之前进行页面清理
		doClear();
		 $("#querystring").val(querystring);
		 $("#bkh011").val(bkh011);
		 $("#argName").val(arg_name);
		 if(powersi.isnull(bkh011)){
			 alert("请选择体检年份");
			 return;                    
		 }
		 
		 var querystring = powersi.trim($("#querystring").val());
			if (powersi.isnull(querystring)) {
				alert("请输入有效查询条件");
				return;
			}
			var caa027 = powersi.trim($("#caa027").val());
			if (powersi.isnull(caa027)) {
				alert("请选择中心系统");
				return;
			}
			findAac002(querystring);
		 
	}
	
	function findAac002(querystring){
		var aac001="";
		var aac002="";
		var aaz500="";
		var arg_name = $("#argName").val();
		if (arg_name == 'aac001') {
			arg_name_temp = "&setMealDTO.indi_id=" + querystring;
		} else if (arg_name == 'aac002') {
			arg_name_temp = "&setMealDTO.idcard=" + querystring;
		} else if (arg_name == 'bka100') {
			arg_name_temp = "&setMealDTO.insr_code=" + querystring;
		}
		var registerData = $("#bizForm").serialize();
		registerData = registerData + arg_name_temp+"&setMealDTO.argName="+arg_name;
			postJSON("${rootPath}/health/ExaminationAction!queryHealthRegisterInfo.action",
				registerData,function(json){
			if(!checkJSONResultNew(json)){
				if (arg_name=="bka100") {
					$("#querystring").prop("disabled", true);
				}
				return;
			}
			if (json.data != "no") {
					$.each(json.data, function(key, value) {
						if (!powersi.isnull(value)) {
							$("#" + key).val(value);
						}
					});
				}
		}); 
	}
	
	function dealKeyDown(para) {
		if (window.event.keyCode == '13') {
			var filed_name = para.id;
			if ('bka057' == filed_name) {
				var bka057 = $("#bka057").val();
				if (isNaN(bka057)) {
					alert("数量不能为字符");
					return $("#bka057").focus();
				}
				var bkh076 = $("#bkh076").val();
				if (!powersi.isnull(bkh076) && !powersi.isnull(bka057)) {
					//修改概要：TS19080900029  - 【问题修复】【体检费用结算】-在选择目录时“数量”录入小数，“金额”的取值的精度显示问题
					//修改描述：数量精度取4位 金额取2位
					//修改人及修改时间：李嘉伦 20190809
					/*
					$("#bka058").val(parseFloat(bkh076) * parseFloat(bka057));
					*/
					$("#bka057").val(parseFloat(bka057).toFixed(4));
					$("#bka058").val((parseFloat(bkh076) * (parseFloat(bka057).toFixed(4))).toFixed(2));
					return  $("#bka058").focus();
				}
				
			}
			if("bka058" == filed_name){
				insertfee();
			}
		}
	}
	
	
	function insertfee() {
		if (powersi.isnull($("#bkh030").val())) {
			alert("未录入项目");
			return;
		}

		if (powersi.isnull($("#bka058").val())) {
			alert("未录入金额");
			return;
		}

		if (parseFloat($("#bka058").val()) <= 0) {
			alert("金额不能小于等于0");
			return;
		}
		var bkh016 = $("#bkh016").val();//登记日期
		var ake007 = $("#curDateStr").val();		//发生时间
		if(Number(ake007) < Number(bkh016)){
			alert("费用发生时间不能小于业务开始时间("+bkh016+")！");
			return;
		}
		
		
		var bkh046 = $("#bkh046").val();				//统计类别id
		var bkh046_name = $("#bkh046_name").val();		//统计类别
		var bkh030 = $("#bkh030").val();		//医院项目名称
		var bkh029 = $("#bkh029").val();        //医院项目编码
		var bkh027 = $("#bkh027").val();		//中心体检项目编码
		var bkh028 = $("#bkh028").val();		//中心项目名称 
		var bka040 = $("#bkh076").val();		//单价
		var akc226 = $("#bka057").val();		//数量
		var aae019 = $("#bka058").val();		    //金额
		var bkh026 = "";						//是否套餐内  id
		var bkh026_name = "";						//是否套餐内  
		var bkh015 =  $("#bkh015").val();       //套餐id
		var bka892 = "0";
		postJSON("${rootPath}/health/ExaminationAction!checkItemIsPackage.action?setMealDTO.bkh015="+bkh015+"&setMealDTO.bkh027="+bkh027,
				function(json){
			if(checkJSONResultNew(json)){
				if(json.data == "0"){
					bkh026 = "1";
					bkh026_name = "套餐外";
				}
				if(json.data == "1"){
					bkh026 = "0";
					bkh026_name = "套餐内";
				}
				
				var jsonFee = {
						"bkh046" : bkh046,
						"bkh046_name" : bkh046_name,
						"bkh030" : bkh030,
						"bkh029" : bkh029,
						"bkh027" : bkh027,
						"bkh028" : bkh028,
						"bka040" : bka040,
						"akc226" : akc226,
						"aae019" :  parseFloat(aae019).toFixed(2) * 1.0000,
						"ake007" : ake007,
						"bkh026" : bkh026,
						"bkh026_name" : bkh026_name,
						"bka892" : bka892
				};
				grid.add(jsonFee);
				$("#stext").val("");
				$("#bkh030").val("");
				$("#bkh076").val("");
				$("#bka057").val("");
				$("#bka058").val("");
				$("#stext").focus();
				
			}
		});

	}
	
	//费用保存
	function saveFeeInfo(){
		var feeinfo = grid.getData();
		var feeinfoAdd = grid.getAdded();
		var flag = false;
		/*页面处理重复保存*/
		if (feeinfoAdd != '[]' && feeinfoAdd != '') {
			feeinfo = feeinfoAdd;
		} else {
			bka892 = feeinfo[0]['bka892'];
			if (bka892 == 1) {
				flag = true;
			}
		}
		if (flag) {
			alert("不存在需要保存的费用明细！");
			return;
		}
	
		$("#aaz217s").val($("#aaz217").val());
		feeinfo = powersi.tostring(feeinfo);
		$("#feeinfo").val(encodeURI(feeinfo));
		var Settlement = $("#bizForm").serialize();
		postJSON("${rootPath}/health/ExaminationAction!saveFee.action",Settlement,function(json){
			if (!checkJSONResultNew(json)) {
				return;
			}
			 $("#feeform").submit();
		});
		
		
	}
	
	//费用试算或结算0试算 1结算
	function calc(str){
	//	$("#btnCalc2").prop("disabled", true);
		if("0" == str){
			fundClean();
			var Settlement = $("#bizForm").serialize();
			postJSON("${rootPath}/health/ExaminationAction!tryCalcTreat.action",Settlement,function(json){
				if(!checkJSONResultNew(json)){
					return;
				}
				if(!powersi.isnull(json.data)){
					$("#btnCalc2").prop("disabled", false);
				}
				$.each(json.data, function(key, value) {
						if (!powersi.isnull(value)) {
							$("#" + key).val(value);
						}
					});
			
			});
			
			
		}else{
			/* var feeinfo = grid.getData();
			feeinfo = powersi.tostring(feeinfo);
			$("#feeinfo").val(encodeURI(feeinfo)); */
		//	var cusioninfo = cusionGrid.getData();
		//	cusioninfo = powersi.tostring(cusioninfo);
		//	$("#cusioninfo").val(encodeURI(cusioninfo));
			var Settlement = $("#bizForm").serialize();
			postJSON("${rootPath}/health/ExaminationAction!settleMentFee.action",Settlement,function(json){
				if(!checkJSONResultNew(json)){
					return;
				}
				$.each(json.data, function(key, value) {
						if (!powersi.isnull(value)) {
							$("#" + key).val(value);
						}
					});
				alert("结算成功！");
				$("#querystring").prop("disabled", true);
				$("#btn_enter").prop("disabled", true);
				$("#btn_delete").prop("disabled", true);
				$("#bt_delete").prop("disabled", true);
				$("#btnCalc0").prop("disabled", true);	
				$("#btnCalc1").prop("disabled", true);	
				$("#btnCalc2").prop("disabled", true);	
				$("#btChecklist").prop("disabled", false);
				
			});
		}

		
	}
	
	function dbSelectRow(rowdata, rowid, rowobj){
		var row = grid.getRow(rowid);
		var bkh029 = row['bkh029'];//医院项目编码
		var bkh030 = row['bkh030'];//医院项目名称
		var bkh027 = row['bkh027'];//中心项目编码
		var bkh028 = row['bkh028'];//中心项目名称
		var bkh149 = "";//结论项目编码
		var bkh150 = ""//结论项目名称
		var bkh151 = "";//结论内容
		if(powersi.isnull(bkh027)){
			alert("中心项目编码不能为空！");
			return;
		}
		popupDialog({   //pages/biz/medicare/health/jielun.jsp
			url : "${rootPath}/health/ExaminationAction!chooseJielun.action?setMealDTO.bkh027="+bkh027,
			onClosed : function() {
				var ret = this.returnValue;
				if('undefined' == ret || undefined == ret){
					return false;
				}
					$("#jielun").css("display","block");//结论grid默认隐藏
					ret = powersi.tojson(ret)
					$.each(ret,function(i,row){
						bkh149 = row['bkh149'];
						bkh150 = row['bkh150'];
						bkh151 = row['bkh151'];
						var jsonFee = {
								"bkh029":bkh029,
								"bkh030":bkh030,
								"bkh027":bkh027,
								"bkh028":bkh028,
								"bkh149":bkh149,
								"bkh150":bkh150,
								"bkh151":bkh151
							}
							cusionGrid.add(jsonFee);
						
					});
			}
		},500, 600);
		
		
	}
	
	
	function deletefee() {
		if (typeof (grid.getSelectedRows()) == "undefined"
				|| grid.getSelectedRows() == null
				|| grid.getSelectedRows() == "") {
			alert('请选择要删除的费用');
			return;
		}
		popupConfirm("是否删除已选择的费用?", "提示", function(isOk) {
			if (isOk) {
				$("#aaz217s").val($("#aaz217").val());
				valideDeFee(grid.getSelectedRows());
			}
		});
			
		
	}
	
	function valideDeFee(rows) {
		$.each(rows, function(i, row) {
			valideDelFee(row)
		});
	}
	
	
	function valideDelFee(row) {
		if (row['bka892'] != undefined && row['bka892'] != '0') {
			var aae019Temp = row['aae019'];
			if (aae019Temp != undefined && aae019Temp != null) {
				aae019Temp = parseFloat(aae019Temp);
				if (aae019Temp < 0) {
					alert("[" + row['bkh030'] + "]已退费用不允许删除!");
					return;
				}
			}
			var delRow = copyObj(row);
			insertffy(delRow, row);
		} else {
			var rowid = grid.getRowId(row);
			grid.deleteRow(rowid);
		}
	}
	
	//插入负费用
	function insertffy(rowPar, oldRow) {
		rowPar['bkh046'] = oldRow['bkh046'];
		rowPar['bkh046_name'] = oldRow['bkh046_name'];
		rowPar['bkh030'] = oldRow['bkh030'];
		rowPar['bkh029'] = oldRow['bkh029'];
		rowPar['bkh027'] = oldRow['bkh027'];
		rowPar['bkh028'] = oldRow['bkh028'];
		rowPar['bka040'] = oldRow['bka040'];
		rowPar['akc226'] = -parseFloat(rowPar['akc226']);
		rowPar['aae019'] = -parseFloat(rowPar['aae019']).toFixed(2) * 1.0000
		rowPar['ake007'] = oldRow['ake007'];
		rowPar['bkh026'] = oldRow['bkh026'];
		rowPar['bkh026_name'] = oldRow['bkh026_name'];
		rowPar['bka892'] = '0';
		grid.add(rowPar);
		setColorError();
	}

	
	
	
	var copyObj = function(obj) {
		var str, newobj = obj.constructor === Array ? [] : {};
		if (typeof obj !== 'object') {
			return;
		} else if (window.JSON) {
			str = JSON.stringify(obj), // 系列化对象
			newobj = JSON.parse(str); // 还原
		} else {
			for ( var i in obj) {
				newobj[i] = typeof obj[i] === 'object' ? cloneObj(obj[i]) : obj[i];
			}
		}
		return newobj;
	};
	
	
	function setColorError() {
		var rows = grid.getRows();
		$.each(rows, function(i, row) {
			var aae019Temp = row['aae019'];
			if (aae019Temp != undefined && aae019Temp != null) {
				aae019Temp = parseFloat(aae019Temp);
				if (aae019Temp < 0) {
					var rowid = grid.getRowId(row);
					grid.setRowColor(rowid, powersi.color('error'));
				}
			}
		});
	}
	
	
	function deletecusion() {
		if (typeof (cusionGrid.getSelectedRows()) == "undefined"
				|| cusionGrid.getSelectedRows() == null
				|| cusionGrid.getSelectedRows() == "") {
			alert('请选择要删除的结论');
			return;
		}
		if (confirm("是否删除已选择的结论?") == true) {
			cusionGrid.deleteSelectedRow();
		}
	}
	
	

	function doClear() {
		$("#jielun").css("display","none");//结论grid默认隐藏
		$("#querystring").prop("disabled", false);
		$("#btn_enter").prop("disabled", false);
		$("#btn_delete").prop("disabled", false);
		$("#bt_delete").prop("disabled", false);
		$("#querystring").val('');
		$("#stext").val('');
		$("#bkh030").val("");
		$("#bkh076").val("");
		$("#bka057").val("");
		$("#bka058").val("");
		$("#akb021").val("");
		$("#akb020").val("");
		$("#bkh059").val("");
		$("#bkh015").val("");
		$("#aac003").val("");
		$("#aac004").val("");
		$("#aac004_name").val("");
		$("#aac002").val("");
		$("#aab069").val("");
		
		$("#bkh201").val('0.00');
		$("#bkh202").val('0.00');
		$("#bkh203").val('0.00');
		$("#bkh204").val('0.00');
		$("#bkh205").val('0.00');
		$("#bkh206").val('0.00');
		$("#bkh207").val('0.00');
		$("#bkh208").val('0.00');
		
		$("#bkh046_name").val('');
		$("#bkh046").val('');
		$("#bkh027").val('');
		$("#bkh028").val('');
		$("#bkh029").val('');
		$("#bkh030").val('');
		$("#bkh026").val('');
		$("#bkh026_name").val('');
		grid.reset();
		cusionGrid.reset();
		$("#btChecklist").prop("disabled", true);
		$("#querystring").focus();
	}
	
	//清空基金显示
	function fundClean(){
		$("#bkh201").val('0.00');
		$("#bkh202").val('0.00');
		$("#bkh203").val('0.00');
		$("#bkh204").val('0.00');
		$("#bkh205").val('0.00');
		$("#bkh206").val('0.00');
		$("#bkh207").val('0.00');
		$("#bkh208").val('0.00');
	}
	
	
	//新增行后跳转至最后一行
 	function scrollToRowLast(rowdata){
 		grid.scrollToRow(rowdata.__index);
 	}
 	function renderTotal(allData, currentData) {
		var money = 0;
		var price = 0;
		var usage = 0;
		var kinds = {};
		if (powersi.rows_length(allData['rows'])) {
			$.each(allData['rows'], function(n, row) {
				if (row['__status'] !== "delete") {
					price += parseFloat(row['bka040']);
					usage += parseFloat(row['akc226']);
					money += parseFloat(row['aae019']);
					kinds[row['bkh029']] = '';
				}
			});
		}
		var a = [];
		a.push('<div class="divCenter textSuccess">');
		a.push('<span>');
		a.push('当前页总金额：<b>');
		//修改概要：TS19080900029  - 【问题修复】【体检费用结算】-在选择目录时“数量”录入小数，“金额”的取值的精度显示问题
		//修改描述：数量精度取4位 金额取2位
		//修改人及修改时间：李嘉伦 20190809
		/*a.push(usage.toFixed(2));*/
		a.push(usage.toFixed(4));
		a.push('</b>');
		a.push('</span>');
		a.push('&nbsp;&nbsp;');
		a.push('<span>');
		a.push('当前页总用量：<b>');
		a.push(usage.toFixed(2));
		a.push('</b>');
		a.push('</span>');
		a.push('&nbsp;&nbsp;');
		a.push('<span>');
		a.push('当前页共 <b>');
		a.push(powersi.length(kinds));
		a.push('</b> 项费用');
		a.push('</span>');
		a.push('</div>');
		return a.join('');
	}
 	
 	
	function doPrint(){
		var aaz217 = $("#aaz217").val();
		
		var center_flag = "1";
		var actionUrl = "${rootPath}/health/ExaminationAction!settlementReport.action"+
		"?setMealDTO.aaz217="+aaz217+"&setMealDTO.center_flag="+center_flag;
		popupDialog(actionUrl, 600, 1000);
		
	}
 	
</script>
</powersi:html>