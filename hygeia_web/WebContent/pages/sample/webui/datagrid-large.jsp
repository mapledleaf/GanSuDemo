<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid大数据"/>
</head>
<body>
	<powersi:panelbox key="panel_result" title="大数据列表">
		<powersi:datagrid id="grid"
			pageSize="1000" pageSizeOptions="[100, 1000, 10000, 50000]"
			alternatingRow="false"
			exportFileName="'大数据测试'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
			<powersi:datagrid-column display="代码编号" name="code_name" width="25%" minWidth="100" />
			<powersi:datagrid-column display="代码名称" name="code_name" width="25%" minWidth="100" />
			<powersi:datagrid-column display="代码SQL" name="code_sql" width="50%" minWidth="120" align="left" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
$(function() {
	var data = [];
	for(var i = 0; i < 50000; i++){
		data.push({
			"code_no": powersi.lpad(i + 1, 18, 0),
			"code_name" : "测试名称-" + powersi.lpad(i + 1, 6, 0),
			"code_sql": "创智和宇基于互联网+、大数据、云计算等现代信息技术在社会保障、公共就业、人事人才、劳动关系等人社领域的应用，从管理决策、业务经办、业务监管、公共服务等四大方面为人社领域提供了良好的技术支撑。"
		});
	}
	grid.setData(data);
	grid.reload(true);
});
</script>
</body>
</powersi:html>