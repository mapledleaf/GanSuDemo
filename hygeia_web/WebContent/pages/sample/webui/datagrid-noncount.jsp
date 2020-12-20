<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid分页无计数"/>
</head>
<body>
	<powersi:panelbox key="panel_result" title="代码列表(useCount=false)">
		<powersi:datagrid id="grid" url="${rootPath }/sample/Sample!queryCodetable.action" 
			exportFileName="'代码表'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true"
			useCount="false">
			<powersi:datagrid-column display="代码类型" name="code_type" width="150" />
			<powersi:datagrid-column display="代码名称" name="code_name" width="40%" minWidth="100" />
			<powersi:datagrid-column display="代码SQL" name="code_sql" width="60%" minWidth="120" align="left" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>