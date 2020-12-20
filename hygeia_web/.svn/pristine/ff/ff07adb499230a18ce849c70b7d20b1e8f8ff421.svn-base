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
		
		//门诊退费，要从最近结算的一次开始退费
		//TS19051400145 结算云职工普通门诊退费可跨单退费.修改：职工普通门诊可跨单退费  cj 20190522
		var akc194Str = biz.split("|")[9];
		var aka130Str = biz.split("|")[8];
		var bka006str = biz.split("|")[10];
		var aae140str = biz.split("|")[11];
		var bizList = document.getElementsByName("aaz164");
		for(var i=0;i<bizList.length;i++){
			var akc194 = bizList[i].value.split("|")[9];
			if(akc194>akc194Str && aka130Str!="10" && (bka006str!="110" && aae140str!="310")){
				popupAlert("请选择最近一次结算费用开始退费！","提示","info");
				return;
			}
		}
		
		var bizinfo = new Object();
		bizinfo.aaz217 = biz.split("|")[0];
		bizinfo.kc21id = biz.split("|")[4];
		bizinfo.akb020 = biz.split("|")[5];
		bizinfo.aka130 = biz.split("|")[8];
		bizinfo.bka008 = biz.split("|")[10];
		if (bizinfo) {
			setDialogReturn(bizinfo);
		}
	
		if("1"==(<%=flag%>)){
			var aaz217_1 = biz.split("|")[0];
			var aac002_1 = biz.split("|")[7];
			var kc21id_1 = biz.split("|")[4];
			var aae030_1 = biz.split("|")[6];
			var url ="<%=path%>/comminter/DetermineFeeAction!queryAac002.action?inHospitalDTO.aaz217="
				+ aaz217_1
				+ "&inHospitalDTO.aac002="
				+ aac002_1
				+ "&inHospitalDTO.kc21id="
				+ kc21id_1
				+ "&inHospitalDTO.aae030="
				+ aae030_1;
			openDialogWithParam(url, "", 600, 1000, function(ret){});
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
				requestURI="/diagnose/GetPersonInfoAction!getPersonBusi.action">
				<display:column title="选择">
					<input type="radio" name="aaz164"
						value="${row.aaz217}|${row.aac003}|${row.aae036}|${row.bkz101}|
						${row.kc21id}|${row.akb020}|${row.aae030}|${row.aac002}|${row.aka130}|
						${row.akc194}|${row.bka006}|${row.aae140}"
						onclick="getBizInfo();" />
				</display:column>
				<display:column property="aaz217" title="就医登记号" />
				<display:column property="aac003" title="姓名" />
				<display:column property="akc194" title="结算时间" />
				<display:column property="bkz101" title="疾病诊断" />
				<display:column property="aae019" title="总费用" />
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