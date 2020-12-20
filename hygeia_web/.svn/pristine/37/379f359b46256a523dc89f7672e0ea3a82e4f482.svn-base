<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pay" tagdir="/WEB-INF/tags/medicarepay"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.biz.medicare.statics.AAE016"%>
<%@ page import="com.powersi.biz.medicare.statics.BKP160"%>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	request.setAttribute("strStartDate",
			DateFunc.dateToString(DateFunc.getFirstDateOfMonth(calendar.getTime()), "yyyy-MM-dd"));//开始时间
	request.setAttribute("strEndDate",
			DateFunc.dateToString(DateFunc.getLastDateOfMonth(calendar.getTime()), "yyyy-MM-dd"));//结束时间
	request.setAttribute("aaz027", BizHelper.getAaa027());
	String aae016 = request.getParameter("aae016");
	aae016 = UtilFunc.isEmpty(aae016) ? AAE016.AAE016_2 : aae016;
	request.setAttribute("aae016", aae016); //可申诉的费用的审核状态
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/feeAppeal");
%>
<powersi:html>
<powersi:head title="异地暂扣费用申诉" />
<script type="text/javascript">
	var audit_phaseList = [ {
		"id" : "1",
		"text" : "初审"
	}, {
		"id" : "2",
		"text" : "复审"
	}, {
		"id" : "3",
		"text" : "终审"
	} ], sexList = [ {
		"id" : "0",
		"text" : "女"
	}, {
		"id" : "1",
		"text" : "男"
	}, {
		"id" : "2",
		"text" : "未知"
	}, {
		"id" : "9",
		"text" : "其他"
	} ];
	$(function() {
		$("#query").click(function() { //检索按钮
			if (!checkFormValidtion())//校验必填项
				return;
			postJSON("${ACTION}!queryDeductionBizYD.action", $("#f_query").serialize(), function(ret) {
				dg_biz.loadData([]);
				dg_fee.loadData([]);
				$("#divTabs").selTab(0);
				if (ret.errortype > 0) {
					popupAlert(ret.message, "提示", "error");
				} else {
					if (ret.data && ret.data.length > 0) {
						dg_biz.loadData(ret.data);
					} else {
						popupAlert("未查询到数据", "提示", "info");
					}
				}
			});
		});

		$(document).on("click", ".show_dg_fee", function() {
			var index = $(this).attr("index"), row = dg_biz.getRow(index);
			$("#divTabs").selTab(1);
			postJSON("${ACTION}!queryDeductionFeeYD.action", {
				"q.bke100" : row.account_id,
				"q.aaz217" : row.serial_no,
				"q.status" : row.status
			}, function(ret) {
				dg_fee.loadData([]);
				$("#dg_fee").data('rowidx', index);
				if (ret.errortype > 0) {
					popupAlert(ret.message, "提示", "error");
				} else {
					dg_fee.loadData(ret.data);
				}
			});
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
						bke100 : r.account_id,
						bkp055 : dg_fee.hasChanged() ? r.hcyj : bkp055,
						ake003 : r.item_name
					});
				});
				postJSON("${ACTION}!saveFeeAppealYD.action", {
					inputs : JSON.stringify(saveData)
				}, function(ret) {
					if (ret.errortype > 0) {
						popupAlert(ret.message, "提示", "error");
					} else {
						popupAlert("申诉成功。", "提示", "info", function() {
							$('a[index=' + $("#dg_fee").data('rowidx') + '].show_dg_fee').click();
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
				startDate : "${strStartDate}",
				endDate : "${strEndDate}"
			}
		}

		$("#ui-id-1").prepend("<i class='icon-desktop green'>&nbsp;</i>");
		$("#ui-id-2").prepend("<i class='icon-list-alt orange'>&nbsp;</i>");

	});
</script>
<body>
	<powersi:form id="f_query">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<pay:refreshCodetype />
				<powersi:button id="query" buttonIcon="icon-search" label="查 询" data-hotkey="Alt+Q" />
			</powersi:panelbox-toolbar>
			<powersi:hidden id="aae016" value="${aae016}" />
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<mediTag:daterange labelValues="{'value':'akc194',text:'结算时间','format':'YYYY-MM-DD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect id="bkp160" label="申述状态" name="q.bkp160" required="true" codeFilter="data_value in('0','1')" codeType="bkp160" headerKey="00"
						value="<%=BKP160.BKP160_0%>" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="业务信息列表" />
			<powersi:tab id="tab2" target="divTab2" label="费用信息列表" />
			<div id="divTab1">
				<powersi:datagrid id="dg_biz">
					<powersi:datagrid-column name="opt" render="function(d,i){return '<a class=show_dg_fee index='+i+'>费用信息</a>';}" width="56" />
					<powersi:datagrid-column name="name" display="姓名" width="70" />
					<powersi:datagrid-column name="sex" display="性别" width="30" render="_render" />
					<powersi:datagrid-column name="idcard" display="身份证号码" width="130" align="left" />
					<powersi:datagrid-column name="biz_type" display="业务类型" width="80" code="aka130" />
					<powersi:datagrid-column name="treatment_type" display="待遇类型" width="80" code="bka006" />
					<powersi:datagrid-column name="pers_type" display="人员类型" width="80" code="bka035" />
					<powersi:datagrid-column name="corp_name" display="单位名称" align="left" />
					<powersi:datagrid-column name="begin_date" display="入院时间" />
					<powersi:datagrid-column name="fee_money" display="总费用金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="pay_fund" display="基金费用金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="audit_money" display="审核扣付金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="disease" display="出院诊断" align="left" />
					<powersi:datagrid-column name="check_status" display="核查标志" width="80" hide="true" />
					<powersi:datagrid-column name="audit_reason" display="核查原因" align="left" />
					<powersi:datagrid-column name="short_name" display="参保地名称" align="left" />
					<powersi:datagrid-column name="patient_id" display="住院号" />
					<powersi:datagrid-column name="serial_no" display="业务号" />
					<powersi:datagrid-column name="account_id" display="登账号" />
					<powersi:datagrid-column name="status" display="申诉状态" render="_render" />
				</powersi:datagrid>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="dg_fee" checkbox="true" selectRowButtonOnly="true" isCheckable="function(row){return row.status=='0';}">
					<powersi:toolbar>
						<powersi:toolbar-item id="dg_fee_condition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="hcyj" display="申诉原因" editor="{type:'text'}" width="200" />
					<powersi:datagrid-column name="item_name" display="药品项目名称" />
					<powersi:datagrid-column name="audit_reason" display="扣费原因" />
					<powersi:datagrid-column name="audit_phase" display="审核阶段" render="_render" width="80" />
					<powersi:datagrid-column name="audit_money" display="审核扣付金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="fee_money" display="总费用金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="check_status" display="核查状态" hide="true" />
					<powersi:datagrid-column name="money1" display="核查扣付金额" format="{0,number,###,##0.00}" totalSummary="{'render':_totalSummary}" align="right" />
					<powersi:datagrid-column name="disease" display="出院诊断" hide="true" />
					<powersi:datagrid-column name="fin_date" display="出院时间" />
					<powersi:datagrid-column name="name" display="姓名" />
					<powersi:datagrid-column name="patient_id" display="住院号" />
					<powersi:datagrid-column name="account_id" display="登帐号" />
					<powersi:datagrid-column name="hospital_name" display="医疗机构名称" />
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>