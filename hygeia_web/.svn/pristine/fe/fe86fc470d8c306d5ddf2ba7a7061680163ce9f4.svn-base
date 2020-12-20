<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
%>

<powersi:html>
<head>
<powersi:head title="上传检查诊疗结果" target="_self" />
</head>
<body>
	<powersi:form id="mainForm" name="mainForm" method="post" enctype="multipart/form-data">
		<powersi:panelbox key="panel_query" title="检查诊疗结果导入"
			allowCollapse="false">
			
			<powersi:panelbox-toolbar>
				<powersi:button name="upload" value="上 传 " onclick="upFile()" />
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:textfield id="aaz217" name="checkCureInfoDTO.aaz217" label="就医登记号" required="true" colspan="4"/>
					<powersi:textfield id="bka509" name="checkCureInfoDTO.bka509" label="影像编号" required="true" colspan="4"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="bka510" name="checkCureInfoDTO.bka510" label="影像名称"
						required="true" colspan="10" validate="maxSize[100]" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:file id="imgFile" name="imgFile" label="影像文件"
						colspan="10" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bae013" name="checkCureInfoDTO.bae013" label="说明" colspan="10" />
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
		
	 	if($("#imgFile").val() == ''){
			popupAlert("导入文件不能为空！");
			return;
		}
	 	$("#mainForm").ajaxSubmit( {   
	        url : "<%=path%>/inhospital/CheckCureInfoManagerAction!saveCheckCureInfo.action",
			datatype : "json",
			success : function(json){ 
	        	//json = json.substring(5,json.length-6);//只要print的json结果   
	        	var jsonData = jQuery.parseJSON(json);   
	        	var data = jsonData.data;
	        	popupInfo(jsonData.message);
	        	clearAll();
            },  
	        error:function(json){ 
	        	//json = json.substring(5,json.length-6);//只要print的json结果
	            popupError(json.message);  
            }
		});
	}

	function clearAll(){
		$("#aaz217").val("");
		$("#bka509").val("");
		$("#bka510").val("");
		$("#imgFile").val("");
		$("#bae013").val("");
	}
</script>
</body>
</powersi:html>