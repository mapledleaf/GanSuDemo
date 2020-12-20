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
	<powersi:form id="bizForm" method="post" action="/universal/DiagnoseQueryAction_HIS!queryOnePersonInfo.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSaveRegister" label="查询在院病人"
					onclick="queryOnePersonInfo();" />
				<powersi:button id="btFeeUpload" label="上传在院费用"
					onclick="feeUpload();" />
				
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="20%,20%">
				<powersi:editorlayout-row>
				<powersi:textfield id="akc190_query" name="akc190_query"  key="住院号："  
					onKeyDown="" onchange="" required="false" />
				<powersi:textfield id="aae036" name="" mask="date" key="入院时间"  
					onKeyDown="" onchange=""  required="false" format="dateFmt:'yyyy-MM-dd'"/>
				<td><input type="hidden" id="businessTypeValue" name="businessTypeValue" value=""/></td> 
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	
	<div class="col-5">
		<powersi:panelbox key="panel_result" title="住院人员信息" >
			<powersi:datagrid id="grid" name="Kcd2_HospFee_HisDTOList" height="100%"  width="100%" exportFileName="'首诊医院参保人信息'" 
	       		delayLoad="true" showReload="false" frozen="false" enabledEdit="true" onSelectRow="queryFeeInfo" checkbox="false">
	       			<powersi:datagrid-column name="aac003" display="姓名" align="center" />
	       			<powersi:datagrid-column name="akc190" display="住院号" align="center" />
	       			<powersi:datagrid-column name="kcd1id" display="住院登记序列号" align="center" />
	       			<powersi:datagrid-column name="aac001" display="个人编号" align="center"/>
	       			<powersi:datagrid-column name="aac002" display="身份证号码" align="center" />
	       			<powersi:datagrid-column name="aae030" display="入院时间" align="center"/>
	       			<powersi:datagrid-column name="akf001" display="入院科室编码" align="center" />
	       			<powersi:datagrid-column name="bka020" display="入院科室名称" align="center"/>
	       			<powersi:datagrid-column name="bka021" display="病区编码" align="center" />
	       			<powersi:datagrid-column name="bka022" display="病区名称" align="center" />
	       			<powersi:datagrid-column name="bka023" display="床位号" align="center" />
	       			<powersi:datagrid-column name="aaz217" display="就医登记号" align="center"/>
	       			<powersi:datagrid-column name="bka035" display="人员类别" align="center" />
	       			<powersi:datagrid-column name="aae036" display="登记时间" align="center" />
			</powersi:datagrid>
		</powersi:panelbox>
	</div>
		<!-- 右边数据库 -->
	<div class="col-7">
		<powersi:panelbox key="panel_result2" title="费用明细" >
			<powersi:datagrid id="gridFee" name="rsList" height="100%"  width="100%" 
	       		 enabledSort="true" delayLoad="true" >
	       			<powersi:datagrid-column name="akb020" display="医院编码" align="center" />
	       			<powersi:datagrid-column name="akc190" display="住院号" align="center" />
	       			<powersi:datagrid-column name="ake005" display="医院药品编码" align="center" />
	       			<powersi:datagrid-column name="ake006" display="医院药品名称" align="center"/>
	       			<powersi:datagrid-column name="bka069" display="费用时间" align="center"/>
	       			<powersi:datagrid-column name="bka040" display="单价" align="center" />
			</powersi:datagrid>
		</powersi:panelbox>
	</div>
	
<script type="text/javascript">
//费用上传
function feeUpload(){
	//打开窗口，通过住院号
	//获取被选择的行
	var selectedRow = grid.getSelectedRow();
	if(selectedRow == null || selectedRow == ''){
		popupWarn("请先选择需要上传的数据","提示信息");
		return;
	}
	//var kcd1hid = selectedRow.kcd1hid;
	var akc190 = selectedRow.akc190;
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!loadUploadData.action?akc190="+akc190;
	
	var dlg = popupDialog({
		url: url,
		onClosed: function(){
			/* var ret = this.returnValue;
			if(ret.message == '0'){
				popupSuccess("费用上传成功","提示信息");
			}else{
				popupWarn("费用上传失败","提示信息");
			} */
			//gridFee.loadData(ret);
		}
	}, null, null); 
}

//查询个人信息
function queryOnePersonInfo(){
	//var saveItemData = $("#bizForm").serialize();
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryInHospitalPersonInfo.action";
	var aae036 = $("#aae036").val();
	var akc190 = $("#akc190_query").val();
	url = url+"?akc190="+akc190+"&aae036="+aae036+"&bka044=1";
	postJSON(url,
		{},
  		importCalcInfo);
}

//回掉函数
function importCalcInfo(ret){
	grid.loadData(ret.data);
}

//双击选择查询费用明细
function queryFeeInfo(data,rowid,rowdata){
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryInHospitalPersonFee.action";
	postJSON(url,data,function(ret){
		gridFee.loadData(ret.data);
	});
}

//费用重传,修改费用是否上传标记
/* function feeUploadAgain(){
	//获取被选中的行
	var data = grid.getSelectedRows();
	var url = "${rootPath}/diagnose_his/DiagnoseQueryAction_HIS!inhospitalfeeUploadAgain.action";
	postJSON(url,{feeUpload:JSON.stringify(data)},function(ret){
		if(ret != null){
			popupSuccess("设置重传成功","提示信息");
		}else{
			popupWarn("未选择需要重传的数据","提示信息");
		}
	});
} */
</script>	
</powersi:html>