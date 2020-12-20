<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<powersi:html>
<powersi:head title="体检费用及结算单查询" />
<body>
	<powersi:form id="quryForm" namespace="/medicare" action="/health/ExaminationAction!findHealthInfo.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" key="button_query" />
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="5%,10%,10%,8%,10%,10%,10%,9%,8%,16%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="bkh012" name="setMealDTO.bkh012"   label="人员类别" cssClass="select2" codeType="bkh012" headerKey="" headerValue="请选择..."  showValue="false" />
					<powersi:codeselect label="结算状态" id="bkh017" name="setMealDTO.bkh017" list="#{'1':'未结算','2':'已结算'}" />
					<powersi:textfield id="bkh016" key="登记日期" name="setMealDTO.aae030" mask="date" />
					<powersi:textfield id="ake010" key="结算日期" name="setMealDTO.aae031" mask="date" />
					<powersi:textfield id="aab069" name="setMealDTO.aab069" label="单位名称"  />	
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="arg_name" label="人员信息" name="setMealDTO.arg_name" cssClass="select2"
						list="#{'':'请选择...','idcard':'身份证号码','indi_id':'个人电脑号','patient_id':'体检登记号'}" />
					<td colspan="2"><powersi:textfield id="querystring" name="setMealDTO.querystring"  buttonText="读取身份证"/></td>
					<powersi:hidden id="flag"  name="setMealDTO.flag" value = "1" label="查询中心业务" />
				
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>		
	</powersi:form>
	
	<div class="row">
			<div id="yyDiv">
				<powersi:panelbox key="panel_result" title="医院业务信息" allowCollapse="false">
					<powersi:datagrid id="grid" formId="quryForm" delayLoad="true" showReload="false">
						<powersi:datagrid-column key="operate" render="renderOperate"  frozen="true"  width="200"/>
						<powersi:datagrid-column name="akb021" label="医疗机构名称" />
						<powersi:datagrid-column name="bkh012" label="体检人员类型" />
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
						<powersi:datagrid-column name="bkh014" label="业务序列号" />
						<powersi:datagrid-column name="bkh017" label="业务状态标志" />
				    </powersi:datagrid>
				</powersi:panelbox>
			</div>
	</div>
	
<powersi:errors />
<script type="text/javascript">
function renderOperate(row, index, value) {
	var a = [];
	a.push("&nbsp;&nbsp;");
	a.push('<input type="button" value="取消登记" class="linkButton"');
	a.push(' onclick="cancelRegister(');
	a.push(index);
	a.push(')"');
	if (row['bkh017'] == '2') {
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	a.push('<input type="button" value="取消结算" class="linkButton"');
	a.push(' onclick="cancelSettlement(');
	a.push(index);
	a.push(')"');
	if (row['bkh017'] == '1') {
		a.push(' disabled="disabled"');
	}
	a.push(" />");
	return a.join('');
}
//取消登记
function cancelRegister(index){
	var row = grid.getRow(index);
	var aac003 = row['aac003'];
	if (isEmpty(aac003)) {
		alert("姓名为空，不能取消登记！");
		return;
	}
	var aaz217 = row['aaz217'];
	if (isEmpty(aaz217)) {
		alert("该笔数据存在问题,体检登记号存在为空的情况，不能取消登记！");
		return;
	}
	if (!confirm("您确认取消" + aac003 + "的体检登记吗?")) {
		return;
	}
	postJSON(
			"${rootPath}/health/ExaminationAction!cancelRegister.action?"+
					"&setMealDTO.aaz217=" + aaz217,
			function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupInfo(json.message);
				grid.deleteRow(index);
			});
	
}

//取消结算
function cancelSettlement(index){
	var row = grid.getRow(index);
	var aac003 = row['aac003'];
	if (isEmpty(aac003)) {
		alert("姓名为空，不能取消登记！");
		return;
	}
	var aaz217 = row['aaz217'];
	if (isEmpty(aaz217)) {
		alert("该笔数据存在问题,体检登记号存在为空的情况，不能取消登记！");
		return;
	}
	if (!confirm("您确认取消" + aac003 + "的体检结算吗?")) {
		return;
	}
	postJSON(
			"${rootPath}/health/ExaminationAction!cancelSettlement.action?"
					 + "&setMealDTO.aaz217=" + aaz217,
			function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupInfo(json.message);
				grid.deleteRow(index);
			});
}


/**
 * 判断是否是空
 * @param value
 */
function isEmpty(value){
    if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
        return true;
    }
    else{
        value = value.replace(/\s/g,"");
        if(value == ""){
            return true;
        }
        return false;
    }
}

</script>
</body>
</powersi:html>