<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%
	String path = request.getContextPath();
%>

<powersi:html>
<head>
<powersi:head title="有效期选择" target="_self" />
</head>
<body>
	<powersi:form id="mainform">
		<powersi:editorlayout cols="2">
			<powersi:editorlayout-row>
				<powersi:textfield id="aae030" name="aae030" label="匹配生效日期"
					mask="date" required="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aae031" name="aae031" label="匹配失效日期"
					mask="date" required="true" disabled="true" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>

	<powersi:form id="queryForm">
		<powersi:editorlayout cols="2">
			<input type="checkbox" name="checkFlag" id="checkFlag"
				onclick="checkDate()" checked="checked"></input>
			<label>无失效日期</label>
		</powersi:editorlayout>
	</powersi:form>

	<div class="div_center">
		<powersi:button cssClass="button" label="保 存" onclick="save()" />
		<powersi:button cssClass="button" label="取消"
			onclick="javascript:closeDialog();" />
	</div>
	<powersi:errors />
	<script type="text/javascript">
		$(function() {
			var nowDate = new Date();
			var year = nowDate.getFullYear()-1;
			var month = nowDate.getMonth() + 1 < 10 ? "0"
					+ (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
			var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate()
					: nowDate.getDate();
			var dateStr = year + "" + month + "" + day;
			$("#aae030").val(dateStr);

		});

		function save() {
			var ho = new Object();

			ho.aae030 = $('#aae030').val();
			ho.aae031 = $('#aae031').val();

			closeDialog(powersi.tostring(ho));
		}

		function checkDate() {
			if (!document.getElementById("checkFlag").checked) {
				$("#aae031").attr("disabled", false);

				var myDate = new Date();
				var year = myDate.getFullYear();
				var month = (myDate.getMonth() + 1).toString().length == 1 ? "0"
						+ (myDate.getMonth() + 1).toString()
						: (myDate.getMonth() + 1).toString();
				var day = myDate.getDate().toString().length == 1 ? "0"
						+ myDate.getDate().toString() : myDate.getDate().toString();
				$('#aae031').val(year + month + day);
			} else {
				$("#aae031").attr("disabled", true);
				$('#aae031').val("");
			}
		}
	
		
	</script>
</body>
</powersi:html>
