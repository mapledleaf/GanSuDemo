<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
%>


<powersi:html>
<head>
<powersi:head title="住院费用导入" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" namespace="/medicare"
		action="">
		<powersi:panelbox key="panel_query" title="文件导入" allowCollapse="false">
			<powersi:panelbox-toolbar>
				<powersi:button name="upload" value="上 传 " onclick="upFile()" />
				<powersi:button value="模版下载" onclick="downloadFile()" />
				<powersi:button id="btClose" label="取 消"
					onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:file id="imgFile" name="imgFile" size="70" label="选择导入文件"
						colspan="4" required="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<script type="text/javascript">
	
	//下载模板
	function downloadFile()
	{
		location.href="<%=path%>/medicare/HospManageAction!downLoadExampleXls.action?bzc001=12";
	}
	
	//导入的文件上传
	function upFile(){
		if(document.getElementById("imgFile").value == ''){
			popupAlert("导入文件不能为空！");
			return;
		}
		
		$("#mainForm").ajaxSubmit( {   
	        url : "<%=path%>/medicare/HospManageAction!uploadFileInFee.action",
				datatype : "json",
				success : function(json) {
					var jsonData = jQuery.parseJSON(json);
					var data = jsonData.data;
					popupInfo(jsonData.message);
				},
				error : function(json) {
					popupError(json.message);
				}
			});

		}
	</script>
</body>
</powersi:html>