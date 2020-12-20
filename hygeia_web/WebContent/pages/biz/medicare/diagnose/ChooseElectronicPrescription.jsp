<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<script type="text/javascript">
	window.onload = function() {
		$("#row").focus();
	}

	function getErxInfo() {
		if ($(':radio[name="erxlist"]:checked').val() == null) {
			popupAlert('请选择一行!');
			return;
		}
		var biz = $(':radio[name="erxlist"]:checked').val();
		var bizinfo = new Object();
		bizinfo.ake1id = biz.split("|")[0];
		bizinfo.bka006 = biz.split("|")[3];
		if (bizinfo) {
			setDialogReturn(bizinfo);
		}	
		setTimeout("closeDialog();", 500);
	}
</script>
<head>
<powersi:head title="选择电子处方" />
</head>
<body>
	<powersi:form name="mainForm"
		action="ElectronicPrescriptionAction!queryErx.action"
		namespace="/diagnose">
		<powersi:groupbox title="处方列表">
			<display:table name="selectErxList" id="row" pagesize="10"
				requestURI="/diagnose/ElectronicPrescriptionAction!queryErx.action">
				<display:column title="选择">
					<input type="radio" name="erxlist"
						value="${row.ake1id}|${row.aac001}|${row.aac003}|${row.bka006}|${row.bka026}"
						onclick="getErxInfo();" />
				</display:column>
				<display:column property="aac001" title="电脑号" />
				<display:column property="aac003" title="姓名" />
				<display:column property="ake1id" title="电子处方流水号" />
				<display:column property="bka006" title="待遇类别" />
				<display:column property="bka026" title="入院诊断" />
			</display:table>

		</powersi:groupbox>
		<div class="div_center" style="display: none">
			<powersi:button cssClass="button" key="button_ok"
				onclick="getErxInfo();" />
			<powersi:button cssClass="button" key="button_cancel"
				onclick="window.close();" />
		</div>

	</powersi:form>
</body>
</html>