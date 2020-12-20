<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@ page import="com.powersi.biz.medicare.statics.AAE016"%>
<%@ page import="com.powersi.biz.medicare.statics.BKP160"%>
<%@ taglib prefix="pay" tagdir="/WEB-INF/tags/medicarepay"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%
	String aae016 = request.getParameter("aae016"); //可申诉的费用审核状态，默认为1
	request.setAttribute("aae016", UtilFunc.isEmpty(aae016) ? aae016 = AAE016.AAE016_1 : aae016);
	request.setAttribute("bkp160", request.getParameter("bkp160")); //可选择的申诉状态，默认全部
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	request.setAttribute("aae041", DateFunc.dateToString(calendar.getTime(), "yyyyMM"));
	calendar.add(Calendar.MONTH, +1);
	request.setAttribute("aae042", DateFunc.dateToString(calendar.getTime(), "yyyyMM"));
	request.setAttribute("aae030",
			DateFunc.dateToString(DateFunc.getFirstDateOfMonth(calendar.getTime()), "yyyyMMdd"));
	request.setAttribute("aae031", DateFunc.dateToString(calendar.getTime(), "yyyyMMdd"));
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("BKP160_0", BKP160.BKP160_0);
	request.setAttribute("BKP160_1", BKP160.BKP160_1);
	request.setAttribute("BKP160_2", BKP160.BKP160_2);
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/feeAppeal");
%>
<powersi:html>
<powersi:head title="扣付费用申诉" />
<powersi:codetable id="aae016List" codeType="aae016_zx" />
<script type="text/javascript">
	var info = {};
	$(function() {
		if ('${bkp160}' == 'readonly') {
			_removeSelectItems('bkp160', '${BKP160_0},${BKP160_2}');
		}

		$("#query").click(function() {
			var params = {};
			if (checkFormValidtion()) { //校验必填项
				setTimeout(function() {
					dg_biz.loadForm(f_query);
					$("#divTabs").selTab(0);
					dg_fee.loadData([]);
				}, 100);
			}
		});
		$(document).on("click", ".show_dg_fee", function() {
			var row = dg_biz.getRow($(this).attr("index"));
			dg_fee.setParms({
				"q.bke100" : row.bke100,
				"q.aae016" : row.aae016
			});
			$("#divTabs").selTab(1);
			dg_fee.loadData();
			if ("readonly" == "${bkp160}") {
				dg_fee.toggleCol("bkp059_show", true);
				dg_fee.toggleCol("bkp060_show", true);
				dg_fee.toggleCol("bkp059", false);
				dg_fee.toggleCol("bkp060", false);
				$("#dg_fee .l-form").hide();
			} else {
				dg_fee.toggleCol("bkp059_show", false);
				dg_fee.toggleCol("bkp060_show", false);
				dg_fee.toggleCol("bkp059", true);
				dg_fee.toggleCol("bkp060", true);
				$("#dg_fee .l-form").show();
			}
		});
		$(document).on("click", ".appeal-save", function() {
			var saveData = [], bkp055 = liger.get("bkp055").getValue();
			_datagridEditValidation(dg_fee, null, function(data) {
				if (data.length == 0) {
					data = dg_fee.getSelectedRows();
					if (data.length == 0) {
						popupAlert("请勾选需要申诉的数据。", "提示", "error");
						return;
					}
					if (!bkp055) {
						popupAlert("请输入申诉原因。", "提示", "error", function() {
							$("input[ligeruiid=bkp055]").focus();
						});
						return;
					}
				}
				$.each(data, function(i, r) {
					saveData.push({
						id : r.id,
						bke100 : r.bke100,
						bkc371 : r.bkc371,
						bkp055 : dg_fee.hasChanged() ? r.bkp055 : bkp055,
						bkp057 : '${loginUser}',
						bkp058 : '${userName}'
					});
				});
				postJSON("${ACTION}!saveFeeAppeal.action", {
					datas : JSON.stringify(saveData)
				}, function(json) {
					if (json.errortype > 0) {
						popupAlert(json.message, "提示", "error");
					} else {
						popupAlert("申诉成功。", "提示", "info", function() {
							dg_fee.loadData();
						});
					}
				});
			});
		});
		setTimeout(function() {
			dg_fee.bindCondition($.extend({}, $.ligerDefaults.Form.custom, {
				showSearch : false,
				fields : [ {
					name : 'bkp055',
					label : "申诉原因",
					type : "text",
					width : 300
				} ],
				onAfterSetFields : function() {
					var id = this.id, btnHtml = '<li title="申诉"><div class="l-button appeal-save">'
							+ '<i class="icon-comments"></i><span> 申诉</span></div></li>';
					setTimeout(function() {
						$("#" + id + " > div > ul").append(btnHtml);
					}, 50);
				}
			}), "dg_fee_condition");
		}, 100);

		daterangeInit = function() {
			return {
				startDate : 'bke399' != this.value ? "${aae030}" : "${aae041}",
				endDate : 'bke399' != this.value ? '${aae031}' : "${aae042}"
			}
		}

		$("#ui-id-1").prepend("<i class='icon-desktop green'>&nbsp;</i>");
		$("#ui-id-2").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");

	});
</script>
<body>
	<powersi:form id="f_query" action="/medicarepay/feeAppeal!queryDeductionBiz.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<pay:refreshCodetype />
				<powersi:button id="query" buttonIcon="icon-search" label="查 询" data-hotkey="Alt+Q" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<input type="hidden" name="q.aae016" value="${aae016}" />
					<mediTag:daterange
						labelValues="{'value':'bke399',text:'申报期间','type':'month','format':'YYYYMM',name:'q.aae041,q.aae042','extFunc':'daterangeInit'},
						{'value':'bkc194',text:'结算期间','type':'date','format':'YYYYMMDD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect codeType="aka130" id="aka130" name="q.aka130" label="业务类型" />
					<powersi:codeselect codeType="bka006" id="bka006" name="q.bka006" label="待遇类型" />
					<powersi:codeselect codeType="bka035" id="bka035" name="q.bka035" label="人员类别" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bkp160" name="q.bkp160" label="申述状态" required="true" codeType="bkp160_zx" headerKey="00" />
					<td class="tdLabel"><select id="col" name="q.col" style="width: 92px;" class="select tdLabel">
							<option value="aac001">个人电脑号</option>
							<option value="aac002">身份证号码</option>
							<option value="aac003">姓名</option>
					</select></td>
					<td><input type="text" id="colVal" name="q.colVal" /></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="业务信息列表" />
			<powersi:tab id="tab2" target="divTab2" label="费用信息列表" />
			<div id="divTab1">
				<powersi:datagrid id="dg_biz" formId="f_query" delayLoad="true" dataAction="local"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize">
					<powersi:datagrid-column name="opt" width="56" render="function(d,i){return '<a class=show_dg_fee index='+i+'>费用信息</a>';}" />
					<powersi:datagrid-column name="bke165" width="150" display="结算方式" />
					<powersi:datagrid-column name="aac003" display="姓名" width="70" />
					<powersi:datagrid-column name="aac002" display="身份证号码" width="147" />
					<powersi:datagrid-column name="aac004" display="性别" width="30" code="aac004" />
					<powersi:datagrid-column name="aae016" display="审核状态" width="70" render="_render" />
					<powersi:datagrid-column name="bkp160" display="申诉标志" code="bkp160_zx" />
					<powersi:datagrid-column name="bka035" display="人员类型" width="80" render="_render" />
					<powersi:datagrid-column name="aka130" display="业务类型" width="80" render="_render" />
					<powersi:datagrid-column name="bka006" display="待遇类型" width="80" render="_render" />
					<powersi:datagrid-column name="akc264" display="医疗总费用" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc465" display="基金总支付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="akc265_mx" display="明细扣付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="akc265_dl" display="大类扣付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc466" display="基本统筹支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc467" display="基本统筹自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc469" display="大病统筹支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc470" display="大病统筹自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc471" display="公务员补助金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc472" display="特殊补贴金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc473" display="生育基金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc474" display="离休基金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc476" display="起付标准金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc477" display="个人账户支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc478" display="超标自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc479" display="现金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc480" display="段外全部自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc481" display="段外部分自付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc483" display="特惠保金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc484" display="民政救助金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc485" display="医院减免金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc486" display="财政兜底金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="bka008" display="所在单位" />
					<powersi:datagrid-column name="aae030" display="入院时间" />
					<powersi:datagrid-column name="aae031" display="出院时间" />
					<powersi:datagrid-column name="akc194" display="结算时间" width="140" />
					<powersi:datagrid-column name="bkz102" display="出院疾病诊断" />
					<powersi:datagrid-column name="aae139" display="异地标志" code="aae139" />
					<powersi:datagrid-column name="aaa027" display="业务中心" code="aaa027" />
					<powersi:datagrid-column name="baa027" display="人员中心" code="aaa027" />
					<powersi:datagrid-column name="daa027" display="拨付中心" code="aaa027" />
					<powersi:datagrid-column name="akb021" display="医院名称" />
					<powersi:datagrid-column name="bke100" display="登账号" />
					<powersi:datagrid-column name="kc21id" display="业务号" />
				</powersi:datagrid>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="dg_fee" url="${ACTION}!queryDeductionFee.action" delayLoad="true"
					isCheckable="function(row){return _toDecimal(row.akc265)>0&&$.isEmptyObject(row.bkp060);}"
					onBeforeEdit="function(p){return _toDecimal(p.record.akc265)>0&&$.isEmptyObject(p.record.bkp060);}" checkbox="true" enabledEdit="true"
					enabledSort="false" showExportExcel2007="true" onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}"
					pageParmName="q.currentPage" pagesizeParmName="q.pageSize">
					<powersi:toolbar>
						<powersi:toolbar-item id="dg_fee_condition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="bkp055" display="医院申述原因" editor="{type:'text'}" width="200" id="bkp055" />
					<powersi:datagrid-column name="bkp060_show" display="中心仲裁结果" hide="true" />
					<powersi:datagrid-column name="bkp059_show" display="中心仲裁结论" hide="true" />
					<powersi:datagrid-column name="ake003" display="类型/大类" />
					<powersi:datagrid-column name="ake002" display="中心费用名称" />
					<powersi:datagrid-column name="bke124" display="费用金额" format="{0,number,###,##0.00}" align="right" />
					<powersi:datagrid-column name="akc265" display="扣付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="bkc370" display="扣付原因" />
					<powersi:datagrid-column name="aae015" display="扣付时间" width="134" />
					<powersi:datagrid-column name="akc226" display="数量" />
					<powersi:datagrid-column name="bka040" display="单价" format="{0,number,###,##0.00}" align="right" />
					<powersi:datagrid-column name="aae016" display="扣付阶段" />
					<powersi:datagrid-column name="bbe014" display="扣付人编号" />
					<powersi:datagrid-column name="ake007" display="费用发生时间" render="_render" />
					<powersi:datagrid-column name="bkp056" display="医院申述时间" render="_render" />
					<powersi:datagrid-column name="bkp058" display="医院申述人" />
					<powersi:datagrid-column name="bkp060" display="中心仲裁结果" />
					<powersi:datagrid-column name="bkp059" display="中心仲裁结论" />
					<powersi:datagrid-column name="bkp061" display="中心仲裁时间" render="_render" />
					<powersi:datagrid-column name="bkp063" display="中心仲裁人" />
					<powersi:datagrid-column name="id" display="ID" id="id" hide="true" />
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>