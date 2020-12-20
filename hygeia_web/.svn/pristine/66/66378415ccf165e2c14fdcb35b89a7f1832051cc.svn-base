<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String type = (String)request.getParameter("type");
%>

<!DOCTYPE HTML>
<html>
<powersi:head title="社保卡激活" />
<script src="${rootPath}/resource/js/md5.js" type="text/javascript"></script>
  <body>
  	<powersi:form id="mainForm" name="mainForm" action="cardActivateAction!queryPersonCardInfo.action" namespace="/common" method="post" >
    	<powersi:panelbox key="panel_query" allowCollapse="false">
        	<powersi:editorlayout cols="8">
				<powersi:codeselect id="argName" label="查询条件" cssClass="select2" list="#{'aac002':'社会保障号'}" />
					<td colspan="2"><powersi:textfield id="querystring" readonly="true"
							name="querystring" title="请输入信息回车" placeholder=""
							onkeydown="" buttonText="读卡" 
							buttonId="readic_button" buttonDisabled="false"/></td>			
       		</powersi:editorlayout>
    	</powersi:panelbox>
		
		<powersi:panelbox title="社保卡信息" allowCollapse="false">
								<powersi:panelbox-toolbar>
									<powersi:button id="save" key="button_save" value="激活" onclick="saveinfo()" buttonIcon="icon-check"/>
					     		</powersi:panelbox-toolbar>
     	  <powersi:datagrid id="cardinfo"  height="200" delayLoad="true">
					<powersi:datagrid-column name="issuer_code"        id="issuer_code"  	  label="发卡地区行政编码"       width="150"/>
					<powersi:datagrid-column name="sscard_no"   	   id="sscard_no"   	  label="社会保障号"            width="180"/>
					<powersi:datagrid-column name="card_no"            id="card_no"   		  label="卡号"                  width="100"/>
					<powersi:datagrid-column name="identifier_code"    id="identifier_code"   label="卡识别码"               width="280"/>
					<powersi:datagrid-column name="name" 	           id="name"   			  label="姓名"                  width="100"/>
					<powersi:datagrid-column name="card_reset_info"    id="card_reset_info"   label="卡复位信息"             width="100"/>
					<powersi:datagrid-column name="spec_version" 	   id="spec_version"      label="规范版本"      			width="50"/>
					<powersi:datagrid-column name="issue_date" 	       id="issue_date"        label="发卡日期"    			width="200"/>
					<powersi:datagrid-column name="card_validity" 	   id="card_validity"     label="卡有效期"      			width="100"/>
					<powersi:datagrid-column name="terminal_no" 	   id="terminal_no"       label="终端机编号"      		width="100"/>
					<powersi:datagrid-column name="equipment_no" 	   id="equipment_no"      label="终端设备号"      		width="200"/>
			</powersi:datagrid> 
     	</powersi:panelbox>
		
		<powersi:panelbox title="医保系统人员信息" allowCollapse="false">
								
			<powersi:datagrid id="auditgrid"  height="200" delayLoad="true">
					<powersi:datagrid-column name="aac001"           id="aac001"   label="电脑号"       width="100"/>
					<powersi:datagrid-column name="aac003"   		 id="aac003"   label="姓名"         width="200"/>
					<powersi:datagrid-column name="aac004"           id="aac004"   label="性别"       render="sex" width="100"/>
					<powersi:datagrid-column name="aac002" 	         id="aac002"   label="社会保障号"    width="200"/>
					<powersi:datagrid-column name="aac006" 	         id="aac006"   label="出生日期"      width="100"/>
					<powersi:datagrid-column name="aaz502" 	         id="aaz502"   label="是否激活"    render="cardStatu"  width="100"/>
					<powersi:datagrid-column name="aaz500" 	         id="aaz500"   label="社保卡号"         width="100"/>
			</powersi:datagrid>
     	</powersi:panelbox>
     	
		<powersi:hidden id="bke548" name="inHospitalDTO.bke548" />
		<powersi:hidden id="caa027" name="inHospitalDTO.caa027" value="4303-zg"/>
		<powersi:hidden id="aaa027" name="diagnoseInfoDTO.aaa027" />
		<powersi:hidden id="sbkjh" value="1" />
    </powersi:form> 
    <powersi:errors/>
    
<script type="text/javascript">

//激活
function saveinfo(){
	var row= cardinfo.getSelectedRow();
	if (row == null) {
		popupAlert("请选择一条需要进行激活的记录！", "提示", "info");
		return;
	}	
	var querystring=row.sscard_no;
	var aab034=row.issuer_code;
	var aac002=row.sscard_no;
	var aaz500=row.card_no;
	postJSON(
			"${rootPath}/diagnose/GetPersonInfoAction!getPersonInfo.action?diagnoseInfoDTO.arg_name="
					+ "aac002"
					+ "&diagnoseInfoDTO.arg_value="
					+ querystring
					+ "&diagnoseInfoDTO.bka006="
					+ "110"
					+ "&diagnoseInfoDTO.aaz509="
					+ "1"
					+ "&diagnoseInfoDTO.aka130="
					+ "11",
		function(json){
		if (!checkJSONResult(json)) {
			return;
		}		
		if (json.message == 'multi-row') {
			choosepersonlist(querystring,aab034,aac002,aaz500);
			return;
		}
		postJSON("${rootPath}/common/CommonManagerAction!queryPersonCardInfo.action?inHospitalDTO.aac001="+json.data.personinfo.aac001
				+"&inHospitalDTO.baa027="+aab034
				+"&inHospitalDTO.aac002="+aac002
				+"&inHospitalDTO.aaz500="+aaz500,
				function(json){
				if (!checkJSONResult(json)) {
					return;
				}				
				popupAlert(json.data.message);
				if(json.data.issuccess=='1'){
					getPersonbyaac002(querystring);
				}
			});		
		});

}

function choosepersonlist(querystring,aab034,aac002,aaz500) {
	var row= cardinfo.getSelectedRow();
	popupDialog(
			{
				url : "${rootPath}/diagnose/GetPersonInfoAction!chooseperson.action?diagnoseInfoDTO.arg_value="
						+ querystring,
				onClosed : function() {
					var ret = this.returnValue;
					if (ret) {
						indi_id = ret.aac001;
						postJSON("${rootPath}/common/CommonManagerAction!queryPersonCardInfo.action?inHospitalDTO.aac001="+indi_id
								+"&inHospitalDTO.baa027="+aab034
								+"&inHospitalDTO.aac002="+aac002
								+"&inHospitalDTO.aaz500="+aaz500,
								function(json){
								if (!checkJSONResult(json)) {
									return;
								}				
								popupAlert(json.data.message);
								if(json.data.issuccess=='1'){
									getPersonbyaac002(querystring);
								}
							});						
					}
				}
			}, 500, 600);
}

function readIcCard() {
	getPerson();
}

function getPerson(para){
	if(!powersi.isnull($("#bke548").val())){
		var icFieldStr = "issuer_code|sscard_no|card_no|identifier_code|name|card_reset_info|"
			+ "spec_version|issue_date|card_validity|terminal_no|equipment_no|";
				
		var _bke548 = $("#bke548").val().split('|');
		
		var issuer_code = _bke548[0];
		var sscard_no = _bke548[1];
		var card_no = _bke548[2];
		var identifier_code = _bke548[3];
		var name = _bke548[4];
		var card_reset_info = _bke548[5];
		var spec_version = _bke548[6];
		var issue_date = _bke548[7];
		var card_validity = _bke548[8];
		var terminal_no = _bke548[9];
		var equipment_no = _bke548[10];
		
		var jsonFee = {
			"issuer_code" : issuer_code,
			"sscard_no" : sscard_no,
			"card_no" : card_no,
			"identifier_code" : identifier_code,
			"name" : name,
			"card_reset_info" : card_reset_info,
			"spec_version" : spec_version,
			"issue_date" : issue_date,
			"card_validity" : card_validity,
			"terminal_no" : terminal_no,
			"equipment_no" : equipment_no
		};

		cardinfo.add(jsonFee);
	}
}


function getPersonbyaac002(para) {
		var querystring = powersi.trim($("#querystring").val());
		if (powersi.isnull(querystring)) {
			alert("请输入有效查询条件!");
			return;
		}
		postJSON("${rootPath}/common/CommonManagerAction!queryAC01Info.action?inHospitalDTO.argName="
						+ $("#argName").val()
						+ "&inHospitalDTO.aac002="
						+querystring,
				function(json){
					if (!checkJSONResult(json)) {
						return;
					}				
					auditgrid.loadData(json.data);
				});
	}

function sex(row, index, value){
	var str = value;
	if(value == "1"){
		str = "男";
	}else if(value == "2"){
		str = "女";
	}else if(value == "9"){
		str = "未说明性别";
	}
	return str;
}

function cardStatu(row, index, value){
	var str = value;
	if(value == "0"){
		str = "封存";
	}else if(value == "1"){
		str = "正常";
	}else if(value == "2"){
		str = "挂失";
	}else if(value == "3"){
		str = "应用锁定";
	}else if(value == "9"){
		str = "注销";
	}
	return str;
}
</script>  
</body>
</html>
