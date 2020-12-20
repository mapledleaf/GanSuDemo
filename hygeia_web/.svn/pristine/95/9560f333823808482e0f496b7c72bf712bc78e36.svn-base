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
	request.setAttribute("isDesignated",request.getParameter("isDesignated")); //1已定点，否则未定点
	request.setAttribute("akb020_not",request.getParameter("akb020_not")); //医疗机构编号加逗号分隔的字符串，首尾需要去掉逗号
	request.setAttribute("caa027",request.getParameter("dto.caa027")); //
	request.setAttribute("aaa027",request.getParameter("dto.aaa027"));
	request.setAttribute("aka130",request.getParameter("dto.aka130"));//业务类型
%>

<powersi:html>
	<base target="_self">
	<powersi:head title="医疗机构查询" />
	<powersi:form name="mainForm" action="specialManager!getHospInfList.action" namespace="/medicarespecial">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btmit" key="button_query" onclick="queryAll();"/>
				<powersi:button id="btok"  key="button_edit" value="确定" onclick="getAllSelect();" />
				<powersi:button id="btnclose" key="button_close" onclick="closeDialog();"/>
			</powersi:panelbox-toolbar>
				<!-- 隐藏元素层 -->
			<div id="div1" style="display: none ">
				<powersi:textfield id="aaa027" name="dto.aaa027" value="${aaa027}" label="统筹区编码"/>
				<powersi:textfield id="isDesignated" name="dto.isDesignated" value="${isDesignated}"  label="是否定点"/>
				<powersi:textfield id="akb020_not" name="dto.akb020_not" value="${akb020_not}" label="不查询的医疗机构"/>
				<powersi:textfield id="caa027" name="dto.caa027" value="${caa027}"/>
				<powersi:textfield id="aka130" name="dto.aka130" value="${aka130}" label="业务类型"/>
			</div>
			
			<powersi:editorlayout cols="20%,20%,15%,15%,15%,15%">
				<tr>
					<powersi:hidden id="akb020" name="dto.akb020" label="医疗服务机构编号"/>
					<powersi:textfield id="akb021" name="dto.akb021" label="医疗服务机构名称"/>
					<powersi:codeselect id="akb022" name="dto.akb022" key="akb022" codeType="akb022" label="医疗机构类型" onchange="queryAll()" value="1"/>
					<powersi:codeselect id="bke301" name="dto.bke301" key="bke301" codeType="bke301" label="医疗机构级别" onchange="queryAll()"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:panelbox title="查询结果">
			<powersi:datagrid id="grid1" formId="mainForm" onSelectRow="getSingleSelect" onDblClickRow="getSDbSingleSelect"
				height="100%" pageSize="10" enabledSort="true" checkbox="${checkbox}" 
				pageSizeOptions="[10,20,50,100]" delayLoad="false"
				onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('dto.totalResult',dg.getTotal());}}"
				pageParmName="dto.currentPage" pagesizeParmName="dto.pageSize">
				<powersi:datagrid-column name="akb020" display="医疗服务机构编号" width="14%"/>
				<powersi:datagrid-column name="aab069" display="医疗机构名称" width="25%"/>
				<powersi:datagrid-column name="bke301_name" display="医疗机构级别" width="10%"/>
				<powersi:datagrid-column name="aka101_name" display="医疗机构等级" width="10%"/>
				<powersi:datagrid-column name="bke300" display="外地标志" width="8%"/>
				<powersi:datagrid-column name="aaf003" display="定点联网开通标志" width="13%"/>
				<powersi:datagrid-column name="bke302_name" display="行政级别" width="6%"/>
				<powersi:datagrid-column name="isopen" display="是否定点医疗机构" width="13%"/>
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
		 $("#akb020,#akb021").each(function() {
  			$(this).keyup(function() {
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
		
		var list = new Array();
		for(var i=0; i<rows.length; i++){
			ob = new Object();
			ob.aaz107 = rows[i]["aaz107"];//医院ID
			ob.akb020 = rows[i]["akb020"];//医院编码
			ob.aab069 = rows[i]["aab069"];//医院名称
			ob.akb021 = rows[i]["aab069"];//医院名称,兼容
			ob.aka101 = rows[i]["aka101"];//医院等级
			ob.bke301 = rows[i]["bke301_name"];//医院级别
			ob.aaz269 = rows[i]["akb020"];//医疗机构及药店ID
			list.push(ob);
		}
		
		closeDialog(list);
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
			ob = new Object();
			ob.aaz107 = rowdata.aaz107;//医院ID
			ob.akb020 = rowdata.akb020;//医院编码
			ob.aab069 = rowdata.aab069;//医院名称
			ob.akb021 = rowdata.aab069;//医院名称,兼容
			ob.aka101 = rowdata.aka101;//医院等级
			ob.bke301 = rowdata.bke301_name;//医院级别
			ob.aaz269 = rowdata.akb020;//医疗机构及药店ID
			list.push(ob);
			closeDialog(list);
		}
	}
	
	
	function queryAll() {
		grid1.reset();
		grid1.loadForm(mainForm);
	}
</script>
</powersi:html>