<%@tag import="java.util.Date"%>
<%@tag import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@tag import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="医疗业务信息" description="医疗业务信息"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
    String bka006Filter = " data_value like '100%'";
    String aaa027 = BizHelper.getAaa027();
%>
<style>
#imgdiv {
	height: 100px !important;
	width: 140px !important;
	border: 1px solid #d5e4f1;
	max-width: 100%; 
	max-height: 100%;
	background: url(${rootPath}/login/images/default.png) repeat 0px 0px;
	background-size:100% 100%;
}
</style>
<script type="text/javascript">
$(document).ready(
	function() {
		$("#aae030").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
		$("#ake007").val('<%=DateFunc.dateToString(new Date(), "yyyyMMdd")%>');
		$("#bka006").val("100");
	});
	
/*
 * 疾病诊断
 */
function chooseDis(str) {
	popupDialog(
			{
				url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
				onClosed : function() {
					var retValue = this.returnValue;
					if (retValue) {
						$("#bkz101").val(retValue.aka121);
						$("#akc193").val(retValue.aka120);
						return $("#bka006").focus();
					} else {
						$("#bkz101").val("");
						$("#akc193").val("");
						return $("#bkz101").focus();
					}
				}
			}, 500, 600);
}

/*
 * 文本框键入回车时处理事件
 */
function keyDown(para) {
	if (event.keyCode == '13') {
		var filed_name = para.id;
		if ('aae030' == filed_name) {
			return $("#bka025").focus();
		}
		if ('bka025' == filed_name) {
			return $("#bka043").focus();
		}
		if ('bka043' == filed_name) {
			return $("#bka026_name").focus();
		}
		if ('bka026_name' == filed_name) {
			return chooseDis('bka026');
		}
	}
}
</script>
<powersi:editorlayout cols="15%,9%,13%,9%,15%,9%,15%,10%,15%">
	<tr>
		<td rowspan="4" colspan="1" align="center" style="border: 1px solid #d5e4f1;" >
			<div id="imgdiv" >
				<img id="dlrimg" height="100px" width="140px" style="max-height : 100%; max-width: 100%;">
				<powersi:hidden id="kc90id" name="diagnoseInfoDTO.kc90id" />
			</div>
		</td>
		<powersi:textfield id="bacu18" name="diagnoseInfoDTO.bacu18" key="个人账户余额"
			readonly="true" cssStyle="color:red;" />
		<powersi:codeselect id="bka035" name="diagnoseInfoDTO.bka035_name" key="人员类别"
			headerKey="0" headerValue="" codeType="bka035" disabled="true"/>
	    <powersi:hidden id="bka035" name="diagnoseInfoDTO.bka035" />
		<powersi:codeselect id="aac004" name="diagnoseInfoDTO.aac004_name" key="性别"
			headerKey="0" headerValue="" codeType="aac004" disabled="true" />
		<powersi:hidden id="aac004" name="diagnoseInfoDTO.aac004" />
		<powersi:textfield id="aac006" name="diagnoseInfoDTO.aac006" key="出生日期"
			readonly="true" />
	</tr>
	<tr>
		<powersi:textfield id="baa027_name" name="diagnoseInfoDTO.baa027_name" key="所属中心"
			readonly="true"/>
		<powersi:hidden id="baa027" name="diagnoseInfoDTO.baa027" />
		<powersi:codeselect id="bac001" name="diagnoseInfoDTO.bac001" key="公务员级别"
			headerKey="0" headerValue="" codeType="bac001" disabled="true" />
		<powersi:hidden id="bac001" name="diagnoseInfoDTO.bac001" />
		<powersi:textfield id="bka888" key="基金冻结情况" name="diagnoseInfoDTO.bka888"
			readonly="true"/>
		<powersi:textfield id="aac001" name="diagnoseInfoDTO.aac001" key="电脑号" 
			readonly="true" />
	</tr>
	<tr>
		<powersi:codeselect id="bka006" name="diagnoseInfoDTO.bka006" key="待遇类型" 
			codeType="bka006" codeFilter="<%=bka006Filter%>" codeLocal="<%=aaa027%>" 
			disabled="true" />
		<powersi:textfield id="aae030" name="diagnoseInfoDTO.aae030" key="购药日期"
			onKeyDown="keyDown(this)"  readonly="true"/>
		<powersi:textfield id="bkz101" name="diagnoseInfoDTO.bkz101" key="疾病诊断"  
			buttonText="..." buttonId="disinse" onbuttonclick="chooseDis('bka026')"
			onKeyDown="keyDown(this)" readonly="true" required="true" value="普通疾病"/>
		<powersi:textfield id="aae005" name="diagnoseInfoDTO.aae005" key="联系电话"
			readonly="true" />
	</tr>
	<tr>
		<powersi:textfield id="aaz217" name="diagnoseInfoDTO.aaz217" key="就医登记号"
			readonly="true" />
	</tr>
	<powersi:hidden id="bka006_reg" name="diagnoseInfoDTO.bka006" />
	<powersi:hidden id="aae030_reg" name="diagnoseInfoDTO.aae030" />
	<powersi:hidden id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001" />
	<powersi:hidden id="aab019" name="diagnoseInfoDTO.aab019" />
	<powersi:hidden id="aaz267" key="选点序列号" name="diagnoseInfoDTO.aaz267" />
	<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130"
		key="aka130" value="10" />
	<powersi:hidden id="aae140" name="diagnoseInfoDTO.aae140" key="险种" />
	<powersi:hidden id="bka001" key="费用批次" name="diagnoseInfoDTO.bka001" />
	<powersi:hidden id="bka039" key="完成情况" name="diagnoseInfoDTO.bka039" />
	<powersi:hidden id="akc193" name="diagnoseInfoDTO.akc193" value="00000" />
	<powersi:hidden id="bka022" key="病区名称" name="diagnoseInfoDTO.bka022" />
	<powersi:hidden id="bka020" key="科室名称" name="diagnoseInfoDTO.bka020" />
	<powersi:hidden id="bka016" name="diagnoseInfoDTO.bka016" />
	<powersi:hidden id="bka011" name="diagnoseInfoDTO.bka011" />
	<powersi:hidden id="bka012" name="diagnoseInfoDTO.bka012" />
	<powersi:hidden id="kc21id" key="主键" name="diagnoseInfoDTO.kc21id" />
</powersi:editorlayout>
