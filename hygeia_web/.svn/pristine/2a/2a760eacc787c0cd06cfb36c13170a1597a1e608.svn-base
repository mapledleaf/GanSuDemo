<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags" %>
<powersi:html>
<head>
<powersi:head title="选项卡(jqueryui)" />
</head>
<body class="page">
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="divTab1" label="线状图表" />
		<powersi:tab id="tab2" target="divTab2" label="分区图表" />
		<powersi:tab id="tab3" target="divTab3" label="柱状图表" />
		<powersi:tab id="tab4" target="divTab4" label="饼状图表" />
		<div id="divTab1">
			<iframe id="frm1" name="frm1" width="100%" border="0" src="${rootPath}/pages/sample/webui/datagrid-multiple.jsp"></iframe>
		</div>
		<div id="divTab2">
			<iframe id="frm2" name="frm2" width="100%" border="0" src="${rootPath}/pages/sample/webui/datagrid-multiple.jsp"></iframe>
		</div>
		<div id="divTab3">
			<iframe id="frm3" name="frm3" width="100%" border="0" src="${rootPath}/pages/sample/webui/datagrid-multiple.jsp"></iframe>
		</div>
		<div id="divTab4">
			<iframe id="frm4" name="frm4" width="100%" border="0" src="${rootPath}/pages/sample/webui/datagrid-multiple.jsp"></iframe>
		</div>
	</powersi:tabbedpanel>
<powersi:errors />
<script type="text/javascript">
$(function(){
	$('iframe').height($(window).height() - 80).bind('load', function(){
		$(this.contentWindow).resize();
	});
	
	$(window).resize(function(){
		$('iframe').height($(window).height() - 80).each(function(){
			$(this.contentWindow).resize();
		});
	});
});
</script>
</body>
</powersi:html>