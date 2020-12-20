<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="转诊转院申请修改" />
</head>
<body>
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="referralTransferModifyid" namespace="/particular"
		action="ParticularManagerAction!referralTransferModify.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>	
				<powersi:submit id="btreferralTransferQuery" key="button_query" />
				<powersi:hidden id="btfundStatusQuery" label="基金状态"
					onclick="fundStatusQuery()" />
				<powersi:button id="btreferralTransferModifyReset" label="重置" 
				    onclick="referralTransferModifyReset()" />
				<powersi:reset id="btReset" label="重置" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8%,17%,8%,17%,8%,17%,8%,17%">
				<powersi:editorlayout-row>
				    <powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="开始日期" required="true" mask="date" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="结束日期" required="true" mask="date" />
				    <powersi:radio id="bka600" name="inHospitalDTO.bka600" label="转出标识"
						codeType="bka600" codeLocal="${inHospitalDTO.aaa027}" required="true" />
					<powersi:textfield id="querystring"
						name="inHospitalDTO.querystring" label="查询条件" title="请输入信息回车"
						placeholder="请输入信息回车" readonly="false"
						onkeydown="findBaseInfo()" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />	
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
				    <powersi:editorlayout-row>
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
					<powersi:textfield id="aac002" name="inHospitalDTO.aac002"
						label="社会保障号码" />
					<powersi:textfield id="aac001" name="inHospitalDTO.aac001"
						label="电脑号"  />
				    </powersi:editorlayout-row>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
	</powersi:form>
	<powersi:panelbox key="业务列表">
		<powersi:datagrid id="grid" formId="referralTransferModifyid"
			delayLoad="true" showReload="false" enabledSort="false"
			alternatingRow="true" colDraggable="false" usePager="false">
			<powersi:datagrid-column key="operate" render="renderOperate"
				width="248" frozen="true" />
			<powersi:datagrid-column name="aae127" display="申请日期" width="80" />
			<powersi:datagrid-column name="bke058_name" display="审核状态" width="80" />
			<powersi:datagrid-column name="akb020" display="医院编号" width="80" />
			<powersi:datagrid-column name="aaz217" display="就医登记号" width="150" />
			<powersi:datagrid-column name="aac001" display="电脑号" width="120" />
			<powersi:datagrid-column name="aac003" display="姓名" width="120" />
			<powersi:datagrid-column name="aac004" display="性别" code="aac004" 
			    width="40" />
			<powersi:datagrid-column name="aac002" display="社会保障号码" width="150" />
			<powersi:datagrid-column name="bka004" display="人员类别" code="bka004"
				width="120" />
			<powersi:datagrid-column name="aae140" display="险种类型" code="aae140"
				width="150" />
			<!-- 
			<powersi:datagrid-column name="aka130" display="业务类别" code="aka130"
				width="80" />
			<powersi:datagrid-column name="bka006_name" display="待遇类型"
				width="150" />
			<powersi:datagrid-column name="bka017" display="业务开始日期" width="80" />
			<powersi:datagrid-column name="bka026_name" display="入院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="bka032" display="业务结束日期" width="80" />
			<powersi:datagrid-column name="bka031_name" display="出院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="bka025" display="住院号" width="120" />
			<powersi:datagrid-column name="bka045" display="业务结算日期" width="80" />
			 -->
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	
	<script type="text/javascript">
		var queryreferralTransferModifyFlag = false;
		var objCard = null;

		$(document).ready(function() {
			$("#querystring").focus();
		});

		/*加载控件*/
		function loadCardControl() {
			try {
				if (objCard == null || objCard.object == null) {
					objCard = document.getElementById("cardControl");
					if (objCard.object == null) {
						popupAlert("请先安装社保卡控件!");
					}
				}
			} catch (e) {
				popupAlert("请先安装社保卡控件!");
			}
		}

		/*读卡*/
		function iReadCardBase() {
			$("#bke548").val("");
			$("#querystring").val("");
			loadCardControl();
			if (objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}

		/*读卡获取后台信息*/
		function readic() {
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			$("#referralTransferModifyid").submit();
			$("#bke548").val("");
		}

		/*重置界面*/
		function referralTransferModifyReset() {
			var aac003 = powersi.trim($("#aac003").val());
			if (!powersi.isnull(aac003) && !queryreferralTransferModifyFlag) {
				if (!confirm("界面已加载数据，您确认要重置吗？")) {
					return;
				}
			}
			$("#btReset").click();
			grid.reset();
		}

		/*基金状态*/
		function fundStatusQuery() {
			var aac001 = powersi.trim($("#aac001").val());
			if (powersi.isnull(aac001)) {
				return;
			}
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
		
		function renderOperate(row, index, value) {
			var a = [];
			a.push('<input type="button" value="详情" class="linkButton"');
			a.push(' onclick="doViewInfo(');
			a.push(index);
			a.push(')"');
			a.push(" />");

			a.push("&nbsp;&nbsp;");

			a.push('<input type="button" value="修改" class="linkButton"');
			a.push(' onclick="doModifyInfo(');
			a.push(index);
			a.push(')"');
			if (row['bke058'] == '1' || row['bke058'] == '2') {
				a.push(' disabled="disabled"');
			}
			a.push(" />");
			
			return a.join('');
		}
		
		//仅仅查看
		function doViewInfo(index){
			var row = grid.getRow(index);
			var aaz267 = row['aaz267'];
			popupDialog({
				url:"${rootPath}/particular/ParticularManagerAction!referralTransferInfoView.action?inHospitalDTO.aaz267="+aaz267,
				onClosed:function(){
				}
			},screen.height,screen.width);
		}
		
		//修改
		function doModifyInfo(index){
			var row = grid.getRow(index);
			var aaz267 = row['aaz267'];
			popupDialog({
				url:"${rootPath}/particular/ParticularManagerAction!referralTransferInfoModify.action?inHospitalDTO.aaz267="+aaz267,
				onClosed:function(){
					$("#referralTransferModifyid").submit();//重新加载
				}
			},screen.height,screen.width);
		}
	</script>
</body>
</powersi:html>