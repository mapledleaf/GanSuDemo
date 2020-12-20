<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="个人累计查询" target="_self" />
</head>
<body>
	<powersi:form id="queryForm"
		action="CommonManagerAction!cumulativeQuery.action" namespace="/comm">
		<powersi:groupbox title="查询条件">
		<powersi:editorlayout cols="6">
			<powersi:editorlayout-row>
				<powersi:hidden id="aac001" name="inHospitalDTO.aac001" />
				<powersi:hidden id="baa027" name="inHospitalDTO.baa027" />
				<powersi:hidden id="caa027" name="inHospitalDTO.caa027" />
				<powersi:hidden id="aka130" name="inHospitalDTO.aka130" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aae001" name="inHospitalDTO.aae001" label="年度" mask='year'/>
				<powersi:editorlayout-button colspan="2">
					<powersi:submit id="btSubmit" key="button_query" />
					<powersi:button cssClass="button" key="button_close" onclick="javascript:closeDialog();"/>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:groupbox>
	<powersi:groupbox title="查询结果">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			showReload="false"  enabledSort="false" >
			<powersi:datagrid-column name="aae140" display="险种" code="aae140" width="100"/>
			<powersi:datagrid-column name="aae001" display="年度" width="100"/>
			<powersi:datagrid-column name="aka036" display="累计名称" width="200"/>
			<powersi:datagrid-column name="ake053" display="累计值" width="100"/>
<%-- 			<powersi:datagrid-column name="ake038" display="剩余金额(仅供参考)" /> --%>
		</powersi:datagrid>
	</powersi:groupbox>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		window.onload = function(){
			var myDate = new Date();
			var aaa027 = $("#baa027").val().substring(0,4);
			var year = myDate.getFullYear();
    		$('#aae001').val(year);
			$("#queryForm").submit();
		}
		</script>
</body>
</powersi:html>