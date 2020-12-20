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
         Date date = new Date();
         SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMddHHmmss");
         String dqrq = dformat.format(date);
         String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
         String aaa027 = BizHelper.getAaa027();
%>
<powersi:html>
<powersi:head title="取消身份登记" />
<%@ include file="/pages/util/header/combogrid.jsp"%>
<script type="text/javascript" src="${rootPath}/resource/js/clinicUtils.js"></script>
<body topmargin="0">
	<div style="display: none;">
		<object id="cardControl"
			classid="clsid:962275D3-CB9F-4CF2-AC8A-33D2D8EFC5C5" width="0"
			height="0" border="0" onerror="popupAlert('读卡控件初始化失败!')"> </object>
	</div>
	<powersi:form id="registerFrom" method="post">
		<powersi:panelbox key="查询条件">
			<powersi:panelbox-toolbar>
				<powersi:buttons>
					<powersi:button id="cancel" label="取消登录(S)" key="button_save"
						onclick="save();" disabled="true" />
					<powersi:button id="btnReset" key="button_clear" onclick="doClear();" label="清屏(C)"  />
				</powersi:buttons>
			</powersi:panelbox-toolbar>
			<powersi:editorlayout cols="0%,10%,10%,10%,15%,10%,10%,10%,25%">
				<powersi:editorlayout-row>
					<td><powersi:codeselect codeType="caa027" id="caa027"
							name="diagnoseInfoDTO.caa027" required="true" headerKey="0"
							label="中心系统" onKeyDown="goNext('arg_name')" /></td>
					<td><select id="arg_name" class="select" onKeyDown="goNext('querystring')">
							<option value="aac002">身份证号码</option>
							<option value="aac001">个人电脑号</option>
							<option value="aac002">社保卡号</option>
					</select></td>
					<td colspan="2"><powersi:textfield id="tracestring" name="tracestring"
							title="请输入信息回车" placeholder="请输入信息回车！" readonly="false"
							 buttonText="读取身份证(G)" onkeydown="findAac001()"
							buttonId="readic_button" buttonDisabled="false" onbuttonclick="readic()" /></td>
							
					<td colspan="4"></td>
				</powersi:editorlayout-row>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:panelbox title="本次登记信息" allowCollapse="false">
	<powersi:editorlayout cols="10">
		<tr>
			<powersi:textfield id="aac003" key="姓名" name="kch2.aac003"  />
			<powersi:textfield id="aac004" key="性别" name="kch2.aac004"  />
			<powersi:textfield id="aac002" key="公民身份号码" name="kch2.aac002" />
			<powersi:textfield id="bka035" key="人员类别" name="kch2.bka035"  />
			<powersi:textfield id="aac006" key="出生日期" name="kch2.aac006"  />
			
		</tr>
		<tr>
			<powersi:textfield id="aab069" key="性质" name="kch2.aab069"  />
			<powersi:textfield id="" key="体检套餐" name="kch2"  />
			<powersi:textfield id="bke069" key="联系电话" name="kch2.bke069"  />
			<powersi:textfield id="bkh019" key="申报日期" name="kch2.bkh019"  />
			<powersi:textfield id="bkh017" key="体检进度" name="kch2.bkh017"  />		
		</tr>
		<tr>
			<powersi:textfield id="aaa028" key="所属中心" name="kch2.aaa028"  />	
			<powersi:textfield id="bkh014" key="体检登记号" name="kch2.bkh014"  />
			<powersi:textfield id="bkh016" key="登记日期" name="kch2.bkh016" mask="date" />
			<powersi:textfield id="aab069" key="所属单位" name="kch2.aab069"  />
		</tr>
	</powersi:editorlayout>
</powersi:panelbox>
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="querystring" name="inHospitalDTO.querystring" />
		<powersi:hidden id="aaa027" name="kch2.aaa027" />
	</powersi:form>
</body>
<script type="text/javascript">
		var objCard = null;
		var saveRegisterSpecialFlag = false;
		$(document).ready(function() {
			$("#tracestring").focus();
		});
		
		/*读卡获取后台信息*/
		function readic() {
			registerReset();
			iReadCardBase();
			if (powersi.isnull($("#bke548").val())) {
				return;
			}
			findAac002Special("");
		}
		
		/*读卡*/
		function iReadCardBase() {
			loadCardControl();
			if (objCard.object != null) {
				var bke548 = null;// 读卡返回
				bke548 = objCard.ReadCardBase();
				$("#bke548").val(bke548);
			}
		}
		
		/*加载控件*/
		function loadCardControl() {
			try {
				if (objCard == null || objCard.object == null) {
					objCard = document.getElementById("cardControl");
					if (objCard.object == null) {
						popupAlert("请先安装读卡控件!");
					}
				}
			} catch (e) {
				popupAlert("请先安装读卡控件!");
			}
		}
		
		/*查询参保信息*/
		function findAac001() {
			if (window.event.keyCode == 13) {
				$("#bke548").val("");
				$("#querystring").val("");
				var tracestring = powersi.trim($("#tracestring").val());
				if (powersi.isnull(tracestring)) {
					popupAlert("请输入有效查询条件!");
					return;
				}
				$("#querystring").prop("disabled", true);
				findAac002(tracestring);
				$("#querystring").prop("disabled", false);
			}
		}
   
		/*登记界面要支持：社会保障号码、电脑号、社会保障号码*/
		function findAac002(tracestring) {
			if (powersi.isnull(tracestring)) {
				$("#querystring").val("");
			} else {
				$("#querystring").val(tracestring);
			}
			var registerData = $("#registerFrom").serialize();
			postJSON(
					"${rootPath}/health/ExaminationAction!findAac001.action",
					registerSpecialData, function(json) {
						if (!checkJSONResult(json)) {
							return;
						}
						if (json.data != "no" && json.data.length > 1) {
							if (powersi.isnull(tracestring)) {
								chooseAac002Special(json.message);
								$("#querystring").val(json.message);
								$("#tracestring").val(json.message);
							} else {
								chooseAac002Special(tracestring);
							}
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
	
	function ReadIDCardBase(){
		objCard = document.getElementById("cardControl");  //获取社保卡控件对象
		var caa027 = $("#caa027").val();
		_VerifyIDCardWithDB(caa027, function(json) {
			if (json.data == null ) {
				if (json.errortype == 1) {
					popupAlert(json.message, "错误", "error");
					return;
				}
				
			}else{
				var retMsg = [];
				retMsg = objCard.ReadIDCardBase();          //获取身份证基本信息
				var photoMsg = objCard.GetIDCardPhoto();        //获取照片信息
				retMsg = retMsg.split("|");
				var flag = retMsg[0];
				if (flag=="0") {
					popupAlert("读取身份证号码出错,请插入身份证卡片!", "错误", "error");
					return;
				}
				var aac003 = retMsg[1];
				var aac004 = retMsg[2];
				var aac005 = retMsg[3];
				var aac006 = retMsg[4];
				var aae006 = retMsg[5];
				var aac002 = retMsg[6];
				var kfd = retMsg[7];
				var paramv = "aac002=" + aac002+"&aac003="+aac003+"&aac004="+aac004+"&aac005="+aac005+"&aac006="+aac006+"&aae006="+aae006+"&kfd="+kfd;
				var param  = encodeURI(encodeURI(paramv));
				popupDialog({
					url: rootPath + "/pages/biz/medicare/diagnose/diagnoseIcard.jsp?"+ param,
					onClosed: function() {
						var ret = this.returnValue;
						if(ret!=null){
							$("#querystring").val(aac002);
							 afterChoosePerson();
						}
					 
					}
				}, 220, 500);
			}
		});
		return;
	}

	

	
	function isNotBlank(str) {
		if (typeof (str) == "undefined" || str == null || str == "") {
			return false;
		} else {
			return true;
		}
	}

	/*清屏*/
	function doClear() {
		$("#aac003").val('');
		$("#aac004_name").val('');
		$("#aac002").val('');
		$("#aac006").val('');
		$("#bka008").val('');
		
		$("#bka057").val('');
		$("#bka058").val('');
		$("#aka069").val('');


		$("#querystring").attr("disabled", false);
		$("#querystring").val('');
		$("#querystring").focus();
	}

	function getPerson(para) {
		if (event.keyCode == '13') {
			//清除先有记录
			var argName = $("#arg_name").val();
			var queryStr = $("#querystring").val();
			doClear();
			$("#arg_name").val(argName);
			$("#querystring").val(queryStr);
			try {
				var e = window.event || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					e.stopPropagation();
				}
				event.returnValue = false;
			} catch (ex) {
			}
			afterChoosePerson();
		}
	}

	

</script>
</powersi:html>