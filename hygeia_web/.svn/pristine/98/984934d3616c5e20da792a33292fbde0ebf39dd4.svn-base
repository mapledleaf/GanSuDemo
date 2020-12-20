<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();

	String param = request.getParameter("param");
	if(param == null){
		param = "";
	}
%>

<powersi:html>
<head>
<powersi:head title="Redis详情" target="_self"/>
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body onload="">
	    <powersi:form id="queryForm" >
		<powersi:panelbox  title="查询的key值">
			<powersi:panelbox-toolbar>
				<powersi:button id="redis_query" label="查 询"
					onclick="query()"  />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout >
				<powersi:editorlayout-row>
					<powersi:textfield id="key" name="key" label="key值" maxlength="30" colspan="3"
						readonly="false"/>
					<powersi:textfield id="conDition" label="过滤条件" />
					<powersi:hidden id = "type" />
					<powersi:hidden id = "len" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox  title="对应的value值" allowCollapse="false">
		<div id = "str" style="">
			<powersi:textarea  id="value" value="" cols="10"  rows="20" />
		</div>
	</powersi:panelbox>
</body>
</powersi:html>
<script type="text/javascript">
	$(function(){
		//对话框需要获取主窗口句柄
		var param = getDialogParam();
		if(!param){
			param = '<%=param%>';
			if(param == ''){
				param = null;
			}
		}
		$("#key").combogrid({
			minLength : 1,
			autoChoose : false,
			searchIcon : true,
			alternate : true,
			width : "595px",
			url : "<%=path%>/manager/RedisInfo.action",
			colModel : [ {
				"columnName" : "key",
				"width" : "90",
				"label" : "key值"
			}, {
				"columnName" : "type",
				"width" : "10",
				"label" : "类型"
			}],
			select : function(event, ui) {
				var data = ui.item;
				$("#key").val(data["key"]);
				queryConDition();
				return false;
			}
		});
	});

	function query(){
		var key = $("#key").val();
		if(key == '' || key == null ){
			alert("key值不能为空！");
	    	return;
		}
		var conDition = $("#conDition").val();
		var type = $("#type").val();
		var len = $("#len").val();
		if(type == "set" || type == "list"){
			if(!isNaN(conDition)){
				if(parseInt(conDition)>=parseInt(len)){
					alert("结果集长度为："+ len +"(输入的数字不能大于这个值！)");
					return;
				}
				if(parseInt(conDition)<0){
					alert("输入的数字不能为负数!)");
					return;
				}
			}else{
				alert("对应的结果集中储存的数据为list,过滤条件必须为数字！");
				return;
			}
		}
		postJSON("<%=path%>/manager/RedisInfo!queryDeital.action",
				{
					"key" : key,
					"conDition" : conDition
				}, function(json) {
					if (!checkResult(json)) {
						return;
					}
					try{
						if(json.data.BZ == 0) {
							$("#value").attr("value",json.data.value);
						}else{
							if(json.data == "[null]" ){
								alert("没有查到对应的数据！");
								//$("#value").attr("value","没有查到对应的数据！");
								return;
							}
							$("#value").attr("value",json.data);
						}
					}catch (e) {
							alert("没有查到对应的数据！");
					}
				});
	}
	
	
	function queryConDition() {
		var key = $("#key").val();
		if(key == '' || key == null ){
			alert("key值不能为空！");
	    	return;
		}
		postJSON("<%=path%>/manager/RedisInfo!queryConDition.action",
				{
		 
					"key" : key
				}, function(json) {
					if (!checkResult(json)) {
						return;
					}
					
					if(json.data.type == "set" || json.data.type == "list") {
						$("#len").attr("value",json.data.lenght);
					}
					$("#type").attr("value",json.data.type);
				});
	}
</script>
