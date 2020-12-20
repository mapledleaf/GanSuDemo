<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="datagrid树" />
<script src="${rootPath }/pages/sample/webui/data-dept.js" charset="utf-8"></script>
</head>
<body>
<body>
	<div class="tabbable">
		<ul class="nav nav-tabs" id="tabs1">
			<li class="active">
				<a data-toggle="tab" href="#tab-pane1" id="tab1"> 
					<i class="icon-desktop red"></i> Tree DataGrid
				</a>
			</li>
		</ul>
		
		<div class="tab-content content">
			<div id="tab-pane1" class="tab-pane active">
				<powersi:datagrid id="grid1" data="deptdata" alternatingRow="false"
					exportFileName="'部门树列表'"
					tree="{columnId:'dept_name', idField:'dept_id', parentIDField:'dept_up_id'}">
					<powersi:toolbar>
						<powersi:toolbar-item id="text" icon="info1" text="树表格需要配置列id"/>
						<powersi:toolbar-item id="text" icon="info2" text="树表格需要自行排序"/>
						<powersi:toolbar-item id="text" icon="info3" text="树表格不支持分页"/>
						<powersi:toolbar-item id="text" icon="info4" text="树表格不支持排序"/>
						<powersi:toolbar-item id="pdf" text="导出pdf" icon="pdf" position="right" click="itemClick" />
						<powersi:toolbar-item id="excel2007" text="导出Excel2007" icon="excel2007" position="right" click="itemClick" />
						<powersi:toolbar-item id="excel2003" text="导出Excel" icon="excel2003" position="right" click="itemClick" />
					</powersi:toolbar>
					<powersi:datagrid-column id="dept_name" name="dept_name" display="部门名称" width="50%" minWidth="150" align="left"/>
					<powersi:datagrid-column id="dept_name2" name="dept_name" display="部门名称(无收缩按钮)" width="50%" minWidth="150" />
					<powersi:datagrid-column id="dept_id" name="dept_id" display="部门编码" width="100" />
					<powersi:datagrid-column name="dept_up_id" display="上级部门编码" width="100" />
					<powersi:datagrid-column name="dis_order" display="显示序号" width="100" />
				</powersi:datagrid>
			</div>
		</div>
	</div>
<powersi:errors />
<script type="text/javascript">
function itemClick(id){
	//树表格必须自行获取数据送入导出函数，否则只会导出一级数据
	var data = grid1.getData();
	if(id == 'pdf'){
		grid1.exportPdf(data);
	} else if(id == 'excel2007'){
		grid1.exportExcel2007(data);
	} else {
		grid1.exportExcel(data);
	}
}
</script>
</body>
</powersi:html>