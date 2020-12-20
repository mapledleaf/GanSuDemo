<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<powersi:html>
<head>
<powersi:head title="基金状态查询" target="_self" />
</head>
<body>
	<powersi:form id="fundList"
		action="CommonManagerAction!queryPersonFund.action" namespace="/comm">
		<table class="its">
			<powersi:editorlayout cols="12%,16%,5%,16%,11%,20%,10%,10%">
				<powersi:hidden id="aac001" name="inHospitalDTO.aac001" />
				<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
				<powersi:hidden id="caa027" name="inHospitalDTO.caa027" />
				<powersi:hidden id="aka130" name="inHospitalDTO.aka130" />
				
				<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
					label="查询日期" mask="date" required="true" readonly="false" />
				<powersi:textfield id="aae031" name="inHospitalDTO.aae031" label="至"
					mask="date" required="true" readonly="false" />
				<td class="tdLabel">
					<label for=fundSelect class="label">基金选择</label>
				</td>
				<td class="tdInput">
					<s:select id="fundSelect"
						name="inHospitalDTO.aaa157" list="fundMap" listKey="key"
						listValue="value" title="" headerKey="" headerValue="---请选择---">
					</s:select>
				</td>
				<td>
					<powersi:submit id="queryBtn" label="查询"
					class="button" key="button_query"/>
				</td>
				<td>
					<powersi:button label="关 闭"
					class="button" key="button_close" onclick="windowclose()"/>
				</td>
			</powersi:editorlayout>
		</table>
		<table class="its">
			<thead>
				<tr>
					<th>基金名称</th>
					<th>期间</th>
					<th>基金状态</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.fundStatusMap" var="fund">
					<s:set var="aad006" value="key"></s:set>
					<s:set var="aae003" value="value"></s:set>
					<s:iterator value="#aae003" var="month" status="stat">
						<tr class="odd">
							<s:if test="#stat.index == 0">
								<td rowspan="<s:property value='#aae003.size()'/>">
								<s:property value='#aad006' /></td>
							</s:if>
							<td><s:property value='key' /></td>
							<td><s:property value='value' /></td>
						</tr>
					</s:iterator>
				</s:iterator>
			</tbody>
		</table>
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		function windowclose() {
			setTimeout("closeDialog();", 300);
		}
	</script>
</body>
</powersi:html>