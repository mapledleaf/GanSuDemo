<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
<head>
<powersi:head title="跨省异地住院信息查询" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/query"
		action="BizQueryAction!queryHospitalInfoKsyd.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="yy_query" label="医院查询" onclick="hospitalQuery()"
					onchange="" />
				<powersi:button id="feeDetail_query" label="结算清单"
					onclick="settlementReportPrint()" />
				<powersi:button id="" label="费用清单" onclick="feeDetailTable()" />
				<powersi:button id="" label="基金查询" onclick="fundStatusInfo()" />
				<powersi:button id="export_button" label="导出"
					onclick="query_zy_bizGridId.exportExcel2007()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="10%,15%,8%,12%,8%,12%,9%,7%,13%">
				<tr>
					<powersi:codeselect id="arg_name" label="人员信息"  name="bizQueryDto.arg_name"
					 list="#{'':'请选择...','idcard':'社会保障号','serial_no':'就医登记号','patient_id':'医院业务号(住院号)'}" />
					<td colspan="2" class="tdLabel" >
						<powersi:textfield id="arg_value" placeholder="请输入信息回车.." 
							name="bizQueryDto.arg_value" onkeydown="queryByPersonInfo()"/></td>	
					<td align="right" class="tdLabel">在院状态</td>
					<td><select id="zys001" name="bizQueryDto.zys001" 
							onchange="changButDis()">
							<option value="1">在院</option>
							<option value="2">出院</option>
						</select></td>
					<td align="right"><input type="radio" id="clinicDate"
						name="radio_date" value="rad_fromdate" checked="checked"
						onchange="changeDatetype()" /><label id="inhospID">入院时间</label></td>
					<powersi:textfield id="fromdate" label="开始时间"
						name="bizQueryDto.fromdate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</tr>
				<tr>
					<td align="right" class="tdLabel">业务类型</td>
					<td><select id="aka130" name="bizQueryDto.aka130" >
							<option value="82">跨省住院</option>
						</select></td>
					<td colspan="2"></td>
					<td align="right" class="tdLabel">结算状态</td>
					<td><select id="zys002" name="bizQueryDto.zys002" class=""
							onchange="settleStatus()">
							<option value="1">未结算</option>
							<option value="2">已结算</option>
						</select></td>
					<td align="right"><input type="radio" id="endclinicDate"
						name="radio_date" value="rad_todate" onchange="changeDatetype()" /><label
						id="settleDate">结算时间</label></td>
					<powersi:textfield id="todate" label="结束时间"
						name="bizQueryDto.todate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</tr>
				<powersi:editorlayout-row>
					<powersi:hidden id="akb020" name="bizQueryDto.akb020" />
					<powersi:hidden id="aaz217" name="bizQueryDto.aaz217" />
					<powersi:hidden id="aae100" name="bizQueryDto.aae100" value="1" />
					<powersi:hidden id="aae140" name="inHospitalDTO.aae140" value="310" />
					<powersi:hidden id="one" name="one" />
					<powersi:hidden id="exec_flag" name="bizQueryDto.exec_flag"
						value="zy" />
					<powersi:hidden id="fin_flag" name="bizQueryDto.fin_flag" value="0" />
					<powersi:hidden id="settlement_time_flag"
						name="bizQueryDto.settlement_time_flag" value="0" />
					<powersi:hidden id="queryFlag" name="queryFlag" value="1"/>	
					<powersi:hidden id="timeType_flag" name="bizQueryDto.timeType_flag" value="1" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<div class="row">
		<div class="col-9">
			<div id="yyDiv">
				<powersi:panelbox key="panel_result" title="业务信息"
					allowCollapse="false">
					<powersi:datagrid id="query_zy_bizGridId" formId="queryForm"
						delayLoad="true" usePager="true" pageSize="10"
						selectRowButtonOnly="true" onDblClickRow="onSelectClick"
						showExportExcel2007="false" exportFileName="'住院信息查询'">
						<powersi:datagrid-column label="业务类型" id="aka130" name="aka130" code="aka130"
							render="renderDictionary" width="80" />
						<powersi:datagrid-column label="个人电脑号" id="aac001" name="aac001" />
						<powersi:datagrid-column label="姓名" id="aac003" name="aac003" width="100" />
						<powersi:datagrid-column label="公民身份号码" id="aac002" name="aac002" />
						<powersi:datagrid-column label="性别" id="aac004" name="aac004" width="60" />
						<powersi:datagrid-column label="人员类别" id="bka004" name="bka004" width="80" />
						<powersi:datagrid-column label="就医登记号" id="aaz217" name="aaz217" width="150" />
						<powersi:datagrid-column label="待遇类别" id="bka006" name="bka006" />
						<powersi:datagrid-column label="入院时间" id="bka017" name="bka017"
							format="{0,date,yyyy-MM-dd}" />
						<powersi:datagrid-column label="入院诊断" id="bka026" name="bka026" />
						<powersi:datagrid-column label="出院时间" id="bka032" name="bka032" 
							 format="{0,date,yyyy-MM-dd}" />
						<powersi:datagrid-column label="出院诊断" id="bka031" name="bka031" />
						<powersi:datagrid-column label="住院号" id="bka025" name="bka025" />
						<powersi:datagrid-column label="病区" id="bka022" name="bka022" width="80" />
						<powersi:datagrid-column label="科室" id="bka019" name="bka019" width="80" />
						<powersi:datagrid-column label="床位号" id="bka023" name="bka023" width="80" />
						<powersi:datagrid-column label="单位名称" id="bka008" name="bka008" />
						<powersi:datagrid-column label="结算时间" id="bka045" name="bka045" 
							 format="{0,date,yyyy-MM-dd}" />
						<powersi:datagrid-column label="公务员级别" id="bka005" name="bka005"
							width="80" code="bka005" />
						<powersi:datagrid-column label="辖区" id="aaa027" name="aaa027" width="80" code="aaa027"/>
						<powersi:datagrid-column label="业务交接号" id="bae010" name="bae010" />
						<powersi:datagrid-column label="报账类型" id="bka037" name="bka037"
							code="bka037List" render="renderDictionary" />
						<powersi:datagrid-column label="完成标志" id="bka039" name="bka039" hide="true" />
						<powersi:datagrid-column label="医院编号" id="akb020" name="akb020" hide="true" />
						<powersi:datagrid-column label="区别标志" id="one" name="one" hide="true" />
						<powersi:datagrid-column label="人员所属区" id="baa027" name="baa027" hide="true" />
						<powersi:datagrid-column label="业务完成标志" id="secfalg" name="secfalg" hide="true" />
					</powersi:datagrid>
				</powersi:panelbox>
			</div>
		</div>
		<div class="col-3">
			<powersi:panelbox iconClass="icon-cog" title="费用信息"
				allowCollapse="false">
				<powersi:datagrid id="feeGridId" delayLoad="true" showReload="false"
					totalRender="feeTotal">
					<powersi:datagrid-column id="aka063" name="stat_type"
						key="stat_type" label="费用类别" code="aka063List"
						render="renderDictionary" width="100" />
					<powersi:datagrid-column id="aae019" name="zfy" key="zfy"
						label="费用" width="80" />
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
	</div>
	<!-- 转换码值 -->
	<powersi:codetable id="bka004List" codeType="bka004"></powersi:codetable>
	<powersi:codetable id="aka130" codeType="aka130"></powersi:codetable>
	<powersi:codetable id="bka006List" codeType="bka006"
		codeLocal="<%=aaa027%>"></powersi:codetable>
	<powersi:codetable id="aka063List" codeType="aka063"></powersi:codetable>
	<powersi:errors />
	<script type="text/javascript">
		window.onload = function() {
			$('#akb020').val("<%=hospital_id%>");
			$('#akb021').val("<%=hospital_name%>");
			if ($("#akb020").val() == '' || $("#akb020").val() == null) {
				popupAlert("医院编码不能为空，请检查登陆信息！");
				return;
			}
			//出院时间
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = (myDate.getMonth() + 1).toString().length == 1 ? "0"
					+ (myDate.getMonth() + 1).toString()
					: (myDate.getMonth() + 1).toString();
			var day = myDate.getDate().toString().length == 1 ? "0"
					+ myDate.getDate().toString() : myDate.getDate().toString();

			$("#fromdate").val(year + "-" + month + "-" + "01");
			$("#todate").val(year + "-" + month + "-" + day);
			settleStatus();
			changButDis();
		}

		function hospitalQuery() {
			$("#queryFlag").val("1");
			$("#queryForm").submit();
			feeGridId.reset();
		}

		function queryByPersonInfo() {
			if (window.event.keyCode == 13) {
				$("#queryFlag").val("1");
				$("#queryForm").submit();
				feeGridId.reset();
			}
		}

		//医院中心查询
		function hospitalCenterQuery() {
			$("#queryFlag").val("2");
			$("#queryForm").submit();
			feeGridId.reset();
		}
		

		//查询费用分类信息
		function onSelectClick(index) {
			var row = query_zy_bizGridId.getRow(index);
			var aaz217 = row['aaz217'];
			var akb020 = row['akb020'];
			var one = row['one'];
			var secfalg = row['secfalg'];
			feeGridId.reset();
			if (one == '0') {//双击行,查询费用（医院端，医院端的费用查询逻辑不变）
				postJSON(
						"${rootPath}/query/BizQueryAction!queryBizFeeInfo.action?bizQueryDto.aaz217="
								+ aaz217 + "&bizQueryDto.akb020=" + akb020
								+ "&bizQueryDto.secfalg=" + secfalg,
						afterInHospBizFee);
			}
			
			if (one == '1') {//双击行,查询费用(前置机端)
				postJSON(
						"${rootPath}/query/BizQueryAction!queryKsydBizFeeInfo.action?bizQueryDto.aaz217="
								+ aaz217 + "&bizQueryDto.akb020=" + akb020,
						afterInHospBizFee);
			}
			
		}
		
		function afterInHospBizFee(json) {
			if (!checkJSONResult(json)) {
				return;
			}
			//加载结果集到datagrid
			feeGridId.loadData(json.data);
		}

		//费用清单查询（可能为医院端也可能为前置机端，和前保持一致，都是通过row.one进行区分）
		function feeDetailTable() {
			var row = null;
			row = query_zy_bizGridId.getSelectedRow();

			if (row == null) {
				popupAlert("请选择一条业务记录！");
				return;
			}
			var akb020 = $("#akb020").val();
			var aaz217 = row.aaz217;
			var zys001 = $("#zys001 option:selected").val();
			if (zys001 == "1") {
				var fromdate = row.bka017;
			} else {
				var fromdate = row.bka032;
			}
			var secfalg = row.secfalg;
			var one = row.one;
			popupDialog(
					{
						url : "${rootPath}/pages/biz/medicare/query/InhospitalBizQueryFeeDetailKsyd.jsp?bizQueryDto.akb020="
								+ akb020
								+ "&bizQueryDto.aaz217="
								+ aaz217
								+ "&bizQueryDto.secfalg="
								+ secfalg
								+ "&bizQueryDto.fromdate="
								+ fromdate
								+ "&bizQueryDto.one=" + one,
						onClosed : function() {
						}
					}, 500, 1000);
		}

		/*打印结算单*/
		function settlementReportPrint() {
			var row = null;
			row = query_zy_bizGridId.getSelectedRow();

			if (row == null) {
				popupAlert("请双击一条业务记录！");
				return;
			}
			var aaz217 = row.aaz217;
			if (powersi.isnull(aaz217)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/SettlementReportAction!settlementReport.action?inHospitalDTO.aaz217="
								+ aaz217,
						onClosed : function() {

						}
					}, 600, 1000);
		}

		//查结算单
		function inhospSquare() {
			var zx_tag = $("#zxDiv").is(":visible");
			var row = null;
			if (zx_tag) {
				row = zx_query_zy_bizGridId.getSelectedRow();
			} else {
				row = query_zy_bizGridId.getSelectedRow();
			}

			if (row == null) {
				popupAlert("请双击一条业务记录！");
				return;
			}
			var aaz217 = row.aaz217;
			var aaa027 = row.aaa027;
			var one = row.one;//one本地、中心区别标志:0 查本地;1查中心
			var akb020 = $('#akb020').val();

			popupDialog(
					{
						url : "${rootPath}/query/BizQueryAction!inhospitalSquare.action?bizQueryDto.aaz217="
								+ aaz217 + "&bizQueryDto.one=" + one,

						onClosed : function() {

						}
					}, 450, 1000);
		}
		
		//出院、入院显示控制
		function changButDis() {
			var zys001 = $("#zys001 option:selected").val();
			if (zys001 == "1") {
				$("#inhospID").text("入院时间");
				$("#zys002").empty();
				$("#zys002")
						.append("<option value='1' >" + "未结算" + "</option>");
				$("#fin_flag").val("0");
				$("#timeType_flag").val("1");//入院时间
			} else {
				$("#inhospID").text("出院时间");
				$("#zys002").empty();
				$("#zys002").append(
						"<option value='1' checked >" + "未结算" + "</option>");
				$("#zys002")
						.append("<option value='2' >" + "已结算" + "</option>");
				$("#zys002").attr("disabled", false);
				$("#fin_flag").val("1");
				$("#timeType_flag").val("2");//出院时间
			}

		}
		
		//结算时间的显示控制
		function settleStatus() {
			if ($("#zys002").val() == "1") {
				$("#endclinicDate").attr("disabled", true);
				$("#settleDate").attr("disabled", true);
				$("#feeDetail_query").attr("disabled", true);
				$("#fin_flag").val("1");
			} else {
				$("#endclinicDate").attr("disabled", false);
				$("#settleDate").attr("disabled", false);
				$("#feeDetail_query").attr("disabled", false);
				$("#fin_flag").val("2");
			}
		}
		
		//基金状态查询
		function fundStatusInfo() {
			var row = null;
			row = query_zy_bizGridId.getSelectedRow();

			if (row == null) {
				popupAlert("请选择要查询的数据!");
				return;
			}
			var aac001 = row.aac001;
			var aae140 = powersi.trim($("#aae140").val());
			if (powersi.isnull(aae140)) {
				return;
			}
			popupDialog(
					{
						url : "${rootPath}/common/CommonManagerAction!queryPersonFund.action?inHospitalDTO.aac001="
								+ aac001 + "&inHospitalDTO.aae140=" + aae140,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {

							} else {

							}
						}
					}, 400, 700);
		}
		
		function FloatAdd(arg1, arg2) {
			var r1, r2, m;
			try {
				r1 = arg1.toString().split(".")[1].length
			} catch (e) {
				r1 = 0
			}
			try {
				r2 = arg2.toString().split(".")[1].length
			} catch (e) {
				r2 = 0
			}
			m = Math.pow(10, Math.max(r1, r2))
			return to2bits((arg1 * m + arg2 * m) / m, 2);
		}

		function to2bits(flt, pos) {
			var rd = 1;
			for (var i = 1; i <= parseInt(pos); i++) {
				rd = rd * 10;
			}
			if (parseFloat(flt) == flt)
				return Math.round(flt * rd) / rd;
			else
				return 0;
		}

		function feeTotal(allData, currentData) {
			//计算费用总和
			var sum = 0;
			$.each(allData['rows'], function(n, row) {
				if (feeGridId.getRowStatus(row) !== 'delete') {
					//sum += parseInt(row.aae019);
					sum = FloatAdd(sum, row.zfy);
				}
			});
			var a = [];

			a.push('<div class="textCenter">');

			a.push('费用总计：');
			a.push(sum);
			a.push(' 元');
			a.push('</b> ');
			a.push('</span>');
			a.push('</div>');
			return a.join('');
		}
		
		/* 字典项渲染函数  解决loadData不能自动加载字典问题*/
		function renderDictionary(rowdata, index, value, arguments) {

			if (value != null && value != "") {
				var list = window[arguments.code];
				for ( var i in list) {
					if (list[i].id == value) {
						return list[i].text;
					}
				}
				return '<span title="未匹配到字典项">' + value + '</span>';
			} else {
				return "";
			}
		}

		function changeDatetype() {
			var rad_value = $(':radio[name="radio_date"]:checked').val();
			if('rad_fromdate'==rad_value){
				$('#settlement_time_flag').val('0');
				$("#timeType_flag").val("2");//出院时间
			}
			if('rad_todate'==rad_value){
				$('#settlement_time_flag').val('1');
				$("#timeType_flag").val("3");
			}
		}
	</script>
</body>
</powersi:html>