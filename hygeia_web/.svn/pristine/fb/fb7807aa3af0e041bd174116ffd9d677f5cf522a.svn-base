<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<html>
<powersi:head title="体检费用申报" />
<%
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	Date date = calendar.getTime();
	SimpleDateFormat dformat = new SimpleDateFormat("yyyyMM");
	request.setAttribute("hospitalName", BizHelper.getAkb021());//医院名称
	request.setAttribute("strStartDate", dformat.format(DateFunc.getFirstDateOfMonth(date)));//开始时间
	request.setAttribute("strEndDate", dformat.format(date));//结束时间
%>
<style type="text/css">
.panelBoxHeader .icon-refresh {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	var params = {};
	var resultData = {};
	$(function() {
		loadClodopModule();
		$("#akb021").val("${hospitalName}");
		$("#aae030").val("${strStartDate}");
		$("#aae031").val("${strEndDate}");
		_removeSelectItems("daa027", "430600");

		$(".panelBoxHeader .icon-refresh").click(function() {
			popupConfirm("刷新结算申报类型需要刷新当前页面，确定需要刷新吗？", "确认", function(isOk) {
				if (isOk) {
					$.get("${rootPath}/RefreshCodeTable", null, function() {
						location.reload();
					});
				}
			});
		});

		//检索按钮
		$("#query").click(
				function() {
					//校验必填项
					if (!checkFormValidtion()) {
						return;
					}
					var query = $(this);
					query.attr('disabled', true)
					params = _getParams();
					params.reset_sum = $("#reset_sum").is(':checked') ? '1' : '0';
					params.get_detail = $("#get_detail").is(':checked') ? '1' : '0';
					postJSON(rootPath + "/medicare/accountDeclare/exec.action", {
						handle : '${ACCOUNT_DECLARE_SEARCH}',
						params : JSON.stringify(params)
					}, function(json) {
						query.attr('disabled', false);
						if (json.errortype == '0') {
							resultData = json.data;
							$.each(json.data.reportIDS, function(i, n) {
								var iframe = $("#report_" + i);
								_showReport(iframe, n);
							});
							if (resultData.bke403s)
								$("#save").click();
						} else {
							var alertType = /^系统正在统计本医院的申报数据.*$/.test(json.message) ? "warn"
									: /^未统计到需要申报的数据.*/.test(json.message) ? "info" : "error";
							popupAlert(json.message, "提示", alertType, function() {
								$("#reset_sum").attr("checked", false);
								resultData.bke403s = null;
								$.each($("iframe"), function(i, iframe) {
									$(iframe).attr("reportID", '');
									iframe.contentWindow.location.reload(true);
								});
							});
						}
					});
				});

		//保存申报备注信息
		$("#save").click(function() {
			if (!$("#report_0").attr("reportID")) {
				popupAlert("无申报内容", "提示", "info");
				return;
			}
			if (!resultData.bke403s || resultData.bke403s.length == 0) {
				popupAlert("您已申报过，请直接打印或导出", "提示", "info");
				return;
			}
			var aae013 = $.trim($("#aae013").val());
			popupConfirm(resultData.checked + "是否申报批次号为[" + resultData.bke403s + "]的数据？", "确认", function(k) {
				if (k) {
					params.bke403s = resultData.bke403s;
					params.aae013 = aae013;
					postJSON(rootPath + "/medicare/accountDeclare/exec.action", {
						handle : '${ACCOUNT_DECLARE_CONFIRM}',
						params : JSON.stringify(params)
					}, function(json) {
						if (json.errortype > 0) {
							popupAlert(json.message, "提示", "error");
						} else {
							popupAlert("申报成功。", "提示", "info");
							resultData.bke403s = "";
						}
					});
				}
			});
		});

		//打印按钮
		$("#print").click(function() {
			var div = $("div[id^=divTab][aria-hidden=false]");
			var name = $("a[href=#" + div.attr("id") + "]").attr("title");
			var iframe = div.find("iframe");
			var reportID = iframe.attr("reportID");
			if (reportID && "none" != reportID) {
				if (resultData.bke403s) {
					popupAlert("包含未申报数据无法打印", "提示", "warn");
				} else {
					$(iframe.prop('contentWindow').document).find("body").PowerPrint({
						name : name
					}).preview();
				}
			} else {
				popupAlert("无打印内容。", "提示", "warn");
			}
		});

		//导出按钮
		$("#export").click(function() {
			var reportID = $("div[id^=divTab][aria-hidden=false] iframe").attr("reportID");
			if (reportID && "none" != reportID) {
				if (resultData.bke403s) {
					popupAlert("包含未申报数据无法导出", "提示", "warn");
				} else {
					location.href = rootPath + "/downloadReportFileServlet.download?reportID=" + reportID;
				}
			} else {
				popupAlert("无导出内容。", "提示", "warn");
			}
		});

		$("#aka140").change(function() {
			var txt = $(this).find("option:selected").text();
			if (/.*住院.*/.test(txt)) {
				$("a[href=#divTab3]").show();
			} else {
				$("a[href=#divTab3]").hide();
			}
		});

	});
</script>
<body>
	<powersi:panelbox key="panel_query" title="查询条件">
		<powersi:form>
			<powersi:panelbox-toolbar>
				<span><font color="red">提示：已退费的业务不参与申报</font>&nbsp;&nbsp;</span>
				<span><input id="reset_sum" type="checkbox" title="医保中心未开始审核前操作有效" />重新生成申报数据&nbsp;&nbsp;</span>
				<span><input id="get_detail" type="checkbox" />是否获取明细&nbsp;&nbsp;</span>
				<i class="icon-refresh" title="刷新结算申报类型"></i>&nbsp;&nbsp;
				<powersi:button id="query" buttonIcon="icon-search" label="检 索" />
				<powersi:button id="save" buttonIcon="icon-check" label="申 报" />
				<powersi:button id="print" buttonIcon="icon-print" label="打 印" />
				<powersi:button id="export" buttonIcon="icon-download" label="导 出" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:codeselect codeType="caa027" id="caa027" required="true" headerKey="00" label="中心系统" />
					<powersi:codeselect id="aka140" label="申报类型" cssClass="select2" required="true" codeType="decl_type" />
					<powersi:textfield id="aae030" label="申报期间" mask="date" required="true" maxlength="6" format="dateFmt:'yyyyMM'" />
					<powersi:textfield id="aae031" label="至" mask="date" required="true" maxlength="6" format="dateFmt:'yyyyMM'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="daa027" label="拨付中心" codeType="aaa027list" required="true" value="<%=BizHelper.getAaa027()%>" />
					<powersi:codeselect id="detail_orderby" label="明细排序" codeType="detail_orderby" headerKey="00" />
					<powersi:textfield id="aae013" label="申报说明" colspan="3" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:form>
	</powersi:panelbox>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="结算申报汇总表" />
			<powersi:tab id="tab2" target="divTab2" label="结算申报明细表" />
			<powersi:tab id="tab3" target="divTab3" label="延伸费用明细表" />
			<div id="divTab1">
				<div class="frame_div">
					<iframe id="report_0" style="width: 96%;"></iframe>
				</div>
			</div>
			<div id="divTab2">
				<div class="frame_div">
					<iframe id="report_1" style="width: 96%;"></iframe>
				</div>
			</div>
			<div id="divTab3" style="display: none;">
				<div class="frame_div">
					<iframe id="report_2" style="width: 96%;"></iframe>
				</div>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</html>