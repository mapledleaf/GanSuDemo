<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="跨省外来就医月度结算" />
</head>
<body>
	<powersi:form id="declForm">
		<powersi:panelbox key="条件设置">
			<powersi:panelbox-toolbar>
				<powersi:button id="btGetQuery" label="查询" onclick="queryData()" />
				<powersi:button id="btGetDetail" label="提取" onclick="extract()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10">
				<tr>
					<powersi:textfield id="yearMonth" required="true" name="yearMonth"
						label="结算所属年月"
						validate="integer,max[209912],min[190001],maxSize[6],minSize[6]"
						onclick="WdatePicker({dateFmt:'yyyyMM'})" />
					<powersi:codeselect id="confirmflag" label="确认情况"
						list="#{'':'请选择...','1':'未确认','2':'已确认(用于申报)','-1':'已确认(用于取消)','-2':'已确认申报'}" />
					<td colspan="6"></td>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>

	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab1" target="paneltab1" label="清分汇总" />
		<powersi:tab id="tab2" target="paneltab2" label="清分明细" />
		<div id="paneltab1">
			<powersi:panelbox>
				<powersi:datagrid id="declTotalGrid" delayLoad="true"
					enabledEdit="true" clickToEdit="true" enabledSort="false"
					showReload="false">
					<powersi:datagrid-column name="bae008" display="确认业务流水号"
						hide="true" />
					<powersi:datagrid-column name="akb020" display="医疗机构编号" hide="true" />
					<powersi:datagrid-column name="otransid" display="原交易流水号"
						hide="true" />
					<powersi:datagrid-column name="akb021" display="医疗机构名称" width="20%" />
					<powersi:datagrid-column name="bae007" display="结算清分数据业务流水号"
						width="18%" />
					<powersi:datagrid-column name="checkflag" display="是否完成确认"
						width="10%" hide="true" />
					<powersi:datagrid-column name="yzz062" display="结算清分数据所属月份"
						width="17%" />
					<powersi:datagrid-column name="yzz061" display="结算月份" width="10%" />
					<powersi:datagrid-column name="akc264" display="总费用" width="10%" />
					<powersi:datagrid-column name="ake149" display="经办机构支付总额"
						width="15%" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
		<div id="paneltab2">
			<powersi:panelbox>
				<powersi:panelbox-toolbar>
					<powersi:button id="btnEnter" label="确认" onclick="doEnter(1)" />
					<powersi:button id="btnEnter" label="取消确认" onclick="doEnter(-1)" />
					<powersi:button id="btnBack" label="确认申报" onclick="doDeclare()" />
					<powersi:button id="btnBack" label="取消申报" onclick="backDeclare()" />
				</powersi:panelbox-toolbar>
				<powersi:datagrid id="declDetailGrid" checkbox="true"
					usePager="false" delayLoad="true" enabledEdit="true"
					clickToEdit="true" enabledSort="false" showReload="false">
					<powersi:datagrid-column name="akb026" display="医疗机构编号" width="15%" />
					<powersi:datagrid-column name="showflag" display="状态" width="10%"
						render="rendershowflag" />
					<powersi:datagrid-column name="aaz216" display="结算流水号" width="15%" />
					<%-- <powersi:datagrid-column name="aac003" 	 display="姓名" 				width="10%" /> --%>
					<powersi:datagrid-column name="ykc700" display="就诊登记号" width="15%" />
					<powersi:datagrid-column name="aac002" display="社会保障号码" width="15%" />
					<powersi:datagrid-column name="akc194" display="就诊结算时间" width="10%" />
					<powersi:datagrid-column name="yzz062" display="结算清分数据所属月份"
						width="15%" />
					<powersi:datagrid-column name="yzz061" display="结算月份" width="10%" />
					<powersi:datagrid-column name="ake105" display="全额垫付标志" width="10%" />
					<powersi:datagrid-column name="akc264" display="总费用" width="10%" />
					<powersi:datagrid-column name="ake149" display="经办机构支付总额"
						width="15%" />
					<powersi:datagrid-column name="bae007" display="提取业务号" width="15%" />
					<powersi:datagrid-column name="bae008" display="确认业务流水号"
						width="15%" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</powersi:tabbedpanel>
	<powersi:errors />
</body>
</powersi:html>

<script type="text/javascript">
	//提取
	function extract() {
		if (!checkFormValidtion()) {
			return;
		}
		var yearMonth = $("#yearMonth").val();
		postJSON("${rootPath}/medicare/OutDeclAction!getDeclDetail.action", {
			"yearMonth" : yearMonth
		}, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			popupInfo(json.data);
			queryData();
		});
	}

	/*查询*/
	function queryData() {
		if (!checkFormValidtion()) {
			return;
		}
		var confirmflag = $("#confirmflag").val();
		var yearMonth = $("#yearMonth").val();
		postJSON("${rootPath}/medicare/OutDeclAction!queryDeclDetail.action", {
			"yearMonth" : yearMonth,
			"confirmflag" : confirmflag
		}, function(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			declTotalGrid.loadData(json.data.lsttotal);
			declDetailGrid.loadData(json.data.lstbiz);
		});
	}

	/*明细确认、不确认*/
	function doEnter(flag) {
		var data = declDetailGrid.getSelectedRows();
		var yearMonth = $("#yearMonth").val();
		if (yearMonth == null || yearMonth == "") {
			popupAlert("请先选择结算所属年月！");
			return;
		}
		if (powersi.isempty(data)) {
			if (flag == 1) {
				popupAlert("请选择需确认的记录！");
			}
			if (flag == -1) {
				popupAlert("请选择需取消确认的记录！");
			}
			return;
		}
		data = powersi.tostring(data);
		postJSON("${rootPath}/medicare/OutDeclAction!enterDeclDetail.action", {
			"data" : data,
			"yearMonth" : yearMonth,
			"confirmflag" : flag
		}, function(json) {
			if (!checkResult(json)) {
				return;
			}
			popupInfo(json.data);
			queryData();
		});
	}

	//申报
	function doDeclare() {
		var allData = declDetailGrid.getAllData();
		var data = [];
		var count = 0;
		for ( var i in allData) {
			if (allData[i]['showflag'] == '11') {
				data[count] = allData[i];
				count++;
			}
		}
		if (powersi.isempty(data)) {
			popupAlert("当前无可申报数据！");
			return;
		}
		data = powersi.tostring(data);
		//START: 2018-01-05 lhy 缺陷：6137 由于申报上个月的，参数传的是当前月，导致申报出错
		var yearMonth = $("#yearMonth").val();
		if (yearMonth == null || yearMonth == "") {
			popupAlert("请先选择结算所属年月！");
			return;
		}
		//end 
		postJSON("${rootPath}/medicare/OutDeclAction!doDeclare.action", {
			"yearMonth" : yearMonth,
			"data" : data
		}, function(json) {
			if (!checkResult(json)) {
				return;
			}
			popupInfo(json.data);
			queryData();
		});
	}

	//取消申报
	function backDeclare() {
		var allData = declDetailGrid.getAllData();
		var totalData = declTotalGrid.getAllData();
		var yearMonth = $("#yearMonth").val();
		var data = [];
		var otransid = "";
		var count = 0;
		if (yearMonth == null || yearMonth == "") {
			popupAlert("请先选择结算所属年月！");
			return;
		}
		for ( var i in allData) {
			if (allData[i]['showflag'] == '21') {
				data[count] = allData[i];
				count++;
			}
		}
		if (powersi.isempty(data)) {
			popupAlert("当前无可取消申报的数据！");
			return;
		}
		for ( var i in totalData) {
			if (totalData[i]['otransid'] != ''
					&& totalData[i]['otransid'] != null) {
				otransid = totalData[i]['otransid'];
			}
		}
		if (otransid == "") {
			popupAlert("原交易流水号不能为空！");
			return;
		}
		data = powersi.tostring(data);
		postJSON("${rootPath}/medicare/OutDeclAction!backDeclare.action", {
			"data" : data,
			"yearMonth" : yearMonth,
			"otransid" : otransid
		}, function(json) {
			if (!checkResult(json)) {
				return;
			}
			popupInfo(json.data);
			queryData(0);
		});
	}

	function rendershowflag(rowdata, index, value) {
		if (value === '00') {
			return "提取";
		} else if (value === '10') {
			return "不确认";
		} else if (value === '11') {
			return "确认";
		} else if (value === '21') {
			return "确认申报";
		} else {
			return " ";
		}
	}
</script>
