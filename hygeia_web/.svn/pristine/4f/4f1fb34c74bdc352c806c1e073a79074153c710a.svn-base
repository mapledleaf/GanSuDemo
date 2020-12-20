<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="鉴定标准" target="_self" />
</head>
<body>
	<powersi:form id="chooseIdentifyForm" namespace="/medicare" action="MzchoHospitalBusApplyAction!getIdentify.action">	
		<powersi:hidden id="bka006" name="mediSpecZHDto.bka006" />	
	</powersi:form>
	<powersi:panelbox key="查询结果">
			<powersi:panelbox-toolbar>
				<powersi:button name="makeChange" label="确定" id="btDel" onclick="makeChange()" />
			</powersi:panelbox-toolbar>
		<powersi:datagrid id="row"  delayLoad="true" showReload="false" checkbox="true" pageSize="10">
			<powersi:datagrid-column name="bkz001" label="鉴定标准代码" width="20%" />
			<powersi:datagrid-column name="bkz002" label="鉴定标准内容" width="80%" />
		</powersi:datagrid>
			
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			var saveItemData = $("#chooseIdentifyForm").serialize();
			postJSON(
					"${rootPath}/medicare/MzchoHospitalBusApplyAction!getIdentify.action",
					saveItemData, function(json){
						if(!checkJSONResult(json)){
						    return;
					    }

						row.loadData(json.data);
				});
			});

		function makeChange() {
			var rows = row.getSelectedRows();	
			var tem="";
			var flag="";
			$.each(rows, function(i, row){
				if(i>0){
					tem += "\r\n";
					flag += ",";
				 }
			     tem += row["bkz001"] + "----" + row["bkz002"] ;
			     flag += row["bkz001"];
			});		
			var indetify = new Object();
			indetify.tem = tem;
			indetify.flag = flag;			
			setDialogReturn(indetify);		
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>