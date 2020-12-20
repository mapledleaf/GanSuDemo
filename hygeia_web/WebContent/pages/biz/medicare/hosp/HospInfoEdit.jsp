<%@page import="com.powersi.ssm.biz.medicare.common.util.BizHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="mediTag" uri="http://www.powersi.com.cn/medicaretaglib"%>
<%
	request.setAttribute("path", request.getContextPath() + "/medicarehospital/medicalInstitutions"); 
	request.setAttribute("aaa027", BizHelper.getAaa027());
%>
<powersi:html >
<powersi:head title="医疗机构及药店管理" />
<body>
<powersi:form id="dataForm" action="">
	<!-- 右侧详细信息 -->
	<powersi:panelbox title="组织机构信息">
		<powersi:panelbox-toolbar>
			<powersi:button key="button_save" onclick="saveinfo()" id="saveBtn" />
		</powersi:panelbox-toolbar>
		<div style="display:none"><!-- 隐藏 -->
			<powersi:textfield id="aka020" name="hospBasicsDto.aka020"  key="aka020"/><!-- 拼音助记码 -->
			<powersi:textfield id="aka021" name="hospBasicsDto.aka021"  key="aka021"/><!-- 五笔助记码 -->
			<powersi:textfield id="aaz001" name="hospBasicsDto.aaz001"  key="aaz001"/><!-- 组织id -->
			<powersi:textfield id="aaz017" name="hospBasicsDto.aaz017"  key="aaz017"/><!-- 组织登记事件ID -->
			<powersi:textfield id="aae013" name="hospBasicsDto.aae013"  key="aae013"/><!-- 备注 -->
			<powersi:textfield id="aaz269" name="hospBasicsDto.aaz269"  key="aaz269"/><!-- 医疗机构及药店id -->
			<powersi:textfield id="aaz003" name="hospBasicsDto.aaz003"  key="aaz003"/><!-- 当事人银行账号id -->
			<powersi:textfield id="aaz065" name="hospBasicsDto.aaz065"  key="aaz065"/><!-- 银行id -->
			<powersi:textfield id="aae008" name="hospBasicsDto.aae008"  key="aae008"/><!-- 银行编号 -->
		</div>
		
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:textfield id="aab003" name="hospBasicsDto.aab003"  key="aab003"  label="社会信用代码" required="true" readonly="true"/>	
				<powersi:codeselect id="aab301" name="hospBasicsDto.aab301" key="aab301" 
					codeType="aaa027" value="${aaa027 }" label="所属统筹区" required="true" displayonly="true"/>
				<powersi:textfield id="akb020" name="hospBasicsDto.akb020"  key="akb020"  label="医疗机构编号" required="true" readonly="true"/>	
				<powersi:textfield id="aab069" name="hospBasicsDto.aab069"  key="aab069" required="true" label="医疗机构名称" />	
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aae007" name="hospBasicsDto.aae007"  key="aae007" validate="integer,min[0],maxSize[6]"/>
				<powersi:textfield id="aae004" name="hospBasicsDto.aae004"  key="aae004"/>	
				<powersi:textfield id="aae005" name="hospBasicsDto.aae005"  key="aae005" maxlength="50"/>
				<powersi:textfield id="aae159" name="hospBasicsDto.aae159"  key="aae159" validate="funcCall[checkFunc]"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aab013" name="hospBasicsDto.aab013"  key="aab013" label="法人姓名" maxlength="50" />
				<powersi:textfield id="aab014" name="hospBasicsDto.aab014"  key="aab014" maxlength="18" label="法人身份证号" validate="funcCall[checkFunc]"/>
				<powersi:textfield id="bae001" name="hospBasicsDto.bae001"  key="bae001" maxlength="11" validate="funcCall[checkFunc]"/>
				<powersi:textfield id="aae006" name="hospBasicsDto.aae006"  key="aae006" colspan="6" maxlength="100"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="aae051" name="hospBasicsDto.aae051"  key="aae051" readonly="true"/>
				<powersi:textfield id="aae049" name="hospBasicsDto.aae049"  key="aae049" mask="date" readonly="true"/>		
				<powersi:textfield id="aae048" name="hospBasicsDto.aae048"  key="aae048" maxlength="100" readonly="true"/>
				<powersi:textfield id="aae047" name="hospBasicsDto.aae047"  key="aae047" mask="date" readonly="true"/>	
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox title="医疗机构或药店信息">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:codeselect id="bke301" name="hospBasicsDto.bke301" label="医院级别" key="bke301" codeType="bke301" displayonly="true"/>
				<powersi:codeselect id="aka101" name="hospBasicsDto.aka101" key="aka101" codeType="aka101" displayonly="true" />	
				<powersi:codeselect id="bke302" name="hospBasicsDto.bke302" key="bke302" codeType="bke302" displayonly="true" />
				<powersi:codeselect id="akb022" name="hospBasicsDto.akb022" key="akb022" codeType="akb022"  label="机构服务类型" displayonly="true" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:codeselect id="akb023" name="hospBasicsDto.akb023" key="akb023" codeType="akb023" displayonly="true" label="医疗机构类型"/>
				<powersi:codeselect id="aka086" name="hospBasicsDto.aka086" key="aka086" codeType="aka086" displayonly="true" label="业务开展类别"/>
				<powersi:codeselect id="aab020" name="hospBasicsDto.aab020" key="aab020" codeType="aab020" displayonly="true"/>
				<powersi:codeselect id="bke304" name="hospBasicsDto.bke304" key="bke304" codeType="bke304" displayonly="true"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:codeselect id="bke305" name="hospBasicsDto.bke305" key="bke305" codeType="bke305" displayonly="true"/>
				<powersi:codeselect id="bkf030" name="hospBasicsDto.bkf030" key="bkf030" codeType="bkf030" displayonly="true" label="营利类型"/>	
				<powersi:textfield id="akf007" name="hospBasicsDto.akf007"  key="akf007" maxlength="20" readonly="true"/>	
				<powersi:textfield id="akf006" name="hospBasicsDto.akf006"  key="akf006" label="药品经营许可证" readonly="true" maxlength="20"/>	
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="akf009" name="hospBasicsDto.akf009"  key="akf009" validate="integer,min[0],maxSize[8]" />
				<powersi:textfield id="akf010" name="hospBasicsDto.akf010"  key="akf010" validate="integer,min[0],maxSize[8]" label="重点科室数"/>
				<powersi:textfield id="akf015" name="hospBasicsDto.akf015"  key="akf015" validate="integer,min[0],maxSize[8]" />
				<powersi:textfield id="akf008" name="hospBasicsDto.akf008"  key="akf008" validate="integer,min[0],maxSize[8]" />
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="akf011" name="hospBasicsDto.akf011"  key="akf011" validate="integer,min[0],maxSize[8]" />
				<powersi:textfield id="akf014" name="hospBasicsDto.akf014"  key="akf014" validate="integer,min[0],maxSize[8]" />	
				<powersi:textfield id="akf012" name="hospBasicsDto.akf012"  key="akf012" validate="integer,min[0],maxSize[8]" />	
				<powersi:textfield id="akf013" name="hospBasicsDto.akf013"  key="akf013" validate="integer,min[0],maxSize[8]" label="专业技术人数"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="akf017" name="hospBasicsDto.akf017"  key="akf017" validate="integer,min[0],maxSize[8]" />
				<powersi:textfield id="akf018" name="hospBasicsDto.akf018"  key="akf018" validate="integer,min[0],maxSize[8]" />
				<powersi:textfield id="akf016" name="hospBasicsDto.akf016"  key="akf016" validate="integer,min[0],maxSize[8]"/>
				<td colspan="1"></td>	
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox title="银行账户信息">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:textfield id="bke306" name="hospBasicsDto.bke306" key="银行名称" readonly="true" />
				<powersi:textfield id="aae009" name="hospBasicsDto.aae009"  key="aae009" lable="户名" readonly="true" maxlength="100"/>
				<powersi:textfield id="aae010" name="hospBasicsDto.aae010"  key="aae010" lable="银行帐号" readonly="true" maxlength="40"/>
				<td colspan="1"></td>	
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
	
	<powersi:panelbox title="医疗机构信息系统情况">
		<powersi:editorlayout cols="8">
			<powersi:editorlayout-row>
				<powersi:textfield id="bkf021" name="hospBasicsDto.bkf021"  key="系统厂商" label="系统厂商" maxlength="100"/>
				<powersi:codeselect id="bkf022" name="hospBasicsDto.bkf022" codeType="bkf022" label="网络公司" />
				<powersi:codeselect id="bkf023" name="hospBasicsDto.bkf023" codeType="bkf023" label="异地业务开通"/>
				<powersi:textfield id="bkf024" name="hospBasicsDto.bkf024"  key="系统维护情况" lable="系统维护情况" maxlength="100"/>
			</powersi:editorlayout-row>
			<powersi:editorlayout-row>
				<powersi:textfield id="bkf025" name="hospBasicsDto.bkf025"  key="接口模式" lable="接口模式" maxlength="100"/>
				<powersi:textfield id="bkf026" name="hospBasicsDto.bkf026"  key="软硬件情况" lable="软硬件情况" maxlength="100"/>
				<powersi:textfield id="bkf027" name="hospBasicsDto.bkf027"  key="网络安防情况" lable="网络安防情况" maxlength="100"/>
				<td colspan="1"></td>	
			</powersi:editorlayout-row>
		</powersi:editorlayout>
	</powersi:panelbox>
</powersi:form>
<powersi:errors />
</body>
<script type="text/javascript">
//保存
function saveinfo(){
	if(!checkForm(dataForm)){
 		return;
 	}
	popupConfirm("确定把修改后的数据提交至中心审核？", "提示", function(isOk) {
			if (isOk) {
				var param = $("#dataForm").serialize();
				postJSON("${rootPath}/medicare/HospElectpresAction!editHospBasics.action" ,param,function(json){
					if(!checkJSONResult(json)){
					    return;
				    }
					popupAlert(json.data,"提示","success");
				});
			}
		});
	
}

function gridRowRender(rowdata, rowid){
	if (rowdata.bkl006 != null) {
		return 'style="color:#ff0000;"';
	}
}

function checkFunc(field){
	if('aab014' == field.attr("id") && field.val() != '') { //校验身份证号
		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(!reg.test(field.val())){
			return "*非法";
		}
	}
	

	if('bae001' == field.attr("id") && field.val() != '') { //校验手机号码
		var reg = /^1[3-9]\d{9}$/;
		if(!reg.test(field.val())){
			return "*非法";
		}
	}
	
	if('aae159' == field.attr("id") && field.val() != '') { //校验电子邮箱
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if(!reg.test(field.val())){
			return "*非法";
		}
	}
}

</script>
</powersi:html>