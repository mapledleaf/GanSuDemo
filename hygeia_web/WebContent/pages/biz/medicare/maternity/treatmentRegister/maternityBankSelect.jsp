<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	request.setAttribute("path", request.getContextPath() + "/maternity/MaternityRegAction");
%>
<powersi:html>
	<base target="_self">
	<powersi:head title="银行信息查询" />
	<powersi:form id="bankForm" action="/maternity/MaternityRegAction!queryBankInfo.action">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="queryBankBtn" key="button_query" onclick="queryall()" title="Alt+Q" data-hotkey="Alt+Q"/>
				<powersi:button id="cleanBankBtn" key="button_clean"  onclick="clearall()" label="重 置" title="Alt+C" data-hotkey="Alt+C"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout  cols="4">
				<powersi:textfield   name="trDTO.aab069" id="bankName" key="银行名称" />
				<powersi:textfield   name="trDTO.aae008" id="aae008" key="aae008" />	
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox title="银行信息">
		<powersi:datagrid id="bankGrid" formId="bankForm" delayLoad="false" 
			onSelectRow="setBankInfo" isMultiSelect="false" 
			pageSize="20" pageSizeOptions="[20,50,100,500]">
			<powersi:datagrid-column name="aaz065" hide="true" />
			<powersi:datagrid-column name="aab069" key="银行名称" width="60%"/>
			<powersi:datagrid-column name="aae008" key="aae008" width="40%"/>
		</powersi:datagrid>
	</powersi:panelbox>
<script type="text/javascript">
function setBankInfo(rowdata,rowid,rowobj) {
	closeDialog(rowdata);
}

function queryall() {
	bankGrid.reset();
	bankGrid.loadForm(bankForm);
}

function clearall() {
	bankForm.reset();
}

$(function(){
	$("#bankName").focus();
});

//esc关闭事件
$(document).keyup(function(event){
	 switch(event.keyCode) {
		 case 27:
			closeDialog();
	 }
});

$('#bankName').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行查询
    	queryall();
    }
});

$('#aae008').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行查询
    	queryall();
    }
});
</script>
</powersi:html>