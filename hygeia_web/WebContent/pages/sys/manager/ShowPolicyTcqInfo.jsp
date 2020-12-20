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
			<powersi:radio id="operFlag" name="operFlag"
					list="#{'ALL': '全量刷新', 'INCREMENT':'增量刷新'}" value='ALL' colspan="2" onclick="dealoperFlag()"/>
			<powersi:button id="btn_enter" value="全部刷新" onclick="refreshPolicy('true')" />
			<powersi:button id="btn_enter" key="button_ok" onclick="refreshPolicy('false')" />
			<powersi:hidden id="refresh_type" name="refresh_type" value="ALL"/>
		</powersi:panelbox-toolbar>
		
		<powersi:datagrid id="gridPolicyTcqInfo" pageSize="50" onReload="loadPolicyTcqInfo()"
		 checkbox="true" autoWidth="true" colDraggable="false" showFilter="true">
		 	<powersi:toolbar>
				<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
			</powersi:toolbar>
			<powersi:datagrid-column name="code"  display="统筹区编码" width="50%"/>
			<powersi:datagrid-column name="value" display="统筹区名称" width="50%"/>
			<%-- <powersi:datagrid-column name="yydj_name" display="医院等级"	width="20%" />
			<powersi:datagrid-column name="yyjb_name" display="医院级别" width="20%" /> --%>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	
<script type="text/javascript">
$(function() {
	$("input[type='radio'][name=operFlag][value='ALL']").attr("checked",
			true);
	$(document).ready(function() {
		loadPolicyTcqInfo();
	});
	//绑定工具栏
	gridPolicyTcqInfo.bindCondition(cond2, 'search');
	
});

//加载统筹区信息列表
function loadPolicyTcqInfo() {
	postJSON(rootPath + "/manager/BizCacheManager!queryGdsPolicyTcqList.action",null,showPolicyTcqInfo);
}
function showPolicyTcqInfo(json) {
    if (!checkJSONResult(json)) {
        return;
    }
   var retdata = powersi.tostring(json);
   var trHtml="";
   var data = json.data;
   gridPolicyTcqInfo.reset();
   
   if(data==""){
  	 	alert("没有符合条件的信息");
   }else{
	   gridPolicyTcqInfo.loadData(json.data);
   }
   
   sortGrid();
};

function sortGrid() {
	gridPolicyTcqInfo.sort('code asc');
}

//文本框点击事件
function begingInput(){
	if($("#stext").val()=='请输入信息回车！'){
		document.getElementById("stext").value = '';
	}
}
function refreshPolicy(ifall) {
	
	var rowsData = gridPolicyTcqInfo.getSelectedRows();
	
	var tcbm = "";
	//判断是否有选择,并且不是刷新全部
	if(rowsData.length == 0 && ifall == "false") {
		alert("至少选择一个统筹机构!");
		return;
	}
	
	//循环取出选择的统筹区编码
	for(var row in rowsData) {
		tcbm = tcbm+rowsData[row].code+",";
	}
	var refresh_type = $("#refresh_type").val();
	postJSON(rootPath + "/manager/BizCacheManager!refreshCache.action", {"bizFlag": "POLICY","tcbm":tcbm,"ifall":ifall,"refresh_type":refresh_type}, showMsg);
}

function showMsg(json) {
    if (!checkJSONResult(json)) {
       return;
   }
   alert(json.message);
   //关闭弹出框
   
}

function dealoperFlag() {
	var operFlag = $(':radio[name="operFlag"]:checked').val();
	$("#refresh_type").val(operFlag);
}
</script>
</body>
</html>