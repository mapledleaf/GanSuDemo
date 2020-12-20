<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
	String hospital_id = BizHelper.getAkb020();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aae016 = request.getParameter("aae016");
%>
<powersi:html>
<head>
<powersi:head title="社保卡信息查询" />
</head>
<body>
	<powersi:form id="deptForm" name="deptForm" namespace="/common"
		action="CommonManagerAction!queryICcardInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<%--<powersi:submit id="btSubmit" label="查 询"/> --%>
				<powersi:button id="button_save" key="查 询" onclick="queryInfo()" ></powersi:button>	
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aaz500" label="社保卡号"
						name="inHospitalDTO.aaz500" />
					<powersi:textfield id="aac002" label="社会保障号"
						name="inHospitalDTO.aac002" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					 <powersi:hidden id="akb020" name="hospInfoDto.akb020" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="社保卡信息" allowCollapse="false">
		
		<powersi:datagrid id="deptid" formId="deptForm" delayLoad="true" enabledSort="false" showReload="false" >
			<powersi:datagrid-column name="aac003" key="aac003" label="姓名" />
			<powersi:datagrid-column name="aac002" key="aac002" label="社会保障号" width="200" />
			<powersi:datagrid-column name="aaz500" key="aaz500" label="社保卡号" />
			<powersi:datagrid-column name="aac004" key="aac004" label="性别" code="sex" />
			<powersi:datagrid-column name="aaz502" key="aaz502" label="社保卡状态" render="cardStatu" />
			<powersi:datagrid-column name="aac001" key="aac001" label="个人电脑号" />
			<%-- <powersi:datagrid-column name="baz106" key="baz106" label="激活标志" /> --%>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />


	<script type="text/javascript">
		window.onload = function(){
			$('#akb020').val("<%=hospital_id%>");
		 	var hospital_id = $('#akb020').val();
		 	if($("#akb020").val() == '' || $("#akb020").val() == null ){
				popupAlert("医院编码不能为空，请检查登陆信息！");
		    	return;
			}
		 	// deptid.reload();	    
		}
	    
		function cardStatu(row, index, value){
				var str = value;
				if(value == "0"){
					str = "封存";
				}else if(value == "1"){
					str = "正常";
				}else if(value == "2"){
					str = "挂失";
				}else if(value == "3"){
					str = "应用锁定";
				}else if(value == "9"){
					str = "注销";
				}
				return str;
		}
		
		/*读卡获取后台信息*/
		function queryInfo() {
			if (powersi.isnull($("#aaz500").val()) && powersi.isnull($("#aac002").val())) {
				deptid.reset();
				popupAlert("请输入查询条件！");
				return;
			}
			$("#deptForm").submit();
			/* var bizFormData = $("#deptForm").serialize();
			postJSON(
					"${rootPath}/common/CommonManagerAction!queryICcardInfo.action",
					bizFormData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no") {
							deptid.load();
						}
					}); */
		}
	</script>
</body>
</powersi:html>
