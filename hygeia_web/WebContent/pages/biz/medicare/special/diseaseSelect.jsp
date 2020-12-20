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
	request.setAttribute("aka035",request.getParameter("dto.aka035")); //病种类别
	request.setAttribute("caa027",request.getParameter("dto.caa027")); //
	request.setAttribute("aaa027",request.getParameter("dto.aaa027"));
	request.setAttribute("aka083",request.getParameter("dto.aka083")); //申请类别
%>

<powersi:html>
	<base target="_self">
	<powersi:head title="疾病查询" />
	<powersi:form name="mainForm" action="specialManager!getDiseaseInfList.action" namespace="/medicarespecial">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btmit" key="button_query" onclick="queryAll();"/>
				<powersi:button id="btok"  key="button_edit" value="确定" onclick="getAllSelect();" />
				<powersi:button id="btmit" key="button_close" onclick="closeDialog();"/>
			</powersi:panelbox-toolbar>
				<!-- 隐藏元素层 -->
			<div id="div1" style="display: none ">
				<powersi:textfield id="aaa027" name="dto.aaa027" value="${aaa027}" label="统筹区编码"/>
				<powersi:textfield id="aka035" name="dto.aka035" key="aka035" value="${aka035}" label="病种类别"/>
				<powersi:textfield id="caa027" name="dto.caa027" value="${caa027}"/>
				<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="${aka083}" label="病种类别"/>
			</div>
			
			<powersi:editorlayout cols="4">
				<tr>
					<powersi:textfield id="aka120" name="dto.aka120" label="疾病编码"/>
					<powersi:textfield id="aka121" name="dto.aka121" label="疾病名称"/>
				</tr>
				<tr>
					<powersi:textfield id="ake001" name="dto.aka020" label="拼音助记码"/>
					<powersi:textfield id="ake002" name="dto.aka021" label="五笔助记码"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:panelbox title="查询结果">
			<powersi:datagrid id="grid1" formId="mainForm" onSelectRow="getSingleSelect" onDblClickRow="getSDbSingleSelect"
				height="100%" pageSize="10" enabledSort="true" checkbox="${checkbox}" 
				pageSizeOptions="[10,20,50,100]" delayLoad="false"
				onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
				pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
				<% if(null != request.getAttribute("aka083")&&"131".equals(request.getAttribute("aka083").toString())){ %>
					<powersi:datagrid-column name="aka120" display="疾病编码" width="20%"/>
					<powersi:datagrid-column name="aka121" display="疾病名称" width="40%"/>
					<powersi:datagrid-column name="bke401" display="病种分型" data="bke401List" render="renderDictionary" isSort="false" width="10%"/>
					<powersi:datagrid-column name="bke402" display="病种严重等级" data="bke402List" render="renderDictionary" isSort="false" width="10%"/>
					<powersi:datagrid-column name="aae019" display="限额" width="20%"/>
				<%}else{ %>
					<powersi:datagrid-column name="aka120" display="疾病编码" width="40%"/>
					<powersi:datagrid-column name="aka121" display="疾病名称" width="60%"/>
				<% }%>
			</powersi:datagrid>
			<powersi:codetable id="bke401List" codeType="bke401"></powersi:codetable>
			<powersi:codetable id="bke402List" codeType="bke401"></powersi:codetable>
		</powersi:panelbox>
		
	</powersi:form>
<script type="text/javascript">
	$(document).ready(function() {
		$("body").focus(); //先聚焦，不然一打开就摁Esc不会生效
		//单选模式不显示确定、取消按钮
		if('true' != '${checkbox}') {
			$("#btok").hide();
		}
		$("#aka120,#aka121,#ake001,#ake002").each(function() {
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
</script>
</powersi:html>