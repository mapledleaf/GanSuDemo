<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<powersi:html>
<head>
<powersi:head title="住院预付款管理" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>
<body>
	<powersi:form id="queryInHospitalForm" namespace="/inhospital"
		action="InhospitalManagerAction!queryForegift.action">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="btSubmit" key="button_query" onClick="doQuery()" label="查询"/>
				<powersi:button id="btReset" key="button_reset" onClick="doClear();" label="重置"/>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aae030" name="inHospitalDTO.aae030"
						label="开始日期" required="true" mask="date" />
					<powersi:textfield id="aae031" name="inHospitalDTO.aae031"
						label="结束日期" required="true" mask="date" />
					 
				    <powersi:codeselect id="aka130" key="aka130" name="inHospitalDTO.aka130" codeType="aka130" codeFilter="data_value='${inHospitalDTO.aka130}'" />
					
					<td class="tdLabel" title=""><label for="bka245is" class="textLabel"><span title="">是否已交预付款</span></label></td>
					<td>
					<select name="inHospitalDTO.bka245is" id="bka245is" class="select">
					    <option value="0" selected="selected">全部</option>
					    <option value="1">是</option>
						<option value="2">否</option>
					</select>
					</td>
					<powersi:textfield id="aaz217" name="inHospitalDTO.aaz217" label="就医登记号" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<td class="tdLabel">
						<span>查询条件</span>
					</td>	
					<td>
						<powersi:codeselect id="argName"  name="inHospitalDTO.argName" class="select" 
						list="#{'akc190':'住院号','aac002':'社会保障号','aaz217':'就医登记号' }" />
					</td>
					<td colspan="2">
						<powersi:textfield id="querystring" name="inHospitalDTO.querystring"
							title="请输入信息回车" placeholder="请输入信息回车!" buttonText="读卡" 
							onkeydown="queryInHospitalBizs()" 
							buttonDisabled="false"  />
					</td>	
					<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="bka891" name="inHospitalDTO.bka891" />
	</powersi:form>
	<powersi:panelbox key="业务列表">
		<powersi:panelbox-toolbar>
			<powersi:button id="btnEnable" key="button_enable" label="设置预付款" onclick="doEdit()"  ></powersi:button>
		</powersi:panelbox-toolbar>	
		<powersi:datagrid id="grid" formId="queryInHospitalForm"
			delayLoad="false" showReload="false" enabledSort="false"
			alternatingRow="true" colDraggable="false" isMultiSelect="false"  checkbox="true" onDblClickRow="dbSelectRow">
			  <%-- <powersi:toolbar>
				 <powersi:toolbar-item id="edit_btn" text="设置预付款" icon="edit" click="itemClick" title="预付款"/>
			</powersi:toolbar> --%>
			<powersi:datagrid-column name="aaz217" display="就医登记号" width="150" />
			<powersi:datagrid-column name="aac001" display="电脑号" width="120" />
			<powersi:datagrid-column name="aac003" display="姓名" width="120" />
			<powersi:datagrid-column name="akc190" display="住院号" width="120" />
			<powersi:datagrid-column name="bka245" display="预付款" width="50" />
			<powersi:datagrid-column name="aac004" display="性别" code="aac004"
				width="40" />
			<powersi:datagrid-column name="aae030" display="业务开始日期" width="80" />	
			<powersi:datagrid-column name="aac002" display="社会保障号码" width="150" />
			<powersi:datagrid-column name="bka035" display="人员类别" code="bka004"
				width="120" />
			<powersi:datagrid-column name="aka130" display="业务类别" code="aka130"
				width="80" />
			<powersi:datagrid-column name="bka006" display="待遇类型"
				width="150" code="bka006"/>
			
			<powersi:datagrid-column name="bkz101" display="入院疾病诊断"
				width="150" />
			<powersi:datagrid-column name="bka032" display="业务结束日期" width="80" />
			<powersi:datagrid-column name="bka031_name" display="出院疾病诊断"
				width="150" />
			
			<powersi:datagrid-column name="aae031" display="业务结算日期" width="80" />
			<powersi:datagrid-column name="aae140" display="险种类型" code="aae140"
				width="150" />
			 <powersi:datagrid-column name="akb020" display="医院编号" width="80" />
		</powersi:datagrid>
	</powersi:panelbox>
	<powersi:errors />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#querystring").focus();
		});

		var objCard = null;
		/*加载控件*/
		function loadCardControl() {
			try {
				if (objCard == null || objCard.object == null) {
					objCard = document.getElementById("cardControl");
					if (objCard.object == null) {
						popupAlert("请先安装社保卡控件!", "提示", "info");
					}
				}
			} catch (e) {
				popupAlert("请先安装社保卡控件!", "提示", "info");
			}
		}

		function queryInHospitalBizs() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					popupAlert("请输入有效查询条件","错误","error");
					return;
				}
				$("#queryInHospitalForm").submit();
			}
		}

		/*读卡获取后台信息*/
		function readic() {
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			$("#queryInHospitalForm").submit();
			$("#bke548").val("");
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
 
		function itemClick(item){
			var itemname = item['id'];
			if(itemname == 'add_btn'){
				doAdd();
			} else if(itemname == 'edit_btn'){
				 
				doEdit();
			} else if(itemname == 'cancel'){
				cancel();
			} else if(itemname == 'import'){
				upload();
			} else if(itemname == 'export'){
				download();
			}
		}
		
		function dbSelectRow(rowdata, rowid, rowobj){
			grid.selectRow(rowid,"select");
			doEdit();
		}
		
		function doEdit() {
	  		 
	  		var data = grid.getData();
	    	data = powersi.tostring(data);
	  		if(powersi.isempty(data)){
				popupAlert("没有数据！", "错误", "error");
				return;
			}
	  		
	  		var rows=grid.getSelectedRows();
	  		if (rows.length != 1){
	  			popupAlert("请选择一行记录修改!","提示","info");
				return ;
			}
	  		var data = rows[0];
	  		/* openDialogWithParam({
				url: "${rootPath}/inhospital/InhospitalManagerAction!foregiftEdit.action?inHospitalDTO.aaz217="+data.aaz217,
				onClosed: function() {
					var retVal = this.returnValue;
					 if(retVal!=null && retVal!="")
						 {
					    alert('成功');
						 }
				}
			},'param1',  600,800); setDialogReturn(powersi.tostring(param));*/

	  		openDialogWithParam("${rootPath}/inhospital/InhospitalManagerAction!foregiftEdit.action?inHospitalDTO.aaz217="+data.aaz217, "param1", 600, 900, function(ret){
				if(ret){
					 
					/* if(ret=="1")
					 {
						 
					  } */
				}
			});
	  		
		}
		
		
		function doPrint() {
	  		 
	  		var data = grid.getData();
	    	data = powersi.tostring(data);
	  		if(powersi.isempty(data)){
				popupAlert("没有数据！", "错误" , "error");
				return;
			}
	  		
	  		var rows=grid.getSelectedRows();
	  		if (rows.length != 1){
	  			popupAlert("请选择一行记录打印!", "提示", "info");
				return ;
			}
	  		var data = rows[0];

	  		openDialogWithParam("${rootPath}/inhospital/InhospitalManagerAction!foregiftEdit.action?type=printForegift&inHospitalDTO.aaz217="+data.aaz217, "param1", 500, 800, function(ret){
				 
			});
	  		
		}
		
		function doClear(){
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
			var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
			$("#aae030").val(year+month+day);
			$("#aae031").val(year+month+day);
			$("#bka006").val("");
			$("#aaz217").val("");
			$("#aac001").val("");
			$("#aac002").val("");
			$("#bka025").val("");
			$("#querystring").val("");
			$("#argName").val("akc190");
			$("#argName").change();
			$("#bka245is").val("0");
			grid.reset();
		}
		
		function doQuery(){
			$("#queryInHospitalForm").submit();
		}
	</script>
</body>
</powersi:html>