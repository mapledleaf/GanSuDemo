<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();

	String aaa027 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAaa027(); 
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	//String userLevel = BizHelper.getUserLevel();
	String aaz267value = request.getAttribute("mediSpecDto.aaz267") == null
			? "0"
			: request.getAttribute("mediSpecDto.aaz267").toString();

	String aae140 = request.getParameter("aae140");
	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat();
	String time = dformat.format(d);
%>

<powersi:html>
<body>
	<powersi:head title="门特特殊业务审批" />
	<powersi:form id="mainForm" namespace="/medicare"
		action="MtmmSpecialApplyAction!getSPInfoList.action">
		<div id="div1" style="display: none">
			<powersi:hidden id="akb020" name="mediSpecDto.akb020" key="akb020" />
			<powersi:hidden id="indi_id" name="indi_id" value="indi_id" />
			<powersi:hidden id="idcard" name="idcard" value="idcard" />
			<powersi:hidden id="arg_name" name="mediSpecDto.arg_name" />
		</div>
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_query" value="查询"
					onclick="queryPresonInfo()" />
				<powersi:button key="button_audit" value="审批"
					onclick="save_auditInfo()" />
				<powersi:button key="button_reset" value="重置" onclick="clearall()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="9%,18%,9%,15%,9%,15%,9%,16%">
				<powersi:editorlayout-row>
					<powersi:textfield id="arg_value" name="mediSpecDto.arg_value" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false" />
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'"
						id="aae127_start" name="mediSpecDto.aae127_start"
						key="aae127_start" readonly="true" />
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'"
						id="aae127_end" name="mediSpecDto.aae127_end" key="aae127_end"
						readonly="true" />

					<powersi:codeselect id="bka006" key="门特项目" 
						name="mediSpecDto.bka006" codeType="bka006"  cssClass="select2" 
						codeFilter="data_value like '16%' " codeSort="1" required="false" codeLocal="<%=aaa027%>" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<div class="row">
	     <div class="col-5">
		<powersi:panelbox title="人员列表">
				<powersi:datagrid id="auditgrid" formId="mainForm"
				isCheckable="chkboxDisabledRow"	 checkbox="true" onDblClickRow="doubleClick"
					 selectRowButtonOnly="true" delayLoad="true">
					<powersi:datagrid-column id="aac003" name="aac003" label="姓名"
						width="100" />
					<powersi:datagrid-column id="aaz267" name="aaz267" label="序列号"
						width="80" />
					<%-- <powersi:datagrid-column id="aab069" name="aab069" label="单位名称"
						width="250" /> --%>
					<powersi:datagrid-column id="bka155" name="bka155" label="门特项目"
						width="100" />
					<powersi:datagrid-column id="aka121" name="aka121" label="疾病名称"
						width="150" />
					<powersi:datagrid-column id="akb021" name="akb021" label="医院名称"
						width="180" />
					<powersi:datagrid-column id="aae016" name="aae016" label="审核标志"
						hide="true" />

				</powersi:datagrid>
		</powersi:panelbox>
			</div>
	 <div class="col-7">
		<powersi:panelbox title="人员详细信息">
		
				<powersi:form id="dForm">
					<powersi:editorlayout cols="13%,12%,11%,11%,13%,14%,12%,14%">
						<tr>
							<powersi:textfield label="姓名" id="aac003" name="aac003"
								readonly="true" />
							<powersi:textfield label="性别" id="aac004" name="aac004"
								readonly="true" />
							<powersi:textfield label="出生日期" id="aac006" name="aac006"
								readonly="true" />
							<powersi:textfield label="序列号" id="aaz267" name="aaz267"
								readonly="true" />
						</tr>
						<tr>
							<powersi:textfield label="身份证号" id="aac002" name="aac002"
								readonly="true" colspan="3" />
							<powersi:textfield label="所属单位" id="aab069" name="aab069"
								readonly="true" colspan="3" />
						</tr>
						<tr>
							<powersi:codeselect label="人员类别" id="bka004" name="bka004"
								disabled="true" codeType="bka004" />
							<powersi:textfield label="门特项目" id="bka155" name="bka155"
								readonly="true" />
							<powersi:textfield label="医院名称" id="akb021" name="akb021"
								readonly="true" colspan="3" />
						</tr>
						<tr>
							<powersi:textfield label="就诊科室" id="bke015" name="bke015"
								readonly="true" />
							<powersi:textfield label="科室电话" id="bke014" name="bke014"
								readonly="true" />
							<powersi:textfield label="疾病名称" id="aka121" name="aka121"
								readonly="true" />
							<powersi:textfield label="诊断医师" id="bke017" name="bke017"
								readonly="true" />
						</tr>
						<tr>
							<powersi:textfield label="主任医生" id="bke018" name="bke018"
								readonly="true" />
							<powersi:textfield label="联系电话" id="bke016" name="bke016"
								readonly="true" />
							<powersi:textfield label="医院意见" id="bke013" name="bke013"
								readonly="true" colspan="3" />
						</tr>
						<tr>
							<powersi:textfield label="有效期开始" id="aae030" name="aae030"
								readonly="true" colspan="3" />
							<powersi:textfield label="有效期结束" id="aae031" name="aae031"
								readonly="true" colspan="3" />
						</tr>
						<tr>
							<powersi:textfield label="审核标志" id="aae016" name="aae016"
								readonly="true" />
							<powersi:textfield label="审核人" id="bke021" name="bke021"
								readonly="true" />
							<powersi:textfield label="审核时间" id="bke022" name="bke022"
								readonly="true" />
							<powersi:textfield label="医院意见时间" id="bke034" name="bke034"
								readonly="true" />
						</tr>
						<tr>
							<powersi:textarea label="病情摘要及诊断" id="bke011" name="bke011"
								readonly="true" colspan="7" />
						</tr>
						<tr>
							<powersi:textarea label="诊治方案及项目构成" id="bke012" name="bke012"
								readonly="true" colspan="7" />
						</tr>
						<tr>
						</tr>
						<tr>
							<powersi:textarea label="备注" id="aae013" name="aae013"
								readonly="false"  colspan="7" />
						</tr>
					</powersi:editorlayout>
				</powersi:form>
		</powersi:panelbox>
		</div>
	</div>
	<script type="text/javascript">

	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");
	//	$('#akb021').val("<%=hospital_name%>");		
		$("#aae140").val=(<%=aae140%>);
		
		//申请结束时间默认为当天
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length ==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length ==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		
		var lastMonthDate = myDate;
		lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
		var lastYear = lastMonthDate.getFullYear();
		var lastMonth = (lastMonthDate.getMonth()+1).toString().length ==1?"0"+(lastMonthDate.getMonth()+1).toString():(lastMonthDate.getMonth()+1).toString();
		
		$("#aae127_start").val(lastYear + "-" + lastMonth + "-" + "01");
		$("#aae127_end").val(year + "-" + month + "-" + day);
		
		$("#bke023").val();
		$("#aae016").val("0");
		$('#bkc027').val("<%=userName%>");
      	$('#bkc028').val("<%=loginUser%>");
      	$('#bkc029').val(year+"-"+month+"-"+day);
      	
	} 
	//去除左右两边的空格
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}	
	//点击查询按钮时,查出待审核的人员信息
	function queryPresonInfo(){
		if(!checkForm){
			return;
		}
		if($("#aae127_start").val() > $("#aae127_end").val()){
			popupAlert("申请开始时间必须小于申请结束时间!");
			return;
		}
		
		var beginDate = $("#aae127_start").val();
		var endDate = $("#aae127_end").val();
		var date1 = new Date(endDate.replace(/-/g,"/"));
		var date2 = new Date(beginDate.replace(/-/g,"/"));
		var days = ((date1-date2)/(1000*3600*24)+1);
		if(days > 366){
			popupAlert("申请开始时间与申请结束时间不能大于1年!");
			return;
		}
		auditgrid.reset();
		dForm.reset();
		
		var indi_id = $("#indi_id").val();
		var idcard  = $("#idcard").val();
		var arg_value = $("#arg_value").val();
		var argValue = arg_value.substr(0);
		//根据电脑号和身份证号的长度来判断输入的是电脑号还是身份证号
			if(argValue.length == "10"){
				$("#arg_name").val("indi_id");
			}else if(argValue.length == "15" || argValue.length == "18"){
				$("#arg_name").val("idcard");
			}
			var saveItemData = $("#mainForm").serialize();
			postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!getSPInfoList.action",saveItemData, queryPresonInfoAfter);	
	}
	function queryPresonInfoAfter(json){
		if(!checkJSONResult(json)){
			return;
		}
		//加载结果集到datagrid
		auditgrid.reload(json.data);
	}
	
	function doubleClick(rowdata, rowid, rowobj){
		var row = auditgrid.getRow(rowid);
		clearForm('#dForm');
		json2form('#dForm', row);
	}
	function chkboxDisabledRow(row){
		if(row.aae016 == "初审通过"){
			return false;
		}
	}
	
	 document.onkeydown = function(event){
		  var e = event || window.event || arguments.callee.caller.arguments[0];
		   if(e && e.keyCode == 13){ // enter 键
			   queryPresonInfo();
	    }
	}
	function save_auditInfo(){	
		var data = auditgrid.getSelectedRows();
			
        if(powersi.rows_length(data) == 0){
			popupAlert("请先勾选要审批的数据!");
			return;
		}
		var detail = powersi.tostring(data);
		 postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!auditInfo.action",
					{
						"applyList" : detail
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
						queryPresonInfo();
						return;
					});
		}

		function clearall() {
			$("#aka083").val("");
			$("#arg_value").val("");
			$("#aac012").val("");
			$("#bke023").val("");
			$("#tracestring").val("");
			$("#arg_name").val("");
			auditgrid.reset();
			dForm.reset();
		}
	</script>
</body>
</powersi:html>