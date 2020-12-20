<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<powersi:head title="统筹区信息" />

<body>
	<powersi:panelbox key="统筹区信息列表">
		<powersi:panelbox-toolbar>
			<powersi:button id="btn_enter" value="全部刷新"
				onclick="refreshPOLICY('true')" />
			<powersi:button id="btn_enter" key="button_ok"
				onclick="refreshPOLICY('false')" />
		</powersi:panelbox-toolbar>

		<powersi:datagrid id="gridTCQInfo" pageSize="20"
			onReload="loadTCQInfo()" checkbox="true" autoWidth="true"
			colDraggable="false" showFilter="true">
			<powersi:toolbar>
				<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
			</powersi:toolbar>
			<powersi:datagrid-column name="tcqbm" display="统筹区编码" width="50%" />
			<powersi:datagrid-column name="tcqmc" display="统筹区名称" width="50%" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />

	<script type="text/javascript">
		$(function() {
			gridTCQInfo.bindCondition(cond2, 'search');
		});

		function loadTCQInfo() {
			postJSON(rootPath + "/manager/BizCacheManager!findTCQInfo.action",
					null, showTCQInfo); 
			$.each(tcqdata, function(n, row){
				tcqinfo.push($.extend({}, row));
			});
		}
		function showTCQInfo(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			var retdata = powersi.tostring(json);
			var trHtml = "";
			var data = json.data;
			gridTCQInfo.reset();

			if (data == "") {
				alert("没有符合条件的信息");
			} else {
				gridTCQInfo.loadData(json.data);
			}

			sortGrid();
		};

		function sortGrid() {
			gridTCQInfo.sort('tcqbm asc');
		}

		//文本框点击事件
		function begingInput() {
			if ($("#stext").val() == '请输入信息回车！') {
				document.getElementById("stext").value = '';
			}
		}
		function refreshPOLICY(ifall) {

			var rowsData = gridTCQInfo.getSelectedRows();

			var tcqbm = "";
			//判断是否有选择,并且不是刷新全部
			if (rowsData.length == 0 && ifall == "false") {
				alert("至少选择一个统筹区!");
				return;
			}

			//循环取出选择的统筹区编码
			for ( var row in rowsData) {
				tcqbm = tcqbm + rowsData[row].tcqbm + ",";
			}

			postJSON(rootPath + "/manager/BizCacheManager!refreshCache.action",
					{
						"bizFlag" : "POLICY",
						"tcqbm" : tcqbm,
						"ifall" : ifall
					}, showMsg);
		}

		function showMsg(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			alert(json.message);
			//关闭弹出框

		}
	</script>
</body>
</html>