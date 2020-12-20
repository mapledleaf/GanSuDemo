<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath(); 

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaz267value = request.getAttribute("mediSpecDto.aaz267")==null?"0":request.getAttribute("mediSpecDto.aaz267").toString();
%>

<powersi:html>
<body>
	<powersi:head title="门特特殊业务申请" />
	<powersi:form id="mainForm" namespace="/medicare" >
		<div id="div1" style="display: none">
			<powersi:hidden id="akb020" name="mediSpecDto.akb020" />
			<powersi:hidden id="akb021" name="mediSpecDto.akb021" />
			<powersi:hidden id="aka083" name="mediSpecDto.aka083" value="1sp"/>
			<powersi:hidden id="aae140" name="inHospitalDTO.aae140" />
			<powersi:hidden id="aaa027" name="inHospitalDTO.aaa027" />
			<powersi:hidden id="indi_id" name="indi_id" value="indi_id" />
			<powersi:hidden id="idcard" name="idcard" value="idcard" />
		</div>
		<powersi:groupbox title="人员信息">
			<powersi:panelbox-toolbar>
				<powersi:button id="button_save" key="保存"
					onclick="saveInfo()" />
				<powersi:button cssClass="button" key="重置"
					onclick="clearall()" />
				<powersi:button id="btClose" label="取 消"
						onclick="javascript:closeDialog();" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo()" buttonText="读     卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" required="true" />
					<powersi:textfield label="性别" id="aac004_name" readonly="true" />
					<powersi:hidden id="aac004" name="mediSpecDto.aac004" readonly="true" />
					<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
					<powersi:textfield label="人员类别" id="aac066" name="aac066"
						key="aac066" readonly="true" />
				</powersi:editorlayout-row>
	
				<powersi:editorlayout-row>
					<powersi:hidden id="aac001" name="mediSpecDto.aac001"></powersi:hidden>
					<powersi:textfield label="身份证号" id="aac002"
						name="mediSpecDto.aac002" key="aac002" readonly="true" required="true" />
					<powersi:textfield label="出生日期" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aac006"
						name="mediSpecDto.aac006" key="aac006" readonly="true" />
					<powersi:textfield label="工作日期" format="dateFmt:'yyyy-MM-dd'"
						id="aac007" name="mediSpecDto.aac007" key="aac007" readonly="true" />
					<powersi:textfield label="所属单位" id="aab069"
						name="mediSpecDto.aab069" key="aab069" readonly="true" colspan="3" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>

		<powersi:groupbox title="申请信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="有效期开始"  id="aae030" readonly="true"
						name="mediSpecDto.aae030" key="aae030" required="true" />
					<powersi:textfield label="有效期结束" id="aae031" readonly="true"
						name="mediSpecDto.aae031" key="aae031" required="true" />
					<powersi:select label="疾病名称" id="bke003" name="mediSpecDto.bke003"
						key="bke003" required="true" />
					<td><powersi:button key="button" value="选择" id="disease"
							onclick="chooseDis()" /></td>
					<td colspan=""></td>
					
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="申请人电话" id="bke016"
						name="mediSpecDto.bke016" key="bke016" 
						validate="integer" maxlength="20" />
					<powersi:textfield label="申请医师" id="bke017"
						name="mediSpecDto.bke017" key="bke017" 
						maxlength="20" />
					<powersi:textfield label="录入人" id="aae011"
						name="mediSpecDto.aae011" key="aae011" readonly="true" />
					<powersi:textfield label="录入时间" id="aae036"
						name="mediSpecDto.aae036" key="aae036" readonly="false" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="医院意见" id="bke013"
						name="mediSpecDto.bke013" key="bke013"  colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="备注" id="aae013" name="mediSpecDto.aae013"
						key="aae013" required="false" readonly="false" colspan="7" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>

	</powersi:form>
	<script type="text/javascript">
	
	window.onload = function(){
		$('#akb020').val("<%=hospital_id%>");
		$('#akb021').val("<%=hospital_name%>");
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		
		
      	$("#button_save").attr("disabled",true);
	
		
		$("#aae036").val(year+"-"+month+"-"+day);
		$("#aae030").val(year+"-"+month+"-"+day);
		$("#aae031").val("2099-12-31");
		
		//统筹区编码
		$('#aaa027').val("<%=aaa027%>");	        
        $('#aae011').val("<%=userName%>");
	}
	
	//去除左右两边的空格
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	

	
	function queryBizInfo(json){
		if(!checkJSONResult(json)){
			return;
		}
		if(json.message !=""){
			popupInfo(json.message);
			clearall();
		}else{
			var select = document.getElementById("bke003");
			if(json.data.length != 0){
				//获取特殊业务申请可用疾病信息
				select.options.length = 0;
			for(i=0;i<json.data.length;i++){
				//[{code_wb=NNIFGNGUGNEO, serial_icd=12, icd=B18.101, disease=慢性活动型乙型病毒性肝炎, code_py=MXHDXYXBDXGY}]
				var dise_code = json.data[i].disease;
				var dise_name = json.data[i].icd;
				var option = new Option(dise_code,dise_name);
				select.add(option);
				}
			}else{
				select.options.length = 0;//置空
			}
		}
	}
	//选择疾病信息
	function chooseDis(){
		popupDialog({
			url: "<%=path%>/medicare/TeamVsSelfPayApplyAction!chooseSelfPayDisease.action",
			onClosed: function() {
				var ret = this.returnValue;
				if(ret != null){
					var select = document.getElementById("bke003");
					select.options.length = 0;
					var option = new Option(ret.aka121,ret.aka120);
					select.add(option);
				}
			}
		}, 600, 600);
	}

	
	function saveInfo(){

		var saveItemData = $("#mainForm").serialize();
		if($("#arg_value").val() ==""){
			popupAlert("请先输入申请人信息查询!");
			return;
		}
		if(!checkForm()){
			return;
		}
		//校验开始日期必须小于或等于结束日期
		if($("#aae030").val() != "" && $("#aae031").val() != ""){
			if($("#aae030").val() > $("#aae031").val()){
				popupAlert("开始日期必须小于或等于结束日期!");
				return;
			}
		}
		if(!confirm("是否保存申请信息!")){
			return;
		}
		postJSON("<%=path%>/medicare/TeamVsSelfPayApplyAction!savePaysCompensation.action",saveItemData, afterSaveItem);
	}

	function afterSaveItem(json) {
		if (!checkJSONResult(json)) {
			return;
		} else {
			if (json.data == "") {
				popupAlert("保存信息失败!");
				clearall();//重置
			} else {
		     	popupInfo(json.data);
		     	closeDialog();
			}
		}
		
	}
	
	function clearall() {
		$("#aac001").val("");
		$("#aac003").val("");
		$("#aac004").val("");
		$("#aac066").val("");
		$("#aac002").val("");
		$("#aac006").val("");
		$("#bka008").val("");
		$("#aac007").val("");
		$("#bka006").val("");
		$("#arg_value").val("");
		$("#bke016").val("");
		$("#bke017").val("");
		$("#bke013").val("");
		$("#aae013").val("");
		$("#bke003").val("");
		$("#bka004").val("");
		$("#aab069").val("");
		$("#tracestring").val("");

	}
	function readic() {
		try {
			var objCard = document.getElementById("cardControl");
			if (objCard.object == null) {
				popupAlert("请先安装读卡插件");
			}
		} catch (e) {
			popupAlert("请先安装读卡插件");
		}
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
			var aaa027 = $("#aaa027").val();
			var argValue = tracestring.substr(0);
			//根据电脑号和身份证号的长度来判断输入的是电脑号还是身份证号
			if(argValue.length == "10"){
				var argName = indi_id;
			}else if(argValue.length == "15" || argValue.length == "18"){
				var argName = idcard;
			}
		   //调用业务申请人员基本信息action
		   postJSON("<%=path%>/medicare/TeamVsSelfPayApplyAction!getPersonByPaysCompensation.action",
				   {"mediSpecDto.arg_name":argName,"mediSpecDto.arg_value":argValue},function(json){
			   if(!checkJSONResult(json)){
				   return;
			   }			   
				if (json.errortype==0) {
					$.each(json.data,function(key,value){
					    $("#"+key).val(value);
					    if(key == "aac031"){
					    	if(value == "0"){
					    		popupAlert("此人没有参保!");
					    	}else if(value == "2"){
					    		popupAlert("此人暂停参保!");
					    	}else if(value == "3"){
					    		popupAlert("此人已停止参保!");
					    	}
					    }
					});
				}else{
					popupInfo(json.message);
					return;
				}
		   });
			
			$("#button_save").attr("disabled", false);
		}
		
						
	</script>
</body>
</powersi:html>