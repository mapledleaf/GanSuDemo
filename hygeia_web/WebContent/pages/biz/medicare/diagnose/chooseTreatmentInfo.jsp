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
		<powersi:panelbox key="panel_result" title="门慢申请信息">
			<powersi:datagrid id="selectPerson" delayLoad="true" height="90%"
				showReload="false" onSelectRow="selectRow" checkbox="true"
				isMultiSelect="false" enabledSort="false">
				<powersi:datagrid-column display="申请号" name="aaz267" />
				<powersi:datagrid-column display="申请医院" name="akb021" />
				<powersi:datagrid-column display="疾病" name="aka121" />
				<powersi:datagrid-column display="生效时间" name="aae030" />
				<powersi:datagrid-column display="失效时间" name="aae031" />
				<powersi:datagrid-column display="申请内容" name="bka155" />
				<powersi:datagrid-column display="bka006" name="bka006" hide="true" />
				<powersi:datagrid-column display="bka026" name="bka026" hide="true" />
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
				alert(e);
			}
		});

		function selectRow(rowdata, rowid, rowobj) {
			var bizinfo = new Object();
			bizinfo.aaz267 = rowdata['aaz267'];
			bizinfo.bka006 = rowdata['bka006'];
			bizinfo.bka155 = rowdata['bka155'];
			bizinfo.aka121 = rowdata['aka121'];
			bizinfo.bka026 = rowdata['bka026'];
			if (bizinfo) {
				setDialogReturn(bizinfo);
			}
			setTimeout("closeDialog();", 500);
		}
	</script>
</body>
</powersi:html>