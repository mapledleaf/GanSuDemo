<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<powersi:head title="卫生站费用批量导入"  target="_self" />
</head>
<body>
	<powersi:form id="leadForm" name="leadForm" action="">
		<powersi:panelbox  allowCollapse="false">
			<powersi:editorlayout cols="5%,20%,60%,20%">
				<tr>
					<td><powersi:file id="imgFile" name="imgFile" size="70" label="选择导入文件" /></td>
					<td><powersi:button name="upload" value="上 传 " onclick="upFile()" /></td>
					<powersi:hidden id="imgFileName" name="imgFileName" />
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	
<script type="text/javascript">
//导入的文件上传
function upFile(){ 
	
	var fileName = $('#imgFile').val();
	if(powersi.isempty(fileName)){
		popupAlert("导入文件不能为空！");
		return;
	}
	var fileNames = new Array();
	fileNames = fileName.split("\\");
	var fileName = fileNames[fileNames.length-1];
	$("#imgFileName").val(fileName);
	$("#leadForm").ajaxSubmit( {   
        url : "${rootPath}/diagnose/DiagnoseBatchChargeAction!uploadFileBatchLead.action",   
        datatype : "json",
        success : function(json){ 
        	json=powersi.tojson(json);
        	if(json.data){
        		//popupAlert(json.data); 
        		setTimeout("closeDialog();", 500);
        		setDialogReturn(json.data);
        	}else{
        		popupInfo(json.message);  
        	}
        	 
        	
        },  
       	error:function(json){ 
       		json=powersi.tojson(json);
       		popupError(json.message);  
        }   
    });    
}

</script>

</body>
</html>