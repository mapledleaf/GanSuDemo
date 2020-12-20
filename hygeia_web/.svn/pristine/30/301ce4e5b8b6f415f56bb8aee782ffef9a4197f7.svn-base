<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pay" tagdir="/WEB-INF/tags/medicarepay"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	request.setAttribute("strStartDate",
			DateFunc.dateToString(DateFunc.getFirstDateOfMonth(calendar.getTime()), "yyyy-MM-dd"));//开始时间
	request.setAttribute("strEndDate",
			DateFunc.dateToString(DateFunc.getLastDateOfMonth(calendar.getTime()), "yyyy-MM-dd"));//结束时间
	request.setAttribute("AAA027", BizHelper.getAaa027());
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/accountDeclare");
%>
<powersi:html>
<powersi:codetable id="pers_typeList" codeType="bka035" />
<powersi:codetable id="treatment_typeList" codeType="bka006" />
<powersi:head title="异地结算申报" />
<script type="text/javascript">
	//去掉  大于小于包括,并改变顺序
	$.ligerDefaults.Filter.operators['string'] = $.ligerDefaults.Filter.operators['text'] = [ "like", "equal", "notequal", "startwith", "endwith" ];
	$.ligerDefaults.Filter.fields = [ {
		display : '主键',
		name : 'CustomerID'
	} ];
	$(function() {
		/**查询按钮,打印按钮,报表显示iframe,获取明细复选框*/
		var b_query = $("#query"), b_print = $("#print"), i_report_sum = $("#report_sum"), c_get_detail = $("#get_detail");
		loadClodopModule();
		b_query.click(function() { //检索按钮
			if (!checkFormValidtion()) //校验必填项
				return;
			b_query.attr('disabled', true);
			postJSON("${ACTION}!queryAccountDeclareYD.action", $("#f_query").serialize(), function(json) {
				b_query.attr('disabled', false);
				if (json.errortype > 0) {
					popupAlert(json.message, "提示", "error");
					i_report_sum.removeData();
					i_report_sum.attr("src", i_report_sum.attr("src"));
					grid_detail.loadData([]);
				} else {
					i_report_sum.data(json.data);
					i_report_sum.contents().find("body").html(json.data.reportHtml);
					if (c_get_detail.is(':checked')) {
						grid_detail.loadData(json.data.details);
					} else {
						grid_detail.loadData([]);
					}
				}
			});
		});

		$("#print").click(function() { //打印按钮
			var data = i_report_sum.data();
			if ($.isEmptyObject(data)) {
				popupAlert("无打印内容。", "提示", "warn");
				return;
			}
			i_report_sum.contents().find("body").PowerPrint({
				name : "结算申报汇总"
			}).preview();
		});

		searchBtn = function() {
			grid_detail.showFind();
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
				<span><input id="get_detail" type="checkbox" name="q.get_detail" />是否获取明细&nbsp;&nbsp;</span>
				<pay:refreshCodetype />
				<powersi:button id="query" buttonIcon="icon-search" label="检 索" data-hotkey="Alt+Q" />
				<powersi:button id="print" buttonIcon="icon-print" label="打 印" data-hotkey="Alt+P" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<mediTag:daterange labelValues="{'value':'akc194',text:'申报时间','format':'YYYY-MM-DD',name:'aae030,aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect id="daa027" label="拨付中心" name="q.daa027" codeType="aaa027" required="true" value="${AAA027}" headerKey=" " />
					<powersi:codeselect id="bkp002" label="申报类型" name="q.bkp002" required="true" codeType="bkp002_yd_zx" headerKey=" " />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="结算申报汇总报表" />
			<powersi:tab id="tab2" target="divTab2" label="结算申报明细" />
			<div id="divTab1">
				<div class="frame_div">
					<iframe id="report_sum" style="width: 96%;"></iframe>
				</div>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="grid_detail" showExportExcel2007="true">
					<powersi:toolbar>
						<powersi:toolbar-item id="search" text="高级筛选查找" icon="search2" click="searchBtn" />
					</powersi:toolbar>
					<powersi:datagrid-column name="other_district_name" display="参保地市统筹区" />
					<powersi:datagrid-column name="other_center_name" display="参保地县统筹区" />
					<powersi:datagrid-column name="name" display="姓名" width="70" />
					<powersi:datagrid-column name="pers_type" display="人员类型" width="80" render="_render" />
					<powersi:datagrid-column name="treatment_type" display="待遇类型" width="80" render="_render" />
					<powersi:datagrid-column name="end_date" display="出院日期" />
					<powersi:datagrid-column name="fin_date" display="结算时间" />
					<powersi:datagrid-column name="disease" display="出院诊断" />
					<powersi:datagrid-column name="in_days" display="住院天数" width="80" />
					<powersi:datagrid-column name="total_fee" display="医疗费用总金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name=" " display="基本医疗保险">
						<powersi:datagrid-column name="calc_ylblzf" display="比例自付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
						<powersi:datagrid-column name="base_001_pay" display="统筹支付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}"
							align="right" />
					</powersi:datagrid-column>
					<powersi:datagrid-column name=" " display="大病互助">
						<powersi:datagrid-column name="calc_dbblzf" display="比例自付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
						<powersi:datagrid-column name="calc_dbbltc" display="大病支付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					</powersi:datagrid-column>
					<powersi:datagrid-column name="all_301_pay" display="公务员补助" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="all_401_pay" display="照顾基金" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="all_901_pay" display="意外伤害基金" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}"
						align="right" />
					<powersi:datagrid-column name="all_003_pay" display="个人账户" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name="all_990_pay" display="其他基金" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					<powersi:datagrid-column name=" " display="超大病段金额">
						<powersi:datagrid-column name="exceed_self_pay" display="比例自付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}"
							align="right" />
						<powersi:datagrid-column name="exceed_301_pay" display="统筹支付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}"
							align="right" />
					</powersi:datagrid-column>
					<powersi:datagrid-column name=" " display="封顶补助">
						<powersi:datagrid-column name="speical_self" display="个人自付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}"
							align="right" />
						<powersi:datagrid-column name="speical_pay" display="统筹支付" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}" align="right" />
					</powersi:datagrid-column>
					<powersi:datagrid-column name="calc_total_pay" display="合计基金支付金额" format="{0,number,###,##0.00}" totalSummary="{'render': _totalSummary}"
						align="right" />
					<powersi:datagrid-column name="patient_id" display="住院号" />
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>