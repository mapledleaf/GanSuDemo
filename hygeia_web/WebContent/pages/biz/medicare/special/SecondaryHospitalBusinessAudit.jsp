<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

//增加操作列
function renderOperate(row,index,vlaue){
	var a = [];
	a.push('<input type="button" value="详情" class="linkbutton">');
	a.push('onclick="doDetail(index)"/>');
	return a.json('');
}

</script>

<powersi:html>
	<powersi:head title="二次返院业务审批"/>
	<powersi:form id="mainForm">
		<powersi:panelbox title="查询条件">
		<powersi:editorlayout cols="6">
		<tr>				
			<powersi:textfield label="个人电脑号" id="aac001" name="dto.aac001" key="aac.001" required="false"/>
			<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_start" name="dto.aae127_start" key="aae127_start" readonly="true"/>
			<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_end" name="dto.aae127_end" key="aae127_end" readonly="true"/>
		</tr>
		<tr>
			<powersi:codeselect label="门特门慢项目" id="aka083" name="dto.aka083" key="aka083" required="false" 
						codeType="mtmm_treat" codeFilter="(aka083 like '16%' and aka083 <> '16B') or (aka083 like '13%' and aka083 not like '13Z%') or aka083='140'"></powersi:codeselect>
			<td colspan="2" align="center">
				<powersi:button key="button_query" onclick="queryInfo()"></powersi:button>
				<powersi:button id="btAudit"  label="确认通过" key="button_audit" onclick="auditYes()"/>
				<powersi:button id="btAuditNO" label="确认不通过" key="button_auditno" onclick="auditinfo_no()"/>
			</td>
		
		</tr>
	   
	</powersi:editorlayout>
	</powersi:panelbox>
	<powersi:panelbox title="人员信息">
		<powersi:datagrid id="grid" formId="mainForm">
		<powersi:datagrid-column key="operate" render="renderOperate" isExport="false" isSort="false" width="80"/>
			<powersi:datagrid-column name="dto.aac003" key="aac003" label="姓名"/>
			<powersi:datagrid-column name="dto.aaz267" key="aaz267" label="申请号"/>
			<powersi:datagrid-column name="dto.aab069" key="aab069" label="单位名称"/>
			<powersi:datagrid-column name="" key="" label="申请医院"/>
			<powersi:datagrid-column name="" key="" label="本次入院时间"/>
			<powersi:datagrid-column name="" key="" label="上次出院时间"/>
			<powersi:datagrid-column name="dto.pke038" key="pke038" label="疾病名称"/>
		</powersi:datagrid>
	</powersi:panelbox>
	</powersi:form>
</powersi:html>