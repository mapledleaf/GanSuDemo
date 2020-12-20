<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.UtilFunc"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%
	request.setAttribute("str_date_beg",DateFunc.dateToString(DateFunc.getFirstDateOfMonth(DateFunc.getDate()), "yyyyMMdd"));
	request.setAttribute("str_date_end", DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
	request.setAttribute("loginUser", BizHelper.getLoginUser());
	request.setAttribute("userName", BizHelper.getUserName());
	request.setAttribute("ACTION", request.getContextPath() + "/medicarepay/accountCancelApply");
%>
<powersi:html>
<powersi:head title="取消登账申请" />
<powersi:codetable id="aac004List" codeType="aac004" />
<powersi:codetable id="aae139List" codeType="aae139" />
<powersi:codetable id="aaa027List" codeType="aaa027" />
<powersi:codetable id="aae016List" codeType="aae016_zx" />
<script type="text/javascript">
	var info = {};
	$(function() {
		$("#query").click(function() {
			var params = {};
			if (checkFormValidtion()) { //校验必填项
				setTimeout(function() {
					$("input[name='q.pageSize']").val(dg_biz.options.pageSize);
					$("input[name='q.currentPage']").val(dg_biz.options.page);
					dg_biz.loadForm(f_query);
					$("#divTabs").selTab(0);
					dg_apply.loadData([]);
				}, 100);
			}
			if ("0" == $("#bkp161").val()) {
				dg_biz.toggleCol("operate", false);
				$("div.l-panel-topbar").show();
			} else {
				dg_biz.toggleCol("operate", true);
				$("div.l-panel-topbar").hide();
			}
		});
		$(document).on("click", ".appeal-save", function() {
			var saveData = [], bkp055 = liger.get("bkp055").getValue();
			_datagridEditValidation(dg_biz, null, function(data) {
				if (data.length == 0) {
					data = dg_biz.getSelectedRows();
					if (data.length == 0) {
						popupAlert("请勾选需要取消登账申请的数据。", "提示", "error");
						return;
					}
					if (!bkp055) {
						popupAlert("请输入申请原因。", "提示", "error", function() {
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
						bkp055 : dg_apply.hasChanged() ? r.bkp055 : bkp055,
						bkp056 : new Date().format("yyyyMMdd hh:mm:ss"),
						bkp057 : '${loginUser}',
						bkp058 : '${userName}'
					});
				});
				postJSON("${ACTION}!saveAccountCancelApply.action", {
					datas : JSON.stringify(saveData)
				}, function(json) {
					if (json.errortype > 0) {
						popupAlert(json.message, "提示", "error");
					} else {
						popupAlert("申请成功。", "提示", "info", function() {
							dg_biz.deleteSelectedRow();
							dg_apply.loadData([]);
						});
					}
				});
			});
		});
		setTimeout(function() {
			dg_biz.bindCondition($.extend({}, $.ligerDefaults.Form.custom, {
				labelWidth : 100,
				inputWidth : 100,
				showSearch : false,
				showClose : false,
				fields : [ {
					id : 'bkp055',
					name : 'bkp055',
					label : "申请原因",
					type : "text",
					width : 500,
					newline : false,
					required : true
				} ],
				onAfterSetFields : function() {
					var id = this.id;
					var btnHtml = '<li title="申请"><div class="l-button appeal-save">';
					btnHtml += '<i class="icon-comments"></i><span>申请</span></div></li>';
					setTimeout(function() {
						$("#" + id + " > div > ul").append(btnHtml);
					}, 50);
				}
			}), "dg_apply_condition");
		}, 100);

		daterangeInit = function() {
			return {
				startDate : "${str_date_beg}",
				endDate : "${str_date_end}"
			}
		}

	});
	
	function renderBkp161(row, index, value) {
		var str = value;
		if(value == "0" || value == ""){
			str = "无申请";
		}else if(value == "1"){
			str = "申请完成";
		}else if(value == "2"){
			str = "申请中";
		}
		return str;
	}
	
	function renderBkp060(row, index, value) {
		var str = value;
		if(value == "0" || value == ""){
			str = "未审批";
		}else if(value == "1"){
			str = "审批同意";
		}else if(value == "2"){
			str = "审批不同意";
		}
		return str;
	}
	
	function renderOperate(row, index, value){
		var a = [];
		if(row['bkp161'] == "1" || row['bkp161'] == "2"){
			a.push('<input type="button" value="查看申请" class="linkButton"');
			a.push(' onclick="popupDetail(');
			a.push(index);
			a.push(')"');
			a.push(" />");
		}
		return a.join('');
	}
	
	function popupDetail(index){
		var row = dg_biz.getRow(index);
		if(row){
			dg_apply.setParms({
				"q.bke100" : row.bke100,
				"q.currentPage" : dg_apply.options.page,
				"q.pageSize" : dg_apply.options.pageSize
			});
			$("#divTabs").selTab(1);
			dg_apply.loadData();
		}
		var e = event || window.event|| arguments.callee.caller.arguments[0];
		e.stopPropagation();
	}
	
	function renderBizCheckAble(row){
		if(row['bkp161'] == "1" || row['bkp161'] == "2"){
			return false;
		}
		return true;
	}
</script>
<body>
	<powersi:form id="f_query" action="/medicarepay/accountCancelApply!queryAccountBiz.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="query" buttonIcon="icon-search" label="查 询" data-hotkey="Alt+Q"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<input type="hidden" name="q.pageSize" />
				<input type="hidden" name="q.currentPage" />
				<powersi:editorlayout-row>
					<mediTag:daterange
						labelValues="{'value':'bkc194',text:'结算期间','type':'date','format':'YYYYMMDD',name:'q.aae030,q.aae031','extFunc':'daterangeInit'}" />
					<powersi:codeselect id="aka130" name="q.aka130" label="业务类型" codeType="aka130" />
					<powersi:codeselect id="bka006" name="q.bka006" label="待遇类型" codeType="bka006" />
					<powersi:codeselect id="bka035" name="q.bka035" label="人员类型" codeType="bka035" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<td align="right" class="tdLabel">
						<span class="required requiredLabel">*</span><span title="">申请状态</span>
					</td>
					<td>
						<select id="bkp161"  style= "width:80 " name="q.bkp161">
							<option value="0">无申请</option>
							<option value="1">申请完成</option>
							<option value="2">申请中</option>
						</select>
					</td>
					<td class="tdLabel"><select id="col" name="q.col" style="width: 92px;" class="select tdLabel">
							<option value="aac002">身份证号码</option>
							<option value="aac001">个人电脑号</option>
							<option value="aac003">姓名</option>
					</select></td>
					<td><input type="text" id="colVal" name="q.colVal" /></td>
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_result" allowCollapse="false">
		<powersi:tabbedpanel id="divTabs">
			<powersi:tab id="tab1" target="divTab1" label="业务信息列表" />
			<powersi:tab id="tab2" target="divTab2" label="申请信息列表" />
			<div id="divTab1">
				<powersi:datagrid id="dg_biz" formId="f_query" delayLoad="true" dataAction="local" checkbox="true" pageSize="50"
					isCheckable="renderBizCheckAble">
					<powersi:toolbar>
						<powersi:toolbar-item id="dg_apply_condition" />
					</powersi:toolbar>
					<powersi:datagrid-column display="操作" name="operate" width="100" render="renderOperate" frozen="true"/>
					<powersi:datagrid-column name="akb021" display="医疗机构名称" align="left" width="190" />
					<powersi:datagrid-column name="aka130" display="业务类型" render="_render" width="80" />
					<powersi:datagrid-column name="bka006" display="待遇类型" render="_render" width="80" />
					<powersi:datagrid-column name="bka035" display="人员类型" render="_render" width="80" />
					<powersi:datagrid-column name="aac003" display="姓名" />
					<powersi:datagrid-column name="aac004" display="性别" render="_render" width="30" />
					<powersi:datagrid-column name="aac002" display="身份证号码" width="130" />
					<powersi:datagrid-column name="aac001" display="个人电脑号" />
					<powersi:datagrid-column name="akc264" display="医疗总费用" id="akc264" align="right" format="{0,number,###,##0.00}"
						render="function(r,i,v,c){return '<span index='+i+'>'+v+'</span>'}" totalSummary="{'render': _totalSummary}" />
					<powersi:datagrid-column name="bkz102" display="诊断疾病" align="left" />
					<powersi:datagrid-column name="akc194" display="结算时间" />
					<powersi:datagrid-column name="aae030" display="入院时间" />
					<powersi:datagrid-column name="aae031" display="出院时间" />
					<powersi:datagrid-column name="aae139" display="异地标志" render="_render" width="70" />
					<powersi:datagrid-column name="daa027" display="拨付中心"
						render="function(r, i, v, c){return _render(r, i, v, {columnname:'aaa027'});}" align="left" width="80" />
					<powersi:datagrid-column name="aaa027" display="业务中心" render="_render" align="left" width="80" />
					<powersi:datagrid-column name="bke100" display="登账号" hide="true" />
					<powersi:datagrid-column name="bkp060" display="中心审核结果" render="renderBkp060"/>
				</powersi:datagrid>
			</div>
			<div id="divTab2">
				<powersi:datagrid id="dg_apply" url="${ACTION}!queryAccountApply.action" delayLoad="true"
					checkbox="false"
					enabledEdit="false" enabledSort="false" showExportExcel2007="true"  pageSize="50"
					>
					<powersi:datagrid-column name="bkp055" display="医院申请原因" width="400" />
					<powersi:datagrid-column name="bkp056" display="医院申请时间" width="150"/>
					<powersi:datagrid-column name="bkp058" display="医院申申请人" width="200"/>
					<powersi:datagrid-column name="bkp060" display="中心审核结果" render="renderBkp060"/>
					<powersi:datagrid-column name="bkp059" display="中心审核意见" width="400"/>
					<powersi:datagrid-column name="bkp061" display="中心审核时间" width="150"/>
					<powersi:datagrid-column name="bkp063" display="中心审核人" width="200"/>
					
					<powersi:datagrid-column name="bke100" display="bke100" hide="true" />
					<powersi:datagrid-column name="kcp0id" display="kcp0id" hide="true" />
				</powersi:datagrid>
			</div>
		</powersi:tabbedpanel>
	</powersi:panelbox>
</body>
</powersi:html>