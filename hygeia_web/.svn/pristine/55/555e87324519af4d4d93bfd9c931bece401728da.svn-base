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
<powersi:head title="体检套餐查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="ExaminationAction!querySetMeal.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<button type="submit" id="btSubmit" class="button">
					<i class="icon-search"></i>查 询
				</button>
				<button type="button" class="button"
					onclick="grid.exportExcel2007()">
					<i class="icon-signout"></i>导 出
				</button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="setMealDTO.akb020" />
					<powersi:hidden id="aaa027" name="setMealDTO.aaa027" />
					<powersi:hidden id="type" name="setMealDTO.type" value="cata" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="service_center_id" name="setMealDTO.caa027"
						key="service_center_id" label="选择中心" codeType="caa027"
						headerKey="0" />
					<powersi:textfield id="bkh059" name="setMealDTO.bkh059"
						key="bkh059" label="套餐名称" />
				<%-- 	<powersi:codeselect id="bkh012" name="setMealDTO.bkh012"
						key="bkh012" codeType="bkh012" label="体检类别" /> --%>
					<powersi:codeselect id="aae100" name="setMealDTO.aae100"
						key="aae100" codeType="aae100" label="有效标志" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="panel_result" title="体检套餐列表">

		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			showReload="false" frozen="false" enabledEdit="true"
			showExportExcel="true" showExportExcel2007="true" enabledSort="false"
			showExportPdf="true" exportFileName="'体检套餐信息'">
			<powersi:datagrid-column name="bkh015" label="套餐标识" hide="true"/>
			<powersi:datagrid-column name="bkh059" label="套餐名称"/>
			<powersi:datagrid-column name="aaa027" label="统筹区编码"/>
			<powersi:datagrid-column name="bkh012" label="体检类别" />
			<powersi:datagrid-column name="bkh060" label="体检对象年龄段"/>
			<powersi:datagrid-column name="aac004" label="性别" code="aac004"/>
			<powersi:datagrid-column name="bkh318" label="是否省级干部离休套餐" code="bkh318" hide="true"/>
			<powersi:datagrid-column name="bkh063" label="默认套餐 " code="bkh063"/>
			<powersi:datagrid-column name="bkh061" label="生效年度"/>
			<powersi:datagrid-column name="bkh062" label="失效年度"/>
			<powersi:datagrid-column name="bke204" label="修改时间"/>
			<powersi:datagrid-column name="bke205" label="修改人工号"/>
			<powersi:datagrid-column name="bke206" label="修改人"/>
			<powersi:datagrid-column name="aae100" label="当前有效标志" code="aae100"/>

		</powersi:datagrid>
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