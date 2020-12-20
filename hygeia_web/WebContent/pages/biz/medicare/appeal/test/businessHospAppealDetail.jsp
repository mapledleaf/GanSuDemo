<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="processTag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.powersi.hygeia.framework.util.UtilFunc,com.powersi.hygeia.framework.BusiContext"%>

<powersi:html>
<head>
<powersi:head title="业务自查申诉处理" target="_self"/>
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
<script type="text/javascript">
function getDoubtDetailList(aaz328){
	selectTab('#divTabsDetail', 0);
	var urlAction = "${rootPath}/doubtconfirm/DoubtAuditAction!getDoubtDetail2.action?aaz328="+aaz328;
	gridDetail.set("url",urlAction);
	gridDetail.refreshSize();
	
}
function getKa52List(aaz328){
	var urlAction = "${rootPath}/doubtconfirm/DoubtAuditAction!queryYwxx.action?ka59.aaz328="+aaz328;
	gridKa52.set("url",urlAction);
	gridKa52.refreshSize();
}
function showDoubt(aaz328,detail_flag, aaz217){
	var url = "${rootPath}/hosp/appealAction!getBusinessStayAppealInfo.action";
	var params = {};
	params.aaz328 = aaz328;
	params.aaz217 = aaz217;
	gridKa52.setParms(params);
	gridKa52.set("url",url);
}

function rowICO(row,index,value){
	var a = [];
	if(row["wkd030"]){
		a.push('<i title="已申诉"  class="');
		a.push("blue icon-ok");
		a.push('"> ');
		a.push(" </i>");
	}
	else{
		a.push('<i title="未申诉" style="color:red" class="');
		a.push("icon-question-sign");
		a.push('"> ');
		a.push(" </i>");
	}
	return a.join('');
}

function rulesName(row,index,value){
		var a = [];
		a.push('<div onclick="showDoubt(');
		a.push(row["aaz328"]);
		a.push(',');
		var detail_flag="'"+row["detail_flag"]+"'";
		a.push(detail_flag);
		a.push(',');
		var aaa027 = "'"+row["aaz217"]+"'";
		a.push(aaa027);
		a.push(')" >');
		var wka111 = row["wka111"];
		if(wka111 == "0"){
			a.push('<i class="');
			a.push("red icon-bolt");
			a.push('"> ');
			a.push("  <span>（违规） </span></i>");
		}
		else if(wka111 == "1"){
			a.push('<i class="');
			a.push("green icon-fire");
			a.push('"> ');
			a.push("  <span>（可疑） </span></i>");
		}
		else{
			a.push('<i class="');
			a.push('icon-warning-sign"');
			a.push(' style="color:#fcc92f"> ');
			a.push(" <span >（提醒）</span></i>");
		}
		var drl_def_name = row["drl_def_name"];
		if(row["aaa005"]){
			drl_def_name = drl_def_name.replace("N",row["aaa005"]);
		}
   		a.push(drl_def_name);
		a.push('</div>');
		return a.join('');
 }

function showKA59Remark(json){
	if(!checkJSONResult(json)){
	    return;
    }
	if(json.data){
		$("#remarkSS").val(json.data.wkd031);
	}
	addDetail();
}
var dlg = null;
function audit(){
	$("#aae013").val('');
	dlg = popupDiv("#addAuditDiv", '审核', 600);
}
function appealInfoCall(json){
	if (!checkJSONResult(json)){
		return ;
	}
	popupSuccess('审核成功!',3000);
	setDialogReturn(true);
	closeDialog();	
}
function closeDiv(){
	dlg.hide();
}

function saveAudit(){
	var aae013 = $("#aae013").val();
	var bpd406 = $(':radio[name="bpd406"]:checked').val();
	var params = {};
	params.aaz328 = $('#aaz328').val();
	params.bpd406 = bpd406;
	params.aae013 = aae013;
	postJSON("${rootPath}/hosp/appealAction!audit.action", params, appealInfoCall);
}
</script>
</head>
<body>
<powersi:form name="mainForm" id="mainForm"  >
<powersi:hidden id="aaz217" name="#request.diagnoseMap.aaz217"/>
<powersi:hidden id="aaz328" name="#request.diagnoseMap.aaz328"/>
	<table width="100%" style="border-bottom-style:solid; border-bottom-color:#dce8f1;border-bottom-width:1px;">
		<tr>
			<td width="60%" style="border-right-style:solid; border-right-color:#dce8f1;border-right-width:1px;">
				<powersi:panelbox title="基本信息" iconClass="icon-inbox" allowCollapse="false">
					<powersi:editorlayout cols="6">
						<powersi:editorlayout-row>
							<powersi:textfield name="#request.diagnoseMap.aac003" label="姓名" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.aac002" label="证件号码" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.aac004_name" label="性别" readonly="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield name="#request.diagnoseMap.aac006" label="出生日期" readonly="true" 
								format="{0,date,yyyy-MM-dd}" />
							<powersi:textfield name="#request.diagnoseMap.akc021_name" label="人员类别" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.akb021" label="就诊医院" readonly="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield name="#request.diagnoseMap.akc264" label="医疗总费用" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.ake039" label="统筹支付" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.bka003_name" label="待遇类型" readonly="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield name="#request.diagnoseMap.aae030" label="入院日期" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.aae031" label="出院日期" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.akc194" label="结算时间" readonly="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield name="#request.diagnoseMap.aae386" label="科室" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.akc050" label="入院主诊断" readonly="true" />
							<powersi:textfield name="#request.diagnoseMap.akc185" label="出院主诊断" readonly="true" />
						</powersi:editorlayout-row>
						<powersi:editorlayout-row>
							<powersi:textfield name="#request.diagnoseMap.akc186" label="其他诊断" readonly="true" />
						</powersi:editorlayout-row>
					</powersi:editorlayout>
				</powersi:panelbox>
			</td>
			<td>
				<powersi:tabbedpanel id="divTabsSS">
					<powersi:tab id="tabSSInsert" target="divTabSSInsert" label="疑点列表" />
					<div id="divTabSSInsert">
						<powersi:datagrid id="gridRules" onAfterShowData="loadComplete" height="210" usePager="false">
							<powersi:datagrid-column render="rowICO" width="25" />
							<powersi:datagrid-column display="规则名称" name="drl_def_name" render="rulesName" width="100%" />
							<powersi:datagrid-column display="规则描述" name="drl_def_desc" width="180" />
						</powersi:datagrid>
					</div>
				</powersi:tabbedpanel>
			</td>
		</tr>
	</table>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="疑点场景"/>
		<powersi:tab id="tab2" target="divTab2" label="申诉说明"/>
		<powersi:tab id="tab3" target="divTab3" label="申诉材料"/>
		
		<div id="divTab1" class="titerbox" style="overflow-x:hidden;">
			<powersi:datagrid id="gridKa52"  frozen="true"    >
				<powersi:toolbar>
					<powersi:toolbar-item id="addSS" text="审核通过" icon="true" click="audit" />
				</powersi:toolbar>
				<powersi:datagrid-column display="三大目录名称" name="ake002" 
					minWidth="60" width="15%"/>
				<powersi:datagrid-column display="三大目录编码" name="ake001" 
					minWidth="60" width="10%"/>
				<powersi:datagrid-column display="药品名称" name="ake006" 
					minWidth="60" width="15%"/>
					<powersi:datagrid-column display="药品编码" name="ake005" 
					minWidth="60" width="10%"/>
				<powersi:datagrid-column display="单价" name="akc225" 
					minWidth="60" width="6%"/>
				<powersi:datagrid-column display="数量" name="akc226" 
					minWidth="60" width="6%"/>
				<powersi:datagrid-column display="自费金额" name="aae019"
					minWidth="60" width="6%"/>
				<powersi:datagrid-column display="费用总额" name="akb065"
					minWidth="60" width="6%"/>
				<powersi:datagrid-column display="费用发生时间" name="bka051"
					minWidth="60" width="12%"/>
					<powersi:datagrid-column display="疑点描述" name="aae013"
					minWidth="60" width="14%"/>
			</powersi:datagrid>
		</div>
		
		<div id="divTab2" style="overflow-x:hidden;">
			<div class="col-12">
				<powersi:datagrid id="gridAppealExplain"
					enabledExportExcel="true" exportFileName="'申诉列表'" frozen="false" 
					selectRowButtonOnly="false">
					<powersi:datagrid-column display="申诉理由" name="ape138"
						minWidth="100" width="20%"/>
					<powersi:datagrid-column display="解释说明" name="aae013"
						minWidth="100"  width="40%"/>
					<powersi:datagrid-column display="申诉人" name="ape008"
						minWidth="100"  width="20%"/>
					<powersi:datagrid-column display="申诉时间" name="ape711"
						minWidth="100"  width="20%"/>
				</powersi:datagrid>
			</div>
		</div>
		
		<div id="divTab3" style="overflow-x:hidden;">
			<processTag:appealData/>
		</div>
	</powersi:tabbedpanel>
	</powersi:form>
	<div id="addAuditDiv" style="display:none; height: auto;">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:radio id="bpd406" name="bpd406" label="审核状态"
						list="#{'0': '不是问题','1':'是问题'}" value='1' colspan="5"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textarea id="aae013" label="备注" rows="5" cols="10" colspan="5"></powersi:textarea>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:buttons>
					<powersi:button label="保存" key="button_save" onclick="saveAudit();" />
					<powersi:button label="关闭" key="button_close" onclick="closeDiv();" />
				</powersi:buttons>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</div>
</body>
</powersi:html>
<script>
var Local_drl_def_name;
var selectIndex = 0;
function initBizRules(){
	var url = "${rootPath}/bizutil/bizAuditAction!getViolateRules.action?aaz217="+$("#aaz217").val();
	gridRules.set("url",url);
}

var querysql = {};
function loadComplete(){
	var rows = gridRules.getData();
	if(rows){
		for(var r in rows){
			var row = rows[r];
			if(row.aaz328 == $("#aaz328").val()){
				gridRules.select(r);
				Local_drl_def_name = row["drl_def_name"];
				showDoubt(row.aaz328,row.detail_flag,row.aaz217);
				var aaa027 = row.aaa027;
				querysql = {'aaz217':$("#aaz217").val(),'aaa027':aaa027,'tjlb':'2'};
			    $("#aaa027").val(aaa027);
			}
		}
	}
}

$(document).ready(function() {
	initBizRules();
	$( "#divTabsDetail" ).tabs({
       	activate: function( event, ui ) {
       		var newid = ui.newTab.attr('id');
       		if(newid == 'tabSum'){
       			gridDetail.refreshSize();
       		}
       		if(newid == 'tabDetail'){
       			gridDetailList.refreshSize();
       		}
       	}
       });
	
	bindAfterSelectTab('#divTabs', showTabsEvent);
});

function showTabsEvent(newTabId, oldTabId){
	if (newTabId == 'tab2'){
		var rows = gridKa52.getSelectedRows();
		if (rows.length != 1){
			popupAlert('请选择一行记录查看申诉说明!');
			return ;
		}
		var data = rows[0];
		aaz328 = data.aaz328;
		ape139 = data.ape139;
		loadAppealExplain(aaz328);
	}else if (newTabId == 'tab3'){
		var rows = gridKa52.getSelectedRows();
		if (rows.length != 1){
			popupAlert('请选择一行记录查看申诉说明!');
			return ;
		}
		var data = rows[0];
		loadAppealData(data.aaz217);
	}
}

function loadAppealExplain(aaz328){
	var url = "${rootPath}/hosp/appealAction!getAppealExplainList.action";
	var params = {};
	params.aaz328 = aaz328;
	gridAppealExplain.setParms(params);
	gridAppealExplain.set("url",url);
}
</script>