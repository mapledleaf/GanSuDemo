<%@page import="org.aspectj.weaver.patterns.TypePatternQuestions.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String path = request.getContextPath(); 
	String kcd1id= request.getParameter("kcd1id") == null ? "":request.getParameter("kcd1id").toString();
	String bka039= request.getParameter("bka039") == null ? "":request.getParameter("bka039").toString();
	String aaz217= request.getParameter("aaz217") == null ? "":request.getParameter("aaz217").toString();
	String selectbatch = "-1";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<script type="text/javascript">
	var selectbatch = "-1";
	var selectkcd1id = "-1";
	var selectaaz217 = "-1";
	var bka058=0

	function selectRow(rowdata, rowid, rowobj){
		showDetail(rowdata);
	}
	
	function showDetail(rowdata){
		if(rowdata){
			
			json2form("#batchform", rowdata);	
			var aaz217 = rowdata['aaz217'];
			var kcd1id = rowdata['kcd1id'];
			var fee_batch = rowdata['bka001'];
			var bka039 = rowdata['bka039'];
			bka058 = parseFloat(rowdata['bka058']);
			
			selectbatch = fee_batch;
			selectkcd1id = kcd1id;
			selectaaz217 = aaz217;

			gridDetail.set("url", rootPath+"/diagnose/GetPersonInfoAction!getBusiBatchFee.action?aaz217="+aaz217+"&kcd1id="+kcd1id+"&bka001="+fee_batch+"&bka039="+bka039);
		} else{
			popupAlert('没有选择行！');
		}
	}

	function getBizInfo() {
		
		if(selectbatch == "-1"){
			popupAlert('请选择一行！');
			return;
		}
		
		if(bka058<=0){
			popupAlert('选择的费用批次已经退完，不允许再进行退费!');
			return;
		}

		var bizinfo = new Object();
		bizinfo.bka001 = selectbatch;
		bizinfo.kcd1id = selectkcd1id;
		bizinfo.aaz217 = selectaaz217;
		
		if(bizinfo){
			setDialogReturn(bizinfo);
		}
		setTimeout("closeDialog();", 500);
	}

</script>
<powersi:head title="选择费用批次" />

		<div class="row">
		
		<div class="col-4" >
		<powersi:form id="batchform" action="GetPersonInfoAction!getBusiFee.action" namespace="/diagnose" >
			<powersi:panelbox key="panel_result" title="费用批次" allowCollapse="false">
				<powersi:datagrid id="selectBizFeeList" formId="batchform" height="80%" delayLoad="true" onSelectRow="selectRow" showReload="false">
					<powersi:datagrid-column display="批次" name="bka001" width="40%" />
					<powersi:datagrid-column display="费用" name="bka058" width="50%" />
					<powersi:datagrid-column display="完成标志" name="bka039" width="50" align="left" hide="true" isAllowHide="true" />
					<powersi:datagrid-column display="唯一主键" name="kcd1id" width="50" align="left" hide="true" isAllowHide="true" />
				</powersi:datagrid>
			</powersi:panelbox>
			<powersi:hidden id="bka039" name="bka039" ></powersi:hidden>
			<powersi:hidden id="kcd1id" name="kcd1id"></powersi:hidden>
			<powersi:hidden id="aaz217" name="aaz217"></powersi:hidden>
			</powersi:form>
			
		</div>

		<div class="col-8">
		<powersi:form id="feeform" action="GetPersonInfoAction!getBusiBatchFee" namespace="/diagnose" >
		<powersi:panelbox key="panel_result" title="费用明细" allowCollapse="false">
		<powersi:datagrid id="gridDetail" formId="feeform" height="80%" delayLoad="true" showReload="false">
			<powersi:datagrid-column display="医院编码" name="ake005" width="60" editor="{type: 'number'}"/>
			<powersi:datagrid-column display="医院名称" name="ake006" width="50%" editor="{type: 'string'}"/>
			<powersi:datagrid-column display="剂型" name="bka052" width="50%" editor="{type: 'string'}"/>
			<powersi:datagrid-column display="厂家" name="bka053" width="30" editor="{type: 'string'}"/>
			<powersi:datagrid-column display="规格" name="bka054" width="30" editor="{type: 'string'}"/>
			<powersi:datagrid-column display="单价" name="bka056" width="60" editor="{type: 'number'}"/>
			<powersi:datagrid-column display="用量" name="bka057" width="60" editor="{type: 'number'}"/>
			<powersi:datagrid-column display="费用" name="bka058" width="60" editor="{type: 'number'}"/>
		</powersi:datagrid>
		</powersi:panelbox>
		</powersi:form>
		</div>
		</div>
		<div class="div_center">
			<powersi:button cssClass="button" key="button_ok" onclick="getBizInfo();"/>
			<powersi:button cssClass="button" key="button_cancel" onclick="closeDialog();"/>
		</div>

<script>
$(function(){
	$("#bka039").val("<%=bka039%>");
	$("#kcd1id").val("<%=kcd1id%>");
	$("#aaz217").val("<%=aaz217%>");

	selectBizFeeList.reload();
});
</script>
</html>