<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String personinfo = request.getParameter("personinfo") == null ? "" : request.getParameter("personinfo");
%>
<powersi:html>
<head>
<powersi:head title="选择申请信息" target="_self" />
</head>
<body>
	<powersi:form id="choosepersonform">
		<powersi:panelbox key="panel_result" title="工伤医疗期信息">
			<powersi:datagrid id="selectPerson" delayLoad="true" height="90%"
				showReload="false" onSelectRow="selectRow" checkbox="true"
				isMultiSelect="false" enabledSort="false">
				<powersi:datagrid-column display="工伤认定ID" name="bka042" />
				<powersi:datagrid-column display="医疗期ID" name="blz516" />
				<powersi:datagrid-column display="生效时间" name="aae030" />
				<powersi:datagrid-column display="失效时间" name="aae031" />
			</powersi:datagrid>
		</powersi:panelbox>
		<powersi:hidden id="personinfo" name="personinfo" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
 	 $(function(){
	 	try{
		 var data = decodeURI(decodeURI("<%=personinfo%>"));
				if (data && data.length > 0) {
					$("#personinfo").val(data);
					var persdata = powersi.tojson($("#personinfo").val());
					selectPerson.loadData(persdata);
				}
			} catch (e) {
				popupError(e);
			}
		});

		function selectRow(rowdata, rowid, rowobj) {
			var bizinfo = new Object();
			bizinfo.bka042 = rowdata['bka042'];
			bizinfo.blz516 = rowdata['blz516'];
			bizinfo.aae031 = rowdata['aae031'];
			bizinfo.aae030 = rowdata['aae030'];
			if (bizinfo) {
				setDialogReturn(bizinfo);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>