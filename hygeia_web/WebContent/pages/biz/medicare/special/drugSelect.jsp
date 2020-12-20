<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarespecial/specialManager");
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	request.setAttribute("checkbox",request.getParameter("checkbox")); //checkbox=true 多选模式；否则单选模式
	request.setAttribute("aka120",request.getParameter("aka120")); //是否限制疾病
	request.setAttribute("aae140",request.getParameter("aae140")); //险种编号
	request.setAttribute("caa027",request.getParameter("dto.caa027")); //
	request.setAttribute("aaa027",request.getParameter("dto.aaa027"));
	request.setAttribute("ake001_not",request.getParameter("ake001_not")); //药品编号加逗号分隔的字符串，首尾需要去掉逗号
%>

<powersi:html>
	<base target="_self">
	<powersi:head title="药品/项目查询" />
	<powersi:form name="mainForm" action="specialManager!getdrugInfList.action" namespace="/medicarespecial">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btmit" key="button_query" onclick="queryAll();"/>
				<powersi:button id="btok"  key="button_edit" value="确定" onclick="getAllSelect();" />
				<powersi:button id="btnclose" key="button_close" onclick="closeDialog();"/>
			</powersi:panelbox-toolbar>
			
			<div id="div1" style="display: none ">
				<powersi:textfield id="aaa027" name="dto.aaa027" value="${aaa027}" label="统筹区编码"/>
				<powersi:textfield id="aae140" name="dto.aae140" value="${aae140}"/>
				<powersi:textfield id="caa027" name="dto.caa027" value="${caa027}"/>
				<powersi:textfield id="aka120" name="dto.aka120" value="${aka120 }"  label="疾病编码"/>
				<powersi:textfield id="ake001_not" name="dto.ake001_not" value="${ake001_not }"  label="不查询的药品编号"/>
			</div>
			<powersi:editorlayout cols="4">
				<tr>
					<powersi:textfield id="ake001" name="dto.ake001" label="药品项目编码"/>
					<powersi:textfield id="ake002" name="dto.ake002" label="药品项目名称"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:panelbox title="查询结果">
			<powersi:datagrid id="grid1" formId="mainForm" onSelectRow="getSingleSelect" onDblClickRow="getSDbSingleSelect"
				height="100%" pageSize="10" enabledSort="true" checkbox="${checkbox}" 
				pageSizeOptions="[10,20,50,100]" delayLoad="false"
				onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
				pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
				<powersi:datagrid-column name="aaz231" display="社保三大目录ID" hide="true"/>
				<powersi:datagrid-column name="ake001" display="药品项目编码" width="30%"/>
				<powersi:datagrid-column name="ake002" display="药品项目名称" width="40%"/>
				<powersi:datagrid-column name="ake003" display="药品项目类型" hide="true" width="5%"/>
				<powersi:datagrid-column name="aka057" display="药品项目自付比例" hide="true" width="5%"/>
				<powersi:datagrid-column name="aae019" display="限额" width="20%"/>
			</powersi:datagrid>
		</powersi:panelbox>
		
	</powersi:form>
<script type="text/javascript">
	$(document).ready(function() {
		$("body").focus(); //先聚焦，不然一打开就摁Esc不会生效
		//单选模式不显示确定、取消按钮
		if('true' != '${checkbox}') {
			$("#btok").hide();
		}
		$("#ake001,#ake002").each(function() {
  			$(this).keydown(function() {
  				if (window.event.keyCode == "13") {
  					queryAll();
  				}
  			})
  		});
	});

	$(document).keyup(function(event){
		 switch(event.keyCode) {
			 case 27:
				closeDialog();
		 }
	});
	
	//多选模式中多选
	function getAllSelect() {
		var rows = grid1.getSelectedRows();
		
		if(rows.length < 1){
			closeDialog();
		}
		closeDialog(rows);
	}
	
	
	//单选模式单击选择一个
	function getSingleSelect(rowdata, rowid, rowobj) {
		if('true' != '${checkbox}') {
			closeDialog(rowdata);
		} else {
			
		}
	}
	
	//多选模式双击选择一个
	function getSDbSingleSelect(rowdata, rowid, rowobj) {
		if('true' == '${checkbox}') {
			var list = new Array();
			list.push(rowdata);
			closeDialog(list);
		}
	}
	
	function queryAll() {
		grid1.reset();
		grid1.loadForm(mainForm);
	}
</script>
</powersi:html>