<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.powersi.hygeia.framework.util.DateFunc"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
         String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
         String aaa027 = BizHelper.getAaa027();
         String akb020 = BizHelper.getAkb020();
         String bka053 =  BizHelper.getLoginUser();//人工号
         String bka054 = BizHelper.getUserName();//操作人
         
%>
<powersi:html>

<powersi:head title="体检身份登记" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
 <%@ include file="/pages/util/header/combogrid.jsp"%> 
<body topmargin="0">
	<powersi:form id="registerFrom" method="post">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button id="btnSave" label="保存(S)" key="button_save"
						onclick="save();" disabled="true" />
					<powersi:button id="btnReset" key="button_clear"
						onclick="doClear();" label="清屏(C)"  />
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="0%,15%,10%,10%,15%,10%,10%,10%,10%,10%">
				<powersi:editorlayout-row>
					<td><powersi:codeselect codeType="caa027" id="caa027" name="setMealDTO.caa027" required="true"  label="中心系统"  headerKey="1"/></td>
					<td><select id="argName" class="select"  name="argName">
							<option value="aac002">身份证号码</option>
							<option value="aac001">个人电脑号</option>
							<option value="bka100">社保卡号</option>
					</select>
				
					</td>
					<td colspan="2"><powersi:textfield id="querystring" name="setMealDTO.querystring"
							title="请输入信息回车" placeholder="请输入信息回车！" buttonText="读卡" 
							onkeydown="findAac001(this)" buttonId="readic_button" /></td>
				<powersi:textfield id="bkh011" required="true"  name="setMealDTO.bkh011" label="年度" validate = "integer,max[209912],min[1901],maxSize[4],minSize[4]"
					 	onclick="WdatePicker({dateFmt:'yyyy'})" />
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:panelbox title="本次登记信息" allowCollapse="false">
	<powersi:editorlayout cols="6%,14%,10%,10%,10%,15%,10%,10%,10%,12%">
		<tr>
			<powersi:textfield id="aac003" key="姓名" name="setMealDTO.aac003" readonly="true"/>
			<powersi:textfield id="aac004_name" key="性别"   readonly="true" />
		    <powersi:hidden id="aac004" key="性别" name="setMealDTO.aac004"  />
			<powersi:textfield id="aac002" key="身份证号" name="setMealDTO.aac002" readonly="true"/>
			<powersi:textfield id="aac006" key="出生日期" name="setMealDTO.aac006"  readonly="true"/>
			<powersi:textfield id="aab069" key="所属单位" name="setMealDTO.aab069" readonly="true" />
		</tr>
		<tr>
			<powersi:textfield id="bka035_name" key="人员类别" readonly="true" />
			<powersi:hidden id="bka035" key="人员类别" name="setMealDTO.bka035" readonly="true" />
			<powersi:textfield id="baa027" key="所属中心" name="setMealDTO.baa027"  readonly="true"/>
			<powersi:textfield id="bac001_name" key="公务员级别"  readonly="true" />
			<powersi:hidden id="bac001" key="公务员级别" name="setMealDTO.bac001" readonly="true" />
			<powersi:textfield id="bke069" key="联系电话" name="setMealDTO.bke069" required="true"/>
			<%-- <powersi:textfield id="bkh002_name" key="体检进度"  readonly="true"/>
			<powersi:hidden id="bkh002" key="体检进度" name="setMealDTO.bkh002"  readonly="true"/> --%>
		</tr>
		<tr>
				<powersi:codeselect id="bkh015" name="setMealDTO.bkh015"   
				label="体检套餐" headerKey="" headerValue="请选择..."  cssClass="select2" list="#request.bkh015List" required="true" showValue="false" />
			<%-- <powersi:textfield id="bkh015_name" key="体检套餐"  readonly="true" />
			<powersi:hidden id="bkh015" key="体检套餐" name="setMealDTO.bkh015"  readonly="true" /> --%>
			<powersi:hidden id="bkh001" key="申报流水号" name="setMealDTO.bkh001"  readonly="true"/>
			<powersi:textfield id="bkh016" key="登记日期" name="setMealDTO.bkh016" mask="date" />
			<powersi:textfield id="bkh006" key="缴费月数" name="setMealDTO.bkh006" readonly="true" />
			<powersi:hidden id="bkh005" key="申报日期" name="setMealDTO.bkh005" readonly="true" />
		<%-- 	<powersi:textfield id="bkh003" key="体检生效日期" name="setMealDTO.bkh003"  readonly="true"/>
			<powersi:textfield id="bkh004" key="体检截止日期" name="setMealDTO.bkh004"  readonly="true"/> --%>
			<powersi:textfield id="aae006" key="居住地址" name="setMealDTO.aae006"  readonly="true"  colspan="3" />
		</tr>

		
	</powersi:editorlayout>
</powersi:panelbox>
		<powersi:hidden id="bacb16" name="setMealDTO.bacb16" value="1"/>
		<powersi:hidden id="aaa027" name="setMealDTO.aaa027"/>
		<powersi:hidden id="akb020" name="setMealDTO.akb020"/>
		<powersi:hidden id="readcardflag" name="setMealDTO.readcardflag"/>
		<powersi:hidden id="aac001" name="setMealDTO.aac001"/>
		<powersi:hidden id="bkh012" name="setMealDTO.bkh012"/>
		<powersi:hidden id="aab001" name="setMealDTO.aab001"/>
		<powersi:hidden id="bka053" name="setMealDTO.bka053"/>
		<powersi:hidden id="bka054" name="setMealDTO.bka054"/>
	</powersi:form>
</body>


<script type="text/javascript">
		var objCard = null;
		var saveRegisterSpecialFlag = false;
		$(document).ready(function() {
			 doClear();
			$("#querystring").focus();
			var myDate = new Date();
			$("#bkh011").val(myDate.getFullYear());
			
		});
		

		function readIcCard() {
			var querystring = powersi.trim($("#querystring").val());
			getPerson(querystring);
		}
		
		function init() {
			doClear();
		}
		
		/*查询参保信息*/
		function findAac001(para){
			if (para == "readic" || event.keyCode == '13') {
				var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					alert("请输入有效查询条件");
					return;
				}
				if('aac002' == $("#argName").val()){
					$("#argName option:first").prop('selected', 'selected'); 
					$("#argName option:selected").val('aac002');
					$("#argName").change();
					$("#argName").val('aac002');
					$("#argName").change();
				}
				if('aac002'==$("#argName").val()){
					checkIcCard(querystring);
					__isPing(identify(querystring));
				} else {
					
						getPerson(querystring);
				}
			}
		}
		

		
		function getPerson(querystring){
			var arg_name = $("#argName").val();
			var bkh011 = $("#bkh011").val();
			//查询人员之前进行页面清理
			doClear();
			 $("#querystring").val(querystring);
			 $("#bkh011").val(bkh011);
			 $("#argName").val(arg_name);
			 if(powersi.isnull(bkh011)){
				 alert("请选择体检年份");
				 return;                    
			 }
			 
			 var querystring = powersi.trim($("#querystring").val());
				if (powersi.isnull(querystring)) {
					popupAlert("请输入有效查询条件", "提示", "info");
					return;
				}
				var caa027 = powersi.trim($("#caa027").val());
				if (powersi.isnull(caa027)) {
					popupAlert("请选择中心系统", "提示", "info");
					return;
				}
				findAac002(querystring);
		}
		
		function findAac002(querystring){
			var aac001="";
			var aac002="";
			var aaz500="";
			var arg_name_temp="";
			var arg_name = $("#argName").val();
			if (arg_name == 'aac001') {
				arg_name_temp = "&setMealDTO.indi_id=" + querystring;
			} else if (arg_name == 'aac002') {
				arg_name_temp = "&setMealDTO.idcard=" + querystring;
			} else if (arg_name == 'bka100') {
				arg_name_temp = "&setMealDTO.insr_code=" + querystring;
			}
			var registerData = $("#registerFrom").serialize();
			registerData = registerData + arg_name_temp+"&setMealDTO.argName="+arg_name;
 			postJSON("${rootPath}/health/ExaminationAction!findAac001.action",
					registerData,function(json){
 				if(null == json){
 					popupAlert("未获得有效的人员信息！", "提示", "info");
 					return;
 				}
				if(!checkJSONResultNew(json)){
					if (arg_name=="bka100") {
						$("#querystring").prop("disabled", true);
					}
					return;
				}
				$("#btnSave").prop("disabled", false);	
				if (json.data != "no" && json.data.length > 1) {
					chooseAac002(aac001,aac002,aaz500);
					return;
				}else{
				//	alert(JSON.stringify(json.data));
					if (json.data != "no") {
						$.each(json.data, function(key, value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
						reloadBkh015();
						
						
					}
				}
			}); 
		}
		
		//根据已获取人员的年龄跟性别取对应的套餐
		function reloadBkh015(){ 
			postJSON("${rootPath}/health/ExaminationAction!querySetMeal.action?setMealDTO.aae100=1&"+
					"setMealDTO.aac004="+$("#aac004").val()+"&setMealDTO.aac006="+$("#aac006").val()+"&setMealDTO.flag=getSetMeal",
					function(json){
				$("#bkh015").empty();
				
				if (!checkJSONResultNew(json)) {
					return;
				}
				if (json.data != "no") {
					var a = [];
					$.each(json.data, function(key, value) {
						a.push('<option value="');
						a.push(key);
						a.push('"');
						a.push(">");
						a.push(value);
						a.push("</option>");
					});
					$("#bkh015").append(a.join(''));
					$("#bkh015").change();
				}
				if (!powersi.isnull(json.message)) {
					popupInfo(json.message);
				}
			})
		} 
		
		
		
		/*查询并选择参保信息*/
		function chooseAac002(aac001,aac002,aaz500) {
 			popupDialog(
					{
						url : "${rootPath}/health/ExaminationAction!choosePersonAac001.action?setMealDTO.indi_id="+aac001+
								"&setMealDTO.idcard="+aac002+"&setMealDTO.insr_code="+aaz500,
						onClosed : function() {
							var retValue = this.returnValue;
							if (retValue) {
								accessAac001(retValue.aac001);
							}
						}
					}, 500, 1000); 
		}

		
		function accessAac001(aac001) {
			if (powersi.isnull(powersi.trim(aac001))) {
				return;
			}
			var registerData = "setMealDTO.aac001=" + aac001
			+ "&setMealDTO.caa027=" + $("#caa027").val() +"&setMealDTO.bacb16=1" + "&setMealDTO.bkh011="+$("#bkh011").val();
			postJSON("${rootPath}/health/ExaminationAction!findAac001.action",
					registerData,function(json){
				if(!checkJSONResultNew(json)){
					return;
				}
				if (!powersi.isnull(json.message)) {
					popupAlert(json.message, "提示", "info");
				}
				if (json.data != "no") {
					$.each(json.data, function(key, value) {
						if (!powersi.isnull(value)) {
							$("#" + key).val(value);
						}
					});
				}
			
			});
			
		}
		
		function save(){
			if (!checkForm()) {
				return;
			}
			//默认未读卡  读卡后该标志改为1 用于中心判断
			$("#readcardflag").val("0");
			$("#aaa027").val("<%=aaa027%>");
			$("#akb020").val("<%=akb020%>");
			$("#bka053").val("<%=bka053%>");
			$("#bka054").val("<%=bka054%>");
			inputOff();
			var currDate = "<%=today%>";
			var bkh016 = parseInt($.trim($("#bkh016").val().replace(/-/g,"")));
			if(bkh016>parseInt(currDate)){
				popupAlert("登记时间不能在当前经办时间之后","提示","info");
				return;
			}
			
			var aac003 = powersi.trim($("#aac003").val());
			if (!confirm("您确认保存[" + aac003 + "]的体检登记信息吗?")) {
				inputOn();
				return;
			}
			inputOn();
			$("#btnSave").prop("disabled", true);	
			var registerData = $("#registerFrom").serialize();
			postJSON(
					"${rootPath}/health/ExaminationAction!identityRegister.action",
					registerData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (!powersi.isnull(json.data)) {
							popupInfo(json.data);
						}
						if (!powersi.isnull(json.message)) {
							popupInfo(json.message);
						}
					});
			
		}
		
		/*不可录入状态*/
		function inputOff() {
			/*规避重复操作处理_不可用*/
			$("#querystring").prop("disabled", true);
		}
		/*可录入状态*/
		function inputOn() {
			$("#querystring").prop("disabled", false);
		}
		function doClear(){
			 $("#querystring").val("");
			 $("#aac001").val("");
			 $("#aac003").val("");
			 $("#aac004").val("");
			 $("#aac004_name").val("");
			 $("#aac002").val("");
			 $("#aac006").val("");
			 $("#aab069").val("");
			 $("#bka035").val("");
			 $("#bka035_name").val("");
			 $("#bkh007").val("");
			 $("#bkh007_name").val("");
			 $("#baa027").val("");
			 $("#bac001").val("");
			 $("#bac001_name").val("");
			 $("#bke069").val("");
			 $("#bke069_name").val("");
			 $("#bkh016").val("");
			 $("#bkh006").val("");
			 $("#bkh005").val("");
			 $("#bkh017").val("");
			 $("#bkh015").val("");
			 $("#bkh015_name").val("");
			 $("#bkh003").val("");
			 $("#bkh004").val("");
			 $("#aae006").val("");
			 $("#bkh002").val("");
			 $("#bkh001").val("");
			 $("#bkh002_name").val("");
	
		}

		
		
		

</script>
</powersi:html>