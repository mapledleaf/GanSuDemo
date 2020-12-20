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
<powersi:head title="体检结算申报" />
<script type="text/javascript">
	//去掉  大于小于包括,并改变顺序
	$.ligerDefaults.Filter.operators['string'] = $.ligerDefaults.Filter.operators['text'] = [ "like", "equal", "notequal", "startwith", "endwith" ];
	$.ligerDefaults.Filter.fields = [ {
		display : '主键',
		name : 'CustomerID'
	} ];
	$(function() {
		/**查询按钮,打印按钮,报表显示iframe,获取明细复选框*/
		var b_query = $("#query"), b_save = $("#save"),  b_print = $("#print"), i_report_sum = $("#report_sum"), c_reset_sum = $("#reset_sum");
		loadClodopModule();
		b_query.click(function() { //检索按钮
			if (!checkFormValidtion()) //校验必填项
				return;
			var param = $("#f_query").serialize();
			param += "&q.reset_sum=" + (c_reset_sum.is(':checked') ? '1' : '0');
			b_query.attr('disabled', true);
			postJSON("${ACTION}!queryAccountDeclareHealth.action", param, function(json) {
				b_query.attr('disabled', false);
				b_print.attr('disabled', true);
				b_save.attr('disabled', true);
				if (json.errortype > 0) {
					popupAlert(json.message, "提示", "error");
					i_report_sum.removeData();
					i_report_sum.attr("src", i_report_sum.attr("src"));
					grid_detail.loadData([]);
				} else {
					i_report_sum.data(json.data);
					i_report_sum.contents().find("body").html(json.data.reportHtml);
					grid_detail.loadData(json.data.details);
					if(json.data.declareFlag == "true"){
						b_save.attr('disabled', false);
					}else{
						b_print.attr('disabled', false);
					}
				}
			});
		});
		
		$("#save").click(function() { //保存申报
			var data = i_report_sum.data();
			if ($.isEmptyObject(data)) {
				popupAlert("无申报内容。", "提示", "info");
				return;
			}
			if (!data.declareFlag || data.declareFlag == "false") {
				popupAlert("您已申报过，请直接打印。", "提示", "info");
				return;
			}
			popupConfirm("是否申报？", "确认", function(k) {
				if (k) {
					postJSON("${ACTION}!confirmAccountDeclareHealth.action", $("#f_query").serialize(), function(json) {
						if (json.errortype > 0) {
							popupAlert(json.message, "提示", "error");
						} else {
							popupAlert("申报成功。", "提示", "info");
							data.declareFlag = "false";
							$("#save").attr('disabled', true);
							$("#print").attr('disabled', false);
						}
					});
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
				name : "体检结算申报汇总"
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
			$.get("${rootPath}/pages/biz/medicare/medicarepay/accountDeclareHealthReport.jsp", function(html) {
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
				<span><input id="reset_sum" type="checkbox" name="q.reset_sum" title="医保中心未开始审核前操作有效" />重新生成申报数据&nbsp;&nbsp;</span>
				<powersi:button id="query" buttonIcon="icon-search" label="检 索" data-hotkey="Alt+Q" />
				<powersi:button id="save" buttonIcon="icon-check" label="申 报" disabled="true" data-hotkey="Alt+S" />
				<powersi:button id="print" buttonIcon="icon-print" label="打 印" disabled="true" data-hotkey="Alt+P" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<mediTag:daterange labelValues="{'value':'akc194',text:'申报时间','format':'YYYY-MM-DD',name:'aae030,aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect id="daa027" label="拨付中心" name="q.daa027" codeType="aaa027" required="true" value="${AAA027}" headerKey=" " />
					<td colspan="2"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="体检结算申报汇总报表" />
			<powersi:tab id="tab2" target="divTab2" label="体检结算申报明细" />
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
					<powersi:datagrid-column name="aac003" display="姓名" width="8%"/>
					<powersi:datagrid-column name="aac004" display="性别" width="5%" render="_render" code="aac004"/>
					<powersi:datagrid-column name="aac002" display="公民身份号码" width="15%"/>
					<powersi:datagrid-column name="akb021" display="体检医院" width="15%" align="left"/>
					<powersi:datagrid-column name="bkh059" display="套餐" width="10%" align="left"/>
					<powersi:datagrid-column name="aab069" display="单位" width="15%" align="left"/>
					<powersi:datagrid-column name="aae019_total" display="总费用" width="8%" totalSummary="{'render': _totalSummary}" align="right"/>
					<powersi:datagrid-column name="aae019_jj" display="基金支付" width="8%" totalSummary="{'render': _totalSummary}" align="right"/>
					<powersi:datagrid-column name="aae019_cash" display="现金支付" width="8%" totalSummary="{'render': _totalSummary}" align="right"/>
					<powersi:datagrid-column name="aae019_qt" display="其他费用" width="8%" totalSummary="{'render': _totalSummary}" align="right"/>
					<powersi:datagrid-column name="bkh016" display="登记日期" width="10%"/>
					<powersi:datagrid-column name="ake010" display="结算日期" width="10%"/>
					<powersi:datagrid-column name="aac001" display="人员ID" width="8%"/>
					<powersi:datagrid-column name="bkh014" display="业务序列号" width="10%"/>
					
					<powersi:datagrid-column name="bkh012" display="体检类别" hide="true"/>
					<powersi:datagrid-column name="bkh017" display="登录状态" hide="true"/>
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>