<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%> 
<%
	String path = request.getContextPath();

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
	String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
<powersi:head title="门诊选点业务申请" />
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="mainForm">
		<div id="div1" style="display: none">
			<powersi:hidden id="aae140" name="mediSpecDto.aae140" />
			<powersi:hidden id="aac004" name="mediSpecDto.aac004" />
			<powersi:hidden id="aka130" name="mediSpecDto.aka130" key="aka130"
				value="11" />
			<powersi:hidden id="baa027" name="mediSpecDto.baa027" />
			<powersi:hidden id="aaa027" name="mediSpecDto.aaa027" />
			<powersi:hidden id="aac066" name="mediSpecDto.aac066" />
			<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
			<!-- bkb100申请标志:1中心 0医院  -->
			<powersi:hidden id="bkb100" name="mediSpecDto.bkb100" value="0"/>
		</div>
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button key="门诊登记表" onclick="clinicRegister()" />
				<powersi:button id="fundStatus" key="基金状态查询"
					onclick="fundStatusInfo()" />
				<powersi:button id="button_save" key="保存" onclick="saveInfo()" />
				<powersi:button key="重置" onclick="clearall()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡"
						buttonId="readic_button" buttonDisabled="false"
						onbuttonclick="readic()" />
					<powersi:textfield id="aac001" name="mediSpecDto.aac001" key="电脑号"
						readonly="true" required="true" />
					<powersi:textfield id="aac003" name="mediSpecDto.aac003" key="姓名"
						readonly="true" required="true" />
					<powersi:textfield id="aac002" name="mediSpecDto.aac002" key="身份证号"
						readonly="true" required="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="aac006" name="mediSpecDto.aac006" key="出生日期"
						readonly="true" />
					<powersi:textfield id="dq11" name="mediSpecDto.dq11" key="当前年龄"
						readonly="true" />
					<powersi:textfield id="aab001" name="mediSpecDto.aab001" key="单位编号"
						readonly="true" required="true"/>
					<powersi:textfield id="aab069" name="mediSpecDto.aab069" key="单位名称"
						readonly="true" colspan="3" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="akb020" name="mediSpecDto.akb020" key="医院编号"
						readonly="true" required="true" />
					<powersi:textfield id="akb021" name="mediSpecDto.akb021"
						key="申请医院名称" readonly="true" required="true" />
					<powersi:textfield id="bac032" name="mediSpecDto.bac032"
						key="困难救助人群类别" readonly="true" code="bac032"/>
					<powersi:textfield id="bka004_name" 
						key="人员类别" readonly="true"  required="true"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<td align="right" class="tdLable"
						style="background: #f4f8fa none repeat scroll 0 0"><label
						for="" class="label">待遇类型<span class="required">*</span></label></td>
					<td><select id="bka006" name="mediSpecDto.bka006"
						class="select" onchange="changebka006()">
							<option value="110">普通门(急)诊</option>
							<option value="51C01">产前检查</option>
					</select></td>
					<powersi:textfield id="aae030" name="mediSpecDto.aae030" key="开始时间"
						readonly="true" required="true" />
					<powersi:textfield id="aae031" name="mediSpecDto.aae031" key="结束时间"
						readonly="true" value="2099-12-31" required="true" />
					<powersi:textfield id="bmc016" name="mediSpecDto.bmc016" key="预产期"
						mask="date" format="dateFmt:'yyyy-MM-dd'" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aae127" name="mediSpecDto.aae127" key="申请时间"
						readonly="true" />
					<powersi:textfield id="bke672" name="mediSpecDto.bke672"
						key="医院意见时间" readonly="true"/>
					<powersi:textfield id="aae011" name="mediSpecDto.aae011" key="录入人"
						readonly="true" />
					<powersi:textfield id="aae036" name="mediSpecDto.aae036" key="录入时间"
						readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="bke013" name="mediSpecDto.bke013" key="医院意见"
						value="同意" colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea id="aae013" name="mediSpecDto.aae013" key="备注"
						validate="maxSize[50]" colspan="7" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>

			<powersi:editorlayout cols="20%,80%">
				<powersi:editorlayout-row>
					<powersi:checkbox key="两病三师签约" name="aaz308" id="aaz308"
						onchange="verifyTeamQualifying()" />
					<powersi:hidden id="team_tag" name="mediSpecDto.team_tag"
						key="两病三师签约标记" value="0" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
			
		</powersi:groupbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
	</powersi:form>
	<script type="text/javascript">
window.onload = function(){
	
    $('#akb020').val("<%=hospital_id%>");
 	$('#akb021').val("<%=hospital_name%>");
 	$('#aaa027').val("<%=aaa027%>");
	
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		$('#aae127').val(year+"-"+month+"-"+day);
		$('#aae036').val(year+"-"+month+"-"+day);
		$('#bke672').val(year+"-"+month+"-"+day);
		$('#aae030').val(year+"-"+month+"-"+day);
		$('#aae011').val("<%=userName%>");
		$("#button_save").attr("disabled",true);
      	$("#fundStatus").attr("disabled",true);
      	
      //隐藏预产期控件
		$("#bmc016").hide(); //隐藏控件框
		$("[for=bmc016]").hide(); //隐藏控件文字
		$("#popupbutton_bmc016").hide(); //隐藏控件按钮
		
		//设置有效期
		//set_up_date();
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

	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	//保存申请信息
	function saveInfo(){	
		if(!checkFormValidtion())
     	{
	  		return;
		}
		 //生育产检一定要输入预产期
		 /*   if ($("#bka006").val()=="11B"){
				if ($("#bmc016").val()==''){
					popupAlert('请先输入预产期！');
					return;
				}
				$("#aae031").val($("#bmc016").val());
		   } */
		   if($("#bka006").val()=="110"){
			   popupAlert("请确认参保人选定本院为本社保年度门诊选定医院?");
		   }else{
			   if($("#bka006").val()=="51C01"){
				   popupAlert("请确认参保人选定本院为产前检查选定医院?");
			   }
		   }
		  
			var bizinfo = $("#mainForm").serialize();
			postJSON("<%=path%>/medicare/MzchoHospitalBusApplyAction!saveSqInfo.action",bizinfo,saveinfo_after);
	}
	
	function saveinfo_after(json){
		if (!checkJSONResult(json)) {
			return;
		} 
		if (json.data.length<=0){
			   popupAlert('选点操作失败！');
		   }else{
			   popupInfo(json.data);
			   if($("#bka006").val()=="110"){
				   $("#button_save").attr("disabled",false);
			   }else{
				   $("#button_save").attr("disabled",true);
			   }
		       $("#fundStatus").attr("disabled",true);
		   }
	}
	//设定社保年度开始日期
	/* function set_up_date(){
		var myDate = new Date();
		var year = myDate.getFullYear();
        var strCode = $("#aaa027").val();
        
		if(strCode.indexOf("4404") == 0){
			if( CompareDate() == "1"){
				var aged = year + 1; 
				$("#aae030").val(year + "-" + "07" + "-" + "01");	
				$("#aae031").val(aged + "-" + "06" + "-" + "30");	
			}else{
				var aged = year - 1; 
				$("#aae030").val(aged + "-" + "07" + "-" + "01");	
				$("#aae031").val(year + "-" + "06" + "-" + "30");	
			}
		}else {
			$("#aae030").val(year + "-" + "01" + "-" + "01");	
			$("#aae031").val(year + "-" + "12" + "-" + "31");	
		}
	}*/

	
	
	function CompareDate(){
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		
		var strToday = year+"-"+month+"-"+day;
		var strDate = year + "-" + "06" + "-" + "30";
		if(new Date(strToday.replace(/-/g,"\/")) > new Date(strDate.replace(/-/g,"\/"))){
			return 1;
		}else{
			return -1;
		}
	}
	 

	//计算参保人年龄
	function age(){
		var myDate = new Date();
		var year = myDate.getFullYear();
	
		var birthday = document.getElementById("aac006").value.substr(0,4);
//			birthday = birthday.substr(0,4);
		var age = (year - birthday);
		document.getElementById("dq11").value = age;				
	}
	
	//门诊登记表
	function clinicRegister(){
		var aac001 = powersi.trim($("#aac001").val());
		if (powersi.isnull(aac001)) {
			popupAlert("人员信息不能为空,请先获取人员信息!");
			return;
		}
		
		popupDialog({
			url: "<%=path%>/medicare/MzchoHospitalBusApplyAction!getChangeApplyHosp.action?mediSpecDto.aac001="
								+ aac001,
						onClosed : function() {

						}
					}, 450, 800);

		}
		//基金状态查询
		function fundStatusInfo() {
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
		//城居产检与普通门(急)诊的选择
		function changebka006() {
			if ($("#bka006").val() == "110") {
				$("#bmc016").hide(); //隐藏控件
				$("[for=bmc016]").hide(); //隐藏控件
				$("#popupbutton_bmc016").hide(); //隐藏控件
				$("#aaz308").show();
				$("#aaz308").attr("disabled",false);
			} 
			if($("#bka006").val() == "51C01"){
				$("#bmc016").show(); //显示控件
				$("[for=bmc016]").show(); //显示控件
				$("#popupbutton_bmc016").show(); //显示控件
				var ldDate = new Date();
				ldDate.setMonth(ldDate.getMonth()+11);
				ldDate.setDate(ldDate.getDate());
				year = ldDate.getFullYear();
				month = (ldDate.getMonth()+1).toString().length == 1?"0"+(ldDate.getMonth()+1).toString():ldDate.getMonth().toString();
				day = ldDate.getDate().toString().length == 1?"0"+ldDate.getDate().toString():ldDate.getDate().toString();
				$("#aae031").val(year + "-" + month + "-" + day);
				$("#aaz308").hide();
				$("#aaz308").attr("disabled",true);
			}
		}

		function clearall() {

			$("#aac001").val("");
			$("#aac002").val("");
			$("#aac003").val("");
			$("#aac004").val("");
			$("#bka004_name").val("");
			$("#aac006").val("");
			$("#aab069").val("");
			$("#aab001").val("");
			$("#bke013").val("");
			$("#aae013").val("");
			$("#bmc016").val("");
			$("#dq11").val("");
			$("#tracestring").val("");
			$("#button_save").attr("disabled", true);
			$("#fundStatus").attr("disabled", true);
		}

		function verifyTeamQualifying() {
			if ($("#aaz308").is(':checked')) {
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("先获取参保人信息！");
					$("#aaz308").prop("checked", false);
					$("#team_tag").val("0");
					return;
				}
				//start  2017-12-20 由于电脑号位数已发生改变，需要修改查询病种方法
			/* var indi_id = $("#indi_id").val();
				var idcard = $("#idcard").val();
				var argValue = tracestring.substr(0);
				//根据电脑号和身份证号的长度来判断输入的是电脑号还是身份证号
				if (argValue.length == "10") {
					var Arg_name = indi_id;
				} else if (argValue.length == "15" || argValue.length == "18") {
					var Arg_name = idcard;
				} */
				postJSON(
						"${rootPath}/medicare/MzchoHospitalBusApplyAction!verifyTeamQualifying.action",
						{
							"mediSpecDto.aac001" : $("#aac001").val()
							
						}, function(json) {
							if (!checkJSONResult(json)) {
								return;
							}
							if (json.errortype == 0) {
								if (json.data == 1) {
									$("#aaz308").prop("checked", true);
									$("#team_tag").val("1");
								} else {
									$("#aaz308").prop("checked", false);
									$("#team_tag").val("0");
									popupInfo(json.message);
								}
							} else {
								$("#aaz308").prop("checked", false);
								$("#team_tag").val("0");
								popupInfo(json.message);
								return;
							}
						});
			} else {
				$("#aaz308").prop("checked", false)
				$("#team_tag").val("0");
			}

		}
		
		//查询业务申请人员基本信息
		function queryPresonInfo(para){
		   if (para == "readic" || window.event.keyCode == 13) {
			 //生育产检一定要输入预产期
			 /*   if ($("#bka006").val()=="11B"){
					if ($("#bmc016").val()==''){
						popupAlert('请先输入预产期！');
						return;
					}
			   } */
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
				var bka006 = powersi.trim($("#bka006").val());
			   //调用业务申请人员基本信息action
			   postJSON("<%=path%>/medicare/MtmmSpecialApplyAction!queryMediPersonInfo.action",
						{
							"mediSpecDto.arg_name" : indi_id,
							"mediSpecDto.arg_value" : argValue,
							"mediSpecDto.bka006": bka006
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
												if (json.data.personinfo[0].aab069 == null
														|| json.data.personinfo[0].aab069 == '') {
													$("#aab069").val(json.data.personinfo[0].bka008);
												} else {
													$("#aab069").val(json.data.personinfo[0].aab069);
												}
												age();
												 verifyApply();
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
								if (json.data.personinfo[0].aab069 == null
										|| json.data.personinfo[0].aab069 == '') {
									$("#aab069").val(json.data.personinfo[0].bka008);
								} else {
									$("#aab069").val(json.data.personinfo[0].aab069);
								}
								age();
								 verifyApply();
							}	
							
						});
			   
			}
		
		function verifyApply(){
			if(!checkFormValidtion())
	     	{
		  		return;
			}
		   //生育产检一定要输入预产期
		/*    if ($("#bka006").val()=="11B"){
				if ($("#bmc016").val()==''){
					popupAlert('请先输入预产期！');
					return;
				}
				$("#aae031").val($("#bmc016").val());
		   } */
			var bizinfo = $("#mainForm").serialize();
		
			postJSON("<%=path%>/medicare/MzchoHospitalBusApplyAction!verifyApply.action",bizinfo, function(json){
				if(!checkJSONResult(json)){
				    return;
			    }
				$("#button_save").attr("disabled",false);
				$("#fundStatus").attr("disabled",false);
				$("#aaz308").attr("disabled",false);
	  		}); 
		}
	</script>
</body>
</powersi:html>