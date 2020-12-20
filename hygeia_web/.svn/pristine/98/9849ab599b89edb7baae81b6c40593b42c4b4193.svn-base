<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="修改用户密码" />
<base target="_self" />
<style type="text/css">
.password_strength_bar{ float:left; background:#d6d3d3; width:70px; height:4px; margin-top:5px; _margin-top:0px; margin-left:5px; _height:2px;font-size:0px;}
.password_strength_txt{ float:left; width:70px; margin-left:5px; text-align:center; color:#b0adad; font-size:12px; }
.password_strength_1 {background:#f77;}
.password_strength_2 {background:#fcac6f;}
.password_strength_3 {background:#b0d877;}
</style>
</head>
<body class="page">
	<powersi:form name="userform" id="userform" action="ChangePassword"
		namespace="/user" method="post" focusElement="oldPassword">
		<powersi:hidden name="method" value="save" />
		<div class="divCenter" style="width: 390px;">
			<powersi:editorlayout cols="150px,240px">
				<powersi:editorlayout-head title="修改用户登录密码"></powersi:editorlayout-head>
				<powersi:editorlayout-row>
					<powersi:password id="oldPassword" name="oldPassword"
						maxlength="20" size="20" label="原密码" required="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:password id="newPassword" name="newPassword" maxlength="20" size="20" required="true" label="新密码" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:password id="confirmPassword" name="confirmPassword" maxlength="20" size="20" label="确认密码" required="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:editorlayout-label>
						<powersi:label required="false">密码强度</powersi:label>
					</powersi:editorlayout-label>
					<powersi:editorlayout-input>
			            <div class="password_strength_bar" id='psb1'></div>
			            <div class="password_strength_bar" id='psb2'></div>
			            <div class="password_strength_bar" id='psb3'></div>
			            <div class="clear"></div>
			            <div class="password_strength_txt" id="pst1">弱</div>
			            <div class="password_strength_txt" id="pst2">中</div>
			            <div class="password_strength_txt" id="pst3">强</div>
					</powersi:editorlayout-input>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<div class="textInfo textLeft" style="margin-top:5px;font-size:105%;">
				<i class="icon-info-sign text130"></i> 长度6-20位，由字母（区分大小写）、数字、符号组成<br />
			</div>
			<div class="divButton">
				<powersi:submit id="btnSubmit" key="button_save" />
				<powersi:button id="btnClose" key="button_close" onclick="closeDialog()" />
			</div>
			<div class="textSuccess textLeft" style="margin-top:5px;font-size:105%;">
				<ul style="padding-left: 20px;">
					强密码规则
					<li>至少包含 8 个字符</li>
					<li>至少包含一个小写字母</li>
					<li>至少包含一个大写字母</li>
					<li>至少包含一个数字</li>
					<li>至少包含一个符号（非字母、数字）</li>
				</ul>
			</div>
		</div>
	</powersi:form>
	<powersi:errors />
<script type="text/javascript">
$(function(){
	if(!isPopupDialog()){
		$('#btnClose').hide();
	}
	
	$('#btnSubmit').click(function(){
		var pwd = $('#newPassword').val();
		if(pwd == $('#oldPassword').val()){
			alert('您的新密码和旧密码一致，无需修改！');
			return false;
		}
		
		if(powersi.length(pwd) < 6){
			alert('新密码至少包含6个字符！');
			$('#newPassword').focus();
			return false;
		}
		
		if(pwd != $('#confirmPassword').val()){
			alert('您的新密码和确认密码不一致，请确认！');
			$('#confirmPassword').focus();
			return false;
		}
		
		if(chekckPasswordStrength(pwd) < 2){
			 return confirm('您的密码过于简单，是否保存？');
		}
		
		return true;
	});
	
	$('#newPassword').keyup(function(){
		showPasswordStrength($(this).val());
	});
});

function showPasswordStrength(pwd) {
	var level = chekckPasswordStrength(pwd);
	$('#psb1').removeClass('password_strength_1');
	$('#psb2').removeClass('password_strength_2');
	$('#psb3').removeClass('password_strength_3');
	$('.password_strength_txt').removeClass("red");
	if(level == 3){
		$('#psb1').addClass('password_strength_1');
		$('#psb2').addClass('password_strength_2');
		$('#psb3').addClass('password_strength_3');
		
		$('#pst3').addClass('red');
	} else if(level == 2){
		$('#psb1').addClass('password_strength_1');
		$('#psb2').addClass('password_strength_2');
		
		$('#pst2').addClass('red');
	} else if(level == 1){
		$('#psb1').addClass('password_strength_1');
		
		$('#pst1').addClass('red');
	}
}

function chekckPasswordStrength(pwd) {
	var len = powersi.length(pwd);
	if(len == 0){
		return 0;
	}
	
	var score = 0;
	if(/[a-z]+/.test(pwd)){
		score ++;
	}
	if(/[0-9]+/.test(pwd)){
		score ++;
	}
	if(/[A-Z]+/.test(pwd)){
		score ++;
	}
	if(/\W+/.test(pwd)){
		score ++;
	}
	
	if(len <= 6){
		return 1;
	} else if(len < 8){
		if(score <= 2){
			return 1;
		} else{
			return 2;
		}
	} else{
		if(score >= 3){
			return 3;
		} else if(score >= 2){
			return 2;
		} else{
			return 1;
		}
	}
}
</script>
</body>
</powersi:html>