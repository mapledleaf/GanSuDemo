<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<powersi:head title="业务采集图片" />
</head>
<body>
	<powersi:hidden id="bkc290"  name="bizQueryDto.bkc290"/>
	<img id="dlrimg"
		 style="width: 100; height: auto; max-width: 100%; max-height: 100%;">
</body>
<script type="text/javascript">
	$(function(){
		if($("#bkc290").val()=="")
			closeDialog();
		$("#dlrimg").attr("src","data:image/png;base64,"+$("#bkc290").val());
	})
</script>
</html>
