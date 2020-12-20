<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("aaz149", request.getParameter("aaz149"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<powersi:head title="特殊疾病限额详情" />
</head>
<powersi:codetable id="aae140List" codeType="aae140" />
<body>
	<powersi:panelbox key="panel_result" title="限额列表">
		<powersi:datagrid id="grid" autoWidth="true" enabledSort="false"
			alternatingRow="true" colDraggable="false">
			<powersi:datagrid-column name="aaz079" display="限额标准ID" />
			<powersi:datagrid-column name="aka120" display="疾病编码" />
			<powersi:datagrid-column name="aka121" display="疾病名称" />
			<powersi:datagrid-column name="aae041" display="开始年月" />
			<powersi:datagrid-column name="aae042" display="终止年月" />
			<powersi:datagrid-column name="aae140" display="险种类型" render="_render"/>
			<powersi:datagrid-column name="aae019" display="金额" />
			<powersi:datagrid-column name="aka057" display="报销比例" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
<script type="text/javascript">
	//加载列表
	$(function(){
		 postJSON("${rootpath}/hygeia_web/medicare/HospCataAction!loadDiseaseDirDetail.action",
				 {"diseaseDirDTO.aaz149" : "${aaz149}" },function(json){
					 if(!checkJSONResult(json))
						 return;
					 if(json.data.length==0)
						 popupAlert("该疾病无限额信息！");
					 else
					 	grid.loadData(json.data);  
				 });
	});
</script>
</html>