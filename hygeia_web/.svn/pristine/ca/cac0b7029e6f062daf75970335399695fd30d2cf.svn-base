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
	
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	
	String aaz267value = request.getAttribute("mediSpecDto.aaz267")==null?"0":request.getAttribute("mediSpecDto.aaz267").toString();

	String aae140 = request.getParameter("aae140");
	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat();
	String time = dformat.format(d);
%>

<powersi:html>
	<head>
		<powersi:head title="门诊选点业务审批"/>
	</head>
	<body>
		<powersi:groupbox title="查询条件">
		<powersi:form id="mainForm">
		
		<div id = "div1" style="display: none">
			 <powersi:textfield id="akb020" name="mediSpecDto.akb020"  key="akb020" value="002018"/>
		</div>
	
			<powersi:editorlayout cols="12%,16%,10%,12%,10%,12%,10%,18%">
				<tr>
					<td>
					<select id="arg_name" name="mediSpecDto.arg_name" class="select" onchange="">
						<option value="indi_id">个人电脑号</option>
						<option value="idcard">身份证号码</option>
						<option value="name">姓名</option>
						<option value="ic_no">借记卡号</option>
						<option value="ic_no">医保卡号</option>
					</select>
				</td>
				<td>
					<input type="text" id="arg_value" name="mediSpecDto.arg_value" class="" />
				</td>
					<td align="right" class="tdLabel">待遇类型</td>
					<td>
						<select id="bka006" name="mediSpecDto.bka006" class="select" onchange="">
							<option value="">(全部)</option>
							<option value="110">普通门(急)诊</option>
							<option value="11B">城居生育产检</option>
						</select>
					</td>		
					<powersi:codeselect label="人员类别" id="bka004" name="mediSpecDto.bka004" key="bka004" codeFilter="" codeType="pers_type_medi"/>
				 	<powersi:codeselect label="初审状态" id="bke023" name="mediSpecDto.bke023" key="bke023" codeType="aae016_pub_medical"/>
				</tr>
				<tr>
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_start" name="mediSpecDto.aae127_start" key="aae127_start" readonly="true"/>
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_end" name="mediSpecDto.aae127_end" key="aae127_end" readonly="true"/>
					<powersi:codeselect label="复审状态" id="aae016" name="mediSpecDto.aae016" key="aae016" codeType="aae016_pub_medical"/>
					<td colspan="2" align="right">
						<powersi:button key="button_query" value="查询" onclick="queryAll()"/>
					    <powersi:button key="button_register" value="门诊登记表" onclick="Register()"/>
						<powersi:button key="button_reset" value="重置" onclick="clearall()"/>
					</td>
				</tr>
			</powersi:editorlayout>
			</powersi:form>
		</powersi:groupbox>
		
		 <table class="table_frame">
	  		<td width="40%">	
				<powersi:groupbox title="申请人员列表">
					<powersi:datagrid id="auditgrid" formId="mainForm"  onSelectRow="selectRow" height="440" delayLoad="true">
					<powersi:datagrid-column name="name"           id="aac003" label="姓名"    width="80"/>
					<powersi:datagrid-column name="serial_apply"   id="aaz267" label="申请号"  width="80"/>
					<powersi:datagrid-column name="sex"            id="aac004"   label="性别"    width="60"/>
					<powersi:datagrid-column name="pers_type_name" id="bka004" label="人员类别" width="80"/>
					</powersi:datagrid>
				</powersi:groupbox>
			</td>
			<td width="60%">
				<powersi:groupbox title="人员详细信息">
					<powersi:form id="dForm" >
						<powersi:editorlayout cols="16%,14%,9%,15%,12%,12%,12%,10%">
							<tr>
						    	<powersi:textfield  id="aac003" name="name"      label="姓名"    readonly="true"/>
						    	<powersi:textfield  id="aac001" name="indi_id"   label="电脑号"   readonly="true"/>
								<powersi:textfield  id="aac004" name="sex"       label="性别"    readonly="true"/>
								<powersi:textfield  id="dq11"   name="age"       label="当前年龄" readonly="true"/>
							</tr>
							<tr>
								<powersi:textfield  id="aac006" name="birthday" label="出生日期"  readonly="true" colspan="3"/>
								<powersi:textfield  id="aac002" name="idcard"   label="身份证号"  readonly="true" colspan="3"/>
							</tr>
							<tr>
								<powersi:textfield  id="aaz267" name="serial_apply"      label="申请号"     readonly="true" colspan="3"/>
								<powersi:textfield  id="bka011" name="rela_serial_apply" label="关联申请号"  readonly="true" colspan="3"/>
							</tr>
							<tr>
								<powersi:textfield  id="bke956" name="urban_nopay_type_name" label="困难救助人群类别"  readonly="true" colspan="3"/>
								<powersi:textfield  id="bka004" name="pers_type_name"        label="人员类别"         readonly="true" colspan="3"/>
							</tr>
							<tr>
								<powersi:textfield label="待遇类型 " id="bka006" name="treatment_name" readonly="true" colspan="3"/>
								<powersi:textfield label="预产期"   id="bmc016" name="due_date"      readonly="true" colspan="3"/>
							</tr>
							<tr>
								<powersi:textfield label="申请医院" id="akb021" name="apply_hospital_name"  readonly="true" colspan="7"/>
							</tr>
							<tr>
								<powersi:textfield label="初审标志" id="bke023" name="audit_flag"  readonly="true"/>
								<powersi:textfield label="初审人"  id="bkb102" name="audit_man"  readonly="true"/>
								<powersi:textfield label="初审时间" id="bkb101" name="audit_date"  readonly="true" colspan="2"/>
								<td colspan="1"></td>
							</tr>
							<tr>
								<powersi:textfield label="复审标志" id="aae016" name="check_flag"  readonly="true"/>
								<powersi:textfield label="复审人"   id="aae014" name="check_man"  readonly="true"/>
								<powersi:textfield label="复审时间" id="aae015" name="check_date"  readonly="true" colspan="2"/>
								<td colspan="1"></td>
							</tr>
							<tr>
								<powersi:textfield label="开始时间" id="aae030" name="admit_effect"  readonly="true" colspan="3"/>
								<powersi:textfield label="结束时间" id="aae031" name="admit_date"  readonly="true" colspan="3"/>
							</tr>
							<tr>
								<powersi:textfield label="审批意见" id="bkb103" name="audit_opinion" readonly="true" colspan="7"/>
							</tr>
							<tr>
								<powersi:textfield label="医院意见" id="bke013" name="outhosp_opinion"  readonly="true" colspan="3"/>
								<powersi:textfield label="意见时间" id="bke672" name="outhosp_audit_date"  readonly="true" colspan="3"/>
							</tr>
							<tr>
								<powersi:textfield label="申请时间" id="aae127" name="apply_date"  readonly="true"/>
								<powersi:textfield label="录入人"   id="aae011" name="input_man"  readonly="true"/>
								<powersi:textfield label="录入时间" id="aae036" name="input_date"  readonly="true" colspan="2"/>
								<td colspan="1"></td>
							</tr>
							<tr>
								<powersi:textfield label="备注" id="aae013" name="remark"  readonly="true" colspan="7"/>
							</tr>
							
						</powersi:editorlayout>
					</powersi:form>
				</powersi:groupbox>
			</td>
		</table>
		<powersi:errors/>
	<script type="text/javascript">
		
		window.onload = function(){
	//		$('#akb020').val("<%=hospital_id%>");
	//	 	$('#akb021').val("<%=hospital_name%>");
	//	 	var hospital_id = $('#akb020').val();
			//申请结束时间为当天
			 var myDate = new Date();
			 var year = myDate.getFullYear();
			 var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
			 var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
			 
			 var lastMonthDate = myDate;
			 lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
			 var lastYear = lastMonthDate.getFullYear();
			 var lastMonth = (lastMonthDate.getMonth()+1).toString().length ==1?"0"+(lastMonthDate.getMonth()+1).toString():(lastMonthDate.getMonth()+1).toString();
		
			 $('#aae127_start').val(lastYear+"-"+lastMonth+"-"+"01");
			 $('#aae127_end').val(year+"-"+month+"-"+day);
			 $('#aae036').val("0000"+"-"+"00"+"-"+"00");
			
		}
	function queryAll(){
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
		var saveItemData = $("#mainForm").serialize();
		postJSON("<%=path%>/medicare/MzchoHospitalBusApplyAction!getAuditPerInfoList.action",saveItemData, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		    	//加载结果集到datagrid
				auditgrid.loadData(json.data);
		});
	}	
	document.onkeydown = function(event){
		  var e = event || window.event || arguments.callee.caller.arguments[0];
		   if(e && e.keyCode==13){ // enter 键
			   queryAll();
	    }
	}		
	function selectRow(rowdata, rowid, rowobj){
		var row = auditgrid.getRow(rowid);
		
    	//popupAlert(powersi.tostring(row));
		clearForm('#dForm');
	    json2form('#dForm', row);	
		}
	function Register(){
		alert("此功能后续开发,敬请期待...");
	}

	function clearall() {
		
		$("#arg_value").val("");
		$("#bke023").val("");
		$("#aae016").val("");
		$("#bka004").val("");
		$("#bka006").val("");
		
		auditgrid.reset();
		dForm.reset();
	}
	</script>
	</body>
</powersi:html>