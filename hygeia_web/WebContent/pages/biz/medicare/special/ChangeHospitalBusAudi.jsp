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
%>
<powersi:html>
		<powersi:head title="转诊转院业务审批"/>
	<body>
		<powersi:form id="mainForm">
		<div id = "div1" style="display: none">
			 <powersi:hidden id="ake017" name="mediSpecDto.ake017" />
			 <powersi:hidden id="akb020" name="mediSpecDto.akb020" />
			 <powersi:hidden id="indi_id" name="indi_id" value="indi_id" />
			<powersi:hidden id="idcard" name="idcard" value="idcard" />
			<powersi:hidden id="arg_name" name="mediSpecDto.arg_name" />
		</div>
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_query"   value="查询"    onclick="queryAllInfo()"/>
				<%-- <powersi:button key="button_print"   value="打印"     onclick="print()"/> --%>
				<powersi:button id="button_audit"   key="button_audit"   value="审批通过" onclick="auditPass()"/>
				<powersi:button id="button_auditNO" key="button_auditNo" value="审批不通过" onclick="auditPassNo()"/>
				<powersi:button key="button_reset"   value="重置"     onclick="clearall()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="9%,18%,9%,15%,9%,15%,9%,16%">
				<powersi:editorlayout-row>
					<powersi:textfield id="arg_value" name="mediSpecDto.arg_value" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false" />
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_start" name="mediSpecDto.aae127_start" key="aae127_start" readonly="true"/>
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_end" name="mediSpecDto.aae127_end" key="aae127_end" readonly="true"/>
					<powersi:codeselect label="人员类型" id="bka004" name="mediSpecDto.bka004" key="aac012" codeType="bka004" disabled="false" />
				</powersi:editorlayout-row>
					
				<tr>
					<td align="right" class="tdLable" style="background:#f4f8fa none repeat scroll 0 0">转院类别</td>
						<td>
							<select id="jsz005" name="mediSpecDto.jsz005" class="select" onchange="hospBotton()">
								<option value="1">本院转出</option>
								<option value="2">转入本院</option>
							</select>
						</td>
					<powersi:hidden id="bke058" name="mediSpecDto.bke058"  />
					<td colspan="6"></td>
				</tr>
			</powersi:editorlayout>
			</powersi:panelbox>
			</powersi:form>
			
	 <div class="row">
	 	 <div class="col-5">
			<powersi:panelbox title="申请人员列表">
				<powersi:datagrid id="changegrid" formId="mainForm" onDblClickRow="dbSelectRow" checkbox="true" 
				                  height="380" selectRowButtonOnly="true" delayLoad="true">
					<powersi:datagrid-column id="aac003" name="name"                   label="姓名"    width="100" frozen="true"/>
					<powersi:datagrid-column id="aaz267" name="serial_apply"           label="申请号"  width="80"/>
					<powersi:datagrid-column id="akb021" name="apply_hospital_name"    label="转出医院" width="200"/>
					<powersi:datagrid-column id="ake014" name="end_date"               label="转出时间" width="120"/>
					<powersi:datagrid-column id="akc172" name="hospital_name"          label="转入医院" width="200"/>
					<powersi:datagrid-column id="ake016" name="begin_date"             label="转入时间" width="120"/>
					<powersi:datagrid-column id="bke129" name="patient_date"           label="申请时间" width="120"/>
					<powersi:datagrid-column id="bke058" name="inhosp_audit_flag"      label="审核标志"     hide="true" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
		 <div class="col-7">
			<powersi:panelbox title="人员详细信息">
			<powersi:form id="dForm" >
				<powersi:editorlayout cols="13%,12%,11%,11%,13%,14%,12%,14%">
				<tr>
			    	<powersi:textfield  id="aac003" name="name"       label="姓名"    readonly="true"/>
					<powersi:textfield  id="aac004" name="sex"          label="性别"    readonly="true"/>
					<powersi:textfield  id="aac006" name="birthday"     label="出生日期"  readonly="true" />
					<powersi:textfield  id="aaz267" name="serial_apply" label="序列号"    readonly="true" />
				</tr>
				<tr>
					<powersi:textfield  id="aab069" name="corp_name"       label="单位名称" readonly="true" colspan="3"/>
					<powersi:codeselect  id="bka004" name="pers_type"       label="人员类别" readonly="true" disabled="true" codeType="bka004"/>
					<powersi:codeselect  id="bka006" name="treatment_type"  label="转院类型 " readonly="true" disabled="true" codeType="bka006"/>
				</tr>
				<tr>
					<powersi:textfield  id="akb021" name="apply_hospital_name" label="申请医院" readonly="true" colspan="3"/>
					<powersi:textfield  id="bke129" name="patient_date"        label="申请时间" readonly="true"/>
					<powersi:textfield  id="bke128" name="patient_name"        label="申请人"  readonly="true"/>
				</tr>
				<tr>
					<powersi:textfield  id="bka020" name="in_dept_name" label="科室"  readonly="true"/>
					<powersi:textfield  id="bka023" name="in_bed"       label="床位号" readonly="true" />
					<powersi:textfield  id="bka025" name="patient_id"   label="住院号" readonly="true"/>
					<td colspan="2"></td>
				</tr>
				<tr>
					<powersi:textfield  id="ake014" name="end_date"   label="转出时间" readonly="true" colspan="3"/>
					<powersi:textfield  id="ake016" name="begin_date" label="转入时间" readonly="true" colspan="3"/>
				</tr>
				<tr>
					<powersi:textfield  id="akc172" name="hospital_name"   label="转入医院" readonly="true"  colspan="3"/>
					<powersi:textfield  id="bke127" name="patient_opinion" label="病人意见" readonly="true"  colspan="3"/>
				</tr>
				<tr>
					<powersi:textfield  id="bke011" name="disease_desc"    label="病情摘要及转诊理由"  readonly="true" colspan="7"/>
				</tr>
				<tr>
					<powersi:textfield  id="bke008" name="outhosp_opinion" label="转出医院医务科意见" readonly="true"  colspan="3"/>
					<powersi:textfield  id="bke010" name="inhosp_opinion"  label="转入医院医务科意见" readonly="false" colspan="3"/>
				</tr>
				
				<tr>
					<powersi:textfield  id="bke058" name="inhosp_audit_flag" label="审核标志" readonly="true"/>
					<powersi:textfield  id="bke021" name="audit_man"         label="初审人"  readonly="true"/>
					<powersi:textfield  id="bke022" name="audit_date"        label="初审时间" readonly="true"/>
					<powersi:textfield  id="aae014" name="check_man"         label="复审人"  readonly="true"/>
				</tr>
				<tr>
					<powersi:textfield  id="aae015" name="check_date" label="复审时间" readonly="true"/>
					<powersi:textfield  id="bke024" name="audit_opinion" label="中心审核意见" readonly="true"  colspan="5"/>
				</tr>
				<tr>
					<powersi:textfield  id="aae013" name="remark" label="备注" readonly="false" colspan="7"/>
				</tr>
				</powersi:editorlayout>
				</powersi:form>
			</powersi:panelbox>
		</div>
	</div>
	<powersi:errors/>
	
	<script type="text/javascript">
	window.onload = function(){
		
	    $('#akb020').val("<%=hospital_id%>");
	 	$('#akb021').val("<%=hospital_name%>");
	 	var hospital_id = $('#akb020').val();
	 	var ake017 = $('#akb020').val();
	 	document.getElementById("ake017").value = ake017;
	 	
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
		
		 $("#bke023").val("");
		 $("#aae016").val("");
		 $("#aae100").val("1");
		 $('#aaz267').attr("readonly",true);
		 bottonDisa();
	}
	 document.onkeydown=function(event){
		  var e = event || window.event || arguments.callee.caller.arguments[0];
		   if(e && e.keyCode==13){ // enter 键
			   queryAllInfo();
         }
	  }	
	function queryAllInfo(){
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
		if($("#jsz005").val() == "1" || $("#jsz005").val() != null){
			var jsz005 = "本院转出";
		}else{
			var jsz005 = "转入本院";
		}
		changegrid.reset();
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
		postJSON("<%=path%>/medicare/ChangeHospitalBusApplyAction!getChaPersInfoList.action",saveItemData, function(json){
			if(!checkJSONResult(json)){
			    return;
		    }
		    	//加载结果集到datagrid
				changegrid.loadData(json.data);
		});
	}
	
	function dbSelectRow(rowdata, rowid, rowobj){
		var row = changegrid.getRow(rowid);
	
    	//popupAlert(powersi.tostring(row));
		clearForm('#dForm');
	    json2form('#dForm', row);
}
	//保存审核状态信息
	function auditPass(){
		var data = changegrid.getSelectedRows();
				
        if(powersi.rows_length(data) == 0){
			popupAlert("请先勾选要审核的数据!");
			return;
	}
		var detail = powersi.tostring(data);
		var bke010 = $("#bke010").val();
			 postJSON("<%=path%>/medicare/ChangeHospitalBusApplyAction!auditPassInfo.action",
					         {"applyList":detail,"audit_flag":1,"inhosp_opinion":bke010},function(json){
					if(!checkJSONResult(json)){
					    return;
				    }
					popupInfo(json.message);
					queryAllInfo();
					return;
				});
	}
	//保存审核不通过状态
	function auditPassNo(){
		var data = changegrid.getSelectedRows();
		
        if(powersi.rows_length(data) == 0){
			popupAlert("请先勾选要审核的数据!");
			return;
	}
        var detail = powersi.tostring(data);
        var bke010 = $("#bke010").val();
        postJSON("<%=path%>/medicare/ChangeHospitalBusApplyAction!auditPassInfo.action",
		         {"applyList":detail,"audit_flag":2,"inhosp_opinion":bke010},function(json){
		if(!checkJSONResult(json)){
		    return;
	    }
		popupInfo(json.message);
		queryAllInfo();
		return;
	});
	}
	function hospBotton(){
		 var jsz005 = $("#jsz005").val();
		 if(jsz005 == '2'){
			$("#button_audit").attr("disabled",false);
		    $("#button_auditNO").attr("disabled",false);
		 }else{
			 $("#button_audit").attr("disabled",true);
		     $("#button_auditNO").attr("disabled",true);
		 }
	}
	 function bottonDisa(){
	    var jsz005 = $("#jsz005").val();
	    if(jsz005 == '1'){
	    	$("#button_audit").attr("disabled",true);
	    	$("#button_auditNO").attr("disabled",true);
	    }else{
	    	$("#button_audit").attr("disabled",false);
	    	$("#button_auditNO").attr("disabled",false);
	    }	
	}
	
	function clearall(){
		
		$("#aka083").val("");
		$("#arg_value").val("");
		$("#bka004").val("");
		$("#bke023").val("");
		changegrid.reset();
		dForm.reset();
	}
	
/**
	//增加操作列
	function renderOperate(row,index,value){
		var a = [];
		a.push('<input type="button" value="" class="linkbutton"' );
		a.push('onclick="(');
		a.push(index);
		a.push(')"');
		a.push("/>");
		
		a.push("&nbsp;&nbsp;");
		
		a.push('<input type="checkbox" value="checkbox" id="checkbox"' );
		a.push('onclick="');
		a.push(index);
		a.push(' "');
		a.push("/>");
		return a.join('');
	}
*/	
	</script>
	</body>
</powersi:html>