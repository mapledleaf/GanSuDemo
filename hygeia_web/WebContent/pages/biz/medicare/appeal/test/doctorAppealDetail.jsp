<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="processTag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<powersi:html>
<head>
<powersi:head title="医师自查申诉处理" target="_self"/>
<script src="${strutsPath}/include/fileupload.js" type="text/javascript"></script>
<script type="text/javascript">
function showDoubt(aaz328){
	var url = "${rootPath}/hosp/appealAction!getStayAppealDetail.action";
	var params = {};
	params.aaz328 = aaz328;
	gridKa52.setParms(params);
	gridKa52.set("url",url);
}
function deleteFileRow(row,index,value){
	var a =[];
	var ape023 = row["ape023"].replace(/\\/g,"\\\\");
	a.push('<a onclick="deleteFile(');
	a.push("'"+row["aaz530"]+"'");
	a.push(',');
	a.push("'"+ape023+"'");
	a.push(',');
	a.push("'"+row["ape022"]+"'");
	a.push(')">');
	a.push('删除');
	a.push("</a>");
	return a.join('');
}

function deleteFile(aaz530, ape023, ape022){
	if (!confirm("您确认删除该附件?")) {
        return;
    }
	postJSON("${rootPath}/hosp/appealAction!deleteAppealData.action?"+
			"aaz530="+aaz530+"&ape022="+encodeURI(encodeURI(ape022))+"&ape023="+encodeURI(encodeURI(ape023)),deleteReturn);
}

function deleteReturn(json){
	if(!checkJSONResult(json)){
		window.returnValue="0";
		return;
    }
    else{
    	popupAlert(json.message);
    	window.returnValue="1";
    	initData4Complaint();
	}
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

function checkreturn(json){
	if(!checkJSONResult(json)){
	    return;
    }
	popupSuccess("保存成功",3000);
	gridAppealExplain.reload();
	$('#divTabs').tabs().tabs('select', 2);
	closeDiv();
}
function saveAppealExplain(){
	var aaz529 = $("#aaz529").val();
	var aaz328 = $("#aaz328").val();
	var ape138 = $("#ape138").val();
	var aae013 = $("#aae013_explain").val();
	
	var params = {};
	params.aaz529 = aaz529;
	params.aaz328 = aaz328;
	params.ape138 = ape138;
	params.aae013 = aae013;
	postJSON("${rootPath}/hosp/appealAction!saveAppealExplain.action", params, checkreturn);
}

var dlgType = 1;
var dlg = null;
function addDetail() {
 	if(dlg){
		dlg.show();
	} else {
		dlg = popupDiv("#addDetailDiv", '疑点申诉', 600);
	}
 	dlgType = 1;
}

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

function appealExplain(aaz328){
	/* var row = gridKa52.getSelectedRow();
	if (row == null){
		alert('请选择需要申诉的记录！');
		return ;
	} */
	$('#aaz529').val('');
	$('#ape138').val('');
	$('#aae013_explain').val('');
	var params = {};
	params.aaz328 = aaz328;
	postJSON("${rootPath}/hosp/appealAction!getAppealExplain.action", params, appealInfoCall);
}
function appealInfoCall(json){
	var data = json.data;
	if (data != null){
		$('#aaz529').val(data.aaz529);
		$('#ape138').val(data.ape138);
		$('#aae013_explain').val(data.aae013);
	}
	dlg = popupDiv("#addDetailDiv", '疑点申诉', 600);
	dlgType = 1;
}
</script>
</head>
<body>
<powersi:form name="mainForm" id="mainForm"  >
	<powersi:hidden id="aaz010" name="#request.doctorMap.aaz010"/>
	<powersi:hidden id="aaz328" name="#request.doctorMap.aaz328"/>
	<div class="col-12">
		<powersi:panelbox title="基本信息" iconClass="icon-inbox" allowCollapse="false">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield name="#request.doctorMap.aac003" label="姓名" readonly="true" />
					<powersi:textfield name="#request.doctorMap.aab014" label="证件号码" readonly="true" />
					<powersi:textfield name="#request.doctorMap.bkc274" label="医师编号" readonly="true" />
					<powersi:textfield name="#request.doctorMap.aae005" label="联系电话" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="#request.doctorMap.akf001_name" label="科室分类" readonly="true" />
					<powersi:textfield name="#request.doctorMap.aae386" label="科室名称" readonly="true" colspan="5"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</div>
	<powersi:panelbox allowCollapse="false"/>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="疑点信息"/>
		<powersi:tab id="tab2" target="divTab2" label="疑点场景"/>
		<powersi:tab id="tab3" target="divTab3" label="申诉说明"/>
		<powersi:tab id="tab4" target="divTab4" label="申诉材料"/>
		
		<div id="divTab1" class="titerbox" style="overflow-x:hidden;">
			<div class="col-12">
				<powersi:datagrid id="gridKa52"
					enabledExportExcel="true" exportFileName="'疑点列表'" frozen="false" 
					selectRowButtonOnly="false">
					<powersi:datagrid-column display="疑点场景" render="renderFind" 
						minWidth="100" width="7%" isExport="false"/>
					<powersi:datagrid-column display="申诉标志" name="ape139_name" data="ape139" code="ape139" 
						minWidth="60" width="8%"/>
					<powersi:datagrid-column display="监控类型" name="aaa153_name" data="aaa153" code="aaa153" 
						minWidth="100" width="10%"/>
					<powersi:datagrid-column display="规则名称" name="drl_def_name"
						minWidth="150"  width="15%"/>
					<powersi:datagrid-column display="规则描述" name="drl_def_desc"
						minWidth="300"  width="30%"/>
					<powersi:datagrid-column display="业务开始日期" name="aae030"
						minWidth="100"  width="10%"/>
					<powersi:datagrid-column display="业务结束日期" name="aae031"
						minWidth="100"  width="10%"/>
					<powersi:datagrid-column display="监控日期" name="abb057" format="{0,date,yyyy-MM-dd}"
						minWidth="140"  width="10%"/>
				</powersi:datagrid>
			</div>
		</div>
		
		<div id="divTab2" style="overflow-x:hidden;">
			<processTag:doubtScene/>
		</div>
		
		<div id="divTab3" style="overflow-x:hidden;">
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
		
		<div id="divTab4" style="overflow-x:hidden;">
			<div class="col-12">
				<powersi:datagrid id="gridComplaint" height="90%" usePager="false">
					<powersi:datagrid-column display="附件名称" align="left"  name="ape022" width="100%" />
					<powersi:datagrid-column display="操作" align="left"  render="deleteFileRow" width="40" />
				</powersi:datagrid>
				<powersi:editorlayout cols="22%,43%,10%,20%">
				<tr>
					<td class="tdLabel">申诉材料上传
					</td>
					<td id="tdUploads">
						<input type="file" id="fileUpload" name="uploads" class="file" />
					</td>
					<td align="left">
						<powersi:button id="btUpload" title="点击上传附件" label="上 传"/>
					</td>
				</tr>
				</powersi:editorlayout>
			</div>
		</div>
	</powersi:tabbedpanel>
	
	<div id="addDetailDiv" style="display:none; height: auto;">
		<powersi:hidden id="aaz529" />
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:textfield id="ape138" label="申诉内容" colspan="5" required="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textarea id="aae013_explain" label="解释说明" rows="5" cols="10" colspan="5"></powersi:textarea>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:buttons>
					<powersi:button label="保存" key="button_save" onclick="saveAppealExplain();" />
					<powersi:button label="关闭" key="button_close" onclick="closeDiv();" />
				</powersi:buttons>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</div>
	</powersi:form>
</body>
</powersi:html>
<script>
function initData4Complaint(){
	var aaz328 = $("#aaz328").val();
	var url = "${rootPath}/hosp/appealAction!getAppealDataInfo.action?bpd413="+aaz328;
	gridComplaint.set("url",url);
}

function initAppealExplain(aaz328){
	var url = "${rootPath}/hosp/appealAction!getAppealExplainList.action";
	var params = {};
	params.aaz328 = aaz328;
	gridAppealExplain.setParms(params);
	gridAppealExplain.set("url",url);
}

$(document).ready(function() {
	var aaz328 = $('#aaz328').val();
	showDoubt(aaz328);
	initData4Complaint();
	initFileUpload();
	initAppealExplain(aaz328);
	$("#btUpload").click(function(){
		uploadFile();
		});
 	});

function uploadFile(){
	var fileName = $('#fileUpload').val();
	if(powersi.isempty(fileName)){
		popupAlert("请选择导入文件");
		return;
	}
	var aaz328 = $("#aaz328").val();
	var fileNames = new Array();
	fileNames = fileName.split("\\");
	var fileName = fileNames[fileNames.length-1];
	var params = {bpd413 : aaz328, fileName : fileName};
	$.ajax({
   		type: "post",
   		data:  params,
   		url: '${rootPath}/hosp/appealAction!checkFileName.action',
   		dataType: "json",
   		success: function(result){
   			if (result.data == null){
   				popupAlert('文件上传失败，请稍后再试!');
   				return ;
   			}
   		    if(result.data) {
   		    	if(confirm(fileName+"  :文件已上传,是否覆盖?") == true){
   		    		showRunning(true);
	   		    	var fileUpload = $('#fileUpload');
	   				fileUpload.fileupload('send', {
	   			        fileInput: fileUpload,
	   			        formData:{bpd413:aaz328,flag:"1"}
	   			    }).complete(
	   			    		function (result, textStatus, jqXHR) {
	   			    			showRunning(false);
	   			    			initData4Complaint();
	   			    			showFlowPic(aaz328);
	   			    		});
		   		} else {
			   		return;
			   	}
   		    } else {
   		    	if(confirm("您确认导入文件" + fileName + "吗?") != true){
   					return;
   				}
   		    	showRunning(true);
   		    	var fileUpload = $('#fileUpload');
   				fileUpload.fileupload('send', {
   			        fileInput: fileUpload,
   			        formData:{bpd413 : aaz328, flag : '0' }
   			    }).complete(
   			    		function (result, textStatus, jqXHR) {
   			    			showRunning(false);
   			    			initData4Complaint();
   			    			showFlowPic(aaz328);
   			    		});
	   		}
   		}
	});
}

function initFileUpload(){
	var fileUpload = $("#fileUpload");
	fileUpload.fileupload({
        url: rootPath + '/hosp/appealAction!uploadAppealData.action',
        dataType: 'json',
        autoUpload: false,
        replaceFileInput: false,
        done: function (e, data) {
			if(data.result.errortype == "0"){
        		fileUpload.fileupload("destroy");
        		fileUpload[0].outerHTML = fileUpload[0].outerHTML;
        		initFileUpload();
        	}
        	showPageError(data.result);
        },
        fail: function (e, data) {
            showPageError(data.message);
        }
    });
}

function renderFind(row,index,value) {
	var show_model = row.show_model;
	var aaz217 = row.aaz217;
	var aaz328 = row.aaz328;
	
	var a = [];
	a.push('<a onclick="showinfo(');
	if (aaz217 != '' && aaz217 != null){
		a.push(aaz217);
	}else{
		a.push('\'\'');
	}
	a.push(',');
	a.push(aaz328);
	a.push(',');
	a.push(show_model);
	a.push(')"> ');
	a.push('查看');
	a.push(" </a>");
	a.push('<a onclick="appealExplain(');
	a.push(aaz328);
	a.push(')">申诉</a>');
	return a.join('');
}

function showinfo(aaz217,aaz328,show_model){
	var data = {};
	data.wae011 = aaz217;
	data.aaz328 = aaz328;
	data.show_model = show_model;
	openTab2(data);
	$('#divTabs').tabs().tabs('select', 1);
}
</script>