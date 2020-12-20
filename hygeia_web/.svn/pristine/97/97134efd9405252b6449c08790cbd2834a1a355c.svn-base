<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<head>
<powersi:head title="个性化通用接口确定费用" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
</head>
<body>

	<powersi:form id="queryform" namespace="/comminter"
		action="DetermineFeeAction!queryAac002.action">

		<powersi:panelbox key="panel_result" title="就诊信息">
			<a>选中某行可以查看费用明细信息</a>
			<powersi:panelbox-toolbar>
				<powersi:button id="bt_save" label="保存" onclick="save()" />
				<powersi:button id="btClose" label="关闭"
					onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:datagrid id="grid" formId="queryform" enabledSort="false"
				showReload="false" checkbox="true" onSelectRow="SelectRow"
				usePager="false"  height="150px">
				<powersi:datagrid-column name="aac002" key="aac002" label="社会保障号码" 
					width="20%" />
				<powersi:datagrid-column name="bka446" key="bka446" label="住院号或门诊号"
					width="20%" />
				<powersi:datagrid-column name="aac003" key="aac003" label="姓名"
					width="15%" />
				<powersi:datagrid-column name="kcd1id" key="kcd1id" label="业务主键"
					hide="true" />
				<powersi:datagrid-column name="bka017" key="bka017" label="业务开始日期"
					hide="true" />
				<powersi:datagrid-column name="aaz217" key="aaz217" label="就医登记号"
					hide="true" />	
				<!-- 入院登记时间或门诊时间 -->
			</powersi:datagrid>
			<powersi:hidden id="aaz217" name="inHospitalDTO.aaz217" />
			<powersi:hidden id="aac002" name="inHospitalDTO.aac002" />
			<powersi:hidden id="kcd1id" name="inHospitalDTO.kcd1id" />
			<powersi:hidden id="bka017" name="inHospitalDTO.bka017" />
			
		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox key="费用明细信息">
		<powersi:datagrid id="gridFeeDetail" height="100%" showReload="false"
			autoWidth="true" alternatingRow="true" colDraggable="false"
			pageSize="30" totalRender="renderTotal">
			<powersi:datagrid-column name="ake005" display="编码" width="10%" />
			<powersi:datagrid-column name="ake006" display="名称" width="18%" />
			<powersi:datagrid-column name="bka053" display="厂家" width="16%" />
			<powersi:datagrid-column name="bka052" display="剂型" width="6%" />
			<powersi:datagrid-column name="bka054" display="规格" width="6%" />
			<powersi:datagrid-column name="bka056" display="单价" width="6%" />
			<powersi:datagrid-column name="bka057" display="数量" width="6%" />
			<powersi:datagrid-column name="bka058" display="金额" width="6%" />
			<powersi:datagrid-column name="bka051" display="日期" width="12%" format="{0,date,yyyyMMdd}" />
			<powersi:datagrid-column name="bka060" display="使用标志" width="6%" />
		</powersi:datagrid>
	</powersi:panelbox>

	<powersi:errors />

	<script type="text/javascript">
		function SelectRow(rowdata, rowid, rowobj) {
			//获取多选，全部勾选的数据
			var rows = grid.getSelectedRows();
			if (powersi.isempty(rows)) {
				popupAlert("请选择就诊信息！");
				return;
			}
			var data = powersi.tostring(rows);
			postJSON(
					"${rootPath}/comminter/DetermineFeeAction!queryFeeInfo.action",
					{
						"data" : data
					}, function(json) {

						if (!checkJSONResult(json)) {
							return;
						}
						gridFeeDetail.loadData(json.data);
					});
		}

		function save() {
			//获取多选，全部勾选的数据
			var rows = grid.getSelectedRows();
			if (powersi.isempty(rows)) {
				popupAlert("请至少选择一行就诊信息");
				return;
			}
			var data = powersi.tostring(rows);
			postJSON("${rootPath}/comminter/DetermineFeeAction!saveFee.action",
					{
						"data" : data
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
							var suss = json.data.suss;
							if (suss == 1) {
								popupSuccess("保存成功", 5000);
							} else {
								popupError(json.data.message);
							}
					});
		}

		function renderTotal(allData, currentData) {
			var money = 0;
			var price = 0;
			var usage = 0;
			var kinds = {};
			if (powersi.rows_length(allData['rows'])) {
				$.each(allData['rows'], function(n, row) {
					if (row['__status'] !== "delete") {
						price += parseFloat(row['bka056']);
						usage += parseFloat(row['bka057']);
						money += parseFloat(row['bka058']);
						kinds[row['ake005']] = '';
					}
				});
			}
			var a = [];
			a.push('<div class="divCenter textSuccess">');
			a.push('<span>');
			a.push('总的金额：<b>');
			a.push(money.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('&nbsp;&nbsp;');
			a.push('<span>');
			a.push('总的用量：<b>');
			a.push(usage.toFixed(2));
			a.push('</b>');
			a.push('</span>');
			a.push('&nbsp;&nbsp;');
			a.push('<span>');
			a.push('共 <b>');
			a.push(powersi.length(kinds));
			a.push('</b> 项费用');
			a.push('</span>');
			a.push('</div>');
			return a.join('');
		}
	</script>
</body>
</powersi:html>