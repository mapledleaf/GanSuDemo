<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<% 
	String path = request.getContextPath();
%>

<powersi:html>
	<powersi:head title="门诊选点业务查询与修改"/>
<body>
	<powersi:form id="mainForm" namespace="/medicare" action="MzchoHospitalBusApplyAction!getModifyPerInfoList.action">
		 <div id = "div1" style="display: none">
	    	 <powersi:hidden id="aac001" name="mediSpecDto.aac001"/>
	    	 <powersi:hidden id="aac002" name="mediSpecDto.aac002"/>
		</div>
	<powersi:panelbox title="查询条件">
		<powersi:panelbox-toolbar>
			<powersi:button key="button_query"  value="查询" onclick="queryAll()"/>
			<powersi:button key="button_export" value="导出" onclick="modifygrid.exportExcel()"/>
		    <powersi:button key="button_reset"  value="重置" onclick="clearall()"/>
		</powersi:panelbox-toolbar>
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
			    <powersi:textfield id="arg_value" name="mediSpecDto.arg_value" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo()" />
				<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" />		
				<powersi:textfield label="录入时间" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae030" name="mediSpecDto.aae030"  readonly="true"/>
				<powersi:textfield label="到" mask="date" format="dateFmt:'yyyy-MM-dd'" id="aae031" name="mediSpecDto.aae031"  readonly="true"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
			    <powersi:codeselect label="人员类别" id="bka004" name="mediSpecDto.bka004" key="bka004"  codeType="bka004"/>
				<powersi:codeselect id="aae100" name="mediSpecDto.aae100"
						key="aae100" codeType="valid_flag" label="有效标志"  headerKey="-1"/>
				<td align="right" class="tdLabel">申请类型</td>
				<td>
					<select id="bkb100" name="mediSpecDto.bkb100" class="select" onchange="">
						<option value="0">医院申请</option>
						<option value="1">中心申请</option>
					</select>
				</td>
				<td align="right" class="tdLabel">门诊业务类别</td>
				<td>
					<select id="bka006" name="mediSpecDto.bka006" class="select" onchange="">
						<option value="">全部</option>
						<option value="110">普通门(急)诊</option>
						<option value="11B">城居生育产检</option>
						<option value="51C01">产前检查</option>
					</select>
				</td>
			</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
	</powersi:form>
			<powersi:groupbox title="申请人员列表">
			<powersi:datagrid id="modifygrid" onDblClickRow="DubSelectRow" 
			exportFileName=" '门诊选点人员信息列表' "  delayLoad="true"
			enabledEdit="false" clickToEdit="false" showExportExcel="false">
				<powersi:datagrid-column id="bkb100" name="bkb100" label="申请类型" />
				<powersi:datagrid-column id="akb021" name="akb021" label="申请医院" />
				<powersi:datagrid-column id="bka006_name" name="bka006_name" label="待遇类型" />
				<powersi:datagrid-column id="aka084" name="aka084" label="特殊业务标识"/>
				<powersi:datagrid-column id="aaz267" name="aaz267" label="申请号" />
				<powersi:datagrid-column id="aac003" name="aac003" label="姓名" />
				<powersi:datagrid-column id="aac004_name" name="aac004_name" label="性别" />
				<powersi:datagrid-column id="aac006" name="aac006" label="出生日期" />
				<powersi:datagrid-column id="aac002" name="aac002" label="公民身份证"/>
				<powersi:datagrid-column id="bka004" name="bka004" label="人员类别" />
				<powersi:datagrid-column id="aae030" name="aae030" label="开始时间" />
				<powersi:datagrid-column id="aae031" name="aae031" label="结束时间" />
				<powersi:datagrid-column id="aae100_name" name="aae100_name" label="有效标志"/>
				<powersi:datagrid-column id="aae016_name" name="aae016_name" label="审核标志"/>
				<powersi:datagrid-column id="bkb101" name="bkb101" label="初审时间"/>
				<powersi:datagrid-column id="bkb102" name="bkb102" label="初审人"/>
				<powersi:datagrid-column id="aae015" name="aae015" label="复审时间" />
				<powersi:datagrid-column id="aae014" name="aae014" label="复审人"/>
				<powersi:datagrid-column id="aaz217" name="aaz217" label="就医登记号" />
				<powersi:datagrid-column id="bmc016" name="bmc016" label="预产期" />
				<powersi:datagrid-column id="aae013" name="aae013" label="备注"/>
			</powersi:datagrid>
		</powersi:groupbox>
		<powersi:errors/>
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
				queryAll();
			}
		}
		
		function queryAll(){
			if(!checkForm){
				return;
			}
			if($("#aae030").val() > $("#aae031").val()){
				popupAlert("申请开始时间必须小于申请结束时间!");
				return;
			}
			var beginDate = $("#aae030").val();
			var endDate = $("#aae031").val();
			var date1 = new Date(endDate.replace(/-/g,"/"));
			var date2 = new Date(beginDate.replace(/-/g,"/"));
			var days = ((date1-date2)/(1000*3600*24)+1);
			if(days > 366){
				popupAlert("申请开始时间与申请结束时间不能大于1年!");
				return;
			}
			modifygrid.reset();
			
			var saveItemData = $("#mainForm").serialize();
			postJSON("<%=path%>/medicare/MzchoHospitalBusApplyAction!getModifyPerInfoList.action",saveItemData, function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
					modifygrid.loadData(json.data);
			});
		}
		
		function DubSelectRow(index){//双击行,弹窗查详细信息
			
			var row = modifygrid.getRow(index);
			var serial_apply = row['aaz267'];
			popupDialog({
				url:"<%=path%>/medicare/MzchoHospitalBusApplyAction!queryMzChoBsPersonInfo.action?mediSpecDto.aaz267="+serial_apply,
				onClosed:function(){
					var ret = this.returnValue;
					queryAll();
				}
			}, 350, 650);
		}
		function clearall(){		
			$("#aac001").val("");
			$("#aac002").val("");
			$("#aac003").val("");
			$("#bke023").val("");
			$("#bka004").val("");
			$("#bka006").val("");
			modifygrid.reset();
		}
	
	</script>
</body>
</powersi:html>