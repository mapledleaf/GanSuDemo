<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName(); 
%>
<powersi:html>
	<head>
	<powersi:head title="实施计划申请" />
	<script src="${strutsPath}/include/multiselect.js" type="text/javascript"></script>
	<script type="text/javascript">
		function queryHospInfo(){
			var aaa027 = $("#aaa027").val();
			postJSON("${rootPath}/actualize/ActualizeManageAction!queryHospInfo.action",
					{
					"aaa027" : aaa027
					}, function(json) {
						if (!checkResult(json)) {
							return;
						}
						hospgrid.loadData(json.data)
					});
		}
	
		$(function() {
			setSize();
			$(window).resize(setSize);
			$(".multiselect").multiselect();
		});
		
		function setSize() {
			try{
				//自适应宽度(因为可能出现纵向滚动条，导致宽度变小，所以需要先设置高度)
				$(".multiselect").height(350).width($("#divTabs").width());
				$(".multiselect").each(function() {
					//检查对象是否初始化
					if ($(this).is(":ui-multiselect")) {
						$(this).multiselect('resize');
					}
				});
			} catch(ex){
				popupAlert(1);
			}
		}
		
		function  save() {
			/* if(!checkFormValidtion())
	     	{
		  		return;
			} */
			var bkf318 = $("#bkf318").val();
			var bkf320 = $("#bkf320").val();
			var bkf321 = $("#bkf321").val();
			var aaa027 = $("#aaa027").val();
			if(powersi.isnull(bkf318)){
				popupAlert("实施计划名称不能为空！");$("#bkf318").focus();
				return;
			}
			if(powersi.isnull(bkf320)){
				popupAlert("计划开始时间不能为空！");$("#bkf320").focus();
				return;
			}
			if(powersi.isnull(bkf321)){
				popupAlert("计划预计结束时间不能为空！");$("#bkf321").focus();
				return;
			}
			if(powersi.isnull(aaa027)){
				popupAlert("统筹区不能为空！");$("#aaa027").focus();
				return;
			}
			var modelist = grid1.getSelectedRows();
			var hosplist = hospgrid.getSelectedRows();
			
			if(powersi.isempty(modelist)){
				popupAlert("请选择模板信息！");
				return;
			}
			if(powersi.isempty(hosplist)){
				popupAlert("请选择参与该计划的医院！");
				return;
			}
			
			for ( var i in modelist) {
				var bkf310 = modelist[i]['__id'];
				modelist[i]['bkf310'] = $(":radio[name='" + bkf310 + "_validid" + "']:checked").val();
			}
			modelist = powersi.tostring(modelist);
			hosplist = powersi.tostring(hosplist);
			postJSON("${rootPath}/actualize/ActualizeManageAction!applyActPlan.action",
					{
						"modelist" : modelist,"hosplist":hosplist,
						"bkf318" : bkf318,
						"bkf320" : bkf320,"bkf321" : bkf321,
						"aaa027":aaa027
					}, function(json) {
						if (!checkResult(json)) {
							return;
						}
						popupInfo(json.data,3000);
						closeDialog();
					});
		}
		
		function renderValidRadio(rowdata, index, value) {
			var a = [];
			var checked = '';
			value = 1;
			$.each(validList, function(i, row){
				if(row["id"] == value){
					checked = ' checked="checked"';
				} else {
					checked = "";
				}
				a.push('<label><input name="' + rowdata["__id"] + '_validid" type="radio" value="' + row["id"] + '"' +  checked + '/>&nbsp;' + row["text"] + '&nbsp;&nbsp;</label>');
			});
			
			return a.join('');
		}
		function renderJB(rowdata, index, value) {
			if(value == '5'){
				return "一级";
			}else if (value == '3') {
				return "二级";
			}else if (value == '1') {
				return "三级";
			}else{
				return "无";
			}
		}
	</script>
	<head>
	<body >
		<powersi:editorlayout cols="10">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="10">
					<powersi:button id="btSave" label="保存计划" onclick="save()" />
					<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bae101" name="kfd6Dto.bae101" value="<%=userName %>"
					key="bae101" label="计划建立人" readonly="true" width="15%"/>
				<powersi:codeselect id="aaa027" key="aaa027" cssClass="select2"
					 label="统筹区" required="true" codeType="aaa027" onchange="queryHospInfo()"/>
				<powersi:textfield id="bkf318" name="kfd6Dto.bkf318"
					key="bkf318" label="计划名称" required="true"/>
				<powersi:textfield id="bkf320" label="开始时间"
					name="kfd6Dto.bkf320" mask="date" required="true"
					format="dateFmt:'yyyy-MM-dd'" />
				<powersi:textfield id="bkf321" label="预计结束时间"
					name="kfd6Dto.bkf321" mask="date" required="true"
					format="dateFmt:'yyyy-MM-dd'" />
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		<br/>
		<powersi:editorlayout>
			<powersi:editorlayout-row>
				<powersi:tabbedpanel id="divTabs">
					<powersi:tab id="tab1" target="divTab1" label="选择该计划的模板" />
					<powersi:tab id="tab2" target="divTab2" label="选择参与计划的医院" />
						<div id="divTab1">
							<powersi:datagrid id="grid1" checkbox="true" usePager="false"
								 url = "${rootPath}/actualize/ActualizeManageAction!queryActualizeModels.action?bz=1"
								 enabledEdit="true" clickToEdit="true" >
								<powersi:datagrid-column display="业务子类编码" name="bkf303" width="30%" />
								<powersi:datagrid-column display="业务子类名称" name="bkf304" width="60%" />
								<powersi:datagrid-column display="是否需要测试" name="bkf310" width="10%" 
									 render="renderValidRadio" type="int" />
							</powersi:datagrid>
						</div>

						<%-- <div id="divTab2">
							<powersi:codeselect id="akb020" cssClass="multiselect" multiple="true"
								 list="#request.hosplist" listKey="akb020" listValue="akb021" />
						</div> --%>
			
						<div id="divTab2">
							<powersi:datagrid id="hospgrid" checkbox="true" usePager="false" >
								<powersi:datagrid-column display="医院编码" name="akb020" hide="true" />
								<powersi:datagrid-column display="医院名称" name="akb021" width="30%"/>
								<powersi:datagrid-column display="医院级别" name="bkc110" width="15%" render="renderJB"/>
							</powersi:datagrid>
						</div>
				</powersi:tabbedpanel>
			</powersi:editorlayout-row>
		</powersi:editorlayout>
		<powersi:errors/>
		<powersi:codetable id="validList" codeType="yes_no"></powersi:codetable>
	</body>
</powersi:html>
