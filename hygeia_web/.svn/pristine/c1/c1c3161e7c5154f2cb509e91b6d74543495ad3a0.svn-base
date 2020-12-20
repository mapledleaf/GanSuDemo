<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.Timestamp,java.text.SimpleDateFormat,java.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/maternity"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper" %>
<%
	String aaa027=BizHelper.getAaa027().substring(0,4);
    String akb021s=BizHelper.getAkb021();
	String path = request.getContextPath();
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	Calendar sy = Calendar.getInstance();
	sy.add(Calendar.YEAR,1);//加一年
	Timestamp ts = com.powersi.hygeia.framework.util.DBHelper.getDBTimestamp();
	String now = sf.format(ts);
	String secondYear = sf.format(sy.getTime());
	String ignore = "";
	if(request.getAttribute("ignore")!=null&&request.getAttribute("ignore")!=""){
		ignore=request.getAttribute("ignore").toString();
	}
	String right ="0";
	String indi_freeze_status ="0";//基金冻结标识
	if("1".equals(ignore)){
		right="1";
	}else{
		right="0";
	}
	if(request.getAttribute("right")!=null&&request.getAttribute("right")!=""){
		right=request.getAttribute("right").toString();
	}
	if(request.getAttribute("indi_freeze_status")!=null&&request.getAttribute("indi_freeze_status")!=""){
		indi_freeze_status=request.getAttribute("indi_freeze_status").toString();
	}
	String aac001 = "";
	if(request.getAttribute("aac001")!=null&&request.getAttribute("aac001")!=""){
		aac001=request.getAttribute("aac001").toString();
	}
	String queryString = "";
	if(request.getAttribute("queryString")!=null&&request.getAttribute("queryString")!=""){
		queryString=request.getAttribute("queryString").toString();
	}
%>
<powersi:html>
<head>
<powersi:head title="生育待遇资格认定" />
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
<script type="text/javascript">
var aaa027 = <%=aaa027%>;

var saveRegisterFlag = false;
var objCard = null;
$(document).ready(function() {
	$("#querystring").focus();
});

function readIcCard() {
	findAac002($("#argName").val(), $("#querystring").val());
}

/*加载控件*/
function loadCardControl() {
	try {
		if (objCard == null || objCard.object == null) {
			objCard = document.getElementById("cardControl");
			if (objCard.object == null) {
				popupAlert("请先安装社保卡控件!");
			}
		}
	} catch (e) {
		popupAlert("请先安装社保卡控件!");
	}
}

/*读卡获取后台信息*/
function readic() {
	var aae030 = powersi.trim($("#aae030").val());
	registerReset();
	if (!powersi.isnull(aae030)) {
		$("#aae030").val(aae030);
	}
	iReadCardBase();
	if (powersi.isnull($("#bke548").val())) {
		return;
	}
	findAac002("");
}

/*读卡*/
function iReadCardBase() {
	loadCardControl();
	if (objCard != null && objCard.object != null) {
		var bke548 = null;// 读卡返回
		bke548 = objCard.ReadCardBase();
		$("#bke548").val(bke548);
	}
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
						afterFindAac001();
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
										afterFindAac001();
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
/*住院登记界面要支持：身份证号码、电脑号、社保卡号*/
 /*
function findAac002(argName, querystring) {
	if (powersi.isnull(querystring)) {
		$("#querystring").val("");
	} else {
		$("#querystring").val(querystring);
	}
	var registerData ={"inHospitalDTO.argName":argName,
			"inHospitalDTO.querystring":$("#querystring").val(),
			"inHospitalDTO.caa027":"4303-zg"
			}
	postJSON(
			"${rootPath}/inhospital/InhospitalManagerAction!findAac001.action",
			registerData, function(json) {
				console.log(json);
				if (!checkJSONResultNew(json)) {
					return;
				}
				if (json.data != "no" && json.data.length > 1) {
					if (powersi.isnull(querystring)) {
						chooseAac002(argName, json.message);
						$("#querystring").val(json.message);
						$("#querystring").val(json.message);
					} else {
						chooseAac002(argName, querystring);
					}
					return;
				}
				if (json.data != "no") {
					//console.log(json);
					$.each(json.data, function(key, value) {
						if (!powersi.isnull(value)) {
							$("#" + key).val(value);
						}
					});
				}
				if (!powersi.isnull(json.message)) {
					popupInfo(json.message);
				}
				afterFindAac001();
			});
}*/
/*查询并选择参保信息*/
function chooseAac002(argName, querystring) {
	var aka130 = powersi.trim($("#aka130").val());
	if (powersi.isnull(aka130)) {
		return;
	}
	var caa027 = powersi.trim($("#caa027").val());
	if (powersi.isnull(caa027)) {
		return;
	}
	popupDialog(
			{
				url : "${rootPath}/common/CommonManagerAction!choosePersonAac001.action"
					+ "?inHospitalDTO.argName=" + argName 
					+ "&inHospitalDTO.querystring=" + querystring 
					+ "&inHospitalDTO.aka130=" + aka130
					+ "&inHospitalDTO.caa027=" + caa027,
				onClosed : function() {
					var retValue = this.returnValue;
					if (retValue) {
						accessAac001(retValue.aac001);
					}
				}
			}, 500, 600);
}

/*根据电脑号查询参保信息*/
function accessAac001(aac001) {
	if (powersi.isnull(powersi.trim(aac001))) {
		return;
	}
	$("#bke548").val("");
	$("#querystring").val(powersi.trim(aac001));
	$("#bka977_name").val("1");
	$("#aac001").val(powersi.trim(aac001));
	$("#argName").val("aac001");
	$("#argName").change();
	$("#querystring").val(powersi.trim(aac001));
	var registerData = $("#register").serialize();
	postJSON(
			"${rootPath}/inhospital/InhospitalManagerAction!findAac001.action",
			registerData, function(json) {
				$("#bka977_name").val("");
				if (!checkJSONResultNew(json)) {
					return;
				}
				if (json.data != "no") {
					$.each(json.data, function(key, value) {
						if (!powersi.isnull(value)) {
							$("#" + key).val(value);
						}
					});
				}
				if (!powersi.isnull(json.message)) {
					popupInfo(json.message);
				}
				afterFindAac001();
			});
}


//根据生育的类型，改变胎次的显示与否
function checkamc026(){
	var amc026 = $("#amc026").val();
	if(amc026 == null||amc026==""){
  		$("#a").hide();
  	}else{
  		if(amc026!="1"&&amc026!="4"){
		    $("#a").hide();
		    $("#amc031").val("");
			$("#amc028").val("");
    	}else{
      	 	$("#a").show();  
    	}
  	}
}
function checkamc050(){
	var amc050 = $("#amc050").val();
	if(amc050 == null||amc050==""){
		$("#amc026").val("");
		$("#amc029").val("");
		$("#amc031").val("");
		$("#amc028").val("");
		$("#amc026").prop('disabled',true);
		$("#amc029").prop('disabled',true);
		
		$("#a").hide();
		$("#b").hide();
		$("#aac016").val("");
	}else if(amc050=='1'){
		// 生育类别可选择,计生手术类别不可用
		$("#amc026").prop('disabled',false);
		$("#amc029").prop('disabled',true);
		$("#amc029").val("");
		$("#aac016").val("2");
		$("#a").show();
 	    $("#b").show();
	}else if(amc050=='2'){
		// 计生手术类别可选择,生育类别不可用 值清空
		$("#amc029").prop('disabled',false);
		$("#amc026").prop('disabled',true);
		$("#a").hide();
		$("#amc026").val("");
		$("#amc031").val("");
		$("#amc028").val("");
		$("#aac016").val("2");
		$("#b").hide();
		$("#bmc018").val("");
		$("#bmc019").val("");
		$("#bmc020").val("");
	}else if(amc050=='3'){
		//生育手术合并，生育类别 手术类型都可选
		$("#amc029").prop('disabled',false);
		$("#amc026").prop('disabled',false);
		$("#a").show();
		$("#amc026").val("");
		$("#amc031").val("");
		$("#amc028").val("");
		$("#aac016").val("2");
	    $("#b").show();
	    $("#bmc018").val("");
		$("#bmc019").val("");
		$("#bmc020").val("");
	}
}
function isNotBlank(str) {
	if (typeof (str) == "undefined" || str == null || str == "") {
		return false;
	} else {
		return true;
	}
}	
function ignorechange(){
	if($('input:checkbox:enabled:checked').val()==undefined){
  		$("#btSubmit").prop('disabled',true);
	}

	if($('input:checkbox:enabled:checked').val()=='on'){
		$("#btSubmit").prop('disabled',false);
		$("#baa020").val(1);
	}
}
	
function runGetUser(e){
	var e = window.event||e;
	if(e.keyCode == 13){
		getUser();
	}
}	
function choosePersonId(){
	var ret = openDialog("<%=path%>/maternity/TreatmentApprobateAction!openChoosePersonInf.action",500,800);
	if(ret!=null){
		$("#aac058").val(ret[0]);
		$("#aac002").val(ret[1]);
		getUser();
	}
}	
function afterChoosePerson(data){
	if(data.aaa027!='430300'&&data.aaa027!=data.baa027){
		alert("不能跨区县办理业务！");
		$("#btSubmit").prop("disabled",true);
		$("#queryString").val("");
		$('#aac001').val("");
	    $('#aac002').val("");
	    $('#aac003').val("");
		return false;
	}
	$("#ycaac001").val($('#aac001').val()); 
	getUser();
}
function getUser() {  
	if(!isNotBlank($('#aac002').val())){
		alert("请输入证件号码！");
		return false;
	}
	if(isNotBlank($('#aac002').val())){
		$.get("<%=path%>/maternity/MaternityRegAction!registerInfAjaxQuery.action",
			{'trDTO.aac001' : $('#aac001').val()},
			function(jsonData) {
				if(jsonData != null){
					alert(jsonData.message) ;
					$("#btSubmit").prop("disabled",true);
				}if(jsonData == null){	
					var ylks = $("#ylks").val();
					if(ylks == null||ylks==""){
						ylks="<%=now%>";
					}
					
					var params = "?";
					
					params = params + "trDTO.aac001=" + $('#aac001').val();
					params = params + "&trDTO.aab001=" + $('#aab001').val();
					params = params + "&trDTO.right=" + $('#right').val();
					params = params + "&trDTO.aaa027=" + $('#baa027').val();
					params = params + "&trDTO.ylks=" + ylks;
					//查询生育个人缴费数据，需要提供接口
					 window.location.href="<%=path%>/maternity/MaternityRegAction!insuranceInfQuery.action"+params ; 
				}
			}
		,"json");
	}	
}
function afterFindAac001(){
	getUser();
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
function save(){
	 changeAmc021();
	if(!isNotBlank($("#aac003").val())){
	   alert("请先选定参保人员");
       return false; 
	}
	/* if(!isNotBlank($("#akb020").val())){
		alert("请选择定点医疗医院!");
		return false ;
	} */
    if(!isNotBlank($("#amc019").val())){
		   alert("请输入末次月经时间！");
	       return false; 
	} 
  /*  var amc020 = $("#amc020").val();
	if(!isNotBlank($("#amc020").val())){
		alert("请选择生育手术或生育日期!");
		return false ;
	}*/
	if(!isNotBlank($("#ylks").val())){
		alert("请输入医疗开始时间!");
		return false ;
	}
	/* if(!isNotBlank($("#yljs").val())){
		alert("请输入医疗结束时间!");
		return false ;
	} */
/* 	if(!isNotBlank($("#amc021").val())){
		alert("请输入准生证号!");
		return false ;
	} */
	/* if($("#ylks").val()>$("#yljs").val()){
		alert("医疗开始时间不能早于医疗结束时间!");
		return false ;
	} */
	var amc026 = $("#amc026").val();
	/* if(!isNotBlank($("#amc026").val())){
		alert("请选择生育类别!");
		return false ;
	} */
	if(amc026=="1"||amc026=="4"){
		if(!isNotBlank($("#amc031").val())){
			alert("请填写胎次!");
			return false ;
		}
		/* if(!isNotBlank($("#amc021").val())){
			alert("请输入准生证号!");
			return false ;
		} */
  	}
	if(!isNotBlank($("#amc010").val())){
		alert("请输入配偶姓名!");
		return false ;
	}
	if(!isNotBlank($("#bmc011").val())){
		alert("请输入配偶身份证号码!");
		return false ;
	}
	var aac001 = $('#ycaac001').val();
	var amc020 =  $('#amc020').val();
	var data = {"trDTO.aac001":$('#ycaac001').val(),
			"trDTO.aaz157":$('#aaz157').val(),
			"trDTO.aaz159":$('#aaz159').val(),
			"trDTO.aaz107":$('#aaz107').val(),
			"trDTO.amc021":$('#amc021').val(),
			"trDTO.ylks":$('#ylks').val(),
			"trDTO.yljs":$('#yljs').val(),
			"trDTO.bmc013":$('#bmc013').val(),
			"trDTO.right":$('#right').val(),
			"trDTO.akb020":$('#akb020').val(),
			"trDTO.aab001":$('#aab001').val(),
			"trDTO.aac066":$('#aac066').val(),
			"trDTO.amc026":$('#amc026').val(),
			"trDTO.amc031":$('#amc031').val(),
			"trDTO.baa020":$('#baa020').val(),
			"trDTO.amc010":$('#amc010').val(),
			"trDTO.bmc011":$('#bmc011').val(),
			"trDTO.bmc012":$('#bmc012').val(),
			"trDTO.amc020":$('#amc020').val(),
			"trDTO.amc019":$('#amc019').val(),
			"trDTO.amc022":$('#amc022').val(),
			"trDTO.amc028":$('#amc028').val(),
			"trDTO.bmc018":$('#bmc018').val(),
			"trDTO.bmc019":$('#bmc019').val(),
			"trDTO.bmc020":$('#bmc020').val(),
			"trDTO.aaz238":$('#aaz238').val(),
			"trDTO.aaa027":$('#baa027').val(),
			"trDTO.aae005":$('#aae005').val(),
			"trDTO.bmc024":$('#bmc024').val(),
			"trDTO.bmc025":$('#bmc025').val(),
			"trDTO.bmc026":$('#bmc026').val(),
			"trDTO.bmc027":$('#bmc027').val(),
			"trDTO.bmc028":$('#bmc028').val(),
			"trDTO.aae009":$('#aae009').val(),
			"trDTO.aae010":$('#aae010').val(),
			"trDTO.aaz065":$('#aaz065').val(),
			"trDTO.aae008":$('#aae008').val()
			 	};
		var url="";
		if(isNotBlank($("#aaz238").val())){
			 url="${rootPath}/maternity/MaternityRegAction!regInfUpdate.action";
		}else{
			 url="${rootPath}/maternity/MaternityRegAction!registerInfSave.action";
		}
		//生育日期为空则不校检是否满足连续缴费10个月
		if(!isNotBlank($("#amc020").val())){
			postJSON(url,data,showData);  
		} else{
			postJSON("${rootPath}/maternity/MaternityRegAction!getLxjf.action",{"trDTO.aac001":aac001,"trDTO.amc020":amc020},function(json){ 
				  // 是否满足连续缴费10个月，1不满足 0 ，满足
				  //console.log(json);
					if(!isNotBlank($("#aaz238").val())){
						if(json.message=="1"){
							  var result=confirm("生育日期往前10个月无连续缴费，是否继续!");
								if(result==false){
									$("#btSubmit").prop("disabled",true);
									return false;
							      }
						    } 
					}
					postJSON(url,data,showData);
					});
		}
	
}
function clear(){	
	$('#queryString').val("");
	$('#aac001').val("");
	$('#aac002').val("");
	$('#aac003').val("");
	$('#aac004').val("");
	$('#aae030').val("");
	$('#aab069').val("");
	$('#aab019').val("");
	
	$('#ycaac002').val("");
	$('#ycaac003').val("");
	$('#ycaac001').val("");
	$('#ljys').val("");
	$('#aab999').val("");
	$('#aab069').val("");
	$('#aaz157').val("");
	$('#aaz159').val("");
	$('#aae003').val("");	
	$('#amc021').val("");	
	$('#bka030').val("");
	$('#aaz107').val("");
	$('#ylks').val("");
	$('#yljs').val("");
	$('#bmc013').val("");
	$('#aac114').val("");
	$('#bmc014').val("");
	$('#babd05').val("");
	$('#amc010').val("");
	$('#bmc011').val("");
	$('#bmc012').val("");
	//hTreatmentGrid.reset();
}

$(function(){
	//查询参保信息后，查询控件赋值
	var aac001 = "<%=aac001%>";
	$("#aac001").val(aac001); 
	var queryString = "<%=queryString%>";
	$("#queryString").val(queryString);
	
	$("#btSubmit").click(function(){	
		save();
	});
	$("#btReset").click(function(){	
		clear();
	});
	//选择银行
	$('#bke306Btn').click(function() {
		popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/maternity/treatmentRegister/maternityBankSelect.jsp",
			onClosed: function(){
				var ret = this.returnValue;
				//console.log(ret);
				if(ret){
					$("#aaz065").val(ret.aaz065);
					$("#bke306").val(ret.aab069);
					$("#aae008").val(ret.aae008);
				}		
			}
		}, 'param1',600,700);
	});
});	



function callBack(data){
	$("#bka030").val(powersi.tostring(data.data[0].aab069));
	$("#aaz107").val(powersi.tostring(data.data[0].aaz107)) ;
	$("#akb020").val(powersi.tostring(data.data[0].akb020));
}

function showData(json){
	if(isNotBlank(json)){
		if(json.errortype == 1){
			alert(json.message) ;
		}else{
			alert(json.data);
			$("#btSubmit").prop("disabled",true);
		}
	}
}
	function codeselect_disabled(){
		$("#akb021").val("<%=akb021s%>");
		/* if(isNotBlank($("#lxys").val())){
			if( $("#lxys").val()==1){
				var result=confirm("生育保险连续缴费月数不满10个月，是否继续!");
				if(result==false){
					$("#btSubmit").prop("disabled",true);
					return false;
			
			      }
		      }
		} */
	if(isNotBlank($("#qfys").val())){
		if( $("#qfys").val()>0){
			var result=confirm("欠费 "+$("#qfys").val()+" 个月，是否继续!");
			if(result==false){
				$("#btSubmit").prop("disabled",true);
				return false;
			}
		}
	}
	var indi_freeze_status = "<%=indi_freeze_status%>";
	if(indi_freeze_status==1){
		$("#btSubmit").prop("disabled",true);
	}
	var ylks ="<%=now%>";
	var yljs = "<%=secondYear%>";
	if(!isNotBlank($("#aaz238").val())){
		$('#ylks').val(ylks);
		$('#yljs').val(yljs); 
	}
    var ignore = "<%=ignore%>";
    var right = "<%=right%>";
    $('#baa020').val(ignore);  
    $("#right").val(right);
    if(isNotBlank($("#ycaac002").val())){
		var aac002=$("#ycaac002").val();
		$("#aac002").val(aac002);
	}
	if(isNotBlank($("#ycaac003").val())){
		var aac003=$("#ycaac003").val();
		$("#aac003").val(aac003);
	}
}
	function changeAmc021(){
		var amc019=$("#amc019").val();
		if(isNotBlank($("#amc019").val())){
			var dateStr=amc019.substring(0,4)+"/"+amc019.substring(4,6)+"/"+amc019.substring(6,8);
			$("#bmc013").val(dateAddDays(dateStr,280));
		}
	}
	//日期加天数的方法
	//dataStr日期字符串
	//dayCount 要增加的天数
	//return 增加n天后的日期字符串
	function dateAddDays(dataStr,dayCount) {
	    var strdate=dataStr; //日期字符串
	    var isdate = new Date(strdate.replace(/-/g,"/"));  //把日期字符串转换成日期格式
	    isdate = new Date((isdate/1000+(86400*dayCount))*1000);  //日期加1天
	    var pdate = isdate.getFullYear()+""+Appendzero(isdate.getMonth()+1)+""+Appendzero(isdate.getDate());   //把日期格式转换成字符串
	    return pdate;
	}
	 function Appendzero(obj)
	    {
	        if(obj<10) return "0" +""+ obj;
	        else return obj;
	    }
	
</script>
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
</head>

<body onLoad="codeselect_disabled();">
	<powersi:form id="mainFrom" action="MaternityRegAction!registerInfSave.action"
		namespace="/maternity">
		<powersi:groupbox title="生育保险参保人员信息">
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
				<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130" value="52" />
				<powersi:hidden id="bka006" name="diagnoseInfoDTO.bka006" value="521" />
				<powersi:hidden id="aaz509" name="inHospitalDTO.aaz509" value="1" />
			</powersi:editorlayout>
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:codeselect  codeType="aac004" id="aac004" name="trDTO.aac004" key="aac004"  disabled="true"  />
			      	<powersi:textfield id="aae030" label="参保日期" name="trDTO.aae030"   readonly="true" />
					<powersi:textfield id="ljys" label="累计缴费月数"  name="trDTO.ljys"  readonly="true"  />
					<powersi:textfield id="aab999" key="aab999" name="trDTO.aab999" readonly="true" />
				</tr>
				<%--<tr>	
					<powersi:textfield id="aab069" label="单位名称"  name="trDTO.aab069" readonly="true" />
					<powersi:codeselect codeType="aab019" id="aab019" name="trDTO.aab019" key="aab019"  disabled="true"  />
					<powersi:hidden id="aab001" name="trDTO.aab001" />
				</tr> --%> 
				<%-- <tr>
                     <powersi:codeselect id="aac114" name="trDTO.aac114" codeType="pro_is_yes" key="aac114" label="是否异地"  readonly="true"  />
                     <td></td><td></td><td></td><td></td>
				</tr> --%> 
				<tr>
				<%if("1".equals(right)){ %>
				  <td >
				<input type="checkbox" id="ignoreCheck" onclick="ignorechange()"/>忽略缴费月数
				 </td >
				<%} %>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:panelbox title="生育待遇资格登记信息">
		   <powersi:panelbox-toolbar>
				<powersi:buttons cols="8">
					<powersi:button  id="btSubmit" key="button_submit" value="登 记" />
					<powersi:button  id="btReset" key="button_reset" value="重 置" />						
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<tags:regInfo/>

			<%--测试写死 --%>
			<powersi:hidden id="ycaac001" name="trDTO.aac001" value="%{trDTO.aac001}"></powersi:hidden>	                    
			<powersi:hidden id="aaz157" name="trDTO.aaz157" value="%{trDTO.aaz157}"></powersi:hidden>
			<powersi:hidden id="aaz159" name="trDTO.aaz159" value="%{trDTO.aaz159}"></powersi:hidden>
			<powersi:hidden id="aaz107" name="trDTO.aaz107" value=""></powersi:hidden>
			<powersi:hidden id="akb020" name="trDTO.akb020" value="%{trDTO.akb020}"></powersi:hidden>
			<powersi:hidden id="ycaac002" name="trDTO.aac002" value="%{trDTO.aac002}"></powersi:hidden>			                    
			<powersi:hidden id="ycaac003" name="trDTO.aac003" value="%{trDTO.aac003}"></powersi:hidden>			                    
			<powersi:hidden id="right" name="right" value="%{trDTO.right}"></powersi:hidden>	
			<powersi:hidden id="aac066" name="trDTO.aac066" value="%{trDTO.aac066}"></powersi:hidden>					                    
			<powersi:hidden id="aab001" name="trDTO.aab001" value="%{trDTO.aab001}"></powersi:hidden>			                    
			<powersi:hidden id="lxys" name="trDTO.lxys" value="%{trDTO.lxys}"></powersi:hidden>
			<powersi:hidden id="baa020" name="trDTO.baa020" value="%{trDTO.baa020}"></powersi:hidden>
			<powersi:hidden id="bmc014" name="trDTO.bmc014" value="%{trDTO.bmc014}"></powersi:hidden>
			<powersi:hidden id="qfys" name="trDTO.qfys" value="%{trDTO.qfys}"></powersi:hidden>
			<powersi:hidden id="cbzt" name="trDTO.cbzt" value="%{trDTO.cbzt}"></powersi:hidden>
			<powersi:hidden id="aaz238" name="trDTO.aaz238"></powersi:hidden>
			<powersi:hidden id="baa027" name="trDTO.aaa027"></powersi:hidden>
		</powersi:panelbox>
		<powersi:panelbox title="生育津贴核定信息">
			
			<powersi:editorlayout cols="8">
				<tr>
			     	<%-- <powersi:textfield name="trDTO.amc022" id="amc022"
						 key="amc022"></powersi:textfield> --%>
					<%-- <powersi:codeselect name="taDTO.amc050" id="amc050"
						  codeType="amc050" key="amc050" onchange="checkamc050()" required="true" ></powersi:codeselect> --%>
	 				<%-- <powersi:codeselect name="trDTO.amc026" id="amc026"  codeFilter="data_value not in('6','7','8')"
	 					key="amc026" onchange="checkamc026()" required="true"
	 					list="#{'1':'正常产','4':'难产','2':'未满4个月终止妊娠','3':'4个月以上终止妊娠' }"></powersi:codeselect> --%>
					 <powersi:codeselect name="trDTO.amc031" id="amc031"
						 codeType="amc031" key="amc031"></powersi:codeselect>
					  <powersi:codeselect name="trDTO.amc028" id="amc028"
						 codeType="amc028" key="amc028"></powersi:codeselect>
						 
				</tr>
 			</powersi:editorlayout>
		</powersi:panelbox>
		<%-- <powersi:panelbox title="银行账户信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="bke306" name="trDTO.bke306" key="银行名称" 
									 readonly="true" buttonId="bke306Btn" buttonText="选择银行" />
					<powersi:textfield id="aae009" name="trDTO.aae009"  key="aae009" lable="户名" maxlength="100"/>
					<powersi:textfield id="aae010" name="trDTO.aae010"  key="aae010" lable="银行帐号" maxlength="40"/>
				</tr>
				<tr>
				<powersi:textfield id="aaz003" name="trDTO.aaz003"  hidden="true"/><!-- 当事人银行账号id -->
				<powersi:textfield id="aaz065" name="trDTO.aaz065"  hidden="true"/><!-- 银行id -->
				<powersi:textfield id="aae008" name="trDTO.aae008"  hidden="true"/><!-- 银行编号 -->
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox> --%>
	</powersi:form>
	<%-- <tags:historyMaternity />--%>
	<powersi:errors />
</body>
</powersi:html>