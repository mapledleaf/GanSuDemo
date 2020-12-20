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
	<powersi:head title="修改意外伤害住院申请" />
<body>
	<powersi:form id="mainForm" action="specialManager!modifySpeAppInf.action" namespace="/medicarespecial">
		<!-- 隐藏元素层 -->
		<div style="display: none">
			<powersi:textfield id="pageFlag" name="dto.pageFlag" label="页面标志"/>
			<powersi:textfield id="aae140" name="dto.aae140" key="aae140" label="险种类型"/>
			<powersi:textfield id="aka083" name="dto.aka083" key="aka083" value="122" label="申请类型"/>
			<powersi:textfield id="aaa027" name="dto.aaa027" key="aaa027"   label="统筹区编码"/>
			<powersi:textfield id="aka130" name="dto.aka130" key="aka130" value="12" label="业务类型"/>
			<powersi:textfield id="bka006" name="dto.bka006" key="bka006" value="122" label="待遇类型"/>
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
					<powersi:textfield id="bke001" name="dto.bke001" colspan="3" maxlength="30" label="详细地址" />
					<powersi:textfield id="aae011" name="dto.aae011" disabled="true" label="经办人"/>
				</tr>
			</powersi:editorlayout>
		</powersi:groupbox>
		
		<powersi:groupbox title="详细信息">
			<powersi:editorlayout cols="8">
				<tr>
					<powersi:textfield id="aka121" name="dto.aka121" required="true" readonly="true"
						buttonId="di" onbuttonclick="chooseDis('', aka120,aka121)" label="申请疾病" />
					<powersi:hidden id="aka120" name="dto.aka120" label="疾病编码" />
					<powersi:textfield id="aae019" name="dto.aae019" validate="money" maxlength="12" key="aae019" label="申请限额"/>
					<powersi:textfield id="bke015" name="dto.bke015" key="bke015" label="就诊科室" />
					<powersi:textfield id="bke014" name="dto.bke014" key="bke014" validate="funcCall[checkFunc]" label="科室电话" />
				</tr>
				<tr>
					<powersi:textfield id="bke017" name="dto.bke017" key="bke017" label="申请医师" />
					<powersi:textfield id="bke020" name="dto.bke020" key="bke020" mask="date" label="医师意见时间" />
					<powersi:textfield id="bke018" name="dto.bke018" key="bke018" label="主任医师" />
					<powersi:textfield id="bke019" name="dto.bke019" key="bke019" mask="date" label="主任意见时间" />
				</tr>
				<tr>
					<powersi:textarea id="bke011" rows='3' name="dto.bke011" key="bke011" maxlength="165" colspan="3" label="病情摘要" />
					<powersi:textarea id="aae013" rows='3' name="dto.aae013" key="aae013" maxlength="165" colspan="3" label="备注" />
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
		parent.popupDialogWithParam({
			url: "${rootPath}/pages/biz/medicare/special/hospitalSelect.jsp\?isDesignated=1" + "&dto.aaa027=" +$("#aaa027").val(),
			onClosed: function(){
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