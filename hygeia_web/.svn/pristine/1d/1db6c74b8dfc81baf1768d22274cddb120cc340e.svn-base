<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String aaa027 = BizHelper.getAaa027();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>
<powersi:html>
<head>
<powersi:head title="转诊转院业务申请" />
</head>
<body>
    <div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('社保卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="mainForm">
		<div id="div1" style="display: none">
			<!-- 转往医疗服务机构编号-->
			<powersi:hidden id="ake017" name="mediSpecDto.ake017"/>
			<!-- 原就诊医疗服务机构编号-->
			<powersi:hidden id="akb041" name="mediSpecDto.akb041"/>
			<!-- 住院业务类型 ：12-->
			<powersi:hidden id="aka130" name="mediSpecDto.aka130" value="12"/>
			<!--  业务序列号 -->
			<powersi:hidden id="aaz217" name="mediSpecDto.aaz217" />
			<powersi:hidden id="aac001value" name="mediSpecDto.aac001value" />
			<powersi:hidden id="akb020" name="mediSpecDto.akb020" />
			<powersi:hidden id="akb021" name="mediSpecDto.akb021" />
			<powersi:hidden id="aac002" name="mediSpecDto.aac002" />
			<powersi:hidden id="aac004" name="mediSpecDto.aac004" />
			<powersi:hidden id="bka004" name="mediSpecDto.bka004" />
			<powersi:hidden id="aae011" name="mediSpecDto.aae011" />
			<powersi:hidden id="aae036" name="mediSpecDto.aae036" />
			<powersi:hidden id="aae140" name="mediSpecDto.aae140" />
		</div>
		<powersi:panelbox title="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:button id="button_save" key="button_save"
					onclick="saveInfo()" />
				<powersi:button key="button_reset" onclick="clearall()" />
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield id="arg_value" name="mediSpecDto.arg_value"
						label="查询条件" title="请输入信息回车" placeholder="请输入信息回车"
						readonly="false" buttonText="读卡" buttonId="readic_button"
						buttonDisabled="false" onbuttonclick="readic()" />
					<powersi:textfield label="姓名" id="aac003" name="mediSpecDto.aac003"
						key="aac003" readonly="true" required="true" />
					<powersi:textfield id="aac001" name="mediSpecDto.aac001" key="电脑号"
						readonly="true" required="true" />
					<powersi:codeselect id="aac066" key="人员类别"
						name="mediSpecDto.bka004" codeType="bka004" disabled="true" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:groupbox title="人员信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="出生日期" id="aac006"
						name="mediSpecDto.aac006" readonly="true" />
					<powersi:textfield label="科室" id="bka020" name="mediSpecDto.bka020"
						readonly="true" />
					<powersi:textfield label="床位号" id="bka023"
						name="mediSpecDto.bka023" readonly="true" />
					<powersi:textfield label="住院号" id="bka025"
						name="mediSpecDto.bka025" readonly="true" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textfield label="单位名称" id="aab069"
						name="mediSpecDto.aab069" readonly="true" colspan="3" />
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:groupbox>
		<powersi:panelbox title="详细信息">
			<powersi:editorlayout cols="8">
				<powersi:editorlayout-row>
					<powersi:textfield label="转入医院" id="akc172"
						name="mediSpecDto.akc172" key="akc172" required="true"
						buttonText="选择" buttonId="hospse" onbuttonclick="chooseToHosp()" />
						
					<powersi:textfield label="医院审批人" id="bke036"
						name="mediSpecDto.bke036" key="bke036" required="true" />
						
					<powersi:textfield label="申请人姓名" id="bke128"
						name="mediSpecDto.bke128" key="bke128" required="false"
						readonly="false" />
					<powersi:textfield label="申请时间" id="bke129"
						name="mediSpecDto.bke129" key="bke129" readonly="true"/>
					
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="病人意见" id="bke127"
						name="mediSpecDto.bke127" key="bke127" required="true" colspan="7"/>
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="转出医院医务科意见" id="bke008"
						name="mediSpecDto.bke008" key="bke008" required="true" colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="病情摘要" id="bke009"
						name="mediSpecDto.bke009" key="bke009" required="true" colspan="7" />
				</powersi:editorlayout-row>
				<powersi:editorlayout-row>
					<powersi:textarea label="备注" id="aae013" name="mediSpecDto.aae013"
						key="aae013" colspan="7" />
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="arg_name" name="arg_name" />
	</powersi:form>
	<powersi:errors />
	<script type="text/javascript">
		window.onload = function(){
			$('#akb020').val("<%=hospital_id%>");
		 	$('#akb021').val("<%=hospital_name%>");
			
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = (myDate.getMonth()+1).toString().length==1?"0"+(myDate.getMonth()+1).toString():(myDate.getMonth()+1).toString();
			var day = myDate.getDate().toString().length==1?"0"+myDate.getDate().toString():myDate.getDate().toString();
			$('#aae127').val(year+"-"+month+"-"+day);
			$('#aae036').val(year+"-"+month+"-"+day);
			$('#aae030').val(year+"-"+month+"-"+day);
			$('#bke047').val(year+"-"+month+"-"+day);
			$('#bke129').val(year+"-"+month+"-"+day);
			$('#bke036').val("<%=userName%>");
			$('#bke127').val("同意");
			$('#bke008').val("同意");
		 	$('#aaa027').val("<%=aaa027%>");		
			$('#aae011').val("<%=userName%>");
			$("#button_save").attr("disabled",true);
		}

		document.onkeydown = function(event) {
			var e = event || window.event;
			if (e && e.keyCode == 13) {
				queryPresonInfo();
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
			$("#arg_value").val("");
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
								$("#arg_value").val(json.data.aac002);
								queryPresonInfo();
								$("#arg_name").val("");
							}
						}
					});
		}

		//查询业务申请人员基本信息
		function queryPresonInfo() {
			//需要校验输入框是否已经输入值，如果没有，给出提示并返回
			var querystring = powersi.trim($("#arg_value").val());
			if (powersi.isnull(querystring)) {
				popupAlert("请输入有效查询条件");
				return;
			}
			
			var indi_id = 0;
			var saveItemData = $("#mainForm").serialize();
			postJSON(
					"${rootPath}/medicare/ChangeHospitalBusApplyAction!queryChanHospPersonInfo.action",
					saveItemData,
					function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data.personinfo.length > 1) {
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
												"${rootPath}/medicare/ChangeHospitalBusApplyAction!queryChanHospPersonInfo.action?mediSpecDto.arg_name=indi_id&mediSpecDto.arg_value="
														+ indi_id,
												function(json) {
													if (!checkJSONResult(json)) {
														return;
													}
													$.each(json.data.personinfo[0],
														function(key,value) {
																if (!powersi.isnull(value)) {
																	$("#" + key).val(value);
																}
															});
													$("#aab069").val(json.data.personinfo[0].bka008);
													$("[name='mediSpecDto.bka004']").val(json.data.personinfo[0].bka004);
													$("#button_save").attr("disabled",false);
									   });
						        	  dlg.close();
						            }
						       }); 
							    var dlg =popupDiv('#openDiv', '选择参保人信息 ', 500, {
							  		showMax: true, 
							  		isHidden: false
							  	}); 
						} else {
							$.each(json.data.personinfo[0],
									function(key, value) {
										if (!powersi.isnull(value)) {
											$("#" + key).val(value);
										}
									});
							$("#aab069").val(json.data.personinfo[0].bka008);
							$("[name='mediSpecDto.bka004']").val(json.data.personinfo[0].bka004);
							$("#button_save").attr("disabled", false);
						}
					});
		}

		function saveInfo() {
		  	 if(!confirm("是否保存申请信息!")){
		  		 return;
		  	 }
		  	 
		  	if(!checkFormValidtion())
	     	{
		  		return;
			}
			var saveItemData = $("#mainForm").serialize();
		  	postJSON("${rootPath}/medicare/ChangeHospitalBusApplyAction!saveChangeHospitalizedInfo.action",saveItemData,afterSaveItem);
		}

	function afterSaveItem(json){
		if(!checkJSONResult(json)){
			return;
		}else {
			if (json.data == "") {
				popupAlert("保存信息失败!");
				clearall();
			} else {
				popupAlert(json.data);
				if(json.data != ""){
					$("#button_save").attr("disabled",true);
				}
			}
		}
	}

	//选择转入医院
	function chooseToHosp(){	
		popupDialog({
		url: "${rootPath}/pages/biz/medicare/special/ChangeHospitalChoose.jsp",
						onClosed : function() {
							var ret = this.returnValue;
							if (ret != null) {
								var akb020 = ret.akb020;
								var akb021 = ret.akb021;
								document.getElementById("akc172").value = akb021;
								document.getElementById("ake017").value = akb020;
							}
						}
					}, 500, 600);
		}
		
		function clearall() {
			if ($("#aac001").val() != "") {
				if (!confirm("已经录入数据,是否重置!")) {
					return;
				}
			}
			
			$("#aac003").val("");
			$("#aac004").val("");
			$("#aac002").val("");
			$("#aac001").val("");
			$("#aac006").val("");
			$("#aab069").val("");
			$("#bka020").val("");
			$("#bka023").val("");
			$("#bka025").val("");
			$("#akc172").val("");
			$("#bke128").val("");
			$("#bke127").val("");
			$("#bke008").val("");
			$("#arg_value").val("");
			$("#aae013").val("");
			$("#bke009").val("");
			$("#bke036").val("");
			$("#bka004").val("");
			$("#aac066").val("");
			$("#button_save").attr("disabled", true);
		}
	</script>
</body>
</powersi:html>