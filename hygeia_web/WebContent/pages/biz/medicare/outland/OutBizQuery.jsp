<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<powersi:html>
<head>
<powersi:head title="异地备案信息查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="OutBizAction!getOutAreaModifyInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect id="baa027" name="outBizDTO.aab301"
						label="参保地统筹区" cssClass="select2" codeType="baa027"
						required="true" displayonly="false" showValue="true" />
					<powersi:codeselect name="outBizDTO.aac043" id="aac043"
						codeType="aac043" label="证件类型" required="true" />
					<powersi:textfield id="aac044" name="outBizDTO.aac044" label="证件号码"
					 	required="true"/>
					<powersi:codeselect name="outBizDTO.bke074" id="bke074"
						codeType="bke074" label="异地类型" 
						codeFilter="data_value in ('4')" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_query" title="异地申请列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			enabledSort="false" showReload="false">
			<powersi:datagrid-column name="aac003" key="aac003" label="姓名" />
			<powersi:datagrid-column name="yzz014" key="yzz014" label="异地就医备案号" />
			<powersi:datagrid-column name="aab301" key="aab301" label="参保地" code="baa027"/>
			<powersi:datagrid-column name="aac002" key="aac002" label="社会保障号" />
			<powersi:datagrid-column name="ykc021" key="ykc021" label="人员类别" code="bka004"/>
			<powersi:datagrid-column name="aac043" key="aac043" label="证件类型" code="aac043"/>
			<powersi:datagrid-column name="aac044" key="aac044" label="证件号码" />
			<powersi:datagrid-column name="aac004" key="aac004" label="性别" code="aac004"/>
			<powersi:datagrid-column name="yyz013" key="yyz013" label="异地就医类别" />
			<powersi:datagrid-column name="yzz041" key="yzz041" label="起始日期" />
			<powersi:datagrid-column name="yzz042" key="yzz042" label="截止日期" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>