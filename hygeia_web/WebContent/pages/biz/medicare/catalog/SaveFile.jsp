<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
%>


<powersi:html>
<head>
<powersi:head title="医院目录导入" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" method="post">
		<powersi:panelbox key="panel_query" title="目录文件导入"
			allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button name="upload" value="上 传 " onclick="upFile()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:textfield id="aaa001" name="aaa001" label="密码"
						required="true" validate="integer" />
					<powersi:textfield id="bzc001" name="bzc001" label="导入序号"
						required="true" validate="integer" />
					<powersi:textfield id="bzc002" name="bzc002" label="导入模板名"
						required="true" colspan="6" validate="maxSize[100]" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:file id="imgFile" name="imgFile" label="选择导入文件"
						colspan="10" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>


	<script type="text/javascript">
	
	//导入的文件上传
	function upFile(){
		if(!checkFormValidtion())
     	{
	  		return;
		}
		
	 	if(document.getElementById("imgFile").value == ''){
			popupAlert("导入文件不能为空！");
			return;
		}
	 	
	 	if($("#aaa001").val() != "100200"){
	 		popupAlert("密码错误！");
	 		return;
	 	}
	 	$("#mainForm").ajaxSubmit( {   
	        url : "<%=path%>/medicare/HospManageAction!saveFile.action",
			datatype : "json",
			success : function(json) {
				var data = json.data;
				popupInfo(data);
			},
			error : function(json) {
				popupError(data);
			}
		});
	}

</script>
</body>
</powersi:html>