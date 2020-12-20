<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<powersi:head title="特殊疾病限额信息查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HospCataAction!queryDiseaseDirectory.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search" />
				<powersi:button id="btExport" label="导 出" buttonIcon="icon-signout" onclick="grid.exportExcel2007()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,18%,9%,15%,9%,15%,10%,16%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="diseaseDirDTO.caa027" label="选择中心"
						codeType="caa027" headerKey="0"/>
					<powersi:codeselect id="aka035" name="diseaseDirDTO.aka035" label="特殊病种类别" 
						codeType="aka035"/>
					<powersi:textfield id="akb021"      label="医院名称" readonly="true" value="<%=BizHelper.getAkb021() %>"/>
					<powersi:textfield id="aaa027_name" label="统筹区"   readonly="true" value="<%=BizHelper.getAaa027Name() %>"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aka120" name="diseaseDirDTO.aka120" label="疾病编码" />
					<powersi:textfield id="aka121" name="diseaseDirDTO.aka121" label="疾病名称" />
					<powersi:textfield id="aka020" name="diseaseDirDTO.aka020" label="首拼码"   />
					<powersi:textfield id="aka021" name="diseaseDirDTO.aka021" label="五笔码"  />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="特殊疾病限额信息列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true" onDblClickRow="doubleClick"
			showReload="false" frozen="false" enabledEdit="true"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'特殊疾病限额信息'">
			<%-- <powersi:datagrid-column name="aaz149" hide="true"/> --%>
			<powersi:datagrid-column name="aaz164" display="病种ID" />
			<powersi:datagrid-column name="aka120" display="疾病编码" />
			<powersi:datagrid-column name="aka121" display="疾病名称" />
			<powersi:datagrid-column name="aka021" display="五笔码" />
			<powersi:datagrid-column name="aka020" display="首拼码" />
			<powersi:datagrid-column name="aka035" display="疾病分类" code="aka035"/>
			<powersi:datagrid-column name="bke404" display="限额类型" code="bke404"/>
			<powersi:datagrid-column name="bke405" display="医院限额方式" code="bke405"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
<script type="text/javascript">
	function doubleClick(index){
		var row = grid.getRow(index);
		var aaz149 = row['aaz149'];
		popupDialog({
			url: "DiseaseDirectoryDetail.jsp?aaz149="+aaz149,
			onClosed: function() {
				var ret = this.returnValue;
				grid.reload(); 
			}
		}, 400, 800);
	}
</script>
</html>