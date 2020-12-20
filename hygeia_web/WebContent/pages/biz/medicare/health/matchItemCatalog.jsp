<%@page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.biz.medicare.comm.statics.AAE016_KEH4"%>

<%
	String path = request.getContextPath();
	request.setAttribute("akb020", BizHelper.getAkb020());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("curDate", DateFunc.dateToString(new Date(), "yyyyMMdd"));
	request.setAttribute("aaa027", BizHelper.getAaa027());
	request.setAttribute("AAE016_KEH4_0", AAE016_KEH4.AAE016_KEH4_0);
	request.setAttribute("AAE016_KEH4_3", AAE016_KEH4.AAE016_KEH4_3);
	request.setAttribute("AAE016_KEH4_2", AAE016_KEH4.AAE016_KEH4_2);
%>
<powersi:html>
<head>
<powersi:head title="医院体检目录匹配" />
</head>
<powersi:codetable id="aae016List" codeType="aae016" />
<powersi:codetable id="bkh046List" codeType="bkh046" />
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HealthAction!queryItemCatalog.action">
		<powersi:editorlayout cols="10">
			<powersi:editorlayout-row>
				<powersi:codeselect required="true" id="caa027" label="中心系统" codeType="caa027" headerKey="00" />
				<powersi:editorlayout-button colspan="10">
					<powersi:button id="btMatch" label="确认匹配" onclick="doMatch()" />
					<powersi:button id="btSavem" label="保存匹配" onclick="doSaveMatch()" />
					<powersi:button id="btAuto" label="自动匹配" onclick="doAutoMatch()" />
					<%-- 		<powersi:button id="btre" label="刷新数据" onclick="init()" /> --%>
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:form>


	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab2" target="divTab2" label="匹配操作" />
		<powersi:tab id="tab1" target="divTab1" label="匹配信息" />
		<div id="divTab1">
			<powersi:datagrid id="matchList" title="提示：双击可删除"
				url="${rootPath }/medicare/HealthAction!queryMatchCenter.action"
				onDblClickRow="onDblClickRow_matchList" delayLoad="true"
				enabledSort="false" showReload="false" frozen="false"
				enabledEdit="true" showExportExcel="true" showExportExcel2007="true"
				showExportPdf="true">
				<powersi:toolbar>
					<powersi:toolbar-item position="right" id="matchListCondition" />
				</powersi:toolbar>
				<powersi:datagrid-column name="aaa027" label="统筹中心编码" width="75" hide="true"/>
				<powersi:datagrid-column name="akb020" label="医疗机构编码" hide="true"/>
				<powersi:datagrid-column name="bkh046" label="统计类别" hide="true" />
				<powersi:datagrid-column name="bkh046_name" label="统计类别" />
				<powersi:datagrid-column name="bkh029" label="医院目录编码" />
				<powersi:datagrid-column name="bkh030" label="医院目录名称" />
				<powersi:datagrid-column name="bkh027" label="中心目录编码" />
				<powersi:datagrid-column name="bkh028" label="中心目录名称" />

				<powersi:datagrid-column name="bkh076" label="医院目录单价" />
				<powersi:datagrid-column name="bkh103" label="生效时间" />
				<powersi:datagrid-column name="bkh104" label="失效时间" />
				<powersi:datagrid-column name="bke204" label="修改时间" />
				<powersi:datagrid-column name="bke205" label="修改人工号" />
				<powersi:datagrid-column name="bke206" label="修改人" />
				<powersi:datagrid-column name="aae016" label="审核标志" />
				<powersi:datagrid-column name="bke207" label="审核时间" />
				<powersi:datagrid-column name="bke208" label="审核人工号" />
				<powersi:datagrid-column name="bke209" label="审核人" />
				<powersi:datagrid-column name="aae100" label="有效标志 " hide="true" />

			</powersi:datagrid>
		</div>
		<div id="divTab2" class="row">
			<div class="col-6">
				<powersi:datagrid id="hospCata" title="医院待匹配目录"
					url="${rootPath }/medicare/HealthAction!queryItemCatalog.action?healthDTO.flag=1&healthDTO.aae100=1"
					onDblClickRow="onDblClickRow_hospCata" delayLoad="false">
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="hospCataCondition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="bkh027" label="医院项目编码" />
					<powersi:datagrid-column name="bkh028" label="医院项目名称" />
					<powersi:datagrid-column name="bkh046_name" label="统计类别" />
					<powersi:datagrid-column name="bkh046" label="统计类别" hide="true" />
					<powersi:datagrid-column name="aka021" label="五笔" />
					<powersi:datagrid-column name="aka020" label="拼音" />
					<powersi:datagrid-column name="bkh044" label="单价" hide="true" />
				</powersi:datagrid>
			</div>
			<div class="col-6">
				<powersi:datagrid id="centerCata" title="中心可匹配目录"
					url="${rootPath }/medicare/HealthAction!queryItemCatalog.action?healthDTO.flag=2"
					onDblClickRow="onDblClickRow_centerCata"
					onSuccess="onSuccess_centerCata" delayLoad="false">
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="centerCataCondition" />
					</powersi:toolbar>

					<powersi:datagrid-column name="bkh027" label="医保项目编码" />
					<powersi:datagrid-column name="bkh028" label="医保项目名称" />
					<powersi:datagrid-column name="bkh046_name" label="统计类别" />
					<powersi:datagrid-column name="bkh046" label="统计类别" hide="true" />
					<powersi:datagrid-column name="aka021" label="五笔" />
					<powersi:datagrid-column name="aka020" label="拼音" />
				</powersi:datagrid>
			</div>
		</div>

		<%-- 		<powersi:panelbox key="panel_query" title="匹配信息" allowCollapse="false">		
			<powersi:datagrid id="matchList" formId="saveMatch"
				showReload="false" enabledSort="false" delayLoad="true"
				onDblClickRow="dbSelectRow" autoWidth="true" autoHeight="true">
				<powersi:datagrid-column name="ake005" label="医院项目编码" with="20%" />
				<powersi:datagrid-column name="ake006" label="医院项目名称" with="15%" />
				<powersi:datagrid-column name="ake006" label="医保项目编码" with="15%" />
				<powersi:datagrid-column name="ake006" label="医保项目名称" with="15%" />
				<powersi:datagrid-column name="ake006" label="匹配生效日期" with="15%" />
				<powersi:datagrid-column name="ake006" label="匹配失效日期" with="15%" />
			</powersi:datagrid>
	</powersi:panelbox>  --%>
	</powersi:tabbedpanel>

	<powersi:errors />

	<script type="text/javascript">
		$(function() {
			bkh046List.unshift({
				"id" : "${bkh046List}",
				"text" : "全部"
			});

			$.each([ "hospCata", "centerCata", "matchList" ], function(i, n) {
				var fields = [ {
					id : n+ '_bkh046',
					name : 'healthDTO.bkh046',
					label : "统计类别",
					type : "combobox",
					width : "120px;",
					newline : false,
					options : {
						data : bkh046List,
						initValue : "${bkh046List}",
						cancelable : false
					}
				}
				, {
					id : n+ '_bkh028',
					name : 'healthDTO.bkh028',
					label : "目录名称",
					type : "text",
					newline : false
				}, {
					id : n+ '_bkh026',
					name : 'healthDTO.bkh026',
					label : "目录编码",
					type : "text",
					newline : false
				}, {
					id : n+ '_bkh046s',
					name : 'healthDTO.bkh046s',
					label : "",
					type : "text",
					width : "0px;",
					newline : true
				} ];
				if ("matchList" == n) {
					fields.unshift({
						id : 'aae016',
						name : 'healthDTO.aae016',
						label : "审核状态",
						type : "combobox",
						newline : false,
						options : {
							data : aae016List,
							initValue : "${AAE016_KEH4_0}",
							cancelable : false,
							onSelected : function(value, text) {
								$("#matchList .l-button:contains(查询)").click();
							}
						}
					});
				}
				var datagrid = window[n];
				var condition = $.extend({}, $.ligerDefaults.Form.custom, {
					inputWidth : "matchList" == n ? 170
							: screen.width > 1900 ? 130 : 70,
					showSearch : true,
					showClose : false,
					fields : fields,
					search : function(d) {
						var parm = {
							"healthDTO.caa027" : $("#caa027").val(),
							"healthDTO.aaa027" : "${aaa027}"
						};
						$.each(d, function(i, n) {
							parm[n.field] = n.value;
							if ('healthDTO.aae016' == n.field)
								parm['healthDTO.aae100'] = '';
						});
						parm['pagesize'] = $(
								"#" + datagrid.id + " select[name=rp]").val();
						datagrid.setParms(parm);
						datagrid.loadServerData(parm);
					}
				});
				datagrid.bindCondition(condition, n + "Condition");
				$.each("${hideColumns}".split(","), function(i, col) {
					datagrid.toggleCol(col, false);
				});
			});

		});

		//手动保存匹配
		function doMatch() {
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
			if (rowHosp.bkh046 != rowCenter.bkh046) {
				popupAlert("匹配的目录统计类别不一致", "提示", "error");
				return;
			}
			var rowData = {
				//bkh074:自动增长
				bkh046 : rowHosp.bkh046, //统计类别
				aaa027 : "${aaa027}", //统筹中心
				akb020 : "${akb020}", //医疗机构编码
				bkh027 : rowCenter.bkh027, //中心目录编码
				bkh028 : rowCenter.bkh028, //中心目录名称
				bkh029 : rowHosp.bkh027, //医院目录编码
				bkh030 : rowHosp.bkh028, //医院目录名称
				bkh076 : rowHosp.bkh044, //医院目录单价
				aka020 : rowHosp.aka020, //医院目录拼音
				aka021 : rowHosp.aka021, //医院目录五笔
				bkh103 : "0", //生效时间
				bkh104 : "0", //失效时间
				bke204 : "${curDate}", //修改时间
				bke205 : "${loginUser}", //修改人工号
				bke206 : "${userName}", //修改人
				aae016 : "0", //审核标志  
				bke207 : "", //审核时间
				bke208 : "", //审核人工号
				bke209 : "", //审核人
				aae013 : "", //备注
				bkh047 : "1", //版本号
				aae100 : "1" //有效标志 (0:无效 1有效)

			};

			popupDialog(
					{
						url : "${rootPath}/pages/biz/medicare/health/MatchDate.jsp",
						onClosed : function() {
							var ret = this.returnValue;
							var d = powersi.tojson(ret);
							if (!d)
								return;
							rowData.bkh103 = d.aae030 ? d.aae030 : "20160101";
							rowData.bkh104 = d.aae031 ? d.aae031 : "20991231";
							postJSON(
									"${rootPath}/medicare/HealthAction!saveMatchCata.action",
									{
										data : powersi.tostring([ rowData ])
									},
									function(ret) {
										if (ret.errortype > 0) {
											popupAlert(ret.message, "提示",
													"error");
										} else {
											popupAlert(ret.data, "提示", "info");
											centerCata.reset();
											$(
													"#hospCata .l-button:contains(查询)")
													.click();
											$(
													"#matchList .l-button:contains(查询)")
													.click();
										}
									});
						}
					}, 400, 400);

		}

		//自动匹配
		function doAutoMatch() {
			popupConfirm(
					"确定自动生成匹配目录吗？\n匹配规则：相同的目录类别，目录名称模糊匹配\n<b>注意：如果目录较多，匹配时间可能会稍长，请耐心等待！</b>",
					"提示",
					function(isOk) {
						if (isOk) {
							postJSON(
									"${rootPath}/medicare/HealthAction!autoMatchCata.action",
									{
										"healthDTO.aaa027" : "${aaa027}",
										"healthDTO.aae100" : "1"
									},
									function(ret) {
										if (ret.errortype > 0) {
											popupAlert(ret.message, "提示",
													"error");
										} else {
											popupAlert("自动匹配到" + ret.data
													+ "条数据", "提示", "info");
											centerCata.reset();
											$(
													"#hospCata .l-button:contains(查询)")
													.click();
											$(
													"#matchList .l-button:contains(查询)")
													.click();
										}
									});
						}
					});
		}

		//保存匹配列表的信息
		function doSaveMatch() {
			popupConfirm(
					"将未审核的匹配目录提交到中心审核？\n<b>注意：提交后中心未审核之前，目录不允许做删除、修改操作！</b>",
					"提示",
					function(isOk) {
						if (isOk) {
							postJSON(
									"${rootPath}/medicare/HealthAction!submitMatchCata.action",
									{
										"HealthDTO.aaa027" : "${aaa027}"
									},
									function(ret) {
										if (ret.errortype > 0) {
											popupAlert(ret.message, "提示",
													"error");
										} else {
											popupAlert("共提交了" + ret.data
													+ "条目录待中心审批", "提示", "info");
											$(
													"#matchList .l-button:contains(查询)")
													.click();
										}
									});
						}
					});

		}

		/* 		//双击匹配信息列表，删除选择的行
		 function dbSelectRow(rowdata, rowid, rowobj) {
		 if (!confirm("您确认删除此条药品的匹配吗?")) {
		 return;
		 }

		 matchList.deleteRow(rowid);
		 } */

		 function onDblClickRow_matchList() {
				popupConfirm(
						"确定删除此条匹配信息吗？",
						"提示",
						function(isOk) {
							if (isOk) {
								var row = matchList.getSelectedRow();
								if ("1" == row.aae016) {
									popupAlert("已审核的目录不能删除", "提示", "error");
									return;
								}
								if ("3" == row.aae016) {
									popupAlert("待中心审核的目录不能删除", "提示", "error");
									return;
								}
								/**
								 * 任务概要：TS19080100098 - 【问题修复】【体检项目目录匹配】-“匹配信息”TAB页签中双击提示删除成功，界面依旧存在该值
								 * 修改说明：区分未审核的内容，未审核的单独调用另外一个方法
								 * 修改人及时间：李嘉伦 20190801
								*/
								if ("0" == row.aae016){
									postJSON(
											"${rootPath}/medicare/HealthAction!delMatchCataTemp.action",
											{
												data : powersi.tostring([ {
													bkh029 : row.bkh029,
													akb020 : "${akb020}",
													aaa027 : "${aaa027}",
													aae016 : row.aae016,
													aae100 : "1"
												} ])

											},
											function(ret) {
												if (ret.errortype > 0) {
													popupAlert(ret.message, "提示",
															"error");
												} else {
													popupAlert("删除成功", "提示", "info");
													$(
															"#matchList .l-button:contains(查询)")
															.click();
												}
											});
								}else{
									postJSON(
											"${rootPath}/medicare/HealthAction!delMatchCata.action",
											{
												data : powersi.tostring([ {
													bkh029 : row.bkh029,
													akb020 : "${akb020}",
													aaa027 : "${aaa027}",
													aae016 : row.aae016,
													aae100 : "1"
												} ])

											},
											function(ret) {
												if (ret.errortype > 0) {
													popupAlert(ret.message, "提示",
															"error");
												} else {
													popupAlert("删除成功", "提示", "info");
													$(
															"#matchList .l-button:contains(查询)")
															.click();
												}
											});
								}
							}
						});
			}


		function onDblClickRow_hospCata(data, rowid, rowdata) {
			$("input[ligeruiid=centerCata_bkh028]").val(data.bkh028);
			$("input[ligeruiid=centerCata_bkh046]").val(data.bkh046_name);
			$("input[ligeruiid=centerCata_bkh046s]").val(data.bkh046);
			$("#centerCata .l-button:contains(查询)").click();
			$("input[ligeruiid=centerCata_bkh046s]").val("");
		}
		function onDblClickRow_centerCata(data, rowid, rowdata) {
			//btnOkMatch.click();
		}

		function onSuccess_centerCata(data, grid) {
			if (hospCata.getSelectedRow() && data.rows.length == 1) {
				setTimeout(function() {
					grid.selectRow(0, 'select');
					//btnOkMatch.click();
				}, 100);
			}
		}
	</script>

</body>
</powersi:html>