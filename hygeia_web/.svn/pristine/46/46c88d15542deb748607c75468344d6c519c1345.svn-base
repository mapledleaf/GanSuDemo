<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	//动态列名集合
	java.util.Map cols = new java.util.LinkedHashMap();
	String[] months = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
	for(int i = 0; i < 12; i ++){
		cols.put("month_" + String.valueOf(i + 1), new String[]{String.valueOf(i + 1), months[i]});
	}
	request.setAttribute("cols", cols);
	
	//测试数据
	String data = "[{\"staff_name\":\"操作员1\", \"dept_name\":\"所属部门1\", \"month_1\":1,\"month_2\":2,\"month_3\":3,\"month_4\":4,\"month_5\":5,\"month_6\":6,\"month_7\":7,\"month_8\":8,\"month_9\":9,\"month_10\":10,\"month_11\":11,\"month_12\":12}," +
	               "{\"staff_name\":\"操作员2\", \"dept_name\":\"所属部门2\", \"month_1\":1,\"month_2\":2,\"month_3\":3,\"month_4\":4,\"month_5\":5,\"month_6\":6,\"month_7\":7,\"month_8\":8,\"month_9\":9,\"month_10\":10,\"month_11\":11,\"month_12\":12}," +
	               "{\"staff_name\":\"操作员3\", \"dept_name\":\"所属部门3\", \"month_1\":1,\"month_2\":2,\"month_3\":3,\"month_4\":4,\"month_5\":5,\"month_6\":6,\"month_7\":7,\"month_8\":8,\"month_9\":9,\"month_10\":10,\"month_11\":11,\"month_12\":12}" +
	               "]";
%>
<powersi:html>
<head>
<powersi:head title="datagrid动态表头" />
<script type="text/javascript">
var data = <%=data %>;
function renderHeader(column){
	return '<span class="red">' + column.display + '</span>';
}
</script>
</head>
<body class="page">
	<powersi:datagrid id="grid" data="data"
		exportFileName="'动态表头'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
		<powersi:datagrid-column display="操作员" name="staff_name" width="50%" minWidth="90" />
		<powersi:datagrid-column display="所属部门" name="dept_name" width="50%" minWidth="90" />
		<powersi:datagrid-column display="月统计汇总">
			<s:iterator value="#request.cols" id="column"> 
				<powersi:datagrid-column display="${ column.value[1]}" headerRender="renderHeader">
			   		<powersi:datagrid-column display="${ column.value[0]}" name="${ column.key}" width="90" isSort="false" />
			   	</powersi:datagrid-column>
			 </s:iterator> 
		</powersi:datagrid-column>
	</powersi:datagrid>
	<powersi:errors />
</body>
</powersi:html>