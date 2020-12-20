<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="biz" uri="http://www.powersi.com.cn/biztags"%>
<%@ page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarespecial/specialManager");
	String aaa027 = BizHelper.getAaa027();
	String hospital_id = BizHelper.getAkb020();
	String hospital_name = BizHelper.getAkb021();
	String loginUser = BizHelper.getLoginUser();
	String userName = BizHelper.getUserName();
%>

<powersi:html>
	<base target="_self">
	<powersi:head title="修改转诊转院申请" />
<body>
	<powersi:form id="mainForm" action="specialManager!modifySpeAppInf.action" namespace="/medicarespecial">
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="123" label="申请类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027"   label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="12" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="123" label="待遇类型"/>
			<powersi:textfield id="aae016" name="dto.aae016" key="aae016" label="审核状态"/>
			<powersi:textfield id="aaz267" name="dto.aaz267" key="aaz267" label="特殊业务申请ID"/>
			<powersi:textfield id="caa027" name="dto.caa027" key="caa027" label="中心系统"/>
		</div>
		
		<powersi:panelbox title="人员信息">
			<powersi:panelbox-toolbar>
					<powersi:button key="button_save" data-hotkey='alt+S' onclick="saveinfo();" />
					<powersi:button key="button_close" data-hotkey='alt+B' value="关 闭" onclick="closeThis();" />				
			</powersi:panelbox-toolbar>
			
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aac001" name="dto.aac001" key="aac001" readonly="true" label="个人电脑号" />
					<powersi:textfield id="aac003" name="dto.aac003" key="aac003" disabled="true" label="姓名" />
					<powersi:codeselect id="aac004" name="dto.aac004" key="aac004" disabled="true" codeType="aac004" headerValue=" " headerKey="" label="性别" />
					<powersi:textfield id="aac002" name="dto.aac002" key="aac002" disabled="true" label="身份证号" />
				</tr>
				<tr>
					<powersi:textfield id="aac006" name="dto.aac006" key="aac006" disabled="true" label="出生日期"/>
					<powersi:codeselect id="bka035" name="dto.bka035" key="bka035" disabled="true" codeType="bka035" headerValue=" " headerKey="" label="人员类别"/>
					<powersi:textfield id="aab069" name="dto.aab069" key="aab069" disabled="true" label="单位名称"/>
					<td colspan="2"/>
				</tr>
			</powersi:editorlayout>
		</powersi:panelbox>
		
		<powersi:groupbox title="基本信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="akb021" name="dto.akb021" required="true" disabled="true" label="申请医院"/>
					<powersi:textfield id="aae127" name="dto.aae127" required="true" disabled="true" key="aae127" mask="date" label="申请日期" />
					<powersi:textfield id="aae030" name="dto.aae030" required="true" key="aae030" mask="date" validate="funcCall[checkFunc]" label="生效日期" />
					<powersi:textfield id="aae031" name="dto.aae031" required="true" key="aae031" mask="date" validate="funcCall[checkFunc]" label="失效日期" />
					
				</tr>
				<tr>
					<powersi:textfield id="bke033" name="dto.bke033" key="bke033" validate="funcCall[checkFunc]" label="联系电话" />
					<powersi:textfield id="bke001" name="dto.bke001" maxlength="30" label="详细地址" />
					<powersi:textfield id="aae011" name="dto.aae011" disabled="true" label="经办人"/>
					<powersi:codeselect id="bke035" name ="dto.bke035" list="%{#{'10':'','11':'一站式转诊'}}" disabled="true" label="申请原因"/>
				</tr>
				<tr>
					<powersi:textfield id="aab299" name="dto.aab299" key="aab299" label="转诊城市" />
					<powersi:textfield id="ake014" name="dto.ake014" required="true" key="ake014" mask="date" label="转院日期" />
					<powersi:codeselect id="bkm301" name="dto.bkm301" key="bkm301" codeType="bkm301" label="转诊类型" />
					<powersi:codeselect id="bke049" name="dto.bke049" key="bke049" codeType="bke049" label="转诊原因" />
				</tr>
				<tr>
					<powersi:textarea id="bke011" rows='3' name="dto.bke011" key="bke011" maxlength="165" colspan="3" label="转诊原因详情" />
					<powersi:textarea id="aae013" rows='3' name="dto.aae013" key="aae013" maxlength="165" colspan="3" label="备注（医嘱）" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="转出信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="bke053" name="dto.bke053" required="true" readonly="true"
						buttonId="hospse" onbuttonclick="chooseHosp(aaz286, bke053)" label="转出医院"/>
					<powersi:hidden id="aaz286" name="dto.aaz286" label="转出医院编码" />
					
					<powersi:textfield id="bke051" name="dto.bke051" required="true" readonly="true"
						buttonId="di" onbuttonclick="chooseDis('', bke046,bke051)" label="转出医院诊断" />
					<powersi:hidden id="bke046" name="dto.bke046" label="转出医院诊断编码" />
					<td colspan="4"/>
				</tr>
				<tr>
					<powersi:textarea id="bke007" rows='3' name="dto.bke007" key="bke007" maxlength="340" colspan="3" label="病情摘要" />
					<powersi:textarea id="bke008" rows='3' name="dto.bke008" key="bke008" maxlength="165" colspan="3" label="转出医院意见" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="转入信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="akc172" name="dto.akc172" required="true" readonly="true"
						buttonId="hospse" onbuttonclick="chooseHosp(ake017, akc172)" label="转入医院"/>
					<powersi:hidden id="ake017" name="dto.ake017" label="转入医院编码" />
					
					<powersi:textfield id="bke003" name="dto.bke003" required="true" readonly="true"
						buttonId="di" onbuttonclick="chooseDis('', bke038,bke003)" label="转入医院诊断" />
					<powersi:hidden id="bke038" name="dto.bke038" label="转入医院诊断编码" />
					<td colspan="4"/>
				</tr>
				<tr>
					<powersi:textarea id="bke009" rows='3' name="dto.bke009" key="bke009" maxlength="340" colspan="3" label="病情摘要" />
					<powersi:textarea id="bke010" rows='3' name="dto.bke010" key="bke010" maxlength="165" colspan="3" label="转入医院意见" />
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
	
	<powersi:errors />
	</powersi:form>
</body>
<script type="text/javascript">
	var pwidth=1000,pheight=500
	var initFormInf;
	
	
	$(document).ready(function() {
		initForm();
		setClientScreenWH();
		initFormInf = $("#mainForm").serialize();
	});
	
	
	function setClientScreenWH() {
		pwidth=0.9*$(window).width();
	}


	function saveinfo() {
		
		if(initFormInf == $("#mainForm").serialize()) {
			popupAlert("数据未作修改，无需保存！", "提示", "warn");
			return
		}
		
		if (!checkForm("#mainForm")) {
			return false;
		}
		
		
		var param = $("#mainForm").serialize();
	    postJSON("${path}!modifySpeAppInf.action", param, function(json){
	    	if(!checkJSONResult(json,"popup")){
			    return;
		    } else {
		    	popupAlert("保存成功", "提示", "success",function(){
			    	closeDialog(json.data);
			    });
		    }
	    });
	}
	
	
	//选择疾病
	function chooseDis(aka035, objindex, objname) {
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/diseaseSelect.jsp\?dto.aka035=" + aka035 + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
				var ret = this.returnValue;
				if(ret){
					objindex.value = ret.aka120;
			   	  	objname.value = ret.aka121;
				}		
			}
		}, null,500,pwidth);
	}
	
	//选择医疗机构
	function chooseHosp(objindex, objname) {
		var akb020_not = '';
		if('aaz286' == objindex.id) {
			akb020_not = $("#ake017").val();
		} else if('ake017' == objindex.id) {
			akb020_not = $("#aaz286").val();
		}
		
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp\?isDesignated=1" + "&dto.aaa027=" +$("#aaa027").val() + "&akb020_not=" + akb020_not,
			onClosed: function(){
				$(objname).focus();
				var ret = this.returnValue;
				if(ret){
					objindex.value = ret.akb020;
			   	  	objname.value = ret.aab069;
				}		
			}
		}, null,500,pwidth);
		
	}
	
	
	function initForm() {
	}
	
	
	function getdateString(){
		var dt = new Date();
		var strDate = dt.getFullYear() ;
		if (dt.getMonth() + 1 <10) strDate += "0";
		strDate += '' + (dt.getMonth()+1);
		if (dt.getDate() <10) strDate += "0";
		strDate += '' + dt.getDate();
		return strDate;
	}
	
	
	//输入校验
	function checkFunc(field){

		if('aae030' == field.attr("id")) { //校验生效日期
			var aae030 = $("#aae030").val();
			var aae031 = $("#aae031").val();
			if(aae031 < aae030) {
				return "*生效日期 不能大于失效日期";
			}
		}
		
		
		if('aae031' == field.attr("id")) { //校验失效日期
			var aae030 = $("#aae030").val();
			var aae031 = $("#aae031").val();
			if(aae031 < aae030) {
				return "*失效日期 不能小于生效日期";
			}
		}
		
		
		if('bke033' == field.attr("id")) { //校验联系电话
			var reg = new RegExp('^[0-9]{0,20}$');
			if(!reg.test(field.val())) {
				//return "\*联系电话 只能是0~20位纯数字";
			}
		}
		
		
		if('bke014' == field.attr("id")) { //校验科室电话
			var reg = new RegExp('^[0-9]{0,20}$');
			if(!reg.test(field.val())) {
				return "*科室电话 只能是0~20位纯数字";
			}
		}
	}
	
	
	function closeThis() {
		if(initFormInf == $("#mainForm").serialize()) {
			closeDialog();
			return;
		}
		
		popupConfirm('已经录入了数据，确定关闭吗？','提示', function(yes){
			if(yes){
				closeDialog();
			}
		});
	}
</script>
</powersi:html>