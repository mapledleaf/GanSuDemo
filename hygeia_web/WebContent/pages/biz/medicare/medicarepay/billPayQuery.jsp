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
	
	//danr-start:TS19031900088 - 【需求开发】结算申报单格式调整
	//修改说明：默认查当月
	//修改人：阮丹
	//修改时间：2019-03-19
	calendar.add(Calendar.MONTH, 0);
	request.setAttribute("aae030",DateFunc.dateToString(DateFunc.getFirstDateOfMonth(calendar.getTime()), "yyyyMMdd"));
	//danr-end:TS19031900088
	
	//start:TS19032600105 - 【需求开发】医疗机构客户端拨付单查询条件设置
	//修改说明：
	//拨付中心取值，无值时取账号默认中心 
	//取当期月份
	//修改人：鲁斌
	//修改时间：2019-03-27
	
	//TS19052300049 【需求开发】结算云（医院端）【拨付单查询】界面查询条件默认为请选择  陈洁  20190528
	request.setAttribute("daa027_default", BizHelper.getAaa027());
	request.setAttribute("current_month", DateFunc.dateToString(calendar.getTime(), "yyyyMM"));
	//end:TS19032600105
	
	request.setAttribute("totalSummary", "{render:_totalSummary}"); //货币类型字段统计函数
	request.setAttribute("format", "{0,number,###,##0.00}"); //货币类型字段格式
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/billQuery");
%>
<powersi:html>
<!-- 拨付类型码表 -->
<powersi:codetable id="bke127List" codeType="bke127_zx" />
<powersi:head title="拨付单查询" />
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
				"q.bke126" : row.bke126,
				"q.getBranch" : $("#getBranch").is(':checked') ? '1' : '0'
			};
			dg_detail.setParms(parms);
			tabs.selTab(1);
			dg_detail.loadData();
			dg_biz.setParms($.extend(true, {}, parms));
			dg_biz.loadData();
		});
		tabs.tabs("disable", 4);
		onSuccess_dg_detail = function(data, grid) {
			var r1body = $("#report_1").contents().find("body"), r2body = $("#report_2").contents().find("body");
			var r1Html = data.rows && data.rows.length > 0 && data.rows[0]["reportHtml"] ? data.rows[0]["reportHtml"] : null;
			var r2Html = data.rows && data.rows.length > 0 && data.rows[0]["reportDetailHtml"] ? data.rows[0]["reportDetailHtml"] : null;
			if (data.errortype > 0) {
				r1body.html("");
				r2body.html("");
				popupAlert(data.message, "提示", "error");
			} else {
				_x = r1Html ? r1body.html(r1Html) : null;
				_x = r2Html ? r2body.html(r2Html) : null;
				_x = r2Html ? tabs.tabs("enable", 4) : tabs.tabs("disable", 4);
			}
		}
		sumViewDetail = function() {
			var rows = dg_sum.getSelectedRows(), parms = {
				"q.bke126" : "",
				"q.getBranch" : $("#getBranch").is(':checked') ? '1' : '0'
			};
			if (rows.length == 0) {
				popupAlert("请勾选需要汇总查看的拨付单。", "提示", "error");
				return;
			}
			$.each(rows, function(i, r) {
				parms["q.bke126"] += parms["q.bke126"] ? "," + r.bke126 : r.bke126;
			});
			dg_detail.setParms(parms);
			tabs.selTab(1);
			dg_detail.loadData();
			dg_biz.setParms($.extend(true, {}, parms));
			dg_biz.loadData();
		}
		confirmBill = function() {
			var data = [];
			$.each(dg_sum.getSelectedRows(), function(i, r) {
				data.push({
					bke126 : r.bke126
				});
			});
			if (data.length > 0)
				postJSON("${ACTION}!confirmBill.action", {
					datas : JSON.stringify(data)
				}, function(json) {
					if (json.errortype > 0) {
						popupAlert(json.message, "提示", "error");
					} else {
						popupAlert("拨付单确认成功。", "提示", "info", function() {
							query.click();
						});
					}
				});
			else
				popupAlert("请勾选需要确认的拨付单。", "提示", "error");
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

		var items = {};
		$.each(bke127List, function(i, item) {
			if (item.id == '2' || item.id == '3' || item.id == '4')
				items[item.id] = item.text;
		});
		_addSelectItems("bke127", items);
		_removeSelectItems("bke142", "A");

		$("#ui-id-1").prepend("<i class='icon-desktop green'>&nbsp;</i>");
		$("#ui-id-2").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");
		$("#ui-id-3").prepend("<i class='icon-bar-chart orange'>&nbsp;</i>");
		$("#ui-id-4").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");

		querySettlementCycle();
	});

	//start:TS19032600105 - 【需求开发】医疗机构客户端拨付单查询条件设置
	//修改说明：
	//获取获取结算周期
	//修改人：鲁斌
	//修改时间：2019-03-27
	function querySettlementCycle(){
		var field =  ${current_month};
		var daa027 = $("#daa027").val() ;
		if(daa027 == ""){
			daa027 = ${daa027_default};
		}
		var aae140 = $("#aae140").val();
		postJSON("${ACTION}!querySettlementCycle.action", {
			"field":field,"daa027":daa027,"aae140":aae140
		}, function(json) {
			if (json.errortype > 0) {
				popupAlert(json.message, "提示", "error");
			} else {
				var start_date = json.data.start_date;
				var end_date = ${aae031};//end_date 取当期日期
				$("#_config-range").val(start_date + $("#_config-range").data('daterangepicker').locale.separator + end_date)
				$("input[name='q.aae030']").val(start_date);
				$("input[name='q.aae031']").val(end_date);
			}
		});
	}
	//end:TS19032600105
</script>
<body>
	<powersi:form id="f_query" action="/medicarepay/billQuery!queryBill.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<span><input id="getBranch" type="checkbox" checked="checked" name="q.getBranch" />同时获取分店(院)&nbsp;&nbsp;</span>
				<pay:refreshCodetype />
				<powersi:button id="query" buttonIcon="icon-search" label="查询" data-hotkey="Alt+Q" />
				<powersi:button id="print" buttonIcon="icon-print" label="打印报表" data-hotkey="Alt+P" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row cols="8">
					<mediTag:daterange name="q.field"
						labelValues="{'value':'a.aae036',text:'制单时间','type':'date','format':'YYYYMMDD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'},
						{'value':'c.akc194',text:'结算时间','type':'date','format':'YYYYMMDD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect id="daa027" name="q.daa027" label="拨付中心" codeType="aaa027" onchange="querySettlementCycle()"/>
					<powersi:codeselect id="aae140" name="q.aae140" label="险种" codeType="aae140" onchange="querySettlementCycle()"/>
					<!-- 
						start:TS19041000148 - 【需求开发】医疗机构客户端拨付单查询条件设置
						修改说明：默认设置为未审核
						修改人：lubin
						修改时间：2019-04-12
					 -->
					<powersi:codeselect id="aae016" name="q.aae016" label="审核状态" codeType="kcb4_aae016" />
					<!-- 
						end:TS19041000148 - 【需求开发】医疗机构客户端拨付单查询条件设置
					 -->
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bke142" name="q.bke142" label="拨付状态" codeType="bke142" value="0"/>
					<powersi:codeselect id="bke127" name="q.bke127" label="拨付类型" codeType="bke127" />
					<powersi:textfield id="bke126" name="q.bke126" label="拨付单号" />
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
			<powersi:tab id="tab5" target="divTab5" label="基金结算明细报表" />
			<div id="divTab1">
				<powersi:datagrid id="dg_sum" formId="f_query" delayLoad="true" checkbox="true" selectRowButtonOnly="true" enabledSort="false"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize">
					<powersi:toolbar>
						<powersi:toolbar-item icon="view" click="sumViewDetail" text="汇总查看明细" />
						<powersi:toolbar-item icon="tick1" click="confirmBill" text="拨付单确认" />
					</powersi:toolbar>
					<powersi:datagrid-column display="拨付单号" name="bke126" render="function(d,i,v){return '<a class=show_dg_detail index='+i+'>'+v+'</a>';}" />
					<powersi:datagrid-column display="拨付中心" name="daa027" code="aaa027" align="left" width="90" />
					<powersi:datagrid-column display="申报金额" name="bke150" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="应拨金额" name="akb094" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="实拨金额" name="akb081" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="预留金额" name="akb090" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="业务扣付金额" name="bke152" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="补/扣金额" name="bke153" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="总拨付人次" name="bke131" totalSummary="${totalSummary}" width="90" />
					<powersi:datagrid-column display="违规人次" name="bke132" totalSummary="${totalSummary}" width="90" />
					<powersi:datagrid-column display="拨付状态" name="bke142" code="bke142" width="80" />
					<powersi:datagrid-column display="结算开始时间" name="bke148" width="80" />
					<powersi:datagrid-column display="结算结束时间" name="bke149" width="80" />
					<powersi:datagrid-column display="制单人" name="aae011" />
					<powersi:datagrid-column display="制单时间" name="aae036" render="_render" />
					<powersi:datagrid-column display="审核状态" name="aae016_" width="60" />
					<powersi:datagrid-column display="审核人" name="aae014" />
					<powersi:datagrid-column display="审核时间" name="aae015" render="_render" />
					<powersi:datagrid-column display="对账状态" name="bke042" render="function(r,i,v,c){return v==null||v==0?'未确认':'已确认';}" width="60" />
					<powersi:datagrid-column display="报盘阶段" name="bke146_" width="80" />
					<powersi:datagrid-column display="拨付类型" name="bke127" code="bke127_zx" width="80" />
					<powersi:datagrid-column display="拨付对象名称" name="bke130" align="left" width="240" />
					<powersi:datagrid-column display="险种类型" name="aae140" code="aae140" align="left" />
					<powersi:datagrid-column display="汇总拨付单号" name="bke145" />
					<powersi:datagrid-column display="结算类型" name="bkp002" hide="true" width="80" />
				</powersi:datagrid>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="dg_detail" url="${ACTION}!queryBillDetail.action" delayLoad="true" onSuccess="onSuccess_dg_detail" enabledSort="false"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize">
					<powersi:datagrid-column display="人员所属中心" name="baa027" code="aaa027" align="left" width="90" />
					<powersi:datagrid-column display="业务类型" name="aka130" code="aka130" width="80" />
					<powersi:datagrid-column display="待遇类型" name="bka006" code="bka006" width="80" />
					<powersi:datagrid-column display="人员类别" name="bka035" code="bka035" width="80" />
					<powersi:datagrid-column display="单位类别" name="aab019" code="aab019_zx" />
					<powersi:datagrid-column display="基金款项" name="aaa157" code="aaa157_zx" width="90" />
					<powersi:datagrid-column display="申报金额" name="bke150" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="应拨金额" name="akb094" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="实拨金额" name="akb081" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="预留金额" name="akb090" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="业务扣付金额" name="bke152" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="补/扣金额" name="bke153" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="定额调节金额" name="bke154" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="预留审核扣减金额" name="bke164" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="医疗机构名称" name="akb021" width="240" />
				</powersi:datagrid>
			</div>
			<div id="divTab3">
				<div class="frame_div">
					<iframe id="report_1" style="width: 96%;"></iframe>
				</div>
			</div>
			<div id="divTab4">
				<powersi:datagrid id="dg_biz" url="${ACTION}!queryBillBiz.action" delayLoad="true"
					onLoadedData="function(dg){if(dg.getTotal()>0){dg.setParm('q.totalResult',dg.getTotal());}}" pageParmName="q.currentPage"
					pagesizeParmName="q.pageSize" enabledSort="false">
					<powersi:datagrid-column display="姓名" name="aac003" />
					<powersi:datagrid-column display="医疗费用金额" name="aae019" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="基金支付金额" name="bkc465" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="现金支付金额" name="bkc479" format="${format}" totalSummary="${totalSummary}" align="right" />
					<powersi:datagrid-column display="床日数" name="bka030" render="function(d,i,v){return v?v:'0';}" totalSummary="${totalSummary}" width="50" />
					<powersi:datagrid-column display="结算日期" name="akc194" />
					<powersi:datagrid-column display="单位名称" name="bka008" align="left" />
					<powersi:datagrid-column display="单位类型" name="aab019" code="aab019_zx" />
					<powersi:datagrid-column display="人员类别" name="bka035" code="bka035" width="80" />
					<powersi:datagrid-column display="业务类型" name="aka130" code="aka130" width="80" />
					<powersi:datagrid-column display="待遇类型" name="bka006" code="bka006" width="80" />
					<powersi:datagrid-column display="诊断疾病" name="bkz102" align="left" />
					<powersi:datagrid-column display="业务序列号" name="aaz217" />
					<powersi:datagrid-column display="结算申报批次" name="bke403" />
					<powersi:datagrid-column display="个人编号" name="aac001" hide="true" />
				</powersi:datagrid>
			</div>
			<div id="divTab5">
				<div class="frame_div">
					<iframe id="report_2" style="width: 96%;"></iframe>
				</div>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>