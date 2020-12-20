<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String path = request.getContextPath();
	String flag = (String)request.getAttribute("flag");
	boolean isNull = request.getAttribute("selectBizList")==null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<script type="text/javascript">
	window.onload = function() {
		if(<%=isNull%>){
			popupInfo("未查到可退费的业务信息！", "提 示", function(){
				closeDialog();
			});
		}
		$("#row").focus();
	}

	function getBizInfo() {
		if ($(':radio[name="aaz164"]:checked').val() == null) {
			popupAlert('请选择一行!');
			return;
		}
		var biz = $(':radio[name="aaz164"]:checked').val();

		var bizinfo = new Object();


		bizinfo.serial_no = biz.split("|")[0];
		bizinfo.name = biz.split("|")[1];
		bizinfo.diagnose_date = biz.split("|")[2];
		bizinfo.fin_disease = biz.split("|")[3];

		bizinfo.disease = biz.split("|")[4];
		bizinfo.hospital_id = biz.split("|")[5];
		bizinfo.begin_date = biz.split("|")[6];
		bizinfo.idcard = biz.split("|")[7];

		bizinfo.biz_type = biz.split("|")[8];
		bizinfo.fin_date = biz.split("|")[9];
		bizinfo.treatment_type = biz.split("|")[10];
		bizinfo.corp_name = biz.split("|")[11];

		bizinfo.pers_type = biz.split("|")[12];
		bizinfo.sex = biz.split("|")[13];
		bizinfo.birthday = biz.split("|")[14];
		bizinfo.indi_id = biz.split("|")[15];

		bizinfo.telephone = biz.split("|")[16];
		bizinfo.serial_apply = biz.split("|")[17];
		bizinfo.icd = biz.split("|")[18];
		bizinfo.fee_batch = biz.split("|")[19];

		bizinfo.center_id = biz.split("|")[20];

		if (bizinfo) {
			setDialogReturn(bizinfo);
		}
		setTimeout("closeDialog();", 500);
	}
</script>
<head>
<powersi:head title="选择业务" />
</head>
<body>
	<powersi:form name="mainForm"
		action="GetPersonInfoAction!getPersonBusi.action"
		namespace="/diagnose">
		<powersi:groupbox title="业务列表">
			<display:table name="selectBizList" id="row" pagesize="10"
				requestURI="/diagnose/GetPersonInfoAction!getPersonBusi_remote.action">
				<display:column title="选择">
					<input type="radio" name="aaz164"
						value="${row.serial_no}|${row.name}|${row.diagnose_date}|${row.fin_disease}|
							${row.disease}|${row.hospital_id}|${row.begin_date}|${row.idcard}|
							${row.biz_type}|${row.fin_date}|${row.treatment_type}|${row.corp_name}|
							${row.pers_type}|${row.sex}|${row.birthday}|${row.indi_id}|
							${row.telephone}|${row.serial_apply}|${row.in_disease}|${row.fee_batch}|
							${row.center_id}"
						onclick="getBizInfo();" />
				</display:column>
				<display:column property="serial_no" title="就医登记号" />
				<display:column property="name" title="姓名" />
				<display:column property="fin_date" title="结算时间" />
				<display:column property="disease" title="疾病诊断" />
			</display:table>

		</powersi:groupbox>
		<div class="div_center" style="display: none">
			<powersi:button cssClass="button" key="button_ok"
				onclick="getBizInfo();" />
			<powersi:button cssClass="button" key="button_cancel"
				onclick="window.close();" />
		</div>
	</powersi:form>
</body>
</html>