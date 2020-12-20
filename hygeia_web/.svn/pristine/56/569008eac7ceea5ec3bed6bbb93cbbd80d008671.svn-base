<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="医疗业务信息" description="医疗业务信息"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String aaa027 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAaa027();
%>
<script type="text/javascript">
	function keyDown(para) {
		if (event.keyCode == '13') {
			var filed_name = para.id;
			if ('bka017' == filed_name) {
				return $("#bka025").focus();
			}
			if ('bka025' == filed_name) {
				return $("#bka043").focus();
			}
			if ('bka043' == filed_name) {

			}
		}
	}
</script>

<s:panelbox title="业务信息">
	<s:editorlayout cols="8">
		<tr>
			<s:codeselect id="bka004_name" key="人员类别"
				name="diagnoseInfoDTO.bka004" codeType="bka004" disabled="true" />
			<s:hidden id="bka004" name="diagnoseInfoDTO.bka004" />
			<s:codeselect id="baa027_name" key="人员所属统筹区"
				name="diagnoseInfoDTO.baa027" codeType="aaa027" disabled="true" />
			<s:hidden id="baa027" key="当事人所在统筹区" name="diagnoseInfoDTO.baa027" />
			<s:textfield id="bka008" key="单位名称" name="diagnoseInfoDTO.bka008"
				readonly="true" />
			<s:textfield id="bka017" name="diagnoseInfoDTO.bka017" key="门诊日期"
				onKeyDown="keyDown(this)" readonly="true" />
			<s:hidden id="aac006" key="出生日期" name="diagnoseInfoDTO.aac006" />
			<s:hidden id="bka005" key="公务员级别" name="diagnoseInfoDTO.bka005" />
			<s:hidden id="aac004" key="性别" name="diagnoseInfoDTO.aac004" />
			<s:hidden id="kcd1id" key="主键" name="diagnoseInfoDTO.kcd1id" />
			<s:hidden id="aka130" key="业务类型" name="diagnoseInfoDTO.aka130"
				value="11" />
			<s:hidden id="aae140" key="险种" name="diagnoseInfoDTO.aae140"
				value="310" />
			<s:hidden id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001"
				readonly="true" />
			<s:hidden id="reducefeebatch" key="费用批次"
				name="diagnoseInfoDTO.reducefeebatch" readonly="true" />
			<s:hidden id="bka001" key="费用批次" name="diagnoseInfoDTO.bka001"
				readonly="true" />
			<s:hidden id="bka039" key="完成情况" name="diagnoseInfoDTO.bka039" />
			<s:hidden id="akb020" key="医院编号" name="diagnoseInfoDTO.akb020" />
		</tr>
		<tr>
			<s:hidden id="bka025" key="挂号" name="diagnoseInfoDTO.bka025"
				 />
			<s:hidden id="bka043" key="处方号" name="diagnoseInfoDTO.bka043"
				 />
			<s:textfield id="aaz217" key="就医登记号" name="diagnoseInfoDTO.aaz217"
				readonly="true" />
			<s:textfield id="aka121" key="疾病诊断" name="diagnoseInfoDTO.aka121"
				readonly="true" />
			<s:hidden id="bka026" name="diagnoseInfoDTO.bka026" />
			<s:codeselect id="bka006_name" key="待遇类型"
				name="diagnoseInfoDTO.bka006" codeType="bka006" disabled="true"
				codeLocal="<%=aaa027%>" />
			<s:hidden id="bka006" name="diagnoseInfoDTO.bka006" />
	</s:editorlayout>
</s:panelbox>