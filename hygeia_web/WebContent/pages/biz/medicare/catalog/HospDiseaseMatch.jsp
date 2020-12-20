<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<powersi:html>
<powersi:head title="医院疾病目录匹配" />
<powersi:codetable id="aae016List" codeType="aae016_kae8" />
<body>
	<powersi:editorlayout cols="8">
		<powersi:editorlayout-row>
			<powersi:codeselect required="true" id="caa027" label="中心系统" codeType="caa027" headerKey="00" />
			<powersi:editorlayout-button colspan="8">
				<powersi:button id="btnOkMatch" label="确认匹配" />
				<powersi:button id="btnSaveMatch" label="保存匹配" />
				<powersi:button id="btnAutoMatch" label="自动匹配" />
			</powersi:editorlayout-button>
		</powersi:editorlayout-row>
	</powersi:editorlayout>
	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab2" target="divTab2" label="匹配操作" />
		<powersi:tab id="tab1" target="divTab1" label="匹配信息" />
		<div id="divTab1">
			<powersi:datagrid id="matchList" title="提示：双击可删除" url="${rootPath }/medicare/HospCataAction!queryMatchDisease.action"
				onDblClickRow="onDblClickRow_matchList" showReload="false" delayLoad="true" enabledExportExcel="true" pageSize="20">  <!-- TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页默认值问题修复 -->
				<powersi:toolbar>
					<powersi:toolbar-item position="right" id="matchListCondition" />
				</powersi:toolbar>
				<powersi:datagrid-column name="aka420" label="医院目录编码" />
				<powersi:datagrid-column name="aka421" label="医院目录名称" />
				<powersi:datagrid-column name="aka120" label="中心目录编码" />
				<powersi:datagrid-column name="aka121" label="中心目录名称" />
				<powersi:datagrid-column name="aae016" label="审核标志"  width="70" code="aae016List"/>
				<powersi:datagrid-column name="aae030" label="生效时间"  width="80" render="renderAae036"/>
				<powersi:datagrid-column name="aae031" label="失效时间"  width="80" render="renderAae036"/>
				<powersi:datagrid-column name="bke204" label="匹配操作时间" width="130" render="renderAae036"/>
				<powersi:datagrid-column name="bke205" label="匹配操作人" />
				<powersi:datagrid-column name="bke206" label="匹配操作人工号" width="80" />
				<powersi:datagrid-column name="aka020" label="拼音码" />
				<powersi:datagrid-column name="aka021" label="五笔码" />
				<powersi:datagrid-column name="akb020" label="医院编码" hide="true" />
				<powersi:datagrid-column name="ka60id" label="医院目录编号" hide="true" />
				<powersi:datagrid-column name="aaz164" label="中心目录编号" />
				<powersi:datagrid-column name="baz279" label="匹配目录ID" width="135" />
			</powersi:datagrid>
		</div>
		<div id="divTab2" class="row">
			<div class="col-6">
				<powersi:datagrid id="hospDisease" title="医院待匹配疾病目录" delayLoad="true"
					url="${rootPath }/medicare/HospCataAction!queryHospDiseasePage.action"
					onDblClickRow="onDblClickRow_hospDisease" pageSize="20">  <!-- TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页默认值问题修复 -->
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="hospDiseaseCondition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="aka420" label="目录编码" />
					<powersi:datagrid-column name="aka421" label="目录名称" />
					<powersi:datagrid-column name="aka122" label="疾病分类" />
					<powersi:datagrid-column name="aka062" label="英文化学名" />
					<powersi:datagrid-column name="bkc002" label="经办人" />
					<powersi:datagrid-column name="aae100" label="有效标志" code="aae100"/>		
				</powersi:datagrid>
			</div>
			<div class="col-6">
				<powersi:datagrid id="centerDisease" title="中心可匹配疾病目录" onDblClickRow="onDblClickRow_centerDisease"
					url="${rootPath }/medicare/HospCataAction!queryCenterDisease.action"
					onSuccess="onSuccess_centerDisease" delayLoad="true" pageSize="20">  <!-- TS20011500163 - 【需求开发】结算云药品目录匹配界面优化   分页默认值问题修复 -->
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="centerDiseaseCondition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="aka120" display="疾病编码" />
					<powersi:datagrid-column name="aka121" display="疾病名称" />
					<powersi:datagrid-column name="aae100" display="有效标志" code="valid_flag" />
					<powersi:datagrid-column name="aae030" display="生效日期" format="{0,date,yyyy-MM-dd}" />
					<powersi:datagrid-column name="aae031" display="失效日期" format="{0,date,yyyy-MM-dd}" />
					<powersi:datagrid-column name="bke961" display="疾病费用标准" />
					<powersi:datagrid-column name="aae013" display="备注" />
				</powersi:datagrid>
			</div>
		</div>
	</powersi:tabbedpanel>
</body>
<script type="text/javascript">
	$(function() {
		$.each([ "hospDisease", "centerDisease", "matchList" ], function(i, n) {
			var fields = [{
				id : n + '_aka120',
				name : 'diseaseDto.aka120',
				label : "疾病编码",
				width : "80px;",
				type : "text",
				newline : false
			},{
				id : n + '_aka121',
				name : 'diseaseDto.aka121',
				label : "疾病名称",
				width : "80px;",
				type : "text",
				newline : false
			} ];
			if ("matchList" == n) {
				fields.unshift({
					id : n + '_aka420',
					name : 'diseaseDto.aka420',
					label : "医院疾病编码",
					width : "80px;",
					type : "text",
					newline : false
				});
				fields.unshift({
					id : n + '_aka421',
					name : 'diseaseDto.aka421',
					label : "医院疾病名称",
					width : "80px;",
					type : "text",
					newline : false
				});
				fields.unshift({
					id : n + '_aae016',
					name : 'diseaseDto.aae016',
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
							"diseaseDto.aae016_ke06" : "-1",
							"diseaseDto.aae100" : "1",
							"diseaseDto.validityFlag" : "1",
							"diseaseDto.caa027" : $("#caa027").val(),
					};
					$.each(d, function(i, n) {
						parm[n.field] = n.value;
						if ('diseaseDto.aae016' == n.field && "2" == n.value)
							parm['diseaseDto.aae100'] = '';
					});
					//parm['pagesize'] = $("#" + datagrid.id + " select[name=rp]").val();    //TS20010800160 - 【需求开发】结算云药品目录匹配界面优化     分页失效问题修复
					datagrid.setParms(parm);
					datagrid.loadServerData(parm);
				}
			});
			datagrid.bindCondition(condition, n + "Condition");
		});

		var btnOkMatch = $("#btnOkMatch"); //确认匹配
		btnOkMatch.click(function() {
			var rowHosp = hospDisease.getSelectedRow();
			if (powersi.isempty(rowHosp)) {
				popupAlert("请选择需要匹配的医院目录", "提示", "error");
				return;
			}
			var rowCenter = centerDisease.getSelectedRow();
			if (powersi.isempty(rowCenter)) {
				popupAlert("请选择需要匹配的中心目录", "提示", "error");
				return;
			}
			popupDialog({
				url : "${rootPath}/pages/biz/medicare/catalog/MatchDate.jsp",
				onClosed : function() {
					var ret = this.returnValue;
					var d = powersi.tojson(ret);
					if (!d)
						return;
					rowHosp.aae030 = d.aae030 ? d.aae030 : "20160101";
					rowHosp.aae031 = d.aae031 ? d.aae031 : "20991231";
					postJSON("${rootPath}/medicare/HospCataAction!saveMatchDisease.action", {
						"diseaseDto.aaz164" : rowCenter.aaz164,
						"diseaseDto.ka60id" : rowHosp.ka60id,
						"diseaseDto.aae030" : rowHosp.aae030,
						"diseaseDto.aae031" : rowHosp.aae031,
						"diseaseDto.caa027" : $("#caa027").val()
					}, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							centerDisease.reset();
							$("#hospDisease .l-button:contains(查询)").click();
							$("#matchList .l-button:contains(查询)").click();
						}
					});
				}
			}, 150, 300);
		});

		$("#btnSaveMatch").click(function() { //保存匹配
			popupConfirm("将未审核的匹配目录提交到中心审核？\n<b>注意：提交后中心未审核之前，目录不允许做删除、修改操作！</b>", "提示", function(isOk) {
				if (isOk) {
					postJSON("${rootPath}/medicare/HospCataAction!submitMatchDisease.action", {
						"diseaseDto.caa027" : $("#caa027").val()
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
			popupConfirm("确定自动生成匹配目录吗？\n匹配规则：根据疾病名称模糊匹配\n<b>注意：如果目录较多，匹配时间可能会稍长，请耐心等待！</b>", "提示", function(isOk) {
				if (isOk) {
					postJSON("${rootPath}/medicare/HospCataAction!autoMatchDisease.action", {
						"diseaseDto.aae016_ke06" : "-1",
						"diseaseDto.caa027" : $("#caa027").val()
					}, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							popupAlert("自动匹配到" + ret.data + "条数据", "提示", "info");
							centerDisease.reset();
							$("#hospDisease .l-button:contains(查询)").click();
							$("#matchList .l-button:contains(查询)").click();
						}
					});
				}
			});
		});

	});

	function onDblClickRow_hospDisease(data, rowid, rowdata) {
		$("input[ligeruiid=centerDisease_aka121]").val(data.aka421);
		$("#centerDisease .l-button:contains(查询)").click();
	}

	function onDblClickRow_centerDisease(data, rowid, rowdata) {
		btnOkMatch.click();
	}

	function onSuccess_centerDisease(data, grid) {
		if (hospDisease.getSelectedRow() && data.rows.length == 1) {
			setTimeout(function() {
				grid.selectRow(0, 'select');
				btnOkMatch.click();
			}, 100);
		}
	}

	function onDblClickRow_matchList() {
		popupConfirm("确定删除此条匹配信息吗？", "提示", function(isOk) {
			if (isOk) {
				var row = matchList.getSelectedRow();
				if ("3" == row.aae016) {
					popupAlert("待中心审核的目录不能删除", "提示", "error");
					return;
				}
				postJSON("${rootPath}/medicare/HospCataAction!delMatchDisease.action", {
					"diseaseDto.baz279" : row.baz279,
					"diseaseDto.caa027" : $("#caa027").val()
				}, function(ret) {
					if (ret.errortype > 0) {
						popupAlert(ret.message, "提示", "error");
					} else {
						popupAlert("删除成功", "提示", "info");
						$("#matchList .l-button:contains(查询)").click();
					}
				});
			}
		});
	}

	function renderAae036(rowdata, index, value){
		if(value!=null){
			return value.substring(0,10);
		}
		return value;
	}
</script>
</powersi:html>