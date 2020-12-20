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
<powersi:head title="社保卡领卡点查询" />
</head>
<body>
	<powersi:form id="deptForm" name="deptForm" namespace="/common"
		action="CommonManagerAction!cardReceiveQueryManage.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="button_save" key="本地查询" onclick="queryInfo(1)" ></powersi:button>	
				<powersi:button id="button_save" key="卡管查询" onclick="queryInfo(0)" ></powersi:button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac002" label="社会保障号" name="inHospitalDTO.aac002" required="true" />
					<powersi:hidden id="bizid" name="inHospitalDTO.bizid" value="1" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="查询信息" allowCollapse="false">
		<powersi:datagrid id="deptid" formId="deptForm" delayLoad="true" enabledSort="false" showReload="false" >
			<powersi:datagrid-column name="aac003" key="aac003" label="姓名" />
			<powersi:datagrid-column name="aac002" key="aac002" label="社会保障号" width="200" />
			<powersi:datagrid-column name="baz015_lk" key="baz015_lk" label="银行联系电话" />
			<powersi:datagrid-column name="aae008" key="aae008" label="开户银行"  />
			<powersi:datagrid-column name="baz012" key="baz012" label="开户网点" width="250" />
			<powersi:datagrid-column name="baz013_lk" key="baz013_lk" label="领卡地址"  width="400"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />


	<script type="text/javascript">
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

		var idcardRegx = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$|^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$/;
		/*读卡获取后台信息*/
		function queryInfo(pra) {
			var aac002=$("#aac002").val();
			if (!idcardRegx.test(aac002)) {
				deptid.reset();
				popupAlert("输入社会保障号 错误！", "提示", "warn");
				return;
			}
			if(0==pra){
				$("#bizid").val("0");
			}else{
				$("#bizid").val("1");
			}
			$("#deptForm").submit();
		}
	</script>
</body>
</powersi:html>
