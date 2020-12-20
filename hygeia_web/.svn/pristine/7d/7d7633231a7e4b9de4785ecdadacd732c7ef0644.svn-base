<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath(); 
	String aae140 = request.getParameter("aae140");

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();

	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaz267value = request.getAttribute("mediSpecDto.aaz267")==null?"0":request.getAttribute("mediSpecDto.aaz267").toString();

	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat();
	String time = dformat.format(d);
%>
<powersi:html>
	<head>
		<powersi:head title="门特(门慢)特殊业务查询与修改" />
	</head>
	<body>
		<powersi:form id="mainForm">
			<div id="div1" style="display: none">
			<powersi:hidden id="indi_id" name="indi_id" value="indi_id" />
			<powersi:hidden id="idcard" name="idcard" value="idcard" />
			<powersi:hidden id="aka083" name="mediSpecDto.aka083" value="1sp"/>
		</div>
			<powersi:panelbox title="查询条件">	
				<powersi:panelbox-toolbar>
				    <powersi:button value="新增" onclick="addInfo()"/>
					<powersi:button value="查询" onclick="queryAllInfo()"/>
					<powersi:button name="btDel" label="删 除" id="btDel"
					onclick="doDel()" />
					<powersi:button value="重置" onclick="clearall()"/>
					<powersi:button value="打 印" onclick="printInfo()" />
				</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="8%,18%,9%,15%,9%,15%,10%,16%">
					<powersi:editorlayout-row>
						<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo()" />
						
						
						<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" />
					<powersi:textfield label="性别" id="aac004_name"  readonly="true" />
					<powersi:hidden id="aac004" name="mediSpecDto.aac004" />
					<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
					<powersi:hidden id="aac001" name="mediSpecDto.aac001"></powersi:hidden>
					<powersi:textfield label="身份证号" id="aac002"
						name="mediSpecDto.aac002" key="aac002" readonly="true" />
						
					</powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield label="出生日期" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aac006"
						name="mediSpecDto.aac006" key="aac006" readonly="true" />
						
						<powersi:textfield label="录入时间" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae030" name="mediSpecDto.aae030" readonly="true" />
						<powersi:textfield label="到" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae031" name="mediSpecDto.aae031" readonly="true" />
					
					<powersi:codeselect id="aae016" name="mediSpecDto.aae016"
						key="aae016" codeType="aae016_audit" label="审核标志" />
				</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:panelbox>
			<powersi:groupbox title="人员信息列表">
				<powersi:datagrid id="querygrid"  formId="mainForm"  onDblClickRow="doubleClick" 
				delayLoad="true" checkbox="true">
					<powersi:datagrid-column id="aac003" name="aac003" label="姓名"     width="60" />
					<powersi:datagrid-column id="aac004" name="aac004" label="性别"     width="50" />
					<powersi:datagrid-column id="aac006" name="aac006" label="出生日期"  width="80" />
					<powersi:datagrid-column id="aac001" name="aac001" label="电脑号"   />
					<powersi:datagrid-column id="aac002" name="aac002" label="身份证号"  width="150" />
					<powersi:datagrid-column id="aab069" name="aab069" label="单位名称"  width="250" />
					<powersi:datagrid-column id="aaz267" name="aaz267" label="序列号"    width="80" />
					<powersi:datagrid-column id="aka121" name="aka121" label="疾病名称" width="100" />
					<powersi:datagrid-column id="akb021" name="akb021" label="医院名称" width="150" />
					<powersi:datagrid-column id="aae030" name="aae030" label="开始时间" width="80" />
					<powersi:datagrid-column id="aae031" name="aae031" label="结束时间" width="80" />
					<powersi:datagrid-column id="aae016" name="aae016" label="审核标志"  width="60" />
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

	 $('#akb020').val("<%=hospital_id%>");
	 $('#akb021').val("<%=hospital_name%>");
	 $('#aae030').val(lastYear+"-"+lastMonth+"-"+"01");
	 $('#aae031').val(year+"-"+month+"-"+day);
	
	 $("#bke023").val("");
	 $("#aae016").val("");
	 $("#aae100").val("1");
	 $('#aaz267').attr("readonly",true);
	 
}
function addInfo(){
	popupDialog({
			url: "<%=path%>/pages/biz/medicare/special/PaysCompensationNew.jsp",
			onClosed: function() {
				var ret = this.returnValue;
				//queryAllInfo();
			}
		}, screen.height, screen.width);
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
		postJSON("<%=path%>/medicare/TeamVsSelfPayApplyAction!queryPaysCompensationList.action",saveItemData,afterQuery);
}

function afterQuery(json){
	if(!checkJSONResult(json)){
	    return;
    }
	if(json.message !=""){
		popupInfo(json.message);
		clearall();
		return;
	}
	//加载结果集到datagrid
	querygrid.loadData(json.data);
//	mainForm.json.data
}

function doubleClick(index){//双击弹窗
	var row = querygrid.getRow(index);
	var aaz267 = row['aaz267'];
	
	popupDialog({
		url:"<%=path%>/medicare/TeamVsSelfPayApplyAction!getDetailByAaz267.action?mediSpecDto.aaz267="+aaz267,
		onClosed:function(){
			var ret = this.returnValue;
			queryAllInfo();
		}
	}, 450, 800);
}
function clearall(){
		
	$("#aac006").val("");
	$("#aae016").val("");
	$("#bka006").val("");
	$("#arg_value").val("");
	$("#aac012").val("");
	$("#bka004").val("");
	querygrid.reset();
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
		var indi_id = $("#indi_id").val();
		var idcard  = $("#idcard").val();
		var argValue = tracestring.substr(0);
		var Arg_name = indi_id;
		//根据电脑号和身份证号的长度来判断输入的是电脑号还是身份证号
		if(argValue.length == "10"){
			Arg_name = indi_id;
		}else if(argValue.length == "15" || argValue.length == "18"){
			Arg_name = idcard;
		}
		
	   //调用业务申请人员基本信息action
	   postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action",
			   {"mediSpecDto.arg_name":Arg_name,"mediSpecDto.arg_value":argValue},function(json){
		   if(!checkJSONResult(json)){
			   return;
		   }			   
			if (json.errortype==0) {
				$.each(json.data,function(key,value){
				    $("#"+key).val(value);
				});
			}else{
				popupInfo(json.message);
				return;
			}
	   });
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

function doDel() {
		
  	//获取多选，全部勾选的数据
  		var rows = querygrid.getSelectedRows();
  		/* popupAlert(rows); */
  		
  		if(powersi.isempty(rows)){
			popupAlert("请选择申请信息！");
			return;
		}
  		
  		var invalid = false;
  		//判断结果集是否为空，为空下面循环取值会抛异常
		$.each(rows, function(i, row){
			if(row['aae016'] != "未审核"){
				invalid = true;
				querygrid.select(row);
				popupAlert(row['aac003']+':审核通过后的信息不能删除！');
				querygrid.reload(true);
				return false;
			}
		});
  		
  		if(invalid){
  			return;
  		}
  		if (!confirm("您确认删除选择的申请信息吗?")) {
            return;
        }
  		
  		var data = powersi.tostring(rows);
  		
  		postJSON("<%=path%>/medicare/TeamVsSelfPayApplyAction!delPaysCompensation.action",
				{
					"data" : data
				}, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					popupInfo(json.message);
					querygrid.reload(true);
				});

	}
</script>
</body>
</powersi:html>