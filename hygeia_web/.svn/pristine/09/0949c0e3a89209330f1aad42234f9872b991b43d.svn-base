<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<powersi:html>
<head>
<powersi:head title="异地备案信息查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/query" 
		action="BizQueryAction!queryOutRecordInfo.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" label="查 询" onclick="doQuery()"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="12">
				<powersi:editorlayout-row>
					<powersi:codeselect id="arg_name" label="人员信息"  name="bizQueryDto.arg_name"
					 list="#{'name':'姓名','idcard':'社会保障号'}" />
					<td colspan="2" class="tdLabel" >
						<powersi:textfield id="arg_value" placeholder="请输入信息回车.." required="true"
						name="bizQueryDto.arg_value" onkeydown="queryByPersonInfo()"/></td>
					<td colspan="1">
						<span style="vertical-align : middle; float: right">申请时间</span>
					</td>
					<td colspan="2">
						<div id="div-demo" class="input-group input-daterange">
							<span id="startDate" class="form-control text" data-name="start" ></span> 
							<span class="input-group-addon"><i class="icon-calendar"></i></span> 
							<span id="endDate" class="form-control text" data-name="end"></span>
						</div>
					</td>
					<powersi:codeselect id="aae016" label="审核标志"  name="bizQueryDto.aae016"
					 	list="#{'A':'全部','1':'已审核','2':'审核不通过','3':'未审核'}" />
					<!-- 【TS19032600009】结算云【异地备案信息查询】查询不到申请信息 【杨世明20190327】  --> 	
					<powersi:codeselect id="yd_flag" label="异地类型"  name="bizQueryDto.yd_flag"
					 	list="#{'0':'省内异地','1':'跨省异地'}" />
				</powersi:editorlayout-row>
				<powersi:hidden id="todate" name="bizQueryDto.todate"/>
				<powersi:hidden id="fromdate" name="bizQueryDto.fromdate"/>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<powersi:panelbox key="panel_query" title="异地申请列表">
		<powersi:datagrid id="grid" formId="queryForm" delayLoad="true"
			enabledSort="false" showReload="false">
			<powersi:datagrid-column key="operate" render="renderOperate" width="50" frozen="true" />
			<powersi:datagrid-column name="name" 			label="姓名" />
			<powersi:datagrid-column name="idcard" 			label="身份证" />
			<powersi:datagrid-column name="indi_id" 		label="电脑号" />
			<powersi:datagrid-column name="sex" 			label="性别" render="renderDictionaryStatic"/>
			<powersi:datagrid-column name="apply_date" 		label="申请日期" render="renderDate" />
			<powersi:datagrid-column name="admit_date" 		label="生效时间" render="renderDate" />
			<powersi:datagrid-column name="admit_effect" 	label="失效时间" render="renderDate" />
			<powersi:datagrid-column name="birthday" 		label="出生年月" render="renderDate" />
			<powersi:datagrid-column name="apply_type" 		label="异地就医类型" render="renderDictionaryStatic2"/>
			<powersi:datagrid-column name="district_name" 	label="参保地统筹区名称" />
			<powersi:datagrid-column name="patient_name" 	label="申请人" />
			<powersi:datagrid-column name="apply_man_tel" 	label="联系电话" />
			<powersi:datagrid-column name="apply_opinion" 	label="申请理由" />
			<powersi:datagrid-column name="pers_name" 		label="人员类别" />
			<powersi:datagrid-column name="serial_apply" 	label="申请序号" />
			<powersi:datagrid-column name="outhosp_name" 	label="转出医院" />
			<powersi:datagrid-column name="center_name" 	label="就医地统筹区" />
			<powersi:datagrid-column name="remark" 			label="备注信息" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
</body>
</powersi:html>
<script type="text/javascript">
	$(document).ready(
		function() {
			$('#div-demo').daterangepicker({
				"locale": {
			        "format": "YYYY-MM-DD"
				}
			});
			$("#startDate").html('<%=DateFunc.dateToString(new Date(), "yyyy-MM")%>'+'-01');
			$("#endDate").html('<%=DateFunc.dateToString(new Date(), "yyyy-MM-dd")%>');
	});
	
	function doQuery(){
		$("#fromdate").val($("#startDate").html());
		$("#todate").val($("#endDate").html());
		$("#queryForm").submit();
	}
	
	function renderOperate(row, index, value) {
		var a = [];
		a.push('<input type="button" value="详情" class="linkButton"');
		a.push(' onclick="showDetail(');
		a.push(index);
		a.push(')"');
		a.push(" />");
		return a.join('');
	}
	
	function showDetail(index){
		var row = grid.getRow(index);
		var serialApply =  row['serial_apply'];
		popupDialog({
				url: "${rootPath}/pages/biz/medicare/query/OutBizDetail.jsp?serialApply="+serialApply,
				onClosed: function() {
					 
				}
			}, 600, 900);
	}
	
		
	function renderDate(rowdata, index, value){
		if(value!=null){
			return value.substring(0,10);
		}
		return value;
	}
	
	function queryByPersonInfo() {
		if (window.event.keyCode == 13) {
			doQuery();
		}
	}
	
	function renderDictionaryStatic(rowdata, index, value,arguments) {
		if(value != null && value != "") {
			
			if('0' == value) {
				return "女"
			} else if('1' == value) {
				return "男"
			} else if('2' == value) {
				return "女"
			} else {
				return '<span title="未说明性别">'+value+'</span>';
			}
		}else{
			return "";
		}
	}
	
	function renderDictionaryStatic2(rowdata, index, value,arguments) {
		if(value != null && value != "") {
			
			if('2' == value) {
				return "异地安置退休"
			} else if('3' == value) {
				return "异地长期居住"
			} else if('4' == value) {
				return "异地转诊"
			} else if('5' == value) {
				return "其他情况异地就医"
			} else if('6' == value) {
				return "常驻异地工作"
			} else if('8' == value) {
				return "不符合规定的异地就医转诊"
			} else {
				return '<span title="未说明">'+value+'</span>';
			}
		}else{
			return "";
		}
	}
</script>