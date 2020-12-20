<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarespecial/specialManager");
	String aaa027 = BizHelper.getAaa027();
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<base target="_self">
<head>
	<powersi:head title="申请查询与修改" />
</head>
<body>
	<powersi:form id="mainForm" action="specialManager!getSpeAppInfList.action" namespace="/medicarespecial">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
					<powersi:button key="button_query" value="查 询" data-hotkey='alt+Q' onclick="queryAll();"/>
					<powersi:button key="button_clear" id="btnReset" data-hotkey='alt+C' onclick="clearall();"/>					
			</powersi:panelbox-toolbar>
			
			<!-- 隐藏元素层 -->
			<div style="display: none">
				<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
				<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
				<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="123" label="申请类型"/>
				<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027" value="0"  label="统筹区编码"/>
				<powersi:textfield id="akb020" name="dto.akb020" key="akb020" value="<%= hospital_id %>"  label="申请医疗机构"/>
				<powersi:textfield id="auditType" name="dto.auditType" label="审核方式"/>
			</div>
			
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:codeselect id="argName" name ="dto.argName" list="%{#{'aac002':'社会保障号','aac003':'姓名'}}" label="查询类型" />
					<powersi:textfield id="querystring" name="querystring"
						title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
						onkeyup="findAac001()" buttonDisabled="false"  label="查询信息"/>
					<powersi:textfield id="aae127_start" name="dto.aae127_start" key="aae127_start" mask="date" label="申请开始时间" />
					<powersi:textfield id="aae127_end" name="dto.aae127_end" key="aae127_end" mask="date" label="申请结束时间" />
					<powersi:hidden id="aac001" name="dto.aac001"
 						label="电脑号" readonly="true"/>
 					<powersi:textfield id="aac002" name="dto.aac002"
						label="身份证号" readonly="true" />
					<powersi:hidden id="aac003" name="dto.aac003"
						label="姓名" readonly="true" />
				</tr>
				<tr>
					<powersi:codeselect id="audit_status" name ="dto.audit_status" list="%{#{'':'全部','0':'未审核','1':'审核通过','2':'审核未通过'}}" label="审核状态" />
					<td colspan="6"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="查询结果">
			
			<powersi:datagrid id="grid1" formId="mainForm" 
				height="100%" pageSize="20" enabledSort="true" showExportExcel="true" showExportExcel2007="true" showExportPdf="true"
				pageSizeOptions="[20,50,100,200]" delayLoad="true"
				onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
				pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
				<powersi:datagrid-column display="操作" frozen="true" key="operate" render="renderOperate1" width="10%"></powersi:datagrid-column>
				<powersi:datagrid-column name="aac003" display="姓名" width="8%"/>
				<powersi:datagrid-column name="aac004" display="性别" data="aac004" code="aac004" isSort="false" width="5%"/>
				<powersi:datagrid-column name="aac002" display="社会保障号"  width="15%"/>
				<powersi:datagrid-column name="aka083" hide="true" isExport="false"/><powersi:datagrid-column name="aka083_name" display="申请类型" data="aka083" code="aka083"  isSort="false" width="10%"/>
				<powersi:datagrid-column name="akb021" display="申请医疗机构" width="16%" />
				<powersi:datagrid-column name="aae127" display="申请日期" width="8%"/>
				<powersi:datagrid-column name="aae030" display="生效日期" width="8%"/>
				<powersi:datagrid-column name="aae031" display="失效日期" width="8%"/>
				<powersi:datagrid-column name="aae016" display="审核状态" render="renderDictionaryStatic" isExport="false"  isSort="false" width="10%"/>
				<powersi:datagrid-column name="aaz267" display="申请号" hide="true" isExport="false"  width=""/>
			</powersi:datagrid>
			<powersi:codetable id="aka083List" codeType="aka083"></powersi:codetable>
			<powersi:codetable id="aac004List" codeType="aac004"></powersi:codetable>
		</powersi:groupbox>
	</powersi:form>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	
	$(document).ready(function() {
		initForm();
		setClientScreenWH();
		dealwithAllAka083Option();
	});
	
	
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}


	function queryAll() { if($("#aae127_end").val()<$("#aae127_start").val()){popupAlert('结束时间不能小于开始时间！');return;}
		$("#"+$("#argName").val()).val(powersi.trim($("#querystring").val()));grid1.reset();
		grid1.loadForm(mainForm);$("#"+$("#argName").val()).val("");
	}
	
	
	function renderOperate1(row, index, value) {
		try {
			var a = [];

			a.push('<input title="单击可查看申请详细信息" type="button" value="详细" class="linkButton"');
			a.push(' onclick="getDetailPage(');
			a.push('\'' + row.aaz267 + '\'');
			a.push(',\'' + row.aka083 + '\'');
			a.push(')"');
			a.push(" />");
			
			return a.join('');
		} catch (ex) {
			popupError(ex.message);
		}
	}
	
	
	//查看详细信息
	function getDetailPage(aaz267, aka083) {
		var param = "dto.aae140=" + $("#aae140").val() + "&dto.pageFlag=" + getPageInitParamByApplyType(aka083, "pageFlag") + "&dto.aaz267=" + aaz267 
		+ "&dto.aaa027=" + $("#aaa027").val() + "&dto.caa027=" +$("#caa027").val() + "&dto.auditType=" + $("#auditType").val();
		popupDialogWithParam({
			url: "${path}!getQueryDetailPage.action?" + param,
			onClosed: function(){
			}
		}, null, getPageInitParamByApplyType(aka083, 'detailPH'),pwidth);
	}
	
	
	//删除申请
	function deleteApply(aaz267) {
		popupConfirm('您确定要删除申请吗？','提示', function(yes){
			if(yes){
				var url = "${path}!deleteSpeAppInf.action";
				postJSON(url ,{"dto.aaz267":aaz267,"dto.aaa027":$("#aaa027").val(),"dto.caa027":$("#caa027").val()},function(json){
					if(null != json.data){
						popupAlert(json.data, "提示", "success");
						queryAll();
					} else {
						popupAlert(json.message, "提示", "error");
						
					}
				});
			}
		});
	}
	
	
	//修改页面
	function getModifyPage(aaz267, aka083) {
		var param = "dto.aae140=" + $("#aae140").val() + "&dto.pageFlag=" + getPageInitParamByApplyType(aka083, 'pageFlag') + "&dto.aaz267=" + aaz267 
		+ "&dto.aaa027=" + $("#aaa027").val() + "&dto.caa027=" +$("#caa027").val() + "&dto.auditType=" + $("#auditType").val();
		popupDialogWithParam({
			url: "${path}!getApplyModifyPage.action?" + param,
			onClosed: function(){
			}
		}, null, getPageInitParamByApplyType(aka083, 'modifyPH'),pwidth);
	}
	
	
	/* 字典项渲染函数  解决loadData不能自动加载字典问题*/
	function renderDictionary(rowdata, index, value,arguments) {
		if(value != null && value != "") {
			var list = window[arguments.data];
			for(var i in list){
				if(list[i].id == value  ){
					return list[i].text;
				}
			}
			return '<span title="未匹配到字典项">'+value+'</span>';
		}else{
			return "";
		}
	}
	
	
	function clearall() {
		document.getElementById("mainForm").reset();
		$("#aac001").val("");
		$("#akb020").val("");
		initForm();
	}
	
	
	function initForm() {
		$("#aae127_start").val(getdateString().substring(0,6) + "01");
		$("#aae127_end").val(getdateString());
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
	
	
	/* 字典项渲染函数 固定值*/
	function renderDictionaryStatic(rowdata, index, value,arguments) {
		if(value != null && value != "") {
			
			if('0' == value) {
				return "未审核"
			} else if('1' == value) {
				return "审核通过"
			} else if('2' == value) {
				return "审核不通过"
			} else {
				return '<span title="未匹配到字典项">'+value+'</span>';
			}
		}else{
			return "";
		}
	}
	
	
	/*查询参保信息*/
	function readIcCard() {queryAll();} function findAac001() {
		if (window.event.keyCode == 13) {
			queryAll();
			
		}
	}
</script>
</powersi:html>
<%@include file="../commonJsForSpecialApply.jsp"%> <!-- 引入特殊业务管理公共js方法 -->