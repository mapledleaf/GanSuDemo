<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("aaa027", BizHelper.getAaa027());
	//TS19102500058 - 【需求开发】结算云目录匹配优化
	//modify by 赵银溪 屏蔽部分删除中心功能
%>
<powersi:html>
<head>
<powersi:head title="匹配信息查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="HospCataAction!queryMatchCata.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询" buttonIcon="icon-search"/>
				<powersi:button id="btDel"    label="删 除" buttonIcon="icon-minus-sign" onclick="doDel()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<powersi:editorlayout-row>
					<powersi:codeselect id="caa027" name="hospCataDto.caa027" label="中心系统" headerKey="00" codeType="caa027" />
					<powersi:textfield  id="ake005" name="hospCataDto.ake005" label="医院目录编码" />
					<powersi:textfield  id="ake006" name="hospCataDto.ake006" label="医院目录名称" />
					<powersi:textfield  id="bkc144" name="hospCataDto.bkc144" label="中心目录编码" />
					<powersi:textfield  id="bkc143" name="hospCataDto.bkc143" label="中心目录名称" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="verify_type" name="hospCataDto.verify_type" label="查询平台"
						list="#{'0':'结算云平台','1':'核心业务平台' }" />
					<powersi:codeselect id="ake003" name="hospCataDto.ake003" label="目录类别" 
						codeType="ake003" headerValue="请选择.." headerKey="1,2,3" />
					<powersi:codeselect id="aae016" name="hospCataDto.aae016" label="审核标志" 
						codeType="aae016" headerValue="请选择.." headerKey="0,1,2,3"/>
					<powersi:codeselect id="aae100" name="hospCataDto.aae100" label="有效标志" 
						codeType="valid_flag" value="1" />
					<powersi:hidden id="aaa027" name="hospCataDto.aaa027" value="${aaa027}"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="目录列表">
<powersi:datagrid id="matchList" formId="queryForm" delayLoad="false" enabledSort="false" checkbox="true"
			showExportExcel="true" showExportExcel2007="true" showExportPdf="true" 
			exportFileName="'医院目录信息'">
<%-- 			<powersi:datagrid id="matchList" title="提示：勾选后点击删除按钮删除" url="${rootPath }/medicare/HospCataAction!queryMatchCata.action" --%>
<%-- 			showReload="false" delayLoad="true" enabledExportExcel="true" checkbox="true"> --%>
<%-- 				<powersi:toolbar> --%>
<%-- 					<powersi:toolbar-item position="right" id="matchListCondition" /> --%>
<%-- 				</powersi:toolbar> --%>
			<powersi:datagrid-column name="aaa027" label="定点中心编码"  />
			<powersi:datagrid-column name="aaa027" label="定点中心名称" code="aaa027"/>
			<powersi:datagrid-column name="ake005" label="医院目录编码" />
			<powersi:datagrid-column name="ake006" label="医院目录名称" />
			<powersi:datagrid-column name="bkc144" label="中心目录编码" />
			<powersi:datagrid-column name="bkc143" label="中心目录名称" />
			<powersi:datagrid-column name="aka057" label="职工支付比例" width="80" />
			<powersi:datagrid-column name="bkc106" label="居民支付比例" width="80" />
			<powersi:datagrid-column name="aae016" label="审核标志"  width="70" />
			<powersi:datagrid-column name="aae030" label="生效时间"  width="80" />
			<powersi:datagrid-column name="aae031" label="失效时间"  width="80" />
			<powersi:datagrid-column name="ake003" label="目录类别"  width="60" code="ake003List"/>
			<powersi:datagrid-column name="aae036" label="匹配操作时间" width="130"/>
			<powersi:datagrid-column name="aae406" label="匹配操作人" />
			<powersi:datagrid-column name="aae407" label="匹配操作人工号" width="80" />
			<powersi:datagrid-column name="aae011" label="修订人工号" />
			<powersi:datagrid-column name="bae100" label="修订人" />
			<powersi:datagrid-column name="bkc138" label="医院目录剂型"  />
			<powersi:datagrid-column name="bkc139" label="医院目录规格型号" />
			<powersi:datagrid-column name="bkc140" label="医院目录单价" />
			<powersi:datagrid-column name="bkc141" label="医院目录厂家" />
			<powersi:datagrid-column name="aka020" label="拼音码" />
			<powersi:datagrid-column name="aka021" label="五笔码" />
			<powersi:datagrid-column name="bkm017" label="药监本位码" />
			<powersi:datagrid-column name="aaz107" label="定点医疗机构编号" />
			<powersi:datagrid-column name="akb020" label="医院编码" hide="true" />
			<powersi:datagrid-column name="ka40id" label="医院目录编号" hide="true" />
			<powersi:datagrid-column name="bkc109" label="中心目录编号" />
			<powersi:datagrid-column name="bkc194" label="匹配目录ID" width="135" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
<script type="text/javascript">
function doDel() {
	//TS20010800160 - 【需求开发】结算云药品目录匹配界面优化   需先选中才能删除
	var rows = matchList.getSelectedRows();
	if (powersi.isempty(rows)) {
		popupAlert("请选择需要删除的目录", "提示", "error");
		return;
	}
	//TS20010800160 - 【需求开发】结算云药品目录匹配界面优化   公司服务器环境对POST参数大小做了限制
	if(rows.length>20){
		popupAlert("一次最多只能删除20条！", "提示", "info");
		return;
	}
	popupConfirm("确定删除此条匹配信息吗？", "提示", function(isOk) {
		if (isOk) {
//			var rows = matchList.getSelectedRow();
// 			if ("3" == row.aae016) {
// 				popupAlert("待中心审核的目录不能删除", "提示", "error");
// 				return;
// 			}
			
			//TS20010800160 - 【需求开发】结算云药品目录匹配界面优化   可删除多个匹配信息
			var data = powersi.tostring(rows);
			postJSON("${rootPath}/medicare/HospCataAction!delMatchCata.action", {
				"data" : data,
				"caa027" : $("#caa027").val()
			}, function(ret) {
				if (ret.errortype > 0) {
					popupAlert(ret.message, "提示", "error");
				} else {
					popupAlert("删除成功", "提示", "info");
					$("#btSubmit").click();
				}
			});
		}
	});
}
</script>
</powersi:html>