<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="医疗业务信息" description="医疗业务信息"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="s" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
	String aae140 = request.getParameter("aae140"); //险种
	String aka130 = request.getParameter("aka130"); //业务类型
	String bka003 = request.getParameter("bka003"); //待遇类型
	String aka130Filter = " instr('" + aka130 + "',data_value) > 0 ";
	String bka003Filter = " instr('" + bka003 + "',data_value) > 0 ";
%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#aae140list").val('<%=aae140%>');
		if('<%=aae140%>'.length <4) {
			$("#aae140").val('<%=aae140%>');
		}
		if('<%=aka130%>'.length <4) {
			$("#aka130").val('<%=aka130%>'); 
		}
		if('<%=bka003%>'.length <4) {
			$("#bka003").val('<%=bka003%>'); 
		}
		if ('<%=aka130%>' == '13') {
			$("#div_special").show();
		}else {
			$("#div_special").hide();
		}
	});

	function chooseDis(str) {
		popupDialog(
				{
					url : "${rootPath}/medicarebiz/MedOrgComQueryAction!queMedOrgInfo.action",
					onClosed : function() {
						var ret = this.returnValue;
						if (ret != null) {
							$("#bka026_name").val(ret.aka121);
							$("#bka026").val(ret.aka120);
							return $("#stext").focus();
						}
					}
				}, 600, 600);
	}

	function filterBka003() {
		if ($("#aae140").val() == "510") {

			if ($("#aka130").val() == "51") {
				$("#bka003").val("511");
			} else {
				$("#bka003").val("521");
			}
		}
	}

	function queryFreezeInfo() {
		if ($("#aac001").val() == "" || $("#aac001").val() == -1
				|| $("#aae140").val() == "") {
			alert("请先选择正确的人员！");
			return;
		}

	}

	function keyDown(para) {
		if (event.keyCode == '13') {
			try {
				var e = window.event || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					e.stopPropagation();
				}
				event.returnValue = false;
			} catch (ex) {
			}
			var filed_name = para.id;
			if ('bka017' == filed_name) {
				return $("#bka025").focus();
			}
			if ('bka025' == filed_name) {
				return $("#ttt").focus();
			}
			if ('ttt' == filed_name) {
				return $("#bka026_name").focus();
			}
			if ('bka026_name' == filed_name) {
				return chooseDis('bka026');
			}
		}
	}
</script>
<s:div id="div_special" style="display:none">
	<s:panelbox title="门诊慢性病申请信息">
		<s:editorlayout cols="6">
			<tr>
				<s:hidden id="aaz267_mm" name="treatmentBizInfoDTO.aaz267" />
				<s:hidden id="aka120_spe" />
				<s:textfield id="aka121_spe" key="aka121" readonly="true"
					disabled="true"></s:textfield>
				<s:textfield id="aae030_spe" label="申请开始日期" readonly="true"
					disabled="true"></s:textfield>
				<s:textfield id="aae031_spe" label="申请结束日期" readonly="true"
					disabled="true"></s:textfield>
			</tr>

		</s:editorlayout>
	</s:panelbox>
</s:div>
<s:div id="div_diagnose" style="display:none">
	<s:panelbox title="门诊选点信息" isCollapse="true">
		<s:editorlayout cols="6">
			<tr>
				<s:textfield id="aaz267" key="选点序列号" readonly="true" disabled="true" />
				<s:textfield id="aae030" key="生效日期" readonly="true" disabled="true" />
				<s:textfield id="aae031" key="失效日期" readonly="true" disabled="true" />
			</tr>
		</s:editorlayout>
	</s:panelbox>
</s:div>
<s:panelbox title="业务信息">
	<s:editorlayout cols="8">
		<tr>
			<s:textfield id="aac001" key="个人电脑号" name="diagnoseInfoDTO.aac001"
				readonly="true" disabled="true" />
			<s:textfield id="aac003" key="姓名" name="diagnoseInfoDTO.aac003"
				readonly="true" disabled="true" />
			<s:textfield id="aac002" key="证件号码" name="diagnoseInfoDTO.aac002"
				readonly="true" disabled="true" />
			<s:textfield id="aac006" key="出生日期" name="diagnoseInfoDTO.aac006"
				readonly="true" disabled="true" />
		</tr>
		<tr>
			<s:codeselect id="bka004" key="医疗人员类别" name="diagnoseInfoDTO.bka004"
				codeType="bka004" disabled="true" />
			<s:textfield id="bka008" key="单位名称" name="diagnoseInfoDTO.bka008"
				readonly="true" disabled="true" />
			<s:hidden id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001" />
		</tr>
		<tr>
			<s:hidden codeType="aka130" id="aka130" required="true"
				name="treatmentBizInfoDTO.aka130" key="aka130"
				codeFilter="<%=aka130Filter%>" codeSort="1"
				onchange="filterBka003()" />
			<s:textfield id="bka017" name="diagnoseInfoDTO.bka017" mask="date"
				key="门诊日期" required="true" onKeyDown="keyDown(this)" />
			<s:textfield id="bka025" key="挂号" name="diagnoseInfoDTO.bka025"
				readonly="false" required="true" onKeyDown="keyDown(this)" />
			<s:textfield id="ttt" key="处方号" name="diagnoseInfoDTO.aab001"
				readonly="false" onKeyDown="keyDown(this)" />
			<s:textfield id="aaz217" key="就医登记号" name="diagnoseInfoDTO.aaz217"
				readonly="true" />
		</tr>
		<tr>
			<s:textfield id="bka026_name" key="选择诊断" readonly="true"
				required="true" buttonText="查询" buttonId="disinse"
				onKeyDown="keyDown(this)" onbuttonclick="chooseDis('bka026')" />
			<s:hidden id="bka026" name="diagnoseInfoDTO.bka026" />
			<s:textfield id="bka006" key="待遇类型" name="diagnoseInfoDTO.bka006"
				value="110" readonly="true" />
		</tr>
		<tr>
			<s:textfield id="bka021" key="门诊病区" name="diagnoseInfoDTO.bka021"
				value="test" readonly="true" />
			<s:textfield id="bka019" key="门诊科室" name="diagnoseInfoDTO.bka019"
				value="test" readonly="true" />
			<s:codeselect codeType="bka003" id="bka003" label="门诊医生"
				name="diagnoseInfoDTO.bka003" key="bka003"
				codeFilter="<%=bka003Filter%>" codeSort="1"
				onchange="filterBka003()" />
			<td class="tdLabel">冻结信息</td>
			<td><s:button style="width:100%" value="冻结信息查询"
					onclick="queryFreezeInfo()" disabled="true"></s:button></td>
		</tr>
	</s:editorlayout>
</s:panelbox>