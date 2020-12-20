<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String path = request.getContextPath(); 

	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();	
%>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/powerprint.min.js"></script>
<script type="text/javascript"
	src="${rootPath}/resource/report/js/LodopFuncs.js"></script>
<powersi:html>
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:head title="门诊特定病种登记" />
	<powersi:form id="mainForm" namespace="/medicare" action="MzchoHospitalBusApplyAction!saveModiSpeciaRegister.action">
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="button_save" key="保存登记" onclick="saveInfo()" ></powersi:button>							
				<powersi:button id="button_printBack" key="打印病种认定回执单" onclick="printDiseaseInfo()"></powersi:button>
				<powersi:reset id="btReset" label="重置界面_hi" cssStyle="display:none;" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
						<powersi:textfield id="tracestring" name="tracestring" label="查询条件"
						title="请输入信息回车" placeholder="请输入信息回车" readonly="false"
						onkeydown="queryPresonInfo(this)" buttonText="读卡" buttonId="readic_button"
						buttonDisabled="false" onbuttonclick="readic()" />
					<powersi:hidden id="querystring" name="mediSpecZHDto.querystring" />	
					<powersi:textfield  id="aac002" name="mediSpecZHDto.aac002" label="身份证号" readonly="true" />			
					<powersi:textfield id="aac001" name="mediSpecZHDto.aac001" label="电脑号" readonly="true" />
					<powersi:textfield id="aac003" name="mediSpecZHDto.aac003" label="姓名" readonly="true" />	
				</powersi:editorlayout-row>
				
			</powersi:editorlayout>
		</powersi:panelbox>

		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>									
					<powersi:textfield label="出生日期" id="aac006" name="mediSpecZHDto.aac006" key="aac006" readonly="true" />							    			
					<powersi:textfield label="人员类别" id="bka004_name" name="mediSpecZHDto.bka004_name"   readonly="true" />
					<powersi:hidden id="bka004" name="mediSpecZHDto.bka004" />					
					<powersi:textfield label="所属单位" id="bka008" name="mediSpecZHDto.bka008" key="bka008" readonly="true"  />
					<powersi:textfield label="参保险种" id="aae140_name" name="mediSpecZHDto.aae140_name" key="aae140_name" readonly="true" />	
					<powersi:hidden id="aae140" name="mediSpecZHDto.aae140" />			
				</powersi:editorlayout-row>				
			</powersi:editorlayout>
		</powersi:groupbox>

		<powersi:groupbox title="病种登记信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="联系人" id="aae004" name="mediSpecZHDto.aae004"  readonly="true" colspan="3" />	
					<powersi:textfield label="手机号码" id="aae005" name="mediSpecZHDto.aae005" key="aae005"  colspan="3"/>							
				</powersi:editorlayout-row>

				<powersi:editorlayout-row>
					
					<powersi:textfield id="aaa001" name="mediSpecZHDto.aaa001" label="医院专家"  colspan="3" readonly="true" buttonText="选     择"   required="true"
						onkeydown="keydown(this)" buttonId="aaa001_button" onbuttonclick="chooseDoctor('aaa001')" />
					<powersi:hidden id="aaa001_bkc406" value=""/>
					<powersi:codeselect id="bka006" label="病种名称" name="mediSpecZHDto.bka006" codeType="bka006" cssClass="select2"  colspan="3"
						codeFilter="data_value like '13%' "  codeSort="1" required="true" codeLocal="<%=aaa027%>"  onchange="getaae031(1)" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aaa002" name="mediSpecZHDto.aaa002" label="医院专家2" colspan="3" readonly="true" buttonText="选     择"
					     onkeydown="keydown(this)"  buttonId="aaa002_button" onbuttonclick="chooseDoctor('aaa002')" />
					<powersi:hidden id="aaa002_bkc406" value=""/>
					<powersi:codeselect id="aka120" label="疾病诊断" name="mediSpecZHDto.aka120" codeType="aka120" cssClass="select2"  colspan="3"
						headerKey="" headerValue="请选择..." showValue="false"  codeSort="1" required="true" codeLocal="<%=aaa027%>" />
				
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield id="aaa003" name="mediSpecZHDto.aaa003" label="医院专家3"  colspan="3" readonly="true" buttonText="选     择"
					     onkeydown="keydown(this)"   buttonId="aaa003_button" onbuttonclick="chooseDoctor('aaa003')" />
					<powersi:hidden id="aaa003_bkc406" value=""/>
					<powersi:textfield id="bka911" name="mediSpecZHDto.bka911" label="病种手术日期" mask="date"   colspan="3" />					
				
				</powersi:editorlayout-row> 
				<powersi:editorlayout-row>
						<powersi:textfield label="病种开始日期" mask="date"  id="aae030" name="mediSpecZHDto.aae030" colspan="3" required="true" onchange="getaae031(0)" />
						<powersi:textfield label="病种截止日期"   id="aae031" name="mediSpecZHDto.aae031" colspan="3" required="true" readonly="true" />
				</powersi:editorlayout-row>
				
				<powersi:editorlayout-row>
					<powersi:textfield id="bka028" name="mediSpecZHDto.bka028" label="确诊时间" mask="date"   colspan="3" />
					
					<powersi:codeselect id="bka011" name="mediSpecZHDto.bka011" label="是否提前认定" codeType="bka848" headerKey="-1"   colspan="3"/>
				
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:codeselect id="bka012" name="mediSpecZHDto.bka012" label="是否需要年审" codeType="bka848"  headerKey="-1"   colspan="3"/>
					<powersi:textfield id="aae016" name="mediSpecZHDto.aae016"  value="未审核" label="审核标志"  readonly="true"  colspan="3"  />	
				</powersi:editorlayout-row>		
				
				<powersi:editorlayout-row>
					<powersi:codeselect id="bka010" name="mediSpecZHDto.bka010" label="是否境外" codeType="bka848" headerKey="-1"  colspan="3"/>
				</powersi:editorlayout-row>		
				<powersi:editorlayout-row>
					<powersi:textarea label="鉴定标准" id="bka014" 
						key="bka013" required="true" readonly="true" colspan="7" onclick="getIdentify()" rows="7" />
				</powersi:editorlayout-row>

			</powersi:editorlayout>
		</powersi:groupbox>
			<powersi:hidden id="bke548" name="mediSpecZHDto.bke548" />
			<powersi:hidden id="bka013" name="mediSpecZHDto.bka013" />
			<powersi:hidden id="bizid" name="mediSpecZHDto.bizid" />	
			<powersi:hidden id="bka007" name="mediSpecZHDto.bka007" />	
			<powersi:hidden id="aka121" name="mediSpecZHDto.aka121" />	
	</powersi:form>
	<script type="text/javascript">
	window.onload = function(){		
		var myDate = new Date();
		var year = myDate.getFullYear();
		var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
		var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
		var today= year+month+day;
		$("#aae030").val(today);
	 	$("#aae031").val("");
		 			
		$('#akb020').val("<%=hospital_id%>");
		$('#akb021').val("<%=hospital_name%>");
		$('#bkc027').val("<%=userName%>");
      	$('#bkc028').val("<%=loginUser%>");
     			
    	$("#button_save").attr("disabled",true);
	    $("#button_cancel").attr("disabled",true);
	    $("#button_printBack").attr("disabled",true);
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
							$("#tracestring").val(json.data.aac002);
							queryPresonInfo("readic");
						}
					}
				});
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
			var argValue = tracestring.substr(0);
		   //调用业务申请人员基本信息action
		   postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!queryMediPersonInfo.action",
					{
						"mediSpecZHDto.querystring" : argValue
					}, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						 if (json.errortype == 0) {
							$.each(json.data, function(key, value) {
								$("#" + key).val(value);
							});
						} else {
							popupInfo(json.message);
							return;
						} 	
							$("#button_save").attr("disabled",false);
							$("#button_cancel").attr("disabled",false);
					});
	  }
	  
	//去除左右两边的空格
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	/*选择医生*/
	function chooseDoctor(para) {
		popupDialog(
				{
					url : "${rootPath}/medicare/MzchoHospitalBusApplyAction!chooseDoctor.action",
					onClosed : function() {
						var retValue = this.returnValue;
						
						if (retValue) {
							var aaa001=$("#aaa001").val();
							var aaa002=$("#aaa002").val();
							var aaa003=$("#aaa003").val();
							if(aaa001 == retValue.bkc275 || aaa002 == retValue.bkc275 || aaa003 == retValue.bkc275){
									popupAlert("该专家已选，请不要重复选择");
							}else{
								$("#"+para).val(retValue.bkc275);
								$("#"+para+"_bkc406").val(retValue.bkc406);
							}							
						} else {
							$("#"+para).val("");
							$("#"+para+"_bkc406").val("");
						}
						//根据专家流水号 --获取对应的病种信息
						postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!chooseDoctorBka006.action",
								{
									"bkc406_1":$("#aaa001_bkc406").val(),
									"bkc406_2":$("#aaa002_bkc406").val(),
									"bkc406_3":$("#aaa003_bkc406").val()
								}, 
								function(json) {
										if (!checkJSONResult(json)) {
											return;
										}	
										if (json.data != null) {
											var a = [];
											$.each(json.data, function(key, value) {
												a.push('<option value="');
												a.push(key);
												a.push('"');
												a.push(">");
												a.push(value);
												a.push("</option>");
											});
											$("#bka006").empty();
											$("#bka006").append("<option value='' >" + "请选择..." + "</option>");
											$("#bka006").change();
											$("#bka006").append(a.join(''));
										}
										if (!powersi.isnull(json.message)) {
											popupInfo(json.message);
										}
									});
						
					}
				}, 500, 600);
		}
	
	/*获得鉴定标准内容*/
	function getIdentify(){
		/* var bka014=$("#bka014").val();
		if(bka014!=""){
			return;
		} */
		var bin=$("#bka006").val();
		if(bin==null || bin =="undefined" || bin==""){
			return;
		}
		popupDialog(
				{
					url : "${rootPath}/medicare/MzchoHospitalBusApplyAction!getIdentify.action?mediSpecZHDto.bka006="+bin,
					onClosed : function() {
						var retValue = this.returnValue;
						$("#bka014").html(retValue.tem);
						$("#bka013").val(retValue.flag);						
					}
				}, 500, 600);
	}
	
	/*保存信息*/
	function saveInfo(){
		var bka007= $("#bka006").find("option:selected").text();
		$("#bka007").val(bka007);
		var aka121= $("#aka120").find("option:selected").text();
		$("#aka121").val(aka121);
		var saveItemData = $("#mainForm").serialize();	
		if($("#bka006").val() =="13C06"){	//恶性肿瘤，确诊日期必填
			$("#bka028").attr("required",true);
		}
		if($("#bka006").val() =="13C08"){	//造血干细胞移植，病种手术日期必填
			$("#bka911").attr("required",true);
		}		
		//是否境外，，如果境外选上，则专家三个需必选
		var bka010=$("#bka010").val();
		if("1"==bka010){
			$("#aaa002").attr("required",true);
			$("#aaa003").attr("required",true);
				
		}else{
			$("#aaa002").attr("required",false);
			$("#aaa003").attr("required",false);
		}		
		
		if(!checkForm()){
			return;
		}

		if($("#bka014").val() ==""){	//鉴定标准内容不能为空
			popupAlert("鉴定标准不能为空！");
			return;
		}
		
		if(!confirm("是否保存病种登记信息!")){
			return;
		}
		postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!saveModiSpeciaRegister.action",saveItemData, afterSaveItem);
	}
	function afterSaveItem(json) {

		if (!checkJSONResult(json)) {
			return;
		} else {
			if (json.data == "") {
				popupAlert("保存信息失败!");
				$("#btReset").click();
				clearall();//重置
			} else {
		     	popupInfo(json.data);
		    	$("#button_save").attr("disabled",true);
		    	$("#button_printBack").attr("disabled",false);	    	
			}
		}
	}

		
	/*打印回执单*/
	function printDiseaseInfo(){
		var aac003=$("#aac003").val();
		var aae005=$("#aae005").val();
		var bka006=$("#bka006").val();
		var aac002=$("#aac002").val();
		popupDialog(
				{
					url : "${rootPath}/medicare/MzchoHospitalBusApplyAction!printDiseaseInfo.action?mediSpecZHDto.bka006="+bka006+
							"&mediSpecZHDto.aac003="+encodeURI(encodeURI(aac003))+"&mediSpecZHDto.aae005="+aae005+"&mediSpecZHDto.aac002="+aac002,
					onClosed : function() {					
					}
				}, 500, 650);
		
	}
	
	
	
	function clearall() {
		var myDate = new Date;
		var year = myDate.getFullYear();
		var month = (myDate.getMonth() + 1).toString().length == 1 ? "0"
				+ (myDate.getMonth() + 1).toString() : (myDate.getMonth() + 1).toString();
		var day = myDate.getDate().toString().length == 1 ? "0"+ myDate.getDate().toString() : myDate.getDate().toString();	 	
		$("#aae030").val(year  + month + day);
	}
	
	function getaae031(pam){
		
		var aae030=$("#aae030").val();
		var bka006=$("#bka006").val();
		if(!(aae030 ==null || aae030 =="undefined" || aae030=="") && !(bka006==null || bka006 =="undefined" || bka006=="")){
			var year=aae030.substring(0,4);			
			var last=aae030.substring(4,9);
			if("13C35" == bka006 || "13C37" == bka006 ){//5年截至日期-->精神类疾病，恶性肿瘤（含恶性血液病）-中
				
				var a=parseInt(year)+5;
				$("#aae031").val(a+last);
													//脑血管病，溃疡性结肠炎，慢性肝炎（中度及以上）-3，结核病（活动性），恶性肿瘤（含恶性血液病）-3
			}else if("13C21"== bka006 || "13C34" == bka006 || "13C32" == bka006 || "13C12" == bka006 || "13C06_2" ==bka006){ //3年截至日期
				
				var b=parseInt(year)+3;
				$("#aae031").val(b+last);
				
			}else if("13C31" == bka006){ //4年截至日期 ---->癫痫
				
				var c=parseInt(year)+4;
				$("#aae031").val(c+last);
				
			}else if("13C32_2" == bka006){ //6年截至日期 ，--->慢性肝炎（中度及以上）-6
				
				var d=parseInt(year)+6;
				$("#aae031").val(d+last); 
				
			}else if("13C06_1" == bka006){//2年截至日期，--->恶性肿瘤（含恶性血液病）-2,
				
				var e=parseInt(year)+2;
				$("#aae031").val(e+last);
				
			}else if("13C08" == bka006){//1年截至日期 ---->造血干细胞移植后第一年
				
				var f=parseInt(year)+2;
				$("#aae031").val(f+last);
				
			}else{		
				
				$("#aae031").val("20991231");
			}
		}
		$("#aka120").empty();
		$("#aka120").append("<option value='' >" + "请选择..." + "</option>");
		$("#aka120").change();
		if(1 == pam){
			postJSON("${rootPath}/medicare/MzchoHospitalBusApplyAction!getbka500.action",
					{
						"mediSpecZHDto.bka006":bka006
					}, 
					function(json) {
							if (!checkJSONResult(json)) {
								return;
							}	
							if (json.data != null) {
								var a = [];
								$.each(json.data, function(key, value) {
									a.push('<option value="');
									a.push(key);
									a.push('"');
									a.push(">");
									a.push(value);
									a.push("</option>");
								});
								$("#aka120").append(a.join(''));
							}
							if (!powersi.isnull(json.message)) {
								popupInfo(json.message);
							}
						});
			} 
			
	}
</script>
</body>
</powersi:html>