<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%
String path = request.getContextPath();
%>
<powersi:html> 
<head>
<powersi:head title="生育登记查询" />

<script type="text/javascript">

var saveRegisterFlag = false;
var objCard = null;
$(document).ready(function() {
	$("#querystring").focus();
});

function readIcCard() {
	findAac002($("#argName").val(), $("#querystring").val());
}

function keydown(param) {
	if (event.keyCode == "13") {
		var filed_name = param.id;
		if ("akc190" == filed_name) {
			var akc190 = powersi.trim($("#akc190").val());
			if (powersi.isnull(akc190)) {
				return $("#akc190").focus();
			}
			return $("#bkz101").focus();
		}
		if ("bkz101" == filed_name) {
			return chooseDisease();
		}
		if ("bka018" == filed_name) {
			return choosebka018();
		}
	}
}


function findAac002(argName, querystring) {
		var bka006 = powersi.trim($("#bka006").val());
		/* if (powersi.isnull(bka006)) {
			alert("请选择待遇类型再获取人员信息!");
			return;
		} */
		var querystring = powersi.trim($("#querystring").val());
		if (powersi.isnull(querystring)) {
			alert("请输入有效查询条件!");
			return;
		}
		 resetpage();
		 /*$("#bka006").val(bka006);
		$("#bka006").change(); */
		$("#querystring").val(querystring);
		if (argName == "readic") {
			$("#argName").val("bka100");
		} else {
			$("#bke548").val('');
		}
		var indi_id = "0";
		postJSON(
				"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name="
						+ $("#argName").val()
						+ "&diagnoseInfoDTO.arg_value="
						+ $("#querystring").val()
						+ "&diagnoseInfoDTO.bka006="
						+ $("#bka006").val()
						+ "&diagnoseInfoDTO.aae030="
						+ $("#aae030").val()
						+ "&diagnoseInfoDTO.aka130="
						+ $("#aka130").val()
						+ "&diagnoseInfoDTO.bke548="
						+ $("#bke548").val(),
				function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.message == 'multi-row') {
						choosepersonlist(querystring);
						return;
					}
					if (json.errortype == '0') {
						$.each(json.data.personinfo, function(key,
								value) {
							if (!powersi.isnull(value)) {
								$("#" + key).val(value);
							}
						});
					}
					if (!powersi.isnull(json.message)) {
						popupInfo(json.message);
					}
				});
	}

function choosepersonlist(querystring) {
	popupDialog(
			{
				url : "${rootPath}/diagnose/GetPersonInfoAction!chooseperson.action?diagnoseInfoDTO.arg_value="
						+ querystring,
				onClosed : function() {
					var ret = this.returnValue;
					if (ret) {
						indi_id = ret.aac001;
						postJSON(
								"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name=aac001&diagnoseInfoDTO.arg_value="
										+ indi_id
										+ "&diagnoseInfoDTO.bka006="
										+ $("#bka006").val()
										+ "&diagnoseInfoDTO.aae030="
										+ $("#aae030").val()
										+ "&diagnoseInfoDTO.aka130="
										+ $("#aka130").val(),
								function(json) {
									if (!checkJSONResult(json)) {
										return;
									}
									if (json.errortype == '0') {
										$.each(json.data.personinfo, function(key,
												value) {
											if (!powersi.isnull(value)) 
												$("#" + key).val(value);
										});
									}
									if (!powersi.isnull(json.message)) 
										popupInfo(json.message);
								});
					}
				}
			}, 500, 600);
}

/*重置界面*/
function resetpage() {
	//init();
	$("#querystring").focus();
}
/*查询参保信息*/
function findAac001() {
	if (window.event.keyCode == 13) {
		var querystring = powersi.trim($("#querystring").val());
		var argName = powersi.trim($("#argName").val());
		if (powersi.isnull(querystring)) {
			popupAlert("请输入有效查询条件!");
			return;
		}
		$("#querystring").val(querystring);
		$("#argName").val(argName);
		$("#argName").change();
		$("#querystring").prop("disabled", true);
		//console.log(querystring);
		//console.log(argName);
		findAac002(argName, querystring);
		$("#querystring").prop("disabled", false);
	}
}

//快捷键调用方法
//Alt+F查询
function altf(){
	queryData();
}
//Alt+C 清屏
function altc(){
	clearData();
}
$(function(){
	$("#btQuery").click(function(){
		queryData();
	});	
});
/**
 * 查询
 */
function queryData(){
	grid.setParm("trDTO.flag",$('#flag').val());
	grid.setParm("trDTO.aae016",$('#aae016').val());	
	grid.setParm("trDTO.beginDate",$('#beginDate').val());
	grid.setParm("trDTO.endDate",$('#endDate').val());
	if($('#aac001').val()!=''){
		grid.setParm("trDTO.aac001",$('#aac001').val());
		grid.setParm("trDTO.aac002",$('#aac002').val());
		grid.setParm("trDTO.aac003",$('#aac003').val());
	}else{
		grid.setParm("trDTO.aac001","");
		grid.setParm("trDTO.aac002","");
		grid.setParm("trDTO.aac003","");
	}
	grid.reload(true);
}
function changeInf(aac001,aaz238,aab001,aae030) {
	var params = "?";
	params = params + "trDTO.aaz238="+aaz238;
	params = params + "&trDTO.aac001="+aac001 ;
	params = params + "&trDTO.aab001="+aab001 ;
	params = params + "&trDTO.aae030="+aae030 ;
	params = params + "&trDTO.flag=1";
	openDialogWithParam("<%=path%>/maternity/MaternityRegAction!showRegInf.action"+params,'');										
}
function editInf(aac001,aaz238,aab001) {
	var params = "?";
	params = params + "trDTO.aaz238="+aaz238;
	params = params + "&trDTO.aac001="+aac001 ;
	params = params + "&trDTO.aab001="+aab001 ;
	params = params + "&trDTO.flag=2";
	openDialogWithParam("<%=path%>/maternity/MaternityRegAction!showRegInf.action",params,200,300);										
}
function deleteInf(aaz238) {
	if (confirm("确定要回退记录吗?")) { 
		var params = "?";
		params = params + "trDTO.aaz238="+aaz238;
		var list = [];
		postJSON("<%=path%>/maternity/MaternityRegAction!deleteInf.action"+params,
			{"list":list},
			function(ret) {
			/*if (ret.errortype == 2) {
				popupAlert(ret.message, "提示", "error");
			} else {
				queryData();
				popupAlert(ret.message, "提示", "success");
			}*/
			alert(ret.message);
		});
	}
}
function queryAmc292(aaz238){
	var params = "?";
	params = params + "trDTO.aaz238="+aaz238;
	openDialogWithParam("<%=path%>/maternity/MaternityRegAction!getAmc292Img.action"+params,'',800,900);	
}
function renderOperate(row, index, value){
	var a = [];
	/* a.push('<input type="button" value="详细" class="linkButton"');
	a.push(' onclick="changeInf( ');
	a.push("'");
	a.push(row['aac001']);
	a.push("','");
	a.push(row['aaz238']);
	a.push("','");
	a.push(row['aab001']);
	a.push("','");
	a.push(row['aae030']);
	a.push("'");
	a.push(')"');
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	a.push('<input type="button" value="修改" class="linkButton"');
	a.push(' onclick="editInf( ');
	a.push("'");
	a.push(row['aac001']);
	a.push("','");
	a.push(row['aaz238']);
	a.push("','");
	a.push(row['aab001']);
	a.push("'");
	a.push(')"');
	if(row['aae016s']=='1' ||row['aae016s']=='2'|| row['aae016s']=='4'){
		a.push(" disabled='true' ");
	}
	a.push(" />");
	
	a.push("&nbsp;&nbsp;");
	a.push('<input type="button" value="回退" class="linkButton"');
	a.push(' onclick="deleteInf( ');
	a.push("'");
	a.push(row['aaz238']);
	a.push("'");
	a.push(')"');
	if(row['flag']!='0'||row['aae016s']=='3'||row['aae016s']=='4'){
		a.push(" disabled='true' ");
	}
	a.push(" />"); */
	a.push('<input type="button" value="生育证" class="linkButton"');
	a.push(' onclick="queryAmc292( ');
	a.push("'");
	a.push(row['aaz238']);
	a.push("'");
	a.push(')"');
	 if(row['amc292flag']!='1'){
		a.push(" disabled='true' ");
	} 
	a.push(" />");
	return a.join(''); 
}	
function clearData(){
	$("#queryString").val("");
	$('#aac001').val("");
    $('#aac002').val("");
    $('#aac003').val("");
    $('#beginDate').val("");
    $('#endDate').val("");
    $('#aae016').val("");
    $("#bka008").val("");
    
    /* grid.setParm("trDTO.flag",$('#flag').val());
	grid.setParm("trDTO.aae016",$('#aae016').val());	
	grid.setParm("trDTO.beginDate",$('#beginDate').val());
	grid.setParm("trDTO.endDate",$('#endDate').val());
	if($('#aac001').val()!=-1){
		grid.setParm("trDTO.aac001",$('#aac001').val());
		grid.setParm("trDTO.aac002",$('#aac002').val());
		grid.setParm("trDTO.aac003",$('#aac003').val());
	} */
}
$(document).ready(function(){
	$("#queryStringHosp").val("${trDTO.akb021}");
	$("#aaz107").val("");
});
</script>
</head>
<body>
	<powersi:form action="MaternityRegAction!queryRegisterInf.action" namespace="/maternity" >
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button  id="btQuery" value="查 询" key="button_query" />
					<powersi:button  id="btClear" value="清 空" key="button_clear" onclick="clearData();"/>
				</powersi:buttons>
			</powersi:panelbox-toolbar>
				<powersi:editorlayout cols="10%,10%,15%,8%,12%,8%,12%,10%,15%">
				<powersi:editorlayout-row>
					<powersi:codeselect id="argName" name="trDTO.argName"
						label="查询条件" cssClass="select2" list="#{'aac002':'社会保障号' }" />
					<td>
						<powersi:textfield id="querystring" name="querystring"
							title="请输入信息回车" placeholder="请输入信息回车!" readonly="false"
							onkeyup="findAac001()" buttonText="读卡" buttonId="readic_button"
							buttonDisabled="false"  />
					</td>
					<powersi:textfield id="aac002" name="trDTO.aac002"
						label="社会保障号" readonly="true" />
					<powersi:textfield id="bka008" name="trDTO.aab069"
						label="单位名称" readonly="true" />
					<powersi:textfield id="aac003" name="trDTO.aac003"
						label="姓名" readonly="true" />
					<powersi:hidden id="querystring" name="trDTO.querystring" />
					<powersi:hidden id="aac001" name="trDTO.aac001" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="beginDate" name="trDTO.beginDate" label="登记日期开始于"  mask="date" />
					<powersi:textfield id="endDate" name="trDTO.endDate" label="登记日期结束于"  mask="date" />
					<%-- 用来区分生育登记查询和生育登记审核的变量，登记查询=1，审核查询=0 --%>
					<powersi:hidden id="flag" name="trDTO.flag" value="1"></powersi:hidden>
					<powersi:codeselect id="aae016"  label="审核状态" name="trDTO.aae016"  
					list="#{'':'请选择','0':'未审核','1':'初审通过','2':'复审通过','3':'初审不通过','4':'复审不通过'}"/>
				</tr>
			</powersi:editorlayout>
			<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130" value="52" />
			<powersi:hidden id="bka006" name="diagnoseInfoDTO.bka006" value="521" />
			<powersi:hidden id="aaz509" name="inHospitalDTO.aaz509" value="1" />
		</powersi:panelbox>
		
		<powersi:groupbox title="查询结果">
			<powersi:datagrid id="grid"  showExportExcel="true" delayLoad="true" height="100%"
			 url ="${rootPath }/maternity/MaternityRegAction!queryRegisterInf.action" >
				<powersi:datagrid-column name="aaz238" hide="true" />	
				<powersi:datagrid-column name="amc060" display="享受待遇类型" width="200" />
				<powersi:datagrid-column name="aab001" display="单位代码" />
				<powersi:datagrid-column name="aab069" display="单位名称" />
				<powersi:datagrid-column name="aac003" display="姓名" />	
				<powersi:datagrid-column name="aac002" display="保险号" width="140" />
				<powersi:datagrid-column name="aae016" display="审核状态" />
				<powersi:datagrid-column name="aae013" display="审核意见" width="300" />
				<powersi:datagrid-column name="amc026" key="amc026" code="amc026"/>
				<powersi:datagrid-column name="akb069" display="定点医疗机构" />
				<powersi:datagrid-column name="amc020" key="amc020" display="生育手术日期" />
				<powersi:datagrid-column name="amc019" display="末次月经时间"/>
				<powersi:datagrid-column name="amc021" key="amc021"/>
				<powersi:datagrid-column name="amc031" key="amc031" code="amc031"/>
				<powersi:datagrid-column name="amc028" key="amc028"/>
				<powersi:datagrid-column name="aae030" key="aae030"/>
				<powersi:datagrid-column name="aae031" key="aae031"/>
				<powersi:datagrid-column name="aae011" key="aae011" code="badd1"/>
				<powersi:datagrid-column name="aae127" key="aae127"/>
				<powersi:datagrid-column name="amc292flag" hide="true" />	
				
			</powersi:datagrid>	 
		</powersi:groupbox>
 	</powersi:form>
	<powersi:errors />
</body>
</powersi:html>