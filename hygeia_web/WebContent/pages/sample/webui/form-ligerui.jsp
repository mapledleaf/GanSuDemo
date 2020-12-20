<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="form表单" />
<style type="text/css">
.l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}

</style>
</head>
<body class="page">
<div class="divCenter">
	<powersi:form name="form1" method="post" id="form1">
		<table cellpadding="0" cellspacing="0" class="tableFrame">
			<tr>
				<td align="right" class="l-table-edit-td">名字:</td>
				<td align="left" class="l-table-edit-td"><input name="txtName"
					type="text" id="txtName" ltype="text" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" valign="top">性别:</td>
				<td align="left" class="l-table-edit-td"><input id="rbtnl_0"
					type="radio" name="rbtnl" value="1" checked="checked" /><label
					for="rbtnl_0">男</label> <input id="rbtnl_1" type="radio"
					name="rbtnl" value="2" /><label for="rbtnl_1">女</label></td>
				<td align="left"></td>
			</tr>

			<tr>
				<td align="right" class="l-table-edit-td" valign="top">爱好:</td>
				<td align="left" class="l-table-edit-td"><input
					id="CheckBoxList1_0" type="checkbox" name="CheckBoxList1$0"
					checked="checked" /><label for="CheckBoxList1_0">篮球</label><br />
				<input id="CheckBoxList1_1" type="checkbox" name="CheckBoxList1$1" /><label
					for="CheckBoxList1_1">网球</label> <br />
				<input id="CheckBox1" type="checkbox" name="CheckBoxList1$1" /><label
					for="CheckBoxList1_1">足球</label></td>
				<td align="left"></td>
			</tr>

			<tr>
				<td align="right" class="l-table-edit-td">入职日期:</td>
				<td align="left" class="l-table-edit-td"><input name="txtDate"
					type="text" id="txtDate" ltype="date" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">年龄:</td>
				<td align="left" class="l-table-edit-td"><input name="txtAge"
					type="text" id="txtAge" ltype='spinner' ligerui="{type:'int'}"
					value="20" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">部门:</td>
				<td align="left" class="l-table-edit-td"><select
					name="ddlDepart" id="ddlDepart" ltype="select">
						<option value="1">主席</option>
						<option value="2">研发中心</option>
						<option value="3">销售部</option>
						<option value="4">市场部</option>
						<option value="5">顾问组</option>
				</select></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">地址:</td>
				<td align="left" class="l-table-edit-td"><textarea cols="100"
						rows="4" class="l-textarea" style="width: 400px"></textarea></td>
				<td align="left"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="提交" id="Button1" onclick="submitForm()"
			class="l-button l-button-submit" /> <input type="reset" value="重置"
			class="l-button l-button-reset" />
	</powersi:form>
</div>
	<powersi:errors />
	<script type="text/javascript">
		$(function() {
			$("#form1").ligerForm();
		});
		function submitForm(){
			alert(powersi.tostring($("#form1").formSerialize()));
		}
	</script>
</body>
</powersi:html>