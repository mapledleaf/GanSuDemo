<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
%>
<powersi:html>
<head>
<powersi:head title="实施模板管理" />
</head>
<body>
	<powersi:form id="modelForm" name="modelForm" namespace="/actualize"
		action="ActualizeManageAction!queryActualizeModels.action">
		<powersi:panelbox >
		<powersi:panelbox-toolbar>
			<powersi:button id="btAdd" label="新增业务大类" onclick="doAdd1()" />
			<powersi:button id="btAdd" label="新增业务子类" onclick="doAdd2()" />
		</powersi:panelbox-toolbar>
		<powersi:editorlayout-row>
			<powersi:hidden id="bkf300" name="kfd1Dto.bkf300" />
			<powersi:hidden id="akb020" name="kfd1Dto.akb020" />
		</powersi:editorlayout-row>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="实施模板维护" allowCollapse="false">
		<powersi:datagrid id="modelid" formId="modelForm" delayLoad="true"
			enabledSort="true" showReload="false" usePager="false">
			<powersi:datagrid-column name="bkf300" key="bkf300" label="业务大类id" width="10%" />
			<powersi:datagrid-column name="bkf301" key="bkf301" label="业务大类描述" width="30%"/>
			<powersi:datagrid-column name="bkf302" key="bkf302" label="业务子类id" width="10%" />
			<powersi:datagrid-column name="bkf303" key="bkf303" label="业务子类编码" width="20%"/>
			<powersi:datagrid-column name="bkf304" key="bkf304" label="业务子类描述" width="30%"/>
		</powersi:datagrid>
	</powersi:panelbox>

	<powersi:errors />


	<script type="text/javascript">
		window.onload = function(){
			$('#akb020').val("<%=hospital_id%>");
		 	var hospital_id = $('#akb020').val();
		 	
		 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
				popupAlert("医院编码不能为空，请检查登陆信息！",3000);
		    	return;
			}
		 	modelid.reload();	    
		}
		
		function doAdd1() {
      		popupDialog({
       			url: "<%=path%>/pages/biz/medicare/actualize/ActualizeNew.jsp",
						onClosed : function() {
							var ret = this.returnValue;
							modelid.reload(true);
						}
					}, 300, 700);

		}

      	function doAdd2() {
      		popupDialog({
       			url: "<%=path%>/actualize/ActualizeManageAction!queryBizInfo.action",
						onClosed : function() {
							modelid.reload(true);
						}
					},300, 700);

		}
	</script>
</body>
</powersi:html>