<%@ tag pageEncoding="UTF-8" body-content="empty" small-icon=""
	display-name="医疗费用结算信息" description="医疗费用结算信息"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<style>
.showpay {
	height: 30px !important;
	font-size: 20px !important;
	text-align: right !important;
}
</style>
<powersi:editorlayout cols="12">
	<powersi:editorlayout-row>
		<powersi:textfield id="st_bcfy" key="本次发生费用" disabled="true"
			cssClass="showpay" value="0.00" />
		<powersi:textfield id="st_jjzf" key="基金支付" disabled="true"
			cssClass="showpay" cssStyle="color:red;" value="0.00" />
		<%-- <powersi:textfield id="st_self_com" key="个人自付金额" disabled="true"
			cssClass="showpay" value="0.00" />
		<powersi:textfield id="st_self_own" key="个人自费金额" disabled="true"
			cssClass="showpay" value="0.00" /> --%>
		<powersi:textfield id="em_grzhzf" key="个人账户支付" disabled="true"
			cssClass="showpay" cssStyle="color:red;" value="0.00" />
		<powersi:textfield id="st_xjzf" key="现金支付" disabled="true"
			cssClass="showpay" value="0.00" />
		<powersi:textfield id="em_jsq" key="今收取" cssClass="showpay"
			value="0.00" onkeydown="dealKeyDown(this)" name="em_jsq" />
		<powersi:textfield id="st_out" key="需找回" disabled="true"
			cssClass="showpay" value="0.00" />
	</powersi:editorlayout-row>
</powersi:editorlayout>