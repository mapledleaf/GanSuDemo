<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="医院接入许可查询" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>
<powersi:form id="accessLicenseForm" name="accessLicenseForm" namespace="/medicare"
		action="HospAccessMgrAction!queryAccessLicenseList.action">
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button id="btAdd" label="新 增"  onclick="doAdd()" />
				<powersi:button name="btDel" label="删 除" id="btDel" onclick="doDel()" />
				<powersi:button name="btClear" label="重 置" id="btClear" onclick="clearall()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout cols="8">
	        <powersi:editorlayout-row>
		        <powersi:textfield id="bae330" name="hospitalAccessLicenseDTO.bae330"  key="bae330" placeholder="请输入医院接入许可流水号查询"  label="医院接入许可流水号"/>
		        <powersi:textfield id="bae314" name="hospitalAccessLicenseDTO.bae314"  key="bae314" placeholder="请输入年度查询"  label="年度"/>
	        	<td colspan="2">
	        		<powersi:hidden id="akb020" name="hospitalAccessLicenseDTO.akb020" />
	        	</td>
	        </powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
</powersi:form>		
	<powersi:groupbox title="查询结果">
		<a>双击单条信息修改明细。</a>
        <powersi:datagrid id="grid_AccessLicense_Query" formId="accessLicenseForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="true" onDblClickRow="dbSelectRow"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'医院接入许可发放情况'">
		      <powersi:datagrid-column name="bae330"  display="医院接入许可流水号" frozen="true" width="200" minWidth="100"/>
		      <powersi:datagrid-column name="akb020"  display="医院编号" frozen="true" width="120" minWidth="100"/>
		      <powersi:datagrid-column name="akb021"  display="医院名称" width="200" minWidth="100"/>
		      <powersi:datagrid-column name="bae314"  display="年度" width="60" minWidth="50"/>
		      <powersi:datagrid-column name="bae315"  display="许可发放日期" width="150" format="{0,date,yyyy-MM-dd}"/>
		      <powersi:datagrid-column name="bae302"  display="录入人" width="100" minWidth="50" />
		      <powersi:datagrid-column name="bae303"  display="录入时间" width="150" format="{0,date,yyyy-MM-dd}"/>
		      <powersi:datagrid-column name="bae313"  display="备注" width="200" minWidth="50" />
		      <powersi:datagrid-column name="deltag"  display="可以删除标记" width="20" minWidth="20" hide="true"/>
		</powersi:datagrid>
	</powersi:groupbox>
	
	<tags:transCode/>
	<powersi:errors />

<script type="text/javascript">
window.onload = function(){
	
	$('#akb020').val("<%=hospital_id%>");
 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
		popupAlert("医院编码不能为空，请检查登陆信息！");
    	return;
	}
 	grid_AccessLicense_Query.reload();
}

function clearall(){
	$('#bae300').val("");
	$('#bae310').val("");
}

function doAdd() {
		popupDialog({
			url: "${rootPath}/pages/biz/medicare/hosp/HospAccessLicenseNew.jsp",
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessLicense_Query.reload(true);
			}
		}, screen.height, screen.width);
		
}
	
	//双击信息列表，修改接入方式信息
	function dbSelectRow(rowdata, rowid, rowobj){
		var row = grid_AccessLicense_Query.getRow(rowid);
		var bae330 = row['bae330'];
		
		popupDialog({
			url: "${rootPath}/medicare/HospAccessMgrAction!queryHospAccessLicenseEdit.action?hospitalAccessLicenseDTO.bae330="+bae330,
			onClosed: function() {
				var ret = this.returnValue;
				grid_AccessLicense_Query.reload(true);
			}
		}, screen.height, screen.width);
	}

	function doDel() {
		
		//获取多选，全部勾选的数据
		var rows = grid_AccessLicense_Query.getSelectedRows();
		if(powersi.isempty(rows)){
			popupAlert("请选择医院接入许可信息！");
			
			return;
		}
		var invalid = false;
  		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row){
			if(row['deltag'] == "1"){
				invalid = true;
				grid_AccessLicense_Query.select(row);
				popupAlert(row['bae330']+':该条存在关联的医院接入方式数据,不能删除！');
				grid_AccessLicense_Query.reload(true);
				return false;
			}
		});
  		
  		if(invalid){
  			return;
  		}
		
		if (!confirm("您确认删除选择的医院接入许可吗?")) {
        return;
    	}
		
		var data = powersi.tostring(rows);
		postJSON("${rootPath}/medicare/HospAccessMgrAction!delAccessLicense.action",
			{
			"data" : data
			}, function(json) {
				if (!checkJSONResult(json)) {
					return;
				}
				popupInfo(json.message);
				grid_AccessLicense_Query.reload(true);
		});

	}
</script>
</body>
</powersi:html>