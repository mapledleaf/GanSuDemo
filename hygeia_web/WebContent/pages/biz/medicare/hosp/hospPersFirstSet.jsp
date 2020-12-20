<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>

<%
	request.setAttribute("path", request.getContextPath() + "/medicare/HospManageAction");
%>

<powersi:html>
<powersi:head title="普通门诊首诊查询与修改" />
<body>
	<powersi:form name="mainForm" id="mainForm" action="/medicare/HospManageAction!queryPersFirstHosp.action">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_query" onclick="query()" id="saveBtn" value="查 询" title="Alt+Q" data-hotkey="Alt+Q"/>
				<powersi:button key="button_clear" value="清 空" onclick="clearall();" title="Alt+C" data-hotkey="Alt+C"/>	
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<powersi:textfield id="aac001" name="aac001"  key="aac001" label="个人电脑号"/>
				<powersi:textfield id="aac002" name="aac002"  key="aac002" label="身份证号"/>
				<powersi:textfield id="aac003" name="aac003"  key="aac003" label="姓名"/>
				<powersi:textfield onfocus="WdatePicker({dateFmt:'yyyy'});" label="年度" required="true" name="bkm221" id="bkm221" value="2019"/>
			</powersi:editorlayout>
	</powersi:panelbox>
		<powersi:panelbox key="panel_result" title="人员定点首诊医院信息" >
			<powersi:datagrid id="grid1" formId="mainForm" delayLoad="true" pageSizeOptions="[0]" showPageInfo="false"
				width="100%" enabledSort="false" >
					<powersi:datagrid-column display="操作" key="operate" render="renderOperate" width="11%" frozen="true"/>
				    <powersi:datagrid-column name="aac001" display="个人电脑号" key="aac001" width="10%"/>
				    <powersi:datagrid-column name="aac002" display="身份证号" key="aac002" width="15%"/>
				    <powersi:datagrid-column name="aac003" display="姓名" key="aac003" width="10%"/>
					<powersi:datagrid-column name="akb021" display="医院名称" key="bka030" width="25%" align="left"/>
					<powersi:datagrid-column name="aka130_name" display="业务类型" width="10%"/>
					<powersi:datagrid-column name="bkm221" display="开始年度" key="bkm221" width="8%"/>
					<powersi:datagrid-column name="bkm222" display="结束年度" key="bkm222" width="8%"/>
			</powersi:datagrid>
		</powersi:panelbox>
	</powersi:form>
	<powersi:errors />
</body>
<script type="text/javascript">
function renderOperate(row, index, value) {
	try {
		var a = [];
		a.push(' <input title="可单击将该人员首诊机构修改成本医疗机构" type="button" value="维护到本机构" class="linkButton"');
		a.push(' onclick="editinfo(\'');
		a.push(row.kcf2id);
		a.push('\')"');
		a.push(" />");
		return a.join('');
	} catch (ex) {
		popupError(ex.message);
	}
}

function editinfo(i){
	popupConfirm('您确定要将该人员首诊机构修改成本医疗机构吗？','提示', function(yes){
		if(yes){
			postJSON("${path}!editPersFirstHosp.action" ,{"hospInfoDto.kcf2id":i},function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
				popupAlert(json.message,"提示","success");
				queryall();
			});
		}
	});
}

function query(){
	if($("#aac001").val() == '' && $("#aac002").val() == '' && $("#aac003").val() == ''){
		popupAlert("请录入至少一个查询条件！","提示","warn");
		return;
	}
	grid1.reset();
	grid1.loadForm(mainForm);
}

//查询
function queryall() {
	grid1.reset();
	grid1.loadForm(mainForm);
}

function clearall() {
	mainForm.reset();
}

function afterGetPerson(){
	queryall();
}
</script>
</powersi:html>