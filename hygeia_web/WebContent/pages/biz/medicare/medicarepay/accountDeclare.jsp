<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pay" tagdir="/WEB-INF/tags/medicarepay"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.biz.medicare.statics.BAZ016"%>
<%
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	Date date = calendar.getTime();
	SimpleDateFormat dformat = new SimpleDateFormat("yyyyMM");
	request.setAttribute("hospitalName", BizHelper.getAkb021());//医院名称
	request.setAttribute("strStartDate", dformat.format(DateFunc.getFirstDateOfMonth(date)));//开始时间
	request.setAttribute("strEndDate", dformat.format(date));//结束时间
	request.setAttribute("UNCHECKED", BAZ016.UNCHECKED);
	request.setAttribute("bke120_f", "a.akb020 in ('" + BizHelper.getAkb020() + "')");
	request.setAttribute("AAA027", BizHelper.getAaa027());
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/accountDeclare");
	//TS19101100058 【需求开发】城乡居民大病拨付需求调整  陈洁 20191011

%>
<powersi:html>
	<powersi:head title="结算申报" />
	<powersi:codetable id="aac004List" codeType="aac004" />
	<powersi:codetable id="aka130List" codeType="aka130" />
	<powersi:codetable id="bka006List" codeType="bka006" />
	<powersi:codetable id="bka035List" codeType="bka035" />
	<powersi:codetable id="aae140List" codeType="aae140" />
	<powersi:codetable id="aae139List" codeType="aae139" />
	<powersi:codetable id="bke120List" codeType="bke120" />
	<powersi:codetable id="bkb282List" codeType="bkb282" />
	<script type="text/javascript">
		$.ligerDefaults.Filter.operators['string'] = $.ligerDefaults.Filter.operators['text'] = [ "like", "equal", "notequal", "startwith", "endwith" ];//去掉大于小于包括,并改变顺序F
		$(function() {
			/**查询按钮,申报按钮,打印按钮,报表显示iframe,重新生成申报数据复选框,获取明细复选框*/
			var b_query = $("#query"), b_save = $("#save"), b_print = $("#print"), i_report_sum = $("#report_sum"), c_reset_sum = $("#reset_sum"), c_get_detail = $("#get_detail"), c_getBranch = $("#getBranch");
			loadClodopModule();
			<%--$("#daa027").change(function(m) { //拨付中心选择事件--%>
			<%--	if (!window["bkp002_map"])--%>
			<%--		bkp002_map = {};--%>
			<%--	var daa027 = $(this).val(), pre_daa027 = $(this).data('pre');--%>
			<%--	var bkp002 = $("#bkp002"), pre_bkp002_def = bkp002.val(), pre_bkp002_options = bkp002.html().trim();--%>
			<%--	if (bkp002_map[daa027]) {--%>
			<%--		bkp002.html(bkp002_map[daa027]);--%>
			<%--		bkp002.val(bkp002_map[daa027 + '_def']);--%>
			<%--		bkp002.change();--%>
			<%--	} else {--%>
			<%--		postJSON("${ACTION}!getDeclareTypeByDaa027.action", {--%>
			<%--			daa027 : daa027--%>
			<%--		}, function(ret) {--%>
			<%--			if (ret.errortype > 0) {--%>
			<%--				popupAlert(ret.message, "提示", "error");--%>
			<%--			} else {--%>
			<%--				bkp002_map[daa027] = '';--%>
			<%--				$.each(ret.data, function(value, text) {--%>
			<%--					bkp002_map[daa027] += '<option value="' + value + '">' + text + '</option>';--%>
			<%--				});--%>
			<%--				bkp002.html(bkp002_map[daa027]);--%>
			<%--				bkp002.change();--%>
			<%--			}--%>
			<%--		});--%>
			<%--	}--%>
			<%--	if (pre_bkp002_options) {--%>
			<%--		bkp002_map[$(this).data('pre')] = pre_bkp002_options;--%>
			<%--		bkp002_map[$(this).data('pre') + "_def"] = pre_bkp002_def;--%>
			<%--	}--%>
			<%--	$(this).data('pre', daa027);--%>
			<%--}).trigger("change");--%>
			<%--$("#bkp002").change(function() { //结算申报类型选择事件--%>
			<%--	var bkp002_text = $(this).find("option:selected").text();--%>
			<%--	setTimeout(function() {--%>
			<%--		var columns = {};--%>
			<%--		$.each({--%>
			<%--			_职工 : [ 'bkc471', 'bka008', 'bkc469', 'bkc470', 'bkc472' ], //职工--%>
			<%--			_离休 : [ 'bkc474', 'bka008', 'bkc469', 'bkc470', 'bkc472' ], //离休--%>
			<%--			_居民 : [ 'bkc475', 'bkc483', 'bkc484', 'bkc485', 'bkc486', 'bkc488' ], //居民--%>
			<%--			_生育 : [ 'bkc473', 'bka008', 'bkc469', 'bkc470', 'bkc472' ], //生育--%>
			<%--		}, function(aae140, cols) {--%>
			<%--			$.each(cols, function(i, col) {--%>
			<%--				if (!columns[col] || columns[col] == false)--%>
			<%--					columns[col] = new RegExp('^' + aae140.replace('_', '') + '.*').test(bkp002_text);--%>
			<%--			});--%>
			<%--		});--%>
			<%--		$.each(columns, function(col, isShow) {--%>
			<%--			grid_detail.toggleCol(col, isShow);--%>
			<%--		});--%>
			<%--	}, 50);--%>
			<%--});--%>

			b_query.click(function() { //检索按钮
				if (!checkFormValidtion()) //校验必填项
					return;
				b_query.attr('disabled', true);
				var param = $("#f_query").serialize();
				param += "&q.reset_sum=" + (c_reset_sum.is(':checked') ? '1' : '0');
				param += "&q.get_detail=" + (c_get_detail.is(':checked') ? '1' : '0');
				param += "&q.getBranch=" + (c_getBranch.is(':checked') ? '1' : '0');
				postJSON("${ACTION}!querySum.action", param, function(json) {
					b_query.attr('disabled', false);
					b_print.attr('disabled', true);
					b_save.attr('disabled', true);
					if (json.errortype > 0) {
						var alertType = /^系统正在统计.*的申报数据.*$/.test(json.message) ? "warn" : /^未统计到需要申报的数据.*/.test(json.message) ? "info" : "error";
						popupAlert(json.message, "提示", alertType, function() {
							c_reset_sum.attr("checked", false);
							i_report_sum.removeData();
							i_report_sum.attr("src", i_report_sum.attr("src"));
							grid_detail.loadData([]);
							if (json.message.indexOf('可尝试勾选右上角') > 0) {
								c_reset_sum.attr("checked", true);
							}
						});
					} else {
						i_report_sum.data(json.data);
						i_report_sum.contents().find("body").html(json.data.reportHtml);
						if (c_get_detail.is(':checked')) {
							queryDetail();
						} else {
							grid_detail.loadData([]);
						}
						_x = json.data.bke403s ? b_save.attr('disabled', false) : b_print.attr('disabled', false);
					}
				});
			});

			queryDetail = function() { //明细查询
				var data = i_report_sum.data();
				grid_detail.setParms({
					"q.bke402" : data.bkp402s
				});
				grid_detail.loadData();
			}

			$("#save").click(function() { //保存申报备注信息
				alert("申报成功！");
				return;
				var data = i_report_sum.data();
				if ($.isEmptyObject(data)) {
					popupAlert("无申报内容。", "提示", "info");
					return;
				}
				if (!data.bke403s || data.bke403s.length == 0) {
					popupAlert("您已申报过，请直接打印。", "提示", "info");
					return;
				}
				if (grid_detail.getChangeRows(true).length > 0) {
					popupAlert("你修改了申报明细，请保存后再申报。", "提示", "warn");
					return;
				}
				popupConfirm("批次号为[" + data.bke403s + "]的数据未申报，是否申报？", "确认", function(k) {
					if (k) {
						postJSON("${ACTION}!confirmAccountDeclare.action", {
							"q.bke403" : data.bke403s,
							"q.aae013" : $.trim($("#aae013").val()),
							"q.bke402" : data.bkp402s
						}, function(json) {
							if (json.errortype > 0) {
								popupAlert(json.message, "提示", "error");
							} else {
								popupAlert("申报成功。", "提示", "info");
								data.bke403s = null;
								$("#save").attr('disabled', true);
								$("#print").attr('disabled', false);
							}
						});
					}
				});
			});

			b_print.click(function() { //打印按钮
				var data = i_report_sum.data();
				if ($.isEmptyObject(data)) {
					popupAlert("无打印内容。", "提示", "warn");
					return;
				}
				if (data.bke403s) {
					popupAlert("包含未申报数据无法打印", "提示", "warn");
				} else {
					i_report_sum.contents().find("body").PowerPrint({
						name : "结算申报汇总"
					}).preview();
				}
			});

			saveBtn = function() { // 保存申报明细修改
				var data = i_report_sum.data();
				if ($.isEmptyObject(data)) {
					return;
				}
				if (!data.bke403s) {
					popupAlert("您已申报过，请直接打印。", "提示", "info");
					return;
				}
				setTimeout(function() {
					var changeRows = grid_detail.getChangeRows(true);
					if (changeRows.length > 0) {
						var datas = [];
						var focusEle = null;
						try {
							var regx = /id$/g;
							$.each(changeRows, function(i, d) {
								var id = $(".l-grid-row-cell-last:contains(" + d.bke100 + ")").attr("id").replace(/\|/g, "\\\|");
								if (d.bkb282 && !d.bkb283) {
									focusEle = $("#" + id.replace(regx, 'bkb283'));
									throw '请输入特殊病例申请原因';
								}
								if (!d.bkb282 && d.bkb283) {
									focusEle = $("#" + id.replace(regx, 'bkb282'));
									throw '请选择特殊病例申请类型';
								}
								datas.push({
									bke100 : d.bke100,
									bke120 : d.bke120,
									bkb282 : d.bkb282,
									bkb283 : d.bkb283
								});
							});
							postJSON("${ACTION}!saveDeclareAccountDetailChanges.action", {
								datas : JSON.stringify(datas)
							}, function(ret) {
								if (ret.errortype > 0) {
									popupAlert(ret.message, "提示", "error");
								} else {
									if (ret.data.specialCasesApplySum && ret.data.specialCasesApplySum > 0) {
										popupAlert("您修改了明细数据的申报方式，需要重新生成申报数据。", "提示", "info", function() {
											c_reset_sum.attr("checked", true);
											b_query.click();
										});
									} else {
										popupAlert("保存成功。", "提示", "info", function() {
											queryDetail();
										});
									}
								}
							});
						} catch (e) {
							popupAlert(e, "提示", "error", function(x) {
								if (focusEle)
									focusEle.click();
							});
						}
					} else {
						popupAlert("无修改内容。", "提示", "warn");
					}
				}, 50);
			}

			onAfterShowData_grid_detail = function(data) {
				if (data.length > 0 && bke120List.length == 0 && data[0].bke120_codes) {
					$.each(JSON.parse(data[0].bke120_codes), function(i, c) {
						bke120List.push(c);
					});
					$('div[toolbarid=save].l-toolbar-item').show();
				} else {
					grid_detail.toggleCol("bke120", false);
					$('div[toolbarid=save].l-toolbar-item').hide();
				}
				if (data.length > 0 && bkb282List.length == 0 && data[0].bkb282_codes) {
					$.each(JSON.parse(data[0].bkb282_codes), function(i, c) {
						bkb282List.push(c);
					});
				} else {
					$.each([ "bkb282", "bkb283", "bkb262" ], function(i, c) {
						grid_detail.toggleCol(c, false);
					});
				}
				if (window['_findcontainerInit']) {
					return;
				}
				window['_findcontainerInit'] = 1;
				$("#grid_detail span:contains(高级筛选查找)").click(); //高级查询字段设置
				if (findcontainer = liger.get('grid_detail_findcontainer')) {
					$(".l-dialog-winbtn,.l-icon-cross.deleterole").click();
					findcontainer.options.fields.splice(0);
					$.each(grid_detail.getColumns(), function(i, c) {
						if (c._hide == false && c.display)
							findcontainer.options.fields.push(c);
					});
				}
			}

			searchBtn = function() {
				grid_detail.showFind();
			}

			onBeforeEdit_dg = function(edit) {
				var data = i_report_sum.data();
				if (!data.bke403s) {
					return false;
				}
				var columnname = edit.column.columnname;
				var bke299 = edit.record['bke299'];
				var baz016 = edit.record['baz016'];
				if ('bke120' == columnname && "${UNCHECKED}" == baz016) {
					return true;
				} else if ('0' == bke299) {
					return true;
				}
				return false;
			}

			daterangeInit = function() {
				return {
					startDate : "${strStartDate}",
					endDate : "${strEndDate}"
				}
			}

			$("#report_sum").load(function() {
				$.get("${rootPath}/pages/biz/medicare/medicarepay/accountDeclareReport.jsp", function(html) {
					$("#report_sum").contents().find('body').html(html);
				});
			});

			$("#ui-id-1").prepend("<i class='icon-bar-chart green'>&nbsp;</i>");
			$("#ui-id-2").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");

		});
	</script>
	<body>
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:form id="f_query">
			<powersi:panelbox-toolbar>
				<%--				<span><font color="red">提示：已退费的业务不参与申报</font>&nbsp;&nbsp;</span>--%>
				<%--				<span><input id="reset_sum" type="checkbox" name="q.reset_sum" title="医保中心未开始审核前操作有效" />重新生成申报数据&nbsp;&nbsp;</span>--%>
				<%--				<span><input id="get_detail" type="checkbox" name="q.get_detail" />是否获取明细&nbsp;&nbsp;</span>--%>
				<%--				<span><input id="getBranch" type="checkbox" checked="checked" name="q.getBranch" />同时获取分店(院)&nbsp;&nbsp;</span>--%>
				<pay:refreshCodetype />
				<powersi:button id="query" buttonIcon="icon-search" label="检 索" data-hotkey="Alt+Q" />
				<powersi:button id="save" buttonIcon="icon-check" label="申 报" disabled="true" data-hotkey="Alt+S" />
				<powersi:hidden id="print" buttonIcon="icon-print" label="打 印" disabled="true" data-hotkey="Alt+P" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<input type="hidden" name="q.akb020" value="<%=BizHelper.getAkb020()%>" />
				<powersi:editorlayout-row>
					<mediTag:daterange
							labelValues="{'value':'bke399',text:'申报期间','type':'month','format':'YYYYMM',name:'q.aae041,q.aae042','extFunc':'daterangeInit'}" />
					<powersi:hidden id="daa027" label="拨付中心" name="q.daa027" codeType="aaa027" required="true" value="${AAA027}" headerKey=" " />
					<powersi:codeselect id="bkp002" label="申报类型" list="#{'1':'门诊申报','2':'住院申报'}" headerKey=" " />
					<powersi:textfield id="aae013" name="q.aae013" label="申报说明" placeholder="申报时可在此输入申报备注信息" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="结算申报汇总报表" />
			<%--			<powersi:tab id="tab2" target="divTab2" label="结算申报明细" />--%>
			<div id="divTab1">
				<div class="frame_div">
					<iframe id="report_sum" style="width: 96%;"></iframe>
				</div>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="grid_detail" url="${ACTION}!queryDetail.action" delayLoad="true" enabledSort="false"
								  onAfterShowData="onAfterShowData_grid_detail" onBeforeEdit="onBeforeEdit_dg" enabledEdit="true"
								  onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
								  pagesizeParmName="q.pageSize" showExportExcel2007="true">
					<powersi:toolbar>
						<powersi:toolbar-item id="search" text="高级筛选查找" icon="search2" click="searchBtn" />
						<powersi:toolbar-item id="save" text="保存修改" icon="save" click="saveBtn" />
					</powersi:toolbar>
					<powersi:datagrid-column name="bke120" label="疾病归类" id="bke120" editor="{type:'select',data:bke120List}" render="_render" />
					<powersi:datagrid-column name="bkb282" label="特殊病例申请类型" id="bkb282" editor="{type:'select',data:bkb282List}"
											 render="function(r,i,v,c){return v?_render(r,i,v,c):r.bke?_render(r,i,r.bke.split('-')[0],c):v;}" />
					<powersi:datagrid-column name="bkb283" label="特殊病例申请原因" id="bkb283" editor="{type:'text'}"
											 render="function(r,i,v,c){return v?v:r.bke?r.bke.split('-')[1]:v;}" />
					<powersi:datagrid-column name="aac003" display="姓名" width="70" />
					<powersi:datagrid-column name="aac002" display="身份证号码" width="147" />
					<powersi:datagrid-column name="aac004" display="性别" width="30" render="_render" code="aac004" />
					<powersi:datagrid-column name="bka035" display="人员类型" width="80" render="_render" code="bka035" />
					<powersi:datagrid-column name="aka130" display="业务类型" width="80" render="_render" code="aka130" />
					<powersi:datagrid-column name="bka006" display="待遇类型" width="80" render="_render" code="bka006" />
					<powersi:datagrid-column name="bke165" display="结算方式" />
					<powersi:datagrid-column name="bkb262" display="月结分数" totalSummary="{'render': _totalSummary}" />
					<powersi:datagrid-column name="akc264" display="医疗总费用" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc465" display="基金总支付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc466" display="基本统筹支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc467" display="基本统筹自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc469" display="大病统筹支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc470" display="大病统筹自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc471" display="公务员补助金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc472" display="特殊补贴金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc488" display="大病补助金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc473" display="生育基金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc474" display="离休基金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc476" display="起付标准金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc477" display="个人账户支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc478" display="超标自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc479" display="现金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc480" display="段外全部自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc481" display="段外部分自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc483" display="特惠保金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc484" display="民政救助金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc485" display="医院减免金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc486" display="财政兜底金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bka008" display="所在单位" />
					<powersi:datagrid-column name="akc194" display="结算时间" width="140" />
					<powersi:datagrid-column name="bkz102" display="疾病诊断" />
					<powersi:datagrid-column name="aae139" display="异地标志" render="_render" code="aae139" />
					<powersi:hidden name="daa027" display="拨付中心" render="function(r, i, v, c){return _render(r, i, v, {columnname:'daa027'});}"
									code="aaa027" />
					<powersi:datagrid-column name="akb021" display="医院名称" />
					<powersi:datagrid-column name="kc21id" display="业务号" />
					<powersi:datagrid-column name="bke100" id="id" display="登账号" />
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
	</body>
</powersi:html>