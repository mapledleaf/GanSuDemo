<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
<head>
<powersi:head title="医院药品目录匹配" />
</head>
<body>
	<powersi:form id="queryForm" namespace="/medicare"
		action="HospCataAction!queryHospCata.action">
		<powersi:editorlayout cols="10">
			<powersi:editorlayout-row>
				<powersi:editorlayout-button colspan="3">
					<powersi:button id="btMatch" label="确认匹配" onclick="doMatch()" />
					<powersi:button id="btSavem" label="保存匹配" onclick="doSaveMatch()" />
					<powersi:button id="btAuto" label="自动匹配" onclick="doAutoMatch()" />
					<powersi:button id="btre" label="刷新数据" onclick="init()" />
				</powersi:editorlayout-button>
			</powersi:editorlayout-row>
		</powersi:editorlayout>

		<powersi:editorlayout cols="10">
			<powersi:editorlayout-row cols="8">
				<powersi:codeselect id="" name="" key="" label="选择中心" headerKey="00"
					list="#{'1':'省直医保'}" />
				<powersi:codeselect id="" name="" key="" label="类型选择" headerKey="00"
					list="#{'1':'体检项目'}" />
				<powersi:codeselect id="" name="" key="" label="匹配方式" headerKey="00"
					list="#{'1':'名称'}" />
				<powersi:codeselect id="" name="" key="" headerKey="00"
					list="#{'1':'按全名匹配'}" />
					<!-- 点击匹配信息时显示该下拉框 -->
					<powersi:codeselect id="" name="" key="" headerKey="00"
					list="#{'1':'未审核','2':'审核已通过','3':'审核未通过'}" />	
			</powersi:editorlayout-row>
		</powersi:editorlayout>

	</powersi:form>
	

	<powersi:tabbedpanel id="divTabs">
		<powersi:tab id="tab2" target="divTab2" label="匹配操作" />
		<powersi:tab id="tab1" target="divTab1" label="匹配信息" />
		<div id="divTab1">
			<powersi:datagrid id="matchList" title="提示：双击可删除" url="${rootPath }/medicare/HospCataAction!queryMatchCata.action"
				onDblClickRow="onDblClickRow_matchList" delayLoad="true" enabledSort="false"
		showReload="false" frozen="false" enabledEdit="true" showExportExcel="true"
			showExportExcel2007="true" showExportPdf="true">
				<powersi:toolbar>
				<powersi:toolbar-item position="right" id="matchListCondition" />
				</powersi:toolbar>
				<powersi:datagrid-column name="aaa027" label="定点中心编码" width="75" />
				<powersi:datagrid-column name="ake005" label="医院目录编码" />
				<powersi:datagrid-column name="ake006" label="医院目录名称" />
				<powersi:datagrid-column name="bkc144" label="中心目录编码" />
				<powersi:datagrid-column name="bkc143" label="中心目录名称" />
				<powersi:datagrid-column name="aka057" label="职工支付比例" width="80" />
				<powersi:datagrid-column name="bkc106" label="居民支付比例" width="80" />
	
				<powersi:datagrid-column name="aae036" label="匹配操作时间" width="130" />
				<powersi:datagrid-column name="aae406" label="匹配操作人" />
				<powersi:datagrid-column name="aae407" label="匹配操作人工号" width="80" />
				<powersi:datagrid-column name="aae011" label="修订人工号" />
				<powersi:datagrid-column name="bae100" label="修订人" />
			
				<powersi:datagrid-column name="bkc139" label="医院目录规格型号" />
				<powersi:datagrid-column name="bkc140" label="医院目录单价" />
				<powersi:datagrid-column name="bkc141" label="医院目录厂家" />
				<powersi:datagrid-column name="aka020" label="拼音码" />
				<powersi:datagrid-column name="aka021" label="五笔码" />
				<powersi:datagrid-column name="bkm017" label="药监本位码" />
				<powersi:datagrid-column name="aaz107" label="定点医疗机构编号" />
				<powersi:datagrid-column name="akb020" label="医院编码" hide="true" />
				<powersi:datagrid-column name="ka40id" label="医院目录编号" hide="true" />
				<powersi:datagrid-column name="bkc109" label="中心目录编号" />
				<powersi:datagrid-column name="bkc194" label="匹配目录ID" width="135" />
			</powersi:datagrid>
		</div>
		<div id="divTab2" class="row">
			<div class="col-6">
				<powersi:datagrid id="hospCata" title="医院待匹配目录" url="${rootPath }/medicare/HospCataAction!queryHospCata.action"
					onDblClickRow="onDblClickRow_hospCata" delayLoad="true">
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="hospCataCondition" />
					</powersi:toolbar>
					<powersi:datagrid-column name="ake006" label="匹配状态" />
					<powersi:datagrid-column name="ake003" label="医院项目编码" code="ake003" />
					<powersi:datagrid-column name="bkc138" label="医院项目名称" code="aka070" />
				</powersi:datagrid>
			</div>
			<div class="col-6">
				<powersi:datagrid id="centerCata" title="中心可匹配目录" url="${rootPath }/medicare/HospCataAction!queryCenterCata.action"
					onDblClickRow="onDblClickRow_centerCata" onSuccess="onSuccess_centerCata" delayLoad="true">
					<powersi:toolbar>
						<powersi:toolbar-item position="right" id="centerCataCondition" />
					</powersi:toolbar>
					
					<powersi:datagrid-column name="bkc143" label="医保项目编码" />
					<powersi:datagrid-column name="ake003" label="医保项目名称" code="ake003" />
				</powersi:datagrid>
			</div>
		</div>
				
	<%-- 		<powersi:panelbox key="panel_query" title="匹配信息" allowCollapse="false">		
			<powersi:datagrid id="matchList" formId="saveMatch"
				showReload="false" enabledSort="false" delayLoad="true"
				onDblClickRow="dbSelectRow" autoWidth="true" autoHeight="true">
				<powersi:datagrid-column name="ake005" label="医院项目编码" with="20%" />
				<powersi:datagrid-column name="ake006" label="医院项目名称" with="15%" />
				<powersi:datagrid-column name="ake006" label="医保项目编码" with="15%" />
				<powersi:datagrid-column name="ake006" label="医保项目名称" with="15%" />
				<powersi:datagrid-column name="ake006" label="匹配生效日期" with="15%" />
				<powersi:datagrid-column name="ake006" label="匹配失效日期" with="15%" />
			</powersi:datagrid>
	</powersi:panelbox>  --%>
	</powersi:tabbedpanel>

	<powersi:errors />

	<script type="text/javascript">

	
	
	//选择行事件
	function selectRow(rowdata, rowid, rowobj){
		var row = hospCata.getRow(rowid);
  		var ake006 = row['ake006'];
  		$("#bkc143").val(ake006);
  		$("#btCenter").click();
	}
	
	
	function itemClick(item){
		var itemid = item["id"];
		
		//结束编辑状态
		centerCata.endEdit();
		
		if(itemid == "search"){
			centerCata.toggleCondition();
		} else if(itemid == "clean"){
			centerCata.clearCondition();
			centerCata.clearSort();
		} 
	}
	
	//手动保存匹配
  	function doMatch(){
  		
  		popupDialog({
			url: "<%=path%>/pages/biz/medicare/catalog/MatchDate.jsp",
			onClosed: function() {
				var ret = this.returnValue;
				
				var d = powersi.tojson(ret);
				
				var aae030,aae031;
			
				if(ret == "" || ret == null){
					aae030 = "20150101";
					aae031 = "20991231";
				}else{
					aae030 = d.aae030;
					aae031 = d.aae031;
				}
				
				if(aae031 == "" || aae031 == null){
					aae031 = "20991231";
				}
				

				var loginUser = '<%=loginUser%>';
				var userName = '<%=userName%>';
				
				var aae408 = $('#today').val();
		     	
				//获取单行，第一次勾选的数据
		  		var rowHosp = hospCata.getSelectedRow();
				
		  		if(powersi.isempty(rowHosp)){
					popupAlert("请选择医院目录！");
					return;
				}
		  		
		  		var hospAke006 = rowHosp['ake006'];
			    //医院药品目錄類別
				var ake003 = rowHosp['ake003'];
				if(ake003 == '' || ake003 == null ){
					popupAlert("医院目录类别不能为空！");
			    	return;
				}
			    var ka40id = rowHosp['ka40id'];
			    if(ka40id == '' || ka40id == null ){
					popupAlert("医院目录序列号不能为空！");
			    	return;
				}
			    var aaa027 = rowHosp['aaa027'];
				if(aaa027 == '' || aaa027 == null ){
					popupAlert("医院目录行政区划不能为空！");
			    	return;
				}
				var hospBkm017 = rowHosp['bkm017'];
		  		
		  		var rowCenter = centerCata.getSelectedRow();
		  		
		  		if(powersi.isempty(rowCenter)){
					popupAlert("请选择医保目录！");
					return;
				}
		  		
		  		var bkc109 = rowCenter['bkc109'];
		  		if(bkc109 == '' || bkc109 == null ){
					popupAlert("医保目录序列号不能为空！");
			    	return;
				}
		  		
		  		
				var center_ake003 = rowCenter['ake003'];
				if(center_ake003 == '' || center_ake003 == null ){
					popupAlert("医保目录类别不能为空！");
			    	return;
				}
				
				var centerBkm017 = rowCenter['bkm017'];
				
		  		if(ake003 != center_ake003){
		  			popupAlert("匹配的目录类别不一致，请重新选择！");
					return;
		  		}
		  		
		  		if(centerBkm017 != '' && centerBkm017 != null ){
			  		if(ake003 == '西药' || ake003 == '中成药'){
			  			if(hospBkm017 != centerBkm017){
			  				popupAlert(hospAke006+"的药监本位码录入错误或者不存在，请重新维护！");
			  				return;
			  			}
			  		}
				}
		  		
							
		  		//新设一个编辑行
		  		matchList.addEditRow({'ake005': rowHosp['ake005'],'ake006': rowHosp['ake006'],'bkm017': rowHosp['bkm017'],
		  			'bkm019': rowHosp['bkm019'],'bkc138': rowHosp['bkc138'],'bkc139': rowHosp['bkc139'],'bkc140': rowHosp['bkc140'],
		  			'bkc141': rowHosp['bkc141'],'bkc142': rowHosp['bkc142'],'bkc148': rowHosp['bkc148'],'bkm020': rowHosp['bkm020'],
		  			'bkm021': rowHosp['bkm021'],'bkm022': rowHosp['bkm022'],'bkm023': rowHosp['bkm009'],'bkm024': rowHosp['bkm007'],
		  			'bkm025': rowHosp['bkm025'],'bkm026': rowHosp['bkm026'],'bkm027':rowHosp['bkm027'],
		  			'bkm028':rowHosp['bkm028'],'bkm018':rowHosp['bkm018'],'bkc144': rowCenter['bkc144'],'bkc143': rowCenter['bkc143'],
		  			'aae030':aae030,'aae031':aae031,'aae407':loginUser,'aae408':aae408,'aae406':userName,'aae011':loginUser,
		  			'aae036':aae408,'bae100':userName,'ake003':ake003,'akb020':rowHosp['akb020'],'aaa027':aaa027,'ka40id':ka40id,
		  			'bkc109':bkc109,'aka020': rowHosp['aka020'],'aka021': rowHosp['aka021']
		  		});
		  	    //结束编辑状态
				matchList.endEdit();
		  	    
		  	    //删除选择的行
				hospCata.deleteSelectedRow();
			}
		}, 150, 300);
  		
  
  	}
  	
	//自动匹配
  	function doAutoMatch(){
  		if (!confirm("自动匹配会对整个医院未匹配目录进行匹配操作，确定操作吗？")) {
			return;
		}
  		hospCata.reset();
  		postJSON("<%=path%>/medicare/HospCataAction!doAutoMatch.action", {'hospCataDto.type':'cata'}, function(json){
			if(!checkResult(json)){
				return;
			}
			matchList.loadData(json.data);
		});
  	} 
  	
  	//保存匹配列表的信息
  	function doSaveMatch(){
  		var rows = matchList.getAllData();
		if(powersi.isempty(rows)){
			popupAlert("没有数据，无需保存");
			return;
		}
		
		var invalid = false;
  		//判断结果集
		$.each(rows, function(i, row){
			if (row['bkc140'] == '' || row['bkc140'] == null) {
				invalid = true;
				popupAlert("医院单价不能为空，请检查信息！");
				return false;
			}
			if (row['bkc138'] == '' || row['bkc138'] == null) {
				invalid = true;
				popupAlert("医院剂型不能为空，请检查信息！");
				return false;
			}
			if (row['bkm024'] == '' || row['bkm024'] == null) {
				invalid = true;
				popupAlert("医院批准文号不能为空，请检查信息！");
				return false;
			}
		});
  		
		if(invalid){
  			return;
  		}
  		
		var data = powersi.tostring(rows);
		
		//popupAlert(data);
		
		postJSON("<%=path%>/medicare/HospCataAction!saveMatchDetail.action",
					{
						"data" : data
					}, function(json) {
						if (!checkResult(json)) {
							return;
						}
						popupInfo(json.data);
						matchList.reset();
						matchList.acceptChanges();
						matchList.filteredData = {};

						$("#queryHospCata").submit();
					});

		}

		//双击匹配信息列表，删除选择的行
		function dbSelectRow(rowdata, rowid, rowobj) {
			if (!confirm("您确认删除此条药品的匹配吗?")) {
				return;
			}

			matchList.deleteRow(rowid);
		}

		function init() {
			$("#ake006").val("");
			$("#ake003").val("");
			$("#bkc143").val("");
			$("#ake003_c").val("");

			$("#queryHospCata").submit();
			$("#queryCenterCata").submit();
			matchList.reset();
		}
		
		

		function onDblClickRow_matchList() {
			popupConfirm("确定删除此条匹配信息吗？", "提示", function(isOk) {
				if (isOk) {
					var row = matchList.getSelectedRow();
					if ("${AAE016_KAE8_3}" == row.aae016) {
						popupAlert("待中心审核的目录不能删除", "提示", "error");
						return;
					}
					postJSON("${rootPath}/medicare/HospCataAction!delMatchCata.action", {
						data : powersi.tostring([ {
							bkc194 : row.bkc194
						} ]),
						caa027 : $("#caa027").val()
					}, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							popupAlert("删除成功", "提示", "info");
							$("#matchList .l-button:contains(查询)").click();
						}
					});
				}
			});
		}
		
		function onDblClickRow_hospCata(data, rowid, rowdata) {
			$("input[ligeruiid=centerCata_ake006]").val(data.ake006);
			$("#centerCata .l-button:contains(查询)").click();
		}
		function onDblClickRow_centerCata(data, rowid, rowdata) {
			btnOkMatch.click();
		}
		

		function onSuccess_centerCata(data, grid) {
			if (hospCata.getSelectedRow() && data.rows.length == 1) {
				setTimeout(function() {
					grid.selectRow(0, 'select');
					btnOkMatch.click();
				}, 100);
			}
		}
	</script>

</body>
</powersi:html>