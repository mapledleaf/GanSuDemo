<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags" %>
<%
	String closeFlag = (String)request.getAttribute("close");
%>
<powersi:html>
<head>
<powersi:head title="新增用户" />
</head>
<body>
	<!-- enctype属性为字符集  必须要填这个属性 才可以发表单提交请求 -->
	<powersi:form id="userForm"  method="post" namespace="/user" action="XakUserEditAction!save" enctype="multipart/form-data" >
		<powersi:editorlayout cols="25% ,75%">
			<powersi:editorlayout-row>
				<powersi:hidden id="id" name="user.id" /> 
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="user.user_idcard" id="user_idcard" required="true" validate="groupRequired[queryString],idcard" maxlength="18" label="身份证号码" placeholder="公民身份证必填"   ></powersi:textfield>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="user.user_name" id="user_name" required="true" validate="maxSize[12]" label="用户名" placeholder="用户名必填"></powersi:textfield>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield name="user.user_age" id="user_age" required="true"  validate="integer,max[100]" label="年龄" placeholder="年龄必填"></powersi:textfield>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:radio codeType="sex" name="user.user_sex"  key="sex"  colspan="1" />
			</powersi:editorlayout-row>
			
		</powersi:editorlayout>
		<div class="divButton">
			 <powersi:submit id="submitBtn" key="button_audit" label="保存" onlick="doSave()"></powersi:submit>
			 <powersi:reset id="userReset" key="button_reset" label="重置"></powersi:reset>
		</div>
	</powersi:form>

<powersi:errors />
<script type="text/javascript">
	//当前数据
	var _curItemData = "";

	function doSave(){
		//保存数据实体对象 
		var saveItemData = $("#userForm").serialize();
		
		//验证
		if (saveItemData == _curItemData) { //所有数据没有被改动过的情况 
	        	alert("没有修改，不需保存");
	        	return;
	     }
		return S('userForm').submit();
		
	}
	
	//关闭窗口  
	function closeWin(flag) {
		if(isPopupDialog()){//
			if(flag){
				setDialogReturn(flag);//设置对话框返回 返回的对象最好转成json 
			}
			setTimeout("closeDialog();", 500);// 定时器 , 500 毫秒关闭 
		} else {// 否则重定向到 下面这个地址 
			window.location = rootPath + "/user/XakUserEditAction.action?id=" + flag;
		}
	}
	
	$(function(){
		//后台审核标志位空 就传close = true 到前台 
		if("<%=closeFlag%>" === "true"){ 
			var id = "${id}";
			
			if(powersi.length(id) > 0){//是否有该id
				if(!isPopupDialog()){//是弹窗则 
					alert('保存成功');
				}
				closeWin(id); //关闭窗口
			} else {
				closeWin(true); 
			}
		} else {
			_curItemData = $("#userForm").serialize();//数据为Form表单提交序列化
		}
		
		
		//更改标题 
		if(($('#id').val()) != ''){
			var title = "编辑用户";
			document.title = title;
		}
	});

		
</script>
</body>
</powersi:html> 