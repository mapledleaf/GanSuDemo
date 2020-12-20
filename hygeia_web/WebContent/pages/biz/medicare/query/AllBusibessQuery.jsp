<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String userName = BizHelper.getLoginUser();
%>
<powersi:html>
<powersi:head title="业务信息查询" />
<body>
	<powersi:form id="queryForm" namespace="/medicare" action="BizQueryAction!queryAll.action">
		<powersi:panelbox key="panel_query" title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="czSubmit" buttonIcon="icon-search" label="查 询" onclick="query()"/>
				<%-- <powersi:button id="idSynchroData" buttonIcon="icon-download" label="同步" onclick="synchroData()"/> --%>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="9">
				<powersi:editorlayout-row>
					<powersi:textfield label="医疗机构编码"  id="akb020" name="bizQueryDto.akb020"/>
					<powersi:codeselect codeType="aka130" id="aka130" name="bizQueryDto.aka130" headerKey="0"
							 label="业务类型"  required="ture"/>
					<powersi:codeselect codeType="bka006" id="bka006" name="bizQueryDto.bka006"
							 label="待遇类型" />
					<td colspan="1">
						<span style="vertical-align : middle; float: right">结算时间</span>
					</td>
					<td colspan="2">
						<div id="div-demo" class="input-group input-daterange">
							<powersi:textfield id="fromdate" name="bizQueryDto.fromdate" mask="date"
								format="dateFmt:'yyyy-MM-dd'" required="ture"/>
							<span class="input-group-addon">-</span> 
							<powersi:textfield id="todate" name="bizQueryDto.todate" mask="date"
						 		format="dateFmt:'yyyy-MM-dd'" required="ture"/>
						</div>
					</td>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="arg_name" label="人员信息"  name="bizQueryDto.arg_name"
						 list="#{'':'请选择...','name':'姓名','indi_id':'个人电脑号','idcard':'身份证号','serial_no':'就医登记号'}" />
					<td colspan="2" class="tdLabel" >
						<powersi:textfield id="arg_value" placeholder="请输入信息.." name="bizQueryDto.arg_value" />
					</td>
					<powersi:hidden id="caa027" name="bizQueryDto.caa027" value="4303-zg"/>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
	<div class="row">
		<div class="col-6">
			<powersi:panelbox iconClass="icon-cog" title="结算云数据" allowCollapse="false">
				<powersi:datagrid id="jsyInfo" delayLoad="true" usePager="true"  
					enabledSort="false" selectRowButtonOnly="true" >
					<powersi:datagrid-column name="akb020" label="医院编号" width="15%" frozen="true"/>
					<powersi:datagrid-column name="aaz217" label="就医登记号" width="30%" frozen="true"/>
					<powersi:datagrid-column name="aac003" label="姓名" width="15%" frozen="true"/>
					<powersi:datagrid-column name="aac002" label="身份证号" width="30%" frozen="true"/>
					<powersi:datagrid-column name="akc194" label="结算日期" width="20%" frozen="true"/>
				</powersi:datagrid>
			</powersi:panelbox>
		</div>
    	<div class="col-6">
    		<powersi:panelbox iconClass="icon-cog" title="中心端数据" allowCollapse="false">
		  		<powersi:datagrid id="centerInfo" delayLoad="true" usePager="true"  
					enabledSort="false" selectRowButtonOnly="true" >
					<powersi:datagrid-column name="akb020" label="医院编号" width="15%" frozen="true"/>
					<powersi:datagrid-column name="aaz217" label="就医登记号" width="30%" frozen="true"/>
					<powersi:datagrid-column name="aac003" label="姓名" width="15%" frozen="true"/>
					<powersi:datagrid-column name="aac002" label="身份证号" width="30%" frozen="true"/>
					<powersi:datagrid-column name="akc194" label="结算日期" width="20%" frozen="true"/>
				</powersi:datagrid>
			</powersi:panelbox>
	    </div>
    </div>
</body>
</powersi:html>
<script type="text/javascript">
	window.onload = function() {
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth() + 1).toString().length == 1 ? "0"
				+ (myDate.getMonth() + 1).toString()
				: (myDate.getMonth() + 1).toString();
		var day = myDate.getDate().toString().length == 1 ? "0"
				+ myDate.getDate().toString() : myDate.getDate().toString();
		$("#fromdate").val("2019-01-01");
		$("#todate").val(year + "-" + month + "-" + day);
	}
	
	function query(){
		var userName = "<%=userName%>";
		if(userName!="admin"){
			alert("非管理员不能使用该功能!");
			return;
		}
		if (!checkFormValidtion())
	  		return;
		if($("#fromdate").val()=="" || $("#todate").val()==""){
			alert("日期不能为空！");
			return;
		}
		if(validateMinDate($("#fromdate").val())){
			alert("开始日期不能小于2019年1月1日！");
			return;
		}
		if(validateMaxDate()){
			alert("开始日期不能大于结束！");
			return;
		}
		var saveItemData = $("#queryForm").serialize();
		postJSON("${rootPath}/query/BizQueryAction!queryAll.action",saveItemData,
			function (json){
				if(!checkJSONResult(json)){
					return;
				}
				jsyInfo.loadData(json.data.jsy);
				centerInfo.loadData(json.data.cen);
			 });
	}
	
	function validateMinDate(fromdate) {
		var date = getDate(fromdate);
		if(date<parseInt("20190101")){
			return true;
		}else{
			return false;
		}
	}
	
	function validateMaxDate(){
		var sDate = getDate($("#fromdate").val());
		var eDate = getDate($("#todate").val());
		if(eDate<sDate){
			return true;
		}else{
			return false;
		}
	}
	function validateDateCha(){
		var sDate = getDate($("#fromdate").val());
		var eDate = getDate($("#todate").val());
		if((eDate-sDate)>3){
			return true;
		}else{
			return false;
		}
	}
	
	function getDate(fromdate){
		var regEx = new RegExp("\\-", "gi");
		fromdate = fromdate.replace(regEx, "/");
		var sfromdate = new Date(fromdate);
		var year = sfromdate.getFullYear();
		var month = (sfromdate.getMonth() + 1).toString().length == 1 ? "0"
				+ (sfromdate.getMonth() + 1).toString()
				: (sfromdate.getMonth() + 1).toString();
		var day = sfromdate.getDate().toString().length == 1 ? "0"
				+ sfromdate.getDate().toString() : sfromdate.getDate().toString();
		return parseInt((year+month+day+""));
	}
	
	<%-- function chooseDis(){
		popupDialog({
			   url:"<%=path%>/medicare/MtmmSpecialApplyAction!queryHospInfo.action?mediSpecDto.caa027="+$("#caa027").val(),
			   onClosed:function(){
				   var ret = this.returnValue;
				   alert(ret.akb020);
				   $("#akb020").val(ret.akb020);
			   }
		   },500, 800);
	} --%>
</script>