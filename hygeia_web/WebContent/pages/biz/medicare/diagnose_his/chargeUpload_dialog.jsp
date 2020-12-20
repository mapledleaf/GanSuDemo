<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="费用单个上传/接口业务查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
	<powersi:codetable id="BZ1List" codeType="BZ1" />
	<powersi:form id="bizForm" method="post" action="/diagnose_his/DiagnoseQueryAction_HIS!queryHospFee.action">
	     	<powersi:panelbox key="操作栏">
	     		<powersi:panelbox-toolbar>
			     	<powersi:editorlayout-row>
			     	<div style="margin-right: 100px;">
				     	<input type="radio" name="uploadWay" id="fee_all" value="" checked="true"/><span style="margin-right:10px; ">全部</span>
				     	<input type="radio" name="uploadWay" id="fee_notupload" value="0"/><span style="margin-right:10px; ">未上传</span>
				     	<input type="radio" name="uploadWay" id="fee_upload" value="1"/><span style="margin-right:10px; ">已上传</span>
				     	<input type="radio" name="uploadWay" id="fee_upload_err" value="2"/><span style="margin-right:10px; ">上传失败</span>
			     	</div>
			    	</powersi:editorlayout-row>
					<powersi:button id="btnCalc0" label="费用上传" key="button_calc0" onclick="uploadAndFee();"
						disabled="false" />
					<powersi:button id="btnFundsub" key="获取上传费用信息" label="退出"
						disabled="false" onclick="closeDialog();"/>
					<powersi:hidden id="akc190" value=""></powersi:hidden>
				</powersi:panelbox-toolbar>
			</powersi:panelbox>
		</powersi:form>
		<div class="row">
			<div class="col-6">
				<powersi:panelbox key="panel_result1" title="医院费用明细列表" >
				<powersi:datagrid id="gridFee" name="rsList" height="100%"  width="100%" exportFileName="'上传成功的信息'" 
		       		 enabledSort="true" delayLoad="true" totalRender="renderTotal">
		       		 	<powersi:datagrid-column name="bz1" display="状态" align="center" render="_render"/>
		       		 	<powersi:datagrid-column name="ake005" display="医院药品编码" align="center" />
		       			<powersi:datagrid-column name="ake006" display="中心药品编码" align="center"/>
		       			<powersi:datagrid-column name="ake007" display="费用时间" align="center"/>
		       			<powersi:datagrid-column name="bka040" display="单价" align="center" />
		       			<powersi:datagrid-column name="BZ3" display="失败原因" align="center" />
				</powersi:datagrid>
				</powersi:panelbox>
			</div>
			<div class="col-6">
				<powersi:panelbox key="panel_result2" title="医院费用上传失败列表" >
				<powersi:datagrid id="gridFee2" name="rsList" height="100%"  width="100%" exportFileName="'上传失败的信息'" 
		       		showExportExcel="true" showExportExcel2007="true"  showExportPdf="true"  enabledSort="true" delayLoad="true"
		       		totalRender="renderTotal" >
		       			<powersi:datagrid-column name="BZ1" display="状态" align="center" render="_render"/>
		       			<powersi:datagrid-column name="ake005" display="医院药品编码" align="center" />
		       			<powersi:datagrid-column name="ake006" display="中心药品编码" align="center"/>
		       			<powersi:datagrid-column name="ake007" display="费用时间" align="center"/>
		       			<powersi:datagrid-column name="bka040" display="单价" align="center" />
		       			<powersi:datagrid-column name="BZ3" display="未知" align="center" />
				</powersi:datagrid>
				</powersi:panelbox>
			</div>
		</div>
<script type="text/javascript">
var s=${feeinfo};
var initakc190 = ${akc190};
$(function(){
	gridFee.loadData(s);
	$("#akc190").val(initakc190[0]);
}); 

//双击及进行费用上传操作
//查询流水号详情
function uploadAndFee(){
	
	var data = gridFee.getAllData();
	if(data == null || data == ''){
		popupWarn("请选择需要上传的费用!","提示信息");
		return;
	}
	//获取选择了的类型
	var value = $('input:radio:checked').val();
	var sURL = "${rootPath}/universal/DiagnoseQueryAction_HIS!uploadAndCheckHospFee.action?akc190="+$("#akc190").val()+"&value="+value;
	postJSON(sURL,{feeUpload:JSON.stringify(data)},function(ret){
		if(ret.message == '-1'){
			popupError("存在费用开始时间为空的值，请重传费用！");
		}else if(ret.message == '-2'){
			popupError("存在"+ret.data.length+"条费用未匹配，请核对后重新上传！");
			//gridFee.reset();
			gridFee2.loadData(ret.data);
		}else if(ret.message == '-3'){
			popupError("该人员不存在费用明细，请核对后重新上传！");	
		}else if(ret.message == '-4'){
			popupWarn("该人员费用正在后台自动上传！");
		}else if(ret.message == '-5'){
			popupWarn("已上传的费用请勿重复上传！");
		}else if(ret.message == '-6'){
			popupError("上传失败！"+ret.data);
		}else{
			//gridFee.reset();
			popupSuccess("费用上传成功！");
			gridFee2.addRows(ret.data,gridFee2.getData);
		}
		//刷新		
		searchFee();
	});
}
//选中事件
$(function(){
 $("input[name=uploadWay]").click(function(){
	 searchFee();
 });
});

//选择不同类型的数据显示,查询数据
function searchFee(){
	var akc190 = $("#akc190").val();
	if(akc190 == null || akc190 == ''){
		alert("住院号不存在");
		return;
	}
	var value = $('input:radio:checked').val();
	var sURL = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryInHospitalPersonFee.action?akc190="+akc190+"&BZ1="+value;
	postJSON(sURL,{},function(ret){
		gridFee.loadData(ret.data);
	});
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
				kinds[row['ake005']] = '';
			}
		});
	}
	var a = [];
	a.push('<div class="divCenter textSuccess">');
	a.push('<span>');
	a.push('总的金额：<b>');
	a.push(money.toFixed(2));
	a.push('</b>');
	a.push('</span>');
	a.push('&nbsp;&nbsp;');
	a.push('<span>');
	a.push('总的用量：<b>');
	a.push(usage.toFixed(2));
	a.push('</b>');
	a.push('</span>');
	a.push('&nbsp;&nbsp;');
	a.push('<span>');
	a.push('共 <b>');
	a.push(powersi.length(kinds));
	a.push('</b> 项费用');
	a.push('</span>');
	a.push('</div>');
	return a.join('');
}

</script>
</powersi:html>