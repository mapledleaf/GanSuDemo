<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="体检目录匹配查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HealthAction!queryItemMatch.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<button type="submit" id="btSubmit" class="button">
					<i class="icon-search"></i>查 询
				</button>
				<button type="button" class="button"
					onclick="grid.exportExcel2007()">
					<i class="icon-signout"></i>导 出
				</button>
				<%-- 	<powersi:button id="print" buttonIcon="icon-print" label="打 印" /> --%>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="0%,10%,10%,13%">
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="healthDTO.akb020" />
					<powersi:hidden id="aaa027" name="healthDTO.aaa027" />
					<powersi:hidden id="type" name="healthDTO.type" value="cata" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="healthDTO.caa027"
						key="caa027" label="中心系统" headerKey="00" codeType="caa027" />
					<powersi:codeselect id="service_center_id" name="healthDTO.flag"
						key="service_center_id" label="选择查询方式"
						list="#{'1':'从结算云获取','2':'从中心获取'}" headerKey="0" />
					<td colspan="10"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="目录列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			checkbox="false" showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" enabledSort="false" exportFileName="'体检项目目录信息'">
			<powersi:datagrid-column name="bkh027" key="bkh027" label="中心目录编码"
				frozen="true" />
			<powersi:datagrid-column name="bkh028" key="bkh028" label="中心目录名称"
				frozen="true" />
			<powersi:datagrid-column name="bkh029" key="bkh029" label="医院目录编码"
				frozen="true" />
			<powersi:datagrid-column name="bkh030" key="bkh030" label="医院目录名称"
				frozen="true" />
			<powersi:datagrid-column name="bkh046" key="bkh046" label="统计类别"
				frozen="true" code="bkh046" />
			<%-- 			<powersi:datagrid-column name="aka021" key="aka021" label="五笔码" />
			<powersi:datagrid-column name="aka020" key="aka020" label="拼音码" /> --%>
			<powersi:datagrid-column name="bkh076" key="bkh076" label="价格" />
			<powersi:datagrid-column name="bkh045" key="bkh045" label="标准单位" />
			<powersi:datagrid-column name="bke204" key="bke204" label="修订时间" />
			<powersi:datagrid-column name="bke205" key="bke205" label="修订人工号" />
			<%-- 			<powersi:datagrid-column name="bke206" key="bke206" label="修改人" />
			<powersi:datagrid-column name="bke207" key="bke207" label="审核时间" />
			<powersi:datagrid-column name="bke208" key="bke208" label="审核人工号" />
			<powersi:datagrid-column name="bke209" key="bke209" label="审核人" />
			<powersi:datagrid-column name="bkh048" key="bkh048" label="审核状态" /> --%>
		</powersi:datagrid>
		<powersi:hidden id="flag" key="flag" name="healthDTO.flag" />
	</powersi:panelbox>
	<powersi:errors />

	<script type="text/javascript">

	window.onload = function(){
	 	$('#akb020').val("<%=hospital_id%>");
		$('#aaa027').val("<%=aaa027%>");
			var hospital_id = $('#akb020').val();

			if ($("#akb020").val() == '' || $("#akb020").val() == null) {
				alert("医院编码不能为空，请检查登陆信息！");
				return;
			}

		}
	
	</script>
</body>
</powersi:html>