<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="processTag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<powersi:html>
<head>
<powersi:head title="业务自查申诉处理" target="_self"/>
</head>
<body>
<powersi:form name="mainForm" id="mainForm"  >
<powersi:hidden id="aaz217" name="#request.diagnoseMap.aaz217"/>
<powersi:hidden id="aaz328" name="#request.diagnoseMap.aaz328"/>
<powersi:hidden id="flag" name="flag"/>
<powersi:hidden id="bpd413" name="bpd413"/>
<powersi:hidden id="fileName" name="fileName"/>
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
							<powersi:textfield name="#request.diagnoseMap.akc186" label="其他诊断" readonly="true" colspan="5"/>
						</powersi:editorlayout-row>
					</powersi:editorlayout>
				</powersi:panelbox>
			</td>
			<td>
				<powersi:tabbedpanel id="divTabsSS">
					<powersi:tab id="tabSSInsert" target="divTabSSInsert" label="疑点列表" />
					<powersi:tab id="tabSSInfo" target="divTabSSInfo" label="申诉材料" />
					<div id="divTabSSInsert">
						<powersi:datagrid id="gridRules" onAfterShowData="loadComplete" height="210" usePager="false">
							<powersi:datagrid-column render="rowICO" width="25" />
							<powersi:datagrid-column display="规则名称" name="drl_def_name" render="rulesName" width="100%" />
							<powersi:datagrid-column display="规则描述" name="drl_def_desc" width="180" />
						</powersi:datagrid>
					</div>
					<div id="divTabSSInfo">
						<powersi:datagrid id="gridComplaint"  height="170"  usePager="false">
							<powersi:datagrid-column display="附件名称" align="left"  name="ape022" width="100%" />
							<powersi:datagrid-column display="操作" align="left"  render="deleteFileRow" width="40" />
						</powersi:datagrid>
						<powersi:editorlayout cols="22%,43%,10%,20%">
						<tr>
							<powersi:file id="fileUpload" name="fileUpload" size="70" label="申诉材料上传"/>
							<td align="left">
								<powersi:button id="btUpload" title="点击上传附件" label="上 传" onclick="uploadFile()"/>
							</td>
						</tr>
						</powersi:editorlayout>
					</div>
				</powersi:tabbedpanel>
			</td>
		</tr>
	</table>
	<div style="display: none height: auto;" id="divKa52">
		<powersi:panelbox title="疑点场景" iconClass="icon-inbox" allowCollapse="false">
			<powersi:datagrid id="gridKa52"  frozen="true"    >
				<powersi:toolbar>
					<powersi:toolbar-item id="addSS" text="申诉" title="点击申诉问题"
					icon="true" click="appealExplain" />
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
		</powersi:panelbox>
	</div>
	<div id="addDetailDiv" style="display:none; height: auto;">
		<powersi:hidden id="aaz529" />
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:textfield id="ape138" label="申诉内容" colspan="5" required="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textarea id="aae013" label="解释说明" rows="5" cols="10" colspan="5"></powersi:textarea>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:buttons>
					<powersi:button label="保存" key="button_save" onclick="saveRemark();" />
					<powersi:button label="关闭" key="button_close" onclick="closeDiv();" />
				</powersi:buttons>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</div>
	<div id="showKA54Detail" style="display:none; height: auto;">
		<table class="tableEditor"><colgroup><col width="20%" /><col width="80%" /></colgroup>
			<tbody>
			   	<tr>
				   	<td class="tdLabel">
				   		<label for="textshow" class="textLabel">说明<span class="requiredLabel">&nbsp;</span></label>
				   	</td>
					<td class="tdInput" colspan="2">
						<textarea name="textshow"  rows="8" id="textshow" class="textarea"></textarea>
					</td>
			    </tr> 
			    <tr>
					<td colspan="2">
						<button type="button" id="btClose" class="button" onclick="closeDiv1()">关闭</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</powersi:form>
</body>
</powersi:html>
<script>
var Local_drl_def_name;
var selectIndex = 0;
function initBizRules(){
	var url = "${rootPath}/medicare/DeclarePayAction!getBusinessAppealRules.action?aaz217="+$("#aaz217").val();
	gridRules.set("url",url);
}
function initData4Complaint(){
	var url = "${rootPath}/medicare/DeclarePayAction!getAppealDataInfo.action?aaz217="+$('#aaz217').val();
	gridComplaint.set("url",url);
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
	initData4Complaint();
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
	//initFileUpload();
	//$("#btUpload").click(function(){
	//	uploadFile();
	//	});
 	});

function uploadFile(){
	var fileName = $('#fileUpload').val();
	if(powersi.isempty(fileName)){
		popupAlert("请选择导入文件");
		return;
	}
	var aaz217 = $("#aaz217").val();
	var fileNames = new Array();
	fileNames = fileName.split("\\");
	var fileName = fileNames[fileNames.length-1];
	var params = {aaz217 : aaz217, fileName : fileName};
	$.ajax({
   		type: "post",
   		data:  params,
   		url: '${rootPath}/medicare/DeclarePayAction!checkFileName.action',
   		dataType: "json",
   		success: function(result){
   			if (result.data == null){
   				popupAlert('文件上传失败，请稍后再试!');
   				return ;
   			}
   		    if(result.data) {
   		    	/* if(confirm(fileName+"  :文件已上传,是否覆盖?") == true){
   		    		showRunning(true);
	   		    	var fileUpload = $('#fileUpload');
	   				fileUpload.fileupload('send', {
	   			        fileInput: fileUpload,
	   			        formData:{bpd413:aaz217,flag:"1"}
	   			    }).complete(
	   			    		function (result, textStatus, jqXHR) {
	   			    			showRunning(false);
	   			    			initData4Complaint();
	   			    			showFlowPic(aaz217);
	   			    		});
		   		} else {
			   		return;
			   	} */
   		    } else {
   		    	if(confirm("您确认导入文件" + fileName + "吗?") != true){
   					return;
   				}
   		    	showRunning(true);
   		    	initFileUpload();
	   		}
   		}
	});
}

function initFileUpload(){
	
	var fileName = $('#fileUpload').val();
	if(powersi.isempty(fileName)){
		popupAlert("请选择导入文件");
		return;
	}
	var aaz217 = $("#aaz217").val();
	var fileNames = new Array();
	fileNames = fileName.split("\\");
	var fileName = fileNames[fileNames.length-1];
	
	$("#bpd413").val(aaz217);
	$("#flag").val("0");
	$("#fileName").val(fileName);
	
	$("#mainForm").ajaxSubmit( {   
        url : "${rootPath}/medicare/DeclarePayAction!uploadAppealData.action",   
        datatype : "json",
        success : function(json){ 
        		initData4Complaint();
            },  
       		error:function(json){ 
           	 popupInfo(json.message);  
             }   
    });   
	
}

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
	var url = "${rootPath}/medicare/DeclarePayAction!getAppealDataInfo.action";
	var params = {};
	params.aaz328 = aaz328;
	params.aaz217 = aaz217;
	gridKa52.setParms(params);
	gridKa52.set("url",url);
}

function deleteFileRow(row,index,value){
	var a =[];
	a.push('<a onclick="deleteFile(');
	a.push("'"+row["aaz530"]+"'");
	a.push(')">');
	a.push('删除');
	a.push("</a>");
	return a.join('');
}

function deleteFile(aaz530){
	if (!confirm("您确认删除该附件?")) {
        return;
    }
	postJSON("${rootPath}/medicare/DeclarePayAction!deleteAppealData.action?aaz530="+aaz530,deleteReturn);
}

function deleteReturn(json){
	if(!checkJSONResult(json)){
		window.returnValue="0";
		return;
    }
    else{
    	alert(json.message);
    	window.returnValue="1";
    	initData4Complaint();
	}
}

function rowICO(row,index,value){
	var a = [];
	if(row["ape139"] != 0){
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

function closeDiv1(){
	$('body').unblock();
	$("#textShow").val("");
	
}
function resetRemark(){
	$("#aaz328").val("");
	$("#remarkSS").val("");
	$("#ssContent").val("");
	$("#ake001").val("");
	$("#ake002").val("");
	$("#aka070").val("");
}
function checkreturn(json){
	if(!checkJSONResult(json)){
	    return;
    }
	alert("保存成功");
	initBizRules();
	closeDiv();
}
function saveRemark(){
	var data = gridKa52.getSelectedRow();
	var aaz356 = data.aaz356;
	var aaz529 = $("#aaz529").val();
	var aaz328 = $("#aaz328").val();
	var ape138 = $("#ape138").val();
	var aae013 = $("#aae013").val();
	
	var params = {};
	params.aaz529 = aaz529;
	params.aaz328 = aaz328;
	params.aaz356 = aaz356;
	params.ape138 = ape138;
	params.aae013 = aae013;
	postJSON("${rootPath}/hosp/appealAction!saveAppealExplain.action", params, checkreturn);
}

var dlgType = 1;
var dlg = null;

function closeDiv(){
	if(dlgType == 1){
		if(dlg){
			dlg.hide();
		}	
	} else if(dlgType == 2){
		if(dlg2){
			dlg2.hide();
		}
	}
}
 
 
function appealExplain(){
	var row = gridKa52.getSelectedRow();
	if (row == null){
		alert('请选择需要申诉的记录！');
		return ;
	}
	$('#aaz529').val('');
	$('#ape138').val('');
	$('#aae013').val('');
	var params = {};
	params.aaz328 = $('#aaz328').val();
	params.aaz356 = row.aaz356;
	postJSON("${rootPath}/hosp/appealAction!getAppealExplain.action", params, appealInfoCall);
}
function appealInfoCall(json){
	var data = json.data;
	if (data != null){
		$('#aaz529').val(data.aaz529);
		$('#ape138').val(data.ape138);
		$('#aae013').val(data.aae013);
	}
	dlg = popupDiv("#addDetailDiv", '疑点申诉', 600);
	dlgType = 1;
}
</script>