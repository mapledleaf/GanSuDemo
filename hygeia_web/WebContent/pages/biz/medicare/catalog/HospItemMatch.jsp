<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<powersi:head title="医院项目目录匹配" />
<powersi:codetable id="ake003List" codeType="ake003" codeFilter="data_value in(2,3)" />
<powersi:codetable id="aae016List" codeType="aae016_kae8" />
<body>
	<powersi:editorlayout cols="8">
		<powersi:editorlayout-row>
			<powersi:codeselect required="true" id="caa027" label="中心系统" codeType="caa027" headerKey="00" />
			<powersi:codeselect required="true" id="aaa027" label="定点中心" codeType="designated_center" headerKey="00" disabled="true" />
			<powersi:editorlayout-button colspan="8">
				<powersi:button id="btnOkMatch" label="确认匹配" />
				<powersi:button id="btnSaveMatch" label="保存匹配" />
				<powersi:button id="btnAutoMatch" label="自动匹配" />
				<powersi:button id="btDel"    label="删 除" buttonIcon="icon-minus-sign" onclick="doDel()" />
			</powersi:editorlayout-button>
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab2" target="divTab2" label="匹配操作" />
		<powersi:tab id="tab1" target="divTab1" label="匹配信息" />
		<div id="divTab1">
			<powersi:datagrid id="matchList" title="提示：勾选后点击删除按钮删除" url="${rootPath }/medicare/HospCataAction!queryMatchCata.action"
				showReload="false" delayLoad="true" enabledExportExcel="true" checkbox="true" pageSize="20"> <!-- TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页默认值问题修复 -->
				<powersi:toolbar>
					<powersi:toolbar-item position="right" id="matchListCondition" />
				</powersi:toolbar>
				<powersi:datagrid-column name="aaa027" label="定点中心编码" width="75" />
				<powersi:datagrid-column name="aaa027" label="定点中心名称" render="aaa027Render" />
				<powersi:datagrid-column name="ake005" label="医院目录编码" />
				<powersi:datagrid-column name="ake006" label="医院目录名称" />
				<powersi:datagrid-column name="bkc144" label="中心目录编码" />
				<powersi:datagrid-column name="bkc143" label="中心目录名称" />
				<powersi:datagrid-column name="aka057" label="职工支付比例" width="80" />
				<powersi:datagrid-column name="bkc106" label="居民支付比例" width="80" />
				<powersi:datagrid-column name="aae016" label="审核标志"  width="70" />
				<powersi:datagrid-column name="aae030" label="生效时间"  width="80" />
				<powersi:datagrid-column name="aae031" label="失效时间"  width="80" />
				<powersi:datagrid-column name="ake003" label="目录类别"  width="60" code="ake003"/>
				<powersi:datagrid-column name="aae036" label="匹配操作时间" width="130" render="renderAae036"/>
				<powersi:datagrid-column name="aae406" label="匹配操作人" />
				<powersi:datagrid-column name="aae407" label="匹配操作人工号" width="80" />
				<powersi:datagrid-column name="aae011" label="修订人工号" />
				<powersi:datagrid-column name="bae100" label="修订人" />
				<powersi:datagrid-column name="bkc139" label="医院目录规格型号" />
				<powersi:datagrid-column name="bkc140" label="医院目录单价" />
				<powersi:datagrid-column name="bkc141" label="医院目录厂家" />
				<powersi:datagrid-column name="aka020" label="拼音码" />
				<powersi:datagrid-column name="aka021" label="五笔码" />
				<powersi:datagrid-column name="aaz107" label="定点医疗机构编号" />
				<powersi:datagrid-column name="akb020" label="医院编码" hide="true" />
				<powersi:datagrid-column name="ka40id" label="医院目录编号" hide="true" />
				<powersi:datagrid-column name="bkc109" label="中心目录编号" />
				<powersi:datagrid-column name="bkc194" label="匹配目录ID" width="135" />
			</powersi:datagrid>
		</div>
		<div id="divTab2" class="row">
			<div class="col-6">
				<powersi:datagrid id="hospCata" title="医院待匹配项目目录" url="${rootPath }/medicare/HospCataAction!queryHospCata.action"
					onDblClickRow="onDblClickRow_hospCata" delayLoad="true" pageSize="20"> <!-- TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页默认值问题修复 -->
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="hospCataCondition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="ake005" label="目录编码" />
					<powersi:datagrid-column name="ake006" label="目录名称" />
					<powersi:datagrid-column name="ake003" label="目录类别" code="ake003"/>
					<powersi:datagrid-column name="bkc140" label="单价" />
					<powersi:datagrid-column name="bkc139" label="规格型号" />
					<powersi:datagrid-column name="bkc141" label="生产单位" />
					<powersi:datagrid-column name="bkm007" label="批准文号" />
				</powersi:datagrid>
			</div>
			<div class="col-6">
				<powersi:datagrid id="centerCata" title="中心可匹配项目目录" url="${rootPath }/medicare/HospCataAction!queryCenterCata.action"
					onDblClickRow="onDblClickRow_centerCata" onSuccess="onSuccess_centerCata" delayLoad="true" pageSize="20"> <!-- TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页默认值问题修复 -->
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="centerCataCondition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="bkc109" label="目录ID" hide="true" />
					<powersi:datagrid-column name="bkc144" label="目录编码" />
					<powersi:datagrid-column name="bkc143" label="目录名称" />
					<powersi:datagrid-column name="ake003" label="目录类别" code="ake003"/>
					<powersi:datagrid-column name="aka065" label="收费等级"  />
					<powersi:datagrid-column name="aka057" label="职工支付比例" />
					<powersi:datagrid-column name="bkc106" label="居民支付比例" />
					<powersi:datagrid-column name="bkc139" label="规格型号" />
					<powersi:datagrid-column name="bkc141" label="生产单位" />
					<powersi:datagrid-column name="bkm007" label="批准文号" />
					<powersi:datagrid-column name="bkm029" label="医保识别码" />
					<powersi:datagrid-column name="aaa027" label="定点中心 " render="aaa027Render" />
				</powersi:datagrid>
			</div>
		</div>
	</powersi:tabbedpanel>
</body>
<script type="text/javascript">
	g_designated_center = [];
	$(function() {
		postJSON("${rootPath}/medicare/HospCataAction!queryDesignatedCenter.action", {
			caa027 : $("#caa027").val()
		}, function(ret) {
			if (ret.errortype > 0) {
				popupAlert(ret.message, "提示", "error");
			} else if (ret.data.length == 0) {
				popupAlert("此医院没有定点中心", "提示", "error");
			} else {
				g_designated_center = ret.data;
				$.each(ret.data, function(i, n) {
					$("#aaa027").append("<option value='"+i+"'>" + n.aaa129 + "</option>");
				});
				$("#hospCata .l-button:contains(查询)").click();
				$("#caa027,#aaa027").change(function() {
					centerCata.reset();
					$("#hospCata .l-button:contains(查询)").click();
					$("#matchList .l-button:contains(查询)").click();
				});
			}
		});

		ake003List.unshift({
			"id" : "2,3",
			"text" : "全部"
		});
		$.each([ "hospCata", "centerCata", "matchList" ], function(i, n) {
			var fields = [ {
				id : n + '_ake003',
				name : 'hospCataDto.ake003',
				label : "目录类别",
				type : "combobox",
				width : "80px;",
				newline : false,
				options : {
					data : ake003List,
					initValue : "2,3",
					cancelable : false
				}
			}, {
				id : n + '_ake005',
				name : 'hospCataDto.ake005',
				label : "目录编码",
				width : "55px;",
				type : "text",
				newline : false
			},{
				id : n + '_ake006',
				name : 'hospCataDto.ake006',
				label : "目录名称",
				width : "55px;",
				type : "text",
				newline : false
			} ];
			if ("matchList" == n) {
				fields.unshift({
					id : n + '_aae016',
					name : 'hospCataDto.aae016',
					label : "审核状态",
					type : "combobox",
					newline : false,
					options : {
						data : aae016List,
						initValue : "0",
						cancelable : false,
						onSelected : function(value, text) {
							$("#matchList .l-button:contains(查询)").click();
						}
					}
				});
			}
			var datagrid = window[n];
			var condition = $.extend({}, $.ligerDefaults.Form.custom, {
				inputWidth : "matchList" == n ? 170 : screen.width > 1900 ? 130 : 70,
				showSearch : true,
				showClose : false,
				fields : fields,
				search : function(d) {
					var parm = {
							"hospCataDto.aae016_kae8" : "-1",
							"hospCataDto.aae100" : "1",
							"hospCataDto.validityFlag" : "1",
							"hospCataDto.caa027" : $("#caa027").val(),
							"hospCataDto.aaa027" : g_designated_center[$("#aaa027").val()].aaa027
					};
					$.each(d, function(i, n) {
						parm[n.field] = n.value;
						if ('hospCataDto.aae016' == n.field && "2" == n.value)
							parm['hospCataDto.aae100'] = '';
					});
					//parm['pagesize'] = $("#" + datagrid.id + " select[name=rp]").val();  //TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页失效问题修复
					datagrid.setParms(parm);
					datagrid.loadServerData(parm);
				}
			});
			datagrid.bindCondition(condition, n + "Condition");
		});

		var btnOkMatch = $("#btnOkMatch"); //确认匹配
		btnOkMatch.click(function() {
			var rowHosp = hospCata.getSelectedRow();
			if (powersi.isempty(rowHosp)) {
				popupAlert("请选择需要匹配的医院目录", "提示", "error");
				return;
			}
			var rowCenter = centerCata.getSelectedRow();
			if (powersi.isempty(rowCenter)) {
				popupAlert("请选择需要匹配的中心目录", "提示", "error");
				return;
			}
			if (rowHosp.ake003 != rowCenter.ake003) {
				popupAlert("匹配的目录类别不一致", "提示", "error");
				return;
			}
			
			var designated_center = g_designated_center[$("#aaa027").val()];
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/catalog/MatchDate.jsp",
				onClosed : function() {
					var ret = this.returnValue;
					var d = powersi.tojson(ret);
					if (!d)
						return;
					rowHosp.aae030 = d.aae030 ? d.aae030 : "20160101";
					rowHosp.aae031 = d.aae031 ? d.aae031 : "20991231";
					postJSON("${rootPath}/medicare/HospCataAction!saveMatchCata.action", {
						"hospCataDto.aaa027" : designated_center.aaa027,
						"hospCataDto.aaz107" : designated_center.aaz107,
						"hospCataDto.bkc109" : rowCenter.bkc109,
						"hospCataDto.ka40id" : rowHosp.ka40id,
						"hospCataDto.aae030" : rowHosp.aae030,
						"hospCataDto.aae031" : rowHosp.aae031,
						"hospCataDto.caa027" : $("#caa027").val()
					}, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							centerCata.reset();
							$("#hospCata .l-button:contains(查询)").click();
							$("#matchList .l-button:contains(查询)").click();
						}
					});
				}
			}, 150, 300);
		});

		$("#btnSaveMatch").click(function() { //保存匹配
			popupConfirm("将未审核的匹配目录提交到中心审核？\n<b>注意：提交后中心未审核之前，目录不允许做删除、修改操作！</b>", "提示", function(isOk) {
				if (isOk) {
					postJSON("${rootPath}/medicare/HospCataAction!submitMatchCata.action", {
						"hospCataDto.ake003" : "2,3",
						"hospCataDto.caa027" : $("#caa027").val(),
						"hospCataDto.aaa027" : g_designated_center[$("#aaa027").val()].aaa027
					}, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							popupAlert("共提交了" + ret.data + "条目录待中心审批", "提示", "info");
							$("#matchList .l-button:contains(查询)").click();
						}
					});
				}
			});
		});

		$("#btnAutoMatch").click(function() { //自动匹配
			popupConfirm("确定自动生成匹配目录吗？\n匹配规则：相同的目录类别，根据项目名称模糊匹配\n<b>注意：如果目录较多，匹配时间可能会稍长，请耐心等待！</b>", "提示", function(isOk) {
				if (isOk) {
					var designated_center = g_designated_center[$("#aaa027").val()];
					postJSON("${rootPath}/medicare/HospCataAction!autoMatchCata.action", {
						"hospCataDto.aae016_kae8" : "-1",
						"hospCataDto.ake003" : "2,3",
						"hospCataDto.caa027" : $("#caa027").val(),
						"hospCataDto.aaa027" : designated_center.aaa027,
						"hospCataDto.aaz107" : designated_center.aaz107
					}, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							popupAlert("自动匹配到" + ret.data + "条数据", "提示", "info");
							centerCata.reset();
							$("#hospCata .l-button:contains(查询)").click();
							$("#matchList .l-button:contains(查询)").click();
						}
					});
				}
			});
		});

	});

	function onDblClickRow_hospCata(data, rowid, rowdata) {
		$("input[ligeruiid=centerCata_ake006]").val(data.ake006);
		//$("input[ligeruiid=centerCata_ake005]").val(data.ake005);
		$("#centerCata .l-button:contains(查询)").click();
	}

	function onDblClickRow_centerCata(data, rowid, rowdata) {
		btnOkMatch.click();
	}

	function onSuccess_centerCata(data, grid) {
		if (hospCata.getSelectedRow() && data.rows.length == 1) {
			setTimeout(function() {
				grid.selectRow(0, 'select');
				btnOkMatch.click();
			}, 100);
		}
	}

	function doDel(){
		//获取多选，全部勾选的数据
		var rows = matchList.getSelectedRows();
		if (powersi.isempty(rows)) {
			popupAlert("请选择需要删除目录", "提示", "error");
			return;
		}
		//TS19102500058 - 【需求开发】结算云目录匹配优化
		//modify by 赵银溪 2011030
// 		var invalid = false;
		//判断结果集是否为空，为空下面循环取值会抛异常
// 		$.each(rows, function(i, row) {
// 			if ("待中心审核" == row['aae016']) {
// 				invalid = true;
// 				popupAlert("待中心审核的目录不能删除", "提示", "error");
// 				return false;
// 			}
// 		});
// 		if (invalid)
// 			return;
		if (!confirm("您确认删除选择的目录信息吗?"))
			return;
		var data = powersi.tostring(rows);
		postJSON("${rootPath}/medicare/HospCataAction!updateKae8Rows.action", {
			"data" : data,
			"caa027" : $("#caa027").val()
		}, function(ret) {
			if (ret.errortype > 0) {
				popupAlert(ret.message, "提示", "error");
			} else {
				popupAlert("操作成功", "提示", "info");
				$("#matchList .l-button:contains(查询)").click();
			}
		});

}


	aaa027Render = function(rowdata, index, value, column) {
		var aaa129 = value;
		$.each(g_designated_center, function(i, n) {
			if (value == n.aaa027) {
				aaa129 = n.aaa129;
				return true;
			}
		});
		return aaa129;
	}
	
	function renderAae036(rowdata, index, value){
		if(value!=null){
			return value.substring(0,10);
		}
		return value;
	}
</script>
</powersi:html>