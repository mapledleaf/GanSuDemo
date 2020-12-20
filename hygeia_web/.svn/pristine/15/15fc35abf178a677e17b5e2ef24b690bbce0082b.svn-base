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
<powersi:head title="医院信息" />

<body>
	<powersi:panelbox key="医院信息列表">
		<powersi:panelbox-toolbar>
			<powersi:button id="btn_enter" value="全部刷新" onclick="refreshYYMl('true')" />
			<powersi:button id="btn_enter" key="button_ok" onclick="refreshYYMl('false')" />
		</powersi:panelbox-toolbar>
		
	<%-- 	<powersi:editorlayout
			cols="10%,80%">
			<powersi:editorlayout-row>
				<powersi:textfield id="stext" name="stext" value='请输入信息回车！' onKeyDown="getYyInfo(this)" label="医院编码" maxlength="30" cssStyle="width:200px;"
					readonly="false" onclick="begingInput();"  />
			</powersi:editorlayout-row>
		</powersi:editorlayout> --%>
		
		<powersi:datagrid id="gridBizYyInfo" pageSize="20" onReload="loadYyInfo()"
		 checkbox="true" autoWidth="true" colDraggable="false" showFilter="true">
		 	<powersi:toolbar>
				<powersi:toolbar-item id="search" position="right"></powersi:toolbar-item>
			</powersi:toolbar>
			<powersi:datagrid-column name="yybm"  display="医院编码" width="50%"/>
			<powersi:datagrid-column name="yymc" display="医院名称" width="50%"/>
			<%-- <powersi:datagrid-column name="yydj_name" display="医院等级"	width="20%" />
			<powersi:datagrid-column name="yyjb_name" display="医院级别" width="20%" /> --%>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	
<script type="text/javascript">
$(function() {
	$(document).ready(function() {
		loadYyInfo();
	});
	//绑定工具栏
	gridBizYyInfo.bindCondition(cond2, 'search');
	
});

//加载医院信息列表
function loadYyInfo() {
	postJSON(rootPath + "/manager/BizCacheManager!findBizYyInfo.action",null,showBizYyInfo);
}
function showBizYyInfo(json) {
    if (!checkJSONResult(json)) {
        return;
    }
   var retdata = powersi.tostring(json);
   var trHtml="";
   var data = json.data;
   gridBizYyInfo.reset();
   
   if(data==""){
  	 	alert("没有符合条件的信息");
   }else{
  	 	gridBizYyInfo.loadData(json.data);
   }
   
   sortGrid();
};

function sortGrid() {
	gridBizYyInfo.sort('yybm asc');
}

//文本框点击事件
function begingInput(){
	if($("#stext").val()=='请输入信息回车！'){
		document.getElementById("stext").value = '';
	}
}
function refreshYYMl(ifall) {
	
	var rowsData = gridBizYyInfo.getSelectedRows();
	
	var yybm = "";
	//判断是否有选择,并且不是刷新全部
	if(rowsData.length == 0 && ifall == "false") {
		alert("至少选择一个医院机构!");
		return;
	}
	
	//循环取出选择的医院编码
	for(var row in rowsData) {
		yybm = yybm+rowsData[row].yybm+",";
	}
	
	postJSON(rootPath + "/manager/BizCacheManager!refreshCache.action", {"bizFlag": "YYML","yybm":yybm,"ifall":ifall}, showMsg);
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