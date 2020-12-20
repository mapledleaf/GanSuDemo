<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>

<%
	String path = request.getContextPath(); 

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaz267value = request.getAttribute("mediSpecDto.aaz267")==null?"0":request.getAttribute("mediSpecDto.aaz267").toString();
%>


<powersi:html>
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:head title="门慢特殊业务申请" />
	<powersi:form id="mainForm" namespace="/medicare"
		action="MtmmSpecialApplyAction!queryMediPersonInfo.action">
		<div id="div1" style="display: none">
			<powersi:hidden id="akb020" name="mediSpecDto.akb020" />
			<powersi:hidden id="akb021" name="mediSpecDto.akb021" />
			<powersi:hidden id="aae140" name="mediSpecDto.aae140" />
			<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
			<powersi:hidden id="baa027" name="mediSpecDto.baa027" />
		</div>

		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="fundStatus" key="基金状态查询"
					onclick="fundStatusInfo()" ></powersi:button>
				<powersi:button id="button_save" key="保存" onclick="saveInfo()"></powersi:button>
				<powersi:button cssClass="button" key="重置" onclick="clearall()"></powersi:button>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac001" label="电脑号" name="mediSpecDto.aac001" key="aac001" readonly="true"/>
					<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" />
					<powersi:hidden id="aac004" name="mediSpecDto.aac004" readonly="true" />
					<powersi:codeselect id="aac066" key="人员类别" name="mediSpecDto.bka004" codeType="bka004" disabled="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="身份证号" id="aac002"
						name="mediSpecDto.aac002" key="aac002" readonly="true" />
					<powersi:textfield label="出生日期" format="dateFmt:yyyy-MM-dd"
						id="aac006" name="mediSpecDto.aac006" key="aac006" readonly="true" />
					<powersi:textfield label="工作日期" format="dateFmt:yyyy-MM-dd"
						id="aac007" name="mediSpecDto.aac007" key="aac007" readonly="true" />
					<powersi:textfield label="所属单位" id="aab069"
						name="mediSpecDto.aab069" key="aab069" readonly="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>

		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">

				<powersi:editorlayout-row>
					<powersi:codeselect id="bka006" key="门慢项目"
						name="mediSpecDto.bka006" codeType="bka006" cssClass="select2" 
						codeFilter="data_value like '13%' "
						codeSort="1" required="true" codeLocal="<%=aaa027%>"
						onchange="wf_get_latest_apply(this)" />
					<powersi:select label="疾病名称" id="bke003" name="mediSpecDto.bke003"
						key="bke003" required="true" />
					<powersi:textfield label="开始日期" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aae030"
						name="mediSpecDto.aae030" key="aae030" required="true" />
					<powersi:textfield label="结束日期" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="aae031"
						name="mediSpecDto.aae031" key="aae031" required="false" />
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					<powersi:textfield label="就诊科室" id="bke015"
						name="mediSpecDto.bke015" key="bke015" required="true"
						maxlength="50" />
					<powersi:textfield label="科室电话" id="bke014"
						name="mediSpecDto.bke014" key="bke014" required="true"
						validate="integer" maxlength="20" />
					<powersi:textfield label="申请人电话" id="bke016"
						name="mediSpecDto.bke016" key="bke016" required="true"
						validate="integer" maxlength="50" />
					<powersi:textfield label="申请医师" id="bke017"
						name="mediSpecDto.bke017" key="bke017" required="true"
						maxlength="20" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="主任医师" id="bke018"
						name="mediSpecDto.bke018" key="bke018" required="true"
						maxlength="20" />
					<powersi:textfield label="主任意见时间" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="bke019"
						name="mediSpecDto.bke019" key="bke019" required="true" />
					<powersi:textfield label="医师意见时间" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="bke020"
						name="mediSpecDto.bke020" key="bke020" required="true" />
					<powersi:textfield label="医院意见时间" mask="date"
						format="dateFmt:'yyyy-MM-dd'" id="bke034"
						name="mediSpecDto.bke034" key="bke034" required="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="医院意见" id="bke013"
						name="mediSpecDto.bke013" key="bke013" required="true" colspan="3" />
					<powersi:textfield label="录入人" id="aae011"
						name="mediSpecDto.aae011" key="aae011" readonly="true" />
					<powersi:textfield label="录入时间" id="aae036"
						name="mediSpecDto.aae036" key="aae036" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="病情摘要及诊断" id="bke011"
						name="mediSpecDto.bke011" key="bke011" required="true" colspan="7" />
				</powersi:editorlayout-row>
				<%-- 	<powersi:editorlayout-row>
				<powersi:textarea label="诊治方案及项目构成" id="bke012" name="mediSpecDto.bke012" key="bke012" required="false" readonly="false" colspan="7" />
			</powersi:editorlayout-row> --%>
				<powersi:editorlayout-row>
					<powersi:textarea label="备注" id="aae013" name="mediSpecDto.aae013"
						key="aae013" required="false" readonly="false" colspan="7" />
				</powersi:editorlayout-row>

			</powersi:editorlayout>
		</powersi:groupbox>
        <powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
	</powersi:form>
	<script type="text/javascript">
	var is_admit_date ="";//最近一次同类业务的截止时间
	var is_apply_count ="";//申请的次数
	
	window.onload = function(){
		
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();

		var today= year+"-"+month+"-"+day;
		$("#aae030").val(today);
	 	$("#aae031").val("");
		 	
		
		$('#akb020').val("<%=hospital_id%>");
		$('#akb021').val("<%=hospital_name%>");
		$('#bkc027').val("<%=userName%>");
      	$('#bkc028').val("<%=loginUser%>");
      	$('#bkc029').val(year+"-"+month+"-"+day);
		
    	$("#button_save").attr("disabled",true);
	  	$("#fundStatus").attr("disabled",true);
		var aaz267value = "<%=aaz267value%>";
	
		//默认意见时间为当天
		if(aaz267value == 0){
			var myDate = new Date;
			var year = myDate.getFullYear();
			var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
			var day = myDate.getDate().toString().length == 1?"0"+ myDate.getDate().toString():myDate.getDate().toString();
		
			$("#aae036").val(year+"-"+month+"-"+day);
			$("#bke019").val(year+"-"+month+"-"+day);
			$("#bke020").val(year+"-"+month+"-"+day);
			$("#bke034").val(year+"-"+month+"-"+day);
			
			//统筹区编码
			$('#aaa027').val("<%=aaa027%>");	        
	        $('#aae011').val("<%=userName%>");
			
		}else{
			oldItemDate= $("#mainForm").serialize();
		}
	}
	
	var objCard = null;
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

	/*读卡*/
	function iReadCardBase() {
		$("#bke548").val("");
		$("#tracestring").val("");
		$("#arg_name").val("");
		loadCardControl();
		if (objCard.object != null) {
			var bke548 = null;// 读卡返回
			bke548 = objCard.ReadCardBase();
			$("#bke548").val(bke548);
		}
	}

	/*读卡获取后台信息*/
	function readic() {
		iReadCardBase();
		if (powersi.isnull($("#bke548").val())) {
			return;
		}
		var bizFormData = $("#mainForm").serialize();
		postJSON(
				"${rootPath}/inhospital/InhospitalManagerAction!readic.action",
				bizFormData, function(json) {
					if (!checkJSONResult(json)) {
						return;
					}
					if (json.data != "no") {
						if (!powersi.isnull(json.data.aac002)) {
							$("#arg_name").val("aac002");
							$("#tracestring").val(json.data.aac002);
							queryPresonInfo("readic");
							$("#arg_name").val("");
						}
					}
				});
	}

	//去除左右两边的空格
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	//校验本院是否可以申请该项目
	function wf_verify_hosp_treat(){
		var apply_content = $("#bka006").val();
		var hospital_id = $("#akb020").val();
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!verifyHospTreat.action",{"mediSpecDto.aka083":apply_content,"mediSpecDto.akb020":hospital_id},afterVerifyHospTreat);
	}
	
	function afterVerifyHospTreat(json){
		if(!checkJSONResult(json)){
			return;
		}
		if(json.message !=""){
			popupInfo(json.message);
			clearall();
		}else{
			
			var saveItemData = $("#mainForm").serialize();
			$("#disese").attr("disabled",true);
			postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!getBizInfo.action",saveItemData,queryBizInfo);
		}
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
	//获取最近一次的有效申请(获取同类申请信息)
	function wf_get_latest_apply(){
		var apply_content =  $("#bka006").val();
		var indi_id = $("#aac001").val();
		var hosptail_id = $("#akb020").val();
		if (powersi.isnull(indi_id)) {
			popupAlert("请先获取参保人员信息！");
			return;
		}

		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!verifySPApply.action",{"mediSpecDto.bka006":apply_content,"mediSpecDto.aac001":indi_id,"mediSpecDto.akb020":hosptail_id},getLatestApplyDate);
	}
	
	function getLatestApplyDate(json){
		if(!checkJSONResult(json)){
			return;
		}
		if(json.message != ""){
			popupInfo(json.message);
			clearall();
		}else{
			is_admit_date = json.data.max_date;
			wf_verify_hosp_treat();	 
		}
	}
	

	function fundStatusInfo(){
		var aac001 = powersi.trim($("#aac001").val());
		if (powersi.isnull(aac001)) {
			return;
		}
		var aae140 = powersi.trim($("#aae140").val());
		if (powersi.isnull(aae140)) {
			return;
		}

		popupDialog(
				{
					url : "${rootPath}/common/CommonManagerAction!queryPersonFund.action?inHospitalDTO.aac001="
							+ aac001 + "&inHospitalDTO.aae140=" + aae140,
					onClosed : function() {
						var retValue = this.returnValue;
						if (retValue) {

						} else {

						}
					}
				}, 400, 700);
	}
	function saveInfo(){
		var saveItemData = $("#mainForm").serialize();
		if($("#aac001").val() ==""){
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
		}else{
			if($("#aae031").val()!=""){
				if(is_admit_date >= $("#aae031").val()){
					if(!confirm("此人已经有截止至"+is_admit_date+"同类门特项目记录,~r~n是否继续!")){
						return;
					}
				}
			}
		}
		postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!saveSpeBsInfo.action",saveItemData, afterSaveItem);
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
		    	$("#button_save").attr("disabled",true);
			  	$("#fundStatus").attr("disabled",true);
			}
		}
	}

	function clearall() {
		$("#aac001").val("");
		$("#arg_value").val("");
		$("#aac003").val("");
		$("#aac004").val("");
		$("#aac012").val("");
		$("#bka004").val("");
		$("#aac002").val("");
		$("#aac006").val("");
		$("#aab069").val("");
		$("#aac007").val("");
		$("#aka083").val("");
		$("#bke014").val("");
		$("#bke015").val("");
		$("#bke016").val("");
		$("#bke017").val("");
		$("#bke018").val("");
		$("#bke019").val("");
		$("#bke020").val("");
//		$("#aae036").val("");
		$("#bke013").val("");
		$("#bke034").val("");
		$("#bke011").val("");
		$("#bke012").val("");
		$("#aae013").val("");
		$("#bke003").val("");
		$("#aac066").val("");
		$("#baa027").val("");

		var myDate = new Date;
		var year = myDate.getFullYear();
		var month = (myDate.getMonth() + 1).toString().length == 1 ? "0"
				+ (myDate.getMonth() + 1).toString() : (myDate.getMonth() + 1).toString();
		var day = myDate.getDate().toString().length == 1 ? "0"+ myDate.getDate().toString() : myDate.getDate().toString();
	 	
		$("#bke019").val(year + "-" + month + "-" + day);
		$("#bke020").val(year + "-" + month + "-" + day);
		$("#bke034").val(year + "-" + month + "-" + day);
		
		$("#bka006").val("");
		$("#tracestring").val("");
		$("#aac004_name").val("");
	}
	
	//查询业务申请人员基本信息
	   function queryPresonInfo(para){
		   if (para == "readic" || window.event.keyCode == 13) {
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
						/* if (json.errortype == 0) {
							$.each(json.data, function(key, value) {
								$("#" + key).val(value);
							});
						} else {
							popupInfo(json.message);
							return;
						} */
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
											
											if(json.data.personinfo[0].aab069 == null || json.data.personinfo[0].aab069 ==''){
												$("#aab069").val(json.data.personinfo[0].bka008);
											}else{
												$("#aab069").val(json.data.personinfo[0].aab069);
											}
											$("[name='mediSpecDto.bka004']").val(
													json.data.personinfo[0].bka004);
											$("[name='mediSpecDto.aac004']").val(
													json.data.personinfo[0].aac004);
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
							if(json.data.personinfo[0].aab069 == null || json.data.personinfo[0].aab069 ==''){
								$("#aab069").val(json.data.personinfo[0].bka008);
							}else{
								$("#aab069").val(json.data.personinfo[0].aab069);
							}
							$("[name='mediSpecDto.bka004']").val(
									json.data.personinfo[0].bka004);
							$("[name='mediSpecDto.aac004']").val(
									json.data.personinfo[0].aac004);
						}	
						
					});
			/** 
			 //启用门特(门慢)项目下拉列表
			 $('#aka083').attr('disabled',false);	
			 */
			 
			$("#button_save").attr("disabled", false);
			$("#fundStatus").attr("disabled", false);
		}
	</script>
</body>
</powersi:html>