<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath();
%>
<powersi:html>
<head>
<powersi:head title="转诊转院查询与修改" />
</head>
<body>
	<powersi:form id="mainForm" namespace="/medicare"
		action="ChangeHospitalBusApplyAction!getSpeBsInfoForEdit.action">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="button_query" value="查询" onclick="queryAll()" />
				<powersi:button key="button_reset" value="重置" onclick="clearall()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
			    	<powersi:hidden id="aac001" name="mediSpecDto.aac001"/>
			    </powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo()" />
					<powersi:textfield id="aac002" label="身份证号"
						name="mediSpecDto.aac002" key="aac002" readonly="true" />
					<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" />
					<td align="right" class="tdLable"
						style="background: #f4f8fa none repeat scroll 0 0">转院类型</td>
					<td><select id="bka006" name="mediSpecDto.bka006"
						class="select">
							<option value="">请选择...</option>
							<option value="120">住院转诊转院</option>
							<option value="110">门诊转诊转院</option>
					</select></td>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<td align="right" class="tdLable"
						style="background: #f4f8fa none repeat scroll 0 0">转院类别</td>
					<td><select id="jsz005" name="mediSpecDto.jsz005"
						class="select">
							<option value="1">本院转出</option>
							<option value="2">转入本院</option>
					</select></td>

					<powersi:textfield label="录入时间" mask="date" format="dateFmt:'yyyy-MM-dd'"
						id="aae030" name="mediSpecDto.aae030" key="aae030" readonly="true"
						required="true" />
					<powersi:textfield label="到" mask="date" format="dateFmt:'yyyy-MM-dd'"
						id="aae031" name="mediSpecDto.aae031" key="aae031" readonly="true"
						required="true" />

					<powersi:codeselect id="aae016" name="mediSpecDto.aae016"
						codeType="aae016_specail" label="中心审核标志" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="申请人员列表">
			<powersi:datagrid id="changeHospgrid" onDblClickRow="dbSelectRow">
				<powersi:datagrid-column id="aac003" name="aac003" label="姓名"  frozen="true" />
				<powersi:datagrid-column id="aac004_name" name="aac004_name" label="性别"  frozen="true" />
				<powersi:datagrid-column id="aac006" name="aac006" label="出生日期" frozen="true" />
				<powersi:datagrid-column id="aac002" name="aac002" label="社会保障号" />
				<powersi:datagrid-column id="aaz267" name="aaz267" label="申请号"/>
				<powersi:datagrid-column id="bka006_name" name="bka006_name" label="待遇类型" render="renderDictionary" />
				<powersi:datagrid-column id="akb021" name="akb021" label="转出医院"/>
				<powersi:datagrid-column id="ake014" name="ake014" label="转出时间"/>
				<powersi:datagrid-column id="akc172" name="akc172" label="转入医院"/>
				<powersi:datagrid-column id="ake016" name="ake016" label="转入时间"/>
				<powersi:datagrid-column id="aab069" name="aab069" label="单位名称"/>
				<powersi:datagrid-column id="aae016_name" name="aae016_name" label="中心审核标志"/>
				<powersi:datagrid-column id="bke022" name="bke022" label="审核时间"/>
				<powersi:datagrid-column id="bke021" name="bke021" label="审批人"/>
				<powersi:datagrid-column id="aae013" name="aae013" label="备注"/>
				<powersi:datagrid-column id="bke129" name="bke129" label="申请日期"/>
			</powersi:datagrid>
		</powersi:groupbox>
	</powersi:form>
	<tags:transCode />
	<powersi:errors />

	<script type="text/javascript">	

window.onload = function(){
	//申请结束时间为当天
	 var myDate = new Date();
	 var year = myDate.getFullYear();
	 var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
	 var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
	 
	 var lastMonthDate = myDate;
	 lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
	 var lastYear = lastMonthDate.getFullYear();
	 var lastMonth = (lastMonthDate.getMonth()+1).toString().length ==1?"0"+(lastMonthDate.getMonth()+1).toString():(lastMonthDate.getMonth()+1).toString();

	 $('#aae030').val(lastYear+"-"+lastMonth+"-"+"01");
	 $('#aae031').val(year+"-"+month+"-"+day);
}


//查询业务申请人员基本信息
function queryPresonInfo(){
   if (window.event.keyCode == 13) {
		var tracestring = powersi.trim($("#tracestring").val());
		if (powersi.isnull(tracestring)) {
			return;
		}
		$("#tracestring").attr("disabled", "disabled");
		findAac002(tracestring);
		$("#tracestring").removeAttr("disabled");
	}
}
function findAac002(tracestring){
	    var indi_id = 0;
		var argValue = tracestring.substr(0);
	   //调用业务申请人员基本信息action
	   postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action",
				{
					"mediSpecDto.arg_name" : indi_id,
					"mediSpecDto.arg_value" : argValue
				}, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data.personinfo.length > 1) {//一个身份证对应多个电脑号，弹出选择窗口
						var personinfo = json.data.personinfo;
						$("#openDiv").remove();
						$("form").append("<div id='openDiv' ><div id='divGrid'></div><div class='space-y'></div></div>");
						$("#divGrid").ligerGrid({
					            columns: [
					                    { display: '姓名', name: 'aac003', width: '60'},
					                    { display: '电脑号', name: 'aac001', width: '80'},
						                { display: '社会保障号码', name: 'aac002', width: '100'},
						                { display: '单位编码', name: 'aab001', width: '100' },
						                { display: '单位名称', name: 'bka008', width: '100' }
						                ], 
					            height: 360,
					            width: '100%',
					            rowHeight: 28,
					            data:personinfo,
					            headerRowHeight: 29,
					            pageSizeOptions: [5, 10, 20],
					            pageStatMessage: '显示 {from} - {to}，共 {total} 条',
					            checkbox: true,
					            isMultiSelect:false,
					            rownumbers: true,
					            usePager:false,
					            showReload: false,
					            onSelectRow:function(data,rowid,rowdata){
					            	var indi_id=data['aac001'];
					            	postJSON(
									rootPath
											+ "/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action?mediSpecDto.arg_name=indi_id&mediSpecDto.arg_value="
											+ indi_id,
									function(json) {
										if (!checkJSONResult(json)) {
											return;
										}
										$.each(json.data.personinfo[0],
											function(key, value) {
												if (!powersi.isnull(value)) {
													$("#" + key).val(value);
												}
											});
									});
					        	  dlg.close();
					            }
					       }); 
						    var dlg =popupDiv('#openDiv', '选择参保人信息 ', 500, {
						  		showMax: true, 
						  		isHidden: false
						  	}); 
					
					}else{
						$.each(json.data.personinfo[0],
							function(key, value) {
								if (!powersi.isnull(value)) {
									$("#" + key).val(value);
								}
							});
					}	
					
				});
	}

function queryAll(){
	if(!checkForm()){
		 return;
	  }
	 	if($('#aae030').val()>$('#aae031').val()){
	 		popupAlert('申请开始时间必须小于等于申请结束时间!');
	 	 	return;
	 	}  	  
	 	var beginDate = $('#aae030').val();
		var endDate = $('#aae031').val();
		var date1 = new Date(endDate.replace(/-/g, "/"));
		var date2 = new Date(beginDate.replace(/-/g, "/"));
		var days = ((date1-date2)/(1000*3600*24)+1);
		if(days>94){
			popupAlert("申请开始与结束时间之间不能大于3个月！");
			return;
		}
		 changeHospgrid.reset();
		 var saveItemData = $("#mainForm").serialize();
			postJSON("<%=path%>/medicare/ChangeHospitalBusApplyAction!getSpeBsInfoForEdit.action",saveItemData,afterQuery);
	}
	
	function afterQuery(json){
		if(!checkJSONResult(json)){
		    return;
	    }
		if(json.message !=""){
			popupInfo(json.message);
			return;
		}
		//加载结果集到datagrid
		changeHospgrid.loadData(json.data);
	}
	//双击行查询人员详细信息
	function dbSelectRow(index){//弹窗
		var row = changeHospgrid.getRow(index);
		var aaz267 = row['aaz267'];
		popupDialog({
			url:"<%=path%>/medicare/ChangeHospitalBusApplyAction!querySpeBsInfoForEdit.action?mediSpecDto.aaz267="
								+ aaz267,
						onClosed : function() {
							var ret = this.returnValue;
							queryAll();
						}
					}, 450, 800);

		}

		function clearall() {
			$("#tracestring").attr("disabled", false);
			$("#tracestring").val('');
			$("#aac002").val("");
			$("#aac003").val("");
			$("#bka004").val("");
			$("#aae016").val("");
			changeHospgrid.reset();
		}
	</script>
</body>
</powersi:html>