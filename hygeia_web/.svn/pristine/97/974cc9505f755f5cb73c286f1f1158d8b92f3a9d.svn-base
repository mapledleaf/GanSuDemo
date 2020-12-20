<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="pay" tagdir="/WEB-INF/tags/medicarepay"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	Calendar calendar = Calendar.getInstance();
	request.setAttribute("aae031", DateFunc.dateToString(calendar.getTime(), "yyyyMMdd"));
	calendar.add(Calendar.MONTH, 0);
	request.setAttribute("aae030",DateFunc.dateToString(DateFunc.getFirstDateOfMonth(calendar.getTime()), "yyyyMMdd"));
	request.setAttribute("daa027_default", BizHelper.getAaa027());
	request.setAttribute("current_month", DateFunc.dateToString(calendar.getTime(), "yyyyMM"));
	
	request.setAttribute("totalSummary", "{render:_totalSummary}"); //货币类型字段统计函数
	request.setAttribute("format", "{0,number,###,##0.00}"); //货币类型字段格式
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/billQuery");
%>
<powersi:html>
<powersi:head title="体检拨付单查询" />
<powersi:codetable id="aac004List" codeType="aac004" />
<script type="text/javascript">
	$(function() {
		loadClodopModule();
		var tabs = $("#divTabs"), query = $("#query");
		query.click(function() {
			if (checkFormValidtion()) { //校验必填项
				setTimeout(function() {
					dg_sum.loadForm(f_query);
					tabs.selTab(0);
					dg_detail.reset();
					dg_biz.reset();
					$("#report_1").contents().find("body").html("");
				}, 100);
			}
		});
		$(document).on("click", ".show_dg_detail", function() {
			var row = dg_sum.getRow($(this).attr("index")), parms = {
				"q.bke126" : row.bke126
			};
			dg_detail.setParms(parms);
			tabs.selTab(1);
			dg_detail.loadData();
			dg_biz.setParms($.extend(true, {}, parms));
			dg_biz.loadData();
		});
		tabs.tabs("disable", 4);
		onSuccess_dg_detail = function(data, grid) {
			var r1body = $("#report_1").contents().find("body");
			var r1Html = data.rows && data.rows.length > 0 && data.rows[0]["reportHtml"] ? data.rows[0]["reportHtml"] : null;
			if (data.errortype > 0) {
				r1body.html("");
				popupAlert(data.message, "提示", "error");
			} else {
				_x = r1Html ? r1body.html(r1Html) : null;
			}
		}
		$("#print").click(function() {
			var div = $("div[id^=divTab][aria-hidden=false]");
			var name = $("a[href=#" + div.attr("id") + "]").attr("title");
			var iframe = div.find("iframe");
			if (iframe.contents().find("body").html()) {
				$(iframe.prop('contentWindow').document).find("body").PowerPrint({
					name : name
				}).preview();
			} else {
				popupAlert("无打印内容。", "提示", "warn");
			}
		});
		
		daterangeInit = function() {
			var range = $("#_config-range").val(), drp = $("#_config-range").data('daterangepicker');
			return {
				startDate : range ? range.split(drp.locale.separator)[0] : "${aae030}",
				endDate : range ? range.split(drp.locale.separator)[1] : "${aae031}"
			}
		}

		_removeSelectItems("bke142", "A");

		$("#ui-id-1").prepend("<i class='icon-desktop green'>&nbsp;</i>");
		$("#ui-id-2").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");
		$("#ui-id-3").prepend("<i class='icon-bar-chart orange'>&nbsp;</i>");
		$("#ui-id-4").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");
	});

</script>
<body>
	<powersi:form id="f_query" action="/medicarepay/billQuery!queryBillHealth.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="query" buttonIcon="icon-search" label="查询" data-hotkey="Alt+Q" />
				<powersi:button id="print" buttonIcon="icon-print" label="打印报表" data-hotkey="Alt+P" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row cols="8">
					<mediTag:daterange name="q.field"
						labelValues="{'value':'a.aae036',text:'制单时间','type':'date','format':'YYYYMMDD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'},
						{'value':'c.ake010',text:'结算时间','type':'date','format':'YYYYMMDD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect id="daa027" name="q.daa027" label="拨付中心" codeType="aaa027" />
					<powersi:codeselect id="aae016" name="q.aae016" label="审核状态" codeType="kcb4_aae016" />
					<td colspan="2"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bke142" name="q.bke142" label="拨付状态" codeType="bke142" value="0"/>
					<powersi:textfield id="bke126" name="q.bke126" label="拨付单号" />
					<td colspan="4"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" title="报表列表">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="拨付结算信息" />
			<powersi:tab id="tab2" target="divTab2" label="基金结算明细" />
			<powersi:tab id="tab3" target="divTab3" label="基金结算报表" />
			<powersi:tab id="tab4" target="divTab4" label="业务明细信息" />
			<div id="divTab1">
				<powersi:datagrid id="dg_sum" formId="f_query" delayLoad="true" checkbox="false" selectRowButtonOnly="true" enabledSort="false"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize">
					<powersi:datagrid-column display="拨付单号" name="bke126" render="function(d,i,v){return '<a class=show_dg_detail index='+i+'>'+v+'</a>';}" />
					<powersi:datagrid-column display="拨付中心" name="daa027" code="aaa027" align="left" width="90" />
					<powersi:datagrid-column display="申报金额" name="pay_money" format="${format}" totalSummary="${totalSummary}" align="right"/>
					<powersi:datagrid-column display="补扣金额" name="penalty_money" format="${format}" totalSummary="${totalSummary}" align="right"/>
					<powersi:datagrid-column display="实际拨付金额" name="fact_pay_money" format="${format}" totalSummary="${totalSummary}" align="right"/>
					<powersi:datagrid-column display="总拨付人次" name="bke131" totalSummary="${totalSummary}" width="90"  />
					<powersi:datagrid-column display="结算开始时间" name="bke148" width="80" />
					<powersi:datagrid-column display="结算结束时间" name="bke149" width="80" />
					<powersi:datagrid-column display="制单人" name="aae011" />
					<powersi:datagrid-column display="制单时间" name="aae036" render="_render" />
					<powersi:datagrid-column display="审核状态" name="aae016_" width="60" />
					<powersi:datagrid-column display="审核人" name="aae014" />
					<powersi:datagrid-column display="审核时间" name="aae015" render="_render" />
					<powersi:datagrid-column display="拨付状态" name="bke142" code="bke142" width="80" />
				</powersi:datagrid>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="dg_detail" url="${ACTION}!queryBillHealthDetail.action" delayLoad="true" onSuccess="onSuccess_dg_detail" enabledSort="false"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize">
					<powersi:datagrid-column display="拨付单号" name="bke126" minWidth="200"/>
					<powersi:datagrid-column display="基金类别" name="aaa157" code="aaa157_zx" width="90" />
					<powersi:datagrid-column display="人员类别" name="bka035" code="bka035" width="80" />
					<powersi:datagrid-column display="记账金额" name="pay_money" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="本次应拨金额" name="ought_pay_money" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="预留金额" name="remain_money" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="补/扣金额" name="penalty_money" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="实际应拨金额" name="fact_pay_money" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="医疗机构名称" name="akb021" width="240" align="left"/>	
				</powersi:datagrid>
			</div>
			<div id="divTab3">
				<div class="frame_div">
					<iframe id="report_1" style="width: 96%;"></iframe>
				</div>
			</div>
			<div id="divTab4">
				<powersi:datagrid id="dg_biz" url="${ACTION}!queryBillHealthBiz.action" delayLoad="true"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize" enabledSort="false">
					<powersi:datagrid-column display="个人编号" name="aac001" />
					<powersi:datagrid-column display="姓名" name="aac003" width="70"/>
					<powersi:datagrid-column display="身份证号" name="aac002" width="200"/>
					<powersi:datagrid-column display="单位编号" name="aab999" />
					<powersi:datagrid-column display="单位名称" name="aab069" minWidth="200" align="left" />
					<powersi:datagrid-column display="人员类别" name="bka035" code="bka035" width="70"/>
					<powersi:datagrid-column display="公务员级别" name="office_grade_name" />
					<powersi:datagrid-column display="性别" name="aac004" render="_render" width="50" />
					<powersi:datagrid-column display="登记日期" name="bkh016" />
					<powersi:datagrid-column display="结束日期" name="ake010" />
					<powersi:datagrid-column display="总金额" name="all_money" format="${format}" totalSummary="${totalSummary}" align="right"/>
					<powersi:datagrid-column display="基金支付" name="fund_money" format="${format}" totalSummary="${totalSummary}" align="right"/>
					<powersi:datagrid-column display="现金" name="cash_money" format="${format}" totalSummary="${totalSummary}" align="right"/>
					<powersi:datagrid-column display="医疗服务机构名称" name="akb021" align="left" />	
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>