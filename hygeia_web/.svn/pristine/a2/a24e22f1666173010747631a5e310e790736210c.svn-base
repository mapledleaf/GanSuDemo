<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="发票重打/补打" />
</head>
<body>
	<powersi:form id="queryForm" name="queryForm" namespace="/comminter"
		action="InvoiceManagerAction!queryKab3Info.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:submit id="btSubmit" label="查 询"/>
				<powersi:button id="btnPrint" key="button_print" label="打 印"  onclick="invoicePrint()" />
				<powersi:button name="btCancel" label="作 废" id="btCancel" onclick="invoiceAbolish()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aaz217" label="就医登记号"  name="kab3DTO.aaz217" />
					<td align="right" class="tdLabel">业务类型</td>
					<td><select id="aka130" name="kab3DTO.aka130" class=""
						onchange="">
							<option value="">全部</option>
							<option value="12">住院</option>
							<option value="11">门诊</option>
							<option value="13">门慢</option>
					</select></td>
					<powersi:textfield id="fromdate" label="开始时间"
						name="kab3DTO.fromdate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
					<powersi:textfield id="todate" label="结束时间"
						name="kab3DTO.todate" mask="date"
						format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

		</powersi:panelbox>
	</powersi:form>

	<powersi:panelbox iconClass="icon-cog" title="结算台账信息" allowCollapse="false">
		<powersi:datagrid id="kab3info" formId="queryForm" delayLoad="true" enabledSort="false"
			showReload="false" checkbox="false"
			showExportExcel="true" showExportExcel2007="true"
			showExportPdf="true" exportFileName="'结算台账信息'">
			<powersi:datagrid-column name="akb020" key="akb020" label="医院编码" />
			<powersi:datagrid-column name="aaz217" key="aaz217" label="就医登记号" />
			<powersi:datagrid-column name="aka130" key="aka130" label="业务类别" code="aka130" />
			<powersi:datagrid-column name="aac003" key="aac003" label="姓名" />
			<powersi:datagrid-column name="aac002" key="aac002" label="社会保障号" />
			<powersi:datagrid-column name="bae410" key="bae410" label="发票类型" code="bae410"/>
			<powersi:datagrid-column name="bae413" key="bae413" label="发票号码" />
			<powersi:datagrid-column name="bae417" key="bae417" label="发票金额" />
			<powersi:datagrid-column name="aae019" key="aae019" label="总金额" />
			<powersi:datagrid-column name="aae020" key="aae020" label="基金支付" />
			<powersi:datagrid-column name="aae021" key="aae021" label="个人自付" />
			<powersi:datagrid-column name="aae022" key="aae022" label="个人自费" />
			<powersi:datagrid-column name="aae023" key="aae023" label="个账支付" />
			<powersi:datagrid-column name="aae024" key="aae024" label="现金支付" />
			<powersi:datagrid-column name="bka020" key="bka020" label="住院科室" />
			<powersi:datagrid-column name="bka025" key="bka025" label="住院号" />
			<powersi:datagrid-column name="bka014" key="bka014" label="开票人工号" />
			<powersi:datagrid-column name="bka015" key="bka015" label="开票人姓名" />
			<powersi:datagrid-column name="aae030" key="aae030" label="开票时间" />
			<powersi:datagrid-column name="bka013" key="bka013" label="台账登记时间" />
			<powersi:datagrid-column name="kab3id"  label="台账id" hide="true"/>
			<powersi:datagrid-column name="kab1id"  label="票据id" hide="true"/>
			<powersi:datagrid-column name="bae410_code" key="bae410" label="发票类型"  hide="true"/>
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />


	<script type="text/javascript">
		window.onload = function(){
			kab3info.reload();
		}
	    
		/* 发票打印  */
		function invoicePrint() {
			var row = null;
			row = kab3info.getSelectedRow();
			if (row == null) {
				popupAlert("请双击一条业务记录！");
				return;
			}
			
			var kab1id = row.kab1id;
			if(!(powersi.isnull(kab1id)||kab1id == null)){
				popupAlert("该笔业务已经打印过发票了! 如需重打,请先进行发票作废处理!");
				return;
			}			
			
			var bae410 = row.bae410_code;//发票类型
			var kab3id = row.kab3id;
			var akb020 = row.akb020;
			var aaz217 = row.aaz217;
			
			var checkUrl = "${rootPath}/comminter/InvoiceManagerAction!queryKab1.action?kab1DTO.bae410="+bae410; 
			postJSON(checkUrl, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if(json.data.kab1DTO.length <= 0){
							popupAlert("对不起,您目前没有可用的发票,无法打印发票!");
							return;
						}else{
							var preUrl = "${rootPath}/comminter/InvoiceManagerAction!updateAndGetKab1.action?kab1DTO.bae410="
								+bae410+"&kab1DTO.aaz217="+aaz217+"&flag=1";
							popupDialog(
									{
										url : preUrl,
										onClosed : function() {
											kab3info.reload(true);
										}
									}, 400, 1000);
						}
					});
			
		}

		/* 发票作废 */
	  	function invoiceAbolish() {
	  		
	  		var row = null;
			row = kab3info.getSelectedRow();
			if (row == null) {
				popupAlert("请双击一条业务记录！");
				return;
			}
			
			var aaz217 = row.aaz217;
			if (powersi.isnull(aaz217)) {
				return;
			}
			
			var akb020 = row.akb020;
			if(powersi.isnull(akb020)){
				return;
			}
			
	  		var kab3id = row.kab3id;
	  		if(powersi.isnull(kab3id)){
	  			return;
	  		}
			
			var kab1id = row.kab1id;
			if(powersi.isnull(kab1id)||kab1id == null){
				popupAlert(aaz217+':发票未打印无需作废操作！');
				return;
			}
			
	  		if (!confirm("您确认作废已选择的发票信息吗?")) {
	            return;
	        }
	  		
	  		postJSON("${rootPath}/comminter/InvoiceManagerAction!invoiceAbolish.action",
					{
						"kab3DTO.kab3id" : kab3id,
						"kab3DTO.akb020" : akb020,
						"kab3DTO.aaz217" : aaz217,
						"kab3DTO.kab1id" : kab1id,
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						popupInfo(json.message);
						kab3info.reload(true);
					});

		}
	</script>
</body>
</powersi:html>
