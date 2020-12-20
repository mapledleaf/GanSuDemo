<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>

<powersi:html>
	<head>
		<powersi:head title="二次返院业务查询与修改"/>
	</head>
	<body>
		<powersi:form id="mainForm">
			<powersi:panelbox title="查询条件">
			<powersi:editorlayout cols="6">
				<tr>
					<powersi:textfield label="个人电脑号" id="aac001" name="mediSpecDto.aac001" key="aac001" required="false"/>
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_start" name="mediSpecDto.aae127_start" key="aae127_start" readonly="true"/>
					<powersi:textfield mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae127_end" name="mediSpecDto.aae127_end" key="aae127_end" readonly="true"/>
				</tr>
				<tr>
					<powersi:textfield label="查询条件" id="aac018" name="dto.aac018"   required="true" />
					<powersi:codeselect label="人员类型" id="aac012" name="mediSpecDto.aac012" key="aac012" codeType="pers_type_medi" disabled="false"/>
					<td colspan="2" align="right">
							<powersi:submit key="button_query" value="查询" onclick="queryApplyInfo()"/>
							<powersi:button key="button_clear" value="清屏" onclick="clear()"/>
					</td>
				</tr>
				
			</powersi:editorlayout>
		
		</powersi:panelbox>
		<powersi:datagrid id="grid">
			<powersi:datagrid-column name="aac003" key="aac003" label="姓名" width="80"/>
			<powersi:datagrid-column name="aac004" key="aac004" label="性别" width="60"/>
			<powersi:datagrid-column name="aac006" key="aac006" label="出生日期" width="120"/>
			<powersi:datagrid-column name="aac002" key="aac002" label="公民身份证号" width="150"/>
			<powersi:datagrid-column name="aab069" key="aab069" label="单位名称" width="150"/>
			<powersi:datagrid-column name="重复住院号" key="" label="重复住院号" width="100"/>
			<powersi:datagrid-column name="本次入院时间" key="" label="本次入院时间" width="100"/>
			<powersi:datagrid-column name="上次出院时间" key="" label="上次出院时间" width="120"/>
			<powersi:datagrid-column name="pke038"   key="pke038" label="疾病名称" width="100"/>
			<powersi:datagrid-column name="是否重复住院" key="" label="是否重复住院" width="120"/>
			<powersi:datagrid-column name="pke022" key="pke022" label="审核日期" width="120"/>
			<powersi:datagrid-column name="pke021" key="pke021" label="审核人" width="80"/>
			<powersi:datagrid-column name="aae013" key="aae013" label="备注" width="200"/>
		</powersi:datagrid>
	</powersi:form>
	<powersi:errors/>
<script type="text/javascript">
window.onload = function(){
	
	 $('#akb020').val("<%=hospital_id%>");
	 $('#akb021').val("<%=hospital_name%>");
	//申请结束时间为当天
	 var myDate = new Date();
	 var year = myDate.getFullYear();
	 var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
	 var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
	 
	 $('#aae127_start').val(year+"-"+month+"-"+"01");
	 $('#aae127_end').val(year+"-"+month+"-"+day);
	 
	 
}
//增加操作列
function renderOperate(row,index,vlaue){
	var a = [];
	a.push('<input type="button" value="详情" class="linkbutton">');
	a.push('onclick="doDetail(index)"/>');
	return a.json('');
}

</script>
	</body>
</powersi:html>