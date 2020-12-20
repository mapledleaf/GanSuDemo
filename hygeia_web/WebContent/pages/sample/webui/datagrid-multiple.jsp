<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="datagrid多表头" />
<script src="${rootPath }/pages/sample/webui/data-company.js" charset="utf-8" type="text/javascript"></script>
<script type="text/javascript">
//复制数据，datagird的操作会修改原始数据，所以每个datagrid使用数据的副本
var data1 = [], data2 = [];
$.each(companydata.Rows, function(n, row){
	data1.push($.extend({}, row));
	data2.push($.extend({}, row));
});
</script>
</head>
<body>
	<div class="space-y"></div>
	<powersi:datagrid id="grid1" height="48%" heightDiff="0" data="data1"
		exportFileName="'多表头导出1'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
		<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" />
		<powersi:datagrid-column display="国家" name="Country" width="30%" minWidth="100" />
		<powersi:datagrid-column display="城市" name="City" width="30%" minWidth="100" />
		<powersi:datagrid-column display="总表头1">
			<powersi:datagrid-column display="表头1">
				<powersi:datagrid-column display="公司名" name="CompanyName" minWidth="100" width="40%" />
				<powersi:datagrid-column display="个人信息">
					<powersi:datagrid-column display="主键" name="CustomerID" align="left" width="150" />
					<powersi:datagrid-column display="联系名" name="ContactName" align="left" width="150" />
				</powersi:datagrid-column>
			</powersi:datagrid-column>
		</powersi:datagrid-column>
	</powersi:datagrid>
	<div class="space-y"></div>
	<powersi:datagrid id="grid2" height="48%" heightDiff="0" data="data2"
		exportFileName="'多表头导出2'" showExportExcel="true" showExportExcel2007="true" showExportPdf="true">
		<powersi:datagrid-column key="operate" width="60" frozen="true" isSort="false" isExport="false" />
		<powersi:datagrid-column display="国家" name="Country" width="30%" minWidth="100" />
		<powersi:datagrid-column display="城市" name="City" width="30%" minWidth="100" />
		<powersi:datagrid-column display="总表头2">
			<powersi:datagrid-column display="表头2">
				<powersi:datagrid-column display="公司名" name="CompanyName" minWidth="100" width="40%" />
				<powersi:datagrid-column display="个人信息">
					<powersi:datagrid-column display="主键" name="CustomerID" align="left" width="150" />
					<powersi:datagrid-column display="联系名" name="ContactName" align="left" width="150" />
				</powersi:datagrid-column>
			</powersi:datagrid-column>
		</powersi:datagrid-column>
	</powersi:datagrid>
	<powersi:errors />
</body>
</powersi:html>