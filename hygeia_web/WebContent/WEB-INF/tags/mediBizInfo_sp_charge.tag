<%@tag import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="医疗业务信息" description="医疗业务信息"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
    String bka006Filter = " data_value like '132%'";
    String aaa027 = com.powersi.ssm.biz.medicare.common.util.BizHelper.getAaa027();
%>
<script type="text/javascript">
$(document).ready(
		function() {
			var curDate = new Date();
			var month = curDate.getMonth() + 1 < 10 ? "0" + (curDate.getMonth() + 1)
					: curDate.getMonth() + 1 ;
			var day = curDate.getDate() < 10 ? "0" + (curDate.getDate()) : curDate
					.getDate();
			var hours = curDate.getHours() < 10 ? "0" + (curDate.getHours())
					: curDate.getHours();
			var min = curDate.getMinutes() < 10 ? "0" + (curDate.getMinutes())
					: curDate.getMinutes();
			var seconds = curDate.getSeconds() < 10 ? "0" + (curDate.getSeconds())
					: curDate.getSeconds();
			var curDateStr = curDate.getFullYear() +"-"+ month +"-"+ day +" "+ hours +":" + min +":" + seconds;
			$("#aae030").val(curDateStr);
			$("#bka051").val(curDateStr);
			$("#bka006").val("132");
		});
		
/*
 * 加载病区
 */
function selectbka021(){
	var akf001 = $("#akf001").val();
	//加载前先清空病区、医保医师
	$("#bka021").empty();
	$("#ake022").empty();
	$("#bka021").append("<option value='' >" + "请选择..." + "</option>");
	$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
	$("#bka021").change();
	$("#ake022").change();
	if(powersi.isnull(akf001)){
		return;
	}
	$("#bka020").val($("#select2-chosen-1").text());
	postJSON("${rootPath}/diagnose/DiagnoseRegisterAction!loadBka021List.action",
			{"diagnoseInfoDTO.akf001":akf001},
			function(json){
				if(!checkJSONResult(json)){
					return;
				}
				if(json.data !='no'){
					var a = [];
					$.each(json.data, function(key, value) {
						a.push('<option value="');
						a.push(key);
						a.push('"');
						a.push(">");
						a.push(value);
						a.push("</option>");
					});
					$("#bka021").append(a.join(''));
				}
				if (!powersi.isnull(json.message)) {
					alert(json.message);
				}
			});
}
/*
 * 加载医保医师
 */
function selectbka503(){
	//科室
	var akf001 = $("#akf001").val();
	//病区
	var bka021 = $("#bka021").val();
	//
	var ake022 = $("#ake022").val();
	if(powersi.isnull(akf001) || powersi.isnull(bka021)){
		return;
	}
	$("#bka022").val($("#select2-chosen-2").text());
	$("#ake022").empty();
	$("#ake022").append("<option value='' >" + "请选择..." + "</option>");
	postJSON("${rootPath}/diagnose/DiagnoseRegisterAction!loadBka503List.action",
				{"diagnoseInfoDTO.akf001":akf001},
				function(json){
					if(!checkJSONResult(json)){
						return;
					}
					if(json.data != "no"){
						var a = [];
						$.each(json.data, function(key, value) {
							a.push('<option value="');
							a.push(key);
							a.push('"');
							a.push(">");
							a.push(value);
							a.push("</option>");
						});
						$("#ake022").append(a.join(''));
					}
					if (!powersi.isnull(json.message)) {
						alert(json.message);
					}
				});
}


function selectake022(){
	$("#ake022").val($("select2-chosen-3").text());
}
/*
 * 疾病诊断
 */
function chooseDis(str) {
	popupDialog(
			{
				url : "${rootPath}/common/CommonManagerAction!chooseDisease.action",
				onClosed : function() {
					var retValue = this.returnValue;
					if (retValue) {
						$("#bkz101").val(retValue.aka121);
						$("#akc193").val(retValue.aka120);
						return $("#bka006").focus();
					} else {
						$("#bkz101").val("");
						$("#akc193").val("");
						return $("#bkz101").focus();
					}
				}
			}, 500, 600);
}

/*
 * 文本框键入回车时处理事件
 */
function keyDown(para) {
	if (event.keyCode == '13') {
		var filed_name = para.id;
		if ('aae030' == filed_name) {
			return $("#bka025").focus();
		}
		if ('bka025' == filed_name) {
			return $("#bka043").focus();
		}
		if ('bka043' == filed_name) {
			return $("#bka026_name").focus();
		}
		if ('bka026_name' == filed_name) {
			return chooseDis('bka026');
		}
	}
}
</script>

<powersi:panelbox title="业务信息">
	<powersi:editorlayout cols="8">
		<tr>
			<powersi:codeselect id="bka006" key="待遇类型" name="diagnoseInfoDTO.bka006" 
			codeType="bka006" codeFilter="<%=bka006Filter%>" codeSort="1" required="true"
			codeLocal="<%=aaa027%>" displayonly="false" />
			<powersi:textfield id="aae030" name="diagnoseInfoDTO.aae030"
				mask="datetime" key="门慢日期" onKeyDown="keyDown(this)"  required="true" />
			<powersi:textfield id="aaz217" key="就医登记号"
				name="diagnoseInfoDTO.aaz217" readonly="true" />
			<powersi:textfield id="bkz101" key="疾病诊断" readonly="true"
				name="diagnoseInfoDTO.bkz101" buttonText="..." buttonId="disinse"
				onKeyDown="keyDown(this)" onbuttonclick="chooseDis('bka026')"
				required="true" />
			<powersi:hidden id="bka006_reg" name="diagnoseInfoDTO.bka006" />
			<powersi:hidden id="aae030_reg" name="diagnoseInfoDTO.aae030" />
			<powersi:hidden id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001" />
			<powersi:hidden id="aab019" name="diagnoseInfoDTO.aab019" />
			<powersi:hidden id="aaz267" key="选点序列号" name="diagnoseInfoDTO.aaz267" />
			<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130"
				key="aka130" value="11" />
			<powersi:hidden id="aae140" name="diagnoseInfoDTO.aae140" key="险种" />
			<powersi:hidden id="bka001" key="费用批次" name="diagnoseInfoDTO.bka001" />
			<powersi:hidden id="bka039" key="完成情况" name="diagnoseInfoDTO.bka039" />
			<powersi:hidden id="akc193" name="diagnoseInfoDTO.akc193" />
			<powersi:hidden id="bka022" key="病区名称" name="diagnoseInfoDTO.bka022" />
			<powersi:hidden id="bka020" key="科室名称" name="diagnoseInfoDTO.bka020" />
			<powersi:hidden id="bka016" name="diagnoseInfoDTO.bka016" />
			<powersi:hidden id="bka011" name="diagnoseInfoDTO.bka011" />
			<powersi:hidden id="bka012" name="diagnoseInfoDTO.bka012" />
			<powersi:hidden id="kc21id" key="主键" name="diagnoseInfoDTO.kc21id" />
		</tr>
		<tr>
		    <powersi:textfield id="bacu18_show" key="账户余额"
				name="diagnoseInfoDTO.bacu18_show"  readonly="true" />
			<powersi:textfield id="bke004_name" key="其他门慢病种"
				name="diagnoseInfoDTO.bke004_name"  readonly="true" />
			<powersi:textfield id="bkm030" key="有效开始时间"
				name="diagnoseInfoDTO.bkm030"  readonly="true" />
			<powersi:textfield id="bkm031" key="有效结束时间"
			name="diagnoseInfoDTO.bkm031" readonly="true" />
		</tr>
	</powersi:editorlayout>
</powersi:panelbox>