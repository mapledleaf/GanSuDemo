<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<powersi:html>
<powersi:head title="医院目录抓取" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
	<div class="row">
		<div class="col-6">
			<powersi:form id="bizForm" method="post" action="/universal/DiagnoseQueryAction_HIS!queryCatalog.action">
				<powersi:panelbox key="panel_result" title="医院端药品临时目录" col="20%,20%,20%" allowCollapse="false">
					<powersi:panelbox-toolbar>
						<powersi:button id="transFundStatusQuery" label="医院目录抓取"
							onclick="tranCatalog();" />
						<powersi:submit id="btSaveRegister" label="医院目录查询"
							onclick=""/>
						<powersi:button id="btFundStatusQuery" label="医院目录上传"
							onclick="syncCatalog();" />
					</powersi:panelbox-toolbar>
					<powersi:datagrid id="grid" formId="bizForm" height="100%"  width="100%" exportFileName="'首诊医院参保人信息'" 
			       		delayLoad="true" showReload="false" frozen="false" enabledEdit="false"
						enabledSort="false">
			       			<powersi:datagrid-column name="akb020" display="医疗机构编码" align="center" />
			       			<powersi:datagrid-column name="ake001" display="三大目录编码" align="center" />
			       			<powersi:datagrid-column name="ake002" display="三大目录名称" align="center" />
			       			<powersi:datagrid-column name="aka070" display="剂型" align="center"/>
			       			<powersi:datagrid-column name="bka040" display="单价" align="center" />
			       			<powersi:datagrid-column name="aka052" display="剂量单位" align="center"/>
			       			<powersi:datagrid-column name="aka074" display="规格" align="center" />
			       			<powersi:datagrid-column name="bka073" display="厂家" align="center" />
			       			<powersi:datagrid-column name="aka063" display="项目类别" align="center" code="ake003"/>
			       			<powersi:datagrid-column name="aka064" display="是否处方药" align="center"/>
			       			<powersi:datagrid-column name="bka045" display="上传标志" align="center" />
			       			<powersi:datagrid-column name="aaz231" display="目录id" align="center" />
			       			<powersi:datagrid-column name="aaa027" display="中心编码" align="center"/>
					</powersi:datagrid>
				</powersi:panelbox>
			</powersi:form>
		</div>
		<!-- 费用明细 -->
		<div class="col-6">
			<powersi:form id="bizForm2" method="post" action="/universal/DiagnoseQueryAction_HIS!queryCatalogByKa40.action">
				<powersi:panelbox key="panel_result" title="结算云中的目录信息" cols="20%" allowCollapse="false">
					<powersi:panelbox-toolbar>
						<powersi:submit id="btSaveRegister2" label="云端目录查询"/>
					</powersi:panelbox-toolbar>
					<powersi:datagrid id="gridFee" formId="bizForm2" delayLoad="true" frozen="false" height="100%"  width="100%">
			       			<powersi:datagrid-column name="ake003" display="目录类别" align="center" code="ake003"/>
			       			<powersi:datagrid-column name="ake005" display="目录编码" align="center" />
			       			<powersi:datagrid-column name="ake006" display="目录名称" align="center"/>
			       			<powersi:datagrid-column name="bkc140" display="单价" align="center" />
			       			<powersi:datagrid-column name="bkc141" display="剂量单位" align="center"/>
			       			<powersi:datagrid-column name="bkc139" display="规格" align="center" /> 
			       			<powersi:datagrid-column name="aaa027" display="统筹区编码" align="center"/>
			       			<powersi:datagrid-column name="akb020" display="医院编码" align="center" />
					</powersi:datagrid>
				</powersi:panelbox>
			</powersi:form>
		</div>
	</div>
	
<script type="text/javascript">
//查询his目录数据
function queryCatalog(){
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!queryCatalog.action";
	var saveItemData = $("#bizForm").serialize();
	postJSON(url,
		saveItemData,
  		function(ret){
		grid.loadData(ret.data);
	});
}

//目录同步
function syncCatalog(){
	$("#btFundStatusQuery").attr("disabled",true);
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!syncCatalog.action";
	var saveItemData = grid.getAllData();
	if(saveItemData == null || saveItemData == ''){
		popupWarn("无数据可上传！");
		return;
	}
	postJSON(url,
		{hisCatalog:JSON.stringify(saveItemData)},
  		function(ret){
		if(ret.message == '-1'){
			popupError("项目类别为编码0、1、2、3，不能为中文，请在医院端维护后再重传！");
			return;
		}else if(ret.message="0"){
			popupSuccess("目录上传成功！");
		}
		//gridFee.reset();
		//gridFee.loadData(ret.data);
	});
}

//目录重传
function tranCatalog(){
	$("#btFundStatusQuery").attr("disabled",false);
	var url = "${rootPath}/universal/DiagnoseQueryAction_HIS!tranCatalog.action";
	postJSON(url,{},function(ret){
		if(ret.message == '-1'){
			popupWarn("正在重传，请稍等！");
		}else{
			popupSuccess("设置重传成功！传输需要一段时间请稍等");
		}
	});
}


</script>	
</powersi:html>