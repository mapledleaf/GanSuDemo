<%@ tag pageEncoding="GBK" body-content="empty" small-icon=""
	display-name="医疗业务信息" description="医疗业务信息"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%
	String bka006Filter = " data_value like '10%' ";
%>
<script type="text/javascript">
	$(document).ready(function() {
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
		$("#bka017").val(curDateStr);
		$("#bka051").val(curDateStr);
		//$("#bka006").val("100");
	});
	
	function setFeeTime(){
		var bka017 = $("#bka017").val();
		var curDate = bka017;
		$("#bka051").val(bka017);
	    var start = new Date(bka017.substr(0,4)+"-"+bka017.substr(4,2)+"-"+bka017.substr(6,2));
	    var now = new Date();
	   	if(start > now){
	    	alert('购药日期不能大于当天！');
	    	$("#bka017").focus();
	    	return;
	    }
	}
	
	function setAka130(){
		var bka006= $("#bka006").val();
	   	if(null != bka006 && bka006 == "101"){
	   		$("[name='diagnoseInfoDTO.aka130']").val("13");
	    }
	}
	
	function queryFreezeInfo() {
		if ($("#aac001").val() == "" || $("#aac001").val() == -1
				|| $("#aae140").val() == "") {
			alert("请先选择正确的人员！");
			return;
		}
	}

	function keyDown(para) {
		if (event.keyCode == '13') {
			var filed_name = para.id;
			if ('bka017' == filed_name) {
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
			if('bka018' == filed_name){
				return choosebka018();
			}
		}
	}
</script>

<powersi:panelbox title="业务信息">
	<powersi:editorlayout cols="8">
		<tr>
			<powersi:codeselect id="bka004_name" key="人员类别"
				name="diagnoseInfoDTO.bka004" codeType="bka004" disabled="true" />
			<powersi:hidden id="bka004" name="diagnoseInfoDTO.bka004" />
			<powersi:codeselect id="baa027_name" key="人员所属统筹区"
				name="diagnoseInfoDTO.baa027" codeType="aaa027" disabled="true" />
			<powersi:hidden id="baa027" name="diagnoseInfoDTO.baa027" />
			<powersi:textfield id="bka017" name="diagnoseInfoDTO.bka017"
				mask="datetime" key="购药日期" onKeyDown="keyDown(this)"
				onchange="setFeeTime()" required="true" />
			<powersi:hidden id="bka017_reg" name="diagnoseInfoDTO.bka017" />
			<powersi:hidden id="bka005" key="公务员级别" name="diagnoseInfoDTO.bka005" />
			<powersi:hidden id="aac004" key="性别" name="diagnoseInfoDTO.aac004" />
			<powersi:hidden id="aka130" name="diagnoseInfoDTO.aka130"
				key="aka130" value="10" />
			<powersi:hidden id="aae140" key="险种" name="diagnoseInfoDTO.aae140"
				value="310" />
			<powersi:hidden id="aac006" key="出生日期" name="diagnoseInfoDTO.aac006" />
			<powersi:hidden id="aab001" key="单位电脑号" name="diagnoseInfoDTO.aab001" />
			<powersi:hidden id="aab019" name="diagnoseInfoDTO.aab019" />
			<powersi:textfield id="bka008" key="单位名称" name="diagnoseInfoDTO.bka008" readonly="true" />
			<powersi:hidden id="bka001" key="费用批次" name="diagnoseInfoDTO.bka001" />
			<powersi:hidden id="bka039" key="完成情况" name="diagnoseInfoDTO.bka039" />
			<powersi:hidden id="bka026" name="diagnoseInfoDTO.bka026" />
		</tr>
	<tr>
			<powersi:textfield id="bka025" key="药店业务号"
				name="diagnoseInfoDTO.bka025" readonly="false"
				onKeyDown="keyDown(this)" required="true"/>
				<powersi:textfield id="aaz267" key="特殊业务申请号"
				name="diagnoseInfoDTO.aaz267" readonly="true"/>
			<powersi:textfield id="aaz217" key="就医登记号"
				name="diagnoseInfoDTO.aaz217" readonly="true" />
		<powersi:textfield id="aka121" key="疾病诊断" readonly="true"
				name="diagnoseInfoDTO.aka121" />
		</tr>
		<tr>
			<powersi:codeselect id="bka006" key="待遇类型"
				name="diagnoseInfoDTO.bka006" codeType="bka006"
				codeFilter="<%=bka006Filter%>" required="true"  onchange="setAka130()"/>
<%-- 			<powersi:textfield id="bka018" key="多诊断" readonly="true"
				name="diagnoseInfoDTO.bka018"  colspan="8" /> --%>
		     <powersi:textfield id="ake1id" key="电子处方流水号" name="diagnoseInfoDTO.ake1id" readonly="true" />
		</tr>
	</powersi:editorlayout>
</powersi:panelbox>