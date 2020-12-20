<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<powersi:html>
<head>
<powersi:head title="文本提示" />
<script type="text/javascript">
	function ajaxSubmit() {
		var data = $('#form1').serialize();
		alert(data);
	}

	function verifyForm() {
		alert(checkForm('#form1'));
	}

	function dynamicModify() {
		//密码框动态赋值必须触发focus和blur,且需要分开写，因为指向的对象会发生变化
		$('#password').focus().blur();
		$('#password').val('123');
		
		$('#staff_name').val('操作员');
		$('#remark').val('备注');
		$('#email').val('brige@powersi.com.cn');
		$('#email').val('');
		$('#login_user').focus();
	}
	
	$(document).ready(function() {
		//测试初始化
		$('#staff_name').val('用户名');
	});
</script>
</head>
<body class="page">
	<div class="divCenter" style="width: 400px;">
		<form id="form1">
			<powersi:editorlayout cols="25%,75%">
				<powersi:editorlayout-head title="测试placeholder属性">
				</powersi:editorlayout-head>
			
				<powersi:editorlayout-row>
					<powersi:textfield name="staff_name" id="staff_name"
						key="staff_name" maxlength="30" required="true"
						placeholder="请输入用户名1" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="login_user" id="login_user"
						key="login_user" maxlength="20" required="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:password name="password" id="password" key="password"
						maxlength="20" title="如果不修改密码，请保留为空" placeholder="密码框动态赋值前必须先focus()再blur()" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="home_phone" id="home_phone"
						key="home_phone" maxlength="20" validate="telephone" placeholder="请输入家庭电话"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield name="email" id="email" key="email"
						maxlength="100" validate="email" placeholder="请输入邮箱地址" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea name="remark" id="remark" maxlength="100"
						rows="5" key="remark" placeholder="请输入备注信息" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<div class="divButton">
				<input type="submit" value="form提交" class="button"> <input
					type="button" value="ajax提交" class="button" onclick="ajaxSubmit()">
				<input type="button" value="form校验" class="button"
					onclick="verifyForm()">
				<input type="button" value="动态修改" class="button"
					onclick="dynamicModify()">
			</div>
		</form>
	</div>
	<powersi:errors />
</body>
</powersi:html>