<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="pagination" />
<%@ include file="/pages/util/header/pagination.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	//初始化分页对象
	$("#pager").pagination({
        rows: 1000,
        url: rootPath + '/sample/Sample!queryAa10.action',
        callback: loadComplete
    });
});

function loadComplete(data){
	//导入数据
	importDataWindowWithArray("dw_1", data, true);
}

function queryForm(){
	if(!checkForm('#form1')){
		return;
	}
	
	//设置表单参数
	$('#pager').pagination("param", $('#form1').serialize());
	
	//加载第一页
	$('#pager').pagination("load", 1);
}

function showAttr() {
	alert(powersi.tostring($('#pager').pagination('attr')));
}

function queryVar(){
	//设置变量参数
	$('#pager').pagination("param", form2json('#form1'));
	
	
	//加载第一页
	$('#pager').pagination("load", 1);
}

function queryNone(){
	//设置无参数
	$('#pager').pagination("param", {});
	
	//修改分页大小
	$('#pager').pagination('option', 'rows', 1000);
	
	//加载第一页
	$('#pager').pagination("load", 1);
}
</script>
</head>
<body>
	<powersi:form id="form1" disabled="true" action="Sample!queryAa10" namespace="/sample">
		<powersi:panelbox title="查询参数">
			<powersi:editorlayout cols="6">
				<tr>
					<powersi:textfield id="aaa100" name="aaa100"  key="aaa100" required="false" />
					<powersi:textfield id="aaa102" name="aaa102" key="aaa102" />
					<powersi:textfield id="aaa103" name="aaa103" key="aaa103" />
				</tr>
				<tr>
					<td colspan="6" class="tdButton">
						<powersi:button id="btSubmit" value="表单参数" onclick="queryForm()" />
						<powersi:button id="btQuery" value="变量参数" onclick="queryVar()" />
						<powersi:button id="btNone" value="无参数" onclick="queryNone()" />
						<powersi:button id="btPage" value="显示属性" onclick="showAttr()" />
					</td>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:panelbox title="查询结果">
			<div id="pager"></div>
			<powersi:datawindow id="dw_1" value="" height="100%" width="100%"
				name="dw_aa10" clicked="sortClicked;selectClicked;checkAllClicked('cbxall', 'flag');"
				rowFocusChanged="selectRowFocusChanged">
			</powersi:datawindow>
		</powersi:panelbox>
	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>