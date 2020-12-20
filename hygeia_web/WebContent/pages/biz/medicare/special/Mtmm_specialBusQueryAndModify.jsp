<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath(); 
	String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
	<head>
		<powersi:head title="门慢业务查询与修改" />
	</head>
	<body>
		<powersi:form id="mainForm" namespace="/medicare"
			action="MtmmSpecialApplyAction!getSpeBsInfoForEdit.action">
			<powersi:panelbox title="查询条件">	
				<powersi:panelbox-toolbar>
					<powersi:button key="button_query" value="查询" onclick="queryAllInfo()"/>
					<powersi:button key="button_reset" value="重置" onclick="clearall()"/>
					<powersi:button cssClass="button" value="打 印" onclick="printInfo()" />
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
						<powersi:codeselect id="aka083" key="门慢项目" 
							name="mediSpecDto.aka083" codeType="bka006"  cssClass="select2" 
							codeFilter="data_value like '13%' " codeSort="1" required="false" codeLocal="<%=aaa027%>" />
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield label="录入时间" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae030" 
						name="mediSpecDto.aae030"  readonly="true" required="true"/>
						<powersi:textfield label="到" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae031" 
						name="mediSpecDto.aae031"  readonly="true" required="true"/>
				
						<powersi:codeselect label="人员类型" id="bka004" name="mediSpecDto.bka004" key="bka004" codeType="bka004"  />
						<powersi:codeselect id="aae016" name="mediSpecDto.aae016"
						codeType="aae016_specail" label="中心审核标志" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:panelbox>
			<powersi:groupbox title="人员信息列表">
				<powersi:datagrid id="querygrid"  onDblClickRow="doubleClick" delayLoad="true">
					<powersi:datagrid-column id="aac003" name="aac003" label="姓名"     width="60" />
					<powersi:datagrid-column id="aac004_name" name="aac004_name" label="性别"     width="50" />
					<powersi:datagrid-column id="aac006" name="aac006" label="出生日期"  width="80" />
					<powersi:datagrid-column id="aac002" name="aac002" label="身份证号"  width="150" />
					<powersi:datagrid-column id="aab069" name="aab069" label="单位名称"  width="250" />
					<powersi:datagrid-column id="aaz267" name="aaz267" label="序列号"    width="80" />
					<powersi:datagrid-column id="bka155" name="bka155" label="门慢项目" width="150" />
					<powersi:datagrid-column id="aka121" name="aka121" label="疾病名称" width="100" />
					<powersi:datagrid-column id="akb021" name="akb021" label="医院名称" width="150" />
					<powersi:datagrid-column id="aae030" name="aae030" label="开始时间" width="80" />
					<powersi:datagrid-column id="aae031" name="aae031" label="结束时间" width="80" />
					<powersi:datagrid-column id="aae016_name" name="aae016_name" label="中心审核标志"  width="60" />
					<powersi:datagrid-column id="bke022" name="bke022" label="审核时间" width="80" />
					<powersi:datagrid-column id="bke021" name="bke021" label="审核人"   width="60" />
					<powersi:datagrid-column id="aae014" name="aae014" label="检查人"   width="80" />
					<powersi:datagrid-column id="aae015" name="aae015" label="检查时间" width="80" />
					<powersi:datagrid-column id="aae013" name="aae013" label="备注"    width="250" />
				</powersi:datagrid>
			</powersi:groupbox>		
		</powersi:form>
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
	 var lastMonth = (lastMonthDate.getMonth()+1).toString().length==1?"0"+(lastMonthDate.getMonth()+1).toString():(lastMonthDate.getMonth()+1).toString();

	 $('#aae030').val(lastYear+"-"+lastMonth+"-"+"01");
	 $('#aae031').val(year+"-"+month+"-"+day);
}

//查询待修改的全部人员信息
function queryAllInfo(){

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
		querygrid.reset();
		
       	var saveItemData = $("#mainForm").serialize();
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!getSpeBsInfoForEdit.action",saveItemData,afterQuery);
}

function afterQuery(json){
	if(!checkJSONResult(json)){
	    return;
    }
	if(json.message !=""){
		popupInfo(json.message);
		clearall();
	}
	//加载结果集到datagrid
	querygrid.loadData(json.data);
}

function doubleClick(index){//双击弹窗
	var row = querygrid.getRow(index);
	var aaz267 = row['aaz267'];
	
	popupDialog({
		url:"<%=path%>/medicare/MtmmSpecialApplyAction!querySpeBsInfoForEdit.action?mediSpecDto.aaz267="+aaz267,
		onClosed:function(){
			var ret = this.returnValue;
			queryAllInfo();
		}
	}, 450, 800);
}
function clearall(){
		
	$("#tracestring").attr("disabled", false);
	$("#tracestring").val('');
	$("#aac002").val("");
	$("#aac003").val("");
	$("#bka004").val("");
	$("#aae016").val("");
	
	querygrid.reset();
}

function printInfo(){
    if(querygrid.getRowsCount() < 1){
    	popupAlert("请先查询出需要打印的数据！");
        return;
    }
    var grid_list = querygrid.getSelectedRow();
    if(grid_list == null || grid_list == ""){
    	popupAlert("请勾选要打印的数据！");
    	return;
    }
    var aaz267Value = grid_list.aaz267;
    var url = '${rootPath}/pages/biz/medicare/special/printSpecialBusApplyInfo.jsp?aaz267='+aaz267Value ;
    popupDialog(url,800,1000,function(ret){});
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
</script>
</body>
</powersi:html>