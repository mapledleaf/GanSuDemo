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
		<powersi:head title="门慢选点业务查询" />
	</head>
	<body>
		<powersi:form id="mainForm" namespace="/medicare"
			action="MtmmSpecialApplyAction!queryPersonChooseHosp.action">
			<powersi:panelbox title="查询条件">	
				<powersi:panelbox-toolbar>
					<powersi:button key="button_query" value="查询" onclick="queryAllInfo()"/>
					<powersi:button key="button_reset" value="重置" onclick="clearall()"/>
				</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="8">
				    <powersi:editorlayout-row>
				    	<powersi:hidden id="akb020" name="mediSpecDto.akb020"/>
				    </powersi:editorlayout-row>
					<powersi:editorlayout-row>
						<powersi:textfield label="经办时间" mask="date" format="dateFmt:'yyyy-MM-dd'" id="startDate" 
							name="mediSpecDto.startDate" />
						<powersi:textfield label="到" mask="date" format="dateFmt:'yyyy-MM-dd'" id="endDate" 
							name="mediSpecDto.endDate" />
							
						<powersi:textfield label="选点时间" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae030" 
							name="mediSpecDto.aae030" />
						<powersi:textfield label="到" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae031" 
							name="mediSpecDto.aae031" />
					</powersi:editorlayout-row>
				</powersi:editorlayout>
			</powersi:panelbox>
			<powersi:groupbox title="人员信息列表"> 
				<powersi:datagrid id="querygrid"  delayLoad="true"  onDblClickRow="doubleClick" showExportExcel="true" exportFileName="'人员选点记录'" >
				    <powersi:datagrid-column id="aaz267" name="aaz267" label="申请序列号"    width="120" />
					<powersi:datagrid-column id="aac003" name="aac003" label="姓名" width="60" />
					<powersi:datagrid-column id="aac004_name" name="aac004_name" label="性别" width="50" />
					<powersi:datagrid-column id="aac006" name="aac006" label="出生日期"  width="80" format="dateFmt:'yyyy-MM-dd'"/>
					<powersi:datagrid-column id="aac002" name="aac002" label="身份证号"  width="150" />
					<powersi:datagrid-column id="aab069" name="aab069" label="单位名称"  width="100" />
					<%-- <powersi:datagrid-column id="akb021" name="akb021" label="医院名称" width="150" /> --%>
					<powersi:datagrid-column id="bke003" name="bke003" label="病种编号" width="80" hide="true"/>
					<powersi:datagrid-column id="bke004" name="bke004" label="申请病种" width="80" />
					<powersi:datagrid-column id="bke055" name="bke055" label="选点原因" width="80" render="renderBke055"/>
					<powersi:datagrid-column id="aae036" name="aae036" label="经办时间" width="80" format="dateFmt:'yyyy-MM-dd'"/>
					<powersi:datagrid-column id="aae030" name="aae030" label="开始时间" width="80" format="dateFmt:'yyyy-MM-dd'"/>
					<powersi:datagrid-column id="aae031" name="aae031" label="结束时间" width="80" format="dateFmt:'yyyy-MM-dd'"/>
					<powersi:datagrid-column id="aae013" name="aae013" label="备注" width="200" />
				</powersi:datagrid>
			</powersi:groupbox>		
		</powersi:form>
	<powersi:errors />	
<script type="text/javascript">	

window.onload = function(){
	//querygrid.loadData([{akb020:"sr0001",aaz267:"000000",aac002:"441822195802225015"}]);
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

function renderBke055(rowdata, index, value) {
	if (value === '1') {
		return '<span title="新签约">新签约</span>';
	} else if(value === '2') {
		return '<span title="年度变更">年度变更</span>';
	} else if(value === '3') {
		return '<span title="新增病种变更">新增病种变更</span>';
	} else if(value === '4') {
		return '<span title="单位/住址变更">单位/住址变更</span>';
	}
}

//查询待修改的全部人员信息
function queryAllInfo(){

	 	/* if($('#aae030').val()>$('#aae031').val()){
 	 	 	popupAlert('申请开始时间必须小于等于申请结束时间!');
 	 	 	return;
 	 	}  */ 	  
	 	var beginDate = $('#aae030').val();
		var endDate = $('#aae031').val();
		var date1 = new Date(endDate.replace(/-/g, "/"));
		var date2 = new Date(beginDate.replace(/-/g, "/"));
		var days = ((date1-date2)/(1000*3600*24)+1);
		/* if(days>94){
			popupAlert("申请开始与结束时间之间不能大于3个月！");
			return;
		} */
		querygrid.reset();
		
       	var saveItemData = $("#mainForm").serialize();
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!queryPersonChooseHosp.action",saveItemData,afterQuery);
}

function afterQuery(json){
	if(!checkJSONResult(json)){
	    return;
    }
	if(json.message !=""){
		popupInfo(json.message);
	}
	//加载结果集到datagrid
	querygrid.loadData(json.data);
}

function doubleClick(index){//双击弹窗
	var row = querygrid.getRow(index);
	var aaz267 = row['aaz267'];
	var aac002 = row['aac002'];
	popupDialog({
		url:"<%=path%>/medicare/MtmmSpecialApplyAction!queryPersonChooseHosp.action?mediSpecDto.aaz267="+aaz267+"&mediSpecDto.aac002="+aac002,
		onClosed:function(){
			var ret = this.returnValue;
			queryAllInfo();
		}
	}, 350, 800);
}

function clearall(){
	$("#aae030").val("");
	$("#aae031").val("");
	querygrid.reset();
}

</script>
</body>
</powersi:html>