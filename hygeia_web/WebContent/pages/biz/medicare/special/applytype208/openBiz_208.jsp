<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%
	String path = request.getContextPath(); 
%>
<powersi:html>
<powersi:head title="结算业务" />
<body>
<powersi:form id="mainForm" >
<powersi:panelbox title="查询条件">
	  <powersi:hidden id="aac001" name="dto.aac001"/>
	  <powersi:hidden id="pageFlag" name="dto.pageFlag"/>
	  <powersi:panelbox-toolbar>
			<powersi:button id="btQuery" key="button_query" value="查  询" onclick="query()" data-hotkey="Alt+Q"/>
	  </powersi:panelbox-toolbar>
	  <powersi:editorlayout cols="8">
		 <tr>
			<tr>
				<powersi:textfield id = "aae030"  label="开始日期" name = "dto.aae030" required="false" mask="date"/>
				<powersi:textfield id = "aae031"  label="结束日期" name = "dto.aae031" required="false" mask="date"/>
				<td colspan="4"/>
			</tr>
		 </tr>
	  </powersi:editorlayout>
</powersi:panelbox>
</powersi:form>

<powersi:groupbox title="请双击要选择的业务">
	<powersi:datagrid id="gridBiz" height="100%" alternatingRow="false"  pageSize="30" pageSizeOptions="[30,50,100,200]"
		enabledSort="true" delayLoad="true" onDblClickRow="click">
		<powersi:datagrid-column name="akb020" display="医疗机构编码" width="0%" hide="true"/>
		<powersi:datagrid-column name="akb021" display="医疗机构名称" width="50%" align="left"/>
		<powersi:datagrid-column name="aaz217" display="业务序列号" width="0%" hide="true"/>
		<powersi:datagrid-column name="aac003" display="姓名" width="10%"/>
		<powersi:datagrid-column name="bkz101" display="入院诊断名称" width="20%" align="left"/>
		<powersi:datagrid-column name="akc193" display="入院诊断编码" width="0%" hide="true"/>
		<powersi:datagrid-column name="aae030" display="入院时间" width="10%"/>
		<powersi:datagrid-column name="aae031" display="出院时间" width="10%"/>
	</powersi:datagrid>
</powersi:groupbox>
<powersi:errors />
</body>
<script type="text/javascript">
/*页面加载的动作*/
$(document).ready(function() {
	initForm();
	query();
});  
/*查询结算业务  */
function query(){
	var mainFrom = $("#mainForm").serialize();
	 
	postJSON(rootPath +"/medicarespecial/specialManager!queryBizFin.action", mainFrom, querySuccess);
	return true;
	
}
function querySuccess(json){
	if(!checkJSONResult(json)){
   		return;
  	}
	gridBiz.reset();
	gridBiz.setData(json.rows);
	gridBiz.loadData();
	
}
//选择业务信息后，返回主界面
function click(){
	var row = gridBiz.getSelectedRow();
	if (row == null) {
		alert("请双击要选择的业务！");
		return false;
	}
	closeDialog(row);
}

function initForm() {
	$("#aae030").val(getdateString().substring(0,4) + "0101");
	$("#aae031").val(getdateString());
}

function getdateString(){
	var dt = new Date();
	var strDate = dt.getFullYear() ;
	if (dt.getMonth() + 1 <10) strDate += "0";
	strDate += '' + (dt.getMonth()+1);
	if (dt.getDate() <10) strDate += "0";
	strDate += '' + dt.getDate();
	return strDate;
}
</script>
</powersi:html>