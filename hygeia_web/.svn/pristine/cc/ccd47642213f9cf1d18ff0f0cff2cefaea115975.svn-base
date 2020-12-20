<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="待办列表" />
</head>
<body>
	<powersi:panelbox title="待办列表" iconClass="icon-tasks" allowCollapse="false">
		<powersi:panelbox-toolbar>
			<a href="javascript:refreshData();" title="刷新" class="orange"><i class="icon-refresh"></i></a>
		</powersi:panelbox-toolbar>
		<powersi:datagrid id="grid" url="${rootPath }/message/MessageAction!queryTaskList.action" dataAction="local">
			<powersi:datagrid-column key="operate" render="renderOp" width="80"></powersi:datagrid-column>
			<powersi:datagrid-column width="50%" minWidth="120" name="task_name" display="业务名称"></powersi:datagrid-column>
			<powersi:datagrid-column width="50%" minWidth="120" name="task_desc" display="业务描述"></powersi:datagrid-column>
			<powersi:datagrid-column name="task_count" display="任务数" width="80"></powersi:datagrid-column>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
<script type="text/javascript">
	function renderOp(rowdata, rowindex, value, column) {
		var a = [];
		
		a.push('<input type="button" value="处理" class="linkButton"');
		a.push(' onclick="handleTask(');
		a.push(rowindex);
		a.push(')"');
		
		a.push(" />");
		
		return a.join('');
	}
	
	function refreshData(){
		if(grid){
			//清除状态
			grid.reset();
			
			//强制刷新
			grid.reload(true);
		}
	}
	
	function handleTask(idx){
		var row = grid.getRow(idx);
		if(!row)return;
		
		if(parent && $.isFunction(parent.openLink)){
			parent.openLink({
				url: row['task_url'],
				title: row['task_name']
			});
		} else {
			openMenu(row['task_url']);
		}
	}
</script>
</body>
</powersi:html>